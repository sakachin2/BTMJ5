//*CID://+van1R~: update#= 582;                                    //~van1R~
//**********************************************************************//~v@@@I~
//2022/07/04 van1 hungle suuprt for Help                           //~van1I~
//2021/12/24 vaih TestOption Dump.Y=false for debugmode performance//~vaihI~
//2021/11/15 vagv move TilesTest to debug dir                      //~vagvI~
//2021/10/21 vaf1 Dump, initially terminal and follow test option to investigate vaf0//~vaf1I~
//2021/08/18 vaca test SmallFont dialog                            //~vacaI~
//2021/06/28 vaa6 Test option; set client position                 //~vaa6I~
//2021/05/01 va8x (Test)specify robot discard tile                 //~va8xI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/02/01 va65 testoption of open hand for discardSmart test    //~va65I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//**********************************************************************//~va60I~
//TestOption                                                       //~v@@@I~
//***********************                                          //~v@@@I~
//TestChankan                                                      //~v@@@I~
//  set CHANKAN_DEAL reset Kan with Any tile                       //~v@@@R~
//                   set long of robot autotake time               //~v@@@I~
//  set Final game to N2N(when 2 player mach, another player :east)//~v@@@I~
//     amother player Pon 1man and later take 1man and do chankan  //~v@@@I~
//     you declare ron                                             //~v@@@I~
//**********************************************************************//~v@@@I~
package com.btmtest;                                               //~v@@@I~
import com.btmtest.BT.BTControl;
import com.btmtest.dialog.SuspendIOErrReqDlg;
import com.btmtest.dialog.TODlg;                                             //~v@@@I~
import com.btmtest.game.TilesTest;                                 //~vagvI~
import static com.btmtest.StaticVars.AG;
import static com.btmtest.AG.*;//~v@@@R~

import com.btmtest.game.Status;
import com.btmtest.utils.Dump;                                                                   //~v@@@I~
import com.btmtest.wifi.WDA;

//~v@@@I~
public class TestOption //extends Handler                          //~v@@@I~
{                                                                  //~v@@@I~
    public static TilesTest aTilesTest;                            //~vagvI~
    public static boolean swActivityDestroyed;                      //~vagvI~
    public static final int TO_HIDE_BUTTON  =0x01;                 //~v@@@R~
    public static final int TO_CONNECTED    =0x02;                 //~v@@@R~
    public static final int TO_ENDGAME      =0x04;                 //~v@@@R~
//  public static final int TO_DUMMYACCOUNT =0x08;                 //~v@@@R~
    public static final int TO_TEST_ORI     =0x10;                 //~v@@@I~
    public static final int TO_TEST_SETUP   =0x20;                 //~v@@@I~
    public static final int TO_PON_TEST     =0x40;                 //~v@@@I~
    public static final int TO_CHII_TEST    =0x80;                 //~v@@@I~
    public static final int TO_KAN_TEST     =0x0100;               //~v@@@I~
//  public static final int TO_POSITIONING  =0x0200;	//bypass positioning touch//~v@@@R~
    public static final int TO_KAN_ADD      =0x0400;	//kan after taken is kanadd//~v@@@R~
    public static final int TO_KAN_ADDDEAL  =0x0800;	//for test add kan ,deal without sort//~v@@@I~
    public static final int TO_KAN_CHANKAN  =0x1000;	//kan after taken is kanadd//~v@@@I~
    public static final int TO_COMPDLG_LAYOUT= 0x2000;	//completedlg layout test//~v@@@I~
    public static final int TO_COMPREQDLG_LAYOUT= 0x4000;	//compreqdlg layout test//~v@@@I~
    public static final int TO_COMPREQDLG_LAYOUT_SHOW= 0x8000;	//compreqdlg layout test//~v@@@I~
    public static final int TO_TAKEDISCARD     =     0x010000;	//tsumogiri//~v@@@R~
    public static final int TO_TAKEDISCARD_STOP=     0x020000;	//tsumogiri//~v@@@R~
    public static final int TO_DRAWNREQDLG_LAYOUT=   0x040000;	//drawndlg//~v@@@I~
    public static final int TO_DRAWNDLG_LAYOUT=      0x080000;	//drawndlg//~v@@@I~
    public static final int TO_DRAWNREQDLGHW_LAYOUT=    0x100000;	//drawndlg//~v@@@I~
    public static final int TO_SKIPDICE    =             0x200000;	////~v@@@R~
    public static final int TO_CLOSEONCONNECT=           0x400000;	//close dialog at successfully connected//~v@@@I~
    public static final int TO_DRAWNREQDLG_LAST=          0x800000;	//drawndlg last//~v@@@I~
    public static final int TO_DRAWNLAST_LAYOUT=        0x01000000;	//drawndlg last//~v@@@I~
    public static final int TO_SCORE_LAYOUT    =        0x02000000;	//drawndlg last//~v@@@I~
    public static final int TO_RULE_NOSYNC     =        0x04000000;	//drawndlg last//~v@@@I~
    public static final int TO_COMPDLG_TEST2   =        0x08000000;	//drawndlg last//~v@@@I~
    public static final int TO_INITIALSCORE    =        0x10000000;	//drawndlg last//~v@@@I~
    public static final int TO_DRAWNDLGHW_LAYOUT=       0x20000000;	//drawndlg//~v@@@R~
    public static final int TO_BIRD_WITH_ROBOT=         0x40000000;	//drawndlg//~v@@@I~
    public static int option;                                      //~v@@@I~
    public static final int TO2_FINAL_GAME=        0x00000001;	//drawndlg//~v@@@I~
    public static final int TO2_LAYOUT_FINALGAME=  0x00000002;	//drawndlg//~v@@@I~
    public static final int TO2_DRAWNLAST_SHOW_STICK=   0x00000004;	//drawndlg//~v@@@I~
    public static final int TO2_UNIT_MILISEC=            0x00000008;	//unit 10ms if over 10//~v@@@R~
    public static final int TO2_SUSPEND=                 0x00000010;//~v@@@R~
    public static final int TO2_WAITSELECT_PON=          0x00000020;//~v@@@R~
    public static final int TO2_WAITSELECT_CHII=         0x00000040;//~v@@@R~
    public static final int TO2_RON_TEST=                0x00000080;//~v@@@I~
    public static final int TO2_SHOWF2 =                 0x00000100;//~v@@@R~
    public static final int TO2_ANKAN_DEAL              =0x00000200;//for test ankan ,deal without sort//~v@@@R~
    public static final int TO2_CHANKAN_DEAL            =0x00000400;//~v@@@I~
    public static final int TO2_RONVALUE_TEST=           0x00000800;//~v@@@I~
    public static final int TO2_RONVALUE_TESTSUB=        0x00001000;//~v@@@I~
    public static final int TO2_RONVALUE_NODORA=         0x00002000;//~v@@@R~
    public static final int TO2_DUMP_SDCARD    =         0x00004000;//~v@@@I~
    public static final int TO2_SETDORA        =         0x00008000;//~v@@@I~
    public static final int TO2_CHKRANK        =         0x00010000;//~v@@@I~
    public static final int TO2_IT             =         0x00020000;    //Instrument Test//~va60R~
    public static final int TO2_OPENHAND       =         0x00040000;//~va65I~
    public static final int TO2_ROBOT_DISCARD_BUTTON=    0x00080000;//~va66I~
    public static final int TO2_ROBOT_TOAST    =         0x00100000;//~va66I~
    public static final int TO2_ROBOT_SKIP_REACH =       0x00200000;//~va66I~
    public static final int TO2_DEAL_MULTIRON    =       0x00400000;//~va66I~
    public static final int TO2_DEAL_SINGLERON   =       0x00800000;//~va66I~
    public static final int TO2_DEAL_PON         =       0x01000000;//~va66I~
    public static final int TO2_DEAL_CHII        =       0x02000000;//~va66I~
    public static final int TO2_CALL1ST          =       0x04000000;//~va66I~
    public static final int TO2_DEAL_PONCHII     =       0x08000000;//~va66I~
    public static final int TO2_DEAL_DOUBLERON   =       0x10000000;//~va66I~
    public static final int TO2_DEAL_KANAFTERREACH=      0x20000000;//~va66I~
    public static final int TO2_DEAL_RINSHANRON   =      0x40000000;//~va66I~
    public static final int TO2_INITIAL_POSITION  =      0x80000000;    //initialposition//~va66I~
    public static final int TO3_DEAL_RONTAKEN     =      0x00000001;    //tenho/chiiho//~va66R~
    public static final int TO3_DEAL_13NOPAIR     =      0x00000002;    //13nopair at 1st take//~va66I~
    public static final int TO3_DEAL_14NOPAIR     =      0x00000004;    //14nopair at 1st take//~va66I~
    public static final int TO3_DEAL_RONTAKEN1ST  =      0x00000008;    //tenho/chiiho//~va66I~
    public static final int TO3_SET_DUPCTR        =      0x00000010;    //tenho/chiiho//~va66I~
    public static final int TO3_NOREACH           =      0x00000020;    //robot bypass reach//~va66I~
    public static final int TO3_RON_RIVER         =      0x00000040;    //robot bypass reach//~va66I~
    public static final int TO3_INTENT_3DRAGON    =      0x00000080;//~va66I~
    public static final int TO3_DEAL_MULTIWAITRON =      0x00000100;//~va66I~
    public static final int TO3_DEAL_MULTIWAITRONOK=     0x00000200;//~va66I~
    public static final int TO3_DEAL_DOUBLERUN_FIX =     0x00000400;//~va66R~
    public static final int TO3_DEAL_DOUBLERUN_NOTFIX=   0x00000800;//~va66I~
    public static final int TO3_DEAL_DOUBLERUN_NOTFIXNG= 0x00001000;//~va66I~
    public static final int TO3_DEAL_DOUBLERUN_CHII    = 0x00002000;//~va66I~
    public static final int TO3_DEAL_MULTIWAITWGR      = 0x00004000;//~va66I~
    public static final int TO3_DEAL_3SHIKI_CHII       = 0x00008000;//~va66I~
    public static final int TO3_DEAL_3SHIKI_CHIING     = 0x00010000;//~va66I~
    public static final int TO3_DEAL_RONTAKEN_DOUBLEREACH=0x00020000;//~va66I~
    public static final int TO3_ROBOT_DO_REACH =          0x00040000;//~va66I~
    public static final int TO3_DEAL_3SHIKI_CHII_TAKE  =  0x00080000;//~va66R~
    public static final int TO3_DEAL_SAMECOLOR         =  0x00100000;//~va66I~
    public static final int TO3_DEAL_ATODUKE_TAKE_SAMECOLOR =  0x00200000;//~va66I~
    public static final int TO3_DEAL_ATODUKE_TAKE_YAKUHAI   =  0x00400000;//~va66I~
    public static final int TO3_ROBOT_DISCARD_TILE          =  0x00800000;//~va8xI~
    public static final int TO3_DEAL_ANKAN_FIX1             =  0x01000000;//~va8xI~
    public static final int TO3_DEAL_SAKIDUKE_2HAN_TAKE     =  0x02000000;//~va8xI~
    public static final int TO3_IT_RARON                    =  0x04000000;//~va8xI~
    public static final int TO3_INITIAL_POSITION_CLIENT     =  0x08000000;    //initialposition//~vaa6I~
    public static final int TO3_DEAL_NOTIFY_ALL             =  0x10000000;    //initialposition//~vaa6I~
    public static final int TO3_DEAL_MINKAN_RON             =  0x20000000;    //initialposition//~vaa6I~
    public static final int TO3_DEAL_DOUBLEREACH_CHII       =  0x40000000;//~vaa6R~
    public static final int TO4_DEAL_DOUBLEREACH_PON        =  0x00000001;//~vaa6I~
    public static final int TO4_DEAL_DOUBLEREACH_PON_HONOR  =  0x00000002;//~vaa6I~
    public static final int TO4_DEAL_MINKAN_HONOR           =  0x00000004;//~vaa6I~
    public static final int TO4_DEAL_MINKAN_HONOR_SAMECOLOR =  0x00000008;//~vaa6I~
    public static final int TO4_DEAL_PON_ALLSAME            =  0x00000010;//~vaa6I~
    public static final int TO4_DEAL_KANCHAN_REACH          =  0x00000020;//~vaa6I~
    public static final int TO4_DEAL_PENCHAN_REACH          =  0x00000040;//~vaa6I~
    public static final int TO4_DEAL_TANKI_REACH            =  0x00000080;//~vaa6I~
    public static final int TO4_REACH_EARLY                 =  0x00000100;//~vaa6I~
    public static final int TO4_DEAL_TANKI_REACH_TANYAO     =  0x00000200;//~vaa6I~
    public static final int TO4_DEAL_13ORPHAN               =  0x00000400;//~vaa6I~
    public static final int TO4_DEAL_7PAIR_TANYAO           =  0x00000800;//~vaa6I~
    public static final int TO4_DEAL_7PAIR_CHANTA           =  0x00001000;//~vaa6I~
    public static final int TO4_DEAL_7PAIR_NOT_TANYAO       =  0x00002000;//~vaa6I~
    public static final int TO4_DEAL_7PAIR_SAMECOLOR        =  0x00004000;//~vaa6I~
    public static final int TO4_DEAL_PON_WAIT_TANKI         =  0x00008000;//~vaa6I~
    public static final int TO4_DEAL_PONPON                 =  0x00010000;//~vaa6I~
    public static final int TO4_DEAL_PONCHII_SELECT_DORA    =  0x00020000;//~vaa6I~
    public static final int TO4_INITIAL_POSITION_CLIENT_FACING =  0x10000000;    //initialposition//~vaa6I~
    public static final int TO4_DEAL_CHANKAN                   =  0x20000000;    //initialposition//~vaa6I~
    public static final int TO4_DEAL_CHANKAN_13ORPHAN          =  0x40000000;    //initialposition//~vaa6I~
    public static final int TO5_DEAL_INTENT_ALLSAME            =  0x00000001;    //initialposition//~vaa6I~
    public static final int TO5_DEAL_INTENT_TANYAO             =  0x00000002;//~vaa6I~
    public static final int TO5_DEAL_INTENT_CHANTA             =  0x00000004;//~vaa6I~
    public static final int TO5_DEAL_SHANPON_REACH             =  0x00000008;//~vaa6R~
    public static final int TO5_NOTHINK_ROBOT                  =  0x00000010;//~vaa6R~//~vaf1R~
    public static final int TO5_KUIKAE_CHK                     =  0x00000020;//~vaf1I~
    public static final int TO5_DEAL_STRAIGHT                  =  0x00000040;//~vaf1I~
    public static final int TO5_DEAL_STRAIGHT_CHII             =  0x00000080;//~vaf1I~
    public static final int TO5_DEAL_3SAMESEQ                  =  0x00000100;//~vaf1I~
    public static final int TO5_DEAL_TANYAO                    =  0x00000200;//~vaf1I~
    public static final int TO5_DEAL_TANYAO_PON                =  0x00000400;//~vaf1I~
    public static final int TO5_DEAL_CHANTA                    =  0x00000800;//~vaf1I~
    public static final int TO5_DEAL_CHANTA_PON                =  0x00001000;//~vaf1I~
    public static final int TO5_INITIAL_POSITION_CLIENT_PREV   =  0x00002000;//~vaf1I~
    public static final int TO5_DEAL_OTHER_YAKUMAN             =  0x00004000;//~vagvI~
    public static final int TO5_NODUMP                         =  0x00008000;//~vaihI~
    public static final int TO5_AVOID_FURITEN                  =  0x00010000;//~vaihI~
    public static final int TO5_SETDISCARD                     =  0x00020000;//~vaihI~
    public static final int TO5_LANG_KO                        =  0x00040000;//~van1I~
    public static final int TO5_NAGASHIMANGAN                  =  0x00080000;//~van1I~
    public static final int TO5_OPENREACHRON                   =  0x00100000;//~van1I~
    public static final int TO5_DRAWNREQDLG_LASTDIALOG         =  0x00200000;   //Drawn menu is DrawnReqDlgLast//~van1I~
    public static final int TO5_RONCANCEL_TEST                 =  0x00400000;   //Drawn menu is DrawnReqDlgLast//~van1I~
    public static int option2;                                     //~v@@@I~
    public static int option3;                                     //~va66I~
    public static int option4;                                     //~vaa6I~
    public static int option5;                                     //~vaa6I~
    public static int firstDealer;                                 //~v@@@I~
    public static int finalGameCtrSet,finalGameCtrGame;            //~v@@@I~
    public static int ioerr;                                       //~v@@@I~
    public static int testCaseRonValue;                            //~v@@@I~
    public static int testDoraUpType,testDoraUpNumber;             //~v@@@I~
    public static int testDoraDownType,testDoraDownNumber;         //~v@@@I~
    public static int testDiscardType,testDiscardNumber;           //~vaihI~
    public static int testKanUpType,testKanUpNumber;               //~v@@@I~
    public static int testKanDownType,testKanDownNumber;           //~v@@@I~
    public static int testKanUpType2,testKanUpNumber2;             //~v@@@I~
    public static int testKanDownType2,testKanDownNumber2;         //~v@@@I~
    public static int testContinueStick,testContinueWin;           //~van1I~
    public static boolean swDisableBT;                             //~v@@@I~
    public static int timingDisableBT;                             //~v@@@I~
    public static final int BTIOE_AFTER_TAKE=0;                    //~v@@@I~
    public static final int BTIOE_AFTER_DISCARD=1;                 //~v@@@I~
    public static final int BTIOE_AFTER_PON=2;                     //~v@@@I~
    public static final int BTIOE_AFTER_KAN=3;                     //~v@@@I~
    public static final int BTIOE_AFTER_RON=4;                     //~v@@@I~
    public static final int BTIOE_AFTER_OPEN=5;                    //~v@@@I~
    public static final int BTIOE_AFTER_ROBOTTAKE=6;               //~v@@@I~
                                                                   //~v@@@I~
    private static String sst="abc";                               //~v@@@I~
    private static String sst2;                                    //~v@@@I~
    private static String sst3;                                    //~v@@@I~
//*********************************************                    //~v@@@I~
	static                                                         //~v@@@I~
    {                                                              //~v@@@I~
//      option|=TO_HIDE_BUTTON; //to get top display panel         //~v@@@I~
//      option|=TO_CONNECTED;   //to enter game widthout connected //~v@@@R~
        option|=TO_ENDGAME;     //back to top panel                //~v@@@I~
//      option|=TO_DUMMYACCOUNT;     //allow dummy account         //~v@@@R~
//        option|=TO_TEST_ORI;         //orientation test          //~v@@@R~
        option|=TO_PON_TEST;          //pon with any tile          //~v@@@R~
        option|=TO_CHII_TEST;          //pon with any tile         //~v@@@R~
        option|=TO_KAN_ADDDEAL;       //pon with any tile          //~v@@@I~
//      option|=TO_KAN_CHANKAN;       //chankan test,set off when kan test//~v@@@R~
//      option|=TO_KAN_TEST;          //kan with any tile,set off when chankan test//~v@@@R~
//  	option|=TO_POSITIONING;//  =0x0200;	//bypass positioning touch//~v@@@R~
//    	option|=TO_COMPDLG_LAYOUT;//  =0x0200;	//set also COMPDLGREQ_LAYOUT//~v@@@R~
//  	option|=TO_COMPREQDLG_LAYOUT;//  =0x0200;                  //~v@@@R~
//  	option|=TO_COMPREQDLG_LAYOUT_SHOW;//  =0x0200;             //~v@@@R~
      	option|=TO_DRAWNREQDLG_LAYOUT;//  =0x0200;                 //~v@@@R~
//    	option|=TO_TAKEDISCARD;//        = 0x8000;	//tsumogiri    //~v@@@R~//~va60R~
//    	option|=TO_DRAWNREQDLGHW_LAYOUT;                           //~v@@@R~
      	option|=TO_DRAWNDLGHW_LAYOUT;                              //~v@@@I~
    	option|=TO_SKIPDICE            ;                           //~v@@@R~
    	option|=TO_CLOSEONCONNECT      ;                           //~v@@@I~
       	option|=TO_DRAWNREQDLG_LAST;        //  0x800000;	 show drawnreqlast at 2nd take//~v@@@R~
//  	option|=TO_DRAWNLAST_LAYOUT;                               //~v@@@R~
//  	option|=TO_SCORE_LAYOUT;                                   //~v@@@R~
//  	option|=TO_RULE_NOSYNC;                                    //~v@@@R~
    	option|=TO_COMPDLG_TEST2;                                  //~v@@@I~
    	option|=TO_INITIALSCORE;                                   //~v@@@I~
    	option|=TO_BIRD_WITH_ROBOT;                                //~v@@@I~
                                                                   //~v@@@I~
    	option2|=TO2_UNIT_MILISEC;                                 //~v@@@I~
    	option2|=TO2_ROBOT_TOAST;                                  //~va66I~
//  	option2|=TO2_ROBOT_SKIP_REACH;	//TODO test                //~va66R~
//  	option5|=TO5_RONCANCEL_TEST  ;                             //+van1R~
        Dump.println("TestOption.static sst="+sst);                      //~v@@@I~
        sst2="sst2";                                               //~v@@@I~
    }                                                              //~v@@@I~
    public TestOption()                                            //~v@@@I~
    {                                                              //~v@@@I~
        if (!AG.isDebuggable)                                      //~v@@@I~
        {                                                          //~v@@@I~
        	option=0; option2=0;                                   //~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
//    	AG a=AG;                                                   //~v@@@I~
        sst3="sst3";                                               //~v@@@I~
        Dump.println("TestOption.constructor sst="+sst+",sst2="+sst2+",sst3="+sst3);//~v@@@I~
        sst2="sst2-2";                                             //~v@@@I~
        Dump.println("TestOption appname="+AG.appName+",yourname="+AG.YourName);//~v@@@R~
        TODlg.prop2TO();                                           //~v@@@I~
      if ((option5 & TO5_NODUMP)!=0)                               //~vaihI~
        Dump.setOption(false);	//set Y=false                      //~vaihI~
      else                                                         //~vaihI~
      if ((option2 & TO2_DUMP_SDCARD)!=0)      //else continue to terminal//~vaf1I~
        Dump.open("Dump.txt",false/*sdcard*/);                 //~vad5I~//~vacaI~
    }                                                              //~v@@@I~
    public static int getTimingBTIOErr()                           //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=-1;                                                 //~v@@@I~
        if (swDisableBT)                                           //~v@@@I~
        	rc=timingDisableBT;                                    //~v@@@I~
        if (Dump.Y) Dump.println("TestOption.getTimingBTIOErr swDisableBT="+swDisableBT+",rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public static void disableBT()                                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TestOption.disableBT");          //~v@@@I~
        if (AG.activeSessionType==AST_WD)                          //~v@@@I~
        {                                                          //~v@@@I~
   			throwIOEWD();                                            //~v@@@R~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        Status.setIOExceptionInGaming(true);	//writable at BTIOThread//~v@@@I~
        AG.aBTI.mBTC.startBTActivity(BTControl.BTA_DISABLE);       //~v@@@I~
    }                                                              //~v@@@I~
    public static void throwIOEWD()                                //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TestOption.throwIOEWD");         //~v@@@R~
//      Status.setIOExceptionInGaming(true);                       //~v@@@R~
        WDA.disableWiFi();                                         //~v@@@I~
//      WDA.enableWiFi();                                          //~v@@@R~
    }                                                              //~v@@@I~
    public static void throwIOEBT()                                //~vacaI~
    {                                                              //~vacaI~
        if (Dump.Y) Dump.println("TestOption.throwIOEBT");         //~vacaI~
        int idx=0;                                                 //~vacaI~
        AG.aBTMulti.BTGroup.setIOErr(idx,true/*swOn*/);            //~vacaI~
        SuspendIOErrReqDlg.newInstance(idx);                       //~vacaI~
    }                                                              //~vacaI~
}                                                                  //~v@@@I~
