//*CID://+v@01R~:                             update#=  497;       //~v@@@R~//~9404R~//~v@01R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.game;                                        //~v@@@R~//~9615R~

import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.dialog.RuleSettingEnum;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Prop;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;


import java.util.Arrays;

import static com.btmtest.dialog.SuspendDlg.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~//~9828I~
import static com.btmtest.game.History.*;

//*****************************************************************//~9615I~
public class HistoryData                                              //~9614I~//~9615R~
{                                                              //~9614I~//~9615R~
    public  static final int    HDCTR_HDR=5; 	//version+timestamp+gametype+ruleid+endgameType//~9615I~//~9823R~//~9825R~//~9828M~
    public  static final int    MAXLINECTR=HDCTR_HDR+PLAYERS*2;    //~9615R~//~9825R~//~9828M~
//  public  static final int    MAXLINECTR_INTERRUPTED=HDCTR_HDR+PLAYERS*4;//~9825R~//~9828M~//~v@01R~
    public  static final int    MAXLINECTR_INTERRUPTED=HDCTR_HDR+PLAYERS*5;//~v@01I~
                                                                   //~9828I~
    public  static final int    HDPOS_HDR=0;                       //~9615R~//~9828M~
    public  static final int      POS_VERSION=0;                     //~9614I~//~9615R~//~9828M~//~9829I~
    public  static final int      POS_TIMESTAMP=1;                   //~9614I~//~9615R~//~9828M~//~9829I~
    public  static final int      POS_GAMESET=2;                      //~9614I~//~9615R~//~9828M~//~9829I~
    public  static final int      POS_RULEID=3;                      //~9615R~//~9828M~//~9829I~
    public  static final int      POS_ENDGAMETYPE=4;                 //~9823I~//~9828M~//~9829I~
    public  static final int    HDPOS_MEMBER=1;                    //~9615R~//~9828M~
    public  static final int    HDPOS_SCORES=2;                     //~9614R~//~9615R~//~9828R~
    public  static final int    HDPOS_SCORE=2;                     //~9828I~
    public  static final int    HDPOS_SCORES_SCORE=0;              //~9829I~
    public  static final int    HDPOS_REACH=3;                     //~9825I~//~9828M~
    public  static final int    HDPOS_SCORES_REACH=1;              //~9829I~
    public  static final int    HDPOS_GAMESEQ=4;                   //~9825I~//~9828M~
    public  static final int    HDPOS_SCORES_GAMESEQ=2;            //~9829I~
    public  static final int      POS_GAMESEQ_CTRSET=0;            //~9829I~
    public  static final int      POS_GAMESEQ_CTRGAME=1;           //~9829I~
    public  static final int      POS_GAMESEQ_CTRDUP=2;            //~9829R~
    public  static final int      POS_GAMESEQ_CTRREACH=3;          //~9829R~
    public  static final int    HDPOS_OTHER=5;                     //~v@01I~
    public  static final int    HDPOS_SCORES_OTHER=3;              //~v@01I~
    public  static final int      POS_OTHER_ENDGAME=0;             //~v@01I~
    public  static final int      POS_OTHER_NEXTGAME=1;            //~v@01I~
    public  static final int      POS_OTHER_BIRDANDCONT=2;         //~v@01I~
    public  static final int    HDPOS_MAX=HDPOS_SCORE+1;           //~9825I~//~9828M~
//  public  static final int    HDPOS_MAX_INTERRUPTED=HDPOS_GAMESEQ+1;//~9825I~//~9828M~//~v@01R~
    public  static final int    HDPOS_MAX_INTERRUPTED=HDPOS_OTHER+1;//~v@01I~
    //****************************************                                                               //~9613I~//~9828I~
	public String[][] HD;                                          //~9615R~
	public int[][] scores;                                         //~9828I~
	public int[] idxMembers;	//pointer to Interrupted Game member table to current member//~v@01R~
	public int posServer;                                          //~9828I~
	public int gamesetType=-1;                                     //~9829I~
	public Prop ruleProp;                                          //~9829I~
    //****************************************                     //~9828I~
    public HistoryData(String[][] PhistoryData)                    //~9615R~
    {                                                          //~9614I~//~9615R~
        HD=PhistoryData;                                           //~9615R~
        if (Dump.Y) Dump.println("HistoryData constructor HD="+Utils.toString(HD));//~9615R~
    }                                                          //~9614I~//~9615R~
    //****************************************                     //~9828I~
    public int[][] setScores()                                     //~9828R~
    {                                                              //~9828I~
        scores=getIntValue();                                      //~9828R~
        if (Dump.Y) Dump.println("HistoryData.setScores score="+Utils.toString(scores));//~9828I~
        return scores;                                             //~9828I~
    }                                                              //~9828I~
	//************************************************************ //~9828M~
    public int[][] getIntValue()                                   //~9828I~
    {                                                              //~9828M~
		if (Dump.Y) Dump.println("HistoryData.getIntValue HD="+Utils.toString(HD));//~9828I~
        int[][] scores=null;                                       //~9828M~
        try                                                        //~9828M~
        {                                                          //~9828M~
            int err=0;                                             //~9828M~
            int[] score=new int[PLAYERS];                          //~9828M~
            int[] ctrReach=new int[PLAYERS];                       //~9828M~
            int[] gameSeq=new int[PLAYERS];                        //~9828M~
            int[] other=new int[PLAYERS];                          //~v@01I~
            String[] strScore=HD[HDPOS_SCORE];                     //~9828I~
	        String[] strReach=HD[HDPOS_REACH];                     //~9828I~
	        String[] strGameSeq=HD[HDPOS_GAMESEQ];                 //~9828I~
	        String[] strOther=HD[HDPOS_OTHER];                     //~v@01I~
            err+=Utils.parseInt(strScore,-1,score);                //~9828M~
            err+=Utils.parseInt(strReach,-1,ctrReach);             //~9828M~
            err+=Utils.parseInt(strGameSeq,-1,gameSeq);            //~9828M~
            err+=Utils.parseInt(strOther,-1,other);                //~v@01I~
//          scores=new int[][]{score,ctrReach,gameSeq};            //~9828M~//~v@01R~
            scores=new int[][]{score,ctrReach,gameSeq,other};      //~v@01I~
			if (Dump.Y) Dump.println("HistoryData.getIntValue scores="+Utils.toString(scores));//~9828I~
            if (err!=0)                                            //~9828M~
	            scores=null;                                       //~9828M~
        }                                                          //~9828M~
        catch (Exception e)                                        //~9828M~
        {                                                          //~9828M~
            Dump.println("HistoryData:getIntValue exception");     //~9828I~
        }                                                          //~9828M~
		if (Dump.Y) Dump.println("HistoryData.getIntValue scores="+Utils.toString(scores));//~9828I~
        return scores;                                             //~9828M~
    }                                                              //~9828M~
	//************************************************************ //~9828I~
	//*pointer to Interrupted Game member table to current member  //~v@01I~
	//************************************************************ //~v@01I~
    public  boolean setupMembers()                                 //~9828R~
    {                                                              //~9828I~
    	boolean rc=false;                                          //~9828I~
		if (Dump.Y) Dump.println("HistoryData.setupMembers");      //~9828R~
        String[] names=HD[HDPOS_MEMBER];                           //~9828I~
        int[] idxs=new int[PLAYERS];                               //~9828I~
        Members memb=AG.aBTMulti.BTGroup;                           //~9828I~
        if (Dump.Y)	memb.toStringMD("HistoryData.setupMembers");   //~v@01R~
        int ctrRobot=0;                                                //~v@01I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9828I~
        {                                                          //~9828I~
        	String name=names[ii];                   //~;9828R~   //~9828I~
        	int idx;                                               //~9828I~
			int idxRobot=Accounts.isRobotName(name);                   //~9828I~
            if (idxRobot>0)                                        //~9828I~
            {                                                      //~9828I~
//              idx=PLAYERS-idxRobot;   //by naming at Account constructot  4,3,2,1 on Members//~9828I~//~v@01R~
        		idx=memb.searchRobot(ctrRobot++);                  //~v@01I~
            }                                                      //~9828I~
            else                                                   //~9828I~
            {                                                      //~9828I~
        		idx=memb.searchByYourname(name);                   //~;9828R~//~9828R~
            	if (idx<0)                                         //~9828R~
            		return false;                                  //~9828R~
            }                                                      //~9828I~
        	idxs[ii]=idx;                                          //~9828I~
            if (memb.MD[idx].isServer())                           //~9828I~
            	posServer=ii;                                      //~9828I~
        }                                                          //~9828I~
        idxMembers=idxs;                                           //~9828I~
		if (Dump.Y) Dump.println("HistoryData.setupMembers idxMembers="+ Arrays.toString(idxMembers));//~9828I~//~v@01R~
        return true;                                               //~9828I~
    }                                                              //~9828I~
	//************************************************************ //~v@01I~
	//*get name by account seq                                     //~v@01I~
	//************************************************************ //~v@01I~
    public String getName(int PidxAccount)                         //~v@01I~
    {                                                              //~v@01I~
        String rc=HD[HDPOS_MEMBER][PidxAccount];                   //~v@01I~
		if (Dump.Y) Dump.println("HistoryData.getName idx="+PidxAccount+",rc="+rc);//~v@01I~
        return rc;                                                 //~v@01I~
    }                                                              //~v@01I~
	//************************************************************ //~v@01I~
	//*get old position of yourname                                //~v@01I~
	//************************************************************ //~v@01I~
    public int  getOldPosition(String Pyourname)                   //~v@01R~
    {                                                              //~v@01I~
    	int rc=-1;                                                 //~v@01I~
        String[] names=HD[HDPOS_MEMBER];                           //~v@01I~
		if (Dump.Y) Dump.println("History.getOldPosition member="+Arrays.toString(names));//~v@01I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@01I~
        {                                                          //~v@01I~
        	if (Pyourname.compareTo(names[ii])==0)                   //~;9828R~//~v@01R~
            {                                                      //~v@01I~
            	rc=ii;                                             //~v@01I~
                break;                                             //~v@01I~
            }                                                      //~v@01I~
        }                                                          //~v@01I~
		if (Dump.Y) Dump.println("History.getOldPosition yourname="+Pyourname+",rc="+rc);//~v@01R~
        return rc;                                                 //~v@01I~
    }                                                              //~v@01I~
	//************************************************************ //~9829I~
    public int getGameSetType()                                    //~9829I~
    {                                                              //~9829I~
    	int tp=-1;                                                 //~9829I~
    	if (gamesetType<0)                                         //~9829I~
        {                                                          //~9829I~
        	String strstp[]= RuleSettingEnum.strsGameSetType;         //~9829I~
        	String strtp=HD[HDPOS_HDR][POS_GAMESET];               //~9829I~
            for (int ii=0;ii<strstp.length;ii++)                   //~9829I~
            	if (strtp.equals(strstp[ii]))                      //~9829I~
                {                                                  //~9829I~
                	tp=ii;                                         //~9829I~
                    break;                                         //~9829I~
                }                                                  //~9829I~
        }                                                          //~9829I~
        gamesetType=tp;                                            //~9829I~
		if (Dump.Y) Dump.println("HistoryData.getGameSetType rc="+gamesetType);//~9829I~
        return gamesetType;                                        //~9829I~
    }                                                              //~9829I~
	//************************************************************ //~9829I~
    public Prop getRuleProp()                                       //~9829I~
    {                                                              //~9829I~
		if (Dump.Y) Dump.println("HistoryData.getRuleProp");       //~v@01I~
    	Prop P=ruleProp;                                           //~9829R~
    	if (P==null)                                               //~9829R~
        {                                                          //~9829I~
        	String ruleid=HD[HDPOS_HDR][POS_RULEID];               //~9829R~
	    	String fnm=AG.aHistory.getRuleFileFpath(ruleid);           //~9829R~
            P=new Prop();                                           //~9829I~
            boolean rc=P.loadProperties(fnm);                      //~9829I~
			if (Dump.Y) Dump.println("HistoryData.getRuleProp rc="+rc+",fnm="+fnm);//~9829I~
            if (!rc)                                               //~9829I~
            {                                                      //~9829I~
				UView.showToastLong(Utils.getStr(R.string.Err_HistoryPropLoadFailed,ruleid));//~9829I~//~v@01R~
            	P=null;                                            //~9829I~
            }                                                      //~9829I~
        }                                                          //~9829I~
        ruleProp=P;                                                //~9829I~
        return ruleProp;                                           //~9829I~
    }                                                              //~9829I~
	//************************************************************ //~v@01I~
    public int setEndgameType(int PendgameType)                    //~v@01I~
    {                                                              //~v@01I~
    	String old=HD[HDPOS_HDR][POS_ENDGAMETYPE];                 //~v@01I~
		if (Dump.Y) Dump.println("HistoryData.setEndgameType old="+old+",new="+PendgameType);//~v@01I~
    	HD[HDPOS_HDR][POS_ENDGAMETYPE]=Integer.toString(PendgameType);//~v@01I~
        int rc=Utils.parseInt(old,SUSPENDGAME_INTERRUPTED);        //~v@01I~
        return rc;                                                 //~v@01I~
    }                                                              //~v@01I~
	//************************************************************ //~v@01I~
    public boolean isInterruptedResume()                           //~v@01R~
    {                                                              //~v@01I~
    	String old=HD[HDPOS_HDR][POS_ENDGAMETYPE];                 //~v@01I~
		if (Dump.Y) Dump.println("HistoryData.isInterruptedResume old="+old);//~v@01I~
        boolean rc=Utils.parseInt(old,SAVE_EGTP_INTERRUPTED_RESUME)==SAVE_EGTP_INTERRUPTED_RESUME;//~v@01R~
		if (Dump.Y) Dump.println("HistoryData.isInterruptedRsume old="+old+",rc="+rc);//+v@01R~
        return rc;                                                 //~v@01I~
    }                                                              //~v@01I~
	//************************************************************ //~v@01I~
    public String toString()                                          //~v@01I~
    {                                                              //~v@01I~
    	return Utils.toString(HD)+"\n"+Utils.toString(scores)+"\nidxMember="+Arrays.toString(idxMembers)+"\nposServer="+posServer;//~v@01R~
    }                                                              //~v@01I~
    //***********************************************              //~9825I~//~v@01I~
    public static String makeSendData(HistoryData Phds)                  //~9825I~//~v@01I~
    {                                                              //~9825I~//~v@01I~
		if (Dump.Y) Dump.println("HistoryDlg.makeSenddata HD="+Utils.toString(Phds.HD));//~9825I~//~v@01I~
        StringBuffer sb=new StringBuffer();                        //~9825I~//~v@01I~
        String[][] strss=Phds.HD;                                  //~9825I~//~v@01I~
        for (int ii=0;ii<strss.length;ii++)                        //~9825I~//~v@01I~
        {                                                          //~9825I~//~v@01I~
        	if (ii!=0)                                             //~9825I~//~v@01I~
//              sb.append("\n");                                   //~9825I~//~v@01I~
                sb.append(CRLF);                                   //~v@01I~
        	String[] strs=strss[ii];                               //~9825I~//~v@01I~
        	for (int jj=0;jj<strs.length;jj++)                     //~9825I~//~v@01I~
            {                                                      //~9825I~//~v@01I~
            	if (jj!=0)                                         //~9825I~//~v@01I~
//              	sb.append('\t');                               //~9825I~//~v@01I~
                	sb.append(TAB);                                //~v@01I~
            	sb.append(strs[jj]);                                    //~9825I~//~v@01I~
            }                                                      //~9825I~//~v@01I~
        }                                                          //~9825I~//~v@01I~
        String rc=sb.toString();                                   //~9825I~//~v@01I~
		if (Dump.Y) Dump.println("HistoryDlg.makeSenddata rc="+rc);//~9825I~//~v@01I~
        return rc;                                                 //~9825I~//~v@01I~
    }                                                              //~9825I~//~v@01I~
    //***********************************************              //~9825I~//~v@01I~
    public static HistoryData parseReceivedData(String Phds)      //~9825R~//~v@01I~
    {                                                              //~9825I~//~v@01I~
		if (Dump.Y) Dump.println("HistoryDlg.parseReceiveddata hds="+Phds);//~9825I~//~v@01I~
//      String[] lines=Phds.split("\n",0);                         //~9825I~//~v@01I~
        String[] lines=Phds.split(CRLF,0);                         //~v@01I~
		if (Dump.Y) Dump.println("HistoryDlg.parseReceiveddata lines="+Arrays.toString(lines));//~9825I~//~v@01I~
        int linectr=lines.length;                                  //~9825I~//~v@01I~
        if (linectr!=HDPOS_MAX &&  linectr!=HDPOS_MAX_INTERRUPTED) //~9825R~//~v@01I~
        	return null;                                           //~9825I~//~v@01I~
        String[][] strss=new String[linectr][];                    //~9825I~//~v@01I~
        for (int ii=0;ii<linectr;ii++)                             //~9825I~//~v@01I~
        {                                                          //~9825I~//~v@01I~
//      	String[] strs=lines[ii].split("\t",0);                   //~9825I~//~v@01I~
        	String[] strs=lines[ii].split(TAB,0);                  //~v@01I~
            strss[ii]=strs;                                        //~9825I~//~v@01I~
        }                                                          //~9825I~//~v@01I~
        if (strss[HDPOS_HDR].length!=HDCTR_HDR)                    //~9825I~//~v@01I~
        	return null;                                           //~9825I~//~v@01I~
		if (Dump.Y) Dump.println("HistoryDlg.parseReceiveddata strss="+Utils.toString(strss));//~9825I~//~v@01I~
        HistoryData hd=new HistoryData(strss);                     //~9825I~//~v@01I~
        return hd;                                                 //~9825I~//~v@01I~
    }                                                              //~9825I~//~v@01I~
}//class                                                           //~v@@@R~
