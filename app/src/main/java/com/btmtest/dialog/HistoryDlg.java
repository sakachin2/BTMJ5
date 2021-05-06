//*CID://+va87R~:                             update#=  587;       //+va87R~
//*****************************************************************//~v101I~
//2021/04/13 va87 show B/S limited to a group                      //+va87I~
//2021/04/06 va7a add function of show balance sheet               //~va7aI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Color;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.HistoryData;
import com.btmtest.game.Status;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.UListView;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Alert2;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UButton;                                    //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import static com.btmtest.BT.Members.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.History.*;                          //~9614I~
import static com.btmtest.game.HistoryData.*;
import static com.btmtest.gui.UListView.*;

public class HistoryDlg extends FileDialog                //~v@@@R~     //~9613R~//~9614R~
            implements UCheckBox.UCheckBoxI                        //~0114I~
//    implements Alert2.AlertI                                     //~0112R~
{                                                                  //~2C29R~
    private static final int TITLEID=R.string.Title_HistoryDlg;    //~v@@@R~//~9613M~
    private static final int HELP_TITLEID=TITLEID;                 //~9614I~
    private static final String HELPFILE="HistoryDlg";           //~9614I~//~9C13R~
    private static final int LAYOUTID=R.layout.historydlg;         //~9614I~
    private static final String STR_FILTER=EXT_HISTORY;            //~9614R~
    private static final int LISTVIEW_ROW_LAYOUTID=R.layout.textrowlist_historydlg;//~v@@@I~//~9614R~
    private static final int COLOR_INTERRUPTED=Color.argb(0xff,0xff,0x00,0x00);//~9824I~
    private static final int COLOR_NOT_INTERRUPTED=Color.argb(0xff,0x00,0x00,0x00);//~9824I~
                                                                   //~9613I~
    private static final int UNIT_SHIFT=-100;                      //~0113I~
    private static final int UCBP_MULTISELECT=1;                   //~0114I~
                                                                   //~0113I~
//  private	String fpathHistory;                                   //~9614I~//~9615R~
//  TextView tvFolderName;                                         //~9615I~//~0114R~
    private boolean swServer;                                      //~9825I~
    private HistoryData reloadHD;                                  //~9825I~
//  private static HistoryData receivedHD;                         //~9825I~//~0111R~
    private UCheckBox cbMultiSelect;                               //~0114R~
    private boolean swMultiSelect;//~0114I~
    //******************************************                   //~v@@@R~
    public HistoryDlg()                                            //~v@@@R~//~9613R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("HistoryDlg.defaultConstructor"); //~9614R~
        AG.aHistoryDlg=this;                                       //~9825I~
        if (Dump.Y) Dump.println("HistoryDlg.defaultConstructor this="+Utils.toString(AG.aHistoryDlg));//~0112I~
    }                                                              //~v@@@R~
    //*****************************                                //~9825I~
    @Override                                                      //~9825I~
    protected void onDismissDialog()                               //~9825I~
    {                                                              //~9825I~
        if (Dump.Y) Dump.println("HistoryDlg:onDismissDialog");    //~9825I~
        AG.aHistoryDlg=null;                                       //~9825I~
    }                                                              //~9825I~
    //******************************************                   //~9614I~
    public static HistoryDlg newInstance()                         //~9614R~
    {                                                              //~v@@@I~//~9614I~
    	HistoryDlg dlg=new HistoryDlg();                           //~v@@@I~//~9614R~
    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,//~v@@@I~             //~9614I~
                    UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN,HELP_TITLEID,HELPFILE);//~v@@@I~//~9614I~//~9C13R~
    	return dlg;                                               //~v@@@I~//~9614I~
    }                                                              //~v@@@I~//~9614I~
    //******************************************                   //~v@@@M~
    @Override
    protected void getComponent(View Playoutview)                          //~v@@@R~//~9614R~
    {                                                              //~v@@@M~
        filterName=STR_FILTER;                                     //~9614M~
        tvFilter          =(TextView)UView.findViewById(Playoutview,R.id.Filter);//~v@@@R~//~9614R~
        tvFileType        =(TextView)UView.findViewById(Playoutview,R.id.FileType);//~v@@@I~//~9614I~
        tvLabelFilter     =(TextView)UView.findViewById(Playoutview,R.id.LabelFilter);//~v@@@I~//~9614I~
        cbMultiSelect     =new UCheckBox(Playoutview,R.id.cbMultiSelect);//~0114I~
        cbMultiSelect.setListener(this,UCBP_MULTISELECT);          //~0114I~
//        btnSelectAll      =UButton.bind(Playoutview,R.id.SelectAll,this);                  //~1A30I~//~v@@@R~//~9614I~//~9824R~
//        btnDeSelectAll    =UButton.bind(Playoutview,R.id.DeSelectAll,this);//~v@@@R~//~9614I~//~9824R~
////      btnOpen           =UButton.bind(Playoutview,R.id.Open,this);//~v@@@R~//~9614I~//~9615R~//~9824R~
//        btnDelete         =UButton.bind(Playoutview,R.id.Delete,this);//~v@@@R~//~9614I~//~9824R~
        UButton.bind(Playoutview,R.id.Send,this);             //~9824I~//~9825R~
        UButton.bind(Playoutview,R.id.SelectAll,this);             //~9825I~
        UButton.bind(Playoutview,R.id.DeSelectAll,this);           //~9824I~
        UButton.bind(Playoutview,R.id.Reload,this);                //~9824I~
        UButton.bind(Playoutview,R.id.Delete,this);                //~9824I~
        UButton.bind(Playoutview,R.id.btnHistoryBS,this);          //~va7aI~
        containerFilename=UView.findViewById(Playoutview,R.id.FileNameContainer);//~v@@@I~//~9614I~
//      lvFilename=new UListView(Playoutview,R.id.FileList,LISTVIEW_ROW_LAYOUTID,this,UListView.CHOICEMODE_MULTIPLE);//~v@@@R~//~9614I~//~0114R~
        lvFilename=new UListView(Playoutview,R.id.FileList,LISTVIEW_ROW_LAYOUTID,this,UListView.CHOICEMODE_SINGLE);//~0114I~
//      tvFolderName      =(TextView)UView.findViewById(Playoutview,R.id.FolderName);//~9615R~//~0114R~
        setInitialValue();                                         //~v@@@I~//~9614I~
    }                                                              //~v@@@M~
	//************************************                         //~v@@@I~//~9614I~
    @Override                                                      //~9614I~
    protected void setInitialValue()                                 //~v@@@I~//~9614I~
    {                                                              //~v@@@I~//~9614I~
     	if (Dump.Y) Dump.println("HistoryDlg.setInitialValue");    //~v@@@I~//~9614I~
//      fpathHistory=AG.aHistory.pathHistory;                      //~9614I~//~9615R~
        workDirSD=AG.aHistory.workDirSD;                           //~9614I~
        pathDataDir=AG.aHistory.pathDataDir;                       //~9614I~
        filenameFilter=getFilter();                                //~9824I~
        swSD=AG.aHistory.swSD;                                     //~9614I~
        setButtonVisibility();                                      //~v@@@I~//~9614I~
        setTitle();                                                //~0114R~
    }                                                              //~v@@@I~//~9614I~
	//************************************                         //~0114I~
    private void setTitle()                                        //~0114I~
    {                                                              //~0114I~
    	String path=swSD ? workDirSD : pathDataDir;                //~0114I~
        if (Dump.Y) Dump.println("HistoryDlg:setTitle path="+path);//~0114I~
//      Spanned s= Html.fromHtml(Utils.getStr(R.string.Title_HistoryDlgFolder,path));//~va40R~
        Spanned s=Utils.fromHtml(Utils.getStr(R.string.Title_HistoryDlgFolder,path));//~va40I~
        getDialog().setTitle(s);                                               //~0114I~
    }                                                              //~0114I~
    //******************************************                   //~9823I~
	@Override	//UFDlg                                            //~9823I~
    protected int getDialogWidth()                                 //~9823I~
    {                                                              //~9823I~
    	int ww=getDialogWidthSmallDevicePortraitFixedRate();       //~9823I~
    	if (Dump.Y) Dump.println("HistoryDlg.getDialogWidth swSmallDevice="+AG.swSmallDevice+",ww="+ww);//~9823I~//~va7aR~
        return ww;                                                 //~9823I~
    }                                                              //~9823I~
	//************************************                         //~v@@@I~//~9614I~
    @Override                                                      //~9614I~
    protected void setButtonVisibility()                             //~v@@@I~//~9614I~
    {                                                              //~v@@@I~//~9614I~
	    containerFilename.setVisibility(View.GONE);	//avoid intercept by Dialog.UiThread;current dir is on entry "./"//~v@@@I~//~9614R~
        tvLabelFilter.setVisibility(View.VISIBLE);             //~v@@@R~//~9614R~
        if (swSD)                                                  //~v@@@I~//~9614R~
            tvFileType.setText(Utils.getStr(R.string.FileTypeSD)); //~v@@@I~//~9614I~
        else                                                       //~v@@@I~//~9614R~
              tvFileType.setText(Utils.getStr(R.string.FileTypeData));//~v@@@I~//~9614R~
    }                                                              //~v@@@I~//~9614I~
	//************************************                         //~9615I~
    @Override                                                      //~9615I~
    protected void updateList()                                    //~9615I~
    {                                                              //~9615I~
        if (Dump.Y) Dump.println("HistoryDlg:updateList");         //~9615I~
        super.updateList();                                        //~9615I~
        if (namelist==null)                                        //~9615I~
        	return;                                                //~9615I~
        getHistoryData();                                          //~9615I~
//      tvFolderName.setText(dirName);                             //~9615I~//~0114R~
//      setTitle();                                                //~0114I~
    }                                                              //~9615I~
    //******************************************                   //~v@@@I~//~9614I~
    @Override                                                      //~v@@@I~//~9614I~
    public void onClickOther(int Pbuttonid)                        //~v@@@I~//~9614I~
	{                                                              //~v@@@I~//~9614I~
        if (Dump.Y) Dump.println("HistoryDlg:onClickOther id="+Pbuttonid);//~v@@@I~//~9614I~//~9615R~
    	getSelectedList();                                         //~v@@@I~//~9614I~
        switch(Pbuttonid)                                          //~v@@@I~//~9614I~
        {                                                          //~v@@@I~//~9614I~
            case R.id.SelectAll:                                   //~v@@@M~//~9614I~
                onClickSelectAll();                                           //~1Ah8I~//~v@@@M~//~9614I~
                break;                                             //~v@@@M~//~9614I~
            case R.id.DeSelectAll:                                 //~v@@@M~//~9614I~
                onClickDeSelectAll();                                         //~1Ah8I~//~v@@@M~//~9614I~
                break;                                             //~v@@@M~//~9614I~
            case R.id.btnHistoryBS:                                //~va7aI~
                onClickHistoryBS();                                //~va7aI~
                break;                                             //~va7aI~
            case R.id.Reload:        //Re-Start                          //~v@@@I~//~9614I~//~9615R~//~9824R~//~0111R~
                onClickReload();                                     //~v@@@R~//~9614I~//~9615R~//~9824R~
                break;                                                 //~v@@@I~//~9614I~//~9615R~//~9824R~
            case R.id.Send:                                        //~9825I~
                onClickSend();                                     //~9825I~
                break;                                             //~9825I~
            case R.id.Delete:                                          //~v@@@I~//~9614I~
                onClickDelete();                                       //~1403I~//~v@@@R~//~9614I~
                break;                                                 //~v@@@I~//~9614I~
        }//~v@@@I~                                                 //~9614I~
    }                                                              //~1403R~//~9614I~
//    //**********************************************************************//~1403I~//~v@@@M~//~9614I~//~9615R~//~9824R~
//    private void onClickReload()                                 //~1403I~//~v@@@M~//~9614I~//~9615R~//~9824R~
//    {                                                              //~1403I~//~v@@@M~//~9614I~//~9615R~//~9824R~
//        if (Dump.Y) Dump.println("HistoryDlg.onClickLoad")       //~9824R~
//        if (!AG.aUserAction.isServer)                            //~9824R~
//        {                                                        //~9824R~
//            GMsg.drawMsgbar(R.string.                            //~9824R~
//        }                                                        //~9824R~
//        int ctr=multipleSelected.length;                           //~9614I~//~9615R~//~9824R~
//        HistoryData[] hd=new HistoryData[ctr];                     //~9614I~//~9615R~//~9824R~
//        int openctr=0;                                             //~9614I~//~9615R~//~9824R~
//        try                                                        //~9614I~//~9615R~//~9824R~
//        {                                                          //~9614I~//~9615R~//~9824R~
//            if (Dump.Y) Dump.println("HistoryDlg onClickOpen selectedctr="+ctr);//~9614I~//~9615R~//~9824R~
//            for (int ii=0;ii<ctr;ii++)                             //~9614I~//~9615R~//~9824R~
//            {                                                      //~9614I~//~9615R~//~9824R~
//                if (getSelectedFilenameMulti(ii))                  //~9614I~//~9615R~//~9824R~
//                {                                                  //~9614I~//~9615R~//~9824R~
//                    hd[ii]=getHistoryData(selectedFilename,selectedFile);//~9614I~//~9615R~//~9824R~
//                    if (hd[ii]!=null)                              //~9614I~//~9615R~//~9824R~
//                        openctr++;                                 //~9614I~//~9615R~//~9824R~
//                }                                                  //~9614I~//~9615R~//~9824R~
//            }                                                      //~9614I~//~9615R~//~9824R~
//            if (openctr!=0)                                         //~9614I~//~9615R~//~9824R~
//            {                                                      //~9614I~//~9615R~//~9824R~
//                lvFilename.notifyChanged();                        //~9614I~//~9615R~//~9824R~
//            }                                                      //~9614I~//~9615R~//~9824R~
//        }                                                          //~9614I~//~9615R~//~9824R~
//        catch (Exception e)                                        //~9614I~//~9615R~//~9824R~
//        {                                                          //~9614I~//~9615R~//~9824R~
//            Dump.println(e,"HistoryDlg:onClickOpen");              //~9614I~//~9615R~//~9824R~
//        }                                                          //~9614I~//~9615R~//~9824R~
//    }                                                              //~1403I~//~v@@@M~//~9614I~//~9615R~//~9824R~
    //**********************************************************************//~0114I~
    protected void getSelectedList()                               //~0114I~
    {                                                              //~0114I~
    	if (swMultiSelect)                                         //~0114I~
        	super.getSelectedList();                               //~0114I~
        else                                                       //~0114I~
        {                                                          //~0114I~
	    	int pos=lvFilename.getValidSelectedPos();              //~0114I~
            if (pos==-1)                                           //~0114I~
            	multipleSelected=new int[0];                          //~0114I~
            else                                                   //~0114I~
            	multipleSelected=new int[]{pos};                     //~0114I~
        }                                                          //~0114I~
        if (Dump.Y) Dump.println("HistoryDlg.getSelectedList swMultiSelect="+swMultiSelect+",multipleSelect="+Arrays.toString(multipleSelected));//~0114I~
    }                                                              //~0114I~
    //**********************************************************************//~0114I~
    @Override                                                      //~0114I~
    protected void onClickSelectAll()                              //~0114I~
    {                                                              //~0114I~
        if (Dump.Y) Dump.println("HistoryDlg.onClickSelectAll");   //~0114I~
        if (!swMultiSelect)                                        //~0114I~
        {                                                          //~0114I~
		    cbMultiSelect.setState(true);	//listener set COICEMODE_MULTIPLE//~0114I~
        }                                                          //~0114I~
        super.onClickSelectAll();                                  //~0114I~
    }                                                              //~0114I~
    //**********************************************************************//~9825I~
    private void onClickSend()                                     //~9825I~
    {                                                              //~9825I~
        if (Dump.Y) Dump.println("HistoryDlg.onClickSend");        //~9825I~
	    HistoryData hd=chkSelection(false/*PswReload*/);           //~9825I~
        if (hd==null)                                              //~9825I~
        	return;                                                //~9825I~
        doSend(swServer,hd);	//swServer by chkSelection         //~9825I~
    }                                                              //~9825I~
    //**********************************************************************//~1403I~//~v@@@M~//~9614I~//~9615R~//~9824I~
    private void onClickReload()                                 //~1403I~//~v@@@M~//~9614I~//~9615R~//~9824I~
    {                                                              //~1403I~//~v@@@M~//~9614I~//~9615R~//~9824I~
        if (Dump.Y) Dump.println("HistoryDlg.onClickReload");      //~9824R~
	    HistoryData hd=chkSelection(true/*PswReload*/);            //~9825R~
        if (hd==null)                                              //~9825I~
        	return;                                                //~9825R~
        doReload(hd);                                              //~9825I~
    }                                                              //~1403I~//~v@@@M~//~9614I~//~9615R~//~9824I~
    //**********************************************************************//~9825I~
    private HistoryData chkSelection(boolean PswReload)            //~9825R~
    {                                                              //~9825I~
        if (Dump.Y) Dump.println("HistoryDlg.chkSelection");       //~9825I~
        Members members=AG.aBTMulti.BTGroup;                       //~9825I~
        int role=-1;                                               //~9825I~
        if (members!=null)                                         //~9825I~
 			role=members.getMemberRole();                          //~9825I~
//        if (role==-1)                                              //~9825I~//~va66R~
//        {                                                          //~9825I~//~va66R~
//            UView.showToast(R.string.Err_ReloadNooneConnected);    //~9825I~//~va66R~
//            return null;                                           //~9825R~//~va66R~
//        }                                                          //~9825I~//~va66R~
//        if (role!=MS_SERVER)                                     //~9825I~
//        {                                                        //~9825I~
//            UView.showToast(R.string.Err_ReloadNotServer);       //~9825I~
//            return;                                              //~9825I~
//        }                                                        //~9825I~
        swServer=(role==MS_SERVER);                                //~9825R~//~va66R~
        int ctr=multipleSelected.length;                           //~9825I~
    	if (ctr!=1)                                                //~9825I~
        {                                                          //~9825I~
        	UView.showToast(R.string.Err_ReloadSelectOne);         //~9825I~
            return null;                                           //~9825R~
        }                                                          //~9825I~
        HistoryData hd;                                            //~9825I~
        try                                                        //~9825I~
        {                                                          //~9825I~
            if (Dump.Y) Dump.println("HistoryDlg.chkSelection selectedctr="+ctr);//~9825I~
            getSelectedFilenameMulti(0);                           //~9825I~
            hd=getHistoryData(selectedFilename,selectedFile);      //~9825I~
            if (hd==null)                                          //~9825I~
            {                                                      //~9825I~
        		UView.showToast(Utils.getStr(R.string.Err_HistoryReadFailed,selectedFilename));//~9825I~
            	return null;                                       //~9825R~
            }                                                      //~9825I~
            if (PswReload)                                         //~9825I~
                if (!isInterrupted(hd))                            //~9825R~
                {                                                  //~9825R~
                    UView.showToast(R.string.Err_ReloadNotInterrupted);//~9825R~
                    return null;                                        //~9825R~
                }                                                  //~9825R~
			int role2=chkMemberTrainingMode(hd,role);              //~va66R~
            if (role2==-1)               //unmatch member          //~va66R~
            	return null;                                       //~va66R~
          if (role2==0)     //history is not training mode         //~va66R~
          {                                                        //~va66I~
			if (!chkMember(hd))                                    //~9825I~
            	return null;                                      //~9825I~
          }                                                        //~va66I~
        }                                                          //~9825I~
        catch (Exception e)                                        //~9825I~
        {                                                          //~9825I~
            Dump.println(e,"HistoryDlg:chkSelection");             //~9825I~
            return null;                                           //~9825R~
        }                                                          //~9825I~
        return hd;                                                 //~9825R~
    }                                                              //~9825I~
    //**********************************************************************//~9614I~
    private HistoryData getHistoryData(String Pfname,File Pfh)     //~9614I~
    {                                                              //~9614I~
    	HistoryData hd=AG.aHistory.getHistoryData(Pfname,Pfh);      //~9614I~
        return hd;                                            //~9614R~
    }                                                              //~9614I~
    //**********************************************************************//~9615I~
    private String[][] getHistoryData(String Pfname)               //~9615I~
    {                                                              //~9615I~
    	HistoryData hd=AG.aHistory.getHistoryData(Pfname);         //~9615I~
        if (hd==null)                                              //~9615I~
        	return null;                                           //~9615I~
	    String[][] hds=hd.HD;                                      //~9615R~
        return hds;//~9615I~
    }                                                              //~9615I~
    //*****************************************************************//~9615I~
    private void getHistoryData()                                  //~9615I~
    {                                                              //~9615I~
    	for (int ii=0;ii<namelist.length;ii++)                     //~9615I~
        {                                                          //~9615I~
        	File f=filelist[name2file[ii]];                        //~9615I~
            if (Dump.Y) Dump.println("HistoryDlg.getHistoryData fnm="+namelist[ii]+",file="+f.getName());//~9615I~
	        getHistoryData(namelist[ii],f);                        //~9615I~
        }                                                          //~9615I~
    }                                                              //~9615I~
    //*****************************************************************//~1A21I~//~9614I~
    @Override                                                      //~9614I~
    protected Integer[]  sortFileList()                                   //~1A21I~//~9614I~
    {                                                              //~1A21I~//~9614I~
    	int filectr=filelist.length;                               //~1A21I~//~9614I~
        if (Dump.Y) Dump.println("HistoryDlg.sortFileList namelist="+Arrays.toString(namelist));//~9615I~
    	Integer[] idx=new Integer[filectr];                        //~1A21I~//~9614I~
        for (int ii=0;ii<filectr;ii++)                             //~1A21I~//~9614I~
        {                                                          //~9615I~
        	idx[ii]=ii;                                            //~1A21I~//~9614I~
        }                                                          //~9615I~
        if (Dump.Y) Dump.println("HistoryDlg.sortFileList ctr="+filectr);//~v@@@I~//~9614I~
    	Arrays.sort(idx,new DataComparator());                      //~1A21I~//~9614I~
        return idx;                                                //~1A21I~//~9614I~
    }                                                              //~1A21I~//~9614I~
    //**********************************************************                           //~1A21I~//~9614I~
    //*reverse seq                                                 //~9614I~
    //**********************************************************   //~9614I~
    class DataComparator implements Comparator<Integer>                      //~1A21I~//~9614I~
    {                                                              //~1A21I~//~9614I~
        public int compare(Integer P1,Integer P2)                     //~1A21I~//~9614I~
        {                                                          //~1A21I~//~9614I~
        	int rc;                                              //~1A21I~//~9614I~
        //*****************************                            //~1A21I~//~9614I~
//      	int i1=P1.intValue();                       //~1A21I~  //~9614I~//~9615R~
//      	int i2=P2.intValue();                       //~1A21I~  //~9614I~//~9615R~
//      	File f1=filelist[i1];                                  //~1A21I~//~9614I~//~9615R~
//      	File f2=filelist[i2];                                  //~1A21I~//~9614I~//~9615R~
        	File f1=filelist[P1];                                  //~9615I~
        	File f2=filelist[P2];                                  //~9615I~
            if (Dump.Y) Dump.println("HistoryDlg compare f1="+P1+"="+f1.getName()+",f2="+P2+"="+f2.getName());//~1A4aI~//~9614I~
            if(f1.isDirectory())                                   //~1A21I~//~9614I~
            	if (f2.isDirectory())                              //~1A21I~//~9614I~
                {	                                               //~1A21I~//~9614I~
                	rc=-f1.getName().compareTo(f2.getName());       //~1A21R~//~9614I~
                }                                                  //~1A21I~//~9614I~
                else                                               //~1A21I~//~9614I~
                	rc=-1;	//dir first                            //~1A21I~//~9614I~
            else                                                   //~1A21I~//~9614I~
            	if (f2.isDirectory())                              //~1A21I~//~9614I~
                	rc=1;                                          //~1A21I~//~9614I~
                else                                               //~1A21I~//~9614I~
                {                                                  //~1A21I~//~9614I~
                	rc=-f1.getName().compareTo(f2.getName());       //~v@@@I~//~9614I~
                }                                                  //~1A21I~//~9614I~
            if (Dump.Y) Dump.println("HistoryDlg compare rc="+rc); //~1A4aI~//~9614I~
            return rc;                                             //~1A21I~//~9614I~
        }                                                          //~1A21I~//~9614I~
    }                                                              //~1A21I~//~9614I~
    //****************************************************************//~v@@@I~//~9614I~
    @Override //UListViewI                                         //~v@@@I~//~9614I~
    public View getViewCustom(int Ppos, View Pview, ViewGroup Pparent)//~v@@@I~//~9614I~
    {                                                              //~v@@@I~//~9614I~
    //*******************                                          //~v@@@I~//~9614I~
        if (Dump.Y) Dump.println("HistoryDlg:getViewCustom Ppos="+Ppos);//~v@@@I~//~9614I~//~9C22R~
        View v=Pview;                                              //~v@@@I~//~9614I~
        if (v == null)                                           //~v@@@I~//~9614I~
        {                                                        //~v@@@I~//~9614I~
            v=AG.inflater.inflate(lvFilename.rowId,null);          //~v@@@R~//~9614I~
        }                                                        //~v@@@I~//~9614I~
        TextView v1=(TextView)UView.findViewById(v,R.id.FileName);//~v@@@R~//~9614I~
//      TextView v2=(TextView)UView.findViewById(v,R.id.Timestamp);//~v@@@I~//~9614I~
        UListView.UListViewData ld=lvFilename.arrayData.get(Ppos);                      //~v@@@R~//~9614I~
        v1.setText(ld.itemtext);                                   //~v@@@R~//~9614I~
//      v2.setText(ld.itemtext2);                                  //~v@@@R~//~9614I~
//      if (ld.choosed)                                            //~v@@@I~//~9614I~//~0114R~
		boolean swSelected;                                        //~0114I~
        if (swMultiSelect)                                         //~0114I~
			swSelected=ld.choosed;                                 //~0114I~
        else                                                       //~0114I~
			swSelected=Ppos==lvFilename.getSelectedPos();          //~0114I~
        if (Dump.Y) Dump.println("HistoryDlg:getViewCustom Ppos="+Ppos+",swSelected="+swSelected+",swMultiSelect="+swMultiSelect);//~0114I~
        if (swSelected)                                            //~0114I~
        {                                                          //~v@@@I~//~9614I~
            v1.setBackgroundColor(lvFilename.bgColorSelected);     //~v@@@I~//~9614I~
            v1.setTextColor(lvFilename.bgColor);                              //~v@@@I~//~9614I~
        }                                                          //~v@@@I~//~9614I~
        else                                                       //~v@@@I~//~9614I~
        {                                                          //~v@@@I~//~9614I~
            v1.setBackgroundColor(lvFilename.bgColor);             //~v@@@I~//~9614I~
            v1.setTextColor(Color.BLACK);                          //~v@@@I~//~9614I~
        }                                                          //~v@@@I~//~9614I~
        String[][] hds=getHistoryData(ld.itemtext);                //~9615I~
        if (hds!=null)                                             //~9615I~
	        setHD(v,hds);                                          //~9615R~
        return v;                                                  //~v@@@I~//~9614I~
    }                                                              //~v@@@I~//~9614I~
    //***********************************************              //~9615I~
    private void setHD(View Pview,String[][] Phds)                      //~9615I~
    {                                                              //~9615I~
        try                                                        //~9829I~
        {                                                          //~9829I~
        String[] hdr=Phds[HDPOS_HDR];                              //~9615R~
        String[] name=Phds[HDPOS_MEMBER];                          //~9615I~
        String[] score=Phds[HDPOS_SCORE];                             //~9615I~
        TextView tv;                                               //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.Member1);       //~9615R~
        tv.setText(name[0]);                                       //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.Score1);        //~9615R~
        tv.setText(score[0]);                                      //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.Member2);       //~9615R~
        tv.setText(name[1]);                                       //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.Score2);        //~9615R~
        tv.setText(score[1]);                                      //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.Member3);       //~9615R~
        tv.setText(name[2]);                                       //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.Score3);        //~9615R~
        tv.setText(score[2]);                                      //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.Member4);       //~9615R~
        tv.setText(name[3]);                                       //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.Score4);        //~9615R~
        tv.setText(score[3]);                                      //~9615I~
                                                                   //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.GameType);      //~9615I~
        tv.setText(hdr[POS_GAMESET]);                              //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.RuleID);        //~9615I~
        tv.setText(hdr[POS_RULEID]);                               //~9615I~
        tv=(TextView)UView.findViewById(Pview,R.id.EndgameType);   //~9823I~
        int integtp=Utils.parseInt(hdr[POS_ENDGAMETYPE],-1);       //~9823R~
        if (Dump.Y) Dump.println("HistoryDlg.setHD integtp="+integtp+",hdr="+Arrays.toString(hdr));//~9824I~
        if (integtp==SAVE_EGTP_INTERRUPTED)                        //~9825R~
            tv.setTextColor(COLOR_INTERRUPTED);                    //~9825I~
        else                                                       //~9825I~
    	    tv.setTextColor(COLOR_NOT_INTERRUPTED);                //~9825I~
        tv.setText(getStrEndgameType(integtp,Phds));                    //~9825R~//~9826R~
        }                                                          //~9829I~
        catch(Exception e)                                         //~9829I~
        {                                                          //~9829I~
//      	Dump.println(e,"HistoryDlg.setHD");                    //~9829R~
        }                                                          //~9829I~
    }                                                              //~9615I~
    //************************************************             //~9825I~
    private static String getStrEndgameType(int PendgameType,String[][] Phds)      //~9825I~//~9826R~
    {                                                              //~9825I~
        String stregtp="";                                         //~9823I~//~9825M~
        switch(PendgameType)                                            //~9823I~//~9825I~
        {                                                          //~9823I~//~9825M~
        case SAVE_EGTP_GAMEOVER:                                   //~9823I~//~9825M~
        	stregtp=Utils.getStr(R.string.Info_LastEndgame);       //~9823I~//~9825M~
        	break;                                                 //~9823I~//~9825M~
        case SAVE_EGTP_SUSPENDED:                                  //~9823I~//~9825M~
        	stregtp=Utils.getStr(R.string.Info_SuspendEndgame);    //~9823I~//~9825M~
        	break;                                                 //~9823I~//~9825M~
        case SAVE_EGTP_INTERRUPTED:                                //~9824R~//~9825M~
//      	stregtp=Utils.getStr(R.string.Info_SuspendSaved);      //~9823I~//~9825M~//~9826R~
        	stregtp=getGameRound(Phds);                            //~9826I~
        	break;                                                 //~9823I~//~9825M~
        default:                                                   //~9823I~//~9825M~
        }                                                          //~9823I~//~9825M~
        if (Dump.Y) Dump.println("HistoryDlg.getStrEndgameType rc="+stregtp+",PendgameType="+PendgameType);//~9825I~
        return stregtp;                                            //~9825I~
    }                                                              //~9825I~
    //************************************************             //~9826I~
    private static String getGameRound(String[][] Phds)            //~9826I~
    {                                                              //~9826I~
    	int ctrSet=Utils.parseInt(Phds[HDPOS_GAMESEQ][0],-1);      //~9826I~
    	int ctrGame=Utils.parseInt(Phds[HDPOS_GAMESEQ][1],-1);     //~9826I~
        String rc;                                                 //~9826I~
        if (ctrSet<0||ctrGame<0)                                   //~9826I~
			rc=Utils.getStr(R.string.Info_SuspendSaved);           //~9826I~
        else                                                       //~9826I~
	    	rc=Status.getStringGameRound(ctrSet,ctrGame);             //~9826I~
        if (Dump.Y) Dump.println("HistoryDlg.getGameRound rc="+rc);
            return rc;//~9826I~
    }                                                              //~9826I~
    //***********************************************              //~9824I~//~9825R~
    private boolean isInterrupted(HistoryData Phds)                //~9824I~//~9825R~
    {                                                              //~9824I~//~9825R~
        String[] hdr=Phds.HD[HDPOS_HDR];                           //~9824R~//~9825R~
        int integtp=Utils.parseInt(hdr[POS_ENDGAMETYPE],-1);       //~9824I~//~9825R~
        boolean rc=integtp==SAVE_EGTP_INTERRUPTED;                 //~9824I~//~9825R~
        if (Dump.Y) Dump.println("HistoryDlg.isInterrupted rc="+rc+",integtp="+integtp+",hdr="+Arrays.toString(hdr));//~9824I~//~9825R~
        return rc;                                                 //~9824I~//~9825R~
    }                                                              //~9824I~//~9825R~
    //***********************************************              //~va66I~
    private int chkMemberTrainingMode(HistoryData Phds,int Prole)  //~va66I~
    {                                                              //~va66I~
    	int rc=0;                                                  //~va66I~
        if (Dump.Y) Dump.println("HistoryDlg.chkMemberTrainingMode role="+Prole);//~va66R~
        int role=Prole;                                            //~va66I~
        if (Prole!=-1)                                             //~va66I~
	        if (AG.aBTMulti.BTGroup.getConnectedCtr()==0)//~va66I~
            	role=-1;	//no session                           //~va66I~
        String[] names=Phds.HD[HDPOS_MEMBER];                      //~va66I~
        int ctrRobot=0;                                            //~va66I~
        int ctrFound=0;                                            //~va66I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~va66I~
        {                                                          //~va66I~
            boolean swFound=false;                                 //~va66I~
            String name=names[ii];                                 //~va66I~
            if (name.compareTo(AG.YourName)==0)                    //~va66I~
            	ctrFound++;                                        //~va66I~
            else                                                   //~va66I~
            if (Accounts.isRobotName(name)>0)                      //~va66I~
                ctrRobot++;                                        //~va66I~
        }                                                          //~va66I~
        if (ctrFound==1 && ctrRobot==PLAYERS-1)	//trainingMode     //~va66I~
		{                                                          //~va66I~
        	if (role!=-1)	//have connection                      //~va66R~
            {                                                      //~va66I~
	            UView.showToastLong(R.string.Err_HistoryIsTrainingMode);//~va66I~
                rc=-1;                                             //~va66I~
            }                                                      //~va66I~
            else                                                   //~va66I~
            {                                                      //~va66I~
            	AG.swTrainingMode=true;                            //~va66I~
		        AG.aBTMulti.setTrainingMode();                     //~va66I~
                swServer=true;                                     //~va66I~
        		rc=MS_SERVER;                                      //~va66I~
            }                                                      //~va66I~
        }                                                          //~va66I~
        else         //not training mode                           //~va66I~
        {                                                          //~va66I~
        	if (role==-1)	//have connection                      //~va66R~
            {                                                      //~va66I~
				UView.showToast(R.string.Err_ReloadNooneConnected);//~va66I~
            	rc=-1;     //err                                   //~va66I~
            }                                                      //~va66I~
        }                                                          //~va66I~
		if (Dump.Y) Dump.println("HistoryDlg.chkMemberTrainingMode rc="+rc+",ctrRobot="+ctrRobot+",ctrFound="+ctrFound);//~va66I~
        return rc;                                                 //~va66I~
    }                                                              //~va66I~
    //***********************************************              //~va66I~
    private boolean chkMember(HistoryData Phds)                    //~va66I~
    {                                                              //~va66I~
    	boolean rc=false;                                          //~9824I~
        if (Dump.Y) Dump.println("HistoryDlg.chkMember");          //~9824I~
        String[] names=Phds.HD[HDPOS_MEMBER];                      //~9824R~
        Members members=AG.aBTMulti.BTGroup;                       //~9824I~
//      String botname=Utils.getStr(R.string.YournameRobot);       //~9824I~//~0204R~
        int ctrRobot=0;                                            //~9824I~
        int ctrFound=0;                                            //~9824I~
        int ctrRobotNow=0;                                         //~9824I~
        for (int jj=0;jj<PLAYERS;jj++)                             //~9824I~
        {                                                          //~9824I~
            String yn=members.MD[jj].getYourName();                //~9824I~
            if (Dump.Y) Dump.println("HistoryDlg.chkMember memberYN="+yn+",MD["+jj+"]="+members.MD[jj].toString());//~9824I~//~0305R~
            if (yn==null || Accounts.isRobotName(yn)>0)              //~9824I~//~9828R~//~0204R~
//          if (yn==null || Accounts.isRobotName2(yn)>0)  //botname japanese and english//~0204R~
                ctrRobotNow++;                                     //~9824I~
        }                                                          //~9824I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9824R~
        {                                                          //~9824R~
            boolean swFound=false;                                 //~9824R~
            String name=names[ii];                                 //~9824R~
            if (Accounts.isRobotName(name)>0)                        //~9824R~//~9828R~//~0204R~
//          if (Accounts.isRobotName2(name)>0)                     //~0204R~
            {                                                      //~9824R~
                ctrRobot++;                                        //~9824R~
                continue;                                          //~9824R~
            }                                                      //~9824R~
            for (int jj=0;jj<PLAYERS;jj++)                         //~9824R~
            {                                                      //~9824R~
                String yn=members.MD[jj].getYourName();            //~9824R~
                if (Dump.Y) Dump.println("HistoryDlg.chkMember hitoryYN="+name+",membersYN="+yn);//~9824R~
                if (yn==null || Accounts.isRobotName(yn)>0)          //~9824R~//~9828R~//~0204R~
//              if (yn==null || Accounts.isRobotName2(yn)>0)       //~0204R~
                    continue;                                      //~9824R~
                if (name.compareTo(yn)==0)                         //~9824R~
                {                                                  //~9824R~
                    swFound=true;                                  //~9824R~
                    break;                                         //~9824R~
                }                                                  //~9824R~
            }                                                      //~9824R~
            if (!swFound)                                          //~9824R~
                break;                                             //~9824R~
            ctrFound++;                                            //~9824R~
        }                                                          //~9824R~
        if (ctrRobot==ctrRobotNow                                  //~9824I~
        &&  ctrFound+ctrRobot==PLAYERS)                            //~9824I~
        	rc=true;                                               //~9824I~
        if (!rc)                                                   //~9824I~
	    	UView.showToastLong(R.string.Err_HistoryNotSameMembers);    //~9824I~//~9825R~//~0212R~//~va66R~
		if (Dump.Y) Dump.println("HistoryDlg.chkMember rc="+rc+",ctrRobot="+ctrRobot+",ctrRobotNow="+ctrRobotNow+",ctrFound="+ctrFound);//~9824R~
        return rc;                                                 //~9824R~
    }                                                              //~9824I~
    //***********************************************              //~9824I~
    private void doReload(HistoryData Phd)                     //~9824R~
    {                                                              //~9824I~
		if (Dump.Y) Dump.println("HistoryDlg.doReload before Alert HD="+Utils.toString(Phd.HD));//~9825I~
    	reloadHD=Phd;                                             //~9825I~
	    String fpath=AG.aHistory.getFpathRule(Phd);                //~9901R~
        if (fpath==null)                                           //~9901M~
        	return;                                                //~9901M~
        confirmReload();                                           //~9825I~
    }                                                              //~9825I~
	//*******************************************************************************************************//~1A87I~//~1A89I~//~@@@@I~//~9825I~
	public void confirmReload()                                 //~@@@@I~//~9825I~
    {                                                              //~1A87I~//~1A89I~//~@@@@I~//~9825I~
		if (Dump.Y) Dump.println("HistoryDlg.confirmReload");       //~9825I~
        if (swServer)                                              //~9828I~
        {                                                          //~9828I~
			doReloadServer(reloadHD);                              //~9828I~
            return;                                                //~9828I~
        }                                                          //~9828I~
        Alert.AlertI ai=new Alert.AlertI()                                 //~1A89R~//~@@@@I~//~9825I~
                            {                                  //~1A89I~//~@@@@I~//~9825I~
                                @Override                      //~1A89I~//~@@@@I~//~9825I~
                                public int alertButtonAction(int Pbuttonid,int Pitempos)//~1A89I~//~@@@@I~//~9825I~
                                {                              //~1A89I~//~@@@@I~//~9825I~
                                    if (Dump.Y) Dump.println("HistoryDlg.confirmReload.alertButtonAction buttonid="+Integer.toHexString(Pbuttonid));//~1A89I~//~@@@@I~//~9825I~
                                    if (Pbuttonid==Alert.BUTTON_POSITIVE)//~1A89R~//~@@@@I~//~9825I~
                                    {                          //~1Ac2I~//~@@@@I~//~9825I~
//                                  	if (swServer)              //~9825I~//~9828R~
//                                      	doReloadServer(reloadHD);          //~@@@@I~//~9825I~//~9828R~
//                                      else                       //~9825I~//~9828R~
	                                    	doReloadClient(reloadHD);//~9825I~
                                    }
                                    return 0;//~1Ac2I~//~@@@@I~//~9825I~
                                }                              //~1A89I~//~@@@@I~//~9825I~
                            };                                 //~1A89I~//~@@@@I~//~9825I~
    	int msgid=swServer ? R.string.Msg_ReloadServer : R.string.Msg_ReloadClient;//~9825I~
    	String hdline=getHistoryDataForAlert(reloadHD);               //~9825R~
        String msg=Utils.getStr(msgid,hdline);                     //~9825I~
        Alert.showAlert(TITLEID,msg,Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,ai);//~@@@@I~//~9825I~
    }                                                              //~1A87I~//~1A89I~//~@@@@I~//~9825I~
    //***********************************************              //~9825I~
    private static String getHistoryDataForAlert(HistoryData Phds) //~9825R~
    {                                                              //~9825I~
        String[] hdr=Phds.HD[HDPOS_HDR];                           //~9825I~
        String[] name=Phds.HD[HDPOS_MEMBER];                       //~9825I~
        String[] strScore=Phds.HD[HDPOS_SCORE];                    //~9825I~
        String rc=hdr[POS_TIMESTAMP]+":"+hdr[POS_GAMESET]+":"+hdr[POS_RULEID]+":::"+getStrEndgameType(Utils.parseInt(hdr[POS_ENDGAMETYPE],-1),Phds.HD)+"\n"//~9825R~
                 +name[0]+"="+strScore[0]+"\n"                     //~9825R~
                 +name[1]+"="+strScore[1]+"\n"                     //~9825R~
                 +name[2]+"="+strScore[2]+"\n"                     //~9825R~
                 +name[3]+"="+strScore[3];                         //~9825I~
		if (Dump.Y) Dump.println("HistoryDlg.getHistoryDataForConfirm rc="+rc);//~9825I~
        return rc;                                                 //~9825I~
    }                                                              //~9825I~
    //***********************************************              //~9825I~
    private boolean doReloadServer(HistoryData Phds)               //~9825I~
    {                                                              //~9825I~
		if (Dump.Y) Dump.println("HistoryDlg.doReloadServer HD="+Utils.toString(Phds.HD));//~9824R~//~9825R~
        boolean rc=false;                                          //~9824I~
        String[] hdr=Phds.HD[HDPOS_HDR];                              //~9824I~
        String[] name=Phds.HD[HDPOS_MEMBER];                          //~9824I~
//        String[] strScore=Phds.HD[HDPOS_SCORE];                       //~9824R~//~9828R~
//        String rule=hdr[POS_RULEID];                               //~9824I~//~9828R~
//        int[] score=new int[PLAYERS];                              //~9824I~//~9828R~
//        int[] ctrReach=new int[PLAYERS];                           //~9824I~//~9828R~
//        int[] gameSeq=new int[PLAYERS];                            //~9824I~//~9828R~
//        int[][] scores;                                          //~9828I~
//        int err=0;                                                 //~9824I~//~9828R~
//        try                                                        //~9824I~//~9828R~
//        {                                                          //~9824I~//~9828R~
//            String[] strReach=Phds.HD[HDPOS_REACH];                //~9825I~//~9828R~
//            String[] strGameSeq=Phds.HD[HDPOS_GAMESEQ];            //~9825I~//~9828R~
//            err+=Utils.parseInt(strScore,-1,score);//~9824R~       //~9825R~//~9828R~
//            err+=Utils.parseInt(strReach,-1,ctrReach);//~9824R~    //~9825R~//~9828R~
//            err+=Utils.parseInt(strGameSeq,-1,gameSeq);//~9824R~   //~9825R~//~9828R~
//            int egtp=SAVE_EGTP_INTERRUPTED;                       //~9824I~//~9828R~
//            scores=new int[][]{score,ctrReach,gameSeq};    //~9824I~//~9828R~
//            if (Dump.Y) Dump.println("HistoryDlg.doReload scores="+Utils.toString(scores));//~9824I~//~9828R~
//            if (err==0)                                            //~9824I~//~9828R~
//                rc=true;                                           //~9824R~//~9828R~
//        }                                                          //~9824I~//~9828R~
//        catch (Exception e)                                        //~9824I~//~9828R~
//        {                                                          //~9824I~//~9828R~
//            Dump.println("HistoryDlg:doReload exception");         //~9824I~//~9828R~
//        }                                                          //~9824I~//~9828R~
    	int[][] scores=Phds.setScores();                           //~9828R~
//      if (!rc)                                                   //~9824I~//~9828R~
        if (scores==null)                                          //~9828I~
        {                                                          //~9824I~
        	UView.showToast(R.string.Err_ReloadDataErr);           //~9824M~
            return false;                                                //~9828I~
        }                                                          //~9824I~
//      ResumeDlg.newInstance(true/*server*/,Phds).show();         //~9828R~//~9831R~
        ResumeDlg.showDialog(true/*server*/,null,Phds);            //~9831R~
		if (Dump.Y) Dump.println("HistoryDlg.doReload rc="+rc);    //~9824I~
        return rc;                                                 //~9824I~
    }                                                              //~9824I~
    //***********************************************              //~9825I~
    private void doReloadClient(HistoryData Phds)               //~9825I~
    {                                                              //~9825I~
		if (Dump.Y) Dump.println("HistoryDlg.doReload HD="+Utils.toString(Phds.HD));//~9825I~
    	if (!AG.aHistory.sendRule(false,Phds))                          //~9827I~//~9901I~
        	return;                                                //~9901I~
        String msg=makeSendData(Phds);                             //~9825I~
//      AG.aBTMulti.sendMsgToServerProp(GCM_HISTORY,msg); //same as Properties//~9825I~//~9828R~
        AG.aBTMulti.sendMsgToServerProp(GCM_HISTORY_RESUME,msg); //same as Properties//~9828I~
    }                                                              //~9825I~
    //***********************************************              //~9825I~
    private void doSend(boolean PswServer,HistoryData Phds)        //~9825I~
    {                                                              //~9825I~
		if (Dump.Y) Dump.println("HistoryDlg.doSend swServer="+PswServer+",HD="+Utils.toString(Phds.HD));//~9825I~
    	if (!AG.aHistory.sendRule(PswServer,Phds))                      //~9827M~//~9901R~
        	return;                                                //~9901I~
        String msg=makeSendData(Phds);                             //~9825I~
        if (PswServer)                                             //~9825I~
			AG.aBTMulti.sendMsgToAllClientProp(GCM_HISTORY,msg);//send string datas as send properties//~9831R~
        else                                                       //~9825I~
	        AG.aBTMulti.sendMsgToServerProp(GCM_HISTORY,msg); //same as Properties//~9825I~
        UView.showToast(PswServer ? R.string.Info_HistorySendToAllClient : R.string.Info_HistorySendToServer);//~9825I~
    }                                                              //~9825I~
    //******************************************                   //~9825I~
    public static boolean isServer()                              //~9825I~//~9828R~
    {                                                              //~9825I~
        Members members=AG.aBTMulti.BTGroup;                       //~9825I~
        int role=-1;                                               //~9825I~
        if (members!=null)                                         //~9825I~
 			role=members.getMemberRole();                          //~9825I~
        if (role==-1)                                              //~9825I~
        {                                                          //~9825I~
            return false;                                          //~9825I~
        }                                                          //~9825I~
        boolean swServer=(role!=MS_SERVER);                        //~9825I~
		if (Dump.Y) Dump.println("HistoryDlg.isServer static rc="+swServer);//~9825I~
        return swServer;                                           //~9825I~
    }                                                              //~9825I~
    //*********************************************************************************//~9831R~
    //*from BTIO at Server received history from client by GCM_HISTORY(_RESUME)//~9831R~
    //*********************************************************************************//~9831R~
//  public static void received(String PsenderYourName,String Phds)                     //~9404I~//~9405R~//~9825I~//~9828R~
    public static void onReceived(boolean PswResume,String PsenderYourName,String Phds)//~9828I~
    {                                                              //~9404I~//~9825I~
		HistoryData receivedHD;                                    //~0111I~
        if (Dump.Y) Dump.println("HistoryDlg.onReceived senderYourName="+PsenderYourName+",hds="+Phds);//~9404R~//~9405R~//~9825I~//~0111R~
//      receivedHD=parseReceivedData(Phds);                        //~9825I~//~9831R~
        receivedHD=HistoryData.parseReceivedData(Phds);            //~9831I~
        if (receivedHD==null)                                              //~9825I~//~9828R~
        {                                                          //~9825I~
        	UView.showToastLong(Utils.getStr(R.string.Err_ReloadDataErrReceived,PsenderYourName));//~9825I~//~0212R~
            return;                                                //~9825I~
        }                                                          //~9825I~
        if (Dump.Y) Dump.println("HistoryDlg.onReceived Object receivedHD="+receivedHD.toString());//~0111I~//~0212M~
        if (PswResume)                                             //~9828I~
        {                                                          //~9828I~
        	onReceivedHistoryResume(PsenderYourName,receivedHD);                   //~9828I~//~9831R~
            return;                                                //~9828I~
        }                                                          //~9828I~
        if (receivedHD.isInterruptedResume())                      //~9831M~
        {	                                                       //~9831M~
        	ResumeDlg.onReceivedHistoryInterruptedResume(PsenderYourName,receivedHD);//~9831M~
            return;                                                //~9831M~
        }                                                          //~9831M~
//        Alert.AlertI ai=new Alert.AlertI()                         //~9825I~//~0111R~
//                            {                                      //~9825I~//~0111R~
//                                @Override                          //~9825I~//~0111R~
//                                public int alertButtonAction(int Pbuttonid,int Pitempos)//~9825I~//~0111R~
//                                {                                  //~9825I~//~0111R~
//                                    HistoryData receivedHD=(HistoryData)parmObject;//~0111R~
//                                    if (Dump.Y) Dump.println("HistoryDlg.onReceived alertButtonAction receivedHD="+Utils.toString((Object)receivedHD));//~0111R~
//                                    if (Dump.Y) Dump.println("HistoryDlg.onReceived.alertButtonAction buttonid="+Integer.toHexString(Pbuttonid));//~9825I~//~9828R~//~0111R~
//                                    if (Pbuttonid==Alert.BUTTON_POSITIVE)//~9825I~//~0111R~
//                                        saveReceivedHistory(receivedHD);//~9825R~//~9828R~//~0111R~
//                                    else                           //~9825I~//~0111R~
//                                        UView.showToast(Utils.getStr(R.string.Info_HistoryReceivedCanceled));//~9825I~//~0111R~
//                                    receivedHD=null;             //~0111R~
//                                    return 0;                      //~9825I~//~0111R~
//                                }                                  //~9825I~//~0111R~
//                                //for java GC     //~9828I~      //~0111R~
//                            };                                     //~9825I~//~0111R~
//        ai.setParm(receivedHD);                                  //~0111R~
//        String hdline=getHistoryDataForAlert(receivedHD);                //~9825I~//~0111R~
//        String msg=AG.resource.getString(R.string.Msg_HistoryReceived,PsenderYourName,hdline);//~9825I~//~0111R~
//        Alert.showAlert(TITLEID,msg,Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,ai);//~9825I~//~0111R~
//        new HistoryDlg().new saveAlert(receivedHD,PsenderYourName).show();//~0111R~//~0112R~
//        Alert2 ai2=new Alert2().setParm(0/*intParm*/,receivedHD);//~0112R~
//        String hdline=getHistoryDataForAlert(receivedHD);        //~0112R~
//        String msg=AG.resource.getString(R.string.Msg_HistoryReceived,PsenderYourName,hdline);//~0112R~
//        ai2.showAlert(TITLEID,msg,Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,this);//~0112R~
		new HistoryDlg().new saveAlert(receivedHD,PsenderYourName).show();//~0112I~
    }                                                              //~9404I~//~9825I~
//    //****************************************************************************//~0112R~
//    @Override  //Alert2.AlertI                                   //~0112R~
//    public int alertButtonAction(int Pbuttonid,int PparmInt,Object parmObject)//~0112R~
//    {                                                            //~0112R~
//        HistoryData receivedHD=(HistoryData)parmObject;          //~0112R~
//        if (Dump.Y) Dump.println("HistoryDlg.onReceived alertButtonAction receivedHD="+Utils.toString((Object)receivedHD));//~0112R~
//        if (Dump.Y) Dump.println("HistoryDlg.onReceived.alertButtonAction buttonid="+Integer.toHexString(Pbuttonid));//~0112R~
//        if (Pbuttonid==Alert.BUTTON_POSITIVE)                    //~0112R~
//            saveReceivedHistory(receivedHD);                     //~0112R~
//        else                                                     //~0112R~
//            UView.showToast(Utils.getStr(R.string.Info_HistoryReceivedCanceled));//~0112R~
//        return 0;                                                //~0112R~
//    }                                                            //~0112R~
    //****************************************************************************//~9825I~
	private static void onReceivedHistoryResume(String PsenderYourName,HistoryData Phds)      //~9825I~//~9828R~//~9831R~
    {                                                              //~9825I~
        if (Dump.Y) Dump.println("HistoryDlg.onReceivedHistory historydata="+Utils.toString(Phds.HD));//~9828R~
    	int[][] scores=Phds.setScores();                           //~9828R~
        if (scores==null)                                         //~9828I~
        {                                                          //~9828I~
        	UView.showToast(R.string.Err_ReloadDataErr);           //~9828I~
            return;                                                //~9828I~
        }                                                          //~9828I~
        AG.aHistory.saveReceived(false/*PswMsg,bypass override without warning*/,Phds);//~9828R~
//      ResumeDlg.newInstance(true/*PswServer*/,Phds).show();      //~9828R~//~9831R~
        ResumeDlg.showDialog(true/*PswServer*/,PsenderYourName,Phds);//~9831R~
    }                                                              //~9828I~
    //****************************************************************************//~9828I~
	private static void saveReceivedHistory(HistoryData Phd)       //~9828I~
    {                                                              //~9828I~
        if (Dump.Y) Dump.println("HistoryDlg.saveReceivedHistory HD="+Utils.toString(Phd.HD));//~9825R~
//      AG.aHistory.saveReceived(Phd);                             //~9825I~//~9828R~
        AG.aHistory.saveReceived(true/*PswMsg,issue override warning*/,Phd);//~9828I~
        if (Utils.isShowingDialogFragment(AG.aHistoryDlg))         //~9825I~
        {                                                          //~9825I~
			AG.aHistoryDlg.updateList();                           //~9825I~
        }                                                          //~9825I~
    }                                                              //~9825I~
    //*******************************************************      //~0114I~
    //*UCheckBoxI                                                  //~0114I~
    //*******************************************************      //~0114I~
    @Override                                                      //~0114I~
    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked, int Pparm)//~0114I~
    {                                                              //~0114I~
        if (Dump.Y) Dump.println("HistoryDlg.onChangedUCB parm="+Pparm+",isChecked="+PisChecked);//~0114R~
        switch(Pparm)                                              //~0114I~
        {                                                          //~0114I~
        case UCBP_MULTISELECT:                                     //~0114I~
        	changedMultiSelect(PisChecked);                        //~0114I~
            break;                                                 //~0114I~
        }                                                          //~0114I~
    }                                                              //~0114I~
    //****************************************************************************//~0114I~
    public void changedMultiSelect(boolean PswOn)                  //~0114I~
    {                                                              //~0114I~
        if (Dump.Y) Dump.println("HistoryDlg.changedMultiSelect sw="+PswOn+",old="+swMultiSelect);//~0114R~
        swMultiSelect=PswOn;                                       //~0114I~
        if (swMultiSelect)                                         //~0114I~
        {                                                          //~0114I~
	        lvFilename.setChoiceMode(CHOICEMODE_MULTIPLE);          //~0114I~
        }                                                          //~0114I~
        else                                                       //~0114I~
        {                                                          //~0114I~
	        lvFilename.setChoiceMode(CHOICEMODE_SINGLE);	//also deselected//~0114I~
        }                                                          //~v@@@I~//~0114I~
    }                                                              //~0114I~
//    //****************************************************************************//~0111I~//~0112R~
//    class saveAlert implements Alert.AlertI                        //~0111I~//~0112R~
//    {                                                              //~0111I~//~0112R~
//        HistoryData receivedHD;                                  //~0112R~
//        String senderYourName;//~0111I~                          //~0112R~
//        //***************************************                  //~0111I~//~0112R~
//        public saveAlert(HistoryData Phd,String Pyourname)                          //~0111I~//~0112R~
//        {                                                          //~0111I~//~0112R~
//            receivedHD=Phd;                                      //~0112R~
//            senderYourName=Pyourname;//~0111I~                   //~0112R~
//        }                                                          //~0111I~//~0112R~
//        //***************************************                  //~0111I~//~0112R~
//        public void show()                                              //~0111I~//~0112R~
//        {                                                          //~0111I~//~0112R~
//            String hdline=getHistoryDataForAlert(receivedHD);      //~0111I~//~0112R~
//            String msg=AG.resource.getString(R.string.Msg_HistoryReceived,senderYourName,hdline);//~0111I~//~0112R~
//            Alert.showAlert(TITLEID,msg,Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,this);//~0111I~//~0112R~
//        }                                                          //~0111I~//~0112R~
//        //***************************************                  //~0111I~//~0112R~
//        @Override   //AlertI                                       //~0111I~//~0112R~
//        public int alertButtonAction(int Pbuttonid,int Pitempos)   //~0111I~//~0112R~
//        {                                                          //~0111I~//~0112R~
//            if (Dump.Y) Dump.println("HistoryDlg.onReceived alertButtonAction receivedHD="+Utils.toString((Object)receivedHD));//~0111I~//~0112R~
//            if (Dump.Y) Dump.println("HistoryDlg.onReceived.alertButtonAction buttonid="+Integer.toHexString(Pbuttonid));//~0111I~//~0112R~
//            if (Pbuttonid==Alert.BUTTON_POSITIVE)                  //~0111I~//~0112R~
//                HistoryDlg.saveReceivedHistory(receivedHD);        //~0111R~//~0112R~
//            else                                                   //~0111I~//~0112R~
//                UView.showToast(Utils.getStr(R.string.Info_HistoryReceivedCanceled));//~0111I~//~0112R~
//            receivedHD=null;                                       //~0111I~//~0112R~
//            return 0;                                              //~0111I~//~0112R~
//        }                                                          //~0111I~//~0112R~
//    }                                                              //~0111I~//~0112R~
    //****************************************************************************//~0112I~
    class saveAlert implements Alert2.AlertI                       //~0112I~
    {                                                              //~0112I~
        HistoryData receivedHD;                                    //~0112I~
        String senderYourName;                                     //~0112I~
        //***************************************                  //~0112I~
        public saveAlert(HistoryData Phd,String Pyourname)         //~0112I~
        {                                                          //~0112I~
            receivedHD=Phd;                                        //~0112I~
            senderYourName=Pyourname;                              //~0112I~
        }                                                          //~0112I~
        //***************************************                  //~0112I~
        public void show()                                         //~0112I~
        {                                                          //~0112I~
            String hdline=getHistoryDataForAlert(receivedHD);      //~0112I~
            String msg=AG.resource.getString(R.string.Msg_HistoryReceived,senderYourName,hdline);//~0112I~
            Alert2 a2=new Alert2();                                //~0113R~
            a2.setParm(0,receivedHD);                              //~0113I~
            a2.setShift(AG.ctrSaveAlert++,UNIT_SHIFT);             //~0113R~
            a2.showAlert(TITLEID,msg,Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,this);//~0113I~
            if (Dump.Y) Dump.println("HistoryDlg.show ctrShift="+AG.ctrSaveAlert);//~0113R~
        }                                                          //~0112I~
        //***************************************                  //~0112I~
        @Override   //Alert2.AlertI                                //~0112I~
        public int alertButtonAction(int Pbuttonid,int PparmInt,Object parmObject)//~0112I~
        {                                                          //~0112I~
            if (Dump.Y) Dump.println("HistoryDlg.saveAlert.alertButtonAction receivedHD="+Utils.toString((Object)receivedHD));//~0112R~
            if (Dump.Y) Dump.println("HistoryDlg.saveAlert.alertButtonAction buttonid="+Integer.toHexString(Pbuttonid));//~0112R~
            if (Pbuttonid==Alert.BUTTON_POSITIVE)                  //~0112I~
                HistoryDlg.saveReceivedHistory(receivedHD);        //~0112I~
            else                                                   //~0112I~
                UView.showToast(Utils.getStr(R.string.Info_HistoryReceivedCanceled));//~0112I~
        	AG.ctrSaveAlert--;                                     //~0113R~
            if (Dump.Y) Dump.println("HistoryDlg.alertButtonAction ctrShift="+AG.ctrSaveAlert);//~0113R~
            return 0;                                              //~0112I~
        }                                                          //~0112I~
    }                                                              //~0112I~
    //****************************************************************************//~va7aI~
    public void onClickHistoryBS()                                 //~va7aI~
    {                                                              //~va7aI~
        if (Dump.Y) Dump.println("HistoryDlg.onClickHistoryBS");   //~va7aI~
//	    HistoryBSDlg.newInstance().show();                         //~va7aR~//+va87R~
  	    HistoryBSDlg.newInstance(multipleSelected).show();         //+va87I~
    }                                                              //~va7aI~
}//class                                                           //~v@@@R~
