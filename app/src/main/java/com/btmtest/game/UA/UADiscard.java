//*CID://+va28R~: update#= 602;                                    //+va28R~
//**********************************************************************//~v101I~
//2020/11/04 va28 Delete force reach option, local yaku is all abount patterns, is not ron format.//+va28I~
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
import com.btmtest.utils.sound.Sound;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;                           //~v@@@I~
import static com.btmtest.game.Players.*;
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
        if (Dump.Y) Dump.println("UADiscard init");//~v@@@R~       //~v@@6R~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    private TileData selectTile(int Pplayer)                       //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.selectTile");          //~v@@@R~
        TileData td=PLS.getTileSelected(Pplayer);                  //~v@@@R~
        return td;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public boolean discard(int Pplayer)                            //~v@@@I~
    {                                                              //~v@@@I~
//        if (PLS.isTakeAvailable())                               //~v@@@R~
//        {                                                        //~v@@@I~
//            if (Dump.Y) Dump.println("UserAction.discard Dup Discard or not take 1st");//~v@@@I~
//            return;                                              //~v@@@I~
//        }                                                        //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.discard");             //~v@@@R~
//        if (!PLS.isYourTurn(actionID,Pplayer))                   //~v@@@R~
//            return;                                              //~v@@@I~
        int player=PLS.getCurrentPlayer();                         //~v@@@R~
        TileData td=selectTile(player);                            //~v@@@I~
        if (td==null)                                              //~v@@@I~
        {                                                          //~v@@@I~
        	GC.actionError(0,player,R.string.AE_NoTileSelected);           //~v@@@I~//~v@@6R~
        	return false;                                          //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.discard player="+player+",tile type="+td.type+",num="+td.number);//~v@@@R~
	    TileData tdDiscarded=river.eraseTaken();	//erased lastdiscarded//~v@@@I~
	    if (tdDiscarded!=null)	//erased lastdiscarded             //~v@@@I~
	    	PLS.takenDiscarded(tdDiscarded.player);	//erased lastdiscarded//~v@@@R~
        PLS.discard(player,td);	//shift currentPlayer              //~v@@@R~
        hands.discard(player,td);                                  //~v@@@I~
	    river.drawDiscarded();                                     //~v@@@I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*by button action                                            //~v@@@I~
	//*************************************************************************//~v@@@I~
    public boolean selectInfo(boolean PswServer,int Pplayer)      //~v@@@R~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.selectInfo");         //~v@@@R~//~v@@6R~
        if ((TestOption.option2 & TestOption.TO2_SUSPEND)!=0)                 //~9819I~//~9820I~//~v@@6I~
        {                                                          //~9820I~//~v@@6I~
        	if (Dump.Y) Dump.println("UADiscard.selectInfo TO2_SUSPEND discardedCtr="+AG.aPlayers.getDiscardedCtr(PLAYER_YOU));//~v@@6R~
            if (AG.aPlayers.getDiscardedCtr(PLAYER_YOU)==0)        //~v@@6I~
	            AG.aGC.suspendGame(true/*suspend*/,-1/*useraction*/);//by TestOption //~9820R~//~v@@6I~//~0307R~
        }                                                          //~9820I~//~v@@6I~
        AG.aHandsTouch.enableMultiSelectionMode(false);            //~v@@6I~
        TileData td=selectTile(PLAYER_YOU);                        //~v@@@I~
        if (td==null)                                              //~v@@@I~
        {                                                          //~v@@@I~
        	GC.actionError(0,Pplayer,R.string.AE_NoTileSelected);  //~v@@6R~
        	return false;                                          //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.selectInfo td="+td.type+":"+td.number+":"+td.flag);//~v@@@R~//~v@@6R~
		if (!chkTenpai(td))                                        //~va27I~
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
    private boolean chkTenpai(TileData PtdDiscard)                 //~va27I~
    {                                                              //~va27I~
    	boolean rc=true;                                           //~va27I~
        if (Dump.Y) Dump.println("UADiscard.chkTenpai");           //~va27I~
		int statReachOld=PLS.getReachStatus(PLAYER_YOU);           //~va27I~
        if (statReachOld==REACH_BEFORE_DISCARD)                    //~va27I~
        {                                                          //~va27I~
            int actionID=PLS.getReachAction();                     //~va27I~
            if (actionID==GCM_REACH||actionID==GCM_REACH_OPEN)     //~va27I~
                if (swCheckReach)                                  //~va27R~
                {                                                  //~va27R~
                    if (!AG.aUAReachChk.chkReach(PLAYER_YOU,PtdDiscard))//~va27R~
                    {                                              //~va27R~
                        GMsg.drawMsgbar(R.string.Err_ReachNoten);  //~va27R~
//                      AG.aUserAction.updateButtonStatusReach(GCM_FORCE_REACH_ENABLE);//+va28R~
                        return false;                              //~va27R~
                    }                                              //~va27R~
                }                                                  //~va27R~
        }                                                          //~va27I~
        if (Dump.Y) Dump.println("UADiscard.chkTenpai rc="+rc);    //~va27I~
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
//            if (accounts.isDummyPlayer(Pplayer))                 //~v@@@I~
//                td=Robot.getTaken(Pplayer);                      //~v@@@I~
//            else                                                 //~v@@@I~
//            {                                                    //~v@@@I~
//                td=selectTile(Pplayer);                          //~v@@@I~
//                if (td==null)                                    //~v@@@I~
//                {                                                //~v@@@I~
//                    GC.actionError(0,"No tile Selected");        //~v@@@I~
//                    return false;                                //~v@@@I~
//                }                                                //~v@@@I~
				if (!PswReceived)                                   //~v@@@I~
                	td=infoSelectedTD;                             //~v@@@I~
                else                                               //~v@@@I~
		        	td=new TileData(true/*swEswnToPlayer*/,PintParm,PARMPOS_TD);          //~v@@@I~
//            }                                                    //~v@@@I~
//          PLS.discard(Pplayer,td);	//shift currentPlayer      //~v@@@R~
//          PLS.discard(false/*PswLight*/,Pplayer,td);	//shift currentPlayer//~v@@@R~
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
        	postNextPlayerPonKan(Pplayer,td);	//swith to next player after delay a moment//~v@@@I~//~v@@6R~
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
        UA.UADL.postDelayedPonKan(player,Ptd);                     //~v@@6I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	// server;from UADelayed.delayedNextPlayer thru GVH,UserAction after pon/kan/chii time delayed//~v@@6R~
	// client:actionReceived-actionOnClient                        //~v@@6I~
	//*************************************************************************//~v@@@I~
    public boolean nextPlayer(boolean PswServer,int Pplayer/*nextPlayer*/)       //~v@@@I~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UADiscard.nextPlayer swServer="+PswServer+",player="+Pplayer);//~v@@@I~//~v@@6R~
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
        if (AG.aTiles.chkLast())                                   //~v@@6I~
        	return true;                                           //~v@@6I~
        TileData td=PLS.getLastDiscarded();                        //~v@@6I~
        td.setLockPonKan(false);                                   //~v@@6I~
	    AG.aDiceBox.drawLightDiscardRonTimeout(Pplayer);            //~v@@6I~//~9B23I~
	    river.drawFrameDiscardedTile(GCM_NEXT_PLAYER_PONKAN);	//draw discarded tile frame//~v@@6R~//~9B23I~
//      if (PswServer)                                             //~v@@6R~
//      	UA.msgDataToClient=UserAction.makeMsgDataToClient(Pplayer);//~v@@6R~
//      else                                                       //~v@@6R~
//  		UA.setNoMsgToServer();                                 //~v@@6R~
        UADL.setRonable(false);     	//for dup ron availability //~9B28I~
		UA.UADL.timeoutPonKan(PswServer);	//for client,save delayedAction//~v@@6I~
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
	//*************************************************************************//~v@@6I~
	//*next player take in the time                                //~v@@6I~
	//*************************************************************************//~v@@6I~
    public void setTimeout(boolean PswServer,int Pplayer/*nextPlayer*/)//~v@@6R~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UADiscard.setTimeout swServer="+PswServer+",player="+Pplayer+",ctrTakenAll="+PLS.ctrTakenAll);//~v@@6R~
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
	    	AG.aGC.sendMsg(GCM_TAKE,Pplayer);	//simulate take button//~v@@6R~
        else                                                       //~v@@6R~
        {                                                          //~v@@6I~
        	if (AG.aAccounts.isDummyPlayer(Pplayer))               //~v@@6R~
            	Robot.autoTakeTimeout(Pplayer);                    //~v@@6I~
            else                                                   //~v@@6I~
//      	if (Dump.Y) Dump.println("UADiscard.discardTakeTimeout Not sent GCM_TAKE");//~v@@6R~
			UA.UADL.sendMsgEmulatedToClient(GCM_TAKE,Pplayer);	//simulate take button//~v@@6R~
        }                                                          //~v@@6I~
    }                                                              //~v@@6I~
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
        int typeSameMeld= RuleSetting.getSameMeld();                //~va15I~
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
        }                                                          //~va15I~
    	if (rc)                                                    //~va15I~
        	GC.actionError(0,PLAYER_YOU,R.string.AE_SameMeld);         //~va15I~
        if (Dump.Y) Dump.println("UADiscard.isSameMeld rc="+rc);   //~va15I~
        return rc;                                                 //~va15I~
    }                                                              //~va15I~
}//class                                                           //~v@@@R~
