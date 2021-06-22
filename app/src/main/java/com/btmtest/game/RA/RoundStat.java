//*CID://+va96R~: update#= 240;                                    //~va96R~
//**********************************************************************//~v101I~
//2021/06/14 va96 When win button pushed in Match mode, issue warning for not ronable hand.//~va96I~
//2021/05/01 va8w (Bug)Furiten chk err,pos should be not take point but discarded point.(ron may occur without take(after all pon/chii))//~va8wI~
//2021/04/29 va8v (Bug)chk also not AllInHabd case for fixed1st for sakizukechk, and consider human also//~va8vI~
//2021/04/29 va8u (Bug)ignore furiten/kataagari for Take not AllInHand,chk sakizuke condition only//~va8uI~
//2021/04/20 va8j KataAgari chk+furiten chk for also Human Take/Ron in PlayAloneNotifyMode//~va8jI~
//2021/04/17 va8c for robot ron,2 hancontraint should ignore rinshan,haitei,one shot//~va8cI~
//2021/04/14 va89 (Bug) when reach done, ron by take was not notified//~va89I~
//2021/04/11 va81 (Bug)In notify mode,btn si not highlighen when take//~va81I~
//2021/03/31 va74 va60 ignore robot Ron if Human  ron is cancelable, Now allow schedule next Robot ron if human canceled also when trainingmode without notify option//~va74I~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//**********************************************************************//~1107I~
package com.btmtest.game.RA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
import android.graphics.Point;

import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Robot;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~

import java.util.Arrays;

//********************************************************************************************//~v@@5R~
//player:position of each player on the device; You area always 0(Hands is show at bottom)//~v@@5R~
//********************************************************************************************//~v@@5R~
public class RoundStat                                               //~v@@@R~//~va60R~
{                                                                  //~0914I~
//* to be reset Start ***                                          //~va60I~
	public  RSPlayer[] RSP;      //current eswn seq                //~va60R~//~1114R~
    public  int[] itsExposed=new int[CTR_TILETYPE];  //evaluate wait expecting value//~va60R~//~1115R~
    private int ctrDiscardedAll;                                   //~va60I~
    public int ctrTakenAll;                                        //~1115I~
    public int ctrCallPonKan;                                      //~1115R~
    private int paoEswn4Wind,paoEswn3Dragon,paoEswn4Kan;           //~va60I~
    private boolean[] btsWinOpenReach=new boolean[CTR_TILETYPE];  //evaluate wait expecting value//~va60I~
	public boolean swDiscardableAll;  //no pao and no openreach    //~1126I~//~1201R~
	public boolean swFix2;                                         //~1119I~//~1215M~
	public boolean swUpdateDora;                                   //~1215I~
//* to be reset End ***                                            //~va60I~
    private int[] itsDiscardedAll=new int[CTR_TILETYPE*PIECE_DUPCTR];  //all discarde in the seq of discard,to chk furiten from reach/my discarded//~1130R~
    private int paoTile3Dragon,paoTile4Wind;           //~va60I~
    public  int windRound;                                         //~1114R~
    private int gameCtrGame;                                       //~1114I~
    public  int gameCtrDup;                                        //~1117I~
    private boolean swServer;                                      //~va60I~
//    private boolean[] btsRobot=new boolean[PLAYERS];    //player seq   //~va60I~//~1130R~
	public boolean swThinkRobot,swKanAfterReach,swAnkanRon,swKuitan;                                   //~1111I~//~1118R~
	private int constraintFix2;                                     //~1118I~//~1119R~
	public boolean sw7PairKan;                                     //~1216I~
	public boolean swReachOneShot;                                 //~1219I~
	public boolean swYakuFixLast;                                  //~va8cI~
	public boolean swFuritenReachOK;                               //~va8jR~
//*************************                                        //~v@@@I~
	public RoundStat()                                               //~v@@@R~//~va60R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("RoundStat Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~va60R~
    	swThinkRobot= RuleSetting.isThinkRobot();                   //~va60I~//~1111M~//~1118M~//~1305M~
        AG.aRoundStat=this;                                          //~v@@@I~//~va60R~
        new Shanten();                                             //~1111I~
        new RADiscard();                                           //~va60I~
        new RAReach();                                             //~va60I~
        new RARon();                                               //~1111I~
        new RACall();                                              //~1117I~
    }                                                              //~0914I~//~v@@@R~
    //*********************************************************    //~va60I~
    //*from Status                                                 //~1112I~
    //*********************************************************    //~1112I~
    public  void newGame(boolean Psw1st,int PgameCtrSet,int PgameCtrGame,int PgameCtrDup)//~va60R~//~1117R~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.newGame swThinkRobot="+swThinkRobot+",Psw1st="+Psw1st+",ctrSet="+PgameCtrSet+",ctrGame="+PgameCtrGame);//~1201I~//~1212R~
        swServer=Accounts.isServer();                              //~va60R~
//  	if (!swThinkRobot)     //maintain non discardable by openreach and pao//~1201I~
//      	return;                                                //~1201I~
//      if (!swServer)                                             //~va60I~//~1118M~//~va96R~
//      	return;                                                //~va60I~//~1118M~//~va96R~
        windRound=PgameCtrSet;                                     //~va60R~
        gameCtrGame=PgameCtrGame;                                  //~va60I~
        gameCtrDup=PgameCtrDup;                                    //~1117I~
        if (Psw1st)                                                //~va60I~
		    newGameSetup();	                                        //~1118I~//~1121R~//~1126R~
    	reset();                                                   //~va60M~
    }                                                              //~va60I~
    //*********************************************************    //~1118I~
    //*for 1st round                                               //~1126I~
    //*********************************************************    //~1126I~
    private void newGameSetup()                                    //~1118I~//~1121R~//~1201R~
    {                                                              //~1118I~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~1118I~//~1130R~
//        {                                                          //~1118I~//~1130R~
//            btsRobot[ii]=AG.aAccounts.isRobotPlayer(ii);           //~1118I~//~1130R~
//        }                                                          //~1118I~//~1130R~
//        if (Dump.Y) Dump.println("RoundStat.newGameSetup btsRobot="+Arrays.toString(btsRobot));//~1118I~//~1130R~
        swKanAfterReach= RuleSettingYaku.isAvailableKanAfterReach();//~1118I~
        swAnkanRon=RuleSettingYaku.isAvailableAnkanRon();          //~1118I~
		sw7PairKan=RuleSettingYaku.is7Pair4Pair();                 //~1216I~
        swKuitan=RuleSettingYaku.isTanyaoEarth();                  //~1118I~
        swReachOneShot=RuleSettingYaku.isReachOneShot();           //~1219I~
        constraintFix2=RuleSettingYaku.getYakuFix2Constraint();//~1117I~//~1118I~
		swYakuFixLast=RuleSettingYaku.isYakuFixLast();             //~va8cI~
		swFuritenReachOK=RuleSettingYaku.isFuritenReachOK();       //~va8jR~
    }                                                              //~1118I~
    //*********************************************************    //~v@@@I~
    //*reset each round                                            //~1126I~
    //*********************************************************    //~1126I~
    private void reset()                        //~v@@@I~//~9503R~ //~va60R~//~1201R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("RoundStat.reset");               //~va60I~
        RSP=new RSPlayer[PLAYERS];                                 //~va60I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~va60I~
        {                                                          //~va60I~
        	int player=Accounts.eswnToPlayer(ii);                  //~va60I~
//            RSP[ii]=new RSPlayer(ii/*eswn*/,player,btsRobot[player]);//~va60R~//~1130R~
            boolean swRobot=AG.aAccounts.isDummyByCurrentEswn(ii/*eswn*/);//~1130I~
            RSP[ii]=new RSPlayer(ii/*eswn*/,player,swRobot);       //~1130I~
        }                                                          //~va60I~
        Arrays.fill(itsExposed,0);                                 //~va60R~
        Arrays.fill(btsWinOpenReach,false);                        //~va60I~
        swDiscardableAll=true;                                     //~1126I~
 	   	paoEswn4Wind=-1; paoEswn3Dragon=-1; paoEswn4Kan=-1;       //~va60I~
        ctrDiscardedAll=0;                                         //~va60I~
        ctrTakenAll=0; ctrCallPonKan=0;                            //~1115R~
        swFix2=constraintFix2!=0 && gameCtrDup>=constraintFix2;                         //~1119I~//~1126M~
        swUpdateDora=true;                                          //~1215I~
    }                                                              //~v@@@I~
    //*****************************************************        //~va60I~
    public int[] getItsHandEswn(int Peswn)                         //~va60I~
    {                                                              //~va60I~
    	int[] itsHand=RSP[Peswn].itsHand;                          //~va60I~
        if (Dump.Y) Dump.println("RoundStat.getItsHandEswn eswn="+Peswn+",itsHand="+Utils.toString(itsHand,9));//~va60R~
        return itsHand;                                            //~va60I~
    }                                                              //~va60I~
    //*****************************************************        //~va8jI~
    public int[] getItsHandEswnYou(int Peswn)                      //~va8jI~
    {                                                              //~va8jI~
    	int[] itsHand=RSP[Peswn].getItsHandYou();                  //~va8jI~
        return itsHand;                                            //~va8jI~
    }                                                              //~va8jI~
//    //*****************************************************        //~1112I~//~1129R~
//    public int[] getItsHandRedEswn(int Peswn)                      //~1112I~//~1129R~
//    {                                                              //~1112I~//~1129R~
//        int[] itsHandRed=RSP[Peswn].itsHandRed;                       //~1112I~//~1129R~
//        if (Dump.Y) Dump.println("RoundStat.getItsHandRedEswn eswn="+Peswn+",itsHand="+Utils.toString(itsHandRed,9));//~1112I~//~1129R~
//        return itsHandRed;                                         //~1112I~//~1129R~
//    }                                                              //~1112I~//~1129R~
    //*****************************************************        //~1118I~
    public boolean isRobot(int Peswn)                              //~1118I~
    {                                                              //~1118I~
//  	boolean rc=btsRobot[Peswn];                                //~1118I~//~1130R~
    	boolean rc=RSP[Peswn].swRobot;                             //~1130I~
        if (Dump.Y) Dump.println("RoundStat.isRobot eswn="+Peswn+",rc="+rc);//~1118I~
        return rc;                                                 //~1118I~
    }                                                              //~1118I~
    //*****************************************************        //~1303I~
    public boolean isSmartRobot(int Peswn)                         //~1303I~
    {                                                              //~1303I~
    	boolean rc=isRobot(Peswn) && swThinkRobot;                 //~1303I~
        if (Dump.Y) Dump.println("RoundStat.isSmartRobot eswn="+Peswn+",rc="+rc);//~1303I~
        return rc;                                                 //~1303I~
    }                                                              //~1303I~
    //*****************************************************        //~1126I~
    public boolean isDiscardableAll()                              //~1126I~
    {                                                              //~1126I~
        if (Dump.Y) Dump.println("RoundStat.isDiscardableAll rc="+swDiscardableAll);//~1126I~
        return swDiscardableAll;                                   //~1126I~
    }                                                              //~1126I~
//    //*****************************************************        //~va60I~//~1112R~
//    public TileData[][] getTdssHandEswn(int Peswn)                 //~va60I~//~1112R~
//    {                                                              //~va60I~//~1112R~
//        TileData[][] tdssHand=RSP[Peswn].tdssHand;                //~va60I~//~1112R~
//        if (Dump.Y) Dump.println("RoundStat.getTdsHandEswn eswn="+Peswn+",tdssHand="+TileData.toString(tdssHand));//~va60I~//~1112R~
//        return tdssHand;                                           //~va60I~//~1112R~
//    }                                                              //~va60I~//~1112R~
    //*********************************************************    //~va60I~
    //*from Players setInitialDeal<--Tiles.setinitialDeal          //~1203R~
    //*********************************************************    //~1112I~
    public void deal(int Pplayer,int Peswn)                        //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.deal swServer="+swServer+",player="+Pplayer+",eswn="+Peswn);//~va60I~//~1201R~
        if (!swServer)                                             //~va60I~
        	return;                                                //~va60I~
        TileData[] tds=AG.aPlayers.getHands(Pplayer);                //~va60I~
        if (Dump.Y) Dump.println("RoundStat.deal getHand="+TileData.toString(tds));//~va60I~
        RSP[Peswn].deal(tds);                                      //~va60I~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    //*from Stock                                                  //~1112I~
    //*********************************************************    //~1112I~
    public void drawDora(TileData Ptd)                             //~va60R~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.drawDora swThinkRobot="+swThinkRobot+",swServer="+swServer+",td="+Ptd.toString());//~va60R~//~1201R~
        if (!swServer)                                             //~va60I~//~1201M~
        	return;                                                //~va60I~//~1201M~
    	if (!swThinkRobot)  //maintain itsExposed for isDiscardable//~1201R~
        	return;                                                //~1201R~
        int pos=RAUtils.getPosTile(Ptd);                                   //~va60I~//~1119R~
        itsExposed[pos]++;                                         //~va60R~
        if (Dump.Y) Dump.println("RoundStat.drawDora pos="+pos+",itsExposed="+itsExposed[pos]);//~va60R~
    }                                                              //~va60I~
    //*********************************************************    //~va60M~
    //*from Players after removed from tdsHand                                               //~1112I~//~1128R~
    //*********************************************************    //~1112I~
    public void discard(int Pplayer,TileData Ptd)                  //~va60I~
    {                                                              //~va60M~
        if (Dump.Y) Dump.println("RoundStat.discard swThinkRobot="+swThinkRobot+",swServer="+swServer+",player="+Pplayer+",td="+Ptd.toString());//~va60I~//~va70R~
//  	if (!swServer)                                             //~va60I~//+va96R~
//      	return;                                                //~va60I~//+va96R~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~va60R~
        int pos=RAUtils.getPosTile(Ptd);                                   //~va60M~//~1119R~
        itsExposed[pos]++;                                         //~va60M~
        RSP[eswn].discard(pos,Ptd);    //update itsHand                            //~va60I~//~1128R~
        RSP[eswn].ctrDiscardedAllDiscarded=ctrDiscardedAll;        //~va8wM~
        itsDiscardedAll[ctrDiscardedAll++]=pos;  //for furiten chk //~va8wI~
                                                                   //~1201I~
    	if (!swServer)                                             //+va96I~
        	return;                                                //+va96I~
                                                                   //+va96I~
    	if (!swThinkRobot)                                         //~1201M~
        	return;                                                //~1201M~
                                                                   //~1201I~
//      itsDiscardedAll[ctrDiscardedAll++]=pos;  //for furiten chk //~va60I~//~1201R~//~va8wR~
	    AG.aRACall.otherDiscard(GCM_DISCARD,Pplayer,eswn,Ptd);                    //~1117I~//~1118R~//~1128R~
        if (Dump.Y) Dump.println("RoundStat.discard eswn="+eswn+",itsExposed="+Utils.toString(itsExposed,9));//~va60R~
        if (Dump.Y) Dump.println("RoundStat.discard ctrDiscardedAll="+ctrDiscardedAll+",itsDiscardedAll="+Utils.toString(itsDiscardedAll,10,ctrDiscardedAll));//~va60I~
    }                                                              //~va60M~
    //*********************************************************    //~1128I~
    //*from UADiscard on Server                                              //~1128I~//~1201R~
    //*********************************************************    //~1128I~
    public void timeoutPonKan(int Pplayer,TileData PtdDiscarded)   //~1128R~
    {                                                              //~1128I~
        if (Dump.Y) Dump.println("RoundStat.timeoutToPonKan swThinkRobot="+swThinkRobot+",swServer="+swServer+",player="+Pplayer+",tdDiscard="+PtdDiscarded.toString());//~1128R~//~1201R~
    	if (!swThinkRobot)                                         //~1201I~
        	return;                                                //~1201I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~1128I~
	    AG.aRACall.otherDiscard(GCM_NEXT_PLAYER_PONKAN,Pplayer,eswn,PtdDiscarded);//~1128R~
        if (Dump.Y) Dump.println("RoundStat.timeoutPonKan exit eswn="+eswn+",player="+Pplayer);//~1128I~
    }                                                              //~1128I~
    //************************************************************************//~1201R~
    //*from UAKan on server at before take rinshan to enable to other call ron//~1201R~
    //************************************************************************//~1201R~
    public boolean timeoutRinshanTakable(int Pplayer)              //~1201I~
    {                                                              //~1201I~
        if (Dump.Y) Dump.println("RoundStat.timeoutRinshanTakable swThinkRobot="+swThinkRobot+",swServer="+swServer+",player="+Pplayer);//~1201R~
    	if (!swServer)                                             //~1201M~
        	return false;                                          //~1201M~
    	if (!swThinkRobot)                                         //~1201I~
        	return false;                                          //~1201I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~1201I~
        int kanType=AG.aPlayers.getKanType();                      //~1201I~
        TileData tdKan;                                            //~1201I~
        if (kanType==KAN_ADD)                                      //~1201I~
        	tdKan=AG.aPlayers.getTileKanAdded();	//chk chaknan  //~1201I~
        else                                                       //~1201I~
        if (kanType==KAN_TAKEN)                                    //~1201I~
        	tdKan=AG.aPlayers.getCurrentTaken();  //before rinshantaken,chk ankan ron//~1201I~
        else                                                       //~1201I~
        {                                                          //~1201I~
        	if (Dump.Y) Dump.println("RoundStat.timeoutRinshanTakableRobot return by kantype");//~1201I~
        	return false;                                                //~1201I~
        }                                                          //~1201I~
	    boolean rc=AG.aRACall.otherKan(Pplayer,eswn,kanType,tdKan);//~1201I~
        if (Dump.Y) Dump.println("RoundStat.timeoutRinshanTakableRobot rc="+rc);//~1201I~
        return rc;
    }                                                              //~1201I~
    //*********************************************************    //~1128I~
    //*from Robot.autoTakeTimeout                                  //~1128I~
    //*rc:issued Chii                                              //~1128I~
    //*********************************************************    //~1128I~
    public boolean autoTakeTimeout(int PeswnRobot)                 //~1128I~
    {                                                              //~1128I~
    	boolean rc=false;                                          //~1128I~
        if (Dump.Y) Dump.println("RoundStat.autoTakeTimeout swThinkRobot="+swThinkRobot+",eswnRobot="+PeswnRobot);//~1128I~//~1201R~
    	if (!swServer)                                             //~1128I~//~1201M~
        	return rc;                                             //~1128I~//~1201M~
    	if (!swThinkRobot)                                         //~1201I~
        	return false;                                          //~1201I~
        int player=AG.aRoundStat.RSP[PeswnRobot].player;                  //~1128I~
        int playerDiscarded= Players.prevPlayer(player);            //~1128I~
        int eswnDiscarded=Players.prevPlayer(PeswnRobot);          //~1128I~
        TileData tdDiscarded=AG.aPlayers.getLastDiscarded();             //~1128I~
	    rc=AG.aRACall.autoTakeTimeout(PeswnRobot,playerDiscarded,eswnDiscarded,tdDiscarded);//~1128I~//~1129R~
        if (Dump.Y) Dump.println("RoundStat.autoTakeTimeout exit rc="+rc);//~1128I~//~1129R~
        return rc;                                                 //~1128I~
    }                                                              //~1128I~
    //*********************************************************    //~va60I~
    //*from Players                                                //~1112I~
    //*********************************************************    //~1112I~
    public void takeOne(int Pplayer,int Peswn,TileData Ptd)        //~va60R~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.takeOne swServer="+swServer+",player="+Pplayer+",eswn="+Peswn+",td="+Ptd.toString());//~va60I~
    	if (!swServer)                                             //~va60I~
        	return;                                                //~va60I~
        int pos=RAUtils.getPosTile(Ptd);                                   //~va60I~//~1119R~
		RSP[Peswn].takeOne(pos,Ptd);                         //~va60I~
        ctrTakenAll++;                                             //~1115I~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    //*from Players after remove from hand and pair made                                               //~1112I~//~1126R~
    //*********************************************************    //~1112I~
    public void takePon(int Pplayer,TileData[] Ptds)               //~va60R~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.takePon swServer="+swServer+",player="+Pplayer+",tds="+TileData.toString(Ptds));//~va60I~
    	if (!swServer)                                             //~va60I~
        	return;                                                //~va60I~
        ctrCallPonKan++;                                           //~1115I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~va60R~
        int pos=RAUtils.getPosTile(Ptds[0]);                               //~va60R~//~1119R~
		RSP[eswn].takePon(pos,Ptds);                               //~va60R~
        if (Dump.Y) Dump.println("RoundStat.takePon eswn="+eswn+",ctrCallPonKan="+ctrCallPonKan+",itsExposed="+Utils.toString(itsExposed,9));//~va60R~//~1115R~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    //*from Players                                                //~1112I~
    //*********************************************************    //~1112I~
    public void takeKan(int Pplayer,TileData[] Ptds)                 //~va60R~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.takeKan swServer="+swServer+",player="+Pplayer+",tds="+TileData.toString(Ptds));//~va60I~
    	if (!swServer)                                             //~va60I~
        	return;                                                //~va60I~
        swUpdateDora=true;                                          //~1215I~
        ctrCallPonKan++;                                           //~1115I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~va60R~
        int pos=RAUtils.getPosTile(Ptds[0]);                                //~va60R~//~1119R~
		RSP[eswn].takeKan(pos,Ptds);                               //~va60R~
//  	AG.aRACall.otherKan(Pplayer,eswn,Ptds);   //chk chankan       //~1118I~//~1126R~//~1201R~
        if (Dump.Y) Dump.println("RoundStat.takeKan eswn="+eswn+",ctrCallPonKan="+ctrCallPonKan+",itsExposed="+Utils.toString(itsExposed,9));//~va60R~//~1115R~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    //*from Players                                                //~1112I~
    //*********************************************************    //~1112I~
    public void takeChii(int Pplayer,TileData[] Ptds)                   //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.takeChii player="+Pplayer+",tds="+TileData.toString(Ptds));//~va60I~
    	if (!swServer)                                             //~va60I~
        	return;                                                //~va60I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~va60R~
        RSP[eswn].takeChii(Ptds);                                  //~va60R~
        if (Dump.Y) Dump.println("RoundStat.takeChii eswn="+eswn+",itsExposed="+Utils.toString(itsExposed,9));//~va60R~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    private void setPao3Dragon(int Peswn,int Ppos)                //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.setPao3Dragon eswn="+Peswn+",pos="+Ppos);//~va60I~
    	paoEswn3Dragon=Peswn; paoTile3Dragon=Ppos;                 //~va60I~
        swDiscardableAll=false;                                    //~1126I~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    private void setPao4Wind(int Peswn,int Ppos)                  //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.setPao4Wind eswn="+Peswn+",pos="+Ppos);//~va60I~
    	paoEswn4Wind=Peswn; paoTile4Wind=Ppos;                     //~va60I~
        swDiscardableAll=false;                                    //~1126I~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    private void setPao4Kan(int Peswn)                            //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.setPao4Kan eswn="+Peswn);//~va60I~
    	paoEswn4Kan=Peswn; //3kan player                          //~va60I~//~1112R~
        swDiscardableAll=false;                                    //~1126I~
    }                                                              //~va60I~
    //*********************************************************    //~1112I~
    //*from UAReach on Server                                      //~1112I~
    //*********************************************************    //~1112I~
    public  void reach(int Pplayer)                                //~1112I~
    {                                                              //~1112I~
        if (Dump.Y) Dump.println("RoundStat.reach swThinkRobot="+swThinkRobot+",player="+Pplayer);//~1112I~//~1201R~//~1312R~
    	if (!swThinkRobot)                                         //~1201I~
        	return;                                                //~1201I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~1112I~
        RSP[eswn].reach();    //set furiten ptr                    //~1112I~
    }                                                              //~1112I~
    //*********************************************************    //~va60I~
    //*from UAReach on Server                                      //~va60I~
    //*Robot dose not set openReach                                //~va60I~
    //*********************************************************    //~va60I~
    public  void reachOpen(int Pplayer)                            //~va60I~//~1112R~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.reachOpen swThinkRobot="+swThinkRobot+",player="+Pplayer);//~va60I~//~1112R~//~1201R~
//  	if (!swThinkRobot)   //tusmogiri robot chk openreach       //~1201R~
//      	return;                                                //~1201I~
        AG.aRAReach.getWinTileAll(Pplayer,btsWinOpenReach);  //merge mode     //~va60R~//~1121R~
        swDiscardableAll=false;                                    //~1126I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~va60I~
        RSP[eswn].reach();                                     //~va60I~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    //*rc=null or by point:0:discardable,1:OpenReach, 2:pao 3Dragon or 4 Wind, 3:pao 4Kan//~va60I~//~1120R~
    //*********************************************************    //~va60I~
    public Point chkDiscardable(int Peswn,int Ppos)                //~va60R~
    {                                                              //~va60I~
    	Point rc=null;                                             //~va60R~
        if (swDiscardableAll)                                      //~1126I~
        {                                                          //~1126I~
	        if (Dump.Y) Dump.println("RoundStat.chkDiscardable return null by swDiscadableAll pos="+Ppos+",eswn="+Peswn);//~1126I~
			return null;                                           //~1126I~
        }                                                          //~1126I~
        if (btsWinOpenReach[Ppos])	//not open reach called,robot do not call open reach//~va60R~
        	rc=new Point(ERR_DISCARD_OPENREACH,0);                 //~va60I~
        else                                                       //~va60I~
        {                                                          //~va60I~
        	rc=chkPao(Peswn,Ppos);                                  //~va60I~
        }                                                          //~va60I~
        if (Dump.Y) Dump.println("RoundStat.chkDiscardable pos="+Ppos+",eswn="+Peswn+",rc="+Utils.toString(rc)+",btsWinOpenReach="+Utils.toString(btsWinOpenReach,9));//~va60R~
        return rc;
    }                                                              //~va60I~
    //*********************************************************    //~1121I~
    public boolean isDiscardable(int Peswn,int Ppos)               //~1121R~
    {                                                              //~1121I~
        boolean rc=!btsWinOpenReach[Ppos] && chkPao(Peswn,Ppos)==null;//~1121I~
        if (Dump.Y) Dump.println("RoundStat.isDiscardable pos="+Ppos+",eswn="+Peswn+",rc="+rc);//~1121I~
        return rc;                                                 //~1121I~
    }                                                              //~1121I~
    //*********************************************************    //~va60I~
    //*return Point(po type,target playerEswn) or null                    //~1113I~//~1120R~
    //*********************************************************    //~1113I~
    public Point chkPao(int Peswn/*You*/,int Ppos)                 //~va60R~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.chkPao pos="+Ppos+",eswn3d="+paoEswn3Dragon+",pos3d="+paoTile3Dragon);//~1112I~
        if (Dump.Y) Dump.println("RoundStat.chkPao eswn4w="+paoEswn4Wind+",pos4w="+paoTile4Wind+",eswn4kan="+paoEswn4Kan);//~1112I~
    	Point rc=null;                                             //~va60I~
//        if (paoEswn3Dragon!=-1 && paoEswn3Dragon!=Peswn)    //not You//~va60I~//~1112R~
//            rc=new Point(ERR_DISCARD_PAO_3DRAGON,paoTile3Dragon);       //~va60I~//~1112R~
//        else                                                       //~va60I~//~1112R~
//        if (paoEswn4Wind!=-1 && paoEswn4Wind!=Peswn)    //not you  //~va60I~//~1112R~
//            rc=new Point(ERR_DISCARD_PAO_4WIND,paoTile4Wind);           //~va60I~//~1112R~
//        else                                                       //~va60I~//~1112R~
//        if (paoEswn4Kan!=-1 && paoEswn4Kan!=Peswn)  //not you      //~va60I~//~1112R~
//            rc=new Point(ERR_DISCARD_PAO_4KAN,paoEswn4Kan);             //~va60R~//~1112R~
		if (Ppos>=OFFS_WORDTILE+4)  	//WRG                      //~1112I~
        {                                                          //~1112I~
            if (paoEswn3Dragon!=-1 && Ppos==paoTile3Dragon && paoEswn3Dragon!=Peswn)    //not You//~1112I~
            {                                                      //~1113I~
        		if (Dump.Y) Dump.println("RoundStat.chkPao #dragon exposed pos="+Ppos+"="+itsExposed[Ppos]);//~1113R~
			    if (itsExposed[Ppos]<PAIRCTR-1)  //could not make meld if >=2//~1113R~
                    rc=new Point(ERR_DISCARD_PAO_3DRAGON,paoEswn3Dragon);//~1112I~//~1113R~
            }                                                      //~1113I~
        }                                                          //~1112I~
        else                                                       //~1112I~
		if (Ppos>=OFFS_WORDTILE)  	//ESWN                         //~1112I~
        {                                                          //~1112I~
            if (paoEswn4Wind!=-1 && Ppos==paoTile4Wind && paoEswn4Wind!=Peswn)    //not you//~1112I~
            {                                                      //~1113I~
        		if (Dump.Y) Dump.println("RoundStat.chkPao 4Wind exposed pos="+Ppos+"="+itsExposed[Ppos]);//~1113I~
			    if (itsExposed[Ppos]<PAIRCTR)  //could not make pair if >=3//~1113R~
                {                                                  //~1113I~
        			if (Dump.Y) Dump.println("RoundStat.chkPao exposed pos="+Ppos+"="+itsExposed[Ppos]);//~1113I~
            		rc=new Point(ERR_DISCARD_PAO_4WIND,paoEswn4Wind);  //~1112I~//~1113R~
                }                                                  //~1113I~
            }                                                      //~1113I~
		}                                                          //~1112I~
        if (rc==null)                                              //~1112I~
        {                                                          //~1112I~
			if (paoEswn4Kan!=-1 && paoEswn4Kan!=Peswn)  //not you  //~1112I~
			    rc=new Point(ERR_DISCARD_PAO_4KAN,paoEswn4Kan);    //~1112I~
        }                                                          //~1112I~
        if (Dump.Y) Dump.println("RoundStat.chkPao pos="+Ppos+",eswn="+Peswn+",rc="+Utils.toString(rc));//~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    public boolean isFuriten(int Peswn/*Other Player*/,int Ppos)   //~va60R~
    {                                                              //~va60I~
        boolean rc=RSP[Peswn].isFuriten(Ppos);                      //~va60I~
        if (Dump.Y) Dump.println("RoundStat.isFuriten pos="+Ppos+",eswn="+Peswn+",rc="+rc);//~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
    //*****************************************************        //~1306I~
    //*ignore last discarded                                       //~1306I~
    //*****************************************************        //~1306I~
    public boolean isFuritenRon(int Peswn/*Other Player*/,int Ppos)//~1306I~
    {                                                              //~1306I~
        boolean rc=RSP[Peswn].isFuritenRon(Ppos);                  //~1306I~
        if (Dump.Y) Dump.println("RoundStat.isFuritenRon pos="+Ppos+",eswn="+Peswn+",rc="+rc);//~1306I~
        return rc;                                                 //~1306I~
    }                                                              //~1306I~
    //*****************************************************        //~1220I~
    //*genbutsuchk                                                 //~1213I~//~1306M~
    //*********************************************************    //~1213I~//~1306M~
    public boolean isFuritenSelf(int Peswn,int Ppos)               //~1220I~
    {                                                              //~1220I~
        boolean rc=RSP[Peswn].isFuritenSelf(Ppos);                 //~1220I~
        if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFuritenSelf eswn="+Peswn+",Ppos="+Ppos+",rc="+rc);//~1220I~
        return rc;                                                 //~1220I~
    }                                                              //~1220I~
    //*********************************************************    //~1118I~
    public int getCurrentShanten(int Peswn)                               //~1118I~//~1128R~
    {                                                              //~1118I~
        int rc=RSP[Peswn].currentShanten;                                 //~1118I~//~1128R~
        if (Dump.Y) Dump.println("RoundStat.getCurrentShanten eswn="+Peswn+",shanten="+rc);//~1118I~//~1128R~
        return rc;                                                 //~1118I~
    }                                                              //~1118I~
    //*********************************************************    //~1201I~
    public void setCurrentShanten(int Peswn,int Pshanten)          //~1201I~
    {                                                              //~1201I~
        RSP[Peswn].currentShanten=Pshanten;                        //~1201I~
        if (Dump.Y) Dump.println("RoundStat.setCurrentShanten eswn="+Peswn+",shanten="+Pshanten);//~1201I~
    }                                                              //~1201I~
    //*********************************************************    //~1219I~
    private void resetOtherReachOneShot()                          //~1219I~
    {                                                              //~1219I~
        if (Dump.Y) Dump.println("RoundStat.resetOtherReachOneShot");//~1219I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~1219I~
        {                                                          //~1219I~
            RSP[ii].callStatus &= ~CALLSTAT_REACH_ONESHOT; //reset oneshot at take of reacher//~1219I~
        }                                                          //~1219I~
    }                                                              //~1219I~
    //*********************************************************    //~1310I~
    public int getCtrOtherReach(int Peswn)                         //~1310R~
    {                                                              //~1310I~
    	int ctr=0;                                                 //~1310I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~1310I~
        {                                                          //~1310I~
        	if (ii==Peswn)                                         //~1310I~
            	continue;                                          //~1310I~
            if ((RSP[ii].callStatus & CALLSTAT_REACH)!=0)               //~1310I~
            	ctr++;                                             //~1310I~
        }                                                          //~1310I~
        if (Dump.Y) Dump.println("RoundStat.getCtrOtherReach ctr="+ctr);//~1310I~//~1314R~
        return ctr;                                                //~1310I~
    }                                                              //~1310I~
    //*********************************************************    //~1311I~
    public int chkFuritenSelfBeforeReach(int Peswn,int Ppos)       //~1311I~
    {                                                              //~1311I~
        return RSP[Peswn].chkFuritenSelfBeforeReach(Ppos);         //~1311I~
    }                                                              //~1311I~
//******************************************************************************//~v@@@I~//~1128R~
//******************************************************************************//~v@@@I~
//******************************************************************************//~v@@@I~
    public class RSPlayer                                            //~v@@@I~//~va60R~//~1303R~
    {                                                              //~v@@@I~
    	public int eswn,player;                                           //~va60R~//~1114R~//~1118R~
//    	private int ctrDiscardedAllTaken,ctrDiscardedAllReach=-1;  //~1306R~//~va8wR~
      	private int ctrDiscardedAllReach=-1;                       //~va8wI~
      	public  int ctrDiscardedAllDiscarded;                      //~va8wI~
    	private int ctrDiscardedSelfReach=-1;                      //~1311I~
    	public boolean swRobot;                                           //~va60I~//~1303R~
    	protected Robot robot;                                             //~1124I~//~1303R~
        private int[] itsHand=new int[CTR_TILETYPE];      		//for Shanten calc//~va60R~//~1114R~
        public  int ctrHand;                                       //~1114R~//~1117R~
//        private int[] itsHandRed=new int[CTR_TILETYPE];                    //~va60R~//~1114R~//~1129R~
        private int[] itsPairStatus=new int[PAIRS_MAX];            //~1114R~
        public  int   ctrPairStatus;                               //~1114R~//~1118R~//~1217R~
        private int[] itsHandStatus=new int[CSI_MAXENTRY];         //~1114R~
        private int[] itsDiscardStatus=new int[CSI_MAXENTRY];      //~1115I~
//      TileData[][] tdssHand=new TileData[CTR_TILETYPE][];      		//for shanten calc//~va60I~//~1112R~
//      TileData[][] tdssPair=new TileData[CTR_TILETYPE][];      		//for shanten calc//~va60I~//~1112R~
        private int[] itsDiscarded=new int[CTR_TILETYPE];      //for furiten,genbutu chk//~va60R~//~1114R~
	    private int[] itsDiscardedSelf=new int[CTR_TILETYPE*PIECE_DUPCTR];  //players discarded in the seq of discard,to chk furiten from reach/my discarded//~1311I~
	    private int[] itsShanten=new int[CTR_SHANTENTYPE];  	//normal,13orphan, 7pair//~va60R~//~1114R~
	    private int[] itsPaoCheck=new int[CTR_TILETYPE-OFFS_WORDTILE];  	//word tile//~va60R~//~1114R~
        private int pao3Dragon,pao4Wind,pao4Kan;                           //~va60I~//~1114R~
        private int statusHand;                                    //~1114R~
        private boolean swIsFuritenRon;                            //~1306I~
        public  int intent;                                        //~1121R~
      	public int ctrTaken,ctrDiscarded,ctrChii,ctrPon,ctrKan;          //~1114I~//~1115R~
      	public int callStatus;                                     //~1115I~
      	public int currentShanten;                                        //~1118I~//~1128R~
      	public int earthColor;                                     //~1215I~
		private boolean swFixed1;   	//having or called pon yor WGR or Round or Wind//~1118R~
//  	private int ctrValueWordSameInHand;                        //~1118R~//~1221R~
		public boolean swAllInHand;	//also true if ankan only      //~1120I~
		private int ctrFixedFirst;                                     //~va8uI~
        //*****************************************************    //~v@@@I~
        public RSPlayer(int Peswn,int Pplayer,boolean PswRobot)                                 //~v@@@I~//~va60R~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.constructor eswn="+Peswn+",player="+Pplayer+",swRobot="+PswRobot);//~va60R~
            eswn=Peswn;                                            //~va60R~
            player=Pplayer;                                        //~va60I~
            swRobot=PswRobot;                                      //~va60I~
            if (swRobot)                                           //~1124I~
            	robot=AG.aAccounts.getRobot(Pplayer);              //~1124I~
//          for (int ii=0;ii<CTR_TILETYPE;ii++)                    //~va60I~//~1112R~
//          {                                                      //~va60I~//~1112R~
//          	tdssHand[ii]=new TileData[PIECE_DUPCTR];               //~va60I~//~1112R~
//          	tdssPair[ii]=new TileData[PIECE_DUPCTR];               //~va60I~//~1112R~
//          }                                                      //~va60I~//~1112R~
        }                                                          //~v@@@I~
        //*****************************************************    //~1125I~
	    public int getShanten(int[] PitsHand,int PctrHand)         //~1125I~
    	{                                                          //~1125I~
        	int rc=AG.aShanten.getShantenMin(PitsHand,PctrHand);   //~1125I~
        	if (Dump.Y) Dump.println("RoundStat.getShanten eswn="+eswn+",shanten="+rc+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1125I~
        	return rc;                                             //~1125I~
    	}                                                          //~1125I~
        //*****************************************************    //~1303I~
	    public int getCurrentShanten()                             //~1303I~
    	{                                                          //~1303I~
        	if (Dump.Y) Dump.println("RoundStat.getCurrentShanten eswn="+eswn+",currentShanten="+currentShanten);//~1303R~
        	return currentShanten;                                 //~1303I~
    	}                                                          //~1303I~
	    //*********************************************************//~1128I~
	    //*from discard                                            //~1128I~
	    //*********************************************************//~1128I~
    	private void setCurrentShanten()                           //~1128I~
    	{                                                          //~1128I~
        	currentShanten=getShanten(itsHand,ctrHand);            //~1128I~
        	if (Dump.Y) Dump.println("RoundStat.setCurrentShanten eswn="+eswn+",ctrHand="+ctrHand+",currentShanten="+currentShanten);//~1128I~
    	}                                                          //~1128I~
        //*****************************************************    //~va60I~
        public void deal(TileData[] Ptds)                          //~va60I~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.deal swRobot="+swRobot+",eswn="+eswn+",Ptds="+TileData.toString(Ptds));//~va60R~//~1201R~
            swAllInHand=true;                                      //~va8jI~
            if (!swRobot)                                          //~1201I~
            {                                                      //~va70I~
        		setShantenYou();                                   //~va70I~
            	return;                                            //~1201I~
            }                                                      //~va70I~
//          swAllInHand=true;                                      //~1120I~//~va8jR~
        	for (TileData td:Ptds)                                 //~va60I~
            {                                                      //~va60I~
            	int pos=RAUtils.getPosTile(td);                            //~va60I~//~1119R~
                addHandTile(pos,td);                                //~va60I~
            }                                                      //~va60I~
//          if (Dump.Y) Dump.println("RoundStat.RSPlayer.deal tdssHand="+TileData.toString(tdssHand));//~va60I~//~1112R~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.deal hand="+Utils.toString(itsHand,9));//~va60R~
//            if (Dump.Y) Dump.println("RoundStat.RSPlayer.deal handRed="+Utils.toString(itsHandRed,9));//~va60R~//~1129R~
            currentShanten=getShanten(itsHand,ctrHand);                   //~1125I~//~1128R~
        }                                                          //~va60I~
        //*****************************************************    //~va60I~
        private void addHandTile(int Ppos,TileData Ptd)             //~va60I~//~1112R~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.addHandTile eswn="+eswn+",pos="+Ppos+",Ptd="+TileData.toString(Ptd));//~va60M~
            ctrHand++;                                             //~1128I~
            itsHand[Ppos]++;                                       //~va60R~
//          tdssHand[Ppos][Ptd.ctrRemain]=Ptd;                     //~va60I~//~1112R~
//            if (Ptd.isRed5())                                      //~va60M~//~1129R~
//                itsHandRed[Ppos]++;                                //~va60R~//~1129R~
        }                                                          //~va60I~
        //*****************************************************    //~va60I~
        private void removeHandTile(int Ppos,TileData Ptd)          //~va60R~//~1112R~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.removeHandTile eswn="+eswn+",pos="+Ppos+",Ptd="+TileData.toString(Ptd));//~va60R~
            itsHand[Ppos]--;                                       //~va60R~
//          tdssHand[Ppos][Ptd.ctrRemain]=null;                    //~va60I~//~1112R~
//            if (Ptd.isRed5())                                      //~va60I~//~1129R~
//                itsHandRed[Ppos]--;                                //~va60R~//~1129R~
        }                                                          //~va60I~
//        //*****************************************************    //~va60I~//~1112R~
//        private void setPairTile(int Ppos,TileData Ptd)             //~va60R~//~1112R~
//        {                                                          //~va60I~//~1112R~
//            if (Dump.Y) Dump.println("RoundStat.RSPlayer.setPairTile eswn="+eswn+",pos="+Ppos+",Ptd="+TileData.toString(Ptd));//~va60R~//~1112R~
//            tdssPair[Ppos][Ptd.ctrRemain]=Ptd;                     //~va60R~//~1112R~
//        }                                                          //~va60I~//~1112R~
        //*****************************************************    //~va60I~
        private void pairExposedChii(TileData[] Ptds)               //~va60I~//~1112R~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedChii eswn="+eswn+",Ptds="+TileData.toString(Ptds));//~va60M~
        	for (TileData td:Ptds)                                 //~va60I~
            {                                                      //~va60I~
                int pos=RAUtils.getPosTile(td);                            //~va60I~//~1119R~
            	if (!td.isTakenRiver())                            //~va60I~
                {                                                  //~va60I~
		        	removeHandTile(pos,td);                        //~va60R~
		            itsExposed[pos]++;                             //~va60I~
                }                                                  //~va60I~
//  		    setPairTile(pos,td);                               //~va60I~//~1112R~
            }                                                      //~va60I~
//          if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedChii tdssHand="+TileData.toString(tdssHand));//~va60I~//~1112R~
//          if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedChii tdssPair="+TileData.toString(tdssPair));//~va60I~//~1112R~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedChii itshand="+Utils.toString(itsHand,9));//~va60R~
//            if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedChii itshandRed="+Utils.toString(itsHandRed,9));//~va60R~//~1129R~
        }                                                          //~va60I~
        //*****************************************************    //~va60I~
        private void pairExposedPon(int Ppos,TileData[] Ptds)       //~va60R~//~1112R~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposed eswn="+eswn+",pos="+Ppos+",Ptds="+TileData.toString(Ptds));//~va60M~
        	for (TileData td:Ptds)                                 //~va60I~
            {                                                      //~va60I~
                if (!td.isTakenRiver())                            //~va60R~
                {                                                  //~va60R~
                    removeHandTile(Ppos,td);                       //~va60R~
                    itsExposed[Ppos]++;                            //~va60R~
                }                                                  //~va60R~
//  		    setPairTile(Ppos,td);                              //~va60I~//~1112R~
            }                                                      //~va60I~
//          if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedPon tdssHand="+TileData.toString(tdssHand));//~va60R~//~1112R~
//          if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedPon tdssPair="+TileData.toString(tdssPair));//~va60I~//~1112R~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedPon hand="+Utils.toString(itsHand,9));//~va60R~
//            if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedPon handRed="+Utils.toString(itsHandRed,9));//~va60R~//~1129R~
        }                                                          //~va60I~
        //*****************************************************    //~va60I~
        private void pairExposedKan(int Ppos,TileData[] Ptds,boolean PswAddKan)//~va60R~//~1112R~
        {                                                          //~va60I~
        	boolean swAdd=Ptds[0].isKanAdd();                       //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposed eswn="+eswn+",swKanAdd="+PswAddKan+",pos="+Ppos+",Ptds="+TileData.toString(Ptds));//~va60R~
        	for (TileData td:Ptds)                                 //~va60I~
            {                                                      //~va60I~
                if (PswAddKan)                                     //~va60R~
                {                                                  //~va60I~
					if (td.isKanAddedTile())                       //~va60I~
                    {                                              //~va60I~
			            removeHandTile(Ppos,td);                   //~va60R~
		                itsExposed[Ppos]++;                        //~va60I~
                    }                                              //~va60I~
                }                                                  //~va60I~
                else                                               //~va60I~
                {                                                  //~va60I~
	            	if (!td.isTakenRiver())                        //~va60I~
//TODO          	if (!td.isTakenRiver())   //robot: +1 at takeOne,human dose not maintain itshand so itsHand set -1. Reset itshand by RAC.setShantenYou is required//~va81R~
                    {                                              //~va60I~
			            removeHandTile(Ppos,td);                   //~va60R~
		                itsExposed[Ppos]++;                        //~va60I~
                    }                                              //~va60I~
                }                                                  //~va60I~
//  		    setPairTile(Ppos,td);                              //~va60I~//~1112R~
            }                                                      //~va60I~
//          if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedKan tdssHand="+TileData.toString(tdssHand));//~va60I~//~1112R~
//          if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedKan tdssPair="+TileData.toString(tdssPair));//~va60I~//~1112R~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedKan hand="+ Utils.toString(itsHand,9));//~va60R~
//            if (Dump.Y) Dump.println("RoundStat.RSPlayer.pairExposedKan handRed="+Utils.toString(itsHandRed,9));//~va60R~//~1129R~
        }                                                          //~va60I~
        //*****************************************************    //~va60I~
        public void takeOne(int Ppos,TileData Ptd)                 //~va60I~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeOne swRobot="+swRobot+",swServer="+swServer);//~1201I~
			callStatus &=~CALLSTAT_REACH_ONESHOT; //reset oneshot at take of reacher//~1219I~
        	ctrTaken++;                                            //~1114I~//~1223M~
//      	ctrDiscardedAllTaken=ctrDiscardedAll;	//for chk furiten discarded after taken//~va60I~//~1223M~//~va8wR~
    		if (!swRobot)                                          //~1201I~
            {                                                      //~va81I~
      			if (AG.swPlayAloneNotify)                          //~va81I~
                	AG.aRACall.takeOnePlayAloneNotify(Ppos,Ptd);   //~va81I~
        		return;                                            //~1201I~
            }                                                      //~va81I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeOne eswn="+eswn+",ctrTaken="+ctrTaken+",ctrDiscardedAllDiscarded="+ctrDiscardedAllDiscarded+",Ptd="+TileData.toString(Ptd));//~va60R~//~1114R~//~va8wR~
            int pos=RAUtils.getPosTile(Ptd);                                //~va60I~//~1119R~
        	addHandTile(pos,Ptd);                                   //~va60I~
//      	if ((TestOption.option2 & TO2_OPENHAND)!=0 && AG.swTrainingMode)//~1205I~//~1220R~
        	if ((TestOption.option2 & TO2_OPENHAND)!=0                     )//~1220I~
            	AG.aHands.drawOpenTakeOneRobot(player,eswn,Ptd);    //~1201R~
//          if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeOne tdssHand="+TileData.toString(tdssHand));//~va60I~//~1112R~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeOne hand="+Utils.toString(itsHand,9));//~va60R~
//            if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeOne handRed="+Utils.toString(itsHandRed,9));//~va60R~//~1129R~
        }                                                          //~va60I~
        //*****************************************************    //~va60I~
        //*set furiten ptr                                         //~va60I~
        //*****************************************************    //~va60I~
        public void reach()                                        //~va60I~
        {                                                          //~va60I~
        	ctrDiscardedAllReach=ctrDiscardedAll;	//for chk furiten discarded after taken//~va60I~
        	ctrDiscardedSelfReach=ctrDiscarded+1;	//for chk furiten discarded before reach//~1311I~
			callStatus|=CALLSTAT_REACH; //reach called             //~1115R~
            if (swReachOneShot)                                    //~1219I~
				callStatus|=CALLSTAT_REACH_ONESHOT; //reach oneshot//~1219I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.reach eswn="+eswn+",ctrDiscardedReach="+ctrDiscardedAllReach);//~va60I~
        }                                                          //~va60I~
        //*****************************************************    //~1306I~
        public boolean isReachCalled()                             //~1118I~
        {                                                          //~1118I~
			boolean rc=(callStatus & CALLSTAT_REACH)!=0; //reach called//~1118I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isReachCalled eswn="+eswn+",rc="+rc);//~1118I~
            return rc;
        }                                                          //~1118I~
        //*****************************************************    //~va60I~
        public void discard(int Ppos,TileData Ptd)                 //~va60I~
        {                                                          //~va60I~
                                                                   //~1201I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.discard eswn="+eswn+",swRobot="+swRobot+",swThinkRobot="+swThinkRobot+",eswn="+eswn+",Ppos="+Ppos+",ctrDiscarded="+ctrDiscarded+",Ptd="+Ptd.toString());//~va60R~//~1114R~//~1128R~//~1201R~//~1223R~
            itsDiscarded[Ppos]++;   //for furiten chk     //for also humen         //~va60I~//~1223I~
    		if (!swServer)                                         //+va96I~
            {                                                      //+va96I~
		   		itsDiscardedSelf[ctrDiscarded++]=Ppos; //players discarded in the seq of discard,to chk furiten from reach/my discarded//+va96I~
        		return;                                            //+va96I~
            }                                                      //+va96I~
	        setStatistic(itsDiscardStatus,Ppos,1/*Pctr*/);  //for also human        //~1217I~//~1223I~
	        ctrHand=AG.aPlayers.getHands(player).length;  //agter removed from Hand by Pon/Kan/Chii//~1201I~//~1223M~
//      	ctrDiscarded++;                                        //~1114I~//~1201M~//~1223M~//~1311R~
	   		itsDiscardedSelf[ctrDiscarded++]=Ppos; //players discarded in the seq of discard,to chk furiten from reach/my discarded//~1311I~
            if (!swRobot)                                          //~1201I~
            {                                                      //~va70I~
//          	if (AG.swPlayAloneNotify)                          //~va70I~//~va74R~
            	if (AG.swTrainingMode)                             //~va74I~
                	setShantenYou();                               //~va70I~
            	return;                                             //~1201I~
            }                                                      //~va70I~
		    removeHandTile(Ppos,Ptd);                              //~va60R~
//      	if ((TestOption.option2 & TO2_OPENHAND)!=0 && AG.swTrainingMode)//~1205I~//~1220R~//~1224M~
        	if ((TestOption.option2 & TO2_OPENHAND)!=0                     )//~1220I~//~1224M~
            	AG.aHands.drawOpenDiscardRobot(player,eswn,Ptd);    //~1201R~//~1224M~
            if (!swThinkRobot)                                     //~1201I~
            	return;                                             //~1201I~
	    	setCurrentShanten();                                   //~1201I~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.discard hand="+Utils.toString(itsHand,9));//~va60R~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.discard eswn="+eswn+",itsDiscarded="+Utils.toString(itsDiscarded,9));//~va60R~//~1223R~
        }                                                          //~va60I~
        //*****************************************************    //~1118I~
        public int getPairCtr()                                    //~1118I~
        {                                                          //~1118I~
            int rc=ctrPairStatus;                                  //~1118R~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getPairCtr rc="+rc);//~1118I~
            return rc;
        }                                                          //~1118I~
        //*****************************************************    //~1118I~
        public boolean isFixed1()                                  //~1118I~
        {                                                          //~1118I~
           	boolean rc=swFixed1/*yakuhai pon at 1st call*/ || isFixed1InHand();  	//pair ctr of WGR, round, wind//~1118R~//~1130R~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFixed1 rc="+rc+",swFixed="+swFixed1);//~1118R~
            return rc;
        }                                                          //~1118I~
        //*****************************************************    //~va8uI~
        //*chk sakiduke 1/2han                                     //~va8uI~
        //*****************************************************    //~va8uI~
        public boolean isFixedFirst()                              //~va8uI~
        {                                                          //~va8uI~
           	int ctrFirst=ctrFixedFirst+getCtrFixedInHand();  	//pair ctr of WGR, round, wind//~va8uI~
            boolean rc=ctrFirst>=(swFix2 ? 2 : 1);                   //~va8uI~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFixedFirst rc="+rc+",swFix2="+swFix2+",ctrFixedFirst="+ctrFixedFirst+",ctrFirst="+ctrFirst);//~va8uI~//~va8wR~
            return rc;                                             //~va8uI~
        }                                                          //~va8uI~
        //*****************************************************    //~1130I~
        private boolean isFixed1InHand()                            //~1130I~//~va8vR~
        {                                                          //~1130I~
        	boolean rc=false;                                      //~1130I~
            if (!swRobot)                                          //~va8vI~
        		getItsHandYou();                                   //~va8vI~
//      	if (swAllInHand)                                       //~1130I~//~va8vR~
//          {                                                      //~1130I~//~va8vR~
	            rc=getCtrValueWordSameInHand()!=0;  	//pair ctr of WGR, round, wind//~1130I~
                if (!rc)                                           //~1130I~
                	rc=isValueWordAnkan();                         //~1130I~
//          }                                                      //~1130I~//~va8vR~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFixed1InHand rc="+rc);//~1130I~
            return rc;                                             //~1130I~
        }                                                          //~1130I~
        //*****************************************************    //~va8uI~
        private int getCtrFixedInHand()                        //~va8uI~
        {                                                          //~va8uI~
        	int rc;                                                //~va8uI~
            if (!swRobot)                                          //~va8uI~
        		getItsHandYou();                                   //~va8uI~
	        rc=getCtrValueWordSameInHand();  	//pair ctr of WGR, round, wind//~va8uI~
//          rc+=getCtrValueWordAnkan();  //1st kan is counted at takeKan, for robot allow 1st call only as Fisrt//~va8wR~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.getCtrFixedInHand rc="+rc);//~va8uI~//~va8wR~
            return rc;                                             //~va8uI~
        }                                                          //~va8uI~
        //*****************************************************************//~1130I~
        private boolean isValueWordAnkan()                             //~1130I~
        {                                                          //~1130I~
        	boolean rc=false;                                      //~1131I~
            if (ctrPairStatus!=0)                                  //~1131I~
            {                                                      //~1131I~
        		rc=true;                                           //~1131R~
                for (int ii=0;ii<ctrPairStatus;ii++)                   //~1130I~//~1131R~
                {                                                      //~1130I~//~1131R~
                    if ((itsPairStatus[ii] & PS_ANKAN)==0)             //~1130I~//~1131R~
                    {                                                  //~1130I~//~1131R~
                        rc=false;                                  //~1131I~
                        break;                                         //~1130I~//~1131R~
                    }                                                  //~1130I~//~1131R~
                    int pos=itsPairStatus[ii] & PS_POSMASK;            //~1130I~//~1131R~
                    if (RAUtils.chkValueWordTile(pos,eswn)==0)         //~1130I~//~1131R~
                    {                                                  //~1130I~//~1131R~
                        rc=false;                                      //~1130I~//~1131R~
                        break;                                         //~1130I~//~1131R~
                    }                                                  //~1130I~//~1131R~
                }                                                  //~1131I~
            }                                                      //~1130I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isValueWordAnkan rc="+rc);//~1130I~
            return rc;                                             //~1130I~
        }                                                          //~1130I~
        //*****************************************************************//~va8uI~
        private int getCtrValueWordAnkan()                         //~va8uI~
        {                                                          //~va8uI~
        	int rc=0;                                              //~va8uI~
            for (int ii=0;ii<ctrPairStatus;ii++)                   //~va8uI~
            {                                                      //~va8uI~
                if ((itsPairStatus[ii] & PS_ANKAN)==0)             //~va8uI~
                    continue;                                      //~va8uI~
                int pos=itsPairStatus[ii] & PS_POSMASK;            //~va8uI~
                if (RAUtils.chkValueWordTile(pos,eswn)==0)         //~va8uI~
                    rc++;                                          //~va8uI~
            }                                                      //~va8uI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getCtrValueWordAnkan rc="+rc+",itsPairStatus="+Utils.toHexString(itsPairStatus));//~va8uI~//~va8wR~
            return rc;                                             //~va8uI~
        }                                                          //~va8uI~
        //*****************************************************    //~1118I~
        //*chk hand and ankan                                      //~1130I~
        //*****************************************************    //~1130I~
        private int getCtrValueWordSameInHand()                    //~1118I~
        {                                                          //~1118I~
    		int ctrValueWordSameInHand=0;                              //~1118I~//~1221R~
        	for (int ii=OFFS_WORDTILE;ii<CTR_TILETYPE;ii++)                       //~1118I~//~1129R~
            {                                                      //~1118I~
            	if (itsHand[ii]>=PAIRCTR)                         //~1118I~
                	if (RAUtils.chkValueWordTile(ii,eswn)!=0)          //~1118I~
			    		ctrValueWordSameInHand++;                  //~1118I~
            }                                                      //~1118I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getCtrValueWordSameInhand ctrValueWordSameInHand="+ctrValueWordSameInHand);//~1118I~
            return ctrValueWordSameInHand;
        }                                                          //~1118I~
        //*****************************************************    //~1221I~
        //*chk value word tile in hand and earth                                      //~1221I~//~va8jR~
        //*****************************************************    //~1221I~
        public int getCtrValueWordSameAndPairInHand()              //~1221R~
        {                                                          //~1221I~
    		int ctrValueWordSameInHand=0;                          //~1221I~
        	for (int ii=OFFS_WORDTILE;ii<CTR_TILETYPE;ii++)        //~1221I~
            {                                                      //~1221I~
            	if (itsHand[ii]>=PAIRCTR-1)   //including pair     //~1221I~
                	if (RAUtils.chkValueWordTile(ii,eswn)!=0)      //~1221I~
			    		ctrValueWordSameInHand++;                  //~1221I~
            }                                                      //~1221I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getCtrValueWordSameAndPairInhand rc="+ctrValueWordSameInHand);//~1221I~
            return ctrValueWordSameInHand;                         //~1221I~
        }                                                          //~1221I~
        //*****************************************************    //~va60I~
        public void takeChii(TileData[] Ptds)                      //~va60R~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeChii eswn="+eswn+",swRobot="+swRobot);//~1201I~
            if (swReachOneShot)                                    //~1219I~
                resetOtherReachOneShot();                          //~1219I~
            swAllInHand=false;                                     //~1120I~//~1223M~
        	pairExposedChii(Ptds);                                 //~va60I~//~1223M~
//      	if (!swRobot)                                          //~1201I~//~1223R~
//          	return;                                            //~1201I~//~1223R~
        	ctrChii++;                                             //~1114I~
            earthColor|=1<<Ptds[0].type;                           //~1215I~//~1223M~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeChii eswn="+eswn+",ctrChii="+ctrChii+",Ptds="+TileData.toString(Ptds));//~va60R~//~1114R~
            itsPairStatus[ctrPairStatus++]=getPairStatus(PS_SEQ,Ptds);//~1114I~
        }                                                          //~va60I~
        //*****************************************************    //~va60I~
        public void takePon(int Ppos,TileData[] Ptds)              //~va60I~
        {                                                          //~va60I~
            swAllInHand=false;                                     //~1120I~
            if (swReachOneShot)                                    //~1219I~
                resetOtherReachOneShot();	//including human player//~1219I~
        	pairExposedPon(Ppos,Ptds);                       //~va60R~//~1223M~
        	ctrPon++;                                              //~1114I~
//      	if (!swRobot)                                          //~1223I~
//          	return;                                            //~1223I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takePon eswn="+eswn+",Ppos="+Ppos+",ctrPon="+ctrPon+",Ptds="+TileData.toString(Ptds));//~va60I~//~1114R~
        	if (Ppos>=OFFS_WORDTILE)                               //~va60R~
            {                                                      //~1118I~
		        takePonWord(Ppos);                                 //~va60I~
        		if (RAUtils.chkValueWordTile(Ptds[0],eswn)!=0)         //~1118I~
        			if (ctrPairStatus==0)                          //~1118R~
                    {                                              //~va8uI~
                    	swFixed1=true;                             //~1118I~
                        ctrFixedFirst++;                           //~va8uI~
                    }                                              //~va8uI~
            }                                                      //~1118I~
            else                                                   //~1215I~
                earthColor|=1<<Ptds[0].type;                       //~1215I~
            itsPairStatus[ctrPairStatus++]=getPairStatus(PS_PON,Ptds);//~1114I~
        }                                                          //~va60I~
        //*****************************************************    //~va60M~
        public void takeKan(int Ppos,TileData[] Ptds)              //~va60R~
        {                                                          //~va60M~
            if (!Ptds[0].isKanTaken())                             //~1120I~
	            swAllInHand=false;                                 //~1120I~
            if (swReachOneShot)                                    //~1219I~
                resetOtherReachOneShot();	//including human player//~1219I~
        	ctrKan++;                                              //~1114I~
        	boolean swAdd=Ptds[0].isKanAdd();                      //~va60I~
        	pairExposedKan(Ppos,Ptds,swAdd);                       //~va60R~
        	if (!swAdd && Ppos>=OFFS_WORDTILE)                     //~va60R~
            {                                                      //~1118I~
        		takePonWord(Ppos);                                 //~va60M~
        		if (RAUtils.chkValueWordTile(Ptds[0],eswn)!=0)         //~1118I~
        			if (ctrPairStatus==0)                          //~1118I~
                    {                                              //~va8uI~
                    	swFixed1=true;                             //~1118I~
                        ctrFixedFirst++;                           //~va8uI~
                    }                                              //~va8uI~
            }                                                      //~1118I~
        	if (Ppos<OFFS_WORDTILE)                                //~1215I~
                earthColor|=1<<Ptds[0].type;                       //~1215I~
            pao4Kan++;                                             //~va60M~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeKan eswn="+eswn+",Ppos="+Ppos+",ctrKan="+ctrKan+",pao4kan="+pao4Kan);//~va60R~//~1114R~
            if (pao4Kan==3)                                        //~va60M~
            	setPao4Kan(eswn);                                  //~va60M~
            if (swAdd)	//chankan                                  //~1303I~
            {                                                      //~1303I~
                int idxPon=srchPonToAdd(Ptds[0]);                      //~1303I~
                if (idxPon>=0)                                     //~1303I~
		            itsPairStatus[idxPon]=getPairStatus(PS_KAN,Ptds);//~1303I~
                else                                               //~1303I~
        			if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeKan @@@@ srchPonToKan error");//~1303I~
            }                                                      //~1303I~
            else                                                   //~1303I~
	            itsPairStatus[ctrPairStatus++]=getPairStatus(PS_KAN,Ptds);//~1114I~//~1303R~
        }                                                          //~va60M~
        //******************************************               //~1303I~
        private int srchPonToAdd(TileData Ptd)                     //~1303I~
        {                                                          //~1303I~
        	int rc=-1,idx;                                         //~1303I~
        //*****************************                            //~1303I~
        	TileData[][] tdssPon=AG.aPlayers.getPonsEarth(player);	//pon on earth//~1303I~
            if (tdssPon!=null)                                     //~1306I~
            {                                                      //~1306I~
                idx=0;                                                 //~1303I~//~1306R~
                for (TileData[] tds:tdssPon)                           //~1303I~//~1306R~
                {                                                      //~1303I~//~1306R~
                    if (tds==null)                                     //~1303I~//~1306R~
                        break;                                         //~1303I~//~1306R~
                    if (Ptd.type==tds[0].type && Ptd.number==tds[0].number)//~1303I~//~1306R~
                    {                                                  //~1303I~//~1306R~
                        rc=idx;                                        //~1303I~//~1306R~
                        break;                                         //~1303I~//~1306R~
                    }                                                  //~1303I~//~1306R~
                    idx++;                                             //~1303I~//~1306R~
                }                                                      //~1303I~//~1306R~
            }                                                      //~1306I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.srchPonToAdd eswn="+eswn+",rc="+rc+",Ptd="+Ptd.toString());//~1303I~
            return rc;                                             //~1303I~
        }                                                          //~1303I~
        //*****************************************************    //~va60I~
        private void takePonWord(int Ppos)                         //~va60R~
        {                                                          //~va60I~
        	int pos=Ppos-OFFS_WORDTILE;                            //~va60I~
        	itsPaoCheck[pos]++;                                    //~va60R~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takePonWord eswn="+eswn+",Ppos="+Ppos+",pos="+pos+",itsPaoChecj="+Arrays.toString(itsPaoCheck));//~va60R~//~1113R~
            if (pos>=4)	//WGR                                      //~va60I~
            {                                                      //~va60I~
            	pao3Dragon++;	                                   //~va60I~
                if (pao3Dragon==2)                                 //~va60I~
                {                                                  //~va60I~
                	for (int ii=4;ii<CTR_WORD_TILE;ii++)           //~va60I~
                    {                                              //~va60I~
                    	if (itsPaoCheck[ii]==0)                    //~va60R~
                        {                                          //~va60I~
                        	setPao3Dragon(eswn,ii+OFFS_WORDTILE);  //~va60I~
                            break;                                 //~va60I~
                        }                                          //~va60I~
                    }                                              //~va60I~
                }                                                  //~va60I~
            }                                                      //~va60I~
            else                                                   //~va60I~
            {                                                      //~va60I~
            	pao4Wind++;                                        //~va60I~
                if (pao4Wind==3)                                   //~va60I~
                {                                                  //~va60I~
                	for (int ii=0;ii<4;ii++)                       //~va60I~
                    {                                              //~va60I~
                    	if (itsPaoCheck[ii]==0)                    //~va60R~
                        {                                          //~va60I~
                        	setPao4Wind(eswn,ii+OFFS_WORDTILE);  //~va60I~
                            break;                                 //~va60I~
                        }                                          //~va60I~
                    }                                              //~va60I~
                }                                                  //~va60I~
            }                                                      //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takePonWord pao3Dragon="+pao3Dragon+",pao4Wind="+pao4Wind);//~1113I~
        }                                                          //~va60I~
        //*****************************************************    //~1306I~
        public boolean isFuritenRon(int Ppos)                      //~1306I~
        {                                                          //~1306I~
        	swIsFuritenRon=true;                                   //~1306I~
		 	boolean rc=isFuriten(Ppos);                         //~1306I~
        	swIsFuritenRon=false;                                  //~1306I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFuritenRon rc="+rc);//~1306I~
            return rc;
        }                                                          //~1306I~
        //*****************************************************    //~va60I~
        public boolean isFuriten(int Ppos)                         //~va60I~
        {                                                          //~va60I~
	        boolean rc=itsDiscarded[Ppos]!=0;  //for furiten,genbutu chk//~va60I~
            if (!rc)	//not genbutu                              //~va60I~
            {                                                      //~va60I~
//          	int start=Math.min(ctrDiscardedAllTaken,ctrDiscardedAllReach);//~va60I~//~1223R~
//          	int start=ctrDiscardedAllReach>=0 ? ctrDiscardedAllReach : ctrDiscardedAllTaken;//~1223I~//~1306R~//~va8wR~
            	int start=ctrDiscardedAllReach>=0 ? ctrDiscardedAllReach : ctrDiscardedAllDiscarded;//~va8wI~
	        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFuriten start="+start);//~1306I~
//          	for (int ii=start;ii<ctrDiscardedAll;ii++)         //~va60R~//~1306R~
                int upTo=ctrDiscardedAll;                          //~1306I~
        		if (swIsFuritenRon)                                //~1306I~
                	upTo--;                                    //~1306I~
            	for (int ii=start;ii<upTo;ii++)                    //~1306I~
				    if (itsDiscardedAll[ii]==Ppos)                 //~va60I~
                    {	                                           //~va60I~
                    	rc=true;                                   //~va60I~
                        break;                                     //~va60I~
                    }                                              //~va60I~
            }                                                      //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFuriten eswn="+eswn+",Ppos="+Ppos+",rc="+rc+",ctrDiscardedAllReach="+ctrDiscardedAllReach+",ctrDiscardedAllDiscarded="+ctrDiscardedAllDiscarded);//~1112R~//~1223R~//~va8wR~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFuriten itsDiscarded="+Utils.toString(itsDiscarded,9));//~1112R~//~1223R~//~va8wR~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFuriten itsDiscardedAll="+Utils.toString(itsDiscardedAll,10,ctrDiscardedAll));//~1112R~
            return rc;                                             //~va60I~
        }                                                          //~va60I~
        //*****************************************************    //~1220I~
        public boolean isFuritenSelf(int Ppos)                     //~1220I~
        {                                                          //~1220I~
	        boolean rc=itsDiscarded[Ppos]!=0;  //for furiten,genbutu chk//~1220I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isFuritenSelf eswn="+eswn+",Ppos="+Ppos+",rc="+rc);//~1220I~
            return rc;                                             //~1220I~
        }                                                          //~1220I~
        //**************************************************************************//~1311I~
        //*from RADSOther.chkSafe                                  //+va96R~
        //*rc:-1:discarde before reach, >1:discarded some time, =0:not reach done//+va96I~
        //**************************************************************************//~1311I~
        public int chkFuritenSelfBeforeReach(int Ppos)         //~1311I~
        {                                                          //~1311I~
            int rc=itsDiscarded[Ppos] ;  //for furiten,genbutu chk //~1311I~
            if (rc!=0)      //discarded some time                  //~1311I~
                for (int ii=0;ii<ctrDiscardedSelfReach;ii++)       //~1311I~
                {                                                  //~1311I~
                    if (itsDiscardedSelf[ii]==Ppos)                //~1311I~
                    {                                              //~1311I~
                        rc=-1;                                     //~1311I~
                        break;                                     //~1311I~
                    }                                              //~1311I~
                }                                                  //~1311I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.chkFuritenSelfBeforeReach eswn="+eswn+",ctrDiscardedSelfBeforeReach="+ctrDiscardedSelfReach+",Ppos="+Ppos+",rc="+rc);//~1311I~
            return rc;                                             //~1311I~
        }                                                          //~1311I~
        //*****************************************************************//~1114I~
        private int getPairStatus(int Ppattern,TileData[] Ptds)    //~1114I~
        {                                                          //~1114I~
        	int rc,type,flag;                                      //~1114I~
            TileData td0=Ptds[0];                                  //~1114I~
            type=td0.type;                                         //~1114I~
            int num=td0.number;                                        //~1114I~
            flag=RAUtils.getPosTile(td0);                          //~1130I~
            switch(Ppattern)                                       //~1114I~
            {                                                      //~1114I~
            case PS_SEQ:                                           //~1114I~
            	flag|=PS_SEQ;                                      //~1114I~
                boolean swChanta=false;                            //~1114I~
                for (TileData td:Ptds)                             //~1114I~
                {                                                  //~1114I~
		            num=td.number;                                 //~1220I~
	            	if (type==TT_JI || num==0 || num==8)           //~1114I~
                    {                                              //~1114I~
                    	swChanta=true;                             //~1114I~
                        break;                                     //~1114I~
                    }                                              //~1114I~
                }                                                  //~1114I~
	            flag|=(swChanta) ? PS_CHANTA : PS_TANYAO;          //~1114I~
            	break;                                             //~1114I~
            case PS_PON:                                           //~1114I~
            	flag|=PS_PON;                                      //~1114I~
            	flag|=(type==TT_JI || num==0 || num==8) ? PS_CHANTA : PS_TANYAO;//~1114I~
            	flag|=(type==TT_JI) ? PS_NOTNUM : 0;               //~1114I~
            	break;                                             //~1114I~
            case PS_KAN:                                           //~1114I~
            	flag|=PS_KAN;                                      //~1114I~
            	flag|=(td0.flag & TDF_KAN_TAKEN)!=0 ? PS_ANKAN : 0;  //~1114I~
            	flag|=(td0.flag & TDF_KAN_ADD)!=0 ? PS_CHANKAN : 0;  //~1114I~
            	flag|=(type==TT_JI || num==0 || num==8) ? PS_CHANTA : PS_TANYAO;//~1114I~
            	flag|=(type==TT_JI) ? PS_NOTNUM : 0;               //~1114I~
            	break;                                             //~1114I~
            }                                                      //~1114I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getPairStatus rc=x"+Integer.toHexString(flag)+",pattern=x"+Integer.toHexString(Ppattern)+",tds="+TileData.toString(Ptds));//~1114I~//~1308R~
            return flag;
        }                                                          //~1114I~
        //*****************************************************************//~1114I~
        private int getPairStatus()                                //~1114I~
        {                                                          //~1114I~
        	int stat=0;                                            //~1114I~
            if (ctrPairStatus!=0)                                  //~1122I~
            {                                                      //~1122I~
                boolean swTanyao=true,swAllSeq=true,swAllSame=true;//~1122R~
                for (int ii=0;ii<ctrPairStatus;ii++)                   //~1114I~//~1122R~
                {                                                      //~1114I~//~1122R~
                    if ((itsPairStatus[ii] & PS_CHANTA)!=0)               //~1114I~//~1122R~
                        swTanyao=false;                                //~1114I~//~1122R~
                    if ((itsPairStatus[ii] & PS_SEQ)==0)                  //~1114I~//~1122R~
                        swAllSeq=false;                                //~1114I~//~1122R~
                    else                                           //~1122I~
                        swAllSame=false;                          //~1122I~
                }                                                      //~1114I~//~1122R~
                stat|=swTanyao ? PS_TANYAO : PS_CHANTA;               //~1114I~//~1122R~
                stat|=swAllSeq ? PS_ALLSEQ : 0;                        //~1114I~//~1122R~
                stat|=swAllSame ? PS_ALLSAME : 0;                 //~1122I~
            }                                                      //~1122I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getPairStatus rc="+Integer.toHexString(stat));//~1114I~
            return stat;
        }                                                          //~1114I~
        //*****************************************************************//~1217I~
        public boolean isPairSameAll()                             //~1217I~
        {                                                          //~1217I~
        	int stat=getPairStatus();                              //~1217I~
            boolean rc=(stat & PS_ALLSAME)!=0;                     //~1217I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isPairSameAll ctrPairStatus="+ctrPairStatus+",rc="+rc);//~1217I~
            return rc;                                             //~1217I~
        }                                                          //~1217I~
        //*****************************************************************//~1114I~
        public boolean isPairChantaAll()                                      //~1114I~//~1118R~
        {                                                          //~1114I~
        	int stat=getPairStatus();                              //~1114I~
            boolean rc=(stat & PS_CHANTA)!=0;                      //~1114I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isPairChantaAll rc="+rc);//~1114I~//~1217R~
            return rc;
        }                                                          //~1114I~
        //*****************************************************************//~1217I~
        public boolean isPairChantaAllOrNoPair()                   //~1217I~
        {                                                          //~1217I~
        	int stat=getPairStatus();                              //~1217I~
            boolean rc=ctrPairStatus==0 || (stat & PS_CHANTA)!=0;  //~1217I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isPairChantaAllOrNoPair rc="+rc);//~1217I~
            return rc;                                             //~1217I~
        }                                                          //~1217I~
        //*****************************************************************//~1114I~
        public boolean isPairTanyaoAll()                                      //~1114I~//~1118R~
        {                                                          //~1114I~
        	int stat=getPairStatus();                              //~1114I~
            boolean rc=(stat & PS_CHANTA)==0;                      //~1114I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isPairTanyaoAll rc="+rc);//~1114I~//~1217R~
            return rc;
        }                                                          //~1114I~
        //*****************************************************************//~1217I~
        public boolean isPairTanyaoAllOrNoPair()                   //~1217R~
        {                                                          //~1217I~
        	int stat=getPairStatus();                              //~1217I~
            boolean rc=ctrPairStatus==0 || (stat & PS_CHANTA)==0;  //~1217I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isPairTanyaoAllOrNoPair rc="+rc);//~1217I~
            return rc;                                             //~1217I~
        }                                                          //~1217I~
        //*****************************************************************//~1114I~
        //*output Point=(ctr,stat)                                 //~1114I~
        //*****************************************************************//~1114I~
        public int[] getPairStatistic(Point Pstat)                 //~1114I~
        {                                                          //~1114I~
		    int stat=getPairStatus();                              //~1114I~
            if (Pstat!=null)                                       //~1120I~
            {                                                      //~1120I~
        		Pstat.x=ctrPairStatus;                                 //~1114I~//~1120R~
        		Pstat.y=stat;                                          //~1114I~//~1120R~
            }                                                      //~1120I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getPairStatistic out Pstat="+Utils.toString(Pstat)+",itsPairStatus="+Utils.toHexString(itsPairStatus));//~1114I~//~1216R~
	        return itsPairStatus;                                  //~1114I~
        }                                                          //~1114I~
        //*****************************************************************//~1114I~
        public int[] getHandStatistic()                           //~1114R~
        {                                                          //~1114I~
        	int[] stat=getStatistic(itsHandStatus,itsHand,itsHand.length);//~1115R~//~1116R~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getHandStatistic itsHandStatus="+Arrays.toString(itsHandStatus));//~1114R~//~1216R~
            return stat;                                             //~1115R~//~1116R~
        }                                                          //~1114I~
//        //*****************************************************************//~1115I~//~1217R~
//        public int[] getDiscardStatistic()                         //~1115I~//~1217R~
//        {                                                          //~1115I~//~1217R~
//            int[] stat=getStatistic(itsDiscardStatus,itsDiscarded,itsDiscarded.length);//~1115I~//~1116R~//~1217R~
//            if (Dump.Y) Dump.println("RoundStat.RSPlayer.getDiscardStatistic itsHandStatus="+Arrays.toString(itsDiscardStatus));//~1115I~//~1116R~//~1216R~//~1217R~
//            return stat;                                             //~1115I~//~1217R~
//        }                                                          //~1115I~//~1217R~
        //*****************************************************************//~1217I~
        public int[] getDiscardStatistic()                         //~1217I~
        {                                                          //~1217I~
            int[] stat=getStatistic(itsDiscardStatus);             //~1217I~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.getDiscardStatistic itsDiscardStatus="+Arrays.toString(itsDiscardStatus));//~1217I~
            return stat;                                           //~1217I~
        }                                                          //~1217I~
        //*****************************************************************//~1115I~
        public int[] getStatistic(int[] Pstat,int[] Psrc,int PctrSrc)//~1115I~//~1116R~
        {                                                          //~1115I~
        	int rc;                                                //~1115I~
            int[] its=Pstat;                                     //~1115I~
            Arrays.fill(its,0);                                    //~1115I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getStatistic ctrDiscarded="+ctrDiscarded+",PctrSrc="+PctrSrc+",Psrc="+Utils.toString(Psrc,9));//~1130I~//~1216R~
            for (int ii=0;ii<PctrSrc;ii++)                         //~1115I~
            {                                                      //~1115I~
            	int ctr=Psrc[ii];                                  //~1115I~
            	if (ctr!=0)                                        //~1115I~
                {                                                  //~1115I~
//                    its[CSI_ALL]+=ctr;                             //~1115I~//~1217R~
//                    its[ii/CTR_NUMBER_TILE]+=ctr;   //CSI_MAN/CSI_PIN\CSI_SOU/CSI_WORD//~1130R~//~1217R~
//                    int num=ii%CTR_NUMBER_TILE;                    //~1115I~//~1217R~
//                    if (ii>=OFFS_WORDTILE)                         //~1215R~//~1217R~
//                    {                                              //~1215I~//~1217R~
//                        its[CSI_WORD]+=ctr;                    //~1115I~//~1215R~//~1217R~
//                        if (ctr>1)                                 //~1215I~//~1217R~
//                            its[CSI_WORD_DUP]+=ctr;                //~1215I~//~1217R~
//                        if (ctrDiscarded<HV_CTR_TO_CHK_WORD_STARTING)    // count word tile of ctrTaken<6 for samecolor//~1216I~//~1217R~
//                            its[CSI_WORD_STARTING]+=ctr;           //~1216I~//~1217R~
//                    }                                              //~1215I~//~1217R~
//                    else                                           //~1115I~//~1217R~
//                    if (num==0 || num==CTR_NUMBER_TILE-1)         //~1215I~//~1217R~
//                        its[CSI_TERMINAL]+=ctr;                    //~1215I~//~1217R~
//                    else                                           //~1215I~//~1217R~
//                        its[CSI_TANYAO]+=ctr;                      //~1115I~//~1217R~
//                                                                   //~1215I~//~1217R~
//                    if (ctr==2)                                    //~1115I~//~1217R~
//                        its[CSI_PAIR]++;                           //~1122R~//~1217R~
//                    else                                           //~1115I~//~1217R~
//                    if (ctr==3)                                    //~1115I~//~1217R~
//                        its[CSI_PON]++;                            //~1122R~//~1217R~
//                    else                                           //~1115I~//~1217R~
//                    if (ctr==4)                                    //~1115I~//~1217R~
//                        its[CSI_KAN]++;                            //~1122R~//~1217R~
//                    else                                           //~1115I~//~1217R~
//                        its[CSI_SINGLE]++;                         //~1122R~//~1217R~
        			setStatistic(its,ii,ctr);                      //~1217I~
                }                                                  //~1115I~
            }                                                      //~1115I~
            its[CSI_CALL_PONKAN]+=ctrCallPonKan;                   //~1115I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getStatistic its="+Arrays.toString(its));//~1115I~//~1130R~
            return its;                                            //~1115I~
        }                                                          //~1115I~
        //*****************************************************************//~1217I~
        public int[] getStatistic(int[] Pstat)                     //~1217I~
        {                                                          //~1217I~
            Pstat[CSI_CALL_PONKAN]+=ctrCallPonKan;                 //~1217I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getStatistic its="+Arrays.toString(Pstat));//~1217I~
            return Pstat;                                          //~1217I~
        }                                                          //~1217I~
        //*****************************************************************//~1217I~
        private void setStatistic(int[] Pstat,int Ppos,int Pctr)   //~1217R~
        {                                                          //~1217I~
            int[] its=Pstat;                                       //~1217I~
            int ctr=Pctr;                                          //~1217I~
                	its[CSI_ALL]+=ctr;                             //~1217I~
	                its[Ppos/CTR_NUMBER_TILE]+=ctr;	//CSI_MAN/CSI_PIN/CSI_SOU/CSI_WORD//~1217R~
	                int num=Ppos%CTR_NUMBER_TILE;                  //~1217I~
                	if (Ppos>=OFFS_WORDTILE)                       //~1217I~
                    {                                              //~1217I~
//              		its[CSI_WORD]+=ctr;                        //~1217I~//~1224R~
                        if (ctr>1)  //not effective for discard statistic//~1217R~
                			its[CSI_WORD_DUP]+=ctr;                //~1217I~
                        if (ctrDiscarded<HV_CTR_TO_CHK_WORD_STARTING)    // count word tile of ctrTaken<6 for samecolor//~1217I~
                			its[CSI_WORD_STARTING]+=ctr;           //~1217I~
                    }                                              //~1217I~
                    else                                           //~1217I~
                	if (num==0 || num==CTR_NUMBER_TILE-1)          //~1217I~
                		its[CSI_TERMINAL]+=ctr;                    //~1217I~
                    else                                           //~1217I~
                		its[CSI_TANYAO]+=ctr;                      //~1217I~
                                                                   //~1217I~
                    if (ctr==2)                                    //~1217I~
                		its[CSI_PAIR]++;    //no meaning for discard//~1224R~
                    else                                           //~1217I~
                    if (ctr==3)                                    //~1217I~
                		its[CSI_PON]++;     //no meaning for discard//~1224R~
                    else                                           //~1217I~
                    if (ctr==4)                                    //~1217I~
                		its[CSI_KAN]++;     //no meaning for discard//~1224R~
                    else                                           //~1217I~
                		its[CSI_SINGLE]++;  //no meaning fo discard//~1224R~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setStatistic pos="+Ppos+",ctr="+Pctr+",its="+Arrays.toString(its));//~1217I~
        }                                                          //~1217I~
        //***********************************************************************//~1131I~
        public int getIntent()                                     //~1131I~
        {                                                          //~1131I~
            if (Dump.Y) Dump.println("RoundStat.getIntent eswn="+eswn+",intent="+Integer.toHexString(intent));//~1131I~
            return intent;                                         //~1131I~
        }                                                          //~1131I~
        //***********************************************************************//~1131I~
        public void setIntent(int Pintent)                         //~1131I~
        {                                                          //~1131I~
            intent=Pintent;                                        //~1131I~
            if (Dump.Y) Dump.println("RoundStat.setIntent eswn="+eswn+",intent="+Integer.toHexString(intent));//~1131I~
        }                                                          //~1131I~
        //***********************************************************************//~1215I~
        public boolean isSameColorEarth(int Ptype)                 //~1215I~
        {                                                          //~1215I~
            boolean rc=(earthColor & ~(1<<Ptype))==0;               //~1215I~
            if (Dump.Y) Dump.println("RoundStat.isSameEarthColor rc="+rc+",type="+Ptype+",earthColor="+earthColor);//~1215I~
            return rc;
        }                                                          //~1215I~
        //***********************************************************************//~1223I~
        public int getCtrValueWordDup()                            //~1223I~
        {                                                          //~1223I~
            int ctr=0;                                              //~1223I~
            for (int ii=OFFS_WORDTILE;ii<CTR_TILETYPE;ii++)            //~1223I~
            {                                                      //~1223I~
            	if (itsHand[ii]>=2)                                //~1223I~
	            	if (RAUtils.chkValueWordTile(ii,eswn)>0)         //~1223I~
                		ctr++;                                     //~1223I~
            }                                                      //~1223I~
            if (Dump.Y) Dump.println("RoundStat.getCtrValueWordDup rc="+ctr+",eswn="+eswn);//~1223I~
            return ctr;                                             //~1223I~
        }                                                          //~1223I~
        //***********************************************************************//~va70I~
	    //*set shanten of human player when PlayAloneNotify mode   //~va70I~
	    //*at deal and discard                                     //~va70I~
        //***********************************************************************//~va70I~
        public void setShantenYou()                                 //~va70I~
        {                                                          //~va70I~
	        if (!isReachCalled())                                  //~va70I~
            {                                                      //~va70I~
//                TileData[] tdsHand=AG.aPlayers.getHands(PLAYER_YOU);//~va70R~//~va89R~
//                ctrHand=tdsHand.length;                            //~va70R~//~va89R~
//                RAUtils.countTile(tdsHand,itsHand);                //~va70R~//~va89R~
//                currentShanten=getShanten(itsHand,ctrHand);        //~va70R~//~va89R~
                currentShanten=getShantenYou();                    //~va89I~
            }                                                      //~va70I~
            if (Dump.Y) Dump.println("RoundStat setShantenYou eswn="+eswn+",player="+player+",currentShanten="+currentShanten);//~va70I~
        }                                                          //~va70I~
        //***********************************************************************//~va89I~
	    //*set shanten of human player when PlayAloneNotify mode   //~va89I~
	    //*at deal and discard                                     //~va89I~
        //***********************************************************************//~va89I~
        public int getShantenYou()                                 //~va89R~
        {                                                          //~va89I~
            TileData[] tdsHand=AG.aPlayers.getHands(PLAYER_YOU);   //~va89R~
            ctrHand=tdsHand.length;                                //~va89R~
            RAUtils.countTile(tdsHand,itsHand);                    //~va89R~
            int shanten=getShanten(itsHand,ctrHand);               //~va89R~
            if (Dump.Y) Dump.println("RoundStat.getShantenYou eswn="+eswn+",player="+player+",shanten="+shanten);//~va89R~//~va8jR~
            return shanten;                                        //~va89I~
        }                                                          //~va89I~
        //***********************************************************************//~va8jI~
        public int[] getItsHandYou()                               //~va8jI~
        {                                                          //~va8jI~
            TileData[] tdsHand=AG.aPlayers.getHands(PLAYER_YOU);   //~va8jI~
            ctrHand=tdsHand.length;                                //~va8jI~
            RAUtils.countTile(tdsHand,itsHand);                    //~va8jI~
            if (Dump.Y) Dump.println("RoundStat.getItsHandYou eswn="+eswn+",player="+player+",ctrJHand="+ctrHand+",itsHand="+Utils.toString(itsHand,9));//~va8jI~
            return itsHand;                                        //~va8jI~
        }                                                          //~va8jI~
	}//class RSPlayer                                              //~va60M~
}//class RoundStat                                                 //~dataR~//~@@@@R~//~v@@@R~//~va60R~
