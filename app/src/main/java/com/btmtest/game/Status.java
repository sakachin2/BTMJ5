//*CID://+va6fR~: update#= 548;                                    //~va6fR~
//**********************************************************************//~v101I~
//2021/03/11 va6f (BUG)when resume ,1st take occures on player currentEswn!=0//~va6fI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/01/07 va60 CalcShanten                                      //~va60I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/04/27 va06:BGM                                              //~va06I~
//2020/04/13 va02:At Server,BackButton dose not work when client app canceled by androiud-Menu button//~va02I~
//**********************************************************************//~va02I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Rect;
import android.text.Html;
import android.text.Spanned;

import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSetting;                             //~v@@@R~

import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.TestOption.*;                    //~v@@@R~

import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

public class Status //extends Handler                              //~v@@@R~
{                                                                  //~0914I~
                                                                   //~v@@@I~
//    public static final int GSF_SETUPEND_SERVER=0x01;            //~v@@@R~
//    public static final int GSF_SETUPEND_ALLCLIENT=0x02;         //~v@@@R~
//    public static final int GSF_SETUPEND_ALL=(GSF_SETUPEND_SERVER|GSF_SETUPEND_ALLCLIENT);//~v@@@R~
    public static final int GSF_GAMEOVER=0x10;                     //~v@@@I~
    public static final int GSF_GAMEOVER_SCORE_FIXED=0x20;         //~9612I~
                                                                   //~v@@@I~
//    public static int player,currentPlayer;                      //~v@@@R~
//    public static int stockPlayer;                               //~v@@@R~
//    public static int posNextTile,posCurrentTile;                //~v@@@R~
//    public static int nextStock;                                 //~v@@@R~
                                                                   //~v@@@I~
    private static Status aStatus;                                 //~v@@@I~
                                                                   //~v@@@I~
    private int flag;                                              //~v@@@I~
                                                                   //~9B30I~
    private int gameStatus;                                        //~v@@@R~
    public static final int GS_INIT               =0;               //~v@@@I~//~9B30M~
    public static final int GS_SETUP              =10;              //~v@@@I~//~9B30M~
    public static final int GS_SETUPEND           =11;             //~v@@@I~//~9B30M~
    public static final int GS_POSITIONING        =12;             //~v@@@R~//~9B30M~
    public static final int GS_POSITION_ACCEPTING =13;             //~v@@@R~//~9B30M~
    public static final int GS_POSITION_ACCEPTED  =14;             //~v@@@R~//~9B30M~
    public static final int GS_READY_TO_STARTGAME =15;             //~v@@@I~//~9B30M~
    public static final int GS_START_GAME         =20;             //~v@@@R~//~9B30M~
    public static final int GS_GAME_STARTED       =21;             //~v@@@R~//~9B30M~
    public static final int GS_READY_TO_NEXTGAME  =30;             //~0322R~
    public static final int GS_READY_TO_NEXTGAME_CONTINUE=31;      //~0322I~
    public static final int GS_READY_TO_NEXTGAME_RESET=32;         //~0307I~//~0322R~
    public static final int GS_BEFORE_DEAL        =40;             //~v@@@R~//~9B30M~
    public static final int GS_DICE_CASTED        =50;             //~v@@@R~//~9B30M~
    public static final int GS_AFTER_DEAL         =60;             //~v@@@R~//~9B30M~
    public static final int GS_COMPLETION_ACCEPTING=71;            //~v@@@I~//~9B30M~
    public static final int GS_COMPLETION_ACCEPTED =72;            //~v@@@R~//~9B30M~
    public static final int GS_SUSPENDGAME         =81;            //~9817I~//~9B30M~
    public static final int GS_SUSPENDGAME_CANCELED=82;            //~9817I~//~9B30M~
//  public static final int GS_ENDGAME_ANYWAY      =90;            //~9B19R~//~9B30M~
                                                                   //~9B30I~
    private int gameCtrSet;                                        //~v@@@I~
    public  int gameCtrGame;                                       //~v@@@R~
    public  int gameCtrDup;                                        //~v@@@R~
    private int gameCtrReachStick;                                 //~v@@@I~
    private boolean swRon;                                             //~v@@@I~
//  private int completeType,completeEswn,completeEswnLooser;      //~v@@@R~
    private int starterGameSets,starterCurrent,playerGameComplete;
    private Complete.Status completeStatus;//~v@@@I~
    public int endGameType;                                        //~v@@@R~
//    private static TileData currentTaken;//taken from stock      //~v@@@R~
    private int gameType,specialRoundEswn;                         //~v@@@R~
    private boolean swAdditionalRound;                             //~9513I~
    private boolean swEndgameSomeone;   //by GCM_ENDGAME           //~9B20I~
    public int ctrSuspendRequest;//,ctrSuspendRequestOld;             //~9818R~//~9822R~
    public boolean[] swEswnSuspendRequest=new boolean[PLAYERS]; //wind seq   //~9818I~//~9822R~
//  public boolean[] swEswnSuspendRequestOld=new boolean[PLAYERS]; //~9820I~//~9822R~
//    public int ctrSpecialRound;                                        //~v@@@I~
    private boolean swSuspendByIOErr;                              //~9A18I~
    private boolean[] swSuspendByIOErrEswn=new boolean[PLAYERS]; //by position//~9A18I~
//  private boolean[] swRestartedIOErr=new boolean[PLAYERS]; //by position//~9A27I~//~9A29R~
    private boolean swIOExceptionInGaming;                         //~9A28I~
//    private boolean swIOExceptionInGamingSendBlocked;            //~0115I~
    private int statusRestart;                                     //~9A29I~
    public static final int RESTART_NONE=0;                        //~9A29I~
    public static final int RESTART_ONCE_IOERR=1;                  //~9A29I~
    public static final int RESTART_RESTARTED=2;                   //~9A29I~
    public static final int RESTART_RESTARTED_ALL=3; 	//all client resposed RESTART_RESTARTED//~9A29I~
    private boolean swGameSuspended;  //finally game end by suspend                             //~0110I~//~0307R~
    private boolean swSuspendRequest; //set by dialog checkbox,open SuspendDlg if set                                    //~0304I~//~0307I~
    private boolean swSuspendGame;    //SuspendDlg once Opened(Svr/Client) for showDismissed                             //~9904R~//~0307R~
//*************************                                        //~v@@@I~
	public Status()                                                //~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("Status Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        AG.aStatus=this;                                           //~v@@@I~
        aStatus=this;                                              //~v@@@I~
    }                                                              //~0914I~//~v@@@R~
	//*************************************************************************//~v@@@I~
	//*from GC, new set of Game by MainFrame Button                //~v@@@R~
	//*************************************************************************//~v@@@I~
	public void startGame()                                             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.startGame");              //~v@@@I~
        flag=0;                                                    //~v@@@I~
	    gameCtrSet=0; gameCtrGame=0; gameCtrDup=0;                 //~v@@@I~
    	gameCtrReachStick=0;                                       //~v@@@I~
//      gameStatus=GS_INIT;                                        //~v@@@I~//~0205R~
        setGameStatus(GS_INIT);                                    //~0205I~
//  	aStatus.resetForNewGame();                                 //~v@@@R~
    	aStatus.resetForNewGameSets();                             //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	public void gameOver()                                         //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.gameOver");               //~v@@@I~
        flag|=GSF_GAMEOVER;                                        //~v@@@R~
//      gameCtrSet=0; gameCtrGame=0; gameCtrDup=0;  //gameCtrGame is used by getCurrentEswn() for BTIO msg, reset it at startGame//~9605R~
    }                                                              //~v@@@I~
	//*************************************************************************//~9612I~
	public void gameOverScoreFixed()                               //~9612I~
    {                                                              //~9612I~
        if (Dump.Y) Dump.println("Status.gameOverScoreFixed");     //~9612I~
        flag|=GSF_GAMEOVER_SCORE_FIXED;                            //~9612I~
    }                                                              //~9612I~
	//*************************************************************************//~v@@@I~
	public static boolean isGameOver()                             //~v@@@R~
    {                                                              //~v@@@I~
        boolean rc=(aStatus.flag & GSF_GAMEOVER)!=0;               //~v@@@R~
        if (Dump.Y) Dump.println("Status.isGameOver rc="+rc);      //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9612I~
	public static boolean isGameOverScoreFixed()                   //~9612I~
    {                                                              //~9612I~
        boolean rc=(aStatus.flag & GSF_GAMEOVER_SCORE_FIXED)!=0;   //~9612I~
        if (Dump.Y) Dump.println("Status.isGameOverScoreFixed rc="+rc);//~9612I~
        return rc;                                                 //~9612I~
    }                                                              //~9612I~
//    //*************************************************************************//~v@@@I~
//    public static boolean isReadyToNextGame()                    //~v@@@I~
//    {                                                            //~v@@@I~
//                                                                 //~v@@@I~
//        boolean rc=!isGameOver() && (aStatus.endGameType!=ENDGAME_NONE);//~v@@@I~
//        if (Dump.Y) Dump.println("Status.isReadyToNextGame rc="+rc);//~v@@@I~
//        return rc;                                               //~v@@@I~
//    }                                                            //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*GC.diceCasted at GS_BEFORE_DEAL   For Test Only             //~v@@@I~//~0222R~//~va60R~
	//*************************************************************************//~v@@@I~
	public static void reset()                                           //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.reset");                  //~v@@@I~
 //       player=0;       //TODO                                     //~v@@@I~
 //       currentPlayer=0;                                           //~v@@@I~
 		aStatus.resetForNewGame();                                 //~v@@@I~
//		resetNextTilePos();                                        //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@M~
    public static void setGameStatus(int Pstatus)                  //~v@@@M~
    {                                                              //~v@@@M~
    	aStatus.gameStatus=Pstatus;                                        //~v@@@M~
        if (Pstatus==GS_GAME_STARTED)                              //~va06I~
    	    GC.playSound(aStatus.gameCtrGame);                     //~va06I~
        if (Dump.Y) Dump.println("Status.setGameStatus status="+aStatus.gameStatus);//~v@@@R~
    }                                                              //~v@@@M~
	//*************************************************************************//~v@@@M~
    public static int getGameStatus()                              //~v@@@M~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("Status.getGameStatus status="+aStatus.gameStatus);//~v@@@R~
    	return aStatus.gameStatus;                                         //~v@@@M~
    }                                                              //~v@@@M~
//    //******************************************************     //~v@@@R~
//    public static int nextPlayer()                               //~v@@@R~
//    {                                                            //~v@@@R~
//        currentPlayer=player;                                    //~v@@@R~
//        if (Dump.Y) Dump.println("Status.nextPlayer "+currentPlayer);//~v@@@R~
//        player=(currentPlayer+1)%PLAYERS;                        //~v@@@R~
//        return currentPlayer;                                    //~v@@@R~
//    }                                                            //~v@@@R~
//    //******************************************************     //~v@@@R~
//    public static void setPlayer(int Pplayer)                    //~v@@@R~
//    {                                                            //~v@@@R~
//        player=Pplayer;                                          //~v@@@R~
//        nextPlayer();                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Status.setPlayer "+currentPlayer);//~v@@@R~
//    }                                                            //~v@@@R~
//    //******************************************************     //~v@@@R~
//    public static int getCurrentPlayer()                         //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Status.getCurrentPlayer player="+currentPlayer);//~v@@@R~
//        return currentPlayer;                                    //~v@@@R~
//    }                                                            //~v@@@R~
//    //******************************************************     //~v@@@R~
//    private static void resetNextTilePos()                       //~v@@@R~
//    {                                                            //~v@@@R~
//        posNextTile=PIECE_CTR_KEEPLEFT+PLAYERS*HANDCTR;          //~v@@@R~
//        if (Dump.Y) Dump.println("Status.setNextTile "+posNextTile);//~v@@@R~
//    }                                                            //~v@@@R~
//    //******************************************************     //~v@@@R~
//    public static int getNextTile()                              //~v@@@R~
//    {                                                            //~v@@@R~
//        int posCurrentTile=posNextTile;                          //~v@@@R~
//        if (Dump.Y) Dump.println("Status.posNextTile "+posCurrentTile);//~v@@@R~
//        posNextTile++;                                           //~v@@@R~
//        return posCurrentTile;                                   //~v@@@R~
//    }                                                            //~v@@@R~
//    //******************************************************     //~v@@@R~
//    public static int getCurrentTile()                           //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Status.posCurrentTile "+posCurrentTile);//~v@@@R~
//        return posCurrentTile;                                   //~v@@@R~
//    }                                                            //~v@@@R~
//    //******************************************************     //~v@@@R~
//    private static void  resetDiscardedCtr()                     //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Status.resetDiscardedCtr");    //~v@@@R~
//    }                                                            //~v@@@R~
    //******************************************************       //~v@@@I~
    //*Rect:(left,top,right,bottom)                                //+va6fI~
    //******************************************************       //+va6fI~
    public static Rect getGameSeq()                                //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.getGameSeq set="+aStatus.gameCtrSet+",game="+aStatus.gameCtrGame+",dup="+aStatus.gameCtrDup+",reach="+aStatus.gameCtrReachStick);//~v@@@R~
        return new Rect(aStatus.gameCtrSet,aStatus.gameCtrGame,aStatus.gameCtrDup,aStatus.gameCtrReachStick);//~v@@@R~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    public static Rect setGameSeq(boolean PswDrawn,boolean Pswdup,boolean PswNextRound)//~0222I~
    {                                                              //~0222I~
	    return setGameSeq(PswDrawn,Pswdup,PswNextRound,false);     //~0222I~
    }                                                              //~0222I~
    public static Rect setGameSeq(boolean PswDrawn,boolean Pswdup,boolean PswNextRound,boolean PswUpdateAcc) //~v@@@R~//~9526R~//~0222R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.setGameSeq swDrawn="+PswDrawn+",swDup="+Pswdup+",set="+aStatus.gameCtrSet+",game="+aStatus.gameCtrGame+",dup="+aStatus.gameCtrDup+",reach="+aStatus.gameCtrReachStick+",swNextRound="+PswNextRound+",updateAcc="+PswUpdateAcc);//~v@@@R~//~9526R~//~0222R~
        if (Pswdup)                                                //~v@@@I~
        {                                                          //~v@@@I~
            aStatus.gameCtrDup++;                                  //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            aStatus.gameCtrGame++;                                 //~9526I~
//            if (aStatus.gameCtrGame>=SET_GAMECTR)                  //~v@@@I~//~9513R~
//            {                                                      //~v@@@I~//~9513R~
//                aStatus.gameCtrGame=0;                             //~v@@@I~//~9513R~
//                if (aStatus.ctrSpecialRound!=0)                               //~v@@@I~//~9513R~
//                    aStatus.gameCtrSet=aStatus.specialRoundEswn;           //~v@@@I~//~9513R~
//                else                                               //~v@@@I~//~9513R~
//                {                                                //~9513R~
////                  aStatus.gameCtrSet++;                          //~v@@@R~//~9513R~
//                    aStatus.gameCtrSet=getNextRound(aStatus.gameCtrSet);//~9513R~
//                }                                                //~9513R~
//            }                                                      //~v@@@I~//~9513R~
//          if (aStatus.gameCtrGame%SET_GAMECTR==0)                //~9526I~
            if (PswNextRound || aStatus.gameCtrGame%SET_GAMECTR==0)//~9526I~
				setNextRound();                                    //~9513I~//~9526R~
            if (PswDrawn)                                          //~v@@@I~
	        	aStatus.gameCtrDup++;                              //~v@@@I~
            else                                                   //~v@@@I~
	        	aStatus.gameCtrDup=0;                              //~v@@@R~
        }                                                          //~v@@@I~
        if (PswDrawn)                                              //~v@@@R~
	    	aStatus.gameCtrReachStick+=AG.aPlayers.ctrReach;       //~v@@@I~
        else                                                       //~v@@@I~
	    	aStatus.gameCtrReachStick=0;                           //~v@@@I~
        if (!Pswdup)                                               //~v@@@I~
        {                                                          //~v@@@M~
    		aStatus.starterCurrent=Players.nextPlayer(aStatus.starterCurrent);//~v@@@M~
        }                                                          //~v@@@M~
        if (PswUpdateAcc)                                          //~0222I~
        {                                                          //~0222I~
        	AG.aAccounts.setCurrentAccountsByESWN();               //~0222I~
        }                                                          //~0222I~
        aStatus.resetForNewGame();                                         //~v@@@I~
        return getGameSeq();                                       //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~9901I~
    //*from Accounts.setGameSeqResume                              //~va60I~
    //******************************************************       //~va60I~
//  public static Rect setGameSeqResume(int PgameSeq[],boolean Pswdup,boolean PswNextRound)//~9901R~//~9904R~
//  public static Rect setGameSeqResume(int PgameSeq[],int PnextgameType,boolean PswNextRound)//~9904I~//~0308R~
    public static Rect setGameSeqResume(int PgameSeq[],int PendgameType,int PnextgameType,boolean PswNextRound)//~0308I~
    {                                                              //~9901I~
        boolean swDrawn=!(PendgameType==EGDR_NORMAL || PendgameType==EGDR_MANGAN_RON);//~0308I~
        if (Dump.Y) Dump.println("Status.setGameSeqResume swDrawn="+swDrawn+",endgameType="+PendgameType+",nextgameType="+PnextgameType+",gameseq="+Arrays.toString(PgameSeq));//~9901I~//~9904R~//~0308R~
        aStatus.gameCtrSet=PgameSeq[0];                            //~9901I~
        aStatus.gameCtrGame=PgameSeq[1];                           //~9901I~
        aStatus.gameCtrDup=PgameSeq[2];                            //~9901I~
        aStatus.gameCtrReachStick=PgameSeq[3];                     //~9901I~
//      if (Pswdup)                                                //~9901I~//~9904R~
        if (PnextgameType==NGTP_CONTINUE)                          //~9904I~
        {                                                          //~9901I~
            aStatus.gameCtrDup++;                                  //~9901I~
        }                                                          //~9901I~
        else                                                       //~9901I~
        if (PnextgameType==NGTP_RESET)                             //~9904I~
        {                                                          //~9904I~
        }                                                          //~9904I~
        else                                                       //~9904I~
        {                                                          //~9901I~
            aStatus.gameCtrGame++;                                 //~9901I~
            if (PswNextRound || aStatus.gameCtrGame%SET_GAMECTR==0)//~9901I~
				setNextRound();                                    //~9901I~
            if (swDrawn)                                          //~9901I~//~0308R~
	        	aStatus.gameCtrDup++;                              //~9901I~//~0308R~
            else                                                   //~9901I~//~0308R~
	        	aStatus.gameCtrDup=0;                              //~9901I~//~0308R~
        }                                                          //~9901I~
//        if (PswDrawn)                                            //~9901I~
//            aStatus.gameCtrReachStick+=AG.aPlayers.ctrReach;     //~9901I~
//        else                                                     //~9901I~
//            aStatus.gameCtrReachStick=0;                         //~9901I~
//      if (!Pswdup)                                               //~9901I~//~9904R~
        if (PnextgameType==NGTP_NEXT)                              //~9904I~
        {                                                          //~9901I~
    		aStatus.starterCurrent=Players.nextPlayer(aStatus.starterCurrent);//~9901I~
        }                                                          //~9901I~
        AG.aAccounts.setCurrentAccountsByESWN();    //RoundsStat.newGame from resetForNewGame needs this//~va60I~
        aStatus.resetForNewGame();                                 //~9901I~
        return getGameSeq();                                       //~9901I~
    }                                                              //~9901I~
    //******************************************************       //~9704I~
    private static Rect setGameSeqReset()                           //~9704I~//~0225R~
    {                                                              //~9704I~
        if (Dump.Y) Dump.println("Status.setGameSeqReset");//~9704I~
//      aStatus.gameCtrReachStick-=AG.aPlayers.ctrReach;           //~9704R~
        aStatus.resetForNewGame();                                 //~9704I~
        return getGameSeq();                                       //~9704I~
    }                                                              //~9704I~
    //******************************************************       //~9513I~
    public static void setNextRound()                              //~9513R~
    {                                                              //~9513I~
    	int type=RuleSetting.getGameSetType();                     //~9513I~
	    if (Dump.Y) Dump.println("Status.setNextRound GameSetType="+type+",before gameCtrSet="+aStatus.gameCtrSet+",gameCtrGame="+aStatus.gameCtrGame);//~9526I~
        switch(type)                                               //~9513I~
        {                                                          //~9513I~
        case GST_ES:                                               //~9513I~
			aStatus.gameCtrGame=0;                                 //~9513I~
            if (aStatus.gameCtrSet==1)                             //~9603I~
	        	aStatus.swAdditionalRound=true;                    //~9603I~
        	aStatus.gameCtrSet++;                                  //~9513R~
        	aStatus.gameCtrSet%=4;                                 //~9513I~//~9526R~
            break;                                                 //~9513I~
        case GST_EN:                                               //~9603I~
			aStatus.gameCtrGame=0;                                 //~9603I~
            if (aStatus.gameCtrSet==3)                             //~9603I~
	        	aStatus.swAdditionalRound=true;                    //~9603I~
        	aStatus.gameCtrSet++;                                  //~9603I~
        	aStatus.gameCtrSet%=4;                                 //~9603I~
            if (aStatus.gameCtrSet==1) //ESWN_S                    //~9603I~
	        	aStatus.gameCtrSet=3;  //ESWN_N                    //~9603I~
            break;                                                 //~9603I~
        case GST_ESWN:                                             //~9513I~
			aStatus.gameCtrGame=0;                                 //~9513I~
        	aStatus.gameCtrSet++;                                  //~9513I~
            if (aStatus.gameCtrSet==3)                             //~9603I~
	        	aStatus.swAdditionalRound=true;                    //~9603I~
        	aStatus.gameCtrSet%=4;                                 //~9513I~
            break;                                                 //~9513I~
        case GST_E:                                                //~9513I~
        	aStatus.swAdditionalRound=true;                                //~9513I~
            break;                                                 //~9513I~
        default:  //GST_EE                                         //~9513R~
        	aStatus.swAdditionalRound=aStatus.gameCtrGame>=8;              //~9513I~
        }                                                          //~9513I~
	    if (Dump.Y) Dump.println("Status.setNextRound after swAdditionalRound="+aStatus.swAdditionalRound+",gameCtrSet="+aStatus.gameCtrSet+",gameCtrGame="+aStatus.gameCtrGame);//~9513R~//~9526R~
    }                                                              //~9513I~
    //******************************************************       //~9513I~
    public static boolean isFinalGame()                            //~9513I~
    {                                                              //~9513I~
//        if ((TestOption.option2 & TO2_FINAL_GAME)!=0)              //~9520I~//~9526M~//~9527R~
//        {                                                          //~9526M~//~9527R~
//            if (aStatus.gameCtrSet==0 && aStatus.gameCtrGame==0 && aStatus.gameCtrDup==0)//~9526I~//~9527R~
//            {                                                      //~9526I~//~9527R~
//                aStatus.gameCtrSet=TestOption.finalGameCtrSet;     //~9526R~//~9527R~
//                aStatus.gameCtrGame=TestOption.finalGameCtrGame;   //~9526R~//~9527R~
//                AG.aAccounts.finalGameTest();                      //~9526I~//~9527R~
//            }                                                      //~9526I~//~9527R~
//        }                                                          //~9526M~//~9527R~
        boolean rc=(aStatus.gameCtrGame+1)%SET_GAMECTR==0;             //~9513I~//~9526R~
		if (rc)		//round 4                                      //~9513I~
        {                                                          //~9513I~
	    	int type=RuleSetting.getGameSetType();                 //~9513I~
            if (Dump.Y) Dump.println("Status.isFinalGame GameSetType="+type);//~9513I~
            switch(type)                                           //~9513I~
            {                                                      //~9513I~
            case GST_ES:                                           //~9513I~
            	rc=aStatus.swAdditionalRound || aStatus.gameCtrSet==1;//~9513I~//~9527R~
                break;                                             //~9513I~
            case GST_EN:                                           //~9603I~
            	rc=aStatus.swAdditionalRound || aStatus.gameCtrSet==3;//~9603I~
                break;                                             //~9603I~
            case GST_ESWN:                                         //~9513I~
            	rc=aStatus.swAdditionalRound || aStatus.gameCtrSet==3;//~9513I~//~9527R~
                break;                                             //~9513I~
            case GST_E:                                            //~9513I~
            	rc=true;                                           //~9527R~
                break;                                             //~9513I~
            default:  //GST_EE                                     //~9513I~
            	rc=aStatus.swAdditionalRound || aStatus.gameCtrGame==7;//~9527R~
            }                                                      //~9513I~
        }                                                          //~9513I~
	    if (Dump.Y) Dump.println("Status.isFinalGame rc="+rc+",swAdditionalRound="+aStatus.swAdditionalRound+",gameCtrGame="+aStatus.gameCtrGame+",gameCtrSet="+aStatus.gameCtrSet);//~9513I~//~9526R~
        return rc;
    }                                                              //~9513I~
    //******************************************************       //~v@@@I~
    private void resetForNewGameSets()                             //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.resetForNewGameSets");    //~v@@@R~
        swRon=false;                                               //~v@@@I~
        endGameType=ENDGAME_NONE;                                //~v@@@I~
    	swGameSuspended=false;                                     //~0110I~
//        ctrSuspendRequest=0;                                       //~9818I~//~9823R~
////      ctrSuspendRequestOld=0;                                    //~9818I~//~9822R~//~9823R~
////      Arrays.fill(swEswnSuspendRequestOld,false);                //~9820I~//~9822R~//~9823R~
//        Arrays.fill(swEswnSuspendRequest,false);                   //~9818I~//~9823R~
        resetSuspendRequest();                                     //~9823I~
//      setSuspendRequest(false);                                           //~0304I~//~0307R~//~0308R~
    	swAdditionalRound=false;                                   //~9527I~
        if ((TestOption.option2 & TO2_FINAL_GAME)!=0)              //~9527I~
        {                                                          //~9527I~
//  		if (aStatus.gameCtrSet==0 && aStatus.gameCtrGame==0 && aStatus.gameCtrDup==0)//~9527I~
//          {                                                      //~9527I~
    			aStatus.gameCtrSet=TestOption.finalGameCtrSet;     //~9527I~
    			aStatus.gameCtrGame=TestOption.finalGameCtrGame;   //~9527I~
        		if (Dump.Y) Dump.println("Status.resetForNewGameSets test finalgame set="+aStatus.gameCtrSet+",game="+aStatus.gameCtrGame);//~9527I~
//              AG.aAccounts.finalGameTest();                      //~9527R~
//          }                                                      //~9527I~
        }                                                          //~9527I~
        if ((TestOption.option3 & TO3_SET_DUPCTR)!=0)              //~va6fI~
        {                                                          //~va6fI~
    		aStatus.gameCtrDup=5;                                  //~va6fI~
        	if (Dump.Y) Dump.println("Status.resetForNewGameSets test set DupCtr set="+aStatus.gameCtrSet+",game="+aStatus.gameCtrGame+",dupctr="+aStatus.gameCtrDup);//~va6fI~
        }                                                          //~va6fI~
        if (AG.aUADelayed!=null)                                   //~9704I~
//        	AG.aUADelayed.resetWaitAll(false/*swRon*/);            //~9704R~//~0226R~
          	AG.aUADelayed.resetWaitAllNewGame();                   //~0226I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    private void resetForNewGame()                                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.resetForNewGame starterCurrent="+aStatus.starterCurrent);        //~v@@@I~//~9526R~
        swRon=false;                                               //~v@@@I~
        endGameType=ENDGAME_NONE;                                  //~v@@@I~
//	    swSuspendByIOErr=false;                                    //~9A18I~//~9A27R~
//	    Arrays.fill(swSuspendByIOErrEswn,false);                   //~9A18I~//~9A27R~
//      ctrSuspendRequestOld=ctrSuspendRequest;	//chk after gameseq advanced//~9818I~//~9822R~
//      System.arraycopy(swEswnSuspendRequest/*src*/,0/*src startpos*/,swEswnSuspendRequestOld/*tgt*/,0/*tgt pos*/,PLAYERS);//~9820I~//~9822R~
//      ctrSuspendRequest=0;                                       //~9818I~//~9823R~
//      Arrays.fill(swEswnSuspendRequest,false);                   //~9818I~//~9823R~
        resetSuspendRequest();                                     //~9823I~
//      setSuspendRequest(false);                                  //~0307I~//~0308R~
        if (AG.aUAEndGame!=null)                                   //~v@@@I~
	        AG.aUAEndGame.newGame();                               //~v@@@I~
//        if (AG.aPlayers!=null)                                   //~v@@@I~
//            AG.aPlayers.resetDiscardedCtr();                     //~v@@@I~
        AG.aUserAction.newGame();                                  //~va66I~
	    clearTable();                                              //~v@@@M~
        AG.aTiles.newGame();                                       //~v@@@R~
        AG.aPlayers.newGame(false/*sw1st*/,aStatus.starterCurrent);//~v@@@I~
        AG.aRoundStat.newGame(false/*sw1st*/,aStatus.gameCtrSet,aStatus.gameCtrGame,aStatus.gameCtrDup);//~va60R~
		if (isFinalGame())                                         //~9520I~
	        AG.aGMsg.drawMsgbar(Utils.getStr(R.string.Info_FinalGame,Status.getStringGameSeq()));//~9520I~
        else                                                       //~9520I~
     	   AG.aGMsg.drawMsgbar(getStringGameSeq());                   //~v@@@I~//~9520R~
//      AG.aUADelayed.resetWaitAll(false/*swRon*/);                //~9704R~//~0226R~
        AG.aUADelayed.resetWaitAllNewGame();                       //~0226I~
        GC.playSound(aStatus.gameCtrGame);                         //~va06M~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    private void clearTable()                                      //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.clearTable");             //~v@@@I~
        AG.aHands.newGame();                                       //~0325I~
        AG.aEarth.newGame();                                       //~v@@@M~
        AG.aRiver.newGame();                                       //~v@@@M~
        AG.aStock.newGame();                                       //~v@@@I~
        AG.aPointStick.newGame();                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    public static void addReach()                                  //~v@@@I~
    {                                                              //~v@@@I~
    	aStatus.gameCtrReachStick++;                                       //~v@@@I~
        if (Dump.Y) Dump.println("Status.addReach result="+aStatus.gameCtrReachStick);//~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*from accounts at position moved                             //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static void setGameStatusNewSet(int Pplayer/*accountIndex*/)//~v@@@R~
    {                                                              //~v@@@I~
    	aStatus.starterGameSets=Pplayer;                           //~v@@@I~
    	aStatus.starterCurrent=Pplayer;                            //~v@@@I~
//  	aStatus.gameStatus=GS_START_GAME;                          //~v@@@R~
        AG.aPlayers.newGame(true/*sw1st*/,Pplayer);                //~v@@@R~
        AG.aRoundStat.newGame(true/*sw1st*/,aStatus.gameCtrSet,aStatus.gameCtrGame,aStatus.gameCtrDup);//~va60R~
        AG.aAccounts.setInitialScore(RuleSetting.getInitialScore());//~v@@@I~
        if (Dump.Y) Dump.println("Status.setGameStatusNewSet status="+aStatus.gameStatus+",player="+Pplayer);//~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9901I~
	//* fromAccounts.positionMovedResume                           //~va6fI~
	//*************************************************************************//~va6fI~
    public static void setGameStatusNewSetResume(int Pplayer/*accountIndex*/,int[] Pscore)//~9901I~
    {                                                              //~9901I~
    	aStatus.starterGameSets=Pplayer;                           //~9901I~
//      if (true) //TODO test                                      //~va6fR~
//      {                                                          //~va6fR~
//        aStatus.starterCurrent=Pplayer;                            //~9901I~//~va6fR~
//        AG.aPlayers.newGame(true/*sw1st*/,Pplayer);                //~9901I~//~va6fR~
//      }                                                          //~va6fR~
//      else                                                       //~va6fR~
//      {                                                          //~va6fR~
    	aStatus.starterCurrent=Accounts.eswnToPlayer(ESWN_E);      //~va6fI~
        AG.aPlayers.newGame(true/*sw1st*/,aStatus.starterCurrent); //~va6fI~
//      }                                                          //~va6fR~
        AG.aAccounts.setInitialScoreResume(RuleSetting.getInitialScore(),Pscore);//~9901I~
        if (Dump.Y) Dump.println("Status.setGameStatusNewSetResume status="+aStatus.gameStatus+",Pplayer="+Pplayer+",starterGameSets="+aStatus.starterGameSets+",starterCurrent="+aStatus.starterCurrent+",score="+Arrays.toString(Pscore));//~9901I~//~va6fR~
    }                                                              //~9901I~
//    //*************************************************************************//~v@@@R~
//    public static int setGameStatusGameComplete()                //~v@@@R~
//    {                                                            //~v@@@R~
//        boolean swRepeat=(aStatus.playerGameComplete==aStatus.starterCurrent);//~v@@@R~
//        return setGameStatusGameComplete(swRepeat);              //~v@@@R~
//    }                                                            //~v@@@R~
	//*************************************************************************//~v@@@I~
    private static int setGameStatusGameComplete(boolean PswDrawn,boolean PswDup,boolean PswNextRound)//~v@@@R~//~9526R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.setGameStatusGameComplete swDrawn="+PswDrawn+",swDup="+PswDup+",swNextRound="+PswNextRound);//~v@@@I~//~9819R~
//  	aStatus.gameStatus=GS_START_GAME;                          //~v@@@I~//~0205R~
        setGameStatus(GS_START_GAME);                              //~0205I~
        boolean swRepeat=PswDup;                                   //~v@@@I~
//      setGameSeq(swRepeat);                                      //~v@@@R~
//      setGameSeq(PswDrawn,swRepeat,PswNextRound);                              //~v@@@I~//~9526R~//~0222R~
        setGameSeq(PswDrawn,swRepeat,PswNextRound,true/*update account eswn*/);//~0222I~
//      aStatus.resetForNewGame();                                 //~v@@@R~
//      AG.aPlayers.newGame(false/*sw1st*/,aStatus.starterCurrent);//~v@@@R~
        if (Dump.Y) Dump.println("Status.setGameStatusGameComplete="+aStatus.gameStatus+",player="+aStatus.starterCurrent);//~v@@@I~
        return aStatus.starterCurrent;                             //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9704I~
    private static void setGameStatusGameReset()                   //~9704I~
    {                                                              //~9704I~
        if (Dump.Y) Dump.println("Status.setGameStatusGameReset"); //~9704I~
//  	aStatus.gameStatus=GS_START_GAME;                          //~9704I~//~0205R~
        setGameStatus(GS_START_GAME);                              //~0205I~
//      setGameSeq(PswDrawn,swRepeat,PswNextRound);                //~9704I~
        setGameSeqReset();                                         //~9704I~
        if (Dump.Y) Dump.println("Status.setGameStatusGameReset="+aStatus.gameStatus+",player="+aStatus.starterCurrent);//~9704I~
    }                                                              //~9704I~
//    //*************************************************************************//~v@@@R~
//    public static void setGameStatusGameCompleteReq(int Pplayer) //~v@@@R~
//    {                                                            //~v@@@R~
//        aStatus.playerGameComplete=Pplayer;                      //~v@@@R~
//        aStatus.gameStatus=GS_COMPLETION_ACCEPTING;              //~v@@@R~
//        if (Dump.Y) Dump.println("Status.setGameStatusGameCompleteReq="+aStatus.gameStatus+",player="+Pplayer);//~v@@@R~
//    }                                                            //~v@@@R~
	//*************************************************************************//~v@@@I~
    public static boolean isIssuedRon()                            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.isIssuedRon="+aStatus.swRon);//~v@@@I~
    	return aStatus.swRon;                                      //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
//    public static void setRon(boolean PswRon,int Peswn,int PcompleteType,int PeswnLooser)//~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Status.setRon swRon="+PswRon+",eswn="+Peswn+",completeType="+PcompleteType);//~v@@@R~
//        aStatus.swRon=PswRon;                                    //~v@@@R~
//        aStatus.completeEswn=Peswn;                              //~v@@@R~
//        aStatus.completeType=PcompleteType;                      //~v@@@R~
//        aStatus.completeEswnLooser=PeswnLooser;                  //~v@@@R~
//    }                                                            //~v@@@R~
	//*************************************************************************//~v@@@I~
    public static void setCompleteStatus(Complete.Status Pstat)    //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.setCompleteStatus stat="+Pstat.toString());//~v@@@I~
        aStatus.completeStatus=Pstat;                              //~v@@@I~
        aStatus.swRon=true;                                                //~v@@@I~
        AG.aComplete.setStatus(Pstat);                             //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9A12I~
    public static void resetCompleteStatus(Complete.Status Pstat)  //~9A12I~
    {                                                              //~9A12I~
        if (Dump.Y) Dump.println("Status.resetCompleteStatus stat="+Pstat.toString());//~9A12I~
//      aStatus.completeStatus=Pstat;                              //~9A12I~
        aStatus.completeStatus=null;                               //~9A12I~
//      aStatus.swRon=true;                                        //~9A12I~
        aStatus.swRon=false;                                       //~9A12I~
//      AG.aComplete.setStatus(Pstat);  //new instance of complete will be created//~9A12I~
    }                                                              //~9A12I~
	//*************************************************************************//~v@@@I~
    public static Complete.Status getCompleteStatus()                         //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.getCompletetStatus "+aStatus.completeStatus.toString());//~v@@@R~
        return aStatus.completeStatus;                             //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static boolean isGaming()                               //~v@@@I~
    {                                                              //~v@@@I~
    	int status=aStatus.gameStatus;                             //~v@@@I~
    	boolean rc=status==GS_GAME_STARTED;   //21                 //~v@@@I~//~0410R~
        if (Dump.Y) Dump.println("Status.isGaming rc="+rc+",status="+status);//~v@@@I~
        return rc;
    }                                                              //~v@@@I~
	//*************************************************************************//~9730I~
	//*In Gameing and Not GameOver declared                        //~9A18I~
	//*************************************************************************//~9A18I~
    public static boolean isGamingNow()                            //~9730I~
    {                                                              //~9730I~
    	boolean rc=isGaming() && !isGameOver();                    //~9730I~
        if (Dump.Y) Dump.println("Status.isGamingNow rc="+rc);     //~9730I~
        return rc;                                                 //~9730I~
    }                                                              //~9730I~
	//*************************************************************************//~va02I~
    public static boolean isGamingNowAndInterRound()               //~va02I~
    {                                                              //~va02I~
    	boolean rc=aStatus.gameStatus>=GS_GAME_STARTED && aStatus.gameStatus<GS_BEFORE_DEAL && !isGameOver();    //21<= && <40//~va02I~
        if (Dump.Y) Dump.println("Status.isGamingNow rc="+rc);     //~va02I~
        return rc;                                                 //~va02I~
    }                                                              //~va02I~
	//*************************************************************************//~0205I~
	//*reject before 1st draw(GS_START_GAME(20) is set at game complete or reset)//~0205I~
	//*GS_GAME_STARTED is set at dealer's fist take                //~0205I~
	//*************************************************************************//~0205I~
    public static boolean isGamingForMenuInGame()                  //~0205I~
    {                                                              //~0205I~
    	int status=aStatus.gameStatus;                             //~0205I~
//  	boolean rc=!(status<GS_START_GAME);                                  //~0205I~//~0224R~//~0322R~
    	boolean rc=status>=GS_START_GAME && status<GS_READY_TO_NEXTGAME;//~0322I~
        if (Dump.Y) Dump.println("Status.isGamingForMenuInGame rc="+rc+",status="+status);//~0205I~
        return rc;                                                 //~0205I~
    }                                                              //~0205I~
	//*************************************************************************//~va02I~
	//*contains period inter round                                 //~va02I~
	//*************************************************************************//~va02I~
    public static boolean isGamingForMenuInGameAndInterRound()     //~va02I~
    {                                                              //~va02I~
    	int status=aStatus.gameStatus;                             //~va02I~
    	boolean rc=status>=GS_START_GAME && status<GS_BEFORE_DEAL; //20<= && <40//~va02I~
        if (Dump.Y) Dump.println("Status.isGamingForMenuInGame rc="+rc+",status="+status);//~va02I~
        return rc;                                                 //~va02I~
    }                                                              //~va02I~
	//*************************************************************************//~v@@@I~
    public static boolean isTileSelectable()                       //~v@@@I~
    {                                                              //~v@@@I~
//  	boolean rc=isGaming();                                     //~v@@@I~//~9730R~
    	boolean rc=isGamingNow();	//before gameover              //~9730I~
        if (Dump.Y) Dump.println("Status.isTileSelectable rc="+rc);//~v@@@R~
        return rc;
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static boolean isAvailableUserAction()                  //~v@@@R~
    {                                                              //~v@@@I~
    	boolean rc=isGaming();                                     //~v@@@I~
        if (Dump.Y) Dump.println("Status.isAvailableUserAction rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@R~
//    public static boolean setupEnd(int Pflag)                    //~v@@@R~
//    {                                                            //~v@@@R~
//        Status s=AG.aStatus;                                     //~v@@@R~
//        boolean rc=false;                                        //~v@@@R~
//        synchronized(s)                                          //~v@@@R~
//        {                                                        //~v@@@R~
//            s.flag|=Pflag;                                       //~v@@@R~
//            rc=(s.flag & GSF_SETUPEND_ALL)==GSF_SETUPEND_ALL;    //~v@@@R~
//            if (rc)                                              //~v@@@R~
//                s.flag &= ~GSF_SETUPEND_ALL;                     //~v@@@R~
//        }                                                        //~v@@@R~
//        if (Dump.Y) Dump.println("Status.setupEnd Pflag="+Pflag+",rc="+rc);//~v@@@R~
//        return rc;                                               //~v@@@R~
//    }                                                            //~v@@@R~
	//*************************************************************************//~v@@@I~
	//*from GC, by endgame button                                  //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static void endGameAnyway()                             //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("Status.endGameAnyway old status="+aStatus.gameStatus);//~v@@@I~
	    aStatus.resetForNewGameSets();                             //~9A28I~
        setGameStatus(GS_INIT);                                    //~v@@@I~//~9B19R~
//      setGameStatus(GS_ENDGAME_ANYWAY); 	//set to GS_INIT at startGame()//~9B19R~
    }                                                              //~v@@@I~
	//*************************************************************************//~9B20I~
	//*by Menu or F1, and GCM_ENDGANME msg                         //~9B20I~
	//*block send/receive until reset by SettingDlg/StartGame Button//~9B20I~
	//*************************************************************************//~9B20I~
    public static void setEndgameSomeone()                         //~9B20I~
    {                                                              //~9B20I~
		if (Dump.Y) Dump.println("Status.setEndgameSomeone gameStatus="+AG.aStatus.gameStatus+",before swEndgameSomeone="+AG.aStatus.swEndgameSomeone);//~9B20I~//~9B30R~
        AG.aStatus.swEndgameSomeone=true;                          //~9B20I~
		if (Dump.Y) Dump.println("Status.setEndgameSomeone after  swEndgameSomeone="+AG.aStatus.swEndgameSomeone);//~9B20I~
    }                                                              //~9B20I~
	//*************************************************************************//~9B20I~
    public static void resetEndgameSomeone()                       //~9B20I~
    {                                                              //~9B20I~
		if (Dump.Y) Dump.println("Status.resetEndgameSomeone before swEndgameSomeone="+AG.aStatus.swEndgameSomeone);//~9B20I~
        AG.aStatus.swEndgameSomeone=false;                         //~9B20I~
		if (Dump.Y) Dump.println("Status.resetEndgameSomeone after  swEndgameSomeone="+AG.aStatus.swEndgameSomeone);//~9B20I~
    }                                                              //~9B20I~
	//*************************************************************************//~9B20I~
    public static boolean isEndgameSomeone()                       //~9B20I~
    {                                                              //~9B20I~
		if (Dump.Y) Dump.println("Status.isEndgameSomeone rc="+AG.aStatus.swEndgameSomeone);//~9B20I~
		return AG.aStatus.swEndgameSomeone;                        //~9B20I~
    }                                                              //~9B20I~
	//*************************************************************************//~v@@@I~
    public static void endGame(int PendgameType,int PnextgameType)  //include EGDR_NORMAL//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Status.endGame endgameType="+PendgameType+",nextgameType="+PnextgameType);//~v@@@R~
    	aStatus.endGameType=PendgameType;                          //~v@@@R~
        boolean swGameOver=PnextgameType==NGTP_GAMEOVER;           //~9519I~
    	boolean swContinue=PnextgameType==NGTP_CONTINUE;           //~9519I~
    	boolean swNextRound=PnextgameType==NGTP_NEXTROUND;                       //~9526I~
        boolean swDrawn=PendgameType!=EGDR_NORMAL;	//DrawnMangan as Drawn at setCtrContinuedGain//~9819I~
        AG.aAccounts.setCtrContinuedGain(swGameOver,swDrawn,swContinue);//before gamectr up//~9519I~
        if (swGameOver)                          //~v@@@I~         //~9519R~
    		aStatus.gameOver();                                            //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	swDrawn=!(PendgameType==EGDR_NORMAL || PendgameType==EGDR_MANGAN_RON);//~v@@@I~//~9519R~
    		setGameStatusGameComplete(swDrawn,swContinue,swNextRound);//~v@@@R~//~9519R~//~9526R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9704I~
    public static void resetGame()                                 //~9704I~
    {                                                              //~9704I~
        if (Dump.Y) Dump.println("Status.resetGame");              //~9704I~
//      AG.aAccounts.setCtrContinuedGain(swGameOver,swDrawn,swContinue);//before gamectr up//~9704I~
//  	setGameStatusGameComplete(swDrawn,swContinue,swNextRound); //~9704I~
    	setGameStatusGameReset();                                  //~9704I~
    }                                                              //~9704I~
	//*************************************************************************//~v@@@I~
    public static Spanned getSpannedGameTitle(String Ptitle)       //~v@@@I~
    {                                                              //~v@@@I~
        String s=getStringGameSeq();                               //~v@@@R~
        if (Dump.Y) Dump.println("Status.getSpannedGameTitle parm="+Ptitle+",s="+s);//~v@@@I~
//		return Html.fromHtml(AG.resource.getString(R.string.Info_GameTitle,s,Ptitle));//~9223I~//~va40R~
  		return Utils.fromHtml(AG.resource.getString(R.string.Info_GameTitle,s,Ptitle));//~va40I~
    }                                                              //~v@@@I~
	//*************************************************************************//~0218I~
    public static Spanned getSpannedGameTitleWithName(String Ptitle,String Pname)//~0218I~
    {                                                              //~0218I~
        String s=getStringGameSeq();                               //~0218I~
        if (Dump.Y) Dump.println("Status.getSpannedGameTitle parm="+Ptitle+",s="+s+",name="+Pname);//~0218I~
//		return Html.fromHtml(AG.resource.getString(R.string.Info_GameTitleWithName,s,Ptitle,Pname));//~va40R~
  		return Utils.fromHtml(AG.resource.getString(R.string.Info_GameTitleWithName,s,Ptitle,Pname));//~va40I~
    }                                                              //~0218I~
	//*************************************************************************//~v@@@I~
    public static String getStringGameSeq()                        //~v@@@I~
    {                                                              //~v@@@I~
//      String s=AG.resource.getString(R.string.Info_GameSeq,GConst.nameESWN[aStatus.gameCtrSet],GConst.gameSeq[aStatus.gameCtrGame],aStatus.gameCtrDup);//~v@@@I~//~9513R~
        int ctr=aStatus.gameCtrGame;                               //~9513I~
//        int ctr10=ctr/10;                                        //~9513R~
//        int ctr1=ctr%10;                                         //~9513R~
//      String s;                                                  //~9513I~//~9527R~
//        if (ctr10==0)                                            //~9513R~
//            s=GConst.gameSeq[ctr1];                              //~9513R~
//        else                                                     //~9513R~
//        if (ctr10==1)                                            //~9513R~
//            s=GConst.gameSeq[9]+GConst.gameSeq[ctr1];            //~9513R~
//        else                                                     //~9513R~
//            s=GConst.gameSeq[ctr10-1]+GConst.gameSeq[9]+GConst.gameSeq[ctr1];//~9513R~
//      if (ctr<10)                                                //~9513I~//~9527R~
//      	s=GConst.gameSeq[ctr];                                 //~9513I~//~9527R~
//      else                                                       //~9513I~//~9527R~
//          s=Integer.toString(ctr+1);                             //~9513I~//~9527R~
//      String msg=AG.resource.getString(R.string.Info_GameSeq,GConst.nameESWN[aStatus.gameCtrSet],s,aStatus.gameCtrDup);//~9513I~//~9527R~
        String msg=AG.resource.getString(R.string.Info_GameSeq,getStringGameRound(),aStatus.gameCtrDup);//~9527I~
        if (Dump.Y) Dump.println("Status.getStringGameSeq msg="+msg);//~v@@@I~//~9513R~
		return msg;                                                  //~v@@@I~//~9513R~
    }                                                              //~v@@@I~
	//*************************************************************************//~9829I~
    public static String getStringGameSeq(int PctrSet,int PctrGame,int PctrDup)//~9829I~
    {                                                              //~9829I~
	    String set_game=getStringGameRound(PctrSet,PctrGame);      //~9829I~
        String msg=AG.resource.getString(R.string.Info_GameSeq,set_game,PctrDup);//~9829I~
        if (Dump.Y) Dump.println("Status.getStringGameSeq ctrSet="+PctrSet+",ctrGame="+PctrGame+",ctrDup="+PctrDup+",out="+msg);//~9829I~
		return msg;                                                //~9829I~
    }                                                              //~9829I~
	//*************************************************************************//~9513I~
    public static String getStringGameRound()                      //~9513I~
    {                                                              //~9513I~
//        int ctr=aStatus.gameCtrGame;                               //~9513I~//~9826R~
//        String postfix=Utils.getStr(R.string.GameSeq);             //~9513I~//~9826R~
//        String s;                                                //~9826R~
//        if (ctr<10)                                                //~9513R~//~9826R~
//            s=GConst.gameSeq[ctr]+postfix;                         //~9513R~//~9826R~
//        else                                                       //~9513I~//~9826R~
//            s=Integer.toString(ctr+1)+postfix;                     //~9513R~//~9826R~
//        int type=RuleSetting.getGameSetType();                     //~9527I~//~9826R~
//        if (type==GST_E || type==GST_EE)                           //~9527I~//~9826R~
//            s=GConst.nameESWN[ESWN_E]+s;                           //~9527I~//~9826R~
//        else                                                       //~9527I~//~9826R~
//            s=GConst.nameESWN[aStatus.gameCtrSet]+s;                   //~9513I~//~9527R~//~9826R~
//        if (Dump.Y) Dump.println("Status.getStringGameRound gamectr="+ctr+",s="+s);//~9513R~//~9826R~
//  	return s;                                                  //~9513I~//~9826I~
	    return getStringGameRound(aStatus.gameCtrSet,aStatus.gameCtrGame);//~9826I~
    }                                                              //~9513I~
	//*************************************************************************//~9826I~
    public static String getStringGameRound(int PctrSet,int PctrGame)//~9826I~
    {                                                              //~9826I~
//      int ctr=aStatus.gameCtrGame;                               //~9826I~
	    String postfix=Utils.getStr(R.string.GameSeq);             //~9826I~
        String s;                                                  //~9826I~
        if (PctrGame<10)                                                //~9826I~
	    	s=GConst.gameSeq[PctrGame]+postfix;                    //~9826I~
        else                                                       //~9826I~
	        s=Integer.toString(PctrGame+1)+postfix;                //~9826I~
    	int type=RuleSetting.getGameSetType();                     //~9826I~
        if (type==GST_E || type==GST_EE)                           //~9826I~
	        s=GConst.nameESWN[ESWN_E]+s;                           //~9826I~
        else                                                       //~9826I~
//          s=GConst.nameESWN[aStatus.gameCtrSet]+s;               //~9826I~
            s=GConst.nameESWN[PctrSet]+s;                          //~9826I~
        if (Dump.Y) Dump.println("Status.getStringGameRound ctrSet="+PctrSet+",ctrGame="+PctrGame+",rc="+s);//~9826I~
		return s;                                                  //~9826I~
    }                                                              //~9826I~
	//*************************************************************************//~v@@@I~
	//*next field at end of set                                    //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static int getDrawnNextGameSeq()                        //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=-1;                                                 //~v@@@I~
	    int set=aStatus.gameCtrSet;                                //~v@@@I~
	    int game=aStatus.gameCtrGame;                              //~v@@@I~
        if (game==PLAYERS-1)	//last game                        //~v@@@I~
        {                                                          //~v@@@I~
        	int topScore=AG.aAccounts.getTopScore();                 //~v@@@I~
        	rc=RuleSetting.getNextFieldEswn(set,topScore);         //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Status.getDrawnNextGameSeq set="+set+",game="+game+",rc="+rc);//~v@@@I~
		return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@I~//~9513R~
//    //*next field at end of set                                    //~v@@@I~//~9513R~
//    //*************************************************************************//~v@@@I~//~9513R~
//    public void setSpecialRound(int PgameType,int PnextRoundEswn)  //~v@@@I~//~9513R~
//    {                                                              //~v@@@I~//~9513R~
//        gameType=PgameType;                                        //~v@@@I~//~9513R~
//        specialRoundEswn=PnextRoundEswn;                           //~v@@@I~//~9513R~
//        ctrSpecialRound++;                                         //~v@@@I~//~9513R~
//    }                                                              //~v@@@I~//~9513R~
    //*************************************************************************//~9817I~
    //*suspend request                                             //~9817R~
    //*rc:1:suspended,0:suspend canceled,-1:already                //~9818R~
    //*************************************************************************//~9817I~
    public int suspendGame(boolean PswSuspend,int Peswn/*wind*/)                     //~9817R~//~9818R~//~9822R~
    {                                                              //~9817I~
    	int rc=-1;                                                 //~9817R~
        if (Dump.Y) Dump.println("Status.suspendGame eswn="+Peswn+",swSuspend="+PswSuspend+",ctrSuspendRequest="+ctrSuspendRequest+",swSuspend="+Arrays.toString(swEswnSuspendRequest));//~9817I~//~9818R~
        if (PswSuspend)                                            //~9817I~
        {                                                          //~9817I~
        	if (!swEswnSuspendRequest[Peswn])                      //~9818I~
            {                                                      //~9818I~
	        	swEswnSuspendRequest[Peswn]=true;                  //~9818I~
        		ctrSuspendRequest++;                                   //~9817I~//~9818R~
            	rc=1;                                              //~9817I~
            }                                                      //~9818I~
        }                                                          //~9817I~
        else                                                       //~9817I~
        {                                                          //~9817I~
        	if (swEswnSuspendRequest[Peswn])                       //~9818I~
            {                                                      //~9817I~
	        	swEswnSuspendRequest[Peswn]=false;                 //~9818I~
    	    	ctrSuspendRequest--;                               //~9817I~
	            rc=0;                                          //~9817I~//~9818R~
            }                                                      //~9817I~
        }                                                          //~9817I~
        if (Dump.Y) Dump.println("Status.suspendGame rc="+rc+",ctrSuspendRequest="+ctrSuspendRequest+",swEswnSuspend="+ Arrays.toString(swEswnSuspendRequest));//~9817I~//~9818R~
        return rc;                                                 //~9817I~
    }                                                              //~9817I~
    //*************************************************************************//~9A12I~
    public int suspendGameResetComplete()                          //~9A12I~
    {                                                              //~9A12I~
        if (Dump.Y) Dump.println("Status.suspendGameResetComplete");//~9A12I~
		Arrays.fill(swEswnSuspendRequest,false);                   //~9A12I~
        int rc=ctrSuspendRequest;                                      //~9A12I~
        ctrSuspendRequest=0;                                       //~9A12I~
        if (Dump.Y) Dump.println("Status.suspendGameResetComplete rc="+rc);//~9A12I~
        return rc;                                                 //~9A12I~
    }                                                              //~9A12I~
    //*************************************************************************//~9823I~
    public void resetSuspendRequest()                              //~9823I~
    {                                                              //~9823I~
        if (Dump.Y) Dump.println("Status.resetSuspendRequest ctrSuspendRequest="+ctrSuspendRequest+",swSuspend="+Arrays.toString(swEswnSuspendRequest));//~9823I~
	    Arrays.fill(swEswnSuspendRequest,false);                   //~9823I~
    	ctrSuspendRequest=0;                                       //~9823I~
	    swSuspendByIOErr=false;                                    //~9A27I~
	    setSuspendRequest(false);                                  //~0307I~
	    swIOExceptionInGaming=false;                               //~9A28I~
	    Arrays.fill(swSuspendByIOErrEswn,false);                   //~9A27I~
//      Arrays.fill(swRestartedIOErr,false);                       //~9A27I~//~9A29R~
        statusRestart=RESTART_NONE;                                //~9A29I~
    }                                                              //~9823I~
    //*************************************************************************//~9904I~
    public static void setSuspendGame(boolean PsuspendGame)               //~9904R~
    {                                                              //~9904I~
        if (Dump.Y) Dump.println("Status.setSuspendGame sw="+PsuspendGame);//~9904R~
    	aStatus.swSuspendGame=PsuspendGame;                                      //~9904R~
    }                                                              //~9904I~
    //*************************************************************************//~9A19I~
    public static boolean isSuspendGame()                          //~9A19I~
    {                                                              //~9A19I~
        if (Dump.Y) Dump.println("Status.isSuspendGame sw="+aStatus.swSuspendGame);//~9A19I~
    	return aStatus.swSuspendGame;                              //~9A19I~
    }                                                              //~9A19I~
    //*************************************************************************//~0110I~
    public void gameSuspended()                                    //~0110I~
    {                                                              //~0110I~
        if (Dump.Y) Dump.println("Status.gameSuspended");           //~0110I~
    	swGameSuspended=true;                                      //~0110I~
    }                                                              //~0110I~
    //*************************************************************************//~0110I~
    public static boolean isGameSuspended()                        //~0110I~
    {                                                              //~0110I~
        if (Dump.Y) Dump.println("Status.isGameSuspended swGameSuspended="+AG.aStatus.swGameSuspended);//~0110I~
    	return AG.aStatus.swGameSuspended;                         //~0110I~
    }                                                              //~0110I~
    //*************************************************************************//~0110I~
    public static void setSuspendByIOErr(int Ppos)                 //~9A18R~
    {                                                              //~9A18I~
    	aStatus.swSuspendByIOErr=true;                             //~9A18R~
    	aStatus.swSuspendByIOErrEswn[Ppos]=true;                   //~9A18I~
        if (Dump.Y) Dump.println("Status.setSuspendByIOErr swEswn="+Arrays.toString(aStatus.swSuspendByIOErrEswn));//~9A18I~
    }                                                              //~9A18I~
    //*************************************************************************//~9904I~
    public static boolean isSuspendByIOErr()                                   //~9904R~//~9A18R~
    {                                                              //~9904I~
        if (Dump.Y) Dump.println("Status.isSuspendByIOErr sw="+aStatus.swSuspendByIOErr);//~9904R~//~9A18R~
    	return aStatus.swSuspendByIOErr;                                      //~9904R~//~9A18R~
    }                                                              //~9904I~
    //*************************************************************************//~9A18I~
    public static boolean[] getSuspendByIOErr()                    //~9A18I~
    {                                                              //~9A18I~
        if (Dump.Y) Dump.println("Status.getSuspendByIOErr sw="+aStatus.swSuspendByIOErr);//~9A18I~
    	return aStatus.swSuspendByIOErrEswn;                       //~9A18I~
    }                                                              //~9A18I~
//    //*************************************************************************//~9A27I~//~9A29R~
//    public static void setRestarted(int Ppos,boolean PswStart)     //~9A27I~//~9A29R~
//    {                                                              //~9A27I~//~9A29R~
//        aStatus.swRestartedIOErr[Ppos]=PswStart;                   //~9A27I~//~9A29R~
//        if (Dump.Y) Dump.println("Status.setRestarted pos="+Ppos+",PswStart="+PswStart+",swRestartedIOErr="+Arrays.toString(aStatus.swRestartedIOErr));//~9A27I~//~9A29R~
//    }                                                              //~9A27I~//~9A29R~
//    //*************************************************************************//~9A27I~//~9A29R~
//    public static void isRestarting(int Ppos)                      //~9A27I~//~9A29R~
//    {                                                              //~9A27I~//~9A29R~
//        boolean rc=aStatus.swRestartedIOErr[Ppos];                 //~9A27I~//~9A29R~
//        if (Dump.Y) Dump.println("Status.isRestarting pos="+Ppos+",rc="+rc+",swRestartedIOErr="+Arrays.toString(aStatus.swRestartedIOErr));//~9A27I~//~9A29R~
//    }                                                              //~9A27I~//~9A29R~
    //*************************************************************************//~9A29I~
    public static void setStatusRestart(int Pstatus)               //~9A29I~
    {                                                              //~9A29I~
        if (Dump.Y) Dump.println("Status.setStatusRestart old="+aStatus.statusRestart+",new="+Pstatus);//~9A29I~
        aStatus.statusRestart=Pstatus;                             //~9A29I~
    }                                                              //~9A29I~
    //*************************************************************************//~9A29I~
    public static int getStatusRestart()                           //~9A29I~
    {                                                              //~9A29I~
        if (Dump.Y) Dump.println("Status.getStatusRestart stat="+aStatus.statusRestart);//~9A29I~
        return aStatus.statusRestart;                              //~9A29I~
    }                                                              //~9A29I~
    //*************************************************************************//~0218I~
    public static boolean isStatusRestarted()                      //~0218R~
    {                                                              //~0218I~
    	boolean rc=//aStatus.statusRestart==RESTART_RESTARTED ||        //~0218I~//~0219R~
    	           aStatus.statusRestart==RESTART_RESTARTED_ALL;   //~0218I~//~0219R~
        if (Dump.Y) Dump.println("Status.isStatusRestarted stat="+aStatus.statusRestart+",rc="+rc);//~0218I~
        return rc;                                                 //~0218I~
    }                                                              //~0218I~
    //*************************************************************************//~9A28I~
    public static void setIOExceptionInGaming(boolean Psw)         //~9A28I~
    {                                                              //~9A28I~
	    aStatus.swIOExceptionInGaming=Psw;                 //~9A28I~
//        aStatus.swIOExceptionInGamingSendBlocked=Psw;            //~0115I~
        if (Psw)                                                 //~9A29I~
		    setStatusRestart(RESTART_ONCE_IOERR);                  //~9A29I~
        if (Dump.Y) Dump.println("Status.setIOExceptionInGaming sw="+Psw);//~9A28I~
    }                                                              //~9A28I~
    //*************************************************************************//~9A28I~
    public static boolean isIOExceptionInGaming()                  //~9A28I~
    {                                                              //~9A28I~
        if (Dump.Y) Dump.println("Status.isIOExceptionInGaming sw="+aStatus.swIOExceptionInGaming);//~9A28I~
        return aStatus.swIOExceptionInGaming;                      //~9A28R~
    }                                                              //~9A28I~
//    //*************************************************************************//~0115I~
//    public static boolean isIOExceptionInGamingSendBlocked()     //~0115I~
//    {                                                            //~0115I~
//        boolean rc=aStatus.swIOExceptionInGamingSendBlocked;     //~0115I~
//        if (Dump.Y) Dump.println("Status.isIOExceptionInGamingSendBlocked rc="+rc+",swIOExceptionInGameing="+aStatus.swIOExceptionInGaming);//~0115I~
//        return rc;                                               //~0115I~
//    }                                                            //~0115I~
    //*************************************************************************//~0304I~
    public static void setSuspendRequest(boolean Psw)              //~0304I~
    {                                                              //~0304I~
        aStatus.swSuspendRequest=Psw;                                     //~0304I~//~0307R~
        if (Dump.Y) Dump.println("Status.setSuspendRequest sw="+Psw);//~0304I~
    }                                                              //~0304I~
    //*************************************************************************//~0304I~
    public static boolean isSuspendRequested()                     //~0304I~
    {                                                              //~0304I~
        boolean rc=aStatus.swSuspendRequest;                              //~0304I~//~0307R~
        if (Dump.Y) Dump.println("Status.isSuspendRequested rc="+rc);//~0304R~
        return rc;
    }                                                              //~0304I~
}//class Status                                                 //~dataR~//~@@@@R~//~v@@@R~
