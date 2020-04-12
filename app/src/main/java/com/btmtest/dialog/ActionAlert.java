//*CID://+v@@@R~:                             update#=  169;       //~1Af6R~//~v@@@R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import android.app.Dialog;
import android.graphics.Color;
import android.widget.TextView;                                    //~v@@@I~
import android.view.View;                                          //~v@@@I~

import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;                                    //~v@@@I~
import static com.btmtest.StaticVars.AG;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.UADelayed2.*;
import static com.btmtest.utils.Alert.*;

public class ActionAlert extends UFDlg                             //~v@@@R~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.actionalert;            //~v@@@I~
    private static final int COLOR_WAIT= Color.argb(0xff,0xff,0x66,0x00);   //Light's orange//~v@@@I~
	private static final int COLOR_RELEASED=Color.argb(0xff,0x54,0xe8,0x1c);   //green//~v@@@I~
    private TextView tvMessage;                                    //~v@@@I~
    private TextView tvMessage2;                                   //~v@@@I~
    private int status;                                            //~v@@@R~
    Alert.AlertI callback;
    int actionID;//~v@@@I~
//**********************************                               //~v@@@I~
	public ActionAlert()                                           //~v@@@R~
    {                                                              //~v@@@I~
    	AG.aActionAlert=this;                                      //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static ActionAlert newInstance()                        //~v@@@R~
    {                                                              //~v@@@I~
    	ActionAlert dlg=new ActionAlert();                         //~v@@@R~
    	UFDlg.setBundle(dlg,"",LAYOUTID,FLAG_OKBTN|FLAG_CANCELBTN|FLAG_NOTITLE,0/*helptitleid*/,null/*helpfile*/);//~v@@@R~
    	return dlg;                                                //~v@@@R~
     }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static void show(Alert.AlertI Pcallback,int PactionID,int Pstatus)//~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("ActionAlert.show callback="+Pcallback.toString()+",actionID="+PactionID+",status="+Pstatus);//~v@@@R~
        ActionAlert dlg=AG.aActionAlert;                           //~v@@@I~
        if (Utils.isShowingDialogFragment(dlg))                    //~v@@@I~
        {                                                          //~v@@@I~
        	updateDlg(dlg,Pstatus);                                //~v@@@I~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
    	ActionAlert aa=newInstance();                              //~v@@@R~
		aa.callback=Pcallback;                                     //~v@@@I~
		aa.actionID=PactionID;                                     //~v@@@I~
		aa.status=Pstatus;                                         //~v@@@R~
        aa.show();                                                 //~v@@@R~
    }                                                              //~v@@@I~
//**********************************************                   //~v@@@I~
	public static void updateStatus(int Pstatus)                   //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("ActionAlert.updateStatus status="+Pstatus);//~v@@@I~
        ActionAlert dlg=AG.aActionAlert;                           //~v@@@I~
        if (!Utils.isShowingDialogFragment(dlg))                   //~v@@@I~
            return;                                                //~v@@@I~
        updateDlg(dlg,Pstatus);                                    //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************                   //+v@@@I~
	public static void doDismiss()                                 //+v@@@I~
    {                                                              //+v@@@I~
    	if (Dump.Y) Dump.println("ActionAlert.doDismiss");         //+v@@@I~
        ActionAlert dlg=AG.aActionAlert;                           //+v@@@I~
        if (!Utils.isShowingDialogFragment(dlg))                   //+v@@@I~
            return;                                                //+v@@@I~
        dlg.dismiss();                                             //+v@@@I~
    }                                                              //+v@@@I~
    //******************************************                   //~v@@@M~
	@Override                                                      //~v@@@M~
    public void initLayout(View Playoutview)                       //~v@@@R~
	{                                                              //~v@@@M~
    	super.initLayout(Playoutview);                             //~v@@@I~
        tvMessage=(TextView)UView.findViewById(Playoutview,R.id.Message); //~v@@@I~
        tvMessage2=(TextView)UView.findViewById(Playoutview,R.id.Message2);//~v@@@I~
        showMsg();                                                 //~v@@@R~
    }                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
    private void showMsg()                                         //~v@@@I~
    {                                                              //~v@@@I~
		String txt=getActionMsg();                                 //~v@@@I~
    	if (Dump.Y) Dump.println("ActionAlert.showMsg setText="+txt);//~v@@@I~
        txt+="？";                                                 //~v@@@I~
	    tvMessage.setText(txt);                                    //~v@@@I~
    	repaint();                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void repaint()                                         //~v@@@R~
    {                                                              //~v@@@I~
		String txt=null;                                           //~v@@@R~
        switch(status)                                             //~v@@@R~
        {                                                          //~v@@@I~
        case STAT_BLOCKED_ALREADY:                                 //~v@@@I~
        case STAT_BLOCKED_MORE:                                    //~v@@@I~
        	txt=Utils.getStr(R.string.ActionWaitOther);                     //~v@@@R~
		    tvMessage2.setBackgroundColor(COLOR_WAIT);             //~v@@@I~
            btnOK.setEnabled(false);                               //~v@@@I~
            break;                                                 //~v@@@I~
        case STAT_BLOCKER_YOU:                                     //~v@@@R~
        	txt=Utils.getStr(R.string.ActionWaitNowYou);                    //~v@@@I~
		    tvMessage2.setBackgroundColor(COLOR_RELEASED);         //~v@@@I~
            btnOK.setEnabled(true);                                //~v@@@I~
            break;                                                 //~v@@@I~
        case STAT_BLOCK_RELEASED:                                  //~v@@@I~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("ActionAlert.repaint status="+status+",setText="+Utils.toString(txt));//~v@@@R~
        if (txt!=null)                                             //~v@@@I~
        {                                                          //~v@@@I~
	    	tvMessage2.setVisibility(View.VISIBLE);                //~v@@@R~
	    	tvMessage2.setText(txt);                               //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onResume()                                         //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("ActionAlert.onResume");          //~v@@@I~
	    super.onResume();                                          //~v@@@I~
    }                                                              //~v@@@I~
	//**********************************                           //~v@@@I~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ActionAlert onDestroy dlg this="+this.toString());//~v@@@I~
        super.onDestroy();                                         //~v@@@I~
    	AG.aActionAlert=null;                                      //~v@@@I~
    }                                                              //~v@@@I~
	//**********************************                           //~v@@@I~
	public String getActionMsg()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	int msgid;                                                 //~v@@@I~
        switch (actionID)                                          //~v@@@I~
        {                                                          //~v@@@I~
        case GCM_PON:                                              //~v@@@I~
        	msgid=R.string.UserAction_Pon;                         //~v@@@I~
            break;                                                 //~v@@@I~
        case GCM_KAN:                                              //~v@@@I~
        	msgid=R.string.UserAction_Kan;                         //~v@@@I~
            break;                                                 //~v@@@I~
        case GCM_CHII:                                             //~v@@@I~
        	msgid=R.string.UserAction_Chii;                        //~v@@@I~
            break;                                                 //~v@@@I~
        case GCM_RON:                                              //~v@@@I~
        	msgid=R.string.UserAction_Ron;                         //~v@@@I~
            break;                                                 //~v@@@I~
        default:                                                   //~v@@@I~
        	msgid=R.string.Unknown;                                //~v@@@I~
        }                                                          //~v@@@I~
    	String msg=Utils.getStr(msgid);
        if (Dump.Y) Dump.println("ActionAlert.getActionMsg actionid="+actionID+",msg="+msg);//~v@@@R~
        return msg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@R~
    //*skip setDialogWidth of UFDlg                                //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@R~
    protected void setWidthOnResume(Dialog Pdlg)                   //~v@@@R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("AlertAction.setWidthOnResume scrWidth="+AG.scrWidth+",hh="+AG.scrHeight);//~v@@@R~
//      UView.setDialogWidthWrapContent(Pdlg);                     //~v@@@R~
    }                                                              //~v@@@R~
//    //******************************************                 //~v@@@R~
//    protected int getDialogWidth()                               //~v@@@R~
//    {                                                            //~v@@@R~
//        int ww = 200;                                            //~v@@@R~
//        if (Dump.Y)                                              //~v@@@R~
//            Dump.println("AlertAction.getDialogWidth:ww=" + ww + ",scrWidth=" + AG.scrWidth + ",hh=" + AG.scrHeight);//~v@@@R~
//        return ww;                                               //~v@@@R~
//    }                                                            //~v@@@R~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    protected void onClickOK()                                     //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ActionAlert.onClickOK");         //~v@@@R~
        callback.alertButtonAction(BUTTON_POSITIVE,actionID);      //~v@@@I~
	    dismiss();                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    protected void onClickCancel()                                 //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ActionAlert.onClickClose");      //~v@@@R~
        callback.alertButtonAction(BUTTON_NEGATIVE,actionID);      //~v@@@I~
	    dismiss();                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private static void updateDlg(ActionAlert Pdlg,int Pstatus)    //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ActionAlert.updateDlg status="+Pstatus);//~v@@@I~
        Pdlg.status=Pstatus;                                       //~v@@@I~
        if (AG.isMainThread())                                     //~v@@@I~
        	Pdlg.repaint();                                        //~v@@@R~
        else                                                       //~v@@@I~
        	Pdlg.updateDlgUI();                                    //~v@@@R~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    private void updateDlgUI()                                     //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ActionAlert.updateDlgUI");       //~v@@@R~
        AG.activity.runOnUiThread(                                 //~v@@@I~
            new Runnable()                                         //~v@@@I~
            {                                                      //~v@@@I~
                @Override                                          //~v@@@I~
                public void run()                                  //~v@@@I~
                {                                                  //~v@@@I~
                    try                                            //~v@@@I~
                    {                                              //~v@@@I~
    				    if (Dump.Y) Dump.println("ActionAlert.updateDlgUI runonUiThread.run");//~v@@@I~
                    	repaint();                             //~v@@@R~
                    }                                              //~v@@@I~
                    catch(Exception e)                             //~v@@@I~
                    {                                              //~v@@@I~
                        Dump.println(e,"ActionAlert.updateDlgUI.run");//~v@@@I~
                    }                                              //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
                                  );                               //~v@@@I~
                                                                   //~v@@@I~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
