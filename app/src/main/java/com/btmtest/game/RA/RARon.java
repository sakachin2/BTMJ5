//*CID://+va8uR~: update#= 125;                                    //~va8uR~
//**********************************************************************//~v101I~
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
import com.btmtest.game.Robot;
import com.btmtest.game.TileData;
import com.btmtest.game.UA.Rank;
import com.btmtest.game.UA.RonResult;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.gv.Pieces.*;
//********************************************************************************************//~v@@5R~
//player:position of each player on the device; You area always 0(Hands is show at bottom)//~v@@5R~
//********************************************************************************************//~v@@5R~
public class RARon                                               //~v@@@R~//~va60R~//~1111R~
{                                                                  //~0914I~
	RoundStat RS;//~va60I~
    private int[] itsTile=new int[CTR_TILETYPE];                   //~1111I~
    private int[] itsHand;                                         //~1111I~
    private boolean[] btsOrphan;                                   //~1111I~
    private int ctrOrphan;                                         //~1111I~
    private TileData[][] tdssHand;                                 //~1111I~
    private int[][] dupCtrAll=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR];//hand and earth //4*9//~1116I~
    public int[][] dupCtr=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR];	//4*9//~9C12I~//~va11R~//~1116I~
    public int hanExceptDora;	//output of getvalueExceptDora     //~1219I~
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
        if (Dump.Y) Dump.println("RARon.init");//~va60I~//~1111R~  //~1118R~
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
    //*********************************************************    //~va8jI~
    //*from RACall at human take in playalone notify mode          //~va8jI~
    //*under shanten=-1                                            //~va8jI~
    //*********************************************************    //~va8jI~
    public  boolean callRonTakenPlayAloneNotify(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,TileData PtdTaken)//~va8jI~
    {                                                              //~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonTakenPlayAloneNotify player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdTaken.toString());//~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonTakenPlayAloneNotify ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~va8jI~
        boolean rc=isRonableMultiWait(true/*swTake*/,Pplayer,Peswn,PitsHand,PctrHand,PtdTaken);//~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonTakenPlayAloneNotify@@@@ rc="+rc+",player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdTaken.toString());//~va8jI~
        return rc;                                                 //~va8jI~
    }                                                              //~va8jI~
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
    //*********************************************************    //~va8jI~
    //*from RACall at human Ron in playalone notify mode           //~va8jI~
    //*under shanten=-1                                            //~va8jI~
    //*********************************************************    //~va8jI~
    public  boolean callRonRiverPlayAloneNotify(int Pplayer,int Peswn,int[] PitsHand,int PctrHand,TileData PtdDiscarded)//~va8jI~
    {                                                              //~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayAloneNotify player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdDiscarded.toString());//~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayAloneNotify ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~va8jI~
        boolean rc=isRonableMultiWait(false/*swTake*/,Pplayer,Peswn,PitsHand,PctrHand,PtdDiscarded);//~va8jI~
        if (Dump.Y) Dump.println("RARon.callRonRiverPlayAloneNotify@@@@ rc="+rc+",player="+Pplayer+",eswn="+Peswn+",tdTaken="+PtdDiscarded.toString());//~va8jI~
        return rc;                                                 //~va8jI~
    }                                                              //~va8jI~
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
    //*from RADEval.getRonValue and RAReach.getValue                                  //~1117I~//~1120R~//~1212R~
    //*********************************************************    //~1117I~
    public int getRonValue(int Pplayer,int[] PitsHand,int Ppos)    //~1117I~
    {                                                              //~1117I~
        if (Dump.Y) Dump.println("RARon.getRonValue player="+Pplayer+",pos="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~1117I~
//      RonResult r=AG.aUARonValue.getValue(false/*swTake*/,Pplayer,PitsHand,null);//~1117I~//~1206R~
//      RonResult r=AG.aUARonValue.getValue(true/*swTake*/,Pplayer,PitsHand,null);//~1206I~//~1212R~
        TileData tdTaken=new TileData(Ppos/CTR_NUMBER_TILE,Ppos%CTR_NUMBER_TILE);   //to give ronType and ronNumber//~1212I~
        RonResult r=AG.aUARonValue.getValue(true/*swTake*/,Pplayer,PitsHand,tdTaken);//~1212I~
        int han;                                                   //~1117I~
        if (r.isYakuman())                                         //~1117I~
        	han= Rank.MIN_RANK_YAKUMAN;     //13                            //~1117I~
        else                                                       //~1117I~
        	han=r.han;                                             //~1117I~
        if (Dump.Y) Dump.println("RARon.getRonValue rc="+han+",ronResult="+r.toString());//~1117I~
        return han;                                                //~1117I~
    }                                                              //~1117I~
    //************************************************************ //~1219R~
    //*from RAReach to evaluate winList(exclude han taken)         //~1219R~
    //*accidental Yaku(OnShot,Last,Rinshan because evaluation for Reach)//~va8cI~
    //*and set hanExceptDora(except self-draw:for Reach chk only) for constraint chk                    //~1219I~//~va8jR~
    //************************************************************ //~1219I~
    public int getRonValueExceptDora(int Pplayer,int[] PitsHand,int Ppos)//~1120I~
    {                                                              //~1120I~
        if (Dump.Y) Dump.println("RARon.getRonValueExceptDora player="+Pplayer+",pos="+Ppos+",itsHand="+Utils.toString(PitsHand,9));//~1120I~
        TileData tdRon=new TileData(Ppos/CTR_NUMBER_TILE,Ppos%CTR_NUMBER_TILE);   //dummy//~1121I~
//      RonResult r=AG.aUARonValue.getValue(false/*swTake*/,Pplayer,PitsHand,tdRon);//~1120I~//~1121R~//~1219R~
        RonResult r=AG.aUARonValue.getValue(true/*swTake*/,Pplayer,PitsHand,tdRon); //swTake:true for do not add for tdRon//~1219I~
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
        if (Dump.Y) Dump.println("RARon.getRonValueExceptDora rc="+han+",hanExceptDora="+hanExceptDora+",ronResult="+r.toString());//~1120I~//~1219R~
        return han;                                                //~1120I~
    }                                                              //~1120I~
    //*********************************************************    //~1117I~
    //*constraint chk  for robot ron/take                                             //~1213I~//~va8cR~
    //*********************************************************    //~1213I~
    public boolean isRonable(RonResult PronResult)                 //~1117I~
    {                                                              //~1117I~
    	boolean rc=true;                                           //~1117I~
        if (!PronResult.isYakuman())                               //~1117I~
        {                                                          //~1117I~
//      	int han=PronResult.getHanExceptDora();                               //~1117I~//~va8cR~
        	int han=getHanExceptDoraConstraint(PronResult,RS.swFix2);//~va8cR~
//          int ctrDup=RS.gameCtrDup;                              //~1117I~//~1119R~
//          if (ctrDup>=RS.constraintFix2)   //2han constraint from dupctr>=4 or 5//~1118R~//~1119R~
            if (RS.swFix2)   //2han constraint from dupctr>=4 or 5//~1119I~
                rc=han>=2;                                         //~1117I~
            else                                                   //~1117I~
                rc=han>=1;                                         //~1117I~
	        if (Dump.Y) Dump.println("RARon.isRonable not Yakuman han="+han+",swfix2="+RS.swFix2);//~1414I~
        }                                                          //~1117I~
        if (Dump.Y) Dump.println("RARon.isRonable@@@@ rc="+rc);         //~1117I~//~1130R~//~1220R~
        return rc;                                                 //~1117I~
    }                                                              //~1117I~
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
     	if (Dump.Y) Dump.println("RARon.callRonChankan for other called Kan type="+Ptype+",eswnOther="+PeswnOther+",playerCalled="+PplayerCalled+"eswnCalled="+PeswnCalled+",tdKan="+TileData.toString(PtdKan));//~1118I~//~1120R~//~1130R~//~1201R~
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
        			issueRon(GCM_TAKE,player,PeswnOther);                                    //~1118I~//~1130R~//~1131R~
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
        	rc=callRonRiver(PeswnOther,PtdKan);                    //~1201I~
        }                                                          //~1118I~//~1201R~
     	if (Dump.Y) Dump.println("RARon.callRonChankan for other called Kan type="+Ptype+",rc="+rc);//~1118R~//~1120R~
        return rc;                                                 //~1118I~
    }                                                              //~1118I~
    //*********************************************************    //~1117I~
    private void issueRon(int Paction,int PplayerCaller,int PeswnCaller)                                        //~1117I~//~1130R~//~1131R~
    {                                                              //~1117I~
        if (Dump.Y) Dump.println("RARon.issueRon Pplayercaller="+PplayerCaller+",Peswncaller="+PeswnCaller+",action="+Paction);                //~1117I~//~1130R~//~1131R~
        Robot r=RS.RSP[PeswnCaller].robot;                         //~1130I~
        r.sendToServer(false/*waiterBlock*/,GCM_RON,PeswnCaller,"");//~1130I~
    }                                                              //~1117I~
    //*********************************************************    //~1213I~
    private boolean isFuriten(int PeswnOther,int[] PitsHand,int PctrHand)//~1213I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RARon.isFuriten eswnOther="+PeswnOther+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1213I~//~1306R~//~va8jR~
        boolean rc=AG.aRADSmart.chkFuriten(PeswnOther,PitsHand,PctrHand);//~1213I~
        if (Dump.Y) Dump.println("RARon.isFuriten rc="+rc);        //~1213I~//~1306R~
        return rc;                                              //~1213I~//~1305R~
    }                                                              //~1213I~
    //*********************************************************    //~va8cI~
    private int getHanExceptDoraConstraint(RonResult PronResult,boolean PswFix2)//~va8cR~
    {                                                              //~va8cI~
        if (Dump.Y) Dump.println("RARon.getHanExceptDoraConstraint RonResult="+PronResult.toString()+",swFix2="+PswFix2+",swYakuFixLast="+RS.swYakuFixLast);//~va8cR~
        boolean swIgnoreAccidental=PswFix2 && !RS.swYakuFixLast;	//ignore accidental YAKU(one shot,haitei,rinshan)//~va8cI~
//      int han=PronResult.getHanExceptDoraConstraint(swIgnoreAccidental);//~va8cR~//~va8iR~
        int han=PronResult.getHanExceptDoraConstraint(swIgnoreAccidental,PswFix2);//~va8iI~
        return han;
    }                                                              //~va8cI~
    //*********************************************************    //~va8fI~
    //*for Robot(callRonTaken,callRonRiver)                        //~va8fR~
    //*for Human in playAloneMode(callRonRiverPlayAloneNotify,callRonTakenPlayAlone)           //~va8fR~//~va8uR~
    //*chk furiten and ronable(chk kataAgari)                      //~va8fI~
    //*********************************************************    //~va8fI~
    private boolean isRonableMultiWait(boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,TileData PtdDiscarded)//~va8fI~
    {                                                              //~va8fI~
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
    	if (!isChkYakuMultiWait(PswTake,r))                         //~va8fI~
        {                                                          //~va8fI~
          if (!PswTake)                                            //+va8uI~
			if (isFuriten(Peswn,PitsH,PctrH))                      //~va8fR~
            {                                                      //~va8fI~
	        	if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ allow Multiwait return false by Furiten");//~va8fR~
              	return false;                                      //~va8fR~
            }                                                      //~va8fI~
	        if (Dump.Y) Dump.println("RARon.isRonableMultiWait@@@@ allow multiwait return true by NOT Furiten swTake=+PswTake");//~va8fI~//+va8uR~
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
	}                                                              //~va8fI~//~va8iR~
    //*********************************************************    //~va8fI~
    //*for Robot from RADSmart.chkFuritenMultiWait(callback)       //~va8fI~
    //*chk MultiWait(1/2han constraint)                            //~va8fI~
    //*********************************************************    //~va8fI~
    public boolean isRonableMultiWaitCB(int PposTile,boolean PswTake,int Pplayer,int Peswn,int[] PitsH,int PctrH,TileData PtdDiscarded)//~va8fR~
    {                                                              //~va8fI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCB posWinTile="+PposTile+",swTake="+PswTake+",eswn="+Peswn+",tdDiscarded="+PtdDiscarded.toString()+",itsHand="+Utils.toString(PitsH,9));//~va8fR~
        int posDiscarded=RAUtils.getPosTile(PtdDiscarded);         //~va8fI~
        if (PposTile==posDiscarded)                                    //~va8fI~
        {                                                          //~va8fI~
        	if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCB return true by pos is of PtdDiscarded");//~va8fI~
        	return true;                                           //~va8fI~
        }                                                          //~va8fI~
//      PitsH[PposTile]++;                                         //~va8fR~
        TileData tdRon=new TileData(PposTile/CTR_NUMBER_TILE,PposTile%CTR_NUMBER_TILE);   //to give ronType and ronNumber//~va8fI~
        RonResult r=getRonValue(false/*PswTake*/,Pplayer,PitsH,tdRon);//~va8fR~
//      PitsH[PposTile]--;                                         //~va8fR~
        boolean rc=isRonable(r);                                   //~va8fI~
        if (Dump.Y) Dump.println("RARon.isRonableMultiWaitCB rc="+rc+",swTake="+PswTake+",PposTile="+PposTile+",eswn="+Peswn+",player="+Pplayer+",ctrH="+PctrH+",PitsH="+Utils.toString(PitsH,9));//~va8fR~
        return rc;                                                 //~va8fI~
	}                                                              //~va8fI~
    //*********************************************************    //~va8fI~
//  private boolean  isChkYakuMultiWait(boolean PswTake)           //~va8fR~
    private boolean  isChkYakuMultiWait(boolean PswTake,RonResult PronResult)//~va8fI~
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
        	if (PronResult.isTakeAllInHand())                      //~va8mI~
            {                                                      //~va8mI~
    	        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait skip multiwait chk by menzen");//~va8mI~
            	rc=false;	//allow multiwait                      //~va8mI~
            }                                                      //~va8mI~
        }                                                          //~va8mI~
        if (Dump.Y) Dump.println("RARon.isChkYakuMultiWait@@@@ rc="+rc+",swTake="+PswTake+",swYakuFixLast="+AG.aUARonValue.swYakuFixLast+",swYakuMultiwaitOK="+AG.aUARonValue.swYakuFixMultiwaitOK);//~va8fR~//~va8kR~
        return rc;
    }                                                              //~va8fI~
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
}//class RARon                                                     //~va8uR~
