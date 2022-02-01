//*CID://+vajdR~: update#= 382;                                    //~vajdR~
//**********************************************************************//~v101I~
//2022/01/25 vajd (bug)FuritenReach err was not set for reach on client//~vajdI~
//2022/01/23 vajb (bug)err FuretenAfterReach was not set for chankan//~vajbI~
//2022/01/22 vaj8 (Bug)When not match notify mode, itshand was notupdated for human on client//~vaj8I~
//2022/01/20 vaj7 display furiten err after reach on complte/drawnhw/drawnlast dialog//~vaj7I~
//2022/01/19 vaj6 set Err for missing Ron(including Take) after Reach//~vaj6I~
//2022/01/19 vaj5 Not ronable when furiten even if taken if furitenreachoption=No//~vaj5I~
//2022/01/18 vaj1 skip kan if loose fixed1 by kan(honor meld in hand)//~vaj1I~
//2022/01/18 vaj0 isFixed1() returns true if not sakiduke mode     //~vaj0I~
//2022/01/15 vaiq when callChii by samecolor,additional to shantendown,chk othercolor exist to discard//~vaiqI~
//2021/11/22 vah3 add Furiten reach reject option                  //~vah3I~
//2021/11/01 vafp (Bug)ctrHand decreased duplecatedly; shanten calc failed.//~vafpI~
//2021/11/01 vafm (Bug)getCtrEarthChanta returns >0 even tanyao earth exist(chanta and tanyao)//~vafmI~
//2021/11/01 vafk INTENT_3SAMESEQ; 2nd call if once called according FixedFirst rule//~vafkI~
//2021/10/28 vafc pon/chii call for INTENT_TANYAO                  //~vafcI~
//2021/10/28 vafb evaluate INTENT_3SAMESEQ                         //~vaf9I~
//2021/10/27 vaf9 evaluate INTENT_STRAIGHT                         //~vaf9I~
//2021/10/26 vaf8 skip reach if PAO status exist                   //~vaf6I~
//2021/10/26 vaf6 (Bug)have to ignore shanten Down for INTENT_3DRAGON//~vaf6I~
//2021/07/29 vabp (Bug of vaad) at human take, not notified Ron    //~vabpI~
//2021/07/28 vabd (Bug)chanta decision missing chk Earth           //~vabdI~
//2021/07/25 vab7 skip reach if other called open reach            //~vab7I~
//2021/07/23 vaaX PlayAlone mode;rinshan ron is not notified(ctrHand was not maintained at Kan)//~vaaXI~
//2021/07/19 vaaU chankan was not notified                         //~vaaUI~
//2021/07/17 vaaP (Bug of Vaad)at discard of reach, shanten was not set. miss notify of Ron at take if reach done//~vaaPI~
//2021/07/09 vaay (Bug)furiten chk; chked prev discarded when at 1st take by ctrDiscardedAllDiscarded=0; reset it at take//~vaayI~
//2021/07/03 vaai strengthen robot; call pon/chii if become shanten 0 with a Yaku+dora>=2//~vaaiI~
//2021/06/30 vaad (Bug)PlayAlone mode,did not notify kan if kan not in deal. maintaine ItsHand also for MatcNotify mode//~vaadI~
//2021/06/30 vaac (Bug)it is required to nextPlayerPonkanr for !thinkRobot//~vaacI~
//2021/06/30 vaab (Bug)it is required to update exposed ctr for !thinkRobot for also open dora//~vaabI~
//2021/06/30 vaaa Notification of Pon/Kan/Chii ignore Reached      //~vaaaI~
//2021/06/30 vaa9 Notify even NOT Think Robot.                     //~vaa9I~
//2021/06/27 vaa2 Notify mode of Match                             //~vaa2I~
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
import com.btmtest.utils.UView;
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
	public boolean swFuritenReachReject;                           //~vah3I~
	public boolean swFuritenReachNo;                                    //~vaj5I~
	private boolean swYakuFixNotFirst;                             //~vaj0I~
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
		swYakuFixNotFirst=RuleSettingYaku.isYakuFixNotFirst();     //~vaj0I~
		swFuritenReachOK=RuleSettingYaku.isFuritenReachOK();       //~va8jR~
		swFuritenReachReject=RuleSettingYaku.isFuritenReachReject();//~vah3I~
		swFuritenReachNo=!swFuritenReachOK && !swFuritenReachReject;//~vaj5I~
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
        if (Dump.Y) Dump.println("RoundStat.reset swFix2="+swFix2);//~vaaUI~
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
//  	int[] itsHand=RSP[Peswn].getItsHandYou();                  //~va8jI~//~vaadR~
    	int[] itsHand=getItsHandEswn(Peswn);    //itsHand is maintaind for also PLAYER_YOU//~vaadI~
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
    //******************************************************************//~vaf6I~
    //*chk other players pao, if openReach option is on return false even if openreach not called//~vaf6I~
    //******************************************************************//~vaf6I~
    public boolean isDiscardableAllExceptOpenReach(int PeswnPlayer)//~vaf6I~
    {                                                              //~vaf6I~
    	boolean rc=true;                                           //~vaf6I~
	    if (paoEswn3Dragon>=0 && paoEswn3Dragon!=PeswnPlayer)       //~vaf6I~
        	rc=false;                                              //~vaf6I~
        else                                                       //~vaf6I~
    	if (paoEswn4Wind>=0 && paoEswn4Wind!=PeswnPlayer)          //~vaf6I~
        	rc=false;                                              //~vaf6I~
        else                                                       //~vaf6I~
    	if (paoEswn4Kan>=0 && paoEswn4Kan!=PeswnPlayer)            //~vaf6I~
        	rc=false;                                              //~vaf6I~
        else                                                       //~vaf6I~
        if (RuleSettingYaku.isAvailableOpenReach())       //openreach available//~vaf6I~
        	rc=false;                                     //return NOT isDiscardableAll//~vaf6I~
        if (Dump.Y) Dump.println("RoundStat.isDiscardableAllExceptOpenReach rc="+rc+"PeswnPlayer="+PeswnPlayer+",paoEswn3Dragon="+paoEswn3Dragon+",paoEswn4Wind="+paoEswn4Wind);//~vaf6I~
        return rc;
    }                                                              //~vaf6I~
    //******************************************************************//~vaf8R~
    //*chk other players pao, if pending pao avaoi reach           //~vaf8R~
    //******************************************************************//~vaf8R~
    public boolean chkPaoForReach(int PeswnPlayer)                 //~vaf8R~
    {                                                              //~vaf8R~
    	boolean rc=true;                                           //~vaf8R~
	    if (paoEswn3Dragon>=0 && paoEswn3Dragon!=PeswnPlayer)      //~vaf8R~
        	rc=false;                                              //~vaf8R~
        else                                                       //~vaf8R~
    	if (paoEswn4Wind>=0 && paoEswn4Wind!=PeswnPlayer)          //~vaf8R~
        	rc=false;                                              //~vaf8R~
        else                                                       //~vaf8R~
    	if (paoEswn4Kan>=0 && paoEswn4Kan!=PeswnPlayer)            //~vaf8R~
        	rc=false;                                              //~vaf8R~
 //     else                                                       //~vaf8R~
 //     if (RuleSettingYaku.isAvailableOpenReach())       //openreach available//~vaf8R~
 //     	rc=false;                                     //return NOT isDiscardableAll//~vaf8R~
        if (Dump.Y) Dump.println("RoundStat.chakPaoForReach rc="+rc+"PeswnPlayer="+PeswnPlayer+",paoEswn3Dragon="+paoEswn3Dragon+",paoEswn4Wind="+paoEswn4Wind);//~vaf8R~
        return rc;                                                 //~vaf8R~
    }                                                              //~vaf8R~
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
//      if (!swServer)                                             //~va60I~//~vaadR~
//      	return;                                                //~va60I~//~vaadR~
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
//  	if (!swThinkRobot)  //maintain itsExposed for isDiscardable//~1201R~//~vaabR~
//      	return;                                                //~1201R~//~vaabR~
        int pos=RAUtils.getPosTile(Ptd);                                   //~va60I~//~1119R~
        itsExposed[pos]++;                                         //~va60R~
        if (Dump.Y) Dump.println("RoundStat.drawDora pos="+pos+",itsExposed="+itsExposed[pos]);//~va60R~
    }                                                              //~va60I~
    //*********************************************************    //~va60M~
    //*from Players after removed from tdsHand <-UADiscard                                   //~1112I~//~1128R~//~vaj7R~
    //*on Serve and YOU on client                                  //~vaj7R~
    //*********************************************************    //~1112I~
    public void discard(int Pplayer,TileData Ptd)                  //~va60I~
    {                                                              //~va60M~
        if (Dump.Y) Dump.println("RoundStat.discard swThinkRobot="+swThinkRobot+",swServer="+swServer+",player="+Pplayer+",td="+Ptd.toString());//~va60I~//~va70R~
//  	if (!swServer)                                             //~va60I~//~va96R~
//      	return;                                                //~va60I~//~va96R~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~va60R~
        int pos=RAUtils.getPosTile(Ptd);                                   //~va60M~//~1119R~
    	if (swServer)            //calStatus will send to all client with GCM_TAKE and GCM_DISCARD//~vaj7R~
        	chkRonableAfterReach(eswn,pos);                        //~vaj7R~
        itsExposed[pos]++;                                         //~va60M~
        RSP[eswn].discard(pos,Ptd);    //update itsHand                            //~va60I~//~1128R~
        RSP[eswn].ctrDiscardedAllDiscarded=ctrDiscardedAll;        //~va8wM~
        itsDiscardedAll[ctrDiscardedAll++]=pos;  //for furiten chk //~va8wI~
                                                                   //~1201I~
    	if (!swServer)                                             //~va96I~
        	return;                                                //~va96I~
                                                                   //~va96I~
//  	if (!swThinkRobot)                                         //~1201M~//~vaa9R~
//      	return;                                                //~1201M~//~vaa9R~
                                                                   //~1201I~
//      itsDiscardedAll[ctrDiscardedAll++]=pos;  //for furiten chk //~va60I~//~1201R~//~va8wR~
	    AG.aRACall.otherDiscard(GCM_DISCARD,Pplayer,eswn,Ptd);                    //~1117I~//~1118R~//~1128R~
        if (Dump.Y) Dump.println("RoundStat.discard eswn="+eswn+",itsExposed="+Utils.toString(itsExposed,9));//~va60R~
        if (Dump.Y) Dump.println("RoundStat.discard ctrDiscardedAll="+ctrDiscardedAll+",itsDiscardedAll="+Utils.toString(itsDiscardedAll,10,ctrDiscardedAll));//~va60I~
    }                                                              //~va60M~
    //*********************************************************    //~1128I~
    //*from UADiscard.nextPlayerPonKan on Server                                              //~1128I~//~1201R~//~vaa2R~
    //*********************************************************    //~1128I~
    public void timeoutPonKan(int Pplayer,TileData PtdDiscarded)   //~1128R~
    {                                                              //~1128I~
        if (Dump.Y) Dump.println("RoundStat.timeoutToPonKan swThinkRobot="+swThinkRobot+",swServer="+swServer+",player="+Pplayer+",tdDiscard="+PtdDiscarded.toString());//~1128R~//~1201R~
//  	if (!swThinkRobot)                                         //~1201I~//~vaacR~
//      	return;                                                //~1201I~//~vaacR~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~1128I~
	    AG.aRACall.otherDiscard(GCM_NEXT_PLAYER_PONKAN,Pplayer,eswn,PtdDiscarded);//~1128R~
        if (Dump.Y) Dump.println("RoundStat.timeoutPonKan exit eswn="+eswn+",player="+Pplayer);//~1128I~
    }                                                              //~1128I~
    //*********************************************************    //~vaa2I~
    //*from UADiscard.nextPlayerPonKan() on Client                 //~vaa2I~
    //*********************************************************    //~vaa2I~
    public void timeoutPonKanClient(int Pplayer/*playerDiscarded*/,TileData PtdDiscarded)//~vaa2R~
    {                                                              //~vaa2I~
        if (Dump.Y) Dump.println("RoundStat.timeoutToPonKan swPlayMatchNotify="+AG.swPlayMatchNotify+",swServer="+swServer+",player="+Pplayer+",tdDiscard="+PtdDiscarded.toString());//~vaa2I~
        if (Pplayer==PLAYER_YOU)                                   //~vaa2R~
        	return;                                                //~vaa2I~
        if (!AG.swPlayMatchNotify)                                 //~vaa2I~
        	return;                                                //~vaa2I~
    	int eswn=Accounts.getCurrentEswn();                        //~vaa2R~
	    AG.aRACall.nextPlayerPonKanPlayMatchNotify(PLAYER_YOU,eswn,PtdDiscarded);//~vaa2R~
        if (Dump.Y) Dump.println("RoundStat.timeoutPonKanClient exit eswn="+eswn+",player="+Pplayer);//~vaa2I~
    }                                                              //~vaa2I~
    //************************************************************************//~1201R~
    //*from UAKan.timeoutRinshanTakable() on server at before take rinshan to enable to other call ron//~1201R~//~vaaUR~
    //************************************************************************//~1201R~
    public boolean timeoutRinshanTakable(int Pplayer)              //~1201I~
    {                                                              //~1201I~
        if (Dump.Y) Dump.println("RoundStat.timeoutRinshanTakable swThinkRobot="+swThinkRobot+",swServer="+swServer+",player="+Pplayer);//~1201R~
    	if (!swServer)                                             //~1201M~
        	return false;                                          //~1201M~
    	if (!swThinkRobot)                                         //~1201I~
        //* calledKan in RAC.otherKa()n is for robot only to call chankan by robot//~vaaUI~
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
        if (Dump.Y) Dump.println("RoundStat.timeoutRinshanTakable rc="+rc);//~1201I~//~vaaUR~
        return rc;
    }                                                              //~1201I~
//    //************************************************************************//~vaaUR~
//    //*from UAKan clientTakableRinshan for notify Chankan inPlayMatchNotifyMode//~vaaUR~
//    //************************************************************************//~vaaUR~
//    public boolean timeoutRinshanTakableClient(int Pplayer)      //~vaaUR~
//    {                                                            //~vaaUR~
//        if (Dump.Y) Dump.println("RoundStat.timeoutRinshanTakableClientplayer="+Pplayer+",swPlayMatchNotify="+AG.swPlayMatchNotify);//~vaaUR~
//        if (!AG.swPlayMatchNotify)                               //~vaaUR~
//            return false;                                        //~vaaUR~
//        int eswn=Accounts.playerToEswn(Pplayer);                 //~vaaUR~
//        int kanType=AG.aPlayers.getKanType();                    //~vaaUR~
//        TileData tdKan;                                          //~vaaUR~
//        if (kanType==KAN_ADD)                                    //~vaaUR~
//            tdKan=AG.aPlayers.getTileKanAdded();    //chk chaknan//~vaaUR~
//        else                                                     //~vaaUR~
//        if (kanType==KAN_TAKEN)                                  //~vaaUR~
//            tdKan=AG.aPlayers.getCurrentTaken();  //before rinshantaken,chk ankan ron//~vaaUR~
//        else                                                     //~vaaUR~
//        {                                                        //~vaaUR~
//            if (Dump.Y) Dump.println("RoundStat.timeoutRinshanTakableRobotClient return by kantype");//~vaaUR~
//            return false;                                        //~vaaUR~
//        }                                                        //~vaaUR~
//        boolean rc=AG.aRACall.otherKanClient(Pplayer,eswn,kanType,tdKan);//~vaaUR~
//        if (Dump.Y) Dump.println("RoundStat.timeoutRinshanTakableClient rc="+rc);//~vaaUR~
//        return rc;                                               //~vaaUR~
//    }                                                            //~vaaUR~
    //************************************************************************//~vaaUI~
    //*from UAKan.takeKan()                                        //~vaaUI~
    //*on Server and human on client                             //~vaaUI~//~vaj8R~
    //*notify to Human                                             //~vaj8I~
    //************************************************************************//~vaaUI~
    public boolean calledKan(boolean PswServer,int Pplayer/*Kan caller*/,int PkanType,TileData PtdKan)//~vaaUI~
    {                                                              //~vaaUI~
        if (Dump.Y) Dump.println("RoundStat.calledKan swServer="+PswServer+",Player="+Pplayer+",kanType="+PkanType+",PtdKan="+PtdKan.toString());//~vaaUI~
        if (Dump.Y) Dump.println("RoundStat.calledKan swPlaAloneNotify="+AG.swPlayAloneNotify+",swPlayMatchNotify="+AG.swPlayMatchNotify);//~vaaUI~
        int pos=RAUtils.getPosTile(PtdKan);                        //~vajbI~
        int playerEswn=Accounts.playerToEswn(Pplayer);             //~vajbI~
        if (PkanType==KAN_ADD)    //chk chankan ron                //~vajbI~
		    chkRonableAfterReach(playerEswn/*discarder*/,pos/*discarded*/);//~vajbR~
        if (!AG.swPlayMatchNotify && !AG.swPlayAloneNotify)        //~vaaUI~
            return false;                                          //~vaaUI~
//      int playerEswn=Accounts.playerToEswn(Pplayer);             //~vaaUI~//~vajbR~
        int yourEswn=Accounts.getCurrentEswn();                    //~vaaUI~
        if (yourEswn==playerEswn)                                   //~vaaUI~
            return false;                                          //~vaaUI~
        boolean rc=AG.aRACall.calledKanNotify(yourEswn,Pplayer,playerEswn,PkanType,PtdKan);//~vaaUI~
        if (Dump.Y) Dump.println("RoundStat.calledKan rc="+rc);    //~vaaUI~
        return rc;                                                 //~vaaUI~
    }                                                              //~vaaUI~
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
    //*On server and YOU on client                                 //~vaj7I~
    //*********************************************************    //~1112I~
    public void takeOne(int Pplayer,int Peswn,TileData Ptd)        //~va60R~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RoundStat.takeOne swServer="+swServer+",player="+Pplayer+",eswn="+Peswn+",td="+Ptd.toString());//~va60I~
//  	if (!swServer)                                             //~va60I~//~vaj8R~
//        if (!AG.swPlayMatchNotify)                               //~vaa2I~//~vaj8R~
//      	return;                                                //~va60I~//~vaj8R~
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
//  	if (!swServer)                                             //~va60I~//~vaadR~
//      	return;                                                //~va60I~//~vaadR~
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
//  	if (!swServer)                                             //~va60I~//~vaadR~
//      	return;                                                //~va60I~//~vaadR~
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
//  	if (!swServer)                                             //~va60I~//~vaadR~
//      	return;                                                //~va60I~//~vaadR~
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
    //*from UAReach on Server and received Client                                      //~1112I~//~vaj7R~
    //*********************************************************    //~1112I~
    public  void reach(int Pplayer)                                //~1112I~
    {                                                              //~1112I~
        if (Dump.Y) Dump.println("RoundStat.reach swThinkRobot="+swThinkRobot+",player="+Pplayer);//~1112I~//~1201R~//~1312R~
//  	if (!swThinkRobot)                                         //~1201I~//~vaaaR~
//      	return;                                                //~1201I~//~vaaaR~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~1112I~
        RSP[eswn].reach();    //set furiten ptr                    //~1112I~
    }                                                              //~1112I~
    //*********************************************************    //~vajdI~
    //*from Players                                                //~vajdI~
    //*on Server and YOU on client                                 //~vajdI~
    //*********************************************************    //~vajdI~
    public  void reachDone(int Pplayer,int Pstatus/*STF_*/,int PreachStatus/*REACH_*/,TileData Ptd)//~vajdR~
    {                                                              //~vajdI~
        if (Dump.Y) Dump.println("RoundStat.reachDone swServer="+swServer+",player="+Pplayer+",status="+Integer.toHexString(Pstatus)+",reachStatus="+PreachStatus+",PtdDiscard="+Ptd.toString());//~vajdR~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~vajdI~
        RSP[eswn].reachDone(Pstatus,PreachStatus,Ptd);    //set furiten ptr//~vajdR~
    }                                                              //~vajdI~
    //*********************************************************    //~va60I~
    //*from UAReach on Server and Client                           //~vaj7I~
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
//      RSP[eswn].reach();                                     //~va60I~//~vab7R~
        RSP[eswn].reachOpen();                                     //~vab7I~
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
    //*********************************************************    //~vab7I~
    public int getCtrOtherReachOpen(int Peswn)                     //~vab7I~
    {                                                              //~vab7I~
    	int ctr=0;                                                 //~vab7I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~vab7I~
        {                                                          //~vab7I~
        	if (ii==Peswn)                                         //~vab7I~
            	continue;                                          //~vab7I~
            if ((RSP[ii].callStatus & CALLSTAT_REACH)!=0           //~vab7I~
            &&  (RSP[ii].callStatus & CALLSTAT_REACH_OPEN)!=0)     //~vab7I~
            	ctr++;                                             //~vab7I~
        }                                                          //~vab7I~
        if (Dump.Y) Dump.println("RoundStat.getCtrOtherReachOpen ctr="+ctr);//~vab7I~
        return ctr;                                                //~vab7I~
    }                                                              //~vab7I~
    //*********************************************************    //~1311I~
    public int chkFuritenSelfBeforeReach(int Peswn,int Ppos)       //~1311I~
    {                                                              //~1311I~
        return RSP[Peswn].chkFuritenSelfBeforeReach(Ppos);         //~1311I~
    }                                                              //~1311I~
    //*********************************************************    //~vaf9I~
    public boolean isAllInHand(int Peswn)                              //~vaf9I~
    {                                                              //~vaf9I~
        if (Dump.Y) Dump.println("RoundStat.isAllInHand eswn="+Peswn+",rc="+RSP[Peswn].swAllInHand);//~vaf9I~
        return RSP[Peswn].swAllInHand;                             //~vaf9I~
    }                                                              //~vaf9I~
    //*********************************************************    //~vaf9R~
    public void setColorStraight(int Peswn,int Pcolor)             //~vaf9R~
    {                                                              //~vaf9R~
        if (Dump.Y) Dump.println("RoundStat.setColorStraight eswn="+Peswn+",color="+Pcolor);//~vaf9R~
        RSP[Peswn].colorStraight=Pcolor;                           //~vaf9R~
    }                                                              //~vaf9R~
    //*********************************************************    //~vaf9R~
    public int getColorStraight(int Peswn)                         //~vaf9R~
    {                                                              //~vaf9R~
        int color=RSP[Peswn].colorStraight;                        //~vaf9R~
        if (Dump.Y) Dump.println("RoundStat.getColorStraight eswn="+Peswn+",color="+color);//~vaf9R~
        return color;                                              //~vaf9R~
    }                                                              //~vaf9R~
    //*********************************************************    //~vaf9R~
    //*chk for 1st call, color and num of earth                    //~vafkR~
    //*********************************************************    //~vafcI~
    public Point getEarthColorStraight(int Peswn)                  //~vaf9R~
    {                                                              //~vaf9R~
    	Point p=RSP[Peswn].getEarthColorStraight();                //~vaf9R~
        if (Dump.Y) Dump.println("RoundStat.getEarthColorStraight eswn="+Peswn+",rc="+Utils.toString(p));//~vaf9R~
        return p;                                                  //~vaf9R~
    }                                                              //~vaf9R~
    //*********************************************************    //~vafbI~
    public void setNum3SameSeq(int Peswn,int Pnum)                 //~vafbI~
    {                                                              //~vafbI~
        if (Dump.Y) Dump.println("RoundStat.setNum3SameSeq eswn="+Peswn+",num="+Pnum);//~vafbI~
        RSP[Peswn].num3SameSeq=Pnum;                               //~vafbI~
    }                                                              //~vafbI~
    //*********************************************************    //~vafbI~
    public int getNum3SameSeq(int Peswn)                           //~vafbI~
    {                                                              //~vafbI~
        int num=RSP[Peswn].num3SameSeq;                            //~vafbI~
        if (Dump.Y) Dump.println("RoundStat.getNum3SameSeq eswn="+Peswn+",num="+num);//~vafbI~
        return num;                                                //~vafbI~
    }                                                              //~vafbI~
    //*********************************************************    //~vafbI~
    public int getEarthNum3SameSeq(int Peswn)                    //~vafbI~
    {                                                              //~vafbI~
    	int num=RSP[Peswn].getEarthNum3SameSeq();                  //~vafbI~
        if (Dump.Y) Dump.println("RoundStat.getEarthNum3SameSeq eswn="+Peswn+",num="+num);//~vafbI~
        return num;                                                  //~vafbI~
    }                                                              //~vafbI~
    //*********************************************************    //~vafkI~
    public Point getEarthColorAndNum3SameSeq(int Peswn)            //~vafkI~
    {                                                              //~vafkI~
    	Point p=RSP[Peswn].getEarthColorAndNum3SameSeq();          //~vafkI~
        if (Dump.Y) Dump.println("RoundStat.getEarthColorAndNum3SameSeq eswn="+Peswn+",colorAndNum="+Utils.toString(p));//~vafkI~
        return p;                                                  //~vafkI~
    }                                                              //~vafkI~
    //*********************************************************    //~vafcI~
    public int setIntentCalled(int Peswn,int Pintent)              //~vafcI~
    {                                                              //~vafcI~
        RSP[Peswn].callStatusIntent|=Pintent;                      //~vafcI~
        int rc=RSP[Peswn].callStatusIntent;                        //~vafcI~
        if (Dump.Y) Dump.println("RoundStat.setIntentCalled eswn="+Peswn+",intent="+Integer.toHexString(Pintent)+",rc="+Integer.toHexString(rc));//~vafcI~
        return rc;
    }                                                              //~vafcI~
    //*********************************************************    //~vafcI~
    public int getIntentCalled(int Peswn)                          //~vafcI~
    {                                                              //~vafcI~
        int rc=RSP[Peswn].callStatusIntent;                        //~vafcI~
        if (Dump.Y) Dump.println("RoundStat.getIntentCalled eswn="+Peswn+",rc="+Integer.toHexString(rc));//~vafcI~
        return rc;                                                //~vafcI~
    }                                                              //~vafcI~
    //*********************************************************    //~vaj7I~
    //*on server at Discard                                        //~vaj7R~
    //*********************************************************    //~vaj7I~
    private void chkRonableAfterReach(int Peswn/*discarder*/,int Ppos/*discarded*/)//~vaj7R~
    {                                                              //~vaj7I~
        if (Dump.Y) Dump.println("RoundStat.chkRonableAfterReach eswn="+Peswn+",pos="+Ppos);//~vaj7I~
        int rc=0;                                                  //~vaj7I~
        for (int ii=0;ii<PLAYERS;ii++)                            //~vaj7I~
        {                                                          //~vaj7I~
        	if (ii==Peswn)                                         //~vaj7I~
            	continue;                                          //~vaj7I~
        	if (RSP[ii].setErrReachFuritenAfterReach()!=0)	//already WINTILE by previous taken or river or Already FURITEN//~vaj7R~
            {                                                      //~vaj7M~
		        if (Dump.Y) Dump.println("RoundStat.chkRonableAfterReach WINTILE changed to FURITEN_AFTER");//~vaj7M~
                continue;                                          //~vaj7M~
            }                                                      //~vaj7M~
		    RSP[ii].setWinStatusAfterReachDiscarded(Ppos); //chk shanten=-1 for human//~vaj7R~
        }                                                          //~vaj7I~
        if (Dump.Y) Dump.println("RoundStat.chkRonableAfterReach Peswn="+Peswn);//~vaj7R~
    }                                                              //~vaj7I~
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
    	private int ctrDiscardedAllReachFuriten=-1;                 //~vaj7I~
    	private int ctrTakenAllReachFuriten=-1;                    //~vaj7I~
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
//      private int statusHand;    //not used                      //~1114R~//~vaf8R~
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
		private int ctrEarthTanyao,ctrEarthChanta;                 //~vabdR~
		private int colorStraight=-1;                              //~vaf9R~
		private int num3SameSeq=-1;                                //~vafbI~
		private int callStatusIntent;  //called Pon/Chii by Tanyao,..//~vafcI~
//  	private boolean swReachErrFuriten;	                       //~vaj5I~//~vajdR~
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
        //*****************************************************    //~vabpI~
	    public int getShanten()                                    //~vabpI~
    	{                                                          //~vabpI~
        	int rc=getShanten(itsHand,ctrHand);                    //~vabpI~
        	if (Dump.Y) Dump.println("RoundStat.getShanten eswn="+eswn+",player="+player+",shanten="+rc);//~vabpI~
        	return rc;                                             //~vabpI~
    	}                                                          //~vabpI~
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
//          if (!swRobot)                                          //~1201I~//~vaadR~
//          {                                                      //~va70I~//~vaadR~
//              //*also set ItsHand                                //~vaadR~
//      		setShantenYou();                                   //~va70I~//~vaadR~
//          	return;                                            //~1201I~//~vaadR~
//          }                                                      //~va70I~//~vaadR~
//          swAllInHand=true;                                      //~1120I~//~va8jR~
        	for (TileData td:Ptds)                                 //~va60I~
            {                                                      //~va60I~
            	int pos=RAUtils.getPosTile(td);                            //~va60I~//~1119R~
                addHandTile(pos,td);                                //~va60I~
            }                                                      //~va60I~
//          if (Dump.Y) Dump.println("RoundStat.RSPlayer.deal tdssHand="+TileData.toString(tdssHand));//~va60I~//~1112R~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.deal hand="+Utils.toString(itsHand,9));//~va60R~
//            if (Dump.Y) Dump.println("RoundStat.RSPlayer.deal handRed="+Utils.toString(itsHandRed,9));//~va60R~//~1129R~
          if (swRobot)                                             //~vaaiR~
		    currentShanten=AG.aRADSmart.getShantenDeal(player,eswn,itsHand,ctrHand);	//set initial intent//~vaaiR~
          else                                                     //~vaaiR~
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
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.addHandTile eswn="+eswn+",itsHand="+Utils.toString(itsHand,9));//~vaadI~//~vaftR~
        }                                                          //~va60I~
        //*****************************************************    //~va60I~
        private void removeHandTile(int Ppos,TileData Ptd)          //~va60R~//~1112R~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.removeHandTile eswn="+eswn+",pos="+Ppos+",Ptd="+TileData.toString(Ptd));//~va60R~
            itsHand[Ppos]--;                                       //~va60R~
            ctrHand--;                                             //~vaaXR~
//          tdssHand[Ppos][Ptd.ctrRemain]=null;                    //~va60I~//~1112R~
//            if (Ptd.isRed5())                                      //~va60I~//~1129R~
//                itsHandRed[Ppos]--;                                //~va60R~//~1129R~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.removeHandTile eswn="+eswn+",itsHand="+Utils.toString(itsHand,9));//~vaadI~//~vaftR~
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
        //*********************************************************************************    //~va60I~//~vaadR~
        //*from Players.takeOne<--UATake.takeOne(also for Kan Take)//~vaadR~
        //*on server for all player including robot, on client PLAYER_YOU only//~vaadI~
        //*********************************************************************************//~vaadI~
        public void takeOne(int Ppos,TileData Ptd)                 //~va60I~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeOne eswn="+eswn+",swRobot="+swRobot+",swServer="+swServer+",swPlayAloneNotify="+AG.swPlayAloneNotify+",swPlaymatchNotify="+AG.swPlayMatchNotify);//~1201I~//~vaa2R~//~vaj7R~
            if (swServer)                                          //~vaj7I~
	        	setErrReachFuritenAfterReachByNextAction(GCM_TAKE);	//WINTILE to FURITEN_AFTER//~vaj7I~
			callStatus &=~CALLSTAT_REACH_ONESHOT; //reset oneshot at take of reacher//~1219I~
        	ctrTaken++;                                            //~1114I~//~1223M~
//      	ctrDiscardedAllTaken=ctrDiscardedAll;	//for chk furiten discarded after taken//~va60I~//~1223M~//~va8wR~
        	ctrDiscardedAllDiscarded=ctrDiscardedAll+1;	//chk furiten from my discrad(for furiten chk at reach)//~vaayI~
//          setErrReachFuritenAfterReach(eswn);                    //~vaj7R~
            int pos=RAUtils.getPosTile(Ptd);                       //~vaadI~
        	addHandTile(pos,Ptd);                                  //~vaadI~
    		if (!swRobot)                                          //~1201I~
            {                                                      //~va81I~
                if (swServer)                                      //~vaj7R~
	                setWinStatusAfterReachTaken();  //set WINTILE  //~vaj7R~
      			if (AG.swPlayAloneNotify)                          //~va81I~
                	AG.aRACall.takeOnePlayAloneNotify(Ppos,Ptd);   //~va81I~
                else                                               //~vaa2I~
      			if (AG.swPlayMatchNotify)                          //~vaa2R~
                	AG.aRACall.takeOnePlayMatchNotify(eswn,player,Ppos,Ptd);//~vaa2R~
//              int pos=RAUtils.getPosTile(Ptd);                   //~vaadR~
//  	       	addHandTile(pos,Ptd);                              //~vaadR~
        		return;                                            //~1201I~
            }                                                      //~va81I~
            else                                                   //~vaj8I~
            {                                                      //~vaj8I~
                if (!swServer)                                     //~vaj8I~
        			return;      //robot on client                 //~vaj8I~
            }                                                      //~vaj8I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeOne eswn="+eswn+",ctrTaken="+ctrTaken+",ctrDiscardedAllDiscarded="+ctrDiscardedAllDiscarded+",Ptd="+TileData.toString(Ptd));//~va60R~//~1114R~//~va8wR~
//          int pos=RAUtils.getPosTile(Ptd);                                //~va60I~//~1119R~//~vaadR~
//      	addHandTile(pos,Ptd);                                   //~va60I~//~vaadR~
//      	if ((TestOption.option2 & TO2_OPENHAND)!=0 && AG.swTrainingMode)//~1205I~//~1220R~
        	if ((TestOption.option2 & TO2_OPENHAND)!=0                     )//~1220I~
            	AG.aHands.drawOpenTakeOneRobot(player,eswn,Ptd);    //~1201R~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeOne hand="+Utils.toString(itsHand,9));//~va60R~
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
//      	if (swReachErrFuriten)                                 //~vaj5I~//~vajdR~
//  	        setReachStatusErrFuriten();                        //~vaj5I~//~vajdR~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.reach eswn="+eswn+",ctrDiscardedReach="+ctrDiscardedAllReach);//~va60I~
        }                                                          //~va60I~
//        //*****************************************************    //~vaj5I~//~vajdR~
//        //*from RAReach.chkFuritenMultiWait                        //~vaj7I~//~vajdR~
//        //*****************************************************    //~vaj7I~//~vajdR~
//        public void setReachErrFuriten(boolean PswErr)             //~vaj5I~//~vajdR~
//        {                                                          //~vaj5I~//~vajdR~
//            swReachErrFuriten=PswErr;                              //~vaj5I~//~vajdR~
//            if (Dump.Y) Dump.println("RoundStat.RSPlayer.setReachErrFuriten swReacherrFuriten="+swReachErrFuriten+",callStatus="+Integer.toHexString(callStatus));//~vaj5I~//~vajdR~
//        }                                                          //~vaj5I~//~vajdR~
        //*****************************************************    //~vaj5I~
        //*from RS.reach()                                         //~vaj7I~
        //*****************************************************    //~vaj7I~
//      private void setReachStatusErrFuriten()                    //~vaj5I~//~vajdR~
        public  void setReachStatusErrFuriten()                    //~vajdI~
        {                                                          //~vaj5I~
          if (swFuritenReachNo) //furitenReach:None; no msg may cause chombo//~vaj6I~
			callStatus|=CALLSTAT_REACH_ERRFURITEN; //reach oneshot //~vaj5I~
          else                                                     //~vaj6I~
          if (swFuritenReachOK) //furitenReach:OK  ; allow furiten reach//~vaj6I~
			callStatus|=CALLSTAT_REACH_OKFURITEN; //allow ron by take//~vaj6I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setReachStatusErrFuriten swFuritenReachNo="+swFuritenReachNo+",swFuritenReachOK="+swFuritenReachOK+",callStatus="+Integer.toHexString(callStatus));//~vaj5I~//~vaj6R~
        }                                                          //~vaj5I~
        //*********************************************************//~vajdI~
        //*from RS                                                 //~vajdI~
	    //*on Server and YOU on client                             //~vajdI~
        //*********************************************************//~vajdI~
        public  void reachDone(int Pstatus/*STF_*/,int PreachStatus/*REACH_*/,TileData Ptd)//~vajdR~
        {                                                          //~vajdI~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.reachDone swServer="+swServer+",player="+player+",eswn="+eswn+",swRobot="+swRobot+",status="+Integer.toHexString(Pstatus)+",reachStatus="+PreachStatus+",old callStatus="+Integer.toHexString(callStatus)+",tileData="+Ptd.toString());//~vajdR~
            if (swRobot)                                           //~vajdI~
            	return;                                            //~vajdI~
            if (swServer)                                          //~vajdI~
            {                                                      //~vajdI~
            	int pos=RAUtils.getPosTile(Ptd);                   //~vajdI~
	            AG.aRAReach.chkReachDoneFuriten(eswn,pos);         //~vajdR~
            }                                                      //~vajdI~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.reachDone swServer="+swServer+",player="+player+",eswn="+eswn+",swRobot="+swRobot+",new callStatus="+Integer.toHexString(callStatus));//~vajdI~
        }                                                          //~vajdI~
        //*****************************************************    //~vaj5I~
        public boolean isReachStatusErrFuriten()                   //~vaj5I~
        {                                                          //~vaj5I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isReachStatusErrFuriten before eswn="+eswn+",callStatus="+Integer.toHexString(callStatus));//~vaj7I~//+vajdR~
  			boolean rc=(callStatus & CALLSTAT_REACH_ERRFURITEN)!=0; //reach oneshot//~vaj5I~
            if ((callStatus & CALLSTAT_REACH_FURITEN_AFTER)!=0)    //~vaj7I~
                rc=true;                                           //~vaj7R~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isReachStatusErrFuriten rc="+rc+",callStatus="+Integer.toHexString(callStatus));//~vaj5I~
            return rc;
        }                                                          //~vaj5I~
        //*****************************************************    //~vaj7I~
        //*On Server,at Discard, Pon, Kan, Chii, Take              //~vaj7I~
        //*****************************************************    //~vaj7I~
        private void setErrReachFuritenAfterReachByNextAction(int Paction)//~vaj7R~
        {                                                          //~vaj7I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setErrReachFuritenAfterReachByNextAction action="+Paction+",eswn="+eswn);//~vaj7R~
            int ctrNew=0;                                          //~vajbI~
            for (int ii=0;ii<PLAYERS;ii++)                         //~vaj7I~
            {                                                      //~vaj7I~
            	if ((Paction==GCM_DISCARD && ii==eswn)	//me discard after take//~vaj7R~
            	||  (Paction!=GCM_DISCARD && ii!=eswn) 	//other player action//~vaj7R~
                )                                                  //~vaj7I~
                	if (RSP[ii].setErrReachFuritenAfterReach()>0)//now or already FURITEN_AFTER//~vaj7R~//~vajbR~
                    	ctrNew++;                                  //~vajbI~
            }                                                      //~vaj7I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setErrReachFuritenAfterReach eswn="+eswn+",ctrNew="+ctrNew);//~vaj7R~//~vajbR~
        }                                                          //~vaj7I~
        //*****************************************************    //~vaj7I~
        //*on Server at Discard                                    //~vaj7R~
        //*rc:1:now set furiten, -1: already set                   //~vaj7I~
        //*****************************************************    //~vaj7I~
        private int setErrReachFuritenAfterReach()                 //~vaj7R~
        {                                                          //~vaj7I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setErrReachFuritenAfterReach before swServer="+swServer+",eswn="+eswn+",callStatus="+Integer.toHexString(callStatus));//~vaj7R~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setErrReachFuritenAfterReach ctrDiscardedAll="+ctrDiscardedAll+",ctrTakenAll="+ctrTakenAll+",ctrDiscardedAllReachFuriten="+ctrDiscardedAllReachFuriten+",ctrTakenAllReachFuriten="+ctrTakenAllReachFuriten);//~vaj7R~
            int rc=0;                                          //~vaj7R~
			if ((callStatus & CALLSTAT_REACH_WINTILE)!=0)               //~vaj7I~
            {                                                      //~vaj7I~
				if ((callStatus & CALLSTAT_REACH_FURITEN_AFTER)==0)//~vaj7I~
                {                                                  //~vaj7I~
					callStatus|=CALLSTAT_REACH_FURITEN_AFTER;         //0x80//~vaj7R~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~vaj7M~
                                    UView.showToastLong("FuritenAfterReach eswn="+eswn);//~vaj7M~//~vaj8R~
                    rc=1;                                          //~vaj7I~
                }                                                  //~vaj7I~
                else                                               //~vaj7I~
                	rc=-1;                                         //~vaj7I~
            }                                                      //~vaj7I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setErrReachStatusFuritenAfterReach take/discard after wintile eswn="+eswn+",rc="+rc+",after callStatus="+Integer.toHexString(callStatus));//~vaj7R~
            return rc;                                         //~vaj7R~
        }                                                          //~vaj7I~
        //****************************************************************//~vaj7R~
        //*rc:true:now detected WINTILE at first under Furiten at Reach//~vaj7I~
        //****************************************************************//~vaj7I~
        private void setWinTileAfterReach(boolean PswTake)         //~vaj7R~
        {                                                          //~vaj7I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setWinTileAfterReach eswn="+eswn+",swTake="+PswTake+",old callStatus="+Integer.toHexString(callStatus));//~vaj7R~
			if ((callStatus & CALLSTAT_REACH_OKFURITEN)==0) //0x40 not Furiten at reach//~vaj7R~
            {                                                      //~vaj7I~
				if ((callStatus & CALLSTAT_REACH_WINTILE)==0)         //0x80//~vaj7R~
                {                                                  //~vaj7I~
                    callStatus|=CALLSTAT_REACH_WINTILE;         //0x80//~vaj7I~
                    ctrDiscardedAllReachFuriten=ctrDiscardedAll;   //~vaj7R~
                    ctrTakenAllReachFuriten=ctrTakenAll;           //~vaj7R~
		        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setWinTileAfterReach set WINTILE eswn="+eswn);//~vaj7I~
                }                                                  //~vaj7I~
            }                                                      //~vaj7I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setWinTileAfterReach wintile swTake="+PswTake+",eswn="+eswn+",new callStatus="+Integer.toHexString(callStatus));//~vaj7R~
        }                                                          //~vaj7I~
        //*****************************************************    //~vaj6I~
        public boolean isReachStatusOKFuriten()                    //~vaj6I~
        {                                                          //~vaj6I~
			boolean rc=(callStatus & CALLSTAT_REACH_OKFURITEN)!=0; //reach oneshot//~vaj6I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isReachStatusOKFuriten rc="+rc+",callStatus="+Integer.toHexString(callStatus));//~vaj6I~
            return rc;                                             //~vaj6I~
        }                                                          //~vaj6I~
        //*****************************************************    //~vab7I~
        public void reachOpen()                                    //~vab7I~
        {                                                          //~vab7I~
	        reach();                                               //~vab7I~
			callStatus|=CALLSTAT_REACH_OPEN; //reach called         //~vab7I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.reachOpen eswn="+eswn+",callStatus="+Integer.toHexString(callStatus));//~vab7I~
        }                                                          //~vab7I~
        //*****************************************************    //~1306I~
        public boolean isReachCalled()                             //~1118I~
        {                                                          //~1118I~
			boolean rc=(callStatus & CALLSTAT_REACH)!=0; //reach called//~1118I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isReachCalled eswn="+eswn+",rc="+rc);//~1118I~
            return rc;
        }                                                          //~1118I~
        //*****************************************************    //~vaaPI~
        public boolean isSetShantenAtReach()                       //~vaaPI~
        {                                                          //~vaaPI~
			boolean rc=(callStatus & CALLSTAT_REACH_SET_SHANTEN)!=0; //reach called//~vaaPI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isSetShantenAtReach eswn="+eswn+",player="+player+",rc="+rc);//~vaaPI~
            return rc;                                             //~vaaPI~
        }                                                          //~vaaPI~
        //*****************************************************    //~vaaPI~
        public void setSetShantenAtReach()                         //~vaaPI~
        {                                                          //~vaaPI~
			callStatus |= CALLSTAT_REACH_SET_SHANTEN;              //~vaaPI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.setSetShantenAtReach eswn="+eswn+",player="+player);//~vaaPI~
        }                                                          //~vaaPI~
        //*****************************************************    //~va60I~
        //*called from Players.discard after tdsHand updated       //~vafpR~
        //*on server for all player including robot, on client PLAYER_YOU only//~vafpI~
        //*****************************************************    //~vaadI~
        public void discard(int Ppos,TileData Ptd)                 //~va60I~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.discard swServer="+swServer+",eswn="+eswn+",swRobot="+swRobot+",swThinkRobot="+swThinkRobot+",eswn="+eswn+",Ppos="+Ppos+",ctrDiscarded="+ctrDiscarded+",Ptd="+Ptd.toString());//~va60R~//~1114R~//~1128R~//~1201R~//~1223R~//~vaa2R~
            if (swServer)                                          //~vaj7I~
	        	setErrReachFuritenAfterReachByNextAction(GCM_DISCARD);	//WINTILE to FURITEN_AFTER//~vaj7I~
            itsDiscarded[Ppos]++;   //for furiten chk     //for also humen         //~va60I~//~1223I~
//  		if (!swServer)                                         //~va96I~//~vaadR~
//          {                                                      //~va96I~//~vaadR~
//  	   		itsDiscardedSelf[ctrDiscarded++]=Ppos; //players discarded in the seq of discard,to chk furiten from reach/my discarded//~va96I~//~vaadR~
//              if (!swRobot && AG.swPlayMatchNotify)              //~vaa2I~//~vaadR~
//              	setShantenYou();                               //~vaa2I~//~vaadR~
//      		return;                                            //~va96I~//~vaadR~
//          }                                                      //~va96I~//~vaadR~
  		  if (swServer)                                            //~vaadI~
          {                                                        //~vaadI~
	        setStatistic(itsDiscardStatus,Ppos,1/*Pctr*/);  //for also human        //~1217I~//~1223I~
          }                                                        //~vaadI~
//          ctrHand=AG.aPlayers.getHands(player).length;  //after removed from Hand by Pon/Kan/Chii//~1201I~//~1223M~//~vafpR~
            //ctrhand maintaind only by removeHandTile and addHandTile//~vafpI~
//      	ctrDiscarded++;                                        //~1114I~//~1201M~//~1223M~//~1311R~
	   		itsDiscardedSelf[ctrDiscarded++]=Ppos; //players discarded in the seq of discard,to chk furiten from reach/my discarded//~1311I~
            if (!swRobot)                                          //~1201I~
            {                                                      //~va70I~
				removeHandTile(Ppos,Ptd);                          //~vaadI~
//          	if (AG.swPlayAloneNotify)                          //~va70I~//~va74R~
            	if (AG.swTrainingMode)                             //~va74I~
//              	setShantenYou();                               //~va70I~//~vaadR~
	                setCurrentShantenYou();                        //~vaadI~
                else                                               //~vaa2I~
				if (AG.swPlayMatchNotify)                          //~vaa2I~
//              	setShantenYou();                               //~vaa2I~//~vaadR~
	                setCurrentShantenYou();                        //~vaadI~
            	return;                                             //~1201I~
            }                                                      //~va70I~
		    removeHandTile(Ppos,Ptd);                              //~va60R~
   		    if (!swServer)                                         //~vaadI~
            	return;                                            //~vaadI~
//      	if ((TestOption.option2 & TO2_OPENHAND)!=0 && AG.swTrainingMode)//~1205I~//~1220R~//~1224M~
        	if ((TestOption.option2 & TO2_OPENHAND)!=0                     )//~1220I~//~1224M~
            	AG.aHands.drawOpenDiscardRobot(player,eswn,Ptd);    //~1201R~//~1224M~
            if (!swThinkRobot)                                     //~1201I~
            	return;                                             //~1201I~
	    	setCurrentShanten();                                   //~1201I~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.discard ctrHand="+ctrHand+",itsHand="+Utils.toString(itsHand,9));//~va60R~//~vafmR~
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
        //*****************************************************    //~vaj1I~
        public int getCtrFixedFirstOverFix2()                      //~vaj1I~
        {                                                          //~vaj1I~
           	int ctrFirst=ctrFixedFirst+getCtrFixedInHand();  	//pair ctr of WGR, round, wind//~vaj1I~
            if (swFix2)                                            //~vaj1I~
            	ctrFirst--;                                        //~vaj1I~
            if (Dump.Y) Dump.println("RoundStat.RSPlayer.getCtrFixedFirst rc="+ctrFirst);//~vaj1I~
            return ctrFirst;                                       //~vaj1I~
        }                                                          //~vaj1I~
        //*****************************************************    //~1130I~
        private boolean isFixed1InHand()                            //~1130I~//~va8vR~
        {                                                          //~1130I~
        	boolean rc=false;                                      //~1130I~
//          if (!swRobot)                                          //~va8vI~//~vaadR~
//      		getItsHandYou();                                   //~va8vI~//~vaadR~
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
        public  int getCtrFixedInHand()                        //~va8uI~//~vafpR~
        {                                                          //~va8uI~
        	int rc;                                                //~va8uI~
//          if (!swRobot)                                          //~va8uI~//~vaadR~
//      		getItsHandYou();                                   //~va8uI~//~vaadR~
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
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getCtrValueWordSameAndPairInhand");//~vabdI~
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
            if (swServer)                                          //~vaj7I~
	        	setErrReachFuritenAfterReachByNextAction(GCM_CHII);	//WINTILE to FURITEN_AFTER//~vaj7I~
        	ctrDiscardedAllDiscarded=ctrDiscardedAll+1;	//chk furiten from my discrad(for furiten chk at reach)//~vaayI~
            if (swReachOneShot)                                    //~1219I~
                resetOtherReachOneShot();                          //~1219I~
            swAllInHand=false;                                     //~1120I~//~1223M~
        	pairExposedChii(Ptds);                                 //~va60I~//~1223M~
//      	if (!swRobot)                                          //~1201I~//~1223R~
//          	return;                                            //~1201I~//~1223R~
        	ctrChii++;                                             //~1114I~
    	    if (!swServer)                                         //~vaadI~
        	    return;                                            //~vaadI~
            earthColor|=1<<Ptds[0].type;                           //~1215I~//~1223M~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeChii eswn="+eswn+",ctrChii="+ctrChii+",Ptds="+TileData.toString(Ptds));//~va60R~//~1114R~
            itsPairStatus[ctrPairStatus++]=getPairStatus(PS_SEQ,Ptds);//~1114I~
        }                                                          //~va60I~
        //*****************************************************    //~vaf9R~
        public Point getEarthColorStraight()                       //~vaf9R~
        {                                                          //~vaf9R~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getEarthColorStraight eswn="+eswn+",player="+player+",ctrChii="+ctrChii);//~vaf9R~//~vafbR~
            int num=CTR_NUMBER_TILE,type=-1;                       //~vaf9R~
            Point p=null;                                          //~vaf9R~
            TileData[][] tdss=AG.aPlayers.getEarth(player);        //~vaf9R~
            if (AG.aPlayers.getCtrPair(player)==1 && ctrChii==1)                      //~vaf9R~//~vafbR~
            {                                                      //~vaf9R~
            	TileData[] tds=tdss[0];                            //~vaf9R~
                for (TileData td:tds)                              //~vaf9R~
                	if ((td.flag & TDF_CHII)!=0)                   //~vaf9R~
                    	if (td.number<num)                         //~vaf9R~
                        	num=td.number;                         //~vaf9R~
                if (num==TN1 || num==TN4 || num==TN7)              //~vaf9R~
                	p=new Point(tds[0].type,num);                  //~vaf9R~
            }                                                      //~vaf9R~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getEarthColorStraignt eswn="+eswn+",rc="+Utils.toString(p));//~vaf9R~
            return p;
        }                                                          //~vaf9R~
        //*****************************************************    //~vafbI~
        public int getEarthNum3SameSeq()                         //~vafbI~
        {                                                          //~vafbI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getEarthNum3SameSeq eswn="+eswn+",player="+player+",ctrChii="+ctrChii);//~vafbR~
            int num=-1;                                            //~vafbI~
            TileData[][] tdss=AG.aPlayers.getEarth(player);        //~vafbI~
            if (AG.aPlayers.getCtrPair(player)==1 && ctrChii==1)   //~vafbI~
            {                                                      //~vafbI~
            	TileData[] tds=tdss[0];                            //~vafbI~
                for (TileData td:tds)                              //~vafbI~
                {                                                  //~vafbI~
	        		if (Dump.Y) Dump.println("RoundStat.RSPlayer.getEarthNum3SameSeq td="+td.toString());//~vafbI~
                	if ((td.flag & TDF_CHII)!=0)                   //~vafbI~
                    	if (num<0 || td.number<num)                //~vafbI~
                        	num=td.number;                         //~vafbI~
                }                                                  //~vafbI~
            }                                                      //~vafbI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getEarthNum3SameSeq eswn="+eswn+",num="+num);//~vafbR~
            return num;                                            //~vafbI~
        }                                                          //~vafbI~
        //*****************************************************    //~vafkI~
        public Point getEarthColorAndNum3SameSeq()                 //~vafkI~
        {                                                          //~vafkI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getEarthColorAndNum3SameSeq eswn="+eswn+",player="+player+",ctrChii="+ctrChii);//~vafkI~
            Point p=null;                                          //~vafkI~
            int num=-1;                                            //~vafkI~
            int type=-1;                                           //~vafkI~
            TileData[][] tdss=AG.aPlayers.getEarth(player);        //~vafkI~
            if (AG.aPlayers.getCtrPair(player)==1 && ctrChii==1)   //~vafkI~
            {                                                      //~vafkI~
            	TileData[] tds=tdss[0];                            //~vafkI~
                for (TileData td:tds)                              //~vafkI~
                {                                                  //~vafkI~
	        		if (Dump.Y) Dump.println("RoundStat.RSPlayer.getEarthColorAndNum3SameSeq td="+td.toString());//~vafkR~
                	if ((td.flag & TDF_CHII)!=0)                   //~vafkI~
                    	if (num<0 || td.number<num)                //~vafkI~
                        {                                          //~vafkI~
                        	num=td.number;                         //~vafkI~
                            type=td.type;                          //~vafkI~
                        }                                          //~vafkI~
                }                                                  //~vafkI~
                if (num!=-1)                                       //~vafkI~
                	p=new Point(type,num);                         //~vafkI~
            }                                                      //~vafkI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getEarthColorAndNum3SameSeq eswn="+eswn+",colorAndNum="+Utils.toString(p));//~vafkR~
            return p;                                              //~vafkI~
        }                                                          //~vafkI~
        //*****************************************************    //~va60I~
        public void takePon(int Ppos,TileData[] Ptds)              //~va60I~
        {                                                          //~va60I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takePon eswn="+eswn+",swRobot="+swRobot+",swServer="+swServer);//~vaayI~//~vaj7R~
            if (swServer)                                          //~vaj7I~
	        	setErrReachFuritenAfterReachByNextAction(GCM_PON);	//WINTILE to FURITEN_AFTER//~vaj7I~
        	ctrDiscardedAllDiscarded=ctrDiscardedAll+1;	//chk furiten from my discrad(for furiten chk at reach)//~vaayI~
            swAllInHand=false;                                     //~1120I~
            if (swReachOneShot)                                    //~1219I~
                resetOtherReachOneShot();	//including human player//~1219I~
        	pairExposedPon(Ppos,Ptds);                       //~va60R~//~1223M~
        	ctrPon++;                                              //~1114I~
    		if (!swServer)                                         //~vaadI~
		      	return;                                            //~vaadI~
//      	if (!swRobot)                                          //~1223I~
//          	return;                                            //~1223I~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takePon eswn="+eswn+",Ppos="+Ppos+",ctrPon="+ctrPon+",Ptds="+TileData.toString(Ptds));//~va60I~//~1114R~
        	if (Ppos>=OFFS_WORDTILE)                               //~va60R~
            {                                                      //~1118I~
		        takePonWord(Ppos);                                 //~va60I~
        		if (RAUtils.chkValueWordTile(Ptds[0],eswn)!=0)         //~1118I~
                {                                                  //~vaj0I~
        			if (ctrPairStatus==0)                          //~1118R~//~vaj0R~
                    {                                              //~va8uI~
			        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takePon set swFixed1 by ctrPairStatus="+ctrPairStatus+",eswn="+eswn+",Ppos="+Ppos);//~vaj0I~
                    	swFixed1=true;                             //~1118I~
                        ctrFixedFirst++;                           //~va8uI~
                    }                                              //~va8uI~
                    else                                           //~vaj0I~
        			if (swYakuFixNotFirst)                         //~vaj0I~
                    {                                              //~vaj0I~
			        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takePon set swFixed1 by swYakuFixNotFirst="+swYakuFixNotFirst+",eswn="+eswn+",Ppos="+Ppos);//~vaj0I~
                    	swFixed1=true;                             //~vaj0I~
                    }                                              //~vaj0I~
                }                                                  //~vaj0I~
            }                                                      //~1118I~
            else                                                   //~1215I~
                earthColor|=1<<Ptds[0].type;                       //~1215I~
            itsPairStatus[ctrPairStatus++]=getPairStatus(PS_PON,Ptds);//~1114I~
        }                                                          //~va60I~
        //*****************************************************    //~va60M~
        public void takeKan(int Ppos,TileData[] Ptds)              //~va60R~
        {                                                          //~va60M~
			if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeKan swServer="+swServer+",eswn="+eswn+",swRobot="+swRobot);//~vaj7I~
            if (swServer)                                          //~vaj7I~
	        	setErrReachFuritenAfterReachByNextAction(GCM_KAN);	//WINTILE to FURITEN_AFTER//~vaj7I~
            if (!Ptds[0].isKanTaken())                             //~1120I~
	            swAllInHand=false;                                 //~1120I~
            if (swReachOneShot)                                    //~1219I~
                resetOtherReachOneShot();	//including human player//~1219I~
        	ctrKan++;                                              //~1114I~
        	boolean swAdd=Ptds[0].isKanAdd();                      //~va60I~
			if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeKan swKanAdd="+swAdd+",ptds="+Utils.toString(Ptds));//~vajbI~
        	pairExposedKan(Ppos,Ptds,swAdd);                       //~va60R~
  			if (!swServer)                                         //~vaadI~
      			return;                                            //~vaadI~
        	if (!swAdd && Ppos>=OFFS_WORDTILE)                     //~va60R~
            {                                                      //~1118I~
        		takePonWord(Ppos);                                 //~va60M~
        		if (RAUtils.chkValueWordTile(Ptds[0],eswn)!=0)         //~1118I~
                {                                                  //~vaj0I~
        			if (ctrPairStatus==0)                          //~1118I~//~vaj0R~
                    {                                              //~va8uI~
			        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeKan set swFixed1 by ctrPairStatus="+ctrPairStatus+",eswn="+eswn+",Ppos="+Ppos);//~vaj0I~
                    	swFixed1=true;                             //~1118I~
                        ctrFixedFirst++;                           //~va8uI~
                    }                                              //~va8uI~
                    else                                           //~vaj0I~
                    if (swYakuFixNotFirst)                         //~vaj0I~
                    {                                              //~vaj0I~
			        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeKan set swFixed1 by swYakuFixNotFirst="+swYakuFixNotFirst+",eswn="+eswn+",Ppos="+Ppos);//~vaj0I~
                    	swFixed1=true;                             //~vaj0I~
                    }                                              //~vaj0I~
                }                                                  //~vaj0I~
            }                                                      //~1118I~
        	if (Ppos<OFFS_WORDTILE)                                //~1215I~
                earthColor|=1<<Ptds[0].type;                       //~1215I~
            pao4Kan++;                                             //~va60M~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeKan eswn="+eswn+",Ppos="+Ppos+",ctrKan="+ctrKan+",pao4kan="+pao4Kan);//~va60R~//~1114R~
            if (pao4Kan==3)                                        //~va60M~
            	setPao4Kan(eswn);                                  //~va60M~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeKan eswn="+eswn+",itsPairStatus="+Utils.toHexString(itsPairStatus));//~vajbI~
            if (swAdd)	//chankan                                  //~1303I~
            {                                                      //~1303I~
//              int idxPon=srchPonToAdd(Ptds[0]);                      //~1303I~//~vajbR~
                int idxPon=srchPonKanToAdd(Ptds[0]);               //~vajbI~
                if (idxPon>=0)                                     //~1303I~
		            itsPairStatus[idxPon]=getPairStatus(PS_KAN,Ptds);//~1303I~
                else                                               //~1303I~
        			if (Dump.Y) Dump.println("RoundStat.RSPlayer.takeKan @@@@ srchPonToKan error");//~1303I~
            }                                                      //~1303I~
            else                                                   //~1303I~
	            itsPairStatus[ctrPairStatus++]=getPairStatus(PS_KAN,Ptds);//~1114I~//~1303R~
        }                                                          //~va60M~
        //******************************************               //~1303I~
        //*return earth index                                      //~vajbI~
        //******************************************               //~vajbI~
//      private int srchPonToAdd(TileData Ptd)                     //~1303I~//~vaaUR~
        public  int srchPonToAdd(TileData Ptd)                     //~vaaUI~
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
        //******************************************               //~vajbI~
        //*including kan earth                                     //~vajbI~
        //******************************************               //~vajbI~
        public  int srchPonKanToAdd(TileData Ptd)                  //~vajbI~
        {                                                          //~vajbI~
        	int rc=-1,idx;                                         //~vajbI~
        //*****************************                            //~vajbI~
        	TileData[][] tdssPon=AG.aPlayers.getPonKanEarth(player);	//pon and kan on earth//~vajbI~
            if (tdssPon!=null)                                     //~vajbI~
            {                                                      //~vajbI~
                idx=0;                                             //~vajbI~
                for (TileData[] tds:tdssPon)                       //~vajbI~
                {                                                  //~vajbI~
                    if (tds==null)                                 //~vajbI~
                        break;                                     //~vajbI~
                    if (Ptd.type==tds[0].type && Ptd.number==tds[0].number)//~vajbI~
                    {                                              //~vajbI~
                        rc=idx;                                    //~vajbI~
                        break;                                     //~vajbI~
                    }                                              //~vajbI~
                    idx++;                                         //~vajbI~
                }                                                  //~vajbI~
            }                                                      //~vajbI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.srchPonKanToAdd eswn="+eswn+",rc="+rc+",Ptd="+Ptd.toString());//~vajbI~
            return rc;                                             //~vajbI~
        }                                                          //~vajbI~
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
        //*from RADSOther.chkSafe                                  //~va96R~
        //*rc:-1:discarde before reach, >1:discarded some time, =0:not reach done//~va96I~
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
        //*set PS_TANYAO/PS_CHANTA                                 //~vafmI~
        //*****************************************************************//~vafmI~
        private int getPairStatus()                                //~1114I~
        {                                                          //~1114I~
        	int stat=0;                                            //~1114I~
            ctrEarthChanta=0; ctrEarthTanyao=0;                    //~vabdI~
            if (ctrPairStatus!=0)                                  //~1122I~
            {                                                      //~1122I~
//              boolean swTanyao=true,swAllSeq=true,swAllSame=true;//~1122R~//~vafmR~
                boolean               swAllSeq=true,swAllSame=true;//~vafmI~
                for (int ii=0;ii<ctrPairStatus;ii++)                   //~1114I~//~1122R~
                {                                                      //~1114I~//~1122R~
		        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getPairStatus itsPairStatus["+ii+"]="+Integer.toHexString(itsPairStatus[ii]));//~vafmI~
                    if ((itsPairStatus[ii] & PS_CHANTA)!=0)               //~1114I~//~1122R~
                    {                                              //~vabdI~
                		stat|=PS_CHANTA;                           //~vafmI~
//                      swTanyao=false;                                //~1114I~//~1122R~//~vafmR~
                        ctrEarthChanta++;                          //~vabdI~
                    }                                              //~vabdI~
                    else                                           //~vabdI~
                    {                                              //~vafmI~
                		stat|=PS_TANYAO;                           //~vafmI~
                        ctrEarthTanyao++;                          //~vabdI~
                    }                                              //~vafmI~
                                                                   //~vabdI~
                    if ((itsPairStatus[ii] & PS_SEQ)==0)                  //~1114I~//~1122R~
                        swAllSeq=false;                                //~1114I~//~1122R~
                    else                                           //~1122I~
                        swAllSame=false;                          //~1122I~
                }                                                      //~1114I~//~1122R~
//              stat|=swTanyao ? PS_TANYAO : PS_CHANTA;               //~1114I~//~1122R~//~vafmR~
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
//          boolean rc=(stat & PS_CHANTA)!=0;                      //~1114I~//~vafmR~
            boolean rc=(stat & PS_CHANTA)!=0 && (stat & PS_TANYAO)==0;//~vafmI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isPairChantaAll rc="+rc);//~1114I~//~1217R~//~vafmR~
            return rc;
        }                                                          //~1114I~
        //*****************************************************************//~1217I~
        public boolean isPairChantaAllOrNoPair()                   //~1217I~
        {                                                          //~1217I~
        	int stat=getPairStatus();                              //~1217I~
//          boolean rc=ctrPairStatus==0 || (stat & PS_CHANTA)!=0;  //~1217I~//~vafmR~
            boolean rc=ctrPairStatus==0 ||                         //~vafmI~
                      ((stat & PS_CHANTA)!=0 && (stat & PS_TANYAO)==0);//~vafmI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isPairChantaAllOrNoPair rc="+rc);//~1217I~
            return rc;                                             //~1217I~
        }                                                          //~1217I~
        //*****************************************************************//~vabdI~
        //*return eath pair ctr or -1 if not chanta pair exist,-2 mixed     //~vabdI~//~vafmR~
        //*****************************************************************//~vabdI~
        public int getCtrEarthChanta()                             //~vabdI~
        {                                                          //~vabdI~
        	int stat=getPairStatus();                              //~vabdI~
            int rc=0;                                              //~vabdI~
            if (ctrPairStatus!=0)                                  //~vabdI~
				if ((stat & PS_CHANTA)!=0)                         //~vabdI~
                {                                                  //~vafmI~
                	rc=ctrEarthChanta;                             //~vabdI~
                    if ((stat & PS_TANYAO)!=0)   //both exist      //~vafmI~
                    	rc=-2;                                     //~vafmI~
                }                                                  //~vafmI~
                else                                               //~vabdI~
                	rc=-1;                                         //~vabdI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getCtrEarthChanta stat=0x"+Integer.toHexString(stat)+",rc="+rc);//~vabdI~//~vafmR~
            return rc;                                             //~vabdI~
        }                                                          //~vabdI~
        //*****************************************************************//~1114I~
        public boolean isPairTanyaoAll()                                      //~1114I~//~1118R~
        {                                                          //~1114I~
        	int stat=getPairStatus();                              //~1114I~
//          boolean rc=(stat & PS_CHANTA)==0;                      //~1114I~//~vafmR~
            boolean rc=(stat & PS_CHANTA)==0 && (stat & PS_TANYAO)!=0;//~vafmI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.isPairTanyaoAll rc="+rc);//~1114I~//~1217R~
            return rc;
        }                                                          //~1114I~
        //*****************************************************************//~1217I~
        public boolean isPairTanyaoAllOrNoPair()                   //~1217R~
        {                                                          //~1217I~
        	int stat=getPairStatus();                              //~1217I~
//          boolean rc=ctrPairStatus==0 || (stat & PS_CHANTA)==0;  //~1217I~//~vafmR~
            boolean rc=ctrPairStatus==0 ||                         //~vafmI~
                      ((stat & PS_CHANTA)==0 && (stat & PS_TANYAO)!=0);//~vafmI~
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
        //*****************************************************************//~vaiqI~
        public int[] getHandStatisticSaved()                       //~vaiqI~
        {                                                          //~vaiqI~
        	if (Dump.Y) Dump.println("RoundStat.RSPlayer.getHandStatisticSaved itsHandStatus="+Arrays.toString(itsHandStatus));//~vaiqI~
            return itsHandStatus;                                  //~vaiqI~
        }                                                          //~vaiqI~
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
//      public void setShantenYou()                                 //~va70I~//~vaadR~
        private void setShantenYou() //No User                     //~vaadI~
        {                                                          //~va70I~
	        if (!isReachCalled())                                  //~va70I~
            {                                                      //~va70I~
//                TileData[] tdsHand=AG.aPlayers.getHands(PLAYER_YOU);//~va70R~//~va89R~
//                ctrHand=tdsHand.length;                            //~va70R~//~va89R~
//                RAUtils.countTile(tdsHand,itsHand);                //~va70R~//~va89R~
//                currentShanten=getShanten(itsHand,ctrHand);        //~va70R~//~va89R~
                currentShanten=getShantenYou();                    //~va89I~
            }                                                      //~va70I~
            else                                                   //~vaaPI~
            {                                                      //~vaaPI~
        		if (!isSetShantenAtReach())                        //~vaaPI~
                {                                                  //~vaaPI~
	                currentShanten=getShantenYou();                //~vaaPI~
        			setSetShantenAtReach();                        //~vaaPI~
                }                                                  //~vaaPI~
        	}                                                      //~vaaPI~
            if (Dump.Y) Dump.println("RoundStat setShantenYou eswn="+eswn+",player="+player+",currentShanten="+currentShanten);//~va70I~
        }                                                          //~va70I~
        //***********************************************************************//~vaadI~
	    //*set shanten of human player PlayAloneNotify/PlayMatchNotify mode//~vaadI~
	    //*(if !PlayMatchMode not need to set currentShanten currentry)//~vajdI~
        //***********************************************************************//~vaadI~
//      public int setCurrentShantenYou()                          //~vaadR~//~vajdR~
        private int setCurrentShantenYou()                         //~vajdI~
        {                                                          //~vaadI~
        	if (player==PLAYER_YOU)                                //~vaadI~
	        	if (!isReachCalled())                              //~vaadI~
			    	setCurrentShanten();  //using itsHand          //~vaadR~
                else                                               //~vaaPI~
                {                                                  //~vaaPI~
	        		if (!isSetShantenAtReach())                    //~vaaPI~
    	            {                                              //~vaaPI~
				    	setCurrentShanten();  //using itsHand      //~vaaPI~
        				setSetShantenAtReach();                    //~vaaPI~
                	}                                              //~vaaPI~
                }                                                  //~vaaPI~
            if (Dump.Y) Dump.println("RoundStat setCurrentShantenYou eswn="+eswn+",player="+player+",currentShanten="+currentShanten);//~vaadR~
            return currentShanten;                                 //~vaadI~
        }                                                          //~vaadI~
        //***********************************************************************//~va89I~
//      public int getShantenYou()                                 //~va89R~//~vaadR~
        private int getShantenYou()                                //~vaadI~
        {                                                          //~va89I~
            TileData[] tdsHand=AG.aPlayers.getHands(PLAYER_YOU);   //~va89R~
            ctrHand=tdsHand.length;                                //~va89R~
            RAUtils.countTile(tdsHand,itsHand);                    //~va89R~
            int shanten=getShanten(itsHand,ctrHand);               //~va89R~
            if (Dump.Y) Dump.println("RoundStat.getShantenYou eswn="+eswn+",player="+player+",ctrHand="+ctrHand+",shanten="+shanten);//~va89R~//~va8jR~//~vafmR~
            return shanten;                                        //~va89I~
        }                                                          //~va89I~
        //***********************************************************************//~va8jI~
//      public int[] getItsHandYou()                               //~va8jI~//~vaadR~
        private int[] getItsHandYou() //No User                    //~vaadI~
        {                                                          //~va8jI~
            TileData[] tdsHand=AG.aPlayers.getHands(PLAYER_YOU);   //~va8jI~
            ctrHand=tdsHand.length;                                //~va8jI~
            RAUtils.countTile(tdsHand,itsHand);                    //~va8jI~
            if (Dump.Y) Dump.println("RoundStat.getItsHandYou eswn="+eswn+",player="+player+",ctrHand="+ctrHand+",itsHand="+Utils.toString(itsHand,9));//~va8jI~//~vafmR~
            return itsHand;                                        //~va8jI~
        }                                                          //~va8jI~
        //*********************************************************//~vafcI~
        public int getIntentCalled()                               //~vafcI~
        {                                                          //~vafcI~
            int rc=callStatusIntent;                               //~vafcI~
            if (Dump.Y) Dump.println("RoundStat.getIntentCalled RSP eswn="+eswn+",rc="+Integer.toHexString(rc));//~vafcI~
            return rc;                                             //~vafcI~
        }                                                          //~vafcI~
        //*********************************************************//~vaj7I~
        //*from takeOne                                            //~vaj7I~
        //*set win status after reach at Taken                     //~vaj7R~
        //*for Human on server and You on client                   //~vaj7I~
        //*********************************************************//~vaj7I~
        private void setWinStatusAfterReachTaken()                 //~vaj7R~
        {                                                          //~vaj7I~
            if (Dump.Y) Dump.println("RoundStat.RSP.setWinStatusAfterReachTaken swServer="+swServer+",eswn="+eswn+",player="+player+",swRobot="+swRobot);//~vaj7R~
  		  	if (swServer && !swRobot)                              //~vaj7R~
            {                                                      //~vaj7I~
                if (isReachCalled())                               //~vaj7I~
        			if (!isReachStatusErrFuriten())	//not yet detected furiten//~vaj7I~
                    {                                              //~vaj7R~
                        if (getShanten()==-1)                      //~vaj7R~
                            setWinTileAfterReach(true/*swTake*/);//set WINTILE//~vaj7R~
                    }                                              //~vaj7R~
            }                                                      //~vaj7I~
            if (Dump.Y) Dump.println("RoundStat.RSP.setWinStatusAfterReachTaken eswn="+eswn);//~vaj7R~
        }                                                          //~vaj7I~
        //*********************************************************//~vaj7I~
        //*set winstatus after reach at other Discarded            //~vaj7I~
        //*********************************************************//~vaj7I~
        private void setWinStatusAfterReachDiscarded(int Ppos)     //~vaj7R~
        {                                                          //~vaj7I~
            if (Dump.Y) Dump.println("RoundStat.RSP.setWinStatusAfterReachDiscarded eswn="+eswn+",player="+player+",swRobot="+swRobot+",Ppos="+Ppos+",itsHand="+Utils.toString(itsHand,9));//~vaj7R~//~vajdR~
            if (Dump.Y) Dump.println("RoundStat.RSP.setWinStatusAfterReachDiscarded callStatus="+Integer.toHexString(callStatus));//~vaj7I~
  		  	if (swServer && !swRobot)                              //~vaj7R~
            {                                                      //~vaj7I~
                if (isReachCalled())                               //~vaj7I~
        			if (!isReachStatusErrFuriten())	//not yet detected furiten//~vaj7I~
                    {                                              //~vaj7R~
                        itsHand[Ppos]++;                           //~vaj7R~
                        ctrHand++;                                 //~vaj7R~
                        if (getShanten()==-1)                      //~vaj7R~
                            setWinTileAfterReach(false/*swTake*/);  //set WINTILE//~vaj7R~
                        itsHand[Ppos]--;                           //~vaj7R~
                        ctrHand--;                                 //~vaj7R~
                    }                                              //~vaj7R~
            }                                                      //~vaj7I~
            if (Dump.Y) Dump.println("RoundStat.RSP.setWinStatusAfterReachDiscarded exit eswn="+eswn);//~vaj7R~//~vajbR~
        }                                                          //~vaj7I~
	}//class RSPlayer                                              //~va60M~
}//class RoundStat                                                 //~dataR~//~@@@@R~//~v@@@R~//~va60R~
