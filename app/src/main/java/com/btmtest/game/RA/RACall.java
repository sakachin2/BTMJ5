//*CID://+vac1R~: update#= 351;                                    //~vac1R~//~vabfR~//~vac1R~
//**********************************************************************//~v101I~
//2021/08/05 vac1 call pon for honor tile at near final round if top, but if only not in east wind//~vac1I~
//2021/08/01 vabt Pon for 1st discard of honor tile if GrillBird option is on in East only round.//~vabtI~
//2021/08/01 vabr avoid other color chii when intent is samecolor  //~vabrI~
//2021/07/29 vabp (Bug of vaad) at human take, not notified Ron if after reach called//~vabpI~
//2021/07/29 vabn bypass notify for KanTaken                       //~vabnI~
//2021/07/28 vabf Robot call Honer tile at first if top at near final game//~vabfI~
//2021/07/25 vab4 call Pon if dora discarded                       //~vab4I~
//2021/07/25 vab3 selectrMeld;select if possibility of dor even not red5 exist//~vab3I~
//2021/07/19 vaaU chankan was not notified                         //~vaaUI~
//2021/07/17 vaaN (Bug of Vaa2)ron button was updated even err of constraint at take//~vaaNI~
//2021/07/15 vaaL do not discard Dora at Chii. selectmeld checks dora considering tanyao/chanta//~vaaLI~
//2021/07/14 vaaJ Call Pon/Chii when shanten up to 1 and penchan/kanchan//~vaaJI~
//2021/07/13 vaaH Call Pon/Chii when tenpai with earth             //~vaaHI~
//2021/07/09 vaax call pon early when intent=~ALLSAME              //~vaaxI~
//2021/07/06 vaat Robot avoids Kan when reach called               //~vaatI~
//2021/07/06 vaas (Bug)selectMeld position chk ignored number boundary//~vaasI~
//2021/07/06 vaar Additional to vaai(call pon/chii if become shanten 0), chk wintile ctr//~vaasI~
//2021/07/03 vaai strengthen robot; call pon/chii if become shanten 0 with a Yaku+dora>=2//~vaaiI~
//2021/07/01 vaae (Bug)SameColor intent should chk tile type       //~vaaeI~
//2021/06/30 vaad (Bug)PlayAlone mode,did not notify kan if kan not in deal. maintaine ItsHand also for MatchNotify mode//~vaadI~//~vaaNR~
//2021/06/28 vaa3 (Bug)PlauAlone mode; 13/14 broke failes by not-win-form.//~vaa3I~
//2021/06/27 vaa2 Notify mode of Match                             //~vaa2I~
//2021/06/27 vaa1 (Bug)missing issue Notify for Kan at Take at PlayAloneNotify mode.//~vaa1I~
//2021/05/04 va8z not avoid call when INTENT_GIVEUP_WEAK(set by remaining tile) is on refered as caution level//~va8zI~
//2021/04/26 va8s (Bug)At Cii, selectMeld fail by isShantenUpCall if A notUp case exist//~va8sI~
//2021/04/26 va8p (Bug)Samecolor did not call chii when intent:same color//~va8pI~
//2021/04/26 va8o bug of v8h, getCtrValueWiordDup return ctr of pair//~va8oI~
//2021/04/20 va8j KataAgari chk+furiten chk for also Human Take/Ron in PlayAloneNotifyMode//~va8jI~
//2021/04/18 va8h avoid callPon when 2han constraint               //~va8hI~
//2021/04/14 va89 (Bug) when reach done, ron by take was not notified//~va89I~
//2021/04/14 va88 chk constrint for TakeRon in notify mode(avoid dump)//~va88I~
//2021/04/13 va84 try Robot also ron by 13/14 NoPair               //~va84I~
//2021/04/12 va83 (Bug)13/14 NoPair was not notified(Decided to Robot will not Ron by 13/14 NoPair)//~va83I~
//2021/04/11 va81 (Bug)In notify mode,btn is not highlighen when take//~va81I~//~va83R~
//2021/03/31 va75 Autotake when Notify mode(Chii or Take)          //~va75I~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//**********************************************************************//~1107I~
package com.btmtest.game.RA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.Players;
import com.btmtest.game.Robot;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.TestOption.*;
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UAD2Touch.*;
import static com.btmtest.game.gv.Pieces.*;

//********************************************************************************************//~v@@5R~
//determine to call Pon,Chii,Kan                                   //~1117R~
//********************************************************************************************//~v@@5R~
public class RACall                                               //~v@@@R~//~va60R~//~1111R~//~1117R~
{                                                                  //~0914I~
	private RoundStat RS;//~va60I~                                 //~1126R~
	private RADiscard RAD;
	private RADSmart RADS;//~1126I~
    private int[] itsWin1=new int[CTR_TILETYPE];  //chk change win list by kan at reach//~1117I~
    private int[] itsWin2=new int[CTR_TILETYPE];  //evaluate wait expecting value//~1117I~
    private TileData[] tdsPairKan;                                     //~1124I~
    private TileData[] tdsHand;                                    //~1124I~
    private int scorePlus;                                         //~vabfI~
//  private boolean swSkipNotifyKanTaken=true;                     //~vabnR~
//*************************                                        //~v@@@I~
	public RACall()                                               //~v@@@R~//~va60R~//~1111R~//~1117R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("RACall.Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~va60R~//~1111R~//~1117R~
        AG.aRACall=this;                                          //~v@@@I~//~va60R~//~1111R~//~1117R~
        init();                                                    //~va60I~
    }                                                              //~0914I~//~v@@@R~
    //*********************************************************    //~va60I~
    private void  init()                                           //~va60I~
    {                                                              //~va60I~
    	RS=AG.aRoundStat;                                          //~1111I~
    	RAD=AG.aRADiscard;
    	RADS=AG.aRADSmart;//~1126I~
    	scorePlus= RuleSetting.getDebt();                           //~vabfI~
        if (Dump.Y) Dump.println("RACall.init");                   //~1117R~//~vabfR~
    }                                                              //~va60I~
    //*********************************************************    //~1116I~
    //*from RDDSmart to discard  at taken                          //~1119I~
    //*return posKan                                               //~1124I~
    //*********************************************************    //~1119I~
    public int callKanTaken(int Pplayer,int PeswnPlayer,int PmyShanten,boolean PswReach,TileData PtdTaken,int Ppos,int[] PitsHand,int PctrHand)//~1117R~//~1124R~
    {                                                              //~1116I~
        if (Dump.Y) Dump.println("RACall.callKanTaken Pmyshanten="+PmyShanten+",swReach="+PswReach+",player="+Pplayer+",posTaken="+Ppos+",tdTaken="+PtdTaken.toString()+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1124R~//~1305R~
        tdsPairKan=null;                                           //~1124I~
        if (AG.aTiles.addCtrKan(false/*swUpdate*/)<0)	//err when ctrKan>4//~1124I~
        	return -1;	//max 4 kan                               //~1124I~//~1305R~
        tdsHand=AG.aPlayers.getHands(Pplayer);                //~va60I~//~1124I~
        int posKan=-1;                                             //~1117R~
        if (PswReach)                                              //~1117I~
        {                                                          //~1117I~
		    if (callKanTakenUnderReach(PeswnPlayer,Ppos,PitsHand,PctrHand))      //~1117I~//~1124R~
            	posKan=Ppos;                                       //~1117R~
        }                                                          //~1117I~
        else                                                       //~1117I~
		    posKan=callKanTaken(PeswnPlayer,PmyShanten,PitsHand,PctrHand,PtdTaken);	//consider to call kan      //~1117R~//~1119R~//~1124R~//~1219R~
        if (posKan>=0)                                     //~1117R~//~1124R~
        {                                                          //~1124I~
        	issueKan(KAN_TAKEN,Pplayer,PeswnPlayer,tdsPairKan);	                   //~1117R~//~1118R~//~1124R~
        }                                                          //~1124I~
        else                                                       //~1124I~
        if (!PswReach)                                             //~1124I~
        {                                                          //~1124I~
    		posKan=callKanAdd(Pplayer,PeswnPlayer,PmyShanten,PitsHand,PctrHand);//~1124R~
        	if (posKan>=0)                                             //~1117I~//~1124I~
        		issueKan(KAN_ADD,Pplayer,PeswnPlayer,tdsPairKan);                     //~1117I~//~1118R~//~1119R~//~1124R~
        }                                                          //~1124I~
        tdsHand=null;	//for gc                                   //~1124I~
        if (Dump.Y) Dump.println("RACall.callKanTaken posKan="+posKan);//~1117R~//~1124R~
        return posKan;                                             //~1117R~//~1124R~
    }                                                              //~1116I~
    //*********************************************************    //~1117I~
    //*chk change of winning list by ankan after reach             //~1117I~
    //*********************************************************    //~1117I~
    private boolean callKanTakenUnderReach(int PeswnPlayer,int Ppos,int[] PitsHand,int PctrHand)//~1117I~//~1119R~//~1124R~
    {                                                              //~1117I~
        if (Dump.Y) Dump.println("RACall.callKanTakenUnderReach pos="+Ppos+",ctr="+PitsHand[Ppos]);//~1117I~
        boolean rc=false;                                          //~1117I~
        if (PitsHand[Ppos]==PIECE_DUPCTR && RS.swKanAfterReach)//~1117I~//~1118R~
        {                                                          //~1117I~
        	rc=true;                                               //~1117I~
            int posKan=-1;                                         //~1124I~
            PitsHand[Ppos]--;                                      //~1126I~
	        int ctrWin1=AG.aRAReach.getItsWinList(PitsHand,PctrHand-1,itsWin1);	//original waiting ;ist//~1117R~//~1126R~
            PitsHand[Ppos]++;                                      //~1126I~
            PitsHand[Ppos]-=PIECE_DUPCTR;                          //~1117I~
	        int ctrWin2=AG.aRAReach.getItsWinList(PitsHand,PctrHand-PIECE_DUPCTR,itsWin2);	//original waiting ;ist//~1117R~
            PitsHand[Ppos]+=PIECE_DUPCTR;                          //~1119I~
            if (ctrWin1!=ctrWin2)                                  //~1117I~
            	rc=false;                                           //~1117I~
            else                                                   //~1117I~
            {                                                      //~1117I~
                for (int ii=0;ii<ctrWin2;ii++)                     //~1117R~
                {                                                  //~1117R~
                    boolean swFound=false;                         //~1117I~
                    for (int jj=0;jj<ctrWin1;jj++)                 //~1117I~
                    {                                              //~1117I~
                        if (itsWin1[jj]==itsWin2[ii])                 //~1117I~
                        {                                          //~1117I~
                            swFound=true;                          //~1117I~
                            break;                                 //~1117I~
                        }                                          //~1117I~
                    }                                              //~1117I~
                    if (!swFound)                                  //~1117I~
                    {                                              //~1117I~
                    	rc=false;                                  //~1117I~
                        break;                                     //~1117I~
                    }                                              //~1117I~
                }                                                  //~1117I~
            }                                                      //~1117I~
        }                                                          //~1117I~
        if (rc)                                                    //~1124I~
		    tdsPairKan=makeKanTaken(PeswnPlayer,Ppos);             //~1124I~
        if (Dump.Y) Dump.println("RACall.callKanTakenUnderReach rc="+rc);//~1117I~
        return rc;                                              //~1117I~
    }                                                              //~1117I~
    //*********************************************************    //~1117I~
    //*search quad and chk shanten not down                              //~1117I~//~1119R~
    //*do kan always for word tile                                 //~1119I~
    //*********************************************************    //~1117I~
    private int callKanTaken(int PeswnPlayer,int PmyShanten,int[] PitsHand,int PctrHand,TileData PtdTaken)//~1219R~
    {                                                              //~1117I~
    	int shanten,posKan=-1;                                     //~1117I~
        //****************                                         //~1117I~
        if (Dump.Y) Dump.println("RACall.callKanTaken PmyShanten="+PmyShanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1117R~//~1305R~
//      if (!isTimeToCall(PeswnPlayer,GCM_KAN,PtdTaken,PmyShanten))                    //~1206I~//~1219R~//~1222R~
		int player= Accounts.eswnToPlayer(PeswnPlayer);             //~1222I~
        if (!isCallable(GCM_KAN,player,PeswnPlayer,PeswnPlayer,PtdTaken))//~1222I~
        	return posKan;                     //-1                //~1206I~
        for (int ii=OFFS_WORDTILE;ii<CTR_TILETYPE;ii++)               //~1117I~
        {                                                          //~1117I~
        	if (PitsHand[ii]==PIECE_DUPCTR)                        //~1120R~
            {                                                      //~1117I~
    			if (!isKanFixed(PeswnPlayer,ii))                   //~1222I~
                	continue;                                      //~1222I~
		        if (chkCtrDiscardable(PeswnPlayer,PitsHand,ii,-PIECE_DUPCTR))	//chk small handctr for openreach and pao//~1128I~
                {                                                  //~1128I~
            		posKan=ii;      //if multiple take first           //~1117I~//~1124R~//~1128R~//~1222R~
                	break;                                             //~1117I~//~1128R~//~1222R~
                }                                                  //~1128I~
            }                                                      //~1117I~
		}                                                          //~1117I~
        if (posKan<0)	//no word Kan                              //~1117I~
        {                                                          //~1117I~
	        int minShanten=MAX_SHANTEN;                            //~1117I~
	        int posMinShanten=-1;                                  //~1117I~
	        for (int ii=0;ii<OFFS_WORDTILE;ii++)      //search min shanten//~1117I~
    	    {                                                      //~1117I~
	        	if (PitsHand[ii]!=PIECE_DUPCTR) //~1117I~
                	continue;                                      //~1117I~
                if ((TestOption.option2 & TO2_CALL1ST)!=0)                   //~va75I~//~va81R~
                {                                                  //~va81R~
                    if (Dump.Y) Dump.println("RACall.callKanTaken call Kan by TO2_CALL1ST posKan="+ii);//~va81R~
                    posMinShanten=ii;                              //~va81R~
                    break;                                         //~va81R~
                }                                                  //~va81R~
    			if (!isKanFixed(PeswnPlayer,ii))                   //~1222I~
                	continue;                                      //~1222I~
//  			shanten=chkShanten(KAN_TAKEN,ii,PitsHand,PctrHand);//~1117I~//~1119R~
//  			shanten=RAUtils.getShantenAdd(PitsHand,PctrHand,ii,-PIECE_DUPCTR);//~1119I~//~1128R~
    			shanten=chkShantenAddCall(PeswnPlayer,PitsHand,PctrHand,ii,-PIECE_DUPCTR);//~1128I~
                if (shanten==0 || shanten<PmyShanten)                           //~1117I~//~1131R~
                {                                                  //~1117I~
                    if (shanten<minShanten) //only if advance shanten/~1117I~//~1119R~//~1124R~
                    {                                              //~1117I~
                        minShanten=shanten;                        //~1117I~
                        posMinShanten=ii;                          //~1117I~
                    }                                              //~1117I~
                }                                                  //~1117I~
            }                                                      //~1117I~
            posKan=posMinShanten;                                  //~1117I~
        }                                                          //~1117I~
        if (posKan>=0)                                             //~1124I~
		    tdsPairKan=makeKanTaken(PeswnPlayer,posKan);           //~1124I~
        if (Dump.Y) Dump.println("RACall.callKanTaken posKan="+posKan);//~1117R~
        return posKan;                                             //~1117R~
    }                                                              //~1117I~
    //*********************************************************    //~1117I~
    //*for smartRobot                                              //~va89I~
    //*do kan add to pon when shanten not down                     //~1119I~
    //*********************************************************    //~1119I~
    private int callKanAdd(int Pplayer,int PeswnPlayer,int PmyShanten,int[] PitsHand,int PctrHand)//~1117R~//~1124R~
    {                                                              //~1117I~
    	int shanten,posKan=-1;                                     //~1117I~
        int minShanten=MAX_SHANTEN;                                //~1117I~
        int posMinShanten=-1;                                      //~1117I~
        int idxPair=-1,minIdxPair=-1,ctrPair=0;                    //~1124I~
        //*********************************                        //~1117I~
        if (Dump.Y) Dump.println("RACall.callKanAdd player="+Pplayer+",shanten="+PmyShanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1117R~
        TileData[][] tdss=AG.aPlayers.getEarth(Pplayer);           //~1117I~
        for (TileData[] tds:tdss)  //multiple kan candidate me be  //~1131R~
        {                                                          //~1117I~
	        if (Dump.Y) Dump.println("RACall.callKanAdd tds="+TileData.toString(tds));//~1131I~
        	if (tds==null)                                         //~1117I~
            	break;                                             //~1117I~
            if ((tds[0].flag & TDF_PON)!=0)                        //~1117R~
            {                                                      //~1117I~
            	int pos=RAUtils.getPosTile(tds[0]);              //~1117I~//~1119R~
                if (PitsHand[pos]!=0)                   //~1117I~  //~1124R~
                {                                                  //~1117I~
                	if (pos>=OFFS_WORDTILE)                            //~1117I~
                    {                                              //~1117I~
				        if (chkCtrDiscardable(PeswnPlayer,PitsHand,pos,-1))	//chk small handctr for openreach and pao//~1128I~
                        {                                          //~1128I~
                    		posKan=pos; //select 1st when multiple kan                               //~1117I~//~1124R~//~1128R~
                        	idxPair=ctrPair;                           //~1124I~//~1128R~
                        	break;                                     //~1117I~//~1128R~
                        }                                          //~1128I~
                    }                                              //~1117I~
                    else                                           //~1117I~
                    {                                              //~1117I~
//                      shanten=chkShanten(KAN_ADD,pos,PitsHand,PctrHand);//~1117I~//~1119R~
//  					shanten=RAUtils.getShantenAdd(PitsHand,PctrHand,pos,-1);//~1119I~//~1126R~
    					shanten=chkShantenAddCall(PeswnPlayer,PitsHand,PctrHand,pos,-1);//~1126I~//~1128R~
                        if (shanten==0 || shanten<PmyShanten)    //kan only if shanten up               //~1117I~//~1124R~//~1131R~
                        {                                          //~1117I~
                            if (shanten<minShanten)                //~1117I~//~1119R~//~1124R~
                            {                                      //~1117I~
                                minShanten=shanten;                //~1117I~
                                posMinShanten=pos;                  //~1117I~
                                minIdxPair=ctrPair;                //~1124I~
                            }                                      //~1117I~
                        }                                          //~1117I~
                    }                                              //~1117I~
                }                                                  //~1117I~
            }                                                      //~1117I~
            ctrPair++;                                             //~1124I~
        }                                                          //~1117I~
        if (posKan<OFFS_WORDTILE)                                      //~1117I~
        {                                                          //~1124I~
        	posKan=posMinShanten;                                  //~1117I~
            idxPair=minIdxPair;                                    //~1124I~
        }                                                          //~1124I~
        if (posKan>=0)                                             //~1124I~
        {                                                          //~1124I~
	        tdsPairKan=makeKanAdd(PeswnPlayer,tdss[idxPair],posKan);//~1124I~
        }                                                          //~1124I~
        if (Dump.Y) Dump.println("RACall.callKanAdd posKan="+posKan+",idxPair="+idxPair);//~1117R~//~1124R~
        return posKan;                                                 //~1117I~//~1124R~
    }                                                              //~1117I~
    //*********************************************************    //~1118I~
    //*from RoundStat                                              //~1118I~
    //*chk chankan(addKan ron and Kukushi ankan ron)               //~1126I~
    //*********************************************************    //~1118I~
    public boolean otherKan(int Pplayer/*kan caller*/,int PplayerEswn,int PkanType,TileData PtdKan)//~1118I~//~1126R~//~1201R~
    {                                                              //~1118I~
        if (Dump.Y) Dump.println("RACall.otherKan Pplayer="+Pplayer+",eswn="+PplayerEswn+",kanType="+PkanType);//~1201I~
        boolean rc=false;
        for (int ii=0;ii<PLAYERS;ii++)                             //~1118I~
        {                                                          //~1118I~
        	if (ii!=PplayerEswn)	//not caller                       //~1118I~
            	if (RS.isRobot(ii))                                //~1118I~
                {                                                  //~vaaUI~
		        	if (calledKan(ii,Pplayer,PplayerEswn,PkanType,PtdKan))
		        		rc=true;//~1118R~                          //~1201R~
                }                                                  //~vaaUI~
//                else  //not this timing but at Kan called for human//~vaaUR~
//                {                                                //~vaaUR~
//                    if (calledKanNotify(ii,Pplayer,PplayerEswn,PkanType,PtdKan))//~vaaUR~
//                        rc=true;                                 //~vaaUR~
//                }                                                //~vaaUR~
        }                                                          //~1118I~
        if (Dump.Y) Dump.println("RACall.otherKan rc="+rc);        //~1201I~
        return rc;
    }                                                              //~1118I~
    //*********************************************************    //~vaaUI~
    public boolean otherKanClient(int Pplayer/*kan caller*/,int PplayerEswn,int PkanType,TileData PtdKan)//~vaaUI~
    {                                                              //~vaaUI~
        if (Dump.Y) Dump.println("RACall.otherKanClient Pplayer="+Pplayer+",eswn="+PplayerEswn+",kanType="+PkanType);//~vaaUI~
        boolean rc;                                                //~vaaUR~
        int yourEswn=AG.aAccounts.getCurrentEswn();	//PLAYER_YOU       //~vaaUR~
		rc=calledKanNotify(yourEswn,Pplayer,PplayerEswn,PkanType,PtdKan);//~vaaUR~
        if (Dump.Y) Dump.println("RACall.otherKanClient rc="+rc);  //~vaaUI~
        return rc;                                                 //~vaaUI~
    }                                                              //~vaaUI~
    //*********************************************************    //~1119I~
    //*for Robot:PeswnOther in thinkRobot mode                      //~va89I~//~vaaUR~
    //*13orphan may can ron for ankan                              //~1119I~
    //*and chk chankan                                             //~1119I~
    //*********************************************************    //~1119I~
    private boolean calledKan(int PeswnOther,int PplayerCalled,int PeswnCalled,int PkanType,TileData PtdKan)//~1118R~//~1126R~//~1201R~
    {
        boolean rc=false;//~1118I~
    	int shanten=RS.getCurrentShanten(PeswnOther);                      //~1118I~//~1126R~
        if (Dump.Y) Dump.println("RACall.calledKan eswnOther="+PeswnOther+",playerCalled="+PplayerCalled+",playerEswn="+PeswnCalled+",shanten="+shanten+",kanType="+PkanType+",tdKan="+TileData.toString(PtdKan));//~1118R~//~1126R~//~1201R~
        if (shanten!=0)     //tenpai                                      //~1118I~//~1201R~
        	return false;                                                //~1118I~
        if (PkanType==KAN_TAKEN)  //chk kokusi ron             //~1118R~//~1201R~
        {                                                          //~1118I~
        	if (RS.swAnkanRon)  //chk kokusi ron                   //~1118I~
            {                                                      //~1118I~
            	rc=AG.aRARon.callRonChankan(KAN_TAKEN,PeswnOther,PtdKan,PplayerCalled,PeswnCalled);//may issue Ron//~1118R~//~1119R~//~1120R~//~1126R~//~1201R~
            }                                                      //~1118I~
        }                                                          //~1118I~
        else                                                       //~1118I~
        if (PkanType==KAN_ADD)    //chk chankan ron            //~1118I~//~1201R~
        {                                                          //~1118I~
            rc=AG.aRARon.callRonChankan(KAN_ADD,PeswnOther,PtdKan,PplayerCalled,PeswnCalled);//may issue Ron//~1118R~//~1119R~//~1120R~//~1126R~//~1201R~
        }                                                          //~1118I~
        if (Dump.Y) Dump.println("RACall.calledKan rc="+rc);       //~1201I~
        return rc;                                                 //~1201R~
    }                                                              //~1118I~
    //*********************************************************    //~vaaUI~
    //*Server and Client                                           //~vaaUI~
    //*from RoundStat.calledKan()<--UAKan.takeKan()                //~vaaUI~
    //*********************************************************    //~vaaUI~
    public boolean calledKanNotify(int PeswnOther,int PplayerCalled,int PeswnCalled,int PkanType,TileData PtdKan)//~vaaUR~
    {                                                              //~vaaUI~
        boolean rc=false;                                          //~vaaUI~
    	int shanten=RS.getCurrentShanten(PeswnOther);              //~vaaUI~
        if (Dump.Y) Dump.println("RACall.calledKanNotify eswnOther="+PeswnOther+",playerCalled="+PplayerCalled+",playerEswn="+PeswnCalled+",shanten="+shanten+",kanType="+PkanType+",tdKan="+TileData.toString(PtdKan));//~vaaUI~
        if (shanten!=0)     //tenpai                               //~vaaUI~
        	return false;                                          //~vaaUI~
        if (PkanType==KAN_TAKEN)  //chk kokusi ron                 //~vaaUI~
        {                                                          //~vaaUI~
        	if (RS.swAnkanRon)  //chk kokusi ron                   //~vaaUI~
            {                                                      //~vaaUI~
            	rc=AG.aRARon.callRonChankanNotify(KAN_TAKEN,PeswnOther,PtdKan,PplayerCalled,PeswnCalled);//may issue Ron//~vaaUI~
            }                                                      //~vaaUI~
        }                                                          //~vaaUI~
        else                                                       //~vaaUI~
        if (PkanType==KAN_ADD)    //chk chankan ron                //~vaaUI~
        {                                                          //~vaaUI~
            rc=AG.aRARon.callRonChankanNotify(KAN_ADD,PeswnOther,PtdKan,PplayerCalled,PeswnCalled);//may issue Ron//~vaaUI~
        }                                                          //~vaaUI~
        if (Dump.Y) Dump.println("RACall.calledKanNotify rc="+rc); //~vaaUI~
        return rc;                                                 //~vaaUI~
    }                                                              //~vaaUI~
    //***********************************************************************    //~1117I~//~1118M~//~1129R~
    //*on Server at timeoutPonKan(RS.timeoutPonKan()<--UADiscard.nextPlayerPonKan())//~vaa2R~
    //***********************************************************************    //~1118M~//~1129R~
    public void otherDiscard(int Paction,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~1118I~//~1124R~//~1128R~
    {                                                              //~1118I~
    	boolean rc=false;                                          //~1128I~
        if (Dump.Y) Dump.println("RACall.otherDiscard action="+Paction+",playerDiscard="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",td="+PtdDiscarded.toString());//~1125I~//~1128R~//~1130R~
        try                                                        //~1126I~
        {                                                          //~1126I~
            int eswn=PeswnDiscarded+1;	//from next player for headbump by robot//~1206I~
            for (int ii=0;ii<PLAYERS;ii++,eswn++)                      //~1118I~//~1126R~//~1206R~
            {                                                          //~1118I~//~1126R~
                if (eswn==PLAYERS)                                 //~1206I~
                	eswn=0;                                        //~1206I~
                if (RS.isRobot(eswn))                                //~1118I~//~1126R~//~1128R~//~1201I~//~1206R~
                {                                              //~1128I~//~1201I~
                    if (eswn==PeswnDiscarded) //discarder                //~1118I~//~1126R~//~1128R~//~1201R~//~1206R~
                    {                                              //~1201I~
                    	AG.aRADSmart.discardedSmart(eswn);	//set intent and shanten//~1201R~//~1206R~
                    }                                              //~1201I~
                    else                                           //~1201I~
                    {                                                  //~1128I~//~1201R~
                        RoundStat.RSPlayer RSP=RS.RSP[eswn];         //~1129I~//~1206R~
	                	tdsHand=AG.aPlayers.getHands(RSP.player);       //~1124I~//~1126R~//~1128I~//~1129R~
                        switch(Paction)                            //~1128I~
                        {                                          //~1128I~
                        case GCM_DISCARD:                          //~1128I~
	                        discarded(eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~1118R~//~1124R~//~1126R~//~1128R~//~1206R~
                            break;                                 //~1128I~
                        case GCM_NEXT_PLAYER_PONKAN:               //~1128I~
	                        nextPlayerPonKan(eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~1128R~//~1206R~
                            break;                                 //~1128I~
                        }                                          //~1128I~
                    }                                                      //~1124I~//~1126R~//~1128R~
                }                                                  //~1128I~
                else                                               //~va70I~
                {                                                  //~va70I~
                    if (eswn==PeswnDiscarded) //discarder          //~va70I~
                    {                                              //~va70I~
                    	;                                          //~va70I~
                    }                                              //~va70I~
                    else                                           //~va70I~
                    {                                              //~va70I~
                        switch(Paction)                            //~va70I~
                        {                                          //~va70I~
                        case GCM_DISCARD:                          //~va70I~
                            discardedPlayAloneNotify(eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~va70R~
                            break;                                 //~va70I~
                        case GCM_NEXT_PLAYER_PONKAN:               //~va70I~
//                          nextPlayerPonKanPlayAlone(eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~va70I~//~vaa2R~
                            nextPlayerPonKanHumanOnServer(eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~vaa2R~
                            break;                                 //~va70I~
                        }                                          //~va70I~
                    }                                              //~va70I~
                }                                                  //~va70I~
            }                                                          //~1118I~//~1126R~
        }                                                          //~1126I~
        catch(Exception e)                                         //~1126I~
        {                                                          //~1126I~
        	Dump.println(e,"RADiscard.otherDiscard");              //~1126I~
        }                                                          //~1126I~
        if (Dump.Y) Dump.println("RACall.otherDiscard end");       //~1126I~
        tdsHand=null;                                              //~1124I~
        if (Dump.Y) Dump.println("RACall.otherDiscard exit action="+Paction);//~1129I~
    }                                                              //~1118I~
    //*********************************************************    //~1126I~
    //*issue ron at ponkan timeout                                 //~1212I~
    //*********************************************************    //~1212I~
    private void discarded(int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~1117I~//~1118I~//~1126R~//~1128R~
    {                                                              //~1117I~//~1118M~
        if (Dump.Y) Dump.println("RACall.discarded eswnOther="+PeswnOther+",playerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",td="+PtdDiscarded.toString());              //~1128I~//~va70R~
//        int shanten=RS.getCurrentShanten(PeswnOther);                      //~1118M~//~1126R~//~1131R~
//        int playerOther=RS.RSP[PeswnOther].player;                       //~1124I~//~1126R~//~1131R~
//        if (Dump.Y) Dump.println("RACall.discarded playerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",shanten="+shanten+",tdDiscarded="+PtdDiscarded.toString());//~1117I~//~1118M~//~1131R~
//        if (shanten==0)                                           //~1118M~//~1131R~
//        {                                                          //~1118M~//~1131R~
//            if (AG.aRARon.callRonRiver(PeswnOther,PtdDiscarded))    //ron issued//~1118R~//~1120R~//~1126R~//~1131R~
//                return;                                            //~1118M~//~1131R~
//        }                                                          //~1118M~//~1131R~
//        if (RS.RSP[PeswnOther].isReachCalled()) //could not call after reach called                     //~1118I~//~1119R~//~1126R~//~1128R~
//        {                                                          //~1118I~//~1128R~
//            if (Dump.Y) Dump.println("RACall.discarded return by already reached");//~1118I~//~1128R~
//            return;                                          //~1118I~//~1128R~
//        }                                                          //~1118I~//~1128R~
//        if ((PtdDiscarded.flag & TDF_LAST)!=0)  //last could not be called//~1118I~//~1128R~
//        {                                                          //~1118I~//~1128R~
//            if (Dump.Y) Dump.println("RACall.discarded return by last tile");//~1118I~//~1128R~
//            return;                                          //~1118I~//~1128R~
//        }                                                          //~1118I~//~1128R~
//        if (!callPonKan(shanten,playerOther,PeswnOther,Pplayer,PplayerEswn,PtdDiscarded))   //may issue Pon/Kan    //~1118R~//~1119R~//~1124R~//~1125R~//~1126R~//~1128R~
//            callChii(PeswnOther,Pplayer,PplayerEswn,PtdDiscarded);    //may issue Chii   //~1118R~//~1119R~//~1125R~//~1126R~//~1128R~
    }                                                              //~1117I~//~1118M~
    //*********************************************************    //~va70I~
    //*notify Ron to PLAYER_YOU in PlayAlone mode                  //~va70I~
    //*********************************************************    //~va70I~
    private void discardedPlayAloneNotify(int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~va70R~
    {                                                              //~va70I~
        if (Dump.Y) Dump.println("RACall.discardedPlayAloneNotify swTrainingMode="+AG.swTrainingMode+",playAlonNotify="+AG.swPlayAloneNotify+",eswnOther="+PeswnOther+",playerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",td="+PtdDiscarded.toString());//~va70R~
    	if (!AG.swPlayAloneNotify)                                 //~va70I~
        	return;                                                //~va70I~
        if (Accounts.eswnToPlayer(PeswnOther)!=PLAYER_YOU)         //~va70R~
        	return;                                                //~va70I~
        if (RS.getCurrentShanten(PeswnOther)!=0)                   //~va70I~
        	return;                                                //~va70I~
        int pos=RAUtils.getPosTile(PtdDiscarded);                  //~va70I~
        int[] itsH=RS.getItsHandEswnYou(PeswnOther);               //~va8jI~
        int   ctrH=RS.RSP[PeswnOther].ctrHand;                     //~va8jI~
//  	int shantenNew=RAUtils.getShantenAdd(RS.getItsHandEswn(PeswnOther),RS.RSP[PeswnOther].ctrHand,pos,1);//~va70I~//~va8jR~
    	int shantenNew=RAUtils.getShantenAdd(itsH,ctrH,pos,1);     //~va8jI~
        if (shantenNew==-1)	//ronable                              //~va70R~
        {                                                          //~va8jI~
		    if (!AG.aRARon.callRonRiverPlayAloneNotify(PLAYER_YOU,PeswnOther,itsH,ctrH,PtdDiscarded))//~va8jI~
            {                                                      //~va8jI~
        		if (Dump.Y) Dump.println("RACall.discardedPlayAloneNotify@@@@ return by MultiWait/Furiten err");//~va8jI~
                return;                                            //~va8jI~
            }                                                      //~va8jI~
        	AG.aUARon.selectInfoPlayAloneNotify();	//highlight Ron btn and show Cancel btn//~va70R~
        }                                                          //~va8jI~
    }                                                              //~va70I~
    //************************************************************************//~vaa2I~
    //*from UADiscard.discard()                                    //~vaa2I~
    //*notify Ron to PLAYER_YOU for other Discarded in PlayMatchNotify mode//~vaa2I~
    //************************************************************************//~vaa2I~
    public void discardedPlayMatchNotify(int Paction,int PplayerDiscarded,TileData PtdDiscarded)//~vaa2I~
    {                                                              //~vaa2I~
        int eswn=Accounts.playerToEswn(PLAYER_YOU);                //~vaa2R~
        if (Dump.Y) Dump.println("RACall.discardedPlayMatchNotify action="+Paction+",eswnOther="+eswn+",playerDiscarded="+PplayerDiscarded+",tdDiscarded="+PtdDiscarded.toString());//~vaa2I~
//  	if (!AG.swPlayAloneNotify)                                 //~vaa2I~
//      	return;                                                //~vaa2I~
//      if (Accounts.eswnToPlayer(PeswnOther)!=PLAYER_YOU)         //~vaa2I~
//      	return;                                                //~vaa2I~
        if (RS.getCurrentShanten(eswn)!=0)	//shanten at discard for human player//~vaa2I~
          	return;                                                //~vaa2I~
        int pos=RAUtils.getPosTile(PtdDiscarded);                  //~vaa2I~
        int[] itsH=RS.getItsHandEswnYou(eswn);                     //~vaa2R~
        int   ctrH=RS.RSP[eswn].ctrHand;                           //~vaa2R~
    	int shantenNew=RAUtils.getShantenAdd(itsH,ctrH,pos,1);     //~vaa2I~
        if (shantenNew==-1)	//ronable                              //~vaa2I~
        {                                                          //~vaa2I~
		    if (!AG.aRARon.callRonRiverPlayAloneNotify(PLAYER_YOU,eswn,itsH,ctrH,PtdDiscarded))//~vaa2I~
            {                                                      //~vaa2I~
        		if (Dump.Y) Dump.println("RACall.discardedPlayMatchNotify@@@@ return by MultiWait/Furiten err");//~vaa2I~
                return;                                            //~vaa2I~
            }                                                      //~vaa2I~
        	AG.aUARon.selectInfoPlayMatchNotify(PtdDiscarded);	//highlight Ron btn and show Cancel btn//~vaa2R~
        }                                                          //~vaa2I~
    }                                                              //~vaa2I~
    //*********************************************************    //~va81I~
    //*from RS.takeOne for Human player at PlayeAloneNotify mode   //~va81I~
    //*itsHand already includes taken                              //~vaadI~
    //*highligten Draw btn                                         //~va81I~
    //*********************************************************    //~va81I~
    public  void takeOnePlayAloneNotify(int Ppos,TileData PtdTaken)//~va81R~
    {                                                              //~va81I~
    	int eswn=Accounts.getCurrentEswn();                        //~va81I~
        boolean swRonNoPair=false;                                 //~va83I~
        if (Dump.Y) Dump.println("RACall.takeOnePlayAloneNotify swTrainingMode="+AG.swTrainingMode+",playAlonNotify="+AG.swPlayAloneNotify+",eswn="+eswn+",tdTake="+PtdTaken.toString());//~va81I~
        if (RS.getCurrentShanten(eswn)!=0)                         //~va81I~
        {                                                          //~va83I~
//      	swRonNoPair=isRonNoPair(eswn,Ppos);  //chk 13/14 NoPair//~va83I~//~vaadR~
        	swRonNoPair=isRonNoPair(eswn);  //chk 13/14 NoPair     //~vaadI~
          if (!swRonNoPair)                                        //~va83I~
          {                                                        //~vaa1I~
//  		callKanTakenPlayAloneNotify(PLAYER_YOU,eswn);          //~vaa1I~//~vaaUR~
    		callKanTakenPlayAloneNotify(PLAYER_YOU,eswn,PtdTaken); //~vaaUI~
        	return;                                                //~va81I~
          }                                                        //~vaa1I~
        }                                                          //~va83I~
        int shantenNew;                                            //~va83I~
      if (swRonNoPair)                                             //~va83I~
        shantenNew=-1;	//ronable                                  //~va83I~
      else                                                         //~va83I~
      {                                                            //~va83I~
//      RS.RSP[eswn].setShantenYou();	//set currentShanten (taken is included)/+va81R~//~va81R~//~va89R~
//      shantenNew=RS.RSP[eswn].getShantenYou();	//set currentShanten (taken is included)/+va81R~//~va89R~//~vaadR~
//      shantenNew=RS.RSP[eswn].setCurrentShantenYou();	//set currentShanten (taken is included)/+va81R~//~vaadI~//~vabpR~
        shantenNew=RS.RSP[eswn].getShanten();	//taken is included//~vabpR~
        if (shantenNew==-1)	//ronable                              //~va8jI~
        {                                                          //~va8jI~
        	int[] itsH=RS.getItsHandEswn(eswn);                       //~va8jI~
        	int   ctrH=RS.RSP[eswn].ctrHand;                       //~va8jI~
		    if (!AG.aRARon.callRonTakenPlayAloneNotify(PLAYER_YOU,eswn,itsH,ctrH,PtdTaken))//~va8jI~
            {                                                      //~va8jI~
        		if (Dump.Y) Dump.println("RACall.takeOnePlayAloneNotify@@@@ return by MultiWait/Furiten err");//~va8jR~
//  			callKanTakenPlayAloneNotify(PLAYER_YOU,eswn);      //~vaa1I~//~vaaUR~
    			callKanTakenPlayAloneNotify(PLAYER_YOU,eswn,PtdTaken);//~vaaUI~
                return;                                            //~va8jI~
            }                                                      //~va8jI~
        }                                                          //~va8jI~
      }                                                            //~va83I~
      boolean swRon=false;                                         //~vaa1I~
        if (shantenNew==-1)	//ronable                              //~va81I~
//          AG.aUADelayed.notify2TouchPlayAloneNotify(GCM_RON);	//update GC btn nochk 1han constraint//~va88I~
          swRon=                                                   //~vaa1I~
//      	AG.aUARon.selectInfoPlayAloneNotifyTake(PtdTaken);	//chk furiten,han constraint then highlight Ron btn and show Cancel btn//~va88R~//~vaa3R~
        	AG.aUARon.selectInfoPlayAloneNotifyTake(PtdTaken,swRonNoPair);	//chk furiten,han constraint then highlight Ron btn and show Cancel btn//~vaa3I~
        if (!swRon)                                                //~vaa1I~
//  		callKanTakenPlayAloneNotify(PLAYER_YOU,eswn);          //~vaa1I~//~vaaUR~
    		callKanTakenPlayAloneNotify(PLAYER_YOU,eswn,PtdTaken); //~vaaUI~
    }                                                              //~va81I~
    //*********************************************************    //~vaa2I~
    //*from RS.takeOne for Human player at PlayeMatchNotify mode   //~vaa2I~
    //*itsHand already includes taken                              //~vaadI~
    //*highligten Draw btn                                         //~vaa2I~
    //*********************************************************    //~vaa2I~
    public  void takeOnePlayMatchNotify(int Peswn,int Pplayer,int Ppos,TileData PtdTaken)//~vaa2I~
    {                                                              //~vaa2I~
        if (Dump.Y) Dump.println("RACall.takeOnePlayMatchNotify swPlayMatchNotify="+AG.swPlayMatchNotify+",eswn="+Peswn+",player="+Pplayer+",currentShanten="+RS.getCurrentShanten(Peswn)+",tdTake="+PtdTaken.toString());//~vaa2I~
        if (Pplayer!=PLAYER_YOU)                                   //~vaa2I~
        	return;                                                //~vaa2I~
        boolean swRonNoPair=false;                                 //~vaa2I~
        boolean swRon=false;                                       //~vaa2I~
        if (RS.getCurrentShanten(Peswn)!=0)                        //~vaa2I~
        {                                                          //~vaa2I~
//      	swRonNoPair=isRonNoPair(Peswn,Ppos);  //chk 13/14 NoPair//~vaa2I~//~vaadR~
        	swRonNoPair=isRonNoPair(Peswn);  //chk 13/14 NoPair    //~vaadI~
            swRon=swRonNoPair;                                     //~vaa2I~
          	if (!swRonNoPair)                                      //~vaa2I~
          	{                                                      //~vaa2I~
//  			callKanTakenPlayAloneNotify(PLAYER_YOU,eswn);      //~vaa2I~
//      		return;                                            //~vaa2I~
                if (Dump.Y) Dump.println("RACall.takeOnePlayMatchNotify current shanten!=0 and not 13/14 no pair");//~vaa2I~
          	}                                                      //~vaa2I~
            else                                                   //~vaa2I~
                swRon=true;                                        //~vaa2I~
        }                                                          //~vaa2I~
        int shantenNew;                                            //~vaa2I~
      	if (swRonNoPair)                                           //~vaa2I~
        	shantenNew=-1;	//ronable                              //~vaa2I~
      	else                                                       //~vaa2I~
      	{                                                          //~vaa2I~
//      	shantenNew=RS.RSP[Peswn].getShantenYou();	//set currentShanten (taken is included)/+va81R~//~vaa2I~//~vaadR~
//          shantenNew=RS.RSP[Peswn].setCurrentShantenYou();	//set currentShanten (taken is included)/+va81R~//~vaadI~//~vabpR~
        	shantenNew=RS.RSP[Peswn].getShanten();	//taken is included//~vabpI~
        	if (shantenNew==-1)	//ronable                          //~vaa2I~
        	{                                                      //~vaa2I~
                int[] itsH=RS.getItsHandEswn(Peswn);                //~vaa2I~
                int   ctrH=RS.RSP[Peswn].ctrHand;                   //~vaa2I~
                if (!AG.aRARon.callRonTakenPlayMatchNotify(PLAYER_YOU,Peswn,itsH,ctrH,PtdTaken))//~vaa2R~
                {                                                  //~vaa2I~
                    if (Dump.Y) Dump.println("RACall.takeOnePlayMatchNotify@@@@ shantenNew=-1 not call Ron by MultiWait/Furiten err");//~vaa2I~//~vaaNR~
//                  callKanTakenPlayAloneNotify(PLAYER_YOU,eswn);  //~vaa2I~
//                  return;                                        //~vaa2I~
					swRon=false;                                   //~vaaNI~
                    shantenNew=0;	//skip selectInfoPlayMatchNotifyMake(update GC button)//~vaaNI~
                }                                                  //~vaa2I~
                else                                               //~vaa2I~
	            	swRon=true;                                    //~vaa2I~
        	}                                                      //~vaa2I~
      	}                                                          //~vaa2I~
        if (shantenNew==-1)	//ronable                              //~vaa2I~
//        	swRon=AG.aUARon.selectInfoPlayAloneNotifyTake(PtdTaken);	//chk furiten,han constraint then highlight Ron btn and show Cancel btn//~vaa2I~//~vaa3R~
          	swRon=AG.aUARon.selectInfoPlayMatchNotifyTake(PtdTaken,swRonNoPair);	//chk furiten,han constraint then highlight Ron btn and show Cancel btn//~vaa3I~//~vaa2R~
        if (!swRon)                                                //~vaa2I~
//  		callKanTakenPlayMatchNotify(PLAYER_YOU,Peswn);          //~vaa2I~//~vaaUR~
    		callKanTakenPlayMatchNotify(PLAYER_YOU,Peswn,PtdTaken);//~vaaUI~
    }                                                              //~vaa2I~
    //*********************************************************    //~1128I~
    //*robot calls ron at nextplayerPonKan                          //~va75I~//~va89R~
    //*********************************************************    //~va75I~
    private void nextPlayerPonKan(int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~1128I~
    {                                                              //~1128I~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKan eswnOther="+PeswnOther+",eswnDiscarded="+PeswnDiscarded);       //~1128I~//~1212R~
    	int shanten=RS.getCurrentShanten(PeswnOther);              //~1128I~
    	int playerOther=RS.RSP[PeswnOther].player;                 //~1128I~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKan playerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",shanten="+shanten+",tdDiscarded="+PtdDiscarded.toString());//~1128R~
        if (shanten==0)                                            //~1131I~
        {                                                          //~1131I~
            if (AG.aRARon.callRonRiver(PeswnOther,PtdDiscarded))    //wait human player's Ron; robot's Ron may be delayed Ron//~1131I~
                return;                                            //~1131I~
        }                                                          //~1131I~
	    if (!isCallable(GCM_PON,playerOther,PeswnOther,PeswnDiscarded,PtdDiscarded))//~1128R~
            return;                                                //~1128I~
//        if (!callPonKan(shanten,playerOther,PeswnOther,Pplayer,PplayerEswn,PtdDiscarded))   //may issue Pon/Kan//~1128I~
//            callChii(PeswnOther,Pplayer,PplayerEswn,PtdDiscarded);    //may issue Chii//~1128I~
        callPonKan(shanten,playerOther,PeswnOther,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);   //may issue Pon/Kan//~1128I~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKan exit");  //~1128I~
    }                                                              //~1128I~
    //*********************************************************    //~va70I~
    //*On Server at timeoutPonKan for Human                        //~vaa2R~
    //*********************************************************    //~va70I~
//  private void nextPlayerPonKanPlayAlone(int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~va70I~//~vaa2R~
    private void nextPlayerPonKanHumanOnServer(int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~vaa2R~
    {                                                              //~va70I~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKanHumanOnServer eswnOther="+PeswnOther+",eswnDiscarded="+PeswnDiscarded+",swPlayMatchNotify="+AG.swPlayMatchNotify+",swPlayMatchNotify="+AG.swPlayAloneNotify);//~vaa2R~
        if (AG.swPlayMatchNotify)                                  //~vaa2I~
        {                                                          //~vaa2I~
			int playerOther=RS.RSP[PeswnOther].player;             //~vaa2I~
	    	nextPlayerPonKanPlayMatchNotify(playerOther,PeswnOther,PtdDiscarded);//~vaa2R~
            return;                                                //~vaa2I~
        }                                                          //~vaa2I~
        if (!AG.swPlayAloneNotify)                                 //~vaa2I~
        {                                                          //~vaa2I~
	        if (Dump.Y) Dump.println("RACall.nextPlayerPonKanHumanOnServer return by NOT PlayAloneNotify");//~vaa2I~
            return;                                                //~vaa2I~
        }                                                          //~vaa2I~
//  	int shanten=RS.getCurrentShanten(PeswnOther);              //~va70R~
//  	int playerOther=RS.RSP[PeswnOther].player;                 //~va70R~
//      if (Dump.Y) Dump.println("RACall.nextPlayerPonKan playerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",shanten="+shanten+",tdDiscarded="+PtdDiscarded.toString());//~va70R~
//      if (shanten==0)                                            //~va70R~
//      {                                                          //~va70R~
//          if (AG.aRARon.callRonRiver(PeswnOther,PtdDiscarded))    //wait human player's Ron; robot's Ron may be delayed Ron//~va70R~
//              return;                                            //~va70R~
//      }                                                          //~va70R~
	    if (!isCallablePlayAlone(GCM_PON,PLAYER_YOU,PeswnOther,PeswnDiscarded,PtdDiscarded))//~va70R~
            return;                                                //~va70I~
        callPonKanPlayAlone(PLAYER_YOU,PeswnOther,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);   //may issue Pon/Kan//~va70R~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKanHumanOnServer exit");//~va70I~//~vaa2R~
    }                                                              //~va70I~
    //****************************************************************************//~vaa2I~
    //*from RoundStatnot.timeoutPonKanClient()<--UADiscard.nextPlayerPonKan()//~vaa2I~
    //*On Client at timeoutPonKan from other discarded if swPlayMatchNotify//~vaa2R~
    //*On Server at timeoutPonKan                                  //~vaa2I~
    //****************************************************************************//~vaa2I~
    public  void nextPlayerPonKanPlayMatchNotify(int Pplayer,int Peswn,TileData PtdDiscarded)//~vaa2R~
    {                                                              //~vaa2I~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKanPlayMatchNotify player="+Pplayer+",eswn="+Peswn+",tdDiscard="+PtdDiscarded.toString());//~vaa2I~
        if (Pplayer!=PLAYER_YOU)                                   //~vaa2I~
        	return;                                                //~vaa2I~
	    if (!isCallablePlayMatchNotify(GCM_KAN_OR_PON,Pplayer,Peswn,PtdDiscarded))//~vaa2I~
            return;                                                //~vaa2I~
        callPonKanPlayMatchNotify(Pplayer,Peswn,PtdDiscarded);   //may issue Pon/Kan//~vaa2R~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKanPlayMatchNotify exit");//~vaa2I~
    }                                                              //~vaa2I~
    //****************************************************************************//~vaa2I~
    //*from UADiscard.nextPlayer()                                 //~vaa2I~
    //*On Server and Client                                        //~vaa2I~
    //****************************************************************************//~vaa2I~
    public  void nextPlayerPlayMatchNotify(boolean PswServer,int Pplayer,TileData PtdDiscarded)//~vaa2I~
    {                                                              //~vaa2I~
        if (Dump.Y) Dump.println("RACall.nextPlayerPlayMatchNotify swServer="+PswServer+",player="+Pplayer+",tdDiscard="+PtdDiscarded.toString());//~vaa2I~
        if (Pplayer!=PLAYER_YOU)                                   //~vaa2I~
        	return;                                                //~vaa2I~
        int eswn=Accounts.playerToEswn(PLAYER_YOU);          //~va75R~//~vaa2M~
	    if (!isCallablePlayMatchNotify(GCM_CHII,Pplayer,eswn,PtdDiscarded))//~vaa2R~
            return;                                                //~vaa2I~
        callChiiPlayMatchNotify(Pplayer,eswn,PtdDiscarded);   //may issue Pon/Kan//~vaa2I~
        if (Dump.Y) Dump.println("RACall.nextPlayerPlayMatchNotify exit");//~vaa2I~
    }                                                              //~vaa2I~
    //*********************************************************    //~1128I~
    //*From UADiscard.autoTakeTimeout()-->Robot.autoTakeTimeout()->RoundStat.AutoTakeTimeout()-->                        //~1129R~//~vaaNR~
    //*********************************************************    //~1129I~
    public boolean autoTakeTimeout(int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~1128R~//~1129R~
    {                                                              //~1128I~
    	boolean rc=false;                                          //~1128I~
        if (Dump.Y) Dump.println("RACall.autoTakeTimeout eswnOther="+PeswnOther+",playerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",tdDiscard="+PtdDiscarded.toString()+"="+PtdDiscarded);//~1128R~//~1129R~//~va75R~
    	int playerOther=RS.RSP[PeswnOther].player;                 //~1128I~
	    if (!isCallable(GCM_CHII,playerOther,PeswnOther,PeswnDiscarded,PtdDiscarded))//~1128R~
            return false;                                          //~1128R~
        rc=callChii(playerOther,PeswnOther,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);    //may issue Chii//~1128R~
        if (Dump.Y) Dump.println("RACall.autoTakeTimeout rc="+rc); //~1129I~
        return rc;                                                 //~1128I~
    }                                                              //~1128I~
    //*********************************************************    //~va75I~
    //*For Human                                                   //~vaaiR~
    //*From UADiscard at autoTakeTimeout when Notify mode          //~vaaiI~
    //*********************************************************    //~va75I~
    public boolean autoTakeTimeoutPlayAloneNotify(int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~va75R~
    {                                                              //~va75I~
    	boolean rc=false;                                          //~va75I~
        if (Dump.Y) Dump.println("RACall.timeoutAutoTakePlayAloneNotify eswnOther="+PeswnOther+",playerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",tdDiscard="+PtdDiscarded.toString()+"="+PtdDiscarded);//~va75I~
	    if (!isCallablePlayAlone(GCM_CHII,PLAYER_YOU,PeswnOther,PeswnDiscarded,PtdDiscarded))//~va75I~
            return false;                                          //~va75I~
        rc=callChiiPlayAloneNotify(PLAYER_YOU,PeswnOther,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);    //may issue Chii//~va75I~
        if (Dump.Y) Dump.println("RACall.autoTakeTimeoutPlayAloneNotify rc="+rc);//~va75I~
        return rc;                                                 //~va75I~
    }                                                              //~va75I~
    //*********************************************************    //~1128I~
    private boolean isCallable(int Paction,int PplayerOther,int PeswnOther,int PeswnDiscarded,TileData PtdDiscarded)//~1128I~
    {                                                              //~1128I~
    	boolean rc=true;                                           //~1128I~
        int myShanten=RS.getCurrentShanten(PeswnOther);            //~1222I~
	    if (Dump.Y) Dump.println("RACall.isCallable action="+Paction+",myShanten="+myShanten);//~vaaeI~
      	if (Status.isIssuedRon())                                  //~va70I~
        {                                                          //~va70I~
	        if (Dump.Y) Dump.println("RACall.isCallable @@@@ False issuedRon");//~va70I~
            return false;                                          //~va70I~
        }                                                          //~va70I~
        if (RS.RSP[PeswnOther].isReachCalled())	//could not call after reach called//~1128I~
        {                                                          //~1128I~
	        if (Dump.Y) Dump.println("RACall.isCallable @@@@ False already reached");//~1128I~//~1215R~//~1221R~
            return false;                                          //~1224R~
        }                                                          //~1128I~
        if ((PtdDiscarded.flag & TDF_LAST)!=0)	//last could not be called//~1128I~
        {                                                          //~1128I~
	        if (Dump.Y) Dump.println("RACall.isCallable @@@@ False last tile");//~1128I~//~1215R~//~1221R~
            return false;                                          //~1224R~
        }                                                          //~1128I~
        int intent=RS.RSP[PeswnOther].getIntent();                 //~1224I~
//      if ((intent & (INTENT_GIVEUP|INTENT_GIVEUP_WEAK))!=0)       //~1224I~//~va8zR~
        if ((intent & (INTENT_GIVEUP))!=0)                         //~va8zI~
        {                                                          //~1224I~
	        if (Dump.Y) Dump.println("RACall.isCallable @@@@ False by intent giveup intent="+Integer.toHexString(intent));//~1224I~//~1302R~
            return false;                                          //~1224I~
        }                                                          //~1224I~
        if ((intent & (INTENT_7PAIR | INTENT_13ORPHAN))!=0)//~1215I~//~1224R~
        {                                                          //~1215I~
	        if (Dump.Y) Dump.println("RACall.isCallable @@@@ False by Intent="+Integer.toHexString(RS.RSP[PeswnOther].intent));//~1215I~//~1221R~
            return false;                                          //~1224I~
        }                                                          //~1215I~
        if (Paction==GCM_KAN) //kan taken, tile is taken           //~1222I~
        {                                                          //~1222I~
	        if (Dump.Y) Dump.println("RACall.isCallable GCM_TAKE by kantaken");//~1222I~
        }                                                          //~1222I~
        else                                                       //~1222I~
        if (Paction==GCM_PON) //include kan for discarded                                     //~1128I~//~1222R~
        {                                                          //~1128I~
            if (AG.aUserAction.UAP.isLocked(PplayerOther))                  //~1128I~
            	rc=false;                                          //~1128I~
        }                                                          //~1128I~
        else                                                       //~1128I~
        if (Paction==GCM_CHII)                                     //~1128R~
        {                                                          //~1128I~
        	int cp=AG.aPlayers.getCurrentPlayer();                 //~1128I~
        	int robotPlayer=RS.RSP[PeswnOther].player;             //~1128R~
        	if (Players.nextPlayer(cp)!=robotPlayer)               //~1128I~
            {                                                      //~1128I~
		        if (Dump.Y) Dump.println("RACall.isCallable @@@@ not next player of CHII cp="+cp+",robotPlayer="+robotPlayer);//~1128R~//~1221R~
            	rc=false;                                          //~1128I~
            }                                                      //~1128I~
            if (AG.aUserAction.UAC.isLocked(PplayerOther))                             //~1128I~
            	rc=false;                                          //~1128I~
        }                                                          //~1128I~
        if (rc)                                                   //~1206I~//~1216R~
	        if (!isTimeToCall(PplayerOther,PeswnOther,Paction,PtdDiscarded,myShanten,intent))                 //~1206I~//~1219R~//~1222R~//~1305R~//~1306R~
    	    	rc=false;                                          //~1206I~
        if (Dump.Y) Dump.println("RACall.isCallable rc="+rc+",action="+Paction+",eswn="+PeswnOther+",eswnDiscard="+PeswnDiscarded+",td="+PtdDiscarded.toString());//~1128I~
        return rc;                                                 //~1128I~
    }                                                              //~1128I~
    //*********************************************************    //~va70I~
    //*human player on Client                                      //~vaa2I~
    //*********************************************************    //~va70I~
    private boolean isCallablePlayAlone(int Paction,int PplayerOther,int PeswnOther,int PeswnDiscarded,TileData PtdDiscarded)//~va70I~
    {                                                              //~va70I~
    	boolean rc=true;                                           //~va70I~
	    if (Dump.Y) Dump.println("RACall.isCallablePlayAlone action="+Paction+",swTrainingMode="+AG.swTrainingMode+",notifymode="+AG.swPlayAloneNotify);//~va70I~//~vaaeR~
        if (!AG.swPlayAloneNotify)                                 //~va70I~
        {                                                          //~va70I~
	        if (Dump.Y) Dump.println("RACall.isCallablePlayAlone @@@@ False not notifymode");//~va70I~
            return false;                                          //~va70I~
        }                                                          //~va70I~
      	if (Status.isIssuedRon())                                  //~va70I~
        {                                                          //~va70I~
	        if (Dump.Y) Dump.println("RACall.isCallablePlayAlone @@@@ False issuedRon");//~va70I~
            return false;                                          //~va70I~
        }                                                          //~va70I~
        if (RS.RSP[PeswnOther].isReachCalled())	//could not call after reach called//~va70I~
        {                                                          //~va70I~
	        if (Dump.Y) Dump.println("RACall.isCallablePlayAlone @@@@ False already reached");//~va70I~
            return false;                                          //~va70I~
        }                                                          //~va70I~
        if ((PtdDiscarded.flag & TDF_LAST)!=0)	//last could not be called//~va70I~
        {                                                          //~va70I~
	        if (Dump.Y) Dump.println("RACall.isCallablePlayAlone @@@@ False last tile");//~va70I~//~vaa2R~
            return false;                                          //~va70I~
        }                                                          //~va70I~
//        int intent=RS.RSP[PeswnOther].getIntent();               //~va70I~
//        if ((intent & (INTENT_GIVEUP|INTENT_GIVEUP_WEAK))!=0)    //~va70I~
//        {                                                        //~va70I~
//            if (Dump.Y) Dump.println("RACall.isCallable @@@@ False by intent giveup intent="+Integer.toHexString(intent));//~va70I~
//            return false;                                        //~va70I~
//        }                                                        //~va70I~
//        if ((intent & (INTENT_7PAIR | INTENT_13ORPHAN))!=0)      //~va70I~
//        {                                                        //~va70I~
//            if (Dump.Y) Dump.println("RACall.isCallable @@@@ False by Intent="+Integer.toHexString(RS.RSP[PeswnOther].intent));//~va70I~
//            return false;                                        //~va70I~
//        }                                                        //~va70I~
        if (Paction==GCM_KAN) //kan taken, tile is taken           //~va70I~
        {                                                          //~va70I~
	        if (Dump.Y) Dump.println("RACall.isCallable GCM_TAKE by kantaken");//~va70I~
        }                                                          //~va70I~
        else                                                       //~va70I~
        if (Paction==GCM_PON) //include kan for discarded          //~va70I~
        {                                                          //~va70I~
            if (AG.aUserAction.UAP.isLocked(PplayerOther))         //~va70I~
            	rc=false;                                          //~va70I~
        }                                                          //~va70I~
        else                                                       //~va70I~
        if (Paction==GCM_CHII)                                     //~va70I~
        {                                                          //~va70I~
        	int cp=AG.aPlayers.getCurrentPlayer();                 //~va70I~
//      	int robotPlayer=RS.RSP[PeswnOther].player;             //~va70I~
//      	if (Players.nextPlayer(cp)!=robotPlayer)               //~va70I~
        	if (Players.nextPlayer(cp)!=PplayerOther)              //~va70I~
            {                                                      //~va70I~
		        if (Dump.Y) Dump.println("RACall.isCallablePlayAlone @@@@ not next player of CHII cp="+cp+",PplayerOther="+PplayerOther);//~va70I~//~va75R~
            	rc=false;                                          //~va70I~
            }                                                      //~va70I~
            if (AG.aUserAction.UAC.isLocked(PplayerOther))         //~va70I~
            	rc=false;                                          //~va70I~
        }                                                          //~va70I~
//      if (rc)                                                    //~va70I~
//          if (!isTimeToCall(PplayerOther,PeswnOther,Paction,PtdDiscarded,myShanten,intent))//~va70I~
//  	    	rc=false;                                          //~va70I~
        if (Dump.Y) Dump.println("RACall.isCallablePlayAlone rc="+rc+",action="+Paction+",eswn="+PeswnOther+",eswnDiscard="+PeswnDiscarded+",td="+PtdDiscarded.toString());//~va70I~
        return rc;                                                 //~va70I~
    }                                                              //~va70I~
    //**********************************************************************//~vaa2I~
    //*human player in PlayMatchNotify mode on Client PLAYER_YOU   //~vaa2R~
    //**********************************************************************//~vaa2I~
    private boolean isCallablePlayMatchNotify(int Paction,int Pplayer,int Peswn,TileData PtdDiscarded)//~vaa2I~
    {                                                              //~vaa2I~
    	boolean rc=true;                                           //~vaa2I~
	    if (Dump.Y) Dump.println("RACall.isCallablePlayMatchNotify action="+Paction+",notifymode="+AG.swPlayAloneNotify+",player="+Pplayer+",eswn="+Peswn);//~vaa2R~
//      if (!AG.swPlayAloneNotify)                                 //~vaa2I~
//      {                                                          //~vaa2I~
//          if (Dump.Y) Dump.println("RACall.isCallablePlayAlone @@@@ False not notifymode");//~vaa2I~
//          return false;                                          //~vaa2I~
//      }                                                          //~vaa2I~
      	if (Status.isIssuedRon())                                  //~vaa2I~
        {                                                          //~vaa2I~
	        if (Dump.Y) Dump.println("RACall.isCallablePlayMatchNotify @@@@ False issuedRon");//~vaa2I~
            return false;                                          //~vaa2I~
        }                                                          //~vaa2I~
        if (RS.RSP[Peswn].isReachCalled())	//could not call after reach called//~vaa2I~
        {                                                          //~vaa2I~
	        if (Dump.Y) Dump.println("RACall.isCallablePlayMatchNotify @@@@ False already reached");//~vaa2I~
            return false;                                          //~vaa2I~
        }                                                          //~vaa2I~
        if ((PtdDiscarded.flag & TDF_LAST)!=0)	//last could not be called//~vaa2I~
        {                                                          //~vaa2I~
	        if (Dump.Y) Dump.println("RACall.isCallablePlayMatchNotify @@@@ False last tile");//~vaa2I~
            return false;                                          //~vaa2I~
        }                                                          //~vaa2I~
//      if (Paction==GCM_KAN) //kan taken, tile is taken           //~vaa2I~
//      {                                                          //~vaa2I~
//          if (Dump.Y) Dump.println("RACall.isCallable GCM_TAKE by kantaken");//~vaa2I~
//      }                                                          //~vaa2I~
//      else                                                       //~vaa2I~
        if (Paction==GCM_KAN_OR_PON) //include kan for discarded   //~vaa2R~
        {                                                          //~vaa2R~
        	if (Pplayer==AG.aPlayers.getLastDiscardedPlayer())     //~vaa2I~
            	rc=false;                                          //~vaa2I~
            else                                                   //~vaa2I~
            if (AG.aUserAction.UAP.isLocked(Pplayer))             //~vaa2R~
            	rc=false;                                          //~vaa2R~
        }                                                          //~vaa2R~
        else                                                       //~vaa2R~
        if (Paction==GCM_CHII)                                     //~vaa2R~
        {                                                          //~vaa2R~
        	int cp=AG.aPlayers.getCurrentPlayer();                 //~vaa2R~
        	if (Players.nextPlayer(cp)!=Pplayer)                   //~vaa2R~
            {                                                      //~vaa2R~
    	        if (Dump.Y) Dump.println("RACall.isCallablePlayAlone @@@@ not next player of CHII cp="+cp+",Pplayer="+Pplayer);//~vaa2R~
            	rc=false;                                          //~vaa2R~
            }                                                      //~vaa2R~
            if (AG.aUserAction.UAC.isLocked(Pplayer))              //~vaa2R~
            	rc=false;                                          //~vaa2R~
        }                                                          //~vaa2R~
        if (Dump.Y) Dump.println("RACall.isCallablePlayMatchNotify rc="+rc+",action="+Paction+",player="+Pplayer+",eswn="+Peswn+",td="+PtdDiscarded.toString());//~vaa2R~
        return rc;                                                 //~vaa2I~
    }                                                              //~vaa2I~
    //*********************************************************    //~1118I~
    //*under not Reach called                                      //~1118I~
    //*shanten>0 or shanten=0 but not ronable                      //~1118I~
    //*********************************************************    //~1118I~
    private boolean callPonKan(int Pshanten,int PplayerOther,int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~1118R~//~1124R~//~1126R~//~1222R~
    {                                                              //~1118I~//~1222R~
        int shanten;                                               //~1222R~
        //************************                                 //~1119I~//~1222R~
        int shanten0=Pshanten;  //>0 as condition                      //~1118I~//~1119I~//~1125R~//~1222R~
        int pos=RAUtils.getPosTile(PtdDiscarded);                  //~1118I~//~1119R~//~1222R~
        int intent=RS.RSP[PeswnOther].intent;                     //~1118I~//~1124R~//~1126R~//~1220I~//~1222R~
		int ctrReach=AG.aPlayers.getCtrReachedPlayer(-1/*Player to be excluded*/);//~vaarI~
        if (Dump.Y) Dump.println("RACall.callPonKan Pshanten="+Pshanten+",pos="+pos+",ctrReach="+ctrReach+",intent="+Integer.toHexString(intent)+",eswnOther="+PeswnOther+",playerOther="+PplayerOther+",PplayerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",tdDiscard="+TileData.toString(PtdDiscarded));//~1118R~//~1124R~//~1125I~//~1126R~//~1130R~//~1220R~//~1222R~//~vaarR~
        int[] itsH=RS.getItsHandEswn(PeswnOther);                   //~1118I~//~1124R~//~1126R~//~1222R~
        int   ctrH=RS.RSP[PeswnOther].ctrHand;                       //~1118I~//~1124R~//~1126R~//~1222R~
        int ctrPos=itsH[pos];                                      //~1118R~//~1222R~
        if (Dump.Y) Dump.println("RACall.callPonKan ctrPos="+ctrPos+",itsH="+Utils.toString(itsH,9));//~vaarI~
        if (ctrPos<PAIRCTR-1)                                      //~1119I~//~1222R~
        {                                                          //~1119I~//~1222R~
            if (Dump.Y) Dump.println("RACall.callPonKan return by could not make same meld ctrPos="+ctrPos);//~1119I~//~1125R~//~1222R~
            return false;                                          //~1119I~//~1222R~
        }                                                          //~1119I~//~1222R~
        int ctrCall=0;                                             //~1119R~//~1222R~
//    if ((TestOption.option2 & TO2_CALL1ST)!=0)                   //~va75I~//~vab3R~
      if ((TestOption.option2 & TO2_CALL1ST)!=0 && ctrPos!=PAIRCTR)//~vab3R~
      {                                                            //~va75I~
	  	if (Dump.Y) Dump.println("RACall.callPonKan ignore intent and fix1 by testoption TO2_CALL1ST and ctrPos="+ctrPos+" is not Kan");//~va75I~//~vab3R~
         ctrCall=ctrPos+1;           //3 or 4                      //~va75I~
      }                                                            //~va75I~
      else                                                         //~va75I~
      {                                                            //~va75I~
        if ((intent & INTENT_ALLSAME)!=0)                          //~1220I~//~1222R~
        {                                                          //~1220I~//~1222R~
            ctrCall=ctrPos+1;           //3 or 4                   //~1220I~//~1222R~
            if (Dump.Y) Dump.println("RACall.callPonKan issue by ALLSAME intent");//~1220I~//~1222R~
        }                                                          //~1220I~//~1222R~
        else                                                       //~1220I~//~1222R~
        if ((intent & INTENT_SAMECOLOR_ANY)!=0)                    //~1220I~//~1222R~
        {                                                          //~1220I~//~1222R~
            if (RAUtils.isMatchSameColor(true/*allow Word*/,intent,pos/CTR_NUMBER_TILE))//~1220I~//~1222R~
                ctrCall=isShantenUp(itsH,ctrH,pos,-2,Pshanten) ? ctrPos+1 : 0;//~1220I~//~1222R~
            if (Dump.Y) Dump.println("RACall.callPonKan chk by ALLSAME intent ctrCall="+ctrCall);//~1220I~//~1222R~
        }                                                          //~1220I~//~1222R~
        if (ctrCall==0)                                            //~1220I~//~1222R~
        {                                                          //~1220I~//~1222R~
            boolean swAllInHand=RS.RSP[PeswnOther].swAllInHand;    //~vab4I~
            boolean swFixed1=RS.RSP[PeswnOther].isFixed1();        //~vab4I~
            if (pos>=OFFS_WORDTILE)                                //~1118R~//~1119R~//~1220R~//~1222R~
            {                                                      //~1118I~//~1119R~//~1220R~//~1222R~
                int v=RS.RSP[PeswnOther].getCtrValueWordDup();     //~va8hI~
                int hanTile=RAUtils.chkValueWordTile(pos,PeswnOther)/2;   //2 for doubleEast//~va8oI~
//              boolean swAllInHand=RS.RSP[PeswnOther].swAllInHand;//~va8oI~//~vab4R~
//              boolean swFixed1=RS.RSP[PeswnOther].isFixed1();    //~va8pI~//~vab4R~
			    if (Dump.Y) Dump.println("RACall.callPonKan v="+v+",hanTile="+hanTile+",swFixed1="+swFixed1+",swAllInHand="+swAllInHand);//~va8pI~//~vaarR~
                if (swFixed1)                                      //~va8pI~
                    hanTile++;                                     //~va8pI~
//              if (RS.RSP[PeswnOther].isFixed1()   //already fixed//~1221R~//~1222R~//~va8pR~
                if (swFixed1   //already fixed                     //~va8pI~
//              ||  RS.RSP[PeswnOther].swAllInHand && RAUtils.chkValueWordTile(pos,PeswnOther)>0    //not yet called and this is yakuhai//~1221I~//~1222R~//~va8oR~
                ||  swAllInHand && hanTile>0    //not yet called and this is yakuhai//~va8oI~
//              ||  RS.RSP[PeswnOther].getCtrValueWordDup()>1 && (intent & INTENT_7PAIR)!=0       //multiple pair of valueword                                  //multiple valueword candidate//~1223R~//~va8hR~
                ||  v                                      >1 && (intent & INTENT_7PAIR)!=0       //multiple pair of valueword                                  //multiple valueword candidate//~va8hI~
                ||  (intent & (INTENT_SAMECOLOR_ANY|INTENT_ALLSAME))!=0   //honitsu or toitoi//~1118I~//~1119R~//~1124R~//~1126R~//~1220R~//~1222R~
                )                                                  //~1118I~//~1119R~//~1220R~//~1222R~
//                if (v/2<2 && RS.swFix2)      //2han constraint   //~va8hR~//~va8oR~
                  if (RS.swFix2)                                   //~va8oR~
                  {                                                //~va8hI~
                   if (swAllInHand || swFixed1)   //already fixed  //~va8pR~
                   {                                               //~va8pI~
                  	if (hanTile>=2 || swAllInHand && v>=2)         //~va8oI~
                    {                                              //~va8oI~
	                    ctrCall=ctrPos+1;           //3 or 4       //~va8oI~
                    }                                              //~va8oI~
                    else                                           //~va8pI~
	                if ((intent & (INTENT_SAMECOLOR_ANY|INTENT_ALLSAME))!=0)   //honitsu or toitoi//~va8pI~
                    {                                              //~va8pI~
	                    ctrCall=ctrPos+1;           //3 or 4       //~va8pI~
                    }                                              //~va8pI~
                    else                                           //~va8oI~
			            if (Dump.Y) Dump.println("RACall.callPonKan skip call by 2han constraint wordValue="+v+",swFix2="+RS.swFix2+",swAllInHand="+swAllInHand);//~va8hR~//~va8pR~
                   }                                               //~va8pI~
                  }                                                //~va8hI~
                  else                                             //~va8hI~
                  {                                                //~vaarI~
                    ctrCall=ctrPos+1;           //3 or 4               //~1119R~//~1220R~//~1222R~
                	if (!chkWinTileCtrPon(PeswnOther,shanten0-1/*will up*/,ctrReach,pos,itsH,ctrH))//~vaarI~
                    {                                              //~vaarI~
					    if (Dump.Y) Dump.println("RACall.callPonKan@@@@ NO issue Pon for REACH by wintile ctr");//~vaarI~
                			ctrCall=-1;                            //~vaarI~
                    }                                              //~vaarI~
                  }                                                //~vaarI~
            }                                                      //~1118I~//~1119R~//~1220R~//~1222R~
            else                                                   //~1118I~//~1119R~//~1220R~//~1222R~
            {                                                      //~1118I~//~1119R~//~1220R~//~1222R~
                if (Dump.Y) Dump.println("RACall.callPonKan intent="+Integer.toHexString(intent));//~1131I~//~1220R~//~1222R~
                if (RS.RSP[PeswnOther].isFixed1()   //yakuhai avail   //~1129I~//~1130R~//~1220R~//~1222R~
                || ((intent & INTENT_TANYAO)!=0 && RS.swKuitan && RAUtils.isTanyaoTile(pos) && RS.RSP[PeswnOther].isPairTanyaoAllOrNoPair())//~1217R~//~1220R~//~1222R~
                )                                                      //~1129I~//~1220R~//~1222R~
                {                                                  //~1118I~//~1119R~//~1220R~//~1222R~
                    int shantenK=MAX_SHANTEN;                          //~1119I~//~1220R~//~1222R~
                    if (ctrPos==PAIRCTR)                           //~1118R~//~1119R~//~1220R~//~1222R~
                    {                                              //~1118I~//~1119R~//~1220R~//~1222R~
    //                  shantenK=RAUtils.getShantenAdd(itsH,ctrH,pos,-PAIRCTR);//~1118I~//~1119R~//~1126R~//~1220R~//~1222R~
                        shantenK=chkShantenAddCall(PeswnOther,itsH,ctrH,pos,-PAIRCTR);//~1126I~//~1128R~//~1220R~//~1222R~
                    }                                              //~1118I~//~1119R~//~1220R~//~1222R~
    //              shanten=RAUtils.getShantenAdd(itsH,ctrH,pos,-(PAIRCTR-1));//~1118I~//~1119R~//~1126R~//~1220R~//~1222R~
                    shanten=chkShantenAddCall(PeswnOther,itsH,ctrH,pos,-(PAIRCTR-1));//~1126I~//~1128R~//~1220R~//~1222R~
                    if ((shantenK==0 || shantenK<shanten) && shantenK<=shanten0)        //~1119I~//~1130R~//~1131R~//~1220R~//~1222R~
                        ctrCall=PAIRCTR_KAN;    //4                    //~1119I~//~1220R~//~1222R~
                    else                                               //~1119I~//~1220R~//~1222R~
//                      if (shanten<shanten0)                          //~1119I~//~1131R~//~1220R~//~1222R~//~vab4R~
                        if (shanten<shanten0                       //~vab4I~
                        ||  (shanten==shanten0 && !swAllInHand && swFixed1 && isDora(pos,PtdDiscarded))//~vab4R~
                        )//~vab4I~
                        {                                          //~vaarI~
		            		if (Dump.Y) Dump.println("RACall.callPonKan may call Pon by shanten or dora shanten="+shanten+",shanten0="+shanten0+",swAllInHand="+swAllInHand);//~vab4I~
                            ctrCall=PAIRCTR;    //3                    //~1119I~//~1220R~//~1222R~
                			if (!chkWinTileCtrPon(PeswnOther,shanten,ctrReach,pos,itsH,ctrH))//~vaarR~
                            {                                      //~vaarI~
					            if (Dump.Y) Dump.println("RACall.callPonKan NO issue Pon for REACH by wintile ctr");//~vaarI~
                				ctrCall=-1;                        //~vaarI~
                            }                                      //~vaarI~
                        }                                          //~vaarI~
                }                                                  //~1118I~//~1119R~//~1220R~//~1222R~
		        if ((pos==TN1 || pos==TN9)                         //~1306I~
		        &&  ((intent & INTENT_CHANTA)!=0 && RS.RSP[PeswnOther].isPairChantaAllOrNoPair())//~1306I~
                &&   isShantenUp(itsH,ctrH,pos,-2,Pshanten)        //~1306I~
                )                                                  //~1306I~
                {                                                  //~1306I~
		            if (Dump.Y) Dump.println("RACall.callPonKan chanta return by issue Pon/Kan pos="+pos);//~1306I~
                    ctrCall=ctrPos+1;                              //~1306I~
                }                                                  //~1306I~
            }                                                      //~1118I~//~1119R~//~1220R~//~1222R~
        }                                                          //~1220I~//~1222R~
      }//testoption                                                //~va75I~
        if (ctrCall==PAIRCTR_KAN)                                  //~vaatI~
        {                                                          //~vaatI~
            if (ctrReach!=0)                                       //~vaatI~
            {                                                      //~vaatI~
		        if (Dump.Y) Dump.println("RACall.callPonKan avoid kan by ctrReach="+ctrReach);//~vaatI~
                ctrCall=-1;                                        //~vaatI~
            }                                                      //~vaatI~
        }                                                          //~vaatI~
        if (ctrCall==PAIRCTR_KAN)                                                 //~1118I~//~1119R~//~1222R~
        {                                                          //~1118I~//~1222R~
            tdsPairKan=makeKanRiver(PeswnOther,pos,PtdDiscarded); //~1124R~//~1126R~//~1222R~
            issueKan(KAN_RIVER,PplayerOther,PeswnOther,tdsPairKan);                       //~1118I~//~1119R~//~1124I~//~1126R~//~1222R~
            if (Dump.Y) Dump.println("RACall.callPonKan return by issueKan");//~1118I~//~1119R~//~1222R~
            return true;                                           //~1118I~//~1222R~
        }                                                          //~1118I~//~1222R~
        if (ctrCall==PAIRCTR)                                       //~1118I~//~1119R~//~1222R~
        {                                                          //~1118I~//~1222R~
            issuePon(PplayerOther,PeswnOther,pos,PtdDiscarded);                       //~1118I~//~1119R~//~1126R~//~1222R~
            if (Dump.Y) Dump.println("RACall.callPonKan return by issuePon");//~1118I~//~1119R~//~1222R~
            return true;                                           //~1118I~//~1222R~
        }                                                          //~1118I~//~1222R~
        if (Dump.Y) Dump.println("RACall.callPonKan return no action");//~1118I~//~1222R~
        return false;//~1118I~                                     //~1222R~
    }                                                              //~1118I~//~1222R~
    //**************************************************************************//~va70I~
    //*chk make meld  and highlight button                         //~va70I~
    //*for Human in PlayAloneNotify mode                           //~vaaiI~
    //**************************************************************************//~va70I~
    private boolean callPonKanPlayAlone(int PplayerOther,int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~va70I~
    {                                                              //~va70I~
        int shanten;                                               //~va70I~
        //************************                                 //~va70I~
        int pos=RAUtils.getPosTile(PtdDiscarded);                  //~va70I~
        int[] itsH=RS.getItsHandEswn(PeswnOther);                  //~va70I~
        int   ctrH=RS.RSP[PeswnOther].ctrHand;                     //~va70I~
        int ctrPos=itsH[pos];                                      //~va70I~
        if (Dump.Y) Dump.println("RACall.callPonKanPlayAlone ctrPos="+ctrPos+",pos="+pos+",eswnOther="+PeswnOther+",playerOther="+PplayerOther+",PplayerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",tdDiscard="+TileData.toString(PtdDiscarded));//~va70I~
        if (Dump.Y) Dump.println("RACall.callPonKanPlayAlone itsHand="+Utils.toString(itsH,9));//~va70I~//~vaa2R~
        if (ctrPos<PAIRCTR-1)                                      //~va70I~
        {                                                          //~va70I~
            if (Dump.Y) Dump.println("RACall.callPonKan return by could not make same meld ctrPos="+ctrPos);//~va70I~
            return false;                                          //~va70I~
        }                                                          //~va70I~
        AG.aUAD2Touch.updateBtnPlayAloneNotify(ctrPos==PAIRCTR ? GCM_KAN_OR_PON : GCM_PON,BTN_STATUS_ENABLE_CANCEL);//~va70R~
        return true;                                               //~va70I~
    }                                                              //~va70I~
    //**************************************************************************//~vaa2I~
    //*chk make meld  and highlight button                         //~vaa2I~
    //**************************************************************************//~vaa2I~
    private boolean callPonKanPlayMatchNotify(int Pplayer,int Peswn,TileData PtdDiscarded)//~vaa2R~
    {                                                              //~vaa2I~
        int shanten;                                               //~vaa2I~
        //************************                                 //~vaa2I~
        int pos=RAUtils.getPosTile(PtdDiscarded);                  //~vaa2I~
        int[] itsH=RS.getItsHandEswn(Peswn);                  //~vaa2I~
        int   ctrH=RS.RSP[Peswn].ctrHand;                     //~vaa2I~
        int ctrPos=itsH[pos];                                      //~vaa2I~
        if (Dump.Y) Dump.println("RACall.callPonKanPlayMatchNotify pos="+pos+",ctrOnPos="+ctrPos+",eswn="+Peswn+",player="+Pplayer+",tdDiscard="+TileData.toString(PtdDiscarded));//~vaa2I~
        if (Dump.Y) Dump.println("RACall.callPonKanPlayMatchNotify itsHand="+Utils.toString(itsH,9));//~vaa2I~
        if (ctrPos<PAIRCTR-1)                                      //~vaa2I~
        {                                                          //~vaa2I~
            if (Dump.Y) Dump.println("RACall.callPonKan return by could not make same meld ctrPos="+ctrPos);//~vaa2I~
            return false;                                          //~vaa2I~
        }                                                          //~vaa2I~
        AG.aUAD2Touch.stopAuto2TouchPlayMatchNotify(ctrPos==PAIRCTR ? GCM_KAN_OR_PON : GCM_PON);//~vaa2I~
        return true;                                               //~vaa2I~
    }                                                              //~vaa2I~
    //**************************************************************************//~vaa2I~
//  private boolean callKanTakenPlayAloneNotify(int Pplayer,int Peswn)//~vaa1I~//~vaaUR~
    private boolean callKanTakenPlayAloneNotify(int Pplayer,int Peswn,TileData PtdTaken)//~vaaUI~
    {                                                              //~vaa1I~
        int shanten;                                               //~vaa1I~
        //************************                                 //~vaa1I~
        if (Dump.Y) Dump.println("RACall.callKanTakenPlayAloneNotify eswn="+Peswn+",swPlayAloneNotify="+AG.swPlayAloneNotify+",tdTaken="+PtdTaken.toString());//~vaa1I~//~vaa2R~//~vaaUR~
        if (!AG.swPlayAloneNotify)                                 //~vaa1I~
        	return false;                                          //~vaa1I~
        int[] itsH=RS.getItsHandEswn(Peswn);                       //~vaa1I~
        int   ctrH=RS.RSP[Peswn].ctrHand;                     //~vaa1I~
        if (Dump.Y) Dump.println("RACall.callKanTakenPlayAloneNotify itsHand="+Utils.toString(itsH,9));//~vaa1I~//~vaa2R~
        boolean swKan=false;                                       //~vaa1I~
        boolean swNotify=false;                                    //~vabnR~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~vaa1I~
        {                                                          //~vaa1I~
        	if (itsH[ii]==PAIRCTR_KAN)                             //~vaa1I~
            {                                                      //~vaa1I~
//          	swNotify=!swSkipNotifyKanTaken || RS.RSP[Peswn].isReachCalled();	//optionally except after reach called//~vabnR~
            	swKan=true;                                        //~vaa1I~
                break;                                             //~vaa1I~
            }                                                      //~vaa1I~
		}                                                          //~vaa1I~
        if (chkEarthToAddKan(Peswn,PtdTaken))                       //~vaaUI~
        {                                                          //~vabnI~
//      	swNotify=true; 	//notify for kan add                   //~vabnR~
            swKan=true;                                            //~vaaUI~
        }                                                          //~vabnI~
//      if (swKan)                                                 //~vaa1I~//~vabnR~
        if (swNotify)                                              //~vabnI~
	        AG.aUAD2Touch.updateBtnPlayAloneNotify(GCM_KAN,BTN_STATUS_ENABLE_CANCEL);//~vaa1I~
        if (Dump.Y) Dump.println("RACall.callKanTakenPlayAloneNotify rc="+swKan+",swNotify="+swNotify);//~vaa1I~//~vaa2R~//~vabnR~
        return swKan;                                              //~vaa1I~
    }                                                              //~vaa1I~
    //**************************************************************************//~vaa2I~
//  private boolean callKanTakenPlayMatchNotify(int Pplayer,int Peswn)//~vaa2I~//~vaaUR~
    private boolean callKanTakenPlayMatchNotify(int Pplayer,int Peswn,TileData PtdTaken)//~vaaUI~
    {                                                              //~vaa2I~
        int shanten;                                               //~vaa2I~
        //************************                                 //~vaa2I~
        if (Dump.Y) Dump.println("RACall.callKanTakenPlayMatchNotify eswn="+Peswn+",swPlayAloneNotify="+AG.swPlayAloneNotify+",tdTaken="+PtdTaken.toString());//~vaa2I~//~vaaUR~
//      if (!AG.swPlayAloneNotify)                                 //~vaa2I~
//      	return false;                                          //~vaa2I~
        int[] itsH=RS.getItsHandEswn(Peswn);                       //~vaa2I~
        int   ctrH=RS.RSP[Peswn].ctrHand;                          //~vaa2I~
        if (Dump.Y) Dump.println("RACall.callKanTakenPlayMatchNotify itsHand="+Utils.toString(itsH,9));//~vaa2R~
        boolean swKan=false;                                       //~vaa2I~
        boolean swNotify=false;                                    //~vabnI~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~vaa2I~
        {                                                          //~vaa2I~
        	if (itsH[ii]==PAIRCTR_KAN)                             //~vaa2I~
            {                                                      //~vaa2I~
            	swKan=true;                                        //~vaa2I~
                break;                                             //~vaa2I~
            }                                                      //~vaa2I~
		}                                                          //~vaa2I~
        if (chkEarthToAddKan(Peswn,PtdTaken))                      //~vaaUI~
            swKan=true;                                            //~vaaUI~
//      if (swKan)                                                 //~vaa2I~//~vabnR~
        if (swNotify)                                              //~vabnI~
	        AG.aUAD2Touch.updateBtnPlayMatchNotify(GCM_KAN,BTN_STATUS_ENABLE_CANCEL);//~vaa2I~
        if (Dump.Y) Dump.println("RACall.callKanTakenMPlayMatchNotify rc="+swKan);//~vaa2I~
        return swKan;                                              //~vaa2I~
    }                                                              //~vaa2I~
    //**************************************************************************//~vaaUI~
    private boolean chkEarthToAddKan(int Peswn,TileData PtdTaken)  //~vaaUI~
    {                                                              //~vaaUI~
        boolean rc=RS.RSP[Peswn].srchPonToAdd(PtdTaken)>=0;               //~vaaUI~
        if (Dump.Y) Dump.println("RACall.chkEarthToAddKan rc="+rc+",Peswn="+Peswn+",tdKan="+PtdTaken.toString());//~vaaUI~
        return rc;
    }                                                              //~vaaUI~
    //*********************************************************    //~1222I~
    private boolean isKanFixed(int PeswnOther,int Ppos)            //~1222R~
    {                                                              //~1222I~
    	boolean rc=false;                                          //~1222I~
        int shanten;                                               //~1222I~
        //************************                                 //~1222I~
//      int shanten0=Pshanten;  //>0 as condition                  //~1222R~
//      int pos=RAUtils.getPosTile(PtdDiscarded);                  //~1222R~
        int intent=RS.RSP[PeswnOther].intent;                      //~1222I~
        if (Dump.Y) Dump.println("RACall.isKanFixed Ppos="+Ppos+",intent="+Integer.toHexString(intent)+",eswnOther="+PeswnOther+",intent="+Integer.toHexString(intent));//~1222R~//~1223R~
//      int[] itsH=RS.getItsHandEswn(PeswnOther);                  //~1222R~
//      int   ctrH=RS.RSP[PeswnOther].ctrHand;                     //~1222R~
//        int ctrPos=itsH[pos];                                    //~1222R~
//        if (ctrPos<PAIRCTR-1)                                    //~1222R~
//        {                                                        //~1222R~
//            if (Dump.Y) Dump.println("RACall.chkPonKanFixed return by could not make same meld ctrPos="+ctrPos);//~1222R~
//            return 0;                                            //~1222R~
//        }                                                        //~1222R~
        int ctrCall=0;                                             //~1222I~
        if ((intent & INTENT_ALLSAME)!=0)                          //~1222I~
        {                                                          //~1222I~
//            ctrCall=ctrPos+1;           //3 or 4                 //~1222R~
            rc=true;                                               //~1222I~
            if (Dump.Y) Dump.println("RACall.isKanFixed true by ALLSAME intent");//~1222R~
        }                                                          //~1222I~
        else                                                       //~1222I~
        if ((intent & INTENT_SAMECOLOR_ANY)!=0)                    //~1222I~
        {                                                          //~1222I~
            if (RAUtils.isMatchSameColor(true/*allow Word*/,intent,Ppos/CTR_NUMBER_TILE))//~1222I~
            {                                                      //~1222I~
//              ctrCall=isShantenUp(itsH,ctrH,pos,-2,Pshanten) ? ctrPos+1 : 0;//~1222R~
                rc=true;                                           //~1222I~
            	if (Dump.Y) Dump.println("RACall.isKanFixed true by ALLSAME");//~1222R~
            }                                                      //~1222I~
        }                                                          //~1222I~
        else                                                       //~1222I~
        if (RS.RSP[PeswnOther].isFixed1())   //already fixed       //~1222I~
        	rc=true;                                               //~1222I~
//      if (ctrCall==0)                                            //~1222R~
        if (!rc)                                                   //~1222I~
        {                                                          //~1222I~
            if (Ppos>=OFFS_WORDTILE)                                //~1222I~
            {                                                      //~1222I~
//              if (RS.RSP[PeswnOther].isFixed1()   //already fixed//~1222R~
//              ||  RS.RSP[PeswnOther].swAllInHand && RAUtils.chkValueWordTile(pos,PeswnOther)>0    //not yet called and this is yakuhai//~1222R~
//              ||  (intent & (INTENT_SAMECOLOR_ANY|INTENT_ALLSAME))!=0   //honitsu or toitoi//~1222R~
                if (RS.RSP[PeswnOther].swAllInHand && RAUtils.chkValueWordTile(Ppos,PeswnOther)>0    //not yet called and this is yakuhai//~1222I~
                )                                                  //~1222I~
                {                                                  //~1222I~
//                  ctrCall=ctrPos+1;           //3 or 4           //~1222R~
            		if (Dump.Y) Dump.println("RACall.isKanFixed true by WordTile");//~1222I~
                    rc=true;                                       //~1222I~
                }                                                  //~1222I~
            }                                                      //~1222I~
            else                                                   //~1222I~
            {                                                      //~1222I~
//              if (RS.RSP[PeswnOther].isFixed1()   //yakuhai avail//~1222R~
//              || ((intent & INTENT_TANYAO)!=0 && RS.swKuitan && RAUtils.isTanyaoTile(pos) && RS.RSP[PeswnOther].isPairTanyaoAllOrNoPair())//~1222R~
                if ((intent & INTENT_TANYAO)!=0 && RS.swKuitan && RAUtils.isTanyaoTile(Ppos) && RS.RSP[PeswnOther].isPairTanyaoAllOrNoPair()//~1222I~
                )                                                  //~1222I~
                {                                                  //~1222I~
//                    int shantenK=MAX_SHANTEN;                    //~1222R~
//                    if (ctrPos==PAIRCTR)                         //~1222R~
//                    {                                            //~1222R~
//                        shantenK=chkShantenAddCall(PeswnOther,itsH,ctrH,pos,-PAIRCTR);//~1222R~
//                    }                                            //~1222R~
//                    shanten=chkShantenAddCall(PeswnOther,itsH,ctrH,pos,-(PAIRCTR-1));//~1222R~
//                    if ((shantenK==0 || shantenK<shanten) && shantenK<=shanten0)//~1222R~
//                        ctrCall=PAIRCTR_KAN;    //4              //~1222R~
//                    else                                         //~1222R~
//                        if (shanten<shanten0)                    //~1222R~
//                            ctrCall=PAIRCTR;    //3              //~1222R~
                    rc=true;                                       //~1222I~
                }                                                  //~1222I~
            }                                                      //~1222I~
        }                                                          //~1222I~
        if (Dump.Y) Dump.println("RACall.isKanFixed rc="+ctrCall); //~1222R~
        return rc;                                            //~1222I~
    }                                                              //~1222I~
    //*********************************************************    //~1118I~
    private boolean callChii(int PplayerOther,int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~1118I~//~1126R~//~1128R~
    {                                                              //~1118I~
    	int num;                                                   //~1220I~
    	int shanten=RS.getCurrentShanten(PeswnOther);                     //~1118I~//~1126R~//~1129R~
    	int pos=RAUtils.getPosTile(PtdDiscarded);                //~1118I~//~1119R~
    	int[] itsH=RS.getItsHandEswn(PeswnOther);                   //~1118I~//~1126R~
    	int   ctrH=RS.RSP[PeswnOther].ctrHand;                       //~1118I~//~1126R~
    	int   intent=RS.RSP[PeswnOther].intent;                     //~1118I~//~1126R~
        if (Dump.Y) Dump.println("RACall.callChii eswnOther="+PeswnOther+",playerOther="+PplayerOther+",intent="+Integer.toHexString(intent)+",PplayerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",shanten="+shanten+",tdDiscard="+TileData.toString(PtdDiscarded));//~1118R~//~1126R~//~1128R~//~1129R~//~vaaiM~
        int posChii=-1;                                            //~1118R~
	    if (pos>=OFFS_WORDTILE)                                     //~1118R~//~1119R~
        {                                                          //~1119I~
	        if (Dump.Y) Dump.println("RACall.callChii return word tile");//~1119I~
        	return false;                                          //~1119I~
        }                                                          //~1119I~
      if ((TestOption.option2 & TO2_CALL1ST)!=0)                   //~va75I~
      {                                                            //~va75I~
	  	if (Dump.Y) Dump.println("RACall.callChii ignore intent and fix1 by testoption TO2_CALL1ST");//~va75I~
//      posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,13/*make shanten up condition*/,itsH,ctrH,pos);//~va75I~//~vaaLR~
        posChii=selectSeqMeld(PplayerOther,PeswnOther,false/*swTanyao*/,13/*make shanten up condition*/,itsH,ctrH,pos);//~vaaLI~
      }                                                            //~va75I~
      else                                                         //~va75I~
      {                                                            //~va75I~
       if (!RS.swFix2)	//allow SAME_COLOR only for fix2           //~vaarI~
       {                                                           //~vaarI~
        if (RS.RSP[PeswnOther].isFixed1())     //1han already fixed                    //~1118I~//~1119R~//~1126R~//~1129R~
        {                                                      //~1118I~//~1119R~
//          posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~1118R~//~1119R~//~1129R~//~vaaLR~
            posChii=selectSeqMeld(PplayerOther,PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~vaaLI~
        }                                                      //~1118I~//~1119R~
        else                                                   //~1118I~//~1119R~
        if ((intent & INTENT_TANYAO)!=0 && RS.swKuitan && RAUtils.isTanyaoTile(pos) && RS.RSP[PeswnOther].isPairTanyaoAllOrNoPair())//~1118I~//~1119R~//~1126R~//~1217R~
        {                                                      //~1118I~//~1119R~
//          posChii=selectSeqMeld(PeswnOther,true/*swTanyao*/,shanten,itsH,ctrH,pos);//~1118R~//~1119R~//~1128R~//~1129R~//~vaaLR~
            posChii=selectSeqMeld(PplayerOther,PeswnOther,true/*swTanyao*/,shanten,itsH,ctrH,pos);//~vaaLI~
            num=posChii%CTR_NUMBER_TILE;                           //~1220I~
            if (num==TN1 || num==TN7)                  //~1220R~   //~1306R~
            	posChii=-1;                                        //~1220I~
        }                                                      //~1118I~//~1119R~
        else                                                       //~1306I~
        if ((intent & INTENT_CHANTA)!=0 && RS.RSP[PeswnOther].isPairChantaAllOrNoPair())//~1306I~
        {                                                          //~1306I~
//          posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~1306I~//~vaaLR~
            posChii=selectSeqMeld(PplayerOther,PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~vaaLI~
            num=posChii%CTR_NUMBER_TILE;                           //~1306I~
            if (num!=TN1 && num!=TN7)                              //~1306I~
            	posChii=-1;                                        //~1306I~
        }                                                          //~1306I~
       }//not Fix2                                                 //~vaarI~
      }                                                            //~va75I~
//      if (posChii<0)                                             //~1220I~//~vabrR~
        if ((TestOption.option2 & TO2_CALL1ST)==0)                 //~vabrI~
        {                                                          //~1220I~
            if ((intent & INTENT_SAMECOLOR_ANY)!=0)                //~1220I~
            {                                                      //~1220I~
                if (RAUtils.isMatchSameColor(true/*allow Word*/,intent,pos/CTR_NUMBER_TILE))//~1220I~
                {                                                  //~vabrI~
//  	            posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~1220I~//~vaaLR~
    	            posChii=selectSeqMeld(PplayerOther,PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~vaaLI~
                	if (Dump.Y) Dump.println("RACall.callChii chk by SAMECOLOR intent posChii="+posChii);//~1220I~//~vabrR~
                }                                                  //~vabrI~
                else                                               //~vabrI~
	            	posChii=-1;                                    //~vabrI~
            }                                                      //~1220I~
        }                                                          //~1220I~
        if (posChii>=0)                                            //~1118R~
        {                                                          //~1118I~
            issueChii(PplayerOther,PeswnOther,posChii,PtdDiscarded,pos);          //~1118R~//~1119R~//~1126R~//~1129R~
	        if (Dump.Y) Dump.println("RACall.callChii return by issueChii");//~1118R~//~1129R~
            return true;                                           //~1118I~
        }                                                          //~1118I~
        if (Dump.Y) Dump.println("RACall.callChii return no action");//~1118R~
        return false;                                              //~1118I~
    }                                                              //~1118I~
    //*********************************************************    //~va75I~
    //*highlight Chii btn in Notify mode                           //~va75I~
    //*********************************************************    //~va75I~
    private boolean callChiiPlayAloneNotify(int PplayerOther,int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~va75I~
    {                                                              //~va75I~
    	boolean rc=false;                                          //~va75I~
        if (Dump.Y) Dump.println("RACall.callChiiPlayAloneNotify eswnOther="+PeswnOther+",playerOther="+PplayerOther+",PplayerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",tdDiscard="+TileData.toString(PtdDiscarded));//~va75I~
        int pos=RAUtils.getPosTile(PtdDiscarded);                  //~va75I~
	    if (pos>=OFFS_WORDTILE)                                    //~va75I~
        {                                                          //~va75I~
	        if (Dump.Y) Dump.println("RACall.callChiiPlayAloneNotify return false by word tile");//~va75I~
        	return false;                                          //~va75I~
        }                                                          //~va75I~
        int[] itsH=RS.getItsHandEswn(PeswnOther);                  //~va75I~
        if (Dump.Y) Dump.println("RACall.callChiiPlayAloneNotify itsH="+Utils.toString(itsH,9));//~va75I~
        int type=PtdDiscarded.type;                                //~va75R~
        int posS=type*CTR_NUMBER_TILE;                             //~va75I~
        int posE=(type+1)*CTR_NUMBER_TILE;                         //~va75I~
        int ctrCont=1;                                             //~va75I~
        for (int posC=pos-1;posC>=posS;posC--)                         //~va75I~
        	if (itsH[posC]!=0)                                     //~va75I~
            {                                                      //~va75I~
            	ctrCont++;                                         //~va75I~
                if (ctrCont==PAIRCTR)                              //~va75I~
                	break;                                         //~va75I~
            }                                                      //~va75I~
            else                                                   //~va75I~
            	break;                                             //~va75I~
        if (ctrCont<PAIRCTR)                                       //~va75I~
            for (int posC=pos+1;posC<posE;posC++)                      //~va75I~
                if (itsH[posC]!=0)                                 //~va75I~
                {                                                  //~va75I~
                    ctrCont++;                                     //~va75I~
                    if (ctrCont==PAIRCTR)                          //~va75I~
                        break;                                     //~va75I~
                }                                                  //~va75I~
                else                                               //~va75I~
                	break;                                         //~va75I~
    	rc=(ctrCont==PAIRCTR);                                     //~va75I~
        if (rc)                                                    //~va75I~
        	AG.aUAD2Touch.updateBtnPlayAloneNotify(GCM_CHII,BTN_STATUS_ENABLE_CANCEL);//~va75R~
        if (Dump.Y) Dump.println("RACall.callChiiPlayAloneNotify rc="+rc+",ctrCont="+ctrCont);//~va75R~
        return rc;                                                 //~va75I~
    }                                                              //~va75I~
    //*********************************************************    //~vaa2I~
    //*highlight Chii btn in Notify mode in PlayMatchNotify mode   //~vaa2I~
    //*********************************************************    //~vaa2I~
    private boolean callChiiPlayMatchNotify(int Pplayer,int Peswn,TileData PtdDiscarded)//~vaa2I~
    {                                                              //~vaa2I~
    	boolean rc=false;                                          //~vaa2I~
        if (Dump.Y) Dump.println("RACall.callChiiPlayMatchNotify eswn="+Peswn+",player="+Pplayer+",tdDiscard="+TileData.toString(PtdDiscarded));//~vaa2I~
        int pos=RAUtils.getPosTile(PtdDiscarded);                  //~vaa2I~
	    if (pos>=OFFS_WORDTILE)                                    //~vaa2I~
        {                                                          //~vaa2I~
	        if (Dump.Y) Dump.println("RACall.callChiiPlayMatchNotify return false by word tile");//~vaa2I~
        	return false;                                          //~vaa2I~
        }                                                          //~vaa2I~
        int[] itsH=RS.getItsHandEswn(Peswn);                       //~vaa2I~
        if (Dump.Y) Dump.println("RACall.callChiiPlayMatchNotify itsH="+Utils.toString(itsH,9));//~vaa2I~
        int type=PtdDiscarded.type;                                //~vaa2I~
        int posS=type*CTR_NUMBER_TILE;                             //~vaa2I~
        int posE=(type+1)*CTR_NUMBER_TILE;                         //~vaa2I~
        int ctrCont=1;                                             //~vaa2I~
        for (int posC=pos-1;posC>=posS;posC--)                     //~vaa2I~
        	if (itsH[posC]!=0)                                     //~vaa2I~
            {                                                      //~vaa2I~
            	ctrCont++;                                         //~vaa2I~
                if (ctrCont==PAIRCTR)                              //~vaa2I~
                	break;                                         //~vaa2I~
            }                                                      //~vaa2I~
            else                                                   //~vaa2I~
            	break;                                             //~vaa2I~
        if (ctrCont<PAIRCTR)                                       //~vaa2I~
            for (int posC=pos+1;posC<posE;posC++)                  //~vaa2I~
                if (itsH[posC]!=0)                                 //~vaa2I~
                {                                                  //~vaa2I~
                    ctrCont++;                                     //~vaa2I~
                    if (ctrCont==PAIRCTR)                          //~vaa2I~
                        break;                                     //~vaa2I~
                }                                                  //~vaa2I~
                else                                               //~vaa2I~
                	break;                                         //~vaa2I~
    	rc=(ctrCont==PAIRCTR);                                     //~vaa2I~
        if (rc)                                                    //~vaa2I~
	        AG.aUAD2Touch.stopAuto2TouchPlayMatchNotify(GCM_CHII); //~vaa2I~
        if (Dump.Y) Dump.println("RACall.callChiiPlayMatchNotify rc="+rc+",ctrCont="+ctrCont);//~vaa2I~
        return rc;                                                 //~vaa2I~
    }                                                              //~vaa2I~
    //**********************************************************************************    //~1119I~//~1220R~
    //*For Robot                                                   //~vaarR~
    //*chk Meld selectable and shantenUp                           //~vaarI~
    //**********************************************************************************//~1220I~
//  private int selectSeqMeld(int PeswnCaller,boolean PswTanyao,int Pshanten,int[] PitsHand,int PctrHand,int Ppos)//~1119I~//~1128R~//~vaaLR~
    private int selectSeqMeld(int PplayerCaller,int PeswnCaller,boolean PswTanyao,int Pshanten,int[] PitsHand,int PctrHand,int Ppos)//~vaaLI~
    {                                                              //~1119I~
		int ctrReach=AG.aPlayers.getCtrReachedPlayer(-1/*Player to be excluded*/);//~vaarI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeld player="+PplayerCaller+",eswn="+PeswnCaller+",swTanyao="+PswTanyao+",pos="+Ppos+",shanten="+Pshanten+",itsHand="+Utils.toString(PitsHand,9));//~1119I~//~1129R~//~vaaLR~
        int top=(Ppos/CTR_NUMBER_TILE)*CTR_NUMBER_TILE;            //~1119I~
        int end=top+CTR_NUMBER_TILE;                               //~1119I~
        int minShanten=Pshanten;                                   //~1119I~
        int ctr=1;                                                 //~1119I~
        int posMeld=-1;                                            //~1119I~
        boolean rc;                                                  //~1128I~
//      Point pt=new Point(minShanten,-posMeld);                   //~1119I~//~1128R~
//      if (Ppos-2>=top && PitsHand[Ppos-2]!=0 && PitsHand[Ppos-1]!=0)  //pos is right edge of meld//~1119I~//~vaasR~
//      if (Ppos-2>=top && Ppos-1<end && PitsHand[Ppos-2]!=0 && PitsHand[Ppos-1]!=0)  //pos is right edge of meld//~vaasI~//~vaaLR~
        if (Ppos-2>=top               && PitsHand[Ppos-2]!=0 && PitsHand[Ppos-1]!=0)  //pos is right edge of meld//~vaaLI~
        {                                                          //~1119I~
        	posMeld=Ppos-2;                                        //~1119I~
//      	if (!PswTanyao || (posMeld>0 && (posMeld+2)<8))        //~1119I~//~vaasR~
        	if (!PswTanyao || (posMeld>top && (posMeld+2)<top+TN9))//~vaasI~
            {                                                      //~1119I~
                PitsHand[Ppos-2]--; PitsHand[Ppos-1]--;            //~1119I~
//              isShantenUpCall(PitsHand,PctrHand-2,pt);       //~1119I~//~1128R~
                rc=isShantenUpCall(PeswnCaller,PitsHand,PctrHand-2,Pshanten);//~1128R~
                if (rc)                                            //~vaarI~
                	if (!chkWinTileCtr(PeswnCaller,Pshanten,ctrReach,PitsHand,PctrHand-2))//~vaarR~
                		rc=false;                                  //~vaarI~
                PitsHand[Ppos-2]++; PitsHand[Ppos-1]++;            //~1119I~
                if (!rc)                                           //~1128I~
                	posMeld=-1;                                    //~1128I~
            }                                                      //~1119I~
        }                                                          //~1119I~
        int posMeldR=posMeld;                                       //~vaaLI~
        posMeld=-1;                                                //~vaaLI~
//    if (posMeld<0)                                               //~va8sI~//~vaaLR~
//      if (Ppos+2< end && PitsHand[Ppos+2]!=0 && PitsHand[Ppos+1]!=0)   //pos is left edge of meld//~1119I~//~vaasR~
//      if (Ppos+2< end && Ppos+1>=top && PitsHand[Ppos+2]!=0 && PitsHand[Ppos+1]!=0)   //pos is left edge of meld//~vaasI~//~vaaLR~
        if (Ppos+2< end                && PitsHand[Ppos+2]!=0 && PitsHand[Ppos+1]!=0)   //pos is left edge of meld//~vaaLI~
        {                                                          //~1119I~
        	posMeld=Ppos;                                          //~1119I~
//      	if (!PswTanyao || (posMeld>0 && (posMeld+2)<8))        //~1119I~//~vaasR~
        	if (!PswTanyao || (posMeld>top && (posMeld+2)<top+TN9))//~vaasI~
            {                                                      //~1119I~
                PitsHand[Ppos+2]--; PitsHand[Ppos+1]--;            //~1119I~
                rc=isShantenUpCall(PeswnCaller,PitsHand,PctrHand-2,Pshanten);       //~1119I~//~1128R~
                if (rc)                                            //~vaarI~
                	if (!chkWinTileCtr(PeswnCaller,Pshanten,ctrReach,PitsHand,PctrHand-2))//~vaarR~
                		rc=false;                                  //~vaarI~
                PitsHand[Ppos+2]++; PitsHand[Ppos+1]++;            //~1119I~
                if (!rc)                                           //~1128I~
                	posMeld=-1;                                    //~1128I~
            }                                                      //~1119I~
        }                                                          //~1119I~
        int posMeldL=posMeld;                                      //~vaaLI~
        posMeld=-1;                                                //~vaaLI~
//    if (posMeld<0)                                               //~va8sI~//~vaaLR~
        if (Ppos-1>=top && PitsHand[Ppos-1]!=0 && Ppos+1<end && PitsHand[Ppos+1]!=0)//~1119I~
        {                                                          //~1119I~
        	posMeld=Ppos-1;                                        //~1119I~
//      	if (!PswTanyao || (posMeld>0 && (posMeld+2)<8))        //~1119I~//~vaasR~
        	if (!PswTanyao || (posMeld>top && (posMeld+2)<top+TN9))//~vaasI~
            {                                                      //~1119I~
                PitsHand[Ppos-1]--; PitsHand[Ppos+1]--;            //~1119I~
                rc=isShantenUpCall(PeswnCaller,PitsHand,PctrHand-2,Pshanten);       //~1119I~//~1128R~
                if (rc)                                            //~vaarI~
                	if (!chkWinTileCtr(PeswnCaller,Pshanten,ctrReach,PitsHand,PctrHand-2))//~vaarR~
                		rc=false;                                  //~vaarI~
                PitsHand[Ppos-1]++; PitsHand[Ppos+1]++;            //~1119I~
                if (!rc)                                           //~1128I~
                	posMeld=-1;                                    //~1128I~
            }                                                      //~1119I~
        }                                                          //~1119I~
        int posMeldM=posMeld;                                      //~vaaLI~
        posMeld=selectSeqMeldDora(PplayerCaller,PswTanyao,PitsHand,top,posMeldR,posMeldL,posMeldM);    //consider dora//~vaaLR~
//      posMeld=pt.y;                                              //~1119I~//~1128R~
        if (Dump.Y) Dump.println("RACall.selectSeqMeld posMeld="+posMeld);//~1119I~//~1129R~
        return posMeld;                                            //~1119I~//~1129R~
    }                                                              //~1119I~
    //**********************************************************************************//~vaaLI~
    private int selectSeqMeldDora(int Pplayer,boolean PswTanyao,int[] PitsHand,int Ptop,int PposMeldR,int PposMeldL,int PposMeldM)//~vaaLR~
    {                                                              //~vaaLI~
        int pos,rc=-1,ctrR=0,ctrM=0,ctrL=0;                        //~vaaLR~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldDora player="+Pplayer+",swTanyao="+PswTanyao+",top="+Ptop+",posMeldR="+PposMeldR+",posMeldL="+PposMeldL+",posMeldM="+PposMeldM);//~vaaLR~
        if (PposMeldR!=-1)                                         //~vaaLI~
        {                                                          //~vaaLI~
            if (isDora(Pplayer,PposMeldR))                          //~vaaLI~
            	ctrR++;                                            //~vaaLI~
            if (isDora(Pplayer,PposMeldR+1))                        //~vaaLI~
            	ctrR++;                                            //~vaaLI~
        }                                                          //~vaaLI~
        if (PposMeldM!=-1)                                         //~vaaLI~
        {                                                          //~vaaLI~
            if (isDora(Pplayer,PposMeldM))                          //~vaaLI~
            	ctrM++;                                            //~vaaLI~
            if (isDora(Pplayer,PposMeldM+2))                        //~vaaLI~
            	ctrM++;                                            //~vaaLI~
        }                                                          //~vaaLI~
        if (PposMeldL!=-1)                                         //~vaaLI~
        {                                                          //~vaaLI~
            if (isDora(Pplayer,PposMeldL+1))                        //~vaaLI~
            	ctrL++;                                            //~vaaLI~
            if (isDora(Pplayer,PposMeldL+2))                        //~vaaLI~
            	ctrL++;                                            //~vaaLI~
        }                                                          //~vaaLI~
	    if (Dump.Y) Dump.println("RACall.selectSeqMeldDora ctrR="+ctrR+",ctrM="+ctrM+",ctrL="+ctrL);//~vaaLI~
        if (PposMeldM!=-1)                                         //~vaaLI~
        {                                                          //~vaaLI~
            if (PposMeldR!=-1 && PposMeldL!=-1) //xxXxx            //~vaaLI~
            {                                                      //~vaaLI~
                if (ctrR<ctrL)                                     //~vaaLI~
                    rc=PposMeldL;                                  //~vaaLI~
                else                                               //~vaaLI~
                if (ctrR>ctrL)                                     //~vaaLI~
                    rc=PposMeldR;                                  //~vaaLI~
                else                                               //~vaaLI~
                    rc=PposMeldR;                                  //~vaaLI~
            }                                                      //~vaaLI~
            else                                                   //~vaaLI~
            if (PposMeldR!=-1)                   //xxXx0           //~vaaLR~
            {                                                      //~vaaLI~
//              if (ctrM>ctrR)                                     //~vaaLI~//~vab3R~
                if (ctrM>=ctrR)	//kanchan priority                 //~vab3I~
                    rc=PposMeldM;                                  //~vaaLI~
                else                                               //~vaaLI~
                    rc=PposMeldR;                                  //~vaaLI~
            }                                                      //~vaaLI~
            else                                                   //~vaaLI~
            if (PposMeldL!=-1)                   //0xXxx           //~vaaLR~
            {                                                      //~vaaLI~
//              if (ctrM>ctrL)                                     //~vaaLI~//~vab3R~
                if (ctrM>=ctrL)   //kanchan priority               //~vab3I~
                    rc=PposMeldM;                                  //~vaaLI~
                else                                               //~vaaLI~
                    rc=PposMeldL;                                  //~vaaLI~
            }                                                      //~vaaLI~
            else                                                   //~vaaLI~
                rc=PposMeldM;                                       //~vaaLI~
        }                                                          //~vaaLI~
        else                                                       //~vaaLI~
        if (PposMeldR!=-1)                                         //~vaaLR~
        	rc=PposMeldR;                                          //~vaaLI~
        else                                                       //~vaaLI~
        	rc=PposMeldL;                                          //~vaaLI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldDora exit rc="+rc);//~vaaLR~
        return rc;                                            //~vaaLI~
    }                                                              //~vaaLI~
    //***********************************************************************//~vaaLI~
    private boolean isDora(int Pplayer,int Ppos)                   //~vaaLR~
    {                                                              //~vaaLI~
    	boolean rc=RADS.isDoraOpenCurrent(Ppos);                   //~vaaLI~
        if (!rc)                                                   //~vaaLI~
//        	rc=AG.aRADSEval.isHavingRed5(Pplayer,Ppos)!=null;            //~vaaLI~//~vab3R~
          	rc=AG.aRADSEval.isHavingRed5(Pplayer,Ppos,false/*PswSelectNonRed5*/)!=null;//~vab3R~
        if (Dump.Y) Dump.println("RACall.isDora pos="+Ppos+",rc="+rc);//~vaaLI~
        return rc;
    }                                                              //~vaaLI~
    //***********************************************************************//~vab4I~
    private boolean isDora(int Ppos,TileData Ptd)                  //~vab4I~
    {                                                              //~vab4I~
    	boolean rc=RADS.isDoraOpenCurrent(Ppos) || Ptd.isRed5();   //~vab4I~
        if (Dump.Y) Dump.println("RACall.isDora pos="+Ppos+",rc="+rc+",td="+Ptd.toString());//~vab4I~
        return rc;                                                 //~vab4I~
    }                                                              //~vab4I~
    //***********************************************************************//~vaarI~
    //*For  Robot Chii,chk win tile ctr if shanten1 is shantenUp and ctrReach=1//~vaarR~
    //*pao is checked                                              //~vaarI~
    //***********************************************************************//~vaarI~
    private boolean chkWinTileCtr(int Peswn,int Pshanten,int PctrReach,int[] PitsHand,int PctrHand)//~vaarR~
    {                                                              //~vaarI~
        boolean rc=false;                                          //~vaarI~
        if (Dump.Y) Dump.println("RACall.chkWinTileCtr for Chii eswn="+Peswn+",shanten="+Pshanten+",ctrReach="+PctrReach+",itsHand="+Utils.toString(PitsHand,9));//~vaarR~
        if (Pshanten!=1 || PctrReach!=1)                            //~vaarI~
        	return true;	//no chk ctrWinTile                    //~vaarI~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~vaarI~
        {                                                          //~vaarI~
        	if (PitsHand[ii]==0)                                   //~vaarI~
            	continue;                                          //~vaarI~
    		if (!chkCtrDiscardable(Peswn,PitsHand,ii,PctrHand))	//pao/openreach chk//~vaarI~
            	continue;                                          //~vaarI~
            PitsHand[ii]--;                                        //~vaarI~
        	int ctr=AG.aRAReach.getWinListTileCtr(PitsHand,PctrHand-1);//~vaarR~
	        if (Dump.Y) Dump.println("RACall.chkWinTileCtr drop pos="+ii+",ctr="+ctr);//~vaarI~
            PitsHand[ii]++;                                        //~vaarI~
        	rc=ctr>HV_CTR_WINTILE_FOR_PONCHII_OTHERREACH; //3 // if dora>=0 call pon/chii if shanten=1 and anyone called reach//~vaarI~
            if (rc)                                                //~vaarI~
            	break;                                             //~vaarI~
        }                                                          //~vaarI~
        if (Dump.Y) Dump.println("RACall.chkWinTileCtr for Chii rc="+rc);//~vaarR~
        return rc;                                                 //~vaarI~
    }                                                              //~vaarI~
    //***********************************************************************//~vaarI~
    //*For Robot Pon,chk win tile ctr and shantenUp and ctrReach=1 //~vaarI~
    //*pao is checked                                              //~vaarI~
    //***********************************************************************//~vaarI~
    private boolean chkWinTileCtrPon(int Peswn,int PnewShanten,int PctrReach,int Ppos,int[] PitsHand,int PctrHand)//~vaarR~
    {                                                              //~vaarI~
        boolean rc=false;                                          //~vaarI~
        if (Dump.Y) Dump.println("RACall.chkWinTileCtrPon eswn="+Peswn+",newShanten="+PnewShanten+",ctrReach="+PctrReach+",pos="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~vaarR~
        if (PnewShanten!=0 || PctrReach!=1)                        //~vaarI~
        	return true;	//no chk ctrWinTile                    //~vaarI~
        PitsHand[Ppos]-=PAIRCTR-1;                                 //~vaarI~
        int ctrHand=PctrHand-(PAIRCTR-1);                              //~vaarI~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~vaarI~
        {                                                          //~vaarI~
        	if (PitsHand[ii]==0)                                   //~vaarI~
            	continue;                                          //~vaarI~
    		if (!chkCtrDiscardable(Peswn,PitsHand,ii,PctrHand))	//pao/openreach chk//~vaarI~
            	continue;                                          //~vaarI~
            PitsHand[ii]--;                                        //~vaarI~
        	int ctr=AG.aRAReach.getWinListTileCtr(PitsHand,ctrHand-1);//~vaarR~
	        if (Dump.Y) Dump.println("RACall.chkWinTileCtrPon pos="+ii+",ctr="+ctr);//~vaarR~
            PitsHand[ii]++;                                        //~vaarI~
        	rc=ctr>HV_CTR_WINTILE_FOR_PONCHII_OTHERREACH; //3 // if dora>=0 call pon/chii if shanten=1 and anyone called reach//~vaarI~
            if (rc)                                                //~vaarI~
            	break;                                             //~vaarI~
        }                                                          //~vaarI~
        PitsHand[Ppos]+=PAIRCTR-1;                                 //~vaarI~
        if (Dump.Y) Dump.println("RACall.chkWinTileCtrPon rc="+rc);//~vaarR~
        return rc;                                                 //~vaarI~
    }                                                              //~vaarI~
    //***********************************************************************//~1118I~
    //*chk for call Chii, no need to chk 7pair and 13orphan        //~1128I~//~1220R~
    //***********************************************************************//~1128I~
    private boolean isShantenUpCall(int PeswnPlayer,int[] PitsHand,int PctrHand,int Pshanten)//~1118I~//~1128R~
    {                                                              //~1118I~
        if (Dump.Y) Dump.println("RACall.isShantenUpCall Pshanten="+Pshanten);//~1128I~
    	if (!chkCtrDiscardable(PeswnPlayer,PitsHand,-1/*pos of PctrAdd*/,0/*PctrAdd*/))//~1128R~
        	return false;                                          //~1128I~
//      int shanten=AG.aShanten.getShantenMin(PitsHand,PctrHand);  //~1118I~//~1128R~
        int shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand);//~1128I~
        boolean rc=shanten<Pshanten;                               //~1118I~//~1128R~
        if (Dump.Y) Dump.println("RACall.isShantenUpCall rc="+rc+",Pshanten="+Pshanten+",shanten="+shanten);//~1118I~//~1128R~
        return rc;                                                 //~1118I~
    }                                                              //~1118I~
    //***********************************************************************//~1220I~
    private boolean isShantenUp(int[] PitsHand,int PctrHand,int Ppos,int Pctr,int Pshanten)//~1220I~
    {                                                              //~1220I~
        boolean rc=false;                                          //~1220I~
        PitsHand[Ppos]+=Pctr;                                      //~1220I~
        if (PitsHand[Ppos]>=0 && PitsHand[Ppos]<=PIECE_DUPCTR)     //~1220I~
        	rc=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand+Pctr)<Pshanten;//~1220I~
        PitsHand[Ppos]-=Pctr;                                      //~1220I~
        if (Dump.Y) Dump.println("RACall.isShantenUp rc="+rc+",Pshanten="+Pshanten+",pos="+Ppos+",ctr="+Pctr);//~1220I~
        return rc;                                                 //~1220I~
    }                                                              //~1220I~
    //***********************************************************************//~1126I~
    //*get shanten in case of tile added/removed after chk discardable//~1220I~
    //***********************************************************************//~1220I~
    private int chkShantenAddCall(int PeswnPlayer,int[] PitsHand,int PctrHand,int Ppos,int PctrAdd)//~1126I~//~1128R~
    {                                                              //~1126I~
    	int shanten;
//      int ctrDiscardable=getCtrDiscardable(PeswnPlayer,PitsHand,Ppos,PctrAdd);//~1126R~
//      if (ctrDiscardable<HV_CTR_REMAINING_DISCARDABLE)   //if <5 do not pon/chii//~1126R~
        if (!chkCtrDiscardable(PeswnPlayer,PitsHand,Ppos,PctrAdd)) //~1126I~
            shanten=MAX_SHANTEN;                                   //~1126I~
        else                                                       //~1126I~
			shanten=RAUtils.getShantenAddCall(PitsHand,PctrHand,Ppos,PctrAdd);//~1126I~//~1128R~
        if (Dump.Y) Dump.println("RACall.chkShantenAddCall shanten="+shanten);//~1126I~//~1128R~
        return shanten;                                            //~1126I~
	}                                                              //~1126I~
    //***********************************************************************//~1126I~
    private int getCtrDiscardable(int PeswnPlayer,int[] PitsHand,int Ppos,int PctrAdd)//~1126R~
    {                                                              //~1126I~
    	int ctrDiscardable=0;                                      //~1126I~
        if (RS.isDiscardableAll())                                 //~1126I~//~1128I~
            return 0;                                              //~1128I~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~1126I~
        {                                                          //~1126I~
        	int ctr=PitsHand[ii];                                  //~1126I~
            if (ii==Ppos)                                         //~1126I~
            	ctr+=PctrAdd;                                      //~1126I~
            if (ctr>0)                                             //~1126R~
            {                                                      //~1126I~
	        	if (RAD.isDiscardable(PeswnPlayer,ii))         //~1126I~
                	ctrDiscardable+=ctr;                          //~1126I~
            }                                                      //~1126I~
        }                                                          //~1126I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDiscardable eswn="+PeswnPlayer+",ctrDiscardable="+ctrDiscardable);//~1126R~//~1128R~
        return ctrDiscardable;                                     //~1126R~
	}                                                              //~1126I~
    //***********************************************************************//~1126I~
    //*chk discadable tile ctr is kept for pao/openreach           //~1222I~
    //***********************************************************************//~1222I~
    private boolean chkCtrDiscardable(int PeswnPlayer,int[] PitsHand,int Ppos,int PctrAdd)//~1126R~
    {                                                              //~1126I~
    	boolean rc=false;                                          //~1126R~
        if (RS.isDiscardableAll())                                 //~1128I~
            return true;                                           //~1128I~
        int ctrDiscardable=0;
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~1126I~
        {                                                          //~1126I~
        	int ctr=PitsHand[ii];                                  //~1126I~
            if (ii==Ppos)                                          //~1126I~
            	ctr+=PctrAdd;   //subtract to be discarded //~1126I~//~1128R~
            if (ctr>0)                                             //~1126I~
            {                                                      //~1126I~
	        	if (RAD.isDiscardable(PeswnPlayer,ii))             //~1126I~
                {                                                  //~1126I~
                	ctrDiscardable+=ctr;                           //~1126I~
					if (ctrDiscardable>=HV_CTR_REMAINING_DISCARDABLE)   //if <5 do not pon/chii//~1126M~
                    {                                              //~1126M~
                    	rc=true;                                   //~1126M~
                        break;                                     //~1126M~
                    }                                              //~1126M~
                }                                                  //~1126I~
            }                                                      //~1126I~
        }                                                          //~1126I~
        if (Dump.Y) Dump.println("RADSmart.chkCtrDiscardable rc="+rc+",eswn="+PeswnPlayer+",ctrDiscardable="+ctrDiscardable);//~1126R~
        return rc;                                                 //~1126R~
	}                                                              //~1126I~
    //***********************************************************************//~1119I~//~1124I~
    private void issueKan(int PkanType,int Pplayer,int PeswnPlayer,TileData[] PtdsKan)  //~1119R~//~1124R~
    {                                                             //~1119I~//~1124I~
        if (Dump.Y) Dump.println("RACall.issueKan kanType="+PkanType+",player="+Pplayer+",eswnPlayer="+PeswnPlayer+",td="+TileData.toString(PtdsKan));//~1119I~//~1124R~
        Tiles.setFlag(PtdsKan,TDF_ROBOT_SELECTION);                                //~1124I~
        Robot r=RS.RSP[PeswnPlayer].robot;                               //~1124I~
	    String msg=AG.aUserAction.UAK.makeMsgDataToServer(Pplayer,PtdsKan,PAIRCTR_KAN);//~1124I~
        r.sendToServer(false/*waiterBlock*/,GCM_KAN,PeswnPlayer,msg);//~1124R~
   }                                                              //~1119I~//~1124I~
    //***********************************************************************//~1119I~//~1124R~
    private void issuePon(int PplayerOther,int PeswnOther,int Ppos,TileData PtdDiscarded)  //~1119R~//~1124R~//~1126R~
    {                                                              //~1119I~//~1124R~
        if (Dump.Y) Dump.println("RACall.issuePon playerOther="+PplayerOther+",eswnOther="+PeswnOther+",td="+PtdDiscarded.toString());//~1119I~//~1124R~//~1126R~
		TileData[] tdsPair=makePon(PeswnOther,Ppos,PtdDiscarded);//~1126I~
        Tiles.setFlag(tdsPair,TDF_ROBOT_SELECTION);                //~1129I~
        Robot r=RS.RSP[PeswnOther].robot;                         //~1126I~
	    String msg=AG.aUserAction.UAP.makeMsgDataToServer(PplayerOther,tdsPair,PAIRCTR);//~1126R~
        r.sendToServer(false/*waiterBlock*/,GCM_PON,PeswnOther,msg);//~1126I~
    }                                                              //~1119I~//~1124R~
    //*********************************************************    //~1118I~//~1119M~
    private void issueChii(int PplayerOther,int PeswnOther,int PposChiiStart,TileData PtdDiscarded,int PposDiscarded)//~1118I~//~1119R~//~1126R~//~1129R~
    {                                                              //~1118I~//~1119M~
        if (Dump.Y) Dump.println("RACall.issueChii eswnOther="+PeswnOther+",posChiiStart="+PposChiiStart+",posDiscarded="+PposDiscarded+",td="+PtdDiscarded.toString());//~1129I~
		TileData[] tdsPair=makeChii(PeswnOther,PposChiiStart,PtdDiscarded,PposDiscarded);//~1129I~
        Tiles.setFlag(tdsPair,TDF_ROBOT_SELECTION);                //~1129I~
        Robot r=RS.RSP[PeswnOther].robot;                          //~1129I~
	    String msg=AG.aUserAction.UAC.makeMsgDataToServer(PplayerOther,tdsPair,PAIRCTR);//~1129I~
        r.sendToServer(false/*waiterBlock*/,GCM_CHII,PeswnOther,msg);//~1129I~
    }                                                              //~1118I~//~1119M~
	//******************************************************************************//~1124M~
    private TileData[] makeKanAdd(int PeswnPlayer,TileData[] PtdsPon,int PposKan)//~1124M~
    {                                                              //~1124M~
		TileData[] tdsPair=new TileData[PAIRCTR_KAN];              //~1124M~
        System.arraycopy(PtdsPon,0,tdsPair,0,PAIRCTR);             //~1124M~
        TileData tdAdd=RAUtils.selectTileInHand(PeswnPlayer,PposKan);//~1124M~
        tdAdd.setKanAddedTile();                                   //~1124M~
        tdsPair[PAIR_KAN_ADDPOS]=tdAdd;                            //~1124M~
	    Tiles.setFlag(tdsPair,TDF_KAN_ADD);                        //~1124M~
        if (Dump.Y) Dump.println("RACall.makeKanAdd eswnPlayer="+PeswnPlayer+",posKan="+PposKan+",tdsKan="+TileData.toString(tdsPair));//~1124M~
        return tdsPair;                                            //~1124M~
    }                                                              //~1124M~
	//******************************************************************************//~1124M~
    private TileData[] makeKanTaken(int PeswnPlayer,int PposKan)   //~1124R~
    {                                                              //~1124M~
		TileData[] tdsPair=new TileData[PAIRCTR_KAN];              //~1124M~
        int ctr=0;                                                 //~1124M~
        for (TileData td:tdsHand)                                  //~1124M~
        {                                                          //~1124M~
        	int pos=RAUtils.getPosTile(td);                        //~1124M~
            if (pos==PposKan)                                      //~1124R~
            {                                                      //~1124M~
            	if (ctr<PAIRCTR_KAN)                               //~1124M~
                	tdsPair[ctr++]=td;                             //~1124M~
            }                                                      //~1124M~
        }                                                          //~1124M~
        Tiles.setFlag(tdsPair,TDF_KAN_TAKEN);                      //~1124M~
        if (Dump.Y) Dump.println("RACall.makeKanTaken eswnPlayer="+PeswnPlayer+",posKan="+PposKan+",tdsKan="+TileData.toString(tdsPair));//~1124M~
        return tdsPair;                                            //~1124M~
    }                                                              //~1124M~
	//******************************************************************************//~1124I~
    private TileData[] makeKanRiver(int PeswnPlayer,int PposKan,TileData PtdDiscarded)//~1124R~//~1129R~
    {                                                              //~1124I~
		TileData[] tdsPair=new TileData[PAIRCTR_KAN];              //~1124I~
        int ctr=0;                                                 //~1124I~
        for (TileData td:tdsHand)                                  //~1124I~
        {                                                          //~1124I~
        	int pos=RAUtils.getPosTile(td);                        //~1124I~
            if (pos==PposKan)                                      //~1124R~
            {                                                      //~1124I~
            	if (ctr<PAIRCTR_KAN-1)                             //~1124I~
                	tdsPair[ctr++]=td;                             //~1124I~
            }                                                      //~1124I~
        }                                                          //~1124I~
        tdsPair[ctr++]=PtdDiscarded;                                 //~1124I~//~1129R~
        PtdDiscarded.setTakenRiver();	//tdLastDiscarded is different instance by sendmsg//~1129I~
        Tiles.setFlag(tdsPair,TDF_KAN_RIVER);                      //~1124I~
        if (Dump.Y) Dump.println("RACall.makeKanRiver eswnPlayer="+PeswnPlayer+",posKan="+PposKan+",tdDiscarded="+PtdDiscarded.toString()+",tdsKan="+TileData.toString(tdsPair));//~1124I~//~1129R~
        return tdsPair;                                            //~1124I~
    }                                                              //~1124I~
	//******************************************************************************//~1126I~
    private TileData[] makePon(int PeswnPlayer,int Ppos,TileData PtdDiscarded)//~1126I~//~1129R~
    {                                                              //~1126I~
		TileData[] tdsPair=new TileData[PAIRCTR];                  //~1126I~
        int ctr=0;                                                 //~1126I~
        for (TileData td:tdsHand)                                  //~1126I~
        {                                                          //~1126I~
        	int pos=RAUtils.getPosTile(td);                        //~1126I~
            if (pos==Ppos)                                         //~1126I~
            {                                                      //~1126I~
            	if (ctr<PAIRCTR-1)                                 //~1126I~
                	tdsPair[ctr++]=td;                             //~1126I~
            }                                                      //~1126I~
        }                                                          //~1126I~
        tdsPair[ctr++]=PtdDiscarded;                                 //~1126I~//~1129R~
        PtdDiscarded.setTakenRiver();	//tdLastDiscarded is different instance by sendmsg//~1129I~
        Tiles.setFlag(tdsPair,TDF_ROBOT_SELECTION);                //~1126I~
        if (Dump.Y) Dump.println("RACall.makePon eswnPlayer="+PeswnPlayer+",pos="+Ppos+",tdDiscarded="+PtdDiscarded.toString()+",tdsPair="+TileData.toString(tdsPair));//~1126I~//~1129R~
        return tdsPair;                                            //~1126I~
    }                                                              //~1126I~
 	//******************************************************************************//~1129I~
    private TileData[] makeChii(int PeswnPlayer,int PposChiiStart,TileData PtdDiscarded,int PposDiscarded)//~1129I~
    {                                                              //~1129I~
		TileData[] tdsPair=new TileData[PAIRCTR];                  //~1129I~
        int ctrPair=0;                                             //~1129I~
        for (int ii=PposChiiStart;ii<PposChiiStart+PAIRCTR;ii++)           //~1129I~
        {                                                          //~1129I~
        	if (ii!=PposDiscarded)                                   //~1129I~
//      		tdsPair[ctrPair++]=RAUtils.selectTileInHand(PeswnPlayer,ii);//~1129I~//~vab3R~
        		tdsPair[ctrPair++]=RAUtils.selectTileInHandRed5(PeswnPlayer,ii);//~vab3I~
        }                                                          //~1129I~
        tdsPair[ctrPair++]=PtdDiscarded;                           //~1129I~
        PtdDiscarded.setTakenRiver();	//tdLastDiscarded is different instance by sendmsg//~1129I~
        if (Dump.Y) Dump.println("RACall.makeChii eswnPlayer="+PeswnPlayer+",posstart="+PposChiiStart+",posDiscarded="+PposDiscarded+",tdDiscarded="+PtdDiscarded.toString()+",tdsPair="+TileData.toString(tdsPair));//~1129I~
        return tdsPair;                                            //~1129I~
    }                                                              //~1129I~
 	//******************************************************************************//~1306I~
 	//*for Robot                                                   //~vaaeR~
 	//*evaluate not timing but value of call                       //~vaaeI~
 	//******************************************************************************//~va8pI~
    private boolean isTimeToCall(int Pplayer/*caller*/,int Peswn/*caller*/,int Paction,TileData Ptd/*KanTaken or Discarded*/,int PmyShanten,int Pintent)//~1306R~
    {                                                              //~1306I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall eswn="+Peswn+",action="+Paction+",shanten="+PmyShanten+",intent=x"+Integer.toHexString(Pintent)+",td="+Utils.toString(Ptd));//~1306I~//~va8pR~
        if ((TestOption.option2 & TO2_CALL1ST)!=0)                  //~va75I~
        {                                                          //~va75I~
	        if (Dump.Y) Dump.println("RACall.isTimeToCall return true by testoption TO2_CALL1ST");//~va75I~
            return true;                                           //~va75I~
        }                                                          //~va75I~
    	boolean rc=false;                                          //~1306I~
    	int shanten=RS.getCurrentShanten(Peswn);                   //~vaaiI~
		int ctrReach=AG.aPlayers.getCtrReachedPlayer(Pplayer);     //~1306I~
		int ctrDora=RADS.getCtrDoraInHandAndEarth(Peswn);           //~1313I~
	    int ctrTaken=RS.RSP[Peswn].ctrTaken;                       //~1306I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall shanten="+shanten+",ctrReach="+ctrReach+",ctrDora="+ctrDora+",ctrTaken="+ctrTaken);//~vaaiI~//~vaatR~
      	int pos=RAUtils.getPosTile(Ptd);                           //~vaaHI~
        for (;;)                                                   //~1306I~
        {                                                          //~1306I~
//      	int pos=RAUtils.getPosTile(Ptd);                               //~1306I~//~vaaHR~
        	if ((Paction==GCM_PON || Paction==GCM_KAN) && pos>=OFFS_WORDTILE && RADS.isDoraOpen(Ptd))        //call at first if drora//~1306R~
            {                                                      //~1306I~
        		rc=true;                                           //~1306I~
            	break;                                             //~1306I~
            }                                                      //~1306I~
            int v=RAUtils.chkValueWordTile(pos,Peswn);             //~1313I~
			if (Paction==GCM_PON)                                  //~1313I~
            {                                                      //~1313I~
        		if (pos>=OFFS_WORDTILE)                            //~1313I~
                {                                                  //~1313I~
                    if (v/2>1)/*2han*/                             //~1313I~
                    {                                              //~1313I~
                        rc=true;                                   //~1313I~
                        break;                                     //~1313I~
                    }                                              //~1313I~
                    if (RS.RSP[Peswn].getCtrValueWordSameAndPairInHand()>1) //*2 pairs of valueword in hand//~1306R~//~1313I~
                    {                                              //~1306R~//~1313I~
                        rc=true;                                   //~1306R~//~1313I~
                        break;                                     //~1306R~//~1313I~
                    }                                              //~1306R~//~1313I~
                    if (Peswn==ESWN_E && PmyShanten<=HV_PARENT_1STCALL_SHANTEN) //<=3 //~1306R~//~1313I~
                    {                                              //~1306R~//~1313I~
        				if (Dump.Y) Dump.println("RACall.isTimeToCall return true by shanten="+PmyShanten+"<="+HV_PARENT_1STCALL_SHANTEN);//~vabfI~
                        rc=true;                                   //~1306R~//~1313I~
                        break;                                     //~1306R~//~1313I~
                    }                                              //~1306R~//~1313I~
            		if (!RS.RSP[Peswn].swAllInHand)         //already called//~va8pI~
		      	       	if (RS.RSP[Peswn].isFixed1())     //1han already fixed//~va8pR~
                	    {                                          //~va8pI~
        					if (Dump.Y) Dump.println("RACall.isTimeToCall return true by already called and fixed");//~va8pI~
                    	    rc=true;                               //~va8pI~
                        	break;                                 //~va8pI~
                    	}                                          //~va8pI~
                    if (v>0 && isStatusHurryUpToGoal(Pplayer))     //~vabfI~
                    {                                              //~vabfI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall return true by StatusHurryToGoal");//~vabfI~
                        rc=true;                                   //~vabfI~
                        break;                                     //~vabfI~
                    }                                              //~vabfI~
                    if (v>0 && isStatusAimToHigherScore(Pplayer,Pintent))//~vabtM~
                    {                                              //~vabtM~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall return false by StatusAimToHigherScore");//~vabtM~
                        rc=false;                                  //~vabtM~
                        break;                                     //~vabtM~
                    }                                              //~vabtM~
                    if (v>0 && isStatusAvoidGrillBird(Pplayer))    //~vabtI~
                    {                                              //~vabtI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall return true by StatusAvoidGrillBird");//~vabtI~
                        rc=true;                                   //~vabtI~
                        break;                                     //~vabtI~
                    }                                              //~vabtI~
                }                                                  //~1313M~
            }                                                      //~1313I~
//          if (!RS.RSP[Peswn].swAllInHand                         //~1306I~//~1313M~//~va8pR~
//          ||  ctrTaken<=HV_TIME_TO_CALL) //     //<=3 save to call up to 3 tiles take//~1306R~//~1313I~//~va8pR~
            if (RS.RSP[Peswn].swAllInHand                          //~va8pI~
            &&  ctrTaken<=HV_TIME_TO_CALL) //     //<=3 save to call up to 3 tiles take//~va8pI~
            {                                                      //~1306I~//~1313M~
			  	if (v>0 && ctrReach!=0 && shanten==1)	               //~vaatR~
              	{                                                    //~vaaiI~//~vaatR~
        			if (Dump.Y) Dump.println("RACall.isTimeToCall return true by WordTile ctrReach!=0 and shanten=1");//~vaatI~
                        rc=true;                                   //~vaatI~
                    break;                                         //~vaatI~
                }                                                  //~vaatI~
//          	if (v>0 && (Pintent & (INTENT_SAMECOLOR_ANY | INTENT_ALLSAME))!=0)//yakuhai and samecolor|allsame//~vaatR~//~vaaxR~
            	if ((v>0 || ctrDora>0) && (Pintent & (INTENT_SAMECOLOR_ANY | INTENT_ALLSAME))!=0)//yakuhai and samecolor|allsame//~vaaxI~
                {                                                  //~vaatI~
        			if (Dump.Y) Dump.println("RACall.isTimeToCall return true by WordTile/Dora intent:samecolor or allsame");//~vaatI~//~vaaxR~
                    rc=true;                                       //~vaatI~
                    break;                                         //~vaatI~
                }                                                  //~vaatI~
        		if (Dump.Y) Dump.println("RACall.isTimeToCall return FALSE by early call and no reach");//~va8pI~//~vaarR~
            	break;  //false                                   //~1306I~//~1313I~
            }                                                      //~1306I~//~1313M~
        	if (Paction==GCM_KAN)                                  //~1306I~
            {                                                      //~1306I~
			    if (ctrReach!=0) //other player's reach            //~1306I~
	            	break;                                         //~1306I~
            }                                                      //~1306I~
			if (Paction==GCM_PON)                                  //~1306I~
            {                                                      //~1306I~
        		if (pos>=OFFS_WORDTILE)                            //~1306R~
                {                                                  //~1306I~
//                  int v=RAUtils.chkValueWordTile(pos,Peswn);     //~1306R~//~1313R~
//                  if (v/2>1)/*2han*/                             //~1306I~//~1313R~
//                  {                                              //~1306R~//~1313R~
//                      rc=true;                                   //~1306R~//~1313R~
//                      break;                                     //~1306R~//~1313R~
//                  }                                              //~1306R~//~1313R~
                    if (v>0)/*1han*/                                 //~1306I~
                    {                                              //~1306I~
//      				if (RS.itsExposed[pos]==2)                 //~1306I~//~1313R~
        				if (RS.itsExposed[pos]==2 && ctrDora>0) //call at 2nd if having a dora//~1313I~
                        {                                          //~1306I~
				        	if (ctrReach!=0) //other player's reach//~1306I~
    	                    	if (PmyShanten>HV_SHANTEN_TO_PON_WORD_ON_REACH)    //>2//~1306I~
		                        	break;	//rc=false             //~1306I~
	                        rc=true;                               //~1306I~
                        	break;                                 //~1306I~
                        }                                          //~1306I~
                        if (ctrReach==0 && shanten<=HV_SHANTEN_KAN_WORD && RS.getItsHandEswn(Peswn)[pos]==PAIRCTR)    //kan if shanten<=2 with no other reach//~vaatI~
                        {                                          //~vaatI~
					        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Kan shanten="+shanten+",ctrReach="+ctrReach);//~vaatI~
	                        rc=true;                               //~vaatI~
                        	break;                                 //~vaatI~
                        }                                          //~vaatI~
                    }                                              //~1306I~
                }                                                  //~1306I~
                if (shanten==1 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1 //dora>=2//~vaaiR~
                ||  shanten==1 && ctrReach==1 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_OTHERREACH //dora>=0//~vaaiR~
                ||  shanten==2 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN2)//dora>=3//~vaaiR~
                {                                                  //~vaaiI~
			        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Pon shanten="+shanten+" and dora="+ctrDora+",ctrReach="+ctrReach);//~vaaiI~//~vaarR~
	                rc=true;	//yaku fix and shantenup will be chked at callPonKan//~vaaiR~
    	            break;                                         //~vaaiI~
                }                                                  //~vaaiI~
                int[] itsH=RS.getItsHandEswn(Peswn);               //~vaaHI~
                int ctrH=RS.RSP[Peswn].ctrHand;                    //~vaaHI~
            	if (!RS.RSP[Peswn].swAllInHand)                     //~vaaJI~
                {                                                  //~vaaJI~
                    if (shanten==1 && chkShantenAddCall(Peswn,itsH,ctrH,pos,-2)==0)   //tenpai after drop pair//~vaaHR~//~vaaJR~
                    {                                                  //~vaaHI~//~vaaJR~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Pon becomming tenpai eswn="+Peswn+",pos="+pos);//~vaaHI~//~vaaJR~
                        rc=true;    //yaku fix and shantenup will be chked at callPonKan//~vaaHI~//~vaaJR~
                        break;                                         //~vaaHI~//~vaaJR~
                    }                                                  //~vaaHI~//~vaaJR~
                }                                                  //~vaaJI~
            }                                                      //~1306I~
            if ((Pintent & INTENT_SAMECOLOR_ANY)!=0)                //~1306I~
            {                                                      //~1306I~
            	int tp=Ptd.type;                                   //~vaaeI~
              if (tp==TT_JI                                        //~vaaeI~
              ||   (tp==TT_MAN && (Pintent & INTENT_SAMECOLOR_MAN)!=0)//~vaaeI~
              ||   (tp==TT_PIN && (Pintent & INTENT_SAMECOLOR_PIN)!=0)//~vaaeI~
              ||   (tp==TT_SOU && (Pintent & INTENT_SAMECOLOR_SOU)!=0)//~vaaeI~
              )                                                    //~vaaeI~
              {                                                    //~vaaeI~
		        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true by samecolor");//~vaaeI~
                rc=true;                                           //~1306I~
                break;                                             //~1306I~
              }                                                    //~vaaeI~
            }                                                      //~1306I~
			if (Paction==GCM_CHII)                                 //~vaaiI~
            {                                                      //~vaaiI~
                if (shanten==1 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1 //shanten=1 and dora>=2//~vaaiR~
                ||  shanten==1 && ctrReach==1 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_OTHERREACH //dora>=0//~vaaiR~
                ||  shanten==2 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN2)//shanten=2 and dora>=3//~vaaiR~
                {                                                  //~vaaiI~
			        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii shanten="+shanten+" and dora="+ctrDora+",ctrReach="+ctrReach);//~vaaiR~
	                rc=true;	//yaku fix and shantenup will be chked at callChii//~vaaiR~
    	            break;                                         //~vaaiI~
                }                                                  //~vaaiI~
                int[] itsH=RS.getItsHandEswn(Peswn);               //~vaaHI~
                int ctrH=RS.RSP[Peswn].ctrHand;                    //~vaaHI~
            	if (!RS.RSP[Peswn].swAllInHand)                     //~vaaJI~
                {                                                  //~vaaJI~
                    if (shanten==1 && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)==0)    //tenpai chk by adding discarded//~vaaHR~//~vaaJR~
                    {                                                  //~vaaHI~//~vaaJR~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii becomming tenpai eswn="+Peswn+",pos="+pos);//~vaaHI~//~vaaJR~
                        rc=true;    //yaku fix and shantenup will be chked at callPonKan//~vaaHI~//~vaaJR~
                        break;                                         //~vaaHI~//~vaaJR~
                    }                                                  //~vaaHI~//~vaaJR~
                    if (shanten==2 && RS.RSP[Peswn].getPairCtr()==1//~vaaJI~
                    &&  (AG.aRAReach.isWaitingPenchan(Peswn,pos,itsH,ctrH) || AG.aRAReach.isWaitingKanchan(Peswn,pos,itsH,ctrH))//~vaaJI~
                    && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)==1)    //tenpai chk by adding discarded//~vaaJI~
                    {                                              //~vaaJI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii shanten up by 2nd call for penchan/kanchan eswn="+Peswn+",pos="+pos);//~vaaJI~
                        rc=true;    //yaku fix and shantenup will be chked at callPonKan//~vaaJI~
                        break;                                     //~vaaJI~
                    }                                              //~vaaJI~
                }                                                  //~vaaJI~
            }                                                      //~vaaiI~
            break;                                                 //~1306I~
		} //for (;;)                                               //~1306I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall rc="+rc+",eswn="+Peswn+",action="+Paction+",swAllInhand="+RS.RSP[Peswn].swAllInHand+",ctrTaken="+RS.RSP[Peswn].ctrTaken);//~1306I~//~va8pR~
        return rc;                                                 //~1306I~
    }                                                              //~1306I~
    //***************************************************************//~va83I~
    //*chk Human player 13NoPair/14NoPair in PlayAloneNotify mode at Taken//~va83I~
    //*itsHand already includes taken                              //~vaadI~
    //***************************************************************//~va83I~
//  private boolean isRonNoPair(int Peswn,int PposTaken)           //~va83I~//~vaadR~
    private boolean isRonNoPair(int Peswn)                         //~vaadI~
    {                                                              //~va83I~
        if (Dump.Y) Dump.println("RACall.isRonNoPair eswn="+Peswn);//~va83I~
    	if (!AG.aPlayers.is1stTake())                              //~va83I~
        	return false;                                          //~va83I~
		int[] itsH=RS.getItsHandEswn(Peswn);                       //~va83I~
//      itsH[PposTaken]++;                                         //~va83I~//~vaadR~
    	int[][] dupCtr=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR];	//4*9//~va83I~
        RAUtils.countTile(itsH,dupCtr);                            //~va83I~
        boolean rc=AG.aUARonValue.isNoPairPlayAlone(dupCtr);      //~va83R~
        return rc;
    }                                                              //~va83I~
    //*******************************************************************************************//~va84I~
    //*from RADSmart,chk Robot player 13NoPair/14NoPair at Taken also in matching mode//~va84I~
    //*******************************************************************************************//~va84I~
    public boolean isRonNoPairRobot(int Peswn,int PposTaken)       //~va84I~
    {                                                              //~va84I~
        if (Dump.Y) Dump.println("RACall.isRonNoPairRobot eswn="+Peswn+",posTaken="+PposTaken);//~va84R~
    	if (!AG.aPlayers.is1stTakeRobot(Peswn))                    //~va84R~
        	return false;                                          //~va84I~
		int[] itsH=RS.getItsHandEswn(Peswn);                       //~va84I~
//      itsH[PposTaken]++;  //already added for robot              //~va84R~
    	int[][] dupCtr=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR];	//4*9//~va84I~
        RAUtils.countTile(itsH,dupCtr);                            //~va84I~
        boolean rc=AG.aUARonValue.isNoPairPlayAlone(dupCtr);       //~va84I~
        return rc;                                                 //~va84I~
    }                                                              //~va84I~
    //*******************************************************************************************//~vabfI~
    private boolean isStatusHurryUpToGoal(int Pplayer)             //~vabfI~
    {                                                              //~vabfI~
    	boolean rc=false;                                          //~vabfI~
        if (Dump.Y) Dump.println("RACall.isStatusHurryUpToGoal player="+Pplayer);//~vabfI~
        int[] scoreS=AG.aAccounts.score;                            //~vabfI~
        if (Dump.Y) Dump.println("RACall.isStatusHurryUpToGoal score="+ Arrays.toString(scoreS));//~vabfR~//~vabtR~
        int mx=-1;                                                 //~vabfI~
        int mxPlayer=-1;                                           //~vabfI~
        int scoreHurryUpGoal=scorePlus+ Complete.POINT_RANKM; //mangan over debt//~vabfI~
        for (int ii=0;ii<PLAYERS;ii++)                             //~vabfI~
        {                                                          //~vabfI~
        	int idx=AG.aAccounts.playerToPosition(ii);             //~vabfI~
        	int score=scoreS[idx];	//account index seq            //~vabfI~
        	if (Dump.Y) Dump.println("RACall.isStatusHurryUpToGoal player="+ii+",score="+score+",scoreHurryUpGoal="+scoreHurryUpGoal);//+vac1I~
            if (score>scoreHurryUpGoal && score>mx)                //~vabfR~
            {                                                      //~vabfI~
            	mx=score;                                          //~vabfI~
                mxPlayer=ii;                                       //~vabfI~
            }                                                      //~vabfI~
        }                                                          //~vabfI~
        if (Dump.Y) Dump.println("RACall.isStatusHurryUpToGoal mxPlayer="+mxPlayer+",Pplayer="+Pplayer+",mxScore="+mx);//~vabfI~
        if (mxPlayer==Pplayer)	//robot is top                     //~vabfI~
        {                                                          //~vac1I~
        	rc=Status.isNearFinalGame();                           //~vabfI~
            if (rc)                                                //~vac1I~
            	rc=Accounts.playerToEswn(Pplayer)!=ESWN_E;	//call if robot is not East wind//~vac1I~
        }                                                          //~vac1I~
        if (Dump.Y) Dump.println("RACall.isStatusHurryUpToGoal rc="+rc+",Pplayer="+Pplayer);//~vabfI~
        return rc;                                                 //~vabfI~
    }                                                              //~vabfI~
    //*******************************************************************************************//~vabtI~
    private boolean isStatusAvoidGrillBird(int Pplayer)            //~vabtI~
    {                                                              //~vabtI~
    	boolean rc=false;                                          //~vabtI~
        int gameSetType=RuleSetting.getGameSetType();              //~vabtI~
        if (gameSetType==GST_E                                     //~vabtI~
        &&  AG.aAccounts.isGrillBird(Pplayer))                     //~vabtI~
        	rc=true;                                               //~vabtI~
        if (Dump.Y) Dump.println("RACall.isStatusAvoidGrillBird rc="+rc+",player="+Pplayer+",gameSetType="+gameSetType);//~vabtI~
        return rc;                                                 //~vabtI~
    }                                                              //~vabtI~
    //*******************************************************************************************//~vabtI~
    private boolean isStatusAimToHigherScore(int Pplayer,int Pintent)//~vabtI~
    {                                                              //~vabtI~
    	boolean rc=false;                                          //~vabtI~
        if (Dump.Y) Dump.println("RACall.isStatusAimToHigherScore player="+Pplayer+",intent="+Integer.toHexString(Pintent));//~vabtR~
        int[] scoreS=AG.aAccounts.score;                           //~vabtI~
        int debt=scorePlus;                                        //~vabtI~
        int limit=debt-Complete.POINT_RANKM; //mangan under debt   //~vabtI~
        if (Dump.Y) Dump.println("RACall.isStatusAimToHigherScore debt="+debt+",limit="+limit+",score="+ Arrays.toString(scoreS));//~vabtI~
        int idx=AG.aAccounts.playerToPosition(Pplayer);            //~vabtI~
        int score=scoreS[idx];	//account index seq                //~vabtI~
        if (score<limit)                                           //~vabtI~
        	rc=Status.isNearFinalGame();                           //~vabtI~
        if (Dump.Y) Dump.println("RACall.isStatusAimToHigherScore rc="+rc+",idx="+idx+",Pplayer="+Pplayer+",playerScore="+score);//~vabtI~
        return rc;                                                 //~vabtI~
    }                                                              //~vabtI~
}//class RACall                                                    //~1117R~
