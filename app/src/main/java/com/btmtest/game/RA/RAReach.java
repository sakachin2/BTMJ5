//*CID://+vapxR~: update#= 297;                                    //~vapxR~
//**********************************************************************
//2022/08/11 vaq1 (Bug)Errmsg for multiple for 2han constraint condition//~vaq1R~
//2022/08/06 vapx add Psuedo-Tennpai:No option                     //~vapxI~
//2022/08/04 vapu PsuedoTenpai;simplify 0han ok or not(allow kataagari,fix last)//~vapuI~
//2022/08/04 vapt PsuedoTenpai;allow kataagari,fix yaku err if furiten OK//~vaptI~
//2022/08/03 vapq Psuedo tenpai; change 2-han constraint option to require 2-han regardless 0-han OK for 1-han constraint option.//~vapqI~
//2022/08/03 vapp Psuedo tenpai; drop allNo(chk tenpai required for repeat/next round) and apply Fix Yaku,Kata-Agari err//~vappI~
//2022/08/03 vapn allow furiten reach err as PsuedoTenpay          //~vapnI~
//2022/07/30 vapk implements keishiki tenpai                       //~vapkI~
//2022/07/30 vapj 7pair reach, select non valueword                //~vapjI~
//2022/07/29 vapg reach by 7pair, skip reach if waiting dora       //~vapgI~
//2022/07/24 vap8 furiten reach msg; change for furitenReachOK to notify dicardable and allow win by take//~vap8I~
//2022/07/24 vap6 OpenReach Robot,chk furiten to allow to discard  //~vap6I~
//2022/01/25 vajd (bug)FuritenReach err was not set for reach on client//~vajdI~
//2022/01/19 vaj5 Not ronable when furiten even if taken if furitenreachoption=No//~vaj5I~
//2022/01/19 vaj4 issue waring for furiten reach even if not reject option//~vaj2I~
//2022/01/19 vaj2 at Reach,wait same color  for 7pair with intent samecolor regardless dora//~vaj2I~
//2022/01/17 vaiy at Reach,wait chanta tile for 7pair with intent chanta regardless dora//~vaiyI~
//2022/01/17 vaix at Reach,wait tanyao tile for 7pair with intent tanyao except dora//~vaixI~
//2021/11/22 vah3 add Furiten reach reject option                  //~vah3I~
//2021/11/16 vagw (Bug)Not checked as Furiten if exaused           //~vagwI~
//2021/10/26 vaf8 skip reach if PAO status exist                   //~vaf8I~
//2021/07/29 vabm skip reach for shanpon to change ryanmen         //~vabmI~
//2021/07/25 vab8 wait reach kanchan if early eveif wintline=4     //~vab8I~
//2021/07/25 vab7 skip reach if other called open reach            //~vab7I~
//2021/07/14 vaaK red5 dora chk error; At getvalue from TryNext chkRedTile count tile of try discard. also for RAReach//~vaaKI~
//2021/07/14 vaaJ Call Pon/Chii when shanten up to 1 and penchan/kanchan//~vaaJI~
//2021/07/10 vaaA (Bug)skip reach by evaluateWinlist, ignore swSkipReach of one  discard candidate if another is not skip.//~vaaAI~
//2021/07/10 vaaz select word tile to discard if winlist=1 for reach//~vaazI~//~vaaAR~//~vaaJR~
//2021/07/09 vaav skip reach for kanchan but not for penchan       //~vaavI~
//2021/07/06 vaar Additional to vaai(call pon/chii if become shanten 0), chk wintile ctr//~vaarI~
//2021/07/05 vaan (Bug)handValue of maxHan should be inclreased for discard priority up//~vaanI~
//2021/07/03 vaaj (Bug)select large han/point even if amt is same(hanMax was not set)//~vaajI~
//2021/06/19 va9h avoid reach when winList=1 except 7pair at eraly timimg//~va9hI~
//2021/06/17 va9d allow reach for the case empty winning tile(getWinlist returns empty for chk furiten)//~va9dI~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/04/20 va8j KataAgari chk for also Human Take in PlayAloneNotifyMode//~va8jI~
//2021/04/17 va8d (Bug)evaluateWinlist has to consider doubleReach //~va8dI~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/01/07 va60 CalcShanten
//**********************************************************************
package com.btmtest.game.RA;
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Robot;
import com.btmtest.game.TileData;
import com.btmtest.game.gv.GMsg;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.RA.RARon.*;
import static com.btmtest.game.Tiles.*;

import java.util.Arrays;

//********************************************************************************************
//player:position of each player on the device; You area always 0(Hands is show at bottom)
//********************************************************************************************
public class RAReach
{
    private RoundStat RS;
    private RADSmart RADS;
    private boolean[] btsWinWork =new boolean[CTR_TILETYPE];
    private int[]     itsWinWork =new int[CTR_TILETYPE];
    private int[]     itsHandWork=new int[CTR_TILETYPE];
    private int[]     itsWait1=new int[6];         //tanki list posDiscard/PosWait/WaitTileTotal//~vaazI~
    private int       ctrWait1;                                    //~vaazI~
    private int  evaluateWinListPosWait1,evaluateWinListTotal;          //~vaazI~
    private int[]     itsHandValue,itsHand,itsHandPos;             //~1122R~
    private int[]     itsReachPos= new int[HANDCTR_TAKEN];//~1122I~
    private int[]     itsHanAmt= new int[HANDCTR_TAKEN];           //~vaajI~
    private int       ctrReachPos;                                 //~1122I~
    public  int ctrWinTotal;    //summ of tilectr of wintile       //~1310R~
    private int ctrWinList;	//type ctr of wintile                                        //~1224I~//~1310I~
    private int playerReach,hanRequired,hanAdd,posTaken,ctrHand,playerEswn;           //~1122R~
    private boolean swDoReach;
    private int hanMax;                                            //~1122I~
    private boolean swSkipReach;                                   //~1215I~
    private int hanExceptDora;                                     //~1219I~
    private boolean swNoChkEmpty;                                  //~va9dI~
    private int amtRonValue,amtMax,amtHanMax;                      //~vaajI~
    private boolean swErrFuritenReach;                                 //~vaj5I~
    private boolean swEmptyOK,swFuritenOK,swRank0OK,swRank2Rank0OK;//~vapkR~
    private boolean swRank2Rank1OK;                                //~vapuI~
    private boolean swFixMultiOK;                                  //~vaptI~
                                                                   //~vapkI~
    public int errPsuedoPending;                                   //~vapkI~
    public static final int ERR_PSUEDO_PENDING_NOT_SHANTEN0     =1;//~vapkR~
    public static final int ERR_PSUEDO_PENDING_EMPTY            =2;//~vapkR~
    public static final int ERR_PSUEDO_PENDING_FURITEN          =3;//~vapkR~
    public static final int ERR_PSUEDO_PENDING_FURITEN_REACH    =4;//~vapkR~
    public static final int ERR_PSUEDO_PENDING_FIX1             =5;//~vapkR~
    public static final int ERR_PSUEDO_PENDING_FIX2             =6;//~vapkR~
//  public static final int ERR_PSUEDO_PENDING_USER             =7;//~vapqR~//~vapxR~
    public static final int ERR_PSUEDO_PENDING_MULTIPLE         =7; //kataagari err//~vapqI~
    public static final int ERR_PSUEDO_PENDING_FIX              =8; //Fix 1st/middle err//~vapqI~
//  public static final int ERR_PSUEDO_PENDING_NO_OPTION        =9;//~vapkR~//~vapxR~
    public static final int ERR_PSUEDO_PENDING_UNKNOWN          =9;//~vapxI~
    public static final int ERR_PSUEDO_PENDING_MULTIPLE_FIX2    =10; //kataagari err//~vapxI~
    private int errMultiWait;                                      //~vaptI~
//*************************
	public RAReach()
    {
        if (Dump.Y) Dump.println("RAReach.Constructor");
        AG.aRAReach=this;
        init();
    }
    //*********************************************************
    private void  init()
    {
    	RS=AG.aRoundStat;
    	RADS=AG.aRADSmart;
    }
    //*********************************************************
    //*from RADSmart.selectSamrt
    //*under shanten=0, not yet reach called
    //*issue reach if it is proper
    //*return ctr of reach pos                                     //~1122R~
    //*********************************************************
    public int callReach(int Pplayer,int PplayerEswn,int PposTaken,int[] PitsHand,int[] PitsHandPos,int PctrHand,int[] PitsHandValue)//~1122R~
    {
        if (Dump.Y) Dump.println("RAReach.callReach player="+Pplayer+",eswn="+PplayerEswn+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1206R~
        playerReach=Pplayer;                                       //~1122M~
        ctrHand=PctrHand;                                          //~1122I~
        posTaken=PposTaken;
        playerEswn=PplayerEswn;//~1122I~
        itsHandValue=PitsHandValue; itsHand=PitsHand; itsHandPos=PitsHandPos;//~1122R~
                                                                   //~1122I~
    	int hanMaxMax=callReach();                                 //~1122R~
                                                                   //~1122I~
        itsHandValue=null; itsHand=null; itsHandPos=null;	//gc   //~1122R~
        if (Dump.Y) Dump.println("RAReach.callReach ctrReachPos="+ctrReachPos);//~1122R~
        return hanMaxMax;                                          //~1122R~
    }
    //*********************************************************    //~1122I~
    private int callReach()                                        //~1122R~
    {                                                              //~1122I~
        if (Dump.Y) Dump.println("RAReach.callReach");             //~1122I~
        ctrReachPos=0;                                             //~1122I~
//      if (!RS.RSP[playerEswn].swAllInHand)	//allow no call or ankan only//~1122M~//~1130R~
        if (!RAUtils.isAllInHand(playerEswn))	//allow no call or ankan only//~1130I~
        {                                                          //~1122M~
	        if (Dump.Y) Dump.println("RAReach.callReach return 0; not all in hand");//~1122M~//~vaf8R~
        	return 0;                                              //~1122M~
        }                                                          //~1122M~
        if (!RS.chkPaoForReach(playerEswn))                        //~vaf8I~
        {                                                          //~vaf8I~
	        if (Dump.Y) Dump.println("RAReach.callReach return 0; avoid Pao");//~vaf8I~
        	return 0;                                              //~vaf8I~
        }                                                          //~vaf8I~
        swDoReach=true;                                            //~1122I~
        swSkipReach=false;                                         //~1311M~
//        int remain=RAUtils.getCtrRemain();                         //~1122I~//~1311R~
//        if (remain<HV_AVOID_REACH_BY_REMAINING_CTR)    //<3*4 remaining tile            //~1122I~//~1216R~//~1311R~
//        {                                                          //~1122I~//~1311R~
////          swDoReach=false;    //yet select discard               //~1122I~//~1309R~//~1311R~
//            swSkipReach=true;   //yet select discard               //~1309I~//~1311R~
//            if (Dump.Y) Dump.println("RAReach.callReach swSkipReach=true by remain="+remain);//~1122I~//~1311R~
//        }                                                          //~1122I~//~1311R~
        hanAdd=AG.aPlayers.is1stTake() ? 2 : 1;		//dounble reach 2 han and reach 1 han//~1122I~
        hanRequired=RS.swFix2 ? 2 : 1;                             //~1122I~
                                                                   //~1122I~
    	int hanMaxMax=selectDiscard();                             //~1122R~
                                                                   //~1122I~
        if (Dump.Y) Dump.println("RAReach.callReach ctrReachPos="+ctrReachPos+",hanMaxMax="+hanMaxMax);//~1122R~//~1310R~
        return hanMaxMax;                                          //~1122R~
    }                                                              //~1122I~
    //*********************************************************    //~1122I~
    //*after tile selected by evaluation, issue reach if discard selected//~1122I~
    //*********************************************************    //~1122I~
    public boolean callReach(int Pplayer, int PplayerEswn, TileData PtdDiscard)     //~1122I~
    {                                                              //~1122I~
    	boolean rc=false;                                          //~1122I~
        if (Dump.Y) Dump.println("RAReach.callReach player="+Pplayer+",eswn="+PplayerEswn+",ctrReachPos="+ctrReachPos+",itsReachpos="+Arrays.toString(itsReachPos)+",tdDiscard="+TileData.toString(PtdDiscard));//~1122R~
        int pos=RAUtils.getPosTile(PtdDiscard);
        for (int ii=0;ii<ctrReachPos;ii++)                         //~1122I~
        {                                                          //~1122I~
        	if (itsReachPos[ii]==pos)    //tdDiscard may not selected from itsReachPos by the reason of other playerMark      //~1122I~//~1222R~
            {                                                      //~1122I~
                issueReach(Pplayer,PplayerEswn,pos);              //~1122I~
                rc=true;                                           //~1122I~
                break;                                             //~1122I~
            }                                                      //~1122I~
        }                                                          //~1122I~
        if (Dump.Y) Dump.println("RAReach.callReach rc="+rc);      //~1122I~
        return rc;                                         //~1122I~
    }                                                              //~1122I~
    //*********************************************************
    //*return ctr discard pos with reach(no furiten)               //~1122R~
    //*********************************************************
    private int selectDiscard()                                    //~1122R~
    {
        if (Dump.Y) Dump.println("RAReach.selectDiscard playerEswn="+playerEswn+",ctrHand="+ctrHand+",itsHand="+Utils.toString(itsHand,9));//~vaazR~
	    if (RS.getCtrOtherReachOpen(playerEswn)>0)                 //~vab7R~
        {                                                          //~vab7R~
            swSkipReach=true;   //yet select discard               //~vab7R~
            if (Dump.Y) Dump.println("RAReach.selectDiscard swSkipReach=true by other ReachOpen");//~vab7R~
        }                                                          //~vab7R~
        int remain=RAUtils.getCtrRemain();                         //~1311I~
        if (remain<HV_AVOID_REACH_BY_REMAINING_CTR)    //<3*4 remaining tile//~1311I~
        {                                                          //~1311I~
            swSkipReach=true;   //yet select discard               //~1311I~
            if (Dump.Y) Dump.println("RAReach.selectDiscard swSkipReach=true by remain="+remain);//~1311I~
        }                                                          //~1311I~
        int hanMaxMax=0;                                           //~1122I~
        boolean swDiscardableAll=RS.isDiscardableAll();            //~1126I~
        int posOld=-1;                                             //~1216I~
        Arrays.fill(itsHanAmt,0);                                  //~vaajI~
        ctrWait1=0;                                                //~vaazI~
        for (int ii=0;ii<ctrHand;ii++)                             //~1122R~
        {
        	int pos=itsHandPos[ii];                                //~1122I~
            if (posOld==pos)                                       //~1216I~
            	continue;                                          //~1216I~
            posOld=pos;                                            //~1216I~
//          if (RS.chkDiscardable(playerEswn,pos)!=null)	//chk pao openreach//~1122R~//~1126R~
            if (!swDiscardableAll && RS.chkDiscardable(playerEswn,pos)!=null)	//chk pao openreach//~1126I~
            	continue;
            itsHand[pos]--;                                       //~1122R~
            TileData tdRed5=AG.aRADSEval.setDropRed5(playerReach,pos);//~vaaKR~
            boolean svSwSkipReach=swSkipReach;                     //~vaaAI~
            swSkipReach=false;	//reset for evaluateWinList        //~vaaAI~
	    	int v=evaluateWinList(playerEswn,pos,itsHand,ctrHand-1); //output to itsWinWork and set swSkipReach//~1122R~//~1215R~
            swSkipReach=svSwSkipReach;	//ignore setting by EvaluateWinList, V<0 if swSkipReach at evaluateWinList//~vaaAI~
            itsHand[pos]++;                                       //~1122I~
			AG.aRADSEval.resetDropRed5(tdRed5);                    //~vaaKI~
            if (v>0)                                               //~1122I~
            {                                                      //~1122I~
//          	if (swDoReach)                                     //~1122I~//~1215R~
            	if (swDoReach && !swSkipReach)                     //~1215I~
                {                                                  //~1122I~
        			if (Dump.Y) Dump.println("RAReach.selectDiscard doReach pos="+pos+",v="+v+",old itsHandValue["+ii+"]="+itsHandValue[ii]);//~1218I~//~1219R~
	                itsHandValue[ii]+=DV_REACH+v;	//		         = 3000000;		//discard for reach//~1122R~//~1216R~//~1218R~
        			if (Dump.Y) Dump.println("RAReach.selectDiscard doReach add DV_REACH pos="+pos+",v="+v+",new itsHandValue["+ii+"]="+itsHandValue[ii]);//~vapgI~
	                itsHandValue[ii]+=AG.aRADSEval.adjustByTileForReach(playerEswn,pos,ii,itsHand,ctrHand);	   //		//discard for reach//~va70R~
        			if (Dump.Y) Dump.println("RAReach.selectDiscard doReach adjustByTileForReach new pos="+pos+",hanMAxMax="+hanMaxMax+",hanMax="+hanMax+",amtMax="+amtMax+",itsHandValue["+ii+"]="+itsHandValue[ii]);//~1218I~//~1219R~//~vaajR~//~vapgR~
                    itsReachPos[ctrReachPos++]=pos;                //~1122R~
                    if (hanMaxMax<hanMax)                          //~1122I~
                    {                                              //~vaazI~
                        hanMaxMax=hanMax;                          //~1122I~
                    }                                              //~vaazI~
                    if (hanMaxMax<=hanMax)                         //~vaajI~
                    {                                              //~vaajI~
                    	if (amtHanMax<amtMax)                      //~vaajI~
                       		amtHanMax=amtMax;                      //~vaajI~
			            if (evaluateWinListPosWait1!=-1)           //~vaazM~
                        {                                          //~vaazM~
//                      	if (ctrWait1<itsWait1.length)    //tanki list//~vapgR~
                        	if (ctrWait1+3<=itsWait1.length)    //tanki list, for safety//~vapgI~
                            {                                      //~vaazM~
                            	itsWait1[ctrWait1++]=ii;	//idx,discard tile cause wait1//~vaazR~
                            	itsWait1[ctrWait1++]=evaluateWinListPosWait1;	//discard tile cause wait1//~vaazM~
                            	itsWait1[ctrWait1++]=evaluateWinListTotal;	//discard tile cause wait1//~vaazM~
        						if (Dump.Y) Dump.println("RAReach.selectDiscard ctrWait1="+ctrWait1+",itsWait1="+Arrays.toString(itsWait1));//~vaazM~//~vabmR~
                            }                                      //~vaazM~
                        }                                          //~vaazM~
                    }                                              //~vaajI~
                    itsHanAmt[ii]=(hanMax<<24)+amtMax;             //~vaajR~
                }                                                  //~1122I~
                else                                               //~1122I~
                {                                                  //~1218I~
        			if (Dump.Y) Dump.println("RAReach.selectDiscard skipReach v="+v+",old itsHandValue["+ii+"]="+itsHandValue[ii]);//~1218I~
	                itsHandValue[ii]+=DV_KEEP_SHANTEN0+v;	//		     = 2000000;		//discard for reach//~1122R~//~1216R~//~1218R~
        			if (Dump.Y) Dump.println("RAReach.selectDiscard skipReach new itsHandValue["+ii+"]="+itsHandValue[ii]);//~1218I~
                }                                                  //~1218I~
            }                                                      //~1122I~
            else                                                   //~1122I~
            if (v<0)	//skip reach                                               //~1122I~//~1216R~
            {                                                      //~1218I~
        		if (Dump.Y) Dump.println("RAReach.selectDiscard skipReach v="+v+",old itsHandValue["+ii+"]="+itsHandValue[ii]);//~1218I~//~1220R~
                itsHandValue[ii]+=DV_KEEP_SHANTEN0-v;	//	        = 2000000;		//discard for reach//~1122R~//~1216R~//~1218R~
        		if (Dump.Y) Dump.println("RAReach.selectDiscard skipReach new itsHandValue["+ii+"]="+itsHandValue[ii]);//~1218I~
            }                                                      //~1218I~
        }
		if (Dump.Y) Dump.println("RAReach.selectDiscard ctrWait1="+ctrWait1);//~vaixI~
        if (ctrWait1==itsWait1.length)                             //~vaazI~
        {                                                          //~vaixI~
//          evaluateWait1();                                       //~vaazR~//~vaixR~
            if (!evaluateWait1(playerEswn))                        //~vaixI~
            {                                                      //~vaixI~
		        if (Dump.Y) Dump.println("RAReach.selectDiscard return 0 by evaluateWait1 rc=false");//~vaixI~
            	return 0;	//skip reach                           //~vaixI~
            }                                                      //~vaixI~
        }                                                          //~vaixI~
        evaluateHanMax(itsHanAmt,(hanMaxMax<<24)+amtHanMax);       //~vaajR~
        if (Dump.Y) Dump.println("RAReach.selectDiscard return swDoReach="+swDoReach+",hanMaxMax="+hanMaxMax+",amtHanMax="+amtHanMax+",ctrReachPos="+ctrReachPos+".itsHandValue="+Utils.toString(itsHandValue,-1,ctrHand));//~1122R~//~1124R~//~1125R~//~1206R~//~vaajR~
        return hanMaxMax;                                          //~1122R~
    }
    //***********************************************************************//~vaazI~
    //*itsWait1: (posDiscard+posWait1)*2                           //~vaazI~
    //*rc=false:skip Reach                                         //~vapgR~
    //***********************************************************************//~vaazI~
//  private void evaluateWait1()                                   //~vaazR~//~vaixR~
    private boolean evaluateWait1(int PplayerEswn)                 //~vaixI~
    {                                                              //~vaazI~
        if (Dump.Y) Dump.println("RAReach.evaluateWait1 ctrWait1="+ctrWait1+",itsWait1="+Arrays.toString(itsWait1));//~vaazR~
        if (Dump.Y) Dump.println("RAReach.evaluateWait1 entry itsHandValue="+Utils.toString(itsHandValue));//~vapgR~
        if (Dump.Y) Dump.println("RAReach.evaluateWait1 entry itsHandPos="+Utils.toString(itsHandPos));//~vapgI~
//      if ((Pintent & INTENT_TANYAO)!=0)   //if tanyao/chanta,rank is up; chk under the same rank//~vaazR~
//      {                                                          //~vaazR~
//          if (Dump.Y) Dump.println("RAReach.evaluateWait1 return by Tanyao intent");//~vaazR~
//      	return;	//select 1/9/ji if not tanyao                  //~vaazR~
//      }                                                          //~vaazR~
        int  totalWait1=itsWait1[2];                              //~vaazI~
        int  totalWait2=itsWait1[5];                              //~vaazI~
//        if (totalWait1==0 || totalWait2==0 || totalWait1!=totalWait2)//~vaazR~//~vaixR~
//        {                                                          //~vaazI~//~vaixR~
//            if (Dump.Y) Dump.println("RAReach.evaluateWait1 return true by totalWait ctr");//~vaazI~//~vaixR~
//            return;                                                //~vaazI~//~vaixR~
//        }                                                          //~vaazI~//~vaixR~
        int idxDiscard1=itsWait1[0];                               //~vaazR~
        int idxDiscard2=itsWait1[3];                               //~vaazR~
        int posWait1=itsWait1[1];                                  //~vaazI~
        int posWait2=itsWait1[4];                                  //~vaazI~
        boolean swTanyao1=RAUtils.isTanyaoTile(posWait1);            //~vaazI~
        boolean swTanyao2=RAUtils.isTanyaoTile(posWait2);            //~vaazI~
        int intent=RS.RSP[PplayerEswn].intent;                 //~vaixI~//~vaj2I~
        if (Dump.Y) Dump.println("RAReach.evaluateWait1 intent="+Integer.toHexString(intent));//~vaixI~//~vaj2I~
	    if (Dump.Y) Dump.println("RAReach.evaluateWait1 posWait1="+posWait1+",swTanyao1="+swTanyao1+",posWait2="+posWait2+",swTanyao2="+swTanyao2);//~vapgR~
	    if (Dump.Y) Dump.println("RAReach.evaluateWait1 idxDiscard1="+idxDiscard1+",idxDiscard2="+idxDiscard2);//~vapgI~
        if (!swTanyao1 && !swTanyao2)                              //~vaixI~
        {                                                          //~vaixI~
          if (!RADS.isDoraOpen(posWait1) && !RADS.isDoraOpen(posWait2))//~vaixI~
          {                                                        //~vaixI~
            if ((intent & INTENT_7PAIR)!=0 && (intent & INTENT_TANYAO)!=0)//~vaixI~
            {                                                      //~vaixI~
	    	    if (Dump.Y) Dump.println("RAReach.evaluateWait1 return false tanyao 7pair and wait1/2 is both not tanyao nand dora");//~vaixI~//~vapgR~
                return false;                                      //~vaixI~
            }                                                      //~vaixI~
          }                                                        //~vaixI~
        }                                                          //~vaixI~
        if (swTanyao1 && swTanyao2)                                //~vaiyR~
        {                                                          //~vaiyR~
//          int intent=RS.RSP[PplayerEswn].intent;                 //~vaiyR~//~vaj2R~
            if ((intent & INTENT_7PAIR)!=0 && (intent & INTENT_CHANTA)!=0)//~vaiyR~
            {                                                      //~vaiyR~
                if (Dump.Y) Dump.println("RAReach.evaluateWait1 return false chanta 7pair and wait1/2 is both tanyao");//~vaiyR~
                return false;                                      //~vaiyR~
            }                                                      //~vaiyR~
        }                                                          //~vaiyR~
        if ((intent & INTENT_7PAIR)!=0 && (intent & INTENT_SAMECOLOR_ANY)!=0)//~vaj2I~
        {                                                          //~vaj2I~
          	if (!RAUtils.isMatchSameColorPos(true,intent,posWait1) && !RAUtils.isMatchSameColorPos(true,intent,posWait2))//~vaj2I~
            {                                                      //~vaj2I~
	          	int ctrOtherColor=RADS.getCtrOtherColorInHand(PplayerEswn,intent);//~vaj2I~
                if (ctrOtherColor==2)                              //~vaj2I~
                {                                                  //~vaj2I~
                	if (Dump.Y) Dump.println("RAReach.evaluateWait1 return false samecolor 7pair and wait1/2 is both other color");//~vaj2I~
                	return false;                                  //~vaj2I~
                }                                                  //~vaj2I~
            }                                                      //~vaj2I~
        }                                                          //~vaj2I~
        if (Dump.Y) Dump.println("RAReach.evaluateWait1 totalWait1="+totalWait1+",totalWait2="+totalWait2);//~vap8I~
//      if (totalWait1==0 || totalWait2==0 || totalWait1!=totalWait2)//~vapgR~
//      {                                                          //~vapgR~
//          if (Dump.Y) Dump.println("RAReach.evaluateWait1 return true(doReach) by totalWait ctr");//~vapgR~
//          return true;                                           //~vapgR~
//      }                                                          //~vapgR~
        if (totalWait1==0 && totalWait2==0)                        //~vapgI~
        {                                                          //~vapgI~
            if (Dump.Y) Dump.println("RAReach.evaluateWait1 return false(skipReach) by totalWait ctr=empty");//~vapgI~
            return false;                                          //~vapgI~
        }                                                          //~vapgI~
        if ((intent & INTENT_7PAIR)!=0)                            //~vapgI~
        {                                                          //~vapgI~
          	if (RADS.isDoraOpen(posWait1) || RADS.isDoraOpen(posWait2))//~vapgI~
    	    {                                                      //~vapgI~
        	    if (Dump.Y) Dump.println("RAReach.evaluateWait1 return false(skip reach) by 7pair waiting dora");//~vapgR~
            	return false;    //skip reach                      //~vapgR~
        	}                                                      //~vapgI~
        }                                                          //~vapgI~
        int idxAdd=-1;                                             //~vaazR~
        if ((intent & INTENT_7PAIR)!=0 &&  (posWait1>=OFFS_WORDTILE && posWait2>=OFFS_WORDTILE))//~vapjI~
        {                                                          //~vapjI~
            int vw1=RAUtils.chkValueWordTile(posWait1,PplayerEswn);//~vapjI~
            int vw2=RAUtils.chkValueWordTile(posWait2,PplayerEswn);//~vapjI~
            if (vw1==0)                                            //~vapjI~
                idxAdd=idxDiscard1;                                //~vapjI~
            else                                                   //~vapjI~
            if (vw2==0)                                            //~vapjI~
                idxAdd=idxDiscard2;                                //~vapjI~
        }                                                          //~vapjI~
        else                                                       //~vapjI~
        if (posWait1>=OFFS_WORDTILE)                               //~vaazI~
        	idxAdd=idxDiscard1;                                    //~vaazR~
	    else                                                       //~vaazI~
        if (posWait2>=OFFS_WORDTILE)                               //~vaazI~
        	idxAdd=idxDiscard2;                                    //~vaazR~
	    else                                                       //~vaazI~
        if (!swTanyao1)                                            //~vaazI~
        	idxAdd=idxDiscard1;                                    //~vaazR~
        else                                                       //~vaazI~
        if (!swTanyao2)                                            //~vaazI~
        	idxAdd=idxDiscard2;                                    //~vaazR~
	    if (Dump.Y) Dump.println("RAReach.evaluateWait1 idxAdd="+idxAdd+",posWait="+(idxAdd==idxDiscard1?posWait1:posWait2));//~vap8R~
        if (idxAdd!=-1)                                            //~vaazR~
        {                                                          //~vaazI~
		    if (Dump.Y) Dump.println("RAReach.evaluateWait1 old="+itsHandValue[idxAdd]);//~vaazR~
        	itsHandValue[idxAdd]+=DV_WAIT1_CHANTA;	//	        = 2000000;		//discard for reach//~vaazR~
		    if (Dump.Y) Dump.println("RAReach.evaluateWait1 new="+itsHandValue[idxAdd]);//~vaazR~
        }                                                          //~vaazI~
        if (Dump.Y) Dump.println("RAReach.evaluateWait1 exit itsHandValue="+Utils.toString(itsHandValue));//~vapgR~
	    if (Dump.Y) Dump.println("RAReach.evaluateWait1 rc="+true);//~vapgR~
        return true;                                               //~vaixI~
    }                                                              //~vaazI~
    //***********************************************************************//~vaajI~
    private void evaluateHanMax(int[] PitsHanAmt,int PhanAmt)      //~vaajI~
    {                                                              //~vaajI~
        if (Dump.Y) Dump.println("RAReach.evaluateHanMax hanAmt="+Integer.toHexString(PhanAmt)+",itsHanPoint="+Utils.toHexString(PitsHanAmt));//~vaajR~
        for (int ii=0;ii<ctrHand;ii++)                             //~vaajI~
        {                                                          //~vaajI~
      		if (PhanAmt==PitsHanAmt[ii])                          //~vaajI~
        	{                                                      //~vaajI~
	        	if (Dump.Y) Dump.println("RAReach.evaluateHanMax old idxHandValue["+ii+"]="+itsHandValue[ii]);//~vaajI~
//  	    	itsHandValue[ii]+=DV_TRYNEXT_HANMAX; //-100,000    //~vaajR~//~vaanR~
    	    	itsHandValue[ii]+=-DV_TRYNEXT_HANMAX; //-(-100,000)//~vaanI~
	        	if (Dump.Y) Dump.println("RAReach.evaluateHanMax new idxHandValue["+ii+"]="+itsHandValue[ii]);//~vaajI~
            }                                                      //~vaajI~
        }                                                          //~vaajI~
                                                                  //~vaajI~
    }                                                              //~vaajI~
    //*****************************************************************
    //*return max Han, by minus if skip reach by furiten or Fix2   //~1328R~
    //*****************************************************************
    private int evaluateWinList(int PplayerEswn,int PposTryDiscard,int[] PitsHand/*dropped Discard*/,int PctrHand)
    {
        int ctrWinTile=0,han;
        int amtMx=0;                                               //~vaajI~
        //************************
        int intent=RS.RSP[PplayerEswn].intent;                     //~1224M~
        if (Dump.Y) Dump.println("RAReach.evaluateWinList playerEswn="+PplayerEswn+",intent="+Integer.toHexString(intent)+",posTryDiscard="+PposTryDiscard+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1206R~//~1224R~//~va8jR~
        getItsWinList(PitsHand,PctrHand,itsWinWork); //output to itsWinListWork//~1224R~//~1310R~
        evaluateWinListPosWait1=ctrWinList==1 ? itsWinWork[0] : -1;//~vaazI~
        if (Dump.Y) Dump.println("RAReach.evaluateWinList evaluateWinListPosWait1="+evaluateWinListPosWait1);//~vaazI~
        if (ctrWinList==0)                                         //~1121I~
        {                                                          //~1121I~
        	if (Dump.Y) Dump.println("RAReach.evaluateWinList ctrWinList=0 return 0");//~1121I~
            return 0;                                              //~1121I~
        }                                                          //~1121I~
        int[] itsExposed=RS.itsExposed;
        if (Dump.Y) Dump.println("RAReach.evaluateWinList itsExposed="+Utils.toString(itsExposed,9));
        hanMax=0;                                                  //~1122R~
//      swSkipReach=false;                                         //~1215R~//~1311R~
        if ((TestOption.option2 & TO2_ROBOT_SKIP_REACH)!=0) //TODO test//~1304I~
	        swSkipReach=true;                                      //~1304I~
        int hvEarly1=HV_CTR_TO_WAIT_REACH_EARLY_WINLIST; //10//tanki/kanchan/penshan//~vaazI~
        int hvEarly2=HV_CTR_TO_WAIT_REACH_EARLY;	//8            //~vaazI~
        if ((TestOption.option4 & TO4_REACH_EARLY)!=0) //TODO test //~vaazI~
        {                                                          //~vaazI~
	        hvEarly1=4;                                            //~vaazI~
    	    hvEarly2=3;                                            //~vaazI~
	        if (Dump.Y) Dump.println("RAReach.evaluateWinList use Test early@@@@");//~vaazI~
        }                                                          //~vaazI~
	    if (Dump.Y) Dump.println("RAReach.evaluateWinList hvEarly 1="+hvEarly1+",2="+hvEarly2);//~vaazI~//~vab8R~
        int ctrWinTotal=0,valueTotal=0;                            //~1120R~
        boolean swWaitingWordTile=false;                           //~1221R~
        for (int ii=0;ii<ctrWinList;ii++)
        {
            int pos=itsWinWork[ii];
            if (pos==PposTryDiscard)	//evaluateing as this pos is discarded
            	swSkipReach=true;
	        ctrWinTile=PIECE_DUPCTR-itsExposed[pos]-PitsHand[pos];
            if (ctrWinTile<=0)                                     //~1120R~
            	continue;
            if (pos>=OFFS_WORDTILE)                                //~1221R~
            	swWaitingWordTile=true;                            //~1221I~
            ctrWinTotal+=ctrWinTile;
            han=getValue(PitsHand,PctrHand,pos)+hanAdd;
            valueTotal+=ctrWinTile*han;                            //~1120I~
	        if (Dump.Y) Dump.println("RAReach.evaluateWinList posWon="+pos+",han="+han+",hanAdd="+hanAdd+",hanRequired="+hanRequired+",hanExceptDora="+hanExceptDora+",ctrWinTotal="+ctrWinTotal);//~1220I~//~va8dR~
//          if (han<hanRequired)  //0 if double reach, else 2 if fix2 constraint  else 1//~1219R~
//          if (hanExceptDora+1/*of reach*/<hanRequired)  //0 if double reach, else 2 if fix2 constraint  else 1//~1219I~//~1220R~//~va8dR~
            if (hanExceptDora+hanAdd/*1 for Reach,2 for double Reach*/<hanRequired)  //hanRequired:2:fix2,1,fix//~va8dI~
            {                                                      //~1220I~
//*robot dose not expect Reach+Take==2 for fix2 constraint         //~va8dI~//~va8jR~
		        if (Dump.Y) Dump.println("RAReach.evaluateWinList@@@@ skipReach by hanExceptDora");//~1220I~//~1307R~
            	swSkipReach=true;
            }                                                      //~1220I~
            else                                                   //~1220I~
            if (RS.isFuriten(PplayerEswn,pos))
            {                                                      //~1220I~
		        if (Dump.Y) Dump.println("RAReach.evaluateWinList@@@@ skipReach by furiten eswn="+PplayerEswn+",pos="+pos);//~1220I~//~1307R~//~vaavR~
            	swSkipReach=true;
            }                                                      //~1220I~
            if (han>hanMax)
            {                                                      //~vaajI~
            	hanMax=han;
                amtMx=amtRonValue;                                 //~vaajI~
            }                                                      //~vaajI~
            else                                                   //~vaajI~
            if (han==hanMax)                                       //~vaajI~
            {                                                      //~vaajI~
                if (amtRonValue>amtMx)                             //~vaajI~
                	amtMx=amtRonValue;                             //~vaajI~
            }                                                      //~vaajI~
        }
        evaluateWinListTotal=ctrWinTotal;                          //~vaazI~
	    int ctrTaken=RS.RSP[PplayerEswn].ctrTaken;                 //~1215I~
        if (!swSkipReach)                                          //~vabmI~
        {                                                          //~vabmI~
            boolean swShanpon=(intent & (INTENT_7PAIR|INTENT_ALLSAME))==0 && isWaitingShanpon(PplayerEswn,itsWinWork/*winList*/,ctrWinList,PitsHand,PctrHand);//~vabmI~
            if (Dump.Y) Dump.println("RAReach.evaluateWinList posTryDiscard="+PposTryDiscard+",ctrWinList="+ctrWinList+",ctrWinTotal="+ctrWinTotal+",ctrTaken="+ctrTaken+",swShanpon="+swShanpon);//~1221I~//~vaarR~//~vaaAR~//~vabmR~
            if (swShanpon)                                         //~vabmI~
            {                                                      //~vabmI~
                if (Dump.Y) Dump.println("RAReach.evaluateWinList swShanpon=True");//~vabmI~
                if (ctrWinTotal<HV_CTRWIN_TO_REACH_SKIP_SHANPON && ctrTaken<hvEarly1 //shanpon chk//~vabmI~
                //* ctrWinTotal<4                               && ctrTaken<10//~vabmI~
                &&  isSkipReachForShanpon(PplayerEswn,itsWinWork,ctrWinTotal)//~vabmI~
                )                                                  //~vabmI~
                {                                                  //~vabmI~
                    if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by shanpon ctrWinTotal="+ctrWinTotal+",ctrTaken="+ctrTaken);//~vabmI~
                    swSkipReach=true;                              //~vabmI~
                }                                                  //~vabmI~
            }                                                      //~vabmI~
    	}                                                          //~vabmI~
        if (swSkipReach)                                           //~vabmI~
        {                                                          //~vabmI~
        	if (Dump.Y) Dump.println("RAReach.evaluateWinList@@@@ skipreach already determined");//~vapkR~
		}                                                          //~vabmI~
        else                                                       //~vabmI~
	    if (!(ctrWinTotal>=HV_CTRWIN_TO_REACH_FORCE && hanMax>=HV_HAN_TO_FORCE_REACH))	//Not special high value of 13han even winlistctr>=1//~1217R~
        //*                1                                   13  //~vaarI~
        {                                                          //~1215I~
        	if (Dump.Y) Dump.println("RAReach.evaluateWinList not high score ctrWinTotal="+ctrWinTotal+",hanMax="+hanMax);//~vabmI~
        	if ((intent & INTENT_7PAIR)!=0 && ctrWinTotal>0)       //~1224I~
            {                                                      //~1224I~
	        	if (Dump.Y) Dump.println("RAReach.evaluateWinList 7Pair and ctrWinTotal!=0");//~vabmI~
            	int pos7p=itsWinWork[0];                           //~1224I~
                int type7p=pos7p/CTR_NUMBER_TILE;                  //~1224I~
                int num7p=pos7p%CTR_NUMBER_TILE;                   //~1224I~
                if (type7p!=TT_JI && ctrWinTotal<HV_CTRWIN_TO_REACH_7PAIR)	//         <2;    // if wintile>=2 do reach//~1224I~//~1310R~
                {                                                  //~1224I~
		        	swSkipReach=true;                              //~1224I~
        			if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ 7Pair skipreach by wintile ctr");//~1224I~
                }                                                  //~1224I~
            }                                                      //~1224I~
            else                                                   //~1224I~
//      	if (!swWaitingWordTile && ctrWinList==1 && ctrWinTotal<HV_CTRWIN_TO_REACH_ONE_TYPE && ctrTaken<HV_CTR_TO_WAIT_REACH_EARLY) //tanki/kanchan/penshan//~1224I~//~va9hR~
        	if (((intent & INTENT_7PAIR)==0)                        //~va9hI~
//          &&  !swWaitingWordTile && ctrWinList==1 && ctrWinTotal<HV_CTRWIN_TO_REACH_ONE_TYPE && ctrTaken<HV_CTR_TO_WAIT_REACH_EARLY_WINLIST) //tanki/kanchan/penshan//~va9hI~//~vaaAR~//~vaazR~
            &&  !swWaitingWordTile && ctrWinList==1 && ctrWinTotal<HV_CTRWIN_TO_REACH_ONE_TYPE && ctrTaken<hvEarly1) //tanki/kanchan/penshan//~vaazI~
        	//**                                       ctrWinTotal<4                           && ctrTaken<10                         ) //TODO test//tanki/kanchan/penshan//~1224I~//~va9hR~//~vaaAR~
            {                                                      //~1224I~
	        	if (Dump.Y) Dump.println("RAReach.evaluateWinList Not 7Pair winList=1 and not word");//~vabmI~
        	  if ((TestOption.option3 & TO3_ROBOT_DO_REACH)!=0) //TODO test//~va8dI~//~vaarR~
              {                                                    //~va8dI~
        		if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by early take and wintile ctr and winlist=1 BUT force REACH by test option");//~va8dI~
              }                                                    //~va8dI~
              else                                                 //~va8dI~
              {                                                    //~va8dI~
               if (!isWaitingPenchan(PplayerEswn,itsWinWork[0]/*winTile*/,PitsHand,PctrHand))//~vaavR~
               {                                                   //~vaavI~
        		if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by early take and wintile ctr and winlist=1 ctrTaken="+ctrTaken);//~1224I~//~vaavR~
	        	swSkipReach=true;                                  //~1224I~
               }                                                   //~vaavI~
              }                                                    //~va8dI~
            }                                                      //~1224I~
            else                                                   //~vab8I~
        	if ((intent & INTENT_7PAIR)==0                         //~vab8R~
            &&  ctrTaken>1               //do reach if double reach//~vab8I~
            &&  !swWaitingWordTile && ctrWinList==1 && ctrWinTotal==HV_CTRWIN_TO_REACH_ONE_TYPE && ctrTaken<hvEarly2 && isWaitingKanchan(PplayerEswn,itsWinWork[0]/*winTile*/,PitsHand,PctrHand))//~vab8I~
        	//**                                       ctrWinTotal=4                           && ctrTaken<8       )//~vab8I~
            {                                                      //~vab8I~
	        	if (Dump.Y) Dump.println("RAReach.evaluateWinList Not 7Pair winList=1 and not word and ctrWinTotal==4 eeary and kanchan");//~vabmI~
        	  	if ((TestOption.option3 & TO3_ROBOT_DO_REACH)!=0) //TODO test//~vab8I~
              	{                                                  //~vab8I~
        			if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by early take and wintile=4 ctr and winlist=1 BUT force REACH by test option");//~vab8I~
              	}                                                  //~vab8I~
             	else                                               //~vab8I~
        		if (AG.aPlayers.getCtrReachedPlayer(playerReach)==0)//~vab8I~
              	{                                                  //~vab8I~
        			if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by early take<8) take and wintile ctr and winlist=1 ctrTaken="+ctrTaken);//~vab8I~
	        		swSkipReach=true;                              //~vab8I~
              	}                                                  //~vab8I~
            }                                                      //~vab8I~
            else                                                   //~1224I~
//          if (ctrWinTotal<HV_CTRWIN_TO_REACH_EARLY && ctrTaken<HV_CTR_TO_WAIT_REACH_EARLY)//winctr<4 & take<8//~1216R~//~1218R~//~vaazR~
            if (ctrWinTotal<HV_CTRWIN_TO_REACH_EARLY && ctrTaken<hvEarly2)//winctr<4 & take<8//~vaazI~
	        //**ctrWinTotal<4                        && ctrTaken<8                          //winctr<4 & take<8//~1224I~
            {                                                      //~1216I~
	        	if (Dump.Y) Dump.println("RAReach.evaluateWinList Not 7Pair ctrWinTotal<4 eary");//~vabmI~
        	  if ((TestOption.option3 & TO3_ROBOT_DO_REACH)!=0) //TODO test//~va8dI~//~vaarR~
              {                                                    //~va8dI~
        		if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by early take and wintile ctr BUT doReach by test option");//~va8dI~
              }                                                    //~va8dI~
              else                                                 //~va8dI~
              {                                                    //~va8dI~
        		if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by early take and wintile ctr ctrTaken="+ctrTaken);//~1216I~//~vaavR~
	        	swSkipReach=true;
              }                                                    //~va8dI~
            }                                                      //~1216I~
            else                                                   //~1215I~
        	if (ctrWinTotal<HV_CTRWIN_TO_REACH_2ND && AG.aPlayers.getCtrReachedPlayer(playerReach)!=0) //wintile<4//~1306I~
            //*             5                                      //~vaarI~
            {                                                      //~1306I~
        		if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by other player reach and wintile ctr="+ctrWinTotal);//~1306I~//~1308R~
	        	swSkipReach=true;                                  //~1306I~
            }                                                      //~1306I~
            else                                                   //~1306I~
        	if (ctrWinTotal<HV_CTRWIN_TO_REACH) //wintile<2                    //~1215I~//~1216R~//~1218R~
            {                                                      //~1216I~
	        	if (Dump.Y) Dump.println("RAReach.evaluateWinList ctrWinTotal<2");//~vabmI~
            	if (!swWaitingWordTile)                            //~1221I~
                {                                                  //~1221I~
        			if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by wintile ctr");//~1216I~//~1221R~
	        		swSkipReach=true;                              //~1221R~
                }                                                  //~1221I~
            }                                                      //~1216I~
        }
        if ((intent & INTENT_GIVEUP)!=0)
        {
	        if (hanMax<HV_HAN_TO_REACH_IGNORE_OTHER)    //if han<6 //~1216R~
	        	swSkipReach=true;
        }
        if ((TestOption.option3 & TO3_ROBOT_DO_REACH)!=0) //TODO test//~vapkI~
        {                                                          //~vapkI~
        	if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach=False by testOption ROBOT DO REACH");//~vapkI~
   	 	    swSkipReach=false;                                     //~vapkI~
        }                                                          //~vapkI~
        if (swSkipReach)
        {                                                          //~1215I~
	        valueTotal=-valueTotal;                                //~1120R~
        }                                                          //~1215I~
        amtMax=amtMx;                                              //~vaajI~
	    int rc=valueTotal*DV_REACH_BYVALUE;  //100000              //~1122I~//~1219R~
        if (Dump.Y) Dump.println("RAReach.evaluateWinList posDiscard="+PposTryDiscard+",amtMax="+amtMax+",valueTotal="+valueTotal+",rc="+rc+",hanMax="+hanMax+",ctrWinTotal="+ctrWinTotal+",ctrTake="+ctrTaken+",skipReach="+swSkipReach);//~1122I~//~1124R~//~1215R~//~1216R~//~vaajR~
        return rc;                                                 //~1122R~
    }
    //*****************************************************************//~vabmI~
    private boolean isWaitingShanpon(int Peswn,int[] PwinList,int PctrWinList,int[] PitsHand,int PctrHand)//~vabmR~
    {                                                              //~vabmI~
        if (Dump.Y) Dump.println("RAReach.isSkipByWaitShanpon eswn="+Peswn+",WinList="+Arrays.toString(PwinList)+",ctrWinList="+PctrWinList);//~vabmI~
        if (Dump.Y) Dump.println("RAReach.isSkipByWaitShanpon itsHand="+Utils.toString(PitsHand,9));//~vabmI~
        boolean rc=false;                                          //~vabmI~
        if (PctrWinList==2)                                        //~vabmI~
        {                                                          //~vabmI~
        	int pos1=PwinList[0];                                  //~vabmI~
        	int pos2=PwinList[1];                                  //~vabmI~
            rc=(PitsHand[pos1]==2 && PitsHand[pos2]==2);          //~vabmR~
        }                                                          //~vabmI~
        if (Dump.Y) Dump.println("RAReach.isWaitingShanpon rc="+rc);//~vabmR~
        return rc;                                                 //~vabmI~
    }                                                              //~vabmI~
    //*****************************************************************//~vabmI~
    //*when isWaitingShanpon=True                                  //~vabmI~
    //*****************************************************************//~vabmI~
    private boolean isSkipReachForShanpon(int Peswn,int[] PwinList,int PctrWinTotal)//~vabmI~
    {                                                              //~vabmI~
        if (Dump.Y) Dump.println("RAReach.isSkipReachForShanpon eswn="+Peswn+",WinList="+Arrays.toString(PwinList)+",ctrWinTotal="+PctrWinTotal);//~vabmI~
        boolean rc;                                                //~vabmI~
        if (PctrWinTotal==0)                                       //~vabmR~
        	rc=true;                                               //~vabmI~
        else                                                       //~vabmI~
        {                                                          //~vabmI~
        	rc=false;                                              //~vabmI~
            int pos1=PwinList[0];                                  //~vabmI~
            int pos2=PwinList[1];                                  //~vabmI~
            if (pos1<OFFS_WORDTILE &&  pos2<OFFS_WORDTILE)         //~vabmI~
            {                                                      //~vabmI~
                int num1=pos1%CTR_NUMBER_TILE;                     //~vabmI~
                int num2=pos2%CTR_NUMBER_TILE;                     //~vabmI~
                if ((num1>TN2 && num1<TN8) && (num2>TN2 && num2<TN8))//~vabmI~
                        rc=true;                                   //~vabmI~
                if (Dump.Y) Dump.println("RAReach.isSkipByWaitShanpon num1="+num1+",num2="+num2);//~vabmI~
            }                                                      //~vabmI~
        }                                                          //~vabmI~
        if (Dump.Y) Dump.println("RAReach.isSkipByReachForShanpon rc="+rc);//~vabmI~
        return rc;                                                 //~vabmI~
    }                                                              //~vabmI~
    //*****************************************************************//~vaavI~
    //*Not complete but covenient chk Penchan                      //~vaaJR~
    //*****************************************************************//~vaavI~
    public  boolean isWaitingPenchan(int Peswn,int Ppos/*winTile*/,int[] PitsHand,int PctrHand)//~vaavI~//~vaaJR~
    {                                                              //~vaavI~
    	boolean rc=false;                                          //~vaavI~
        int type=Ppos/CTR_NUMBER_TILE;                             //~vaavI~
        int num=Ppos%CTR_NUMBER_TILE;                              //~vaavI~
        if (type<TT_JI)                                            //~vaavI~
        {                                                          //~vaavI~
        	if (num==TN3)                                          //~vaavI~
            {                                                      //~vaavI~
            	if (PitsHand[Ppos]==0 && PitsHand[Ppos-2]!=0 && PitsHand[Ppos-1]!=0)//~vaavI~
                {                                                  //~vaavI~
			        if (Dump.Y) Dump.println("RAReach.isWaitingPenchan rc=True type="+type+",num="+num);//~vaavI~
                	rc=true;                                       //~vaavI~
                }                                                  //~vaavI~
            }                                                      //~vaavI~
            else                                                   //~vaavI~
        	if (num==TN7)                                          //~vaavI~
            {                                                      //~vaavI~
            	if (PitsHand[Ppos]==0 && PitsHand[Ppos+2]!=0 && PitsHand[Ppos+1]!=0)//~vaavI~
                {                                                  //~vaavI~
			        if (Dump.Y) Dump.println("RAReach.isWaitingPenchan rc=True type="+type+",num="+num);//~vaavI~
                	rc=true;                                       //~vaavI~
                }                                                  //~vaavI~
            }                                                      //~vaavI~
        }                                                          //~vaavI~
        if (Dump.Y) Dump.println("RAReach.isWaitingPenchan rc="+rc+",eswn="+Peswn+",pos="+Ppos+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaavR~
        return rc;                                                 //~vaavI~
    }                                                              //~vaavI~
    //*****************************************************************//~vaaJI~
    //*Not complete but covenient chk Kanchan                      //~vaaJI~
    //*****************************************************************//~vaaJI~
    public  boolean isWaitingKanchan(int Peswn,int Ppos/*winTile*/,int[] PitsHand,int PctrHand)//~vaaJI~
    {                                                              //~vaaJI~
    	boolean rc=false;                                          //~vaaJI~
        int type=Ppos/CTR_NUMBER_TILE;                             //~vaaJI~
        int num=Ppos%CTR_NUMBER_TILE;                              //~vaaJI~
        if (type<TT_JI)                                            //~vaaJI~
        {                                                          //~vaaJI~
        	if (num>=TN2 && num<=TN8)                              //~vaaJI~
            {                                                      //~vaaJI~
            	if (PitsHand[Ppos]==0 && PitsHand[Ppos-1]!=0 && PitsHand[Ppos+1]!=0)//~vaaJI~
                {                                                  //~vaaJI~
			        if (Dump.Y) Dump.println("RAReach.isWaitingKanchan rc=True type="+type+",num="+num);//~vaaJR~
                	rc=true;                                       //~vaaJI~
                }                                                  //~vaaJI~
            }                                                      //~vaaJI~
        }                                                          //~vaaJI~
        if (Dump.Y) Dump.println("RAReach.isWaitingKanchan rc="+rc+",eswn="+Peswn+",pos="+Ppos+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaaJI~
        return rc;                                                 //~vaaJI~
    }                                                              //~vaaJI~
    //*****************************************************************//~vaarI~
    //*return win tile ctr (no chk furiten)                        //~vaarI~
    //*****************************************************************//~vaarI~
    public int getWinListTileCtr(int[] PitsHand,int PctrHand)     //~vaarI~
    {                                                              //~vaarI~
        if (Dump.Y) Dump.println("RAReach.getWinListTileCtr ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaarI~
        int ctrTotal=0;                                            //~vaarR~
        getItsWinList(PitsHand,PctrHand,itsWinWork); //output to itsWinListWork//~vaarI~
        if (ctrWinList==0)                                         //~vaarI~
        {                                                          //~vaarI~
        	if (Dump.Y) Dump.println("RAReach.getWinListTileCtr ctrWinList=0 return 0");//~vaarI~
            return 0;                                              //~vaarI~
        }                                                          //~vaarI~
        int[] itsExposed=RS.itsExposed;                            //~vaarI~
        if (Dump.Y) Dump.println("RAReach.getWinListTileCtr itsExposed="+Utils.toString(itsExposed,9));//~vaarI~
        for (int ii=0;ii<ctrWinList;ii++)                          //~vaarI~
        {                                                          //~vaarI~
            int pos=itsWinWork[ii];                                //~vaarI~
	        int ctrWinTile=PIECE_DUPCTR-itsExposed[pos]-PitsHand[pos]; //~vaarI~
            ctrTotal+=ctrWinTile;                                  //~vaarR~
	        if (Dump.Y) Dump.println("RAReach.getWinListTileCtr posWon="+pos+",ctrWinTile="+ctrWinTile+",ctrTotal="+ctrTotal);//~vaarR~
        }                                                          //~vaarI~
        if (Dump.Y) Dump.println("RAReach.getWinListTileCtr exit ctrTotal="+ctrTotal);//~vaarR~//~vaf8R~
        return ctrTotal;                                           //~vaarR~
    }                                                              //~vaarI~
    //*****************************************************************//~va8jI~
    //*return kataagariok                                          //~va8jR~
    //*****************************************************************//~va8jI~
    private boolean chkMultiWait(int PplayerEswn,int PposTryDiscard,int PhanReachAdd,int PhanRequired,int[] PitsHand/*excluding dicard*/,int PctrHand)//~va8jI~
    {                                                              //~va8jI~
        //************************                                 //~va8jI~
        if (Dump.Y) Dump.println("RAReach.chkMultiWait playerEswn="+PplayerEswn+",hanReachAdd="+PhanReachAdd+",hanRequired="+PhanRequired+",posTryDiscard="+PposTryDiscard+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~va8jI~
//        getItsWinList(PitsHand,PctrHand,itsWinWork); //output to itsWinListWork//~va8jR~
//        if (ctrWinList==0)                                       //~va8jR~
//        {                                                        //~va8jR~
//            if (Dump.Y) Dump.println("RAReach.chkMultiWait ctrWinList=0 return 0");//~va8jR~
//            return false;                                        //~va8jR~
//        }                                                        //~va8jR~
        boolean rc=true;                                           //~va8jI~
        if (PhanReachAdd>=PhanRequired)                            //~va8jI~
        {                                                          //~va8jI~
            if (Dump.Y) Dump.println("RAReach.chkMultiWait return true by hanReach="+PhanReachAdd+">="+PhanRequired);//~va8jI~
            return rc;                                             //~va8jI~
        }                                                          //~va8jI~
        for (int ii=0;ii<ctrWinList;ii++)                          //~va8jI~
        {                                                          //~va8jI~
            int pos=itsWinWork[ii];                                //~va8jI~
	        TileData tdWin=new TileData(pos/CTR_NUMBER_TILE,pos%CTR_NUMBER_TILE);   //to give ronType and ronNumber//~va8jI~
            int han=AG.aUARonValue.chkRankReachExceptDora(PplayerEswn,tdWin,RS.swFix2,PitsHand);//~va8jR~
	        if (Dump.Y) Dump.println("RAReach.chkMultiWait posWon="+pos+",hanAdd="+PhanReachAdd+",hanRequired="+PhanRequired+",han="+han);//~va8jR~
            if (han+PhanReachAdd<PhanRequired)  //kataagari        //~va8jR~
            {                                                      //~va8jI~
                rc=false;                                          //~va8jI~
                break;                                             //~va8jI~
            }                                                      //~va8jI~
        }                                                          //~va8jI~
        if (!rc)                                                   //~va8jI~
            GMsg.drawMsgbar(R.string.AE_RankFix2MultiWait);        //~va8jR~
        if (Dump.Y) Dump.println("RAReach.chkMultiWait rc="+rc);   //~va8jI~
        return rc;                                                 //~va8jI~
    }                                                              //~va8jI~
    //*****************************************************************//~va8jI~
    //*for human player in swTrainingMode; return true:errFuriten  //~va8jI~
    //*****************************************************************//~va8jI~
    private boolean chkFuritenReach(int PplayerEswn,int[] PitsWin,int PctrWin,int PposDiscard)//~va8jR~
    {                                                              //~va8jI~
        //************************                                 //~va8jI~
        if (Dump.Y) Dump.println("RAReach.chkFuritenReach swFuritenReachReject="+RS.swFuritenReachReject+",swFuritenReachOK="+RS.swFuritenReachOK+",swFuritenReachReject="+RS.swFuritenReachReject+",playerEswn="+PplayerEswn+",posDiscard="+PposDiscard+",itsWin="+Utils.toStringMax(PitsWin,PctrWin));//~va8jR~//~vah3R~//~vaj4R~
        boolean rc=false;                                          //~va8jI~
//      if (!RS.swFuritenReachOK)                                  //~va8jI~//~vaj4R~
//      {                                                          //~va8jI~//~vaj4R~
            for (int ii=0;ii<PctrWin;ii++)                         //~va8jR~
            {                                                      //~va8jR~
                int pos=itsWinWork[ii];                            //~va8jR~
                if (pos==PposDiscard || RS.RSP[PplayerEswn].isFuritenSelf(pos))//~va8jR~
                {                                                  //~va8jR~
                    rc=true;                                       //~va8jR~
                    break;                                         //~va8jR~
                }                                                  //~va8jR~
            }                                                      //~va8jR~
//      }                                                          //~va8jI~//~vaj4R~
        if (rc)                                                    //~va8jI~
        {                                                          //~vaj4R~
//        if (RS.swFuritenReachOK || RS.swFuritenReachReject) //furitenReach:None; no msg may cause chombo//~vap8R~
          if (RS.swFuritenReachReject) //furitenReach:None; no msg may cause chombo//~vap8I~
            GMsg.drawMsgbar(R.string.AE_FuritenReach);             //~va8jI~
          else                                                     //~vap8I~
          if (RS.swFuritenReachOK) //furitenReach:None; no msg may cause chombo//~vap8I~
            GMsg.drawMsgbar(R.string.AE_FuritenReachOK);           //~vap8I~
//        else             //err regardless FuritenReach option, set reach err status at RoundStat at reach by the option//~vaj5R~
          	swErrFuritenReach=true;	//furiten but do reach silently by option FuritenReach:No//~vaj5I~
        }                                                          //~vaj4R~
        if (RS.swFuritenReachOK)                                   //~vaj4R~
        {                                                          //~vaj4R~
	        if (Dump.Y) Dump.println("RAReach.chkFuritenReach return false by swFuritenReachOK");//~vaj4R~
        	rc=false;                                              //~vaj4R~
        }                                                          //~vaj4R~
        if (Dump.Y) Dump.println("RAReach.chkFuritenReach rc="+rc);//~va8jI~
        return rc;                                                 //~va8jI~
    }                                                              //~va8jI~
    //*****************************************************************
    //*to evaluate winlist winList,and 2han constraint chk         //~1219I~
    //*****************************************************************//~1219I~
    private int getValue(int[] PitsHand/*dropped Discard*/,int PctrHand,int PposWin)
    {
        if (Dump.Y) Dump.println("RAReach.getValue posWin="+PposWin);
        PitsHand[PposWin]++;                                           //~1121I~
//  	int han=AG.aRARon.getRonValueExceptDora(playerReach,PitsHand,PposWin);//han as not taken//~1219R~//~va91R~
    	int han=AG.aRARon.getRonValueExceptDoraReach(playerReach,PitsHand,PposWin);//han as not taken//~va91I~
        PitsHand[PposWin]--;                                           //~1121I~
        hanExceptDora=AG.aRARon.hanExceptDora;                     //~1219I~
        amtRonValue=AG.aRARon.amtRonValue;	//output of RARon.getRonValue()//~vaajI~
        if (Dump.Y) Dump.println("RAReach.getValue playerReach="+playerReach+",posWin="+PposWin+",han="+han+",hanExceptDora="+hanExceptDora+",amtRonvalue="+amtRonValue+",ctrHand="+PctrHand);//~1219R~//~vaajR~
        return han;
    }
    //*********************************************************
    //*return list of win tile pos list
    //*********************************************************
    public int[] getItsWinList(int[] PitsHand,int PctrHand)
    {
        if (Dump.Y) Dump.println("RAReach.getItsWinList entry ctrHand="+PctrHand);//~vaf8I~
		int ctrWin=getShanten0WinList(PitsHand,PctrHand,itsWinWork);//~1122R~
        int[] itsWin=new int[ctrWin];
        System.arraycopy(itsWinWork,0,itsWin,0,ctrWin);
        if (Dump.Y) Dump.println("RAReach.getItsWinList return ctrWin="+ctrWin+",itsWin="+Arrays.toString(itsWin));//~1213R~//~1216R~//~1310R~
        return itsWin;
    }
    //*********************************************************
    //*set list of win tile pos list
    //*********************************************************
    public int getItsWinList(int[] PitsHand,int PctrHand,int[] PitsWin)
    {
		int ctrWin=getShanten0WinList(PitsHand,PctrHand,PitsWin);  //~1122R~
        if (Dump.Y) Dump.println("RAReach.getItsWinList return ctrWin="+ctrWin+",itsWin="+Utils.toString(PitsWin,-1,ctrWin));//~1213R~
        return ctrWin;
    }
    //*********************************************************
    //*set list of win tile pos list
    //*********************************************************
    public boolean[] getBtsWinList(int[] PitsHand,int PctrHand)
    {
    	getBtsWinList(PitsHand,PctrHand,btsWinWork);
        if (Dump.Y) Dump.println("RAReach.getBtsWinList return btsWinWork="+Utils.toString(btsWinWork,9));
        return btsWinWork;
    }
    //*********************************************************    //~vagwI~
    //*for Furiten chk                                             //~vagwI~
    //*set list of win tile pos list with No check exposed all     //~vagwI~
    //*********************************************************    //~vagwI~
    public boolean[] getBtsWinListNoChkEmpty(int[] PitsHand,int PctrHand)//~vagwI~
    {                                                              //~vagwI~
    	getBtsWinListNoChkEmpty(PitsHand,PctrHand,btsWinWork);     //~vagwR~
        if (Dump.Y) Dump.println("RAReach.getBtsWinListNoChkEmpty return btsWinWork="+Utils.toString(btsWinWork,9));//~vagwI~
        return btsWinWork;                                         //~vagwI~
    }                                                              //~vagwI~
    //*********************************************************
    //*set list of win tile pos list
    //*********************************************************
    public int getBtsWinList(int[] PitsHand,int PctrHand,boolean[] PbtsWin)
    {
        Arrays.fill(PbtsWin,false);
	    int ctrWin=getBtsWinListMerge(PitsHand,PctrHand,PbtsWin);
        if (Dump.Y) Dump.println("RAReach.getBtsWinList set mode ctrWin="+ctrWin+",return btsWinWork="+Utils.toString(btsWinWork,9));
        return ctrWin;
    }
    //*********************************************************    //~vagwI~
    //*set list of win tile pos list                               //~vagwI~
    //*********************************************************    //~vagwI~
    public int getBtsWinListNoChkEmpty(int[] PitsHand,int PctrHand,boolean[] PbtsWin)//~vagwI~
    {                                                              //~vagwI~
        Arrays.fill(PbtsWin,false);                                //~vagwI~
        swNoChkEmpty=true;                                         //~vagwI~
	    int ctrWin=getBtsWinListMerge(PitsHand,PctrHand,PbtsWin);  //~vagwI~
        swNoChkEmpty=false;                                        //~vagwI~
        if (Dump.Y) Dump.println("RAReach.getBtsWinListNoChkEmpty set mode ctrWin="+ctrWin+",return btsWinWork="+Utils.toString(btsWinWork,9));//~vagwI~
        return ctrWin;                                             //~vagwI~
    }                                                              //~vagwI~
    //*********************************************************
    //*set list of win tile pos list,merge without clear
    //*********************************************************
    private int getBtsWinListMerge(int[] PitsHand,int PctrHand,boolean[] PbtsWin)
    {
		int ctrWin=getItsWinList(PitsHand,PctrHand,itsWinWork);
        for (int ii=0;ii<ctrWin;ii++)
        	PbtsWin[itsWinWork[ii]]=true;
        if (Dump.Y) Dump.println("RAReach.getBtsWinListMerge ctrWin="+ctrWin+",return PbtsWin="+Utils.toString(PbtsWin,9));
        return ctrWin;
    }
    private int getBtsWinListMerge(int[] PitsHand,int PctrHand,boolean[] PbtsWin,boolean[] PbtsWinByEswn)//~vap6I~
    {                                                              //~vap6I~
		int ctrWin=getItsWinList(PitsHand,PctrHand,itsWinWork);    //~vap6R~
        for (int ii=0;ii<ctrWin;ii++)                              //~vap6I~
        {                                                          //~vap6I~
        	PbtsWin[itsWinWork[ii]]=true;                          //~vap6I~
        	PbtsWinByEswn[itsWinWork[ii]]=true;                    //~vap6I~
        }                                                          //~vap6I~
        if (Dump.Y) Dump.println("RAReach.getBtsWinListMerge ctrWin="+ctrWin+",return PbtsWin="+Utils.toString(PbtsWin,9)+",PbtsWinByEswn="+Utils.toString(PbtsWinByEswn,9));//~vap6R~
        return ctrWin;                                             //~vap6I~
    }                                                              //~vap6I~
    //*********************************************************
    //*by itsTile[34 entry]
    //*********************************************************
    private int getShanten0WinList(int [] PitsTile,int PctrHand,int[] PitsWin)//~1122R~
    {
        //*************
        if (Dump.Y) Dump.println("RAReach.getShanten0WinList ctrHand="+PctrHand+",itsTile="+Utils.toString(PitsTile,9));//~1126R~//~1206R~
//      int ctrWin=0;                                              //~1310R~
        ctrWinList=0;                                              //~1310I~
        int flag0=Shanten.chkShanten0(PitsTile,PctrHand);	//get tenpai pattern
        if ((flag0 & SHANTEN_STANDARD)!=0)
            getWinList_Standard(PitsTile,PctrHand,PitsWin);        //~1310R~
        if ((flag0 & SHANTEN_13ORPHAN)!=0)
            getWinList_13Orphan(PitsTile,PitsWin);                 //~1310R~
        if ((flag0 & SHANTEN_7PAIR)!=0)
            getWinList_7Pair(PitsTile,PitsWin);                    //~1310R~
        if (Dump.Y) Dump.println("RAReach.getShanten0WinList0 ctrWinList="+ctrWinList+",itsWin="+Utils.toStringMax(PitsWin,ctrWinList));//~1124R~//~1125R~//~1310R~//~vaf8R~
        return ctrWinList;                                             //~1310R~
	}
    //*********************************************************
    //*public for ITRAReach
    //*********************************************************
    public int getWinList(int[][] Pdupctr,int PctrHand)
    {
        //*************
        if (Dump.Y) Dump.println("RAReach.getWinList ctrHand="+PctrHand);
        RAUtils.countTile(Pdupctr,itsHandWork);
		int[] listWin=getItsWinList(itsHandWork,PctrHand);
        if (Dump.Y) Dump.println("RAReach.getWinList itsWin="+Utils.toString(listWin));
        return listWin.length;
	}
    //*********************************************************
    //*from RoundStat.openReach; accum not discardable list
    //*********************************************************
    public void getWinTileAll(int Pplayer,boolean[] PbtsWinAll)
    {
        //*************
        if (Dump.Y) Dump.println("RAReach.getWinTileAll Pplayer="+Pplayer);
    	int eswn=Accounts.playerToEswn(Pplayer);
        int[] itsHandEswn=RS.getItsHandEswn(eswn);
        int ctrHandEswn=RS.RSP[eswn].ctrHand;
    	getBtsWinListMerge(itsHandEswn,ctrHandEswn,PbtsWinAll);
	}
    public void getWinTileAll(int Pplayer,boolean[] PbtsWinAll,boolean[] PbtsWinByEswn)//~vap6I~
    {                                                              //~vap6I~
        //*************                                            //~vap6I~
        if (Dump.Y) Dump.println("RAReach.getWinTileAll set also each btsWinByEswnPplayer="+Pplayer);//~vap6R~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~vap6I~
        int[] itsHandEswn=RS.getItsHandEswn(eswn);                 //~vap6I~
        int ctrHandEswn=RS.RSP[eswn].ctrHand;                      //~vap6I~
    	getBtsWinListMerge(itsHandEswn,ctrHandEswn,PbtsWinAll,PbtsWinByEswn);//~vap6I~
	}                                                              //~vap6I~
    //*********************************************************
    private int getWinList_Standard(int[] PitsTile,int PctrHand,int[] PitsWin)
    {
    	int ctrWin=0,pos=0;
//*number tile
        for (int ii=0;ii<CTR_NUMSUIT;ii++)
        {
            for (int jj=0;jj<CTR_NUMBER_TILE;jj++)
            {
                boolean swOrphan=false;
                if (PitsTile[pos]==0)                              //~1124R~
                {
                    switch(jj)
                    {
                        case 0:
                            swOrphan=(PitsTile[pos+1]==0);          //~1120R~
                            break;
                        case 8:                                    //~1120M~
                            swOrphan=(PitsTile[pos-1]==0);         //~1120I~
                            break;                                 //~1120M~
                        default:
                            swOrphan=(PitsTile[pos-1]==0 && PitsTile[pos+1]==0);//~1120R~
                    }
                }
                if (!swOrphan)
                {
                    ctrWin+=getWinList_Standard_Sub(PitsTile,PctrHand,pos,PitsWin);
                }
                pos++;
            }
        }
//*word tile
        for (;pos<CTR_TILETYPE;pos++)
        {
            if (PitsTile[pos]!=0)
                ctrWin+=getWinList_Standard_Sub(PitsTile,PctrHand,pos,PitsWin);
        }
        if (Dump.Y) Dump.println("RAReach.getWinList_Standard ctrWin="+ctrWin+",itsWin="+Utils.toString(PitsWin,-1,ctrWin));//~1124R~//~1125R~
        return ctrWin;
    }
    //*********************************************************
    private int getWinList_Standard_Sub(int[] PitsTile,int PctrHand,int Ppos,int[] PitsWin)
    {
    	int ctrWin=0;
    	int ctr=PctrHand+1;
//      if (!RAUtils.isEmpty(PitsTile,Ppos)) //all exposed         //~1124R~//~va9dR~
    	if (swNoChkEmpty || !RAUtils.isEmpty(PitsTile,Ppos)) //allow emptr or all exposed//~va9dI~
        {
	    	PitsTile[Ppos]++;
            boolean rc=(Shanten.chkWinStandard(PitsTile,ctr)==-1);
            PitsTile[Ppos]--;
            if (rc)
            {
                ctrWin=1;
//              PitsWin[ctrWinTotal++]=Ppos;                       //~1310R~
                PitsWin[ctrWinList++]=Ppos;                        //~1310I~
            }
        }
        if (Dump.Y) Dump.println("RAReach.getWinList_Standard_Sub swNochkEmpty="+swNoChkEmpty+",ctrWin="+ctrWin+",pos="+Ppos);//~va9dR~
        return ctrWin;
    }
    //*********************************************************
    private int getWinList_13Orphan(int[] PitsTile,int[] PitsWin)
    {
    	int ctrWin=0;
    	int pending=AG.aShanten.getWin_13Orphan(PitsTile);  //pos or CTR_TILETYPE(13 wait) or -1
        if (pending>=0)
        {
        	if (pending<CTR_TILETYPE) 	//single wait
            {
//      		if (!PbtsWin[pending])
//              {
//      			PitsWin[ctrWinTotal++]=pending;                //~1310R~
        			PitsWin[ctrWinList++]=pending;                 //~1310I~
            		ctrWin=1;
//              }
            }
            else						//13 wait
            {
            	for (int terminal:Shanten.itsOrphans)
//                  if (!PbtsWin[terminal])
//                  {
//                      PitsWin[ctrWinTotal++]=terminal;           //~1310R~
                        PitsWin[ctrWinList++]=terminal;            //~1310I~
                        ctrWin++;
//                  }
            }
        }
        if (Dump.Y) Dump.println("RAReach.getWinList_13Orphan ctrWin="+ctrWin);
        return ctrWin;
    }
    //*********************************************************
    private int getWinList_7Pair(int[] PitsTile,int[] PitsWin)
    {
    	int ctrWin=0;
    	int pending=AG.aShanten.getWin_7Pair(PitsTile);
//      if (pending>=0 && !PbtsWin[pending])
        if (pending>=0)
		{
//      	PitsWin[ctrWinTotal]=pending;
            addItsWin(PitsWin,pending);	//dupchk
            ctrWin=1;
        }
        if (Dump.Y) Dump.println("RAReach.getWinList_7Pair pending="+pending+",ctrWin="+ctrWin);
        return ctrWin;
    }
    //*********************************************************
    //*7pair win list may duplicated with standard
    //*avoid duplicated setting
    //*********************************************************
    private boolean addItsWin(int[] PitsWin,int Ppos)
    {
    	boolean swAdd=true;
//  	for (int ii=0;ii<ctrWinTotal;ii++)                         //~1310R~
    	for (int ii=0;ii<ctrWinList;ii++)                          //~1310I~
        {
        	if (PitsWin[ii]==Ppos)
            {
            	swAdd=false;
            	break;
            }
        }
        if (swAdd)                                                 //~1206R~
//      	PitsWin[ctrWinTotal++]=Ppos;                           //~1310R~
        	PitsWin[ctrWinList++]=Ppos;                            //~1310I~
        if (Dump.Y) Dump.println("RAReach.AddItsWin pos="+Ppos+",ctrWinList="+ctrWinList+",itsWin="+Utils.toStringMax(PitsWin,ctrWinList));//~1310R~
        return swAdd;
    }
    //*********************************************************
    private void issueReach(int Pplayer,int PplayerEswn,int PposDiscard)
    {
        if (Dump.Y) Dump.println("RAReach.issueReach player="+Pplayer+",eswn="+PplayerEswn+",pos="+PposDiscard);
        TileData td=RAUtils.selectTileInHand(PplayerEswn,PposDiscard);         //~1122I~//~1125R~
        td.setRobotSelection();                                    //~1125R~
        String data= ACAction.strTD(td);                            //~1125I~
        Robot r=RS.RSP[PplayerEswn].robot;                         //~1125I~
        r.sendToServer(false/*waiterBlock*/,GCM_REACH,PplayerEswn,data);//~1125I~
    }
    //*********************************************************    //~va8jI~
    //*from UAReach.chkTenpai                                      //~va8jI~
    //*no chk fureten for human player                             //~va8jI~
    //*rc:fals:err ignore Reach button                             //~va8jI~
    //*********************************************************    //~va8jI~
    public boolean chkFuritenMultiWait(int PactionID/*Open or Not*/,TileData PtdDiscard)//~va8jI~
    {                                                              //~va8jI~
    	boolean rc;                                                //~va8jI~
        if (Dump.Y) Dump.println("RAReach.chkFuritenMultiWait swFuritenReachReject="+RS.swFuritenReachReject+",swTrainingMode="+AG.swTrainingMode+",actionID="+PactionID+",PtdDiscard="+PtdDiscard.toString());//~va8jI~//~vaj4R~
//      if (!AG.swTrainingMode)                                    //~va8jI~//~vah3R~
//      if (!RS.swFuritenReachReject)                              //~vah3I~//~vaj4R~
//      {                                                          //~vaj4R~
//          if (Dump.Y) Dump.println("RAReach.chkFuritenMultiWait return true by !swFuritenReachReject");//~vaj4R~
//      	return true;    //do Reach                             //~va8jI~//~vaj4R~
//      }                                                          //~vaj4R~
        int hanReach=AG.aPlayers.is1stTake() ? 2 : 1;		//dounble reach 2 han and reach 1 han//~va8jI~
        if (PactionID==GCM_REACH_OPEN)                             //~va8jI~
        	hanReach++;                                            //~va8jI~
        int hanRequired=RS.swFix2 ? 2 : 1;                         //~va8jI~
        int playerEswn=Accounts.playerToEswn(PLAYER_YOU);          //~va8jI~
        int posDiscard=RAUtils.getPosTile(PtdDiscard);             //~va8jI~
        int[] itsH=RS.getItsHandEswnYou(playerEswn);               //~va8jR~
        int ctrH=RS.RSP[playerEswn].ctrHand;                             //~va8jI~
                                                                   //~va8jI~
        itsH[posDiscard]--;                                        //~va8jR~
        swNoChkEmpty=true;                                         //~va9dI~
        int ctrWin=getItsWinList(itsH,ctrH-1,itsWinWork); //output to itsWinListWork//~va8jR~
        swNoChkEmpty=false;                                        //~va9dI~
        itsH[posDiscard]++;                                        //~va8jR~
        if (ctrWin==0)                                             //~va8jR~
        {                                                          //~va8jI~
        	if (Dump.Y) Dump.println("RAReach.chkFuritenMultiWait@@@@ ctrWinList=0 return 0");//~va8jR~//~va9dR~
//          return false;                                          //~va8jI~//~va9dR~
            return true;    //empty list                           //~va9dI~
        }                                                          //~va8jI~
                                                                   //~va8jI~
        itsH[posDiscard]--;                                        //~va8jR~
        rc=chkMultiWait(playerEswn,posDiscard,hanReach,hanRequired,itsH/*dropped Discard*/,ctrH-1);//~va8jR~
        itsH[posDiscard]++;                                        //~va8jR~
        swErrFuritenReach=false;                                    //~vaj5R~
        if (rc)                                                    //~va8jI~
	    	rc=!chkFuritenReach(playerEswn,itsWinWork,ctrWin,posDiscard);//~va8jR~
//      RS.RSP[playerEswn].setReachErrFuriten(swErrFuritenReach);  //~vaj5I~//~vajdR~
        if (!RS.swFuritenReachReject)                              //~vaj4R~
        {                                                          //~vaj4R~
            if (Dump.Y) Dump.println("RAReach.chkFuritenMultiWait return true by !swFuritenReachReject");//~vaj4R~
        	rc=true;        //do Reach                             //~vaj4R~
        }                                                          //~vaj4R~
        if (Dump.Y) Dump.println("RAReach.chkFuritenMultiWait rc="+rc);//~va8jI~
        return rc;
    }                                                              //~va8jI~
    //*********************************************************    //~vajdI~
    //*from RoundStat.reachDone                                    //~vajdI~
    //*for human on Server                                         //~vajdI~
    //*return set furiten reach                                    //~vajdI~
    //*********************************************************    //~vajdI~
    public boolean chkReachDoneFuriten(int Peswn,int PposDiscard)  //~vajdR~
    {                                                              //~vajdI~
    	boolean rc;                                                //~vajdI~
        if (Dump.Y) Dump.println("RAReach.chkReachDoneFuriten eswn="+Peswn+",posDiscard="+PposDiscard);//~vajdR~
        int[] itsH=RS.getItsHandEswnYou(Peswn);                    //~vajdR~
        int ctrH=RS.RSP[Peswn].ctrHand;                            //~vajdR~
        if (Dump.Y) Dump.println("RAReach.chkReachDoneFuriten ctrH="+ctrH+",itsH="+Utils.toString(itsH,9));//~vajdI~
        swNoChkEmpty=true;                                         //~vajdI~
        itsH[PposDiscard]--;                                       //~vajdI~
        int ctrWin=getItsWinList(itsH,ctrH-1,itsWinWork); //output to itsWinListWork//~vajdR~
        itsH[PposDiscard]++;                                       //~vajdI~
        swNoChkEmpty=false;                                        //~vajdI~
        if (ctrWin==0)                                             //~vajdI~
        {                                                          //~vajdI~
        	if (Dump.Y) Dump.println("RAReach.chkFuritenMultiWait@@@@ ctrWinList=0 return 0");//~vajdI~
            return false;                                          //~vajdR~
        }                                                          //~vajdI~
        swErrFuritenReach=false;  //parm to chkFuritenReach        //~vajdI~
        itsH[PposDiscard]--;                                        //~vajdI~
	    chkFuritenReach(Peswn,itsWinWork,ctrWin,PposDiscard);      //~vajdR~
        itsH[PposDiscard]++;                                       //~vajdI~
        if (swErrFuritenReach)	                                   //~vajdI~
	        RS.RSP[Peswn].setReachStatusErrFuriten(); //set REACH_ERRFURITEN or REACH_OKFURITEN//~vajdR~
        rc=swErrFuritenReach;                                      //~vajdI~
        if (Dump.Y) Dump.println("RAReach.chkFuritenMultiWait rc="+rc);//~vajdI~
        return rc;                                                 //~vajdI~
    }                                                              //~vajdI~
    //*******************************************************      //~vapkI~
    //*from DrawnReqDlg.chkPseudoTenpai                            //~vaptI~
    //*from UAEndGame.setPendingRobot                              //~vaptI~
    //*called under shanten=0                                             //~vaptI~//~vapuR~
    //*******************************************************      //~vaptI~
    public boolean chkPsuedoTenpai(int Pplayer,int Peswn,boolean PswReach,int[] PitsHand,int PctrHand)//~vapkR~
    {                                                              //~vapkI~
    	boolean rc;                                                //~vapkI~
	    errPsuedoPending=0;                                        //~vapkI~
        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai player="+Pplayer+",eswn="+Peswn+",swReach="+PswReach+",itsHand="+Utils.toString(PitsHand,9));//~vapkR~
//      swEmptyOK=RuleSettingYaku.isPendingRankEmpty();            //~vapkI~//~vapuR~
//      swFuritenOK=RuleSettingYaku.isPendingRankFuriten();        //~vapkI~//~vapuR~
//      swFixMultiOK=RuleSettingYaku.isPendingRankFixMulti();      //~vaptI~//~vapuR~
        swRank0OK= RuleSettingYaku.isPendingRankRank0();                //~vapkI~
        swRank2Rank0OK= RuleSettingYaku.isPendingRankRank2Rank0OK();     //~vapkI~
    //    swRank2Rank1OK= RuleSettingYaku.isPendingRankRank2Rank1OK();//~vapuI~
        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai swFix2="+RS.swFix2+",swRank0OK="+swRank0OK+",swRank2Rank0OK="+swRank2Rank0OK);//~vapkR~//~vapuR~
        if (RuleSettingYaku.isPendingRankNo()) //no Shuedo-Tenpai but ronable tenpai//~vapxI~
        {                                                          //~vapxI~
		    return chkPsuedoTenpaiNo(Pplayer,Peswn,PswReach,PitsHand,PctrHand);//~vapxI~
        }                                                          //~vapxI~
        if ((!RS.swFix2 && swRank0OK)                          //~vapuR~
        ||   (RS.swFix2 && swRank2Rank0OK))                          //~vapuI~
        {                                                      //~vaptI~//~vapuR~
        	if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai eswn="+Peswn+",rc=true by Rank0OK or Ran2kRank0OK");//~vaptI~//~vapuR~
            return true;                                       //~vaptI~//~vapuR~
        }                                                          //~vaptI~//~vapuI~
        if (!RS.swFix2 && !swRank0OK)   	//1han required        //~vapuI~
        {                                                          //~vapuI~
        	if (PswReach || RAUtils.isAllInHand(Peswn))	//allow no call or ankan only//~vapuI~
            {                                                      //~vapuI~
	        	if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai eswn="+Peswn+",rc=true by 1han constraint and !swRank0OK and Reach or all in hand");//~vapuR~//~vapxR~
    	        return true;                                       //~vapuI~
            }                                                      //~vapuI~
        }                                                          //~vapuI~
//        int ctrTile=getWinListTileCtr(PitsHand,PctrHand);   //output itsWinWork and ctrWinList//~vapkI~//~vapuR~
//        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai itsWinWork="+Utils.toString(itsWinWork));//~vapkR~//~vapuR~
//        if (!swEmptyOK)                                            //~vapkI~//~vapuR~
//        {                                                          //~vapkI~//~vapuR~
//            if (ctrTile==0)                                        //~vapkI~//~vapuR~
//            {                                                      //~vapkI~//~vapuR~
//                if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai eswn="+Peswn+",rc=false by empty win tile");//~vapkR~//~vapuR~
//                errPsuedoPending=ERR_PSUEDO_PENDING_EMPTY; //            2;//~vapkR~//~vapuR~
//                return false;                                      //~vapkI~//~vapuR~
//            }                                                      //~vapkI~//~vapuR~
//        }                                                          //~vapkI~//~vapuR~
        int[] itsWL=getItsWinList(PitsHand,PctrHand);   //itsWL is new int[]//~vapuI~
        int ctrWL=itsWL.length;                                  //~vapuI~
        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai ctrWinList="+ctrWL+",itsWinList="+Utils.toString(itsWL));//~vapuI~
        if (ctrWL==0)                                              //~vapxI~
        {                                                          //~vapxI~
        	errPsuedoPending=ERR_PSUEDO_PENDING_NOT_SHANTEN0;      //~vapxI~
            return false;                                          //~vapxI~
        }                                                          //~vapxI~
//        if (PswReach)                                            //~vaptR~
//        {                                                        //~vaptR~
////            if (RS.RSP[Peswn].isReachStatusErrFuriten())    //furiten under nochk option or missed win tile after reach//~vaptR~
////            {                                                  //~vaptR~
////                if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai eswn="+Peswn+",rc=false by furitenReach under nochk option or missed win tile after Reach");//~vaptR~
////                errPsuedoPending=ERR_PSUEDO_PENDING_FURITEN_REACH; //            4;//~vaptR~
////                return false;                                  //~vaptR~
////            }                                                  //~vaptR~
//            //*no need chk 2han constrint because assuming ron by take//~vaptR~
//            if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai eswn="+Peswn+",rc=true(furiten was chked,no need chk fix1,kataagari) by reach");//~vaptR~
//            return true;                                         //~vaptR~
//        }                                                        //~vaptR~
        if (PswReach)                                              //~vapxI~
        {                                                          //~vapxI~
            //*no need chk 2han constrint because assuming ron by take//~vapxI~
            if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai eswn="+Peswn+",rc=true by reach(furiten OK)");//~vapxI~
            return true;                                           //~vapxI~
        }                                                          //~vapxI~
//        if (!swFuritenOK)                                          //~vaptR~//~vapuR~
//        {                                                          //~vaptR~//~vapuR~
//            if (RS.RSP[Peswn].isReachStatusErrFuriten())    //furiten under nochk option or missed win tile after reach//~vaptI~//~vapuR~
//            {                                                      //~vaptI~//~vapuR~
//                if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai eswn="+Peswn+",rc=false by furitenReach under nochk option or missed win tile after Reach");//~vaptI~//~vapuR~
//                errPsuedoPending=ERR_PSUEDO_PENDING_FURITEN_REACH; //            4;//~vaptI~//~vapuR~
//                return false;                                      //~vaptI~//~vapuR~
//            }                                                      //~vaptI~//~vapuR~
//            if (isPsuedoTenpaiFuriten(Peswn,itsWinWork,ctrWinList,false/*chkLast*/))//~vaptR~//~vapuR~
//            {                                                      //~vaptR~//~vapuR~
//                if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai eswn="+Peswn+",rc=false by Furiten");//~vaptR~//~vapuR~
//                errPsuedoPending=ERR_PSUEDO_PENDING_FURITEN; //            3;//~vaptR~//~vapuR~
//                return false;                                      //~vaptR~//~vapuR~
//            }                                                      //~vaptR~//~vapuR~
//        }                                                          //~vaptR~//~vapuR~
//        if (RS.swFix2)                                           //~vaptR~
//        {                                                        //~vaptR~
////          rc=chkPsuedoTenpai2(Pplayer,Peswn,PitsHand,PctrHand,swRank0OK,itsWinWork,ctrWinList);//~vaptR~
//            rc=chkPsuedoTenpai2(Pplayer,Peswn,PitsHand,PctrHand,itsWinWork,ctrWinList);//~vaptR~
//            if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai swFix2=true eswn="+Peswn+",rc="+rc);//~vaptR~
//            if (!rc)                                             //~vaptR~
//                errPsuedoPending=ERR_PSUEDO_PENDING_FIX2; //            6;//~vaptR~
//            return rc;                                           //~vaptR~
//        }                                                        //~vaptR~
//        if (swRank0OK)                                           //~vaptR~
//        {                                                        //~vaptR~
//            if (Dump.Y) Dump.println("DrawnReqDlgLast.chkPsuedoTenpai eswn="+Peswn+",rc=true by swRanbk0OK");//~vaptR~
//            return true;                                         //~vaptR~
//        }                                                        //~vaptR~
//      rc=isPsuedoTenpaiRonable(Pplayer,Peswn,PitsHand,PctrHand,itsWinWork,ctrWinList,false/*swChkFix2*/);//~vaptR~
//      rc=isPsuedoTenpaiRonable(Pplayer,Peswn,PitsHand,PctrHand,itsWinWork,ctrWinList,PswReach);//~vaptI~//~vapuR~
        rc=isPsuedoTenpaiRonable(Pplayer,Peswn,PitsHand,PctrHand,itsWL,ctrWL,PswReach);//~vapuI~
//      if (!rc)                                                   //~vaptR~
//  		errPsuedoPending=ERR_PSUEDO_PENDING_FIX1; //            5;//~vaptR~
//        if (!swFuritenOK)                                          //~vaptI~//~vapuR~
//            if (errMultiWait==0)                                   //~vaptR~//~vapuR~
//            {                                                      //~vaptI~//~vapuR~
//                if (isPsuedoTenpaiFuriten(Peswn,itsWinWork,ctrWinList,true/*chkLast*/)) //*chk last if ronable//~vaptR~//~vapuR~
//                {                                                  //~vaptI~//~vapuR~
//                    errPsuedoPending=ERR_PSUEDO_PENDING_FURITEN; //            3;//~vaptI~//~vapuR~
//                    rc=false;                                      //~vaptI~//~vapuR~
//                    if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai eswn="+Peswn+",rc=false by Furiten of chkLast");//~vaptI~//~vapuR~
//                }                                                  //~vaptI~//~vapuR~
//            }                                                      //~vaptI~//~vapuR~
        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai rc="+rc+",eswn="+Peswn);//~vapkR~//~vapuR~
        return rc;
    }                                                              //~vapkI~
    //*******************************************************      //~vapxI~
    public boolean chkPsuedoTenpaiNo(int Pplayer,int Peswn,boolean PswReach,int[] PitsHand,int PctrHand)//~vapxI~
    {                                                              //~vapxI~
    	boolean rc;                                                //~vapxI~
	    errPsuedoPending=0;                                        //~vapxI~
        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpaiNo player="+Pplayer+",eswn="+Peswn+",swReach="+PswReach+",itsHand="+Utils.toString(PitsHand,9));//~vapxI~
        int[] itsWL=getItsWinList(PitsHand,PctrHand);   //itsWL is new int[]//~vapxI~
        int ctrWL=itsWL.length;                                    //~vapxI~
        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpaiNo ctrWinList="+ctrWL+",itsWinList="+Utils.toString(itsWL));//~vapxI~
        if (ctrWL==0)                                              //~vapxI~
        {                                                          //~vapxI~
        	errPsuedoPending=ERR_PSUEDO_PENDING_NOT_SHANTEN0;      //~vapxI~
            return false;                                          //~vapxI~
        }                                                          //~vapxI~
        if (PswReach)                                              //~vapxR~
        {                                                          //~vapxR~
            if (RS.RSP[Peswn].isReachStatusErrFuriten(false/*swTake*/))    //err even if furitenReach OK//~vapxR~
            {                                                      //~vapxR~
                if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpaiNo eswn="+Peswn+",rc=false by furitenReach under nochk option or missed win tile after Reach");//~vapxR~
                errPsuedoPending=ERR_PSUEDO_PENDING_FURITEN_REACH; //            4;//~vapxR~
                return false;                                      //~vapxR~
            }                                                      //~vapxR~
        	if (!RS.swFix2)                                        //~vapxI~
            {                                                      //~vapxI~
                if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpaiNo eswn="+Peswn+",rc=true by reach and not 2-han constraint");//~vapxI~
                return true;                                       //~vapxI~
            }                                                      //~vapxI~
        }                                                          //~vapxR~
        rc=isPsuedoTenpaiRonableNo(Pplayer,Peswn,PitsHand,PctrHand,itsWL,ctrWL,PswReach);//~vapxI~
        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpaiNo rc="+rc+",eswn="+Peswn);//~vapxI~
        return rc;                                                 //~vapxI~
    }                                                              //~vapxI~
    //*******************************************************      //~vaptR~//~vapuR~//~vapxR~
//  private boolean isPsuedoTenpaiFuriten(int Peswn,int[] PitsWinList,int PctrWinList)//~vaptR~//~vapuR~//~vapxR~
    private boolean isPsuedoTenpaiFuriten(int Peswn,int[] PitsWinList,int PctrWinList,boolean PswChkLast)//~vaptI~//~vapuR~//~vapxR~
    {                                                              //~vaptR~//~vapuR~//~vapxR~
        boolean rc=false;                                          //~vaptR~//~vapuR~//~vapxR~
        if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiFuriten swChkLast="+PswChkLast+",eswn="+Peswn+",ctrWinList="+PctrWinList+",itsWinList="+Utils.toString(PitsWinList));//~vaptR~//~vapuR~//~vapxR~
        for (int ii=0;ii<PctrWinList;ii++)                         //~vaptR~//~vapuR~//~vapxR~
        {                                                          //~vaptR~//~vapuR~//~vapxR~
            int pos=PitsWinList[ii];                               //~vaptR~//~vapuR~//~vapxR~
//          if (RS.RSP[Peswn].isFuritenDrawnLast(pos))             //~vaptR~//~vapuR~//~vapxR~
            if (RS.RSP[Peswn].isFuritenDrawnLast(pos,PswChkLast))  //~vaptI~//~vapuR~//~vapxR~
            {                                                      //~vaptR~//~vapuR~//~vapxR~
                if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiFuriten true at pos="+pos);//~vaptR~//~vapuR~//~vapxR~
                rc=true;                                           //~vaptR~//~vapuR~//~vapxR~
                break;                                             //~vaptR~//~vapuR~//~vapxR~
            }                                                      //~vaptR~//~vapuR~//~vapxR~
        }                                                          //~vaptR~//~vapuR~//~vapxR~
        if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiFuriten rc="+rc);//~vaptR~//~vapuR~//~vapxR~
        return rc;                                                 //~vaptR~//~vapuR~//~vapxR~
    }                                                              //~vaptR~//~vapuR~//~vapxR~
    //*******************************************************      //~vapkI~
//  private boolean isPsuedoTenpaiRonable(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,int[] PitsWinList,int PctrWinList,boolean PswChkFix2)//~vaptR~
    private boolean isPsuedoTenpaiRonable(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,int[] PitsWinList,int PctrWinList,boolean PswReach)//~vaptI~
    {                                                              //~vapkI~
//  	boolean rc=false;                                          //~vappR~
//  	boolean rc=true;                                           //~vappI~//~vapuR~
        if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonable entry player="+Pplayer+",eswn="+Peswn+",PswReach="+PswReach+",ctrWinList="+PctrWinList+",itsWinList="+Utils.toString(PitsWinList)+",itsHand="+Utils.toString(PitsHand,9));//+vapkR~//~vaptR~//+vapxR~
        errMultiWait=0;                                        //~vapqI~//~vaptR~
//        for (int ii=0;ii<PctrWinList;ii++)                         //~vapkI~//~vapuR~
//        {                                                          //~vapkI~//~vapuR~
//            int pos=PitsWinList[ii];                               //~vapkI~//~vapuR~
//            PitsHand[pos]++;                                      //~vapkI~//~vapuR~
//            int ctrHand=PctrHand+1;                                //~vapkI~//~vapuR~
////          boolean rc2=AG.aRARon.isRonableMultiWaitDrawnLast(true/*swTake*/,Pplayer,Peswn,PitsHand,ctrHand,pos,PswChkFix2);  //ronable as tusmo; 1 han constarint, with fixlast(kataagari ok//~vapqR~//~vapuR~
////          int rc2=AG.aRARon.isRonableMultiWaitDrawnLast(true/*swTake*/,Pplayer,Peswn,PitsHand,ctrHand,pos,PswChkFix2);  //ronable as tusmo; 1 han constarint, with fixlast(kataagari ok//~vaptR~//~vapuR~
//            int rc2=AG.aRARon.isRonableMultiWaitDrawnLast(true/*swTake*/,Pplayer,Peswn,PitsHand,ctrHand,pos,PswReach);  //ronable as tusmo; 1 han constarint, with fixlast(kataagari ok//~vaptI~//~vapuR~
//            PitsHand[pos]--;                                      //~vapkI~//~vapuR~
////          if (rc2)                                               //~vappR~//~vapuR~
////          {                                                      //~vappR~//~vapuR~
////              if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonable true by 1/2 han constraint ok at pos="+pos+",chkFix2="+PswChkFix2);//~vappR~//~vapuR~
////              rc=true;                                           //~vappR~//~vapuR~
////              break;                                             //~vappR~//~vapuR~
////          }                                                      //~vappR~//~vapuR~
////          if (!rc2)                                              //~vapqR~//~vapuR~
//            if (rc2!=0)                                            //~vapqI~//~vapuR~
//            {                                                      //~vapqR~//~vapuR~
//                if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonable false by rc of RARon.isRonableMultiWaitDrawnLast pos="+pos);//~vapqR~//~vapuR~
////              rc=false;                                          //~vaptR~//~vapuR~
////              errMultiWait=rc2;                                  //~vaptR~//~vapuR~
//                errMultiWait|=rc2;                                 //~vaptI~//~vapuR~
////              break;                                             //~vaptR~//~vapuR~
//            }                                                      //~vapqR~//~vapuR~
//        }                                                          //~vapkI~//~vapuR~
    	boolean rc=false;                                          //~vapuI~
        for (int ii=0;ii<PctrWinList;ii++)                         //~vapuI~
        {                                                          //~vapuI~
            int pos=PitsWinList[ii];                               //~vapuI~
            PitsHand[pos]++;                                       //~vapuI~
            int ctrHand=PctrHand+1;                                //~vapuI~
            int rc2=AG.aRARon.isRonableMultiWaitDrawnLast(true/*swTake*/,Pplayer,Peswn,PitsHand,ctrHand,pos,PswReach);  //ronable as tusmo; 1 han constarint, with fixlast(kataagari ok//~vapuR~
            PitsHand[pos]--;                                       //~vapuI~
            if ((rc2 & RARON_ERR_CONSTRAINT)==0)	//furiten,fixerr may exist//~vapuR~
            {                                                      //~vapuI~
                if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonable true by 1/2 han constraint ok at pos="+pos+",swReach="+PswReach);//~vapuI~
                rc=true;                                           //~vapuI~
                break;                                             //~vapuI~
            }                                                      //~vapuI~
        }                                                          //~vapuI~
        if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonable after all win tile errMultiWait="+Integer.toHexString(errMultiWait));//~vaptR~
//        if (RS.swFix2)                                           //~vaptR~
//        {                                                        //~vaptR~
//            if (swRank2Rank0OK)                                  //~vaptR~
//                errMultiWait &= ~RARON_ERR_CONSTRAINT;           //~vaptR~
//        }                                                        //~vaptR~
//        else                                                     //~vaptR~
//        {                                                        //~vaptR~
//            if (swRank0OK)                                       //~vaptR~
//                errMultiWait &= ~RARON_ERR_CONSTRAINT;           //~vaptR~
//        }                                                        //~vaptR~
//        if (swFuritenOK)                                         //~vaptR~
//        {                                                        //~vaptR~
//            errMultiWait &= ~(RARON_ERR_FURITEN | RARON_ERR_FURITEN_REACH);//~vaptR~
//                            | RARON_ERR_MULTIPLE | RARON_ERR_FIX | RARON_ERR_FIXFIRST | RARON_ERR_FIXMIDDLE);//~vaptR~
//        }                                                        //~vaptR~
//      if ((errMultiWait & RARON_ERR_FURITEN)!=0)                 //~vaptR~
//          errPsuedoPending=ERR_PSUEDO_PENDING_FURITEN;           //~vaptR~
//      else                                                       //~vaptR~
//        if ((errMultiWait & RARON_ERR_MULTIPLE)!=0)                //~vaptM~//~vapuR~
//        {                                                          //~vaptI~//~vapuR~
//            if (!swFixMultiOK)                                     //~vaptI~//~vapuR~
//                errPsuedoPending=ERR_PSUEDO_PENDING_MULTIPLE;      //~vaptR~//~vapuR~
//        }                                                          //~vaptI~//~vapuR~
//        else                                                       //~vaptM~//~vapuR~
//        if ((errMultiWait & RARON_ERR_FIX)!=0)                     //~vaptM~//~vapuR~
//        {                                                          //~vaptI~//~vapuR~
//            if (!swFixMultiOK)                                     //~vaptI~//~vapuR~
//                errPsuedoPending=ERR_PSUEDO_PENDING_FIX;           //~vaptR~//~vapuR~
//        }                                                          //~vaptI~//~vapuR~
//        else                                                       //~vaptI~//~vapuR~
//      if ((errMultiWait & RARON_ERR_CONSTRAINT)!=0)              //~vaptI~//~vapuR~
        if (!rc)                                                   //~vapuI~
        {                                                          //~vaptI~
        	if (RS.swFix2)                                         //~vaptI~
	            errPsuedoPending=ERR_PSUEDO_PENDING_FIX2;          //~vapqI~
            else                                                   //~vapqI~
	            errPsuedoPending=ERR_PSUEDO_PENDING_FIX1;          //~vapqI~
        }                                                          //~vapqI~
        rc=errPsuedoPending==0;                                    //~vaptR~
        if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonable rc="+rc+",errMultiWait="+Integer.toHexString(errMultiWait)+",errPsudoPending="+errPsuedoPending);//~vapqR~
        return rc;
    }                                                              //~vapkI~
    //*******************************************************      //~vapxI~
    private boolean isPsuedoTenpaiRonableNo(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,int[] PitsWinList,int PctrWinList,boolean PswReach)//~vapxI~
    {                                                              //~vapxI~
        if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonableNo player="+Pplayer+",eswn="+Peswn+",PswReach="+PswReach+",ctrWinList="+PctrWinList+",itsWinList="+Utils.toString(PitsWinList)+",itsHand="+Utils.toString(PitsHand,9));//~vapxI~
        if (isPsuedoTenpaiFuriten(Peswn,PitsWinList,ctrWinList,true/*chkLast*/)) //*chk last if ronable//~vapxI~
        {                                                          //~vapxI~
            if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonableNo reurn false by isFuriten");//~vapxI~
        	errPsuedoPending=ERR_PSUEDO_PENDING_FURITEN;           //~vapxI~
            return false;                                          //~vapxI~
        }                                                          //~vapxI~
        errMultiWait=0;                                            //~vapxI~
//      boolean swMulti=false,swConstraintNG=false;                //~vapxR~//~vaq1R~
        int ctr2hanY=0,ctr2hanN=0;                                 //~vaq1R~
        for (int ii=0;ii<PctrWinList;ii++)                         //~vapxI~
        {                                                          //~vapxI~
            int pos=PitsWinList[ii];                               //~vapxI~
//            if (RS.RSP[Peswn].isFuritenDrawnLast(pos,true/*swChkLast*/))//~vapxR~
//            {                                                    //~vapxR~
//                if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonableNo isFuriten=true at pos="+pos);//~vapxR~
//                errMultiWait|=RARON_ERR_FURITEN;                 //~vapxR~
//                break;                                           //~vapxR~
//            }                                                    //~vapxR~
            int ctrHand=PctrHand;                                  //~vapxR~
            int rc2=AG.aRARon.isRonableMultiWaitDrawnLastNo(false/*swTake*/,Pplayer,Peswn,PitsHand,ctrHand,pos,PswReach);  //ronable as Ron, ; 1 han constarint, with fixlast(kataagari ok//~vapxR~
            if (rc2>=2)                                            //~vaq1R~
            {                                                      //~vaq1R~
                if ((rc2 & RARON_ERR_FIX)==0)  //no FIX_FIRST/FIX_MIDDLE vioration//~vaq1R~
	            	ctr2hanY++; //valid win tile for 2han constraint//~vaq1R~
            }                                                      //~vaq1R~
            else                                                   //~vaq1R~
            	ctr2hanN++; //2han constarint err in 2han constraint env//~vaq1R~
            if (rc2!=0)                                            //~vapxI~
            {                                                      //~vapxI~
                errMultiWait|=rc2;                                 //~vapxI~
//  	      	if ((errMultiWait & RARON_ERR_CONSTRAINT)!=0)      //~vapxR~//~vaq1R~
//              	swConstraintNG=true;                           //~vapxR~//~vaq1R~
            }                                                      //~vapxI~
//          else                                                   //~vapxR~//~vaq1R~
//          	swMulti=true;                                      //~vapxR~//~vaq1R~
            if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonableNo rc2="+Integer.toHexString(rc2)+",errMultiWait="+Integer.toHexString(errMultiWait));//~vapxI~
        }                                                          //~vapxI~
//      if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonableNo swFix2="+RS.swFix2+",swMulti="+swMulti+",swConstraintNG="+swConstraintNG);//~vapxR~//~vaq1R~
//      if (RS.swFix2 && swConstraintNG && swMulti)	//2han constraint,kataagari//~vapxR~//~vaq1R~
//      {                                                          //~vapxR~//~vaq1R~
//          if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonableNo set kataagari err in 2 han constraint");//~vapxR~//~vaq1R~
//      	errMultiWait|=RARON_ERR_MULTIPLE;	//kataagari in 2 han constraint//~vapxR~//~vaq1R~
//      }                                                          //~vapxR~//~vaq1R~
        if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonableNo ctr2hanY="+ctr2hanY+",ctr2hanN="+ctr2hanN+",swFix2="+RS.swFix2);//~vaq1R~
        if (RS.swFix2)	//2han constraint,kataagari,UARank not chk kataagari if YAKU_FIX_LAST//~vaq1R~
        {                                                          //~vaq1R~
            if (ctr2hanY!=0 && ctr2hanN!=0)                        //~vaq1R~
        	errMultiWait|=RARON_ERR_MULTIPLE;	//kataagari in 2 han constraint//~vaq1R~
            if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonableNo set kataagari err in 2 han constraint");//~vaq1R~
        }                                                          //~vaq1R~
        if (errMultiWait!=0)                                       //~vapxI~
        {                                                          //~vapxI~
      		if ((errMultiWait & RARON_ERR_FURITEN)!=0)             //~vapxI~
          		errPsuedoPending=ERR_PSUEDO_PENDING_FURITEN;       //~vapxI~
      		else                                                   //~vapxI~
      		if ((errMultiWait & RARON_ERR_FURITEN_REACH)!=0)       //~vapxI~
          		errPsuedoPending=ERR_PSUEDO_PENDING_FURITEN_REACH; //~vapxI~
      		else                                                   //~vapxI~
        	if ((errMultiWait & RARON_ERR_MULTIPLE)!=0)            //~vapxI~
            {                                                      //~vaq1R~
  			  if (RS.swFix2)                                       //~vaq1R~
                errPsuedoPending=ERR_PSUEDO_PENDING_MULTIPLE_FIX2; //~vaq1R~
              else                                                 //~vaq1R~
                errPsuedoPending=ERR_PSUEDO_PENDING_MULTIPLE;      //~vapxI~
            }                                                      //~vaq1R~
	        else                                                   //~vapxI~
    		if ((errMultiWait & RARON_ERR_FIX)!=0)                 //~vapxI~
                errPsuedoPending=ERR_PSUEDO_PENDING_FIX;           //~vapxI~
        	else                                                   //~vapxI~
	      	if ((errMultiWait & RARON_ERR_CONSTRAINT)!=0)          //~vapxI~
            {                                                      //~vapxI~
        		if (RS.swFix2)                                     //~vapxI~
	            	errPsuedoPending=ERR_PSUEDO_PENDING_FIX2;      //~vapxI~
            	else                                               //~vapxI~
	            	errPsuedoPending=ERR_PSUEDO_PENDING_FIX1;      //~vapxI~
            }                                                      //~vapxI~
            else                                                   //~vapxI~
	            errPsuedoPending=ERR_PSUEDO_PENDING_UNKNOWN;       //~vapxI~
        }                                                          //~vapxI~
        boolean rc=errPsuedoPending==0;                                    //~vapxI~
        if (Dump.Y) Dump.println("RAReach.isPsuedoTenpaiRonableNo rc="+rc+",errMultiWait="+Integer.toHexString(errMultiWait)+",errPsudoPending="+errPsuedoPending);//~vapxR~
        return rc;                                                 //~vapxI~
    }                                                              //~vapxI~
//    //*******************************************************    //~vaptR~
//    //*RS.swFix2:on, chk keiten option of 2han constraint        //~vaptR~
//    //*******************************************************    //~vaptR~
////  private boolean chkPsuedoTenpai2(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,boolean PswRank0OK,int[] PitsWinList,int PctrWinList)//~vaptR~
//    private boolean chkPsuedoTenpai2(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,int[] PitsWinList,int PctrWinList)//~vaptR~
//    {                                                            //~vaptR~
//        if (swRank2Rank0OK)                                      //~vaptR~
//        {                                                        //~vaptR~
//            if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai2 eswn="+Peswn+",rc=true by Rank2Rank0OK");//~vaptR~
//            return true;                                         //~vaptR~
//        }                                                        //~vaptR~
//        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai2 eswn="+Peswn+",ctrWinList="+PctrWinList+",itsWinList="+Utils.toString(PitsWinList)+",itsHand="+Utils.toString(PitsHand,9));//~vaptR~
////      boolean swChkFix2=(swRank0OK)?false:true;   //need 2 han if 1 han constraint=No//~vaptR~
//        boolean swChkFix2=true; //need 2 han                     //~vaptR~
//        boolean rc=isPsuedoTenpaiRonable(Pplayer,Peswn,PitsHand,PctrHand,PitsWinList,PctrWinList,swChkFix2);//~vaptR~
//        if (Dump.Y) Dump.println("RAReach.chkPsuedoTenpai2 rc="+rc);//~vaptR~
//        return rc;                                               //~vaptR~
//    }                                                            //~vaptR~
}//class RAReach
