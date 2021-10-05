//*CID://+vad4R~:                             update#=   65;       //~vad4R~
//*****************************************************************
//2021/08/25 vad4 (Bug)HistoryBS; when selected not completed game issues toast but have to dismiss dialog//~vad4I~
//2021/08/25 vad3 (Bug)HistoryBS summary ctr                       //~vad3I~
//2021/08/24 vad2 HistoryBS;add function to show detail            //~vad2I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/04/13 va87 show B/S limited to a group                      //~va87I~
//2021/04/06 va7a add function of show balance sheet
//*****************************************************************
package com.btmtest.dialog;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.HistoryData;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UListView;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static com.btmtest.StaticVars.AG;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.HistoryData.*;

public class HistoryBSDlg extends UFDlg                                         //~1406R~//~vad2R~
    implements UListView.UListViewI                                //~1406I~
{
    private static final int TITLEID=R.string.Title_HistoryBSDlg;  //~1406R~
    private static final int HELP_TITLEID=TITLEID;
    private static final String HELPFILE="HistoryBSDlg";           //~1406R~
    private static final int LAYOUTID=R.layout.historybsdlg;       //~1406R~
    private static final int LAYOUTID_SMALLFONT=R.layout.historybsdlg_theme;//~vac5I~
    private static final int LISTVIEW_ROW_LAYOUTID=R.layout.textrowlist_historybsdlg;//~1406R~//~vac5R~
    private static final int LISTVIEW_ROW_LAYOUTID_SMALLFONT=R.layout.textrowlist_historybsdlg_theme;//~vac5I~
    private static final int LISTVIEW_ROW_LAYOUTID_DETAIL=R.layout.textrowlist_historydlg;//~vad2R~
    private static final int LISTVIEW_ROW_LAYOUTID_SMALLFONT_DETAIL=R.layout.textrowlist_historydlg_theme;//~vad2R~
    private static final int ID_ALONE=1;                           //~1406I~
    private static final int ID_MATCH=2;                           //~1406I~

	private HistoryDlg HD;                                         //~1406I~
	private boolean[] swRobot=new boolean[PLAYERS];                  //~1406I~
    private Map<String,BSData> mapAlone=new HashMap<String,BSData>();//~1406R~
    private Map<String,BSData> mapMatch=new HashMap<String,BSData>();//~1406R~
    private String nameRobot=Utils.getStr(R.string.Label_Robot);   //~1406I~
    private int ctrGameAlone,ctrGameMatch,ctrPending;              //~1406R~
    private UListView lvBS;                                        //~1406I~
    private TextView tvSummary;                                    //~1406I~
    private TextView tvCtrFileList;                                //~vad2I~
    private String dateFrom="";                                    //~1408I~
    private int[] selectedList;                                    //~va87I~
    private int[] selectedMember;
    private String[] selectedMemberName=new String[PLAYERS];       //~va87I~
    private int ctrRobotSelectedGame,ctrSelectedMembers;           //~va87R~
    private HistoryData hdSelectedGame;//~va87I~
    private LinearLayout llListView;                               //~vad2I~
    private UListView lvFilename;                                  //~vad2R~
    private int rowIDDetail,ctrAdd;                                //~vad2R~
    //******************************************
    public HistoryBSDlg()                                          //~1406R~
    {
        if (Dump.Y) Dump.println("HistoryBSDlg.defaultConstructor");//~1406R~
        HD=AG.aHistoryDlg;                                         //~1406I~
    }
    //******************************************
//  public static HistoryBSDlg newInstance()                       //~1406R~//~va87R~
    public static HistoryBSDlg newInstance(int[] PselectedList)    //~va87I~
    {
    	HistoryBSDlg dlg=new HistoryBSDlg();                       //~1406R~
//  	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~vac5R~
    	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//~vac5I~
                    UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN,HELP_TITLEID,HELPFILE);
        dlg.selectedList=PselectedList;                            //~va87I~
        if (Dump.Y) Dump.println("HistoryBSDlg.newInstance slectedLis=length="+PselectedList.length+"="+Utils.toString(PselectedList));//~va87R~
    	return dlg;
    }
    //******************************************************************//~1406I~
    //*called at onCreateView to setup layout                      //~1406I~
    //*******************************************************************//~1406I~
	@Override	//UFDlg                                            //~1406I~
    public void initLayout(View Playoutview)                       //~1406I~
	{                                                              //~1406I~
    	super.initLayout(Playoutview);                             //~1406I~
        ctrSelectedMembers=0;                                      //~va87I~
        getComponent(Playoutview) ;                                //~1406I~
        setSelectedMember();                                       //~va87I~
        makeBS();                                                  //~1406I~
        setupDialog();                                             //~1406I~
        setSummary();                                              //~1406I~
        tvCtrFileList.setText(Utils.getStr(R.string.Info_HistoryCtrDetail,ctrAdd));//~vad2R~
    }                                                              //~1406I~
    //******************************************                   //~1406I~
    protected void getComponent(View PView)                        //~1406I~
    {                                                              //~1406I~
        if (Dump.Y) Dump.println("HistoryBSDlg.getComponent");     //~1406I~
//      lvBS=new UListView(PView,R.id.lvBS,LISTVIEW_ROW_LAYOUTID,this/*UListViewI*/,UListView.CHOICEMODE_NONE);//~1406I~//~vac5R~
        lvBS=new UListView(PView,R.id.lvBS,(AG.swSmallFont ? LISTVIEW_ROW_LAYOUTID_SMALLFONT : LISTVIEW_ROW_LAYOUTID),this/*UListViewI*/,UListView.CHOICEMODE_NONE);//~vac5I~
        tvSummary=(TextView)    UView.findViewById(PView,R.id.tvSummary);//~1406I~
        llListView=(LinearLayout)    UView.findViewById(PView,R.id.llListView);//~vad2I~
        rowIDDetail=AG.swSmallFont ? LISTVIEW_ROW_LAYOUTID_SMALLFONT_DETAIL : LISTVIEW_ROW_LAYOUTID_DETAIL;//~vad2I~
        lvFilename=new UListView(PView,R.id.FileList,rowIDDetail,this,UListView.CHOICEMODE_NONE);//~vad2R~
                                                                   //~vad2I~
        UButton.bind(PView,R.id.ShowDetail,this);                  //~vad2I~
        UButton.bind(PView,R.id.HideDetail,this);                  //~vad2I~
        tvCtrFileList=(TextView)    UView.findViewById(PView,R.id.CtrFileList);//~vad2I~
    }                                                              //~1406I~
    //******************************************
	@Override	//UFDlg
    protected int getDialogWidth()
    {
    	int ww=getDialogWidthSmallDevicePortraitFixedRate();
    	if (Dump.Y) Dump.println("HistoryBSDlg.getDialogWidth swSmallDevice="+AG.swSmallDevice+",ww="+ww);//~1406R~
        return ww;
    }
    //****************************************************************
    private void makeBS()                                          //~1406R~
    {
        if (Dump.Y) Dump.println("HistoryBSDlg:makeBS");           //~1406I~
        lvFilename.clearList();                                        //~vad2I~
    	HistoryData hd;                                            //~1406I~
        String[] namelist=HD.namelist;                               //~1406I~
		if (Dump.Y) Dump.println("HistoryBSDlg:makeBS namelist="+Arrays.toString(namelist));//~vad2I~
        ctrGameAlone=0; ctrGameMatch=0; ctrPending=0;              //~1406R~
        ctrAdd=0;                                                  //~vad2I~
        for (int ii=0;ii<namelist.length;ii++)                     //~1406I~
        {                                                          //~1406I~
    		hd=AG.aHistory.getHistoryData(namelist[ii]);    //get from Map//~1406I~
            dateFrom=hd.HD[HDPOS_HDR][POS_TIMESTAMP];              //~1408M~
		    if (Dump.Y) Dump.println("HistoryBSDlg:makeBS name="+namelist[ii]+",swPending="+hd.swComplete);//~1406I~
        	if (!hd.swComplete)                                    //~1406R~
            {                                                      //~1406I~
            	ctrPending++;                                      //~1406I~
		        if (Dump.Y) Dump.println("HistoryBSDlg:makeBS not gameover ctrPending="+ctrPending+",name="+namelist[ii]);//~1406R~
            	continue;                                          //~1406I~
            }                                                      //~1406I~
            int ctrRobot=chkRobot(hd);                             //~va87I~
            if (ctrRobotSelectedGame!=0)	//summary of selected member's game//~va87I~
            {                                                      //~va87I~
	            if (!isSelectedGame(hd,ctrRobot,swRobot))          //~va87I~
    	            continue;                                      //~va87I~
            }                                                      //~va87I~
            hd.setScores();                                        //~1406M~
            addFileList(ii);                                       //~vad2I~
            ctrAdd++;                                              //~vad2I~
//          int ctrRobot=chkRobot(hd);                             //~1406I~//~va87R~
            if (ctrRobot==PLAYERS-1)                               //~1406I~
            {                                                      //~1406I~
            	setBSAlone(hd);                                    //~1406I~
                ctrGameAlone++;                                    //~1406I~
            }                                                      //~1406I~
            else                                                   //~1406I~
            {                                                      //~1406I~
            	setBSMatch(hd);                                    //~1406I~
                ctrGameMatch++;                                    //~1406I~
            }                                                      //~1406I~
        }                                                          //~1406I~
    }                                                              //~1406I~
    //****************************************************************//~1406I~
    private void setSummary()                                      //~1406I~
    {                                                              //~1406I~
//        String s=Utils.getStr(R.string.BS_ctrGame)                 //~1406I~//~1408R~
//                    +" : "+Utils.getStr(R.string.BS_ctrGameAlone)+" = "+ctrGameAlone//~1406I~//~1408R~
//                    +" , "+Utils.getStr(R.string.BS_ctrGameMatch)+" = "+ctrGameMatch//~1406I~//~1408R~
//                    +" , "+Utils.getStr(R.string.BS_ctrGamePending)+" = "+ctrPending;//~1406I~//~1408R~
        String s;                                                  //~va87I~
	  if (ctrSelectedMembers!=0)                                      //~va87I~
      {                                                            //~va87I~
      	String names="";                                           //~va87I~
      	for (int ii=0;ii<ctrSelectedMembers;ii++)                  //~va87R~
            names+=selectedMemberName[ii]+"  ";                    //~va87I~
        if (ctrSelectedMembers<PLAYERS)                            //~va87R~
//          names+=nameRobot+"("+ctrSelectedMembers+")";           //~va87R~//~vad3R~
            names+=nameRobot+"("+(PLAYERS-ctrSelectedMembers)+")"; //~vad3I~
//      s=AG.resource.getString(R.string.Desc_BS_Summary_selected,names,ctrGameMatch,dateFrom);//~va87I~//~vad3R~
        s=AG.resource.getString(R.string.Desc_BS_Summary_selected,names,ctrGameMatch+ctrGameAlone,dateFrom);//~vad3I~
      }                                                            //~va87I~
      else                                                         //~va87I~
        s=AG.resource.getString(R.string.Desc_BS_Summary,ctrGameAlone,ctrGameMatch,ctrPending,dateFrom);//~1408I~//~va87R~
        if (Dump.Y) Dump.println("HistoryBSDlg.setSummary ctrSelectedMembers="+ctrSelectedMembers+",desc="+s);       //~1406I~//~1408I~//~vad3R~
        tvSummary.setText(s);                                      //~1406I~
    }                                                              //~1406I~
    //***********************************************
    private int chkRobot(HistoryData Phds)                         //~1406R~
    {
    	int rc=0;                                                  //~1406R~
        if (Dump.Y) Dump.println("HistoryBSDlg.chkRobot");         //~1406R~
        String[] names=Phds.HD[HDPOS_MEMBER];
        for (int ii=0;ii<PLAYERS;ii++)
        {
            String name=names[ii];
            swRobot[ii]=Accounts.isRobotName(name)>0;              //~1406R~
            if (swRobot[ii])                                       //~1406I~
                rc++;
        }
        if (Dump.Y) Dump.println("HistoryBSDlg.chkRobot rc="+rc+",swRobot="+Arrays.toString(swRobot));//~1406I~
        return rc;
    }
    //***********************************************              //~va87I~
    private void setSelectedMember()                               //~va87I~
    {                                                              //~va87I~
        ctrRobotSelectedGame=0;                                    //~va87I~
        hdSelectedGame=null;                                       //~va87I~
        int ctr=selectedList.length;                               //~va87I~
		if (Dump.Y) Dump.println("HistoryBSDlg.setSelectedMember ctrSelected="+ctr);//~va87I~
        if (ctr>1)                                                 //~va87I~
        {                                                          //~va87I~
	        UView.showToast(R.string.Err_BSMultipleSelected);      //~va87I~
            dismiss();                                             //~vad4I~
        	return;                                                //~va87I~
        }                                                          //~va87I~
        else                                                       //~va87I~
        if (ctr==0)                                                //~va87R~
        	return;                                                //~va87I~
		setSelectedMember(HD.namelist[selectedList[0]]);               //~va87I~
    }                                                              //~va87I~
    //***********************************************              //~va87I~
    private void setSelectedMember(String Pname)                   //~va87I~
    {                                                              //~va87I~
		if (Dump.Y) Dump.println("HistoryBSDlg.setSelectedMember name="+Pname);//~va87I~
    	HistoryData hd=AG.aHistory.getHistoryData(Pname);    //get from Map//~va87I~
        int ctrRobot=chkRobot(hd);                                 //~va87I~
//      if (ctrRobot==PLAYERS-1)                                   //~va87R~
//      {                                                          //~va87R~
//          UView.showToast(R.string.Err_BSPlayAloneSelected);     //~va87R~
//          return;                                                //~va87R~
//      }                                                          //~va87R~
        if (!hd.swComplete)                                        //~va87I~
        {                                                          //~va87I~
          	UView.showToast(R.string.Err_BS_NotCompleteSelected);  //~va87I~
            dismiss();                                             //+vad4I~
          	return;                                                //~va87I~
        }                                                          //~va87I~
        ctrRobotSelectedGame=ctrRobot;                             //~va87I~
        hdSelectedGame=hd;                                         //~va87I~
    }                                                              //~va87I~
    //***********************************************              //~va87I~
    private boolean isSelectedGame(HistoryData Phd,int PctrRobot,boolean[] PswRobot)//~va87I~
    {                                                              //~va87I~
		if (Dump.Y) Dump.println("HistoryBSDlg.isSelectedGame PctrRobot="+PctrRobot+",ctrRobotSelectedGame="+ctrRobotSelectedGame+",swRobot="+Utils.toString(PswRobot)+",selectedHD="+hdSelectedGame.toString());//~va87I~
        if (PctrRobot!=ctrRobotSelectedGame)                       //~va87I~
        	return false;                                          //~va87I~
        String[] names=Phd.HD[HDPOS_MEMBER];                      //~va87I~
        int ctrSameMember=0;                                       //~va87I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~va87I~
        {                                                          //~va87I~
            if (PswRobot[ii])	//robot name                       //~va87I~
            	continue;                                          //~va87I~
            String name=names[ii];                                 //~va87I~
            String[] namesSelected=hdSelectedGame.HD[HDPOS_MEMBER];//~va87I~
	        for (int jj=0;jj<PLAYERS;jj++)                         //~va87I~
    	    {                                                      //~va87I~
            	if (name.equals(namesSelected[jj]))                //~va87I~
                {                                                  //~va87I~
                	ctrSameMember++;                                //~va87I~
                    break;                                         //~va87I~
                }                                                  //~va87I~
            }                                                      //~va87I~
        }                                                          //~va87I~
        boolean rc=ctrSameMember==PLAYERS-PctrRobot;               //~va87I~
        if (rc && ctrSelectedMembers==0)                           //~va87I~
        {                                                          //~va87I~
        	Arrays.fill(selectedMemberName,null);                  //~va87R~
            for (int ii=0;ii<PLAYERS;ii++)                         //~va87I~
            {                                                      //~va87I~
                if (PswRobot[ii])   //robot name                   //~va87I~
                    continue;                                      //~va87I~
                selectedMemberName[ctrSelectedMembers++]=names[ii]; //~va87I~
            }                                                      //~va87I~
        }                                                          //~va87I~
		if (Dump.Y) Dump.println("HistoryBSDlg.isSelectedGame rc="+rc+",ctrSameMember="+ctrSameMember+",PctrRobot="+PctrRobot+",selectedMemberName="+Arrays.toString(selectedMemberName));//~va87R~
        return rc;                                                 //~va87I~
    }                                                              //~va87I~
    //***********************************************              //~1406I~
    private void setBSAlone(HistoryData Phd)                   //~1406I~
    {                                                              //~1406I~
		if (Dump.Y) Dump.println("HistoryBSDlg.setBSAlone");       //~1406I~
    	int idx=-1;                                                //~1406I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~1406I~
        	if (!swRobot[ii])                                      //~1406R~
            {	                                                   //~1406I~
            	idx=ii;                                            //~1406I~
                break;                                             //~1406I~
            }                                                      //~1406I~
        if (idx<0)                                                 //~1406I~
        	return;                                                //~1406I~
        String name=Phd.HD[HDPOS_MEMBER][idx];                     //~1406I~
        int point=Phd.scores[HDPOS_SCORES_SCORE][idx];              //~1406R~
        int order=getOrder(Phd.scores[HDPOS_SCORES_SCORE],point);  //~1406I~
        if (Dump.Y) Dump.println("HistoryBSDlg.setBSAlone name="+name+",order="+order+",names="+Arrays.toString(Phd.HD[HDPOS_MEMBER])+",score="+Arrays.toString(Phd.scores[HDPOS_SCORES_SCORE]));//~1406R~
        updateBS(name,point,order,mapAlone,true);                  //~1406R~
    }                                                              //~1406I~
    //***********************************************              //~1406I~
    private void setBSMatch(HistoryData Phd)                   //~1406I~
    {                                                              //~1406I~
		if (Dump.Y) Dump.println("HistoryBSDlg.setBSMatch");       //~1406I~
        String name;                                               //~1406I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~1406I~
        {                                                          //~1406I~
        	if (swRobot[ii])                                       //~1406I~
            	name=nameRobot;                                    //~1406I~
            else                                                   //~1406I~
		        name=Phd.HD[HDPOS_MEMBER][ii];                     //~1406I~
	        int point=Phd.scores[HDPOS_SCORES_SCORE][ii];          //~1406I~
	        int order=getOrder(Phd.scores[HDPOS_SCORES_SCORE],point);//~1406I~
	        if (Dump.Y) Dump.println("HistoryBSDlg.setBSMatch name="+name+",order="+order+",names="+Arrays.toString(Phd.HD[HDPOS_MEMBER])+",score="+Arrays.toString(Phd.scores[HDPOS_SCORES_SCORE]));//~1406I~
            updateBS(name,point,order,mapMatch,false);             //~1406R~
        }                                                          //~1406I~
    }                                                              //~1406I~
    //***********************************************              //~1406I~
    private int getOrder(int[] Pscores,int Pscore)                 //~1406I~
    {                                                              //~1406I~
    	int order=0;                                               //~1406I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~1406I~
        	if (Pscores[ii]>Pscore)                                //~1406I~
            	order++;                                           //~1406I~
        if (Dump.Y) Dump.println("HistoryBSDlg.getOrder order="+order);//~1406I~
        return order;                                              //~1406I~
    }                                                              //~1406I~
    //***********************************************              //~1406I~
    private void updateBS(String Pname,int Ppoint,int Porder,Map<String,BSData> Pmap,boolean PswAlone)//~1406R~
    {                                                              //~1406I~
        if (Dump.Y) Dump.println("HistoryBSDlg.updateBS name="+Pname+",point="+Ppoint);//~1406R~
    	BSData total=Pmap.get(Pname);                              //~1406R~
        if (total==null)                                             //~1406I~
        	total=new BSData(Ppoint,Porder,PswAlone);              //~1406R~
        else                                                       //~1406I~
        {                                                          //~1406I~
			if (Dump.Y) Dump.println("HistoryBSDlg.updateBS name="+Pname+",oldpoint="+total.toString());//~1406I~
        	total.update(Ppoint,Porder);                           //~1406R~
        }                                                          //~1406I~
    	Pmap.put(Pname,total);                                     //~1406R~
		if (Dump.Y) Dump.println("HistoryBSDlg.updateBS name="+Pname+",point="+Ppoint+",total="+total.toString());//~1406R~
    }                                                              //~1406I~
    //***********************************************              //~1406I~
    private void setupDialog()                                     //~1406I~
    {                                                              //~1406I~
        if (Dump.Y) Dump.println("HistoryBSDlg.setupDialog sizeAlone="+mapAlone.size()+",sizeMatch="+mapMatch.size());//~1406R~
        for (HashMap.Entry<String,BSData> entry:mapAlone.entrySet())//~1406R~
        {                                                          //~1406I~
        	if (Dump.Y) Dump.println("setupDilaog mapAlone name="+entry.getKey()+",BSData="+entry.getValue().toString());//~1406R~
        	lvBS.add(entry.getKey(),""/*item2*/,ID_ALONE);         //~1406I~
        }                                                          //~1406I~
        for (HashMap.Entry<String,BSData> entry:mapMatch.entrySet())//~1406I~
        {                                                          //~1406I~
        	if (Dump.Y) Dump.println("setupDilaog mapMatch name="+entry.getKey()+",BSData="+entry.getValue().toString());//~1406R~
        	lvBS.add(entry.getKey(),""/*item2*/,ID_MATCH);         //~1406I~
        }                                                          //~1406I~
    }                                                              //~1406I~
    //****************************************************************//~1406I~
    @Override //UListViewI                                         //~1406I~
    public View getViewCustom(int Ppos, View Pview, ViewGroup Pparent)//~1406I~
    {                                                              //~1406I~
    //*******************                                          //~1406I~
        if (Dump.Y) Dump.println("HistoryDlg:getViewCustom Ppos="+Ppos+",view="+Utils.toString(Pview)+",parent="+Utils.toString(Pparent));//~vad2R~
        if (Pparent.getId()==R.id.FileList)                        //~vad2R~
        	return getViewCustomFileList(Ppos,Pview,Pparent);	   //~vad2I~
        View v=Pview;                                              //~1406I~
        if (v == null)                                             //~1406I~
        {                                                          //~1406I~
//          v=AG.inflater.inflate(LISTVIEW_ROW_LAYOUTID,null);     //~1406I~//~vac5R~
            v=AG.inflater.inflate((AG.swSmallFont ? LISTVIEW_ROW_LAYOUTID_SMALLFONT : LISTVIEW_ROW_LAYOUTID),null);//~vac5I~
        }                                                          //~1406I~
        UListView.UListViewData ld=lvBS.arrayData.get(Ppos);       //~1406I~
        String name=ld.itemtext;                                   //~1406I~
        boolean swAlone=ld.itemint==ID_ALONE;                      //~1406I~
    	BSData data=swAlone ? mapAlone.get(name) : mapMatch.get(name);//~1406I~
	    setupLine(v,name,swAlone,data);                            //~1406I~
        return v;                                                  //~1406I~
    }                                                              //~1406I~
    private void setupLine(View Pview,String Pname,boolean PswAlone,BSData Pbsd)//~1406I~
    {                                                              //~1406I~
        if (Dump.Y) Dump.println("HistoryDlg:setupLine BSData="+Pbsd.toString());//~1406I~
        TextView tvName=(TextView) UView.findViewById(Pview,R.id.tvMemberName);//~1406I~
        TextView tvAlone=(TextView) UView.findViewById(Pview,R.id.tvAlone);//~1406I~
        TextView tvPoint=(TextView)UView.findViewById(Pview,R.id.tvPoint);//~1406I~
        TextView tvCtr1=(TextView)UView.findViewById(Pview,R.id.tvCtrSeq1);//~1406I~
        TextView tvCtr2=(TextView)UView.findViewById(Pview,R.id.tvCtrSeq2);//~1406I~
        TextView tvCtr3=(TextView)UView.findViewById(Pview,R.id.tvCtrSeq3);//~1406I~
        TextView tvCtr4=(TextView)UView.findViewById(Pview,R.id.tvCtrSeq4);//~1406I~
        TextView tvCtrGame=(TextView)UView.findViewById(Pview,R.id.tvCtrGame);//~1406I~
        tvName.setText(Pname);                                     //~1406I~
        tvAlone.setText(PswAlone ? "*" : " ");                     //~1406I~
        int score=Pbsd.total;                                      //~1406R~
        int overpt=score/1000;                                     //~1406R~
        int underpt=score%1000;                                    //~1406I~
        if (underpt<0)                                              //~1406I~
        	underpt=-underpt;                                      //~1406I~
        underpt/=100;                                              //~1406I~
        String pt=overpt+"."+underpt;                              //~1406I~
        tvPoint.setText(pt);                      //~1406R~
        tvCtr1.setText(Integer.toString(Pbsd.ctrOrder[0]));        //~1406I~
        tvCtr2.setText(Integer.toString(Pbsd.ctrOrder[1]));        //~1406I~
        tvCtr3.setText(Integer.toString(Pbsd.ctrOrder[2]));        //~1406I~
        tvCtr4.setText(Integer.toString(Pbsd.ctrOrder[3]));        //~1406I~
        tvCtrGame.setText(Integer.toString(Pbsd.ctrGame));          //~1406R~
    }                                                              //~1406I~
    //******************************************                   //~1406I~
    //*if COICEMODE_SINGLE                                         //~1406I~
    //******************************************                   //~1406I~
    @Override //ListViewI                                          //~1406I~
    public void onListItemClicked(int Ppos,int Pselectedpos)       //~1406I~
    {                                                              //~1406I~
        if (Dump.Y) Dump.println("HistoryBSDlg.onListItemClicked pos="+Ppos);//~1406I~
    }                                                              //~1406I~
    //******************************************                   //~1406I~
    //*if COICEMODE_MULTIPLE,return 0 if selectable                //~1406I~
    //******************************************                   //~1406I~
    @Override //ListViewI                                          //~1406I~
    public int onListItemClickedMultiple(int Ppos)                 //~1406I~
    {                                                              //~1406I~
        if (Dump.Y) Dump.println("HistoryBSDlg.onListItemClickedMultiple pos="+Ppos);//~1406I~
        return 0;                                                  //~1406I~
	}                                                              //~1406I~
	//*****************************************************************//~1406I~
	//*****************************************************************//~1406I~
	//*****************************************************************//~1406I~
	class BSData                                                   //~1406I~
	{                                                              //~1406I~
    	int total,ctrGame;                                         //~1406R~
    	boolean swAlone;                                           //~1406I~
        int[] ctrOrder=new int[PLAYERS];                           //~1406I~
        public BSData(int Ppoint,int Porder,boolean PswAlone)      //~1406R~
        {                                                          //~1406I~
        	total=Ppoint;                                          //~1406I~
        	ctrGame=1;                                             //~1406I~
            ctrOrder[Porder]=1;                                    //~1406I~
            swAlone=PswAlone;                                      //~1406I~
        }                                                          //~1406I~
        public void update(int Ppoint,int Porder)                  //~1406I~
        {                                                          //~1406I~
        	total+=Ppoint;                                         //~1406I~
        	ctrGame++;;                                            //~1406I~
            ctrOrder[Porder]++;                                    //~1406I~
        }                                                          //~1406I~
        public String toString()                                   //~1406I~
        {                                                          //~1406I~
        	return "total="+total+",ctrOrder="+Arrays.toString(ctrOrder);//~1406I~
        }                                                          //~1406I~
    }                                                              //~1406I~
    //******************************************                   //~vad2I~
    @Override                                                      //~vad2I~
    public void onClickOther(int Pbuttonid)                        //~vad2I~
	{                                                              //~vad2I~
        if (Dump.Y) Dump.println("HistoryBSDlg:onClickOther id="+Pbuttonid);//~vad2I~
        switch(Pbuttonid)                                          //~vad2I~
        {                                                          //~vad2I~
        case R.id.ShowDetail:                                      //~vad2I~
        	showDetail();                                          //~vad2I~
            break;                                                 //~vad2I~
        case R.id.HideDetail:                                      //~vad2I~
        	hideDetail();                                          //~vad2I~
            break;                                                 //~vad2I~
        }                                                          //~vad2I~
    }                                                              //~vad2I~
    //******************************************                   //~vad2I~
    private void hideDetail()                                      //~vad2I~
	{                                                              //~vad2I~
        if (Dump.Y) Dump.println("HistoryBSDlg:hideDetail");       //~vad2I~
        llListView.setVisibility(View.GONE);                       //~vad2I~
    }                                                              //~vad2I~
    //******************************************                   //~vad2I~
    private void showDetail()                                      //~vad2I~
	{                                                              //~vad2I~
        if (Dump.Y) Dump.println("HistoryBSDlg:showDetail");       //~vad2I~
        llListView.setVisibility(View.VISIBLE);                    //~vad2I~
    }                                                              //~vad2I~
    //**********************************                           //~vad2I~
    //* init ListView                                              //~vad2I~
    //**********************************                           //~vad2I~
    protected void addFileList(int Ppos)                           //~vad2I~
    {                                                              //~vad2I~
        UListView.UListViewData ld=HD.lvFilename.arrayData.get(Ppos);//~vad2I~
        lvFilename.add(ld.itemtext,ld.itemtext2,0);                //~vad2I~
        if (Dump.Y) Dump.println("HistoryBSDlg:addFileList pos="+Ppos+",itemtext="+ld.itemtext+",itemtext2="+ld.itemtext2);//~vad2I~
    }                                                              //~vad2I~
    //****************************************************************//~vad2I~
    private View getViewCustomFileList(int Ppos, View Pview, ViewGroup Pparent)//~vad2R~
    {                                                              //~vad2I~
    //*******************                                          //~vad2I~
        if (Dump.Y) Dump.println("HistoryBSDlg:getViewCustomFileList Ppos="+Ppos);//~vad2R~
        View v=Pview;                                              //~vad2I~
        try                                                        //~vad2I~
        {                                                          //~vad2I~
        	v=getViewCustomFileListBS(Ppos,Pview,Pparent);         //~vad2R~
        }                                                          //~vad2I~
		catch(Exception e)                                         //~vad2I~
		{                                                          //~vad2I~
        	Dump.println(e,"HistoryBSDlg getViewCustomFileList");  //~vad2I~
        }                                                          //~vad2I~
        return v;                                                  //~vad2I~
    }                                                              //~vad2I~
    private View getViewCustomFileListBS(int Ppos, View Pview, ViewGroup Pparent)//~vad2I~
    {                                                              //~vad2I~
    //*******************                                          //~vad2I~
        if (Dump.Y) Dump.println("HistoryBSDlg:getViewCustomFileListBS Ppos="+Ppos);//~vad2I~
        View v=Pview;                                              //~vad2I~
        if (v == null)                                             //~vad2I~
        {                                                          //~vad2I~
            v=AG.inflater.inflate(lvFilename.rowId,null);          //~vad2I~
        }                                                          //~vad2I~
        TextView v1=(TextView)UView.findViewById(v,R.id.FileName); //~vad2I~
        UListView.UListViewData ld=lvFilename.arrayData.get(Ppos); //~vad2I~
        v1.setText(ld.itemtext);                                   //~vad2I~
        if (Dump.Y) Dump.println("HistoryDlg:getViewCustom itemText="+ld.itemtext);//~vad2I~
        v1.setBackgroundColor(lvFilename.bgColor);                 //~vad2I~
        v1.setTextColor(Color.BLACK);                              //~vad2I~
        String[][] hds=HD.getHistoryData(ld.itemtext);             //~vad2R~
        if (hds!=null)                                             //~vad2I~
	        HD.setHD(v,hds);                                       //~vad2R~
        return v;                                                  //~vad2I~
    }                                                              //~vad2I~
}//class
