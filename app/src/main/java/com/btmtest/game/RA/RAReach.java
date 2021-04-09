//*CID://+va70R~: update#= 128;                                    //~va70R~
//**********************************************************************
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/01/07 va60 CalcShanten
//**********************************************************************
package com.btmtest.game.RA;
import com.btmtest.TestOption;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Robot;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
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
    private int[]     itsHandValue,itsHand,itsHandPos;             //~1122R~
    private int[]     itsReachPos= new int[HANDCTR_TAKEN];//~1122I~
    private int       ctrReachPos;                                 //~1122I~
    public  int ctrWinTotal;    //summ of tilectr of wintile       //~1310R~
    private int ctrWinList;	//type ctr of wintile                                        //~1224I~//~1310I~
    private int playerReach,hanRequired,hanAdd,posTaken,ctrHand,playerEswn;           //~1122R~
    private boolean swDoReach;
    private int hanMax;                                            //~1122I~
    private boolean swSkipReach;                                   //~1215I~
    private int hanExceptDora;                                     //~1219I~
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
	        if (Dump.Y) Dump.println("RAReach.callReach return -1; not all in hand");//~1122M~
        	return 0;                                              //~1122M~
        }                                                          //~1122M~
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
        if (Dump.Y) Dump.println("RAReach.selectDiscard playerEswn="+playerEswn+",ctrHand="+ctrHand+",itsHand="+Utils.toString(itsHand,9));
        int remain=RAUtils.getCtrRemain();                         //~1311I~
        if (remain<HV_AVOID_REACH_BY_REMAINING_CTR)    //<3*4 remaining tile//~1311I~
        {                                                          //~1311I~
            swSkipReach=true;   //yet select discard               //~1311I~
            if (Dump.Y) Dump.println("RAReach.selectDiscard swSkipReach=true by remain="+remain);//~1311I~
        }                                                          //~1311I~
        int hanMaxMax=0;                                           //~1122I~
        boolean swDiscardableAll=RS.isDiscardableAll();            //~1126I~
        int posOld=-1;                                             //~1216I~
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
	    	int v=evaluateWinList(playerEswn,pos,itsHand,ctrHand-1); //output to itsWinWork and set swSkipReach//~1122R~//~1215R~
            itsHand[pos]++;                                       //~1122I~
            if (v>0)                                               //~1122I~
            {                                                      //~1122I~
//          	if (swDoReach)                                     //~1122I~//~1215R~
            	if (swDoReach && !swSkipReach)                     //~1215I~
                {                                                  //~1122I~
        			if (Dump.Y) Dump.println("RAReach.selectDiscard doReach pos="+pos+",v="+v+",old itsHandValue["+ii+"]="+itsHandValue[ii]);//~1218I~//~1219R~
	                itsHandValue[ii]+=DV_REACH+v;	//		         = 3000000;		//discard for reach//~1122R~//~1216R~//~1218R~
	                itsHandValue[ii]+=AG.aRADSEval.adjustByTileForReach(playerEswn,pos,ii,itsHand,ctrHand);	   //		//discard for reach//+va70R~
        			if (Dump.Y) Dump.println("RAReach.selectDiscard doReach new pos="+pos+",itsHandValue["+ii+"]="+itsHandValue[ii]);//~1218I~//~1219R~
                    itsReachPos[ctrReachPos++]=pos;                //~1122R~
                    if (hanMaxMax<hanMax)                          //~1122I~
                        hanMaxMax=hanMax;                          //~1122I~
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
        if (Dump.Y) Dump.println("RAReach.selectDiscard return swDoReach="+swDoReach+",hanMaxMax="+hanMaxMax+",ctrReachPos="+ctrReachPos+".itsHandValue="+Utils.toString(itsHandValue,-1,ctrHand));//~1122R~//~1124R~//~1125R~//~1206R~
        return hanMaxMax;                                          //~1122R~
    }
    //*****************************************************************
    //*return max Han, by minus if skip reach by furiten or Fix2   //~1328R~
    //*****************************************************************
    private int evaluateWinList(int PplayerEswn,int PposTryDiscard,int[] PitsHand/*dropped Discard*/,int PctrHand)
    {
        int ctrWinTile=0,han;
        //************************
        int intent=RS.RSP[PplayerEswn].intent;                     //~1224M~
        if (Dump.Y) Dump.println("RAReach.evaluateWinList playerEswn="+PplayerEswn+",intent="+Integer.toHexString(intent)+",posTryDiscard="+PposTryDiscard+",ctrHand="+PctrHand+",itsWinListHand="+Utils.toString(PitsHand,9));//~1206R~//~1224R~
        getItsWinList(PitsHand,PctrHand,itsWinWork); //output to itsWinListWork//~1224R~//~1310R~
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
	        if (Dump.Y) Dump.println("RAReach.evaluateWinList posWon="+pos+",han="+han+",hanRequired="+hanRequired+",hanExceptDora="+hanExceptDora+",ctrWinTotal="+ctrWinTotal);//~1220I~
//          if (han<hanRequired)  //0 if double reach, else 2 if fix2 constraint  else 1//~1219R~
            if (hanExceptDora+1/*of reach*/<hanRequired)  //0 if double reach, else 2 if fix2 constraint  else 1//~1219I~//~1220R~
            {                                                      //~1220I~
		        if (Dump.Y) Dump.println("RAReach.evaluateWinList@@@@ skipReach by hanExceptDora");//~1220I~//~1307R~
            	swSkipReach=true;
            }                                                      //~1220I~
            else                                                   //~1220I~
            if (RS.isFuriten(PplayerEswn,pos))
            {                                                      //~1220I~
		        if (Dump.Y) Dump.println("RAReach.evaluateWinList@@@@ skipReach by furten");//~1220I~//~1307R~
            	swSkipReach=true;
            }                                                      //~1220I~
            if (han>hanMax)
            	hanMax=han;
        }
	    int ctrTaken=RS.RSP[PplayerEswn].ctrTaken;                 //~1215I~
        if (Dump.Y) Dump.println("RAReach.evaluateWinList ctrWinTotal="+ctrWinTotal+",ctrTaken="+ctrTaken);//~1221I~
	    if (!(ctrWinTotal>=HV_CTRWIN_TO_REACH_FORCE && hanMax>=HV_HAN_TO_FORCE_REACH))	//Not special high value of 13han even winlistctr>=1//~1217R~
        {                                                          //~1215I~
        	if ((intent & INTENT_7PAIR)!=0 && ctrWinTotal>0)       //~1224I~
            {                                                      //~1224I~
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
        	if (!swWaitingWordTile && ctrWinList==1 && ctrWinTotal<HV_CTRWIN_TO_REACH_ONE_TYPE && ctrTaken<HV_CTR_TO_WAIT_REACH_EARLY) //tanki/kanchan/penshan//~1224I~
        	//**                                       ctrWinTotal<4                           && ctrTaken<8                         ) //tanki/kanchan/penshan//~1224I~
            {                                                      //~1224I~
        		if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by early take and wintile ctr and winlist=1");//~1224I~
	        	swSkipReach=true;                                  //~1224I~
            }                                                      //~1224I~
            else                                                   //~1224I~
	        if (ctrWinTotal<HV_CTRWIN_TO_REACH_EARLY && ctrTaken<HV_CTR_TO_WAIT_REACH_EARLY)//winctr<4 & take<8//~1216R~//~1218R~
	        //**ctrWinTotal<4                        && ctrTaken<8                          //winctr<4 & take<8//~1224I~
            {                                                      //~1216I~
        		if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by early take and wintile ctr");//~1216I~
	        	swSkipReach=true;
            }                                                      //~1216I~
            else                                                   //~1215I~
        	if (ctrWinTotal<HV_CTRWIN_TO_REACH_2ND && AG.aPlayers.getCtrReachedPlayer(playerReach)!=0) //wintile<4//~1306I~
            {                                                      //~1306I~
        		if (Dump.Y) Dump.println("RAReach.evaluateWinList @@@@ skipreach by other player reach and wintile ctr="+ctrWinTotal);//~1306I~//~1308R~
	        	swSkipReach=true;                                  //~1306I~
            }                                                      //~1306I~
            else                                                   //~1306I~
        	if (ctrWinTotal<HV_CTRWIN_TO_REACH) //wintile<2                    //~1215I~//~1216R~//~1218R~
            {                                                      //~1216I~
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
        if (swSkipReach)
        {                                                          //~1215I~
	        valueTotal=-valueTotal;                                //~1120R~
        }                                                          //~1215I~
	    int rc=valueTotal*DV_REACH_BYVALUE;  //100000              //~1122I~//~1219R~
        if (Dump.Y) Dump.println("RAReach.evaluateWinList posDiscard="+PposTryDiscard+",valueTotal="+valueTotal+",rc="+rc+",hanMax="+hanMax+",ctrWinTotal="+ctrWinTotal+",ctrTake="+ctrTaken+",skipReach="+swSkipReach);//~1122I~//~1124R~//~1215R~//~1216R~
        return rc;                                                 //~1122R~
    }
    //*****************************************************************
    //*to evaluate winlist winList,and 2han constraint chk         //~1219I~
    //*****************************************************************//~1219I~
    private int getValue(int[] PitsHand/*dropped Discard*/,int PctrHand,int PposWin)
    {
        if (Dump.Y) Dump.println("RAReach.getValue posWin="+PposWin);
        PitsHand[PposWin]++;                                           //~1121I~
    	int han=AG.aRARon.getRonValueExceptDora(playerReach,PitsHand,PposWin);//han as not taken//~1219R~
        PitsHand[PposWin]--;                                           //~1121I~
        hanExceptDora=AG.aRARon.hanExceptDora;                     //~1219I~
        if (Dump.Y) Dump.println("RAReach.getValue playerReach="+playerReach+",posWin="+PposWin+",han="+han+",hanExceptDora="+hanExceptDora+",ctrHand="+PctrHand);//~1219R~
        return han;
    }
    //*********************************************************
    //*return list of win tile pos list
    //*********************************************************
    public int[] getItsWinList(int[] PitsHand,int PctrHand)
    {
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
        if (Dump.Y) Dump.println("RAReach.getWinList0 ctrWinList="+ctrWinList+",itsWin="+Utils.toStringMax(PitsWin,ctrWinList));//~1124R~//~1125R~//~1310R~
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
        if (!RAUtils.isEmpty(PitsTile,Ppos)) //all exposed         //~1124R~
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
        if (Dump.Y) Dump.println("RAReach.getWinList_Standard_Sub ctrWin="+ctrWin+",pos="+Ppos);
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
}//class RAReach
