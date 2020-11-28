//*CID://+va40R~: update#= 161;                                    //~va40R~
//**********************************************************************//~1107I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//@003:20181103 dismiss alert dialog when interrupted bt other appl//~@003R~
//**********************************************************************//~1107I~
package com.btmtest.utils;                                         //~1107R~  //~1108R~//~1109R~//~@@@@R~//~v@@@R~

import com.btmtest.R;                                                 //~@@@@R~//~v@@@R~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@003I~

//import android.app.DialogFragment;                               //~va40R~
import android.support.v4.app.DialogFragment;                      //~va40I~
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.os.Bundle;                                          //~v@@@I~

import java.util.Arrays;

//~v@@@I~
//**********************************************************************//~1107I~
public class Alert extends DialogFragment                          //~v@@@R~
{                                                                  //~0914I~
	public static final int BUTTON_POSITIVE =0x01;                      //~1211I~//~1212R~
	public static final int BUTTON_NEGATIVE =0x02;                      //~1211I~//~1212R~
	public static final int BUTTON_CLOSE    =0x04;                         //~1211I~//~1212R~
	public static final int EXIT            =0x40;                 //~1212I~
	public static final int NO_TITLE        =0x80;                 //~1212I~
	
//  private AlertI callback;                                  //~1425R~//~@@@@R~//~@003R~
	protected AlertI svCallback;                                   //~@003R~
	private View selectedView;                                     //~1425R~
    private ListView listview;                                     //~1425R~
    private int flag;                                              //~1425R~
    private String title,text;                                     //~@@@2I~
    private boolean swTheme=false;                                 //~@003R~
//    private Object parmObject;                                   //~@003R~
    protected Dialog androidDialog;                                //~@003I~
    private int ctrShift,unitShift;                                //~@003R~
    private int[] labelIDs;                                        //~0411I~
//**********************************                               //~v@@@I~
	public interface AlertI                                        //~v@@@I~
	{                                                              //~v@@@I~
		public int alertButtonAction(int Pbuttonid,int Prc);       //~v@@@R~
	}//interface AjagoAlertI                                       //~v@@@I~
//**********************************                               //~v@@@I~
	public Alert() 	//called when recreate fragment                //~v@@@I~
	{                                                              //~v@@@I~
	}//                                                            //~v@@@R~
//**********************************                               //~v@@@I~
	private static Alert createAlert(String Ptitle,String Ptext,int Pflag,AlertI Pcallback)//~v@@@R~//~@003R~
    {                                                              //~v@@@I~
    	Alert dlg=new Alert();                                     //~v@@@I~
    	Bundle b=new Bundle();                                     //~v@@@I~
        b.putString("title",Ptitle);                               //~v@@@I~
        b.putString("text",Ptext);                                 //~v@@@I~
        b.putInt("flag",Pflag);                                    //~v@@@I~
        b.putString("title",Ptitle);                               //~v@@@I~
        if (Dump.Y) Dump.println("Alert:createAlert bundle="+b.toString());//~@003I~
        dlg.setArguments(b);                                       //~v@@@R~
//      if (Pcallback!=null)                                       //~v@@@I~//~@003R~
//          dlg.setTargetFragment((Fragment)Pcallback,0);          //~v@@@R~//~@003R~
        dlg.svCallback=Pcallback;                                  //~@003R~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
//===============================================================================//~@003I~
	private static void getParmAlert2(AlertI Pcallback,Alert Pdlg) //~@003R~
    {                                                              //~@003I~
    	int ctrShift;
        if (Pcallback instanceof Alert2)                           //~@003I~
        {                                                          //~@003I~
        	Pdlg.ctrShift=((Alert2)Pcallback).ctrShift;            //~@003R~
        	Pdlg.unitShift=((Alert2)Pcallback).unitShift;          //~@003I~
        }                                                          //~@003I~
        else                                                       //~@003I~
        {                                                          //~@003I~
        	Pdlg.ctrShift=0;                                       //~@003R~
        	Pdlg.unitShift=0;                                      //~@003I~
        }                                                          //~@003I~
        if (Dump.Y) Dump.println("getParmAlert2 ctr="+Pdlg.ctrShift+",unit="+Pdlg.unitShift);//~@003R~
    }                                                              //~@003I~
//===============================================================================//~v@@@I~
	public static void  showAlert(String Ptitle,String Ptext,int Pflag,AlertI Pcallback)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Alert:showAlert title="+Ptitle+",text="+Ptext+",flag="+Integer.toHexString(Pflag));//~@003I~
        Alert dlg=createAlert(Ptitle,Ptext,Pflag,Pcallback);       //~v@@@R~
        getParmAlert2(Pcallback,dlg);                              //~@003R~
        dlg.show(AG.fragmentManager,Alert.class.getSimpleName());  //~v@@@R~
    }                                                              //~v@@@I~
//===============================================================================//~@003I~
	public static void  showAlert(int Ptitleid,String Ptext,int Pflag,AlertI Pcallback)//~@003I~
    {                                                              //~@003I~
//  	String title=Utils.getStr(Ptitleid);                       //~@003R~
    	String title;                                              //~@003I~
        if (Ptitleid==-1)                                          //~@003I~
            title="";                                              //~@003I~
        else                                                       //~@003I~
        if (Ptitleid==0)                                           //~@003I~
            title=null;	//app_name                                 //~@003I~
        else                                                       //~@003I~
	    	title=Utils.getStr(Ptitleid);                          //~@003I~
        Alert dlg=createAlert(title,Ptext,Pflag,Pcallback);        //~@003I~
        getParmAlert2(Pcallback,dlg);                              //~@003R~
        dlg.show(AG.fragmentManager,Alert.class.getSimpleName());  //~@003I~
    }                                                              //~@003I~
//===============================================================================//~0411I~
	public static void  showAlert(int Ptitleid,String Ptext,int Pflag,AlertI Pcallback,int[] PlabelIDs)//~0411I~
    {                                                              //~0411I~
    	String title;                                              //~0411I~
        if (Ptitleid==-1)                                          //~0411I~
            title="";                                              //~0411I~
        else                                                       //~0411I~
        if (Ptitleid==0)                                           //~0411I~
            title=null;	//app_name                                 //~0411I~
        else                                                       //~0411I~
	    	title=Utils.getStr(Ptitleid);                          //~0411I~
        Alert dlg=createAlert(title,Ptext,Pflag,Pcallback);        //~0411I~
        dlg.labelIDs=PlabelIDs;                                    //~0411I~
        getParmAlert2(Pcallback,dlg);                              //~0411I~
        dlg.show(AG.fragmentManager,Alert.class.getSimpleName());  //~0411I~
    }                                                              //~0411I~
//===============================================================================//~v@@@I~//~1212I~//~v@@@M~
    public static void showAlert(int Ptitleid,int Ptextid,int Pflag,AlertI Pcallback)//~v1B9I~//~1A89I~//~v@@@R~
    {                                                              //~v1B9I~//~1A89I~//~v@@@M~
    //***********                                                  //~v1B9I~//~1A89I~//~v@@@M~
//  	String title=Utils.getStr(Ptitleid);              //~v1B9I~//~1A89I~//~v@@@R~//~@003R~
    	String text=Utils.getStr(Ptextid);                //~v1B9I~//~1A89I~//~v@@@R~
//      showAlert(title,text,Pflag,Pcallback);             //~v1B9I~//~1A89I~//~v@@@R~//~@003R~
        showAlert(Ptitleid,text,Pflag,Pcallback);                  //~@003I~
    }                                                              //~v1B9I~//~1A89I~//~v@@@M~
//===============================================================================//~0411I~
    public static void showAlert(int Ptitleid,int Ptextid,int Pflag,AlertI Pcallback,int[] PlabelIDs)//~0411I~
    {                                                              //~0411I~
    //***********                                                  //~0411I~
    	String text=Utils.getStr(Ptextid);                         //~0411I~
        showAlert(Ptitleid,text,Pflag,Pcallback,PlabelIDs);        //~0411R~
    }                                                              //~0411I~
//===============================================================================//~v@@@I~
	public static void  showMessage(String Ptitle,String Ptext)    //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Alert:showMessage:"+Ptext);      //~v@@@I~
        Alert dlg=createAlert(Ptitle,Ptext,BUTTON_CLOSE,null);     //~v@@@I~
        dlg.show(AG.fragmentManager,Alert.class.getSimpleName());  //~v@@@I~
    }                                                              //~v@@@I~
//===============================================================================//~v@@@I~
    public static void showMessage(int Ptitleid,int Ptextid)       //~v@@@I~
    {                                                              //~v@@@I~
    //***********                                                  //~v@@@I~
//  	String title=Utils.getStr(Ptitleid);                       //~v@@@R~//~@003R~
    	String text=Utils.getStr(Ptextid);                         //~v@@@R~
//      showMessage(title,text);                                   //~v@@@I~//~@003R~
        showMessage(Ptitleid,text);                                //~@003I~
    }                                                              //~v@@@I~
//===============================================================================//~@003I~
    public static void showMessage(int Ptitleid,String Ptext)      //~@003I~
    {                                                              //~@003I~
    //***********                                                  //~@003I~
    	String title;                                              //~@003I~
        if (Ptitleid==-1)                                          //~@003I~
            title="";                                              //~@003I~
        else                                                       //~@003I~
        if (Ptitleid==0)                                           //~@003I~
            title=null;	//app_name                                 //~@003R~
        else                                                       //~@003I~
	    	title=Utils.getStr(Ptitleid);                          //~@003R~
	    showMessage(title,Ptext);                                  //~@003I~
    }                                                              //~@003I~
//**********************************                               //~1211I~
	@Override
    public Dialog onCreateDialog(Bundle Pbundle)                    //~v@@@R~
    {                                                              //~1211I~
        Bundle b=getArguments();                                   //~v@@@I~
        String title=b.getString("title",null);                    //~v@@@I~
        String text=b.getString("text","");                        //~v@@@I~
        int flag=b.getInt("flag",0);                               //~v@@@I~
                                                                   //~v@@@I~
    	AlertDialog.Builder builder;                               //~@003I~
        if (Dump.Y) Dump.println("Alert:onCreateDialog swTheme="+swTheme);//~@003I~
        if (swTheme)                                               //~@003I~
    		builder=new AlertDialog.Builder(new ContextThemeWrapper(AG.context,R.style.AlertDialogThemeCustom));//~@003I~
        else                                                       //~@003I~
    		builder=new AlertDialog.Builder(AG.context);//~1211I~  //~@003R~
    	if (title!=null)                                           //~v@@@I~
    		builder.setTitle(title);                               //~v@@@I~
        else                                                       //~v@@@I~
	    	builder.setTitle(Utils.getStr(R.string.app_name));     //~v@@@I~
    	builder.setMessage(text);                                  //~v@@@I~
        setButton(builder,flag);                                   //~v@@@I~
    	Dialog dlg=builder.create();                                                  //~v@@@I~//~1211I~//~v@@@M~
    	shiftDialog(dlg);                                          //~@003I~
        if ((flag & NO_TITLE)!=0)                                  //~v@@@I~
	        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);     //~v@@@I~
        dlg.setCanceledOnTouchOutside(false);                      //~v@@@I~
        androidDialog=dlg;                                         //~@003I~
    	return dlg;                                                //~v@@@R~
    }                                                              //~1211I~
//**********************************                               //~@003I~
    private void shiftDialog(Dialog Pdlg)                               //~@003I~
    {                                                              //~@003I~
        if (Dump.Y) Dump.println("Alert:shiftDialog ctr="+ctrShift+",unit="+unitShift);//~@003R~
        if (ctrShift !=0)                                          //~@003I~
        	UView.shiftDialog(Pdlg,ctrShift,unitShift);            //~@003R~
    }                                                              //~@003I~
//**********************************                               //~v@@@I~
//    @Override                                                    //~v@@@R~
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle Pbundle)//~v@@@R~
//    {                                                            //~v@@@R~
//    }                                                            //~v@@@R~
    //******************************************                   //~@003I~
    @Override                                                      //~@003I~
    public void onResume() {                                       //~@003I~
        super.onResume();                                          //~@003I~
        if (Dump.Y) Dump.println("Alert:onResume");                //~@003I~
    }                                                              //~@003I~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Alert onDestroy dlg this="+this.toString());           //~v@@@I~//~@003R~
        super.onDestroy();                                         //~v@@@I~
    }                                                              //~v@@@I~
////**********************************                             //~@003R~
//    public void setParm(Object Pparm)                            //~@003R~
//    {                                                            //~@003R~
//        if (Dump.Y) Dump.println("Alert setParm parm="+Utils.toString(Pparm));//~@003R~
//        parmObject=Pparm;                                        //~@003R~
//    }                                                            //~@003R~
//**********************************                               //~1212I~
	private void setButton(AlertDialog.Builder Pbuilder,int Pflag) //~1212I~//~v@@@R~
    {                                                              //~1212I~
        int labelID;                                               //~0411I~
    	flag=Pflag;                                                //~1212I~//~v@@@R~
        if (Dump.Y) Dump.println("Alert.setButton flag=x"+Integer.toHexString(flag)+",labelIDs="+ Arrays.toString(labelIDs));//~0411R~
        if ((Pflag & BUTTON_POSITIVE)!=0)                          //~1212I~
        {                                                          //~1212I~
        	if (labelIDs!=null)                                    //~0411I~
	        	labelID=labelIDs[0];                               //~0411I~
            else                                                   //~0411I~
            	labelID=R.string.OK;                               //~0411I~
//          Pbuilder.setPositiveButton("OK",new DialogInterface.OnClickListener()//~1212I~//~0411R~
            Pbuilder.setPositiveButton(Utils.getStr(labelID),new DialogInterface.OnClickListener()//~0411I~
                                        {                          //~1212I~
                                                                   //~1212I~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~1212I~
                                            {                      //~1212I~
//                                          	AlertI callback=(AlertI)getTargetFragment();//~v@@@I~//~@003R~
                                            	AlertI callback=svCallback;//~@003R~
                                                Pdlg.dismiss();    //~1212I~
                                                if (Dump.Y) Dump.println("Alert.setPositiveButton.OK.onClick callback="+Utils.toString(callback));//~@003R~
                                                if (callback!=null)//~1212I~
	                                            	callback.alertButtonAction(BUTTON_POSITIVE,0);//~1212R~//~v@@@R~
                                                if ((flag & EXIT)!=0)//~1212I~
                                                	Utils.stopFinish();//~@@@2I~
                                            }                      //~1212I~
                                        }                          //~1212I~
                                     );                            //~1212I~
        }                                                          //~1212I~
        if ((Pflag & BUTTON_CLOSE)!=0)                             //~1212I~
        {                                                          //~1212I~
        	if (labelIDs!=null)                                    //~0411I~
	        	labelID=labelIDs[1];                               //~0411I~
            else                                                   //~0411I~
            	labelID=R.string.Close;                            //~0411I~
//          Pbuilder.setPositiveButton("Close",new DialogInterface.OnClickListener()//~1212I~//~0411R~
            Pbuilder.setPositiveButton(Utils.getStr(labelID),new DialogInterface.OnClickListener()//~0411I~
                                        {                          //~1212I~
                                                                   //~1212I~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~1212I~
                                            {                      //~1212I~
//                                          	AlertI callback=(AlertI)getTargetFragment();//~v@@@I~//~@003R~
                                            	AlertI callback=svCallback;//~@003R~
                                                Pdlg.dismiss();    //~1212I~
                                                if (Dump.Y) Dump.println("Alert.setPositiveButton.Close.onClick callback="+Utils.toString(callback));//~@003R~
                                                if (callback!=null)//~1212I~
		                                            callback.alertButtonAction(BUTTON_CLOSE,0);//~1212R~//~v@@@R~
                                            }                      //~1212I~
                                        }                          //~1212I~
                                     );                            //~1212I~
        }                                                          //~1212I~
        if ((Pflag & BUTTON_NEGATIVE)!=0)                          //~1212I~
        {                                                          //~1212I~
        	if (labelIDs!=null)                                    //~0411I~
	        	labelID=labelIDs[2];                               //~0411I~
            else                                                   //~0411I~
            	labelID=R.string.Cancel;                           //~0411I~
//          Pbuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener()//~1212I~//~0411R~
            Pbuilder.setNegativeButton(Utils.getStr(labelID),new DialogInterface.OnClickListener()//~0411I~
                                        {                          //~1212I~
                                                                   //~1212I~
                                            public void onClick(DialogInterface Pdlg,int buttonID)//~1212I~
                                            {                      //~1212I~
                                                Pdlg.dismiss();    //~1212I~
//                                          	AlertI callback=(AlertI)getTargetFragment();//~v@@@I~//~@003R~
                                            	AlertI callback=svCallback;//~@003R~
                                                if (Dump.Y) Dump.println("Alert.setNegativeButton.Cancel.onClick callback="+Utils.toString(callback));//~@003R~
                                                if (callback!=null)//~1212I~
		                                            callback.alertButtonAction(BUTTON_NEGATIVE,0);//~1212R~//~v@@@R~
                                            }                      //~1212I~
                                        }                          //~1212I~
                                     );                            //~1212I~
        }                                                          //~1212I~
    }//setButton                                                   //~1212I~
}//class Alert                                                //~1211R~//~@@@@R~
