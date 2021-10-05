//*CID://+vae9R~:                             update#=   45;       //~vae9R~
//*****************************************************************
//*Audio picker
//*****************************************************************
//2021/09/19 vae9 1ak2(access external audio file) for BTMJ        //~vae9I~
//1ak3 2021/09/10 picker(ACTION_PICK) for API30
//*****************************************************************
package com.btmtest.dialog;
import static com.btmtest.StaticVars.AG;                           //~1ak3I~
import com.btmtest.R;                                              //~1ak3I~
import com.btmtest.utils.Alert;                                    //~1ak3I~
import com.btmtest.utils.Dump;                                     //~1ak3I~
import com.btmtest.utils.Utils;                                    //~1ak3I~
                                                                   //~1ak3I~
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import android.net.Uri;

import android.content.Intent;
import android.bluetooth.BluetoothDevice;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Dialog;
import android.graphics.Color;
import android.view.WindowManager;
import android.os.Bundle;
import android.content.DialogInterface;                            //~1ak3I~

import com.btmtest.dialog.AxeDlg;                                  //~1ak3R~
import com.btmtest.utils.UMediaStore;
import com.btmtest.utils.UMediaStore.AudioFile;
import com.btmtest.utils.UView;
import com.btmtest.gui.UListView;
import com.btmtest.gui.UButton;

import static com.btmtest.game.GConst.*;

//public class UPicker extends AxeDlg                                //~1ak3R~//~vae9R~
public class UPicker extends UFDlg                                 //~vae9I~
{
	private   static final String CN="UPicker.";
	private   static final String HELPFILE="UPicker";
	private   static final int LAYOUTID=R.layout.upicker;
	private   static final int LAYOUTID_LISTROW=R.layout.textrowlist_audio;
	private   static final int RID_LISTURL=R.id.UrlList;
	private   static final int TITLEID=R.string.Title_Picker;
	private   static final int ROWID_TITLE=R.id.Audio_Title;
	private   static final int ROWID_ARTIST=R.id.Audio_Artist;
	private   static final int ROWID_DURATION=R.id.Audio_Duration;
//  private   static final int COLOR_BG_LIST=Color.rgb(0xc8,0xff,0xc8);  //orange//~vae9R~
	private   static final int COLOR_BG_LIST=AG.getColor(R.color.upicker_list);//~vae9I~

    private ListMedia LM;   	//listview media file
//  private View layoutView;                                       //~vae9R~
    private UPickerI callback;                                                               //~1A6fI]~
    private UMediaStore UMS;                                                               //~1A6fI]~
    private Uri uriDir,uriPicked;
    private TextView tvUriDir;
    private ArrayList<AudioFile> audioList;
    private boolean swOK;
//**********************************
    public interface UPickerI
    {
		void itemSelected(Uri PitemUri);
    }
    //******************************************
//  public UPicker(int PlayoutID)                                  //~1ak3R~
    public UPicker()                                               //~1ak3I~
	{
//      super(PlayoutID);                                          //~1ak3R~
	    if (Dump.Y) Dump.println(CN+"constructor exit");
	}
    //******************************************
    public static UPicker newInstance(Uri PuriDir,UMediaStore PumediaStore)
    {
        if (Dump.Y) Dump.println(CN+"newInstancer uriDir="+PuriDir);//~1ak3I~
//  	UPicker dlg=new UPicker(LAYOUTID);                         //~1ak3R~
    	UPicker dlg=new UPicker();                                 //~1ak3I~
    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                     //~vae9I~
                    UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,TITLEID/*helpTitleID*/,HELPFILE);//~vae9I~
        dlg.UMS=PumediaStore;
        dlg.callback=PumediaStore;
        dlg.uriDir=PuriDir;
//      String title=Utils.getStr(TITLEID);                        //~1ak3R~
//      AxeDlg.newInstance(dlg,TITLEID,LAYOUTID,HELPFILE);         //~1ak3I~//~vae9R~
//  	dlg.showDialog();                                          //~vae9R~
        return dlg;
    }
    //******************************************                   //~1ak3R~
    public void showDialog()                                       //~1ak3R~
    {                                                              //~1ak3R~
//      String title=Utils.getStr(TITLEID);                        //~1ak3R~
//      showDialog(title);                                         //~1ak3R~
//      super.showDialog(null);                                    //~1ak3I~//~vae9R~
        show();                                                    //~vae9I~
    }                                                              //~1ak3R~
	//***********************************************************************************
    @Override
//  protected void setupDialogExtend(ViewGroup PlayoutView)        //~1ak3R~
//  protected void setupDialogExtend(View PlayoutView)             //~1ak3I~//~vae9R~
    protected void initLayout(View PlayoutView)                    //~vae9I~
    {
    	super.initLayout(PlayoutView);                             //~vae9I~
        tvUriDir=(TextView)    UView.findViewById(layoutView,R.id.tvUriDir);
        LM=new ListMedia(this,layoutView,RID_LISTURL,LAYOUTID_LISTROW);
        setDir();
        setListViewData();
        LM.setBackground(COLOR_BG_LIST);
    }
    //*****************************************
    private void setDir()
    {
        if (Dump.Y) Dump.println(CN+"setDir uriDir="+uriDir);
        tvUriDir.setText(uriDir.toString());
    }
    //*****************************************
    private void setListViewData()
    {
        if (Dump.Y) Dump.println(CN+"setListViewData uriDir="+uriDir);
        audioList=UMediaStore.getMemberList(uriDir);
        for (AudioFile af:audioList)
        {
        	LM.add(af.title,af.artist,af.min*60+af.sec);
        }
    }
    //******************************************
    @Override
//  protected void onDismiss()                                     //~1ak3I~
    public void onDismiss(DialogInterface Pdialog)                 //~1ak3I~
    {                                                              //~1ak3I~
    	super.onDismiss(Pdialog);	//callback onDismissDialog from UFDlg//~1ak3I~
    }                                                              //~1ak3I~
    @Override                                                      //~1ak3I~
    public void onDismissDialog()                                  //~1ak3I~
	{
        if (Dump.Y) Dump.println(CN+"onDismissDialog swOK="+swOK); //~1ak3R~
//  	if (!swOK)       //reset even when ok                      //+vae9R~
        	UMS.itemCanceled();
    }
    //******************************************
    @Override
//  protected boolean onClickOK()                                  //~1ak3R~
    protected void onClickOK()                                     //~1ak3I~
	{
        if (Dump.Y) Dump.println(CN+"onClickOK");                  //~vae9I~
    	int selected=getSelected();
//      boolean rc=false;                                          //~1ak3R~
        if (selected>=0)
        {
    		uriPicked=UMediaStore.getMemberUri(uriDir,audioList.get(selected).id);
    		callback.itemSelected(uriPicked);
            swOK=true;
//          rc=true;                                               //~1ak3R~
	    	dismiss();                                             //~1ak3I~
        }
//  	return rc;    //dismiss                                    //~1ak3R~
    }
//    //**********************************                         //~1ak3R~
//    @Override                                                    //~1ak3R~
//    protected boolean onClickHelp()                              //~1ak3R~
//    {                                                            //~1ak3R~
//        if (Dump.Y) Dump.println(CN+"onClickHelp");              //~1ak3R~
//        HelpDialog.newInstance(TITLEID,HELPFILE).showDlg();      //~1ak3R~
//        return false;   //no dismiss                             //~1ak3R~
//    }                                                            //~1ak3R~
    //******************************************
	private int getSelected()
    {
	    int idx=LM.getValidSelectedPos();
        if (idx==-1)
        {
    		UView.showToast(R.string.ErrNotSelectedAudio);         //~vae9R~
        }
        return idx;
    }
    //**********************************************************************
    public void onItemClicked(int Ppos,int PoldSelected)
    {
    	if (Dump.Y) Dump.println(CN+"onItemClicked Ppos="+Ppos+",PoldSelected="+PoldSelected);
    	Uri uriClicked=UMediaStore.getMemberUri(uriDir,audioList.get(Ppos).id);
        UMS.itemClicked(uriClicked);
    }
    //**********************************************************************
    //**********************************************************************
    //**********************************************************************
  	class ListMedia extends UListView
	{
    	UPicker upicker;
    //*****************
        ListMedia(UPicker Pupicker,View Playout,int Plvid,int Prowresid)
        {
            super(Playout,Plvid,Prowresid,null/*listener*/,UListView.CHOICEMODE_SINGLE);
            upicker=Pupicker;
        }
    //**********************************************************************
        @Override
        public void onItemClicked(int Ppos,int PposSelected)
        {
        	upicker.onItemClicked(Ppos,PposSelected);
        }
    //**********************************************************************
        @Override
        public View getViewCustom(int Ppos, View Pview, ViewGroup Pparent)
        {
        //*******************
            if (Dump.Y) Dump.println(CN+"ListMedia.getViewCustom Ppos="+Ppos+",CheckedItemPos="+((ListView)Pparent).getCheckedItemPosition());
            View v=Pview;
            if (v == null)
			{
                v=AG.inflater.inflate(rowId/*super*/,null);
            }
            TextView v1=(TextView)v.findViewById(ROWID_TITLE);
            TextView v2=(TextView)v.findViewById(ROWID_ARTIST);
            TextView v3=(TextView)v.findViewById(ROWID_DURATION);
            UListViewData ld=arrayData.get(Ppos);
            v1.setText(ld.itemtext);
            if (Ppos==selectedpos)
            {
                v1.setBackgroundColor(bgColorSelected);
                v1.setTextColor(bgColor);
            }
            else
            {
                v1.setBackgroundColor(bgColor);
                v1.setTextColor(ld.itemcolor);
            }
            String artist;
            if (ld.itemtext2==null)
            	artist="";
            else
            	artist=ld.itemtext2;
            if (Dump.Y) Dump.println(CN+"ListMedia.getViewCustom itemtext="+ld.itemtext+",itemtext2="+ld.itemtext2);
            v2.setText(artist);
            int sec=ld.itemint;
            String duration=sec/60+"."+sec%60;
	        v3.setText(duration);
            return v;
        }
	}//class
}//class
