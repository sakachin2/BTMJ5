//*CID://+vaz2R~:                                   update#=   63; //+vaz2R~
//***********************************************                  //~@@@1I~
//2025/03/02 vaz2 TestOption DUMP=SDCARD-->Dump to File(if off write to logcat). currently requires restart//+vaz2I~
//2021/12/23 vai7 Even not debug mode, write exception to terminal //~vai3I~
//2021/10/22 vaf3 Dump to logcat unconditionally before open       //~vaf3I~
//2021/09/21 vaeb try not cache but file, cache miss line?         //~vaebI~
//1ak2 2021/09/04 access external audio file                       //~1ak2I~
//2021/08/25 vad5 move Dump.txt to cache to avoid /sdcard          //~vad5I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//1Ad8 2015/07/21 (Asgts)//1A4h 2014/12/03 catch OutOfMemory(Ajagot1w)//1B0g//~1Ad8I~
//1Ab9 2015/05/09 Dump byte[]                                      //~1Ab9I~
//1A6A 2015/02/20 Another Trace option if (Dump.C) for canvas drawing//~1A6AI~
//1075:121207 control dumptrace by manifest debuggable option      //~v107I~
//@@@1 prepend timestamp/threadid                                  //~@@@1I~
//     exception dump                                              //~@@@1I~
//     optional dump by ajago menu option                          //~@@@1I~
//***********************************************                  //~@@@1I~
package com.btmtest.utils;                                               //~1Ad8I~

import java.io.*;
import java.util.*;

import com.btmtest.R;//~v@@@R~

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~1Ad8I~

import android.content.Context;
import android.os.Build;

public class Dump
{                                                                  //~1Ad8R~
	public static  boolean Y;   //test before call Dump to avoid memory for parameter String//~1506I~//~1Ad8I~
	public static  boolean C;   //split Canvas drawing              //~1A6AI~//~1Ad8I~
	public static  boolean T;   //split UiTHread trace              //~1A6AI~//~1Ad8I~
                                                                   //~1Ad8I~
	private static PrintWriter Out=null;                           //~1Ad8R~
    private static boolean Terminal=false;                         //~1Ad8R~
	private static boolean exceptionOnly=false;                     //~1507R~//~1Ad8R~
	private static boolean swSD;	//write to sdcard              //~1Ad8I~
	private static boolean swNoMsg;	//no Toast                     //~1Ad8I~
    //**************************************************************//~1Ad8I~
	public static void openEx (String file,boolean PswSD)          //~1Ad8I~
	{                                                              //~1Ad8I~
    	swSD=PswSD;                                                //~1Ad8I~
		openEx(file);                                              //~1Ad8I~
    }                                                              //~1Ad8I~
    //**************************************************************//~1Ad8I~
	public static void openEx (String file)                        //~1504I~
	{                                                              //~1504I~
		open(file);                                                //~1504I~
      	if (AG.isDebuggable                                          //~v107R~//~1Ad8R~
      	&& (Out!=null || Terminal)                                                //~v107R~//~1Ad8R~
      	)                                                            //~v@@@I~//~1Ad8R~
      	{                                                            //~v107R~//~1Ad8R~
        	Y=true;                                                    //~v107R~//~1Ad8R~
      	}                                                            //~v107R~//~1Ad8R~
      	else                                                         //~v107R~//~1Ad8R~
      	{                                                            //~v107R~//~1Ad8R~
    		exceptionOnly=true;                                        //~1506M~//~1Ad8R~
        	Y=false;//dont call Dump except case of Exceoption         //~1506I~//~1Ad8R~
      	}                                                            //~v107R~//~1Ad8R~
    }                                                              //~1504I~
    //**************************************************************//~1Ad8I~
	public static void openExOnlyTerminal()                        //~1Ad8I~
	{                                                              //~1Ad8I~
      	Terminal=true;                                             //~1Ad8I~
    	exceptionOnly=true;                                        //~1Ad8I~
        Y=false;//dont call Dump except case of Exceoption         //~1Ad8I~
    }                                                              //~1Ad8I~
    //**************************************************************//~1Ad8I~
	public static void open(String Pfile,boolean PswSD)            //~1Ad8I~
	{                                                              //~1Ad8I~
    	swSD=PswSD;                                                //~1Ad8I~
		open(Pfile);                                               //~1Ad8I~
    }                                                              //~1Ad8I~
    //**************************************************************//~va40I~
    //*for UnitTest                                                //~va40I~
    //**************************************************************//~va40I~
	public static void open(PrintWriter Ppw)                       //~va40I~
	{                                                              //~va40I~
		Out=Ppw;                                                   //~va40I~
        Terminal=false;                                            //~va40I~
		Y = true; //call Dump                                      //~va40I~
    }                                                              //~va40I~
    //**************************************************************//~1Ad8I~
	public static void open(String file)                           //~1Ad8R~
	{                                                              //~1329R~
    	exceptionOnly=false;//not exception only                   //~1506I~
        if (!file.equals(""))                                      //~1Ad8I~
        {                                                          //~1Ad8I~
        	Terminal=false;                                        //~1Ad8I~
        }                                                          //~1Ad8I~
        if (Terminal)                                              //~1Ad8I~
        {                                                          //~1Ad8I~
			Y = true; //call Dump                                  //~1Ad8I~
        	return;                                                //~1Ad8I~
        }                                                          //~1Ad8I~
//  	if (Out!=null)                                             //~1329I~//+vaz2R~
//      	return;                                                //~1329I~//+vaz2R~
        if (file.equals(""))                                       //~1Ad8I~
        {                                                          //~1Ad8I~
            Terminal=true;                                         //~1Ad8I~
			Y = true; //call Dump                                  //~1Ad8I~
        }   		                                               //~1Ad8I~
        else                                                       //~1Ad8I~
        {                                                          //~1Ad8I~
    		if (Out!=null)                                         //+vaz2I~
        		return;                                            //+vaz2I~
			try                                                        //~1329I~//~1Ad8I~
			{                                                          //~1227R~//~1Ad8I~
                FileOutputStream out;
                if (file.startsWith("/sdcard"))                    //~va40I~
    				out = openOutputData(file);                    //~va40I~
                else                                               //~va40I~
			    if (swSD)                                          //~1Ad8I~
					out = UFile.openOutputSD("",file); // /sdcard//~1Ad8I~
                else                                               //~1Ad8I~
//  				out = UFile.openOutputData(file, Context.MODE_WORLD_READABLE); // ../files//~1Ad8R~//~va40R~
//  				out = openOutputData(file);                    //~va40I~//~vad5R~
                    out=UFile.openOutputDataCacheDir(file);  // ../cache//~1Ak1I~//~vad5I~//~vaebR~
//  				out = openOutputData(file);                    //~vaebR~
				if (out != null)                                     //~1Ad8R~
				{//~1313R~                                         //~1Ad8R~
					Out = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), true/*autoFlash*/);//~1227I~//~1309R~//~1Ad8R~
					Out.println("Locale: " + Locale.getDefault() + "\n");//~1Ad8R~
					Y = true; //call Dump                                //~1506I~//~1Ad8R~
				}
			}   //~1Ad8R~
			catch (IOException e)                                  //~1Ad8I~
			{                                                      //~1Ad8I~
				Out=null;                                          //~1Ad8I~
            	System.out.println("Dump open failed");                //~1329I~//~1Ad8I~
                Terminal=true;                                     //~1Ad8I~
			}                                                      //~1Ad8I~
		}
	}
    //**************************************************************//~va40I~
    private static FileOutputStream openOutputData(String Pfnm)           //~va40I~
    {                                                              //~va40I~
    	FileOutputStream os;                                       //~va40I~
		if (Build.VERSION.SDK_INT>=17) //api17 android4.2:JellyBean MR1//~va40I~
			os=UFile.openOutputData(Pfnm); // MODE_PRIVATE         //~va40I~
        else                                                       //~va40I~
		    os=openOutputData_Bellow17(Pfnm);                //~va40I~
        return os;                                                 //~va40I~
    }                                                              //~va40I~
	@SuppressWarnings("deprecation")                               //~va40I~
    //**************************************************************//~va40I~
    private static FileOutputStream openOutputData_Bellow17(String Pfnm)         //~va40I~
    {                                                              //~va40I~
		return  UFile.openOutputData(Pfnm, Context.MODE_WORLD_READABLE); // ../files//~va40I~
    }                                                              //~va40I~
    //**************************************************************//~1Ad8I~
	public synchronized static void println (String s)             //~1305R~
	{                                                              //~1228R~
    	if (exceptionOnly)                                         //~1504I~
        	return;                                                //~1504I~
                                                                   //~1504I~
	    String tidts=null,tid;                                     //~v@@@I~
  		if (Out!=null)                                             //~1425R~
        {                                                          //~1425I~
//    try           //TODO test                                    //~vai3R~
//    {                                                            //~vai3R~
	    	tidts=Utils.getThreadTimeStamp();          //~1425I~   //~v@@@R~
			Out.println(tidts+":"+s);                              //~1425I~
//    }                                                            //~vai3R~
//    catch(Exception e)                                           //~vai3R~
//    {                                                            //~vai3R~
//        printlnLog("Dump.println e="+e);                         //~vai3R~
//    }                                                            //~vai3R~
        }                                                          //~1425I~
  		if (Terminal)                                              //~1511R~
        {                                                          //~1425I~
	    	tidts=Utils.getThreadTimeStamp();                      //~1Ad8I~
            System.out.println(tidts+":"+s);                         //~1425I~//~v@@@R~//~1Ad8R~
        }                                                          //~1425I~
	}
    //**************************************************************//~vaf3I~
    //*uncoditionally write log                                    //~vaf3I~
    //**************************************************************//~vaf3I~
	public static void printlnLog(String s)                        //~vaf3I~
	{                                                              //~vaf3I~
	    String tidts=Utils.getThreadTimeStamp();                   //~vaf3I~
        System.out.println(tidts+":"+s);                           //~vaf3I~
	}                                                              //~vaf3I~
    //**************************************************************//~1Ad8I~
    private static void byte2string(StringBuffer Psb,int Poutoffs,byte[] Pbytes,int Pinpoffs,int Plen)//~1Ab9I~
    {                                                              //~1Ab9I~
    	String s;                                                  //~1Ab9I~
		try {                                                      //~1Ab9I~
			s = new String(Pbytes,Pinpoffs,Plen,"US-ASCII");       //~1Ab9I~
		} catch (UnsupportedEncodingException e) {                 //~1Ab9I~
			e.printStackTrace();                                   //~1Ab9I~
            return;                                                //~1Ab9I~
		}                                                          //~1Ab9I~
        for (int ii=0;ii<16;ii++)                                  //~1Ab9I~
        {                                                          //~1Ab9I~
            if (ii<Plen)                                           //~1Ab9I~
            {                                                      //~1Ab9I~
        		char ch=s.charAt(ii);                              //~1Ab9I~
//              System.out.println("ch="+ch);                      //~1Ab9I~
            	if (ch<' '|| ch>=0x7f)                             //~1Ab9I~
	            	Psb.setCharAt(ii+Poutoffs,'.');                //~1Ab9I~
            	else                                               //~1Ab9I~
            		Psb.setCharAt(ii+Poutoffs,ch);                 //~1Ab9I~
            }                                                      //~1Ab9I~
            else                                                   //~1Ab9I~
	            Psb.setCharAt(ii+Poutoffs,' ');                    //~1Ab9I~
        }                                                          //~1Ab9I~
    }                                                              //~1Ab9I~
//                                       00 00 00 00 - 00 00 00 00 - 00 00 00 00 - 00 00 00 00 - *0123456789abcdef*//~1Ab9I~
    //**************************************************************//~1Ad8I~
    private static final String dumpfmt="            -             -             -               *                *";//~1Ab9I~
	public synchronized static void println (String Ptitle,byte[] Pbytes)//~1Ab9I~
    {                                                              //~1Ab9I~
    	println(Ptitle,Pbytes,0,Pbytes.length);                    //~1Ab9I~
    }                                                              //~1Ab9I~
    //**************************************************************//~1Ad8I~
	public synchronized static void println(String Ptitle,byte[] Pbytes,int Poffs,int Plen)//~1Ab9I~//~1Ad8R~
	{                                                              //~1Ab9I~
    	if (exceptionOnly)                                         //~1Ab9I~
        	return;                                                //~1Ab9I~
	    println(Ptitle+" : size="+Pbytes.length+"=0x"+Integer.toHexString(Pbytes.length)+",offs=0x"+Integer.toHexString(Poffs)+",len=0x"+Integer.toHexString(Plen));//~1Ab9I~
        StringBuffer sb=new StringBuffer(dumpfmt);                 //~1Ab9I~
        int intch,intch2;                                          //~1Ab9I~
        int fillsz=((Plen+15)/16)*16;                              //~1Ab9I~
        int lastinpoffs=0;                                         //~1Ab9I~
        for (int ii=0,offs=0;ii<fillsz;ii++,offs+=3)               //~1Ab9I~
        {                                                          //~1Ab9I~
        	if (ii!=0)                                             //~1Ab9I~
            {                                                      //~1Ab9I~
                if (ii%16==0)                                      //~1Ab9I~
                {                                                  //~1Ab9I~
                    byte2string(sb,(3*4+2)*4+1,Pbytes,Poffs+ii-16,16);//~1Ab9I~
                    String s=sb.toString();                        //~1Ab9I~
                    if (Out!=null)                                 //~1Ab9I~
                        Out.println(s);                            //~1Ab9I~
                    if (Terminal)                                  //~1Ab9I~
                        System.out.println(s);                     //~1Ab9I~
	                offs=0;                                        //~1Ab9I~
                    lastinpoffs=ii;                                //~1Ab9I~
                }                                                  //~1Ab9I~
                else                                               //~1Ab9I~
                if (ii%4==0)                                       //~1Ab9I~
                    offs+=2;                                       //~1Ab9I~
            }                                                      //~1Ab9I~
            if (ii<Plen)                                           //~1Ab9I~
            {                                                      //~1Ab9I~
            	intch=(Pbytes[Poffs+ii] & 0xff);                   //~1Ab9I~
            	intch2=intch/16;                                   //~1Ab9I~
                if (intch2<10)                                     //~1Ab9I~
            		sb.setCharAt(offs,(char) ('0'+intch2));        //~1Ab9I~
                else                                               //~1Ab9I~
            		sb.setCharAt(offs,(char) ('a'+intch2-10));     //~1Ab9I~
            	intch2=intch%16;                                   //~1Ab9I~
                if (intch2<10)                                     //~1Ab9I~
            		sb.setCharAt(offs+1,(char) ('0'+intch2));      //~1Ab9I~
                else                                               //~1Ab9I~
            		sb.setCharAt(offs+1,(char) ('a'+intch2-10));   //~1Ab9I~
            }                                                      //~1Ab9I~
            else                                                   //~1Ab9I~
            {                                                      //~1Ab9I~
            	sb.setCharAt(offs,' ');                            //~1Ab9I~
            	sb.setCharAt(offs+1,' ');                          //~1Ab9I~
            }                                                      //~1Ab9I~
        }                                                          //~1Ab9I~
        if (lastinpoffs<Plen)	//remaining char dump              //~1Ab9I~
        {                                                          //~1Ab9I~
	    	byte2string(sb,(3*4+2)*4+1,Pbytes,lastinpoffs,Plen-lastinpoffs);//~1Ab9I~
            String s=sb.toString();                                //~1Ab9I~
            if (Out!=null)                                         //~1Ab9I~
            	Out.println(s);                                    //~1Ab9I~
            if (Terminal)                                          //~1Ab9I~
            	System.out.println(s);                             //~1Ab9I~
        }                                                          //~1Ab9I~
	}                                                              //~1Ab9I~
    //**************************************************************//~1Ad8I~
	//** Exception Dump                                                //~1309I~//~1Ad8I~
    //**************************************************************//~1Ad8I~
	public synchronized static void println(Exception e,String s)  //~1309I~
	{                                                              //~1309I~
		println(false,e,s);                        //~1Ad8I~
	}                                                              //~1309I~
    //**************************************************************//~1Ad8I~
	//** Exception Dump                                            //~1Ad8I~
    //**************************************************************//~1Ad8I~
	public synchronized static void printlnNoMsg(Exception e,String s)//~1Ad8I~
	{                                                              //~1Ad8I~
    	swNoMsg=true;                                              //~1Ad8I~
		println(false,e,s);                                        //~1Ad8I~
    	swNoMsg=false;                                             //~1Ad8I~
	}                                                              //~1Ad8I~
    //**************************************************************//~1Ad8I~
	//** Exception Dump                                            //~1Ad8R~
	//** is swmsg=true:show message dialog, false:show toast       //~1Ad8I~
    //**************************************************************//~1Ad8I~
	public synchronized static void println(Boolean Pswmsgdialog,Exception e,String s)//~1Ad8I~
	{                                                              //~1Ad8I~
	    String tidts=Utils.getThreadTimeStamp();                   //~1Ad8I~
        StringWriter sw=new StringWriter();                        //~1Ad8I~
        PrintWriter pw= new PrintWriter(sw);                       //~1Ad8I~
        e.printStackTrace(pw);                                     //~1Ad8I~
		String sst=sw.toString();                                 //~1Ad8I~
        pw.close();                                                //~1Ad8I~
//      if (Terminal)                                              //~1Ad8I~//~vai3R~
        if (Terminal || !AG.isDebuggable)                          //~vai3I~
        {                                                          //~1Ad8I~
			System.out.println(tidts+"Dump.Exception:"+s+"\n"+sst);//~1Ad8R~
        }                                                          //~1Ad8I~
        else                                                       //~1Ad8I~
  		if (Out!=null)                                             //~1Ad8I~
        {                                                          //~1Ad8I~
			Out.println(tidts+"Dump.Exception:"+s+"\n"+sst);       //~1Ad8R~
			Out.flush();                                           //~1Ad8I~
        }                                                          //~1Ad8I~
        if (Pswmsgdialog)                                          //~1Ad8I~
	        Alert.showMessage("Dump.Exception:"+s,e.toString());   //~1Ad8R~
        else                                                       //~1Ad8I~
        if (!swNoMsg)                                              //~1Ad8I~
        	UView.showToastLong(R.string.ErrExceptionDetected,":"+s+":"+e.toString());//~1Ad8R~
	}                                                              //~1Ad8I~
    //**************************************************************//~1B0gI~//~1Ad8I~
	public synchronized static void println(OutOfMemoryError e,String s)//~1B0gI~//~1Ad8I~
	{                                                              //~1B0gI~//~1Ad8I~
	    String tidts=Utils.getThreadTimeStamp();              //~1B0gI~//~1Ad8I~
//      if (Terminal)                                              //~1Ad8I~//~vai3R~
        if (Terminal || !AG.isDebuggable)                          //~vai3I~
        {                                                          //~1Ad8I~
            StringWriter sw=new StringWriter();                    //~1Ad8I~
            PrintWriter pw= new PrintWriter(sw);                   //~1Ad8I~
            e.printStackTrace(pw);                                 //~1Ad8I~
			System.out.println(tidts+"Dump.Exception:"+s+"\n"+sw.toString());//~1Ad8R~
			pw.close();                                            //~1Ad8I~
        }                                                          //~1Ad8I~
        else                                                       //~1Ad8I~
  		if (Out!=null)                                             //~1B0gI~//~1Ad8I~
        {                                                          //~1B0gI~//~1Ad8I~
            StringWriter sw=new StringWriter();                    //~1B0gI~//~1Ad8I~
            PrintWriter pw= new PrintWriter(sw);                   //~1B0gI~//~1Ad8I~
            e.printStackTrace(pw);                                 //~1B0gI~//~1Ad8I~
			Out.println(tidts+"Dump.Exception:"+s+"\n"+sw.toString());//~1Ad8R~
			Out.flush();                                           //~1B0gI~//~1Ad8I~
			pw.close();                                            //~1B0gI~//~1Ad8I~
        }                                                          //~1B0gI~//~1Ad8I~
	}                                                              //~1B0gI~//~1Ad8I~
    //**************************************************************//~1Ad8I~//~1ak2I~
	public synchronized static void println(NoClassDefFoundError e,String s)//~1Ak2I~//~1ak2I~
	{                                                              //~1Ak2I~//~1ak2I~
	    String tidts=Utils.getThreadTimeStamp();                   //~1Ak2I~//~1ak2I~
//      if (Terminal)                                              //~1Ak2I~//~1ak2I~//~vai3R~
        if (Terminal || !AG.isDebuggable)                          //~vai3I~
        {                                                          //~1Ak2I~//~1ak2I~
            StringWriter sw=new StringWriter();                    //~1Ak2I~//~1ak2I~
            PrintWriter pw= new PrintWriter(sw);                   //~1Ak2I~//~1ak2I~
            e.printStackTrace(pw);                                 //~1Ak2I~//~1ak2I~
			System.out.println(tidts+"Dump.Exception:"+s+"\n"+sw.toString());//~1Ak2I~//~1ak2I~
			pw.close();                                            //~1Ak2I~//~1ak2I~
        }                                                          //~1Ak2I~//~1ak2I~
        else                                                       //~1Ak2I~//~1ak2I~
  		if (Out!=null)                                             //~1Ak2I~//~1ak2I~
        {                                                          //~1Ak2I~//~1ak2I~
            StringWriter sw=new StringWriter();                    //~1Ak2I~//~1ak2I~
            PrintWriter pw= new PrintWriter(sw);                   //~1Ak2I~//~1ak2I~
            e.printStackTrace(pw);                                 //~1Ak2I~//~1ak2I~
			Out.println(tidts+"Dump.Exception:"+s+"\n"+sw.toString());//~1Ak2I~//~1ak2I~
			Out.flush();                                           //~1Ak2I~//~1ak2I~
			pw.close();                                            //~1Ak2I~//~1ak2I~
        }                                                          //~1Ak2I~//~1ak2I~
	}                                                              //~1Ak2I~//~1ak2I~
    //**************************************************************//~1Ad8I~
	/** dump a string without linefeed */
	public static void print (String s)
	{                                                              //~1504R~
    	if (exceptionOnly)                                         //~1504I~
        	return;                                                //~1504I~
		if (Out!=null) Out.print(s);                               //~1504I~
		if (Terminal) System.out.print(s);
	}
    //**************************************************************//~1Ad8I~
	/** close the dump file */
	public static void close ()
	{                                                              //~1503R~
		if (Out!=null)                                             //~1503I~
        {                                                          //~1503I~
        	println("Dump closed");                               //~1503I~
			Out.close();                                           //~1503I~
        }                                                          //~1503I~
    	Out=null;                                                  //~1425I~
	}
    //**************************************************************//~1Ad8I~
    public static void setOption(boolean Pflag)                    //~1507I~//~v@@@R~
    {                                                              //~1507I~//~v@@@R~
        println("DumpOption Changed to "+Pflag);                    //~1507I~//~v@@@R~//~vai3R~
        Y=Pflag;    //debug dump                                   //~1507I~//~v@@@R~
        exceptionOnly=!Pflag;                                      //~1507I~//~v@@@R~
    }                                                              //~1507I~//~v@@@R~
    //**************************************************************//~1Ad8I~
    public static void println(String Pcmt,int[][] Pintss)         //~1Ad8I~
    {                                                              //~1Ad8I~
    	for (int ii=0;ii<Pintss.length;ii++)                       //~1Ad8I~
	        println(Pcmt+" ii="+ii+"="+Arrays.toString(Pintss[ii]));//~1Ad8R~
    }                                                              //~1Ad8I~
}
