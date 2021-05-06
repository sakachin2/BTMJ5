//*CID://+va8xR~: update#= 554;                                    //~va8xR~
//**********************************************************************//~v101I~
//2021/05/01 va8x (Test)specify robot discard tile                 //~va8xI~
//v@@5 20190126 player means position on the device                //~v@@5I~
//reset tile to new game                                           //~v@@@R~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;                                    //~v@@@I~
import com.btmtest.dialog.RuleSetting;                             //~9412I~
                                                                   //~9412I~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GConst.*;                                  //~v@@@I~//~9412M~
import static com.btmtest.game.RA.RAConst.*;
import static com.btmtest.game.gv.Pieces.*;                        //~v@@@I~//~9412M~
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.gv.Stock.*;//~9412M~


import java.util.ArrayList;

public class Tiles                                 //~v@@@R~
{                                                                  //~0914I~
    public static final int TT_MAN = 0; //TileType                 //~v@@@R~
    public static final int TT_PIN = 1;                            //~v@@@R~
    public static final int TT_SOU = 2;                            //~v@@@R~
    public static final int TT_JI = 3;                            //~v@@@R~
    //  public  static final int TT_4ESWN=3;                           //~v@@@I~
    public  static final int TT_4ESWN_E = 0;                       //~v@@@R~
    public  static final int TT_4ESWN_S = 1;                       //~v@@@R~
    public  static final int TT_4ESWN_W = 2;                       //~v@@@R~
    public  static final int TT_4ESWN_N = 3;                       //~v@@@R~
    public  static final int TT_4ESWN_CTR = 4;                     //~v@@@R~
    //  public  static final int TT_3WGR=4;                            //~v@@@R~
    public static final int TT_3WGR_W = 0;                         //~v@@@R~
    public static final int TT_3WGR_G = 1;                         //~v@@@R~
    public static final int TT_3WGR_R = 2;                         //~v@@@R~
    public static final int TT_3WGR_CTR = 3;                       //~v@@@R~
    //  private static final int TT_CTR=5;                             //~v@@@R~
    private static final int TT_CTR = 4;                             //~v@@@I~
    //~v@@@I~
    private TileData[] shuffledTileData, baseTileData;              //~v@@@R~
    private TileData[] tileData;                                   //~v@@@I~
    private int[] typeIndex;                                       //~v@@@I~
    private boolean swUseRedTile;                                          //~v@@@I~
    private int posCut;     //cut position when deal               //~v@@@I~
    public int ctrKan;     //cut position when deal                //~v@@@R~
    private int posNextTile,posCurrentTile;                        //~v@@@I~
    private boolean swPendingOpen;                                 //~v@@5I~
    //*********************************************************************//~v@@@I~
    public Tiles()                                                 //~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("Tiles Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~//~v@@@R~

    //*************************                                        //~v@@@I~
    public void init()                                                  //~v@@@I~
    {                                                              //~v@@@I~
        AG.aTiles = this;                                        //~v@@@R~
        swUseRedTile = AG.aRule.swUseRedTile;                        //~v@@@I~
        setupBase();        //tbl for all piecetype                //~v@@@I~
    	posNextTile=TILECTR_KEEPLEFT+PLAYERS*HANDCTR;              //~v@@@R~
        if (Dump.Y) Dump.println("Tiles.init posNextTile="+posNextTile);//~9225I~
    }                                                              //~v@@@I~
    //*************************                                    //~9503I~
    public void newGame()                                          //~9503I~
    {                                                              //~9503I~
//  private TileData[] shuffledTileData, baseTileData;             //~9503I~
//  private TileData[] tileData;                                   //~9503I~
//  private int[] typeIndex;                                       //~9503I~
//  private boolean swUseRedTile;                                  //~9503I~
//  private int posCut;     //cut position when deal               //~9503I~
    	ctrKan=0;                                                  //~9503I~
    	posNextTile=TILECTR_KEEPLEFT+PLAYERS*HANDCTR;              //~9503I~
    	posCurrentTile=0;                                          //~9503R~
    	swPendingOpen=false;                                       //~9503I~
    }                                                              //~9503I~
    //*************************                                    //~v@@@I~
    public static boolean isTakenStatus(int Pctr)                  //~v@@@I~
    {                                                              //~v@@@I~
        boolean rc=Pctr%PAIRCTR!=PAIRCTR_REMAIN;	//taken status   3*x+1//~v@@@I~
        if (Dump.Y) Dump.println("Tiles.isTakenStatus ctr="+Pctr+",rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************                                        //~v@@@I~
    public TileData[] getNewAllType()                              //~v@@@R~
    {                                                              //~v@@@I~
        TileData[] tds = new TileData[PIECE_TYPECTR];   //34         //~v@@@R~
        return tds;                                                //~v@@@I~
    }                                                              //~v@@@I~

    //*************************                                        //~v@@@I~
    public TileData[] getNewAllTile()                              //~v@@@R~
    {                                                              //~v@@@I~
        TileData[] tds = new TileData[PIECE_TILECTR];      //*4         //~v@@@R~
        return tds;                                                //~v@@@I~
    }                                                              //~v@@@I~

    //********************************************************************                                        //~v@@@I~//~9C01R~
    //*setup all piece type(3*9+3+4=34)                            //~9C01I~
    //********************************************************************//~9C01I~
    public void setupBase()                                        //~v@@@I~
    {                                                              //~v@@@I~
        TileData[] tds = getNewAllType();     //array[34]                       //~v@@@R~//~9C01R~
        baseTileData = tds;                                          //~v@@@I~
        int[] idx = new int[TT_CTR];                                 //~v@@@I~
        typeIndex = idx;                                             //~v@@@I~
        TileData td;                                               //~v@@@I~
        int ctr = 0;                                                 //~v@@@I~
        for (int ii = 0; ii < PIECE_NUMBERTYPECTR; ii++)   //3           //~v@@@R~
        {                                                          //~v@@@I~
            idx[ii] = ctr;                                           //~v@@@I~
            for (int jj = 0; jj < PIECE_NUMBERCTR; jj++)     //9         //~v@@@R~
            {                                                      //~v@@@I~
//              td = new TileData(jj, ii/*man,pin,sou*/, (swUseRedTile && jj == PIECE_NUMBER_DORA - 1)/*dora*/);//~v@@@R~//~v@@5R~
                td = new TileData(ii/*man,pin,sou*/,jj, (swUseRedTile && jj == PIECE_NUMBER_DORA - 1)/*dora*/);//~v@@5I~
                tds[ctr++] = td;                                     //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
//      idx[TT_4ESWN]=ctr;                                         //~v@@@R~
        idx[TT_JI] = ctr;                                            //~v@@@I~
        for (int jj = 0; jj < TT_4ESWN_CTR + TT_3WGR_CTR; jj++)             //~v@@@R~
        {                                                          //~v@@@I~
//          td = new TileData(jj/*number*/, TT_JI, false/*dora*/);     //~v@@@R~//~v@@5R~
            td = new TileData(TT_JI,jj/*number*/, false/*dora*/);  //~v@@5I~
            tds[ctr++] = td;                                         //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.setupBase ="+TileData.toString(baseTileData));//~9C01I~
    }                                                              //~v@@@I~

    //**************************************************************** //~v@@@I~
    public void cutTiles(int Prolls)                               //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.cutTiles dice total=" + Prolls);//~v@@@I~
        posCut = Prolls;                                             //~v@@@I~
    }                                                              //~v@@@I~

//*************************                                        //~v@@@I~
    public TileData[] getShuffled()                                           //~v@@@I~
    {                                                              //~v@@@I~
        return shuffledTileData;                                   //~v@@@I~
    }                                                              //~v@@@I~
//*************************                                        //~v@@5I~
//*client only                                                     //~v@@5I~
//*************************                                        //~v@@5I~
    public void setShuffled(TileData[] Ptds)                       //~v@@5I~
    {                                                              //~v@@5I~
        shuffledTileData=Ptds;                                     //~v@@5I~
        if (Dump.Y) Dump.println("setShuffled tds:"+TileData.toString(Ptds));//~v@@5I~
    }                                                              //~v@@5I~
    //*************************                                        //~v@@@I~
    public ArrayList<TileData> deepCopyToArrayList()                //~v@@@I~
    {                                                              //~v@@@I~
        TileData[] src = baseTileData.clone();                       //~v@@@I~
        ArrayList<TileData> al = new ArrayList<TileData>();          //~v@@@I~
        for (int ii = 0; ii < src.length; ii++)                          //~v@@@I~
        {                                                          //~v@@@I~
            al.add(TileData.newInstance(src[ii]));                 //~v@@@I~
        }                                                          //~v@@@I~
        return al;                                                 //~v@@@I~
    }                                                              //~v@@@I~

    //*************************************************************************//~v@@@I~
    //*place red5 and other randomely for each numer type suit     //~9C01I~
    //*************************************************************************//~9C01I~
    public boolean[][] shuffleRed5()                               //~v@@@I~
    {                                                              //~v@@@I~
        boolean[][] list = new boolean[PIECE_NUMBERTYPECTR][PIECE_DUPCTR];//[3][4] //~v@@@I~//~9C01R~
        if (!swUseRedTile)                                         //~9C01I~
        {                                                          //~9C01I~
        	if (Dump.Y) Dump.println("Tiles.shuffleRed5 swUseRedTile:false list="+Utils.toString(list));//~9C01I~
        	return list;                                                //~9C01I~
        }                                                          //~9C01I~
        for (int ii = 0; ii < PIECE_NUMBERTYPECTR; ii++)                 //~v@@@I~
        {                                                          //~v@@@I~
            int redctr = 0;                                          //~v@@@I~
            int loopctr = 0;                                             //~v@@@I~
            while (true)                                          //~v@@@I~
            {                                                      //~v@@@I~
                int rand = Utils.getRandom(PIECE_DUPCTR);   //max 34 //~v@@@R~
            	if (Dump.Y) Dump.println("Tiles.shuffleRed5 rand="+rand);//~v@@@I~
                if (!list[ii][rand])                               //~v@@@R~
                {                                                  //~v@@@R~
                    list[ii][rand] = true;                           //~v@@@R~
                    redctr++;                                      //~v@@@R~
                    if (redctr == PIECE_DUPCTR / 2)                    //~v@@@R~
                        break;                                     //~v@@@R~
                }                                                  //~v@@@R~
                loopctr++;                                         //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.shuffleRed5 list="+Utils.toString(list));//~9C01I~
        return list;                                               //~v@@@I~
    }                                                              //~v@@@I~

    //*****************************************************************//~v@@@R~
    //*create sorted deepcopy by 4                                 //~v@@@I~
    //*****************************************************************//~v@@@I~
    public void shuffle()                                          //~v@@@I~
    {                                                              //~v@@@M~
        boolean[][] red5List = shuffleRed5();                        //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.shuffle testOption 1="+Integer.toHexString(TestOption.option)+",2="+Integer.toHexString(TestOption.option2)+",3="+Integer.toHexString(TestOption.option3));//~1425R~
        if ((TestOption.option & TestOption.TO_KAN_ADDDEAL)!=0)               //~v@@5I~
        {	shuffleKanAdd(); return;}                               //~v@@5I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_PON)!=0)      //~1401I~//~1402R~
        {	shufflePonTest(); return;}                             //~1401I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_CHII)!=0)    //~1403I~
        {	shuffleChiiTest(); return;}                            //~1403I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_PONCHII)!=0) //~1405I~
        {	shufflePonChiiTest(1); return;}                         //~1405I~//~1407R~
        if ((TestOption.option2 & TestOption.TO2_DEAL_DOUBLERON)!=0)//~1407I~
        {	shufflePonChiiTest(2); return;}                        //~1407I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_KANAFTERREACH)!=0)//~1407I~
        {	shufflePonChiiTest(3); return;}                        //~1407I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_RINSHANRON)!=0)//~1411I~
        {	shufflePonChiiTest(4); return;}                        //~1411I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_RONTAKEN1ST)!=0)//~1414I~
        {	shufflePonChiiTest(5); return;}                        //~1414I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_RONTAKEN)!=0)//~1412I~//~1414M~
        {	shufflePonChiiTest(6); return;}                        //~1412I~//~1414I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_RONTAKEN_DOUBLEREACH)!=0)//~1420I~
        {	shufflePonChiiTest(61); return;}                       //~1420I~
        if ((TestOption.option3 & TestOption.TO3_RON_RIVER)!=0)    //~1414I~
        {	shufflePonChiiTest(7); return;}                        //~1414I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_MULTIWAITRON)!=0)//~1418I~
        {	shufflePonChiiTest(8); return;}                        //~1418I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_MULTIWAITRONOK)!=0)//~1418I~
        {	shufflePonChiiTest(9); return;}                        //~1418I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_DOUBLERUN_FIX)!=0)//~1419I~
        {	shufflePonChiiTest(10); return;}                       //~1419I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_DOUBLERUN_NOTFIX)!=0)//~1419I~
//      {	shufflePonChiiTest(11); return;}                       //~1419I~//~1422R~
        {	shufflePonChiiTest(111); return;}                      //~1422I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_DOUBLERUN_NOTFIXNG)!=0)//~1419I~
        {	shufflePonChiiTest(12); return;}                       //~1419I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_DOUBLERUN_CHII)!=0)//~1419I~
        {	shufflePonChiiTest(13); return;}                       //~1419I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_3SHIKI_CHII)!=0)//~1420I~
        {	shufflePonChiiTest(14); return;}                       //~1420I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_3SHIKI_CHII_TAKE)!=0)//~1425I~
        {	shufflePonChiiTest(141); return;}                      //~1425I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_3SHIKI_CHIING)!=0)//~1420I~
        {	shufflePonChiiTest(15); return;}                       //~1420I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_13NOPAIR)!=0)//~1412I~
        {	shuffleNoPairTest(1); return;}                         //~1412R~
        if ((TestOption.option3 & TestOption.TO3_DEAL_14NOPAIR)!=0)//~1412I~
        {	shuffleNoPairTest(2); return;}                         //~1412I~
        if ((TestOption.option3 & TestOption.TO3_INTENT_3DRAGON)!=0) //~1415I~
        {	shuffleNoPairTest(3); return;}                         //~1415I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_MULTIWAITWGR)!=0)//~1419I~
        {	shuffleNoPairTest(4); return;}                         //~1419I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_SAMECOLOR)!=0)//~1427I~
        {	shuffleNoPairTest(411); return;}                       //~1427I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_ATODUKE_TAKE_SAMECOLOR)!=0)//~1501I~
        {	shuffleNoPairTest(412); return;}                       //~1501I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_ATODUKE_TAKE_YAKUHAI)!=0)//~1501I~
        {	shuffleNoPairTest(413); return;}                       //~1501I~
        if ((TestOption.option3 & TestOption.TO3_DEAL_ANKAN_FIX1)!=0)//~va8xI~
        {	shuffleNoPairTest(414); return;}                       //~va8xI~
        if ((TestOption.option3 & TestOption.TO3_DEAL_SAKIDUKE_2HAN_TAKE)!=0)//~va8xI~
        {	shuffleNoPairTest(415); return;}                       //~va8xI~
        if ((TestOption.option2 & TestOption.TO2_ANKAN_DEAL)!=0)   //~0406I~//~0407R~
        {	shuffleMinkanTest(); return;}                          //~0406I~
        if ((TestOption.option2 & TestOption.TO2_CHANKAN_DEAL)!=0) //~0407I~
        {	shuffleChankanTest(); return;}                         //~0407I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_MULTIRON)!=0)     //~1328I~
        {	shuffleMultiRonTest(true); return;}                    //~1328I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_SINGLERON)!=0)    //~1328I~
        {	shuffleMultiRonTest(false); return;}                   //~1328I~
        synchronized (baseTileData)                                 //~v@@@I~
        {                                                          //~v@@@I~
            ArrayList<TileData> al = deepCopyToArrayList();	//copy of baseTileData(34 entry)           //~v@@@R~//~9C01R~
            TileData[] out = getNewAllTile();                        //~v@@@R~
            int outctr = 0;                                          //~v@@@R~
            int typectr = PIECE_TYPECTR;                             //~v@@@I~
//          for (int ii = 0; ii < PIECE_TILECTR; ii++)                   //~v@@@R~//~1315R~
            for (;;)                                               //~1315I~
            {                                                      //~v@@@R~
                int jj = Utils.getRandom(typectr);   //max 34-1        //~v@@@R~//~v@@5R~//~9C01R~
                if (Dump.Y) Dump.println("Tiles.shuffle rand="+jj);//~v@@@I~//~v@@5R~
                TileData td = al.get(jj);                            //~v@@@R~
                td.ctrRemain--;                                    //~v@@@R~
                if (td.ctrRemain >= 0)                               //~v@@@R~//~v@@5R~
                {                                                  //~v@@@R~//~v@@5R~
                    TileData tdnew = TileData.newInstance(td);              //~v@@@I~//~9C01R~
                    if ((td.flag & TDF_RED5) != 0)	//on when swUseRedTile                        //~v@@@I~//~v@@5R~//~9C01R~
                    {                                              //~v@@@I~//~v@@5R~
                        tdnew.setRed5(red5List[td.type][td.ctrRemain]);//~v@@@R~//~v@@5R~
                        if (Dump.Y) Dump.println("Tiles.shuffle red5 type=" + td.type + ",remain=" + td.ctrRemain + ",flag=" + td.flag);//~v@@5R~
                    }                                              //~v@@@I~//~v@@5R~
                    out[outctr++] = tdnew;                         //~v@@@R~//~v@@5R~
                }                                                  //~v@@@R~//~v@@5R~
                else                                               //~v@@@R~//~v@@5R~
                {                                                  //~v@@@R~//~v@@5R~
                    if (Dump.Y)  Dump.println("Tiles.shuffle remove typectr=" + typectr + ",outctr=" + outctr);//~v@@5R~
                    al.remove(jj);                                 //~v@@@R~//~v@@5R~
                    typectr--;                                     //~v@@@R~//~v@@5R~
//                  ii--;                                          //~v@@@R~//~v@@5R~//~1315R~
                    if (typectr==0)                                //~1315I~
                        break;                                     //~1315I~
                }                                                  //~v@@@R~//~v@@5R~
            }                                                      //~v@@@R~
	        if ((TestOption.option2 & TestOption.TO2_SETDORA)!=0)//~0A12I~//~0A14R~
    	    {                                                      //~0A12I~
        	    testSetDora(out);                                  //~0A12I~
       		}                                                      //~0A12I~
                                                                   //~0A12I~
            shuffledTileData = out;                                  //~v@@@R~
        }//synch                                                   //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.shuffle "+TileData.toString(shuffledTileData));//~v@@5R~//~0A12R~
    }                                                              //~v@@@M~
    //*****************************************************************//~0A12I~
    //*for test                                                    //~0A12I~
    //*****************************************************************//~0A12I~
    private void testSetDora(TileData[] Pout)                           //~0A12I~
    {                                                              //~0A12I~
        if (Dump.Y) Dump.println("Tiles.testSetDora");              //~0A12I~
        int type,num;                                              //~0A12I~
        type=TestOption.testDoraUpType;                            //~0A12I~
        num=TestOption.testDoraUpNumber;                           //~0A12I~
        if (type>=0&&type<=3 && num>=0 && num<=8)                  //~0A12I~
        	Pout[DORA_TDPOS]=new TileData(type,num);               //~0A12R~
        type=TestOption.testDoraDownType;                          //~0A12I~
        num=TestOption.testDoraDownNumber;                         //~0A12I~
        if (type>=0&&type<=3 && num>=0 && num<=8)                  //~0A12I~
        	Pout[DORA_TDPOS-1]=new TileData(type,num);             //~0A12R~
                                                                   //~0A12I~
        type=TestOption.testKanUpType;                             //~0A12I~
        num=TestOption.testKanUpNumber;                            //~0A12R~
        if (type>=0&&type<=3 && num>=0 && num<=8)                  //~0A12I~
        	Pout[DORA_TDPOS-2]=new TileData(type,num);             //~0A12R~
        type=TestOption.testKanDownType;                           //~0A12I~
        num=TestOption.testKanDownNumber;                          //~0A12R~
        if (type>=0&&type<=3 && num>=0 && num<=8)                  //~0A12I~
        	Pout[DORA_TDPOS-3]=new TileData(type,num);             //~0A12R~
                                                                   //~0A12I~
        type=TestOption.testKanUpType2;                            //~0A12I~
        num=TestOption.testKanUpNumber2;                           //~0A12I~
        if (type>=0&&type<=3 && num>=0 && num<=8)                  //~0A12I~
        	Pout[DORA_TDPOS-4]=new TileData(type,num);             //~0A12I~
        type=TestOption.testKanDownType2;                          //~0A12I~
        num=TestOption.testKanDownNumber2;                         //~0A12I~
        if (type>=0&&type<=3 && num>=0 && num<=8)                  //~0A12I~
        	Pout[DORA_TDPOS-5]=new TileData(type,num);             //~0A12I~
    }                                                              //~0A12I~
    //*****************************************************************//~v@@5I~
    //*for test                                                    //~v@@5I~
    //*****************************************************************//~v@@5I~
    public void shuffleKanAdd()                                    //~v@@5I~
    {                                                              //~v@@5I~
            ArrayList<TileData> al = deepCopyToArrayList();        //~v@@5I~
            TileData[] out = getNewAllTile();                      //~v@@5I~
            int outctr = 0;                                        //~v@@5I~
            int typectr = 0;                                       //~v@@5R~
            int tc=PIECE_TYPECTR-1;                                //~v@@5I~
            TileData td = al.get(tc);                              //~v@@5R~
            td.ctrRemain--;                                        //~v@@5I~
            TileData tdnew = TileData.newInstance(td);                      //~v@@5I~//~9C01R~
            out[outctr++] = tdnew;                                 //~v@@5I~
            td = al.get(tc);                                       //~v@@5R~
            td.ctrRemain--;                                        //~v@@5I~
            tdnew = TileData.newInstance(td);                               //~v@@5I~//~9C01R~
            out[outctr++] = tdnew;                                 //~v@@5I~
            for (;;)                                               //~v@@5I~
            {                                                      //~v@@5I~
                for (int jj=0;jj<3;jj++)                           //~v@@5I~
                {                                                  //~v@@5I~
                    td = al.get(typectr);                 //~v@@5I~
                    td.ctrRemain--;                                //~v@@5I~
                    tdnew = TileData.newInstance(td);              //~v@@5I~//~9C01R~
                    if ((tdnew.flag & TDF_RED5) != 0)              //~v@@5I~
                    	if (tdnew.ctrRemain<2)                      //~v@@5I~
		                    tdnew.flag &= ~TDF_RED5;               //~v@@5I~
                                                                   //~v@@5I~
                    out[outctr++] = tdnew;                         //~v@@5I~
                    if (outctr>=14+13*4)                           //~v@@5I~
                        break;                                     //~v@@5I~
                }                                                  //~v@@5I~
                typectr++;                                         //~v@@5I~
                td = al.get(tc);                          //~v@@5I~
                td.ctrRemain--;                                    //~v@@5I~
                if (td.ctrRemain==0)                               //~v@@5I~
                	tc--;                                          //~v@@5I~
                TileData tdnew2 = TileData.newInstance(td);                 //~v@@5I~//~9C01R~
                if ((tdnew2.flag & TDF_RED5) != 0)                 //~v@@5I~
                {                                                  //~9C01I~
                    if (tdnew2.ctrRemain<2)                         //~v@@5I~
		                tdnew2.flag &= ~TDF_RED5;                  //~v@@5I~
                    if (Dump.Y) Dump.println("Tiles.shuffle red5 type=" + td.type + ",remain=" + td.ctrRemain + ",flag=" + td.flag);//~9C01I~
                }                                                  //~9C01I~
                out[outctr++] = tdnew2;                            //~v@@5I~
                if (outctr>=14+13*4)                               //~v@@5I~
                	break;                                         //~v@@5I~
            }                                                      //~v@@5I~
            typectr=0;                                             //~v@@5I~
            for (int ii = outctr; ii < PIECE_TILECTR; ii++)        //~v@@5R~
            {                                                      //~v@@5I~
                td = al.get(typectr);                     //~v@@5R~
                td.ctrRemain--;                                    //~v@@5I~
                tdnew = TileData.newInstance(td);                  //~v@@5R~//~9C01R~
                if ((tdnew.flag & TDF_RED5) != 0)                  //~v@@5I~
                    if (tdnew.ctrRemain<2)                          //~v@@5I~
		                tdnew.flag &= ~TDF_RED5;                   //~v@@5I~
                out[outctr++] = tdnew;                             //~v@@5I~
                if (td.ctrRemain==0)                               //~v@@5I~
                	typectr++;                                     //~v@@5I~
            }                                                      //~v@@5I~
//          out[12]=new TileData(1,4);                             //~v@@5R~//~0405R~
//          out[13]=new TileData(1,2);                             //~v@@5R~//~0405R~
            shuffledTileData = out;                                //~v@@5I~
        if (Dump.Y) Dump.println("shuffleKanAdd "+TileData.toString(shuffledTileData));//~v@@5R~
    }                                                              //~v@@5I~
    //*****************************************************************//~0406I~
    //*for test Minkan                                             //~0406I~
    //*****************************************************************//~0406I~
    public void shuffleMinkanTest()                                //~0406I~
    {                                                              //~0406I~
            ArrayList<TileData> al = deepCopyToArrayList();        //~0406I~
            TileData[] out = getNewAllTile();                      //~0406I~
            int outctr = 0;                                        //~0406I~
            int typectr = 0;                                       //~0406I~
            int tc=PIECE_TYPECTR-1;
            TileData td,tdnew;//~0406I~
        //*wanpai 14                                               //~0406I~
            for (;;)                                               //~0406I~
            {                                                      //~0406I~
                for (int jj=0;jj<4;jj++)                           //~0406I~
                {                                                  //~0406I~
                    td = al.get(tc);                               //~0406R~
                    td.ctrRemain--;                                //~0406I~
                    tdnew = TileData.newInstance(td);              //~0406I~
                    out[outctr++] = tdnew;                         //~0406I~
                	if (td.ctrRemain==0)                           //~0406I~
                		tc--;                                      //~0406I~
                    if (outctr>=14)                                //~0406I~
                        break;                                     //~0406I~
                }                                                  //~0406I~
                if (outctr>=14)                                    //~0406I~
                	break;                                         //~0406I~
            }                                                      //~0406I~
        //*hands 4 player                                          //~0406I~
            for (;;)                                               //~0406I~
            {                                                      //~0406I~
                for (int jj=0;jj<4;jj++)                           //~0406I~
                {                                                  //~0406I~
                    td = al.get(typectr);                          //~0406I~
                    td.ctrRemain--;                                //~0406I~
                	if (td.ctrRemain==0)                           //~0406I~
                		typectr++;                                 //~0406I~
                    tdnew = TileData.newInstance(td);              //~0406I~
                    if ((tdnew.flag & TDF_RED5) != 0)              //~0406I~
                    	if (tdnew.ctrRemain<2)                     //~0406I~
		                    tdnew.flag &= ~TDF_RED5;               //~0406I~
                                                                   //~0406I~
                    out[outctr++] = tdnew;                         //~0406I~
                    if (outctr>=14+13*4)                           //~0406I~
                        break;                                     //~0406I~
                }                                                  //~0406I~
                if (outctr>=14+13*4)                               //~0406I~
                    break;                                         //~0406I~
            }                                                      //~0406I~
        //*remaining on stock                                      //~0406I~
            for (int ii = outctr; ii < PIECE_TILECTR; ii++)        //~0406I~
            {                                                      //~0406I~
                td = al.get(typectr);                              //~0406I~
                td.ctrRemain--;                                    //~0406I~
                if (td.ctrRemain==0)                               //~0406I~
                	typectr++;                                     //~0406I~
                tdnew = TileData.newInstance(td);                  //~0406I~
                if ((tdnew.flag & TDF_RED5) != 0)                  //~0406I~
                    if (tdnew.ctrRemain<2)                         //~0406I~
		                tdnew.flag &= ~TDF_RED5;                   //~0406I~
                out[outctr++] = tdnew;                             //~0406I~
            }                                                      //~0406I~
            shuffledTileData = out;                                //~0406I~
        if (Dump.Y) Dump.println("shuffleMinkanTest "+TileData.toString(shuffledTileData));//~0406R~
    }                                                              //~0406I~
    //*****************************************************************//~1401I~
    //*for test Pon                                                //~1401I~
    //*****************************************************************//~1401I~
    public void shufflePonTest()                                   //~1401I~
    {                                                              //~1401I~
            ArrayList<TileData> al = deepCopyToArrayList();        //~1401I~
            TileData[] out = getNewAllTile();                      //~1401I~
            int outctr = 0;                                        //~1401I~
            int typectr = 0;                                       //~1401I~
            int tc=PIECE_TYPECTR-1;                                //~1401I~
            TileData td,tdnew;                                     //~1401I~
        //*wanpai 14                                               //~1401I~
		    outctr=setTestWanpai(out,outctr,al);                   //~1402I~
        //*hands 4 player                                          //~1401I~
            for (;;)                                               //~1401I~
            {                                                      //~1401I~
                td = al.get(typectr);                          //~1401I~//~1402R~
                td.ctrRemain--;                                //~1401I~//~1402R~
                tdnew = TileData.newInstance(td);              //~1401I~//~1402R~
                out[outctr++] = tdnew;                         //~1401M~//~1402R~
                td = al.get(typectr);                              //~1402I~
                td.ctrRemain--;                                    //~1402I~
                tdnew = TileData.newInstance(td);                  //~1402I~
                out[outctr++] = tdnew;                             //~1402I~
                if (outctr>=14+8*4)                               //~1401I~//~1402R~
                    break;                                         //~1401I~
                typectr++;                                         //~1402I~
            }                                                      //~1401I~
        //*remaining on stock                                      //~1401I~
		    setTestRemainRandom(out,outctr,al);                    //~1402R~
            shuffledTileData = out;                                //~1401I~
        if (Dump.Y) Dump.println("shufflePonTest "+TileData.toString(shuffledTileData));//~1401I~//~1402R~
    }                                                              //~1401I~
    //*****************************************************************//~1403I~
    //*for test Chii                                               //~1403I~
    //*****************************************************************//~1403I~
    public void shuffleChiiTest()                                   //~1403I~
    {                                                              //~1403I~
            ArrayList<TileData> al = deepCopyToArrayList();        //~1403I~
            TileData[] out = getNewAllTile();                      //~1403I~
            int outctr = 0;                                        //~1403I~
            int typectr = 0;                                       //~1403I~
            int tc=PIECE_TYPECTR-1;                                //~1403I~
            TileData td,tdnew;                                     //~1403I~
        //*wanpai 14                                               //~1403I~
		    outctr=setTestWanpai(out,outctr,al);                   //~1403I~
        //*hands 4 player                                          //~1403I~
//            int[][] itsDeal={                                   //~1403I~//~1405R~
//                                    {23,24,25,  4, 5, 6, 10,11,28, 19,19,28 },//~1403R~//~1405R~
//                                    {23,24,25,  4, 5,29,  6,11,12, 20,20,29 },//~1403R~//~1405R~
//                                    {22,23,24,  4, 5, 6, 10,11,12, 21,21,30 },//~1403R~//~1405R~
//                                    {22,23,22,  4, 6, 7, 10,11,12, 22,26,27 },//~1403R~//~1405R~
//                                };                                  //~1403I~//~1405R~
//            outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1403I~//~1405R~
//            int[] itsDealTake={ 8, 8, 8, 8};                       //~1403R~//~1405R~
//            outctr=setTestTake(out,outctr,al,itsDealTake);         //~1403I~//~1405R~
//            itsDealTake=new int[]{ 6,25,24,11};                    //~1403R~//~1405R~
//            outctr=setTestTake(out,outctr,al,itsDealTake);         //~1403I~//~1405R~
        	int[][] itsDeal={                                      //~1405I~
            						{23,24,25,  2, 3, 4, 10,15,16, 28,28, 8 },//~1405I~
            						{23,24,25,  2, 3, 4,  9,11,16, 29,29, 8 },//~1405I~
            						{23,24,25,  2, 3, 4, 10,11,12, 27,27, 8 },//~1405I~
            						{23,24,25,  2, 3, 4, 10,11,12, 30,30, 8 },//~1405I~
            					};                                 //~1405I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1405I~
        	int[] itsDealTake={28,29,27,30};                       //~1405I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        	itsDealTake=new int[]{ 7, 7, 7, 7};                    //~1405I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        //*remaining on stock                                      //~1403I~
		    setTestRemainRandom(out,outctr,al);                    //~1403I~
            shuffledTileData = out;                                //~1403I~
        if (Dump.Y) Dump.println("shuffleChiiTest "+TileData.toString(shuffledTileData));//~1403R~
    }                                                              //~1403I~
    //*****************************************************************//~1405I~
    //*for test Pon and Chii                                       //~1405I~
    //*****************************************************************//~1405I~
    public void shufflePonChiiTest(int Pcase)                               //~1405I~//~1407R~
    {                                                              //~1405I~
        if (Dump.Y) Dump.println("shufflePonChiiTest case="+Pcase);//~1411I~
        	int[][] itsDeal;                                       //~1405I~
        	int[] itsDealTake;                                     //~1405I~
            ArrayList<TileData> al = deepCopyToArrayList();        //~1405I~
            TileData[] out = getNewAllTile();                      //~1405I~
            int outctr = 0;                                        //~1405I~
            int typectr = 0;                                       //~1405I~
            int tc=PIECE_TYPECTR-1;                                //~1405I~
            TileData td,tdnew;                                     //~1405I~
        //*wanpai 14                                               //~1405I~
		    outctr=setTestWanpai(out,outctr,al);      //RGW,2Pei  //~1405I~//~1418R~
        //*hands 4 player                                          //~1405I~
        if (Pcase==15) //(15)  chii + 3shiki tsumo  NG             //~1420I~
        {                                                          //~1420I~
        	itsDeal=new int[][]{                                   //~1420I~
                                    { 3, 4, 5, 11,13,14, 18,21,24, 12,13,27 },//~1420I~
                                    { 0, 1, 2,  9,10,11, 19,20, 7,  7,25,26 },     //chii,South pos//~1420I~
                                    { 6, 8, 8, 15,16,16, 23,24,25, 15,29,30 },//~1420I~
                                    { 6, 6, 8, 14,15,16, 23,24,25, 27,28,30 },//~1420I~
            					};                                 //~1420I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1420I~
        	itsDealTake=new int[]{27,28, 0, 0};                    //~1420I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{10,19,19,19};  //18tsumo         //~1420I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{21,17,17,17};  //18tsumo         //~1420I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        }                                                          //~1420I~
        else                                                       //~1420I~
        if (Pcase==14) //(14)  chii + 3shiki tsumo  OK             //~1420R~
        {                                                          //~1420I~
        	itsDeal=new int[][]{                                   //~1420I~
//                                    { 3, 4, 5, 11,13,14, 22,23,24, 12,13,14 },//~1420I~
//                                    { 0, 1, 2,  9,10,17,  1, 2, 7,  7, 7,26 },     //chii,South pos//~1420I~
//                                    { 6, 8, 8, 15,16,16, 23,24,25, 15,29,29 },//~1420I~
//                                    { 6, 6, 8, 14,15,16, 23,24,25, 27,28,28 },//~1420I~
                                    { 3, 4, 5, 11,13,14, 18,19,24, 12,13,27 },//~1420R~//~1425R~
                                    { 0, 1, 2,  9,10,11, 19,20, 7,  7,25,26 },     //chii,South pos//~1420I~
                                    { 6, 8, 8, 15,16,16, 23,24,25, 15,29,30 },//~1420I~
                                    { 6, 6, 8, 14,15,16, 23,24,25, 27,28,30 },//~1420I~
            					};                                 //~1420I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1420I~
        	itsDealTake=new int[]{27,28, 0, 0};                    //~1420R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{10,19,21,21};  //18tsumo         //~1420R~//~1425R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{18,17,17,17};  //18tsumo         //~1420I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        }                                                          //~1420I~
        else                                                       //~1425I~
        if (Pcase==141) //(141)  chii + 3shiki tsumo  OK           //~1425I~
        {                                                          //~1425I~
        	itsDeal=new int[][]{                                   //~1425I~
                                    { 3, 4, 5, 11,13,14, 18,19,24, 12,13,27 },//~1425I~
                                    { 0, 1, 2,  9,10,11, 19,20, 7,  7,25,26 },     //chii,South pos//~1425I~
                                    { 6, 8, 8, 15,16,16, 23,24,25, 15,29,30 },//~1425I~
                                    { 6, 6, 8, 14,15,16, 23,24,25, 27,28,30 },//~1425I~
            					};                                 //~1425I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1425I~
        	itsDealTake=new int[]{27,28, 0, 0};                    //~1425I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1425I~
        	itsDealTake=new int[]{10,19,21,28};                    //~1425I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1425I~
        	itsDealTake=new int[]{18,17,17,17};                    //~1425I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1425I~
        }                                                          //~1425I~
        else                                                       //~1420I~
        if (Pcase==13) //(13)double run and chii     east discard 7sou,souse chhi it//~1420R~
        {                                                          //~1419I~
        	itsDeal=new int[][]{                                   //~1419I~
            						{ 3, 4, 5, 11,13,14, 22,23,24, 12,13,14 },//~1419I~
            						{ 0, 1, 2,  9,10,17,  1, 2, 7,  7, 7,26 },     //chii,South pos//~1419I~
            						{ 6, 8, 8, 15,16,16, 23,24,25, 15,29,29 },//~1419I~
            						{ 6, 6, 8, 14,15,16, 23,24,25, 27,28,28 },//~1419I~
            					};                                 //~1419I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1419I~
        	itsDealTake=new int[]{27,26, 0, 1};                    //~1419I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);
        	itsDealTake=new int[]{26, 0, 1, 2};                    //~1419I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);
        }                                                          //~1419I~
        else                                                       //~1419I~
        if (Pcase==12) //(12)multiWait ron(kataagari) OK test   DoubleRun NotFix NG//~1419R~
        {                                                          //~1419I~
        	itsDeal=new int[][]{                                   //~1419I~
            						{ 0, 1, 2,  9,10,11,  1, 2, 7,  7, 7,26 },//~1419R~
            						{ 3, 4, 5, 12,13,14, 22,23,24, 12,13,14 },//~1419I~
            						{ 6, 8, 8, 15,16,16, 25,25,26, 15,28,29 },//~1419R~
            						{ 6, 6, 8, 15,15,16, 24,24,25, 13,28,29 },//~1419R~
            					};                                 //~1419I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1419I~
        	itsDealTake=new int[]{27,27, 0, 1};                    //~1419I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        	itsDealTake=new int[]{28,29, 0, 1};                    //~1419I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        	itsDealTake=new int[]{26, 3,21,18};                    //~1419I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        }                                                          //~1419I~
        else                                                       //~1419I~
        if (Pcase==10) //multiWait ron(kataagari) OK test   DoubleRun kanchan//~1419R~
        {                                                          //~1419I~
        	itsDeal=new int[][]{                                   //~1419I~
            						{ 0, 1, 2,  9,10,11,  0, 2, 7,  7, 7,26 },//~1419R~
            						{ 3, 4, 5, 12,13,14, 22,23,24, 12,13,18 },//~1419I~//~1424R~
            						{ 6, 8, 8, 15,16,16, 25,25,26, 15,28,29 },//~1419R~
            						{ 6, 6, 8, 15,15,16, 24,24,25, 13,28,29 },//~1419R~
            					};                                 //~1419I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1419I~
        	itsDealTake=new int[]{27,30, 0, 1};                    //~1419I~//~1424R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        	itsDealTake=new int[]{28,29, 0, 1};                    //~1419I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        	itsDealTake=new int[]{26, 1,21,18};                    //~1419I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        }                                                          //~1419I~
        else                                                       //~1419I~
        if (Pcase==11) //(11)multiWait ron(kataagari) OK test   DoubleRun ryanmen//~1419R~//~1424R~
        {                                                          //~1419I~
        	itsDeal=new int[][]{                                   //~1419I~
            						{ 0, 1, 2,  9,10,11,  1, 2, 7,  7, 7,26 },//~1419R~
            						{ 3, 4, 5, 12,13,14, 22,23,24, 12,13,14 },//~1419I~
            						{ 6, 8, 8, 15,16,16, 25,25,26, 15,28,29 },//~1419R~
            						{ 6, 6, 8, 15,15,16, 24,24,25, 13,28,29 },//~1419R~
            					};                                 //~1419I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1419I~
        	itsDealTake=new int[]{27,23, 0, 1};                    //~1419I~//~1422R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        	itsDealTake=new int[]{28,26, 0, 1};                    //~1419I~//~1422R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        	itsDealTake=new int[]{27, 0,21,18};                    //~1419I~//~1420R~//~1422R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        	itsDealTake=new int[]{18,17,17,17};                    //~1420R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        }                                                          //~1419I~
        else                                                       //~1419I~
        if (Pcase==111) //(111)multiWait ron(kataagari) OK test   DoubleRun ryanmen tsumo//~1422I~//~1424R~
        {                                                          //~1422I~
        	itsDeal=new int[][]{                                   //~1422I~
            						{ 0, 1, 2,  9,10,11,  1, 2, 7,  7, 7,26 },//~1422I~
            						{ 3, 4, 5, 12,13,14, 17,23,24, 12,13,14 },//~1422I~//~1425R~
            						{ 6, 8, 8, 15,16,16, 25,25,26, 15,28,29 },//~1422I~
            						{ 6, 6, 8, 15,15,16, 24,24,25, 13,28,29 },//~1422I~
            					};                                 //~1422I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1422I~
        	itsDealTake=new int[]{27,23, 0, 1};                    //~1422I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1422I~
        	itsDealTake=new int[]{28,26,18, 1};                    //~1422I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1422I~
        	itsDealTake=new int[]{27,19,21,18};                    //~1422I~//~1425R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1422I~
        	itsDealTake=new int[]{ 0,17,18,19};                    //~1422I~//~1425R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1422I~
        	itsDealTake=new int[]{ 0,17,19,19};           //furiten tsumo//~1425I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1425I~
        }                                                          //~1422I~
        else                                                       //~1422I~
        if (Pcase==9) //(9)multiWait ron(3shiki kataagari) OK test           //~1418R~//~1420R~//~1424R~
        {                                                          //~1418I~
        	itsDeal=new int[][]{                                   //~1418I~
            						{ 0, 1, 2,  9,10,11, 19,20, 7,  7, 7,26 },//~1418I~//~1419R~
            						{ 3, 4,10, 12,13,14, 22,23,24, 12,13,14 },//~1418I~//~1424R~
            						{ 6, 8, 8, 15,16,16, 25,25,26, 15,28,29 },//~1418I~//~1419R~
            						{ 6, 6, 8, 15,15,16, 24,24,25, 13,28,29 },//~1418I~//~1419R~
            					};                                 //~1418I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1418I~
        	itsDealTake=new int[]{27,27, 0, 1};                    //~1418I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1418I~
        	itsDealTake=new int[]{28,29, 0, 1};                    //~1418I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1418I~
        	itsDealTake=new int[]{26,18,21,18};                    //~1418R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1418I~
        }                                                          //~1418I~
        else                                                       //~1418I~
        if (Pcase==8) //(8)multiWait ron(3shiki kataagari) NG test           //~1418I~//~1420R~//~1424R~
        {                                                          //~1418I~
        	itsDeal=new int[][]{                                   //~1418I~
            						{ 0, 1, 2,  9,10,11, 19,20, 7,  7, 7,26 },//~1418I~//~1419R~
            						{ 3, 4,10, 12,13,14, 22,23,24, 12,13,14 },//~1418I~//~1424R~
            						{ 6, 8, 8, 15,16,16, 25,25,26, 15,28,29 },//~1418I~//~1419R~
            						{ 6, 6, 8, 15,15,16, 24,24,25, 13,28,29 },//~1418I~//~1419R~
            					};                                 //~1418I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1418I~
        	itsDealTake=new int[]{27,27, 0, 1};                    //~1418I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1418I~
        	itsDealTake=new int[]{28,29, 0, 1};                    //~1418I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1418I~
        	itsDealTake=new int[]{26,21,17,18};                    //~1418I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1418I~
        }                                                          //~1418I~
        else                                                       //~1418I~
        if (Pcase==7) //ron river                                  //~1414I~
        {                                                          //~1414I~
        	itsDeal=new int[][]{                                   //~1414I~
            						{21,22,23,  1, 2, 3,  4, 5, 6, 18,19,20 },//~1414R~
            						{ 1, 2, 3,  4, 5, 6, 18,19,20, 21,22,23 },//~1414I~
            						{ 4, 5, 6, 18,19,20,  9, 9, 9,  7, 7, 7 },//~1414R~
            						{ 1, 2, 3, 24,24,24,  0, 0, 0, 26,26,26 },//~1414R~
            					};                                 //~1414I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1414I~
        	itsDealTake=new int[]{18, 8, 8,21};                    //~1414R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1414I~
        	itsDealTake=new int[]{30,10,10,10};                    //~1414R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1414I~
        	itsDealTake=new int[]{30,21,25,25};                    //~1414R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1414I~
        }                                                          //~1414I~
        else                                                       //~1414I~
        if (Pcase==6) //(6)rontaken not double reach not oneshot     //~1414I~//~1420R~
        {                                                          //~1414I~
        	itsDeal=new int[][]{                                   //~1414I~
            						{ 9, 9, 9,  1, 2, 3,  4, 5, 6, 18,19, 8 },//~1414R~//~1420R~
            						{ 1, 2, 3, 10,10,10, 18,19,20, 21,22,23 },//~1414I~//~1425R~
            						{ 4, 5, 6, 18,19,20, 21,22,24,  0, 5, 6 },//~1414I~//~1420R~
            						{ 0, 2, 3, 21,22,23, 18,19,15, 20,22,23 },//~1414I~//~1420R~
            					};                                 //~1414I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1414I~
        	itsDealTake=new int[]{30,17,27,25};                    //~1414I~//~1420R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1414I~
        	itsDealTake=new int[]{28,17,24,25};                    //~1420I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{20, 8,28,28};                    //~1414R~//~1420R~//~1421R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1414I~
        	itsDealTake=new int[]{17,29,24, 0};                    //~1414I~//~1420R~//~1421R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1414I~
        	itsDealTake=new int[]{ 8,17,24,25};                    //~1420R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        }                                                          //~1414I~
        else                                                       //~1414I~
        if (Pcase==61) //(61)rontaken double reach not oneshot //~1420I~//~1421R~
        {                           //robot east double reach     //~1420I~//~1421R~
        	itsDeal=new int[][]{                                   //~1420I~
            						{18,19,20,  1, 2, 3,  4, 5, 6, 18,19, 8 },//~1420I~//~1427R~
            						{ 1, 2, 3,  4, 5, 6,  9, 9,11, 21,22,23 },//~1420I~//~1427R~
            						{ 4, 5, 6, 18,19,30, 21,22,24,  0, 5, 6 },//~1420I~//~1427R~
            						{ 0, 2, 3, 21,22,23, 18,19,15, 20,22,23 },//~1420I~
            					};                                 //~1420I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1420I~
        	itsDealTake=new int[]{ 8,30,27,25};                    //~1420I~//~1421R~//~1427R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{20,29,28,28};                    //~1420I~//~1427R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{17,17,24, 0};                    //~1420I~//~1421R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{20,17,24,25};                    //~1420I~//~1427R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        }                                                          //~1420I~
        else                                                       //~1420I~
        if (Pcase==5) //rontaken 1st                               //~1412R~//~1414R~
        {                                                          //~1412I~
        	itsDeal=new int[][]{                                   //~1412I~
            						{ 0, 0, 0,  1, 2, 3,  4, 5, 6, 18,19,20 },//~1412I~//~1414R~
            						{ 1, 2, 3,  4, 5, 6, 18,19,20, 21,22,23 },//~1412I~//~1414R~
            						{ 4, 5, 6, 18,19,20, 21,22,23,  4, 5, 6 },//~1412I~//~1414R~
            						{ 1, 2, 3, 21,22,23, 18,19,20, 21,22,23 },//~1412I~//~1414R~
            					};                                 //~1412I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1412I~
        	itsDealTake=new int[]{ 8,17,24,25};                    //~1412I~//~1414R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1412I~
        	itsDealTake=new int[]{ 8,17,24,25};                    //~1412I~//~1414R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1412I~
        }                                                          //~1412I~
        else                                                       //~1412I~
        if (Pcase==4) //rinshanron                                 //~1411R~
        {                //kan after reach                         //~1411I~
        	itsDeal=new int[][]{                                   //~1411I~
            						{ 0, 0, 0,  1, 2, 3,  4, 5, 6, 10,11,12 },//~1411R~//~1413R~
            						{ 9, 9, 9,  4, 5, 6, 11,12,13, 14,15,16 },//~1411R~//~1413R~
            						{18,18,18, 19,20,21, 22,23,24, 25,25,25 },//~1411R~//~1413R~
            						{ 1, 2, 3,  4, 5, 6, 26,26,26,  7, 7, 7 },//~1411R~//~1413R~
            					};                                 //~1411R~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1411I~
        	itsDealTake=new int[]{ 8,17,23,26};                    //~1411R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1411I~
        	itsDealTake=new int[]{ 0, 9,18, 4};                    //~1411R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1411I~
            TileData tdrinshan1 = al.get(8);                               //~1411I~
            out[13]=tdrinshan1;	//1t rinshan                       //~1411R~
                                                                   //~1411I~
        }                                                          //~1411I~
        else                                                       //~1411I~
        if (Pcase==3)                                                  //~1405I~//~1407R~
        {                //kan after reach                         //~1405I~
        	itsDeal=new int[][]{                                   //~1405I~
            						{23,24,25,  2, 3, 4, 15,15,15, 28,28, 5 },//~1405I~
            						{23,24,25,  2, 3, 4, 10,13,16, 29,29, 6 },//~1405I~
            						{23,24,25,  2, 3, 4, 10,13,16, 27,27, 7 },//~1405I~
            						{23,24,25,  2, 3, 4, 10,13,16, 26,26, 8 },//~1405I~
            					};                                 //~1405I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1405I~
        	itsDealTake=new int[]{28,29,27,26};                             //~1405I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        	itsDealTake=new int[]{ 0, 0, 0, 0};                    //~1405I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        	itsDealTake=new int[]{15, 6, 7, 8};                    //~1405R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        }                                                          //~1405I~
        else                                                       //~1405I~
        if (Pcase==1)                                                 //~1405I~//~1407R~
        {                //pon and chii by 2pin                    //~1405R~
        	itsDeal=new int[][]{                                   //~1405R~
            						{23,24,25,  2, 3, 4, 10,15,16, 28,28, 8 },//~1405I~
            						{23,24,25,  2, 3, 4,  9,11,16, 29,29, 8 },//~1405I~
            						{23,24,25,  2, 3, 4, 10,10,12, 27,27, 8 },//~1405I~
            						{23,24,25,  2, 3, 4, 10,11,12, 26,26, 8 },//~1405R~
            					};                                 //~1405I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1405I~
        	itsDealTake=new int[]{28,29,27,26};                             //~1405R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        	itsDealTake=new int[]{ 7, 7, 7, 7};                    //~1405I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        }                                                          //~1405I~
        else  //double ron test                                    //~1405I~
        if (Pcase==2)                                              //~1407I~
        {                                                          //~1405I~
        	itsDeal=new int[][]{                                   //~1405I~
            						{23,24,25,  2, 3, 4,  9,11,16, 29,29, 8 },//~1405M~
            						{23,24,25,  2, 3, 4, 11,12,17, 27,27, 8 },//~1405M~
            						{23,24,25,  2, 3, 4, 10,15,16, 28,28,16 },//~1405R~
            						{23,24,25,  2, 3, 4, 10,15,17, 26,26,17 },//~1405R~
            					};                                 //~1405I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1405I~
        	itsDealTake=new int[]{29,27,28,26};                             //~1405R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        	itsDealTake=new int[]{16,17,16,17};                    //~1405R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        }                                                          //~1405I~
        //*remaining on stock                                      //~1405I~
		    setTestRemainRandom(out,outctr,al);                    //~1405I~
            shuffledTileData = out;                                //~1405I~
        if (Dump.Y) Dump.println("shufflePonChiiTest "+TileData.toString(shuffledTileData));//~1405I~
    }                                                              //~1405I~
    //*****************************************************************//~1412I~
    //*****************************************************************//~1501I~
    //*****************************************************************//~1501I~
    public void shuffleNoPairTest(int Pcase)                       //~1412I~
    {                                                              //~1412I~
        if (Dump.Y) Dump.println("shuffleNoPairTest case="+Pcase); //~1412I~
        	int[][] itsDeal;                                       //~1412I~
        	int[] itsDealTake;                                     //~1412I~
            ArrayList<TileData> al = deepCopyToArrayList();        //~1412I~
            TileData[] out = getNewAllTile();                      //~1412I~
            int outctr = 0;                                        //~1412I~
            int typectr = 0;                                       //~1412I~
            int tc=PIECE_TYPECTR-1;                                //~1412I~
            TileData td,tdnew;                                     //~1412I~
        //*wanpai 14                                               //~1412I~
		    outctr=setTestWanpai2(out,outctr,al);    //8,9man,1,2pin(7,8,9,10)//~1412R~
        //*hands 4 player                                          //~1412I~
        if (Pcase==1) //13NoPAir                                   //~1412I~
        {                                                          //~1412I~
        	itsDeal=new int[][]{                                   //~1412I~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 27,28, 29},//~1412R~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 27,28, 30},//~1412R~
            						{19,19,19, 20,20,20, 22,22,22, 23,23,23 },//~1412R~
            						{ 1, 1, 1,  2, 2, 2,  4, 4, 4,  5, 5, 5 },//~1412R~
            					};                                 //~1412I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1412I~
        	itsDealTake=new int[]{30,31,24,25};                    //~1412R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1412I~
        	itsDealTake=new int[]{ 0, 3,12,13};                    //~1412R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1412I~
        }                                                          //~1412I~
        else                                                       //~1412I~
        if (Pcase==2) //13NoPAir                                   //~1412I~
        {                                                          //~1412I~
        	itsDeal=new int[][]{                                   //~1412I~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 27,28, 29},//~1412I~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 27,28, 30},//~1412I~
            						{19,19,19, 20,20,20, 22,22,22, 23,23,23 },//~1412I~
            						{ 1, 1, 1,  2, 2, 2,  4, 4, 4,  5, 5, 5 },//~1412I~
            					};                                 //~1412I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1412I~
        	itsDealTake=new int[]{30,31,24,25};                    //~1412I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1412I~
        	itsDealTake=new int[]{31,32, 7, 8};                    //~1412I~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1412I~
        }                                                          //~1412I~
        else                                                       //~1415I~
        if (Pcase==3) //intent 3dragon                             //~1415I~
        {                                                          //~1415I~
        	itsDeal=new int[][]{                                   //~1415I~
            						{31,31,12, 32,32,17, 18,21,24, 27,28, 29},//~1415I~//~1427R~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 27,29, 30},//~1415I~//~1427R~
            						{ 0,19,19,  1,20,20,  2,22,22,  3,23,32 },//~1415R~
            						{ 1, 1,27,  2, 2,28,  4, 4,11,  5, 5,19 },//~1415R~
            					};                                 //~1415I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1415I~
        	itsDealTake=new int[]{33,31, 1, 2};                    //~1415I~//~1427R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1415I~
        	itsDealTake=new int[]{ 0,32,27,28};                    //~1415R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1415I~
        }                                                          //~1415I~
        else                                                       //~1419I~
        if (Pcase==4) //multiwait 2 of WGR and doubleEast          //~1420R~
        {                                                          //~1419I~
        	itsDeal=new int[][]{                                   //~1419I~
            						{31,32,27, 27, 0, 1,  2,10,11, 12,18, 19},//~1419I~//~1420R~//~1427R~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 32,28, 30},//~1419I~//~1420R~
            						{ 0,19,19,  1,21,22,  2,22,22,  3,23,24 },//~1419I~
            						{ 1, 1, 3,  2, 2,28,  4, 4,11,  5, 5,20 },//~1419I~//~1420R~
            					};                                 //~1419I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1419I~
        	itsDealTake=new int[]{33,19,20,21};                    //~1419I~//~1420R~//~1427R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        	itsDealTake=new int[]{20,31,26,26};                    //~1419I~//~1420R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        	itsDealTake=new int[]{28,27,33,20};                    //~1419I~//~1420R~//~1427R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1419I~
        }                                                          //~1419I~
        else                                                       //~1427I~
        if (Pcase==411) //samecolor                                //~1427I~
        {                                                          //~1427I~
        	itsDeal=new int[][]{                                   //~1427I~
            						{20,20,23,  0, 1, 2, 12,11,11, 12,18, 19},//~1427I~
            						{17,18,19, 21,21,22, 24,24,25, 27,27, 30},//~1427I~//~1430R~
            						{ 0, 1, 2, 17,17,11, 12,28,17, 18,20, 21},//~1427I~
            						{ 1, 2, 3,  4, 5, 6,  4, 5,20, 31,32, 33},//~1427I~
            					};                                 //~1427I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1427I~
        	itsDealTake=new int[]{33,30,32,30};                    //~1427R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1427I~
        }                                                          //~1427I~
        else                                                       //~1501I~
        if (Pcase==412) //atoduke take samecolor                   //~1501I~
        {                                                          //~1501I~
        	itsDeal=new int[][]{                                   //~1501I~
            						{ 0, 0, 2,  4, 4, 5,  6, 6,11, 18,20+100, 23},//~1501R~//~va8xR~
            						{18,19,17, 27,27,28, 21,22,23, 24,25, 26},//~1501I~
            						{ 2, 5,11, 12,12,15, 18,21,24, 31,32, 33},//~1501R~
            						{ 1, 4, 6, 11,13,17, 18,21,24, 31,32, 33},//~1501R~
            					};                                 //~1501I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1501I~
        	itsDealTake=new int[]{27+200,26,28,28};                    //~1501R~//~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        	itsDealTake=new int[]{ 1,29,29, 1};                    //~1501R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        	itsDealTake=new int[]{ 1,29,26+300,23};                    //~1501I~//~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        }                                                          //~1501I~
        else                                                       //~1501I~
        if (Pcase==413) //atoduke take yakuhai 1 han               //~1501I~
        {                                                          //~1501I~
        	itsDeal=new int[][]{                                   //~1501I~
            						{ 0, 0, 2,  4, 4, 5,  6, 6,11, 17,20+100, 23},//~1501I~//~va8xR~
            						{18,19,17, 27,27,28, 13,13,13, 24,25, 17},//~1501I~//~va8xR~
            						{ 2, 5,11, 12,12,15, 18,21,24, 31,32, 33},//~1501I~
            						{ 1, 4, 6, 11,14,16, 18,21,24, 31,32, 33},//~1501I~//~va8xR~
            					};                                 //~1501I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1501I~
        	itsDealTake=new int[]{27+200,26,28,28};                    //~1501I~//~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        	itsDealTake=new int[]{ 1,29,29,15};                    //~1501I~//~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        	itsDealTake=new int[]{27,14,14,15};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        }                                                          //~1501I~
        else                                                       //~va8xI~
        if (Pcase==414) //chii,ankan,ron by ankan fix1             //~va8xI~
        {                                                          //~va8xI~
        	itsDeal=new int[][]{                                   //~va8xI~
            						{27,27, 2,  4, 4, 5,  6, 6,11, 17,20, 23},//~va8xR~
            						{18,19,17,  0, 1,28, 33,33,33, 24,25, 17},//~va8xR~
            						{ 2, 5,11, 12,12,15, 18,21,24, 31,32, 13},//~va8xR~
            						{ 1, 4, 6, 11,14,16, 18,21,24, 31,32, 13},//~va8xR~
            					};                                 //~va8xI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~va8xI~
        	itsDealTake=new int[]{27,33,28,28};                    //~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{ 1,29,29,15};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{27,14,14,15};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        }                                                          //~va8xI~
        else                                                       //~va8xI~
        if (Pcase==415) //sakizuke 2siba kataagari tsumo           //~va8xI~
        {                                                          //~va8xI~
        	itsDeal=new int[][]{                                   //~va8xI~
            						{27+100,31,26, 18,19,20,  0, 1, 2, 15,16, 17},//~va8xR~
            						{27,27,29, 33,33,26, 26, 0, 1, 2 ,11, 12},//~va8xR~
            						{ 2, 5,11, 12,12,15, 18,21,24, 30,32, 13},//~va8xR~
            						{ 1, 4, 6, 11,14,16, 18,21,24, 30,32, 13},//~va8xR~
            					};                                 //~va8xI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~va8xI~
        	itsDealTake=new int[]{33+200,13,14,15};                //~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{29,30,14,17};                    //~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{33,16,29,17};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        }                                                          //~va8xI~
        //*remaining on stock                                      //~1412I~
		    setTestRemainRandom(out,outctr,al);                    //~1412I~
            shuffledTileData = out;                                //~1412I~
        if (Dump.Y) Dump.println("shuffleNoPairTest "+TileData.toString(shuffledTileData));//~1412I~
    }                                                              //~1412I~
    //*****************************************************************//~1403I~
    private int setTestDeal12(TileData[] out,int outctr,ArrayList<TileData> al,int[][] PitsDeal)//~1403I~
    {                                                              //~1403I~
    	for (int ii=0;ii<4;ii++)                                   //~1403I~
        {                                                          //~1403I~
        	for (int jj=0;jj<12;jj++)                              //~1403I~
            {                                                      //~1403I~
            	int grp=jj/4;                                      //~1403I~
                int outpos=ii*4+grp*16+jj%4+outctr;                //~1403R~
                int tc=PitsDeal[ii][jj];                           //~1403I~
                int testOrder=tc/100;                              //~va8xI~
                tc%=100;                                           //~va8xI~
  		        if (Dump.Y) Dump.println("setTestDeal12 ii="+ii+",jj="+jj+",tc="+tc+",outpos="+outpos);//~1403I~
                TileData td = al.get(tc);                          //~1403I~
                td.ctrRemain--;                                    //~1403I~
  		        if (Dump.Y) Dump.println("setTestDeal12 ii="+ii+",jj="+jj+",tc="+tc+",ctrRemain="+td.ctrRemain+",outpos="+outpos);//~1403R~
                if (td.ctrRemain>=0)                               //~1403I~
                {                                                  //~1403I~
                    TileData tdnew = TileData.newInstance(td);     //~1403I~
	                if (td.type!=3 && td.number==TN5)                            //~1403R~//~1427R~
    		            tdnew.setRed5(td.ctrRemain==3||td.ctrRemain==2);//~1403R~
                    TileData.setTestSelectionOrder(tdnew,testOrder);//~va8xR~
                    out[outpos] = tdnew;                           //~1403I~
                }                                                  //~1403I~
                else                                               //~1403I~//~1412R~
                {                                                  //~1403I~//~1412R~
  		        	if (Dump.Y) Dump.println("setTestDeal12 remove tc="+tc);//~1412I~
                    al.remove(tc);                                 //~1403I~//~1412R~
                }                                                  //~1403I~//~1412R~
            }                                                      //~1403I~
        }                                                          //~1403I~
        return outctr+48;                                          //~1403I~
    }                                                              //~1403I~
    //*****************************************************************//~1403I~
    private int setTestTake(TileData[] out,int outctr,ArrayList<TileData> al,int[] PitsDeal)//~1403I~
    {                                                              //~1403I~
    	for (int ii=0;ii<4;ii++)                                   //~1403I~
        {                                                          //~1403I~
                int tc=PitsDeal[ii];                               //~1403I~
                int testOrder=tc/100;                              //~va8xI~
                tc%=100;                                           //~va8xI~
                TileData td = al.get(tc);                          //~1403I~
                td.ctrRemain--;                                    //~1403I~
  		        if (Dump.Y) Dump.println("setTestTake ii="+ii+",tc="+tc+",ctrRemain="+td.ctrRemain+",outctr="+outctr);//~1405I~//~1414R~
                if (td.ctrRemain>=0)                               //~1403I~
                {                                                  //~1403I~
                    TileData tdnew = TileData.newInstance(td);     //~1403I~
    	            if (td.number==TN5)                            //~1403R~
    		            tdnew.setRed5(td.ctrRemain==3||td.ctrRemain==2);//~1403I~
                    TileData.setTestSelectionOrder(tdnew,testOrder);//~va8xR~
                    out[outctr++] = tdnew;                         //~1403I~
                }                                                  //~1403I~
                else                                               //~1403I~
                {                                                  //~1403I~
                    al.remove(tc);                                 //~1403I~
                }                                                  //~1403I~
        }                                                          //~1403I~
        return outctr;                                             //~1403I~
    }                                                              //~1403I~
    //*****************************************************************//~1412I~
    //*RGW and 2pei                                                //~1412I~
    //*****************************************************************//~1412I~
    private int setTestWanpai(TileData[] out,int outctr,ArrayList<TileData> al)//~1402I~
    {                                                              //~1402I~
            int tc=PIECE_TYPECTR-1;                                //~1402I~
            for (;;)                                               //~1402I~
            {                                                      //~1402I~
                for (int jj=0;jj<4;jj++)                           //~1402I~
                {                                                  //~1402I~
                    TileData td = al.get(tc);                      //~1402R~
                    td.ctrRemain--;                                //~1402I~
                    if (Dump.Y) Dump.println("setTestWanpai jj="+jj+",tc="+tc+",ctrRemain="+td.ctrRemain);//~1426I~
                    if (td.ctrRemain>=0)                           //~1402I~
                    {                                              //~1402I~
                    	TileData tdnew = TileData.newInstance(td); //~1402R~
                    	out[outctr++] = tdnew;                     //~1402I~
	                    if (Dump.Y) Dump.println("setTestWanpai jj="+jj+",outctr="+outctr+",td="+td.toString());//~1426I~
                    }                                              //~1402I~
                    else                                           //~1402I~
                    {                                              //~1402I~
                    	al.remove(tc);                             //~1402I~
                    	tc--;                                      //~1402I~
                    }                                              //~1402I~
                    if (outctr>=14)                                //~1402I~
                        break;                                     //~1402I~
                }                                                  //~1402I~
                if (outctr>=14)                                    //~1402I~
                	break;                                         //~1402I~
            }                                                      //~1402I~
        return 14;                                                 //~1402I~
    }                                                              //~1402I~
    //*****************************************************************//~1412I~
    //*14tile from 89man,12pin                                     //~1412I~
    //*****************************************************************//~1412I~
    private int setTestWanpai2(TileData[] out,int outctr,ArrayList<TileData> al)//~1412I~
    {                                                              //~1412I~
            int tc=7;                                              //~1412I~
            for (;;)                                               //~1412I~
            {                                                      //~1412I~
                for (int jj=0;jj<4;jj++)                           //~1412I~
                {                                                  //~1412I~
                    TileData td = al.get(tc);                      //~1412I~
                    td.ctrRemain--;                                //~1412I~
  		        	if (Dump.Y) Dump.println("setWanpai2 jj="+jj+",tc="+tc+",ctrReamin="+td.ctrRemain+",outpos="+outctr);//~1412I~
                    if (td.ctrRemain>=0)                           //~1412I~
                    {                                              //~1412I~
                    	TileData tdnew = TileData.newInstance(td); //~1412I~
                    	out[outctr++] = tdnew;                     //~1412I~
                    }                                              //~1412I~
                    else                                           //~1412I~
                    {                                              //~1412I~
//                  	al.remove(tc);                             //~1412R~
                    	tc++;                                      //~1412I~
                    }                                              //~1412I~
                    if (outctr>=14)                                //~1412I~
                        break;                                     //~1412I~
                }                                                  //~1412I~
                if (outctr>=14)                                    //~1412I~
                	break;                                         //~1412I~
            }                                                      //~1412I~
        return 14;                                                 //~1412I~
    }                                                              //~1412I~
    //*****************************************************************//~1412I~
    private void setTestRemainRandom(TileData[] out,int outctr,ArrayList<TileData> al)//~1402R~
    {                                                              //~1402I~
//  		int typectr=al.size()-1;                               //~1402R~//~1426R~
    		int typectr=al.size();                                 //~1426I~
  		    if (Dump.Y) Dump.println("setTestRemainRandom outctr="+outctr+",typectr="+typectr);//~1426I~
            for (;;)                                               //~1402I~
            {                                                      //~1402I~
                int jj = Utils.getRandom(typectr);   //max 34-1    //~1402I~
                TileData td = al.get(jj);                          //~1402I~
                td.ctrRemain--;                                    //~1402I~
  		    	if (Dump.Y) Dump.println("setTestRemainRandom outpos="+outctr+",typectr="+typectr+",jj="+jj+",td="+td.toString());//~1426I~
                if (td.ctrRemain >= 0)                             //~1402I~
                {                                                  //~1402I~
                    TileData tdnew = TileData.newInstance(td);     //~1402I~
                    out[outctr++] = tdnew;                         //~1402I~
                }                                                  //~1402I~
                else                                               //~1402I~
                {                                                  //~1402I~
                    al.remove(jj);                                 //~1402I~
                    typectr--;                                     //~1402I~
                    if (typectr==0)                                //~1402I~
                        break;                                     //~1402I~
                }                                                  //~1402I~
                if (outctr>=PIECE_TILECTR)                         //~1402I~
                	break;                                         //~1402I~
            }                                                      //~1402I~
    }                                                              //~1402I~
    //*****************************************************************//~1328I~
    public void shuffleMultiRonTest(boolean PswMulti)              //~1328I~
    {                                                              //~1328I~
        if (Dump.Y) Dump.println("shuffleMultiRonTest swMulti="+PswMulti);//~1328I~
            ArrayList<TileData> al = deepCopyToArrayList();        //~1328I~
            TileData[] out = getNewAllTile();                      //~1328I~
            int outctr = 0,tc;                                     //~1328I~
            TileData td,tdnew;                         //~1328I~
        //*wanpai 14                                               //~1328I~
        tc=3*9+4;     //TT_JI                                        //~1328I~//~1401R~
        for (;;)                                                   //~1328I~
        {                                                          //~1328I~
            for (int jj=0;jj<4;jj++)                               //~1328I~
            {                                                      //~1328I~
                td = al.get(tc);                                   //~1328I~
                td.ctrRemain--;                                    //~1328I~
                tdnew = TileData.newInstance(td);                  //~1328I~
                out[outctr++] = tdnew;                             //~1328I~
                if (td.ctrRemain==0)                               //~1328I~
                {                                                  //~1401I~
                    tc++;                                          //~1328R~//~1401R~
                    if (tc>=3*9+7)                                 //~1401I~
                    	tc=3*9-1;                                  //~1401I~
                }                                                  //~1401I~
                if (outctr>=14)                                    //~1328I~
                    break;                                         //~1328I~
            }                                                      //~1328I~
            if (outctr>=14)                                        //~1328I~
                break;                                             //~1328I~
        }                                                          //~1328I~
        //*hands 4 player                                          //~1328I~
        int outpos=outctr;                                         //~1328I~
        for (int ii=0;ii<4;ii++)                                       //~1328I~
        {                                                          //~1328I~
    	    tc=0;                                                  //~va8xI~
        	if (PswMulti)                                          //~1328I~//~1331R~//~va8xR~
    	        tc=ii==3 ? 1 : 0;    //  for human=2                 //~1328R~//+va8xR~
//  	        tc=(ii>=2) ? 1 : 0;  //for human=1                 //+va8xI~
            else                                                   //~1328I~//~1331R~//~va8xR~
    	        tc=ii;                                             //~1328I~//~1331R~//~va8xR~
            for (int kk=0;kk<12;kk++)                              //~1328I~
            {                                                      //~1328I~
            	td = al.get(tc+kk);                                //~1328I~
                td.ctrRemain--;                                    //~1328I~
                tdnew = TileData.newInstance(td);      //seq*4     //~1328I~
                int grp=kk/4;                                      //~1328I~
                int posin4=kk%4;                                   //~1328I~
                outpos=outctr+grp*16+ii*4+posin4;                  //~1328R~
                out[outpos] = tdnew;                               //~1328I~
            }                                                      //~1328I~
        }                                                          //~1328I~
        outctr+=4*12;                                              //~1328I~
        //*hands 4 player pillow and draw                          //~1328I~
        //double reach                                             //~1331I~
            tc=28;            //pillow 1/2 for plkayer 1, 2        //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            tc=29;                                                 //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
            tc=30;                                                 //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
            tc=27;                                                 //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
                                                                   //~1331I~
        if (PswMulti)                                              //~1331I~
        {                                                          //~1331I~
            tc=22;            //pillow 1/2 for plkayer 1, 2        //~1328R~
            td = al.get(tc);                                       //~1328I~
            td.ctrRemain--;                                        //~1328I~
            tdnew = TileData.newInstance(td);                      //~1328R~
            tc=18;                                                 //~1328I~
            out[outctr++] = tdnew;                                 //~1328I~
            td = al.get(tc);                                       //~1328I~
            td.ctrRemain--;                                        //~1328I~
            tdnew = TileData.newInstance(td);                      //~1328R~
            out[outctr++] = tdnew;                                 //~1328I~
            tc=18;                                                 //~1328I~
            td = al.get(tc);                                       //~1328I~
            td.ctrRemain--;                                        //~1328I~
            tdnew = TileData.newInstance(td);                      //~1328R~
            out[outctr++] = tdnew;                                 //~1328I~
            tc=18;                                                 //~1328I~
            td = al.get(tc);                                       //~1328I~
            td.ctrRemain--;                                        //~1328I~
            tdnew = TileData.newInstance(td);                      //~1328R~
            out[outctr++] = tdnew;                                 //~1328I~
                                                                   //~1328I~
            tc=18;            //discard this ron tile              //~1328R~
            td = al.get(tc);                                       //~1328I~
            td.ctrRemain--;                                        //~1328I~
            tdnew = TileData.newInstance(td);                      //~1328I~
            out[outctr++] = tdnew;                                 //~1328I~
            tc=18;            //pillow 1/2 for plkayer 3, 4        //~1328I~
            td = al.get(tc);                                       //~1328I~
            td.ctrRemain--;                                        //~1328I~
            tdnew = TileData.newInstance(td);                      //~1328I~
            out[outctr++] = tdnew;                                 //~1328I~
            tc=18;            //pillow 1/2 for plkayer 3, 4        //~1328R~
            td = al.get(tc);                                       //~1328I~
            td.ctrRemain--;                                        //~1328I~
            tdnew = TileData.newInstance(td);                      //~1328I~
            out[outctr++] = tdnew;                                 //~1328I~
            tc=18;            //pillow 1/2 for plkayer 3, 4        //~1328I~
            td = al.get(tc);                                       //~1328I~
            td.ctrRemain--;                                        //~1328I~
            tdnew = TileData.newInstance(td);                      //~1328I~
            out[outctr++] = tdnew;                                 //~1328I~
        }                                                          //~1331I~
        else    //single                                           //~1331I~
        {                                                          //~1331I~
            tc=22;            //5sou                               //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            tc=18;            //1so                                //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
            tc=19;           //2s                                  //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
            tc=20;           //3s                                  //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
                                                                   //~1331I~
            tc=18;            //discard this ron tile              //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
            tc=19;            //pillow 1/2 for plkayer 3, 4        //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
            tc=20;            //pillow 1/2 for plkayer 3, 4        //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
            tc=21;            //pillow 1/2 for plkayer 3, 4        //~1331I~
            td = al.get(tc);                                       //~1331I~
            td.ctrRemain--;                                        //~1331I~
            tdnew = TileData.newInstance(td);                      //~1331I~
            out[outctr++] = tdnew;                                 //~1331I~
        }                                                          //~1331I~
        //*remaining on stock                                      //~1328I~
        	int typectr=0;                                             //~1328I~
            for (int ii = outctr; ii < PIECE_TILECTR; ii++)        //~1328R~
            {                                                      //~1328I~
                td = al.get(typectr);                              //~1328R~
                if (td.ctrRemain==0)                               //~1328I~
                {                                                  //~1328I~
                	typectr++;                                     //~1328I~
                    continue;                                      //~1328I~
                }                                                  //~1328I~
                td.ctrRemain--;                                    //~1328I~
                tdnew = TileData.newInstance(td);                  //~1328I~
                out[outctr++] = tdnew;                             //~1328I~
            }                                                      //~1328I~
            shuffledTileData = out;                                //~1328I~
        if (Dump.Y) Dump.println("shuffleMultiRonTest "+TileData.toString(shuffledTileData));//~1328I~
    }                                                              //~1328I~
    //*****************************************************************//~0407I~
    //*for test Minkan                                             //~0407I~
    //*****************************************************************//~0407I~
    public void shuffleChankanTest()                               //~0407I~
    {                                                              //~0407I~
	    shuffleMinkanTest();                                       //~0407I~
        TileData[] out=shuffledTileData;                           //~0407I~
        TileData td;                                               //~0407I~
        int hand,take;                                              //~0407I~
                                                                   //~0407I~
        hand=14+2; take=14+13*4+1;                                  //~0407I~
        td=out[take];                                              //~0407I~
        out[take]=out[hand];                                       //~0407I~
        out[hand]=td;                                              //~0407I~
        hand++; take+=4;                                           //~0407I~
        td=out[take];                                              //~0407I~
        out[take]=out[hand];                                       //~0407I~
        out[hand]=td;                                              //~0407I~
                                                                   //~0407I~
        hand=14+2+4*4; take++;                                     //~0407R~
        td=out[take];                                              //~0407I~
        out[take]=out[hand];                                       //~0407I~
        out[hand]=td;                                              //~0407I~
        hand++; take+=4;                                           //~0407I~
        td=out[take];                                              //~0407I~
        out[take]=out[hand];                                       //~0407I~
        out[hand]=td;                                              //~0407I~
                                                                   //~0407I~
        shuffledTileData = out;                                    //~0407I~
        if (Dump.Y) Dump.println("shuffleChankanTest "+TileData.toString(shuffledTileData));//~0407I~
    }                                                              //~0407I~
    //*************************************************************//~v@@@R~
    //*Deal as 4*3+1=13 for each player                            //~v@@@I~
    //*************************************************************//~v@@@I~
    public void setInitialDeal()                                   //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.setInitialDeal");          //~v@@@I~
//  	int pos=TILECTR_KEEPLEFT;                                  //~v@@@R~
//        for (int ii=0;ii<PLAYERS;ii+=HANDCTR)                    //~v@@@R~
//        {                                                        //~v@@@R~
//            AG.aPlayers.setInitialDeal(ii,shuffledTileData,pos); //~v@@@R~
//        }                                                        //~v@@@R~
        TileData[] tgt=new TileData[HANDCTR*PLAYERS];              //~v@@@I~
        TileData[] src=shuffledTileData;                           //~v@@@I~
//        if (Dump.Y)                                                //~v@@@I~//~1109R~
//            for (int ii=TILECTR_KEEPLEFT;ii<HANDCTR*PLAYERS;ii++)  //~v@@@R~//~1109R~
//            {                                                      //~v@@@I~//~1109R~
//                TileData td=src[ii];                               //~v@@@I~//~1109R~
//                Dump.println("Tiles.SetInitialDeal before type="+td.type+",no="+td.number+",ctr="+td.ctrRemain);//~v@@@R~//~1109R~
//            }                                                      //~v@@@I~//~1109R~
        int tgtpos=0;                                              //~v@@@I~
		int srcpos=TILECTR_KEEPLEFT;                               //~v@@@I~
        int ctreach=4;                                             //~v@@@I~
        for (int ii=0;ii<HANDCTR/PLAYERS;ii++)     //*3            //~v@@@I~
        {                                                          //~v@@@I~
        	for (int jj=0;jj<PLAYERS;jj++)                         //~v@@@I~
            {                                                      //~v@@@I~
            	tgtpos=HANDCTR*jj+ii*ctreach;                      //~v@@@R~
            	for (int kk=0;kk<ctreach;kk++)                     //~v@@@I~
            		tgt[tgtpos++]=src[srcpos++];                   //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        for (int jj=0;jj<PLAYERS;jj++)   //13th tile               //~v@@@I~
        {                                                          //~v@@@I~
	        tgtpos=HANDCTR*(jj+1)-1;                               //~v@@@R~
        	tgt[tgtpos]=src[srcpos++];                             //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("setInitialDeal player sequence tds:"+TileData.toString(tgt));                                               //~v@@@I~//~v@@5R~
//            for (int ii=0;ii<HANDCTR*PLAYERS;ii++)                 //~v@@@I~//~v@@5R~
//            {                                                      //~v@@@I~//~v@@5R~
//                TileData td=tgt[ii];                               //~v@@@I~//~v@@5R~
//                Dump.println("Tiles.SetInitialDeal after  type="+td.type+",no="+td.number+",red5="+td.isRed5()+",ctr="+td.ctrRemain);//~v@@@R~//~v@@5R~
//            }                                                      //~v@@@I~//~v@@5R~
//        for (int ii=0, pos=0;ii<PLAYERS;ii++)    //player is ESWN  //~v@@@R~//~v@@5R~
//        {                                                          //~v@@@R~//~v@@5R~
//            AG.aPlayers.setInitialDeal(ii,tgt,pos); //Players will sort//~v@@@R~//~v@@5R~
//            pos+=HANDCTR;                                         //~v@@@R~//~v@@5R~
//        }                                                          //~v@@@R~//~v@@5R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@5I~
        {                                                          //~v@@5I~
            int eswn=Accounts.playerToEswn(ii);                    //~v@@5I~
            AG.aPlayers.setInitialDeal(ii,tgt,eswn*HANDCTR,eswn,true/*swSort*/); //Players will sort//~v@@5R~
        }                                                          //~v@@5I~
    }                                                              //~v@@@I~
    //*************************                                    //~v@@@I~
    private TileData getTile(int Ppos)                             //~v@@@R~
    {                                                              //~v@@@I~
//  	int remains=PIECE_TILECTR-TILECTR_KEEPLEFT-ctrKan;         //~v@@@R~
    	int remains=PIECE_TILECTR-ctrKan;       //KEEPLEFT is on Top of array                   //~v@@@I~//~9225R~
        if (Dump.Y) Dump.println("Tiles.getTile remain="+remains+",Ppos="+Ppos);//~v@@@R~
        if (Ppos >= remains)	//pos start from 7*2+13*4          //~v@@@R~
        {                                                          //~v@@@I~
        	GC.actionError(0,"Tiles.getNext no more tile="+Ppos+",remains="+remains);//~v@@@R~
            return null;                                           //~v@@@I~
        }                                                          //~v@@@I~
        TileData td = shuffledTileData[Ppos];                      //~v@@@I~
        if (Ppos + 1 == remains)                                   //~v@@@I~
            td.addFlag(TDF_LAST);                                  //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.getTile pos="+Ppos+",posCurrentTile="+posCurrentTile+",td:"+td.toString());//~9225I~
        return td;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //****************************************************************//~9225R~
    //*chk haitei                                                  //~9225I~
    //****************************************************************//~9225I~
	public int chkLast(boolean PswReach)                           //~9225R~
    {                                                              //~9225I~
    	int rc;                                                    //~9225R~
    	int remains=PIECE_TILECTR-ctrKan;       //KEEPLEFT is on Top of array//~9225I~
//  	if (!PswReach || RuleSetting.isReachAvailableExceptLast()) //~9225R~//~9427R~
    	if (!PswReach)                                             //~9427I~
        	rc=posCurrentTile<remains-1 ? 0: R.string.AE_CouldNotGetLast;//~9225R~
        else                                                       //~9225I~
        	rc=posCurrentTile<remains-PLAYERS ? 0 : R.string.AE_CouldNotGetLastReach;//~9225R~
        if (Dump.Y) Dump.println("Tiles.chkLast rc="+Integer.toHexString(rc)+",cpos="+posCurrentTile+",posLast="+remains);//~9225R~
	    return rc;
    }                                                              //~9225I~
    //****************************************************************//~9225I~
    //*chk exauted                                                 //~9225I~
    //****************************************************************//~9225I~
	public boolean chkLast()                                       //~9225I~
    {                                                              //~9225I~
    	boolean rc;                                                //~9225I~
    	int remains=PIECE_TILECTR-ctrKan;       //KEEPLEFT is on Top of array//~9225I~
        rc=posNextTile>= remains;	//pos start from 7*2+13*4      //~9225R~//~9623R~
//      rc=posNextTile>  remains;	//pos start from 7*2+13*4      //~9623R~
        if (Dump.Y) Dump.println("Tiles.chkLast reached eof rc="+rc+",cpos="+posCurrentTile+",posNext="+posNextTile+",posLast="+remains);//~9225I~//~9623R~
	    return rc;                                                 //~9225I~
    }                                                              //~9225I~
    //*************************                                        //~v@@@I~
//  public TileData getNext(int Pplayer)                           //~v@@@R~
    public TileData getNext()                                      //~v@@@I~
    {                                                              //~v@@@I~
//      int next = Status.getNextTile();                           //~v@@@R~
        int next = getNextTilePos();                               //~v@@@I~
        TileData td = getTile(next);                               //~v@@@R~
        if (td==null)                                              //~v@@@I~
        	return null;                                           //~v@@@I~
//      td.setTaken(Pplayer);	//set TDF_TAKEN                    //~v@@@R~
        td.setTaken();	//set TDF_TAKEN                            //~v@@@I~
        return td;                                                 //~v@@@R~
    }                                                              //~v@@@I~
//    //*************************                                    //~9622I~//~9623R~
//    public boolean chkNextTile()                                   //~9622I~//~9623R~
//    {                                                              //~9622I~//~9623R~
//        int next = getNextTilePos();                               //~9622I~//~9623R~
//        int remains=PIECE_TILECTR-ctrKan;       //KEEPLEFT is on Top of array//~9622I~//~9623R~
//        boolean rc=!(next >= remains);  //pos start from 7*2+13*4  //~9622I~//~9623R~
//        if (Dump.Y) Dump.println("Tiles.chkNext remain="+remains+",ctrKan="+ctrKan+",nextpos="+next+",rc="+rc);//~9622I~//~9623R~
//        return rc;                                                 //~9622I~//~9623R~
//    }                                                              //~9622I~//~9623R~
    //*************************                                    //~v@@@I~
//  public TileData getNextKan(int Pplayer)                        //~v@@@R~
    public TileData getNextKan()                                   //~v@@@I~
    {                                                              //~v@@@I~
//        ctrKan++;                                                  //~v@@@R~//~v@@5R~
//        if (Dump.Y) Dump.println("Tiles.getNextKan ctrKan="+ctrKan);//~v@@@R~//~v@@5R~
//        if (ctrKan>MAXCTR_KAN)                                     //~v@@@R~//~v@@5R~
//        {                                                          //~v@@@I~//~v@@5R~
////          GC.actionError(0,Pplayer,"Tiles.getNextKan Over max 4 times Kan");//~v@@@R~//~v@@5R~
//            GC.actionError(0,"Tiles.getNextKan Over max 4 times Kan");//~v@@@I~//~v@@5R~
//            return null;                                           //~v@@@I~//~v@@5R~
//        }                                                          //~v@@@I~//~v@@5R~
        TileData td = shuffledTileData[TILECTR_KEEPLEFT-ctrKan];   //~v@@@R~
//      td.setTaken(Pplayer);	//set player and TDF_TAKEN         //~v@@@R~
//      td.setKanTaken();	//set player and TDF_KAN_TAKEN         //~v@@@I~//~v@@5R~
        td.setKanTakenRinshan();	//set player and TDF_KAN_RINSHAN|TDF_TAKEN;//~v@@5I~
        if (Dump.Y) Dump.println("Tiles.getNextKan td:"+td.toString());//~v@@@R~//~v@@5R~
        return td;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@5I~
    public int addCtrKan(boolean PswUpdate)                        //~v@@5R~
    {                                                              //~v@@5I~
        if (ctrKan==MAXCTR_KAN)                                    //~v@@5I~
        {                                                          //~v@@5I~
//      	GC.actionError(0,"Tiles.addCtrKan Over max 4 times Kan");//~v@@5I~//~0407R~
			String yn=Utils.getStr(RuleSettingYaku.is5thKanOn() ? R.string.On:R.string.Off);//~0407I~
			UserAction.showInfo(0,Utils.getStr(R.string.Err_5thKan,yn));//~0407I~
            return -1;                                             //~v@@5I~
        }                                                          //~v@@5I~
        if (PswUpdate)                                             //~v@@5I~
	    	ctrKan++;                                              //~v@@5R~
        if (Dump.Y) Dump.println("Tiles.addCtrKan swChkOnly="+PswUpdate+",current ctrKan="+ctrKan);//~v@@5R~
        return ctrKan;                                             //~v@@5I~
    }                                                              //~v@@5I~
    //******************************************************       //~v@@5I~
    public int cancelKan()                                         //~v@@5I~
    {                                                              //~v@@5I~
	    ctrKan--;                                                  //~v@@5I~
        if (Dump.Y) Dump.println("Tiles.cancelKan result="+ctrKan);//~v@@5I~
        return ctrKan;                                             //~v@@5I~
    }                                                              //~v@@5I~
    //******************************************************       //~v@@5I~
    //*from Stock.drawDora                                         //~9528R~
    //*kantype:at UAKan, 0:discard                                 //~9528I~
    //******************************************************       //~v@@5I~
    public boolean chkOpenKanDora(int Pkantype)                    //~v@@5I~
    {                                                              //~v@@5I~
        boolean swOpen=false;                                      //~v@@5I~
        if (RuleSetting.isKanDoraNo())                             //~9528I~
        {                                                          //~9528I~
	        if (Dump.Y) Dump.println("Tiles.chkOpenKandora RuleSetting kandora=no return false");//~9528I~
        	return swOpen;                                         //~9528I~
        }                                                          //~9528I~
        if (Pkantype>0)                                            //~v@@5I~
        {                                                          //~v@@5I~
        	if (Pkantype==KAN_TAKEN)	//ankan                    //~v@@5I~
            {                                                      //~v@@5I~
            	if (!swPendingOpen)//Not (kan by rishan-tile after kan-add,kan-river)//~v@@5I~
	            	swOpen=true;                                   //~v@@5R~
            }                                                      //~v@@5I~
        	else                                                   //~v@@5I~
	        if (RuleSetting.isOpenKanDoraJustNow())        //~v@@5R~//~9218R~
            	swOpen=true;                                       //~v@@5I~
            else                                                   //~v@@5I~
            	swPendingOpen=true;                                //~v@@5I~
        }                                                          //~v@@5I~
        else   //discard                                           //~9218I~
        if (Pkantype<0)	//minkan,pon,chii,take                     //~9218I~
        {                                                          //~9218I~
        	if (RuleSetting.isOpenKanDoraExceptRon())              //~9218I~
            {                                                      //~9218I~
        		swOpen=swPendingOpen;                              //~9218I~
        		swPendingOpen=false;                               //~9218I~
            }                                                      //~9218I~
        }                                                          //~9218I~
        else   //discard                                           //~v@@5I~
        {                                                          //~v@@5I~
        	if (!RuleSetting.isOpenKanDoraExceptRon())             //~v@@5I~
            {                                                      //~v@@5I~
        		swOpen=swPendingOpen;                              //~v@@5R~
        		swPendingOpen=false;                               //~v@@5R~
            }                                                      //~v@@5I~
        }                                                          //~v@@5I~
        if (Dump.Y) Dump.println("Tiles.chkOpenKandora rc="+swOpen+",ctrKan="+ctrKan+",parm kantype="+Pkantype+",swPendingOpen="+swPendingOpen);//~v@@5R~
        return swOpen;                                             //~v@@5I~
    }                                                              //~v@@5I~
    //******************************************************       //~v@@@I~
    public  int getNextTilePos()                             //~v@@@I~
    {                                                              //~v@@@I~
        posCurrentTile=posNextTile;                                //~v@@@I~
//  	if (posCurrentTile<PIECE_TILECTR-TILECTR_KEEPLEFT-ctrKan)  //~v@@@R~
    	if (posCurrentTile<PIECE_TILECTR-ctrKan)                   //~v@@@I~
	        posNextTile++;                                         //~v@@@R~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	GC.actionError(0,"Tiles.getNextTilePos reached End Of Tile="+posCurrentTile);//~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.getNextTilePos current="+posCurrentTile+",posNextTile="+posNextTile);//~v@@@R~//~9623I~
        return posCurrentTile;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    public int getCurrentTilePos()                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.posCurrentTile "+posCurrentTile);//~v@@@R~
        return posCurrentTile;                                     //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    public static void setFlag(TileData[] Ptds,int Pflag)           //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.setFlag flag=x"+Integer.toHexString(Pflag));//~v@@@I~//~v@@5R~
        for (TileData td:Ptds)                                     //~v@@@I~
	        td.flag|=Pflag;                                        //~v@@@I~
    }                                                              //~v@@@I~
//    //******************************************************     //~v@@5I~
//    public static int getPosTaken(int Pctr)                      //~v@@5I~
//    {                                                            //~v@@5I~
//        int pos;                                                 //~v@@5I~
//        if ((Pctr%PAIRCTR)==2)                                   //~v@@5I~
//            pos=Pctr-1;                                          //~v@@5I~
//        else                                                     //~v@@5I~
//            pos=0;                                               //~v@@5I~
//        if (Dump.Y) Dump.println("Tiles.getPosTaken ctr="+Pctr+",pos="+pos);//~v@@5I~
//        return pos;                                              //~v@@5I~
//    }                                                            //~v@@5I~
    //******************************************************       //~v@@5I~
    public static boolean isCtrTaken(int Pctr)                     //~v@@5I~
    {                                                              //~v@@5I~
    	boolean rc=(Pctr%PAIRCTR)==2;                              //~v@@5I~
        if (Dump.Y) Dump.println("Tiles.isCtrTaken ctr="+Pctr+",rc="+rc);//~v@@5I~
        return rc;                                                 //~v@@5I~
    }                                                              //~v@@5I~
}
