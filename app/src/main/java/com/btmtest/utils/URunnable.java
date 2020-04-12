//*CID://+@@@@R~: update#= 151;                                    //~@@@@R~
//**********************************************************************//~v106I~
//*run on UIThread after delay time specified                      //~1A6tR~
//*run on subThread(setRunFuncSubthread())                         //~@@@@I~
//**********************************************************************//~1107I~
package com.btmtest.utils;                                //~1107R~  //~1108R~//~1109R~//~@@@@R~//~1A6tR~

import android.app.ProgressDialog;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@@@@I~
//**********************************************************************//~1107I~
public class URunnable                                             //~@@@@R~
{                                                                  //~0914I~
	private int delay;                                              //~1A6tI~
	private URunnableI callback;                           //~1214R~//~@@@@R~
	private Object parmObject;                                      //~1214R~//~@@@@R~
	private int parmInt;                                           //~1A6tI~
    private boolean swMainThread,swFromSubthreadRunnable;                  //~@@@@R~
    private boolean swRunOnSubthread;                              //~v101I~
//**********************************                               //~1211I~
	public URunnable(URunnableI Pcallback,int Pdeley,Object Pparmobject,int Pparmint)    //~1214R~//~@@@@R~
    {                                                              //~1214I~
    	callback=Pcallback;                                        //~1214I~
        parmObject=Pparmobject;                                                //~1214R~//~@@@@R~
        parmInt=Pparmint;                                         //~@@@@I~
        delay=Pdeley;                                              //~@@@@I~
        swMainThread=AG.isMainThread();                            //~@@@@I~
    }                                                              //~1214I~
	public URunnable()                                             //~1A6eI~
    {                                                              //~1A6eI~
    }                                                              //~1A6eI~
//===============================================================================//~1A6tI~
	public interface URunnableI                                    //~1A6tI~
	{                                                              //~1A6tI~
		public void URunnableCB(Object parmObj,int parmInt);       //~1A6tI~
	}                                                              //~1A6tI~
//===============================================================================//~@@@@I~
//=request callback on UiThread after some delay on subthread      //~@@@@R~
//===============================================================================//~@@@@I~
    public static void setRunFunc(URunnableI Pcallback,int Pdelay,Object Pparmobj,int Pparmint)//~@@@@R~
    {                                                              //~@@@@I~
		URunnable uh=new URunnable(Pcallback,Pdelay,Pparmobj,Pparmint);//~@@@@R~
        CallbackRunnable cbrun=uh.new CallbackRunnable(uh);                            //~@@@@I~
		if (Dump.Y) Dump.println("setRunFunc start delay="+Pdelay);//~@@@@R~
    	if (uh.swMainThread)                                       //~@@@@I~
        {                                                          //~@@@@I~
        	new Thread(cbrun).start();  //callback on UiThread after sleep on subthread//~@@@@I~
        }                                                          //~@@@@I~
        else	//post from subthread                              //~@@@@I~
        {                                                          //~@@@@I~
        	if (Pdelay!=0)                                         //~@@@@I~
            {                                                      //~1A6tI~
	        	Utils.sleep((long)Pdelay);                         //~@@@@I~
                uh.delay=0; //do not sleep on UI thread            //~1A6tI~
            }                                                      //~1A6tI~
	        AG.activity.runOnUiThread(cbrun);                      //~@@@@I~
        }                                                          //~@@@@I~
		if (Dump.Y) Dump.println("setRunFunc return");             //~@@@@R~
    }                                                              //~@@@@I~
//===============================================================================//~@@@@I~
//=request callback on UiThread without delay                      //~@@@@I~
//=direct call if on mainthread                                    //~@@@@I~
//===============================================================================//~@@@@I~
    public static void setRunFuncDirect(URunnableI Pcallback,Object Pparmobj,int Pparmint)//~@@@@I~
    {                                                              //~@@@@I~
		if (Dump.Y) Dump.println("setRunFuncDirect");              //~@@@@I~
    	if (AG.isMainThread())                                       //~@@@@I~
        {                                                          //~@@@@I~
        	Pcallback.URunnableCB(Pparmobj,Pparmint);                  //~@@@@I~//~1A6tR~
        }                                                          //~@@@@I~
        else	//call from subthread                              //~@@@@I~
        {                                                          //~@@@@I~
			URunnable uh=new URunnable(Pcallback,0/*delay*/,Pparmobj,Pparmint);//~@@@@I~
	        CallbackRunnable cbrun=uh.new CallbackRunnable(uh);    //~@@@@I~
	        AG.activity.runOnUiThread(cbrun);                      //~@@@@I~
        }                                                          //~@@@@I~
		if (Dump.Y) Dump.println("setRunFuncDirect return");       //~@@@@R~
    }                                                              //~@@@@I~
//===============================================================================//~v101I~
//=run on subthread                                                //~v101I~
//===============================================================================//~v101I~
    public static void setRunFuncSubthread(URunnableI Pcallback,int Pdelay,Object Pparmobj,int Pparmint)//~v101I~
    {                                                              //~v101I~
		if (Dump.Y) Dump.println("setRunFuncSubThread");           //~v101I~
    	if (!AG.isMainThread())   //alread not UI thread           //~v101I~
        {                                                          //~v101I~
        	if (Pdelay!=0)                                         //~v101I~
				Utils.sleep((long)Pdelay);                         //~v101I~
        	Pcallback.URunnableCB(Pparmobj,Pparmint);                  //~v101I~//~1A6tR~
        }                                                          //~v101I~
        else	//call from mainthread                             //~v101I~
        {                                                          //~v101I~
			URunnable uh=new URunnable(Pcallback,Pdelay,Pparmobj,Pparmint);//~v101I~
            uh.swRunOnSubthread=true;                              //~v101I~
	        CallbackRunnable cbrun=uh.new CallbackRunnable(uh);    //~v101I~
	        (new Thread(cbrun)).start();                           //~v101I~
        }                                                          //~v101I~
		if (Dump.Y) Dump.println("setRunFuncSubthread return");    //~v101I~
    }                                                              //~v101I~
//************************************************************     //~@@@@I~
    class CallbackRunnable	implements Runnable                    //~@@@@I~
    {                                                              //~@@@@I~
    	URunnable uh;                                              //~@@@@R~
    	public CallbackRunnable(URunnable Puh)                     //~@@@@R~
        {                                                          //~@@@@I~
        	uh=Puh;                                                //~@@@@I~
        }                                                          //~@@@@I~
        @Override                                                  //~@@@@I~
        public void run()                                          //~@@@@I~
        {                                                          //~@@@@I~
			if (Dump.Y) Dump.println("URunnable CallbackRunnable start run");//~@@@@R~
        	try                                                    //~@@@@I~
        	{                                                      //~@@@@I~
    			if (AG.isMainThread()) //scheduled on Mainthread by RunOnUiThread//~1A6tI~
                {                                                  //~1A6tI~
                    if (Dump.Y) Dump.println("CallbackRunnable:MainThread(RunOnUiThread");//~1A6tI~
            		uh.callback.URunnableCB(uh.parmObject,uh.parmInt);//~1A6tI~
                }                                                  //~1A6tI~
                else                                               //~1A6tI~
                {                                                  //~@@@@I~
        			if (uh.delay!=0)                               //~@@@@I~
                    {                                              //~1A6tI~
			        	Utils.sleep((long)uh.delay);               //~@@@@I~
                    	if (Dump.Y) Dump.println("CallbackRunnable delay" +uh.delay);//~1A6tI~
                        uh.delay=0;	                               //~1A6tI~
                    }                                              //~1A6tI~
                    if (swRunOnSubthread)                          //~1A6tI~
                    {                                              //~1A6tI~
                    	if (Dump.Y) Dump.println("CallbackRunnable RunonSubthread, call CB");//~1A6tI~
	            		uh.callback.URunnableCB(uh.parmObject,uh.parmInt);//~1A6tI~
                    }                                              //~1A6tI~
                    else                                           //~1A6tI~
                    {                                              //~1A6tI~
			        	CallbackRunnable cbrun=uh.new CallbackRunnable(uh);  //new runnable//~@@@@I~//~1A6tI~
	        			AG.activity.runOnUiThread(cbrun);              //~@@@@I~//~1A6tR~
                    	if (Dump.Y) Dump.println("kick UI thread from subthread after sleep");//~@@@@I~//~1A6tR~
                    }                                              //~1A6tI~
                }                                                  //~@@@@I~
            }                                                      //~@@@@I~
        	catch (Exception e)                                    //~@@@@I~
        	{                                                      //~@@@@I~
        		Dump.println(e,"URunnable:CallbackUiThread");      //~@@@@R~
        	}                                                      //~@@@@I~
			if (Dump.Y) Dump.println("URunnable:CallbackRunnable end run");//~@@@@R~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
//******************************************************************//~@@@@I~
    public static void dismissDialog(URunnableData Pdata)          //~1A6tI~//~@@@@I~
    {                                                              //~1A6tI~//~@@@@I~
    	if (Pdata==null)                                           //~1A6tI~//~@@@@I~
        	return;                                                //~1A6tI~//~@@@@I~
    	android.app.Dialog dlg=Pdata.progressDialog;       //~1A6tI~//~@@@@I~
		dismissDialog(dlg);                                        //~1A6tI~//~@@@@I~
    }                                                              //~1A6tI~//~@@@@I~
    public static void dismissDialog(android.app.Dialog Pdialog)   //~1A6eM~//~@@@@I~
    {                                                              //~1A6eM~//~@@@@I~
    	if (Pdialog==null)                                         //~1A6eM~//~@@@@I~
        	return;                                                //~1A6eM~//~@@@@I~
        if (Dump.Y) Dump.println("URunnable:DismissDialogI:runOnUiThread="+Pdialog.toString());//~1A6eR~//+@@@@R~
    	DismissDialogI uithreadi=new URunnable().new DismissDialogI();   //~1A6eI~//~@@@@I~
        UiThread.runOnUiThread(uithreadi,Pdialog);                 //~1A6eM~//~@@@@I~
    }                                                              //~1A6eM~//~@@@@I~
    class DismissDialogI implements UiThread.UiThreadI                      //~1A6eI~//~@@@@I~
    {                                                              //~1A6eM~//~@@@@I~
    	@Override                                                  //~1A6eM~//~@@@@I~
		public void runOnUiThread(Object Pparm)                    //~1A6eM~//~@@@@I~
        {                                                          //~1A6eM~//~@@@@I~
        	android.app.Dialog dialog=(android.app.Dialog)Pparm;   //~1A6eM~//~@@@@I~
        	if (Dump.Y) Dump.println("URunnable:DismissDialogI:runOnUiThread="+dialog.toString());//~1A6eR~//~@@@@I~
	    	if (dialog.isShowing())                                //~1A6eM~//~@@@@I~
	        	dialog.dismiss();                                  //~1A6eM~//~@@@@I~
        }                                                          //~1A6eM~//~@@@@I~
	}                                                              //~1A6eM~//~@@@@I~
//****************************************                         //~1A6eI~//~@@@@I~
//  public static ProgressDialog simpleProgressDialogShow(int Ptitleid,String Pmsg,boolean Pindeterminate,boolean Pcancelable)//~1A6eI~//~1A6tR~//~@@@@I~
    public static URunnableData simpleProgressDialogShow(int Ptitleid,String Pmsg,boolean Pindeterminate,boolean Pcancelable)//~1A6tI~//~@@@@I~
    {                                                              //~1A6eI~//~@@@@I~
//      ProgressDialog dlg=new ProgressDialog(AG.context);         //~1A6eI~//~1A6tR~//~@@@@I~
        URunnableData dlg=new URunnable().new URunnableData();	//asynchronously filled at uithread execution//~1A6tI~//~@@@@I~
        if (Dump.Y) Dump.println("URunnable:simpleProgressDialogShow msg="+Pmsg+",dialog="+dlg.toString());//~1A6eI~//~@@@@I~
	    SimpleProgressDialogShowI uithreadi=new URunnable().new SimpleProgressDialogShowI(Ptitleid,Pmsg,Pindeterminate,Pcancelable);//~1A6eI~//~@@@@I~
        UiThread.runOnUiThread(uithreadi,dlg);                     //~1A6eI~//~@@@@I~
        return dlg;                                                //~1A6eI~//~@@@@I~
    }                                                              //~1A6eI~//~@@@@I~
    class SimpleProgressDialogShowI implements UiThread.UiThreadI           //~1A6eI~//~@@@@I~
    {                                                              //~1A6eI~//~@@@@I~
        int titleid;                                               //~1A6eI~//~@@@@I~
        String msg;                                                //~1A6eI~//~@@@@I~
		boolean indeterminate,cancelable;                          //~1A6eI~//~@@@@I~
        URunnableData data;                                        //~1A6tI~//~@@@@I~
	    public SimpleProgressDialogShowI(int Ptitleid,String Pmsg,boolean Pindeterminate,boolean Pcancelable)//~1A6eI~//~@@@@I~
        {                                                          //~1A6eI~//~@@@@I~
        	titleid=Ptitleid; msg=Pmsg; indeterminate=Pindeterminate; cancelable=Pcancelable;//~1A6eI~//~@@@@I~
        }                                                          //~1A6eI~//~@@@@I~
        @Override                                                  //~1A6eI~//~@@@@I~
		public void runOnUiThread(Object Pparm)                                //~1A6eI~//~@@@@I~
        {                                                          //~1A6eI~//~@@@@I~
        	if (Dump.Y) Dump.println("URunnable:SimpleProgressDialogShow:runOnUiThread");//~1A6eR~//~@@@@I~
//          ProgressDialog dlg=(ProgressDialog)Pparm;              //~1A6eI~//~1A6tR~//~@@@@I~
            data=(URunnableData)Pparm;                             //~1A6tI~//~@@@@I~
			ProgressDialog dlg=new ProgressDialog(AG.context);     //~1A6tR~//~@@@@I~
			data.progressDialog=dlg;                               //~1A6tI~//~@@@@I~
            dlg.setTitle(AG.resource.getString(titleid));           //~1A6eI~//~@@@@I~
            dlg.setMessage(msg);                                   //~1A6eI~//~@@@@I~
            dlg.setIndeterminate(indeterminate);                   //~1A6eI~//~@@@@I~
            dlg.setCancelable(cancelable);                         //~1A6eI~//~@@@@I~
	        dlg.show();                                           //~1A6eI~//~@@@@I~
        }                                                          //~1A6eI~//~@@@@I~
	}                                                              //~1A6eI~//~@@@@I~
//****************************************                         //~@@@@I~
    public class URunnableData                                         //~1A6tR~//~@@@@I~
    {                                                              //~@@@@I~
        public ProgressDialog progressDialog;                                           //~1A6tI~//~@@@@I~
        public URunnableData()                                         //~1A6tR~//~@@@@I~
        {                                                          //~@@@@I~
        }                                                          //~@@@@I~
        public URunnableData(ProgressDialog Pdlg)                      //~1A6tI~//~@@@@I~
        {                                                              //~1A6tI~//~@@@@I~
            progressDialog=Pdlg;                                                 //~1A6tI~//~@@@@I~
        }                                                              //~1A6tI~//~@@@@I~
    }//class                                                       //~@@@@I~
}//class URunnable                                            //~1214R~//~@@@@R~
