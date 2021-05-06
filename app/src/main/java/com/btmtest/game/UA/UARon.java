//*CID://+va88R~: update#= 695;                                    //+va88R~
//**********************************************************************//~v101I~
//2021/04/14 va88 chk constraint for TakeRon in notify mode(avoid dump)//~va88R~
//2021/04/13 va85 (Bug)Robot loose envaironment yaku:rinshan,hitei,hotei//~va85I~
//2021/03/30 va71 (Bug)when multi ron called for reach tile,river tile is drawn by stand and lying.//~va71I~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/03/10 va6d (BUG)RYAKU_REACH was lost when ron for discarde calling reach//~va6dI~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/11/21 va49 highlight compreqdlg button when Ron             //~va49I~
//2020/11/04 va29 (BUG)missing to add 1han of OpenReach            //~va29I~
//2020/10/19 va1a drop ronchk option,1han constraint only          //~va1aI~
//2020/10/18 va19 warning use anywan if blocked topn               //~va19I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//2020/05/09 va08:Sound:Ron may delayed for client.                //~va08I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Point;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.CompReqDlg;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.dialog.RuleSettingYaku;
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
import static com.btmtest.dialog.CompReqDlg.*;
import static com.btmtest.game.Complete.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Players.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.UA.Rank.*;

//~v@@@I~
public class UARon                                                 //~v@@@R~//~v@@6R~
{                                                                  //~0914I~
	private static final int DELAYTIME=50; //ms                    //~va08I~
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
//  private UARonChk UARC;                                         //~9C11I~//~va11R~
    private UARonValue UARV;                                       //~va11I~
//  private boolean swCheckRonable;                                //~va11I~//~va1aR~
    private boolean swCheckFix1;                                   //~va11I~
    private boolean swCheckFix2;                                   //~va88I~
    private int completeType,currentEswn;                          //~va11R~
    private boolean swTake;                                        //~va11I~
    private TileData completeTD;                                   //~va11I~
    private boolean swChkCompleteTake;                             //~va88I~
    private TileData tdNotifyTake;                                 //~va88I~
    private int constraintFix2;                                    //~va88I~
//*************************                                        //~v@@@I~
	public UARon(UserAction PuserAction)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~v@@6R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UARon Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~v@@6R~
        AG.aUARon=this;                                            //~va11I~
        UA=PuserAction;                                            //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	public void init()                                             //~v@@@I~
    {                                                              //~v@@@I~
//  	UARC=new UARonChk();                                       //~9C11I~//~va11R~
    	UARV=new UARonValue();                                     //~va11I~
    	AG.aUARonValue=UARV;                                       //~va11I~
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
//      swCheckRonable= RuleSettingOperation.isCheckRonable();     //~va11I~//~va1aR~
        swCheckFix1= RuleSettingOperation.isYakuFix1();            //~va11R~
        constraintFix2=RuleSettingYaku.getYakuFix2Constraint();//~1117I~//~1118I~//~va88I~
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
      if (PactionID==GCM_RON)   //!GCM_RON_ANYWAY                  //~0205I~//~0402R~
        if (!chkComplete(Pplayer))                                  //~9C11R~//~9C12R~
        {                                                          //~9C12I~
//  		GMsg.drawMsgbar(R.string.Err_RonChk);                  //~0205R~//~va11R~
            if (UADL.isBlockedTop())                               //~va19I~
            {                                                      //~va19I~
            	UView.showToastLong(R.string.Warn_BlockedNeedRonAnyway);//~va19I~
            }                                                      //~va19I~
        	return false;                                          //~9C11R~
        }                                                          //~9C12I~
      if (AG.swPlayAloneNotify)                                    //~va70I~
      {                                                            //~va70I~
        if (Dump.Y) Dump.println("UARon.selectInfo playAloneNotify mode not cancelable, rc=true");//~va70I~
      }                                                            //~va70I~
      else                                                         //~va70I~
      {                                                            //~va70I~
        if (!UADL.chkSelectInfo2Touch(PswServer,GCM_RON,Pplayer,PintParm))	//actionAlert optionally//~9B23I~
            return false;                                          //~9B23I~
      }                                                            //~va70I~
        return true;                                               //~9B23I~
    }                                                              //~9B23I~
	//*************************************************************************//~va70I~
	//* from RACall in PlayAloneNotifyMode for human player        //~va70I~
	//*************************************************************************//~va70I~
    public boolean selectInfoPlayAloneNotify()                     //~va70R~
    {                                                              //~va70I~
        if (Dump.Y) Dump.println("UARon.selectInfoPlayAloneNotify");//~va70R~
//    if (PactionID==GCM_RON)   //!GCM_RON_ANYWAY                  //~va70I~
        if (!chkComplete(PLAYER_YOU))                                 //~va70I~
        {                                                          //~va70I~
//          if (UADL.isBlockedTop())                               //~va70I~
//          {                                                      //~va70I~
//          	UView.showToastLong(R.string.Warn_BlockedNeedRonAnyway);//~va70I~
//          }                                                      //~va70I~
        	return false;                                          //~va70I~
        }
//      int[] intp=new int[]{0,0,0,0,0};                           //~va70R~
//      if (!UADL.chkSelectInfo2Touch(true/*PswServer*/,GCM_RON,PLAYER_YOU,intp))	//actionAlert optionally//~va70R~
        UADL.notify2TouchPlayAloneNotify(GCM_RON);	//update GC btn//~va70I~
        return true;                                               //~va70I~
    }                                                              //~va70I~
	//*************************************************************************//~va88I~
	//*when Human taken                                            //~va88I~
	//*************************************************************************//~va88I~
    public boolean selectInfoPlayAloneNotifyTake(TileData PtdTaken)//~va88I~
    {                                                              //~va88I~
        if (Dump.Y) Dump.println("UARon.selectInfoPlayAloneNotifyTake");//~va88I~
        tdNotifyTake=PtdTaken;                                     //~va88I~
        if (!chkCompleteTake(PLAYER_YOU))                          //~va88I~
        {                                                          //~va88I~
        	tdNotifyTake=null;                                     //~va88I~
        	return false;                                          //~va88I~
        }                                                          //~va88I~
        tdNotifyTake=null;                                         //~va88I~
        UADL.notify2TouchPlayAloneNotify(GCM_RON);	//update GC btn//~va88I~
        return true;                                               //~va88I~
    }                                                              //~va88I~
	//*************************************************************************//~va88I~
    private boolean chkCompleteTake(int Pplayer)                   //~va88I~
    {                                                              //~va88I~
        if (Dump.Y) Dump.println("UARon.chkCompleteTake");         //~va88I~
    	swChkCompleteTake=true;                                    //~va88I~
	    boolean rc=chkComplete(Pplayer);                           //~va88I~
    	swChkCompleteTake=false;                                   //~va88I~
        return rc;                                                 //~va88I~
    }                                                              //~va88I~
	//*************************************************************************//~9C11I~
    private boolean chkComplete(int Pplayer)                       //~9C11I~
    {                                                              //~9C11I~
		boolean rc=true;                                           //~va11R~
        if (Dump.Y) Dump.println("UARon.chkComplete player="+Pplayer+",swChkFix1="+swCheckFix1);             //~9C11I~//~va11R~//~va1aR~
//      setForChkRank(Pplayer);                                    //~va11R~
//      if (!swCheckRonable)                                       //~va11R~
//  		return true;                                           //~va11R~
//      if (swCheckRonable)                                          //~va11R~//~va1aR~
//      {                                                            //~va11I~//~va1aR~
////      UARonChk UARC=new UARonChk();                              //~va11R~//~va1aR~
//        rc=UARV.chkComplete(Pplayer);                            //~9C11I~//~9C12R~//~va11R~//~va1aR~
//        if (!rc)                                                   //~va11I~//~va1aR~
//            GMsg.drawMsgbar(R.string.Err_RonChk);                  //~va11I~//~va1aR~
//      }                                                            //~va11I~//~va1aR~
        if (rc)                                                    //~va11R~
        {                                                          //~va11R~
            if (swCheckFix1)                                       //~va11R~
            {                                                      //~va11I~
            	swCheckFix2=isCheckFix2();                         //~va88I~
//              int rc2=UARV.chkRank(this,Pplayer);                //~va11R~//~va88R~
                int rc2;                                           //~va88I~
                if (swChkCompleteTake)                             //~va88I~
    	          	rc2=UARV.chkRank(this,Pplayer,tdNotifyTake,swCheckFix2);//~va88R~
                else                                               //~va88I~
    	          	rc2=UARV.chkRank(this,Pplayer,swCheckFix2);    //~va88R~
                if (rc2!=0)                                        //~va11I~
                {                                                  //~va11I~
                	if (rc2>0)                                     //~va11R~
                    {                                              //~va88I~
                      if (rc2==1)                                  //~va88I~
			  			GMsg.drawMsgbar(R.string.AE_RankFix1);     //~va11R~
                      else                                         //~va88I~
			  			GMsg.drawMsgbar(R.string.AE_RankFix2);     //~va88I~
                    }                                              //~va88I~
                	else                                           //~va11R~
    			  		GMsg.drawMsgbar(R.string.Err_RonChk);      //~va11R~
                	rc=false;                                      //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11R~
        if (Dump.Y) Dump.println("UARon.chkComplete rc="+rc);      //~va70I~
		return rc;                                          //~9C11I~
	}                                                              //~9C11I~
    //*************************************************************************//~va88I~
    private boolean isCheckFix2()                                  //~va88I~
    {                                                              //~va88I~
        boolean rc=false;                                          //~va88I~
        if (constraintFix2>0)                                      //~va88I~
        {                                                          //~va88I~
    		int dupctr=Status.getGameSeq().right;                  //~va88I~
            if (dupctr>=constraintFix2)                            //~va88I~
            	rc=true;                                           //~va88I~
	        if (Dump.Y) Dump.println("UARon.isCheckFix2 dupctr="+dupctr);//~va88I~
        }                                                          //~va88I~
        if (Dump.Y) Dump.println("UARon.isCheckFix2 rc="+rc+",constraintFix2="+constraintFix2);//~va88I~
        return rc;
    }                                                              //~va88I~
//    //*************************************************************************//~va11R~
//    //* set completeType,currentEswn,swTake,completeTD           //~va11R~
//    //*************************************************************************//~va11R~
//    private void setForChkRank(int Pplayer)                      //~va11R~
//    {                                                            //~va11R~
//        completeType=PLS.getCompleteFlag(PLAYER_YOU);            //~va11R~
//        swTake=(completeType & (COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0;//~va11R~
//        currentEswn=Accounts.playerToEswn(PLAYER_YOU);           //~va11I~
//        completeTD=PLS.getTileCompleteSelectInfoRon();           //~va11R~
//        if (Dump.Y) Dump.println("UARon.chkComplete completeType="+completeType+",swTake="+swTake);//~va11R~
//    }                                                            //~va11R~
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
          if (!Status.isIssuedRon())	//dupRon skip 2nd drwa because resetReach called at 1st ron//~va71I~
            river.complete(Pplayer,typeComplete);  //draw discarded tile when river taken//~v@@6R~
            AG.aNamePlate.complete(false/*reset*/,Pplayer);        //~0303M~
            AG.aPointStick.complete(Pplayer);   //erase point stick if just reached//~v@@6R~
//          AG.aAccounts.resetReachDonePay(Pplayer);   //back 1000 //~v@@6R~
//          AG.aPlayers.resetReachDone(Pplayer);                   //~v@@6I~//~va6dR~
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
            AG.aPlayers.resetReachDone(Accounts.eswnToPlayer(eswnLooser));//~va6dI~
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
//  	   			Sound.play(SOUNDID_RON,false/*not change to beep when beeponly option is on*/);//~9C03I~//~va08R~
                }                                                  //~9C03I~
                else                                               //~9C02I~
                {                                                  //~9C03I~
//  		        GMsg.showHL(0,GCM_RON,Pplayer);                        //~9C02I~//~9C06R~//~0218R~
    		        GMsg.showHLName(0,GCM_RON,Pplayer);            //~0218I~
//  	   			Sound.play(SOUND_RON,false/*not change to beep when beeponly option is on*/);//~9C03R~
//  	   			Sound.play(SOUNDID_RON,false/*not change to beep when beeponly option is on*/);//~9C03I~//~va08R~
                }                                                  //~9C03I~
    	   		soundRon(PswServer);                               //~va08I~
            }                                                      //~v@@6I~
        }                                                          //~v@@6I~
        if (TestOption.getTimingBTIOErr()==TestOption.BTIOE_AFTER_RON)//~9A28I~
          	TestOption.disableBT();                                //~9A28I~
//      if (Pplayer==PLAYER_YOU)                                   //~va49R~//~va60R~
        int supporterPlayer=AG.aAccounts.getRealDealerForRobot(Pplayer);//dealer when robot ron//~va60R~
        if (Pplayer==PLAYER_YOU || supporterPlayer==PLAYER_YOU)    //~va60R~
        {                                                          //~va49R~
        	AG.aGC.highlightCompReq(true/*swOn*/);                 //~va49R~
        }                                                          //~va49R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~va08I~
    private void soundRon(boolean PswServer)                       //~va08I~
    {                                                              //~va08I~
        if (Dump.Y) Dump.println("UARon.soundRon swServer="+PswServer);//~va08I~
		if (!PswServer)                                            //~va08I~
			Sound.play(SOUNDID_RON,false/*not change to beep when beeponly option is on*/);//~va08I~
        else                                                       //~va08I~
			Sound.playDelayed(DELAYTIME,SOUNDID_RON,false);        //~va08I~
    }                                                              //~va08I~
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
//  	boolean swChk= RuleSettingOperation.isCheckRonable();       //~0205I~//~va11R~//~va1aR~
        boolean swFix1= RuleSettingOperation.isYakuFix1();         //~va11R~
		if (Dump.Y) Dump.println("UARon.winAnyway swCheckFix1="+swFix1);//~0205R~//~va11R~//~va1aR~
//      if (!swChk)	//no ronchk, no need to RON_ANYWAY             //~0205I~//~va11R~
//      if (!swChk && !swFix1)                                     //~va11R~//~va1aR~
        if (!swFix1)                                               //~va1aI~
        {                                                          //~0205I~
            UView.showToastLong(R.string.Err_NoNeedRonAnyway);         //~0205I~//~0215R~
        	return false;	//no dismiss                           //~0205I~
        }                                                          //~0205I~
        GameViewHandler.sendMsg(GCM_RON_ANYWAY,null);              //~0205I~
        return rc;                                                 //~0205I~
    }                                                              //~0205I~
	//*************************************************************************//~va11I~
	//*************************************************************************//~va11I~
	//*************************************************************************//~va11I~
                                                                   //~va11I~
    //*************************************************************************//~va11I~
    //*from UARonValue.chkEnvironmentYaku<--chkCompleteSub         //~va11I~
    //*************************************************************************//~va11I~
    public void chkEnvironmentYaku(boolean PswAllInHand,TileData PtdRonLast)//~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARon.chkEnvironmentYaku");      //~va11R~
        completeTD=PtdRonLast;                                     //~va11I~
        currentEswn=ACC.getCurrentEswn();                          //~va11I~
      if (swChkCompleteTake)	//from selectInfoPlayAloneNotifyTake for human player take in playAloneNotify mode//~va88I~
      {                                                            //~va88I~
        swTake=true;                                               //~va88I~
        if (Dump.Y) Dump.println("UARon.chkEnvironmentYaku swChkCompleteTake=true");//~va88I~
	  }                                                            //~va88I~
      else                                                         //~va88I~
      {                                                            //~va88I~
        completeType=PLS.getCompleteFlag(PLAYER_YOU);              //~va11I~
        swTake=(completeType & (COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0;//~va11I~
      }                                                            //~va88I~
        if (Dump.Y) Dump.println("UARon.chkEnvironmentYaku currentEswn="+currentEswn+",swTake="+swTake+",completeType="+Integer.toHexString(completeType)+",tdRon="+TileData.toString(completeTD));//~va11I~//~va88R~
    	if (chkTimingYakuman())                                    //~va11I~
        	return;                                                //~va11I~
//      chkReach();  //reach, double-reach, open-reach and taken jasut after reach//~va11I~//~va49R~
        chkReach(PLAYER_YOU);  //reach, double-reach, open-reach and taken jasut after reach//~va49I~
        chkTaken(PswAllInHand);  //tsumo                           //~va11I~
        chkLastTile();	//hitei hotei                              //~va11I~
        chkKan();    //chankan                                     //~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va49I~
    //*From UARonnValue, for Robot player to evaluate ronValue     //~va49I~
    //*************************************************************************//~va49I~
    public void chkEnvironmentYaku(boolean PswAllInHand,TileData PtdRonLast,boolean PswTaken,int Pplayer)//~va49I~
    {                                                              //~va49I~
        if (Dump.Y) Dump.println("UARon.chkEnvironmentYaku player="+Pplayer+",swAllInHand="+PswAllInHand+",ptdLast="+TileData.toString(PtdRonLast));//~va49I~
        completeTD=PtdRonLast;                                     //~va49I~
//      currentEswn=ACC.getCurrentEswn();                          //~va49I~
        currentEswn=Accounts.playerToEswn(Pplayer);                //~va49I~
//      completeType=PLS.getCompleteFlag(PLAYER_YOU);              //~va49I~
        completeType=PLS.getPlayerCompleteFlag(Pplayer);           //~va85I~
//      swTake=(completeType & (COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0;//~va49I~
//      completeType=0;                                            //~va49I~//~va85R~
        swTake=PswTaken;                                           //~va49I~
        if (Dump.Y) Dump.println("UARon.chkEnvironmentYaku currentEswn="+currentEswn+",swTake="+swTake+",tdRon="+TileData.toString(completeTD));//~va49R~
    	if (chkTimingYakuman())                                    //~va49I~
        	return;                                                //~va49I~
//      chkReach();  //reach, double-reach, open-reach and taken jasut after reach//~va49I~
        chkReach(Pplayer);  //reach, double-reach, open-reach and taken jasut after reach//~va49I~
        chkTaken(PswAllInHand);  //tsumo                           //~va49I~
        chkLastTile();	//hitei hotei                              //~va49I~//~va85R~
        chkKan();    //chankan                                     //~va49I~//~va85R~
    }                                                              //~va49I~
    //*************************************************************************//~va11I~
    private boolean chkTimingYakuman()                             //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARon.chkTimingYakuman currentEswn="+currentEswn);        //~va11R~//~va49R~
        boolean rc=false;                                          //~va11I~
//      chk1stTake();           //1st taken availability of 13nopair and 14no pair//~va11R~
        if (chk1stParentTake())     //tenho                        //~va11I~
        	rc=true;                                               //~va11I~
        if (chk1stChildTake())      //chiiho                       //~va11I~
        	rc=true;                                               //~va11I~
        if (chk1stChildRon())       //renho                        //~va11I~
        	rc=true;                                               //~va11I~
        if (chk8ContinuedRon())     //8renchan                     //~va11I~
        	rc=true;                                               //~va11I~
        if (Dump.Y) Dump.println("UARon.chkTimingYakuman rc="+rc); //~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
    private boolean chk1stParentTake()                   //tenho   //~va11I~
    {                                                              //~va11I~
        int ctr=PLS.ctrTakenAll;                                   //~va11R~
        boolean sw=swTake && currentEswn==ESWN_E && ctr==1;        //~va11I~
        if (sw)                                                    //~va11I~
        {                                                          //~va11I~
	        UARV.addTimingYakuman(RYAKU_PARENTTAKE,1/*yakumanRank*/,0/*amt*/);//~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARon.chk1stParentTake sw="+sw+",swTake="+swTake+",currentEswn="+currentEswn+",ctrTakenAll="+ctr);//~va11R~
        return sw;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
    private boolean chk1stChildTake()                    //chiho   //~va11I~
    {                                                              //~va11I~
	    //TODO test ankan                                          //~va11I~
        int ctr=PLS.ctrTakenAll;                                   //~va11R~
        int ctrDiscarded=PLS.ctrDiscardedAll;                      //~va11R~
        boolean sw=swTake && currentEswn!=ESWN_E && ctr==currentEswn+1 && ctrDiscarded==currentEswn/*no pon,kan,chii*/;//~va11I~
        if (sw)                                                    //~va11I~
        {                                                          //~va11I~
        	UARV.addTimingYakuman(RYAKU_CHILDTAKE,1/*yakumanRank*/,0/*amt*/);//~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARon.chk1stChildTake sw="+sw+",swTake="+swTake+",currentEswn="+currentEswn+",ctrTakenAll="+ctr+",ctrDiscarded="+ctrDiscarded);//~va11R~
        return sw;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
    private boolean chk1stChildRon()					//lenho    //~va11I~
    {                                                              //~va11I~
    	boolean rc=false;                                          //~va11I~
        int ctr=PLS.ctrTakenAll;                                   //~va11R~
        int ctrDiscarded=PLS.ctrDiscardedAll;                      //~va11R~
        boolean sw=!swTake && currentEswn!=ESWN_E && ctrDiscarded<=currentEswn && ctr==ctrDiscarded/*no pon,kan,chii*/;//~va11I~
        if (Dump.Y) Dump.println("UARon.chk1stChildRon sw="+sw+",swTake="+swTake+",currentEswn="+currentEswn+",ctrTakenAll="+ctr+",ctrDiscarded="+ctrDiscarded);//~va11R~
        if (sw)                                                    //~va11I~
        {                                                          //~va11I~
        	int idxRank=RuleSettingYaku.getRank1stChildRon(); //0,4(mangan),5,6,7,8(yakuman)//~va11I~
            if (idxRank==RANKIDX_YAKUMAN)    //8                   //~va11I~
            {                                                      //~va11I~
	        	UARV.addTimingYakuman(RYAKU_CHILDRON,1/*yakumanRank*/,0/*amt*/);//~va11I~
                rc=true;                                           //~va11I~
            }                                                      //~va11I~
            else                                                   //~va11I~
            if (idxRank>0)	//0:renho is not used                  //~va11I~
            {                                                      //~va11I~
//	            idxRank--;                                         //~va11I~
			    int rankID= CompReqDlg.intsRank[idxRank]; //10,15   //~va11R~
       		 	int amt=POINT_RANKM*rankID/10;                     //~va11I~
       		 	int han=CompReqDlg.intsRankIdx[idxRank];        //4(mangan),6(haneman),8(dowble),12(triple)//~va11R~
        		if (Dump.Y) Dump.println("UARon.chk1stChildRon idxRank="+idxRank+",amt="+amt+",han="+han);//~va11R~
	        	UARV.addTimingYakuman(RYAKU_CHILDRON_NY,han,amt);  //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
    private boolean chk8ContinuedRon()                  //8renchan //~va11I~
    {                                                              //~va11I~
    	boolean rc=false;                                          //~va11I~
        //TODO set manually                                        //~va11I~
        if (Dump.Y) Dump.println("UARon.8ContinuedRon rc="+rc);    //~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
//  private void chkReach()  //reach, double-reach, open-reach and taken jasut after reach//~va11I~//~va49R~
    private void chkReach(int Pplayer)  //reach, double-reach, open-reach and taken jasut after reach//~va49I~
    {                                                              //~va11I~
	    if (Dump.Y) Dump.println("UARon.chkReach player="+Pplayer);                //~va11R~//~va49R~
//      if (PLS.getReachStatus(PLAYER_YOU)==REACH_DONE)            //~va11R~//~va49R~
        if (PLS.getReachStatus(Pplayer)==REACH_DONE)               //~va49I~
        {                                                          //~va11I~
//      	if (PLS.isOpen(PLAYER_YOU))                         //~va11R~//~va49R~
        	if (PLS.isOpen(Pplayer))                               //~va49I~
	        	UARV.addOtherYaku(RYAKU_REACH_OPEN,RANK_REACH_OPEN);//~va11I~
        	int ctrTaken=PLS.ctrTakenAll;                          //~va11R~
        	int ctrDiscarded=PLS.ctrDiscardedAll;                  //~va11R~
//          Point p=PLS.getCtrReachDone(PLAYER_YOU);               //~va11R~//~va49R~
            Point p=PLS.getCtrReachDone(Pplayer);                  //~va49I~
            int ctrTakenReach=p.x;                                 //~va11I~
    	    int ctrDiscardedReach=p.y;                             //~va11I~
            int diffTaken=ctrTaken-ctrTakenReach;                  //~va11I~
            int diffDiscarded=ctrDiscarded-ctrDiscardedReach;      //~va11I~
	        if (Dump.Y) Dump.println("UARon.chkReach currentEswn="+currentEswn+",ctrTakenReach="+ctrTakenReach+",ctrDiscardedReach="+ctrDiscardedReach+",ctrTaken="+ctrTaken+",ctrDiscarded="+ctrDiscarded);//~va11R~
	        if (ctrTakenReach==currentEswn+1)                      //~va11I~
	        	UARV.addOtherYaku(RYAKU_REACH_DOUBLE,RANK_REACH_DOUBLE);//~va11I~
            else                                                   //~va11I~
	        	UARV.addOtherYaku(RYAKU_REACH,RANK_REACH);         //~va11I~
//          if (PLS.isOpenReach(PLAYER_YOU))                       //~va29I~//~va49R~
            if (PLS.isOpenReach(Pplayer))                          //~va49I~
	        	UARV.addOtherYaku(RYAKU_REACH_OPEN,RANK_REACH_OPEN);//~va29I~
    	  if (RuleSettingYaku.isReachOneShot())                    //~va11I~
            if (swTake)	//                                         //~va11I~
            {                                                      //~va11I~
            	if (diffTaken==PLAYERS && diffTaken==diffDiscarded+1)//~va11I~
		        	UARV.addOtherYaku(RYAKU_REACH_JUST,RANK_REACH_JUST);//~va11I~
            }                                                      //~va11I~
            else                                                   //~va11I~
            {                                                      //~va11I~
            	if (diffDiscarded<=PLAYERS-1 && diffDiscarded==diffTaken)//~va11I~
		        	UARV.addOtherYaku(RYAKU_REACH_JUST,RANK_REACH_JUST);//~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
    private void chkTaken(boolean PswAllInHand)  //tumo and just next take rinshan//~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARon.chkTaken swTake="+swTake+",swAllInHand="+PswAllInHand);//~va11R~
        if (swTake)   //take or kan taken                          //~va11I~
//      	if (PLS.getHandCtr(PLAYER_YOU)==0)                     //~va11R~
//      	if (PLS.isClosedHand(PLAYER_YOU))                      //~va11R~
        	if (PswAllInHand)                                      //~va11I~
				UARV.addOtherYaku(RYAKU_TAKE_NOEARTH,RANK_TAKE_NOEARTH);//~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
    private void chkLastTile()	//hitei hotei                      //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARon.chkLastTile completeTD.flag="+Integer.toHexString(completeTD.flag));//~va11R~
	    if ((completeTD.flag & TDF_LAST)!=0)                       //~va11I~
        	if (swTake)                                            //~va11I~
				UARV.addOtherYaku(RYAKU_LAST_TAKEN,RANK_LAST_TAKEN);//~va11I~
            else                                                   //~va11I~
				UARV.addOtherYaku(RYAKU_LAST_DISCARDED,RANK_LAST_DISCARDED);//~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
    private void chkKan() //chankan,rinshan                        //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARon.chkKan completeType=0x"+Integer.toHexString(completeType));//~va11R~
        if ((completeType & (COMPLETE_KAN_TAKEN|COMPLETE_KAN_RIVER))!=0)//~va11I~
			UARV.addOtherYaku(RYAKU_KAN_TAKEN,RANK_KAN_TAKEN);     //~va11I~
        else                                                       //~va11I~
        if ((completeType & COMPLETE_KAN_ADD)!=0)	//chankan      //~va11I~
			UARV.addOtherYaku(RYAKU_KAN_ADD,RANK_KAN_ADD);         //~va11I~
    }                                                              //~va11I~
}//class                                                           //~v@@@R~
