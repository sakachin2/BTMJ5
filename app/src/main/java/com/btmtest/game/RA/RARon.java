//*CID://+DATER~: update#=  77;                                    //~1111R~
//**********************************************************************//~v101I~
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
    //*from RADSmart at to discard                                 //~1120R~
    //*under shanten=-1                                            //~1120I~
    //*********************************************************    //~1118I~
    public  boolean callRonTaken(int PplayerDiscard,int PeswnDiscard,int[] PitsHand,TileData PtdTaken)//~1117R~//~1118R~//~1120R~
    {                                                              //~1117I~
        if (Dump.Y) Dump.println("RARon.callRon player="+PplayerDiscard+",eswnDiscard="+PeswnDiscard+",tdTaken="+PtdTaken.toString());//~1117I~//~1120R~
        RonResult r=getRonValue(true/*PswTake*/,PplayerDiscard,PitsHand,PtdTaken);//~1117R~//~1118R~//~1120R~
        boolean rc=isRonable(r);                                           //~1117I~
        if (rc)                                                    //~1117I~
        	issueRon(GCM_TAKE,PplayerDiscard/*player taken=now discard*/,PeswnDiscard);                                            //~1117I~//~1130R~//~1131R~
        return rc;                                                 //~1117I~
    }                                                              //~1117I~
    //*********************************************************    //~1118I~
    //*from RACall.discarded                                       //~1120I~
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
          if (!isFuriten(PeswnOther,itsH,ctrH))                    //~1213I~
          {                                                        //~1213I~
        	RonResult r=getRonValue(false/*PswTake*/,player,itsH,PtdDiscarded);//~1118I~
        	rc=isRonable(r);                                       //~1118I~
          }                                                        //~1213I~
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
    //*and set hanExceptDora for constraint chk                    //~1219I~
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
        	hanExceptDora=r.getHanExceptDora();                    //~1219I~
        	if (hanExceptDora>0)                                   //~1219I~
	        	hanExceptDora--;	//drop of take                 //~1219I~
        }                                                          //~1219I~
        if (Dump.Y) Dump.println("RARon.getRonValueExceptDora rc="+han+",hanExceptDora="+hanExceptDora+",ronResult="+r.toString());//~1120I~//~1219R~
        return han;                                                //~1120I~
    }                                                              //~1120I~
    //*********************************************************    //~1117I~
    //*constraint chk                                              //~1213I~
    //*********************************************************    //~1213I~
    public boolean isRonable(RonResult PronResult)                 //~1117I~
    {                                                              //~1117I~
    	boolean rc=true;                                           //~1117I~
        if (!PronResult.isYakuman())                               //~1117I~
        {                                                          //~1117I~
        	int han=PronResult.getHanExceptDora();                               //~1117I~
//          int ctrDup=RS.gameCtrDup;                              //~1117I~//~1119R~
//          if (ctrDup>=RS.constraintFix2)   //2han constraint from dupctr>=4 or 5//~1118R~//~1119R~
            if (RS.swFix2)   //2han constraint from dupctr>=4 or 5//~1119I~
                rc=han>=2;                                         //~1117I~
            else                                                   //~1117I~
                rc=han>=1;                                         //~1117I~
        }                                                          //~1117I~
        if (Dump.Y) Dump.println("RARon.isRonable@@@@ rc="+rc);         //~1117I~//~1130R~//~1220R~
        return rc;                                                 //~1117I~
    }                                                              //~1117I~
    //*********************************************************    //~1118I~
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
        if (Dump.Y) Dump.println("RARon.isFuriten eswnOther="+PeswnOther+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9,PctrHand));//~1213I~//+1306R~
        boolean rc=AG.aRADSmart.chkFuriten(PeswnOther,PitsHand,PctrHand);//~1213I~
        if (Dump.Y) Dump.println("RARon.isFuriten rc="+rc);        //~1213I~//+1306R~
        return rc;                                              //~1213I~//~1305R~
    }                                                              //~1213I~
}//class RARon                                                 //~dataR~//~@@@@R~//~v@@@R~//~va60R~//~1111R~
