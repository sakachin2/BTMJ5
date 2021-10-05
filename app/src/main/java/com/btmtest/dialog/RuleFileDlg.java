//*CID://+vae0R~:                             update#=  495;       //+vae0R~
//*****************************************************************//~v101I~
//2021/09/12 vae0 Scped for BTMJ5                                  //+vae0I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.CompoundButton;

import com.btmtest.R;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.UListView;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UScoped;
import com.btmtest.utils.Utils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import static com.btmtest.AG.*;
import static com.btmtest.StaticVars.AG;                           //~9305I~//~9B09I~
import static com.btmtest.game.History.*;
import static com.btmtest.gui.UListView.*;

public class RuleFileDlg extends FileDialog                //~v@@@R~     //~9613R~//~9614R~//~9616R~
            implements UCheckBox.UCheckBoxI                        //~0114I~
{                                                                  //~2C29R~
//    private static final int TITLEID_LOAD=R.string.Title_RuleFileDlgLoad;    //~v@@@R~//~9613M~//~9616R~//~0114R~
//    private static final int TITLEID_SAVE=R.string.Title_RuleFileDlgSave;//~9616I~//~0114R~
    private static final int TITLEID=R.string.Title_RuleFileDlg;   //~0114I~
    private static final int HELP_TITLEID=R.string.Title_RuleFileDlg;//~9616R~
    private static final String HELPFILE="RuleFileDlg";           //~9614I~//~9616R~//~9C13R~
    private static final int LAYOUTID=R.layout.rulefiledlg;         //~9614I~//~9616R~
    private static final int LAYOUTID_SMALLFONT=R.layout.rulefiledlg_theme;//~vac5I~
    private static final String PREFIX_SYNCDATE="._";              //~9B09I~
    private static final int UCBP_MULTISELECT=1;                   //~0114I~
                                                                   //~0114I~
    private String fnmSyncDate;                                    //~9B09I~
    private UCheckBox cbMultiSelect;                               //~0114I~
    private boolean swMultiSelect;                                 //~0114I~
                                                                   //~9613I~
    //******************************************                   //~v@@@R~
    public RuleFileDlg()                                            //~v@@@R~//~9613R~//~9616R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("RuleFileDlg.defaultConstructor"); //~9614R~//~9616R~
        swU2S=true;	//display \\uxxxx by string                     //~9617R~
        swScoped= UScoped.isScoped();    //protected on FileDialog  //+vae0I~
    }                                                              //~v@@@R~
	//*****************************************************************//~9616I~
    public static RuleFileDlg newInstance(String Pfilter,boolean Pswload)//~9616I~
    {                                                              //~9616I~
    	RuleFileDlg dlg=new RuleFileDlg();                         //~9616R~
//  	UFDlg.setBundle(dlg,(Pswload?TITLEID_LOAD:TITLEID_SAVE),LAYOUTID,//~9616I~//~0114R~
//  	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~0114I~//~vac5R~
    	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//~vac5I~
                    UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN,HELP_TITLEID,HELPFILE);//~9616I~//~9C13R~
        Bundle b=dlg.bundle;                                       //~9616I~
        b.putString(PARM_FILTER,Pfilter);                          //~9616I~
        b.putBoolean(PARM_SWLOAD,Pswload);                         //~9616I~
        dlg.swDiscendant=true;                                         //~9901I~
    	return dlg;                                                //~9616I~
    }                                                              //~9616I~
    //******************************************                   //~0114I~
    protected void getComponent(View Playoutview)                  //~0114I~
    {                                                              //~0114I~
    	super.getComponent(Playoutview);                                      //~0114I~
        cbMultiSelect     =new UCheckBox(Playoutview,R.id.cbMultiSelect);//~0114I~
        cbMultiSelect.setListener(this,UCBP_MULTISELECT);          //~0114I~
	    lvFilename.setChoiceMode(CHOICEMODE_SINGLE);	//default also deselected//~0114I~
    }                                                              //~0114I~
	//************************************                         //~9616I~
    @Override                                                      //~9616I~
    protected void setButtonVisibility()                           //~9616I~
    {                                                              //~9616I~
        if (Dump.Y) Dump.println("RuleFileDlg.setButtonVisibility");//~9616I~
        if (workDirSD!=null)                                       //~9616I~
        {                                                          //~9616I~
        	if (Dump.Y) Dump.println("RuleFileDlg.setButtonVisibility workDirSD="+workDirSD);//~9616I~
            swSD=true;                                             //~9616I~
        }                                                          //~9616I~
        super.setButtonVisibility();                              //~9616I~
        setTitle();                                                //~9617I~
    }                                                              //~9616I~
	//************************************                         //~9617I~
    private void setTitle()                                        //~9617I~
    {                                                              //~9617I~
//  	String title=Utils.getStr(swLoad ? TITLEID_LOAD : TITLEID_SAVE)+"："+((workDirSD!=null) ? workDirSD : pathDataDir);//~9617R~//~0114R~
    	String action=Utils.getStr(swLoad ? R.string.Load : R.string.Save);//~0114I~
    	String path=(workDirSD!=null) ? workDirSD : pathDataDir;   //~0114I~
//      Spanned title= Html.fromHtml(Utils.getStr(R.string.Title_RuleFileDlgFolder,action,path));//~va40R~
        Spanned title=Utils.fromHtml(Utils.getStr(R.string.Title_RuleFileDlgFolder,action,path));//~va40I~
        getDialog().setTitle(title);                                           //~9617I~
    }                                                              //~9617I~
    //*********************************************************************//~9B09I~
    //*append syncdate becuase setLastModified fails for SDCard    //~9B09I~
    //*********************************************************************//~9B09I~
    protected String getSaveFilename()                             //~9B09I~
    {                                                              //~9B09I~
    	String fnm=etFileName.getText().toString();                //~9B09I~
        fnm=fnmSyncDate;                                           //~9B09R~
        if (Dump.Y) Dump.println("RuleFileDlg.getSaveFilename fnm="+fnm);//~9B09I~
        return fnm;                                                //~9B09I~
    }                                                              //~9B09I~
    //*********************************************************************//~9B09I~
    //*dialogToProperty to get Synchdate                            //~9B09I~//~vac5R~
    //*********************************************************************//~9B09I~
    protected boolean saveFile()                                   //~9B09I~
    {                                                              //~9B09I~
        if (Dump.Y) Dump.println("RuleFileDlg.saveFile");          //~9B09I~
        if (!Utils.isShowingDialogFragment(AG.aRuleSetting))        //~9408I~//~9B09R~
        	return false;                                          //~9B09I~
        String fnm=super.getSaveFilename(); //without syncdate     //~9B09I~
	    String syncDate=AG.aRuleSetting.preFileDialogSaveCB(fnm);//yyyy/MM/dd-HH.mm.ss//~9B09I~
        fnmSyncDate=fnm+PREFIX_SYNCDATE+syncDate.replaceAll("[/.]","");//~9B09I~
        if (Dump.Y) Dump.println("RuleFileDlg.saveFile syncDate="+syncDate+",fnmSyncDate="+fnmSyncDate);//~9B09I~
        return super.saveFile();	//UfileDialog;callback getSaveFilename and SettingDlg.fileDialogSaveCB() by filename with syncdate appended//~9B09I~
    }                                                              //~9B09I~
    //*****************************************************************//~9B09I~
    protected Integer[]  sortFileList()                            //~9B09I~
    {                                                              //~9B09I~
    	int filectr=filelist.length;                               //~9B09I~
    	Integer[] idx=new Integer[filectr];                        //~9B09I~
        for (int ii=0;ii<filectr;ii++)                             //~9B09I~
        	idx[ii]=ii;                                            //~9B09I~
        if (Dump.Y) Dump.println("RuleFileDlg.sortFileList ctr="+filectr);//~9B09I~//~9C22R~
    	Arrays.sort(idx,new DataComparator());                     //~9B09I~
        return idx;                                                //~9B09I~
    }                                                              //~9B09I~
    //**********************************************************************//~0114I~
    protected void getSelectedList()                               //~0114I~
    {                                                              //~0114I~
    	if (swMultiSelect)                                         //~0114I~
        	super.getSelectedList();                               //~0114I~
        else                                                       //~0114I~
        {                                                          //~0114I~
	    	int pos=lvFilename.getValidSelectedPos();              //~0114I~
            if (pos==-1)                                           //~0114I~
            	multipleSelected=new int[0];                       //~0114I~
            else                                                   //~0114I~
            	multipleSelected=new int[]{pos};                   //~0114I~
        }                                                          //~0114I~
        if (Dump.Y) Dump.println("RuleFileDlg.getSelectedList swMultiSelect="+swMultiSelect+",multipleSelect="+Arrays.toString(multipleSelected));//~0114I~//~0115R~
    }                                                              //~0114I~
    //**********************************************************************//~0114I~
    @Override                                                      //~0114I~
    protected void onClickSelectAll()                              //~0114I~
    {                                                              //~0114I~
        if (Dump.Y) Dump.println("RuleFileDlg.onClickSelectAll");   //~0114I~//~0115R~
        if (!swMultiSelect)                                        //~0114I~
        {                                                          //~0114I~
		    cbMultiSelect.setState(true);	//listener set COICEMODE_MULTIPLE//~0114I~
        }                                                          //~0114I~
        super.onClickSelectAll();                                  //~0114I~
    }                                                              //~0114I~
    //******************************************                   //~0114I~
    @Override                                                      //~0114I~
    protected boolean isSelectedItem(int Ppos)                     //~0114R~
    {                                                              //~0114I~
    	boolean rc;                                                //~0114I~
        if (swMultiSelect)                                         //~0114I~
        {                                                          //~0114I~
        	UListView.UListViewData ld=lvFilename.arrayData.get(Ppos);//~0114I~
			rc=ld.choosed;                                         //~0114I~
        }                                                          //~0114I~
        else                                                       //~0114I~
			rc=Ppos==lvFilename.getSelectedPos();                  //~0114I~
        if (Dump.Y) Dump.println("RuleFileDlg.isSelectedItem pos="+Ppos+",swMultiSelect="+swMultiSelect+",rc="+rc);//~0114I~//~0115R~
		return rc;                                                 //~0114I~
    }                                                              //~0114I~
    //*******************************************************      //~0114I~
    //*UCheckBoxI                                                  //~0114I~
    //*******************************************************      //~0114I~
    @Override                                                      //~0114I~
    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked, int Pparm)//~0114I~
    {                                                              //~0114I~
        if (Dump.Y) Dump.println("RuleFileDlg.onChangedUCB parm="+Pparm+",isChecked="+PisChecked);//~0114I~//~0115R~
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
        if (Dump.Y) Dump.println("RuleFileDlg.changedMultiSelect sw="+PswOn+",old="+swMultiSelect);//~0114I~//~0115R~
        swMultiSelect=PswOn;                                       //~0114I~
        if (swMultiSelect)                                         //~0114I~
        {                                                          //~0114I~
	        lvFilename.setChoiceMode(CHOICEMODE_MULTIPLE);         //~0114I~
        }                                                          //~0114I~
        else                                                       //~0114I~
        {                                                          //~0114I~
	        lvFilename.setChoiceMode(CHOICEMODE_SINGLE);	//also deselected//~0114I~
        }                                                          //~v@@@I~//~0114I~
    }                                                              //~0114I~
    //**********************************                           //~9B09I~
    //**********************************                           //~9B09I~
    //**********************************                           //~9B09I~
    class DataComparator implements Comparator<Integer>            //~9B09I~
    {                                                              //~9B09I~
        public int compare(Integer P1,Integer P2)                  //~9B09I~
        {                                                          //~9B09I~
        	int rc=0;                                              //~9B09I~
        //*****************************                            //~9B09I~
        	int i1=P1.intValue();                                  //~9B09I~
        	int i2=P2.intValue();                                  //~9B09I~
        	File f1=filelist[i1];                                  //~9B09I~
        	File f2=filelist[i2];                                  //~9B09I~
            if (Dump.Y) Dump.println("RuleFileDlg compare f1="+i1+"="+f1.getName()+",f2="+i2+"="+f2.getName());//~9B09I~//~9C22R~
            if(f1.isDirectory())                                   //~9B09I~
            	if (f2.isDirectory())                              //~9B09I~
                {                                                  //~9B09I~
                	rc=f1.getName().compareTo(f2.getName());       //~9B09I~
                }                                                  //~9B09I~
                else                                               //~9B09I~
                	rc=-1;	//dir first                            //~9B09I~
            else                                                   //~9B09I~
            	if (f2.isDirectory())                              //~9B09I~
                	rc=1;                                          //~9B09I~
                else                                               //~9B09I~
                	rc=compareRuleFilename(f1.getName(),f2.getName());//~9B09I~
            if (Dump.Y) Dump.println("RuleFileDlg.compare rc="+rc); //~9B09R~//~9C22R~
            return rc;                                             //~9B09I~
        }                                                          //~9B09I~
	    //**********************************                       //~9B09I~
        private int compareRuleFilename(String Pfnm1,String Pfnm2)  //~9B09I~
        {
            int rc;//~9B09I~
            int pos1,pos2;                                         //~9B09R~
        //*************************                                //~9B09I~
            pos1=Pfnm1.lastIndexOf(EXT_HISTORY_RULE);              //~9B09I~
            pos2=Pfnm2.lastIndexOf(EXT_HISTORY_RULE);              //~9B09I~
            if (pos1>0 || pos2>0)	//move suspendgame rule file to last//~9B09I~
            {                                                      //~9B09I~
            	if (pos1>0 && pos2>0)                              //~9B09I~
	                rc=-Pfnm1.compareTo(Pfnm2);                    //~9B09I~
                else                                               //~9B09I~
            	if (pos1>0)	//pos2<0                               //~9B09I~
                	rc=1;                                          //~9B09R~
                else        //pos1< and pos2>0                     //~9B09I~
                	rc=-1;                                         //~9B09R~
            }                                                      //~9B09I~
            else                                                   //~9B09I~
            {                                                      //~9B09I~
                pos1=Pfnm1.lastIndexOf(PREFIX_SYNCDATE);           //~9B09I~
                pos2=Pfnm2.lastIndexOf(PREFIX_SYNCDATE);           //~9B09I~
                if (pos1>0 && pos2>0)                              //~9B09R~
                {                                                  //~9B09R~
                    rc=Pfnm1.substring(0,pos1).compareTo(Pfnm2.substring(0,pos2));//~9B09R~
                    if (rc==0)                                     //~9B09R~
                        rc=-Pfnm1.substring(pos1).compareTo(Pfnm2.substring(pos2));//~9B09R~
                }                                                  //~9B09R~
                else                                               //~9B09R~
                {                                                  //~9B09R~
                    pos1=Pfnm1.lastIndexOf(PROP_EXT_RULE);         //~9B09R~
                    pos2=Pfnm2.lastIndexOf(PROP_EXT_RULE);         //~9B09R~
                    if (pos1>0 && pos2>0)                          //~9B09R~
                        rc=Pfnm1.substring(0,pos1).compareTo(Pfnm2.substring(0,pos2));//~9B09R~
                    else                                           //~9B09R~
                        rc=Pfnm1.compareTo(Pfnm2);                 //~9B09R~
                }                                                  //~9B09R~
            }                                                      //~9B09I~
            if (Dump.Y) Dump.println("RuleFileDlg.compareRuleFilename fnm1="+Pfnm1+",fnm2="+Pfnm2+",rc="+rc);//~9B09R~//~9C22R~
            return rc;
        }                                                          //~9B09I~
    }                                                              //~9B09I~
}//class
