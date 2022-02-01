//*CID://+vaifR~: update#= 618;                                    //~va66R~//~vaifR~
//**********************************************************************//~v101I~
//2021/12/24 vaig over vaif, basically have not set enable light when positioning skip mode//~vaifI~
//2021/12/24 vaif protect dup touch on position acception          //~vaifI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
                                                                   //~v@@@I~
import android.graphics.Point;
import android.os.Message;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.game.gv.DiceBox;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.game.Status.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.BT.enums.MsgIDConst.*;//~v@@@I~
//**********************************************************************//~v@@@I~
//*touch Light                                                     //~v@@@I~
//*  GVH.touchEvent()-->Action.touchLight()-->ACATouch.touchLight()//~v@@@I~
//*  	case GS_POSITION_ACCEPTING  <-->touchLightPositionAccepting()//~v@@@I~
//*  	case GS_POSITION_ACCEPTED   <-->touchLightPositionMove() (tempStarter touched)//~v@@@R~
//*                                                                //~v@@@I~
//*  	touchLightPositionAccepting()                              //~v@@@I~
//*       <-->sendTouched()                                        //~v@@@M~
//*             if server --(GCM_LIGHT_TOUCHED)-->clientALL        //~v@@@R~
//*             if client --(GCM_LIGHT_TOUCHED)-->server           //~v@@@M~
//*       <-->showPositionTile()                                   //~v@@@I~
//*                                                                //~v@@@I~
//*       (GCM_LIGHT_TOUCHED)                                      //~v@@@I~
//*          -->light_Touched()                                    //~v@@@R~
//*             case GS_POSITION_ACCEPTING <-->light_Touched_Position_Accepting_Msg()//~v@@@R~
//*                                                <-->showPositionTile()//~v@@@R~
//*                                                if server --(GCM_LIGHT_TOUCHED)-->client:light_Touched()//~v@@@R~
//*       <-->showPositionTile()                                   //~v@@@I~
//*             River.setupAccepted()                              //~v@@@I~
//*             if next==1stPicker <-->positionAcceptedAll()       //~v@@@R~
//*             	                     if server   <-->sendMoveData() --(GCM_SETUP_MOVE)-->client ALL//~v@@@R~
//*             else <-->enableLight(next);                        //~v@@@I~
//*                                                                //~v@@@I~
//*      (GCM_SETUP_MOVE)                                          //~v@@@I~
//*           setup_Move()                                         //~v@@@I~
//*               if server                                        //~v@@@I~
//*                 	<-->positionAcceptedAll()  (send GCM_SETUP_MOVE to client)//~v@@@R~
//*               if client                                        //~v@@@I~
//*               	  <-->parsePosMemberData()                     //~v@@@I~
//*                   --(GCM_SETUP_MOVED)-->server                 //~v@@@R~
//*      (GCM_SETUP_MOVED)                                         //~v@@@I~
//*           setup_Moved()                                        //~v@@@I~
//*               if resposneAll <-->showReadyToMove() :status=ACCEPTED//~v@@@R~
//*                                  sendStatusChange(ACCEPTED)    //~v@@@I~
//*************                                                    //~v@@@I~
//**                                                               //~v@@@I~
//*  	touchLightPositionMove() (tempstarter touched)             //~v@@@R~
//*       if server <-->Account.PositionMove()                     //~v@@@R~
//*       <-->sendTouched()                                        //~v@@@I~
//*             if server --(GCM_LIGHT_TOUCHED)-->client           //~v@@@I~
//*             if client --(GCM_LIGHT_TOUCHED)-->server           //~v@@@I~
//*                -->light_Touched()                              //~v@@@I~
//*                    case GS_POSITION_ACCEPTED <-->light_Touched_Position_Accepted_Msg()//~v@@@R~
//*                                                         <-->Account.positionMoved()//~v@@@R~
//*                                                         if server//~v@@@R~
//*         													if (ResponsedAll)<-->showReadyToStartGame();//~v@@@I~
//*                                                             else --(GCM_LIGHT_TOUCHED)-->client//~v@@@I~
//*                                                                       -->light_Touched()//~v@@@R~
//*                                                         if clent  <-->showReadyToStartGame()//~v@@@R~
//*                                                                   -->(GCM_LIGHT_TOUCHED_RESP)-->server//~v@@@R~
//*     (GCM_LIGHT_TOUCHED_RESP)                                   //~v@@@R~
//*        case GS_POSITION_ACCEPTED <-->light_Touched_Position_Accepted_Resp_Msg(Psender)//~v@@@I~
//*                                        if (responsedAll) <-->showReadyToStartGame();//~v@@@R~
//*                                                          --(GCM__STATUS_CHANGE)-->client//~v@@@I~
//**********************************************************************//~v@@@I~
public class ACATouch                                              //~v@@@R~
{                                                                  //~0914I~
	private Accounts accounts;                                     //~v@@@I~
	private ACAction acaction;                                     //~v@@@I~
	private int ctrResponsed;                                      //~v@@@R~
	private int starter;                                           //~v@@@I~
	private boolean isServer;                                      //~v@@@I~
	private boolean swSkipPositioning;                              //~vaifI~
//*****************************                                    //~v@@@I~
	public ACATouch(ACAction PacAction,Accounts Paccounts)         //~v@@@R~
    {                                                              //~0914I~
    	AG.aACATouch=this;                                         //~v@@@I~
        acaction=PacAction;                                        //~v@@@R~
        accounts=Paccounts;                                        //~v@@@I~
        isServer=accounts.isServer();                               //~v@@@I~
      	swSkipPositioning=RuleSettingOperation.isPositioningSkip();//~vaifI~
        if (Dump.Y) Dump.println("ACATouch.Constructor isServer="+isServer+",swSkipPositioning="+swSkipPositioning);         //~1506R~//~@@@@R~//~v@@@R~//~vaifR~
    }                                                              //~0914I~//~v@@@R~
//***************************************************************************//~v@@@I~
    public void resetCtrResponsed()                                //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.resetCtrResponsed");    //~v@@@R~
    	ctrResponsed=0;                                            //~v@@@R~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
    public int addCtrResponsed()                                   //~v@@@R~
    {                                                              //~v@@@I~
    	ctrResponsed++;                                            //~v@@@R~
        if (Dump.Y) Dump.println("ACATouch.addCtrAccepted ctr="+ctrResponsed);//~v@@@R~
        return ctrResponsed;                                       //~v@@@R~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
    public boolean isCtrResponsedAll(boolean PswActiveOnly)        //~v@@@R~
    {                                                              //~v@@@I~
        boolean rc;                                                //~v@@@I~
    	if (PswActiveOnly)                                        //~v@@@I~
	        rc=ctrResponsed==accounts.activeMembers;               //~v@@@R~
        else                                                       //~v@@@I~
	        rc=ctrResponsed==PLAYERS;                              //~v@@@R~
        if (Dump.Y) Dump.println("ACATouch.isCtrResponsedAll swActiveOnly="+PswActiveOnly+",ctr="+ctrResponsed+",activeCtr="+accounts.activeMembers+",rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//*on each player by touchEvent                                    //~v@@@R~
//***************************************************************************//~v@@@I~
	public void touchLight(int Pplayer)                            //~v@@@I~
    {                                                              //~v@@@I~
    	boolean swAll;                                             //~v@@@I~
    //********************                                         //~v@@@I~
        int stat=Status.getGameStatus();                           //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.touchLight player="+Pplayer+",status="+stat);//~v@@@I~
        switch(stat)                                               //~v@@@I~
        {                                                          //~v@@@I~
        case GS_POSITION_ACCEPTING:                                //~v@@@I~
        	touchLightPositionAccepting(Pplayer);                  //~v@@@R~
            break;                                                 //~v@@@I~
        case GS_POSITION_ACCEPTED:                                 //~v@@@I~
        	touchLightPositionMove();                              //~v@@@R~
            break;                                                 //~v@@@I~
        case GS_COMPLETION_ACCEPTING:                              //~v@@@I~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
	}                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//*on each player by GCM_LIGHT_TOUCHED msg                         //~v@@@I~
//***************************************************************************//~v@@@I~
	public void light_Touched(int Psender,int Pplayer/*!=sender if dummy exist*/)//~v@@@R~
    {                                                              //~v@@@I~
        int stat=Status.getGameStatus();                           //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.light_touched sender="+Psender+",player="+Pplayer+",status="+stat);//~v@@@I~
        resetLight(Pplayer);                                       //~v@@@I~
//        if ((TestOption.option & TestOption.TO_POSITIONING)!=0) //TODO//~v@@@R~
//        {                                                        //~v@@@R~
//            if (Status.getGameStatus()==GS_POSITIONING)          //~v@@@R~
//                Status.setGameStatus(GS_POSITION_ACCEPTING);     //~v@@@R~
//        }                                                        //~v@@@R~
        switch(stat)                                               //~v@@@I~
        {                                                          //~v@@@I~
        case GS_POSITION_ACCEPTING:                                //~v@@@I~
        	light_Touched_Position_Accepting_Msg(Psender,Pplayer); //~v@@@R~
            break;                                                 //~v@@@I~
        case GS_POSITION_ACCEPTED:                                 //~v@@@I~
//            touchLightAtPositionAcceptedAllReceived(Psender,Pplayer);//~v@@@R~
            light_Touched_Position_Accepted_Msg(Psender,Pplayer);  //~v@@@R~
            break;                                                 //~v@@@I~
        case GS_COMPLETION_ACCEPTING:                              //~v@@@I~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//*on Server by GCM_LIGHT_ENABLE msg                               //~v@@@R~
//***************************************************************************//~v@@@I~
//    public void light_Enable(int Pplayer)                          //~v@@@R~//~va66R~
//    {                                                              //~v@@@I~//~va66R~
//        if (Dump.Y) Dump.println("ACATouch.light_enable player="+Pplayer);//~v@@@I~//~va66R~
//        enableLight(Pplayer,true/*swResetAll*/);                   //~v@@@R~//~va66R~
//    }                                                              //~v@@@I~//~va66R~
//***************************************************************************//~v@@@R~
//*on Server by GCM_LIGHT_TOUCHED_RESP msg                         //~v@@@R~
//***************************************************************************//~v@@@R~
    public void light_Touched_Resp(int Psender,int Pplayer)        //~v@@@R~
    {                                                              //~v@@@R~
    //********************                                         //~v@@@R~
        int stat=Status.getGameStatus();                           //~v@@@R~
        if (Dump.Y) Dump.println("ACATouch.light_Touched_Resp sender="+Psender+",player="+Pplayer+",stat="+stat);//~v@@@R~
        addCtrResponsed();                                         //~v@@@I~
        switch(stat)                                               //~v@@@R~
        {                                                          //~v@@@R~
        case GS_POSITION_ACCEPTING:                                //~v@@@R~
//            touchLightPositionAcceptingResp(Pplayer);            //~v@@@R~
            break;                                                 //~v@@@R~
        case GS_POSITION_ACCEPTED:                                 //~v@@@R~
            light_Touched_Position_Accepted_Resp_Msg(Psender);     //~v@@@I~
            break;                                                 //~v@@@R~
        case GS_COMPLETION_ACCEPTING:                              //~v@@@R~
            break;                                                 //~v@@@R~
        }                                                          //~v@@@R~
    }                                                              //~v@@@R~
//***************************************************************************//~v@@@I~
//*server/client touched at status GS_ACCEPTING                    //~v@@@R~
//***************************************************************************//~v@@@I~
	private void touchLightPositionAccepting(int Pplayer)          //~v@@@R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("ACATouch.touchLightPositionAccepting player="+Pplayer+",isserver="+isServer);//~v@@@R~
//        if (Accounts.isDummy(Pplayer))                           //~v@@@I~
//        {                                                        //~v@@@I~
//            touchLightAcceptingDummy(Pplayer);                   //~v@@@I~
//            return;                                              //~v@@@I~
//        }                                                        //~v@@@I~
        if (AG.aRiver.isPositionSetupAccepted(Pplayer))            //~vaifR~
        {                                                          //~vaifR~
	        if (Dump.Y) Dump.println("ACATouch.touchLightPositionAccepting return by dup touch player="+Pplayer);//~vaifR~
            return;                                                //~vaifR~
        }                                                          //~vaifR~
		sendTouched(Pplayer);                                      //~v@@@I~
        showPositionTile(Pplayer);                                 //~v@@@R~
//  	sendTouched(Pplayer);                                      //~v@@@R~
    }                                                              //~v@@@M~
//***************************************************************************//~v@@@I~
	private void sendTouched(int Pplayer)                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.sendTouched player="+Pplayer+",isServer="+isServer);//~v@@@R~
        if (isServer)                                              //~v@@@R~
        {                                                          //~v@@@I~
        	addCtrResponsed();                                     //~v@@@R~
	    	accounts.sendToClient(false/*swRobot*/,-1/*all client*/,GCM_LIGHT_TOUCHED,Pplayer);//~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
	    	accounts.sendToServer(GCM_LIGHT_TOUCHED,Pplayer);      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//*server/client by TOUCHED msg                                    //~v@@@R~
//***************************************************************************//~v@@@I~
	private void light_Touched_Position_Accepting_Msg(int Psender,int Pplayer)//~v@@@R~
    {                                                              //~v@@@I~
    //********************                                         //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.light_Touch_PositionAccepting_msg player="+Pplayer);//~v@@@I~//~0117R~
        if (AG.aRiver.isPositionSetupAccepted(Pplayer))            //~vaifR~
        {                                                          //~vaifR~
	        if (Dump.Y) Dump.println("ACATouch.light_Touched_Position_Accepting_Msg return by dup touch player="+Pplayer);//~vaifR~
            return;                                                //~vaifR~
        }                                                          //~vaifR~
        showPositionTile(Pplayer);                                 //~v@@@R~
//      sendTouchedResp(Psender,Pplayer);                          //~v@@@R~
        if (isServer) //client touched                             //~v@@@R~
        {                                                          //~v@@@I~
//      	addCtrResponsed();                                     //~v@@@R~
	    	accounts.sendToClient(false/*swRobot*/,Psender/*skip*/,GCM_LIGHT_TOUCHED,Pplayer);//~v@@@R~
        }                                                          //~v@@@I~
//        else                     //server touched                //~v@@@R~
//        {                                                        //~v@@@R~
//            accounts.sendToServer(GCM_LIGHT_TOUCHED_RESP,Pplayer);//~v@@@R~
//        }                                                        //~v@@@R~
    }                                                              //~v@@@I~
////***************************************************************************//~v@@@R~
//    private void sendTouchedResp(int Psender,int Pplayer)        //~v@@@R~
//    {                                                            //~v@@@R~
//        if (isServer) //client touched                           //~v@@@R~
//        {                                                        //~v@@@R~
//            addCtrResponsed();                                   //~v@@@R~
//            accounts.sendToClient(true/*swRobot*/,Psender/*skip*/,GCM_LIGHT_TOUCHED,Pplayer);//~v@@@R~
//        }                                                        //~v@@@R~
//        else                     //server touched                //~v@@@R~
//        {                                                        //~v@@@R~
//            accounts.sendToServer(GCM_LIGHT_TOUCHED_RESP,Pplayer);//~v@@@R~
//        }                                                        //~v@@@R~
//    }                                                            //~v@@@R~
////***************************************************************************//~v@@@R~
////*on Server by GCM_LIGHT_TOUCHED_RESP msg                       //~v@@@R~
////***************************************************************************//~v@@@R~
//    private void touchLightPositionAcceptingResp(int Pplayer)    //~v@@@R~
//    {                                                            //~v@@@R~
//    //********************                                       //~v@@@R~
//        if (Dump.Y) Dump.println("ACATouch.touchLightPositionAcceptingResp player="+Pplayer);//~v@@@R~
//        addCtrResponsed();  //all responsed                      //~v@@@R~
//        if (isCtrResponsedAll(false/*PswActiveOnly*/))           //~v@@@R~
//            touchLightPositionAcceptedAll();                     //~v@@@R~
//    }                                                            //~v@@@R~
//***************************************************************************//~v@@@I~
	public void enableLight(int Pplayer,boolean PswResetAll)       //~v@@@R~
    {                                                              //~v@@@I~
        int player=Accounts.mapDummy(Pplayer);                     //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.enableLight Pplayer="+Pplayer+",mapDummy="+player+",idxLocal="+accounts.idxLocal+",swResetAll="+PswResetAll+",status="+Status.getGameStatus()+",swSkipPositioning="+swSkipPositioning);//~v@@@R~//~vaifR~
      if (swSkipPositioning && Status.getGameStatus()==GS_POSITION_ACCEPTING)//~vaifI~
      {                                                            //+vaifI~
        if (Dump.Y) Dump.println("ACATouch.enableLight skipPositioning set shadowLight");//+vaifI~
        AG.aDiceBox.setWaitingResponse(Pplayer,true/*swShadow,to bypass onTouch msg*/,PswResetAll);//~vaifI~
      }                                                            //+vaifI~
      else                                                         //~vaifI~
        AG.aDiceBox.setWaitingResponse(Pplayer,player!=accounts.idxLocal/*swShadow*/,PswResetAll);//~v@@@R~
//      if ((TestOption.option & TestOption.TO_POSITIONING)!=0) //TODO//~v@@@R~
//      if (RuleSettingOperation.isPositioningSkip())              //~v@@@I~//~vaifR~
        if (swSkipPositioning)                                     //~vaifI~
        {                                                          //~v@@@I~
        	if (Status.getGameStatus()==GS_POSITION_ACCEPTING      //~v@@@R~
//      	||  Status.getGameStatus()==GS_POSITIONING)            //~v@@@R~
            )                                                      //~v@@@I~
            {                                                      //~v@@@I~
//      		if (Status.getGameStatus()==GS_POSITIONING)        //~v@@@R~
//              	Status.setGameStatus(GS_POSITION_ACCEPTING);   //~v@@@R~
        		if (Dump.Y) Dump.println("ACATouch.enableLight mapDummy player="+player+",idxLocal="+accounts.idxLocal);//~v@@@I~//~vaifR~
//  			if (player==accounts.idxLocal || player==PLAYER_YOU)//~v@@@R~
//  			if (player==PLAYER_YOU)                            //~v@@@R~
    			if (player==accounts.idxLocal)                     //~v@@@R~
                {                                                  //~vaifI~
    				touchLight(Pplayer);                           //~v@@@R~
//                    if (player==Pplayer)    //TODO test human player//~vaifR~
//                    {                                            //~vaifR~
//                        if (Dump.Y) Dump.println("ACATouch.enableLight TEST sendMsg"); //TODO//~vaifR~
//                        GameViewHandler.sendMsg(GCM_TOUCH,1/*actionUp*/,AG.aDiceBox.boxLight[player].left,AG.aDiceBox.boxLight[player].top,0,0);    //TODO test skip pos move dump//~vaifR~
//                    }                                            //~vaifR~
                }                                                  //~vaifI~
            }                                                      //~v@@@I~
        	if (Status.getGameStatus()==GS_POSITION_ACCEPTED)      //~v@@@I~
            {                                                      //~v@@@I~
    			if (player==accounts.idxLocal)                     //~v@@@I~
    				touchLight(Pplayer);                           //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
	public void resetLight(int Pplayer)                            //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.resetLight Pplayer="+Pplayer);//~v@@@I~
        AG.aDiceBox.resetLight(Pplayer);                           //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//*server/Client                                                   //~v@@@I~
//***************************************************************************//~v@@@I~
	private void showPositionTile(int Pplayer)                     //~v@@@R~
    {                                                              //~v@@@I~
        AG.aRiver.setupAccepted(Pplayer);   //show ESWN tile for each,all accepted//~v@@@I~
        int next=Players.nextPlayer(Pplayer);                      //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.showPositionTile player="+Pplayer+",next="+next+",1stPicker="+acaction.positioning1stPicker);//~v@@@R~
        if (next==acaction.positioning1stPicker)                   //~v@@@I~
			positionAcceptedAll();                                 //~v@@@R~
        else                                                       //~v@@@I~
	        enableLight(next,true/*swResetAll*/);  //with shadow or not//~v@@@R~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@M~
//*server/Client                                                   //~v@@@I~
//***************************************************************************//~v@@@I~
	private void positionAcceptedAll()                             //~v@@@R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("ACATouch.positionAcceptedAll isServer="+isServer+",tempStarter="+accounts.tempStarter+",idxLocal="+accounts.idxLocal);//~v@@@R~//~va66R~
//        showReadyToMove();                                       //~v@@@R~
//        sendMoveData();                                          //~v@@@R~
		if (AG.swTrainingMode)                                     //~va66I~
        {                                                          //~va66I~
            showReadyToMove();                                     //~va66I~
        }                                                          //~va66I~
        else                                                       //~va66I~
		if (isServer)                                              //~v@@@I~
        {                                                          //~v@@@I~
    		resetCtrResponsed();                                   //~v@@@I~
			addCtrResponsed();      //                             //~v@@@I~
//  	    showReadyToMove();      //status:ACCEPTED              //~v@@@I~
	        sendMoveData();                                        //~v@@@I~
//	    	accounts.sendToClient(false/*swRobot*/,-1/*all client*/,GCM_SETUP_MOVE,Pplayer);//~v@@@I~
        }                                                          //~v@@@I~
//      else                                                       //~v@@@R~
//         	accounts.sendToServer(GCM_SETUP_MOVE); //wile be done by TOUCHED msg//~v@@@R~
	}                                                              //~v@@@M~
//***************************************************************************//~v@@@I~
	private void showReadyToMove()                                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.showReadyToMove isServer="+isServer);//~v@@@I~
        Status.setGameStatus(GS_POSITION_ACCEPTED);                //~v@@@I~
        resetCtrResponsed();    //acceptctr=0                      //~v@@@R~
//      addCtrResponsed();                                         //~v@@@R~
        AG.aGMsg.drawMsgbar(R.string.Msg_PositionAcceptedAll);  //request tempStarter roll for PostionMove//~v@@@R~
        boolean swShadow=accounts.tempStarter==accounts.idxLocal;          //~v@@@I~
      if (isServer)                                                //~0222I~
        sendStatusChange(GS_POSITION_ACCEPTED);                    //~v@@@I~
//	    enableLight(accounts.tempStarter,swShadow);                //~v@@@M~//~va66R~
  	    enableLight(accounts.tempStarter,true/*swResetAll*/);      //~va66I~
	}                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
	private void sendMoveData()                                    //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.sendMoveData isServer="+isServer);//~v@@@R~
//      if (isServer)                                              //~v@@@R~
//      {                                                          //~v@@@R~
//            Status.setGameStatus(GS_POSITION_ACCEPTED);          //~v@@@R~
//            resetCtrResponsed();    //acceptctr=0                //~v@@@R~
//            addCtrResponsed();                                   //~v@@@R~
//            AG.aGMsg.drawMsgbar(R.string.Msg_PositionAcceptedAll);  //request tempStarter roll for move//~v@@@R~
	    	sendPosMemberData();                                   //~v@@@R~
//      }                                                          //~v@@@R~
//        else                                                     //~v@@@R~
//        {                                                        //~v@@@R~
//            accounts.sendToServer(GCM_POSITION_MOVE);            //~v@@@R~
//        }                                                        //~v@@@R~
	}                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//*On Server                                                       //~v@@@I~
//***************************************************************************//~v@@@I~
	public  void sendStatusChange(int Pstatus)                     //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.sendStatusChange Pstatus="+Pstatus+",status="+Status.getGameStatus());//~v@@@I~
        accounts.sendToClient(false,-1,GCM_STATUS_CHANGE,Pstatus); //~v@@@I~
	}                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//*On client by GS_STATUS_CHANGE msg                               //~v@@@I~
//***************************************************************************//~v@@@I~
	public void status_Change(int Pstatus)                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.status_Change Pstatus="+Pstatus+",status="+Status.getGameStatus());//~v@@@I~
        switch(Pstatus)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case GS_POSITION_ACCEPTED:                                 //~v@@@I~
			showReadyToMove();                                      //~v@@@I~
            break;                                                 //~v@@@I~
        case GS_READY_TO_STARTGAME:                               //~v@@@I~
        	showReadyToStartGame();                                //~v@@@I~
            break;                                                 //~v@@@I~
        case GS_GAME_STARTED:                                      //~v@@@I~
			Status.setGameStatus(GS_GAME_STARTED);                 //~v@@@I~
//          AG.aGMsg.drawMsgbar("");  //clear msgbar               //~v@@@R~
            AG.aGMsg.reset();  //clear msgbar                    //~v@@@I~
            break;                                                 //~v@@@I~
//      case GS_READY_TO_NEXTGAME:                                 //~v@@@I~//~0307R~
        case GS_READY_TO_NEXTGAME_CONTINUE:                        //~0307I~
        case GS_READY_TO_NEXTGAME_RESET:                           //~0307I~
        	if (Dump.Y) Dump.println("ACATouch.status_Change isSuspendGame="+Status.isSuspendGame()+",isSuspendRequested="+Status.isSuspendRequested());//~0307R~
//  		showReadyToStartNextGame();                            //~v@@@R~//~9830R~
//      	if (AG.aStatus.ctrSuspendRequest!=0)                       //~9822I~//~9905I~//~0307R~
//  	  	if (Status.isGameSuspended())                         //~0110I~//~0307R~
//            if (Status.isSuspendGame()) //SuspendDlg Opened      //~0307R~
//                accounts.nextGameWithoutSuspended();                   //~9830R~//~9905R~//~0307R~
//          if (Pstatus==GS_READY_TO_NEXTGAME_CONTINUE)            //~0307I~//~0308R~
        	if (Status.isSuspendRequested())                       //~0308R~
                accounts.nextGameWithoutSuspended();               //~0307I~
            break;                                                 //~v@@@I~
//        case GS_SUSPENDGAME:    //cliend                         //~v@@@R~
//            AG.aStatus.suspendGame(true);                        //~v@@@R~
//            break;                                               //~v@@@R~
//        case GS_SUSPENDGAME_CANCELED:   //client                 //~v@@@R~
//            AG.aStatus.suspendGame(false);                       //~v@@@R~
//            break;                                               //~v@@@R~
        }                                                          //~v@@@I~
	}                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//*Server send movePosData to client                               //~v@@@R~
//***************************************************************************//~v@@@I~
    private void sendPosMemberData()                                    //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.sendPosMemberData");    //~v@@@I~
        int[] pos=accounts.posMember;                              //~v@@@R~
        int sz=pos.length;                                         //~v@@@I~
        String posdata="";                                         //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
        	posdata+=pos[ii]+MSG_SEPAPP;                           //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.sendPosMemberData data="+posdata);//~v@@@I~
        accounts.sendToClient(false,-1,GCM_SETUP_MOVE,posdata);    //~v@@@R~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
    private int[] parsePosMemberData(String Pdata)                       //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACAction.parsePosMemberData data="+Pdata);//~v@@@I~
        String[] strPos=Pdata.split(MSG_SEPAPP,0/*No out size limit*/);//~v@@@I~
        int[] intPos=new int[PLAYERS];                             //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	intPos[ii]=Utils.parseInt(strPos[ii],0);               //~v@@@I~
        }                                                          //~v@@@I~
         return intPos;
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//* by msg GCM_POSITION_MOVE                                       //~v@@@R~
//* On Server/Client                                               //~v@@@R~
//***************************************************************************//~v@@@I~
	public void setup_Move(int Psender,String PposMember)          //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.setup_Move sender="+Psender+",data="+PposMember);//~v@@@R~
        if (isServer)   //last picker is client                    //~v@@@I~
        {                                                          //~v@@@I~
			positionAcceptedAll();	//send dat to client           //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	accounts.posMember=parsePosMemberData(PposMember);     //~v@@@R~
//  		showReadyToMove();                                     //~v@@@R~
            accounts.sendToServer(GCM_SETUP_MOVED);                //~v@@@R~
        }                                                          //~v@@@I~
//      showPositionTile(Psender);                                 //~v@@@R~
	}                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//* by msg GCM_POSITION_MOVED                                      //~v@@@I~
//* On Server                                                      //~v@@@I~
//***************************************************************************//~v@@@I~
	public void setup_Moved(int Psender)         //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.setup_Moved sender="+Psender);//~v@@@I~
        addCtrResponsed();                                         //~v@@@I~
        if (isCtrResponsedAll(true/*PswActive*/))                  //~v@@@I~
	    	showReadyToMove();                                     //~v@@@I~
	}                                                              //~v@@@I~
////***************************************************************************//~v@@@I~
////* by msg GCM_POSITION_MOVE                                     //~v@@@R~
////* On client                                                    //~v@@@I~
////***************************************************************************//~v@@@I~
//    public void seup_Move(int Psender,String PposMember)         //~v@@@I~
//    {                                                            //~v@@@I~
//        if (Dump.Y) Dump.println("ACATouch.position_Move sender="+Psender+",data="+PposMember);//~v@@@R~
//        if (isServer)     //msg from client                      //~v@@@I~
//        {                                                        //~v@@@I~
//            sendMoveData(); //to client                          //~v@@@R~
//        }                                                        //~v@@@I~
//        else    //received from server                           //~v@@@I~
//        {                                                        //~v@@@I~
//            accounts.posMember=parsePosMemberData(PposMember);   //~v@@@R~
//            accounts.sendToServer(GCM_POSITION_MOVE_RESP);       //~v@@@I~
//        }                                                        //~v@@@I~
//    }                                                            //~v@@@I~
//***************************************************************************//~v@@@I~
//**on server, GCM_POSTION_MOVED(client sent)                      //~v@@@R~
//***************************************************************************//~v@@@I~
//  public  void position_Moved(int Psender)                       //~v@@@R~
    private void light_Touched_Position_Accepted_Resp_Msg(int Psender)     //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.light_Touched_Position_Accepted_Resp_Msg sender="+Psender);//~v@@@R~
//      addCtrResponsed();                                         //~v@@@R~
        if (isCtrResponsedAll(true/*PswActive*/))                  //~v@@@R~
        {                                                          //~v@@@I~
        	showReadyToStartGame();                                //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
	private void showReadyToStartGame()                            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.showReadyToStartGame"); //~v@@@I~
		Status.setGameStatus(GS_READY_TO_STARTGAME);               //~v@@@R~
        AG.aGMsg.drawMsgbar(R.string.Msg_DiceForStart1stGame);     //~v@@@I~
//      acaction.enableDice(starter);                              //~v@@@R~
        if (isServer)                                              //~v@@@I~
            sendStatusChange(GS_READY_TO_STARTGAME);               //~v@@@I~
    	accounts.positionMoved();	//show GameSeq                 //~v@@@R~
    }                                                              //~v@@@I~
//***************************************************************************//~v@@@I~
//  public  void showReadyToStartNextGame()                        //~v@@@R~//~0307R~
    public  void showReadyToStartNextGame(int Pstatus)             //~0307I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.showReadyToStartNextGame");//~v@@@I~
//  	Status.setGameStatus(GS_READY_TO_NEXTGAME);                //~v@@@I~//~0307R~
    	Status.setGameStatus(Pstatus);                             //~0307I~
        if (isServer)                                              //~v@@@I~
//          sendStatusChange(GS_READY_TO_NEXTGAME);                //~v@@@R~//~0307R~
            sendStatusChange(Pstatus);                             //~0307I~
    	accounts.startNextGame();	//show GameSeq                 //~v@@@I~
    }                                                              //~v@@@I~
////***************************************************************************//~v@@@R~
////*on each player by GCM_LIGHT_TOUCHED msg                       //~v@@@R~
////***************************************************************************//~v@@@R~
//    public void showTouched(boolean PswResponse,int Pplayer)     //~v@@@R~
//    {                                                            //~v@@@R~
//        int stat=Status.getGameStatus();                         //~v@@@R~
//        if (Dump.Y) Dump.println("ACATouch.showTouched player="+Pplayer+",status="+stat);//~v@@@R~
//        switch(stat)                                             //~v@@@R~
//        {                                                        //~v@@@R~
//        case GS_POSITION_ACCEPTING:                              //~v@@@R~
//            showTouchedAccepting(PswResponse,Pplayer);           //~v@@@R~
//            break;                                               //~v@@@R~
//        case GS_POSITION_ACCEPTED:                               //~v@@@R~
////TODO        if ((Pplayer & ISTOUCH_ALL)!=0)   //all accepted   //~v@@@R~
////            {                                                  //~v@@@R~
////                AG.aRiver.endOfPositioning(); //erase setup 6 tiles//~v@@@R~
////                playerStarter=AG.aNamePlate.setPlayerPosition(playerTempStarter,posMember);//~v@@@R~
////                AG.aAccounts.setPosition(posMember);           //~v@@@R~
////                AG.aGMsg.drawMsgbar(R.string.Msg_DiceForStart1stGame);//~v@@@R~
////                Status.setGameStatusNewSet(playerStarter);     //~v@@@R~
////                AG.aDiceBox.setWaitingDice(playerStarter,true/*set starterID*/);   //waiting lamp//~v@@@R~
////                AG.aStarter.moveStarter(playerStarter);        //~v@@@R~
////                AG.aStarter.showGameSeq(playerStarter);        //~v@@@R~
////            }                                                  //~v@@@R~
//            break;                                               //~v@@@R~
//        case GS_COMPLETION_ACCEPTING:                            //~v@@@R~
////TODO        if ((Pplayer & ISTOUCH_ALL)!=0)   //all accepted   //~v@@@R~
////            {                                                  //~v@@@R~
////                int nextStarter=Status.setGameStatusGameComplete();//~v@@@R~
////                AG.aGMsg.drawMsgbar(R.string.Msg_DiceForNewGame);//~v@@@R~
////                AG.aDiceBox.setWaitingDice(nextStarter,true/*set starterID*/);   //waiting lamp//~v@@@R~
////                AG.aStarter.showGameSeq(playerStarter);        //~v@@@R~
////            }                                                  //~v@@@R~
//            break;                                               //~v@@@R~
//        }                                                        //~v@@@R~
//    }                                                            //~v@@@R~
////***************************************************************************//~v@@@R~
////*on each player by GCM_LIGHT_TOUCHED msg                       //~v@@@R~
////***************************************************************************//~v@@@R~
//    public void showTouchedAccepting(boolean PswResponse,int Pplayer)//~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACATouch.showTouchedAccepting player="+Pplayer);//~v@@@R~
//        if (!PswResponse)                                        //~v@@@R~
//            AG.aRiver.setupAccepted(Pplayer);   //show ESWN tile for each,all accepted//~v@@@R~
//        if (isCtrResponsedAll())  //all accepted                 //~v@@@R~
//        {                                                        //~v@@@R~
//            Status.setGameStatus(GS_POSITION_ACCEPTED);          //~v@@@R~
//            AG.aGMsg.drawMsgbar(R.string.Msg_DiceForPositionAccepted);//~v@@@R~
//            AG.aDiceBox.setWaitingResponseAll();                 //~v@@@R~
//        }                                                        //~v@@@R~
//        else                                                     //~v@@@R~
//        {                                                        //~v@@@R~
//            AG.aDiceBox.setWaitingResponse(Players.nextPlayer(Pplayer));   //waiting lamp//~v@@@R~
//            AG.aGMsg.drawMsgbar(R.string.Msg_DiceForPositionAccepting);//~v@@@R~
//        }                                                        //~v@@@R~
//    }                                                            //~v@@@R~
//***************************************************************************//~v@@@I~
//*on each player                                                  //~v@@@I~
//*   Server-->TOUCHED-->all Client-->TOUCHRESP-->Server           //~v@@@I~
//*   or                                                           //~v@@@I~
//*   Client-->TOUCHED-->Server                                    //~v@@@I~
//*                      Server-->TOUCHED-->other Client-->TOUCHRESP//~v@@@I~
//***************************************************************************//~v@@@I~
//    private void touchLightAtPositionAcceptedAll(int Pplayer)    //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACATouch.touchLightAtPositionAcceptedAll player="+Pplayer+",isserver="+isServer);//~v@@@R~
//        showAtPositionAccepted(Pplayer);                         //~v@@@R~
//        sendTouched(Pplayer);                                    //~v@@@R~
//    }                                                            //~v@@@R~
//***************************************************************************//~v@@@I~
//*server/client by TOUCHED msg                                    //~v@@@I~
//***************************************************************************//~v@@@I~
//    private void touchLightAtPositionAcceptedAllReceived(int Psender,int Pplayer)//~v@@@R~
//    {                                                            //~v@@@R~
//    //********************                                       //~v@@@R~
//        if (Dump.Y) Dump.println("ACATouch.touchLisghtAcceptedReceived player="+Pplayer);//~v@@@R~
//        showAtPositionAccepted(Pplayer);                         //~v@@@R~
//        sendTouchedResp(Psender,Pplayer);                        //~v@@@R~
//    }                                                            //~v@@@R~
//***************************************************************************//~v@@@I~
//*tempStarter touched after POSITION_ACCEPTED                     //~v@@@R~
//***************************************************************************//~v@@@I~
    private void touchLightPositionMove()                          //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.touchLightPositionMove player="+accounts.tempStarter);//~v@@@R~
//      if (isServer)                                              //~v@@@R~
//  		addCtrResponsed();                                     //~v@@@R~
        if (isServer)                                              //~v@@@I~
        {                                                          //~v@@@I~
//      	resetCtrResponsed();    //acceptctr=0                  //~v@@@R~
//  		addCtrResponsed();  //of server, sendTouched will +1   //~v@@@R~
        	accounts.positionMove();                               //~v@@@I~
        }                                                          //~v@@@I~
	  if (AG.swTrainingMode)    //assume all responsed positionmoved//~va66I~
      {                                                            //~va66I~
        if (Dump.Y) Dump.println("ACATouch.touchLightPositionMove trainingmode goto showReadyToStartGame");//~va66I~
    	showReadyToStartGame();                                    //~va66I~
      }                                                            //~va66I~
      else                                                         //~va66I~
        sendTouched(accounts.tempStarter);	//send GCM_LIGHT_TOUCHED//~v@@@R~
    }                                                              //~v@@@I~
////***************************************************************************//~v@@@R~
////*by GCM_POSITION_MOVE                                          //~v@@@R~
////***************************************************************************//~v@@@R~
//    public void position_Move(int Psender)                       //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACATouch.position_Move sender="+Psender);//~v@@@R~
//        accounts.positionMove();                                 //~v@@@R~
//    }                                                            //~v@@@R~
//***************************************************************************//~v@@@I~
//*by GCM_LIGHT_TOUCHED msg                                        //~v@@@R~
//***************************************************************************//~v@@@I~
    private void light_Touched_Position_Accepted_Msg(int Psender,int Pplayer)//~v@@@R~//~9B20R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("ACATouch.light_Touched_Position_Accepted_Msg sender="+Psender+",player="+Pplayer);//~v@@@R~
        accounts.positionMove();                                    //~v@@@I~
        if (isServer) //client touched  tempStarter                //~v@@@R~
        {                                                          //~v@@@I~
//      	resetCtrResponsed();    //acceptctr=0                  //~v@@@R~
			addCtrResponsed();  //of server                        //~v@@@R~
	        if (isCtrResponsedAll(true/*PswActive*/))              //~v@@@I~
    	    	showReadyToStartGame();                            //~v@@@I~
            else                                                   //~v@@@I~
		    	accounts.sendToClient(false/*swRobot*/,-1/*skip*/,GCM_LIGHT_TOUCHED,Pplayer);//~v@@@R~
        }                                                          //~v@@@I~
        else          //server touched  tempStarter                //~v@@@R~
        {                                                          //~v@@@I~
//  	    showReadyToStartGame();                                //~v@@@R~
	    	accounts.sendToServer(GCM_LIGHT_TOUCHED_RESP,Pplayer); //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
////***************************************************************************//~v@@@I~
//    private void showAtPositionAccepted(int Pplayer)             //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("ACATouch.showAtPositionAccepted player="+Pplayer);//~v@@@R~
//        AG.aDiceBox.resetLight(Pplayer);                         //~v@@@R~
//    }                                                            //~v@@@R~
//***************************************************************************//~v@@@I~
//    private void touchLightAtPositionAcceptedAllResp(int Pplayer)//~v@@@R~
//    {                                                            //~v@@@R~
//    //********************                                       //~v@@@R~
//        if (Dump.Y) Dump.println("ACATouch.touchLightPositionAcceptingResp player="+Pplayer);//~v@@@R~
//        addCtrResponsed();  //all responsed                      //~v@@@R~
//        if (isCtrResponsedAll(false/*PswActiveOnly*/))           //~v@@@R~
//            touchLightAtPositionAcceptedAllAll();                //~v@@@R~
//    }                                                            //~v@@@R~
//***************************************************************************//~v@@@I~
//    private void touchLightAtPositionAcceptedAllAll()            //~v@@@R~
//    {                                                            //~v@@@R~
//        AG.aRiver.endOfPositioning(); //erase setup 6 tiles      //~v@@@R~
//        playerStarter=AG.aNamePlate.setPlayerPosition(playerTempStarter,posMember);//~v@@@R~
//        AG.aAccounts.setPosition(posMember);                     //~v@@@R~
//        AG.aGMsg.drawMsgbar(R.string.Msg_DiceForStart1stGame);   //~v@@@R~
//        Status.setGameStatusNewSet(playerStarter);     //status=GS_START_GAME//~v@@@R~
//        AG.aDiceBox.setWaitingDice(playerStarter,true/*set starterID*/);   //waiting lamp//~v@@@R~
//        AG.aStarter.moveStarter(playerStarter);                  //~v@@@R~
//        AG.aStarter.showGameSeq(playerStarter);                  //~v@@@R~
//    }                                                            //~v@@@R~
}//class ACATouch                                                 //~dataR~//~@@@@R~//~v@@@R~
