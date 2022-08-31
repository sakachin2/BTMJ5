//*CID://+vapvR~: update#= 440;                                    //~vaphR~//+vapvR~
//**********************************************************************
//2022/08/05 vapv (Bug)Furiten chk after discarded was not done    //+vapvI~
//2022/07/29 vapg need clear itshandvalue. vaiw is not enough if call swDoreach=false//~vaphI~
//2022/02/18 vak6 differenciate kataagari err  and fix err         //~vak6I~
//                fixLast:allow kataagari, else chk kataagari with chk option if not fix err//~vak6I~
//2022/02/16 vak5 with no chk kataagari,do not lit win button in notify mode for human
//2022/01/19 vaj5 Not ronable when furiten even if taken if furitenreachoption=No//~vaj5I~
//2022/01/17 vaiz (Bug)chanta 7pair,missing set INTENT_CHANT when chant pair>=5//~vaizI~
//2022/01/17 vaiw (bug)itsHandValue was not clearted before evaluateTile if SwDoReach was reset//~vaiwI~
//2022/01/15 vaiq when callChii by samecolor,additional to shantendown,chk othercolor exist to discard//~vaiqI~
//2022/01/14 vain consider orphan TN3/TN7 for INTENT_CHANTA        //~vainI~
//2021/12/22 vai6 (Bug)isDoraOpen was not evaluated kan dora.(Ron value is OK)//~vai6I~
//2021/11/18 vagy for INTENT_SAMECOLOR;take word pair only into acount//~vagyI~
//2021/11/16 vagw (Bug)Not checked as Furiten if exaused           //~vagwI~
//2021/11/15 vagt more ristriction for intent tanyao;chk honor tile pair//~vagtI~
//2021/11/14 vags more ristriction for intent chanta;count 456 tile//~vagsI~
//2021/11/12 vagh support INTENT_4ANKO                             //~vaghI~
//2021/11/10 vagb count dora of discarded at isTimeToCall          //~vagbI~
//2021/11/08 vag8 INTENT_ALLSAME;avoid when othercolor meld exist  //~vag8I~
//2021/11/08 vag7 INTENT_ALLSAME;triplet>=1 and (triplet+pair)>=4 and no seq meld//~vag7I~
//2021/11/07 vag6 (Bug)samecolor chk did not see earth             //~vag6I~
//2021/11/06 vafx for early samecolor, allow 3 shanten             //~vafxI~
//2021/11/06 vafw for early samecolor, ctrDora is to of samecolor  //~vafwI~
//2021/11/05 vafu requires ctr=3 for INTENT_3DRAGON                //~vafuI~
//2021/11/05 vaft ignore INTENT_CHANTA if INTENT_3DRAGON           //~vaftI~
//2021/11/05 vafr chkChanta;consider tanyao meld                   //~vafrI~
//2021/11/01 vafk INTENT_3SAMESEQ; 2nd call if once called according FixedFirst rule//~vafkI~
//2021/11/01 vafj INTENT_STRAIGHT; 2nd call if once called according FixedFirst rule//~vafjI~
//2021/10/28 vafg for INTENT_CHANTA evaluate also pair             //~vafgI~
//2021/10/31 vafe (Bug)For INTENT_TANYAO;reverse determination for Chanta meld count//~vafeI~
//2021/10/28 vafb evaluate INTENT_3SAMESEQ                         //~vafbI~
//2021/10/28 vafa (Bug)itsDoraOpen was not set until discard, it need to chk isTimeToCall when call Pon/Chii beore 1st discard//~vafaI~
//2021/10/27 vaf9 evaluate INTENT_STRAIGHT                         //~vaf9I~
//2021/07/29 vabk no select INTENT_7PAIR if 2 seq meld exist       //~vabkR~
//2021/07/29 vabj select AllSame over to 7Pair                     //~vaajI~
//2021/07/28 vabe no set intent=TANYAO when a chanta meld exist    //~vaaeI~
//2021/07/28 vabd (Bug)chanta decision missing chk Earth           //~vaadI~
//2021/07/27 vabc defuse decision of chanta                        //~vaacI~
//2021/07/15 vaaL do not discard Dora at Chii. selectmeld checks dora considering tanyao/chanta//~vaaLI~
//2021/07/12 vaaD avoid kuikae when selectdiscard failed           //~vaaDI~
//2021/07/11 vaaC set intent tanyao/chanta/samecolor for also 7pair//~vaaCI~
//2021/07/11 vaaB skip reach if yakuman                            //~vaaBI~
//2021/07/09 vaaw (Bug)Miss to set ALLSAME(INTENT_TREND_SAME is similar flag, us ALLSAME only)//~vaawI~
//2021/07/05 vaaq (Bug)doraOpen is not set when dealers double reach//~vaaqI~
//2021/07/05 vaam (Bug)miss to set tanyai if shanten<2             //~vaamI~
//2021/07/03 vaai strengthen robot; call pon/chii if become shanten 0 with a Yaku+dora>=2//~vaaiI~
//2021/06/28 vaa4 (Bug)for 13/14 broken(at 1st take), Robot ronvalue=Tenho. Not harmfull because CompReqDlg addYaku 13 broken at Point button.//~vaa4I~
//2021/06/14 va96 When win button pushed in Match mode, issue warning for not ronable hand.//~va96I~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/05/01 va8x (Test)specify robot discard tile                 //~va8xI~
//2021/04/20 va8i KataAgari chk for also Robot Take                //~va8iI~
//2021/04/18 va8g intent 3dragon,allow 2+2+1                       //~va8gI~
//2021/04/18 va8f KataAgari chk                                    //~va8fI~
//2021/04/15 va8a (Bug) seting of intent 3dragon(loose on earth)   //~va8aI~
//2021/04/13 va84 try Robot also ron by 13/14 NoPair               //~va84I~
//2021/04/05 va79 (Bug)hung when ron at take without ronable       //~va79I~
//2021/01/07 va60 CalcShanten
//**********************************************************************
package com.btmtest.game.RA;
import android.graphics.Point;

import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.game.Accounts;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.RA.RARon.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UA.Rank.*;

//********************************************************************************************
public class RADSmart
{
	private RoundStat RS;
	private RoundStat.RSPlayer RSP;
	private RADiscard RAD;
	private RADSEval RADSE;
	public  RADSOther RADSO;
    public  TileData[] tdsHand;
    private int ctrHand;
    private int[] itsHand,itsExposed;	//34 entry
    private int[] itsHandWork=new int[CTR_TILETYPE];	//34 entry //~vabkI~
//  private boolean[] btsNonDiscardable=new boolean[CTR_TILETYPE];	//34 entry//~1121R~
    public  int[] itsStatHand,itsStatPair;                         //~1121R~
    public  int[] itsHandValue=new int[HANDCTR_TAKEN];
//    private int[] itsPosShanten0Discard= new int[HANDCTR_TAKEN];
//    private int[] itsHanShanten0Discard= new int[HANDCTR_TAKEN];
//    private int ctrPosShanten0Discard;
    private int[] itsHandNumberStat=new int[HANDCTR_TAKEN];
    private int[] itsMinShantenTile=new int[HANDCTR_TAKEN];
    public  int[] itsHandPos=new int[HANDCTR_TAKEN];               //~1122R~
    private int[] itsReachValue=new int[HANDCTR_TAKEN];            //~1122I~
//  private int myCtrPair,myStatPair;                              //~1121R~
    public  int playerDiscard;                                     //~1121R~
    private int eswnDiscard,posTaken;
    public  int[] itsDoraOpen=new int[2*(1+MAXCTR_KAN)];           //~1217R~
    private boolean swDoraOpened;                                          //~vafaI~
    public  int   ctrDoraOpen;                                     //~1215I~//~1217R~
    public  int myIntent;
    public  int myShanten;                                         //~1201R~
//  private int ctrMan,ctrPin,ctrSou,ctrWord,ctrAll;               //~1215R~
    private boolean[] btsWin=new boolean[CTR_TILETYPE];  //evaluate wait expecting value
    private boolean[] btsWinMerge=new boolean[CTR_TILETYPE];  //evaluate wait expecting value
    public	boolean swDoReach;                                     //~1122I~
    public	int hanMaxReach;                                       //~1122I~
    private boolean swDiscardedSmart;                              //~1201I~
    private int[] wk3SameSeq=new int[CTR_NUMBER_TILE];             //~vafbI~
    private boolean swCheckMultiWait;                              //~vak5I~
//*************************
	public RADSmart()
    {
        if (Dump.Y) Dump.println("RADSmart.Constructor");
        AG.aRADSmart=this;
        RADSO=new RADSOther();                                     //~1122M~
        RADSE=new RADSEval();
        init();
    }
    //*********************************************************
    private void  init()
    {
    	RAD=AG.aRADiscard;
    	RS=AG.aRoundStat;
        swCheckMultiWait= RuleSettingOperation.isCheckMultiWait();  //~vak5I~
    }
    //*********************************************************    //~1201I~
    //*from RACAll at Robot itself discard done                                  //~1201I~//~1205R~//~va84R~
    //*set currentShanten and Intent                               //~1201I~
    //*********************************************************    //~1201I~
    public void discardedSmart(int Peswn)                          //~1201I~
    {                                                              //~1201I~
        if (Dump.Y) Dump.println("RADSmart.discardedSmart eswn="+Peswn);//~1201I~
        swDiscardedSmart=true;                                     //~1201I~
    	selectSmart(Peswn,null);                                   //~1201I~
        swDiscardedSmart=false;                                    //~1201I~
        if (Dump.Y) Dump.println("RADSmart.return");               //~1201I~
    }                                                              //~1201I~
    //*********************************************************
    //*from RADiscard
    //*13/14-NoPair	Robot pass it                                  //~1124I~
    //*99TileDraw  Robot pass it                                   //~1124I~
    //*PtdTaken==null when discard at Pon/Chii                     //~1126I~
    //*********************************************************
    public  TileData selectSmart(int Peswn,TileData PtdTaken)
    {
        TileData tdDiscard=null;
        //********************
        if (Dump.Y) Dump.println("RADSmart.selectSmart eswn="+Peswn+",PtdTaken="+Utils.toString(PtdTaken)+",swDiscardedSmart="+swDiscardedSmart);//~1126I~//~1205R~
        eswnDiscard=Peswn;
        RSP=RS.RSP[Peswn];
//      if ((RSP.callStatus & CALLSTAT_REACH)!=0) //already reach issued//~1219I~//~1221R~
//      {                                                          //~1219I~//~1221R~
//          if (Dump.Y) Dump.println("RADSmart.selectSmart after reach eswn="+Peswn+",PtdTaken="+Utils.toString(PtdTaken));//~1219I~//~1221R~
//      	return PtdTaken;                                       //~1219I~//~1221R~
//      }                                                          //~1219I~//~1221R~
//      playerDiscard=RAD.playerDiscard;                           //~1201R~
        playerDiscard= Accounts.eswnToPlayer(Peswn);               //~1201I~
//      itsHand=RAD.itsHand;	//34 entry                         //~1201R~
        itsHand=RS.getItsHandEswn(Peswn);   //including taken      //~1201I~
//      tdsHand=RAD.tdsHand;    //max 14                           //~1201R~
        tdsHand=AG.aPlayers.getHands(playerDiscard);               //~1201I~
        ctrHand=RAUtils.getTilesPos(tdsHand,itsHandPos);	//max 14 entry pos of hand//~1122I~
        itsExposed=RS.itsExposed;
        if (swDiscardedSmart)                                      //~1201I~
        {                                                          //~1201I~
        	myShanten=getShanten(true/*swIntent*/,ctrHand);	//save myIntent to RSP//~1201I~
    		RS.setCurrentShanten(Peswn,myShanten);                 //~1201I~
        }                                                          //~1201I~
        else                                                       //~1201I~
        {                                                          //~1201I~
	        if ((TestOption.option3 & TO3_ROBOT_DISCARD_TILE)!=0)  //~va8xI~
    	    {                                                      //~va8xI~
        		tdDiscard=selectTestDiscard(playerDiscard);        //~va8xI~
        	}                                                      //~va8xI~
          if (tdDiscard==null)    //not set by test option                 //~va8xI~
          {                                                        //~va8xI~
            Arrays.fill(itsHandValue,DV_BASE);                         //~1122M~//~1201R~
            if (PtdTaken==null)                                        //~1126I~//~1201R~
                tdDiscard=selectSmartPonChii(Peswn);                   //~1126I~//~1201R~
            else                                                       //~1126I~//~1201R~
                tdDiscard=selectSmartTaken(Peswn,PtdTaken);            //~1126I~//~1201R~
            if (Dump.Y) Dump.println("RADSmart.selectSmart tdDiscard="+TileData.toString(tdDiscard));//~1126R~//~1201R~
          }                                                        //~va8xI~
        }                                                          //~1201I~
        itsHand=null;	//for gc                                   //~1126I~
        tdsHand=null;                                              //~1126I~
        itsExposed=null;                                           //~1126I~
        return tdDiscard;
    }
    //*********************************************************    //~1305I~
    private int getIdxHandPos(int Ppos)                            //~1305I~
    {                                                              //~1305I~
        int idx=-1;                                                //~1305I~
        for (int ii=0;ii<ctrHand;ii++)                             //~1305I~
        {                                                          //~1305I~
        	if (Ppos==itsHandPos[ii])                               //~1305I~
            {                                                      //~1305I~
  				idx=ii;                                            //~1305I~
                break;                                             //~1305I~
            }                                                      //~1305I~
        }                                                          //~1305I~
        if (Dump.Y) Dump.println("RADSmart.getIdxHandPos pos="+Ppos+",idx="+idx+",itsHandPos="+Utils.toStringMax(itsHandPos,ctrHand));//~1305I~
        return idx;
    }                                                              //~1305I~
    //*********************************************************    //~1126I~
    private TileData selectSmartTaken(int Peswn,TileData PtdTaken) //~1126R~
    {                                                              //~1126I~
        TileData tdDiscard=null;                                   //~1126I~
        //********************                                     //~1126I~
        if (Dump.Y) Dump.println("RADSmart.selectSmartTaken eswn="+Peswn+",PtdTaken="+Utils.toString(PtdTaken));//~1126I~
        swDoReach=false;                                           //~1126I~
        posTaken=RAUtils.getPosTile(PtdTaken);                     //~1126I~
        if ((RSP.callStatus & CALLSTAT_REACH)!=0) //reach called   //~1126I~
        {                                                          //~1126I~
        	myShanten=getShanten(false/*swIntent*/,ctrHand);  //set also myintent//~1206I~
            if (myShanten==-1)	//                                 //~1206I~
            {                                                      //~1206I~
//        		if (callRonTaken(itsHand,PtdTaken))                //~1206I~//~va8iR~
          		if (callRonTaken(itsHand,ctrHand,PtdTaken))        //~va8iI~
            	{                                                  //~1206I~
		        	if (Dump.Y) Dump.println("RADSmart.selectSmartTaken under reach return null after Ron issued tdTaken="+PtdTaken.toString());//~1206I~//~va79R~
            		return null;                                   //~1206I~
            	}                                                  //~1206I~
            }                                                      //~1206I~
	        if (callKanTaken(true/*reach*/,PtdTaken,posTaken))     //~1126I~
            {                                                      //~1126I~
		        if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return KanTaken under reach to take rinshan tdTaken="+PtdTaken.toString());//~1126I~
                return null;                                       //~1126I~
            }                                                      //~1126I~
		    if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return taken because after reach tdDiscard="+PtdTaken.toString());//~1126I~//~1218R~
            return PtdTaken;                                   //~1126I~//~1218R~
        }                                                          //~1126I~
//      getDoraOpen();                                             //~vaphR~
        myShanten=getShanten(true/*swIntent*/,ctrHand);	//output also myIntent;//~1201R~
        if (myShanten!=-1)                                         //~va84I~
        {                                                          //~va84I~
        	if (AG.aRACall.isRonNoPairRobot(Peswn,posTaken))  //chk 13/14 NoPair//~va84I~
            {                                                      //~vaa4I~
//      		myShanten=-1;                                     //~va84I~//~vaa4R~
        		callRonTakenNoPair(itsHand,ctrHand,PtdTaken);      //~vaa4I~
		        if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return null after Ron issued by NoPair at 1st Take(13/14 broken)");//~vaa4I~
            	return null;                                       //~vaa4I~
            }                                                      //~vaa4I~
        }                                                          //~va84I~
        if (myShanten==-1)                                         //~1126I~
        {                                                          //~1126I~
//      	if (callRonTaken(itsHand,PtdTaken))                    //~1126I~//~va8iR~
        	if (callRonTaken(itsHand,ctrHand,PtdTaken))            //~va8iI~
            {                                                      //~1126I~
		        if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return null after Ron issued tdTaken="+PtdTaken.toString());//~1126I~//~1405R~
            	return null;                                       //~1126I~
            }                                                      //~1126I~
        }                                                          //~1126I~
//      if (callKanTaken(false/*reach*/,PtdTaken,posTaken))        //~1126I~//~1222R~
//      {                                                          //~1126I~//~1222R~
//  		if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return by KanTaken not under Reach to take Rinshan tdTaken="+PtdTaken.toString());//~1126I~//~1222R~
//          return null;                                           //~1126I~//~1222R~
//      }                                                          //~1126I~//~1222R~
      if ((TestOption.option3 & TO3_NOREACH)!=0)                   //~va84I~
      {                                                            //~va84I~
  		if (Dump.Y) Dump.println("RADSmart.selectSmartTaken@@@@ skip reach by test option");//~va84I~
      }                                                            //~va84I~
      else                                                         //~va84I~
	    if (myShanten==0 && (RSP.callStatus & CALLSTAT_REACH)==0) //reach not called//~1126I~
    	    swDoReach=callReach();  //not skip reach               //~1126I~//~1309R~
     	if (swDoReach)                                             //~1309I~
        {
            tdDiscard = selectDiscardReach(myShanten);    //chk otherPlayer//~1309R~
            if (tdDiscard==null)                                   //~1309I~
            {                                                      //~vaiwI~
            	swDoReach=false;                                   //~1309R~
//          	Arrays.fill(itsHandValue,DV_BASE); //avoid duplicated chkOther value by selectDiscardReach//~vaphR~
            }                                                      //~vaiwI~
        }
      if (!swDoReach)                                         //~1309I~
      {                                                            //~vaphI~
        Arrays.fill(itsHandValue,DV_BASE); //reset itsHandValue by callReach()//~vaphI~
	    tdDiscard=selectDiscard(myShanten);                                   //~1126I~//~1127R~//~1201R~
      }                                                            //~vaphI~
        if (tdDiscard==null)                                       //~1126I~
        	tdDiscard=PtdTaken;                                    //~1126I~
        if (callKanTaken(false/*reach*/,PtdTaken,posTaken))  //not take but discard timing for selected tile//~1222I~
        {                                                          //~1222I~
    		if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return by KanTaken not under Reach to take Rinshan tdTaken="+PtdTaken.toString());//~1222I~
            return null;                                           //~1222I~
        }                                                          //~1222I~
    	if (Dump.Y) Dump.println("RADSmart.selectSmartTaken swDoReach="+swDoReach+",hanMAxReach="+hanMaxReach);//~vaaBI~
        if (swDoReach)                                             //~1126I~
          if (hanMaxReach<MIN_RANK_YAKUMAN)                        //~vaaBI~
            callReach(tdDiscard);	//issue Reach                  //~1126I~
        if (Dump.Y) Dump.println("RADSmart.selectSmartTaken tdDiscard="+tdDiscard.toString());//~1126I~
        return tdDiscard;                                          //~1126I~
    }                                                              //~1126I~
    //*********************************************************    //~1126I~
    private TileData selectSmartPonChii(int Peswn)                 //~1126I~
    {                                                              //~1126I~
        TileData tdDiscard=null;                                   //~1126I~
        //********************                                     //~1126I~
        if (Dump.Y) Dump.println("RADSmart.selectSmartPonChii eswn="+Peswn);//~1126R~
        swDoReach=false;                                           //~1126I~
        posTaken=RAUtils.getPosTile(tdsHand[0]);    //may not used //~1126R~
        myShanten=getShanten(true/*swIntent*/,ctrHand);  //set also myintent          //~1126I~//~1201R~
     int idxOK=                                                              //~1126I~//~vaaDR~
        setNonDiscardablePonChii();	//kuikae                       //~1305I~
	    tdDiscard=selectDiscard(myShanten);                          //~1126I~//~1127R~//~1201R~
                                                                   //~1126I~
        if (tdDiscard==null)                                       //~1126I~
//      	tdDiscard=tdsHand[0];                                  //~1126I~//~vaaDR~
        	tdDiscard=tdsHand[idxOK];   //not violation of kuikae  //~vaaDI~
        if (Dump.Y) Dump.println("RADSmart.selectSmartPonChii tdDiscard="+tdDiscard.toString());//~1126I~//~1213R~
        return tdDiscard;                                          //~1126I~
    }                                                              //~1126I~
    //***********************************************************************
    private boolean callKanTaken(boolean PswReach,TileData PtdTaken,int PposTaken)
    {
        if (Dump.Y) Dump.println("RADSmart.callKanTaken swReach="+PswReach);//~1124R~
        int posKan=AG.aRACall.callKanTaken(playerDiscard,eswnDiscard,myShanten,PswReach,PtdTaken,PposTaken,itsHand,ctrHand);//~1122R~//~1124R~
        boolean rc=(posKan>=0);                                    //~1124I~
//        if (rc)                //decrease at GCM_KAN msg         //~1124R~
//        {                                                        //~1124R~
//            itsHand[posKan]=0;                                   //~1124R~
//            ctrHand-=PIECE_DUPCTR;                               //~1124R~
//        }                                                        //~1124R~
        if (Dump.Y) Dump.println("RADSmart.callKanTaken rc="+rc+",posKan="+posKan+",ctrHand="+ctrHand+",itsHand="+Utils.toString(itsHand,9));//~1124R~
        return rc;
    }
    //***********************************************************************
    //*evaluate reachPos                                           //~1222I~
    //***********************************************************************//~1222I~
    private boolean callReach()
    {
        if (Dump.Y) Dump.println("RADSmart.callReach entry itsHandValue="+Utils.toString(itsHandValue));//~vak6R~
        getDoraOpen();                                             //~vaphI~
        int hanMaxMax=AG.aRAReach.callReach(playerDiscard,eswnDiscard,posTaken,itsHand,itsHandPos,ctrHand,itsHandValue);//~1122R~
        boolean rc=hanMaxMax!=0;    //not skipReach                //~1309R~
        hanMaxReach=hanMaxMax;                                     //~1122I~
        if (Dump.Y) Dump.println("RADSmart.callReach return rc="+rc);//~1122R~
        return rc;
    }
    //***********************************************************************//~1122I~
    //*issueReach                                                  //~1222I~
    //***********************************************************************//~1222I~
    private boolean callReach(TileData PtdDiscard)                 //~1122I~
    {                                                              //~1122I~
        if (Dump.Y) Dump.println("RADSmart.callReach tdDiscard="+TileData.toString(PtdDiscard));//~1122I~
        boolean rc=AG.aRAReach.callReach(playerDiscard,eswnDiscard,PtdDiscard);//~1122I~
        if (Dump.Y) Dump.println("RADSmart.callReach rc="+rc);     //~1122I~
        return rc;                                          //~1122I~
    }                                                              //~1122I~
    //***********************************************************************
//  private boolean callRonTaken(int[] PitsHand,TileData PtdTaken) //~va8iR~
    private boolean callRonTaken(int[] PitsHand,int PctrHand,TileData PtdTaken)//~va8iI~
    {
        if (Dump.Y) Dump.println("RADSmart.callRonTaken tdTaken="+PtdTaken.toString());
//      AG.aRARon.callRonTaken(playerDiscard,eswnDiscard,PitsHand,PtdTaken);//~va79R~
//      return true;                                               //~va79R~
//      boolean rc=AG.aRARon.callRonTaken(playerDiscard,eswnDiscard,PitsHand,PtdTaken);//~va79R~//~va8iR~
        boolean rc=AG.aRARon.callRonTaken(playerDiscard,eswnDiscard,PitsHand,PctrHand,PtdTaken);//~va8iI~
        if (Dump.Y) Dump.println("RADSmart.callRonTaken rc="+rc);  //~va79I~
        return rc;                                                 //~va79R~
    }
    //***********************************************************************//~vaa4I~
    //*13/14 broken Ron of Robot                                   //~vaa4I~
    //***********************************************************************//~vaa4I~
    private boolean callRonTakenNoPair(int[] PitsHand,int PctrHand,TileData PtdTaken)//~vaa4I~
    {                                                              //~vaa4I~
        if (Dump.Y) Dump.println("RADSmart.callRonTakenNoPair tdTaken="+PtdTaken.toString());//~vaa4I~
        boolean rc=AG.aRARon.callRonTakenNoPair(playerDiscard,eswnDiscard,PitsHand,PctrHand,PtdTaken);	//issueRon//~vaa4I~
        if (Dump.Y) Dump.println("RADSmart.callRonTakenNoPair rc="+rc);//~vaa4I~
        return rc;                                                 //~vaa4I~
    }                                                              //~vaa4I~
    //***********************************************************************//~vaaiI~
    //*from RoundStat.deal()                                       //~vaaiI~
    //***********************************************************************//~vaaiI~
    public int getShantenDeal(int Pplayer,int Peswn,int[] PitsHand,int PctrHand)//~vaaiI~
    {                                                              //~vaaiI~
        if (Dump.Y) Dump.println("RADSmart.getShantenDeal player="+Pplayer+",eswn="+Peswn+",PctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaaiI~
        eswnDiscard=Peswn;                                         //~vaaiI~//~vaamR~//~vaaqM~
        RSP=RS.RSP[Peswn];                                         //~vaamI~//~vaaqM~
        playerDiscard=Pplayer;                                     //~vaaqI~
        itsHand=PitsHand;                                          //~vaaiI~
        tdsHand=AG.aPlayers.getHands(playerDiscard);               //~vaaqI~
        if (Dump.Y) Dump.println("RADSmart.getShantenDeal tdsHand="+TileData.toString(tdsHand));//~vaaqI~
        ctrHand=PctrHand;                                          //~vaaqI~
        itsExposed=RS.itsExposed;                                  //~vaaqI~
    	int shanten=getShanten(true/*PswSetIntent*/,PctrHand);  //~vaaiI~
        if (Dump.Y) Dump.println("RADSmart.getShantenDeal shanten="+shanten+",intent="+Integer.toHexString(myIntent));//~vaaiI~
        return shanten;                                            //~vaaiI~
    }                                                              //~vaaiI~
    //***********************************************************************
    public int getShanten(boolean PswSetIntent,int PctrHand)       //~1121R~
    {
        if (Dump.Y) Dump.println("RADSmart.getShanten entry swSetIntent="+PswSetIntent+",eswnDiscard="+eswnDiscard+",PctrHand="+PctrHand);//~1213R~//~1217R~//~vaghR~
    	int flag_shanten=AG.aShanten.getShantenMinFlag(itsHand,PctrHand);	//standard,13orphan,7pair//~1121R~
        int shanten=flag_shanten & 0xff;                           //~1121I~
        if (shanten==0xff)                                         //~1205I~
        	shanten=-1;                                            //~1205I~
        int flag=flag_shanten >>8;                                 //~1121I~
        if (PswSetIntent)                                          //~1121R~
        {                                                          //~1121I~
        	int intent=RSP.getIntent();                            //~1213I~
            if (PctrHand>=HANDCTR)  //swAllInHand is true for Ankan(ctrHand<HANDCTR),but invalid for 13orphan/7pair//~1219R~
            {                                                      //~1213I~
                if ((flag & SHANTEN_STANDARD)!=0)                   //~1215R~
                {                                                  //~vabkR~
	                intent&=~(INTENT_13ORPHAN | INTENT_7PAIR);     //~1215I~
        			if (Dump.Y) Dump.println("RADSmart.getShanten @@@@ select SHANTEN_STANDARD eswn="+eswnDiscard);//~vabkR~
                }                                                  //~vabkR~
                else                                               //~1215I~
                if ((intent & (INTENT_13ORPHAN | INTENT_7PAIR))==0)//~1213I~
                {                                                  //~1213R~
                    if ((flag & SHANTEN_13ORPHAN)!=0 && shanten<=HV_SET_INTENT_MAX_SHANTEN_13ORPHAN) //<=4                      //~1121I~//~1213R~//~1214R~//~1219R~//~1302R~
                    {                                                      //~1121I~//~1213R~
                        if (RSP.ctrTaken <=HV_SET_INTENT_TAKE_13ORPHAM) //select 13 orphan at 1st take//~1121I~//~1213R~
                        	if ((intent & INTENT_7PAIR)==0)    //~1213R~//~1214R~
                            {                                      //~1214I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~
                                    UView.showToastLong("RADSmart Set intent 13ORPHAN eswn="+eswnDiscard);//~1214I~//~1220R~//~1223R~
        						if (Dump.Y) Dump.println("RADSmart.getShanten @@@@ set intent 13orphan eswn="+eswnDiscard);//~1220I~
                            	intent|=INTENT_13ORPHAN;        //~1121R~//~1213R~//~1214R~
                            }                                      //~1214I~
                    }                                                      //~1121I~//~1213R~
                    if ((flag & SHANTEN_7PAIR)!=0 && shanten<HV_SET_INTENT_MAX_SHANTEN_7PAIR) //<3 shanten           //~1121I~//~1213I~//~1214R~//~1217R~//~1223R~
                    {                                                      //~1121I~//~1213I~
                        if (RSP.ctrTaken<=HV_SET_INTENT_TAKE_7PAIR     //select 7pair before 4 take//~1121I~//~1213I~//~1217R~
                        ||  shanten<=HV_SET_INTENT_MAX_SHANTEN_7PAIR_ANYTIME)	//set intent anytime if shanten<=1//~1217I~
                        	if ((intent & INTENT_13ORPHAN)==0) //~1213I~//~1214R~
                            {                                      //~1214I~
                              if (!chkSeqMeld(itsHand))            //~vabkR~
                              {                                    //~vabkR~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~
                                    UView.showToastLong("RADSmart Set intent 7PAIR eswn="+eswnDiscard);//~1214I~//~1220R~//~1223R~
        						if (Dump.Y) Dump.println("RADSmart.getShanten @@@@ set intent 7Pair eswn="+eswnDiscard);//~1220I~
	                        	intent|=INTENT_7PAIR;                          //~1121I~//~1213I~//~1214R~
                              }                                    //~vabkR~
                            }                                      //~1214I~
                    }                                                      //~1121I~//~1213I~
                }                                                  //~1213I~
            }                                                      //~1213I~
            else                                                   //~1213I~
                intent&=~(INTENT_13ORPHAN | INTENT_7PAIR);         //~1213I~
        	RSP.setIntent(intent);  //for chkstatuistic            //~1216I~
//      	RSP.intent|=intent;                              //~1121R~//~1201R~
//  	    if ( (intent & (INTENT_7PAIR | INTENT_13ORPHAN))==0)   //~1213I~//~1224R~
//  		    intent|=chkStatistic();    //may be TREND_ALLSAME      //~1201I~//~1213R~//~1215R~
//  		    intent=chkStatistic(shanten);    //may be TREND_ALLSAME   //~1215I~//~1217R~//~1224R~
    		intent=chkStatistic(intent,shanten);    //may be TREND_ALLSAME//~1224I~
            myIntent=intent;                                       //~1201R~
        	RSP.setIntent(myIntent);                               //~1201I~
        }                                                          //~1121I~
        if (Dump.Y) Dump.println("RADSmart.getShanten exit swSetIntent="+PswSetIntent+",shanten="+shanten+",ctrTaken="+RSP.ctrTaken+",intent="+Integer.toHexString(myIntent));//~1121R~//~1201R~//~1214R~
        return shanten;
    }
    //***********************************************************************
    private TileData selectDiscard(int PmyShanten)                   //~1127R~//~1201R~
    {
    	TileData tdDiscard;
        //**************************
        if (Dump.Y) Dump.println("RADSmart.selectDiscard swDoReach="+swDoReach+",PmyShanten="+PmyShanten);        //~1201R~//~1309R~//~vak6R~
        if (Dump.Y) Dump.println("RADSmart.selectDiscard entry itsHandValue="+Utils.toString(itsHandValue));//~vak6I~
        setNonDiscardable();	//pao and no tile
//      getStatistic();    //suit,19ji                             //~1201R~
        getDoraOpen();
        RADSE.evaluateHand(PmyShanten,eswnDiscard,itsHand,ctrHand,itsHandValue);//~1121R~//~1127R~
//      chkSameColor();
//      chk13Orphan();
//        chkOtherPlayer();
//        chkRonValue();
        tdDiscard=selectTile();
        return tdDiscard;
    }
    //***********************************************************************//~1309I~
    private TileData selectDiscardReach(int PmyShanten)            //~1309R~
    {                                                              //~1309I~
    	TileData tdDiscard;                                        //~1309I~
        int posOld,posNew;                                          //~1309I~
        //**************************                               //~1309I~
        if (Dump.Y) Dump.println("RADSmart.selectDiscardReach swDoReach="+swDoReach);//~1309I~//~vaiqR~
        tdDiscard=selectTile();                                    //~1309I~
        if (tdDiscard==null)                                       //~1309I~
        	return null;                                          //~1309I~
        posOld=RAUtils.getPosTile(tdDiscard);                      //~1309I~
        int hanMax=hanMaxReach;                                    //~1309I~
        int maxWinTile=AG.aRAReach.ctrWinTotal;                    //~1309I~
        RADSO.chkOtherPlayer(PmyShanten,hanMax,maxWinTile,eswnDiscard,itsHand,ctrHand);//~1309I~
        tdDiscard=selectTile();                                    //~1309I~
        if (tdDiscard==null)                                       //~1309I~
        	return null;                                          //~1309I~
        posNew=RAUtils.getPosTile(tdDiscard);                      //~1309I~
        if (posOld!=posNew)	//chkOther not changed discard tile    //~1309R~
        {                                                          //~1309I~
        	tdDiscard=null;                                        //+1309I~             //~1309I~
        }                                                          //~1309I~
        if (Dump.Y) Dump.println("RADSmart.selectDiscardReach rc="+TileData.toString(tdDiscard)+",swDoReach="+swDoReach+",posOld="+posOld+",posNew="+posNew);//~1309R~
        return tdDiscard;                                          //~1309R~
    }                                                              //~1309I~
    //***********************************************************************
    //*set non discardable tbl
    //***********************************************************************
    private void setNonDiscardable()
    {
        if (Dump.Y) Dump.println("RADSmart.setNonDiscardable itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1121R~//~1125R~//~1305R~
//      Arrays.fill(btsNonDiscardable,false);                      //~1121R~
        for (int ii=0;ii<ctrHand;ii++)
        {
        	int pos=RAUtils.getPosTile(tdsHand[ii]);
        	if (!RAD.isDiscardable(eswnDiscard,pos))
            {                                                      //~1121I~
  				itsHandValue[ii]=DV_NOTDISCARDABLE;                //~1121R~
//          	btsNonDiscardable[pos]=true;                       //~1121R~
        		if (Dump.Y) Dump.println("RADSmart.setNonDiscardable itsHandValue["+ii+"]="+itsHandValue[ii]);//~1220I~
            }                                                      //~1121I~
        }
    }
    //***********************************************************************//~1305I~
    //*chk kuikae                                                  //~1305I~
    //***********************************************************************//~1305I~
//  private void setNonDiscardablePonChii()                        //~1305I~//~vaaDR~
    private int  setNonDiscardablePonChii()                        //~vaaDI~
    {                                                              //~1305I~
        int idxOK=0;                                               //~vaaDI~
        if (Dump.Y) Dump.println("RADSmart.setNonDiscardablePonChii itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1305I~
        int[] posNotDiscardable=new int[2];                        //~1305I~
        int ctr=AG.aUserAction.UAD.getSameMeldTile(playerDiscard,posNotDiscardable);//~1305I~
        for (int ii=0;ii<ctrHand;ii++)                             //~1305I~
        {                                                          //~1305I~
        	int pos=itsHandPos[ii];                                //~1305I~
            for (int jj=0;jj<ctr;jj++)                             //~1305I~
            {                                                      //~1305I~
                if (pos==posNotDiscardable[jj])                    //~1305I~
                {                                                  //~1305I~
                	itsHandValue[ii]=DV_NOTDISCARDABLE;            //~1305I~
                	if (Dump.Y) Dump.println("RADSmart.setNonDiscardable itsHandValue["+ii+"]="+itsHandValue[ii]);//~1305I~
                }                                                  //~1305I~
                else                                               //~vaaDI~
                	idxOK=ii;                                      //~vaaDI~
            }                                                      //~1305I~
        }                                                          //~1305I~
        if (Dump.Y) Dump.println("RADSmart.setNonDiscardablePonChii idxOK="+idxOK+",itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1305I~//~vaaDR~
        return idxOK;                                              //~vaaDI~
    }                                                              //~1305I~
//    //***********************************************************************//~1201R~
//    private void getStatistic()                                  //~1201R~
//    {                                                            //~1201R~
//        if (Dump.Y) Dump.println("RADSmart.getStatistic");       //~1201R~
//        itsStatHand=RS.RSP[eswnDiscard].getHandStatistic();      //~1201R~
//        myIntent=getIntent(intentByShanten);                                       //~1131R~//~1201R~
//        Point p=new Point();                                       //~1121R~//~1122R~//~1201R~
//        itsStatPair=RS.RSP[eswnDiscard].getPairStatistic(null/*p:ctr and status summary*/);//~1121R~//~1201R~
//        int ctrPair=p.x;                                             //~1121R~//~1122R~//~1201R~
//        int stat=p.y;                                            //~1121R~//~1122R~//~1201R~
//        int ctrPairSame=(stat & PS_ALLSAME)!=0 ? ctrPair : 0;      //~1122I~//~1201R~
//        int ctrSame=ctrPairSame+itsStatHand[CSI_PON]+itsStatHand[CSI_KAN];//~1122R~//~1201R~
//        if (ctrSame>=HV_TREND_SAME)                              //~1201R~
//            myIntent|=INTENT_TREND_SAME;                         //~1201R~
//        RS.RSP[eswnDiscard].setIntent(myIntent);                   //~1131I~//~1201R~
//    }                                                            //~1201R~
    //***********************************************************************//~1201I~
    //*return intent by statistic                                  //~1201I~
    //***********************************************************************//~1201I~
    private int chkStatistic(int Pintent,int Pshanten)                                     //~1201I~//~1217R~//~1224R~
    {                                                              //~1201I~
        int intent=Pintent;                                        //~1224R~
        if (Dump.Y) Dump.println("RADSmart.chkStatistic shanten="+Pshanten+",intent="+Integer.toHexString(Pintent));         //~1201I~//~1217R~//~1224R~
        itsStatHand=RS.RSP[eswnDiscard].getHandStatistic();        //~1201I~
        Point p=new Point();                                       //~1201I~
        itsStatPair=RS.RSP[eswnDiscard].getPairStatistic(null/*p:ctr and status summary*/);//~1201I~
        int ctrPair=p.x;                                           //~1201I~
        int stat=p.y;                                              //~1201I~
        int ctrDiscard=RS.RSP[eswnDiscard].ctrDiscarded;             //~1217I~
        if (ctrDiscard>=HV_DISCARD_GIVEUP && Pshanten>=HV_SHANTEN_GIVEUP)//~1217I~
//          ctrDiscard>=15                && Pshanten>=2                )//~1218I~//~1222R~//~1302I~
        {                                                          //~1302I~
            intent|=INTENT_GIVEUP;                                 //~1217I~
        	if (Dump.Y) Dump.println("RADSmart.chkStatistic giveup by discard="+ctrDiscard+",shanten="+Pshanten);//~1302I~
        }                                                          //~1302I~
        else                                                       //~1217I~
        if (ctrDiscard>=HV_DISCARD_GIVEUP_WEAK && Pshanten>=HV_SHANTEN_GIVEUP_WEAK)//14 & 2 //~1217I~
        {                                                          //~1302I~
//         (ctrDiscard>=12                     && Pshanten>=2                     )//~1218I~
            intent|=INTENT_GIVEUP_WEAK;                            //~1217I~
        	if (Dump.Y) Dump.println("RADSmart.chkStatistic giveup weak by discard="+ctrDiscard+",shanten="+Pshanten);//~1302I~
        }                                                          //~1302I~
                                                                   //~1217I~
        if ((intent & (INTENT_GIVEUP_WEAK|INTENT_GIVEUP))==0)       //~1217I~
        {                                                          //~1217I~
    	    if ((Pintent & INTENT_13ORPHAN)==0)                    //~1224I~
            {                                                      //~1224I~
                int ctrPairSame=(stat & PS_ALLSAME)!=0 ? ctrPair : 0;      //~1201I~//~1217M~//~1224R~
                int ctrSame=ctrPairSame+itsStatHand[CSI_PON]+itsStatHand[CSI_KAN];//~1201I~//~1217I~//~1224R~
                int ctrPairInHand=itsStatHand[CSI_PAIR];                 //~1224I~
                if (ctrSame>=HV_TREND_SAME_SAME && ctrPairInHand>=HV_TREND_SAME_PAIR) //>=2 and >=3//~1224R~
                {                                                          //~1214I~//~1217I~//~1224R~
                                        if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~//~1217I~//~1224R~
                                            UView.showToastLong("RADSmart Set intent TREND_SAME eswn="+eswnDiscard);//~1214I~//~1217I~//~1223R~//~1224R~
                    if (Dump.Y) Dump.println("RADSmart.chkStatistic@@@@ Sreset 7Pair by TREND_SAME eswn="+eswnDiscard);//~1223I~//~1224R~//~vaawR~
//                  intent|=INTENT_TREND_SAME;                             //~1201I~//~1217I~//~1224R~//~vaawR~
                    intent&=~INTENT_7PAIR;                         //~1224I~
                }                                                          //~1214I~//~1217I~//~1224R~
//              if ((intent & (INTENT_TREND_SAME | INTENT_7PAIR))==0)//~1224I~//~1302R~//~vaawR~
//              if ((intent & INTENT_7PAIR)==0)                    //~vaawI~//~vaaCR~
//              {                                                  //~1308I~//~vaaCR~
			        intent&=~(INTENT_CHANTA|INTENT_TANYAO|INTENT_ALLSAME|INTENT_SAMECOLOR_ANY);//~1308I~
			        intent&=~(INTENT_STRAIGHT);                //~vaf9I~
			        intent&=~(INTENT_3SAMESEQ);                    //~vafbI~
			        intent&=~(INTENT_4ANKO);                       //~vaghI~
//			        intent|=getIntentByStatistics(Pshanten,ctrPair);                        //~1201I~//~1217I~//~1218R~//~1224I~//~vaaCR~
 			        intent|=getIntentByStatistics(intent,Pshanten,ctrPair);//~vaaCI~
//              }                                                  //~1308I~//~vaaCR~
            }                                                      //~1224I~
        	if (chkIntent3Dragon()!=0)                            //~1307I~//~1308R~
            {                                                      //~vaftI~
            	intent|=INTENT_3DRAGON;                             //~1308I~
            }                                                      //~vaftI~
            else                                                   //~1308I~
            	intent&=~INTENT_3DRAGON;                            //~1308I~
            if ((intent & INTENT_3DRAGON)!=0)                      //~1307I~
            {                                                      //~vabkI~
            	intent&=~(INTENT_7PAIR|INTENT_13ORPHAN);           //~1307I~
            	intent&=~(INTENT_ALLSAME);  //priority for 3dragon over toitoi//~vabkI~
			    intent&=~(INTENT_CHANTA);                          //~vaftI~
			    intent&=~(INTENT_4ANKO);                           //~vaghI~
            }                                                      //~vabkI~
        }                                                          //~1217I~
        else                                                       //~1224I~
			intent &= ~(INTENT_7PAIR | INTENT_13ORPHAN);           //~1224I~
        if (Dump.Y) Dump.println("RADSmart.chkStatistic intent="+Integer.toHexString(intent));//~1217I~
        return intent;                                             //~1201I~
    }                                                              //~1201I~
    //***********************************************************************
    //*get upper only considered next/conbutsu
    //*set array of (type,number)
    //*set itsHandvalue for dora                                   //~vai6I~
    //***********************************************************************
    private void getDoraOpen()
    {
        if (Dump.Y) Dump.println("RADSmart.getDoraOpen swUpdateDora="+RS.swUpdateDora);//~vaaqI~
    	if (RS.swUpdateDora)                                       //~1215I~
        {                                                          //~1215I~
        	RS.swUpdateDora=false;                                 //~1215I~
            Arrays.fill(itsDoraOpen,0);                            //~1215I~
	        ctrDoraOpen=AG.aUARonValue.UARDT.getDoraOpen(itsDoraOpen);//~1215I~
	        swDoraOpened=true;                                     //~vafaI~
        }                                                          //~1215I~
        for (int ii=0;ii<ctrDoraOpen;ii++)	//conver to pos
        {
        	int type=itsDoraOpen[ii+ii];
			int num=itsDoraOpen[ii+ii+1];
            setDoraOpen(type,num);
        }
        if (Dump.Y) Dump.println("RADSmart.getDoraOpen ctrDoraOpen="+ctrDoraOpen+",itsDoraOpen="+Arrays.toString(itsDoraOpen));
    }
    //***********************************************************************//~vai6I~
    //*no update itsHandValue                                      //~vai6I~
    //***********************************************************************//~vai6I~
    private void setItsDoraOpen()                                  //~vai6I~
    {                                                              //~vai6I~
        if (Dump.Y) Dump.println("RADSmart.setItsDoraOpen swUpdateDora="+RS.swUpdateDora);//~vai6I~
        RS.swUpdateDora=false;                                     //~vai6I~
        Arrays.fill(itsDoraOpen,0);                                //~vai6I~
        ctrDoraOpen=AG.aUARonValue.UARDT.getDoraOpen(itsDoraOpen); //~vai6I~
        swDoraOpened=true;                                         //~vai6I~
        if (Dump.Y) Dump.println("RADSmart.setItsDoraOpen ctrDoraOpen="+ctrDoraOpen+",itsDoraOpen="+Arrays.toString(itsDoraOpen));//~vai6I~
    }                                                              //~vai6I~
    //***********************************************************************
    private void setDoraOpen(int Ptype,int Pnum)
    {
    	for (int ii=0;ii<ctrHand;ii++)
        {
        	TileData td=tdsHand[ii];
            if (Ptype==td.type && Pnum==td.number)
            {                                                      //~1220I~
        		if (Dump.Y) Dump.println("RADSmart.setDoraOpen old itsHandValue["+ii+"]="+itsHandValue[ii]);//~vaiqI~
	            itsHandValue[ii]+=DV_DORA;    //= -600             //~1220R~
        		if (Dump.Y) Dump.println("RADSmart.setDoraOpen idx="+ii+",pos="+(Ptype*9+Pnum)+",new itsHandValue["+ii+"]="+itsHandValue[ii]);//~1220I~//~vaiqR~
            }                                                      //~1220I~
        }
        if (Dump.Y) Dump.println("RADSmart.setDoraOpen type="+Ptype+",num="+Pnum+",itsDoraOpen="+Utils.toStringMax(itsHandValue,ctrHand));//~1125R~//~1305R~
    }
    //***********************************************************************//~1216I~
    public boolean isDoraOpen(TileData Ptd)                        //~1216I~
    {                                                              //~1216I~
        if (Dump.Y) Dump.println("RADSmart.isDoraOpen swDoraOpened="+swDoraOpened+",swUpdateDora="+RS.swUpdateDora);//~vai6I~
    	boolean rc=false;                                          //~1216I~
        if (!swDoraOpened)                                         //~vafaI~
//  		getDoraOpen();                                         //~vafaI~//~vai6R~
    		setItsDoraOpen();//avoid update itsHandValue           //~vai6I~
        else                                                       //~vai6I~
    	if (RS.swUpdateDora)                                       //~vai6I~
    		setItsDoraOpen();                                      //~vai6R~
        for (int ii=0;ii<ctrDoraOpen;ii++)	//conver to pos        //~1216I~
        {                                                          //~1216I~
        	if (Ptd.type==itsDoraOpen[ii+ii] && Ptd.number==itsDoraOpen[ii+ii+1])//~1216I~
            {                                                      //~1216I~
            	rc=true;                                           //~1216I~
                break;                                             //~1216I~
            }                                                      //~1216I~
            	                                                   //~1216I~
        }                                                          //~1216I~
        if (Dump.Y) Dump.println("RADSmart.isDoraOpen rc="+rc+",type="+Ptd.type+",num="+Ptd.number+",ctrDoraOpen="+ctrDoraOpen+",itsDoraOpen="+Utils.toString(itsDoraOpen));//~1216I~//~vaamR~//~vai6R~
        return rc;
    }                                                              //~1216I~
    //***********************************************************************//~vagbI~
    public int getCtrDora(TileData Ptd)                            //~vagbI~
    {                                                              //~vagbI~
	    int rc=0;                                                  //~vagbI~
		if (isDoraOpen(Ptd))                                       //~vagbI~
           	rc++;                                                  //~vagbI~
        if (Ptd.isRed5())                                          //~vagbI~
            rc++;                                                  //~vagbI~
        if (Dump.Y) Dump.println("RADSmart.getCtrDora td="+Ptd.toString()+",rc="+rc);//~vagbI~
        return rc;                                                 //~vagbI~
    }                                                              //~vagbI~
    //***********************************************************************//~1313I~
    public int getCtrDoraInHand()                                  //~1313I~
    {                                                              //~1313I~
	    int rc=getCtrDoraInHand(eswnDiscard);                      //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInHand eswn="+eswnDiscard+",rc="+rc);//~1313I~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInHand(int Peswn)                         //~1313I~
    {                                                              //~1313I~
    	int player=Accounts.eswnToPlayer(Peswn);                   //~1313I~
        TileData[] tds=AG.aPlayers.getHands(player);               //~1313I~
    	int rc=0;                                                  //~1313I~
    	for (int ii=0;ii<tds.length;ii++)                          //~1313I~
        {                                                          //~1313I~
        	TileData td=tds[ii];                              //~1313I~
		    if (isDoraOpen(td))                                    //~1313I~
            	rc++;                                             //~1313I~
            if (td.isRed5())                                       //~1313I~
            	rc++;                                             //~1313I~
        }                                                          //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInHand eswn="+Peswn+",player="+player+",rc="+rc);//~1313I~//~vaf9R~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~vafwI~
    public int getCtrDoraInHandSameColor(int Peswn,int Pintent,TileData Ptd)//~vafwR~
    {                                                              //~vafwI~
    	int player=Accounts.eswnToPlayer(Peswn);                   //~vafwI~
        TileData[] tds=AG.aPlayers.getHands(player);               //~vafwI~
    	int rc=0;                                                  //~vafwI~
        if (Ptd!=null)                                             //~vafwI~
		    if (RAUtils.isMatchSameColor(true/*return true for word tile*/,Pintent,Ptd.type))//~vafwI~
            {                                                      //~vafwI~
			    if (isDoraOpen(Ptd))                               //~vafwI~
    	        	rc++;                                          //~vafwI~
        	    if (Ptd.isRed5())                                  //~vafwI~
            		rc++;                                          //~vafwI~
            }                                                      //~vafwI~
    	for (int ii=0;ii<tds.length;ii++)                          //~vafwI~
        {                                                          //~vafwI~
        	TileData td=tds[ii];                                   //~vafwI~
		    if (!RAUtils.isMatchSameColor(true/*return true for word tile*/,Pintent,td.type))//~vafwI~
            	continue;                                          //~vafwI~
		    if (isDoraOpen(td))                                    //~vafwI~
            	rc++;                                              //~vafwI~
            if (td.isRed5())                                       //~vafwI~
            	rc++;                                              //~vafwI~
        }                                                          //~vafwI~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInHandSameColor eswn="+Peswn+",player="+player+",Ptd="+Utils.toString(Ptd)+",rc="+rc);//~vafwI~
        return rc;                                                 //~vafwI~
    }                                                              //~vafwI~
    //***********************************************************************//~1313I~
    public int getCtrDoraInEarch()                                 //~1313I~
    {                                                              //~1313I~
		int rc=getCtrDoraEarth(eswnDiscard);                      //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInEarth eswn="+eswnDiscard+",rc="+rc);//~1313R~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInEarth(int Peswn)                        //~1313I~
    {                                                              //~1313I~
		int rc=getCtrDoraEarth(Peswn);                             //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInEarth eswn="+Peswn+",rc="+rc);//~1313R~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInHandAndEarch()                          //~1313I~
    {                                                              //~1313I~
		int rc=getCtrDoraInHandAndEarth(eswnDiscard);             //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInHandAndEarth eswn="+eswnDiscard+",rc="+rc);//~1313R~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInHandAndEarth(int Peswn)                 //~1313I~
    {                                                              //~1313I~
		int rc=getCtrDoraInHand(Peswn)+getCtrDoraInEarth(Peswn);    //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInHandAndEarth eswn="+Peswn+",rc="+rc);//~1313R~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1221I~
    //*chk dora is 1/9/ji                                          //~vaamI~
    //***********************************************************************//~vaamI~
    private boolean isDoraOpenChanta()                             //~1221I~
    {                                                              //~1221I~
        if (!swDoraOpened)                                         //~vafaI~
//    		getDoraOpen();                                         //~vafaI~//~vai6R~
      		setItsDoraOpen();   //avoid update itsHandValue        //~vai6I~
        else                                                       //~vai6I~
    	if (RS.swUpdateDora)                                       //~vai6I~
      		setItsDoraOpen();                                      //~vai6I~
    	boolean rc=false;                                          //~1221I~
        for (int ii=0;ii<ctrDoraOpen;ii++)	//conver to pos        //~1221I~
        {                                                          //~1221I~
        	int type=itsDoraOpen[ii+ii];                           //~1221I~
        	int num=itsDoraOpen[ii+ii+1];                          //~1221I~
        	if (type==TT_JI || num==TN1 || num==TN9)   //~1221I~   //~1222R~
            {                                                      //~1221I~
            	rc=true;                                           //~1221I~
                break;                                             //~1221I~
            }                                                      //~1221I~
                                                                   //~1221I~
        }                                                          //~1221I~
        if (Dump.Y) Dump.println("RADSmart.isDoraOpenChanta rc="+rc+",itsDoraOpen="+Utils.toString(itsDoraOpen,-1,ctrDoraOpen));//~1221I~
        return rc;                                                 //~1221I~
    }                                                              //~1221I~
    //***********************************************************************//~1220I~
    public boolean isDoraOpen(int Ppos)                            //~1220I~
    {                                                              //~1220I~
        if (!swDoraOpened)                                         //~vafaI~
//  		getDoraOpen();                                         //~vafaI~//~vai6R~
    		setItsDoraOpen();   //avoid update itshandvalue        //~vai6I~
        else                                                       //~vai6I~
    	if (RS.swUpdateDora)                                       //~vai6I~
    		setItsDoraOpen();                                      //~vai6I~
    	boolean rc=false;                                          //~1220I~
        int type=Ppos/CTR_NUMBER_TILE; int num=Ppos%CTR_NUMBER_TILE;//~1220I~
        for (int ii=0;ii<ctrDoraOpen;ii++)	//conver to pos        //~1220I~
        {                                                          //~1220I~
        	if (type==itsDoraOpen[ii+ii] && num==itsDoraOpen[ii+ii+1])//~1220I~
            {                                                      //~1220I~
            	rc=true;                                           //~1220I~
                break;                                             //~1220I~
            }                                                      //~1220I~
                                                                   //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RADSmart.isDoraOpen rc="+rc+",pos="+Ppos+",type="+type+",num="+num+",ctDoraOpen="+ctrDoraOpen+",itsDoraOpen="+Utils.toString(itsDoraOpen));//~1220I~//~vai6R~
        return rc;                                                 //~1220I~
    }                                                              //~1220I~
    //***********************************************************************//~vaaLR~
    public boolean isDoraOpenCurrent(int Ppos)                     //~vaaLR~
    {                                                              //~vaaLR~
        if (Dump.Y) Dump.println("RADSmart.isDoraOpenCurrent swUpdateDora="+RS.swUpdateDora);//~vaaLR~
//  	if (RS.swUpdateDora)                                       //~vaaLR~//~vai6R~
//  		getDoraOpen();       //will be done at itsDoraOpen     //~vaaLR~//~vai6R~
		boolean rc=isDoraOpen(Ppos);                               //~vaaLR~
        if (Dump.Y) Dump.println("RADSmart.isDoraOpenCurrent rc="+rc+",pos="+Ppos);//~vaaLR~
        return rc;                                                 //~vaaLR~
    }                                                              //~vaaLR~
    //***********************************************************************
    private TileData selectTile()
    {
        TileData tdDiscard=null;
        int mx=DV_NOTDISCARDABLE,mxPos=-1;
        //*********************************
        if (Dump.Y) Dump.println("RADSmart.selectTile@@@@ itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1125R~//~1301R~//~1305R~
        for (int ii=0;ii<ctrHand;ii++)
        {
        	int hv=itsHandValue[ii];
        	if (hv>mx)
            {
            	mx=hv;
                mxPos=ii;
            }
        }
        if (mxPos>=0)
        {                                                          //~1222I~
	        tdDiscard=tdsHand[mxPos];
            if (tdDiscard.isRed5())	//select non red5 if exist     //~1222I~
            {                                                      //~1222I~
            	int num=tdDiscard.number; int type=tdDiscard.type; //~1222R~
                for (int ii=0;ii<ctrHand;ii++)                     //~1222I~
                {                                                  //~1222I~
                	TileData td=tdsHand[ii];                       //~1222I~
                    if (td.type==type && td.number==num && !td.isRed5())//~1222I~
                    {                                              //~1222I~
                        tdDiscard=td;                              //~1222I~
                        break;                                     //~1222I~
                    }                                              //~1222I~
                }                                                  //~1222I~
            }                                                      //~1222I~
        }                                                          //~1222I~
        if (Dump.Y) Dump.println("RADSmart.selectTile eswn="+eswnDiscard+",ctrHand="+ctrHand+",mxPos="+mxPos+",mx="+mx+",tdDiscard="+Utils.toString(tdDiscard));//~1125R~//~1220R~
        return tdDiscard;
    }
    //***********************************************************************
    //*chk any winning tile is furiten
    //*from RADSEval.tryNextTake                                   //~1213I~
    //***********************************************************************
    public  boolean chkFuriten()                                   //~1121R~
    {
//        boolean rc=false;                                        //~1213R~
//        getWinList();                                            //~1213R~
//        for (int ii=0;ii<CTR_TILETYPE;ii++)                      //~1213R~
//        {                                                        //~1213R~
//            if (btsWin[ii])                                      //~1213R~
//                if (RS.isFuriten(eswnDiscard,ii))                  //~1121R~//~1213R~
//                {                                                //~1213R~
//                    rc=true;                                     //~1213R~
//                    break;                                       //~1213R~
//                }                                                //~1213R~
//        }                                                        //~1213R~
        boolean rc=chkFuriten(eswnDiscard,itsHand,ctrHand);        //~1213I~
        if (Dump.Y) Dump.println("RADSmart.chkFuriten rc="+rc);    //~1213R~
        return rc;                                                 //~1213R~
	}
    //***********************************************************************//~1220I~
    public  boolean chkFuritenSelf()                               //~1220I~
    {                                                              //~1220I~
        if (Dump.Y) Dump.println("RADSmart.chkFuritenSelf");       //~vafbI~
        boolean rc=chkFuritenSelf(eswnDiscard,itsHand,ctrHand);    //~1220I~
        if (Dump.Y) Dump.println("RADSmart.chkFuritenSelf rc="+rc);//~1220I~
        return rc;                                                 //~1220I~
	}                                                              //~1220I~
    //***********************************************************************//~1213I~
    //*from RARon at RonRiver                                      //~1213I~
    //***********************************************************************//~1213I~
    public  boolean chkFuriten(int PeswnOther,int[] PitsHand,int PctrHand)//~1213I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RADSmart.chkFuriten eswnOther="+PeswnOther+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1213I~//~1306R~//~vaj5R~
    	boolean rc=false;                                          //~1213I~
//  	getWinList(PitsHand,PctrHand);                             //~1213I~//~vagwR~
    	getWinListNoChkEmpty(PitsHand,PctrHand);                   //~vagwI~
        int ctrAfter=0;                                            //+vapvI~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~1213I~
        {                                                          //~1213I~
        	if (btsWin[ii])                                        //~1213I~
            {                                                      //+vapvI~
//          	if (RS.isFuriten(PeswnOther,ii))                    //~1213I~//~1306R~
            	if (RS.isFuritenRon(PeswnOther,ii))                //~1306I~
                {                                                  //~1213I~
                	rc=true;                                       //~1213I~
                    break;                                         //~1213I~
                }                                                  //~1213I~
        		ctrAfter+=RS.RSP[PeswnOther].isFuritenAfterDiscard(ii);//+vapvI~
            }                                                      //+vapvI~
        }                                                          //~1213I~
        if (ctrAfter>1)	//multiple winning tile after Discard until nex Discard//+vapvI~
        	rc=true;                                               //+vapvI~
        if (Dump.Y) Dump.println("RADSmart.chkFuriten rc="+rc+",ctrAfter="+ctrAfter);    //~1213I~//+vapvR~
        return rc;                                                 //~1213I~
	}                                                              //~1213I~
    //***********************************************************************//~va8fI~
    //*from RARon.isRonableMultiWait only,chk furiten and MultiWait:NG         //~va8fI~//~vak5R~
    //*NOT Used                                                    //~vak6I~
    //***********************************************************************//~va8fI~
    public  boolean chkFuritenMultiWait(boolean PswTake,int Pplayer,int Peswn,int[] PitsHand,int PctrHand,TileData PtdDiscarded)//~va8fR~
    {                                                              //~va8fI~
        if (Dump.Y) Dump.println("RADSmart.chkFuritenMultiWait eswn="+Peswn+",ctrHand="+PctrHand+",tdDiscarded="+PtdDiscarded.toString()+",itsHand="+Utils.toString(PitsHand,9));//~va8fR~
    	boolean rc=false;                                          //~va8fI~
    	boolean rcMultiNG=false;                                   //~va8fI~
      int ctrWin=                                                  //~va91I~
//  	getWinList(PitsHand,PctrHand);                             //~va8fI~//~vagwR~
    	getWinListNoChkEmpty(PitsHand,PctrHand);                   //~vagwI~
//      boolean swFuriten=false;                                   //~va96R~//~vak6R~
        int ctrOK=0,ctrNG=0;                                       //~va96I~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~va8fI~
        {                                                          //~va8fI~
        	if (btsWin[ii])                                        //~va8fI~
            {                                                      //~va8fI~
//          	if (RS.isFuritenRon(Peswn,ii))                     //~va8fI~//~va96R~
//          	if (!PswTake && RS.isFuritenRon(Peswn,ii))         //~va96I~//~vaj5R~
//          	if (!PswTake && RS.isFuritenRon(Peswn,ii)          //~vaj5I~//~vak5R~
//      		||   PswTake && RS.RSP[Peswn].isReachStatusErrFuriten())//~vaj5I~//~vak5R~
//                if (!PswTake && RS.isFuritenRon(Peswn,ii))         //~vak5I~//~vak6R~
//                {                                                  //~vak5I~//~vak6R~
//                    if (Dump.Y) Dump.println("RADSmart.chkFuritenMultiWait FuritenRon");//~vak5I~//~vak6R~
//                  if (AG.aRARon.setRonableMultiWaitCBFuritenRon(Peswn,ii/*pos of Ron Tile*/)) //set err depending chkmode//~vak5I~//~vak6R~
//                  {                                                //~vak5I~//~vak6R~
//                    swFuriten=true;                                //~vak5I~//~vak6R~
//                    rc=true;                                       //~vak5I~//~vak6R~
//                    break;                                         //~vak5I~//~vak6R~
//                  }                                                //~vak5I~//~vak6R~
//                }                                                  //~vak5I~//~vak6R~
//                else                                               //~vak5I~//~vak6R~
//                if ( PswTake && RS.RSP[Peswn].isReachStatusErrFuriten())//~vak5I~//~vak6R~
//                {                                                  //~va8fI~//~vak6R~
//                    if (Dump.Y) Dump.println("RADSmart.chkFuritenMultiWait FuritenReach");//~vaj5I~//~vak5R~//~vak6R~
//                    AG.aRARon.setRonableMultiWaitCBFuriten(ii/*pos of Ron Tile*/);//~va96R~//~vak6R~
//                    swFuriten=true;                                //~va96I~//~vak6R~
//                    rc=true;                                       //~va8fI~//~vak6R~
//                    break;                                         //~va8fI~//~vak6R~
//                }                                                  //~va8fI~//~vak6R~
//                else                                               //~va8fI~//~vak6R~
//                {   //*Furiten is already chked at RARon.isRonableMultiWait//~vak6R~
        	    	boolean rc2=AG.aRARon.isRonableMultiWaitCB(ii/*pos of Ron Tile*/,PswTake,Pplayer,Peswn,PitsHand,PctrHand,PtdDiscarded);//~va8fI~
                    if (!rc2)                                      //~va8fI~
                    {                                              //~va8fI~
				        if (Dump.Y) Dump.println("RADSmart.chkFuritenMultiWait@@@@ MultiWait NG rc2="+rc2);//~va8fR~
//      				if (ctrWin>1)	//multi                    //~va96R~
//      					AG.aRARon.setRonableMultiWaitCBErr();  //~va96R~
                    	rc=true; 	//fix err(kataAgari NG)        //~va8fI~
                        ctrNG++;                                   //~va96I~
//                      break;                                     //~va8fI~//~va96R~
                    }                                              //~va8fI~
                    else                                           //~va96I~
                        ctrOK++;                                   //~va96I~
//                }                                                  //~va8fI~//~vak6R~
            }                                                      //~va8fI~
        }                                                          //~va8fI~
//      if (rc && !swFuriten)   //ctrNG!=0 without furiten         //~va96I~//~vak6R~
        if (rc)   //ctrNG!=0                                       //~vak6I~
        {                                                          //~va96I~
        	if (ctrOK==0)                                          //~va96I~
				AG.aRARon.setRonableMultiWaitCBErr(RARON_ERR_FIX); //~va96I~
            else                                                   //~va96I~
            {                                                      //~vak5I~
				AG.aRARon.setRonableMultiWaitCBErr(RARON_ERR_MULTIPLE);//~va96I~
		    	if (!swCheckMultiWait)                             //~vak5I~
                	rc=false;	//accept Win button push without notify//~vak5I~
            }                                                      //~vak5I~
        }                                                          //~va96I~
        if (Dump.Y) Dump.println("RADSmart.chkFuritenMultiWait rc="+rc+",ctrWin="+ctrWin+",ctrOK="+ctrOK+",ctrNG="+ctrNG);//~va8fI~//~va96R~
        return rc;                                                 //~va8fI~
	}                                                              //~va8fI~
    //***********************************************************************//~1220I~
    //*from RADSEval                                               //~1220I~
    //***********************************************************************//~1220I~
    public  boolean chkFuritenSelf(int PeswnOther,int[] PitsHand,int PctrHand)//~1220I~
    {                                                              //~1220I~
        if (Dump.Y) Dump.println("RADSmart.chkFuriten eswnOther="+PeswnOther+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1220I~//~1305R~
    	boolean rc=false;                                          //~1220I~
//  	getWinList(PitsHand,PctrHand);                             //~1220I~//~vagwR~
    	getWinListNoChkEmpty(PitsHand,PctrHand);                   //~vagwI~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~1220I~
        {                                                          //~1220I~
        	if (btsWin[ii])                                        //~1220I~
            	if (RS.isFuritenSelf(PeswnOther,ii))               //~1220I~
                {                                                  //~1220I~
                	rc=true;                                       //~1220I~
                    break;                                         //~1220I~
                }                                                  //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RADSmart.chkFuritenSelf rc="+rc);//~1220I~
        return rc;                                                 //~1220I~
	}                                                              //~1220I~
    //***********************************************************************//~1213I~
    //*no user by getWinListNoChkEmpty                             //~vagwI~
    //***********************************************************************//~vagwI~
//  private void getWinList(int[] PitsHand,int PctrHand)           //~1213I~//~va91R~
    private int getWinList(int[] PitsHand,int PctrHand)            //~va91I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RADSmart.getWinList ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1213I~//~1305R~
      int ctrWin=                                                  //~va91I~
        AG.aRAReach.getBtsWinList(PitsHand,PctrHand,btsWin);	//get tenpai pattern//~1213I~
        if (Dump.Y) Dump.println("RADSmart.getWinList rc=ctrWin="+ctrWin+",btsWin="+Utils.toString(btsWin,9));//~1213I~//~va8fR~//~va91R~
        return ctrWin;                                             //~va91I~
    }                                                              //~1213I~
    //***********************************************************************//~vagwI~
    private int getWinListNoChkEmpty(int[] PitsHand,int PctrHand)  //~vagwI~
    {                                                              //~vagwI~
        if (Dump.Y) Dump.println("RADSmart.getWinListNoChkEmpty ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vagwI~
      	int ctrWin=AG.aRAReach.getBtsWinListNoChkEmpty(PitsHand,PctrHand,btsWin);	//get tenpai pattern//~vagwI~
        if (Dump.Y) Dump.println("RADSmart.getWinListNoChkEmpty rc=ctrWin="+ctrWin+",btsWin="+Utils.toString(btsWin,9));//~vagwI~
        return ctrWin;                                             //~vagwI~
    }                                                              //~vagwI~
//    //***********************************************************************//~1131R~
//    public int setIntent(int[] PitsStat)                         //~1131R~
//    {                                                            //~1131R~
//        if (Dump.Y) Dump.println("RoundStat.setIntent");         //~1131R~
//        if (ctrTaken>=HV_SET_INTENT)                             //~1131R~
//            return intent;                                       //~1131R~
//        intent=(intent & INTENT_BY_SHANTEN);//        =0x0700; //to reset 13orphan,7pair,normal//~1131R~
//        intent|=chkIntentSameColor(ctrTaken,PitsStat);           //~1131R~
//        intent|=chkIntentAllSame(ctrTaken,PitsStat);             //~1131R~
//        if (Dump.Y) Dump.println("RoundStat.setIntent eswn="+eswn+",intent="+Integer.toHexString(intent));//~1131R~
//        return intent;                                           //~1131R~
//    }                                                            //~1131R~
//    //***********************************************************************//~1131R~
//    //*set intent of same color                                  //~1131R~
//    //***********************************************************************//~1131R~
//    private int chkIntentSameColor(int ctrTaken,int[] PitsStat)  //~1131R~
//    {                                                            //~1131R~
//        int intent=0;                                            //~1131R~
//        int ctrMan=PitsStat[CSI_MAN];                            //~1131R~
//        int ctrPin=PitsStat[CSI_PIN];                            //~1131R~
//        int ctrSou=PitsStat[CSI_SOU];                            //~1131R~
//        int ctrWord=PitsStat[CSI_WORD];                          //~1131R~
//        int limit=HV_SET_INTENT_SAMECOLOR-ctrTaken/2;            //~1131R~
//        if ((ctrPin+ctrSou)<limit)                               //~1131R~
//            intent=INTENT_SAMECOLOR_MAN;                         //~1131R~
//        else                                                     //~1131R~
//        if ((ctrSou+ctrMan)<limit)                               //~1131R~
//            intent=INTENT_SAMECOLOR_PIN;                         //~1131R~
//        else                                                     //~1131R~
//        if ((ctrMan+ctrPin)<limit)                               //~1131R~
//            intent=INTENT_SAMECOLOR_SOU;                         //~1131R~
//        if (Dump.Y) Dump.println("RADEval.chkIntentSameColor intent="+Integer.toHexString(intent)+",man="+ctrMan+",pin="+ctrPin+",sou="+ctrSou+",ctrWord="+ctrWord+",HV_SET_INTENT_SAMECOLOR="+HV_SET_INTENT_SAMECOLOR+",limit="+limit);//~1131R~
//        return intent;                                           //~1131R~
//    }                                                            //~1131R~
//    //***********************************************************************//~1131R~
//    //*set intent of toitoi                                      //~1131R~
//    //***********************************************************************//~1131R~
//    private int chkIntentAllSame(int PctrTaken,int[] PitsStat)   //~1131R~
//    {                                                            //~1131R~
//        int intent=0;                                            //~1131R~
//        int ctrPair=PitsStat[CSI_PAIR];                          //~1131R~
//        int ctrPon=PitsStat[CSI_PON];                            //~1131R~
//        int ctrKan=PitsStat[CSI_KAN];                            //~1131R~
//        int v=(ctrKan+ctrPon)*HV_SET_INTENT_ALLSAME_VALUE_PON+ctrPair*HV_SET_INTENT_ALLSAME_VALUE_PAIR;//~1131R~
////      v-=PctrTaken;                                            //~1131R~
//        if (v>=HV_SET_INTENT_ALLSAME_VALUE)                      //~1131R~
//            intent=INTENT_ALLSAME;                               //~1131R~
//        if (Dump.Y) Dump.println("RADEval.chkIntentAllSame rc="+intent+",pair="+ctrPair+",ppon="+ctrPon+",kan="+ctrKan+",ctrTaken="+PctrTaken+",val="+v+",HV_SET_INTENT_ALLSAME_VALUE="+HV_SET_INTENT_ALLSAME_VALUE);//~1131R~
//        return intent;                                           //~1131R~
//    }                                                            //~1131R~
    //***********************************************************************//~1131I~
//  private int getIntentByStatistics(int Pshanten,int PctrPair)                                        //~1131R~//~1201R~//~1217R~//~1218R~//~vaaCR~
    private int getIntentByStatistics(int Pintent,int Pshanten,int PctrPair)//~vaaCI~
    {                                                              //~1131I~
        if (Dump.Y) Dump.println("RADSmart.getIntentByStatistics shanten="+Pshanten+",intent="+Integer.toHexString(Pintent));            //~1131I~//~1201R~//~1218R~//~vaaCR~
        int intent=0;
//      int intent=RSP.getIntent();                                //~1217R~
//      if (RSP.ctrTaken>=HV_SET_INTENT)                           //~1131I~//~1217R~
//          return intent;                                     //~1131I~//~1217R~
//      int intent=(RSP.intent & INTENT_BY_SHANTEN);//        =0x0700; //to reset 13orphan,7pair,normal//~1131I~//~1201R~
//      intent|=chkIntentSameColor(RSP.ctrTaken);                  //~1131I~//~1213R~
//      intent|=chkIntentAllSame(RSP.ctrTaken);                    //~1131I~//~1213R~
//      RSP.setIntent(intent); //add to 7pair,13orphan                 //~1131I~//~1201R~
        intent|=chkIntentSameColor(RSP.ctrTaken,Pshanten) | chkIntentAllSame(RSP.ctrTaken);//~vaajI~
//      if ((Pintent & (INTENT_SAMECOLOR_ANY|INTENT_ALLSAME))==0)   //samecolor priority is higher than straight//~vaf9I~//~vafbI~//~vafxR~
        if ((intent & (INTENT_SAMECOLOR_ANY|INTENT_ALLSAME))==0)   //samecolor priority is higher than straight//~vafxI~
	    	intent|=chkIntentStraight(intent,RSP.ctrTaken,Pshanten);   //~vaf9I~//~vafbR~
        if ((intent & (INTENT_SAMECOLOR_ANY|INTENT_ALLSAME|INTENT_STRAIGHT))==0)//~vafbR~
		    intent|=chkIntent3SameSeq(intent,RSP.ctrTaken,Pshanten);//~vafbR~
        if ((intent & INTENT_ALLSAME)!=0)                              //~vaajI~
            intent&=~INTENT_7PAIR;                                 //~vaajI~
//      if (chkChanta(PctrPair))                                   //~1217I~//~vagsR~
        if (chkChanta(PctrPair,Pshanten))                          //~vagsI~
			intent|=INTENT_CHANTA;                                 //~1217I~
        else                                                       //~1217I~
        if (chkTanyao(PctrPair,Pshanten))                                   //~1217I~//~1223R~
			intent|=INTENT_TANYAO;                                 //~1217I~
        else                                                       //~vaaCI~
        	if ((Pintent & INTENT_7PAIR)!=0)                       //~vaaCR~
		        if (chkChanta7Pair(PctrPair))                      //~vaaCI~
					intent|=INTENT_CHANTA;                         //~vaaCI~
//      intent|=chkIntentSameColor(RSP.ctrTaken,Pshanten) | chkIntentAllSame(RSP.ctrTaken);//~1213I~//~1215R~//~1218R~//~1219R~//~vaajR~
        if (Dump.Y) Dump.println("RADSmart.getIntentByStatistics eswnDiscard="+eswnDiscard+",intent=x"+Integer.toHexString(intent));//~1131I~//~1201R~//~1303R~
        return intent;                                             //~1131I~
    }                                                              //~1131I~
    //***********************************************************************//~1215I~
    //*chk earth color including no earth                          //~1218I~
    //***********************************************************************//~1218I~
    private boolean chkEarthDiscard(int Ptype)                     //~1215I~
    {                                                              //~1215I~
        boolean rc=RSP.isSameColorEarth(Ptype); //including no earth                    //~1215I~//~1218R~
//        if (true)                                                //~1215I~
//        {                                                        //~1215I~
//            int ctrSameDiscarded=0;                              //~1215I~
//            for (int ii=Ptype*CTR_NUMBER_TILE;ii<(Ptype+1)*CTR_NUMBE_TILE;ii++)//~1215I~
//                ctrSameDiscarded+=RSP.discarded[ii];             //~1215I~
//            if (ctrSameDiscarded                                 //~1215I~
//        }                                                        //~1215I~
        if (Dump.Y) Dump.println("RADSmart.chkEarthDiscard rc="+rc+",eswn="+RSP.eswn+",type="+Ptype);//~1215I~
        return rc;                                                 //~1215I~
    }                                                              //~1215I~
    //***********************************************************************//~1218I~
//  private boolean chkSameColorTrend(int Ptype,int Pshanten,int PctrType,int PctrWordDup,int PctrOther,int PctrTaken)//~1218I~//~vagyR~
    private boolean chkSameColorTrend(int Ptype,int Pshanten,int PctrType,int PctrWordDup,int PctrOther,int PctrWordSingle,int PctrTaken)//~vagyI~
    {                                                              //~1218I~
        if (Dump.Y) Dump.println("RADSmart.chkSameColorTrend entry type="+Ptype+",shanten="+Pshanten);//~vafgI~
    	boolean rc;                                                //~1219I~
		int intent=RSP.intent;                                     //~1219I~
//      int limit=HV_SET_INTENT_SAMECOLOR-PctrTaken/3;    //4-ctrTaken/3//~1219R~
//      int limit=Pshanten;                                        //~1219I~//~1308R~
        int limit=PctrTaken<=HV_SET_INTENT_SAMECOLOR_TAKEN ? HV_SET_INTENT_SAMECOLOR_OTHER1 : HV_SET_INTENT_SAMECOLOR_OTHER2; //6?3:2//~1308I~
		if ((intent & INTENT_SAMECOLOR_ANY)!=0)                    //~1219I~
        {                                                          //~1219I~
//            if (Ptype==TT_MAN)                                     //~1219I~//~1220R~
//                rc=(intent & INTENT_SAMECOLOR_MAN)!=0;             //~1219I~//~1220R~
//            else                                                   //~1219I~//~1220R~
//            if (Ptype==TT_PIN)                                     //~1219I~//~1220R~
//                rc=(intent & INTENT_SAMECOLOR_PIN)!=0;             //~1219I~//~1220R~
//            else                                                   //~1219I~//~1220R~
//                rc=(intent & INTENT_SAMECOLOR_SOU)!=0;             //~1219I~//~1220R~
            rc=RAUtils.isMatchSameColor(false/*No allow Word*/,intent,Ptype);//~1220I~
            if (rc && !chkEarthDiscard(Ptype))                     //~vafgI~
            {                                                      //~vafgI~
                if (Dump.Y) Dump.println("RADSmart.chkSameColorTrend samecolor intent return false by earth");//~vafgI~//~vag8R~
                return false;                                      //~vafgI~
            }                                                      //~vafgI~
        }                                                          //~1219I~
        else                                                       //~1219I~
        {                                                          //~1219I~
//          rc=chkEarthDiscard(Ptype) && Pshanten<HV_INTENT_SAMECOLOR_SHANTEN;//<=3//~1219R~//~1308R~//~vafxR~
            if (PctrTaken<=HV_SET_INTENT_SAMECOLOR_TAKEN)          //~vag6R~
				rc=Pshanten<=HV_INTENT_SAMECOLOR_SHANTEN &&  chkEarthDiscard(Ptype);//<=3//~vag6R~
            else                                                   //~vag6R~
				rc=Pshanten< HV_INTENT_SAMECOLOR_SHANTEN &&  chkEarthDiscard(Ptype);//<3//~vag6R~
            if (rc)                                                    //~1218I~//~1219R~
            {                                                          //~1218I~//~1219R~
//              rc=PctrOther<PctrType && (PctrOther-PctrWordDup)<limit;//~1218I~//~1219R~//~1308R~
//              rc=PctrOther<PctrType && PctrOther<=limit;         //~1308I~//~vagyR~
                rc=PctrOther<(PctrType+PctrWordDup) && PctrOther<=limit && (PctrOther+PctrWordSingle)<=(limit+1);//~vagyR~
            }                                                          //~1218I~//~1219R~
        }                                                          //~1219I~
        if (Dump.Y) Dump.println("RADSmart.chkSameColorTrend rc="+rc+",old intent=x"+Integer.toHexString(intent)+",ctrTaken="+PctrTaken+",limit="+limit+",type="+Ptype+",shanten="+Pshanten+",ctrType="+PctrType+",ctrWordDup="+PctrWordDup+",ctrOther="+PctrOther+",ctrWordSingle="+PctrWordSingle);//~1218I~//~1219R~//~1308R~//~vagyR~
        return rc;                                                 //~1218I~
    }                                                              //~1218I~
    //***********************************************************************//~1131I~
    //*set intent of same color                                    //~1131I~
    //***********************************************************************//~1131I~
    private int chkIntentSameColor(int PctrTaken,int Pshanten)                   //~1131I~//~1215R~//~1218R~
    {                                                              //~1131I~
        if (Dump.Y) Dump.println("RADSmart.chkIntentSameColor eswn="+eswnDiscard+",ctrTaken="+PctrTaken+",shanten="+Pshanten);//~1215I~//~1218R~//~vagyR~
		int ctrMan,ctrPin,ctrSou/*,ctrWord,ctrAll*/,ctrWordDup;               //~1215I~//~1218R~
		int ctrWord,ctrWordSingle;                                 //~vagyI~
        int intent=0;                                              //~1131I~
        ctrMan=itsStatHand[CSI_MAN];                               //~1131I~
        ctrPin=itsStatHand[CSI_PIN];                               //~1131I~
        ctrSou=itsStatHand[CSI_SOU];                               //~1131I~
//      ctrWord=itsStatHand[CSI_WORD];                             //~1131I~//~1215R~
//      ctrWordDup=itsStatHand[CSI_WORD_DUP]/2;	//decrese if word pair exist; 1 for pair and tripret,2 for 2 pair,...//~1217R~//~1218R~//~vagyR~
        ctrWordDup=itsStatHand[CSI_WORD_DUP];	//decrese if word pair exist; 1 for pair and tripret,2 for 2 pair,...//~vagyI~
        ctrWord=itsStatHand[CSI_WORD];	//total word tile          //~vagyI~
        ctrWordSingle=ctrWord-ctrWordDup;                          //~vagyI~
        if (Dump.Y) Dump.println("RADSmart.chkIntentSameColor ctrWordSingle="+ctrWordSingle+",ctrWordDup="+ctrWordDup+",ctrWord="+ctrWord);//~vagyR~
//      int limit=HV_SET_INTENT_SAMECOLOR-PctrTaken/3;    //other color <4//~1215R~//~1218R~
//      if ((ctrPin+ctrSou)<ctrMan && (ctrPin+ctrSou-ctrWordDup)<limit && chkEarthDiscard(TT_MAN))      //~1215R~//~1217R~//~1218R~
		int color=-1;                                              //~vag8I~
//      if (chkSameColorTrend(TT_MAN,Pshanten,ctrMan,ctrWordDup,ctrPin+ctrSou,PctrTaken))//~1218I~//~vagyR~
        if (chkSameColorTrend(TT_MAN,Pshanten,ctrMan,ctrWordDup,ctrPin+ctrSou,ctrWordSingle,PctrTaken))//~vagyR~
        {                                                          //~1214I~
            intent=INTENT_SAMECOLOR_MAN;                           //~1131I~
            color=TT_MAN;                                          //~vag8I~
        }                                                          //~1214I~
        else                                                       //~1131I~
//      if ((ctrSou+ctrMan)<ctrPin && (ctrSou+ctrMan-ctrWordDup)<limit && chkEarthDiscard(TT_PIN))                                 //~1131I~//~1215R~//~1217R~//~1218R~
//      if (chkSameColorTrend(TT_PIN,Pshanten,ctrPin,ctrWordDup,ctrSou+ctrMan,PctrTaken))//~1218I~//~vagyR~
        if (chkSameColorTrend(TT_PIN,Pshanten,ctrPin,ctrWordDup,ctrSou+ctrMan,ctrWordSingle,PctrTaken))//~vagyR~
        {                                                          //~1214I~
            intent=INTENT_SAMECOLOR_PIN;                           //~1131I~
            color=TT_PIN;                                          //~vag8I~
        }                                                          //~1214I~
        else                                                       //~1131I~
//      if ((ctrMan+ctrPin)<ctrSou && (ctrMan+ctrPin-ctrWordDup)<limit && chkEarthDiscard(TT_SOU))                                 //~1131I~//~1215R~//~1217R~//~1218R~
//      if (chkSameColorTrend(TT_SOU,Pshanten,ctrSou,ctrWordDup,ctrMan+ctrPin,PctrTaken))//~1218I~//~vagyR~
        if (chkSameColorTrend(TT_SOU,Pshanten,ctrSou,ctrWordDup,ctrMan+ctrPin,ctrWordSingle,PctrTaken))//~vagyR~
        {                                                          //~1214I~
            intent=INTENT_SAMECOLOR_SOU;                           //~1131I~
            color=TT_SOU;                                          //~vag8I~
        }                                                          //~1214I~
        if (color>=0 && RAUtils.getCtrOtherColorMeld(color,itsHand)>0)//~vag8I~
        {                                                          //~vag8M~
        	if (Dump.Y) Dump.println("RADSmart.chkSameColorTrend reset samecolor intent  by other color meld ctr");//~vag8I~
            intent=0;                                              //~vag8I~
		}                                                          //~vag8M~
        if (intent!=0)                                             //~vag8I~
        {                                                          //~vag8I~
	        if (Dump.Y) Dump.println("RADSmart.chkIntentSameColor setIntent eswn="+eswnDiscard+",color="+color+",intent="+Integer.toHexString(intent));//~1219I~//~1303R~//~vaajR~//~vag8I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~//~vag8M~
                                    UView.showToastLong("RADSmart Set intent samecolor="+color+",eswn="+eswnDiscard);//~1214I~//~1223R~//~vag8I~
        }                                                          //~vag8I~
        if (Dump.Y) Dump.println("RADSmart.chkIntentSameColor intent="+Integer.toHexString(intent)+",man="+ctrMan+",pin="+ctrPin+",sou="+ctrSou+",ctrWordDup="+ctrWordDup+",HV_SET_INTENT_SAMECOLOR="+HV_SET_INTENT_SAMECOLOR);//~1131I~//~1217R~
        return intent;                                             //~1131I~
    }                                                              //~1131I~
    //***********************************************************************//~vaiqI~
    //*from RACall at samecolor intent to get other color count    //~vaiqI~
    //***********************************************************************//~vaiqI~
    public int getCtrOtherColorInHand(int Peswn,int Pintent)       //~vaiqI~
    {                                                              //~vaiqI~
        int[] itsStatHand=RS.RSP[Peswn].getHandStatisticSaved();   //~vaiqR~
        int ctrMan=itsStatHand[CSI_MAN];                           //~vaiqI~
        int ctrPin=itsStatHand[CSI_PIN];                           //~vaiqI~
        int ctrSou=itsStatHand[CSI_SOU];                           //~vaiqI~
        int ctrOther;                                              //~vaiqI~
        if ((Pintent & INTENT_SAMECOLOR_MAN)!=0)                   //~vaiqI~
        	ctrOther=ctrPin+ctrSou;                                //~vaiqI~
        else                                                       //~vaiqI~
        if ((Pintent & INTENT_SAMECOLOR_PIN)!=0)                   //~vaiqI~
        	ctrOther=ctrSou+ctrMan;                                //~vaiqI~
        else                                                       //~vaiqI~
        	ctrOther=ctrMan+ctrPin;                                //~vaiqI~
        if (Dump.Y) Dump.println("RADSmart.getCtrOtherColorInHand eswn="+Peswn+",intent="+Integer.toHexString(Pintent)+",ctrOther="+ctrOther);//~vaiqI~
        return ctrOther;                                           //~vaiqI~
    }                                                              //~vaiqI~
    //***********************************************************************//~vaf9I~
    //*set intent of straight if not samecolor                     //~vaf9I~
    //***********************************************************************//~vaf9I~
    private int chkIntentStraight(int Pintent,int PctrTaken,int Pshanten)//~vaf9I~
    {                                                              //~vaf9I~
        if (Dump.Y) Dump.println("RADSmart.chkIntentStraight eswn="+eswnDiscard+",intent="+Integer.toHexString(Pintent)+",ctrTaken="+PctrTaken+",shanten="+Pshanten);//~vaf9R~
        if (Dump.Y) Dump.println("RADSmart.chkIntentStraight itsHand="+Utils.toString(itsHand,9));//~vaf9I~//~vafbR~
        int intent=0,type=-1,ctrType=0,numEarth=0;//~vaf9I~
		boolean swAllInHand=RS.isAllInHand(eswnDiscard);           //~vaf9I~
		if (swAllInHand)                                           //~vaf9I~
        {                                                          //~vaf9I~
        	if (Dump.Y) Dump.println("RADSmart.chkIntentStraight StatHand man="+itsStatHand[CSI_MAN]+",pin="+itsStatHand[CSI_PIN]+",sou="+itsStatHand[CSI_SOU]);//~vaf9I~
        	if (itsStatHand[CSI_MAN]>=HV_INTENT_STRAIGHT_CTRTYPE)	//>=7//~vaf9I~
            	type=TT_MAN;	                                   //~vaf9I~
            else                                                   //~vaf9I~
        	if (itsStatHand[CSI_PIN]>=HV_INTENT_STRAIGHT_CTRTYPE)	//>=7//~vaf9I~
            	type=TT_PIN;                                       //~vaf9I~
            else                                                   //~vaf9I~
        	if (itsStatHand[CSI_SOU]>=HV_INTENT_STRAIGHT_CTRTYPE)	//>=7//~vaf9I~
            	type=TT_SOU;                                       //~vaf9I~
        }                                                          //~vaf9I~
        else                                                       //~vaf9I~
        {                                                          //~vaf9I~
        	Point p=getEarthColorStraight();                       //~vaf9I~
            if (p!=null)                                           //~vaf9I~
            {                                                      //~vaf9I~
                int typeEarth=p.x;                                 //~vaf9R~
                numEarth=p.y;                                      //~vaf9R~
                int ctrHand=0;                                     //~vaf9R~
                switch(typeEarth)                                  //~vaf9R~
                {                                                  //~vaf9R~
                case TT_MAN:                                       //~vaf9R~
                    ctrHand=itsStatHand[CSI_MAN];                  //~vaf9R~
                    break;                                         //~vaf9R~
                case TT_PIN:                                       //~vaf9R~
                    ctrHand=itsStatHand[CSI_PIN];                  //~vaf9R~
                    break;                                         //~vaf9R~
                case TT_SOU:                                       //~vaf9R~
                    ctrHand=itsStatHand[CSI_SOU];                  //~vaf9R~
                    break;                                         //~vaf9R~
                }                                                  //~vaf9R~
                if (Dump.Y) Dump.println("RADSmart.chkIntentStraight not AllInHand typeEarth="+typeEarth+",ctrHand="+ctrHand);//~vaf9R~
                if (ctrHand+PAIRCTR>=HV_INTENT_STRAIGHT_CTRTYPE)    //>=7//~vaf9R~
                    type=typeEarth;                                //~vaf9R~
            }                                                      //~vaf9I~
            else                                                   //~vafjI~
            {                                                      //~vafjI~
			    type=RS.getColorStraight(eswnDiscard);             //~vafjI~
	        	intent=INTENT_STRAIGHT;                            //~vafjI~
            }                                                      //~vafjI~
        }                                                          //~vaf9I~
        if (type==-1)                                              //~vaf9I~
        {                                                          //~vaf9I~
        	if (Dump.Y) Dump.println("RADSmart.chkIntentStraight return 0 by type=-1");//~vaf9I~
            return 0;                                              //~vafbI~
        }                                                          //~vaf9I~
      if (intent==0)	//not by getColorStraight for 2nd call     //~vafjI~
      {                                                            //~vafjI~
        for (int ii=0,pos=type*CTR_NUMBER_TILE;ii<CTR_NUMBER_TILE;pos++,ii++)//~vaf9R~
        {                                                          //~vaf9I~
        	if (!swAllInHand)                                      //~vaf9I~
            {                                                      //~vaf9I~
            	if (ii>=numEarth && ii<numEarth+PAIRCTR)           //~vaf9I~//~vafbR~
                	ctrType++;                                     //~vaf9I~
                else                                               //~vafbI~
        		if (itsHand[pos]!=0)                               //~vafbI~
            		ctrType++; 	//                                 //~vafbI~
            }                                                      //~vaf9I~
            else                                                   //~vaf9I~
            {                                                      //~vaf9I~
        		if (itsHand[pos]!=0)                               //~vaf9R~
            		ctrType++; 	//                                 //~vaf9R~
            }                                                      //~vaf9I~
        }                                                          //~vaf9I~
        if (ctrType>=HV_INTENT_STRAIGHT_CTRTYPE)                   //~vaf9I~
        {                                                          //~vaf9I~
            if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)         //~vaf9I~
                UView.showToastLong("RADSmart.intent=STRAIGHT eswn="+eswnDiscard+",color="+type);//~vaf9I~
        	intent=INTENT_STRAIGHT;                                //~vaf9R~
            RS.setColorStraight(eswnDiscard,type);                 //~vaf9I~
        }                                                          //~vaf9I~
      }                                                            //~vafjI~
        if (Dump.Y) Dump.println("RADSmart.chkIntentStraight ctrType="+ctrType+",intent="+Integer.toHexString(intent));//~vaf9I~//~vafbR~
        return intent;                                             //~vaf9I~
    }                                                              //~vaf9I~
    //***********************************************************************//~vafbI~
    //*set intent of 3sameseq                                      //~vafbI~
    //***********************************************************************//~vafbI~
    private int chkIntent3SameSeq(int Pintent,int PctrTaken,int Pshanten)//~vafbI~
    {                                                              //~vafbI~
        if (Dump.Y) Dump.println("RADSmart.chkIntent3SameSeq eswn="+eswnDiscard+",intent="+Integer.toHexString(Pintent)+",ctrTaken="+PctrTaken+",shanten="+Pshanten);//~vafbI~
        if (Dump.Y) Dump.println("RADSmart.chkIntent3SameSeq itsHand="+Utils.toString(itsHand,9));//~vafbI~
		boolean swAllInHand=RS.isAllInHand(eswnDiscard);           //~vafbI~
        int numTop=-1,intent=0;                                             //~vafbI~
        Arrays.fill(wk3SameSeq,0);                                 //~vafbI~
        for (int ii=0;ii<CTR_NUMBER_TILE;ii++)                     //~vafbR~
        {                                                          //~vafbI~
            if (itsHand[ii]!=0)                                    //~vafbI~
            	wk3SameSeq[ii]++;                                  //~vafbI~
            if (itsHand[ii+CTR_NUMBER_TILE]!=0)                    //~vafbI~
            	wk3SameSeq[ii]++;                                  //~vafbI~
            if (itsHand[ii+CTR_NUMBER_TILE*2]!=0)                  //~vafbI~
            	wk3SameSeq[ii]++;                                  //~vafbI~
        }                                                          //~vafbI~
        if (Dump.Y) Dump.println("RADSmart.chkIntent3SameSeq wk3SameSeq="+Arrays.toString(wk3SameSeq));//~vafbI~
		if (swAllInHand)                                           //~vafbI~
        {                                                          //~vafbI~
        	if (Dump.Y) Dump.println("RADSmart.chkIntent3SameSeq StatHand man="+itsStatHand[CSI_MAN]+",pin="+itsStatHand[CSI_PIN]+",sou="+itsStatHand[CSI_SOU]);//~vafbI~
            for (int ii=0;ii<=CTR_NUMBER_TILE-PAIRCTR;ii++)     //0-->6//~vafbI~
            {                                                      //~vafbI~
            	int ctrSameSeq=0;                                  //~vafbI~
                for (int jj=0;jj<PAIRCTR;jj++)                         //~vafbI~
                {                                                  //~vafbI~
//              	if (wk3SameSeq[ii+jj]>=2)   //allow 234,_34,_34 ; ctr of 2 is 1 but total=1//~vafbR~
                    	ctrSameSeq+=wk3SameSeq[ii+jj];             //~vafbI~
                }                                                  //~vafbI~
		        if (Dump.Y) Dump.println("RADSmart.chkIntent3SameSeq allInHand ii="+ii+",ctrSameSeq="+ctrSameSeq);//~vafbI~
	        	if (ctrSameSeq>=HV_INTENT_3SAMESEQ_CTRTYPE)	//>=7  //~vafbI~
                {                                                  //~vafbI~
                	numTop=ii;                                     //~vafbI~
                    break;                                         //~vafbI~
                }                                                  //~vafbI~
            }                                                      //~vafbI~
        }                                                          //~vafbI~
        else                                                       //~vafbI~
        {                                                          //~vafbI~
        	int num=getEarchNum3SameSeq();                         //~vafbI~
            if (num!=-1)                                           //~vafbI~
            {                                                      //~vafbI~
            	int ctrSameSeq=PAIRCTR;                            //~vafbI~
                for (int jj=0;jj<PAIRCTR;jj++)                         //~vafbI~
                {                                                  //~vafbI~
                	ctrSameSeq+=wk3SameSeq[num+jj];               //~vafbI~
                }                                                  //~vafbI~
		        if (Dump.Y) Dump.println("RADSmart.chkIntent3SameSeq notAllInHand num="+num+",ctrSameSeq="+ctrSameSeq);//~vafbI~
	        	if (ctrSameSeq>=HV_INTENT_3SAMESEQ_CTRTYPE)	//>=7  //~vafbI~
                	numTop=num;                                    //~vafbI~
            }                                                      //~vafbI~
            else                                                   //~vafkI~
            {                                                      //~vafkI~
	        	numTop=RS.getNum3SameSeq(eswnDiscard);  //already issued chii by 3sameseq//~vafkI~
            }                                                      //~vafkI~
        }                                                          //~vafbI~
        if (numTop!=-1)                                            //~vafbI~
        {                                                          //~vafbI~
            if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)         //~vafbI~
                UView.showToastLong("RADSmart.intent=3SAMESEQ eswn="+eswnDiscard+",numTop="+numTop);//~vafbI~
        	intent=INTENT_3SAMESEQ;                                //~vafbI~
            RS.setNum3SameSeq(eswnDiscard,numTop);                 //~vafbI~
        }                                                          //~vafbI~
        if (Dump.Y) Dump.println("RADSmart.chkIntent3SameSeq eswn="+eswnDiscard+",intent="+Integer.toHexString(intent));//~vafbR~
        return intent;                                             //~vafbI~
    }                                                              //~vafbI~
    //***********************************************************************//~vaf9I~
    //*return Earth is one Chii for straight                       //~vaf9I~
    //***********************************************************************//~vaf9I~
    public Point getEarthColorStraight()                           //~vaf9I~
    {                                                              //~vaf9I~
        Point p=RS.getEarthColorStraight(eswnDiscard);         //~vaf9I~
        if (Dump.Y) Dump.println("RADSmart.getEarthColorStraight rc="+Utils.toString(p));//~vaf9I~
        return p;
    }                                                              //~vaf9I~
    //***********************************************************************//~vafbI~
    //*return Earth is one Chii for straight                       //~vafbI~
    //***********************************************************************//~vafbI~
    public int getEarchNum3SameSeq()                               //~vafbI~
    {                                                              //~vafbI~
        int num=RS.getEarthNum3SameSeq(eswnDiscard);               //~vafbI~
        if (Dump.Y) Dump.println("RADSmart.getEarthNum3SameSeq num="+num);//~vafbI~
        return num;                                                //~vafbI~
    }                                                              //~vafbI~
    //***********************************************************************//~1131I~
    //*set intent of toitoi                                        //~1131I~
    //***********************************************************************//~1131I~
    private int chkIntentAllSame(int PctrTaken)                    //~1131I~
    {                                                              //~1131I~
        int intent=0;                                              //~1131I~
        int ctrPair=itsStatHand[CSI_PAIR];                         //~1131I~
        int ctrPon=itsStatHand[CSI_PON];                           //~1131I~
        int ctrKan=itsStatHand[CSI_KAN];                           //~1131I~
        int ctrEarth=RS.RSP[eswnDiscard].ctrPairStatus;            //~1217I~
        if (ctrEarth!=0 && !RS.RSP[eswnDiscard].isPairSameAll())   //~1217I~
        	return 0;                                              //~1217I~
//      int v=(ctrKan+ctrPon+ctrEarth)*HV_SET_INTENT_ALLSAME_VALUE_PON+ctrPair*HV_SET_INTENT_ALLSAME_VALUE_PAIR;//*3+*1//~1305R~//~vag7R~
//  	//pon*3+pair*1>=8                                         //~1305I~//~1308R~//~vaaLR~//~vaiqR~
        int ctrTriple=ctrKan+ctrPon+ctrEarth;                      //~vag7M~
        boolean swAllSame=ctrTriple>=HV_SET_INTENT_ALLSAME_VALUE_PON && (ctrTriple+ctrPair)>=HV_SET_INTENT_ALLSAME_VALUE_PAIR;//~vag7M~
    	//* triplet>=1 && (triplet + pair)>=4                      //~vaiqI~
        if (swAllSame && RAUtils.getCtrSeqMeld(itsHand)>0)           //~vag7R~
        	swAllSame=false;                                       //~vag7I~
//      v-=PctrTaken;                                              //~1131I~
//      if (v>=HV_SET_INTENT_ALLSAME_VALUE)       //8                 //~1131I~//~1217R~//~1308R~//~vaaLR~//~vag7R~
        if (swAllSame)                                             //~vag7I~
        {                                                          //~1217I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1217I~
                                    UView.showToastLong("RADSmart Set intent ALLSAME eswn="+eswnDiscard);//~1217I~//~1223R~
	        if (Dump.Y) Dump.println("RADSmart.chkIntentAllSame@@@@ setIntent eswn="+eswnDiscard);//~1217I~//~1219R~//~1303R~
            intent=INTENT_ALLSAME;                                 //~1131I~
			if (RS.isAllInHand(eswnDiscard) && (ctrTriple)>=HV_SET_INTENT_ALLSAME_VALUE_4ANKO && (ctrTriple+ctrPair)>=HV_SET_INTENT_ALLSAME_VALUE_PAIR)//~vaghI~
            	if (isPossibilityOf4Anko(eswnDiscard,itsHand,itsExposed))//~vaghR~
                {                                                  //~vaghI~
			        if (Dump.Y) Dump.println("RADSmart.chkIntentAllSame@@@@ setIntent INTENT_4ANKO eswn="+eswnDiscard);//~vaghI~
		            intent|=INTENT_4ANKO;                          //~vaghI~
                }                                                  //~vaghI~
        }                                                          //~1217I~
        if (Dump.Y) Dump.println("RADSmart.chkIntentAllSame rc=intent=x"+Integer.toHexString(intent)+",pair="+ctrPair+",pon="+ctrPon+",kan="+ctrKan+",ctrEarth="+ctrEarth+",ctrTaken="+PctrTaken+",swAllSame="+swAllSame);//~vag7R~
        return intent;                                             //~1131I~
    }                                                              //~1131I~
    //***********************************************************************//~1217I~
    public static boolean isPossibilityOf4Anko(int Peswn,int[] PitsHand,int[] PitsExposed)//~vaghR~
    {                                                              //~vaghI~
        if (Dump.Y) Dump.println("RADSmart.isPossibilityOf4Anko extry eswn="+Peswn+",itsHand="+Utils.toString(PitsHand,9));//~vaghR~
        int ctr,ctrValidPair=0;                                    //~vaghI~
        for (int pos=0;pos<CTR_TILETYPE;pos++)                     //~vaghI~
        {                                                          //~vaghI~
        	ctr=PitsHand[pos];                                     //~vaghI~
        	if (ctr==2)                                            //~vaghI~
            {                                                      //~vaghI~
		        if (Dump.Y) Dump.println("RADSmart.isPossibilityOf4Anko pos="+pos+",ctr=2,exposed="+PitsExposed[pos]);//~vaghR~
        		if (2+PitsExposed[pos]<PIECE_DUPCTR)               //~vaghR~
                	ctrValidPair++;                                //~vaghI~
            }                                                      //~vaghI~
        }                                                          //~vaghI~
        boolean rc=ctrValidPair>=(HV_SET_INTENT_ALLSAME_VALUE_PAIR-HV_SET_INTENT_ALLSAME_VALUE_4ANKO);//>=2 (=4-2)//~vaghI~
        if (Dump.Y) Dump.println("RADSmart.isPossibilityOf4Anko rc="+rc+",ctrValidPair="+ctrValidPair);//~vaghR~
        return rc;                                                 //~vaghR~
    }                                                              //~vaghI~
    //***********************************************************************//~vaghI~
    private boolean chkTanyao(int PctrPair,int Pshanten)                            //~1217I~//~1223R~
    {                                                              //~1217I~
    	boolean rc=false;                                          //~1217I~
        if (Dump.Y) Dump.println("RADSmart.chkTanyao eswn="+eswnDiscard+",ctrPair="+PctrPair);//~1217I~
        if (!RS.RSP[eswnDiscard].isPairTanyaoAllOrNoPair())        //~1217R~
        {                                                          //~vaaeI~
	        if (Dump.Y) Dump.println("RADSmart.chkTanyao return false by chantaMeld in Earth");//~vaaeI~
            return false;                                          //~1217R~
        }                                                          //~vaaeI~
//      if (getCtrChantaInHand()!=0)                               //~vaaeI~//~vagtR~
        if (getCtrChantaInHand(eswnDiscard)!=0)                    //~vagtI~
        {                                                          //~vaaeI~
	        if (Dump.Y) Dump.println("RADSmart.chkTanyao return false by chantaMeld in Hand");//~vaaeI~
            return false;                                          //~vaaeI~
        }                                                          //~vaaeI~
        int ctrNonTanyao=itsStatHand[CSI_WORD]+itsStatHand[CSI_TERMINAL];//~1217I~
        int ctrTaken=RS.RSP[eswnDiscard].ctrTaken;                 //~1217I~
        if (ctrNonTanyao<=(ctrTaken<HV_INTENT_TANYAO_TAKECTR ? HV_INTENT_TANYAO_TILECTR_EARLY : HV_INTENT_TANYAO_TILECTR))//~1218R~
//*                        ctrTaken<8                        ? <=3                              <=2//~1218I~//~vagtR~
        {                                                          //~1217I~
    		if (Pshanten>=HV_INTENT_TANYAO_SHANTEN_FORCE)	//  =2;     //if shanten<2 ignore chanta meld & chanta dora//~1223I~
            {                                                      //~vaamI~
                if (!isDoraOpenChanta() && !chkChantaMeldForTanyao())  //~1221I~//~1223R~
                {                                                      //~1221I~//~1223R~
                                    if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1217I~//~1223R~
                                        UView.showToastLong("RADSmart.chkTanyao set intent TANYAO eswn="+eswnDiscard);//~1217I~//~1223R~
                    if (Dump.Y) Dump.println("RADSmart.chkTanyao @@@@ setIntent eswn="+eswnDiscard);//~1217I~//~1219R~//~1221R~//~1223R~
                    rc=true;                                               //~1217I~//~1221R~//~1223R~
                }                                                      //~1221I~//~1223R~
            }                                                      //~vaamI~
            else                                                   //~vaamI~
            	rc=true;                                           //~vaamI~
        }                                                          //~1217I~
        if (Dump.Y) Dump.println("RADSmart.chkTanyao rc="+rc+",ctrTaken="+ctrTaken+",ctrNonTanyaoTile="+ctrNonTanyao);//~1217I~
        return rc;                                                 //~1217I~
    }                                                              //~1217I~
    //***********************************************************************//~1217I~
//  private boolean chkChanta(int PctrPair)                        //~1217I~//~vagsR~
    private boolean chkChanta(int PctrPair,int Pshanten)           //~vagsI~
    {                                                              //~1217I~
    	boolean rc=false;                                          //~1217I~
    	int ctr,ctrMeld3=0,ctrMeld=0;                              //~1307I~
        boolean swManyTanyaoTile=false;                            //~vagsI~
        if (Dump.Y) Dump.println("RADSmart.chkChanta entry eswn="+eswnDiscard+",ctrPair="+PctrPair+",shanten="+Pshanten);//~1217I~//~vagsR~
//      if (!RS.RSP[eswnDiscard].isPairChantaAllOrNoPair())        //~1217R~//~vaadR~
//          return false;                                          //~1217R~//~vaadR~
        int ctrEarthChanta=RS.RSP[eswnDiscard].getCtrEarthChanta();//~vaadI~
		if (ctrEarthChanta<0)	//tanyao Earth                     //~vaadI~
        {                                                          //~vafkI~
        	if (Dump.Y) Dump.println("RADSmart.chkChanta return false by ctrEarthChanta");//~vafkI~
          	return false;                                          //~vaadI~
        }                                                          //~vafkI~
		if (ctrEarthChanta!=0)	//chanta Earth                     //~vaadI~
        {                                                          //~vaadI~
	    	ctrMeld3++;                                            //~vaadI~
            ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4            //~vaadI~
        }                                                          //~vaadI~
        int ctrTanyao=0;                                           //~vainI~
        for (int pos=0;pos<OFFS_WORDTILE;pos+=CTR_NUMBER_TILE)         //~1217I~
        {                                                          //~1217I~
        	ctr=itsHand[pos];                                      //~1217I~
//      	if (ctr>1)                                             //~1217I~//~vaaeR~
            if (ctr>=PAIRCTR)                                      //~vaaeI~
            {                                                      //~vaacI~
            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4                                   //~1217I~//~1221R~
//              if (ctr>=PAIRCTR)                                  //~vaacI~//~vaaeR~
	            	ctrMeld3++;                                    //~vaacI~
            }                                                      //~vaacI~
            else                                                   //~1217I~
			if (itsHand[pos+1]!=0 && itsHand[pos+2]!=0 && itsHand[pos+3]!=0)//~vafrI~
            {                                                      //~vafrI~
        		if (Dump.Y) Dump.println("RADSmart.chkChanta tanyaoMeld exist at pos="+(pos+1));//~vafrI~
            }                                                      //~vafrI~
            else                                                   //~vafrI~
//      	if (ctr==1)                                            //~1217I~//~vaaeR~
        	if (ctr>0)                                             //~vaaeI~
            {                                                      //~1217I~
				if (itsHand[pos+1]!=0 && itsHand[pos+2]!=0)        //~1217I~//~1307R~
                {                                                  //~vaacI~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4    //~vaacI~
	            	ctrMeld3++;                                    //~1307R~
                }                                                  //~vaacI~
                else                                               //~1307I~
				if (itsHand[pos+1]!=0 || itsHand[pos+2]!=0)        //~1307I~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;   //~1307I~
                else                                               //~1217I~
        		if (ctr==2)    //pair                              //~vafgI~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;   //~vafgI~
                else                                               //~vafgI~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT1;  //1;                                    //~1217I~//~1221R~
            }                                                      //~1217I~
            else                                                   //~vafgI~
				if (itsHand[pos+1]!=0 && itsHand[pos+2]!=0 && itsHand[pos+3]==0)//~vafgR~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;   //~vafgI~
//          int ctrTanyao=itsHand[pos+TN4]+itsHand[pos+TN5]+itsHand[pos+TN6];//~vagsI~//~vainR~
            ctrTanyao+=itsHand[pos+TN4]+itsHand[pos+TN5]+itsHand[pos+TN6];//~vainI~
            if (itsHand[pos+TN3]!=0 && itsHand[pos+TN1]==0 && itsHand[pos+TN2]==0)//~vainI~
            	ctrTanyao+=itsHand[pos+TN3];                       //~vainI~
            if (itsHand[pos+TN7]!=0 && itsHand[pos+TN8]==0 && itsHand[pos+TN9]==0)//~vainI~
            	ctrTanyao+=itsHand[pos+TN7];                       //~vainI~
        	if (Dump.Y) Dump.println("RADSmart.chkChanta ctrTanyaoTile="+ctrTanyao+",shanten="+Pshanten);//~vainI~
            if (ctrTanyao>Pshanten)                                //~vagsI~
            {                                                      //~vagsI~
        		if (Dump.Y) Dump.println("RADSmart.chkChanta set False by ctrTanyao="+ctrTanyao+">shanten="+Pshanten);//~vagsI~
                swManyTanyaoTile=true;                             //~vagsI~
                break;                                             //~vagsI~
            }                                                      //~vagsI~
        	int pos2=pos+TN9;                        //~1217I~     //~1307R~
        	ctr=itsHand[pos2];                                     //~1217I~
//      	if (ctr>1)                                             //~1217I~//~vaaeR~
            if (ctr>=PAIRCTR)                                      //~vaaeI~
            {                                                      //~vaaeI~
            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;                                        //~1217I~//~1221R~
	            ctrMeld3++;                                        //~vaaeI~
            }                                                      //~vaaeI~
            else                                                   //~1217I~
			if (itsHand[pos2-1]!=0 && itsHand[pos2-2]!=0 && itsHand[pos2-3]!=0)//~vafrR~
            {                                                      //~vafrI~
        		if (Dump.Y) Dump.println("RADSmart.chkChanta tanyaoMeld exist at pos="+(pos2-3));//~vafrR~
            }                                                      //~vafrI~
            else                                                   //~vafrI~
//      	if (ctr==1)                                            //~1217I~//~vaaeR~
        	if (ctr>0)                                             //~vaaeI~
            {                                                      //~1217I~
        		if (itsHand[pos2-1]!=0 && itsHand[pos2-2]!=0)      //~1217R~//~1307R~
                {                                                  //~vaacI~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4    //~vaacI~
	            	ctrMeld3++;                                    //~1307I~
                }                                                  //~vaacI~
                else                                               //~1217I~
        		if (itsHand[pos2-1]!=0 || itsHand[pos2-2]!=0)      //~1307I~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;   //~1307I~
                else                                               //~1307I~
        		if (ctr==2)    //pair                              //~vafgR~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;   //~vafgR~
                else                                               //~vafgR~
//              	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2;  //1;                                    //~1217I~//~1221R~//~vaaeR~
                	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT1;  //1;  //~vaaeI~
            }                                                      //~1217I~
            else                                                   //~vafgI~
        		if (itsHand[pos2-1]!=0 && itsHand[pos2-2]!=0 &&  itsHand[pos2-3]==0)//~vafgR~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;   //~vafgI~
        }                                                          //~1217I~
//      ctrMeld+=itsStatHand[CSI_WORD_DUP]/2*HV_INTENT_CHANTA_MELD_WEIGHT2;//~1221R~//~vaacR~
     if (!swManyTanyaoTile)                                        //~vagsI~
     {                                                             //~vagsI~
        for (int pos=OFFS_WORDTILE;pos<CTR_TILETYPE;pos++)         //~vaacI~
        {                                                          //~vaacI~
        	ctr=itsHand[pos];                                      //~vaacI~
        	if (ctr>1)                                             //~vaacI~
            {                                                      //~vaacI~
            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4        //~vaacI~
                if (ctr>=PAIRCTR)                                  //~vaacI~
	            	ctrMeld3++;                                    //~vaacI~
            }                                                      //~vaacI~
        }                                                          //~vaacI~
//      if (ctrMeld3!=0 && (ctrMeld/HV_INTENT_CHANTA_MELD_WEIGHT2+PctrPair)>=HV_INTENT_CHANTA_MELD)     //intent chanta if candidate>=5//~1217R~//~1221R~//~1307R~//~vaacR~
        if (ctrMeld3!=0 && (ctrMeld/HV_INTENT_CHANTA_MELD_WEIGHT2)>=HV_INTENT_CHANTA_MELD)     //intent chanta if ctrMeld/4=candidate>=4//~vaacI~
        {                                                          //~1217I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1217I~
                                    UView.showToastLong("RADSmart Set intent CHANTA eswn="+eswnDiscard);//~1217I~//~1223R~
	        if (Dump.Y) Dump.println("RADSmart.chkChanta @@@@ setIntent eswn="+eswnDiscard);//~1217I~//~1219R~
            rc=true;                                               //~1217I~
        }                                                          //~1217I~
      }                                                            //~vagsI~
        if (Dump.Y) Dump.println("RADSmart.chkChanta itsHand="+Utils.toString(itsHand,9));//~1217I~//~1305R~
        if (Dump.Y) Dump.println("RADSmart.chkChanta itsStatHand="+Utils.toString(itsStatHand));//~1307I~
        if (Dump.Y) Dump.println("RADSmart.chkChanta rc="+rc+",swManyTanyaoTile="+swManyTanyaoTile+",ctrMeld="+ctrMeld+",ctrMeld3="+ctrMeld3);//~1217I~//~1307R~//~vagsR~
        return rc;                                                 //~1217I~
    }                                                              //~1217I~
    //***********************************************************************//~vaaeI~
    //*count chanta Meld and honor tile pair                       //~vagtI~
    //***********************************************************************//~vagtI~
//  private int getCtrChantaInHand()                               //~vaaeI~//~vagtR~
    private int getCtrChantaInHand(int Peswn)                      //~vagtI~
    {                                                              //~vaaeI~
    	int ctr,ctrMeld3=0;                                        //~vaaeR~
        for (int pos=0;pos<OFFS_WORDTILE;pos+=CTR_NUMBER_TILE)     //~vaaeI~
        {                                                          //~vaaeI~
        	ctr=itsHand[pos];                                      //~vaaeI~
            if (ctr>=PAIRCTR)                                      //~vaaeI~
	            ctrMeld3++;                                        //~vaaeR~
            else                                                   //~vaaeI~
        	if (ctr>0)                                             //~vaaeI~
            {                                                      //~vaaeI~
				if (itsHand[pos+1]!=0 && itsHand[pos+2]!=0)        //~vaaeI~
	            	ctrMeld3++;                                    //~vaaeI~
            }                                                      //~vaaeI~
        	int pos2=pos+TN9;                                      //~vaaeI~
        	ctr=itsHand[pos2];                                     //~vaaeI~
            if (ctr>=PAIRCTR)                                      //~vaaeI~
	            ctrMeld3++;                                        //~vaaeI~
            else                                                   //~vaaeI~
        	if (ctr>0)                                             //~vaaeI~
            {                                                      //~vaaeI~
        		if (itsHand[pos2-1]!=0 && itsHand[pos2-2]!=0)      //~vaaeI~
	            	ctrMeld3++;                                    //~vaaeI~
            }                                                      //~vaaeI~
        }                                                          //~vaaeI~
        for (int pos=OFFS_WORDTILE;pos<CTR_TILETYPE;pos++)         //~vaaeI~
        {                                                          //~vaaeI~
        	ctr=itsHand[pos];                                      //~vaaeI~
            if (ctr>=PAIRCTR)                                      //~vaaeI~
	            ctrMeld3++;                                        //~vaaeR~
            else                                                   //~vagtI~
            if (ctr==PAIRCTR-1)                                    //~vagtI~
            	if (RAUtils.chkValueWordTile(pos,Peswn)>0)         //~vagtI~
                	ctrMeld3++;                                    //~vagtI~
        }                                                          //~vaaeI~
        if (Dump.Y) Dump.println("RADSmart.getCtrChantaInHand itsHand="+Utils.toString(itsHand,9));//~vaaeR~
        if (Dump.Y) Dump.println("RADSmart.getCtrChantaInHand rc="+ctrMeld3+",eswn="+eswnDiscard);//~vaaeR~
        return ctrMeld3;                                           //~vaaeR~
    }                                                              //~vaaeI~
    //***********************************************************************//~vaaCI~
    private boolean chkChanta7Pair(int PctrPair)                   //~vaaCI~
    {                                                              //~vaaCI~
    	boolean rc=false;                                          //~vaaCI~
    	int ctr,ctrPairChanta=0,ctrPairTanyao=0,ctrSingleChanta=0; //~vaaCI~
	    boolean sw7PairKan=RS.sw7PairKan;                          //~vaaCI~
        if (Dump.Y) Dump.println("RADSmart.chkChanta7Pair eswn="+eswnDiscard+",ctrPair="+PctrPair+",sw7PairKan="+sw7PairKan);//~vaaCI~
        for (int pos=0;pos<CTR_TILETYPE;pos++)                     //~vaaCI~
        {                                                          //~vaaCI~
        	ctr=itsHand[pos];                                      //~vaaCI~
            if (ctr==0)                                            //~vaaCI~
            	continue;                                          //~vaaCI~
            boolean swChanta=!RAUtils.isTanyaoTile(pos);           //~vaaCI~
            switch (ctr)                                           //~vaaCI~
            {                                                      //~vaaCI~
            case 1:                                                //~vaaCI~
            	if (swChanta)                                      //~vaaCI~
                	ctrSingleChanta++;                             //~vaaCI~
            	break;                                             //~vaaCI~
            case 4:                                                //~vaaCI~
	        	if (sw7PairKan)                                    //~vaaCI~
	            	if (swChanta)                                  //~vaaCI~
    	            	ctrPairChanta+=2;                          //~vaaCI~
                    else                                           //~vaaCI~
    	            	ctrPairTanyao+=2;                          //~vaaCI~
                else                                               //~vaaCI~
	            	if (swChanta)                                  //~vaaCI~
    	            	ctrPairChanta++;                           //~vaaCI~
                    else                                           //~vaaCI~
    	            	ctrPairTanyao++;                           //~vaaCI~
            	break;                                             //~vaaCI~
            default:    //2/3                                      //~vaaCI~
                if (swChanta)                                      //~vaaCI~
                    ctrPairChanta++;                               //~vaaCI~
                else                                               //~vaaCI~
                    ctrPairTanyao++;                               //~vaaCI~
            }                                                      //~vaaCI~
        }                                                          //~vaaCI~
        if (ctrPairTanyao==0)                                      //~vaaCI~
        	if (ctrPairChanta>=HV_INTENT_CTR_CHANTA_7PAIR           //4//~vaaCI~
        	&&  ctrSingleChanta>=HV_INTENT_CTR_CHANTA_7PAIR_SINGLE) //2//~vaaCI~
            	rc=true;                                           //~vaaCI~
            else                                                   //~vaizI~
        	if (ctrPairChanta>HV_INTENT_CTR_CHANTA_7PAIR)           //>4; 5 or 6//~vaizI~
            	rc=true;                                           //~vaizI~
        if (Dump.Y) Dump.println("RADSmart.chkChanta7Pair itsHand="+Utils.toString(itsHand,9));//~vaaCI~//~vaizR~
        if (Dump.Y) Dump.println("RADSmart.chkChanta7Pair rc="+rc+",ctrPairTanyao="+ctrPairTanyao+",ctrPairChanta="+ctrPairChanta+",ctrSingleChanta="+ctrSingleChanta);//~vaaCI~//~vaizR~
        return rc;                                                 //~vaaCI~
    }                                                              //~vaaCI~
    //***********************************************************************//~1221I~
    //*chk complete chanta meld                                    //~1221I~
    //*rc:true if chanta earth or chanta meld in hand>=2           //~vafeI~
    //***********************************************************************//~1221I~
    private boolean chkChantaMeldForTanyao()           //~1221I~
    {                                                              //~1221I~
        if (Dump.Y) Dump.println("RADSmart.chkChantaMeldForTanyao eswn="+eswnDiscard);//~1221I~
        if (!RS.RSP[eswnDiscard].isPairTanyaoAllOrNoPair())        //~1221I~
//          return false;                                          //~1221I~//~vafeR~
            return true;  //ctrPair!=0 && PS_CHANTA!=0             //~vafeI~
        int ctrMeld=0;                                             //~1221I~
        for (int pos=0;pos<OFFS_WORDTILE;pos+=CTR_NUMBER_TILE)     //~1221I~
        {                                                          //~1221I~
        	int ctr=itsHand[pos];                                      //~1221I~
        	if (ctr>=3)                                            //~1221I~
            	ctrMeld++;                                         //~1221I~
            else                                                   //~1221I~
        	if (ctr>0)                                             //~1221I~
            {                                                      //~1221I~
				if (itsHand[pos+1]!=0 || itsHand[pos+2]!=0)        //~1221I~
                	ctrMeld++;                                     //~1221I~
            }                                                      //~1221I~
        	int pos2=pos+CTR_NUMBER_TILE-1;                        //~1221I~
        	ctr=itsHand[pos2];                                     //~1221I~
        	if (ctr>=3)                                           //~1221I~
            	ctrMeld++;                                         //~1221I~
            else                                                   //~1221I~
        	if (ctr>0)                                             //~1221I~
            {                                                      //~1221I~
        		if (itsHand[pos2-1]!=0 || itsHand[pos2-2]!=0)      //~1221I~
	            	ctrMeld++;                                     //~1221I~
            }                                                      //~1221I~
        }                                                          //~1221I~
        ctrMeld+=itsStatHand[CSI_WORD_DUP]/2;                       //~1221I~
//      boolean rc=ctrMeld<=HV_INTENT_TANYAO_MAX_CHANTA_MELD;	//2//~1221I~//~vafeR~
        boolean rc=ctrMeld>=HV_INTENT_TANYAO_MAX_CHANTA_MELD;	//true chanta by chanta meld>=2//~vafeI~
        if (Dump.Y) Dump.println("RADSmart.chkChantaMeldForTanyao rc="+rc+",ctrMeld="+ctrMeld);//~1221I~
        return rc;                                                 //~1221I~
    }                                                              //~1221I~
    //***********************************************************************//~1220I~
    //*from RADSOther                                              //~1220I~
    //***********************************************************************//~1220I~
    public int getCtrDoraEarth(int Peswn/*otherPlayer*/)           //~1220I~
    {                                                              //~1220I~
        int ctr=0;                                                 //~1220I~
        int player=RS.RSP[Peswn].player;                           //~1220I~
        TileData[][] tdss=AG.aPlayers.getEarth(player);        //~1220I~
        if (tdss!=null)                                            //~1220I~
        {                                                          //~1220I~
            for (TileData[] tds:tdss)                              //~1220I~
            {                                                      //~1220I~
                if (tds==null)                                     //~1220R~
                    break;                                         //~1220I~
                if (Dump.Y) Dump.println("RoundStat.getCtrDoraEarth earth tds="+TileData.toString(tds));//~1220I~
                for (TileData td:tds)                              //~1220I~
                {                                                  //~1220I~
                    if (td==null)                                  //~1220I~
                        break;                                     //~1220I~
                    int pos=RAUtils.getPosTile(td);                //~1220I~
                    if (isDoraOpen(pos))                           //~1220I~
                        ctr++;                                     //~1220I~
                    if (td.isRed5())                               //~1220I~
                        ctr++;                                     //~1220I~
                }                                                  //~1220I~
            }                                                      //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RoundStat.getCtrDoraEarth ctr="+ctr+",eswn="+Peswn+",player="+player);//~1220I~
        return ctr;                                                 //~1220I~
    }                                                              //~1220I~
    //***********************************************************************//~1220I~
    public int getHanEarthWord(int Peswn/*otherPlayer*/)           //~1220I~
    {                                                              //~1220I~
        int ctr=0;                                                 //~1220I~
        int player=RS.RSP[Peswn].player;                           //~1220I~
        TileData[][] tdss=AG.aPlayers.getEarth(player);        //~1220I~
        if (tdss!=null)                                            //~1220I~
        {                                                          //~1220I~
            for (TileData[] tds:tdss)                              //~1220I~
            {                                                      //~1220I~
                if (tds==null)                                     //~1220I~
                    break;                                         //~1220I~
                if (Dump.Y) Dump.println("RoundStat.getHanEarthWord earth tds="+TileData.toString(tds));//~1220I~
                TileData td=tds[0];                                //~1220I~
                if (td.type==TT_JI)                                //~1220I~
                {                                                  //~1220I~
                	ctr+=RAUtils.chkValueWordTile(td,Peswn)/2;     //~1220I~
                }                                                  //~1220I~
            }                                                      //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RoundStat.getHanEarthWord ctr="+ctr+",eswn="+Peswn+",player="+player);//~1220I~
        return ctr;                                                 //~1220I~
    }                                                              //~1220I~
    //***********************************************************************//~1307I~
    //big/small 3dragon                                            //~1307I~
    //***********************************************************************//~1307I~
    private int chkIntent3Dragon()                                 //~1307I~
    {                                                              //~1307I~
        if (Dump.Y) Dump.println("RADSmart.chkIntent3Dragon itsExposed="+Utils.toString(itsExposed,9));//~vaaqI~
        int intent=0,ctr1=0,ctr2=0,ctr3=0,ctr;                     //~1307I~
        ctr3+=itsHand[OFFS_WORDTILE_DRAGON  ]>=3 ? 1 : 0;          //~1307I~
        ctr3+=itsHand[OFFS_WORDTILE_DRAGON+1]>=3 ? 1 : 0;          //~1307I~
        ctr3+=itsHand[OFFS_WORDTILE_DRAGON+2]>=3 ? 1 : 0;          //~1307I~
        ctr2+=itsHand[OFFS_WORDTILE_DRAGON  ]==2 ? 1 : 0;          //~1307I~
        ctr2+=itsHand[OFFS_WORDTILE_DRAGON+1]==2 ? 1 : 0;          //~1307I~
        ctr2+=itsHand[OFFS_WORDTILE_DRAGON+2]==2 ? 1 : 0;          //~1307I~
        ctr1+=itsHand[OFFS_WORDTILE_DRAGON  ]==1 && itsExposed[OFFS_WORDTILE_DRAGON  ]<=2 ? 1:0;//~1307I~
        ctr1+=itsHand[OFFS_WORDTILE_DRAGON+1]==1 && itsExposed[OFFS_WORDTILE_DRAGON+1]<=2 ? 1:0;//~1307I~
        ctr1+=itsHand[OFFS_WORDTILE_DRAGON+2]==1 && itsExposed[OFFS_WORDTILE_DRAGON+2]<=2 ? 1:0;//~1307I~
//      ctr=ctr1+ctr2*2+ctr3*3;                                    //~1307I~//~va8aR~
        int ctr3InHand=ctr3;                                       //~vafuI~
		TileData[][] pairOnEarth=AG.aPlayers.getEarth(playerDiscard);//~1307I~
        for (int ii=0;ii<PAIRS_MAX;ii++)                           //~1307I~
        {                                                          //~1307I~
            TileData[] tds=pairOnEarth[ii];                        //~1307I~
            if (tds==null)                                         //~1307I~
                break;                                             //~1307I~
            if (tds[0].type==TT_JI && tds[0].number>=TT_4ESWN_CTR)  //~1307I~
            	ctr3++;                                            //~1307I~
        }                                                          //~1307I~
        ctr=ctr1+ctr2*2+ctr3*3;                                    //~va8aI~
      if (ctr3InHand!=0)                                           //~vafuM~
      {                                                            //~vafuM~
//      if (ctr>=HV_SET_INTENT_3DRAGON)                                //~1307I~//~va8gR~
        if ((ctr>=HV_SET_INTENT_3DRAGON)                           //~va8gI~
        ||  (ctr==HV_SET_INTENT_3DRAGON-1 && ctr2==2)	//2+2+1    //~va8gI~
        )                                                          //~va8gI~
        {                                                          //~va84I~
        	intent|=INTENT_3DRAGON;	//>=6              //~1307I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~va84I~
                                    UView.showToastLong("RADSmart Set intent 3 Dragon eswn="+eswnDiscard);//~va84I~
        }                                                          //~va84I~
      }                                                            //~vafuI~
        if (Dump.Y) Dump.println("RADSmart.chkIntent3Dragon rc=intent=x"+Integer.toHexString(intent)+",ctr3InHand="+ctr3InHand+",ctr="+ctr+",ctr1="+ctr1+",ctr2="+ctr2+",ctr3="+ctr3);//~1307I~//~vafuR~
        return intent;                                             //~1307I~
    }                                                              //~1307I~
    //*************************************************************//~va8xI~
    private TileData selectTestDiscard(int Pplayer)                //~va8xI~
    {                                                              //~va8xI~
    	TileData[] tds=AG.aPlayers.getHands(Pplayer);              //~va8xI~
        if (Dump.Y) Dump.println("RADSmart.selectTestDiscard tds="+tds.toString());//~va8xI~
    	TileData tdLow=null;                                       //~va8xI~
        int low=16;                                                //~va8xI~
        for (TileData td:tds)                                      //~va8xI~
        {                                                          //~va8xI~
			int order=TileData.getTestSelectionOrder(td);              //~va8xI~
            if (order!=0 && order<low)                             //~va8xI~
            {                                                      //~va8xI~
            	low=order;                                         //~va8xI~
                tdLow=td;                                          //~va8xI~
            }                                                      //~va8xI~
        }                                                          //~va8xI~
        if (Dump.Y) Dump.println("RADSmart.selectTestDiscard tdLow="+Utils.toString(tdLow));//~va8xI~
        return tdLow;                                              //~va8xI~
    }                                                              //~va8xI~
    //*************************************************************//~vabkR~
    private boolean chkSeqMeld(int[] PitsHand)                     //~vabkR~
    {                                                              //~vabkR~
        int[] itsH=itsHandWork;                                    //~vabkI~
	    System.arraycopy(itsHand,0,itsH,0,CTR_TILETYPE);           //~vabkI~
        if (Dump.Y) Dump.println("RADSmart.chkSeqMeld itsH="+Utils.toString(itsH,9));//~vabkR~
        int ctr=0;                                                 //~vabkR~
        for (int ii=0;ii<OFFS_WORDTILE;ii++)                       //~vabkR~
        {                                                          //~vabkR~
        	if (itsH[ii]<=0)                                       //~vabkR~
                continue;                                          //~vabkR~
            int num=ii%CTR_NUMBER_TILE;                            //~vabkR~
            if (num>TN7)                                           //~vabkR~
                continue;                                          //~vabkR~
            if (!(itsH[ii+1]>0 && itsH[ii+2]>0))                   //~vabkR~
            	continue;                                          //~vabkR~
//          if (itsH[ii]+itsH[ii+1]+itsH[ii+2])<=4) //at least 2 single//~vabkR~
            ctr++;                                                 //~vabkR~
            itsH[ii]--; itsH[ii+1]--; itsH[ii+2]--;                //~vabkR~
            ii--;                                                  //~vabkR~
        }                                                          //~vabkR~
        boolean rc=ctr>=2;                                         //~vabkR~
        if (Dump.Y) Dump.println("RADSmart.chkSeqMeld rc="+rc+",ctr="+ctr);//~vabkR~
        return rc;                                                 //~vabkR~
    }                                                              //~vabkR~
}//class RADSmart
