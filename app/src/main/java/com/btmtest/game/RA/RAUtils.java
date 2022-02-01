//*CID://+vaj2R~: update#= 167;                                    //+vaj2R~
//**********************************************************************//~v101I~
//2022/01/19 vaj2 at Reach,wait same color  for 7pair with intent samecolor regardless dora//+vaj2I~
//2021/11/08 vag8 INTENT_ALLSAME;avoid when othercolor meld exist  //~vag7I~
//2021/11/08 vag7 INTENT_ALLSAME;triplet>=1 and (triplet+pair)>=4 and no seq meld//~vag7I~
//2021/10/28 vaff pon/chii call for INTENT_CHANTA                  //~vaffI~
//2021/10/28 vafc pon/chii call for INTENT_TANYAO                  //~vafcI~
//2021/07/25 vab3 selectrMeld;select if possibility of dor even not red5 exist//~vab3I~
//2021/07/12 vaaE select word tile to discard if winlist=1 also for NOT reach//~vaaEI~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//**********************************************************************//~1107I~
package com.btmtest.game.RA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
import com.btmtest.game.Accounts;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.gv.Pieces.*;

import java.util.Arrays;

//********************************************************************************************//~v@@5R~
//player:position of each player on the device; You area always 0(Hands is show at bottom)//~v@@5R~
//********************************************************************************************//~v@@5R~
public class RAUtils                                               //~v@@@R~//~va60R~//~1119R~
{                                                                  //~0914I~
//*************************                                        //~v@@@I~
	private static boolean swSelectRed5;                           //~vab3I~
	public RAUtils()                                               //~v@@@R~//~va60R~//~1119R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("RAUtils Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~va60R~//~1119R~
    }                                                              //~0914I~//~v@@@R~
    //*********************************************************    //~va60I~
    public static int getPosTile(TileData Ptd)                    //~va60I~
    {                                                              //~va60I~
        int pos=Ptd.type*CTR_NUMBER_TILE+Ptd.number;               //~va60I~
        if (Dump.Y) Dump.println("RAUtils.getPosTile pos="+pos+",tile="+Ptd.toString());//~va60I~//~1119R~
        return pos;                                                //~va60I~
    }                                                              //~va60I~
    //*********************************************************    //~1114I~
    public static boolean isTerminal(int Ppos)                     //~1114I~
    {                                                              //~1114I~
        boolean rc=Ppos>OFFS_WORDTILE || Ppos%CTR_NUMBER_TILE==0 || (Ppos%CTR_NUMBER_TILE==CTR_NUMBER_TILE-1);//~1114I~
        if (Dump.Y) Dump.println("RAUtils.isTerminal pos="+Ppos+",rc="+rc);//~1114I~//~1119R~
        return rc;                                                 //~1114I~
    }                                                              //~1114I~
    //*****************************************************        //~va60I~
    public static void countTile(TileData[] Ptds,int[] PitsTile)   //~va60I~
    {                                                              //~va60I~
        Arrays.fill(PitsTile,0);  //34 type                        //~va60I~
        for (TileData td:Ptds)                                     //~va60I~
        {                                                          //~va60I~
            int pos=getPosTile(td);                                //~va60I~
            PitsTile[pos]++;                                       //~va60I~
        }                                                          //~va60I~
        if (Dump.Y) Dump.println("RAUtils.countTile Ptds="+TileData.toString(Ptds)+",itsTile="+Utils.toString(PitsTile,9));//~va60I~//~1119R~
    }                                                              //~va60I~
    //*****************************************************        //~1122I~
    public static int getTilesPos(TileData[] Ptds,int[] PitsPos)   //~1122I~
    {                                                              //~1122I~
        Arrays.fill(PitsPos,0);  //max 14 entry                    //~1122I~
        int ctrHand=0;                                             //~1122I~
        for (TileData td:Ptds)                                     //~1122I~
        {                                                          //~1122I~
            int pos=getPosTile(td);                                //~1122I~
            PitsPos[ctrHand++]=pos;                                //~1122I~
        }                                                          //~1122I~
        if (Dump.Y) Dump.println("RAUtils.getTilesPos ctr="+ctrHand+",Ptds="+TileData.toString(Ptds)+",PitsPos="+Utils.toString(PitsPos,-1,ctrHand));//~1122I~
        return ctrHand;                                            //~1122I~
    }                                                              //~1122I~
    //*****************************************************        //~va60I~
    public static void countTile(int[][] Pdupctr/*from*/,int[] PitsTile/*to*/)   //~va60I~//~1116R~
    {                                                              //~va60I~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~va60R~
        {                                                          //~va60I~
	        System.arraycopy(Pdupctr[ii],0,PitsTile,ii*PIECE_NUMBERCTR,PIECE_NUMBERCTR);//~va60R~
        }                                                          //~va60I~
	    System.arraycopy(Pdupctr[TT_JI],0,PitsTile,PIECE_NUMBERTYPECTR*PIECE_NUMBERCTR,PIECE_NOTNUM_CTR);//~va60I~
        if (Dump.Y) Dump.println("RAUtils.countTile Pdupctr(from)="+Utils.toString(Pdupctr)+",PitsTile(to)="+Utils.toString(PitsTile,9));//~va60R~//~1116R~//~1119R~
    }                                                              //~va60I~
    //*****************************************************        //~vafcI~
    public static int selectTile(int[] PitsHand,int Ptype,boolean Pyes)//~vafcI~
    {                                                              //~vafcI~
    	int rc=-1;                                                 //~vafcI~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~vafcI~
        {                                                          //~vafcI~
        	if (PitsHand[ii]==0)                                   //~vafcI~
            	continue;                                          //~vafcI~
        	if (Ptype==CSI_TANYAO)                                 //~vafcI~
            {                                                      //~vafcI~
            	if (isTanyaoTile(ii))                              //~vafcI~
                {                                                  //~vafcI~
            		if (Pyes)                                      //~vafcI~
                    {                                              //~vafcI~
                    	rc=ii;                                     //~vafcI~
                        break;                                     //~vafcI~
                    }                                              //~vafcI~
                }                                                  //~vafcI~
            	else                                               //~vafcI~
                {                                                  //~vafcI~
            		if (!Pyes)                                     //~vafcI~
                    {                                              //~vafcI~
                    	rc=ii;                                     //~vafcI~
                        break;                                     //~vafcI~
                    }                                              //~vafcI~
                }                                                  //~vafcI~
            }                                                      //~vafcI~
        }                                                          //~vafcI~
        if (Dump.Y) Dump.println("RAUtils.selectTile Ptype="+Ptype+",Pyes="+Pyes+",rc="+rc+",itsHand="+Utils.toString(PitsHand,9));//~vafcR~
        return rc;                                                 //~vafcI~
    }                                                              //~vafcI~
    //*****************************************************        //~1116I~
    public static void countTile(int[] PitsTile/*from*/,int [][] Pdupctr/*to*/)//~1116I~
    {                                                              //~1116I~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~1116I~
        {                                                          //~1116I~
	        System.arraycopy(PitsTile,ii*PIECE_NUMBERCTR,Pdupctr[ii],0,PIECE_NUMBERCTR);//~1116I~
        }                                                          //~1116I~
	    System.arraycopy(PitsTile,PIECE_NUMBERTYPECTR*PIECE_NUMBERCTR,Pdupctr[TT_JI],0,PIECE_NOTNUM_CTR);//~1116I~
        if (Dump.Y) Dump.println("RAUtils.countTile Pdupctr(to)="+Utils.toString(Pdupctr)+",PitsTile(from)="+Utils.toString(PitsTile,9));//~1116I~//~1119R~
    }                                                              //~1116I~
    //*****************************************************        //~va60I~
    public static int getHandPos(int Pplayer,int[] PitsHand)     //~va60I~     //~1120R~
    {                                                              //~va60I~
        TileData[] tdsHand=AG.aPlayers.getHands(Pplayer);    //~1120I~
    	int ctr=getHandPos(tdsHand,PitsHand);                      //~1120R~
        if (Dump.Y) Dump.println("RAUtils.getHandPos itsHand="+Arrays.toString(PitsHand));//~va60I~//~1119R~//~1120R~
        return ctr;                                                //~1120I~
    }                                                              //~va60I~
    //*****************************************************        //~1303I~
    public static int setItsHand(int Pplayer,int[] PitsHand)       //~1303I~
    {                                                              //~1303I~
        TileData[] tdsHand=AG.aPlayers.getHands(Pplayer);          //~1303I~
    	int ctr=tdsHand.length;                                    //~1303I~
	    countTile(tdsHand,PitsHand);                               //~1303I~
        if (Dump.Y) Dump.println("RAUtils.setItsHand player="+Pplayer+",ctr="+ctr+",itsHand="+Utils.toString(PitsHand,9));//~1303I~
        return ctr;                                                //~1303I~
    }                                                              //~1303I~
    //*****************************************************        //~1120I~
    public static int getHandPos(TileData[] Ptds,int[] PitsHandPos)   //~1120R~//~1213R~
    {                                                              //~1120I~
    	int ctr=0;                                                 //~1120I~
        for (TileData td:Ptds)                                     //~1120I~
        {                                                          //~1120I~
            int pos=getPosTile(td);                                //~1120I~
            PitsHandPos[ctr++]=pos;                                   //~1120I~//~1213R~
        }                                                          //~1120I~
        if (Dump.Y) Dump.println("RAUtils.getHandPos ctr="+ctr+",Ptds="+TileData.toString(Ptds)+",itsHandPos="+Arrays.toString(PitsHandPos));//~1120R~//~1213R~
        return ctr;                                                //~1120I~
    }                                                              //~1120I~
    //***********************************************************************//~1118I~//~1119M~
    public static boolean isTanyaoTile(TileData Ptd)               //~1118I~//~1119M~
    {                                                              //~1118I~//~1119M~
    	int pos=getPosTile(Ptd);                         //~1118I~ //~1119I~
	    boolean rc=isTanyaoTile(pos);                              //~1118I~//~1119M~
        if (Dump.Y) Dump.println("RAUtils.isTanyaoTile rc="+rc+",td="+Ptd.toString());//~1118I~//~1119I~
        return rc;                                                 //~1118I~//~1119M~
    }                                                              //~1118I~//~1119M~
    //***********************************************************************//~1118I~//~1119M~
    public static boolean isTanyaoTile(int Ppos)                   //~1118I~//~1119M~
    {                                                              //~1118I~//~1119M~
    	boolean rc;                                                //~1118I~//~1119M~
        if (Ppos>=OFFS_WORDTILE)                                   //~1118I~//~1119M~
            rc=false;                                              //~1118I~//~1119M~
        else                                                       //~1118I~//~1119M~
        {                                                          //~1118I~//~1119M~
        	int num=Ppos%CTR_NUMBER_TILE;                          //~1118I~//~1119M~
            rc=num>0 && num<8;                                     //~1118I~//~1119M~
        }                                                          //~1118I~//~1119M~
        if (Dump.Y) Dump.println("RAUtils.isTanyaoTile pos="+Ppos+",rc="+rc);//~1118I~//~1119I~
        return rc;                                                 //~1118I~//~1119M~
    }                                                              //~1118I~//~1119M~
    //***********************************************************************//~vaffR~
    public static boolean isChantaMeldTile(int Ppos,int Paction)   //~vaffR~
    {                                                              //~vaffR~
    	boolean rc=false;                                             //~vaffR~
        int num=Ppos%CTR_NUMBER_TILE;                              //~vaffI~
        if (Paction==GCM_CHII || Paction==0)                       //~vaffR~
        {                                                          //~vaffI~
            if (Ppos<OFFS_WORDTILE)                                //~vaffR~
            {                                                      //~vaffR~
            	if (num<=TN3 || num>=TN7)                          //~vaffI~
                	rc=true;                                       //~vaffI~
            }                                                      //~vaffR~
        }                                                          //~vaffI~
        if (Paction==GCM_PON || Paction==0)                        //~vaffI~
        {                                                          //~vaffI~
            if (Ppos<OFFS_WORDTILE)                                //~vaffI~
            {                                                      //~vaffI~
                if (num==TN1 || num==TN9)                          //~vaffR~
                	rc=true;                                       //~vaffI~
            }                                                      //~vaffI~
            else                                                   //~vaffI~
            	rc=true;                                           //~vaffI~
        }                                                          //~vaffI~
        if (Dump.Y) Dump.println("RAUtils.isChantaMeldTile action="+Paction+",pos="+Ppos+",num="+num+",rc="+rc);//~vaffR~
        return rc;                                                 //~vaffR~
    }                                                              //~vaffR~
    //***********************************************************************//~1118I~//~1119M~
    //*rc:dragon:2, wind=3, round=2, round+wind=5, get han by ctr/2//~1224I~
    //***********************************************************************//~1118I~//~1119M~
    public static int chkValueWordTile(TileData Ptd,int Peswn)     //~1118R~//~1119M~
    {                                                              //~1118I~//~1119M~
    	int pos=getPosTile(Ptd);                         //~1118I~ //~1119I~
    	int rc=chkValueWordTile(pos,Peswn);                         //~1118R~//~1119M~
        if (Dump.Y) Dump.println("RAUtils.chkValueWordTile eswn="+Peswn+",Tile="+Ptd.toString());//~1118R~//~1119I~
        return rc;                                                 //~1119M~
    }                                                              //~1118I~//~1119M~
    //*********************************************************************//~1220R~
    //*rc:dragon:2, wind=3, round=2, round+wind=5, get han by ctr/2//~1220I~
    //*********************************************************************//~1220I~
    public static int chkValueWordTile(int Ppos,int Peswn)         //~1118R~//~1119M~
    {                                                              //~1118I~//~1119M~
    	int rc=0;                                                  //~1118I~//~1119M~
        if (Ppos>=OFFS_WORDTILE_DRAGON)                            //~1118I~//~1119M~
            rc=2;                                                  //~1118I~//~1119M~//~1215R~
        else                                                       //~1118I~//~1119M~
        if (Ppos>=OFFS_WORDTILE)                                   //~1129I~
        {                                                          //~1118I~//~1119M~
        	int windPos=Ppos-OFFS_WORDTILE;                        //~1125I~
            if (windPos==AG.aRoundStat.windRound)    //wind of round             //~1118I~//~1119M~//~1125R~
    	        rc+=2;                                             //~1118I~//~1119M~//~1215R~
            if (windPos==Peswn)          //wind of you                //~1118I~//~1119M~//~1125R~
                rc+=3;                                             //~1118I~//~1119M~//~1215R~
        	if (Dump.Y) Dump.println("RAUtils.chkValueWordTile tile wind="+windPos+",round="+AG.aRoundStat.windRound);//~vab3R~
        }                                                          //~1118I~//~1119M~
        if (Dump.Y) Dump.println("RAUtils.chkValueWordTile Ppos="+Ppos+",eswn="+Peswn+",rc="+rc);//~1118R~//~1119I~//~1125R~
        return rc;                                                  //~1118I~//~1119M~
    }                                                              //~1118I~//~1119M~
//    //*********************************************************  //~1129R~
//    public static int chkValueWordTileInHand(int Peswn,int[] PitsHand)//~1129R~
//    {                                                            //~1129R~
//        int rc=0;                                                //~1129R~
//        for (int ii=OFFS_WORDTILE;ii<CTR_TILETYPE;ii++)          //~1129R~
//        {                                                        //~1129R~
//            if (PitsHand[ii]>=PAIRCTR && chkValueWordTile(ii,Peswn)>0)//~1129R~
//                rc++;                                            //~1129R~
//        }                                                        //~1129R~
//        if (Dump.Y) Dump.println("RAUtils.chkValueWordTileInHand eswn="+Peswn+",rc="+rc);//~1129R~
//        return rc;                                               //~1129R~
//    }                                                            //~1129R~
    //*********************************************************    //~1118I~//~1119M~
    public static int getShantenAdd(int[] PitsHand,int PctrHand,int Ppos,int Pctr)//~1118I~//~1119I~
    {                                                              //~1118I~//~1119M~
        int shanten=MAX_SHANTEN;                                   //~1118I~//~1119M~
        if (Dump.Y) Dump.println("RAUtils.getShantenAdd ctrHand="+PctrHand+",pos="+Ppos+",Pctr="+Pctr+",PitsHand["+Ppos+"]="+PitsHand[Ppos]);//~1213R~//~1306R~
        PitsHand[Ppos]+=Pctr;                                      //~1118I~//~1119M~
        if (PitsHand[Ppos]>=0 && PitsHand[Ppos]<=PIECE_DUPCTR)      //~1118I~//~1119M~//~1124R~
        {                                                          //~1118I~//~1119M~
        	shanten=AG.aShanten.getShantenMin(PitsHand,PctrHand+Pctr);//~1118I~//~1119M~
        }                                                          //~1118I~//~1119M~
        else                                                       //~1213I~
        	if (Dump.Y) Dump.println("RAUtils.getShantenAdd @@@@itsHandErr");//~1213I~
                                                                   //~1213I~
        PitsHand[Ppos]-=Pctr;                                      //~1118I~//~1119M~
        if (Dump.Y) Dump.println("RAUtils.getShantenAdd shanten="+shanten);//~1118I~//~1119I~
        return shanten;                                            //~1118I~//~1119M~
    }                                                              //~1118I~//~1119M~
    //*********************************************************    //~1128I~
    //**for call, no need to chk 7pair,13orphan                    //~1128I~
    //*get shanten in case of tile added/removed after chk discardable//~1220I~
    //*********************************************************    //~1128I~
    public static int getShantenAddCall(int[] PitsHand,int PctrHand,int Ppos,int Pctr)//~1128I~
    {                                                              //~1128I~
        int shanten=MAX_SHANTEN;                                   //~1128I~
        if (Dump.Y) Dump.println("RAUtils.getShantenAdd ctrHand="+PctrHand+",pos="+Ppos+",Pctr="+Pctr+",PitsHand[Ppos]="+PitsHand[Ppos]);//~1128I~//~1213R~
        PitsHand[Ppos]+=Pctr;                                      //~1128I~
        if (PitsHand[Ppos]>=0 && PitsHand[Ppos]<=PIECE_DUPCTR)     //~1128I~
        {                                                          //~1128I~
//      	shanten=AG.aShanten.getShantenMin(PitsHand,PctrHand+Pctr);//~1128I~
        	shanten=AG.aShanten.getShantenNewNormal(PitsHand,PctrHand+Pctr);//~1128I~
        }                                                          //~1128I~
        else                                                       //~1213I~
        	if (Dump.Y) Dump.println("RAUtils.getShantenAdd @@@@itsHandErr");//~1213I~
                                                                   //~1213I~
        PitsHand[Ppos]-=Pctr;                                      //~1128I~
        if (Dump.Y) Dump.println("RAUtils.getShantenAdd shanten="+shanten);//~1128I~
        return shanten;                                            //~1128I~
    }                                                              //~1128I~
    //*********************************************************    //~1119I~
    //*select tile non red5;                                       //~1119I~
    //*********************************************************    //~1119I~
    public static TileData selectTileInHand(int Peswn,int Ppos)    //~1119R~
    {                                                              //~1119I~
        int[] itsHand=AG.aRoundStat.getItsHandEswn(Peswn);         //~1119I~
//        int[] itsHandRed=AG.aRoundStat.getItsHandRedEswn(Peswn);   //~1119I~//~1129R~
        int playerDiscard= Accounts.eswnToPlayer(Peswn);            //~1119I~
        TileData[] tdsHand=AG.aPlayers.getHands(playerDiscard);           //~1119I~
//        TileData tdDiscard=selectTileInHand(Ppos,itsHand,itsHandRed,tdsHand);//~1119I~//~1129R~
        TileData tdDiscard=selectTileInHand(Ppos,itsHand,tdsHand); //~1129I~
        return tdDiscard;                                          //~1119I~
    }                                                              //~1119I~
    //*********************************************************    //~vab3I~
    //*select tile red5 for make Chii                              //~vab3I~
    //*********************************************************    //~vab3I~
    public static TileData selectTileInHandRed5(int Peswn,int Ppos)//~vab3I~
    {                                                              //~vab3I~
    	swSelectRed5=true;                                         //~vab3I~
    	TileData td=selectTileInHand(Peswn,Ppos);                  //~vab3I~
    	swSelectRed5=false;                                        //~vab3I~
        if (Dump.Y) Dump.println("RAUtils.selectTileInHandRed5 td="+Utils.toString(td));//~vab3I~
        return td;                                                 //~vab3I~
    }                                                              //~vab3I~
    //*********************************************************    //~1119I~
    //*select tile non red5;                                       //~1119I~
    //*********************************************************    //~1119I~
//    public static TileData selectTileInHand(int Ppos,int[] PitsHand,int[] PitsHandRed,TileData[] PtdsHand)//~1119R~//~1129R~
    public static TileData selectTileInHand(int Ppos,int[] PitsHand,TileData[] PtdsHand)//~1129I~
    {                                                              //~1119I~
    	TileData tdDiscard=null;                                   //~1119I~
        TileData tdRed5=null;                                      //~1129I~
        //******************************                           //~1129I~
        int[] itsHand=PitsHand;                                    //~1119I~
//        int[] itsHandRed=PitsHandRed;                              //~1119I~//~1129R~
        TileData[] tdsHand=PtdsHand;                               //~1119I~
//        boolean swRed5=itsHand[Ppos]==itsHandRed[Ppos]; //all tile is red5//~1119I~//~1129R~
		if (Dump.Y) Dump.println("RAUtils.selectTileInHand swSelectRed5="+swSelectRed5+",pos="+Ppos+",itsHand="+Utils.toString(itsHand,9));//~1119I~//~vab3R~
//        if (Dump.Y) Dump.println("RAUtils.selectTileInHand swRed5="+swRed5+",itsHandRed="+Utils.toString(itsHandRed,9));//~1119I~//~1129R~
        int type=Ppos/CTR_NUMBER_TILE;                             //~1119I~
        int num=Ppos%CTR_NUMBER_TILE;                              //~1119I~
        for (TileData td:tdsHand)                                  //~1119I~
        {                                                          //~1119I~
        	if (td.type==type && td.number==num)                   //~1119I~
            {                                                      //~1119I~
//                if (!(swRed5 ^ td.isRed5())) //same                //~1119I~//~1129R~
//                {                                                  //~1119I~//~1129R~
//                    tdDiscard=td;                                  //~1119I~//~1129R~
//                    break;                                         //~1119I~//~1129R~
//                }                                                  //~1119I~//~1129R~
                if (td.isRed5())                                   //~1129I~
                {                                                  //~1129I~
                    tdRed5=td;                                     //~1129I~
                }                                                  //~1129I~
                else                                               //~1129I~
                {                                                  //~1129I~
                    tdDiscard=td;                                  //~1129I~
                    break;                                         //~1129I~
                }                                                  //~1129I~
            }                                                      //~1119I~
        }                                                          //~1119I~
        if (swSelectRed5 && tdRed5!=null)                          //~vab3I~
        	tdDiscard=tdRed5;                                      //~vab3I~
        else                                                       //~vab3I~
        if (tdDiscard==null) //select red5 if no red5 found        //~1129I~
        	tdDiscard=tdRed5;                                      //~1129I~
        if (Dump.Y) Dump.println("RAUtils.selectTileInHand tdDiscard="+Utils.toString(tdDiscard));//~1119I~//~1124R~
        return tdDiscard;                                          //~1119I~
    }                                                              //~1119I~
    //*********************************************************    //~1120I~
    //*chk remaining tile ctr (except in-hand and exposed)         //~1124R~
    //*********************************************************    //~1120I~
    public static boolean isEmpty(int[] PitsHand,int Ppos)                 //~1120I~
    {                                                              //~1120I~
        boolean rc=AG.aRoundStat.itsExposed[Ppos]+PitsHand[Ppos]>=PIECE_DUPCTR;//~1120I~
		if (Dump.Y) Dump.println("RAUtils.isEmpty rc="+rc+",pos="+Ppos+",exposed="+AG.aRoundStat.itsExposed[Ppos]+",hand="+PitsHand[Ppos]);//~1120I~//~1222R~
        return rc;                                                 //~1120I~
    }                                                              //~1120I~
    //*********************************************************    //~1222I~
    //*chk remaining tile ctr (except in-hand and exposed)         //~1222I~
    //*********************************************************    //~1222I~
    public static boolean isEmpty(int Peswn,int Ppos)              //~1222I~
    {                                                              //~1222I~
        boolean rc=AG.aRoundStat.itsExposed[Ppos]+AG.aRoundStat.getItsHandEswn(Peswn)[Ppos]>=PIECE_DUPCTR;//~1222I~
		if (Dump.Y) Dump.println("RAUtils.isEmpty rc="+rc+",eswn="+Peswn+",pos="+Ppos+",exposed="+AG.aRoundStat.itsExposed[Ppos]+",hand="+AG.aRoundStat.getItsHandEswn(Peswn)[Ppos]);//~1222I~
        return rc;                                                 //~1222I~
    }                                                              //~1222I~
    //***********************************************************************//~1117I~//~1120M~
    public static int getCtrRemain()                                      //~1117I~//~1120I~
    {                                                              //~1117I~//~1120M~
        int ctr=PIECE_TILECTR-AG.aRoundStat.ctrTakenAll-TILECTR_KEEPLEFT-HANDCTR*PLAYERS;     //~1117I~//~1120I~//~1310R~
		if (Dump.Y) Dump.println("RAUtils.getCtrRemain="+ctr);     //~1206I~
        return ctr;                                                //~1117I~//~1120M~
    }                                                              //~1117I~//~1120M~
    //***********************************************************************//~1116M~//~1120M~
    //*genbutsu chk                                                //~1213I~
    //***********************************************************************//~1213I~
    public static boolean isFuriten(int Peswn,int Ppos)                  //~1116M~//~1120R~
    {                                                              //~1116M~//~1120M~
        boolean rc=AG.aRoundStat.isFuriten(Peswn,Ppos);            //~1120R~
        if (Dump.Y) Dump.println("RAUtils.isFuriten eswn="+Peswn+",pos="+Ppos+",rc="+rc);//~1116M~//~1120I~
        return rc;                                                 //~1116M~//~1120M~
    }                                                              //~1116M~//~1120M~
    //***********************************************************************//~1130I~
    public static boolean isAllInHand(int Peswn)                   //~1130I~
    {                                                              //~1130I~
        boolean rc=AG.aRoundStat.RSP[Peswn].swAllInHand;           //~1130I~
        if (Dump.Y) Dump.println("RAUtils.isAllInHand eswn="+Peswn+",rc="+rc);//~1130I~
        return rc;                                                 //~1130I~
    }                                                              //~1130I~
    //***********************************************************************//~1220I~
    public static boolean isMatchSameColor(boolean PswWord,int Pintent,int Ptype)//~1220I~
    {                                                              //~1220I~
        boolean rc=false;                                          //~1220I~
        switch(Ptype)                                              //~1220I~
        {                                                          //~1220I~
        case TT_MAN:                                               //~1220I~
        	rc=(Pintent & INTENT_SAMECOLOR_MAN)!=0;                 //~1220I~
            break;                                                 //~1220I~
        case TT_PIN:                                               //~1220I~
        	rc=(Pintent & INTENT_SAMECOLOR_PIN)!=0;                 //~1220I~
            break;                                                 //~1220I~
        case TT_SOU:                                               //~1220I~
        	rc=(Pintent & INTENT_SAMECOLOR_SOU)!=0;                 //~1220I~
            break;                                                 //~1220I~
        default:                                                   //~1220I~
        	rc=PswWord;                                            //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RAUtils.isMatchSameColor rc="+rc+",intent="+Pintent+",type="+Ptype);//~1220I~//~1427R~
        return rc;                                                 //~1220I~
    }                                                              //~1220I~
    //***********************************************************************//+vaj2I~
    public static boolean isMatchSameColorPos(boolean PswWord/*rc for wordTile*/,int Pintent,int Ppos)//+vaj2I~
    {                                                              //+vaj2I~
        int type=Ppos/CTR_NUMBER_TILE;                             //+vaj2I~
        boolean rc=isMatchSameColor(PswWord,Pintent,type);          //+vaj2I~
        if (Dump.Y) Dump.println("RAUtils.isMatchSameColor rc="+rc+",intent="+Pintent+",pos="+Ppos);//+vaj2I~
        return rc;                                                 //+vaj2I~
    }                                                              //+vaj2I~
    //*********************************************************    //~vaaEI~
    //*single tile (ctr=1 & both side=0)                           //~vaaEI~
    //*********************************************************    //~vaaEI~
    public static boolean isSingle(int[] PitsHand,int Ppos,int PctrAdd)//~vaaER~
    {                                                              //~vaaEI~
        if (Dump.Y) Dump.println("RAUtils.isSingle pos="+Ppos+",ctrAdd="+PctrAdd+",itsHand="+Utils.toString(PitsHand,9));//~vaaER~
    	boolean rc;                                                //~vaaEI~
        int ctr=PitsHand[Ppos]+PctrAdd;                            //~vaaER~
        if (ctr!=1)                                                //~vaaEI~
        {                                                          //~vaaEI~
	        if (Dump.Y) Dump.println("RAUtils.isSingle return FALSE by ctr="+ctr+"!=1");//~vaaEI~
        	return false;                                          //~vaaEI~
        }                                                          //~vaaEI~
    	if (Ppos>=OFFS_WORDTILE)                                   //~vaaEI~
        {                                                          //~vaaEI~
	        if (Dump.Y) Dump.println("RAUtils.isSingle return TRUE by WORD tile");//~vaaEI~
        	return true;                                           //~vaaEI~
        }                                                          //~vaaEI~
        int num=Ppos%CTR_NUMBER_TILE;                              //~vaaER~
        switch (num)                                               //~vaaEI~
        {                                                          //~vaaEI~
        case TN1:                                                  //~vaaEI~
        	rc=PitsHand[Ppos+1]==0;                                //~vaaEI~
            break;                                                 //~vaaEI~
        case TN9:                                                  //~vaaEI~
        	rc=PitsHand[Ppos-1]==0;                                //~vaaEI~
            break;                                                 //~vaaEI~
        default:                                                   //~vaaEI~
        	rc=PitsHand[Ppos-1]==0 && PitsHand[Ppos+1]==0;         //~vaaEI~
        }                                                          //~vaaEI~
        if (Dump.Y) Dump.println("RAUtils.isSingle rc="+rc+",pos="+Ppos);//~vaaEI~
        return rc;                                                 //~vaaEI~
    }                                                              //~vaaEI~
    //*********************************************************    //~vag7R~
    //*count seq meld not including pair or triplet                //~vag7R~//~vag8R~
    //*********************************************************    //~vag7R~
    public static int getCtrSeqMeld(int[] PitsHand)                //~vag7R~
    {                                                              //~vag7R~
        if (Dump.Y) Dump.println("RAUtils.getCtrSeqMeld itsHand="+Utils.toString(PitsHand,9));//~vag7R~
    	int ctrSeq=0;                                              //~vag7R~
        for (int pos=0;pos<OFFS_WORDTILE;pos+=CTR_NUMBER_TILE)     //~vag7R~
        {                                                          //~vag7R~
        	int ctrCont=0;                                         //~vag7R~
        	for (int posNum=pos,ii=TN1;ii<=TN9;posNum++,ii++)        //~vag7R~//~vag8R~
            {                                                      //~vag7R~
            	if (PitsHand[posNum]==1)                           //~vag7R~//~vag8R~
                {                                                  //~vag7R~
                	ctrCont++;                                     //~vag7R~
                    if (ctrCont==PAIRCTR)                          //~vag7R~
                    {                                              //~vag7R~
                    	ctrSeq++;	                               //~vag7R~
                        ctrCont=0;                                 //~vag7R~
                    }                                              //~vag7R~
                }                                                  //~vag7R~
                else                                               //~vag7R~
                	ctrCont=0;                                     //~vag7R~
            }                                                      //~vag7R~
        }                                                          //~vag7R~
        if (Dump.Y) Dump.println("RAUtils.getCtrSeqMeld rc="+ctrSeq);//~vag7R~
        return ctrSeq;                                             //~vag7R~
    }                                                              //~vag7R~
    //*********************************************************    //~vag8I~
    //*count other color meld for samecolor intent                 //~vag8I~
    //*********************************************************    //~vag8I~
    public static int getCtrOtherColorMeld(int Ptype,int[] PitsHand)//~vag8I~
    {                                                              //~vag8I~
        if (Dump.Y) Dump.println("RAUtils.getCtrOtherColorMeld type="+Ptype+",itsHand="+Utils.toString(PitsHand,9));//~vag8I~
    	int ctrSeq=0;                                              //~vag8I~
        int ctrTriplet=0;                                          //~vag8I~
        for (int pos=0,color=0;pos<OFFS_WORDTILE;pos+=CTR_NUMBER_TILE,color++)//~vag8R~
        {                                                          //~vag8I~
        	if (color==Ptype)                                      //~vag8I~
            	continue;                                          //~vag8I~
        	int ctrCont=0;                                         //~vag8I~
        	for (int posNum=pos,ii=TN1;ii<=TN9;posNum++,ii++)      //~vag8I~
            {                                                      //~vag8I~
            	if (PitsHand[posNum]==1)                           //~vag8R~
                {                                                  //~vag8I~
                	ctrCont++;                                     //~vag8I~
                    if (ctrCont==PAIRCTR)                          //~vag8I~
                    {                                              //~vag8I~
                    	ctrSeq++;                                  //~vag8I~
                        ctrCont=0;                                 //~vag8I~
                    }                                              //~vag8I~
                }                                                  //~vag8I~
                else                                               //~vag8I~
                {                                                  //~vag8I~
                	ctrCont=0;                                     //~vag8M~
	            	if (PitsHand[posNum]>=PAIRCTR)                 //~vag8I~
    	            	ctrTriplet++;                              //~vag8I~
                }                                                  //~vag8I~
            }                                                      //~vag8I~
        }                                                          //~vag8I~
        int rc=ctrSeq+ctrTriplet;                                  //~vag8I~
        if (Dump.Y) Dump.println("RAUtils.getCtrOtherColorMeld rc="+rc+",ctrSeq="+ctrSeq+",ctrTriplet="+ctrTriplet);//~vag8R~
        return rc;                                                 //~vag8I~
    }                                                              //~vag8I~
}//class RAUtils                                                   //~vag7R~
