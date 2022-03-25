//*CID://+vaKNR~: update#= 581;                                    //+vaKNR~
//**********************************************************************//~v101I~
//2022/03/19 vakN (Bug)MakeChii Dump.e;chii for straight,missing color check//+vaKNI~
//2022/01/23 vajc (bug)Add kan was not notified when at just taken(should chk all in hand)-->bothersome notify only at take//~vajcI~
//2022/01/23 vaj9 Not Notify Ankan but notify addKan               //~vaj9I~
//2022/01/20 vaj7 display furiten err after reach on complte/drawnhw/drawnlast dialog//~vaj7I~
//2022/01/18 vaj1 skip kan if loose fixed1 by kan(honor meld in hand)//~vaj1I~
//2022/01/15 vair issue pon for Double East even when INTENT_7PAIR //~vairI~
//2022/01/15 vaiq when callChii by samecolor,additional to shantendown,chk othercolor exist to discard//~vaiqI~
//2022/01/14 vaip near finalgame aim higher score; consider current value and intent//~vaipI~
//2021/12/21 vai5 isTimeToCall; consider chkOtherPlayer            //~vai5I~
//2021/12/21 vai4 drop "once called" from isTimeToCall condition   //~vai4I~
//2021/11/13 vagp (Bug)vagn but origanally,when robot called Ron, Pon should not be notified to Human by that tile like as vagm.//~vagpI~
//2021/11/13 vagm (Bug)Robot Ron was intercepted by Robot Pon by headbump sequence//~vagmI~
//2021/11/13 vagk (Bug)same functionality so not harmfule but for future//~vagkI~
//2021/11/12 vagj No need to set shanten/intent for discarded after reach called.//~vagjI~
//2021/11/12 vagh support INTENT_4ANKO                             //~vaghI~
//2021/11/10 vagd when kantaken;issue kan by fixed1 or allInHand   //~vagdR~
//2021/11/10 vagc when kantaken,before chk istimetocall ctrPos==4 or adkan availability//~vagcI~
//2021/11/10 vagb count dora of discarded at isTimeToCall          //~vagbI~
//2021/11/08 vaga For same color priority is Chii>Pon              //~vagaI~
//2021/11/08 vag7 INTENT_ALLSAME;triplet>=1 and (triplet+pair)>=4 and no seq meld//~vag7I~
//2021/11/07 vag5 for Kan,chk samecolor for the kan tile           //~vag5I~
//2021/11/07 vag4 avoid addkan if reacher exist                   //~vag4I~//~vagcR~
//2021/11/07 vag3 accept num tile kan if shantenK=1 for expecting becomming tenpai by rinshan//~vag3R~
//2021/11/07 vag2 force call for samecolor even shanten down especially 2 han constraint//~vag2I~
//2021/11/07 vag1 skip num tile kan if allInHand                   //~vag1I~
//2021/11/06 vag0 (Bug)Kan call is not shanten up
//2021/11/06 vafy skip chii if INTENT_ALLSAME                      //~vafyI~
//2021/11/06 vafw for early samecolor, ctrDora is to of samecolor  //~vafwI~
//2021/11/05 vafv no KAN for INTENT_3DRAGON                        //~vafvI~
//2021/11/05 vafs call chii/pion if already fixed1(not word tile pon case)//~vafsI~
//2021/11/01 vafq consider 2han constraint at call in allinhand    //~vafqI~
//2021/11/01 vafn chk ronable(inclucding 2han constraint) required if shanten up to 0 in Not AllInHand//~vafnI~
//2021/11/01 vafk INTENT_3SAMESEQ; 2nd call if once called according FixedFirst rule//~vafkI~
//2021/11/01 vafj INTENT_STRAIGHT; 2nd call if once called according FixedFirst rule//~vafjI~
//2021/10/28 vaff pon/chii call for INTENT_CHANTA                  //~vaffI~
//2021/10/31 vafd it should be chk already fixed for pon for other than word and chii//~vafdI~
//2021/10/28 vafc pon/chii call for INTENT_TANYAO                  //~vafcR~
//2021/10/28 vafb evaluate INTENT_3SAMESEQ                         //~vafbI~
//2021/10/27 vaf9 evaluate INTENT_STRAIGHT                         //~vaf9I~
//2021/10/26 vaf6 (Bug)have to ignore shanten Down for INTENT_3DRAGON//~vaf6I~
//2021/08/05 vac1 call pon for honor tile at near final round if top, but if only not in east wind//~vac1I~
//2021/08/01 vabt Pon for 1st discard of honor tile if GrillBird option is on in East only round.//~vabtI~
//2021/08/01 vabr avoid other color chii when intent is samecolor  //~vabrI~
//2021/07/29 vabp (Bug of vaad) at human take, not notified Ron if after reach called//~vabpI~
//2021/07/29 vabn bypass notify for KanTaken                       //~vabnI~
//2021/07/28 vabf Robot call Honer tile at first if top at near final game//~vabfI~
//2021/07/25 vab4 call Pon if dora discarded                       //~vab4I~
//2021/07/25 vab3 selectrMeld;select if possibility of dora even not red5 exist//~vab3I~//~vaj7R~
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

import android.graphics.Point;

import com.btmtest.TestOption;
import com.btmtest.dialog.CompReqDlg;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.RuleSettingYaku;
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
    private int[] itsHandWkNum=new int[CTR_NUMBER_TILE];  //evaluate wait expecting value//~vag2I~
    private TileData[] tdsPairKan;                                     //~1124I~
    private TileData[] tdsHand;                                    //~1124I~
    private int scorePlus;                                         //~vabfI~
    private boolean swIssueChiiStraight;                           //~vaf9I~
    private boolean swIssueChii3SameSeq;                           //~vafbI~
//  private boolean swChkFix2ndCall;                               //~vafnR~
    private boolean swByHonorTile;                                 //~vafsI~
    private int actionIssueTanyao;                                 //~vafcI~
    private int posIssueTanyao;                                    //~vafcR~
    private int actionIssueChanta;                                 //~vaffI~
    private int posIssueChanta;                                    //~vaffR~
    private int maxAmtWinList;                                     //~vafcI~
    private int optYakuFixTiming;                                  //~vafjI~
//  private boolean swSkipNotifyKanTaken=true;                     //~vabnR~
    private int posFixedCallShanten0_Chii;                         //~vafnR~
    private int posFixedCallShanten0_Pon;                          //~vafnI~
    private boolean swAcceptShantenDown;                           //~vag2I~
    private boolean swRankMUp;                                     //~vaipI~
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
    	swRankMUp=RuleSetting.isRankMUp();   //kiriage mangan      //~vaipI~
	    optYakuFixTiming= RuleSettingYaku.getYakuFix();             //~vafjI~
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
		  if (PmyShanten>0  //not tenpai                           //~vag4R~
		  &&  AG.aPlayers.getCtrReachedPlayer(-1/*Player to be excluded*/)>0)//~vag4I~
          {                                                        //~vag4I~
	        if (Dump.Y) Dump.println("RACall.callKanTaken avoid KanAdd by other reacher shanten="+PmyShanten);//~vag4R~
          }                                                        //~vag4I~
          else                                                     //~vag4I~
          {                                                        //~vag4I~
    		posKan=callKanAdd(Pplayer,PeswnPlayer,PmyShanten,PitsHand,PctrHand);//~1124R~
        	if (posKan>=0)                                             //~1117I~//~1124I~
        		issueKan(KAN_ADD,Pplayer,PeswnPlayer,tdsPairKan);                     //~1117I~//~1118R~//~1119R~//~1124R~
          }                                                        //~vag4I~
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
        if (!isMakableKanMeld(player,PeswnPlayer,PtdTaken,PitsHand,false/*swAddKan*/))//~vagcR~//~vagdR~
        	return posKan;                     //-1                //~vagcI~
        if (!isCallable(GCM_KAN,player,PeswnPlayer,PeswnPlayer,PtdTaken))//~1222I~
        	return posKan;                     //-1                //~1206I~
        int posTaken=RAUtils.getPosTile(PtdTaken);                     //~vagdI~
        for (int ii=OFFS_WORDTILE;ii<CTR_TILETYPE;ii++)               //~1117I~
        {                                                          //~1117I~
        	if (PitsHand[ii]==PIECE_DUPCTR)                        //~1120R~
            {                                                      //~1117I~
              if (ii!=posTaken)	//posTaken was chked to call Kan by isCallable//~vagdI~
              {                                                    //~vagdI~
    			if (!isKanFixed(PeswnPlayer,ii))                   //~1222I~
                	continue;                                      //~1222I~
              }                                                    //~vagdI~
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
              if (ii!=posTaken)	//posTaken was chked to call Kan by isCallable//~vagdI~
              {                                                    //~vagdI~
    			if (!isKanFixed(PeswnPlayer,ii))                   //~1222I~
                	continue;                                      //~1222I~
              }                                                    //~vagdI~
//  			shanten=chkShanten(KAN_TAKEN,ii,PitsHand,PctrHand);//~1117I~//~1119R~
//  			shanten=RAUtils.getShantenAdd(PitsHand,PctrHand,ii,-PIECE_DUPCTR);//~1119I~//~1128R~
    			shanten=chkShantenAddCall(PeswnPlayer,PitsHand,PctrHand,ii,-PIECE_DUPCTR);//~1128I~
//              if (shanten==0 || shanten<PmyShanten)                           //~1117I~//~1131R~//~vagdR~
                if (shanten==0 || shanten<=PmyShanten)             //~vagdI~
                {                                                  //~1117I~
//                  if (shanten<minShanten) //only if advance shanten/~1117I~//~1119R~//~1124R~//~vagdR~
                    if (shanten<=minShanten) //only if advance shanten/~1117I~//~vagdI~
                    {                                              //~1117I~
                        minShanten=shanten;                        //~1117I~
                        posMinShanten=ii;                          //~1117I~
                    }                                              //~1117I~
                }                                                  //~1117I~
		        if (Dump.Y) Dump.println("RACall.callKanTaken pos="+ii+",shanten="+shanten+",minShanten="+minShanten+",Pshanten="+PmyShanten);//~vagdI~
            }                                                      //~1117I~
            posKan=posMinShanten;                                  //~1117I~
        }                                                          //~1117I~
        if (posKan>=0)                                             //~1124I~
		    tdsPairKan=makeKanTaken(PeswnPlayer,posKan);           //~1124I~
        if (Dump.Y) Dump.println("RACall.callKanTaken exit posKan="+posKan);//~1117R~//~vag2R~
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
    					if (!isKanFixed(PeswnPlayer,pos))          //~vagdI~
                        {                                          //~vagdI~
        					if (Dump.Y) Dump.println("RACall.callKanAdd skip kanAdd by Fix chk pos="+pos);//~vagdI~
                        	continue;                              //~vagdI~
                        }                                          //~vagdI~
//                      shanten=chkShanten(KAN_ADD,pos,PitsHand,PctrHand);//~1117I~//~1119R~
//  					shanten=RAUtils.getShantenAdd(PitsHand,PctrHand,pos,-1);//~1119I~//~1126R~
    					shanten=chkShantenAddCall(PeswnPlayer,PitsHand,PctrHand,pos,-1);//~1126I~//~1128R~
//                      if (shanten==0 || shanten<PmyShanten)    //kan only if shanten up               //~1117I~//~1124R~//~1131R~//~vagdR~
                        if (shanten==0 || shanten<=PmyShanten)    //kan only if shanten up//~vagdI~
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
//    //*********************************************************    //~vaaUI~//~vaj9R~
//    public boolean otherKanClient(int Pplayer/*kan caller*/,int PplayerEswn,int PkanType,TileData PtdKan)//~vaaUI~//~vaj9R~
//    {                                                              //~vaaUI~//~vaj9R~
//        if (Dump.Y) Dump.println("RACall.otherKanClient Pplayer="+Pplayer+",eswn="+PplayerEswn+",kanType="+PkanType);//~vaaUI~//~vaj9R~
//        boolean rc;                                                //~vaaUR~//~vaj9R~
//        int yourEswn=AG.aAccounts.getCurrentEswn(); //PLAYER_YOU       //~vaaUR~//~vaj9R~
//        rc=calledKanNotify(yourEswn,Pplayer,PplayerEswn,PkanType,PtdKan);//~vaaUR~//~vaj9R~
//        if (Dump.Y) Dump.println("RACall.otherKanClient rc="+rc);  //~vaaUI~//~vaj9R~
//        return rc;                                                 //~vaaUI~//~vaj9R~
//    }                                                              //~vaaUI~//~vaj9R~
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
        boolean swIssuedRon=false;                                 //~vagmI~
        if (Dump.Y) Dump.println("RACall.otherDiscard action="+Paction+",playerDiscard="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",td="+PtdDiscarded.toString());//~1125I~//~1128R~//~1130R~
        try                                                        //~1126I~
        {                                                          //~1126I~
            int eswn=PeswnDiscarded+1;	//from next player for headbump by robot//~1206I~
            for (int ii=0;ii<PLAYERS;ii++,eswn++)                      //~1118I~//~1126R~//~1206R~
            {                                                          //~1118I~//~1126R~
                if (Dump.Y) Dump.println("RACall.otherDiscard 1stLoop eswn="+eswn);//~vagmI~
                if (eswn==PLAYERS)                                 //~1206I~
                	eswn=0;                                        //~1206I~
                if (RS.isRobot(eswn))                                //~1118I~//~1126R~//~1128R~//~1201I~//~1206R~
                {                                              //~1128I~//~1201I~
                    if (eswn==PeswnDiscarded) //discarder                //~1118I~//~1126R~//~1128R~//~1201R~//~1206R~
                    {                                              //~1201I~
                      if (Dump.Y) Dump.println("RACall.otherDiscard eswn=PeswnDiscarded="+eswn);//~vagjI~
					  if (!RS.RSP[eswn].isReachCalled()) //no need to set intent and shanten after Reach//~vagjI~
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
//                          nextPlayerPonKan(eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~1128R~//~1206R~//~vagmR~
//                        if (true)  //TODO test                   //~vagmI~//~vagpR~
//                        {                                        //~vagmI~//~vagpR~
//                          nextPlayerPonKan(true/*swRon*/,eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~vagmI~//~vagpR~
//                          nextPlayerPonKan(false/*swRon*/,eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~vagmI~//~vagpR~
//                        }                                        //~vagmI~//~vagpR~
//                        else                                     //~vagmI~//~vagpR~
                            if (nextPlayerPonKan(true/*swRon*/,eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded))//~vagmR~
                            	swIssuedRon=true;                  //~vagmI~
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
//                          nextPlayerPonKanHumanOnServer(eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~vaa2R~//~vagpR~
                            break;                                 //~va70I~
                        }                                          //~va70I~
                    }                                              //~va70I~
                }                                                  //~va70I~
            }                                                          //~1118I~//~1126R~
            if (Dump.Y) Dump.println("RACall.otherDiscard after chk robot ron swIssuedRon="+swIssuedRon);//~vagpI~
            if (!swIssuedRon)                                      //~vagmI~
            {                                                      //~vagmI~
                eswn=PeswnDiscarded+1;  //from next player for headbump by robot//~vagmR~
                for (int ii=0;ii<PLAYERS;ii++,eswn++)              //~vagmR~
                {                                                  //~vagmR~
                    if (Dump.Y) Dump.println("RACall.otherDiscard 2ndLoop eswn="+eswn);//~vagmR~
                    if (eswn==PLAYERS)                             //~vagmR~
                        eswn=0;                                    //~vagmR~
                    if (RS.isRobot(eswn))                          //~vagmR~
                    {                                              //~vagmR~
                        if (eswn==PeswnDiscarded) //discarder      //~vagmR~
                        {                                          //~vagmR~
                            ;                                      //~vagmR~
                        }                                          //~vagmR~
                        else                                       //~vagmR~
                        {                                          //~vagmR~
                            RoundStat.RSPlayer RSP=RS.RSP[eswn];   //~vagmR~
                            tdsHand=AG.aPlayers.getHands(RSP.player);//~vagmR~
                            switch(Paction)                        //~vagmR~
                            {                                      //~vagmR~
                            case GCM_DISCARD:                      //~vagmR~
                                break;                             //~vagmR~
                            case GCM_NEXT_PLAYER_PONKAN:           //~vagmR~
                                nextPlayerPonKan(false/*swRon*/,eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~vagmR~
                                break;                             //~vagmR~
                            }                                      //~vagmR~
                        }                                          //~vagmR~
                    }                                              //~vagmR~
                    else //human                                   //~vagpI~
                    {                                              //~vagpI~
                        if (eswn==PeswnDiscarded) //discarder      //~vagpI~
                        {                                          //~vagpI~
                            ;                                      //~vagpI~
                        }                                          //~vagpI~
                        else                                       //~vagpI~
                        {                                          //~vagpI~
                            switch(Paction)                        //~vagpI~
                            {                                      //~vagpI~
                            case GCM_DISCARD:                      //~vagpI~
                                break;                             //~vagpI~
                            case GCM_NEXT_PLAYER_PONKAN:           //~vagpI~
                                nextPlayerPonKanHumanOnServer(eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~vagpI~
                                break;                             //~vagpI~
                            }                                      //~vagpI~
                        }                                          //~vagpI~
                    }                                              //~vagpI~
                }   //for                                          //~vagmR~
            }//!swIssuedRon                                        //~vagmI~
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
//  	if (!AG.swPlayAloneNotify)                                 //~va70I~//~vaj7R~
//      	return;    //need furitenchk after reach                                            //~va70I~//~vaj7R~
    	if (!AG.swPlayAloneNotify)                                 //~vaj7I~
        	return;    //need furitenchk after reach               //~vaj7I~
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
//  	    setRonableAfterReach(PeswnOther);                      //~vaj7R~
		    if (!AG.aRARon.callRonRiverPlayAloneNotify(PLAYER_YOU,PeswnOther,itsH,ctrH,PtdDiscarded))//~va8jI~
            {                                                      //~va8jI~
        		if (Dump.Y) Dump.println("RACall.discardedPlayAloneNotify@@@@ return by MultiWait/Furiten err");//~va8jI~
                return;                                            //~va8jI~
            }                                                      //~va8jI~
//    	  if (AG.swPlayAloneNotify)                                //~vaj7R~
        	AG.aUARon.selectInfoPlayAloneNotify();	//highlight Ron btn and show Cancel btn//~va70R~
        }                                                          //~va8jI~
    }                                                              //~va70I~
    //************************************************************************//~vaa2I~
    //*from UADiscard.discard() on server and Client                //~vaa2I~//~vaj7R~
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
//  	    setRonableAfterReach(eswn);                            //~vaj7R~
//  	    if (!AG.aRARon.callRonRiverPlayAloneNotify(PLAYER_YOU,eswn,itsH,ctrH,PtdDiscarded))//~vaa2I~//~vagkR~
    	    if (!AG.aRARon.callRonRiverPlayMatchNotify(PLAYER_YOU,eswn,itsH,ctrH,PtdDiscarded))////~vagkI~
            {                                                      //~vaa2I~
        		if (Dump.Y) Dump.println("RACall.discardedPlayMatchNotify@@@@ return by MultiWait/Furiten err");//~vaa2I~
                return;                                            //~vaa2I~
            }                                                      //~vaa2I~
//        if (AG.swPlayMatchNotify)                                //~vaj7R~
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
//  private void nextPlayerPonKan(int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~1128I~//~vagmR~
    private boolean nextPlayerPonKan(boolean PswRon,int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~vagmR~
    {                                                              //~1128I~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKan entry swRon="+PswRon+",eswnOther="+PeswnOther+",eswnDiscarded="+PeswnDiscarded);       //~1128I~//~1212R~//~vagmR~
    	int shanten=RS.getCurrentShanten(PeswnOther);              //~1128I~
    	int playerOther=RS.RSP[PeswnOther].player;                 //~1128I~
        boolean rc=false;                                          //~vagmI~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKan playerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",shanten="+shanten+",tdDiscarded="+PtdDiscarded.toString());//~1128R~
      if (PswRon)                                                  //~vagmI~
      {                                                            //~vagmI~
        if (shanten==0)                                            //~1131I~
        {                                                          //~1131I~
            if (AG.aRARon.callRonRiver(PeswnOther,PtdDiscarded))    //wait human player's Ron; robot's Ron may be delayed Ron//~1131I~
//              return;                                            //~1131I~//~vagmR~
                rc=true;                                           //~vagmI~
        }                                                          //~1131I~
      }                                                            //~vagmI~
      else                                                         //~vagmI~
      {                                                            //~vagmI~
	    if (!isCallable(GCM_PON,playerOther,PeswnOther,PeswnDiscarded,PtdDiscarded))//~1128R~
//          return;                                                //~1128I~//~vagmR~
            ;                                                      //~vagmI~
        else                                                       //~vagmI~
        {                                                          //~vagmI~
//        if (!callPonKan(shanten,playerOther,PeswnOther,Pplayer,PplayerEswn,PtdDiscarded))   //may issue Pon/Kan//~1128I~
//            callChii(PeswnOther,Pplayer,PplayerEswn,PtdDiscarded);    //may issue Chii//~1128I~
        callPonKan(shanten,playerOther,PeswnOther,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);   //may issue Pon/Kan//~1128I~
        }                                                          //~vagmI~
      }                                                            //~vagmI~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKan exit rc="+rc);  //~1128I~//~vagmR~
        return rc;                                                 //~vagmI~
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
    //*Paction:avalable timeing ID:Pon Avalable or Chii Available  //~vaf9I~
    //*********************************************************    //~vaf9I~
    private boolean isCallable(int Paction,int PplayerOther,int PeswnOther,int PeswnDiscarded,TileData PtdDiscarded)//~1128I~
    {                                                              //~1128I~
    	boolean rc=true;                                           //~1128I~
        int myShanten=RS.getCurrentShanten(PeswnOther);            //~1222I~
	    if (Dump.Y) Dump.println("RACall.isCallable eswnPther="+PeswnOther+",eswnDiscard="+PeswnDiscarded+",action="+Paction+",myShanten="+myShanten);//~vaaeI~//~vairR~
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
//      if ((intent & (INTENT_7PAIR | INTENT_13ORPHAN))!=0)//~1215I~//~1224R~//~vairR~
        if ((intent & (INTENT_13ORPHAN))!=0)                       //~vairI~
        {                                                          //~1215I~
	        if (Dump.Y) Dump.println("RACall.isCallable @@@@ False by intent 13orphan Intent="+Integer.toHexString(RS.RSP[PeswnOther].intent));//~1215I~//~1221R~//~vairR~
            return false;                                          //~1224I~
        }                                                          //~1215I~
        if ((intent & (INTENT_7PAIR))!=0)                          //~vairI~
        {                                                          //~vairI~
          if (!isTimeToCallPonFor7Pair(Paction,PeswnOther,myShanten,intent,PtdDiscarded))//~vairI~
          {                                                        //~vairI~
	        if (Dump.Y) Dump.println("RACall.isCallable @@@@ False by intent 7pair Intent="+Integer.toHexString(RS.RSP[PeswnOther].intent));//~vairI~
            return false;                                          //~vairI~
          }                                                        //~vairI~
        }                                                          //~vairI~
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
    //*********************************************************    //~vairI~
    private boolean isTimeToCallPonFor7Pair(int Paction,int PeswnOther,int Pshanten,int Pintent,TileData PtdDiscarded)//~vairI~
    {                                                              //~vairI~
        if (Dump.Y) Dump.println("RACall.isTimeToCallPonFor7Pair action="+Paction+",eswn="+PeswnOther+",shanten="+Pshanten+",intent="+Integer.toHexString(Pintent)+",td="+PtdDiscarded.toString());//~vairI~
        boolean rc=false;                                          //~vairI~
        if (Paction!=GCM_PON)                                      //~vairI~
        	; //false                                              //~vairI~
        else                                                       //~vairI~
        if (Pshanten<(HV_SET_INTENT_MAX_SHANTEN_7PAIR-1)) //<2,1 shanten//~vairI~
        {                                                          //~vairI~
        //* shanten<HV_SET_INTENT_MAX_SHANTEN_7PAIR) //<3 shanten  for INTENT_7PAIR//~vairI~
        	; //false                                              //~vairI~
        }                                                          //~vairI~
        else                                                       //~vairI~
        {                                                          //~vairI~
        	int pos=RAUtils.getPosTile(PtdDiscarded);                //~vairI~
            if (pos>=OFFS_WORDTILE)                                //~vairI~
            {                                                      //~vairI~
                int[] itsH=RS.getItsHandEswn(PeswnOther);          //~vairI~
                if (itsH[pos]==PAIRCTR-1)                          //~vairI~
                {                                                  //~vairI~
					int han=RAUtils.chkValueWordTile(pos,PeswnOther)/2;//~vairI~
                    if (han==2) //wind and round                   //~vairI~
                    	rc=true;                                   //~vairI~
                }                                                  //~vairI~
            }                                                      //~vairI~
        }                                                          //~vairI~
        if (Dump.Y) Dump.println("RACall.isTimeToCallPonFor7Pair rc="+rc);//~vairI~
        return rc;
    }                                                              //~vairI~
    //*********************************************************    //~vagcI~
    private boolean isMakableKanMeld(int Pplayer,int Peswn,TileData Ptd,int[] PitsHand,boolean PswAddKan)//~vagcR~//~vagdR~
    {                                                              //~vagcI~
    	boolean rc=false;                                          //~vagcI~
        int pos=RAUtils.getPosTile(Ptd);                           //~vagcI~
	    if (Dump.Y) Dump.println("RACall.isMakableKanMeld eswn="+Peswn+",player="+Pplayer+",pos="+pos+",swAddKan="+PswAddKan+",itsHand="+Utils.toString(PitsHand,9));//~vagcI~//~vagdI~
        int ctr=PitsHand[pos];                                     //~vagcI~
        if (ctr==PAIRCTR_KAN)                                      //~vagcI~
        {                                                          //~vagcI~
			if (Dump.Y) Dump.println("RACall.isMakableKanMeld Kan availability by count pos="+pos);//~vagcI~//~vagdR~
        	rc=true;                                               //~vagcI~
        }                                                          //~vagcI~
        else                                                       //~vagcI~
        if (ctr==1 && PswAddKan)	//addkan-ablity                                                //~vagcI~//~vagdR~
        {                                                          //~vagcI~
        	TileData[][] tdss=AG.aPlayers.getEarth(Pplayer);       //~vagcI~
        	for (TileData[] tds:tdss)  //multiple kan candidate me be//~vagcI~
        	{                                                      //~vagcI~
	        	if (Dump.Y) Dump.println("RACall.isMakableKanMeld tds="+TileData.toString(tds));//~vagcI~//~vagdR~
        		if (tds==null)                                     //~vagcI~
            		break;                                         //~vagcI~
            	if ((tds[0].flag & TDF_PON)!=0)                    //~vagcI~
            	{                                                  //~vagcI~
            		if (pos==RAUtils.getPosTile(tds[0]))           //~vagcI~
                    {                                              //~vagcI~
			        	if (Dump.Y) Dump.println("RACall.isMakableKanMeld addKan availability pos="+pos);//~vagcI~//~vagdR~
                    	rc=true;                                   //~vagcI~
                        break;                                     //~vagcI~
                    }                                              //~vagcI~
                }                                                  //~vagcI~
            }                                                      //~vagcI~
        }                                                          //~vagcI~
        else                                                       //~vagcI~
        {                                                          //~vagcI~
            for (int ii=0;ii<CTR_TILETYPE;ii++)                    //~vagcI~
            {                                                      //~vagcI~
                if (PitsHand[ii]==PAIRCTR_KAN)                     //~vagcI~
                {                                                  //~vagcI~
			        if (Dump.Y) Dump.println("RACall.ismakableKanMeld found ctr=4 other than taken");//~vagcI~//~vagdR~
                    rc=true;                                       //~vagcI~
                    break;                                         //~vagcI~
                }                                                  //~vagcI~
            }                                                      //~vagcI~
        }                                                          //~vagcI~
        if (Dump.Y) Dump.println("RACall.isMakableKanMeld rc="+rc+",eswn="+Peswn);//~vagcI~//~vagdR~
        return rc;                                                 //~vagcI~
    }                                                              //~vagcI~
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
    //*call Pon/Kan for Discarded(not Taken status)                //~vag1R~
    //*under not Reach called                                      //~vag1I~
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
        if (Dump.Y) Dump.println("RACall.callPonKan entry Pshanten="+Pshanten+",pos="+pos+",ctrReach="+ctrReach+",intent="+Integer.toHexString(intent)+",eswnOther="+PeswnOther+",playerOther="+PplayerOther+",PplayerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",tdDiscard="+TileData.toString(PtdDiscarded));//~1118R~//~1124R~//~1125I~//~1126R~//~1130R~//~1220R~//~1222R~//~vaarR~//~vag0R~
        if (Dump.Y) Dump.println("RACall.callPonKan posFixedCallShanten0_Pon="+posFixedCallShanten0_Pon);//~vafsI~
        int[] itsH=RS.getItsHandEswn(PeswnOther);                   //~1118I~//~1124R~//~1126R~//~1222R~
        int   ctrH=RS.RSP[PeswnOther].ctrHand;                       //~1118I~//~1124R~//~1126R~//~1222R~
        int ctrPos=itsH[pos];                                      //~1118R~//~1222R~
        boolean swAllInHand=RS.RSP[PeswnOther].swAllInHand;        //~vag7I~
        if (Dump.Y) Dump.println("RACall.callPonKan ctrPos="+ctrPos+",itsH="+Utils.toString(itsH,9));//~vaarI~
        if (ctrPos<PAIRCTR-1)                                      //~1119I~//~1222R~
        {                                                          //~1119I~//~1222R~
            if (Dump.Y) Dump.println("RACall.callPonKan return by could not make same meld ctrPos="+ctrPos);//~1119I~//~1125R~//~1222R~
            return false;                                          //~1119I~//~1222R~
        }                                                          //~1119I~//~1222R~
        int ctrCall=0;                                             //~1119R~//~1222R~
//    if ((TestOption.option2 & TO2_CALL1ST)!=0)                   //~va75I~//~vab3R~
//    if ((TestOption.option2 & TO2_CALL1ST)!=0 && ctrPos!=PAIRCTR)//~vab3R~//~vaj7R~
      if ((TestOption.option2 & TO2_CALL1ST)!=0)                   //~vaj7I~
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
            {                                                      //~vag2I~
                ctrCall=isShantenUp(itsH,ctrH,pos,-2,Pshanten) ? ctrPos+1 : 0;//~1220I~//~1222R~
            }                                                      //~vag2I~
            if (Dump.Y) Dump.println("RACall.callPonKan chk by SAMECOLOR intent ctrCall="+ctrCall);//~1220I~//~1222R~//~vag0R~
        }                                                          //~1220I~//~1222R~
        if (ctrCall==0)                                            //~1220I~//~1222R~
        {                                                          //~1220I~//~1222R~
//          boolean swAllInHand=RS.RSP[PeswnOther].swAllInHand;    //~vab4I~//~vag7R~
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
            else  //not wordTile                                   //~vafcI~
            if (actionIssueTanyao==GCM_PON)  //dteremined issue Pon at isTimeToCall,furitenself is already chked//~vafcI~
            {                                                      //~vafcI~
                if (Dump.Y) Dump.println("RACall.callPonKan callCtr=3 by actionUIssueTanyao=GCM_PON");//~vafcI~
                ctrCall=PAIRCTR;                                   //~vafcI~
                RS.setIntentCalled(PeswnOther,INTENT_TANYAO);       //~vafcI~
            }                                                      //~vafcI~
            else                                                   //~1118I~//~1119R~//~1220R~//~1222R~
            if (actionIssueChanta==GCM_PON)  //dteremined issue Pon at isTimeToCall,furitenself is already chked//~vaffI~
            {                                                      //~vaffI~
                if (Dump.Y) Dump.println("RACall.callPonKan callCtr=3 by actionUIssueChanta=GCM_PON");//~vaffI~
                ctrCall=PAIRCTR;                                   //~vaffI~
                RS.setIntentCalled(PeswnOther,INTENT_CHANTA);      //~vaffI~
            }                                                      //~vaffI~
            else                                                   //~vaffI~
            {                                                      //~1118I~//~1119R~//~1220R~//~1222R~
                if (Dump.Y) Dump.println("RACall.callPonKan intent="+Integer.toHexString(intent));//~1131I~//~1220R~//~1222R~
//              if (RS.RSP[PeswnOther].isFixed1()   //yakuhai avail   //~1129I~//~1130R~//~1220R~//~1222R~//~vag0R~
//              || ((intent & INTENT_TANYAO)!=0 && RS.swKuitan && RAUtils.isTanyaoTile(pos) && RS.RSP[PeswnOther].isPairTanyaoAllOrNoPair())//~1217R~//~1220R~//~1222R~//~vag0R~
//              )                                                      //~1129I~//~1220R~//~1222R~//~vag0R~
//              {                                                  //~1118I~//~1119R~//~1220R~//~1222R~//~vag0R~
                    int shantenK=MAX_SHANTEN;                          //~1119I~//~1220R~//~1222R~
                    if (ctrPos==PAIRCTR)                           //~1118R~//~1119R~//~1220R~//~1222R~
                    {                                              //~1118I~//~1119R~//~1220R~//~1222R~
                      if (swAllInHand)   //skip KAN for num tile at allinHand,legardless tenpai//~vag1I~
                      {                                            //~vag1I~
					  	if (Dump.Y) Dump.println("RACall.callPonKan NO issue KAN by swAllInHand");//~vag1I~
                      }                                            //~vag1I~
                      else                                         //~vag1I~
                      {                                            //~vag1I~
    //                  shantenK=RAUtils.getShantenAdd(itsH,ctrH,pos,-PAIRCTR);//~1118I~//~1119R~//~1126R~//~1220R~//~1222R~
                        shantenK=chkShantenAddCall(PeswnOther,itsH,ctrH,pos,-PAIRCTR);//~1126I~//~1128R~//~1220R~//~1222R~
                      }                                            //~vag1I~
                    }                                              //~1118I~//~1119R~//~1220R~//~1222R~
    //              shanten=RAUtils.getShantenAdd(itsH,ctrH,pos,-(PAIRCTR-1));//~1118I~//~1119R~//~1126R~//~1220R~//~1222R~
                    shanten=chkShantenAddCall(PeswnOther,itsH,ctrH,pos,-(PAIRCTR-1));//~1126I~//~1128R~//~1220R~//~1222R~
		            if (Dump.Y) Dump.println("RACall.callPonKan shantenK="+shantenK+",shanten="+shanten+",shanten0="+shanten0);//~vag3R~
//                  if ((shantenK==0 || shantenK<shanten) && shantenK<=shanten0)        //~1119I~//~1130R~//~1131R~//~1220R~//~1222R~//~vag3R~
                    if (shantenK<=1 && shantenK<=shanten && shantenK<=shanten0)//~vag3R~
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
//              }                                                  //~1118I~//~1119R~//~1220R~//~1222R~//~vag0R~
//  	        if ((pos==TN1 || pos==TN9)                         //~1306I~//~vafnR~
//  	        &&  ((intent & INTENT_CHANTA)!=0 && RS.RSP[PeswnOther].isPairChantaAllOrNoPair())//~1306I~//~vafnR~
//              &&   isShantenUp(itsH,ctrH,pos,-2,Pshanten)        //~1306I~//~vafnR~
//              )                                                  //~1306I~//~vafnR~
//              {                                                  //~1306I~//~vafnR~
//  	            if (Dump.Y) Dump.println("RACall.callPonKan chanta return by issue Pon/Kan pos="+pos);//~1306I~//~vafnR~
//                  ctrCall=ctrPos+1;                              //~1306I~//~vafnR~
//              }                                                  //~1306I~//~vafnR~
            }                                                      //~1118I~//~1119R~//~1220R~//~1222R~
        }                                                          //~1220I~//~1222R~
        if (Dump.Y) Dump.println("RACall.callPonKan posFixedCallShanten0_Pon="+posFixedCallShanten0_Pon+",callCtr="+ctrCall);//~vafsI~
		if (ctrCall==0)                                            //~vafnI~
        {                                                          //~vafnI~
            if (posFixedCallShanten0_Pon!=-1)                       //~vafnI~//~vafsR~
            	ctrCall=PAIRCTR;                                   //~vafnI~
        }                                                          //~vafnI~
		if (ctrCall==0)                                            //~vafsI~
        {                                                          //~vafsI~
            if ((intent & INTENT_3DRAGON)!=0)                       //~vafsI~
            	ctrCall=ctrPos+1;                                  //~vafsI~
        }                                                          //~vafsI~
        if (Dump.Y) Dump.println("RACall.callPonKan after 3dragon chk callCtr="+ctrCall);//~vafsI~
      }//testoption                                                //~va75I~
        if (ctrCall==PAIRCTR_KAN)                                  //~vaatI~
        {                                                          //~vaatI~
          if ((TestOption.option2 & TO2_CALL1ST)==0)               //~vaj7I~
          {                                                        //~vaj7I~
            if ((intent & INTENT_3DRAGON)!=0)                      //~vafvI~
            {                                                      //~vafvI~
                ctrCall=-1;                                        //~vafvI~
		        if (Dump.Y) Dump.println("RACall.callPonKan avoid kan by INTENT_3DRAGON");//~vafvI~
            }                                                      //~vafvI~
            else                                                   //~vafvI~
            if (ctrReach!=0)                                       //~vaatI~
            {                                                      //~vaatI~
		        if (Dump.Y) Dump.println("RACall.callPonKan avoid kan by ctrReach="+ctrReach);//~vaatI~
                ctrCall=-1;                                        //~vaatI~
            }                                                      //~vaatI~
          }                                                        //~vaj7I~
        }                                                          //~vaatI~
        if (ctrCall==PAIRCTR_KAN)                                                 //~1118I~//~1119R~//~1222R~
        {                                                          //~1118I~//~1222R~
          if ((TestOption.option2 & TO2_CALL1ST)==0)               //~vaj7I~
          {                                                        //~vaj7I~
			if (selectFixedCallShanten0_KanSelected(PeswnOther,shanten0,itsH,ctrH,pos)==-1)	//confirm constraint if current shanten=1//~vag0I~
            {                                                      //~vag0I~
	            if (Dump.Y) Dump.println("RACall.callPonKan for KAN return false by constraintchk");//~vag0I~
    	        return false;                                      //~vag0I~
            }                                                      //~vag0I~
            if (RADS.RADSO.chkOtherPlayerBeforeCall(Pshanten,PeswnOther,intent,ctrReach,itsH,ctrH))//~vai5I~
            {                                                      //~vai5I~
		        if (Dump.Y) Dump.println("RACall.callPonKan skip Kan by chkOtherPlayer pos="+pos);//~vai5I~
    	        return false;                                      //~vai5I~
            }                                                      //~vai5I~
          }                                                        //~vaj7I~
            tdsPairKan=makeKanRiver(PeswnOther,pos,PtdDiscarded); //~1124R~//~1126R~//~1222R~
            issueKan(KAN_RIVER,PplayerOther,PeswnOther,tdsPairKan);                       //~1118I~//~1119R~//~1124I~//~1126R~//~1222R~
            if (Dump.Y) Dump.println("RACall.callPonKan return by issueKan");//~1118I~//~1119R~//~1222R~
            return true;                                           //~1118I~//~1222R~
        }                                                          //~1118I~//~1222R~
        if (ctrCall==PAIRCTR)                                       //~1118I~//~1119R~//~1222R~
        {                                                          //~1118I~//~1222R~
            if (!swAllInHand && (intent & (INTENT_3DRAGON|INTENT_ALLSAME|INTENT_SAMECOLOR_ANY|INTENT_3DRAGON))!=0)//~vag7I~
            {                                                      //~vag7I~
	            if (Dump.Y) Dump.println("RACall.callPonKan force issue Pon at !swAllInHand to keep intent="+Integer.toHexString(intent));//~vag7I~
            }                                                      //~vag7I~
            else                                                   //~vag7I~
			if (selectFixedCallShanten0_PonSelected(PeswnOther,shanten0,itsH,ctrH,pos)==-1)	//confirm constraint if current shanten=1
            {                                                      //~vafnI~
	            if (Dump.Y) Dump.println("RACall.callPonKan return false by constraintchk");//~vafnI~
    	        return false;                                      //~vafnR~
            }                                                      //~vafnI~
          if ((TestOption.option2 & TO2_CALL1ST)==0)               //~vaj7I~
            if (RADS.RADSO.chkOtherPlayerBeforeCall(Pshanten,PeswnOther,intent,ctrReach,itsH,ctrH))//~vai5I~
            {                                                      //~vai5I~
		        if (Dump.Y) Dump.println("RACall.callPonKan skip Pon by chkOtherPlayer pos="+pos);//~vai5I~
    	        return false;                                      //~vai5I~
            }                                                      //~vai5I~
            issuePon(PplayerOther,PeswnOther,pos,PtdDiscarded);                       //~1118I~//~1119R~//~1126R~//~1222R~
            if (Dump.Y) Dump.println("RACall.callPonKan return by issuePon");//~1118I~//~1119R~//~1222R~
            return true;                                           //~1118I~//~1222R~
        }                                                          //~1118I~//~1222R~
        if (Dump.Y) Dump.println("RACall.callPonKan return no action");//~1118I~//~1222R~
        return false;//~1118I~                                     //~1222R~
    }//callPonKan                                                  //~vag7R~
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
//      if (chkEarthToAddKan(Peswn,PtdTaken))                       //~vaaUI~//~vajcR~
//      if (chkEarthToAddKan(Peswn,Pplayer,ctrH))                  //~vajcI~
        if (chkEarthToAddKan(Peswn,PtdTaken))                      //~vajcI~
        {                                                          //~vabnI~
//      	swNotify=true; 	//notify for kan add                   //~vabnR~
            swNotify=true;                                         //~vaj9I~
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
//      if (chkEarthToAddKan(Peswn,PtdTaken))                      //~vaaUI~//~vajcR~
//      if (chkEarthToAddKan(Peswn,Pplayer,ctrH))                  //~vajcI~
        if (chkEarthToAddKan(Peswn,PtdTaken))                      //~vajcI~
        {                                                          //~vaj9I~
            swNotify=true;                                         //~vaj9I~
            swKan=true;                                            //~vaaUI~
        }                                                          //~vaj9I~
//      if (swKan)                                                 //~vaa2I~//~vabnR~
        if (swNotify)                                              //~vabnI~
	        AG.aUAD2Touch.updateBtnPlayMatchNotify(GCM_KAN,BTN_STATUS_ENABLE_CANCEL);//~vaa2I~
        if (Dump.Y) Dump.println("RACall.callKanTakenMPlayMatchNotify rc="+swKan);//~vaa2I~
        return swKan;                                              //~vaa2I~
    }                                                              //~vaa2I~
    //**************************************************************************//~vaaUI~//~vajcI~
    //*chk just taken                                              //~vajcI~
    //**************************************************************************//~vajcI~
    private boolean chkEarthToAddKan(int Peswn,TileData PtdTaken)  //~vaaUI~//~vajcI~
    {                                                              //~vaaUI~//~vajcI~
        boolean rc=RS.RSP[Peswn].srchPonToAdd(PtdTaken)>=0;               //~vaaUI~//~vajcI~
        if (Dump.Y) Dump.println("RACall.chkEarthToAddKan rc="+rc+",Peswn="+Peswn+",tdKan="+PtdTaken.toString());//~vaaUI~//~vajcI~
        return rc;                                                 //~vajcI~
    }                                                              //~vaaUI~//~vajcI~
    //**************************************************************************//~vajcI~
    //*chk all in hand                                             //~vajcI~
    //**************************************************************************//~vajcI~
    private boolean chkEarthToAddKan(int Peswn,int Pplayer,int PctrH)//~vajcI~
    {                                                              //~vajcI~
        if (Dump.Y) Dump.println("RACall.chkEarthToAddKan chk all in hand Peswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH);//~vajcI~
        TileData[] tdsHand=AG.aPlayers.getHands(Pplayer);            //~vajcI~
        boolean rc=false;                                          //~vajcI~
        for (int ii=0;ii<PctrH;ii++)                               //~vajcI~
        {                                                          //~vajcI~
			if (RS.RSP[Peswn].srchPonToAdd(tdsHand[ii])>=0)	//earth index//~vajcI~
            {                                                      //~vajcI~
            	rc=true;                                           //~vajcI~
                break;                                             //~vajcI~
            }                                                      //~vajcI~
        }                                                          //~vajcI~
        return rc;                                                 //~vajcI~
    }                                                              //~vajcI~
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
        boolean swAllInHand=RS.RSP[PeswnOther].swAllInHand;        //~vag2I~
		int ctrReach=AG.aPlayers.getCtrReachedPlayer(-1/*Player to be excluded*/);//~vai5I~
        if (Dump.Y) Dump.println("RACall.callChii entry eswnOther="+PeswnOther+",playerOther="+PplayerOther+",intent="+Integer.toHexString(intent)+",PplayerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",shanten="+shanten+",tdDiscard="+TileData.toString(PtdDiscarded));//~1118R~//~1126R~//~1128R~//~1129R~//~vaaiM~//~vafkR~
        if (Dump.Y) Dump.println("RACall.callChii actionIssueTanyao="+actionIssueTanyao+",actionIssueChanta="+actionIssueChanta);//~vaffI~
        if (Dump.Y) Dump.println("RACall.callChii swIssueChiiStraight="+swIssueChiiStraight+",swIssueChii3SameSeq="+swIssueChii3SameSeq);//~vaffI~
        if (Dump.Y) Dump.println("RACall.callChii eswn="+PeswnOther+",getNum3SameSeq="+RS.getNum3SameSeq(PeswnOther)+",getColorStraight="+RS.getColorStraight(PeswnOther));//~vaffI~
        if (Dump.Y) Dump.println("RACall.callChii swByHonorTile="+swByHonorTile+",ctrReach="+ctrReach);//~vafsI~//~vai5R~
        int posChii=-1;                                            //~1118R~
	    if (pos>=OFFS_WORDTILE)                                     //~1118R~//~1119R~
        {                                                          //~1119I~
	        if (Dump.Y) Dump.println("RACall.callChii return word tile");//~1119I~
        	return false;                                          //~1119I~
        }                                                          //~1119I~
        if ((intent & INTENT_ALLSAME)!=0)                          //~vafyI~
        {                                                          //~vafyI~
	        if (Dump.Y) Dump.println("RACall.callChii return by INTENT_ALLSAME");//~vafyI~
        	return false;                                          //~vafyI~
        }                                                          //~vafyI~
                                                                   //~vafyI~
      if ((TestOption.option2 & TO2_CALL1ST)!=0)                   //~va75I~
      {                                                            //~va75I~
	  	if (Dump.Y) Dump.println("RACall.callChii ignore intent and fix1 by testoption TO2_CALL1ST");//~va75I~
//      posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,13/*make shanten up condition*/,itsH,ctrH,pos);//~va75I~//~vaaLR~
        posChii=selectSeqMeld(PplayerOther,PeswnOther,false/*swTanyao*/,13/*make shanten up condition*/,itsH,ctrH,pos);//~vaaLI~
      }                                                            //~va75I~
      else                                                         //~va75I~
      {                                                            //~va75I~
      	int posChiiTanyao=-1;                                      //~vafcI~
      	int posChiiChanta=-1;                                      //~vaffI~
	   	if (actionIssueTanyao==GCM_CHII)	//To issue Pon/Chii for tanyao At AllInHand//~vafcR~
       		posChiiTanyao=selectSeqMeldForTanyao(PeswnOther,shanten,itsH,ctrH,pos,actionIssueTanyao);//furiten,fix2 is chked//~vafcR~
        else                                                       //~vaffI~
	   	if (actionIssueChanta==GCM_CHII)	//To issue Pon/Chii for chanta at AllInHand//~vaffR~
       		posChiiChanta=selectSeqMeldForChanta(PeswnOther,shanten,itsH,ctrH,pos,actionIssueChanta);//furiten,fix2 is chked//~vaffR~
//     if (!RS.swFix2)	//allow SAME_COLOR only for fix2,fix2 was chked for tanyao,straight,chanta,3sameseq,for other chked 2han constarint later//~vafqR~
//     {                                                           //~vaarI~//~vafqR~
        if (RS.RSP[PeswnOther].isFixed1())     //1han already fixed                    //~1118I~//~1119R~//~1126R~//~1129R~
        {                                                      //~1118I~//~1119R~
//          posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~1118R~//~1119R~//~1129R~//~vaaLR~
            posChii=selectSeqMeld(PplayerOther,PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~vaaLI~
        }                                                      //~1118I~//~1119R~
        else                                                   //~1118I~//~1119R~
        if (swIssueChiiStraight)                                   //~vaf9I~//~vafcM~
        {                                                          //~vaf9I~//~vafcM~
            posChii=selectSeqMeldForStraight(PeswnOther,shanten,itsH,ctrH,pos);//~vaf9R~//~vafcM~
        }                                                          //~vaf9I~//~vafcM~
        else                                                       //~vafbI~//~vafcM~
        if (swIssueChii3SameSeq)                                   //~vafbI~//~vafcM~
        {                                                          //~vafbI~//~vafcM~
            posChii=selectSeqMeldFor3SameSeq(PeswnOther,shanten,itsH,ctrH,pos);//~vafbI~//~vafcM~
        }                                                          //~vafbI~//~vafcM~
        else                                                       //~vaf9I~//~vafcM~
        if ((intent & INTENT_TANYAO)!=0 && RS.swKuitan && RAUtils.isTanyaoTile(pos) && RS.RSP[PeswnOther].isPairTanyaoAllOrNoPair())//~1118I~//~1119R~//~1126R~//~1217R~
        {                                                      //~1118I~//~1119R~
          if (posChiiTanyao==-1)                                   //~vaffI~
          {                                                        //~vaffI~
//          posChii=selectSeqMeld(PeswnOther,true/*swTanyao*/,shanten,itsH,ctrH,pos);//~1118R~//~1119R~//~1128R~//~1129R~//~vaaLR~
            posChii=selectSeqMeld(PplayerOther,PeswnOther,true/*swTanyao*/,shanten,itsH,ctrH,pos);//~vaaLI~
            num=posChii%CTR_NUMBER_TILE;                           //~1220I~
            if (num==TN1 || num==TN7)                  //~1220R~   //~1306R~
            	posChii=-1;                                        //~1220I~
          }                                                        //~vaffI~
        }                                                      //~1118I~//~1119R~
        else                                                       //~1306I~
        if ((intent & INTENT_CHANTA)!=0 && RS.RSP[PeswnOther].isPairChantaAllOrNoPair())//~1306I~
        {                                                          //~1306I~
          if (posChiiChanta==-1)                                   //~vaffI~
          {                                                        //~vaffI~
//          posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~1306I~//~vaaLR~
            posChii=selectSeqMeld(PplayerOther,PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~vaaLI~
            num=posChii%CTR_NUMBER_TILE;                           //~1306I~
            if (num!=TN1 && num!=TN7)                              //~1306I~
            	posChii=-1;                                        //~1306I~
          }                                                        //~vaffI~
        }                                                          //~1306I~
//     }//not Fix2                                                 //~vaarI~//~vafqR~
//     if (posChii==-1 && posChiiTanyao!=-1)                       //~vafcI~//~vaffR~
	   if (Dump.Y) Dump.println("RACall.callChii posChii="+posChii+",poschiiTanyao="+posChiiTanyao+",posChiiChanta="+posChiiChanta);//~vaffR~
       if (posChii==-1)                                            //~vaffI~
       {                                                           //~vafcM~
       	  if (posChiiTanyao!=-1)                                   //~vaffI~
          {                                                        //~vaffI~
            posChii=posChiiTanyao;                                 //~vafcI~
	   		if (Dump.Y) Dump.println("RACall.callChii OK by posChiiTanyao posChii="+posChii);//~vafcI~
        	RS.setIntentCalled(PeswnOther,INTENT_TANYAO);          //~vafcM~
          }                                                        //~vaffI~
          else                                                     //~vaffI~
       	  if (posChiiChanta!=-1)                                   //~vaffI~
          {                                                        //~vaffI~
            posChii=posChiiChanta;                                 //~vaffI~
	   		if (Dump.Y) Dump.println("RACall.callChii OK by posChiiChanta posChii="+posChii);//~vaffI~
        	RS.setIntentCalled(PeswnOther,INTENT_CHANTA);          //~vaffI~
          }                                                        //~vaffI~
       }                                                           //~vafcM~
      }                                                            //~va75I~
       	if (posChii==-1)                                           //~vafjI~
       	{                                                          //~vafjI~
        	if (shanten==1 && (intent & INTENT_STRAIGHT)!=0 && RS.getColorStraight(PeswnOther)!=-1)	//already issued chii for Straight//~vafjI~//~vafkR~
            {                                                      //~vafjI~
			    posChii=selectSeqMeldForStraight2ndCall(PeswnOther,itsH,ctrH,pos,shanten);//~vafjI~
            }                                                      //~vafjI~
        	if (shanten==1 && (intent & INTENT_3SAMESEQ)!=0 && RS.getNum3SameSeq(PeswnOther)!=-1)	//already issued chii for Straight//~vafkI~
            {                                                      //~vafkI~
			    posChii=selectSeqMeldFor3SameSeq2ndCall(PeswnOther,itsH,ctrH,pos,shanten);//~vafkI~
            }                                                      //~vafkI~
       	}                                                          //~vafjI~
//      if (posChii<0)                                             //~1220I~//~vabrR~
        if ((TestOption.option2 & TO2_CALL1ST)==0)                 //~vabrI~
        {                                                          //~1220I~
            int posChiiForce=-1;                                   //~vag2I~
            if ((intent & INTENT_SAMECOLOR_ANY)!=0)                //~1220I~
            {                                                      //~1220I~
                if (RAUtils.isMatchSameColor(true/*allow Word*/,intent,pos/CTR_NUMBER_TILE))//~1220I~
                {                                                  //~vabrI~
//  	            posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~1220I~//~vaaLR~
//  	            posChii=selectSeqMeld(PplayerOther,PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~vaaLI~//~vag2R~
//  	            posChii=selectSeqMeldForSameColor(PplayerOther,PeswnOther,shanten,itsH,ctrH,pos,swAllInHand);//~vag2I~//~vaiqR~
    	            posChii=selectSeqMeldForSameColor(PplayerOther,PeswnOther,shanten,itsH,ctrH,pos,swAllInHand,intent);//~vaiqI~
                	if (Dump.Y) Dump.println("RACall.callChii chk by SAMECOLOR intent posChii="+posChii);//~1220I~//~vabrR~
                    if (posChii!=-1)                               //~vag2I~
                        posChiiForce=posChii;                      //~vag2I~
                }                                                  //~vabrI~
                else                                               //~vabrI~
	            	posChii=-1;                                    //~vabrI~
            }                                                      //~1220I~
            if (Dump.Y) Dump.println("RACall.callChii posChiiForce="+posChiiForce);//~vag2I~
//          if (posChii>=0 && swChkFix2ndCall)	//shanten up to tenpai in Not AllInHand//~vafnR~
//  			if (!isFixed2ndCall(PeswnOther,itsH,ctrH,GCM_CHII,pos,posChii))//~vafnR~
//              	posChii=-1;                                    //~vafnR~
			if (posChii==-1)                                       //~vafnI~
            	posChii=posFixedCallShanten0_Chii;                 //~vafnR~
            else                                                   //~vafnI~
            if (posChiiForce==-1)                                  //~vag2I~
			    posChii=selectFixedCallShanten0_ChiiSelected(PeswnOther,shanten,itsH,ctrH,pos,posChii);	//confirm constraint//~vafnI~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RACall.callChii before swByHonorTile posChii="+posChii+",wsByHonorTile="+swByHonorTile);//~vafsI~
        if (posChii<0)                                             //~vafsI~
        {                                                          //~vafsI~
        	if (swByHonorTile                                      //~vafsR~
            ||  (intent & INTENT_3DRAGON)!=0                       //~vafsI~
            )                                                      //~vafsI~
            	posChii=selectSeqMeld(PplayerOther,PeswnOther,false,shanten,itsH,ctrH,pos);//~vafsI~
        }                                                          //~vafsI~
        if (posChii>=0)                                            //~vai5I~
        {                                                          //~vai5I~
          if ((TestOption.option2 & TO2_CALL1ST)==0)               //~vaj7I~
          {                                                        //~vaj7I~
            if (RADS.RADSO.chkOtherPlayerBeforeCall(shanten,PeswnOther,intent,ctrReach,itsH,ctrH))//~vai5I~
            {                                                      //~vai5I~
		        if (Dump.Y) Dump.println("RACall.callChii skip chii by chkOtherPlayer posChii="+posChii);//~vai5I~
		        posChii=-1;                                        //~vai5I~
            }                                                      //~vai5I~
          }                                                        //~vaj7I~
        }                                                          //~vai5I~
        if (posChii>=0)                                            //~1118R~
        {                                                          //~1118I~
            issueChii(PplayerOther,PeswnOther,posChii,PtdDiscarded,pos);          //~1118R~//~1119R~//~1126R~//~1129R~
	        if (Dump.Y) Dump.println("RACall.callChii return by issueChii posChii="+posChii);//~1118R~//~1129R~//~vafnR~
            return true;                                           //~1118I~
        }                                                          //~1118I~
        if (Dump.Y) Dump.println("RACall.callChii return no action");//~1118R~
        return false;                                              //~1118I~
    } //callChii                                                   //~1118I~
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
        if (Dump.Y) Dump.println("RACall.selectSeqMeld entry player="+PplayerCaller+",eswn="+PeswnCaller+",swTanyao="+PswTanyao+",pos="+Ppos+",shanten="+Pshanten+",itsHand="+Utils.toString(PitsHand,9));//~1119I~//~1129R~//~vaaLR~
        if (Dump.Y) Dump.println("RACall.selectSeqMeld swAcceptShantenDown="+swAcceptShantenDown);//~vaiqI~
		int ctrReach=AG.aPlayers.getCtrReachedPlayer(-1/*Player to be excluded*/);//~vaarI~
        int top=(Ppos/CTR_NUMBER_TILE)*CTR_NUMBER_TILE;            //~1119I~
        int end=top+CTR_NUMBER_TILE;                               //~1119I~
        int minShanten=Pshanten;                                   //~1119I~
        int ctr=1;                                                 //~1119I~
        int posMeld=-1;                                            //~1119I~
        int posMeldShantenDownL=-1,posMeldShantenDownM=-1,posMeldShantenDownR=-1;//~vag2R~
//      int posMeldShantenUp=-1,posMeldShantenUpM=-1,posMeldShantenUpR=-1;//~vag2R~
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
		        if (Dump.Y) Dump.println("RACall.selectSeqMeld chk shantenUp Right");//~vag2I~
                PitsHand[Ppos-2]--; PitsHand[Ppos-1]--;            //~1119I~
//              isShantenUpCall(PitsHand,PctrHand-2,pt);       //~1119I~//~1128R~
                rc=isShantenUpCall(PeswnCaller,PitsHand,PctrHand-2,Pshanten);//~1128R~
                if (rc)                                            //~vag2I~
                	;//posMeldShantenUpR=posMeld;                  //~vag2R~
                else                                               //~vag2I~
                	posMeldShantenDownR=posMeld;                   //~vag2I~
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
		        if (Dump.Y) Dump.println("RACall.selectSeqMeld chk shantenUp Left");//~vag2I~
                PitsHand[Ppos+2]--; PitsHand[Ppos+1]--;            //~1119I~
                rc=isShantenUpCall(PeswnCaller,PitsHand,PctrHand-2,Pshanten);       //~1119I~//~1128R~
                if (rc)                                            //~vag2I~
                	;//posMeldShantenUpL=posMeld;                  //~vag2R~
                else                                               //~vag2I~
                	posMeldShantenDownL=posMeld;                   //~vag2I~
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
		        if (Dump.Y) Dump.println("RACall.selectSeqMeld chk shantenUp Middle");//~vag2I~
                PitsHand[Ppos-1]--; PitsHand[Ppos+1]--;            //~1119I~
                rc=isShantenUpCall(PeswnCaller,PitsHand,PctrHand-2,Pshanten);       //~1119I~//~1128R~
                if (rc)                                            //~vag2I~
                	;//posMeldShantenUpM=posMeld;                  //~vag2R~
                else                                               //~vag2I~
                	posMeldShantenDownM=posMeld;                   //~vag2I~
                if (rc)                                            //~vaarI~
                	if (!chkWinTileCtr(PeswnCaller,Pshanten,ctrReach,PitsHand,PctrHand-2))//~vaarR~
                		rc=false;                                  //~vaarI~
                PitsHand[Ppos-1]++; PitsHand[Ppos+1]++;            //~1119I~
                if (!rc)                                           //~1128I~
                	posMeld=-1;                                    //~1128I~
            }                                                      //~1119I~
        }                                                          //~1119I~
        int posMeldM=posMeld;                                      //~vaaLI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeld swAcceptShhantenDown="+swAcceptShantenDown+",posMeldR="+posMeldR+",posMeldL="+posMeldL+",posMeldM="+posMeldM);//~vag2R~
      if (swAcceptShantenDown && (posMeldR<0 && posMeldM<0 && posMeldL<0))//~vag2R~
      {                                                            //~vag2I~
//        if (posMeld<0)                                           //~vag2R~
//            posMeld=posMeldShantenDownR;                         //~vag2R~
//        if (posMeld<0)                                           //~vag2R~
//            posMeld=posMeldShantenDownL;                         //~vag2R~
//        if (posMeld<0)                                           //~vag2R~
//            posMeld=posMeldShantenDownM;                         //~vag2R~
//      posMeld=selectSeqMeldShantenDown(top,posMeldShantenUpR,posMeldShantenUpL,posMeldShantenUpM,posMeldShantenDownR,posMeldShantenDownL,posMeldShantenDownM,PitsHand);    //consider dora//~vag2R~
        posMeld=selectSeqMeldShantenDown(top,posMeldShantenDownR,posMeldShantenDownL,posMeldShantenDownM,PitsHand);    //consider dora//~vag2I~
      }                                                            //~vag2I~
      else                                                         //~vag2I~
        posMeld=selectSeqMeldDora(PplayerCaller,PswTanyao,PitsHand,top,posMeldR,posMeldL,posMeldM);    //consider dora//~vaaLR~
//      posMeld=pt.y;                                              //~1119I~//~1128R~
        if (Dump.Y) Dump.println("RACall.selectSeqMeld exit posMeld="+posMeld);//~1119I~//~1129R~
        return posMeld;                                            //~1119I~//~1129R~
    }                                                              //~1119I~
    //***********************************************************************//~vag2I~
    //*select meld at pos of MatchColor                            //~vag2I~
    //***********************************************************************//~vag2I~
//  private int selectSeqMeldForSameColor(int Pplayer,int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Ppos,boolean PswAllInHand)//~vag2I~//~vaiqR~
    private int selectSeqMeldForSameColor(int Pplayer,int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Ppos,boolean PswAllInHand,int Pintent)//~vaiqI~
    {                                                              //~vag2I~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldForSameColor entry eswn="+Peswn+",shanten="+Pshanten+",swAllInHand="+PswAllInHand+",fix2="+RS.swFix2+",pos="+Ppos+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vag2R~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldForSameColor intent="+Integer.toHexString(Pintent));//~vaiqI~
        boolean swCall;                                            //~vag2I~
		if (RS.swFix2 && !RS.RSP[Peswn].isFixed1())     //1han already fixed//~vag2I~
        {                                                          //~vag2I~
            swCall=true;                                           //~vag2I~
        }                                                          //~vag2I~
        else                                                       //~vag2I~
        {                                                          //~vag2I~
            if (PswAllInHand)                                       //~vag2I~
            {                                                      //~vag2I~
            	if (Pshanten>=2)                                   //~vag2R~
                	swCall=true;                                   //~vag2I~
                else                                               //~vag2I~
                	swCall=false;                                  //~vag2I~
            }                                                      //~vag2I~
            else                                                   //~vag2I~
                swCall=true;                                       //~vag2I~
        }                                                          //~vag2I~
        swAcceptShantenDown=swCall;                                //~vag2I~
        if (RADS.getCtrOtherColorInHand(Peswn,Pintent)==0)	           //~vaiqI~
        	swAcceptShantenDown=false;	//if other color to discard is not exist, disallow shanten down//~vaiqI~
    	int posChii=selectSeqMeld(Pplayer,Peswn,false/*PswTanyao*/,Pshanten,PitsHand,PctrHand,Ppos);//~vag2I~
        swAcceptShantenDown=false;                                 //~vag2I~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldForSameColor exit swCall="+swCall+",rc="+posChii);//~vag2R~
        return posChii;                                            //~vag2I~
    }                                                              //~vag2I~
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
    //**********************************************************************************//~vag2I~
    //*select R,L,M meld for shantenbdown; more feasibility        //~vag2I~
    //**********************************************************************************//~vag2I~
//  private int selectSeqMeldShantenDown(int Ptop,int PposUpR,int PposUpL,int PposUpdM,int PposDownR,int PposDownL,int PposDownM,int[] PitsHand)//~vag2R~
    private int selectSeqMeldShantenDown(int Ptop,int PposDownR,int PposDownL,int PposDownM,int[] PitsHand)//~vag2I~
    {                                                              //~vag2I~
    	int num,valueL=-1,valueR=-1,valueM=-1,value,rc,valueHigh=CTR_NUMBER_TILE*CTR_NUMBER_TILE*2;//~vag2R~
//      if (Dump.Y) Dump.println("RACall.selectSeqMeldShantenDown top="+Ptop+",posUpR="+PposUpR+",posUpL="+PposUpL+",posUpM="+PposUpM+",posDownR="+posDownR+",posDownL="+posDownL+",posDownM="+posDownM);//~vag2R~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldShantenDown top="+Ptop+",posDownR="+PposDownR+",posDownL="+PposDownL+",posDownM="+PposDownM);//~vag2I~
    	System.arraycopy(PitsHand,0,itsHandWkNum,0,CTR_NUMBER_TILE);//~vag2I~
        if (PposDownR>=0)                                          //~vag2I~
        {                                                          //~vag2I~
	        if (PposDownL>=0 || PposDownM>=0)                      //~vag2I~
            {                                                      //~vag2I~
                num=PposDownR/CTR_NUMBER_TILE;                     //~vag2R~
                itsHandWkNum[num]--; itsHandWkNum[num+1]--;        //~vag2R~
                valueR=evaluateSeqSelection(itsHandWkNum);         //~vag2R~
                System.arraycopy(PitsHand,0,itsHandWkNum,0,CTR_NUMBER_TILE);//~vag2R~
            }                                                      //~vag2I~
            else                                                   //~vag2I~
            	valueR=valueHigh;                                  //~vag2I~
        }                                                          //~vag2I~
        value=valueR;                                              //~vag2I~
        if (PposDownL>=0)                                          //~vag2I~
        {                                                          //~vag2I~
	        if (PposDownR>=0 || PposDownM>=0)                      //~vag2I~
            {                                                      //~vag2I~
                num=PposDownL/CTR_NUMBER_TILE;                     //~vag2R~
                itsHandWkNum[num+1]--; itsHandWkNum[num+2]--;      //~vag2R~
                valueL=evaluateSeqSelection(itsHandWkNum);         //~vag2R~
                System.arraycopy(PitsHand,0,itsHandWkNum,0,CTR_NUMBER_TILE);//~vag2R~
            }                                                      //~vag2I~
            else                                                   //~vag2I~
            	valueL=valueHigh;                                  //~vag2I~
        }                                                          //~vag2I~
        value=Math.max(value,valueL);                              //~vag2I~
        if (PposDownM>=0)                                          //~vag2I~
        {                                                          //~vag2I~
	        if (PposDownR>=0 || PposDownL>=0)                      //~vag2I~
            {                                                      //~vag2I~
                num=PposDownM/CTR_NUMBER_TILE;                     //~vag2R~
                itsHandWkNum[num]--; itsHandWkNum[num+1]--;        //~vag2R~
                valueM=evaluateSeqSelection(itsHandWkNum);         //~vag2R~
                System.arraycopy(PitsHand,0,itsHandWkNum,0,CTR_NUMBER_TILE);//~vag2R~
            }                                                      //~vag2I~
            else                                                   //~vag2I~
		        valueM=valueHigh;                                  //~vag2I~
        }                                                          //~vag2I~
        value=Math.max(value,valueM);                              //~vag2I~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldShantenDown value="+value+",valueR="+valueR+",valueL="+valueL+",valueM="+valueM);//~vag2I~
        if (value==valueR)                                         //~vag2I~
        	rc=PposDownR;                                          //~vag2I~
        else                                                       //~vag2I~
        if (value==valueL)                                         //~vag2I~
        	rc=PposDownL;                                          //~vag2I~
        else                                                       //~vag2I~
        	rc=PposDownM;                                          //~vag2I~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldShantenDown exit rc="+rc);//~vag2I~
        return rc;                                                 //~vag2I~
    }                                                              //~vag2I~
    //**********************************************************************************//~vag2I~
    private int evaluateSeqSelection(int[] PitsHandNum)            //~vag2R~
    {                                                              //~vag2I~
        int value=0,ctr,ctrCont;                                   //~vag2I~
        if (Dump.Y) Dump.println("RACall.evaluateSeqSelection itsHandNum="+Utils.toString(PitsHandNum));//~vag2R~
        for (int kk=0;kk<2;kk++)                                       //~vag2I~
        {                                                          //~vag2I~
	        ctrCont=0;                                             //~vag2I~
            for (int ii=0;ii<CTR_NUMBER_TILE;ii++)                 //~vag2R~
            {                                                      //~vag2R~
                ctr=PitsHandNum[ii];                               //~vag2R~
                if (ctr!=0)                                        //~vag2R~
                {                                                  //~vag2R~
                    ctrCont++;                                     //~vag2R~
                }                                                  //~vag2R~
                else                                               //~vag2R~
                if (ctrCont>0)                                     //~vag2I~
                {                                                  //~vag2R~
                    for (int jj=0;jj<ctrCont;jj++)                 //~vag2R~
                        PitsHandNum[ii-ctrCont+jj]--;              //~vag2R~
                    value+=ctrCont*ctrCont;                        //~vag2R~
			        if (Dump.Y) Dump.println("RACall.evaluateSeqSelection kk="+kk+",ii="+ii+",ctrCont="+ctrCont+",value="+value);//~vag2I~
                    ctrCont=0;                                     //~vag2R~
                }                                                  //~vag2R~
            }                                                      //~vag2R~
        }                                                          //~vag2I~
        if (Dump.Y) Dump.println("RACall.evaluateSeqSelection exit value="+value);//~vag2I~
        return value;                                              //~vag2I~
    }                                                              //~vag2I~
    //***********************************************************************//~vaf9I~
    //*confirm melds other than meld by chii are prepared in hand       //~vaf9I~//~vafbR~
    //***********************************************************************//~vaf9I~
    private int selectSeqMeldForStraight(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Ppos)//~vaf9R~
    {                                                              //~vaf9I~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight eswn="+Peswn+",pos="+Ppos+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaf9R~
        int color=RS.getColorStraight(Peswn);                      //~vaf9I~
        int num1,pos=-1;                                           //~vaf9R~
//  	if (!isFixedStraight(Peswn,PitsHand,color,Ppos))           //~vaf9I~//~vafbR~
//      {                                                          //~vaf9I~//~vafbR~
//      	if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight return -1 by isFixedStraight");//~vaf9I~//~vafbR~
//      	return -1;                                             //~vaf9I~//~vafbR~
//      }                                                          //~vaf9I~//~vafbR~
        if (Ppos/CTR_NUMBER_TILE!=color)                           //+vaKNI~
        {                                                          //+vaKNI~
        	if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight @@@@ return -1 by different color");//+vaKNI~
        	return -1;                                             //+vaKNI~
        }                                                          //+vaKNI~
        int num=Ppos%CTR_NUMBER_TILE;                              //~vaf9I~
        if (num>=TN1 && num<=TN3)                                  //~vaf9I~
            num1=TN1;                                              //~vaf9R~
        else                                                       //~vaf9I~
        if (num>=TN4 && num<=TN6)                                  //~vaf9I~
            num1=TN4;                                              //~vaf9R~
        else                                                       //~vaf9I~
            num1=TN7;                                              //~vaf9R~
//      if (Pshanten!=1	//to shanten=0 by chii                     //~vaf9R~
//  	||  chkWaitingFixedAfterChiiStraight(Peswn,Pshanten,PitsHand,PctrHand,color,num1,num))//~vaf9R~
    	if (isFixedAfterChiiStraight(Peswn,Pshanten,PitsHand,PctrHand,color,num1))//~vaf9R~
        	pos=color*CTR_NUMBER_TILE+num1;                        //~vaf9I~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight return num="+num1+",pos="+pos);//~vaf9R~
        return pos;                                                //~vaf9R~
    }                                                              //~vaf9I~
    //***********************************************************************//~vafjI~
    //*select meld at shanten=1 and after once called by intente:straight//~vafjI~
    //***********************************************************************//~vafjI~
    private int selectSeqMeldForStraight2ndCall(int Peswn,int[] PitsHand,int PctrHand,int Ppos,int Pshanten)//~vafjI~
    {                                                              //~vafjI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight2ndCall eswn="+Peswn+",pos="+Ppos+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafjI~
        int posChii=-1;                                             //~vafjI~
        int ctrOK;                                                 //~vafjI~
        Point colorAndNum=RS.getEarthColorStraight(Peswn);	//color and num of 1 part of sytraight//~vafjI~
        if (colorAndNum==null)                                     //~vafjI~
        {                                                          //~vafjI~
    	    if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight2ndCall return -1 by earth colorAndNum");//~vafjI~
            return -1;                                             //~vafjI~
        }                                                          //~vafjI~
        int colorEarth=colorAndNum.x;                              //~vafjI~
        int numEarth=colorAndNum.y;                                //~vafjI~
        int type=Ppos/CTR_NUMBER_TILE;                             //~vafjI~
        int num=Ppos%CTR_NUMBER_TILE;                              //~vafjI~
        int numTop=-1;                                             //~vafjI~
    	if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight2ndCall type="+type+",colorEarth="+colorEarth+",numEarth="+numEarth);//~vafjI~
        boolean swOK;
        if (type==colorEarth)	                                   //~vafjI~
        {                                                          //~vafjI~
        	numTop=(num/PAIRCTR)*PAIRCTR;                          //~vafjI~
	    	ctrOK=chkFixedAfterChiiStraight(Peswn,Pshanten,PitsHand,PctrHand,colorEarth,numTop,true/*last part shoud be complete Meld*/);//last meld shouldbe fixed if FIX_FIRST//~vafjI~
            swOK=ctrOK==1;                                         //~vafjI~
        }                                                          //~vafjI~
        else                                                       //~vafjI~
        {                                                          //~vafjI~
        	if (optYakuFixTiming!=YAKUFIX_LAST)                         //~vafjI~
            {                                                      //~vafjI~
    	    	if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight2ndCall return -1 by YakuFix optYakuFixTiming="+optYakuFixTiming+",type="+type+",earthColor="+colorEarth);//~vafjI~
            	return -1;                                         //~vafjI~
            }                                                      //~vafjI~
	    	ctrOK=chkFixedAfterChiiStraight(Peswn,Pshanten,PitsHand,PctrHand,colorEarth,numEarth,false/*remaining part should be complete Meld*/);	//if FIX_LAST, allow not complete meld but fixed(kanchan/penchan)//~vafjI~//~vafkR~
            swOK=ctrOK==2;                                         //~vafjI~
        }                                                          //~vafjI~
        if (!swOK)                                                 //~vafjI~
        {                                                          //~vafjI~
    	    if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight2ndCall return -1 by ctrOK="+ctrOK);//~vafjI~
            return -1;                                             //~vafjI~
        }                                                          //~vafjI~
        if (type==colorEarth)                                      //~vafjI~
        {                                                          //~vafjI~
	    	if (isShantenUp(PitsHand,PctrHand,Ppos,1/*Pctr*/,Pshanten))	//go if bacoame shanten=0//~vafjI~
	        	posChii=type*CTR_NUMBER_TILE+numTop;                //~vafjR~
        }    		                                               //~vafjI~
        else                                                       //~vafjI~
        {                                                          //~vafjI~
        	int player=RS.RSP[Peswn].player;                       //~vafjI~
    		posChii=selectSeqMeld(player,Peswn,false/*PswTanyao*/,Pshanten,PitsHand,PctrHand,Ppos);//~vafjI~
        }                                                          //~vafjI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldForStraight2ndCall return posChii="+posChii);//~vafjI~
        return posChii;                                            //~vafjI~
    }                                                              //~vafjI~
    //***********************************************************************//~vafkI~
    //*select meld at shanten=1 and after once called by intente:3sameseq//~vafkI~
    //***********************************************************************//~vafkI~
    private int selectSeqMeldFor3SameSeq2ndCall(int Peswn,int[] PitsHand,int PctrHand,int Ppos,int Pshanten)//~vafkI~
    {                                                              //~vafkI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldFor3SameSeq2ndCall eswn="+Peswn+",pos="+Ppos+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafkI~
        int posChii=-1;                                            //~vafkI~
        int ctrOK;                                                 //~vafkI~
        Point colorAndNum=RS.getEarthColorAndNum3SameSeq(Peswn);	//color and num of 1 part of sytraight//~vafkI~
        if (colorAndNum==null)                                     //~vafkI~
        {                                                          //~vafkI~
    	    if (Dump.Y) Dump.println("RACall.selectSeqMeldFor3SameSeq2ndCall return -1 by earth colorAndNum=null");//~vafkI~
            return -1;                                             //~vafkI~
        }                                                          //~vafkI~
        int colorEarth=colorAndNum.x;                              //~vafkI~
        int numEarth=colorAndNum.y;                                //~vafkI~
        int type=Ppos/CTR_NUMBER_TILE;                             //~vafkI~
        int num=Ppos%CTR_NUMBER_TILE;                              //~vafkI~
    	if (Dump.Y) Dump.println("RACall.selectSeqMeldFor3SameSeq2ndCall type="+type+",colorEarth="+colorEarth+",numEarth="+numEarth+",num="+num+",optYakuFixTming="+optYakuFixTiming);//~vafkR~
        boolean swOK;                                              //~vafkI~
        boolean sw2nd=false;                                       //~vafkI~
        if (type!=colorEarth && (num>=numEarth && num<numEarth+PAIRCTR))//~vafkR~
        {                                                          //~vafkI~
	    	ctrOK=chkFixedAfterChii3SameSeq(Peswn,Pshanten,PitsHand,PctrHand,colorEarth,numEarth,Ppos,true/*last part shoud be complete Meld*/);//last meld shouldbe fixed if FIX_FIRST//~vafkR~
            swOK=ctrOK==1;                                         //~vafkI~
	        sw2nd=true;                                            //~vafkI~
        }                                                          //~vafkI~
        else                                                       //~vafkI~
        {                                                          //~vafkI~
        	if (optYakuFixTiming!=YAKUFIX_LAST)                    //~vafkI~
            {                                                      //~vafkI~
    	    	if (Dump.Y) Dump.println("RACall.selectSeqMeldFor3SameSeq2ndCall return -1 by YakuFix optYakuFixTiming="+optYakuFixTiming+",type="+type+",earthColor="+colorEarth);//~vafkI~
            	return -1;                                         //~vafkI~
            }                                                      //~vafkI~
	    	ctrOK=chkFixedAfterChii3SameSeq(Peswn,Pshanten,PitsHand,PctrHand,colorEarth,numEarth,Ppos,false/*remaining part should be complete Meld*/);	//if FIX_LAST, allow not complete meld but fixed(kanchan/penchan)//~vafkR~
            swOK=ctrOK==2;                                         //~vafkI~
        }                                                          //~vafkI~
        if (!swOK)                                                 //~vafkI~
        {                                                          //~vafkI~
    	    if (Dump.Y) Dump.println("RACall.selectSeqMeldFor3SameSeq2ndCall return -1 by ctrOK="+ctrOK);//~vafkI~
            return -1;                                             //~vafkI~
        }                                                          //~vafkI~
        if (sw2nd) //chii 2/3 of 3sameseq                          //~vafkR~
        {                                                          //~vafkI~
	    	if (isShantenUp(PitsHand,PctrHand,Ppos,1/*Pctr*/,Pshanten))	//go if bacoame shanten=0//~vafkI~
	        	posChii=type*CTR_NUMBER_TILE+numEarth;             //~vafkR~
        }                                                          //~vafkI~
        else                                                       //~vafkI~
        {                                                          //~vafkI~
        	int player=RS.RSP[Peswn].player;                       //~vafkI~
    		posChii=selectSeqMeld(player,Peswn,false/*PswTanyao*/,Pshanten,PitsHand,PctrHand,Ppos);//~vafkI~
        }                                                          //~vafkI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldFor3SameSeq2ndCall return posChii="+posChii);//~vafkI~
        return posChii;                                            //~vafkI~
    }                                                              //~vafkI~
    //***********************************************************************//~vafbI~
    //*confirm melds other than meld by chii are prepared in hand  //~vafbI~
    //***********************************************************************//~vafbI~
    private int selectSeqMeldFor3SameSeq(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Ppos)//~vafbI~
    {                                                              //~vafbI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldFor3SameSeq eswn="+Peswn+",pos="+Ppos+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafbI~
        int numTop=RS.getNum3SameSeq(Peswn);                       //~vafbI~
        int pos=-1;                                                //~vafbI~
//  	if (!isFixed3SameSeq(Peswn,PitsHand,color,Ppos))           //~vafbR~
//      {                                                          //~vafbR~
//      	if (Dump.Y) Dump.println("RACall.selectSeqMeldFor3SameSeq return -1 by isFixed3SameSeq");//~vafbR~
//      	return -1;                                             //~vafbR~
//      }                                                          //~vafbR~
    	if (isFixedAfterChii3SameSeq(Peswn,Pshanten,PitsHand,PctrHand,numTop,Ppos))//~vafbR~
        	pos=(Ppos/CTR_NUMBER_TILE)*CTR_NUMBER_TILE+numTop;      //~vafbR~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldFor3SameSeq return numTop="+numTop+",pos="+pos);//~vafbR~
        return pos;                                                //~vafbI~
    }                                                              //~vafbI~
    //***********************************************************************//~vafcI~
    //*confirm melds other than meld by chii are prepared in hand  //~vafcI~
    //***********************************************************************//~vafcI~
    private int selectSeqMeldForTanyao(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Ppos,int Paction)//~vafcI~
    {                                                              //~vafcI~
        if (Dump.Y) Dump.println("RACall.selectMeldForTanyao action="+Paction+",eswn="+Peswn+",pos="+Ppos+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafcI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldForTanyao return rc="+posIssueTanyao);//~vafcR~//~vaffR~
        return posIssueTanyao;                                     //~vafcR~
    }                                                              //~vafcI~
    //***********************************************************************//~vaffI~
    //*confirm melds other than meld by chii are prepared in hand  //~vaffI~
    //***********************************************************************//~vaffI~
    private int selectSeqMeldForChanta(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Ppos,int Paction)//~vaffI~
    {                                                              //~vaffI~
        if (Dump.Y) Dump.println("RACall.selectMeldForChanta action="+Paction+",eswn="+Peswn+",pos="+Ppos+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaffI~
        if (Dump.Y) Dump.println("RACall.selectSeqMeldForChanta return rc="+posIssueChanta);//~vaffI~
        return posIssueChanta;                                     //~vaffI~
    }                                                              //~vaffI~
    //***********************************************************************//~vaf9M~
    //*under the condition max missing num=1(1/2 was completed by chii)//~vaf9M~
    //***********************************************************************//~vaf9M~
    private boolean isFixedAfterChiiStraight(int Peswn,int Pshanten,int[] PitsH,int PctrHand,int Pcolor,int PnumTop)//~vaf9R~
    {                                                              //~vaf9I~
    	int ctrOK=chkFixedAfterChiiStraight(Peswn,Pshanten,PitsH,PctrHand,Pcolor,PnumTop,false/*part may be not complete Meld*/);//~vafjI~
        boolean rc=ctrOK==2;                                       //~vafjI~
        if (Dump.Y) Dump.println("RACall.isFixedAfterChiiStraight ctrOK="+ctrOK+",rc="+rc);//~vafjI~
        return rc;                                                 //~vafjI~
    }                                                              //~vafjI~
    //***********************************************************************//~vafjI~
    private int chkFixedAfterChiiStraight(int Peswn,int Pshanten,int[] PitsH,int PctrHand,int Pcolor,int PnumTop,boolean PswComplete)//~vafjI~
    {                                                              //~vafjI~
        if (Dump.Y) Dump.println("RACall.chkFixedAfterChiiStraight swComplete="+PswComplete+",eswn="+Peswn+",color="+Pcolor+",numTop="+PnumTop+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsH,9));//~vaf9R~//~vafjR~
        int posTop=Pcolor*CTR_NUMBER_TILE;                         //~vaf9I~
        int ctrOK=0;                                               //~vaf9I~
        if (Dump.Y) Dump.println("RACall.chkFixedAfterChiiStraight posTop="+posTop+",itsHand="+Utils.toString(PitsH,9));//~vaf9R~//~vafjR~
        if (PnumTop==TN1)	//chii for 1-2-3                       //~vaf9R~
        {                                                          //~vaf9M~
        	if (PswComplete)                                       //~vafjI~
            {                                                      //~vafjI~
	        	if (PitsH[posTop+TN4]!=0 && PitsH[posTop+TN5]!=0 && PitsH[posTop+TN6]!=0)//~vafjI~
    	            ctrOK++;           //456 is complete           //~vafjI~
            }                                                      //~vafjI~
            else                                                   //~vafjI~
        	if (PitsH[posTop+TN5]==0)                              //~vaf9I~
                ctrOK++;           //4(5)6 is kanchan                //~vaf9R~//~vafjR~
            else                                                   //~vaf9M~
        	if (PitsH[posTop+TN4]!=0 && PitsH[posTop+TN6]!=0)      //~vaf9I~
                ctrOK++;           //456 is complete               //~vaf9R~
                                                                   //~vaf9M~
        	if (PswComplete)                                       //~vafjI~
            {                                                      //~vafjI~
	        	if (PitsH[posTop+TN7]!=0 && PitsH[posTop+TN8]!=0 && PitsH[posTop+TN9]!=0)//~vafjI~
    	            ctrOK++;           //789 is complete           //~vafjI~
            }                                                      //~vafjI~
            else                                                   //~vafjI~
            if (PitsH[posTop+TN7]==0 || PitsH[posTop+TN8]==0)      //~vaf9I~
                ctrOK++;           //(7)89 or 7(8)9                //~vafjR~
            else                                                   //~vaf9M~
        	if (PitsH[posTop+TN9]!=0)                              //~vaf9I~
                ctrOK++;           //78(9)                         //~vafjR~
        }                                                          //~vaf9M~
        else                                                       //~vaf9M~
        if (PnumTop==TN4)	//chii for 4-5-6                       //~vaf9R~
        {                                                          //~vaf9M~
        	if (PswComplete)                                       //~vafjI~
            {                                                      //~vafjI~
	        	if (PitsH[posTop+TN1]!=0 && PitsH[posTop+TN2]!=0 && PitsH[posTop+TN3]!=0)//~vafjI~
    	            ctrOK++;           //123 is complete           //~vafjI~
            }                                                      //~vafjI~
            else                                                   //~vafjI~
        	if (PitsH[posTop+TN2]==0 || PitsH[posTop+TN3]==0)      //~vaf9I~
                ctrOK++;           //1(2)3 or 12(3)                //~vafjR~
            else                                                   //~vaf9M~
        	if (PitsH[posTop+TN1]!=0)                              //~vaf9I~
                ctrOK++;           //123 is complete               //~vaf9I~//~vafjR~
                                                                   //~vaf9M~
        	if (PswComplete)                                       //~vafjI~
            {                                                      //~vafjI~
	        	if (PitsH[posTop+TN7]!=0 && PitsH[posTop+TN8]!=0 && PitsH[posTop+TN9]!=0)//~vafjI~
    	            ctrOK++;           //789 is complete           //~vafjI~
            }                                                      //~vafjI~
            else                                                   //~vafjI~
            if (PitsH[posTop+TN7]==0 || PitsH[posTop+TN8]==0)      //~vaf9I~
                ctrOK++;           //(7)89 7(8)9                   //~vafjR~
            else                                                   //~vaf9M~
        	if (PitsH[posTop+TN9]!=0)                              //~vaf9I~
                ctrOK++;           //789 is complete               //~vaf9I~//~vafjR~
        }                                                          //~vaf9M~
        else  //PnumTop==TN7                                       //~vaf9R~
        {                                                          //~vaf9M~
        	if (PswComplete)                                       //~vafjI~
            {                                                      //~vafjI~
	        	if (PitsH[posTop+TN4]!=0 && PitsH[posTop+TN5]!=0 && PitsH[posTop+TN6]!=0)//~vafjI~
    	            ctrOK++;           //456 is complete           //~vafjI~
            }                                                      //~vafjI~
            else                                                   //~vafjI~
        	if (PitsH[posTop+TN5]==0)                              //~vaf9I~
                ctrOK++;           //4(5)6                         //~vafjR~
            else                                                   //~vaf9M~
        	if (PitsH[posTop+TN4]!=0 && PitsH[posTop+TN6]!=0)      //~vaf9I~
                ctrOK++;           //456                           //~vafjR~
                                                                   //~vaf9M~
        	if (PswComplete)                                       //~vafjI~
            {                                                      //~vafjI~
	        	if (PitsH[posTop+TN1]!=0 && PitsH[posTop+TN2]!=0 && PitsH[posTop+TN3]!=0)//~vafjI~
    	            ctrOK++;           //123 is complete           //~vafjI~
            }                                                      //~vafjI~
            else                                                   //~vafjI~
        	if (PitsH[posTop+TN2]==0 || PitsH[posTop+TN3]==0)      //~vaf9I~
                ctrOK++;           //1(2)3 or 12(3)                //~vafjR~
            else                                                   //~vaf9M~
        	if (PitsH[posTop+TN1]!=0)                              //~vaf9I~
                ctrOK++;           //123 is complete               //~vaf9I~//~vafjR~
        }                                                          //~vaf9M~
//      boolean rc=ctrOK==2;                                       //~vaf9I~//~vafjR~
        int rc=ctrOK;                                              //~vafjI~
        if (Dump.Y) Dump.println("RACall.chkFixedAfterChiiStraight ctrOK="+ctrOK);//~vaf9R~//~vafjR~
        return rc;                                                 //~vaf9M~
    }                                                              //~vaf9M~
    //***********************************************************************//~vafbI~
    //*under the condition max missing num=1(1/2 was completed by chii)//~vafbI~
    //***********************************************************************//~vafbI~
    private boolean isFixedAfterChii3SameSeq(int Peswn,int Pshanten,int[] PitsH,int PctrHand,int PnumTop,int Ppos)//~vafbR~
    {                                                              //~vafbI~
    	int ctrOK=chkFixedAfterChii3SameSeq(Peswn,Pshanten,PitsH,PctrHand,-1/*colorEarth*/,PnumTop,Ppos,false);//~vafkR~
        boolean rc=ctrOK==2;                                       //~vafkI~
        if (Dump.Y) Dump.println("RACall.isFixedAfterChii3SameSeq ctrOK="+ctrOK+",rc="+rc);//~vafkI~
        return rc;                                                 //~vafkI~
    }                                                              //~vafkI~
    //***********************************************************************//~vafkI~
    private int chkFixedAfterChii3SameSeq(int Peswn,int Pshanten,int[] PitsH,int PctrHand,int PcolorEarth,int PnumEarth,int Ppos,boolean PswComplete)//~vafkR~
    {                                                              //~vafkI~
        if (Dump.Y) Dump.println("RACall.chkFixedAfterChii3SameSeq eswn="+Peswn+",colorEarth="+PcolorEarth+",numEarth="+PnumEarth+",pos="+Ppos+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsH,9));//~vafbR~//~vafkR~
        int color=Ppos/CTR_NUMBER_TILE;                            //~vafbI~
        int ctrOK=0;                                               //~vafbI~
        for (int ii=0;ii<3;ii++)                                   //~vafbI~
        {                                                          //~vafbI~
            if (ii==PcolorEarth)                                   //~vafkI~
                continue;                                          //~vafkI~
        	int pos=ii*CTR_NUMBER_TILE+PnumEarth;                    //~vafbI~//~vafkR~
            int ctr0=PitsH[pos];                                   //~vafbI~
            int ctr1=PitsH[pos+1];                                 //~vafbI~
            int ctr2=PitsH[pos+2];                                 //~vafbI~
        	if (Dump.Y) Dump.println("RACall.chkFixedAfterChii3SameSeq ii="+ii+",pos="+pos+",color="+color+",ctr0="+ctr0+",ctr1="+ctr1+",ctr2="+ctr2);//~vafbI~//~vafkR~
            if (ctr0!=0 && ctr1!=0 && ctr2!=0)                     //~vafbI~
                ctrOK++;           //456 is complete               //~vafbI~
            else                                                   //~vafbI~
            if (PswComplete)                                       //~vafkI~
            	;                                                  //~vafkI~
            else                                                   //~vafkI~
            if (PnumEarth==TN1 && (ctr1==0 || ctr2==0))   //1(2)3 or 12(3)//~vafbR~//~vafkR~
                ctrOK++;                                           //~vafbR~
            else                                                   //~vafbI~
            if (PnumEarth==TN7 && (ctr0==0 || ctr1==0))   //(7)89 or 7(8)9//~vafbI~//~vafkR~
                ctrOK++;                                           //~vafbI~
            else                                                   //~vafbR~
            if (ctr1==0)            //4(5)6                        //~vafbR~
                ctrOK++;                                           //~vafbR~
        }                                                          //~vafbI~
//      boolean rc=ctrOK==2;                                       //~vafbI~//~vafkR~
        int rc=ctrOK;                                              //~vafkI~
        if (Dump.Y) Dump.println("RACall.chkFixedAfterChii3SameSeq ctrOK="+ctrOK);//~vafbI~//~vafkR~
        return rc;                                                 //~vafbI~
    }                                                              //~vafbI~
//    //***********************************************************************//~vaf9R~
//    //*confirm multiple waiting tile after Chii                  //~vaf9R~
//    //***********************************************************************//~vaf9R~
//    private boolean chkWaitingFixedAfterChiiStraight(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Pcolor,int PnumTop/*Pair starting Num*/,int Pnum)//~vaf9R~
//    {                                                            //~vaf9R~
//        if (Dump.Y) Dump.println("RACall.chkWaitingAfterChiiStraight eswn="+Peswn+",color="+Pcolor+",numTop="+PnumTop+",num="+Pnum+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaf9R~
//        int posTop=Pcolor*CTR_NUMBER_TILE;                       //~vaf9R~
//        for (int ii=0;ii<CTR_NUMBER_TILE;ii++)                   //~vaf9R~
//        {                                                        //~vaf9R~
//            if (ii!=Pnum)                                        //~vaf9R~
//                if (ii>=PnumTop && ii<PnumTop+PAIRCTR)           //~vaf9R~
//                    PitsHand[posTop+ii]--;                       //~vaf9R~
//        }                                                        //~vaf9R~
//        boolean rc=isFixedAfterChiiStraight(PitsHand,posTop,PnumTop);//~vaf9R~
//        for (int ii=0;ii<CTR_NUMBER_TILE;ii++)                   //~vaf9R~
//        {                                                        //~vaf9R~
//            if (ii!=Pnum)                                        //~vaf9R~
//                if (ii>=PnumTop && ii<PnumTop+PAIRCTR)           //~vaf9R~
//                    PitsHand[posTop+ii]++;                       //~vaf9R~
//        }                                                        //~vaf9R~
//        if (Dump.Y) Dump.println("RACall.chkWaitingAfterChiiStraight rc="+rc);//~vaf9R~
//        return rc;                                               //~vaf9R~
//    }                                                            //~vaf9R~
//    //***********************************************************************//~vaf9R~
//    //*confirm multiple waiting tile after Chii                  //~vaf9R~
//    //***********************************************************************//~vaf9R~
//    private boolean chkWaitingFixedAfterChiiStraight(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Pcolor,int PnumTop/*Pair starting Num*/,int Pnum)//~vaf9R~
//    {                                                            //~vaf9R~
//        if (Dump.Y) Dump.println("RACall.chkWaitingAfterChiiStraight eswn="+Peswn+",color="+Pcolor+",numTop="+PnumTop+",num="+Pnum+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaf9R~
//        int posTop=Pcolor*CTR_NUMBER_TILE;                       //~vaf9R~
//        for (int ii=0;ii<CTR_NUMBER_TILE;ii++)                   //~vaf9R~
//        {                                                        //~vaf9R~
//            if (ii!=Pnum)                                        //~vaf9R~
//                if (ii>=PnumTop && ii<PnumTop+PAIRCTR)           //~vaf9R~
//                    PitsHand[posTop+ii]--;                       //~vaf9R~
//        }                                                        //~vaf9R~
//        int ctrH=PctrHand-(PAIRCTR-1);  //2 tile exposed for chii//~vaf9R~
//        boolean rc=getWinListAfterChiiStraight(Peswn,Pshanten,Pcolor,PitsHand,ctrH);//~vaf9R~
//        for (int ii=0;ii<CTR_NUMBER_TILE;ii++)                   //~vaf9R~
//        {                                                        //~vaf9R~
//            if (ii!=Pnum)                                        //~vaf9R~
//                if (ii>=PnumTop && ii<PnumTop+PAIRCTR)           //~vaf9R~
//                    PitsHand[posTop+ii]++;                       //~vaf9R~
//        }                                                        //~vaf9R~
//        if (Dump.Y) Dump.println("RACall.chkWaitingAfterChiiStraight rc="+rc);//~vaf9R~
//        return rc;                                               //~vaf9R~
//    }                                                            //~vaf9R~
//    //***********************************************************************//~vaf9R~
//    private boolean getWinListAfterChiiStraight(int Peswn,int Pshanten,int Pcolor,int[] PitsHand,int PctrHand)//~vaf9R~
//    {                                                            //~vaf9R~
//        if (Dump.Y) Dump.println("RACall.getWinListAfterChiiStraight eswn="+Peswn+",color="+Pcolor+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaf9R~
//        boolean rc=true;                                         //~vaf9R~
//        int posDiscard=-1;                                       //~vaf9R~
//        for (int ii=0;ii<CTR_TILETYPE;ii++)                      //~vaf9R~
//        {                                                        //~vaf9R~
//            if (PitsHand[ii]==0)                                 //~vaf9R~
//                continue;                                        //~vaf9R~
//            PitsHand[ii]--;                                      //~vaf9R~
//            int[] itsWin=AG.aRAReach.getItsWinList(PitsHand,PctrHand-1);//~vaf9R~
//            PitsHand[ii]++;                                      //~vaf9R~
//            int ctrWin=itsWin.length;                            //~vaf9R~
//            if (ctrWin==0)                                       //~vaf9R~
//                continue;                                        //~vaf9R~
//            int ctrWinColor=0,posWin=-1;                         //~vaf9R~
//            for (int jj=0;jj<ctrWin;jj++)                        //~vaf9R~
//            {                                                    //~vaf9R~
//                int colorWin=itsWin[jj]/CTR_NUMBER_TILE;         //~vaf9R~
//                if (colorWin==Pcolor)                            //~vaf9R~
//                {                                                //~vaf9R~
//                    ctrWinColor++;                               //~vaf9R~
//                    posWin=itsWin[jj];                           //~vaf9R~
//                }                                                //~vaf9R~
//            }                                                    //~vaf9R~
//            if (Dump.Y) Dump.println("RACall.getWinListAfterChiiStraight ctrWin="+ctrWin+",ctrWinColor="+ctrWinColor);//~vaf9R~
//            if (ctrWinColor>1)                                   //~vaf9R~
//            {                                                    //~vaf9R~
//                if (Dump.Y) Dump.println("RACall.getWinListAfterChiiStraight return false by multiwait of the color eswn="+Peswn);//~vaf9R~
//                rc=false;                                        //~vaf9R~
//                break;                                           //~vaf9R~
//            }                                                    //~vaf9R~
//            if (posWin!=-1)                                      //~vaf9R~
//            {                                                    //~vaf9R~
//                if (RS.isFuritenSelf(Peswn,posWin))  //ron next take but ron tile is furiteniscard//~vaf9R~
//                {                                                //~vaf9R~
//                    if (Dump.Y) Dump.println("RACall.getWinListAfterChiiStraight return false by furiten eswn="+Peswn);//~vaf9R~
//                    rc=false;                                    //~vaf9R~
//                    break;                                       //~vaf9R~
//                }                                                //~vaf9R~
//                if ((PIECE_DUPCTR-RS.itsExposed[posWin])<=0)     //~vaf9R~
//                {                                                //~vaf9R~
//                    if (Dump.Y) Dump.println("RACall.getWinListAfterChiiStraight return false by no remaining eswn="+Peswn);//~vaf9R~
//                    rc=false;                                    //~vaf9R~
//                    break;                                       //~vaf9R~
//                }                                                //~vaf9R~
//            }                                                    //~vaf9R~
//        }                                                        //~vaf9R~
//        if (Dump.Y) Dump.println("RACall.getWinListAfterChiiStraight rc="+rc);//~vaf9R~
//        return rc;                                               //~vaf9R~
//    }                                                            //~vaf9R~
    //***********************************************************************//~vafnI~
    //*under shanten=0,get discardaable tile list                  //~vafnI~
    //***********************************************************************//~vafnI~
    private int[] getDiscardList(int Peswn,int[] PitsHand,int PctrHand)//~vafnI~
    {                                                              //~vafnI~
		if (Dump.Y) Dump.println("RACall.getDiscardList eswn="+Peswn+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafnR~
        int[] list=new int[PctrHand];                              //~vafnI~
        int ctrList=0;                                             //~vafnI~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~vafnI~
        {                                                          //~vafnI~
        	if (PitsHand[ii]==0)                                   //~vafnI~
            	continue;                                          //~vafnI~
//  		if (RAUtils.getShantenAddCall(PitsHand,PctrHand,ii,-1)==0)//~vafnI~
    		if (chkShantenAddCall(Peswn,PitsHand,PctrHand,ii,-1)==0)  //false if not discardable by pao etc//~vafnI~
            	list[ctrList++]=ii;                                //~vafnI~
        }                                                          //~vafnI~
        int[] listDiscard=new int[ctrList];                        //~vafnI~
        if (ctrList!=0)                                            //~vafnI~
	        System.arraycopy(list,0,listDiscard,0,ctrList);        //~vafnI~
		if (Dump.Y) Dump.println("RACall.getDiscardList exit eswn="+Peswn+",listDiscard="+Utils.toString(listDiscard));//~vafnR~
        return listDiscard;                                        //~vafnI~
    }                                                              //~vafnI~
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
//    //***********************************************************************//~1126I~//~vaf6R~
//    private int getCtrDiscardable(int PeswnPlayer,int[] PitsHand,int Ppos,int PctrAdd)//~1126R~//~vaf6R~
//    {                                                              //~1126I~//~vaf6R~
//        int ctrDiscardable=0;                                      //~1126I~//~vaf6R~
//        if (RS.isDiscardableAll())                                 //~1126I~//~1128I~//~vaf6R~
//            return 0;                                              //~1128I~//~vaf6R~
//        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~1126I~//~vaf6R~
//        {                                                          //~1126I~//~vaf6R~
//            int ctr=PitsHand[ii];                                  //~1126I~//~vaf6R~
//            if (ii==Ppos)                                         //~1126I~//~vaf6R~
//                ctr+=PctrAdd;                                      //~1126I~//~vaf6R~
//            if (ctr>0)                                             //~1126R~//~vaf6R~
//            {                                                      //~1126I~//~vaf6R~
//                if (RAD.isDiscardable(PeswnPlayer,ii))         //~1126I~//~vaf6R~
//                    ctrDiscardable+=ctr;                          //~1126I~//~vaf6R~
//            }                                                      //~1126I~//~vaf6R~
//        }                                                          //~1126I~//~vaf6R~
//        if (Dump.Y) Dump.println("RADSmart.getCtrDiscardable eswn="+PeswnPlayer+",ctrDiscardable="+ctrDiscardable);//~1126R~//~1128R~//~vaf6R~
//        return ctrDiscardable;                                     //~1126R~//~vaf6R~
//    }                                                              //~1126I~//~vaf6R~
    //***********************************************************************//~1126I~
    //*chk discadable tile ctr is kept for pao/openreach           //~1222I~
    //***********************************************************************//~1222I~
    private boolean chkCtrDiscardable(int PeswnPlayer,int[] PitsHand,int Ppos,int PctrAdd)//~1126R~
    {                                                              //~1126I~
    	boolean rc=false;                                          //~1126R~
//      if (RS.isDiscardableAll())                                 //~1128I~//~vaf6R~
        if (RS.isDiscardableAllExceptOpenReach(PeswnPlayer))    //False if openreach available//~vaf6I~
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
				        if (Dump.Y) Dump.println("RADSmart.chkCtrDiscardable ctrDiscardable="+ctrDiscardable);//~vaf6I~
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
        if (Dump.Y) Dump.println("RACall.makeChii ctrPair="+ctrPair+",posChiiStart="+PposChiiStart);//+vaKNI~
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
        boolean swAllInHand=RS.RSP[Peswn].swAllInHand;             //~vafnI~
        boolean swEarly=false;                                     //~vagdR~
        if (Dump.Y) Dump.println("RACall.isTimeToCall entry eswn="+Peswn+",action="+Paction+",swAllInHand="+swAllInHand+",shanten="+PmyShanten+",intent=x"+Integer.toHexString(Pintent)+",td="+Utils.toString(Ptd));//~1306I~//~va8pR~//~vafnR~
        swIssueChiiStraight=false;                                 //~vaf9I~
        swIssueChii3SameSeq=false;                                 //~vafbI~
        swByHonorTile=false;                                       //~vafsI~
//      swChkFix2ndCall=false;                                     //~vafnR~
		actionIssueTanyao=0;                                       //~vafcI~
		actionIssueChanta=0;                                       //~vaffI~
        posFixedCallShanten0_Chii=-1;                              //~vafnR~
        posFixedCallShanten0_Pon=-1;                               //~vafnI~
        if ((TestOption.option2 & TO2_CALL1ST)!=0)                  //~va75I~
        {                                                          //~va75I~
	        if (Dump.Y) Dump.println("RACall.isTimeToCall return true by testoption TO2_CALL1ST");//~va75I~
            return true;                                           //~va75I~
        }                                                          //~va75I~
    	boolean rc=false;                                          //~1306I~
        int[] itsH=RS.getItsHandEswn(Peswn);                       //~vafnI~
        int ctrH=RS.RSP[Peswn].ctrHand;                            //~vafnI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall ctrH="+ctrH+",itsH="+Utils.toString(itsH,9));//~vafnI~
    	int shanten=RS.getCurrentShanten(Peswn);                   //~vaaiI~
		int ctrReach=AG.aPlayers.getCtrReachedPlayer(Pplayer);     //~1306I~
		int ctrDora=RADS.getCtrDoraInHandAndEarth(Peswn);           //~1313I~
        if (Paction!=GCM_KAN)   //kanTaken is included in Hand     //~vagbI~
			ctrDora+=RADS.getCtrDora(Ptd);                         //~vagbI~
	    int ctrTaken=RS.RSP[Peswn].ctrTaken;                       //~1306I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall shanten="+shanten+",ctrReach="+ctrReach+",ctrDora="+ctrDora+",ctrTaken="+ctrTaken);//~vaaiI~//~vaatR~
      	int pos=RAUtils.getPosTile(Ptd);                           //~vaaHI~
		boolean swFixed1=RS.RSP[Peswn].isFixed1();     //1han already fixed//~vaipI~
        for (;;)                                                   //~1306I~
        {                                                          //~1306I~
//      	int pos=RAUtils.getPosTile(Ptd);                               //~1306I~//~vaaHR~
          	if ((Pintent & (INTENT_4ANKO))!=0//4 anko in AllInHand //~vaghR~
			&&  Paction!=GCM_CHII)                                 //~vaghI~
            {                                                      //~vaghI~
            	if (ctrTaken<=HV_TIME_TO_CALL_4ANKO) //     //<=8 save to call up to 3 tiles take//~vaghI~
                {                                                  //~vaghI~
                	if (RADSmart.isPossibilityOf4Anko(Peswn,itsH,RS.itsExposed))//~vaghI~
                    {                                              //~vaghI~
	        			if (Dump.Y) Dump.println("RACall.isTimeToCall INTENT_4ANKO break False by ctrTaken="+ctrTaken);//~vaghR~
            			break;       	// with False              //~vaghR~
                    }                                              //~vaghI~
                }                                                  //~vaghI~
            }                                                      //~vaghI~
//  	    if (Paction!=GCM_KAN && RS.RSP[Peswn].isFixed1())     //1han already fixed//~vafsI~//~vaipR~
    	    if (Paction!=GCM_KAN && swFixed1)     //1han already fixed//~vaipI~
  				if (isTimeToCall_Fixed1(Peswn,PmyShanten,ctrDora,ctrTaken,itsH,ctrH,pos))//~vafsR~
                {                                                  //~vafsI~
	        		if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true by already fixed1");//~vafsI~
                    swByHonorTile=true;                            //~vafsI~
     		   		rc=true;                                       //~vafsI~
            		break;                                         //~vafsI~
                }                                                  //~vafsI~
        	if ((Paction==GCM_PON || Paction==GCM_KAN) && pos>=OFFS_WORDTILE && RADS.isDoraOpen(Ptd))        //call at first if drora//~1306R~
            {                                                      //~1306I~
        		if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true by Pon/Kan Dora wordtile");//~vaf6I~
        		rc=true;                                           //~1306I~
            	break;                                             //~1306I~
            }                                                      //~1306I~
          	if ((Pintent & (INTENT_3DRAGON))!=0)//yakuhai and samecolor|allsame//~vaf6I~
            {                                                      //~vaf6I~
        		if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true by Intent_3dragon");//~vaf6I~
        		rc=true;                                           //~vaf6I~
            	break;                                             //~vaf6I~
            }                                                      //~vaf6I~
            int v=RAUtils.chkValueWordTile(pos,Peswn);             //~1313I~
        	if (Dump.Y) Dump.println("RACall.isTimeToCall v="+v);  //~vaipI~
			if (Paction==GCM_PON)                                  //~1313I~
            {                                                      //~1313I~
        		if (pos>=OFFS_WORDTILE)                            //~1313I~
                {                                                  //~1313I~
                    int tempHan=(swFixed1?1:0)+v/2+ctrDora;        //~vaipI~
                    if (Dump.Y) Dump.println("RACall.isTimeToCall v="+v+",swFixed="+swFixed1+",swAllinHand="+swAllInHand+",ctrDora="+ctrDora+",tempHan="+tempHan);//~vaipR~
                    if (v>0 && swAllInHand && isStatusAimToHigherScore(Peswn,Pplayer,Pintent,tempHan))//~vaipI~
                    {                                              //~vaipI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall return false by StatusAimToHigherScore");//~vaipI~
                        rc=false;                                  //~vaipI~
                        break;                                     //~vaipI~
                    }                                              //~vaipI~
                    if (v/2>1)/*2han*/                             //~1313I~
                    {                                              //~1313I~
                        rc=true;                                   //~1313I~
                        break;                                     //~1313I~
                    }                                              //~1313I~
                    if (RS.RSP[Peswn].getCtrValueWordSameAndPairInHand()>1) //*2 pairs of valueword in hand//~1306R~//~1313I~
                    {                                              //~1306R~//~1313I~
        				if (Dump.Y) Dump.println("RACall.isTimeToCall return true by 2pair of valueword");//~vaipI~
                        rc=true;                                   //~1306R~//~1313I~
                        break;                                     //~1306R~//~1313I~
                    }                                              //~1306R~//~1313I~
                    if (Peswn==ESWN_E && PmyShanten<=HV_PARENT_1STCALL_SHANTEN) //<=3 //~1306R~//~1313I~
                    {                                              //~1306R~//~1313I~
        				if (Dump.Y) Dump.println("RACall.isTimeToCall return true by shanten="+PmyShanten+"<="+HV_PARENT_1STCALL_SHANTEN);//~vabfI~
                        rc=true;                                   //~1306R~//~1313I~
                        break;                                     //~1306R~//~1313I~
                    }                                              //~1306R~//~1313I~
//          		if (!RS.RSP[Peswn].swAllInHand)         //already called//~va8pI~//~vafnR~
//                    if (!swAllInHand)         //already called     //~vafnI~//~vai4R~
//                        if (RS.RSP[Peswn].isFixed1())     //1han already fixed//~va8pR~//~vai4R~
//                        {                                          //~va8pI~//~vai4R~
//                            if (Dump.Y) Dump.println("RACall.isTimeToCall !swAllInHnd and isFixed1 ctrReach="+ctrReach);//~vai4R~
//                            if (Dump.Y) Dump.println("RACall.isTimeToCall return true by already called and fixed");//~va8pI~//~vai4R~
//                            rc=true;                               //~va8pI~//~vai4R~
//                            break;                                 //~va8pI~//~vai4R~
//                        }                                          //~va8pI~//~vai4R~
                    if (v>0 && isStatusHurryUpToGoal(Pplayer))     //~vabfI~
                    {                                              //~vabfI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall return true by StatusHurryToGoal");//~vabfI~
                        rc=true;                                   //~vabfI~
                        break;                                     //~vabfI~
                    }                                              //~vabfI~
//                  if (v>0 && isStatusAimToHigherScore(Pplayer,Pintent))//~vabtM~//~vaipR~
//                    int tempHan=(swFixed1?1:0)+v/2+ctrDora;      //~vaipR~
//                    if (v>0 && swAllInHand && isStatusAimToHigherScore(Peswn,Pplayer,Pintent,tempHan))//~vaipR~
//                    {                                              //~vabtM~//~vaipR~
//                        if (Dump.Y) Dump.println("RACall.isTimeToCall return false by StatusAimToHigherScore");//~vabtM~//~vaipR~
//                        rc=false;                                  //~vabtM~//~vaipR~
//                        break;                                     //~vabtM~//~vaipR~
//                    }                                              //~vabtM~//~vaipR~
                    if (v>0 && isStatusAvoidGrillBird(Pplayer))    //~vabtI~
                    {                                              //~vabtI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall return true by StatusAvoidGrillBird");//~vabtI~
                        rc=true;                                   //~vabtI~
                        break;                                     //~vabtI~
                    }                                              //~vabtI~
                }                                                  //~1313M~
            } //PON                                                //~1313I~
//          if (!RS.RSP[Peswn].swAllInHand                         //~1306I~//~1313M~//~va8pR~
//          ||  ctrTaken<=HV_TIME_TO_CALL) //     //<=3 save to call up to 3 tiles take//~1306R~//~1313I~//~va8pR~
//          if (RS.RSP[Peswn].swAllInHand                          //~va8pI~//~vafnR~
            if (swAllInHand                                        //~vafnI~
            &&  ctrTaken<=HV_TIME_TO_CALL) //     //<=3 save to call up to 3 tiles take//~va8pI~
            {                                                      //~1306I~//~1313M~
            //*Early                                               //~vag3I~
                swEarly=true;                                      //~vagdR~
        		if (Dump.Y) Dump.println("RACall.isTimeToCall swAllInHand and Early case");//~vag7I~
//				if ((Paction==GCM_PON || Paction==GCM_CHII) && (Pintent & INTENT_TANYAO)!=0  && isTimeToCall_ForTanyao(Paction,true,Peswn/*caller*/,Ptd/*Discarded*/,PmyShanten,ctrDora))//~vafcM~//~vafqR~
  				if ((Paction==GCM_PON || Paction==GCM_CHII) && (Pintent & INTENT_TANYAO)!=0  && isTimeToCall_ForTanyao(Paction,true,Peswn/*caller*/,Ptd/*Discarded*/,PmyShanten,ctrDora,Pintent))//~vafqI~
                {                                                  //~vafcM~
        			if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true by Tanyao");//~vafcM~
                    rc=true;                                       //~vafcM~
                }                                                  //~vafcM~
//  			if ((Paction==GCM_PON || Paction==GCM_CHII) && (Pintent & INTENT_CHANTA)!=0  && isTimeToCall_ForChanta(Paction,true,Peswn/*caller*/,Ptd/*Discarded*/,PmyShanten,ctrDora))//~vaffI~//~vafqR~
    			if ((Paction==GCM_PON || Paction==GCM_CHII) && (Pintent & INTENT_CHANTA)!=0  && isTimeToCall_ForChanta(Paction,true,Peswn/*caller*/,Ptd/*Discarded*/,PmyShanten,ctrDora,Pintent))//~vafqI~
                {                                                  //~vaffI~
        			if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true by Chanta");//~vaffI~
                    rc=true;                                       //~vaffI~
                }                                                  //~vaffI~
			  	if (v>0 && ctrReach!=0 && shanten==1)	               //~vaatR~
              	{                                                    //~vaaiI~//~vaatR~
        			if (Dump.Y) Dump.println("RACall.isTimeToCall return true by WordTile ctrReach!=0 and shanten=1");//~vaatI~
                        rc=true;                                   //~vaatI~
                    break;                                         //~vaatI~
                }                                                  //~vaatI~
//          	if (v>0 && (Pintent & (INTENT_SAMECOLOR_ANY | INTENT_ALLSAME))!=0)//yakuhai and samecolor|allsame//~vaatR~//~vaaxR~
//          	if ((v>0 || ctrDora>0) && (Pintent & (INTENT_SAMECOLOR_ANY | INTENT_ALLSAME))!=0)//yakuhai and samecolor|allsame//~vaaxI~//~vafwR~
//              {                                                  //~vaatI~//~vafwR~
//      			if (Dump.Y) Dump.println("RACall.isTimeToCall return true by WordTile/Dora intent:samecolor or allsame");//~vaatI~//~vaaxR~//~vafwR~
//                  rc=true;                                       //~vaatI~//~vafwR~
//                  break;                                         //~vaatI~//~vafwR~
//              }                                                  //~vaatI~//~vafwR~
            	if ((Pintent & (INTENT_SAMECOLOR_ANY))!=0)          //~vafwI~
                {                                                  //~vafwI~
                  if (RAUtils.isMatchSameColor(true/*allow Word*/,Pintent,pos/CTR_NUMBER_TILE))//~vag5I~
                  {                                                //~vag5I~
                    int ctrDoraSameColor=RADS.getCtrDoraInHandSameColor(Peswn,Pintent,Ptd);//~vafwR~
                    if (v>0 || ctrDoraSameColor>0)                //~vafwI~
                    {                                              //~vafwI~
                      if (isTimeToCall_PonChiiForSameColor(true/*PswEarly*/,Paction,pos,Ptd,itsH))//~vagaI~
                      {                                            //~vagaI~
        				if (Dump.Y) Dump.println("RACall.isTimeToCall return true by Early WordTile or Doractr  pos="+pos+",intent:samecolor v="+v+">0 and ctrDoraSameColor="+ctrDoraSameColor+">0");//~vafwR~//~vag5R~
                    	rc=true;                                   //~vafwI~
                    	break;                                     //~vafwI~
                      }                                            //~vagaI~
                    }                                              //~vafwI~
                  }                                                //~vag5I~
                }                                                  //~vafwI~
            	if ((Pintent & (INTENT_ALLSAME))!=0)     //toitoi  //~vafwI~
                {                                                  //~vafwI~
                    if (v>0 || ctrDora>0)                         //~vafwI~
                    {                                              //~vafwI~
        				if (Dump.Y) Dump.println("RACall.isTimeToCall return true by Early WordTile/Dora intent:allsame v="+v+">0 and ctrDora="+ctrDora+">0");//~vafwR~//~vag5R~
                    	rc=true;                                   //~vafwI~
                    	break;                                     //~vafwI~
                    }                                              //~vafwI~
                }                                                  //~vafwI~
				if (Paction==GCM_CHII && (Pintent & INTENT_STRAIGHT)!=0 && isTimeToCall_ChiiForStraight(Pintent,true,Peswn/*caller*/,Ptd/*Discarded*/,PmyShanten,ctrDora))//~vafbR~
                {                                                  //~vaf9I~
        			if (Dump.Y) Dump.println("RACall.isTimeToCall return true by Straight");//~vaf9I~
                    rc=true;                                       //~vaf9I~
                    break;                                         //~vaf9I~
                }                                                  //~vaf9I~
//  			if (Paction==GCM_CHII && (Pintent & INTENT_3SAMESEQ)!=0  && isTimeToCall_ChiiFor3SameSeq(true,Peswn/*caller*/,Ptd/*Discarded*/,PmyShanten,ctrDora))//~vafbR~//~vafqR~
    			if (Paction==GCM_CHII && (Pintent & INTENT_3SAMESEQ)!=0  && isTimeToCall_ChiiFor3SameSeq(true,Peswn/*caller*/,Ptd/*Discarded*/,PmyShanten,ctrDora,Pintent))//~vafqI~
                {                                                  //~vafbI~
        			if (Dump.Y) Dump.println("RACall.isTimeToCall return true by 3SameSeq");//~vafbI~
                    rc=true;                                       //~vafbI~
                    break;                                         //~vafbI~
                }                                                  //~vafbI~
        	  if (Paction!=GCM_KAN)                                //~vagdR~
              {                                                    //~vagdR~
        		if (Dump.Y) Dump.println("RACall.isTimeToCall early call and no reach rc="+rc);//~va8pI~//~vaarR~//~vafcR~
            	break;  //false                                   //~1306I~//~1313I~
              }                                                    //~vagdR~
            }                                                      //~1306I~//~1313M~
//      	if (Paction==GCM_KAN)                                  //~1306I~//~vagdR~
//          {                                                      //~1306I~//~vagdR~
//  		    if (ctrReach!=0) //other player's reach            //~1306I~//~vagdR~
//              	break;                                         //~1306I~//~vagdR~
//          }                                                      //~1306I~//~vagdR~
			boolean swSkipKan=false;                               //~vaj1I~
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
					        if (Dump.Y) Dump.println("RACall.isTimeToCall for Kan shanten="+shanten+",ctrReach="+ctrReach);//~vaj1I~
                          if (isFixed1AfterKan(Peswn,pos,v/2/*honor tile han*/))//~vaj1R~
                          {                                        //~vaj1I~
					        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Kan shanten="+shanten+",ctrReach="+ctrReach);//~vaatI~
	                        rc=true;                               //~vaatI~
                        	break;                                 //~vaatI~
                          }                                        //~vaj1I~
                          else                                     //~vaj1I~
                          	swSkipKan=true;                        //~vaj1I~
                        }                                          //~vaatI~
                    }                                              //~1306I~
                }                                                  //~1306I~
            	if (!swAllInHand && (Pintent & (INTENT_ALLSAME))!=0)     //toitoi//~vag7I~
                {                                                  //~vag7I~
        			if (Dump.Y) Dump.println("RACall.isTimeToCall GCM_PON return true by !swAllInHand & INTENT_ALLSAME eswn="+Peswn+",pos="+pos);//~vag7I~
                    rc=true;                                       //~vag7I~
                    break;                                         //~vag7I~
                }                                                  //~vag7I~
        		if (Dump.Y) Dump.println("RACall.isTimeToCall swSkipKan="+swSkipKan);//~vaj1I~
//  	      if (RS.RSP[Peswn].isFixed1())     //1han already fixed//~vafdI~//~vaj1R~
    	      if (!swSkipKan && RS.RSP[Peswn].isFixed1())     //1han already fixed//~vaj1I~
                if (shanten==1 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1 //dora>=2//~vaaiR~
                ||  shanten==1 && ctrReach==1 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_OTHERREACH //dora>=1//~vaaiR~
                ||  shanten==2 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN2)//dora>=3//~vaaiR~
                {                                                  //~vaaiI~
			        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Pon shanten="+shanten+" and dora="+ctrDora+",ctrReach="+ctrReach);//~vaaiI~//~vaarR~
	                rc=true;	//yaku fix and shantenup will be chked at callPonKan//~vaaiR~
    	            break;                                         //~vaaiI~
                }                                                  //~vaaiI~
//              int[] itsH=RS.getItsHandEswn(Peswn);               //~vaaHI~//~vafnR~
//              int ctrH=RS.RSP[Peswn].ctrHand;                    //~vaaHI~//~vafnR~
//          	if (!RS.RSP[Peswn].swAllInHand)                     //~vaaJI~//~vafnR~
//              {                                                  //~vaaJI~//~vafnR~
//                  if (shanten==1 && chkShantenAddCall(Peswn,itsH,ctrH,pos,-2)==0)   //tenpai after drop pair//~vaaHR~//~vaaJR~//~vafnR~
//                  {                                                  //~vaaHI~//~vaaJR~//~vafnR~
//                      if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Pon becomming tenpai eswn="+Peswn+",pos="+pos);//~vaaHI~//~vaaJR~//~vafnR~
//                      rc=true;    //yaku fix and shantenup will be chked at callPonKan//~vaaHI~//~vaaJR~//~vafnR~
//                      break;                                         //~vaaHI~//~vaaJR~//~vafnR~
//                  }                                                  //~vaaHI~//~vaaJR~//~vafnR~
//              }                                                  //~vaaJI~//~vafnR~
            	if ((Pintent & INTENT_TANYAO)!=0)                  //~vafcI~
                {                                                  //~vafcI~
//              	if (isTimeToCall_ForTanyao(GCM_PON,false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora))//~vafcI~//~vafqR~
                	if (isTimeToCall_ForTanyao(GCM_PON,false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora,Pintent))//~vafqI~
                    {                                              //~vafcI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Pon Tanyao eswn="+Peswn+",td="+Ptd.toString());//~vafcI~
                        rc=true;                                   //~vafcI~
                        break;                                     //~vafcI~
                    }                                              //~vafcI~
                }                                                  //~vafcI~
            	if ((Pintent & INTENT_CHANTA)!=0)                  //~vaffI~
                {                                                  //~vaffI~
//              	if (isTimeToCall_ForChanta(GCM_PON,false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora))//~vaffI~//~vafqR~
                	if (isTimeToCall_ForChanta(GCM_PON,false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora,Pintent))//~vafqI~
                    {                                              //~vaffI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Pon Chanta eswn="+Peswn+",td="+Ptd.toString());//~vaffI~
                        rc=true;                                   //~vaffI~
                        break;                                     //~vaffI~
                    }                                              //~vaffI~
                }                                                  //~vaffI~
            }//Pon                                                 //~1306I~
            if ((Pintent & INTENT_SAMECOLOR_ANY)!=0)                //~1306I~
            {                                                      //~1306I~
            	int tp=Ptd.type;                                   //~vaaeI~
		        if (Dump.Y) Dump.println("RACall.isTimeToCall INTENT_SAMECOLOR Pintent="+Integer.toString(Pintent)+",type="+tp);//~vag4I~
              if (tp==TT_JI                                        //~vaaeI~
              ||   (tp==TT_MAN && (Pintent & INTENT_SAMECOLOR_MAN)!=0)//~vaaeI~
              ||   (tp==TT_PIN && (Pintent & INTENT_SAMECOLOR_PIN)!=0)//~vaaeI~
              ||   (tp==TT_SOU && (Pintent & INTENT_SAMECOLOR_SOU)!=0)//~vaaeI~
              )                                                    //~vaaeI~
              {                                                    //~vaaeI~
			   if (isTimeToCall_PonChiiForSameColor(false/*swEarly*/,Paction,pos,Ptd,itsH))//~vagaI~
               {                                                   //~vagaI~
		        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true by samecolor");//~vaaeI~
                rc=true;                                           //~1306I~
                break;                                             //~1306I~
               }                                                   //~vagaI~
              }                                                    //~vaaeI~
            }                                                      //~1306I~
			if (Paction==GCM_CHII)                                 //~vaaiI~
            {                                                      //~vaaiI~
            	if ((Pintent & INTENT_TANYAO)!=0)                  //~vafcM~
                {                                                  //~vafcM~
//              	if (isTimeToCall_ForTanyao(GCM_CHII,false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora))//~vafcM~//~vafqR~
                	if (isTimeToCall_ForTanyao(GCM_CHII,false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora,Pintent))//~vafqI~
                    {                                              //~vafcM~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii Tanyao eswn="+Peswn+",td="+Ptd.toString());//~vafcM~
                        rc=true;                                   //~vafcM~
                    }                                              //~vafcM~
                }                                                  //~vafcM~
                else                                               //~vaffI~
            	if ((Pintent & INTENT_CHANTA)!=0)                  //~vaffI~
                {                                                  //~vaffI~
//              	if (isTimeToCall_ForChanta(GCM_CHII,false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora))//~vaffI~//~vafqR~
                	if (isTimeToCall_ForChanta(GCM_CHII,false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora,Pintent))//~vafqI~
                    {                                              //~vaffI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii Chanta eswn="+Peswn+",td="+Ptd.toString());//~vaffI~
                        rc=true;                                   //~vaffI~
                    }                                              //~vaffI~
                }                                                  //~vaffI~
            	if ((Pintent & INTENT_STRAIGHT)!=0)                //~vaf9I~
                {                                                  //~vaf9I~
                	if (isTimeToCall_ChiiForStraight(Pintent,false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora))//~vaf9R~
                    {                                              //~vaf9I~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii Straight eswn="+Peswn+",td="+Ptd.toString());//~vaf9I~
                        rc=true;    //yaku fix and shantenup will be chked at callPonKan//~vaf9I~
                        break;                                     //~vaf9I~
                    }                                              //~vaf9I~
                }                                                  //~vaf9I~
            	if ((Pintent & INTENT_3SAMESEQ)!=0)                //~vafbI~
                {                                                  //~vafbI~
//              	if (isTimeToCall_ChiiFor3SameSeq(false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora))//~vafbR~//~vafqR~
                	if (isTimeToCall_ChiiFor3SameSeq(false,Peswn/*caller*/,Ptd/*discarded*/,PmyShanten,ctrDora,Pintent))//~vafqI~
                    {                                              //~vafbI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii 3SameSeq eswn="+Peswn+",td="+Ptd.toString());//~vafbI~
                        rc=true;    //yaku fix and shantenup will be chked at callPonKan//~vafbI~
                        break;                                     //~vafbI~
                    }                                              //~vafbI~
                }                                                  //~vafbI~
		      if (RS.RSP[Peswn].isFixed1())     //1han already fixed//~vafdI~
                if (shanten==1 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1 //shanten=1 and dora>=2//~vaaiR~
                ||  shanten==1 && ctrReach==1 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_OTHERREACH //dora>=1//~vaaiR~
                ||  shanten==2 && ctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN2)//shanten=2 and dora>=3//~vaaiR~
                {                                                  //~vaaiI~
			        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii shanten="+shanten+" and dora="+ctrDora+",ctrReach="+ctrReach);//~vaaiR~
	                rc=true;	//yaku fix and shantenup will be chked at callChii//~vaaiR~
    	            break;                                         //~vaaiI~
                }                                                  //~vaaiI~
//              int[] itsH=RS.getItsHandEswn(Peswn);               //~vaaHI~//~vafnR~
//              int ctrH=RS.RSP[Peswn].ctrHand;                    //~vaaHI~//~vafnR~
//            	if (!RS.RSP[Peswn].swAllInHand)                     //~vaaJI~//~vafnR~
              	if (!swAllInHand)                                  //~vafnI~
                {                                                  //~vaaJI~
//                  if (shanten==1 && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)==0)    //tenpai chk by adding discarded//~vaaHR~//~vaaJR~//~vafnR~
//                  {                                                  //~vaaHI~//~vaaJR~//~vafnR~
//                      if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii becomming tenpai eswn="+Peswn+",pos="+pos);//~vaaHI~//~vaaJR~//~vafnR~
//                      rc=true;    //yaku fix and shantenup will be chked at callChii//~vaaHI~//~vaaJR~//~vafnR~
//                      break;                                         //~vaaHI~//~vaaJR~//~vafnR~
//                  }                                                  //~vaaHI~//~vaaJR~//~vafnR~
                    if (shanten==2 && RS.RSP[Peswn].getPairCtr()==1//~vaaJI~
                    &&  (AG.aRAReach.isWaitingPenchan(Peswn,pos,itsH,ctrH) || AG.aRAReach.isWaitingKanchan(Peswn,pos,itsH,ctrH))//~vaaJI~
                    && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)==1)    //tenpai chk by adding discarded//~vaaJI~
                    {                                              //~vaaJI~
                        if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii shanten up by 2nd call for penchan/kanchan eswn="+Peswn+",pos="+pos);//~vaaJI~
                        rc=true;    //yaku fix and shantenup will be chked at callPonKan//~vaaJI~
                        break;                                     //~vaaJI~
                    }                                              //~vaaJI~
                }                                                  //~vaaJI~
            }//Chii                                                //~vaaiI~
            break;                                                 //~1306I~
		} //for (;;)                                               //~1306I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall lastChk for shanten=1 action="+Paction+",rc="+rc+",allInHand="+swAllInHand);//~vafnR~//~vafqR~
        if (!rc && Paction==GCM_KAN)                               //~vagdR~
        {                                                          //~vagdR~
        	rc=isTimeToCall_KanTaken(Peswn,swAllInHand,swEarly,shanten,ctrReach,ctrDora,pos,ctrH,itsH);//~vagdR~
        }                                                          //~vagdR~
//      if (!RS.RSP[Peswn].swAllInHand)                            //~vafnR~
        if (!swAllInHand)                                          //~vafnI~
        {                                                          //~vafnR~
            if (Paction==GCM_PON)                                  //~vafnI~
            {                                                      //~vafnI~
                if (shanten==1 && chkShantenAddCall(Peswn,itsH,ctrH,pos,-2)==0)   //tenpai after drop pair//~vafnI~
                {                                                  //~vafnI~
//                  swChkFix2ndCall=true;                          //~vafnR~
//                  if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Pon by Tenpai eswn="+Peswn+",pos="+pos);//~vafnR~
//                  rc=true;                                       //~vafnR~
                    int posPon=selectFixedCallShanten0_Pon(Peswn,itsH,ctrH,pos);//~vafnR~
                    if (posPon!=-1)                                //~vafnI~
                    {                                              //~vafnI~
                    	rc=true;                                   //~vafnI~
                        posFixedCallShanten0_Pon=posPon;           //~vafnI~
                    }                                              //~vafnI~
                }                                                  //~vafnI~
            }                                                      //~vafnI~
            else                                                   //~vafnI~
            if (Paction==GCM_CHII)                                 //~vafnI~
            {                                                      //~vafnI~
                if (shanten==1 && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)==0)    //tenpai chk by adding discarded//~vafnI~
                {                                                  //~vafnI~
//                  swChkFix2ndCall=true;  //chk ronable at callChii//~vafnR~
//    			    if (!isFixed2ndCall(PeswnOther,itsH,ctrH,GCM_CHII,pos,posChii))//~vafnI~
//                  if (Dump.Y) Dump.println("RACall.isTimeToCall rc=true for Chii by Tenpai eswn="+Peswn+",pos="+pos);//~vafnR~
//                  rc=true;                                       //~vafnR~
                    int posChii=selectFixedCallShanten0_Chii(Peswn,itsH,ctrH,pos);//~vafnR~
                    if (posChii!=-1)                               //~vafnI~
                    {                                              //~vafnI~
                    	rc=true;                                   //~vafnI~
                        posFixedCallShanten0_Chii=posChii;         //~vafnR~
                    }                                              //~vafnI~
                }                                                  //~vafnI~
            }                                                      //~vafnI~
        }//allInHand                                               //~vafnR~
        if (Dump.Y) Dump.println("RACall.isTimeToCall rc="+rc+",eswn="+Peswn+",action="+Paction+",swAllInhand="+swAllInHand+",ctrTaken="+RS.RSP[Peswn].ctrTaken);//~1306I~//~va8pR~//~vafnR~
        return rc;                                                 //~1306I~
    }//isTimeToCall                                                              //~1306I~//~vafnR~
 	//******************************************************************************//~vagaI~
 	//*select Pon or Chii for Samecolor                            //~vagaI~
 	//******************************************************************************//~vagaI~
    private boolean isTimeToCall_PonChiiForSameColor(boolean PswEarly,int Paction,int Ppos,TileData Ptd,int[] PitsHand)//~vagaI~
    {                                                              //~vagaI~
    	boolean rc=true;                                           //~vagaI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_PonChiiSamecolor swEarly="+PswEarly+",action="+Paction+",pos="+Ppos+",Ptd="+Ptd.toString()+",itsHand="+Utils.toString(PitsHand,9));//~vagaI~
        if (Paction==GCM_PON)                                      //~vagaI~
        {                                                          //~vagaI~
        	if (Ptd.type!=TT_JI)                                   //~vagaI~
            {                                                      //~vagaI~
 		       	int num=Ptd.number;                                //~vagaI~
                if (num==TN1)                                      //~vagaI~
                {                                                  //~vagaI~
                	if (PitsHand[Ppos+1]!=0)                       //~vagaR~
                    	rc=false;                                  //~vagaI~
                }                                                  //~vagaI~
                else                                               //~vagaI~
                if (num==TN9)                                      //~vagaI~
                {                                                  //~vagaI~
                	if (PitsHand[Ppos-1]!=0)                       //~vagaR~
                    	rc=false;                                  //~vagaI~
                }                                                  //~vagaI~
                else                                               //~vagaI~
                	if (PitsHand[Ppos-1]!=0 || PitsHand[Ppos+1]!=0)//~vagaR~
                    	rc=false;                                  //~vagaI~
            }                                                      //~vagaI~
        }                                                          //~vagaI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_PonChiiForSameColor rc="+rc);//~vagaI~
        return rc;                                                 //~vagaI~
    }                                                              //~vagaI~
 	//******************************************************************************//~vaf9I~
    private boolean isTimeToCall_ChiiForStraight(int Pintent,boolean PswEarly,int Peswn/*caller*/,TileData Ptd/*Discarded*/,int PmyShanten,int PctrDora)//~vaf9R~
    {                                                              //~vaf9I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiForStraight swEarly="+PswEarly+",eswn="+Peswn+",shanten="+PmyShanten+",ctrDora="+PctrDora+",td="+Utils.toString(Ptd)+",intent="+Integer.toHexString(Pintent));//~vaf9I~
        if ((Pintent & INTENT_SAMECOLOR_ANY)!=0)                   //~vaf9I~
        	return false;                                          //~vaf9I~
    	boolean rc=false;                                          //~vaf9I~
        int color=RS.getColorStraight(Peswn);                      //~vaf9I~
        boolean swAllInHand=RS.isAllInHand(Peswn);                 //~vaf9I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiForStraight color="+color+",swAllInHand="+swAllInHand);//~vaf9R~
      	int pos=RAUtils.getPosTile(Ptd);                           //~vaf9I~
        int[] itsH=RS.getItsHandEswn(Peswn);                       //~vaf9I~
        int ctrH=RS.RSP[Peswn].ctrHand;                            //~vaf9I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiForStraight pos="+pos+",itsH="+Utils.toString(itsH,9));//~vaf9I~
        if (swAllInHand)                                           //~vaf9R~
		{                                                          //~vaf9I~
        	boolean swCall=false;                                  //~vaf9I~
            if (PswEarly)                                          //~vaf9I~
            {                                                      //~vaf9I~
				if (PmyShanten<=2 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1+1 //dora>=3//~vaf9I~
					 && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)<=1)    //shantenUp//~vaf9I~
	                swCall=true;                                   //~vaf9I~
            }
            else                                                   //~vaf9I~
            {                                                      //~vaf9I~
				if (PmyShanten==1 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1 //dora>=2//~vaf9I~
					 && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)==0)    //1-naki tenpai//~vaf9R~
                	swCall=true;                                   //~vafbR~
            }                                                      //~vaf9I~
            if (swCall)                                            //~vaf9I~
            {                                                      //~vafqI~
            	if (PmyShanten>1 && RS.swFix2)     //more 1 han required for straight//~vafqI~
            		if (!RS.RSP[Peswn].isFixed1())     //1 han not already fixed//~vafqI~
                    {                                              //~vafqI~
				        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiForStraight rc=false by fix2, fix1="+RS.RSP[Peswn].isFixed1());//~vafqI~
                		swCall=false;                              //~vafqI~
                    }                                              //~vafqI~
              if (swCall)                                          //~vafqI~
        		if (isFixedStraight(Peswn,itsH,color,pos))         //~vaf9R~
	        		rc=true;
            }                                                      //~vafqI~
        }//~vaf9R~
		swIssueChiiStraight=rc;                                    //~vaf9I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiForStraight rc=swIssueChiiStraignt="+rc);//~vaf9R~
        return rc;                                                 //~vaf9I~
    }                                                              //~vaf9I~
 	//******************************************************************************//~vafbI~
//  private boolean isTimeToCall_ChiiFor3SameSeq(boolean PswEarly,int Peswn/*caller*/,TileData Ptd/*Discarded*/,int PmyShanten,int PctrDora)//~vafbR~//~vafqR~
    private boolean isTimeToCall_ChiiFor3SameSeq(boolean PswEarly,int Peswn/*caller*/,TileData Ptd/*Discarded*/,int PmyShanten,int PctrDora,int Pintent)//~vafqI~
    {                                                              //~vafbI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiFor3SameSeq swEarly="+PswEarly+",eswn="+Peswn+",shanten="+PmyShanten+",ctrDora="+PctrDora+",intent="+Integer.toHexString(Pintent)+",td="+Utils.toString(Ptd));//~vafbR~//~vafqR~
    	boolean rc=false;                                          //~vafbI~
        int numTop=RS.getNum3SameSeq(Peswn);                       //~vafbI~
        boolean swAllInHand=RS.isAllInHand(Peswn);                 //~vafbI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiFor3SameSeq numTop="+numTop+",swAllInHand="+swAllInHand);//~vafbR~
      	int pos=RAUtils.getPosTile(Ptd);                           //~vafbI~
        int[] itsH=RS.getItsHandEswn(Peswn);                       //~vafbI~
        int ctrH=RS.RSP[Peswn].ctrHand;                            //~vafbI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiFor3SameSeq pos="+pos+",itsH="+Utils.toString(itsH,9));//~vafbI~
        if (swAllInHand)                                           //~vafbI~
		{                                                          //~vafbI~
        	boolean swCall=false;                                  //~vafbI~
            if (PswEarly)                                          //~vafbI~
            {                                                      //~vafbI~
				if (PmyShanten<=2 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1+1 //dora>=3//~vafbI~
					 && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)<=1)    //shantenUp//~vafbI~
	                swCall=true;                                   //~vafbI~
            }                                                      //~vafbI~
            else                                                   //~vafbI~
            {                                                      //~vafbI~
				if (PmyShanten==1 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1 //dora>=2//~vafbI~
					 && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)==0)    //1-naki tenpai//~vafbI~
                	swCall=true;                                   //~vafbI~
            }                                                      //~vafbI~
            if (swCall)                                            //~vafbI~
            {                                                      //~vafqI~
            	if (PmyShanten>1 && RS.swFix2)     //more 1 han required for 3sameseq//~vafqI~
                {                                                  //~vafqI~
            		if (!RS.RSP[Peswn].isFixed1() && (Pintent & (INTENT_TANYAO|INTENT_CHANTA))==0)	//another han required//~vafqI~
                    {                                              //~vafqI~
				        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiFor3SameSeq rc=false by fix2, fix1="+RS.RSP[Peswn].isFixed1());//~vafqI~
                		swCall=false;                              //~vafqI~
                    }                                              //~vafqI~
                }                                                  //~vafqI~
              if (swCall)                                          //~vafqI~
        		if (isFixed3SameSeq(Peswn,itsH,numTop,pos))        //~vafbR~
	        		rc=true;                                       //~vafbI~
            }                                                      //~vafqI~
        }                                                          //~vafbI~
		swIssueChii3SameSeq=rc;                                    //~vafbI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiFor3SameSeq rc=swIssueChii3SameSeq="+rc);//~vafbI~
        return rc;                                                 //~vafbI~
    }                                                              //~vafbI~
 	//******************************************************************************//~vafsI~
  	private boolean isTimeToCall_Fixed1(int Peswn,int Pshanten,int PctrDora,int PctrTaken,int[] PitsH,int PctrH,int Ppos)//~vafsR~
    {                                                              //~vafsI~
        boolean swEarly=RS.isAllInHand(Peswn) && PctrTaken<=HV_TIME_TO_CALL; //     //<=3 save to call up to 3 tiles take//~vafsI~
        boolean swCall=false;                                      //~vafsI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_Fixed1 entry swEarly="+swEarly+",eswn="+Peswn+",shanten="+Pshanten+",ctrDora="+PctrDora+",ctrTaken="+PctrTaken+",fix2="+RS.swFix2);//~vafsI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_Fixed1 ctrH="+PctrH+",itsHand="+Utils.toString(PitsH,9));//~vafsI~
        if (RS.swFix2 && Peswn!=ESWN_E)     //not parent and 2han constraint//~vafsI~
            swCall=true;                                           //~vafsI~
        else                                                       //~vafsI~
        if (swEarly && (Pshanten<=3 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1+1) //dora>=3//~vafsI~
        ||              (Pshanten<=2 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1) //dora>=2//~vafsI~
    	)
        {//~vafsI~
        	if (chkShantenAddCall(Peswn,PitsH,PctrH,Ppos,1)<Pshanten)    //shantenUp//~vafsI~
            	swCall=true;                                       //~vafsI~
        }                                                          //~vafsI~
        if (swCall)                                                //~vafsI~
        {                                                          //~vafsI~
            if (RS.swFix2)     //more 1 han required for 3sameseq  //~vafsI~
	        	if (RS.RSP[Peswn].getCtrFixedInHand()<=1 && RS.RSP[Peswn].getCtrValueWordDup()<=1) //meld:1 and pair:1(including Meld 1)//~vafsR~
                {                                                  //~vafsI~
			        if (Dump.Y) Dump.println("RACall.isTimeToCall_Fixed1 rc=false by fix2 and honor meld/pair ctr");//~vafsI~
                	swCall=false;                                  //~vafsI~
                }                                                  //~vafsI~
        }                                                          //~vafsI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_Fixed1 exit rc="+swCall);//~vafsI~
        return swCall;
    }                                                              //~vafsI~
 	//******************************************************************************//~vaj1I~
  	private boolean isFixed1AfterKan(int Peswn,int Ppos,int PhanHonor)//~vaj1R~
    {                                                              //~vaj1I~
    	boolean rc;                                                //~vaj1I~
        if (optYakuFixTiming!=YAKUFIX_FIRST)                       //~vaj1I~
        	rc=true;                                               //~vaj1I~
        else                                                       //~vaj1I~
        {                                                          //~vaj1I~
        	int ctrFirst=RS.RSP[Peswn].getCtrFixedFirstOverFix2();      //~vaj1I~
            if (PhanHonor>0)       //confirmed honor               //~vaj1R~
            	ctrFirst-=PhanHonor;                               //~vaj1R~
            else                                                   //~vaj1I~
            if (PhanHonor<0) 	//unknown                          //~vaj1R~
            {                                                      //~vaj1I~
            	int v=RAUtils.chkValueWordTile(Ppos,Peswn);        //~vaj1I~
                ctrFirst-=v/2;	//2:double East,1:other honor      //~vaj1I~
            }                                                      //~vaj1I~
            rc=ctrFirst>0;	//-1:pair change to minkan and loose fixedDirst//~vaj1R~
        }                                                          //~vaj1I~
        if (Dump.Y) Dump.println("RACall.isFixed1AfterKan rc="+rc+",eswn="+Peswn+",pos="+Ppos+",hanHonor="+PhanHonor+",optYakuFixTiming="+optYakuFixTiming);//~vaj1R~
        return rc;
    }                                                              //~vaj1I~
 	//******************************************************************************//~vagdR~
  	private boolean isTimeToCall_KanTaken(int Peswn,boolean PswAllInHand,boolean PswEarly,int Pshanten,int PctrReach,int PctrDora,int Ppos,int PctrHand,int[] PitsHand)//~vagdR~
    {                                                              //~vagdR~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_KanTaken eswn="+Peswn+",swAllInHand="+PswAllInHand+",swEarly="+PswEarly+",shanten="+Pshanten+",ctrReach="+PctrReach+",ctrDora="+PctrDora+",pos="+Ppos+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vagdR~
    	boolean swCall=false;                                      //~vagdR~
        if (PswEarly)                                              //~vagdR~
        {                                                          //~vagdR~
            if (Pshanten<=2 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1+1) //dora>=3//~vagdR~
	            swCall=true;                                       //~vagdR~
        }                                                          //~vagdR~
        else                                                       //~vagdR~
        {                                                          //~vagdR~
            if (Pshanten<=1 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1) //dora>=2//~vagdR~
                swCall=true;                                       //~vagdR~
        }                                                          //~vagdR~
        if (swCall)                                                //~vagdR~
        {                                                          //~vagdR~
            if (PctrReach!=0)                                      //~vagdR~
				if (PswAllInHand && PctrReach<=1 && Pshanten<=1)   //~vagdR~
                	;                                              //~vagdR~
                else                                               //~vagdR~
	        		swCall=false;                                  //~vagdR~
        }                                                          //~vagdR~
//      if (swCall)                                                //~vagdR~
//      {                                                          //~vagdR~
//      	if (chkShantenAddCall(Peswn,PitsHand,PctrHand,Ppos,-PAIRCTR)>Pshanten)    //shantenDown//~vagdR~
//      		swCall=false;                                      //~vagdR~
//      }                                                          //~vagdR~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_KanTaken exit rc="+swCall);//~vagdR~
        return swCall;                                             //~vagdR~
    }                                                              //~vagdR~
 	//******************************************************************************//~vafcI~
 	//*determin to call by tanyao intent at allinhand
 	//******************************************************************************
//  private boolean isTimeToCall_ForTanyao(int Paction,boolean PswEarly,int Peswn/*caller*/,TileData Ptd/*Discarded*/,int PmyShanten,int PctrDora)//~vafcI~//~vafqR~
    private boolean isTimeToCall_ForTanyao(int Paction,boolean PswEarly,int Peswn/*caller*/,TileData Ptd/*Discarded*/,int PmyShanten,int PctrDora,int Pintent)//~vafqI~
    {                                                              //~vafcI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ForTanyao action="+Paction+",swEarly="+PswEarly+",eswn="+Peswn+",shanten="+PmyShanten+",intent="+Integer.toHexString(Pintent)+",ctrDora="+PctrDora+",td="+Utils.toString(Ptd));//~vafcR~//~vafqR~
    	boolean rc=false;                                          //~vafcM~
        if (!RS.swKuitan)                                          //~vafcI~
        {                                                          //~vafcI~
        	if (Dump.Y) Dump.println("RACall.isTimeToCall_ForTanyao return false by Not kuitan");//~vafcI~
            return false;                                          //~vafcI~
        }                                                          //~vafcI~
        int posChii=-1;                                            //~vafcI~
        boolean swAllInHand=RS.isAllInHand(Peswn);                 //~vafcI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ForTanyao swAllInHand="+swAllInHand);//~vafcI~
      	int pos=RAUtils.getPosTile(Ptd);                           //~vafcI~
        int[] itsH=RS.getItsHandEswn(Peswn);                       //~vafcI~
        int ctrH=RS.RSP[Peswn].ctrHand;                            //~vafcI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ForTanyao pos="+pos+",ctrH="+ctrH+",itsH="+Utils.toString(itsH,9));//~vafcI~
        if (swAllInHand)                                           //~vafcI~
		{                                                          //~vafcI~
        	boolean swCall=false;                                  //~vafcI~
            if (PswEarly)                                          //~vafcI~
            {                                                      //~vafcI~
				if (PmyShanten<=2 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1+1) //dora>=3//~vafcI~
                {                                                  //~vafcI~
					int shanten=chkShantenAddCall(Peswn,itsH,ctrH,pos,1);    //shantenUp//~vafcR~
                    if (shanten<PmyShanten)                        //~vafcI~
		                swCall=true;                               //~vafcR~
                }                                                  //~vafcI~
            }                                                      //~vafcI~
            else                                                   //~vafcI~
            {                                                      //~vafcI~
				if (PmyShanten==1 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1 //dora>=2//~vafcI~
					 && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)==0)    //1-naki tenpai//~vafcI~
                	swCall=true;                                   //~vafcI~
            }                                                      //~vafcI~
            if (swCall)                                            //~vafqI~
            {                                                      //~vafqI~
            	if (PmyShanten>1 && RS.swFix2)     //more 1 han required//~vafqI~
       				if ((Pintent & (INTENT_3SAMESEQ))==0)	//another han required for tanyao//~vafqI~
                    {                                              //~vafqI~
				        if (Dump.Y) Dump.println("RACall.isTimeToCall_ForTanyao rc=false by fix2");//~vafqI~
                		swCall=false;                              //~vafqI~
                    }                                              //~vafqI~
            }                                                      //~vafqI~
            if (swCall)                                            //~vafcI~
            {                                                      //~vafcI~
                posChii = chkFixedTanyao(Paction, PmyShanten, Peswn, itsH, ctrH, pos);//~vafcI~
                if (posChii != -1)                                   //~vafcI~
                    rc = true;                                       //~vafcI~
            }                                                       //~vafcI~
        }                                                          //~vafcI~
		actionIssueTanyao=rc ? Paction : 0;                        //~vafcI~
		posIssueTanyao=posChii;                                    //~vafcR~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ForTanyao rc="+rc+",posChii="+posChii+",actionIssueTanyao="+actionIssueTanyao);//~vafcI~
        return rc;                                                 //~vafcI~
    }                                                              //~vafcI~
 	//******************************************************************************//~vaffI~
 	//*determin to call by tanyao intent at allinhand
 	//******************************************************************************
//  private boolean isTimeToCall_ForChanta(int Paction,boolean PswEarly,int Peswn/*caller*/,TileData Ptd/*Discarded*/,int PmyShanten,int PctrDora)//~vaffI~//~vafqR~
    private boolean isTimeToCall_ForChanta(int Paction,boolean PswEarly,int Peswn/*caller*/,TileData Ptd/*Discarded*/,int PmyShanten,int PctrDora,int Pintent)//~vafqI~
    {                                                              //~vaffI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ForChanta action="+Paction+",swEarly="+PswEarly+",eswn="+Peswn+",shanten="+PmyShanten+",intent="+Integer.toHexString(Pintent)+",ctrDora="+PctrDora+",td="+Utils.toString(Ptd));//~vaffI~//~vafqR~
    	boolean rc=false;                                          //~vaffI~
//        if (!RS.swKuitan)                                        //~vaffI~
//        {                                                        //~vaffI~
//            if (Dump.Y) Dump.println("RACall.isTimeToCall_ForChanta return false by Not kuitan");//~vaffI~
//            return false;                                        //~vaffI~
//        }                                                        //~vaffI~
        int posChii=-1;                                            //~vaffI~
        boolean swAllInHand=RS.isAllInHand(Peswn);                 //~vaffI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ForChanta swAllInHand="+swAllInHand);//~vaffI~
      	int pos=RAUtils.getPosTile(Ptd);                           //~vaffI~
        int[] itsH=RS.getItsHandEswn(Peswn);                       //~vaffI~
        int ctrH=RS.RSP[Peswn].ctrHand;                            //~vaffI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ForChanta pos="+pos+",ctrH="+ctrH+",itsH="+Utils.toString(itsH,9));//~vaffI~
        if (swAllInHand)                                           //~vaffI~
		{                                                          //~vaffI~
        	boolean swCall=false;                                  //~vaffI~
            if (PswEarly)                                          //~vaffI~
            {                                                      //~vaffI~
				if (PmyShanten<=2 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1+1) //dora>=3//~vaffI~
                {                                                  //~vaffI~
					int shanten=chkShantenAddCall(Peswn,itsH,ctrH,pos,1);    //shantenUp//~vaffI~
                    if (shanten<PmyShanten)                        //~vaffI~
		                swCall=true;                               //~vaffI~
                }                                                  //~vaffI~
            }                                                      //~vaffI~
            else                                                   //~vaffI~
            {                                                      //~vaffI~
				if (PmyShanten==1 && PctrDora>=HV_CTR_DORA_FOR_PONCHII_SHANTEN1 //dora>=2//~vaffI~
					 && chkShantenAddCall(Peswn,itsH,ctrH,pos,1)==0)    //1-naki tenpai//~vaffI~
                	swCall=true;                                   //~vaffI~
            }                                                      //~vaffI~
            if (swCall)                                            //~vafqI~
            {                                                      //~vafqI~
            	if (PmyShanten>1 && RS.swFix2)     //more 1 han required//~vafqI~
            		if (!RS.RSP[Peswn].isFixed1() && (Pintent & (INTENT_3SAMESEQ))==0)	//another han required for chanta//~vafqI~
                    {                                              //~vafqI~
				        if (Dump.Y) Dump.println("RACall.isTimeToCall_ChiiForChanta rc=false by fix2, fix1="+RS.RSP[Peswn].isFixed1());//~vafqI~
                		swCall=false;                              //~vafqI~
                    }                                              //~vafqI~
            }                                                      //~vafqI~
            if (swCall)                                            //~vaffI~
            {                                                      //~vaffI~
                posChii = chkFixedChanta(Paction, PmyShanten, Peswn, itsH, ctrH, pos);//~vaffI~
                if (posChii != -1)                                 //~vaffI~
                    rc = true;                                     //~vaffI~
            }                                                      //~vaffI~
        }                                                          //~vaffI~
		actionIssueChanta=rc ? Paction : 0;                        //~vaffI~
		posIssueChanta=posChii;                                    //~vaffI~
        if (Dump.Y) Dump.println("RACall.isTimeToCall_ForChanta rc="+rc+",posChii="+posChii+",actionIssueChanta="+actionIssueChanta);//~vaffI~
        return rc;                                                 //~vaffI~
    }                                                              //~vaffI~
 	//******************************************************************************//~vaf9I~
 	//*consruct straight by the chii ? at allInHand                //~vaf9I~//~vafcR~
 	//*and confirm pair is fixed(not multiple waiting tile)        //~vaf9I~
 	//******************************************************************************//~vaf9I~
    private boolean isFixedStraight(int Peswn,int[] PitsHand,int Pcolor,int Ppos)//~vaf9I~
    {                                                              //~vaf9I~
    	boolean rc=true;                                           //~vaf9I~
        if (Dump.Y) Dump.println("RACall.isFixedStraight eswn="+Peswn+",color="+Pcolor+",pos="+Ppos);//~vaf9I~
        int pos=Pcolor*CTR_NUMBER_TILE;                                //~vaf9I~
        int ctrStraight=0;                                           //~vaf9I~
        for (int ii=0;ii<CTR_NUMBER_TILE;ii++,pos++)               //~vaf9I~
        {                                                          //~vaf9I~
        	int ctr=PitsHand[pos];                                 //~vaf9I~
        	if (pos==Ppos)                                         //~vaf9I~
            {                                                      //~vaf9I~
				if (ctr==0)                                        //~vaf9I~
	            	ctrStraight++;                                 //~vaf9I~
            }                                                      //~vaf9I~
            else                                                   //~vaf9I~
            {                                                      //~vaf9I~
        		if (ctr!=0)                                        //~vaf9I~
	            	ctrStraight++;                                  //~vaf9I~
            }                                                      //~vaf9I~
        }                                                          //~vaf9I~
        if (ctrStraight<HV_INTENT_STRAIGHT_CTRTYPE+1)  //allow max missing 2//~vaf9R~
        	rc=false;                                              //~vaf9I~
	    if (Dump.Y) Dump.println("RACall.isFixedStraight rc="+rc+",ctrStraight="+ctrStraight);//~vaf9R~
    	return rc;                                                 //~vaf9I~
    }                                                              //~vaf9I~
 	//******************************************************************************//~vafbI~
 	//*consruct 3SameSeq by the chii ? at allInHand                //~vafbI~//~vafcR~
 	//*and confirm pair is fixed(not multiple waiting tile)        //~vafbI~
 	//******************************************************************************//~vafbI~
    private boolean isFixed3SameSeq(int Peswn,int[] PitsHand,int PnumTop,int Ppos)//~vafbR~
    {                                                              //~vafbI~
    	boolean rc=true;                                           //~vafbI~
        if (Dump.Y) Dump.println("RACall.isFixed3SameSeq eswn="+Peswn+",numTop="+PnumTop+",pos="+Ppos);//~vafbI~
        int ctr3SameSeq=0;                                         //~vafbI~
        for (int ii=0;ii<3;ii++) //3 tile seq                      //~vafbR~
        {                                                          //~vafbI~
            int ctr3Same=0;                                        //~vafbI~
        	int pos=PnumTop+ii;                                     //~vafbI~
            for (int jj=0;jj<3;jj++)                               //~vafbI~
            {                                                      //~vafbI~
                int ctr=PitsHand[pos];                             //~vafbR~
                if (pos==Ppos)                                     //~vafbR~
                {                                                  //~vafbR~
                    if (ctr==0)                                    //~vafbR~
                        ctr3Same++;                                //~vafbR~
                }                                                  //~vafbR~
                else                                               //~vafbR~
                {                                                  //~vafbR~
                    if (ctr!=0)                                    //~vafbR~
                        ctr3Same++;                                //~vafbR~
                }                                                  //~vafbR~
                pos+=CTR_NUMBER_TILE;                              //~vafbI~
            }                                                      //~vafbI~
		    if (Dump.Y) Dump.println("RACall.isFixed3SameSeq num="+(PnumTop+ii)+",ctr3Same="+ctr3Same);//~vafbR~
            if (ctr3Same>=PAIRCTR-1)                               //~vafbI~
            	ctr3SameSeq+=ctr3Same;                             //~vafbI~
        }                                                          //~vafbI~
        if (ctr3SameSeq<HV_INTENT_3SAMESEQ_CTRTYPE+1)  //allow max missing 2//~vafbI~
        	rc=false;                                              //~vafbI~
	    if (Dump.Y) Dump.println("RACall.isFixed3SameSeq rc="+rc+",ctr3SameSeq="+ctr3SameSeq);//~vafbI~
    	return rc;                                                 //~vafbI~
    }                                                              //~vafbI~
 	//******************************************************************************//~vafcI~
 	//*keep tanyao by the chii ? at allInHand                //~vafcI~//~vaffR~
 	//*and confirm pair is fixed(not multiple waiting tile)        //~vafcI~
 	//******************************************************************************//~vafcI~
    private int chkFixedTanyao(int Paction,int Pshanten,int Peswn,int[] PitsHand,int PctrHand,int Ppos)//~vafcI~
    {                                                              //~vafcI~
    	boolean rc=false;                                          //~vafcI~
        int posChii=-1;                                            //~vafcI~
        int shanten=Pshanten;                                      //~vafcI~
        int maxAmtWinListMax=-1;                                   //~vafcI~
        if (Dump.Y) Dump.println("RACall.chkFixedTanyao action="+Paction+",shannten="+Pshanten+",eswn="+Peswn+",ctrH="+PctrHand+",pos="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~vafcR~
        if (!RAUtils.isTanyaoTile(Ppos))                             //~vafcI~
        {                                                          //~vafcI~
	        if (Dump.Y) Dump.println("RACall.chkFixedTanyao return false by discarded tile is not tanyao");//~vafcR~
            return -1;                                             //~vafcR~
        }                                                          //~vafcI~
        if (Paction==GCM_PON)                                      //~vafcI~
        {                                                          //~vafcI~
            if (PitsHand[Ppos]<PAIRCTR-1)                          //~vafcI~
            {                                                      //~vafcI~
	        	if (Dump.Y) Dump.println("RACall.chkFixedTanyao return false by tilectr for Pon pos="+Ppos+",ctr="+PitsHand[Ppos]);//~vafcR~
            	return -1;                                         //~vafcR~
            }                                                      //~vafcI~
            PitsHand[Ppos]-=PAIRCTR-1;                             //~vafcI~
            shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand-2);//~vafcI~
            PitsHand[Ppos]+=PAIRCTR-1;                             //~vafcM~
            if (shanten<Pshanten)                                  //~vafcI~
            {                                                      //~vafcI~
            	rc=isFixedTanyaoWinList(Peswn,shanten,PitsHand,PctrHand,GCM_PON,Ppos,Ppos);//~vafcR~
                if (rc)                                            //~vafcI~
                    posChii=Ppos;   //not Chii but return not -1   //~vafcI~
            }                                                      //~vafcI~
        }                                                          //~vafcI~
        else                                                       //~vafcI~
        {                                                          //~vafcI~
        	int num=Ppos%CTR_NUMBER_TILE;                          //~vafcR~
            for (;;)                                               //~vafcI~
            {                                                      //~vafcI~
                if (num<=TN6)   //(2)34--(6)78   (num is confirmed as tanyao)//~vafcR~
                {                                                  //~vafcI~
                    if (PitsHand[Ppos+1]>0 && PitsHand[Ppos+2]>0)  //~vafcR~
                    {                                              //~vafcI~
	                    PitsHand[Ppos+1]--; PitsHand[Ppos+2]--;    //~vafcI~
                        shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand-2);//~vafcI~
	                    PitsHand[Ppos+1]++; PitsHand[Ppos+2]++;    //~vafcI~
		            	if (shanten<Pshanten)                      //~vafcI~
                        {                                          //~vaffI~
	            			rc=isFixedTanyaoWinList(Peswn,shanten,PitsHand,PctrHand,GCM_CHII,Ppos,Ppos);//~vafcR~
                            if (rc)                                            //~vafcI~//~vaffI~
                            {                                                  //~vafcI~//~vaffI~
                                posChii=Ppos;                                  //~vafcI~//~vaffI~
                                maxAmtWinListMax=maxAmtWinList;                //~vafcI~//~vaffI~
                            }                                                  //~vafcI~//~vaffI~
                        }                                          //~vaffI~
                    }                                              //~vafcI~
                }                                                  //~vafcI~
                if (num>=TN4)   //23(4)--67(8)                     //~vafcR~
                {                                                  //~vafcI~
                    if (PitsHand[Ppos-1]>0 && PitsHand[Ppos-2]>0)  //~vafcR~
                    {                                              //~vafcI~
	                    PitsHand[Ppos-1]--; PitsHand[Ppos-2]--;    //~vafcI~
                        shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand-2);//~vafcI~
	                    PitsHand[Ppos-1]++; PitsHand[Ppos-2]++;    //~vafcI~
		            	if (shanten<Pshanten)                      //~vafcI~
                        {                                          //~vaffI~
	            			rc=isFixedTanyaoWinList(Peswn,shanten,PitsHand,PctrHand,GCM_CHII,Ppos,Ppos-2);//~vafcR~
                            if (rc)                                            //~vafcI~//~vaffI~
                            {                                                  //~vafcI~//~vaffI~
                                if (maxAmtWinList>maxAmtWinListMax)            //~vafcI~//~vaffI~
                                {                                              //~vafcI~//~vaffI~
                                    posChii=Ppos-2;                            //~vafcR~//~vaffI~
                                    maxAmtWinListMax=maxAmtWinList;            //~vafcI~//~vaffI~
                                }                                              //~vafcI~//~vaffI~
                            }                                                  //~vafcI~//~vaffI~
                        }                                          //~vaffI~
                    }                                              //~vafcI~
                }                                                  //~vafcI~
                if (num>=TN3 && num<=TN7)   //2(3)4--6(7)8         //~vafcI~
                {                                                  //~vafcI~
                    if (PitsHand[Ppos-1]>0 && PitsHand[Ppos+1]>0)  //~vafcR~
                    {                                              //~vafcI~
	                    PitsHand[Ppos-1]--; PitsHand[Ppos+1]--;    //~vafcI~
                        shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand-2);//~vafcI~
	                    PitsHand[Ppos-1]++; PitsHand[Ppos+1]++;    //~vafcI~
		            	if (shanten<Pshanten)                      //~vafcI~
                        {                                          //~vaffI~
	            			rc=isFixedTanyaoWinList(Peswn,shanten,PitsHand,PctrHand,GCM_CHII,Ppos,Ppos-1);//~vafcR~
                            if (rc)                                            //~vafcI~//~vaffI~
                            {                                                  //~vafcI~//~vaffI~
                                if (maxAmtWinList>maxAmtWinListMax)            //~vafcI~//~vaffI~
                                {                                              //~vafcI~//~vaffI~
                                    posChii=Ppos-1;                            //~vafcR~//~vaffI~
                                }                                              //~vafcI~//~vaffI~
                            }                                                  //~vafcI~//~vaffI~
                        }                                          //~vaffI~
                    }                                              //~vafcI~
                }                                                  //~vafcI~
            	break;                                             //~vafcI~
            }                                                      //~vafcI~
        }                                                          //~vafcI~
	    if (Dump.Y) Dump.println("RACall.chkFixedTanyao rc="+rc+",posChii="+posChii);//~vafcR~
        return posChii;                                            //~vafcI~
    }                                                              //~vafcI~
 	//******************************************************************************//~vaffI~
 	//*keep Chanta by the chii ? at allInHand                      //~vaffI~
 	//*and confirm pair is fixed(not multiple waiting tile)        //~vaffI~
 	//******************************************************************************//~vaffI~
    private int chkFixedChanta(int Paction,int Pshanten,int Peswn,int[] PitsHand,int PctrHand,int Ppos)//~vaffI~
    {                                                              //~vaffI~
    	boolean rc=false;                                          //~vaffI~
        int posChii=-1;                                            //~vaffI~
        int shanten=Pshanten;                                      //~vaffI~
        int maxAmtWinListMax=-1;                                   //~vaffI~
        if (Dump.Y) Dump.println("RACall.chkFixedChanta action="+Paction+",shannten="+Pshanten+",eswn="+Peswn+",ctrH="+PctrHand+",pos="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~vaffI~
        if (!RAUtils.isChantaMeldTile(Ppos,Paction))               //~vaffR~
        {                                                          //~vaffI~
	        if (Dump.Y) Dump.println("RACall.chkFixedChanta return false by discarded tile is not chantameld");//~vaffR~
            return -1;                                             //~vaffI~
        }                                                          //~vaffI~
        if (Paction==GCM_PON)                                      //~vaffI~
        {                                                          //~vaffI~
            if (PitsHand[Ppos]<PAIRCTR-1)                          //~vaffI~
            {                                                      //~vaffI~
	        	if (Dump.Y) Dump.println("RACall.chkFixedChanta return false by tilectr for Pon pos="+Ppos+",ctr="+PitsHand[Ppos]);//~vaffI~
            	return -1;                                         //~vaffI~
            }                                                      //~vaffI~
            PitsHand[Ppos]-=PAIRCTR-1;                             //~vaffI~
            shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand-2);//~vaffI~
            PitsHand[Ppos]+=PAIRCTR-1;                             //~vaffI~
            if (shanten<Pshanten)                                  //~vaffI~
            {                                                      //~vaffI~
            	rc=isFixedChantaWinList(Peswn,shanten,PitsHand,PctrHand,GCM_PON,Ppos,Ppos);//~vaffI~
                if (rc)                                            //~vaffI~
                    posChii=Ppos;   //not Chii but return not -1   //~vaffI~
            }                                                      //~vaffI~
        }                                                          //~vaffI~
        else                                                       //~vaffI~
        {                                                          //~vaffI~
        	int num=Ppos%CTR_NUMBER_TILE;                          //~vaffI~
            for (;;)                                               //~vaffI~
            {                                                      //~vaffI~
                if (num==TN1 || num==TN7)   //(1)23 or (7)89   num constructs meld//~vaffR~
                {                                                  //~vaffI~
                    if (PitsHand[Ppos+1]>0 && PitsHand[Ppos+2]>0)  //~vaffI~
                    {                                              //~vaffI~
	                    PitsHand[Ppos+1]--; PitsHand[Ppos+2]--;    //~vaffI~
                        shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand-2);//~vaffI~
	                    PitsHand[Ppos+1]++; PitsHand[Ppos+2]++;    //~vaffI~
		            	if (shanten<Pshanten)                      //~vaffI~
                        {                                          //~vaffI~
	            			rc=isFixedChantaWinList(Peswn,shanten,PitsHand,PctrHand,GCM_CHII,Ppos,Ppos);//~vaffI~
                            if (rc)                                //~vaffI~
                            {                                      //~vaffI~
                                posChii=Ppos;                      //~vaffI~
                                maxAmtWinListMax=maxAmtWinList;    //~vaffI~
                            }                                      //~vaffI~
                        }                                          //~vaffI~
                    }                                              //~vaffI~
                }                                                  //~vaffI~
                if (num==TN3 || num ==TN9)   //12(3) or 78(9)      //~vaffR~
                {                                                  //~vaffI~
                    if (PitsHand[Ppos-1]>0 && PitsHand[Ppos-2]>0)  //~vaffI~
                    {                                              //~vaffI~
	                    PitsHand[Ppos-1]--; PitsHand[Ppos-2]--;    //~vaffI~
                        shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand-2);//~vaffI~
	                    PitsHand[Ppos-1]++; PitsHand[Ppos-2]++;    //~vaffI~
		            	if (shanten<Pshanten)                      //~vaffI~
                        {                                          //~vaffI~
	            			rc=isFixedChantaWinList(Peswn,shanten,PitsHand,PctrHand,GCM_CHII,Ppos,Ppos-2);//~vaffI~
                            if (rc)                                //~vaffI~
                            {                                      //~vaffI~
                                if (maxAmtWinList>maxAmtWinListMax)//~vaffI~
                                {                                  //~vaffI~
                                    posChii=Ppos-2;                //~vaffI~
                                    maxAmtWinListMax=maxAmtWinList;//~vaffI~
                                }                                  //~vaffI~
                            }                                      //~vaffI~
                        }                                          //~vaffI~
                    }                                              //~vaffI~
                }                                                  //~vaffI~
                if (num==TN2 || num==TN8)   //1(2)3 or 7(8)9        //~vaffR~
                {                                                  //~vaffI~
                    if (PitsHand[Ppos-1]>0 && PitsHand[Ppos+1]>0)  //~vaffI~
                    {                                              //~vaffI~
	                    PitsHand[Ppos-1]--; PitsHand[Ppos+1]--;    //~vaffI~
                        shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand-2);//~vaffI~
	                    PitsHand[Ppos-1]++; PitsHand[Ppos+1]++;    //~vaffI~
		            	if (shanten<Pshanten)                      //~vaffI~
                        {                                          //~vaffI~
                            if (rc)                                //~vaffI~
                            {                                      //~vaffI~
                                if (maxAmtWinList>maxAmtWinListMax)//~vaffI~
                                {                                  //~vaffI~
                                    posChii=Ppos-1;                //~vaffI~
                                }                                  //~vaffI~
                            }                                      //~vaffI~
	            			rc=isFixedChantaWinList(Peswn,shanten,PitsHand,PctrHand,GCM_CHII,Ppos,Ppos-1);//~vaffI~
                        }                                          //~vaffI~
                    }                                              //~vaffI~
                }                                                  //~vaffI~
            	break;                                             //~vaffI~
            }                                                      //~vaffI~
        }                                                          //~vaffI~
	    if (Dump.Y) Dump.println("RACall.chkFixedChanta rc="+rc+",posChii="+posChii);//~vaffI~
        return posChii;                                            //~vaffI~
    }                                                              //~vaffI~
    //***************************************************************//~vafcI~
    private boolean isFixedTanyaoWinList(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Paction,int Ppos,int PposTop)//~vafcR~
    {                                                              //~vafcI~
    	boolean rc=false;                                          //~vafcI~
        if (Dump.Y) Dump.println("RACall.isFixedTanyaoWinList eswn="+Peswn+",shannten="+Pshanten+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafcI~
        if (Dump.Y) Dump.println("RACall.isFixedTanyaoWinList action="+Paction+",pos="+Ppos+",posTop="+PposTop);//~vafcR~
        int[] stat=RS.RSP[Peswn].getHandStatistic();               //~vafcI~
        int ctrTanyao=stat[CSI_TANYAO];                                //~vafcI~
        int ctrChanta=PctrHand-ctrTanyao;                               //~vafcI~
	    if (Dump.Y) Dump.println("RACall.isFixedTanyaoWinList ctrChanta="+ctrChanta+",ctrTanyao="+ctrTanyao+",ctrHand="+PctrHand);//~vafcI~
//      if (ctrChanta>1)                                           //~vafcI~//~vafkR~
        if (ctrChanta-Pshanten>1)                                  //~vafkI~
        {                                                          //~vafcI~
		    if (Dump.Y) Dump.println("RACall.isFixedTanyaoWinList return false by ctrChanta="+ctrChanta+",Pshanten="+Pshanten);//~vafcI~//~vafkR~
        	return false;                                          //~vafcI~
        }                                                          //~vafcI~
        maxAmtWinList=-1;                                          //~vafcI~
        if (Pshanten==1)                                           //~vafcI~
        {                                                          //~vafcI~
            rc=true;                                               //~vafcR~
        }                                                          //~vafcI~
        else                                                       //~vafcI~
        if (Pshanten==0)                                           //~vafcI~
        {                                                          //~vafcI~
	        int posChanta=RAUtils.selectTile(PitsHand,CSI_TANYAO,false/*select not Tanyao*/);//~vafcR~
            if (posChanta!=-1)                                     //~vafcI~
            {                                                      //~vafcI~
                PitsHand[Ppos]++;	//river tile to call           //~vafcR~
                PitsHand[posChanta]--;                             //~vafcI~
                int[] itsWin=AG.aRAReach.getItsWinList(PitsHand,PctrHand);//~vafcR~
                PitsHand[posChanta]++;                             //~vafcR~
                PitsHand[Ppos]--;	//river tile to call           //~vafcI~
                if (itsWin.length!=0)                              //~vafcR~
                {                                                  //~vafcR~
                    int ctrTanyaoWin=0;                            //~vafcR~
                    boolean swFuriten=false;                       //~vafcR~
                    for (int pos:itsWin)                           //~vafcR~
                    {                                              //~vafcR~
                        if (RAUtils.isTanyaoTile(pos))             //~vafcR~
                            ctrTanyaoWin++;                        //~vafcR~
                        if (RS.isFuritenSelf(Peswn,pos))           //~vafcR~
                        {                                          //~vafcR~
                            swFuriten=true;                        //~vafcR~
                            break;                                 //~vafcR~
                        }                                          //~vafcR~
                    }                                              //~vafcR~
                    if (!swFuriten && ctrTanyaoWin==itsWin.length) //~vafcR~
                        rc=true;                                   //~vafcR~
                    if (Dump.Y) Dump.println("RACall.isFixedTanyaoWinList ctrTanyaoWin="+ctrTanyaoWin);//~vafcR~
                }                                                  //~vafcR~
	            if (rc)                                            //~vafcI~
                {                                                  //~vafcI~
	                PitsHand[Ppos]++;	//river tile to call       //~vafcI~
	                PitsHand[posChanta]--;                         //~vafcI~
    	        	maxAmtWinList=evaluateWinListCall(Peswn,PitsHand,PctrHand,itsWin,Paction,Ppos,PposTop);	//winTilePos and amt//~vafcR~
	                PitsHand[posChanta]++;                         //~vafcI~
	                PitsHand[Ppos]--;	//river tile to call       //~vafcI~
                }                                                  //~vafcI~
            }                                                      //~vafcI~
        }                                                          //~vafcI~
	    if (Dump.Y) Dump.println("RACall.isFixedTanyaoWinList rc="+rc+",maxAmtWinList="+maxAmtWinList);//~vafcR~
    	return rc;                                                 //~vafcI~
    }                                                              //~vafcI~
    //***************************************************************//~vaffI~
    private boolean isFixedChantaWinList(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Paction,int Ppos,int PposTop)//~vaffI~
    {                                                              //~vaffI~
    	boolean rc=false;                                          //~vaffI~
        if (Dump.Y) Dump.println("RACall.isFixedChantaWinList eswn="+Peswn+",shannten="+Pshanten+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaffI~
        if (Dump.Y) Dump.println("RACall.isFixedChantaWinList action="+Paction+",pos="+Ppos+",posTop="+PposTop);//~vaffI~
//      int[] stat=RS.RSP[Peswn].getHandStatistic();               //~vaffR~
//      int ctrTanyao=stat[CSI_TANYAO];                            //~vaffR~
//      int ctrChanta=PctrHand-ctrTanyao;                          //~vaffI~
//      if (Dump.Y) Dump.println("RACall.isFixedChantaWinList ctrTanyao="+ctrTanyao);//~vaffR~
//      if (ctrTanyao>1)                                           //~vaffR~
//      {                                                          //~vaffR~
//  	    if (Dump.Y) Dump.println("RACall.isFixedChantaWinList return false by ctrTanyao="+ctrTanyao);//~vaffR~
//      	return false;                                          //~vaffR~
//      }                                                          //~vaffR~
        maxAmtWinList=-1;                                          //~vaffI~
        if (Pshanten==1)                                           //~vaffI~
        {                                                          //~vaffI~
            rc=true;                                               //~vaffI~
        }                                                          //~vaffI~
        else                                                       //~vaffI~
        if (Pshanten==0)                                           //~vaffI~
        {                                                          //~vaffI~
	        int posTanyao=RAUtils.selectTile(PitsHand,CSI_TANYAO,true/*select Tanyao*/);//~vaffI~
            if (posTanyao!=-1)                                     //~vaffI~
            {                                                      //~vaffI~
                PitsHand[Ppos]++;	//river tile to call           //~vaffI~
                PitsHand[posTanyao]--;                             //~vaffI~
                int[] itsWin=AG.aRAReach.getItsWinList(PitsHand,PctrHand);//~vaffI~
                PitsHand[posTanyao]++;                             //~vaffI~
                PitsHand[Ppos]--;	//river tile to call           //~vaffI~
                if (itsWin.length!=0)                              //~vaffI~
                {                                                  //~vaffI~
                    int ctrChantaWin=0;                            //~vaffI~
                    boolean swFuriten=false;                       //~vaffI~
                    for (int pos:itsWin)                           //~vaffI~
                    {                                              //~vaffI~
                        if (RAUtils.isChantaMeldTile(pos,Paction)) //~vaffR~
                            ctrChantaWin++;                        //~vaffI~
                        if (RS.isFuritenSelf(Peswn,pos))           //~vaffI~
                        {                                          //~vaffI~
                            swFuriten=true;                        //~vaffI~
                            break;                                 //~vaffI~
                        }                                          //~vaffI~
                    }                                              //~vaffI~
                    if (!swFuriten && ctrChantaWin==itsWin.length) //~vaffI~
                        rc=true;                                   //~vaffI~
                    if (Dump.Y) Dump.println("RACall.isFixedChantaWinList ctrChantaWin="+ctrChantaWin);//~vaffI~
                }                                                  //~vaffI~
	            if (rc)                                            //~vaffI~
                {                                                  //~vaffI~
	                PitsHand[Ppos]++;	//river tile to call       //~vaffI~
	                PitsHand[posTanyao]--;                         //~vaffI~
    	        	maxAmtWinList=evaluateWinListCall(Peswn,PitsHand,PctrHand,itsWin,Paction,Ppos,PposTop);	//winTilePos and amt//~vaffI~
	                PitsHand[posTanyao]++;                         //~vaffI~
	                PitsHand[Ppos]--;	//river tile to call       //~vaffI~
                }                                                  //~vaffI~
            }                                                      //~vaffI~
        }                                                          //~vaffI~
	    if (Dump.Y) Dump.println("RACall.isFixedChantaWinList rc="+rc+",maxAmtWinList="+maxAmtWinList);//~vaffI~
    	return rc;                                                 //~vaffI~
    }                                                              //~vaffI~
//    //*NoUSer*                                                   //~vajcR~
//    //***************************************************************//~vajcI~
//    private boolean isFixed2ndCall(int Peswn,int[] PitsHand,int PctrHand,int Paction,int Ppos,int PposTop)//~vafnI~//~vajcR~
//    {                                                              //~vafnI~//~vajcR~
//        if (Dump.Y) Dump.println("RACall.isFixed2ndCall eswn="+Peswn+",pos="+Ppos+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafnR~//~vajcR~
//        boolean rc=true;                                         //~vajcR~
//        //*get discard list for shanten=0                              //~vafnI~//~vajcR~
//        if (Paction==GCM_PON)                                      //~vafnI~//~vajcR~
//            PitsHand[Ppos]-=2;                                     //~vafnI~//~vajcR~
//        else                                                       //~vafnI~//~vajcR~
//            for (int pos=PposTop,ii=0;ii<PAIRCTR;ii++,pos++)       //~vafnI~//~vajcR~
//            {                                                      //~vafnI~//~vajcR~
//                if (pos!=Ppos)                                     //~vafnI~//~vajcR~
//                    PitsHand[ii]--;                                 //~vafnI~//~vajcR~
//            }                                                      //~vafnI~//~vajcR~
//                                                                   //~vafnI~//~vajcR~
//        int[] itsDiscard=getDiscardList(Peswn,PitsHand,PctrHand-2);//~vafnI~//~vajcR~
//    //*get winlist and chk fixed                                   //~vafnI~//~vajcR~
//        int ctrDiscard=itsDiscard.length;                              //~vafnI~//~vajcR~
//        for (int ii=0;ii<ctrDiscard;ii++)                          //~vafnI~//~vajcR~
//        {                                                          //~vafnI~//~vajcR~
//            int pos=itsDiscard[ii];                                    //~vafnI~//~vajcR~
//            PitsHand[pos]--;        //as discarded                     //~vafnI~//~vajcR~
//            int[] itsWin=AG.aRAReach.getItsWinList(PitsHand,PctrHand-1);//~vafnI~//~vajcR~
//            boolean rc2=isFixed2ndCallRonable(Peswn,PitsHand,PctrHand-1,Paction,Ppos,PposTop,itsWin);//~vafnI~//~vajcR~
//            PitsHand[pos]++;                                       //~vafnI~//~vajcR~
//            if (!rc2)                                              //~vafnI~//~vajcR~
//            {                                                      //~vafnI~//~vajcR~
//                rc=false;                                          //~vafnI~//~vajcR~
//                break;                                             //~vafnI~//~vajcR~
//            }                                                      //~vafnI~//~vajcR~
//        }                                                          //~vafnI~//~vajcR~
//        if (Paction==GCM_PON)                                      //~vafnI~//~vajcR~
//            PitsHand[Ppos]+=2;                                     //~vafnI~//~vajcR~
//        else                                                       //~vafnI~//~vajcR~
//            for (int pos=PposTop,ii=0;ii<PAIRCTR;ii++,pos++)       //~vafnI~//~vajcR~
//            {                                                      //~vafnI~//~vajcR~
//                if (pos!=Ppos)                                     //~vafnI~//~vajcR~
//                    PitsHand[ii]++;                                 //~vafnI~//~vajcR~
//            }                                                      //~vafnI~//~vajcR~
//        if (Dump.Y) Dump.println("RACall.isFixed2ndCall eswn="+Peswn+",rc="+rc);//~vafnI~//~vajcR~
//        return rc;                                                 //~vafnI~//~vajcR~
//    } //isFixed2ndCall                                                             //~vafnI~//~vajcR~
    //***************************************************************//~vafnI~
    //*from isTimeToCall under the condition shanten up to 0       //~vafnI~
    //***************************************************************//~vafnI~
    private int selectFixedCallShanten0_Chii(int Peswn,int[] PitsHand,int PctrHand,int Ppos)//~vafnR~
    {                                                              //~vafnI~
        if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_Chii entry eswn="+Peswn+",pos="+Ppos+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafnR~
        int type=Ppos/CTR_NUMBER_TILE;                             //~vafnI~
        int num=Ppos%CTR_NUMBER_TILE;                              //~vafnI~
        int amtMax=0,amt;                                          //~vafnI~
        int posChii=-1;                                            //~vafnI~
        if (num<=TN7)   //(1)23--(7)89   (num is confirmed as tanyao)//~vafnI~
        {                                                          //~vafnI~
            if (PitsHand[Ppos+1]>0 && PitsHand[Ppos+2]>0)          //~vafnI~
            {                                                      //~vafnI~
                amt=evaluateFixedCallShanten0(GCM_CHII,Peswn,PitsHand,PctrHand,Ppos,Ppos);//~vafnI~
                if (amt!=0)                                        //~vafnI~
                {                                                  //~vafnI~
                	amtMax=amt;                                    //~vafnI~
                    posChii=Ppos;                                  //~vafnI~
                }                                                  //~vafnI~
            }                                                      //~vafnI~
        }                                                          //~vafnI~
        if (num>=TN3)   //12(3)--78(9)                             //~vafnR~
        {                                                          //~vafnI~
            if (PitsHand[Ppos-1]>0 && PitsHand[Ppos-2]>0)          //~vafnI~
            {                                                      //~vafnI~
                amt=evaluateFixedCallShanten0(GCM_CHII,Peswn,PitsHand,PctrHand,Ppos,Ppos-2);//~vafnI~
                if (amt!=0 && amt>amtMax)                             //~vafnI~
                {                                                  //~vafnI~
                	amtMax=amt;                                    //~vafnI~
                    posChii=Ppos-2;                                //~vafnR~
                }                                                  //~vafnI~
            }                                                      //~vafnI~
        }                                                          //~vafnI~
        if (num>=TN2 && num<=TN8)   //1(2)3--7(8)9                 //~vafnI~
        {                                                          //~vafnI~
            if (PitsHand[Ppos-1]>0 && PitsHand[Ppos+1]>0)          //~vafnI~
            {                                                      //~vafnI~
                amt=evaluateFixedCallShanten0(GCM_CHII,Peswn,PitsHand,PctrHand,Ppos,Ppos-1);//~vafnI~
                if (amt!=0 && amt>amtMax)                             //~vafnI~
                {                                                  //~vafnI~
                    posChii=Ppos-1;                                //~vafnR~
                }                                                  //~vafnI~
            }                                                      //~vafnI~
        }                                                          //~vafnI~
	    if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_Chii exit eswn="+Peswn+",posChii="+posChii);//~vafnR~
        return posChii;
    }                                                              //~vafnI~
    //***************************************************************//~vafnI~
    //*from isTimeToCall under the condition shanten up to 0       //~vafnI~
    //***************************************************************//~vafnI~
    private int selectFixedCallShanten0_ChiiSelected(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Ppos,int PposTop)//~vafnI~
    {                                                              //~vafnI~
        if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_ChiiSelected entry eswn="+Peswn+",shanten="+Pshanten+",pos="+Ppos+",posTop="+PposTop+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafnR~
        int posChii=PposTop;                                       //~vafnR~
        if (Pshanten==1 && chkShantenAddCall(Peswn,PitsHand,PctrHand,Ppos,1)==0)    //1-naki tenpai//~vafnI~
        {                                                          //~vafnI~
        	int amt=evaluateFixedCallShanten0(GCM_CHII,Peswn,PitsHand,PctrHand,Ppos,PposTop);//~vafnI~
        	if (amt==0)                                            //~vafnR~
        		posChii=-1;                                        //~vafnR~
        }                                                          //~vafnI~
	    if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_ChiiSelected exit eswn="+Peswn+",posChii="+posChii);//~vafnI~
        return posChii;                                            //~vafnI~
    }                                                              //~vafnI~
    //***************************************************************//~vafnI~
    //*from isTimeToCall under the condition shanten up to 0       //~vafnI~
    //***************************************************************//~vafnI~
    private int selectFixedCallShanten0_Pon(int Peswn,int[] PitsHand,int PctrHand,int Ppos)//~vafnI~
    {                                                              //~vafnI~
        if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_Pon entry eswn="+Peswn+",pos="+Ppos+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafnI~
        int posPon;                                                //~vafnI~
        int amt=evaluateFixedCallShanten0(GCM_PON,Peswn,PitsHand,PctrHand,Ppos,Ppos);//~vafnI~
        if (amt==0)                                                //~vafnI~
        	posPon=-1;                                              //~vafnI~
        else                                                       //~vafnI~
        	posPon=Ppos;                                           //~vafnI~
	    if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_Pon exit eswn="+Peswn+",amt="+amt+",posPon="+posPon);//~vafnR~
        return posPon;                                             //~vafnR~
    }                                                              //~vafnI~
    //***************************************************************//~vafnI~
    private int selectFixedCallShanten0_PonSelected(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Ppos)//~vafnI~
    {                                                              //~vafnI~
        if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_PonSelected entry eswn="+Peswn+",shanten="+Pshanten+",pos="+Ppos+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafnI~
        int posPon=Ppos;                                           //~vafnR~
        if (Pshanten==1 && chkShantenAddCall(Peswn,PitsHand,PctrHand,Ppos,1)==0)    //1-naki tenpai//~vafnI~
        {                                                          //~vafnI~
        	int amt=evaluateFixedCallShanten0(GCM_PON,Peswn,PitsHand,PctrHand,Ppos,Ppos);//~vafnI~
        	if (amt==0)                                            //~vafnR~
        		posPon=-1;                                         //~vafnR~
        }                                                          //~vafnI~
	    if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_PonSelected exit eswn="+Peswn+",posPon="+posPon);//~vafnI~
        return posPon;                                             //~vafnI~
    }                                                              //~vafnI~
    //***************************************************************//~vag0I~
    private int selectFixedCallShanten0_KanSelected(int Peswn,int Pshanten,int[] PitsHand,int PctrHand,int Ppos)//~vag0I~
    {                                                              //~vag0I~
        if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_KanSelected entry eswn="+Peswn+",shanten="+Pshanten+",pos="+Ppos+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vag0I~
        int posPon=Ppos;                                           //~vag0I~
        if (Pshanten==0)                                           //~vag0R~
        {                                                          //~vag0I~
        	int amt=evaluateFixedCallShanten0_Kan(Peswn,PitsHand,PctrHand,Ppos,Ppos);//~vag0I~
        	if (amt==0)                                            //~vag0I~
        		posPon=-1;                                         //~vag0I~
        }                                                          //~vag0I~
	    if (Dump.Y) Dump.println("RACall.selectFixedCallShanten0_KanSelected exit eswn="+Peswn+",posPon="+posPon);//~vag0R~
        return posPon;                                             //~vag0I~
    }                                                              //~vag0I~
    //***************************************************************//~vafnI~
    private int evaluateFixedCallShanten0(int Paction,int Peswn,int[] PitsHand,int PctrHand,int Ppos,int PposTop)//~vafnR~
    {                                                              //~vafnI~
        if (Dump.Y) Dump.println("RACall.evaluateFixedCallShanten0 entry action="+Paction+",eswn="+Peswn+",pos="+Ppos+",posTop="+PposTop+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafnR~
        boolean rc=true;                                           //~vafnI~
        //*get discard list for shanten=0                          //~vafnI~
        if (Paction==GCM_PON)                                      //~vafnI~
        	PitsHand[Ppos]-=2;                                     //~vafnI~
        else                                                       //~vafnI~
            for (int pos=PposTop,ii=0;ii<PAIRCTR;ii++,pos++)       //~vafnI~
            {                                                      //~vafnI~
            	if (pos!=Ppos)                                     //~vafnI~
	                PitsHand[pos]--;                               //~vafnR~
            }                                                      //~vafnI~
                                                                   //~vafnI~
        int[] itsDiscard=getDiscardList(Peswn,PitsHand,PctrHand-2);//~vafnI~
    //*get winlist and chk fixed                                   //~vafnI~
        int ctrDiscard=itsDiscard.length;                          //~vafnI~
        int amtMax=0;                                              //~vafnI~
        for (int ii=0;ii<ctrDiscard;ii++)                          //~vafnI~
        {                                                          //~vafnI~
        	int pos=itsDiscard[ii];                                //~vafnI~
	    	if (Dump.Y) Dump.println("RACall.evaluateFixedCallShanten0 posDiscard="+pos);//~vafnI~
            PitsHand[pos]--;		//as discarded                 //~vafnI~
			int amt=evaluateFixedCallRonable(Peswn,PitsHand,PctrHand-3,Paction,Ppos,PposTop);//~vafnR~
        	PitsHand[pos]++;                                       //~vafnI~
//          if (amt==0)         amt!=0 will be selected at discard //~vafnR~
//          {                                                      //~vafnR~
//          	amtMax=0;                                          //~vafnR~
//          	break;                                             //~vafnR~
//          }                                                      //~vafnR~
            amtMax=Math.max(amtMax,amt);                           //~vafnI~
        }                                                          //~vafnI~
	    if (Dump.Y) Dump.println("RACall.evaluateFixedCallShanten0 after all discardlist tryed eswn="+Peswn+",amtMax="+amtMax+",itsHand="+Utils.toString(PitsHand,9));//~vafnI~
        if (Paction==GCM_PON)                                      //~vafnI~
        	PitsHand[Ppos]+=2;                                     //~vafnI~
        else                                                       //~vafnI~
            for (int pos=PposTop,ii=0;ii<PAIRCTR;ii++,pos++)       //~vafnI~
            {                                                      //~vafnI~
            	if (pos!=Ppos)                                     //~vafnI~
	                PitsHand[pos]++;                               //~vafnR~
            }                                                      //~vafnI~
	    if (Dump.Y) Dump.println("RACall.evaluateFixedCallShanten0 exit eswn="+Peswn+",amtMax="+amtMax+",itsHand="+Utils.toString(PitsHand,9));//~vafnR~
    	return amtMax;                                             //~vafnR~
    }                                                              //~vafnI~
    //***************************************************************//~vag0I~
    private int evaluateFixedCallShanten0_Kan(int Peswn,int[] PitsHand,int PctrHand,int Ppos,int PposTop)//~vag0I~
    {                                                              //~vag0I~
        if (Dump.Y) Dump.println("RACall.evaluateFixedCallShanten0_Kan entry eswn="+Peswn+",pos="+Ppos+",posTop="+PposTop+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vag0I~
        boolean rc=true;                                           //~vag0I~
        //*get discard list for shanten=0                          //~vag0I~
        PitsHand[Ppos]-=3;                                         //~vag0I~
		int amt=evaluateFixedCallRonable(Peswn,PitsHand,PctrHand-3,GCM_KAN,Ppos,PposTop);//~vag0I~
        PitsHand[Ppos]+=3;                                         //~vag0I~
	    if (Dump.Y) Dump.println("RACall.evaluateFixedCallShanten0 exit_Kan eswn="+Peswn+",amt="+amt+",itsHand="+Utils.toString(PitsHand,9));//~vag0I~//~vag3R~
    	return amt;                                                //~vag0I~
    }                                                              //~vag0I~
//    //***************************************************************//~vafnI~//~vajcR~
//    //*NoUser                                                    //~vajcI~
//    private boolean isFixed2ndCallRonable(int Peswn,int[] PitsHand,int PctrHand,int Paction,int Ppos,int PposTop,int[] PitsWin)//~vafnI~//~vajcR~
//    {                                                              //~vafnI~//~vajcR~
//        if (Dump.Y) Dump.println("RACall.isFixed2ndCallRonable eswn="+Peswn+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafnI~//~vajcR~
//        if (Dump.Y) Dump.println("RACall.isFixed2ndCallRonable action="+Paction+",PposTop="+PposTop+",itsWin="+Utils.toString(PitsWin));//~vafnI~//~vajcR~
//        int[] itsWin=AG.aRAReach.getItsWinList(PitsHand,PctrHand); //~vafnI~//~vajcR~
//        if (itsWin.length==0)                                      //~vafnI~//~vajcR~
//        {                                                          //~vafnI~//~vajcR~
//            if (Dump.Y) Dump.println("RACall.isFixed2ndCallRonable return false by winlist=0");//~vafnI~//~vajcR~
//            return false;                                          //~vafnI~//~vajcR~
//        }                                                          //~vafnI~//~vajcR~
//        for (int pos:itsWin)                                       //~vafnI~//~vajcR~
//        {                                                          //~vafnI~//~vajcR~
//            if (RS.isFuritenSelf(Peswn,pos))                       //~vafnI~//~vajcR~
//            {                                                      //~vafnI~//~vajcR~
//                if (Dump.Y) Dump.println("RACall.isFixed2ndCallRonable return false by fuliten pos="+pos);//~vafnI~//~vajcR~
//                return false;                                      //~vafnI~//~vajcR~
//            }                                                      //~vafnI~//~vajcR~
//        }                                                          //~vafnI~//~vajcR~
//        boolean rc=AG.aRADSEval.evaluateWinListCallRonable(Peswn,PitsHand,PctrHand,Paction,PposTop,PitsWin);    //winTilePos and amt//~vafnI~//~vajcR~
//        if (Dump.Y) Dump.println("RACall.isFixed2ndCallRonable eswn="+Peswn+",rc="+rc);//~vafnI~//~vajcR~
//        return rc;                                                 //~vafnI~//~vajcR~
//    }                                                              //~vafnI~//~vajcR~
    //***************************************************************//~vafnI~
    private int evaluateFixedCallRonable(int Peswn,int[] PitsHand,int PctrHand,int Paction,int Ppos,int PposTop)//~vafnR~
    {                                                              //~vafnI~
        if (Dump.Y) Dump.println("RACall.evaluateFixedCallRonable entry eswn="+Peswn+",ctrH="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafnI~
        if (Dump.Y) Dump.println("RACall.evaluateFixedCallRonable action="+Paction+",PposTop="+PposTop);//~vafnR~
        int[] itsWin=AG.aRAReach.getItsWinList(PitsHand,PctrHand); //~vafnI~
        if (itsWin.length==0)                                      //~vafnI~
        {                                                          //~vafnI~
            if (Dump.Y) Dump.println("RACall.evaluateFixedCallRonable return 0 by winlist=0");//~vafnI~
        	return 0;                                              //~vafnI~
        }                                                          //~vafnI~
        for (int pos:itsWin)                                       //~vafnI~
        {                                                          //~vafnI~
            if (RS.isFuritenSelf(Peswn,pos))                       //~vafnI~
            {                                                      //~vafnI~
	            if (Dump.Y) Dump.println("RACall.evaluateFixedCallRonable return false by fuliten pos="+pos);//~vafnR~
                return 0;                                      //~vafnI~
            }                                                      //~vafnI~
        }                                                          //~vafnI~
        int amt=AG.aRADSEval.evaluateFixedCallRonableWinList(Peswn,PitsHand,PctrHand,Paction,PposTop,itsWin);	//winTilePos and amt//~vafnR~
        if (Dump.Y) Dump.println("RACall.evaluateFixedCallRonable exit eswn="+Peswn+",amt="+amt);//~vafnI~
    	return amt;                                                //~vafnI~
    }                                                              //~vafnI~
    //***************************************************************//~vafcI~
    private int evaluateWinListCall(int Peswn,int[] PitsHand,int PctrHand,int[] PitsWin,int Paction,int Ppos,int PposTop)//~vafcR~
    {                                                              //~vafcI~
        if (Dump.Y) Dump.println("RACall.evaluateWinListCall eswn="+Peswn+",winList="+Utils.toString(PitsWin)+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vafcR~
        if (Dump.Y) Dump.println("RACall.evaluateWinListCall action="+Paction+",pos="+Ppos+",posTop="+PposTop);//~vafcR~
//        if (Paction==GCM_PON)                                    //~vafcR~
//            PitsHand[Ppos]+=PAIRCTR-1;                           //~vafcR~
//        else                                                     //~vafcR~
//        if (Paction==GCM_CHII)                                   //~vafcR~
//        {                                                        //~vafcR~
//            for (int pos=PposTop,ii=0;ii<PAIRCTR;ii++,pos++)     //~vafcR~
//                if (pos!=Ppos)                                   //~vafcR~
//                    PitsHand[pos]++;                             //~vafcR~
//        }                                                        //~vafcR~
        Point posAndAmt=AG.aRADSEval.evaluateWinListCall(Peswn,PitsHand,PctrHand,PitsWin);	//winTilePos and amt//~vafcR~
//        if (Paction==GCM_PON)                                    //~vafcR~
//            PitsHand[Ppos]-=PAIRCTR-1;                           //~vafcR~
//        else                                                     //~vafcR~
//        if (Paction==GCM_CHII)                                   //~vafcR~
//        {                                                        //~vafcR~
//            for (int pos=PposTop,ii=0;ii<PAIRCTR;ii++,pos++)     //~vafcR~
//                if (pos!=Ppos)                                   //~vafcR~
//                    PitsHand[pos]--;                             //~vafcR~
//        }                                                        //~vafcR~
        int amt=posAndAmt.y;                                       //~vafcI~
	    if (Dump.Y) Dump.println("RACall.evaluateWinListCall posAndAmt="+posAndAmt.toString()+",rc="+amt);//~vafcR~
    	return amt;                                                //~vafcI~
    }                                                              //~vafcI~
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
        	if (Dump.Y) Dump.println("RACall.isStatusHurryUpToGoal player="+ii+",score="+score+",scoreHurryUpGoal="+scoreHurryUpGoal);//~vac1I~
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
    //*for wordtile Pon at final game in AllInHand,chk place up    //~vaipR~
    //*******************************************************************************************//~vaipI~
//  private boolean isStatusAimToHigherScore(int Pplayer,int Pintent)//~vabtI~//~vaipR~
    private boolean isStatusAimToHigherScore(int Peswn,int Pplayer,int Pintent,int Phan)//~vaipR~
    {                                                              //~vabtI~
//  	boolean rc=false;                                          //~vabtI~//~vaipR~
        if (Dump.Y) Dump.println("RACall.isStatusAimToHigherScore eswn="+Peswn+",player="+Pplayer+",intent="+Integer.toHexString(Pintent)+",tempHan="+Phan);//~vaipR~
      	if (!Status.isNearFinalGame())                             //~vaipI~
        {                                                          //~vaipI~
	        if (Dump.Y) Dump.println("RACall.isStatusAimToHigherScore return false by Not finalGame");//~vaipI~
        	return false;                                          //~vaipI~
        }                                                          //~vaipI~
        int[] scoreS=AG.aAccounts.score;                           //~vabtI~
//      int debt=scorePlus;                                        //~vabtI~//~vaipR~
//      int limit=debt-Complete.POINT_RANKM; //mangan under debt   //~vabtI~//~vaipR~
        int idx=AG.aAccounts.playerToPosition(Pplayer);            //~vabtI~
        int score=scoreS[idx];	//account index seq                //~vabtI~
        if (Dump.Y) Dump.println("RACall.isStatusAimToHigherScore current score="+score+",idx="+idx);//~vaipI~
        int idxNextHigh=getIdxNextHigh(scoreS,idx);                //~vaipI~
        if (idxNextHigh==-1)    //me is top                        //~vaipI~
        {                                                          //~vaipI~
	        if (Dump.Y) Dump.println("RACall.isStatusAimToHigherScore return false by me is Top");//~vaipI~
        	return false;                                          //~vaipI~
        }                                                          //~vaipI~
        int limit=scoreS[idxNextHigh];                             //~vaipI~
        int han=Phan;	//fixed1+dora+honor tile                   //~vaipI~
        if ((Pintent & (INTENT_3DRAGON))!=0)                       //~vaipM~
        	han+=4;                                                //~vaipI~
        if ((Pintent & (INTENT_ALLSAME | INTENT_SAMECOLOR_ANY))!=0)//~vaipM~
        	han+=2;                                                //~vaipM~
//      if ((Pintent & (INTENT_CHANTA))!=0)   //ctrDora is ambiguous for chanta//~vaipR~
//      	han+=1;                                                //~vaipR~
        score+=calcTempPoint(Peswn,han);                           //~vaipI~
        if (Dump.Y) Dump.println("RACall.isStatusAimToHigherScore new score by han="+score);//~vaipR~
//      if (score<limit)                                           //~vabtI~//~vaipR~
//      	rc=Status.isNearFinalGame();                           //~vabtI~//~vaipR~
		boolean rc=score<=limit;                                   //~vaipR~
        if (Dump.Y) Dump.println("RACall.isStatusAimToHigherScore rc="+rc+",idx="+idx+",Pplayer="+Pplayer+",playerScore="+score+",limit="+limit);//~vabtI~//~vaipR~
        return rc;                                                 //~vabtI~
    }                                                              //~vabtI~
    //*******************************************************************************************//~vaipI~
    private int calcTempPoint(int Peswn,int Phan)                  //~vaipI~
    {                                                              //~vaipI~
    	int amt= CompReqDlg.calcTempPoint(Peswn,Phan,swRankMUp); //~vaipI~
        if (Dump.Y) Dump.println("RACall.calcTempPoint amt="+amt+",eswn="+Peswn+",han="+Phan);//~vaipI~
        return amt;                                                //~vaipI~
    }                                                              //~vaipI~
    //*******************************************************************************************//~vaipI~
    private int getIdxNextHigh(int[] PscoreS,int PidxMe)           //~vaipI~
    {                                                              //~vaipI~
        if (Dump.Y) Dump.println("RACall.getIdxNextHigh score="+Utils.toString(PscoreS)+",idxMe="+PidxMe);//~vaipI~
        int idxNextHigh=-1;                                        //~vaipI~
        int myScore=PscoreS[PidxMe];                               //~vaipI~
        int nextHigh=scorePlus+32000;                             //~vaipI~
        for (int ii=0;ii<PLAYERS;ii++)                             //~vaipI~
        {                                                          //~vaipI~
        	if (ii!=PidxMe && PscoreS[ii]>=myScore)                //~vaipI~
            {                                                      //~vaipI~
            	if (PscoreS[ii]<nextHigh)                           //~vaipI~
                {                                                  //~vaipI~
                	nextHigh=PscoreS[ii];                          //~vaipI~
                    idxNextHigh=ii;                                //~vaipI~
                }                                                  //~vaipI~
            }                                                      //~vaipI~
        }                                                          //~vaipI~
        if (Dump.Y) Dump.println("RACall.getIdxNextHigh idx="+idxNextHigh);//~vaipI~
        return idxNextHigh;                                        //~vaipI~
    }                                                              //~vaipI~
}//class RACall                                                    //~1117R~
