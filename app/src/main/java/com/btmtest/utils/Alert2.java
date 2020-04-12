//*CID://+DATER~: update#= 152;                                    //~@003R~//~0411R~
//**********************************************************************//~1107I~
//*support dialog shift vertically                                 //+0411I~
//**********************************************************************//+0411I~
package com.btmtest.utils;                                         //~1107R~  //~1108R~//~1109R~//~@@@@R~//~v@@@R~

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@003I~

import android.app.DialogFragment;

//**********************************************************************//~1107I~
public class Alert2 extends DialogFragment                          //~v@@@R~//~@003R~
        implements Alert.AlertI                                    //~@003I~
{                                                                  //~0914I~
	protected AlertI svCallback;                                   //~@003I~
    private Object parmObject;                                     //~@003I~
    private int parmInt;                                           //~@003I~
    public int ctrShift,unitShift;                                 //~@003R~
//**********************************                               //~v@@@I~
	public interface AlertI                                       //~v@@@I~//~@003R~
	{                                                              //~v@@@I~
		public int alertButtonAction(int Pbuttonid,int PparmInt,Object PparmObject);       //~v@@@R~//~@003R~
	}//interface AjagoAlertI                                       //~v@@@I~
//**********************************                               //~v@@@I~
	public Alert2() 	//called when recreate fragment                //~v@@@I~//~@003R~
	{                                                              //~v@@@I~
	}//                                                            //~v@@@R~
//**********************************                               //~@003I~
	public Alert2 setParm(int PparmInt,Object PparmObject)         //~@003I~
	{                                                              //~@003I~
    	parmInt=PparmInt;                                          //~@003I~
    	parmObject=PparmObject;                                    //~@003I~
        return this;       //like as new Alert2().setParm(int,obj).showlert(..)                                        //~@003I~
	}//                                                            //~@003I~
//**********************************                               //~@003I~
	public void setShift(int Pctr,int PunitShift)                  //~@003R~
	{                                                              //~@003I~
    	ctrShift=Pctr;                                             //~@003I~
    	unitShift=PunitShift;                                      //~@003I~
        if (Dump.Y) Dump.println("Alert2:setShift ctr="+ctrShift+",unit="+unitShift);//~@003R~
	}//                                                            //~@003I~
//===============================================================================//~v@@@I~
	public void showAlert(String Ptitle,String Ptext,int Pflag,AlertI Pcallback)//~v@@@R~//~@003R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Alert2:showAlert title="+Ptitle+",text="+Ptext+",flag="+Integer.toHexString(Pflag));//~@003R~
        svCallback=Pcallback;                                      //~@003I~
        Alert.showAlert(Ptitle,Ptext,Pflag,this);                    //~@003I~
    }                                                              //~v@@@I~
//===============================================================================//~@003I~
	public void  showAlert(int Ptitleid,String Ptext,int Pflag,AlertI Pcallback)//~@003R~
    {                                                              //~@003I~
    	String title=Utils.getStr(Ptitleid);                       //~@003I~
		showAlert(title,Ptext,Pflag,Pcallback);                    //~@003I~
    }                                                              //~@003I~
//===============================================================================//~v@@@I~//~1212I~//~v@@@M~
    public void showAlert(int Ptitleid,int Ptextid,int Pflag,AlertI Pcallback)//~v1B9I~//~1A89I~//~v@@@R~//~@003R~
    {                                                              //~v1B9I~//~1A89I~//~v@@@M~
    //***********                                                  //~v1B9I~//~1A89I~//~v@@@M~
    	String title=Utils.getStr(Ptitleid);              //~v1B9I~//~1A89I~//~v@@@R~
    	String text=Utils.getStr(Ptextid);                //~v1B9I~//~1A89I~//~v@@@R~
	    showAlert(title,text,Pflag,Pcallback);             //~v1B9I~//~1A89I~//~v@@@R~
    }                                                              //~v1B9I~//~1A89I~//~v@@@M~
    //***************************************                      //~@003I~
    @Override   //AlertI                                           //~@003I~
    public int alertButtonAction(int Pbuttonid,int Pitempos)       //~@003I~
    {                                                              //~@003I~
        if (Dump.Y) Dump.println("Alert2.alertButtonAction buttonid="+Integer.toHexString(Pbuttonid));//~@003I~
        svCallback.alertButtonAction(Pbuttonid,parmInt,parmObject);                  //~@003I~
        return 0;                                                  //~@003I~
    }                                                              //~@003I~
}//class Alert                                                //~1211R~//~@@@@R~
