//*CID://+DATER~: update#= 636;                                    //~v@@@R~//~v@@5R~//~v@@@R~//~v@@6R~//~9A12R~
//**********************************************************************//~v101I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.game.Players;
import com.btmtest.dialog.RuleSetting;                             //~v@@6R~
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.game.UserAction;                                //~v@@@I~
import com.btmtest.game.gv.GMsg;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.game.gv.Hands;
import com.btmtest.game.gv.River;
import com.btmtest.game.gv.Stock;
import com.btmtest.game.UADelayed2;                                //~9B23I~
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.Sound;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.Complete.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
                                                                   //~v@@@I~
public class UARon                                                 //~v@@@R~//~v@@6R~
{                                                                  //~0914I~
    private UserAction UA;                                         //~v@@@I~
    private ACAction ACA;                                          //~v@@@I~
    private Accounts ACC;                                          //~v@@@I~
    private UADelayed2 UADL;                                       //~9B23I~
    private Players PLS;                                           //~v@@@R~
    private boolean isServer;                                      //~v@@@I~
    private Tiles tiles;                                           //~v@@@I~
    private Hands hands;                                           //~v@@@I~
    private Stock stock;                                           //~v@@@I~
    private River river;                                           //~v@@@R~
    private TileData[] tdsPair;                                    //~v@@@I~
    private boolean[] swSelectedMulti;                             //~v@@6I~
    private boolean swServer,swReceived;                           //~v@@6I~
    private boolean swMultiRon,swMultiRon3Next;                    //~9B29I~
    private UARonChk UARC;                                         //~9C11I~
//*************************                                        //~v@@@I~
	public UARon(UserAction PuserAction)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~v@@6R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UARon Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~v@@6R~
        UA=PuserAction;                                            //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	public void init()                                             //~v@@@I~
    {                                                              //~v@@@I~
    	UARC=new UARonChk();                                       //~9C11I~
        PLS=AG.aPlayers;                                           //~v@@@R~
        ACC=AG.aAccounts;                                          //~v@@@R~
        ACA=AG.aACAction;                                          //~v@@@I~
        UADL=AG.aUADelayed;                                        //~9B23I~
        tiles=AG.aTiles;                                           //~v@@@R~
        hands=AG.aHands;                                           //~v@@@I~
        river=AG.aRiver;                                           //~v@@@R~
        stock=AG.aStock;                                           //~v@@@R~
//        accounts=AG.aAccounts;                                   //~v@@@I~
//        acaction=AG.aACAction;                                   //~v@@@I~
        isServer=Accounts.isServer();                              //~v@@@R~
        if (Dump.Y) Dump.println("UARon init isServer="+isServer); //~v@@@R~//~v@@6R~
    	swMultiRon=RuleSetting.isMultiRon();               //~v@@6I~//~9B29I~
    	swMultiRon3Next=RuleSetting.isDrawnHW3R();     //~v@@6I~   //~9B29I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@I~//~v@@6R~
//    public boolean complete(int Pplayer)                            //~v@@@I~//~v@@6R~
//    {                                                              //~v@@@I~//~v@@6R~
//        if (true)                                                //~v@@6R~
//        return false;   //TODO test                              //~v@@6R~
//        if (Dump.Y) Dump.println("UserAction.complete player="+Pplayer);//~v@@6R~
////        if (!players.isYourTurn(actionID,Pplayer))             //~v@@6R~
////            return;                                            //~v@@6R~
//        Status.setGameStatusGameCompleteReq(Pplayer);            //~v@@6R~
//        int rc=PLS.complete(Pplayer);                            //~v@@6R~
//        AG.aHands.complete(Pplayer,rc);  //draw taken tile when taken//~v@@6R~
//        AG.aRiver.complete(Pplayer,rc);  //draw discarded tile when river taken//~v@@6R~
//        AG.aDiceBox.setWaitingResponseAll();                     //~v@@6R~
//        AG.aGMsg.drawMsgbar(R.string.Msg_AcceptComplete); //TDO test//~v@@6R~
//        Status.setGameStatus(GS_COMPLETION_ACCEPTING);           //~v@@6R~
//        return true;                                             //~v@@6R~
//    }                                                            //~v@@6R~
//    //*************************************************************************//~v@@6R~
//    //*chk delayed Ron(delay control is done at server)          //~v@@6R~
//    //*************************************************************************//~v@@6R~
//    public boolean isDelayedRon(boolean PswReceived,int Pplayer) //~v@@6R~
//    {                                                            //~v@@6R~
//        int rc=false;                                            //~v@@6R~
//        if (AG.aStatus.isIssuedRon())   //already issued           //~9222I~//~v@@6R~
//        {                                                        //~v@@6R~
//            if (!AG.aUADelayed.isDupRonOK(Pplayer))        //~9222R~//~v@@6R~
//            {                                                    //~v@@6R~
//                rc=true;                                         //~v@@6R~
//                GC.actionError(0,Pplayer/*sendto*/,R.string.AE_DupRonDelayed);        //~9222R~//~v@@6R~
//            }                                                    //~v@@6R~
//        }                                                        //~v@@6R~
//        if (Dump.Y) Dump.println("UARon.isDelayedRon rc="+rc);   //~v@@6R~
//        return rc;                                               //~v@@6R~
//    }                                                            //~v@@6R~
	//*************************************************************************//~9B23I~
//  public boolean selectInfo(boolean PswServer,int Pplayer,int[] PintParm)//~9B23I~//~0205R~
    public boolean selectInfo(int PactionID,boolean PswServer,int Pplayer,int[] PintParm)//~0205I~
    {                                                              //~9B23I~
        if (Dump.Y) Dump.println("UARon.selectInfo actionID="+PactionID+",swServer="+PswServer+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~9B23I~//~0205R~
      if (PactionID==GCM_RON)   //!GCM_RON_ANYWAY                  //~0205I~//+0402R~
        if (!chkComplete(Pplayer))                                  //~9C11R~//~9C12R~
        {                                                          //~9C12I~
			GMsg.drawMsgbar(R.string.Err_RonChk);                  //~0205R~
        	return false;                                          //~9C11R~
        }                                                          //~9C12I~
        if (!UADL.chkSelectInfo2Touch(PswServer,GCM_RON,Pplayer,PintParm))	//actionAlert optionally//~9B23I~
            return false;                                          //~9B23I~
        return true;                                               //~9B23I~
    }                                                              //~9B23I~
	//*************************************************************************//~9C11I~
    private boolean chkComplete(int Pplayer)                       //~9C11I~
    {                                                              //~9C11I~
		boolean rc;                                                //~9C12I~
        if (Dump.Y) Dump.println("UARon.chkComplete player="+Pplayer);             //~9C11I~
		rc=UARC.chkComplete(Pplayer);                            //~9C11I~//~9C12R~
		return rc;                                          //~9C11I~
	}                                                              //~9C11I~
	//*************************************************************************//~v@@@I~
    public boolean complete(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@@R~//~v@@6R~
    {                                                              //~v@@@I~
    	boolean swDraw;                                            //~v@@@R~
        TileData[] tds;                                            //~v@@@R~
        int msgid;                                                 //~v@@6I~
    //***********************                                      //~v@@@I~
        if (Dump.Y) Dump.println("UARon.complete swServer="+PswServer+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~v@@@R~//~v@@6R~//~9B23R~
//      AG.aUADelayed.actionDone(GCM_PON,PswServer,PswReceived,Pplayer);//~9B23I~//~9B27R~
        int typeComplete=0;
        swServer=PswServer;                                        //~v@@6I~
        swReceived=PswReceived;                                    //~v@@6I~
        if (PswServer)                                             //~v@@@I~
        {                                                          //~v@@@I~
//            if (isDelayedRon(PswReceived,Pplayer))               //~v@@6R~
//            {                                                    //~v@@6R~
//                return false;                                    //~v@@6R~
//            }                                                    //~v@@6R~
            typeComplete=PLS.complete(Pplayer);    //insert into Hands       //~v@@@R~//~v@@6R~
            PLS.setTileRon();  //TDF_RON                           //~v@@6R~
//            int ctrComp=AG.aComplete.getCtrComplete();           //~v@@6R~
//            if (ctrComp>0) //already set complete                //~v@@6R~
//            {                                                    //~v@@6R~
//                showMsgMultiRon(ctrComp);                        //~v@@6R~
//            }                                                    //~v@@6R~
//            else                                                 //~v@@6R~
//            {                                                    //~v@@6R~
//                if ((typeComplete &(COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0)//~v@@6R~
//                    msgid=R.string.UserActionMsg_RonTaken;       //~v@@6R~
//                else                                             //~v@@6R~
//                    msgid=R.string.UserActionMsg_RonRiver;       //~v@@6R~
//                UserAction.showInfoAllEswn(0/*opt*/,Pplayer,Utils.getStr(msgid));//~v@@6R~
//            }                                                    //~v@@6R~
            tds=PLS.getHands(Pplayer);                             //~v@@6I~
          	UA.msgDataToClient=makeMsgDataToClient(Pplayer,tds,tds.length);//~v@@@R~//~v@@6R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	if (PswReceived)                                       //~v@@6R~
            {                                                      //~v@@6I~
            	tds=getReceivedPair(PintParm,PARMPOS_TD);          //~v@@6R~
                if (Pplayer!=PLAYER_YOU)                           //~v@@6I~
//                  PLS.setHandsClient(Pplayer,tds);               //~v@@6R~
                    PLS.setHandsClientReplace(Pplayer,tds);        //~v@@6I~
				typeComplete=PLS.complete(Pplayer);                //~v@@6I~
	            PLS.setTileRon();	//TDF_RON                      //~v@@6R~
            }                                                      //~v@@6I~
        }                                                          //~v@@@I~
        if (PswServer||PswReceived)                                //~v@@6I~
        {                                                          //~v@@6I~
//          AG.aUADelayed.resetWaitAll();   //switch to next player after delay a moment//~v@@6R~//~9B28R~
//          UADL.resetWaitAll();   //switch to next player after delay a moment//~9B28I~
            UADL.resetWaitAllByRon(Pplayer);   //resetWaitAll if not MultiRon//~9B29R~
	        UADL.resetWait2Touch(PswServer,GCM_RON,Pplayer);	//actionDone() to release wait//~9B29R~
            if (Dump.Y) Dump.println("UARon.complete typeComplete="+Integer.toHexString(typeComplete));//~v@@6R~
            hands.complete(Pplayer,typeComplete);  //draw discarded tile when river taken//~v@@6R~
            river.complete(Pplayer,typeComplete);  //draw discarded tile when river taken//~v@@6R~
            AG.aNamePlate.complete(false/*reset*/,Pplayer);        //~0303M~
            AG.aPointStick.complete(Pplayer);   //erase point stick if just reached//~v@@6R~
//          AG.aAccounts.resetReachDonePay(Pplayer);   //back 1000 //~v@@6R~
            AG.aPlayers.resetReachDone(Pplayer);                   //~v@@6I~
//            if ((typeComplete & COMPLETE_KAN_TAKEN_OTHER)!=0)       //~v@@6I~
//            {                                                      //~v@@6I~
//            	if (AG.aStock.completeResetDora())                 //~v@@6R~
//                	AG.aTiles.cancelKan();                         //~v@@6I~
//            }                                                      //~v@@6I~
            AG.aStock.drawDoraComplete();   //show hidden dora     //~v@@6I~
            TileData td=PLS.getTileComplete(typeComplete);                     //~v@@6I~
            TileData tdCompKanTake=null;                           //~v@@6I~
            if ((typeComplete & COMPLETE_KAN_RIVER)!=0)                  //~v@@6I~
            	tdCompKanTake=PLS.getCurrentTaken();                   //~v@@6I~
			int eswnLooser=td.eswn;                                //~v@@6I~
//          Status.setRon(true,Accounts.playerToEswn(Pplayer),typeComplete,td.eswn/*looser*/);//~v@@6R~
            Complete.Status compStat=AG.aComplete.new Status(typeComplete,Accounts.playerToEswn(Pplayer),eswnLooser,td,tdCompKanTake);//~v@@6R~
            Status.setCompleteStatus(compStat);                          //~v@@6I~
        }                                                          //~v@@6I~
//      if (PswServer)                                             //~v@@6I~//~9B11R~
        if (PswServer||PswReceived)                                //~9B11I~
        {                                                          //~v@@6I~
            int ctrComp=AG.aComplete.getCtrComplete();             //~v@@6I~
            if (ctrComp>1) //already set complete                  //~v@@6R~
            {                                                      //~v@@6I~
            	showMsgMultiRon(ctrComp);                          //~v@@6I~
            }                                                      //~v@@6I~
            else                                                   //~v@@6I~
            {                                                      //~v@@6I~
//                if ((typeComplete &(COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0)//~v@@6I~//~9C02R~
//                    msgid=R.string.UserActionMsg_RonTaken;         //~v@@6I~//~9C02R~
//                else                                               //~v@@6I~//~9C02R~
//                    msgid=R.string.UserActionMsg_RonRiver;         //~v@@6I~//~9C02R~
////              UserAction.showInfoAllEswn(0/*opt*/,Pplayer,Utils.getStr(msgid));//~v@@6I~//~9B11R~//~9C02R~
//                UserAction.showInfoEswn(0/*opt*/,Pplayer,Utils.getStr(msgid));//~9B11I~//~9C02R~
                if ((typeComplete &(COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0)//~9C02I~
                {                                                  //~9C03I~
//  		        GMsg.showHL(0,GCM_TAKE,Pplayer);                       //~9C02I~//~9C06R~//~0218R~
    		        GMsg.showHLName(0,GCM_TAKE,Pplayer);           //~0218I~
//  	   			Sound.play(SOUND_RON,false/*not change to beep when beeponly option is on*/);//~9C01I~//~9C03R~
    	   			Sound.play(SOUNDID_RON,false/*not change to beep when beeponly option is on*/);//~9C03I~
                }                                                  //~9C03I~
                else                                               //~9C02I~
                {                                                  //~9C03I~
//  		        GMsg.showHL(0,GCM_RON,Pplayer);                        //~9C02I~//~9C06R~//~0218R~
    		        GMsg.showHLName(0,GCM_RON,Pplayer);            //~0218I~
//  	   			Sound.play(SOUND_RON,false/*not change to beep when beeponly option is on*/);//~9C03R~
    	   			Sound.play(SOUNDID_RON,false/*not change to beep when beeponly option is on*/);//~9C03I~
                }                                                  //~9C03I~
            }                                                      //~v@@6I~
        }                                                          //~v@@6I~
        if (TestOption.getTimingBTIOErr()==TestOption.BTIOE_AFTER_RON)//~9A28I~
          	TestOption.disableBT();                                //~9A28I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@6I~
    private void showMsgMultiRon(int Pctr)                          //~v@@6I~//~9B11R~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UARon.showMsgMultiRon ctr="+Pctr+",swMultiRon="+swMultiRon+",swMultiRon3Next="+swMultiRon3Next);//~v@@6I~//~9B29R~
        Complete.Status[] stats=AG.aComplete.statusS;   //eswn seq //~v@@6I~//~9B29R~
        StringBuffer sb=new StringBuffer();                         //~v@@6I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@6R~
        {                                                          //~v@@6R~
            if (Dump.Y) Dump.println("UARon.showMsgMultiRon stats ii="+ii+"="+Utils.toString(stats[ii]));//~9B29I~
//          if (stats[ii]!=null || ii==ACC.getCurrentEswn())       //~v@@6R~//~9B29R~
            if (stats[ii]!=null)                                   //~9B29I~
                sb.append(GConst.nameESWN[ii]+" ");                //~v@@6R~//~9B29R~
        }                                                          //~v@@6R~
        if (Pctr==2)	//already described one player             //~v@@6R~
        	sb.append(Utils.getStr(R.string.Ron2));                           //~v@@6I~
        else                                                       //~v@@6I~
        	sb.append(Utils.getStr(R.string.Ron3));                           //~v@@6I~
//      if (Pctr==3 && !swMultiRon3Next)                           //~v@@6R~
        if (Pctr==3 && swMultiRon3Next)                            //~v@@6I~
        {                                                          //~v@@6I~
        	sb.append(","+Utils.getStr(R.string.Info_MultiRon3Drawn));//~v@@6R~
        }                                                          //~v@@6I~
        else                                                       //~v@@6I~
        if (!swMultiRon)                                           //~v@@6I~
        {                                                          //~v@@6I~
            Complete.Status[] sorted=AG.aComplete.sortStatusS();   //~v@@6I~
            sb.append(","+GConst.nameESWN[sorted[0].completeEswn]+" "+Utils.getStr(R.string.Label_ByEswn));//~v@@6R~
        }                                                          //~v@@6I~
//      UserAction.showInfoAllEswn(0/*opt*/,sb.toString());        //~v@@6I~//~9B11R~
//      UserAction.showInfo(0/*opt*/,sb.toString());               //~9B11R~//~9C02R~
        GMsg.showHL(0/*opt*/,sb.toString());                       //~9C02I~
                                                           //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@@I~//~v@@6I~
    public  String makeMsgDataToClient(int Pplayer,TileData[] Ptds,int Pctr)//~v@@@I~//~v@@6I~
    {                                                              //~v@@@I~//~v@@6I~
        if (Dump.Y) Dump.println("UARon.makeMsgDataToClient ctr="+Pctr+",tds="+TileData.toString(Ptds));//~v@@@I~//~v@@6R~
    	return UAPon.makeMsgDataToClient(Pplayer,Ptds,Pctr);       //~v@@6I~
    }                                                              //~v@@@I~//~v@@6I~
	//*************************************************************************//~v@@6I~
    private TileData[] getReceivedPair(int[] PintParm,int Ppos)    //~v@@6R~
    {                                                              //~v@@6I~
	    TileData[] tds=UAPon.getReceivedPair(PintParm,Ppos,-1);    //~v@@6I~
        if (Dump.Y) Dump.println("UARon.getReceivedPair ctr="+tds.length+",tds="+TileData.toString(tds));//~v@@6I~
        return tds;
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@6I~
	//*from UserAction                                             //~9A13I~
	//*************************************************************************//~9A13I~
    public void resetComplete(boolean PswServer,boolean PswReceived,int[] PcompIndexNextPlayer)          //~v@@6I~//~9A13R~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UARon.resetComplete swServer="+PswServer+",swReceived="+PswReceived+",compIndexNextPlayer="+Arrays.toString(PcompIndexNextPlayer));//~v@@6R~//~9A13R~
        Complete.Status[] sorted=AG.aComplete.sortStatusS();       //~v@@6I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@6I~
        	if (PcompIndexNextPlayer[ii]>=0)                       //~v@@6I~
            {                                                      //~v@@6I~
            	Complete.Status stat=sorted[PcompIndexNextPlayer[ii]];   //~v@@6I~
            	resetComplete(PswServer,PswReceived,stat);                                //~v@@6I~//~9A13R~
            }                                                      //~v@@6I~
        TileData[] tds;                                            //~v@@6I~
        int msgid;                                                 //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@6I~
    private void resetComplete(boolean PswServer,boolean PswReceived,Complete.Status Pstat)               //~v@@6R~//~9A13R~//~0227R~
    {                                                              //~v@@6I~
    //***********************                                      //~v@@6I~
    	int eswn=Pstat.completeEswn;                               //~v@@6I~
        int player=Accounts.eswnToPlayer(eswn);                    //~v@@6I~
		if (Dump.Y) Dump.println("UARon.resetComplete compEswn="+eswn+",player="+player+",stat="+Pstat.toString());//~v@@6R~//~0227R~
        if (!Pstat.swErr)        //PcompIndex is by swInavalid and swErr//~0227I~
			Pstat.setInvalid(true);                                //~0227R~
        int typeComplete=0;                                        //~v@@6I~
//      if (PswServer)                                             //~v@@6R~
//      if (swServer)                                              //~v@@6I~//~9A13R~
//      {                                                          //~v@@6I~//~9A13R~
//          typeComplete=PLS.complete(Pplayer);    //insert into Hands//~v@@6R~
//          PLS.setTileRon();  //TDF_RON                           //~v@@6R~
//          tds=PLS.getHands(Pplayer);                             //~v@@6R~
//        	UA.msgDataToClient=makeMsgDataToClient(player,tds,tds.length);//~v@@6R~
//      }                                                          //~v@@6I~//~9A13R~
//      else                                                       //~v@@6I~//~9A13R~
//      {                                                          //~v@@6I~//~9A13R~
//      	if (swReceived)                                       //~v@@6I~//~9A13R~
//          {                                                      //~v@@6I~//~9A13R~
//          	tds=getReceivedPair(PintParm,PARMPOS_TD);          //~v@@6R~
//              if (Pplayer!=PLAYER_YOU)                           //~v@@6R~
//                  PLS.setHandsClientReplace(Pplayer,tds);        //~v@@6R~
//  			typeComplete=PLS.complete(Pplayer,PswReceived);    //~v@@6R~
//              PLS.setTileRon();	//TDF_RON                      //~v@@6R~
//          }                                                      //~v@@6I~//~9A13R~
//      }                                                          //~v@@6I~//~9A13R~
        if (PswServer||PswReceived)                                //~v@@6R~//~9A13R~
        {                                                          //~v@@6R~//~9A13R~
            typeComplete=PLS.resetComplete(player); //set NotNonable//~0227R~
//          AG.aUADelayed.resetWaitAll();   //switch to next player after delay a moment//~v@@6R~
            AG.aUADelayed.resetComplete();   //switch to next player after delay a moment//~v@@6I~
            if (Dump.Y) Dump.println("UARon.complete typeComplete="+Integer.toHexString(typeComplete));//~v@@6I~
//          hands.complete(Pplayer,typeComplete);  //draw discarded tile when river taken//~v@@6R~
            hands.resetComplete(player,typeComplete);  //reset open//~9A13R~
//          river.complete(Pplayer,typeComplete);  //draw discarded tile when river taken//~v@@6R~
//          river.resetComplete(player,typeComplete);  //draw discarded tile when river taken//~v@@6R~
//          AG.aPointStick.complete(Pplayer);   //leave reach stick//~v@@6R~
            AG.aNamePlate.complete(true/*reset*/,player);          //~0303I~
//          AG.aPlayers.resetReachDone(Pplayer);                   //~v@@6R~
//          PLS.resetReachDone(player);      //re-set REACH_DONE    //~v@@6R~//~9A12R~
//          AG.aStock.drawDoraComplete();   //show hidden dora     //~v@@6R~
//          TileData td=PLS.getTileComplete(typeComplete);         //~v@@6R~
//          TileData tdCompKanTake=null;                           //~v@@6R~
//          if ((typeComplete & COMPLETE_KAN_RIVER)!=0)            //~v@@6R~
//          	tdCompKanTake=PLS.getCurrentTaken();               //~v@@6R~
//  		int eswnLooser=td.eswn;                                //~v@@6R~
//          Complete.Status compStat=AG.aComplete.new Status(typeComplete,Accounts.playerToEswn(Pplayer),eswnLooser,td,tdCompKanTake);//~v@@6R~
//          Status.setCompleteStatus(compStat);                    //~v@@6R~
            Status.resetCompleteStatus(Pstat);                     //~v@@6R~
        }                                                          //~v@@6R~//~9A13R~
//        if (PswServer)                                           //~v@@6R~
//        {                                                        //~v@@6R~
//            int ctrComp=AG.aComplete.getCtrComplete();           //~v@@6R~
//            if (ctrComp>1) //already set complete                //~v@@6R~
//            {                                                    //~v@@6R~
//                showMsgMultiRon(ctrComp);                        //~v@@6R~
//            }                                                    //~v@@6R~
//            else                                                 //~v@@6R~
//            {                                                    //~v@@6R~
//                if ((typeComplete &(COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0)//~v@@6R~
//                    msgid=R.string.UserActionMsg_RonTaken;       //~v@@6R~
//                else                                             //~v@@6R~
//                    msgid=R.string.UserActionMsg_RonRiver;       //~v@@6R~
//                UserAction.showInfoAllEswn(0/*opt*/,Pplayer,Utils.getStr(msgid));//~v@@6R~
//            }                                                    //~v@@6R~
//        }                                                        //~v@@6R~
//        return true;                                             //~v@@6R~
    }                                                              //~v@@6I~
	//*************************************************************************//~0205I~
	//* ignore Ronchk                                              //~0205I~
	//* rc:true: dismiss menuInGame                                //~0205I~
	//*************************************************************************//~0205I~
    public static boolean winAnyway()                                     //~0205I~
    {                                                              //~0205I~
    	boolean rc=true;                                           //~0205I~
    //***********************                                      //~0205I~
    	boolean swChk= RuleSettingOperation.isCheckRonable();       //~0205I~
		if (Dump.Y) Dump.println("UARon.winAnyway isCheckRonable="+swChk);//~0205R~
        if (!swChk)	//no ronchk, no need to RON_ANYWAY             //~0205I~
        {                                                          //~0205I~
            UView.showToastLong(R.string.Err_NoNeedRonAnyway);         //~0205I~//~0215R~
        	return false;	//no dismiss                           //~0205I~
        }                                                          //~0205I~
        GameViewHandler.sendMsg(GCM_RON_ANYWAY,null);              //~0205I~
        return rc;                                                 //~0205I~
    }                                                              //~0205I~
}//class                                                           //~v@@@R~
