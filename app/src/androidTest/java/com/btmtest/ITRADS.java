//*CID://+dateR~:                             update#=  111;       //~1108I~
//******************************************************************//~0B03I~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//******************************************************************//~0B03I~
//RADSmart test                                                    //~1121I~
//******************************************************************//~1108I~
package com.btmtest;
//******************************************************************//~0B01R~
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.res.Resources;

import androidx.test.platform.app.InstrumentationRegistry;               //~0A31R~
import androidx.test.ext.junit.runners.AndroidJUnit4;                  //~0A31I~
                                                                   //~0B03I~
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//***********************************                              //~0A31R~
                                                                   //~0A31I~
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.RA.RoundStat;
import com.btmtest.game.Robot;
import com.btmtest.game.Rule;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.game.UA.UARon;
import com.btmtest.game.UADelayed;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.GameView;
import com.btmtest.utils.Dump;                                     //~0A31M~
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.ITMock.*;
import static com.btmtest.StaticVars.AG;                           //~1108I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.TileData.*;

//+0A31I~

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ITRADS                                        //~0B03R~//~1108R~//~1111R~//~1121R~
{                                                                  //~0A31I~
    private	static final String testfile="RoundStat_input";         //~1108R~//~1111R~
    private	static final int COL_DATA=3;                           //~1112R~
    private static int[] itsData;                                  //~1112R~
    private static TileData tdDiscard=null;                               //~1111I~
    private static RoundStat RS;                                   //~1112I~
    private static int func;                                       //~1113I~
    public static TileData tdTakenRobot;                           //~1123I~
    public static TileData tdRinshan;                              //~1125I~
    public static boolean swSync=true;                             //~1123I~
    public static boolean swNextLine=true;                         //~1123R~
    public static boolean swNextLine1=false;                       //~1124I~
    public static TileData[]   	tdAutoTake=new TileData[100];      //~1127I~
    public static int[]    	itsAutoTakePlayer=new int[100];        //~1127R~
    public static int ctrAutoTake,ctrAutoTakeRead;
    public static TileData tdPostTake; public static int postPlayer;//~1127R~
	//*************************************************************************//~0B01I~
    private void initEnv()                                         //~0B01I~
    {                                                              //~0B01I~
        AG=new AG();                                               //~1108R~
    	AG.appName="ｅ雀";                                         //~0B01I~//~1108R~
    	AG.appNameE="eMahJong";                                    //~0B01I~//~1108R~
        AG.context=InstrumentationRegistry.getInstrumentation().getTargetContext();//~1123I~
        AG.resource=AG.context.getResources();                     //~0B01I~
        AG.dirSep="/";                                             //~0B01I~
                                                                   //~0B01I~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~0B01I~
        Dump.open("Dump.txt",true/*sdcard*/);                      //~0B01I~
        int flag=ITM_DICEBOX|ITM_ACCOUNTS|ITM_POINTSTICK;          //~1122R~
    try                                                            //~1123I~
    {                                                              //~1123I~
        ITMock itm=new ITMock();
        itm.init(flag,0);                                 //~1122I~
                                                                   //~1112I~
        AG.createSettings();                                       //~1111M~//~1112M~
        AG.loadProp();                                             //~1111M~//~1112M~
                                                                   //~1112I~
        AG.aPlayers=new Players();                                 //~1111I~
        new Rule();
        itm.new Mock_Tiles();                                               //~1112I~//~1123R~
//      AG.aAccounts=new Accounts();                               //~1111I~//~1112R~//~1122R~
//      AG.aStatus=new Status();                                   //~1111I~//~1123R~
//      initACC();                                                 //~1111I~//~1122R~//~1123R~
     if (!swSync)                                                  //~1123R~
        new UserAction();                                          //~1123R~
     else                                                          //~1123R~
     {//~1123I~
         UserAction ua = itm.new Mock_UserAction();
     }//~1112R~//~1122R~
        AG.aTiles.shuffle();                                       //~1122I~
        new UARon(AG.aUserAction);     //also new UARonValue                   //~1112R~
     }                                                             //~1123I~
        catch(Exception e)                                         //~v@@@I~//~1123I~
        {                                                          //~v@@@I~//~1123I~
        	Dump.println(e,"ITRAD.initEnv");         //~v@@@I~     //~1123I~
        }                                                          //~v@@@I~//~1123I~
                                                                   //~1123I~
        if (Dump.Y) Dump.println("ITShanten.constructor");      //~0B03R~//~1108R~
    }                                                              //~0B01I~
    //*************************************************************************//~0A31I~
    @Before                                                        //~0A31I~
    public void setUp() throws Exception                           //~0A31M~
    {                                                              //~0A31M~
        if (Dump.Y) Dump.println("ITShanten.setUp");            //~0B03R~//~1108I~
    	initEnv();                                                 //~0B01I~
                                                                   //~1111I~
        RS=new RoundStat();                                           //~1111I~//~1112R~
	    RS.newGame(true,0,0,0/*dupctr*/);                                      //~1112I~
    }                                                              //~0A31M~
    @Test                                                          //~0B01I~
    public void testShanten()                                  //~0B03R~//~1108R~
    {                                                              //~0B01I~
        testRoundStat(testfile);                                 //~1108I~//~1111R~
    }                                                              //~0B01I~
//***********************************************                  //~1108I~//~1111M~
	private static void testRoundStat(String Pfnm)               //~1108I~//~1111I~
    {                                                              //~1108I~//~1111M~
        ITData.open(Pfnm);                                     //~1109I~//~1111I~
        while(true)                                            //~1108I~//~1111I~
        {                                                      //~1108I~//~1111I~
            String line=ITData.readLine();                     //~1109I~//~1111I~
            if (line==null)                                    //~1108I~//~1111I~
                break;                                         //~1108I~//~1111I~
            if (Dump.Y) Dump.println("ITRoundSet.testRoundStat**********************************:"+line);//~1112I~//~1123R~
            if (line.charAt(0)=='*')                               //~1111I~//~1112M~
            {                                                      //~1112I~
                continue;                                          //~1111I~//~1112M~
            }                                                      //~1112I~
            int endpos=line.indexOf("*");                       //~1112I~
            if (endpos>0)                                          //~1112I~
            	line=line.substring(0,endpos);                     //~1112I~
        	if (Dump.Y) Dump.println("ITRoundSet.testRoundStat line="+line);//~1111I~
            testLine(line);                                //~1108I~//~1111I~
        }                                                      //~1108I~//~1111I~
    }                                                              //~1108I~//~1111M~
//***********************************************                  //~1123I~
	private static void sleep()                                           //~1123I~
    {                                                              //~1123I~
        if (Dump.Y) Dump.println("ITRoundSet.sleep start 100 ms swNextLine="+swNextLine);//~1123R~//~1124R~
    	for (;;)                                                   //~1123I~
        {                                                          //~1123I~
	   		Utils.sleep(100);                                      //~1123R~
        	if (swNextLine)                                        //~1123M~
            {                                                      //~1124I~
        		if (swNextLine1)                                   //~1124I~
                {                                                  //~1124I~
    			    if (Dump.Y) Dump.println("ITRoundSet.sleep swnextline1 more 100 ms");//~1125I~
			   		Utils.sleep(500);                              //~1124I~//~1125R~
	        		swNextLine1=false;                             //~1124I~
                }                                                  //~1124I~
            	break;                                             //~1123M~
            }                                                      //~1124I~
        }                                                          //~1123I~
        if (Dump.Y) Dump.println("ITRoundSet.sleep end 100 ms");    //~1123I~//~1124R~
    }                                                              //~1123I~
//***********************************************                  //~1125I~
	private static void sleep(int Psleep)                          //~1125I~
    {                                                              //~1125I~
        if (Dump.Y) Dump.println("ITRoundSet.sleep start sleep "+Psleep);//~1125I~
        int ms=Psleep;                                                 //~1125I~
    	for (;;)                                                   //~1125I~
        {                                                          //~1125I~
	   		Utils.sleep(100);                                      //~1125I~
            ms-=100;                                               //~1125I~
            if (Dump.Y) Dump.println("ITRoundSet.sleep remain="+ms);//~1125I~
            if (ms<0)                                              //~1125I~
            {                                                      //~1125I~
                break;                                             //~1125I~
            }                                                      //~1125I~
        }                                                          //~1125I~
        if (Dump.Y) Dump.println("ITRoundSet.sleep end   sleep "+Psleep);//~1125I~
    }                                                              //~1125I~
//***********************************************                  //~1108I~
//test file                                                        //~1108I~
//1st lien:line ctr                                                //~1108I~
//2nd 14 items of tile id                                          //~1108I~
// man:0-8, pin:9-17, sou:18-26, ji:27-30,31-33                    //~1108I~
//***********************************************                  //~1108I~
	private static boolean testLine(String Pline)                  //~1108I~
    {                                                              //~1108I~
    	String line=Pline.trim();                                  //~1108I~
        String[] data=line.split(" +");                            //~1108I~//~1111R~
        if (data.length==0)                                        //~1112I~
            return false;                                          //~1112I~
    	itsData=new int[data.length];                              //~1112I~
        try                                                        //~1111I~
        {                                                          //~1111I~
        	for (int ii=0;ii<data.length;ii++)                         //~1108I~//~1111R~
			{                                                          //~1108I~//~1111R~
            	itsData[ii]=Integer.parseInt(data[ii]);            //~1111I~
            }                                                      //~1111I~
        }                                                          //~1111I~
        catch(NumberFormatException e)                         //~1108I~//~1111I~
        {                                                      //~1108I~//~1111I~
            System.out.println("line="+Pline+"\n"+e.toString());//~1108I~//~1111I~
        }                                                      //~1108I~//~1111I~
        try                                                        //~1108I~
        {                                                          //~1108I~
        	callRoundTest(itsData,data.length);//~1108I~                       //~1111R~//~1112R~
        }                                                          //~1108I~
        catch(Exception e)                                         //~1108I~//~1111I~
        {                                                          //~1108I~//~1111I~
        	Dump.println(e,"ITRoundTest testLine");                //~1111I~
            System.exit(4);                                        //~1108I~//~1111I~
        }                                                          //~1108I~//~1111I~
        return true;                                               //~1108I~
    }                                                              //~1108I~
    //**************************************************************//~1111I~
    //*func,player,eswn,TD data                                         //~1111I~//~1121R~
    //*5:deal, 11 take, 17:discard 20 openreach  15 reach          //~1112I~//~1121R~
    //*12:pon 13:chii, 14:kan 15:reach                             //~1112I~
    //*250:startgame                                               //~1112I~
    //**************************************************************//~1111I~
	private static void callRoundTest(int[] PitsData,int PctrData)              //~1111I~//~1112R~
    {                                                              //~1111I~
        if (Dump.Y) Dump.println("ITRoundSet.callRoundTest data="+Arrays.toString(PitsData));//~1111I~
		TileData td;                                               //~1121I~
        func=PitsData[0];                                      //~1111I~//~1113R~
        int player=PitsData[1];                                    //~1111I~
        int eswn=PitsData[2];                                      //~1112I~
        TileData[] tds;                                            //~1111I~
        if (func!=GCM_TAKE)                                        //~1127I~
        {                                                          //~1127I~
	        if (Dump.Y) Dump.println("ITRoundSet.callRoundTest Not Take Msg func="+func);//~1127I~
            if (tdPostTake!=null)                                  //~1127I~
            {                                                      //~1127I~
		        if (Dump.Y) Dump.println("ITRoundSet.callRoundTest **************** Not Take Msg saved td="+tdPostTake.toString());//~1127R~
//              AG.aPlayers.setCurrentPlayerWithoutLight(Players.prevPlayer(postPlayer));//~1127R~
//                String strTD= ACAction.strTD(tdPostTake);          //~1127R~//+1128R~
//                String msg=eswn+MSG_SEPAPP2+strTD;                 //~1127R~//+1128R~
//                UADelayed.postDelayedRobotMsg(false/*PswWaiterBlock*/,0/*delayRobot*/,GCM_TAKE,eswn,msg);   //after current action process returned//~1127R~//+1128R~
                AG.aGC.sendMsg(GCM_TAKE,null);                     //+1128I~
                tdPostTake=null;                                   //~1127I~
            }                                                      //~1127I~
        }                                                          //~1127I~
    	switch(func)                                               //~1111I~
        {                                                          //~1111I~
        case GCM_DEAL: //deal                                             //~1111I~//~1112R~
			tds=makeTDs(player,PitsData,PctrData);                     //~1111I~//~1112R~
            AG.aPlayers.setInitialDeal(player,tds,0/*pos in shuffled*/,eswn,true/*swSort*/);//~1112R~
//          RS.deal(player,player/*eswn*/);             //~1111I~  //~1112R~
        	break;                                                 //~1111I~
        case GCM_TAKE: //take                                             //~1111I~//~1112R~
//            if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE swSync="+swSync);//~1125I~//~1127R~
//            td=makeTD(player,PitsData[COL_DATA]);                          //~1111I~//~1112R~//~1121R~//~1127R~
//            boolean swRobot=PitsData[COL_DATA+1]==2;               //~1112I~//~1126R~//~1127R~
//            boolean swRinshan=PitsData[COL_DATA+1]==3;             //~1126R~//~1127R~
//            if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE swRobot="+swRobot+",swRinshan="+swRinshan);//~1126I~//~1127R~
////          RS.takeOne(player,eswn,td);       //~1111I~            //~1112R~//~1127R~
////            AG.aPlayers.takeOne(player,td,true/*shadow*/);    //~1112R~//~1123R~//~1127R~
////            if (swRobot)                                           //~1112I~//~1123R~//~1127R~
////            {                                                      //~1112I~//~1123R~//~1127R~
////                Robot r=AG.aAccounts.getRobot(player);              //~1112I~//~1123R~//~1127R~
////                r.takeOne(player,td);                              //~1112I~//~1123R~//~1127R~
////            }                                                      //~1112I~//~1123R~//~1127R~
//            String strTD= ACAction.strTD(td);                     //~va60R~//~1123I~//~1127R~
//            String msg=eswn+MSG_SEPAPP2+strTD;                     //~1123I~//~1127R~
//                swNextLine=false; //wait discard msg               //~1123M~//~1127R~
//                swNextLine1=false; //wait discard msg              //~1124I~//~1127R~
////          if (!AG.aPlayers.isLastActionIsKan())                  //~1125R~//~1127R~
//            if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE Mock_UserAction_lastAction="+ITMock.Mock_UserAction_lastAction);//~1126I~//~1127R~
//            if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE Mock_UserAction.prevActionID="+ITMock.Mock_UserAction_prevActionID);//~1126I~//~1127R~
////          if (ITMock.Mock_UserAction_lastAction!=GCM_KAN)      //~1125I~//~1126R~//~1127R~
////          if (ITMock.Mock_UserAction_prevActionID!=GCM_KAN)      //~1126R~//~1127R~
//            if (swRobot)                                           //~1126I~//~1127R~
//            {                                                      //~1125I~//~1126M~//~1127R~
//                tdTakenRobot=makeTD(player,PitsData[COL_DATA+2]);  //~1126M~//~1127R~
//                if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE tdTakeRobot="+tdTakenRobot.toString());//~1126I~//~1127R~
//            }                                                      //~1125I~//~1126M~//~1127R~
//            else                                                   //~1126I~//~1127R~
//            if (swRinshan)                                         //~1126I~//~1127R~
//            {                                                      //~1126I~//~1127R~
//                tdRinshan=makeTD(player,PitsData[COL_DATA+2]);     //~1126M~//~1127R~
//                if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE tdRinshan="+tdRinshan.toString());//~1126I~//~1127R~
//            }                                                      //~1126I~//~1127R~
//            {                                                      //~1125I~//~1127R~
//                if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE setCurrentPlayer by last action is not kan");//~1125I~//~1127R~
//                AG.aPlayers.setCurrentPlayerWithoutLight(Players.prevPlayer(player));//~1124I~//~1125R~//~1127R~
//            }                                                      //~1125I~//~1127R~
//          if (!swSync)                                             //~1123R~//~1127R~
//          {                                                        //~1123I~//~1127R~
//            Robot.autoTakeTimeout(player);                          //~1123I~//~1127R~
//          }                                                        //~1123I~//~1127R~
//          else                                                     //~1123I~//~1127R~
//          {                                                        //~1123I~//~1127R~
//            ((ITMock.Mock_UserAction)AG.aUserAction).actionMock(func,player,msg);             //~1123I~//~1127R~
////          sleep();                                           //~1123R~//~1125I~//~1126R~//~1127R~
//          }                                                        //~1123I~//~1127R~
//            tdDiscard=td;                                          //~1111I~//~1127R~
//*************************                                        //~1127I~
            td=makeTD(player,PitsData[COL_DATA]);                  //~1127I~
            boolean swAutoTake=PitsData[COL_DATA+1]!=0;            //~1127I~
            if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE swAutoTake="+swAutoTake+",ctrAutoTake="+ctrAutoTake+",ctrAutoTakeRead="+ctrAutoTakeRead+",td="+td.toString());//~1127I~
            if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE Mock_UserAction_lastAction="+ITMock.Mock_UserAction_lastAction);//~1127I~
            if (Dump.Y) Dump.println("ITRoundSet.GCM_TAKE Mock_UserAction.prevActionID="+ITMock.Mock_UserAction_prevActionID);//~1127I~
            itsAutoTakePlayer[ctrAutoTake]=player;                 //~1127I~
            tdAutoTake[ctrAutoTake++]=td;                          //~1127I~
            if (!swAutoTake)                                       //~1127I~
            {                                                      //~1127I~
            	tdPostTake=td;                                     //~1127I~
            	postPlayer=player;                                 //~1127I~
            }                                                      //~1127I~
        	break;                                                 //~1111I~
        case GCM_DISCARD: //discard                                          //~1111I~//~1112R~
//          RS.discard(player,tdDiscard);               //~1111R~  //~1112R~
			boolean swRobot2=PitsData[COL_DATA+1]!=0;               //~1121I~
            if (swRobot2)                                           //~1121I~
            {                                                      //~1121I~
				td=makeTD(player,PitsData[COL_DATA]);              //~1121I~
            	Robot r=AG.aAccounts.getRobot(player);             //~1121I~
            	r.takeOne(player,td);   //-->aftertakeOne-->RADiscad//~1121I~
            }                                                      //~1121I~
            else                                                   //~1121I~
            {                                                      //~1122I~
            	AG.aAccounts.accounts[player].type=0;              //~1122I~
	            AG.aPlayers.discard(player,tdDiscard);                        //~1112I~//~1121R~
            }                                                      //~1122I~
        	break;                                                 //~1111I~
        case GCM_REACH_OPEN: //openReach                           //~1112R~
            RS.reachOpen(player);                                  //~1112I~
        	break;                                                 //~1112I~
        case GCM_STARTGAME: //newgame                              //~1112R~
	        int ctrSet=PitsData[1];                                //~1112I~
        	int ctrGame=PitsData[2];
        	int ctrDup=PitsData[3];//~1112I~
	        RS.newGame(false,ctrSet,ctrGame,ctrDup);                      //~1112R~
        	break;                                                 //~1112I~
        case GCM_PON: //pos                                        //~1112I~
			tds=makeTDs(player,PitsData,PctrData);                 //~1112I~
            AG.aPlayers.takePon(player,tds);                       //~1112I~
        	break;                                                 //~1112I~
        case GCM_KAN: //pos                                        //~1112I~
			tds=makeTDs(player,PitsData,PctrData);                 //~1112I~
            AG.aPlayers.takeKan(player,tds);                       //~1112I~
        	break;                                                 //~1112I~
        case GCM_REACH:                                            //~1121I~
			AG.aPlayers.reach(player);                             //~1121I~
			AG.aRoundStat.reach(player);                          //~1121I~
        	break;                                                 //~1121I~
        case 99:                                                   //~1125I~
        	sleep(player);                                         //~1125I~
        	break;                                                 //~1125I~
        }                                                          //~1111I~
    }                                                              //~1111I~
	private static TileData[] makeTDs(int Pplayer,int[] PitsData,int PctrData)        //~1111I~//~1112R~
    {                                                              //~1111I~
    	TileData[] tds=new TileData[PctrData-COL_DATA];                   //~1111I~//~1112R~
    	for (int ii=COL_DATA;ii<PctrData;ii++)                     //~1111I~//~1112R~
        {                                                          //~1111I~
        	int iTD=PitsData[ii];                                   //~1111I~
            tds[ii-COL_DATA]=makeTD(Pplayer,iTD);                         //~1111I~//~1112R~
        }                                                          //~1111I~
        return tds;                                                //~1111I~
    }                                                              //~1111I~
	private static TileData makeTD(int Pplayer,int PiTD)               //~1111I~
    {                                                              //~1111I~
    	int type,num,remain,red,flag;                                   //~1111I~
        if (PiTD==0)    //last discarded                           //~1112I~
        {                                                          //~1112I~
	        if (func!=GCM_DEAL)                                    //~1113I~
	            return AG.aPlayers.getLastDiscarded();                 //~1112I~//~1113R~
        }                                                          //~1112I~
        type=PiTD/1000;                                            //~1111I~
        num=(PiTD%1000)/100;                                       //~1111I~
        remain=(PiTD%100)/10;                                      //~1111I~
        red=(PiTD%10);                                             //~1111I~
        flag=red!=0 ? TDF_RED5 : 0;                                //~1111I~
    	TileData td=new TileData(type,num,flag,remain,Pplayer);    //~1111I~
        if (Dump.Y) Dump.println("ITRoundSet.makeTD player="+Pplayer+",id="+PiTD+",td="+td.toString());//~1111I~
        return td;                                                 //~1111I~
    }                                                              //~1111I~
}
