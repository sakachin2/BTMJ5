//*CID://+vae0R~:                             update#=  328;       //~vae0R~
//*************************************************************************//~v105I~
//2021/08/25 vae0 Scped for BTMJ5                                  //~vae0I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//*************************************************************************//~v105I~
package com.btmtest.dialog;                                           //~1108R~//~1109R~//~1A21R~//~v@@@R~

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.btmtest.R;                                                //~1A21R~//~v@@@M~
import com.btmtest.gui.UListView;
import com.btmtest.gui.UButton;                                    //~v@@@M~
import com.btmtest.utils.Alert;                                            //~1A21R~//~v@@@R~
import com.btmtest.utils.UFile;                                             //~1A21R~//~v@@@R~
import com.btmtest.utils.UScoped;
import com.btmtest.utils.UView;                                            //~1A21R~//~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@I~
import com.btmtest.utils.Utils;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~

//~1319R~
public class FileDialog extends UFDlg                                      //~v@@@R~
    implements UListView.UListViewI , Alert.AlertI                 //~v@@@R~
{                                                                  //~1111I~
    private static final int LAYOUTID=R.layout.filedialog;         //~v@@@I~
    private static final int LAYOUTID_SMALLFONT=R.layout.filedialog_theme;//~vac5I~
    private static final int LISTVIEW_ROW_LAYOUTID=R.layout.textrowlist_filedialog;//~v@@@I~
    private static final int LISTVIEW_ROW_LAYOUTID_SMALLFONT=R.layout.textrowlist_filedialog_theme;//~vac5I~
    private static final int TITLEID_LOAD=R.string.Title_FileDialog_Load;//~v@@@I~
    private static final int TITLEID_SAVE=R.string.Title_FileDialog_Save;//~v@@@I~
    private static final int HELP_TITLEID=R.string.Title_Help_FileDialog;//~v@@@I~
    private static final String HELPFILE="filedialog";             //~v@@@R~
    private static final String WORKDIR_SD=AG.appName;           //~v@@@I~
                                                                   //~v@@@I~
    protected static final String PARM_FILTER="filter";            //~v@@@R~
    protected static final String PARM_SWLOAD="swload";            //~v@@@R~
                                                                   //~v@@@I~
                                                                   //~v@@@I//~1402I~
    protected TextView tvFilter,tvLabelFilter;                     //~v@@@R~
    protected TextView tvFileType;                                 //~v@@@R~
    protected EditText etFileName;                                 //~v@@@R~
//  protected  Button btnSelectAll,btnDeSelectAll,btnLoad,btnOpen,btnSave,btnDelete;//~v@@@R~
    protected  Button                             btnLoad,        btnSave;//~v@@@I~
    private  Button btnSwitchToSD,btnSwitchToFiles,btnBackup,btnRestore;//~v@@@I~
    protected View containerFilename;                              //~v@@@R~
    protected UListView lvFilename;                                //~v@@@R~
    protected String dirName="";                                     //~1324R~//~v@@@R~
    public  String[] namelist=new String[0];                                      //~1319I~//~1402R~//~v@@@R~
	protected File[] filelist;                                       //~1319I~//~v@@@R~
    protected ArrayList<UScoped.TreeMember> filelistScoped;                //~vae0I~
	protected int[]  name2file;                                      //~1330I~//~v@@@R~
	protected File   selectedFile;                                   //~1403R~//~v@@@R~
	protected UScoped.TreeMember  selectedFileScoped;                      //~vae0I~
    protected	String selectedFullpath;                           //~v@@@R~
	protected String selectedFilename=null;                   //~1319I~//~1330R~//~v@@@R~
    private String saveFilename,saveFullpath;                                //~1402I~//~v@@@R~
    protected String workDirSD;                                    //~v@@@R~
    protected String pathDataDir; // /data/data/<pkg>/files        //~v@@@R~
    private FileDialogI FDI;                                       //~1A24I~
//  private UFDlg ufdlg;                                           //~v@@@R~
    protected boolean swLoad,swSD;                                //~v@@@R~
	private FileDialog.FileDialogI listener=null;                 //~v@@@R~
    protected	int[] multipleSelected;                            //~v@@@R~
    protected	FilenameFilter filenameFilter;                     //~v@@@R~
    protected String filterName;                                   //~v@@@R~
    private	String alertTitle;                                     //~v@@@I~
    private	String defaultItemName;                                //~v@@@I~
    private	String saveFilenameByID;                               //~v@@@R~
    protected	boolean swDelete;                                  //~v@@@R~
    protected	boolean swU2S;                                     //~v@@@I~
    protected	boolean swDiscendant=false;                        //~v@@@I~
    protected	boolean swScoped;  //scoed dir,set by extender     //~vae0R~
//*****************************************************************//~1Ah9I~
//*scheduled after FileDialog dismissed*************               //~1Ah9I~
//*****************************************************************//~1Ah9I~
    public interface FileDialogI                                   //~1Ah9I~
    {                                                              //~1Ah9I~
        boolean fileDialogLoadCB(String Pfname);       //~1Ah9I~   //~v@@@R~
        boolean fileDialogSaveCB(String Pfname);                   //~v@@@R~
    }                                                              //~1Ah9I~
//*****************************************************************//~1A4sI~
    public FileDialog()                                            //~v@@@R~
    {                                                              //~1A4sI~
    }                                                              //~1A4sI~
	//**********************************                           //~v@@@M~
	public void show(FileDialogI Plistener,String Pinitialselection) //~v@@@R~
    {                                                              //~v@@@M~
    	if (Dump.Y) Dump.println("FileDialog.show listener="+Utils.getClassName(Plistener));//~v@@@M~
        listener=Plistener;                                        //~v@@@M~
        defaultItemName=Pinitialselection;                         //~v@@@I~
        show(); //by UFDlg.show() ,show DialogFragment         //~v@@@I~
    }                                                              //~v@@@M~
	//**********************************                           //~v@@@I~
	public void show(FileDialogI Plistener,String Pinitialselection,String PsaveFilenameByID)//~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("FileDialog.show listener="+Utils.getClassName(Plistener)+",initialselection="+Pinitialselection+",savefilenameByID="+PsaveFilenameByID);//~v@@@R~
        listener=Plistener;                                        //~v@@@I~
        defaultItemName=Pinitialselection;                         //~v@@@I~
        saveFilenameByID=PsaveFilenameByID;                        //~v@@@R~
        show(); //by UFDlg.show() ,show DialogFragment             //~v@@@I~
    }                                                              //~v@@@I~
	//*****************************************************************//~v@@@I~
    public static FileDialog newInstance(String Pfilter,boolean Pswload)//~v@@@R~
    {                                                              //~v@@@I~
    	FileDialog dlg=new FileDialog();                           //~v@@@I~
//  	UFDlg ufdlg=UFDlg.newInstance(dlg,(Pswload?TITLEID_LOAD:TITLEID_SAVE),LAYOUTID,//~v@@@R~
//  	UFDlg.setBundle(dlg,(Pswload?TITLEID_LOAD:TITLEID_SAVE),LAYOUTID,//~v@@@I~//~vac5R~
    	UFDlg.setBundle(dlg,(Pswload?TITLEID_LOAD:TITLEID_SAVE),(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//~vac5R~
                    UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN,HELP_TITLEID,HELPFILE);//~v@@@R~
//  	dlg.ufdlg=ufdlg;                                           //~v@@@R~
//      Bundle b=ufdlg.bundle;                                     //~v@@@R~
        Bundle b=dlg.bundle;                                       //~v@@@I~
        b.putString(PARM_FILTER,Pfilter);                          //~v@@@R~
        b.putBoolean(PARM_SWLOAD,Pswload);                         //~v@@@I~
    	return dlg;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
	@Override                                                      //~v@@@I~
    protected int getDialogWidth()                                 //~v@@@I~
    {                                                              //~v@@@I~
    	int ww=(int)getDialogWidthSmallDevicePortraitFixedRate(); //~v@@@I~
    	if (Dump.Y) Dump.println("FileDialog.getDialogWidth swSmallDevice="+AG.swSmallDevice+",ww="+ww);//~v@@@I~
        return ww;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************************//~v@@@R~
    //*called at onCreateView to setup layout                      //~v@@@I~
    //*******************************************************************//~v@@@I~
    @Override                                                      //~v@@@I~
    protected void initLayout(View Playoutview)                    //~v@@@I~
    {                                                              //~v@@@I~
    	super.initLayout(Playoutview);                             //~v@@@M~
        getComponent(Playoutview);                                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void getComponent(View Playoutview)                  //~v@@@R~
    {                                                              //~v@@@I~
//      Bundle b=ufdlg.bundle;                                     //~v@@@R~
        Bundle b=bundle;                                           //~v@@@I~
        filterName=b.getString(PARM_FILTER,"*");                   //~v@@@R~
        swLoad=b.getBoolean(PARM_SWLOAD,false);                    //~v@@@I~
     	if (Dump.Y) Dump.println("FileDialog.initLayout filter="+filterName+",swLoad="+swLoad);//~v@@@R~
                                                                   //~v@@@I~
        tvFilter          =(TextView)UView.findViewById(Playoutview,R.id.Filter);//~v@@@R~
        tvFileType        =(TextView)UView.findViewById(Playoutview,R.id.FileType);//~v@@@I~
        tvLabelFilter     =(TextView)UView.findViewById(Playoutview,R.id.LabelFilter);//~v@@@I~
        etFileName        =(EditText)UView.findViewById(Playoutview,R.id.FileName);//~v@@@I~
//        btnSelectAll      =UButton.bind(Playoutview,R.id.SelectAll,this);                  //~1A30I~//~v@@@R~
//        btnDeSelectAll    =UButton.bind(Playoutview,R.id.DeSelectAll,this);//~v@@@R~
//        btnOpen           =UButton.bind(Playoutview,R.id.Open,this);//~v@@@R~
                           UButton.bind(Playoutview,R.id.SelectAll,this);//~v@@@I~
                           UButton.bind(Playoutview,R.id.DeSelectAll,this);//~v@@@I~
                           UButton.bind(Playoutview,R.id.Open,this);//~v@@@I~
        btnSave           =UButton.bind(Playoutview,R.id.Save,this);//~v@@@R~
        btnLoad           =UButton.bind(Playoutview,R.id.Load,this);//~v@@@I~
//        btnDelete         =UButton.bind(Playoutview,R.id.Delete,this);//~v@@@R~
                           UButton.bind(Playoutview,R.id.Delete,this);//~v@@@I~
        btnSwitchToSD     =UButton.bind(Playoutview,R.id.SwitchToSD,this);//~v@@@I~
        btnSwitchToFiles  =UButton.bind(Playoutview,R.id.SwitchToFiles,this);//~v@@@I~
        btnBackup         =UButton.bind(Playoutview,R.id.Backup,this);//~v@@@I~
        btnRestore        =UButton.bind(Playoutview,R.id.Restore,this);//~v@@@I~
        containerFilename=(View)UView.findViewById(Playoutview,R.id.FileNameContainer);//~v@@@I~
//      lvFilename=new UListView(Playoutview,R.id.FileList,LISTVIEW_ROW_LAYOUTID,this,UListView.CHOICEMODE_MULTIPLE);//~v@@@R~//~vac5R~
        lvFilename=new UListView(Playoutview,R.id.FileList,(AG.swSmallFont ? LISTVIEW_ROW_LAYOUTID_SMALLFONT : LISTVIEW_ROW_LAYOUTID),this,UListView.CHOICEMODE_MULTIPLE);//~vac5R~
        setInitialValue();                                         //~v@@@I~
    }                                                              //~1214I~
	//************************************                         //~v@@@I~
    protected void setInitialValue()                               //~v@@@R~
    {                                                              //~v@@@I~
//      int stat=UScoped.chkScoped();    //api>=30                 //~vae0R~
     	if (Dump.Y) Dump.println("FileDialog.setInitialValue swScoped="+swScoped);    //~v@@@I~//~vae0R~
//    if (stat>=0)    //api>=30                                    //~vae0R~
      if (swScoped)   //scoped dir                                 //~vae0I~
      {                                                            //~vae0I~
//      if (stat==0)	//denied use scoped document dir           //~vae0R~
//      	workDirSD=null;                                        //~vae0R~
//      else            //allowd use scoped document dir           //~vae0R~
//      {                                                          //~vae0R~
	    	workDirSD=AG.aUScoped.getDirPath();                    //~vae0I~
//      	swScoped=true;                                         //~vae0R~
//      }                                                          //~vae0R~
      }                                                            //~vae0I~
      else                                                         //~vae0I~
        workDirSD=UFile.getSDPath(""); //"sdpath/appname"          //~v@@@R~
        if (workDirSD==null)                                       //~v@@@I~
            UView.showToast(R.string.NoSDCard);                    //~v@@@I~
        else                                                       //~vae0I~
        	swSD=true;                                             //~vae0I~
        pathDataDir=AG.context.getFilesDir().getAbsolutePath();    //~v@@@I~
        filenameFilter=getFilter();                                //~v@@@I~
        alertTitle=Utils.getStr(R.string.Title_FileDialog);        //~v@@@R~
        if (saveFilenameByID!=null)                                //~v@@@R~
	        etFileName.setText(saveFilenameByID);                  //~v@@@R~
        setButtonVisibility();                                      //~v@@@I~
     	if (Dump.Y) Dump.println("FileDialog.setInitialValue workDirSD="+workDirSD+",pathDataDir="+pathDataDir);//~vae0I~
    }                                                              //~v@@@I~
	//************************************                         //~v@@@I~
//  public static String[] getFolder()                             //~v@@@I~//~vae0R~
    public static String[] getFolder(boolean PswScoped)            //~vae0I~
    {                                                              //~v@@@I~
     	if (Dump.Y) Dump.println("FileDialog.getFolder");          //~vac5I~
//      String workDirSD=UFile.getSDPath(""); //"sdpath/appname"   //~v@@@I~//~vae0R~
        String workDirSD;                                          //~vae0I~
//      int stat=UScoped.chkScoped();    //api>=30                 //~vae0R~
//      if (stat>=0)    //api>=30                                  //~vae0R~
        if (PswScoped)    //use scoped storage                  //~vae0I~
        {                                                          //~vae0I~
//          if (stat==0)	//denied use scoped document dir       //~vae0R~
//              workDirSD=null;                                    //~vae0R~
//          else            //allowd use scoped document dir       //~vae0R~
	            workDirSD=AG.aUScoped.getDirPath();                //~vae0I~
        }                                                          //~vae0I~
        else                                                       //~vae0I~
        	workDirSD=UFile.getSDPath(""); //"sdpath/appname"      //~vae0I~
//      if (workDirSD==null)                                       //~v@@@I~//~vae0R~
//          UView.showToast(R.string.NoSDCard);                    //~v@@@I~//~vae0R~
//      String pathDataDir=AG.context.getFilesDir().getAbsolutePath();//~v@@@R~
        File dir=AG.context.getFilesDir();                       //~v@@@I~
     	if (Dump.Y) Dump.println("FileDialog.getFolder getFilesDir="+dir.toString());//~v@@@I~
        String pathDataDir=dir.getAbsolutePath();                  //~v@@@I~
     	if (Dump.Y) Dump.println("FileDialog.getFolder getAbsolutePath="+pathDataDir);//~v@@@I~
        String[] rc=new String[]{workDirSD,pathDataDir};           //~v@@@I~
     	if (Dump.Y) Dump.println("FileDialog.getFolder rc="+Arrays.toString(rc));//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//************************************                         //~v@@@I~
    protected void setButtonVisibility()                           //~v@@@R~
    {                                                              //~v@@@I~
        if (swLoad)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	btnLoad.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.GONE);                      //~v@@@I~
	        containerFilename.setVisibility(View.GONE);	//avoid intercept by Dialog.UiThread;current dir is on entry "./"//~v@@@I~
            tvLabelFilter.setVisibility(View.VISIBLE);             //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	btnLoad.setVisibility(View.GONE);
            btnSave.setVisibility(View.VISIBLE);                   //~v@@@I~
	        containerFilename.setVisibility(View.VISIBLE);	//avoid intercept by Dialog.UiThread;current dir is on entry "./"//~v@@@I~
            tvLabelFilter.setVisibility(View.GONE);                //~v@@@R~
        }                                                          //~v@@@I~
        if (swSD)                                                  //~v@@@I~
        {                                                          //~v@@@I~
        	btnSwitchToSD.setVisibility(View.GONE);                //~v@@@R~
        	btnSwitchToFiles.setVisibility(View.VISIBLE);          //~v@@@R~
        	btnBackup.setVisibility(View.GONE);                    //~v@@@R~
        	btnRestore.setVisibility(View.VISIBLE);                //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	btnSwitchToSD.setVisibility(View.VISIBLE);             //~v@@@I~
        	btnSwitchToFiles.setVisibility(View.GONE);             //~v@@@R~
        	btnBackup.setVisibility(View.VISIBLE);                  //~v@@@I~
        	btnRestore.setVisibility(View.GONE);                   //~v@@@R~
        	if (workDirSD==null)	//sdcard available             //~v@@@M~
            {                                                      //~v@@@M~
	        	btnSwitchToSD.setEnabled(false);                   //~v@@@M~
	        	btnBackup.setEnabled(false);                       //~v@@@M~
            }                                                      //~v@@@M~
            else                                                   //~v@@@M~
            {                                                      //~v@@@M~
	        	btnSwitchToSD.setEnabled(true);                    //~v@@@M~
	        	btnBackup.setEnabled(true);                        //~v@@@M~
            }                                                      //~v@@@M~
        }                                                          //~v@@@I~
        if (swScoped)                                              //~vae0M~
            tvFileType.setText(Utils.getStr(R.string.FileTypeDocument));//~vae0M~
        else                                                       //~vae0M~
        if (swSD)                                                  //~v@@@I~
            tvFileType.setText(Utils.getStr(R.string.FileTypeSD)); //~v@@@I~
        else                                                       //~v@@@I~
            tvFileType.setText(Utils.getStr(R.string.FileTypeData));//~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    //*called at onActivityCreated                                 //~v@@@I~
    //******************************************                   //~v@@@I~
	@Override                                                      //~v@@@I~
    public void setupDialog(Bundle Pbundle)                        //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog:setupDialog");        //~v@@@I~
        tvFilter.setText(filterName);                              //~v@@@R~
	    updateList();                                               //~v@@@I~
    }                                                              //~1319I~
    //**********************************                           //~1403I~
    //* init ListView                                              //~1403I~
    //**********************************                           //~1403I~
    protected void setList()                                         //~1403R~//~v@@@R~
    {                                                              //~1403I~
		if (Dump.Y) Dump.println("FileDialog.setList swScoped="+swScoped);//~vae0I~
    	if (swScoped)                                              //~vae0I~
        {                                                          //~vae0I~
        	setListScoped();                                       //~vae0I~
            return;                                                //~vae0I~
        }                                                          //~vae0I~
    	int initialSelected=-1;                                     //~v@@@I~
        int sz=namelist.length;                                    //~1411R~
        for (int ii=0;ii<sz;ii++)                                      //~1411I~
        {                                                          //~1403I~
	        int filelistpos=name2file[ii];                         //~v@@@I~
        	File f =filelist[filelistpos];                         //~v@@@I~
            String fnm=namelist[ii];                               //~v@@@R~
            if (defaultItemName!=null && defaultItemName.equals(fnm))//~v@@@I~
            {                                                      //~v@@@I~
                initialSelected=ii;                                //~v@@@I~
		        if (Dump.Y) Dump.println("FileDialog.setList initialSelected="+defaultItemName+",pos="+initialSelected);//~v@@@I~
	            defaultItemName=null;	//at initial only          //~v@@@I~
            }                                                      //~v@@@I~
            fnm=fnm.substring(0,fnm.length()-filterName.length());//~v@@@I~
            String timestamp=UFile.getLastModified(f,"yy.MM.dd-HH:mm:ss");//~v@@@I~
        	lvFilename.add(fnm,timestamp,0);                       //~1403R~//~v@@@R~
        }                                                          //~1403I~
        if (sz>0)                                                  //~1411I~
        {                                                          //~1411I~
		  	if (initialSelected>=0)                                //~v@@@R~
          	{                                                        //~1A4nI~//~v@@@R~
				lvFilename.setSelection(initialSelected);          //~v@@@R~
          	}                                                        //~1A4nI~//~v@@@R~
        }                                                          //~1411I~
    }                                                              //~1403I~
    //**********************************                           //~vae0I~
    protected void setListScoped()                                 //~vae0I~
    {                                                              //~vae0I~
		if (Dump.Y) Dump.println("FileDialog.setListScoped");      //~vae0I~
    	int initialSelected=-1;                                    //~vae0I~
        int sz=namelist.length;                                    //~vae0I~
        for (int ii=0;ii<sz;ii++)                                  //~vae0I~
        {                                                          //~vae0I~
	        int filelistpos=name2file[ii];                         //~vae0I~
        	UScoped.TreeMember tm=filelistScoped.get(filelistpos); //~vae0R~
            String fnm=namelist[ii];                               //~vae0I~
            if (defaultItemName!=null && defaultItemName.equals(fnm))//~vae0I~
            {                                                      //~vae0I~
                initialSelected=ii;                                //~vae0I~
		        if (Dump.Y) Dump.println("FileDialog.setList initialSelected="+defaultItemName+",pos="+initialSelected);//~vae0I~
	            defaultItemName=null;	//at initial only          //~vae0I~
            }                                                      //~vae0I~
            fnm=fnm.substring(0,fnm.length()-filterName.length()); //~vae0I~
            String timestamp=tm.lastModified;                      //~vae0R~
        	lvFilename.add(fnm,timestamp,0);                       //~vae0I~
        }                                                          //~vae0I~
        if (sz>0)                                                  //~vae0I~
        {                                                          //~vae0I~
		  	if (initialSelected>=0)                                //~vae0I~
          	{                                                      //~vae0I~
				lvFilename.setSelection(initialSelected);          //~vae0I~
          	}                                                      //~vae0I~
        }                                                          //~vae0I~
    }                                                              //~vae0I~
    //****************************************************         //~v@@@I~
    //* file submenu selected;create submenu listview
    //****************************************************         //~v@@@I~
    protected FilenameFilter getFilter()                           //~v@@@R~
    {                                                              //~v@@@I~
    	return new FilenameFilter()                                //~v@@@I~
        			{                                              //~v@@@I~
                        @Override                                  //~v@@@I~
                    	public boolean accept(File Pfile,String Pfnm)//~v@@@I~
                        {                                          //~v@@@I~
                        	if (Dump.Y) Dump.println("FilenameFilter Pfile="+Pfile.getName()+",Pfnm="+Pfnm+",filter="+filterName);//~v@@@R~
                            File f=new File(Pfnm);                 //~v@@@I~
                    		if (f==null || f.isDirectory())        //~v@@@R~
                            	return false;                      //~v@@@I~
                        	return (Pfnm.endsWith(filterName));     //~v@@@I~
                        }                                          //~v@@@I~
                    };                                            //~v@@@I~
    }                                                              //~v@@@I~
    //****************************************************                           //~1319I~//~v@@@R~
    //* file submenu selected;create submenu listview              //~1319I~
    //****************************************************         //~1319I~//~v@@@R~
    protected void createList()                       //~1402R~    //~v@@@R~
    {                                                              //~1319I~
    	File fileCurDir;                                           //~1403R~
    //************************                                     //~1319I~
//      namelist=null;                                     //~1323I~//~v@@@R~
        namelist=new String[0];                                    //~v@@@I~
    	String path=swSD ? workDirSD : pathDataDir;                //~v@@@R~
        if (Dump.Y) Dump.println("FileDialog.createList swSD="+swSD+",swScoped="+swScoped+",path="+path);         //~1506R~//~vae0R~
        if (path==null                                             //~1323I~
        ||  path.equals("")                                        //~1323I~
        )                                                          //~1323I~
        {                                                          //~v@@@I~
            UView.showToast(R.string.NoAvailableDir);            //~1403I~//~1A21R~//~v@@@R~
            return;                                            //~1323I~//~v@@@R~
        }                                                          //~1323I~
        dirName=path;                                              //~1402I~//~v@@@R~
     if (swScoped)                                                 //~vae0I~
     {                                                             //~vae0I~
        createListScoped(path,filterName);                         //~vae0I~
     }                                                             //~vae0I~
     else                                                          //~vae0I~
     {                                                             //~vae0I~
        try                                                        //~v@@@R~
		{                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("FileDialog path="+path);     //~1506R~
            fileCurDir=new File(path);                             //~1403R~
            filelist=fileCurDir.listFiles(filenameFilter);   //file and dir      //~1403R~//~v@@@R~
            if(filelist==null||filelist.length==0)                                     //~1319I~//~v@@@I~
            {                                                      //~1319I~
				Alert.showMessage(alertTitle,AG.resource.getString(R.string.NoFileFound,filterName));//~v@@@R~
            }                                                      //~1319I~
            else                                                   //~1319I~
            {                                                      //~1319I~
                String name;                                       //~1403M~
            	int listctr=filelist.length;          //~1403R~    //~v@@@R~
            	name2file=new int[listctr];  //namelist->filelist  //~v@@@I~
                namelist=new String[listctr];            //~1323R~   //~1325R~//~v@@@R~
                Integer[] sortout=sortFileList(); //sorted index to filelist                 //~1A21I~//~v@@@R~
                for (int ii=0;ii<listctr;ii++)              //~1A21I~//~v@@@R~
                {                                                  //~1319I~
                	int idx=sortout[ii].intValue();                //~1A21I~
                	File file=filelist[idx];                       //~1A21I~
                    name=file.getName();                           //~1319I~
                    name2file[ii]=idx;                          //~1A21I~//~v@@@R~
                    namelist[ii]=name;                        //~1319I~//~v@@@R~
		        	if (Dump.Y) Dump.println("FileDialog sorted file="+name);//~1506R~//~v@@@I~
                }                                                  //~1319I~
		        if (Dump.Y) Dump.println("FileDialog sorted namelist="+Arrays.toString(namelist));//~v@@@I~
            }                                                      //~1319I~
        }                                                          //~1319I~
        catch(SecurityException e)                                 //~1319I~
        {                                                          //~1319I~
        	Dump.println(e,"FileDialog createList Security");      //~1A2fI~
            UView.showToast(R.string.FailedListDirBySecurity);   //~1403I~//~1A21R~
//          namelist=null;                                         //~v@@@R~
            namelist=new String[0];                                //~v@@@I~
        }                                                          //~1319I~
		catch(Exception e)                                         //~1319I~
		{                                                          //~1319I~
        	Dump.println(e,"FileDialog createList");               //~1A2fI~
            UView.showToast(R.string.FailedListDir);           //~1403I~//~1A21R~
//          namelist=null;                                         //~v@@@R~
            namelist=new String[0];                                //~v@@@I~
        }                                                          //~1319I~
      }//!scoped                                                   //~vae0I~
    }//createList                                                  //~1402R~
    //*****************************************************************//~vae0I~
    protected void createListScoped(String Ppath,String Pfilter)   //~vae0I~
    {                                                              //~vae0I~
    //************************                                     //~vae0I~
        if (Dump.Y) Dump.println("FileDialog.createListScoped path="+Ppath+",filter="+Pfilter);//~vae0I~
        namelist=new String[0];                                    //~vae0I~
        ArrayList<UScoped.TreeMember> filelist=AG.aUScoped.getTreeMember(Ppath,Pfilter);//~vae0I~
        filelistScoped=filelist;                                   //~vae0I~
            if(filelist==null||filelist.size()==0)                 //~vae0I~
            {                                                      //~vae0I~
				Alert.showMessage(alertTitle,AG.resource.getString(R.string.NoFileFound,filterName));//~vae0I~
            }                                                      //~vae0I~
            else                                                   //~vae0I~
            {                                                      //~vae0I~
                String name;                                       //~vae0I~
            	int listctr=filelist.size();                       //~vae0I~
            	name2file=new int[listctr];  //namelist->filelist  //~vae0I~
                namelist=new String[listctr];                      //~vae0I~
                Integer[] sortout=sortFileListScoped(); //sorted index to filelist//~vae0I~
                for (int ii=0;ii<listctr;ii++)                     //~vae0I~
                {                                                  //~vae0I~
                	int idx=sortout[ii].intValue();                //~vae0I~
                	UScoped.TreeMember file=filelistScoped.get(idx);                 //~vae0I~
                    name=file.name;                              //~vae0I~
                    name2file[ii]=idx;                             //~vae0I~
                    namelist[ii]=name;                             //~vae0I~
		        	if (Dump.Y) Dump.println("FileDialog.createListScoped sorted file="+name);//~vae0I~
                }                                                  //~vae0I~
		        if (Dump.Y) Dump.println("FileDialog.createListScoped sorted namelist="+Arrays.toString(namelist));//~vae0I~
            }                                                      //~vae0I~
    }//createList                                                  //~vae0I~
    //*****************************************************************//~1A21I~
    protected Integer[]  sortFileList()                                   //~1A21I~//~v@@@R~
    {                                                              //~1A21I~
    	int filectr=filelist.length;                               //~1A21I~
    	Integer[] idx=new Integer[filectr];                        //~1A21I~
        for (int ii=0;ii<filectr;ii++)                             //~1A21I~
        	idx[ii]=ii;                                            //~1A21I~
        if (Dump.Y) Dump.println("FileDialog.sortFileList ctr="+filectr);//~v@@@I~
    	Arrays.sort(idx,new DataComparator());                      //~1A21I~
        return idx;                                                //~1A21I~
    }                                                              //~1A21I~
    //**********************************                           //~1A21I~
    class DataComparator implements Comparator<Integer>                      //~1A21I~
    {                                                              //~1A21I~
        public int compare(Integer P1,Integer P2)                     //~1A21I~
        {                                                          //~1A21I~
        	int rc=0;                                              //~1A21I~
        //*****************************                            //~1A21I~
        	int i1=P1.intValue();                       //~1A21I~
        	int i2=P2.intValue();                       //~1A21I~
        	File f1=filelist[i1];                                  //~1A21I~
        	File f2=filelist[i2];                                  //~1A21I~
            if (Dump.Y) Dump.println("FileDialog compare f1="+i1+"="+f1.getName()+",f2="+i2+"="+f2.getName());//~1A4aI~
            if(f1.isDirectory())                                   //~1A21I~
            	if (f2.isDirectory())                              //~1A21I~
                {	                                               //~1A21I~
                	rc=f1.getName().compareTo(f2.getName());       //~1A21R~
                }                                                  //~1A21I~
                else                                               //~1A21I~
                	rc=-1;	//dir first                            //~1A21I~
            else                                                   //~1A21I~
            	if (f2.isDirectory())                              //~1A21I~
                	rc=1;                                          //~1A21I~
                else                                               //~1A21I~
                {                                                  //~1A21I~
                	rc=f1.getName().compareTo(f2.getName());       //~v@@@I~
                    if (swDiscendant)                              //~v@@@I~
                    	rc=-rc;                                    //~v@@@I~
                }                                                  //~1A21I~
            if (Dump.Y) Dump.println("FileDialog compare rc="+rc); //~1A4aI~
            return rc;                                             //~1A21I~
        }                                                          //~1A21I~
    }                                                              //~1A21I~
    //*****************************************************************//~vae0I~
    protected Integer[]  sortFileListScoped()                      //~vae0I~
    {                                                              //~vae0I~
    	int filectr=filelistScoped.size();                         //~vae0I~
    	Integer[] idx=new Integer[filectr];                        //~vae0I~
        for (int ii=0;ii<filectr;ii++)                             //~vae0I~
        	idx[ii]=ii;                                            //~vae0I~
        if (Dump.Y) Dump.println("FileDialog.sortFileList ctr="+filectr);//~vae0I~
    	Arrays.sort(idx,new DataComparatorScoped());               //~vae0I~
        return idx;                                                //~vae0I~
    }                                                              //~vae0I~
    //**********************************                           //~vae0I~
    class DataComparatorScoped implements Comparator<Integer>      //~vae0I~
    {                                                              //~vae0I~
        public int compare(Integer P1,Integer P2)                  //~vae0I~
        {                                                          //~vae0I~
        	int rc=0;                                              //~vae0I~
        //*****************************                            //~vae0I~
        	int i1=P1.intValue();                                  //~vae0I~
        	int i2=P2.intValue();                                  //~vae0I~
        	UScoped.TreeMember f1=filelistScoped.get(i1);                      //~vae0I~
        	UScoped.TreeMember f2=filelistScoped.get(i2);                            //~vae0I~
            rc=f1.name.compareTo(f2.name);                     //~vae0I~
            if (swDiscendant)                                      //~vae0I~
            	rc=-rc;                                            //~vae0I~
            if (Dump.Y) Dump.println("FileDialog compare rc="+rc+",swDiscendant="+swDiscendant+",f1="+i1+"="+f1.name+",f2="+i2+"="+f2.name);//+vae0R~
            return rc;                                             //~vae0I~
        }                                                          //~vae0I~
    }                                                              //~vae0I~
    //**********************************                           //~1402I~
    protected void updateList()                    //~1402I~       //~v@@@R~
    {                                                              //~1402I~
    	if (Dump.Y) Dump.println("FileDialog:updateAdapter");//~1506R~
    	createList();                                      //~1402I~//~v@@@R~
        lvFilename.clearList();                                        //~1403I~//~v@@@R~
//      if (namelist==null)                                        //~1403I~//~v@@@R~
        if (namelist==null || namelist.length==0)                  //~v@@@I~
        	return;                                                //~1403I~//~v@@@M~
    	setList();                                                 //~1403I~
    }//FileList                                                    //~1402R~
    //****************************************************************//~v@@@I~
    @Override //UListViewI                                         //~v@@@I~
    public View getViewCustom(int Ppos, View Pview, ViewGroup Pparent)//~v@@@I~
    {                                                              //~v@@@I~
    //*******************                                          //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog:getViewCustom Ppos="+Ppos);//~v@@@I~
        View v=Pview;                                              //~v@@@I~
        if (v == null)                                           //~v@@@I~
        {                                                        //~v@@@I~
            v=AG.inflater.inflate(lvFilename.rowId,null);          //~v@@@R~
        }                                                        //~v@@@I~
        TextView v1=(TextView)UView.findViewById(v,R.id.FileName);//~v@@@R~
        TextView v2=(TextView)UView.findViewById(v,R.id.Timestamp);//~v@@@I~
//      View v1C=(View)UView.findViewById(v,R.id.FileNameContainer);//~v@@@R~
        UListView.UListViewData ld=lvFilename.arrayData.get(Ppos); //~v@@@R~
        v1.setText(ld.itemtext);                                   //~v@@@R~
        v2.setText(ld.itemtext2);                                  //~v@@@R~
//      if (ld.choosed)                                            //~v@@@R~
        boolean swSelected=isSelectedItem(Ppos);                   //~v@@@I~
        if (swSelected)                                            //~v@@@I~
        {                                                          //~v@@@I~
//          v1C.setBackgroundColor(lvFilename.bgColorSelected);    //~v@@@R~
            v1.setBackgroundColor(lvFilename.bgColorSelected);     //~v@@@I~
            v1.setTextColor(lvFilename.bgColor);                              //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//          v1C.setBackgroundColor(lvFilename.bgColor);            //~v@@@R~
            v1.setBackgroundColor(lvFilename.bgColor);             //~v@@@I~
            v1.setTextColor(Color.BLACK);                          //~v@@@I~
        }                                                          //~v@@@I~
        return v;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected boolean isSelectedItem(int Ppos)                      //~v@@@I~
    {                                                              //~v@@@I~
        UListView.UListViewData ld=lvFilename.arrayData.get(Ppos); //~v@@@I~
		return ld.choosed;                                        //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    //*if COICEMODE_SINGLE                                         //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override //ListViewI
    public void onListItemClicked(int Ppos,int Pselectedpos)      //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog.onListItemClicked pos="+Ppos);//~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    //*if COICEMODE_MULTIPLE,return 0 if selectable                //~v@@@R~
    //******************************************
    @Override //ListViewI
    public int onListItemClickedMultiple(int Ppos)                //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog.onListItemClickedMultiple pos="+Ppos);//~v@@@I~
        return 0;                                                  //~v@@@R~
	}                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onClickOther(int Pbuttonid)                        //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting:onClickButton id="+Pbuttonid);//~v@@@I~
    	getSelectedList();                                         //~v@@@I~
        switch(Pbuttonid)                                          //~v@@@I~
        {                                                          //~v@@@I~
            case R.id.SelectAll:                                   //~v@@@M~
                onClickSelectAll();                                           //~1Ah8I~//~v@@@M~
                break;                                             //~v@@@M~
            case R.id.DeSelectAll:                                 //~v@@@M~
                onClickDeSelectAll();                                         //~1Ah8I~//~v@@@M~
                break;                                             //~v@@@M~
            case R.id.Load:                                        //~v@@@I~
                onClickLoad();                                     //~v@@@I~
                break;                                             //~v@@@I~
            case R.id.Save:                                        //~v@@@I~
                onClickSave();                                     //~v@@@I~
                break;                                             //~v@@@I~
            case R.id.Open:                                            //~v@@@I~
                onClickOpen();                                     //~v@@@R~
                break;                                                 //~v@@@I~
            case R.id.Delete:                                          //~v@@@I~
                onClickDelete();                                       //~1403I~//~v@@@R~
                break;                                                 //~v@@@I~
            case R.id.SwitchToSD:                                  //~v@@@I~
                onClickSwitchToSD();                               //~v@@@I~
                break;                                             //~v@@@I~
            case R.id.SwitchToFiles:                               //~v@@@I~
                onClickSwitchToFiles();                            //~v@@@I~
                break;                                             //~v@@@M~
            case R.id.Backup:                                      //~v@@@I~
                onClickBackup();                                   //~v@@@I~
                break;                                             //~v@@@I~
            case R.id.Restore:                                     //~v@@@I~
                onClickRestore();                                  //~v@@@I~
                break;                                             //~v@@@I~
        }//~v@@@I~
    }                                                              //~1403R~
    //******************************************************************//~1A21I~//~v@@@M~
    private void onClickLoad()                                    //~1A21I~//~v@@@M~
    {                                                              //~1A21I~//~v@@@M~
    	if (!getSelectedFilename(true/*toast if not selected*/))   //~v@@@R~
        	return;                                                //~v@@@M~
        callbackLoad(selectedFilename,selectedFullpath);           //~v@@@R~
    }                                                              //~1A21I~//~v@@@M~
    //**********************************************************************//~1402I~
    private void onClickSave()                                 //~1403R~//~v@@@R~
    {                                                              //~1402I~
    	saveFile();                                                //~v@@@R~
    }                                                              //~1402I~
    //**********************************************************************//~1403I~//~v@@@M~
    private void onClickOpen()                                 //~1403I~//~v@@@M~
    {                                                              //~1403I~//~v@@@M~
    	int selectedstatus;                                        //~1403I~//~v@@@M~
        boolean rc=false;                                          //~1403I~//~v@@@M~
    //***********************                                                          //~1403I~//~v@@@M~
    	if (!getSelectedFilename(true/*toast if not selected*/))   //~v@@@R~
        	return;                                                //~v@@@M~
        openFileViewer(selectedFilename,selectedFullpath);         //~1A4zI~//~v@@@M~
    }                                                              //~1403I~//~v@@@M~
    //**********************************************************************//~1402I~
    //* delete                                                     //~1403R~
    //**********************************************************************//~1402I~
    protected void onClickDelete()                                  //~1402I~//~v@@@R~
    {                                                              //~1402I~
        int ctr=multipleSelected.length;                      //~1A4nR~
	    if (Dump.Y) Dump.println("FileDialog deletedMultiple chk ctr="+ctr);//~1A4nR~
        if (ctr==0)                                                //~1A4nR~
        {                                                          //~1A4nR~
        	UView.showToast(R.string.ErrFileNotSelected);         //~1A4nR~//~v@@@R~
        }                                                          //~1A4nR~
        else                                                       //~1A4nR~
        {                                                          //~1A4nR~
        	String msg;                                            //~v@@@I~
        	if (ctr==1)                                            //~v@@@I~
            {                                                      //~v@@@I~
    			getSelectedFilenameMulti(0);                       //~v@@@I~
        		msg=Utils.getStr(R.string.FileDialogDeleteQuestionFile,selectedFilename);//~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
        		msg=Utils.getStr(R.string.FileDialogDeleteQuestion,ctr);//~1A4nR~//~v@@@R~
            swDelete=true;                                         //~v@@@I~
			Alert.showAlert(alertTitle,msg,Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,this);//calback alertButtonAction//~1A4nR~//~v@@@R~
        }                                                          //~1A4nR~
    }                                                              //~1A4nR~
    //*********************************************************************//~1A4nR~
	public int doDelete(int Pbuttonid)                     //~1A4nR~//~v@@@R~
    {                                                              //~1A4nR~
    	int rc=0;                                                  //~1A4nR~
    	boolean agree=(Pbuttonid==Alert.BUTTON_POSITIVE);          //~1A4nR~
	    if (Dump.Y) Dump.println("FileDialog dodelete agree="+agree);//~v@@@I~
        if (!agree)                                                //~1A4nR~
        	return rc;                                             //~1A4nR~
    	try                                                        //~1A4nR~
        {                                                          //~1A4nR~
            int ctr=multipleSelected.length;                  //~1A4nR~//~v@@@R~
	        if (Dump.Y) Dump.println("FileDialog dodelete selectedctr="+ctr);//~v@@@I~
            int delctr=0;                                          //~1A4nR~
            for (int ii=0;ii<ctr;ii++)                             //~1A4nR~
            {                                                      //~1A4nR~
    			if (getSelectedFilenameMulti(ii))                  //~v@@@R~
                {                                                  //~1A4nR~
//      			boolean rc2=selectedFile.delete();                         //~1A4nR~//~v@@@R~//~vae0R~
        			boolean rc2;                                   //~vae0I~
                  if (swScoped)                                    //~vae0I~
                  {                                                //~vae0I~
        			rc2=deleteScoped(selectedFileScoped);          //~vae0I~
                  }                                                //~vae0I~
        		  else                                             //~vae0I~
                  {                                                //~vae0I~
        			rc2=selectedFile.delete();                     //~vae0I~
	        		if (Dump.Y) Dump.println("FileDialog deleted rc2="+rc2+",file="+selectedFile.getName());//~1A4nR~//~v@@@R~//~vae0M~
                  }                                                //~vae0I~
                    delctr++;                                      //~1A4nR~
                }                                                  //~1A4nR~
            }                                                      //~1A4nR~
        	String msg=Utils.getStr(R.string.FileDialogDeleted,delctr);//~1A4nR~//~v@@@R~
            UView.showToast(msg);                                  //~1A4nR~
		    updateList();                                   //~1A4nR~
        }                                                          //~1A4nR~
        catch (Exception e)                                        //~1A4nR~
        {                                                          //~1A4nR~
        	Dump.println(e,"FileDialog:deleteMultiple ButtonAction");//~1A4nR~
            UView.showToast(R.string.FailedDelete);                //~1A4nR~
        	rc=0;                                                  //~1A4nR~
        }                                                          //~1A4nR~
    	return rc;                                                 //~1A4nR~
    }                                                              //~1A4nR~
    //*********************************************************************//~v@@@I~
	public boolean chkDuplicated(String Pfname)                     //~v@@@I~
    {                                                              //~v@@@I~
    	int ctr=namelist.length;                                   //~v@@@R~
	    if (Dump.Y) Dump.println("FileDialog.chkDuplicated search="+Pfname+",namelistctr="+ctr);//~v@@@R~//~vae0R~
        String fnm=Pfname+filterName;                              //~v@@@I~
        for (int ii=0;ii<ctr;ii++)                                 //~v@@@I~
        {                                                          //~v@@@I~
	    	if (Dump.Y) Dump.println("FileDialog.chkDuplicated ii="+ii+",name="+namelist[ii]);//~v@@@R~//~vae0R~
            String n=namelist[ii];                                 //~v@@@I~
        	if (n.equals(fnm))                                     //~v@@@R~
            {                                                      //~v@@@R~
                return true;                                       //~v@@@R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("FileDialog.chkDuplicated not found");       //~v@@@I~//~vae0R~
    	return false;                                              //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public String makeFullpath(String Pfname)                      //~v@@@R~
    {                                                              //~v@@@I~
    	String path=dirName+AG.dirSep+Pfname+filterName;           //~v@@@R~
	    if (Dump.Y) Dump.println("makeFullpath="+path);            //~v@@@R~
    	return path;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
    protected String getSaveFilename()                             //~v@@@I~
    {                                                              //~v@@@I~
    	String fnm=etFileName.getText().toString();                //~v@@@I~
        return fnm;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    protected boolean saveFile()                                   //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog:saveFile");           //~v@@@I~
    	saveFilename=null;                                         //~v@@@I~
//  	String fnm=etFileName.getText().toString();                //~v@@@R~
    	String fnm=getSaveFilename();                              //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog:saveFile fnm="+fnm);  //~v@@@I~
        boolean swOverride=false;                                  //~v@@@I~
        if (fnm.equals(""))                                        //~v@@@I~
        {                                                          //~v@@@I~
        	if (getSelectedFilename(false/*no msg*/))              //~v@@@I~
            {                                                      //~v@@@I~
            	swOverride=true;                                   //~v@@@R~
                saveFilename=selectedFilename;                     //~v@@@R~
                saveFullpath=selectedFullpath;                     //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
	            UView.showToast(R.string.ErrSaveFileNotSelected);  //~v@@@I~
                return false;                                      //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	if (chkDuplicated(fnm))                                //~v@@@I~
            {                                                      //~v@@@I~
            	swOverride=true;                                   //~v@@@R~
//              saveFullpath=selectedFullpath;                     //~v@@@R~
            	saveFullpath=makeFullpath(fnm);                    //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            	saveFullpath=makeFullpath(fnm);                    //~v@@@R~
            saveFilename=fnm+filterName;                           //~v@@@R~
        }                                                          //~v@@@I~
        if (swOverride)                                           //~v@@@I~
        {                                                          //~v@@@I~
	    	swDelete=false;                                        //~v@@@I~
        	String msg=Utils.getStr(R.string.SaveOverrideQuestion,saveFilename);//~v@@@I~
			Alert.showAlert(alertTitle,msg,Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,this);//calback alertButtonAction//~v@@@I~
            return false;                                          //~v@@@I~
        }                                                          //~v@@@I~
	    doSave(0);                                                 //~v@@@R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    private int doSave(int Pbtnid)                                 //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog:doSave saveFullpath="+saveFullpath);//~v@@@R~
    	int rc=0;                                                  //~v@@@I~
    	boolean agree=(Pbtnid==0||Pbtnid==Alert.BUTTON_POSITIVE);//~v@@@I~
        if (!agree)                                                //~v@@@I~
        	return rc;                                             //~v@@@I~
	    callbackSave(saveFilename,saveFullpath);                   //~v@@@R~
    	return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~1Ah8I~
    protected void onClickSelectAll()                                       //~1Ah8I~//~v@@@R~
    {                                                              //~1Ah8I~
        if (Dump.Y) Dump.println("FileDialog:selectAll");//~1Ah8R~ //~v@@@R~
        lvFilename.selectAll();                                    //~v@@@R~
    }                                                              //~1Ah8I~
    //**************************************                       //~1Ah8I~
    protected void onClickDeSelectAll()                                     //~1Ah8I~//~v@@@R~
    {                                                              //~1Ah8I~
        if (Dump.Y) Dump.println("FileDialog:deselectAll");        //~1Ah8I~
        lvFilename.deselectAll();                                      //~1Ah8R~//~v@@@R~
    }                                                              //~1Ah8I~
    //**************************************                       //~v@@@I~
    private void openFileViewer(String Pfname,String Pfullpath)     //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog:openFileViewer:filename="+Pfname);//~v@@@I~
//      FileViewer.newInstance(Pfname,Pfullpath).show();           //~v@@@R~
//      FileViewer.newInstance(Pfname,Pfullpath,swU2S).show();     //~v@@@I~//~vae0R~
        FileViewer.newInstance(Pfname,Pfullpath,swU2S,swScoped).show();//~vae0I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    private void callbackLoad(String Pfname,String Pfullpath)      //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog:callbackLoad:filename="+Pfname);//~v@@@I~
    	if (listener!=null)                                        //~v@@@I~
	        if (listener.fileDialogLoadCB(Pfullpath))              //~v@@@R~
	            UView.showToast(Utils.getStr(R.string.InfoLoaded,Pfname));//~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    private void callbackSave(String Pfname,String Pfullpath)      //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("FileDialog:callbackSave:filename="+Pfullpath);//~v@@@R~
    	if (listener!=null)                                        //~v@@@I~
	    	if (listener.fileDialogSaveCB(Pfullpath))              //~v@@@R~
            {                                                      //~v@@@I~
			    updateList();                                      //~v@@@I~
	            UView.showToast(Utils.getStr(R.string.InfoSaved,Pfname));//~v@@@I~
            }                                                      //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    @Override //* AlertI                                           //~v@@@I~
	public int alertButtonAction(int Pbuttonid,int Pparm/*not used,value set by setSimpleData*/)//~1A4nR~//~v@@@M~
    {                                                              //~1A4nR~//~v@@@M~
    	int rc=0;                                                    //~1A4nR~//~v@@@R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	if (swDelete)                                          //~v@@@I~
				rc=doDelete(Pbuttonid);                            //~1A4nR~//~v@@@R~
        	else                                                   //~v@@@I~
				rc=doSave(Pbuttonid);                              //~v@@@I~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
        	Dump.println(true/*messagedialog*/,e,"FileDialog:alerButtonAction");//~v@@@I~
        	rc=0;                                                  //~v@@@I~
        }                                                          //~v@@@I~
    	return rc;                                                 //~1A4nR~//~v@@@M~
    }                                                              //~1A4nR~//~v@@@M~
    //**************************************                       //~v@@@I~
    private void onClickSwitchToSD()                               //~v@@@I~
    {                                                              //~v@@@I~
    	if (workDirSD==null)                                       //~v@@@I~
        {                                                          //~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
    	swSD=true;                                                 //~v@@@R~
        updateList();                                              //~v@@@I~
        setButtonVisibility();                                     //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    private void onClickSwitchToFiles()                            //~v@@@I~
    {                                                              //~v@@@I~
    	swSD=false;                                                //~v@@@I~
        setButtonVisibility();                                     //~v@@@I~
        updateList();                                              //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    private void onClickBackup()                                   //~v@@@I~
    {                                                              //~v@@@I~
        if (workDirSD==null)                                       //~v@@@I~
        	return;                                                //~v@@@I~
    	int ctr=multipleSelected.length;                           //~v@@@I~
        if (ctr==0)                                                //~v@@@I~
        {                                                          //~v@@@I~
	        UView.showToast(R.string.ErrFileNotSelected);          //~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
    	int copyctr=0;                                             //~v@@@I~
	    if (Dump.Y) Dump.println("onClickBackup selected ctr="+ctr);//~v@@@I~
        for (int ii=0;ii<ctr;ii++)                                 //~v@@@I~
        {                                                          //~v@@@I~
    		if (getSelectedFilenameMulti(ii))                      //~v@@@I~
            {                                                      //~v@@@I~
	            if (Dump.Y) Dump.println("onClickBackup ii="+ii+",name="+selectedFullpath);//~v@@@I~
                String tgtfnm=UFile.makePathName(selectedFullpath,workDirSD);//~v@@@I~
        		if (!UFile.copyFile(selectedFullpath,tgtfnm))     //~v@@@R~
                	return;                                        //~v@@@I~
        		UView.showToast(Utils.getStr(R.string.InfoCopyedFile,selectedFilename));//~v@@@I~
                copyctr++;                                         //~v@@@R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        UView.showToast(Utils.getStr(R.string.InfoCopyedFileCtr,copyctr));//~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    private void onClickRestore()                                  //~v@@@I~
    {                                                              //~v@@@I~
        if (workDirSD==null)                                       //~v@@@I~
        	return;                                                //~v@@@I~
    	int ctr=multipleSelected.length;                           //~v@@@I~
        if (ctr==0)                                                //~v@@@I~
        {                                                          //~v@@@I~
	        UView.showToast(R.string.ErrFileNotSelected);          //~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
    	int copyctr=0;                                             //~v@@@I~
	    if (Dump.Y) Dump.println("onClickRestore selected ctr="+ctr);//~v@@@I~
        for (int ii=0;ii<ctr;ii++)                                 //~v@@@I~
        {                                                          //~v@@@I~
    		if (getSelectedFilenameMulti(ii))                      //~v@@@I~
            {                                                      //~v@@@I~
	            if (Dump.Y) Dump.println("onClickRestore ii="+ii+",name="+selectedFullpath);//~v@@@I~
                String tgtfnm=UFile.makePathName(selectedFullpath,pathDataDir);//~v@@@I~
        		if (!UFile.copyFile(selectedFullpath,tgtfnm))     //~v@@@R~
                	return;                                        //~v@@@I~
        		UView.showToast(Utils.getStr(R.string.InfoRestoredFile,selectedFilename));//~v@@@I~
                copyctr++;                                         //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        UView.showToast(Utils.getStr(R.string.InfoCopyedFileCtr,copyctr));//~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    protected void getSelectedList()                               //~v@@@R~
    {                                                              //~v@@@M~
	    multipleSelected=lvFilename.getCheckedItemPositions();    //~1A4nR~//~v@@@M~
        if (Dump.Y) Dump.println("FileDialog.getSelectedList ctr="+multipleSelected.length+"="+Arrays.toString(multipleSelected));//~v@@@R~
    }                                                              //~v@@@M~
    //*************************************************************//~v@@@I~
    private boolean getSelectedFilename(boolean Pswmsg)            //~v@@@M~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("FileDialog.getSelectedFilename swmsg="+Pswmsg);//~vae0I~
    	int ctr=multipleSelected.length;                           //~v@@@M~
        if (ctr==0)                                                //~v@@@M~
        {                                                          //~v@@@M~
        	if (Pswmsg)                                            //~v@@@M~
	            UView.showToast(R.string.ErrFileNotSelected);      //~v@@@M~
            return false;                                          //~v@@@M~
        }                                                          //~v@@@M~
        if (ctr>1)                                                 //~v@@@M~
        {                                                          //~v@@@M~
        	if (Pswmsg)                                            //~v@@@M~
	            UView.showToast(R.string.ErrFileMultipleSelected); //~v@@@M~
            return false;                                          //~v@@@M~
        }                                                          //~v@@@M~
        int pos=multipleSelected[0];                               //~v@@@M~
        int filelistpos=name2file[pos];                            //~v@@@M~
      if (swScoped)                                                //~vae0I~
      {                                                            //~vae0I~
        selectedFileScoped=filelistScoped.get(filelistpos);            //~vae0I~
        selectedFilename=namelist[pos];                            //~vae0I~
        selectedFullpath=AG.aUScoped.getFullpath(selectedFilename); //~vae0R~
      }                                                            //~vae0I~
      else                                                         //~vae0I~
      {                                                            //~vae0I~
        selectedFile=filelist[filelistpos];                        //~v@@@M~
        selectedFilename=namelist[pos];                            //~v@@@M~
        selectedFullpath=selectedFile.getAbsolutePath();           //~v@@@M~
      }                                                            //~vae0I~
        return true;                                               //~v@@@M~
    }                                                              //~v@@@M~
    //**********************************************************   //~v@@@M~
    //*for multiple selected                                       //~v@@@M~
    //**********************************************************   //~v@@@M~
    protected boolean getSelectedFilenameMulti(int Ppos)           //~v@@@R~
    {                                                              //~v@@@M~
        int pos=multipleSelected[Ppos];                            //~v@@@M~
        if (Ppos>=multipleSelected.length)                         //~v@@@M~
        	return false;                                          //~v@@@M~
        int filelistpos=name2file[pos];                            //~v@@@M~
      if (swScoped)                                                //~vae0I~
      {                                                            //~vae0I~
        selectedFileScoped=filelistScoped.get(filelistpos);            //~vae0I~
        selectedFilename=namelist[pos];                            //~vae0I~
        selectedFullpath=selectedFilename;                         //~vae0I~
      }                                                            //~vae0I~
      else                                                         //~vae0I~
      {                                                            //~vae0I~
        selectedFile=filelist[filelistpos];                        //~v@@@M~
        selectedFilename=namelist[pos];                            //~v@@@M~
        selectedFullpath=selectedFile.getAbsolutePath();           //~v@@@M~
      }                                                            //~vae0I~
        if (Dump.Y) Dump.println("FileDialog.getSelectedFilenameMulti pos="+Ppos+",filename="+selectedFilename);//~v@@@M~//~vae0R~
        return true;                                               //~v@@@M~
    }                                                              //~v@@@M~
    //**********************************************************   //~vae0I~
    private boolean deleteScoped(UScoped.TreeMember Pmember)               //~vae0I~
    {                                                              //~vae0I~
        if (Dump.Y) Dump.println("getSelectedFilenameScoped");//~vae0I~
        boolean rc=AG.aUScoped.deleteMember(Pmember.name);	       //~vae0R~
        return rc;                                               //~vae0I~
    }                                                              //~vae0I~
}//class                                                           //~1318R~
