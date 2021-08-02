//*CID://+vaadR~: update#= 863;                                    //~vaadR~
//**********************************************************************//~v101I~
//2021/06/30 vaad (Bug)PlayAlone mode,did not notify kan if kan not in deal. maintaine ItsHand also for MatcNotify mode//~vaadI~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/05/04 va8A accept dupron for also robot                     //~va8AI~
//2021/04/13 va84 try Robot also ron by 13/14 NoPair               //~va84I~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/03/12 va6g (BUG)suspend/resume reach stick remains if last gane ended ron with anyone reach//~va6gI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/01/07 va60 CalcShanten                                      //~va60I~
//2020/11/03 va27 Tenpai chk at Reach                              //~va27I~
//2020/11/01 va21 move chk1stTake to Players from CompReqDlg because static method mocking is hard//~va21I~
//2020/10/13 va15 Add chk kuikae                                   //~va15I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
import android.graphics.Point;
import android.graphics.Rect;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.UA.UARestart;
import com.btmtest.game.gv.GMsg;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~

import static com.btmtest.TestOption.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.Complete.*;
import static com.btmtest.game.TileData.*;
import java.util.ArrayList;                                        //~v@@@I~
//********************************************************************************************//~v@@5R~
//player:position of each player on the device; You area always 0(Hands is show at bottom)//~v@@5R~
//********************************************************************************************//~v@@5R~
public class Players                                               //~v@@@R~
{                                                                  //~0914I~
   	public static final int REACH_NONE=0;                           //~v@@@R~
   	public static final int REACH_BEFORE_DISCARD=1;                //~v@@@I~
//    public static final int REACH_AFTER_DISCARD=2;                 //~v@@@I~//~v@@6R~
   	public static final int REACH_DONE=3;//~v@@@I~
   	public static final int REACH_NA=9;                            //~v@@@I~
   	public static final int STF_OPEN=0x01;                         //~v@@@I~
   	public static final int STF_REACH=0x02;                        //~v@@@I~
   	public static final int STF_COMPLETE=0x04;                     //~v@@@I~//~9207R~//~9315R~
   	public static final int STF_NOTRONABLE=0x08;    //game continue but err/invalid ron//~9A12I~
//    public static final int REACH_PICKED_FROM_RIVER=4;             //reach tile was taken from river//~v@@@R~
    private static final int ERR_PENDINGHW=R.string.AE_PendingHW;  //~9B14I~
                                                                   //~v@@@I~
	private Player[] players;                                      //~v@@@R~
	public int playerLastDiscarded=-1;                             //~v@@@R~
//  public int nextPlayer,currentPlayer;                           //~v@@@R~
    public int playerCurrent;                                         //~v@@@I~//~9A12R~
//  private boolean swLastActionIsDiscard=false;                   //~v@@@R~
	public int ctrTakenAll; //,ctrKan;                             //~v@@@R~
	public int ctrDiscardedAll;	//discard action ctr of all players                                    //~9624I~//~0404R~
	public TileData tileCurrentTaken;    //also for kan taken when ankan//~v@@@R~//~9A12R~
	public TileData tileLastDiscarded;                             //~v@@@M~
	public TileData tileKanAdded;                                  //~v@@@I~
	public TileData tileComplete;                                  //~9208I~//~9217R~
	public int lastActionID;                                           //~v@@@I~//~9626R~
//  public   int currentActionID;                                  //~9622R~//~9623R~
    public boolean swLastActionIsDiscard;                         //~v@@@I~//~v@@6R~
//  private int typeKan;                                           //~v@@@I~//~9209R~
    private int errReach;                                          //~v@@6I~
    private int completeFlag;                                  //~v@@@I~//~9208I~
    private int kanType;                                           //~9208R~
    private int playerKan;                                         //~9208I~
    public int lastReach=-1;	//player                               //~9511I~
    public int ctrReach;                                           //~9511I~
    private TileData lastReachTD;                                  //~9511I~
    public int actionBeforeRon;                                    //~9A12I~
    private int actionBeforeDrawnHWTemp;                           //~0228I~
    private int actionBeforeDrawnHW;                               //~0228I~
    private int playerBeforeDrawnHWTemp;                           //~0228I~
    private int playerBeforeDrawnHW;                               //~0228I~
    public int actionCurrent;                                      //~9A26I~
    public int actionCurrentCombination;                           //~9B14I~
                                                                   //~9B14I~
    public int typeAction;                                         //~9B14I~
    private static final int AT_STD=1; //standard action           //~9B14I~
    private static final int AT_COMB=2; //combination action       //~9B14I~
                                                                   //~va66I~
    private boolean swManualRobot; //robot take by button intraining mode//~va66R~
//*************************                                        //~v@@@I~
	public Players()                                               //~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("Players Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        AG.aPlayers=this;                                          //~v@@@I~
        players=new Player[PLAYERS];                               //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        	players[ii]=new Player(ii);                           //~v@@@I~
    }                                                              //~0914I~//~v@@@R~
    //*************************************************************************//~va21I~
    //*at ron declared                                             //~va60I~
    //*************************************************************************//~va60I~
//  public boolean chk1stTake()                                    //~va21I~//~va60R~
    public boolean chk1stTakeRon()                                 //~va60I~
    {                                                              //~va21I~
    	boolean rc=false;                                          //~va21I~
//  	int lastAction=AG.aPlayers.actionBeforeRon;                //~va21I~
    	int lastAction=actionBeforeRon;                            //~va21I~
    	boolean swTake=lastAction==GCM_TAKE;                       //~va21I~
        int currentEswn=AG.aAccounts.getCurrentEswn();             //~va21I~
//      int ctrTaken=AG.aPlayers.ctrTakenAll;                      //~va21I~
        int ctrTaken=ctrTakenAll;                                  //~va21I~
//      int ctrDiscarded=AG.aPlayers.ctrDiscardedAll;              //~va21I~
        int ctrDiscarded=ctrDiscardedAll;                          //~va21I~
        boolean swParent=swTake && currentEswn==ESWN_E && ctrTaken==1;//~va21I~
        boolean swChild=swTake && currentEswn!=ESWN_E && ctrTaken==currentEswn+1 && ctrDiscarded==currentEswn/*no pon,kan,chii*/;//~va21I~
        rc=swParent | swChild;                                     //~va21I~
        if (Dump.Y) Dump.println("Players.chk1stTakeRon rc="+rc+",swParent="+swParent+",swChild="+swChild+",lastAction="+lastAction+",swTake="+swTake+",currentEswn="+currentEswn+",ctrTakenAll="+ctrTaken+",ctrDiscardedAll="+ctrDiscarded);//~va21I~//~va60R~
        return rc;                                                 //~va21I~
    }                                                              //~va21I~
    //*************************************************************************//~va84I~
    //*at ron declared, chk for also robot                         //~va84I~
    //*************************************************************************//~va84I~
    public boolean chk1stTakeRon(int Pplayer)                      //~va84I~
    {                                                              //~va84I~
    	boolean rc=false;                                          //~va84I~
    	int lastAction=actionBeforeRon;                            //~va84I~
    	boolean swTake=lastAction==GCM_TAKE;                       //~va84I~
//      int currentEswn=AG.aAccounts.getCurrentEswn();             //~va84I~
        int currentEswn=AG.aAccounts.playerToEswn(Pplayer);        //~va84I~
        int ctrTaken=ctrTakenAll;                                  //~va84I~
        int ctrDiscarded=ctrDiscardedAll;                          //~va84I~
        boolean swParent=swTake && currentEswn==ESWN_E && ctrTaken==1;//~va84I~
        boolean swChild=swTake && currentEswn!=ESWN_E && ctrTaken==currentEswn+1 && ctrDiscarded==currentEswn/*no pon,kan,chii*/;//~va84I~
        rc=swParent | swChild;                                     //~va84I~
        if (Dump.Y) Dump.println("Players.chk1stTakeRon rc="+rc+",swParent="+swParent+",swChild="+swChild+",lastAction="+lastAction+",swTake="+swTake+",currentEswn="+currentEswn+",ctrTakenAll="+ctrTaken+",ctrDiscardedAll="+ctrDiscarded);//~va84I~
        return rc;                                                 //~va84I~
    }                                                              //~va84I~
    //*************************************************************************//~va60I~
    //*at taken                                                    //~va60I~
    //*************************************************************************//~va60I~
    public boolean is1stTake()                                     //~va60I~
    {                                                              //~va60I~
    	boolean rc=false;                                          //~va60I~
//  	int lastAction=actionBeforeRon;                            //~va60I~
//  	boolean swTake=lastAction==GCM_TAKE;                       //~va60I~
        int currentEswn=AG.aAccounts.getCurrentEswn();             //~va60I~
        int ctrTaken=ctrTakenAll;                                  //~va60I~
        int ctrDiscarded=ctrDiscardedAll;                          //~va60I~
//      boolean swParent=swTake && currentEswn==ESWN_E && ctrTaken==1;//~va60I~
        boolean swParent=currentEswn==ESWN_E && ctrTaken==1;       //~va60I~
        boolean swChild=currentEswn!=ESWN_E && ctrTaken==currentEswn+1 && ctrDiscarded==currentEswn/*no pon,kan,chii*/;//~va60I~
        rc=swParent | swChild;                                     //~va60I~
        if (Dump.Y) Dump.println("Players.is1stTake rc="+rc+",swParent="+swParent+",swChild="+swChild+",currentEswn="+currentEswn+",ctrTakenAll="+ctrTaken+",ctrDiscardedAll="+ctrDiscarded);//~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
    //*************************************************************************//~va84I~
    public boolean is1stTakeRobot(int Peswn)                       //~va84I~
    {                                                              //~va84I~
    	boolean rc=false;                                          //~va84I~
//      int currentEswn=AG.aAccounts.getCurrentEswn();             //~va84I~
        int currentEswn=Peswn;                                     //~va84I~
        int ctrTaken=ctrTakenAll;                                  //~va84I~
        int ctrDiscarded=ctrDiscardedAll;                          //~va84I~
        boolean swParent=currentEswn==ESWN_E && ctrTaken==1;       //~va84I~
        boolean swChild=currentEswn!=ESWN_E && ctrTaken==currentEswn+1 && ctrDiscarded==currentEswn/*no pon,kan,chii*/;//~va84I~
        rc=swParent | swChild;                                     //~va84I~
        if (Dump.Y) Dump.println("Players.is1stTakeRobot rc="+rc+",swParent="+swParent+",swChild="+swChild+",currentEswn="+currentEswn+",ctrTakenAll="+ctrTaken+",ctrDiscardedAll="+ctrDiscarded);//~va84I~
        return rc;                                                 //~va84I~
    }                                                              //~va84I~
    //*********************************************************    //~v@@@I~
    public  void newGame(boolean Psw1st,int Pplayer)                        //~v@@@I~//~9503R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.newGame sw1st="+Psw1st+",player="+Pplayer);//~v@@@I~//~9503R~
	    playerCurrent=Pplayer;                                     //~9503I~
        if (AG.swTrainingMode)                                     //~va66I~
	    	swManualRobot= RuleSettingOperation.isAllowRobotAllButton();//~va66R~
        if (Psw1st)                                                //~9503I~
        	return;                                                //~9503I~
        if (ctrTakenAll==0)	//dup call                             //~9503I~
        	return;                                                //~9503I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9503I~
        	players[ii].newGame();                                 //~9503I~
		playerLastDiscarded=-1;                                    //~9503I~
        playerCurrent=AG.aAccounts.eswnToPlayer(ESWN_E);           //~9527I~
		ctrTakenAll=0;                                             //~9503I~
		tileCurrentTaken=null;                                     //~9503I~
		tileLastDiscarded=null;                                    //~9503I~
		tileKanAdded=null;                                         //~9503I~
		tileComplete=null;                                         //~9503I~
		lastActionID=0;                                            //~9503I~
//  	currentActionID=0;                                         //~9622I~//~9623R~
		lastReach=-1;                                              //~9511I~
		lastReachTD=null;                                          //~9511I~
		ctrReach=0;                                                //~9511I~
		ctrDiscardedAll=0;                                            //~9624I~
    	swLastActionIsDiscard=false;                               //~9503I~
//      errReach;                                                  //~9503I~
    	completeFlag=0;                                            //~9503I~
	    kanType=0;                                                 //~9503I~
	    actionCurrent=0;                                           //~9A26I~
	    actionCurrentCombination=0;                                //~9B14I~
	    typeAction=0;                                              //~9B14I~
//      playerKan;                                                 //~9503I~
	    actionBeforeRon=0;                                         //~0228I~
    	actionBeforeDrawnHW=0;                                     //~0228I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@M~
    public static int nextPlayer(int Pplayer)                      //~v@@@M~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("Players.nextPlayer player="+Pplayer+",next="+(Pplayer+1)%PLAYERS);//~v@@@I~
        return (Pplayer+1)%PLAYERS;                                //~v@@@M~
    }                                                              //~v@@@M~
    //*********************************************************    //~v@@6I~
    public int getNextPlayer()                                     //~v@@6I~
    {                                                              //~v@@6I~
    	int next=nextPlayer(playerCurrent);                        //~v@@6I~
        if (Dump.Y) Dump.println("Players.getNextPlayer next="+next);//~v@@6I~
        return next;                                               //~v@@6I~
    }                                                              //~v@@6I~
    //*********************************************************    //~v@@@I~
    //*step offset from base:roll1+roll2-1                         //~v@@@R~
    //*********************************************************    //~v@@@I~
    public static int nextPlayer(int Pbase,int Pstep)              //~v@@@I~
    {                                                              //~v@@@I~
//  	int next=(Pbase+Pstep+PLAYERS*2)%PLAYERS; //*2:ctrGame is 1-8 for EE round//~9513R~//~9607R~
    	int next=(Pbase+Pstep)%PLAYERS;                            //~9607I~
        while (next<0)                                             //~9607I~
            next+=PLAYERS;                                         //~9607I~
        if (Dump.Y) Dump.println("Players.nextPlayer base="+Pbase+",step="+Pstep+",out="+next);//~v@@@I~
        return next;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@M~
    public static int prevPlayer(int Pplayer)                      //~v@@@M~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("Players.prevPlayer player="+Pplayer+",prev="+(Pplayer+PLAYERS-1)%PLAYERS);//~v@@@I~//~v@@6R~
        return (Pplayer+PLAYERS-1)%PLAYERS;                        //~v@@@M~
    }                                                              //~v@@@M~
   //*********************************************************************//~v@@@I~
   //*player is rigt/facinf/left for the player                    //~v@@@I~
   //*********************************************************************//~v@@@I~
	public static int playerRelative(int Ptgt,int Pbase)           //~v@@@R~
    {                                                              //~v@@@I~
    	int player=(Ptgt-Pbase+PLAYERS)%PLAYERS;                   //~v@@@I~
        if (Dump.Y) Dump.println("Players.playerRelative tgt="+Ptgt+",base="+Pbase+",player="+player);//~v@@@R~
        return player;                                             //~v@@@I~
    }                                                              //~v@@@I~
   //*********************************************************************//~v@@@I~
   //*player is rigt/facinf/left for the player                    //~v@@@I~
   //*********************************************************************//~v@@@I~
	public static int playerByDice(int Pdice)                      //~v@@@I~
    {                                                              //~v@@@I~
    	int player=(Pdice-1)%PLAYERS;                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.playerByDice dice="+Pdice+",player="+player);//~v@@@I~
        return player;                                             //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
//	public int nextPlayer()                                        //~v@@@I~
//    {                                                              //~v@@@I~
//    	currentPlayer=nextPlayer;                                  //~v@@@I~
//        if (Dump.Y) Dump.println("Players.nextPlayer "+currentPlayer);//~v@@@R~
//    	nextPlayer=(currentPlayer+1)%PLAYERS;                      //~v@@@I~
//        return currentPlayer;                                      //~v@@@I~
//    }                                                              //~v@@@I~
//    //******************************************************     //~v@@@R~
//    public void setPlayer(int Pplayer)                           //~v@@@R~
//    {                                                            //~v@@@R~
//        nextPlayer=Pplayer;                                      //~v@@@R~
//        nextPlayer();                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Players.setPlayer "+currentPlayer);//~v@@@R~
//    }                                                            //~v@@@R~
    //******************************************************       //~v@@@I~
    //*taken from river                                            //~v@@@I~
    //******************************************************       //~v@@@I~
//  private void setCurrentPlayerWithoutLight(int Pplayer)                     //~v@@@I~//~v@@6R~//~va60R~
    public void setCurrentPlayerWithoutLight(int Pplayer) //public for IT mock//~va60I~
    {                                                              //~v@@@I~
        playerCurrent=Pplayer;                                     //~v@@@I~
        if (Dump.Y) Dump.println("Players.setCurrentPlayerWithoutLight="+playerCurrent);//~v@@@I~//~v@@6R~
    }                                                              //~v@@@I~
    private void setCurrentPlayer(int Pplayer)                     //~v@@6I~
    {                                                              //~v@@6I~
        playerCurrent=Pplayer;                                     //~v@@6I~
        if (Dump.Y) Dump.println("Players.setCurrentPlayer="+playerCurrent);//~v@@6I~
        AG.aDiceBox.setWaitingDiscard(Pplayer);                    //~v@@6I~
    }                                                              //~v@@6I~
    //******************************************************       //~v@@5I~
    private void setCurrentPlayer(int Pplayer,boolean PswShadow)   //~v@@5I~
    {                                                              //~v@@5I~
        playerCurrent=Pplayer;                                     //~v@@5I~
        if (Dump.Y) Dump.println("Players.setCurrentPlayer="+playerCurrent+",shadow="+PswShadow);//~v@@5I~
//      AG.aDiceBox.setWaitingDiscard(Pplayer);                    //~v@@5I~
    }                                                              //~v@@5I~
    //******************************************************       //~v@@@I~
    public int setNextPlayer(int Pplayer)                          //~v@@@I~
    {                                                              //~v@@@I~
    	return setNextPlayer(Pplayer,false/*PswShadow*/);                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    public int setNextPlayer(int Pplayer,boolean PswShadow)        //~v@@@R~
    {                                                              //~v@@@I~
        int next=nextPlayer(Pplayer);                              //~v@@@R~
        if (Dump.Y) Dump.println("Players.setNextPlayer player="+Pplayer+",swShadow="+PswShadow+",next="+next);//~v@@@I~
//      playerCurrent=next;	//curreni will be set at taken         //~v@@@R~
	    AG.aDiceBox.drawLightCurrent(next,PswShadow);              //~v@@@I~
        return next;                                               //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
	public int getCurrentPlayer()                                  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getCurrentPlayer player="+playerCurrent);//~v@@@R~
    	return playerCurrent;                                      //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************************       //~9A26I~//~9B04R~//~9B14R~
    public int getCurrentAction()                                  //~9A26I~//~9B04R~//~9B14R~
    {                                                              //~9A26I~//~9B04R~//~9B14R~
        if (Dump.Y) Dump.println("Players.getCurrentAction action="+actionCurrent);//~9A26I~//~9B04R~//~9B14R~
        return actionCurrent;                                      //~9A26I~//~9B04R~//~9B14R~
    }                                                              //~9A26I~//~9B04R~//~9B14R~
    //******************************************************       //~9625I~
    //*taking(need to discard,contains pon,kan,chii)               //~9625R~
    //*return -1 if playerCurrent discarded                        //~9625I~
    //******************************************************       //~9625I~
	public int getCurrentPlayerTaking()                            //~9625I~
    {                                                              //~9625I~
    	int rc=playerCurrent;                                      //~9625I~
        if (playerLastDiscarded==playerCurrent)                    //~9625I~
        	rc=-1;                                                 //~9625I~
        if (Dump.Y) Dump.println("Players.getCurrentPlayerTaking rc="+rc+",playerCurrent="+playerCurrent+",playerLastDiscarded="+playerLastDiscarded);//~9625I~
    	return rc;                                                 //~9625I~
    }                                                              //~9625I~
    //******************************************************       //~9625I~
    //*chk player is currentPlayerTaking                           //~9625I~
    //******************************************************       //~9625I~
	public boolean isCurrentPlayerTaking(int Pplayer)              //~9625I~
    {                                                              //~9625I~
    	boolean rc=Pplayer==playerCurrent;                         //~9625I~
        if (playerLastDiscarded==Pplayer)                          //~9625I~
        	rc=false;                                              //~9625I~
        if (Dump.Y) Dump.println("Players.isCurrentPlayerTaking Pplayer="+Pplayer+",rc="+rc+",playerCurrent="+playerCurrent+",playerLastDiscarded="+playerLastDiscarded);//~9625I~
    	return rc;                                                 //~9625I~
    }                                                              //~9625I~
   //*********************************************************************//~v@@@R~
   //*for server                                                   //~v@@@I~
   //*********************************************************************//~v@@@I~
//    public void setInitialDeal(int Pplayer,TileData[] Ptd,int Ppos)//~v@@@R~//~v@@5R~
//    {                                                              //~v@@@R~//~v@@5R~
//        if (Dump.Y) Dump.println("Players.setInitialDeal player="+Pplayer+",pos="+Ppos);//~v@@@R~//~v@@5R~
//        players[Pplayer].setInitialDeal(Ptd,Ppos);                 //~v@@@R~//~v@@5R~
//    }                                                              //~v@@@R~//~v@@5R~
    public void setInitialDeal(int Pplayer,TileData[] Ptd,int Ppos,int Peswn,boolean PswSort)//~v@@5R~
    {                                                              //~v@@5I~
        if (Dump.Y) Dump.println("Players.setInitialDeal player="+Pplayer+",pos="+Ppos+",eswn="+Peswn+",swSort="+PswSort);//~v@@5R~
//      players[Pplayer].setInitialDeal(Ptd,Ppos,Peswn);           //~v@@5R~
        players[Pplayer].setInitialDeal(Ptd,Ppos,Peswn,PswSort);   //~v@@5I~
        AG.aRoundStat.deal(Pplayer,Peswn);                         //~va60I~
    }                                                              //~v@@5I~
   //*********************************************************************//~v@@@I~
   //*for client                                                   //~v@@@I~
   //*********************************************************************//~v@@@I~
    public void setInitialDeal(TileData[] Ptd,int Ppos,int Peswn)  //~v@@@R~
    {                                                              //~v@@@I~
//      int player=AG.aAccounts.getPlayerPosOnServer();            //~v@@@R~
        if (Dump.Y) Dump.println("Players.setInitialDeal Peswn="+Peswn+",pos="+Ppos);//~v@@@R~
//      players[player].setInitialDeal(Ptd,Ppos);                  //~v@@@R~
        players[PLAYER_YOU].setInitialDeal(Ptd,Ppos,Peswn);	//set on player[0]//~v@@@I~
        AG.aRoundStat.deal(PLAYER_YOU,Peswn);                      //~vaadI~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public TileData[] getHands(int Pplayer)                        //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getHand player="+Pplayer);//~v@@@I~
    	return players[Pplayer].getHand();                         //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public int getHandCtr(int Pplayer)                             //~v@@@I~
    {                                                              //~v@@@I~
    	int ctr=players[Pplayer].arrayList.size();                 //~v@@@R~
        if (Dump.Y) Dump.println("Players.getHandCtr player="+Pplayer+",ctr="+ctr);//~v@@@I~
    	return ctr;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~9207I~
	public void setHandsClient(int Pplayer,TileData[] Ptds)        //~9207I~
    {                                                              //~9207I~
    	players[Pplayer].setHandsClient(Ptds);                     //~9207I~
    }                                                              //~9207I~
    //*********************************************************************//~9302I~
	public void setHandsClientReplace(int Pplayer,TileData[] Ptds) //~9302I~
    {                                                              //~9302I~
    	players[Pplayer].setHandsClientReplace(Ptds);              //~9302I~
    }                                                              //~9302I~
    //*********************************************************************//~v@@@I~
	public TileData[] getCurrentPair(int Pplayer)                  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getCurrentPair player="+Pplayer);//~v@@@I~
    	return players[Pplayer].getCurrentPair();                  //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public int getCtrPair(int Pplayer)                             //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getCtrPair player="+Pplayer+",ctr="+players[Pplayer].ctrPair);//~v@@@I~
        return players[Pplayer].ctrPair;                           //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
    //*is available to select tile at discard                      //~v@@@I~//~9207R~
    //*********************************************************************//~v@@@I~
    public boolean isTileSelectable(int Pplayer)                   //~v@@@I~
    {                                                              //~v@@@I~
        boolean rc=true;                                           //~v@@@I~
    //***********************                                      //~v@@@I~
        rc=(players[Pplayer].status & (STF_REACH | STF_OPEN))==0   //~v@@@R~
			&& Status.isTileSelectable();                          //~v@@@I~
        if (Dump.Y) Dump.println("Playser.isTileSelectable player="+Pplayer+",rc="+rc+",status=x"+Integer.toHexString(players[Pplayer].status));//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
    public boolean isLastActionIsKan()                             //~v@@@I~
    {                                                              //~v@@@I~
        boolean rc=lastActionID==GCM_KAN;                          //~v@@@I~
        if (Dump.Y) Dump.println("Playser.isLastActionIsKan rc="+rc+",lastaction="+lastActionID);//~v@@@I~//~9A29R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
    public boolean isLastActionIsDiscardable()                     //~v@@@I~
    {                                                              //~v@@@I~
        boolean rc=!(lastActionID==GCM_KAN                         //~v@@@R~
                     ||lastActionID==GCM_DISCARD                     //~v@@@I~
                     ||lastActionID==GCM_RON                         //~v@@@I~
                    );                                             //~v@@@I~
        if (Dump.Y) Dump.println("Playser.isLastActionIsDiscardable lastAction="+lastActionID+",rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~9209I~
    public int getKanType()                                        //~9209I~
    {                                                              //~9209I~
        if (Dump.Y) Dump.println("Playser.getKanType:"+kanType);   //~9209I~
	    return kanType;                                            //~9209I~
    }                                                              //~9209I~
    //*********************************************************************//~9624I~
    public boolean isActionDone(int Pplayer,int PctrTaken,int PctrDiscarded)//~9624I~
    {                                                              //~9624I~
        if (Dump.Y) Dump.println("Players.isActionDone stat currentplayer="+getCurrentPlayer()+",ctrTakenAll="+ctrTakenAll+",ctrDiscardAll="+ctrDiscardedAll);//~9624I~
    	boolean rc=true;                                           //~9624R~
      	if (!Status.isIssuedRon())                                 //~9624I~
    		rc=!(Pplayer==getCurrentPlayer() && PctrTaken==ctrTakenAll && PctrDiscarded==ctrDiscardedAll);//~9624I~
        if (Dump.Y) Dump.println("Players.isActionDone rc="+rc+",parm Pplayer="+Pplayer+",taken="+PctrTaken+",discard="+PctrDiscarded);//~9624I~
	    return rc;                                                 //~9624I~
    }                                                              //~9624I~
    //*********************************************************************//~0404I~
    public boolean isActionDoneExceptRon(int Pplayer,int PctrTaken,int PctrDiscarded)//~0404I~
    {                                                              //~0404I~
        if (Dump.Y) Dump.println("Players.isActionDoneExceptRon stat currentplayer="+getCurrentPlayer()+",ctrTakenAll="+ctrTakenAll+",ctrDiscardAll="+ctrDiscardedAll);//~0404I~
    	boolean rc=true;                                           //~0404I~
//    	if (!Status.isIssuedRon())                                 //~0404I~
    		rc=!(Pplayer==getCurrentPlayer() && PctrTaken==ctrTakenAll && PctrDiscarded==ctrDiscardedAll);//~0404I~
        if (Dump.Y) Dump.println("Players.isActionDoneExceptRon rc="+rc+",parm Pplayer="+Pplayer+",taken="+PctrTaken+",discard="+PctrDiscarded);//~0404I~
	    return rc;                                                 //~0404I~
    }                                                              //~0404I~
    //*********************************************************************//~va66I~
    public int getCtrReachedPlayer(int PplayerExcept/*-1 count all*/)//~va66I~
    {                                                              //~va66I~
    	int ctr=0;                                                 //~va66I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~va66I~
	        if (players[ii].reachStatus==REACH_DONE)               //~va66I~
            	ctr++;                                             //~va66I~
        if (Dump.Y) Dump.println("Player.getCtrReachedPlayer player="+PplayerExcept+",rc="+ctr);//~va66I~
        return ctr;                                                //~va66I~
    }                                                              //~va66I~
    //*********************************************************************//~v@@@I~
    public boolean isYourTurn(int PactionID,int Pplayer,int PprevActionID)//~v@@@R~
    {                                                              //~v@@@I~
	    return isYourTurn(true,PactionID,Pplayer,PprevActionID);   //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~9A13I~
    private int chkRonable(int Pplayer)                            //~9A13I~
    {                                                              //~9A13I~
    	int errmsg;                                                //~9A13I~
		if ((players[Pplayer].status & STF_NOTRONABLE)!=0) //dup button//~9A13I~
		    errmsg=R.string.AE_ContinueAsNotRonable;             //~9A13I~
        else                                                       //~9A13I~
            errmsg=0;                                              //~9A13I~
        if (Dump.Y) Dump.println("Players.chkRonable rc="+Integer.toHexString(errmsg));//~9A13R~
        return errmsg;                                             //~9A13I~
    }                                                              //~9A13I~
    //*********************************************************************//~9B20I~
    private void errNotInGame(int Pplayer)                       //~9B20I~//~0111R~
    {                                                              //~9B20I~
        int eswn=Accounts.playerToEswn(Pplayer);                   //~9B20I~
        String msg=Utils.getStr(R.string.Err_NotGamingNow,nameESWN[eswn]);//~9B20I~
        if (Dump.Y) Dump.println("Players.errNotInGame player="+Pplayer+",eswn="+eswn+",msg="+msg);//~9B20I~
      	GC.actionError(0,Pplayer,msg);                             //~9B20I~
    }                                                              //~9B20I~
    //*********************************************************************//~v@@@I~
    public boolean isYourTurn(boolean PswMsg,int PactionID,int Pplayer,int PprevActionID)//~v@@@I~
    {                                                              //~v@@@I~
        boolean rc=true;                                           //~v@@@I~
        int errmsgid,errmsgidNotRonable;                                              //~v@@6R~//~9A13R~
        int errmsgidLast;                                          //~9225I~
        int typeAction=0;                                          //~9B14I~
        int emsg;                                                  //~9C06I~
    //***********************                                      //~v@@@I~
        if (Dump.Y) Dump.println("Players.isYourTurn swMsg="+PswMsg+",PactionID="+PactionID+",player="+Pplayer+",prevaction="+PprevActionID);//~v@@@I~
		actionBeforeDrawnHWTemp=0;	//isPendingHW() called         //~0228I~
//    	actionCurrent=0;                                           //~9A26I~//~9B14R~
		if (!Status.isAvailableUserAction())                       //~v@@@I~
        {                                                          //~v@@@I~
          if (UARestart.chkUserAction(PactionID))	//take,discard,pon,...//~0111I~
          {                                                        //~0111I~
//      	GC.actionError(0,Pplayer,"Not in gameStaus player="+Pplayer+",status="+Status.getGameStatus());//~v@@@R~//~9B20R~
        	errNotInGame(Pplayer);                                 //~9B20I~
            return false;                                          //~v@@@I~
          }                                                        //~0111I~
        }                                                          //~v@@@I~
//      currentActionID=PactionID;                                 //~9622I~//~9623R~
	    errmsgid=R.string.AE_NotYourTurn;                          //~v@@6I~
        lastActionID=PprevActionID;                                //~v@@@I~
        players[Pplayer].lastActionID=lastActionID;                //~v@@@I~
        swLastActionIsDiscard=lastActionID==GCM_DISCARD;           //~v@@@I~
        int cp=getCurrentPlayer();    //-1 if discarded            //~v@@@I~//~9C07R~
//        if (PswInterceptAction) //pon,chii,kan                   //~v@@@R~
//        {                                                        //~v@@@R~
//            rc=swLastActionIsDiscard && (Pplayer!=playerLastDiscarded)//~v@@@R~
//                && getReachStatus(Pplayer)==REACH_NONE;          //~v@@@R~
//        }                                                        //~v@@@R~
//        else                    //reach,takeone,discard          //~v@@@R~
//        {                                                        //~v@@@R~
		boolean swIgnore=false;                                    //~9208I~
      if (PactionID!=GCM_RON && Status.isIssuedRon())                                    //~9207R~//~9222R~
      {                                                            //~9226I~
//        switch(PactionID)                                          //~9318I~//~9B29R~
//        {                                                          //~9318I~//~9B29R~
//            case GCM_ENDGAME_SCORE:                                    //~9318I~//~9B29R~
//            case GCM_ENDGAME_ACCOUNTS:                             //~9322I~//~9B29R~
//            case GCM_SUSPENDDLG:                                   //~9822R~//~9B29R~
//            case GCM_SUSPENDDLG_IOERR:                             //~9A21I~//~9B29R~
//            case GCM_2TOUCH:                                     //~9B29I~
//            swIgnore=true;                                         //~9318I~//~9B29R~
//            break;                                                 //~9318I~//~9B29R~
//        default:                                                   //~9318I~//~9B29R~
//            rc=false;                                                  //~9207I~//~9318R~//~9B29R~
//            errmsgid=R.string.AE_AfterIssuedRon;                       //~9226I~//~9318R~//~9B29R~
//        }                                                          //~9318I~//~9B29R~
        switch(PactionID)                                          //~9B29I~
        {                                                          //~9B29I~
        case GCM_TAKE:                                             //~9B29I~
        case GCM_PON:                                              //~9B29I~
        case GCM_CHII:                                              //~9B29I~
        case GCM_KAN:                                              //~9B29I~
        case GCM_DISCARD:                                          //~9B29I~
        case GCM_REACH:                                            //~9B29I~
        case GCM_REACH_OPEN:                                       //~9B29I~
            rc=false;                                              //~9B29I~
            errmsgid=R.string.AE_AfterIssuedRon;                   //~9B29I~
            break;                                                 //~9B29I~
        default:                                                   //~9B29I~
			swIgnore=true;                                         //~9B29I~
        }                                                          //~9B29I~
      }                                                            //~9226I~
      else                                                         //~9207I~
      {                                                            //~9207I~
        switch(PactionID)                                          //~v@@@R~
        {                                                          //~v@@@R~
            case GCM_TAKE:                                         //~v@@@M~
            	typeAction=AT_STD;                                 //~9B14R~
                emsg=AG.aUADelayed.isYourTurn(PactionID,Pplayer);  //~9C06R~
                if (emsg!=0)                                       //~9C06R~
                {                                                  //~9C06I~
                	errmsgid=emsg;                                 //~9C06I~
                    rc=false;                                      //~9C06I~
                	break;                                         //~9C06I~
                }                                                  //~9C06I~
                if (isPendingHW(PactionID,Pplayer))                //~0228I~
                {                                                  //~0228I~
                    rc=false;                                      //~0228I~
                    errmsgid=ERR_PENDINGHW;                        //~0228I~
                    break;                                         //~0228I~
                }                                                  //~0228I~
                if (ctrTakenAll==0)            //~v@@@R~           //~9527R~
                	playerCurrent=Pplayer;                         //~9527R~
                else                                               //~v@@@I~
                if (isLastActionIsKan())                           //~9527I~
                	rc=Pplayer==cp;                                //~9527I~
                else                                               //~9527I~
                {                                                  //~v@@6I~
//                  if (isPendingHW(PactionID,Pplayer))                             //~9B14I~//~0228R~
//                  {                                              //~9B14I~//~0228R~
//                      rc=false;                                  //~9B14I~//~0228R~
//                      errmsgid=ERR_PENDINGHW;                    //~9B14I~//~0228R~
//                      break;                                     //~9B14I~//~0228R~
//                  }                                              //~9B14I~//~0228R~
                	rc = (Pplayer == nextPlayer(cp)) && swLastActionIsDiscard;//~v@@@R~
		        	if (Dump.Y) Dump.println("Players.isYourTurn GCM_TAKE rc="+rc+",Pplayer="+Pplayer+",swLastActionIsDiscard="+swLastActionIsDiscard+",playerCurrent="+playerCurrent+"swTrainingMode="+AG.swTrainingMode+",swManualRobot="+swManualRobot);//~va66R~
                    if (!rc)                                       //~v@@6I~
                    {                                              //~v@@6I~
                    	if (Pplayer==playerCurrent && !swLastActionIsDiscard)//~v@@6R~
                        {                                          //~9225I~
						  if (swManualRobot)                       //~va66R~
                          {                                        //~va66I~
					        GC.actionError(0,Pplayer,GMsg.editEswn(R.string.AE_DiscardTakenRobot,Pplayer));//~va66I~
                            errmsgid=-1;	//issued               //~va66I~
                          }                                        //~va66I~
                          else                                     //~va66I~
		        			errmsgid=R.string.AE_DiscardTaken;     //~v@@6I~
                            break;                                 //~9225I~
                        }                                          //~9225I~
                    }                                              //~v@@6I~
                    else                                           //~9225I~
		        	if (AG.aTiles.chkLast())                                 //~9225I~
                    {                                              //~9225I~
			        	errmsgid=R.string.AE_ReachedToLast;        //~9225I~
	                    rc=false;                                  //~9225I~
                        break;                                     //~9225I~
                    }                                              //~9225I~
                }                                                  //~v@@6I~
                break;                                             //~v@@@M~
            case GCM_PON:                                          //~v@@@I~
//          case GCM_PON_C:                                      //~9B16R~//~9B17R~//~9B18R~
            	typeAction=AT_STD;                                 //~9B14R~
                emsg=AG.aUADelayed.isYourTurn(PactionID,Pplayer);  //~9C07I~
                if (emsg!=0)                                       //~9C07I~
                {                                                  //~9C07I~
                	errmsgid=emsg;                                 //~9C07I~
                    rc=false;                                      //~9C07I~
                	break;                                         //~9C07I~
                }                                                  //~9C07I~
				if (isPendingHW(PactionID,Pplayer))                                 //~9B14I~//~0228R~
                {                                                  //~9B14I~
                    rc=false;                                      //~9B14I~
		        	errmsgid=ERR_PENDINGHW;                        //~9B14I~
                	break;                                         //~9B14I~
                }                                                  //~9B14I~
				if ((errmsgidNotRonable=chkRonable(Pplayer))!=0)   //~9A13R~
                {                                                  //~9A13I~
                    rc=false;                                      //~9A13I~
		        	errmsgid=errmsgidNotRonable;                   //~9A13I~
                	break;                                         //~9A13I~
                }                                                  //~9A13I~
                if (Pplayer == cp && isLastActionIsDiscardable())  //~9C07I~
                {                                                  //~9C07I~
		        	errmsgid=R.string.AE_DiscardTaken;             //~9C07I~
                    rc=false;                                      //~9C07I~
                    break;                                         //~9C07I~
                }                                                  //~9C07I~
                rc=swLastActionIsDiscard && (Pplayer!=playerLastDiscarded);//~v@@@I~//~v@@6R~
                if (rc)                                            //~v@@6I~
                {                                                  //~v@@6I~
                    if (getReachStatus(Pplayer)!=REACH_NONE)       //~v@@6R~
                    {                                              //~v@@6I~
                    	rc=false;                                  //~v@@6I~
		        		errmsgid=R.string.AE_InReach;              //~v@@6I~
                        break;                                     //~9225I~
                    }                                              //~v@@6I~
		        	errmsgidLast=chkLast(false/*swReach*/);        //~9225R~
                    if (errmsgidLast!=0)                           //~9225R~
                    {                                              //~9225I~
			        	errmsgid=errmsgidLast;                     //~9225I~
	                    rc=false;                                  //~9225R~
                        break;                                     //~9225I~
                    }                                              //~9225I~
                }                                                  //~v@@6I~
                break;                                             //~v@@@I~
            case GCM_CHII:                                         //~v@@@I~
            	typeAction=AT_STD;                                 //~9B14R~
                emsg=AG.aUADelayed.isYourTurn(PactionID,Pplayer);  //~9C06R~
                if (emsg!=0)                                       //~9C06R~
                {                                                  //~9C06I~
                	errmsgid=emsg;                                 //~9C06I~
                    rc=false;                                      //~9C06I~
                	break;                                         //~9C06I~
                }                                                  //~9C06I~
				if (isPendingHW(PactionID,Pplayer))                                 //~9B14I~//~0228R~
                {                                                  //~9B14I~
                    rc=false;                                      //~9B14I~
		        	errmsgid=ERR_PENDINGHW;                        //~9B14I~
                	break;                                         //~9B14I~
                }                                                  //~9B14I~
				if ((errmsgidNotRonable=chkRonable(Pplayer))!=0)   //~9A13I~
                {                                                  //~9A13I~
                    rc=false;                                      //~9A13I~
		        	errmsgid=errmsgidNotRonable;                   //~9A13I~
                	break;                                         //~9A13I~
                }                                                  //~9A13I~
                if (Pplayer == cp && isLastActionIsDiscardable())  //~9C07R~
                {                                                  //~9C07I~
		        	errmsgid=R.string.AE_DiscardTaken;             //~9C07I~
                    rc=false;                                      //~9C07I~
                    break;                                         //~9C07I~
                }                                                  //~9C07I~
//              rc=swLastActionIsDiscard && (Pplayer==playerCurrent)//~v@@@R~
                rc=swLastActionIsDiscard && (Pplayer==nextPlayer(playerCurrent));//~v@@@I~//~v@@6R~
                if (rc)                                            //~v@@6I~
                {                                                  //~v@@6I~
                    if (getReachStatus(Pplayer)!=REACH_NONE)       //~v@@6I~
                    {                                              //~v@@6I~
                    	rc=false;                                  //~v@@6I~
		        		errmsgid=R.string.AE_InReach;              //~v@@6I~
                        break;                                     //~9225I~
                    }                                              //~v@@6I~
		        	errmsgidLast=chkLast(false/*swReach*/);        //~9225I~
                    if (errmsgidLast!=0)                           //~9225I~
                    {                                              //~9225I~
			        	errmsgid=errmsgidLast;                     //~9225I~
	                    rc=false;                                  //~9225I~
                        break;                                     //~9225I~
                    }                                              //~9225I~
                }                                                  //~v@@6I~
                break;                                             //~v@@@I~
            case GCM_KAN:                                          //~v@@@I~
            	typeAction=AT_STD;                                 //~9B14R~
                emsg=AG.aUADelayed.isYourTurn(PactionID,Pplayer);  //~9C06I~
                if (emsg!=0)                                       //~9C06I~
                {                                                  //~9C06I~
                	errmsgid=emsg;                                 //~9C06I~
                    rc=false;                                      //~9C06I~
                	break;                                         //~9C06I~
                }                                                  //~9C06I~
				if (isPendingHW(PactionID,Pplayer))                                 //~9B14I~//~0228R~
                {                                                  //~9B14I~
                    rc=false;                                      //~9B14I~
		        	errmsgid=ERR_PENDINGHW;                        //~9B14I~
                	break;                                         //~9B14I~
                }                                                  //~9B14I~
				if ((errmsgidNotRonable=chkRonable(Pplayer))!=0)   //~9A13I~
                {                                                  //~9A13I~
                    rc=false;                                      //~9A13I~
		        	errmsgid=errmsgidNotRonable;                   //~9A13I~
                	break;                                         //~9A13I~
                }                                                  //~9A13I~
            	if (swLastActionIsDiscard)                         //~v@@@I~
                {                                                  //~v@@6I~
               		rc=(Pplayer!=playerLastDiscarded);             //~v@@6R~
                	if (rc)                                        //~v@@6I~
                	{                                              //~v@@6I~
                        if (getReachStatus(Pplayer)!=REACH_NONE)   //~v@@6I~
                        {                                          //~v@@6I~
                            rc=false;                              //~v@@6I~
                            errmsgid=R.string.AE_InReach;          //~v@@6I~
		                    break;                                 //~9225I~
                        }                                          //~v@@6I~
                    }                                              //~v@@6I~
                }                                                  //~v@@6I~
                else                                               //~v@@@I~
                {                                                  //~9218I~
                	rc=(Pplayer==playerCurrent);                    //~v@@@I~
                    if (rc)                                        //~9218I~
                    {                                              //~9218I~
                	  if (lastActionID==GCM_TAKE)	               //~9C06I~
                      {                                            //~9C06I~
                        if (getReachStatus(Pplayer)!=REACH_NONE    //~9218I~
//                      &&  !RuleSetting.isAvailableKanAfterReach())//~9218I~//~9530R~
                        &&  !RuleSettingYaku.isAvailableKanAfterReach())//~9530I~
                        {                                          //~9218I~
                            rc=false;                              //~9218I~
                            errmsgid=R.string.AE_KanAfterReach;    //~9218I~
		                    break;                                 //~9225I~
                        }                                          //~9218I~
                      }                                            //~9C06I~
                      else                                         //~9C06I~
                      {                                            //~9C06I~
		        		errmsgid=R.string.AE_DiscardTaken;         //~9C07I~
                      	rc=false;                                  //~9C06I~
                        break;                                     //~9C06I~
                      }                                            //~9C06I~
                    }                                              //~9218I~
		        	if (Dump.Y) Dump.println("Players.isYourTurn Kan lastActionID="+lastActionID+",playerCurrent="+playerCurrent);//~9C06I~
                }                                                  //~9218I~
                errmsgidLast=chkLast(false/*swReach*/);            //~9225I~
                if (errmsgidLast!=0)                               //~9225I~
                {                                                  //~9225I~
                    errmsgid=errmsgidLast;                         //~9225I~
                    rc=false;                                      //~9225I~
                    break;                                         //~9225I~
                }                                                  //~9225I~
                break;                                             //~v@@@I~
            case GCM_DISCARD:                                      //~v@@@M~
            	typeAction=AT_STD;                                 //~9B14R~
                emsg=AG.aUADelayed.isYourTurn(PactionID,Pplayer);//~9C06R~
                if (emsg!=0)                                       //~9C06R~
                {                                                  //~9C06I~
                	errmsgid=emsg;                                 //~9C06I~
                    rc=false;                                      //~9C06I~
                	break;                                         //~9C06I~
                }                                                  //~9C06I~
				if (isPendingHW(PactionID,Pplayer))                //~0228R~
                {                                                  //~0228I~
                    rc=false;                                      //~0228I~
		        	errmsgid=ERR_PENDINGHW;                        //~0228I~
                	break;                                         //~0228I~
                }                                                  //~0228I~
                rc = Pplayer == cp && isLastActionIsDiscardable();//~v@@@R~
//                if (!rc)                                           //~v@@6R~//~9C07R~
//                {                                                  //~v@@6I~//~9C07R~
//                    if (Pplayer == nextPlayer(cp))                 //~v@@6I~//~9C07R~
//                        errmsgid=R.string.AE_YouHaveToTake;        //~v@@6I~//~9C07R~
//                }                                                  //~v@@6I~//~9C07R~
				if (!rc)                                           //~va66I~
                {                                                  //~va66I~
                    if ((TestOption.option2 & TO2_ROBOT_DISCARD_BUTTON)!=0)//~va66I~
                    {                                              //~va66I~
						if (lastActionID==GCM_KAN)                 //~va66I~
                        {                                          //~va66I~
					        GC.actionError(0,Pplayer,GMsg.editEswn(R.string.AE_TakeKanAdditionalRobot,Pplayer));//~va66I~
                            errmsgid=-1;	//issued               //~va66I~
                        }                                          //~va66I~
						if (lastActionID==GCM_DISCARD)             //~va66I~
                        {                                          //~va66I~
					        GC.actionError(0,Pplayer, GMsg.editEswn(R.string.AE_NextHaveToTakeRobot,nextPlayer(Pplayer)));//~va66I~
                            errmsgid=-1;	//issued               //~va66I~
                        }                                          //~va66I~
                    }                                              //~va66I~
                }                                                  //~va66I~
                break;                                             //~v@@@M~
            case GCM_REACH:                                        //~v@@@R~
            case GCM_REACH_OPEN:                                   //~9301I~
            	typeAction=AT_COMB;                                //~9B14I~
				if ((errmsgidNotRonable=chkRonable(Pplayer))!=0)   //~9A13I~
                {                                                  //~9A13I~
                    rc=false;                                      //~9A13I~
		        	errmsgid=errmsgidNotRonable;                   //~9A13I~
                	break;                                         //~9A13I~
                }                                                  //~9A13I~
            	if (PactionID==GCM_REACH_OPEN)                     //~9427I~
                {                                                  //~9427I~
                	if (!RuleSettingYaku.isAvailableOpenReach())       //~9427I~
                    {                                              //~9427I~
	                    errmsgid=R.string.AE_UnavailableOpenReach; //~9427I~
                    	rc=false;                                  //~9427I~
                    	break;                                     //~9427I~
                    }                                              //~9427I~
                }                                                  //~9427I~
                rc = players[Pplayer].isReachAvailable();          //~v@@@R~
                if (!rc)                                           //~v@@6I~
                {                                                  //~v@@6I~
                	if (errReach!=0)                               //~v@@6I~
		        		errmsgid=errReach;                         //~v@@6I~
                    break;                                         //~9225I~
                }                                                  //~v@@6I~
                errmsgidLast=chkLast(true/*swReach*/);  //DrawnLst chk//~9C06R~
                if (errmsgidLast!=0)                               //~9225M~
                {                                                  //~9225M~
                    errmsgid=errmsgidLast;                         //~9225M~
                    rc=false;                                      //~9225M~
                    break;                                         //~9225M~
                }                                                  //~9225M~
                break;                                             //~v@@@R~
            case GCM_RON:                                          //~v@@@R~
            	typeAction=AT_STD;                                 //~9B14I~
                emsg=AG.aUADelayed.isYourTurn(PactionID,Pplayer);  //~9C06I~
                if (emsg!=0)                                       //~9C06I~
                {                                                  //~9C06I~
                	errmsgid=emsg;                                 //~9C06I~
                    rc=false;                                      //~9C06I~
                	break;                                         //~9C06I~
                }                                                  //~9C06I~
				if ((errmsgidNotRonable=chkRonable(Pplayer))!=0)   //~9A13I~
                {                                                  //~9A13I~
                    rc=false;                                      //~9A13I~
		        	errmsgid=errmsgidNotRonable;                   //~9A13I~
                	break;                                         //~9A13I~
                }                                                  //~9A13I~
	        	if (Dump.Y) Dump.println("Players.isYourTurn Ron kanType="+kanType+",playerKan="+playerKan+",playerLastDiscarded="+playerLastDiscarded);//~9208I~
//              rc = Pplayer != playerLastDiscarded;               //~v@@@R~//~9208R~
//              errmsgid=isRonAvailable(Pplayer);                  //~9208R~//~9315R~//~9C06R~
                emsg=isRonAvailable(Pplayer);                      //~9C06I~
//              if (errmsgid!=0)                                           //~v@@@R~//~9208R~//~9315R~//~9C06R~
                if (emsg!=0)                                       //~9C06I~
                {                                                  //~9208I~
                    errmsgid=emsg;                                 //~9C06I~
                	rc=false;                                      //~9208I~
                    break;                                         //~v@@@R~
                }                                                  //~9208I~
                rc = players[Pplayer].chkComplete();               //~v@@@R~
                if (lastActionID!=GCM_RON)	//for multi Ron        //~9B11I~
	                actionBeforeRon=lastActionID;                      //~9A12I~//~9B11R~
                break;                                             //~v@@@R~
//          case GCM_OPEN:                                         //~v@@@I~//~9301R~
//              rc = Pplayer == cp && (!swLastActionIsDiscard && ctrTakenAll != 0);//~v@@@I~//~9301R~
//              break;                                             //~v@@@I~//~9301R~
            default:                                               //~9208I~
            	swIgnore=true;                                     //~9208I~
        }                                                          //~v@@@R~
//        }                                                        //~v@@@R~
	  }//!issuedRon                                                //~9207I~
      	if (!swIgnore)                                             //~9208I~
        {                                                          //~9208I~
        	if (Pplayer==playerCurrent && Pplayer==PLAYER_YOU)     //~9208R~
            {                                                      //~9208I~
                if (isLastActionIsKan())                           //~9208I~
                {                                                  //~9208R~
                    if (PactionID!=GCM_TAKE)                       //~9208R~
                    {                                              //~9208R~
                        errmsgid=R.string.AE_TakeKanAdditional;    //~9208R~
                        rc=false;                                  //~9208R~
                    }                                              //~9208R~
                }                                                  //~9208R~
            }                                                      //~9208I~
        }                                                          //~9208I~
        if (Dump.Y) Dump.println("Players.isYourTurn rc="+rc+",player="+Pplayer);//~v@@@I~//~9225M~
        if (Dump.Y) Dump.println("swLastDiscard="+swLastActionIsDiscard+",ctrTakenAll="+ctrTakenAll+",reachStatus="+getReachStatus(Pplayer));//~v@@@R~//~9225M~
        if (Dump.Y) Dump.println("playerLastDiscarded="+playerLastDiscarded);//~v@@@I~//~9225M~
        if (!rc)                                                   //~v@@@I~
        {                                                          //~v@@@I~
            if (errmsgid>0) //-1 for err but no msg                //~9315I~
            {                                                      //~9315I~
        	if (Dump.Y) Dump.println("Players.isYourTurn set err player="+Pplayer+",currentPlayer="+cp+",action="+PactionID+",lastActionID="+PprevActionID);//~v@@@R~//~va60R~
        	if (PswMsg)                                            //~v@@@I~
            {                                                      //~v@@6I~
	        	GC.actionError(0,Pplayer,errmsgid); //~v@@@R~      //~v@@6R~
            }                                                      //~v@@6I~
            }                                                      //~9315I~
        }                                                          //~v@@@I~
        else                                                     //~v@@@R~//~9A26R~
        {                                                          //~9A26I~
//            lastActionID=PactionID;                              //~v@@@R~
//			if (!swIgnore)                                         //~9A26I~//~9B14R~
//				actionCurrent=PactionID;                           //~9A26I~//~9B14R~
			if (typeAction==AT_STD)                                //~9B14I~
				actionCurrent=PactionID;                           //~9B14I~
            else                                                   //~9B14I~
			if (typeAction==AT_COMB)                               //~9B14I~
				actionCurrentCombination=PactionID;                //~9B14I~
		    if (actionBeforeDrawnHWTemp!=0)	//isPendingHW() called //~0228I~
            {                                                      //~0228I~
		    	actionBeforeDrawnHW=actionBeforeDrawnHWTemp;       //~0228R~
		    	playerBeforeDrawnHW=playerBeforeDrawnHWTemp;       //~0228R~
            }                                                      //~0228I~
        	if (Dump.Y) Dump.println("Players.isYourTurn rc=0 typeAction="+typeAction+",actionCurrent="+actionCurrent+",actionCurrentCombination="+actionCurrentCombination+",swIgnore="+swIgnore+",PactionID="+PactionID+",actionBeforeDrawnHW="+actionBeforeDrawnHW+",playerBeforeDrawnHW="+playerBeforeDrawnHW);//~9A26I~//~9B14R~//~0228R~//~0403R~
		}                                                          //~9A26I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~9B14I~
    private boolean isPendingHW(int PactionID,int Pplayer)                                  //~9B14I~//~0228R~
    {                                                              //~9B14I~
    	boolean rc=AG.aUADelayed.isPendingHW();                    //~9B14I~
        if (Dump.Y) Dump.println("Players.isPendingHW rc="+rc+",action="+PactionID+",Player="+Pplayer+",playerCurrent="+playerCurrent);    //~9B14I~//~0228R~
	    actionBeforeDrawnHWTemp=PactionID;                         //~0228I~
	    playerBeforeDrawnHWTemp=Pplayer;                           //~0228R~
        return rc;                                                 //~9B14I~
    }                                                              //~9B14I~
    //*********************************************************************//~0228I~
    public Point getActionBeforeDrawnHW()                          //~0228I~
    {                                                              //~0228I~
    	Point p=new Point(actionBeforeDrawnHW,playerBeforeDrawnHW);//~0228I~
        if (Dump.Y) Dump.println("Players.getActionBeforeDrawnHW action="+actionBeforeDrawnHW+",player="+playerBeforeDrawnHW);//~0228I~
        return p;                                                  //~0228I~
    }                                                              //~0228I~
    //*********************************************************************//~9225I~
    private int chkLast(boolean PswReach)                          //~9225R~//~9304R~
    {                                                              //~9225I~
    	int rc;                                                    //~9225R~
		rc=AG.aTiles.chkLast(PswReach);                            //~9225R~
        if (Dump.Y) Dump.println("Players.chkLast swReach="+PswReach+",rc="+Integer.toHexString(rc));//~9225R~
        return rc;
    }                                                              //~9225I~
    //*********************************************************************//~9208I~
    //*return errmsgid or 0(noerr) or -1(ignore err)               //~va66I~
    //*********************************************************************//~va66I~
    private int isRonAvailable(int Pplayer)                        //~9208R~
    {                                                              //~9208I~
        int errmsgid=0;                                            //~9208R~
        if (Dump.Y) Dump.println("Players.isRonAvailable Pplayer="+Pplayer+",playerCurrent="+playerCurrent+",lastActionID="+lastActionID+",kanType="+kanType+",swLastActionisDiscard="+swLastActionIsDiscard);//~9226I~//~0401R~//~0404R~//~va60R~//~va84R~
//      if (Pplayer!=PLAYER_YOU)                                   //~9208I~//~9226R~
//      	return 0;                                              //~9208I~//~9226R~
		if (AG.aComplete.isDrawnDelayLastTimeout())                //~9603I~
		    errmsgid=R.string.AE_DrawnLastDelayTimeout;            //~9603I~
        else                                                       //~9603I~
        if ((players[Pplayer].status & STF_COMPLETE)!=0) //dup button   //~9315I~
		    errmsgid=R.string.AE_DupActionRon;                     //~9315R~
        else                                                       //~9315I~
//      if (Pplayer==playerLastDiscarded)	//ron to your discarded//~9315I~//~9531R~
//      if (swLastActionIsDiscard &&  Pplayer==playerLastDiscarded)	//ron to your discarded//~9531I~//~9C06R~
        if (Pplayer==playerLastDiscarded 	//ron to your discarded//~9C06I~
        && (                                                       //~9C06I~
				swLastActionIsDiscard                              //~9C06I~
//           ||                                                    //~9C06I~//~0401R~
//              lastActionID==GCM_KAN    //do not disable chankan  //~9C06I~//~0401R~
           )                                                       //~9C06I~
        )
        {//~9C06I~
			errmsgid=R.string.AE_NotYourTurn;                      //~9315I~
        }                                                          //~9C06I~
        else                                                       //~9315I~
        if (Pplayer==playerCurrent)                                //~9208I~
        {                                                          //~9208I~
            if (lastActionID==GCM_PON                              //~9208I~
//          ||  lastActionID==GCM_PON_C                          //~9B16R~//~9B17R~
            ||  lastActionID==GCM_CHII                             //~9208I~
            )           //should discard                           //~9208I~
            {                                                      //~9208I~
                errmsgid=R.string.AE_DiscardTaken;                 //~9208I~
            }                                                      //~9208I~
            else                                                   //~9208I~
            if (lastActionID==GCM_DISCARD)                         //~9208I~
            {                                                      //~9208I~
			    errmsgid=R.string.AE_NotYourTurn;                  //~9208I~
            }                                                      //~9208I~
        }                                                          //~9208I~
        else                                                       //~9208I~
        {                                                          //~9208I~
            if (lastActionID==GCM_RON)    //delay chk avail server only , move to UARon//~9226R~
            {                                                      //~9222I~//~9226R~
                if (AG.aStatus.isIssuedRon())   //already issued           //~9222I~//~9226R~
                {                                                  //~9302I~
                	if (tileComplete.isTaken())                    //~9302I~//~0401R~
                    {                                              //~0401I~
//              		if (!tileComplete.isKanAddedTile()	//chankan//~0401R~
                		if (kanType==KAN_ADD)                      //~0401I~
                            ;                                      //~0401I~
                        else                                       //~0401I~
                		if (kanType==KAN_TAKEN)                    //~0401I~
                        {                                          //~0401I~
        			    	if (!RuleSettingYaku.isAvailableAnkanRon())//~0401I~
				    			errmsgid=R.string.AE_AnkanRonNG;   //~0401I~
                        }                                          //~0401I~
                        else                                       //~0401I~
                        	errmsgid=R.string.AE_DupRonTaken;   //anyone called tumo       //~9302I~//~0401R~//~0404R~
                        if (errmsgid==0 && (kanType==KAN_ADD||kanType==KAN_TAKEN))//~0404I~
                        {                                          //~0404I~
                            if (!AG.aUADelayed.isDupRonOK2Touch(Pplayer))//swRonnable!=true//~0404I~
                            {                                      //~0404I~
                                if (!AG.aUADelayed.isDupRonOKKan(Pplayer,tileComplete))//~0404I~
                                    errmsgid=R.string.AE_DupRonDelayed;//~0404I~
                            }                                      //~0404I~
                        }                                          //~0404I~
                    }                                              //~0401I~
//TODO test         else                                           //~9302I~//~0404R~//~va8AR~
//                  if (AG.swTrainingMode &&  AG.aAccounts.isRobotPlayer(Pplayer))//~va70I~//~va8AR~
//                  {                                              //~va70I~//~va8AR~
//                      chkDupRonRobotPlayAlone(Pplayer,tileComplete);//~va70I~//~va8AR~
//                  }                                              //~va70I~//~va8AR~
                    else                                           //~va70I~//~va8AR~
                    if (AG.aAccounts.isRobotPlayer(Pplayer))       //~va8AR~
                    {                                              //~va8AR~
//                      if (!AG.aUADelayed.isDupRonOK2Touch(Pplayer))//swRonnable!=true //robot ignore swCancelable and issue ron at RonTime//~va8AR~
//                          if (!AG.aUADelayed.isDupRonOK(Pplayer,tileComplete)) //robot issueRon at rontime//~va8AR~
//                          {                                      //~va8AR~
//                              delayedRonRobotMsg(Pplayer);       //~va8AR~
//                              errmsgid=-1;    //issued           //~va8AR~
//                          }                                      //~va8AR~
                                                                   //~va8AR~
                        if (Dump.Y) Dump.println("Players.isRonAvailable always accept Robot Ron Pplayer="+Pplayer);//~va8AR~
                    }                                              //~va8AR~
                    else                                           //~va8AI~
        		    if (!AG.aUADelayed.isDupRonOK2Touch(Pplayer))//swRonnable!=true  //~9B29I~//~0404R~
                    {                                              //~9B29I~
                    	if (!AG.aUADelayed.isDupRonOK(Pplayer,tileComplete))//~9226R~//~9B29R~
                        {                                          //~va66I~
            			  if (AG.aAccounts.isRobotPlayer(Pplayer)) //~va66I~
                          {                                        //~va66I~
                            delayedRonRobotMsg(Pplayer);           //~va66I~
                            errmsgid=-1;	//issued               //~va66R~
                          }                                        //~va66I~
                          else                                     //~va66I~
                        	errmsgid=R.string.AE_DupRonDelayed;        //~9222R~//~9226R~//~9B29R~
                        }                                          //~va66I~
                    }                                              //~9B29I~
                }                                                  //~9302I~
            }                                                      //~9222I~//~9226R~
            else                                                   //~9222I~//~9226R~
            if (lastActionID!=GCM_DISCARD                          //~9208I~
//          &&  lastActionID!=GCM_KAN                              //~9208I~//~0405R~
//          &&  kanType==0		//discarded after kan(ronnable until discard for kan taken)//~9209I~//~0405R~
            )                                                      //~9208I~
            {                                                      //~9208I~
			    errmsgid=R.string.AE_NotYourTurn;                  //~9208I~
              	if (kanType==KAN_TAKEN||kanType==KAN_ADD)          //~0405I~
				    errmsgid=0;                                    //~0405I~
            }                                                      //~9208I~
            if (lastActionID==GCM_KAN                              //~9218I~
            &&  kanType==KAN_TAKEN)   //an-kan                     //~9218I~
            {                                                      //~9218I~
//          	if (!RuleSetting.isAvailableAnkanRon())            //~9218I~//~9530R~
            	if (!RuleSettingYaku.isAvailableAnkanRon())        //~9530I~
				    errmsgid=R.string.AE_AnkanRonNG;               //~9218I~
            }                                                      //~9218I~
        }                                                          //~9208I~
        if (Dump.Y) Dump.println("Players.isRonAvailable Pplayer="+Pplayer+",errmsgid="+Integer.toHexString(errmsgid)+",lastActionID="+lastActionID+",playerCurrent="+playerCurrent+",kanType=="+kanType);//~9208R~//~9218R~//~9226R~
        return errmsgid;                                           //~9208R~
    }                                                              //~9208I~
    //*********************************************************************//~va70I~
    private boolean chkDupRonRobotPlayAlone(int Pplayer,TileData PtdComplete)//~va70I~
    {                                                              //~va70I~
    	boolean rc=true;                                           //~va70I~
        if (Dump.Y) Dump.println("Players.chkDupRonRobotPlayAlone Pplayer="+Pplayer+",td="+PtdComplete.toString()+",msgPlayAlone="+AG.aGC.getStatusPlayAlone());//~va70I~
        if (AG.aGC.getStatusPlayAlone()==GCM_RON)	//blocked by human player//~va70I~
        {                                                          //~va70I~
	        if (Dump.Y) Dump.println("Players.chkDupRonRobotPlayAlone protect robot ron by human Ron");//~va70I~
        	rc=false;                                              //~va70I~
        }                                                          //~va70I~
        return rc;                                                 //~va70I~
    }                                                              //~va70I~
    //*********************************************************************//~va66I~
    //*robot dup ron, re-show other winner                         //~va66R~
    //*********************************************************************//~va66I~
	private void delayedRonRobotMsg(int Pplayer)                   //~va66I~
    {                                                              //~va66I~
        if (Dump.Y) Dump.println("Players.delayedRonRobotMsg player="+Pplayer);//~va66R~//~va84R~
        String msg="";                                             //~va66I~
        boolean sw1st=true;                                        //~va66I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~va66I~
        {                                                          //~va66I~
			if (getPlayerCompleteFlag(ii/*players*/)!=0)           //~va66R~
            {                                                      //~va66I~
        	    if (!sw1st)                                        //~va66I~
        			msg+=", ";                                     //~va66I~
        		msg+=AG.aGMsg.getHLName(0,GCM_RON,ii);             //~va66R~
                sw1st=false;                                       //~va66I~
            }                                                      //~va66I~
        }                                                          //~va66I~
        GMsg.showHL(0,msg);                                        //~va66I~
    }                                                              //~va66I~
    //*********************************************************************//~9208I~
    //*Pon/Chii is for tileLastDiscarded                           //~9B12I~
    //*********************************************************************//~9B12I~
	public TileData getCurrentTile()                               //~9208I~
    {                                                              //~9208I~
    	TileData td;                                               //~9208I~
		if (swLastActionIsDiscard)                                 //~9208I~
        	td=tileLastDiscarded;                                  //~9208I~
        else                                                       //~9208I~
	        td=tileCurrentTaken;                                   //~9208I~
        if (Dump.Y) Dump.println("Players.getCurrentTile swLastActionIsDiscard="+swLastActionIsDiscard+",td:"+TileData.toString(td));//~9208I~
    	return td;                                                 //~9208I~
    }                                                              //~9208I~
    //*********************************************************************//~9222I~
	public TileData getCurrentTileForRon()                         //~9222I~
    {                                                              //~9222I~
    	TileData td;                                               //~9222I~
        if (lastActionID==GCM_RON)                                 //~9222I~
    		td=tileComplete;                                       //~9222I~
        else                                                       //~9222I~
			td=getCurrentTile();	//discarded or taken                                   //~9222I~//~9B12R~
        if (Dump.Y) Dump.println("Players.getCurrentTileForRon lastActionID="+lastActionID+",td:"+td.toString());//~9222I~
    	return td;                                                 //~9222I~
    }                                                              //~9222I~
    //*********************************************************************//~v@@@I~
	public boolean takeOne(int Pplayer,TileData Ptd)               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.takeOne no Eswn parm player="+Pplayer+",Ptd="+Ptd.toString());//~v@@@R~
//  	return takeOne(Pplayer,Ptd,false/*PswShadow*/,Pplayer);    //~v@@@R~
    	return takeOne(Pplayer,Ptd,false/*PswShadow*/,AG.aAccounts.playerToEswn(Pplayer));//~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public boolean takeOne(int Pplayer,TileData Ptd,boolean PswShadow)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.takeOne no Eswn parm player="+Pplayer+",swShadow="+PswShadow+",Ptd="+Ptd.toString());//~v@@@I~
    	return takeOne(Pplayer,Ptd,PswShadow,AG.aAccounts.playerToEswn(Pplayer));//~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public boolean takeOne(int Pplayer,TileData Ptd,int Peswn)     //~v@@@I~
    {                                                              //~v@@@I~
		return takeOne(Pplayer,Ptd,false/*PswShadow*/,Peswn);      //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public boolean takeOne(int Pplayer,TileData Ptd,boolean PswShadow,int Peswn)//~v@@@R~
    {                                                              //~v@@@I~
//        if (!swLastActionIsDiscard)                              //~v@@@R~
//        {                                                        //~v@@@R~
//            if (Dump.Y) Dump.println("Players.takeOne Dup TakeOne");//~v@@@R~
//            return false;                                        //~v@@@R~
//        }                                                        //~v@@@R~
//  	if (ctrTakenAll==0)                                        //~v@@@R~
        	setCurrentPlayer(Pplayer);	//light on;                //~v@@@I~
        ctrTakenAll++;                                             //~v@@@R~
        if (Dump.Y) Dump.println("Players.takeOne player="+Pplayer+",ctrTakenAll="+ctrTakenAll+",Ptd="+Ptd.toString());//~v@@@R~//~9623I~
//        swLastActionIsDiscard=false;                             //~v@@@R~
//      Ptd.setTaken(Pplayer);                                     //~v@@@R~
//      Ptd.setPlayer(Pplayer);                                    //~v@@@R~
        Ptd.setPlayer(Pplayer,Peswn);                              //~v@@@I~
    	players[Pplayer].takeOne(Ptd);                              //~v@@@R~
        AG.aRoundStat.takeOne(Pplayer,Peswn,Ptd);                 //~va60I~
        tileCurrentTaken=Ptd;                                      //~v@@@R~
//      AG.aDiceBox.setWaitingDiscard(Pplayer);                    //~v@@@R~
//      AG.aDiceBox.setWaitingDiscard(Pplayer,PswShadow);          //~v@@@R~
        AG.aDiceBox.drawLightTakeOne(Pplayer,PswShadow);           //~v@@@I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public boolean takeOneOtherOnClient(int Pplayer,TileData Ptd,boolean PswShadow)//~v@@@I~
    {                                                              //~v@@@I~
//      setCurrentPlayer(Pplayer);	//light on;                    //~v@@@I~
    	setCurrentPlayerWithoutLight(Pplayer);                  //~v@@6I~
        ctrTakenAll++;                                             //~v@@@I~
        if (Dump.Y) Dump.println("Players.takeOneOtherOnClient player="+Pplayer+",ctrTakenAll="+ctrTakenAll+",Ptd="+Ptd.toString());//~v@@@R~//~9225R~//~9623I~
//      Ptd.setPlayer(Pplayer,Peswn);                              //~v@@@I~
//  	players[Pplayer].takeOne(Ptd);                             //~v@@@I~
        tileCurrentTaken=Ptd;                                      //~v@@@I~
        AG.aDiceBox.drawLightTakeOne(Pplayer,PswShadow);           //~v@@@I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
    public TileData getCurrentTaken()                              //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getCurrentTaken tile="+TileData.toString(tileCurrentTaken));//~v@@@R~//~9208R~
        return tileCurrentTaken;                                   //~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
    public TileData getLastDiscarded()                             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getLastDiscarded tile="+TileData.toString(tileLastDiscarded));//~v@@@R~//~9208R~
        return tileLastDiscarded;                                  //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public boolean discard(int Pplayer,TileData Ptd)               //~v@@@M~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.discard player="+Pplayer+",Ptd="+Ptd.toString());//~v@@@R~
		return discard(true/*PswLight*/,Pplayer,Ptd);              //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public boolean discard(boolean PswLight,int Pplayer,TileData Ptd)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.discard swLight="+PswLight+",player="+Pplayer+",Ptd="+Ptd.toString());//~v@@@R~
//        if (swLastActionIsDiscard)                               //~v@@@R~
//        {                                                        //~v@@@R~
//            if (Dump.Y) Dump.println("Players.takeOne Dup Discard");//~v@@@R~
//            return false;                                        //~v@@@R~
//        }                                                        //~v@@@R~
//      Ptd.setDiscarded(); //TDF_DISCARDED at players[].discard because multiple tile exist of swme type/number/red5//~v@@@R~
        tileLastDiscarded=Ptd; //it may be new TileData,but remove original searched with ctrRemain//~v@@6R~
        playerLastDiscarded=Pplayer;                               //~v@@@M~
//        swLastActionIsDiscard=true;                              //~v@@@R~
    	players[Pplayer].discard(Ptd);                             //~v@@@M~
        AG.aRoundStat.discard(Pplayer,Ptd);                       //~va60I~
//      tileLastDiscarded=players[Pplayer].discardedTile;    //specific object remover//~v@@@I~//~v@@6R~
//      TileData.copyTD(Ptd,tileLastDiscarded);                    //~v@@@R~//~v@@6R~
        if (PswLight)                                              //~v@@@I~
        {                                                          //~v@@@I~
//	        setNextPlayer(Pplayer);                                //~v@@@R~
//	        setNextPlayer(Pplayer,true/*swShadow*/);               //~v@@@R~
	    	AG.aDiceBox.drawLightDiscard(Pplayer);                 //~v@@@I~
        }                                                          //~v@@@I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public boolean discardOtherOnClient(boolean PswLight,boolean PswShadow,int Pplayer,TileData Ptd)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.discardOtherOnClient swLight="+PswLight+",swShadow="+PswShadow+",player="+Pplayer+",Ptd="+Ptd.toString());//~v@@@R~
        Ptd.setDiscarded(); //TDF_DISCARDED                        //~v@@@I~
        tileLastDiscarded=Ptd;                                     //~v@@@I~
        if (Dump.Y) Dump.println("Players.discardOtherOnClient player="+Pplayer+",tileLastDiscarded="+Ptd.toString());//~vaadI~
        playerLastDiscarded=Pplayer;                               //~v@@@I~
    	players[Pplayer].discardOtherOnClient(Ptd);                //~v@@@I~
        if (PswLight)                                              //~v@@@I~
        {                                                          //~v@@@I~
//	        setNextPlayer(Pplayer,PswShadow);                      //~v@@@R~
	    	AG.aDiceBox.drawLightDiscard(Pplayer);                 //~v@@@I~
        }                                                          //~v@@@I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************************//~v@@@R~
//    public boolean isTakeAvailable()                             //~v@@@R~
//    {                                                            //~v@@@R~
//        boolean rc=swLastActionIsDiscard || ctrTakenAll==0;      //~v@@@R~
//        if (Dump.Y) Dump.println("Players.isTakeAvalable rc="+rc+",lastdiscard="+swLastActionIsDiscard+",ctrTakenAll="+ctrTakenAll);//~v@@@R~
//        return rc;                                               //~v@@@R~
//    }                                                            //~v@@@R~
//    //*********************************************************************//~v@@@R~
//    public boolean isYourDiscarded(int Pplayer)                  //~v@@@R~
//    {                                                            //~v@@@R~
//        boolean rc=(Pplayer==playerLastDiscarded);               //~v@@@R~
//        if (Dump.Y) Dump.println("Players.isYourDiscarded player="+Pplayer+",discarded player="+playerLastDiscarded);//~v@@@R~
//        if (rc)                                                  //~v@@@R~
//        {                                                        //~v@@@R~
//            if (Dump.Y) Dump.println("Players.isYourDiscarded it is your discarded");//~v@@@R~
//        }                                                        //~v@@@R~
//        return rc;                                               //~v@@@R~
//    }                                                            //~v@@@R~
    //*********************************************************************//~v@@@I~
	public boolean reach(int Pplayer)                                         //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.reach player="+Pplayer);  //~v@@@I~
    	boolean rc=players[Pplayer].reach();                       //~v@@@R~
        if (!rc)                                                   //~v@@@I~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("UserAction.reach not your turn");//~v@@@I~
        }                                                          //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~9301I~
	public boolean reachOpen(int Pplayer)                          //~9301I~
    {                                                              //~9301I~
        if (Dump.Y) Dump.println("Players.reachOpen player="+Pplayer);//~9301I~
    	boolean rc=players[Pplayer].reachOpen();                   //~9301I~
        if (Dump.Y) Dump.println("UserAction.reachOpen rc="+rc);   //~9301I~
        return rc;                                                 //~9301I~
    }                                                              //~9301I~
    //*********************************************************************//~va27I~
//  public void setReachAction(int PactionID)                      //~va27I~//~va66R~
    public void setReachAction(int PactionID,int Pplayer)          //~va66I~
    {                                                              //~va27I~
//  	players[PLAYER_YOU].actionReach=PactionID;                 //~va27I~//~va66R~
    	players[Pplayer].actionReach=PactionID;                    //~va66I~
        if (Dump.Y) Dump.println("Players.setReachAction player="+Pplayer+",actionID="+PactionID);//~va27I~//~va66R~
    }                                                              //~va27I~
    //*********************************************************************//~va27I~
//	public int getReachAction()                                    //~va27R~//~va66R~
  	public int getReachAction(int Pplayer)                         //~va66I~
    {                                                              //~va27I~
//  	int actionID=players[PLAYER_YOU].actionReach;              //~va27I~//~va66R~
    	int actionID=players[Pplayer].actionReach;                 //~va66I~
        if (Dump.Y) Dump.println("Players.getReachAction player="+Pplayer+",actionID="+actionID);//~va27I~//~va66R~
        return actionID;                                           //~va27I~
    }                                                              //~va27I~
    //*********************************************************************//~v@@@I~
	public int getDiscardedCtr(int Pplayer)                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getDiscardedCtr player="+Pplayer+",ctr="+players[Pplayer].ctrDiscarded);//~v@@@I~//~va60R~
    	return players[Pplayer].ctrDiscarded;                      //~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public int getLastDiscardedPlayer()                            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getLastDiscardedPlayer ="+playerLastDiscarded);//~v@@@I~//~va60R~
    	return playerLastDiscarded;                                //~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************************//~v@@@R~
//    //*for Client,save discard data                              //~v@@@R~
//    //*********************************************************************//~v@@@R~
//    public int saveDiscarded(int Pplayer)                        //~v@@@R~
//    {                                                            //~v@@@R~
//        players[Pplayer].ctrDiscarded++;                         //~v@@@R~
//        playerLastDiscarded=Pplayer;                             //~v@@@R~
//        if (Dump.Y) Dump.println("Players.saveDiscarded player="+Pplayer+",ctr="+players[Pplayer].ctrDiscarded);//~v@@@R~//~va60R~
//        return players[Pplayer].ctrDiscarded;                    //~v@@@R~
//    }                                                            //~v@@@R~
    //*********************************************************************//~v@@@I~
	public int takenDiscarded(int Pplayer)                         //~v@@@I~
    {                                                              //~v@@@I~
    	players[Pplayer].ctrDiscarded--;                           //~v@@@I~
        if (Dump.Y) Dump.println("Players.takentDiscarded player="+Pplayer+",ctr="+players[Pplayer].ctrDiscarded);//~v@@@I~//~va60R~
    	return players[Pplayer].ctrDiscarded;                      //~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************************//~v@@@I~//~9503R~
//    public void resetDiscardedCtr()                                //~v@@@I~//~9503R~
//    {                                                              //~v@@@I~//~9503R~
//        for (Player player:players)                                //~v@@@I~//~9503R~
//            player.resetDiscardedCtr();                            //~v@@@I~//~9503R~
//    }                                                              //~v@@@I~//~9503R~
    //*********************************************************************//~v@@@I~
    //*return pos to use lying bitmap or -1(not reach doen)        //~v@@@I~
    //*********************************************************************//~v@@@I~
	public int getPosReach(int Pplayer)                            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getPosReach player="+Pplayer);//~v@@@R~//~va60R~
        return players[Pplayer].getPosReach();                     //~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public TileData getDiscardedTile(int Pplayer)                  //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getDiscarddTile player="+Pplayer+",discardedTile="+players[Pplayer].discardedTile);//~v@@@I~//~va60R~
        return players[Pplayer].discardedTile;                     //~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@R~
    //*from river                                                  //~v@@@R~
    //*********************************************************************//~v@@@R~
    public int getReachStatus(int Pplayer)                         //~v@@@R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("Players.getReachStatus player="+Pplayer+",rc="+players[Pplayer].reachStatus);//~v@@@I~//~9301R~//~va60R~
		return players[Pplayer].reachStatus;                       //~v@@@I~
    }                                                              //~v@@@R~
//    //*********************************************************************//~v@@@I~//~0322R~
//    public boolean takePon(int Pplayer)                            //~v@@@I~//~0322R~
//    {                                                              //~v@@@I~//~0322R~
//        if (Dump.Y) Dump.println("Players.takePon player="+Pplayer);//~v@@@I~//~0322R~//~va60R~
////        if (isYourDiscarded(Pplayer))                            //~v@@@R~//~0322R~
////            return false;                                        //~v@@@R~//~0322R~
//        TileData td=tileLastDiscarded;                             //~v@@@I~//~0322R~
//        td.setTakenRiver();                                        //~v@@@R~//~0322R~
//        players[Pplayer].takePon(td);                              //~v@@@I~//~0322R~
////      setPlayer(Pplayer);                                        //~v@@@R~//~0322R~
//        setCurrentPlayer(Pplayer);                                 //~v@@@R~//~0322R~
////        swLastActionIsDiscard=false;                             //~v@@@R~//~0322R~
//        return true;                                               //~v@@@I~//~0322R~
//    }                                                              //~v@@@I~//~0322R~
    //*********************************************************************//~v@@@I~
	public boolean takePon(int Pplayer,TileData[] Ptds)                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.takePon player="+Pplayer);//~v@@@I~//~va60R~
        TileData td=tileLastDiscarded;                             //~v@@@I~
        td.setTakenRiver();                                        //~v@@@I~
        players[Pplayer].takePon(Ptds);                             //~v@@@I~
        AG.aRoundStat.takePon(Pplayer,Ptds);                       //~va60I~
//      setCurrentPlayer(Pplayer);                                 //~v@@@I~//~v@@5R~
//      setCurrentPlayer(Pplayer,false/*swShadow*/);               //~v@@5I~//~v@@@R~
        setCurrentPlayer(Pplayer,Pplayer!=PLAYER_YOU/*swShadow*/); //~v@@@R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@5I~
	public boolean takePonOtherOnClient(boolean PswShadow,int Pplayer,TileData[] Ptds)//~v@@5I~
    {                                                              //~v@@5I~
        if (Dump.Y) Dump.println("Players.takePonOtherOnClient shadow="+PswShadow+",player="+Pplayer);//~v@@5I~//~va60R~
//      TileData td=tileLastDiscarded;                             //~v@@5I~//~va60R~
        Ptds[PAIRCTR-1].setTakenRiver();                                        //~v@@5I~
//      if (Pplayer==PLAYER_YOU)                                   //~v@@@R~
//          players[PLAYER_YOU].takePon(Ptds);            //~v@@5I~//~v@@@R~
        players[Pplayer].takePonOtherOnClient(Ptds);               //~v@@@R~
        if (Pplayer==PLAYER_YOU)                                   //~vaadI~
        	AG.aRoundStat.takePon(Pplayer,Ptds);                   //~vaadI~
        setCurrentPlayer(Pplayer,PswShadow);                       //~v@@5I~
        return true;                                               //~v@@5I~
    }                                                              //~v@@5I~
//    //*********************************************************************//~v@@@I~//~0322R~
//    public boolean takeChii(int Pplayer)                            //~v@@@I~//~0322R~
//    {                                                              //~v@@@I~//~0322R~
//        if (Dump.Y) Dump.println("Players.takeChii player="+Pplayer);//~v@@@I~//~0322R~//~va60R~
////        if (isYourDiscarded(Pplayer))                            //~v@@@R~//~0322R~
////            return false;                                        //~v@@@R~//~0322R~
//        TileData td=tileLastDiscarded;                             //~v@@@I~//~0322R~
//        td.setTakenRiver();                                        //~v@@@R~//~0322R~
//        players[Pplayer].takeChii(td);                             //~v@@@I~//~0322R~
////      setPlayer(Pplayer);                                        //~v@@@R~//~0322R~
//        setCurrentPlayer(Pplayer);                                 //~v@@@R~//~0322R~
////        swLastActionIsDiscard=false;                             //~v@@@R~//~0322R~
//        return true;                                               //~v@@@I~//~0322R~
//    }                                                              //~v@@@I~//~0322R~
    //*********************************************************************//~v@@@I~
	public boolean takeChii(int Pplayer,TileData[] Ptds)           //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.takeChii player="+Pplayer);//~v@@@I~//~va60R~
        TileData td=tileLastDiscarded;                             //~v@@@I~
        td.setTakenRiver();                                        //~v@@@I~
        players[Pplayer].takeChii(Ptds);                           //~v@@@I~
        AG.aRoundStat.takeChii(Pplayer,Ptds);                      //~va60I~
        setCurrentPlayer(Pplayer,Pplayer!=PLAYER_YOU/*swShadow*/); //~v@@@I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public boolean takeChiiOtherOnClient(boolean PswShadow,int Pplayer,TileData[] Ptds)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.takeChiiOtherOnClient shadow="+PswShadow+",player="+Pplayer);//~v@@@I~//~va60R~
//      Ptds[PAIRCTR-1].setTakenRiver();                           //~v@@@R~
        TileData.setTakenRiver(Ptds);  //taken is not last tile    //~v@@@I~
//      if (Pplayer==PLAYER_YOU)                                   //~v@@@R~
//          players[PLAYER_YOU].takeChii(Ptds);                    //~v@@@R~
        players[Pplayer].takeChiiOtherOnClient(Ptds);              //~v@@@R~
        if (Pplayer==PLAYER_YOU)                                   //~vaadI~
        	AG.aRoundStat.takeChii(Pplayer,Ptds);                  //~vaadI~
        setCurrentPlayer(Pplayer,PswShadow);                       //~v@@@I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
////  public int takeKan(int Pplayer,TileData Ptdkan)                //~v@@@R~//~v@@6R~
//    public int takeKan(int Pplayer)                                //~v@@@I~//~v@@6R~
//    {                                                              //~v@@@I~//~v@@6R~
//        TileData td;                                               //~v@@@I~//~v@@6R~
////        int rc=-1;                                               //~v@@@R~//~v@@6R~
////        boolean swRiver;                                         //~v@@@R~//~v@@6R~
////        if (isYourDiscarded(Pplayer))                            //~v@@@R~//~v@@6R~
////            return false;                                        //~v@@@R~//~v@@6R~
////        if (swLastActionIsDiscard)                               //~v@@@R~//~v@@6R~
////        {                                                        //~v@@@R~//~v@@6R~
////            td=tileLastDiscarded;                                //~v@@@R~//~v@@6R~
////            td.setTakenRiver();                                  //~v@@@R~//~v@@6R~
////            swRiver=true;                                        //~v@@@R~//~v@@6R~
////            rc=KAN_RIVER;   //minkan                             //~v@@@R~//~v@@6R~
////        }                                                        //~v@@@R~//~v@@6R~
////        else                                                     //~v@@@R~//~v@@6R~
////        {                                                        //~v@@@R~//~v@@6R~
////            td=tileCurrentTaken;                                 //~v@@@R~//~v@@6R~
////            swRiver=false;                                       //~v@@@R~//~v@@6R~
////            rc=KAN_TAKEN;   //ankan                              //~v@@@R~//~v@@6R~
////        }                                                        //~v@@@R~//~v@@6R~
//        if (AG.aTiles.ctrKan==MAXCTR_KAN)                          //~v@@@R~//~v@@6R~
//        {                                                          //~v@@@I~//~v@@6R~
//            GC.actionError(0,Pplayer,"Max Kan("+MAXCTR_KAN+") reached");//~v@@@R~//~v@@6R~
//            return -1;                                             //~v@@@I~//~v@@6R~
//        }                                                          //~v@@@I~//~v@@6R~
////        ctrKan++;                                                //~v@@@R~//~v@@6R~
//        if (Dump.Y) Dump.println("Players.takeKan player="+Pplayer);//~v@@@R~//~v@@6R~//~va60R~
////      Ptdkan.setPlayer(Pplayer);                                 //~v@@@R~//~v@@6R~
////      players[Pplayer].takeKan(td,Ptdkan,swRiver);               //~v@@@R~//~v@@6R~
//        int rc=players[Pplayer].takeKan();                           //~v@@@I~//~v@@6R~
////      setPlayer(Pplayer);                                        //~v@@@R~//~v@@6R~
//        setCurrentPlayer(Pplayer);                                 //~v@@@R~//~v@@6R~
//        setNextPlayer(Pplayer-1);   //next is take from wanpai     //~v@@@I~//~v@@6R~
////        swLastActionIsDiscard=false;                             //~v@@@R~//~v@@6R~
//        return rc;                                                 //~v@@@R~//~v@@6R~
//    }                                                              //~v@@@I~//~v@@6R~
    //*********************************************************************//~v@@6I~
    public int takeKan(int Pplayer,TileData[] Ptds)                //~v@@6I~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("Players.takeKan player="+Pplayer+",ctrKan="+AG.aTiles.ctrKan+",tds="+TileData.toString(Ptds));//~v@@6I~//~0407R~
        if (AG.aTiles.ctrKan==MAXCTR_KAN)                          //~v@@6I~
        {                                                          //~v@@6I~
//      	GC.actionError(0,Pplayer,Utils.getStr(R.string.Err_5thKan,yn));//~v@@6I~//~0407R~
			String yn=Utils.getStr(RuleSettingYaku.is5thKanOn() ? R.string.On:R.string.Off);//~0407I~
			UserAction.showInfo(0,Utils.getStr(R.string.Err_5thKan,yn));//~0407I~
            return -1;                                             //~v@@6I~
        }                                                          //~v@@6I~
        int rc=players[Pplayer].takeKan(Ptds);                     //~v@@6I~
        AG.aRoundStat.takeKan(Pplayer,Ptds);                       //~va60I~
        setCurrentPlayer(Pplayer);                                 //~v@@6I~
    	setNextPlayer(Pplayer-1);	//next is take from wanpai     //~v@@6I~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
    //*********************************************************************//~v@@6I~
	public int takeKanOtherOnClient(boolean PswShadow,int Pplayer,TileData[] Ptds)//~v@@6R~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("Players.takeKanOtherOnClient shadow="+PswShadow+",player="+Pplayer);//~v@@6I~//~va60R~
        int rc=players[Pplayer].takeKanOtherOnClient(Ptds);        //~v@@6R~
        if (Pplayer==PLAYER_YOU)                                   //~vaadI~
        	AG.aRoundStat.takeKan(Pplayer,Ptds);                   //~vaadI~
        setCurrentPlayer(Pplayer,PswShadow);                       //~v@@6I~
        return rc;                                                 //~v@@6R~
    }                                                              //~v@@6I~
    //*********************************************************************//~v@@@I~
	public int complete(int Pplayer)                           //~v@@@R~
    {                                                              //~v@@@I~
        int rc=players[Pplayer].complete();                        //~v@@@R~
        if (Dump.Y) Dump.println("Players.complete player="+Pplayer+",rc="+rc);//~v@@@R~
        return rc;  //completeflag                                               //~v@@@I~
     }                                                              //~v@@@I~
    //*********************************************************************//~va11I~
	public int getCompleteFlag(int Pplayer)                        //~va11I~
    {                                                              //~va11I~
        int rc=completeFlag;                      //~va11I~
        if (Dump.Y) Dump.println("Players.getCompleteFlag player="+Pplayer+",rc=0x"+Integer.toHexString(rc));//~va11I~
        return rc;  //completeflag                                 //~va11I~
     }                                                             //~va11I~
    //*********************************************************************//~va66I~
	public int getPlayerCompleteFlag(int Pplayer)                  //~va66I~
    {                                                              //~va66I~
        int rc=players[Pplayer].playerCompleteFlag;                //~va66I~
        if (Dump.Y) Dump.println("Players.getplayerCompleteFlag player="+Pplayer+",rc=0x"+Integer.toHexString(rc));//~va66I~
        return rc;  //playerCompleteflag                           //~va66I~
     }                                                             //~va66I~
    //*********************************************************************//~9A12I~
	public int resetComplete(int Pplayer)                          //~9A12I~
    {                                                              //~9A12I~
        int rc=players[Pplayer].resetComplete();                   //~9A12I~
        if (Dump.Y) Dump.println("Players.resetComplete player="+Pplayer+",rc="+rc);//~9A12I~
        return rc;                                                 //~9A12I~
     }                                                             //~9A12I~
    //*********************************************************************//~9A14I~
	public void setNotRonable(int Pplayer)                         //~9A14I~
    {                                                              //~9A14I~
        if (Dump.Y) Dump.println("Players.setNotRonable player="+Pplayer);//~9A14I~
        players[Pplayer].setNotRonable();                          //~9A14I~
     }                                                             //~9A14I~
    //*********************************************************************//~v@@@I~
	public void open(int Pplayer)                                  //~v@@@I~
    {                                                              //~v@@@I~
        players[Pplayer].open();                            //~v@@@I~
        if (Dump.Y) Dump.println("Players.open player="+Pplayer);  //~v@@@I~
     }                                                             //~v@@@I~
    //*********************************************************************//~v@@@I~
	public boolean isOpen(int Pplayer)                             //~v@@@I~
    {                                                              //~v@@@I~
        boolean rc=(players[Pplayer].status & STF_OPEN)!=0;            //~v@@@I~
        if (Dump.Y) Dump.println("Players.isOpen player="+Pplayer+",rc="+rc);//~v@@@I~
        return rc;
    }                                                             //~v@@@I~
    //*********************************************************************//~v@@@I~
	public TileData[][] getEarth(int Pplayer)                      //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.getEarth player="+Pplayer+",pairOnEarth="+TileData.toString(players[Pplayer].pairOnEarth));//~va60R~
        return players[Pplayer].pairOnEarth;                       //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public Point getPointPair(int Pplayer)                         //~v@@@I~
    {                                                              //~v@@@I~
    	Point p=players[Pplayer].pointPairRightBottom;             //~v@@@I~
        if (Dump.Y) Dump.println("Players.getPointPair "+(p==null ? "null" : "x="+p.x+",y="+p.y));//~v@@@I~//~va60R~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public Rect getPieceRectForAddKan(int Pplayer)                //~v@@@R~
    {                                                              //~v@@@I~
    	Rect r=players[Pplayer].pieceRectForAddKan[players[Pplayer].idxEarthAddKan];//~v@@@R~
        if (Dump.Y) Dump.println("Players.getPointPairForAddKan idxEarthAddKan="+players[Pplayer].idxEarthAddKan+",rect= l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@R~//~va60R~
        return r;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public void savePointPair(int Pplayer,Point Prightbottom)     //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.savePointPair player="+Pplayer+",point="+Prightbottom.toString());//~9312I~//~va60R~
        players[Pplayer].savePointPair(Prightbottom);               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public void savePieceRectForAddKan(int Pplayer,Rect Prect)     //~v@@@I~
    {                                                              //~v@@@I~
        players[Pplayer].savePieceRectForAddKan(Prect);            //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public void setTileSelected(int Pplayer,int Ppos)             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.setTileSelected player="+Pplayer+",pos="+Ppos);//~9626I~//~va60R~
        players[Pplayer].setTileSelected(Ppos);                    //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~va66I~
	public void setTileSelected(int Pplayer,TileData Ptd)          //~va66I~
    {                                                              //~va66I~
        if (Dump.Y) Dump.println("Players.setTileSelected player="+Pplayer+",Ptd="+TileData.toString(Ptd));//~va66I~
        int pos=players[Pplayer].searchWithCtrRemain(Ptd);         //~va66I~
        setTileSelected(Pplayer,pos);                              //~va66I~
    }                                                              //~va66I~
    //*********************************************************************//~v@@@I~
	public TileData getTileSelected(int Pplayer)                   //~v@@@I~
    {                                                              //~v@@@I~
        return players[Pplayer].getTileSelected();                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public int getTileSelectedPos(int Pplayer)                     //~v@@@I~
    {                                                              //~v@@@I~
        return players[Pplayer].selectedPos;                       //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public TileData getTileKanAdded()                              //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Players.getTileKanAdded "+TileData.toString(tileKanAdded));//~9208I~
        return tileKanAdded;                                       //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@6I~
    public TileData[] getEarthForAddKan(int Pplayer)               //~v@@@I~
    {                                                              //~v@@@I~
    	return players[Pplayer].getEarthForAddKan();               //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~va91I~
    //*for IT                                                      //~va91I~
    //*********************************************************************//~va91I~
    public void addPair(int Pplayer,TileData[] Ptds,int Pflag)     //~va91R~
    {                                                              //~va91I~
    	players[Pplayer].addPair(Ptds,Pflag);               //~va91R~
    }                                                              //~va91I~
    //*********************************************************************//~v@@6I~
    public TileData[] getKanAddEarth(int Pplayer,boolean PswRep)   //~v@@6R~
    {                                                              //~v@@6I~
    	return players[Pplayer].getKanAddEarth(PswRep);            //~v@@6R~
    }                                                              //~v@@6I~
    //*********************************************************************//~0407I~
    public TileData[][] getPonsEarth(int Pplayer)                  //~0407I~
    {                                                              //~0407I~
    	return players[Pplayer].getPonsEarth();                    //~0407I~
    }                                                              //~0407I~
//    //*********************************************************************//~v@@@I~
//    public void reachPicked(TileData Ptd)                        //~v@@@I~
//    {                                                            //~v@@@I~
//        int player=Ptd.player;                                   //~v@@@I~
//        players[player].reachPicked(Ptd);                        //~v@@@I~
//    }                                                            //~v@@@I~
    //*********************************************************************//~9208I~
    public TileData setTileRon()                                       //~9208I~//~9211R~
    {                                                              //~9208I~
        if (Dump.Y) Dump.println("Players.setTileRon flag="+completeFlag);//~9208I~
        if (completeFlag==0)                                       //~9208I~
        	return null;                                                //~9208I~
	    return setTileRon(completeFlag);                                  //~9208I~//~9211R~
    }                                                              //~9208I~
    //*********************************************************************//~9208I~
    public TileData setTileRon(int Pflag)                              //~9208I~//~9211R~
    {                                                              //~9208I~
        if (Dump.Y) Dump.println("Players.setTileRon flag="+Pflag);//~9208I~
        TileData td=getTileComplete(Pflag);                        //~9208I~//~9217R~
        td.setRon();                                               //~9208I~
        if ((Pflag & COMPLETE_KAN_RIVER)!=0)                            //~9208I~//~9217R~
        {                                                          //~9208I~
        	getCurrentTaken().setRon(); //also to rinshan          //~9217R~
            if (Dump.Y)Dump.println("Players.setTileRon for kan taken tile"+TileData.toString(td));// +9208I~//~9217R~//~9A12R~
        }                                                          //~9208I~
        if (Dump.Y) Dump.println("Players.setTileRon td:"+TileData.toString(td));//~9208I~
        return td;                                                 //~9211I~
    }                                                              //~9208I~
    //*********************************************************************//~9A12I~
    public TileData resetTileRon(int Pflag)                        //~9A12I~
    {                                                              //~9A12I~
        if (Dump.Y) Dump.println("Players.resetTileRon flag="+Pflag);//~9A12I~
        TileData td=getTileComplete(Pflag);                        //~9A12I~
//      td.setRon();                                               //~9A12I~
        td.resetRon();                                             //~9A12I~
        if ((Pflag & COMPLETE_KAN_RIVER)!=0)                       //~9A12I~
        {                                                          //~9A12I~
//      	getCurrentTaken().setRon(); //also to rinshan          //~9A12I~
        	getCurrentTaken().resetRon(); //also to rinshan        //~9A12I~
            if (Dump.Y)Dump.println("Players.resetRon for kan taken tile"+TileData.toString(td));// +9208I~//~9A12I~
        }                                                          //~9A12I~
        if (Dump.Y) Dump.println("Players.resetTileRon td:"+TileData.toString(td));//~9A12I~
        return td;                                                 //~9A12I~
    }                                                              //~9A12I~
    //*********************************************************************//~9208I~
    //*for KAN_RIVER(rinshan tumo) return td of River not rinshan  //~9217I~
    //*********************************************************************//~9217I~
    public TileData getTileComplete(int Pflag)                    //~9208I~//~9217R~
    {                                                              //~9208I~
        if (Dump.Y) Dump.println("Players.getTileComplete flag="+Pflag);//~9208I~
        TileData td;                                               //~9208I~
        if ((Pflag & COMPLETE_TAKEN)==0)    //not take             //~9208I~
        {                                                          //~9208I~
            if ((Pflag & COMPLETE_KAN_ADD)!=0)  //chankan          //~9208I~
                td=getTileKanAdded(); //show tile in earth         //~9208I~
            else                                                   //~9208I~
//          if ((Pflag & COMPLETE_KAN_TAKEN)!=0)    //chankan      //~9208I~//~9209R~
            if ((Pflag & (COMPLETE_KAN_TAKEN | COMPLETE_KAN_TAKEN_OTHER))!=0)    //chankan//~9209I~
                td=getCurrentTaken(); //show tile in hand          //~9208I~
            else                                                   //~9208I~
                td=getLastDiscarded(); //show tile in hand         //~9208I~
        }                                                          //~9208I~
        else                                                       //~9208I~
            td=getCurrentTaken(); //show tile in hand              //~9208I~
        tileComplete=td;                                           //~9208I~//~9217R~
        if (Dump.Y) Dump.println("Players.getTileComplete td:"+TileData.toString(td));//~9208I~
        return td;                                                 //~9208I~
    }                                                              //~9208I~
    //*********************************************************************//~9208I~
    public TileData getTileComplete()                              //~9208I~//~9217R~
    {                                                              //~9208I~
        if (Dump.Y) Dump.println("Players.getTileComplete td="+TileData.toString(tileComplete));//~9208I~
        return tileComplete;                                       //~9208I~
    }                                                              //~9208I~
    //*********************************************************************//~9C11I~
    public TileData getTileCompleteSelectInfoRon()                 //~9C11I~
    {                                                              //~9C11I~
        if (Dump.Y) Dump.println("Players.getTileCompleteSelectInfoRon completeFlag="+completeFlag);//~9C11I~
    	TileData td=getTileComplete(completeFlag);                 //~9C11I~
        if (Dump.Y) Dump.println("Players.getTileCompleteSelectInfoRon td="+Utils.toString(td));//~9C11I~
        return td;                                                 //~9C11I~
    }                                                              //~9C11I~
    //*********************************************************************//~9301I~
    public boolean isOpenReach(int Pplayer)                        //~9301I~
    {                                                              //~9301I~
    	boolean rc=players[Pplayer].swOpenReach;                   //~9301I~
        if (Dump.Y) Dump.println("Players.isOpenReach player="+Pplayer+",rc="+rc);//~9301I~
        return rc;                                                 //~9301I~
    }                                                              //~9301I~
    //*********************************************************************//~9301I~
    public boolean removePair(int Pplayer,TileData[] Ptds)         //~9301I~
    {                                                              //~9301I~
        if (Dump.Y) Dump.println("Players.removePair player="+Pplayer);//~9301I~
    	boolean rc=players[Pplayer].removePair(Ptds);              //~9301I~
        return rc;                                                 //~9301I~
    }                                                              //~9301I~
    //*********************************************************************//~9228I~
    //*TODO for TEST                                               //~9228I~
    //*********************************************************************//~9228I~
    public void setReachDone(int Pplayer)                          //~9228I~
    {                                                              //~9228I~
        if (Dump.Y) Dump.println("Players.setReachDone player="+Pplayer);//~9511R~
//      players[Pplayer].reachStatus=REACH_DONE;                   //~9228I~//~va11R~
        players[Pplayer].setReachDone();                           //~va11R~
    }                                                              //~9228I~
    //*********************************************************************//~va11I~
    public Point getCtrReachDone(int Pplayer)                      //~va11I~
    {                                                              //~va11I~
        int t=players[Pplayer].ctrTakenReachDone;                  //~va11I~
		int d=players[Pplayer].ctrDiscardedReachDone;              //~va11I~
        if (Dump.Y) Dump.println("Players.getCtrReachDone take="+t+",discard="+d);//~va11I~
        return new Point(t,d);                                     //~va11I~
    }                                                              //~va11I~
    //*********************************************************************//~9A30I~
    public boolean  resetReachDoneBeforeDiscard(int Pplayer)       //~9A30R~
    {                                                              //~9A30I~
        boolean rc=players[Pplayer].resetReachDoneBeforeDiscard(); //~9A30R~
        if (Dump.Y) Dump.println("Players.resetReachDone player="+Pplayer+",rc="+rc);//~9A30I~//~9B12R~
        return rc;                                                 //~9A30I~
    }                                                              //~9A30I~
    //*********************************************************************//~9511I~
    //*from UARon to reaset reach by ron as winning tile           //~va66R~
    //*********************************************************************//~9706I~
    public void resetReachDone(int Pplayer)                        //~9511I~
    {                                                              //~9511I~
        if (Dump.Y) Dump.println("Players.resetReachDone player="+Pplayer);//~9511I~
//        if (Pplayer!=playerLastDiscarded)                          //~9706I~//~9707R~
//        {                                                          //~9706I~//~9707R~
//            if (Dump.Y) Dump.println("Players.resetReachDone player is not lastDiscarded:="+playerLastDiscarded);//~9706I~//~9707R~
//            return;                                                //~9706I~//~9707R~
//        }                                                          //~9706I~//~9707R~
        players[Pplayer].resetReachDone();                         //~9511I~
    }                                                              //~9511I~
    //*********************************************************************//~9704I~
    public void resetReachAll()                                    //~9704I~
    {                                                              //~9704I~
        if (Dump.Y) Dump.println("Players.resetReachAll before ctrReach="+ctrReach);         //~9704I~//~9904R~
        for (int player=0;player<PLAYERS;player++)                     //~9704I~
	        players[player].resetReachAll();                      //~9704I~
        if (Dump.Y) Dump.println("Players.resetReachAll after ctrReach="+ctrReach);//~9904I~
    }                                                              //~9704I~
    //*********************************************************************//~va6gI~
    public void clearReachAll()                                    //~va6gI~
    {                                                              //~va6gI~
        if (Dump.Y) Dump.println("Players.clearReachAll before ctrReach="+ctrReach);//~va6gI~
        for (int player=0;player<PLAYERS;player++)                 //~va6gI~
	        players[player].clearReachAll();                       //~va6gI~
        ctrReach=0;                                                //~va6gI~
        if (Dump.Y) Dump.println("Players.clearReachAll after ctrReach="+ctrReach);//~va6gI~
    }                                                              //~va6gI~
    //*********************************************************************//~va11I~
    public boolean isClosedHand(int Pplayer)                          //~va11I~
    {                                                              //~va11I~
    	boolean rc=players[Pplayer].isClosedHand();                 //~va11I~
        if (Dump.Y) Dump.println("Players.isClosedhand player="+Pplayer+",rc="+rc);//~va11I~
        return rc;
    }                                                              //~va11I~
    //*********************************************************************//~va15I~
    public int getLastActionID(int Pplayer)                    //~va15I~
    {                                                              //~va15I~
    	int rc=players[Pplayer].lastActionID;                       //~va15I~
        if (Dump.Y) Dump.println("Players.getLastActionID player="+Pplayer+",rc="+rc);//~va15I~
        return rc;                                                 //~va15I~
    }                                                              //~va15I~
//******************************************************************************//~v@@@I~
//******************************************************************************//~v@@@I~
//******************************************************************************//~v@@@I~
    class Player                                            //~v@@@I~
    {                                                              //~v@@@I~
        public int player;                                         //~v@@@I~
        private boolean swFound;                                   //~v@@@I~
//      private boolean swReachBeforeDiscard,swReachDiscarded,swReachFollowing;//~v@@@R~
        public  TileData discardedTile;                            //~v@@@R~
//      private TileData tileReach;                                //~v@@@I~
        private ArrayList<TileData> arrayList;	//in Hands         //~v@@@R~
        private int ctrPair=0;                                     //~v@@@R~
        private TileData[][] pairOnEarth;                          //~v@@@I~
        private Point pointPairRightBottom;                        //~v@@@I~
        public int reachStatus;                                    //~9228R~
        public boolean swOpenReach;                                //~9301I~
        private int posReach;                                      //~9228I~
        private int ctrDiscarded;	//ctr on river(-1 if erased)   //~0404R~
        private int saveCtrTaken;                                  //~0404I~
        private int lastActionID;                                  //~v@@@I~//~9208R~
        private int status;                                            //~v@@@I~
        private int selectedPos=-1;                                //~v@@@R~
        private int ctrTakenReachDone,ctrDiscardedReachDone;       //~va11R~
        private int idxEarthAddKan;                                //~v@@@R~
        public int ctrKan;                                         //~0407I~
        private TileData tileKan;                                  //~v@@@I~//~9208M~
        private Rect[] pieceRectForAddKan;                         //~v@@@I~//~9208M~
//      private boolean swLastActionIsKan;                         //~v@@@R~//~9208M~
        private int actionReach;                                   //~va27I~
        private int playerCompleteFlag;                            //~va66I~
        //*****************************************************    //~v@@@I~
        public Player(int Pplayer)                                 //~v@@@I~
        {                                                          //~v@@@I~
            player=Pplayer;                                        //~v@@@I~
            arrayList=new ArrayList<TileData>();                   //~v@@@I~
            pairOnEarth=new TileData[PAIRS_MAX][];                 //~v@@@R~
            pieceRectForAddKan=new Rect[PAIRS_MAX];                //~v@@@R~
        }                                                          //~v@@@I~
        //*****************************************************    //~9503I~
        public void newGame()                                      //~9503I~
        {                                                          //~9503I~
            if (Dump.Y) Dump.println("Player.newGame player="+player);//~9503I~
//      	int player;                                            //~9503I~
//      	boolean swFound;                                       //~9503I~
            discardedTile=null;                                    //~9503I~
        	arrayList.clear();	//in Hands                         //~9503I~
        	ctrPair=0;                                             //~9503I~
            pairOnEarth=new TileData[PAIRS_MAX][];                 //~9503I~
	        pointPairRightBottom=null;                             //~9503I~
        	reachStatus=0;                                         //~9503I~
       		swOpenReach=false;                                     //~9503I~
        	posReach=0;                                            //~9503I~
        	ctrDiscarded=0;saveCtrTaken=0;                         //~9503I~
        	lastActionID=0;                                        //~9503I~
        	status=0;                                              //~9503I~
        	selectedPos=-1;                                        //~9503I~
        	idxEarthAddKan=0;                                      //~9503I~
        	ctrKan=0;                                              //~0407I~
        	tileKan=null;                                          //~9503I~
            pieceRectForAddKan=new Rect[PAIRS_MAX];                //~9503I~
        }                                                          //~9503I~
        //*********************************************************************//~v@@@R~
        //*sort is shallow copy                                    //~v@@@R~
        //*********************************************************************//~v@@@R~
        public void setInitialDeal(TileData[] Ptd,int Ppos)        //~v@@@R~
        {                                                          //~v@@@R~
            if (Dump.Y) Dump.println("Player.setInitialDeal pos="+Ppos);//~v@@@R~
            TileData.sort(Ptd,Ppos,Ppos+HANDCTR);                  //~v@@@R~
            for (int ii=Ppos;ii<Ppos+HANDCTR;ii++)                 //~v@@@R~
            {                                                      //~v@@@R~
                Ptd[ii].setPlayer(player);                         //~v@@@R~
                arrayList.add(Ptd[ii]);                            //~v@@@R~
            }                                                      //~v@@@R~
        }                                                          //~v@@@R~
        //*********************************************************************//~v@@@I~
        //*for client sendData missing player and eswn             //~v@@@I~
        //*********************************************************************//~v@@@I~
        public void setInitialDeal(TileData[] Ptd,int Ppos,int Peswn)//~v@@@I~
        {                                                          //~v@@@I~
	        setInitialDeal(Ptd,Ppos,Peswn,false/*PswSort*/);        //~v@@5I~
        }                                                          //~v@@5I~
        //*********************************************************************//~v@@5I~
        public void setInitialDeal(TileData[] Ptd,int Ppos,int Peswn,boolean PswSort)//~v@@5I~
        {                                                          //~v@@5I~
            if (Dump.Y) Dump.println("Player.setInitialDeal pos="+Ppos+",eswn="+Peswn+",player="+player+",swSort="+PswSort);//~v@@@I~//~v@@5R~
            if (PswSort)                                           //~v@@5I~
	            TileData.sort(Ptd,Ppos,Ppos+HANDCTR);   //send data is already sorted//~v@@@I~//~v@@5R~
//          arrayList.clear();                                     //~9502I~//~9503R~
            for (int ii=Ppos;ii<Ppos+HANDCTR;ii++)                 //~v@@@I~
            {                                                      //~v@@@I~
                Ptd[ii].setPlayer(player,Peswn);                   //~v@@@I~
                arrayList.add(Ptd[ii]);                            //~v@@@I~
            }                                                      //~v@@@I~
            if (Dump.Y) Dump.println("Player.setInitialDeal out "+TileData.toString(Ptd,Ppos,HANDCTR));//~v@@6I~
        }                                                          //~v@@@I~
        //*********************************************************************//~9207I~
        public void setHandsClient(TileData[] Ptds)                //~9207I~
        {                                                          //~9207I~
            if (Dump.Y) Dump.println("Player.sethandsClient player="+player+",Ptds:"+TileData.toString(Ptds));//~9207I~
            for (int ii=0;ii<Ptds.length;ii++)                     //~9207I~
            {                                                      //~9207I~
                arrayList.add(Ptds[ii]);                           //~9207I~
            }                                                      //~9207I~
        }                                                          //~9207I~
        //*********************************************************************//~9302I~
        public void setHandsClientReplace(TileData[] Ptds)         //~9302I~
        {                                                          //~9302I~
            if (Dump.Y) Dump.println("Player.sethandsClientReplace player="+player+",Ptds:"+TileData.toString(Ptds));//~9302I~
            arrayList.clear();                                     //~9302I~
            setHandsClient(Ptds);	                               //~9302I~
        }                                                          //~9302I~
        //*********************************************************************//~v@@@I~
        public void add(TileData Ptd)                              //~v@@@I~
        {                                                          //~v@@@I~
            arrayList.add(Ptd);                                    //~v@@@I~
            if (Dump.Y) Dump.println("Player.add player="+player+",Ptd="+Ptd.toString()+",ctr="+arrayList.size());//~v@@@R~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        public int insert(TileData Ptd)                            //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.insert Ptd:"+Ptd.toString());//~v@@@R~
            int pos=search(Ptd);                                   //~v@@@I~
            arrayList.add(pos,Ptd);                                //~v@@@I~
            return pos;                                            //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        //*search in hand                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        private int search(TileData Ptd)                           //~v@@@R~
        {                                                          //~v@@@I~
            swFound=false;                                         //~v@@@I~
            int pos;                                               //~v@@@I~
            for (pos=0;pos<arrayList.size();pos++)                 //~v@@@I~
            {                                                      //~v@@@I~
                TileData td=arrayList.get(pos);                    //~v@@@I~
                int comp=TileData.TDCompare(td,Ptd);               //~v@@@I~
                if (comp>=0)                                       //~v@@@I~
                {                                                  //~v@@@I~
                    swFound=comp==0;                               //~v@@@I~
                    break;                                         //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
            if (Dump.Y) Dump.println("Player.search Ptd:"+Ptd.toString()+",pos="+pos+",swFound="+swFound);//~v@@@R~
            return pos;                                            //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@6I~
        //*search compare type/number/Red5/ctrRemain               //~v@@6I~
        //*********************************************************************//~v@@6I~
        private int searchWithCtrRemain(TileData Ptd)              //~v@@6I~
        {                                                          //~v@@6I~
            swFound=false;                                         //~v@@6I~
            int pos;                                               //~v@@6I~
            for (pos=0;pos<arrayList.size();pos++)                 //~v@@6I~
            {                                                      //~v@@6I~
                TileData td=arrayList.get(pos);                    //~v@@6I~
                int comp=TileData.TDCompare(td,Ptd,true/*compare ctrRemain*/);//~v@@6R~
//                if (comp>=0)                                     //~v@@6R~
//                {                                                //~v@@6R~
//                    swFound=comp==0;                             //~v@@6R~
//                    break;                                       //~v@@6R~
//                }                                                //~v@@6R~
                if (comp==0)     //current taken is not yet sorted,searth to last//~v@@6I~
                {                                                  //~v@@6I~
                    swFound=true;                                  //~v@@6I~
                    break;                                         //~v@@6I~
                }                                                  //~v@@6I~
            }                                                      //~v@@6I~
            if (Dump.Y) Dump.println("Player.searchWithCtrRemain arrayList.size="+arrayList.size()+",Ptd:"+Ptd.toString()+",pos="+pos+",swFound="+swFound);//~v@@6R~
            return pos;                                            //~v@@6I~
        }                                                          //~v@@6I~
        //*********************************************************************//~v@@6I~
        private boolean removeWithCtrRemain(TileData Ptd)          //~v@@6I~
        {                                                          //~v@@6I~
        	int pos=searchWithCtrRemain(Ptd);                      //~v@@6I~
            if (!swFound)                                          //~v@@6I~
            	return false;                                      //~v@@6I~
	        arrayList.remove(pos);                                 //~v@@6I~
            if (Dump.Y) Dump.println("Player.removeWithCtrRemain removed");//~v@@6I~
            return true;                                           //~v@@6I~
        }                                                          //~v@@6I~
        //*********************************************************************//~v@@@I~
        //* returns shallow copy                                   //~v@@@I~
        //*********************************************************************//~v@@@I~
        public TileData[] getHand()                                //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.getHand");            //~v@@@I~
            TileData[] tds=new TileData[arrayList.size()];         //~v@@@I~
            arrayList.toArray(tds);                                //~v@@@I~
            if (Dump.Y) Dump.println("Player.getHand tds="+TileData.toString(tds));//~v@@@R~
            return tds;                                            //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        //* returns shallow copy                                   //~v@@@I~
        //*********************************************************************//~v@@@I~
        public TileData[] getCurrentPair()                         //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.getCurrentPair player="+player+",pairctr="+ctrPair);//~v@@@R~
	    	TileData[] lasttds=null;                                  //~v@@@I~
	        for (TileData[] tds:pairOnEarth)                       //~v@@@I~
        	{                                                      //~v@@@I~
        		if (tds!=null && tds.length!=0)                    //~v@@@R~
            		lasttds=tds;                                       //~v@@@I~
        	}                                                      //~v@@@I~
            if (Dump.Y) Dump.println("Player.getCurrentPair out:"+TileData.toString(lasttds));//~v@@@I~
            return lasttds;                                            //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        //*from Players                                            //~v@@@I~
        //*********************************************************************//~v@@@I~
        public void takeOne(TileData Ptd)                          //~v@@@I~
        {                                                          //~v@@@I~
//            swLastActionIsKan=false;                             //~v@@@R~
            saveCtrTaken=ctrTakenAll;                              //~v@@@R~
//          posTaken=AG.aTiles.getCurrentTilePos();                //~v@@@R~
            if (Dump.Y) Dump.println("Player.takeOne ctrTaken="+saveCtrTaken);//~v@@@R~
//          insert(Ptd);                                           //~v@@@R~
            add(Ptd);      //inserted later at discard             //~v@@@I~
	        setTileSelected(arrayList.size()-1);    //default selection for discard//~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        //*from Players                                            //~v@@@I~
        //*********************************************************************//~v@@@I~
        public boolean discard(TileData Ptd)                       //~v@@@I~
        {                                                          //~v@@@I~
			int pos,posTaken;                                      //~v@@@I~
			TileData tdTaken;                                      //~v@@@I~
            boolean swTaken=false;                                 //~v@@@I~
        //********************************                         //~v@@@I~
            if (Dump.Y) Dump.println("Players.discard discard t="+Ptd.type+",n="+Ptd.number);//~v@@@I~//+vaadR~
            posTaken=arrayList.size()-1;                           //~v@@@I~
            tdTaken=arrayList.get(posTaken);                            //~v@@@I~
//          swTaken=TileData.TDCompare(tdTaken,Ptd)==0;	//discard taken//~v@@@I~//~v@@6R~
            swTaken=TileData.TDCompare(tdTaken,Ptd,true/*compare ctrRemain*/)==0;	//discard taken//~v@@6I~
            if (swTaken)	                                       //~v@@@I~
            {                                                      //~v@@@I~
            	pos=posTaken;                                      //~v@@@I~
            	if (Dump.Y) Dump.println("Players.discard discard taken");//~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
//          	pos=search(Ptd);                                   //~v@@@R~//~v@@6R~
            	pos=searchWithCtrRemain(Ptd);                     //~v@@6I~
            	if (!swFound)                                      //~v@@@R~
            	{                                                  //~v@@@R~
                	if (Dump.Y) Dump.println("Players.discard player="+player+",Ptd:"+Ptd.toString()+",tds:"+TileData.toString(getHands(player)));//~v@@6R~
            		GC.actionError(0,player,"Players.discard player="+player+",tile t="+Ptd.type+",n="+Ptd.number+";internal logic err No tile found");//~v@@@R~
                	return false; //TODO                           //~v@@@R~
            	}                                                  //~v@@@R~
            }                                                      //~v@@@I~
//            swLastActionIsKan=false;                             //~v@@@R~
            discardedTile=Ptd;                                     //~v@@@R~//~v@@6R~
    		Ptd.setDiscarded();                                    //~v@@6I~
            ctrDiscarded++;                                        //~v@@@I~
            ctrDiscardedAll++;                                     //~9624I~
//          arrayList.remove(pos);                                 //~v@@@R~
			TileData posTD=arrayList.get(pos);                     //~v@@@I~
            arrayList.remove(posTD);    //consideration of multiple tile of same type/number/red5//~v@@@I~
//  		posTD.setDiscarded();		                           //~v@@@I~//~v@@6R~
//          discardedTile=posTD;                                   //~v@@@I~//~v@@6R~
            if (!swTaken)                                          //~v@@@I~
            {                                                      //~v@@@I~
            	posTaken--;	//after removed                        //~v@@@I~
	            arrayList.remove(posTaken); //once remove taken    //~v@@@I~
            	insert(tdTaken); 			//then sort into       //~v@@@I~
            }                                                      //~v@@@I~
			if (reachStatus==REACH_BEFORE_DISCARD)                 //~v@@@I~
            {                                                      //~v@@@I~
	            reachDone(Ptd);                                    //~v@@@I~
            }                                                      //~v@@@I~
	        setTileSelected(-1);    //no selection to discard      //~v@@@I~
            if (Dump.Y) Dump.println("Players.discard player="+player+",discardedCtr="+ctrDiscarded);//~v@@@R~
            kanType=0;	//status at 1st kan until discard          //~9208R~
            return true;                                           //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        //*for Not PLAYER_YOU on client                            //~v@@@I~
        //*********************************************************************//~v@@@I~
        public boolean discardOtherOnClient(TileData Ptd)          //~v@@@I~
        {                                                          //~v@@@I~
			int pos,posTaken;                                      //~v@@@I~
			TileData tdTaken;                                      //~v@@@I~
            boolean swTaken=false;                                 //~v@@@I~
        //********************************                         //~v@@@I~
            if (Dump.Y) Dump.println("Players.discardOtherOnClient discard td="+Ptd.type+",no="+Ptd.number);//~v@@@I~//~9511R~
//          posTaken=arrayList.size()-1;                           //~v@@@I~
//          tdTaken=arrayList.get(posTaken);                       //~v@@@I~
//          swTaken=TileData.TDCompare(tdTaken,Ptd)==0;	//discard taken//~v@@@I~
//          if (swTaken)                                           //~v@@@I~
//          {                                                      //~v@@@I~
//          	pos=posTaken;                                      //~v@@@I~
//          	if (Dump.Y) Dump.println("Players.discard discard taken");//~v@@@I~
//          }                                                      //~v@@@I~
//          else                                                   //~v@@@I~
//          {                                                      //~v@@@I~
//          	pos=search(Ptd);                                   //~v@@@I~
//          	if (!swFound)                                      //~v@@@I~
//          	{                                                  //~v@@@I~
//          		GC.actionError(0,player,"Players.discard player="+player+",tile type="+Ptd.type+",no="+Ptd.number+";internal logic err No tile found");//~v@@@I~
//              	return false; //TODO                           //~v@@@I~
//          	}                                                  //~v@@@I~
//          }                                                      //~v@@@I~
            discardedTile=Ptd;                                     //~v@@@I~
            ctrDiscarded++;                                        //~v@@@I~
            ctrDiscardedAll++;                                     //~0404I~
//            arrayList.remove(pos);                               //~v@@@R~
//            if (!swTaken)                                        //~v@@@R~
//            {                                                    //~v@@@R~
//                posTaken--; //after removed                      //~v@@@R~
//                arrayList.remove(posTaken); //once remove taken  //~v@@@R~
//                insert(tdTaken);            //then sort into     //~v@@@R~
//            }                                                    //~v@@@R~
            if (reachStatus==REACH_BEFORE_DISCARD)               //~v@@@R~//~9207R~
            {                                                    //~v@@@R~//~9207R~
                reachDone(Ptd);                                  //~v@@@R~//~9207R~
            }                                                    //~v@@@R~//~9207R~
            setTileSelected(-1);    //no selection to discard    //~v@@@R~//~9207R~
            kanType=0;	//status at 1st kan until discard          //~9208R~
            if (Dump.Y) Dump.println("Players.discardOtherOnClient player="+player+",discardedCtr="+ctrDiscarded);//~v@@@R~
            return true;                                           //~v@@@I~
        }                                                          //~v@@@I~
//        //*********************************************************************//~v@@@I~//~9503R~
//        public void resetDiscardedCtr()                            //~v@@@I~//~9503R~
//        {                                                          //~v@@@I~//~9503R~
//            if (Dump.Y) Dump.println("Player.resetDiscardedCtr player="+player);//~v@@@I~//~9503R~
//            ctrDiscarded=0;                                        //~v@@@I~//~9503R~
//        }                                                          //~v@@@I~//~9503R~
        //*********************************************************************//~v@@@I~
        public boolean isReachAvailable()                          //~v@@@I~
        {                                                          //~v@@@I~
			int stat=reachAvailableStatus();                       //~v@@@I~
			boolean rc=stat!=REACH_NA;                             //~v@@@R~
            if (Dump.Y) Dump.println("Player.isReachAvailable rc="+rc+",stat="+stat);//~v@@@I~
            return rc;                                             //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        public boolean reach()                          //~v@@@R~
        {                                                          //~v@@@I~
//            int pos=posReach;                                    //~v@@@R~
//            if (Dump.Y) Dump.println("Players.reach player="+player+",posreach="+pos+",ctrTaken="+ctrTaken+",ctrDiscard="+ctrDiscarded);//~v@@@R~
//            if (pos>=0)                                          //~v@@@R~
//                return false;                                    //~v@@@R~
//            if (playerCurrent!=player)                           //~v@@@R~
//                return false;                                    //~v@@@R~
//            if (!swLastActionIsDiscard)  //before discard        //~v@@@R~
//            {                                                    //~v@@@R~
//                posReach=ctrTaken-1;                             //~v@@@R~
//                swReachBeforeDiscard=true;                       //~v@@@R~
//            }                                                    //~v@@@R~
//            else                                                 //~v@@@R~
//            {                                                    //~v@@@R~
//                posReach=ctrTaken-1;                             //~v@@@R~
//                swReachDiscarded=true;                           //~v@@@R~
//            }                                                    //~v@@@R~
//            if (Dump.Y) Dump.println("Player.reach player="+player+",posreach="+posReach);//~v@@@R~
//            if (Dump.Y) Dump.println("Player.reach swReachBeforeDiscard="+swReachBeforeDiscard+",swReachDiscarded="+swReachDiscarded);//~v@@@R~
//            return posReach!=0;                                  //~v@@@R~
			int stat=reachAvailableStatus();                       //~v@@@R~
            if (Dump.Y) Dump.println("Player.reach reachStatus="+reachStatus);//~v@@@I~
//            if (stat==REACH_NON)                                 //~v@@@R~
//                return false;                                    //~v@@@R~
            reachStatus=stat;                                      //~v@@@I~
//            if (stat==REACH_AFTER_DISCARD)                         //~v@@@R~//~v@@6R~
//            {                                                      //~v@@@I~//~v@@6R~
//                reachDone(tileLastDiscarded);                      //~v@@@I~//~v@@6R~
//            }                                                      //~v@@@I~//~v@@6R~
            return true;                                           //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~9301I~
        public boolean reachOpen()                                 //~9301I~
        {                                                          //~9301I~
	        reach();                                               //~9301I~
            swOpenReach=true;                                      //~9301I~
            if (Dump.Y) Dump.println("Player.reachOpen player="+player);//~9301I~
            return true;                                           //~9301I~
        }                                                          //~9301I~
        //*********************************************************************//~9A30I~
        public boolean resetReachDoneBeforeDiscard()               //~9A30R~
        {                                                          //~9A30I~
            if (Dump.Y) Dump.println("Players.resetReachDoneBeforeDiscard player="+player+",reachStatus before="+reachStatus+",swOpenReadh before="+swOpenReach);//~9A30I~
            boolean rc=false;                                      //~9A30I~
            if (reachStatus==REACH_BEFORE_DISCARD)	//=1           //~9A30I~
            {                                                      //~9A30I~
	            reachStatus=REACH_NONE;                            //~9A30R~
            	swOpenReach=false;                                 //~9A30R~
                rc=true;                                           //~9A30I~
            }                                                      //~9A30I~
            if (Dump.Y) Dump.println("Players.resetReachDoneBeforeDiscard rc="+rc+",reachStatus after="+reachStatus+",swOpenReadh after="+swOpenReach);//~9A30I~
            return rc;
        }                                                          //~9A30I~
        //*********************************************************************//~v@@@I~
        private void reachDone(TileData Ptd)                       //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.reachDone player="+player);          //~v@@@I~//~va84R~
	        reachStatus=REACH_DONE;                                //~v@@@R~
        	setReachDone();	//save ctr for rech just               //~va11I~
            status|=STF_REACH;                                     //~v@@@I~
            posReach=ctrDiscarded-1;	//draw next of posRiver as Lying//~v@@@R~
            AG.aPointStick.reachDone(player);                            //~v@@@I~
//            tileReach=Ptd;                                       //~v@@@I~
			AG.aAccounts.reachDonePay(player);                     //~9511I~
            Ptd.setReach();                                      //~v@@@I~//~9207R~
            lastReach=player;                                      //~9511I~
            lastReachTD=Ptd;                                       //~9511I~
            ctrReach++;                                            //~9511I~
            if (Dump.Y) Dump.println("Player.reachDone player="+player+",ctrReach="+ctrReach);//~9706I~//~va84R~
        }                                                          //~v@@@I~
        //*****************************************************************************//~va66R~
        //*from UARon reset reach when called ron for the tile discarded calling reach//~va66R~
        //*****************************************************************************//~va66R~
        public void resetReachDone()                               //~9511R~
        {                                                          //~9511I~
            if (Dump.Y) Dump.println("Player.resetReachDone player Discarded player="+player+",swLastActionIsDiscard="+swLastActionIsDiscard+",tileLastDiscarded="+ Utils.toString(tileLastDiscarded));//~9B12R~//~va66R~//~va70R~
//			if (tileLastDiscarded==null || !tileLastDiscarded.isReached())//~9706I~//~9B12R~
  			if (!swLastActionIsDiscard  || !tileLastDiscarded.isReached())//~9B12I~
            {                                                      //~9706I~
	            if (Dump.Y) Dump.println("Player.resetReachDone lastdiscarded is not reach");//~9706I~
            	return;                                            //~9706I~
            }                                                      //~9706I~
	        reachStatus=REACH_NONE;                                //~9511I~
            status&=~(STF_REACH | STF_OPEN);                       //~9511I~
            posReach=0;                                            //~9511I~
            if (lastReachTD!=null)                                       //~9513I~
            {                                                      //~9513I~
	            AG.aPointStick.resetReach();                           //~9511I~//~9513I~
            	lastReachTD.resetReach();                              //~9511I~//~9513R~
            	ctrReach--;                                            //~9511I~//~9513R~
	            AG.aAccounts.resetReachDonePay(lastReach);             //~9511I~//~9513I~
	            lastReachTD=null;                                  //~9708I~
            }                                                      //~9513I~
			lastReach=-1;                                          //~9708I~
            ctrDiscardedReachDone=0;                               //~va11I~
            ctrTakenReachDone=0;                                   //~va11I~
            if (Dump.Y) Dump.println("Player.resetReachDone ctrReach="+ctrReach);//~9706I~
        }                                                          //~9511I~
        //*********************************************************************//~9704I~
        public void resetReachAll()                                //~9704I~
        {                                                          //~9704I~
            if ((status & (STF_REACH | STF_OPEN))!=0)              //~9704I~
            {                                                      //~9704I~
	            if (Dump.Y) Dump.println("Player.resetReachAll reachFlag on player="+player+",ctrReach="+ctrReach+",status="+Integer.toHexString(status));//~9704I~//~9904I~
            	status&=~(STF_REACH | STF_OPEN);                   //~9704I~
            	ctrReach--;                                        //~9704I~
	            AG.aAccounts.resetReachAll(player); //back ptrReach               //~9704I~//~9904R~
            }                                                      //~9704I~
        }                                                          //~9704I~
        //*********************************************************************//~va6gI~
        public void clearReachAll()                                //~va6gI~
        {                                                          //~va6gI~
            if ((status & (STF_REACH | STF_OPEN))!=0)              //~va6gI~
            {                                                      //~va6gI~
	            if (Dump.Y) Dump.println("Player.clearReachAll reachFlag on player="+player+",ctrReach="+ctrReach+",status="+Integer.toHexString(status));//~va6gI~
            	status&=~(STF_REACH | STF_OPEN);                   //~va6gI~
            }                                                      //~va6gI~
        }                                                          //~va6gI~
        //*********************************************************************//~v@@@I~
        //*-1 if not reached                                       //~v@@@I~
        //*********************************************************************//~v@@@I~
        public int getPosReach()                                   //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.getPosReach player="+player+",reachStatus="+reachStatus+",posReach="+posReach);//~v@@@R~
	        if (reachStatus!=REACH_DONE)                           //~v@@@I~
            	return -1;                                         //~v@@@I~
            return posReach;	//posreach is reach posistion -1(reach pos may be picked up from river)//~v@@@R~
        }                                                          //~v@@@I~
//        //*********************************************************************//~v@@@I~
//        private void reachPicked(TileData Ptd)                   //~v@@@I~
//        {                                                        //~v@@@I~
//            if (Dump.Y) Dump.println("Player.reachPicked");      //~v@@@I~
//            reachStatus==REACH_PICKED_FROM_RIVER;                //~v@@@I~
//            tileReach=Ptd;                                       //~v@@@I~
//            Ptd.setReach();                                      //~v@@@I~
//        }                                                        //~v@@@I~
        //*********************************************************************//~v@@@I~
        //*return 0:reach NA,1:reach before discard, 2:reach after discard before next action//~v@@@I~
        //*********************************************************************//~v@@@I~
        public int reachAvailableStatus()                          //~v@@@R~
        {                                                          //~v@@@I~
        	errReach=0;                                            //~v@@6I~
            if (Dump.Y) Dump.println("Player.reachAvalableStatus player="+player+",current="+getCurrentPlayer()+",saveCtrTaken="+saveCtrTaken+",ctrTakenAll="+ctrTakenAll+",reachStatus="+reachStatus+",ctrPair="+ctrPair);//~v@@@R~//~v@@6R~
//      	if (saveCtrTaken!=ctrTakenAll)	//next player taken    //~v@@@R~//~v@@6R~
        	if (player!=getCurrentPlayer())                        //~v@@6I~
            	return REACH_NA;                                   //~v@@@R~
        	if (reachStatus==REACH_DONE)                           //~v@@@I~
            {                                                      //~v@@6I~
            	errReach=R.string.AE_DuplicatedAction;             //~v@@6I~
            	return REACH_NA;                                   //~v@@@R~
            }                                                      //~v@@6I~
        	if (ctrPair!=0)	//picked river                         //~v@@@I~//~v@@6M~
            {                                                      //~v@@6M~
		        if (!isEarthReachable())                           //~v@@6I~
                {                                                  //~v@@6I~
	            	errReach=R.string.AE_CouldNotReach;            //~v@@6I~
            		return REACH_NA;                                   //~v@@@R~//~v@@6I~
                }                                                  //~v@@6I~
            }                                                      //~v@@6M~
            if (!AG.aAccounts.isReachable(player))  //chk score    //~9427I~
            {                                                      //~9427I~
            	errReach=R.string.AE_ShortScoreForReach;           //~9427I~
	            return REACH_NA;                                   //~9427I~
            }                                                      //~9427I~
            if (!swLastActionIsDiscard)  //after discard           //~v@@@I~
            {                                                      //~v@@6I~
            	return REACH_BEFORE_DISCARD;                       //~v@@@I~
            }                                                      //~v@@6I~
//          return REACH_AFTER_DISCARD;                            //~v@@@I~//~v@@6R~
            return REACH_NA;   //disallow reach after discard      //~v@@6I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~//~v@@6R~
        //*for Test                                                //~v@@6R~
        //*********************************************************************//~v@@6R~
        public boolean takePon(TileData Ptd)                       //~v@@@I~//~v@@6R~
        {                                                          //~v@@@I~//~v@@6R~
            if (Dump.Y) Dump.println("Player.takePon");            //~v@@@I~//~v@@6R~
//            swLastActionIsKan=false;                             //~v@@@R~//~v@@6R~
            TileData[] tds=new TileData[PAIRCTR];                   //~v@@@R~//~v@@6R~
            tds[0]=Ptd;                                            //~v@@@I~//~v@@6R~
            tds[1]=arrayList.get(arrayList.size()-1); //TODO        //~v@@@I~//~v@@6R~
            arrayList.remove(arrayList.size()-1);                   //~v@@@I~//~v@@6R~
            tds[2]=arrayList.get(arrayList.size()-1);               //~v@@@I~//~v@@6R~
            arrayList.remove(arrayList.size()-1);                   //~v@@@I~//~v@@6R~
            addPair(tds,TDF_PON);                                  //~v@@@R~//~v@@6R~
            return true; //TODO                                                          //~v@@@I~//~v@@6R~
        }                                                          //~v@@@I~//~v@@6R~
        //*********************************************************************//~v@@@I~
        public boolean takePon(TileData[] Ptds)                    //~v@@@I~
        {                                                          //~v@@@I~
	        return takePonChii(Ptds,TDF_PON);                      //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        //*on client                                               //~vaadI~
        //*********************************************************************//~vaadI~
        public boolean takePonOtherOnClient(TileData[] Ptds)       //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.takePonOtherOnClient player="+player+",Ptds="+TileData.toString(Ptds));//~vaadI~
            return takePonChiiOtherOnClient(Ptds,TDF_PON);         //~v@@@I~//~vaadR~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        //*On Server for All player including robot                //~vaadI~
        //*On Client(OtherOnClient) for PLAYER_YOU                 //~vaadI~
        //*********************************************************************//~vaadI~
        public boolean takePonChii(TileData[] Ptds,int PactionID)  //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.takePonChii player="+player+",Ptds="+TileData.toString(Ptds));//~v@@@I~
            int pos;                                               //~v@@@I~
            if (!Ptds[0].isDiscarded())                            //~v@@@I~
            {                                                      //~v@@@I~
		        if (!removeWithCtrRemain(Ptds[0]))                 //~v@@6I~
            		return false;                                  //~v@@6I~
            }                                                      //~v@@@I~
            if (!Ptds[1].isDiscarded())                            //~v@@@I~
            {                                                      //~v@@@I~
		        if (!removeWithCtrRemain(Ptds[1]))                 //~v@@6I~
            		return false;                                  //~v@@6I~
            }                                                      //~v@@@I~
            if (!Ptds[2].isDiscarded())                            //~v@@@I~
            {                                                      //~v@@@I~
		        if (!removeWithCtrRemain(Ptds[2]))                 //~v@@6I~
            		return false;                                  //~v@@6I~
            }                                                      //~v@@@I~
            addPair(Ptds,PactionID);                               //~v@@@I~
	        setTileSelected(arrayList.size()-1);    //default selection for discard  at pon/chii//~9626I~
            if (Dump.Y) Dump.println("Player.takePonChii return tdsctr="+arrayList.size());//~v@@@I~
            return true;                                           //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        //*from UAPon/UAPon on client                              //~vaadI~
        //*********************************************************************//~vaadI~
        public boolean takePonChiiOtherOnClient(TileData[] Ptds,int PactionID)//~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.takePonChiiOnOtherClient player="+player+",Ptds="+TileData.toString(Ptds));//~v@@@I~
            if (player==PLAYER_YOU)                                //~v@@@I~
        		takePonChii(Ptds,PactionID);                       //~v@@@I~
            else                                                   //~v@@@I~
	            addPair(Ptds,PactionID);                           //~v@@@R~
            if (Dump.Y) Dump.println("Player.takePonChiiOtherOnClient return tdsctr="+arrayList.size());//~v@@@I~
            return true;                                           //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~//~v@@6R~
        //*for Test                                                //~v@@6R~
        //*********************************************************************//~v@@6R~
        public boolean takeChii(TileData Ptd)                      //~v@@@I~//~v@@6R~
        {                                                          //~v@@@I~//~v@@6R~
            if (Dump.Y) Dump.println("Player.takeChii");           //~v@@@I~//~v@@6R~
//            swLastActionIsKan=false;                             //~v@@@R~//~v@@6R~
            TileData[] tds=new TileData[PAIRCTR];                  //~v@@@I~//~v@@6R~
            tds[0]=Ptd;                                            //~v@@@I~//~v@@6R~
            tds[1]=arrayList.get(arrayList.size()-1); //TODO       //~v@@@I~//~v@@6R~
            arrayList.remove(arrayList.size()-1);                  //~v@@@I~//~v@@6R~
            tds[2]=arrayList.get(arrayList.size()-1);              //~v@@@I~//~v@@6R~
            arrayList.remove(arrayList.size()-1);                  //~v@@@I~//~v@@6R~
            addPair(tds,TDF_CHII);                                  //~v@@@I~//~v@@6R~
            return true; //TODO                                    //~v@@@I~//~v@@6R~
        }                                                          //~v@@@I~//~v@@6R~
        //*********************************************************************//~v@@@I~
        public boolean takeChii(TileData[] Ptds)                   //~v@@@I~
        {                                                          //~v@@@I~
	        return takePonChii(Ptds,TDF_CHII);                     //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        public boolean takeChiiOtherOnClient(TileData[] Ptds)      //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.takeChiiOtherOnClient player="+player+",tds="+TileData.toString(Ptds));//~vaadI~
            return takePonChiiOtherOnClient(Ptds,TDF_CHII);        //~v@@@I~//~vaadR~
        }                                                          //~v@@@I~
//        //*********************************************************************//~v@@@I~//~v@@6R~
////      public boolean takeKan(TileData Ptd,TileData Ptdkan,boolean Pswtakeriver)//~v@@@R~//~v@@6R~
//        public int takeKan()                           //~v@@@I~ //~v@@6R~
//        {                                                          //~v@@@I~//~v@@6R~
//            TileData td;                                         //~v@@6R~
//            int rc;                                              //~v@@6R~
//            if (swLastActionIsDiscard)                             //~v@@@I~//~v@@6R~
//            {                                                      //~v@@@I~//~v@@6R~
//                td=tileLastDiscarded;                              //~v@@@I~//~v@@6R~
//                td.setTakenRiver();                                //~v@@@I~//~v@@6R~
//                rc=KAN_RIVER;   //minkan                           //~v@@@I~//~v@@6R~
//            }                                                      //~v@@@I~//~v@@6R~
//            else                                                   //~v@@@I~//~v@@6R~
//            {                                                      //~v@@@I~//~v@@6R~
//                td=tileCurrentTaken;                               //~v@@@I~//~v@@6R~
////              if (chkKanAdd(td))                                //~v@@@I~//~v@@6R~
//                if (setKanAdd(td))                               //~v@@6R~
//                    rc=KAN_ADD;   //kakan                          //~v@@@I~//~v@@6R~
//                else                                               //~v@@@I~//~v@@6R~
//                    rc=KAN_TAKEN;   //ankan                        //~v@@@I~//~v@@6R~
//            }                                                      //~v@@@I~//~v@@6R~
//            if (Dump.Y) Dump.println("Player.takeKan rc="+rc+",td="+td.toString());//~v@@@R~//~v@@6R~
//            if (rc==KAN_ADD)                                           //~v@@@I~//~v@@6R~
//            {                                                      //~v@@@I~//~v@@6R~
//                arrayList.remove(td);                             //~v@@@I~//~v@@6R~
//                tileKanAdded=td;                                   //~v@@@I~//~v@@6R~
//            }                                                      //~v@@@I~//~v@@6R~
//            else                                                   //~v@@@I~//~v@@6R~
//            {                                                      //~v@@@I~//~v@@6R~
//                TileData[] tds=new TileData[PAIRCTR_KAN];          //~v@@@R~//~v@@6R~
////            tds[0]=Ptd;                                          //~v@@@R~//~v@@6R~
////            if (!Pswtakeriver)                                   //~v@@@R~//~v@@6R~
////            {                                                    //~v@@@R~//~v@@6R~
////                arrayList.remove(Ptd);                           //~v@@@R~//~v@@6R~
////                if (Dump.Y) Dump.println("Player.takeKan remove kan");//~v@@@R~//~v@@6R~
////            }                                                    //~v@@@R~//~v@@6R~
////            tds[1]=Ptdkan;                       //TODO          //~v@@@R~//~v@@6R~
////            tds[2]=arrayList.get(arrayList.size()-1);            //~v@@@R~//~v@@6R~
////            arrayList.remove(arrayList.size()-1);                //~v@@@R~//~v@@6R~
////            tds[3]=arrayList.get(arrayList.size()-1);            //~v@@@R~//~v@@6R~
////            arrayList.remove(arrayList.size()-1);                //~v@@@R~//~v@@6R~
//                tds[0]=arrayList.get(0);    //TODO                 //~v@@@R~//~v@@6R~
//                arrayList.remove(0);                               //~v@@@R~//~v@@6R~
//                tds[1]=arrayList.get(0);                           //~v@@@R~//~v@@6R~
//                arrayList.remove(0);                               //~v@@@R~//~v@@6R~
//                tds[2]=arrayList.get(0);                           //~v@@@R~//~v@@6R~
//                arrayList.remove(0);                               //~v@@@R~//~v@@6R~
//                if (rc==KAN_RIVER)                                 //~v@@@R~//~v@@6R~
//                {                                                  //~v@@@R~//~v@@6R~
//                    tds[3]=td;                                    //~v@@@R~//~v@@6R~
//                    addPair(tds,TDF_KAN_RIVER);                    //~v@@@R~//~v@@6R~
//                }                                                  //~v@@@R~//~v@@6R~
//                else                                               //~v@@@R~//~v@@6R~
//                {                                                  //~v@@@R~//~v@@6R~
//                    tds[3]=arrayList.get(0);                       //~v@@@R~//~v@@6R~
//                    arrayList.remove(0);                           //~v@@@R~//~v@@6R~
//                    arrayList.remove(arrayList.size()-1); //last taken tile, remove then//~v@@@R~//~v@@6R~
//                    insert(td);                                   //~v@@@R~//~v@@6R~
//                    addPair(tds,TDF_KAN_TAKEN);                    //~v@@@I~//~v@@6R~
//                }                                                  //~v@@@I~//~v@@6R~
//            }                                                      //~v@@@I~//~v@@6R~
////          add(Ptdkan);                                           //~v@@@R~//~v@@6R~
////          setTileSelected(arrayList.size()-1);    //default selection for discard//~v@@@R~//~v@@6R~
////          tileKan=Ptdkan;                                        //~v@@@R~//~v@@6R~
////            swLastActionIsKan=true;                              //~v@@@R~//~v@@6R~
//            typeKan=rc;                                            //~v@@@I~//~v@@6R~
//            return rc;                                             //~v@@@R~//~v@@6R~
//        }                                                          //~v@@@I~//~v@@6R~
//        //*********************************************************************//~v@@6R~
//        //*get minkan/ankan                                      //~v@@6R~
//        //*********************************************************************//~v@@6R~
//        private TileData serachInHands(TileData Ptd,int Ppos)    //~v@@6R~
//        {                                                        //~v@@6R~
//            int pos=-1;                                          //~v@@6R~
//            for (int ii=Ppos;ii<arrayList.size();ii++)           //~v@@6R~
//            {                                                    //~v@@6R~
//                TileData td=arrayList.get(0);                    //~v@@6R~
//                if (TileData.TDCompareTN(td,Ptd))                //~v@@6R~
//                {                                                //~v@@6R~
//                    pos=ii;                                      //~v@@6R~
//                    break;                                       //~v@@6R~
//                }                                                //~v@@6R~
//            }                                                    //~v@@6R~
//            if (Dump.Y) Dump.println("Player.searchInHands pos="+pos+",Ptd="+Ptd.toString());//~v@@6R~
//            return pos;                                          //~v@@6R~
//        }                                                        //~v@@6R~
        //*********************************************************************//~v@@6I~
        //*search 4 tiles in Hands                                 //~v@@6I~
        //*********************************************************************//~v@@6I~
        private int searchKanTiles(TileData Ptd,int Ppos)          //~v@@6I~
        {                                                          //~v@@6I~
            int pos=-1,ctr=0;                                      //~v@@6I~
	        for (int ii=Ppos;ii<arrayList.size()-(PAIRCTR_KAN-1);ii++)//~v@@6I~
            {                                                      //~v@@6I~
            	TileData td=arrayList.get(ii);                     //~v@@6I~
            	if (!TileData.TDCompareTN(td,Ptd))                 //~v@@6I~
                    break;                                         //~v@@6I~
                ctr++;                                             //~v@@6I~
                if (ctr==PAIRCTR_KAN)                              //~v@@6I~
                {                                                  //~v@@6I~
                	pos=ii-(ctr-1);                                //~v@@6R~
                	break;                                         //~v@@6I~
                }                                                  //~v@@6I~
            }                                                      //~v@@6I~
            if (Dump.Y) Dump.println("Player.searchKanTiles Ppos="+Ppos+",pos="+pos+",Ptd="+Ptd.toString());//~v@@6I~
            return pos;                                            //~v@@6I~
        }                                                          //~v@@6I~
//        //*********************************************************************//~v@@6R~
//        //*get minkan/ankan                                      //~v@@6R~
//        //*********************************************************************//~v@@6R~
//        public int takeKan()                                     //~v@@6R~
//        {                                                        //~v@@6R~
//            TileData td;                                         //~v@@6R~
//            int rc = -1, pos;                                    //~v@@6R~
//            if (swLastActionIsDiscard)                           //~v@@6R~
//            {                                                    //~v@@6R~
//                td = tileLastDiscarded;                          //~v@@6R~
//                pos = searchKanTiles(td, 0);                     //~v@@6R~
//                if (pos >= 0)                                    //~v@@6R~
//                {                                                //~v@@6R~
//                    td.setTakenRiver();                          //~v@@6R~
//                    rc = KAN_RIVER;   //minkan                   //~v@@6R~
//                }                                                //~v@@6R~
//            }                                                    //~v@@6R~
//            else                                                 //~v@@6R~
//            {                                                    //~v@@6R~
//                pos = -1;                                        //~v@@6R~
//                for (int ii = 0; ii < arrayList.size() - (PAIRCTR_KAN - 1); ii++)//~v@@6R~
//                {                                                //~v@@6R~
//                    TileData tdh = arrayList.get(ii);            //~v@@6R~
//                    if (searchKanTiles(tdh, ii) >= 0)            //~v@@6R~
//                    {                                            //~v@@6R~
//                        pos = ii;                                //~v@@6R~
//                        break;                                   //~v@@6R~
//                    }                                            //~v@@6R~
//                }                                                //~v@@6R~
//                if (pos >= 0)                                    //~v@@6R~
//                    rc = KAN_TAKEN;   //ankan                    //~v@@6R~
//            }                                                    //~v@@6R~
//                                                                 //~v@@6R~
//                TileData[] tds=new TileData[PAIRCTR_KAN];        //~v@@6R~
//                tds[0]=arrayList.get(0);    //TODO               //~v@@6R~
//                arrayList.remove(0);                             //~v@@6R~
//                tds[1]=arrayList.get(0);                         //~v@@6R~
//                arrayList.remove(0);                             //~v@@6R~
//                tds[2]=arrayList.get(0);                         //~v@@6R~
//                arrayList.remove(0);                             //~v@@6R~
//                if (rc==KAN_RIVER)                               //~v@@6R~
//                {                                                //~v@@6R~
//                    tds[3]=td;                                   //~v@@6R~
//                    addPair(tds,TDF_KAN_RIVER);                  //~v@@6R~
//                }                                                //~v@@6R~
//                else                                             //~v@@6R~
//                {                                                //~v@@6R~
//                    tds[3]=arrayList.get(0);                     //~v@@6R~
//                    arrayList.remove(0);                         //~v@@6R~
//                    arrayList.remove(arrayList.size()-1); //last taken tile, remove then//~v@@6R~
//                    insert(td);                                  //~v@@6R~
//                    addPair(tds,TDF_KAN_TAKEN);                  //~v@@6R~
//                }                                                //~v@@6R~
//                                                                 //~v@@6R~
////          add(Ptdkan);                                         //~v@@6R~
////          setTileSelected(arrayList.size()-1);    //default selection for discard//~v@@6R~
////          tileKan=Ptdkan;                                      //~v@@6R~
////            swLastActionIsKan=true;                            //~v@@6R~
//            typeKan=rc;                                          //~v@@6R~
//            return rc;                                           //~v@@6R~
//        }                                                        //~v@@6R~
        //*********************************************************************//~v@@6I~
        //*Ptds:4 by selectinfo in UAKan                           //~v@@6R~
        //*rc=-1:err or kanType                                    //~v@@6I~//~9208R~
        //*********************************************************************//~v@@6I~
        public int takeKan(TileData[] Ptds)                    //~v@@6I~
        {                                                          //~v@@6I~
//            TileData td;                                         //~v@@6R~
//            int rc;                                              //~v@@6R~
//            if (swLastActionIsDiscard)                           //~v@@6R~
//            {                                                    //~v@@6R~
//                td=tileLastDiscarded;                            //~v@@6R~
//                td.setTakenRiver();                              //~v@@6R~
//                rc=KAN_RIVER;   //minkan                         //~v@@6R~
//            }                                                    //~v@@6R~
//            else                                                 //~v@@6R~
//            {                                                    //~v@@6R~
//                td=tileCurrentTaken;                             //~v@@6R~
//                if (setKanAdd(td))                               //~v@@6R~
//                    rc=KAN_ADD;   //ka-kan                       //~v@@6R~
//                else                                             //~v@@6R~
//                    rc=KAN_TAKEN;   //an-kan                     //~v@@6R~
//            }                                                    //~v@@6R~
//            if (Dump.Y) Dump.println("Player.takeKan rc="+rc+",td="+td.toString());//~v@@6R~
//            if (rc==KAN_ADD)                                     //~v@@6R~
//            {                                                    //~v@@6R~
//                if (!removeWithCtrRemain(td))                    //~v@@6R~
//                    return -1;                                   //~v@@6R~
//                tileKanAdded=td;                                 //~v@@6R~
//            }                                                    //~v@@6R~
//            else                                                 //~v@@6R~
//            {                                                    //~v@@6R~
//                for (int ii=0;ii<PAIRCTR_KAN-1;ii++)             //~v@@6R~
//                {                                                //~v@@6R~
//                    if (!removeWithCtrRemain(Ptds[ii]))          //~v@@6R~
//                        return -1;                               //~v@@6R~
//                }                                                //~v@@6R~
//                if (!removeWithCtrRemain(td))                    //~v@@6R~
//                    return -1;                                   //~v@@6R~
//                Ptds[PAIRCTR_KAN-1]=td;                          //~v@@6R~
//                if (rc==KAN_RIVER)                               //~v@@6R~
//                    addPair(Ptds,TDF_KAN_RIVER);                 //~v@@6R~
//                else                                             //~v@@6R~
//                    addPair(Ptds,TDF_KAN_TAKEN);                 //~v@@6R~
//            }                                                    //~v@@6R~
//            typeKan=rc;                                          //~v@@6R~
//            return rc;                                           //~v@@6R~
            int rc;                                                //~v@@6I~
            TileData td=Ptds[0];                                   //~v@@6I~
            if (Dump.Y) Dump.println("Player.takeKan tds:"+TileData.toString(Ptds));//~v@@6I~
            if ((td.flag & TDF_KAN_ADD)!=0)                        //~v@@6I~
            {                                                      //~v@@6I~
                tileKanAdded=Ptds[PAIR_KAN_ADDPOS];                //~9208I~
                if (!removeWithCtrRemain(tileKanAdded))	//added tile is on last//~v@@6I~//~9208R~
                    return -1;                                     //~v@@6I~
				int idx=getKanAddEarthIndex(td);                    //~v@@6I~
                if(idx<0)                                          //~v@@6I~
                    return -1;                                     //~v@@6I~
                pairOnEarth[idx]=Ptds;                             //~v@@6I~
                rc=KAN_ADD;   //kakan                              //~v@@6I~
                idxEarthAddKan=idx;                                //~0407I~
            }                                                      //~v@@6I~
            else                                                   //~v@@6I~
            {                                                      //~v@@6I~
            	int delctr;                                        //~v@@6R~
            	if ((td.flag & TDF_KAN_RIVER)!=0)                  //~v@@6I~
                {                                                  //~v@@6I~
        			tileLastDiscarded.setTakenRiver();             //~v@@6I~
                	delctr=PAIRCTR_KAN-1;                          //~v@@6R~
                    rc = KAN_RIVER;   //minkan                     //~v@@6I~
                }                                                  //~v@@6I~
                else                                               //~v@@6I~
                {                                                  //~v@@6I~
                	delctr=PAIRCTR_KAN;                            //~v@@6R~
                    rc = KAN_TAKEN;   //ankan                      //~v@@6I~
				}                                                  //~v@@6I~
                for (int ii=0;ii<delctr;ii++)                      //~v@@6R~
                {                                                  //~v@@6I~
                    if (!removeWithCtrRemain(Ptds[ii]))            //~v@@6I~
                        return -1;                                 //~v@@6I~
                }                                                  //~v@@6I~
                addPair(Ptds);                                     //~v@@6I~
            }                                                      //~v@@6I~
            if (rc!=KAN_RIVER)	//	right most tile in hand may not sorted//~v@@6I~
            {                                                      //~v@@6I~
		        sortTaken();                                       //~v@@6I~
            }                                                      //~v@@6I~
            if (Dump.Y) Dump.println("Player.takeKan lastDiscarded="+TileData.toString(tileLastDiscarded));//~v@@6I~//~9208R~//~0407R~
            if (kanType==0)   //for the case kan by kan taken      //~9208R~
            {                                                      //~9208I~
                kanType=rc;	//status at 1st kan until discard      //~9208R~
                playerKan=player;                                  //~9208I~
            }                                                      //~9208I~
            ctrKan++;                                              //~0407I~
            if (Dump.Y) Dump.println("Player.takeKan return rc="+rc+",ctrKan="+ctrKan+",playerKan="+playerKan);//~0407I~
            return rc;                                             //~v@@6R~
        }                                                          //~v@@6I~
        //*********************************************************************//~9301I~
        public boolean removePair(TileData[] Ptds)                 //~9301I~
        {                                                          //~9301I~
            if (Dump.Y) Dump.println("Player.removePair player="+player+",before:"+TileData.toString(getHands(player)));//~9302I~
            int ctr=0;                                             //~9301I~
            for (TileData td:Ptds)                                 //~9301I~
            {                                                      //~9301I~
                if (removeWithCtrRemain(td))                       //~9301I~
                    ctr++;                                         //~9301I~
            }                                                      //~9301I~
            boolean rc=ctr==Ptds.length;                           //~9301I~//~9302R~
            if (Dump.Y) Dump.println("Player.removePair rc="+rc+",player="+player+",tds:"+TileData.toString(Ptds));//~9301I~
            return rc;
        }                                                          //~9301I~
        //*********************************************************************//~v@@6I~
        //*sort taken into hand                                    //~v@@6I~
        //*********************************************************************//~v@@6I~
        private void sortTaken()                                   //~v@@6I~
        {                                                          //~v@@6I~
            TileData tdTaken=tileCurrentTaken;                     //~v@@6I~
            int posRight=arrayList.size()-1;                       //~v@@6I~
            TileData tdRight=arrayList.get(posRight);              //~v@@6I~
            if (Dump.Y) Dump.println("Player.sortTaken player="+player+",currentTaken="+tdTaken.toString()+",tdRight:"+tdRight.toString());//~v@@6I~
            if (TileData.TDCompare(tdTaken,tdRight,true/*compare ctrRemain*/)==0)	//taken remains to right//~v@@6I~
            {                                                      //~v@@6I~
            	arrayList.remove(posRight); //once remove taken    //~v@@6I~
                insert(tdTaken);            //then sort into       //~v@@6I~
	            if (Dump.Y) Dump.println("Player.sortTaken inserted hand:"+TileData.toString(getHands(player)));//~v@@6I~
            }                                                      //~v@@6I~
        }                                                          //~v@@6I~
        //*********************************************************************//~v@@6I~
        //*rc:kantype                                              //~9208I~
        //*********************************************************************//~9208I~
        public int takeKanOtherOnClient(TileData[] Ptds)           //~v@@6R~
        {                                                          //~v@@6I~
            if (Dump.Y) Dump.println("Players.takeKanOtherOnClient player="+player+",Ptds="+TileData.toString(Ptds));//~v@@6I~//~va91R~
            int rc;                                                //~v@@6I~
            if (player==PLAYER_YOU)                                //~v@@6I~
        		rc=takeKan(Ptds);                                  //~v@@6R~
            else                                                   //~v@@6I~
            {                                                      //~v@@6I~
//                if (setKanAdd(Ptds[PAIRCTR_KAN-1]))              //~v@@6R~
//                    rc=KAN_ADD;   //ka-kan                       //~v@@6R~
//                else                                             //~v@@6R~
//                {                                                //~v@@6R~
                  TileData td=Ptds[0];                           //~v@@6R~//~9217R~
                  if (td.isKanAdd())                             //~v@@6I~//~9217R~
                  {                                              //~9208I~//~9217R~
                      rc=KAN_ADD;   //ka-kan                     //~v@@6I~//~9217R~
                      TileData tdadd=Ptds[PAIR_KAN_ADDPOS];    //~9208I~//~9217R~
                      if (tdadd.isKanAddedTile())               //~9208I~//~9217R~
                      {                                          //~9208I~//~9217R~
                          tileKanAdded=tdadd;                    //~9208I~//~9217R~
                          tileCurrentTaken=tdadd;                //~9208I~//~9217R~
                      }                                          //~9208I~//~9217R~
                      int idx=getKanAddEarthIndex(td);             //~9217I~
                      if(idx<0)                                    //~9217I~
                          return -1;                               //~9217I~
                      pairOnEarth[idx]=Ptds;                       //~9217I~
		              idxEarthAddKan=idx;                          //~0407I~
                  }                                              //~9208I~//~9217R~
                  else                                           //~v@@6I~//~9217R~
                  {                                                //~9217I~
                      if (td.isKanRiver())                           //~v@@6R~//~9217R~
                      {                                              //~v@@6I~//~9217R~
                          tileLastDiscarded.setTakenRiver();         //~v@@6I~//~9217R~
                          rc=KAN_RIVER;   //minkan                   //~v@@6R~//~9217R~
                      }                                              //~v@@6I~//~9217R~
                      else                                           //~v@@6R~//~9217R~
                          rc=KAN_TAKEN;   //an-kan                   //~v@@6R~//~9217R~
                      addPair(Ptds);                                 //~v@@6I~//~9217R~
                  }                                                //~9217I~
//                }                                                //~v@@6R~
            }                                                      //~v@@6I~
            if (kanType==0)   //for the case kan by kan taken      //~9208R~
            {                                                      //~9208I~
                kanType=rc;	//status at 1st kan until discard      //~9208R~
                playerKan=player;                                  //~9208I~
            }                                                      //~9208I~
            ctrKan++;                                              //~0407I~
            if (Dump.Y) Dump.println("Player.takeKanOtherOnClient return rc="+rc+",ctrKan="+ctrKan+",tdsctr="+arrayList.size()+",lastDiscarded="+TileData.toString(tileLastDiscarded));//~v@@6R~//~9208R~//~0407R~
            return rc;                                             //~v@@6R~
        }                                                          //~v@@6I~
        //*********************************************************************//~v@@@I~
        private void  addPair(TileData[] Ptds,int Pflag)           //~v@@@R~
        {                                                          //~v@@@I~
	        pairOnEarth[ctrPair++]=Ptds;                           //~v@@@R~
            if (Dump.Y) Dump.println("Player.addPair player="+player+",Ptds="+TileData.toString(Ptds));//~v@@@I~//~9301R~
            if (Dump.Y) Dump.println("Player.addPair flag="+Integer.toHexString(Pflag)+",result pairctr="+ctrPair);//~v@@@I~//~v@@5R~//~v@@@R~
            Tiles.setFlag(Ptds,Pflag);                             //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@6I~
        private void  addPair(TileData[] Ptds)                     //~v@@6I~
        {                                                          //~v@@6I~
	        pairOnEarth[ctrPair++]=Ptds;                           //~v@@6I~
            if (Dump.Y) Dump.println("Player.addPair without flag player="+player+",ctrPair="+ctrPair+",Ptds:"+TileData.toString(Ptds));//~v@@6I~//~9217R~//~9301R~
        }                                                          //~v@@6I~
        //*********************************************************************//~v@@@I~
        //*add kan,returnn pair to be updated                      //~v@@@I~
        //*********************************************************************//~v@@@I~
        private TileData[] getEarthForAddKan()                     //~v@@@R~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.getEarthForAddKan idxEarthKan="+idxEarthAddKan+",tds:"+TileData.toString(pairOnEarth[idxEarthAddKan]));  //~v@@@I~//~9217I~
            return pairOnEarth[idxEarthAddKan];                    //~v@@@R~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@6I~
        //*search avaliable tile in hand and return index of pairOnEarth//~v@@6I~
        //*********************************************************************//~v@@6I~
        public  int getKanAddEarthIndex()                          //~v@@6R~
        {                                                          //~v@@6I~
            int rc=-1;                                             //~v@@6I~
            if (player==playerCurrent)                             //~v@@6R~
            {                                                      //~v@@6I~
	        	for (int ii=0;ii<ctrPair;ii++)                     //~v@@6I~
            	{                                                  //~v@@6I~
    		        if (Dump.Y) Dump.println("Player.getKanAddEarthIndex pairOnEarth="+TileData.toString(pairOnEarth[ii]));//~v@@6I~
            		TileData tdp=pairOnEarth[ii][0];               //~v@@6I~
                    if (pairOnEarth[ii].length==PAIRCTR_KAN)       //~0406I~
                    	continue;                                  //~0406I~
                    if ((tdp.flag & TDF_PON)==0)                   //~v@@6I~
                    	continue;                                  //~v@@6I~
                    for (int jj=0;jj<arrayList.size();jj++)        //~v@@6I~
                    {                                              //~v@@6I~
                        TileData tdh=arrayList.get(jj);            //~v@@6R~
                        if (TileData.TDCompareTN(tdp,tdh))         //~v@@6R~
                        {                                          //~v@@6R~
                            tileKanAdded=tdh;                      //~v@@6R~
                            rc=ii;                                 //~v@@6R~
                            break;                                 //~v@@6R~
                        }                                          //~v@@6R~
                    }                                              //~v@@6I~
                    if (rc>=0)                                     //~v@@6I~
                        break;                                     //~v@@6I~
                }                                                  //~v@@6I~
            }                                                      //~v@@6I~
            if (Dump.Y) Dump.println("Player.getKanAddEarthIndex idx="+rc+",player="+player+",currentplayer="+playerCurrent+",tileKanAdd="+TileData.toString(tileKanAdded));//~v@@6R~//~9208R~
            return rc;                                             //~v@@6I~
        }                                                          //~v@@6I~
        //*********************************************************************//~v@@6R~
        //*return index of pairOnEarth                             //~v@@6R~
        //*********************************************************************//~v@@6R~
        public  int getKanAddEarthIndex(TileData Ptd)              //~v@@6R~
        {                                                          //~v@@6R~
            TileData[] tds;                                        //~9208I~
            int rc=-1;                                             //~v@@6R~
            for (int ii=0;ii<ctrPair;ii++)                         //~v@@6R~
            {                                                      //~v@@6R~
                tds=pairOnEarth[ii];                    //~v@@6R~  //~9208R~
                TileData td=tds[0];                                //~v@@6R~
                if ((td.flag & TDF_PON)!=0 && TileData.TDCompareTN(td,Ptd))//~v@@6R~
                {                                                  //~v@@6R~
                    rc=ii;                                         //~v@@6R~
                    break;                                         //~v@@6R~//~9208R~
                }                                                  //~v@@6R~
            }                                                      //~v@@6R~
//            if ((TestOption.option & TestOption.TO_KAN_CHANKAN)!=0)//~va11R~
//            {                                                    //~va11R~
//                if (Dump.Y) Dump.println("Player.getKanAddEarthIndex rc="+rc+",@@@@reset to 0 by testoption");//~va11R~
//                rc=0;                                            //~va11R~
//            }                                                    //~va11R~
            if (Dump.Y) Dump.println("Player.getKanAddEarthIndex rc="+rc+",earcthctr="+pairOnEarth.length+",Ptds="+Ptd.toString());//~v@@6R~//~9208R~
            return rc;                                             //~v@@6R~
        }                                                          //~v@@6R~
        //*********************************************************************//~v@@6I~
        public TileData[] getKanAddEarth()                         //~v@@6R~
        {                                                          //~v@@6I~
        	TileData[] tds=null;                                   //~v@@6I~
	        int idx=getKanAddEarthIndex();                         //~v@@6I~
            if (idx>=0)                                            //~v@@6R~
            {                                                      //~v@@6I~
            	tds=pairOnEarth[idx];                              //~v@@6I~
            }                                                      //~v@@6I~
            if (Dump.Y) Dump.println("Player.getKanAddEarth player="+player+"tds:"+(tds==null?"null":TileData.toString(tds)));//~v@@6R~
            return tds;                                            //~v@@6R~
        }                                                          //~v@@6I~
        //*********************************************************************//~v@@6I~
        private TileData[] getKanAddEarth(boolean PswRep)          //~v@@6R~
        {                                                          //~v@@6I~
            if ((TestOption.option & TestOption.TO_KAN_CHANKAN)!=0)//~9208I~
            {                                                      //~9208I~
//            	TestOption.option &= ~TestOption.TO_KAN_CHANKAN;   //~9529I~//~0401R~//~0405R~
//            	TestOption.option |=  TestOption.TO_KAN_TEST;      //~9529I~//~0401R~//~0405R~
				return getKanAddEarthTestChankan(PswRep);   //TO_KAN_CHANKAN TEST//~0322R~
            }                                                      //~9208I~
			TileData[] addKan=null;                                //~v@@6I~
        	int idx=getKanAddEarthIndex();                         //~v@@6R~
            if (idx>=0)                                            //~v@@6I~
            {                                                      //~v@@6I~
				TileData[] tds=pairOnEarth[idx];                   //~v@@6I~
				addKan=new TileData[PAIRCTR_KAN];                  //~v@@6I~
                System.arraycopy(tds,0,addKan,0,PAIRCTR);          //~v@@6I~
                addKan[PAIR_KAN_ADDPOS]=tileKanAdded;              //~v@@6R~
                if (PswRep)                                        //~v@@6M~
                {                                                  //~v@@6I~
	                tileKanAdded.setKanAddedTile();                    //~v@@6I~
                	Tiles.setFlag(addKan,TDF_KAN_ADD);             //~v@@6R~
                	pairOnEarth[idx]=addKan;                       //~v@@6I~
                }                                                  //~v@@6I~
	            if (Dump.Y) Dump.println("Player.getKanAddEarth swRep="+PswRep+",idx="+idx+",earcthctr="+pairOnEarth.length+",added="+tileKanAdded.toString()+",out="+TileData.toString(addKan));//~v@@6I~
            }                                                      //~v@@6I~
            return addKan;                                         //~v@@6I~
        }                                                          //~v@@6I~
        //*********************************************************************//~0407I~
        //*TODO test only                                          //~0407I~
        //*********************************************************************//~0407I~
        private TileData[][] getPonsEarth()                        //~0407I~
        {                                                          //~0407I~
			TileData[][] tdss=new TileData[PAIRS_MAX][];            //~0407I~
            int ctr=0;                                             //~0407I~
            for (int ii=0;ii<PAIRS_MAX;ii++)                       //~0407I~
            {                                                      //~0407I~
				TileData[] tds=pairOnEarth[ii];                               //~0407I~
                if (tds==null)                                     //~0407I~
                	break;                                         //~0407I~
	        	if (Dump.Y) Dump.println("Player.getPonsEarth tds="+TileData.toString(tds));//~0407I~
                if (tds.length!=PAIRCTR)                           //~0407I~
                	continue;                                      //~0407I~
                if ((tds[0].flag & TDF_PON)!=0)                    //~0407I~
                	tdss[ctr++]=tds;                               //~0407I~
            }                                                      //~0407I~
            if (ctr==0)                                            //~0407I~
            	tdss=null;                                         //~0407I~
	        if (Dump.Y) Dump.println("Player.getPonsEarth ctr="+ctr+",tdss="+TileData.toString(tdss));//~0407I~
            return tdss;                                           //~0407I~
        }                                                          //~0407I~
        //*********************************************************************//~9208I~
        //*TODO test only                                          //~9208I~
        //*********************************************************************//~9208I~
        private TileData[] getKanAddEarthTestChankan(boolean PswRep)//~9208I~
        {                                                          //~9208I~
			TileData[] addKan=null;                                //~9208I~
			TileData[] tds=null;
			int idx=-1;//~0406R~
            for (int ii=0;ii<PAIRS_MAX;ii++)                       //~0406I~
            {                                                      //~0406I~
				tds=pairOnEarth[ii];                               //~0406I~
                if (tds==null)                                     //~0406I~
                	break;                                         //~0406I~
	        	if (Dump.Y) Dump.println("Player.getKanAddEarthTestChankan tds="+TileData.toString(tds));//~0406R~
                if (tds.length!=PAIRCTR)                           //~0406I~
                	continue;                                      //~0406I~
                if ((tds[0].flag & TDF_PON)!=0)                    //~0406R~
                {
                    idx=ii;
                    break;                                         //~0406I~
                }
            }                                                      //~0406I~
            if (idx==-1)                                         //~9208I~
            	return null;                                       //~9208I~
			addKan=new TileData[PAIRCTR_KAN];                      //~9208I~
            System.arraycopy(tds,0,addKan,0,PAIRCTR);              //~9208I~
            tileKanAdded=arrayList.get(arrayList.size()-1);        //~9208I~
            addKan[PAIR_KAN_ADDPOS]=tileKanAdded;                  //~9208I~
            if (PswRep)                                            //~9208I~
            {                                                      //~9208I~
	        	tileKanAdded.setKanAddedTile();                        //~9208I~
                Tiles.setFlag(addKan,TDF_KAN_ADD);                 //~9208I~
                pairOnEarth[idx]=addKan;                           //~9208I~
            }                                                      //~9208I~
	        if (Dump.Y) Dump.println("Player.getKanAddEarthTestChankan swRep="+PswRep+",idx="+idx+",earcthctr="+pairOnEarth.length+",added="+tileKanAdded.toString()+",out="+TileData.toString(addKan));//~9208R~
            return addKan;                                         //~9208I~
        }                                                          //~9208I~
        //*********************************************************************//~v@@@I~
        //*add kan,update pair by pon, add taken to end of list    //~v@@@R~
        //*********************************************************************//~v@@@I~
        private boolean setKanAdd(TileData Ptd)                    //~v@@@I~//~v@@6R~
        {                                                          //~v@@@I~
//            int pos=-1;                                            //~v@@@R~//~v@@6R~
            TileData[] addKan=null;                                     //~v@@@I~
            boolean rc=false;                                          //~v@@@I~
        //****************************                             //~v@@@I~
            if (Dump.Y) Dump.println("Player.chkAddKan pairctr="+pairOnEarth.length+",Ptds="+Ptd.toString());//~v@@@R~
//            for (int ii=0;ii<ctrPair;ii++)                             //~v@@@I~//~v@@6R~
//            {                                                      //~v@@@I~//~v@@6R~
//                TileData[] tds=pairOnEarth[ii];                    //~v@@@R~//~v@@6R~
//                if ((tds[0].flag & TDF_PON)!=0 && (tds[0].flag & TDF_KAN_ADD)==0)//~v@@@R~//~v@@6R~
//                {                                                  //~v@@@I~//~v@@6R~
//                    for (int jj=0;jj<tds.length;jj++)              //~v@@@I~//~v@@6R~
//                    {                                              //~v@@@I~//~v@@6R~
//                        if ((tds[jj].flag & TDF_TAKEN_RIVER)!=0)    //TODO     //~v@@@I~//~v@@6R~
//                        {                                          //~v@@@I~//~v@@6R~
//                            addKan=new TileData[tds.length+1];     //~v@@@I~//~v@@6R~
//                            idxEarthAddKan=ii;                     //~v@@@R~//~v@@6R~
//                            break;                                 //~v@@@I~//~v@@6R~
//                        }                                          //~v@@@I~//~v@@6R~
//                    }                                              //~v@@@I~//~v@@6R~
//                    if (idxEarthAddKan>=0)                         //~v@@@R~//~v@@6R~
//                    {                                              //~v@@@I~//~v@@6R~
//                        int ctr=0;                                 //~v@@@I~//~v@@6R~
//                        for (int jj=0;jj<tds.length;jj++)          //~v@@@I~//~v@@6R~
//                        {                                          //~v@@@I~//~v@@6R~
//                            addKan[ctr++]=tds[jj];                 //~v@@@I~//~v@@6R~
//                        }                                          //~v@@@I~//~v@@6R~
//                        if (Dump.Y) Dump.println("Player.chkAddKan added pos="+ctr);//~v@@@R~//~v@@6R~
//                        Ptd.flag|=TDF_KAN_ADDED_TILE;              //~v@@@I~//~v@@6R~
//                        addKan[ctr++]=Ptd;                         //~v@@@I~//~v@@6R~
//                        Tiles.setFlag(addKan,TDF_KAN_ADD);         //~v@@@I~//~v@@6R~
//                        pairOnEarth[idxEarthAddKan]=addKan;        //~v@@@R~//~v@@6R~
//                        rc=true;                                   //~v@@@I~//~v@@6R~
//                        break;                                     //~v@@@I~//~v@@6R~
//                    }                                              //~v@@@I~//~v@@6R~
//                }                                                  //~v@@@I~//~v@@6R~
//            }                                                      //~v@@@I~//~v@@6R~
//            idxEarthAddKan=getKanAddEarth(Ptd);                  //~v@@6I~
//            if (idxEarthAddKan>=0)                               //~v@@6I~
//            {                                                    //~v@@6I~
//                TileData[] tds=pairOnEarth[idxEarthAddKan];      //~v@@6I~
//                addKan=new TileData[tds.length+1];               //~v@@6I~
//                int ctr=0;                                       //~v@@6I~
//                for (int jj=0;jj<tds.length;jj++)                //~v@@6I~
//                {                                                //~v@@6I~
//                    addKan[ctr++]=tds[jj];                       //~v@@6I~
//                }                                                //~v@@6I~
//                Ptd.flag|=TDF_KAN_ADDED_TILE;                    //~v@@6I~
//                addKan[ctr++]=Ptd;                               //~v@@6I~
//                Tiles.setFlag(addKan,TDF_KAN_ADD);               //~v@@6I~
        	addKan=getKanAddEarth(true/*swRep*/);              //~v@@6I~
            if (addKan!=null)                                      //~v@@6I~
            {                                                      //~v@@6I~
                rc=true;                                           //~v@@6I~
            }                                                      //~v@@6I~
            return rc;                                             //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@6I~
        //*reach available if KAN_TAKEN only                       //~v@@6I~
        //*********************************************************************//~v@@6I~
        private boolean isEarthReachable()                         //~v@@6I~
        {                                                          //~v@@6I~
            boolean rc=true;                                       //~v@@6I~
        //****************************                             //~v@@6I~
            for (int ii=0;ii<ctrPair;ii++)                         //~v@@6I~
            {                                                      //~v@@6I~
                TileData td=pairOnEarth[ii][0];                    //~v@@6I~
                if ((td.flag & TDF_KAN_TAKEN)==0)              //~v@@6I~
                {                                                  //~v@@6I~
                	rc=false;                                      //~v@@6I~
                	break;                                         //~v@@6I~
                }                                                  //~v@@6I~
            }                                                      //~v@@6I~
            if (Dump.Y) Dump.println("Player.isEarthReachable rc="+rc+",ctrPair="+ctrPair);//~v@@6I~
            return rc;                                             //~v@@6I~
        }                                                          //~v@@6I~
        //*********************************************************************//~v@@@I~
        private void  savePointPair(Point Prightbottom)            //~v@@@R~
        {                                                          //~v@@@I~
	        pointPairRightBottom=Prightbottom;                     //~v@@@I~
            if (Dump.Y) Dump.println("Player.savePointPair ctr="+ctrPair+", x="+Prightbottom.x+",y="+Prightbottom.y);//~v@@@R~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        private void  savePieceRectForAddKan(Rect Prect)           //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.savePointPair ctr="+ctrPair+",l="+Prect.left+",t="+Prect.top+",r="+Prect.right+",b="+Prect.bottom);//~v@@@I~
            pieceRectForAddKan[ctrPair-1]=Prect;                   //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        private boolean chkComplete()                              //~v@@@I~//~9208R~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.chkComplete player="+player+",lastActionID="+lastActionID+",kanType="+kanType);//~v@@@R~//~9207R~//~9209R~
            int flag=0;	                                           //~v@@@I~
////          if (saveCtrTaken==ctrTakenAll)  //after taken          //~v@@@R~//~9208R~
//            if (lastActionID==GCM_TAKE)     //after taken          //~v@@@I~//~9208R~
//            {                                                      //~v@@@I~//~9208R~
//                flag|=COMPLETE_TAKEN;                              //~v@@@I~//~9208R~
//                if (lastActionID==GCM_KAN)                         //~v@@@I~//~9208R~
//                    if (lastActionID==GCM_KAN)                     //~v@@@I~//~9208R~
//                    {                                              //~v@@@I~//~9208R~
//                        if (typeKan==KAN_RIVER)                    //~v@@@I~//~9208R~
//                            flag|=COMPLETE_KAN_RIVER;   //loss of discarder//~v@@@I~//~9208R~
//                        else                                       //~v@@@I~//~9208R~
//                            flag|=COMPLETE_KAN_TAKEN;   //gain of takener//~v@@@I~//~9208R~
//                    }                                              //~v@@@I~//~9208R~
//            }                                                      //~v@@@I~//~9208R~
////          if (swLastActionIsKan)                                 //~v@@@R~//~9208R~
//            else                                                   //~v@@@I~//~9208R~
//            if (AG.aPlayers.lastActionID==GCM_KAN)                 //~v@@@R~//~9208R~
//            {                                                      //~v@@@I~//~9208R~
//                if (typeKan==KAN_ADD)                              //~v@@@I~//~9208R~
//                    flag|=COMPLETE_KAN_ADD; //loss of kan describer//~v@@@R~//~9208R~
//            }                                                      //~v@@@I~//~9208R~
			switch (kanType)                                       //~9208R~
            {                                                      //~9208I~
            case KAN_ADD:	//chankan                              //~9208I~
				flag=COMPLETE_KAN_ADD; //loss of kan describer     //~9208I~
                break;                                             //~9208I~
            case KAN_RIVER:   //minkan                             //~9208I~
				flag=COMPLETE_KAN_RIVER;   //loss of discarder     //~9208I~
                break;                                             //~9208I~
            case KAN_TAKEN:   //ankan                              //~9208I~
                if (player!=playerKan)                             //~9209I~
					flag=COMPLETE_KAN_TAKEN_OTHER;   //ron for ankan(available for kokusi)//~9209I~
                else                                               //~9209I~
					flag=COMPLETE_KAN_TAKEN;   //gain of takener       //~9208I~//~9209R~
                break;                                             //~9208I~
            default:                                               //~9208I~
                if (lastActionID==GCM_TAKE)                        //~9208I~
					flag=COMPLETE_TAKEN;                           //~9208M~
                else                                               //~9208I~
					flag=COMPLETE_RIVER;                           //~9208M~
            }                                                      //~9208I~
            if ((status & STF_OPEN)!=0)                            //~v@@@I~
            	flag|=COMPLETE_OPEN;                               //~v@@@I~
        	boolean rc=AG.aComplete.chkComplete(this,flag);            //~v@@@I~
            if (Dump.Y) Dump.println("Player.chkComplete player="+player+",rc="+rc+",flag="+flag);//~v@@@R~
            if (rc)                                                //~v@@@I~
	            completeFlag=flag;                                 //~v@@@I~
            else                                                   //~9208I~
	            completeFlag=0;                                    //~9208I~
            playerCompleteFlag=completeFlag;                       //~va66I~
            return rc;                                             //~9208R~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        private int complete()                                     //~v@@@R~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.complete player="+player);//~v@@@I~
        	status|=STF_COMPLETE;                                  //~9315I~
        	AG.aComplete.complete(this,completeFlag);              //~v@@@R~
            setTileRon(completeFlag);                              //~9208I~
            return completeFlag;                                   //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~9A12I~
        private int resetComplete()                                //~9A12I~
        {                                                          //~9A12I~
            if (Dump.Y) Dump.println("Player.resetComplete player="+player);//~9A12I~
//      	status|=STF_COMPLETE;                                  //~9A12I~
        	status&=~STF_COMPLETE;                                 //~9A12I~
        	status|=STF_NOTRONABLE;    //game continue but err/invalid ron//~9A12I~
//      	AG.aComplete.resetComplete(this,completeFlag);         //~9A12I~
//          setTileRon(completeFlag);                              //~9A12I~
            resetTileRon(completeFlag);                            //~9A12I~
            playerCompleteFlag=0;                                  //~va66I~
            return completeFlag;                                   //~9A12I~
        }                                                          //~9A12I~
        //*********************************************************************//~9A14I~
        private void setNotRonable()                               //~9A14I~
        {                                                          //~9A14I~
            if (Dump.Y) Dump.println("Player.setNotRonable player="+player);//~9A14I~
        	status|=STF_NOTRONABLE;    //game continue but err/invalid ron//~9A14I~
        }                                                          //~9A14I~
        //*********************************************************************//~v@@@I~
        private void open()                                        //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.complete player="+player);//~v@@@I~
            status|=STF_OPEN;                               //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        private void setTileSelected(int Ppos)                        //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Player.setTileSelected player="+player+",pos="+Ppos);//~v@@@I~
            selectedPos=Ppos;                                      //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~v@@@I~
        private TileData getTileSelected()                        //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("Player.getTileSelected player="+player+",pos="+selectedPos);//~v@@@I~
        	if (selectedPos<0)                                     //~v@@@I~
            {                                                      //~v@@@I~
//                if (player!=PLAYER_YOU) //TODO test                //~v@@@I~//~v@@6R~
//                {                                                  //~v@@@I~//~v@@6R~
//                    return arrayList.get(0);                       //~v@@@I~//~v@@6R~
//                }                                                  //~v@@@I~//~v@@6R~
	            if (Dump.Y) Dump.println("Player.getTileSelected td=null");//~v@@6I~
            	return null;                                       //~v@@@I~
            }                                                      //~v@@@I~
            TileData td=arrayList.get(selectedPos);                //~v@@@I~
            if (Dump.Y) Dump.println("Player.getTileSelected td="+TileData.toString(td));//~v@@@R~//~9208R~
            return td;                                             //~v@@@I~
        }                                                          //~v@@@I~
        //*********************************************************************//~va11I~
        public void setReachDone()                                      //~va11I~
        {                                                          //~va11I~
            ctrDiscardedReachDone=ctrDiscardedAll;                 //~va11I~
            ctrTakenReachDone=ctrTakenAll;                         //~va11I~
			reachStatus=REACH_DONE;                                //~va11I~
	        if (Dump.Y) Dump.println("Player.setReachDone player="+player+",reachStatus="+reachStatus+",ctrDiscardedAll="+ctrDiscardedAll+",ctrTakenAll="+ctrTakenAll);//~va11I~
       }                                                          //~va11I~
        //*********************************************************************//~va11I~
        public boolean isClosedHand()                              //~va11I~
        {                                                          //~va11I~
            boolean rc=isEarthReachable();                         //~va11I~
	        if (Dump.Y) Dump.println("Player.isClosedHand player="+player+",rc="+rc);//~va11I~
            return rc;
        }                                                           //~va11I~
	}//class Player                                                //~v@@@I~
}//class Players                                                 //~dataR~//~@@@@R~//~v@@@R~
