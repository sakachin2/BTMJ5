//*CID://+va8zR~: update#= 245;                                    //+va8zR~
//**********************************************************************//~v101I~
//2021/05/04 va8z not avoid call when INTENT_GIVEUP_WEAK(set by remaining tile) is on refered as caution level//+va8zI~
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
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Robot;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.TileData.*;
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
        if (Dump.Y) Dump.println("RACall.init");                   //~1117R~
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
		        	if (calledKan(ii,Pplayer,PplayerEswn,PkanType,PtdKan))
		        		rc=true;//~1118R~                          //~1201R~
        }                                                          //~1118I~
        if (Dump.Y) Dump.println("RACall.otherKan rc="+rc);        //~1201I~
        return rc;
    }                                                              //~1118I~
    //*********************************************************    //~1119I~
    //*for Robot:PeswnOther inthinkRobot mode                      //~va89I~
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
    //***********************************************************************    //~1117I~//~1118M~//~1129R~
    //*from RoundStat  at discard(<--Player)/timeoutPonKan(<--UADiscard)                   //~1118M~//~1128R~//~1129R~
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
	                        nextPlayerPonKanPlayAlone(eswn,PplayerDiscarded,PeswnDiscarded,PtdDiscarded);//~va70I~
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
    //*********************************************************    //~va81I~
    //*from RS.takeOne for Human player at PlayeAloneNotify mode   //~va81I~
    //*highligten Draw btn                                         //~va81I~
    //*********************************************************    //~va81I~
    public  void takeOnePlayAloneNotify(int Ppos,TileData PtdTaken)//~va81R~
    {                                                              //~va81I~
    	int eswn=Accounts.getCurrentEswn();                        //~va81I~
        boolean swRonNoPair=false;                                 //~va83I~
        if (Dump.Y) Dump.println("RACall.takeOnePlayAloneNotify swTrainingMode="+AG.swTrainingMode+",playAlonNotify="+AG.swPlayAloneNotify+",eswn="+eswn+",tdTake="+PtdTaken.toString());//~va81I~
        if (RS.getCurrentShanten(eswn)!=0)                         //~va81I~
        {                                                          //~va83I~
        	swRonNoPair=isRonNoPair(eswn,Ppos);  //chk 13/14 NoPair//~va83I~
          if (!swRonNoPair)                                        //~va83I~
        	return;                                                //~va81I~
        }                                                          //~va83I~
        int shantenNew;                                            //~va83I~
      if (swRonNoPair)                                             //~va83I~
        shantenNew=-1;	//ronable                                  //~va83I~
      else                                                         //~va83I~
      {                                                            //~va83I~
//      RS.RSP[eswn].setShantenYou();	//set currentShanten (taken is included)/+va81R~//~va81R~//~va89R~
        shantenNew=RS.RSP[eswn].getShantenYou();	//set currentShanten (taken is included)/+va81R~//~va89R~
        if (shantenNew==-1)	//ronable                              //~va8jI~
        {                                                          //~va8jI~
        	int[] itsH=RS.getItsHandEswn(eswn);                       //~va8jI~
        	int   ctrH=RS.RSP[eswn].ctrHand;                       //~va8jI~
		    if (!AG.aRARon.callRonTakenPlayAloneNotify(PLAYER_YOU,eswn,itsH,ctrH,PtdTaken))//~va8jI~
            {                                                      //~va8jI~
        		if (Dump.Y) Dump.println("RACall.takeOnePlayAloneNotify@@@@ return by MultiWait/Furiten err");//~va8jR~
                return;                                            //~va8jI~
            }                                                      //~va8jI~
        }                                                          //~va8jI~
      }                                                            //~va83I~
        if (shantenNew==-1)	//ronable                              //~va81I~
//          AG.aUADelayed.notify2TouchPlayAloneNotify(GCM_RON);	//update GC btn nochk 1han constraint//~va88I~
        	AG.aUARon.selectInfoPlayAloneNotifyTake(PtdTaken);	//chk furiten,han constraint then highlight Ron btn and show Cancel btn//~va88R~
    }                                                              //~va81I~
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
    //*not reached called and time to Pon/Kan notify callable      //~va70I~
    //*********************************************************    //~va70I~
    private void nextPlayerPonKanPlayAlone(int PeswnOther,int PplayerDiscarded,int PeswnDiscarded,TileData PtdDiscarded)//~va70I~
    {                                                              //~va70I~
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKanPlayAlone eswnOther="+PeswnOther+",eswnDiscarded="+PeswnDiscarded);//~va70I~
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
        if (Dump.Y) Dump.println("RACall.nextPlayerPonKanPlayAlone exit");//~va70I~
    }                                                              //~va70I~
    //*********************************************************    //~1128I~
    //*From UADiscard-->Robot->RoundStat-->                        //~1129R~
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
    //*From UADiscard at autoTakeTimeout when Notify mode          //~va75I~
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
//      if ((intent & (INTENT_GIVEUP|INTENT_GIVEUP_WEAK))!=0)       //~1224I~//+va8zR~
        if ((intent & (INTENT_GIVEUP))!=0)                         //+va8zI~
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
    //*human player in trainingn mode                              //~va70R~
    //*********************************************************    //~va70I~
    private boolean isCallablePlayAlone(int Paction,int PplayerOther,int PeswnOther,int PeswnDiscarded,TileData PtdDiscarded)//~va70I~
    {                                                              //~va70I~
    	boolean rc=true;                                           //~va70I~
	    if (Dump.Y) Dump.println("RACall.isCallablePlayAlone swTrainingMode="+AG.swTrainingMode+",notifymode="+AG.swPlayAloneNotify);//~va70I~
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
	        if (Dump.Y) Dump.println("RACall.isCallablePPlayAlone @@@@ False last tile");//~va70I~
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
        if (Dump.Y) Dump.println("RACall.callPonKan Pshanten="+Pshanten+",pos="+pos+",intent="+Integer.toHexString(intent)+",eswnOther="+PeswnOther+",playerOther="+PplayerOther+",PplayerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",tdDiscard="+TileData.toString(PtdDiscarded));//~1118R~//~1124R~//~1125I~//~1126R~//~1130R~//~1220R~//~1222R~
        int[] itsH=RS.getItsHandEswn(PeswnOther);                   //~1118I~//~1124R~//~1126R~//~1222R~
        int   ctrH=RS.RSP[PeswnOther].ctrHand;                       //~1118I~//~1124R~//~1126R~//~1222R~
        int ctrPos=itsH[pos];                                      //~1118R~//~1222R~
        if (ctrPos<PAIRCTR-1)                                      //~1119I~//~1222R~
        {                                                          //~1119I~//~1222R~
            if (Dump.Y) Dump.println("RACall.callPonKan return by could not make same meld ctrPos="+ctrPos);//~1119I~//~1125R~//~1222R~
            return false;                                          //~1119I~//~1222R~
        }                                                          //~1119I~//~1222R~
        int ctrCall=0;                                             //~1119R~//~1222R~
      if ((TestOption.option2 & TO2_CALL1ST)!=0)                   //~va75I~
      {                                                            //~va75I~
	  	if (Dump.Y) Dump.println("RACall.callPonKan ignore intent and fix1 by testoption TO2_CALL1ST");//~va75I~
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
            if (pos>=OFFS_WORDTILE)                                //~1118R~//~1119R~//~1220R~//~1222R~
            {                                                      //~1118I~//~1119R~//~1220R~//~1222R~
                int v=RS.RSP[PeswnOther].getCtrValueWordDup();     //~va8hI~
                int hanTile=RAUtils.chkValueWordTile(pos,PeswnOther)/2;   //2 for doubleEast//~va8oI~
                boolean swAllInHand=RS.RSP[PeswnOther].swAllInHand;//~va8oI~
                boolean swFixed1=RS.RSP[PeswnOther].isFixed1();    //~va8pI~
			    if (Dump.Y) Dump.println("RACall.callPonKan v="+v+",hanTile="+hanTile+",swFixed1="+swFixed1+"swAllInHand="+swAllInHand);//~va8pI~
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
                    ctrCall=ctrPos+1;           //3 or 4               //~1119R~//~1220R~//~1222R~
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
                        if (shanten<shanten0)                          //~1119I~//~1131R~//~1220R~//~1222R~
                            ctrCall=PAIRCTR;    //3                    //~1119I~//~1220R~//~1222R~
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
        if (Dump.Y) Dump.println("RACall.callPonKanPlayAlone itsHand="+Utils.toString(itsH,9,ctrH));//~va70I~
        if (ctrPos<PAIRCTR-1)                                      //~va70I~
        {                                                          //~va70I~
            if (Dump.Y) Dump.println("RACall.callPonKan return by could not make same meld ctrPos="+ctrPos);//~va70I~
            return false;                                          //~va70I~
        }                                                          //~va70I~
        AG.aUAD2Touch.updateBtnPlayAloneNotify(ctrPos==PAIRCTR ? GCM_KAN_OR_PON : GCM_PON,BTN_STATUS_ENABLE_CANCEL);//~va70R~
        return true;                                               //~va70I~
    }                                                              //~va70I~
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
        if (Dump.Y) Dump.println("RACall.callChii eswnOther="+PeswnOther+",playerOther="+PplayerOther+",PplayerDiscarded="+PplayerDiscarded+",eswnDiscarded="+PeswnDiscarded+",shanten="+shanten+",tdDiscard="+TileData.toString(PtdDiscarded));//~1118R~//~1126R~//~1128R~//~1129R~
    	int pos=RAUtils.getPosTile(PtdDiscarded);                //~1118I~//~1119R~
    	int[] itsH=RS.getItsHandEswn(PeswnOther);                   //~1118I~//~1126R~
    	int   ctrH=RS.RSP[PeswnOther].ctrHand;                       //~1118I~//~1126R~
    	int   intent=RS.RSP[PeswnOther].intent;                     //~1118I~//~1126R~
        int posChii=-1;                                            //~1118R~
	    if (pos>=OFFS_WORDTILE)                                     //~1118R~//~1119R~
        {                                                          //~1119I~
	        if (Dump.Y) Dump.println("RACall.callChii return word tile");//~1119I~
        	return false;                                          //~1119I~
        }                                                          //~1119I~
      if ((TestOption.option2 & TO2_CALL1ST)!=0)                   //~va75I~
      {                                                            //~va75I~
	  	if (Dump.Y) Dump.println("RACall.callChii ignore intent and fix1 by testoption TO2_CALL1ST");//~va75I~
        posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,13/*make shanten up condition*/,itsH,ctrH,pos);//~va75I~
      }                                                            //~va75I~
      else                                                         //~va75I~
      {                                                            //~va75I~
        if (RS.RSP[PeswnOther].isFixed1())     //1han already fixed                    //~1118I~//~1119R~//~1126R~//~1129R~
        {                                                      //~1118I~//~1119R~
            posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~1118R~//~1119R~//~1129R~
        }                                                      //~1118I~//~1119R~
        else                                                   //~1118I~//~1119R~
        if ((intent & INTENT_TANYAO)!=0 && RS.swKuitan && RAUtils.isTanyaoTile(pos) && RS.RSP[PeswnOther].isPairTanyaoAllOrNoPair())//~1118I~//~1119R~//~1126R~//~1217R~
        {                                                      //~1118I~//~1119R~
            posChii=selectSeqMeld(PeswnOther,true/*swTanyao*/,shanten,itsH,ctrH,pos);//~1118R~//~1119R~//~1128R~//~1129R~
            num=posChii%CTR_NUMBER_TILE;                           //~1220I~
            if (num==TN1 || num==TN7)                  //~1220R~   //~1306R~
            	posChii=-1;                                        //~1220I~
        }                                                      //~1118I~//~1119R~
        else                                                       //~1306I~
        if ((intent & INTENT_CHANTA)!=0 && RS.RSP[PeswnOther].isPairChantaAllOrNoPair())//~1306I~
        {                                                          //~1306I~
            posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~1306I~
            num=posChii%CTR_NUMBER_TILE;                           //~1306I~
            if (num!=TN1 && num!=TN7)                              //~1306I~
            	posChii=-1;                                        //~1306I~
        }                                                          //~1306I~
      }                                                            //~va75I~
        if (posChii<0)                                             //~1220I~
        {                                                          //~1220I~
            if ((intent & INTENT_SAMECOLOR_ANY)!=0)                //~1220I~
            {                                                      //~1220I~
                if (RAUtils.isMatchSameColor(true/*allow Word*/,intent,pos/CTR_NUMBER_TILE))//~1220I~
		            posChii=selectSeqMeld(PeswnOther,false/*swTanyao*/,shanten,itsH,ctrH,pos);//~1220I~
                if (Dump.Y) Dump.println("RACall.callChii chk by SAMECOLOR intent posChii="+posChii);//~1220I~
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
    //**********************************************************************************    //~1119I~//~1220R~
    //*chk Meld selectable and shantenUp                           //~1220I~
    //**********************************************************************************//~1220I~
    private int selectSeqMeld(int PeswnCaller,boolean PswTanyao,int Pshanten,int[] PitsHand,int PctrHand,int Ppos)//~1119I~//~1128R~
    {                                                              //~1119I~
        if (Dump.Y) Dump.println("RACall.selectSeqMeld swTanyao="+PswTanyao+",pos="+Ppos+",shanten="+Pshanten+",itsHand="+Utils.toString(PitsHand,9));//~1119I~//~1129R~
        int top=(Ppos/CTR_NUMBER_TILE)*CTR_NUMBER_TILE;            //~1119I~
        int end=top+CTR_NUMBER_TILE;                               //~1119I~
        int minShanten=Pshanten;                                   //~1119I~
        int ctr=1;                                                 //~1119I~
        int posMeld=-1;                                            //~1119I~
        boolean rc;                                                  //~1128I~
//      Point pt=new Point(minShanten,-posMeld);                   //~1119I~//~1128R~
        if (Ppos-2>=top && PitsHand[Ppos-2]!=0 && PitsHand[Ppos-1]!=0)  //pos is right edge of meld//~1119I~
        {                                                          //~1119I~
        	posMeld=Ppos-2;                                        //~1119I~
        	if (!PswTanyao || (posMeld>0 && (posMeld+2)<8))        //~1119I~
            {                                                      //~1119I~
                PitsHand[Ppos-2]--; PitsHand[Ppos-1]--;            //~1119I~
//              isShantenUpCall(PitsHand,PctrHand-2,pt);       //~1119I~//~1128R~
                rc=isShantenUpCall(PeswnCaller,PitsHand,PctrHand-2,Pshanten);//~1128R~
                PitsHand[Ppos-2]++; PitsHand[Ppos-1]++;            //~1119I~
                if (!rc)                                           //~1128I~
                	posMeld=-1;                                    //~1128I~
            }                                                      //~1119I~
        }                                                          //~1119I~
      if (posMeld<0)                                               //~va8sI~
        if (Ppos+2< end && PitsHand[Ppos+2]!=0 && PitsHand[Ppos+1]!=0)   //pos is left edge of meld//~1119I~
        {                                                          //~1119I~
        	posMeld=Ppos;                                          //~1119I~
        	if (!PswTanyao || (posMeld>0 && (posMeld+2)<8))        //~1119I~
            {                                                      //~1119I~
                PitsHand[Ppos+2]--; PitsHand[Ppos+1]--;            //~1119I~
                rc=isShantenUpCall(PeswnCaller,PitsHand,PctrHand-2,Pshanten);       //~1119I~//~1128R~
                PitsHand[Ppos+2]++; PitsHand[Ppos+1]++;            //~1119I~
                if (!rc)                                           //~1128I~
                	posMeld=-1;                                    //~1128I~
            }                                                      //~1119I~
        }                                                          //~1119I~
      if (posMeld<0)                                               //~va8sI~
        if (Ppos-1>=top && PitsHand[Ppos-1]!=0 && Ppos+1<end && PitsHand[Ppos+1]!=0)//~1119I~
        {                                                          //~1119I~
        	posMeld=Ppos-1;                                        //~1119I~
        	if (!PswTanyao || (posMeld>0 && (posMeld+2)<8))        //~1119I~
            {                                                      //~1119I~
                PitsHand[Ppos-1]--; PitsHand[Ppos+1]--;            //~1119I~
                rc=isShantenUpCall(PeswnCaller,PitsHand,PctrHand-2,Pshanten);       //~1119I~//~1128R~
                PitsHand[Ppos-1]++; PitsHand[Ppos+1]++;            //~1119I~
                if (!rc)                                           //~1128I~
                	posMeld=-1;                                    //~1128I~
            }                                                      //~1119I~
        }                                                          //~1119I~
//      posMeld=pt.y;                                              //~1119I~//~1128R~
        if (Dump.Y) Dump.println("RACall.selectSeqMeld posMeld="+posMeld);//~1119I~//~1129R~
        return posMeld;                                            //~1119I~//~1129R~
    }                                                              //~1119I~
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
        		tdsPair[ctrPair++]=RAUtils.selectTileInHand(PeswnPlayer,ii);//~1129I~
        }                                                          //~1129I~
        tdsPair[ctrPair++]=PtdDiscarded;                           //~1129I~
        PtdDiscarded.setTakenRiver();	//tdLastDiscarded is different instance by sendmsg//~1129I~
        if (Dump.Y) Dump.println("RACall.makeChii eswnPlayer="+PeswnPlayer+",posstart="+PposChiiStart+",posDiscarded="+PposDiscarded+",tdDiscarded="+PtdDiscarded.toString()+",tdsPair="+TileData.toString(tdsPair));//~1129I~
        return tdsPair;                                            //~1129I~
    }                                                              //~1129I~
 	//******************************************************************************//~1306I~
 	//*evaluate not timing but value of call                       //~va8pI~
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
		int ctrReach=AG.aPlayers.getCtrReachedPlayer(Pplayer);     //~1306I~
		int ctrDora=RADS.getCtrDoraInHandAndEarth(Peswn);           //~1313I~
	    int ctrTaken=RS.RSP[Peswn].ctrTaken;                       //~1306I~
        for (;;)                                                   //~1306I~
        {                                                          //~1306I~
        	int pos=RAUtils.getPosTile(Ptd);                               //~1306I~
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
                    if (Peswn==ESWN_E && PmyShanten<=HV_PARENT_1STCALL_SHANTEN)//~1306R~//~1313I~
                    {                                              //~1306R~//~1313I~
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
                }                                                  //~1313M~
            }                                                      //~1313I~
//          if (!RS.RSP[Peswn].swAllInHand                         //~1306I~//~1313M~//~va8pR~
//          ||  ctrTaken<=HV_TIME_TO_CALL) //     //<=3 save to call up to 3 tiles take//~1306R~//~1313I~//~va8pR~
            if (RS.RSP[Peswn].swAllInHand                          //~va8pI~
            &&  ctrTaken<=HV_TIME_TO_CALL) //     //<=3 save to call up to 3 tiles take//~va8pI~
            {                                                      //~1306I~//~1313M~
        		if (Dump.Y) Dump.println("RACall.isTimeToCall return FALSE by early call");//~va8pI~
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
                    }                                              //~1306I~
                }                                                  //~1306I~
            }                                                      //~1306I~
            if ((Pintent & INTENT_SAMECOLOR_ANY)!=0)                //~1306I~
            {                                                      //~1306I~
                rc=true;                                           //~1306I~
                break;                                             //~1306I~
            }                                                      //~1306I~
            break;                                                 //~1306I~
		} //for (;;)                                               //~1306I~
        if (Dump.Y) Dump.println("RACall.isTimeToCall rc="+rc+",eswn="+Peswn+",action="+Paction+",swAllInhand="+RS.RSP[Peswn].swAllInHand+",ctrTaken="+RS.RSP[Peswn].ctrTaken);//~1306I~//~va8pR~
        return rc;                                                 //~1306I~
    }                                                              //~1306I~
    //***************************************************************//~va83I~
    //*chk Human player 13NoPair/14NoPair in PlayAloneNotify mode at Taken//~va83I~
    //***************************************************************//~va83I~
    private boolean isRonNoPair(int Peswn,int PposTaken)           //~va83I~
    {                                                              //~va83I~
        if (Dump.Y) Dump.println("RACall.isRonNoPair eswn="+Peswn+",posTaken="+PposTaken);//~va83I~
    	if (!AG.aPlayers.is1stTake())                              //~va83I~
        	return false;                                          //~va83I~
		int[] itsH=RS.getItsHandEswn(Peswn);                       //~va83I~
        itsH[PposTaken]++;                                         //~va83I~
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
}//class RACall                                                    //~1117R~
