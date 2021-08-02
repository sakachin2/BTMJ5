//*CID://+vab0R~: update#=1017;                                    //+vab0R~
//**********************************************************************//~v101I~
//2021/07/24 vab0 PlayAlone mode;no need startTimer for Block timeout. timer used by DrawnReqDlgHW for STOP auto//+vab0I~
//2021/07/24 vaaZ PlayAlone mode;after Cancel Ron,discard rejected by err msg of "push orange btn" when ron is cancelable//~vaaYI~
//2021/07/24 vaaY PlayAlone mode;avoid Win/Cancel butn update twice when ron is cancelable//~vaaYI~
//2021/06/27 vaa2 Notify mode of Match                             //~vaa2I~
//2021/04/28 va8t (Bug)interrace of human and robot RON, 1st human RON becomes DelayedRon.//~va8tI~
//2021/03/31 va74 va60 ignore robot Ron if Human  ron is cancelable, Now allow schedule next Robot ron if human canceled also when trainingmode without notify option//~va74I~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/10/18 va19 warning use anywan if blocked topn               //~va19I~
//**********************************************************************//~va19I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import android.graphics.Point;
import android.graphics.Rect;
import android.os.Message;

import com.btmtest.R;
import com.btmtest.dialog.ActionAlert;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Alert;                                    //~9B16I~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;                          //~v@@@M~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.gv.GMsg.*;
import static com.btmtest.utils.Alert.*;                           //~9B16I~

//~v@@@I~
public class UADelayed2 extends UADelayed                          //~9B17R~
            implements Alert.AlertI                                //~9B16I~
{                                                                  //~0914I~
	private  final static int PARMPOS_2TOUCH_STATUS=1;              //~v@11R~//~9B17I~
	private  final static int PARMPOS_ACTION_2TOUCH=1;             //~9B18R~
    private  final static int PARMPOS_ACTION_2TOUCH_STATUS=2;      //~9B18R~
    private  final static int PARMPOS_ACTION_2TOUCH_PARM_ESWN=3;        //~9B26I~//~9B28R~
    private  final static int PARMPOS_ACTION_2TOUCH_PARM_FLAG=4;   //~9B28I~
                                                                   //~9B29I~
    public  final static int PARM_FLAG_DUPRONOK=0x01;             //~9B28I~
    public  final static int PARM_FLAG_WAITSELECT=0x02;            //~9C04I~
    public  final static int PARM_FLAG_WAITSELECT_2ND=0x04;        //~9C07I~
    public  final static int PARM_FLAG_SCHEDULED=0x08; 	//by top cancel(timeout or cancel btn)//~9C10I~
    public  final static int PARM_FLAG_SCHEDULED_NEXTTOP_MULTISELECT=0x10;//~9C10I~
                                                                   //~9B17I~
	public   final static int STAT_ACTION_BTN=0;                   //~9B18R~
	public   final static int STAT_ACTION_2TOUCH=1;                //~9B18I~
                                                                   //~9B18I~
	private  final static int STAT_CONFIRM_REQ=0;                  //~9B18I~//~9B19R~//~9B28R~
	private  final static int STAT_BLOCKED    =1;      //first blocker               //~v@11R~//~9B17I~//~9B19R~//~9B26R~//~9B27I~//~9B28R~
	private  final static int STAT_CONFIRM_OK =2;                  //~v@11R~//~9B17I~//~9B19R~//~9B27R~//~9B28R~
	private  final static int STAT_CONFIRM_CANCEL=3;              //~v@11R~//~9B17I~//~9B19R~//~9B27R~//~9B28R~
	private  final static int STAT_CONFIRMED_OK=4;                 //~9B17I~//~9B19R~//~9B27R~//~9B28R~
	private  final static int STAT_CONFIRMED_CANCEL=5;             //~9B17I~//~9B19R~//~9B27R~//~9B28R~
	private  final static int STAT_CONFIRMED_CANCEL_TIMEOUT=6;     //~9C05I~
	private  final static int STAT_RESCHEDULED_NEXTTOP=7;          //~9C10I~
                                                                   //~9B27I~
    public  final static int STAT_BLOCKED_NOW=10;      //first blocker//~9B27I~//~9B28R~
    public  final static int STAT_BLOCKED_ALREADY=11; //2nd blocker            //~9B17I~//~9B19R~//~9B26R~//~9B27R~//~9B28R~
    public  final static int STAT_BLOCKED_2TOUCH=12;    //2nd touch//~9B27R~//~9B28R~
                                                                   //~9B27I~//~9B28R~
    public  final static int STAT_BLOCKED_MORE=20;                //~9B18I~//~9B19R~//~9B21R~//~9B22R~//~9B27R~//~9B28R~
    public  final static int STAT_BLOCKER_YOU=21;     //now new first blocker//~9B26R~//~9B27R~//~9B28R~
    public  final static int STAT_BLOCKER_YET=22;     //first blocker not changed//~9B26I~//~9B27R~//~9B28R~
    public  final static int STAT_BLOCK_RELEASED=23;        //one released       //~9B24I~//~9B26R~//~9B27R~//~9B28R~
    public  final static int STAT_BLOCK_RELEASED_ALL=24;    //last relesed//~9B27R~//~9B28R~
                                                                   //~9C07I~
	private static final int DELY_SCHEDULE2ND  =1000;  //delay for 2nd blocker action//~9C07I~
                                                                   //~9B28I~
	public int timeoutAutoTake;//access from Robot                 //~9702I~
    public int delay2Touch;                                    //~9B16I~
//  public boolean sw2Touch;                                       //~9B16I~//~9C04R~
    public boolean sw2TouchPonRon;                                 //~9C04I~
//  public boolean sw2TouchPon,sw2TouchRon;                        //~9C04R~//~0205R~
//  private boolean sw2TouchPon,sw2TouchRon;                       //~0205I~//~vaaYR~
    private boolean sw2TouchPon;                                   //~vaaYI~
    public  boolean sw2TouchRon;                                   //~vaaYI~
//  public boolean swBlockTimeoutPon,swBlockTimeoutRon;            //~9C05I~//~9C09R~
    public boolean swBlockTimeout;                                 //~9C09I~
    public boolean swWaitSelectMultiple;                           //~9C04R~
//    private int[] blockerAction=new int[PLAYERS]; //actionID by current eswn                  //~9B18I~//~9B19R~//~9B28R~
//    private int[] blockerActionQ=new int[PLAYERS];//blocker eswn by blockerCtr sequence//~9B19R~//~9B24R~//~9B28R~
//    private int blockerCtr;                                        //~9B18I~//~9B28R~
    private boolean swBtn2Touch=true;   //2touch not by ActionAlert but button action//~9B25I~
    private UAD2Touch UAD2T;                                      //~9B25I~//~9B28R~
    private int actionNewBlocker;                                  //~9B26I~
    private boolean swRonable;                                    //~9B28I~
    private boolean swRobot;                                       //~va60I~
    private boolean swStopAuto2TouchRobotRon;                      //~va8tR~
//*************************                                        //~v@@@I~
	public UADelayed2()   //for IT                                 //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("UADelayed2.default Constructor");//~va60I~
    }                                                              //~va60I~
	public UADelayed2(UserAction Pua)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~9B17R~
    {                                                              //~0914I~
        AG.aUADelayed=this;                                        //~v@@@I~//~9B17R~
        UA=Pua;                                                    //~v@@@I~//~9B17R~
        if (swBtn2Touch)                                           //~9B25I~
        	UAD2T=new UAD2Touch(this);                            //~9B25I~//~9B28R~
//        GVH=AG.aGameViewHandler;                                 //~9B17R~
//        PLS=AG.aPlayers;                                       //~v@@@R~//~9B17R~
//        swWaiting=new boolean[PLAYERS];                            //~v@@@I~//~9B17R~
//        swWaitingAutoByDrawnHW=new boolean[PLAYERS];               //~9702I~//~9B17R~
//        Arrays.fill(swWaiting,false);                              //~v@@@I~//~9B17R~
//        UADLT=new UADelayTimer();                                  //~v@@@I~//~9B17R~
//        swStop=false;                                              //~v@@@I~//~9B17R~
//        delayPonKan=RuleSettingOperation.getDelayPonKan();                  //~v@@@I~//~9624R~//~9B17R~
//        delayTake=RuleSettingOperation.getDelayTake();                      //~v@@@I~//~9624R~//~9B17R~
//        delayLast=RuleSettingOperation.getDelayLast();                      //~v@@@I~//~9624R~//~9B17R~
//        currentEswn=Accounts.getCurrentEswn();                     //~v@@@I~//~9624R~//~9B17R~
//        timeoutAutoDiscard=RuleSettingOperation.getDelayDiscard();         //~9624I~//~9B17R~
//        timeoutAutoTake=RuleSettingOperation.getTimeoutTake(); //~9624I~//~9B17R~
//        timeoutAutoTakeRobot=RuleSettingOperation.getTimeoutTakeRobot();//~9701I~//~9B17R~
////      timeoutAutoTakeKan=RuleSettingOperation.getTimeoutTakeKan();//~9624I~//~9625R~//~9B17R~
//  	sw2Touch=RuleSettingOperation.isRuleWait();                //~9B16I~//~9C03R~
//  	int stop2TPon=RuleSettingOperation.get2TouchOptionPonKanChii();//~9C03I~//~9C07R~
//  	int stop2TRon=RuleSettingOperation.get2TouchOptionRon();   //~9C03I~//~9C07R~
//      sw2TouchPon=stop2TPon==STOP2T_CANCELABLE;                             //~9C03I~//~9C04R~//~9C07R~
//      sw2TouchRon=stop2TRon==STOP2T_CANCELABLE;                          //~9C03I~//~9C04R~//~9C07R~
//      sw2TouchPon=RuleSettingOperation.is2TouchWithCancelPonKanChii();  //~9C07I~//~9C10R~
        sw2TouchPon=false;                                         //~9C10I~
//      sw2TouchRon=RuleSettingOperation.is2TouchWithCancelRon();  //~9C07I~//~9C10R~
        sw2TouchRon=RuleSettingOperation.is2TouchCancelableRon();  //~9C10I~
//      if (AG.swPlayAloneNotify)                                  //~va70R~
//      	sw2TouchRon=true;                                      //~va70R~
        sw2TouchPonRon=sw2TouchPon||sw2TouchRon;                   //~9C04I~
//      swWaitSelectMultiple=stop2TPon==STOP2T_YES;                //~9C04R~//~9C07R~
        swWaitSelectMultiple=!sw2TouchPon;                         //~9C07R~
    	delay2Touch=RuleSettingOperation.getDelay2Touch();	//stop timeout         //~9B16I~//~9C03R~
//      swBlockTimeoutPon=RuleSettingOperation.is2TouchTimeoutPonKanChii(); //~9C05I~//~9C09R~
//      swBlockTimeoutRon=RuleSettingOperation.is2TouchTimeoutRon();          //~9C05I~//~9C09R~
        swBlockTimeout=RuleSettingOperation.is2TouchTimeout();     //~9C09I~
//      if (AG.swPlayAloneNotify)                                  //~va70I~//~va74R~
        if (AG.swTrainingMode)                                     //~va74I~
        	swBlockTimeout=false;                                  //~va70I~
        if (Dump.Y) Dump.println("UADelayed2.constructor sw2TouchPon="+sw2TouchPon+",sw2TouchRon"+sw2TouchRon+",delay2Touch="+delay2Touch);//~9C04I~
    }                                                              //~0914I~
	//*************************************************************************//~9C04I~
    private int getBlockTimeout(int PactionID)                      //~9C05R~//~9C09R~
    {                                                              //~9C04I~
    	int rc=delay2Touch;                                        //~9C05R~
//        if (PactionID==GCM_RON)                                    //~9C05I~//~9C09R~
//        {                                                          //~9C05I~//~9C09R~
//            if (!swBlockTimeoutRon)                                //~9C05I~//~9C09R~
//                rc=0;                                              //~9C05I~//~9C09R~
//        }                                                          //~9C05I~//~9C09R~
//        else                                                       //~9C05I~//~9C09R~
//        {                                                          //~9C05I~//~9C09R~
//            if (!swBlockTimeoutPon)                                //~9C05I~//~9C09R~
//                rc=0;                                              //~9C05I~//~9C09R~
//        }                                                          //~9C05I~//~9C09R~
        if (!swBlockTimeout)                                       //~9C09I~
            rc=0;                                                  //~9C09I~
        if (Dump.Y) Dump.println("UADelayed2.getBlockTimeout actionID="+PactionID+",rc="+rc+",delay2Touch="+delay2Touch+",swBlockTimeout="+swBlockTimeout);//~9C04I~//~9C05R~//~9C09R~
        return rc;                                                 //~9C04I~
    }                                                              //~9C04I~
	//*************************************************************************//~9C07I~
    private int getBlockTimeoutTop()                                //~9C07I~//~9C09R~
    {                                                              //~9C07I~
    	int rc=0;                                                  //~9C07I~
	    Rect r=UAD2T.getTopBlockerPlayer();                        //~9C07R~
        if (r!=null)                                               //~9C07R~
		    rc=getBlockTimeout(r.right);                           //~9C07R~
        if (Dump.Y) Dump.println("UADelayed2.getBlockTimeoutTop rc="+rc+",action+flag="+Utils.toString(r));//~9C07I~
        return rc;                                                 //~9C07I~
    }                                                              //~9C07I~
    //*************************************************************************//~9C05I~//~9C06R~
    //*from updateBtn                                              //~9C06I~
    //*************************************************************************//~9C06I~
    public boolean is2TouchModeCancelable(int PactionID)                                  //~9C05I~//~9C06R~
    {                                                              //~9C05I~//~9C06R~
//      boolean rc=sw2TouchPonRon;                                 //~9C05I~//~9C06R~
      	boolean rc=PactionID==GCM_RON ? sw2TouchRon : sw2TouchPon; //~9C06I~
        if (Dump.Y) Dump.println("UADelayed2.is2TouchModeCancelable actionID="+PactionID+",rc="+rc);//~9C05I~//~9C06R~
        return rc;                                                 //~9C05I~//~9C06R~
    }                                                              //~9C05I~//~9C06R~
	//*************************************************************************
	//*if Pon is on Ron is also on to block                        //~9C06R~
	//*************************************************************************//~9C06I~
    private boolean is2TouchMode(int PactionID)                    //~9C04R~
    {                                                              //~9C04I~
//    	boolean rc=PactionID==GCM_RON ? sw2TouchRon : sw2TouchPon; //~9C04I~//~9C06R~
      	boolean rc;                                                //~9C06I~
		if (PactionID==GCM_RON)       //~9C06I~
			rc=sw2TouchRon || sw2TouchPon;                         //~9C06I~
        else                                                       //~9C06I~
			rc=sw2TouchPon;                                        //~9C06I~
        if (Dump.Y) Dump.println("UADelayed2.is2TouchMode rc="+rc+",actionID="+PactionID+",sw2TouchRon="+sw2TouchRon+",sw2TouchPon="+sw2TouchPon);//~9C04R~//~9C06R~
        return rc;
    }                                                              //~9C04I~
	//*************************************************************************//~9C04I~
    public boolean isWaitSelectMultipleMode(int PactionID)         //~9C04R~
    {                                                              //~9C04I~
//  	boolean rc=swWaitSelectMultiple;     //Pon,Chii,Kan blocks until multiple select comp//~9C07I~//~9C09R~
    	boolean rc=PactionID!=GCM_RON && swWaitSelectMultiple;     //Pon,Chii,Kan blocks until multiple select comp//~9C09I~
        if (Dump.Y) Dump.println("UADelayed2.isWaitSelectMultipleMode rc="+rc+",actionID="+PactionID);//~9C04R~//~9C10R~
        return rc;                                                 //~9C04I~
    }                                                              //~9C04I~
	//*************************************************************************//~9C04I~
    private boolean isBlockMode(int PactionID)                      //~9C04I~//~9C10R~
    {                                                              //~9C04I~
    	boolean rc=is2TouchMode(PactionID)||isWaitSelectMultipleMode(PactionID);//~9C04I~
        if (Dump.Y) Dump.println("UADelayed2.isBlockMode rc="+rc+",actionID="+PactionID);//~9C04R~
        return rc;                                                 //~9C04I~
    }                                                              //~9C04I~
	//*************************************************************************//~va8tR~
    private void setBlockRobotRon(boolean Pblock)                  //~va8tR~
    {                                                              //~va8tR~
        if (Dump.Y) Dump.println("UADelayed2.setBlockRobotRon Pblock="+Pblock);//~va8tR~
        swStopAuto2TouchRobotRon=Pblock;                           //~va8tR~
    }                                                              //~va8tR~
	//*************************************************************************//~va8tR~
    private boolean isBlockRobotRon()                              //~va8tR~
    {                                                              //~va8tR~
        if (Dump.Y) Dump.println("UADelayed2.isBlockRobotRon rc="+swStopAuto2TouchRobotRon);//~va8tR~
        return swStopAuto2TouchRobotRon;                           //~va8tR~
    }                                                              //~va8tR~
	//*************************************************************************//~va60I~
    private boolean isBlockMode(int PactionID,boolean PswServer,int Pplayer)//~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("UADelayed2.isBlockMode swServer="+PswServer+",Pplayer="+Pplayer+",actionid="+PactionID);//~va60I~
		if (PactionID!=GCM_RON)                                    //~va60I~
        {                                                          //~va60I~
        	if (PswServer)                                         //~va60I~
        	{                                                      //~va60I~
        		if (AG.aAccounts.isRobotPlayer(Pplayer))           //~va60I~
                {                                                  //~va60I~
			    	if (Dump.Y) Dump.println("UADelayed.isBlockMode return false by robot action on server");//~va60I~
        	    	return false;                                  //~va60I~
                }                                                  //~va60I~
        	}                                                      //~va60I~
        }                                                          //~va60I~
    	boolean rc=is2TouchMode(PactionID)||isWaitSelectMultipleMode(PactionID);//~va60I~
        if (Dump.Y) Dump.println("UADelayed2.isBlockMode rc="+rc+",actionID="+PactionID);//~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
	//*************************************************************************//~9B28I~
	//*from UA.selectInfo at action device                         //~9B28I~
	//*For other than Pon/Kan/Chii/Ron, for Take and Reach                             //~9B28I~//~9C04R~//~va74R~
    //*************************************************************************//~9B28I~
    public boolean chkSelectInfo2TouchOtherAction(boolean PswServer,int PactionID,int Pplayer)//~9B28I~
    {                                                              //~9B28I~
    	boolean rc;                                                //~9B28I~
        if (swStopAuto2Touch)                                      //~9B28I~
        {                                                          //~9B28I~
            if (!UAD2T.is2ndTouch())    //NOT you are already top(2nd touch)//~9C06I~
	    		UA.showInfo(0,R.string.ActionBlockedThisAction);       //~9B28I~//~9C06R~
            else                                                   //~9C06I~
                UA.showInfo(0,R.string.ActionBlockedYouAreNotBlockerOfTheAction);//~9C06I~
            rc=false;                                              //~9B28I~
        }                                                          //~9B28I~
        else                                                       //~9B28I~
			rc=true;                                               //~9B28I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2TouchOtherAction rc="+rc+",swServer="+PswServer+",action="+PactionID+",player="+Pplayer+",swStopAuto2Touch="+swStopAuto2Touch);//~9B28I~
        return rc;                                                //~9B28I~
    }                                                              //~9B28I~
	//*************************************************************************//~9C06I~
	//*from Players.isYourTurn                                     //~9C06I~
	//*chk blocked                                                 //~9C06I~
	//*************************************************************************//~9C06I~
    public int isYourTurn(int PactionID,int Pplayer)               //~9C06R~
    {                                                              //~9C06I~
    	int msgid=0;                                               //~9C06I~
        if (Dump.Y) Dump.println("UADelayed2.isYourTurn actionID="+PactionID+",player="+Pplayer+",swStopAuto2Touch="+swStopAuto2Touch);//~9C06I~//~va60R~//~va70R~
        if (Pplayer!=PLAYER_YOU)                                   //~9C06I~
        {                                                          //~va60I~
            if (AG.aAccounts.isDummyPlayer(Pplayer))  //call by robot//~va60I~
            {                                                      //~va60I~
		        if (swStopAuto2Touch)  //blocked                   //~va60I~
                {                                                  //~va60I~
	        		UView.showToast(R.string.ActionBlockedByHumanForRobot);//~va60I~
                    return -1;                                     //~va60I~
                }                                                  //~va60I~
		        if (PactionID==GCM_RON && isBlockRobotRon())  //blocked//~va8tR~
                {                                                  //~va8tR~
	        		UView.showToast(R.string.ActionBlockedByHumanForRobot);//~va8tR~
                    return -1;                                     //~va8tR~
                }                                                  //~va8tR~
            }                                                      //~va60I~
        	return msgid;                                          //~9C06I~
        }                                                          //~va60I~
        if (swStopAuto2Touch)  //blocked                                    //~9C06I~//~9C07R~
        {                                                          //~9C06I~
            if (UAD2T.is2ndTouchOtherAction(PactionID))    //Top But different action//~9C06R~
                msgid=R.string.ActionBlockedYouAreNotBlockerOfTheAction;//~9C06M~
            else   //not top or (top and same btn)                                                //~9C06I~//~9C07R~
            if (!UAD2T.is2ndTouch())    //not top bloocker         //~9C07R~
            {                                                      //~9C06R~//~9C07R~
//              if (isWaitSelectMultipleMode(PactionID))  //pon,chii,kan blockes until select comp         //~9C06R~//~9C07R~//~9C09R~
                if (UAD2T.isWaitSelectMultipleMode(0/*status*/,PactionID))  //pon,chii,kan blockes until select comp//~9C09R~
//                  if (getBlockTimeout(PactionID)==0)             //~9C06R~//~9C07R~
                    if (getBlockTimeoutTop()==0) //reject,else enq //~9C07R~
                        msgid=R.string.ActionBlockedYouAreNotTopWaitSelectModeWithNoTimeout;//~9C06R~//~9C07R~
//                  else                                           //~9C06R~//~9C07R~
//                      msgid=R.string.ActionBlockedYouAreNotTopWaitSelectModeWithTimeout;//~9C06R~//~9C07R~
//              else                                               //~9C06R~//~9C07R~
//                  msgid=R.string.ActionBlockedThisAction;        //~9C06R~//~9C07R~
            }                                                      //~9C06R~//~9C07R~
        }                                                          //~9C06I~
        if (Dump.Y) Dump.println("UADelayed2.isYourTurn msgid="+Integer.toHexString(msgid));//~9C06R~
        return msgid;                                              //~9C06I~
    }                                                              //~9C06I~
	//*************************************************************************//~va60I~
	//*from RARon                                                  //~va60I~
	//*rc:true:issue Ron                                           //~va60I~
	//*************************************************************************//~va60I~
    public boolean chkSelectInfo2TouchRobot(int Pplayer,int[] PintParm)//~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2TouchRobot player="+Pplayer);//~va60I~
        swRobot=true;                                              //~va60I~
	    boolean rc=chkSelectInfo2Touch(true/*PswServer*/,GCM_RON/*PactionID*/,Pplayer,PintParm);//~va60I~
        swRobot=false;                                             //~va60I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2TouchRobot rc="+rc);//~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
	//*************************************************************************//~9B17I~
	//*from UA.selectInfo at action device                         //~9B17R~
	//*rc:true:continue to action                                  //~9B17I~
	//*************************************************************************//~9B17I~
    public boolean chkSelectInfo2Touch(boolean PswServer,int PactionID,int Pplayer,int[] PintParm)//~9B17I~//~9B18R~
    {                                                              //~9B17I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2Touch swRobot="+swRobot+",swServer="+PswServer+",action="+PactionID+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~9B17R~//~9B18R~//~9B28R~//~9C10R~//~va60R~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2Touch swStopAuto2Touch="+swStopAuto2Touch+",sw2TouchPon="+sw2TouchPon+",sw2touchRon="+sw2TouchRon+",swBtn2Touch="+swBtn2Touch);//~9B25I~//~9B28R~//~va70R~//~va8tR~
//      if (!sw2Touch)                                             //~9B17I~//~9C04R~
//      if (!is2TouchMode(PactionID))	//Not cancelable Ron                             //~9C04R~//~9C10R~//~va60R~
        if (swRobot || !is2TouchMode(PactionID))	//Not cancelable Ron//~va60I~
        {                                                          //~9B18I~
//  		if (isWaitSelectMultipleMode(PactionID))               //~9C05I~//~9C10R~
            if (UAD2T.isWaitSelectMultipleMode(0/*status*/,PactionID))  //pon,chii blockes until select multiple comp//~9C10I~
            	return chkSelectInfoMultiple(PswServer,PactionID,Pplayer); //~9C05R~//~9C07R~
	        if (!swStopAuto2Touch)              //Not blocked by Ron//~9C10I~
            {                                                      //~9C10I~
        		if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2Touch no 2touchMode swRobot="+swRobot);//~9B18I~//~9C10R~//~va60R~
        		return true;                                           //~9B17I~//~9C10R~
            }                                                      //~9C10I~
            else                                                   //~va60I~
        	if (swRobot) //from chkSelectInfo2TouchRobot<-RARon    //~va60I~
            {                                                      //~va60I~
            	UView.showToast(R.string.ActionBlockedByHumanForRobot);//~va60I~
                return false;                                      //~va60I~
            }                                                      //~va60I~
        }                                                          //~9B18I~
        if (swStopAuto2Touch)                                      //~9B28M~
        {                                                          //~9B28M~
            if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2Touch blocked swStopAuto2Touch");
        	if (!UAD2T.isEnableActionBtn()) //yourStatus==YS_BLOCK_NEXT                       //~9B28M~//~9C09R~//~9C10R~
            {                                                      //~9B28M~
	    		UA.showInfo(0,R.string.ActionBlockedYouAreNotTop); //~9B28M~
                if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2Touch return false by 2ndBlocker");//~9B28I~
            	return false;                                      //~9B28M~
            }                                                      //~9B28M~
            if (UAD2T.is2ndTouch()) //yourStatus==YS_BLOCK_TOP                                //~9B28I~//~9C10R~
            {                                                      //~9B25I~//~9B27M~//~9B28I~
                if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2Touch return true by 2ndTouch");//~9B25I~//~9B27M~//~9B28I~
                return true;                                       //~9B25I~//~9B27M~//~9B28I~
            }                                                      //~9B25I~//~9B27M~//~9B28I~
        }                                                          //~9B28M~
        if (PintParm[PARMPOS_ACTION_2TOUCH]!=STAT_ACTION_BTN)	//! game panel button//~9B23R~
        {                                                          //~9B18I~
        	if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2Touch status=2touch");//~9B18I~
        	return true;                                           //~9B18I~
        }                                                          //~9B18I~
	    UA.setNoMsgToServer();                                     //~9B18I~
        int eswn=Accounts.getCurrentPlayerEswn();                  //~9B26I~
        int flag=0;                                                //~9B28I~
        if (isDupRonOK2Touch(PactionID,Pplayer))                   //~9B28I~
	        flag|=PARM_FLAG_DUPRONOK;                              //~9B28I~
        if (!PswServer)                                            //~9B17R~
        {                                                          //~9B18I~
//          UA.msgDataToServer=makeMsgData2Touch(PactionID,Pplayer,STAT_CONFIRM_REQ);//~9B17R~//~9B18R~
//          sendToServer2Touch(PactionID,Pplayer,STAT_CONFIRM_REQ);	//to action2Touch//~9B26I~
            sendToServer2Touch(PactionID,Pplayer,STAT_CONFIRM_REQ,eswn,flag);	//to action2Touch//~9B26I~//~9B28R~
        }                                                          //~9B18I~
        else                                                       //~9B17R~
        {                                                          //~9B18I~
        	if (PactionID==GCM_RON)                                //~va8tR~
        		if (!AG.aAccounts.isRobotPlayer(Pplayer))	//human request RON//~va8tR~
				    setBlockRobotRon(true);	//set block before GCM_2TOUCH scheduled//~va8tR~
//  		sendMsg2Touch(PactionID,Pplayer,STAT_CONFIRM_REQ);//to selectInfo2Touch & actionInfo2Touch ~9B17R~//~9B18R~//~9B26R~
    		sendMsg2Touch(PactionID,Pplayer,STAT_CONFIRM_REQ,eswn,flag);//to selectInfo2Touch & actionInfo2Touch ~9B17R~//~9B26I~//~9B28R~
        }                                                          //~9B18I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2Touch rc=false");//~9B18I~
        return false;                                                 //~9B17I~//~9B18R~
    }                                                              //~9B17I~
	//*************************************************************************//~va70I~
	//*from UARon.selectInfoPlayAloneNotify for Human player in PlayAlone mode,show Ron & Cancel Button//~va70R~//~vaa2R~
	//*************************************************************************//~va70I~
    public void notify2TouchPlayAloneNotify(int PactionID)         //~va70R~
    {                                                              //~va70I~
        if (Dump.Y) Dump.println("UADelayed2.notify2TouchPlayAloneNotify action="+PactionID+",sw2TouchRon="+sw2TouchRon);//~va70R~//~vaaYR~
//    if (false) //TODO test                                       //~vaaYR~
      	if (PactionID==GCM_RON && sw2TouchRon)                     //~vaaYI~
        {                                                          //~vaaYI~
        	AG.aGC.sendMsg(GCM_RON,null);	//emulate onClick      //~vaaYI~
	        if (Dump.Y) Dump.println("UADelayed2.notify2TouchPlayAloneNotify exit by sendMsg GCM_RON");//~vaaYI~
            return;                                                //~vaaYI~
        }                                                          //~vaaYI~
        UAD2T.stopAuto2TouchPlayAloneNotify(PactionID);            //~va70I~
        if (Dump.Y) Dump.println("UADelayed2.notify2TouchPlayAloneNotify exit");//~vaaYI~
    }                                                              //~va70I~
	//*************************************************************************//~vaa2I~
	//*from UARon.selectInfoPlayMatchNotify for Human player in Match mode,show Ron & Cancel Button//~vaa2I~
	//*************************************************************************//~vaa2I~
    public void notify2TouchPlayMatchNotify(int PactionID)         //~vaa2I~
    {                                                              //~vaa2I~
        if (Dump.Y) Dump.println("UADelayed2.notify2TouchPlayMatchNotify action="+PactionID);//~vaa2I~
        UAD2T.stopAuto2TouchPlayMatchNotify(PactionID);            //~vaa2I~
    }                                                              //~vaa2I~
//    //*************************************************************************//~va70R~
//    //*from GC at button push                                    //~va70R~
//    //*************************************************************************//~va70R~
//    public void reset2TouchPlayAloneNotify(int PactionID)        //~va70R~
//    {                                                            //~va70R~
//        if (Dump.Y) Dump.println("UADelayed2.reset2TouchPlayAloneNotify action="+PactionID);//~va70R~
//        UAD2T.reset2TouchPlayAloneNotify(PactionID);             //~va70R~
//    }                                                            //~va70R~
	//*************************************************************************//~9C05I~
    public boolean chkSelectInfoMultiple(boolean PswServer,int PactionID,int Pplayer)//~9C05R~//~9C07R~
    {                                                              //~9C05I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfoMultiple swStopAuto2Touch="+swStopAuto2Touch);//~9C05R~
        boolean rc=true;   //pass to chkSelectInfoMultiple to chk multiple selection wait//~9C05I~
        if (swStopAuto2Touch)                                      //~9C05I~
        {                                                          //~9C05I~
            if (!UAD2T.is2ndTouch())    //NOT you are already top(2nd touch)//~9C05I~//~9C06R~
            {                                                      //~9C05I~
//              if (getBlockTimeout(PactionID)==0)                 //~9C05I~//~9C09R~
                if (getBlockTimeoutTop()==0)              //~9C09I~
                {                                                  //~9C05I~
                    UA.showInfo(0,R.string.ActionBlockedYouAreNotTopWaitSelectModeWithNoTimeout);//~9C05I~
                }                                                  //~9C05I~
                else	//1st blocker selecting ,multiple candidate//~9C07I~
                {                                                  //~9C07I~
			        int flag=PARM_FLAG_WAITSELECT_2ND;        //~9C07I~
			        int eswn=Accounts.getCurrentPlayerEswn();      //~9C07I~
                    if (!PswServer)                                //~9C07I~
                    {                                              //~9C07I~
                        sendToServer2Touch(PactionID,Pplayer,STAT_CONFIRM_REQ,eswn,flag);   //to action2Touch//~9C07I~
                    }                                              //~9C07I~
                    else                                           //~9C07I~
                    {                                              //~9C07I~
                        sendMsg2Touch(PactionID,Pplayer,STAT_CONFIRM_REQ,eswn,flag);//to selectInfo2Touch & actionInfo2Touch ~9B17R~//~9C07I~
                    }                                              //~9C07I~
                }                                                  //~9C07I~
            	return false;                                  //~9C05R~//~9C07I~
            }                                                      //~9C05I~
            else           //you are top blcker                    //~9C06I~
            if (!UAD2T.is2ndTouchAction(PactionID))    //action changed  //~9C06I~
            {                                                      //~9C06I~
                UA.showInfo(0,R.string.ActionBlockedYouAreNotBlockerOfTheAction);//~9C06I~
                return false;                                      //~9C06I~
            }                                                      //~9C06I~
        }                                                          //~9C05I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfoMultiple rc="+rc);//~9C05R~
        return rc;                                                 //~9C05I~
    }                                                              //~9C05I~
	//*************************************************************************//~9C04I~
	//*from UA.selectInfo at action device when selection of multiple candidate required                        //~9C04I~//~9C07R~
	//*rc:true:continue to action                                  //~9C04I~
	//*************************************************************************//~9C04I~
    public boolean chkSelectInfoMultiple(boolean PswServer,int PactionID,int Pplayer,int[] PintParm)//~9C04I~
    {                                                              //~9C04I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfoMultiple swServer="+PswServer+",action="+PactionID+",player="+Pplayer);//~9C04I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfoMultiple swWaitSelectMultiple="+swWaitSelectMultiple+",intparm="+Arrays.toString(PintParm));//~9C04I~//~9C10R~
        int flag=0;                                                //~9C10I~
        if (!isWaitSelectMultipleMode(PactionID))                           //~9C04I~
        {                                                          //~9C04I~
        	if (Dump.Y) Dump.println("UADelayed2.chkSelectInfoMultiple no waitSelectMultipleMode");//~9C04I~
        	return true;                                           //~9C04I~
        }                                                          //~9C04I~
        if (swStopAuto2Touch)                                      //~9C04I~
        {                                                          //~9C04I~
        	if (!UAD2T.isEnableActionBtn())   //yourStatus==YS_BLOCKED_NEXT                     //~9C04I~//~9C10R~
            {                                                      //~9C04I~
	    		UA.showInfo(0,R.string.ActionBlockedYouAreNotTop); //~9C04I~
                if (Dump.Y) Dump.println("UADelayed2.chkSelectInfoMultiple return false by 2ndBlocker");//~9C04I~//~9C10R~
            	return false;                                      //~9C04I~
            }                                                      //~9C04I~
            if (UAD2T.is2ndTouch())   //yourStatus==YS_BLOCKED_TOP                             //~9C04I~//~9C10R~
            {                                                      //~9C04I~
            	int stat=PintParm[PARMPOS_2TOUCH_STATUS];          //~9C10I~
                if (stat==STAT_RESCHEDULED_NEXTTOP	//=7;	//msg scheduled for NextTop//~9C10I~
                && getBlockTimeout(PactionID)!=0)                  //~9C10I~
					flag|=PARM_FLAG_SCHEDULED_NEXTTOP_MULTISELECT;	//=0x10;//~9C10I~
                else                                               //~9C10I~
                {                                                  //~9C10I~
                	if (Dump.Y) Dump.println("UADelayed2.chkSelectInfoMultiple return true by 2ndTouch");//~9C04I~//~9C10R~
                	return true;                                       //~9C04I~//~9C10R~
                }                                                  //~9C10I~
            }                                                      //~9C04I~
        }                                                          //~9C04I~
//      if (PintParm[PARMPOS_ACTION_2TOUCH]!=STAT_ACTION_BTN)	//! game panel button//~9C04I~
//      {                                                          //~9C04I~
//      	if (Dump.Y) Dump.println("UADelayed2.chkSelectInfo2Touch status=2touch");//~9C04I~
//      	return true;                                           //~9C04I~
//      }                                                          //~9C04I~
	    UA.setNoMsgToServer();                                     //~9C04I~
        int eswn=Accounts.getCurrentPlayerEswn();                  //~9C04I~
//      int flag=0;                                                //~9C04I~
//      if (isDupRonOK2Touch(PactionID,Pplayer))                   //~9C04I~
//          flag|=PARM_FLAG_DUPRONOK;                              //~9C04I~
//      int flag=PARM_FLAG_WAITSELECT;                             //~9C04I~//~9C10R~
        flag|=PARM_FLAG_WAITSELECT;                            //~9C10I~
        if (!PswServer)                                            //~9C04I~
        {                                                          //~9C04I~
            sendToServer2Touch(PactionID,Pplayer,STAT_CONFIRM_REQ,eswn,flag);	//to action2Touch//~9C04I~
        }                                                          //~9C04I~
        else                                                       //~9C04I~
        {                                                          //~9C04I~
    		sendMsg2Touch(PactionID,Pplayer,STAT_CONFIRM_REQ,eswn,flag);//to selectInfo2Touch & actionInfo2Touch ~9B17R~//~9C04I~
        }                                                          //~9C04I~
        if (Dump.Y) Dump.println("UADelayed2.chkSelectInfoMultiple rc=false");//~9C04I~
        return false;                                              //~9C04I~
    }                                                              //~9C04I~
    //*************************************************************************//~9B28I~
    //*from chkSelectInfo2Touch at Ron btn pushed, is it in before PonKan time              //~9B28I~//~9B29R~
    //*************************************************************************//~9B28I~
    private boolean isDupRonOK2Touch(int PactionID,int Pplayer)     //~9B28I~
    {                                                              //~9B28I~
//  	if (!sw2Touch)                                             //~9B29I~//~9C04R~
    	if (!sw2TouchRon)                                          //~9C04I~
        	return false;                                          //~9B29I~
    	boolean rc=PactionID==GCM_RON && swRonable;                //~9B28I~
        if (Dump.Y) Dump.println("UADelayed2.isDupRonOK2Touch rc="+rc+",swRonable="+swRonable+",actionID="+PactionID+",Pplayer="+Pplayer);//~9B28I~//~9B29R~
        return rc;                                                 //~9B28I~
    }                                                              //~9B28I~
    //*************************************************************************//~9B28I~
    //*from Players at isYourTurn befor Ron executed               //~9B28I~
    //*************************************************************************//~9B28I~
    public boolean isDupRonOK2Touch(int Pplayer)                    //~9B28I~
    {                                                              //~9B28I~
    	boolean rc=UAD2T.isDupRonable2Touch(Pplayer);              //~9B29R~
        if (Dump.Y) Dump.println("UADelayed2.isDupRonOK2Touch rc="+rc+",player="+Pplayer);//~9B28I~//~9B29R~
        return rc;                                                 //~9B28I~
    }                                                              //~9B28I~
    //*************************************************************************//~9B28I~
    //*from UADiscrd at discard and timeoutPonKan exausted         //~9B28I~
    //*************************************************************************//~9B28I~
    public void setRonable(boolean PswRonable)                   //~9B28I~
    {                                                              //~9B28I~
        if (Dump.Y) Dump.println("UADelayed2.setRonable sw="+PswRonable);//~9B28I~
        swRonable=PswRonable;                                      //~9B28I~
    }                                                              //~9B28I~
//    //*************************************************************************//~9B18M~//~9B27R~
//    //*From UserAction.action  on Client by GCM_2TOUCH             //~9B18M~//~9B27R~
//    //*set msgToClient if rc=true on client                        //~9B18I~//~9B27R~
//    //*************************************************************************//~9B18M~//~9B27R~
//    public boolean selectInfo2Touch(boolean PswServer,int Pplayer,int[] PintParm)//~9B18M~//~9B27R~
//    {                                                              //~9B18M~//~9B27R~
//        if (swStop)                                                //~9B20I~//~9B27R~
//        {                                                          //~9B20I~//~9B27R~
//            if (Dump.Y) Dump.println("UADelayed2.selectInfo2Touch return by swStop");//~9B20I~//~9B27R~
//            return false;                                          //~9B20I~//~9B27R~
//        }                                                          //~9B20I~//~9B27R~
//        if (Dump.Y) Dump.println("UADelayed2.selectInfo2Touch swServer="+PswServer+",player="+Pplayer+",intParm="+ Arrays.toString(PintParm));//~9B18R~//~9B27R~
//        boolean rc=false;   //goto action                          //~9B18R~//~9B27R~
//        int actionID=PintParm[PARMPOS_ACTION_2TOUCH];              //~9B18M~//~9B27R~
//        int statusAction=PintParm[PARMPOS_ACTION_2TOUCH_STATUS];   //~9B18M~//~9B27R~
//        UA.setNoMsgToServer();                                     //~9B18I~//~9B27R~
//        UA.setNoMsgToClient();                                     //~9B18I~//~9B27R~
//        switch(statusAction)                                       //~9B18M~//~9B27R~
//        {                                                          //~9B18M~//~9B27R~
//        case STAT_CONFIRM_REQ: //on server by GC button            //~9B18I~//~9B27R~
////          if (!PswServer)                                        //~9B18I~//~9B22R~//~9B27R~
////              sendToServer2Touch(actionID,Pplayer,STAT_CONFIRMED_CANCEL); //to action2Touch on server//~9B18I~//~9B22R~//~9B27R~
//            rc=true;    //goto action2Touch, send CONFIRM_REQ      //~9B18I~//~9B27R~
//            break;                                                 //~9B18I~//~9B27R~
//                                                                  //~9B18I~//~9B27R~
//        case STAT_CONFIRM_CANCEL: //on the device                  //~9B18I~//~9B27R~
//            if (!PswServer)                                        //~9B18I~//~9B27R~
////              sendToServer2Touch(actionID,Pplayer,STAT_CONFIRMED_CANCEL); //to action2Touch on server//~9B18I~//~9B26R~//~9B27R~
//                sendToServer2Touch(actionID,Pplayer,STAT_CONFIRMED_CANCEL,0/*parm:dummy*/); //to action2Touch on server//~9B26I~//~9B27R~
//            rc=true;    //goto action2Touch, send CONFIRMED_CANCEL //~9B18I~//~9B27R~
//            break;                                                 //~9B18I~//~9B27R~
//        case STAT_CONFIRMED_CANCEL: //on server                    //~9B18I~//~9B27R~
//            rc=true;    //goto action2Touch, send CONFIRMED_CANCEL //~9B18I~//~9B27R~
//            break;                                                 //~9B18I~//~9B27R~
//        }                                                          //~9B18M~//~9B27R~
//        if (Dump.Y) Dump.println("UADelayed2.selectInfo2Touch rc="+rc);//~9B18I~//~9B27R~
//        return rc;                                                 //~9B18I~//~9B27R~
//    }                                                              //~9B18M~//~9B27R~
	//*************************************************************************//~9B27I~
	//*By Pon,KAn,Chii,Ron and Cancel Btn                                               //~9B27I~//~9C04R~
	//*************************************************************************//~9B27I~
    public boolean selectInfo2Touch(boolean PswServer,int Pplayer,int[] PintParm)//~9B27I~
    {                                                              //~9B27I~
    	if (swStop)                                                //~9B27I~
        {                                                          //~9B27I~
	    	if (Dump.Y) Dump.println("UADelayed2.selectInfo2Touch return by swStop");//~9B27I~
        	return false;                                          //~9B27I~
        }                                                          //~9B27I~
        int actionID=PintParm[PARMPOS_ACTION_2TOUCH];              //~9B27I~
        int statusAction=PintParm[PARMPOS_ACTION_2TOUCH_STATUS];   //~9B27I~
        if (Dump.Y) Dump.println("UADelayed2.selectInfo2Touch swServer="+PswServer+",player="+Pplayer+",status="+statusAction+",intParm="+ Arrays.toString(PintParm));//~9B27R~
        boolean rc=false;	//goto action                          //~9B27I~
	    UA.setNoMsgToServer();                                     //~9B27I~
	    UA.setNoMsgToClient();                                     //~9B27I~
        switch(statusAction)                                       //~9B27I~
        {                                                          //~9B27I~
        case STAT_CONFIRM_REQ: //on server by GC button            //~9B28I~
            rc=true;    //goto action2Touch                        //~9B28I~
            break;                                                 //~9B28I~
        case STAT_CONFIRM_CANCEL: //on the device                  //~9B27I~
        	if (PswServer)                                         //~9B27I~
	            rc=true;	//goto action2Touch, send CONFIRMED_CANCEL//~9B27I~
            else                                                   //~9B27I~
                sendToServer2Touch(actionID,Pplayer,STAT_CONFIRM_CANCEL,0/*parm:dummy*/,0);	//to action2Touch on server//~9B27I~
            break;                                                 //~9B27I~
        }                                                          //~9B27I~
        if (Dump.Y) Dump.println("UADelayed2.selectInfo2Touch rc="+rc);//~9B27I~
        return rc;                                                 //~9B27I~
    }                                                              //~9B27I~
    //*************************************************************************//~9B27I~
    //*From UserAction.action  by GCM_2TOUCH Server/Client         //~9B27I~
    //*************************************************************************//~9B27I~
    public boolean action2Touch(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9B27I~
    {                                                              //~9B27I~
    	int flag;                                                  //~9B28I~
    //************************                                     //~9B28I~
        if (swStop)                                                //~9B27I~
        {                                                          //~9B27I~
            if (Dump.Y) Dump.println("UADelayed2.action2Touch return by swStop");//~9B27I~
            return false;                                          //~9B27I~
        }                                                          //~9B27I~
        if (Dump.Y) Dump.println("UADelayed2.action2Touch swServer="+PswServer+",player="+Pplayer+",intParm="+Arrays.toString(PintParm));//~9B27I~
        boolean rc=true;                                           //~9B27I~
        int actionID=PintParm[PARMPOS_ACTION_2TOUCH];              //~9B27I~
        int statusAction=PintParm[PARMPOS_ACTION_2TOUCH_STATUS];   //~9B27I~
        UA.setNoMsgToServer();                                     //~9B27I~
        UA.setNoMsgToClient();                                     //~9B27I~
        switch(statusAction)                                       //~9B27I~
        {                                                          //~9B27I~
        case STAT_CONFIRM_REQ: //on server                         //~9B27I~
	        flag=PintParm[PARMPOS_ACTION_2TOUCH_PARM_FLAG];        //~9B28R~
            if ((flag & PARM_FLAG_SCHEDULED_NEXTTOP_MULTISELECT)!=0)    //~9C10I~
            {                                                      //~9C10I~
	        	postDelayedBlockTimeout(0/*add delay*/,actionID,Pplayer,flag);//~9C10I~
            	break;                                             //~9C10I~
            }                                                      //~9C10I~
            int eswnDiscarder=PintParm[PARMPOS_ACTION_2TOUCH_PARM_ESWN];//~9B27I~//~9B28M~
            int eswnCurrent=Accounts.getCurrentPlayerEswn();       //~9B27I~
            if (Dump.Y) Dump.println("UADelayed2.action2Touch STAT_CONFIRM_REQ parm eswnDiscarder="+eswnDiscarder+",eswnCurrentPlayer="+eswnCurrent);//~9B27I~
            if (eswnDiscarder!=eswnCurrent) //another action overtaken//~9B27I~
            {                                                      //~9B27I~
                UserAction.showInfoToThePlayer(GMSGOPT_ANDTOAST,R.string.Warning_ActionOverTaken,Pplayer);//~9B27I~
                rc=false;                                          //~9B27I~
                break;                                             //~9B27I~
            }                                                      //~9B27I~
//        if (!AG.swPlayAloneNotify)	//no timeout for playalone notify mode//~va70R~
          if (!AG.swPlayAloneNotify)	//no need, avoid cost of timer thread//+vab0I~
            startTimer();   //to release blocked msg at canceled   //~9B27I~
//          setActionBlocked(PswServer,actionID,Pplayer,0/*status:not used*/);//~9B27I~//~9B28R~
            setActionBlocked(PswServer,actionID,Pplayer,flag);     //~9B28I~
            UA.msgDataToClient=makeMsgData2Touch(actionID,Pplayer,STAT_BLOCKED,flag);//to action2Touch//~9B27R~//~9B28R~
//          postDelayedBlockTimeout(actionID,Pplayer,flag);               //~9C05I~//~9C07R~
            break;                                                 //~9B27I~
        case STAT_BLOCKED:	//on client                            //~9B27I~
	        flag=PintParm[PARMPOS_ACTION_2TOUCH_PARM_FLAG];        //~9B28I~
            if (Dump.Y) Dump.println("UADelayed2.action2Touch STAT_BLOCKED flag="+flag);//~9B28I~
            setActionBlockedClient(actionID,Pplayer,flag);              //~9B27R~//~9B28R~
            break;                                                 //~9B27I~
        case STAT_CONFIRM_CANCEL: //on server                      //~9B27I~
            actionCanceled(actionID,PswServer,PswReceived,Pplayer); //release block and send CONFIRMED_CANCEL to client//~9B27I~
            break;                                                 //~9B27I~
        case STAT_CONFIRMED_OK: //on Client                        //~9B27I~
//  		actionDone(actionID,PswServer,Pplayer);                //~9B27I~//~9B29R~
            break;                                                 //~9B27I~
        case STAT_CONFIRMED_CANCEL: //on client                    //~9B27I~
            actionCanceled(actionID,PswServer,PswReceived,Pplayer);//~9B27I~
            break;                                                 //~9B27I~
        case STAT_CONFIRMED_CANCEL_TIMEOUT: //on client            //~9C05I~
            actionCanceledTimeout(actionID,PswServer,PswReceived,Pplayer);//~9C05I~
            break;                                                 //~9C05I~
        }                                                          //~9B27I~
        return rc;                                                 //~9B27I~
    }                                                              //~9B27I~
    //*************************************************************************//~9B23I~
    public void actionCanceled(int PactionID,boolean PswServer,boolean PswReceived,int Pplayer)//~9B23I~
    {                                                              //~9B23I~
        Message msg=msgWaiting;                                    //~9B23I~
        if (Dump.Y) Dump.println("UADelayed2.actionCanceled actionID="+PactionID+",swServer="+PswServer+",player="+Pplayer+",msgWaiting="+ Utils.toString(msgWaiting));//~9B23I~
        removePendingMsg2Touch();                                  //~9B23I~
        releaseActionBlocked(PswServer,PactionID,Pplayer);          //~9B23I~
        AG.aHandsTouch.resetSelection();                           //~9C01I~
//        boolean swNextTop=false;                                   //~9C07I~
        if (PswServer)                                             //~9B23I~
        {                                                          //~9B23I~
        	UA.msgDataToClient=makeMsgData2Touch(PactionID,Pplayer,STAT_CONFIRMED_CANCEL);//~9B23I~
            if (msg!=null && msgWaiting==null)	//clear by removePendingMsg2Touch, :GCM_NEXT_PLAYER_AUTODISCARD,AUTOTAKE//~9B23I~
            	rescheduleMsg2Touch(msg);                          //~9B23I~
//            Rect r=UAD2T.getTopBlockerPlayer();                    //~9C07R~//~9C10R~
//            if (r!=null)                                           //~9C07R~//~9C10R~
//            {                                                      //~9C07I~//~9C10R~
//                if (r.left==PLAYER_YOU)                            //~9C07R~//~9C10R~
//                    swNextTop=true;                                //~9C07I~//~9C10R~
//            }                                                      //~9C07I~//~9C10R~
        }                                                          //~9B23I~
//  	boolean swNextTop=scheduleNextTop(PactionID,PswServer);    //~9C10R~
    	int newTop=scheduleNextTop(PactionID,PswServer);           //~9C10I~
//      if (swNextTop)                                             //~9C07I~//~9C10R~
        if (newTop==1)                                             //~9C10I~
	    	UA.showInfo(0,R.string.Info_BlockCanceledYouAreNewTop);//~9C07I~
        else                                                       //~9C07I~
        if (newTop==0)                                             //~9C10I~
	    	UA.showInfo(0,R.string.Info_BlockCanceled);            //~9C07I~
    }                                                              //~9B23I~
    //*************************************************************************//~va70I~
    //*reschedule pending msg                                      //~va70I~
    //*************************************************************************//~va70I~
    public void actionCanceledPlayAlone(int PactionID)             //~va70I~
    {                                                              //~va70I~
        Message msg=msgWaiting;                                    //~va70I~
        if (Dump.Y) Dump.println("UADelayed2.actionCanceledPlayAlone actionID="+PactionID+",msgWaiting="+ Utils.toString(msgWaiting));//~va70I~
        removePendingMsg2Touch();                                  //~va70I~
        releaseActionBlocked(true/*PswServer*/,PactionID,PLAYER_YOU);//~va70I~
//      AG.aHandsTouch.resetSelection();                           //~va70I~
//      if (PswServer)                                             //~va70I~
//      {                                                          //~va70I~
//      	UA.msgDataToClient=makeMsgData2Touch(PactionID,Pplayer,STAT_CONFIRMED_CANCEL);//~va70I~
            if (msg!=null && msgWaiting==null)	//clear by removePendingMsg2Touch, :GCM_NEXT_PLAYER_AUTODISCARD,AUTOTAKE//~va70I~
            	rescheduleMsg2Touch(msg);                          //~va70I~
//      }                                                          //~va70I~
//  	int newTop=scheduleNextTop(PactionID,PswServer);           //~va70I~
//      if (newTop==1)                                             //~va70I~
//      	UA.showInfo(0,R.string.Info_BlockCanceledYouAreNewTop);//~va70I~
//      else                                                       //~va70I~
//      if (newTop==0)                                             //~va70I~
//      	UA.showInfo(0,R.string.Info_BlockCanceled);            //~va70I~
    }                                                              //~va70I~
    //*************************************************************************//~9C05I~
    private void actionCanceledTimeout(int PactionID,boolean PswServer,boolean PswReceived,int Pplayer)//~9C05I~//~va60R~
    {                                                              //~9C05I~
        Message msg=msgWaiting;                                    //~9C05I~
        if (Dump.Y) Dump.println("UADelayed2.actionCanceledTimeout actionID="+PactionID+",swServer="+PswServer+",player="+Pplayer+",msgWaiting="+ Utils.toString(msgWaiting));//~9C05R~
        removePendingMsg2Touch();                                  //~9C05I~
        releaseActionBlocked(PswServer,PactionID,Pplayer);         //~9C05I~
        AG.aHandsTouch.resetSelection();                           //~9C05I~
        if (PswServer)                                             //~9C05I~
        {                                                          //~9C05I~
            if (msg!=null && msgWaiting==null)	//clear by removePendingMsg2Touch, :GCM_NEXT_PLAYER_AUTODISCARD,AUTOTAKE//~9C05I~
            	rescheduleMsg2Touch(msg);                          //~9C05I~
	    	sendToClient2Touch(PactionID,Pplayer,STAT_CONFIRMED_CANCEL_TIMEOUT);//~9C05I~
        }                                                          //~9C05I~
//        Rect r=UAD2T.getTopBlockerPlayer();   //player,eswn-action,flag//~9C07R~
//        if (r!=null)                                             //~9C07R~
//        {                                                        //~9C07R~
//            if (PswServer)                                       //~9C07R~
//                postDelayedBlockTimeout(DELY_SCHEDULE2ND,r.right/*action*/,r.left/*player*/,r.bottom/*flag*/);//~9C07R~
//            if (r.left==PLAYER_YOU)                              //~9C07R~
//                swNextTop=true;                                  //~9C07R~
//        }                                                        //~9C07R~
//  	boolean swNextTop=scheduleNextTop(PactionID,PswServer);           //~9C07I~//~9C10R~
    	int newTop=scheduleNextTop(PactionID,PswServer);           //~9C10I~
//      if (Pplayer==PLAYER_YOU)                                   //~9C05I~//~9C07R~
//  	int opt=GMSGOPT_ANDTOAST;                                  //~9C10R~
    	int opt=0;                                                 //~9C10I~
//      if (swNextTop)                                             //~9C07I~//~9C10R~
        if (newTop==1)  //now top                                  //~9C10R~
	    	UA.showInfo(opt,R.string.Info_BlockTimeoutYouAreNewTop); //~9C07I~//~9C10R~
//      else                                                       //~9C07I~//~9C10R~
        if (newTop!=0)  //not 2nd                                  //~9C10R~
	    	UA.showInfo(opt,R.string.Info_BlockTimeout);              //~9C05I~//~9C10R~
    }                                                              //~9C05I~
    //*************************************************************************//~9C07I~
    //*rc:-1:dup cancel(btn and timeout),0:not you, 1: your are new top//~9C10I~
    //*************************************************************************//~9C10I~
//  private boolean scheduleNextTop(int PactionID/*1stBlockerAction*/,boolean PswServer)//~9C07I~//~9C10R~
    private int scheduleNextTop(int PactionID/*1stBlockerAction*/,boolean PswServer)//~9C10I~
    {                                                              //~9C07I~
        if (Dump.Y) Dump.println("UADelayed2.scheduleNextTop swServer="+PswServer);//~9C07I~
        Rect r=UAD2T.getSetTopBlockerPlayer();   //reset flag rescheduled//~9C10R~
        if (r==null)                                               //~9C07I~
        {                                                          //~9C10I~
        	if (Dump.Y) Dump.println("UADelayed2.scheduleNextTop already scheduled");//~9C10I~
//      	return false;                                          //~9C07I~//~9C10R~
        	return -1;                                             //~9C10I~
        }                                                          //~9C10I~
        int nextPlayer=r.left;                                         //~9C07I~
        int newAction=r.right;                                         //~9C07I~
//  	boolean swCancelable=is2TouchModeCancelable(PactionID);    //~9C07I~//~9C10R~
    	boolean swCancelable=is2TouchModeCancelable(newAction);    //~9C10I~
        if (swCancelable)                                          //~9C07I~
        {                                                          //~9C07I~
        	if (PswServer)                                         //~9C07I~
	            postDelayedBlockTimeout(0,newAction,nextPlayer,r.bottom/*flag*/);//~9C07I~
        }                                                          //~9C07I~
        else  //waiting multi selection                            //~9C07I~
        {                                                          //~9C07I~
        	if (nextPlayer==PLAYER_YOU)                            //~9C07I~
            {                                                      //~9C10I~
//  	        GVH.sendMsg(newAction,null);                       //~9C07I~//~9C10R~
    	        sendMsgDelayed(newAction,DELY_SCHEDULE2ND,nextPlayer,STAT_RESCHEDULED_NEXTTOP); //=7, =1000;  //delay for 2nd blocker action//~9C10R~
            }                                                      //~9C10I~
        }                                                          //~9C07I~
//      boolean rc=nextPlayer==PLAYER_YOU;                         //~9C07I~//~9C10R~
        int rc=nextPlayer==PLAYER_YOU ? 1 :0 ;                      //~9C10I~
        if (Dump.Y) Dump.println("UADelayed2.scheduleNextTop rc="+rc);//~9C07I~
        return rc;                                                 //~9C07I~
	}                                                              //~9C07I~
    //*************************************************************************//~9B21I~
    private void actionDone(int PactionID,boolean PswServer,int Pplayer)//~9B21I~//~9B24R~//~9B27R~//~va60R~
    {                                                              //~9B21I~
        if (Dump.Y) Dump.println("UADelayed2.actionDone actionID="+PactionID+",swServer="+PswServer+",player="+Pplayer);//~9B21I~
    	if (swStop)                                                //~9B21I~
        {                                                          //~9B21I~
	    	if (Dump.Y) Dump.println("UADelayed2.actionDone return by swStop");//~9B21I~
        	return;                                                //~9B21I~
        }                                                          //~9B21I~
//      if (!sw2Touch)                                             //~9B21I~//~9C04R~
//      if (!is2TouchMode(PactionID))                              //~9C04R~
//      if (!isBlockMode(PactionID))                               //~9C04I~//~va60R~
        if (!isBlockMode(PactionID,PswServer,Pplayer))             //~va60I~
        {                                                          //~9B21I~
        	if (Dump.Y) Dump.println("UADelayed2.actionDone not 2touchMode");//~9B21I~//~va60R~
        	return;                                                //~9B21I~
        }                                                          //~9B21I~
        removePendingMsg2Touch();                                  //~9B22I~
        releaseActionBlockedAll(PswServer,PactionID,Pplayer);  //~9B21R~//~9B24R~
        if (swStopAuto2Touch)	//yet stoppd                       //~9C11I~
        {                                                          //~9C11I~
        	if (UAD2T.is2ndTouch())	//yourStatus=TOP               //~9C11I~
            {                                                      //~9C11I~
                int opt=GMSGOPT_ANDTOAST;                          //~9C11I~
	            if (UAD2T.swSettingMultiRon)                       //~9C11I~
		    		UA.showInfo(opt,R.string.Info_MultiRonYouAreNewTopAll);//~9C11R~
                else                                               //~9C11I~
		    		UA.showInfo(opt,R.string.Info_MultiRonYouAreNewTopEswn);//~9C11R~
            }                                                      //~9C11I~
        }                                                          //~9C11I~
        if (PswServer)                                             //~9B21I~
	    	sendToClient2Touch(PactionID,Pplayer,STAT_CONFIRMED_OK);//~9B21R~
    }                                                              //~9B21I~
    //*****************************************************************//~9B21I~
    //*                                                            //~9B21I~
    //*****************************************************************//~9B21I~
    private void updateActionAlert(int PactionID,int Pstatus)                     //~9B21I~//~9B25R~//~9B26R~
    {                                                              //~9B21I~
		if (Dump.Y) Dump.println("UADelayed2.updateActionAlert actionID="+PactionID+",status="+Pstatus);//~9B21I~//~9B25R~
//        if (swBtn2Touch)                                           //~9B25I~//~9B26R~
//            UAD2T.updateBtn(PactionID,Pstatus);                       //~9B25I~//~9B26R~//~9B28R~
//        else                                                       //~9B25I~//~9B26R~
	        ActionAlert.updateStatus(Pstatus);                         //~9B21I~//~9B25R~
    }                                                              //~9B21I~
    //*****************************************************************//~9B24I~
    //*                                                            //~9B24I~
    //*****************************************************************//~9B24I~
    public void dismissActionAlert()                               //~9B24I~
    {                                                              //~9B24I~
		if (Dump.Y) Dump.println("UADelayed2.dismissActionAlert"); //~9B24I~
        ActionAlert.doDismiss();                                   //~9B24I~
    }                                                              //~9B24I~
//    //*****************************************************************//~9B16I~//~9B17M~//~9B28R~
//    //*confirm action by ActionAlert                               //~9B25R~//~9B28R~
//    //*****************************************************************//~9B16I~//~9B17M~//~9B28R~
//    public void popupConfirm(int PactionID,int Pstatus)                        //~9B16R~//~9B17R~//~9B28R~
//    {                                                              //~9B16I~//~9B17M~//~9B28R~
//        if (Dump.Y) Dump.println("UADelayed2.popupConfirm actionID="+PactionID+",status="+Pstatus);//~9B16R~//~9B17R~//~9B28R~
//        if (swBtn2Touch)                                           //~9B25I~//~9B28R~
//            UAD2T.updateBtn(PactionID,Pstatus);                       //~9B25I~//~9B26R~//~9B28R~
//        else                                                       //~9B26I~//~9B28R~
//            showAlert(PactionID,Pstatus);                                     //~9B16R~//~9B17R~//~9B26R~//~9B28R~
//    }                                                              //~9B16I~//~9B17M~//~9B28R~
    //*****************************************************************//~9B16I~//~9B17M~
    public void showAlert(int PactionID,int Pstatus)                           //~9B16R~//~9B17R~
    {                                                              //~9B16I~//~9B17M~
		if (Dump.Y) Dump.println("UADelayed2.showAlert actionID="+PactionID+",status="+Pstatus);//~9B16R~//~9B17R~
        ActionAlert.show(this,PactionID,Pstatus);                          //~9B16R~//~9B17R~//~9B19R~
    }                                                              //~9B16I~//~9B17M~
    //*******************************************************      //~9B16I~//~9B17M~
    @Override   //AlertI                                           //~9B16I~//~9B17M~
	public int alertButtonAction(int Pbuttonid,int PactionID)      //~9B16R~//~9B17M~
    {                                                              //~9B16I~//~9B17M~
        if (Dump.Y) Dump.println("UADelayed2.alertButtonAction buttonID="+Pbuttonid+",actionID="+PactionID);//~9B16R~//~9B17M~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9B16I~//~9B17M~
        {                                                          //~9B16I~//~9B17M~
	        if (Dump.Y) Dump.println("UADelayed2.alertButtonAction positive");//~9B16I~//~9B17M~
			actionAlertOKNG(PactionID,true);                        //~9B17M~//~9B23R~
        }                                                          //~9B16I~//~9B17M~
        else                                                       //~9B16I~//~9B17M~
    	if (Pbuttonid==BUTTON_NEGATIVE)                            //~9B16I~//~9B17M~
        {                                                          //~9B16I~//~9B17M~
	        if (Dump.Y) Dump.println("UADelayed2.alertButtonAction negative");//~9B16I~//~9B17M~
			actionAlertOKNG(PactionID,false);                       //~9B17M~//~9B23R~
        }                                                          //~9B16I~//~9B17M~
        return 0;                                                  //~9B17M~
    }                                                              //~9B16I~//~9B17M~
	//*************************************************************************//~9B17M~
	//*On UIThread on the device send GVH msg by dialog button     //~9B17I~
	//*************************************************************************//~9B17I~
    private void actionAlertOKNG(int PactionID,boolean PswOKNG)    //~9B17M~//~9B23R~
    {                                                              //~9B17M~
        if (Dump.Y) Dump.println("UADelayed2.actionAlertOKNG actionID="+PactionID+",swOKNG="+PswOKNG);//~9B17I~//~9B23R~
        if (PswOKNG)                                                 //~9B18I~
			sendMsgAction(PactionID,PLAYER_YOU,STAT_ACTION_2TOUCH);	//go to nomal action//~9B18I~
        else                                                       //~9B18I~
    		sendMsg2Touch(PactionID,PLAYER_YOU,STAT_CONFIRM_CANCEL);//~9B18R~
    }                                                              //~9B17M~
    //*************************************************************************//~9B26I~
    //*On Serve/Client by OK button onActionAlert                  //~9B26I~
    //*************************************************************************//~9B26I~
    public void actionCancelBtn(int PactionID)                     //~9B26I~
    {                                                              //~9B26I~
        if (Dump.Y) Dump.println("UADelayed2.actionCancelBtn actionID="+PactionID);//~9B26I~
    	sendMsg2Touch(PactionID,PLAYER_YOU,STAT_CONFIRM_CANCEL);   //~9B26I~
    }                                                              //~9B26I~
//    //*************************************************************************//~9B17I~//~9B18R~
//    private boolean selectInfoAction(int PactionID,boolean PswServer,int Pplayer)//~9B17I~//~9B18R~
//    {                                                              //~9B17I~//~9B18R~
//        if (Dump.Y) Dump.println("UADelayed2.selectInfoAction actionID="+PactionID+",player="+Pplayer);//~9B17I~//~9B18R~
//        boolean rc=false;                                        //~9B18R~
//        switch(PactionID)                                          //~9B17I~//~9B18R~
//        {                                                          //~9B17I~//~9B18R~
//        case GCM_PON_C:                                            //~9B17I~//~9B18R~
//            rc=UA.UAP.selectInfo2Touch(PswServer,Pplayer); //TODO  //~9B17R~//~9B18R~
//            break;                                                 //~9B17I~//~9B18R~
//        }                                                          //~9B17I~//~9B18R~
//        return rc;                                               //~9B18R~
//    }                                                              //~9B17I~//~9B18R~
	//*************************************************************************//~9B27I~
	//*block autoTakeDiscard, if dup enq request                   //~9B27I~
	//*************************************************************************//~9B27I~
    private void setActionBlockedClient(int PactionID,int Pplayer,int Pflag)  //~9B27I~//~9B28R~
    {                                                              //~9B27I~
        if (Dump.Y) Dump.println("UADelayed2.setActionBlocked actionID="+PactionID+",player="+Pplayer);//~9B27I~
//      int rc=setActionBlocked(false/*swServer*/,PactionID,Pplayer,0);//~9B27I~//~9B28R~
        setActionBlocked(false/*swServer*/,PactionID,Pplayer,Pflag);//~9B28R~
//        UAD2T.updateBtn(PactionID,rc);                               //~9B27I~//~9B28R~
    }                                                              //~9B27I~
	//*************************************************************************//~9B17I~
	//*block autoTakeDiscard, if dup enq request                   //~9B18I~
	//*************************************************************************//~9B18I~
//    private int setActionBlocked(boolean PswServer,int PactionID,int Pplayer,int Pstatus)        //~9B17I~//~9B18R~//~9B28R~
    private void setActionBlocked(boolean PswServer,int PactionID,int Pplayer,int Pflag)//~9B28R~
    {                                                              //~9B17I~
        if (Dump.Y) Dump.println("UADelayed2.setActionBlocked actionID="+PactionID+",player="+Pplayer+",flag="+Pflag);//~9B17I~//~9B18R~//~9B28R~
//      int rc;                                                    //~9B18I~//~9B28R~
        setWaiterLight(Pplayer,true/*swOn*/);	                   //~9B19I~
        if (PswServer)                                             //~9B18I~
        {                                                          //~9B18I~
//            if (timeoutAutoDiscard==0 && timeoutAutoTake==0)       //~9B18I~//~9B28R~
//            {                                                      //~9B18I~//~9B28R~
//                if (Dump.Y) Dump.println("UADelayed2.stopAutoServer no autoTakeDiscard timeout set");//~9B18I~//~9B28R~
//                return 0;                                          //~9B18I~//~9B28R~
//            }                                                      //~9B18I~//~9B28R~
//          rc=stopAuto2Touch(PactionID,Pplayer);                        //~9B18I~//~9B19R~//~9B28R~
            stopAuto2Touch(PactionID,Pplayer,Pflag);               //~9B28R~
            if (UAD2T.getTopBlockerAction(Pplayer)!=0)             //~9C07R~
	        	postDelayedBlockTimeout(0,PactionID,Pplayer,Pflag);//~9C07R~
        }                                                          //~9B18I~
        else                                                       //~9B18I~
        {                                                          //~9B18I~
//      	rc=stopAuto2Touch(PactionID,Pplayer);                           //~9B18I~//~9B19R~//~9B27R~//~9B28R~
        	stopAuto2Touch(PactionID,Pplayer,Pflag);               //~9B28R~
        }                                                          //~9B18I~
        if (Dump.Y) Dump.println("UADelayed2.setActionBlocked swStopAuto2Touch="+swStopAuto2Touch);//~9B18I~
//      return rc;                                                 //~9B17I~//~9B28R~
    }                                                              //~9B17I~
	//*************************************************************************//~9B18I~
    private int stopAuto2Touch(int PactionID,int Pplayer,int Pflag)                //~9B18I~//~9B19R~//~9B21R~//~9B28R~
    {                                                              //~9B18I~
        int rc=0;                                                //~9B18I~//~9B27R~//~9B28R~
        if (Dump.Y) Dump.println("UADelayed2.stopAuto2Touch actionID="+PactionID+",player="+Pplayer+",flag="+Pflag);//~vaa2I~
//        int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~9B18I~//~9B28R~
//        synchronized(blockerAction)                                //~9B18I~//~9B28R~
//        {                                                          //~9B18I~//~9B28R~
//            if (blockerAction[eswn]==0)                            //~9B18I~//~9B28R~
//            {                                                      //~9B18I~//~9B28R~
//                if (blockerCtr!=0)                                 //~9B18I~//~9B28R~
//                    rc=STAT_BLOCKED_ALREADY;                       //~9B18I~//~9B28R~
//                else                                               //~9B18I~//~9B28R~
//                    rc=STAT_BLOCKED_NOW;                           //~9B18I~//~9B28R~
//                blockerAction[eswn]=PactionID;                     //~9B18I~//~9B28R~
//                blockerActionQ[blockerCtr++]=eswn;                 //~9B19I~//~9B28R~
//                stopAuto2Touch=true;                                   //~9B18I~//~9B24I~//~9B28R~
//            }                                                      //~9B18I~//~9B28R~
//            else                                                   //~9B24I~//~9B28R~
//            {                                                      //~9B27I~//~9B28R~
//                rc=STAT_BLOCKED_2TOUCH;                            //~9B27R~//~9B28R~
//                if (Dump.Y) Dump.println("UADelayed2.stopAuto2Touch !!!dup block request");//~9B24I~//~9B28R~
//            }                                                      //~9B27I~//~9B28R~
//        }                                                          //~9B18I~//~9B28R~
//        if (Dump.Y) Dump.println("UADelayed2.stopAuto2Touch rc="+rc+",stopauto2Touch="+stopAuto2Touch+",eswn="+eswn+",blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction)+",actionQ="+Arrays.toString(blockerActionQ));//~9B18I~//~9B19R~//~9B24R~//~9B28R~
//      UAD2T.stopAuto2Touch(PactionID,Pplayer);                   //~9B28R~
        UAD2T.stopAuto2Touch(PactionID,Pplayer,Pflag);             //~9B28I~
        swStopAuto2Touch=true;                                     //~9B28I~
        return rc;                                                 //~9B18I~
    }                                                              //~9B18I~
	//*************************************************************************//~9B17I~
	//*on all by Cancel Button                                                      //~9B21I~//~9B24R~
	//*************************************************************************//~9B21I~
    private int releaseActionBlocked(boolean PswServer,int PactionID,int Pplayer)    //~9B17I~//~9B18R~
    {                                                              //~9B17I~
        if (Dump.Y) Dump.println("UADelayed2.releaseActionBlocked actionID="+PactionID+",player="+Pplayer);//~9B17I~
        int rc=0;                                                  //~9B18I~//~9B28R~
        setWaiterLight(Pplayer,false/*swOn*/);                     //~9B19I~
//        rc=releaseAuto2Touch(PactionID,Pplayer);                   //~9B23R~//~9B28R~
////        if (PswServer)                                             //~9B18I~//~9B21M~//~9B24R~//~9B28R~
////        {                                                          //~9B18I~//~9B21M~//~9B24R~//~9B28R~
////            if (timeoutAutoDiscard==0 && timeoutAutoTake==0)       //~9B18I~//~9B21M~//~9B24R~//~9B28R~
////            {                                                      //~9B18I~//~9B21M~//~9B24R~//~9B28R~
////                if (Dump.Y) Dump.println("UADelayed2.stopAutoServer no autoTakeDiscard timeout set");//~9B18I~//~9B21M~//~9B24R~//~9B28R~
////                return 0;                                          //~9B18I~//~9B21M~//~9B24R~//~9B28R~
////            }                                                      //~9B18I~//~9B21M~//~9B24R~//~9B28R~
////        }                                                          //~9B18I~//~9B21M~//~9B24R~//~9B28R~
//        if (swBtn2Touch)                                           //~9B26I~//~9B28R~
//        {                                                          //~9B26I~//~9B28R~
//            UAD2T.updateBtn(PactionID,rc);                           //~9B26R~//~9B28R~
////            if (rc==STAT_BLOCKER_YOU)      //now new first blocker//~9B26R~//~9B28R~
////                UAD2T.updateBtn(actionNewBlocker,STAT_BLOCKER_YOU);//~9B26R~//~9B28R~
//        }                                                          //~9B26I~//~9B28R~
//        else                                                       //~9B26I~//~9B28R~
//        if (Pplayer!=PLAYER_YOU)                                   //~9B23I~//~9B28R~
//            updateActionAlert(PactionID,rc);                                 //~9B21I~//~9B22R~//~9B23R~//~9B25R~//~9B28R~
    	releaseAuto2Touch(PactionID,Pplayer);	//all released     //~9B28R~
        return rc;//~9B18I~
    }                                                              //~9B18I~
	//*************************************************************************//~9B24I~
	//*from actionDone, on all by Execute Button                                    //~9B24I~//~9B28R~
	//*rc:multiron available                                       //~9B28I~//~9C11R~
	//*************************************************************************//~9B24I~
    private boolean releaseActionBlockedAll(boolean PswServer,int PactionID,int Pplayer)//~9B24I~//~9B28R~
    {                                                              //~9B24I~
        if (Dump.Y) Dump.println("UADelayed2.releaseActionBlockedAll actionID="+PactionID+",player="+Pplayer);//~9B24R~
//        for (int ii=0;ii<blockerCtr;ii++)                          //~9B24I~//~9B28R~
//        {                                                          //~9B24I~//~9B28R~
//            int eswn=blockerActionQ[ii];                           //~9B24I~//~9B28R~
//            int player=Accounts.eswnToPlayer(eswn);                //~9B24I~//~9B28R~
//            if (Dump.Y) Dump.println("UADelayed2.releaseActionBlockedAll eswn="+eswn+",player="+player);//~9B24I~//~9B28R~
//            setWaiterLight(player,false/*swOn*/);                  //~9B24I~//~9B28R~
//        }                                                          //~9B24I~//~9B28R~
        boolean rc=resetAuto2Touch(PactionID,Pplayer);                                         //~9B24I~//~9B28R~
//        if (swBtn2Touch)                                           //~9B26I~//~9B28R~
//            UAD2T.releasedAll(PactionID);                            //~9B26I~//~9B28R~
//        else                                                       //~9B26I~//~9B28R~
//        if (Pplayer!=PLAYER_YOU)                                   //~9B24I~//~9B28R~
//            dismissActionAlert();                                  //~9B24R~//~9B25R~//~9B26R~//~9B28R~
        if (Dump.Y) Dump.println("UADelayed2.releaseActionBlockedAll swStopAuto2Touch="+swStopAuto2Touch+",rc="+rc);//~9B24I~//~9B28R~
        return rc;
    }                                                              //~9B24I~
    //*************************************************************************//~9B18I~//~9B28R~
//  private int releaseAuto2Touch(int PactionID,int Pplayer)       //~9B18I~//~9B23R~//~9B28R~
    private void releaseAuto2Touch(int PactionID,int Pplayer)      //~9B28I~
    {                                                              //~9B18I~//~9B28R~
//        int rc=0;                                                  //~9B21R~//~9B28R~
//        boolean swYouAreBlocker=false;                            //~9B26I~//~9B28R~
//        int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~9B18I~//~9B28R~
//        int currentEswn=Accounts.getCurrentEswn();                 //~9B24I~//~9B28R~
//        if (Dump.Y) Dump.println("UADelayed2.releaseAuto2Touch currentEswn="+currentEswn+",actionID="+PactionID+",player="+Pplayer+",eswn="+eswn+",blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction));//~9B18I~//~9B21R~//~9B23R~//~9B24R~//~9B28R~
//        synchronized(blockerAction)                                //~9B18I~//~9B28R~
//        {                                                          //~9B18I~//~9B28R~
//            if (blockerAction[eswn]!=0)                            //~9B18I~//~9B28R~
//            {                                                      //~9B18I~//~9B28R~
//                blockerAction[eswn]=0;                             //~9B18I~//~9B28R~
//                int jj=0;                                          //~9B24I~//~9B28R~
//                int oldtop=blockerActionQ[0];                      //~9B24I~//~9B28R~
//                for (int ii=0;ii<blockerCtr;ii++)                  //~9B24I~//~9B28R~
//                {                                                  //~9B24I~//~9B28R~
//                    if (blockerActionQ[ii]!=eswn)  //not release   //~9B24I~//~9B28R~
//                    {                                              //~9B26I~//~9B28R~
//                        blockerActionQ[jj++]=blockerActionQ[ii];   //~9B24I~//~9B28R~
//                        if (blockerActionQ[ii]==currentEswn)       //~9B26I~//~9B28R~
//                            swYouAreBlocker=true;                 //~9B26I~//~9B28R~
//                    }                                              //~9B26I~//~9B28R~
//                }                                                  //~9B24I~//~9B28R~
//                blockerCtr=jj;                                     //~9B24I~//~9B28R~
//                if (blockerCtr!=0)                                 //~9B18I~//~9B28R~
//                {                                                  //~9B19I~//~9B28R~
//                    if (blockerActionQ[0]==currentEswn)            //~9B24I~//~9B28R~
//                    {                                              //~9B24I~//~9B28R~
//                        if (oldtop==currentEswn)    //first blocker not changed//~9B24I~//~9B28R~
//                            rc=STAT_BLOCKER_YET;                   //~9B24I~//~9B26R~//~9B28R~
//                        else                                       //~9B24I~//~9B28R~
//                        {                                          //~9B26I~//~9B28R~
//                            actionNewBlocker=blockerAction[currentEswn];//~9B26I~//~9B28R~
//                            rc=STAT_BLOCKER_YOU;    //you are new first responser//~9B24I~//~9B26R~//~9B28R~
//                        }                                          //~9B26I~//~9B28R~
//                    }                                              //~9B24I~//~9B28R~
//                    else                                           //~9B24I~//~9B28R~
//                    if (swYouAreBlocker)                           //~9B26I~//~9B28R~
//                        rc=STAT_BLOCKED_MORE;                          //~9B18I~//~9B24I~//~9B28R~
//                    else                                           //~9B26I~//~9B28R~
//                        rc=STAT_BLOCK_RELEASED;                    //~9B26I~//~9B28R~
//                }                                                  //~9B19I~//~9B28R~
//                else                                               //~9B18I~//~9B28R~
//                {                                                  //~9B21I~//~9B28R~
//                    stopAuto2Touch=false;                          //~9B18I~//~9B28R~
//                    rc=STAT_BLOCK_RELEASED_ALL;                        //~9B21I~//~9B27R~//~9B28R~
//                }                                                  //~9B21I~//~9B28R~
//            }                                                      //~9B18I~//~9B28R~
//        }                                                          //~9B18I~//~9B28R~
    	if (!UAD2T.releaseAuto2Touch(PactionID,Pplayer))	//all released//~9B28R~
            swStopAuto2Touch=false;                                //~9B28I~
	    setBlockRobotRon(false);                                   //~va8tR~
        if (Dump.Y) Dump.println("UADelayed2.releaseAuto2Touch exit swStopauto2Touch="+swStopAuto2Touch);//~9B18I~//~9B19R~//~9B23R~//~9B24R~//~9B26R~//~9B28R~
//        return rc;                                                 //~9B17I~//~9B28R~
    }                                                              //~9B17I~//~9B28R~
    //************************************************************ //~9B18I~
    @Override                                                      //~9B18I~
    protected void resetAuto2Touch()                                 //~9B18I~
    {                                                              //~9B18I~
//        if (Dump.Y) Dump.println("UADelayed2.resetAuto2Touch before stopAuto2Touch="+stopAuto2Touch+",blockerCtr="+blockerCtr+",blockerAction="+Arrays.toString(blockerAction));//~9B18I~//~9B28R~//~9C11R~
//        synchronized(blockerAction)                                //~9B18I~//~9B28R~
//        {                                                          //~9B18I~//~9B28R~
//            stopAuto2Touch=false;                                  //~9B18I~//~9B28R~
//            Arrays.fill(blockerAction,0);   //actionID by eswn                       //~9B18I~//~9B19R~//~9B28R~
//            Arrays.fill(blockerActionQ,-1); //eswn by clockerCtr   //~9B19I~//~9B28R~
//            blockerCtr=0;                                          //~9B18I~//~9B28R~
//        }                                                          //~9B18I~//~9B28R~
        swStopAuto2Touch=false;                                  //~v@@@I~//~9B28R~
	    setBlockRobotRon(false);                                   //~va8tR~
    	UAD2T.resetAuto2Touch();                                   //~9B28R~
    	if (Dump.Y) Dump.println("UADelayed2.resetAuto2Touch after swStopAuto2Touch="+swStopAuto2Touch);//~9B24I~//~9B28R~//~9C11R~
    }                                                              //~9B18I~
    //************************************************************ //~9B28I~
    //*consideration of MultiRon                                   //~9B28I~
    //*rc:stop continued                                           //~9C11I~
    //************************************************************ //~9B28I~
    protected boolean resetAuto2Touch(int PactionID,int Pplayer)   //~9B28I~
    {                                                              //~9B28I~
        if (Dump.Y) Dump.println("UADelayed2.resetAuto2Touch action="+PactionID+",player="+Pplayer);//~9C11I~
    	boolean rc=UAD2T.resetAuto2Touch(PactionID,Pplayer);       //~9B28I~
        if (!rc)	//not multiron available                       //~9B28I~
	        swStopAuto2Touch=false;                                //~9B28I~
	    setBlockRobotRon(false);                                   //~va8tR~
    	if (Dump.Y) Dump.println("UADelayed.resetAuto2Touch after rc="+rc+",swStopAuto2Touch="+swStopAuto2Touch);//~9B28I~
        return rc;                                                 //~9B28I~
    }                                                              //~9B28I~
    //************************************************************ //~9B27I~
    //*on each device                                               //~9B27I~//~9B29R~
    //*from Pon/Kan/Chii/Ron                                       //~9B27I~//~9B29R~
    //************************************************************ //~9B27I~
    public void resetWait2Touch(boolean PswServer,int PactionID,int Pplayer)//~9B27I~
    {                                                              //~9B27I~
    	if (Dump.Y) Dump.println("UADelayed.resetWait2Touch swServer="+PswServer+",action="+PactionID+",player="+Pplayer);//~9B27I~//~9B28R~
//      if (!sw2Touch)                                             //~9B27I~//~9C04R~
//      if (!is2TouchMode(PactionID))                              //~9C04R~
        if (!isBlockMode(PactionID))                               //~9C04I~
            return;                                                //~9B27I~
	    actionDone(PactionID,PswServer,Pplayer);                   //~9B27I~
    }                                                              //~9B27I~
    //************************************************************ //~9B28I~
    //*from UARon, chkMultiron                                     //~9B28I~
    //************************************************************ //~9B28I~
    public boolean resetWaitByRon2Touch(int Pplayer)               //~9B28I~
    {                                                              //~9B28I~
    	if (Dump.Y) Dump.println("UADelayed2.resetWaitByRon2Touch player="+Pplayer);//~9B28I~
//      if (!sw2Touch)                                             //~9B28I~//~9C04R~
        if (!sw2TouchRon)                                          //~9C04I~
            return false;                                          //~9B28I~
        boolean rc=UAD2T.chkMultiRon(GCM_RON,Pplayer);             //~9B28I~
    	if (Dump.Y) Dump.println("UADelayed2.resetWaitByRon2Touch rc="+rc);//~9B28I~
        return rc;                                                 //~9B28I~
    }                                                              //~9B28I~
//    //*************************************************************************//~9B17I~//~9B26R~//~9B28M~
//    public void sendMsgAction2Touch(int PactionID,int Pplayer,int Pstatus)//~9B17I~//~9B26R~//~9B28M~
//    {                                                              //~9B17I~//~9B26R~//~9B28M~
//        if (swStop)                                                //~9B20I~//~9B26R~//~9B28M~
//        {                                                          //~9B20I~//~9B26R~//~9B28M~
//            if (Dump.Y) Dump.println("UADelayed2.sendMsgAction2Touch return by swStop");//~9B20I~//~9B26R~//~9B28M~
//            return;                                                //~9B20I~//~9B26R~//~9B28M~
//        }                                                          //~9B20I~//~9B26R~//~9B28M~
//        if(Dump.Y) Dump.println("UADelayed2.sendMsgAction2Touch actionID="+PactionID);//~9B17I~//~9B18R~//~9B26R~//~9B28M~
//        AG.aGC.sendMsg(PactionID,Pplayer,Pstatus);                 //~9B17I~//~9B26R~//~9B28M~
//    }                                                              //~9B17I~//~9B26R~//~9B28M~
	//*************************************************************************//~9B18I~//~9B28M~
    public void sendMsg2Touch(int PactionID,int Pplayer,int Pstatus)//~9B18I~//~9B28M~
    {                                                              //~9B18I~//~9B28M~
    	if (swStop)                                                //~9B20I~//~9B28M~
        {                                                          //~9B20I~//~9B28M~
	    	if (Dump.Y) Dump.println("UADelayed2.sendMsg2Touch return by swStop");//~9B20I~//~9B28M~
        	return;                                                //~9B20I~//~9B28M~
        }                                                          //~9B20I~//~9B28M~
        if(Dump.Y) Dump.println("UADelayed2.sendMsg2Touch actionID="+PactionID+",player="+Pplayer+",status="+Pstatus);//~9B18I~//~9B28M~
//      AG.aGameViewHandler.sendMsg(GCM_2TOUCH,Pplayer,PactionID,Pstatus);//~9B18R~//~9B22R~//~9B28M~
        GameViewHandler.sendMsg(GCM_2TOUCH,Pplayer,PactionID,Pstatus);//~9B22I~//~9B28M~
    }                                                              //~9B18I~//~9B28M~
	//*************************************************************************//~9B26I~//~9B28M~
	//*parm4:discarderEswn                                         //~9B26I~//~9B28M~
	//*************************************************************************//~9B26I~//~9B28M~
    public void sendMsg2Touch(int PactionID,int Pplayer,int Pstatus,int Pparm4,int Pparm5)//~9B26I~//~9B28M~
    {                                                              //~9B26I~//~9B28M~
    	if (swStop)                                                //~9B26I~//~9B28M~
        {                                                          //~9B26I~//~9B28M~
	    	if (Dump.Y) Dump.println("UADelayed2.sendMsg2Touch return by swStop");//~9B26I~//~9B28M~
        	return;                                                //~9B26I~//~9B28M~
        }                                                          //~9B26I~//~9B28M~
        if(Dump.Y) Dump.println("UADelayed2.sendMsg2Touch actionID="+PactionID+",player="+Pplayer+",status="+Pstatus+",parm4="+Pparm4+",parm5="+Pparm5);//~9B28M~
        GameViewHandler.sendMsg(GCM_2TOUCH,Pplayer,PactionID,Pstatus,Pparm4,Pparm5);//~9B26I~//~9B28M~
    }                                                              //~9B26I~//~9B28M~
	//*************************************************************************//~9B18I~//~9B28M~
    public void sendMsgAction(int PactionID,int Pplayer,int Pstatus)//~9B18I~//~9B28M~
    {                                                              //~9B18I~//~9B28M~
    	if (swStop)                                                //~9B20I~//~9B28M~
        {                                                          //~9B20I~//~9B28M~
	    	if (Dump.Y) Dump.println("UADelayed2.sendMsgAction return by swStop");//~9B20I~//~9B28M~
        	return;                                                //~9B20I~//~9B28M~
        }                                                          //~9B20I~//~9B28M~
        if(Dump.Y) Dump.println("UADelayed2.sendMsgAction actionID="+PactionID+",player="+Pplayer+",status="+Pstatus);//~9B18I~//~9B28M~
//      AG.aGameViewHandler.sendMsg(PactionID,Pplayer,Pstatus,0);  //~9B18I~//~9B22R~//~9B28M~
        GameViewHandler.sendMsg(PactionID,Pplayer,Pstatus,0);      //~9B22I~//~9B28M~
    }                                                              //~9B18I~//~9B28M~
	//*************************************************************************//~9B17I~//~9B28M~
    public String makeMsgData2Touch(int PactionID,int Pplayer,int Pstatus)//~9B17R~//~9B28M~
    {                                                              //~9B17I~//~9B28M~
    	String msg=UserAction.makeMsgDataToClient(Pplayer,PactionID,Pstatus,0/*parm3*/);//~9B18R~//~9B22R~//~9B28M~
        if (Dump.Y) Dump.println("UADelayed2.makeMsgData2Touch action="+PactionID+",player="+Pplayer+",status="+Pstatus+",msg="+msg);//~9B17I~//~9B18R~//~9B26R~//~9B28M~
        return msg;                                                //~9B17I~//~9B28M~
    }                                                              //~9B17I~//~9B28M~
	//*************************************************************************//~9B28I~
    public String makeMsgData2Touch(int PactionID,int Pplayer,int Pstatus,int Pparm)//~9B28I~
    {                                                              //~9B28I~
//  	String msg=UserAction.makeMsgDataToClient(Pplayer,PactionID,Pstatus,0);//~9B28I~
    	String strParm=PactionID+MSG_SEPAPP2+Pstatus+MSG_SEPAPP2+"0"+MSG_SEPAPP2+Pparm;//~9B28I~
    	String msg=UserAction.makeMsgDataToClient(Pplayer,strParm);//~9B28R~
        if (Dump.Y) Dump.println("UADelayed2.makeMsgData2Touch action="+PactionID+",player="+Pplayer+",status="+Pstatus+",parm="+Pparm+",msg="+msg);//~9B28I~
        return msg;                                                //~9B28I~
    }                                                              //~9B28I~
	//*************************************************************************//~9B18I~//~9B28M~
//  private void sendToServer2Touch(int PactionID,int Pplayer,int Pstatus)//~9B18R~//~9B26R~//~9B28M~
    private void sendToServer2Touch(int PactionID,int Pplayer,int Pstatus,int Pparm4,int Pparm5)//~9B26I~//~9B28M~
    {                                                              //~9B18I~//~9B28M~
    	if (swStop)                                                //~9B20I~//~9B28M~
        {                                                          //~9B20I~//~9B28M~
	    	if (Dump.Y) Dump.println("UADelayed2.sendToServer2Touch return by swStop");//~9B20I~//~9B28M~
        	return;                                                //~9B20I~//~9B28M~
        }                                                          //~9B20I~//~9B28M~
        if (Dump.Y) Dump.println("UADelayed2.sendToServer2Touch action="+PactionID+",player="+Pplayer+",status="+Pstatus+",parm4="+Pparm4+",parm5="+Pparm5);//~9B18R~//~9B26R~//~9B28M~
//      UA.sendToServer(GCM_2TOUCH,Pplayer,PactionID,Pstatus,0);   //~9B18I~//~9B26R~//~9B28M~
//      UA.sendToServer(GCM_2TOUCH,Pplayer,PactionID,Pstatus,Pparm);//~9B26I~//~9B28M~
        UA.sendToServer(GCM_2TOUCH,Pplayer,PactionID,Pstatus,Pparm4,Pparm5);//~9B28M~
    }                                                              //~9B18I~//~9B28M~
    //*****************************************************************//~9B21I~//~9B28M~
    private void sendToClient2Touch(int PactionID,int Pplayer,int Pstatus)//~9B21I~//~9B28M~
    {                                                              //~9B21I~//~9B28M~
    //**************************                                   //~9B21I~//~9B28M~
    	if (swStop)                                                //~9B21I~//~9B28M~
        {                                                          //~9B21I~//~9B28M~
	    	if (Dump.Y) Dump.println("UADelayed2.sendToClient2Touch return by swStop");//~9B21I~//~9B28M~
        	return;                                                //~9B21I~//~9B28M~
        }                                                          //~9B21I~//~9B28M~
    	if (Dump.Y) Dump.println("UADelayed.sendtoClient2Touch actionid="+PactionID+",player="+Pplayer+",stat="+Pstatus);//~9B21I~//~9B28M~
        int eswn= Accounts.playerToEswn(Pplayer);            //~9321I~//~v@01R~//~9B21I~//~9B28M~
		String msg=eswn+MSG_SEPAPP2+PactionID+MSG_SEPAPP2+Pstatus; //~9B21R~//~9B28M~
		if (Dump.Y) Dump.println("UADelayed2.sendToClient2Touch eswn="+eswn+",msg="+msg);//~9B21R~//~9B28M~
        UA.sendToClient(true/*swSendAll*/,false/*swRobot*/,GCM_2TOUCH,Pplayer,msg);//~9B21I~//~9B28M~
    }                                                              //~9B21I~//~9B28M~
//    //***********************************************************************//~9B16I~//~9B17R~//~9B28M~
//    //*on each device; pre-chk iswaitable,return false if err detected//~9B16I~//~9B17R~//~9B28M~
//    //*for GCM_WAITON2/GCM_WAITOFF2 (by 2 touchmode dialog button) //~9B16I~//~9B17R~//~9B28M~
//    //***********************************************************************//~9B16I~//~9B17R~//~9B28M~
//    public boolean selectInfo2(int PactionID,boolean PswServer,int Pplayer)//~9B16I~//~9B17R~//~9B28M~
//    {                                                              //~9B16I~//~9B17R~//~9B28M~
//        boolean rc=true;                                           //~9B16I~//~9B17R~//~9B28M~
//        if (PactionID==GCM_WAITON)                                 //~9B16I~//~9B17R~//~9B28M~
//        {                                                          //~9B16I~//~9B17R~//~9B28M~
//            rc=chkWaitable(true/*PswMsg*/,Pplayer,delayedAction);  //~9B16I~//~9B17R~//~9B28M~
//        }                                                          //~9B16I~//~9B17R~//~9B28M~
//        if (Dump.Y) Dump.println("UADelayed2.selectInfo2 rc="+rc+",player="+Pplayer+",actionID="+PactionID+",player="+Pplayer);//~9B16I~//~9B17R~//~9B28M~
//        return rc;                                                 //~9B16I~//~9B17R~//~9B28M~
//    }                                                              //~9B16I~//~9B17R~//~9B28M~
//    //***********************************************************************//~9B17R~//~9B28M~//~9C05R~
//    public boolean actionWait2(int Pmsgid,boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9B17R~//~9B28M~//~9C05R~
//    {                                                              //~9B17R~//~9B28M~//~9C05R~
//    //TODO                                                         //~9B17I~//~9B28M~//~9C05R~
//        boolean  rc=false;  //send msgDataToClent                  //~9B17R~//~9B28M~//~9C05R~
////        if (Dump.Y) Dump.println("UADelayed2.actionWait2 msgid="+Pmsgid+",swServer="+PswServer+",swReceived="+PswReceived+",player="+Pplayer+",delayedAction="+delayedAction);//~9B17R~//~9B28M~//~9C05R~
////        if (!Status.isGamingNow())  //before gameover            //~9B17R~//~9B28M~//~9C05R~
////            return false;                                        //~9B17R~//~9B28M~//~9C05R~
////        startTimer();                                            //~9B17R~//~9B28M~//~9C05R~
////        int eswn=Accounts.playerToEswn(Pplayer);                 //~9B17R~//~9B28M~//~9C05R~
////        if (Dump.Y) Dump.println("UADelayed2.actionWait2 eswn="+eswn);//~9B17R~//~9B28M~//~9C05R~
////        String msg;                                              //~9B17R~//~9B28M~//~9C05R~
////        boolean swReturn=false;                                  //~9B17R~//~9B28M~//~9C05R~
////        if (PswServer)                                           //~9B17R~//~9B28M~//~9C05R~
////        {                                                        //~9B17R~//~9B28M~//~9C05R~
////            int errid=0;                                         //~9B17R~//~9B28M~//~9C05R~
////            synchronized(swWaiting)                              //~9B17R~//~9B28M~//~9C05R~
////            {                                                    //~9B17R~//~9B28M~//~9C05R~
////                if (Pmsgid==GCM_WAITON)                          //~9B17R~//~9B28M~//~9C05R~
////                {                                                //~9B17R~//~9B28M~//~9C05R~
////                    int er=isWaitable(Pplayer);                  //~9B17R~//~9B28M~//~9C05R~
////                    if (er!=0)                                   //~9B17R~//~9B28M~//~9C05R~
////                        errid=er;                                //~9B17R~//~9B28M~//~9C05R~
////                    else                                         //~9B17R~//~9B28M~//~9C05R~
////                    if (!swWaiting[eswn])                        //~9B17R~//~9B28M~//~9C05R~
////                    {                                            //~9B17R~//~9B28M~//~9C05R~
////                        swWaiting[eswn]=true;                    //~9B17R~//~9B28M~//~9C05R~
////                        ctrWaitingPlayer++;                      //~9B17R~//~9B28M~//~9C05R~
////                        showWaited(true/*swDelayed*/,Pplayer,delayedAction,false/*PswOff*/);//~9B17R~//~9B28M~//~9C05R~
////                    }                                            //~9B17R~//~9B28M~//~9C05R~
////                    else                                         //~9B17R~//~9B28M~//~9C05R~
////                        errid= R.string.Info_WaitRequestedDup;   //~9B17R~//~9B28M~//~9C05R~
////                }                                                //~9B17R~//~9B28M~//~9C05R~
////                else                                             //~9B17R~//~9B28M~//~9C05R~
////                {                                                //~9B17R~//~9B28M~//~9C05R~
////                    if (swWaiting[eswn])                         //~9B17R~//~9B28M~//~9C05R~
////                    {                                            //~9B17R~//~9B28M~//~9C05R~
////                        if (ctrWaitingPlayer==1 && rescheduleAutoActionByWaitOff(msgWaiting))   //reset at GCM_TAKE,GCM_DISCARD//~9B17R~//~9B28M~//~9C05R~
////                        {                                        //~9B17R~//~9B28M~//~9C05R~
////                            swReturn=true;                       //~9B17R~//~9B28M~//~9C05R~
////                        }                                        //~9B17R~//~9B28M~//~9C05R~
////                        else                                     //~9B17R~//~9B28M~//~9C05R~
////                        {                                        //~9B17R~//~9B28M~//~9C05R~
////                        swWaiting[eswn]=false;                   //~9B17R~//~9B28M~//~9C05R~
////                        ctrWaitingPlayer--;                      //~9B17R~//~9B28M~//~9C05R~
////                        }                                        //~9B17R~//~9B28M~//~9C05R~
////                    }                                            //~9B17R~//~9B28M~//~9C05R~
////                    else                                         //~9B17R~//~9B28M~//~9C05R~
////                        errid=R.string.Info_WaitCanceledDup;     //~9B17R~//~9B28M~//~9C05R~
////                }                                                //~9B17R~//~9B28M~//~9C05R~
////            }                                                    //~9B17R~//~9B28M~//~9C05R~
////            if (errid!=0)                                        //~9B17R~//~9B28M~//~9C05R~
////            {                                                    //~9B17R~//~9B28M~//~9C05R~
////                UserAction.sendErr(0/*opt*/,Pplayer,errid);      //~9B17R~//~9B28M~//~9C05R~
////            }                                                    //~9B17R~//~9B28M~//~9C05R~
////            else                                                 //~9B17R~//~9B28M~//~9C05R~
////            if (swReturn)                                        //~9B17R~//~9B28M~//~9C05R~
////            {                                                    //~9B17R~//~9B28M~//~9C05R~
////                UA.setNoMsgToClient();                           //~9B17R~//~9B28M~//~9C05R~
////                if (Dump.Y) Dump.println("UADelayed2.actionWait2 return by rescheduled");//~9B17R~//~9B28M~//~9C05R~
////            }                                                    //~9B17R~//~9B28M~//~9C05R~
////            else                                                 //~9B17R~//~9B28M~//~9C05R~
////            {                                                    //~9B17R~//~9B28M~//~9C05R~
////                UA.msgDataToClient=makeMsgDataToClientWait(Pplayer,delayedAction);//~9B17R~//~9B28M~//~9C05R~
////                setWaiterLightServer(Pplayer,Pmsgid==GCM_WAITON);//~9B17R~//~9B28M~//~9C05R~
////                rc=true;    //send msgDataToClent                //~9B17R~//~9B28M~//~9C05R~
////            }                                                    //~9B17R~//~9B28M~//~9C05R~
////        }                                                        //~9B17R~//~9B28M~//~9C05R~
////        else //client                                            //~9B17R~//~9B28M~//~9C05R~
////        {                                                        //~9B17R~//~9B28M~//~9C05R~
////            if (PswReceived)                                     //~9B17R~//~9B28M~//~9C05R~
////            {                                                    //~9B17R~//~9B28M~//~9C05R~
////                delayedAction=PintParm[PARMPOS_WAITED_ACTION];   //~9B17R~//~9B28M~//~9C05R~
////                if (!(Pmsgid==GCM_WAITOFF && rescheduleMsgClient(delayedAction,Pplayer)))   //take/discard issued//~9B17R~//~9B28M~//~9C05R~
////                {                                                //~9B17R~//~9B28M~//~9C05R~
////                    setWaitingClient(PintParm);                  //~9B17R~//~9B28M~//~9C05R~
////                    setWaiterLightClient(Pplayer,eswn);          //~9B17R~//~9B28M~//~9C05R~
////                }                                                //~9B17R~//~9B28M~//~9C05R~
////            }                                                    //~9B17R~//~9B28M~//~9C05R~
////            else                                                 //~9B17R~//~9B28M~//~9C05R~
////            {                                                    //~9B17R~//~9B28M~//~9C05R~
////                AG.aAccounts.sendToServer(Pmsgid,"");            //~9B17R~//~9B28M~//~9C05R~
////            }                                                    //~9B17R~//~9B28M~//~9C05R~
////        }                                                        //~9B17R~//~9B28M~//~9C05R~
////        if (Dump.Y) Dump.println("UADelayed2.actionWait2 rc="+rc);//~9B17R~//~9B28M~//~9C05R~
//        return rc;                                                 //~9B17R~//~9B28M~//~9C05R~
//    }                                                              //~9B17R~//~9B28M~//~9C05R~
//    //*************************************************************************//~v@@@I~//~9B16I~//~9B28M~//~9C04R~
//    public void sendMsgWait(boolean PswWaitOn,int PactionID)                     //~v@@@I~//~9622R~//~9B16I~//~9B28M~//~9C04R~
//    {                                                              //~v@@@I~//~9B16I~//~9B28M~//~9C04R~
//        if (swStop)                                                //~9623I~//~9B20I~//~9B28M~//~9C04R~
//        {                                                          //~9623I~//~9B20I~//~9B28M~//~9C04R~
//            if (Dump.Y) Dump.println("UADelayed2.sendMsgWait return by swStop");//~9623I~//~9B20I~//~9B28M~//~9C04R~
//            return;                                                //~9623I~//~9625R~//~9B20I~//~9B28M~//~9C04R~
//        }                                                          //~9623I~//~9B20I~//~9B28M~//~9C04R~
//        if(Dump.Y) Dump.println("UADelayed2.sendMsgWait waitOn="+PswWaitOn+",actionID="+PactionID);//~9B16I~//~9B17R~//~9B28M~//~9C04R~
//        AG.aGC.sendMsg((PswWaitOn ? GCM_WAITON2 : GCM_WAITOFF2),PLAYER_YOU,PactionID);                 //~v@@@I~//~9B16R~//~9B28M~//~9C04R~
//    }                                                              //~v@@@I~//~9B16I~//~9B28M~//~9C04R~
    //*************************************************************************//~9C05I~
    private void postDelayedBlockTimeout(int PdelaySchedule,int PactionID,int Pplayer,int Pflag)//~9C05I~//~9C07R~
    {                                                              //~9C05I~
        if (swStop)                                                //~9C05I~
        {                                                          //~9C05I~
            if (Dump.Y) Dump.println("UADelayed2.postDelayedBlockTimeout return by swStop");//~9C05R~//~9C07R~
            return;                                                //~9C05I~
        }                                                          //~9C05I~
        if(Dump.Y) Dump.println("UADelayed2.postDelayedBlockTimeout PdelaySchedule="+PdelaySchedule+",actionID="+PactionID+",deley2Touch="+delay2Touch+",player="+Pplayer+",flag="+Pflag);//~9C05R~//~9C07R~
    	if (getBlockTimeout(PactionID)+PdelaySchedule==0)                         //~9C05R~//~9C07R~
        {                                                          //~9C05I~
            if (Dump.Y) Dump.println("UADelayed2.postDelayedBlockimeout return by timeout=0");//~9C05R~//~9C07R~
            return;                                                //~9C05I~
        }                                                          //~9C05I~
        int delay=delay2Touch+PdelaySchedule;                       //~9C07R~
        Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_BLOCK,delay,Pplayer,PactionID,Pflag);//~9C07I~
        sendMsgDelayed(msg,delay);	//callback to timeoutBlock//~9C05I~//~9C07R~
    }                                                              //~9C05I~
    //*************************************************************************//~9C05I~
    //*on GVH                                                      //~9C05I~
    //*************************************************************************//~9C05I~
    public void timeoutBlock(Message Pmsg)                         //~9C05I~
    {                                                              //~9C05I~
        int[] intp=getMsgData(Pmsg);            //delaytime,player,actionID,flag//~9C05R~
        if(Dump.Y) Dump.println("UADelayed2.timeoutBlock intp="+Arrays.toString(intp));//~9C05I~
        notifyTimeout(Pmsg,GCM_TIMEOUT_BLOCK,intp);                //~9C05I~
    }                                                              //~9C05I~
    //*************************************************************************//~9C05I~
    //*on Server,from notifyTimeout                                //~9C05I~
    //*************************************************************************//~9C05I~
    public void timeoutBlockRelease(int[] PintParm)                //~9C05I~
    {                                                              //~9C05I~
    	int actionID=PintParm[POSPARM_BLOCK_ACTIONID];             //~9C05I~
    	int player=PintParm[POSPARM_PLAYER];                       //~9C05I~
        if(Dump.Y) Dump.println("UADelayed2.timeoutBlockRelease on server intParm="+Arrays.toString(PintParm)+",actionID="+actionID+",player="+player);//~9C05I~
    	actionCanceledTimeout(actionID,true/*PswServer*/,false/*PswReceived*/,player);//~9C05I~
    }                                                              //~9C05I~
    //*************************************************************************//~va19I~
    public boolean isBlockedTop()                                  //~va19I~
    {                                                              //~va19I~
    	boolean rc=UAD2T.is2ndTouch(); //yourStatus==YS_BLOCK_TOP  //~va19I~
        if(Dump.Y) Dump.println("UADelayed2.isBlockedTop rc="+rc); //~va19I~
        return rc;                                                 //~va19I~
    }                                                              //~va19I~
}//class                                                           //~v@@@R~
