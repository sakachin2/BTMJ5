//*CID://+vagvR~: update#= 725;                                    //~vaauR~//~vagvR~
//**********************************************************************//~v101I~
//2021/11/15 vagv move TilesTest to debug dir                      //~vagvI~
//2021/07/08 vaau split test deal to TilesTest.java                //~vaauI~
//2021/05/01 va8x (Test)specify robot discard tile                 //~va8xI~
//v@@5 20190126 player means position on the device                //~v@@5I~
//reset tile to new game                                           //~v@@@R~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;                                    //~v@@@I~
                                                                   //~9412I~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GConst.*;                                  //~v@@@I~//~9412M~
import static com.btmtest.game.RA.RAConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.gv.Stock.*;//~9412M~


import java.util.ArrayList;

public class TilesTest                                 //~v@@@R~   //~va8xR~
{                                                                  //~0914I~
//  private Tiles aTiles;                                          //~va8xR~//~vagvR~
    protected Tiles aTiles;	//protexted with androidTest           //~vagvI~
    private TileData[] shuffledTileData;                           //~vaauI~
    private boolean swTestDeal;                                    //~vaauI~
    private boolean swUseRed5;                                     //~vaauI~
    //*********************************************************************//~v@@@I~
    public TilesTest()                                                 //~v@@@R~//~va8xR~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("Tiles Constructor");         //~1506R~//~@@@@R~//~v@@@R~
//      aTiles=AG.aTiles;                                          //~va8xI~//~vagvR~
//  	swUseRed5= RuleSetting.isUseRed5Tile();                    //~vaauI~//~vagvR~
    }                                                              //~0914I~//~v@@@R~
    //*************************                                    //~vagvI~
    public void init(Tiles Ptiles)                                      //~vagvI~
    {                                                              //~vagvI~
        aTiles=Ptiles;                                             //~vagvI~
		swUseRed5= RuleSetting.isUseRed5Tile();                    //~vagvI~
    }                                                              //~vagvI~
    //*************************                                    //~vaauI~
//  public ArrayList<TileData> deepCopyToArrayList()               //~vaauI~//~vagvR~
    private ArrayList<TileData> deepCopyToArrayList()              //~vagvI~
    {                                                              //~vaauI~
        return aTiles.deepCopyToArrayList();                       //~vaauI~
    }                                                              //~vaauI~
    //*************************                                    //~vaauI~
//  public TileData[] getNewAllTile()                              //~vaauI~//~vagvR~
    private TileData[] getNewAllTile()                             //~vagvI~
    {                                                              //~vaauI~
        return aTiles.getNewAllTile();                             //~vaauI~
    }                                                              //~vaauI~
    //*****************************************************************//~v@@@R~
    //*create sorted deepcopy by 4                                 //~v@@@I~
    //*****************************************************************//~v@@@I~
    public boolean shuffleTest()                                   //~vaauR~
    {                                                              //~vaauI~
	    shuffleTestSub();                                           //~vaauI~
        if (swTestDeal)	//deal was set                             //~vaauI~
        {                                                          //~vaauI~
        	if (Dump.Y) Dump.println("TilesTest.shuffleTest shuffledTileData="+TileData.toString(shuffledTileData));//~v@@5R~//~0A12R~//~va8xR~//~vaauI~
        	aTiles.shuffledTileData=shuffledTileData;              //~vaauI~
            if ((TestOption.option2 & TestOption.TO2_SETDORA)!=0)  //~vaauI~
            	testSetDora(shuffledTileData);                     //~vaauI~
        }                                                          //~vaauI~
        if (Dump.Y) Dump.println("TilesTest.shuffleTest rc="+swTestDeal);//~vaauI~
        return swTestDeal;                                         //~vaauI~
    }                                                              //~vaauI~
    private void shuffleTestSub()                                          //~v@@@I~//~va8xR~//~vaauR~
    {                                                              //~v@@@M~
    	swTestDeal=true;                                           //~vaauI~
        if (Dump.Y) Dump.println("TilesTest.shuffleTestSub testOption 1="+Integer.toHexString(TestOption.option)+",2="+Integer.toHexString(TestOption.option2)+",3="+Integer.toHexString(TestOption.option3)+",4="+Integer.toHexString(TestOption.option4)+",5="+Integer.toHexString(TestOption.option5));//~va8xR~//~vaauR~
        if ((TestOption.option2 & TestOption.TO2_DEAL_PON)!=0)      //~1401I~//~1402R~
        {	shufflePonTest(); return;}                             //~1401I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_CHII)!=0)    //~1403I~
        {	shuffleChiiTest(); return;}                            //~1403I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_PONCHII)!=0) //~1405I~
        {	shufflePonChiiTest(1000); return;}                         //~1405I~//~1407R~//~vaauR~
        if ((TestOption.option4 & TestOption.TO4_DEAL_PON_ALLSAME)!=0)//~vaauI~
        {	shufflePonChiiTest(1001); return;}                     //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_INTENT_ALLSAME)!=0)//~vaauI~
        {	shufflePonChiiTest(10011); return;}                    //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_INTENT_TANYAO)!=0)//~vaauI~
        {	shufflePonChiiTest(10012); return;}                    //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_INTENT_CHANTA)!=0)//~vaauI~
        {	shufflePonChiiTest(10013); return;}                    //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_STRAIGHT)!=0)//~vaauI~
        {	shufflePonChiiTest(10014); return;}                    //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_STRAIGHT_CHII)!=0)//~vaauI~
        {	shufflePonChiiTest(10015); return;}                    //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_3SAMESEQ)!=0)//~vaauI~
        {	shufflePonChiiTest(10016); return;}                    //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_TANYAO)!=0)  //~vaauI~
        {	shufflePonChiiTest(10017); return;}                    //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_TANYAO_PON)!=0)//~vaauI~
        {	shufflePonChiiTest(10018); return;}                    //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_CHANTA)!=0)  //~vaauI~
        {	shufflePonChiiTest(10019); return;}                    //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_CHANTA_PON)!=0)//~vaauI~
        {	shufflePonChiiTest(10020); return;}                    //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_PON_WAIT_TANKI)!=0)//~vaauI~
        {	shufflePonChiiTest(1002); return;}                     //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_PONPON)!=0)  //~vaauI~
        {	shufflePonChiiTest(1003); return;}                     //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_PONCHII_SELECT_DORA)!=0)//~vaauI~
        {	shufflePonChiiTest(1004); return;}                     //~vaauI~
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
        if ((TestOption.option3 & TestOption.TO3_DEAL_DOUBLEREACH_CHII)!=0)//~va8xI~
        {	shufflePonChiiTest(611); return;}                      //~va8xI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_DOUBLEREACH_PON)!=0)//~va8xI~
        {	shufflePonChiiTest(612); return;}                      //~va8xI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_DOUBLEREACH_PON_HONOR)!=0)//~va8xI~
        {	shufflePonChiiTest(613); return;}                      //~va8xI~
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
                                                                   //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_KANCHAN_REACH)!=0)//~vaauI~
        {	shufflePonChiiTest(112); return;}                      //~vaauI~
                                                                   //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_PENCHAN_REACH)!=0)//~vaauI~
        {	shufflePonChiiTest(113); return;}                      //~vaauI~
                                                                   //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_SHANPON_REACH)!=0)//~vaauI~
        {	shufflePonChiiTest(1131); return;}                     //~vaauI~
                                                                   //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_TANKI_REACH)!=0)//~vaauI~
        {	shufflePonChiiTest(114); return;}                      //~vaauI~
                                                                   //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_TANKI_REACH_TANYAO)!=0)//~vaauI~
        {	shufflePonChiiTest(115); return;}                      //~vaauI~
                                                                   //~vaauI~
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
        if ((TestOption.option3 & TestOption.TO3_DEAL_NOTIFY_ALL)!=0)//~va8xI~
        {	shufflePonChiiTest(16); return;}                       //~va8xI~
        if ((TestOption.option3 & TestOption.TO3_DEAL_13NOPAIR)!=0)//~1412I~
        {	shuffleNoPairTest(1); return;}                         //~1412R~
        if ((TestOption.option4 & TestOption.TO4_DEAL_13ORPHAN)!=0)//~vaauI~
        {	shuffleFreeWanpai(101); return;}                       //~vaauI~
        if ((TestOption.option5 & TestOption.TO5_DEAL_OTHER_YAKUMAN)!=0)//~vagvR~
        {	shuffleFreeWanpai(1011); return;}                      //~vagvI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_7PAIR_TANYAO)!=0)//~vaauI~
        {	shuffleFreeWanpai(102); return;}                       //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_7PAIR_CHANTA)!=0)//~vaauI~
        {	shuffleFreeWanpai(103); return;}                       //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_7PAIR_NOT_TANYAO)!=0)//~vaauI~
        {	shuffleFreeWanpai(104); return;}                       //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_7PAIR_SAMECOLOR)!=0)//~vaauI~
        {	shuffleFreeWanpai(105); return;}                       //~vaauI~
        if ((TestOption.option3 & TestOption.TO3_DEAL_14NOPAIR)!=0)//~1412I~
        {	shuffleNoPairTest(2); return;}                         //~1412I~
        if ((TestOption.option3 & TestOption.TO3_INTENT_3DRAGON)!=0) //~1415I~
        {	shuffleNoPairTest(3); return;}                         //~1415I~
        if ((TestOption.option5 & TestOption.TO5_KUIKAE_CHK)!=0)   //~vaauI~
        {	shuffleNoPairTest(301); return;}                       //~vaauI~
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
        if ((TestOption.option4 & TestOption.TO4_DEAL_MINKAN_HONOR)!=0)//~va8xI~
        {	shuffleNoPairTest(4140); return;}                      //~va8xI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_MINKAN_HONOR_SAMECOLOR)!=0)//~vaauI~
        {	shuffleNoPairTest(41401); return;}                     //~vaauI~
        if ((TestOption.option3 & TestOption.TO3_DEAL_MINKAN_RON)!=0)//~va8xI~
        {	shuffleNoPairTest(4141); return;}                      //~va8xI~
        if ((TestOption.option3 & TestOption.TO3_DEAL_SAKIDUKE_2HAN_TAKE)!=0)//~va8xI~
        {	shuffleNoPairTest(415); return;}                       //~va8xI~
        if ((TestOption.option2 & TestOption.TO2_CHANKAN_DEAL)!=0) //~0407I~
//      {	shuffleChankanTest(); return;}                         //~0407I~//~va8xR~
        {	shufflePonChiiTest(2001); return;}                      //~va8xI~//~vaauR~
        if ((TestOption.option4 & TestOption.TO4_DEAL_CHANKAN)!=0) //~vaauI~
        {	shuffleNoPairTest(4143); return;}                      //~vaauI~
        if ((TestOption.option2 & TestOption.TO2_ANKAN_DEAL)!=0)   //~0406I~//~0407R~//~vaauM~
//      {	shuffleMinkanTest(9901); return;}                          //~0406I~//~vaauI~
    	{   shufflePonChiiTest(1005); return;}                     //~vaauI~
        if ((TestOption.option & TestOption.TO_KAN_ADDDEAL)!=0)               //~v@@5I~//~vaauM~
//      {	shuffleKanAdd(8801); return;}                               //~v@@5I~//~vaauI~
    	{   shufflePonChiiTest(1006); return;}                     //~vaauI~
        if ((TestOption.option4 & TestOption.TO4_DEAL_CHANKAN_13ORPHAN)!=0)//~vaauI~
        {	shuffleFreeWanpai(106); return;}                       //~vaauI~
        if ((TestOption.option2 & TestOption.TO2_DEAL_MULTIRON)!=0)     //~1328I~
        {	shuffleMultiRonTest(true); return;}                    //~1328I~
        if ((TestOption.option2 & TestOption.TO2_DEAL_SINGLERON)!=0)    //~1328I~
        {	shuffleMultiRonTest(false); return;}                   //~1328I~
        if (Dump.Y) Dump.println("TilesTest.shuffleTestSub NO testOption detected");//~vaauI~
    	swTestDeal=false;                                          //~vaauI~
    }                                                              //~v@@@M~
    //*****************************************************************//~0A12I~
    //*for test                                                    //~0A12I~
    //*****************************************************************//~0A12I~
    public void testSetDora(TileData[] Pout)                           //~0A12I~//~vaauR~
    {                                                              //~0A12I~
        if (Dump.Y) Dump.println("TilesTest.testSetDora");              //~0A12I~//~va8xR~
        int type,num;                                              //~0A12I~
        type=TestOption.testDoraUpType;                            //~0A12I~
        num=TestOption.testDoraUpNumber;                           //~0A12I~
        if (type>=1&&type<=4 && num>=1 && num<=9)                  //~0A12I~//~vaauR~
        	Pout[DORA_TDPOS]=new TileData(type-1,num-1);               //~0A12R~//~vaauR~
        type=TestOption.testDoraDownType;                          //~0A12I~
        num=TestOption.testDoraDownNumber;                         //~0A12I~
        if (type>=1&&type<=4 && num>=1 && num<=9)                  //~0A12I~//~vaauR~
        	Pout[DORA_TDPOS-1]=new TileData(type-1,num-1);             //~0A12R~//~vaauR~
                                                                   //~0A12I~
        type=TestOption.testKanUpType;                             //~0A12I~
        num=TestOption.testKanUpNumber;                            //~0A12R~
        if (type>=1&&type<=4 && num>=1 && num<=9)                  //~0A12I~//~vaauR~
        	Pout[DORA_TDPOS-2]=new TileData(type-1,num-1);             //~0A12R~//~vaauR~
        type=TestOption.testKanDownType;                           //~0A12I~
        num=TestOption.testKanDownNumber;                          //~0A12R~
        if (type>=1&&type<=4 && num>=1 && num<=9)                  //~0A12I~//~vaauR~
        	Pout[DORA_TDPOS-3]=new TileData(type-1,num-1);             //~0A12R~//~vaauR~
                                                                   //~0A12I~
        type=TestOption.testKanUpType2;                            //~0A12I~
        num=TestOption.testKanUpNumber2;                           //~0A12I~
        if (type>=1&&type<=4 && num>=1 && num<=9)                  //~0A12I~//~vaauR~
        	Pout[DORA_TDPOS-4]=new TileData(type-1,num-1);             //~0A12I~//~vaauR~
        type=TestOption.testKanDownType2;                          //~0A12I~
        num=TestOption.testKanDownNumber2;                         //~0A12I~
        if (type>=1&&type<=4 && num>=1 && num<=9)                  //~0A12I~//~vaauR~
        	Pout[DORA_TDPOS-5]=new TileData(type-1,num-1);             //~0A12I~//~vaauR~
    }                                                              //~0A12I~
    //*****************************************************************//~v@@5I~
    //*for test                                                    //~v@@5I~
    //*****************************************************************//~v@@5I~
    public void shuffleKanAdd(int Pcase)                                    //~v@@5I~//~vaauR~
    {                                                              //~v@@5I~
        if (Dump.Y) Dump.println("shuffleKanAdd case="+Pcase);     //~vaauI~
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
                    if (Dump.Y) Dump.println("TilesTest.shuffle red5 type=" + td.type + ",remain=" + td.ctrRemain + ",flag=" + td.flag);//~9C01I~//~va8xR~
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
    public void shuffleMinkanTest(int Pcase)                                //~0406I~//~vaauR~
    {                                                              //~0406I~
        if (Dump.Y) Dump.println("shuffleMinkanTest case="+Pcase); //~vaauI~
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
        if (Pcase==112) //(112)kanchan reach                       //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{12,13,14, 24,22, 1,  1,18,23, 23,23,26 },//~vaauR~
//          						{ 6, 7, 8, 12,13,14, 17,21,24,  0, 1, 2 },//~vaauR~
            						{ 6, 7, 8, 12,13,14, 19,19,22,  0, 1, 2 },//red5so//~vaauI~
            						{ 3, 4, 7, 15,16,27, 13,25,26, 15,28,29 },//~vaauR~
            						{ 6, 6, 8, 15,15,27, 23,24,25, 13,28,29 },//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{27,24, 0,22};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{25,26,18,22};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{17,19, 2,22};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,17,18,19};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{26, 9,24,25};           //furiten tsumo//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==113) //(113)Penchan reach                       //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{12,13,14, 24,22,22, 22,21,23, 23,23,26 },//~vaauI~
            						{ 6, 7, 8, 12,13,14, 17,21,25,  0, 1, 2 },//~vaauI~
            						{ 3, 4, 7, 15,16,27, 13,25,26, 15,28,29 },//~vaauI~
            						{ 6, 6, 8, 15,15,27, 23,24,25, 13,28,29 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{27,21, 0, 1};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{25,26,18, 1};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{17,19, 2,18};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,17,18,19};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{26, 9,24,24};           //furiten tsumo//~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==1131) //(1131)Shanpon reach                     //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{12,13,14, 17,22,22, 22,21,23, 23,23,26 },//~vaauR~
            						{ 6, 7, 8, 12,13,14, 17,21,24, 24, 1, 2 },//~vaauR~
            						{ 3, 4, 7, 15,16,27, 13, 0, 1, 15,28,29 },//~vaauR~
            						{ 6, 6, 8, 15,15,27, 23,13,11, 14,28,29 },//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{27,21, 0, 1};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{25, 0,18, 1};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{17,19, 2,18};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,14,17,19};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{26, 9,24, 3};           //furiten tsumo//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==114) //(114)tanki   reach                       //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{12,13,14, 21,22,22, 22,23,23, 28,28,28 },//~vaauR~
            						{ 0, 1, 2,  6, 7, 8, 12,13,14, 17,21,30 },//~vaauR~
            						{ 3, 4, 7, 13,15,15, 16,25,26, 27,24,30 },//~vaauR~
            						{ 6, 6, 8, 13,15,15, 19,24,25, 27,26,27 },//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{18,19, 0, 1};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{25,20,18, 1};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{17,23, 2,18};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,27,18,19};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{26,28,24,24};           //furiten tsumo//~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==115) //(114)tanki   reach  tanyao               //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{12,13,14, 21,22,22, 22,23,23, 28,28,28 },//~vaauI~
            						{ 1, 2, 3,  5, 6, 7, 12,13,14, 17,21,27 },//~vaauI~
            						{ 3, 4, 7, 13,15,15, 16,25,26, 27,24,29 },//~vaauI~
            						{ 6, 7, 8, 13,14,15, 19,20,24, 25,26,29 },//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{18,19, 0, 1};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{25,20,18, 1};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{17,23, 2,18};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,27,18,19};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{26,28,24,24};           //furiten tsumo//~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
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
            						{ 4, 5, 6, 29,19,20, 21,22,24,  0, 5, 6 },//~1414I~//~1420R~//~vaauR~
            						{ 0, 2, 3, 21,22,23, 17,19,15, 20,22,23 },//~1414I~//~1420R~//~vaauR~
            					};                                 //~1414I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1414I~
        	itsDealTake=new int[]{30,17,27,25};                    //~1414I~//~1420R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1414I~
        	itsDealTake=new int[]{28,18,24,25};                    //~1420I~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{20, 8,28,28};                    //~1414R~//~1420R~//~1421R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1414I~
        	itsDealTake=new int[]{17,18,24, 0};                    //~1414I~//~1420R~//~1421R~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1414I~
        	itsDealTake=new int[]{ 8,17,24,25};                    //~1420R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        }                                                          //~1414I~
        else                                                       //~1414I~
        if (Pcase==61) //(61)rontaken double reach not oneshot //~1420I~//~1421R~
        {                           //robot east double reach     //~1420I~//~1421R~
        	itsDeal=new int[][]{                                   //~1420I~
            						{19,20,21,  1, 2, 3,  4, 5, 6, 18,19, 8 },//~1420I~//~1427R~//~va8xR~
            						{ 1, 2, 3,  4, 5, 6,  9, 9,11, 16,22,23 },//~1420I~//~1427R~//~va8xR~
            						{ 4, 5, 6, 18,19,30, 21,22,24,  0, 5, 6 },//~1420I~//~1427R~
            						{ 0, 2, 3, 21,22,23, 18,19,15, 20,22,23 },//~1420I~
            					};                                 //~1420I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1420I~
        	itsDealTake=new int[]{15,30,27,25};                    //~1420I~//~1421R~//~1427R~//~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{20,29,28,28};                    //~1420I~//~1427R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{ 8,17,24, 0};                    //~1420I~//~1421R~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        	itsDealTake=new int[]{20,17,24,25};                    //~1420I~//~1427R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1420I~
        }                                                          //~1420I~
        else                                                       //~va8xI~
        if (Pcase==611) //(611)chii for double reach               //~va8xI~
        {                           //human east double reach      //~va8xI~
        	itsDeal=new int[][]{                                   //~va8xI~
            						{19,20,21,  1, 2, 3,  4, 5, 6, 19,20, 9 },//~va8xR~
            						{ 2, 3, 4,  4, 5, 6, 10,11,18, 15,15,15 },//~va8xI~
            						{ 4, 5, 6, 11,13,28, 21,22,24,  0, 5, 6 },//~va8xI~
            						{ 0, 2, 3, 11,13,29, 18,19,15, 21,22,23 },//~va8xI~
            					};                                 //~va8xI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~va8xI~
        	itsDealTake=new int[]{12,14,27,25};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{21,28,29,30};                    //~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        }                                                          //~va8xI~
        else                                                       //~va8xI~
        if (Pcase==612) //(612)Pon for double reach                //~va8xI~
        {                           //human east double reach      //~va8xI~
        	itsDeal=new int[][]{                                   //~va8xI~
            						{19,20,21,  1, 2, 3,  4, 5, 6, 19,20, 9 },//~va8xI~
            						{ 2, 3, 4,  4, 5, 6, 12,12,18, 15,15,15 },//~va8xI~
            						{ 4, 5, 6, 11,13,28, 21,22,24,  0, 5, 6 },//~va8xI~
            						{ 0, 2, 3, 11,13,29, 18,19,15, 21,22,23 },//~va8xI~
            					};                                 //~va8xI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~va8xI~
        	itsDealTake=new int[]{12,14,27,25};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{21,28,29,30};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        }                                                          //~va8xI~
        else                                                       //~va8xI~
        if (Pcase==613) //(613)Pon honor tile for double reach     //~va8xI~
        {                           //human east double reach      //~va8xI~
        	itsDeal=new int[][]{                                   //~va8xI~
            						{19,20,21,  1, 2, 3,  4, 5, 6, 15,20,27 },//~va8xI~//~vaauR~
            						{ 2, 2, 7, 14,15, 9, 28,28,27, 16,16,28 },//~va8xR~//~vaauR~
            						{ 4, 5, 6, 11,13, 9, 21,22,24,  0, 5, 6 },//~va8xI~
            						{ 0, 4, 3, 11,13,29, 18,19,15, 21,22,23 },//~va8xI~//~vaauR~
            					};                                 //~va8xI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~va8xI~
        	itsDealTake=new int[]{29,27,14,25};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{21,18, 1,30};                    //~va8xI~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        }                                                          //~va8xI~
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
        	itsDealTake=new int[]{ 9,17,24,25};                    //~1412I~//~1414R~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1412I~
        }                                                          //~1412I~
        else                                                       //~1412I~
        if (Pcase==4) //rinshanron                                 //~1411R~
        {                                                          //~vaauR~
        	itsDeal=new int[][]{                                   //~1411I~
            						{ 0, 0,19,  1, 2, 3,  4, 5,10,  4,11,15 },//~1411R~//~1413R~//~vaauR~
            						{ 9, 9, 9,  2, 5,27, 11,12,13, 14,15,16 },//~1411R~//~1413R~//~vaauR~
            						{18,18,18, 19,20,21, 22,23,24,  1, 3, 8 },//~1411R~//~1413R~//~vaauR~
            						{ 1, 2, 3,  4, 5, 6, 26,26,26,  7, 7, 7 },//~1411R~//~1413R~
            					};                                 //~1411R~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1411I~
        	itsDealTake=new int[]{ 8,17, 2,26};                    //~1411R~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1411I~
        	itsDealTake=new int[]{ 0,10,18, 9};                    //~1411R~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1411I~
            TileData tdrinshan1 = al.get(8);                              //~1411I~//~vaauR~
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
        	itsDealTake=new int[]{ 0,28, 0, 0};                    //~1405I~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        	itsDealTake=new int[]{15, 6, 7, 8};                    //~1405R~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1405I~
        }                                                          //~1405I~
        else                                                       //~1405I~
        if (Pcase==1000)                                                 //~1405I~//~1407R~//~vaauR~
        {                //Robot Ron and Human Pon                 //~vaauR~
            itsDeal=new int[][]{                                   //~vaauI~
                                    {18,18,25,  1, 2, 3, 10,15,16, 10,17,17 },//~vaauR~
//                                  {23,24,25,  0, 1, 4, 28,28,12, 27,27, 5 }, //tenpai by Chii//~vaauI~
//                                  {23,24,26,  0, 1, 4, 28,28,12, 27,27, 5 }, //Not Tenpai penchan//~vaauR~
//                                  {23,24,25,  0, 2, 4, 28,28,12, 27,27,11 }, //Not Tenpai kanchan//~vaauR~
                                    {14,15,16,  0, 2,14, 28,28,22, 27,27,18 }, //Not Tenpai kanchan red5//~vaauR~
                                    {23,24,25,  9,10,11, 19,20,29, 29,29,30 },//~vaauR~
                                    {23,24,25,  3, 6, 6, 10,11,12, 26,26, 8 },//~vaauR~
                                };                                 //~vaauI~
            outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
            itsDealTake=new int[]{27,24,30,26};                    //~vaauR~
            outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
            itsDealTake=new int[]{28, 7,27, 7};                    //~vaauR~
            outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~1405I~
        else                                                       //~vaauI~
        if (Pcase==1001)  //pon for allsame                        //~vaauR~
        {                //pon and chii by 2pin                    //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{23,20,24,  2, 3, 4, 10,15,17, 28,28, 8 },//~vaauR~
            						{13,13,13, 17,17,21, 23,23,24, 24,25,28 },//~vaauR~
            						{ 2, 3, 4,  5, 6, 7, 10,11,12, 22,24,29 },//~vaauR~
            						{10,11,25,  2, 3, 4, 10,11,12, 26,26, 8 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{23,28,29,30};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{17, 7,30,26};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==10011)  //intent allsame to ignore shantenup    //~vaauI~
        {                //pon and chii by 2pin                    //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{23,20,24,  2, 3, 4, 11,15,16, 28,29, 8 },//~vaauR~
            						{18,20,12, 19,13,13, 17,23,23, 24,24,29 },//~vaauR~
            						{ 2, 3, 4,  4, 6,17, 10,12,14, 18,27, 8 },//~vaauR~
            						{11,14,25,  2, 3, 4, 11,12,15,  6,26, 8 },//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{23,29,28,30};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
//      	itsDealTake=new int[]{ 0,18,27,26};  //pon for toitoi  //~vaauR~
        	itsDealTake=new int[]{ 0,29,27,26};  //allinhand toitoi//~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 9, 9,27,26};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==10012)  //intent tanyao  to ignore shantenup    //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{12,13,14, 21,22,22, 22,23,23, 28,28,28 },//~vaauI~
//          						{ 0, 0, 2,  5, 6, 7, 12,13,14, 14,21,27 },//~vaauR~
            						{ 0, 1, 2,  5, 6, 7, 12,13,14, 14,21,27 },//~vaauI~
            						{ 3, 4, 7, 13,15,15, 16,25,26, 27,24,29 },//~vaauI~
            						{ 6, 7, 8, 13,14,15, 19,20,24, 25,26,29 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{18,15, 0, 1};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0, 3,18, 1};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 3,23, 2,18};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==10013)  //intent chanta  to ignore shantenup    //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0,13,14, 21,22,22, 22,23,23,  9, 9,29 },//~vaauR~
            						{ 0, 0, 1,  6, 7, 8, 29,29, 9, 14,21,15 },//~vaauR~
            						{ 3, 4, 7, 13,15,15, 16,25,26, 27,24,28 },//~vaauR~
            						{ 6, 7, 8, 13,14,15, 19,20,24, 25,26,28 },//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{20,17, 0, 1};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0, 2,18, 1};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 3,23, 2,18};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==10014)  //intent straight                       //~vaauR~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0,13,14, 21,22,22, 22,23,23,  4, 9,29 },//~vaauI~
            						{ 0, 1, 2,  3, 4, 5,  6, 9,10, 11, 3,10 },//~vaauI~
            						{10,12,13, 14,15,16, 17,18,19, 24,13,13 },//~vaauI~
            						{18,21,22, 23,24,25, 26, 0, 1,  2,26,28 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{20,17, 2, 3};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 2, 4, 3,22};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==10015)  //intent straight      chii             //~vaauR~
        {                                                          //~vaauM~
        	itsDeal=new int[][]{                                   //~vaauM~
            						{ 0,13,14, 16,22,23, 24,25, 1,  4, 6, 9 },//~vaauR~
//          						{10,11,12, 13,14,15, 16,17,22, 22,22,23 },//~vaauR~
            						{10,11,12,  9,14,15, 30,17,22, 22,22,23 },//~vaauR~
//          						{18,19,20, 21,23,24, 25,26, 1, 17,17,17 },//~vaauI~
            						{18,19,20, 21,23,24, 25,30, 1, 15,21,16 },//~vaauR~
//          						{ 0, 1, 2,  3, 4, 5,  7, 8,10, 26,26,26 },//~vaauR~
            						{ 0, 1, 2,  3, 4, 5,  7, 8,10, 17,18,20 },//~vaauR~
            					};                                 //~vaauM~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauM~
        	itsDealTake=new int[]{26,18, 2, 3};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauM~
        	itsDealTake=new int[]{20, 4, 3, 0};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauM~
        	itsDealTake=new int[]{16,10,12,26};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{17,11,13,26};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauM~
        else                                                       //~vaauM~
        if (Pcase==10016)  //intent 3sameseq                       //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
//          						{ 0, 1, 2,  9,10,11, 18,19,20, 24,25,25 },//~vaauR~
//          						{ 0, 1, 2,  9,10,11, 18,19,20, 25,25,25 },//~vaauR~
//          						{ 6,30, 8, 15,16,17, 24, 4,26,  3, 3, 3 },//~vaauI~
            						{ 1, 2, 3, 10,11,12, 19,20,21, 24,24,25 },//~vaauI~
            						{ 5, 2, 3, 10,30,12, 19,20,21, 26,26,26 },//~vaauR~
            						{ 5, 7, 7, 12,14,14,  9,10,24, 24,16,16 },//~vaauR~
            						{ 4, 6, 8,  9,11,13, 12,21,22,  4, 6, 8 },//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{23,25, 2, 3};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{30, 9,10,15};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{11, 4, 5, 6};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{18, 4, 5, 6};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==10017)  //intent Tanyao                         //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 1, 6, 3, 10,11,12, 19, 4,21, 23,24,26 },//~vaauR~
            						{ 2, 3,20, 10,30,12, 19,20,21, 25,25,25 },//~vaauR~
            						{ 5, 7, 7, 12,14,14,  9,10,24, 24,16,16 },//~vaauI~
            						{ 4, 6, 8,  9,11,13, 12,21,22,  4, 6, 8 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{23,26, 2, 3};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{30,15,10,15};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{11, 4, 5, 6};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{18, 4, 5, 6};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==10018)  //intent Tanyao pon                     //~vaauR~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 1, 2, 3, 10,11,12, 19,20,21, 24,24,25 },//~vaauI~
            						{ 5, 3, 3, 10,30,12, 19,20,21, 25,25,25 },//~vaauI~
            						{ 5, 7, 7, 12,14,14,  9,10,24, 24,16,16 },//~vaauI~
            						{ 4, 6, 8,  9,11,13, 12,21,22,  4, 6, 8 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{23,23, 2, 4};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{30, 9,10,15};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{11, 4, 5, 6};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{18, 4, 5, 6};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==10019)  //intent chanta                         //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0, 2, 5,  8,10,17, 19,20,21, 24,24,25 },//~vaauR~
            						{18, 1, 2,  9,30,11, 27,27,27, 28,28,26 },//~vaauR~
            						{ 5, 7, 7, 12,14,14,  9,10,24, 24,16,16 },//~vaauI~
            						{ 4, 6, 8,  9,11,13, 12,21,22,  4, 6, 8 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{23,18, 2, 4};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{30, 9,10,15};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{10, 4, 5, 6};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{17,15, 5, 6};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==10020)  //intent chanta pon                     //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0, 2, 3, 10,11,11, 19,20,21, 24,24,25 },//~vaauR~
            						{ 5, 0, 0,  9,17,12, 18,19,20, 26,26,26 },//~vaauR~
            						{ 5, 7, 7, 12,14,14, 10,10,24, 24,16,16 },//~vaauR~
            						{ 4, 6, 8,  9,11,13, 12,21,22,  4, 6, 8 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{17,17, 2, 4};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{30, 9,13,15};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{30, 4, 5, 6};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{18,13, 5, 6};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==1002)  //pon and wait tanki                     //~vaauI~
        {                //pon and chii by 2pin                    //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 1, 2, 3, 18,10,15, 16,20,23, 25,27,28 },//~vaauR~
            						{ 2, 3, 4, 14,15,16, 28,28,29, 20,21,22 },//~vaauR~
//            						{ 2, 3, 4, 14,15,16, 27,27, 6, 28,29,29 },//getronvalueEvaluate test,haexceptDora//~vaauI~
            						{ 2, 3, 5,  4, 6, 9, 10,12,14, 18,21, 8 },//~vaauI~
            						{10,11,25,  2, 3, 4, 10,11,12, 26,26, 8 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{30,18,19,13};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{29, 9,20, 1};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else  //Pon+pon to tenpai                                  //~vaauI~
        if (Pcase==1003)                                           //~vaauI~
        {                //pon and chii by 2pin                    //~vaauI~
            itsDeal=new int[][]{                                   //~vaauI~
                                    {23,24,25,  0, 3, 4, 10,15,16, 10,11, 8 },//~vaauR~
//                                  {23,24,25,  0, 0, 4, 28,28,12, 27,27, 5 },//~vaauR~
                                    {23,24,25,  9,10, 4,  5, 3,12, 27,27, 4 },//~vaauR~
                                    {23,24,25,  2,28, 5,  9,11,16, 29,29, 8 },//~vaauR~
                                    {23,24,25,  0, 3, 6, 10,11,12, 26,26, 8 },//~vaauI~
                                };                                 //~vaauI~
            outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
            itsDealTake=new int[]{27, 4, 7,26};                    //~vaauR~
            outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
            itsDealTake=new int[]{29,28, 7, 7};                    //~vaauR~
            outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else  //double ron test                                    //~1405I~
        if (Pcase==1004)     //pon+chii select dora                //~vaauI~
        {                //pon and chii for tenpai                 //~vaauI~
            itsDeal=new int[][]{                                   //~vaauI~
                                    {23,24, 5,  1, 2, 4, 10,15,16, 10,10, 6 },//~vaauR~
//                                  { 1, 2, 4, 19,21,23, 11,12,27, 27,28,28 }, //select 2.3.4   by dora//~vaauR~
//                                  { 1, 2, 4,  5,21,23, 11,12,27, 27,28,28 }, //select 3.4.5   by dora//~vaauI~
//                                  { 4, 5, 7,  1,21,23, 11,12,27, 27,28,28 }, //select 4.5.6   by dora//~vaauI~
//                                  { 4, 6, 7,  1,21,23, 11,12,27, 27,28,28 }, //select 4.5.6   by dora//~vaauI~
//                                  { 1, 3, 4,  8,21,23, 11,12,27, 27,28,28 }, //select 2.3.4   by dora//~vaauI~
//                                  { 3, 4, 6,  7,21,23, 11,12,27, 27,28,28 }, //select 2.3.4   by dora//~vaauI~
//                                  { 5, 6, 8,  1,21,23, 11,12,27, 27,28,28 }, //select 5.6.7   by dora//~vaauI~
//                                  { 5, 7, 8,  1,21,23, 11,12,27, 27,28,28 }, //select 6.7.8   by dora//~vaauI~
//                                  { 4, 5, 7,  8,21,23, 11,12,27, 27,28,28 }, //select 6.7.8   by dora//~vaauR~
                                    { 3, 4, 4,  6,21,23, 11,12,27, 27,28,28 }, //select 6.7.8   by dora//~vaauI~
                                    {23,24,25,  2, 3, 4,  9,11,16, 29,29, 8 },//~vaauI~
                                    {23,24,25,  0, 3, 6, 10,11,12, 26,26, 8 },//~vaauI~
                                };                                 //~vaauI~
            outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
            itsDealTake=new int[]{27,17, 7,26};                    //~vaauI~
            outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
            itsDealTake=new int[]{28, 7, 7, 7};                    //~vaauI~
            outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==1005) //chankan ron                             //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0, 0, 0,  0, 4, 4,  4, 4, 8,  8, 8,  8},//~vaauI~
            						{ 1, 1, 1,  5, 5, 5,  5, 9, 9,  9,14, 12},//~vaauI~
            						{ 2, 2, 2,  2, 6, 6,  6, 6,10, 10,10, 12},//~vaauI~
            						{ 3, 3, 3,  3, 7, 7,  7, 7,11, 11,11, 12},//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{12,13,14,13};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 9,13,13,13};                    //~vaauI~
            outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==1006) //kanadd                                  //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 1, 1, 1,  5, 5, 5,  5, 8, 9, 28,14, 12},//~vaauI~
            						{18,19,20,  3, 4, 4,  7, 8, 9,  9,27, 27},//~vaauI~
            						{ 2, 2, 2,  2, 6, 6,  6, 6,10, 10,10, 12},//~vaauI~
            						{ 3, 3, 3,  4, 7, 7,  7, 8,11, 11,11, 12},//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{12,27,14,13};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 8,13,14,16};                    //~vaauI~
            outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 9,15,15,15};                    //~vaauI~
            outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==16)    //notify all ron pon chii                //~va8xI~
        {                                                          //~va8xI~
        	itsDeal=new int[][]{                                   //~va8xI~
            						{ 0, 1, 2,  3, 4, 5,  6, 7, 8, 27,28,29 },//~va8xI~
            						{11,12,13, 14,14,15, 16,17,19, 19,19,20 },//~va8xI~
            						{17,18,20,  2, 3, 4, 10,11,12,  9,18,21 },//~va8xR~
            						{21,22,23,  2, 4, 6, 10,11,12,  9,10,18 },//~va8xR~
            					};                                 //~va8xI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~va8xI~
        	itsDealTake=new int[]{13,20,28,29};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{14,27,28,29};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        }                                                          //~va8xI~
        else  //double ron test                                    //~va8xI~
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
        else                                                       //~va8xI~//~vaauM~
        if (Pcase==2001) //chankan ron                             //~va8xI~//~vaauI~
        {                                                          //~va8xI~//~vaauM~
        	itsDeal=new int[][]{                                   //~va8xI~//~vaauM~
            						{11,12,13, 14,15,16, 14,15,16,  7,27, 27},//~va8xI~//~vaauR~
            						{ 1, 1, 1,  2, 3, 4,  7, 7, 4, 17,17, 17},//~va8xI~//~vaauI~
            						{18,19,20, 19,20,21, 21,22,23, 24,24,  9},//~va8xI~//~vaauI~
            						{21,22,23, 26,11,12, 10,16,16, 27,28, 29},//~va8xI~//~vaauI~
            					};                                 //~va8xI~//~vaauM~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~va8xI~//~vaauM~
        	itsDealTake=new int[]{27,28, 6, 4};                    //~va8xI~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~//~vaauM~
        	itsDealTake=new int[]{ 1, 8, 3,28};                    //~va8xI~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~//~vaauM~
        	itsDealTake=new int[]{ 7,17,15,15};                    //~va8xI~//~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~//~vaauM~
        }                                                          //~va8xI~//~vaauM~
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
        if (Pcase==2) //14NoPAir                                   //~1412I~//~vaauR~
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
            						{ 3, 3, 5, 11,14,17, 19,21,24, 27,29, 30},//~1415I~//~1427R~//~vaauR~
            						{31,31,32, 32,17,17, 20,21,24, 27,28, 33},//~1415I~//~1427R~//~vaauI~
            						{ 0,19,19,  1,20,20,  2,22,22,  3,23, 1 },//~1415R~//~vaauR~
            						{31,12,27,  2, 2,28,  4,21,11, 13, 5,19 },//~1415R~//~vaauR~
            					};                                 //~1415I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1415I~
        	itsDealTake=new int[]{ 1,31, 1, 2};                    //~1415I~//~1427R~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1415I~
        	itsDealTake=new int[]{13,11,27,28};                    //~1415R~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1415I~
        }                                                          //~1415I~
        else                                                       //~vaauI~
        if (Pcase==301) //kuikae chk                               //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 2,15,21, 22,26,26, 26,13,13, 13,32, 32},//~vaauR~
            						{ 0,16,17, 11,12,13, 19,20,21, 27,27, 18},//~vaauR~
            						{ 0, 1, 6,  6,11,12, 19,20,22, 30,31, 32},//~vaauR~
            						{ 4, 4, 4,  5, 5, 5, 27,28,29, 30,31, 32},//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{20,28, 3, 3};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 5,27,13,13};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
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
            						{ 0, 0, 0,  4, 5, 6, 11,12,13, 18,19, 20},//~1427I~//~vaauR~//~vagvR~
            						{ 1, 2, 3,  4, 5, 6, 11,12,13, 18,19, 20},//~1427I~//~vaauR~//~vagvR~
            						{16,17, 3, 19,20,21, 23,25,26, 26,27, 27},//~1427I~//~1430R~//~vaauR~//~vagvR~
            						{ 1, 2, 3, 16,17,11, 12,28,17, 18,21, 22},//~1427I~//~vaauR~//~vagvR~
            					};                                 //~1427I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1427I~
        	itsDealTake=new int[]{13, 6,32,30};                    //~1427R~//~vaauR~//~vagvR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1427I~
        	itsDealTake=new int[]{30, 0,32,29};                    //~vaauR~//~vagvR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~1427I~
        else                                                       //~1501I~
        if (Pcase==412) //atoduke take samecolor                   //~1501I~
        {                                                          //~1501I~
        	itsDeal=new int[][]{                                   //~1501I~
            						{ 0, 0, 2,  4, 4, 5,  6, 6,11, 18,20+100, 23},//~1501R~//~va8xR~
            						{18,19,17, 27,28,29, 21,22,23, 24,25, 26},//~1501I~//~vagvR~
            						{ 2, 5,11, 12,12,15, 18,21,24, 31,32, 33},//~1501R~
            						{ 1, 4, 6, 11,13,17, 18,21,24, 31,32, 33},//~1501R~
            					};                                 //~1501I~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~1501I~
        	itsDealTake=new int[]{27+200,29,28,28};                    //~1501R~//~va8xR~//~vagvR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        	itsDealTake=new int[]{ 1,16,29, 1};                    //~1501R~//~vagvR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        	itsDealTake=new int[]{ 1,29,26+300,23};                    //~1501I~//~va8xR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        }                                                          //~1501I~
        else                                                       //~1501I~
        if (Pcase==413) //atoduke take yakuhai 1 han   //no 7 8 9 10  //~1501I~//~vaauR~
        {                                                          //~1501I~
            itsDeal=new int[][]{                                   //~1501I~//~vaauR~
//                                  { 0, 0, 2,  4, 4, 5,  6, 6,11, 17,20+100, 23},//~1501I~//~va8xR~//~vaauR~
//                                  {18,19,17, 33,33,28, 13,13,13, 24,25, 17},//~1501I~//~va8xR~//~vaauR~
                                    {18,19,17, 33,33,28, 11,13,16, 21,25, 17},//~vaauR~
                                    { 0, 0, 2,  4, 4, 5,  6, 6,11, 17,20+100, 23},//~vaauI~
                                    { 2, 5,11, 12,12,15, 18,21,24, 31,32, 27},//~1501I~//~vaauR~
                                    { 1, 4, 6, 11,14,16, 18,21,24,  4, 6, 10},//~1501I~//~va8xR~//~vaauR~
                                };                                 //~1501I~//~vaauR~
//            itsDeal=new int[][]{                                 //~vaauR~
//                                    { 0, 2, 3,  4,13, 5,  6,11,28, 17,20+100, 27},//~vaauR~
//                                    {18,19,15, 27,28,28, 13,13,13, 24,25, 17},//~vaauR~
//                                    { 2, 5,11, 12,12,15, 18,21,24, 31,32, 33},//~vaauR~
//                                    { 1, 4, 6, 11,14,16, 18,21,24, 31,32, 33},//~vaauR~
//                                };                               //~vaauR~
                                                                   //~vaauR~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{27+200,26,28,15};   //200 selection order//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        	itsDealTake=new int[]{ 0,29,29,33};                    //~1501I~//~va8xR~//~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~1501I~
        	itsDealTake=new int[]{ 1,14,14,15};                    //~va8xI~//~vaauR~
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
        if (Pcase==4140) //deal minkan homnor                      //~va8xI~
        {                                                          //~va8xI~
        	itsDeal=new int[][]{                                   //~va8xI~
            						{ 2, 4, 4,  5, 6, 6, 11,17,20, 23,27, 27},//~va8xI~
            						{ 0, 1,17, 17,18,19, 24,25,28, 33,33, 33},//~va8xI~
            						{ 2, 5,11, 12,12,15, 18,21,24, 31,32, 13},//~va8xI~
            						{ 1, 4, 6, 11,14,16, 18,21,24, 31,32, 13},//~va8xI~
            					};                                 //~va8xI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~va8xI~
        	itsDealTake=new int[]{27, 1,28,28};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{33,29,29,15};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{14,27,14,15};                    //~va8xI~//+vagvR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        }                                                          //~va8xI~
        else                                                       //~va8xI~
        if (Pcase==41401) //deal minkan homnor samecolor           //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 2, 4, 4,  5, 6, 6, 11,18,17, 22,27, 27},//~vaauR~
            						{18,18,18, 20,21,23, 25,26,28, 32,33, 33},//~vaauR~
            						{ 2, 5,11, 12,12,15, 20,21,24, 31,32, 13},//~vaauI~
            						{ 1, 4, 6, 11,14,16, 17,21,24, 31,32, 13},//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{27,20,28,28};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{33,29,29,15};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{27,14,14,15};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==4141) //ron for discard of minkan               //~va8xR~
        {                                                          //~va8xI~
        	itsDeal=new int[][]{                                   //~va8xI~
            						{11,12,13, 14,15,16, 14,15,16, 17,17, 18},//~va8xI~
            						{27,27,27,  0, 1, 2,  0, 1, 2,  3, 4,  5},//~va8xI~
            						{19,20,21, 19,21,21, 22,23,24, 22,23, 33},//~va8xI~
            						{24,25,26,  0, 1, 2, 24,25,26, 31,32, 33},//~va8xI~
            					};                                 //~va8xI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~va8xI~
        	itsDealTake=new int[]{27,17,28,28};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{18, 6,29,29};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        	itsDealTake=new int[]{11,12,30,30};                    //~va8xI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~va8xI~
        }                                                          //~va8xI~
        else                                                       //~va8xI~
        if (Pcase==4143) //chankan ron                             //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 4, 5, 6, 14,15,16, 14,15,16, 31,31, 31},//~vaauR~
            						{ 1, 1, 1,  2, 3, 4, 11,11,26, 27,27, 27},//~vaauI~
            						{18,19,20,  0, 0, 0, 21,22,23, 24,33, 12},//~vaauR~
//          						{18,19,20, 18,19,20, 21,22,23, 24,33, 12},//~vaauI~
            						{21,22,23, 12,12,13, 14,16,16, 31,32, 33},//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{ 4,28,13, 5};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{11,24,18,32};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{11,32,30,30};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
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
    //*****************************************************************//~vaauI~
    //*****************************************************************//~vaauI~
    //*****************************************************************//~vaauI~
    public void shuffleFreeWanpai(int Pcase)                       //~vaauI~
    {                                                              //~vaauI~
        if (Dump.Y) Dump.println("shuffleFreeWanpai test case="+Pcase); //~vaauI~//~vagvR~
        	int[][] itsDeal;                                       //~vaauI~
        	int[] itsDealTake;                                     //~vaauI~
            ArrayList<TileData> al = deepCopyToArrayList();        //~vaauI~
            TileData[] out = getNewAllTile();                      //~vaauI~
            int outctr = 14;                                       //~vaauI~
            int typectr = 0;                                       //~vaauI~
            int tc=PIECE_TYPECTR-1;                                //~vaauI~
            TileData td,tdnew;                                     //~vaauI~
        //*hands 4 player                                          //~vaauI~
        if (Pcase==101) //13Orphan                                 //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 27,28, 29},//~vaauI~
            						{ 0, 8, 9, 17,18,26, 27,28,29, 30,31, 32},//~vaauI~
            						{ 9,12,15, 20,20,20, 22,22,22, 23,23,23 },//~vaauI~
            						{10,13,16,  2, 2, 2,  4, 4, 4,  5, 5, 5 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{ 0,32,24,25};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0, 1,12,13};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==1011) //Other Yakuman                           //~vagvI~
        {                                                          //~vagvI~
        	itsDeal=new int[][]{                                   //~vagvI~
            						{19,20,21, 23,23,23, 25,25,25, 19,20, 21}, //all green without dragon//~vagvR~
            						{10,11,12, 17,17,17, 27,27,31, 31,32, 33},//~vagvR~
            						{ 9,12,15, 22,22,22, 24,24,24, 26,26,26 },//~vagvR~
            						{10,13,16,  2, 2, 2,  4, 4, 4,  5, 5,32 },//~vagvR~
            					};                                 //~vagvI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vagvI~
        	itsDealTake=new int[]{19,10,24,28};                    //~vagvR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vagvI~
        	itsDealTake=new int[]{28,19,12,13};                    //~vagvR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vagvI~
        }                                                          //~vagvI~
        else                                                       //~vagvI~
        if (Pcase==102) //7Pair tanyao                             //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 27,28, 29},//~vaauI~
            						{ 1, 1, 3,  3, 5, 5, 10,10,12, 12,19, 29},//~vaauR~
            						{ 9,12,15, 20,20,20, 22,22,22, 23,23,23 },//~vaauI~
            						{10,13,16,  2, 2, 2,  4, 4, 4,  6, 6, 6 },//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{ 0,26,24,25};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,28,12,13};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,19,12,13};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==103) //7Pair chanta                             //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 27,28, 29},//~vaauI~
            						{ 0, 0, 8,  8, 9, 9, 17,17,18, 18,19, 29},//~vaauI~
            						{ 9,12,15, 17,20,20, 22,22,22, 23,23,23 },//~vaauI~
            						{10,13,16,  2, 2, 2,  4, 4, 4,  6, 6, 6 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{ 0,26,24,25};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,28,12,13};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,19,12,13};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==104) //7Pair not tanyao                         //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 1, 1, 3, 11,14,17, 18,21,24, 27,29, 29},//~vaauR~
            						{ 0, 0, 2,  2, 3, 4,  4, 5, 5,  6, 7,  7},//~vaauR~
            						{ 9,12,15, 17,20,20, 22,22,22, 23,23,23 },//~vaauI~
            						{10,13,16,  2, 2, 3, 15,28,28,  6, 6, 1 },//~vaauR~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{ 9, 8,24,25};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 9, 8,12,13};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{10,12,12,13};                    //~vaauR~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else                                                       //~vaauI~
        if (Pcase==105) //7Pair samecolor                          //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 27,28, 27},//~vaauI~
            						{ 0, 0, 3,  3, 5, 5,  8, 8, 9,  9,28, 30},//~vaauI~
            						{ 9,12,15, 17,17,20, 21,22,22, 23,23,24 },//~vaauI~
            						{10,13,16,  2, 2, 2,  4, 4, 4, 11,14,16 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{ 0, 1,24,25};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0,28,12,13};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{ 0, 1,12,13};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
        else
        if (Pcase==106) //chankan ron 13orphan kokushi             //~vaauI~
        {                                                          //~vaauI~
        	itsDeal=new int[][]{                                   //~vaauI~
            						{ 0, 3, 6, 11,14,17, 18,21,24, 33,33, 33},//~vaauI~
            						{ 0, 8, 9, 17,18,26, 27,28,29, 30,31, 32},//~vaauI~
            						{ 9,12,15, 20,20,20, 22,22,22, 23,23,23 },//~vaauI~
            						{10,13,16,  2, 2, 2,  4, 4, 4,  5, 5, 5 },//~vaauI~
            					};                                 //~vaauI~
		    outctr=setTestDeal12(out,outctr,al,itsDeal);           //~vaauI~
        	itsDealTake=new int[]{ 1,32,24,25};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        	itsDealTake=new int[]{33, 1,12,13};                    //~vaauI~
    		outctr=setTestTake(out,outctr,al,itsDealTake);         //~vaauI~
        }                                                          //~vaauI~
                                                              //~vaauI~
        //*wanpai 14                                               //~vaauI~
            int outctr2=outctr;                                    //~vaauI~
            outctr=0;                                              //~vaauI~
		    setTestRemainRandom(out,outctr,al,14);                 //~vaauI~
            outctr=outctr2;                                        //~vaauI~
        //*remaining on stock                                      //~vaauI~
		    setTestRemainRandom(out,outctr,al);                    //~vaauI~
            shuffledTileData = out;                                //~vaauI~
        if (Dump.Y) Dump.println("shuffleFreeWanpai "+TileData.toString(shuffledTileData));//~vaauR~
    }                                                              //~vaauI~
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
                      if (swUseRed5)                               //~vaauI~
    		            tdnew.setRed5(td.ctrRemain==3||td.ctrRemain==2);//~1403R~
                    TileData.setTestSelectionOrder(tdnew,testOrder);//~va8xR~
                    out[outpos] = tdnew;                           //~1403I~
                }                                                  //~1403I~
                else                                               //~1403I~//~1412R~
                {                                                  //~1403I~//~1412R~
  		        	if (Dump.Y) Dump.println("setTestDeal12 @@@@remove tc="+tc);//~1412I~//~vaauR~
					UView.showToast("TilesTest.setTestDeal12 remove tc="+tc);//~vaauI~
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
                      if (swUseRed5)                               //~vaauI~
    		            tdnew.setRed5(td.ctrRemain==3||td.ctrRemain==2);//~1403I~
                    TileData.setTestSelectionOrder(tdnew,testOrder);//~va8xR~
                    out[outctr++] = tdnew;                         //~1403I~
                }                                                  //~1403I~
                else                                               //~1403I~
                {                                                  //~1403I~
  		        	if (Dump.Y) Dump.println("setTestTake @@@@remove tc="+tc);//~vaauR~
					UView.showToast("TilesTest.setTestTake remove tc="+tc);//~vaauI~
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
  		        	if (Dump.Y) Dump.println("setTestWanpai2 jj="+jj+",tc="+tc+",ctrReamin="+td.ctrRemain+",outpos="+outctr);//~1412I~//~vaauR~
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
    //*****************************************************************//~vaauI~
    private int ctrMax=0;                                           //~vaauI~
    private void setTestRemainRandom(TileData[] out,int outctr,ArrayList<TileData> al,int PctrMax)//~vaauI~
    {                                                              //~vaauI~
    	ctrMax=PctrMax;	                                           //~vaauI~
	    setTestRemainRandom(out,outctr,al);                        //~vaauI~
    	ctrMax=0;                                                  //~vaauI~
    }                                                              //~vaauI~
    //*****************************************************************//~1412I~
    private void setTestRemainRandom(TileData[] out,int outctr,ArrayList<TileData> al)//~1402R~
    {                                                              //~1402I~
//  		int typectr=al.size()-1;                               //~1402R~//~1426R~
    		int typectr=al.size();                                 //~1426I~
            int ctrPut=0;                                          //~vaauI~
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
                    ctrPut++;                                      //~vaauI~
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
                if (ctrMax!=0 && ctrPut==ctrMax)                   //~vaauI~
                	break;                                         //~vaauI~
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
    	        tc=ii==3 ? 1 : 0;    //  for human=2                 //~1328R~//~va8xR~
//  	        tc=(ii>=2) ? 1 : 0;  //for human=1                 //~va8xI~
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
	    shuffleMinkanTest(9902);                                       //~0407I~//~vaauR~
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
}
