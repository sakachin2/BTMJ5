//*CID://+vaaKR~: update#= 864;                                    //~vaaKR~
//**********************************************************************//~v101I~
//2021/07/14 vaaK red5 dora chk error; At getvalue from TryNext chkRedTile count tile of try discard//~vaaKI~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/06/06 va92 drop duplicated call of getRankStandard          //~va92I~
//2021/01/26 va64 (Bug)yaku:1stChildRon falsg was lost when not mix and get higher//~va64I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~va11R~
                                                                   //~va11I~
import android.graphics.Point;                                     //~va11I~
import android.graphics.Rect;

import com.btmtest.TestOption;
import com.btmtest.dialog.CompDlgDora;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.Accounts;
import com.btmtest.game.Status;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;
import com.btmtest.game.TileData;                                  //~va11I~

import java.util.Arrays;

import static com.btmtest.TestOption.*;
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Players.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UA.Pair.*;
import static com.btmtest.game.UA.Rank.*;
import static com.btmtest.game.gv.Pieces.*;
import static com.btmtest.StaticVars.AG;                           //~va11I~
                                                                   //~va11I~
public class UARonDataTree                                         //~va11R~
{                                                                  //~0914I~
	public static final int RV_YAKUMAN     =8000*4;                //~va11I~
	public static final int RV_YAKUMAN2    =RV_YAKUMAN*2;          //~va11I~
                                                                   //~va11I~
    private int type,number;                                       //~va11R~
    public  int ronType,ronNumber;                                 //~va11R~
//  private int eswn,round,total;                                  //~va11I~//~va91R~
    public  int eswn,round;                                        //~va91I~
    private int total;                                                 //~va91I~
    private UARonValue UARV;                                       //~va11I~
//  private TileData tdRon;                                        //~va11R~
    private static final int MAX_LISTPAIRED_HAND=7;                //~va11M~
    private UARonData[] uardS=new UARonData[MAX_LISTPAIRED_HAND];  //~va11R~
    private int ctrUardS;                                          //~va11R~
    private static final int MAX_LIST_YAKUMAN=8;                  //~va11I~
//  private int[] listYakuman=new int[MAX_LIST_YAKUMAN];           //~va11R~
    private int rankYakuman;                                       //~va11R~
    private Rank longRankYakuman;                                  //~va11I~
    private int amt7Pair,rank7Pair,point7Pair;                     //~va11R~
	private int player;                                            //~va11I~
    private static final int MAX_YAKU_LIST=50;                     //~va11I~
    private int[] listYaku=new int[MAX_YAKU_LIST];                 //~va11I~
    private int ctrListYaku;                                       //~va11I~
    private boolean swRuleTanyaoEarth;                             //~va11R~
    public  boolean swTanyao,swHonor;                              //~va11R~
    public  boolean swRuleRankMUp;                                 //~va11I~
    public  boolean swRuleDoublePillowPoint;                       //~va11I~
    private int amtTotal,rankHonor;                                //~va11R~
//  public  int rankTanyao;                                        //~va11R~
    private int rankSpecial,amtSpecial,yakuSpecial,rankFinal,pointFinal;//~va11R~
    private int rankFixErrFinal;                                   //~va91I~
    private int rankOther;	//dora,reach,..                        //~va11R~
    private int[][] dupCtrAll,dupCtr;                              //~va11I~
    private UARonData maxUARD;                                      //~va11I~
    int typeMaxUARD;                                               //~va11I~
    private static final int TYPE_MAX_UARD_STANDARD=0;             //~va11I~
    private static final int TYPE_MAX_UARD_7PAIR=1;                //~va11I~
    private static final int TYPE_MAX_UARD_SPECIAL=2;              //~va11I~
    public UARank aUARank;                                         //~va11R~
    public Rank longRankOther/*dora,honor*/,longRankFinal,longRankSpecial/*renho*/;//~va11R~
    public Rank longRankFixErrFinal;                               //~va91I~
    public Rank longRank7/*7pair*/;                                //~va11I~
    public boolean swTaken,swAllInHand;                            //~va11R~
    public boolean swNoMixSpecial;                                 //~va11I~
    public int rankDora;                                           //~va11I~
    private int[] itsDoraOpen;                                     //~va60I~
    private int   ctrDoraOpen;                                     //~va60I~
	//*************************************************************************//~va11I~
    public UARonDataTree(UARonValue PuaRonValue)                   //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("UARonDataTree.Constructor");     //~va60I~
        UARV=PuaRonValue;                                          //~va60I~
//      UARDT=new UARonDataTree(this);	//chk dora                 //~va60I~
    }                                                              //~va60I~
//  public UARonDataTree(UARonValue PuaRonValue,int Pplayer,int[][] PdupCtr)//~va11R~//~va60R~
    public void initInstance(int Pplayer,int[][] PdupCtr)          //~va60I~
    {                                                              //~va11I~
//      UARV=PuaRonValue;                                          //~va11R~//~va60R~
//      tdRon=PtdRon;                                              //~va11R~
        player=Pplayer;                                            //~va11I~
        dupCtr=PdupCtr;                                            //~va11I~
        init();                                                    //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.Constructor player="+Pplayer);//~va11R~
    }                                                              //~va11I~
    private void init()                                            //~va11I~
    {                                                              //~va11I~
        swTaken=UARV.swTaken;                                      //~va11I~
        swAllInHand=UARV.swAllInHand;                              //~va11R~
        aUARank=new UARank(UARV);                                  //~va11M~
        longRankYakuman=new Rank();	//for yakuman                  //~va11I~
    	initRule();                                                //~va11I~
    	longRankOther=new Rank();                                      //~va11I~
    	longRankSpecial=new Rank();                                //~va11I~
        ronType=UARV.ronType;                                      //~va11R~
        ronNumber=UARV.ronNumber;                                  //~va11R~
        eswn= Accounts.playerToEswn(player);                        //~va11I~
        Rect gamectr= Status.getGameSeq();                          //~v@@@R~//~va11I~
        round=gamectr.left; //setCtr                                  //~v@@@I~//~va11R~
        dupCtrAll=UARV.dupCtrAll;                                  //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.init dupCtrAll="+Utils.toString(dupCtrAll));//~va11I~
        rankDora=chkDora();                                        //~va11R~
//  	chkTanyao();                                               //~va11R~
    	swHonor=isHavingHonor();                                   //~va11R~
    	rankHonor=chkHonorRank();                                                 //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.init ronType="+ronType+",ronNumber="+ronNumber+",player="+player+",eswn="+eswn+",round="+round);//~va11R~
    }                                                              //~va11I~
    private void initRule()                                        //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.initRule");        //~va11I~
        swRuleTanyaoEarth= RuleSettingYaku.isTanyaoEarth();         //~va11I~
        swRuleDoublePillowPoint= RuleSettingYaku.isDoublePillowPoint();//~va11I~
    	swRuleRankMUp= RuleSetting.isRankMUp();   //kiriage mangan  //~va11I~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    public UARonData addPillow(Point Ppillow)                      //~va11I~
    {                                                              //~va11I~
        UARonData uard=new UARonData(PT_DUMMY_TOP,Ppillow.x/*type*/,Ppillow.y/*number*/,0/*dupCtr*/);//~va11I~
        uardS[ctrUardS++]=uard;                                    //~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.addPillow ctr="+ctrUardS+",pillow="+Ppillow.toString());//~va11R~
        return uard;                                               //~va11I~
    }                                                              //~va11I~
    public void delPillow()                                        //~va11I~
    {                                                              //~va11I~
        uardS[ctrUardS--]=null;                                    //~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.delPillow ctr="+ctrUardS);//~va11R~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    public int getAmmount(RonResult PronValue,boolean PswMadePair) //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.getAmmount player="+player+",eswn="+eswn+",round="+round+",swMade7Pair="+PswMadePair);//~va11R~
	    typeMaxUARD=TYPE_MAX_UARD_STANDARD;                        //~va11R~
        int amt;                                                   //~va11I~
        if (isYakuman())                                           //~va11R~
        {                                                          //~va11I~
        	amt=rankYakuman*RV_YAKUMAN;                            //~va11I~
	    	rankFinal=rankYakuman;                                 //~va11I~
            pointFinal=0;                                          //~va11I~
            longRankFinal=longRankYakuman;                         //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
//        if (swNoMixSpecial) //renho could not mix with other yaku//~va11R~
//        {                                                        //~va11R~
//            amt=amtSpecial;                                      //~va11R~
//            rankFinal=rankSpecial;                               //~va11R~
//            longRankFinal=longRankSpecial;                       //~va11R~
//            pointFinal=0;                                        //~va11R~
//        }                                                        //~va11R~
//        else                                                     //~va11R~
        {                                                        //~va11R~
            amt=0;                                                 //~va11I~
//          int rankBase=rankHonor+rankTanyao+rankOther;           //~va11R~
//          int rankBase=rankHonor+rankOther;                      //~va11R~
            int rankBase=rankHonor+rankOther+rankDora;             //~va11I~
            if (!swNoMixSpecial)                                   //~va11I~
                rankBase+=rankSpecial;  //mix rank of renho,0 if not renho//~va11I~
			if (Dump.Y) Dump.println("UARonDataTree.getAmmount rankBase="+rankBase+",rankHonor="+rankHonor+",rankOther="+rankOther+",rankDora="+rankDora);//~va11R~
			if (Dump.Y) Dump.println("UARonDataTree.getAmmount ctrUardS="+ctrUardS+",UARV.sw7P="+UARV.sw7P);//~va11R~
			if (Dump.Y) Dump.println("UARonDataTree.getAmmount longRankOther="+longRankOther.toStringName());//~va11I~
            if (UARV.sw7P)                                         //~va11R~
            {                                                      //~va11I~
		    	Rank rank7=new Rank();                             //~va11R~
		    	rank7.mix(longRankOther);   //mix tanyao           //~va11R~
				int rank7P=aUARank.setRank7Pair(rank7);  //addYaku to rank7//~va11R~
            	UARV.evaluate7Pair(rankBase+rank7P,rank7);//callback setValue7Pair//~va11R~
                if (!PswMadePair) //makepair failed                //~va11R~
                {                                                  //~va11I~
//              	longRank7.mix(longRankOther);                  //~va11R~
//                  longRankFinal=longRank7;                       //~va11R~
					if (Dump.Y) Dump.println("UARonDataTree.getAmmount 7Pair only longRank7="+longRank7.toStringName(true));//~va11R~
    				return getAmmount7Pair(PronValue);                    //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
            for (int ii=0;ii<ctrUardS;ii++)                        //~va11R~
            {                                                      //~va11R~
                UARonData uard=uardS[ii];                          //~va11R~
//              uard.makePattern();                                //~va11R~
                uard.makePattern(UARV.pairEarth);                  //~va11I~
//  			aUARank.getRankStandard(this,uard); getRankStandard will be called at uard.getAmmount//~va11R~//~va92R~
                int v=0;                                           //~va11I~
	            v=uard.getAmmount(this,player,eswn,round,rankBase);//~va11R~
		        if (isYakuman()) //now may include kazoeyakuman    //~va11M~
                {                                                  //~va11M~
                	maxUARD=uard;                                  //~va11M~
        			amt=rankYakuman*RV_YAKUMAN;                    //~va11M~
                    rankFinal=uard.intRankMax;                     //~va11R~
                    pointFinal=uard.pointMax;                      //~va11I~
                    longRankFinal=uard.longRankMax;                //~va11I~
			        if (Dump.Y) Dump.println("UARonDataTree.getAmmount RYAKU_BYRANK amt="+amt+",rankFinal="+rankFinal+",pontFinal="+pointFinal+",longRankFinal="+Rank.toString(longRankFinal)+"="+Rank.toStringName(longRankFinal));//~va11I~
                    break;                                         //~va11M~
                }                                                  //~va11M~
                if (v>=amt)                                        //~va11R~
                {                                                  //~va11I~
                	maxUARD=uard;                                  //~va11I~
                	amt=v;                                         //~va11R~
                    rankFinal=uard.intRankMax;                        //~va11R~
                    rankFixErrFinal=uard.intRankFixErrMax;         //~va91I~
                    pointFinal=uard.pointMax;                      //~va11R~
                    longRankFinal=uard.longRankMax;                //~va11I~
                    longRankFixErrFinal=uard.longRankFixErrMax;    //~va91I~
			        if (Dump.Y) Dump.println("UARonDataTree.getAmmount swChkRank="+UARV.swChkRank+",v="+v+",amt="+amt+",rankFinal="+rankFinal+",rankFixErrFinal"+rankFixErrFinal+",pontFinal="+pointFinal+",longRankFinal="+Rank.toString(longRankFinal)+"="+Rank.toStringName(longRankFinal)+",longRankFixErrDinal="+Rank.toStringName(longRankFixErrFinal));//~va11R~//~va91R~
                    if (UARV.swChkRank && longRankFinal.isContainsAnyYakuExceptDora())//~va11R~
                    	break;                                     //~va11I~
                }                                                  //~va11I~
                                                                   //~va11I~
            }//for                                                 //~va11R~
		    if (Dump.Y) Dump.println("UARonDataTree.getAmmount amt7Pair="+amt7Pair+",amt="+amt);//~va11I~
            if (amt7Pair>amt)                                      //~va11I~
            {                                                      //~va11I~
		        if (Dump.Y) Dump.println("UARonDataTree.getAmmount selected 7pair amt="+amt+",amt7Pair="+amt7Pair+",rank7Pair="+rank7Pair+",point7Pair="+point7Pair);//~va11R~
            	amt=amt7Pair;                                      //~va11I~
            	rankFinal=rank7Pair;                               //~va11R~
                pointFinal=point7Pair;                             //~va11R~
                addYakuOther(RYAKU_7PAIR);                         //~va11R~
			    typeMaxUARD=TYPE_MAX_UARD_7PAIR;                   //~va11I~
            }                                                      //~va11I~
		    if (Dump.Y) Dump.println("UARonDataTree.getAmmount swNoMixSpecial="+swNoMixSpecial+",amtSpecial="+amtSpecial+",amt="+amt);//~va11I~
			if (swNoMixSpecial && amtSpecial>amt)//renho           //~va11R~
            {                                                      //~va11I~
            	//TODO test renho+other mix                        //~va11I~
		        if (Dump.Y) Dump.println("UARonDataTree.getAmmount selected special amt="+amt+",amtSpecial="+amtSpecial+",rankSpecial="+rankSpecial+",longRankSpecial="+longRankSpecial);//~va11R~//~va64R~
            	amt=amtSpecial;                                    //~va11I~
            	rankFinal=rankSpecial;                             //~va11R~
            	longRankFinal=longRankSpecial;                     //~va64I~
                pointFinal=0;                                      //~va11R~
//              addYakuOther(yakuSpecial);//renho not Yakuman      //~va11R~
			    typeMaxUARD=TYPE_MAX_UARD_SPECIAL;                 //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        amtTotal=amt;                                              //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.getAmmount return rankYakuman="+rankYakuman+",amtTotal="+amtTotal+",rankFinal="+rankFinal+",rankFixErrFinal="+rankFixErrFinal+",pontFinal="+pointFinal+",longRankFinal="+Rank.toString(longRankFinal)+"="+Rank.toStringName(longRankFinal)+".longRankFixErrFinal="+Rank.toStringName(longRankFixErrFinal));//~va11R~//~va91R~
	    setResult(PronValue);                                      //~va11I~
        return amtTotal;                                           //~va11R~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    //*no other standard rank                                      //~va11I~
    //***************************************************************************//~va11I~
    private int getAmmount7Pair(RonResult PronValue)               //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.getAmmount7Pair player="+player+",eswn="+eswn+",round="+round);//~va11R~
	    typeMaxUARD=TYPE_MAX_UARD_STANDARD;                        //~va11R~
        int amt;                                                   //~va11I~
        longRankFinal=new Rank();	//later longRankOther will be added//~va11I~
        if (longRankYakuman.isYakumanExceptByRank())               //~va11I~
        {                                                          //~va11I~
        	amt=rankYakuman*RV_YAKUMAN;                            //~va11I~
	    	rankFinal=rankYakuman;                                 //~va11I~
            pointFinal=0;                                          //~va11I~
            longRankFinal=longRankYakuman;                         //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
        {                                                          //~va11I~
//            if (amt7Pair>amt)                                    //~va11I~
//            {                                                    //~va11I~
		        if (Dump.Y) Dump.println("UARonDataTree.getAmmount7Pair amt7Pair="+amt7Pair+",rank7Pair="+rank7Pair+",point7Pair="+point7Pair);//~va11I~
            	amt=amt7Pair;                                      //~va11I~
            	rankFinal=rank7Pair;                               //~va11I~
                pointFinal=point7Pair;                             //~va11I~
//              addYakuOther(RYAKU_7PAIR);                         //~va11R~
                longRankFinal=longRank7;                           //~va11I~
			    typeMaxUARD=TYPE_MAX_UARD_7PAIR;                   //~va11I~
//            }                                                    //~va11I~
		    if (Dump.Y) Dump.println("UARonDataTree.getAmmount swNoMixSpecial="+swNoMixSpecial+",amtSpecial="+amtSpecial+",amt="+amt);//~va11I~
			if (swNoMixSpecial && amtSpecial>amt)//renho           //~va11R~
            {                                                      //~va11I~
            	//TODO test renho+other mix                        //~va11I~
		        if (Dump.Y) Dump.println("UARonDataTree.getAmmount selected special amt="+amt+",amtSpecial="+amtSpecial+",rankSpecial="+rankSpecial);//~va11I~
            	amt=amtSpecial;                                    //~va11I~
            	rankFinal=rankSpecial;                             //~va11I~
                longRankFinal=longRankSpecial;                         //~va11I~
                pointFinal=0;                                      //~va11I~
			    typeMaxUARD=TYPE_MAX_UARD_SPECIAL;                 //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        amtTotal=amt;                                              //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.getAmmount7Pair return rankYakuman="+rankYakuman+",amtTotal="+amtTotal+",rankFinal="+rankFinal+",pontFinal="+pointFinal+",longRankFinal="+longRankFinal.toStringName(true));//~va11R~
	    setResult(PronValue);                                      //~va11I~
        return amtTotal;                                           //~va11I~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    public void setResult(RonResult Presult)                       //~va11R~
    {                                                              //~va11I~
    	Presult.amt=amtTotal;                                      //~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.setResult longRankFinal="+longRankFinal.toString()+",longRankOther="+longRankOther+",longRankYakuman="+longRankYakuman.toString());//~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.setResult swNoMixSpecial="+swNoMixSpecial);//~va11I~
        if (!longRankFinal.isYakumanExceptByRank())                //~va11R~
        {                                                          //~va11I~
			if (typeMaxUARD!=TYPE_MAX_UARD_SPECIAL)                //~va11I~
            {                                                      //~va11I~
	        	longRankFinal.mix(longRankYakuman); //BYRANK:kazoe yakuman//~va11R~
	        	longRankFinal.mix(longRankOther);                  //~va11R~
	        	longRankFinal.mix(longRankSpecial);                //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        Presult.longRank=longRankFinal;                            //~va11I~
        Presult.longRankFixErr=longRankFixErrFinal;                //~va91I~
    	Presult.han=rankFinal;                                     //~va11R~
    	Presult.hanFixErr=rankFixErrFinal;                         //~va91I~
	    Presult.point=pointFinal;	//roundup by 10 if not 7Pair   //~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.setResult pointFinal="+pointFinal+",PronResult="+Presult.toString()+",longRank="+longRankFinal.toStringName()+",longRankFixErrFinal="+Rank.toStringName(longRankFixErrFinal));//~va11R~//~va91R~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    //*from UARonValue                                             //~va11I~
    //***************************************************************************//~va11I~
    public void setResultYakuman(RonResult PronResult)            //~va11I~
    {                                                              //~va11I~
        PronResult.amt=rankYakuman*RV_YAKUMAN;                     //~va11I~
	    PronResult.han=rankYakuman;                          //~va11I~
        PronResult.point=0;                                   //~va11I~
        PronResult.longRank=longRankYakuman;                  //~va11I~
         if (Dump.Y) Dump.println("UARonDataTree.setResultYakuman PronResult="+PronResult.toString());//~va11I~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    public void addYakuman(int Pyaku,boolean PswDouble)             //~va11I~
    {                                                              //~va11I~
        if (Pyaku==RYAKU_BYRANK)                                   //~va11I~
        {                                                          //~va11I~
            if (!Rank.isYakuman(longRankYakuman))                                //~va11I~
            {                                                      //~va11I~
		        Rank.addYaku(longRankYakuman,Pyaku);                        //~va11I~
		        rankYakuman=1;                                     //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
        {                                                          //~va11I~
            if (Rank.isContains(longRankYakuman,RYAKU_BYRANK))                           //~va11I~
            {                                                      //~va11I~
            	Rank.reset(longRankYakuman,RYAKU_BYRANK);               //~va11I~
                rankYakuman--;                                     //~va11I~
            }                                                      //~va11I~
        	Rank.addYaku(longRankYakuman,Pyaku);                            //~va11R~
	        rankYakuman+=PswDouble ? 2 : 1;                        //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.addYakuman rankYakuman="+rankYakuman+",longRankYakuman="+longRankYakuman.toString());//~va11R~
    }                                                              //~va11I~
    //***************************************************************************//~va11M~
    public boolean isYakuman()                                     //~va11M~
    {                                                              //~va11M~
        boolean rc=rankYakuman!=0;                                 //~va11M~
        if (Dump.Y) Dump.println("UARonDataTree.isYakuman rc="+rc+",rankYakuman="+rankYakuman+",longRankYakuman="+Rank.toString(longRankYakuman));//~va11R~
        return rc;                                                 //~va11M~
    }                                                              //~va11M~
    //***************************************************************************//~va11I~
    public void addYakuOther(int Pyaku)                            //~va11R~
    {                                                              //~va11I~
        longRankOther.addYaku(Pyaku);                              //~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.addYakuOther Pyaku="+Pyaku+",longRank="+Rank.toString(longRankOther));//~va11R~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    private boolean addYakuOtherWithCtr(int Pyaku,int Pctr)        //~va11R~
    {                                                              //~va11I~
    	boolean rc=false;                                          //~va11I~
//  	if (Pctr>=MIN_RANK_YAKUMAN)         //over by dora         //~va11R~
    	if (Pctr>=MIN_RANK_YAKUMAN && RuleSettingYaku.isYakumanByRank())//~va11I~
        {                                                          //~va11I~
    		addYakuman(RYAKU_BYRANK,false/*not double*/);           //~va11I~
            rc=true;                                               //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
//          Rank.addYaku(longRankOther,Pyaku,Pctr);                //~va11R~
            longRankOther.addYaku(Pyaku,Pctr);    //at ReqDlg dcrese amount up to Mangan*3//~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.addYakuOtherWithCtr rc="+rc+",Pyaku="+Pyaku+",ctr="+Pctr+",longRank="+Rank.toString(longRankOther));//~va11R~
        return rc;
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    //*tankayomwind,round, 7pair(if finally selected)              //~va11I~
    //***************************************************************************//~va11I~
    public void addYakuOther(int Pyaku,int Prank)                  //~va11R~
    {                                                              //~va11I~
        addYakuOther(Pyaku);                                       //~va11R~
        rankOther+=Prank;                                          //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.addYakuOther Pyaku="+Pyaku+",rank="+Prank+",rankOther="+rankOther+",listYaku="+listYaku.toString());//~va11R~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    //* from UARV addTimingYakuman when renho is not yakuman       //~va11I~
    //***************************************************************************//~va11I~
    public void addSpecialValue(int Pyaku,int Prank,int Pamt)      //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.addSpecialValue yaku="+Pyaku+",rank="+Prank+",amt="+Pamt);//~va11I~
        if (Pyaku==RYAKU_CHILDRON_NY)                              //~va11R~
			swNoMixSpecial=!RuleSettingYaku.isMixRank1stChildRon();//~va11R~
    	yakuSpecial=Pyaku; rankSpecial=Prank; amtSpecial=Pamt;     //~va11I~
        longRankSpecial.addYaku(Pyaku);                            //~va11R~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    //*from UARV.evaluate7Pait                                     //~va11I~
    //***************************************************************************//~va11I~
    public void setValue7Pair(int Pamt,int Prank,int Ppoint,Rank PlongRank7)//~va11R~
    {                                                              //~va11I~
        amt7Pair=Pamt; rank7Pair=Prank; point7Pair=Ppoint;         //~va11R~
        longRank7=PlongRank7;                                      //~va11I~
        longRank7.addYaku(RYAKU_7PAIR);                                //~va11I~
        if (Prank>=MIN_RANK_YAKUMAN && RuleSettingYaku.isYakumanByRank())//~va11R~
        {                                                          //~va11R~
            addYakuman(RYAKU_BYRANK,false);                        //~va11R~
        }                                                          //~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.setValue7Pair amt="+Pamt+",rank="+Prank+",point="+Ppoint+",longRank7="+longRank7.toStringName(true));//~va11R~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public boolean chkTanyao()                                     //~va11I~
    {                                                              //~va11I~
    	boolean rc=true;                                           //~va11I~
        for (int jj=0;jj<PIECE_NOTNUM_CTR;jj++)                    //~va11I~
            if (dupCtrAll[TT_JI][jj]!=0)                           //~va11I~
            {                                                      //~va11I~
		        if (Dump.Y) Dump.println("UARonDataTree.chkTanyao not tankayo jj="+jj);//~va11I~
                rc=false;                                          //~va11I~
                break;                                             //~va11I~
            }                                                      //~va11I~
        if (rc)                                                    //~va11I~
            for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)             //~va11I~
            {                                                      //~va11I~
                if (dupCtrAll[ii][0]!=0 || dupCtrAll[ii][8]!=0)    //~va11R~
	            {                                                  //~va11I~
			        if (Dump.Y) Dump.println("UARonDataTree.chkTanyao not tankayo ii="+ii);//~va11I~
    	            rc=false;                                      //~va11I~
        	        break;                                         //~va11I~
            	}                                                  //~va11I~
            }                                                      //~va11I~
        swTanyao=rc;                                               //~va11I~
        if (rc)                                                    //~va11I~
        {                                                          //~va11I~
        	if (swRuleTanyaoEarth || UARV.swAllInHand)             //~va11R~
            	addYakuOther(RYAKU_TANYAO,RANK_TANYAO);            //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.chkTanyao rc="+rc+",swRuleTanyao="+swRuleTanyaoEarth+",swAllInHand="+UARV.swAllInHand+",dupctrAll="+Utils.toString(dupCtrAll));//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private int chkHonorRank()                                     //~va11I~
    {                                                              //~va11I~
    	int rc=0;                                                  //~va11I~
        int ctrWGR=0;                                              //~va11I~
        for (int jj=0;jj<PIECE_NOTNUM_CTR;jj++)                    //~va11I~
            if (dupCtrAll[TT_JI][jj]>=3)                           //~va11I~
            {                                                      //~va11I~
                if (jj>=TT_4ESWN_CTR)                              //~va11R~
                {                                                  //~va11I~
                	rc+=RANK_WGR;                                  //~va11R~
                    ctrWGR++;                                      //~va11I~
                }                                                  //~va11I~
                else                                               //~va11I~
                {                                                  //~va11I~
                	if (jj==eswn)                                  //~va11I~
                    {                                              //~va11I~
				        addYakuOther(RYAKU_WIND);                  //~va11R~
                    	rc+=RANK_WIND;                             //~va11R~
                    }                                              //~va11I~
                	if (jj==round)                                 //~va11I~
                    {                                              //~va11I~
				        addYakuOther(RYAKU_ROUND);                 //~va11R~
                    	rc+=RANK_ROUND;                            //~va11R~
                    }                                              //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
        if (ctrWGR!=0)                                             //~va11I~
	        addYakuOtherWithCtr(RYAKU_CTR_WGR,ctrWGR);             //~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.chkHonorRank rc="+rc+",eswn="+eswn+",round="+round+",jihai="+ Arrays.toString(dupCtrAll[TT_JI]));//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private boolean isHavingHonor()                            //~va11I~
    {                                                              //~va11I~
    	boolean rc=false;                                          //~va11I~
        for (int jj=0;jj<PIECE_NOTNUM_CTR;jj++)                    //~va11I~
        {                                                          //~va11I~
            if (dupCtrAll[TT_JI][jj]==0)                           //~va11I~
                continue;                                          //~va11I~
            if (jj>=TT_4ESWN_CTR)                                  //~va11R~
                rc=true;                                           //~va11I~
            else                                                   //~va11I~
            {                                                      //~va11I~
                if (jj==eswn)                                      //~va11I~
                    rc=true;                                       //~va11I~
                if (jj==round)                                     //~va11I~
                    rc=true;                                          //~va11I~
            }                                                      //~va11I~
            if (rc)                                                //~va11I~
            	break;                                             //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.isHavingHonor rc="+rc+",eswn="+eswn+",round="+round);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private int chkDora()                                          //~va11I~
    {                                                              //~va11I~
    	int type,num,ctrUp=0,ctrDown=0,ctrKanUp=0,ctrKanDown=0;    //~va11I~
        //**********************************                       //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.chkDora player="+player+",swEmulation="+UARV.swEmulation);//~va60I~
        if ((TestOption.option2 & TO2_RONVALUE_NODORA)!=0)          //~va11I~
        	return 0;                                              //~va11I~
//      if (UARV.itsDoraOpen!=null)                                //~va60R~
        if (UARV.swEmulation)                                      //~va60I~
        {                                                          //~va60I~
		    return chkDoraOpen(UARV.itsDoraOpen,UARV.ctrDoraOpen); //~va60I~
        }                                                          //~va60I~
        ctrDoraOpen=0;                                             //~va60I~
    	int[] dora= CompDlgDora.getDoraTiles();	//{upper.type,upper.number,lower.type,lower.number}//~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.chkDora stock="+Arrays.toString(dora));//~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.chkDora dupCtrAll="+Utils.toString(dupCtrAll));//~va11R~
    //*upper                                                       //~va11I~
        type=dora[0]; num=dora[1];                                 //~va11I~
        num=getNextDora(RuleSetting.isDoraNext(),type,num);        //~va11I~
        ctrUp=dupCtrAll[type][num];  //ron tile is containd        //~va11R~
        if (itsDoraOpen!=null)                                     //~va60I~
        {                                                          //~va60I~
            itsDoraOpen[ctrDoraOpen++]=type;                       //~va60I~
            itsDoraOpen[ctrDoraOpen++]=num;                        //~va60I~
        }                                                          //~va60I~
    //*lower                                                       //~va11I~
        int styleUnder=RuleSetting.getStyleHiddenDora();       //~va11R~
//      boolean swReach=AG.aPlayers.getReachStatus(PLAYER_YOU)==REACH_DONE;//~va11I~//~va60R~
        boolean swReach=AG.aPlayers.getReachStatus(player)==REACH_DONE;//~va60I~
        if (swReach)                                               //~va11I~
        {                                                          //~va11I~
            type=dora[2]; num=dora[3];                             //~va11R~
            if (styleUnder!=DORA_HIDDEN_NO)                        //~va11R~
            {                                                      //~va11R~
                num=getNextDora(styleUnder==DORA_HIDDEN_NEXT,type,num);//~va11R~
                ctrDown=dupCtrAll[type][num];                      //~va11R~
            }                                                      //~va11R~
        }                                                          //~va11I~
    //*kan                                                         //~va11I~
        int styleKanUpper=RuleSetting.getStyleKanDora();           //~va11I~
        int styleKanLower=RuleSetting.getStyleKanDoraHidden();     //~va11I~
    	for (int ii=4;ii<dora.length;ii+=4)                           //~va11I~
        {                                                          //~va11I~
        	if (styleKanUpper!=DORA_KANDORA_NO)                    //~va11I~
            {                                                      //~va11I~
	        	type=dora[ii]; num=dora[ii+1];                     //~va11I~
                if (type>=0)                                       //~va11I~
                {                                                  //~va11I~
	     	    	num=getNextDora(styleKanUpper==DORA_KANDORA_NEXT,type,num);//~va11I~
        			ctrKanUp+=dupCtrAll[type][num];                  //~va11I~
                    if (itsDoraOpen!=null)                         //~va60I~
                    {                                              //~va60I~
                        itsDoraOpen[ctrDoraOpen++]=type;           //~va60I~
                        itsDoraOpen[ctrDoraOpen++]=num;            //~va60I~
                    }                                              //~va60I~
                }                                                  //~va11I~
            }                                                      //~va11I~
	        if (swReach)                                           //~va11I~
                if (styleKanLower!=DORA_KANDORA_HIDDEN_NO)         //~va11R~
                {                                                  //~va11R~
                    type=dora[ii+2]; num=dora[ii+3];               //~va11R~
                    if (type>=0)                                   //~va11R~
                    {                                              //~va11R~
                        num=getNextDora(styleKanLower==DORA_KANDORA_HIDDEN_NEXT,type,num);//~va11R~
                        ctrKanDown+=dupCtrAll[type][num];          //~va11R~
                    }                                              //~va11R~
                }                                                  //~va11R~
        }                                                          //~va11I~
        int rc=ctrUp+ctrDown+ctrKanUp+ctrKanDown;                               //~va11I~
        rc+=chkRedTile();                                      //~va11I~
        if (rc!=0)                                                 //~va11I~
			addYakuOtherWithCtr(RYAKU_CTR_DORA,rc);                //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.chkDora style under="+styleUnder+",kanUp="+styleKanUpper+",kanLower="+styleKanLower);//~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.chkDora ctr="+rc+",ctrUp="+ctrUp+",ctrDown="+ctrDown+",ctrKanUp="+ctrKanUp+",ctrKanDown="+ctrKanDown);//~va11R~
        if (Dump.Y) Dump.println("UARonDataTree.chkDora itsDoraOpen="+Arrays.toString(itsDoraOpen));//~va60I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va60I~
	//*when swEmulation                                            //~vaaKI~
	//************************************************************************//~vaaKI~
    private int chkDoraOpen(int[] PitsDoraOpen,int PctrDoraOpen)    //~va60I~
    {                                                              //~va60I~
        //**********************************                       //~va60I~
        int ctrUp=0,type,num;                                               //~va60I~
    	int[] dora=PitsDoraOpen;                                   //~va60I~
        if (Dump.Y) Dump.println("UARonDataTree.chkDoraOpen dora="+Utils.toString(dora,-1,PctrDoraOpen*2));//~va60I~
        if (Dump.Y) Dump.println("UARonDataTree.chkDora dupCtrAll="+Utils.toString(dupCtrAll));//~va60I~
    	for (int ii=0;ii<PctrDoraOpen;ii++)                        //~va60I~
        {                                                          //~va60I~
    	    type=dora[ii+ii]; num=dora[ii+ii+1];                   //~va60I~
        	ctrUp+=dupCtrAll[type][num];  //ron tile is containd   //~va60I~
        }                                                          //~va60I~
        int rc=ctrUp;                                              //~va60I~
        rc+=chkRedTile();                                          //~va60I~
        if (rc!=0)                                                 //~va60I~
			addYakuOtherWithCtr(RYAKU_CTR_DORA,rc);                //~va60I~
        if (Dump.Y) Dump.println("UARonDataTree.chkDoraOpen rc="+rc);//~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
	//*************************************************************************//~va60M~
	//*from RoundStat,get exposed dora                             //~va60M~
	//*output:[type,num],retrn dora ctr                            //~va60I~
	//*************************************************************************//~va60M~
    public  int getDoraOpen(int[] PitsDoraOpen/*max 2*(1+4) entry*/)//~va60I~
    {                                                              //~va60I~
    	int type,num;                                              //~va60I~
        //**********************************                       //~va60I~
        ctrDoraOpen=0;                                             //~va60I~
    	int[] dora= CompDlgDora.getDoraTiles();	//{upper.type,upper.number,lower.type,lower.number}//~va60I~
        if (Dump.Y) Dump.println("UARonDataTree.getDoraOpen dora="+Arrays.toString(dora));//~va60R~
        if (Dump.Y) Dump.println("UARonDataTree.getDoraOpen dupCtrAll="+Utils.toString(dupCtrAll));//~va60R~
    //*upper                                                       //~va60I~
        type=dora[0]; num=dora[1];                                 //~va60I~
        num=getNextDora(RuleSetting.isDoraNext(),type,num);        //~va60I~
        PitsDoraOpen[ctrDoraOpen++]=type;                          //~va60I~
        PitsDoraOpen[ctrDoraOpen++]=num;                           //~va60I~
    //*kan                                                         //~va60I~
        int styleKanUpper=RuleSetting.getStyleKanDora();           //~va60I~
    	for (int ii=4;ii<dora.length;ii+=4)                        //~va60I~
        {                                                          //~va60I~
        	if (styleKanUpper!=DORA_KANDORA_NO)                    //~va60I~
            {                                                      //~va60I~
	        	type=dora[ii]; num=dora[ii+1];                     //~va60I~
                if (type>=0)                                       //~va60I~
                {                                                  //~va60I~
	     	    	num=getNextDora(styleKanUpper==DORA_KANDORA_NEXT,type,num);//~va60I~
                    PitsDoraOpen[ctrDoraOpen++]=type;              //~va60I~
                    PitsDoraOpen[ctrDoraOpen++]=num;               //~va60I~
                }                                                  //~va60I~
            }                                                      //~va60I~
        }                                                          //~va60I~
        int rc=ctrDoraOpen/2;                                      //~va60I~
        if (Dump.Y) Dump.println("UARonDataTree.getDoraOpen rc="+rc+",PitsDoraOpen="+Utils.toString(PitsDoraOpen));//~va60R~//+vaaKR~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
	//*************************************************************************//~va11I~
    private int getNextDora(boolean PswNext,int Ptype,int Pnum)    //~va11I~
    {                                                              //~va11I~
    	int num=Pnum;                                              //~va11I~
        if (PswNext)                                               //~va11I~
        {                                                          //~va11I~
            if (Ptype==TT_JI)                                      //~va11I~
            {                                                      //~va11I~
                if (Pnum==TT_4ESWN_CTR-1)                          //~va11I~
                    num=0;                                         //~va11I~
                else                                               //~va11I~
                if (Pnum==TT_4ESWN_CTR+TT_3WGR_CTR-1)              //~va11I~
                    num=TT_4ESWN_CTR;                             //~va11I~
                else                                               //~va11I~
                	num++;                                         //~va11I~
            }                                                      //~va11I~
            else                                                   //~va11I~
            {                                                      //~va11I~
                if (Pnum==PIECE_NUMBERCTR-1)                       //~va11I~
                    num=0;                                         //~va11I~
                else                                               //~va11I~
                	num++;                                         //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.getNextDora rc="+num+",type="+Ptype+",num="+Pnum+",swNext="+PswNext);//~va11I~
        return num;    	                                           //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private int chkRedTile()                                       //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.chkRedTile swUseRedTile="+AG.aRule.swUseRedTile);//~va11I~
        if (!AG.aRule.swUseRedTile)                                //~va11R~
        	return 0;                                                //~va11I~
        int ctrRed5=0;                                             //~va11I~
	    TileData[][] tdssEarth=UARV.tdssEarth;                     //~va11I~
        for (TileData[] tds:tdssEarth)                             //~va11I~
        {                                                          //~va11I~
        	if (tds==null)                                         //~va11I~
            	break;                                             //~va11I~
 	        if (Dump.Y) Dump.println("UARonDataTree.chkRedTile earth pair="+ TileData.toString(tds));//~va11I~
	        for (TileData td:tds)                                  //~va11I~
            {                                                      //~va11I~
	        	if (td==null)                                      //~va11I~
    	        	break;                                         //~va11I~
                if ((td.flag & TDF_RED5)!=0)                       //~va11I~
                	ctrRed5++;                                     //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
	    TileData[] tdsHand=UARV.tdsHand;                           //~va11I~
	    if (Dump.Y) Dump.println("UARonDataTree.chkRedTile hand="+TileData.toString(tdsHand));//~va11I~
	    for (TileData td:tdsHand)                                  //~va11I~
        {                                                          //~va11I~
        	if ((td.flag & TDF_RED5)!=0)                           //~va11I~
              if (!td.isDiscardedRed5())                           //~vaaKI~
              {                                                    //~vaaKI~
                ctrRed5++;                                         //~va11I~
				if (Dump.Y) Dump.println("UARonDataTree.chkRedTile ctrRed5="+ctrRed5+",td="+td.toString());//~vaaKI~
              }                                                    //~vaaKI~
        }                                                          //~va11I~
        //*ron tile is not included in Hand when ron River         //~va11I~
        if (UARV.tdRonRiver!=null)                                 //~va11R~
        {                                                          //~va11I~
		    if (Dump.Y) Dump.println("UARonDataTree.chkRedTile ronTile="+TileData.toString(UARV.tdRonRiver));//~va11I~
        	if ((UARV.tdRonRiver.flag & TDF_RED5)!=0)                   //~va11I~
                ctrRed5++;                                         //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonDataTree.chkRedTile ctrRed5="+ctrRed5);//~va11R~
        return ctrRed5;                                            //~va11R~
    }                                                              //~va11I~
}                                                                  //~va11I~
