//*CID://+DATER~: update#= 874;                                    //~v@@@R~//~9622R~
//**********************************************************************//~v101I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import android.os.Bundle;
import android.os.Message;

import com.btmtest.BT.BTIOThread;
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.ActionAlert;
import com.btmtest.dialog.DrawnReqDlgHW;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.game.UA.UARestart;
import com.btmtest.game.gv.GMsg;
import com.btmtest.game.gv.GameView;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;
import com.btmtest.utils.Alert;                                    //~9B16I~
                                                                   //~9B16I~
import java.util.Arrays;

import static com.btmtest.TestOption.*;
import static com.btmtest.game.GCMsgID.*;                          //~v@@@M~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Status.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.game.gv.GameViewHandler.*;
import static com.btmtest.utils.Alert.*;                           //~9B16I~

//~v@@@I~
public class UADelayed //implements Runnable                            //~v@@@R~//~9623R~
//            implements Alert.AlertI                                //~9B16I~//~9B17R~
{                                                                  //~0914I~
	private static final int SLEEP_TIME=900;	//900ms,see MainActivity ondestry process//~v@@@I~
	protected static final int POSPARM_TIME=0;                       //~9625I~//~9B18R~
	protected static final int POSPARM_PLAYER=1;                     //~9625I~//~9B18R~
	protected static final int POSPARM_CTRTAKEN=2;                   //~9625I~//~9B18R~
	protected static final int POSPARM_CTRDISCARDED=3;               //~9625I~//~9B18R~
	protected static final int POSPARM_TIMEOUT_AUTOTAKE=3;         //~9C09I~
	protected static final int POSPARM_BLOCK_ACTIONID=2;           //~9C05I~
	protected static final int POSPARM_BLOCK_FLAG=3;               //~9C05I~
                                                                   //~9C05I~
	private static final int POSPARM_ACTION=4;                     //~9626R~
	public  final static int PARMPOS_WAIT_RELEASE_PLAYER=1;        //~9627I~
	public  final static int PARMPOS_WAIT_RELEASE_MSGID=2;         //~9627I~
	public  final static int PARMPOS_AUTOSTOP_ONOFF=1;             //~9703I~
	public  final static int PARMPOS_AUTOSTOP_FIX=2;               //~9703I~
                                                                   //~9A24I~
    public static final int STOPAUTO_DRAWNHW=0;  //from EndGame by Menu:drawn selection//~9A24I~
    public static final int STOPAUTO_FIX   =1    ;  //no user      //~9A24I~
    public static final int STOPAUTO_RESETALL=2     ;  //reset stopauto//~9A24I~
    public static final int STOPAUTO_IOERR=3     ;  //set stopauto//~9A24I~//~9A26R~
    public static final int STOPAUTO_DRAWNHW_NOTIFY=4;  //from EndGame by Menu:drawn selection//~9B14I~
                                                                   //~9A24I~
  	private static final int RESCHEDULE_DELAY=100;	               //~v@@@I~//~9628R~
//	private int actionID,player;                                           //~v@@@I~
	private TileData TD;                                                   //~v@@@I~
//  private UserAction UA;                                                 //~v@@@I~//~9B17R~
    protected UserAction UA;                                       //~9B17I~
    protected GameViewHandler GVH;                                   //~9B22R~//~9C07R~
    private UADelayTimer UADLT;                                          //~v@@@I~
    protected Players PLS;                                               //~v@@@R~//~9B26R~
//    private String msgData;
	private boolean[] swWaiting;                                   //~9702R~
	private boolean[] swWaitingAutoByDrawnHW; //current eswn seq                      //~9702I~//~9A27R~
	private int ctrWaitingPlayer=0;//~v@@@R~
    protected boolean swStop;                                                //~v@@@I~//~9B20R~
    private boolean swStopTemporally;                              //~0223I~
//  private boolean swActionReleased;                                      //~v@@@I~//~9624R~
//  private boolean swActionReleasedPonKan;                        //~v@@@I~//~9624R~
//    private int actionWaiting;                                             //~v@@@I~//~9623R~
//  private int delayedAction;    //actionID of scheduled delayed msg//~9B16R~//~9B17R~
    protected int delayedAction;    //actionID of scheduled delayed msg//~9B17I~
//    private boolean swOldWaitingStatus;                          //~v@@@R~
    private int delayTake,delayPonKan,delayLast;                             //~v@@@R~
    protected Message msgWaiting;//~v@@@I~                         //~9B18R~
//  protected int currentEswn;                                       //~v@@@I~//~9B24R~
	protected int timeoutAutoDiscard;                                //~9702R~//~9B18R~
	public int timeoutAutoTake;//access from Robot                 //~9702I~
	public int timeoutAutoTakeRobot;                              //~9701I~//~9B27R~
//  private int sendMsgWhat;                                       //~9624I~
//  private Bundle sendMsgData;                                    //~9624I~
    private boolean swResetAll;                                    //~9626I~
    private int stopAutoCtr;                                       //~9701I~
    private boolean stopAutoFix;                                   //~9703I~
//    protected boolean stopAuto2Touch;                              //~9B18I~//~9B28R~
    protected boolean swStopAuto2Touch;                            //~9B28I~
    public int delay2Touch;                                    //~9B16I~
//  public boolean sw2Touch;                                       //~9B16I~//~9C04R~
    protected boolean swServer;                                    //~9B17I~
    private boolean swAtRestart;                                   //~0221I~
    private boolean swResetMsgWaiting;                             //~0226I~
//*************************                                        //~v@@@I~
//  public UADelayed(UserAction Pua)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~9B17R~
    public UADelayed()                                             //~9B17I~
    {                                                              //~0914I~
//      AG.aUADelayed=this;                                        //~v@@@I~//~9B17R~
//  	UA=Pua;	//set at UADelayed2                                                    //~v@@@I~//~9B17R~//~0403R~
        GVH=AG.aGameViewHandler;                                   //~9B22R~//~9C07R~
        PLS=AG.aPlayers;                                       //~v@@@R~
		swWaiting=new boolean[PLAYERS];                            //~v@@@I~
		swWaitingAutoByDrawnHW=new boolean[PLAYERS];               //~9702I~
        Arrays.fill(swWaiting,false);                              //~v@@@I~
        UADLT=new UADelayTimer();                                  //~v@@@I~
        swStop=false;                                              //~v@@@I~
        swServer=Accounts.isServer();                              //~9B20I~
		delayPonKan=RuleSettingOperation.getDelayPonKan();                  //~v@@@I~//~9624R~
  		delayTake=RuleSettingOperation.getDelayTake();                      //~v@@@I~//~9624R~
		delayLast=RuleSettingOperation.getDelayLast();                      //~v@@@I~//~9624R~
//      currentEswn=Accounts.getCurrentEswn();                     //~v@@@I~//~9624R~//~9B24R~
		timeoutAutoDiscard=RuleSettingOperation.getDelayDiscard();         //~9624I~
		timeoutAutoTake=RuleSettingOperation.getTimeoutTake(); //~9624I~
		timeoutAutoTakeRobot=RuleSettingOperation.getTimeoutTakeRobot();//~9701I~
//  	timeoutAutoTakeKan=RuleSettingOperation.getTimeoutTakeKan();//~9624I~//~9625R~
//  	sw2Touch=RuleSettingOperation.isRuleWait();                //~9B16I~//~9B17R~
//  	delay2Touch=RuleSettingOperation.getDelay2Touch();         //~9B16I~//~9B17R~
    }                                                              //~0914I~
    //***********************************************************************//~v@@@I~
	public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADelayed.onDstroy");           //~v@@@I~
    	swStop=true;                                               //~v@@@I~
//      GVH.removeCallbacksAndMessages(null);                       //~v@@@I~//~9B22R~
        GameViewHandler.resetHandler();                            //~9B22I~
    }                                                              //~v@@@I~
    //***********************************************************************//~v@@@I~
	public void endGame()                                         //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADelayed.endGame");            //~v@@@I~
    	swStop=true;                                               //~v@@@I~
//      GVH.removeCallbacksAndMessages(null);                       //~v@@@I~
    }                                                              //~v@@@I~
//    //***********************************************************************//~v@@@I~//~9623R~
//    public void postDelayed(int Ptime/*milisec*/,int PactionID,int Pplayer,TileData Ptd)//~v@@@R~//~9623R~
//    {                                                              //~v@@@I~//~9623R~
//        actionID=PactionID; player=Pplayer; TD=Ptd;                //~v@@@I~//~9623R~
//        if (Dump.Y) Dump.println("UADelayed postDelayed actionID="+actionID+"="+GCMsgID.getEnum(actionID)+",player="+player+",time="+Ptime+",Ptd:"+Ptd.toString());//~v@@@R~//~9623R~
//        GVH.postDelayed(this,Ptime);    //GVH:Handler extended,callback this.run()     //~v@@@R~//~9622R~//~9623R~
//    }                                                              //~v@@@I~//~9623R~
//    //***********************************************************************//~v@@@I~//~9623R~
//    //*2phase timeout for Pon\Kan then for Take                    //~v@@@I~//~9623R~
//    //***********************************************************************//~v@@@I~//~9623R~
//    public void postDelayedDiscard(int Pplayer,TileData Ptd)       //~v@@@I~//~9623R~
//    {                                                              //~v@@@I~//~9623R~
//        if (Dump.Y) Dump.println("UADelayed postDelayedDiscard player="+Pplayer+",Ptd:"+Ptd.toString());//~v@@@R~//~9623R~
//        postDelayed(delayPonKan,GCM_NEXT_PLAYER_PONKAN,Pplayer,Ptd);//~v@@@R~//~9623R~
//    }                                                              //~v@@@I~//~9623R~
//    //***********************************************************************//~9624I~
//    private void sendMsgDelayed(boolean PswSave,Message Pmsg,int Ptime)//~9624I~
//    {                                                            //~9624I~
//        if (Dump.Y) Dump.println("UADelayed.sendMsgDelayed swSave="+PswSave+",time="+Ptime+",msg="+Pmsg.toString());//~9624I~
//        if (PswSave && Ptime!=0)    //reschedule if waited       //~9624I~
//        {                                                        //~9624I~
//            sendMsgWhat=Pmsg.what;                               //~9624I~
//            sendMsgData=Pmsg.getData();                          //~9624I~
//            if (Dump.Y) Dump.println("UADelayed.sendMsgDelayed what="+senMsgWhat+",sendData="+sendMsgData.toString());//~9624I~
//        }                                                        //~9624I~
//        GameViewHandler.sendMsgDelayed(Pmsg,Ptime);              //~9624I~
//    }                                                            //~9624I~
    //***********************************************************************//~9626I~
    protected void sendMsgDelayed(Message Pmsg,int Ptime)            //~9626I~//~9C05R~
    {                                                              //~9626I~
    	if (swStop)                                                //~9623I~//~9B20I~
        {                                                          //~9623I~//~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.sendMsgDelayed return by swStop");//~9623I~//~9B20I~
        	return;                                                //~9623I~//~9625R~//~9B20I~
        }                                                          //~9623I~//~9B20I~
        if (Dump.Y) Dump.println("UADelayed.sendMsgDelayed Pmsg="+Pmsg.toString()+",time="+Ptime);//~9626I~
        delayedAction=Pmsg.what;                                   //~9626I~
        GameViewHandler.sendMsgDelayed(Pmsg,Ptime);                //~9B26R~
    }                                                              //~9626I~
    //***********************************************************************//~9C10I~
    protected void sendMsgDelayed(int Pmsgid,int Ptime,int Pparm1/*player*/,int Pparm2/*status*/)//~9C10R~
    {                                                              //~9C10I~
    	if (swStop)                                                //~9C10I~
        {                                                          //~9C10I~
	    	if (Dump.Y) Dump.println("UADelayed.sendMsgDelayed return by swStop");//~9C10I~
        	return;                                                //~9C10I~
        }                                                          //~9C10I~
        if (Dump.Y) Dump.println("UADelayed.sendMsgDelayed msgid="+Pmsgid+",time="+Ptime+",parm1="+Pparm1+",parm2="+Pparm2);//~9C10R~
		Message msg=GameViewHandler.obtainMsg(Pmsgid,Pparm1,Pparm2,0);//~9C10R~
        GameViewHandler.sendMsgDelayed(msg,Ptime);                 //~9C10I~
    }                                                              //~9C10I~
    //***********************************************************************//~9624I~
    protected Message renewalMsg(Message Pmsg)                       //~9624I~//~9B18R~
    {                                                              //~9624I~
        if (Dump.Y) Dump.println("UADelayed.renewalMsg Pmsg="+Pmsg.toString());//~9624I~
        int what=Pmsg.what;                                        //~9624I~
        Bundle data=Pmsg.getData();                                //~9624I~
	    if (Dump.Y) Dump.println("UADelayed.renewalMsg what="+what+",data="+data.toString());//~9624I~
        Message msg=GameViewHandler.obtainMsg(what);               //~9624I~
        msg.setData(data);                                         //~9624I~
        return msg;                                                //~9624I~
    }                                                              //~9624I~
    //***********************************************************************//~9623I~
    //*from UADiscard.discard()                                    //~9B16R~
    //*Server only after Discard,schedule delayed msg TIMEOUT_TO_PONKAN if exausted pon/kan is available//~9B16I~
    //***********************************************************************//~9623I~
	public void postDelayedPonKan(int Pplayer,TileData Ptd)        //~9623I~
    {                                                              //~9623I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedPonKan return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        if (Dump.Y) Dump.println("UADelayed postDelayedPonKan player="+Pplayer+",Ptd:"+Ptd.toString());//~9623I~
//      String strTD=ACAction.strTD(Ptd);            //~9623I~     //~9624R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_PONKAN,Pplayer,AG.aPlayers.ctrTakenAll,0,strTD);//~9623I~//~9624R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_PONKAN,Pplayer,0,0,strTD);//~9624R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_PONKAN,Pplayer,AG.aPlayers.ctrTakenAll,AG.aPlayers.ctrDiscardedAll);//~9624I~//~9625R~
        Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_PONKAN,delayPonKan,Pplayer,AG.aPlayers.ctrTakenAll,AG.aPlayers.ctrDiscardedAll);//~9625I~
//      GameViewHandler.sendMsgDelayed(msg,delayPonKan);	//callback to timeoutToPonKan//~9623I~//~9624R~//~9626R~
        sendMsgDelayed(msg,delayPonKan);	//callback to timeoutToPonKan//~9626I~
    }                                                              //~9623I~
    //***********************************************************************//~9623I~//~9624M~
    //*On Server                                                   //~9628R~
    //*not by postDelayed but by sendMsgDelayed                    //~9628I~
    //at Timeout of GCM_NEXT_PLAYER_PONKAN,schedule delayed msg TIMEOUT_TO_TAKE if exausted TAKE is available//~9B16I~
    //***********************************************************************//~9623I~//~9624M~//~9628R~
//  public void postDelayedTake(int Pplayer,TileData Ptd)          //~9623I~//~9624R~
    private void postDelayedTakable(int Pplayer)                       //~9624I~//~9628R~
    {                                                              //~9623I~//~9624M~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedTakable return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        if (Dump.Y) Dump.println("UADelayed postDelayedTakable player="+Pplayer);//~9623I~//~9624M~//~9628R~
//      String strTD=ACAction.strTD(Ptd);                          //~9624R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_TAKE,Pplayer,AG.aPlayers.ctrTakenAll,0,stdTD);//~9623I~//~9624I~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_TAKE,Pplayer,0,0,strTD);//~9624R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_TAKE,Pplayer,AG.aPlayers.ctrTakenAll,AG.aPlayers.ctrDiscardedAll);//~9624I~//~9625R~
        Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_TAKE,delayTake,Pplayer,AG.aPlayers.ctrTakenAll,AG.aPlayers.ctrDiscardedAll);//~9625I~
//      GameViewHandler.sendMsgDelayed(msg,delayTake);	 //callback to timeoutToTake//~9624R~//~9626R~
        sendMsgDelayed(msg,delayTake);	 //callback :timeoutToTake //~9628R~
    }                                                              //~9623I~//~9624M~
    //***********************************************************************//~0403I~
    //*On Server                                                   //~0403I~
    //keep ron time for ankan,chankan                              //~0403I~
    //***********************************************************************//~0403I~
    public void postDelayedTakableKan(int Pplayer)                 //~0403R~
    {                                                              //~0403I~
    	if (swStop)                                                //~0403I~
        {                                                          //~0403I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedTakableKan return by swStop");//~0403I~
        	return;                                                //~0403I~
        }                                                          //~0403I~
        if (Dump.Y) Dump.println("UADelayed postDelayedTakableKan delay="+delayPonKan+",player="+Pplayer+",ctrTakenAll="+AG.aPlayers.ctrTakenAll+",ctrDiscardedAll="+AG.aPlayers.ctrDiscardedAll);//~0403I~
        Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_TAKABLE_RINSHAN,delayPonKan,Pplayer,AG.aPlayers.ctrTakenAll,AG.aPlayers.ctrDiscardedAll);//~0403R~
        sendMsgDelayed(msg,delayPonKan);	//callback to timeoutToTakeRinshan//~0403I~
    }                                                              //~0403I~
    //***********************************************************************//~9624I~
	public void postDelayedLastDrawn(int Pplayer)                  //~9624I~
    {                                                              //~9624I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedLastDrawn return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        if (Dump.Y) Dump.println("UADelayed postDelayedLastDrawn player="+Pplayer);//~9624I~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_LASTDRAWN,Pplayer,0,0);//~9624I~//~9625R~
        Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_TO_LASTDRAWN,delayLast,Pplayer,0);//~9625I~
//      GameViewHandler.sendMsgDelayed(msg,delayLast);	 //callback to timeoutToLastDrawn//~9624R~//~9626R~
        sendMsgDelayed(msg,delayLast);	 //callback to timeoutToLastDrawn thru GVH//~9626I~//~9B16R~
    }                                                              //~9624I~
    //***********************************************************************//~9623I~
    //*from GVH by GCM_TIMEOUT_TO_PONKAN                           //~9623I~
    //***********************************************************************//~9623I~
    public void timeoutToPonKan(Message Pmsg)                      //~9623I~
    {                                                              //~9623I~
//      int[][] intss=getMsgDataTD(Pmsg);                          //~9624R~
//      int pl=intss[0][0];                                            //~9623I~//~9624R~
//      int ctrTaken=intss[0][1];                                  //~9624I~
//      TileData td=new TileData(false/*PswEswnToPlayer*/,intss[1],0); //~9623I~//~9624R~
//      if (Dump.Y) Dump.println("UADelayed.timeoutToPonKan td="+td.toString());//~9623I~//~9624R~
//      notifyTimeout(Pmsg,GCM_NEXT_PLAYER_PONKAN,pl,td);          //~9623R~//~9624R~
        int[] intp=getMsgData(Pmsg);                               //~9624I~
        notifyTimeout(Pmsg,GCM_NEXT_PLAYER_PONKAN,intp);           //~9624R~
    }                                                              //~9623I~
    //***********************************************************************//~0403I~
    //*from GVH by GCM_TIMEOUT_TO_TAKABLE_RINSHAN                  //~0403R~
    //***********************************************************************//~0403I~
    public void timeoutToTakableRinshan(Message Pmsg)              //~0403R~
    {                                                              //~0403I~
        int[] intp=getMsgData(Pmsg);                               //~0403I~
        notifyTimeout(Pmsg,GCM_TIMEOUT_TO_TAKABLE_RINSHAN,intp);   //~0403R~
    }                                                              //~0403I~
    //***********************************************************************//~9628I~
    //*from Discard, timeout of TIMEOUT_TO_PONKAN(exausted Time for Ron)                       //~9628I~//~9630R~
    //***********************************************************************//~9628I~
    public void timeoutPonKan(boolean PswServer)                   //~9628I~
    {                                                              //~9628I~
        if (!PswServer)                                            //~9628I~
	        delayedAction=GCM_NEXT_PLAYER;	//entered span of waiting NEXT_PLAYER//~9628I~
        if (Dump.Y) Dump.println("UADelay.timeoutPonkan swServer="+PswServer+",delayedAction="+delayedAction);//~9628I~//~9A26R~
    }                                                              //~9628I~
    //***********************************************************************//~9623I~
    //*on Server                                                   //~9628R~
    //*from GVH by GCM_TIMEOUT_TO_TAKE                             //~9628I~
    //***********************************************************************//~9623I~
    public void timeoutToTake(Message Pmsg)                        //~9623I~
    {                                                              //~9623I~
//      int[][] intss=getMsgDataTD(Pmsg);                          //~9624R~
//      int pl=intss[0][0];                                        //~9624R~
//      int ctrTaken=intss[0][1];                                  //~9624I~
//      TileData td=new TileData(false/*PswEswnToPlayer*/,intss[1],0);//~9624R~
//      if (Dump.Y) Dump.println("UADelayed.timeoutToTAke td="+td.toString());//~9623I~//~9624R~
//      notifyTimeout(Pmsg,GCM_NEXT_PLAYER,pl,td);                 //~9623R~//~9624R~
        int[] intp=getMsgData(Pmsg);                               //~9624I~
//      notifyTimeout(Pmsg,GCM_NEXT_PLAYER,intp);                  //~9624I~//~9B23R~
        notifyTimeout(Pmsg,GCM_TIMEOUT_TO_TAKE,intp);              //~9B23I~
    }                                                              //~9623I~
    //***********************************************************************
    public void timeoutToLastDrawn(Message Pmsg)                   //~9624I~
    {
        int[] intp=getMsgData(Pmsg);                               //~9624I~
        notifyTimeout(Pmsg,GCM_ENDGAME_DRAWN,intp);                //~9624R~
    }
    //***********************************************************************//~9624I~
//  private int[][] getMsgDataTD(Message Pmsg)                     //~9624R~
    protected int[] getMsgData(Message Pmsg)                         //~9624I~//~9C05R~
    {                                                              //~9624I~
        int[] intps=GameViewHandler.getMsgIntData(Pmsg);           //~9624I~
//      String[] strps=GameViewHandler.getMsgStrData(Pmsg);        //~9624R~
//      int[] inttd=ACAction.parseAppData(strps[0]);	//TD       //~9624R~
        if (Dump.Y) Dump.println("UADelayed.getMsgData intp="+Arrays.toString(intps));//~9624R~
//      if (Dump.Y) Dump.println("UADelayed.timeoutToPonKan td="+Arrays.toString(inttd));//~9624R~
//      return new int[][]{intps,inttd};                           //~9624R~
        return intps;                                              //~9624I~
    }                                                              //~9624I~
    //***********************************************************************//~v@@@I~
    //on Server and client, sendMsgDelayed by Server only          //~9627R~//~0224R~
    //dealer may be Robot,but Robot's 1st Auto discard is same as human dealer's one.//~0229I~
    //*set timeout from Take to Discard, received by GVH.gvHandleMsg//~9627I~
    //***********************************************************************//~v@@@I~
	public void postDelayedAutoDiscard(boolean PswServer,int Pplayer,int PctrTaken,int Paction)//~v@@@I~//~9623R~//~9624R~//~9626R~//~9627R~
    {                                                              //~v@@@I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedAutoDiscard return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        if (Dump.Y) Dump.println("UADelayed postDealyedAutoDiscard swServer="+PswServer+",timeout="+timeoutAutoDiscard+",player="+Pplayer+",action="+Paction);//~v@@@I~//~9623R~//~9624R~//~9626R~//~9627R~
        if (timeoutAutoDiscard==0)                                 //~9624I~//~9628M~
        	return;                                                //~9624I~//~9628M~
        if (!PswServer)                                            //~9627I~
        {                                                          //~9628I~
	        delayedAction=GCM_TIMEOUT_AUTODISCARD;                 //~9628I~
        	if (Dump.Y) Dump.println("UADelayed postDelayedAutoDiscard return by Server set delayedAction="+delayedAction);//~9628I~
        	return;                                                //~9627I~
        }                                                          //~9628I~
//      Message msg=GameViewHandler.obtainMsg(GCM_DISCARD_TIMEOUT,Pplayer,PctrTaken,0);//~v@@@I~//~9624R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_AUTODISCARD,Pplayer,PctrTaken,0);//~9624I~//~9625R~
        Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_AUTODISCARD,RESCHEDULE_DELAY,Pplayer,PctrTaken,AG.aPlayers.ctrDiscardedAll,Paction);//~9626R~//~9628R~
//      GameViewHandler.sendMsgDelayed(msg,timeoutAutoDiscard);	//callback to UATake//~v@@@I~//~9624R~//~9626R~
        sendMsgDelayed(msg,timeoutAutoDiscard);	//callback to autoDiscardTimeout thru GVH by GCM_TIMEOYUT_AUTODISCARD//~9B16R~
    }                                                              //~v@@@I~
    //***********************************************************************//~9622I~
    //*Server and client, sendMsgDelayed by server only            //~9628R~//~0224R~
    //*set timeout from Discard to Take by next player             //~9628I~
    //***********************************************************************//~9622I~
	public void postDelayedAutoTake(boolean PswServer,int Pplayer/*nextPlayer*/,int PctrTaken)//~9622R~//~9624R~//~9627R~//~9B16R~
    {                                                              //~9622I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedAutoTake return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        int timeout=timeoutAutoTake;                               //~9701I~
        if (AG.aAccounts.isDummyPlayer(Pplayer))               //~v@@6R~//~9701I~
	        timeout=timeoutAutoTakeRobot;                          //~9701I~
        if (Dump.Y) Dump.println("UADelayed postDelayedAutoTake swServer="+PswServer+",timeout="+timeout+",autotake="+timeoutAutoTake+",autotakeRobot="+timeoutAutoTakeRobot+",player="+Pplayer+",ctraTakenAll="+PctrTaken);//~9622R~//~9624R~//~9627R~//~9701I~
        if (timeout==0)                                    //~9628I~//~9701R~
        {                                                          //~9628I~
	        delayedAction=GCM_TIMEOUT_AUTOTAKE0;                   //~9630I~
        	if (Dump.Y) Dump.println("UADelayed postDelayedAutoTake return by timeout=0 set delayedAction="+delayedAction);//~9630I~
            return;                                                //~9628I~
        }                                                          //~9628I~
        if (!PswServer)                                            //~9628I~
        {                                                          //~9628I~
	        delayedAction=GCM_TIMEOUT_AUTOTAKE;                    //~9628I~
        	if (Dump.Y) Dump.println("UADelayed postDelayedAutoTake return by Not Server set delayedAction="+delayedAction);//~9628I~//~9630R~
        	return;                                                //~9628I~
        }                                                          //~9628I~
//  	if (!UA.UADL.isWaitableAuto(Pplayer))                      //~9627R~
//  	if (!UA.UADL.isWaitableAuto(false/*PswMsg*/,Pplayer,GCM_TIMEOUT_AUTOTAKE))    //Player is next player//~9627R~//~9628R~
//    	if (!UA.UADL.isWaitableAutoTake())    //Player is next player//~9628I~//~9B30R~
      	if (!isWaitableAutoTake(timeout))    //Player is next player//~9B30R~
        	return;                                                //~9627I~
//      Message msg=GameViewHandler.obtainMsg(GCM_DISCARD_TAKE_TIMEOUT,Pplayer,PctrTaken,0);//~9622R~//~9624R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_AUTOTAKE,Pplayer,PctrTaken,0);//~9624I~//~9625R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_AUTOTAKE,RESCHEDULE_DELAY,Pplayer,PctrTaken);//~9625I~//~9626R~//~9628R~//~9C09R~
        Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_AUTOTAKE,RESCHEDULE_DELAY,Pplayer,PctrTaken,timeout);//~9C09I~
//      GameViewHandler.sendMsgDelayed(msg,timeout);	//callback to UADiscard//~9622I~//~9624R~//~9626R~//~9701R~
        sendMsgDelayed(msg,timeout);	//callback to autoTakeTimeout thru GVH//~9B16R~//~9B19R~
    }                                                              //~9622I~
    //***********************************************************************//~9627I~
    //*on server                                                   //~9627I~
    //***********************************************************************//~9627I~
	public void postDelayedAutoTakeKan(boolean PswServer,int Pplayer,int PctrTaken,int PctrDiscarded)//~9623R~//~9624R~//~9627R~//~0406R~
    {                                                              //~9623I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedAutoTakeKan return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        if (Dump.Y) Dump.println("UADelayed postDelayedAutoTakeKan swServer="+PswServer+",timeout="+timeoutAutoTake+",player="+Pplayer+",ctraTakenAll="+PctrTaken);//~9623I~//~9624R~//~9625R~//~9627R~
        if (timeoutAutoTake==0)                                    //~9625I~//~9627R~//~9628I~
        {                                                          //~9630I~
	        delayedAction=GCM_TIMEOUT_AUTOTAKE_KAN0;               //~9630I~
        	if (Dump.Y) Dump.println("UADelayed postDelayedAutoTakeKan return by timeout=0 set delayedAction="+delayedAction);//~9630I~
        	return;                                                //~9624I~//~9627R~//~9628I~
        }                                                          //~9630I~
        if (!PswServer)                                            //~9627R~//~9628R~
        {                                                          //~9628I~
	        delayedAction=GCM_TIMEOUT_AUTOTAKE_KAN;                //~9628I~
        	if (Dump.Y) Dump.println("UADelayed postDelayedAutoTakeKan return by Server set delayedAction="+delayedAction);//~9628I~
        	return;                                                //~9627R~//~9628R~
        }                                                          //~9628I~
//      if (timeoutAutoTakeKan==0)                                 //~9624I~//~9625R~
    	if (!UA.UADL.isWaitableAutoTakeKan(Pplayer,PctrTaken,PctrDiscarded))    //Player is next player//~9627I~//~0406R~
        	return;                                                //~9627I~
//      Message msg=GameViewHandler.obtainMsg(GCM_TAKEKAN_TIMEOUT,Pplayer,PctrTaken,PctrKan);//~9623R~//~9624R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_AUTOTAKE_KAN,Pplayer,PctrTaken,PctrKan);//~9624I~//~9625R~
//      Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_AUTOTAKE_KAN,timeoutAutoTakeKan,Pplayer,PctrTaken,PctrKan);//~9625R~
        Message msg=GameViewHandler.obtainMsg(GCM_TIMEOUT_AUTOTAKE_KAN,RESCHEDULE_DELAY,Pplayer,PctrTaken,PctrDiscarded);//~9625I~//~9626R~//~9628R~
//      GameViewHandler.sendMsgDelayed(msg,timeoutAutoTakeKan);	//callback to UADiscard//~9623I~//~9625R~
//      GameViewHandler.sendMsgDelayed(msg,timeoutAutoTake);	//callback to UADiscard//~9625I~//~9626R~
        sendMsgDelayed(msg,timeoutAutoTake);	//callback to UADiscard//~9626I~
    }                                                              //~9623I~
    //***********************************************************************//~v@@@I~//~9625M~
    //*on Server                                                   //~9628I~
    //*from GVH by GCM_TIMEOUT_AUTODISCARD,timeup to auto discard                                      //~9623I~//~9625I~//~9B16R~
    //***********************************************************************//~9623I~//~9625M~
	public void autoDiscardTimeout(Message Pmsg)                       //~v@@@I~//~9625R~
    {                                                              //~v@@@I~//~9625M~
        autoDiscardTimeout(Pmsg, false/*PswIgnoreWaiting*/);        //~9628I~
    }                                                              //~9628I~
    //***********************************************************************//~9628I~
    //*On server reschedule by WaitOff                             //~9628I~
    //***********************************************************************//~9628I~
	private boolean autoDiscardTimeoutByWaitOff(Message Pmsg)       //~9628I~//~9A26R~
    {                                                              //~9628I~
		return autoDiscardTimeout(Pmsg,true/*PswIgnoreWaiting*/);  //~9628I~
    }                                                              //~9628I~
    //***********************************************************************//~9628I~
	public boolean autoDiscardTimeout(Message Pmsg,boolean PswIgnoreWaiting)//~9628I~
    {                                                              //~9628I~
    	boolean rc=false;                                          //~9628I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.autoDiscardTimeout return by swStop");//~9B20I~
        	return false;                                          //~9B20I~
        }                                                          //~9B20I~
        int[] intp=GameViewHandler.getMsgIntData(Pmsg);             //~v@@@I~//~9625M~
        if (Dump.Y) Dump.println("UADelayed autoDiscardTimeout msg parm="+Arrays.toString(intp));//~v@@@I~//~9625R~
	    if (!PswIgnoreWaiting)                                     //~9629I~
			showRescheduledGmsg(Pmsg);                             //~9629I~
	    if (UA.UAT.isActiveAutoDiscardTimeout(intp[POSPARM_PLAYER]/*player*/,intp[POSPARM_CTRTAKEN]/*ctrTaken*/,intp[POSPARM_ACTION]))//~9627I~
	        if (PswIgnoreWaiting || !notifyTimeout(Pmsg,GCM_TIMEOUT_AUTODISCARD,intp))	//not blocked//~9625I~//~9627R~//~9628R~
            {                                                      //~9628I~
		        UA.UAT.autoDiscardTimeout(intp[POSPARM_PLAYER]/*player*/,intp[POSPARM_CTRTAKEN]/*ctrTaken*/,intp[POSPARM_ACTION]);//~v@@@R~//~9625R~//~9626R~//~9627R~
		    	rc=true;                                           //~9628I~
            }                                                      //~9628I~
        if (Dump.Y) Dump.println("UADelayed autoDiscardTimeout rc="+rc+",PswIgnoreWaitmsg="+PswIgnoreWaiting);//~9628I~
        return rc;                                                 //~9628I~
    }                                                              //~v@@@I~//~9625M~
    //***********************************************************************//~9622I~
    //*on Server                                                   //~9628I~
    //*from GVH,timeup to auto take                                //~9628I~
    //***********************************************************************//~9628I~
	public void autoTakeTimeout(Message Pmsg)                   //~9622I~//~9625R~
    {                                                              //~9622I~
		autoTakeTimeout(Pmsg,false/*PswIgnoreWaiting*/);   //~9628I~
    }                                                              //~9628I~
    //***********************************************************************//~9628I~
    //*On server reschedule by WaitOff                             //~9628I~
    //***********************************************************************//~9628I~
	private boolean autoTakeTimeoutByWaitOff(Message Pmsg)             //~9628I~//~9A26R~
    {                                                              //~9628I~
		return autoTakeTimeout(Pmsg,true/*PswIgnoreWaiting*/);    //~9628I~
    }                                                              //~9628I~
    //***********************************************************************//~9628I~
    //*timeout from discard to take by next player                 //~9B16I~
    //***********************************************************************//~9B16I~
	public boolean autoTakeTimeout(Message Pmsg,boolean PswIgnoreWaiting)//~9628I~
    {                                                              //~9628I~
    	boolean rc=false;                                          //~9628I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.autoTakeTimeout return by swStop");//~9B20I~
        	return false;                                          //~9B20I~
        }                                                          //~9B20I~
        int[] intp=GameViewHandler.getMsgIntData(Pmsg);            //~9622I~
        if (Dump.Y) Dump.println("UADelayed autoTakeTimeout msg parm="+Arrays.toString(intp));//~9622I~//~9625R~
	    if (!PswIgnoreWaiting)                                     //~9629I~
			showRescheduledGmsg(Pmsg);                             //~9629I~
        if (UA.UAD.isActiveAutoTakeTimeout(intp[POSPARM_PLAYER]/*player*/,intp[POSPARM_CTRTAKEN]/*ctrTaken*/))//~9627R~
//          if (!notifyTimeout(Pmsg,GCM_TIMEOUT_AUTOTAKE,intp)) //not blocked//~9625I~//~9627R~//~9628R~
            if (PswIgnoreWaiting || !notifyTimeout(Pmsg,GCM_TIMEOUT_AUTOTAKE,intp)) //not blocked//~9628I~
            {                                                      //~9628I~
                UA.UAD.autoTakeTimeout(intp[POSPARM_PLAYER]/*player*/,intp[POSPARM_CTRTAKEN]/*ctrTaken*/);//sendMsg GCM_TAKE//~9622R~//~9623R~//~9625R~//~9627R~//~9B21R~
		    	rc=true;                                           //~9628I~
            }                                                      //~9628I~
        if (Dump.Y) Dump.println("UADelayed autoTakeTimeout rc="+rc+",PswIgnoreWaitmsg="+PswIgnoreWaiting);//~9628I~
        return rc;                                                 //~9628I~
    }                                                              //~9622I~
    //***********************************************************************//~9623I~
    //*on Server                                                   //~9628I~
    //*from GVH by GCMTIMEOUT_AUTOTAKE_KAN, timeup to auto take                                //~9628I~//~9B23R~
    //***********************************************************************//~9628I~
	public void autoTakeKanTimeout(Message Pmsg)                       //~9623R~//~9625R~
    {                                                              //~9628I~
		autoTakeKanTimeout(Pmsg,false);                            //~9628I~
    }                                                              //~9628I~
    //***********************************************************************//~9628I~
    //*On server reschedule by WaitOff                             //~9628I~
    //***********************************************************************//~9628I~
	private boolean autoTakeKanTimeoutByWaitOff(Message Pmsg)       //~9628I~//~9A26R~
    {                                                              //~9628I~
		return autoTakeKanTimeout(Pmsg,true);                      //~9628I~
    }                                                              //~9628I~
    //***********************************************************************//~9628I~
	public boolean autoTakeKanTimeout(Message Pmsg,boolean PswIgnoreWaiting)//~9628I~
    {                                                              //~9623I~
    	boolean rc=false;                                          //~9628I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.autoTakeKanTimeout return by swStop");//~9B20I~
        	return false;                                          //~9B20I~
        }                                                          //~9B20I~
        int[] intp=GameViewHandler.getMsgIntData(Pmsg);            //~9623I~
        if (Dump.Y) Dump.println("UADelayed.autoTakeKanTimeout msg parm="+Arrays.toString(intp));//~9623R~//~9625R~
	    if (!PswIgnoreWaiting)                                     //~9629I~
			showRescheduledGmsg(Pmsg);                             //~9629I~
        if (UA.UAK.isActiveTakeKanTimeout(intp[POSPARM_PLAYER]/*player*/,intp[POSPARM_CTRTAKEN]/*ctrTaken*/,intp[POSPARM_CTRDISCARDED]/*ctrDiscarded*/))//~9627I~//+0406R~
//          if (!notifyTimeout(Pmsg,GCM_TIMEOUT_AUTOTAKE_KAN,intp)) //not blocked//~9625I~//~9627R~//~9628R~
            if (PswIgnoreWaiting || !notifyTimeout(Pmsg,GCM_TIMEOUT_AUTOTAKE_KAN,intp)) //not blocked//~9628I~
            {                                                      //~9628I~
                 UA.UAK.autoTakeKanTimeout(intp[POSPARM_PLAYER]/*player*/,intp[POSPARM_CTRTAKEN]/*ctrTaken*/,intp[POSPARM_CTRDISCARDED]/*ctrDiscarded*/);//~9623I~//~9625R~//~9627R~//+0406R~
		    	rc=true;                                           //~9628I~
            }                                                      //~9628I~
        if (Dump.Y) Dump.println("UADelayed autoTakeKanTimeout rc="+rc+",PswIgnoreWaitmsg="+PswIgnoreWaiting);//~9628I~
        return rc;                                                 //~9628I~
    }                                                              //~9623I~
//    //***********************************************************************//~v@@@I~//~9623R~
//    public void postDelayed(int Ptime/*milisec*/,int PactionID,String PmsgData)//~v@@@I~//~9623R~
//    {                                                              //~v@@@I~//~9623R~
//        actionID=PactionID; msgData=PmsgData;      //~v@@@I~     //~9623R~
//        if (Dump.Y) Dump.println("UADelayed postDelayed actionID="+actionID+"="+GCMsgID.getEnum(actionID)+",player="+player+",time="+Ptime+",data="+PmsgData);//~v@@@R~//~9623R~
//        GVH.postDelayed(this,Ptime);    //GVH:Handler extended     //~v@@@R~//~9623R~
//    }                                                              //~v@@@I~//~9623R~
    //***********************************************************************//~v@@@I~
    //*from Robot by GCM_TAKE and GCM_NEXTP_PLAYER                 //~9624I~
    //***********************************************************************//~9624I~
	public static void postDelayedRobotMsg(boolean PswWaiterBlock,int Ptime/*milisec*/,int PactionID,int Psender,String PmsgData)//~v@@@R~//~9624R~
    {                                                              //~v@@@I~
    	if (AG.aUADelayed.swStop)                                  //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedRobotMsg return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        if (Dump.Y) Dump.println("UADelayed.postDelayedRobotMsg actionID="+PactionID+"="+GCMsgID.getEnum(PactionID)+",sender="+Psender+",data="+PmsgData);//~v@@@R~//~9624R~
//      Message msg=GameViewHandler.obtainMsg(GCM_RECEIVED_APPMSG,PactionID,Psender,PmsgData,null,null);//~v@@@R~
//      Message msg=GameViewHandler.obtainMsg(GCM_RECEIVED_APPMSG,GCM_USER_ACTION,Psender,Integer.toString(PactionID),PmsgData,null);//~v@@@I~//~9625R~
        Message msg=GameViewHandler.obtainMsg(GCM_RECEIVED_APPMSG,GCM_USER_ACTION,Psender,Integer.toString(PactionID),PmsgData,Integer.toString(Ptime));//~9625I~
//      if (PswWaiterBlock) //no need to block GCM_NEXT_PLAYER will be blocked by WAIT_ON       //~v@@@R~//~9625R~
//          if (AG.aUADelayed.isWaiterBlocking(msg))                             //~v@@@I~//~9625R~
//          	return;                                            //~v@@@I~//~9625R~
        GameViewHandler.sendMsgDelayed(msg,Ptime);           //~v@@@R~//~9625R~//~0229R~
//*!!UserAction.prevActionID is reset by original actionID         //~0229I~
//      GameViewHandler.handleMsgDirect(msg); //not through Queue to avoid other action(DrawReqDlgHW) interrupt//~0229R~
    }                                                              //~v@@@I~
    //***********************************************************************//~v@@@I~
	public static void postDelayedActionMsg(int Ptime/*milisec*/,int PactionID,String PmsgData)//~v@@@I~//~9624R~
    {                                                              //~v@@@I~
    	if (AG.aUADelayed.swStop)                                  //~9B20R~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedActionMsg strp return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        if (Dump.Y) Dump.println("UADelayed.postDelayedActionMsg actionID="+PactionID+"="+GCMsgID.getEnum(PactionID)+",data="+PmsgData);//~v@@@I~
        Message msg=GameViewHandler.obtainMsg(PactionID,PmsgData); //~v@@@I~
        GameViewHandler.sendMsgDelayed(msg,Ptime);           //~v@@@R~
    }                                                              //~v@@@I~
    //***********************************************************************//~v@@@I~
	public  static void postDelayedActionMsg(int Ptime/*milisec*/,int PactionID,int Pparm1,int Pparm2,int Pparm3)//~v@@@R~//~9624R~
    {                                                              //~v@@@I~
    	if (AG.aUADelayed.swStop)                                  //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.postDelayedActionMsg intp return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        if (Dump.Y) Dump.println("UADelayed.postDelayedActionMsg actionID="+PactionID+"="+GCMsgID.getEnum(PactionID)+",parm1="+Pparm1+",parm2="+Pparm2+",parm3="+Pparm3);//~v@@@R~
        Message msg=GameViewHandler.obtainMsg(PactionID,Pparm1,Pparm2,Pparm3);//~v@@@R~
        GameViewHandler.sendMsgDelayed(msg,Ptime);                 //~v@@@I~
    }                                                              //~v@@@I~
    //***********************************************************************//~v@@@I~
    //*on Server/Client                                            //~v@@@I~
    //***********************************************************************//~v@@@I~
	public boolean isDupRonOK(int Pplayer,TileData PtdComplete)    //~v@@@R~
    {                                                              //~v@@@I~
//        if (TD==null)                                            //~v@@@R~
//        {                                                        //~v@@@R~
//            if (Dump.Y) Dump.println("UADelayed.isDupRonOK TD:null");//~v@@@R~
//            return false;                                        //~v@@@R~
//        }                                                        //~v@@@R~
        boolean rc=(PtdComplete.flag & (TDF_INTERCEPTED-TDF_RON))==0 && PtdComplete.isLockedPonKan();   //case of player is not of You//~v@@@R~
        if (Dump.Y) Dump.println("UADelayed.isDupRonOK rc="+rc+",tdComplete="+PtdComplete.toString());//~v@@@R~
        return rc;
    }                                                              //~v@@@I~
    //***********************************************************************//~0404I~
	public boolean isDupRonOKKan(int Pplayer,TileData PtdComplete) //~0404I~
    {                                                              //~0404I~
        boolean rc=(PtdComplete.flag & (TDF_INTERCEPTED-TDF_RON))==0 && PtdComplete.isLocked();//~0404I~
        if (Dump.Y) Dump.println("UADelayed.isDupRonOKKan rc="+rc+",tdComplete="+PtdComplete.toString());//~0404I~
        return rc;                                                 //~0404I~
    }                                                              //~0404I~
//    //***********************************************************************//~v@@@I~//~9623R~
//    //*on server,(U)Handler callback after timeout                                                   //~v@@@I~//~9622R~//~9623R~
//    //***********************************************************************//~v@@@I~//~9623R~
//    @Override                                                      //~v@@@I~//~9623R~
//    public void run()                                                   //~v@@@I~//~9623R~
//    {                                                              //~v@@@I~//~9623R~
//        int ctrWaiting;                                                //~v@@@I~//~9623R~
//    //*********************                                        //~v@@@I~//~9623R~
//        if (Dump.Y) Dump.println("UADelayed.run this="+this.toString());//~v@@@I~//~9623R~
//        if (swStop)                                                //~v@@@I~//~9623R~
//        {                                                          //~v@@@I~//~9623R~
//            if (Dump.Y) Dump.println("UADelayed.run return by swStop");//~v@@@I~//~9623R~
//            return;                                                //~v@@@I~//~9623R~
//        }                                                          //~v@@@I~//~9623R~
//        int status=Status.getGameStatus();                         //~v@@@I~//~9623R~
//        if (Dump.Y) Dump.println("UADelayed.run after timeout actionid="+actionID+"="+GCMsgID.getEnum(actionID)+",status="+status);//~v@@@R~//~9623R~
//        if (status!=GS_GAME_STARTED)                               //~v@@@I~//~9623R~
//        {                                                          //~v@@@I~//~9623R~
//            if (Dump.Y) Dump.println("UADelayed.run after timeout endgame requested");//~v@@@I~//~9623R~
//                return;                                            //~v@@@I~//~9623R~
//        }                                                          //~v@@@I~//~9623R~
//        synchronized(swWaiting)                                    //~v@@@I~//~9623R~
//        {                                                          //~v@@@I~//~9623R~
//            ctrWaiting=ctrWaitingPlayer;                           //~v@@@I~//~9623R~
//        }                                                          //~v@@@I~//~9623R~
//        if (Dump.Y) Dump.println("UADelayed.run ctrWaiting="+ctrWaiting);//~v@@@I~//~9623R~
////        if (ctrWaiting!=0)                                       //~v@@@R~//~9623R~
////        {                                                        //~v@@@R~//~9623R~
////            delayAction(actionID);                               //~v@@@R~//~9623R~
////        }                                                        //~v@@@R~//~9623R~
////        else                                                     //~v@@@R~//~9623R~
////        {                                                        //~v@@@R~//~9623R~
//            actionWaiting=0;                                       //~v@@@I~//~9623R~
//            switch(actionID)                                       //~v@@@R~//~9623R~
//            {                                                      //~v@@@R~//~9623R~
//            case GCM_NEXT_PLAYER_PONKAN:                           //~v@@@I~//~9623R~
//                if (swActionReleasedPonKan)                        //~v@@@I~//~9623R~
//                {                                                  //~v@@@I~//~9623R~
//                    if (Dump.Y) Dump.println("UADelayed.delayAction next_player_ponkan resheduled");//~v@@@I~//~9623R~
//                    swActionReleasedPonKan=false;                  //~v@@@I~//~9623R~
//                    postDelayed(delayPonKan,GCM_NEXT_PLAYER_PONKAN,player,TD);//~v@@@I~//~9623R~
//                    break;                                         //~v@@@I~//~9623R~
//                }                                                  //~v@@@I~//~9623R~
//                delayedNextPlayerPonKan();                         //~v@@@I~//~9623R~
//                break;                                             //~v@@@I~//~9623R~
//            case GCM_NEXT_PLAYER:                                  //~v@@@R~//~9623R~
//                if (swActionReleased)                              //~v@@@I~//~9623R~
//                {                                                  //~v@@@I~//~9623R~
//                    if (Dump.Y) Dump.println("UADelayed.delayAction next_player resheduled");//~v@@@I~//~9623R~
//                    swActionReleased=false;                        //~v@@@I~//~9623R~
//                    postDelayed(delayTake,GCM_NEXT_PLAYER,player,TD);//~v@@@R~//~9623R~
//                    break;                                         //~v@@@I~//~9623R~
//                }                                                  //~v@@@I~//~9623R~
//                delayedNextPlayer();                               //~v@@@R~//~9623R~
//                break;                                             //~v@@@R~//~9623R~
////            case GCM_TAKE:      //for Robot auto take            //~v@@@R~//~9623R~
////                delayedRobotAutoTake();                          //~v@@@R~//~9623R~
////                break;                                           //~v@@@R~//~9623R~
////            case GCM_DISCARD:   //for Robot auto discard         //~v@@@R~//~9623R~
////                delayedRobotDiscard();                           //~v@@@R~//~9623R~
////                break;                                           //~v@@@R~//~9623R~
//            default:                                               //~v@@@I~//~9623R~
//                if (Dump.Y) Dump.println("UADelayed.delayAction unknown actionID="+actionID);//~v@@@I~//~9623R~
//            }                                                      //~v@@@R~//~9623R~
////        }                                                        //~v@@@R~//~9623R~
//    }                                                              //~v@@@I~//~9623R~
    //***********************************************************************//~9627I~
    //*on Client,from UATake.autoDiscardTimeout at received WAIT_OFF//~9627I~
    //***********************************************************************//~9627I~
    public void sendMsgEmulatedToClient(int Pmsgid,int Pplayer) //~9627I~
    {                                                              //~9627I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.sendMsgEmulatedToClient return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
		int eswn=Accounts.playerToEswn(Pplayer);                   //~9627I~
        if (AG.aAccounts.isDummyByCurrentEswn(eswn))               //~0221I~
        {                                                          //~0221I~
			if (Dump.Y) Dump.println("UADelayed.sendMsgEmulatedToClient bypass robot eswn="+eswn+",msgid="+Pmsgid+",player="+Pplayer);//~0221I~//~0223R~
        	return;                                                //~0221I~
        }                                                          //~0221I~
    	String s=eswn+MSG_SEPAPP2+Pmsgid;                          //~9627R~
		if (Dump.Y) Dump.println("UADelayed.sendMsgEmulatedToClient msgid="+Pmsgid+",player="+Pplayer+",data="+s);//~9627R~//~0223R~
//      AG.aAccounts.sendToTheClient(Pplayer,GCM_WAIT_RELEASE_ACTION,s);//~9627R~
        UA.sendToTheClient(Pplayer,GCM_WAIT_RELEASE_ACTION,s);	//to actionReleaseWait on Client//~0223R~
    }                                                              //~9627I~
    //***********************************************************************//~9625I~
    //*on Client,at received WAIT_OFF                              //~9627I~
    //***********************************************************************//~9627I~
    private boolean rescheduleMsgClient(int Paction/*delayedAction*/,int Pplayer)                        //~9625I~//~9627R~//~9B16R~
    {                                                              //~9625I~
		if (Dump.Y) Dump.println("UADelayed.rescheduleMsgClient action="+Paction+",player="+Pplayer);//~9627I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.rescheduleMsgClient return by swStop");//~9B20I~
        	return false;                                          //~9B20I~
        }                                                          //~9B20I~
        boolean rc=false;                                          //~9627I~
        switch(Paction)                                            //~9627I~
        {                                                          //~9627I~
        case GCM_TIMEOUT_AUTODISCARD:                              //~9627I~
		    rc=UA.UAT.autoDiscardTimeoutClient(Pplayer);           //~9627R~
        	break;                                                 //~9627I~
        case GCM_TIMEOUT_AUTOTAKE:                                 //~9627I~
            rc=UA.UAD.autoTakeTimeoutClient(Pplayer);              //~9627R~
        	break;                                                 //~9627I~
        case GCM_TIMEOUT_AUTOTAKE_KAN:                             //~9627I~
            rc=UA.UAK.autoTakeKanTimeoutClient(Pplayer);           //~9627R~
        	break;                                                 //~9627I~
        }                                                          //~9627I~
		if (Dump.Y) Dump.println("UADelayed.rescheduleMsgClient rc="+rc);//~9627I~
        return rc;                                                 //~9627I~
    }                                                              //~9627I~
    //***********************************************************************//~9628I~
    //*on Server on GVH thread                                                   //~9628I~//~9629R~
    //***********************************************************************//~9628I~
    private boolean rescheduleAutoActionByWaitOff(Message Pmsg)             //~9628R~//~9629R~
    {                                                              //~9628I~
    	boolean rc;                                                //~9628R~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.rescheduleAutoActionByWaitOff return by swStop");//~9B20I~
        	return false;                                          //~9B20I~
        }                                                          //~9B20I~
		if (Dump.Y) Dump.println("UADelayed.rescheduleAutoActionByWaitOff delayedAction="+delayedAction);//~9628I~//~9629R~
		if (Dump.Y) Dump.println("UADelayed.rescheduleAutoActionByWaitOff Pmsg="+(Pmsg==null?"null":Pmsg.toString()));//~9628I~//~9629R~
        if (Pmsg==null)                                            //~9628I~
        	return false;                                                //~9628I~
    	switch(Pmsg.what)                                          //~9628R~
        {                                                          //~9628I~
        case GCM_TIMEOUT_AUTODISCARD:    //sendmsg Discrd          //~9628R~
			rc=autoDiscardTimeoutByWaitOff(Pmsg);                   //~9628I~
            break;                                                 //~9628I~
        case GCM_TIMEOUT_AUTOTAKE:       //sendmsg Take            //~9628R~
			rc=autoTakeTimeoutByWaitOff(Pmsg);                      //~9628I~
            break;                                                 //~9628I~
        case GCM_TIMEOUT_AUTOTAKE_KAN:   //sendmsg Take            //~9628R~
			rc=autoTakeKanTimeoutByWaitOff(Pmsg);                   //~9628I~
        	break;                                                 //~9628I~
        default:                                                   //~9628I~
        	rc=false;                                              //~9628I~
        }                                                          //~9628I~
		if (Dump.Y) Dump.println("UADelayed.rescheduleAutoActionByWaitOff rc="+rc);//~9628I~//~9629R~
        return rc;
    }                                                              //~9628I~
    //***********************************************************************//~9627I~
    private void rescheduleMsgFromTimer(Message Pmsg)                        //~9627I~//~9628R~//~9629R~//~9B18R~//~0225R~
    {                                                              //~9627I~
    	int timedelay;                                                  //~9625I~
        int player;                                                //~9626I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.rescheduleMsgFromTimer return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
//        if (Pmsg.what==GCM_RECEIVED_APPMSG) //robot              //~9625R~
//        {                                                        //~9625R~
//            Bundle data=Pmsg.getData();                          //~9625R~
//            String strTime=data.getString(GVPARM3,"");           //~9625R~
//            timedelay=Utils.parseInt(strTime,0);                 //~9625R~
//        }                                                        //~9625R~
//        else                                                     //~9625R~
//        {                                                        //~9625R~
        	int[] intp=getMsgData(Pmsg);                           //~9625R~
        	timedelay=intp[POSPARM_TIME];                          //~9625R~
        	player=intp[POSPARM_PLAYER];                           //~9626I~
//        }                                                        //~9625R~
		if (Dump.Y) Dump.println("UADelayed.rescheduleMsgFromTimer what="+Pmsg.what+",time="+timedelay);//~9625R~//~9628R~//~9629R~
//      showWaited(false/*swDelayed*/,player,true/*PswInvalidate*/,true/*PswOff*/,Pmsg);    //~9625R~//~9626R~//~9627R~//~9629R~
        int action=Pmsg.what;                                      //~9629I~
    	String gmsg=showWaited(player,action,true/*PswOff*/);       //~9629I~
//  	GameViewHandler.sendMsgDelayed(Pmsg,timedelay);	//callback to timeout//~9625R~//~9626R~
        if (gmsg!=null)                                            //~9629I~
			setGmsg(Pmsg,gmsg);                                    //~9629I~
    	sendMsgDelayed(Pmsg,timedelay);	//callback to timeout      //~9626I~
	}                                                              //~9625I~
    //***********************************************************************//~9B23I~
    protected void rescheduleMsg2Touch(Message Pmsg)               //~9B23I~
    {                                                              //~9B23I~
    	if (swStop)                                                //~9B23I~
        {                                                          //~9B23I~
	    	if (Dump.Y) Dump.println("UADelayed.rescheduleMsg2Touch return by swStop");//~9B23I~
        	return;                                                //~9B23I~
        }                                                          //~9B23I~
		if (Dump.Y) Dump.println("UADelayed.rescheduleMsg2Touch msg="+Utils.toString(Pmsg));//~9B23I~
        if (Pmsg==null)                                            //~9B23I~
        	return;                                                //~9B23I~
        int[] intp=getMsgData(Pmsg);                               //~9B23M~
        int timedelay=intp[POSPARM_TIME];                          //~9B23I~
        switch(Pmsg.what)                                          //~9B23I~
        {                                                          //~9B23I~
//      case GCM_NEXT_PLAYER:     //notifytimeout's action         //~9B23R~
        case GCM_TIMEOUT_TO_TAKE: //msg's action                   //~9B23I~
        	timedelay=delayTake;                                   //~9B23I~
        	break;                                                 //~9B23I~
        case GCM_TIMEOUT_AUTODISCARD:	//for take+ron,block discard//~9B23I~
        	timedelay=timeoutAutoDiscard;                          //~9B23I~
        	break;                              //~9B23I~
        case GCM_TIMEOUT_AUTOTAKE:                                 //~9B23I~
//      	timedelay=timeoutAutoTake;                             //~9B23I~//~9C09R~
        	timedelay=intp[POSPARM_TIMEOUT_AUTOTAKE];              //~9C09I~
        	break;                                                 //~9B23I~
        }                                                          //~9B23I~
	    if (Dump.Y) Dump.println("UADelayed.rescheduleMsg2Touch timedelay="+timedelay);//~9B23I~
        if (timedelay!=0)                                          //~9C09I~
    	sendMsgDelayed(Pmsg,timedelay);	                           //~9B23R~
	}                                                              //~9B23I~
    //***********************************************************************//~9B23I~
    private boolean isStopMsg2Touch(int PactionID)                 //~9B23I~
    {                                                              //~9B23I~
//  	boolean rc=(PactionID==GCM_NEXT_PLAYER                     //~9B23R~
//  	boolean rc=(PactionID==GCM_TIMEOUT_TO_TAKE                 //~9B23I~//~9B26R~
//          	|| PactionID==GCM_TIMEOUT_AUTODISCARD	//for take+ron,block discard//~9B23I~//~9B26R~
    	boolean rc=(PactionID==GCM_TIMEOUT_AUTODISCARD	//for take+ron,block discard//~9B26I~
            	|| PactionID==GCM_TIMEOUT_AUTOTAKE);                //~9B23I~
		if (Dump.Y) Dump.println("UADelayed.isStopMsg2Touch rc="+rc+",actionID="+PactionID);//~9B23I~
        return rc;                                                 //~9B23I~
    }                                                              //~9B23I~
    //***********************************************************************//~9629I~
    private void setGmsg(Message Pmsg,String Pgmsg)                //~9629I~
    {                                                              //~9629I~
		if (Dump.Y) Dump.println("UADelayed.setGmsg what="+Pmsg.what+",gmsg="+Pgmsg);//~9629R~
        Bundle bundle=Pmsg.getData();                              //~9629I~
	    bundle.putString(GVPARM3,Pgmsg);                            //~9629I~
    }                                                              //~9629I~
    //***********************************************************************//~9629I~
    private String getGmsg(Message Pmsg)                           //~9629I~
    {                                                              //~9629I~
        Bundle bundle=Pmsg.getData();                              //~9629I~
	    String gmsg=bundle.getString(GVPARM3,null);                //~9629I~
		if (Dump.Y) Dump.println("UADelayed.getGmsg what="+Pmsg.what+",gmsg="+(gmsg==null?"null":gmsg));//~9629R~
        return gmsg;                                                //~9629I~
    }                                                              //~9629I~
    //***********************************************************************//~9629I~
    private void showRescheduledGmsg(Message Pmsg)                 //~9629R~
    {                                                              //~9629I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.showRescheduledMsg return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
		if (Dump.Y) Dump.println("UADelayed.showRescheduleGmsg what="+Pmsg.what);//~9629I~
        int[] intp=GameViewHandler.getMsgIntData(Pmsg);            //~9629I~
        int player=intp[POSPARM_PLAYER];                           //~9629I~
		String gmsg=getGmsg(Pmsg);                                 //~9629I~
        if (gmsg!=null)                                            //~9629I~
			UserAction.showInfoAllEswn(0/*opt*/,player,gmsg);//TODO NOLANG support//~0305R~
    }                                                              //~9629I~
    //***********************************************************************//~9B22I~
    protected void removePendingMsg2Touch()                        //~9B22I~
    {                                                              //~9B22I~
	    if (Dump.Y) Dump.println("UADelayed.removePendingMsg2Touch msgWaiting="+Utils.toString(msgWaiting));//~9B22I~
        synchronized(swWaiting)                                    //~9B22I~
        {                                                          //~9B22I~
            if (msgWaiting!=null)                                  //~9B22I~
            {                                                      //~9B22I~
	        	if (isStopMsg2Touch(msgWaiting.what))              //~9B23R~
                	msgWaiting=null;                               //~9B22I~
            }                                                      //~9B22I~
        }                                                          //~9B22I~
    }                                                              //~9B22I~
    //***********************************************************************//~9623I~
    //*from GVH,after time exausted, chk waiting Player           //~9624I~//~9629R~
    //*return true:blocked                                         //~9625I~
    //***********************************************************************//~9624I~
//  private void notifyTimeout(Message Pmsg,int PactionID,int Pplayer,TileData Ptd)//~9623R~//~9624R~
    protected boolean notifyTimeout(Message Pmsg,int PactionID,int[] PintParm)//~9624I~//~9625R~//~9B18R~
    {                                                              //~9623I~
    	int ctrWaiting;                                            //~9623I~
        int player,ctrTaken,ctrDiscarded;                          //~9624I~
        int delaytime;                                             //~9625I~
        boolean rc=true;	//blocked                              //~9625I~
    //*********************                                        //~9623I~
    	if (swStop)                                                //~9623I~//~9B20M~
        {                                                          //~9623I~//~9B20M~
	    	if (Dump.Y) Dump.println("UADelayed.notifyTimeout return by swStop");//~9623I~//~9B20M~
        	return true; //blocked                                               //~9623I~//~9625R~//~9B20I~
        }                                                          //~9623I~//~9B20M~
//      player=PintParm[0]; ctrTaken=PintParm[1]; ctrDiscarded=PintParm[2];//~9624I~//~9625R~
        delaytime=PintParm[POSPARM_TIME];                          //~9625I~
        player=PintParm[POSPARM_PLAYER];                           //~9625I~
        ctrTaken=PintParm[POSPARM_CTRTAKEN];                       //~9625I~
        ctrDiscarded=PintParm[POSPARM_CTRDISCARDED];               //~9625I~
	    if (Dump.Y) Dump.println("UADelayed.notifyTimeout actionID="+PactionID+",player="+player+",intparm="+Arrays.toString(PintParm));//~9623I~//~9624R~
    	int status=Status.getGameStatus();                         //~9623I~
    	if (Dump.Y) Dump.println("UADelayed.notifyTimeout after timeout actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",status="+status);//~9623I~
        if (status!=GS_GAME_STARTED)                               //~9623I~
        {                                                          //~9623I~
	    	if (Dump.Y) Dump.println("UADelayed.notifyTimeout after timeout endgame requested");//~9623I~
            return rc;                                            //~9623I~//~9625R~//~9626R~
        }                                                          //~9623I~
      	if (Status.isIssuedRon())                                  //~9626I~
        {                                                          //~9626I~
	    	if (Dump.Y) Dump.println("UADelayed.notifyTimeout issuedRon");//~9626I~
          if (PactionID==GCM_TIMEOUT_TO_TAKABLE_RINSHAN            //~0404R~
          ||  PactionID==GCM_NEXT_PLAYER_PONKAN)                   //~0404R~
          {                                                        //~0404I~
	    	if (Dump.Y) Dump.println("UADelayed.notifyTimeout ignore issuedRon");//~0404I~
          }                                                        //~0404I~
          else                                                     //~0404I~
            return rc;                                             //~9626I~
        }                                                          //~9626I~
      	if (UARestart.isIOExceptionBeforeRestart())                //~9A29I~
        {                                                          //~9A29I~
	    	if (Dump.Y) Dump.println("UADelayed.notifyTimeout ioException");//~9A29I~
            return rc;                                             //~9A29I~
        }                                                          //~9A29I~
        synchronized(swWaiting)                                    //~9623I~
        {                                                          //~9623I~
            ctrWaiting=ctrWaitingPlayer;                           //~9623I~
        }                                                          //~9623I~
	    if (Dump.Y) Dump.println("UADelayed.notifyTimeout ctrWaiting="+ctrWaiting+",stopAutoFix="+stopAutoFix+",stopAutoCtr="+stopAutoCtr+",swStopAuto2Touch="+swStopAuto2Touch);//~9623I~//~9624R~//~9B13R~//~9B21R~//~9B28R~
        boolean swBlock=ctrWaiting!=0;                             //~9701I~
//      if (stopAutoCtr!=0)                                        //~9701I~//~9702R~//~9703R~
        if (stopAutoFix || stopAutoCtr!=0)                         //~9703I~//~9B18R~//~9B22R~
        {                                                          //~9701I~//~9702R~
//          if (PactionID==GCM_TIMEOUT_AUTODISCARD || PactionID==GCM_TIMEOUT_AUTOTAKE)//~9701R~//~9702R~//~9B14R~
//              swBlock=true;                                      //~9701I~//~9702R~//~9B14R~
//          if (PactionID==GCM_NEXT_PLAYER)  //stop nextplayer(robot or not robot) TAKE//~9B13I~//~9B14R~
                swBlock=true;                                      //~9B13I~
        }                                                          //~9701I~//~9702R~
        else                                                       //~9B22I~
//      if (stopAuto2Touch)                                        //~9B22I~//~9B28R~
        if (swStopAuto2Touch)                                      //~9B28I~
        {                                                          //~9B22I~
		    if (Dump.Y) Dump.println("UADelayed.notifyTimeout stopAuto2Touch action="+PactionID);//~9B22I~
          	if (PactionID==GCM_TIMEOUT_BLOCK)                      //~9C05I~
          	{                                                      //~9C05I~
            	AG.aUADelayed.timeoutBlockRelease(PintParm);	//callback UADelayed2//~9C05I~
                swBlock=false;                                     //~9C05I~
            }                                                      //~9C05I~
            else                                                   //~9C05I~
	        if (isStopMsg2Touch(PactionID))                        //~9B23I~
            {                                                      //~9B22I~
			    if (Dump.Y) Dump.println("UADelayed.notifyTimeout stopAuto2Touch action blocked");//~9B22I~
                swBlock=true;                                      //~9B22I~
            }                                                      //~9B22I~
        }                                                          //~9B22I~
//      if (ctrWaiting!=0)                                         //~9623I~//~9701R~
        if (swBlock)                                               //~9701I~
        {                                                          //~9623I~
//      	msgWaiting=Pmsg;	//reschedule wait released         //~9623I~//~9624R~
//      	msgWaiting=new Message();	//reschedule wait released TODO//~9624R~
//      	msgWaiting.copyFrom(Pmsg);	//reschedule wait released TODO//~9624R~
//      	Message ms=Pmsg;                                       //~9624I~
	        msgWaiting=renewalMsg(Pmsg);    //Pmsg will be recycled after some time elapsed//~9624I~
//          showWaited(false/*PswInvalidate*/,false/*PswOff*/,msgWaiting);              //~9624I~//~9625R~//~9626R~//~9627R~
		    if (Dump.Y) Dump.println("UADelayed.notifyTimeout waited msg="+msgWaiting.toString()+",Pmsg="+Pmsg.toString());//~9624R~
        }                                                          //~9623I~
        else                                                       //~9623I~
        {                                                          //~9623I~
//        	actionWaiting=0;                                       //~9623I~
            switch(PactionID)                                      //~9623I~
            {                                                      //~9623I~
            case GCM_NEXT_PLAYER_PONKAN:                           //~9623I~
//                if (swActionReleasedPonKan)                        //~9623I~//~9624R~
//                {                                                  //~9623I~//~9624R~
//                    if (Dump.Y) Dump.println("UADelayed.notifyTimeout next_player_ponkan resheduled");//~9623I~//~9624R~
//                    swActionReleasedPonKan=false;                  //~9623I~//~9624R~
////                  postDelayed(delayPonKan,GCM_NEXT_PLAYER_PONKAN,player,TD);//~9623I~//~9624R~
//                    postDelayedPonKan(Pplayer,Ptd);                 //~9623I~//~9624R~
//                    break;                                         //~9623I~//~9624R~
//                }                                                  //~9623I~//~9624R~
//              delayedNextPlayerPonKan(Pplayer,Ptd);              //~9623I~//~9624R~
                delayedNextPlayerPonKan(player,ctrTaken,ctrDiscarded);//~9624I~
                break;                                             //~9623I~
            case GCM_TIMEOUT_TO_TAKABLE_RINSHAN:                   //~0403R~
                delayedRinshanTakable(player,ctrTaken,ctrDiscarded);//~0403I~
                break;                                             //~0403I~
//          case GCM_NEXT_PLAYER:                                  //~9623I~//~9B23R~
            case GCM_TIMEOUT_TO_TAKE:                              //~9B23I~
//                if (swActionReleased)                              //~9623I~//~9624R~
//                {                                                  //~9623I~//~9624R~
//                    if (Dump.Y) Dump.println("UADelayed.notifyTimeout next_player resheduled");//~9623I~//~9624R~
//                    swActionReleased=false;                        //~9623I~//~9624R~
////                  postDelayed(delayTake,GCM_NEXT_PLAYER,player,TD);//~9623I~//~9624R~
//                    postDelayedTake(Pplayer,Ptd);                   //~9623I~//~9624R~
//                    break;                                         //~9623I~//~9624R~
//                }                                                  //~9623I~//~9624R~
//              delayedNextPlayer(Pplayer,Ptd);                    //~9623I~//~9624R~
                delayedNextPlayer(player,ctrTaken,ctrDiscarded);   //~9624I~
                break;                                             //~9623I~
            case GCM_ENDGAME_DRAWN:                                //~9624I~
                delayedLastDrawn(player);                          //~9624I~
                break;
            case GCM_TIMEOUT_AUTODISCARD:                          //~9625I~
            case GCM_TIMEOUT_AUTOTAKE:                             //~9625I~
            case GCM_TIMEOUT_AUTOTAKE_KAN:                         //~9625I~
            case GCM_TIMEOUT_BLOCK:                                //~9C05I~
            //*returned false,do process                           //~9625I~
                break;                                             //~9625I~
            default:                                               //~9623I~
    			if (Dump.Y) Dump.println("UADelayed.notifyTimeout unknown actionID="+PactionID);//~9623I~//~9625R~
            }                                                      //~9623I~
            rc=false;	//not blcoked                              //~9625I~
        }                                                          //~9623I~
    	if (Dump.Y) Dump.println("UADelayed.notifyTimeout rc="+rc);//~9625I~
        return rc;                                                 //~9625I~
    }                                                              //~9623I~
//    //***********************************************************************//~v@@@I~
//    private void delayAction(int PactionID)                        //~v@@@I~
//    {                                                              //~v@@@I~
//    	if (Dump.Y) Dump.println("UADelayed.delayAction actionID="+PactionID);//~v@@@I~
//        actionWaiting=PactionID;                                   //~v@@@I~
//    }                                                              //~v@@@I~
    //***********************************************************************//~v@@@I~
    //*on Server                                                   //~9628I~
    //from notifyTimeout of GCM_NEXT_PLAYER_PONKAN                 //~9B16I~
    //***********************************************************************//~9628I~
//  private void delayedNextPlayerPonKan(int Pplayer,TileData Ptd)                         //~v@@@I~//~9623R~//~9624R~
    protected void delayedNextPlayerPonKan(int Pplayer,int PctrTaken,int PctrDiscarded)//~9624I~//~9B18R~
    {                                                              //~v@@@I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.delayedNextPlayerPonKan return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
    	if (Dump.Y) Dump.println("UADelayed.delayedNextPlayerPonKan player="+Pplayer);//~9624R~
//      TileData lastTD=AG.aPlayers.tileLastDiscarded;             //~v@@@I~//~9624R~
//  	if (Dump.Y) Dump.println("UADelayed.delayedNextPlayerPonKan lastDiscarded="+TileData.toString(AG.aPlayers.tileLastDiscarded)+",ctrTakenAll="+AG.aPlayers.ctrTakenAll+",currentPlayer="+AG.aPlayers.getCurrentPlayer());//~9624R~
//      TD.setLockPonKan(false/*unlock*/);                         //~v@@@I~//~9623R~
//      if  (TileData.TDCompare(TD,lastTD,true/*swCompareCtr*/)!=0 //~9624I~
//      ||  (TD.flag & TDF_INTERCEPTED)!=0                         //~v@@@I~//~9623R~
//      ||  (lastTD.flag & TDF_INTERCEPTED)!=0                     //~9623I~//~9624R~
//      if  (AG.aPlayers.isActionDone(Pplayer,PctrTaken,PctrDiscarded)//~9624I~//~0404R~
        if  (AG.aPlayers.isActionDoneExceptRon(Pplayer,PctrTaken,PctrDiscarded)//~0404I~
                )                                                  //~v@@@I~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("UADelayed.delayedNextPlayerPonKan tile intercepted or take advanced");//~9624R~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
//  	postDelayed(delayTake,GCM_NEXT_PLAYER,player,TD);          //~v@@@I~//~9623R~
//  	postDelayedTake(player,Ptd); //start next timelimit                              //~9623R~//~9624R~
    	postDelayedTakable(Pplayer); //start next timelimit            //~9624R~//~9628R~
        GameViewHandler.sendMsg(GCM_NEXT_PLAYER_PONKAN,Pplayer,0,0);	//schedule as UserAction,sendtoclient//~v@@@R~//~9624R~
    }                                                              //~v@@@I~
    //***********************************************************************//~0403I~
    //*on Server                                                   //~0403I~
    //from notifyTimeout of GCM_TIMEOUT_TO_TAKABLE_RINSHAN         //~0403R~
    //***********************************************************************//~0403I~
    protected void delayedRinshanTakable(int Pplayer,int PctrTaken,int PctrDiscarded)//~0403I~
    {                                                              //~0403I~
    	if (swStop)                                                //~0403I~
        {                                                          //~0403I~
	    	if (Dump.Y) Dump.println("UADelayed.delayedRinshanTakable return by swStop");//~0403I~
        	return;                                                //~0403I~
        }                                                          //~0403I~
    	if (Dump.Y) Dump.println("UADelayed.delayedRinshanTakable player="+Pplayer+",ctrTake="+PctrTaken+",ctrDiscarded="+PctrDiscarded);//~0403I~
//      if  (AG.aPlayers.isActionDone(Pplayer,PctrTaken,PctrDiscarded)//~0403I~//~0404R~
        if  (AG.aPlayers.isActionDoneExceptRon(Pplayer,PctrTaken,PctrDiscarded)//~0404I~
                )                                                  //~0403I~
        {                                                          //~0403I~
        	if (Dump.Y) Dump.println("UADelayed.delayedRinshanTakable tile intercepted or take advanced");//~0403I~
            return;                                                //~0403I~
        }                                                          //~0403I~
        UA.UAK.setTimeout(true/*timeout*/,true/*swServer*/,Pplayer);	//autotake timeout//~0403R~
    }                                                              //~0403I~
    //***********************************************************************//~v@@@I~//~9624M~
    //*on Server                                                   //~9628I~
    //***********************************************************************//~9628I~
//  private void delayedNextPlayer(int Pplayer,TileData Ptd)                               //~v@@@R~//~9623R~//~9624M~
    protected void delayedNextPlayer(int Pplayer,int PctrTaken,int PctrDiscarded)//~9624M~//~9B18R~
    {                                                              //~v@@@I~//~9624M~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.delayedNextPlayer return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
    	if (Dump.Y) Dump.println("UADelayed.delayedNextPlayer player="+Pplayer);//~v@@@R~//~9624M~
//      TD.setLock(false/*unlock*/);                                  //~v@@@I~//~9624M~
//      if  (TileData.TDCompare(TD,lastTD,true/*swCompareCtr*/)!=0 //~v@@@R~//~9624M~
//      ||  (TD.flag & TDF_INTERCEPTED)!=0                         //~9624M~
        if  (AG.aPlayers.isActionDone(Pplayer,PctrTaken,PctrDiscarded)//~9624M~
                )//~v@@@I~                                         //~9624M~
        {                                                          //~v@@@I~//~9624M~
        	if (Dump.Y) Dump.println("UADelayed.delayedNextPlayer tile intercepted");//~v@@@R~//~9624R~
            return;                                                //~v@@@I~//~9624M~
        }                                                          //~v@@@I~//~9624M~
		if ((TestOption.option & TO_DRAWNREQDLG_LAST)!=0)          //~v@@@I~//~9624M~
        {                                                          //~v@@@I~//~9624M~
        	if (AG.aTiles.getCurrentTilePos()>TILECTR_KEEPLEFT+PLAYERS*HANDCTR+4)//~v@@@R~//~9624M~
            {                                                      //~v@@@I~//~9624M~
				int currentEswn=Accounts.getCurrentEswn();         //~9B24I~
				postDelayedActionMsg(delayLast,GCM_ENDGAME_DRAWN,currentEswn,ENDGAME_DRAWN_LAST,0);//~v@@@R~//~9624M~
            	return;                                            //~v@@@R~//~9624M~
            }                                                      //~v@@@I~//~9624M~
        }                                                          //~v@@@I~//~9624M~
//      if ((TD.flag & TDF_LAST)!=0)                               //~v@@@I~//~9623R~//~9624M~
//      {                                                          //~v@@@I~//~9623R~//~9624M~
//  		postDelayedActionMsg(delayLast,GCM_ENDGAME_DRAWN,currentEswn,ENDGAME_DRAWN_LAST,0);//~v@@@R~//~9623R~//~9624M~
//          return;                                                //~v@@@I~//~9623R~//~9624M~
//      }                                                          //~v@@@I~//~9623R~//~9624M~
//        UA.delayedNextPlayer(true/*swServer*/,player);           //~v@@@R~//~9624M~
//        String data=UserAction.makeMsgDataToClient(player);      //~v@@@R~//~9624M~
//        UA.sendToClient(true/*PswSendAll*/,true/*PswRobot*/,GCM_NEXT_PLAYER,player,data);//~v@@@R~//~9624M~
//        GameViewHandler.sendMsg(GCM_NEXT_PLAYER,player,0,0);	//on server//~v@@@R~//~9624M~
          GameViewHandler.sendMsg(GCM_NEXT_PLAYER,Players.nextPlayer(Pplayer),0,0);	//on server,to UADiscard.nextPlayer()//~v@@@I~//~9624M~//~9628R~
        TileData lastTD=AG.aPlayers.tileLastDiscarded;               //~v@@@I~//~9624M~
//      if ((TD.flag & TDF_LAST)!=0)                               //~9623I~//~9624M~
        if (lastTD!=null && (lastTD.flag & TDF_LAST)!=0)           //~9624R~
        {                                                          //~9623I~//~9624M~
//  		postDelayedActionMsg(delayLast,GCM_ENDGAME_DRAWN,currentEswn,ENDGAME_DRAWN_LAST,0);//~9623I~//~9624M~
    		postDelayedLastDrawn(Pplayer); //start next timelimit  //~9624M~
        }                                                          //~9623I~//~9624M~
    }                                                              //~v@@@I~//~9624M~
    //***********************************************************************
    protected void delayedLastDrawn(int Pplayer)                     //~9624I~//~9B18R~
    {
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.delayedLastDrawn return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
    	if (Dump.Y) Dump.println("UADelayed.delayedLastDrawn player="+Pplayer);//~9624I~
//      TileData lastTD=AG.aPlayers.tileLastDiscarded;             //~9624R~
    	if (Dump.Y) Dump.println("UADelayed.delayedLastDrawn lastDiscarded="+TileData.toString(AG.aPlayers.tileLastDiscarded)+",ctrTakenAll="+AG.aPlayers.ctrTakenAll+",currentPlayer="+AG.aPlayers.getCurrentPlayer());//~9624R~
//      if  ((lastTD.flag & TDF_INTERCEPTED)!=0)                   //~9624R~
      	if (Status.isIssuedRon())                                  //~9624I~
        {
        	if (Dump.Y) Dump.println("UADelayed.delayedLastDrawn tile intercepted");//~9624I~
            return;
        }
		int currentEswn=Accounts.getCurrentEswn();                 //~9B24I~
		postDelayedActionMsg(delayLast,GCM_ENDGAME_DRAWN,currentEswn,ENDGAME_DRAWN_LAST,0);//~9624I~
    }
//    //***********************************************************************//~v@@@R~
//    private void delayedRobotAutoTake()                          //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UADelayed.delayedRobotAutoTake player="+player+",msg="+msgData);//~v@@@R~
//        AG.aUserAction.actionReceived(actionID,msgData);         //~v@@@R~
//    }                                                            //~v@@@R~
//    //***********************************************************************//~v@@@R~
//    private void delayedRobotDiscard()                           //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UADelayed.delayedRobotDiscard player="+player+",msg="+msgData);//~v@@@R~
//        AG.aUserAction.actionReceived(actionID,msgData);         //~v@@@R~
//    }                                                            //~v@@@R~
    //***********************************************************************//~9626I~
    //*for GCM_WAITON/GCM_WAITOFF                                  //~9B20R~
    //*on each device; pre-chk iswaitable,return false if err detected//~9B20I~
    //***********************************************************************//~9626I~
    public boolean selectInfo(int PactionID,boolean PswServer,int Pplayer)//~9626I~
    {                                                              //~9626I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.delayedLastDrawn return by swStop");//~9B20I~
        	return false;	//skip sendToServer                    //~9B20I~
        }                                                          //~9B20I~
    	boolean rc=true;                                           //~9626I~
        if (PactionID==GCM_WAITON)                                 //~9626I~
        {                                                          //~9626I~
//  		rc=isWaitableAuto(Pplayer);                            //~9626I~//~9628R~
			rc=chkWaitable(true/*PswMsg*/,Pplayer,delayedAction);   //~9628I~
        }                                                          //~9626I~
//        else                                                     //~9701R~
//        if (PactionID==GCM_TIMEOUT_STOPAUTO)                     //~9701R~
//        {                                                        //~9701R~
//            stopAuto(PactionID);                                 //~9701R~
//        }                                                        //~9701R~
		if (Dump.Y) Dump.println("UADelayed.selectInfo rc="+rc+",player="+Pplayer+",actionID="+PactionID+",player="+Pplayer);//~9626I~
        return rc;                                                 //~9626I~
    }                                                              //~9626I~
    //***********************************************************************//~9B16I~
    //*on each device; pre-chk iswaitable,return false if err detected//~9B16I~
    //*for GCM_WAITON2/GCM_WAITOFF2 (by 2 touchmode dialog button) //~9B16I~
    //***********************************************************************//~9B16I~
    public boolean selectInfo2(int PactionID,boolean PswServer,int Pplayer)//~9B16I~
    {                                                              //~9B16I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.selectInfo2 return by swStop");//~9B20I~
        	return false;	//skip sendToServer                    //~9B20I~
        }                                                          //~9B20I~
    	boolean rc=true;                                           //~9B16I~
        if (PactionID==GCM_WAITON)                                 //~9B16I~
        {                                                          //~9B16I~
			rc=chkWaitable(true/*PswMsg*/,Pplayer,delayedAction);  //~9B16I~
        }                                                          //~9B16I~
		if (Dump.Y) Dump.println("UADelayed.selectInfo2 rc="+rc+",player="+Pplayer+",actionID="+PactionID+",player="+Pplayer);//~9B16I~
        return rc;                                                 //~9B16I~
    }                                                              //~9B16I~
    //***********************************************************************//~9627I~
    //*on client,at timeout on server                              //~9627I~
    //***********************************************************************//~9627I~
    public void  actionReleaseWait(int Pplayer,int[] PintParm)     //~9627R~
    {                                                              //~9627I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.actionReleaseWait return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
        if (Dump.Y) Dump.println("UADelayed.actionReleaseWait player="+Pplayer+",parm="+Arrays.toString(PintParm));//~9627R~
        int eswn=PintParm[PARMPOS_WAIT_RELEASE_PLAYER];            //~9627R~
	    int player=AG.aAccounts.eswnToPlayer(eswn);                //~9627I~
        int msgid=PintParm[PARMPOS_WAIT_RELEASE_MSGID];            //~9627I~
		AG.aGC.sendMsg(msgid,player);	//simulate discard button  //~9627R~
    }                                                              //~9627I~
    //***********************************************************************//~9701I~
    protected void startTimer()                                      //~9701I~//~9B17R~
    {                                                              //~9701I~
        if (Dump.Y) Dump.println("UADelayed.startTimer");          //~9B13I~
        if (!UADLT.isAlive())                                      //~9701I~
        {                                                          //~9701I~
    	    if (Dump.Y) Dump.println("UADelayed.startTimer start");//~9B22I~
        	swStop=false;                                          //~9701I~
        	UADLT.start();                                         //~9701I~
        }                                                          //~9701I~
    }                                                              //~9701I~
    //***********************************************************************//~v@@@I~
    //*GCM_WAITON/GCM_WAITOFF is not used                          //~0228I~
    //***********************************************************************//~0228I~
    public boolean actionWait(int Pmsgid,boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@@R~
    {                                                              //~v@@@I~
        boolean  rc=false;	//send msgDataToClent                  //~v@@@R~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.actionWait return by swStop");//~9B20I~//~0223R~
        	return false; //no send to client when server          //~9B20I~
        }                                                          //~9B20I~
    	if (Dump.Y) Dump.println("UADelayed.actionWait msgid="+Pmsgid+",swServer="+PswServer+",swReceived="+PswReceived+",player="+Pplayer+",delayedAction="+delayedAction);//~9626I~//~9627R~//~9B19R~
//      if (!Status.isGaming())                                    //~v@@@I~//~9730R~
        if (!Status.isGamingNow())	//before gameover              //~9730I~
        	return false;                                                //~v@@@I~
//        if (!UADLT.isAlive())                                      //~v@@@R~//~9701R~
//        {                                                          //~v@@@I~//~9701R~
//            swStop=false;                                          //~v@@@I~//~9701R~
//            UADLT.start();                                         //~v@@@I~//~9701R~
//        }                                                          //~v@@@I~//~9701R~
    	startTimer();                                              //~9701I~
//      if (Pmsgid==GCM_WAITON)                                    //~9626R~//~9627R~
//      	if (!isWaitableAuto(Pplayer))                          //~9626R~//~9627R~
//      		return false;                                      //~9626R~//~9627R~
        int eswn=Accounts.playerToEswn(Pplayer);                     //~v@@@I~
    	if (Dump.Y) Dump.println("UADelayed.actionWait eswn="+eswn);//~v@@@I~//~9626R~
        String msg;                                                //~v@@@I~
//        String[] nameEswn=AG.resource.getStringArray(R.array.nameESWN);//~v@@@R~
		boolean swReturn=false;                                    //~9628I~
        if (PswServer)                                             //~v@@@I~
        {                                                          //~v@@@I~
            int errid=0;                                       //~v@@@I~
        	synchronized(swWaiting)                                //~v@@@R~
            {                                                      //~v@@@I~
            	if (Pmsgid==GCM_WAITON)                            //~v@@@I~
                {                                                  //~v@@@I~
//              	if (!isWaitable(Pplayer))                      //~v@@@I~//~9625R~
//                      errid=R.string.Info_YouCouldNotWait;       //~v@@@I~//~9625I~
                	int er=isWaitable(Pplayer);                    //~9625I~
                	if (er!=0)                                     //~9625I~
                        errid=er;                                  //~9625I~
                    else                                           //~v@@@I~
                	if (!swWaiting[eswn])                          //~v@@@I~
                    {                                              //~v@@@I~
	                	swWaiting[eswn]=true;                      //~v@@@I~
			        	ctrWaitingPlayer++;                        //~v@@@R~
						showWaited(true/*swDelayed*/,Pplayer,delayedAction,false/*PswOff*/);//~9626R~//~9627R~
                    }                                              //~v@@@I~
                    else                                           //~v@@@I~
                        errid=R.string.Info_WaitRequestedDup;      //~v@@@I~
                }                                                  //~v@@@I~
                else                                               //~v@@@I~
                {                                                  //~v@@@I~
                	if (swWaiting[eswn])                           //~v@@@I~
                    {                                              //~v@@@I~
                    	if (ctrWaitingPlayer==1 && rescheduleAutoActionByWaitOff(msgWaiting))	//reset at GCM_TAKE,GCM_DISCARD//~9628R~//~9629R~
                        {                                          //~9628I~
                    		swReturn=true;                          //~9628I~
                        }                                          //~9628I~
                        else                                       //~9628I~
                        {                                          //~9628I~
	                	swWaiting[eswn]=false;                     //~v@@@I~
			        	ctrWaitingPlayer--;                        //~v@@@I~
                        }                                          //~9628I~
                    }                                              //~v@@@I~
                    else                                           //~v@@@I~
                        errid=R.string.Info_WaitCanceledDup;       //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
            if (errid!=0)                                          //~v@@@I~
            {                                                      //~v@@@I~
            	UserAction.sendErr(0/*opt*/,Pplayer,errid);        //~v@@@R~
            }                                                      //~v@@@I~
            else                                                   //~9628I~
            if (swReturn)                                          //~9628I~
            {                                                      //~9628I~
                UA.setNoMsgToClient();                      //~9628I~
    			if (Dump.Y) Dump.println("UADelayed.actionWait return by rescheduled");//~9628I~
            }                                                      //~9628I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
//                if (Pmsgid==GCM_WAITON)                            //~v@@@R~//~9626R~
////                  msg=AG.resource.getString(R.string.Info_WaitRequested,nameEswn[eswn]);//~v@@@R~//~9626R~
//                    msg=Utils.getStr(R.string.Info_WaitRequested); //~v@@@I~//~9626R~
//                else                                               //~v@@@R~//~9626R~
////                  msg=AG.resource.getString(R.string.Info_WaitCanceled,nameEswn[eswn]);//~v@@@R~//~9626R~
//                    msg=Utils.getStr(R.string.Info_WaitCanceled);  //~v@@@I~//~9626R~
//                UserAction.showInfoAllEswn(0/*opt*/,Pplayer,msg);  //~v@@@R~//~9626R~
//              UA.msgDataToClient=makeMsgDataToClientWait(Pplayer);//~v@@@R~//~9627R~
                UA.msgDataToClient=makeMsgDataToClientWait(Pplayer,delayedAction);//~9627I~
            	setWaiterLightServer(Pplayer,Pmsgid==GCM_WAITON);  //~v@@@I~
		        rc=true;	//send msgDataToClent                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        else //client                                              //~v@@@I~//~9B12R~
        {                                                          //~v@@@I~
        	if (PswReceived)                                       //~v@@@I~
            {                                                      //~v@@@I~
	    		delayedAction=PintParm[PARMPOS_WAITED_ACTION];     //~9627M~
              	if (!(Pmsgid==GCM_WAITOFF && rescheduleMsgClient(delayedAction,Pplayer)))	//take/discard issued//~9627I~
                {                                                  //~9627I~
            		setWaitingClient(PintParm);                        //~v@@@I~//~9627R~
            		setWaiterLightClient(Pplayer,eswn);                //~v@@@R~//~9627R~
//  				showWaited(Pplayer,delayedAction,Pmsgid==GCM_WAITOFF/*PswOff*/);//~9626R~//~9627R~
                }                                                  //~9627I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~9627I~
	        	AG.aAccounts.sendToServer(Pmsgid,"");              //~v@@@R~
            }                                                      //~9627I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("UADelayed.actionWait rc="+rc);   //~v@@@I~
        return rc;                                                 //~v@@@I~
    }//actionWait                                                              //~v@@@I~//~9B20R~
    //***********************************************************************//~9A26I~
    //*on server                                                   //~9A26I~
    //***********************************************************************//~9A26I~
    public void actionWaitOffAtRestartGame()                       //~9A26R~
    {                                                              //~9A26I~
    	TileData td;                                               //~9A26I~
    //**************************                                   //~9A26I~
    	if (swStop)                                                //~9623I~//~9B20I~
        {                                                          //~9623I~//~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.actionWaitOffAtRestartGame return by swStop");//~9623I~//~9B20I~
        	return;                                                //~9623I~//~9625R~//~9B20I~
        }                                                          //~9623I~//~9B20I~
    	if (Dump.Y) Dump.println("UADelayed.actionWaitOffAtRestartGame msgWaiting="+Utils.toString(msgWaiting));//~9A26I~
//      sendPendingMsgToClient();                                     //~9A26I~//~9A27R~//~0221R~
        msgWaiting=null;                                           //~9A26I~//~0225R~//~0226R~
        swAtRestart=true; //parm to sendToClientStopAuto,no seqNo set//~0221I~
	    resetWaitAll(false/*swRon*/);                              //~9A26R~
        swAtRestart=false;                                         //~0221I~
        sendPendingMsgToClient();  //after resetWait               //~0221I~
//        int player=AG.aPlayers.getCurrentPlayer();                 //~9A26R~//~9A28R~
//        int actionID=AG.aPlayers.getCurrentAction();               //~9A26R~//~9A28R~
//        if (Dump.Y) Dump.println("UADelayed.actionWaitOffAtRestartGame currentPlayer="+player+",lastAction="+actionID+",isRobotPlayer="+AG.aAccounts.isRobotPlayer(player));//~9A26I~//~9A28R~
//        switch(actionID)                                           //~9A26I~//~9A28R~
//        {                                                          //~9A26I~//~9A28R~
//        case GCM_DISCARD:                                          //~9A26I~//~9A28R~
//            td=AG.aPlayers.tileLastDiscarded;                      //~9A26I~//~9A28R~
//            UA.UAD.postNextPlayerPonKan(player,td); //swith to next player after delay a moment//~9A26R~//~9A28R~
//            break;                                                 //~9A26I~//~9A28R~
//        case GCM_TAKE:                                             //~9A26I~//~9A28R~
//        case GCM_PON:                                              //~9A26I~//~9A28R~
//        case GCM_CHII:                                             //~9A26I~//~9A28R~
//            UA.UAT.setAutoDiscardTimeout(true/*PswServer*/,player,actionID);//~9A26R~//~9A28R~
//            break;                                                 //~9A26I~//~9A28R~
//        case GCM_KAN:                                              //~9A26I~//~9A28R~
//            UA.UAK.setTimeout(true,player);                        //~9A26R~//~9A28R~
//            break;                                                 //~9A26I~//~9A28R~
//        default: //RON/REACH/REACH_OPEN                            //~9A26R~//~9A28R~
//            if (Dump.Y) Dump.println("UADelayed.actionWaitOffAtRestartGame no Action");//~9A26I~//~9A28R~
//        }                                                          //~9A26I~//~9A28R~
    }                                                              //~9A26I~
//    //***********************************************************************//~9A26I~//~9A27M~//~9A28R~
//    //*on Client,from UATake.autoDiscardTimeout at received WAIT_OFF//~9A26I~//~9A27M~//~9A28R~
//    //***********************************************************************//~9A26I~//~9A27M~//~9A28R~
//    public void sendRestartGame(boolean PswStart,int PidxMember,int Peswn)                                  //~9A26I~//~9A27I~//~9A28R~
//    {                                                              //~9A26I~//~9A27M~//~9A28R~
//        if (Dump.Y) Dump.println("UADelayed.sendRestartGame swStart="+PswStart+",eswn="+Peswn);//~9A27R~//~9A28R~
//        String msg=Peswn+MSG_SEPAPP2+(PswStart ? "0" : "1");                          //~9A26I~//~9A27I~//~9A28R~
//        BTIOThread.sendMsg(PidxMember,true/*swApp*/,GCM_RESTARTGAME,msg);//~9A27R~//~9A28R~
//    }                                                              //~9A26I~//~9A27M~//~9A28R~
    //***********************************************************************//~9A26I~
    //*on Server                                                   //~9A27I~
    //***********************************************************************//~9A27I~
    public void sendPendingMsgToClient()                              //~9A26I~//~9A27R~
    {                                                              //~9A26I~
    //**************************                                   //~9A26I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.sendPendingMsgToClient return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
    	if (Dump.Y) Dump.println("UADelayed.sendPendingMsgToClient waiting="+Arrays.toString(swWaitingAutoByDrawnHW)); //~9A26I~//~9A27R~
//        for (int eswn=0;eswn<PLAYERS;eswn++)                           //~9A27I~//~9A28R~
//        {                                                          //~9A27I~//~9A28R~
//            if (swWaitingAutoByDrawnHW[eswn])                      //~9A27I~//~9A28R~
//            {                                                      //~9A27I~//~9A28R~
//                int idxMember=AG.aAccounts.currentEswnToMember(eswn);//~9A27I~//~9A28R~
//                sendRestartGame(true/*start*/,idxMember,eswn);     //~9A27R~//~9A28R~
//                for (;;)                                           //~9A27I~//~9A28R~
//                {                                                  //~9A27I~//~9A28R~
//                    String msg=AG.aBTMulti.BTGroup.getPendingMsg(idxMember);//~9A27I~//~9A28R~
//                    if (Dump.Y) Dump.println("UADelayed.sendPendingMsgToClient eswn="+eswn+",idxMember="+idxMember+",msg="+msg);//~9A27I~//~9A28R~
//                    if (msg==null)                                 //~9A27I~//~9A28R~
//                        break;                                     //~9A27I~//~9A28R~
//                    BTIOThread.sendRestartMsg(idxMember,msg);      //~9A27I~//~9A28R~
//                }                                                  //~9A27I~//~9A28R~
//                sendRestartGame(false/*end*/,idxMember,eswn);                //~9A27R~//~9A28R~
//            }                                                      //~9A27I~//~9A28R~
//        }                                                          //~9A27I~//~9A28R~
//        AG.aBTMulti.BTGroup.clearPendingMsg();                     //~9A27I~//~9A28R~
//  	AG.aUARestart.sendPendingMsgToClient(swWaitingAutoByDrawnHW);//~9A28I~//~0220R~
    	AG.aUARestart.sendPendingMsgToClientSeqNo(swWaitingAutoByDrawnHW);//~0220R~
    }                                                              //~9A26I~
    //***********************************************************************//~9B14I~
    //*on Server                                                   //~9B14I~
    // from sendStopAutoStatus                                     //~0228I~
    //***********************************************************************//~9B14I~
    private void sendToClientStopAuto(int Peswn,boolean PswOn)      //~9B14R~//~9B19R~
    {                                                              //~9B14I~
    //**************************                                   //~9B14I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.sendToClientStopAuto return by swStop");//~9B20R~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
    	if (Dump.Y) Dump.println("UADelayed.sendToClientStopAuto swOn="+PswOn);//~9B14I~//~0222R~
      if (swAtRestart)  //parm from actionWaitOffAtRestartGame()   //~0221M~
      {                                                            //~0221I~
		String msg=(PswOn ? "1":"0") + MSG_SEPAPP2 + STOPAUTO_DRAWNHW_NOTIFY;  //from EndGame by Menu:drawn selection//~0221I~
        AG.aUARestart.sendToClientAll(GCM_TIMEOUT_STOPAUTO,msg);   //~0221M~
      }                                                            //~0221I~
      else                                                         //~0221M~
      {                                                            //~0221I~
		String msg=Peswn+MSG_SEPAPP2+(PswOn ? "1":"0") + MSG_SEPAPP2 + STOPAUTO_DRAWNHW_NOTIFY;  //from EndGame by Menu:drawn selection//~9B14R~
		if (Dump.Y) Dump.println("UADelayed.sendToClientStopAuto msg="+msg);//~9B14I~
        UA.sendToClient(true/*swSendAll*/,false/*swRobot*/,GCM_TIMEOUT_STOPAUTO,0/*Pplayer*/,msg);//~9B14I~
      }                                                            //~0221I~
    }                                                              //~9B14I~
//    //***********************************************************************//~9A27I~//~9A28R~
//    //*on Client                                                   //~9A27I~//~9A28R~
//    //***********************************************************************//~9A27I~//~9A28R~
//    public void sendPendingMsgToServer()                           //~9A27R~//~9A28R~
//    {                                                              //~9A27I~//~9A28R~
//    //**************************                                   //~9A27I~//~9A28R~
//        int idxServer=AG.aBTMulti.BTGroup.idxServer;               //~9A27I~//~9A28R~
//        String msg=AG.aBTMulti.BTGroup.getPendingMsg(idxServer);   //~9A27I~//~9A28R~
//        if (Dump.Y) Dump.println("UADelayed.sendPendingMsgToServer idxServer="+idxServer+",msg="+msg);//~9A27I~//~9A28R~
//        BTIOThread.sendRestartMsg(idxServer,msg);                  //~9A27I~//~9A28R~
//    }                                                              //~9A27I~//~9A28R~
    //***********************************************************************//~v@@@I~
    //*on Server/client reset by action pon,chii,kan,take,reach    //~v@@@R~
    //***********************************************************************//~v@@@I~
    private void resetActionWait(int Pplayer)                      //~v@@@I~
    {                                                              //~v@@@I~
        int eswn=Accounts.playerToEswn(Pplayer);                   //~v@@@I~
    	if (Dump.Y) Dump.println("UADelayed.resetActionWait player="+Pplayer+",eswn="+eswn+",swWaiting="+Arrays.toString(swWaiting));//~v@@@I~//~9A26R~
//      String[] nameEswn=AG.resource.getStringArray(R.array.nameESWN);//~v@@@R~
        boolean swReset=false;                                     //~v@@@I~
        synchronized(swWaiting)                                    //~v@@@I~
        {                                                          //~v@@@I~
        	if (swWaiting[eswn])                                   //~v@@@I~
            {                                                      //~v@@@I~
	        	swWaiting[eswn]=false;                             //~v@@@I~
			    ctrWaitingPlayer--;                                //~v@@@I~
	            swReset=true;                                      //~v@@@I~
            	setWaiterLight(Pplayer,false);                     //~v@@@R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("UADelayed.resetActionWait ctrWaitingPlayer="+ctrWaitingPlayer);//~9624I~
//        if (swReset)                                             //~v@@@R~
//        {                                                        //~v@@@R~
//            String msg=AG.resource.getString(R.string.Info_WaitCanceledByTake,AG.aUserAction.nameESWN[eswn]);//~v@@@R~
//            UserAction.showInfoAll(0/*opt*/,msg);                //~v@@@R~
//        }                                                        //~v@@@R~
    }                                                              //~v@@@I~
    //***********************************************************************//~9A12I~
    private void resetActionWaitResetComplete(int Pplayer)         //~9A12I~
    {                                                              //~9A12I~
    	if (Dump.Y) Dump.println("UADelayed.resetActionWaitResetComplete player="+Pplayer);	//leave waiting cleared//~9A12I~
    }                                                              //~9A12I~
    //***********************************************************************//~v@@@I~//~9704M~
    //*on Server/Client;   at kan,chii,take,pon,discard            //~9B13R~
    //***********************************************************************//~v@@@I~//~9704M~
	public void resetWait(int Pplayer)                             //~v@@@I~//~9704M~
    {                                                              //~v@@@I~//~9704M~
        if (Dump.Y) Dump.println("UADelayed.resetWait player="+Pplayer);//~v@@@I~//~9704M~
//      resetActionWait(Pplayer);                                  //~v@@@I~//~9630R~//~9704M~
//      resetWaitAll(false/*PswRon*/);   //reset also other by pon,kan,chii,take//~9630R~//~9704M~//~9B13R~
        resetWaitAll(false/*PswRon*/,false/*swResetStopAuto*/);   //reset also other by pon,kan,chii,take//~9B13I~
    }                                                              //~v@@@I~//~9704M~
    //***********************************************************************//~v@@@I~
    //*from Ron                                                    //~9626I~
    //***********************************************************************//~9626I~
//    public void resetWaitAll()                                     //~v@@@R~//~9B28R~
//    {                                                              //~v@@@I~//~9B28R~
//        resetWaitAll(true/*swRon*/);                          //~9630I~//~9B13R~//~9B28R~
//    }                                                              //~9630I~//~9B28R~
    public void resetWaitAllByRon(int Pplayer)                     //~9B28I~
    {                                                              //~9B28I~
    	if (Dump.Y) Dump.println("UADelayed.resetWaitAllByRon");   //~9B28I~
        if (!UA.UADL.resetWaitByRon2Touch(Pplayer))	//not multiRon         //~9B28I~
	        resetWaitAll(true/*swRon*/);                           //~9B28I~
    }                                                              //~9B28I~
    //************************************************************ //~9A13I~
    //*server and client                                           //~9B19I~
    //************************************************************ //~9B19I~
    public void resetWaitAll(boolean PswRon)                       //~9630I~
    {                                                              //~9630I~
	    resetWaitAll(PswRon,true/*swResetAutoStop*/);               //~9B13I~
    }                                                              //~9B13I~
    //************************************************************ //~0226I~
    public void resetWaitAllNewGame()                              //~0226I~
    {                                                              //~0226I~
    	if (Dump.Y) Dump.println("UADelayed.resetWaitAllNewGame"); //~0226I~
//      swResetMsgWaiting=true;	//parm to resetWaitAll             //~0226I~//~0228R~
//      resetWaitAll(false);                                       //~0226I~//~0228R~
//      swResetMsgWaiting=false;	//parm to resetWaitAll         //~0226I~//~0228R~
    	resetWaitAllClearPendingMsg();                             //~0228I~
    }                                                              //~0226I~
    //************************************************************ //~0228I~
    public void resetWaitAllClearPendingMsg()                      //~0228I~
    {                                                              //~0228I~
    	if (Dump.Y) Dump.println("UADelayed.resetWaitAllClearPendingMsg");//~0228I~
        swResetMsgWaiting=true;	//parm to resetWaitAll             //~0228I~
	    resetWaitAll(false);                                       //~0228I~
        swResetMsgWaiting=false;	//parm to resetWaitAll         //~0228I~
    }                                                              //~0228I~
    //************************************************************ //~9B13I~
    //*on server and client                                        //~9B19I~
    //************************************************************ //~9B19I~
    private void resetWaitAll(boolean PswRon,boolean PswResetAutoStop)//~9B13I~//~0222R~
    {                                                              //~9B13I~
    	if (Dump.Y) Dump.println("UADelayed.resetWaitAll PswRon="+PswRon+",PswResetAutoStop="+PswResetAutoStop+",msgWaiting="+Utils.toString(msgWaiting));//~9B13R~//~0226R~
//      if (PswRon)                                                //~9630I~//~9704R~
	    //  swResetAll=true;                                           //~9626I~//~9630R~//~9704R~
        if (swResetMsgWaiting)                                     //~0226I~
        	msgWaiting=null;                                       //~0226I~
	    swResetAll=PswRon;                                         //~9704I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
		    resetActionWait(ii);                                   //~v@@@I~
        }                                                          //~v@@@I~
      if (PswResetAutoStop)                                        //~9B19R~
	    resetStopAuto();      //sendToClient  if Server                                   //~9703I~//~9B19R~//~0226R~
    }                                                              //~v@@@I~
    //************************************************************ //~9A12I~//~9A13M~
    //*restart Timer                                               //~9A12I~//~9A13M~
    //************************************************************ //~9A12I~//~9A13M~
    public void resetComplete()                                    //~9A12I~//~9A13M~
    {                                                              //~9A12I~//~9A13M~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.resetComplete return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
    	if (Dump.Y) Dump.println("UADelayed.resetComplete");       //~9A12I~//~9A13M~
	    swResetAll=false;                                          //~9A12I~//~9A13M~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9A12I~//~9A13M~
        {                                                          //~9A12I~//~9A13M~
		    resetActionWaitResetComplete(ii);                      //~9A12I~//~9A13M~
        }                                                          //~9A12I~//~9A13M~
//      resetStopAutoResetComplete(); TODO drawnHW only            //~9A12I~//~9A13M~
    }                                                              //~9A12I~//~9A13M~
    //************************************************************ //~9703I~
    //*on server/client                                            //~9B19I~
    //************************************************************ //~9B19I~
    private void resetStopAuto()                                    //~9703I~//~9B13R~
    {                                                              //~9703I~
    	if (Dump.Y) Dump.println("UADelayed.resetStopAuto old stopAutoFix="+stopAutoFix+",stopAutoCtr="+stopAutoCtr);//~9703I~
        Arrays.fill(swWaitingAutoByDrawnHW,false);                 //~9703I~
//	    stopAutoCtr=0;                                             //~9703I~//~9B14R~
//	    stopAutoCtr=0;                                             //~0228I~
//	    stopAutoFix=false;                                         //~9703I~//~9B14R~
	    sendStopAutoStatus(0/*eswn:no meaning*/,false);            //~9B14I~
    }                                                              //~9703I~
    //************************************************************ //~9B14I~
    private void sendStopAutoStatus(int Peswn,boolean PswOn)       //~9B14I~
    {                                                              //~9B14I~
    	if (Dump.Y) Dump.println("UADelayed.sendStopAutoStatus eswn="+Peswn+",sw="+PswOn);//~9B14I~
        if (!PswOn)                                                //~9B14I~
        {                                                          //~9B14I~
  	    	stopAutoCtr=0;                                         //~9B14I~
  	    	stopAutoFix=false;                                     //~9B14I~
            resetAuto2Touch();                                     //~9B18I~
        }                                                          //~9B14I~
        if (Status.isGaming())                                     //~9B14I~
//        if (swServer);                                           //~9B19I~//~0226R~
          if (swServer)                                            //~0226I~
		    sendToClientStopAuto(Peswn,PswOn);	//send DRAWNHW_NOTIFY                     //~9B14R~//~0228R~
    }                                                              //~9B14I~
    //************************************************************ //~9B18I~
    //*UADelayed2 will Override                                    //~9B21I~
    //************************************************************ //~9B21I~
    protected void resetAuto2Touch()                               //~9B18R~
    {                                                              //~9B18I~
    	if (Dump.Y) Dump.println("UADelayed.resetAuto2Touch before stopAuto2Touch="+swStopAuto2Touch);//~9B18I~//~9B28R~
//  	stopAuto2Touch=false;                                      //~9B18I~//~9B28R~
    	swStopAuto2Touch=false;                                    //~9B28I~
    }                                                              //~9B18I~
//    //************************************************************ //~9A12I~//~9B18R~
//    public void resetStopAutoResetComplete()                       //~9A12I~//~9B18R~
//    {                                                              //~9A12I~//~9B18R~
//        if (Dump.Y) Dump.println("UADelayed.resetStopAutoResetComplete old stopAutoFix="+stopAutoFix+",stopAutoCtr="+stopAutoCtr);//~9A12I~//~9B18R~
//        Arrays.fill(swWaitingAutoByDrawnHW,false);                 //~9A12I~//~9B18R~
////      stopAutoCtr=0;                                             //~9A12I~//~9B14R~//~9B18R~
////      stopAutoFix=false;                                         //~9A12I~//~9B14R~//~9B18R~
//        sendStopAutoStatus(0/*eswn:no meaning*/,false);            //~9B14I~//~9B18R~
//    }                                                              //~9A12I~//~9B18R~
    //************************************************************ //~v@@@I~
    //*On server; rc:true=waitable                                            //~9625I~//~9626R~
    //*true if                                                     //~9B16I~
    //*		when taking, your are taking & autoDiscard:on          //~9B16I~
    //*		when discarded your are not discarder                  //~9B16I~
    //************************************************************ //~9625I~
//  private boolean isWaitable(int Pplayer)                        //~v@@@I~//~9625R~
    private int isWaitable(int Pplayer)                            //~9625I~
    {                                                              //~v@@@I~
//  	boolean rc;                                                //~v@@@I~//~9625R~
    	int rc=0;                                                  //~9625I~
        if (true)                                                  //~9626I~
        	return rc;	//TODO                                     //~9626I~
//      rc=Pplayer!=PLS.getCurrentPlayer();                        //~v@@@I~
//      rc=PLS.swLastActionIsDiscard && Pplayer!=PLS.playerLastDiscarded;//~v@@@R~//~9625R~
        int playerTaking=PLS.getCurrentPlayerTaking();             //~9625I~
        if (playerTaking!=-1)	//anyone taking status             //~9625I~
        {                                                          //~9625I~
        	if (timeoutAutoDiscard==0)                             //~9625I~
				rc=R.string.Info_YouCouldNotWaitByNoTimeoutSettingAutoDiscard;//~9625I~//~9626R~
            else                                                   //~9625I~
            if (playerTaking!=Pplayer)                             //~9625R~
				rc=R.string.Info_YouCouldNotWaitByNotTakingYou;     //~9625I~
        }                                                          //~9625I~
        else                                                       //~9625I~
//      if (PLS.swLastActionIsDiscard && Pplayer!=PLS.playerLastDiscarded)//~9625R~
        if (PLS.swLastActionIsDiscard)                             //~9625I~
        {                                                          //~9625I~
			if (Pplayer==PLS.playerLastDiscarded)                  //~9625I~
				rc=R.string.Info_YouCouldNotWaitYourDiscard;       //~9625R~
        }                                                          //~9625I~
        else                                                       //~9625I~
			rc=R.string.Info_YouCouldNotWait;                      //~9625I~
	    if (Dump.Y) Dump.println("UADelayed.isWaitable Pplayer="+Pplayer+",rc="+rc+",timeoutAutoDiscard="+timeoutAutoDiscard+",playerTaking="+playerTaking+",lastdiscarder="+PLS.playerLastDiscarded+",swLastActionISDiscard="+PLS.swLastActionIsDiscard);//~v@@@R~//~9625R~
        return rc;
    }                                                              //~v@@@I~
    //************************************************************ //~9628I~
    //*On server/client; true=waitable                             //~9628I~
    //************************************************************ //~9628I~
    protected boolean chkWaitable(boolean PswMsg,int Pplayer,int PdelayedAction)//~9628R~//~9B17R~
    {                                                              //~9628I~
    	boolean rc;                                                //~9628I~
        int err=0;                                                 //~9628I~
	    if (Dump.Y) Dump.println("UADelayed.chkWaitable msgsw="+PswMsg+",Pplayer="+Pplayer+",action="+PdelayedAction);//~9628I~
      	if (Status.isIssuedRon())                                  //~9628I~
        {                                                          //~9628I~
        	err=R.string.AE_AfterIssuedRonAlready;                 //~9628R~
        }                                                          //~9628I~
        else                                                       //~9628I~
        if (PLS.swLastActionIsDiscard)                             //~9628I~
        {                                                          //~9628I~
            switch(PdelayedAction)                                 //~9628I~
            {                                                      //~9628I~
            case GCM_TIMEOUT_TO_TAKE:   //at takable, after PonKan time upped//~9628I~
            case GCM_TIMEOUT_AUTOTAKE:                             //~9628I~
            case GCM_TIMEOUT_AUTOTAKE0:                            //~9630I~
    			err=chkWaitableAutoTake(Pplayer);                  //~9628I~
                break;                                             //~9628I~
            case GCM_TIMEOUT_TO_LASTDRAWN:                         //~9628I~
                break;                                             //~9628I~
            default:	//time of Ron,Up to PonKan                 //~9628I~
            }                                                      //~9628I~
        }                                                          //~9628I~
        else                                                       //~9628I~
        {                                                          //~9628I~
            switch(PdelayedAction)                                 //~9628I~
            {                                                      //~9628I~
            case GCM_TIMEOUT_AUTODISCARD:                          //~9628I~
    			err=chkWaitableAutoDiscard(Pplayer);               //~9628I~
                break;                                             //~9628I~
            }                                                      //~9628I~
        }                                                          //~9628I~
        if (PswMsg)                                                //~9628I~
	        if (err!=0)                                            //~9628I~
			    GMsg.drawMsgbar(err);                              //~9628I~
        rc=err==0;                                                 //~9628I~
	    if (Dump.Y) Dump.println("UADelayed.chkWaitable rc="+rc+",err="+err);//~9628I~
        return rc;                                                 //~9628I~
    }                                                              //~9628I~
    //************************************************************ //~9628I~
    private int chkWaitableAutoTake(int Pplayer)                   //~9628R~
    {                                                              //~9628I~
    	int err=0;                                                 //~9628I~
//        if (Players.nextPlayer(PLS.playerLastDiscarded)!=PLAYER_YOU)//~9628R~
//            err=R.string.Info_YouCouldNotWaitOtherAutoTake;      //~9628R~
	    if (Dump.Y) Dump.println("UADelayed.chkWaitableAutoTake rc="+err+",Pplayer="+Pplayer+",playerLastDiscarded="+PLS.playerLastDiscarded);//~9628I~
        return err;                                                //~9628I~
    }                                                              //~9628I~
    //************************************************************ //~9628I~
    private int chkWaitableAutoTakeKan(int Pplayer)                //~9628R~
    {                                                              //~9628I~
    	int err=0;                                                 //~9628I~
        int playerTaking=PLS.getCurrentPlayerTaking();             //~9628I~
		if (playerTaking!=PLAYER_YOU)                              //~9628I~
			err=R.string.Info_YouCouldNotWaitOtherAutoTake;        //~9628I~
	    if (Dump.Y) Dump.println("UADelayed.chkWaitableAutoTakeKan rc="+err+",Pplayer="+Pplayer+",playerTaking="+playerTaking);//~9628I~
        return err;                                                //~9628I~
    }                                                              //~9628I~
    //************************************************************ //~9628I~
    private int chkWaitableAutoDiscard(int Pplayer)                //~9628R~
    {                                                              //~9628I~
    	int err=0;                                                 //~9628I~
        int playerTaking=PLS.getCurrentPlayerTaking();             //~9628I~
		if (playerTaking!=PLAYER_YOU)                              //~9628I~
			err=R.string.Info_YouCouldNotWaitOtherAutoDiscard;     //~9628I~
	    if (Dump.Y) Dump.println("UADelayed.chkWaitableAutoDiscard rc="+err+",Pplayer="+Pplayer+",playerTaking="+playerTaking);//~9628I~
        return err;                                                //~9628I~
    }                                                              //~9628I~
    //************************************************************ //~9626I~
    //*On server/client; chk waitable of autodiscard/autotake      //~9626I~
    //************************************************************ //~9626I~
    private boolean isWaitableAuto(int Pplayer)                    //~9626I~//~9B17R~
    {                                                              //~9626I~
//        int err=0;                                                 //~9626I~//~9627R~
//        int playerTaking=PLS.getCurrentPlayerTaking();             //~9626I~//~9627R~
//        if (playerTaking!=-1)   //anyone taking status             //~9626I~//~9627R~
//        {                                                          //~9626I~//~9627R~
//            if (PLS.lastActionID==GCM_KAN && timeoutAutoTake==0)   //~9626I~//~9627R~
//                err=R.string.Info_YouCouldNotWaitByNoTimeoutSettingAutoTake;//~9626I~//~9627R~
//            else                                                   //~9626I~//~9627R~
//            if (PLS.lastActionID==GCM_TAKE && timeoutAutoDiscard==0)//~9626R~//~9627R~
//                err=R.string.Info_YouCouldNotWaitByNoTimeoutSettingAutoDiscard;//~9626I~//~9627R~
//            else                                                   //~9626I~//~9627R~
//            if (playerTaking!=Pplayer)                             //~9626I~//~9627R~
//                err=R.string.Info_YouCouldNotWaitByNotTakingYou;   //~9626I~//~9627R~
//        }                                                          //~9626I~//~9627R~
//        else                                                       //~9626I~//~9627R~
//        if (PLS.swLastActionIsDiscard)                             //~9626I~//~9627R~
//        {                                                          //~9626I~//~9627R~
//            if (Pplayer==PLS.playerLastDiscarded)                  //~9626I~//~9627R~
//                err=R.string.Info_YouCouldNotWaitYourDiscard;      //~9626I~//~9627R~
//            else                                                   //~9626I~//~9627R~
////          if (!PLS.getLastDiscarded().isLocked()) //after NEXT_PLAYER can take                                     //~v@@6I~//~9626R~//~9627R~
//            if (delayedAction==GCM_TIMEOUT_AUTOTAKE)    //nextplayer can take//~9626I~//~9627R~
//            {                                                      //~9626I~//~9627R~
//                if (timeoutAutoTake==0)                            //~9626I~//~9627R~
//                    err=R.string.Info_YouCouldNotWaitByNoTimeoutSettingAutoTake;//~9626I~//~9627R~
//                else                                               //~9626I~//~9627R~
//                if (Pplayer!=Players.nextPlayer(PLS.playerLastDiscarded))//~9626I~//~9627R~
//                    err=R.string.Info_YouCouldNotWaitOtherAutoTake;//~9626I~//~9627R~
//            }                                                      //~9626I~//~9627R~
//        }                                                          //~9626I~//~9627R~
//        if (err!=0)                                                //~9626I~//~9627R~
//            GMsg.drawMsgbar(err);                                //~9626I~//~9627R~
//        if (Dump.Y) Dump.println("UADelayed.isWaitableAuto Pplayer="+Pplayer+",rc="+(err==0)+",timeoutAutoTake="+timeoutAutoTake+",playerTaking="+playerTaking+",lastdiscarder="+PLS.playerLastDiscarded+",swLastActionISDiscard="+PLS.swLastActionIsDiscard);//~9626I~//~9627R~
//        if (Dump.Y) Dump.println("UADelayed.isWaitableAuto Pplayer="+Pplayer+",rc="+(err==0)+",timeoutAutoDiscard="+timeoutAutoDiscard+",playerTaking="+playerTaking+",lastdiscarder="+PLS.playerLastDiscarded+",swLastActionISDiscard="+PLS.swLastActionIsDiscard);//~9626I~//~9627R~
//        if (Dump.Y) Dump.println("UADelayed.isWaitableAuto lastACtion="+PLS.lastActionID);//~9626I~//~9627R~
//        return err==0;                                             //~9626I~//~9627R~
		return isWaitableAuto(true/*PswMsg*/,Pplayer,delayedAction);//~9627R~
    }                                                              //~9626I~
    //************************************************************ //~9627I~
    //*On server/client; chk waitable of autodiscard/autotake      //~9627I~
    //************************************************************ //~9627I~
    protected boolean isWaitableAuto(boolean PswMsg,int Pplayer,int Paction)//~9627R~//~9B17R~
    {                                                              //~9627I~
    	int err=0;                                                 //~9627I~
        int playerTaking=PLS.getCurrentPlayerTaking();             //~9627I~
        if (playerTaking!=-1)	//anyone taking status             //~9627I~
        {                                                          //~9627I~
        	if (PLS.lastActionID==GCM_KAN && timeoutAutoTake==0)   //~9627I~
				err=R.string.Info_YouCouldNotWaitByNoTimeoutSettingAutoTake;//~9627I~
            else                                                   //~9627I~
        	if (PLS.lastActionID==GCM_TAKE && timeoutAutoDiscard==0)//~9627I~
				err=R.string.Info_YouCouldNotWaitByNoTimeoutSettingAutoDiscard;//~9627I~
            else                                                   //~9627I~
            if (playerTaking!=Pplayer)                             //~9627I~
				err=R.string.Info_YouCouldNotWaitByNotTakingYou;   //~9627I~
        }                                                          //~9627I~
        else                                                       //~9627I~
        if (PLS.swLastActionIsDiscard)                             //~9627I~
        {                                                          //~9627I~
			if (Pplayer==PLS.playerLastDiscarded)                  //~9627I~
				err=R.string.Info_YouCouldNotWaitYourDiscard;      //~9627I~
            else                                                   //~9627I~
//          if (!PLS.getLastDiscarded().isLocked()) //after NEXT_PLAYER can take//~9627I~
//          if (delayedAction==GCM_TIMEOUT_AUTOTAKE)	//nextplayer can take//~9627I~
            if (Paction==GCM_TIMEOUT_AUTOTAKE	//nextplayer can take//~9627I~//~9630R~
            ||  Paction==GCM_TIMEOUT_AUTOTAKE0)	//nextplayer can take//~9630I~
            {                                                      //~9627I~
        		if (timeoutAutoTake==0)                            //~9627I~
					err=R.string.Info_YouCouldNotWaitByNoTimeoutSettingAutoTake;//~9627I~
                else                                               //~9627I~
//              if (Pplayer!=Players.nextPlayer(PLS.playerLastDiscarded))//~9627R~
//              if (Players.nextPlayer(PLS.playerLastDiscarded)!=PLAYER_YOU)//~9627R~
                if (Pplayer!=PLAYER_YOU)    //NEXT_PLAYER changed player to next player//~9627I~
					err=R.string.Info_YouCouldNotWaitOtherAutoTake;//~9627I~
            }                                                      //~9627I~
        }                                                          //~9627I~
        if (PswMsg)                                                //~9627I~
	        if (err!=0)                                            //~9627R~
			    GMsg.drawMsgbar(err);                              //~9627R~
	    if (Dump.Y) Dump.println("UADelayed.isWaitableAuto Pplayer="+Pplayer+",rc="+(err==0)+",timeoutAutoTake="+timeoutAutoTake+",playerTaking="+playerTaking+",lastdiscarder="+PLS.playerLastDiscarded+",swLastActionISDiscard="+PLS.swLastActionIsDiscard);//~9627I~
	    if (Dump.Y) Dump.println("UADelayed.isWaitableAuto rc="+(err==0)+",err="+Integer.toHexString(err)+",timeoutAutoDiscard="+timeoutAutoDiscard+",lastdiscarder="+PLS.playerLastDiscarded+",swLastActionIsDiscard="+PLS.swLastActionIsDiscard);//~9627I~//~0406R~
	    if (Dump.Y) Dump.println("UADelayed.isWaitableAuto Paction="+Paction+",lastACtion="+PLS.lastActionID);//~9627R~//~0406R~
        return err==0;                                             //~9627I~
    }                                                              //~9627I~
    //************************************************************ //~0406I~
    //*On server/client; chk waitable of autotake after Kan        //~0406I~
    //************************************************************ //~0406I~
    protected boolean isWaitableAutoTakeKan(int Pplayer,int PctrTaken,int PctrDiscarded)//~0406R~
    {                                                              //~0406I~
    	boolean rc=true;                                               //~0406I~
	    if (Dump.Y) Dump.println("UADelayed.isWaitableAutoTakeKan Pplayer="+Pplayer+",ctrTaken="+PctrTaken+",ctrDiscarded="+PctrDiscarded);//~0406I~
		int cp=AG.aPlayers.getCurrentPlayer();                     //~0406I~
		if (timeoutAutoTake==0)                                    //~0406I~
            rc=false;                                              //~0406I~
        else                                                       //~0406I~
        if (cp!=Pplayer)                                           //~0406I~
        	rc=false;                                              //~0406I~
        else                                                       //~0406I~
        if (PctrTaken!=PLS.ctrTakenAll || PctrDiscarded!=PLS.ctrDiscardedAll)//~0406R~
        {                                                          //~0406I~
            rc=false;                                              //~0406R~
        }                                                          //~0406I~
	    if (Dump.Y) Dump.println("UADelayed.isWaitableAutoTakeKan rc="+rc+",currentPlayer="+cp+",timeoutAutoTake="+timeoutAutoTake+",ctrTakenAll="+PLS.ctrTakenAll+",ctrDiscardedAll="+PLS.ctrDiscardedAll);//~0406R~
        return rc;                                             //~0406I~
    }                                                              //~0406I~
    //************************************************************ //~9628I~
    //*On server; chk anyone before taking                         //~9628I~
    //************************************************************ //~9628I~
//  protected boolean isWaitableAutoTake()                           //~9628I~//~9B17R~//~9B30R~
    private   boolean isWaitableAutoTake(int Ptimeout)             //~9B30I~
    {                                                              //~9628I~
	    if (Dump.Y) Dump.println("UADelayed.isWaitableAutoTake timeout="+Ptimeout);//~9B30I~
    	boolean rc=true;                                           //~9628I~
        int playerTaking=-1;                                       //~9628I~
//      if (timeoutAutoTake==0)                                    //~9628I~//~9B30R~
        if (Ptimeout==0)                                           //~9B30I~
        	rc=false;                                              //~9628I~
        else                                                       //~9628I~
        {                                                          //~9628I~
        	playerTaking=PLS.getCurrentPlayerTaking();	//need to discard, contains pon kan chii//~9628R~
        	if (playerTaking!=-1)	//anyone taking status         //~9628I~
        	{                                                      //~9628I~
        		if (PLS.lastActionID!=GCM_KAN)                     //~9628I~
	        		rc=false;                                      //~9628I~
        	}                                                      //~9628I~
        	else                                                   //~9628I~
        	if (PLS.lastActionID!=GCM_DISCARD)                      //~9628I~
	        	rc=false;                                          //~9628I~
        }                                                          //~9628I~
	    if (Dump.Y) Dump.println("UADelayed.isWaitableAutoTake rc="+rc+",playerTaking="+playerTaking+",lastdiscarder="+PLS.playerLastDiscarded+",lastActionID="+PLS.lastActionID);//~9628I~//~9B20R~//~9B30R~
        return rc;                                                 //~9628I~
    }                                                              //~9628I~
    //************************************************************ //~v@@@I~
    //*rc:0 no waiter,1:only you are waiting,2:other waiting;      //~v@@@I~
    //************************************************************ //~v@@@I~
    public int isWaiting(int Pplayer)                              //~v@@@R~
    {                                                              //~v@@@I~
    	int eswn=0;                                                  //~v@@@I~
        int rc=0;                                                  //~v@@@I~
    	if (ctrWaitingPlayer>1)                                    //~v@@@R~
        {                                                          //~9629I~
        	rc=2;                                                  //~v@@@I~
			if (swWaiting[eswn])                                   //~9629I~
            	rc=3;	//you and other                            //~9629I~
        }                                                          //~9629I~
        else                                                       //~v@@@I~
	    if (ctrWaitingPlayer==1)                                   //~v@@@I~
        {                                                          //~v@@@I~
            eswn=AG.aAccounts.playerToEswn(Pplayer);                    //~v@@@I~
			if (swWaiting[eswn])                                   //~v@@@I~
                rc=1;                                              //~v@@@I~
            else                                                   //~v@@@I~
                rc=2;                                              //~v@@@I~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("UADelayed.isWaiting player="+Pplayer+",eswn="+eswn+",rc="+rc+",swWaiting="+Arrays.toString(swWaiting));//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //************************************************************ //~9629I~
    //*from UserAction.getActionInfo                               //~9629I~
    //************************************************************ //~9629I~
    public String isWaiting(int PactionID,int Pplayer)             //~9629I~
    {                                                              //~9629I~
	    if (Dump.Y) Dump.println("UADelayed.isWaiting actionid="+PactionID+",player="+Pplayer+",delayedAction="+delayedAction);//~9629R~
		int rc=isWaiting(Pplayer);                                 //~9629I~
        String rcstr=null;                                         //~9629I~
        if (rc!=0)                                                 //~9630R~
        {                                                          //~9629I~
        	boolean swBlock=false;                                 //~9630I~
        	String time=null;                                      //~9630I~
            switch(PactionID)                                      //~9630I~
            {                                                      //~9630I~
            case GCM_PON:                                          //~9630I~
//          case GCM_PON_C:	//not called from UserAction,called as selectInfo                                      //~9B16R~//~9B17R~
            case GCM_KAN:                                          //~9630I~
                swBlock=delayedAction==GCM_TIMEOUT_TO_PONKAN;      //~9630R~
                time=Utils.getStr(R.string.TimeToRon);             //~9630I~
                break;                                             //~9630I~
            case GCM_TAKE:                                         //~9630I~
            case GCM_CHII:                                         //~9630I~
                swBlock=(delayedAction==GCM_TIMEOUT_TO_PONKAN || delayedAction==GCM_TIMEOUT_TO_TAKE);//~9630R~
                if (swBlock)                                       //~9630I~
	                time=Utils.getStr(R.string.TimeToRonPonKan);   //~9630R~
//                else                                             //~9630R~
//                {                                                //~9630R~
//                    swBlock=(delayedAction==GCM_TIMEOUT_AUTOTAKE || delayedAction==GCM_TIMEOUT_AUTOTAKE0);//~9630R~
//                    if (swBlock)                                 //~9630R~
//                        time=Utils.getStr(R.string.TimeAfterTakable);//~9630R~
//                }                                                //~9630R~
                break;                                             //~9630I~
            }                                                      //~9630I~
            if (swBlock)                                           //~9630I~
            {                                                      //~9630I~
            	if (rc==1)                                         //~9630I~
	            	rcstr=Utils.getStr(R.string.Info_PleaseWaitWithReasonYou,time);//~9630I~
                else                                               //~9630I~
		        if (rc==2)                                         //~9630I~
		        	rcstr=Utils.getStr(R.string.Info_PleaseWaitWithReasonOther);//~9630I~
                else                                               //~9630I~
                    rcstr=Utils.getStr(R.string.Info_PleaseWaitWithReasonYouAndOther,time);//~9629I~//~9630M~
            }                                                      //~9630I~
        }                                                          //~9630I~
	    if (Dump.Y) Dump.println("UADelayed.isWaiting rcstr="+Utils.toString(rcstr));//~9629I~//~9630R~
        return rcstr;                                              //~9629I~
    }                                                              //~9629I~
//    //************************************************************ //~v@@@I~//~9625R~
//    //*for robot TAKE delay until wait released                    //~v@@@I~//~9625R~
//    //************************************************************ //~v@@@I~//~9625R~
//    private boolean isWaiterBlocking(Message Pmsg)                 //~v@@@I~//~9625R~
//    {                                                              //~v@@@I~//~9625R~
//        boolean rc=false;                                          //~v@@@I~//~9625R~
//        if (ctrWaitingPlayer!=0)                                   //~v@@@I~//~9625R~
//        {                                                          //~v@@@I~//~9625R~
////          msgWaiting=Pmsg;                                       //~v@@@I~//~9624R~//~9625R~
//            msgWaiting=renewalMsg(Pmsg);    //Pmsg will be recycled after some time elapsed//~9624R~//~9625R~
//            showWaited(false/*PswInvalidate*/,false/*PswSend*/,msgWaiting);               //~9624I~//~9625R~
//            rc=true;                                               //~v@@@I~//~9625R~
//        }                                                          //~v@@@I~//~9625R~
//        if (Dump.Y) Dump.println("UADelayed.isWaiterBlocking rc="+rc+",ctrWaitingPlayer="+ctrWaitingPlayer+",msg="+Pmsg.toString());//~v@@@I~//~9624R~//~9625R~
//        return rc;                                                 //~v@@@I~//~9625R~
//    }                                                              //~v@@@I~//~9625R~
//    //************************************************************ //~9624I~//~0304R~
//    private void showWaited(boolean PswDelayed,int Pplayer,boolean PswInvalidate,boolean PswOff,Message Pmsg)         //~9624I~//~9625R~//~9626R~//~9627R~//~0304R~
//    {                                                              //~9624I~//~0304R~
//        if (Dump.Y) Dump.println("UADelayed.showWaited swResetAll="+swResetAll+",what="+Pmsg.what+",data="+Pmsg.getData().toString());//~9624R~//~9626R~//~0304R~
//        if (swResetAll)                                            //~9626I~//~0304R~
//            return;                                                //~9626I~//~0304R~
//        String func;                                               //~9624I~//~0304R~
//        int what=Pmsg.what;                                        //~9625I~//~0304R~
//////        if (what==GCM_RECEIVED_APPMSG)  //robot                  //~9625R~//~9626R~//~0304R~
//////        {                                                          //~9624I~//~9625R~//~9626R~//~0304R~
//////            Bundle data=Pmsg.getData();                            //~9624I~//~9625R~//~9626R~//~0304R~
//////            String strActionID=data.getString(GVPARM1,"");                //~v@@@I~//~9624I~//~9625R~//~9626R~//~0304R~
//////            int action=Utils.parseInt(strActionID,-1);             //~9624I~//~9625R~//~9626R~//~0304R~
//////            switch(action)                                         //~9624I~//~9625R~//~9626R~//~0304R~
//////            {                                                      //~9624I~//~9625R~//~9626R~//~0304R~
//////            case GCM_TAKE:                                         //~9624I~//~9625R~//~9626R~//~0304R~
//////                func=Utils.getStr(R.string.Label_Take);            //~9624I~//~9625R~//~9626R~//~0304R~
//////                break;                                             //~9624I~//~9625R~//~9626R~//~0304R~
//////            default:                                               //~9624I~//~9625R~//~9626R~//~0304R~
//////                func=action+":"+Utils.getStr(R.string.Unknown);    //~9624I~//~9625R~//~9626R~//~0304R~
//////            }                                                      //~9624I~//~9625R~//~9626R~//~0304R~
//////            if (PswSend)                                         //~9625R~//~9626R~//~0304R~
//////                GMsg.drawMsgBarAll(0,Utils.getStr(R.string.Info_RobotWaitedRescheduled,func));//~9624I~//~9625R~//~9626R~//~0304R~
//////            else                                                 //~9625R~//~9626R~//~0304R~
//////                GMsg.drawMsgBarAll(0,Utils.getStr(R.string.Info_RobotWaited,func));//~9625R~//~9626R~//~0304R~
//////        }                                                          //~9624I~//~9625R~//~9626R~//~0304R~
//////        else                                                       //~9624I~//~9625R~//~9626R~//~0304R~
//////        {                                                          //~9624I~//~9625R~//~9626R~//~0304R~
////            switch (what)                                          //~9624I~//~9626R~//~0304R~
////            {                                                      //~9624I~//~9626R~//~0304R~
////            case GCM_TIMEOUT_TO_PONKAN:                            //~9624I~//~9626R~//~0304R~
////                func=Utils.getStr(R.string.WaitPonKan);            //~9624I~//~9626R~//~0304R~
////                break;                                             //~9624I~//~9626R~//~0304R~
////            case GCM_TIMEOUT_TO_TAKE:                              //~9624I~//~9626R~//~0304R~
////                func=Utils.getStr(R.string.WaitTake);              //~9624I~//~9626R~//~0304R~
////                break;                                             //~9624I~//~9626R~//~0304R~
////            case GCM_TIMEOUT_TO_LASTDRAWN:                         //~9624I~//~9626R~//~0304R~
////                func=Utils.getStr(R.string.LastDrawn);              //~9624I~//~9626R~//~0304R~
////                break;                                             //~9624I~//~9626R~//~0304R~
////            case GCM_TIMEOUT_AUTODISCARD:                          //~9624I~//~9626R~//~0304R~
////                func=Utils.getStr(R.string.AutoDiscard);           //~9624I~//~9626R~//~0304R~
////                break;                                             //~9624I~//~9626R~//~0304R~
////            case GCM_TIMEOUT_AUTOTAKE:                             //~9624I~//~9626R~//~0304R~
////                func=Utils.getStr(R.string.AutoTake);              //~9624I~//~9626R~//~0304R~
////                break;                                             //~9624I~//~9626R~//~0304R~
////            case GCM_TIMEOUT_AUTOTAKE_KAN:                         //~9624I~//~9626R~//~0304R~
////                func=Utils.getStr(R.string.AutoTakeKan);           //~9624I~//~9626R~//~0304R~
////                break;                                             //~9624I~//~9626R~//~0304R~
////            default:                                               //~9624I~//~9626R~//~0304R~
////                func=what+":"+Utils.getStr(R.string.Unknown);      //~9624I~//~9626R~//~0304R~
////            }                                                      //~9624I~//~9626R~//~0304R~
////            if (PswOff)                                           //~9625I~//~9626R~//~9627R~//~0304R~
////                GMsg.drawMsgBarAll(0,Utils.getStr(R.string.Info_PlayerWaitedRescheduled,func));//~9625I~//~9626R~//~0304R~
////            else                                                   //~9625I~//~9626R~//~0304R~
////                GMsg.drawMsgBarAll(0,Utils.getStr(R.string.Info_PlayerWaited,func));//~9624I~//~9625R~//~9626R~//~0304R~
//////        }                                                          //~9624I~//~9625R~//~9626R~//~0304R~
//        showWaited(PswDelayed,Pplayer,what,PswOff);                    //~9626R~//~9627R~//~0304R~
//        if (PswInvalidate)                                         //~9625I~//~0304R~
//            AG.aGameView.paint();                                  //~9625I~//~0304R~
//    }                                                              //~9624I~//~0304R~
    //************************************************************ //~9626I~
    private void showWaited(boolean PswDelayedMsg,int Pplayer,int Paction,boolean PswOff)//~9626R~//~9627R~
    {                                                              //~9626I~
	    if (Dump.Y) Dump.println("UADelayed.showWaited swDelayed="+PswDelayedMsg+",player="+Pplayer+",action="+Paction+",swOff="+PswOff);//~9626I~//~9628R~//~9629R~
        if (swResetAll)                                            //~9626I~
        	return;                                                //~9626I~
        if (Paction==0)	//on client,active only AUTOTAKE,AUTODISCARD//~9626I~
        	return;                                                //~9626I~
//        String func;                                               //~9626I~//~9629R~
//        switch (Paction)                                           //~9626I~//~9629R~
//        {                                                          //~9626I~//~9629R~
//        case GCM_TIMEOUT_TO_PONKAN:                                //~9626I~//~9629R~
//            func=Utils.getStr(R.string.WaitPonKan);                //~9626I~//~9629R~
//            break;                                                 //~9626I~//~9629R~
//        case GCM_TIMEOUT_TO_TAKE:                                  //~9626I~//~9629R~
//            func=Utils.getStr(R.string.WaitTake);                  //~9626I~//~9629R~
//            break;                                                 //~9626I~//~9629R~
//        case GCM_TIMEOUT_TO_LASTDRAWN:                             //~9626I~//~9629R~
//            func=Utils.getStr(R.string.LastDrawn);                 //~9626I~//~9629R~
//            break;                                                 //~9626I~//~9629R~
//        case GCM_TIMEOUT_AUTODISCARD:                              //~9626I~//~9629R~
//            func=Utils.getStr(R.string.AutoDiscard);               //~9626I~//~9629R~
//            break;                                                 //~9626I~//~9629R~
//        case GCM_TIMEOUT_AUTOTAKE:                                 //~9626I~//~9629R~
//            func=Utils.getStr(R.string.AutoTake);                  //~9626I~//~9629R~
//            break;                                                 //~9626I~//~9629R~
//        case GCM_TIMEOUT_AUTOTAKE_KAN:                             //~9626I~//~9629R~
//            func=Utils.getStr(R.string.AutoTakeKan);               //~9626I~//~9629R~
//            break;                                                 //~9626I~//~9629R~
//        default:                                                   //~9626I~//~9629R~
//            func=Paction+":"+Utils.getStr(R.string.Unknown);          //~9626I~//~9629R~
//        }                                                          //~9626I~//~9629R~
////        if (PswOff)                                       //~9626R~//~9627R~//~9629R~
////            GMsg.drawMsgBarAll(0,Utils.getStr(R.string.Info_PlayerWaitedRescheduled,func));//~9626R~//~9629R~
////        else                                                     //~9626R~//~9629R~
////            GMsg.drawMsgBarAll(0,Utils.getStr(R.string.Info_PlayerWaited,func));//~9626R~//~9629R~
//        String txt;                                                //~9626I~//~9629R~
//        if (PswOff)                                         //~9626I~//~9627R~//~9629R~
//            txt=Utils.getStr(R.string.Info_PlayerWaitedRescheduled,func);//~9626I~//~9629R~
//        else                                                       //~9626I~//~9629R~
//            txt=Utils.getStr(R.string.Info_PlayerWaited,func);     //~9626I~//~9629R~
		String txt=showWaited(Pplayer,Paction,PswOff);             //~9629M~
        if (txt==null)                                             //~9629M~
        	return;                                                //~9629M~
        if (PswDelayedMsg)                                         //~9627I~
        	UA.setDelayedGMsg(0,true/*PswAllEswn*/,Pplayer,txt); //GCM_WAITON is not used, so always PswDelayed=false//~0305R~
        else                                                       //~9627I~
			UserAction.showInfoAllEswn(0/*opt*/,Pplayer,txt);//TODO NOLANG support~9627R~//~0305R~
    }                                                              //~9626I~
    //************************************************************ //~9629I~
    private String showWaited(int Pplayer,int Paction/*delayedAction*/,boolean PswOff)//~9629I~//~9B16R~
    {                                                              //~9629I~
	    if (Dump.Y) Dump.println("UADelayed.showWaited player="+Pplayer+",action="+Paction+",PswOff="+PswOff);//~9629I~
        if (swResetAll)                                            //~9629R~
            return null;                                           //~9629R~
        if (Paction==0) //on client,active only AUTOTAKE,AUTODISCARD//~9629R~
            return null;                                           //~9629R~
//        String func;                                             //~9629R~
//        switch (Paction)                                         //~9629R~
//        {                                                        //~9629R~
//        case GCM_TIMEOUT_TO_PONKAN:                              //~9629R~
//            func=Utils.getStr(R.string.WaitPonKan);              //~9629R~
//            break;                                               //~9629R~
//        case GCM_TIMEOUT_TO_TAKE:                                //~9629R~
//            func=Utils.getStr(R.string.WaitTake);                //~9629R~
//            break;                                               //~9629R~
//        case GCM_TIMEOUT_TO_LASTDRAWN:                           //~9629R~
//            func=Utils.getStr(R.string.LastDrawn);               //~9629R~
//            break;                                               //~9629R~
//        case GCM_TIMEOUT_AUTODISCARD:                            //~9629R~
//            func=Utils.getStr(R.string.AutoDiscard);             //~9629R~
//            break;                                               //~9629R~
//        case GCM_TIMEOUT_AUTOTAKE:                               //~9629R~
//            func=Utils.getStr(R.string.AutoTake);                //~9629R~
//            break;                                               //~9629R~
//        case GCM_TIMEOUT_AUTOTAKE_KAN:                           //~9629R~
//            func=Utils.getStr(R.string.AutoTakeKan);             //~9629R~
//            break;                                               //~9629R~
//        default:                                                 //~9629R~
//            func=Paction+":"+Utils.getStr(R.string.Unknown);     //~9629R~
//        }                                                        //~9629R~
    	String func=showWaitedAction(Paction);                //~9629I~
        String txt;                                                //~9629I~
        if (PswOff)                                                //~9629I~
            txt=Utils.getStr(R.string.Info_PlayerWaitedRescheduled,func);//~9629I~
        else                                                       //~9629I~
            txt=Utils.getStr(R.string.Info_PlayerWaited,func);     //~9629I~
	    if (Dump.Y) Dump.println("UADelayed.showWaited rc="+txt);  //~9629I~
        return txt;                                                //~9629I~
    }                                                              //~9629I~
    //************************************************************ //~9629I~
    private String showWaitedAction(int Paction)                   //~9629I~
    {                                                              //~9629I~
	    if (Dump.Y) Dump.println("UADelayed.showWaited action="+Paction);//~9629I~
        String func;                                               //~9629I~
        switch (Paction)                                           //~9629I~
        {                                                          //~9629I~
        case GCM_TIMEOUT_TO_PONKAN:                                //~9629I~
            func=Utils.getStr(R.string.WaitPonKan);                //~9629I~
            break;                                                 //~9629I~
        case GCM_TIMEOUT_TO_TAKE:                                  //~9629I~
            func=Utils.getStr(R.string.WaitTake);                  //~9629I~
            break;                                                 //~9629I~
        case GCM_TIMEOUT_TO_LASTDRAWN:                             //~9629I~
            func=Utils.getStr(R.string.LastDrawn);                 //~9629I~
            break;                                                 //~9629I~
        case GCM_TIMEOUT_AUTODISCARD:                              //~9629I~
            func=Utils.getStr(R.string.AutoDiscard);               //~9629I~
            break;                                                 //~9629I~
        case GCM_TIMEOUT_AUTOTAKE:                                 //~9629I~
            func=Utils.getStr(R.string.AutoTake);                  //~9629I~
            break;                                                 //~9629I~
        case GCM_TIMEOUT_AUTOTAKE0:                                //~9630I~
            func=Utils.getStr(R.string.AutoTake0);                 //~9630I~
            break;                                                 //~9630I~
        case GCM_TIMEOUT_AUTOTAKE_KAN:                             //~9629I~
            func=Utils.getStr(R.string.AutoTakeKan);               //~9629I~
            break;                                                 //~9629I~
        case GCM_TIMEOUT_AUTOTAKE_KAN0:                            //~9630I~
            func=Utils.getStr(R.string.AutoTakeKan0);              //~9630I~
            break;                                                 //~9630I~
        default:                                                   //~9629I~
            func=Paction+":"+Utils.getStr(R.string.Unknown);       //~9629I~
        }                                                          //~9629I~
	    if (Dump.Y) Dump.println("UADelayed.showWaited rc="+func); //~9629I~
        return func;                                               //~9629I~
    }                                                              //~9629I~
    //************************************************************ //~v@@@I~
    private String makeMsgDataToClientWait(int Pplayer,int PdelayedAction)            //~v@@@R~//~9627R~
    {                                                              //~v@@@I~
		int eswn=Accounts.playerToEswn(Pplayer);                   //~v@@@I~
//  	String s=eswn+MSG_SEPAPP2+(swWaiting[0]?"1":"0")+MSG_SEPAPP+(swWaiting[1]?"1":"0")+MSG_SEPAPP+(swWaiting[2]?"1":"0")+MSG_SEPAPP+(swWaiting[3]?"1":"0")+MSG_SEPAPP;//~v@@@R~//~9627R~
    	String s=eswn+MSG_SEPAPP2+PdelayedAction+MSG_SEPAPP2+(swWaiting[0]?"1":"0")+MSG_SEPAPP+(swWaiting[1]?"1":"0")+MSG_SEPAPP+(swWaiting[2]?"1":"0")+MSG_SEPAPP+(swWaiting[3]?"1":"0")+MSG_SEPAPP;//~9627I~
	    if (Dump.Y) Dump.println("UADelayed.makeMsgdataToCleient s="+s);//~v@@@I~
        return s;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //************************************************************ //~v@@@I~
    //*on Server                                                   //~v@@@I~
    //************************************************************ //~v@@@I~
    private void setWaiterLightServer(int Pplayer,boolean PswOn)   //~v@@@I~//~9B17R~//~9B19R~
    {                                                              //~v@@@I~
	    if (Dump.Y) Dump.println("UADelayed.setWaiterLightServer player="+Pplayer+",sw="+PswOn);//~v@@@I~
	    setWaiterLight(Pplayer,PswOn);                             //~v@@@I~
    }                                                              //~v@@@I~
    //************************************************************ //~v@@@I~
    //*on client                                                   //~v@@@I~
    //************************************************************ //~v@@@I~
    private void setWaitingClient(int[] PintParm)                  //~v@@@R~
    {                                                              //~v@@@I~
    	int ctr=0;                                                 //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
	    	if (swWaiting[ii]=PintParm[PARMPOS_WAITER+ii]!=0)      //~v@@@R~
	            ctr++;                                             //~v@@@R~
        }                                                          //~v@@@I~
		ctrWaitingPlayer=ctr;                                      //~v@@@I~
	    if (Dump.Y) Dump.println("UADelayed.setWaitingClient ctr="+ctr+",sw="+Arrays.toString(swWaiting));//~v@@@R~
    }                                                              //~v@@@I~
    //************************************************************ //~v@@@I~
    //*on client                                                   //~v@@@I~
    //************************************************************ //~v@@@I~
    private void setWaiterLightClient(int Pplayer,int Peswn)       //~v@@@I~//~9B19R~
    {                                                              //~v@@@I~
    	boolean sw=swWaiting[Peswn];                               //~v@@@I~
	    if (Dump.Y) Dump.println("UADelayed.setWaiterLightClient Player="+Pplayer+",sw="+sw+",eswn="+Peswn);//~v@@@I~
	    setWaiterLight(Pplayer,sw);                                //~v@@@I~
    }                                                              //~v@@@I~
    //************************************************************ //~v@@@I~
    //*on client                                                   //~v@@@I~
    //************************************************************ //~v@@@I~
    protected void setWaiterLight(int Pplayer,boolean PswOn)             //~v@@@I~//~9B19R~
    {                                                              //~v@@@I~
	    if (Dump.Y) Dump.println("UADelayed.setWaiterLight sw="+PswOn+",player="+Pplayer);//~v@@@R~
//        if (swOldWaitingStatus!=PswOn)                           //~v@@@R~
//        {                                                        //~v@@@R~
//            swOldWaitingStatus=PswOn;                            //~v@@@R~
            AG.aDiceBox.setWaiterLight(Pplayer,PswOn);             //~v@@@R~
//      GameView.repaint();                                        //~9B26R~
//        }                                                        //~v@@@R~
    }                                                              //~v@@@I~
    //************************************************************ //~9701I~
    //*on server,from GVH                                          //~9701I~
    //************************************************************ //~9701I~
    public void stopAuto(Message Pmsg)                             //~9701I~
    {                                                              //~9701I~
	    if (Dump.Y) Dump.println("UADelayed.stopAuto msgid="+Pmsg.what);//~9701I~
        int[] intp=GameViewHandler.getMsgIntData(Pmsg);            //~9701I~
	    if (Dump.Y) Dump.println("UADelayed.stopAuto parm="+Arrays.toString(intp));//~9701I~
        int eswn=intp[0];                                          //~9702R~
        boolean swon=intp[PARMPOS_AUTOSTOP_ONOFF]!=0;                                   //~9702I~//~9703R~
        int fixStop=intp[PARMPOS_AUTOSTOP_FIX];                    //~9704R~
        stopAuto(eswn,swon,fixStop);                                            //~9701I~//~9702R~//~9703R~//~9704R~
    }                                                              //~9701I~
    //************************************************************ //~9701I~
    //*on server,from UserAction by msg requested by client                                  //~9701I~//~9B14R~
    //************************************************************ //~9701I~
    public void stopAuto(int[] PintParm)                           //~9701I~
    {                                                              //~9701I~
	    if (Dump.Y) Dump.println("UADelayed.stopAuto intParm="+Arrays.toString(PintParm));//~9701I~
        int eswn=PintParm[0];                                      //~9702I~
        boolean swon=PintParm[PARMPOS_AUTOSTOP_ONOFF]!=0; //1                              //~9701R~//~9703R~//~0228R~
        int fixStop=PintParm[PARMPOS_AUTOSTOP_FIX];       //2    //~9703I~//~9704R~//~0228R~
        stopAuto(eswn,swon,fixStop);                                            //~9701I~//~9702R~//~9703R~//~9704R~
    }                                                              //~9701I~
    //************************************************************ //~9701I~
    //*timeoutAutoTakeRobot is always !=0                          //~9703I~
    //*on client by PfixStop==STOPAUTO_DRAWNHW_NOTIFY, set ctr and return//~9B19I~
    //*on server                                                   //~9B19I~
    //************************************************************ //~9703I~
    private void stopAuto(int Peswn,boolean PswOn,int PfixStop)                            //~9701I~//~9702R~//~9703R~//~9704R~//~9B12R~
    {                                                              //~9701I~
        String infomsg;                                            //~9701I~
        int infomsgid;                                          //~0303I~
    	if (swStop)                                                //~9B20I~
        {                                                          //~9B20I~
	    	if (Dump.Y) Dump.println("UADelayed.stopAuto return by swStop");//~9B20I~
        	return;                                                //~9B20I~
        }                                                          //~9B20I~
	    if (Dump.Y) Dump.println("UADelayed.stopAuto PswOn="+PswOn+",eswn="+Peswn+",swWaitingAutoByDrawnHW="+Arrays.toString(swWaitingAutoByDrawnHW)+",stopAutoCtr="+stopAutoCtr+",PfixStop="+PfixStop);//~9703R~//~9704R~//~9A27R~
	    if (Dump.Y) Dump.println("UADelayed.stopAuto timeoutAutoTake="+timeoutAutoTake+",timeoutAutoDiscard="+timeoutAutoDiscard+",timeoutAutoTakeRobot="+timeoutAutoTakeRobot+",widthRobot="+AG.aAccounts.isGameWithRobot());//~9703I~
        if (timeoutAutoTake==0 && timeoutAutoDiscard==0 && !AG.aAccounts.isGameWithRobot())//~9703I~
        {                                                          //~9703I~
		    if (Dump.Y) Dump.println("UADelayed.stopAuto bypass by no timeout setting");//~9703I~
        }                                                          //~9703I~
    	if (PfixStop==STOPAUTO_DRAWNHW_NOTIFY)  //on client from Server  at ctr=1 or 0//~9B14I~//~9B19R~
        {                                                          //~9B14I~
        	stopAutoCtr=PswOn ? 1: 0;                              //~9B14I~
		    if (Dump.Y) Dump.println("UADelayed.stopAuto DRAWNHW_NOTIFY stopAutoCtr="+stopAutoCtr);//~9B14I~
        	return;                                                //~9B14I~
        }                                                          //~9B14I~
//      if (PfixStop!=0)                                           //~9A24R~
        if (PfixStop==STOPAUTO_FIX||PfixStop==STOPAUTO_RESETALL)	//1 or 2//~9A24I~
//* STOPAUTO_FIX:no user                                           //~0228I~
        {                                                          //~9703I~
        	if (PswOn)                                             //~9704I~
//* STOPAUTO_FIX:no user,so PswOn:no user                          //~0228I~
            {                                                      //~9704I~
                if (!stopAutoFix)                                      //~9703I~//~9704R~
                {                                                      //~9703I~//~9704R~
                    infomsg=Utils.getStr(R.string.Info_StopAutoFixedHW);//~9703I~//~9704R~
                    UA.showInfoAll(0,infomsg);                         //~9703I~//~9704R~
//* STOPAUTO_FIX:no user, stopAutoFix is always false              //~0228I~
                    stopAutoFix=true;                                  //~9703I~//~9704R~
                }                                                      //~9703I~//~9704R~
            }                                                      //~9704I~
            else                                                   //~9704I~
            {                                                      //~9704I~
                if (stopAutoFix)                                   //~9704I~
//* STOPAUTO_FIX:no user, stopAutoFix is always false              //~0228I~
                {                                                  //~9704I~
                    infomsg=Utils.getStr(R.string.Info_StopAutoFixedHWReleased);//~9704I~
                    UA.showInfoAll(0,infomsg);                     //~9704I~
                    stopAutoFix=false;                             //~9704I~
                }                                                  //~9704I~
                if (PfixStop==STOPAUTO_RESETALL)	//game continue from drawnHW   //~9704I~//~9A26R~
                {                                                  //~0228I~
//* STOPAUTO_FIX:no user, STOPAUTO_RESETALL is only from DrawnReqDlgHW.releaseStopAutoTakeDiscardAll//~0228I~
//  				resetWaitAll(false/*PswRon*/);                   //~9704I~//~0228R~
    				resetWaitAllClearPendingMsg();                 //~0228I~
                    DrawnReqDlgHW.setDelayedTimer(swServer);  //from continueThisRound of DrawnDlgHw//~0228R~
                }                                                  //~0228I~
            }                                                      //~9704I~
        }                                                          //~9703I~
        else                                                       //~9703I~
        if (stopAutoFix)                                           //~9703I~
//* STOPAUTO_FIX:no user, stopAutoFix is always false              //~0228I~
        {                                                          //~9703I~
		    if (Dump.Y) Dump.println("UADelayed.stopAuto already stopAutoFix:On");//~9703I~
        }                                                          //~9703I~
        else	//DrawnHW,IOErr                                                       //~9703I~//~9A24R~
        if (PswOn)                                                 //~9701I~
        {                                                          //~9701I~
        	startTimer();                                          //~9701I~
            if (!swWaitingAutoByDrawnHW[Peswn])                    //~9702I~
            {                                                      //~9702I~
	            swWaitingAutoByDrawnHW[Peswn]=true;                //~9702I~
        		stopAutoCtr++;                                         //~9701I~//~9702R~
                if (stopAutoCtr==1)                                //~9B14I~
				    sendStopAutoStatus(Peswn,true);                //~9B14I~
//  	        infomsg=Utils.getStr(R.string.Info_StopAutoByDrawnHW,stopAutoCtr);//~9701I~//~9702R~//~9705R~
    		  if (PfixStop!=STOPAUTO_IOERR)                        //~9A24I~//~9A29R~
              {                                                    //~9A24I~//~9A29R~
//  	        infomsg=Utils.getStr(R.string.Info_StopAutoByDrawnHW);//~9705I~//~0225R~
//  			UA.showInfoAllEswn(0,Accounts.eswnToPlayer(Peswn),infomsg);                                 //~9701I~//~9702R~//~0225R~
    			UA.showInfoAllEswnEswn(0,Peswn,R.string.Info_StopAutoByDrawnHW);//~0225I~
              }                                                    //~9A24I~//~9A29R~
            }                                                      //~9702I~
//            timeoutAutoDiscard=0;                                //~9702R~
//            timeoutAutoTake=0;                                   //~9702R~
//            timeoutAutoTakeRobot=0;                              //~9702R~
        }                                                          //~9701I~
        else  //Psw:off                                                     //~9701I~//~9A29R~
        {                                                          //~9701I~
			if (PfixStop==STOPAUTO_IOERR)	//on Server                        //~9A24I~//~9A26I~
            {                                                    //~9A24I~//~9A26I~
				actionWaitOffAtRestartGame();                      //~9A26I~
            }                                                      //~9A26I~
            else                                                   //~9A26I~
            if (swWaitingAutoByDrawnHW[Peswn])                     //~9702I~
            {                                                      //~9702I~
	            swWaitingAutoByDrawnHW[Peswn]=false;               //~9702I~
        		stopAutoCtr--;                                         //~9701I~//~9702R~
                if (stopAutoCtr==0)                                //~9B14I~
				    sendStopAutoStatus(Peswn,false);               //~9B14I~
                if (stopAutoCtr==0)                                    //~9701I~//~9702R~
                {                                                  //~9702R~
				  if (timeoutAutoTake==0 && timeoutAutoDiscard==0) //~9B12I~
//                  infomsg=Utils.getStr(R.string.Info_StopAutoByDrawnHWReleasedAll);//~9701I~//~9702R~//~0303R~
                    infomsgid=R.string.Info_StopAutoByDrawnHWReleasedAll;//~0303I~
                  else                                             //~9B12I~
//                  infomsg=Utils.getStr(R.string.Info_StopAutoByDrawnHWReleasedAllAutoTakeDiscard);//~9B12I~//~0303R~
                    infomsgid=R.string.Info_StopAutoByDrawnHWReleasedAllAutoTakeDiscard;//~0303I~
    //                timeoutAutoDiscard=RuleSettingOperation.getDelayDiscard();//~9702R~
    //                timeoutAutoTake=RuleSettingOperation.getTimeoutTake();//~9702R~
    //                timeoutAutoTakeRobot=RuleSettingOperation.getTimeoutTakeRobot();//~9702R~
                }                                                  //~9702R~
                else                                                   //~9701I~//~9702R~
//                  infomsg=Utils.getStr(R.string.Info_StopAutoByDrawnHWReleased,stopAutoCtr);//~9701I~//~9702R~//~9705R~
//                  infomsg=Utils.getStr(R.string.Info_StopAutoByDrawnHWReleased);//~9705I~//~0303R~
                    infomsgid=R.string.Info_StopAutoByDrawnHWReleased;//~0303I~
//  			UA.showInfoAllEswn(0,Accounts.eswnToPlayer(Peswn),infomsg);                                 //~9701I~//~9702R~//~0303R~
    			UA.showInfoAllEswn(0,Accounts.eswnToPlayer(Peswn),infomsgid);//~0303I~
            }                                                      //~9702I~
        }                                                          //~9701I~
	    if (Dump.Y) Dump.println("UADelayed.stopAuto exit swWaitingAuto="+Arrays.toString(swWaitingAutoByDrawnHW)+",stopAutoCtr="+stopAutoCtr+",stopAutoFix="+stopAutoFix);//~9701I~//~9702R~//~9703R~//~0228R~
    }                                                              //~9701I~
    //************************************************************ //~9B14I~
    public boolean isPendingHW()                                   //~9B14R~
    {                                                              //~9B14I~
        boolean rc=stopAutoFix || stopAutoCtr!=0;                 //~9B14I~
	    if (Dump.Y) Dump.println("UADelayed.isPendingHW rc="+rc+",stopAutoFix="+stopAutoFix+",stopAutoCtr="+stopAutoCtr);  //~9B14I~//~0228R~
        return rc;                                                 //~9B14I~
    }                                                              //~9B14I~
    //************************************************************ //~v@@@R~
    //*subthread Class. a Thread for server to process msg        //~1424I~//~v@@@I~
    //************************************************************ //~v@@@R~
    class UADelayTimer extends Thread                              //~v@@@I~
    {                                                              //~1420I~//~v@@@I~
        UADelayTimer()                                  //~1420R~  //~v@@@I~
        {                                                          //~1420I~//~v@@@I~
	        if (Dump.Y) Dump.println("UADelayTimer new()");        //~v@@@R~
        }                                                          //~1420I~//~v@@@I~
    	//**************************                               //~v@@@I~
        public void run ()                                         //~1420I~//~v@@@I~
        {                                                          //~1420I~//~v@@@I~
	        if (Dump.Y) Dump.println("UADelayTimer run started");     //~1506R~//~1A6AR~//~v@@@I~
        	while(true)                                            //~1420I~//~v@@@I~
            {                                                      //~1420I~//~v@@@I~
            	try                                                //~v@@@I~
                {                                                  //~v@@@I~
                  if (!swStopTemporally)                           //~0223I~
                  {                                                //~0223I~
            		if (swStop)                                    //~v@@@I~
                		break;                                     //~v@@@I~
					runTimer();                                    //~9B18I~
                  }                                                //~0223I~
                	Utils.sleep(SLEEP_TIME) ;                       //~v@@@I~
                }                                                  //~v@@@I~
                catch (Exception e)                                //~1420R~//~v@@@I~
                {                                                  //~1420R~//~v@@@I~
                    Dump.println(false/*show toast*/,e,"UADelayTimer.run");         //~1420R~//~v@@@I~
                }                                                  //~1420R~//~v@@@I~
            }                                                      //~1420I~//~v@@@I~
            if (Dump.Y) Dump.println("UADelayTimer.run exit");     //~v@@@I~
        }                                                          //~1420I~//~v@@@I~
    }//UADelayTimer thread                                                              //~1420I~//~v@@@I~
    //**************************************************************//~9B18I~
    protected void runTimer()                                           //~9B18I~
    {                                                              //~9B18I~
//                    boolean swPost;                              //~9B18I~
                    boolean swPostM;                               //~9B18I~
                    int ctrWaiting;                                //~9B18I~
                    synchronized(swWaiting)                        //~9B18I~
                    {                                              //~9B18I~
//                      swPost=(ctrWaitingPlayer==0 && actionWaiting!=0);//~9B18I~
//                      swPostM=(ctrWaitingPlayer==0 && msgWaiting!=null);//~9B18I~
                        ctrWaiting=ctrWaitingPlayer;               //~9B18I~
                    }                                              //~9B18I~
//                  if (ctrWaiting!=0)                             //~9B18I~
                    boolean swBlock=(ctrWaiting!=0);               //~9B18I~
//                  if (stopAutoCtr!=0 && msgWaiting!=null)        //~9B18I~
//                  if ((stopAutoFix || stopAutoCtr!=0) && msgWaiting!=null)//~9B18R~
//                  if ((stopAutoFix || stopAutoCtr!=0 || stopAuto2Touch) && msgWaiting!=null)//~9B18I~//~9B28R~
                    if ((stopAutoFix || stopAutoCtr!=0 || swStopAuto2Touch) && msgWaiting!=null)//~9B28I~
                    {                                              //~9B18I~
//                      int what=msgWaiting.what;                  //~9B18I~
//                      if (what==GCM_TIMEOUT_AUTODISCARD || what==GCM_TIMEOUT_AUTOTAKE)//~9B18I~
                            swBlock=true;                          //~9B18I~
                        if (Dump.Y) Dump.println("UADelayed.runTimer stopAutoFix="+stopAutoFix+",swStopAuto2Touch="+swStopAuto2Touch+",stopAutoCtr="+stopAutoCtr+",swBlock="+swBlock);//~9B18R~//~9B28R~
                    }                                              //~9B18I~
                    if (swBlock)                                   //~9B18I~
                    {                                              //~9B18I~
                        swPostM=false;                             //~9B18I~
                        if (Dump.Y) Dump.println("UADelay2d.runTimer ctrWaitingPlayer="+ctrWaiting+",msgWaiting="+(msgWaiting==null ? "null" : msgWaiting.toString()));//~9B18R~
                    }                                              //~9B18I~
                    else                                           //~9B18I~
                    {                                              //~9B18I~
                        swPostM=msgWaiting!=null;                  //~9B18I~
                        if (swPostM)                               //~9B18I~
                            if (AG.aUARestart.isIOExceptionBeforeRestart())//~9B18I~
                            {                                      //~9B18I~
                                if (Dump.Y) Dump.println("UADelayed.runTimer ioException skip Post");//~9B18R~
                                swPostM=false;                     //~9B18I~
                            }                                      //~9B18I~
                    }                                              //~9B18I~
                    if (swPostM)                                   //~9B18I~
                    {                                              //~9B18I~
                        Message msg=msgWaiting;                    //~9B18I~
                        msgWaiting=null;                           //~9B18I~
//                      GVH.sendMessage(msg);                      //~9B18I~
                        if (Dump.Y) Dump.println("UADelayed.runTimer rescheduled waiting msg msgid="+msg.what+",data="+msg.getData().toString());//~9B18R~//~9B23R~
//                      GameViewHandler.sendMsgDelayed(msg,0/*time*/);  //callback to timeout//~9B18I~
                        rescheduleMsgFromTimer(msg);               //~9B18I~
                    }                                              //~9B18I~
//                    if (swPost)                                  //~9B18I~
//                    {                                            //~9B18I~
//                        swActionReleased=true;                   //~9B18I~
//                        swActionReleasedPonKan=true;             //~9B18I~
//                        if (Dump.Y) Dump.println("UADelayTimer.reschedule actionid="+actionWaiting);//~9B18I~
//                        actionWaiting=0;                         //~9B18I~
////                      GVH.postDelayed(AG.aUADelayed,RESCHEDULE_DELAY);    //GVH:Handler extended//~9B18I~
//                        GameViewHandler.sendMsgDelayed(msgWaiting,RESCHEDULE_DELAY);    //callback to timeoutToPonKan//~9B18I~
//                    }                                            //~9B18I~
    }                                                              //~9B18I~
//    //*****************************************************************//~9B16I~//~9B17R~
//    //*confirm action by popupdialog                               //~9B16I~//~9B17R~
//    //*****************************************************************//~9B16I~//~9B17R~
//    public void popupConfirm(int PactionID)                        //~9B16R~//~9B17R~
//    {                                                              //~9B16I~//~9B17R~
//        if (Dump.Y) Dump.println("UADelayed.popupConfirm actionID="+PactionID);//~9B16R~//~9B17R~
//        sendMsgWait(true/*waitOn*/,PactionID);                     //~9B16I~//~9B17R~
//        showAlert(PactionID);                                     //~9B16R~//~9B17R~
//    }                                                              //~9B16I~//~9B17R~
//    //*****************************************************************//~9B16I~//~9B17R~
//    public void showAlert(int PactionID)                           //~9B16R~//~9B17R~
//    {                                                              //~9B16I~//~9B17R~
//        if (Dump.Y) Dump.println("UADelayed.showAlert actionID="+PactionID);//~9B16R~//~9B17R~
//        ActionAlert.show(this,PactionID);                          //~9B16R~//~9B17R~
//    }                                                              //~9B16I~//~9B17R~
//    //*******************************************************      //~9B16I~//~9B17R~
//    @Override   //AlertI                                           //~9B16I~//~9B17R~
//    public int alertButtonAction(int Pbuttonid,int PactionID)      //~9B16R~//~9B17R~
//    {                                                              //~9B16I~//~9B17R~
//        if (Dump.Y) Dump.println("UADelayed.alertButtonAction buttonID="+Pbuttonid+",actionID="+PactionID);//~9B16R~//~9B17R~
//        if (Pbuttonid==BUTTON_POSITIVE)                            //~9B16I~//~9B17R~
//        {                                                          //~9B16I~//~9B17R~
//            if (Dump.Y) Dump.println("UADelayed.alertButtonAction positive");//~9B16I~//~9B17R~
//            sendMsgAction(PactionID);                              //~9B16I~//~9B17R~
//        }                                                          //~9B16I~//~9B17R~
//        else                                                       //~9B16I~//~9B17R~
//        if (Pbuttonid==BUTTON_NEGATIVE)                            //~9B16I~//~9B17R~
//        {                                                          //~9B16I~//~9B17R~
//            if (Dump.Y) Dump.println("UADelayed.alertButtonAction negative");//~9B16I~//~9B17R~
//            sendMsgWait(false/*waitOff*/,PactionID);               //~9B16I~//~9B17R~
//        }                                                          //~9B16I~//~9B17R~
//        return 0;                                                //~9B17R~
//    }                                                              //~9B16I~//~9B17R~
//    //*************************************************************************//~v@@@I~//~9B16I~//~9B17R~
//    public void sendMsgWait(boolean PswWaitOn,int PactionID)                     //~v@@@I~//~9622R~//~9B16I~//~9B17R~
//    {                                                              //~v@@@I~//~9B16I~//~9B17R~
//        if(Dump.Y) Dump.println("UADelayed.sendMsgWait waitOn="+PswWaitOn+",actionID="+PactionID);//~9B16I~//~9B17R~
//        AG.aGC.sendMsg((PswWaitOn ? GCM_WAITON2 : GCM_WAITOFF2),PLAYER_YOU,PactionID);                 //~v@@@I~//~9B16R~//~9B17R~
//    }                                                              //~v@@@I~//~9B16I~//~9B17R~
//    //*************************************************************************//~9B16I~//~9B17R~
//    public void sendMsgAction(int PactionID)                       //~9B16I~//~9B17R~
//    {                                                              //~9B16I~//~9B17R~
//        if(Dump.Y) Dump.println("UADelayed.sendMsgAction actionID="+PactionID);//~9B16I~//~9B17R~
////        int actionID=0;                                          //~9B16R~//~9B17R~
////        switch(PactionID)                                        //~9B16R~//~9B17R~
////        {                                                        //~9B16R~//~9B17R~
////        case GCM_PON:                                            //~9B16R~//~9B17R~
////            actionID=GCM_PON_C; //confirmed,skip re-open dialog  //~9B16R~//~9B17R~
////            break;                                               //~9B16R~//~9B17R~
////        case GCM_KAN:                                            //~9B16R~//~9B17R~
////            actionID=GCM_KAN_C;                                  //~9B16R~//~9B17R~
////            break;                                               //~9B16R~//~9B17R~
////        case GCM_CHII:                                           //~9B16R~//~9B17R~
////            actionID=GCM_CHII_C;                                 //~9B16R~//~9B17R~
////            break;                                               //~9B16R~//~9B17R~
////        case GCM_RON:                                            //~9B16R~//~9B17R~
////            actionID=GCM_RON_C;                                  //~9B16R~//~9B17R~
////            break;                                               //~9B16R~//~9B17R~
////        }                                                        //~9B16R~//~9B17R~
////        if (actionID!=0)                                         //~9B16R~//~9B17R~
////            AG.aGC.sendMsg(actionID,null);                      //~v@@@R~//~9B16R~//~9B17R~
//            AG.aGC.sendMsg(PactionID,PLAYER_YOU,ACTION_CONFIRMED);  //~9B16I~//~9B17R~
//    }                                                              //~9B16I~//~9B17R~
    //*************************************************************************//~0223I~
    //*from UARestart,do not stop timer                            //~0223I~
    //*************************************************************************//~0223I~
    public void setStopTemporally(boolean PswStop)                 //~0223I~
    {                                                              //~0223I~
        if(Dump.Y) Dump.println("UADelayed.setStopTemporally old="+swStop+",new="+PswStop);//~0223I~
        swStopTemporally=PswStop;                                  //~0223I~
        swStop=PswStop;                                            //~0223I~
    }                                                              //~0223I~
    //*************************************************************************//~0223I~
}//class                                                           //~v@@@R~
