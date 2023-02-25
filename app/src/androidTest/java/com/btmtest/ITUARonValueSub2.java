//*CID://+vagrR~: update#=1098;                                    //~vagrR~
//**********************************************************************//~v101I~
//2021/11/14 vagr (Bug of vafh)determins honchan when pillow:tanyao//~vagrI~
//2020/11/02 va23 use Junit for UARonValue                         //~va23I~
//**********************************************************************//~1107I~
package com.btmtest;                                               //~va23I~

import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.Players;
import com.btmtest.game.TileData;
import com.btmtest.game.UA.Pair;
import com.btmtest.game.UA.Rank;
import com.btmtest.game.UA.RonResult;
import com.btmtest.game.UA.UARonValue;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import static com.btmtest.StaticVars.AG;                           //~vagrI~

import static com.btmtest.game.GCMsgID.GCM_TAKE;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.UA.Pair.*;


public class ITUARonValueSub2 extends ITUARonValueSub              //~vagrR~
{                                                                  //~va11I~
    protected void init(){};                                       //~vagrI~
    public void setFixOption(int ii)                               //~vagrI~
    {                                                              //~vagrI~
            	if (ii==0) RuleSettingYaku.setYakuFixForIT(0,false); //LAST//~vagrI~
            	if (ii==1) RuleSettingYaku.setYakuFixForIT(2,false); //FIRST//~vagrI~
            	if (ii==2) RuleSettingYaku.setYakuFixForIT(2,true ); //FIRST//~vagrI~
            	if (ii==3) RuleSettingYaku.setYakuFixForIT(1,false); //MIDDLE//~vagrI~
            	if (ii==4) RuleSettingYaku.setYakuFixForIT(1,true ); //MIDDLE//~vagrI~
    }                                                              //~vagrI~
	public RonResult ronTestValue5(int PtestCase)                  //~vagrR~
	{                                                              //~vagrI~
	    RonResult rc=null;                                         //~vagrI~
        int testCase=PtestCase;                                    //~vagrR~
        boolean swTestAll=true;                                    //~vagrR~
    switch(testCase)                                               //~vagrI~
    {                                                              //~vagrI~
//*********                                                        //~vagrI~
    case 22901101:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901101 big 3dragon NR 3anko tanki rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
//*********                                                        //~vagrI~
    case 22901102:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901102 big 3dragon NR shabo  rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
//*********                                                        //~vagrI~
    case 22901103:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901103 big 3dragon NR shabo TAKE rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
//*********                                                        //~vagrI~
    case 22901104:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,3, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901104 big 3dragon R+NR 2anko tanki rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901105:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,3, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901105 big 3dragon R+NR shabo rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901106:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,3, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901106 big 3dragon R+NR shabo TAKE rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901107:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,3, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901107 big 3dragon R+R 1anko tanki rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901108:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,3, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901108 big 3dragon R+1anko shaboo rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901109:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,3, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,3, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901109 big 3dragon R+1anko shaboo Take rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
//*********                                                        //~vagrI~
//*3dragon little small                                            //~vagrI~
    case 22901111:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901111 little 3dragon NR 2anko tanki rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
//*********                                                        //~vagrI~
    case 22901112:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901112 little 3dragon NR shabo  rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
//*********                                                        //~vagrI~
    case 22901113:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901113 little 3dragon NR shabo TAKE rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
//*********                                                        //~vagrI~
    case 22901114:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901114 little 3dragon R+NR 1anko tanki rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011141:                                                //~vagrR~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011141 little 3dragon R+NR 1anko shabo other rc="+rc);//~vagrR~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011142:                                                //~vagrR~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011142 little 3dragon R+NR 1anko shabo other TAKE  rc="+rc);//~vagrR~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011143:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011143 little 3dragon R+NR 1anko shabo WGR RON rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011144:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011144 little 3dragon R+NR 1anko shabo WGR TAKE  rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011145:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011145 little 3dragon NR+R 1anko shabo WGR rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011146:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011146 little 3dragon NR+R 1anko TAKE WGR rc="+rc);//~vagrR~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011147:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011147 little 3dragon NR+R 1anko tanki WGR rc="+rc);//~vagrR~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011148:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011148 little 3dragon NR+R 1anko tanki TAKE WGR rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901115:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901115 little 3dragon R+NR shabo rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011151:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011151 little 3dragon R+NR+R shabo other rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011152:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011152 little 3dragon R+NR+R tanki other rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011153:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011153 little 3dragon R shabo other Ron ="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 229011154:                                                //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011154 little 3dragon R shabo other Ron ="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901116:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901116 little 3dragon R+NR shabo TAKE rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901117:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901117 little 3dragon R+R       tanki rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901118:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901118 little 3dragon R+1anko shaboo rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901119:                                                 //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,3,2, 0,0} };                         //7 //~vagrI~
        dupCtrAll=new int[][]{                                     //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  3,3,2, 0,0} };                         //7 //~vagrI~
        pairEarth=new Pair[]{                                      //~vagrI~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON),//~vagrI~
        };                                                         //~vagrI~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*saTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901119 little 3dragon R+1anko shaboo Take rc="+rc);//~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
        if (ITUARonValue.MaxCase<22901201)                         //~vagrI~
        	break;                                                  //~vagrI~
//*honor                                                           //~vagrI~
			case 22901201:                                         //~vagrR~
			for (int ii=0;ii<5;ii++)                               //~vagrI~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
				                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				                                                 //~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901201 Honor no earth anko rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901202:                                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{4, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901202 Honor no earth anko:Kan rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901203:                                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = null ;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901203 Honor no earth shabo R+R rc=" + rc);//~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null ;                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901203-2 Honor no earth shabo R+R TAKErc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901204:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{3, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{3, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = null;                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901204 Honor no earth shabo R+NR rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 1, 1, 1, 1, 1, 1, 0},               //~vagrI~
						{3, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 1, 1, 1, 1, 1, 1, 0},               //~vagrI~
						{3, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901204-2 Honor no earth shabo R+NR Take rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901205:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{3, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{3, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = null;                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrR~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901205 Honor no earth shabo R+NR TAKE rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901206:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrI~
						{4, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901206 Honor R only rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901207:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 2, 3, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{3, 0, 0, 0, 2, 3, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901207 Honor R , shabo R+R rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901208:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{2, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901208 Honor R , shabo R+NR:NRrc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901209:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901209 Honor NR , anko R rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901210:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),  //NR//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901210 Honor NR , shabo RR rc=" + rc);//~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901210-2 honor NR , shabo RR TAKE rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901211:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{0, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{0, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901211 Honor NR , shabo RNR rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901212:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901212 Honor NR , shabo RNR TAKE rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901213:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{2, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0,0, 0},               //~vagrR~
						{2, 0, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrR~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901213 Honor R+NR , shabo RR rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901214:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 2, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 1, 1,1, 0},               //~vagrI~
						{0, 2, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901214 Honor R+NR , shabo RNR rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901215:                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 2, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 1, 1,1, 0},               //~vagrI~
						{0, 2, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true /*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901215 Honor R+NR , shabo RNR TAKE rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012016:                                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 2, 0, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 2, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrR~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901216 Honor NR+R            rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012017:                                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 2, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 2, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901217 Honor NR+R  anko      rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012018:                                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
				if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901218 Honor NR+R  shabo RR  rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,true /*swTaken*/);//~vagrR~
				if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901218-2-TAKE Honor NR+R  shabo RR  rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012019 :                                         //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901219 Honor NR+R  shabo RNR  rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012020:                                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 2, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901220 Honor NR+R  shabo RNR  TAKE rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012021:                                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901221 Honor NR+R+R shabo RNR shabo NR RON rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012022:                                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 2, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 2, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901222 Honor NR+R+R shabo RNR shabo R RON rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012023:                                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 2, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 2, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901223 Honor NR+R+R shabo RNR shabo R TAKE rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012024:                                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 2, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 2, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901224 Honor NR shabo R+NR shabo R RON rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 229012025:                                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 2, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 2, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)                                        //~vagrI~
					Dump.println("ITUARonValueSub.ronTest-22901225 Honor NR shabo RNR shabo TAKE rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
       	}//for  ii=0-4                                             //~vagrI~
        if (ITUARonValue.MaxCase<22901301) break;                  //~vagrI~
//*********                                                        //~vagrI~
//* 3colorStraight                                                 //~vagrI~
    case 22901301:                                                 //~vagrR~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 2,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 2,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901301 3colorStraight STD rc="+rc.toString());//~vagrI~
    case 22901302:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{1,1,1, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{1,1,1, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901302-1 3colorStraight +1double-1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 1,1,1, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 1,1,1, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901302-2 3colorStraight +1double-2 pinfu rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 1,1,1},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 1,1,1},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901302-2 3colorStraight +1double-3 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901303:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{2,2,2, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{2,2,2, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901303-1 3colorStraight +1peiko-1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},        //pinfu                  //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,2, 2,2,2, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},        //pinfu                  //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,2, 2,2,2, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901303-2 3colorStraight +1peiko-2 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 0,0,0, 2,2,2},                                 //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,2,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 0,0,0, 2,2,2},                                 //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,2,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901303-3 3colorStraight +1peiko-3 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901304:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,2,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,2,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901304-1 3colorStraight ERR rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,2,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,2,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901304-2 3colorStraight ERR rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,2,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,2,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901304-2 3colorStraight ERR rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901305:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrR~
		};//~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901305-1 3colorStraight Related 1Earth rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,1,1, 1,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSAME, 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),
		};//~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901305-2 3colorStraight Related 1Earth rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901306:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				new Pair(PT_NUMSEQ, 2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				new Pair(PT_NUMSAME,3/*type*/, 1/*number*/, 3/*ctr*/, TDF_PON ),//~vagrI~
		};                                                         //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901305-1 3colorStraight RRRNR tanki    rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
//*3colorstraight chkEarth                                         //~vagrI~
    case 22901401:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901401 3colorStraight menzen ron NR  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,4,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSAME, 3/*type*/, 1/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
		};                                                         //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901401 3colorStraight menzen ANKAN ron NR  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901402:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = null;                                          //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901402-1 3colorStraight menzen ryanmen 4  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901402-2 3colorStraight menzen ryanmen 6  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901402-3 3colorStraight menzen kanchan 5 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901402-4 3colorStraight menzen penchan 3  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901402-5 3colorStraight menzen penchan 7  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901402-6 3colorStraight menzen ryanmen 1  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901402-7 3colorStraight menzen ryanmen 9  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901402-8 3colorStraight menzen kanchan 2  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901402-9 3colorStraight menzen kanchan 8  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901403:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrR~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
		};                                                         //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901403 3colorStraight R, RR, ron NR  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901404:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{2,2,2, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
		};                                                         //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901404-01 3colorStraight R, RNR ron Rdup Ryanmen rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901404-02 3colorStraight R, RNR ron Rdup kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901404-03 3colorStraight R, RNR ron Rdup penchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901405:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{2,2,2, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
		};                                                         //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901405-01 3colorStraight R, RNR ron lastR Ryanmen rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901405-02 3colorStraight R, RNR ron lastR kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901405-03 3colorStraight R, RNR ron lastR penchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901405-01 3colorStraight R, RNR TAKE lastR Ryanmen rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,true /*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901405-02 3colorStraight R, RNR TAKE lastR kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,true /*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901405-03 3colorStraight R, RNR TAKE lastR penchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901406:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
		};                                                         //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901306 3colorStraight NR, RRR, tank  rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901407:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
		};                                                         //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901407-01 3colorStraight NR, RR, ron R ryanmen rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901407-02 3colorStraight NR, RR, ron R kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901407-03 3colorStraight NR, RR, ron R penhan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,true /*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901407-04 3colorStraight NR, RR, TAKE R ryanmen rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,true /*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901407-05 3colorStraight NR, RR, TAKE R kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,true /*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901407-06 3colorStraight NR, RR, TAKE R penhan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901408:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
		};                                                         //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901408-01 3colorStraight RNR, RR, ron tanki rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901408-02 3colorStraight RNR, R, ron R ryanmen rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901408-03 3colorStraight RNR, R, ron R kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901408-04 3colorStraight RNR, R, ron R penchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901408-05 3colorStraight RNR, R, TAKE R ryanmen rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901408-06 3colorStraight RNR, R, TAKE R kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901408-07 3colorStraight RNR, R, TAKE R penchan rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
    case 22901409:                                                 //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		pairEarth = new Pair[]{                                    //~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
		};                                                         //~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901409-01 3colorStraight RNRR, R, ron Tanki rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901409-02 3colorStraight RNRR, R ron ryanmen rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901409-03 3colorStraight RNRR, R ron kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901409-04 3colorStraight RNRR, R ron penchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901409-05 3colorStraight RNRR, R TAKE ryanmen rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901409-06 3colorStraight RNRR, R TAKE kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901409-09 3colorStraight RNRR, R TAKE penchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        if (!swTestAll) break;                                     //~vagrI~
        if (ITUARonValue.MaxCase<22901501)                         //~vagrR~
        	break;                                                 //~vagrI~
//3shiki chkEarth                                                  //~vagrI~
    case 22901501:                                                 //~vagrI~
			for (int ii=0;ii<5;ii++)                               //~vagrI~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901501-1 3shiki menzen last NR tanki  rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901501-2 3shiki menzen last ryanmen  rc=" + rc.toString());//~vagrM~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, true /*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901501-2-TAKE 3shiki menzen last ryanmen TAKE rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901501-3 3shiki menzen last penchan FIX rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901502:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901502-1 3shiki R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901502-2 3shiki R, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901502-2-TAKE 3shiki R, last Not FIX ryanmen TAKE rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901502-3 3shiki R, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901503:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901503-1 3shiki NR, last FIX Tanki rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901503-2 3shiki NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901503-2-TAKE 3shiki NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901503-3 3shiki NR, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901504:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901504-1 3shiki R+NR, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901504-2 3shiki R+NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901504-2-TAKE 3shiki R+NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901504-3 3shiki R+NR, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901505:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901505-1 3shiki NR+R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901505-2 3shiki NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901505-2-TAKE 3shiki NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901505-3 3shiki NR+R, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901506:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901506-1 3shiki R+NR+R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901506-2 3shiki R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901506-2-TAKE 3shiki R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901506-3 3shiki R+NR+R, last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901507:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901507-1 3shiki R+NR+R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901507-2 3shiki R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901507-2-TAKE 3shiki R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901507-3 3shiki R+NR+R, last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901508:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901508-1 3shiki R+NR+R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901508-2 3shiki R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901508-2-TAKE 3shiki R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901508-3 3shiki R+NR+R, last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc=ronTest54(testCase);                            //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
       	}//for  ii=0-4                                             //~vagrI~
        if (ITUARonValue.MaxCase<22901601) break;                  //~vagrR~
//**********straight chkEarth                                      //~vagrR~
    case 22901601:                                                 //~vagrI~
			for (int ii=0;ii<5;ii++)                               //~vagrI~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*swTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901601-1 straight menzen last NR tanki  rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*swTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901601-2 straight menzen last ryanmen  rc=" + rc.toString());//~vagrM~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, true/*swTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901601-2-TAKE straight menzen last ryanmen  rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*swTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901601-3 straight menzen last penchan FIX rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901602:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901602-1 straight R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901602-2 straight R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901602-2-TAKE straight R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901602-3 straight R, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901603:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901603-1 straight NR, last FIX Tanki rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901603-2 straight NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901603-2-TAKE straight NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901603-3 straight NR, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901604:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901604-1 straight R+NR, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901604-2 straight R+NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901604-2-TAKE straight R+NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901604-3 straight R+NR, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901605:                                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrM~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901605-1 straight NR+R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901605-2 straight NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901605-2-TAKE straight NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901605-3 straight NR+R, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901606:  //R,NR,R                                     //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),   //R//~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),   //NR//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),   //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901606-1 straight R+NR+R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901606-2 straight R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901606-2-TAKE straight R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901606-3 straight R+NR+R, last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901607:  //R,R                                        //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),   //R//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),   //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901607-1 straight R+R, last FIX Tanki rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901607-2 straight R+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901607-2-TAKE straight R+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901607-3 straight R+R, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22901608:  //R,R,NR                                     //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),   //R//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),   //R//~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),   //NR//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901608-1 straight R+R+NR, last FIX Tanki rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901608-2 straight R+R+NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901608-2-TAKE straight R+R+NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901608-3 straight R+R+NR, last FIX penchan rc=" + rc.toString());//~vagrR~
				rc=ronTest53(testCase);                            //~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
       	}//for  ii=0-4                                             //~vagrI~
        if (ITUARonValue.MaxCase<22901701)                         //~vagrR~
               break;                                                    //~vagrI~
//************straight3                                            //~vagrI~
    		case 22901701:                                         //~vagrR~
				ronTestValue52(testCase);                          //~vagrI~
        		if (ITUARonValue.MaxCase<22901801)  break;         //~vagrR~
                                                                   //~vagrI~
//*honor                                                           //~vagrI~
			case 22901801:    //RR                                 //~vagrI~
				ronTestValue6(testCase);                            //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
    default:       //value3                                        //~vagrI~
				ronTestValue6(testCase);                           //~vagrI~
        if (swTestAll) break;                                      //~vagrI~
        UView.showToastLong("testCaseValue5 ERR="+testCase);       //~vagrR~
    }                                                              //~vagrI~
        return rc;                                                 //~vagrI~
    }//func                                                        //~vagrR~
                                                                   //~vagrI~
//************************************************************     //~vagrI~
	private RonResult ronTestValue52(int PtestCase)                //~vagrI~
	{                                                              //~vagrI~
	    RonResult rc=null;                                         //~vagrI~
        boolean swTestAll=true;                                    //~vagrI~
                                                                   //~vagrM~
			for (int ii=0;ii<5;ii++)                               //~vagrM~
        	{                                                      //~vagrM~
			    setFixOption(ii);                                  //~vagrM~
//    case 22901701:                                               //~vagrI~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,3,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,3,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		pairEarth = null;                                          //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901701-01 3colorStraight menxen NR rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901701-02 3colorStraight menxen R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901701-02-TAKE 3colorStraight menxen R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901701-03 3colorStraight menxen R kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 2/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901701-04-TAKE 3colorStraight menxen R penchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
//    case 22901702:                                               //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,3,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,3,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901702-01 3colorStraight R, NR rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901702-02 3colorStraight R, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901702-02-TAKE 3colorStraight R, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901702-03 3colorStraight R, R kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901702-04-TAKE 3colorStraight R, R penchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
//    case 22901703:                                               //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 1,1,1, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901703-01 3colorStraight NR, NR rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901703-02 3colorStraight NR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901703-02-TAKE 3colorStraight NR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901703-03 3colorStraight NR, R kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901703-04-TAKE 3colorStraight NR, R penchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
//    case 22901704:                                               //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 1,1,1, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII),//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901704-01 3colorStraight RNR, NR tanki rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901704-02 3colorStraight RNR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901704-02-TAKE 3colorStraight RNR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901704-03 3colorStraight RNR, R kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901704-04-TAKE 3colorStraight RNR, R penchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
//    case 22901705:                                               //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 1,1,1, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII),//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901705-01 3colorStraight NRR, NR tanki rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901705-02 3colorStraight NRR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901705-02-TAKE 3colorStraight NRR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901705-03 3colorStraight NRR, R kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901705-04-TAKE 3colorStraight NRR, R penchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
//    case 22901706:      //R NR R                                 //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 1,1,1, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901706-01 3colorStraight RNRR, NR tanki rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901706-02 3colorStraight RNRR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901706-02-TAKE 3colorStraight RNRR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901706-03 3colorStraight RNRR, R kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901706-04-TAKE 3colorStraight RNRR, R penchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
//    case 22901707:      //R R                                    //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrM~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 1,1,1, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901707-01 3colorStraight RNRR, NR tanki rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901707-02 3colorStraight RNRR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901707-02-TAKE 3colorStraight RNRR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901707-03 3colorStraight RNRR, R kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901707-04-TAKE 3colorStraight RNRR, R penchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
//    case 22901708:      //R R NR                                 //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 1,1,1, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901708-01 3colorStraight RNRR, NR tanki rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901708-02 3colorStraight RNRR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901708-02-TAKE 3colorStraight RNRR, R ryanmen rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901708-03 3colorStraight RNRR, R kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901708-04-TAKE 3colorStraight RNRR, R penchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
//    case 22901709:      //dup  test                              //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = null;                                  //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-01 3colorStraight Near menzen 456 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-02 3colorStraight Near menzen ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-03 3colorStraight Near menzen ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-04 3colorStraight Near menzen ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-05 3colorStraight Near menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-06 3colorStraight Near menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{2,2,2, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{2,2,2, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = null;                                  //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-07 3colorStraight Near menzen 123 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-08 3colorStraight Near menzen ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-09 3colorStraight Near menzen ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-10 3colorStraight Near menzen ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-11 3colorStraight Near menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901709-12 3colorStraight Near menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
//    case 22901710:      //dup on earth                           //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-01 3colorStraight DupEarth R+ 456 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-02 3colorStraight DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-03 3colorStraight DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-04 3colorStraight DupEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-05 3colorStraight DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-06 3colorStraight DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{2,2,2, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-07 3colorStraight DupEarth R+ 123 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-08 3colorStraight DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-09 3colorStraight DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-10 3colorStraight DupEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-11 3colorStraight DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901710-12 3colorStraight DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
//    case 22901711:      //ron near with earth                    //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,1, 1,1,0, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,1, 2,2,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-01 3colorStraight NearEarth R+ 456 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-02 3colorStraight NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-03 3colorStraight NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-04 3colorStraight NearEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-05 3colorStraight NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-06 3colorStraight NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{0,1,1, 1,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,2,2, 1,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-07 3colorStraight NearEarth R+ 123 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-08 3colorStraight NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-09 3colorStraight NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-10 3colorStraight NearEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-11 3colorStraight NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901711-12 3colorStraight NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
//    case 22901712:      //ron member near on earth  NR+          //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,1, 2,2,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 2/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-01 3colorStraight NearEarth NR+ 456 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-02 3colorStraight NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-03 3colorStraight NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-04 3colorStraight NearEarth NR+ ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-05 3colorStraight NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-06 3colorStraight NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,2,2, 1,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-07 3colorStraight NearEarth NR+ 123 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-08 3colorStraight NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-09 3colorStraight NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-10 3colorStraight NearEarth NR+ ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-11 3colorStraight NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901712-12 3colorStraight NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
//    case 22901713:      //ron member near on earth    NR+R+      //~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,1, 2,2,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 2/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-01 3colorStraight NearEarth NR+R+ 456 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-02 3colorStraight NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-03 3colorStraight NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-04 3colorStraight NearEarth NR+R+ ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-05 3colorStraight NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-06 3colorStraight NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
        dupCtr=new int[][]{                                        //~vagrM~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
		dupCtrAll = new int[][]{                                   //~vagrM~
        	{1,2,2, 1,0,0, 0,0,0},        //pinfu                  //~vagrM~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrM~
        	{0,0,2, 0,0,0, 1,1,1},                                 //~vagrM~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrM~
				};                                                 //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-07 3colorStraight NearEarth NR+R+ 123 ryanmen1 rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-08 3colorStraight NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-09 3colorStraight NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-10 3colorStraight NearEarth NR+R+ ryanmen1 TAKE rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-11 3colorStraight NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901713-12 3colorStraight NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrM~
                                                                   //~vagrM~
		if (!swTestAll) break;                                     //~vagrM~
                                                                   //~vagrM~
       	}//for  ii=0-4                                             //~vagrM~
        return rc;                                                 //~vagrI~
    } //ronTestValue52                                             //~vagrI~
//************************************************************     //~vagrR~
//*straight chk earth dup meld                                     //~vagrI~
//************************************************************     //~vagrI~
	private RonResult ronTest53(int PtestCase)                     //~vagrR~
	{                                                              //~vagrI~
	    RonResult rc=null;                                         //~vagrI~
        boolean swTestAll=true;                                    //~vagrI~
//    case 22901609:      //dup on earth                           //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 2,2,2, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{1,1,1, 2,2,2, 1,1,1},                                 //~vagrI~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = null;                                  //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-01 3colorStraight Near menzen 456 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-02 3colorStraight Near menzen ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-03 3colorStraight Near menzen ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-04 3colorStraight Near menzen ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-05 3colorStraight Near menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-06 3colorStraight Near menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{2,2,2, 1,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{2,2,2, 1,1,1, 1,1,1},                                 //~vagrI~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = null;                                  //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-07 3colorStraight Near menzen 123 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-08 3colorStraight Near menzen ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-09 3colorStraight Near menzen ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-10 3colorStraight Near menzen ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-11 3colorStraight Near menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901609-12 3colorStraight Near menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
//    case 22901610:      //dup on earth R+                        //~vagrR~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 2,2,2, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-01 3colorStraight DupEarth R+ 456 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-02 3colorStraight DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-03 3colorStraight DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-04 3colorStraight DupEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-05 3colorStraight DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-06 3colorStraight DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{2,2,2, 1,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-07 3colorStraight DupEarth R+ 123 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-08 3colorStraight DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-09 3colorStraight DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-10 3colorStraight DupEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-11 3colorStraight DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901610-12 3colorStraight DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
//    case 22901611:      //ron near with earth                    //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,2, 1,1,0, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-01 3colorStraight NearEarth R+ 456 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-02 3colorStraight NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-03 3colorStraight NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-04 3colorStraight NearEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-05 3colorStraight NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-06 3colorStraight NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{0,1,1, 2,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,2,2, 2,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-07 3colorStraight NearEarth R+ 123 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-08 3colorStraight NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-09 3colorStraight NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-10 3colorStraight NearEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-11 3colorStraight NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901611-12 3colorStraight NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
//    case 22901612:      //ron member near on earth  NR+          //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 2/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-01 3colorStraight NearEarth NR+ 456 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-02 3colorStraight NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-03 3colorStraight NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-04 3colorStraight NearEarth NR+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-05 3colorStraight NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-06 3colorStraight NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,2,2, 2,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrR~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-07 3colorStraight NearEarth NR+ 123 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-08 3colorStraight NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-09 3colorStraight NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-10 3colorStraight NearEarth NR+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-11 3colorStraight NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901612-12 3colorStraight NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
//    case 22901613:      //ron member near on earth    NR+R+      //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 1,1,1, 0,0,0},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 2/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-01 3colorStraight NearEarth NR+R+ 456 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-02 3colorStraight NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-03 3colorStraight NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-04 3colorStraight NearEarth NR+R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-05 3colorStraight NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-06 3colorStraight NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 1,1,1, 0,0,0},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,2,2, 2,1,1, 1,1,1},                                 //~vagrR~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
						new Pair(PT_NUMSEQ, 1/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-07 3colorStraight NearEarth NR+R+ 123 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-08 3colorStraight NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-09 3colorStraight NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-10 3colorStraight NearEarth NR+R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-11 3colorStraight NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrR~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901613-12 3colorStraight NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
        return rc;                                                 //~vagrI~
}//rontestValue53                                                  //~vagrI~
//************************************************************     //~vagrI~
//*3shiki   chk earth dup meld                                     //~vagrI~
//************************************************************     //~vagrI~
	private RonResult ronTest54(int PtestCase)                     //~vagrI~
	{                                                              //~vagrI~
	    RonResult rc=null;                                         //~vagrI~
        boolean swTestAll=true;                                    //~vagrI~
//    case 22901509:      //dup 1peiko                             //~vagrR~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrR~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~vagrR~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~vagrR~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrI~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = null;                                  //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-01 3shiki Dup menzen 456 ryanmen1 rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-02 3shiki Dup menzen ryanmen Kanchan rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-03 3shiki Dup menzen ryanmen Kanchan rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-04 3shiki Dup menzen ryanmen1 TAKE rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-05 3shiki Dup menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-06 3shiki Dup menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{2,2,2, 0,0,0, 0,0,0},                                 //~vagrR~
        	{1,1,1, 2,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrR~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{2,2,2, 0,0,0, 0,0,0},                                 //~vagrI~
        	{1,1,1, 2,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrR~
				pairEarth = null;                                  //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-07 3shiki Dup menzen 123 ryanmen1 rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-08 3shiki Dup menzen ryanmen Kanchan rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-09 3shiki Dup menzen ryanmen Kanchan rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-10 3shiki Dup menzen ryanmen1 TAKE rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-11 3shiki Dup menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrR~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901509-12 3shiki Dup menzen ryanmen TAKE Kanchan rc="+rc.toString());//~vagrR~
//    case 22901510:      //dup on earth R+                        //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrR~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrR~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~vagrR~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-01 3shiki DupEarth R+ 456 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-02 3shiki DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-03 3shiki DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-04 3shiki DupEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-05 3shiki DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-06 3shiki DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrR~
        	{1,1,1, 2,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrR~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{2,2,2, 0,0,0, 0,0,0},                                 //~vagrR~
        	{1,1,1, 2,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-07 3shiki DupEarth R+ 123 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-08 3shiki DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-09 3shiki DupEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-10 3shiki DupEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-11 3shiki DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901510-12 3shiki DupEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
//    case 22901511:      //near with earth R                      //~vagrR~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrR~
        	{0,0,1, 1,1,0, 0,0,0},                                 //~vagrR~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrR~
        	{0,0,1, 2,2,1, 0,0,0},                                 //~vagrR~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-01 3shiki NearEarth R+ 456 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-02 3shiki NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-03 3shiki NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-04 3shiki NearEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-05 3shiki NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-06 3shiki NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~vagrR~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~vagrR~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,2,2, 1,0,0, 0,0,0},                                 //~vagrR~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-07 3shiki NearEarth R+ 123 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-08 3shiki NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-09 3shiki NearEarth R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-10 3shiki NearEarth R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-11 3shiki NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901511-12 3shiki NearEarth R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
//    case 22901512:      //ron member near on earth  NR+          //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrR~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrR~
        	{0,0,1, 2,2,1, 0,0,0},                                 //~vagrR~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 2/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-01 3shiki NearEarth NR+ 456 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-02 3shiki NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-03 3shiki NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-04 3shiki NearEarth NR+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-05 3shiki NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-06 3shiki NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrR~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~vagrR~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{1,1,1, 0,0,0, 0,0,0},        //pinfu                  //~vagrR~
        	{1,2,2, 1,0,0, 0,0,0},                                 //~vagrR~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-07 3shiki NearEarth NR+ 123 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-08 3shiki NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-09 3shiki NearEarth NR+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-10 3shiki NearEarth NR+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-11 3shiki NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901512-12 3shiki NearEarth NR+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
//    case 22901513:      //ron member near on earth    NR+R+      //~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrR~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~vagrR~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 1,1,1, 0,0,0},        //pinfu                  //~vagrR~
        	{0,0,1, 2,2,1, 0,0,0},                                 //~vagrR~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~vagrR~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 2/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrI~
						new Pair(PT_NUMSEQ, 2/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-01 3shiki NearEarth NR+R+ 456 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-02 3shiki NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-03 3shiki NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-04 3shiki NearEarth NR+R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-05 3shiki NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-06 3shiki NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
        dupCtr=new int[][]{                                        //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{1,1,1, 1,1,1, 0,0,0},                                 //~vagrI~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
		dupCtrAll = new int[][]{                                   //~vagrI~
        	{0,0,0, 0,0,0, 0,0,0},        //pinfu                  //~vagrI~
        	{1,2,2, 2,1,1, 1,1,1},                                 //~vagrI~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~vagrI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-07 3shiki NearEarth NR+R+ 123 ryanmen1 rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-08 3shiki NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-09 3shiki NearEarth NR+R+ ryanmen Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-10 3shiki NearEarth NR+R+ ryanmen1 TAKE rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-11 3shiki NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
                                                                   //~vagrI~
		rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-242901513-12 3shiki NearEarth NR+R+ ryanmen TAKE Kanchan rc="+rc.toString());//~vagrI~
        return rc;                                                 //~vagrI~
}//rontestValue54                                                  //~vagrI~
//***********************************************************      //~vagrI~
	private RonResult ronTestValue6(int PtestCase)                 //~vagrI~
	{                                                              //~vagrI~
	    RonResult rc=null;                                         //~vagrI~
        int testCase=PtestCase;                                    //~vagrI~
        boolean swTestAll=true;                                    //~vagrI~
    switch(testCase)                                               //~vagrI~
    {                                                              //~vagrI~
			case 22901801:    //RR                                 //~vagrI~
                                                                   //~vagrI~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 2, 3, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 2, 3, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = null;                                  //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901801   Honor menzen R+R rc=" + rc);//~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901802:      //RNR                              //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 2, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 2, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = null;                                  //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901802   Honor menzen R+NR,R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901803:      //RNR                              //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 3, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 2, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 3, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 2, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = null;                                  //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901803   Honor menzen R+NR,NR rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901804:   //R, RR                               //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 2, 3, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 2, 3, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901804      Honor R,     R+R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901805:   //R, RNR                              //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 2, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 2, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901805     Honor R,     R+NR,R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901806:   //R, NRR                              //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 2, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 0, 2, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901806     Honor R,    NR+R,NR rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901807:   //NR,RR                               //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901807     Honor NR,  RR      rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901808:   //NR,RNR                              //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{2, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{2, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901808     Honor NR,  RNR     rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901809:   //NR,RNR                              //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 0, 0, 0, 3, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901809     Honor NR,  RNR,NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901810:   //R+NR,RR                             //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{3, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //NR//~vagrR~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901810     Honor RNR,  RR,R  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901811:   //R+NR,RNR                            //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 2, 3, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{3, 0, 0, 2, 3, 0, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //NR//~vagrR~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901811     Honor RNR,  RNR,R  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901812:   //R+NR,RNR                            //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 3, 2, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{3, 0, 0, 3, 2, 0, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //NR//~vagrR~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901812     Honor RNR,  RNR,NR rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901813:   //NR+R,RR                             //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 3, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{3, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //NR//~vagrR~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901813     Honor NR+R,  RR rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901814:   //NR+R,NRR                            //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 2, 3, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{3, 0, 0, 2, 3, 0, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //NR//~vagrR~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901814     Honor NR+R, NRR rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901815:   //NR+R,NRR                            //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 3, 2, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrR~
						{3, 0, 0, 3, 2, 0, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //NR//~vagrR~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901815     Honor NR+R, NRR,NR rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
                                                                   //~vagrM~
			case 22901816:   //R+NR+R,RNR                          //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 5/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901816     Honor R+NR+R, NRR,R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
                                                                   //~vagrM~
			case 22901817:   //R+NR+R,RNR                          //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 5/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901817     Honor R+NR+R, NRR,NRR rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
                                                                   //~vagrM~
			case 22901819:   //R+R,RR                              //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 2, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901819     Honor R+R, RR, rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901820:   //R+R,RNR                             //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{2, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{2, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901820     Honor R+R, RNR, rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
                                                                   //~vagrM~
			case 22901821:   //R+R,RNR                             //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 2, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901821     Honor R+R, RNR, rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901822:   //R+R+NR,RNR                          //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{2, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 5/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901822     Honor R+RNR, RNR, rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
			case 22901823:   //R+R+NR,RNR                          //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 2, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrM~
						{3, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrM~
                                                                   //~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON), //R//~vagrM~
						new Pair(PT_NUMSEQ, 2/*type*/, 5/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrM~
				};                                                 //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901823     Honor R+RNR, RNR,NR rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
        if (ITUARonValue.MaxCase<22901901)     break;              //~vagrM~
//3WindNoHonor  Earth                                              //~vagrI~
			case 22901901:                                         //~vagrI~
				                                                   //~vagrI~
			for (int ii=0;ii<5;ii++)                               //~vagrI~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 3, 3, 0, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 3, 3, 4, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_TAKEN)//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901901 3wind menzenn tanki allInHand with ankan rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 4, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_RIVER)//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901901-2 3wind menzenn tanki with minkan rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901902:      //menzenn RNR                    //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901902 3wind menzenn RNR,R last fix rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901902-TAKE 3wind menzenn RNR,R last fix take rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901903:      //menzenn                        //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 2, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 2, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901903 3wind menzenn RNR,NR=East 0 han      rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901904:    //R+                               //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 3/*ctr*/, TDF_PON) //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, false /*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901904-1 3wind  R+, tanki rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false /*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901904-2 3wind  R+, RNR,R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false /*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901904-2-TAKE 3wind  R+, RNR,R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901905:    //NR+                              //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//NR//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, false /*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901905-1 3wind NR+,tanki rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false /*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901905-2 3wind NR+,RNR,R rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false /*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901905-3=TAKE 3wind  NR+,RNR,R TAKE rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901906:                                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 3/*ctr*/, TDF_PON ), //R//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//NR//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901906-1 3wind R+NR,tanki rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901906-2 3wind R+NR,RNR rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901906-2-TAKE 3wind R+NR,RNR rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901907:                                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrR~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 3/*ctr*/, TDF_PON ), //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901907-1 3wind NR+R,TANKI rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901907-2 3wind NR+R,RNR rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901907-2-TAKE 3wind NR+R,RNR rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901908:                                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 3/*ctr*/, TDF_PON ), //R//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrR~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 2/*ctr*/, TDF_PON ), //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901908-1 3wind R+NR+R,TANKI rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901908-2 3wind R+NR+R,RNR rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901908-2-TAKE 3wind R+NR+R,RNR rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901909:                                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 3/*ctr*/, TDF_PON ), //R//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 2/*ctr*/, TDF_PON ), //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901909-1 3wind R+R,TANKI rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901909-2 3wind R+R,RNR rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901909-2-TAKE 3wind R+R,RNR rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//case 22901910:                                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 3/*ctr*/, TDF_PON ), //R//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 3/*number*/, 2/*ctr*/, TDF_PON ), //R//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901910-1 3wind R+R+NR,TANKI rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901910-2 3wind R+R+NR,RNR rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22901910-2-TAKE 3wind R+R+NR,RNR rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
       	}//for  ii=0-4                                             //~vagrI~
        if (ITUARonValue.MaxCase<22902001)     break;              //~vagrI~
        //*3kan earth                                              //~vagrI~
			case 22902001:                                         //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 4, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 4, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 0/*type*/, 1/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
						new Pair(PT_NUMSAME, 1/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 4/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902001   3kan menzen        rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
			case 22902002:                                         //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 4, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 4, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 0/*type*/, 1/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSAME, 1/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 4/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902002   3kan R, ron NR     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			case 22902003:                                         //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 4, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 4, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 4/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 0/*type*/, 1/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSAME, 1/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 4/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902003   3kan NR, ron tanki    rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			case 22902004:                                         //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 4, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 4, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 0/*type*/, 1/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 4/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 1/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 4/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902004   3kan RNR, ron tanki    rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			case 22902005:                                         //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 4, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 4, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 4/*ctr*/, TDF_CHII ),//~vagrI~
						new Pair(PT_NUMSAME, 0/*type*/, 1/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSAME, 1/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 4/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902005   3kan NRR, ron tanki    rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			case 22902006:                                         //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 4, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 4, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 0/*type*/, 1/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 4/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 1/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 4/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902006   3kan R,NR,R, ron tanki    rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			case 22902007:                                         //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 4, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 4, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 0/*type*/, 1/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSAME, 1/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 4/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902007   3kan R,R, ron tanki    rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			case 22902008:                                         //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 4, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 4, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 0/*type*/, 1/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSAME, 1/*type*/, 3/*number*/, 4/*ctr*/, TDF_KAN_RIVER),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 4/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 4/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902008   3kan R,R,NR ron tanki    rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
//                                                                 //~vagrR~
        if (ITUARonValue.MaxCase<22902101)     break;              //~vagrI~
//little dragon                                                    //~vagrR~
			case 22902101:  //memzen tanki                         //~vagrR~
			for (int ii=0;ii<5;ii++)                               //~vagrI~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902101 little dragon menzen tanki        rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
//			case 22902102:   //memzen RR                           //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902102 little dragon menzen RR        rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
//			case 22902103:   //memzen RNR                          //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3 , 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902103 little dragon menzen RNR,NR        rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
//			case 22902104:      //R+,tanki                         //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 3, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902104 little dragon R+, tanki       rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902104-2-TAKE little dragon R+, tanki       rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902105:      //R+,RR,                        //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 3, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902105 little dragon R+, RR       rc=" + rc);//~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902105-2-TAKE little dragon R+, RR       rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902106:      //R+,RNR,R                      //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 3, 3, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 3, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902106 little dragon R+, RNR, R big 3dragon       rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
			// case 22902107:      //R+,RNR,NR                     //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 3, 2, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902107 little dragon R+, RNR, NR     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902108:      //NR+,tanki                     //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3 , 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902108-1 little dragon NR+,tanki     rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902108-2-TAKE little dragon NR+,tanki     rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902108-2 little dragon NR+,NR SEQ     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902109:      //NR+,RR                        //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3 , 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902109 little dragon NR+,RR        rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902109-2-TAKE little dragon NR+,RR        rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902110:      //NR+,RNR,R                     //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 3, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902110 little dragon NR+,RNR, R 3dragon   rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
			// case 22902111:      //NR+,RNR,NR                    //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3 , 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902111 little dragon NR+,RNR, NR    rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902111-2-TAKE little dragon NR+,RNR, NR    rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902112:      //R+NR+,tanki                   //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 2, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902112 little dragon R+NR,tanki     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902112-2-TAKE little dragon R+NR,tanki     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902113:      //R+NR,RR                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 2, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902113 little dragon R+NR,RR     rc=" + rc);//~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902113-2-TAKE little dragon R+NR,RR     rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902114:      //R+NR,RNR,R                    //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902114 little dragon R+NR,RNR,R big dragon     rc=" + rc);//~vagrR~
			// case 22902115:      //R+NR,RNR,NR                   //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NOTNUM , 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrR~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902115 little dragon R+NR,RNR,NR     rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902115-2-TAKE little dragon R+NR,RNR,NR     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902116:      //R+NR+,tanki                   //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 2, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902116 little dragon NR+R,tanki     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902116-2-TAKE little dragon NR+R,tanki     rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902117:      //NR+R,RR                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 2, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902117 little dragon NR+R,RR     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902117-2-TAKE little dragon NR+R,RR     rc=" + rc);//~vagrR~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902118:      //NR+R,RNR,R                    //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902118 little dragon R+NR,RNR,R big dragon     rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902119:      //NR+R,RNR,NR                   //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902119 little dragon R+NR,RNR,NR     rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902119-2-TAKE little dragon R+NR,RNR,NR     rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902120:      //R+NR+R,tanki                  //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 3, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902120 little dragon R+NR+R,tanki     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902121:      //R+NR+R,RNR,R                  //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902121 little dragon R+NR+R,RNR,R big dragon      rc=" + rc);//~vagrI~
			// case 22902122:      //R+NR+R,RNR,NR                 //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3 , 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902122 little dragon R+NR+R,RNR,NR     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902122-2-TAKE little dragon R+NR+R,RNR,NR     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902123:      //R+R,tanki                     //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902123 little dragon R+R,tanki     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902124:      //R+R,RNR,R                     //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902124 little dragon R+R,PNR,R big dragon    rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902125:      //R+R,RNR,NR                    //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902125 little dragon R+R,PNR,NR              rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902125-2-TAKE little dragon R+R,PNR,NR              rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902126:      //R+R+NR,tanki                  //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902126 little dragon R+R,      tanki         rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902127:      //R+R+NR,RNR,R                  //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902127 little dragon R+R+NR,PNR,R big drgon             rc=" + rc);//~vagrI~
			// case 22902128:      //R+R+NR,RNR,NR                 //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902128 little dragon R+R+NR,PNR,NR                      rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902128-2-TAKE little dragon R+R+NR,PNR,NR                      rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			// case 22902129:      //R+R+NR,RNR,NR                 //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 2, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3,  0, 0, 0, 0, 0, 0, 0, 0},              //~vagrI~
						{0, 0, 0, 0, 2, 3, 3, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ , 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),    //NR//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 6/*number*/, 3/*ctr*/, TDF_PON),     //R//~vagrI~
						new Pair(PT_NUMSAME, 3/*type*/, 5/*number*/, 3/*ctr*/, TDF_PON),     //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902129-1 little dragon RNR+R+R,RNR,NR                     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902129-2 TAKE little dragon RNR+R+R,RNR,NR                     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902129-3 tanki little dragon RNR+R+R,                     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902129-3 tanki TAKE little dragon RNR+R+R,                     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
       	}//for  ii=0-4                                             //~vagrI~
                                                                   //~vagrI~
        if (ITUARonValue.MaxCase<22902201)     break;              //~vagrI~
//3anko fix chk                                                    //~vagrI~
			case 22902201:  //memzen tanki                         //~vagrI~
				rc=ronTest3Anko();                       //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
        if (ITUARonValue.MaxCase<22902301)     break;              //~vagrI~
//*other multitake option test                                     //~vagrI~
		case 22902301:  //tanyao, menzen                           //~vagrR~
			for (int ii=0;ii<5;ii++)                               //~vagrR~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 1, 1, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 1, 1, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                   //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902301-1  tanyao menzen ryanmen     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902301-2-TAKE  tanyao memzen ryanmen     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902301-3  tanyao menzen kanchan     rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
		//	case 22902302:  //tanyao, R+                           //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 1, 1, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 2/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902302-1  tanyao R+ ryanmen     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902302-2-TAKE  tanyao R+ ryanmen     rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902302-3  tanyao R+ kanchan     rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
		//	case 22902303:  //3tonko menzen                        //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902303-1  3tonko menzen shabo R+NR,R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902303-2-TAKE  3tonko memzen shabo R+NR  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902303-3  3tonko memzen TANKI NR  rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
             //menzen DupSeq                                       //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{4, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{4, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-1  3tonko menzen dupSeq DUP rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-2  3tonko menzen dupSeq R1 rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-3  3tonko menzen dupSeq R2 rc=" + rc);//~vagrI~
                                                                   //~vagrI~
             //menzen DupPillow                                    //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 0, 0, 0, 0, 0, 1, 3, 1},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 0, 0, 0, 0, 0, 1, 3, 1},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 6/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-4  3tonko menzen dupPillow L rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-5  3tonko menzen dupPillow Dup rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 8/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-6  3tonko menzen dupPillow R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
             //menzen DupSeq R+                                    //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 4, 1, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{0, 3, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 4, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,2/*type*/, 1/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-7  3tonko R+ dupSeq L rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-8  3tonko R+ dupSeq Dup rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-9  3tonko R+ dupSeq R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
		//	case 22902304:  //3tonko R+                            //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-1  3tonko R+     shabo R+NR,R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-2-TAKE  3tonko R+     shabo R+NR  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902304-3  3tonko R+     TANKI R+NR  rc=" + rc);//~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
			//	case 22902305:  //3tonko NR+                       //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII)//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902305-1  3tonko NR+    shabo R+NR,R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902305-2-TAKE  3tonko NR+    shabo R+NR  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902305-3  3tonko R+     TANKI R+NR  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//	case 22902306:  //3tonko NR+R+                         //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME,2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902306-1  3tonko NR+R   shabo R+NR,R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902306-2-TAKE  3tonko NR+R   shabo R+NR  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902306-3  3tonko NR+R   TANKI R+NR  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//	case 22902307:  //3tonko R+NR+                         //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII)//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902307-1  3tonko R+NR   shabo R+NR,R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902307-2-TAKE  3tonko R+NR   shabo R+NR  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902307-3  3tonko R+NR   TANKI R+NR  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//	case 22902308:  //mix chanta menzen                    //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902308-1  honchanta menzen ryanmen rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902308-2-TAKE  honchanta menzen ryanmen rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902308-3-TAKE  honchanta menzen kanchan  rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902308-4  junchanta menzen ryanmen rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902308-5-TAKE  junchanta menzen ryanmen rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902308-6-TAKE  junchanta menzen kanchan  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//	case 22902309:  //mix chanta R+                        //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,0/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902309-1  chanta R+ ryanmen rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902309-2-TAKE  chanta R+ ryanmen rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902309-3   chanta R+ kanchan  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 22902310:  //sameseq                                //~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 2, 2, 2},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 2, 2, 2},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 9/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902310-1  1peko ryanmen rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 9/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902310-2-TAKE  1peko ryanmen rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902310-3  1peko kanchan rc=" + rc);//~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902310-3  1peko penchan rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
		//case 22902311:  //hadaka tanki                           //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 4, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME,1/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME,0/*type*/, 7/*number*/, 4/*ctr*/, TDF_KAN_TAKEN),//~vagrI~
						new Pair(PT_NUMSEQ,2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902311-1  hadaka tanki with Ankan rc="+rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//+vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902311-2  hadaka tanki TAKE with Ankan rc="+rc);//+vagrI~
                                                                   //+vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 4, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSAME,1/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME,0/*type*/, 7/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ,2/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902311-3  hadaka tanki rc=" + rc);//+vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//+vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902311-4  hadaka tanki rc=" + rc);//+vagrI~
                                                                   //+vagrI~
				if (!swTestAll) break;                             //~vagrI~
       	}//for  ii=0-4                                             //~vagrR~
        if (ITUARonValue.MaxCase<22902401)     break;              //~vagrI~
//*chk Help sample                                                 //~vagrR~
		case 22902401:  //tanyao, menzen                           //~vagrI~
			for (int ii=0;ii<5;ii++)                               //~vagrI~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
			//1-1. 123M 345M 123P WW GG    W/G OK                  //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 2, 1, 1, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 2, 1, 1, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 3, 2, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-1-1  menzen R+R shabo rc=" + rc);//~vagrI~
			//1-2. 123M 345M 123P 99s WW   W NG                    //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 2, 1, 1, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 2, 1, 1, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-1-2  menzen R+NR shabo,R rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			//1-3. 123M 456M 78M  222S 33S     69M ng              //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-1-3 menzen straight ryanmen KTAAGARI rc=" + rc);//~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 1, 1, 2, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 1, 1, 2, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-1-3-2 menzen straight ryanmen 0ha rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-1-4 TAKE straight kataagari TAKE rc=" + rc);//~vagrR~
                                                                   //~vagrI~
			//1-5. 123M 456M 89M  222S 33S      7M ok              //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-1-5 straight penchan rc=" + rc);//~vagrR~
			//1-6 123M 234P WWW  GGG  R         R  ok              //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 1, 1, 1, 0, 3, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrR~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-1-6 menzen littleDragon tanki rc=" + rc);//~vagrI~
			//1-7. 123M 555M 333P 11S 22S        1S/2S tumo ok     //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 3, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 3, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 3, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 3, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-1-7 menzen 3anko 0han rc=" + rc);//~vagrR~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-1-7-2 TAKE menzen 3anko  TAKE rc=" + rc);//~vagrI~
			//2-1. 123M 123P 23S  444S 88S/WWW   14S OK            //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-2-1 Honor1 3shiki ryanmen kataagari   rc=" + rc);//~vagrR~
			//2-2. 123P      12S  88S /123M 456M    3s ok          //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-2-2 R+NR,3shiki penchan  rc=" + rc);//~vagrR~
			//2-3. 123P      23S  88S /123M 456M   14s kataagari NG, 1S tumo OK by option//~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-2-3 R+NR,3shiki ryanmen  KATAAGARI rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-2-3-2 R+NR,2shiki ryanmen KATAAGARI TAKE rc=" + rc);//~vagrR~
			//2-4. 555M 333P 11S  22S /123M        1S/2S tumo 3anko NG//~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 3, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 2, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 3, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 2, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-2-4 NR,3anko  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-2-4-2 NR,3anko TAKE rc=" + rc);//~vagrI~
			//3-1. 123M    123P 123S  8M   /888S    8m OK          //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 3, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,2/*type*/, 7/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-1 NR,3shiki all in hand  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
			//3-2. 123M    123P 12S  88M   /888S    3s NG by C     //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 3, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,2/*type*/, 7/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-2 NR,3shiki penchan 0han by NR rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-2 NR,3shiki penchan TAKE  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
                                                                   //~vagrI~
			//3-3. 123P 12S  88M   /888S,123M       3s NG by C, Nakaduke OK//~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 3, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,2/*type*/, 7/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-3 NR,3shiki ryanmen, KATAAGARI OK  rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-3 NR,3shiki ryanmen, KATAAGARI TAKE rc=" + rc);//~vagrI~
                                                                   //~vagrI~
			//3-4. 123P 23S  88M   /888S,123M       1s Take is optionally OK//~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 3, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,2/*type*/, 7/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-4 NR+R,3shiki ryanmen  kataagari     rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-4-2 NR+R,3shiki ryanmen  kataagari TAKE    rc=" + rc);//~vagrR~
                                                                   //~vagrI~
			//3-5. 123M    23P 88M   /888S,WWW      14p NG, Nakaduke OK//~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSAME,2/*type*/, 7/*number*/, 3/*ctr*/, TDF_PON),//~vagrI~
						new Pair(PT_NUMSAME,3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-5 NR+Yakuhai ,ryanmen       rc=" + rc);//~vagrI~
                                                                   //~vagrI~
			//3-6. WWW GGG RR 22S /123S             R/2s OK        //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,1/*type*/, 0/*number*/, 1/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-6 NR,little dragon shabo rc=" + rc);//~vagrR~
                                                                   //~vagrI~
			//3-7. WWW GGG R 234S /123S             R NG By C      //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrR~
				dupCtrAll = new int[][]{                           //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 3, 3, 2, 0, 0}};                         //7//~vagrR~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,0/*type*/, 0/*number*/, 1/*ctr*/, TDF_CHII),//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-7 NR,little3dragon tanki     rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902401-3-7 NR,little3Dragon Tanki,TAKE      rc=" + rc);//~vagrR~
                                                                   //~vagrI~
                                                                   //~vagrI~
                                                                   //~vagrI~
			}                                                      //~vagrI~
        if (ITUARonValue.MaxCase<22902501)     break;              //~vagrI~
//*3renpon                                                         //~vagrI~
		case 22902501:  //tanyao, menzen                           //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902501-1 all in hand also Pinfu  3renpon OK rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902501-2  3renpon also Pinfu shabo OK rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902501-2-2 TAKE 3renpon also Pinfu shabo OK rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{2, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902501-3 all in hand NOT Pinfu  3renpon OK rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902501-4  3renpon Not pinfu shabo OK rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902501-4-2TAKE  3renpon Not pinfu shabo OK rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902501-5 all in hand  3renpon OK Jihai rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrM~
			case 22902502:  //3renpon err                          //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 0, 3, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 0, 3, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902502-1  3renpon ERR rc=" + rc);//~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 1, 1, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 1, 1, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902502-2  3renpon ERR rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 3, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 3, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902502-3  3renpon ERR rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902502-3-2  3renpon ERR Pillow Tanki rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 2, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902502-4  3renpon ERR rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 3, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 3, 0, 0, 0, 1, 1, 1, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902502-5  3renpon ERR rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 2, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 3, 3, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 2, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ,3/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON)//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902502-6  3renpon ERR 3ren jihai rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
			case 22902503:  //3renpon menzen                       //~vagrR~
				rc=ronTest3renpon();                               //~vagrI~
        if (ITUARonValue.MaxCase<22902601)     break;              //~vagrI~
		case 22902601:  //tanyao, menzen                           //~vagrI~
		case 22902609:  //tanyao, menzen                           //~vagrI~
			rc=ronTestValue7(PtestCase);                           //~vagrI~
        break;                                                      //~vagrI~
    default:       //value3                                        //~vagrI~
		if (Dump.Y)	Dump.println("ronTestValue6 switch .ronTest-end of rotestValue7");//~vagrI~
		rc=null;                                                   //~vagrR~
    }                                                              //~vagrI~
        return rc;                                                 //~vagrI~
    }//func                                                        //~vagrI~
//**********************************************************************//~vagrI~
	private RonResult ronTest3renpon()                             //~vagrI~
	{                                                              //~vagrI~
	    RonResult rc=null;                                         //~vagrI~
        boolean swTestAll=true;                                    //~vagrI~
			for (int ii=0;ii<5;ii++)                               //~vagrM~
        	{                                                      //~vagrM~
			    setFixOption(ii);                                  //~vagrM~
                                                                   //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 3, 3, 3, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 3, 3, 3, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = null;                                  //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902503-1  3renpon menzen shabo R+NR,R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902503-2-TAKE  3renpon memzen shabo R+NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902503-3  3renpon memzen TANKI NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrM~
						{1, 1, 1, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = null;                                  //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902503-4 all in hand  3renpon OK or junchan ryanmen rc=" + rc);//~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902503-5 all in hand  3renpon OK or junchan penchan rc=" + rc);//~vagrM~
				if (!swTestAll) break;                             //~vagrM~
                                                                   //~vagrM~
		//	case 22902504:  //3renpon R+                           //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 3, 3, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 3, 3, 3},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSAME,2/*type*/, 8/*number*/, 3/*ctr*/, TDF_PON),//~vagrM~
				};                                                 //~vagrM~
                                                                   //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902504-1  3renpon R+     shabo R+NR,R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902504-2-TAKE  3renpon R+     shabo R+NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902504-3  3renpon R+     TANKI R+NR  rc=" + rc);//~vagrM~
				if (!swTestAll) break;                             //~vagrM~
                                                                   //~vagrM~
			//	case 22902505:  //3renpon NR+                      //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 3, 3, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 1, 1, 1, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 3, 3, 3, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 2, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII)//~vagrM~
				};                                                 //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902505-1  3renpon NR+    shabo R+NR,R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902505-2-TAKE  3renpon NR+    shabo R+NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902505-3  3renpon R+     TANKI R+NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
		//	case 22902506:  //3renpon NR+R+                        //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 3, 3, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 1, 1, 1, 0, 0, 0, 0, 2},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 3, 3, 3, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrM~
						new Pair(PT_NUMSAME,2/*type*/, 1/*number*/, 3/*ctr*/, TDF_PON),//~vagrM~
				};                                                 //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902506-1  3renpon NR+R   shabo R+NR,R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902506-2-TAKE  3renpon NR+R   shabo R+NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902506-3  3renpon NR+R   TANKI R+NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
		//	case 22902507:  //3renpon R+NR+                        //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSAME,2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrM~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII)//~vagrM~
				};                                                 //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902507-1  3renpon R+NR   shabo R+NR,R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902507-2-TAKE  3renpon R+NR   shabo R+NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth,false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902507-3  3renpon R+NR   TANKI R+NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
		//	case 22902508:  //3renpon R+R+NR+                      //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 3, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSAME,2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrM~
						new Pair(PT_NUMSAME,2/*type*/, 2/*number*/, 3/*ctr*/, TDF_PON),//~vagrM~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII)//~vagrM~
				};                                                 //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902508-1  3renpon R+NR   shabo R+NR,R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902508-2-TAKE  3renpon R+NR   shabo R+NR  rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				if (!swTestAll) break;                             //~vagrM~
		//	case 22902509:  //3renpon NR+R+R+R                     //~vagrM~
                                                                   //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 1, 1, 1, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSEQ,0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~vagrM~
						new Pair(PT_NUMSAME,2/*type*/, 2/*number*/, 3/*ctr*/, TDF_PON),//~vagrM~
						new Pair(PT_NUMSAME,2/*type*/, 1/*number*/, 3/*ctr*/, TDF_PON),//~vagrM~
						new Pair(PT_NUMSAME,2/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrM~
				};                                                 //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902509-1 NR,R,R,R tanki   rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 7/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902509-2-TAKE NR,R,R,R tanki   rc=" + rc);//~vagrM~
                                                                   //~vagrM~
		//	case 22902510:  //menzen DUPseq                        //~vagrM~
             //menzen DupSeq                                       //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{3, 3, 4, 1, 1, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{3, 3, 4, 1, 1, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = null;                                  //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-1  3renpon menzen dupSeq DUP rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-2  3renpon menzen dupSeq R1 rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 4/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-3  3renpon menzen dupSeq R2 rc=" + rc);//~vagrM~
                                                                   //~vagrM~
             //menzen DupSeq                                       //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 1, 4, 4, 3},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 1, 4, 4, 3},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = null;                                  //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 5/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-4  3renpon menzen dupSeq L rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 6/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-5  3renpon menzen dupSeq DUP1 rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-6  3renpon menzen dupSeq DUP2 rc=" + rc);//~vagrM~
                                                                   //~vagrM~
             //menzen DupPillow                                    //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{0, 0, 0, 0, 0, 0, 1, 3, 1},               //~vagrM~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{0, 0, 0, 0, 0, 0, 1, 3, 1},               //~vagrM~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = null;                                  //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 6/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-7  3renpon menzen dupPillow L rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 7/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-8  3renpon menzen dupPillow Dup rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 8/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-9  3renpon menzen dupPillow R rc=" + rc);//~vagrM~
                                                                   //~vagrM~
             // DupSeq R+                                          //~vagrM~
				dupCtr = new int[][]{                              //~vagrM~
						{1, 4, 4, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				dupCtrAll = new int[][]{                           //~vagrM~
						{4, 4, 4, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrM~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrM~
				pairEarth = new Pair[]{                            //~vagrM~
						new Pair(PT_NUMSAME,0/*type*/, 0/*number*/, 3/*ctr*/, TDF_PON),//~vagrM~
				};                                                 //~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-10 3renpon R+ dupSeq L rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-11 3renpon R+ dupSeq Dup rc=" + rc);//~vagrM~
                                                                   //~vagrM~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 01/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrM~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902510-12 3renpon R+ dupSeq R rc=" + rc);//~vagrM~
       	}//for  ii=0-4                                             //~vagrM~
        return rc;                                                 //~vagrI~
    }                                                              //~vagrI~
//**********************************************************************//~vagrI~
	private RonResult ronTest3Anko()                               //~vagrI~
	{                                                              //~vagrI~
	    RonResult rc=null;                                         //~vagrI~
        boolean swTestAll=true;                                    //~vagrI~
			for (int ii=0;ii<5;ii++)                               //~vagrI~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
                                                                   //~vagrI~
//			case 22902201:  //memzen 1earth  other                 //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902201-1 3anko menen menzen tanki        rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902201-2 3anko menen menzen seq        rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902201-3 3anko menen menzen TAKE      rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,true/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902201-4 3anko menen menzen TAKE      rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				if (!swTestAll) break;                             //~vagrI~
                                                                   //~vagrI~
//			case 22902202:  //memzen 1earth  other                 //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 1, 1, 1, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 3, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 3/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902202-1 3anko NR, tanki        rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, true/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902202-2 3anko NR  TAKE      rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902202-3 3anko NR RON      rc=" + rc);//~vagrI~
                                                                   //~vagrI~
                                                                   //~vagrI~
//			case 22902203:  //memzen 1earth  other                 //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 1, 1, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 1, 1, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-1 3anko menzen DupSeq L1  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-2 3anko menzen DupSeq L2  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-3 3anko menzen DupSeq DUP  rc=" + rc);//~vagrR~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 1, 4, 1, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 1, 4, 1, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-4 3anko menzen DupSeq L1  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-5 3anko menzen DupSeq DUP  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-6 3anko menzen DupSeq L2  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 4, 1, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 4, 1, 4, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 2, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-7 3anko menzen DupSeq DUPL rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-8 3anko menzen DupSeq Middle  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-9 3anko menzen DupSeq DUPR  rc=" + rc);//~vagrI~
//			case 22902204:  //memzen dup to pillow                 //~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 3, 0, 0},               //~vagrI~
						{0, 1, 1, 3, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 3, 0, 0},               //~vagrI~
						{0, 1, 1, 3, 0, 0, 0, 0, 0},               //~vagrI~
						{3, 0, 0, 0, 0, 3, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902204-1 3anko menzen DupPillow L1  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-2 3anko menzen DupPillow L2  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/,false/*saAllHand*/, pairEarth, false/*swTaken*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902203-3 3anko menzen DupPillow DUP  rc=" + rc);//~vagrI~
                                                                   //~vagrI~
                                                                   //~vagrI~
       	}//for  ii=0-4                                             //~vagrI~
        return rc;                                                 //~vagrI~
    }//func                                                        //~vagrI~
//**********************************************************************//~vagrI~
//**********************************************************************//~vagrI~
	private RonResult ronTestValue7(int PtestCase)                 //~vagrI~
	{                                                              //~vagrI~
	    RonResult rc=null;                                         //~vagrI~
        int testCase=PtestCase;                                    //~vagrI~
        boolean swTestAll=true;                                    //~vagrI~
    switch(testCase)                                               //~vagrI~
    {                                                              //~vagrI~
//**************                                                   //~vagrI~
//3DupSeq chkEarth                                                 //~vagrR~
    case 22902601:                                                 //~vagrI~
//                setFixOption(2);                                 //~vagrI~
//                dupCtr = new int[][]{                            //~vagrI~
//                        {3, 3, 3, 0, 0, 0, 0, 0, 0},             //~vagrI~
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0},             //~vagrI~
//                        {0, 0, 0, 0, 0, 0, 0, 2, 0},             //~vagrI~
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
//                dupCtrAll = new int[][]{                         //~vagrI~
//                        {4, 4, 4, 0, 0, 0, 0, 0, 0},             //~vagrI~
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0},             //~vagrI~
//                        {0, 0, 0, 0, 0, 0, 0, 2, 0},             //~vagrI~
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
//                pairEarth = new Pair[]{                          //~vagrI~
//                        new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
//                };                                               //~vagrI~
//                rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
//                if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22902612-3 3DupSeq R+  4DupSeq  penchan rc=" + rc.toString());//~vagrI~
			for (int ii=0;ii<5;ii++)                               //~vagrI~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902601-1 3dupSeq menzen last NR tanki  rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902601-2 3dupSeq menzen last ryanmen  rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, true /*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902601-2-TAKE 3dupSeq menzen last ryanmen TAKE rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902601-3 3dupSeq menzen last penchan FIX rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902601-4 3dupSeq menzen TAKE  last penchan FIX rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902602:       //R+                                    //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrR~
						{2, 2, 2, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrR~
						{3, 3, 3, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902602-1 3dupSeq R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902602-2 3dupSeq R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902602-2-TAKE 3dupSeq R, last Not FIX ryanmen TAKE rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902602-3 3dupSeq R, last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902602-4 3dupSeq R,TAKE last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902603:          //NR+                                //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{3, 3, 3, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrR~
						{3, 3, 3, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902603-1 3dupSeq NR, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902603-2 3dupSeq NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902603-2-TAKE 3dupSeq NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902603-3 3dupSeq NR, last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902603-4 3dupSeq NR, TAKE last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902604:            //R+NR                             //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{2, 2, 2, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrR~
						{3, 3, 3, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrR~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902604-1 3dupSeq R+NR, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902604-2 3dupSeq R+NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902604-2-TAKE 3dupSeq R+NR, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902604-3 3dupSeq R+NR, last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902604-4 3dupSeq R+NR, TAKE last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902605:     //NR+R+                                   //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{2, 2, 2, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrR~
						{3, 3, 3, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII),//~vagrI~
						new Pair(PT_NUMSEQ, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902605-1 3dupSeq NR+R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902605-2 3dupSeq NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902605-2-TAKE 3dupSeq NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902605-3 3dupSeq NR+R, last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902605-4 3dupSeq NR+R, TAKE last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902606:        //R+NR+R                               //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrR~
						{3, 3, 3, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrI~
						new Pair(PT_NUMSEQ, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902606-1 3dupSeq R+NR+R, last FIX Tanki rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902606-2 3dupSeq R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902606-2-TAKE 3dupSeq R+NR+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902606-3 3dupSeq R+NR+R, last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902606-4 3dupSeq R+NR+R, TAKE last FIX penchan rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902607:  //RR                                         //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrR~
						{3, 3, 3, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
						new Pair(PT_NUMSEQ, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902607-1 3dupSeq R+R, last FIX Tanki rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902607-2 3dupSeq R+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902607-2-TAKE 3dupSeq R+R, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902607-3 3dupSeq R+R, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902607-4 3dupSeq R+R, TAKE last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902608:  //R+R+NR                                     //~vagrR~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{1, 1, 1, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 1, 1, 1, 0, 0},               //~vagrR~
						{3, 3, 3, 0, 0, 0, 0, 0, 2},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
						new Pair(PT_NUMSEQ, 2/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
						new Pair(PT_NUMSEQ, 1/*type*/, 4/*number*/, 3/*ctr*/, TDF_CHII), //NR//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902608-1 3dupSeq RRNR, last FIX Tanki rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902608-2 3dupSeq RRNR, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902608-2-TAKE 3dupSeq RRNR, last Not FIX ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902608-3 3dupSeq RRNR, last FIX penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, true/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902608-4 3dupSeq RRNR, TAKE last FIX penchan rc=" + rc.toString());//~vagrR~
            }//for                                                 //~vagrR~
			break;                                                 //~vagrR~
      case 22902609:                                               //~vagrR~
                                                                   //~vagrI~
			for (int ii=0;ii<5;ii++)                               //~vagrI~
        	{                                                      //~vagrI~
			    setFixOption(ii);                                  //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 4, 4, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 4, 4, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-1 3dupSeq with Near ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-2 3dupSeq kanchan and ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-3 3dupSeq penchan and kanchan of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-4 3dupSeq ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-5 3dupSeq width near TANKI      rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 3, 4, 4, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 3, 4, 4, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-6 3dupSeq with Near ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-7 3dupSeq kanchan and ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-8 3dupSeq penchan and kanchan of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 4/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-9 3dupSeq ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-10 3dupSeq width near TANKI      rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 1, 4, 4, 3, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 1, 4, 4, 3, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = null;                                  //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-11 3dupSeq with Near ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-12 3dupSeq kanchan and ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-13 3dupSeq penchan and kanchan of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 4/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-14 3dupSeq ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, true/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902609-15 3dupSeq width near TANKI      rc=" + rc.toString());//~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902610:    //R+                                       //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{2, 3, 3, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 4, 4, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-1 3DupSeq R+  with Near ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-2 3DupSeq R+  kanchan and ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-3 3DupSeq R+  penchan and kanchan of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-4 3DupSeq R+  ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-5 3DupSeq R+  width near TANKI      rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 2, 3, 3, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 3, 4, 4, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-6 3DupSeq R+  with Near ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-7 3DupSeq R+  kanchan and ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-8 3DupSeq R+  penchan and kanchan of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 4/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-9 3DupSeq R+  ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-10 3DupSeq R+  width near TANKI      rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 2, 3, 3, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 3, 4, 4, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-11 3DupSeq R+  with Near ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-12 3DupSeq R+  kanchan and ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-13 3DupSeq R+  penchan and kanchan of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 4/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-14 3DupSeq R+  ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902610-15 3DupSeq R+  width near TANKI      rc=" + rc.toString());//~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902611:    //NR+                                      //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{2, 3, 3, 1, 0, 0, 0, 0, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{3, 4, 4, 1, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrR~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-1 3DupSeq NR+  with Near ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-2 3DupSeq NR+  kanchan and ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-3 3DupSeq NR+  penchan and kanchan of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-4 3DupSeq NR+  ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-5 3DupSeq NR+  width near TANKI      rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 2, 3, 3, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 3, 4, 4, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-6 3DupSeq NR+  with Near ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-7 3DupSeq NR+  kanchan and ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-8 3DupSeq NR+  penchan and kanchan of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 4/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-9 3DupSeq NR+  ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-10 3DupSeq NR+  width near TANKI      rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{0, 2, 3, 3, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{0, 3, 4, 4, 1, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-11 3DupSeq NR+  with Near ryanmen rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-12 3DupSeq NR+  kanchan and ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-13 3DupSeq NR+  penchan and kanchan of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 4/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-14 3DupSeq NR+  ryanmen of near rc=" + rc.toString());//~vagrI~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 8/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902611-15 3DupSeq NR+  width near TANKI      rc=" + rc.toString());//~vagrI~
		if (!swTestAll) break;                                     //~vagrI~
//    case 22902612:    //NR+                                      //~vagrI~
				dupCtr = new int[][]{                              //~vagrI~
						{3, 3, 3, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				dupCtrAll = new int[][]{                           //~vagrI~
						{4, 4, 4, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~vagrI~
						{0, 0, 0, 0, 0, 0, 0, 2, 0},               //~vagrR~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~vagrI~
				pairEarth = new Pair[]{                            //~vagrI~
						new Pair(PT_NUMSEQ, 0/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII), //R//~vagrI~
				};                                                 //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902612-1 3DupSeq R+  4DupSeq  ryanmen rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 1/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902612-2 3DupSeq R+  4DupSeq  kanchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 2/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrI~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902612-3 3DupSeq R+  4DupSeq  penchan rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
				rc = ronTestSub(dupCtr, dupCtrAll, 2/*ronType*/, 7/*ronNumber*/, 0/*ctrAnkan*/, false/*swAllHand*/, pairEarth, false/*saTake*/);//~vagrR~
				if (Dump.Y)	Dump.println("ITUARonValueSub.ronTest-22902612-4 3DupSeq R+  4DupSeq  TANKI rc=" + rc.toString());//~vagrR~
                                                                   //~vagrI~
       	}//for  ii=0-4                                             //~vagrI~
        break;                                                     //~vagrI~
    default:       //value3                                        //~vagrI~
		if (Dump.Y)	Dump.println("ronTestValue7 switch .ronTest-end of rotestValue7");//~vagrI~
//      UView.showToastLong("testCaseValue7 ERR="+testCase);       //~vagrR~
        rc=null;                                                   //~vagrI~
    }                                                              //~vagrI~
        return rc;                                                 //~vagrI~
    }//func                                                        //~vagrI~
                                                                   //~vagrM~
}//class                                                           //~v@@@R~
