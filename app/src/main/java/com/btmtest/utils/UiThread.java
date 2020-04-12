//*CID://+v@@@R~: update#= 133;                                    //~v@@@R~
//**********************************************************************//~1107I~
package com.btmtest.utils;                                         //~v@@@R~

import static com.btmtest.StaticVars.AG;                           //~v@21I~//+v@@@I~
                                                                   //+v@@@I~
import java.util.concurrent.CountDownLatch;

import android.app.Activity;

//**********************************************************************//~1107I~
public class UiThread                                         //~1214R~//~@@@@R~//~v@@@R~
{                                                                  //~0914I~
	public static final int LID_GMP=0;                             //~v106I~
	private static	CountDownLatch[] Slatch=new CountDownLatch[1]; //~v106I~
                                                                   //~1211I~
	private UiThreadI callback=null;                           //~1214R~//~@@@@R~//~v@@@R~
	private Object parm=null;                                      //~1214R~
//**********************************                               //~1A6AI~
	public interface UiThreadI                                     //~1A6AI~//~v@@@R~
	{                                                              //~1A6AI~
		void runOnUiThread(Object Pany);                           //~1A6AI~//~v@@@R~
	}//                                                            //~1A6AI~
//**********************************                               //~1211I~
//*                                                                //~1211R~
//**********************************                               //~1211I~
	public UiThread(UiThreadI Pcallback,Object Pparm)    //~1214R~ //~@@@@R~//~v@@@R~
    {                                                              //~1214I~
    	callback=Pcallback;                                        //~1214I~
        parm=Pparm;                                                //~1214R~
    }                                                              //~1214I~
//===============================================================================//~1309I~
//=on UI Thread;default NoSync                                     //~1309I~
//===============================================================================//~1309I~
    public static void runOnUiThread(UiThreadI Pcallback,Object Pparm)//~1309I~//~@@@@R~//~v@@@R~
    {                                                              //~1309I~
		runOnUiThreadNoSync(Pcallback,Pparm); //~1309I~            //~v@@@R~
    }                                                              //~1309I~
//===============================================================================//~1310I~
//=on UI Thread;with wait parameter                                //~1310I~
//===============================================================================//~1310I~
    public static void runOnUiThread(boolean Pwait,UiThreadI Pcallback,Object Pparm)//~1310I~//~@@@@R~//~v@@@R~
    {                                                              //~1310I~
    	if (Pwait)                                                 //~1310I~
			runOnUiThreadSync(Pcallback,Pparm);                    //~1310I~//~v@@@R~
        else                                                       //~1310I~
			runOnUiThreadNoSync(Pcallback,Pparm);                  //~1310I~//~v@@@R~
    }                                                              //~1310I~
//===============================================================================//~1309I~
//=on UI Thread;wait postback                                      //~1309I~
//===============================================================================//~1309I~
    public static void runOnUiThreadWait(UiThreadI Pcallback,Object Pparm)//~1309I~//~@@@@R~//~v@@@R~
    {                                                              //~1309I~
		runOnUiThreadSync(Pcallback,Pparm);   //~1309I~            //~v@@@R~
    }                                                              //~1309I~
//===============================================================================//~1309I~
//=on UI Thread;no wait postback                                   //~1309I~
//===============================================================================//~1309I~
    public static void runOnUiThreadXfer(UiThreadI Pcallback,Object Pparm)//~1309I~//~@@@@R~//~v@@@R~
    {                                                              //~1309I~
		runOnUiThreadNoSync(Pcallback,Pparm);                      //~1309I~//~v@@@R~
    }                                                              //~1309I~
//===============================================================================//~1A6gI~
    public static void runOnUiThreadXfer(Activity Pactivity,UiThreadI Pcallback,Object Pparm)//~1A6gI~//~v@@@R~
    {                                                              //~1A6gI~
		runOnUiThreadNoSync(Pactivity,Pcallback,Pparm);            //~1A6gI~//~v@@@R~
    }                                                              //~1A6gI~
//===============================================================================//~v@@@I~//~1212I~//~1214M~
//=no wait UI Thread end                                           //~1220I~
//===============================================================================//~1220I~
    private static void runOnUiThreadNoSync(UiThreadI Pcallback,Object Pparm)//~1214R~//~1221R~//~1309R~//~@@@@R~//~v@@@R~
    {                                                              //~1214I~
    	UiThread aut=new UiThread(Pcallback,Pparm);//~1214R~       //~@@@@R~//~v@@@R~
    	if (AG.isMainThread())                                     //~1214M~
        {                                                          //~1214M~
        	try                                                    //~1512I~
            {                                                      //~1512I~
            	if (Dump.T) Dump.println("runOnUiThreadNoSync mainthread before callback");//~@@@@I~//~1A6AR~//~v@@@R~
        		Pcallback.runOnUiThread(aut.parm);                 //~1512R~//~v@@@R~
            	if (Dump.T) Dump.println("runOnUiThreadNoSync mainthread after callback");//~@@@@I~//~1A6AR~//~v@@@R~
            }                                                      //~1512I~
            catch (Exception e)                                    //~1512I~
            {                                                      //~1512I~
                Dump.println(e,"runOnUiThreadNoSync(Mainthread) Exception");//~1512I~//~v@@@R~
            }                                                      //~1512I~
            return;                                                //~1214M~
        }                                                          //~1214M~
    	aut.runOnUiThreadNoSync2(aut);                  //~1214I~  //~v@@@R~
    }                                                              //~1214I~
//===============================================================================//~1A6gI~
    private static void runOnUiThreadNoSync(Activity Pactivity,UiThreadI Pcallback,Object Pparm)//~1A6gI~//~v@@@R~
    {                                                              //~1A6gI~
    	UiThread aut=new UiThread(Pcallback,Pparm);                //~1A6gI~//~v@@@R~
    	if (AG.isMainThread())                                     //~1A6gI~
        {                                                          //~1A6gI~
        	try                                                    //~1A6gI~
            {                                                      //~1A6gI~
            	if (Dump.T) Dump.println("runOnUiThreadNoSync mainthread before callback");//~1A6gI~//~1A6AR~//~v@@@R~
        		Pcallback.runOnUiThread(aut.parm);                 //~1A6gI~//~v@@@R~
            	if (Dump.T) Dump.println("runOnUiThreadNoSync mainthread after callback");//~1A6gI~//~1A6AR~//~v@@@R~
            }                                                      //~1A6gI~
            catch (Exception e)                                    //~1A6gI~
            {                                                      //~1A6gI~
                Dump.println(e,"runOnUiThreadNoSync(Mainthread) Exception");//~1A6gI~//~v@@@R~
            }                                                      //~1A6gI~
            return;                                                //~1A6gI~
        }                                                          //~1A6gI~
    	aut.runOnUiThreadNoSync2(Pactivity,aut);                   //~1A6gI~//~v@@@R~
    }                                                              //~1A6gI~
//===============================================================================//~v@@@I~//~1212I~
    private void runOnUiThreadNoSync2(UiThread Paut)//~1212I~//~1214R~//~1221R~//~@@@@R~//~v@@@R~
    {                                                              //~1212I~
        final UiThread aut=Paut;                                    //~1214I~//~@@@@R~//~v@@@R~
        AG.activity.runOnUiThread(              //execute on mainthread after posted//~1214I~//~v@@@R~
            new Runnable()                                         //~1214I~
            {                                                      //~1214I~
                @Override                                          //~1214I~
                public void run()                                  //~1214I~
                {                                                  //~1214I~
			        if (Dump.T) Dump.println("RunOnUiThreadNoSync2 start");           //~1214I~//~1308R~//~1506R~//~1A6AR~//~v@@@R~
                    if (aut.callback!=null)                        //~1214R~
                    {                                              //~1214I~
			        	if (Dump.T) Dump.println("RunOnUiThreadNoSync2 parm="+(aut.parm==null?"null":aut.parm.toString()));//~1506R~//~1A6AR~//~v@@@R~
        				try                                        //~1311I~
        				{                                          //~1311I~
	                    	aut.callback.runOnUiThread(aut.parm);      //~1214R~//~1311R~//~v@@@R~
                        }                                          //~1311I~
                        catch (Exception e)             //~1311I~
                        {                                          //~1311I~
                            Dump.println(e,"runOnUiThreadNoSync2 Exception");//~1311I~//~v@@@R~
                        }                                          //~1311I~
                    }                                              //~1214I~
			        if (Dump.T) Dump.println("RunOnUiThreadNoSync2 end");//~1506R~//~1A6AR~//~v@@@R~
                }                                                  //~1214I~
            }                                                      //~1214I~
                                  );                               //~1214I~
    }                                                              //~1212I~
//===============================================================================//~1A6gI~
    private void runOnUiThreadNoSync2(final Activity Pactivity,UiThread Paut)//~1A6gI~//~v@@@R~
    {                                                              //~1A6gI~
        final UiThread aut=Paut;                                   //~1A6gI~//~v@@@R~
        Pactivity.runOnUiThread(              //execute on mainthread after posted//~1A6gI~//~v@@@R~
            new Runnable()                                         //~1A6gI~
            {                                                      //~1A6gI~
                @Override                                          //~1A6gI~
                public void run()                                  //~1A6gI~
                {                                                  //~1A6gI~
			        if (Dump.T) Dump.println("RunOnUiThreadNoSync2  start activity="+Pactivity.toString());//~1A6gI~//~1A6AR~//~v@@@R~
                    if (aut.callback!=null)                        //~1A6gI~
                    {                                              //~1A6gI~
			        	if (Dump.T) Dump.println("RunOnUiThreadNoSync2 parm="+(aut.parm==null?"null":aut.parm.toString()));//~1A6gI~//~1A6AR~//~v@@@R~
        				try                                        //~1A6gI~
        				{                                          //~1A6gI~
	                    	aut.callback.runOnUiThread(aut.parm);  //~1A6gI~//~v@@@R~
                        }                                          //~1A6gI~
                        catch (Exception e)                        //~1A6gI~
                        {                                          //~1A6gI~
                            Dump.println(e,"runOnUiThreadNoSync2 Exception");//~1A6gI~//~v@@@R~
                        }                                          //~1A6gI~
                    }                                              //~1A6gI~
			        if (Dump.T) Dump.println("RunOnUiThreadNoSync2 end");//~1A6gI~//~1A6AR~//~v@@@R~
                }                                                  //~1A6gI~
            }                                                      //~1A6gI~
                                  );                               //~1A6gI~
    }                                                              //~1A6gI~
//===============================================================================//~1214I~
//= wait UI Thread end                                             //~1220I~
//===============================================================================//~1220I~
    private static void runOnUiThreadSync(UiThreadI Pcallback,Object Pparm)//~1214I~//~1309R~//~@@@@R~//~v@@@R~
    {                                                              //~1214I~
    	UiThread aut=new UiThread(Pcallback,Pparm);//~1214I~       //~@@@@R~//~v@@@R~
		if (Dump.T) Dump.println("RunOnUiThreadSync parm="+(Pparm==null?"null":Pparm.toString()));//~1506R~//~1A6AR~//~v@@@R~
    	if (AG.isMainThread())                                     //~1214I~
        {                                                          //~1214I~
        	try                                                    //~1512I~
            {                                                      //~1512I~
            	if (Dump.T) Dump.println("runOnUiThreadSync mainthread before callback");//~@@@@I~//~1A6AR~//~v@@@R~
	        	Pcallback.runOnUiThread(aut.parm);                 //~1512R~//~v@@@R~
            	if (Dump.T) Dump.println("runOnUiThreadSync mainthread after callback");//~@@@@I~//~1A6AR~//~v@@@R~
            }                                                      //~1512I~
            catch (Exception e)                                    //~1512I~
            {                                                      //~1512I~
                Dump.println(e,"runOnUiThreadSync(Mainthread) Exception");//~1512I~//~v@@@R~
            }                                                      //~1512I~
			if (Dump.T) Dump.println("RunOnUiThreadSync mainthread return parm="+(Pparm==null?"null":Pparm.toString()));//~@@@@I~//~1A6AR~//~v@@@R~
            return;                                                //~1214I~
        }                                                          //~1214I~
    	aut.runOnUiThreadSync2();                         //~1214I~//~v@@@R~
    }                                                              //~1214I~
//===============================================================================//~1214I~
    private void runOnUiThreadSync2()            //~1214I~         //~v@@@R~
    {                                                              //~1214I~
        if (Dump.T) Dump.println("AjagoUiThread:runOnUiThreadSync2");              //~1214I~//~1506R~//~1A6AR~//~v@@@R~
 //create handler is aval on Main thread only                      //~1214R~
		CountDownLatch latch=new CountDownLatch(1/*posted by counddown once*/);
        runOnUiThreadSub sub=new runOnUiThreadSub();                                    //~1214R~//~v@@@R~
        sub.init(this,latch);                                      //~1214I~
        try                                                        //~1214I~
        {                                                          //~1214I~
	        AG.activity.runOnUiThread(sub);                        //~1513I~//~v@@@R~
	        if (Dump.T) Dump.println("UiThread  wait");            //~@@@@I~//~1A6AR~//~v@@@R~
        	latch.await();   //subthread wakeup by dismiss         //~1214I~
	        if (Dump.T) Dump.println("UiThread posted wait");                     //~1214I~//~1506R~//~@@@@R~//~1A6AR~//~v@@@R~
        }                                                          //~1214I~
        catch (InterruptedException e)                             //~1214I~
        {                                                          //~1214I~
        	Dump.println(e,"Modal Thread Interrupted ");//~1214I~//~1308R~//~1311R~
        }                                                          //~1214I~
        if (Dump.T) Dump.println("AjagoUiThread:runOnUiThreadSync2 return");//~@@@@I~//~1A6AR~//~v@@@R~
    }                                                              //~1214I~
//*******************************                                  //~1214I~
// wait/pos by latch                                               //~v106I~
//===============================================================================//~v106I~
    public static void initlatch(int Platchid)                     //~v106I~
    {                                                              //~v106I~
		Slatch[Platchid]=new CountDownLatch(1/*posted by counddown once*/);//~v106I~
    }                                                              //~v106I~
    public static void wait(int Platchid)                          //~v106I~
    {                                                              //~v106I~
		if (Dump.T) Dump.println("UiThread Wait id="+Platchid);//~v106I~//~@@@@R~//~1A6AR~//~v@@@R~
    	if (AG.isMainThread())                                     //~v106I~
        	return;    //not supported                             //~v106I~
        try                                                        //~v106I~
        {                                                          //~v106I~
	        if (Dump.T) Dump.println("UiThread await");            //~@@@@I~//~1A6AR~//~v@@@R~
        	Slatch[Platchid].await();   //subthread wakeup by dismiss//~v106I~
	        if (Dump.T) Dump.println("UiThread await:posted"); //~v106I~//~@@@@R~//~1A6AR~//~v@@@R~
			Slatch[Platchid]=null;                                 //~v106I~
        }                                                          //~v106I~
        catch (InterruptedException e)                             //~v106I~
        {                                                          //~v106I~
        	Dump.println(e,"wait Thread Interrupted ");            //~v106I~
        }                                                          //~v106I~
    }                                                              //~v106I~
    public static void post(int Platchid)                          //~v106I~
    {                                                              //~v106I~
		if (Dump.T) Dump.println("UiThread post id="+Platchid);//~v106I~//~@@@@R~//~1A6AR~//~v@@@R~
        Slatch[Platchid].countDown();  //count exausted,post latch.await()//~v106I~
	    if (Dump.T) Dump.println("UiThread post");            //~v106I~//~@@@@R~//~1A6AR~//~v@@@R~
    }                                                              //~v106I~
//*******************************                                  //~1214I~
    class runOnUiThreadSub                                  //~1214I~//~v@@@R~
			implements Runnable                                    //~1214I~
    {                                                              //~1214I~
    	private UiThread aut;                                      //~@@@@R~//~v@@@R~
    	CountDownLatch latch;
    	public runOnUiThreadSub()                                  //~1214R~//~v@@@R~
        {                                                          //~1214I~
        }                                                          //~1214I~
    	public void init(UiThread Paut,CountDownLatch Platch) //~1214I~//~@@@@R~//~v@@@R~
        {                                                          //~1214I~
        	aut=Paut;                                              //~1214I~
        	latch=Platch;                                          //~1214I~
        }                                                          //~1214I~
        @Override                                                  //~1214I~
        public void run()                                          //~1214I~
        {                                                          //~1214I~
			if (Dump.T) Dump.println("before runOnUiTHreadSub");         //~1214I~//~1506R~//~1A6AR~
        	try                                                    //~1311I~
        	{                                                      //~1311I~
		        if (Dump.T) Dump.println("UiThreadSub before callback");//~@@@@I~//~1A6AR~//~v@@@R~
            	aut.callback.runOnUiThread(aut.parm);                  //~1214I~//~1311R~//~v@@@R~
		        if (Dump.T) Dump.println("UiThreadSub after callback");//~@@@@I~//~1A6AR~//~v@@@R~
            }                                                      //~1311I~
        	catch (Exception e)                         //~1311I~
        	{                                                      //~1311I~
        		Dump.println(e,"runOnUiThreadcSub(Sync) Exception");//~1311I~//~v@@@R~
        	}                                                      //~1311I~
			if (Dump.T) Dump.println("runOnUiThreadSub contdown"); //~@@@@R~//~1A6AR~//~v@@@R~
            latch.countDown();  //count exausted,post latch.await()//~1214I~
			if (Dump.T) Dump.println("runOnUiThreadSub return");   //~@@@@I~//~1A6AR~//~v@@@R~
        }                                                          //~1214I~
    }                                                              //~1214I~
}//class UiThread                                             //~1214R~//~@@@@R~//~v@@@R~
