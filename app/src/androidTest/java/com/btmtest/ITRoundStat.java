//*CID://+dateR~:                             update#=   43;       //~1108I~
//******************************************************************//~0B03I~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//******************************************************************//~0B03I~
//*test data:androidTest\assets\calshan_input1                     //~1108I~
//*tablefile:main/assets\calshan_bytetbl.zip                       //~1108I~
//*result Dump(adbhw3pulldump)                                     //~1108I~
//******************************************************************//~1108I~
package com.btmtest;
//******************************************************************//~0B01R~
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
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.RA.RoundStat;
import com.btmtest.game.Robot;
import com.btmtest.game.Rule;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.game.UA.UARon;
import com.btmtest.game.UserAction;
import com.btmtest.utils.Dump;                                     //~0A31M~
import java.util.Arrays;

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
public class ITRoundStat                                        //~0B03R~//~1108R~//~1111R~
{                                                                  //~0A31I~
    private	static final String testfile="RoundStat_input";         //~1108R~//~1111R~
    private	static final int COL_DATA=3;                           //~1112R~
    private static int[] itsData;                                  //~1112R~
    private static TileData tdDiscard=null;                               //~1111I~
    private static RoundStat RS;                                   //~1112I~
    private static int func;                                       //+1113I~
	//*************************************************************************//~0B01I~
    private void initEnv()                                         //~0B01I~
    {                                                              //~0B01I~
        AG=new AG();                                               //~1108R~
    	AG.appName="ｅ雀";                                         //~0B01I~//~1108R~
    	AG.appNameE="eMahJong";                                    //~0B01I~//~1108R~
        AG.context=InstrumentationRegistry.getInstrumentation().getTargetContext();     //~1108I~
        AG.resource=AG.context.getResources();                     //~0B01I~
        AG.dirSep="/";                                             //~0B01I~
                                                                   //~0B01I~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~0B01I~
        Dump.open("Dump.txt",true/*sdcard*/);                      //~0B01I~
        new ITMock().init(ITM_DICEBOX|ITM_ACCOUNTS,0);             //~1112R~
                                                                   //~1112I~
        AG.createSettings();                                       //~1111M~//~1112M~
        AG.loadProp();                                             //~1111M~//~1112M~
                                                                   //~1112I~
        AG.aPlayers=new Players();                                 //~1111I~
        new Rule();
        new Tiles();                                               //~1112I~
//      AG.aAccounts=new Accounts();                               //~1111I~//~1112R~
        AG.aStatus=new Status();                                   //~1111I~
        initACC();                                                 //~1111I~
        UserAction ua=new UserAction();                            //~1112R~
        new UARon(ua);     //also new UARonValue                   //~1112R~
        if (Dump.Y) Dump.println("ITShanten.constructor");      //~0B03R~//~1108R~
    }                                                              //~0B01I~
    private void initACC()                                         //~1111I~
    {                                                              //~1111I~
        if (Dump.Y) Dump.println("Accounts.init");                 //~v@@@I~//~1111I~
    	boolean rc=true;                                           //~v@@@I~//~1111I~
        Accounts.Account[] accounts=new Accounts.Account[PLAYERS];                             //~v@@@M~//~1111I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@M~//~1111I~
        {                                                          //~v@@@I~//~1111I~
        	accounts[ii]=AG.aAccounts.newAccount(ii);                          //~v@@@I~//~1111I~
        }                                                          //~v@@@I~//~1111I~
		Accounts.Account[] byESWN=new Accounts.Account[PLAYERS];                    //~1111I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~//~1111I~
        {                                                          //~v@@@I~//~1111I~
        	byESWN[ii]=accounts[ii];                               //~1111R~
        }                                                          //~v@@@I~//~1111I~
		AG.aAccounts.accounts=accounts;                            //~1111R~
		AG.aAccounts.accountsByESWN=byESWN;                        //~1111I~
        AG.aAccounts.setCurrentAccountsByESWN();                   //~1111M~
    }                                                              //~1111I~
    //*************************************************************************//~0A31I~
    @Before                                                        //~0A31I~
    public void setUp() throws Exception                           //~0A31M~
    {                                                              //~0A31M~
        if (Dump.Y) Dump.println("ITShanten.setUp");            //~0B03R~//~1108I~
    	initEnv();                                                 //~0B01I~
                                                                   //~1111I~
        RS=new RoundStat();                                           //~1111I~//~1112R~
	    RS.newGame(true,0,0,0);                                      //~1112I~
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
            if (Dump.Y) Dump.println("ITRoundSet.testRoundStat**********************************"+line);//~1112I~
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
    //*func,player,TD data                                         //~1111I~
    //*5:deal, 11 take, 17:discard 20 openreach                    //~1112I~
    //*12:pon 13:chii, 14:kan 15:reach                             //~1112I~
    //*250:startgame                                               //~1112I~
    //**************************************************************//~1111I~
	private static void callRoundTest(int[] PitsData,int PctrData)              //~1111I~//~1112R~
    {                                                              //~1111I~
        if (Dump.Y) Dump.println("ITRoundSet.callRoundTest data="+Arrays.toString(PitsData));//~1111I~
        func=PitsData[0];                                      //~1111I~//+1113R~
        int player=PitsData[1];                                    //~1111I~
        int eswn=PitsData[2];                                      //~1112I~
        TileData[] tds;                                            //~1111I~
    	switch(func)                                               //~1111I~
        {                                                          //~1111I~
        case GCM_DEAL: //deal                                             //~1111I~//~1112R~
			tds=makeTDs(player,PitsData,PctrData);                     //~1111I~//~1112R~
            AG.aPlayers.setInitialDeal(player,tds,0/*pos in shuffled*/,eswn,true/*swSort*/);//~1112R~
//          RS.deal(player,player/*eswn*/);             //~1111I~  //~1112R~
        	break;                                                 //~1111I~
        case GCM_TAKE: //take                                             //~1111I~//~1112R~
			TileData td=makeTD(player,PitsData[COL_DATA]);                          //~1111I~//~1112R~
			boolean swRobot=PitsData[COL_DATA+1]!=0;               //~1112I~
//          RS.takeOne(player,eswn,td);       //~1111I~            //~1112R~
            AG.aPlayers.takeOne(player,td,true/*shadow*/);    //~1112R~
            if (swRobot)                                           //~1112I~
            {                                                      //~1112I~
            	Robot r=AG.aAccounts.getRobot(player);              //~1112I~
            	r.takeOne(player,td);                              //~1112I~
            }                                                      //~1112I~
            tdDiscard=td;                                          //~1111I~
        	break;                                                 //~1111I~
        case GCM_DISCARD: //discard                                          //~1111I~//~1112R~
//          RS.discard(player,tdDiscard);               //~1111R~  //~1112R~
            AG.aPlayers.discard(player,tdDiscard);                        //~1112I~
        	break;                                                 //~1111I~
        case GCM_REACH_OPEN: //openReach                           //~1112R~
            RS.reachOpen(player);                                  //~1112I~
        	break;                                                 //~1112I~
        case GCM_STARTGAME: //newgame                              //~1112R~
	        int ctrSet=PitsData[1];                                //~1112I~
        	int ctrGame=PitsData[2];
	        RS.newGame(false,ctrSet,ctrGame,0);                      //~1112R~
        	break;                                                 //~1112I~
        case GCM_PON: //pos                                        //~1112I~
			tds=makeTDs(player,PitsData,PctrData);                 //~1112I~
            AG.aPlayers.takePon(player,tds);                       //~1112I~
        	break;                                                 //~1112I~
        case GCM_KAN: //pos                                        //~1112I~
			tds=makeTDs(player,PitsData,PctrData);                 //~1112I~
            AG.aPlayers.takeKan(player,tds);                       //~1112I~
        	break;                                                 //~1112I~
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
	        if (func!=GCM_DEAL)                                    //+1113I~
	            return AG.aPlayers.getLastDiscarded();                 //~1112I~//+1113R~
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
