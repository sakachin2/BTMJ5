//*CID://+vakSR~: update#= 492;                                    //~vakSR~
//**********************************************************************//~v101I~
//2022/03/23 vakS (Bug)dismiss CompleteDialog for DobleRon by Robot//~vakSI~
//2021/03/30 va73 (Bug of va60)when multiron of human and robot,reply OK on CompReqDlg is always set, so dismissAll issued at 1st clickOK.//~va73I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/10/20 va1c send net point to show setYaku on CompReqDlg     //~va1cI~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Color;
                                                                   //~v@@@I~
import com.btmtest.dialog.CompReqDlg;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.game.UserAction.*;

public class Complete                            //~v@@@R~
{                                                                  //~0914I~
    public  static final int CALC_AMT_NETUP    =0; //index of calcPoint output,result of roundup//~9220I~//~9221R~//~v@@@M~
    public  static final int CALC_AMT_NET      =1; //before roundup//~9220I~//~9221R~//~v@@@M~
    public  static final int CALC_AMT_DEALER   =2; //for dealer when TakenUp//~9220I~//~9221R~//~v@@@M~
    public  static final int CALC_AMT_NONDEALER=3; //for non dealer when takeUp//~9220I~//~9221R~//~v@@@M~
    public  static final int CALC_AMT_NONDEALER_CUTPOS=4;   //non dealer is cutPlayer when Take//~9530I~
    public  static final int CALC_AMT_IDXPOINT =5;                 //~9220I~//~9221R~//~v@@@R~//~9530R~
    public  static final int CALC_AMT_IDXRANK  =6;                 //~v@@@I~//~9530R~
//  public  static final int CALC_AMT_MAXCTR   =6;                 //~v@@@I~//~9530R~
//  public  static final int CALC_AMT_MAXCTR   =7;                 //~9530I~//~va11R~
    public  static final int CALC_AMT_RANKHIGH =7; //longRank int high//~va11I~
    public  static final int CALC_AMT_RANKLOW  =8; //low           //~va11I~
    public  static final int CALC_AMT_HAN      =9; //low           //~va11I~
//  public  static final int CALC_AMT_MAXCTR   =10;                //~va11R~//~va1cR~
    public  static final int CALC_AMT_NETPOINT =10; //low          //~va1cI~
    public  static final int CALC_AMT_MAXCTR   =11;                //~va1cI~
                                                                   //~v@@@I~
    public  static final int CALC_AMT_POS   =1;      //OK/NG+ammount values//~v@@@I~
                                                                   //~v@@@I~
//  public static final int COMPLETE_COLOR=Color.argb(0x60,0xff,0xff,0x33);	//orange//~v@@@R~
//  public static final int COMPLETE_COLOR=Color.argb(0xff,0xff,0xff,0x00);	//orange//~v@@@R~
//  public static final int COMPLETE_COLOR=Color.argb(0xff,0xff,0x66,0x00); //orange//~v@@@I~//~9B23R~
//  public static final int COMPLETE_COLOR=Color.argb(0xff,0xff,0x00,0x00); //red//~9B23R~
    public static final int COMPLETE_COLOR=Color.argb(0xff,0xff,0x33,0x66); //red//~9B23I~
//  public static final int COMPLETE_STROKE_WIDTH=4;               //~v@@@R~//~0401R~
    public static final int COMPLETE_TAKEN          =0x01;         //~v@@@R~
    public static final int COMPLETE_RIVER          =0x02;         //~v@@@R~
    public static final int COMPLETE_KAN_TAKEN      =0x04;         //~v@@@R~
    public static final int COMPLETE_KAN_RIVER      =0x08;         //~v@@@R~
    public static final int COMPLETE_KAN_ADD        =0x10;  //chankan//~v@@@R~
    public static final int COMPLETE_KAN_TAKEN_OTHER=0x20;	//ankan ron//~v@@@R~
    public static final int COMPLETE_OPEN           =0x80;         //~v@@@R~
    public Status[] statusS=new Status[PLAYERS];                   //~v@@@R~
    public Status[] sortedStatusS;                                 //~v@@@I~
                                                                   //~v@@@I~
    public static final int COMPREPLY_BEFORESEND=0;                //~v@@@R~
    public static final int COMPREPLY_NORESP=1;                    //~v@@@I~
    public static final int COMPREPLY_OK=2;                        //~v@@@R~
    public static final int COMPREPLY_NG=3;                        //~v@@@R~
    public static final int COMPREPLY_YOU=4;                       //~v@@@R~
    public static final int COMPREPLY_ROBOT=5;                     //~v@@@R~
                                                                   //~v@@@I~
    public static final int COMPRESULT_REPLY_NONE=0;               //~v@@@I~
    public static final int COMPRESULT_REPLY_OK=1;                 //~v@@@I~
    public static final int COMPRESULT_REPLY_NG=2;                 //~v@@@I~
                                                                   //~9420I~
    public  static final int POINT_RANKM  =8000;                   //~9420M~//~9B10R~
    public  static final int POINT_RANKM_DEALER  =12000;           //~9213I~//~9420M~//~9B10R~
                                                                   //~9310I~
    public boolean swExistingNotRequested;                         //~9310R~
                                                                   //~v@@@I~
    public int[] resultOK=new int[PLAYERS];                        //~v@@@R~
    private int requesterEswn;                                     //~v@@@I~
//  public int[] lastMinusAmmount,lastMinusScore,lastMinusPay,lastMinusTotal,lastMinusPayerInfo;//~9322R~//~9408R~
    public int[] lastMinusAmmount,lastMinusScore,lastMinusPay,lastMinusTotal,lastMinusCharge,lastMinusChargeResult;//~9408I~
    public int lastEndGameType;                                    //~9413I~
    public int[] finalScore,lastScore;                                         //~9322I~//~9415R~
    public int[] minusPrize,minusCharge;                           //~9415R~
    public int[][] amtsPayedToEswn;      //amt[payedTo][payedFrom] //~9403I~
//    public int[][] amtsError;            //amt[payedTo][payedFrom] //~9414I~
//    public int[][] amtsErrorByLooser;     //amt[payedFrom][payedTo] //~9414I~
    public boolean[] swsErrLooser;    	 //by eswn                 //~9414R~
    public  int[][] amtsError=new int[PLAYERS][];                  //~9420R~
    public  int[][] amtsErrorByLooser=new int[PLAYERS][];          //~9420R~
    public  int[] amtPending=new int[PLAYERS];                   //~9420I~
                                                                   //~9422I~
    public  int[] minusPrizeDrawn;                                 //~9420R~//~9422M~
    public  boolean[] swsMangan=new boolean[PLAYERS];                     //~9420I~//~9422R~//~9501R~
//  public  boolean[] swsPending=new boolean[PLAYERS];                    //~9420I~//~9422R~
    public  boolean[] swsPending;                                  //~9504I~
    public  boolean[] swsReach;                                    //~9521I~
    public  boolean[] swsInvalid=new boolean[PLAYERS];//account position seq//~9705R~
//  public  int[][] amtssDrawnMangan;	//[gainer][looser]         //~9420I~//~9422R~
    public  int[] adjustedScoreDrawn;                              //~9422I~
    public  int[] chargeToGainerDrawn;                             //~9422R~
    public  int[] intsCompType=new int[PLAYERS];	//pos seq      //~9521I~
    public  int[] pos2Order;                                       //~9520I~
//  public  int[] intsCtrPendingReach=new int[PLAYERS];            //~9521I~//~9522R~
    public  int typeClose;                                         //~9521I~
    private boolean swDrawnDelayLastTimeout;                       //~9603I~
    private boolean swTotalAgreed;                                 //~9612I~
    private boolean swSort;                                        //~9B10I~
    private boolean swDrawn3R;                                     //~9B29I~
    public int typeNextGame=-1;	//save for reopen dialog           //~0301R~
    public  int[] paoLooser;                                       //~0302I~
    public boolean swSuspend;                                      //~0304R~
    public boolean swSent;  //re-send required when updated(ignore response received after change after send)//~0309I~
//*************************                                        //~v@@@I~
	public Complete()                                              //~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("Complete.Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~9320R~
        AG.aComplete=this;                                         //~v@@@R~
//  	Arrays.fill(statusS,null);                                 //~v@@@I~//~9B10R~
//  	Arrays.fill(resultOK,COMPRESULT_REPLY_NONE);               //~v@@@R~//~9B10R~
    }                                                              //~0914I~//~v@@@R~
    //**************************************************           //~9A12I~
    public void resetComplete()                                    //~9A12I~
    {                                                              //~9A12I~
        if (Dump.Y) Dump.println("Complete.resetComplete");        //~9A12I~
	    newInstance();                                             //~9A12I~
    }                                                              //~9A12I~
    //**************************************************           //~9B10I~
    public static void resetCompleteNextPlayer()                   //~9B10R~
    {                                                              //~9B10I~
        if (Dump.Y) Dump.println("Complete.resetCompleteNextPlayer");//~9B10I~
        AG.aRiver.resetComplete();	//erase frame of COMPLETE_COLOR//~0303I~
        Complete oldCMP=AG.aComplete;                                 //~9B10I~
	    Complete newCMP=newInstance();                                //~9B10I~
        System.arraycopy(oldCMP.statusS,0,newCMP.statusS,0,PLAYERS);     //~9B10I~
        if (oldCMP.sortedStatusS!=null)                            //~9B10I~
		    newCMP.sortedStatusS=oldCMP.sortedStatusS.clone();     //~9B10I~
        if (oldCMP.swsReach!=null)                                        //~9B10I~
		    newCMP.swsReach=oldCMP.swsReach.clone();               //~9B10I~
    }                                                              //~9B10R~
//********************************************************************//~9320I~
//*from ScoreDlg                                                   //~9320I~
//********************************************************************//~9320I~
	public static Complete newInstance()                           //~9320I~
    {                                                              //~9320I~
        if (Dump.Y) Dump.println("Complete.newInstance");          //~9320I~
        return new Complete();                                     //~9320I~
    }                                                              //~9320I~
//*******************************************************************                                        //~v@@@I~//~9320R~
	public boolean chkComplete(Players.Player Pplayer,int Pflag)  //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Complete.chkComplete player="+Pplayer.player+",flag="+Pflag);//~v@@@R~
        return true;                                               //~v@@@R~
    }                                                              //~v@@@I~
//*************************                                        //~v@@@I~
	public void complete(Players.Player Pplayer,int Pflag)         //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Complete.complete player="+Pplayer.player+",flag="+Pflag);//~v@@@I~//~9C11R~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public void setStatus(Status Pstat)                            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Complete.setStatus stat="+Pstat.toString());//~v@@@I~
        statusS[Pstat.completeEswn]=Pstat;                         //~v@@@I~
        swSort=true;                                               //~9B10I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9612I~
	//*before nextgame or show ScoreDlg(minusStop)                 //~9612I~
	//*************************************************************************//~9612I~
    public void setTotalAgreed()                                  //~9612I~
    {                                                              //~9612I~
        if (Dump.Y) Dump.println("Complete.setTotalAgreed");        //~9612I~
        swTotalAgreed=true;                                        //~9612I~
    }                                                              //~9612I~
	//*************************************************************************//~9612I~
    public boolean isTotalAgreed()                                //~9612I~
    {                                                              //~9612I~
        if (Dump.Y) Dump.println("Complete.isTotalAgreed rc="+swTotalAgreed);//~9612I~
        return swTotalAgreed;                                      //~9612I~
    }                                                              //~9612I~
	//*************************************************************************//~v@@@I~
    public int getCtrComplete()                                    //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=0;                                                  //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
	        if (statusS[ii]!=null)                                 //~v@@@I~
            {                                                      //~9B11I~
              if (statusS[ii].isValidCompleteion())                    //~9B11I~
            	rc++;                                              //~v@@@I~
            }                                                      //~9B11I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Complete.getCtrComplete ctr="+rc);//~v@@@I~
        return rc;
    }                                                              //~v@@@I~
	//*************************************************************************//~9320I~
	//*rc:0 allReplayed,1:not all but relyAll exist:-1 noone replayed all//~9B11R~
	//*************************************************************************//~9B11I~
//  public boolean chkCompReqReplyAll()                            //~9320I~//~9B11R~
    public int chkCompReqReplyAll()                                //~9B11I~
    {                                                              //~9320I~
        int ctrComp=0,ctrReply=0;                                  //~9320I~
        int rc=-1;                                                 //~9B11I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9320I~
        {                                                          //~9320I~
	        if (statusS[ii]!=null)                                 //~9320I~
            {                                                      //~9320I~
            	ctrComp++;                                         //~9320I~
		        if (Dump.Y) Dump.println("Complete.chkCompReqReplyAll ii="+ii+",swReplyAll="+statusS[ii].swReplyAll+",replyOK="+Utils.toString(statusS[ii].replyOK));//~va73R~
                if (statusS[ii].swReplyAll)                        //~9320I~
                	ctrReply++;                                     //~9320I~
            }                                                      //~9320I~
        }                                                          //~9320I~
//      boolean rc=ctrComp!=0 && ctrReply==ctrComp;               //~9320I~//~9B11R~
        if (ctrComp!=0)                                            //~9B11I~
			rc=ctrReply==ctrComp ? 0 :1;                           //~9B11I~
        if (Dump.Y) Dump.println("Complete.chkCompReqReplyAll rc="+rc+",ctrComp="+ctrComp+",ctrReply="+ctrReply);//~9320I~//~9B11R~
        return rc;                                                 //~9320I~
    }                                                              //~9320I~
	//*************************************************************************//~v@@@I~
	//*sort by distance from looser                                //~v@@@I~//~9402R~
	//*************************************************************************//~v@@@I~
    public Status[] getSortedStatusS()                                  //~v@@@I~//~9707R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Complete.getSortedStatusS swExistingNotRequested="+swExistingNotRequested+",sortedStatus="+ Utils.toString(sortedStatusS));//~9B10R~
    	if (sortedStatusS==null)                                   //~9707I~
			sortStatusS();                                         //~9707I~
        else                                                       //~9B10I~
        if (swSort)                                                //~9B10I~
        {                                                          //~9B10I~
			sortStatusS();                                         //~9B10I~
            swSort=false;                                          //~9B10I~
        }                                                          //~9B10I~
        else                                                       //~9707I~
			if (swExistingNotRequested)                            //~9707I~
            {                                                      //~9707I~
    			sortStatusS();                                     //~9707I~
            }                                                      //~9707I~
        if (Dump.Y) Dump.println("Complete.getSortedStatusS sortedStatus ctr="+(sortedStatusS==null?0:sortedStatusS.length));//~9707I~
        return sortedStatusS;                                      //~9707I~
    }                                                              //~9707I~
	//*************************************************************************//~9707I~
	//*sort by distance from looser                                //~9707I~
	//*select !swInvalid(swErr is included)                        //~0227I~
	//*************************************************************************//~9707I~
    public Status[] sortStatusS()                                  //~9707I~
    {                                                              //~9707I~
        if (Dump.Y) Dump.println("Complete.sortStatusS");          //~v@@@I~
        int ctr=0,looser=0;                                        //~v@@@I~
        swExistingNotRequested=false;                              //~9310I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
		    if (Dump.Y) Dump.println("Complete.sortStatusS statusS["+ii+"]="+Utils.toString(statusS[ii]));//~9B10I~
            if (statusS[ii]!=null)                                 //~v@@@I~//~9402R~
            {                                                      //~9402I~
		        if (Dump.Y) Dump.println("Complete.sortStatusS ii="+ii+",compEswn="+statusS[ii].completeEswn+",swOK="+statusS[ii].swOK+",swErr="+statusS[ii].swErr+",respall="+statusS[ii].swReplyAll+",swInvalid="+statusS[ii].swInvalid);//~9706R~//~9B10R~
            if (!statusS[ii].swInvalid)                            //~9402I~
            {                                                      //~v@@@I~
//              if (statusS[ii].swOK || statusS[ii].swErr)         //~v@@@R~
//              if (statusS[ii].swReplyAll)                        //~v@@@R~//~9310R~
                {                                                  //~v@@@I~
	            	ctr++;                                         //~v@@@I~
	                looser=statusS[ii].completeEswnLooser;         //~v@@@R~
                }                                                  //~v@@@I~
              if (!statusS[ii].swReplyAll)                         //~0106I~
                if (!statusS[ii].swOK && !statusS[ii].swErr)       //~9310I~
			        swExistingNotRequested=true;                   //~9310I~
            }                                                      //~v@@@I~
            }                                                      //~9402I~
        }                                                          //~v@@@I~
        if (ctr==0)                                                //~v@@@I~
        	return null;                                           //~v@@@I~
        Status[] sortout=new Status[ctr];                          //~v@@@I~
        int pos=looser;                                            //~v@@@I~
        int sortctr=0;                                             //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	pos=Players.nextPlayer(pos);                            //~v@@@I~
//          if (statusS[pos]!=null)                                //~v@@@I~//~9402R~
            if (statusS[pos]!=null && !statusS[pos].swInvalid)     //~9402I~
//              if (statusS[pos].swOK || statusS[pos].swErr)       //~v@@@R~
//              if (statusS[pos].swReplyAll)                      //~v@@@I~//~9228R~//~9310R~
            	{                                                  //~v@@@R~
		        	if (Dump.Y) Dump.println("Complete.sortStatusS sortout ii="+sortctr+",pos="+pos+",stat="+statusS[pos].toString());//~0227I~
            		sortout[sortctr++]=statusS[pos];               //~v@@@R~
            	}                                                  //~v@@@R~
        }                                                          //~v@@@I~
        sortedStatusS=sortout;                                     //~v@@@R~
        return sortout;                                            //~v@@@R~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@M~
    public Status getStatus(int Peswn)                             //~v@@@I~
    {                                                              //~v@@@M~
        Status stat=statusS[Peswn];                                //~v@@@I~
        if (Dump.Y) Dump.println("Complete.getStatus eswn="+Peswn+",status="+toString(stat));//~v@@@R~
        return stat;                                               //~v@@@M~
    }                                                              //~v@@@M~
	//*************************************************************************//~v@@@I~
    public int getCtrNotAgreed()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	int ctr=0;                                                 //~v@@@I~
    	for (Status st:statusS)                                    //~v@@@I~
        {                                                          //~v@@@I~
        	if (st!=null && !st.swOK)                              //~v@@@I~
            	ctr++;                                             //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Complete.getCtrNotAgreed ctr="+ctr);//~v@@@I~
        return ctr;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************************//~v@@@I~
    public static void setAmmount(int[] Pintparm,int Ppos,Status Pstat)//~v@@@I~
    {                                                              //~v@@@I~
        int[] amts=new int[CALC_AMT_MAXCTR];                       //~v@@@R~
        System.arraycopy(Pintparm,Ppos,amts,0,CALC_AMT_MAXCTR);    //~v@@@I~
        if (Dump.Y) Dump.println("Complete.setAmmount from intparm swOK="+Pstat.swOK+",ammount="+Arrays.toString(amts));//~v@@@I~
        Pstat.setAmmount(amts);                                    //~v@@@I~
    }                                                              //~v@@@I~
    //****************************************                     //~v@@@I~
    public static String toString(Status Pstat)                    //~v@@@I~
    {                                                              //~v@@@I~
        return Pstat==null ? "null" : Pstat.toString();            //~v@@@I~
    }                                                              //~v@@@I~
    //****************************************                     //~v@@@I~
    public boolean setResultOK(int PrequesterEswn,int PreplyEswn,boolean PswOK)//~v@@@R~
    {                                                              //~v@@@I~
    	requesterEswn=PrequesterEswn;                              //~v@@@I~
    	resultOK[PreplyEswn]=PswOK ? COMPRESULT_REPLY_OK : COMPRESULT_REPLY_NG;//~v@@@R~
        boolean rc=chkResultOK()==0;                               //~v@@@I~
        if (Dump.Y) Dump.println("Complete.setResultOK eswn="+PrequesterEswn+",replyEswn="+PreplyEswn+",PswOK="+PswOK+",rc="+rc+",resultOK="+Arrays.toString(resultOK));//~v@@@I~//~9315R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //****************************************                     //~v@@@I~
    public int chkResultOK()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=0;                                                  //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	if (ii!=requesterEswn)                                 //~v@@@I~
            {                                                      //~v@@@I~
                if (!AG.aAccounts.isDummyByCurrentEswn(ii))        //~v@@@I~
                    rc+=resultOK[ii]!=COMPRESULT_REPLY_OK ? 0 : 1; //~v@@@R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Complete.chkResultOK rc="+rc);   //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //****************************************                     //~v@@@I~
    //*chk all Complete.Status                                     //~v@@@I~
    //*rc=-1:anyone not replayed,0:NG exist,1:all OK                //~v@@@I~//~9B11R~
    //****************************************                     //~v@@@I~
    public int chkCompReqReply()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=1;                                                  //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	Complete.Status stat=statusS[ii];                      //~v@@@I~
	        if (stat==null)                                        //~v@@@I~
            	continue;                                          //~v@@@I~
            if (!stat.swReplyAll)                                 //~v@@@I~
            {                                                      //~v@@@I~
            	rc=-1;                                             //~v@@@I~
            	break;                                             //~v@@@I~
            }                                                      //~v@@@I~
            if (!stat.swOK)                                        //~v@@@I~
            {                                                      //~v@@@I~
            	rc=0;                                              //~v@@@I~
            	break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Complete.chkReplyStatusS rc="+rc);//~v@@@I~//~9B11R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //****************************************                     //~9705I~
    //*from ScoreDlg                                               //~9705I~
    //*setInvlid() by swsInvalid                                   //~9705I~
    //****************************************                     //~9705I~
    public void setInvalidCompletion()                             //~0227I~
    {                                                              //~0227I~
	    setInvalidCompletion(swsInvalid);                           //~0227I~
    }                                                              //~0227I~
    private void setInvalidCompletion(boolean[] PswsInvalid)                             //~9705I~//~0227R~//~0301R~
    {                                                              //~9705I~
     	if (Dump.Y) Dump.println("Complete.setInvaldCompleteion PswsInvalid="+Arrays.toString(PswsInvalid));//~9705I~//~0227R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9705I~
        {                                                          //~9705I~
        	Complete.Status stat=statusS[ii];                      //~9705I~
	        if (stat==null)                                        //~9705I~
            	continue;                                          //~9705I~
        	int pos=AG.aAccounts.currentEswnToPosition(stat.completeEswn);//~9705I~
            stat.setInvalid(PswsInvalid[pos]);                      //~9705I~//~0227R~
	     	if (Dump.Y) Dump.println("Complete.setInvaldCompleteion ii="+ii+",compEswn="+stat.completeEswn+",pos="+pos);//~9705I~
        }                                                          //~9705I~
    }                                                              //~9705I~
//    //****************************************                   //~0302R~
//    public void setErrCompletion()                               //~0302R~
//    {                                                            //~0302R~
//        if (Dump.Y) Dump.println("Complete.setErrCompletion swsErrLooser="+Arrays.toString(swsErrLooser));//~0302R~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~0302R~
//        {                                                        //~0302R~
//            Complete.Status stat=statusS[ii];                    //~0302R~
//            if (stat==null)                                      //~0302R~
//                continue;                                        //~0302R~
//            int eswn=stat.completeEswn;                          //~0302R~
//            stat.setInvalid(swsErrLooser[eswn]);                 //~0302R~
//            if (Dump.Y) Dump.println("Complete.setErrCompleteion stat="+stat.toString());//~0302R~
//        }                                                        //~0302R~
//    }                                                            //~0302R~
    //****************************************                     //~9315I~
    //*dismiss TReqDlg at CompleteDlg open                         //~9315I~
    //****************************************                     //~9315I~
    public void dismissAllReqDlg()                                 //~9315I~
    {                                                              //~9315I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9315I~
        {                                                          //~9315I~
        	Complete.Status stat=statusS[ii];                      //~9315I~
	        if (stat==null)                                        //~9315I~
            	continue;                                          //~9315I~
            CompReqDlg dlg=stat.compReqDlg;                        //~9315I~
	        if (dlg==null)                                         //~9315I~
            	continue;                                          //~9315I~
        	if (Dump.Y) Dump.println("Complete.dismissAllReqDlg ii="+ii+",dlg="+dlg.toString());//~9315I~
            dlg.dismiss();                                         //~9315I~
        }                                                          //~9315I~
    }                                                              //~9315I~
//*******************************************************************************//~9B29I~
    public void setDrawn3R(boolean PswDrawn3R)                     //~9B29I~
    {                                                              //~9B29I~
        if (Dump.Y) Dump.println("Complete.setDrawn3R parm="+PswDrawn3R);//~9B29I~
        swDrawn3R=PswDrawn3R;                                      //~9B29I~
    }                                                              //~9B29I~
    public boolean getDrawn3R()                                    //~9B29I~
    {                                                              //~9B29I~
        if (Dump.Y) Dump.println("Complete.getDrawn3R parm="+swDrawn3R);//~9B29I~
        return swDrawn3R;                                          //~9B29I~
    }                                                              //~9B29I~
//*******************************************************************************//~v@@@I~
//*******************************************************************************//~v@@@I~
//*******************************************************************************//~v@@@I~
    public class Status                                                   //~v@@@R~
    {                                                              //~v@@@I~
        public int completeType;                                   //~v@@@R~
        public int completeEswn;                                   //~v@@@R~
        public int completeEswnLooser;                             //~v@@@R~
        public TileData completeTD;                                //~v@@@R~
        public TileData completeKanTakenTD;	//null or TD of rinshan river case of rinshan kaihou//~v@@@R~
        public int[] ammount=new int[CALC_AMT_MAXCTR];//   =6; //~9310R~
        public int [] replyOK=new int[PLAYERS];                    //~v@@@I~
        private int ctrNG,ctrReply,ctrResponsible;                 //~v@@@I~
        public boolean swOK,swReplyAll,swNG,swInvalid,swInvalidByEswn;                 //~v@@@R~//~9319R~//~9409R~
        public boolean swErr;	//chombo                           //~9319I~
        public  boolean swTake;                                    //~v@@@R~
	    public CompReqDlg compReqDlg;                              //~v@@@I~
	    private boolean swSetAmmount;                              //~v@@@R~
	    public int supporterEswn;                                  //~va60R~
        //*******************************************************************************************//~v@@@I~
        public Status(int PcompType,int Peswn,int PeswnLooser,TileData Ptd,TileData PtdCompKanTake)//~v@@@R~
        {                                                          //~v@@@I~
	    	completeEswn=Peswn; completeType=PcompType; completeTD=Ptd;//~v@@@I~
	    	completeKanTakenTD=PtdCompKanTake;                      //~v@@@I~
            completeEswnLooser=PeswnLooser;                        //~v@@@I~
//          supporterEswn=Accounts.mapDummyByEswn(completeEswn);   //~va60R~
            if (AG.aAccounts.isDummyByCurrentEswn(completeEswn))	//robot//~va60I~
//  			supporterEswn=Accounts.mapDummyByEswn(ESWN_E);     //~va60R~
		        supporterEswn=AG.aAccounts.getRealDealerEswnForRobotEswn(completeEswn);//dealer when robot ron//~va60I~
            else                                                   //~va60I~
            	supporterEswn=completeEswn;                        //~va60I~
	        swTake=(completeType & (COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0;//~v@@@I~
            Arrays.fill(replyOK,COMPREPLY_BEFORESEND);             //~v@@@R~
            if (Dump.Y) Dump.println("Complete.Status constructor "+toString());//~v@@@I~//~va60R~
        }                                                          //~v@@@I~
        //****************************************                 //~v@@@I~
        public String toString()                                   //~v@@@I~
        {                                                          //~v@@@I~
            return "Complete.Status type="+completeType+",completeEswn="+completeEswn+",eswnLooser="+completeEswnLooser+",supporterEswn="+supporterEswn+",swReplyAll="+swReplyAll+",swErr="+swErr+",swInvalid="+swInvalid+",swInvalidByEswn="+swInvalidByEswn+",td:"+TileData.toString(completeTD)+",kantaken="+TileData.toString(completeKanTakenTD)+",ammount="+Utils.toString(ammount);//~v@@@R~//~0227R~//~va60R~
        }                                                          //~v@@@I~
        //****************************************                 //~v@@@I~
        public String toSendText()                                 //~v@@@I~
        {                                                          //~v@@@I~
            String txt=completeType+MSG_SEPAPP+completeEswn+MSG_SEPAPP+completeEswnLooser+MSG_SEPAPP2+//~v@@@I~
            			TileData.toSendText(completeTD)+MSG_SEPAPP2+  //~v@@@I~
                        ((completeKanTakenTD==null)                  //~v@@@I~
                        	? "0"                                  //~v@@@I~
                            : "1" +MSG_SEPAPP2+TileData.toSendText(completeKanTakenTD));//~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.toSendText txt="+txt);//~v@@@I~
            return txt;
        }                                                          //~v@@@I~
//        //****************************************                 //~v@@@I~//~va11R~
//        public Status toStatus(int[] PintParm,int Ppos )           //~v@@@I~//~va11R~
//        {                                                          //~v@@@I~//~va11R~
//            int pos=Ppos;                                          //~v@@@I~//~va11R~
//            int compType  =PintParm[pos++];                        //~v@@@I~//~va11R~
//            int eswn      =PintParm[pos++];                        //~v@@@I~//~va11R~
//            int eswnLooser=PintParm[pos++];                        //~v@@@I~//~va11R~
//            TileData td=new TileData(true/*swEswnToPlayer*/,PintParm,pos);//~v@@@I~//~va11R~
//            pos+=PARMPOS_CTRFORTD;                                 //~v@@@I~//~va11R~
//            int nullkantd         =PintParm[pos++];                //~v@@@I~//~va11R~
//            TileData tdCompKanTake=null;                           //~v@@@I~//~va11R~
//            if (nullkantd==1)                                      //~v@@@I~//~va11R~
//                tdCompKanTake=new TileData(true/*swEswnToPlayer*/,PintParm,pos);//~v@@@I~//~va11R~
//            Status s=new Status(compType,eswn,eswnLooser,td,tdCompKanTake);//~v@@@I~//~va11R~
//            if (Dump.Y) Dump.println("Complete.Status.toStatus status="+s.toString());//~v@@@I~//~va11R~
//            return s;                                            //~va11R~
//        }                                                          //~v@@@I~//~va11R~
        //****************************************                 //~v@@@I~
        public void setAmmount(int[] Pamt)                         //~v@@@I~
        {                                                          //~v@@@I~
        	swSetAmmount=true;                                     //~v@@@I~
        	ammount=Pamt;                                         //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.setAmmount completeEswn="+completeEswn+",amt="+Arrays.toString(Pamt));//~v@@@I~//~va60R~
        }                                                          //~v@@@I~
        //****************************************                 //~v@@@I~
        public boolean isShowable()                                //~v@@@I~
        {                                                          //~v@@@I~
        	int currentEswn=AG.aAccounts.getCurrentEswn();         //~v@@@I~
        	boolean rc=completeEswn==currentEswn||swSetAmmount;    //~v@@@I~
            if (swInvalid)                                         //~9402I~
            	rc=false;                                          //~9402I~
            if (Dump.Y) Dump.println("Complete.Status.isShowable rc="+rc+",swInvalid="+swInvalid+",completeEswn="+completeEswn+",swSetAmmount="+swSetAmmount);//~v@@@I~//~9402R~
            return rc;                                             //~v@@@I~
        }                                                          //~v@@@I~
        //***********************************************************//~va60I~
        //*show robot completion by next human player              //~va60I~
        //***********************************************************//~va60I~
        public boolean isShowableRobot(int PcurEswn)               //~va60I~
        {                                                          //~va60I~
            boolean rc=supporterEswn==PcurEswn || swSetAmmount;    //~va60R~
            if (swInvalid)                                         //~va60I~
            	rc=false;                                          //~va60I~
            if (Dump.Y) Dump.println("Complete.Status.isShowableRobot PcurEswn="+PcurEswn+",supporterEswn="+supporterEswn+",rc="+rc+",swInvalid="+swInvalid+",completeEswn="+completeEswn+",swSetAmmount="+swSetAmmount);//~va60R~
            return rc;                                             //~va60I~
        }                                                          //~va60I~
        //*************************************************************************//~v@@@I~
        public boolean setOK(int Peswn,boolean PswOK)              //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.setOK replayOK="+Arrays.toString(replyOK));//~va73I~
            if (Peswn==-1)	//for TEST TODO                        //~v@@@I~
            	swOK=PswOK;                                         //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
        		replyOK[Peswn]=PswOK ? COMPREPLY_OK : COMPREPLY_NG;//~v@@@I~
	        	swOK=chkOK();                                      //~v@@@R~
            }                                                      //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.setOK rc="+swOK+",eswn="+Peswn+",PswOK="+PswOK+",replayOK="+Arrays.toString(replyOK));//~v@@@I~
            return swOK;                                           //~v@@@I~
        }                                                          //~v@@@I~
        //*************************************************************************//~v@@@I~
        //*set chombo,not calculated to ammount                    //~v@@@I~
        //*************************************************************************//~v@@@I~
        public boolean setErr(boolean PswErr)                      //~v@@@I~
        {                                                          //~v@@@I~
        	swErr=PswErr;                                          //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.setErr swErr="+PswErr);//~v@@@I~//~9B10R~
            return swErr;                                          //~v@@@I~
        }                                                          //~v@@@I~
        //*************************************************************************//~v@@@I~
        public boolean setInvalid(boolean PswInvalid)              //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.setInvalid old swInvalid="+swInvalid+",new="+PswInvalid+",completeEswn="+completeEswn);//~v@@@I~//~9409R~//~0227I~
        	swInvalid=PswInvalid;                                 //~v@@@I~
            return swInvalid;                                          //~v@@@I~//~9402R~
        }                                                          //~v@@@I~
        //*************************************************************************//~9409I~
        //*invalid by ESWN sequence                                //~9519I~
        //*************************************************************************//~9519I~
        public boolean setInvalidByEswn(boolean PswInvalid)        //~9409I~
        {                                                          //~9409I~
        	swInvalidByEswn=PswInvalid;                            //~9409I~
            if (Dump.Y) Dump.println("Complete.Status.setInvalidByEswn swInvalid="+swInvalid+",swInvalidByEswn="+swInvalidByEswn+",completeEswn="+completeEswn);//~9409R~//~9705R~
//          return swInvalid;                                      //~9409I~//~9705R~
            return swInvalidByEswn;                                //~9705I~
        }                                                          //~9409I~
        //*************************************************************************//~9519I~
        public boolean isValidCompleteion()                        //~9519I~
        {                                                          //~9519I~
//      	boolean rc=!swErr && !swInvalidByEswn;                 //~9519I~//~9705R~
        	boolean rc=!swErr && !swInvalidByEswn && !swInvalid;   //~9705I~
            if (Dump.Y) Dump.println("Complete.isValidCompleteion rc="+rc+",swErr="+swErr+" swInvalidByEswn="+swInvalidByEswn);//~9519I~
            return rc;                                             //~9519I~
        }                                                          //~9519I~
        //*************************************************************************//~v@@@I~
        public void requestSent()                                  //~v@@@R~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.requestSent before reset ="+Arrays.toString(replyOK));//~v@@@R~
        	Arrays.fill(replyOK,COMPREPLY_NORESP);                 //~v@@@I~
        }                                                          //~v@@@I~
        //*************************************************************************//~v@@@I~
        public void setDlg(CompReqDlg Pdlg)                        //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.setDlg dlg="+Pdlg.toString());//~v@@@I~
        	compReqDlg=Pdlg;                                       //~v@@@I~
        }                                                          //~v@@@I~
        //*************************************************************************//~v@@@I~
        public void dismissDlg(boolean PswDismissBeforeNew)                                   //~v@@@I~//~9322R~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.dismissDlg swDismissBeforeNew="+PswDismissBeforeNew);//~v@@@I~//~9322R~
            if (!PswDismissBeforeNew)	//avoid timing miss        //~9322I~
	        	compReqDlg=null;                                       //~v@@@I~//~9322R~
        }                                                          //~v@@@I~
        //*************************************************************************//~v@@@I~
        public boolean chkOK()                                      //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.chkOK completeEswn="+completeEswn+",replyOK="+Utils.toString(replyOK));      //~v@@@I~//~va73R~
            ctrReply=0;                                            //~v@@@I~
            ctrNG=0;                                               //~v@@@I~
            ctrResponsible=0;                                      //~v@@@I~
            for (int ii=0;ii<PLAYERS;ii++)                         //~v@@@I~
            {                                                      //~v@@@I~
              if (AG.swTrainingMode)                               //~va73I~
              {                                                    //~va73I~
            		if (!AG.aAccounts.isDummyByCurrentEswn(ii))    //~va73I~
                    {                                              //~va73I~
                    	ctrResponsible++;                          //~va73I~
                    	int reply=replyOK[ii];                     //~va73I~
                    	if (reply==COMPREPLY_OK)                   //~va73I~
                        {                                          //~va73I~
                    		ctrReply++;                            //~va73I~
                        }                                          //~va73I~
                        else                                       //~va73I~
                    	if (reply==COMPREPLY_NG)                   //~va73I~
                        {                                          //~va73I~
                    		ctrReply++;                            //~va73I~
	                    	ctrNG++;                               //~va73I~
                        }                                          //~va73I~
                        else                                       //~va73I~
		                if (ii==completeEswn)                      //~va73I~
                    		replyOK[ii]=COMPREPLY_YOU;             //~va73I~
                    }                                              //~va73I~
                    else                                           //~va73I~
	                    replyOK[ii]=COMPREPLY_ROBOT;               //~va73I~
              }                                                    //~va73I~
              else                                                 //~va73I~
              {                                                    //~va73I~
                if (ii!=completeEswn)                              //~v@@@I~
                {                                                  //~v@@@I~
            		if (!AG.aAccounts.isDummyByCurrentEswn(ii))                 //~v@@@I~
                    {                                              //~v@@@I~
                        if (ii==supporterEswn)                      //~va60I~
                        {                                          //~va60I~
      	              		if (replyOK[ii]!=COMPREPLY_OK)         //~va60I~
                            {                                      //~va60I~
//                  			ctrReply++;	//for robot,RealDealer send CompReqDlg,assume replyed OK//~va60R~//+vakSR~
      	              			replyOK[ii]=COMPREPLY_OK;          //~va60R~
                            }                                      //~va60I~
                        }                                          //~va60I~
                    	ctrResponsible++;                          //~v@@@I~
                    	int reply=replyOK[ii];                     //~v@@@I~
                    	if (reply==COMPREPLY_OK)                   //~v@@@R~
                        {                                          //~v@@@I~
                    		ctrReply++;                            //~v@@@I~
                        }                                          //~v@@@I~
                        else                                       //~v@@@I~
                    	if (reply==COMPREPLY_NG)                   //~v@@@I~
                        {                                          //~v@@@I~
                    		ctrReply++;                            //~v@@@I~
	                    	ctrNG++;                               //~v@@@I~
                        }                                          //~v@@@I~
                    }                                              //~v@@@I~
                    else                                           //~v@@@I~
	                    replyOK[ii]=COMPREPLY_ROBOT;               //~v@@@I~
                }                                                  //~v@@@I~
                else                                               //~v@@@I~
                    replyOK[ii]=COMPREPLY_YOU;                     //~v@@@I~
              }//!trainingmode                                     //~va73I~
            }                                                      //~v@@@I~
            swReplyAll=ctrResponsible==ctrReply;                   //~v@@@I~
            boolean rc=(swReplyAll && ctrNG==0);                  //~v@@@R~
            swNG=swReplyAll && !rc;                               //~v@@@I~
            if (Dump.Y) Dump.println("Complete.Status.chkOK rc="+rc+",swNG="+swNG+",swReplyAll="+swReplyAll+",ctrResponsible="+ctrResponsible+",ctrReply="+ctrReply+",ctrNG="+ctrNG+",supporterEswn="+supporterEswn);//~v@@@I~//~9315R~//~9320R~//~va60R~//~va73R~
            if (Dump.Y) Dump.println("Complete.Status.chkOK exit completeEswn="+completeEswn+",replyOK="+Utils.toString(replyOK));//~va73R~
            return rc;                                             //~v@@@I~
        }                                                          //~v@@@I~
        //*************************************************************************//~v@@@I~
//      public String getAmmountMsgText()                          //~v@@@I~//~va60R~
        public String getAmmountMsgText(int PcompleteEswn)         //~va60I~
        {                                                          //~v@@@I~
        	StringBuffer sb=new StringBuffer();                    //~v@@@I~
//          sb.append(Accounts.getCurrentEswn());                   //~v@@@R~//~va60R~
            sb.append(PcompleteEswn);                              //~va60I~
            int ii=0;                                              //~v@@@I~
            for (;ii<CALC_AMT_MAXCTR;ii++)                         //~v@@@R~
            	sb.append(MSG_SEPAPP3+ammount[ii]);                //~v@@@R~
            String s=sb.toString();
            if (Dump.Y) Dump.println("Complete.getAmmountMsgText="+s);//~v@@@I~
            return s;                                              //~v@@@I~
        }                                                          //~v@@@I~
    } //class Status                                                             //~v@@@I~//~9501R~
    //*******************************************************************//~9420M~
	public  void setAmtError(boolean[] PswsErrLooser,int[] PamtError)//~9420R~
    {                                                              //~9420M~
        if (Dump.Y) Dump.println("Complete.setAmtError before PamtErr="+Arrays.toString(PamtError));//~9420R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9420M~
        {                                                          //~9420M~
            amtsError[ii]=new int[PLAYERS]; //[gainer][payer]      //~9420M~
            amtsErrorByLooser[ii]=new int[PLAYERS]; //[looser][gainer]//~9420M~
        }                                                          //~9420M~
        if (Dump.Y) Dump.println("Complete.setAmtError swsErrLooser="+Arrays.toString(PswsErrLooser));//~9420R~
        Arrays.fill(PamtError,0);                                  //~9424I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9420M~
        {                                                          //~9420M~
            if (PswsErrLooser[ii])    //ii erreswn                 //~9420I~
            {                                                      //~9420M~
                int a=ii==0 ? POINT_RANKM_DEALER :POINT_RANKM;     //~9420M~
                for (int jj=0;jj<PLAYERS;jj++)                     //~9420M~
                {                                                  //~9420M~
                    if (jj==ii)                                    //~9420M~
                    {                                              //~9420M~
                        PamtError[jj]+=-a;                         //~9420R~
                        amtsErrorByLooser[ii][jj]=-a;              //~9420M~
                        amtsError[jj][ii]=a;                       //~9420M~
                    }                                              //~9420M~
                    else                                           //~9420M~
                        if (ii==0)                                 //~9420M~
                        {                                          //~9420M~
                            PamtError[jj]+=a/3;                    //~9420R~
                            amtsErrorByLooser[ii][jj]=a/3;         //~9420M~
                            amtsError[jj][ii]=-a/3;                //~9420M~
                        }                                          //~9420M~
                        else                                       //~9420M~
                        {                                          //~9420M~
                            if (jj==0)                             //~9420M~
                            {                                      //~9420M~
                                PamtError[jj]+=a/2;                //~9420R~
                                amtsErrorByLooser[ii][jj]=a/2;     //~9420M~
                                amtsError[jj][ii]-=a/2;            //~9420M~
                            }                                      //~9420M~
                            else                                   //~9420M~
                            {                                      //~9420M~
                                PamtError[jj]+=a/4;                //~9420R~
                                amtsErrorByLooser[ii][jj]=a/4;     //~9420M~
                                amtsError[jj][ii]=-a/4;            //~9420M~
                            }                                      //~9420M~
                        }                                          //~9420M~
                }                                                  //~9420M~
                if (Dump.Y) Dump.println("Complete.setAmtError ii="+ii+",amtsError="+Arrays.toString(amtsError[ii]));//~9420R~
                if (Dump.Y) Dump.println("Complete.setAmtError ii="+ii+",amtsErrorByLooser="+Arrays.toString(amtsErrorByLooser[ii]));//~9420R~
            }                                                      //~9420M~
        }                                                          //~9420M~
        if (Dump.Y) Dump.println("Complete.setAmtError after PamtErr="+Arrays.toString(PamtError));//~9420R~
    }                                                              //~9420M~
    //*******************************************************************//~9501I~
    public void resetGrilledBird(int PendgameType)                 //~9501I~
    {                                                              //~9501I~
        if (Dump.Y) Dump.println("CompleteDlg.resetGrilledBird endgameType="+PendgameType);//~9501I~
        if (sortedStatusS!=null)                                   //~9501I~
        {                                                          //~9501I~
            for (Complete.Status stat:sortedStatusS)               //~9501R~
            {                                                      //~9501R~
                if (Dump.Y) Dump.println("CompleteDlg.resetGrilledBird completeEswn="+stat.completeEswn);//~9501R~//~9519R~
//              if (!stat.swErr)                                   //~9519R~
			    if (stat.isValidCompleteion())                     //~9519I~
                {                                                  //~9501R~
                    int eswn=stat.completeEswn;                    //~9501R~
                    int idx=AG.aAccounts.currentEswnToMember(eswn);//~9501R~
                    AG.aAccounts.accounts[idx].setGrilled(false/*resetGrilled*/);//~9501R~
                }                                                  //~9501R~
            }                                                      //~9501R~
        }                                                          //~9501I~
        if (Dump.Y) Dump.println("CompleteDlg.resetGrilledBird DrawnMangan swsMangan="+Arrays.toString(swsMangan));//~9501R~
//      if (swsMangan!=null && RuleSetting.isDrawnManganDropBird())//~9501R~//~9505R~
        if (swsMangan!=null && RuleSettingYaku.isDrawnManganAsRon())   //~9505I~
        {                                                          //~9501I~
            for (int eswn=0;eswn<PLAYERS;eswn++)                   //~9501R~
            {                                                      //~9501R~
                if (!swsMangan[eswn])                              //~9501R~
                    continue;                                      //~9501R~
                int idx=AG.aAccounts.currentEswnToMember(eswn);    //~9501R~
                AG.aAccounts.accounts[idx].setGrilled(false);      //~9501R~
                if (Dump.Y) Dump.println("CompleteDlg.resetGrilledBird DrawnMangan reset actName="+AG.aAccounts.accounts[idx].name);//~9501R~
            }                                                      //~9501R~
        }                                                          //~9501I~
    }                                                              //~9501I~
    //*******************************************************************//~9521I~
    public boolean isCompletedRon()                                //~9521I~
    {                                                              //~9521I~
        boolean rc=lastEndGameType==EGDR_NORMAL || lastEndGameType==EGDR_MANGAN_RON;//~9521I~
        if (Dump.Y) Dump.println("CompleteDlg.isCompletedRon lastEndGameType="+lastEndGameType+",rc="+rc);//~9521I~
        return rc;                                                 //~9521I~
    }                                                              //~9521I~
    //*******************************************************************//~9504I~
    //*output intsCompType;                                        //~9521I~
    //*******************************************************************//~9521I~
    public boolean isCompletedDealerRon()                          //~9504R~
    {                                                              //~9504I~
        boolean rc=false;                                          //~9504R~
        if (lastEndGameType==EGDR_NORMAL && sortedStatusS!=null)   //~9504I~
        {                                                          //~9504I~
            for (Complete.Status stat:sortedStatusS)               //~9504I~
            {                                                      //~9504I~
                if (Dump.Y) Dump.println("CompleteDlg.isCompletedDealerRon swErr="+stat.swErr+",swInvalidByEswn="+stat.swInvalidByEswn+",completeEswn="+stat.completeEswn);//~9504I~//~9519R~
//              if (!stat.swErr)                                   //~9504I~//~9519R~
			    if (stat.isValidCompleteion())                     //~9519I~
                {                                                  //~9504I~
                	int pos=AG.aAccounts.currentEswnToPosition(stat.completeEswn);//~9521I~
    				intsCompType[pos]=EGDR_NORMAL;                 //~9521I~
                    if (stat.completeEswn==ESWN_E)                  //~9504I~
                    {                                              //~9504I~
                    	rc=true;                                   //~9504I~
                    }                                              //~9504I~
                }                                                  //~9504I~
            }                                                      //~9504I~
        }                                                          //~9504I~
        else                                                       //~9506I~
        if (lastEndGameType==EGDR_MANGAN_RON)                      //~9506I~
        {                                                          //~9506I~
        	if (swsMangan[0])                                      //~9506I~
	        	rc=true;                                           //~9506R~
            for (int ii=0;ii<PLAYERS;ii++)                         //~9521I~
            {                                                      //~9521I~
	        	if (swsMangan[ii])                                 //~9521I~
                {                                                  //~9521I~
                	int pos=AG.aAccounts.currentEswnToPosition(ii);//~9521I~
    				intsCompType[pos]=EGDR_MANGAN_RON;             //~9521I~
                }                                                  //~9521I~
            }                                                      //~9521I~
        }                                                          //~9506I~
                                                                   //~9506I~
        if (Dump.Y) Dump.println("CompleteDlg.isCompletedDealerRon lastEndGameType="+lastEndGameType+",rc="+rc+",intsCompType="+Arrays.toString(intsCompType));//~9504I~//~9520R~//~9521R~
        return rc;                                                 //~9504I~
    }                                                              //~9504I~
    //*******************************************************************//~9709I~
    public boolean isCompletedDealerPendingCont()                  //~9709R~
    {                                                              //~9709I~
    	boolean rc=isCompletedDealerPending();                     //~9709I~
        rc=rc && RuleSetting.isPendingCont();                      //~9709I~
        if (Dump.Y) Dump.println("CompleteDlg.isCompletedDealerPendingCont rc="+rc);//~9709I~
        return rc;                                                 //~9709I~
    }                                                              //~9709I~
    //*******************************************************************//~9504I~
    private boolean isCompletedDealerPending()                      //~9504I~//~9709R~
    {                                                              //~9504I~
        boolean rc=false;                                          //~9504I~
        if (lastEndGameType!=EGDR_NORMAL && swsPending!=null)      //~9504R~
        {                                                          //~9504I~
            for (int eswn=0;eswn<PLAYERS;eswn++)                   //~9521I~
            {                                                      //~9521I~
                int pos=AG.aAccounts.currentEswnToPosition(eswn);  //~9521I~
	            if (swsPending[eswn])                              //~9521I~
                {                                                  //~9521I~
    				intsCompType[pos]=EGDR_PENDING;                //~9521I~
		            if (eswn==ESWN_E)                              //~9521I~
                    	rc=true;                                   //~9521I~
                }                                                  //~9521I~
                if (swsReach!=null && swsReach[eswn])              //~9521I~
                {                                                  //~9521I~
			        if (Dump.Y) Dump.println("CompleteDlg.isCompletedDealerPending swsReach="+Arrays.toString(swsReach));//~9521I~
    				intsCompType[pos]=EGDR_PENDING_REACH;          //~9521I~
		            if (eswn==ESWN_E)                              //~9521I~
                    	rc=true;                                   //~9521I~
                }                                                  //~9521I~
            }                                                      //~9521I~
	        if (Dump.Y) Dump.println("CompleteDlg.isCompletedDealerPending swsPending="+Arrays.toString(swsPending));//~9504I~
        }                                                          //~9504I~
        if (Dump.Y) Dump.println("CompleteDlg.isCompletedDealerPending endgameType="+lastEndGameType+",rc="+rc+",intsCompType="+Arrays.toString(intsCompType));//~9504R~//~9521R~
        return rc;                                                 //~9504I~
    }                                                              //~9504I~
    //*******************************************************************//~9603I~
	public void setDrawnDelayLastTimeout()                         //~9603I~
    {                                                              //~9603I~
        if (Dump.Y) Dump.println("CompleteDlg.setDrawnDelayLastTimeout old="+swDrawnDelayLastTimeout);//~9603I~
    	swDrawnDelayLastTimeout=true;                              //~9603I~
    }                                                              //~9603I~
    //*******************************************************************//~9603I~
	public boolean isDrawnDelayLastTimeout()                          //~9603I~
    {                                                              //~9603I~
        if (Dump.Y) Dump.println("CompleteDlg.isDrawnDelayLastTimeout rc="+swDrawnDelayLastTimeout);//~9603I~
    	return swDrawnDelayLastTimeout;                            //~9603I~
    }                                                              //~9603I~
//    //*******************************************************************//~9504I~//~9703R~
//    public boolean isCompletedByHW()                               //~9504I~//~9703R~
//    {                                                              //~9504I~//~9703R~
//        boolean rc=lastEndGameType==EGDR_DRAWN_HW;                 //~9504I~//~9703R~
//        if (Dump.Y) Dump.println("CompleteDlg.isCompletedByHW  endgameType="+lastEndGameType+",rc="+rc);//~9504I~//~9703R~
//        return rc;                                                 //~9504I~//~9703R~
//    }                                                              //~9504I~//~9703R~
    //*******************************************************************//~9504I~
    public boolean isCompletedByDrawnMangan()                      //~9504I~
    {                                                              //~9504I~
//      boolean rc=(lastEndGameType==EGDR_MANGAN || lastEndGameType==EGDR_MANGAN_PENDING || lastEndGameType==EGDR_MANGAN_PENDING_BYSETTING);//~9504I~//~9506R~
        boolean rc=(lastEndGameType==EGDR_MANGAN || lastEndGameType==EGDR_MANGAN_PENDING);//~9506I~
        if (Dump.Y) Dump.println("CompleteDlg.isCompletedByDrawnMangan  endgameType="+lastEndGameType+",rc="+rc);//~9504I~
        return rc;                                                 //~9504I~
    }                                                              //~9504I~
    //**************************************************           //~9319I~//~9504I~
    public boolean isCompletedDealerTop()                                       //~9319I~//~9504R~
    {                                                              //~9319I~//~9504I~
    	int[] score=AG.aAccounts.score;                            //~9504I~
        int dealerScore=score[ESWN_N];                             //~9504I~
        boolean rc=true;                                           //~9504I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9319I~//~9504I~
        {                                                          //~9319I~//~9504I~
            if (dealerScore<score[ii])                                //~9319I~//~9504I~
            {                                                      //~9504I~
            	rc=false;                                          //~9504I~
                break;                                             //~9504I~
            }                                                      //~9504I~
        }                                                          //~9319I~//~9504I~
        if (Dump.Y) Dump.println("Complete.isCompletedDealerTop rc="+rc+",score="+Arrays.toString(score));//~9504I~
        return rc;                                                 //~9504I~
    }                                                              //~9319I~//~9504I~
    //**************************************************           //~9504I~
    public boolean isCompletedAllMinus()                           //~9504I~
    {                                                              //~9504I~
    	int[] score=AG.aAccounts.score;                            //~9504I~
        boolean rc=true;                                           //~9504I~
        int debt=RuleSetting.getDebt();                            //~9504I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9504I~
        {                                                          //~9504I~
            if (score[ii]>=debt)                                   //~9504I~
            {                                                      //~9504I~
            	rc=false;                                          //~9504I~
                break;                                             //~9504I~
            }                                                      //~9504I~
        }                                                          //~9504I~
        if (Dump.Y) Dump.println("Complete.isCompletedAllMinus rc="+rc+",score="+Arrays.toString(score));//~9504I~
        return rc;                                                 //~9504I~
    }                                                              //~9504I~
    //**************************************************           //~9521I~
    public void savePendingReach()                                 //~9521I~
    {                                                              //~9521I~
	    boolean swRon=isCompletedRon();                            //~9521I~
        if (Dump.Y) Dump.println("Complete.savePendingReach swRon="+swRon);//~9522I~
        AG.aAccounts.savePendingReach(swRon,swsReach);             //~9522I~
//        if (Dump.Y) Dump.println("Complete.savePendingReach swRon="+swRon+",before ctrPendingReach="+Arrays.toString(intsCtrPendingReach));//~9521I~//~9522R~
//        if (swRon)                                                 //~9521I~//~9522R~
//            Arrays.fill(intsCtrPendingReach,0);                    //~9521I~//~9522R~
//        else                                                       //~9521I~//~9522R~
//        {                                                          //~9521I~//~9522R~
//            if (swsReach!=null)                                    //~9521I~//~9522R~
//            {                                                      //~9521I~//~9522R~
//                if (Dump.Y) Dump.println("Complete.savePendingReach swsReach="+Arrays.toString(swsReach));//~9521I~//~9522R~
//                for (int eswn=0;eswn<PLAYERS;eswn++)               //~9521I~//~9522R~
//                {                                                  //~9521I~//~9522R~
//                    if (!swsReach[eswn])                           //~9521I~//~9522R~
//                        continue;                                  //~9521I~//~9522R~
//                    int pos=AG.aAccounts.currentEswnToPosition(eswn);//~9521I~//~9522R~
//                    if (Dump.Y) Dump.println("Complete.savePendingReach reach eswn="+eswn+",pos="+pos);//~9521I~//~9522R~
//                    intsCtrPendingReach[pos]++;                    //~9521I~//~9522R~
//                }                                                  //~9521I~//~9522R~
//            }                                                      //~9521I~//~9522R~
//        }                                                          //~9521I~//~9522R~
//        if (Dump.Y) Dump.println("Complete.savePendingReach after ctrPendingReach="+Arrays.toString(intsCtrPendingReach));//~9521I~//~9522R~
    }                                                              //~9521I~
}//class Complete                                                 //~dataR~//~@@@@R~//~v@@@R~
