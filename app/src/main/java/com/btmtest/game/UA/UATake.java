//*CID://+vakqR~: update#= 596;                                    //~vakqR~
//**********************************************************************//~v101I~
//2022/03/05 vakq (Bug)PAN mode; DrawnHW by 4kan fail by GCM_TAKE by Take button overtake postDelayedAutoTakeKan//~vakqI~
//2022/01/20 vaj7 display furiten err after reach on complte/drawnhw/drawnlast dialog//~vaj7I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Robot;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.game.UADelayed;
import com.btmtest.game.UserAction;                                //~v@@@I~
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.game.gv.Hands;
import com.btmtest.game.gv.River;
import com.btmtest.game.gv.Stock;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.Sound;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UserAction.*;//~v@@@I~
                                                                   //~v@@@I~
public class UATake                                                //~v@@@R~
{                                                                  //~0914I~
    private static final int POSPARM_CALLSTATUS=7;                 //~vaj7R~
    private UserAction UA;                                         //~v@@@I~
    private Players PLS;                                           //~v@@@R~
    private boolean isServer;                                      //~v@@@I~
    private Tiles tiles;                                           //~v@@@I~
    private Hands hands;                                           //~v@@@I~
    private Stock stock;                                           //~v@@@I~
	private Accounts ACC;                                          //~v@@@R~
//  private int timeout;                                           //~9622I~//~9624R~
    private River river;                                           //~v@@@I~//~9B23R~
//*************************                                        //~v@@@I~
	public UATake(UserAction PuserAction)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UATake Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        UA=PuserAction;                                            //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	public void init()                                             //~v@@@I~
    {                                                              //~v@@@I~
        PLS=AG.aPlayers;                                           //~v@@@R~
        tiles=AG.aTiles;                                           //~v@@@R~
        hands=AG.aHands;                                           //~v@@@I~
        river=AG.aRiver;                                           //~v@@@R~//~9B23R~
        stock=AG.aStock;                                           //~v@@@R~
        ACC=AG.aAccounts;                                          //~v@@@R~
//        acaction=AG.aACAction;                                   //~v@@@I~
        isServer=Accounts.isServer();                              //~v@@@R~
//      timeout=RuleSetting.getDelayDiscard();                     //~9622I~//~9624R~
        if (Dump.Y) Dump.println("UATake init isServer="+isServer);//~v@@@I~//~9A28R~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@M~//~9622R~
//    public boolean takeOne(int Pplayer)                            //~v@@@R~//~9622R~
//    {                                                              //~v@@@M~//~9622R~
//        boolean swKan;                                             //~v@@@I~//~9622R~
//        if (Dump.Y) Dump.println("UATake.takeOne");                //~v@@@R~//~9622R~
////        if (!PLS.isTakeAvailable())                              //~v@@@R~//~9622R~
////        {                                                        //~v@@@R~//~9622R~
////            if (Dump.Y) Dump.println("UATake.takeOne Dup takeOne");//~v@@@R~//~9622R~
////            return;                                              //~v@@@R~//~9622R~
////        }                                                        //~v@@@R~//~9622R~
//        swKan=PLS.isLastActionIsKan();                             //~v@@@R~//~9622R~
////        if (!PLS.isYourTurn(actionID,Pplayer))                   //~v@@@R~//~9622R~
////          return false;                                          //~v@@@R~//~9622R~
//        TileData td;                                               //~v@@@I~//~9622R~
//        if (swKan)                                                 //~v@@@R~//~9622R~
////          td=AG.aTiles.getNextKan(Pplayer);                      //~v@@@R~//~9622R~
//            td=AG.aTiles.getNextKan();                             //~v@@@I~//~9622R~
//        else                                                       //~v@@@I~//~9622R~
////          td= tiles.getNext(Pplayer);//TDF_TAKEN                 //~v@@@R~//~9622R~
//            td= tiles.getNext();//TDF_TAKEN                        //~v@@@I~//~9622R~
//        if (td == null)                                            //~v@@@M~//~9622R~
//            return false;                                          //~v@@@R~//~9622R~
////      tempTD=td;                                                 //~v@@@R~//~9622R~
////      Status.setTaken(td,player);                                //~v@@@R~//~9622R~
////      PLS.takeOne(Pplayer,td);    //insert into Hands            //~v@@@R~//~9622R~
////      boolean swShadow=Pplayer!=PLAYER_YOU;                      //~v@@@R~//~9622R~
////      PLS.takeOne(Pplayer,td,swShadow);    //insert into Hands   //~v@@@R~//~9622R~
//        PLS.takeOne(Pplayer,td);    //insert into Hands            //~v@@@R~//~9622R~
//        if (swKan)                                                 //~v@@@I~//~9622R~
//            stock.takeOneKan();                                    //~v@@@I~//~9622R~
//        else                                                       //~v@@@I~//~9622R~
//            stock.takeOne();               //draw stock            //~v@@@R~//~9622R~
////      if (Pplayer==PLAYER_YOU)                                   //~v@@@R~//~9622R~
//            hands.takeOne(Pplayer,td);  //draw Hands               //~v@@@R~//~9622R~
//        return true;                                               //~v@@@I~//~9622R~
//    }                                                              //~v@@@R~//~9622R~
	//*************************************************************************//~v@@6I~
	//*on each device                                              //~v@@6I~
	//*************************************************************************//~v@@6I~
    public boolean selectInfo(boolean PswServer,int Pplayer)       //~v@@6I~//~9B28R~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UATake.selectInfo swServer="+PswServer+",Pplayer="+Pplayer);//~1723I~
    	boolean rc=true;                                           //~v@@6I~
	    if (isLocked(Pplayer))	//before NEXT_PLAYER can take                                     //~v@@6I~//~9626R~
        	rc=false;                                              //~v@@6I~
        else                                                       //~9B28I~
			rc=AG.aUADelayed.chkSelectInfo2TouchOtherAction(PswServer,GCM_TAKE,Pplayer);//~9B28R~
        if (Dump.Y) Dump.println("UATake.selectInfo rc="+rc);      //~v@@6I~
        return rc;
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@6I~
    private boolean isLocked(int Pplayer)                          //~v@@6I~
    {                                                              //~v@@6I~
        boolean rc=false;                                          //~v@@6I~
//      TileData td=PLS.getLastDiscarded();                        //~v@@6I~//~0404R~
        TileData td=PLS.getCurrentTile();   //tileLastDiscarded or tileCurrentTaken//~0404I~
      if (false)    //TODO test                                    //~vakqI~
      {                                                            //~vakqI~
		if (td!=null && td.isLocked())                             //~v@@6I~
		{                                                          //~v@@6I~
	    	if (Dump.Y) Dump.println("UATake.isLocked ignore Take by Locked");//~0404I~
//        if (!td.isKanRiver())	//waiting Rinshan Take after kan   //~0402I~//~0404R~
          if (td.isKanTaken()||td.isKanAdd())	//waiting Rinshan Take after kan//~0404I~
	        UserAction.sendErr(0,Pplayer,R.string.Info_WaitKanTakableTimeout);//~0404I~
          else                                                     //~0404I~
	        UserAction.sendErr(0,Pplayer,R.string.Info_WaitDiscardTimeout);//~v@@6I~
            rc=true;                                               //~v@@6I~
        }                                                          //~v@@6I~
      }                                                            //~vakqI~
      else                                                         //~vakqI~
	  if (td!=null)                                                //~vakqI~
      {                                                            //~vakqI~
	    if (Dump.Y) Dump.println("UATake.isLocked td objectID="+td.hashCode());

        if (PLS.isLastActionIsKan())                               //~vakqI~
        {                                                          //~vakqI~
        	if (td.isLockedKanTake())                              //~vakqI~
        	{                                                      //~vakqI~
	        	UserAction.sendErr(0,Pplayer,R.string.Info_WaitKanTakableTimeout);//~vakqI~
        		rc=true;                                           //~vakqI~
        	}                                                      //~vakqI~
        }                                                          //~vakqI~
        else                                                       //~vakqI~
		if (td.isLocked())                                         //~vakqI~
        {                                                          //~vakqI~
	        UserAction.sendErr(0,Pplayer,R.string.Info_WaitDiscardTimeout);//~vakqI~
        	rc=true;                                               //~vakqI~
        }                                                          //~vakqI~
      }                                                            //~vakqI~
	    if (Dump.Y) Dump.println("UATake.isLocked rc="+rc+",player="+Pplayer);        //~v@@6I~//~vaj7R~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@@I~
	//*Pplayer: relative pos of triggered player                   //~v@@@R~
	//*************************************************************************//~v@@@I~
    public boolean takeOne(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@@R~
    {                                                              //~v@@@I~
    	boolean swKan,swDraw;                                      //~v@@@R~
        TileData td;                                               //~v@@@R~
//      int eswn,player;                                                  //~v@@@I~//~v@@6R~
    //***********************                                      //~v@@@I~
        if (Dump.Y) Dump.println("UATake.takeOne swServer="+PswServer+",swReceive="+PswReceived+",player="+Pplayer+",intP="+ Arrays.toString(PintParm));//~v@@@R~//~1128R~
        AG.aHandsTouch.enableMultiSelectionMode(false);            //~v@@6I~
//        Status.setRon(false,0,0,0);                                      //~v@@6I~
        if (!PswReceived)                                          //~v@@@I~
        {                                                          //~v@@@I~
	        td=PLS.getLastDiscarded();                             //~v@@@I~
//            if (td!=null && td.isLocked())                         //~v@@@R~//~v@@6R~
//            {                                                      //~v@@@I~//~v@@6R~
//                if (Dump.Y) Dump.println("UATake.takeOne ignore Take by Locked");//~v@@@I~//~v@@6R~
//                ACAction.showErrmsg(0,R.string.Info_WaitDiscardTimeout);//~v@@@I~//~v@@6R~
//                return false;                                      //~v@@@I~//~v@@6R~
//            }                                                      //~v@@@I~//~v@@6R~
        }                                                          //~v@@@I~
        if (PswServer)                                             //~v@@@I~
        {                                                          //~v@@@I~
	        swKan=PLS.isLastActionIsKan();                         //~v@@@R~
            if (swKan)                                             //~v@@@I~
//              td=AG.aTiles.getNextKan(Pplayer);                  //~v@@@R~
                td=AG.aTiles.getNextKan();                         //~v@@@I~
            else                                                   //~v@@@I~
//              td= tiles.getNext(Pplayer);//TDF_TAKEN             //~v@@@R~
                td= tiles.getNext();//TDF_TAKEN                    //~v@@@I~
//          tempTD=td;                                             //~v@@@R~
            if (td == null)                                        //~v@@@I~
                return false;                                      //~v@@@I~
//            eswn=ACC.getCurrentEswn();                           //~v@@@R~
//            if (swReceived)                                      //~v@@@R~
//                player=Pplayer;                                  //~v@@@R~
//            else                                                 //~v@@@R~
//                player=eswn;                                     //~v@@@R~
//            PLS.takeOne(player,td,eswn);    //insert into Hands  //~v@@@R~
			boolean swShadow=Pplayer!=PLAYER_YOU;                  //~v@@@I~
            PLS.takeOne(Pplayer,td,swShadow);    //insert into Hands//~v@@@R~
//      	UA.msgDataToClient=UA.makeMsgDataToClient(Pplayer,td,swKan?1:0);//~v@@@R~//~vaj7R~
        	UA.msgDataToClient=UA.makeMsgDataToClient(Pplayer,td,swKan?1:0,makeMsgDataInfoCallStatus());//~vaj7I~
        	AG.aUADelayed.resetWait(Pplayer);	//switch to next player after delay a moment//~v@@6R~
            if (!swKan)                                            //~v@@6I~
	            stock.drawPendingOpenDora(Pplayer,GCM_TAKE);       //~v@@6R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			if (PswReceived)	                                   //~vaj7I~
                saveServerCallStatus(PintParm,POSPARM_CALLSTATUS); //~vaj7M~
	        swKan=PintParm[PARMPOS_SWKAN]!=0;                      //~v@@@I~
            if (swKan)                                             //~v@@6I~
                td=AG.aTiles.getNextKan();	//add ctrKan           //~v@@6I~
            else                                                   //~v@@6I~
            {                                                      //~v@@6I~
	            if (PswReceived)                                   //~v@@6I~
			        AG.aTiles.getNextTilePos();	//advance posCurrntTile        //~9225I~//~v@@6I~
            }                                                      //~v@@6I~
        	td=new TileData(true/*swEswnToPlayer*/,PintParm,PARMPOS_TD);                   //~v@@@R~
            if (Pplayer==PLAYER_YOU)                               //~v@@@I~
//  	        PLS.takeOne(Pplayer,td,ACC.getCurrentEswn());    //insert into Hands//~v@@@R~
    	        PLS.takeOne(Pplayer,td);    //insert into Hands    //~v@@@I~
            else                                                   //~v@@@I~
    	        PLS.takeOneOtherOnClient(Pplayer,td,true/*swShadow*/);    //insert into Hands//~v@@@I~
            if (PswReceived)                                        //~v@@6I~
            {                                                      //~v@@6I~
	        	AG.aUADelayed.resetWait(Pplayer);	//switch to next player after delay a moment//~v@@6I~//~9226R~
            if (!swKan)                                            //~v@@6I~
		    	stock.drawPendingOpenDora(Pplayer,GCM_TAKE);       //~v@@6R~
            }                                                      //~v@@6I~
        }                                                          //~v@@@I~
        if (swKan)                                                 //~v@@@R~
        {                                                          //~v@@6I~
            stock.takeOneKan();                                    //~v@@@R~
            stock.takeKan(Pplayer,PLS.getKanType());               //~v@@6I~
        }                                                          //~v@@6I~
        else                                                       //~v@@@R~
            stock.takeOne();               //draw stock            //~v@@@R~
//		swDraw=ACC.isDrawThisDevice(Pplayer);                      //~v@@@R~
  		swDraw=Pplayer==PLAYER_YOU;                                //~v@@@I~
        if (swDraw)                                                //~v@@@I~
	    	hands.takeOne(Pplayer,td);  //draw Hands               //~v@@@R~
        if (Dump.Y) Dump.println("UATake.takeOne TestOption="+Integer.toHexString(TestOption.option));//~1125R~
        if ((TestOption.option & TestOption.TO_TAKEDISCARD)!=0)    //~v@@6I~//~9225I~//~v@@6M~
        {                                                          //~9225I~//~v@@6M~
        	int cpos=AG.aTiles.getCurrentTilePos();                   //~v@@6I~//~9225R~
	    	int remains=PIECE_TILECTR-AG.aTiles.ctrKan;       //KEEPLEFT is on Top of array//~v@@6I~
        	if (cpos>=remains-8 && cpos<=remains-4)	//pos start from 7*2+13*4//~v@@6I~
            {                                                      //~9225I~//~v@@6M~
		        TestOption.option &= ~TestOption.TO_TAKEDISCARD;   //~9225I~//~v@@6M~
		        TestOption.option |= TestOption.TO_TAKEDISCARD_STOP;//~9225I~//~v@@6M~
            }                                                      //~9225I~//~v@@6M~
        }                                                          //~9225I~//~v@@6M~
        if ((TestOption.option & TestOption.TO_TAKEDISCARD_STOP)!=0)//~9225I~//~v@@6M~
        {                                                          //~9225I~//~v@@6M~
        	int cpos=AG.aTiles.getCurrentTilePos();                //~9225I~
        	if (cpos==TILECTR_KEEPLEFT+PLAYERS*HANDCTR)            //~9225R~//~v@@6I~
            {                                                      //~9225I~//~v@@6M~
		        if (Dump.Y) Dump.println("Tiles.getTile reset Stop Ppos="+cpos);//~9225I~//~v@@6I~
		        TestOption.option |= TestOption.TO_TAKEDISCARD;    //~9225I~//~v@@6M~
		        TestOption.option &= ~TestOption.TO_TAKEDISCARD_STOP;//~9225I~//~v@@6M~
            }                                                      //~9225I~//~v@@6M~
        }                                                          //~9225I~//~v@@6M~
        if ((TestOption.option & TestOption.TO_TAKEDISCARD)!=0)    //~v@@6I~
            if (Pplayer==PLAYER_YOU)                               //~9225I~
            {                                                      //~1125I~
        		if (Dump.Y) Dump.println("UATake.takeOne postDiscard by test option");//~1125R~
//			    GameViewHandler.sendMsg(GCM_DISCARD,PLAYER_YOU,0,0);   //~v@@6R~//~9225R~
  		    	UADelayed.postDelayedActionMsg(100,GCM_DISCARD,PLAYER_YOU,0,0);//~v@@6I~//~9225R~
            }                                                      //~1125I~
//      if (PswServer)                                             //~9622R~
//          setTimeout(Pplayer,GCM_TAKE);	//swith to next player after delay a moment//~9622R~//~9626R~//~9627R~
	    river.drawFrameDiscardedTile(GCM_TAKE);	//draw discarded tile frame//~9B23I~
        Robot r;                                                   //~0229I~
      	if (PswServer)                                             //~0229I~
        	r=ACC.getRobot(Pplayer);                               //~0229I~
        else                                                       //~0229I~
        	r=null;                                                //~0229I~
        AG.aUADelayed.setRonable(false);     	//for KAN dup ron availability,reset at take//~0402I~
      if (r!=null)                                                 //~0229I~
        r.takeOne(Pplayer,td);                                     //~0229I~
      else                                                         //~0229I~
        setAutoDiscardTimeout(PswServer,Pplayer,GCM_TAKE);	//swith to next player after delay a moment//~9627R~
        if (TestOption.getTimingBTIOErr()==TestOption.BTIOE_AFTER_TAKE)//~9A28I~
        	if (Pplayer==PLAYER_YOU)                               //~9A28I~
	          	TestOption.disableBT();                            //~9A28R~
// 		Sound.play(SOUND_TAKE,false/*not change to beep when beeponly option is on*/);//~9C01I~//~9C03R~
   		Sound.play(SOUNDID_TAKE,false/*not change to beep when beeponly option is on*/);//~9C03I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9622I~
	//*set autodiscard timeout                                     //~9627I~
	//*************************************************************************//~9627I~
//  public void setTimeout(int Pplayer,int Paction)                            //~9622R~//~9626R~//~9627R~
    public void setAutoDiscardTimeout(boolean PswServer,int Pplayer,int Paction)//~9627R~
    {                                                              //~9622I~
        if (Dump.Y) Dump.println("UATake.setAutoDiscardTimeout player="+Pplayer+",action="+Paction);//~9622I~//~9626R~//~9B22R~
//      if (timeout==0)                                            //~9622I~//~9624R~
//      	return;                                                //~9622I~//~9624R~
//      if (Pplayer!=PLAYER_YOU)                                   //~9622I~//~9627R~
//      if (Pplayer!=PLAYER_YOU)                                   //~9627I~
//      if (!PswServer)                                            //~9627R~
//      	return;                                                //~9622I~//~9627R~
        if ((TestOption.option & TestOption.TO_TAKEDISCARD)!=0)    //~9622I~
        	return;                                                //~9622I~
//      UA.UADL.postDelayedAutoDiscard(timeout,Pplayer,PLS.ctrTakenAll);  //~9622I~//~9623R~//~9624R~
        UA.UADL.postDelayedAutoDiscard(PswServer,Pplayer,PLS.ctrTakenAll,Paction);   //~9624I~//~9626R~//~9627R~
    }                                                              //~9622I~
	//*************************************************************************//~9627I~
    public boolean isActiveAutoDiscardTimeout(int Pplayer,int PctrTakenAll,int Paction)//~9627R~
    {                                                              //~9627I~
        boolean rc=(PctrTakenAll==PLS.ctrTakenAll && PLS.isCurrentPlayerTaking(Pplayer));//~9627I~
        if (Dump.Y) Dump.println("UATake.isActiveAutoDiscardTimeout rc="+rc+",action="+Paction+",player="+Pplayer+",ctrTakenAll="+PctrTakenAll+",Players.ctrTakenAll="+PLS.ctrTakenAll+",currentActionID="+AG.aUserAction.currentActionID);//~9627R~
        return rc;
    }                                                              //~9627I~
	//*************************************************************************//~9622I~
	//*on server at discardtimeout                                 //~9627I~
	//*************************************************************************//~9627I~
    public void autoDiscardTimeout(int Pplayer,int PctrTakenAll,int Paction)        //~9622R~//~9626R~
    {                                                              //~9622I~
        if (Dump.Y) Dump.println("UATake.autoDiscardTimeout action="+Paction+",player="+Pplayer+",ctrTakenAll="+PctrTakenAll+",Players.ctrTakenAll="+PLS.ctrTakenAll+",currentActionID="+AG.aUserAction.currentActionID);//~9622R~//~9623R~//~9625R~//~9626R~//~9627R~
//      if (PctrTakenAll==PLS.ctrTakenAll && AG.aUserAction.currentActionID==GCM_TAKE)//~9622R~//~9623R~//~9626R~
//      if (PctrTakenAll==PLS.ctrTakenAll && AG.aUserAction.currentActionID==Paction)//~9626R~
//      if (PctrTakenAll==PLS.ctrTakenAll && PLS.isCurrentPlayerTaking(Pplayer))//~9626I~//~9627R~
//            if (Pplayer==PLAYER_YOU)    //server                 //~9622R~
//                GameViewHandler.sendMsg(GCM_DISCARD);            //~9622R~
//            else                                                 //~9622R~
//                AG.aUserAction.sendToClient(false/*sendAll*/,GCM_DISCARD,Pplayer,null);//~9622R~
//  		AG.aGC.sendMsg(GCM_DISCARD,PLAYER_YOU);	//simulate discard button//~9622I~//~9626R~//~9627R~
//  		AG.aGC.sendMsg(GCM_DISCARD,Pplayer);	//simulate discard button//~9627R~
//      else                                                       //~9626I~//~9627R~
//      if (Dump.Y) Dump.println("UATake.autoDiscardTimeout Not Sent GCM_DISCARD");//~9626I~//~9627R~
    	if (Pplayer==PLAYER_YOU)                                   //~9627R~
			AG.aGC.sendMsg(GCM_DISCARD,Pplayer);	//simulate discard button//~9627I~
        else                                                       //~9627I~
        {                                                          //~1129I~
          Robot r=AG.aAccounts.getRobot(Pplayer);                    //~1129I~
          if (r!=null)                                             //~1129R~
           	r.autoDiscardTimeout(Pplayer);                         //~1129R~
          else                                                     //~1129I~
			UA.UADL.sendMsgEmulatedToClient(GCM_DISCARD,Pplayer);	//simulate discard button//~9627I~
        }                                                          //~1129I~
    }                                                              //~9622I~
	//*************************************************************************//~9627I~
	//*on Client,by GCM_WAITOFF at waitingAutoDiscard              //~9627R~
	//*************************************************************************//~9627I~
    public boolean autoDiscardTimeoutClient(int Pplayer)           //~9627R~
    {                                                              //~9627I~
    	boolean rc=false;                                          //~9627I~
        if (Dump.Y) Dump.println("UATake.discardTimeoutClient player="+Pplayer);//~9627I~
		if (Pplayer==PLAYER_YOU)                                   //~9627I~
        {                                                          //~9627I~
			AG.aGC.sendMsg(GCM_DISCARD,Pplayer);	//simulate discard button//~9627I~
            rc=true;                                               //~9627I~
        }                                                          //~9627I~
        if (Dump.Y) Dump.println("UATake.discardTimeoutClient rc="+rc);//~9627I~
        return rc;                                                 //~9627I~
    }                                                              //~9627I~
	//*************************************************************************//~vaj7I~
	//*On Server                                                   //~vaj7I~
	//*************************************************************************//~vaj7I~
    public static String makeMsgDataInfoCallStatus()               //~vaj7I~
    {                                                              //~vaj7I~
    	String rc=AG.aRoundStat.RSP[0].callStatus+MSG_SEPAPP       //~vaj7I~
    	         +AG.aRoundStat.RSP[1].callStatus+MSG_SEPAPP       //~vaj7I~
    	         +AG.aRoundStat.RSP[2].callStatus+MSG_SEPAPP       //~vaj7I~
    	         +AG.aRoundStat.RSP[3].callStatus;                  //~vaj7I~
        if (Dump.Y) Dump.println("UATake.makeMsgDataInfoCallStatus rc="+rc);//~vaj7I~
        return rc;                                                 //~vaj7I~
    }                                                              //~vaj7I~
	//*************************************************************************//~vaj7I~
	//*On Client                                                   //~vaj7I~
	//*************************************************************************//~vaj7I~
    public static void saveServerCallStatus(int[] PintParm,int Ppos)//~vaj7I~
    {                                                              //~vaj7I~
        if (Dump.Y) Dump.println("UATake.saveServerCallStatus pos="+Ppos+",intp(Hex)="+ Utils.toHexString(PintParm));//~vaj7R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~vaj7I~
        {                                                          //~vaj7I~
            int callStatus=PintParm[Ppos+ii];   //furiten info     //~vaj7I~
            AG.aRoundStat.RSP[ii].callStatus=callStatus;                      //~vaj7I~
        }                                                          //~vaj7I~
    }                                                              //~vaj7I~
}//class                                                           //~v@@@R~
