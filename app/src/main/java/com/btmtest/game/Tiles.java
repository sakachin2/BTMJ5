//*CID://+vav0R~: update#= 580;                                    //~vav0R~
//**********************************************************************//~v101I~
//2023/01/09 vav0 try xoshift as randomizer                        //~vav0I~
//2022/07/17 vap0 shuffle by Randomizing, try 34*4 for base        //~vap0R~
//2021/07/08 vaau split test deal to TilesTest.java                //~vaauI~
//2021/05/01 va8x (Test)specify robot discard tile                 //~va8xI~
//v@@5 20190126 player means position on the device                //~v@@5I~
//reset tile to new game                                           //~v@@@R~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.utils.Dump;
import com.btmtest.utils.URand;
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
//  private TileData[] shuffledTileData, baseTileData;              //~v@@@R~//~vaauR~
    public  TileData[] shuffledTileData;                           //~vaauI~
    private TileData[] baseTileData;                               //~vaauI~
    private TileData[] tileData;                                   //~v@@@I~
    private int[] typeIndex;                                       //~v@@@I~
    private boolean swUseRedTile;                                          //~v@@@I~
    private int posCut;     //cut position when deal               //~v@@@I~
    public int ctrKan;     //cut position when deal                //~v@@@R~
    private int posNextTile,posCurrentTile;                        //~v@@@I~
    private boolean swPendingOpen;                                 //~v@@5I~
    private TilesTest aTilesTest;                                  //~vaauI~
    private boolean swShuffle136=true;                             //~vap0I~
    private boolean swURand64=true;                                //~vav0I~
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
      	if (AG.isDebuggable)                                       //~vaauI~
        {                                                          //~vaauI~
          if (TestOption.aTilesTest!=null)                         //~vaauI~
		    aTilesTest=TestOption.aTilesTest;                      //~vaauI~
          else                                                     //~vaauI~
	        aTilesTest=new TilesTest();                            //~vaauI~
            aTilesTest.init(this);                                 //~vaauI~
        }                                                          //~vaauI~
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
      	if (aTilesTest!=null)                                      //~vaauI~
			if (aTilesTest.shuffleTest())	//set shuffledTileData by TestOption//~vaauR~
            	return;                                            //~vaauI~
    	URand aURand64=null;                                       //+vav0I~
        if (swURand64)                                             //+vav0M~
            aURand64=URand.initRandom64(0L/*seed:use timestamp*/,PIECE_TYPECTR/*34*/);//+vav0M~
        synchronized (baseTileData)                                 //~v@@@I~
        {                                                          //~v@@@I~
            ArrayList<TileData> al = deepCopyToArrayList();	//copy of baseTileData(34 entry)           //~v@@@R~//~9C01R~
            TileData[] out = getNewAllTile();                        //~v@@@R~
            int outctr = 0;                                          //~v@@@R~
            int typectr = PIECE_TYPECTR;                             //~v@@@I~
//          for (int ii = 0; ii < PIECE_TILECTR; ii++)                   //~v@@@R~//~1315R~
            for (;;)                                               //~1315I~
            {                                                      //~v@@@R~
//              int jj = Utils.getRandom(typectr);   //max 34-1        //~v@@@R~//~v@@5R~//~vap0R~
                int jj;                                            //~vap0I~
              if (swURand64)                                       //~vav0I~
              {                                                    //~vav0I~
                jj = aURand64.getRandom(typectr);   //max 34-1     //~vav0I~
              }                                                    //~vav0I~
              else                                                 //~vav0I~
              if (swShuffle136)                                    //~vap0I~
              {                                                    //~vap0I~
                jj=Utils.getRandom(typectr*PIECE_DUPCTR);   //max 34-1//~vap0I~
                jj/=PIECE_DUPCTR;                                  //~vap0I~
              }                                                    //~vap0I~
              else                                                 //~vap0I~
              {                                                    //~vap0I~
                jj = Utils.getRandom(typectr);   //max 34-1        //~vap0I~
              }                                                    //~vap0I~
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
            if ((TestOption.option2 & TestOption.TO2_SETDORA)!=0)//~0A12I~//~0A14R~//~vaauR~
            {                                                      //~0A12I~//~vaauR~
//              testSetDora(out);                                  //~0A12I~//~vaauR~-//~vaauR~
                if (aTilesTest!=null)                              //~vaauR~
                    aTilesTest.testSetDora(out);                   //~vaauR~
            }                                                      //~0A12I~//~vaauR~
                                                                   //~0A12I~
            shuffledTileData = out;                                  //~v@@@R~
        }//synch                                                   //~v@@@I~
        if (Dump.Y) Dump.println("Tiles.shuffle "+TileData.toString(shuffledTileData));//~v@@5R~//~0A12R~
    }                                                              //~v@@@M~
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
