//*CID://+DATER~:                             update#=  461;       //~v@@@R~//~9404R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Point;
import android.view.View;
import android.widget.Button;                                      //~v@@@I~
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btmtest.BT.BTMulti;
import com.btmtest.R;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Prop;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UButton;                                    //~v@@@R~
import com.btmtest.utils.UFile;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;

public abstract class SettingDlg extends UFDlg                //~v@@@R~
			implements FileDialog.FileDialogI                      //~v@@@I~
{                                                                  //~2C29R~
	protected static final String RESET_SYNCDATE="ResetDate";        //~9406I~//~9412R~
	protected Button btnLoad;                                      //~v@@@R~
	protected Button btnSave;                                      //~v@@@R~
	protected Button btnSend;                                      //~9404I~
	protected Button btnSendBackOK,btnSendBackNG,btnShowStatus;    //~9405R~
	protected TextView tvShowStatus;                               //~9405I~
    protected LinearLayout llShowStatus;                           //~9405I~
                                                                   //~1A6fI~
    protected UFDlg ufdlg;                                         //~v@@@R~
    public    Prop curProp;                                        //~v@@@R~//~9516R~
    protected boolean swChanged;                                   //~9405R~
    protected boolean swReceived,swFixed;                          //~9405I~//~9408R~
    protected boolean swShowInGame;                                //~9408I~
    protected boolean swServer,swClient;    //undefined status may exist before connectted//~9405R~
    protected String propCmt;                                      //~v@@@I~
    protected boolean swRuleSetting;                               //~9622I~
    protected boolean swSaveDialog2Properties;                     //~9B09I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    protected abstract Prop checkValidity(String Pfname);                        //~1A4zI~//~v@@@R~
    protected abstract void onClickOtherUnknown(int Pbuttonid);    //~v@@@R~
    protected abstract boolean dialog2Properties();                //~v@@@R~
    protected abstract boolean dialog2Properties(String Pfnm);     //~9405I~
    protected abstract void properties2Dialog(Prop Pprop);         //~v@@@R~
    protected abstract String getFilter();                         //~v@@@I~
    //*************************************************************************//~v@@@I~
    //******************************************                   //~v@@@R~
    public SettingDlg()                                            //~v@@@R~
    {                                                              //~v@@@R~
        swServer=BTMulti.isServerDevice();                         //~9405I~
        swClient=BTMulti.isClientDevice();                         //~9405I~
        if (Dump.Y) Dump.println("SettingDlg.defaultConstructor swServer="+swServer+",swClient="+swClient); //~v@@@R~//~9405R~
    }                                                              //~v@@@R~
//    //******************************************                 //~v@@@R~
//    public static SettingDlg newInstance(int Ptitleid,int Playoutid,int Pflag,int Phelptitleid,String Phelpfilename)//~v@@@R~
//    {                                                            //~v@@@R~
//        SettingDlg dlg=new SettingDlg();                         //~v@@@R~
//        dlg.ufdlg=UFDlg.newInstance(dlg,Ptitleid,Playoutid,Pflag,Phelptitleid,Phelpfilename);//~v@@@R~
//        return dlg;                                              //~v@@@R~
//    }                                                            //~v@@@R~
//    public SettingDlg(int Ptitleid,int Playoutid,int Pflag,int Phelptitleid,String Phelpfilename)//~v@@@R~
//    {                                                            //~v@@@R~
//        ufdlg=UFDlg.newInstance((UFDlg)this,Ptitleid,Playoutid,Pflag,Phelptitleid,Phelpfilename);//~v@@@R~
//    }                                                            //~v@@@R~
    //******************************************                   //~v@@@M~
    @Override
    protected void initLayout(View PView)                          //~v@@@R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("SettingDlg.initLayout");         //~v@@@I~
        super.initLayout(PView);                                   //~v@@@R~
        btnLoad          =              UButton.bind(PView,R.id.Load,this);//~v@@@I~
        btnSave          =              UButton.bind(PView,R.id.Save,this);//~v@@@I~
        btnSend          =              UButton.bind(PView,R.id.Send,this);//~9404I~
        btnSendBackOK    =              UButton.bind(PView,R.id.SendBackOK,this);//~9405I~
        btnSendBackNG    =              UButton.bind(PView,R.id.SendBackNG,this);//~9405I~
        btnShowStatus    =              UButton.bind(PView,R.id.btnShowStatus,this);//~9405I~
        tvShowStatus     =(TextView)UView.findViewById(PView,R.id.tvShowStatus);//~9405I~
        llShowStatus=(LinearLayout)UView.findViewById(PView,R.id.llShowStatus);//~9405I~
                                                                   //~9405I~
        LinearLayout llFile=(LinearLayout)UView.findViewById(PView,R.id.llFile);//~9405I~
        if (swShowInGame)                                          //~9408I~
        {                                                          //~9408I~
            llFile.setVisibility(View.GONE);                      //~9408R~//~9409R~
            btnOK.setVisibility(View.GONE);                       //~9408I~//~9409R~
			btnSend.setVisibility(View.GONE);                     //~9408I~//~9409R~
//  	    btnCancel.setText(Utils.getStr(R.string.Close));      //~9408I~//~9409R~
//  		setButtonClose(true);                                  //~9409R~
        }                                                          //~9408I~
        else                                                       //~9408I~
        {                                                          //~9408I~
            if (BTMulti.isClientDevice())                              //~9406I~//~9408R~
            {                                                          //~9406I~//~9408R~
                llShowStatus.setVisibility(View.VISIBLE);   //Load/Save button//~9406I~//~9408R~
                btnShowStatus.setVisibility(View.GONE);                //~9406I~//~9408R~
                if (swReceived)                                        //~9406R~//~9408R~
                {                                                      //~9406I~//~9408R~
                    btnSendBackOK.setVisibility(View.VISIBLE);         //~9406I~//~9408R~
                    btnSendBackNG.setVisibility(View.VISIBLE);         //~9406I~//~9408R~
                    btnLoad.setEnabled(false);                     //~9408R~
                    btnOK.setVisibility(View.GONE);                //~9408I~
                    btnSend.setVisibility(View.GONE);                  //~9406I~//~9408R~
//                  btnCancel.setText(Utils.getStr(R.string.Close));   //~9406I~//~9408R~//~9409R~
//  			    setButtonClose(true);                          //~9409R~
                }                                                      //~9406I~//~9408R~
            }                                                          //~9406I~//~9408R~
            else                                                       //~9406I~//~9408R~
            {                                                          //~9406I~//~9408R~
                if (BTMulti.isServerDevice())                          //~9406I~//~9408R~
                {                                                      //~9406I~//~9408R~
                    llShowStatus.setVisibility(View.VISIBLE);          //~9406R~//~9408R~
                }                                                      //~9406I~//~9408R~
                else                                                   //~9406I~//~9408R~
                {                                                      //~9406I~//~9408R~
                    llShowStatus.setVisibility(View.VISIBLE);          //~9406R~//~9408R~
//                  btnShowStatus.setVisibility(View.GONE);            //~9406I~//~9408R~//~9629R~
                    btnShowStatus.setEnabled(false);               //~9629I~
                }                                                      //~9406I~//~9408R~
            }                                                          //~9406I~//~9408R~
            showSyncStatus();                                   //~9405I~//~9406I~//~9408R~
        }                                                          //~9408I~
    }                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onClickOther(int Pbuttonid)                       //~v@@@R~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("SettingDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~v@@@I~
        switch(Pbuttonid)                                    //~v@@@R~
        {                                                          //~v@@@R~
            case R.id.Load:                                        //~v@@@R~
                onClickLoad();                                     //~v@@@R~
                break;                                             //~v@@@R~
            case R.id.Save:                                        //~v@@@R~
                onClickSave();                                     //~v@@@R~
                break;                                             //~v@@@R~
            case R.id.Send:                                        //~9404I~
                onClickSend();                                     //~9404I~
                break;                                             //~9404I~
            case R.id.btnShowStatus:                               //~9405I~
                onClickShowStatus();                               //~9405I~
                break;                                             //~9405I~
            case R.id.SendBackOK:                               //~9406I~
                onClickSendBack(true);                             //~9406I~
                break;                                             //~9406I~
            case R.id.SendBackNG:                               //~9406I~
                onClickSendBack(false);                            //~9406I~
                break;                                             //~9406I~
            default:                                               //~v@@@R~
                onClickOtherUnknown(Pbuttonid);                             //~v@@@I~
        }                                                          //~v@@@R~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onClickOK()                                       //~v@@@R~
    {                                                              //~1602M~//~v@@@I~
        if (Dump.Y) Dump.println("SettingDlg.onClickOK");                     //~v@@@I~//~9404R~
//        dialog2Properties();                                        //~v@@@I~//~9404R~
//        if (swChanged)                                             //~v@@@I~//~9404R~
//        {                                                          //~v@@@I~//~9404R~
//            saveProperties();   //save to current.rule             //~v@@@I~//~9404R~
//            AG.ruleProp.resetProperties(curProp);                               //~v@@@R~//~9404R~
//        }                                                          //~v@@@I~//~9404R~
	    getValue();                                                //~9404I~
        dismiss();                                                 //~v@@@R~
    }                                                              //~1602M~//~v@@@I~
//    //*******************************************************    //~9409R~
//    private void setButtonClose(boolean PswClose)                //~9409R~
//    {                                                            //~9409R~
//        btnCancel.setText(Utils.getStr(PswClose ? R.string.Close : R.string.Cancel));//~9409R~
//    }                                                            //~9409R~
    //*******************************************************      //~9406I~
//  private void showSyncStatusReset(int PctrSent)                             //~9406I~//~9A29R~//~9B25R~
    private void showSyncStatusReset(Point Ppoint)                 //~9B25I~
    {                                                              //~9406I~
        if (Dump.Y) Dump.println("SettingDlg.showSyncStatusReset ctrSent,ctrClient="+Ppoint.toString());//~9B25I~
//  	if (PctrSent!=0)                                            //~9A29I~//~9B25R~
    	if (Ppoint.x!=0)                                           //~9B25I~
	        tvShowStatus.setText(Utils.getStr(R.string.Info_RuleSyncReset,Ppoint.x));//~9406I~//~9A29R~
        else                                                       //~9A29I~
    	if (Ppoint.y==0)                                           //~9B25I~
	        tvShowStatus.setText(Utils.getStr(R.string.Info_RuleSyncNoConnection));//~9A29I~
        else                                                       //~9B25I~
	        tvShowStatus.setText(Utils.getStr(R.string.Info_RuleSynchedAlready,Ppoint.y));//~9B25I~
//      setButtonClose(true);                                      //~9409R~
    }                                                              //~9406I~
    //*******************************************************      //~9405I~
    private void showSyncStatus()                                       //~9405I~//~9406R~
    {                                                              //~9405I~
        if (Dump.Y) Dump.println("SettingDlg.showSyncStatus");         //~9405I~//~9B03R~
        boolean swSynched=BTMulti.isRuleSynched();                     //~9405I~
        String stat;                                               //~9405I~
        if (BTMulti.isClientDevice() || !BTMulti.isServerDevice()) //~9406R~
        {                                                          //~9406I~
//  		int id=AG.aBTMulti.isRuleSettingSynchedAll() ? R.string.Info_RuleSynched : R.string.Info_NoRuleSynched; //continue if true//~9406I~//~9621R~
//  		int id=AG.aBTMulti.isRuleSynched() ? R.string.Info_RuleSynched : R.string.Info_NoRuleSynched; //continue if true//~9621I~//~9B25R~
    		int id=swSynched ? R.string.Info_RuleSynched : R.string.Info_NoRuleSynched; //continue if true//~9B25I~
        	stat=Utils.getStr(id);                                 //~9406I~
//  		setButtonClose(AG.aBTMulti.isRuleSettingSynchedAll()); //~9409R~
        }                                                          //~9406I~
        else                                                       //~9406I~
        {                                                          //~9406I~
            if (swSynched)                                              //~9405I~//~9406R~
            {                                                      //~9406R~
                stat=Utils.getStr(R.string.Info_RuleSynched);             //~9405I~//~9406R~
//  			setButtonClose(true);                              //~9409R~
            }                                                      //~9406R~
            else                                                       //~9405I~//~9406R~
            {                                                          //~9405I~//~9406R~
                String[][] sss=BTMulti.getRuleSyncStatus();                //~9405I~//~9406R~
                int ctrOK=sss[0]==null ? 0 :  sss[0].length;           //~9405R~//~9406R~
                int ctrNG=sss[1]==null ? 0 :  sss[1].length;           //~9405I~//~9406R~
                int ctrNR=sss[2]==null ? 0 :  sss[2].length;           //~9405I~//~9406R~
                int ctrAL=sss[3]==null ? 0 :  sss[3].length;           //~9405I~//~9406R~
                stat=AG.resource.getString(R.string.Info_RuleSynchedNotYet,ctrAL,ctrOK,ctrNG,ctrNR);//~9405R~//~9406R~
            }                                                          //~9405I~//~9406R~
        }                                                          //~9406I~
        tvShowStatus.setText(stat);                                //~9405I~
    }                                                              //~9405I~
    //*******************************************************      //~9405I~
    public void onClickShowStatus()                                //~9405I~
    {                                                              //~9405I~
        if (Dump.Y) Dump.println("SettingDlg.onClickShowStatus");  //~9405I~
        boolean swSynched=BTMulti.isRuleSynched();                 //~9405I~
        String stat;                                               //~9405I~
        if (swSynched)                                             //~9405I~
	        tvShowStatus.setText(Utils.getStr(R.string.Info_RuleSynched));//~9405I~
        else                                                       //~9405I~
        {                                                          //~9405I~
        	String[][] sss=BTMulti.getRuleSyncStatus();            //~9405I~
            int ctrOK=sss[0]==null ? 0 :  sss[0].length;           //~9405I~
            int ctrNG=sss[1]==null ? 0 :  sss[1].length;           //~9405I~
            int ctrNR=sss[2]==null ? 0 :  sss[2].length;           //~9405I~
            if (ctrNG!=0)                                          //~9405I~
            	UView.showToast(Utils.getStr(R.string.Info_RuleSynchNG,Arrays.toString(sss[1])));//~9405R~
            if (ctrNR!=0)                                          //~9405I~
            	UView.showToast(Utils.getStr(R.string.Info_RuleSynchNoResp,Arrays.toString(sss[2])));//~9405R~
        }                                                          //~9405I~
    }                                                              //~9405I~
    //*******************************************************      //~9404I~
    public void getValue()                                         //~9404I~
    {                                                              //~9404I~
        if (Dump.Y) Dump.println("SettingDlg.getvalue");           //~9404I~
        dialog2Properties();                                       //~9404I~
//        if (swChanged)                                             //~9404I~//~9412R~
//        {                                                          //~9404I~//~9412R~
//            if (AG.aBTMulti!=null)                                 //~9406I~//~9412R~
//            {                                                      //~9406I~//~9412R~
//                AG.aBTMulti.setRuleSettingSynchedAll(false/*swOK*/,""/*syncDate*/);//~9406I~//~9412R~
//                if (BTMulti.isServerDevice())                      //~9406I~//~9412R~
//                    notifySynched(false/*swOK*/,RESET_SYNCDATE);   //~9406R~//~9412R~
//            }                                                      //~9406I~//~9412R~
//            saveProperties();   //save to current.rule             //~9404I~//~9412R~
//            AG.ruleProp.resetProperties(curProp);                  //~9404R~//~9412R~
//            swChanged=false;                                       //~9404I~//~9412R~
//        }                                                          //~9404I~//~9412R~
//        else                                                       //~9405I~//~9412R~
//        if (swReceived)                                            //~9405I~//~9412R~
//        {                                                          //~9405I~//~9412R~
//            saveProperties();   //save to current.rule             //~9405I~//~9412R~
//            AG.ruleProp.resetProperties(curProp);                  //~9405I~//~9412R~
//        }                                                          //~9405I~//~9412R~
    }                                                              //~9404I~
    //*******************************************************      //~v@@@I~
    protected void onClickLoad()                                   //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("SettingDlg.onClickLoad");                   //~v@@@I~//~9404R~
        FileDialog.newInstance(getFilter()/*extension*/,true/*load*/).show(this/*FileDialogI*/,null/*defaultitemname*/);//~v@@@R~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    protected void onClickSave()                                   //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("SettingDlg.onClickSave");                   //~v@@@I~//~9404R~
        FileDialog.newInstance(getFilter()/*extension*/,false/*not load*/).show(this/*FileDialogI*/,AG.ruleFile/*defaultitemname*/);//~v@@@R~
    }                                                              //~v@@@I~
    //*******************************************************      //~9404I~
    protected void onClickSend()                                   //~9404I~
    {                                                              //~9404I~
    //*Override this                                               //~9404I~
        if (Dump.Y) Dump.println("SettingDlg.onClickSend");        //~9404R~
    }                                                              //~9404I~
    //*******************************************************      //~9406I~
    //*on clinet, respose to rulesetting sync req                  //~9406I~
    //*******************************************************      //~9406I~
    protected void onClickSendBack(boolean PswOK)                  //~9406I~
    {                                                              //~9406I~
        if (Dump.Y) Dump.println("SettingDlg.onClickSendBack swOK="+PswOK+",AG.ruleSyncDate="+AG.ruleSyncDate);//~9406I~//~9A31R~
	    AG.aBTMulti.setLockRuleSetting(PswOK);	//lock on client   //~9406R~
        if (PswOK)                                                 //~9616I~
		    saveReceived(); //update AG.ruleSyncDate               //~9616I~//~9617R~
        String msg=(PswOK ? 1:0)+MSG_SEP+AG.ruleSyncDate;          //~9406R~
        AG.aBTMulti.sendMsgToServer(true/*swApp*/,GCM_SETTING_RESP,msg);//~9406I~
        dismiss();                                                 //~9618I~
    }                                                              //~9406I~
    //*******************************************************      //~9404I~
    //*on Server,send setting to all client                        //~9404I~
    //*******************************************************      //~9404I~
    protected void send(String Pcmt,Prop Pprop)                    //~9404I~
    {                                                              //~9404I~
    	int ctrSent=0;                                             //~9A29I~
        if (Dump.Y) Dump.println("SettingDlg.send Pcmt="+Pcmt);    //~9404I~
        if (BTMulti.isUndefinedDevice())//~9404R~                  //~9405R~
        {                                                          //~9404M~
        	UView.showToast(R.string.Err_LackingMember);           //~9404M~
            return;                                                //~9404M~
        }                                                          //~9404M~
        BTMulti aBTM=AG.aBTMulti;                                  //~9406I~
        if (BTMulti.isClientDevice())                  //~9404I~   //~9405R~
        {                                                          //~9404M~
//        	if (aBTM.isRuleSettingSynchedAll())                    //~9406I~//~9621R~
//          {                                                      //~9406I~//~9621R~
//          	UView.showToast(R.string.Err_SendAfterSyncAll);    //~9406I~//~9621R~
//          	return;                                            //~9406I~//~9621R~
//          }                                                      //~9406I~//~9621R~
//  		aBTM.setRuleSettingSynchedAll(false/*swOK*/,"");       //~9406I~//~9617R~
//      	UView.showToast(R.string.Err_TrySendFromServer);       //~9404M~//~9406R~
            String props=Pprop.toString(Pcmt);                     //~9406I~
    		aBTM.setRuleSettingSynchedAll(false/*swOK*/,"");       //~9406R~
            ctrSent=aBTM.sendMsgToServerProp(GCM_SETTING,props);           //~9406R~//~9A29R~
            if (ctrSent==0)                                        //~9A29I~
		        tvShowStatus.setText(Utils.getStr(R.string.Info_RuleSyncNoConnection));//~9A29I~
            return;                                                //~9404M~
        }                                                          //~9404M~
//      AG.aBTMulti.sendMsgToAllClientProp(GCM_SETTING,Pcmt,Pprop);//~9404R~
//  	BTMulti.setRuleOutOfSynch();                               //~9709I~//~9B25R~
        String props=Pprop.toString(Pcmt);                         //~9404I~
//      ctrSent=aBTM.sendMsgToAllClientProp(GCM_SETTING,props);     //~9404I~//~9406R~//~9A29R~//~9B25R~
//      ctrSent=aBTM.sendMsgToAllClientPropNotSynched(GCM_SETTING,props);//~9B25R~
        Point p=aBTM.sendMsgToAllClientPropNotSynched(GCM_SETTING,props);//~9B25I~
//  	BTMulti.setRuleOutOfSynch();                               //~9405I~//~9709R~
	    aBTM.setLockRuleSetting(false);	//release on server        //~9406R~
//  	showSyncStatusReset(ctrSent);                                     //~9406I~//~9A29R~//~9B25R~
    	showSyncStatusReset(p);                                    //~9B25I~
    }                                                              //~9404I~
//    //*******************************************************      //~9826I~//~9901R~
//    //*on Server,send setting to all client                        //~9826I~//~9901R~
//    //*******************************************************      //~9826I~//~9901R~
//    public static void sendHistoryRule(String Pfnm,String Pcmt,Prop Pprop)//~9826R~//~9901R~
//    {                                                              //~9826I~//~9901R~
//        if (Dump.Y) Dump.println("SettingDlg.sendHistoryRule Pfnm="+Pfnm+",Pcmt="+Pcmt);//~9826I~//~9901R~
//        String props=Pprop.toString(Pcmt)+"\n"+HISTORY_FILENAME+Pfnm+"\n";//~9826R~//~9901R~
//        AG.aBTMulti.sendMsgToAllClientProp(GCM_SETTING_HISTORY,props);//~9826I~//~9901R~
//    }                                                              //~9826I~//~9901R~
    //*******************************************************      //~9406I~
    //*on Server,send All Synched to all client                    //~9406I~
    //*******************************************************      //~9406I~
    protected static void notifySynched(boolean PswOK,String PsyncDate)//~9406R~
    {                                                              //~9406I~
        if (Dump.Y) Dump.println("SettingDlg.notifySync swOK="+PswOK+",PsyncDate="+PsyncDate);//~9406I~//~9616R~
        String msg=(PswOK ? "1" : "0")+MSG_SEP+PsyncDate;          //~9406I~
        AG.aBTMulti.sendMsgToAllClient(true/*swApp*/,GCM_SETTING_SYNC,msg);//~9406I~
    }                                                              //~9406I~
    //*******************************************************      //~9617I~
    //*on Client Setting was changed                               //~9617I~
    //*******************************************************      //~9617I~
    protected static void notifyOutOfSyncToServer(String PsyncDate)//~9617I~
    {                                                              //~9617I~
        if (Dump.Y) Dump.println("SettingDlg.notifyOutOfSyncToServer PsyncDate="+PsyncDate);//~9617I~
        String msg="0"/*swNG*/+MSG_SEP+PsyncDate;                  //~9617I~
        AG.aBTMulti.sendMsgToServer(true/*swApp*/,GCM_SETTING_SYNC,msg);//~9617I~
    }                                                              //~9617I~
    //*******************************************************      //~v@@@I~
    protected void saveProperties()                                //~v@@@R~
    {                                                              //~v@@@I~
	    saveProperties(false/*swReceived*/);                       //~9621I~
    }                                                              //~9621I~
    //*******************************************************      //~9621I~
    protected void saveProperties(boolean PswReceived)             //~9621I~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("SettingDlg.saveProperties swReceived="+PswReceived);     //~9616I~//~9621R~
        curProp.savePropDataFile(propCmt);                         //~v@@@R~
	    if (swRuleSetting)                                         //~9622I~
	        if (AG.aBTMulti!=null)                                     //~9621I~//~9622R~
    	        AG.aBTMulti.saveProperties(PswReceived,curProp.getParameter(getKeyRS(RSID_SYNCDATE),""));//~9621R~//~9622R~
    }                                                              //~v@@@I~
    //*******************************************************      //~9826R~
    //*from History                                                //~9826I~
    //*******************************************************      //~9826I~
    public static void saveProperties(String Pfnm,String Pcmt)     //~9826R~
    {                                                              //~9826R~
        if (Dump.Y) Dump.println("SettingDlg.saveProperties Pfnm="+Pfnm+",Pcmt="+Pcmt);//~9826R~
        AG.ruleProp.saveProperties(Pfnm,Pcmt);                     //~9826R~
    }                                                              //~9826R~
    //**************************************                       //~9616I~
    //* when agreed                                                //~9616I~
    //**************************************                       //~9616I~
	protected void saveReceived()                                  //~9616R~
    {                                                              //~9616I~
        if (Dump.Y) Dump.println("SettingDlg.saveReceived");       //~9616I~
        saveProperties(true/*swReceived*/);   //save to current.rule                 //~9616I~//~9621R~
        AG.ruleProp.resetProperties(curProp);   //replace Properties of AG.ruleProp.P by curProp.P//~9616I~
        AG.ruleSyncDate=AG.ruleProp.getParameter(getKeyRS(RSID_SYNCDATE),"Unknown");//~9616I~//~9A31R~
        if (Dump.Y) Dump.println("SettingDlg.saveReceived AG.ruleSyncDate="+AG.ruleSyncDate);//+1319I~
    }                                                              //~9616I~
    //*******************************************************      //~v@@@I~
    protected int updateProp(String Pkey,int Pnewval)              //~v@@@R~
    {                                                              //~v@@@I~
    	int rc=0;                                                  //~v@@@I~
        if (Pnewval<0)                                             //~v@@@I~
        	return rc;                                             //~v@@@I~
    	int oldval=curProp.getParameter(Pkey,-1);                  //~v@@@R~
        if (oldval!=Pnewval)                                       //~v@@@I~
        {                                                          //~v@@@I~
	        curProp.setParameter(Pkey,Pnewval);                    //~v@@@R~
            rc=1;                                                  //~v@@@I~
        }                                                          //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~9404I~
    protected int updateProp(String Pkey,String Pnewval)           //~9404I~
    {                                                              //~9404I~
    	int rc=0;                                                  //~9404I~
    	String oldval=curProp.getParameter(Pkey,"");               //~9404I~
		if (Dump.Y) Dump.println("SettingDlg.updateProp old="+oldval+",new="+Pnewval);//~9405I~
        if (oldval.compareTo(Pnewval)!=0)                          //~9404I~
        {                                                          //~9404I~
	        curProp.setParameter(Pkey,Pnewval);                    //~9404I~
            rc=1;                                                  //~9404I~
        }                                                          //~9404I~
        return rc;                                                 //~9404I~
    }                                                              //~9404I~
	//**********************************                           //~v@@@I~
	@Override //FileDialogI                                        //~v@@@I~
    public boolean fileDialogSaveCB(String Pfullpath)              //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("SettingDlg.fileDialogSaveCB fnm="+Pfullpath+",swSaveDialog2Properties="+swSaveDialog2Properties);//~v@@@R~//~9405R~//~9B08R~//~9B09R~
        if (!swSaveDialog2Properties)                              //~9B09I~
		    dialog2Properties(Pfullpath);	//update curProp                   //~v@@@I~//~9405R~//~9B09R~
        swSaveDialog2Properties=false;                             //~9B09I~
//      return curProp.saveProperties(Pfullpath,propCmt);          //~v@@@R~//~9B08R~
        boolean rc=curProp.saveProperties(Pfullpath,propCmt);      //~9B08I~
        if (rc)                                                    //~9B08I~
        {                                                          //~9B08I~
	        String syncDate=curProp.getParameter(getKeyRS(RSID_SYNCDATE),"0");//~9B08R~
			if (Dump.Y) Dump.println("SettingDlg.fileDialogSave syncDate="+syncDate);//~9B08I~
        	UFile.setLastModified(Pfullpath,Long.parseLong(syncDate,16));//~9B08R~
        }                                                          //~9B08I~
		if (Dump.Y) Dump.println("SettingDlg.fileDialogSave rc="+rc+",AG.ruleSyncDate="+AG.ruleSyncDate);//~9B08I~
        return rc;                                                 //~9B08I~
    }                                                              //~v@@@I~
	//*******************************************************************//~9B09I~
	//*return syncdate by yyyy/MM/dd-HH.mm.ss                      //~9B09I~
	//*******************************************************************//~9B09I~
    public String preFileDialogSaveCB(String Pfullpath)            //~9B09I~
    {                                                              //~9B09I~
		if (Dump.Y) Dump.println("SettingDlg.prefileDialogSaveCB fnm="+Pfullpath);//~9B09I~
	    dialog2Properties(Pfullpath);	//update curProp           //~9B09I~
        swSaveDialog2Properties=true;                              //~9B09I~
	    String syncDate=curProp.getParameter(getKeyRS(RSID_SYNCDATE_FORMATTED),"0");//~9B09I~
		if (Dump.Y) Dump.println("SettingDlg.fileDialogSave rc="+syncDate);//~9B09I~
        return syncDate;                                           //~9B09I~
    }                                                              //~9B09I~
	//**********************************                           //~v@@@I~
	@Override //FileDialogI                                        //~v@@@I~
    public boolean fileDialogLoadCB(String Pfullpath)              //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("SettingDlg.fileDialogLoadCB fnm="+Pfullpath);//~v@@@R~//~9405R~

        Prop p=checkValidity(Pfullpath);
        if (p==null)//load result chk      //~v@@@R~
        {                                                          //~v@@@I~
			Alert.showMessage(title,Utils.getStr(R.string.ErrInvalidPropertyFile,Pfullpath));//~v@@@R~
            return false;                                          //~v@@@R~
        }                                                          //~v@@@I~
    	properties2Dialog(p);	//update dialog                    //~v@@@I~
        return true;                                               //~v@@@R~
    }                                                              //~v@@@I~
    //*******************************************************      //~9406I~
    public void repaintOKNG()                                      //~9406I~
    {                                                              //~9406I~
        if (Dump.Y) Dump.println("SettingDlg.repaintOKNG");        //~9406I~
        AG.activity.runOnUiThread(                                 //~9406I~
            new Runnable()                                         //~9406I~
            {                                                      //~9406I~
                @Override                                          //~9406I~
                public void run()                                  //~9406I~
                {                                                  //~9406I~
                    try                                            //~9406I~
                    {                                              //~9406I~
    				    if (Dump.Y) Dump.println("SettingDlg.repaintOKNG runonUiThread.run");//~9406I~
                    	updateOKNG();                              //~9406I~
                    }                                              //~9406I~
                    catch(Exception e)                             //~9406I~
                    {                                              //~9406I~
                        Dump.println(e,"SettingDlg.repaintOKNG.run");//~9406I~
                    }                                              //~9406I~
                }                                                  //~9406I~
            }                                                      //~9406I~
                                  );                               //~9406I~
                                                                   //~9406I~
    }                                                              //~9406I~
    //*******************************************************      //~9406I~
    private void updateOKNG()                                      //~9406I~
    {                                                              //~9406I~
        if (Dump.Y) Dump.println("SettingDlg.updateOKNG");         //~9406I~
    	showSyncStatus();                                          //~9406I~
    }                                                              //~9406I~
}//class                                                           //~v@@@R~
