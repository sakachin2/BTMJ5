//*CID://+va9eR~: update#= 603;                                    //~va9cR~//~va9eR~
//**********************************************************************//~v101I~
//2021/06/17 va9e del va9c because reach call is expanded to other player. alternatively add force-reach to menu item//~va9cI~
//2021/06/17 va9c allow reach and warinig only for 2han-constraint or kataagari err. set reach at discard even issed warning//~va9cI~
//2021/06/14 va96 When win button pushed in Match mode, issue warning for not ronable hand.//~va96I~
//2021/04/20 va8j KataAgari chk for also Human Take in PlayAloneNotifyMode//~va8jI~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/11/04 va28 Delete force reach option, local yaku is all abount patterns, is not ron format.//~va28I~
//2020/11/03 va27 Tenpai chk at Reach                              //~va27I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.TileData;
import com.btmtest.game.UserAction;                                //~v@@@I~
import com.btmtest.game.gv.GMsg;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.game.gv.River;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UserAction.*;
                                                                   //~v@@@I~
public class UAReach                                                //~v@@@R~//~v@@6R~
{                                                                  //~0914I~
	public static final int OPT_OPEN_SAVE=0; //reach not open      //~v@@6R~
	public static final int OPT_OPEN_OPEN=1; //open reach          //~v@@6I~
	public static final int OPT_OPEN_KAN=2;  //ankan after reached //~v@@6I~
	public static final int OPT_OPEN_ONLY_REACH=3; //open at end of game//~v@@6R~//~9427R~
	public static final int OPT_OPEN_ONLY_PENDING=4; //open at end of game for pending notification//~v@@6I~
	public static final int OPT_OPEN_ONLY99=9;      //no need to send Hands//~9519I~
                                                                   //~v@@6I~
    private UserAction UA;                                         //~v@@@I~
    private UAReachChk UARC;                                       //~va27I~
    private Players PLS;                                           //~v@@@R~
    private boolean isServer;                                      //~v@@@I~
//  private Tiles tiles;                                           //~v@@@I~//~v@@6R~
//  private Hands hands;                                           //~v@@@I~//~v@@6R~
//  private Stock stock;                                           //~v@@@I~//~v@@6R~
//  private Accounts ACC;                                          //~v@@6R~
//  private boolean statusReach;                                   //~v@@6R~
    private River river;                                           //~v@@@I~//~v@@6R~
    private boolean swCheckReach;                                  //~va27I~
    private int actionID;                                          //~va27I~
    private int actionIDErr;                                       //~va9eI~
//*************************                                        //~v@@@I~
	public UAReach(UserAction PuserAction)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~v@@6R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UAReach Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~v@@6R~
        UA=PuserAction;                                            //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	public void init()                                             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserReach init");//~v@@@I~       //~va27I~
        PLS=AG.aPlayers;                                           //~v@@@R~
//      tiles=AG.aTiles;                                           //~v@@@R~//~v@@6R~
//      hands=AG.aHands;                                           //~v@@6R~
        river=AG.aRiver;//~v@@@I~
//        river=AG.aRiver;                                           //~v@@@R~
//      stock=AG.aStock;                                           //~v@@@R~//~v@@6R~
//      ACC=AG.aAccounts;                                          //~v@@@R~//~v@@6R~
//        acaction=AG.aACAction;                                   //~v@@@I~
//      isServer=Accounts.isServer();                              //~v@@@R~//~v@@6R~
        UARC=new UAReachChk();                                     //~va27I~
    	swCheckReach= RuleSettingOperation.isCheckReach();//~va27I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@6I~//~va60R~
//    public boolean reach(int Pplayer)                              //~v@@6I~//~va60R~
//    {                                                              //~v@@6I~//~va60R~
//        if (Dump.Y) Dump.println("UAReach.reach player="+Pplayer); //~v@@6R~//~va60R~
////        if (!players.isYourTurn(actionID,Pplayer))               //~v@@6I~//~va60R~
////            return ;                                             //~v@@6I~//~va60R~
//        if (!PLS.reach(Pplayer))                               //~v@@6I~//~va60R~
//            return false;                                          //~v@@6I~//~va60R~
//        river.reach(Pplayer);                                      //~v@@6I~//~va60R~
//        return true;                                               //~v@@6I~//~va60R~
//    }                                                              //~v@@6I~//~va60R~
	//*************************************************************************//~v@@6I~
//  public boolean selectInfo(boolean PswServer,int Pplayer)       //~va27R~
    public boolean selectInfo(boolean PswServer,int Pplayer,int PactionID)//~va27I~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UAReach.selectInfo actionID="+PactionID+",swServer="+PswServer+",player="+Pplayer+",swCheckReach="+swCheckReach);//~va27R~
        actionID=PactionID;                                        //~va27I~
        actionIDErr=0;                                             //~va9eI~
//        statusReach=PLS.reachAvailableStatus();                  //~v@@6R~
//        boolean rc=(statusReach!=REACH_NA)                       //~v@@6R~
		if (!chkTenpai(PactionID))                                 //~va27R~
			return false;                                          //~va27R~
		boolean rc=AG.aUADelayed.chkSelectInfo2TouchOtherAction(PswServer,GCM_TAKE,Pplayer);//~9B28R~
        if (Dump.Y) Dump.println("UAReach.selectInfo rc="+rc);     //~va27R~
        return rc;
    }                                                              //~v@@6I~
	//*************************************************************************//~va27I~
    private boolean chkTenpai(int PactionID)                       //~va27R~
    {                                                              //~va27I~
    	boolean rc=true;                                           //~va27I~
        if (Dump.Y) Dump.println("UAReach.chkTenpai action="+PactionID+",swCheckReach="+swCheckReach);             //~va27I~//~va8jR~
        if (PactionID==GCM_REACH||PactionID==GCM_REACH_OPEN)	//not FORCE//~va27I~
            if (swCheckReach)                                      //~va27R~
            {                                                      //~va27R~
                TileData td=PLS.getTileSelected(PLAYER_YOU);       //~va27R~
                if (td==null)                                      //~va27R~
                {                                                  //~va27R~
                    GMsg.drawMsgbar(R.string.AE_NoTileSelectedAtReach);//~va27R~
                    return false;                                  //~va27R~
                }                                                  //~va27R~
                if (!UARC.chkReach(PLAYER_YOU,td))                 //~va27R~
                {                                                  //~va27R~
                    GMsg.drawMsgbar(R.string.Err_ReachNoten);      //~va27R~
//                  AG.aUserAction.updateButtonStatusReach(GCM_FORCE_REACH_ENABLE);//~va28R~
                	actionIDErr=actionID;                          //~va9eI~
                    return false;                                  //~va27R~
                }                                                  //~va27R~
//              rc=AG.aRAReach.chkFuritenMultiWait(PactionID,td);  //~va8jI~//~va9cR~
//              AG.aRAReach.chkFuritenMultiWait(PactionID,td); //rc=true evenif chkFuritenErr//~va9cR~
                rc=AG.aRAReach.chkFuritenMultiWait(PactionID,td);  //~va9cI~
                if (!rc)                                           //~va9eI~
                	actionIDErr=actionID;                          //~va9eI~
            }                                                      //~va27R~
        if (Dump.Y) Dump.println("UAReach.chkTenpai rc="+rc+",actionIDErr="+actionIDErr);      //~va27I~//~va9eR~
        return rc;                                                 //~va27I~
    }                                                              //~va27I~
	//*************************************************************************//~v@@@I~
	//*Pplayer: relative pos of triggered player                   //~v@@@R~
	//*************************************************************************//~v@@@I~
    public boolean reach(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@@R~//~v@@6R~
    {                                                              //~v@@@I~
//  	boolean swKan/*,swDraw*/;                                      //~v@@@R~//~v@@6R~//~9A30R~
//      TileData td;                                               //~v@@@R~//~9A30R~
//      int eswn,player;                                                  //~v@@@I~//~v@@6R~
		boolean rc;                                                //~v@@6I~
    //***********************                                      //~v@@@I~
        if (Dump.Y) Dump.println("UAReach.reach swServer="+PswServer+",swReceive="+PswReceived+",player="+Pplayer);//~v@@@R~//~v@@6R~
        if (PswServer)                                             //~v@@@I~
        {                                                          //~v@@@I~
//          td=PLS.getCurrentTaken();                              //~9A30R~
//          td.setReach();           //~v@@@I~//~v@@6M~            //~9A30R~
//			boolean swShadow=Pplayer!=PLAYER_YOU;                  //~v@@@I~//~v@@6R~
//      	if (Dump.Y) Dump.println("UAReach.reach currentTaken="+td.toString());//~v@@6I~//~9A30R~
            PLS.reach(Pplayer);    //insert into Hands//~v@@@R~//~v@@6R~
//          UserAction.showInfoAllEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Reach));//~v@@6R~//~9830R~
//      	UA.msgDataToClient=UA.makeMsgDataToClient(Pplayer,td);//~v@@@R~//~v@@6R~//~9A30R~
        	UA.msgDataToClient=UA.makeMsgDataToClient(Pplayer);    //~9A30I~
//      	AG.aUADelayed.resetWait(Pplayer);	//switch to next player after delay a moment//~v@@6R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//  	    if (PswReceived)                                          //~v@@@I~//~v@@6I~//~9A30R~
//          	td=new TileData(true/*swEswnToPlayer*/,PintParm,PARMPOS_TD);//~v@@6I~//~9A30R~
//          if (Pplayer==PLAYER_YOU)                               //~v@@@I~//~v@@6R~
    	        PLS.reach(Pplayer);    //insert into Hands    //~v@@@I~//~v@@6R~
//          else                                                   //~v@@@I~//~v@@6R~
//  	        PLS.reachOtherOnClient(Pplayer,td,true/*swShadow*/);    //insert into Hands//~v@@@I~//~v@@6R~
//          if (PswReceived)                                       //~v@@6R~
//          	AG.aUADelayed.resetWait(Pplayer);	//switch to next player after delay a moment//~v@@6R~
        }                                                          //~v@@@I~
//      UserAction.showInfoEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Reach));//~9830I~//~9A30R~
//		swDraw=Pplayer==PLAYER_YOU;                                //~v@@@I~//~v@@6R~
        river.reach(Pplayer);                                      //~v@@6I~
//	    if (Pplayer==PLAYER_YOU)                                   //~9A30I~//~va60R~
//      {                                                          //~va27I~//~va60R~
//          ACAction.showErrmsg(0,R.string.UserAction_Reach);	//show requester only//~9A30I~//~va60R~
//      	PLS.setReachAction(actionID);	//chk at Discard       //~va27I~//~va60R~
        	PLS.setReachAction(actionID,Pplayer);	//chk at Discard//~va60I~
//      }                                                          //~va27I~//~va60R~
//      GMsg.showHL(0,GCM_REACH);                                  //~9C02I~//~va60R~
        GMsg.showHLName(0,GCM_REACH,Pplayer);                      //~va60I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@6I~
    public boolean reachOpen(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@6I~
    {                                                              //~v@@6I~
//  	boolean swKan/*,swDraw*/;                                  //~v@@6I~//~9A30R~
//      TileData td;                                               //~v@@6I~//~9A30R~
		boolean rc;                                                //~v@@6I~
    //***********************                                      //~v@@6I~
        if (Dump.Y) Dump.println("UAReach.reachOpen swServer="+PswServer+",swReceive="+PswReceived+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~v@@6I~//~9A30R~
        if (PswServer)                                             //~v@@6I~
        {                                                          //~v@@6I~
//          td=PLS.getCurrentTaken();                              //~v@@6I~//~9A30R~
//          td.setReach();                                         //~v@@6I~//~9A30R~
//      	if (Dump.Y) Dump.println("UAReach.reach currentTaken="+td.toString());//~v@@6I~//~9A30R~
//          PLS.reach(Pplayer);    //insert into Hands             //~v@@6I~
            PLS.reachOpen(Pplayer);    //insert into Hands         //~v@@6I~
//          UserAction.showInfoAllEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Reach_Open));//~v@@6R~//~9A30R~
//      	UA.msgDataToClient=UA.makeMsgDataToClient(Pplayer,td); //~v@@6I~//~9A30R~
        	UA.msgDataToClient=UA.makeMsgDataToClient(Pplayer);    //~9A30I~
        }                                                          //~v@@6I~
        else                                                       //~v@@6I~
        {                                                          //~v@@6I~
//  	    if (PswReceived)                                       //~v@@6I~//~9A30R~
//          	td=new TileData(true/*swEswnToPlayer*/,PintParm,PARMPOS_TD);//~v@@6I~//~9A30R~
//  	    PLS.reach(Pplayer);    //insert into Hands             //~v@@6I~
    	    PLS.reachOpen(Pplayer);    //insert into Hands         //~v@@6I~
        }                                                          //~v@@6I~
        river.reach(Pplayer);                                      //~v@@6I~
//	    if (Pplayer==PLAYER_YOU)                                   //~9A30R~//~va60R~
//      {                                                          //~va27I~//~va60R~
//          ACAction.showErrmsg(0,R.string.UserAction_Reach_Open);	//show requester only//~9A30I~//~va60R~
//      	PLS.setReachAction(actionID);	//chk at Discard       //~va27I~//~va60R~
        	PLS.setReachAction(actionID,Pplayer);	//chk at Discard//~va60I~
//      }                                                          //~va27I~//~va60R~
        if (TestOption.getTimingBTIOErr()==TestOption.BTIOE_AFTER_OPEN)//~9A28I~
          	TestOption.disableBT();                                //~9A28I~
//      GMsg.showHL(0,GCM_REACH);  //requester only,at discard for other      //~9C02I~//~va60R~
        GMsg.showHLName(0,GCM_REACH_OPEN,Pplayer);                 //~va60R~
        return true;                                               //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~9A30I~
	//*Pplayer: by resetButton                                     //~9A30I~
	//*************************************************************************//~9A30I~
    public boolean reachReset(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9A30I~
    {                                                              //~9A30I~
		boolean rc,swReset;                                        //~9A30R~
    //***********************                                      //~9A30I~
        if (Dump.Y) Dump.println("UAReach.reachReset swServer="+PswServer+",swReceive="+PswReceived+",player="+Pplayer);//~9A30I~
        if (PswServer)                                             //~9A30I~
        {                                                          //~9A30I~
            swReset=PLS.resetReachDoneBeforeDiscard(Pplayer);    //insert into Hands//~9A30R~
        	UA.msgDataToClient=UA.makeMsgDataToClient(Pplayer);    //~9A30R~
        }                                                          //~9A30I~
        else                                                       //~9A30I~
        {                                                          //~9A30I~
            swReset=PLS.resetReachDoneBeforeDiscard(Pplayer);    //insert into Hands//~9A30R~
        }                                                          //~9A30I~
//      river.reach(Pplayer);	//ignored when option is not reach after discard//~9A30I~
  	    if (Pplayer==PLAYER_YOU)                                   //~9A30I~
        	if (swReset)                                           //~9A30I~
		        ACAction.showErrmsg(0,R.string.UserAction_ReachReset);	//show requester only//~9A30R~
        return true;                                               //~9A30I~
    }                                                              //~9A30I~
	//*************************************************************************//~9A30I~
    public boolean reachOpenReset(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9A30I~
    {                                                              //~9A30I~
        if (Dump.Y) Dump.println("UAReach.reachOpenReset swServer="+PswServer+",swReceive="+PswReceived+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~9A30I~
	    return reachReset(PswServer,PswReceived,Pplayer,PintParm); //~9A30I~
    }                                                              //~9A30I~
	//*************************************************************************//~v@@6I~
	//*by msg issued by UADiscard after reach                      //~v@@6I~
	//*************************************************************************//~v@@6I~
    public boolean open(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@6I~
    {                                                              //~v@@6I~
		TileData[] tds;                                            //~v@@6I~
		TileData td;                                               //~v@@6I~
        int optOpen;                                               //~v@@6I~
        if (Dump.Y) Dump.println("UAReach.open swServer="+PswServer+",swReceive="+PswReceived+",player="+Pplayer+",intP="+Arrays.toString(PintParm));//~v@@6R~
        if (PswServer)                                             //~v@@6I~
        {                                                          //~v@@6I~
			tds=PLS.getHands(Pplayer);                             //~v@@6I~
			int ctr=tds.length;                                    //~v@@6I~
            optOpen=PintParm[PARMPOS_OPEN_OPTION];                 //~v@@6M~
        	if (Dump.Y) Dump.println("UAReach.open server optOpen="+optOpen+",tds="+TileData.toString(tds));//~v@@6I~//~9427I~
          	if (optOpen==OPT_OPEN_ONLY_REACH)	//already sent at reach//~v@@6I~//~9309R~
	          	UA.msgDataToClient=makeMsgDataToClientOpenLastReach(Pplayer,optOpen);//~9309I~
            else                                                   //~9309I~
          	if (optOpen==OPT_OPEN_ONLY99)	//no need to send Hands//~9519R~
//            	UA.msgDataToClient=makeMsgDataToClientOpenLastReach(Pplayer,optOpen);//~9519I~//~9609R~
	          	UA.msgDataToClient=makeMsgDataToClient(Pplayer,optOpen,tds,ctr);//~9609I~
            else                                                   //~9519I~
	          	UA.msgDataToClient=makeMsgDataToClient(Pplayer,optOpen,tds,ctr);//~v@@6R~
            if (Pplayer!=PLAYER_YOU)                               //~v@@6I~
//          	if (optOpen==OPT_OPEN_OPEN)                        //~v@@6R~
            	if (optOpen==OPT_OPEN_OPEN || optOpen==OPT_OPEN_ONLY_REACH || optOpen==OPT_OPEN_ONLY_PENDING)//~v@@6R~
	            	AG.aHands.open(Pplayer);                       //~v@@6R~
                else                                               //~9427I~
                if (optOpen==OPT_OPEN_KAN)                         //~9427I~
//              	AG.aHands.open(Pplayer);                       //~9427I~//~9530R~
                	;                                              //~9530I~
                else                                               //~9519I~
                if (optOpen==OPT_OPEN_ONLY99)                      //~9519I~
	            	AG.aHands.open(Pplayer);                       //~9519I~
                if (optOpen==OPT_OPEN_OPEN)                        //~9A30I~
                {                                                  //~va60I~
                    AG.aRoundStat.reachOpen(Pplayer);              //~va60R~
//  	            UserAction.showInfoEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Reach_Open));//~9A30R~//~va60R~
        			GMsg.showHLName(0,GCM_REACH_OPEN,Pplayer);     //~va60I~
                }                                                  //~va60I~
                else                                               //~9A30I~
				if (optOpen==OPT_OPEN_SAVE) //reach not open       //~9A30I~
                {                                                  //~va60I~
                    AG.aRoundStat.reach(Pplayer);                  //~va60I~
//  	            UserAction.showInfoEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Reach));//~9A30I~//~va60R~
        			GMsg.showHLName(0,GCM_REACH,Pplayer);          //~va60I~
                }                                                  //~va60I~
        }                                                          //~v@@6I~
        else                                                       //~v@@6I~
        {                                                          //~v@@6I~
    	    if (PswReceived)                                       //~v@@6I~
            {                                                      //~v@@6I~
  				optOpen=PintParm[PARMPOS_OPEN_OPTION];             //~9A30I~
            	if (Pplayer!=PLAYER_YOU)                           //~v@@6I~
                {                                                  //~v@@6I~
//          		tds=getReceivedPair(PintParm,PARMPOS_TD);      //~v@@6R~
//  				optOpen=PintParm[PARMPOS_OPEN_OPTION];         //~v@@6I~//~9A30R~
		        	if (Dump.Y) Dump.println("UAReach.open client optOpen="+optOpen);//~9427I~
                  if (optOpen==OPT_OPEN_ONLY_REACH)                //~v@@6I~
                  {                                                //~v@@6I~
    	            AG.aHands.open(Pplayer);                       //~v@@6I~
                  }                                                //~v@@6I~
//                else                                             //~v@@6I~//~9609R~
//                if (optOpen==OPT_OPEN_ONLY99)                    //~9519I~//~9609R~
//                {                                                //~9519I~//~9609R~
//  	            AG.aHands.open(Pplayer);                       //~9519I~//~9609R~
//                }                                                //~9519I~//~9609R~
                  else                                             //~9519I~
                  {                                                //~v@@6I~
            		tds=getReceivedPair(PintParm,PARMPOS_OPEN_TDS);//~v@@6I~
                    if (optOpen==OPT_OPEN_ONLY_PENDING)            //~v@@6I~
                    {                                              //~v@@6I~
//  	            	PLS.setHandsClient(Pplayer,tds);           //~v@@6M~//~9522R~
    	            	PLS.setHandsClientReplace(Pplayer,tds);    //~9522I~
    	                AG.aHands.open(Pplayer);                   //~v@@6I~
                    }                                              //~v@@6I~
	                if (optOpen==OPT_OPEN_ONLY99)                  //~9609I~
                    {                                              //~9609I~
    	            	PLS.setHandsClientReplace(Pplayer,tds);    //~9609I~
    	                AG.aHands.open(Pplayer);                   //~9609I~
                    }                                              //~9609I~
                    else                                           //~v@@6I~
                    if (optOpen==OPT_OPEN_KAN)                     //~v@@6I~
                    {                                              //~9427I~
		            	PLS.setHandsClientReplace(Pplayer,tds);    //~v@@6I~
//  	                AG.aHands.open(Pplayer);                   //~9427I~//~9530R~
                    }                                              //~9427I~
                    else	//OPT_OPEN_SAVE(reach) and OPT_OPEN_OPEN(openReach)                                           //~v@@6I~//~9522R~
                    {                                              //~v@@6I~
//                    if (optOpen==OPT_OPEN_OPEN) //OpenReach      //~v@@6R~
////                  if (optOpen==OPT_OPEN_ONLY)                  //~v@@6R~
//                    {                                            //~v@@6I~
//                        PLS.setHandsClient(Pplayer,tds);         //~v@@6I~
//                        AG.aHands.open(Pplayer);                 //~v@@6R~
//                    }                                            //~v@@6I~
//                    else                                         //~v@@6R~
//                    if (PLS.isOpenReach(Pplayer))                //~v@@6R~
//                        AG.aHands.open(Pplayer);                 //~v@@6R~
//                    PLS.setHandsClient(Pplayer,tds);             //~v@@6I~//~9522R~
                      PLS.setHandsClientReplace(Pplayer,tds);      //~9522I~
                      if (optOpen==OPT_OPEN_OPEN) //OpenReach      //~v@@6I~
                          AG.aHands.open(Pplayer);                 //~v@@6I~
                    }                                              //~v@@6I~
                  }//open                                          //~v@@6I~
                }                                                  //~v@@6I~
                if (optOpen==OPT_OPEN_OPEN)                        //~9A30I~
                {                                                  //~va96I~
                    AG.aRoundStat.reachOpen(Pplayer);              //~va96I~
//  	            UserAction.showInfoEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Reach_Open));//~9A30R~//~va60R~
        			GMsg.showHLName(0,GCM_REACH_OPEN,Pplayer);     //~va60I~
                }                                                  //~va96I~
                else                                               //~9A30I~
				if (optOpen==OPT_OPEN_SAVE) //reach not open       //~9A30I~
                {                                                  //~va96I~
                    AG.aRoundStat.reach(Pplayer);                  //~va96I~
//  	            UserAction.showInfoEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Reach));//~9A30I~//~va60R~
        			GMsg.showHLName(0,GCM_REACH,Pplayer);          //~va60I~
                }                                                  //~va96I~
            }                                                      //~v@@6I~
        }                                                          //~v@@6I~
        return true;                                               //~v@@6I~
    }                                                              //~v@@6I~
//    //*************************************************************************//~v@@6R~
//    public  String makeMsgDataToClient(int Pplayer,TileData[] Ptds,int Pctr)//~v@@6R~
//    {                                                            //~v@@6R~
//        if (Dump.Y) Dump.println("UARon.makeMsgDataToClient ctr="+Pctr+",tds="+TileData.toString(Ptds));//~v@@6R~
//        return UAPon.makeMsgDataToClient(Pplayer,Ptds,Pctr);     //~v@@6R~
	//*************************************************************************//~v@@@I~//~v@@6I~
    public  String makeMsgDataToClient(int Pplayer,int Poption,TileData[] Ptds,int Pctr)//~v@@@I~//~v@@6I~
    {                                                              //~v@@@I~//~v@@6I~
        if (Dump.Y) Dump.println("UAReach.makeMsgDataToClient ctr="+Pctr+",tds="+TileData.toString(Ptds));//~v@@@I~//~v@@6I~//~9427R~
		int eswn=Accounts.playerToEswn(Pplayer);                   //~v@@6I~
        StringBuffer sb=new StringBuffer();                        //~v@@6I~
        sb.append(eswn+MSG_SEPAPP2+Poption+MSG_SEPAPP2+Pctr+MSG_SEPAPP2);//~v@@6I~
		for (int ii=0;ii<Pctr;ii++)                                //~v@@6I~
        {                                                          //~v@@6I~
	        sb.append(ACAction.strTD(Ptds[ii])+MSG_SEPAPP2);       //~v@@6I~
        }                                                          //~v@@6I~
        if (Dump.Y) Dump.println("UAReach.makeMsgDataToServer sb="+sb);//~v@@6I~//~9427R~
        return sb.toString();                                                              //~v@@@I~//~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~9309I~
    private String makeMsgDataToClientOpenLastReach(int Pplayer,int Poption)//~9309I~//~va60R~
    {                                                              //~9309I~
		int eswn=Accounts.playerToEswn(Pplayer);                   //~9309I~
        String s=eswn+MSG_SEPAPP2+Poption;                        //~9309I~
        if (Dump.Y) Dump.println("UAReach.makeMsgDataToClientOpenLastReach player="+Pplayer+",option="+Poption+",out msg="+s);//~9309I~//~9427R~
        return s;                                                  //~9309I~
    }                                                              //~9309I~
	//*************************************************************************//~v@@6I~
    private TileData[] getReceivedPair(int[] PintParm,int Ppos)    //~v@@6I~
    {                                                              //~v@@6I~
	    TileData[] tds=UAPon.getReceivedPair(PintParm,Ppos,-1);    //~v@@6I~
        if (Dump.Y) Dump.println("UAReach.getReceivedPair ctr="+tds.length+",tds="+TileData.toString(tds));//~v@@6I~//~9427R~
        return tds;                                                //~v@@6I~
    }                                                              //~v@@6I~
    //*************************************************************************//~v@@6M~
    //*on Server    from UADiscard                                               //~v@@6M~//~va60R~
    //*************************************************************************//~v@@6M~
    public static void postOpenReach(int Pplayer,boolean PswOpen)  //~v@@6I~
    {                                                              //~v@@6M~
        if (Dump.Y) Dump.println("UAReach.postOpenReach player="+Pplayer+",swOpen="+PswOpen);//~v@@6M~//~va60R~
        GameViewHandler.sendMsg(GCM_OPEN,Pplayer,(PswOpen ? OPT_OPEN_OPEN : OPT_OPEN_SAVE),0);//~v@@6M~
    }                                                              //~v@@6M~
    //*************************************************************************//~v@@6I~
    //*on Server                                                   //~v@@6I~
    //*************************************************************************//~v@@6I~
    public static void postOpenOnly(int Pplayer,boolean PswReach)      //~v@@6R~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UAReach.postOpenOnly player="+Pplayer+",swReach="+PswReach);//~v@@6R~//~9519R~//~0329R~
        GameViewHandler.sendMsg(GCM_OPEN,Pplayer,PswReach ? OPT_OPEN_ONLY_REACH : OPT_OPEN_ONLY_PENDING,0);//~v@@6R~
    }                                                              //~v@@6I~
    //*************************************************************************//~9519I~
    //*on Server                                                   //~9519I~
    //*************************************************************************//~9519I~
    public static void postOpenOnly99(int Pplayer)                 //~9519I~
    {                                                              //~9519I~
        if (Dump.Y) Dump.println("UAReach.postOpen99 player="+Pplayer);//~9519I~//~va9eR~
        GameViewHandler.sendMsg(GCM_OPEN,Pplayer,OPT_OPEN_ONLY99,0);//~9519I~
    }                                                              //~9519I~
    //*************************************************************************//~va9eI~
    //*on the device(PLAYER_YOU)                                   //~va9eI~
    //*************************************************************************//~va9eI~
    public static boolean reachAnyway()                            //~va9eR~
    {                                                              //~va9eI~
        if (Dump.Y) Dump.println("UAReach.reachAnyway static");    //~va9eI~
        boolean rc=AG.aUserAction.UARE.reachAnyway(PLAYER_YOU);            //~va9eR~
        if (Dump.Y) Dump.println("UAReach.reachAnyway static rc="+rc);//~va9eI~
        return rc;                                                 //~va9eI~
    }                                                              //~va9eI~
    //*************************************************************************//~va9eI~
    //*on the device(PLAYER_YOU)                                   //~va9eI~
    //*************************************************************************//~va9eI~
    private boolean reachAnyway(int Pplayer)                       //~va9eR~
    {                                                              //~va9eI~
        if (Dump.Y) Dump.println("UAReach.reachAnyway player="+Pplayer+",actionID="+actionID+",actionIDErr="+actionIDErr);//~va9eR~
        boolean rc=true;                                           //~va9eI~
        int msgID;	                                               //~va9eI~
        if (actionIDErr==GCM_REACH_OPEN)	//not FORCE            //~va9eI~
        	msgID=GCM_FORCE_REACH_OPEN;	//    =28;                 //~va9eI~
        else                                                       //~va9eI~
        if (actionIDErr==GCM_REACH)	//not FORCE                    //~va9eI~
        	msgID=GCM_FORCE_REACH;                                 //~va9eI~
        else                                                       //~va9eI~
        {                                                          //~va9eI~
        	GMsg.drawMsgbar(R.string.AE_NoReachErrToForce);        //~va9eI~
            return false;                                          //~va9eR~
        }                                                          //~va9eI~
        actionIDErr=0;                                             //+va9eI~
        GameViewHandler.sendMsg(msgID,null);                       //~va9eI~
        if (Dump.Y) Dump.println("UAReach.reachAnyway return true");//~va9eI~
        return true;                                               //~va9eI~
    }                                                              //~va9eI~
}//class                                                           //~v@@@R~
