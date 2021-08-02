//*CID://+vab5R~: update#= 187;                                    //~vaaUR~//~vab5R~
//**********************************************************************//~v101I~
//2021/07/25 vab5 itsHandValue up by hanMaxed should be by rank exceptDora//~vaaUI~
//2021/07/19 vaaU chankan was not notified                         //~vaaUI~
//2021/07/19 vaaT addtional to VaaS, When Chankan, lastDiscarder is not kan adder//~vaaTI~
//2021/07/18 vaaS (Bug of vaaR)need set data to sendmsg GCM_RON for also robot ron//~vaaSI~
//2021/07/05 vaap (Bug)getValue for evaluate Reach should not timing yakuman//~vaapI~
//2021/07/03 vaaj (Bug)select large han/point even if amt is same(hanMax was not set)//~vaaiI~
//2021/06/30 vaad (Bug)PlayAlone mode,did not notify kan if kan not in deal. maintaine ItsHand also for MatcNotify mode//~vaadI~
//2021/06/30 vaa8 even PlayAlone mode, chk kataagari/furiten/sakiduke for human according RuleSetting.//~vaa8I~
//2021/06/28 vaa4 (Bug)for 13/14 broken(at 1st take), Robot ronvalue=Tenho. Not harmfull because CompReqDlg addYaku 13 broken at Point button.//~vaa4I~
//2021/06/15 va98 allow multiwait for take with allInHand          //~va98I~
//2021/06/14 va96 When win button pushed in Match mode, issue warning for not ronable hand.//~va96I~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/04/29 va8u (Bug)ignore furiten/kataagari Take not AllInHand,chk sakazuke condition only//~va8uR~
//2021/04/25 va8m NOT va8m, not for all Draw but Conceild Draw only because YakuLast is not checked now//~va8mI~
//2021/04/25 va8k KataAgari OK for all Draw(+pon/kan/chii) regardless fix option//~va8kI~
//2021/04/20 va8j KataAgari chk+furiten chk for also Human Take in PlayAloneNotifyMode//~va8jI~
//2021/04/20 va8i KataAgari chk for also Robot Take                //~va8iI~
//2021/04/18 va8f KataAgari chk                                    //~va8fI~
//2021/04/17 va8c for robot ron,2 hancontraint should ignore rinshan,haitei,one shot//~va8cI~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//**********************************************************************//~1107I~
package com.btmtest.game.RA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.Accounts;
import com.btmtest.game.Robot;
import com.btmtest.game.TileData;
import com.btmtest.game.UA.Rank;
import com.btmtest.game.UA.RonResult;
import com.btmtest.game.gv.GMsg;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.gv.Pieces.*;
//********************************************************************************************//~v@@5R~
//player:position of each player on the device; You area always 0(Hands is show at bottom)//~v@@5R~
//********************************************************************************************//~v@@5R~
public class RARon                                               //~v@@@R~//~va60R~//~1111R~
{                                                                  //~0914I~
   public static final int RARON_ERR_FURITEN  =0x01;               //~va96I~
   public static final int RARON_ERR_FIX      =0x02;               //~va96I~
   public static final int RARON_ERR_FIXFIRST =0x04;               //~va96I~
   public static final int RARON_ERR_FIXMIDDLE=0x08;               //~va96I~
   public static final int RARON_ERR_CONSTRAINT =0x10;             //~va96I~
   public static final int RARON_ERR_CONSTRAINT1=0x20;             //~va96I~
   public static final int RARON_ERR_CONSTRAINT2=0x40;             //~va96I~
   public static final int RARON_ERR_MULTIPLE=0x80;                //~va96I~
                                                                   //~va96I~
	private RoundStat RS;//~va60I~                                 //~va96R~
    private int[] itsTile=new int[CTR_TILETYPE];                   //~1111I~
    private int[] itsHand;                                         //~1111I~
    private boolean[] btsOrphan;                                   //~1111I~
    private int ctrOrphan;                                         //~1111I~
    private TileData[][] tdssHand;                                 //~1111I~
    private int[][] dupCtrAll=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR];//hand and earth //4*9//~1116I~
    public int[][] dupCtr=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR];	//4*9//~9C12I~//~va11R~//~1116I~
    public int hanExceptDora;	//output of getvalueExceptDora     //~1219I~
    private boolean swYakuFixLast,swYakuFixMultiWaitOK,swChkFixErr;//~va91I~
//  private boolean swYakuFixMultiwaitOKTake,swYakuFixMultiwaitOKTakeAllInHand;//~va91I~//~va98R~
    private boolean swYakuFixMultiwaitOKTake;                      //~va98I~
    private int errMultiWait;
    private boolean swCheckMultiWait;                              //~vaa8I~
    public  int amtRonValue;	//output of getRonValue to caller  //~vaajR~
    private int eswnKanAdd=-1;   //chankan                         //~vaaTI~
    private boolean swChankan;   //add RYAKU_KAN_ADD               //~vaaUI~
//*************************                                        //~v@@@I~
	public RARon()                                               //~v@@@R~//~va60R~//~1111R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("RARon.Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~va60R~//~1111R~
        AG.aRARon=this;                                          //~v@@@I~//~va60R~//~1111R~
        init();                                                    //~va60I~
    }                                                              //~0914I~//~v@@@R~
    //*********************************************************    //~va60I~
    private void  init()                                           //~va60I~
    {                                                              //~va60I~
    	RS=AG.aRoundStat;                                          //~1111I~
		swYakuFixLast= RuleSettingYaku.isYakuFixLast();             //~va91I~
        swYakuFixMultiWaitOK=RuleSettingYaku.isYakuFixMultiwaitOK();//~va91I~
        int fixTake=RuleSettingYaku.getYakuFixMultiwaitTake();      //~va91I~
        swYakuFixMultiwaitOKTake=fixTake==YAKUFIX_TAKE_ALL;        //~va91I~
//      swYakuFixMultiwaitOKTakeAllInHand=fixTake==YAKUFIX_TAKE_ALLINHAND;//~va91R~//~va98R~
        swCheckMultiWait= RuleSettingOperation.isCheckMultiWait();  //~vaa8I~
        if (Dump.Y) Dump.println("RARon.init swYakuFixMultiWaitOK="+swYakuFixMultiWaitOK+",takeOK="+fixTake+",swCheckMultiWait="+swCheckMultiWait);//~va91R~//~vaa8R~
    }                                                              //~va60I~
    //*********************************************************    //~1117I~
    //*from RADSmart at to discard for Robot                                //~1120R~//~1417R~
    //*under shanten=-1                                            //~1120I~
    //*********************************************************    //~1118I~
//  public  boolean callRonTaken(int PplayerDiscard,int PeswnDiscard,int[] PitsHand,TileData PtdTaken)//~1117R~//~1118R~//~1120R~//~va8iR~
    public  boolean callRonTaken(int PplayerDiscard,int PeswnDiscard,int[] PitsHand,int PctrHand,TileData PtdTaken)//~va8iI~
    {                                                              //~1117I~
        if (Dump.Y) Dump.println("RARon.callRonTaken player="+PplayerDiscard+",eswnDiscard="+PeswnDiscard+",tdTaken="+PtdTaken.toString());//~1117I~//~1120R~//~1405R~
//      RonResult r=getRonValue(true/*PswTake*/,PplayerDiscard,PitsHand,PtdTaken);//~1117R~//~1118R~//~1120R~//~va8iR~
//      boolean rc=isRonable(r);                                           //~1117I~//~va8iR~
        boolean rc=isRonableMultiWait(true/*swTake*/,PplayerDiscard,PeswnDiscard,PitsHand,PctrHand,PtdTaken);//~va8iR~
        if (rc)                                                    //~1117I~
        	issueRon(GCM_TAKE,PplayerDiscard/*player taken=now discard*/,PeswnDiscard);                                            //~1117I~//~1130R~//~1131R~
        return rc;                                                 //~1117I~
    }                                                              //~1117I~
    //*********************************************************    //~vaa4I~
    //*from RADSmart at to discard for Robot at 1st take 13/14 broken//~vaa4I~
    //*********************************************************    //~vaa4I~
    public  boolean callRonTakenNoPair(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,TileData PtdTaken)//~vaa4I~
    {                                                              //~vaa4I~
        if (Dump.Y) Dump.println("RARon.callRonTakenNoPair player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdTaken.toString());//~vaa4I~
        issueRon(GCM_TAKE,Pplayer/*player taken=now discard*/,Peswn);//~vaa4I~
        return true;                                            //~vaa4I~
    }                                                              //~vaa4I~
    //*********************************************************    //~va8jI~
    //*from RACall at human take in playAlone notify mode          //~va8jI~//~vaadR~
    //*under shanten=-1                                            //~va8jI~
    //*********************************************************    //~va8jI~
    public  boolean callRonTakenPlayAloneNotify(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,TileData PtdTaken)//~va8jI~
    {                                                              //~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonTakenPlayAloneNotify player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdTaken.toString());//~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonTakenPlayAloneNotify ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~va8jI~
        boolean rc=isRonableMultiWait(true/*swTake*/,Pplayer,Peswn,PitsHand,PctrHand,PtdTaken);//~va8jI~
        if (errMultiWait!=0)                                       //~va96I~
		    issueRonableMultiWaitErrMsg();                         //~va96I~
        if (Dump.Y) Dump.println("RARon.callRonTakenPlayAloneNotify@@@@ rc="+rc+",player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdTaken.toString());//~va8jI~
        return rc;                                                 //~va8jI~
    }                                                              //~va8jI~
    //*********************************************************    //~vaa2I~
    //*from RACall at human take in playMatch notify mode          //~vaa2I~
    //*under shanten=-1                                            //~vaa2I~
    //*********************************************************    //~vaa2I~
    public  boolean callRonTakenPlayMatchNotify(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,TileData PtdTaken)//~vaa2I~
    {                                                              //~vaa2I~
        if (Dump.Y) Dump.println("RARon.callRonTakenPlayMatchNotify player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdTaken.toString());//~vaa2I~
        if (Dump.Y) Dump.println("RARon.callRonTakenPlaymatchNotify ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaa2I~
        boolean rc=isRonableMultiWait(true/*swTake*/,Pplayer,Peswn,PitsHand,PctrHand,PtdTaken);//~vaa2I~
        if (errMultiWait!=0)                                       //~vaa2I~
		    issueRonableMultiWaitErrMsg();                         //~vaa2I~
        if (Dump.Y) Dump.println("RARon.callRonTakenPlayMatchNotify@@@@ rc="+rc+",player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdTaken.toString());//~vaa2I~
        return rc;                                                 //~vaa2I~
    }                                                              //~vaa2I~
    //*********************************************************    //~1118I~
    //*from RACall.nextPlayerPonKan for Robot                               //~1331R~//~1417R~
    //*chk shanten=-1 and issue ron                                //~1118I~//~1120R~
    //*********************************************************    //~1118I~
    public  boolean callRonRiver(int PeswnOther,TileData PtdDiscarded)//~1118I~//~1120R~//~1130R~
    {                                                              //~1118I~
    	boolean rc=false;                                          //~1118I~
        int[] itsH=RS.getItsHandEswn(PeswnOther);                       //~1118I~//~1130R~
        int   ctrH=RS.RSP[PeswnOther].ctrHand;                          //~1118I~//~1130R~
        int   player=RS.RSP[PeswnOther].player;                         //~1118I~//~1130R~
        int pos=RAUtils.getPosTile(PtdDiscarded);                    //~1120R~
        int shanten=RAUtils.getShantenAdd(itsH,ctrH,pos,1);          //~1120I~
        if (Dump.Y) Dump.println("RARon.callRonRiver player="+player+",eswn="+PeswnOther+",shanten="+shanten+",pos="+pos+",tdDiscarded="+PtdDiscarded.toString());//~1118I~//~1120I~//~1125R~//~1130R~
        if (shanten==-1)                                           //~1118I~
        {                                                          //~1118I~
//          if (!isFuriten(PeswnOther,itsH,ctrH))                    //~1213I~//~va8fR~
//          {                                                        //~1213I~//~va8fR~
//            RonResult r=getRonValue(false/*PswTake*/,player,itsH,PtdDiscarded);//~1118I~//~va8fR~
//            rc=isRonable(r);                                       //~1118I~//~va8fR~
//          }                                                        //~1213I~//~va8fR~
            rc=isRonableMultiWait(false/*swTake*/,player,PeswnOther,itsH,ctrH,PtdDiscarded);//~va8fI~
        }                                                          //~1118I~
        if (rc)                                                    //~1130I~
        {                                                          //~1130I~
        	int[] intp=new int[4];	//dummy                        //~1130I~
        	rc=AG.aUADelayed.chkSelectInfo2TouchRobot(player,intp);//~1130I~
        }                                                          //~1130I~
        if (rc)                                                    //~1118I~
        	issueRon(GCM_RON,player,PeswnOther);                                            //~1118I~//~1130R~//~1131R~
        return rc;                                                 //~1118I~
    }                                                              //~1118I~
    //*********************************************************    //~vaaUI~
    public  boolean callRonRiverNotifyChankan(int PplayerRon,int PeswnRon,int[] PitsH,int PctrH,TileData PtdKan)//~vaaUR~
    {                                                              //~vaaUI~
    	boolean rc=false;                                          //~vaaUI~
        if (Dump.Y) Dump.println("RARon.callRonRiverNotifyChankan PplayerRon="+PplayerRon+",PeswnRon="+PeswnRon+",tdKan="+PtdKan.toString());//~vaaUR~
        int pos=RAUtils.getPosTile(PtdKan);                  //~vaaUI~
        int shanten=RAUtils.getShantenAdd(PitsH,PctrH,pos,1);        //~vaaUI~
        if (shanten!=-1)                                           //~vaaUI~
        {                                                          //~vaaUI~
	        if (Dump.Y) Dump.println("RARon.callRonRiverNotifyChankan return false by shanten!=-1");//~vaaUI~
            return false;                                          //~vaaUI~
        }                                                          //~vaaUI~
    	if (AG.swPlayAloneNotify)                                  //~vaaUI~
        {                                                          //~vaaUI~
//        	PitsH[pos]++;                                          //~vaaUR~
            swChankan=true;     //add Yaku KAN_ADD                 //~vaaUI~
			rc=callRonRiverPlayAloneNotify(PplayerRon,PeswnRon,PitsH,PctrH+1,PtdKan);//~vaaUR~
            swChankan=false;    //add Yaku KAN_ADD                 //~vaaUI~
//      	PitsH[pos]--;                                          //~vaaUR~
            if (rc)                                                //~vaaUI~
            {                                                      //~vaaUI~
                AG.aGC.setPlayAloneNotifyChankan(true);            //~vaaUI~
        		AG.aUARon.selectInfoPlayAloneNotify();	//highlight Ron btn and show Cancel btn//~vaaUR~
            }                                                      //~vaaUI~
        }                                                          //~vaaUI~
        else                                                       //~vaaUI~
        if (AG.swPlayMatchNotify && PplayerRon==PLAYER_YOU)        //~vaaUR~
        {                                                          //~vaaUI~
        	PitsH[pos]++;                                          //~vaaUI~
            swChankan=true;     //add Yaku KAN_ADD                 //~vaaUI~
    		rc=callRonRiverPlayMatchNotify(PplayerRon,PeswnRon,PitsH,PctrH+1,PtdKan);//~vaaUR~
            swChankan=false;    //add Yaku KAN_ADD                 //~vaaUI~
        	PitsH[pos]--;                                          //~vaaUI~
            if (rc)                                                //~vaaUI~
        		AG.aUARon.selectInfoPlayMatchNotify(PtdKan);	//highlight Ron btn and show Cancel btn//~vaaUR~
        }                                                          //~vaaUI~
        if (Dump.Y) Dump.println("RARon.callRonRiverNotifyChankan rc="+rc);//~vaaUI~
        return rc;                                                 //~vaaUI~
    }                                                              //~vaaUI~
    //*********************************************************    //~va8jI~
    //*from RACall at human Ron in playalone notify mode           //~va8jI~
    //*under shanten=-1                                            //~va8jI~
    //*********************************************************    //~va8jI~
    public  boolean callRonRiverPlayAloneNotify(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,TileData PtdDiscarded)//~va8jI~
    {                                                              //~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayAloneNotify player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdDiscarded.toString());//~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayAloneNotify ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~va8jI~
        boolean rc=isRonableMultiWait(false/*swTake*/,Pplayer,Peswn,PitsHand,PctrHand,PtdDiscarded);//~va8jI~
        if (errMultiWait!=0)                                       //~va96I~
		    issueRonableMultiWaitErrMsg();                         //~va96I~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayAloneNotify@@@@ rc="+rc+",player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdDiscarded.toString());//~va8jI~
        return rc;                                                 //~va8jI~
    }                                                              //~va8jI~
    //*********************************************************    //~vaaUI~
    public  boolean callRonRiverPlayMatchNotify(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,TileData PtdDiscarded)//~vaaUI~
    {                                                              //~vaaUI~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayMatchNotify player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdDiscarded.toString());//~vaaUI~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayMatchNotify ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaaUI~
        boolean rc=isRonableMultiWait(false/*swTake*/,Pplayer,Peswn,PitsHand,PctrHand,PtdDiscarded);//~vaaUI~
        if (errMultiWait!=0)                                       //~vaaUI~
		    issueRonableMultiWaitErrMsg();                         //~vaaUI~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayMatchNotify@@@@ rc="+rc+",player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdDiscarded.toString());//~vaaUI~
        return rc;                                                 //~vaaUI~
    }                                                              //~vaaUI~
    //*********************************************************    //~1116I~
    private RonResult getRonValue(boolean PswTake, int Pplayer, int[] PitsHand, TileData PtdTaken)//~1116R~//~1117R~
    {                                                              //~1116I~
        int han=0;
        if (Dump.Y) Dump.println("RARon.getRonValue player="+Pplayer+",swTake="+PswTake+",tdTaken="+PtdTaken.toString()+",itsHand="+Utils.toString(PitsHand,9));//~1116I~//~1117R~
        RonResult r=AG.aUARonValue.getValue(PswTake,Pplayer,PitsHand,PtdTaken);//~1116R~//~1117R~
        if (Dump.Y) Dump.println("RARon.getRonValue ronResult="+r.toString());//~1117R~
        return r;                                                  //~1117R~
    }                                                              //~1116I~
    //*********************************************************    //~1117I~
    //*from RADEval.getRonValue                                    //~vab5R~
    //*********************************************************    //~1117I~
//  public int getRonValue(int Pplayer,int[] PitsHand,int Ppos)    //~1117I~//~vab5R~
    public int getRonValueEvaluate(int Pplayer,int[] PitsHand,int Ppos)//~vab5R~
    {                                                              //~1117I~
        if (Dump.Y) Dump.println("RARon.getRonValueEvaluate player="+Pplayer+",pos="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~1117I~//~vab5R~
//      RonResult r=AG.aUARonValue.getValue(false/*swTake*/,Pplayer,PitsHand,null);//~1117I~//~1206R~
//      RonResult r=AG.aUARonValue.getValue(true/*swTake*/,Pplayer,PitsHand,null);//~1206I~//~1212R~
        TileData tdTaken=new TileData(Ppos/CTR_NUMBER_TILE,Ppos%CTR_NUMBER_TILE);   //to give ronType and ronNumber//~1212I~
        RonResult r=AG.aUARonValue.getValue(true/*swTake*/,Pplayer,PitsHand,tdTaken);//~1212I~
        int han;                                                   //~1117I~
        boolean swConstraintOK=false;                              //~vab5I~
        if (r.isYakuman())                                         //~1117I~
        	han= Rank.MIN_RANK_YAKUMAN;     //13                            //~1117I~
        else                                                       //~1117I~
        {                                                          //~vab5R~
//      	han=r.han;                                             //~1117I~//~vab5R~
        	han=r.getHanExceptDora();                              //~vab5R~
            if (RS.swFix2)   //2han constraint from dupctr>=4 or 5 //~vab5I~
                swConstraintOK=han>=2;                             //~vab5I~
            else                                                   //~vab5I~
                swConstraintOK=han>=1;                             //~vab5I~
            if (swConstraintOK)                                    //~vab5I~
            	han=r.han;	//including Dora                       //~vab5I~
        }                                                          //~vab5R~
    	amtRonValue=r.amt;	//output of getRonValue to caller      //~vaajR~
        if (han==0)                                                //+vab5I~
	    	amtRonValue=0;                                         //+vab5I~
        if (Dump.Y) Dump.println("RARon.getRonValueEvaluate rc="+han+",swConstraintOK="+swConstraintOK+",ronResult="+r.toString());//~1117I~//~vab5R~
        return han;                                                //~1117I~
    }                                                              //~1117I~
    //************************************************************ //~1219R~
    //*from RAReach to evaluate winList(exclude han taken)         //~1219R~
    //*accidental Yaku(OnShot,Last,Rinshan because evaluation for Reach)//~va8cI~
    //*and set hanExceptDora(except self-draw:for Reach chk only) for constraint chk                    //~1219I~//~va8jR~
    //************************************************************ //~1219I~
//  public int getRonValueExceptDora(int Pplayer,int[] PitsHand,int Ppos)//~1120I~//~va91R~
    public int getRonValueExceptDoraReach(int Pplayer,int[] PitsHand,int Ppos)//~va91I~
    {                                                              //~1120I~
        if (Dump.Y) Dump.println("RARon.getRonValueExceptDora player="+Pplayer+",pos="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~1120I~
        TileData tdRon=new TileData(Ppos/CTR_NUMBER_TILE,Ppos%CTR_NUMBER_TILE);   //dummy//~1121I~
//      RonResult r=AG.aUARonValue.getValue(false/*swTake*/,Pplayer,PitsHand,tdRon);//~1120I~//~1121R~//~1219R~
//      RonResult r=AG.aUARonValue.getValue(true/*swTake*/,Pplayer,PitsHand,tdRon); //swTake:true for do not add for tdRon//~1219I~//~vaapR~
        RonResult r=AG.aUARonValue.getValueReach(true/*swTake*/,Pplayer,PitsHand,tdRon); //swTake:true for do not add for tdRon//~vaapI~
        int han;                                                   //~1219R~
        if (r.isYakuman())                                         //~1120I~
        {                                                          //~1219I~
        	han= Rank.MIN_RANK_YAKUMAN;     //13                   //~1120I~
        	hanExceptDora=han;                                     //~1219I~
        }                                                          //~1219I~
        else                                                       //~1120I~
        {                                                          //~1219I~
        	han=r.getHanExceptDora();                              //~1120I~
//      	hanExceptDora=r.getHanExceptDora();                    //~1219I~//~va8cR~
        	hanExceptDora=han;                                     //~va8cI~
        	if (hanExceptDora>0)                                   //~1219I~
	        	hanExceptDora--;	//drop of take                 //~1219I~
        }                                                          //~1219I~
    	amtRonValue=r.amt;	//output of getRonValue to caller      //~vaajI~
        if (Dump.Y) Dump.println("RARon.getRonValueExceptDora rc="+han+",hanExceptDora="+hanExceptDora+",ronResult="+r.toString());//~1120I~//~1219R~
        return han;                                                //~1120I~
    }                                                              //~1120I~
    //*********************************************************    //~1117I~
    //*constraint chk                                              //~vaa4R~
    //*********************************************************    //~1213I~
//  public boolean isRonable(RonResult PronResult)                 //~1117I~//~va91R~
    private boolean isRonable(RonResult PronResult)                //~va91I~
    {                                                              //~1117I~
    	boolean rc=true;                                           //~1117I~
        if (!PronResult.isYakuman())                               //~1117I~
        {                                                          //~1117I~
//      	int han=PronResult.getHanExceptDora();                               //~1117I~//~va8cR~
        	int han=getHanExceptDoraConstraint(PronResult,RS.swFix2);//~va8cR~//~va96R~
//          int ctrDup=RS.gameCtrDup;                              //~1117I~//~1119R~
//          if (ctrDup>=RS.constraintFix2)   //2han constraint from dupctr>=4 or 5//~1118R~//~1119R~
            if (RS.swFix2)   //2han constraint from dupctr>=4 or 5//~1119I~
                rc=han>=2;                                         //~1117I~
            else                                                   //~1117I~
            if (!swChankan)	//not effective for 2 han constraint   //~vaaUI~
                rc=han>=1;                                         //~1117I~
	        if (Dump.Y) Dump.println("RARon.isRonable not Yakuman han="+han+",swfix2="+RS.swFix2);//~1414I~//~va91R~
        }                                                          //~1117I~
        if (Dump.Y) Dump.println("RARon.isRonable@@@@ swChankan="+swChankan+",rc="+rc);         //~1117I~//~1130R~//~1220R~//~vaaUR~
        return rc;                                                 //~1117I~
    }                                                              //~1117I~
    //*********************************************************    //~va91I~
    private boolean isRonableFixErr(RonResult PronResult)          //~va91I~
    {                                                              //~va91I~
    	swChkFixErr=true;     //parm to getHanExceptDoraConstraint //~va91I~
	    boolean rc=isRonable(PronResult);                          //~va91I~
    	swChkFixErr=false;                                         //~va91I~
        if (Dump.Y) Dump.println("RARon.isRonableFixErr@@@@ rc="+rc);//~va91I~
        return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //*********************************************************    //~1118I~
    //*for smart Robot                                             //~1417I~
    //*from RACall.calledKan ankan ron for 13orphan and chankan ron//~1120R~
    //*under shanten=-1                                            //~1118I~
    //*********************************************************    //~1118I~
    public boolean callRonChankan(int Ptype,int PeswnOther,TileData PtdKan,int PplayerCalled/*player called Kan*/,int PeswnCalled)//~1118R~//~1120R~//~1130R~//~1201R~
    {                                                              //~1118I~
    	boolean rc=false;                                          //~1118R~
        int shanten;                                               //~1118I~
    	//************************                                 //~1118I~
     	if (Dump.Y) Dump.println("RARon.callRonChankan for other called Kan type="+Ptype+",eswnOther="+PeswnOther+",playerCalled="+PplayerCalled+",eswnCalled="+PeswnCalled+",tdKan="+TileData.toString(PtdKan));//~1118I~//~1120R~//~1130R~//~1201R~//~vaaTR~
        int[] itsH=RS.getItsHandEswn(PeswnOther);                   //~1118I~//~1130R~
        int pos=RAUtils.getPosTile(PtdKan);                              //~1118I~//~1201R~
        int player=RS.RSP[PeswnOther].player;                 //~1130I~
        if (Ptype==KAN_TAKEN)	//ankan chk 13orphan ronnable      //~1118I~
        {                                                          //~1118I~
        	if (itsH[pos]==0)                                      //~1118I~
            {                                                      //~1118I~
	            itsH[pos]++;                                       //~1118I~
        		shanten=AG.aShanten.getShanten_13Orphan(itsH);     //~1118R~
	            itsH[pos]--;                                       //~1118I~
            	if (shanten==-1)                                   //~1118R~
                {                                                  //~1118I~
		            eswnKanAdd=PeswnCalled;                        //~vaaUI~
        			issueRon(GCM_TAKE,player,PeswnOther);                                    //~1118I~//~1130R~//~1131R~
		            eswnKanAdd=-1;                                 //~vaaUI~
            		rc=true;                                       //~1118R~
                }                                                  //~1118I~
            }                                                      //~1118I~
        }                                                          //~1118I~
        else    //KAN_ADD                                          //~1118I~
        {                                                          //~1118I~//~1118I~
//                TileData tdChankan=null;                           //~1118I~//~1201R~
//                for (TileData td:Ptds)                                      //~1118I~//~1201R~
//                {                                                  //~1118I~//~1201R~
//                    if (td.isKanAddedTile())                       //~1118I~//~1201R~
//                    {                                              //~1118I~//~1201R~
//                        tdChankan=td;                              //~1118I~//~1201R~
//                        break;                                     //~1118I~//~1201R~
//                    }                                              //~1118I~//~1201R~
//                }                                                  //~1118I~//~1201R~
//                rc=callRonRiver(PeswnOther,tdChankan);//~1118I~     //~1120R~//~1130R~//~1201R~
            eswnKanAdd=PeswnCalled;                                //~vaaTI~
        	rc=callRonRiver(PeswnOther,PtdKan);                    //~1201I~
            eswnKanAdd=-1;                                         //~vaaTI~
        }                                                          //~1118I~//~1201R~
     	if (Dump.Y) Dump.println("RARon.callRonChankan for other called Kan type="+Ptype+",rc="+rc);//~1118R~//~1120R~
        return rc;                                                 //~1118I~
    }                                                              //~1118I~
    //*********************************************************    //~vaaUI~
    //*under shanten=0                                             //~vaaUI~
    //*********************************************************    //~vaaUI~
    public boolean callRonChankanNotify(int Ptype,int PeswnOther/*winner*/,TileData PtdKan,int PplayerCalled/*player called Kan*/,int PeswnCalled)//~vaaUR~
    {                                                              //~vaaUI~
    	boolean rc=false;                                          //~vaaUI~
        int shanten;                                               //~vaaUI~
    	//************************                                 //~vaaUI~
     	if (Dump.Y) Dump.println("RARon.callRonChankanNotify for other called Kan type="+Ptype+",eswnOther="+PeswnOther+",playerCalled="+PplayerCalled+",eswnCalled="+PeswnCalled+",tdKan="+TileData.toString(PtdKan));//~vaaUI~
        if (Dump.Y) Dump.println("RARon.callRonChankanNotify swPlayAloneNotify="+AG.swPlayAloneNotify+",swPlaymatchNotify="+AG.swPlayMatchNotify);//~vaaUI~
        int[] itsH=RS.getItsHandEswn(PeswnOther);                  //~vaaUI~
        int   ctrH=RS.RSP[PeswnOther].ctrHand;                     //~vaaUI~
        int pos=RAUtils.getPosTile(PtdKan);                        //~vaaUI~
        int player=RS.RSP[PeswnOther].player;                      //~vaaUI~
        if (Ptype==KAN_TAKEN)	//ankan chk 13orphan ronnable      //~vaaUI~
        {                                                          //~vaaUI~
        	if (itsH[pos]==0)                                      //~vaaUI~
            {                                                      //~vaaUI~
	            itsH[pos]++;                                       //~vaaUI~
        		shanten=AG.aShanten.getShanten_13Orphan(itsH);     //~vaaUI~
	            itsH[pos]--;                                       //~vaaUI~
            	if (shanten==-1)                                   //~vaaUI~
                {                                                  //~vaaUI~
		            eswnKanAdd=PeswnCalled;                        //~vaaUI~
		            itsH[pos]++;                                   //~vaaUI~
        			rc=issueRonTakenNotifyChankan(GCM_TAKE,player,PeswnOther,itsH,ctrH,PtdKan);//~vaaUR~
		            itsH[pos]--;                                   //~vaaUI~
		            eswnKanAdd=-1;                                 //~vaaUI~
                }                                                  //~vaaUI~
            }                                                      //~vaaUI~
        }                                                          //~vaaUI~
        else    //KAN_ADD                                          //~vaaUI~
        {                                                          //~vaaUI~
            eswnKanAdd=PeswnCalled;                                //~vaaUI~
        	rc=callRonRiverNotifyChankan(player,PeswnOther,itsH,ctrH,PtdKan);//~vaaUR~
            eswnKanAdd=-1;                                         //~vaaUI~
        }                                                          //~vaaUI~
     	if (Dump.Y) Dump.println("RARon.callRonChankanNotify for other called Kan type="+Ptype+",rc="+rc);//~vaaUI~
        return rc;                                                 //~vaaUI~
    }                                                              //~vaaUI~
    //*********************************************************    //~1117I~
    private void issueRon(int Paction,int PplayerCaller,int PeswnCaller)                                        //~1117I~//~1130R~//~1131R~
    {                                                              //~1117I~
        if (Dump.Y) Dump.println("RARon.issueRon Pplayercaller="+PplayerCaller+",Peswncaller="+PeswnCaller+",action="+Paction);                //~1117I~//~1130R~//~1131R~
        if ((TestOption.option3 & TestOption.TO3_IT_RARON)!=0)                //~va91I~
        {                                                          //~va91I~
	        if (Dump.Y) Dump.println("RARon.issueRon@@@@ return by TestOption");//~va91I~
            return;                                                //~va91I~
        }                                                          //~va91I~
        Robot r=RS.RSP[PeswnCaller].robot;                         //~1130I~//~vaaUR~
//      r.sendToServer(false/*waiterBlock*/,GCM_RON,PeswnCaller,"");//~1130I~//~vaaSR~
//      int eswnDiscarded=Accounts.playerToEswn(AG.aPlayers.playerLastDiscarded);//~vaaSI~//~vaaTR~
        int eswnDiscarded;                                         //~vaaTI~
        if (eswnKanAdd!=-1)        //chankan                       //~vaaTI~
        	eswnDiscarded=eswnKanAdd;                                            //~vaaTI~
        else                                                       //~vaaTI~
			eswnDiscarded=Accounts.playerToEswn(AG.aPlayers.playerLastDiscarded);//~vaaTI~
		String looserEswn=Integer.toString(eswnDiscarded);         //~vaaSI~
        if (Dump.Y) Dump.println("RARon.issueRon msgData looserEswn="+looserEswn);//~vaaSI~
        r.sendToServer(false/*waiterBlock*/,GCM_RON,PeswnCaller,looserEswn);//~vaaSI~
    }                                                              //~1117I~
    //*********************************************************    //~vaaUI~
    //*chankan 13orphan                                            //~vaaUI~
    //*********************************************************    //~vaaUI~
    private boolean issueRonTakenNotifyChankan(int Paction,int PplayerRon,int PeswnRon,int[] PitsH,int PctrH,TileData PtdKan)//~vaaUR~
    {                                                              //~vaaUI~
        if (Dump.Y) Dump.println("RARon.issueRonTakenNotifyChanken PplayerRon="+PplayerRon+",PeswnRon="+PeswnRon+",action="+Paction+",tdKan="+PtdKan.toString());//+vaaUR~        boolean rc=false;//~vaaUR~
    	boolean rc=false;
        if (AG.swPlayAloneNotify)                                  //~vaaUI~
        {                                                          //~vaaUI~
			rc=callRonTakenPlayAloneNotify(PplayerRon,PeswnRon,PitsH,PctrH,PtdKan);//~vaaUR~
            if (rc)                                                //~vaaUI~
            {                                                      //~vaaUI~
                AG.aGC.setPlayAloneNotifyChankan(true);            //~vaaUI~
	        	AG.aUARon.selectInfoPlayAloneNotifyTake(PtdKan,false/*swRonNoPair*/);	//chk furiten,han constraint then highlight Ron btn and show Cancel btn//~vaaUI~
            }                                                      //~vaaUI~
        }                                                          //~vaaUI~
        else                                                       //~vaaUI~
        if (AG.swPlayMatchNotify && PplayerRon==PLAYER_YOU)        //~vaaUR~
        {                                                          //~vaaUI~
		    rc=callRonTakenPlayMatchNotify(PplayerRon,PeswnRon,PitsH,PctrH,PtdKan);//~vaaUR~
            if (rc)                                                //~vaaUI~
	          	AG.aUARon.selectInfoPlayMatchNotifyTake(PtdKan,false/*swRonNoPair*/);	//chk furiten,han constraint then highlight Ron btn and show Cancel btn//~vaaUI~
        }                                                          //~vaaUI~
        if (Dump.Y) Dump.println("RARon.issueRonTakenNotifyChanken rc="+rc);//~vaaUR~
        return rc;
    }                                                              //~vaaUI~
    //*********************************************************    //~1213I~
    private boolean isFuriten(int PeswnOther,int[] PitsHand,int PctrHand)//~1213I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RARon.isFuriten eswnOther="+PeswnOther+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1213I~//~1306R~//~va8jR~
        boolean rc=AG.aRADSmart.chkFuriten(PeswnOther,PitsHand,PctrHand);//~1213I~
        if (Dump.Y) Dump.println("RARon.isFuriten rc="+rc);        //~1213I~//~1306R~
        return rc;                                              //~1213I~//~1305R~
    }                                                              //~1213I~
    //*********************************************************    //~va8cI~
    //*return han, optionally subtracted by han by kata-agari      //~vaa4I~
    //*********************************************************    //~vaa4I~
    private int getHanExceptDoraConstraint(RonResult PronResult,boolean PswFix2)//~va8cR~
    {                                                              //~va8cI~
        if (Dump.Y) Dump.println("RARon.getHanExceptDoraConstraint RonResult="+PronResult.toString()+",swFix2="+PswFix2+",swYakuFixLast="+swYakuFixLast);//~va8cR~//~va91R~
//      boolean swIgnoreAccidental=PswFix2 && !RS.swYakuFixLast;	//ignore accidental YAKU(one shot,haitei,rinshan)//~va8cI~//~va91R~
        boolean swIgnoreAccidental=PswFix2 && !swYakuFixLast;	//ignore accidental YAKU(one shot,haitei,rinshan)//~va91I~
//      int han=PronResult.getHanExceptDoraConstraint(swIgnoreAccidental);//~va8cR~//~va8iR~
		int han;                                                   //~va91I~
      	if (swChkFixErr)                                           //~va91I~
			han=PronResult.getHanExceptDoraConstraintChkFix(swIgnoreAccidental,PswFix2);//~va91I~
        else                                                       //~va91I~
        	han=PronResult.getHanExceptDoraConstraint(swIgnoreAccidental,PswFix2);//~va8iI~//~va91R~
        if (Dump.Y) Dump.println("RARon.getHanExceptDoraConstraint@@@ rc=han="+han+",swChkFixErr="+swChkFixErr);//~va91R~
        return han;
    }                                                              //~va8cI~
    //*********************************************************    //~va8fI~
    //*for Robot(callRonTaken,callRonRiver)                        //~va8fR~
    //*for Human in playAloneMode(callRonRiverPlayAloneNotify,callRonTakenPlayAlone)           //~va8fR~//~va8uR~
    //*chk furiten and ronable(chk kataAgari)                      //~va8fI~
    //*********************************************************    //~va8fI~
    private boolean isRonableMultiWait(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,TileData PtdDiscarded)//~va8fI~
    {                                                              //~va8fI~
  if (false)                                                       //~va91I~
  {                                                                //~va91I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ swTake="+PswTake+",player="+Pplayer+",eswn="+Peswn+",tdDiscarded="+PtdDiscarded+toString()+",itsHand="+Utils.toString(PitsH,9));//~va8fI~//~va8iR~
    	boolean rc;                                                //~va8fR~
        RonResult r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded);//~va8fI~//~va8iR~
        rc=isRonable(r);                                           //~va8fI~
        if (!rc)    //constraint NG                                //~va8fI~
        {                                                          //~va8fI~
        	if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ return false by constraint NG ronResult="+r.toString()+",tdDiscarded="+PtdDiscarded.toString());//~va8fR~
            return false;                                          //~va8fI~
        }                                                          //~va8fI~
      if (RS.RSP[Peswn].swAllInHand && PswTake)                               //~va8jI~
      {                                                            //~va8jI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ rc=true by AllinHand Taken");//~va8jI~
        rc=true;                                                   //~va8jI~
      }                                                            //~va8jI~
      else                                                         //~va8jI~
      {                                                            //~va8jI~
      	if (!RS.RSP[Peswn].swAllInHand)                            //~va8uI~
        	if (!AG.aUARonValue.swYakuFixLast)	//take and !fixLast//~va8uI~
            {                                                      //~va8uI~
            	if (chkYakuFixedFirst(r,Pplayer,Peswn,PitsH,PctrH,PtdDiscarded))//~va8uR~
                {                                                  //~va8uI~
                    if (PswTake)                                    //~va8uI~
                    {                                              //~va8uI~
    		        	if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ sakiduke OK for Take");//~va8uI~
        	    		return true;                               //~va8uI~
                    }                                              //~va8uI~
                }                                                  //~va8uI~
                else                                               //~va8uI~
            	{                                                  //~va8uI~
    	        	if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ sakiduke err");//~va8uI~
            		return false;                                  //~va8uI~
            	}                                                  //~va8uI~
            }                                                      //~va8uI~
            else	//FixLast                                      //~va8uI~
            {                                                      //~va8uI~
            	if (PswTake)                                       //~va8uI~
                {                                                  //~va8uI~
    		        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ atoduke OK by Take");//~va8uI~
        	    	return true;                                   //~va8uI~
                }                                                  //~va8uI~
            }                                                      //~va8uI~
//  	if (!isChkYakuMultiWait(PswTake))                          //~va8fR~
    	if (!isChkYakuMultiWait_deprecated(PswTake,r))                         //~va8fI~
        {                                                          //~va8fI~
          if (!PswTake)                                            //~va8uI~
			if (isFuriten(Peswn,PitsH,PctrH))                      //~va8fR~
            {                                                      //~va8fI~
	        	if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ allow Multiwait return false by Furiten");//~va8fR~
              	return false;                                      //~va8fR~
            }                                                      //~va8fI~
	        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ allow multiwait return true by NOT Furiten swTake=+PswTake");//~va8fI~//~va8uR~
            return true;                                           //~va8fI~
        }                                                          //~va8fI~
//      if (PswTake && !AG.aUARonValue.swYakuFixLast)	//take and !fixLast//~va8uR~
//          if (chkYakuFixedFirst(r,Pplayer,Peswn,PitsH,PctrH,PtdDiscarded))//~va8uR~
//          {                                                      //~va8uR~
//  	        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ allow multiwait return true by swTake and fixed1");//~va8uR~
//          	return true;                                       //~va8uR~
//          }                                                      //~va8uR~
        int posTaken=RAUtils.getPosTile(PtdDiscarded);             //~va8iI~
        int ctrH=PctrH;                                             //~va8iI~
        if (PswTake)                                               //~va8iI~
        {                                                          //~va8iI~
        	PitsH[posTaken]--;	//for getWinList                   //~va8iI~
            ctrH--;                                                //~va8iI~
		}                                                          //~va8iI~
	    rc=!AG.aRADSmart.chkFuritenMultiWait(PswTake,Pplayer,Peswn,PitsH,ctrH,PtdDiscarded);	//callback isRoMultiWaitCB//~va8fR~//~va8iR~
        if (PswTake)                                               //~va8iI~
        {                                                          //~va8iI~
        	PitsH[posTaken]++;                                     //~va8iI~
		}                                                          //~va8iI~
      }                                                            //~va8jI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ rc="+rc+",swAllInHand="+RS.RSP[Peswn].swAllInHand+",swTake="+PswTake+",eswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~va8fR~//~va8jR~
        return rc;                                                 //~va8fI~
  }//if false                                                      //~va91I~
  else                                                             //~va91I~
  {                                                                //~va91I~
    //*****************************************************************//~va91I~
    //*New logic                                                   //~va91I~
    //*****************************************************************//~va91I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ swTake="+PswTake+",player="+Pplayer+",eswn="+Peswn+",tdDiscarded="+PtdDiscarded+toString()+",itsHand="+Utils.toString(PitsH,9));//~va8fI~//~va8iR~//~va91M~
        errMultiWait=0;                                            //~va96M~
    	boolean rc=true;                                                //~va8fR~//~va91R~
        boolean swAllInHand=RS.RSP[Peswn].swAllInHand;             //~va91I~
        RonResult r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded);//~va91I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ result="+r.toString());//~va91I~
        rc=isRonable(r);   //no chk kataagariErr                         //~va91I~
        if (!rc)    //constraint NG                                //~va91I~
        {                                                          //~va91I~
        	if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ return false by constraint NG ronResult="+r.toString()+",tdDiscarded="+PtdDiscarded.toString());//~va91I~
        	errMultiWait|=RARON_ERR_CONSTRAINT;                    //~va96I~
            return false;                                          //~va91I~
        }                                                          //~va91I~
//  	if (!isChkYakuMultiWait(PswTake,swAllInHand))	//no need to chk kataagari//~va91R~//~vaa8R~
    	if (!isChkYakuMultiWait(PswTake,swAllInHand,RS.RSP[Peswn].swRobot))	//no need to chk kataagari//~vaa8I~
        {                                                          //~va91I~
        	if (!PswTake && isFuriten(Peswn,PitsH,PctrH)) //chk winlist//~va91R~
            {                                                      //~va91R~
                if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ allow Multiwait return false by Furiten");//~va91R~
                errMultiWait|=RARON_ERR_FURITEN;	//  =0x01;     //~va96I~
                rc=false;                                          //~va91R~
            }                                                      //~va91R~
        }                                                          //~va91I~
        else  //chk kataagari                                      //~va91R~
        {                                                          //~va91I~
        	int posTaken=RAUtils.getPosTile(PtdDiscarded);             //~va8iI~//~va91I~
        	int ctrH=PctrH;                                             //~va8iI~//~va91I~
        	if (PswTake)                                               //~va8iI~//~va91I~
        	{                                                          //~va8iI~//~va91I~
        		PitsH[posTaken]--;	//for getWinList                   //~va8iI~//~va91I~
            	ctrH--;                                                //~va8iI~//~va91I~
			}                                                          //~va8iI~//~va91I~
	    	rc=!AG.aRADSmart.chkFuritenMultiWait(PswTake,Pplayer,Peswn,PitsH,ctrH,PtdDiscarded);	//callback isRoMultiWaitCB//~va8fR~//~va8iR~//~va91I~
        	if (PswTake)                                               //~va8iI~//~va91I~
        	{                                                          //~va8iI~//~va91I~
        		PitsH[posTaken]++;                                     //~va8iI~//~va91I~
			}                                                          //~va8iI~//~va91I~
      	}                                                            //~va8jI~//~va91I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ rc="+rc+",swTake="+PswTake+",swAllInHand="+swAllInHand+",eswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~va8fR~//~va8jR~//~va91R~
        return rc;                                                 //~va8fI~//~va91M~
  }//new logic                                                     //~va91I~
	}                                                              //~va8fI~//~va8iR~
    //*********************************************************    //~va8fI~
    //*for Robot from RADSmart.chkFuritenMultiWait(callback) after furitenchk      //~va8fI~//~va91R~
    //*chk MultiWait(1/2han constraint)                            //~va8fI~
    //*rc:true:ronable                                             //~va91I~
    //*********************************************************    //~va8fI~
    public boolean isRonableMultiWaitCB(int PposTile,boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,TileData PtdDiscarded)//~va8fR~
    {                                                              //~va8fI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCB posWinTile="+PposTile+",swTake="+PswTake+",eswn="+Peswn+",tdDiscarded="+PtdDiscarded.toString()+",itsHand="+Utils.toString(PitsH,9));//~va8fR~
//      int posDiscarded=RAUtils.getPosTile(PtdDiscarded);         //~va8fI~//~va91R~
//      if (PposTile==posDiscarded)                                    //~va8fI~//~va91R~
//      {                                                          //~va8fI~//~va91R~
//      	if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCB return true by pos is of PtdDiscarded");//~va8fI~//~va91R~
//      	return true;                                           //~va8fI~//~va91R~
//      }                                                          //~va8fI~//~va91R~
//      PitsH[PposTile]++;                                         //~va8fR~
        TileData tdRon=new TileData(PposTile/CTR_NUMBER_TILE,PposTile%CTR_NUMBER_TILE);   //to give ronType and ronNumber//~va8fI~
        RonResult r=getRonValue(false/*PswTake*/,Pplayer,PitsH,tdRon);//~va8fR~
//      PitsH[PposTile]--;                                         //~va8fR~
//      boolean rc=isRonable(r);                                   //~va8fI~//~va91R~
        boolean rc=isRonableFixErr(r);                             //~va91I~
        if (!rc)                                                   //~va96I~
        	errMultiWait|=RARON_ERR_FIX;                           //~va96I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCB rc="+rc+",swTake="+PswTake+",PposTile="+PposTile+",eswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~va8fR~
        return rc;                                                 //~va8fI~
	}                                                              //~va8fI~
    //*********************************************************    //~va96I~
    public void setRonableMultiWaitCBFuriten(int PposTile)         //~va96R~
    {                                                              //~va96I~
		errMultiWait|=RARON_ERR_FURITEN;                           //~va96I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCBFuriten posTile="+PposTile+",errMultiWait="+errMultiWait);//~va96I~
    }                                                              //~va96I~
    //*********************************************************    //~va96I~
    //*from RADSmart, Ok is not all of ctrWin and no furiten       //~va96I~
    //*********************************************************    //~va96I~
    public void setRonableMultiWaitCBErr(int Preason)              //~va96R~
    {                                                              //~va96I~
//  	errMultiWait|=RARON_ERR_MULTIPLE;                          //~va96R~
    	errMultiWait|=Preason;                                     //~va96I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCBErr reason="+Integer.toHexString(Preason)+",errMultiWait="+Integer.toHexString(errMultiWait));//~va96R~
    }                                                              //~va96I~
    //*********************************************************    //~va8fI~
    //*chk whether kataagari chk required                          //~vaa4I~
    //*********************************************************    //~vaa4I~
//  private boolean  isChkYakuMultiWait(boolean PswTake)           //~va8fR~
//  private boolean  isChkYakuMultiWait(boolean PswTake,RonResult PronResult)//~va8fI~//~va98R~
    private boolean  isChkYakuMultiWait_deprecated(boolean PswTake,RonResult PronResult)//~va98I~
    {                                                              //~va8fI~
    	boolean rc=true;	//kataagari NG                         //~va8fI~
//    	if (PswTake)                                               //~va8kI~//~va8mR~
//        	rc=false;	//allow multiwait                          //~va8kI~//~va8mR~
//      else                                                       //~va8kI~//~va8mR~
    	if (AG.aUARonValue.swYakuFixLast)	//allow yaku at last   //~va8fI~
        {                                                          //~va8fI~
        	if (AG.aUARonValue.swYakuFixMultiwaitOK)                  //~va8fI~
            	rc=false;	//allow multiwait                      //~va8fI~
        }                                                          //~va8fI~
//      else                                                       //~va8fI~//~va8kR~
//      {                                                          //~va8fI~//~va8kR~
//      	if (PronResult.isTakeAllInHand())                      //~va8fI~//~va8kR~
//          {                                                      //~va8fI~//~va8kR~
//  	        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait skip multiwait chk by menzen");//~va8fI~//~va8kR~
//          	rc=false;	//allow multiwait                      //~va8fI~//~va8kR~
//          }                                                      //~va8fI~//~va8kR~
//          else                                                   //~va8fI~//~va8kR~
//      	if (PswTake && AG.aUARonValue.swYakuFixMultiwaitDrawOK)   //~va8fI~//~va8kR~
//          	rc=false;	//allow multiwait                      //~va8fI~//~va8kR~
//      }                                                          //~va8fI~//~va8kR~
        else                                                       //~va8mI~
        {                                                          //~va8mI~
          if (false)        //rc=True, kataagari:NG for FixFirst/FixMiddle//~va91I~
          {                                                        //~va91I~
        	if (PronResult.isTakeAllInHand())                      //~va8mI~
            {                                                      //~va8mI~
    	        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait skip multiwait chk by menzen");//~va8mI~
            	rc=false;	//allow multiwait                      //~va8mI~
            }                                                      //~va8mI~
          }                                                        //~va91I~
        }                                                          //~va8mI~
        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait@@@@ rc="+rc+",swTake="+PswTake+",swYakuFixLast="+AG.aUARonValue.swYakuFixLast+",swYakuMultiwaitOK="+AG.aUARonValue.swYakuFixMultiwaitOK);//~va8fR~//~va8kR~
        return rc;
    }                                                              //~va8fI~
    //*********************************************************    //~va91I~
    //*rc=true:chk kataagari, false:no need to chk kataagari       //~va91I~
    //*********************************************************    //~va91I~
//  private boolean  isChkYakuMultiWait(boolean PswTake,boolean PswAllInHand)//~va91I~//~vaa8R~
    private boolean  isChkYakuMultiWait(boolean PswTake,boolean PswAllInHand,boolean PswRobot)//~vaa8I~
    {                                                              //~va91I~
        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait PswTake="+PswTake+",PswAllInHand="+PswAllInHand+",PswRobot="+PswRobot+"+swCheckMultiWait="+swCheckMultiWait);//~vaa8I~
    	if (!PswRobot && !swCheckMultiWait)	//human obey RuleSetting//~vaa8I~
        {                                                          //~vaa8I~
        	if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait return false for Human by RuleSetting");//~vaa8I~
        	return false;                                          //~vaa8I~
        }                                                          //~vaa8I~
    	boolean rc=true;	//kataagari NG                         //~va91I~
        if (PswTake)                                               //~va91I~
        {                                                          //~va91I~
            if (swYakuFixMultiwaitOKTake)                          //~va91I~
            	rc=false;                                          //~va91I~
            else                                                   //~va98I~
            if (PswAllInHand)                                      //~va91I~
//              if (swYakuFixMultiwaitOKTakeAllInHand)             //~va91I~//~va98R~
    	        	rc=false;                                      //~va91I~
        }                                                          //~va91I~
    	if (swYakuFixLast)	//allow yaku at last                   //~va91I~
        {                                                          //~va91I~
        	if (swYakuFixMultiWaitOK)                              //~va91R~
            	rc=false;	//allow multiwait                      //~va91I~
        }                                                          //~va91I~
        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait@@@@ rc="+rc+",swTake="+PswTake+",swAllInHand="+PswAllInHand+",swYakuFixLast="+swYakuFixLast+",swYakuMultiwaitOK="+swYakuFixMultiWaitOK+",swYakuFixMultiwaitOKTake="+swYakuFixMultiwaitOKTake);//~va91R~
        return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //*********************************************************************//~va8uI~
    //*chk Fixed First at take not all in hand                     //~va8uI~
    //*rc:true:sakiduke OK                                         //~va8uI~
    //*********************************************************************//~va8uI~
    private boolean chkYakuFixedFirst(RonResult Presult,int Pplayer,int Peswn,int[] PitsH,int ctrH,TileData PtdTaken)//~va8uR~
    {                                                              //~va8uI~
    	boolean rc=false;                                          //~va8uI~
        if (Dump.Y) Dump.println("RARon.chkYakuFixedFirst player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdTaken.toString()+",RonResult="+Presult.toString());//~va8uR~
        if (Presult.longRank.isContainsFixFirst())                 //~va8uI~
        	rc=true;                                               //~va8uI~
        else                                                       //~va8uI~
        if (RS.RSP[Peswn].isFixedFirst())     //1/2han already fixed//~va8uI~
        	rc=true;                                               //~va8uR~
        else                                                       //~va8uI~
        if (!RS.RSP[Peswn].swRobot)                                //~va8uR~
        	rc=true;     //notify mode, notify without FixedFirst check//~va8uI~
        if (Dump.Y) Dump.println("RARon.chkYakuFixedFirst rc="+rc+",player="+Pplayer+",eswn="+Peswn+",swRobot="+RS.RSP[Peswn].swRobot);//~va8uR~
        return rc;                                                 //~va8uI~
    }                                                              //~va8uI~
    //*********************************************************************//~va96I~
    //*from UARon at button Pushed                                 //~va96I~
    //*********************************************************************//~va96I~
    public boolean isRonableMultiWaitMatchModeHuman(int Pplayer,boolean PswTake,TileData PtdRon)//~va96R~
    {                                                              //~va96I~
    	int eswn= Accounts.playerToEswn(Pplayer);                   //~va96I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitMatchModeHuman player="+Pplayer+",eswn="+eswn+",swTake="+PswTake+",tdRon="+PtdRon.toString());//~va96R~
//      int[] itsH=AG.aRoundStat.RSP[eswn].getItsHandYou();        //~va96I~//~vaadR~
        int[] itsH=AG.aRoundStat.getItsHandEswnYou(eswn);          //~vaadI~
        int   ctrH=AG.aRoundStat.RSP[eswn].ctrHand;                //~va96I~
        boolean rc=isRonableMultiWait(PswTake,Pplayer,eswn,itsH,ctrH,PtdRon);//~va96R~
        if (errMultiWait!=0)                                       //~va96R~
		    issueRonableMultiWaitErrMsg();                         //~va96I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitMatchModeHuman rc="+rc);//~va96R~
        return rc;                                                 //~va96R~
    }                                                              //~va96I~
    //*********************************************************************//~va96I~
    //*from UARon at button Pushed                                 //~va96I~
    //*********************************************************************//~va96I~
    public int issueRonableMultiWaitErrMsg()                       //~va96I~
    {                                                              //~va96I~
        if ((errMultiWait & RARON_ERR_FIX)!=0)                     //~va96I~
        {                                                          //~va96I~
			if (RuleSettingYaku.getYakuFix()==YAKUFIX_FIRST)       //~va96I~
		        errMultiWait|=RARON_ERR_FIXFIRST;                  //~va96I~
            else                                                   //~va96I~
		        errMultiWait|=RARON_ERR_FIXMIDDLE;                 //~va96I~
        }                                                          //~va96I~
        if ((errMultiWait & RARON_ERR_CONSTRAINT)!=0)              //~va96I~
        {                                                          //~va96I~
        	if (RS.swFix2)                                         //~va96I~
		        errMultiWait|=RARON_ERR_CONSTRAINT2;               //~va96I~
            else                                                   //~va96I~
		        errMultiWait|=RARON_ERR_CONSTRAINT1;               //~va96I~
        }                                                          //~va96I~
        if (Dump.Y) Dump.println("RARon.issueRonableMultiWaitErrMsg errMultiWait="+Integer.toHexString(errMultiWait));//~va96I~
        if ((errMultiWait & RARON_ERR_FURITEN)!=0)                 //~va96M~
			GMsg.drawMsgbar(R.string.AE_MultiWait_Furiten);        //~va96M~
        else                                                       //~va96M~
        if ((errMultiWait & RARON_ERR_MULTIPLE)!=0)                //~va96I~
			GMsg.drawMsgbar(R.string.AE_MultiWait_Multiple);       //~va96I~
        else                                                       //~va96I~
        if ((errMultiWait & RARON_ERR_CONSTRAINT1)!=0)             //~va96I~
			GMsg.drawMsgbar(R.string.AE_MultiWait_Constraint1);    //~va96I~
        else                                                       //~va96I~
        if ((errMultiWait & RARON_ERR_CONSTRAINT2)!=0)             //~va96I~
			GMsg.drawMsgbar(R.string.AE_MultiWait_Constraint2);    //~va96I~
        else                                                       //~va96I~
        if ((errMultiWait & RARON_ERR_FIXFIRST)!=0)                //~va96R~
			GMsg.drawMsgbar(R.string.AE_MultiWait_FixFirst);       //~va96I~
        else                                                       //~va96I~
        if ((errMultiWait & RARON_ERR_FIXMIDDLE)!=0)               //~va96R~
			GMsg.drawMsgbar(R.string.AE_MultiWait_FixMiddle);      //~va96I~
        return errMultiWait;                                       //~va96I~
    }                                                              //~va96I~
}//class RARon                                                     //~va8uR~
