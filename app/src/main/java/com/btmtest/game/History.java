//*CID://+v@01R~:                             update#=  541;       //~v@@@R~//~9404R~//~v@01R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.game;                                        //~v@@@R~
import com.btmtest.R;
import com.btmtest.dialog.FileDialog;
import com.btmtest.dialog.HistoryDlg;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.SettingDlg;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.Prop;
import com.btmtest.utils.UFile;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.HistoryData.*;
import static com.btmtest.utils.Utils.*;

public class History                                               //~9614R~
{                                                                  //~2C29R~
    private static final String VERSION="v1.01";                   //~9614R~
    public  static final String EXT_HISTORY=".history";            //~9614R~
//  public  static final String EXT_HISTORY_RULE=".historyrule";   //~9826R~
//  public  static final String EXT_HISTORY_RULE=".suspendgame.rulefile";//~9826I~//~9827R~//~v@01R~
    public  static final String EXT_HISTORY_RULE=".sg.rulefile";   //~v@01I~
    private static final String TIMESTAMP_SEP="-";                 //~9826I~
    private static final String RULEID_SEP_TIMESTAMP=":";          //~9827I~
//  private static final String FNM_LATEST="#";                    //~9614R~
    private static final String CMT_HISTORY_RULE="Rule for interrupted History file";//~9826I~
	public  static final String HISTORY_FILENAME="#HistoryRuleFileName=";//~9826I~//~9827R~
                                                                   //~9826I~
                                                                   //~9824I~
//    public  static final int    POS_SCORE_SCORE=0;                 //~9824I~//~9825R~
//    public  static final int    POS_SCORE_CTRREACH=1;              //~9824I~//~9825R~
//    public  static final int    POS_SCORE_GAMESEQ=2;               //~9824I~//~9825R~
                                                                   //~9614I~
    public  static final int    SAVE_EGTP_GAMEOVER=0;              //~9823I~
    public  static final int    SAVE_EGTP_SUSPENDED=1;             //~9823I~
    public  static final int    SAVE_EGTP_INTERRUPTED=2;         //~9823R~//~9824R~
    public  static final int    SAVE_EGTP_INTERRUPTED_RESUME=3;    //~v@01I~
                                                                   //~9823I~
    public String pathDataDir,workDirSD;                           //~9614R~
    public String pathHistory;                                     //~9614R~
    public boolean swSD;                                          //~9614I~//~9615R~
    private int endgameType;                                       //~9823I~
    private Map<String,HistoryData> HDMap=new HashMap<String,HistoryData>();//~9615I~
    //******************************************                   //~v@@@R~
    public History()                                            //~v@@@R~//~9613R~//~9614R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("History.defaultConstructor");    //~9614R~
        AG.aHistory=this;                                          //~9614I~
        String[] folders=FileDialog.getFolder();                   //~9614I~
        workDirSD=folders[0];                                       //~9614I~
        pathDataDir=folders[1];                                     //~9614I~
        swSD=workDirSD!=null;                                      //~9614R~
        pathHistory=!swSD ? pathDataDir : workDirSD;               //~9614I~
    }                                                              //~v@@@R~
	//**********************************                           //~9826I~
    public static String timestampToFilename(int Pdate,int Ptime)  //~9826R~
    {                                                              //~9826I~
    	String rc=Pdate+TIMESTAMP_SEP+String.format("%06d",Ptime);    //"-"              //~9826R~//~9827R~
		if (Dump.Y) Dump.println("History.timestampToFilename rc="+rc);//~9826I~
        return rc;
    }                                                              //~9826I~
	//**********************************                           //~9826I~
    public static String timestampToFilename(String Ptimestamp)    //~9826R~
    {                                                              //~9826I~
    	String rc=Ptimestamp.replace(MSG_SEPAPP,TIMESTAMP_SEP);// ":"-->"-"//~9826R~
		if (Dump.Y) Dump.println("History.timestampToFilename rc="+rc);//~9826I~
        return rc;
    }                                                              //~9826I~
	//**********************************                           //~9613I~
//  public void saveLatestGame(String[] Paccountnames,int[][] Pscores/*total,minuspay,minuscharge,finalscore*/)//~9613I~//~9614R~//~9826R~
    public void saveLatestGame(String Pfnm,String[] Paccountnames,int[][] Pscores/*total,minuspay,minuscharge,finalscore*/)//~9826I~
    {                                                              //~9613I~
		if (Dump.Y) Dump.println("History.saveLatestGame");     //~9613I~//~9614R~
        endgameType=SAVE_EGTP_GAMEOVER;                            //~9823I~
//      String ts=Utils.getTimeStamp(TS_DATE_TIME);                //~9614I~//~9615M~//~9826R~
//      String fpath=UFile.makeFullpath(pathHistory,ts,EXT_HISTORY);//~9614R~//~9615R~//~9826R~
//  	String txt=makeHistoryData(ts,Paccountnames,Pscores);//~9614R~//~9615R~//~9826R~
        String fpath=UFile.makeFullpath(pathHistory,Pfnm,EXT_HISTORY);//~9826I~
    	String txt=makeHistoryData(Pfnm,Paccountnames,Pscores);    //~9826I~
        boolean rc=writeFile(fpath,txt);                                      //~9614I~//~9615R~
    }                                                              //~9613I~
	//**********************************                           //~9823I~
	//*GameOver by suspend                                         //~9824I~
	//**********************************                           //~9824I~
//  public void saveLatestGameSuspended(String[] Paccountnames,int[][] Pscores/*total,ctrReach,penalty,finalscore*/)//~9823I~//~9824R~//~9826R~
    public void saveLatestGameSuspended(String Pfnm,String[] Paccountnames,int[][] Pscores/*total,ctrReach,penalty,finalscore*/)//~9826I~
    {                                                              //~9823I~
		if (Dump.Y) Dump.println("History.saveLatestGameSuspended intp="+Utils.toString(Pscores));        //~9823I~//~9824R~
        endgameType=SAVE_EGTP_SUSPENDED;                           //~9823I~
//      String ts=Utils.getTimeStamp(TS_DATE_TIME);                //~9823I~//~9826R~
//      String fpath=UFile.makeFullpath(pathHistory,ts,EXT_HISTORY);//~9823I~//~9826R~
//  	String txt=makeHistoryData(ts,Paccountnames,Pscores);      //~9823I~//~9826R~
        String fpath=UFile.makeFullpath(pathHistory,Pfnm,EXT_HISTORY);//~9826I~
    	String txt=makeHistoryData(Pfnm,Paccountnames,Pscores);    //~9826I~
        boolean rc=writeFile(fpath,txt);                           //~9823I~
    }                                                              //~9823I~
	//**********************************                           //~9824I~
	//*Save for restart by suspend                                 //~9824I~
	//**********************************                           //~9824I~
//  public void saveLatestGameInterrupted(String[] Paccountnames,int[][] Pscores/*score,ctrReach,gameSeq,score*/)//~9824I~//~9826R~
//  public void saveLatestGameInterrupted(String Pfnm,String[] Paccountnames,int[][] Pscores/*score,ctrReach,gameSeq,score*/)//~9826I~//~v@01R~
    public void saveLatestGameInterrupted(String Pfnm,String[] Paccountnames,int[][] Pscores/*score,ctrReach,gameSeq,other*/)//~v@01I~
    {                                                              //~9824I~
		if (Dump.Y) Dump.println("History.saveLatestGameInterrupted intp="+Utils.toString(Pscores));//~9824R~
        endgameType=SAVE_EGTP_INTERRUPTED;                         //~9824I~
//      String ts=Utils.getTimeStamp(TS_DATE_TIME);                //~9824I~//~9826R~
//      String fpath=UFile.makeFullpath(pathHistory,ts,EXT_HISTORY);//~9824I~//~9826R~
//  	String txt=makeHistoryData(ts,Paccountnames,Pscores)+"\n"+scoreToStringInterrupted(Pscores);//~9824R~//~9826R~
        String fpath=UFile.makeFullpath(pathHistory,Pfnm,EXT_HISTORY);//~9826I~
    	String txt=makeHistoryData(Pfnm,Paccountnames,Pscores)+"\n"+scoreToStringInterrupted(Pscores);//~9826I~
        boolean rc=writeFile(fpath,txt);                           //~9824I~
    }                                                              //~9824I~
	//**********************************                           //~9825I~
	//*from HistoryDlg static received()                           //~9825I~
	//**********************************                           //~9825I~
//  public void saveReceived(HistoryData Phd)                      //~9825I~//~9828R~
    public boolean saveReceived(boolean PswMsg,HistoryData Phd)    //~9828I~
    {                                                              //~9825I~
		if (Dump.Y) Dump.println("History.saveReceived swMsg="+PswMsg+",HD="+Utils.toString(Phd.HD));//~9825I~//~9828R~
        String[][] hd=Phd.HD;                                      //~9825I~
        String[] hdr=hd[HDPOS_HDR];                                 //~9825I~
        String ts=hdr[POS_TIMESTAMP];                              //~9825I~
        String fpath=UFile.makeFullpath(pathHistory,ts,EXT_HISTORY);//~9825I~
        if ((new File(fpath)).exists())                              //~9825I~//~9827R~
        {                                                          //~9825I~
        	if (!PswMsg)                                           //~9828I~
            {                                                      //~9828I~
				if (Dump.Y) Dump.println("History.saveReceived already exist return by noMsg");//~9828R~
            	return true;                                       //~9828I~
            }                                                      //~9828I~
        	UView.showToast(Utils.getStr(R.string.Err_AlreadyFileExists,fpath));//~9825I~
            return true;                                                //~9825I~//~9828R~
        }                                                          //~9825I~
    	String txt=makeHistoryData(Phd);                           //~9825I~
        boolean rc=writeFile(fpath,txt);                           //~9825I~
        UView.showToast(Utils.getStr(R.string.Info_FileWritten,fpath));//~9825I~
        return rc;                                                 //~9828I~
    }                                                              //~9825I~
	//**********************************                           //~9614I~
    public  String makeHistoryData(HistoryData Phd)                //~9825R~//~v@01R~
    {                                                              //~9614I~
        String[][] hd=Phd.HD;                                      //~9825I~
        String hdr=makeHeader(hd[HDPOS_HDR]);                         //~9615I~//~9825R~
        String score=scoreToString(Phd);           //~9614R~   //~9615R~//~9825R~
        String members=accountsToString(hd[HDPOS_MEMBER]);            //~9614I~//~9825R~
        String txt=hdr+"\n"+members+"\n"+score;   //~9614R~        //~9615R~
		if (Dump.Y) Dump.println("History.makeHistoryData rc="+txt);//~9614I~
        return txt;                                                //~9614I~
    }                                                              //~9614I~
	//**********************************                           //~9825I~
    private String makeHistoryData(String Ptimestamp,String[] Paccountnames,int[][] Pscores/*total,minuspay,minuscharge,finalscore*/)//~9825I~
    {                                                              //~9825I~
        String hdr=makeHeader(Ptimestamp);                         //~9825I~
        String score=scoreToString(Pscores);                       //~9825I~
        String members=accountsToString(Paccountnames);            //~9825I~
        String txt=hdr+"\n"+members+"\n"+score;                    //~9825I~
		if (Dump.Y) Dump.println("History.makeHistoryData rc="+txt);//~9825I~
        return txt;                                                //~9825I~
    }                                                              //~9825I~
	//**********************************                           //~9615I~
    private String makeHeader(String Ptimestamp)                   //~9615I~
    {                                                              //~9615I~
        String syncDateFormatted=getSyncDateShort();               //~9826I~
//      String idName=AG.ruleProp.getParameter(getKeyRS(RSID_IDNAME),Utils.getStr(R.string.UnknownRuleName));//~9615R~//~9826R~
        String idName=AG.ruleProp.getParameter(getKeyRS(RSID_IDNAME),Utils.getStr(R.string.UnknownRuleName))+RULEID_SEP_TIMESTAMP+syncDateFormatted;//~9827R~
        int idxGT=AG.ruleProp.getParameter(getKeyRS(RSID_GAMESET_TYPE),0);//~9615I~
        String gameSetType=strsGameSetType[idxGT];                 //~9615I~
//      String hdr=VERSION+"\n"+Ptimestamp+"\n"+gameSetType+"\n"+idName;//~9615I~//~9823R~
        String hdr=VERSION+"\n"+Ptimestamp+"\n"+gameSetType+"\n"+idName+"\n"+endgameType;//~9823I~
		if (Dump.Y) Dump.println("History.makeHeader rc="+hdr);    //~9615I~
        return hdr;                                                //~9615I~
    }                                                              //~9615I~
	//**********************************                           //~9825I~
    private String makeHeader(String[] Phdr)                       //~9825I~
    {                                                              //~9825I~
        String hdr=Phdr[POS_VERSION]+"\n"+Phdr[POS_TIMESTAMP]+"\n"+Phdr[POS_GAMESET]+"\n"+Phdr[POS_RULEID]+"\n"+Phdr[POS_ENDGAMETYPE];//~9825I~
		if (Dump.Y) Dump.println("History.makeHeader rc="+hdr);    //~9825I~
        return hdr;                                                //~9825I~
    }                                                              //~9825I~
	//**********************************                           //~9613I~
    private String scoreToString(int[][] Pscores)            //~9613I~//~9614R~
    {                                                              //~9613I~
        String[] fp=AG.aAccounts.finalPoint1000;          //~9614I~//~9615I~
		if (Dump.Y) Dump.println("History.scoreToString total/score="+Arrays.toString(Pscores[0]));//~9613I~//~9614R~//~9824R~
		if (Dump.Y) Dump.println("History.scoreToString minuspay/ctrReach="+Arrays.toString(Pscores[1]));//~9613I~//~9614R~//~9824R~
		if (Dump.Y) Dump.println("History.scoreToString minuscharge/gameSeq="+Arrays.toString(Pscores[2]));//~9613I~//~9614R~//~9824R~
		if (Dump.Y) Dump.println("History.scoreToString finalscore/score="+Arrays.toString(Pscores[3]));//~9613I~//~9614R~//~9824R~
		if (Dump.Y) Dump.println("History.scoreToString finalPoint="+Arrays.toString(fp));//~9614I~//~9615R~
//      String score=Arrays.toString(Pscores[0])+"\n"+Arrays.toString(Pscores[1])+"\n"+Arrays.toString(Pscores[2])+"\n"+Arrays.toString(Pscores[3])+"\n"+Arrays.toString(PfinalPoint);//~9613I~//~9614R~//~9615R~
        String score;                                              //~9824I~
        if (endgameType==SAVE_EGTP_INTERRUPTED)                    //~9824I~
        	score=Pscores[0][0]+"\n"+Pscores[0][1]+"\n"+Pscores[0][2]+"\n"+Pscores[0][3];//~9824I~
        else                                                       //~9824I~
        	score=fp[0]+"\n"+fp[1]+"\n"+fp[2]+"\n"+fp[3];       //~9615I~//~9824R~
        return score;                                               //~9613I~
    }                                                              //~9613I~
	//**********************************                           //~9825I~
    private String scoreToString(HistoryData Phd)                  //~9825I~
    {                                                              //~9825I~
        String[][] hd=Phd.HD;                                      //~9825I~
		if (Dump.Y) Dump.println("History.scoreToString HD="+Utils.toString(hd));//~9825I~
        StringBuffer sb=new StringBuffer();                         //~9825I~
        for (int ii=HDPOS_SCORE;ii<hd.length;ii++)                 //~9825R~
        {                                                          //~9825I~
            if (ii!=HDPOS_SCORE)                                   //~9825R~
                sb.append("\n");                                   //~9825I~
        	String[] strs=hd[ii];                                  //~9825I~
        	for (int jj=0;jj<strs.length;jj++)                     //~9825I~
            {                                                      //~9825I~
            	if (jj!=0)                                         //~9825R~
                	sb.append("\n");                               //~9825I~
                sb.append(strs[jj]);                               //~9825I~
            }                                                      //~9825I~
        }                                                          //~9825I~
        String rc=sb.toString();                                   //~9825I~
		if (Dump.Y) Dump.println("History.scoreToString rc="+rc);  //~9825I~
        return rc;                                              //~9825I~
    }                                                              //~9825I~
	//**********************************                           //~9824I~
    private String scoreToStringInterrupted(int[][] Pscores)       //~9824I~
    {                                                              //~9824I~
        String score;                                              //~9824I~
        score=Pscores[1][0]+"\n"+Pscores[1][1]+"\n"+Pscores[1][2]+"\n"+Pscores[1][3]+"\n"  //ctrReach//~9824R~
//           +Pscores[2][0]+"\n"+Pscores[2][1]+"\n"+Pscores[2][2]+"\n"+Pscores[2][3];   //gameSeq//~9824I~//~v@01R~
             +Pscores[2][0]+"\n"+Pscores[2][1]+"\n"+Pscores[2][2]+"\n"+Pscores[2][3]+"\n"   //gameSeq//~v@01I~
             +Pscores[3][0]+"\n"+Pscores[3][1]+"\n"+Pscores[3][2]+"\n"+Pscores[3][3]+"\n";   //other//~v@01I~
        return score;                                              //~9824I~
    }                                                              //~9824I~
	//**********************************                           //~9615I~
    public HistoryData getHistoryData(String Pfname)               //~9615I~
    {                                                              //~9615I~
		if (Dump.Y) Dump.println("History.getHistoryData mapget Pfname="+Pfname);//~9615R~
        String basename=Pfname.replace(EXT_HISTORY,"");            //~9615I~
    	HistoryData hd=HDMap.get(basename);                        //~9615R~
		if (Dump.Y) Dump.println("History.getHistoryData mapget rc="+((hd==null)?"null":Utils.toString(hd.HD)));//~9615R~
        return hd;                                                 //~9615I~
    }                                                              //~9615I~
	//**********************************                           //~9614I~
    public HistoryData getHistoryData(String Pfname,File Pfh)       //~9614I~//~9615R~
    {                                                              //~9614I~
		if (Dump.Y) Dump.println("History.getHistoryData Pfname="+Pfname+",file="+Pfh.getName());//~9615R~
    	HistoryData hd=getHistoryData(Pfname);                        //~9615R~
        if (hd!=null)                                              //~9615I~
        {                                                          //~9615I~
			if (Dump.Y) Dump.println("History.getHistoryData map found "+Utils.toString(hd.HD));//~9615I~
        	return hd;                                             //~9615R~
        }                                                          //~9615I~
		String data=readFile(Pfname,Pfh);                          //~9614I~
        if (data==null)                                            //~9614I~
        	return null;                                           //~9614I~
        String[] lines=data.split("\n",0);                          //~9614I~//~9615M~//~9825R~
		if (Dump.Y) Dump.println("History.getHistoryData lines="+lines.length+"="+Arrays.toString(lines));//~9614I~//~9615R~
        if (lines.length<MAXLINECTR)                               //~9614I~//~9615M~
        	return null;                                           //~9614I~//~9615M~
        String[] hdrs=new String[HDCTR_HDR];                       //~9615I~
        String[] members=new String[PLAYERS];                      //~9615I~
        String[] score=new String[PLAYERS];                        //~9615I~
        System.arraycopy(lines,0,hdrs,0,HDCTR_HDR);                //~9615I~//~9825M~
        System.arraycopy(lines,HDCTR_HDR,members,0,PLAYERS);       //~9615I~//~9825M~
        System.arraycopy(lines,HDCTR_HDR+PLAYERS,score,0,PLAYERS); //~9615I~
        String[][] rc;                                             //~9824I~
        int et=Utils.parseInt(hdrs[POS_ENDGAMETYPE],-1);           //~9824I~//~9825I~
        if (et==SAVE_EGTP_INTERRUPTED)                             //~9824I~
        {                                                          //~9824I~
	        if (lines.length<MAXLINECTR_INTERRUPTED)               //~9825I~
	        	return null;                                       //~9825I~
        	String[] ctrReach=new String[PLAYERS];                 //~9824I~
        	String[] gameSeq=new String[PLAYERS];                  //~9824I~
        	String[] other  =new String[PLAYERS];                  //~v@01I~
        	System.arraycopy(lines,HDCTR_HDR+PLAYERS*2,ctrReach,0,PLAYERS);//~9824I~
        	System.arraycopy(lines,HDCTR_HDR+PLAYERS*3,gameSeq,0,PLAYERS);//~9824I~
        	System.arraycopy(lines,HDCTR_HDR+PLAYERS*4,other,0,PLAYERS);//~v@01I~
//      	rc=new String[][]{hdrs,members,score,ctrReach,gameSeq};//~9824I~//~v@01R~
        	rc=new String[][]{hdrs,members,score,ctrReach,gameSeq,other};//~v@01I~
        }                                                          //~9824I~
        else                                                       //~9824I~
        	rc=new String[][]{hdrs,members,score};          //~9615R~//~9824R~
    	HistoryData hdnew=new HistoryData(rc);	//replace if dup   //~9615R~
        String basename=Pfname.replace(EXT_HISTORY,"");            //~9615I~
		if (Dump.Y) Dump.println("History.getHistoryData map put="+basename);//~9615I~
    	HDMap.put(basename,hdnew);	//replace if dup               //~9615R~
		if (Dump.Y) Dump.println("History.getHistoryData endgametype="+et+",hdrs="+Arrays.toString(hdrs));//~9614I~//~9615R~//~9824R~
		if (Dump.Y) Dump.println("History.getHistoryData member="+Arrays.toString(members));//~9614I~
		if (Dump.Y) Dump.println("History.getHistoryData score="+Arrays.toString(score));//~9614I~
		if (Dump.Y) Dump.println("History.getHistoryData scores="+Utils.toString(rc));//~9824I~
        return hdnew;                                                 //~9614I~//~9615R~
    }                                                              //~9614I~
	//**********************************                           //~9614I~
    private String accountsToString(String[] Pnames)               //~9614I~
    {                                                              //~9614I~
//      String rc=String.Join(":",Pnames);                         //~9614R~
        String rc=Pnames[0]+"\n"+Pnames[1]+"\n"+Pnames[2]+"\n"+Pnames[3];//~9614I~
		if (Dump.Y) Dump.println("History.accountsToString names="+Arrays.toString(Pnames)+",rc="+rc);//~9614I~
        return rc;                                                 //~9614I~
    }                                                              //~9614I~
	//************************************************************ //~@@@@I~//~9614I~
	//*output by fullpath                                          //~@@@@I~//~9614I~
	//************************************************************ //~@@@@I~//~9614I~
	private boolean writeFile(String Pfname,String Ptxt)//~@@@@R~  //~9614I~
	{                                                              //~@@@@I~//~9614I~
		if (Dump.Y) Dump.println("History.writeFile fnm="+Pfname+",txt="+Ptxt);//~9614I~
    	boolean rc=false;                                          //~@@@@I~//~9614I~
		try                                                        //~@@@@I~//~9614I~
		{                                                          //~@@@@I~//~9614I~
        	File f=new File(Pfname);                               //~9614I~
            FileWriter fw=new FileWriter(f);                       //~9614I~
            BufferedWriter bw=new BufferedWriter(fw);              //~9614I~
            PrintWriter pw=new PrintWriter(bw);                    //~9614I~
        	pw.println(Ptxt);                                      //~9614I~
        	pw.flush();                                            //~9614R~
        	pw.close();                                            //~9614I~
            rc=true;                                               //~@@@@I~//~9614I~
		}                                                          //~@@@@I~//~9614I~
		catch (Exception e)                                        //~@@@@I~//~9614I~
		{                                                          //~@@@@I~//~9614I~
			Dump.println(e,"History.writeFile txt="+Ptxt);   //~@@@@I~//~9614I~
		}                                                          //~@@@@I~//~9614I~
		if (Dump.Y) Dump.println("History.writeFile rc="+rc);       //~9614I~
        return rc;                                                 //~@@@@I~//~9614I~
	}                                                              //~@@@@I~//~9614I~
	//************************************************************ //~9614I~
	//*read history file                                           //~9614I~
	//************************************************************ //~9614I~
	private String readFile(String Pfname,File Pfh)                //~9614I~
	{                                                              //~9614I~
		if (Dump.Y) Dump.println("History.readFile fnm="+Pfname);  //~9614I~
    	String rc=null;                                            //~9614I~
		try                                                        //~9614I~
		{                                                          //~9614I~
            FileReader fr=new FileReader(Pfh);                     //~9614I~
            BufferedReader br=new BufferedReader(fr);              //~9614I~
            String line;                                           //~9614I~
            StringBuffer sb=new StringBuffer();                    //~9614I~
            while ((line=br.readLine())!=null)                     //~9614I~
            {                                                      //~9614I~
            	sb.append(line+"\n");                              //~9614R~
            }                                                      //~9614I~
        	br.close();                                            //~9614I~
            rc=sb.toString();                                      //~9614I~
			if (Dump.Y) Dump.println("History.readFile rc="+rc);   //~9614I~
		}                                                          //~9614I~
		catch (Exception e)                                        //~9614I~
		{                                                          //~9614I~
			Dump.println(e,"History.readFile fnm="+Pfname);        //~9614I~
		}                                                          //~9614I~
        return rc;                                                 //~9614I~
	}                                                              //~9614I~
	//************************************************************ //~9614I~
	//*from Main.btnHistory                                        //~9614I~
	//************************************************************ //~9614I~
	public void showDlg()                                          //~9614I~
	{                                                              //~9614I~
        if (Dump.Y) Dump.println("SettingDlg.onClickLoad");                   //~v@@@I~//~9404R~//~9614I~
        try                                                        //~9614I~
        {                                                          //~9614I~
			if (Utils.isShowingDialogFragment(AG.aHistoryDlg))                    //~v@@@I~//~9226R~//~v@01I~
            {                                                      //~v@01I~
	        	UView.showToast(R.string.Err_AlreadyShowing);      //~v@01I~
            	return;                                            //~v@01I~
            }                                                      //~v@01I~
        	HistoryDlg.newInstance().show();                       //~9614R~
        }                                                          //~9614I~
        catch (Exception e)                                        //~9614I~
        {                                                          //~9614I~
        	Dump.println(e,"History:showDlg");                     //~9614I~
        }                                                          //~9614I~
	}                                                              //~9614I~
	//************************************************************ //~9826I~
	//*From SuspendDlg for Suspend.interrupted                     //~9826I~
	//************************************************************ //~9826I~
    public void saveRuleInterrupted()                              //~9826R~
    {                                                              //~9826I~
        String fpath=makeFullpathHistoryRule();//~9826I~
		if (Dump.Y) Dump.println("History.saveRuleInterrupted fpath="+fpath);//~9826I~
        if (fpath==null)                                           //~9826I~
        	return;                                                //~9826I~
        if ((new File(fpath)).exists())                               //~9826I~//~9827R~
        {                                                          //~9826I~
			if (Dump.Y) Dump.println("History.saveRuleInterrupted already exist="+fpath);//~9826I~
            return;                                                //~9826I~
        }                                                          //~9826I~
    	SettingDlg.saveProperties(fpath,CMT_HISTORY_RULE);           //~9826I~
    }                                                              //~9826I~
	//************************************************************ //~9826I~
    public String makeFullpathHistoryRule()                          //~9826I~
    {                                                              //~9826I~
	    String sd=syncDateToFilename();                                   //~9826I~
        if (sd==null)                                              //~9826I~
        {                                                          //~9826I~
			if (Dump.Y) Dump.println("History.makeFullpathHistoryRule syncdate:null");//~9826I~
        	return null;                                           //~9826I~
        }                                                          //~9826I~
        String fpath=UFile.makeFullpath(pathHistory,sd,EXT_HISTORY_RULE);//~9826R~
		if (Dump.Y) Dump.println("History.saveRuleInterrupted syncdate="+sd+",fpath="+fpath);//~9826I~
        return fpath;                                              //~9826I~
    }                                                              //~9826I~
	//************************************************************ //~9826I~
    private static String syncDateToFilename()                     //~9826I~
    {                                                              //~9826I~
        String sd=RuleSetting.getSyncDateFormatted();              //~9826I~
        if (sd==null)                                              //~9826I~
        {                                                          //~9826I~
			if (Dump.Y) Dump.println("History.syncDateToFilename syncdate:null");//~9826I~
        	return null;                                           //~9826I~
        }                                                          //~9826I~
        String sd1=sd.replace("/","");                             //~9826I~
        String sd2=sd1.replace(".","");                            //~9826I~
		if (Dump.Y) Dump.println("History.syncDateToFilename syncdate="+sd+",filename="+sd2);//~9826I~
        return sd2;                                                //~9826I~
    }                                                              //~9826I~
	//**********************************                           //~9826I~
    public static String getSyncDateShort()                        //~9826I~
    {                                                              //~9826I~
        String sd=syncDateToFilename();                             //~9826I~
        if (sd==null)                                              //~9826I~
        {                                                          //~9826I~
			if (Dump.Y) Dump.println("History.getSyncDateShort syncdate:null");//~9826I~
        	return "NoSyncDate";                                   //~9826I~
        }                                                          //~9826I~
//      String sd2=sd.substring(2);	//yymmdd-hhmmss                //~9826R~
        String sd2=sd;             	//yyyymmdd-hhmmss              //~9826I~
		if (Dump.Y) Dump.println("History.getSyncDateShort rc="+sd2);//~9826I~
        return sd2;                                                //~9826I~
    }                                                              //~9826I~
	//************************************************************ //~9829I~
    public static String getRuleFileName(String PruleID)           //~9829R~
    {                                                              //~9829I~
        int pos=PruleID.lastIndexOf(RULEID_SEP_TIMESTAMP);         //~9829I~
        String fnm;                                                //~9829I~
        if (pos<0)                                                 //~9829I~
        	fnm=PruleID;                                           //~9829I~
        else                                                       //~9829I~
        	fnm=PruleID.substring(pos+1);                          //~9829I~
		if (Dump.Y) Dump.println("History.getRuleFileName id="+PruleID+",fnm="+fnm);//~9829R~
        return fnm;                                              //~9829R~
    }                                                              //~9829I~
	//************************************************************ //~9829I~
    public String getRuleFileFpath(String PruleID)                 //~9829I~
    {                                                              //~9829I~
		String fnm=getRuleFileName(PruleID);                       //~9829I~
		String fpath=UFile.makeFullpath(pathHistory,fnm,EXT_HISTORY_RULE);//~9829I~
		if (Dump.Y) Dump.println("History.getRuleFilFpath id="+PruleID+",fnm="+fnm+",fpath="+fpath);//~9829I~
        return fpath;                                              //~9829I~
    }                                                              //~9829I~
	//************************************************************ //~9826I~
    public boolean sendRule(boolean PswServer,HistoryData Phd)                              //~9826R~//~9827R~//~v@01R~
    {                                                              //~9826I~
		if (Dump.Y) Dump.println("History.sendRule swServer="+PswServer);     //~9826I~//~9827R~
        boolean rc;                                                //~v@01I~
	    String fpath=getFpathRule(Phd);                            //~v@01R~
        if (fpath!=null)                                           //~v@01I~
    		sendRuleFile(PswServer,fpath);                         //~v@01I~
        rc=fpath!=null;                                            //~v@01I~
		if (Dump.Y) Dump.println("History.sendRule rc="+rc);       //~v@01I~
        return rc;                                                 //~v@01I~
    }                                                              //~9826I~
	//************************************************************ //~v@01I~
    public String getFpathRule(HistoryData Phd)                    //~v@01R~
    {                                                              //~v@01I~
		if (Dump.Y) Dump.println("History.getFpathRule");          //~v@01I~
        String[][] strss=Phd.HD;                                   //~v@01I~
        String ruleid=strss[HDPOS_HDR][POS_RULEID];                //~v@01I~
    	String fnm=getRuleFileName(ruleid);                        //~v@01I~
        String fpath=UFile.makeFullpath(pathHistory,fnm,EXT_HISTORY_RULE);//~v@01I~
        if (!((new File(fpath)).exists()))                         //~v@01I~
        {                                                          //~v@01I~
			if (Dump.Y) Dump.println("History.sendRule Notfound fnm="+fpath);//~v@01I~
        	UView.showToast(Utils.getStr(R.string.Warn_HistoryPropNotSent,fnm));//~v@01I~
            return null;                                           //~v@01I~
        }                                                          //~v@01I~
		if (Dump.Y) Dump.println("History.getFpathRule fpath="+fpath);//~v@01I~
        return fpath;                                              //~v@01I~
    }	                                                           //~v@01I~
	//************************************************************ //~v@01I~
    private void sendRuleFile(boolean PswServer,String Pfpath)     //+v@01R~
    {                                                              //~v@01I~
		if (Dump.Y) Dump.println("History.sendRule fpath="+Pfpath); //~v@01I~
        StringBuffer sb=UFile.fileToStringBuffer(Pfpath);           //~v@01I~
        if (sb==null)                                              //~v@01I~
        {                                                          //~v@01I~
			if (Dump.Y) Dump.println("History.sendRule readFailed");//~v@01I~
            return;                                                //~v@01I~
        }                                                          //~v@01I~
        sb.append(HISTORY_FILENAME+Pfpath);                         //~v@01I~
        String msg=sb.toString();                                  //~v@01I~
        if (PswServer)                                             //~v@01I~
			AG.aBTMulti.sendMsgToAllClientProp(GCM_SETTING_HISTORY,msg);//~v@01I~
        else                                                       //~v@01I~
	        AG.aBTMulti.sendMsgToServerProp(GCM_SETTING_HISTORY,msg); //same as Properties//~v@01I~
    }                                                              //~v@01I~
//    //************************************************************//~v@01I~
//    public void sendRule(boolean PswServer,HistoryData Phds)     //~v@01I~
//    {                                                            //~v@01I~
//        if (Dump.Y) Dump.println("History.sendRule swServer="+PswServer);//~v@01I~
//        String[][] strss=Phds.HD;                                //~v@01I~
//        String ruleid=strss[HDPOS_HDR][POS_RULEID];              //~v@01I~
////        int pos=ruleid.lastIndexOf(RULEID_SEP_TIMESTAMP);      //~v@01I~
////        String fnm;                                            //~v@01I~
////        if (pos<0)                                             //~v@01I~
////            fnm=ruleid;                                        //~v@01I~
////        else                                                   //~v@01I~
////            fnm=ruleid.substring(pos+1);                       //~v@01I~
//        String fnm=getRuleFileName(ruleid);                      //~v@01I~
//        String fpath=UFile.makeFullpath(pathHistory,fnm,EXT_HISTORY_RULE);//~v@01I~
//        if (!((new File(fpath)).exists()))                       //~v@01I~
//        {                                                        //~v@01I~
//            if (Dump.Y) Dump.println("History.sendRule Notfound fnm="+fpath);//~v@01I~
//            UView.showToast(Utils.getStr(R.string.Warn_HistoryPropNotSent,fnm));//~v@01I~
//            return;                                              //~v@01I~
//        }                                                        //~v@01I~
//        if (Dump.Y) Dump.println("History.sendRule fpath="+fpath);//~v@01I~
//        StringBuffer sb=UFile.fileToStringBuffer(fpath);         //~v@01I~
//        if (sb==null)                                            //~v@01I~
//        {                                                        //~v@01I~
//            if (Dump.Y) Dump.println("History.sendRule readFailed");//~v@01I~
//            return;                                              //~v@01I~
//        }                                                        //~v@01I~
//        sb.append(HISTORY_FILENAME+fpath);                       //~v@01I~
//        String msg=sb.toString();                                //~v@01I~
//        if (PswServer)                                           //~v@01I~
//            AG.aBTMulti.sendMsgToAllClientProp(GCM_SETTING_HISTORY,msg);//~v@01I~
//        else                                                     //~v@01I~
//            AG.aBTMulti.sendMsgToServerProp(GCM_SETTING_HISTORY,msg); //same as Properties//~v@01I~
//    }                                                            //~v@01I~
	//************************************************************ //~9826I~
//    public void saveReceivedRule(String Pprops)                    //~9826I~//~9827R~
//    {                                                              //~9826I~//~9827R~
//        if (Dump.Y) Dump.println("History.saveReceivedRule props="+Pprops);//~9826I~//~9827R~
//        int pos=Pprops.lastIndexOf(HISTORY_FILENAME);              //~9826I~//~9827R~
//        if (pos<0)                                                 //~9826I~//~9827R~
//        {                                                          //~9826I~//~9827R~
//            if (Dump.Y) Dump.println("History.saveReceivedRule no filename found "+HISTORY_FILENAME);//~9826I~//~9827R~
//            return;                                                //~9826I~//~9827R~
//        }                                                          //~9826I~//~9827R~
//        String fpath=Pprops.substring(pos+HISTORY_FILENAME.length());  //~9826I~//~9827R~
//        pos=fpath.lastIndexOf("\n");                             //~9827R~
//        if (pos>0)                                               //~9827R~
//            fpath=fpath.substring(0,pos);                        //~9827R~
//        if (Dump.Y) Dump.println("History.saveReceived fnm="+fpath);     //~9826R~//~9827R~
////      String fpath=UFile.makeFullpath(pathHistory,fnm,EXT_HISTORY_RULE);//~9826R~//~9827R~
//        if ((new File(fpath)).exists())                          //~9827R~
//        {                                                        //~9827R~
//            if (Dump.Y) Dump.println("History.saveReceivedRule Already exist="+fpath);//~9827R~
//            return;                                              //~9827R~
//        }                                                        //~9827R~
//        Prop.savePropertiesString(fpath,Pprops);                   //~9826R~//~9827R~
//    }                                                              //~9826I~//~9827R~
    public void saveReceivedRule(StringBuffer Pprops)              //~9827I~
    {                                                              //~9827I~
        if (Dump.Y) Dump.println("History.saveReceivedRule props="+Pprops.toString());//~9827I~
        int pos=Pprops.lastIndexOf(HISTORY_FILENAME);              //~9827I~
        if (pos<0)                                                 //~9827I~
        {                                                          //~9827I~
            if (Dump.Y) Dump.println("History.saveReceivedRule no filename found "+HISTORY_FILENAME);//~9827I~
            return;                                                //~9827I~
        }                                                          //~9827I~
        String props=Pprops.substring(0,pos);                      //~9827I~
        String fpath=Pprops.substring(pos+HISTORY_FILENAME.length());//~9827I~
        if (Dump.Y) Dump.println("History.saveReceivedRule fpath="+fpath+",props="+props);//~9827I~
        pos=fpath.lastIndexOf("\n");                               //~9827I~
        if (pos>0)                                                 //~9827I~
            fpath=fpath.substring(0,pos);                          //~9827I~
        if (Dump.Y) Dump.println("History.saveReceived fnm="+fpath);//~9827I~
        if ((new File(fpath)).exists())                            //~9827I~
        {                                                          //~9827I~
            if (Dump.Y) Dump.println("History.saveReceivedRule Already exist="+fpath);//~9827I~
            return;                                                //~9827I~
        }                                                          //~9827I~
        Prop.savePropertiesString(fpath,props);                   //~9827I~
        UView.showToast(Utils.getStr(R.string.Info_HistoryRuleSavedReceived,fpath));//~v@01I~
    }                                                              //~9827I~
	//************************************************************ //~9828I~
    public static int[] getCurrentEswn(HistoryData Phds)           //~9828R~
    {                                                              //~9828I~
		if (Dump.Y) Dump.println("History.getCurrentEswn HD="+Utils.toString(Phds.scores));//~9828R~
        int ctrGame=Phds.scores[HDPOS_GAMESEQ-HDPOS_SCORES][1];    //~9828R~
        int[] winds=Accounts.getCurrentEswnByPositionAtResume(ctrGame);    //~9828I~
		if (Dump.Y) Dump.println("History.getCurrentEswn ctrGame="+ctrGame+",wind="+Arrays.toString(winds));//~9828I~
        return winds;                                               //~9828R~
    }                                                              //~9828I~
}//class                                                           //~v@@@R~
