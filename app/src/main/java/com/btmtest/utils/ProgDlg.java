//*CID://+va40R~: update#= 163;                                    //~va40R~
//**********************************************************************//~1107I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//**********************************************************************//~1107I~
package com.btmtest.utils;                                         //~1107R~  //~1108R~//~1109R~//~@@@@R~//~v@@@R~

import java.io.Serializable;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import com.btmtest.R;

import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.ProgressDialog;                               //+va40R~
//import android.app.DialogFragment;                               //~va40R~
import android.support.v4.app.DialogFragment;                      //~va40I~
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//**********************************************************************//~1107I~
public class ProgDlg extends DialogFragment                        //~v@@@R~
	implements URunnable.URunnableI                                          //~1A2jI~
{                                                                  //~0914I~
	private static final String PARM_TITLE="title";                //~v@@@I~
	private static final String PARM_MSG="msg";                    //~v@@@I~
	private static final String PARM_CANCELABLE="cancelable";      //~v@@@I~
	private static final int LAYOUTID=R.layout.progdlg;              //~va40I~
//    private static final String PARM_CALLBACK="callback";        //~v@@@R~
	public  static final int REASON_CANCEL=0;                      //~v@@@R~
	public  static final int REASON_DISMISS=1;                     //~v@@@R~
	private ProgDlgI dismissCallback;                                  //~1425R~//~@@@@R~//~@@@2R~//~v@@@R~
	private Boolean swCancel=false;                                //~v@@@I~
//    private boolean dismissCallback=true;                                  //~@@@2I~//~v@@@R~
//    private boolean showing;                                       //~@@@2I~//~v@@@R~
//  private ProgressDialog androidDialog;                          //~va40R~
    private Dialog androidDialog;                                  //~va40I~
    private String dialogMsg;                                      //~9A05I~
    private View layoutview;                                       //~va40I~
    private AlertDialog.Builder builder;                           //~va40I~
//**********************************                               //~1211I~
	public interface ProgDlgI extends Serializable                                            //~1107R~//~1211R~//~@@@@R~//~v@@@I~
	{                                                                  //~0914I~//~v@@@I~
    	void onCancelProgDlg(int Preason);             //~@@@2I~    //~@@@@I~//~v@@@I~
	}//interface ProgDlgI                                      //~1211R~//~v@@@I~
//**********************************                               //~v@@@I~
	public ProgDlg()                                              //~1A2jI~
    {                                                              //~1A2jI~
    }                                                              //~1A2jI~
//**********************************                               //~@@@2I~
//* spinner type                                                   //~@@@2I~
//**********************************                               //~@@@2I~
    public static void showProgDlg(int Ptitle,int Pmsg,boolean Pcancelable,ProgDlgI Pcallback)       //~1A2jI~//~v@@@R~
    {                                                              //~@@@2I~
    	showProgDlg(Ptitle!=0?Utils.getStr(Ptitle):"",Pmsg!=0?Utils.getStr(Pmsg):"",Pcancelable,Pcallback);//~@@@2R~//~v@@@R~
    }                                                              //~@@@2I~
//**********************************                               //~@@@2I~
    public static void showProgDlg(int Ptitle,String Pmsg,boolean Pcancelable,ProgDlgI Pcallback)//~v@@@R~
    {                                                              //~@@@2I~
    	showProgDlg(Ptitle!=0?Utils.getStr(Ptitle):"",Pmsg,Pcancelable,Pcallback);//~@@@2I~//~v@@@R~
    }                                                              //~@@@2I~
//****************************************                         //~1A2jI~//~v@@@M~
    public static void showProgDlg(String Ptitle,String Pmsg,boolean Pcancelable,ProgDlgI Pcallback)//~1A2jI~//~v@@@R~
    {                                                              //~1A2jI~//~v@@@M~
    	Bundle b=new Bundle();                                     //~v@@@I~
        b.putString(PARM_TITLE,Ptitle);                            //~v@@@I~
        b.putString(PARM_MSG,Pmsg);                                //~v@@@I~
        b.putBoolean(PARM_CANCELABLE,Pcancelable);                 //~v@@@I~
//      b.putSerializable(PARM_CALLBACK,Pcallback);                //~v@@@R~
    	ProgDlg dlg=new ProgDlg();                                 //~v@@@I~
        dlg.setArguments(b);                                       //~v@@@I~
        if (Dump.Y) Dump.println("ProgDlg showProgDlg setArgument="+b.toString());//~v@@@R~
	    dlg.setCancelableDlg(Pcancelable);                                    //~v@@@R~
        dlg.setDismissCallback(Pcallback);                         //~v@@@I~
        URunnable.setRunFuncDirect(dlg,null/*objparm*/,0/*int parm*/);//~1A2jI~//~v@@@M~
    }                                                              //~1A2jI~//~v@@@M~
//****************************************                         //~v@@@I~
    private void setCancelableDlg(Boolean Pcancelable)             //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ProgDlg setCancelableDlg cancelable="+Pcancelable);//~v@@@R~
		setCancelable(Pcancelable);                                //~v@@@R~
    }                                                              //~v@@@I~
//****************************************                         //~v@@@I~
    private void setDismissCallback(ProgDlgI Pcallback)            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ProgDlg setDismissCallback");    //~v@@@R~
    	dismissCallback=Pcallback;                                 //~v@@@I~
    }                                                              //~v@@@I~
//===============================================================================//~@@@2I~//~1A2jI~//~v@@@M~
//=run on UIThread                                                 //~@@@2I~//~1A2jI~//~v@@@M~
//===============================================================================//~@@@2I~//~1A2jI~//~v@@@M~
	public void URunnableCB(Object Pobj,int Pint)                           //~@@@2I~//~1A2jI~//~v@@@I~
    {                                                              //~@@@2I~//~1A2jI~//~v@@@M~
		showProgDlgUI();//~1A2jI~                                  //~v@@@R~
    }                                                              //~@@@2I~//~1A2jI~//~v@@@M~
//****************************************                         //~1A2jI~//~v@@@M~
    public void showProgDlgUI()                             //~v@@@R~
    {                                                              //~1A2jI~//~v@@@M~
        show(AG.fragmentManager,ProgDlg.class.getSimpleName());                                                 //~1A2jI~//~v@@@I~
        AG.progDlg=this;                                           //~v@@@M~
        if (Dump.Y) Dump.println("ProgDlg.showProgDlg this="+this.toString());//~v@@@R~
    }                                                              //~1A2jI~//~v@@@M~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public Dialog onCreateDialog(Bundle Pbundle)                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ProgDlg onCreateDialog Pbundle notnull"+(Pbundle!=null));//~v@@@R~
//        if (AG.androidDialog!=null)                              //~v@@@R~
//        {                                                        //~v@@@R~
//            if (Dump.Y) Dump.println("ProgDlg onCreateDialog not null");//~v@@@R~
//            return AG.androidDialog;                             //~v@@@R~
//        }                                                        //~v@@@R~
        Bundle b=getArguments();                                   //~v@@@I~
        if (Dump.Y) Dump.println("ProgDlg onCreateDialog getArgument ="+b.toString());//~v@@@R~
        String title=b.getString(PARM_TITLE,null);                 //~v@@@R~
        String msg=b.getString(PARM_MSG,"");                       //~v@@@R~
        Boolean cancelable=b.getBoolean(PARM_CANCELABLE,false);    //~v@@@R~
//      callback=(ProgDlgI)b.getSerializable(PARM_CALLBACK);       //~v@@@R~
  		setCancelable(cancelable);                                 //~v@@@R~
                                                                   //~v@@@I~
//      ProgressDialog pdlg=new ProgressDialog(getActivity());     //~va40R~
    	builder=new AlertDialog.Builder(AG.context);               //~va40I~
        layoutview = AG.inflater.inflate(LAYOUTID,null);           //~va40I~
        builder.setView(layoutview);                               //~va40I~
    	if (title!=null)                                           //~v@@@I~
//  		pdlg.setTitle(title);                                  //~va40R~
    		builder.setTitle(title);                               //~va40I~
        else                                                       //~v@@@I~
//      	pdlg.setTitle(Utils.getStr(R.string.app_name));        //~va40R~
        	builder.setTitle(Utils.getStr(R.string.app_name));     //~va40I~
//      pdlg.setMessage(msg);                                      //~va40R~
        builder.setMessage(msg);                                   //~va40I~
        dialogMsg=msg;                                             //~9A05I~
//      pdlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);       //~va40R~
//        AG.androidDialog=pdlg;                                   //~v@@@R~
        setButton();                                        //~va40I~
    	Dialog pdlg=builder.create();                              //~va40I~
        androidDialog=pdlg;                                        //~v@@@I~
//      setButton();                                               //~va40R~
        if (Dump.Y) Dump.println("ProgDlg onCreateDialog return androidDialog="+androidDialog.toString());//~v@@@R~
        if (Dump.Y) Dump.println("ProgDlg.showProgDlg this="+this.toString()+",msg="+msg);//~v@@@R~//~9A05R~
    	return pdlg;                                               //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~@@@2I~
	@Override                                                      //~v@@@I~
    public void onCancel(DialogInterface Pdi)                           //~v@@@R~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("ProgDlg onCancel");//~@@@2R~     //~v@@@R~
//            showing=false;                             //~@@@2I~ //~v@@@R~
//      doCallback(REASON_CANCEL);                               //~@@@2R~//~v@@@R~
		swCancel=true;                                             //~v@@@I~
//      AG.androidDialog.dismiss();                                 //~@@@2I~//~v@@@R~
        androidDialog.dismiss();                                   //~v@@@I~
        if (Dump.Y) Dump.println("ProgDlg onCancel return");           //~@@@2I~//~v@@@R~
    }                                                              //~@@@2I~
////**********************************                             //~v@@@R~
//    @Override                                                    //~v@@@R~
//    public Dialog getDialog()                                    //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ProgDlg getDialog android dlg="+(AG.androidDialog==null?"null":AG.androidDialog.toString()));//~v@@@R~
//        return AG.androidDialog;                                 //~v@@@R~
//    }                                                            //~v@@@R~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ProgDlg onDestroy progDlg="+(AG.progDlg==null?"null":AG.progDlg.toString()));//~v@@@R~
        super.onDestroy();                                         //~v@@@I~
//  	AG.androidDialog=null;                                     //~v@@@R~
		AG.progDlg=null;                                           //~v@@@I~
    }                                                              //~v@@@I~
//*******************************                                  //~@@@2I~
	private void setButton()                                       //~@@@2I~//~v@@@R~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("ProgDlg setButton");      //~@@@2I~//~v@@@R~
//      AG.androidDialog.setButton(                                   //~@@@2I~//~v@@@R~
//      		DialogInterface.BUTTON_NEGATIVE,                   //~va40R~
//              Utils.getStr(R.string.Close),             //~1A2jI~//~v@@@R~//~9A05R~
        builder.setNegativeButton(                                 //~va40I~
                Utils.getStr(R.string.Cancel),                     //~9A05I~
                new OnClickListener()              //~@@@2I~
                	{                                              //~@@@2I~
                    	@Override                                  //~@@@2I~
                        public void onClick(DialogInterface Pdlg,int Pid)//~@@@2I~
                        {                                          //~@@@2I~
					        if (Dump.Y) Dump.println("ProgDlg CancelButton Click");//~@@@2I~//~v@@@R~
//                      	AG.androidDialog.cancel();                 //~@@@2I~//~v@@@R~
                        	androidDialog.cancel();                //~v@@@I~
                        }                                          //~@@@2I~
                    }
        		);//~@@@2I~
    }                                                              //~@@@2I~
////*******************************                                  //~@@@2I~//~v@@@R~
//    public void dismiss(boolean Pdismisscallback)                  //~@@@2R~//~v@@@R~
//    {                                                              //~@@@2I~//~v@@@R~
//        if (Dump.Y) Dump.println("ProgDlg dismiss");        //~@@@2I~//~v@@@R~
//        dismissCallback=Pdismisscallback;                          //~@@@2I~//~v@@@R~
//        androidDialog.dismiss();                                   //~@@@2I~//~v@@@R~
////        showing=false;                                             //~@@@2I~//~v@@@R~
//    }                                                              //~@@@2I~//~v@@@R~
//*******************************                                  //~@@@2I~
//*callback                                                        //~@@@2I~
//*parm 0:cancel,1:dismiss                                         //~@@@2I~
//*******************************                                  //~@@@2I~
	private void doCallback(int Preason)                             //~@@@2I~//~v@@@R~
    {                                                              //~@@@2I~
    	try                                                        //~@@@2I~
        {                                                          //~@@@2I~
        	if (dismissCallback!=null)                                    //~@@@2I~//~v@@@R~
	        	dismissCallback.onCancelProgDlg(Preason);          //~@@@2I~//~v@@@R~
        }                                                          //~@@@2I~
        catch(Exception e)                                         //~@@@2I~
        {                                                          //~@@@2I~
    		Dump.println(e,"ProgDlg:doCallback="+Preason);    //~@@@2I~//~v@@@R~
        }                                                          //~@@@2I~
    }                                                              //~@@@2I~
//    public boolean isShowing()                                     //~@@@2I~//~v@@@R~
//    {                                                              //~@@@2I~//~v@@@R~
//        return showing;                                            //~@@@2I~//~v@@@R~
//    }                                                              //~@@@2I~//~v@@@R~
//**********************************************************************    //~1326I~//~1330R~//~@@@2I~//~v@@@R~
//*dialogfragment has dismisslistener, do not setondismisslistener                    //~1326I~//~1330R~//~@@@2I~//~v@@@R~
//*****************************************************************//~1326I~//~1330R~//~@@@2I~//~v@@@R~
    @Override                                                  //~1326I~//~@@@2I~//~v@@@R~
    public void onDismiss(final DialogInterface Pdialog)             //~1326I~//~@@@2I~//~v@@@R~
    {                                                          //~1326I~//~@@@2I~//~v@@@R~
		if (Dump.Y) Dump.println("ProgDlg.onDismiss msg="+dialogMsg);//~9A05R~
        super.onDismiss(Pdialog);                                  //~v@@@I~
        doCallback(swCancel?REASON_CANCEL:REASON_DISMISS);                                       //~@@@2I~//~v@@@R~
        dialogMsg=null;                                            //~9A05R~
        AG.progDlg=null;                                           //~9A05I~
    }                                                              //~1326I~//~@@@2I~
//****************************************                         //~@@@2I~
    public static boolean dismissCurrentUI()                                //~@@@2I~//~v@@@R~
    {                                                              //~@@@2I~
    	boolean rc=false;                                          //~@@@2I~//~v@@@R~
		if (Dump.Y) Dump.println("ProgDlg.dismissCurrent "+Utils.toString(AG.progDlg));//~9A05R~
//  	if (Utils.isShowingDialogFragment(AG.progDlg))            //~@@@2R~//~v@@@R~
    	if (Utils.isShowingDialogFragment(AG.progDlg))          //~v@@@I~
    	{                                                          //~@@@2I~
//      	AG.progDlg.getDialog().dismiss();                             //~@@@2I~//~v@@@R~
        	AG.progDlg.dismiss();                               //~v@@@I~
            rc=true;                                               //~@@@2I~
        }                                                          //~@@@2I~
    	if (Dump.Y) Dump.println("ProgDlg static dismiss rc="+rc); //~9A05R~
        AG.progDlg=null;	                                       //~v@@@I~//~9A05M~
        return rc;                                                 //~@@@2I~
    }                                                              //~@@@2I~
//***********************************************************************//~v@@@I~
//* dismiss() can be on subthread, but confirm time of after showDialog()//~v@@@I~
//***********************************************************************//~v@@@I~
    public static boolean dismissCurrent()                         //~v@@@I~
    {                                                              //~v@@@I~
		boolean rc;                                                //~v@@@R~
        if (Dump.Y) Dump.println("ProgDlg.dismissCurrent"+(AG.progDlg==null?"null":AG.progDlg.toString()));//~v@@@I~
	    if (AG.isMainThread())                                     //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("ProgDlg.dismissCurrent mainThread");//~v@@@I~
			rc=dismissCurrentUI();                                 //~v@@@R~
            return rc;                                             //~v@@@R~
        }                                                          //~v@@@I~
    	rc=Utils.isShowingDialogFragment(AG.progDlg);              //~v@@@I~
	    AG.activity.runOnUiThread(                                 //~v@@@I~
        	new Runnable()                                         //~v@@@I~
            	{                                                  //~v@@@I~
                	@Override                                      //~v@@@I~
                    public void run()                              //~v@@@I~
                    {                                              //~v@@@I~
				        if (Dump.Y) Dump.println("ProgDlg.dismissCurrent runOnUiThread");//~v@@@I~
                        try                                        //~v@@@I~
                        {                                          //~v@@@I~
							dismissCurrentUI();                    //~v@@@I~
                        }                                          //~v@@@I~
                        catch(Exception e)                         //~v@@@I~
                        {                                          //~v@@@I~
            				Dump.println(e,"ProgDlg:dismissCurrent.runOnUIThread.run");//~v@@@I~
                        }                                          //~v@@@I~
                    }                                              //~v@@@I~
                });                                                //~v@@@I~
    	return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
}//class ProgDlg                                                //~1211R~//~@@@@R~//~@@@2R~
