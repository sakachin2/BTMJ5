//*CID://+vaq5R~: update#= 302;                                    //~vaq5R~
//**********************************************************************//~v101I~
//2022/08/15 vaq5 if chkFix1 is OFF, confirm no fix err before apply 8cont yakuman//~vaq5I~
//2022/08/15 vaq4 if chkFix1 os OFF, issue fix multiple err msg for Constraint err//~vaq4I~
//2022/08/06 vapx add Psuedo-Tennpai:No option                     //~vapxI~
//2022/08/04 vapu PsuedoTenpai;simplify 0han ok or not(allow kataagari,fix last)//~vapuI~
//2022/08/04 vapt PsuedoTenpai;allow kataagari,fix yaku err if furiten OK//~vaptI~
//2022/08/03 vapq Psuedo tenpai; change 2-han constraint option to require 2-han regardless 0-han OK for 1-han constraint option.//~vappI~
//2022/08/03 vapp Psuedo tenpai; drop allNo(chk tenpai required for repeat/next round) and apply Fix Yaku,Kata-Agari err//~vappI~
//2022/07/30 vapk implements keishiki tenpai                       //~vapkI~
//2022/07/24 vapa with Oper:furiten check=No option, avoid lit Win button if furiten in palyMatchNotify mode//~vapaI~
//2022/07/24 vap6 OpenReach Robot,chk furiten to allow to discard  //~vap6I~
//2022/07/24 vap4 Yakuman for discarding OpenReach winning tile; change option for human discard to Yakuman or reject//~vap4I~
//2022/02/20 vakh set kataagari err different from fix err         //~vakhI~
//2022/02/19 vak8 (BUG)with FuritenChk=NO and FuritenReachOK, it can call Ron for furiten tile.//~vak8I~
//2022/02/19 vak7 drop option chk kataagari(temporally False always AND and option is chk furiten only)//~vak7I~
//2022/02/18 vak6 differenciate kataagari err  and fix err         //~vak6I~
//                fixLast:allow kataagari, else chk kataagari with chk option if not fix err//~vak6I~
//2022/02/16 vak5 with no chk kataagari,do not lit win button in notify mode for human//~vak5I~
//2022/02/16 vak4 with no chk 1hanConstraint/furiten,do not lit win button in notify mode for human//~vak4I~
//2022/02/15 vak2 No 1han constraint err msg when chk option:off(oper setting)//~vak2I~
//2022/02/15 vak1 Furiten msg even chkMultiwait option is OFF(chk furiten and kataagari)//~vak1I~
//2022/01/23 vaja (Bug)Chankan was not notified and not get win for Human//~vajaI~
//2022/01/20 vaj7 display furiten err after reach on complte/drawnhw/drawnlast dialog//~vaj7I~
//2022/01/20 vaj6 set Err for missing Ron(including Take) after Reach//~vaj6I~
//2022/01/19 vaj5 Not ronable when furiten even if taken if furitenreachoption=No//~vaj5I~
//2021/11/13 vagm (Bug)Robot Ron was intercepted by Robot Pon by headbump sequence//~vagmI~
//2021/11/11 vagg (Bug)Robot chankan Notified to human even if rule is not atoduke//~vaggI~
//2021/11/10 vagf (Bug)Robot could Ron not by chankan                  //~vagfI~//~vaggR~
//2021/11/10 vage if Not atoduke,disallow accidental han for Robot not only when fix2 constraint//~vageI~
//2021/11/06 vag0 (Bug)Kan call is not shanten up                  //~vag0I~
//2021/11/01 vafn chk ronable(inclucding 2han constraint) required if shanten up to 0 in Not AllInHand//~vafnI~
//2021/10/28 vafc pon/chii call for INTENT_TANYAO                  //~vafcI~
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
import android.graphics.Point;

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
import static com.btmtest.game.UA.Rank.*;
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
   public static final int RARON_ERR_FURITEN_REACH=0x0100;         //~vaj7I~
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
    private boolean swCheckFix1; //oper setting to chk 1han constraint//~vak2I~
    private int errMultiWait;
    private int errMultiWaitNoChkMode;                             //~vak4I~
    private boolean swCheckMultiWait;                              //~vaa8I~
    private boolean swCheckFuriten;                                //~vak7I~
    public  int amtRonValue;	//output of getRonValue to caller  //~vaajR~
    private int eswnKanAdd=-1;   //chankan                         //~vaaTI~
    private boolean swChankan;   //add RYAKU_KAN_ADD               //~vaaUI~
    private int actionEvaluateCall2nd,posTopEvaluateCall2nd;      //~vafnI~
    private int environmentYaku;                                   //~vagfI~
	private int typeYakuFix;                                       //~vak5I~
    private boolean              swChkFixErrMultiWait;                                  //~vakhI~
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
		typeYakuFix=RuleSettingYaku.getYakuFix();                  //~vak5I~
		swYakuFixLast= RuleSettingYaku.isYakuFixLast();             //~va91I~
        swYakuFixMultiWaitOK=RuleSettingYaku.isYakuFixMultiwaitOK();//~va91I~
        int fixTake=RuleSettingYaku.getYakuFixMultiwaitTake();      //~va91I~
        swYakuFixMultiwaitOKTake=fixTake==YAKUFIX_TAKE_ALL;        //~va91I~
//      swYakuFixMultiwaitOKTakeAllInHand=fixTake==YAKUFIX_TAKE_ALLINHAND;//~va91R~//~va98R~
        swCheckMultiWait= RuleSettingOperation.isCheckMultiWait();  //~vaa8I~
        swCheckFuriten=RuleSettingOperation.isCheckFuriten();     //~vak7I~
        swCheckFix1= RuleSettingOperation.isYakuFix1();            //~vak2I~
        if (Dump.Y) Dump.println("RARon.init swYakuFixMultiWaitOK="+swYakuFixMultiWaitOK+",takeOK="+fixTake+",swCheckMultiWait="+swCheckMultiWait+",swCheckFuriten="+swCheckFuriten);//~va91R~//~vaa8R~//~vak7R~
    }                                                              //~va60I~
    //*********************************************************    //~1117I~
    //*from RADSmart at Robot discarding                           //~vajaR~
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
    	if (isErrInNoChkMode())                                    //~vak4I~
        	rc=false;                                              //~vak4I~
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
        if (Dump.Y) Dump.println("RARon.callRonTakenPlayMatchNotify ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~vaa2I~//~vaj6R~
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
        if (Dump.Y) Dump.println("RARon.callRonRiver entry player="+player+",eswn="+PeswnOther+",shanten="+shanten+",pos="+pos+",tdDiscarded="+PtdDiscarded.toString());//~1118I~//~1120I~//~1125R~//~1130R~//~vagmR~
        if (shanten==-1)                                           //~1118I~
        {                                                          //~1118I~
//  		setRonableAfterReach(PeswnOther);                      //~vaj7R~
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
//      	issueRon(GCM_RON,player,PeswnOther);                                            //~1118I~//~1130R~//~1131R~//~vagmR~
        	rc=issueRon(GCM_RON,player,PeswnOther);                //~vagmI~
        if (Dump.Y) Dump.println("RARon.callRonRiver exit rc="+rc);//~vagmI~
        return rc;                                                 //~1118I~
    }                                                              //~1118I~
    //*********************************************************    //~vaaUI~
    //*at KAN_ADD                                                  //~vaj7I~
    //*********************************************************    //~vaj7I~
//  public  boolean callRonRiverNotifyChankan(int PplayerRon,int PeswnRon,int[] PitsH,int PctrH,TileData PtdKan)//~vaaUR~//~vagfR~
    private boolean callRonRiverNotifyChankan(int PplayerRon,int PeswnRon,int[] PitsH,int PctrH,TileData PtdKan)//~vagfI~
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
//  	setRonableAfterReach(PeswnRon);                            //~vaj7R~
    	if (AG.swPlayAloneNotify)                                  //~vaaUI~
        {                                                          //~vaaUI~
//        	PitsH[pos]++;                                          //~vaaUR~
            swChankan=true;     //add Yaku KAN_ADD                 //~vaaUI~
            environmentYaku=RYAKU_KAN_ADD;                         //~vaggI~
//  		rc=callRonRiverPlayAloneNotify(PplayerRon,PeswnRon,PitsH,PctrH+1,PtdKan);//~vaaUR~//~vapuR~
    		rc=callRonRiverPlayAloneNotify(PplayerRon,PeswnRon,PitsH,PctrH,PtdKan); //ctrH+1 cause winlist=0//~vapuI~
            environmentYaku=0;                                     //~vaggI~
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
//      	PitsH[pos]++;                                          //~vaaUI~//~vajaR~
            swChankan=true;     //add Yaku KAN_ADD                 //~vaaUI~
            environmentYaku=RYAKU_KAN_ADD;                         //~vaggI~
//    		rc=callRonRiverPlayMatchNotify(PplayerRon,PeswnRon,PitsH,PctrH+1,PtdKan);//~vaaUR~//~vapuR~
      		rc=callRonRiverPlayMatchNotify(PplayerRon,PeswnRon,PitsH,PctrH,PtdKan); //ctrH+1 cause winlist=0 and fulitenchk fail//~vapuI~
            environmentYaku=0;                                     //~vaggI~
            swChankan=false;    //add Yaku KAN_ADD                 //~vaaUI~
//      	PitsH[pos]--;                                          //~vaaUI~//~vajaR~
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
    	if (isErrInNoChkMode())                                    //~vak4I~
        	rc=false;                                              //~vak4I~
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
    	if (isErrInNoChkMode())                                    //~vapaI~
        	rc=false;                                              //~vapaI~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayMatchNotify@@@@ rc="+rc+",player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdDiscarded.toString());//~vaaUI~
        return rc;                                                 //~vaaUI~
    }                                                              //~vaaUI~
//    //*********************************************************    //~1116I~//~vajaR~
//    private RonResult getRonValue(boolean PswTake, int Pplayer, int[] PitsHand, TileData PtdTaken)//~1116R~//~1117R~//~vajaR~
//    {                                                              //~1116I~//~vajaR~
//        int han=0;                                               //~vajaR~
//        if (Dump.Y) Dump.println("RARon.getRonValue player="+Pplayer+",swTake="+PswTake+",tdTaken="+PtdTaken.toString()+",itsHand="+Utils.toString(PitsHand,9));//~1116I~//~1117R~//~vajaR~
//        RonResult r=AG.aUARonValue.getValue(PswTake,Pplayer,PitsHand,PtdTaken);//~1116R~//~1117R~//~vajaR~
//        if (Dump.Y) Dump.println("RARon.getRonValue ronResult="+r.toString());//~1117R~//~vajaR~
//        return r;                                                  //~1117R~//~vajaR~
//    }                                                              //~1116I~//~vajaR~
    //*********************************************************    //~vagfI~
    private RonResult getRonValue(boolean PswTake, int Pplayer, int[] PitsHand, TileData PtdTaken,int PenvironmentYaku)//~vagfI~
    {                                                              //~vagfI~
        int han=0;                                                 //~vagfI~
        if (Dump.Y) Dump.println("RARon.getRonValue player="+Pplayer+",swTake="+PswTake+",environmentYaku="+PenvironmentYaku+",tdTaken="+PtdTaken.toString()+",itsHand="+Utils.toString(PitsHand,9));//~vagfI~
        RonResult r=AG.aUARonValue.getValueEvaluateRon(PswTake,Pplayer,PitsHand,PtdTaken,PenvironmentYaku);//~vagfI~
        if (Dump.Y) Dump.println("RARon.getRonValue ronResult="+r.toString());//~vagfI~
        return r;                                                  //~vagfI~
    }                                                              //~vagfI~
    //*********************************************************    //~1117I~
    //*from RADEval.getRonValue                                    //~vab5R~
    //*for Robot                                                   //~vajaI~
    //*********************************************************    //~1117I~
//  public int getRonValue(int Pplayer,int[] PitsHand,int Ppos)    //~1117I~//~vab5R~
    public int getRonValueEvaluate(int Pplayer,int[] PitsHand,int Ppos)//~vab5R~
    {                                                              //~1117I~
        if (Dump.Y) Dump.println("RARon.getRonValueEvaluate player="+Pplayer+",pos="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~1117I~//~vab5R~
//      RonResult r=AG.aUARonValue.getValue(false/*swTake*/,Pplayer,PitsHand,null);//~1117I~//~1206R~
//      RonResult r=AG.aUARonValue.getValue(true/*swTake*/,Pplayer,PitsHand,null);//~1206I~//~1212R~
        TileData tdTaken=new TileData(Ppos/CTR_NUMBER_TILE,Ppos%CTR_NUMBER_TILE);   //to give ronType and ronNumber//~1212I~
//      RonResult r=AG.aUARonValue.getValue(true/*swTake*/,Pplayer,PitsHand,tdTaken);//~1212I~//~vagfR~
        RonResult r=AG.aUARonValue.getValueEvaluateRon(true/*swTake*/,Pplayer,PitsHand,tdTaken,0/*environmentYaku*/);	//avoid chkKan refers completeflag//~vagfI~
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
        if (han==0)                                                //~vab5I~
	    	amtRonValue=0;                                         //~vab5I~
        if (Dump.Y) Dump.println("RARon.getRonValueEvaluate rc="+han+",swConstraintOK="+swConstraintOK+",ronResult="+r.toString());//~1117I~//~vab5R~
        return han;                                                //~1117I~
    }                                                              //~1117I~
    //*********************************************************    //~vafnI~
    //*from RADEval.getRonValue for Robot                                    //~vafnI~//~vajaR~
    //*********************************************************    //~vafnI~
    public Point getRonValueEvaluateCall2nd(int Pplayer, int[] PitsHand, int Ppos,int Paction,int PposTop)//~vafnI~
    {                                                              //~vafnI~
        if (Dump.Y) Dump.println("RARon.getRonValueEvaluateCall2nd player="+Pplayer+",posWin="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~vafnI~
        if (Dump.Y) Dump.println("RARon.getRonValueEvaluateCall2nd action="+Paction+",PposTop="+PposTop);//~vafnI~
        actionEvaluateCall2nd=Paction;                             //~vafnI~
        posTopEvaluateCall2nd=PposTop;                    //~vafnI~
    	Point hanAndAmt=getRonValueEvaluateCall(Pplayer,PitsHand,Ppos);//~vafnI~
        actionEvaluateCall2nd=0;                                   //~vafnI~
        return hanAndAmt;
    }                                                              //~vafnI~
    //*********************************************************    //~vafcI~
    //*from RADEval.getRonValue                                    //~vafcI~
    //*********************************************************    //~vafcI~
    public Point getRonValueEvaluateCall(int Pplayer, int[] PitsHand, int Ppos)//~vafcR~
    {                                                              //~vafcI~
        if (Dump.Y) Dump.println("RARon.getRonValueEvaluateCall player="+Pplayer+",posWin="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~vafcR~
        TileData tdTaken=new TileData(Ppos/CTR_NUMBER_TILE,Ppos%CTR_NUMBER_TILE);   //to give ronType and ronNumber//~vafcR~
//      RonResult r=AG.aUARonValue.getValueEvaluateCall(Pplayer,PitsHand,tdTaken);//~vafcI~//~vafnR~
        RonResult r;                                               //~vafnI~
        if (actionEvaluateCall2nd!=0)                              //~vafnI~
        	r=AG.aUARonValue.getValueEvaluateCall2nd(Pplayer,PitsHand,tdTaken,actionEvaluateCall2nd,posTopEvaluateCall2nd);//~vafnI~
        else                                                       //~vafnI~
        	r=AG.aUARonValue.getValueEvaluateCall(Pplayer,PitsHand,tdTaken);//~vafnI~
        int han;                                                   //~vafcI~
        boolean swConstraintOK=false;                              //~vafcI~
        if (r.isYakuman())                                         //~vafcI~
        	han= Rank.MIN_RANK_YAKUMAN;     //13                   //~vafcI~
        else                                                       //~vafcI~
        {                                                          //~vafcI~
        	han=r.getHanExceptDora();                              //~vafcI~
            if (RS.swFix2)   //2han constraint from dupctr>=4 or 5 //~vafcI~
                swConstraintOK=han>=2;                             //~vafcI~
            else                                                   //~vafcI~
                swConstraintOK=han>=1;                             //~vafcI~
            if (swConstraintOK)                                    //~vafcI~
            	han=r.han;	//including Dora                       //~vafcI~
        }                                                          //~vafcI~
    	int amt=r.amt;	//output of getRonValue to caller          //~vafcI~
        if (han==0)                                                //~vafcI~
	    	amt=0;                                                 //~vafcI~
        Point hanAndAmt=new Point(han,amt);                        //~vafcI~
        if (Dump.Y) Dump.println("RARon.getRonValueEvaluateCall hanAndAmt="+hanAndAmt.toString());//~vafcI~
        return hanAndAmt;                                          //~vafcI~
    }                                                              //~vafcI~
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
    //*from CompReqDlg.chkApply8Cont                               //~vaq5I~
    //*********************************************************    //~1213I~
//  public boolean isRonable(RonResult PronResult)                 //~1117I~//~va91R~
//  private boolean isRonable(RonResult PronResult)                //~va91I~//~vaq5R~
    public  boolean isRonable(RonResult PronResult)                //~vaq5I~
    {                                                              //~1117I~
    	boolean rc=true;                                           //~1117I~
        if (!PronResult.isYakuman())                               //~1117I~
        {                                                          //~1117I~
//      	int han=PronResult.getHanExceptDora();                               //~1117I~//~va8cR~
        	int han=getHanExceptDoraConstraint(PronResult,RS.swFix2);//~va8cR~//~va96R~
	        if (Dump.Y) Dump.println("RARon.isRonable after getHanExceptDoraConstraint han="+han+",ronResult="+PronResult);//~vaq4I~
//          int ctrDup=RS.gameCtrDup;                              //~1117I~//~1119R~
//          if (ctrDup>=RS.constraintFix2)   //2han constraint from dupctr>=4 or 5//~1118R~//~1119R~
            if (RS.swFix2)   //2han constraint from dupctr>=4 or 5//~1119I~
                rc=han>=2;                                         //~1117I~
            else                                                   //~1117I~
//          if (!swChankan)	//not effective for 2 han constraint   //~vaaUI~//~vaggR~
                rc=han>=1;                                         //~1117I~
	        if (Dump.Y) Dump.println("RARon.isRonable not Yakuman han="+han+",swfix2="+RS.swFix2);//~1414I~//~va91R~
        }                                                          //~1117I~
        if (Dump.Y) Dump.println("RARon.isRonable@@@@ swChankan="+swChankan+",environmentYaku="+environmentYaku+",rc="+rc);         //~1117I~//~1130R~//~1220R~//~vaaUR~//~vaggR~
        return rc;                                                 //~1117I~
    }                                                              //~1117I~
    //*********************************************************    //~vapkI~
    private boolean isRonableDrawnLast(RonResult PronResult,boolean PswFix2)//~vapkI~
    {                                                              //~vapkI~
    	boolean rc=true;                                           //~vapkI~
        if (!PronResult.isYakuman())                               //~vapkI~
        {                                                          //~vapkI~
        	int han=getHanExceptDoraConstraint(PronResult,PswFix2);//~vapkI~
            if (PswFix2)   //2han constraint from dupctr>=4 or 5   //~vapkI~
                rc=han>=2;                                         //~vapkI~
            else                                                   //~vapkI~
                rc=han>=1;                                         //~vapkI~
	        if (Dump.Y) Dump.println("RARon.isRonable not Yakuman han="+han+",Pswfix2="+PswFix2);//~vapkI~
        }                                                          //~vapkI~
        if (Dump.Y) Dump.println("RARon.isRonable rc="+rc+",PswFix2="+PswFix2);//~vapkI~
        return rc;                                                 //~vapkI~
    }                                                              //~vapkI~
    //*********************************************************    //~va91I~
    private boolean isRonableFixErr(RonResult PronResult)          //~va91I~
    {                                                              //~va91I~
    	swChkFixErr=true;     //parm to getHanExceptDoraConstraint //~va91I~
	    boolean rc=isRonable(PronResult);                          //~va91I~
    	swChkFixErr=false;                                         //~va91I~
        if (Dump.Y) Dump.println("RARon.isRonableFixErr@@@@ rc="+rc);//~va91I~
        return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //*********************************************************    //~vak7I~
    //*allow kataagari by option                                   //~vak7I~
    //*********************************************************    //~vak7I~
    private boolean isRonableFixErrMultiWait(boolean PswTake,boolean PswRobot,RonResult PronResult)//~vak7I~
    {                                                              //~vak7I~
        if (Dump.Y) Dump.println("RARon.isRonableFixErrMultiWait swTake="+PswTake+",swRobot="+PswRobot+",ronResult="+PronResult.toString());//~vak7I~
    	swChkFixErr=true;     //parm to getHanExceptDoraConstraint //~vak7I~
    	swChkFixErrMultiWait=true;     //parm to getHanExceptDoraConstraint,chk also kataagari at take//~vakhI~
	    boolean rc=isRonable(PronResult);                          //~vak7I~
    	swChkFixErrMultiWait=false;     //parm to getHanExceptDoraConstraint,chk also kataagari at take//~vakhI~
    	swChkFixErr=false;                                         //~vak7I~
        if (!rc)                                                   //~vak7I~
        	if (!PswRobot)                                         //~vak7R~
            {                                                      //~vakhI~
              if (PronResult.swMultiWaitErr)                       //~vakhI~
            	errMultiWait|=RARON_ERR_MULTIPLE;//=0x80;          //~vakhI~
              else                                                 //~vakhI~
            	errMultiWait|=RARON_ERR_FIX;//issue msg            //~vak7R~
            }                                                      //~vakhI~
        if (Dump.Y) Dump.println("RARon.isRonableFixErrMultiWait rc="+rc+",errMultiWait=0x"+Integer.toHexString(errMultiWait));//~vak7I~//~vapxR~//~vaq4R~
        return rc;                                                 //~vak7I~
    }                                                              //~vak7I~
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
            environmentYaku=RYAKU_KAN_ADD;                         //~vagfI~
        	rc=callRonRiver(PeswnOther,PtdKan);                    //~1201I~
            environmentYaku=0;                                     //~vagfI~
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
//  				setRonableAfterReach(PeswnOther);              //~vaj7R~
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
//  private void issueRon(int Paction,int PplayerCaller,int PeswnCaller)                                        //~1117I~//~1130R~//~1131R~//~vagmR~
    private boolean issueRon(int Paction,int PplayerCaller,int PeswnCaller)//~vagmI~
    {                                                              //~1117I~
        if (Dump.Y) Dump.println("RARon.issueRon Pplayercaller="+PplayerCaller+",Peswncaller="+PeswnCaller+",action="+Paction);                //~1117I~//~1130R~//~1131R~
        if ((TestOption.option3 & TestOption.TO3_IT_RARON)!=0)                //~va91I~
        {                                                          //~va91I~
	        if (Dump.Y) Dump.println("RARon.issueRon@@@@ return by TestOption");//~va91I~
//            return;                                                //~va91I~//~vagmR~
            return false;                                          //~vagmI~
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
        if (Dump.Y) Dump.println("RARon.issueRon exit rc=true");   //~vagmI~
        return true;                                               //~vagmI~
    }                                                              //~1117I~
    //*********************************************************    //~vaaUI~
    //*chankan 13orphan                                            //~vaaUI~
    //*********************************************************    //~vaaUI~
    private boolean issueRonTakenNotifyChankan(int Paction,int PplayerRon,int PeswnRon,int[] PitsH,int PctrH,TileData PtdKan)//~vaaUR~
    {                                                              //~vaaUI~
        if (Dump.Y) Dump.println("RARon.issueRonTakenNotifyChankan PplayerRon="+PplayerRon+",PeswnRon="+PeswnRon+",action="+Paction+",tdKan="+PtdKan.toString());//+vaaUR~        boolean rc=false;//~vaaUR~//~vak4R~
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
        if (Dump.Y) Dump.println("RARon.issueRonTakenNotifyChankan rc="+rc);//~vaaUR~//~vak4R~
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
//      boolean swIgnoreAccidental=PswFix2 && !swYakuFixLast;	//ignore accidental YAKU(one shot,haitei,rinshan)//~va91I~//~vageR~
        boolean swIgnoreAccidental=!swYakuFixLast;	//ignore accidental YAKU(one shot,haitei,rinshan) even if not Fix2 conbstraint//~vageI~
//      int han=PronResult.getHanExceptDoraConstraint(swIgnoreAccidental);//~va8cR~//~va8iR~
		int han;                                                   //~va91I~
      	if (swChkFixErrMultiWait)                                  //~vakhI~
			han=PronResult.getHanExceptDoraConstraintChkFixMultiWait(swIgnoreAccidental,PswFix2);//~vakhI~
        else                                                       //~vakhI~
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
    //*for Human in playMatchNotifyMode(callRonTakenPlayMatchNotify,callRonRiverPlayMatchNotify)//~vapxI~
    //*chk furiten and ronable(chk kataAgari)                      //~va8fI~
    //*********************************************************    //~va8fI~
    private boolean isRonableMultiWait(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,TileData PtdDiscarded)//~va8fI~
    {                                                              //~va8fI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ entry swTake="+PswTake+",player="+Pplayer+",eswn="+Peswn+",tdDiscarded="+PtdDiscarded+toString()+",itsHand="+Utils.toString(PitsH,9));//~va8fI~//~va8iR~//~va91M~//~vaj7R~
        errMultiWait=0;                                            //~va96M~
        errMultiWaitNoChkMode=0;         //control Win button light to notify//~vak4R~
    	boolean rc=true;                                                //~va8fR~//~va91R~
        boolean swAllInHand=RS.RSP[Peswn].swAllInHand;             //~va91I~
        boolean swRobot=RS.RSP[Peswn].swRobot;                     //~vak2I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ swRobot="+swRobot+",swCheckFix1="+swCheckFix1);//~vak2I~
        RonResult r;                                               //~vak6I~
      if (swRobot || swCheckFix1)                                  //~vak2R~
      {                                                            //~vak2I~
//      RonResult r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded);//~va91I~//~vagfR~
//      RonResult r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded,environmentYaku);//~vagfI~//~vak6R~
        r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded,environmentYaku);//~vak6I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ result="+r.toString());//~va91I~
        rc=isRonable(r);   //no chk kataagariErr                         //~va91I~
        if (!rc)    //constraint NG                                //~va91I~
        {                                                          //~va91I~
        	if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ return false by constraint NG ronResult="+r.toString()+",tdDiscarded="+PtdDiscarded.toString());//~va91I~
          if (!swRobot)                                            //~vak2I~
        	errMultiWait|=RARON_ERR_CONSTRAINT;                    //~va96I~
            return false;                                          //~va91I~
        }                                                          //~va91I~
      }                                                            //~vak2I~
      else	//human and not checkFix1                              //~vak4I~
      {                                                            //~vak4I~
//      RonResult r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded,environmentYaku);//~vak4I~//~vak6R~
        r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded,environmentYaku);//~vak6I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ result="+r.toString());//~vak4I~
        rc=isRonable(r);   //no chk kataagariErr                   //~vak4I~
        if (!rc)    //constraint NG                                //~vak4I~
        {                                                          //~vak4I~
        	if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ 1han constraint err for human NOT swCheckFix1 r="+r.toString()+",tdDiscarded="+PtdDiscarded.toString());//~vak4I~
            errMultiWaitNoChkMode|=RARON_ERR_CONSTRAINT;           //~vak4I~
            rc=true;	//continue other check                     //~vak4I~
            return rc;  //no errmsg                                //~vaq4I~
        }                                                          //~vak4I~
      }                                                            //~vak4I~
//		if (RS.RSP[Peswn].isReachStatusErrFuriten())               //~vaj7I~//~vak8R~
  		if (RS.RSP[Peswn].isReachStatusErrFuriten(PswTake))        //~vak8I~
        {                                                          //~vaj7I~
            if (Dump.Y) Dump.println("RARon.isRonableMultiWait return false by Furiten Reach eswn="+Peswn);//~vaj7I~//~vajaR~
            errMultiWait|=RARON_ERR_FURITEN_REACH;	//  =0x0100;   //~vaj7I~
//          rc=false;                                              //~vaj7I~//~vak1R~
            return false;                                          //~vak1I~
        }                                                          //~vaj7I~
//      else                                                       //~vaj7I~//~vak1R~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait swCheckFuriten="+swCheckFuriten);//~vak1I~//~vak5R~//~vak7R~//~vapxR~
//      boolean swRobot=RS.RSP[Peswn].swRobot;                     //~vak1I~//~vak2R~
//  	if (swCheckMultiWait)	//oper setting(chk kataagara & furiten)//~vak1I~//~vak6R~
//  	if (swCheckMultiWait || swRobot)	//oper setting(chk kataagara & furiten)//~vak6I~//~vak7R~
    	if (swCheckFuriten || swRobot)	//oper setting(chk kataagara & furiten)//~vak7I~
        {                                                          //~vak1I~
            if (!PswTake && isFuriten(Peswn,PitsH,PctrH))  //chk winlist//~vak1R~
            {                                                      //~vak1R~
                if (Dump.Y) Dump.println("RARon.isRonableMultiWait swCheckFuriten ON:return false by Furiten swRobot="+swRobot);//~vak1R~//~vak7R~
                if (!swRobot) //chk furiten for human              //~vak1I~
                	errMultiWait|=RARON_ERR_FURITEN;    //  =0x01; //~vak1R~
                return false;                                      //~vak1R~
            }                                                      //~vak1I~
        }                                                          //~vak1I~
        else                                                       //~vak4I~
        {                                                          //~vak4I~
//          if (!swRobot) //chk furiten for human                  //~vak4I~//~vak6R~
                if (!PswTake && isFuriten(Peswn,PitsH,PctrH))  //chk winlist//~vak4R~
                {                                                  //~vak4R~
                    if (Dump.Y) Dump.println("RARon.isRonableMultiWait swCheckFuriten OFF:human Furiten");//~vak4R~//~vak7R~
                    errMultiWaitNoChkMode|=RARON_ERR_FURITEN;    //  =0x01;//~vak4R~
                }                                                  //~vak4R~
        }                                                          //~vak4I~
        rc=isRonableFixErrMultiWait(PswTake,swRobot,r);	//sakiduke/nakaduke err//~vak7R~
        if (!rc)	//sakiduke/nakaduke err                        //~vak7I~
        {                                                          //~vak7I~
            if (Dump.Y) Dump.println("RARon.isRonableMultiWait return false by rc of isRonableFixErrMultiWait");//~vak7I~
            return false;                                          //~vak7I~
        }                                                          //~vak7I~
      if (false)                                                   //~vak7I~
      {                                                            //~vak7I~
//  	if (!isChkYakuMultiWait(PswTake,swAllInHand))	//no need to chk kataagari//~va91R~//~vaa8R~
    	if (!isChkYakuMultiWait(PswTake,swAllInHand,RS.RSP[Peswn].swRobot))	//no need to chk kataagari//~vaa8I~
        {                                                          //~va91I~
//          if (swRobot)  //test chk furiten for human          //~vak1R~//~vak6R~
//          {                                                        //~vak1I~//~vak6R~
////          if (!PswTake && isFuriten(Peswn,PitsH,PctrH)) //chk winlist//~va91R~//~vaj5R~//~vak6R~
//            if (!PswTake && isFuriten(Peswn,PitsH,PctrH)  //chk winlist//~vaj5I~//~vak6R~
////          ||   PswTake && RS.RSP[Peswn].isReachStatusErrFuriten())//~vaj5R~//~vaj6R~//~vak6R~
////          ||   PswTake && isFuritenErrReachTake(Peswn,PitsH,PctrH,PtdDiscarded))//~vaj6R~//~vaj7R~//~vak6R~
//            )                                                      //~vaj7I~//~vak6R~
//            {                                                      //~va91R~//~vak6R~
//                if (Dump.Y) Dump.println("RARon.isRonableMultiWait swCheckMultiWait:OFF return false by Furiten");//~va91R~//~vaj5R~//~vak1R~//~vak6R~
////              errMultiWait|=RARON_ERR_FURITEN;    //  =0x01;  No errmsg for robot   //~va96I~//~vak1R~//~vak6R~
//                rc=false;                                          //~va91R~//~vak6R~
//            }                                                      //~va91R~//~vak6R~
//          }                                                        //~vak1I~//~vak6R~
            if (Dump.Y) Dump.println("RARon.isRonableMultiWait isChkYakuMultiWait:OFF No need to chk kataagari rc="+rc);//~vak6I~//~vak7R~
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
      }                                                            //~vak7I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ rc="+rc+",swTake="+PswTake+",swAllInHand="+swAllInHand+",eswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~va8fR~//~va8jR~//~va91R~
        return rc;                                                 //~va8fI~//~va91M~
	}                                                              //~va8fI~//~va8iR~
    //*********************************************************    //~vapxI~
    //*at DrawnLast chk tenpai                                     //~vapxI~
    //* under option of pshuedo Tenpai:No; chk furiten,multiwait,constraint//~vapxI~
    //*********************************************************    //~vapxI~
    private boolean isRonableMultiWaitDrawnLastNo(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,TileData PtdDiscarded)//~vapxI~
    {                                                              //~vapxI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitNo@@@@ entry swTake="+PswTake+",player="+Pplayer+",eswn="+Peswn+",tdDiscarded="+PtdDiscarded+toString()+",itsHand="+Utils.toString(PitsH,9));//~vapxI~
        errMultiWait=0;                                            //~vapxI~
        errMultiWaitNoChkMode=0;         //control Win button light to notify//~vapxI~
    	boolean rc=true;                                           //~vapxI~
        boolean swAllInHand=RS.RSP[Peswn].swAllInHand;             //~vapxI~
        boolean swRobot=RS.RSP[Peswn].swRobot;                     //~vapxI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLastNo@@@@ swRobot="+swRobot+",swCheckFix1="+swCheckFix1);//~vapxR~
        RonResult r;                                               //~vapxI~
//      if (swRobot || swCheckFix1)                                //~vapxI~
//      {                                                          //~vapxI~
        r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded,environmentYaku);//~vapxI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitNoDrawnLast@@@@ result="+r.toString());//~vapxR~
        rc=isRonable(r);   //no chk kataagariErr                   //~vapxI~
        if (!rc)    //constraint NG                                //~vapxI~
        {                                                          //~vapxI~
        	if (Dump.Y) Dump.println("RARon.isRonableMultiWaitNoDrawnLast@@@@ return false by constraint NG ronResult="+r.toString()+",tdDiscarded="+PtdDiscarded.toString());//~vapxR~
//        if (!swRobot)                                            //~vapxI~
        	errMultiWait|=RARON_ERR_CONSTRAINT;                    //~vapxI~
            return false;                                          //~vapxI~
        }                                                          //~vapxI~
//      }                                                          //~vapxI~
//      else  //human and not checkFix1                            //~vapxI~
//      {                                                          //~vapxI~
//        r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded,environmentYaku);//~vapxI~
//        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ result="+r.toString());//~vapxI~
//        rc=isRonable(r);   //no chk kataagariErr                 //~vapxI~
//        if (!rc)    //constraint NG                              //~vapxI~
//        {                                                        //~vapxI~
//            if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ 1han constraint err for human NOT swCheckFix1 r="+r.toString()+",tdDiscarded="+PtdDiscarded.toString());//~vapxI~
//            errMultiWaitNoChkMode|=RARON_ERR_CONSTRAINT;         //~vapxI~
//            rc=true;    //continue other check                   //~vapxI~
//        }                                                        //~vapxI~
//      }                                                          //~vapxI~
//*reach err is chked at RAReach.chkPsuedoTenpaiNo                 //~vapxI~
//        if (RS.RSP[Peswn].isReachStatusErrFuriten(PswTake))      //~vapxR~
//        {                                                        //~vapxR~
//            if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLastNo return false by Furiten Reach eswn="+Peswn);//~vapxR~
//            errMultiWait|=RARON_ERR_FURITEN_REACH;  //  =0x0100; //~vapxR~
//            return false;                                        //~vapxR~
//        }                                                        //~vapxR~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWait swCheckFuriten="+swCheckFuriten);//~vapxI~
//        if (swCheckFuriten || swRobot)  //oper setting(chk kataagara & furiten)//~vapxI~
//        {                                                        //~vapxI~
//          if (!PswTake && isFuriten(Peswn,PitsH,PctrH))  //chk winlist//~vapxI~
//            if (isFuriten(Peswn,PitsH,PctrH))  //chk winlist     //~vapxR~
//            {                                                    //~vapxR~
//                if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLastNo swCheckFuriten ON:return false by Furiten swRobot="+swRobot);//~vapxR~
////              if (!swRobot) //chk furiten for human            //~vapxR~
//                    errMultiWait|=RARON_ERR_FURITEN;    //  =0x01;//~vapxR~
//                return false;                                    //~vapxR~
//            }                                                    //~vapxR~
//        }                                                        //~vapxI~
//        else                                                     //~vapxI~
//        {                                                        //~vapxI~
//                if (!PswTake && isFuriten(Peswn,PitsH,PctrH))  //chk winlist//~vapxI~
//                {                                                //~vapxI~
//                    if (Dump.Y) Dump.println("RARon.isRonableMultiWait swCheckFuriten OFF:human Furiten");//~vapxR~
//                    errMultiWaitNoChkMode|=RARON_ERR_FURITEN;    //  =0x01;//~vapxI~
//                }                                                //~vapxI~
//        }                                                        //~vapxI~
        rc=isRonableFixErrMultiWait(PswTake,swRobot,r);	//sakiduke/nakaduke err//~vapxI~
        if (!rc)	//sakiduke/nakaduke err                        //~vapxI~
        {                                                          //~vapxI~
            if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLastNo return false by rc of isRonableFixErrMultiWait()");//~vapxR~
            return false;                                          //~vapxI~
        }                                                          //~vapxI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitNoDrawnLast@@@@ rc="+rc+",swTake="+PswTake+",swAllInHand="+swAllInHand+",eswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~vapxR~
        return rc;                                                 //~vapxI~
	}                                                              //~vapxI~
    //*********************************************************    //~vappI~
    //*for Robot(callRonTaken,callRonRiver)                        //~vappI~
    //*for Human in playAloneMode(callRonRiverPlayAloneNotify,callRonTakenPlayAlone)//~vappI~
    //*chk furiten and ronable(chk kataAgari)                      //~vappI~
    //*********************************************************    //~vappI~
    private boolean isRonableMultiWaitDrawnLast(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,TileData PtdDiscarded)//~vappR~
    {                                                              //~vappI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast@@@@ entry swTake="+PswTake+",player="+Pplayer+",eswn="+Peswn+",tdDiscarded="+PtdDiscarded+toString()+",itsHand="+Utils.toString(PitsH,9));//~vappR~
        errMultiWait=0;                                            //~vappI~
        errMultiWaitNoChkMode=0;         //control Win button light to notify//~vappR~
    	boolean rc=true;                                           //~vappI~
        boolean swAllInHand=RS.RSP[Peswn].swAllInHand;             //~vappI~
        boolean swRobot=RS.RSP[Peswn].swRobot;                     //~vappI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast swRobot="+swRobot+",swAllInHand="+swAllInHand+",environmentYaku="+environmentYaku);//~vappR~
        RonResult r;                                               //~vappI~
//    if (swRobot || swCheckFix1)                                  //~vappR~
//*at DrawnLast chekking under ! 0-han OK                          //~vappI~
//    {                                                            //~vappR~
        r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded,environmentYaku);//~vappR~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast result="+r.toString());//~vappR~
        rc=isRonable(r);   //no chk kataagariErr                   //~vappI~
        if (!rc)    //constraint NG                                //~vappI~
        {                                                          //~vappI~
        	if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast@@@@ return false by constraint NG ronResult="+r.toString()+",tdDiscarded="+PtdDiscarded.toString());//~vappI~
//        if (!swRobot)                                            //~vappR~
        	errMultiWait|=RARON_ERR_CONSTRAINT;                    //~vappI~
            return false;                                          //~vappI~
        }                                                          //~vappI~
//    }                                                            //~vappR~
//      else  //human and not checkFix1                            //~vappR~
//      {                                                          //~vappR~
//        r=getRonValue(PswTake,Pplayer,PitsH,PtdDiscarded,environmentYaku);//~vappR~
//        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast@@@@ result="+r.toString());//~vappR~
//        rc=isRonable(r);   //no chk kataagariErr                 //~vappR~
//        if (!rc)    //constraint NG                              //~vappR~
//        {                                                        //~vappR~
//            if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast@@@@ 1han constraint err for human NOT swCheckFix1 r="+r.toString()+",tdDiscarded="+PtdDiscarded.toString());//~vappR~
//            errMultiWaitNoChkMode|=RARON_ERR_CONSTRAINT;         //~vappR~
//            rc=true;    //continue other check                   //~vappR~
//        }                                                        //~vappR~
//      }                                                          //~vappR~
//		if (RS.RSP[Peswn].isReachStatusErrFuriten(PswTake))        //~vappR~
//      {                                                          //~vappR~
//          if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast return false by Furiten Reach eswn="+Peswn);//~vappR~
//          errMultiWait|=RARON_ERR_FURITEN_REACH;	//  =0x0100;   //~vappR~
//          return false;                                          //~vappR~
//      }                                                          //~vappR~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast swCheckFuriten="+swCheckFuriten);//~vappI~
//* if furiten is not allowed, already set not a PsuedoTenpai      //~vappI~
//        if (swCheckFuriten || swRobot)  //oper setting(chk kataagara & furiten)//~vappR~
//        {                                                        //~vappR~
//            if (!PswTake && isFuriten(Peswn,PitsH,PctrH))  //chk winlist//~vappR~
//            {                                                    //~vappR~
//                if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast swCheckFuriten ON:return false by Furiten swRobot="+swRobot);//~vappR~
//                if (!swRobot) //chk furiten for human            //~vappR~
//                    errMultiWait|=RARON_ERR_FURITEN;    //  =0x01;//~vappR~
//                return false;                                    //~vappR~
//            }                                                    //~vappR~
//        }                                                        //~vappR~
//        else                                                     //~vappR~
//        {                                                        //~vappR~
//                if (!PswTake && isFuriten(Peswn,PitsH,PctrH))  //chk winlist//~vappR~
//                {                                                //~vappR~
//                    if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast swCheckFuriten OFF:human Furiten");//~vappR~
//                    errMultiWaitNoChkMode|=RARON_ERR_FURITEN;    //  =0x01;//~vappR~
//                }                                                //~vappR~
//        }                                                        //~vappR~
//        if (isFuriten(Peswn,PitsH,PctrH))  //chk winlist         //~vaptR~
//        {                                                        //~vaptR~
//            if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast swCheckFuriten OFF:human Furiten");//~vaptR~
//            errMultiWaitNoChkMode|=RARON_ERR_FURITEN;    //  =0x01;//~vaptR~
//        }                                                        //~vaptR~
//        rc=isRonableFixErrMultiWait(PswTake,swRobot,r); //sakiduke/nakaduke err//~vappI~//~vapuR~
//        if (!rc)    //sakiduke/nakaduke err                        //~vappI~//~vapuR~
//        {                                                          //~vappI~//~vapuR~
//            //set errMultiWait|=RARON_ERR_MULTIPLE or errMultiWait|=RARON_ERR_FIX;//issue msg//~vappI~//~vapuR~
//            if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast return false by rc of isRonableFixErrMultiWait errMultiWait="+Integer.toHexString(errMultiWait));//~vappR~//~vapuR~
//            return false;                                          //~vappI~//~vapuR~
//        }                                                          //~vappI~//~vapuR~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast@@@@ rc="+rc+",swTake="+PswTake+",swAllInHand="+swAllInHand+",eswn="+Peswn+",player="+Pplayer+",errMultiWait="+Integer.toString(errMultiWait)+",errMultiWaitNoChkMode="+Integer.toHexString(errMultiWaitNoChkMode)+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~vappR~
        return rc;                                                 //~vappI~
	}                                                              //~vappI~
//    //*********************************************************    //~vaj6I~//~vaj7R~
//    //*At taken chk furiten                                        //~vaj6I~//~vaj7R~
//    //*err if furiten at reach with FuritenReach=Nochk             //~vaj6I~//~vaj7R~
//    //* or take after missed ron tile after reach                  //~vaj6I~//~vaj7R~
//    //*********************************************************    //~vaj6I~//~vaj7R~
//    private boolean isFuritenErrReachTake(int Peswn,int[] PitsH,int PctrH,TileData PtdDiscarded/*talken*/)//~vaj6R~//~vaj7R~
//    {                                                              //~vaj6I~//~vaj7R~
//        if (Dump.Y) Dump.println("RARon.isFuritenerrReachTake eswn="+Peswn+",PitsH="+Utils.toString(PitsH,9));//~vaj6I~//~vaj7R~
//        boolean rc=false;                                          //~vaj6I~//~vaj7R~
//        if (RS.RSP[Peswn].isReachStatusErrFuriten())               //~vaj6I~//~vaj7R~
//            rc=true;                                               //~vaj6I~//~vaj7R~
//        else                                                       //~vaj6I~//~vaj7R~
//        if (RS.RSP[Peswn].isReachStatusOKFuriten())                //~vaj6I~//~vaj7R~
//        {                                                          //~vaj6I~//~vaj7R~
//            if (Dump.Y) Dump.println("RARon.isFuritenerrReachTake rc=false by Furiten accepted at Reach eswn="+Peswn);//~vaj6I~//~vaj7R~
//        }                                                          //~vaj6I~//~vaj7R~
//        else                                                       //~vaj6I~//~vaj7R~
//        if (RS.RSP[Peswn].isReachCalled())                         //~vaj6I~//~vaj7R~
//        {                                                          //~vaj6I~//~vaj7R~
//            int posTaken=RAUtils.getPosTile(PtdDiscarded);         //~vaj6I~//~vaj7R~
//            PitsH[posTaken]--;  //for getWinList                   //~vaj6I~//~vaj7R~
//            if (isFuriten(Peswn,PitsH,PctrH-1))                    //~vaj6R~//~vaj7R~
//                rc=true;                                           //~vaj6I~//~vaj7R~
//            PitsH[posTaken]++;  //for getWinList                   //~vaj6I~//~vaj7R~
//        }                                                          //~vaj6I~//~vaj7R~
//        if (Dump.Y) Dump.println("RARon.isFuritenerrReachTake rc="+rc+",eswn="+Peswn);//~vaj6I~//~vaj7R~
//        return rc;                                                 //~vaj6I~//~vaj7R~
//    }                                                              //~vaj6I~//~vaj7R~
    //*********************************************************    //~va8fI~
    //*for Robot from RADSmart.chkFuritenMultiWait(callback) after furitenchk      //~va8fI~//~va91R~
    //*chk MultiWait(1/2han constraint)                            //~va8fI~
    //*rc:true:ronable                                             //~va91I~
    //*NOT Used                                                    //~vakhI~
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
//      RonResult r=getRonValue(false/*PswTake*/,Pplayer,PitsH,tdRon);//~va8fR~//~vagfR~
        RonResult r=getRonValue(false/*PswTake*/,Pplayer,PitsH,tdRon,environmentYaku);//~vagfI~
//      PitsH[PposTile]--;                                         //~va8fR~
//      boolean rc=isRonable(r);                                   //~va8fI~//~va91R~
        boolean rc=isRonableFixErr(r);                             //~va91I~//~vak6R~
//      if (!rc)                                                   //~va96I~//~vak5R~
//      	errMultiWait|=RARON_ERR_FIX; //set for all win tile at caller//~vak5R~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCB rc="+rc+",swTake="+PswTake+",PposTile="+PposTile+",eswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~va8fR~
        return rc;                                                 //~va8fI~
	}                                                              //~va8fI~
    //*********************************************************    //~va96I~
    //*only from RADSmart.chkFuritenMultiWait                      //~vak5I~
    //*********************************************************    //~vak5I~
    public void setRonableMultiWaitCBFuriten(int PposTile)         //~va96R~
    {                                                              //~va96I~
		errMultiWait|=RARON_ERR_FURITEN;                           //~va96I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCBFuriten posTile="+PposTile+",errMultiWait="+errMultiWait);//~va96I~
    }                                                              //~va96I~
    //*********************************************************    //~vak5I~
    //*only from RADSmart.chkFuritenMultiWait                      //~vak5I~
    //*return true:furiten chk mode                                //~vak5I~
    //*********************************************************    //~vak5I~
    public boolean setRonableMultiWaitCBFuritenRon(int Peswn,int PposTile)//~vak5I~
    {                                                              //~vak5I~
    	boolean swRobot=RS.RSP[Peswn].swRobot;                     //~vak5I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCBFuriten eswn="+Peswn+",swRobot="+swRobot+",swCheckFuriten="+swCheckFuriten+",posTile="+PposTile+",errMultiWait="+errMultiWait);//~vak5I~//~vak7R~
//  	if (swCheckMultiWait || swRobot)                           //~vak5I~//~vak7R~
    	if (swCheckFuriten || swRobot)                             //~vak7I~
        {                                                          //~vak5I~
			setRonableMultiWaitCBFuriten(PposTile);                //~vak5I~
            return true;                                           //~vak5I~
        }                                                          //~vak5I~
        errMultiWaitNoChkMode|=RARON_ERR_FURITEN;                  //~vak5I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCBFuriten return False eswn="+Peswn+",errMultiWaitNoChkMode="+Integer.toHexString(errMultiWaitNoChkMode));//~vak5I~
        return false;                                              //~vak5I~
    }                                                              //~vak5I~
    //*********************************************************    //~va96I~
    //*from RADSmart, Ok is not all of ctrWin and no furiten       //~va96I~
    //*********************************************************    //~va96I~
    public void setRonableMultiWaitCBErr(int Preason)              //~va96R~
    {                                                              //~va96I~
//  	errMultiWait|=RARON_ERR_MULTIPLE;                          //~va96R~
      if (Preason==RARON_ERR_MULTIPLE && !swCheckMultiWait)	//kataagari err in no chk mode//~vak5I~
    	errMultiWaitNoChkMode|=Preason;                            //~vak5I~
      else                                                         //~vak5I~
    	errMultiWait|=Preason;                                     //~va96I~
        if (Dump.Y) Dump.println("RARon.setRonableMultiWaitCBErr reason="+Integer.toHexString(Preason)+",errMultiWait="+Integer.toHexString(errMultiWait)+",errMultiWaitNoChkMode="+Integer.toHexString(errMultiWaitNoChkMode)+",swCheckMultiWait="+swCheckMultiWait);//~va96R~//~vak5R~
    }                                                              //~va96I~
    //*********************************************************    //~va8fI~
    //*chk whether kataagari chk required                          //~vaa4I~
    //*********************************************************    //~vaa4I~
////  private boolean  isChkYakuMultiWait(boolean PswTake)           //~va8fR~//~vak2R~
////  private boolean  isChkYakuMultiWait(boolean PswTake,RonResult PronResult)//~va8fI~//~va98R~//~vak2R~
//    private boolean  isChkYakuMultiWait_deprecated(boolean PswTake,RonResult PronResult)//~va98I~//~vak2R~
//    {                                                              //~va8fI~//~vak2R~
//        boolean rc=true;    //kataagari NG                         //~va8fI~//~vak2R~
////      if (PswTake)                                               //~va8kI~//~va8mR~//~vak2R~
////          rc=false;   //allow multiwait                          //~va8kI~//~va8mR~//~vak2R~
////      else                                                       //~va8kI~//~va8mR~//~vak2R~
//        if (AG.aUARonValue.swYakuFixLast)   //allow yaku at last   //~va8fI~//~vak2R~
//        {                                                          //~va8fI~//~vak2R~
//            if (AG.aUARonValue.swYakuFixMultiwaitOK)                  //~va8fI~//~vak2R~
//                rc=false;   //allow multiwait                      //~va8fI~//~vak2R~
//        }                                                          //~va8fI~//~vak2R~
////      else                                                       //~va8fI~//~va8kR~//~vak2R~
////      {                                                          //~va8fI~//~va8kR~//~vak2R~
////          if (PronResult.isTakeAllInHand())                      //~va8fI~//~va8kR~//~vak2R~
////          {                                                      //~va8fI~//~va8kR~//~vak2R~
////              if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait skip multiwait chk by menzen");//~va8fI~//~va8kR~//~vak2R~
////              rc=false;   //allow multiwait                      //~va8fI~//~va8kR~//~vak2R~
////          }                                                      //~va8fI~//~va8kR~//~vak2R~
////          else                                                   //~va8fI~//~va8kR~//~vak2R~
////          if (PswTake && AG.aUARonValue.swYakuFixMultiwaitDrawOK)   //~va8fI~//~va8kR~//~vak2R~
////              rc=false;   //allow multiwait                      //~va8fI~//~va8kR~//~vak2R~
////      }                                                          //~va8fI~//~va8kR~//~vak2R~
//        else                                                       //~va8mI~//~vak2R~
//        {                                                          //~va8mI~//~vak2R~
//          if (false)        //rc=True, kataagari:NG for FixFirst/FixMiddle//~va91I~//~vak2R~
//          {                                                        //~va91I~//~vak2R~
//            if (PronResult.isTakeAllInHand())                      //~va8mI~//~vak2R~
//            {                                                      //~va8mI~//~vak2R~
//                if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait skip multiwait chk by menzen");//~va8mI~//~vak2R~
//                rc=false;   //allow multiwait                      //~va8mI~//~vak2R~
//            }                                                      //~va8mI~//~vak2R~
//          }                                                        //~va91I~//~vak2R~
//        }                                                          //~va8mI~//~vak2R~
//        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait@@@@ rc="+rc+",swTake="+PswTake+",swYakuFixLast="+AG.aUARonValue.swYakuFixLast+",swYakuMultiwaitOK="+AG.aUARonValue.swYakuFixMultiwaitOK);//~va8fR~//~va8kR~//~vak2R~
//        return rc;                                               //~vak2R~
//    }                                                              //~va8fI~//~vak2R~
    //*********************************************************    //~va91I~
    //*rc=true:chk kataagari, false:no need to chk kataagari       //~va91I~
    //*********************************************************    //~va91I~
//  private boolean  isChkYakuMultiWait(boolean PswTake,boolean PswAllInHand)//~va91I~//~vaa8R~
    private boolean  isChkYakuMultiWait(boolean PswTake,boolean PswAllInHand,boolean PswRobot)//~vaa8I~
    {                                                              //~va91I~
        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait swYakuFixlast="+swYakuFixLast+",PswTake="+PswTake+",PswAllInHand="+PswAllInHand+",PswRobot="+PswRobot+",swCheckMultiWait="+swCheckMultiWait);//~vaa8I~//~vaggR~//~vak5R~
    	if (swYakuFixLast)	//allow yaku at last                   //~vak5I~
        {                                                          //~vak5I~
        	if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait return false by swYakuFixLast");//~vak5R~
            return false;	//allow multiwait                      //~vak5I~
        }                                                          //~vak5I~
//        if (!PswRobot && !swCheckMultiWait) //human obey RuleSetting//~vaa8I~//~vak5R~
//        {                                                          //~vaa8I~//~vak5R~
//            if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait return true for Human by NOT swCheckMultiWait option");//~vaa8I~//~vak5R~
////          return false;                                          //~vaa8I~//~vak5R~
//            return true;    //do kataagarichk without button lit //~vak5R~
//        }                                                          //~vaa8I~//~vak5R~
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
//  	if (swYakuFixLast)	//allow yaku at last                   //~va91I~//~vak5R~
//      {                                                          //~va91I~//~vak5R~
//      	if (swYakuFixMultiWaitOK)  //true by take              //~va91R~//~vak5R~
//          	rc=false;	//allow multiwait                      //~va91I~//~vak5R~
//      }                                                          //~va91I~//~vak5R~
        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait@@@@ rc="+rc+",swTake="+PswTake+",swAllInHand="+PswAllInHand+",swYakuFixLast="+swYakuFixLast+",swYakuMultiwaitOK="+swYakuFixMultiWaitOK+",swYakuFixMultiwaitOKTake="+swYakuFixMultiwaitOKTake);//~va91R~
        return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //*********************************************************************//~va8uI~
    //*chk Fixed First at take not all in hand                     //~va8uI~
    //*rc:true:sakiduke OK                                         //~va8uI~
    //*!! not Used                                                 //+vaq5I~
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
    //*Only from UARon at button Pushed                                 //~va96I~//~vak5R~
    //*********************************************************************//~va96I~
    public boolean isRonableMultiWaitMatchModeHuman(int Pplayer,boolean PswTake,TileData PtdRon)//~va96R~
    {                                                              //~va96I~
    	int eswn= Accounts.playerToEswn(Pplayer);                   //~va96I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitMatchModeHuman player="+Pplayer+",eswn="+eswn+",swTake="+PswTake+",tdRon="+Utils.toString(PtdRon));//~va96R~//~vajaR~
//      int[] itsH=AG.aRoundStat.RSP[eswn].getItsHandYou();        //~va96I~//~vaadR~
        int[] itsH=AG.aRoundStat.getItsHandEswnYou(eswn);          //~vaadI~
        int   ctrH=AG.aRoundStat.RSP[eswn].ctrHand;                //~va96I~
        if (PtdRon != null && PtdRon.isKanAddedTile())       //~vajaI~
        {                                                          //~vajaM~
            swChankan=true;     //add Yaku KAN_ADD                 //~vajaM~
            environmentYaku=RYAKU_KAN_ADD;                         //~vajaM~
        }                                                          //~vajaM~
        boolean rc=isRonableMultiWait(PswTake,Pplayer,eswn,itsH,ctrH,PtdRon);//~va96R~
        environmentYaku=0;                                         //~vajaM~
        swChankan=false;    //add Yaku KAN_ADD                     //~vajaM~
        if (errMultiWait!=0)                                       //~va96R~
		    issueRonableMultiWaitErrMsg();                         //~va96I~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitMatchModeHuman rc="+rc);//~va96R~
        return rc;                                                 //~va96R~
    }                                                              //~va96I~
    //*********************************************************************//~vak4I~
    //*chk errMultiWaitNoChkMode(1han constarint,furiten,kataagari err) set for Human only//~vak4I~
    //*********************************************************************//~vak4I~
    private boolean isErrInNoChkMode()                             //~vak4I~
    {                                                              //~vak4I~
        boolean rc=errMultiWaitNoChkMode!=0;                        //~vak4I~
        if (Dump.Y) Dump.println("RARon.isErrInNoChkMode rc="+rc+",errMultiWaitNoChkMode="+Integer.toHexString(errMultiWaitNoChkMode));//~vak4I~//~vak5R~
        return rc;                                                 //~vak4I~
    }                                                              //~vak4I~
    //*********************************************************************//~va96I~
    //*from UARon at button Pushed                                 //~va96I~
    //*********************************************************************//~va96I~
    public int issueRonableMultiWaitErrMsg()                       //~va96I~
    {                                                              //~va96I~
        if (Dump.Y) Dump.println("RARon.issueRonableMultiWaitErrMsg typeYakuFix="+typeYakuFix+",errMultiWait="+Integer.toHexString(errMultiWait)+",errMultiWaitNoChkMode="+Integer.toHexString(errMultiWaitNoChkMode));//~vak5R~
        if ((errMultiWait & RARON_ERR_FIX)!=0)                     //~va96I~
        {                                                          //~va96I~
//  		if (RuleSettingYaku.getYakuFix()==YAKUFIX_FIRST)       //~va96I~//~vak5R~
    		if (typeYakuFix==YAKUFIX_FIRST)                        //~vak5I~
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
        if ((errMultiWait & RARON_ERR_FURITEN_REACH)!=0)           //~vaj7I~
			GMsg.drawMsgbar(R.string.AE_MultiWait_Furiten_Reach);  //~vaj7I~
        else                                                       //~vaj7I~
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
//    //*******************************************************************************************//~vaj7R~
//    public boolean setRonableAfterReach(int Peswn)               //~vaj7R~
//    {                                                            //~vaj7R~
//        if (Dump.Y) Dump.println("RARon.setRonnableAfterReach swTake="+PswTake+",eswn="+Peswn+",Ptd="+Ptd.toString());//~vaj7R~
//        rc=RS.RSP[Peswn].isReachCalled();                        //~vaj7R~
//        if (rc)                                                  //~vaj7R~
//            RS.RSP[Peswn].setFuritenAfterReach();                //~vaj7R~
//        if (Dump.Y) Dump.println("RARon.setRonnableAfterReach rc="+rc);//~vaj7R~
//        return rc;                                               //~vaj7R~
//    }                                                            //~vaj7R~
//*******************************************************************************************//~vap4I~
//*from Human UADiscard                                            //~vap6R~
//*rc=0: Ptd is discardable, -1:no dicardable tile, 1:other discardable tile exist//~vap4I~
//*******************************************************************************************//~vap4I~
	public int chkRonableOpenReach(int PeswnReach,int PeswnDiscard,TileData PtdDiscard)//~vap4I~
    {                                                              //~vap4I~
		if (Dump.Y) Dump.println("RARon.chkRonableOpenReach eswnReach="+PeswnReach+",eswnDiscard="+PeswnDiscard+",tdDiscard="+PtdDiscard);//~vap4I~
        int rc=-1;                                                 //~vap4R~
        int pos;                                                   //~vap4I~
        int playerReach=RS.RSP[PeswnReach].player;                 //~vap4I~
        int[] itsH=RS.getItsHandEswn(PeswnReach);                  //~vap4I~
        int ctrH=RS.RSP[PeswnReach].ctrHand;                       //~vap4I~
                                                                   //~vap4I~
        pos=RAUtils.getPosTile(PtdDiscard);                        //~vap4I~
//  	if (RS.isDiscardableOpenReach(pos))                        //~vap6R~
    	if (RS.isDiscardableOpenReachHuman(PeswnReach,pos))                   //~vap6I~
        	rc=0;                                                  //~vap4I~
//      else                                                       //~vap6R~
//      if (!isRonableMultiWait(false/*swTake*/,playerReach,PeswnReach,itsH,ctrH,PtdDiscard))	//furiten kataagari chk//~vap6R~
//      {                                                          //~vap6R~
//      	rc=0;                                                  //~vap6R~
//      }                                                          //~vap6R~
        else                                                       //~vap4I~
        {                                                          //~vap4I~
//            TileData[] tdsHand=AG.aPlayers.getHands(PLAYER_YOU); //~vap6R~
//            int pos0=pos;                                        //~vap6R~
//            for (TileData td:tdsHand)                            //~vap6R~
//            {                                                    //~vap6R~
//                pos=RAUtils.getPosTile(td);                      //~vap6R~
//                if (pos==pos0)                                   //~vap6R~
//                    continue;                                    //~vap6R~
////              if (RS.isDiscardableOpenReach(pos))              //~vap6R~
//                if (RS.isDiscardableOpenReachHuman(PeswnReach,pos))//~vap6R~
//                {                                                //~vap6R~
//                    rc=1;                                        //~vap6R~
//                    break;                                       //~vap6R~
//                }                                                //~vap6R~
////              if (!isRonableMultiWait(false/*swTake*/,playerReach,PeswnReach,itsH,ctrH,td))//~vap6R~
////              {                                                //~vap6R~
////                  rc=1;                                        //~vap6R~
////                  break;                                       //~vap6R~
////              }                                                //~vap6R~
//            }                                                    //~vap6R~
	    	if (RS.chkDiscardableOpenReachInHand(PeswnReach,PeswnDiscard))//~vap6R~
				rc=1;                                              //~vap6I~
        }                                                          //~vap4I~
		if (Dump.Y) Dump.println("RARon.chkRonableOpenReach rc="+rc);//~vap4I~
        return rc;
    }                                                              //~vap4I~
    //************************************************************************************//~vapkI~
    //*from RAReach.isPSuedoTenpaiRonable                          //~vappI~
    //*at DrawnLast chk keishiki tenpai of 1/2 han cnstraint OK assuming tsumo//~vappR~
    //*furiten is already chked(condition of furiten OK), under condition of FixLast//~vapkI~
    //*chk furiten and ronable(chk kataAgari)                      //~vapkI~
    //************************************************************************************//~vapkI~
//  public boolean isRonableMultiWaitDrawnLast(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,int Ppos,boolean PswFix2)//~vappR~
//  public int isRonableMultiWaitDrawnLast(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,int Ppos,boolean PswFix2)//~vaptR~
//  public int isRonableMultiWaitDrawnLast(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,int Ppos,boolean PswReach)//~vaptI~//~vapuR~
    public int isRonableMultiWaitDrawnLast(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,int Ppos,boolean PswReach)//~vapuR~
    {                                                              //~vapkI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast entry swTake="+PswTake+",player="+Pplayer+",eswn="+Peswn+",PswReach="+PswReach+",pos="+Ppos+",itsHand="+Utils.toString(PitsH,9));//~vapkR~//~vapuR~
                                                                   //~vappI~
        TileData tdTaken=new TileData(Ppos/CTR_NUMBER_TILE,Ppos%CTR_NUMBER_TILE);   //to give ronType and ronNumber//~vapkI~
        environmentYaku=0;                                         //~vappI~
        if (PswReach)                                              //~vaptI~
	        environmentYaku=RYAKU_REACH;                           //~vaptI~
//      boolean swFix2Save=RS.swFix2;                              //~vappR~
//      boolean swChkFuritenSave=swCheckFuriten;                      //~vappI~//~vaptR~
//      swCheckFuriten=false;	//Furiten OK or Furiten NG & no furiten at DrawnLast//~vaptR~
//      swCheckFuriten=true;	//later apply PsueTenpai option    //~vaptR~
//      RS.swFix2=PswFix2;                                         //~vappR~
                                                                   //~vappI~
    	boolean rc=isRonableMultiWaitDrawnLast(PswTake,Pplayer,Peswn,PitsH,PctrH,tdTaken);//~vappI~
                                                                   //~vappI~
//      RS.swFix2=swFix2Save;                                      //~vappR~
//      swCheckFuriten=swChkFuritenSave;                             //~vappI~//~vaptR~
                                                                   //~vappI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLast rc=errMultiWait="+Integer.toHexString(errMultiWait)+",pos="+Ppos+",swTake="+PswTake+",eswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~vaptR~
//      return rc;                                                 //~vappR~
        return errMultiWait;                                       //~vappR~
	}                                                              //~vapkI~
    //************************************************************************************//~vapxI~
    public int isRonableMultiWaitDrawnLastNo(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,int Ppos,boolean PswReach)//~vapxI~
    {                                                              //~vapxI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLastNo entry swTake="+PswTake+",player="+Pplayer+",eswn="+Peswn+",PswReach="+PswReach+",pos="+Ppos+",itsHand="+Utils.toString(PitsH,9));//~vapxI~
                                                                   //~vapxI~
        TileData tdRon=new TileData(Ppos/CTR_NUMBER_TILE,Ppos%CTR_NUMBER_TILE);   //to give ronType and ronNumber//~vapxR~
        environmentYaku=0;                                         //~vapxI~
        if (PswReach)                                              //~vapxI~
	        environmentYaku=RYAKU_REACH;                           //~vapxI~
        boolean swChkFuritenSave=swCheckFuriten;                   //~vapxI~
    	boolean swCheckFix1Save=swCheckFix1;                       //~vapxI~
        swCheckFuriten=false;	//already chked at RAReach before  //~vapxR~
        swCheckFix1=true;                                          //~vapxI~
                                                                   //~vapxI~
    	boolean rc=isRonableMultiWaitDrawnLastNo(PswTake,Pplayer,Peswn,PitsH,PctrH,tdRon);//~vapxR~
                                                                   //~vapxI~
        swCheckFuriten=swChkFuritenSave;                           //~vapxI~
        swCheckFix1=swCheckFix1Save;                                 //~vapxI~
                                                                   //~vapxI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitDrawnLastNo rc=errMultiWait="+Integer.toHexString(errMultiWait)+",pos="+Ppos+",swTake="+PswTake+",eswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~vapxI~
        return errMultiWait;                                       //~vapxI~
	}                                                              //~vapxI~
}//class RARon                                                     //~va8uR~
