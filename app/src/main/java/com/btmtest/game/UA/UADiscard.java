//*CID://+va8yR~: update#= 639;                                    //+va8yR~
//**********************************************************************//~v101I~
//2021/05/02 va8y (Bug)Kuikae err chk should check type also.      //+va8yI~
//2021/04/05 va78 (Bug)PlayAlone notifymode; next player is not blocked by pending on//~va78I~
//2021/04/04 va77 (Bug)when manual robot(autotake) mode,chii is not notified because autotake timeout is not set//~va77I~
//2021/03/31 va75 Autotake when Notify mode(Chii or Take)          //~va75I~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va60I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/11/04 va28 Delete force reach option, local yaku is all abount patterns, is not ron format.//~va28I~
//2020/11/03 va27 Tenpai chk at Reach                              //~va27I~
//2020/10/13 va15 Add chk kuikae                                   //~va15I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.PrefSetting;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.GC;
import com.btmtest.game.Players;
import com.btmtest.game.Robot;
import com.btmtest.game.TileData;
import com.btmtest.game.UADelayed;
import com.btmtest.game.UADelayed2;
import com.btmtest.game.gv.GMsg;
import com.btmtest.game.gv.Hands;
import com.btmtest.game.gv.River;
import com.btmtest.game.gv.Stock;
import com.btmtest.game.UserAction;                                //~v@@@I~
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.Sound;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.TestOption.*;
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;                           //~v@@@I~
import static com.btmtest.game.Players.*;
import static com.btmtest.game.RA.RAConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.UserAction.*;                       //~v@@@I~
                                                                   //~v@@@I~
public class UADiscard                                             //~v@@@R~
{                                                                  //~0914I~
    private UserAction UA;                                         //~v@@@I~
    private Players PLS;                                           //~v@@@R~
    private UADelayed2 UADL;                                       //~9B28I~
    private boolean isServer;                                      //~v@@@I~
    private Hands hands;                                           //~v@@@I~
    private Stock stock;                                           //~v@@@I~
    private River river;                                           //~v@@@R~
    private TileData infoSelectedTD;                               //~v@@@I~
   private boolean swCheckReach;                                    //~va27I~
    private boolean swManualRobot;   //take by button in training mode//~va66R~
    private int typeSameMeld;                                          //~va60I~
//  private TileData tdPlayAlone;                                  //~va70R~
//*************************                                        //~v@@@I~
	public UADiscard(UserAction PuserAction)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UADiscard Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        UA=PuserAction;                                            //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	public void init()                                             //~v@@@I~
    {                                                              //~v@@@I~
        PLS=AG.aPlayers;                                           //~v@@@R~
        UADL=AG.aUADelayed;                                        //~9B28I~
//      tiles=AG.aTiles;                                           //~v@@@R~//~v@@6R~
        hands=AG.aHands;                                           //~v@@@I~
        river=AG.aRiver;                                           //~v@@@R~
        stock=AG.aStock;                                           //~v@@@R~
//        accounts=AG.aAccounts;                                   //~v@@@I~
//        acaction=AG.aACAction;                                   //~v@@@I~
//      isServer=Accounts.isServer();                              //~v@@@R~//~v@@6R~
//      delayTake=OperationSetting.getDelayTake();                 //~v@@6R~
//      delayPonKan=OperationSetting.getDelayPonKan();             //~v@@6R~
//      timeout=RuleSetting.getTimeoutTake();                      //~v@@6R~
    	swCheckReach= RuleSettingOperation.isCheckReach();         //~va27I~
        typeSameMeld=RuleSetting.getSameMeld();                    //~va60I~
        if (AG.swTrainingMode)                                     //~va66I~
	    	swManualRobot=RuleSettingOperation.isAllowRobotAllButton();//~va66R~
        if (Dump.Y) Dump.println("UADiscard init");//~v@@@R~       //~v@@6R~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    private TileData selectTile(int Pplayer)                       //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.selectTile");          //~v@@@R~
        TileData td=PLS.getTileSelected(Pplayer);                  //~v@@@R~
        return td;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@I~//~va70R~
//    public boolean discard(int Pplayer) //No user                            //~v@@@I~//~va70R~
//    {                                                              //~v@@@I~//~va70R~
////        if (PLS.isTakeAvailable())                               //~v@@@R~//~va70R~
////        {                                                        //~v@@@I~//~va70R~
////            if (Dump.Y) Dump.println("UserAction.discard Dup Discard or not take 1st");//~v@@@I~//~va70R~
////            return;                                              //~v@@@I~//~va70R~
////        }                                                        //~v@@@I~//~va70R~
//        if (Dump.Y) Dump.println("UADiscard.discard");             //~v@@@R~//~va70R~
////        if (!PLS.isYourTurn(actionID,Pplayer))                   //~v@@@R~//~va70R~
////            return;                                              //~v@@@I~//~va70R~
//        int player=PLS.getCurrentPlayer();                         //~v@@@R~//~va70R~
//        TileData td=selectTile(player);                            //~v@@@I~//~va70R~
//        if (td==null)                                              //~v@@@I~//~va70R~
//        {                                                          //~v@@@I~//~va70R~
//            GC.actionError(0,player,R.string.AE_NoTileSelected);           //~v@@@I~//~v@@6R~//~va70R~
//            return false;                                          //~v@@@I~//~va70R~
//        }                                                          //~v@@@I~//~va70R~
//        if (Dump.Y) Dump.println("UADiscard.discard player="+player+",tile type="+td.type+",num="+td.number);//~v@@@R~//~va70R~
//        TileData tdDiscarded=river.eraseTaken();    //erased lastdiscarded//~v@@@I~//~va70R~
//        if (tdDiscarded!=null)  //erased lastdiscarded             //~v@@@I~//~va70R~
//            PLS.takenDiscarded(tdDiscarded.player); //erased lastdiscarded//~v@@@R~//~va70R~
//        PLS.discard(player,td); //shift currentPlayer              //~v@@@R~//~va70R~
//        hands.discard(player,td);                                  //~v@@@I~//~va70R~
//        river.drawDiscarded();                                     //~v@@@I~//~va70R~
//        return true;                                               //~v@@@I~//~va70R~
//    }                                                              //~v@@@I~//~va70R~
	//*************************************************************************//~v@@@I~
	//*by button action                                            //~v@@@I~
	//*************************************************************************//~v@@@I~
    public boolean selectInfo(boolean PswServer,int Pplayer)      //~v@@@R~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.selectInfo swServer="+PswServer+",player="+Pplayer);         //~v@@@R~//~v@@6R~//~va66R~
        if ((TestOption.option2 & TestOption.TO2_SUSPEND)!=0)                 //~9819I~//~9820I~//~v@@6I~
        {                                                          //~9820I~//~v@@6I~
        	if (Dump.Y) Dump.println("UADiscard.selectInfo TO2_SUSPEND discardedCtr="+AG.aPlayers.getDiscardedCtr(PLAYER_YOU));//~v@@6R~
            if (AG.aPlayers.getDiscardedCtr(PLAYER_YOU)==0)        //~v@@6I~
	            AG.aGC.suspendGame(true/*suspend*/,-1/*useraction*/);//by TestOption //~9820R~//~v@@6I~//~0307R~
        }                                                          //~9820I~//~v@@6I~
		if ((TestOption.option2 & TO2_ROBOT_DISCARD_BUTTON)!=0)    //~va66I~
            if (!PswServer && Pplayer!=PLAYER_YOU)  //robot discard is allowed server only with testoption//~va66I~
            {                                                      //~va66I~
                GMsg.drawMsgbar(R.string.AE_TestRobotDiscardButtonServerOnly);//~va66I~
                return false;                                      //~va66I~
            }                                                      //~va66I~
        AG.aHandsTouch.enableMultiSelectionMode(false);            //~v@@6I~
//      TileData td=selectTile(PLAYER_YOU);                        //~v@@@I~//~va66R~
        TileData td=selectTile(Pplayer);                           //~va66I~
        if (td==null)                                              //~v@@@I~
        {                                                          //~v@@@I~
        	GC.actionError(0,Pplayer,R.string.AE_NoTileSelected);  //~v@@6R~
        	return false;                                          //~v@@@I~
        }                                                          //~v@@@I~
//TODO  if (AG.aTiles.chkLast())    //set to discarded for hotei ron//~va6aR~
//TEST  	td.addFlag(TDF_LAST);                                  //~va6aR~
        if (Dump.Y) Dump.println("UADiscard.selectInfo td="+td.type+":"+td.number+":"+td.flag);//~v@@@R~//~v@@6R~
//  	if (!chkTenpai(td))                                        //~va27I~//~va66R~
    	if (!chkTenpai(td,Pplayer))                                //~va66I~
			return false;                                          //~va27I~
    	if (isSameMeld(td))                                        //~va15I~
        	return false;                                          //~va15I~
        infoSelectedTD=td;                                         //~v@@@I~
//      UA.msgDataToServer=UserAction.makeMsgDataToServer(Pplayer,td);//~v@@@R~
		if (!PswServer)                                            //~v@@@I~
	        UA.msgDataToServer=ACAction.strTD(td);                 //~v@@@R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~va27I~
//  private boolean chkTenpai(TileData PtdDiscard)                 //~va27I~//~va66R~
    private boolean chkTenpai(TileData PtdDiscard,int Pplayer)     //~va66I~
    {                                                              //~va27I~
    	boolean rc=true;                                           //~va27I~
        if (Dump.Y) Dump.println("UADiscard.chkTenpai");           //~va27I~
//  	int statReachOld=PLS.getReachStatus(PLAYER_YOU);           //~va27I~//~va66R~
    	int statReachOld=PLS.getReachStatus(Pplayer);              //~va66I~
        if (statReachOld==REACH_BEFORE_DISCARD)                    //~va27I~
        {                                                          //~va27I~
//          int actionID=PLS.getReachAction();                     //~va27I~//~va66R~
            int actionID=PLS.getReachAction(Pplayer);              //~va66I~
            if (actionID==GCM_REACH||actionID==GCM_REACH_OPEN)     //~va27I~
            {                                                      //~va66I~
//              if (swCheckReach)                                  //~va27R~//~va66R~
                if (swCheckReach && Pplayer==PLAYER_YOU)	//no chk for robot//~va66I~
                {                                                  //~va27R~
                    if (!AG.aUAReachChk.chkReach(PLAYER_YOU,PtdDiscard))//~va27R~
                    {                                              //~va27R~
                        GMsg.drawMsgbar(R.string.Err_ReachNoten);  //~va27R~
//                      AG.aUserAction.updateButtonStatusReach(GCM_FORCE_REACH_ENABLE);//~va28R~
                        return false;                              //~va27R~
                    }                                              //~va27R~
                }                                                  //~va27R~
            }                                                      //~va66I~
        }                                                          //~va27I~
        if (Dump.Y) Dump.println("UADiscard.chkTenpai player="+Pplayer+",rc="+rc+",statReachOld="+statReachOld+",swChkReach="+swCheckReach);    //~va27I~//~va66R~
        return rc;                                                 //~va27I~
    }                                                              //~va27I~
	//*************************************************************************//~v@@@I~
    public boolean discard(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@@R~
    {                                                              //~v@@@I~
    	boolean swKan;                                             //~v@@@I~
    	boolean swOpenReach=false;                                       //~v@@6I~
    	boolean swReach=false;                                     //~v@@6I~
        int playerLastDiscarded,eswn;                              //~v@@@I~
        TileData td;                                               //~v@@@I~
        int statReachOld,statReachNew;                             //~v@@6I~
    //***********************                                      //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.discard swServer="+PswServer+",swReceived="+PswReceived+",player="+Pplayer);//~v@@@R~
        eraseRiverTaken();                                         //~v@@@I~
        if (PswServer)                                             //~v@@@I~
        {                                                          //~v@@@I~
        	swKan=PLS.isLastActionIsKan();                         //~v@@@R~
				if (!PswReceived)                                   //~v@@@I~
                	td=infoSelectedTD;                             //~v@@@I~
                else                                               //~v@@@I~
		        	td=new TileData(true/*swEswnToPlayer*/,PintParm,PARMPOS_TD);          //~v@@@I~
                                                                   //~va66I~
			statReachOld=PLS.getReachStatus(Pplayer);              //~v@@6I~
            PLS.discard(Pplayer,td);	//shift currentPlayer      //~v@@@R~
			statReachNew=PLS.getReachStatus(Pplayer);              //~v@@6I~
            if (td.isDiscarded())                                  //~v@@@I~
            {                                                      //~v@@@I~
            	playerLastDiscarded=PLS.playerLastDiscarded;  //to be erased at tumo//~v@@@R~
    			eswn=Accounts.playerToEswn(playerLastDiscarded);//~v@@@I~
        	}                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
            	playerLastDiscarded=-1;                            //~v@@@I~
                eswn=-1;                                           //~v@@@I~
            }                                                      //~v@@@I~
	        UADL.resetWait(Pplayer);   //swith to next player after delay a moment//~v@@6I~//~9B28R~
        	if (Dump.Y) Dump.println("UADiscard.discard player="+Pplayer+",lastdiscardedplayer="+playerLastDiscarded+",eswn="+eswn+",tile type="+td.type+",num="+td.number);//~v@@@R~
//      	msgDataToClient=makeMsgDataToClient(Pplayer,td,swKan?1:0,playerDiscarded);//~v@@@I~
        	UA.msgDataToClient=makeMsgDataToClient(Pplayer,td,swKan?1:0,eswn);//~v@@@R~
            swReach=(statReachOld==REACH_BEFORE_DISCARD && statReachNew==REACH_DONE);//~v@@6R~
            swOpenReach=swReach && PLS.isOpenReach(Pplayer);       //~v@@6I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//          playerLastDiscarded=PLS.getLastDiscardedPlayer();      //~v@@@R~
//      	PLS.saveDiscarded(Pplayer);	//save on client data,discardCtr and Player//~v@@@R~
	        swKan=PintParm[PARMPOS_SWKAN]!=0;                      //~v@@@I~
//            eswn=PintParm[PARMPOS_PLAYER2];                      //~v@@@I~
//            playerLastDiscarded=accounts.eswnToPlayer(eswn);     //~v@@@I~
			if (!PswReceived)                                       //~v@@@I~
                td=infoSelectedTD;                                 //~v@@@I~
            else                                                   //~v@@@I~
        		td=new TileData(true/*swEswnToPlayer*/,PintParm,PARMPOS_TD);              //~v@@@I~
//          PLS.discard(Pplayer,td);                               //~v@@@R~
    		if (Pplayer==PLAYER_YOU)                               //~v@@@R~
            {                                                      //~v@@@I~
//              PLS.discard(false/*PswLight*/,Pplayer,td);         //~v@@@R~
//              PLS.discard(true/*PswLight*/,Pplayer,td);          //~v@@@R~
                PLS.discard(Pplayer,td);                           //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
                PLS.discardOtherOnClient(true/*swLight*/,true/*swShadow*/,Pplayer,td);//~v@@@I~
			if (PswReceived)                                       //~v@@6I~
	        	UADL.resetWait(Pplayer);   //swith to next player after delay a moment//~v@@6I~//~9B28R~
//          PLS.setNextPlayer(Pplayer,true/*swShadow*/);           //~v@@@R~
        }                                                          //~v@@@I~
//  	    setDiscardedPlayer(td);                                //~v@@@I~//~v@@6R~
//          playerLastDiscarded=PLS.getLastDiscardedPlayer();      //~v@@@I~
	        hands.discard(Pplayer,td);                             //~v@@@I~
//  	    river.drawDiscarded();                                 //~v@@@I~
//  	    river.drawDiscarded(Pplayer,td,playerLastDiscarded);   //~v@@@R~
    	    river.drawDiscarded(Pplayer,td);                       //~v@@@I~
    	    stock.discard(Pplayer);                                //~v@@6I~
//      if (Dump.Y) Dump.println("UADiscard.discard lastDiscardedPlayer="+playerLastDiscarded);//~v@@@R~
//      if (playerLastDiscarded>=0)                                //~v@@@R~
//      {                                                          //~v@@@R~
//      	                                                       //~v@@@R~
//          td=PLS.getLastDiscarded();                             //~v@@@R~
//      	if (Dump.Y) Dump.println("UADiscard.discard lastDiscarded tile type="+td.type+",num="+td.number+",flag=x"+Integer.toHexString(td.flag)+",player="+td.player+",eswn="+td.eswn);//~v@@@R~
//          td.player=playerLastDiscarded;                         //~v@@@R~
//          td.eswn=Accounts.playerToEswn(playerLastDiscarded);	   //~v@@@R~
//      	if (Dump.Y) Dump.println("UADiscard.discard lastDiscarded tile type="+td.type+",num="+td.number+",flag=x"+Integer.toHexString(td.flag)+",player="+td.player+",eswn="+td.eswn);//~v@@@R~
//            TileData tdDiscarded=river.eraseTaken();    //erased lastdiscarded//~v@@@I~
//            if (tdDiscarded!=null)  //erased lastdiscarded       //~v@@@I~
//                PLS.takenDiscarded(tdDiscarded.player); //erased lastdiscarded//~v@@@R~
//      }                                                          //~v@@@R~
        if (PswServer)                                             //~v@@@I~
        {                                                          //~va70I~
          if (AG.aGC.getStatusPlayAlone()==GCM_RON)  //PLS.discard ==>RACall.otherDiscard, it may highlighten Ron button and set statusRon//~va70R~
          {                                                        //~va70I~
			if (Dump.Y) Dump.println("UADiscard.discard already Notified RON");//~va78I~
//        	tdPlayAlone=td;                                        //~va70R~
          	;                                                      //~va70I~
          }                                                        //~va70I~
          else                                                     //~va70I~
        	postNextPlayerPonKan(Pplayer,td);	//swith to next player after delay a moment//~v@@@I~//~v@@6R~
        }                                                          //~va70I~
        UADL.setRonable(true);     	//for dup ron availability     //~9B28I~
        if (swReach)                                               //~v@@6I~
        {                                                          //~9C01I~
	        UAReach.postOpenReach(Pplayer,swOpenReach);            //~v@@6R~
// 			Sound.play(SOUND_REACH,false/*not change to beep when beeponly option is on*/);//~9C01I~//~9C03R~
   			Sound.play(SOUNDID_REACH,false/*not change to beep when beeponly option is on*/);//~9C03I~
        }                                                          //~9C01I~
//  	Sound.play(SOUND_DISCARD,false/*not change to beep when beeponly option is on*/);//~@@@2I~//~9C01I~//~9C03R~
    	Sound.play(SOUNDID_DISCARD,false/*not change to beep when beeponly option is on*/);//~9C03I~
        if (TestOption.getTimingBTIOErr()==TestOption.BTIOE_AFTER_DISCARD)//~v@@6I~
          	TestOption.disableBT();                                //~v@@6R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*erase taken tile on river                                   //~v@@@I~
	//*************************************************************************//~v@@@I~
    private void eraseRiverTaken()                                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.eraseRiverTaken");     //~v@@@I~
//      if (!OperationSetting.isRiverEraseTaken())                 //~v@@@I~//~v@@6R~
        if (!PrefSetting.isDeleteRiverTileTaken())                 //~v@@6I~
        	return;                                                //~v@@@I~
	    TileData tdDiscarded=river.eraseTaken();	//erased lastdiscarded//~v@@@I~
	    if (tdDiscarded!=null)	//erased lastdiscarded             //~v@@@I~
	    	PLS.takenDiscarded(tdDiscarded.player);	//erased lastdiscarded//~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*On Server at discard, set pon/kan/chii available some seconds later                  //~v@@@I~//~v@@6R~
	//*************************************************************************//~v@@@I~
    public void postNextPlayerPonKan(int Pplayer,TileData Ptd)          //~v@@@I~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.postNextPlayerPonKan player="+Pplayer+",td="+Ptd.toString());//~v@@@R~//~v@@6R~
//      int player=PLS.nextPlayer(Pplayer);//send to next player   //~v@@@R~//~v@@6R~
        int player=Pplayer;                                        //~v@@6I~
//      UA.UADL.postDelayed(delayPonKan,GCM_NEXT_PLAYER_PONKAN,player,Ptd);//~v@@@R~//~v@@6R~
//      UA.UADL.postDelayed(delayTake,GCM_NEXT_PLAYER,player,Ptd); //~v@@6R~
//      UA.UADL.postDelayedDiscard(player,Ptd);                    //~v@@6R~
//      UA.UADL.postDelayedPonKan(player,Ptd);                     //~v@@6I~//~va70R~
        UA.UADL.postDelayedPonKan(player);                         //~va70I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	// server;from UADelayed.delayedNextPlayer thru GVH,UserAction after pon/kan/chii time delayed//~v@@6R~
	// client:actionReceived-actionOnClient                        //~v@@6I~
	//*************************************************************************//~v@@@I~
    public boolean nextPlayer(boolean PswServer,int Pplayer/*nextPlayer*/)       //~v@@@I~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.nextPlayer swServer="+PswServer+",player="+Pplayer);//~v@@@I~//~v@@6R~
        if (AG.aGC.getStatusPlayAlone()!=0)  //PLS.discard ==>RACall.otherDiscard, it may highlighten Ron button and set statusRon//~va78I~
        {                                                          //~va78I~
	        if (Dump.Y) Dump.println("UADiscard.nextPlayer@@@@ ignore next player by statusplayalone");//~va78I~
    		UA.setNoMsgToServer();                                 //~va78I~
        	return true;                                           //~va78I~
        }                                                          //~va78I~
        if (PswServer)                                             //~v@@6I~
	    	UA.msgDataToClient=UserAction.makeMsgDataToClient(Pplayer);//~v@@6I~
        else                                                       //~v@@6I~
    		UA.setNoMsgToServer();                                 //~v@@6I~
        if (AG.aTiles.chkLast())                                   //~v@@6I~
        	return true;                                           //~v@@6I~
        TileData td=PLS.getLastDiscarded();                        //~v@@6I~
        td.setLock(false);                                         //~v@@6I~
//      boolean swShadow=!accounts.isDrawThisDevice(Pplayer);      //~v@@@I~
//      players.setNextPlayer(Pplayer,swShadow);                   //~v@@@I~
//      players.setNextPlayer(Pplayer);                            //~v@@@I~
    	int player=PLS.prevPlayer(Pplayer);                    //~v@@@I~
		boolean swShadow=Pplayer!=PLAYER_YOU;                      //~v@@@I~
//      PLS.setNextPlayer(player,swShadow);	//color of waite to take//~v@@@R~
	    AG.aDiceBox.drawLightDiscardTimeout(player,swShadow);      //~v@@@I~
	    river.drawFrameDiscardedTile(GCM_NEXT_PLAYER);	//draw discarded tile frame//~v@@6I~//~9B23R~
//        Robot r=Accounts.getRobotPlayer(Pplayer)                 //~v@@@I~
//        if (r!=null)                                             //~v@@@I~
//        {                                                        //~v@@@I~
//            if (Dump.Y) Dump.println("UserAction.nextPlayer is robot");//~v@@@I~
//        }                                                        //~v@@@I~
//      if (PswServer)                                             //~v@@6R~
//      	UA.msgDataToClient=UserAction.makeMsgDataToClient(Pplayer);//~v@@@I~//~v@@6R~
//      else                                                       //~v@@6R~
//  		UA.setNoMsgToServer();                                 //~v@@6R~
        if ((TestOption.option & TestOption.TO_TAKEDISCARD)!=0)    //~v@@6I~
        {                                                          //~v@@6I~
//  	        GameViewHandler.sendMsg(GCM_TAKE,null);            //~v@@6R~
			if (Pplayer==PLAYER_YOU)                               //~v@@6I~
	    	    UADelayed.postDelayedActionMsg(100,GCM_TAKE,null); //~v@@6R~
        }                                                          //~v@@6I~
        setTimeout(PswServer,Pplayer/*nextPlayer*/);    //timeout to take by nextplayer//~v@@6R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@6I~
	//*Server and Client                                           //~9B28I~
	//*************************************************************************//~9B28I~
    public boolean nextPlayerPonKan(boolean PswServer,int Pplayer) //~v@@6I~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UADiscard.nextPlayerPonKan swServer="+PswServer+",player="+Pplayer);//~v@@6R~
        if (PswServer)                                             //~v@@6I~
	    	UA.msgDataToClient=UserAction.makeMsgDataToClient(Pplayer);//~v@@6I~
        else                                                       //~v@@6I~
    		UA.setNoMsgToServer();                                 //~v@@6I~
//      if (AG.aTiles.chkLast())                                   //~v@@6I~//~va60R~
//      	return true;                                           //~v@@6I~//~va60R~
        TileData td=PLS.getLastDiscarded();                        //~v@@6I~
//      td.setLockPonKan(false);                                   //~v@@6I~//~va70R~
	    AG.aDiceBox.drawLightDiscardRonTimeout(Pplayer);            //~v@@6I~//~9B23I~
	    river.drawFrameDiscardedTile(GCM_NEXT_PLAYER_PONKAN);	//draw discarded tile frame//~v@@6R~//~9B23I~
//      if (PswServer)                                             //~v@@6R~
//      	UA.msgDataToClient=UserAction.makeMsgDataToClient(Pplayer);//~v@@6R~
//      else                                                       //~v@@6R~
//  		UA.setNoMsgToServer();                                 //~v@@6R~
//      UADL.setRonable(false);     	//for dup ron availability //~9B28I~//~va70R~
		UA.UADL.timeoutPonKan(PswServer);	//for client,save delayedAction//~v@@6I~
        td.setLockPonKan(false);	//after Robot ron chk for playalone mode//~va70I~
        UADL.setRonable(false);     //for dup ron availability, after robot ron chk for playalone mode//~va70I~
        if (PswServer)                                             //~va60R~
		    AG.aRoundStat.timeoutPonKan(Pplayer,td);               //~va60R~
        return true;                                               //~v@@6I~
    }                                                              //~v@@6I~
//    //*************************************************************************//~v@@@I~//~v@@6R~
//    public static void setDiscardedPlayer(TileData Ptd)           //~v@@@I~//~v@@6R~
//    {                                                              //~v@@@I~//~v@@6R~
//        if (Dump.Y) Dump.println("UADiscard.setDiscardedPlayer in:"+Ptd.toString());//~v@@@R~//~v@@6R~
//        int player=AG.aPlayers.getLastDiscardedPlayer();           //~v@@@I~//~v@@6R~
//        Ptd.player=player;                                         //~v@@@I~//~v@@6R~
//        Ptd.eswn=Accounts.playerToEswn(player);                    //~v@@@I~//~v@@6R~
//        if (Dump.Y) Dump.println("UADiscard.setDiscardedPlayer out:"+Ptd.toString());//~v@@@R~//~v@@6R~
//    }                                                              //~v@@@I~//~v@@6R~
	//*************************************************************************//~v@@6I~//~va70R~
	//*next player take in the time                                //~v@@6I~
	//*public for GC.actionPlayALone                               //~va70I~
	//*************************************************************************//~v@@6I~
//  private void setTimeout(boolean PswServer,int Pplayer/*nextPlayer*/)//~v@@6R~//~va60R~//~va70R~
    public  void setTimeout(boolean PswServer,int Pplayer/*nextPlayer*/)//~va70I~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UADiscard.setTimeout swServer="+PswServer+",player="+Pplayer+",ctrTakenAll="+PLS.ctrTakenAll);//~v@@6R~
        if (Dump.Y) Dump.println("UADiscard.setTimeout swTrainingMode="+AG.swTrainingMode+",swManualRobot="+swManualRobot);//~va66I~
//      if (timeout==0)                                            //~v@@6R~
//      	return;                                                //~v@@6R~
//      if (Pplayer!=PLAYER_YOU)                                   //~v@@6R~
//      	return;                                                //~v@@6R~
//      if ((TestOption.option & TestOption.TO_TAKEDISCARD)!=0)    //~v@@6R~
//      	return;                                                //~v@@6R~
//      if (PswServer && !AG.aTiles.chkNextTile())                 //~v@@6R~
//      	return;                                                //~v@@6R~
//  	if (!UA.UADL.isWaitableAuto(Pplayer))                      //~v@@6I~
//      	return;                                                //~v@@6I~
        if (swManualRobot) //in training mode                      //~va66R~
        {                                                          //~va66R~
            if (Dump.Y) Dump.println("UADiscard.setTimeout manualRobot");//~va66R~//~va78R~
            if (PswServer)                                         //~va66I~
            {                                                      //~va66I~
	        	if (AG.aAccounts.isDummyPlayer(Pplayer))           //~va66I~
                {                                                  //~va77I~
    	        	Robot.nextPlayerManual(Pplayer);//issue chii   //~va66R~
        		    return;                                        //~va77I~
                }                                                  //~va77I~
//  			if (AG.aTiles.chkLast())                                 //~9225I~//~va66R~
//  		        UA.UADL.postDelayedAutoTake(PswServer,Pplayer,PLS.ctrTakenAll);	//to show DrawnReqDlgLast//~va66R~
            }                                                      //~va66I~
//          return;                                                //~va66R~//~va77R~
        }                                                          //~va66R~
        UA.UADL.postDelayedAutoTake(PswServer,Pplayer,PLS.ctrTakenAll);//~v@@6R~
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@6I~
    public boolean isActiveAutoTakeTimeout(int Pplayer,int PctrTakenAll)//~v@@6I~
    {                                                              //~v@@6I~
        boolean rc=(Pplayer==Players.nextPlayer(PLS.getCurrentPlayer()) && PctrTakenAll==PLS.ctrTakenAll && AG.aUserAction.currentActionID==GCM_DISCARD);//~v@@6I~
        if (Dump.Y) Dump.println("UADiscard.isActiveAutoTakeTimeout rc="+rc+",player="+Pplayer+",currentActionID="+AG.aUserAction.currentActionID+",PctrTakenAll="+PctrTakenAll+",ctrTakenAll="+PLS.ctrTakenAll);//~v@@6I~
        return rc;
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@6I~
    public void autoTakeTimeout(int Pplayer,int PctrTakenAll)      //~v@@6R~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UADiscard.autoTakeTimeout player="+Pplayer+",currentActionID="+AG.aUserAction.currentActionID+",PctrTakenAll="+PctrTakenAll+",ctrTakenAll="+PLS.ctrTakenAll);//~v@@6R~
//      if (Pplayer==Players.nextPlayer(PLS.getCurrentPlayer()) && PctrTakenAll==PLS.ctrTakenAll && AG.aUserAction.currentActionID==GCM_DISCARD)//~v@@6R~
		if (Pplayer==PLAYER_YOU)                                   //~v@@6I~
        {                                                          //~va75I~
          	if (AG.swPlayAloneNotify)                              //~va75I~
    			if (autoTakeTimeoutPlayAloneNotify())               //~va75I~
            		return;                                        //~va75I~
	    	AG.aGC.sendMsg(GCM_TAKE,Pplayer);	//simulate take button//~v@@6R~
        }                                                          //~va75I~
        else                                                       //~v@@6R~
        {                                                          //~v@@6I~
        	if (AG.aAccounts.isDummyPlayer(Pplayer))               //~v@@6R~
            	Robot.autoTakeTimeout(Pplayer);                    //~v@@6I~
            else                                                   //~v@@6I~
//      	if (Dump.Y) Dump.println("UADiscard.discardTakeTimeout Not sent GCM_TAKE");//~v@@6R~
			UA.UADL.sendMsgEmulatedToClient(GCM_TAKE,Pplayer);	//simulate take button//~v@@6R~
        }                                                          //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~va75I~
    private boolean autoTakeTimeoutPlayAloneNotify()                //~va75I~
    {                                                              //~va75I~
    	boolean rc=false;                                          //~va75I~
        if (Dump.Y) Dump.println("UADiscard.autoTakeTimeoutPlayAloneNotify");//~va75I~
        int eswnPlayer=Accounts.playerToEswn(PLAYER_YOU);          //~va75R~
        int playerDiscarded= Players.prevPlayer(PLAYER_YOU);       //~va75I~
        int eswnDiscarded=Accounts.playerToEswn(playerDiscarded);  //~va75I~
        TileData tdDiscarded=AG.aPlayers.getLastDiscarded();       //~va75I~
	    rc=AG.aRACall.autoTakeTimeoutPlayAloneNotify(eswnPlayer,playerDiscarded,eswnDiscarded,tdDiscarded);//~va75R~
        return rc;                                                 //~va75I~
    }                                                              //~va75I~
	//*************************************************************************//~v@@6I~
	//*on Client,by GCM_WAITOFF at waitingAutoTake                 //~v@@6I~
	//*************************************************************************//~v@@6I~
    public boolean autoTakeTimeoutClient(int Pplayer)              //~v@@6R~
    {                                                              //~v@@6I~
    	boolean rc=false;                                          //~v@@6I~
        if (Dump.Y) Dump.println("UADiscard.autoTakeTimeoutClient player="+Pplayer);//~v@@6R~
		if (Pplayer==PLAYER_YOU)                                   //~v@@6I~
        {                                                          //~v@@6I~
	    	AG.aGC.sendMsg(GCM_TAKE,PLAYER_YOU);	//simulate take button//~v@@6I~
            rc=true;                                               //~v@@6I~
        }                                                          //~v@@6I~
        if (Dump.Y) Dump.println("UADiscard.autoTakeTimeoutClient rc="+rc);//~v@@6I~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~va15I~
	//*rc:true prohibit discard                                    //~va15I~
	//*************************************************************************//~va15I~
    private boolean isSameMeld(TileData PtileSelected)             //~va15I~
    {                                                              //~va15I~
    	boolean rc=false;                                          //~va15I~
	    if (Dump.Y) Dump.println("UADiscard.isSameMeld Discard tile="+TileData.toString(PtileSelected));//~va15I~
//      int typeSameMeld= RuleSetting.getSameMeld();                //~va15I~//~va60R~
        if (typeSameMeld==EATCHANGE_ALLOK)                         //~va15I~
        {                                                          //~va15I~
	        if (Dump.Y) Dump.println("UADiscard.isSameMeld false by RuleSetting");//~va15I~
        	return false;                                          //~va15I~
        }                                                          //~va15I~
    	int action=PLS.getLastActionID(PLAYER_YOU);                //~va15I~
        if (action!=GCM_PON && action!=GCM_CHII)                   //~va15I~
        {	                                                       //~va15I~
	        if (Dump.Y) Dump.println("UADiscard.isSameMeld false by lastAction="+action);//~va15I~
            return false;                                          //~va15I~
        }                                                          //~va15I~
        TileData[][] earth=PLS.getEarth(PLAYER_YOU);               //~va15I~
	    if (Dump.Y) Dump.println("UADiscard.isSameMeld earth="+TileData.toString(earth));//~va15I~
		int ctrPair=PLS.getCtrPair(PLAYER_YOU);                        //~va15I~
        if (ctrPair==0)                                            //~va15I~
        {                                                          //~va15I~
	        if (Dump.Y) Dump.println("UADiscard.isSameMeld @@@@err ctrPair=0");//~va15I~
        	return false;                                          //~va15I~
        }                                                          //~va15I~
        TileData[] lastPair=earth[ctrPair-1];                      //~va15I~
        TileData tdRiver=null;                                     //~va15I~
        int posRiver=0;                                            //~va15I~
        for (TileData td:lastPair)                                 //~va15I~
        {                                                          //~va15I~
	        if ((td.flag & TDF_TAKEN_RIVER)!=0)                    //~va15I~
            {                                                      //~va15I~
            	tdRiver=td;	                                       //~va15I~
                break;                                             //~va15I~
            }                                                      //~va15I~
            posRiver++;                                            //~va15I~
        }                                                          //~va15I~
        if (tdRiver==null)                                         //~va15I~
        {                                                          //~va15I~
	        if (Dump.Y) Dump.println("UADiscard.isSameMeld @@@@err no River tile found");//~va15I~
        	return false;                                          //~va15I~
        }                                                          //~va15I~
        if (PtileSelected.type==tdRiver.type && PtileSelected.number==tdRiver.number)//~va15I~
        {	                                                       //~va15I~
	        if (Dump.Y) Dump.println("UADiscard.isSameMeld same tile");//~va15I~
            rc=true;                                               //~va15I~
        }                                                          //~va15I~
        else                                                       //~va15I~
        if (typeSameMeld==EATCHANGE_EXCEPTIT)	//1:err only itself//~va15I~
        {                                                          //~va15I~
	        if (Dump.Y) Dump.println("UADiscard.isSameMeld OK by prohibit itself only");//~va15I~
            rc=false;                                              //~va15I~
        }                                                          //~va15I~
        else                                                       //~va15I~
        if ((tdRiver.flag & TDF_CHII)!=0)                          //~va15I~
        {                                                          //~va15I~
          if (PtileSelected.type==tdRiver.type)                    //+va8yI~
          {                                                        //+va8yI~
        	if (posRiver==0)	//left side eat                    //~va15I~
            {                                                      //~va15I~
            	if (PtileSelected.number==tdRiver.number+3)        //~va15I~
                {                                                  //~va15I~
			        if (Dump.Y) Dump.println("UADiscard.isSameMeld err by prohibit right");//~va15I~
        		    rc=true;                                       //~va15I~
                }                                                  //~va15I~
            }                                                      //~va15I~
            else                                                   //~va15I~
        	if (posRiver==2)	//right side eat                   //~va15I~
            {                                                      //~va15I~
            	if (PtileSelected.number==tdRiver.number-3)        //~va15I~
                {                                                  //~va15I~
			        if (Dump.Y) Dump.println("UADiscard.isSameMeld err by prohibit left");//~va15I~
        		    rc=true;                                       //~va15I~
                }                                                  //~va15I~
            }                                                      //~va15I~
          }                                                        //+va8yI~
        }                                                          //~va15I~
    	if (rc)                                                    //~va15I~
        	GC.actionError(0,PLAYER_YOU,R.string.AE_SameMeld);         //~va15I~
        if (Dump.Y) Dump.println("UADiscard.isSameMeld rc="+rc);   //~va15I~
        return rc;                                                 //~va15I~
    }                                                              //~va15I~
	//*************************************************************************//~va60I~
	//*from RADSmart.setNonDiscardable                             //~va60I~
	//*rc:ctr non discardable tile                                 //~va60I~
	//*************************************************************************//~va60I~
    public int getSameMeldTile(int Pplayer,int[] PposNotDiscardable)//~va60I~
    {                                                              //~va60I~
    	int rc;                                                //~va60I~
	    TileData tdDiscarded=PLS.getLastDiscarded();                //~va60I~
	    if (Dump.Y) Dump.println("UADiscard.getSameMeldTile player="+Pplayer+",lastDiscarded="+TileData.toString(tdDiscarded));//~va60I~
        if (typeSameMeld==EATCHANGE_ALLOK)                         //~va60I~
        {                                                          //~va60I~
	        if (Dump.Y) Dump.println("UADiscard.getSameMeldTile false by RuleSetting");//~va60I~
        	return 0;                                              //~va60I~
        }                                                          //~va60I~
        int action=PLS.getLastActionID(Pplayer);                   //~va60I~
        PposNotDiscardable[0]=tdDiscarded.type*PIECE_NUMBERCTR+tdDiscarded.number;//~va60I~
        if (typeSameMeld==EATCHANGE_EXCEPTIT || action==GCM_PON)   //~va60I~
        {                                                          //~va60I~
	        if (Dump.Y) Dump.println("UADiscard.isSameMeld OK by prohibit itself only");//~va60I~
        	return 1;                                              //~va60I~
        }                                                          //~va60I~
        TileData[][] earth=PLS.getEarth(Pplayer);                  //~va60I~
	    if (Dump.Y) Dump.println("UADiscard.getSameMeldTile earth="+TileData.toString(earth));//~va60I~
		int ctrPair=PLS.getCtrPair(Pplayer);                       //~va60I~
        if (ctrPair==0)                                            //~va60I~
        {                                                          //~va60I~
	        if (Dump.Y) Dump.println("UADiscard.getSameMeld @@@@err ctrPair=0");//~va60I~
        	return 1;                                              //~va60I~
        }                                                          //~va60I~
        TileData[] lastPair=earth[ctrPair-1];                      //~va60I~
        TileData tdRiver=null;                                     //~va60I~
        int posRiver=0;                                            //~va60I~
        for (TileData td:lastPair)                                 //~va60I~
        {                                                          //~va60I~
	        if ((td.flag & TDF_TAKEN_RIVER)!=0)                    //~va60I~
            {                                                      //~va60I~
            	tdRiver=td;                                        //~va60I~
                break;                                             //~va60I~
            }                                                      //~va60I~
            posRiver++;                                            //~va60I~
        }                                                          //~va60I~
        if (tdRiver==null                                          //~va60I~
        ||  tdDiscarded.type!=tdRiver.type || tdDiscarded.number!=tdRiver.number//~va60I~
        ||  (tdRiver.flag & TDF_CHII)==0
        )//~va60I~
        {                                                          //~va60I~
	        if (Dump.Y) Dump.println("UADiscard.isSameMeld same tile");//~va60I~
            return 1;                                              //~va60I~
        }                                                          //~va60I~
        rc=1;                                                      //~va60I~
        if (posRiver==0)    //left side eat                        //~va60I~
        {                                                          //~va60I~
            if (tdDiscarded.number<=TN6)                           //~va60I~
            {                                                      //~va60I~
                if (Dump.Y) Dump.println("UADiscard.isSameMeld err by prohibit right");//~va60I~
		        PposNotDiscardable[1]=tdDiscarded.type*PIECE_NUMBERCTR+tdDiscarded.number+3;//~va60I~
                rc++;                                              //~va60I~
            }                                                      //~va60I~
        }                                                          //~va60I~
        else                                                       //~va60I~
        if (posRiver==2)    //right side eat                       //~va60I~
        {                                                          //~va60I~
            if (tdDiscarded.number>=TN4)                           //~va60I~
            {                                                      //~va60I~
                if (Dump.Y) Dump.println("UADiscard.isSameMeld err by prohibit left");//~va60I~
		        PposNotDiscardable[1]=tdDiscarded.type*PIECE_NUMBERCTR+tdDiscarded.number-3;//~va60I~
                rc++;                                              //~va60I~
            }                                                      //~va60I~
        }                                                          //~va60I~
        if (Dump.Y) Dump.println("UADiscard.isSameMeld rc="+rc+",PposNotDiscardable"+ Utils.toStringMax(PposNotDiscardable,rc));//~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
//    //*************************************************************************//~va70R~
//    //*from GC                                                   //~va70R~
//    //*call(Ron/Pon/Kan/Chii) canceled,start timer               //~va70R~
//    //*************************************************************************//~va70R~
//    public void resetCallPlayAlone(int PmsgID)                   //~va70R~
//    {                                                            //~va70R~
//        if (Dump.Y) Dump.println("UADiscard.resetCallPlayAlone nsgID="+PmsgID);//~va70R~
//        switch(PmsgID)                                           //~va70R~
//        {                                                        //~va70R~
//        case GCM_RON:                                            //~va70R~
//            postNextPlayerPonKan(PLAYER_YOU,tdPlayAlone);   //robot's ronability//~va70R~
//            tdPlayAlone=null;                                    //~va70R~
//            break;                                               //~va70R~
//        case GCM_PON:                                            //~va70R~
//            break;                                               //~va70R~
//        case GCM_KAN:                                            //~va70R~
//            break;                                               //~va70R~
//        case GCM_CHII:                                           //~va70R~
//            break;                                               //~va70R~
//        }                                                        //~va70R~
//    }                                                            //~va70R~
}//class                                                           //~v@@@R~
