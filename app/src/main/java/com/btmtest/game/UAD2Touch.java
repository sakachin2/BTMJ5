//*CID://+DATER~:                             update#=  245;       //~1Af6R~//~v@@@R~//~9B28R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.game;                                         //~v@@@R~

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.dialog.RuleSetting;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UADelayed2.*;
import static com.btmtest.game.gv.GMsg.*;

public class UAD2Touch                                             //~v@@@R~
{                                                                  //~2C29R~
    private static final int COLOR_BLOCKING= Color.argb(0xff,0xff,0x66,0x00);   //Light's orange//~v@@@R~
    private static final int COLOR_MORE= Color.argb(0xff,0xff,0xff,0x00);   //Light's orange//~v@@@I~
    public static final int COLOR_NORMAL=-1;   //gc.btnbackground  //~v@@@R~
	public static final int BTN_STATUS_ENABLE_CANCEL=1;            //~v@@@R~
	public static final int BTN_STATUS_DISABLE_CANCEL=2;           //~v@@@R~
                                                                   //~9B28I~
    private UADelayed2 UADL;                                       //~v@@@I~
//  private int actionToCancel;                                    //~v@@@I~//~9B28R~
//  private int actionBlocking;		//btn status changed color     //~v@@@I~//~9B28R~
    private int[] blockerAction=new int[PLAYERS]; //actionID by current eswn//~v@@@I~
    private int[] blockerEswnQ=new int[PLAYERS];//blocker eswn by blockerCtr sequence//~v@@@I~//~9B28R~
    private int[] blockerFlagQ=new int[PLAYERS];//for multiRon availability//~9B28I~
    private int blockerCtr;                                        //~v@@@I~
    private int yourAction;                                        //~9B28I~
    private int yourActionBlocking;                                //~9B28I~
    private int yourActionBtn;                                     //~9B28I~
	public boolean swSettingMultiRon;                             //~9B29R~//~9C11R~
	private boolean swDelayedRon;                                  //~9B29I~
                                                                   //~9B29I~
    private int yourStatus;                                        //~9B28I~
	private  final static int YS_BLOCKED_TOP=30;                   //~9B28I~
	private  final static int YS_BLOCKED_NEXT=31;                  //~9B28I~
                                                                   //~9B29I~
	private  int statusMultiRon;                               //~9B29I~
	private  final static int MRSTAT_ALL=1;                        //~9B29I~
	private  final static int MRSTAT_ESWN=2;                       //~9B29I~
	private  final static int MRSTAT_DELAYED=3;                    //~9B29I~
	private  final static int MRSTAT_NOTRON=4;                     //~9C11I~
                                                                   //~9B29I~
//**********************************                               //~v@@@I~
	public UAD2Touch(UADelayed2 Pparent)                           //~v@@@R~
    {                                                              //~v@@@I~
    	AG.aUAD2Touch=this;                                        //~v@@@R~
        UADL=Pparent;                                              //~v@@@I~
    	swSettingMultiRon= RuleSetting.isMultiRon();                       //~9223I~//~9B29I~
    	if (Dump.Y) Dump.println("UAD2Touch.constructor swSettingMultiRon="+swSettingMultiRon);         //~v@@@R~//~9B29I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9B28I~
    private boolean updateYourStatus()                             //~9B28I~
    {                                                              //~9B28I~
    	int eswn=AG.aAccounts.playerToEswn(PLAYER_YOU);            //~9B28I~
        yourAction=blockerAction[eswn];                            //~9B28I~
        int old=yourStatus;                                        //~9B28I~
        if (yourAction==0)	//released                             //~9B28R~
        	yourStatus=0;                                          //~9B28I~
        else	//blocking                                         //~9B28R~
        {                                                          //~9B28I~
            if (blockerEswnQ[0]==eswn)                             //~9B28I~
		    	yourStatus=YS_BLOCKED_TOP;        //1st blocker    //~9B28I~
            else                                                   //~9B28I~
            {                                                      //~9B29I~
            	statusMultiRon=chkMultiRonStatus(yourAction,PLAYER_YOU);//~9B29I~
			    yourStatus=YS_BLOCKED_NEXT;	//2nd blocker          //~9B28I~//~9B29R~
            }                                                      //~9B29I~
        }                                                          //~9B28I~
        boolean rc=yourStatus!=old;                                //~9B28I~
        if (Dump.Y) Dump.println("UAD2Touch.updateYourStatus rc="+rc+",yourAction="+yourAction+",yourStatus="+yourStatus+",blockerAction="+ Arrays.toString(blockerAction)+",rc="+rc+",eswn="+eswn+",blockerCtr="+blockerCtr+",actionEswnQ="+Arrays.toString(blockerEswnQ));//~9B28R~//~9C07R~//~9C09R~
        if (Dump.Y) Dump.println("UAD2Touch.updateYourStatus statusMultiRon="+statusMultiRon);//~9C09I~
        return rc;                                                 //~9B28I~
    }                                                              //~9B28I~
	//*************************************************************************//~9C07I~
    public int getTopBlockerAction(int Pplayer)                    //~9C07I~
    {                                                              //~9C07I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~9C07I~
        int action=blockerAction[eswn];                            //~9C07I~
        if (blockerCtr==0 || blockerEswnQ[0]!=eswn)                //~9C07I~
        	action=0;                                              //~9C07I~
        if (Dump.Y) Dump.println("UAD2Touch.getTopBlockerAction player="+Pplayer+",eswn="+eswn+",action="+action+",blockerAction="+ Arrays.toString(blockerAction)+",blockerCtr="+blockerCtr+",actionQ="+Arrays.toString(blockerEswnQ));//~9C07I~
        return action;                                             //~9C07I~
    }                                                              //~9C07I~
	//*************************************************************************//~9C09I~
    public int getTopBlockerAction()                               //~9C09I~
    {                                                              //~9C09I~
        int action=0;                                              //~9C09I~
        if (blockerCtr!=0)                                         //~9C09I~
        {                                                          //~9C09I~
			int eswn=blockerEswnQ[0];                              //~9C09I~
	        action=blockerAction[eswn];                            //~9C09I~
        }                                                          //~9C09I~
        if (Dump.Y) Dump.println("UAD2Touch.getTopBlockerAction action="+action+",blockerCtr="+blockerCtr);//~9C09I~
        return action;                                             //~9C09I~
    }                                                              //~9C09I~
	//*************************************************************************//~9C07I~
    public Rect getTopBlockerPlayer()                              //~9C07R~
    {                                                              //~9C07I~
        Rect r=null;                                               //~9C07R~
        if (blockerCtr!=0)                                         //~9C07I~
        {                                                          //~9C07I~
			int eswn=blockerEswnQ[0];                              //~9C07I~
            int player=AG.aAccounts.eswnToPlayer(eswn);             //~9C07I~
            int action=blockerAction[eswn];                        //~9C07I~
		    int flag=blockerFlagQ[0];                             //~9C07I~
            r=new Rect(player/*left*/,eswn/*top*/,action/*right*/,flag/*bottom*/);//~9C07R~
        }                                                          //~9C07I~
        if (Dump.Y) Dump.println("UAD2Touch.getTopBlockerEswn player,eswn-action,flag="+ Utils.toString(r)+",blockerAction="+ Arrays.toString(blockerAction)+",blockerCtr="+blockerCtr+",actionQ="+Arrays.toString(blockerEswnQ));//~9C07R~
        return r;                                                  //~9C07R~
    }                                                              //~9C07I~
	//*************************************************************************//~9C10I~
    public Rect getSetTopBlockerPlayer()                           //~9C10I~
    {                                                              //~9C10I~
        Rect r=null;                                               //~9C10I~
        if (blockerCtr!=0)                                         //~9C10I~
        {                                                          //~9C10I~
			int eswn=blockerEswnQ[0];                              //~9C10I~
            int player=AG.aAccounts.eswnToPlayer(eswn);            //~9C10I~
            int action=blockerAction[eswn];                        //~9C10I~
		    int flag=blockerFlagQ[0];                              //~9C10I~
	        if (Dump.Y) Dump.println("UAD2Touch.getTopBlockerEswn flagQ="+Arrays.toString(blockerFlagQ));//~9C10I~
            if ((flag & PARM_FLAG_SCHEDULED)==0) 	//by top cancel(timeout or cancel btn)//~9C10I~
            {                                                      //~9C10I~
			    blockerFlagQ[0]|=PARM_FLAG_SCHEDULED;             //~9C10I~
            	r=new Rect(player/*left*/,eswn/*top*/,action/*right*/,flag/*bottom*/);//~9C10I~
            }                                                      //~9C10I~
        }                                                          //~9C10I~
        if (Dump.Y) Dump.println("UAD2Touch.getTopBlockerEswn player,eswn-action,flag="+ Utils.toString(r)+",blockerAction="+ Arrays.toString(blockerAction)+",blockerCtr="+blockerCtr+",actionQ="+Arrays.toString(blockerEswnQ));//~9C10I~
        return r;                                                  //~9C10I~
    }                                                              //~9C10I~
	//*************************************************************************//~v@@@I~
	//*Queueing multiple blocked request                           //~9C04I~
	//*************************************************************************//~9C04I~
//  public void stopAuto2Touch(int PactionID,int Pplayer)          //~v@@@I~//~9B28R~
    public void stopAuto2Touch(int PactionID,int Pplayer,int Pflag)//~9B28I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAD2Touch.stopAuto2Touch actionID="+PactionID+",Pplayer="+Pplayer+",flag="+Pflag);//~9B28I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@@I~
        if (blockerAction[eswn]==0)                            //~v@@@I~//~9B28R~
        {                                                      //~v@@@I~//~9B28R~
            blockerAction[eswn]=PactionID;                     //~v@@@I~//~9B28R~
            blockerFlagQ[blockerCtr]=Pflag;                        //~9B28I~
            blockerEswnQ[blockerCtr++]=eswn;                 //~v@@@I~//~9B28R~
        }                                                      //~v@@@I~//~9B28R~
        if (Dump.Y) Dump.println("UAD2Touch.stopAuto2Touch eswn="+eswn+",blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction)+",actionQ="+Arrays.toString(blockerEswnQ)+",flagQ="+Arrays.toString(blockerFlagQ));//~v@@@R~//~9B28R~
        boolean rc=updateYourStatus();                             //~9B28I~
        yourActionBlocking=yourAction;                             //~9B28M~
        if (rc)                                                    //~9B28R~
        	updateBtn();                                           //~9B28I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public boolean releaseAuto2Touch(int PactionID,int Pplayer)       //~v@@@I~//~9B28R~
    {                                                              //~v@@@I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@@I~
        if (Dump.Y) Dump.println("UAD2Touch.releaseAuto2Touch actionID="+PactionID+",player="+Pplayer+",eswn="+eswn+",blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction)+",blockerEswnQ="+Arrays.toString(blockerEswnQ));//~9B28R~//~9B29R~
        if (blockerAction[eswn]!=0)                            //~v@@@I~//~9B28R~
        {                                                      //~v@@@I~//~9B28R~
            blockerAction[eswn]=0;                             //~v@@@I~//~9B28R~
            int jj=0;                                          //~v@@@I~//~9B28R~
            for (int ii=0;ii<blockerCtr;ii++)                  //~v@@@I~//~9B28R~
            {                                                  //~v@@@I~//~9B28R~
                if (blockerEswnQ[ii]!=eswn)  //not release   //~v@@@I~//~9B28R~
                {                                              //~v@@@I~//~9B28R~
                    blockerFlagQ[jj]=blockerFlagQ[ii];             //~9B28I~
                    blockerEswnQ[jj++]=blockerEswnQ[ii];   //~v@@@I~//~9B28R~
                }                                              //~v@@@I~//~9B28R~
            }                                                  //~v@@@I~//~9B28R~
            blockerCtr=jj;                                     //~v@@@I~//~9B28R~
        }                                                      //~v@@@I~//~9B28R~
        if (updateYourStatus())                                    //~9B28I~
        	updateBtn();                                           //~9B28I~
        yourActionBlocking=yourAction;	//after released           //~9B28I~
        boolean rc=blockerCtr!=0;                                  //~9B28M~
        if (Dump.Y) Dump.println("UAD2Touch.relaeaseAuto2Touch rc="+rc+",eswn="+eswn+",blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction)+",actionQ="+Arrays.toString(blockerEswnQ));//~9B28I~//~0402R~
        return rc;                                                 //~9B28I~
    }                                                              //~v@@@I~
    //************************************************************ //~v@@@I~
    public void resetAuto2Touch()                               //~v@@@I~//~9B28R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("UAD2Touch.resetAuto2Touch blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction));//~v@@@I~//~9B28R~//~9B29R~
        for (int ii=0;ii<blockerCtr;ii++)                          //~9B28I~
        {                                                          //~9B28I~
            int eswn=blockerEswnQ[ii];                             //~9B28I~
            int player=Accounts.eswnToPlayer(eswn);                //~9B28I~
        	if (Dump.Y) Dump.println("UAD2Touch.resetAuto2Touch eswn="+eswn+",player="+player);//~9B28I~
	        UADL.setWaiterLight(player,false/*swOn*/);             //~9B28I~
            if (ii>1 && player==PLAYER_YOU)                        //~9B29R~
	        	AG.aUserAction.showInfo(GMSGOPT_ANDTOAST,R.string.ActionBlockReleasedByOtherActionDone);//~9B29I~
        }                                                          //~9B28I~
        Arrays.fill(blockerAction,0);   //actionID by eswn     //~v@@@I~//~9B28R~
        Arrays.fill(blockerEswnQ,0); //eswn by clockerCtr   //~v@@@I~//~9B28R~
        blockerCtr=0;                                          //~v@@@I~//~9B28I~
        if (updateYourStatus())                                    //~9B28I~
        	updateBtn();                                           //~9B28I~
        yourActionBlocking=yourAction;	//after released           //~9B28I~
    	if (Dump.Y) Dump.println("UAD2Touch.resetAuto2Touch blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction));//~v@@@I~//~9B28R~//~9B29R~
    }                                                              //~v@@@I~
    //************************************************************ //~9B28I~
    //*by actionDone                                               //~9B28I~
    //*rc:alow next Ron descriver//                                //~9B28I~
    //************************************************************ //~9B28I~
    public boolean resetAuto2Touch(int PactionID,int Pplayer)       //~9B28I~
    {                                                              //~9B28I~
    	if (Dump.Y) Dump.println("UAD2Touch.resetAuto2Touch actionID="+PactionID+",player="+Pplayer+",yourAction="+yourAction);//~9B28I~//~9B29R~
    	boolean rc=chkMultiRon(PactionID,Pplayer);                 //~9B28I~
        if (!rc)                                                   //~9B28I~
        {                                                          //~9B28I~
//          if (PactionID==GCM_RON && player!=PLAYER_YOU)          //~9B29R~
            if (yourAction!=0 && Pplayer!=PLAYER_YOU)              //~9B29R~
	        	AG.aUserAction.showInfo(GMSGOPT_ANDTOAST,R.string.ActionBlockReleasedByOtherActionDone);//~9B29I~
			resetAuto2Touch();                                     //~9B28I~
            return false;                                          //~9B28I~
        }                                                          //~9B28I~
        else                                                       //~9B29I~
		    UADL.setWaiterLight(Pplayer,false/*swOn*/);            //~9B29I~
	    releaseAuto2Touch(PactionID,Pplayer);                      //~9B28I~
    	if (Dump.Y) Dump.println("UAD2Touch.resetAuto2Touch rc="+rc);//~9B28I~//~9B29R~
        return rc;
    }                                                              //~9B28I~
    //************************************************************ //~9B28I~
    //*return ronctr>1 & DUPRONK and alseo set swDelayedRon if not DUPRONOK//~9C09I~
    //************************************************************ //~9C09I~
    public boolean chkMultiRon(int PactionID,int Pplayer)          //~9B28R~
    {                                                              //~9B28I~
    	boolean rc=false;                                          //~9B28I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~9B29I~
    	if (Dump.Y) Dump.println("UAD2Touch.chkMultiRon actionID="+PactionID+",player="+Pplayer+",eswn="+eswn);//~9B28I~//~9B29R~
    	if (Dump.Y) Dump.println("UAD2Touch.chkMultiRon blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction)+",blockerFlagQ="+Arrays.toString(blockerFlagQ)+",blockerEswnQ="+Arrays.toString(blockerEswnQ));//~9B28R~//~9B29R~
        if (PactionID!=GCM_RON)                                    //~9B28I~
        	return false;                                          //~9B28I~
        int ctrMultiOK=0;                                          //~9B28I~
        swDelayedRon=false;                                        //~9B29I~
        for (int ii=0;ii<blockerCtr;ii++)                          //~9B28I~
        {                                                          //~9B28I~
            if ((blockerFlagQ[ii] & PARM_FLAG_DUPRONOK)!=0)        //~9B28I~
            	ctrMultiOK++;                                      //~9B28I~
            else                                                   //~9B29I~
            if (blockerEswnQ[ii]==eswn && blockerAction[eswn]==GCM_RON)//~9C09I~//~9C11R~
            {                                                      //~9C09I~
//              int eswnQ=blockerEswnQ[ii];                            //~9C09I~//~9C11R~
//  			if (eswnQ!=eswn && blockerAction[eswnQ]==GCM_RON) //not DUPOK ron except me//~9C09R~//~9C11R~
            		swDelayedRon=true;                             //~9C09R~
            }                                                      //~9C09I~
        }                                                          //~9B28I~
    	if (Dump.Y) Dump.println("UAD2Touch.chkMultiRon ctrMultiRon="+ctrMultiOK+",swDelayedRon="+swDelayedRon);//~9B28R~//~9B29R~
        if (ctrMultiOK<2)                                          //~9B28I~
        	return false;                                          //~9B28I~
        return true;	//multiple ron declared                    //~9B28I~
    }                                                              //~9B28I~
    //************************************************************ //~9B29I~
    private int chkMultiRonStatus(int PactionID,int Pplayer)    //~9B29I~//~9C09R~
    {                                                              //~9B29I~
    	int status=0;                                              //~9B29I~
    	if (Dump.Y) Dump.println("UAD2Touch.chkMultiRonStatus actionID="+PactionID+",player="+Pplayer);//~9B29R~
    	if (Dump.Y) Dump.println("UAD2Touch.chkMultiRonStatus blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction)+",blockerFlagQ="+Arrays.toString(blockerFlagQ)+",blockerEswnQ="+Arrays.toString(blockerEswnQ));//~9B29R~//~9C09R~
        boolean rc=chkMultiRon(PactionID,Pplayer);           //~9B29I~
//      if (!rc)                                                   //~9B29I~//~9C09R~
//      	return status;                                         //~9B29I~//~9C09R~
        if (swDelayedRon)	//your Ron is delayed                   //~9B29I~//~9C09R~
        	status=MRSTAT_DELAYED;                                 //~9B29I~
        else                                                       //~9B29I~
        if (rc)                                                    //~9C09I~
        {                                                          //~9C09I~
            if (swSettingMultiRon)                                     //~9B29I~//~9C09R~
                status=MRSTAT_ALL;                                     //~9B29I~//~9C09R~
            else                                                       //~9B29I~//~9C09R~
                status=MRSTAT_ESWN;                                    //~9B29I~//~9C09R~
        }                                                          //~9C09I~
        else                                                       //~9C11I~
            if (PactionID!=GCM_RON)                                //~9C11I~
                status=MRSTAT_NOTRON;                              //~9C11I~
    	if (Dump.Y) Dump.println("UAD2Touch.chkMultiRonStatus status="+status);//~9C09I~
        return status;  //multiple ron declared                    //~9B29I~
    }                                                              //~9B29I~
    //************************************************************ //~9B29I~
    public boolean isDupRonable2Touch(int Pplayer)                     //~9B29I~
    {                                                              //~9B29I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~9B29I~
    	if (Dump.Y) Dump.println("UAD2Touch.isDupRon2Touch player="+Pplayer+",eswn="+eswn);//~9B29I~
    	if (Dump.Y) Dump.println("UAD2Touch.isDupRon2Touch blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction)+",blockerFlagQ="+Arrays.toString(blockerFlagQ)+",blockerEswnQ="+Arrays.toString(blockerEswnQ));//~9B29I~
        boolean rc=false;                                          //~9B29I~
        if (blockerAction[eswn]==GCM_RON)                          //~9B29I~
            for (int ii=0;ii<blockerCtr;ii++)                      //~9B29I~
            {                                                      //~9B29I~
                if (blockerEswnQ[ii]==eswn)	                       //~9B29I~
                    rc=(blockerFlagQ[ii] & PARM_FLAG_DUPRONOK)!=0;  //~9B29I~
            }                                                      //~9B29I~
    	if (Dump.Y) Dump.println("UAD2Touch.isDupRon2Touch rc="+rc);//~9B29I~
        return rc;                                                 //~9B29R~
    }                                                              //~9B29I~
////**********************************                               //~v@@@I~//~9B28R~
//    public void releasedAll(int PactionID)                         //~v@@@I~//~9B28R~
//    {                                                              //~v@@@I~//~9B28R~
//        if (Dump.Y) Dump.println("UAD2Touch.releasedAll actionID="+PactionID);//~v@@@R~//~9B28R~
//        updateBtn(actionToCancel,STAT_BLOCK_RELEASED_ALL);         //~v@@@R~//~9B28R~
//    }                                                              //~v@@@I~//~9B28R~
////**********************************                               //~v@@@I~//~9B28R~
//    public void updateBtn(int PactionID,int Pstatus)               //~v@@@R~//~9B28R~
//    {                                                              //~v@@@I~//~9B28R~
//        if (Dump.Y) Dump.println("UAD2Touch.updateBtn actionID="+PactionID+",status="+Pstatus+",actionToCancel="+actionToCancel);//~v@@@R~//~9B28R~
//        int status=0,color=0;                                      //~v@@@I~//~9B28R~
//        actionBlocking=0;                                          //~v@@@I~//~9B28R~
//        int actionBtn=0;                                           //~v@@@I~//~9B28R~
//        if (UADL.sw2Touch)                                         //~v@@@I~//~9B28R~
//            status=BTN_STATUS_DISABLE_CANCEL;                      //~v@@@I~//~9B28R~
//        switch(Pstatus)                                            //~v@@@I~//~9B28R~
//        {                                                          //~v@@@I~//~9B28R~
//        case STAT_BLOCKED_NOW:       //you are 1st blocker         //~v@@@R~//~9B28R~
//            if (UADL.sw2Touch)                                     //~v@@@I~//~9B28R~
//                status=BTN_STATUS_ENABLE_CANCEL;                   //~v@@@I~//~9B28R~
//            actionBlocking=PactionID;                              //~v@@@I~//~9B28R~
//            color=COLOR_BLOCKING;                                  //~v@@@I~//~9B28R~
//            actionToCancel=PactionID;                              //~v@@@I~//~9B28R~
//            actionBtn=PactionID;                                   //~v@@@I~//~9B28R~
//            break;                                                 //~v@@@I~//~9B28R~
//        case STAT_BLOCKED_ALREADY:  //2nd blocker                  //~v@@@I~//~9B28R~
//            if (UADL.sw2Touch)                                     //~v@@@I~//~9B28R~
//                status=BTN_STATUS_ENABLE_CANCEL;                   //~v@@@I~//~9B28R~
//            color=COLOR_MORE;                                      //~v@@@I~//~9B28R~
//            AG.aUserAction.showInfo(0,R.string.ActionBlockedByOther);//~v@@@I~//~9B28R~
//            actionToCancel=PactionID;                              //~v@@@I~//~9B28R~
//            actionBtn=PactionID;                                   //~v@@@I~//~9B28R~
//            break;                                                 //~v@@@I~//~9B28R~
//        case STAT_BLOCKED_MORE:     //released but not 1st yet     //~v@@@R~//~9B28R~
//            if (UADL.sw2Touch)                                     //~v@@@I~//~9B28R~
//                status=BTN_STATUS_ENABLE_CANCEL;                   //~v@@@I~//~9B28R~
//            color=COLOR_MORE;                                      //~v@@@I~//~9B28R~
//            AG.aUserAction.showInfo(0,R.string.ActionBlockedByOther);//~v@@@I~//~9B28R~
//            actionBtn=actionToCancel;                              //~v@@@I~//~9B28R~
//            break;                                                 //~v@@@I~//~9B28R~
//        case STAT_BLOCKER_YOU:                                     //~v@@@I~//~9B28R~
//            if (UADL.sw2Touch)                                     //~v@@@I~//~9B28R~
//                status=BTN_STATUS_ENABLE_CANCEL;                   //~v@@@I~//~9B28R~
//            actionBlocking=PactionID;                              //~v@@@I~//~9B28R~
//            color=COLOR_BLOCKING;                                  //~v@@@I~//~9B28R~
//            actionBtn=actionToCancel;                              //~v@@@I~//~9B28R~
//            AG.aUserAction.showInfo(0,R.string.ActionBlockedNowYourTurn);//~v@@@I~//~9B28R~
//            break;                                                 //~v@@@I~//~9B28R~
//        case STAT_BLOCK_RELEASED:                                  //~v@@@I~//~9B28R~
//            color=COLOR_NORMAL;                                    //~v@@@I~//~9B28R~
//            actionBtn=actionToCancel;                              //~v@@@I~//~9B28R~
//            break;                                                 //~v@@@I~//~9B28R~
//        case STAT_BLOCK_RELEASED_ALL:   //last released            //~v@@@I~//~9B28R~
//            color=COLOR_NORMAL;                                    //~v@@@I~//~9B28R~
//            actionBtn=actionToCancel;                              //~v@@@I~//~9B28R~
//            actionToCancel=0;                                      //~v@@@I~//~9B28R~
//            break;                                                 //~v@@@I~//~9B28R~
//        case STAT_BLOCKER_YET:      //other canceled,but top blocker is not changed//~v@@@I~//~9B28R~
//            if (Dump.Y) Dump.println("UAD2Touch.runUpdateBtn 1st blocker not changed return");//~v@@@R~//~9B28R~
//            return;                                                //~v@@@I~//~9B28R~
//        }                                                          //~v@@@I~//~9B28R~
//        if (Dump.Y) Dump.println("UAD2Touch.updateBtn actionBtn="+actionBtn+",actionBlocking="+actionBlocking+",actionToCancel="+actionToCancel);//~v@@@R~//~9B28R~
//        runUpdateBtnUI(actionBtn,status,color);                    //~v@@@R~//~9B28R~
//    }                                                              //~v@@@I~//~9B28R~
//**********************************                               //~9B28I~
    private boolean isWaitSelectMultipleMode()                                        //~9B28I~//~9C09R~
    {                                                              //~9B28I~
        if (Dump.Y) Dump.println("UAD2Touch.isWaitSelectMode by yourAction="+yourAction);//~9C09I~
	    boolean rc=isWaitSelectMultipleMode(yourStatus,yourAction);//~9C09R~
        return rc;
    }                                                              //~9C09I~
//**********************************                               //~9C09I~
    public boolean isWaitSelectMultipleMode(int Pstatus,int PactionID)//~9C09R~
    {                                                              //~9C09I~
        int action;
        if (Pstatus==YS_BLOCKED_NEXT || Pstatus==0/*from UADelayed2.isYourTurn*/)//~9C09R~
        {                                                          //~9C09I~
    		action=getTopBlockerAction() ;                         //~9C09I~
            if (action==0)                                         //~9C09I~
            	action=PactionID;                                  //~9C09I~
        }                                                          //~9C09I~
        else                                                       //~9C09I~
        	action=PactionID;                                       //~9C09I~
	    boolean rc=UADL.isWaitSelectMultipleMode(action);          //~9C09I~
        if (Dump.Y) Dump.println("UAD2Touch.isWaitSelectMode action="+action+",yourStatus="+yourStatus+",rc="+rc);//~9C09I~
        return rc;                                                 //~9C09I~
    }                                                              //~9C09I~
//**********************************                               //~9C09I~
    private void updateBtn()                                        //~9C09I~//+0A18R~
    {                                                              //~9C09I~
    	int eswn,actionID;                                         //~9C06I~
        if (Dump.Y) Dump.println("UAD2Touch.updateBtn yourActionBlocking="+yourActionBlocking+",yourStatus="+yourStatus);//~9B28R~
//      if (UADL.isWaitSelectMultipleMode(yourAction))	//not cancelable        //~9C05I~//~9C07R~//~9C09R~
        if (isWaitSelectMultipleMode())	//not cancelable           //~9C09I~
        {                                                          //~9C05I~
    		updateBtnWaitSelect();                                 //~9C05I~
            return;                                                //~9C07I~
    	}                                                          //~9C05I~
        int status=0,color=0;                                      //~9B28I~
//      if (UADL.sw2Touch)                                         //~9B28I~//~9C04R~
//      boolean swCancelable=UADL.is2TouchMode(); //Pon+Ron 2touch mode//~9C04I~//~9C06R~
        boolean swCancelable;                                      //~9C06I~
//      if (swCancelable)                                          //~9C04R~//~9C06R~
//          status=BTN_STATUS_DISABLE_CANCEL;                      //~9B28I~//~9C06R~
        status=BTN_STATUS_DISABLE_CANCEL;                          //~9C06I~
        switch(yourStatus)                                         //~9B28I~
        {                                                          //~9B28I~
        case YS_BLOCKED_TOP:       //you are 1st blocker           //~9B28I~
//          if (UADL.sw2Touch)                                     //~9B28I~//~9C04R~
//  	    if (UADL.sw2TouchPonRon)                               //~9C04R~
    		eswn=blockerEswnQ[0];                                  //~9C06I~
    		actionID=blockerAction[eswn];                          //~9C06I~
			swCancelable=UADL.is2TouchModeCancelable(actionID);    //~9C06I~
    	    if (swCancelable)                                      //~9C04I~//~9C06R~
                status=BTN_STATUS_ENABLE_CANCEL;                   //~9B28I~
            color=COLOR_BLOCKING;                                  //~9B28I~
            break;                                                 //~9B28I~
        case YS_BLOCKED_NEXT:  //2nd blocker                       //~9B28I~
//          if (UADL.sw2Touch)                                     //~9B28I~//~9C04R~
//  	    if (UADL.sw2TouchPonRon)                               //~9C04R~
//  		eswn=blockerEswnQ[0];                                  //~9C06I~//~9C09R~
//  		actionID=blockerAction[eswn];                          //~9C06I~//~9C09R~
    		actionID=yourAction;                                   //~9C09I~
			swCancelable=UADL.is2TouchModeCancelable(actionID);    //~9C06I~
	        if (Dump.Y) Dump.println("UAD2Touch.updateBtn statusMultiRon="+statusMultiRon);//~9C09I~
    	    if (swCancelable)                                      //~9C04I~
                status=BTN_STATUS_ENABLE_CANCEL;                   //~9B28I~
            color=COLOR_MORE;                                      //~9B28I~
            int msgid=0;                                             //~9B29I~//~9C11R~
            if (statusMultiRon==MRSTAT_ALL)                         //~9B29I~
	            msgid=R.string.ActionBlockedMultiRonNextAll;       //~9B29I~
            else                                                   //~9B29I~
            if (statusMultiRon==MRSTAT_ESWN)                        //~9B29I~
	            msgid=R.string.ActionBlockedMultiRonNextEswn;      //~9B29I~
            else                                                   //~9B29I~
            if (statusMultiRon==MRSTAT_DELAYED)                    //~9B29I~
	            msgid=R.string.ActionBlockedMultiRonDelayed;       //~9B29I~
            else                                                   //~9B29I~
            if (statusMultiRon==MRSTAT_NOTRON)                     //~9C11I~
	            msgid=R.string.ActionBlockedByOther;//~9B28I~      //~9B29R~
//          AG.aUserAction.showInfo(GMSGOPT_ANDTOAST,msgid);                      //~9B29I~//~9C10R~
			if (msgid!=0)                                          //~9C11I~
	            AG.aUserAction.showInfo(0,msgid);                      //~9C10I~//~9C11R~
            break;                                                 //~9B28I~
        default:                                                   //~9B28I~
            color=COLOR_NORMAL;                                    //~9B28I~
        }                                                          //~9B28I~
        runUpdateBtnUI(status,color);                              //~9B28R~
    }                                                              //~9B28I~
//**********************************                               //~9C05I~
    private void updateBtnWaitSelect()                              //~9C05I~//~9C07R~
    {                                                              //~9C05I~
        if (Dump.Y) Dump.println("UAD2Touch.updateBtnWaitSelect yourActionBlocking="+yourActionBlocking+",yourStatus="+yourStatus);//~9C05I~
        int color=0;                                               //~9C05I~
        int status=BTN_STATUS_DISABLE_CANCEL;                      //~9C05I~
        switch(yourStatus)                                         //~9C05I~
        {                                                          //~9C05I~
        case YS_BLOCKED_TOP:       //you are 1st blocker           //~9C05I~
            color=COLOR_BLOCKING;                                  //~9C05I~
            break;                                                 //~9C05I~
        case YS_BLOCKED_NEXT:  //2nd blocker                       //~9C05I~
            color=COLOR_MORE;                                      //~9C05I~
	    	int msgid=R.string.ActionBlockedYouAreNotTopWaitSelectModeWithTimeout;//~9C05I~
//          AG.aUserAction.showInfo(GMSGOPT_ANDTOAST,msgid);       //~9C05I~//~9C10R~
            AG.aUserAction.showInfo(0,msgid);                      //~9C10I~
            break;                                                 //~9C05I~
        default:                                                   //~9C05I~
            color=COLOR_NORMAL;                                    //~9C05I~
        }                                                          //~9C05I~
        runUpdateBtnUI(status,color);                              //~9C05I~
    }                                                              //~9C05I~
    //*******************************************************      //~v@@@I~
    private void runUpdateBtnUI(final int Pstat,final int Pcolor)//~v@@@R~//~9B28R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAD2Touch.runUpdateBtnUI yourActionBlocking="+yourActionBlocking+",stat="+Pstat+",color="+Integer.toHexString(Pcolor));//~v@@@R~//~9B28R~
        yourActionBtn=yourActionBlocking;                          //~9B28I~
        AG.activity.runOnUiThread(                                 //~v@@@I~
            new Runnable()                                         //~v@@@I~
            {                                                      //~v@@@I~
                @Override                                          //~v@@@I~
                public void run()                                  //~v@@@I~
                {                                                  //~v@@@I~
                    try                                            //~v@@@I~
                    {                                              //~v@@@I~
    				    if (Dump.Y) Dump.println("UAD2Touch.updateDlgUI runonUiThread.run");//~v@@@R~
//                      updateBtnUI(PactionID,Pstat,Pcolor);               //~v@@@I~//~9B28R~
                        updateBtnUI(yourActionBtn,Pstat,Pcolor);   //~9B28R~
                    }                                              //~v@@@I~
                    catch(Exception e)                             //~v@@@I~
                    {                                              //~v@@@I~
                        Dump.println(e,"UAD2Touch.updateDlgUI.run");//~v@@@R~
                    }                                              //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
                                  );                               //~v@@@I~
                                                                   //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    //*on MainThread                                               //~v@@@I~
    //*******************************************************      //~v@@@I~
    private void updateBtnUI(int PactionID,int Pstat,int Pcolor)   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAD2Touch.updateBtnUI PactionID="+PactionID+",stat="+Pstat+",color="+Integer.toHexString(Pcolor));//~v@@@R~//~9B28R~
        AG.aGC.updateActionBtn2Touch(PactionID,Pstat,Pcolor);      //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~//~9B28R~
    //*from GC,cancel btn                                          //~9B28I~
    //*******************************************************      //~9B28I~
    public void actionCancel()                                     //~v@@@I~//~9B28R~
    {                                                              //~v@@@I~//~9B28R~
        if (Dump.Y) Dump.println("UAD2Touch.actionCancel yourAction="+yourAction);//~v@@@R~//~9B28R~
        UADL.actionCancelBtn(yourAction);                    //~v@@@R~//~9B28R~
    }                                                              //~v@@@I~//~9B28R~
    //*******************************************************      //~v@@@I~
    public int getActionBlocking()                                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAD2Touch.getActionBlocking rc="+yourAction);//~v@@@R~//~9B28R~
//      return actionBlocking;                                     //~v@@@I~//~9B28R~
        return yourAction;                                         //~9B28I~
    }                                                              //~v@@@I~
    //*******************************************************      //~9B28I~
    public boolean isEnableActionBtn()                             //~9B28I~
    {                                                              //~9B28I~
    	boolean rc=yourStatus!=YS_BLOCKED_NEXT;        //1st blocker,or none blocking//~9B28I~
        if (Dump.Y) Dump.println("UAD2Touch.isEnableActionBtn yourStatus="+yourStatus+",rc="+rc);//~9B28I~//~9C05R~
        return rc;                                                 //~9B28I~
    }                                                              //~9B28I~
    //*******************************************************      //~9B28I~
    public boolean is2ndTouch()                                    //~9B28I~
    {                                                              //~9B28I~
    	boolean rc=yourStatus==YS_BLOCKED_TOP;        //1st blocker//~9B28I~
        if (Dump.Y) Dump.println("UAD2Touch.is2ndTouch rc="+rc);   //~9B28I~
        return rc;                                                 //~9B28I~
    }                                                              //~9B28I~
    //*******************************************************      //~9C06I~
    public boolean is2ndTouchAction(int PactionID)                 //~9C06I~
    {                                                              //~9C06I~
    	boolean rc=yourStatus==YS_BLOCKED_TOP && yourAction==PactionID;        //1st blocker with the action//~9C06I~
        if (Dump.Y) Dump.println("UAD2Touch.is2ndTouchAction rc="+rc+",actionID="+PactionID+",yourStatus="+yourStatus+",yourAction="+yourAction);//~9C06I~
        return rc;                                                 //~9C06I~
    }                                                              //~9C06I~
    //*******************************************************      //~9C06I~
    public boolean is2ndTouchOtherAction(int PactionID)            //~9C06I~
    {                                                              //~9C06I~
    	boolean rc=yourStatus==YS_BLOCKED_TOP && yourAction!=PactionID;        //1st blocker with the action//~9C06I~
        if (Dump.Y) Dump.println("UAD2Touch.is2ndTouchOtherAction rc="+rc+",actionID="+PactionID+",yourStatus="+yourStatus+",yourAction="+yourAction);//~9C06I~
        return rc;                                                 //~9C06I~
    }                                                              //~9C06I~
}//class                                                           //~v@@@R~
