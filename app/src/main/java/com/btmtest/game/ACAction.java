//*CID://+va66R~: update#= 677;                                    //~v@@@R~//~v@@5R~//~v@@6R~//~v@@7R~//~9214R~//~va66R~
//**********************************************************************//~v101I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//v@@7 20190131 del GCM_DORA msg exchange(deal msg contains wanpai)//~v@@7R~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@7I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
                                                                   //~v@@@I~
import android.graphics.Point;
import android.os.Message;

import com.btmtest.BT.BTMulti;
import com.btmtest.MainView;
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.CompReqDlg;
import com.btmtest.dialog.CompleteDlg;
import com.btmtest.game.gv.DiceBox;
import com.btmtest.game.gv.GMsg;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Status.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.BT.enums.MsgIDConst.*;//~v@@@I~
import static com.btmtest.game.gv.GMsg.*;

//**********************************************************************//~v@@@I~
//*DiceCasting                                                     //~v@@@I~
//*  GVH.touchEvent()-->Action.touchDice()                         //~v@@@I~
//*  	if server  <-->diceCastOnServer()                          //~v@@@I~
//*                        DiceBox.cast()                          //~v@@@I~
//*                        --(GCM_DICE_CASTREQ)-->client           //~v@@@I~
//*     if client  --(GCM_DICE_CASTREQ)-->server<-->dice_CastReq() //~v@@@I~
//*                                                   if server <-->diceCastOnServer//~v@@@I~
//*                                                   if client <-->DiceBox.emulateDiceCasted()//~v@@@I~
//*  DiceBox--(GCM_DICE_CASTED)-->GVH-->Action.dice_Casted()       //~v@@@I~
//*     if server if responsedAll <-->diceResponsedAll()           //~v@@@I~
//*                    case GCM_FIRSTDICE         <-->Action.diceCastedAll_FirstDice()//~v@@@R~
//*                    case GCM_TEMPSTARTERDICE   <-->ActiondiceCastedAll_TempStarterDice()//~v@@@R~
//*     if client --(GCM_DICE_CASTEDRESP)-->server:Action.dice_CastedResp()//~v@@@I~
//*                                                   if responsedAll <-->diceResponsedAll()//~v@@@I~
//**********************************************************************//~v@@@I~
public class ACAction                                              //~v@@@R~
{                                                                  //~0914I~
	private Accounts accounts;                                     //~v@@@I~
    private ACATouch acatouch;                                     //~v@@@I~
	public int player1stDice;                                      //~v@@@I~
	private int ctrResponsed;                                      //~v@@@R~
	private int ctrResponsedDeal;                                  //~0309I~
	private int waitStatus;                                        //~v@@@I~
	private int diceRoll1,diceRoll2,diceCaster;                    //~v@@@R~
	private int positionAccepted;                                  //~v@@@I~
    public  int positioning1stPicker;                              //~v@@@R~
    public int[] tilesPositioning;                                 //~v@@@I~
	private boolean isServer;                                      //~v@@@I~
//*****************************                                    //~v@@@I~
	public ACAction(Accounts Paccounts)                            //~v@@@R~
    {                                                              //~0914I~
    	AG.aACAction=this;                                         //~v@@@I~
        accounts=Paccounts;                                        //~v@@@I~
        acatouch=new ACATouch(this,accounts);                      //~v@@@R~
        isServer=Accounts.isServer();                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.Constructor isServer="+isServer);         //~1506R~//~@@@@R~//~v@@@R~//~va66R~
    }                                                              //~0914I~//~v@@@R~
//***************************************************************************//~v@@@I~
    public  void setWaitStatus(int Pstatus)                        //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.setWaitStatus status="+Pstatus);//~v@@@I~
		waitStatus=Pstatus;                                        //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
    private int  getWaitStatus()                                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.getWaitStatus status="+waitStatus);//~v@@@I~
		return waitStatus;                                         //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
    private void resetCtrResponse()                                //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.resetCtrResponse");     //~v@@@I~
		ctrResponsed=0;                                            //~v@@@R~
    }                                                              //~v@@@I~
//***************************************************************************//~0309I~
    private void resetCtrResponseDeal()                            //~0309I~
    {                                                              //~0309I~
        if (Dump.Y) Dump.println("ACAction.resetCtrResponseDeal"); //~0309I~
		ctrResponsedDeal=0;                                        //~0309I~
    }                                                              //~0309I~
//***************************************************************************//~v@@@I~
    private boolean addCtrResponse()                                  //~v@@@I~
    {                                                              //~v@@@I~
		ctrResponsed++;                                            //~v@@@R~
        boolean rc=ctrResponsed==accounts.activeMembers;           //~v@@@R~
        if (Dump.Y) Dump.println("ACAction.addCtrResponse new ctr="+ctrResponsed+",activeMember="+accounts.activeMembers+",rc="+rc);//~v@@@R~
        return rc;
    }                                                              //~v@@@I~
//***************************************************************************//~0309I~
    private boolean addCtrResponseDeal()                           //~0309I~
    {                                                              //~0309I~
		ctrResponsedDeal++;                                        //~0309I~
        boolean rc=ctrResponsedDeal==accounts.activeMembers;       //~0309I~
        if (Dump.Y) Dump.println("ACAction.addCtrResponseDeal new ctr="+ctrResponsedDeal+",activeMember="+accounts.activeMembers+",rc="+rc);//~0309I~
        return rc;                                                 //~0309I~
    }                                                              //~0309I~
//***************************************************************************//~v@@@M~
    private void saveDiceRoll(int Proll1,int Proll2)               //~v@@@M~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("ACAction.saveDiceRoll roll1="+Proll1+",roll2="+Proll2);//~v@@@M~
		diceRoll1=Proll1; diceRoll2=Proll2;                        //~v@@@M~
    }                                                              //~v@@@M~
//***************************************************************************//~v@@@M~
//*on BTIOThread go to action.receivedMsg(Message) through GVH     //~v@@@R~
//***************************************************************************//~v@@@M~
	public void receivedAppMsg(int Psender,int Pmsgid,String Pdata1,String Pdata2,String Pdata3)//~v@@@R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("ACAction.receivedAppMsg msgid="+Pmsgid+",sender="+Psender+",data1="+Pdata1+",data2="+Pdata2+",data3="+Pdata3);//~v@@@R~
        GameViewHandler.sendMsg(GCM_RECEIVED_APPMSG,Pmsgid,Psender,Pdata1,Pdata2,Pdata3);  //switch to GameViewHandler thread and callback tempStarterClient//~v@@@I~
    }                                                              //~v@@@M~
//***************************************************************************//~9524I~
//emulatee recieved from myself                                    //~9524I~
//***************************************************************************//~9524I~
	public void receivedAppMsgEmulated(int Pmsgid,String Pdata1,String Pdata2,String Pdata3)//~9524I~
    {                                                              //~9524I~
    	int eswn=Accounts.getCurrentEswn();                        //~9524I~
    	int sender=AG.aAccounts.currentEswnToMember(eswn);         //~9524I~
        if (Dump.Y) Dump.println("ACAction.receivedAppMsgEmulated msgid="+Pmsgid+",currentEswn="+eswn+",sender="+sender+",data1="+Pdata1+",data2="+Pdata2+",data3="+Pdata3);//~9524I~
        GameViewHandler.sendMsg(GCM_RECEIVED_APPMSG,Pmsgid,sender,Pdata1,Pdata2,Pdata3);  //switch to GameViewHandler thread and callback tempStarterClient//~9524I~
    }                                                              //~9524I~
//***************************************************************************//~0223I~
	public void receivedAppMsgEmulatedDelayed(int Pdelay,int Pmsgid,String Pdata1,String Pdata2,String Pdata3)//~0223I~
    {                                                              //~0223I~
    	int eswn=Accounts.getCurrentEswn();                        //~0223I~
    	int sender=AG.aAccounts.currentEswnToMember(eswn);         //~0223I~
        if (Dump.Y) Dump.println("ACAction.receivedAppMsgEmulatedDelayed delay="+Pdelay+",msgid="+Pmsgid+",currentEswn="+eswn+",sender="+sender+",data1="+Pdata1+",data2="+Pdata2+",data3="+Pdata3);//~0223I~
        Message msg=GameViewHandler.obtainMsg(GCM_RECEIVED_APPMSG,Pmsgid,sender,Pdata1,Pdata2,Pdata3);  //switch to GameViewHandler thread and callback tempStarterClient//~0223I~
        GameViewHandler.sendMsgDelayed(msg,Pdelay);                //~0223I~
    }                                                              //~0223I~
//***************************************************************************//~v@@@I~
//*on GamViewHandler thread                                        //~v@@@I~
//***************************************************************************//~v@@@I~
	public void receivedAppMsg(Message Pmsg)                       //~v@@@I~
    {                                                              //~v@@@I~
    	int player,actionID;                                                //~v@@@I~
    //*******************                                          //~v@@@I~
    	int[] intp=GameViewHandler.getMsgIntData(Pmsg);                //~v@@@I~
    	String[] strp=GameViewHandler.getMsgStrData(Pmsg);             //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.receivedAppMsg msgid="+intp[0]+",sender="+intp[1]+",intp="+ Arrays.toString(intp)+",data1="+strp[0]+",data2="+strp[1]+",data3="+strp[2]);//~v@@@M~//~9817R~
    	int msgid=intp[0];                                         //~v@@@M~
    	int sender=intp[1];	//memberIndex                                        //~v@@@I~//~9A27R~
    	String data1=strp[0];                                      //~v@@@I~
    	String data2=strp[1];                                      //~v@@@I~
    	String data3=strp[2];                                      //~v@@@I~
        switch(msgid)                                             //~v@@@I~
        {                                                          //~v@@@I~
        case GCM_FIRSTDICE_SETUP:       //request by server        //~v@@@I~
            firstDice_Setup_Client(data1);                         //~v@@@I~
            break;                                                 //~v@@@I~
        case GCM_FIRSTDICE_SETUP_RESUME:       //request by server //~9902I~
            firstDice_Setup_Client_Resume(data1);                  //~9902I~
            break;                                                 //~9902I~
        case GCM_DICE_CASTREQ:          //rerequest by server      //~v@@@I~
            dice_CastReq(sender,data1,data2);                      //~v@@@R~
            break;                                                 //~v@@@I~
        case GCM_DICE_CASTEDRESP:      //client casted dice        //~v@@@I~
            dice_CastedResp();                                     //~v@@@R~
            break;                                                 //~v@@@I~
	    case GCM_TEMPSTARTER_SETUP:                                //~v@@@I~
        	player=Utils.parseInt(data1,0);                        //~v@@@R~
        	tempStarter_Setup(false/*isServer*/,player);  //switch to GameViewHandler thread and callback tempStarterClient//~v@@@R~
            break;                                                 //~v@@@I~
	    case GCM_TEMPSTARTER_CASTED:                               //~v@@@I~
        	player=Utils.parseInt(data1,1);                        //~v@@@R~
        	int spot=Utils.parseInt(data2,1);                          //~v@@@I~
        	tempStarter_Casted(player,spot,data3);  //switch to GameViewHandler thread and callback tempStarterClient//~v@@@R~
            break;                                                 //~v@@@I~
	    case GCM_LIGHT_TOUCHED:                                    //~v@@@I~
        	player=Utils.parseInt(data1,1);                        //~v@@@R~
        	acatouch.light_Touched(sender,player);                 //~v@@@I~
            break;                                                 //~v@@@I~
        case GCM_LIGHT_TOUCHED_RESP:                               //~v@@@R~
            player=Utils.parseInt(data1,1);                        //~v@@@R~
            acatouch.light_Touched_Resp(sender,player);            //~v@@@R~
            break;                                                 //~v@@@R~
	    case GCM_SETUPEND:  //client setupend                      //~v@@@I~
        	setupEndClient(sender);                                //~v@@@I~
            break;                                                 //~v@@@I~
	    case GCM_SETUP_MOVE:                                       //~v@@@I~
        	acatouch.setup_Move(sender,data1);                     //~v@@@I~
            break;                                                 //~v@@@I~
	    case GCM_SETUP_MOVED:                                      //~v@@@I~
        	acatouch.setup_Moved(sender);                          //~v@@@I~
            break;                                                 //~v@@@I~
	    case GCM_STATUS_CHANGE:                                    //~v@@@I~
        	acatouch.status_Change(Utils.parseInt(data1,0));        //~v@@@I~
            break;                                                 //~v@@@I~
	    case GCM_USER_ACTION:                                       //~v@@@I~
        	actionID=Utils.parseInt(data1,0);                  //~v@@@I~
        	user_Action(sender,actionID,data2,data3);              //~v@@@R~
            break;                                                 //~v@@@I~
	    case GCM_USER_ACTION_RESP:                                 //~v@@@I~
        	actionID=Utils.parseInt(data1,0);                  //~v@@@I~
        	user_Action_Resp(sender,actionID);                     //~v@@@I~
            break;                                                 //~v@@@I~
//        case GCM_POSITION_MOVED:    //client response of moved   //~v@@@R~
//            acatouch.position_Moved(sender);                     //~v@@@R~
//            break;                                               //~v@@@R~
//        case GCM_POSITION_MOVE_RESP:                             //~v@@@R~
//            acatouch.position_Move_Resp(sender);                 //~v@@@R~
//            break;                                               //~v@@@R~
//        case GCM_LIGHT_ENABLE:                                   //~v@@@R~
//            player=Utils.parseInt(data1,1);                      //~v@@@R~
//            acatouch.light_Enable(player);                       //~v@@@R~
//            break;                                               //~v@@@R~
          case GCM_ERRMSG:                                         //~v@@@I~
	          showErrmsg(0,data1);                                //~v@@@I~
              break;                                               //~v@@@I~
          case GCM_ERRMSG_NOLANG:                                  //~0215I~
	          showErrmsgNoLang(sender,data1,data2);                           //~0215I~//~0224R~//~0303R~
              break;                                               //~0215I~
          case GCM_ERRMSG_ANDTOAST:                                //~9B27I~
	          showErrmsg(GMSGOPT_ANDTOAST,data1);                  //~9B27I~
              break;                                               //~9B27I~
          case GCM_ERRMSG_ALL:                                     //~v@@7I~
    		  UserAction.showInfoAll(0,data1);                      //~v@@7I~
              break;                                               //~v@@7I~
          case GCM_COMPDLG_REQ:                                    //~9221I~
          case GCM_COMPDLG_RESP:                                   //~9221I~
    		  CompReqDlg.onReceivedRequest(msgid,sender,data1);      //~9221R~
              break;                                               //~9221I~
          case GCM_COMPRESULT_REQ:                                 //~9225I~
          case GCM_COMPRESULT_RESP:                                //~9225I~
          case GCM_COMPDLG_NEXTPLAYER:                             //~9A12I~
    		  CompleteDlg.onReceivedRequest(msgid,sender,data1);   //~9225I~
              break;                                               //~9225I~
          case GCM_LASTGAME:                                       //~9523I~
    		  LastGame.onReceivedAppMsg(msgid,sender,data1);   //~9523I~
              break;                                               //~9523I~
          case GCM_SUSPENDGAME:                                    //~9817I~
	          int eswn=Utils.parseInt(data1,0);                    //~9818R~
	          boolean swSuspend=Utils.parseInt(data2,0)!=0;        //~9818I~
    		  AG.aGC.suspendGame(swSuspend,eswn);                  //~9817R~//~9818R~
              break;                                               //~9817I~
          case GCM_RESTARTGAME:                                    //~9A27I~
    		  AG.aUARestart.restartGame(sender/*idxMember*/,data1);       //~9A27I~//~9A28R~
              break;                                               //~9A27I~
//************************                                         //~v@@@I~
	    case GCM_DICE_TEMPSTARTER:                                 //~v@@@I~
        	GameViewHandler.sendMsg(msgid,data1);  //switch to GameViewHandler thread and callback tempStarterClient//~v@@@I~
            break;                                                 //~v@@@I~
	    case GCM_SHOOT_FOR_TEMPSTARTER:                            //~v@@@I~
        	GameViewHandler.sendMsg(msgid,data1);  //switch to GameViewHandler thread and callback tempStarterClient//~v@@@R~
            break;                                                 //~v@@@I~
	    case GCM_TEMPSTARTER_DECIDED:                              //~v@@@I~
        	int roll1=Utils.parseInt(data1,1);                    //~v@@@I~
        	int roll2=Utils.parseInt(data2,1);                    //~v@@@I~
        	GameViewHandler.sendMsg(msgid,sender,roll1,roll2);  //switch to GameViewHandler thread and callback tempStarterClient//~v@@@I~
            break;                                                 //~v@@@I~
	    case GCM_TEMPSTARTER_DECIDED_ALL:                          //~v@@@I~
        	GameViewHandler.sendMsg(msgid,"");  //switch to GameViewHandler thread and callback tempStarterClient//~v@@@I~
            break;                                                 //~v@@@I~
	    case GCM_TEMPSTARTER_SHOOT: //client received              //~v@@@I~
        	int tempstarter=Utils.parseInt(data1,1);              //~v@@@I~
        	GameViewHandler.sendMsg(msgid,tempstarter,0,0);  //switch to GameViewHandler thread and callback//~v@@@I~
            break;                                                 //~v@@@I~
        default:                                                   //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//* from GC.touchEvent(light touched)                              //~v@@@I~
//***************************************************************************//~v@@@I~
    public void touchLight(int Pplayer)	                           //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.touchLight player="+Pplayer);//~v@@@I~
    	acatouch.touchLight(Pplayer);                               //~v@@@R~
    }                                                              //~v@@@I~
////***************************************************************************//~v@@@R~
////*on BTIOThread                                                 //~v@@@R~
////***************************************************************************//~v@@@R~
//    public void receivedAppMsgResponse(int PidxMember,int Pmsgid,String Pmsg)//~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.receivedAppMsgResponse idxMember="+PidxMember+",msgid="+Pmsgid+",msg="+Pmsg);//~v@@@R~
//        boolean rc=accounts.receivedResponse(PidxMember);   //chk resposnse received all//~v@@@R~
//        if (rc) //all client responsed                           //~v@@@R~
//        {                                                        //~v@@@R~
//            switch(Pmsgid)                                       //~v@@@R~
//            {                                                    //~v@@@R~
//            case GCM_TEMPSTARTER_DECIDED:                        //~v@@@R~
//                if (accounts.isServer())                         //~v@@@R~
//                {                                                //~v@@@R~
//                    if (Dump.Y) Dump.println("ACAction.receivedAppMsgResponse TEMPSTARTER_DECEIDED from all Member");//~v@@@R~
//                    GameViewHandler.sendMsg(GCM_TEMPSTARTER_DECIDED_ALL,"");  //switch to GameViewHandler thread and callback//~v@@@R~
//                }                                                //~v@@@R~
//                break;                                           //~v@@@R~
//            }                                                    //~v@@@R~
//        }                                                        //~v@@@R~
//    }                                                            //~v@@@R~
////***************************************************************************//~v@@@R~
////*on GVH thread,on server at DECIDED response gotten,or on cliend received DECIDED_ALL//~v@@@R~
////***************************************************************************//~v@@@R~
//    public void tempStarterDecidedAll()                          //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.tempStarterDecidedAll starter="+accounts.tempStarter);//~v@@@R~
//        showBeforeTempStarterShoot(accounts.tempStarter);        //~v@@@R~
//        if (accounts.isServer())                                 //~v@@@R~
//            accounts.sendToClient(false/*skip robot*/,GCM_TEMPSTARTER_DECIDED_ALL,0);//~v@@@R~
//    }                                                            //~v@@@R~
//*******************************************************************************//~v@@@I~
//*server received setupEnd from all client:from BTIOThread-->BTMulti->GVH//~v@@@R~
//*******************************************************************************//~v@@@I~
//    public static void setupEndAllClient()                       //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.setupEndClient");     //~v@@@R~
//        Accounts a=AG.aAccount;                                  //~v@@@R~
//        if (a==null)                                             //~v@@@R~
//            return;                                              //~v@@@R~
//        a.setupEnd(0);                                           //~v@@@R~
//    }                                                            //~v@@@R~
    //**************************************************           //~v@@@I~
    //*from GC-->GVH, after each setup end                         //~v@@@I~
    //**************************************************           //~v@@@I~
//    public static void setupEnd()                                //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.setupEnd");           //~v@@@R~
//        ACAction ac=AG.aACAction;                                //~v@@@R~
//        if (ac==null)                                            //~v@@@R~
//            return;                                              //~v@@@R~
//        ac.setupEndMember();                                     //~v@@@R~
//    }                                                            //~v@@@R~
    //**************************************************           //~v@@@I~
    //*received from client                                        //~v@@@I~
    //**************************************************           //~v@@@I~
    private void setupEndClient(int PidxMember)                    //~v@@@I~
    {                                                              //~v@@@I~
    	int stat=Status.getGameStatus();                           //~9227I~
	    if (Dump.Y) Dump.println("ACAction.setupEndClient GameStatus="+stat);//~9227R~
        if (stat!=GS_SETUP && stat!=GS_SETUPEND)                   //~9227I~
        {                                                          //~9227I~
	        if (Dump.Y) Dump.println("GameViewHandler.setupEnd ignored by status");//~9227I~
//          String nm=AG.aGC.memberName!=null ? AG.aGC.memberName[PidxMember] : AG.aBTMulti.BTGroup.getName(PidxMember);//~9227I~//~0305R~
			String[] nms=AG.aAccounts.getAccountNames();                 //~0305I~
            String nm=nms!=null ? nms[PidxMember] : AG.aBTMulti.BTGroup.getName(PidxMember);//~0305R~
	        UView.showToast(Utils.getStr(R.string.Err_StatusForSetupEnd,nm));//~9227I~
        	return;                                                //~9227I~
        }                                                          //~9227I~
        if (Dump.Y) Dump.println("ACAction.setupEnd idx="+PidxMember);//~v@@@I~
		BTMulti.setupEnd(PidxMember);                              //~v@@@I~
        chkSetupEnd();                                             //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    private int  chkSetupEnd()                                     //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.chkSetupEnd");          //~v@@@I~
        int rc=BTMulti.chkSetupEnd();	//remainig count           //~v@@@I~
        if (rc==0)                                                 //~@002I~//~v@@@I~
        {                                                          //~@002I~//~v@@@I~
        	AG.aAccounts.setupEndAll();                        //~@002I~//~v@@@I~
		}                                                          //~@002I~//~v@@@I~
        else                                                       //~@002I~//~v@@@I~
//          UView.showToast(Utils.getStr(R.string.Info_WaitingAllMemberSetup,rc));//~@002I~//~v@@@I~//~0123R~
            MainView.drawMsg(Utils.getStr(R.string.Info_WaitingAllMemberSetup,rc));//~0123I~
        if (Dump.Y) Dump.println("ACAction.chkSetupEnd remainig="+rc);//~v@@@I~
        return rc;                                                 //~@002I~//~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*from BTIOThread under serve not initialized                 //~v@@@I~
    //**************************************************           //~v@@@I~
    public static int chkSetupEndBT()                              //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.chkSetupEndBT");        //~v@@@I~
        int rc=BTMulti.chkSetupEnd();	//remainig count           //~v@@@I~
        if (rc!=0)                                                 //~v@@@I~
//          UView.showToast(Utils.getStr(R.string.Info_WaitingAllMemberSetup,rc));//~v@@@I~//~0123R~
            MainView.drawMsg(Utils.getStr(R.string.Info_WaitingAllMemberSetup,rc));//~0123I~
        if (Dump.Y) Dump.println("ACAction.chkSetupEndBT remainig="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    public void setupEndMember()                                   //~v@@@R~
    {                                                              //~v@@@I~
    	boolean rc=false;                                          //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.setupEndMember isServer="+isServer);       //~v@@@R~//~va66R~
    	Status.setGameStatus(GS_SETUPEND);                         //~v@@@I~
        if (isServer)                                              //~v@@@R~
        {                                                          //~v@@@I~
//          accounts.setupEndServer();                             //~v@@@R~
            setupEndServer();                                      //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
		    setupEndSendMsg();                                     //~v@@@I~
    }                                                              //~v@@@I~
	//******************************************************************************//~v@@@M~
	//**SetupEnd on Server                                         //~v@@@M~
	//******************************************************************************//~v@@@M~
    private void setupEndServer()                                  //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("ACAction.setupEndServer");       //~v@@@I~
		BTMulti.setupEnd(-1/*local(=server)device */);             //~v@@@I~
    	chkSetupEnd();                                             //~v@@@I~
    }                                                              //~v@@@M~
    //**************************************************           //~v@@@I~
    private void setupEndSendMsg()                                 //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.setupEndSendMsg");      //~v@@@I~
	    accounts.sendToServer(GCM_SETUPEND,"");                       //~v@@@I~
    }                                                              //~v@@@I~
//*******************************************************************************//~v@@@I~
//*on Server                                                       //~v@@@I~
//*******************************************************************************//~v@@@I~
//    public boolean tempStarter()                                 //~v@@@R~
//    {                                                            //~v@@@R~
//        boolean rc=true;                                         //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.tempStarter");        //~v@@@R~
////        if (isServer())                                        //~v@@@R~
////        {                                                      //~v@@@R~
////            rc=true;                                           //~v@@@R~
//            tempStarterServer();                                 //~v@@@R~
////        }                                                      //~v@@@R~
//        return rc;                                               //~v@@@R~
//    }                                                            //~v@@@R~
    //**************************************************           //~v@@@I~
    //*server send to all client at all setupEnd                   //~v@@@I~
    //**************************************************           //~v@@@I~
//    private void tempStarterServer()                             //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.tempStarterServer");  //~v@@@R~
//        int player=getStarterPos();                              //~v@@@R~
//        player1stDice=player;                                    //~v@@@R~
//        if (Dump.Y) Dump.println("Account.tempStarterServer 1st DicePlayer="+player);//~v@@@R~
//        showTempStarter(player);                                 //~v@@@R~
//        tempStarterSendMsg(player);                              //~v@@@R~
//    }                                                            //~v@@@R~
    //**************************************************           //~v@@@I~
    //*server send to all client at all setupEnd                   //~v@@@I~
    //**************************************************           //~v@@@I~
    public void firstDice_Setup()                                  //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.firstDice_Setup");      //~v@@@I~
		setWaitStatus(GCM_FIRSTDICE);                              //~v@@@R~
        int player=getStarterPos();                                //~v@@@I~
//      player=2; //TODO test                                      //~9B25R~
        player1stDice=player;                                      //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.firstDice_Setup 1st DicePlayer="+player);//~v@@@I~//~9902R~
        showFirstDiceShooter(player);                              //~v@@@R~
        accounts.sendToClient(false/*skip robot*/,GCM_FIRSTDICE_SETUP,player);//~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************************//~9902I~
    //*server,send to all client at all setupEnd                   //~9902I~
    //**************************************************           //~9902I~
    public void firstDice_Setup_Resume()                           //~9902I~
    {                                                              //~9902I~
//  	setWaitStatus(GCM_FIRSTDICE);                              //~9902I~
        int player=getStarterPos();                                //~9902I~
//      player1stDice=player;                                      //~9902I~
//      showFirstDiceShooter(player);                              //~9902I~
        if (Dump.Y) Dump.println("ACAction.firstDice_Setup_Resume player="+player);//~9902I~
        accounts.sendToClient(false/*skip robot*/,GCM_FIRSTDICE_SETUP_RESUME,player);//~9902I~
    }                                                              //~9902I~
    //*************************************************************************//~v@@@M~
    public static int getStarterPos()                              //~v@@@R~
    {                                                              //~v@@@M~
//      int player=Utils.getRandom(PLAYERS);                       //~v@@@R~
        int player=Utils.getRandom(AG.aAccounts.activeMembers);    //~v@@@R~
        if (Dump.Y) Dump.println("ACAction.getStarterPos player="+player);//~v@@@M~
        return player;                                             //~v@@@M~
    }                                                              //~v@@@M~
//    //**************************************************         //~v@@@R~
//    public void tempStarterSendMsg(int Pstarter)                 //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.tempStarterSendMsg player="+Pstarter);//~v@@@R~
//        accounts.sendToClient(false/*skip robot*/,GCM_DICE_TEMPSTARTER,Pstarter);//~v@@@R~
//    }                                                            //~v@@@R~
//    //**************************************************         //~v@@@R~
//    //*on client at received GCM_DICE_TEMPSTARTER under GVH thread//~v@@@R~
//    //**************************************************         //~v@@@R~
//    public void tempStarterClient(String Pmsg)                   //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.tempStarterClient msg="+Pmsg);//~v@@@R~
//        int starter=Utils.parseInt(Pmsg,-1/*default*/);          //~v@@@R~
//        if (starter!=-1)                                         //~v@@@R~
//        {                                                        //~v@@@R~
//            player1stDice=starter;                               //~v@@@R~
//        }                                                        //~v@@@R~
//        showTempStarter(starter);                                //~v@@@R~
//    }                                                            //~v@@@R~
    //**************************************************           //~v@@@I~
    //*on client at received GCM_DICE_TEMPSTARTER under GVH thread //~v@@@I~
    //**************************************************           //~v@@@I~
    private void firstDice_Setup_Client(String Pdata)               //~v@@@I~//~9B22R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.firstDice_Setup_Client data="+Pdata);//~v@@@I~//~9902R~
//        if (Status.isGaming())                                   //~9407R~
//        {                                                        //~9407R~
//            GMsg.drawMsgbar(R.string.Err_StartGameReqInGaming);  //~9407R~
//        }                                                        //~9407R~
        int starter=Utils.parseInt(Pdata,-1/*default*/);           //~v@@@I~
        if (starter!=-1)                                           //~v@@@I~
        {                                                          //~v@@@I~
            player1stDice=starter;                                 //~v@@@I~
        }                                                          //~v@@@I~
        showFirstDiceShooter(starter);                             //~v@@@R~
    }                                                              //~v@@@I~
    //**************************************************           //~9902I~
    //*on client at received GCM_FIRSTDICE_SETUP_RESUME under GVH thread //~9902I~//~0111R~
    //**************************************************           //~9902I~
    private void firstDice_Setup_Client_Resume(String Pdata)        //~9902I~//~9B22R~
    {                                                              //~9902I~
        if (Dump.Y) Dump.println("ACAction.firstDice_Setup_Client_resume data="+Pdata);//~9902I~
        if (AG.resumeHD==null)                                     //~9902I~
        {                                                          //~9902I~
	        if (Dump.Y) Dump.println("ACAction.firstDice_Setup_Client resumeHD==null");//~9902I~
            return;                                                //~9902I~
        }                                                          //~9902I~
        HistoryData hd=AG.resumeHD;                                //~9902I~
        AG.resumeHD=null;                                          //~9902I~
        AG.aAccounts.resumeGame(false/*swServer*/,hd);             //~9902I~
    }                                                              //~9902I~
    //**************************************************           //~v@@@I~
    private void showFirstDiceShooter(int Pplayer)                 //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.showFirstDiceShooter player="+Pplayer+",idxLocal="+accounts.idxLocal);//~v@@@R~
        player1stDice=Pplayer;                                      //~v@@@I~
        AG.aNamePlate.showPlate();                                 //~v@@@I~
	    enableDice(Pplayer);                                   //~v@@@I~
//      AG.aGMsg.drawMsg("仮東を決めます、ランプの場所の人が\nサイコロを振ってください");//~v@@@I~
        AG.aGMsg.drawMsgbar(R.string.Msg_DiceForTempStarter);      //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*for firstDiceShooter                                        //~9B25I~
    //**************************************************           //~9B25I~
    private  void enableDice(int Pplayer)                                 //~v@@@I~//~9B25R~
    {                                                              //~v@@@I~
//      boolean enable=Pplayer==accounts.idxLocal; //TODO test     //~v@@@I~//~9B25R~
        boolean enable=Accounts.mapDummy(Pplayer)==accounts.idxLocal;//~9B25R~
        if (Dump.Y) Dump.println("ACAction.enableDice enable="+enable+",player="+Pplayer+",idxLocal="+accounts.idxLocal);//~9B25I~
        if (enable)                                                //~v@@@I~
	        AG.aDiceBox.setWaitingDice(Pplayer);                   //~v@@@I~
        else                                                       //~v@@@I~
	        AG.aDiceBox.setWaitingDiceOther(Pplayer);              //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************************         //~v@@@R~
//    private void showBeforeTempStarterShoot(int Pplayer)         //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.showBeforeTempStarterShoot player="+Pplayer+",idxLocal="+accounts.idxLocal);//~v@@@R~
//        AG.aRiver.setup(Pplayer,false);   //show 6 tiles face down;//~v@@@R~
//        AG.aStarter.drawStarter(Pplayer);                        //~v@@@R~
//        showWaitingDiceLight(Pplayer);                           //~v@@@R~
//        AG.aGMsg.drawMsgbar(R.string.Msg_DiceForPositioning);    //~v@@@R~
//    }                                                            //~v@@@R~
    //**************************************************           //~v@@@I~
    private boolean showWaitingDiceLight(int Pplayer)              //~v@@@R~
    {                                                              //~v@@@I~
    	boolean rc;		//player is dummy                          //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.showWaitingDiceLight player="+Pplayer+",idxLocal="+accounts.idxLocal);//~v@@@I~
        boolean enable=(Accounts.mapDummy(Pplayer)==accounts.idxLocal);//~v@@@R~
        if (enable)                                                //~v@@@I~
        {                                                          //~v@@@I~
	        AG.aDiceBox.setWaitingDice(Pplayer);                   //~v@@@I~
	        if (Pplayer>accounts.activeMembers)                    //~v@@@I~
            {                                                      //~va66I~
              if (!AG.swTrainingMode)                              //~va66I~
				UView.showToastLong(R.string.Warning_DiceForDummy);//~v@@@I~
            }                                                      //~va66I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
	        AG.aDiceBox.setWaitingDiceOther(Pplayer);              //~v@@@I~
        rc=Pplayer>=accounts.activeMembers;                        //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.showWaitingDiceLight rc(dummy member)="+rc);//~v@@@I~
        return rc;
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    public void showWaitingDiceLight(int Pplayer,boolean PswShadow,boolean PswDummy)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.showWaitingDiceLight player="+Pplayer+",swShadow="+PswShadow+",swDummy="+PswDummy);//~v@@@I~//~9503R~
        if (!PswShadow)                                            //~v@@@I~
	        AG.aDiceBox.setWaitingDice(Pplayer);                   //~v@@@I~
        else                                                       //~v@@@I~
	        AG.aDiceBox.setWaitingDiceOther(Pplayer);              //~v@@@I~
        if (PswDummy)                                              //~v@@@I~
        {                                                          //~va66I~
          if (!AG.swTrainingMode)                                  //~va66I~
			UView.showToastLong(R.string.Warning_DiceForDummy);    //~v@@@I~
        }                                                          //~va66I~
    }                                                              //~v@@@I~
	//**************************************************************//~v@@@I~
	public  void touchDice()                                       //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.touchDice isServer="+isServer);//~v@@@R~
        if (isServer)                                              //~v@@@R~
        {                                                          //~v@@@I~
        	diceCaster=0;                                          //~v@@@I~
			diceCastOnServer();                                    //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            AG.aAccounts.sendToServer(GCM_DICE_CASTREQ);    //request to server to roll dice//~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//**************************************************************//~v@@@I~
	public  void diceCastOnServer()                                //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.diceCastOnServer");      //~v@@@I~//~0309R~
        resetCtrResponse();                                        //~v@@@I~
    	Point p=DiceBox.cast(true/*swServer*/);                    //~v@@@R~
		saveDiceRoll(p.x,p.y);                                     //~v@@@I~
	    String roll=p.x+MSG_SEP+p.y;                               //~v@@@I~
		accounts.sendToClient(false,-1,GCM_DICE_CASTREQ,roll);     //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************************           //~v@@@I~//~9B20R~
//    //*client received DICE_CASTREQ                                //~v@@@I~//~9B20R~
//    //**************************************************           //~v@@@I~//~9B20R~
//    private boolean diceResponsed()                                 //~v@@@R~//~9B20R~
//    {                                                              //~v@@@I~//~9B20R~
//        ctrResponsed++;                                            //~v@@@R~//~9B20R~
//        boolean rc=ctrResponsed==accounts.activeMembers;           //~v@@@R~//~9B20R~
//        if (Dump.Y) Dump.println("ACAction.diceResponsed ctr="+ctrResponsed+",rc="+rc);//~v@@@R~//~9B20R~
//        return rc;                                                 //~v@@@I~//~9B20R~
//    }                                                              //~v@@@I~//~9B20R~
    //**************************************************           //~v@@@I~
    //*server received DICE_CASTREQ from client                    //~v@@@R~
    //*client received DICE_CASTREQ from Server                    //~v@@@I~
    //**************************************************           //~v@@@I~
    private void dice_CastReq(int Psender,String Pdata1,String Pdata2)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.dice_castReq sender="+Psender+",data1="+Pdata1+",data2="+Pdata2+",isServer="+isServer);//~v@@@R~
        if (isServer)  //received from client                      //~v@@@R~
        {                                                          //~v@@@I~
        	diceCaster=Psender;                                    //~v@@@I~
			diceCastOnServer();                                    //~v@@@I~
        }                                                          //~v@@@I~
        else	//client                                           //~v@@@I~
        {                                                          //~v@@@I~
        	int roll1=Utils.parseInt(Pdata1,1);                     //~v@@@I~
        	int roll2=Utils.parseInt(Pdata2,1);                     //~v@@@I~
    		saveDiceRoll(roll1,roll2);                             //~9924I~
            DiceBox.emulateDiceCasted(false/*swServer*/,roll1,roll2);//~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*on Server or Client                                         //~v@@@I~
    //**************************************************           //~v@@@I~
    public void dice_Casted(Message Pmsg)                          //~v@@@I~
    {                                                              //~v@@@I~
        int status=Status.getGameStatus();                         //~v@@@R~
        if (Dump.Y) Dump.println("ACAction.dice_Casted status="+status+",msg="+Pmsg.what);//~v@@@I~//~9B20R~
        if (isServer)                                              //~v@@@R~
        {                                                          //~v@@@I~
        	if (addCtrResponse())	//all client resonsed          //~v@@@R~
            	diceResponsedAll();                                //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
	    	accounts.sendToServer(GCM_DICE_CASTEDRESP);           //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*client received DICE_CASTRESP                               //~0309R~
    //**************************************************           //~v@@@I~
    private void dice_CastedResp()                                 //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.dice_castedResp");      //~v@@@R~
        if (addCtrResponse())	//all client resonsed              //~v@@@I~
            diceResponsedAll();                                    //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*all member cast end                                         //~v@@@I~
    //**************************************************           //~v@@@I~
    private void diceResponsedAll()                                //~v@@@I~
    {                                                              //~v@@@I~
    	int status=getWaitStatus();                                //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.diceResponsedAll status="+status);     //~v@@@I~//~9503I~
        switch(status)                                             //~v@@@I~
        {                                                          //~v@@@I~
        case GCM_FIRSTDICE:                                        //~v@@@R~
        	diceCastedAll_FirstDice();                              //~v@@@I~
        	break;                                                 //~v@@@I~
		case GCM_TEMPSTARTERDICE:                                  //~v@@@R~
        	diceCastedAll_TempStarterDice();                       //~v@@@I~
        	break;                                                 //~v@@@I~
		case GCM_STARTGAME:	//by starter of each game              //~v@@@R~
        	diceCastedAll_StartGame();                             //~v@@@R~
        	break;                                                 //~v@@@I~
		case GCM_STARTGAME_NEXT:	//by starter of each game      //~9502I~
        	diceCastedAll_StartGameNext();                         //~9502I~
        	break;                                                 //~9502I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*on Server                                                   //~v@@@R~
    //**************************************************           //~v@@@I~
    private void diceCastedAll_FirstDice()                         //~v@@@R~
    {                                                              //~v@@@I~
//      int player=Players.playerByDice(diceRoll1+diceRoll2);      //~v@@@R~
        int player=Players.nextPlayer(diceRoll1+diceRoll2-1/*offset from base*/,diceCaster);//~v@@@R~
        if (Dump.Y) Dump.println("ACAction.diceCastedAll_FirstDice roll1="+diceRoll1+",roll2="+diceRoll2+",caster="+diceCaster+",tempStarter="+player);//~v@@@I~
        tempStarter_Setup(true/*isServer*/,player);                //~v@@@R~
//        if (!isServer)                                           //~v@@@R~
//        {                                                        //~v@@@R~
			accounts.sendToClient(false,-1,GCM_TEMPSTARTER_SETUP,player);//~v@@@I~
//        }                                                        //~v@@@R~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*on Client/Server                                            //~v@@@R~
    //**************************************************           //~v@@@I~
    private void tempStarter_Setup(boolean PswServer,int Pplayer)  //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.tempStarter_Setup swServer="+PswServer+",player="+Pplayer);//~v@@@R~
        accounts.tempStarter_Setup(Pplayer);                       //~v@@@I~
        showTempStarter(PswServer,Pplayer);                         //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    private void showTempStarter(boolean PswServer,int Pplayer)    //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.showTempStarter player="+Pplayer);//~v@@@I~
        Status.setGameStatus(GS_POSITIONING);                      //~v@@@I~
	    if (PswServer)                                             //~v@@@I~
        {                                                          //~v@@@I~
			setWaitStatus(GCM_TEMPSTARTERDICE);                    //~v@@@R~
            acatouch.resetCtrResponsed();                              //~v@@@I~
        }                                                          //~v@@@I~
        AG.aRiver.setup(Pplayer,false);	//show 6 tiles face down;  //~v@@@I~
        AG.aStarter.drawStarter(Pplayer);                          //~v@@@I~
	    showWaitingDiceLight(Pplayer);                              //~v@@@I~
        AG.aGMsg.drawMsgbar(R.string.Msg_DiceForPositioning);      //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*on Server                                                   //~v@@@I~
    //**************************************************           //~v@@@I~
    private void diceCastedAll_TempStarterDice()                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.diceCastedAll_TempStarterDice tempstarter="+accounts.tempStarter+",roll1="+diceRoll1+",roll2="+diceRoll2);//~v@@@I~//~va66R~
        int starter=accounts.tempStarter;                          //~v@@@I~
        int spot=diceRoll1+diceRoll2;                              //~v@@@I~
        positioning1stPicker=AG.aRiver.get1stPicker(starter,spot); //~v@@@R~
        accounts.posMember=AG.aRiver.getPlayerPosition(positioning1stPicker);   //waiting lamp//~v@@@R~
//  	showPositioning(true/*PswServer*/,positioning1stPicker);  //~v@@@R~//~v@@6R~
        sendPositioningData(positioning1stPicker,spot);            //~v@@@R~
    	showPositioning(true/*PswServer*/,positioning1stPicker); //after sendPositioningdata(it change game status to POSITION_ACCEPTING//~v@@6I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*on Server                                                   //~v@@@I~
    //**************************************************           //~v@@@I~
    private void sendPositioningData(int Picker,int Pspot)         //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.sendPositioningData picker="+Picker+",spot="+Pspot);//~v@@@R~
        int[] tiles=AG.aRiver.getTilesSetup();                   //~v@@@I~
        StringBuffer sb=new StringBuffer(128);                     //~v@@@R~
        sb.append(Picker+MSG_SEP+Pspot+MSG_SEP);                   //~v@@@I~
        int sz=tiles.length;                                       //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
        	sb.append(tiles[ii]+MSG_SEPAPP);                       //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.sendPositioningData data="+sb);//~v@@@R~
        accounts.sendToClient(false,-1,GCM_TEMPSTARTER_CASTED,sb.toString());//~v@@@R~
        tilesPositioning=tiles;                                     //~v@@@R~
    }                                                              //~v@@@I~
//    //**************************************************         //~v@@@R~
//    private int[] getPositioningData(String[] PsetupData)        //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.getPositioningData size="+PsetupData.length);//~v@@@R~
//        int sz=(PsetupData.length/2)*2;                          //~v@@@R~
//        int[] tiles=new int[sz];                                 //~v@@@R~
//        for (int ii=0;ii<sz;ii++)                                //~v@@@R~
//        {                                                        //~v@@@R~
//            tiles[ii]=Utils.parseInt(PsetupData[ii],0);          //~v@@@R~
//            if (Dump.Y) Dump.println("ACAction.getPositioningData ii="+ii+",data="+tiles[ii]);//~v@@@R~
//        }                                                        //~v@@@R~
//        return tiles;                                            //~v@@@R~
//    }                                                            //~v@@@R~
    //**************************************************           //~v@@@I~
    //*on Server/client                                            //~v@@@I~
    //**************************************************           //~v@@@I~
    private void showPositioning(boolean PswServer,int Ppicker)    //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.showPositioning tempstarter="+accounts.tempStarter+",roll1="+diceRoll1+",roll2="+diceRoll2+",swServer="+PswServer+",picker="+Ppicker);//~v@@@I~//~9923R~
        int starter=accounts.tempStarter;                          //~v@@@I~
        AG.aRiver.setup(starter,true);    //show 6 tile face up    //~v@@@I~
//      int pos=AG.aRiver.getPlayerPosition(Ppicker);   //waiting lamp//~v@@@I~
//      setYourPosition(pos); TODO                                 //~v@@@R~
//      showWaitingDiceLight(Ppicker);                             //~v@@@R~
//      acatouch.enableLight(Ppicker,true);                             //~v@@@I~//~v@@6R~
        Status.setGameStatus(GS_POSITION_ACCEPTING);               //~v@@@I~
        AG.aGMsg.drawMsgbar(R.string.Msg_DiceForPositionAccepting); //before enableLight because enableLight loops until next=1stPicker when isPositioningSkip mode//~va66I~
        acatouch.enableLight(Ppicker,true);                        //~v@@6R~
//      AG.aGMsg.drawMsgbar(R.string.Msg_DiceForPositionAccepting); //~v@@@I~//~0322R~//~va66R~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    public  static int[] parseAppData(String Pdata)                        //~v@@@I~
    {                                                              //~v@@@I~
	    return parseAppData(Pdata,MSG_PARSEAPP);                   //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@R~
    public  static int[] parseAllData(String Pdata)                //~v@@@R~
    {                                                              //~v@@@R~
        return parseAppData(Pdata,MSG_PARSEALL);                   //~v@@@R~
    }                                                              //~v@@@R~
    //**************************************************           //~v@@@I~
    public  static int[] parseAppData(String Pdata,String Pdelm)   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.parseAppData data="+Pdata+",delm="+Pdelm);//~v@@@I~
        String[] strs=Pdata.split(Pdelm,0/*No out size limit*/);   //~v@@@I~
        int sz=strs.length;                                        //~v@@@I~
        int[] ints=new int[sz];                                    //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
        	ints[ii]=Utils.parseInt(strs[ii],0);                   //~v@@@I~
        	if (Dump.Y) Dump.println("ACAction.parseAppData ii="+ii+"="+ints[ii]);//~v@@@I~
        }                                                          //~v@@@I~
        return ints;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*on client received positioning data                         //~v@@@R~
    //**************************************************           //~v@@@I~
    private void tempStarter_Casted(int Ppicker,int Pspot,String PsetupTiles)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.tempstarterCasted picker="+Ppicker+",spot="+Pspot+",tiledata="+PsetupTiles);//~v@@@R~
        positioning1stPicker=Ppicker;                              //~v@@@I~
//        String[] strTD=PsetupTiles.split(MSG_SEPAPP,0/*No out size limit*/);//~v@@@R~
//        int[] intTD=getPositioningData(strTD);                   //~v@@@R~
        int[] intTD=parseAppData(PsetupTiles);                     //~v@@@I~
//      AG.aRiver.setSetupTiles(strTD);                            //~v@@@R~
        AG.aRiver.setTilesSetup(positioning1stPicker,Pspot,intTD); //~v@@@R~
	    showPositioning(false/*PswServer*/,Ppicker);                //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*on Server                                                   //~v@@@I~
    //**************************************************           //~v@@@I~
    private void diceCastedAll_StartGame()                         //~v@@@I~
    {                                                              //~v@@@I~
        AG.aDiceBox.resetLight(accounts.starterRelativePos);   //new starter take that place//~v@@@I~
//      AG.aNamePlate.gameStarted();   //disable name plate        //~v@@@I~//~9502R~
//      AG.aGMsg.drawMsgbar("");                                   //~v@@@I~//~9626R~
        AG.aGMsg.reset();                                          //~9626I~
        int spot=diceRoll1+diceRoll2;                              //~v@@@I~
	    int player=accounts.starterRelativePos;                    //~v@@@R~
        if ((TestOption.option2 & TestOption.TO2_FINAL_GAME)!=0)   //~9607I~
        {                                                          //~9607I~
        	player=Players.nextPlayer(player,AG.aStatus.gameCtrGame);//~9607I~
	        if (Dump.Y) Dump.println("ACAction.diceCastedAll_StartGame finalGameTest player="+player+",starterRelative="+accounts.starterRelativePos);//~9607I~
        }                                                          //~9607I~
        if (Dump.Y) Dump.println("ACAction.diceCastedAll_StartGame starterRelative="+accounts.starterRelativePos+",roll1="+diceRoll1+",roll2="+diceRoll2+",spot="+spot);//~v@@@R~
        deal(player,spot);                                         //~v@@@R~
    }                                                              //~v@@@I~
    //**************************************************           //~9502I~
    //*on Server                                                   //~9502I~
    //**************************************************           //~9502I~
    private void diceCastedAll_StartGameNext()                     //~9502I~
    {                                                              //~9502I~
        AG.aDiceBox.resetLight(accounts.starterRelativePos);   //new starter take that place//~9502I~
//      AG.aNamePlate.gameStarted();   //disable name plate        //~9502I~
//      AG.aGMsg.drawMsgbar("");                                   //~9502I~//~9626R~
        AG.aGMsg.reset();                                          //~9626I~
        int spot=diceRoll1+diceRoll2;                              //~9502I~
	    int player=accounts.getCurrentStarter();             //~9502I~
        if (Dump.Y) Dump.println("ACAction.diceCastedAll_StartGameNext player="+player+",starterRelative="+accounts.starterRelativePos+",roll1="+diceRoll1+",roll2="+diceRoll2+",spot="+spot);//~9502I~//~va66R~
        deal(player,spot);                                         //~9502I~
    }                                                              //~9502I~
//    //**************************************************         //~v@@@R~
//    //*client received SHOOT                                     //~v@@@R~
//    //**************************************************         //~v@@@R~
//    public  void tempStarterShoot(int Pplayer)                   //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.tempStarterShoot player="+Pplayer);//~v@@@R~
//        showBeforeTempStarterShoot(Pplayer);                     //~v@@@R~
//    }                                                            //~v@@@R~
//    //**************************************************         //~v@@@R~
//    //*dice casted to get tempstarter at client or server        //~v@@@R~
//    //**************************************************         //~v@@@R~
//    public  void diceCastedTempStarter(int Proll1,int Proll2)    //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.diceCastedTempStarter roll1="+Proll1+",roll2="+Proll2);//~v@@@R~
//        String roll=Proll1+MSG_SEP+Proll2;                       //~v@@@R~
//        if (isServer)                                            //~v@@@R~
//        {                                                        //~v@@@R~
//            accounts.tempStarter_Setup(Players.playerByDice(Proll1+Proll2));//~v@@@R~
//            accounts.sendToClient(false,-1,GCM_TEMPSTARTER_DECIDED,roll);//~v@@@R~
//        }                                                        //~v@@@R~
//        else                                                     //~v@@@R~
//        {                                                        //~v@@@R~
//            accounts.sendToServer(GCM_TEMPSTARTER_DECIDED,roll); //~v@@@R~
//        }                                                        //~v@@@R~
//    }                                                            //~v@@@R~
//    //**************************************************         //~v@@@R~
//    //*on GVH;BTIO msg notified to cliend or server that dice casted to get tempstarter//~v@@@R~
//    //**************************************************         //~v@@@R~
//    public void tempStarterDecided(int PidxMember,int Proll1,int Proll2)//~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.tempStarterDecided idx="+PidxMember+",roll1="+Proll1+",roll2="+Proll2);//~v@@@R~
//        accounts.tempStarter_Setup(Players.playerByDice(Proll1+Proll2));//~v@@@R~
//        if (isServer)    //by msg setdToServer                   //~v@@@R~
//        {                                                        //~v@@@R~
//            String roll=Proll1+MSG_SEP+Proll2;                   //~v@@@R~
//            accounts.sendToClient(false,PidxMember/*skip to send*/,GCM_TEMPSTARTER_DECIDED,roll);//~v@@@R~
//        }                                                        //~v@@@R~
//        AG.aDiceBox.emulateDiceCasted(isServer,Proll1,Proll2);   //~v@@@R~
//    }                                                            //~v@@@R~
//******************************************************************************//~v@@@I~
    //*******************************************************************//~v@@@I~
    //*on Server                                                   //~v@@@I~
    //*******************************************************************//~v@@@I~
    private void deal(int Pplayer,int Pspot)                       //~v@@@I~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.deal player="+Pplayer+",spot="+Pspot);//~v@@@I~
        AG.aTiles.shuffle();                                       //~v@@@I~
        AG.aGCanvas.drawStock(Pplayer,Pspot);                      //~v@@@R~
        AG.aTiles.setInitialDeal();                                //~v@@@I~
        AG.aHands.drawHands(false/*not takeone*/,false/*not intercept*/);//~v@@@I~
      if (AG.swTrainingMode)                                       //~va66I~
		user_Action_FirstTake();                                   //~va66I~
      else                                                         //~va66I~
        sendDeal(Pspot);                                           //~v@@@R~
//      sendMsg(GCM_TAKE,null);                                    //~v@@@I~
	}                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    public static String strTD(TileData Ptd)                       //~v@@@I~
    {                                                              //~v@@@I~
//        String str=Ptd.type+MSG_SEPAPP+Ptd.number+MSG_SEPAPP+(Ptd.isRed5()?"1":"0")+MSG_SEPAPP2;//~v@@@R~
//        String str=Ptd.type+MSG_SEPAPP+Ptd.number+MSG_SEPAPP+Ptd.flag;//~v@@@R~//~v@@6R~
//      String str=Ptd.type+MSG_SEPAPP+Ptd.number+MSG_SEPAPP+Ptd.flag+MSG_SEPAPP+Ptd.ctrRemain+MSG_SEPAPP+Ptd.eswn;//~v@@6I~//~9218R~
        String str=TileData.toSendText(Ptd);                       //~9218I~
		if (Dump.Y) Dump.println("ACAction.strTD str="+str);       //~v@@@I~
        return str;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    public static TileData splitToTD(String Pstr)                  //~v@@@I~
    {                                                              //~v@@@I~
	    int[] ints=parseAppData(Pstr);                             //~v@@@I~
//      TileData td=new TileData(ints[1]/*num*/,ints[0]/*type*/,ints[2]!=0/*red5*/);//~v@@@R~
        TileData td=new TileData(true/*swEswnToPlayer*/,ints,0/*type pos*/);              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.splitToTD str="+Pstr+",type="+ints[0]+",num="+ints[1]+",red5="+ints[2]);//~v@@@I~
        return td;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    private String makeDealMsg(int Pplayer,int Pspot)              //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.makeDealMsg player="+Pplayer);//~v@@@I~
        TileData[] tds;                                            //~v@@6R~
        StringBuffer sb=new StringBuffer(1024); //data2             //~v@@@R~//~v@@6I~
        sb.append(Pspot+MSG_SEP); //data2                          //~v@@@I~//~v@@6M~
                                                                   //~v@@6I~
        tds=AG.aTiles.getShuffled();                                    //~v@@6I~
        for (int ii=0;ii<TILECTR_KEEPLEFT;ii++)	//wanpai 7*2=14    //~v@@6I~
        {                                                          //~v@@6I~
        	sb.append(strTD(tds[ii])+MSG_SEPAPP2);                 //~v@@6I~
        }                                                          //~v@@6I~
        tds=AG.aPlayers.getHands(Pplayer);                         //~v@@6I~
        for (TileData td:tds)                                     //~v@@@I~
        {                                                          //~v@@@I~
        	sb.append(strTD(td)+MSG_SEPAPP2);                      //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.makeDealMsg data="+sb); //~v@@@R~
        return sb.toString();                                      //~v@@@R~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    private TileData[][] parseDealData(String Pdata)                 //~v@@@I~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.parseDealData data="+Pdata);//~v@@@I~
//        String[] strs=Pdata.split(MSG_SEPAPP,0/*No out size limit*/);//~v@@@R~
    	int[] ints=parseAppData(Pdata);                            //~v@@@I~
        int sz=(ints.length)/PARMPOS_CTRFORTD;                                    //~v@@@R~//~v@@6R~
        TileData[] tds=new TileData[sz];                           //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
//          int type=ints[ii*3];                                   //~v@@@R~
//          int num =ints[ii*3+1];                                 //~v@@@R~
//          boolean red5=ints[ii*3+2]!=0;                          //~v@@@R~
//      	tds[ii]=new TileData(num,type,red5);                   //~v@@@R~
//      	tds[ii]=new TileData(true/*swEswnToPlayer*/,ints,ii*3/*type pos*/);           //~v@@@I~//~v@@6R~
        	tds[ii]=new TileData(true/*swEswnToPlayer*/,ints,ii*PARMPOS_CTRFORTD);//~v@@6I~
        	if (Dump.Y) Dump.println("ACAction.parseDealData ii="+ii+",type="+tds[ii].type+",num="+tds[ii].number+",flag="+tds[ii].flag);//~v@@@R~
        }                                                          //~v@@@I~
        TileData tdss[][]=new TileData[2][];                       //~v@@6I~
        tdss[0]=tds;      //wanpai                                 //~v@@6R~
        TileData[] tdsH=new TileData[HANDCTR];                       //~v@@6I~
        System.arraycopy(tds,TILECTR_KEEPLEFT,tdsH,0,HANDCTR);     //~v@@6R~
        tdss[1]=tdsH;                                              //~v@@6R~
        return tdss;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    private void sendDeal(int Pspot)                               //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.sendDeal currentESWN="+Accounts.getCurrentEswn());//~v@@@R~
		setWaitStatus(GCM_DEAL);                                   //~v@@@R~
//      resetCtrResponse();                                        //~v@@@I~//~0309R~
        resetCtrResponseDeal();                                    //~0309I~
        addCtrResponseDeal();   //1 of server                            //~v@@@I~//~0309R~
        for (int ii=1;ii<PLAYERS;ii++)                             //~v@@5I~
        {                                                          //~v@@5I~
            String deal=makeDealMsg(ii,Pspot);                     //~v@@5I~
        	int idxAccount=accounts.playerToMember(ii);            //~v@@5I~
            sendActionData(false/*sendToRobot*/,idxAccount,GCM_DEAL,deal);//~v@@5I~
        }                                                          //~v@@5I~
    }                                                              //~v@@@I~
//    //*******************************************************************//~v@@@I~//~v@@7R~//~9214R~
//    private void sendDora()                                        //~v@@@I~//~v@@7R~//~9214R~
//    {                                                              //~v@@@I~//~v@@7R~//~9214R~
//        int starter=accounts.getCurrentStarter();                           //~v@@@R~//~v@@7R~//~9214R~
//        int spot=diceRoll1+diceRoll2;                              //~v@@@I~//~v@@7R~//~9214R~
//        if (Dump.Y) Dump.println("ACAction.sendDora currentESWN="+accounts.getCurrentEswn()+",starter="+starter+",spot="+spot);//~v@@@R~//~v@@7R~//~9214R~
//        int player=Players.nextPlayer(starter,spot-1);             //~v@@@R~//~v@@7R~//~9214R~
//        Point outp=new Point();                                    //~v@@@I~//~v@@7R~//~9214R~
//        TileData td=AG.aStock.getTileDora(player,spot,AG.aTiles.ctrKan,outp);//~v@@@R~//~v@@7R~//~9214R~
//        int cutplayer=outp.x;  //advance cutpos if cutpos==2           //~v@@@I~//~v@@7R~//~9214R~
////      int cutESWN=Players.nextPlayer(accounts.currentESWN,cutplayer);//~v@@@R~//~v@@7R~//~9214R~
//        int cutESWN=Players.nextPlayer(accounts.getCurrentEswn(),cutplayer);//~v@@@I~//~v@@7R~//~9214R~
//        int cutpos=outp.y;                                        //~v@@@I~//~v@@7R~//~9214R~
//        if (Dump.Y) Dump.println("ACAction.sendDora cutESWN="+cutESWN+",cutplayer="+cutplayer+",cutpos="+cutpos);//~v@@@R~//~v@@7R~//~9214R~
//        String data=cutESWN+MSG_SEPAPP2+cutpos+MSG_SEPAPP2+AG.aTiles.ctrKan+MSG_SEPAPP2//~v@@@R~//~v@@7R~//~9214R~
////                        +td.type+MSG_SEPAPP+td.number+MSG_SEPAPP+(td.isRed5()?"1":"0");//~v@@@R~//~v@@7R~//~9214R~
//                        +ACAction.strTD(td);                      //~v@@@I~//~v@@7R~//~9214R~
//        sendActionData(false,GCM_DORA,data);                       //~v@@@R~//~v@@7R~//~9214R~
//    }                                                              //~v@@@I~//~v@@7R~//~9214R~
    //*******************************************************************//~v@@@I~
    public int sendActionData(boolean PswRobot,int PactionID,String Pdata)//~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.sendActionData swRobot="+PswRobot+",currentESWN="+accounts.getCurrentEswn()+",actionid="+PactionID+",data="+Pdata);//~v@@@R~
		setWaitStatus(PactionID);                                  //~v@@@I~
        resetCtrResponse();                                        //~v@@@I~
        addCtrResponse();   //of server                            //~v@@@I~
        int sendctr=0;                                             //~v@@@I~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~v@@@R~
        for (int ii=1;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
//            if (ii!=accounts.getCurrentESWN())                   //~v@@@R~
//            {                                                    //~v@@@R~
//                int idxAccount=accounts.currentEswnToMember(ii); //~v@@@R~
//                sendActionData(true/*sendToRobot*/,idxAccount,PactionID,Pdata);//~v@@@R~
			    if (sendActionDataPlayer(PswRobot,ii,PactionID,Pdata))//~v@@@R~
	                sendctr++;                                     //~v@@@R~
//            }                                                    //~v@@@R~
        }                                                          //~v@@@I~
        return sendctr;                                            //~v@@@I~
    }                                                              //~v@@@I~
//    //*******************************************************************//~v@@@R~
//    public int sendUserActionDataPlayer(boolean PswRobot,int PactionID,String Pdata)//~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.senUserdActionData currentESWN="+accounts.getCurrentESWN()+",actionid="+PactionID+",data="+Pdata);//~v@@@R~
//        int sendctr=0;                                           //~v@@@R~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~v@@@R~
//        {                                                        //~v@@@R~
//            if (ii!=accounts.getCurrentESWN())                   //~v@@@R~
//            {                                                    //~v@@@R~
//                if (sendActionDataPlayer(PswRobot,ii,PactionID,Pdata))  //not dummy//~v@@@R~
//                    sendctr++;                                   //~v@@@R~
//            }                                                    //~v@@@R~
//        }                                                        //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.senUserdActionData sendctr="+sendctr);//~v@@@R~
//        return sendctr;                                          //~v@@@R~
//    }                                                            //~v@@@R~
    //*******************************************************************//~v@@@I~
    public boolean sendActionDataPlayer(boolean PswRobot,int Pplayer,int PactionID,String Pdata)//~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.sendActionDataPlayer swRobot="+PswRobot+",player="+Pplayer+",currentESWN="+accounts.getCurrentEswn()+",actionid="+PactionID+",data="+Pdata);//~v@@@R~
//      int idxAccount=accounts.currentEswnToMember(Pplayer);      //~v@@@R~
        int idxAccount=accounts.playerToMember(Pplayer);           //~v@@@I~
	    boolean rc=sendActionData(PswRobot,idxAccount,PactionID,Pdata);//~v@@@R~
		if (Dump.Y) Dump.println("ACAction.sendActionDataPlayer rc="+rc);//~v@@@R~
        return rc;
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
	private boolean sendActionData(boolean PswRobot,int PidxAccount,int PactionID,String Pdata)//~v@@@R~
    {                                                              //~v@@@I~
		boolean rc=accounts.sendToClientAction(PswRobot,PidxAccount,GCM_USER_ACTION,PactionID,Pdata);//~v@@@R~
		if (Dump.Y) Dump.println("ACAction.sendActionData swRobot="+PswRobot+",idx="+PidxAccount+",actionID="+PactionID+",data="+Pdata+",rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    public static String strUserActionData(int PactionID,int Pplayer,String PstrData)//~v@@@I~
    {                                                              //~v@@@I~
    	String str=PactionID+MSG_SEP+Pplayer+MSG_SEP+PstrData;     //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.strUserActionData actionID="+PactionID+",player="+Pplayer+"outstr="+str);//~v@@@I~
        return str;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
//    public static int[] parseUserActionData(String Pdata)        //~v@@@R~
//    {                                                            //~v@@@R~
//        int[] intapp=parseAppData(Pdata);                        //~v@@@R~
//        int[] ints=new int[2+intapp.length];                     //~v@@@R~
//        int[-]=strs[0];                                          //~v@@@R~
//        if (Dump.Y) Dump.println("ACAction.parseUserActionData str="+Pdata+",out ctr="+ints.length);//~v@@@R~
//        return ints;                                             //~v@@@R~
//    }                                                            //~v@@@R~
    //*******************************************************************//~v@@@I~
    //*reveived GCM_USER_ACTION                                    //~v@@@I~
    //*******************************************************************//~v@@@I~
	private void user_Action(int Psender,int PactionID/*data1*/,String Pdata2,String Pdata3)//~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.user_Action isServer="+isServer+",sender="+Psender+",actionID="+PactionID+",data2="+Pdata2+",data3="+Pdata3);//~v@@@R~
    	if (isServer)                                               //~v@@@I~
        {                                                          //~v@@@I~
//            if (addCtrResponse())   //server get all client response//~v@@@R~//~v@@5R~
//            {                                                      //~v@@@I~//~v@@5R~
				if (Dump.Y) Dump.println("ACAction.user_Action responseAll");//~v@@@I~
                switch(PactionID)                                  //~v@@@I~
                {                                                  //~v@@@I~
                case GCM_DEAL:	//get deal response                //~v@@@R~
//  	        	if (addCtrResponse())	//server get all client response//~v@@5I~//~0309R~
    	        	if (addCtrResponseDeal())	//server get all client response//~0309I~
                    {                                              //~9214I~
//                      sendDora();                                    //~v@@@I~//~v@@5R~//~v@@7R~//~9214R~
                        user_Action_FirstTake();                   //~9214I~
                    }                                              //~9214I~
                    break;                                         //~v@@@I~
//                case GCM_DORA:  //get dora response                //~v@@@I~//~v@@7R~//~9214R~
////                  AG.aUserAction.actionTaken(true/*PswServer*/,GCM_TAKE,accounts.getCurrentStarterPos());//~v@@@R~//~v@@7R~//~9214R~
//                    if (addCtrResponse())   //server get all client response//~v@@5I~//~v@@7R~//~9214R~
//                        user_Action_FirstTake();                       //~v@@@I~//~v@@5R~//~v@@7R~//~9214R~
//                    break;                                         //~v@@@I~//~v@@7R~//~9214R~
                default:                                           //~v@@@I~
	                AG.aUserAction.actionReceived(PactionID,Pdata2);//~v@@@I~
                }                                                  //~v@@@I~
//            }                                                      //~v@@@I~//~v@@5R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            switch(PactionID)                                      //~v@@@R~
            {                                                      //~v@@@R~
            case GCM_DEAL:                                         //~v@@@R~
                user_Action_Deal(Pdata2,Pdata3);                   //~v@@@R~
	            accounts.sendToServer(GCM_USER_ACTION,PactionID);  //~v@@@I~
                break;                                             //~v@@@R~
//            case GCM_DORA:                                         //~v@@@R~//~v@@7R~//~9214R~
//                user_Action_Dora(Pdata2);                          //~v@@@R~//~v@@7R~//~9214R~
//                accounts.sendToServer(GCM_USER_ACTION,PactionID);  //~v@@@I~//~v@@7R~//~9214R~
//                break;                                             //~v@@@I~//~v@@7R~//~9214R~
            default:                                               //~v@@@R~
                AG.aUserAction.actionReceived(PactionID,Pdata2);   //~v@@@R~
            }                                                      //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*server reveived GCM_USER_ACTION_RESP                        //~v@@@I~
    //*******************************************************************//~v@@@I~
	private void user_Action_Resp(int Psender,int PactionID)       //~v@@@I~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.user_Action_Resp isServer="+isServer+",sender="+Psender+",actionID="+PactionID);//~v@@@I~
        AG.aUserAction.actionReceivedResp(PactionID);              //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*Client received Deal                                        //~v@@@I~
    //*******************************************************************//~v@@@I~
	private void user_Action_Deal(String Pdata2,String Pdata3)     //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.user_Action_Deal isServer="+isServer+",data2="+Pdata2+",Pdata3="+Pdata3);//~v@@@R~
        int spot=Utils.parseInt(Pdata2,2);                         //~v@@@I~
//      TileData[] tds=parseDealData(Pdata3);                      //~v@@@R~//~v@@6R~
        TileData[][] tdss=parseDealData(Pdata3);                   //~v@@6I~
        int posStarter=accounts.getCurrentStarterPos();            //~v@@@I~
//      AG.aGCanvas.drawStock(posStarter,spot);                      //~v@@@R~//~v@@7R~
//      AG.aPlayers.setInitialDeal(PLAYER_YOU,tds,0/*start index of tds*/);//~v@@@R~
        AG.aTiles.setShuffled(tdss[0]);                          //~v@@6I~
        AG.aPlayers.setInitialDeal(tdss[1],0/*start index of tds*/,accounts.getCurrentEswn());//~v@@@R~//~v@@6R~
        AG.aHands.drawHands(tdss[1]);                                  //~v@@@I~
        AG.aGCanvas.drawStock(posStarter,spot);  //after setShuffled//~v@@7I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*not used                                                    //+va66I~
    //*******************************************************************//+va66I~
	private void user_Action_Dora(String Pdata)                    //~v@@@I~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.user_Action_Dora isServer="+isServer+",data="+Pdata);//~v@@@I~
    	int[] ints=parseAppData(Pdata);                            //~v@@@I~
//      cutplayer,cutpos,ctrKan,td.type,td.number,td.isDora5()?"1":"0";//~v@@@I~
        int eswn=ints[0];                                          //~v@@@I~
        int pos=ints[1];                                           //~v@@@I~
        int ctrKan=ints[2];                                        //~v@@@I~
//      TileData td=new TileData(ints[4]/*number*/,ints[3]/*type*/,ints[5]!=0);//~v@@@R~
        TileData td=new TileData(true/*swEswnToPlayer*/,ints,PARMPOS_TD_DORA/*type pos*/);              //~v@@@I~//~v@@6R~
        int player=Players.playerRelative(eswn,accounts.getCurrentEswn());	//relative pos from your ESWN//~v@@@R~
		if (Dump.Y) Dump.println("ACAction.user_Action_Dora eswn="+eswn+",pos="+pos+",ctrKan="+ctrKan+",currentESWN="+accounts.getCurrentEswn()+",player="+player);//~v@@@R~
        AG.aStock.drawDora(player,pos,ctrKan,td);                  //~v@@@R~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*on Server                                                   //~v@@@I~
    //*******************************************************************//~v@@@I~
	private void user_Action_FirstTake()                           //~v@@@I~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.user_Action_FirstTake isServer="+isServer+",status="+Status.getGameStatus());//~v@@@R~
		Status.setGameStatus(GS_GAME_STARTED);                     //~v@@@I~
        AG.aACATouch.sendStatusChange(GS_GAME_STARTED);                         //~v@@@I~
        int player=accounts.getCurrentStarterPos();                //~v@@@R~
		if (Dump.Y) Dump.println("ACAction.user_Action_FirstTake currentStarter="+player);//~va66I~
        if (player==PLAYER_YOU)                                    //~v@@@I~
	        GameViewHandler.sendMsg(GCM_TAKE,player,0,0);          //~v@@@R~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	String msg=Integer.toString(PLAYER_YOU/*eswn*/);       //~v@@@R~
	        AG.aUserAction.actionReceived(GCM_TAKE,msg);           //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
	public static void showErrmsg(int Popt,String Pmsg)            //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("ACAction.showErrmsg msg="+Pmsg); //~v@@@I~
//      AG.aGMsg.drawMsgbar(Pmsg); //TDO test                      //~v@@@R~
        AG.aGMsg.errorMsg(Popt,Pmsg);                             //~v@@@R~
    }                                                              //~v@@@I~
    //*******************************************************************//~0215I~
	public static void showErrmsgNoLang(int Psender,String Pdata,String PstrParm) //~0215I~//~0224R~//~0225R~//~0303R~
    {                                                              //~0215I~
		if (Dump.Y) Dump.println("ACAction.showErrmsgNoLang sender="+Psender+",data="+Pdata+",strParm="+Utils.toString(PstrParm));//~0215I~//~0224R~//~0303R~
        int idxSkip=Psender;                                       //~0225I~
	    int[] intp= GMsg.parseSendMsg(Pdata);                      //~0224I~
        int opt=intp[0];                                           //~0224I~
        int msgid=intp[1];                                         //~0224I~
		if (Dump.Y) Dump.println("ACAction.showErrmsgNoLang msgid="+Integer.toHexString(msgid)+",opt=0x"+Integer.toHexString(opt));//~0224I~//~0225R~
        if (msgid!=0)                                //~0215I~
        {                                                          //~0224I~
            if ((opt & GMSGOPT_STRPARM)!=0)              //received from client by showInfoAll(Eswn)//~0303I~
            {                                                      //~0303I~
        		int eswn;
                if ((opt & GMSGOPT_ESWN)!=0)              //received from client by showInfoAll//~0303I~
                	eswn=intp[2];                                  //~0303I~
                else                                               //~0303I~
                	eswn=-1;                                       //~0303I~
                showErrmsgNoLang(Psender,opt,eswn,msgid,PstrParm);//~0303I~
                return;                                            //~0303I~
            }                                                      //~0303I~
        	if ((opt & GMSGOPT_ALL)!=0)              //received from client by showInfoAll(Eswn)//~0225R~
            {                                                      //~0225I~
        		if ((opt & GMSGOPT_ESWN)!=0)              //received from client by showInfoAll//~0225I~
					UserAction.showInfoAllEswnEswn((opt & ~GMSGOPT_ALL),idxSkip,intp[2]/*eswn*/,msgid); //pass eswn//~0225R~
                else                                               //~0225I~
    		  		UserAction.showInfoAll(opt,idxSkip,msgid);//showinfo and sendto client //~0224I~//~0225R~
            }                                                      //~0225I~
            else                                                   //~0224I~
            {                                                      //~0225I~
        		if ((opt & GMSGOPT_ESWN)!=0)              //received from client by showInfoAll//~0225I~
					UserAction.showInfoEswn(opt,intp[2],msgid);    //~0225R~
            	else                                               //~0225I~
					showErrmsg(opt,msgid);                                //~0215I~//~0224R~//~0225R~
            }                                                      //~0225I~
        }                                                          //~0224I~
    }                                                              //~0215I~
    //*******************************************************************//~0303I~
	public static void showErrmsgNoLang(int Psender,int Popt,int Peswn,int Pmsgid,String PstrParm)//~0303I~
    {                                                              //~0303I~
		if (Dump.Y) Dump.println("ACAction.showErrmsgNoLang sender="+Psender+",strParm="+Utils.toString(PstrParm));//~0303I~
		if (Dump.Y) Dump.println("ACAction.showErrmsgNoLang msgid="+Integer.toHexString(Pmsgid)+",opt=0x"+Integer.toHexString(Popt));//~0303I~
        int idxSkip=Psender;                                       //~0303I~
        if ((Popt & GMSGOPT_ALL)!=0)              //received from client by showInfoAll(Eswn)//~0303I~
        {                                                          //~0303I~
            if ((Popt & GMSGOPT_ESWN)!=0)              //received from client by showInfoAll//~0303I~
                UserAction.showInfoAllEswnEswn((Popt & ~GMSGOPT_ALL),idxSkip,Peswn,Pmsgid,PstrParm); //pass eswn//~0303I~
            else                                                   //~0303I~
                UserAction.showInfoAll(Popt,idxSkip,Pmsgid,PstrParm);//showinfo and sendto client//~0303I~
        }                                                          //~0303I~
        else                                                       //~0303I~
        {                                                          //~0303I~
            if ((Popt & GMSGOPT_ESWN)!=0)              //received from client by showInfoAll//~0303I~
                UserAction.showInfoEswn(Popt,Peswn,Pmsgid,PstrParm);//~0303I~
            else                                                   //~0303I~
                showErrmsg((Popt&~GMSGOPT_STRPARM),Utils.getStr(Pmsgid,PstrParm));//~0303R~
        }                                                          //~0303I~
    }                                                              //~0303I~
    //*******************************************************************//~v@@5I~
	public static void showErrmsg(int Popt,int Pmsgid)             //~v@@5I~
    {                                                              //~v@@5I~
		if (Dump.Y) Dump.println("ACAction.showErrmsg msgid=x"+Integer.toHexString(Pmsgid));//~v@@5I~
        AG.aGMsg.errorMsg(Popt,Utils.getStr(Pmsgid));              //~v@@5I~
    }                                                              //~v@@5I~
}//class ACAction                                                 //~dataR~//~@@@@R~//~v@@@R~
