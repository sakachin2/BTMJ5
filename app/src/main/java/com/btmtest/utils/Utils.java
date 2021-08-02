//*CID://+vaafR~: update#= 309;                                    //~vaafR~
//**********************************************************************//~1107I~
//2021/07/01 vaaf Button BG color by span string because setbackgroundColor is expands button to its boundary.//~vaafI~
//2021/06/16 vaa0 support <img> in htmlText                        //~vaa0I~
//2021/04/06 va7b (Bug)HistryData setScore exception (out of bound)//~va7bI~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//@@01 20181105 for BTMJ3                                            //~@@01I~
//**********************************************************************//~1107I~//~v106M~
package com.btmtest.utils;                                               //~1Ad8I~//~1Ac0I~//~v@@@R~

                                                                   //~v@@@I~
import java.net.Inet6Address;
import java.net.InetAddress;                                       //~v106R~
import java.net.NetworkInterface;                                  //~v106R~
import java.net.Socket;
import java.util.Arrays;
import java.util.Enumeration;                                      //~v106I~
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.content.Context;                                    //~v107R~
import android.content.Intent;
import android.content.pm.ApplicationInfo;                         //~v107R~
import android.content.pm.PackageManager;                          //~v107R~
import android.content.pm.PackageManager.NameNotFoundException;    //~v107R~
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.graphics.Color;                                     //~v@@@I~
//import android.app.DialogFragment;                               //~va40R~
import android.os.Build;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;                      //~va40I~
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.widget.Button;
import android.widget.TextView;

import static androidx.core.text.HtmlCompat.*;
import static com.btmtest.StaticVars.AG;
import static com.btmtest.AG.*;//~v@21I~//~@@01I~

//**********************************************************************//~1107I~
public class Utils                                            //~1309R~//~@@@@R~
{                                                                  //~0914I~
    public static final String	IPA_NA="N/A";                      //~1A05I~
	private static final String sharedPreferenceName=AG.appName+"-PrivatePreference";//~@@@@I~//~v@@@I~//~@@01R~
//**********************************                               //~@@@@I~
//*from Alert,replyed Yes                                          //~@@@@I~
//**********************************                               //~@@@@I~
	public static void stopFinish()		//from Alert by Stop:Yes   //~@@@2I~
    {                                                              //~@@@2I~
    	if (Dump.Y) Dump.println("Utils stopFinish");         //~@@@2I~//~v@@@R~
       	try                                                        //~@@@2I~//~v@@@R~
        {                                                          //~@@@2I~
        	AG.aMainActivity.destroyClose();                               //~@@@2R~
        }                                                          //~@@@2I~
        catch (Exception e)                                               //~@@@2I~
        {                                                          //~@@@2I~
        	Dump.println(e,"stopFinish");                          //~@@@2I~
            finish();                                              //~@@@2I~
        }                                                          //~@@@2I~
    }                                                              //~@@@2I~
//**********************************                               //~1211I~
	public static void exit(int Pexitcode)	//from Mainframe:doclose()                         //~1309I~//~@@@2R~
    {                                                              //~1309I~
    	if (Dump.Y) Dump.println("AjagoUtils exit()");//~1309I~//~1506R~//~@@@2R~
		finish();                                                  //~1309R~
    }                                                              //~1309I~
//*******************************************************          //~@@@@R~
//*from onDestroy,kill process                                                  //~@@@@I~//~@@@2R~
//*******************************************************          //~@@@@I~
	public static void exit(int Pexitcode,boolean Pkill)           //~1329I~
    {                                                              //~1329I~
    	if (Dump.Y) Dump.println("AjagoUtils kill exit() code="+Pexitcode+",kill="+Pkill);//~1506R~//~@@@2R~
        if (Pkill)                                                 //~1329I~
        {                                                          //~1503I~
    		if (Dump.Y) Dump.println("AjagoUtils kill exit() killProcess");//~@@@2I~
            Dump.close();                                          //~1503I~//~@@@2M~
            android.os.Process.killProcess(android.os.Process.myPid());                  //~@@@@I~//~@@@2R~
        }                                                          //~1503I~
    }                                                              //~1329I~
//**********************************                               //~@@@2I~
//*Activity:finish()                                               //~@@@2I~
//**********************************                               //~@@@2I~
    public static void finish()                                    //~@@01I~
    {                                                              //~@@01I~
    	if (Dump.Y) Dump.println("Utils finish osverasion="+AG.osVersion);//~@@01I~
		if (AG.osVersion>=LOLLIPOP)                             //~@@01I~
	        finishAndRemoveTask();                                 //~@@01I~
        else                                                       //~@@01I~
        {                                                          //~@@01I~
    		if (Dump.Y) Dump.println("Utils.finish issue Activity.finish()");//~@@01I~
        	AG.aMainActivity.finish();	//schedule onDestroy       //~@@01I~
        }                                                          //~@@01I~
    }                                                              //~@@01I~
//**********************************                               //~@@01I~
	@TargetApi(LOLLIPOP)                                       //~1A4sI~//~@@01I~
    public static void finishAndRemoveTask()                                    //~@@@@I~//~@@01R~
    {                                                              //~@@@@I~
    	if (Dump.Y) Dump.println("Utils finish api>=21(Lollipop:5.0)");             //~@@@@I~//~@@@2R~//~@@01R~
        AG.aMainActivity.finishAndRemoveTask();	//schedule onDestroy//~@@01I~
    }                                                              //~@@@@I~
//**********************************                               //~@@@@I~
	public static void sleep(long Pmilisec)                        //~1503I~
    {                                                              //~1503I~
    	if (Dump.Y) Dump.println("Utils.sleep milisec="+Pmilisec); //~@@01I~
        try                                                        //~1503I~
        {                                                          //~1503I~
        	Thread.sleep(Pmilisec);//wait subtread termination  1.2sec//~1503I~
        }                                                          //~1503I~
        catch(InterruptedException e)                              //~1503I~
		{                                                          //~1503I~
        	Dump.println(e,"sleep interrupted Exception");         //~1503I~
		}                                                          //~1503I~
    }                                                              //~1503I~
//**********************************                               //~1412I~
//*elapsed time calc                                               //~1412I~
//**********************************                               //~1412I~
	public static final int TSID_TITLE_TOUCH=0;                   //~1412I~
//  private static final int TSID_MAX        =1;                   //~1412I~//~@@01R~
//  private static long[] Stimestamp=new long[TSID_MAX];                                 //~1412I~//~@@01R~
	public static long setTimeStamp(int Pid)                       //~1412I~
    {                                                              //~1412I~
        if (Pid>=AG.TSID_MAX)                                         //~1412I~
            return 0;                                              //~1412I~
        long t=System.currentTimeMillis();                         //~1412I~
        AG.Stimestamp[Pid]=t;                                         //~1412I~//~@@01R~
    	if (Dump.Y) Dump.println("AjagoUtils setTimeStamp id="+Pid+",ts="+Long.toHexString(t));//~1506R~
        return t;                                                  //~1412I~
    }                                                              //~1412I~
	public static int getElapsedTimeMillis(int Pid)                //~1412I~
    {                                                              //~1412I~
        if (Pid>=AG.TSID_MAX)                                         //~1412I~
            return 0;                                              //~1412I~
        if (AG.Stimestamp[Pid]==0)                                    //~1413I~//~@@01R~
            return 0;                                              //~1413I~
        long t=System.currentTimeMillis();                         //~1412I~
    	if (Dump.Y) Dump.println("AjagoUtils getElapsed now id="+Pid+",ts="+Long.toHexString(t));//~1506R~
        int  elapsed=(int)(t-AG.Stimestamp[Pid]);                     //~1412I~//~@@01R~
    	if (Dump.Y) Dump.println("AjagoUtils getElapsetTimeMillis id="+Pid+",ts="+Integer.toHexString(elapsed));//~1506R~
        AG.Stimestamp[Pid]=0;                                         //~1413I~//~@@01R~
        return elapsed;                                            //~1412I~
    }                                                              //~1412I~
//**********************************                               //~1425I~
//*edit date/time                                                  //~1425I~
//**********************************                               //~1425I~
	public static final int TS_DATE_TIME=1;                        //~1425I~
	public static final int TS_MILI_TIME=2;                        //~1425I~
	public static final int TS_DATE_TIME2=3;                       //~@@01I~
	private static final SimpleDateFormat fmtdt=new SimpleDateFormat("yyyyMMdd-HHmmss");//~1425I~
	private static final SimpleDateFormat fmtdt2=new SimpleDateFormat("yyyyMMdd:HHmmss");//~@@01R~
	private static final SimpleDateFormat fmtms=new SimpleDateFormat("HHmmss.SSS");//~1425I~
	public static String getTimeStamp(int Popt,Date Pdate)                    //~1425I~//~v@@@R~
    {                                                              //~1425I~
        SimpleDateFormat f;                                        //~1425I~
    //**********************:                                      //~1425I~
    	switch(Popt)                                               //~1425I~
        {                                                          //~1425I~
        case TS_DATE_TIME:                                         //~1425I~
        	f=fmtdt;                                               //~1425I~
            break;                                                 //~1425I~
        case TS_DATE_TIME2:                                        //~@@01I~
        	f=fmtdt2;                                              //~@@01I~
            break;                                                 //~@@01I~
        case TS_MILI_TIME:                                         //~1425I~
        	f=fmtms;                                               //~1425I~
            break;                                                 //~1425I~
        default:                                                   //~1425I~
        	return null;                                           //~1425I~
        }                                                          //~1425I~
        String s=f.format(Pdate);                                  //~@@01I~
//      if (Dump.Y) Dump.println("Utils.getTimeStamp opt="+Popt+",rc="+s);//~@@01R~
        return s;                                                  //~@@01I~
    }                                                              //~1425I~
	public static String getTimeStamp(int Popt)                    //~v@@@I~
    {                                                              //~v@@@I~
        return getTimeStamp(Popt,new Date());                      //~v@@@I~
    }                                                              //~v@@@I~
	public static String getTimeStamp(int Popt,long Ptime)         //~v@@@I~
    {                                                              //~v@@@I~
        return getTimeStamp(Popt,new Date(Ptime));             //~v@@@I~
    }                                                              //~v@@@I~
	public static String getTimeStamp(String Pfmt,long Ptime)      //~v@@@I~
    {                                                              //~v@@@I~
		SimpleDateFormat f=new SimpleDateFormat(Pfmt);             //~v@@@I~
        return f.format(new Date(Ptime));                          //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~1425I~
//* Digit Thread ID                                                //~1425I~
//**********************************                               //~1425I~
	public static String getThreadId()                             //~1425I~
    {                                                              //~1425I~
    //**********************:                                      //~1425I~
    	int tid=(int)Thread.currentThread().getId();               //~1425I~
        if (tid<10)                                                //~1425I~
        	return "0"+tid;                                        //~1425I~
        return Integer.toString(tid);                              //~1425I~
    }                                                              //~1425I~
//**********************************                               //~1425I~
	public static String getThreadTimeStamp()                      //~1425I~
    {                                                              //~1425I~
    //**********************:                                      //~1425I~
    	String tidts=getThreadId()+":"+getTimeStamp(TS_MILI_TIME);  //~1425I~
        return tidts;                                              //~1425I~
    }                                                              //~1425I~
//**********************************                               //~v@@@I~
	public static boolean isAlive(Thread Pthread)                  //~v@@@I~
    {                                                              //~v@@@I~
    	boolean rc=Pthread!=null && Pthread.isAlive();                 //~v@@@I~//~@@01R~
    	if (Dump.Y) Dump.println("Utils.isAlive rc="+rc);          //~@@01I~
    	return rc;                                                 //~@@01I~
    }                                                              //~v@@@I~
//***************************************************************************//~1A8bI~
//  private static int SswDirect;                                  //~1A8bI~//~@@01R~
    private static final int MAC_LOCAL_ADDRESS=0x02;	//if off global address//~1A8bI~
//***********                                                      //~1A8bI~
    public static String getIPAddressDirect()                      //~1A8bI~
    {                                                              //~1A8bI~
//  	SswDirect=1; //local only                                  //~1A8bI~//~@@01R~
//  	String ipa=getIPAddress(false);                            //~1A8bI~//~@@01R~
//  	SswDirect=0;                                               //~1A8bI~//~@@01R~
    	String ipa=getIPAddress(false,1);                          //~@@01I~
        return ipa;                                                //~1A8bI~
    }                                                              //~1A8bI~
//***********                                                      //~1A8bI~
    public static String getIPAddressAll()                         //~1A8bI~
    {                                                              //~1A8bI~
//  	SswDirect=2; //both global and local                       //~1A8bI~//~@@01R~
//  	String ipa=getIPAddress(false);                            //~1A8bI~//~@@01R~
//  	SswDirect=0;                                               //~1A8bI~//~@@01R~
    	String ipa=getIPAddress(false,2);                          //~@@01I~
        return ipa;                                                //~1A8bI~
    }                                                              //~1A8bI~
//**********************************                               //~@@01I~
    public static String getIPAddress(boolean Pipv6)               //~@@01I~
    {                                                              //~@@01I~
    	return getIPAddress(Pipv6,0);                              //~@@01I~
    }                                                              //~@@01I~
//**********************************                               //~v106I~
    public static String getIPAddress(boolean Pipv6,int PswDirect)                          //~v106I~//~v101R~//~@@01R~
    {                                                              //~v106I~
//  	String ipa="N/A";                                          //~v106R~//~1A05R~
    	String ipa=IPA_NA;                                         //~1A05I~
    	String ipv6="";                                           //~v106I~
        int ctr=0;                                                 //~1A67I~
    //**********************:                                      //~v106I~
        try                                                        //~v106I~
        {                                                          //~v106I~
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();//~v106I~
            while(interfaces.hasMoreElements())                    //~v106I~
            {                                                      //~v106I~
                NetworkInterface network = interfaces.nextElement();//~v106I~
    		    if (Dump.Y) Dump.println("getIPAddress mac="+getMacString(network.getHardwareAddress()));//~1A67R~
    		    byte[] bmac=network.getHardwareAddress();          //~1A8bI~
                if (bmac!=null)                                    //~1A8bI~
                {                                                  //~1A8bI~
                    if ((bmac[0] & MAC_LOCAL_ADDRESS)!=0)          //~1A8bI~
                    {                                              //~1A8bI~
//                      if (SswDirect==0) //global only            //~1A8bI~//~@@01R~
                        if (PswDirect==0) //global only            //~@@01I~
                            continue;                              //~1A8bI~
                    }                                              //~1A8bI~
                    else    //global                               //~1A8bI~
                    {                                              //~1A8bI~
//                      if (SswDirect==1)   //local only           //~1A8bI~//~@@01R~
                        if (PswDirect==1)   //local only           //~@@01I~
                            continue;                              //~1A8bI~
                    }                                              //~1A8bI~
                }                                                  //~1A8bI~
    		    if (Dump.Y) Dump.println("isPont2point="+network.isPointToPoint()+",isUp="+network.isUp());//~1A05I~
    		    if (Dump.Y) Dump.println("name="+network.getName()+",displayName="+network.getDisplayName());//~1A05R~
    		    if (Dump.Y) Dump.println("toString="+network.toString());//~1A05I~
                Enumeration<InetAddress> addresses = network.getInetAddresses();//~v106I~
                while(addresses.hasMoreElements())                 //~v106I~
                {                                                  //~v106I~
                    InetAddress na=addresses.nextElement();        //~v106R~
                    String ipa2=na.getHostAddress();               //~v106I~
                    if (na.isLoopbackAddress()                     //~v106R~
                    ||  na.isLinkLocalAddress()                    //~v106R~
//                  ||  na.isSiteLocalAddress()                    //~v106R~
                    )                                              //~v106I~
                    	continue;                                  //~v106I~
                    if (na instanceof Inet6Address)//ipv6          //~v106M~
                    {                                              //~v106I~
                    	ipv6=ipa2;                                 //~v106I~
                    	continue;                                  //~v106M~
                    }                                              //~v106I~
			        if (Dump.Y) Dump.println("getIPAddress:"+ipa2);//~v106R~
                  if (ctr++==0)                                    //~1A67I~
                    ipa=ipa2;                                      //~v106R~//~1A67R~
				  else                                             //~1A67I~
                    ipa+=";"+ipa2;                                 //~1A67R~
                    break;                                         //~v106R~
                }                                                  //~v106I~
            }                                                      //~v106I~
        }                                                          //~v106I~
        catch(Exception e)                                         //~v106I~
        {                                                          //~v106I~
        	Dump.println(e,"getIPAddress");                        //~v106I~
        }                                                          //~v106I~
//      if (!Pipv6)                                                //~v101I~//~1A8bR~
        if (!Pipv6 || ipv6.equals(""))                             //~1B10R~//~1A8bI~
            return ipa;                                             //~v101I~//~1A67R~
        return ipa+","+ipv6;                                       //~v106R~
    }                                                              //~v106I~
//**********************************                               //~1A86I~
    public static String getIPAddressFromMacAddr(String Pmacaddr)  //~1A86I~
    {                                                              //~1A86I~
    	String ipa=IPA_NA;                                         //~1A86I~
//  	String ipv6="";                                            //~1A86I~
        int ctr=0;                                                 //~1A86I~
    //**********************:                                      //~1A86I~
        try                                                        //~1A86I~
        {                                                          //~1A86I~
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();//~1A86I~
            while(interfaces.hasMoreElements())                    //~1A86I~
            {                                                      //~1A86I~
                NetworkInterface network = interfaces.nextElement();//~1A86I~
                String macaddr=getMacString(network.getHardwareAddress());//~1A86I~
                if (Dump.Y) Dump.println("getIPAddressFromMacaddr mac="+macaddr+",parm="+Pmacaddr);//~1A86I~
    		    if (Dump.Y) Dump.println("isPont2point="+network.isPointToPoint()+",isUp="+network.isUp());//~1A86I~
    		    if (Dump.Y) Dump.println("name="+network.getName()+",displayName="+network.getDisplayName());//~1A86I~
    		    if (Dump.Y) Dump.println("toString="+network.toString());//~1A86I~
                if (!macaddr.equals(Pmacaddr))                     //~1A86I~
                	continue;                                      //~1A86I~
                Enumeration<InetAddress> addresses = network.getInetAddresses();//~1A86I~
                while(addresses.hasMoreElements())                 //~1A86I~
                {                                                  //~1A86I~
                    InetAddress na=addresses.nextElement();        //~1A86I~
                    String ipa2=na.getHostAddress();               //~1A86I~
                    if (na.isLoopbackAddress()                     //~1A86I~
                    ||  na.isLinkLocalAddress()                    //~1A86I~
//                  ||  na.isSiteLocalAddress()                    //~1A86I~
                    )                                              //~1A86I~
                    	continue;                                  //~1A86I~
                    if (na instanceof Inet6Address)//ipv6          //~1A86I~
                    {                                              //~1A86I~
//                    	ipv6=ipa2;                                 //~1A86I~
                    	continue;                                  //~1A86I~
                    }                                              //~1A86I~
			        if (Dump.Y) Dump.println("getIPAddress:"+ipa2);//~1A86I~
                  if (ctr++==0)                                    //~1A86I~
                    ipa=ipa2;                                      //~1A86I~
				  else                                             //~1A86I~
                    ipa+=";"+ipa2;                                 //~1A86I~
                    break;                                         //~1A86I~
                }                                                  //~1A86I~
            }                                                      //~1A86I~
        }                                                          //~1A86I~
        catch(Exception e)                                         //~1A86I~
        {                                                          //~1A86I~
        	Dump.println(e,"getIPAddressFromMacAddr");             //~1A86I~
        }                                                          //~1A86I~
//      if (!Pipv6 || ipv6.equals(""))                             //~1A86I~
//          return ipa;                                            //~1A86I~
//      return ipa+","+ipv6;                                       //~1A86I~
        return ipa;                                                //~1A86I~
    }                                                              //~1A86I~
//***********************************************************************//~v107R~
    public static boolean isDebuggable(Context ctx)                //~v107R~
    {                                                              //~v107R~
        PackageManager manager = ctx.getPackageManager();          //~v107R~
        ApplicationInfo appInfo = null;                            //~v107R~
        try                                                        //~v107R~
        {                                                          //~v107R~
            appInfo = manager.getApplicationInfo(ctx.getPackageName(), 0);//~v107R~
        }                                                          //~v107R~
        catch (NameNotFoundException e)                            //~v107R~
        {                                                          //~v107R~
            return false;                                          //~v107R~
        }                                                          //~v107R~
        if ((appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) == ApplicationInfo.FLAG_DEBUGGABLE)//~v107R~
            return true;                                           //~v107R~
        return false;                                              //~v107R~
    }                                                              //~v107R~
//***********************************************************************//~1A67I~//~1A6aR~
    public static String getMacString(byte[] Pbytemacaddr)                //~1A67R~//~1A6aR~
    {                                                              //~1A67R~//~1A6aR~
        if (Pbytemacaddr==null)                                    //~1A6aR~
            return "";                                             //~1A6aR~
        StringBuilder sb=new StringBuilder("");                      //~1A67R~//~1A6aR~
        int sz=Pbytemacaddr.length;                                //~1A67R~//~1A6aR~
        for (int ii=0;ii<sz;ii++)                                  //~1A67R~//~1A6aR~
        {                                                          //~1A67R~//~1A6aR~
            sb.append(String.format("%s%02x",((ii==0) ? "" : ":"),Pbytemacaddr[ii]));//~1A67R~//~1A6aR~
        }                                                          //~1A67R~//~1A6aR~
        return new String(sb);                                     //~1A67R~//~1A6aR~
    }                                                              //~1A67R~//~1A6aR~
//***********************************************************************//~1A6aI~
	public static String getRemoteIPAddr(Socket Psocket,String Pnullopt)                       //~@@@2I~//~1A8bI~
    {                                                              //~@@@2I~//~1A8bI~
    	String ipa=null;                                           //~1A8bI~
        InetAddress ia=Psocket.getInetAddress();                   //~@@@2I~//~1A8bI~
        if (ia!=null)                                              //~@@@2M~//~1A8bI~
        {                                                          //~@@@2M~//~1A8bI~
	        ipa=ia.getHostAddress();              //~@@@2R~        //~1A8bI~
	        if (Dump.Y) Dump.println("Utils:getRemoteIPAddr="+ipa+",name="+ia.getHostName());//~@@@2I~//~1A8bI~//~@@01R~
        }                                                          //~@@@2M~//~1A8bI~
        if (ipa==null)                                             //~1A8bI~
        {                                                          //~1A8bI~
        	ipa=Pnullopt;                                          //~1A8bI~
        }                                                          //~1A8bI~
        return ipa;                                                //~1A8bI~
    }                                                              //~@@@2I~//~1A8bI~
//***********************************************************************//~1A8bI~
	public static String getLocalIPAddr(Socket Psocket,String Pnullopt)//~1A8bI~
    {                                                              //~1A8bI~
    	String ipa=null;                                           //~1A8bI~
        InetAddress ia=Psocket.getLocalAddress();                  //~1A8bI~
        if (ia!=null)                                              //~1A6sI~//~1A8bI~
        {                                                          //~1A6sI~//~1A8bI~
	        ipa=ia.getHostAddress();               //~1A6sI~       //~1A8bI~
	        if (Dump.Y) Dump.println("AjagoUtils:getLocalIPAddr="+ipa+",name="+ia.getHostName());//~1A8bI~
        }                                                          //~1A6sI~//~1A8bI~
        if (ipa==null)                                             //~1A8bI~
        {                                                          //~1A8bI~
        	ipa=Pnullopt;                                          //~1A8bI~
        }                                                          //~1A8bI~
        return ipa;                                                //~1A8bI~
    }                                                              //~1A8bI~
//***********************************************************      //~v1E7I~//~1Aa6I~
	public static void showWebSite(String Purl)                    //~v1E7I~//~1Aa6I~
	{                                                              //~v1E7I~//~1Aa6I~
		if (Dump.Y) Dump.println("Utils:showWebSite url="+Purl);   //~v1E7I~//~1Aa6I~
        Intent intent=new Intent(Intent.ACTION_VIEW);              //~v1E7I~//~1Aa6I~
        intent.setData(Uri.parse(Purl));                           //~v1E7I~//~1Aa6I~
        AG.aMainActivity.startActivity(intent);                         //~v1E7I~//~1Aa6I~
	}                                                              //~v1E7I~//~1Aa6I~
//***********************************************************************//~1Ac0I~
    public static ConnectivityManager getCM()                      //~1Ac0I~
    {                                                              //~1Ac0I~
        return (ConnectivityManager)AG.context.getSystemService(Context.CONNECTIVITY_SERVICE);//~1Ac0I~
    }                                                              //~1Ac0I~
//**********************                                           //~1127I~//~v@@@I~
    private static final double FACTOR = 0.7;                      //~v@@@I~
    public static int brighterColor(int Pcolor) {                                      //~1127I~//~v@@@I~
        int r = Color.red(Pcolor);                                          //~1127I~//~v@@@I~
        int g = Color.green(Pcolor);                                        //~1127I~//~v@@@I~
        int b = Color.blue(Pcolor);                                         //~1127I~//~v@@@I~
                                                                   //~1127I~//~v@@@I~
        int i = (int)(1.0/(1.0-FACTOR));                           //~1127I~//~v@@@I~
        if ( r == 0 && g == 0 && b == 0) {                         //~1127I~//~v@@@I~
           return Color.rgb(i, i, i);                              //~1127I~//~v@@@I~
        }                                                          //~1127I~//~v@@@I~
        if ( r > 0 && r < i ) r = i;                               //~1127I~//~v@@@I~
        if ( g > 0 && g < i ) g = i;                               //~1127I~//~v@@@I~
        if ( b > 0 && b < i ) b = i;                               //~1127I~//~v@@@I~
                                                                   //~1127I~//~v@@@I~
        return Color.rgb(Math.min((int)(r/FACTOR), 255),           //~1127I~//~v@@@I~
                         Math.min((int)(g/FACTOR), 255),           //~1127I~//~v@@@I~
                         Math.min((int)(b/FACTOR), 255));          //~1127I~//~v@@@I~
    }                                                              //~1127I~//~v@@@I~
//**********************                                           //~1127I~//~v@@@I~
    public static int darkerColor(int Pcolor)                           //~v@@@I~
	{                                                              //~v@@@I~
    	return Color.rgb(Math.max((int)(Color.red(Pcolor)  *FACTOR), 0),        //~1127I~//~v@@@R~
             Math.max((int)(Color.green(Pcolor)*FACTOR), 0),                //~1127I~//~v@@@I~
             Math.max((int)(Color.blue(Pcolor)*FACTOR), 0));               //~1127I~//~v@@@I~
    }                                                              //~1127I~//~v@@@I~
//**********************                                           //~v@@@I~
    public static Boolean isShowingDialogFragment(DialogFragment Pdlg)//~v@@@I~
	{                                                              //~v@@@I~
    	Boolean rc=Pdlg!=null && Pdlg.getDialog()!=null && Pdlg.getDialog().isShowing();//~v@@@I~
    	if (Dump.Y) Dump.println("Utils.isShowingDialogFragment rc="+rc+",Pdlg="+Utils.toString(Pdlg));//~@@01R~
    	return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************                                           //~v@@@I~
    public static String getStr(int Presid)                        //~v@@@I~
	{                                                              //~v@@@I~
    	if (Presid==0)                                             //~@@01I~
        	return "";                                             //~@@01I~
    	return AG.resource.getString(Presid);                      //~v@@@R~
    }                                                              //~v@@@I~
//**********************                                           //~va11I~
    public static Spanned getStrHtml(int Presid)                   //~va11I~
    {                                                              //~va11I~
//      Spanned txt= Html.fromHtml(Utils.getStr(Presid));          //~va11I~//~va40R~
        Spanned txt=Utils.fromHtml(Utils.getStr(Presid));          //~va40I~
        return txt;                                                //~va11I~
    }                                                              //~va11I~
//**********************                                           //~v@@@I~
    public static String getStr(int Presid,String P1)              //~v@@@I~
	{                                                              //~v@@@I~
    	return AG.resource.getString(Presid,P1);                   //~v@@@I~
    }                                                              //~v@@@I~
//**********************                                           //~v@@@I~
    public static String getStr(int Presid,int P1)                 //~v@@@I~
	{                                                              //~v@@@I~
    	return AG.resource.getString(Presid,P1);                   //~v@@@I~
    }                                                              //~v@@@I~
//**********************                                           //~v@@@I~
    public static String getStr(int Presid,String P1,String P2)    //~v@@@I~
	{                                                              //~v@@@I~
    	return AG.resource.getString(Presid,P1,P2);                //~v@@@I~
    }                                                              //~v@@@I~
//**********************                                           //~v@@@I~
    public static String joinStr(String Pseparater,String[] Pstrarray)//~v@@@I~
	{
	 //  	StringJoiner sj=new StringJoiner(Pseparater);     //from api24          //~v@@@I~
        StringBuffer sb=new StringBuffer();
        for (String s:Pstrarray)                                   //~v@@@I~
        {                                                          //~v@@@I~
    		sb.append(s+Pseparater);                                             //~v@@@I~
    	}                                                          //~v@@@I~
        return sb.substring(0,sb.length()-1);                                      //~v@@@I~
    }                                                              //~v@@@I~
//**********************                                           //~v@@@I~
    public static String joinStr(String[] Pstrarray)               //~v@@@I~
	{                                                              //~v@@@I~
        return joinStr(",",Pstrarray);                                    //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~1402I~//~@@@@M~//~v@@@M~
//*Preference read/write                                               *//~1402I~//~@@@@M~//~v@@@M~
//**********************************************************************//~1402I~//~@@@@M~//~v@@@M~
    public static String getPreference(String Pkey)                      //~1402I~//~@@@@R~//~v@@@M~
    {                                                              //~1402I~//~@@@@M~//~v@@@M~
	    return getPreference(Pkey,"");                             //~1402I~//~@@@@M~//~v@@@M~
    }                                                              //~1402I~//~@@@@M~//~v@@@M~
    //******************                                           //~v107I~//~@@@@M~//~v@@@M~
    public static boolean getPreference(String Pkey,boolean Pdefault)//~v107I~//~@@@@M~//~v@@@M~
    {                                                              //~v107I~//~@@@@M~//~v@@@M~
    	SharedPreferences pref=getPreferenceName();                //~v107I~//~@@@@M~//~v@@@M~
        boolean value=pref.getBoolean(Pkey,Pdefault);              //~v107I~//~@@@@M~//~v@@@M~
        return value;                                              //~v107I~//~@@@@M~//~v@@@M~
    }                                                              //~v107I~//~@@@@M~//~v@@@M~
    //******************                                           //~v107I~//~@@@@M~//~v@@@M~
    public static int getPreference(String Pkey,int Pdefault)      //~v107I~//~@@@@M~//~v@@@M~
    {                                                              //~v107I~//~@@@@M~//~v@@@M~
    	SharedPreferences pref=getPreferenceName();                //~v107I~//~@@@@M~//~v@@@M~
        int value=pref.getInt(Pkey,Pdefault);                      //~v107I~//~@@@@M~//~v@@@M~
        return value;                                              //~v107I~//~@@@@M~//~v@@@M~
    }                                                              //~v107I~//~@@@@M~//~v@@@M~
    //******************                                           //~v107I~//~@@@@M~//~v@@@M~
    public static String getPreference(String Pkey,String Pdefault)//~1402I~//~@@@@M~//~v@@@M~
    {                                                              //~1402I~//~@@@@M~//~v@@@M~
    	SharedPreferences pref=getPreferenceName();                 //~1402I~//~@@@@M~//~v@@@M~
        String value=pref.getString(Pkey,Pdefault/*default value*/);//~1402R~//~@@@@M~//~v@@@M~
        if (Dump.Y) Dump.println("getPreference:"+Pkey+"="+value); //~1506R~//~@@@@M~//~v@@@M~
        return value;                                              //~1402I~//~@@@@M~//~v@@@M~
    }//readwriteQNo                                                //~1402I~//~@@@@M~//~v@@@M~
    //******************                                           //~1402I~//~@@@@M~//~v@@@M~
    public static void putPreference(String Pkey,String Pvalue)        //~1402I~//~@@@@M~//~v@@@M~
    {                                                              //~1402I~//~@@@@M~//~v@@@M~
        if (Dump.Y) Dump.println("putPreference:"+Pkey+"="+Pvalue);//~1506R~//~@@@@M~//~v@@@M~
    	SharedPreferences pref=getPreferenceName();                 //~1402I~//~@@@@M~//~v@@@M~
        SharedPreferences.Editor editor=pref.edit();               //~1402I~//~@@@@M~//~v@@@M~
        editor.putString(Pkey,Pvalue);                             //~1402I~//~@@@@M~//~v@@@M~
        editor.commit();                                           //~1402I~//~@@@@M~//~v@@@M~
    }                                                              //~v107R~//~@@@@M~//~v@@@M~
    //******************                                           //~1402I~//~@@@@M~//~v@@@M~
    public static void putPreference(String Pkey,boolean Pvalue)   //~v107I~//~@@@@M~//~v@@@M~
    {                                                              //~v107I~//~@@@@M~//~v@@@M~
    	SharedPreferences pref=getPreferenceName();                //~v107I~//~@@@@M~//~v@@@M~
        SharedPreferences.Editor editor=pref.edit();               //~v107I~//~@@@@M~//~v@@@M~
        editor.putBoolean(Pkey,Pvalue);                            //~v107I~//~@@@@M~//~v@@@M~
        editor.commit();                                           //~v107I~//~@@@@M~//~v@@@M~
    }                                                              //~v107I~//~@@@@M~//~v@@@M~
    //******************                                           //~v107I~//~@@@@M~//~v@@@M~
    public static void putPreference(String Pkey,int Pvalue)       //~v107I~//~@@@@M~//~v@@@M~
    {                                                              //~v107I~//~@@@@M~//~v@@@M~
    	SharedPreferences pref=getPreferenceName();                //~v107I~//~@@@@M~//~v@@@M~
        SharedPreferences.Editor editor=pref.edit();               //~v107I~//~@@@@M~//~v@@@M~
        editor.putInt(Pkey,Pvalue);                                //~v107I~//~@@@@M~//~v@@@M~
        editor.commit();                                           //~v107I~//~@@@@M~//~v@@@M~
    }                                                              //~v107I~//~@@@@M~//~v@@@M~
    //******************                                           //~v107I~//~@@@@M~//~v@@@M~
    private static SharedPreferences getPreferenceName()                   //~1402I~//~@@@@M~//~v@@@M~
    {                                                              //~1402I~//~@@@@M~//~v@@@M~
        return AG.context.getSharedPreferences(sharedPreferenceName,Context.MODE_PRIVATE);//~@@@@I~//~v@@@M~
    }                                                              //~1402I~//~@@@@M~//~v@@@M~
    //******************                                           //~v@@@I~
    public static String getClassName(Object Pobj)                //~v@@@I~
    {                                                              //~v@@@I~
        return (Pobj==null ? "null" :Pobj.getClass().getSimpleName());//~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************            //~v@@@I~//~@@01M~
    public static int getRandom(int Pmax)                                //~v@@@I~//~@@01R~
    {                                                              //~v@@@I~//~@@01M~
    	int r=(int)(Math.random()*Pmax);  //random generate double 0.0<=  <1.0//~v@@@I~//~@@01M~
        return r;                                                  //~v@@@I~//~@@01M~
    }                                                              //~v@@@I~//~@@01M~
    //*************************************************            //~@@01I~
    public static int parseInt(String Pstr,int Pdefault)           //~@@01R~
    {                                                              //~@@01I~
    	int ii;                                                    //~@@01I~
        try                                                        //~@@01I~
        {                                                          //~@@01I~
    		ii=Integer.parseInt(Pstr);                             //~@@01I~
        }                                                          //~@@01I~
        catch(Exception e)                                         //~@@01I~
        {                                                          //~@@01I~
//      	Dump.println(e,"parseInt str="+Pstr);                  //~@@01R~
        	if (Dump.Y) Dump.println("Utils.parseInt str="+Pstr+",e="+e.toString());//~@@01R~
        	ii=Pdefault;                                           //~@@01R~
        }                                                          //~@@01I~
        return ii;                                                 //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static int parseIntHex(String Pstr,int Pdefault)        //~@@01I~
    {                                                              //~@@01I~
    	int ii;                                                    //~@@01I~
        try                                                        //~@@01I~
        {                                                          //~@@01I~
    		ii=Integer.parseInt(Pstr,16);                          //~@@01I~
        }                                                          //~@@01I~
        catch(Exception e)                                         //~@@01I~
        {                                                          //~@@01I~
        	if (Dump.Y) Dump.println("Utils.parseIntHex str="+Pstr+",e="+e.toString());//~@@01I~
        	ii=Pdefault;                                           //~@@01I~
        }                                                          //~@@01I~
        return ii;                                                 //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    //*return err ctr                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static int parseInt(String[] Pstr,int Pdefault,int[] Pint)//~@@01R~
    {                                                              //~@@01I~
	    return parseInt(Pstr,0,Pstr.length,Pdefault,Pint);            //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    //*return err ctr                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static int parseInt(String[] Pstr,int Ppos,int Pctr,int Pdefault,int[] Pint)//~@@01I~
    {                                                              //~@@01I~
    	int rc=0;                                                  //~@@01R~
    	for (int ii=0,pos=Ppos;ii<Pctr;ii++,pos++)                 //~@@01R~
        {                                                          //~@@01I~
    		int pi;                                                //~@@01I~
            if (pos>=Pstr.length)                                //~@@01I~
            {                                                      //~@@01I~
            	pi=Pdefault;                                       //~@@01I~
                rc++;                                              //~@@01I~
            }                                                      //~@@01I~
            else                                                   //~@@01I~
                try                                                //~@@01R~
                {                                                  //~@@01R~
                    pi=Integer.parseInt(Pstr[pos]);                //~@@01R~
                }                                                  //~@@01R~
                catch(Exception e)                                 //~@@01R~
                {                                                  //~@@01R~
                    if (Dump.Y) Dump.println("Utils.parseInt str="+Pstr[ii]+"e="+e.toString());//~@@01R~
                    pi=Pdefault;                                   //~@@01R~
                    rc++;                                          //~@@01R~
                }                                                  //~@@01R~
            Pint[ii]=pi;                                           //~@@01R~
        }                                                          //~@@01I~
        if (Dump.Y) Dump.println("Utils.parseInt rc="+rc);         //~@@01I~
        if (Dump.Y) Dump.println("Utils.parseInt Pstr="+Arrays.toString(Pstr)+",pos="+Ppos+",ctr="+Pctr+",default="+Pdefault+",out="+Arrays.toString(Pint));//~@@01I~
        return rc;                                                 //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~va7bI~
    //*return err ctr                                              //~va7bI~
    //*************************************************            //~va7bI~
    public static int parseDouble(String[] Pstr,double Pdefault,double[] Pdouble)//~va7bR~
    {                                                              //~va7bI~
	    return parseDouble(Pstr,0,Pstr.length,Pdefault,Pdouble);   //~va7bI~
    }                                                              //~va7bI~
    //*************************************************            //~va7bI~
    //*get int from double by cut flagment                         //~va7bI~
    //*************************************************            //~va7bI~
    public static int parseDoubleToInt(String[] Pstr,int Punit1,int Punit2,int[] Pint)//~va7bI~
    {                                                              //~va7bI~
    	int err=0;                                                 //~va7bI~
    	for (int ii=0;ii<Pstr.length;ii++)                         //~va7bI~
        {                                                          //~va7bI~
	    	Pint[ii]=parseDoubleToInt(Pstr[ii],Punit1,Punit2);     //~va7bI~
	    	if (Pint[ii]==Integer.MAX_VALUE)                       //~va7bI~
            	err++;                                             //~va7bI~
        }                                                          //~va7bI~
        if (Dump.Y) Dump.println("Utils.parseDoubleToInt err="+err+",str="+Arrays.toString(Pstr)+",unit1="+Punit1+"unit2="+Punit2+",ints="+Arrays.toString(Pint));//~va7bI~
        return err;                                                //~va7bI~
    }                                                              //~va7bI~
    public static int parseDoubleToInt(String Pstr,int Punit1,int Punit2)//~va7bI~
    {                                                              //~va7bI~
    	double dbl;                                                //~va7bI~
        int pi;                                                     //~va7bI~
        try                                                        //~va7bI~
        {                                                          //~va7bI~
            dbl=Double.parseDouble(Pstr);                          //~va7bI~
            double tmp=dbl*Punit1+(dbl<0 ? -(Punit2-1) : (Punit2-1));//~va7bR~
            pi=(((int)tmp)/Punit2)*Punit2;                         //~va7bR~
            if (Dump.Y) Dump.println("Utils.parseDoubleToInt str="+Pstr+",dbl="+dbl+",tmp="+tmp+",pi="+pi);//~va7bR~
        }                                                          //~va7bI~
        catch(Exception e)                                         //~va7bI~
        {                                                          //~va7bI~
            if (Dump.Y) Dump.println("Utils.parseDouble str="+Pstr+"e="+e.toString());//~va7bI~
            pi=Integer.MAX_VALUE;                                  //~va7bI~
        }                                                          //~va7bI~
        return pi;                                                 //~va7bI~
    }                                                              //~va7bI~
    //*************************************************            //~va7bI~
    //*return err ctr                                              //~va7bI~
    //*************************************************            //~va7bI~
    public static int parseDouble(String[] Pstr,int Ppos,int Pctr,double Pdefault,double[] Pdouble)//~va7bI~
    {                                                              //~va7bI~
    	int rc=0;                                                  //~va7bI~
    	for (int ii=0,pos=Ppos;ii<Pctr;ii++,pos++)                 //~va7bI~
        {                                                          //~va7bI~
    		double pi;                                            //~va7bI~
            if (pos>=Pstr.length)                                  //~va7bI~
            {                                                      //~va7bI~
            	pi=Pdefault;                                       //~va7bI~
                rc++;                                              //~va7bI~
            }                                                      //~va7bI~
            else                                                   //~va7bI~
                try                                                //~va7bI~
                {                                                  //~va7bI~
                    pi=Double.parseDouble(Pstr[pos]);              //~va7bI~
                }                                                  //~va7bI~
                catch(Exception e)                                 //~va7bI~
                {                                                  //~va7bI~
                    if (Dump.Y) Dump.println("Utils.parseDouble str="+Pstr[ii]+"e="+e.toString());//~va7bI~
                    pi=Pdefault;                                   //~va7bI~
                    rc++;                                          //~va7bI~
                }                                                  //~va7bI~
            Pdouble[ii]=pi;                                        //~va7bI~
        }                                                          //~va7bI~
        if (Dump.Y) Dump.println("Utils.parseDouble rc="+rc);      //~va7bI~
        if (Dump.Y) Dump.println("Utils.parseDouble Pstr="+Arrays.toString(Pstr)+",pos="+Ppos+",ctr="+Pctr+",default="+Pdefault+",out="+Arrays.toString(Pdouble));//~va7bI~
        return rc;                                                 //~va7bI~
    }                                                              //~va7bI~
    //*************************************************            //~@@01I~
    public static int roundUp(int Pval,int Punit)                  //~@@01I~
    {                                                              //~@@01I~
    	int up=((Pval+Punit-1)/Punit)*Punit;                       //~@@01I~
        if (Dump.Y) Dump.println("Utils.roundup in="+Pval+",unit="+Punit+",out="+up);//~@@01I~
        return up;                                                 //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static int[] arrayAdd(int[] PaddTo,int[] Padd)          //~@@01I~
    {                                                              //~@@01I~
        if (Dump.Y) Dump.println("Utils.arrayAdd add="+Arrays.toString(Padd)+",to="+Arrays.toString(PaddTo));//~@@01I~
        for (int ii=0;ii<Padd.length;ii++)     //account sequence  //~@@01I~
        {                                                          //~@@01I~
            PaddTo[ii]+=Padd[ii];                                  //~@@01I~
        }                                                          //~@@01I~
        if (Dump.Y) Dump.println("Utils.arrayAdd result="+Arrays.toString(PaddTo));//~@@01I~
        return PaddTo;                                             //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static String toString(String[] Psa)                    //~@@01I~
    {                                                              //~@@01I~
    	String s;                                                  //~@@01I~
        if (Psa==null)                                             //~@@01I~
	        s="null";                                              //~@@01I~
        else                                                       //~@@01I~
            s=Arrays.toString(Psa);                                //~@@01I~
        if (Dump.Y) Dump.println("Utils.toString(String[]) out="+s);//~@@01I~
        return s;                                                  //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static String toString(String[][] Psa2)                   //~@@01I~
    {                                                              //~@@01I~
        StringBuffer sb=new StringBuffer();                        //~@@01I~
        sb.append("[");                                            //~@@01I~
        if (Psa2==null)                                            //~@@01I~
	        sb.append("null");                                     //~@@01I~
        else                                                       //~@@01I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~@@01I~
        {                                                          //~@@01I~
        	if (ii!=0)                                             //~@@01I~
    	        sb.append(",");                                    //~@@01I~
            sb.append(Arrays.toString(Psa2[ii]));                  //~@@01R~
        }                                                          //~@@01I~
        sb.append("]");                                            //~@@01I~
        String s=sb.toString();                                    //~@@01I~
        if (Dump.Y) Dump.println("Utils.toString(String[][]) out="+s);//~@@01R~
        return s;                                                  //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~va60I~
    public static String toString(int[] Psa2)                      //~va60I~
    {                                                              //~va60I~
        if (Psa2==null)                                            //~va60I~
        	return "null";                                         //~va60I~
        return Arrays.toString(Psa2);                              //~va60I~
    }                                                              //~va60I~
    //*************************************************            //~va40I~
    public static String toString(int[] Psa2,int PctrSplit)        //~va40I~
    {                                                              //~va40I~
    	if (Psa2==null)                                            //+vaafI~
        	return "null";                                         //+vaafI~
    	return toString(Psa2,PctrSplit,Psa2.length);               //~va40I~
    }                                                              //~va40I~
    //*************************************************            //~vaafI~
    public static String toHexString(int[] Psa2,int PctrSplit)     //~vaafR~
    {                                                              //~vaafI~
    	if (Psa2==null)                                            //+vaafI~
        	return "null";                                         //+vaafI~
    	return toHexString(Psa2,PctrSplit,Psa2.length);            //~vaafI~
    }                                                              //~vaafI~
    //*************************************************            //~vaafI~
    //*splitctr: 0=all,-1=up to max ctr                            //~vaafI~
    //*************************************************            //~vaafI~
    public static String toHexString(int[] Psa2,int PctrSplit,int PctrMax)//~vaafI~
    {                                                              //~vaafI~
        if (Psa2==null)                                            //~vaafI~
        	return "null";                                         //~vaafI~
    	if (PctrSplit==0)                                          //~vaafI~
        {                                                          //~vaafI~
        	return Arrays.toString(Psa2);                          //~vaafI~
        }                                                          //~vaafI~
        int ctrMax=Math.min(PctrMax,Psa2.length);                  //~vaafI~
        StringBuffer sb=new StringBuffer();                        //~vaafI~
        sb.append("[");                                            //~vaafI~
        if (Psa2==null)                                            //~vaafI~
	        sb.append("null");                                     //~vaafI~
        else                                                       //~vaafI~
        for (int ii=0;ii<ctrMax;ii++)     //account sequence       //~vaafI~
        {                                                          //~vaafI~
        	if (ii!=0)                                             //~vaafI~
                if (PctrSplit<0)                                   //~vaafI~
    	        	sb.append(",");                                //~vaafI~
                else                                               //~vaafI~
            	if (ii%PctrSplit==0)                               //~vaafI~
                {                                                  //~vaafI~
	    	        sb.append("]");                                //~vaafI~
                    if (ii<ctrMax)                                 //~vaafI~
		    	        sb.append("[");                            //~vaafI~
                }                                                  //~vaafI~
                else                                               //~vaafI~
    	        	sb.append(",");                                //~vaafI~
            sb.append(Integer.toHexString(Psa2[ii]));              //~vaafI~
        }                                                          //~vaafI~
        sb.append("]");                                            //~vaafI~
        String s=sb.toString();                                    //~vaafI~
        return s;                                                  //~vaafI~
    }                                                              //~vaafI~
    //*************************************************            //~va60I~
    public static String toHexString(int[] Psa2)                   //~va60I~
    {                                                              //~va60I~
        StringBuffer sb=new StringBuffer();                        //~va60I~
        sb.append("[");                                            //~va60I~
        if (Psa2==null)                                            //~va60I~
	        sb.append("null");                                     //~va60I~
        else                                                       //~va60I~
        {                                                          //~va60I~
        	int ctrMax=Psa2.length;                                    //~va60I~
            for (int ii=0;ii<ctrMax;ii++)     //account sequence   //~va60I~
            {                                                      //~va60I~
                sb.append(Integer.toHexString(Psa2[ii]));          //~va60I~
                if (ii!=ctrMax-1)                                  //~va60I~
                	sb.append(",");                                //~va60I~
            }                                                      //~va60I~
        }                                                          //~va60I~
        sb.append("]");                                            //~va60I~
        String s=sb.toString();                                    //~va60I~
        return s;                                                  //~va60I~
    }                                                              //~va60I~
    //*************************************************            //~va60I~
    public static String toStringMax(int[] Psa2,int PctrMax)       //~va60I~
    {                                                              //~va60I~
		return toString(Psa2,-1,PctrMax);                          //~va60I~
    }                                                              //~va60I~
    //*************************************************            //~va40I~
    //*splitctr: 0=all,-1=up to max ctr                            //~va60I~
    //*************************************************            //~va60I~
    public static String toString(int[] Psa2,int PctrSplit,int PctrMax)//~va40I~
    {                                                              //~va40I~
        if (Psa2==null)                                            //~va60I~
        	return "null";                                         //~va60I~
    	if (PctrSplit==0)                                          //~va40I~
        {                                                          //~va40I~
        	return Arrays.toString(Psa2);                          //~va40I~
        }                                                          //~va40I~
        int ctrMax=Math.min(PctrMax,Psa2.length);                 //~va40I~
        StringBuffer sb=new StringBuffer();                        //~va40I~
        sb.append("[");                                            //~va40I~
        if (Psa2==null)                                            //~va40I~
	        sb.append("null");                                     //~va40I~
        else                                                       //~va40I~
        for (int ii=0;ii<ctrMax;ii++)     //account sequence       //~va40I~
        {                                                          //~va40I~
        	if (ii!=0)                                             //~va40I~
                if (PctrSplit<0)                                   //~va60R~
    	        	sb.append(",");                                //~va60I~
                else                                               //~va60I~
            	if (ii%PctrSplit==0)                               //~va40I~
                {                                                  //~va40I~
	    	        sb.append("]");                                //~va40I~
//                  if (ii+1<ctrMax)                               //~va40I~//~va60R~
                    if (ii<ctrMax)                                 //~va60I~
		    	        sb.append("[");                            //~va40I~
                }                                                  //~va40I~
                else                                               //~va40I~
    	        	sb.append(",");                                //~va40I~
            sb.append(Integer.toString(Psa2[ii]));                 //~va40I~
        }                                                          //~va40I~
        sb.append("]");                                            //~va40I~
        String s=sb.toString();                                    //~va40I~
//      if (Dump.Y) Dump.println("Utils.toString(int[][]) out="+s);//~va40I~
        return s;                                                  //~va40I~
    }                                                              //~va40I~
    //*************************************************            //~va60I~
    public static String toString(boolean[] Psa2,int PctrSplit)    //~va60I~
    {                                                              //~va60I~
    	if (Psa2==null)                                            //~va60I~
        	return "null";                                         //~va60I~
    	return toString(Psa2,PctrSplit,Psa2.length);               //~va60I~
    }                                                              //~va60I~
    //*************************************************            //~va60I~
    public static String toStringMax(boolean[] Psa2,int PctrMax)   //~va60I~
    {                                                              //~va60I~
		return toString(Psa2,-1,PctrMax);                          //~va60I~
    }                                                              //~va60I~
    //*************************************************            //~va60I~
    public static String toString(boolean[] Psa2,int PctrSplit,int PctrMax)//~va60I~
    {                                                              //~va60I~
        if (Psa2==null)                                            //~va60I~
        	return "null";                                         //~va60I~
    	if (PctrSplit==0)                                          //~va60I~
        {                                                          //~va60I~
        	return Arrays.toString(Psa2);                          //~va60I~
        }                                                          //~va60I~
        int ctrMax=Math.min(PctrMax,Psa2.length);                  //~va60I~
        StringBuffer sb=new StringBuffer();                        //~va60I~
        sb.append("[");                                            //~va60I~
        if (Psa2==null)                                            //~va60I~
	        sb.append("null");                                     //~va60I~
        else                                                       //~va60I~
        for (int ii=0;ii<ctrMax;ii++)     //account sequence       //~va60I~
        {                                                          //~va60I~
        	if (ii!=0)                                             //~va60I~
            	if (ii%PctrSplit==0)                               //~va60I~
                {                                                  //~va60I~
	    	        sb.append("]");                                //~va60I~
//                  if (ii+1<ctrMax)                               //~va60R~
                    if (ii<ctrMax)                                 //~va60I~
		    	        sb.append("[");                            //~va60I~
                }                                                  //~va60I~
                else                                               //~va60I~
    	        	sb.append(",");                                //~va60I~
            sb.append(Psa2[ii]?"T":"-");                           //~va60R~
        }                                                          //~va60I~
        sb.append("]");                                            //~va60I~
        String s=sb.toString();                                    //~va60I~
//      if (Dump.Y) Dump.println("Utils.toString(int[][]) out="+s);//~va60I~
        return s;                                                  //~va60I~
    }                                                              //~va60I~
    //*************************************************            //~@@01I~
    public static String toString(int[][] Psa2)                    //~@@01I~
    {                                                              //~@@01I~
        StringBuffer sb=new StringBuffer();                        //~@@01I~
        sb.append("[");                                            //~@@01I~
        if (Psa2==null)                                            //~@@01I~
	        sb.append("null");                                     //~@@01I~
        else                                                       //~@@01I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~@@01I~
        {                                                          //~@@01I~
        	if (ii!=0)                                             //~@@01I~
    	        sb.append(",");                                    //~@@01I~
            sb.append(Arrays.toString(Psa2[ii]));                  //~@@01I~
        }                                                          //~@@01I~
        sb.append("]");                                            //~@@01I~
        String s=sb.toString();                                    //~@@01I~
//      if (Dump.Y) Dump.println("Utils.toString(int[][]) out="+s);//~@@01I~//~va40R~
        return s;                                                  //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static String toString(boolean[][] Psa2)                //~@@01I~
    {                                                              //~@@01I~
        StringBuffer sb=new StringBuffer();                        //~@@01I~
        sb.append("[");                                            //~@@01I~
        if (Psa2==null)                                            //~@@01I~
	        sb.append("null");                                     //~@@01I~
        else                                                       //~@@01I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~@@01I~
        {                                                          //~@@01I~
        	if (ii!=0)                                             //~@@01I~
    	        sb.append(",");                                    //~@@01I~
            sb.append(Arrays.toString(Psa2[ii]));                  //~@@01I~
        }                                                          //~@@01I~
        sb.append("]");                                            //~@@01I~
        String s=sb.toString();                                    //~@@01I~
//      if (Dump.Y) Dump.println("Utils.toString(boolean[][]) out="+s);//~@@01I~//~va40R~
        return s;                                                  //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static String toString(Rect[] Psa2)                     //~@@01I~
    {                                                              //~@@01I~
        StringBuffer sb=new StringBuffer();                        //~@@01I~
        sb.append("[");                                            //~@@01I~
        if (Psa2==null)                                            //~@@01I~
	        sb.append("null");                                     //~@@01I~
        else                                                       //~@@01I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~@@01I~
        {                                                          //~@@01I~
        	if (ii!=0)                                             //~@@01I~
    	        sb.append(",");                                    //~@@01I~
            sb.append(Psa2[ii]==null ? "null" : Psa2[ii].toString());//~@@01R~
        }                                                          //~@@01I~
        sb.append("]");                                            //~@@01I~
        String s=sb.toString();                                    //~@@01I~
//      if (Dump.Y) Dump.println("Utils.toString(Rect[]) out="+s); //~@@01I~//~va40R~
        return s;                                                  //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static String toString(Rect[][] Psa2)                   //~@@01I~
    {                                                              //~@@01I~
        StringBuffer sb=new StringBuffer();                        //~@@01I~
        sb.append("[");                                            //~@@01I~
        if (Psa2==null)                                            //~@@01I~
	        sb.append("null");                                     //~@@01I~
        else                                                       //~@@01I~
        for (int ii=0;ii<Psa2.length;ii++)     //account sequence  //~@@01I~
        {                                                          //~@@01I~
        	if (ii!=0)                                             //~@@01I~
    	        sb.append(",");                                    //~@@01I~
            sb.append(toString(Psa2[ii]));                         //~@@01I~
        }                                                          //~@@01I~
        sb.append("]");                                            //~@@01I~
        String s=sb.toString();                                    //~@@01I~
//      if (Dump.Y) Dump.println("Utils.toString(Rect[][]) out="+s);//~@@01I~//~va40R~
        return s;                                                  //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static String toString(String Pstr)                     //~@@01I~
    {                                                              //~@@01I~
    	return Pstr==null ? "null" : Pstr;                         //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static String toString(Object Pobj)                     //~@@01I~
    {                                                              //~@@01I~
    	return Pobj==null ? "null" : Pobj.toString();              //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~va11I~
    public static String toString(Object[] Pobj)                   //~va11I~
    {                                                              //~va11I~
        StringBuffer sb=new StringBuffer();                        //~va11I~
        if (Pobj==null)                                            //~va11I~
        {                                                          //~va11I~
	        sb.append("Pobj[]=null"+"\n");                         //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
        {                                                          //~va11I~
        	sb.append(Pobj.toString()+"\n");                       //~va11R~
            int ctr=0;                                             //~va11I~
    		for (Object obj:Pobj)                                  //~va11R~
            {                                                      //~va11I~
	        	sb.append("["+ctr+"]="+Utils.toString(obj)+"\n");  //~va11R~
                ctr++;                                             //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        return sb.toString();                                      //~va11I~
    }                                                              //~va11I~
    //*************************************************            //~va11I~
    public static String toString(Object[][] Pobj)                 //~va11I~
    {                                                              //~va11I~
        StringBuffer sb=new StringBuffer();                        //~va11I~
        if (Pobj==null)                                            //~va11I~
        {                                                          //~va11I~
	        sb.append("Pobj[][]=null"+"\n");                       //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
        {                                                          //~va11I~
        	sb.append(Pobj.toString()+"\n");                       //~va11R~
            int ctr=0;                                             //~va11I~
    		for (Object[] obj:Pobj)                                //~va11R~
            {                                                      //~va11I~
	        	sb.append("["+ctr+"][]="+Utils.toString(obj));  //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        return sb.toString();                                      //~va11I~
    }                                                              //~va11I~
    //*************************************************            //~@@01I~
    public static String cvTextU2S(String Ptxt)                    //~@@01I~
    {                                                              //~@@01I~
        StringBuffer sb=new StringBuffer();                        //~@@01I~
        int pos=0;                                                 //~@@01I~
        int len=Ptxt.length();                                       //~@@01I~
        while(true)                                                //~@@01I~
        {                                                          //~@@01I~
        	int pos2=Ptxt.indexOf("\\u",pos);                      //~@@01I~
            if (pos2<0 || pos2+6>len)                              //~@@01R~
            {                                                      //~@@01I~
            	sb.append(Ptxt.substring(pos));                     //~@@01I~
            	break;                                             //~@@01I~
            }                                                      //~@@01I~
            int cp=getCodePointU2S(Ptxt,pos2+2);                   //~@@01R~
            if (cp==-1)                                            //~@@01I~
            {                                                      //~@@01I~
            	sb.append(Ptxt.substring(pos,pos2+2));             //~@@01R~
                pos=pos2+2;                                        //~@@01R~
            }                                                      //~@@01I~
            else                                                   //~@@01I~
            {                                                      //~@@01I~
            	sb.append(Ptxt.substring(pos,pos2));               //~@@01I~
            	sb.append(Character.valueOf((char)cp));                  //~@@01I~
                pos=pos2+6;                                        //~@@01R~
            }                                                      //~@@01I~
        }                                                          //~@@01I~
        String s=sb.toString();                                    //~@@01I~
        if (Dump.Y) Dump.println("Utils.cvTextU2S str="+Ptxt+",out="+s);//~@@01I~
        return s;                                                  //~@@01I~
    }                                                              //~@@01I~
    //*************************************************            //~@@01I~
    public static int getCodePointU2S(String Ptxt,int Ppos)        //~@@01I~
    {                                                              //~@@01I~
    	int cp=0;                                                  //~@@01I~
        boolean swErr=false;                                       //~@@01I~
    	for (int ii=0;ii<4;ii++)                                   //~@@01I~
        {                                                          //~@@01I~
    		int cp2=Ptxt.codePointAt(Ppos+ii);                     //~@@01R~
            if (cp2>='0' && cp2<='9')                              //~@@01I~
            	cp+=cp2-'0';                                       //~@@01I~
            else                                                   //~@@01I~
            if (cp2>='a' && cp2<='f')                              //~@@01I~
            	cp+=cp2-'a'+10;                                    //~@@01R~
            else                                                   //~@@01I~
            if (cp2>='A' && cp2<='F')                              //~@@01I~
            	cp+=cp2-'A'+10;                                    //~@@01R~
            else                                                   //~@@01I~
            {                                                      //~@@01I~
            	swErr=true;                                        //~@@01I~
                break;                                             //~@@01I~
            }                                                      //~@@01I~
            if (ii!=3)                                             //~@@01I~
            	cp<<=4;                                             //~@@01I~
        }                                                          //~@@01I~
        if (swErr)                                                 //~@@01I~
        	cp=-1;                                                 //~@@01I~
        else                                                       //~@@01I~
        if (!Character.isDefined(cp))                              //~@@01I~
        	cp=-1;                                                 //~@@01I~
        if (Dump.Y) Dump.println("Utils.getCodePointU2S str="+Ptxt.substring(Ppos,Ppos+4)+",out="+Integer.toHexString(cp));//~@@01I~
        return cp;                                                 //~@@01I~
    }                                                              //~@@01I~
    //*******************************************************************//~@@01I~
    public static void boolean_int(boolean Pswb2i,boolean[] Pb,int[] Pi)//~@@01I~
    {                                                              //~@@01I~
        if (Dump.Y) Dump.println("Utils.boolean_int swb2i="+Pswb2i+",Pb="+Arrays.toString(Pb)+",Pi="+Arrays.toString(Pi));//~@@01R~
    	if (Pswb2i)                                                //~@@01R~
        {                                                          //~@@01I~
        	for (int ii=0;ii<Pb.length;ii++)                       //~@@01I~
            	Pi[ii]=Pb[ii]?1:0;                                 //~@@01R~
        }                                                          //~@@01I~
        else                                                       //~@@01I~
        {                                                          //~@@01I~
        	for (int ii=0;ii<Pi.length;ii++)                       //~@@01I~
            	Pb[ii]=Pi[ii]!=0;                                  //~@@01R~
        }                                                          //~@@01I~
        if (Dump.Y) Dump.println("Utils.boolean_int out Pb="+Arrays.toString(Pb)+",Pi="+Arrays.toString(Pi));//~@@01R~
    }                                                              //~@@01I~
    //*******************************************************************//~@@01I~
    public static void moveAppToFront()                            //~@@01I~
    {                                                              //~@@01I~
    	String pkgname=AG.context.getPackageName();                //~@@01I~
        if (Dump.Y) Dump.println("Utils.moveAppToFront pkgname="+pkgname);//~@@01I~
	    int taskid;                                                //~@@01R~
		if (AG.osVersion>=LOLLIPOP)	//android5-api21               //~@@01I~
		    moveAppToFront_From_LOLLIPOP(pkgname);                 //~@@01R~
        else                                                       //~@@01I~
        {                                                          //~@@01I~
		    taskid=getAppID_Under_LOLLIPOP(pkgname);               //~@@01I~
            if (taskid>=0)                                         //~@@01R~
            {                                                      //~@@01R~
                ActivityManager am=(ActivityManager)AG.context.getSystemService(Context.ACTIVITY_SERVICE);//~@@01R~
                am.moveTaskToFront(taskid,ActivityManager.MOVE_TASK_WITH_HOME);//~@@01R~
            }                                                      //~@@01R~
        }                                                          //~@@01I~
    }                                                              //~@@01I~
    //*******************************************************************//~@@01I~
	@TargetApi(LOLLIPOP)                                           //~@@01I~
    public static void moveAppToFront_From_LOLLIPOP(String Ppkgname)//~@@01R~
    {                                                              //~@@01I~
        if (Dump.Y) Dump.println("Utils.moveAppToFront_From_LOLLIPOP pkgname="+Ppkgname);//~@@01I~
        ActivityManager am=(ActivityManager)AG.context.getSystemService(Context.ACTIVITY_SERVICE);//~@@01I~
        List<ActivityManager.AppTask> tasks=am.getAppTasks();     //~@@01I~
        if (tasks!=null)                                           //~@@01I~
        {                                                          //~@@01I~
        	ActivityManager.AppTask task=tasks.get(0);             //~@@01R~
	        if (Dump.Y) Dump.println("Utils.moveAppToFront_From_LOLLIPOP request moveToFront");//~@@01I~
        	task.moveToFront();                                    //~@@01R~
        }                                                          //~@@01I~
    }                                                              //~@@01I~
	@SuppressWarnings("deprecation")                               //~va40I~
    //*******************************************************************//~@@01I~
    public static int getAppID_Under_LOLLIPOP(String Ppkgname)     //~@@01I~
    {                                                              //~@@01I~
    	int taskid=-1;                                             //~@@01I~
        ActivityManager am=(ActivityManager)AG.context.getSystemService(Context.ACTIVITY_SERVICE);//~@@01I~
        List<ActivityManager.RunningTaskInfo> tasks=am.getRunningTasks(Integer.MAX_VALUE);//~@@01I~
        for (ActivityManager.RunningTaskInfo task:tasks)           //~@@01I~
        {                                                          //~@@01I~
            String pn=task.baseActivity.getPackageName();          //~@@01I~
	        if (Dump.Y) Dump.println("Utils.getAppID_Under_LOLLIPOP id="+Integer.toHexString(task.id)+",pkgname="+pn);//~@@01I~
            if (pn.equals(Ppkgname))                               //~@@01I~
            {                                                      //~@@01I~
            	taskid=task.id;                                //~@@01I~
            }                                                      //~@@01I~
        }                                                          //~@@01I~
        if (Dump.Y) Dump.println("Utils.getAppID_UnderLOLLIPOP pkgname="+Ppkgname+",id="+taskid);//~@@01I~
        return taskid;                                             //~@@01I~
    }                                                              //~@@01I~
//***********                                                      //~vai3I~//~va11I~
    public static int[][] cloneArray2(int[][] Pfrom)               //~vai3R~//~va11I~
    {                                                              //~vai3I~//~va11I~
    	int[][] to=Pfrom.clone();                                  //~vai3I~//~va11I~
    	if (Dump.Y) Dump.println("cloneArray2 clone 2demension 1dimen clone array="+Utils.toString(to));//~vai3I~//~va11I~
    	int sz1=Pfrom.length;                                      //~vai3I~//~va11I~
        for (int ii=0;ii<sz1;ii++)                                 //~vai3I~//~va11I~
        	to[ii]=Pfrom[ii].clone();                              //~vai3I~//~va11I~
    	if (Dump.Y) Dump.println("cloneArray2 clone 2demension return array="+Utils.toString(to));//~vai3I~//~va11I~
        return to;                                                 //~vai3I~//~va11I~
    }                                                              //~vai3I~//~va11I~
//***********                                                      //~va11I~
    public static void copyArray2(int[][] Pfrom,int[][] Pto)    //~va11I~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("Utils.copyArray2");              //~va11I~
    	int sz1=Pfrom.length;                                      //~va11I~
        for (int ii=0;ii<sz1;ii++)                                 //~va11I~
        	System.arraycopy(Pfrom[ii],0,Pto[ii],0,Pfrom[ii].length);//~va11I~
    	if (Dump.Y) Dump.println("Utils.copyArray2 Pto"+Utils.toString(Pto));//~va11I~
    }                                                              //~va11I~
//***********                                                      //~va40I~
    public static Spanned fromHtml(String PtextHtml)               //~va40I~
    {                                                              //~va40I~
    	if (Dump.Y) Dump.println("Utils.fromHtml text="+PtextHtml); //~va40I~
        Spanned s;                                                 //~va40I~
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) //api24 android7:N//~va40I~
        {                                                          //~va40I~
        	int flag=FROM_HTML_MODE_LEGACY;                        //~va40I~
        	s=Html.fromHtml(PtextHtml,flag);                            //~va40I~
        }                                                          //~va40I~
        else                                                       //~va40I~
		    s=fromHtml_Under24(PtextHtml);                   //~va40I~
        return s;                                                  //~va40I~
    }                                                              //~va40I~
//*************************************************************    //~va40I~
	@SuppressWarnings("deprecation")                               //~va40I~
    private static Spanned fromHtml_Under24(String PtextHtml)      //~va40I~
    {                                                              //~va40I~
        return Html.fromHtml(PtextHtml);                           //~va40I~
    }                                                              //~va40I~
//***********                                                      //~vaa0I~
    public static Spanned fromHtmlImage(String PtextHtml)          //~vaa0I~
    {                                                              //~vaa0I~
    	if (Dump.Y) Dump.println("Utils.fromHtmlImage text="+PtextHtml);//~vaa0I~
        UImageGetter imageGetter=new UImageGetter();           //~vaa0I~
        Spanned s;                                                 //~vaa0I~
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) //api24 android7:N//~vaa0I~
        {                                                          //~vaa0I~
        	int flag=FROM_HTML_MODE_LEGACY;                        //~vaa0I~
        	s=Html.fromHtml(PtextHtml,flag,imageGetter,null/*tagHandler*/);//~vaa0I~
        }                                                          //~vaa0I~
        else                                                       //~vaa0I~
		    s=fromHtml_Under24(PtextHtml,imageGetter,null/*tagHandler*/);//~vaa0I~
        return s;                                                  //~vaa0I~
    }                                                              //~vaa0I~
//*************************************************************    //~vaa0I~
	@SuppressWarnings("deprecation")                               //~vaa0I~
    private static Spanned fromHtml_Under24(String PtextHtml,Html.ImageGetter PimageGetter,Html.TagHandler PtagH)//~vaa0I~
    {                                                              //~vaa0I~
        return Html.fromHtml(PtextHtml,PimageGetter,PtagH);              //~vaa0I~
    }                                                              //~vaa0I~
//*************************************************************    //~vaa0I~
	static class UImageGetter implements Html.ImageGetter                    //~vaa0I~
    {                                                              //~vaa0I~
    	@Override                                                  //~vaa0I~
        public Drawable getDrawable(String Psrc)                   //~vaa0I~
        {                                                          //~vaa0I~
			String pkg=AG.context.getPackageName();                //~@@01I~//~vaa0I~
        	int resID=AG.resource.getIdentifier(Psrc,"drawable",pkg);//~vaa0I~
    		if (Dump.Y) Dump.println("Utils.UImageGetter.getDrawable src="+Psrc+",pkg="+pkg+",resID="+Integer.toHexString(resID));//~vaa0I~
//          Drawable drawable=AG.resource.getDrawable(resID);      //~vaa0R~
            Drawable drawable= ContextCompat.getDrawable(AG.context,resID);//~vaa0I~
            int hh=drawable.getIntrinsicHeight();                  //~vaa0I~
            int ww=drawable.getIntrinsicWidth();                   //~vaa0I~
    		if (Dump.Y) Dump.println("Utils.UImageGetter.getDrawable intrinsic ww="+ww+",hh="+hh);//~vaa0I~
            drawable.setBounds(0,0,ww,hh);                         //~vaa0I~
            return drawable;                                       //~vaa0I~
        }                                                          //~vaa0I~
    }                                                              //~vaa0I~
//*************************************************************    //~vaafR~
//*background of text(small rect)                                  //~vaafR~
//*************************************************************    //~vaafR~
	public static void setSpanTextBG(TextView Pview, int Pcolor/*rgb only*/)//~vaafR~
    {                                                              //~vaafR~
    	int color=Pcolor|0x01000000;	//to                       //~vaafR~
    	String txt=(String)Pview.getText();                        //~vaafR~
    	String htmlText="<span style=\"background-color:#"         //~vaafR~
						+Integer.toHexString(Pcolor).substring(2)  //~vaafR~
						+"\">"                                     //~vaafR~
                        +txt                                       //~vaafR~
						+"</span>";                                //~vaafR~
    	if (Dump.Y) Dump.println("Utils.setSpanTextBG color="+Integer.toHexString(Pcolor)+",htmlText="+htmlText+",view="+Pview.toString());//~vaafR~
        Spanned strSpan=Utils.fromHtml(htmlText);                  //~vaafR~
    	Pview.setText(strSpan,TextView.BufferType.SPANNABLE);      //~vaafR~
    }                                                              //~vaafR~
//*************************************************************    //~vaafR~
//*background of text(small rect)                                  //~vaafR~
//*************************************************************    //~vaafR~
	public static void setSpanBG(TextView Pview,int Pcolor/*rgb only*/)//~vaafR~
    {                                                              //~vaafR~
    	if (Dump.Y) Dump.println("Utils.setSpanBG color="+Integer.toHexString(Pcolor)+",view="+Pview.toString());//~vaafR~
        SpannableString ss=new SpannableString(Pview.getText());   //~vaafR~
        BackgroundColorSpan bcs=new BackgroundColorSpan(Pcolor);   //~vaafR~
        ss.setSpan(bcs,0,ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//~vaafR~
        Pview.setText(ss);                                         //~vaafR~
    }                                                              //~vaafR~
//*************************************************************    //~vaafR~
//*background of text(small rect)                                  //~vaafR~
//*************************************************************    //~vaafR~
	public static void setTintBG(TextView Pview,int Pcolor/*rgb only*/)//~vaafR~
    {                                                              //~vaafR~
    	if (Dump.Y) Dump.println("Utils.setTintBG color="+Integer.toHexString(Pcolor)+",view="+Pview.toString());//~vaafR~
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) //~vaafR~
            Pview.getBackground().setTint(Pcolor);                 //~vaafR~
        else                                                       //~vaafR~
            Pview.setBackgroundColor(Pcolor);                      //~vaafR~
//          Pview.getBackground().setTint(new ColorDrawable(Pcolor));//~vaafR~
//      Pbtn.setBackground(new ColorDrawable(Pcolor));             //~vaafR~
//      Pbtn.getBackground().setTintMode(PorterDuff.Mode.SRC_OVER);//~vaafR~
    }                                                              //~vaafR~
//*************************************************************    //~vaafR~
	public static void clearTintBG(TextView Pview,Drawable Pdrawable)//~vaafR~
    {                                                              //~vaafR~
    	if (Dump.Y) Dump.println("Utils.clearTintBG view="+Pview.toString());//~vaafR~
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) //~vaafR~
        {                                                          //~vaafR~
//          Pview.setBackground(Pdrawable); //not work after setTint//~vaafI~
            Pview.getBackground().setTintList(null);               //~vaafR~
        }                                                          //~vaafR~
        else                                                       //~vaafR~
            Pview.setBackground(Pdrawable);                        //~vaafR~
    }                                                              //~vaafR~
//*************************************************************    //~vaafR~
	public static void setBtnBG(Button Pbtn, int Pcolor)           //~vaafR~
    {                                                              //~vaafR~
    	if (Dump.Y) Dump.println("Utils.setBtnBG color="+Integer.toHexString(Pcolor)+",view="+Pbtn.toString());//~vaafR~
//      Pbtn.getBackground().setColorFilter(Pcolor,PorterDuff.Mode.SRC_OVER);//~vaafR~
//      Pbtn.getBackground().setColorFilter(Pcolor, PorterDuff.Mode.MULTIPLY);//~vaafR~
//      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)//~vaafR~
//  	{                                                          //~vaafI~
//          ColorFilter cf=Pbtn.getBackground().getColorFilter();  //~vaafR~
//      	if (Dump.Y) Dump.println("Utils.setBtnBG cf="+Utils.toString(cf));//~vaafR~
//      }                                                          //~vaafR~
        Pbtn.getBackground().setColorFilter(new PorterDuffColorFilter(Pcolor, PorterDuff.Mode.SRC_ATOP));//~vaafR~
    }                                                              //~vaafR~
}//class Utils                                                //~1309R~//~v@@@R~
