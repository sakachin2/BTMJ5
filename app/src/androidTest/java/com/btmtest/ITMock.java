//*CID://+dateR~:                             update#=  130;       //~1108I~
//******************************************************************//~0B03I~
//2021/11/01 vafh (Bug) for HonChanta(TerminalMix)                 //+1B01I~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//******************************************************************//~0B03I~
//*test data:androidTest\assets\calshan_input1                     //~1108I~
//*tablefile:main/assets\calshan_bytetbl.zip                       //~1108I~
//*result Dump(adbhw3pulldump)                                     //~1108I~
//******************************************************************//~1108I~
package com.btmtest;
//******************************************************************//~0B01R~

import android.content.Context;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.GC;
import com.btmtest.game.GCMsgID;
import com.btmtest.game.Players;
import com.btmtest.game.Robot;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.game.UA.UAChii;
import com.btmtest.game.UA.UADiscard;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.UA.UAKan;
import com.btmtest.game.UA.UAPon;
import com.btmtest.game.UA.UAReach;
import com.btmtest.game.UA.UARon;
import com.btmtest.game.UA.UATake;
import com.btmtest.game.UADelayed;
import com.btmtest.game.UADelayed2;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.DiceBox;
import com.btmtest.game.gv.GMsg;
import com.btmtest.game.gv.GameView;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.game.gv.Hands;
import com.btmtest.game.gv.HandsTouch;
import com.btmtest.game.gv.NamePlate;
import com.btmtest.game.gv.PointStick;
import com.btmtest.game.gv.River;
import com.btmtest.game.gv.Stock;
import com.btmtest.utils.Dump;                                     //~0A31M~
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.SPList;
import com.btmtest.utils.sound.Sound;
import com.btmtest.utils.sound.SoundList;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~1109I~
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Status.*;

//~1109I~
public class ITMock                                          //~0B03R~//~1108R~//~1109R~//~1112R~
{                                                                  //~0A31I~
    private int newClass,optMock;                                      //~1112I~
    public static final int ITM_DICEBOX=0x01;                      //~1112R~
    public static final int ITM_ACCOUNTS=0x02;                     //~1112I~
    public static final int ITM_POINTSTICK=0x04;                   //~1121I~
    public static final int ITM_USERACTION=0x08;                   //~1122I~
//    private static int msgActionID,msgSender,msgEswn;                      //~1122I~//~1123R~//~1126R~
    private static String[] msgData=new String[8];                                 //~1122I~
    private static int[]  msgIntData;                              //~1123R~
    public static int Mock_UserAction_lastAction;                  //~1125I~
    public static int Mock_UserAction_prevActionID;                //~1126I~
//***********************************************                  //~1108I~
	public void init(int PnewClass,int Popt)                       //~1112R~
    {                                                              //~1108I~
        TestOption.option2|=TO2_IT; //instrumented test            //~1112M~
    	new Mock_GC();                                             //~1123I~
    	newClass=PnewClass;                                        //~1112I~
    	optMock=Popt;                                              //~1112I~
//      if ((newClass=ITM_ACCOUNTS)!=0)                            //~1112I~//~1123R~
        new Mock_Accounts();                                   //~1112I~//~1123R~
        AG.aStatus=new Status();                                   //~1123I~
        initACC();                                                 //~1123I~
        if ((newClass=ITM_DICEBOX)!=0)                                 //~1112I~
        	new Mock_DiceBox();                                     //~1112I~
        if ((newClass=ITM_POINTSTICK)!=0)                          //~1121I~
        	new Mock_PointStick();                                 //~1121I~
      	if ((newClass=ITM_USERACTION)!=0)                          //~1122R~//~1611R~
      		new Mock_UserAction();                                 //~1122R~//~1611R~
        AG.aHands=new Mock_Hands();                                //~1123R~
        AG.aRiver=new Mock_River();                                          //~1123I~
        AG.aStock=new Mock_Stock();                                //~1123I~
        AG.aStock=new Mock_Stock();                                //~1131I~
        AG.aNamePlate=new Mock_NamePlate();                        //~1131I~
        new Mock_Sound();                                          //~1123R~
        AG.aGMsg=new Mock_GMsg();                                  //~1123I~
    }                                                              //~1108I~
    private void initACC()                                         //~1111I~//~1123M~
    {                                                              //~1111I~//~1123M~
        if (Dump.Y) Dump.println("Accounts.init");                 //~v@@@I~//~1111I~//~1123M~
    	boolean rc=true;                                           //~v@@@I~//~1111I~//~1123M~
        Accounts.Account[] accounts=new Accounts.Account[PLAYERS];                             //~v@@@M~//~1111I~//~1123M~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@M~//~1111I~//~1123M~
        {                                                          //~v@@@I~//~1111I~//~1123M~
        	accounts[ii]=AG.aAccounts.newAccount(ii);                          //~v@@@I~//~1111I~//~1123M~
        	accounts[ii].robot= new Mock_Robot(accounts[ii]);      //~1123I~
        }                                                          //~v@@@I~//~1111I~//~1123M~
		Accounts.Account[] byESWN=new Accounts.Account[PLAYERS];                    //~1111I~//~1123M~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~//~1111I~//~1123M~
        {                                                          //~v@@@I~//~1111I~//~1123M~
        	byESWN[ii]=accounts[ii];                               //~1111R~//~1123M~
        }                                                          //~v@@@I~//~1111I~//~1123M~
//  	AG.aAccounts.getAccountNames();                              //~v@@@I~//~1131R~
		AG.aAccounts.accounts=accounts;                            //~1111R~//~1123M~
		AG.aAccounts.accountsByESWN=byESWN;                        //~1111I~//~1123M~
        AG.aAccounts.setCurrentAccountsByESWN();                   //~1111M~//~1123M~
        new ACAction(AG.aAccounts);                                //~1123M~
        Complete.newInstance();                                    //~1130I~
    }                                                              //~1111I~//~1123M~
    public void setNextLine(boolean Psw1)                          //~1125I~
    {                                                              //~1125I~
        	if (Dump.Y) Dump.println("ITMock.setNextLine stop sleep sw1="+Psw1);//~1125R~
            ITRADS.swNextLine=true;                                //~1123I~//~1125M~
            if (Psw1)                                              //~1125I~
				ITRADS.swNextLine1=true;                           //~1125I~
    }                                                              //~1125I~
//***********************************************                  //~1112I~
	class Mock_DiceBox extends DiceBox                             //~1112I~
    {                                                              //~1112I~
    	public Mock_DiceBox()                                        //~1112I~
        {                                                          //~1112I~
        	if (Dump.Y) Dump.println("Mock_DiceBox.constructor");  //~1112I~
	        AG.aDiceBox=this;                                          //~va60I~//~1112I~
        }                                                          //~1112I~
        @Override
        public void setWaitingDiscard(int Pplayer)                    //~v@@6I~//~1112I~
        {                                                          //~1112I~
        	if (Dump.Y) Dump.println("Mock_DiceBox.setWaitingDiscard player="+Pplayer);//~1112I~
        }                                                          //~1112I~
        @Override                                                  //~1112I~
    	public void drawLightTakeOne(int Pplayer,boolean PswShadow)    //~v@21I~//~1112I~
        {                                                          //~1112I~
        	if (Dump.Y) Dump.println("Mock_DiceBox.drawLightTakeOne player="+Pplayer+",swShadow="+PswShadow);//~1112I~
        }                                                          //~1112I~
        @Override                                                  //~1112I~
	    public void drawLightDiscard(int Pplayer)                      //~v@21I~//~1112I~
        {                                                          //~1112I~
        	if (Dump.Y) Dump.println("Mock_DiceBox.drawLightDiscard player="+Pplayer);//~1112R~
        }                                                          //~1112I~
        @Override                                                  //~1112I~
	    public void drawLightCurrent(int Pplayer)                      //~v@@@I~//~1112I~
        {                                                          //~1112I~
        	if (Dump.Y) Dump.println("Mock_DiceBox.drawLightCurrent player="+Pplayer);//~1112I~
        }                                                          //~1112I~
        @Override                                                  //~1112I~
	    public void drawLightCurrent(int Pplayer,boolean PswShadow)    //~v@21I~//~1112I~
        {                                                          //~1112I~
        	if (Dump.Y) Dump.println("Mock_DiceBox.drawLightCurrent player="+Pplayer+",swShadow="+PswShadow);//~1112I~
        }                                                          //~1112I~
        @Override                                                  //~1124I~
	    public void drawLightKanTakable(int Pplayer,boolean PswShadow) //~0403I~//~1124I~
        {                                                          //~1124I~
        	if (Dump.Y) Dump.println("Mock_DiceBox.drawLightKanTakable");//~1124I~
        }                                                          //~1124I~
    	public void drawLightDiscardTimeout(int Pplayer,boolean PswShadow)//~v@21I~//~1126I~
        {                                                          //~1126I~
        	if (Dump.Y) Dump.println("Mock_DiceBox.drawLightDiscardTimeout");//~1126I~
        }                                                          //~1126I~
	    public void drawLightDiscardRonTimeout(int Pplayer)            //~v@11I~//~1126I~
        {                                                          //~1126I~
        	if (Dump.Y) Dump.println("Mock_DiceBox.drawLightDiscardRonTimeout");//~1126I~
        }                                                          //~1126I~
    }                                                              //~1112M~
//***********************************************                  //~1112I~
	class Mock_Accounts extends Accounts                           //~1112I~
    {                                                              //~1112I~
    	public Mock_Accounts()                                     //~1112I~
        {                                                          //~1112I~
        	if (Dump.Y) Dump.println("Mock_Accounts.constructor"); //~1112I~
	        AG.aAccounts=this;                                     //~1112I~
        }                                                          //~1112I~
        @Override                                                  //~1112I~
        public Robot getRobot(int Pplayer)                         //~1112I~
        {                                                          //~1112I~
        	if (Dump.Y) Dump.println("Mock_Accounts.getRobot player="+Pplayer);//~1112R~
        	Robot r=super.getRobot(Pplayer);                   //~1112I~
            if (r==null)                                            //~1112I~
//  	    	r=Robot.createNewInstance(AG.aAccounts.accounts[Pplayer]);           //~v@@@R~//~1112R~
    	    	r=createNewInstance(AG.aAccounts.accounts[Pplayer]);//~1112I~
            return r;                                              //~1112I~
        }                                                          //~1112I~
        public Robot createNewInstance(Accounts.Account Pacc)      //~1112I~
        {                                                          //~1112I~
        	if (Dump.Y) Dump.println("Mock_Accounts.createNewInstance");//~1112I~
	        Robot r= new Mock_Robot(Pacc);                                      //~@002I~//~1112R~
		    return r;                                              //~1112I~
        }                                                          //~1112I~
	    public void reachDonePay(int Pplayer)                          //~9511R~//~1121I~
        {                                                          //~1121I~
        	if (Dump.Y) Dump.println("Mock_Accounts.reachDonepay player="+Pplayer);//~1121I~
        }                                                          //~1121I~
		public String currentEswnToAccountName(int Peswn)     //~0218I~//~1131I~
        {                                                          //~1131I~
        	if (Dump.Y) Dump.println("Mock_Accounts.currentEswnToAccountName eswn="+Peswn);//~1131I~
            return "RobotEswn"+Peswn;                              //~1131I~
        }                                                          //~1131I~
		public void sendToTheClient(int Pplayer,int PmsgID,String Pmsg)//~v@@@I~//~1131I~
        {                                                          //~1131I~
        	if (Dump.Y) Dump.println("Mock_Accounts.sendToTheClient player="+Pplayer+",psdid="+PmsgID+",msg="+Pmsg);//~1131I~
        }                                                          //~1131I~
    }                                                              //~1112I~
//***********************************************                  //~1112I~
	class Mock_Robot extends Robot                                 //~1112I~
    {                                                              //~1112I~
    	public Mock_Robot(Accounts.Account Pacc)                   //~1112I~
        {                                                          //~1112I~
        	super();                                               //~1112I~
    		account=Pacc;                                        //~1112I~
        	init();                                              //~1112I~
        }                                                          //~1112I~
    	@Override                                                  //~1112I~
        public  void calledKanAtTake()                             //~1124R~
        {                                                              //~v@@@I~//~@002I~//~1123I~
            if (Dump.Y) Dump.println("Mock_Robot.calledKanAtTake reset sleep");//~1124R~//~1125R~
            setNextLine(true);       //wait next kan take          //~1125R~
        }                                                              //~v@@@I~//~@002I~//~1123I~
//        @Override                                                  //~1124I~//~1126R~
//        public  void sendToServer(boolean PswWaiterBlock,int PactionID,int Peswn,String Pdata)//~1124I~//~1126R~
//        {                                                          //~1124I~//~1126R~
//            if (Dump.Y) Dump.println("Mock_Robot.sendToServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",eswn="+Peswn+",msgDataToServer="+Pdata);//~1124I~//~1126R~
//            String msg=Peswn+MSG_SEPAPP2+Pdata;                    //~1124I~//~1126R~
//            UADelayed.postDelayedRobotMsg(PswWaiterBlock,0/*elayRobot*/,PactionID,0/*initialEswn*/,msg); //after current action process returned//~1124I~//~1126R~
//        }                                                          //~1124I~//~1126R~
//        public  void sendToServerIT(boolean PswWaiterBlock,int PactionID,int Peswn,String Pdata)//~v@@@R~//~@002R~//~1112I~//~1123R~//~1126R~
//        {                                                              //~v@@@I~//~@002I~//~1112I~//~1126R~
//            if (Dump.Y) Dump.println("Mock_Robot.sendToServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",eswn="+Peswn+",msgDataToServer="+Pdata);//~@002I~//~1122I~//~1126R~
//            String msg=Peswn+MSG_SEPAPP2+Pdata;//~v@@@I~               //~@002R~//~1122I~//~1126R~
////          postDelayedRobotMsg(PswWaiterBlock,0/*delayRobot*/,PactionID,initialEswn,msg);  //after current action process returned//~@002R~//~9624R~//~1122R~//~1126R~
//            postDelayedRobotMsg(PswWaiterBlock,0/*delayRobot*/,PactionID,0/*initialEswn*/,msg); //after current action process returned//~1122I~//~1126R~
//        }                                                          //~1112R~//~1126R~
//        public  void postDelayedRobotMsg(boolean PswWaiterBlock,int Ptime/*milisec*/,int PactionID,int Psender,String PmsgData)//~v@@@R~//~9624R~//~1122R~//~1126R~
//        {                                                              //~v@@@I~//~1122I~//~1126R~
////            if (AG.aUADelayed.swStop)                                  //~9B20I~//~1122I~//~1126R~
////            {                                                          //~9B20I~//~1122I~//~1126R~
////                if (Dump.Y) Dump.println("UADelayed.postDelayedRobotMsg return by swStop");//~9B20I~//~1122I~//~1126R~
////                return;                                                //~9B20I~//~1122I~//~1126R~
////            }                                                          //~9B20I~//~1122I~//~1126R~
//            if (Dump.Y) Dump.println("Mock_Robot.UADelayed.postDelayedRobotMsg actionID="+PactionID+"="+GCMsgID.getEnum(PactionID)+",sender="+Psender+",data="+PmsgData);//~v@@@R~//~9624R~//~1122I~//~1126R~
////          Message msg=GameViewHandler.obtainMsg(GCM_RECEIVED_APPMSG,GCM_USER_ACTION,Psender,Integer.toString(PactionID),PmsgData,Integer.toString(Ptime));//~9625I~//~1122R~//~1126R~
//            msgActionID=PactionID;                                 //~1122I~//~1126R~
//            msgEswn=Psender;                    //~1122I~          //~1123R~//~1126R~
//            msgData[0]=PmsgData;                                      //~1122I~//~1126R~
//            msgIntData=UserAction.parseMsgDataToClient(PmsgData);  //~1123I~//~1126R~
////          GameViewHandler.sendMsgDelayed(msg,Ptime);           //~v@@@R~//~9625R~//~0229R~//~1122I~//~1126R~
////          userAction(msg);                               //~v@@@R~//~1122R~//~1126R~
//            userAction();                                          //~1122I~//~1126R~
//        }                                                              //~v@@@I~//~1122I~//~1126R~
//      public boolean userAction(Message Pmsg)                        //~v@@@R~//~1122R~
//        public boolean userAction()                                //~1122I~//~1126R~
//        {                                                              //~v@@@I~//~1122I~//~1126R~
//            boolean rc=true;   //no need call draw                     //~v@@@I~//~1122I~//~1126R~
//            if (Dump.Y) Dump.println("Mock_Robot.GameViewHandler.userAction");//~v@@@R~//~1122I~//~1126R~
////          AG.aGC.userAction(Pmsg);                                   //~v@@@R~//~1122I~//~1126R~
////          AG.aUserAction.action(Pmsg);                               //~v@@@R~//~1122R~//~1126R~
//            AG.aUserAction.action(null);                               //~1122I~//~1126R~
//            return rc;  //true:do not call gCanvas                     //~v@@@I~//~1122I~//~1126R~
//        }                                                              //~v@@@I~//~1122I~//~1126R~
    }                                                              //~1122I~
    class Mock_UserAction extends UserAction                       //~1122I~
    {                                                              //~1122I~
//      boolean swRobot,swKeepPrevActionID,swSendAll,swReceived;   //~1122R~//~1127R~
        boolean swKeepPrevActionID;                                //~1127I~
        int prevActionID;
        Players players;//~1122R~
        Tiles tiles; River river; Stock stock; Hands hands; Accounts accounts; //ACAction acaction;//~1123R~
//      UADelayed2 uadelayed2; UATake UAT; UADiscard UAD; UARon uaron; UAPon uapon;//~1123R~
        UAChii uachii;
        UAReach uareach;
        UAKan uakan; UAEndGame UAEG;
        //**************************************************************//~v@@@I~//~1122I~
        public Mock_UserAction()                                   //~1122I~
        {                                                          //~1122I~
            super();                                               //~1122I~
            AG.aUserAction=this;                                   //~1122I~
            init();                                                //~1123R~
        }                                                          //~1122I~
      public void init()                                             //~v@@@R~//~1123R~//~1611R~
      {                                                              //~v@@@I~//~1123R~//~1611R~
//        players=AG.aPlayers;                                       //~v@@@I~//~1123R~
//        tiles=AG.aTiles;                                           //~v@@@I~//~1123R~
//        hands=AG.aHands;                                           //~v@@@I~//~1123R~
//        river=AG.aRiver;                                           //~v@@@I~//~1123R~
//        stock=AG.aStock;                                           //~v@@@I~//~1123R~
//        accounts=AG.aAccounts;                                      //~v@@@I~//~1123R~
//        acaction=AG.aACAction;                                     //~v@@@I~//~1123R~
//        isServer=Accounts.isServer();                              //~v@@@I~//~1123R~
////      UADL=new UADelayed(this);                             //~v@@@I~//~9B17R~//~1123R~
          UADL=new Mock_UADelayed2();                                 //~9B17I~//~1123R~//~1611R~
//        UAT=new UATake(this);                                      //~v@@@I~//~1123R~
//        UAD=new UADiscard(this);                                   //~v@@@I~//~1123R~
//        UAP=new UAPon(this);                                       //~v@@@I~//~1123R~
//        UAC=new UAChii(this);                                      //~v@@@I~//~1123R~
//        UAK=new UAKan(this);                                       //~v@@7I~//~1123R~
//        UAR=new UARon(this);                                       //~v@@7I~//~1123R~
//        UARE=new UAReach(this);                                    //~v@@7I~//~1123R~
//        UAEG=new UAEndGame();                                             //~v@@@I~//~v@@7I~//~1123R~
////      new UARestart(this);                                       //~9A28R~//~1123R~
          if (Dump.Y) Dump.println("Mock_UserAction init isServer="+isServer);//~v@@@I~//~1123R~//~1611R~
      }                                                              //~v@@@I~//~1123I~//~1611R~
        @Override                                                  //~1122I~
//      public void action(Message Pmsg)                               //~v@@@I~//~1122R~
        public void action(Message Pmsg)                                       //~1122I~
        {                                                              //~v@@@I~//~1122I~
//            if (!Status.isGaming())                                    //~v@@7I~//~1122R~
//            {                                                          //~v@@7I~//~1122R~
//                return;                                                //~v@@7I~//~1122R~
//            }                                                          //~v@@7I~//~1122R~
    isServer=true;                                                 //~1123I~
    Status.setGameStatus(GS_GAME_STARTED);                         //~1123I~
            boolean rc=false;                                          //~v@@@I~//~1122I~
            msgDataToServer=""; //for also robot                                        //~v@@@I~//~v@@7I~//~1122I~
            int actionID=Pmsg.what;                                    //~v@@@I~//~1122R~//~1125R~
//  TODO    int actionID=msgActionID;                              //~1122I~//~1125R~
//            if (actionID==0)                                     //~1125R~
//            {                                                    //~1125R~
//                AG.aMJTable.getOpenRect(); to crash test         //~1125R~
//            }                                                    //~1125R~
//          resetGmsg(actionID);                                       //~9629I~//~1122R~
            int[] parm= GameViewHandler.getMsgIntData(Pmsg);            //~v@@@I~//~1122R~//~1125R~
//  TODO    int[] parm=msgIntData;                                 //~1122I~//~1125R~
//          String[] strParm=GameViewHandler.getMsgStrData(Pmsg);         //~v@@7I~//~1122R~
            String[] strParm=msgData;                              //~1122I~
            if (Dump.Y) Dump.println("Mock_UserAction.action id="+actionID+"="+GCMsgID.getEnum(actionID)+",int parm="+Arrays.toString(parm)+",strparm="+ Utils.toString(strParm));//~9B16R~//~1122I~//~1123R~
//          int eswn=msgEswn;                                          //~1123I~//~1126R~
            int eswn=parm[0];                                      //~1126I~
		    int player=AG.aAccounts.eswnToPlayer(eswn);                             //~v@@@R~//~1123I~
//          if (accounts.isDummyPlayer(player))                       //~v@@@I~//~1122R~
            if (AG.aAccounts.isDummyPlayer(player))                //~1122I~
                swReceived=true;                                       //~v@@@I~//~1122I~
            else                                                       //~v@@@I~//~1122I~
            {                                                          //~v@@@I~//~1122I~
                swReceived=false;                                      //~v@@@I~//~1122I~
//              if (!getActionInfo(actionID,player,parm,strParm))      //~9426I~//~1122I~//+1B01R~
//                  return;                                      //~v@@@I~//~1122I~//+1B01R~
                if (actionID==GCM_RON_ANYWAY)  //bypass rochk done     //~0205I~//~1122I~
                    actionID=GCM_RON;                                  //~0205I~//~1122I~
            }                                                          //~v@@@I~//~1122I~
            if (isServer)                                              //~v@@@I~//~1122I~
            {                                                          //~v@@@I~//~1122I~
                actionOnServer(actionID,player,parm/*may contain optional parm,server only*/,null);//~v@@7I~//~1122I~//~1123R~
            }                                                          //~v@@@I~//~1122I~
//          else                                                       //~v@@@I~//~1122R~
//          {                                                          //~v@@@I~//~1122R~
//              sendToServer(true/*PswRequest*/,actionID,player);//~v@@7I~//~1122R~
//          }                                                          //~v@@@I~//~1122R~
//          if (delayedGMsgTxt!=null)                                  //~9627M~//~1122I~
//          {                                                          //~9627M~//~1122I~
//              showDelayedGMsg();                                     //~9627M~//~1122I~
//          }                                                          //~9627M~//~1122I~
        }                                                              //~v@@@I~//~1122I~
        //*************************************************************************//~v@@@I~//~1122I~
        //*player:if received,pos of triger player                     //~v@@@R~//~1122I~
        //*       else PLAYER_YOU by touch                             //~v@@@R~//~1122I~
        //*************************************************************************//~v@@@I~//~1122I~
        private void actionOnServer(int PactionID,int Pplayer/*player relative pos on server*/,int[] PintParm,String[] PstrParm)//~v@@@R~//~v@@7R~//~1122I~
        {                                                              //~v@@@I~//~1122I~
            if (Dump.Y) Dump.println("Mock_Robot.Mock_UserAction.actionOnServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer);//~v@@@R~//~1122I~//~1123R~
//          msgDataToClient="";                                                //~v@@@I~//~1122I~
//          swSendAll=false;                                           //~v@@@R~//~1122I~
            boolean rc=action(true/*swServer*/,PactionID,Pplayer,PintParm,PstrParm);//~v@@@R~//~v@@7R~//~1122I~
//          if (rc)                                                    //~v@@@I~//~1122I~
//          {                                                          //~v@@@I~//~1122I~
//              sendToClient(PactionID,Pplayer);    //send actionID,player with msgDataToClient                       //~v@@@I~//~v@@7R~//~1122I~
//            if (!swKeepPrevActionID)                                      //~v@@@R~//~v@@7R~//~1122I~
//                prevActionID=PactionID;                              //~v@@@R~//~1122I~
//          }                                                          //~v@@@I~//~1122I~
        }                                                              //~v@@@I~//~1122I~
        public void actionMock(int PactionID,int Pplayer/*player relative pos on server*/,String PstrParm)//~1123I~
        {                                                          //~1123I~
            int[] intParm=UserAction.parseMsgDataToClient(PstrParm);//~1123I~
            String[] strParm=new String[]{PstrParm};                 //~1123I~
            if (Dump.Y) Dump.println("Mock_Robot.Mock_UserAction.actionOnServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer);//~1123R~
            boolean rc=action(true/*swServer*/,PactionID,Pplayer,intParm,strParm);//~1123I~
        }                                                          //~1123I~
        //*************************************************************************//~v@@@I~//~1122I~
        //*on server(set data and draw) and client(draw)               //~v@@@I~//~1122I~
        //*rc:true:send to client when server                          //~9B20I~//~1122I~
        //*************************************************************************//~v@@@I~//~1122I~
        public boolean action(boolean PswServer,int PactionID,int Pplayer/*relative pos on the device*/,int[] PintParm,String[] PstrParm)//~v@@@R~//~v@@7R~//~1122I~
        {                                                              //~v@@@I~//~1122I~
            if (Dump.Y) Dump.println("Mock_UserAction.action actionID="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer);//~v@@@R~//~1122I~//~1123R~
            if (Dump.Y) Dump.println("Mock_UserAction.action intParm="+ Arrays.toString(PintParm));//~v@@7I~//~1122I~//~1123R~
            if (Dump.Y) Dump.println("Mock_UserAction.action strParm="+Arrays.toString(PstrParm));//~9826I~//~1122I~//~1123R~
            if (PactionID==0)                                      //~1125I~
            {                                                      //~1125I~
	            if (Dump.Y) Dump.println("Mock_UserAction.action strParm="+Arrays.toString(PstrParm));//~1125I~
            }                                                      //~1125I~
    isServer=true;                                                 //~1123I~
    swReceived=true;                                               //~1123I~
    Status.setGameStatus(GS_GAME_STARTED);                         //~1123I~
//          if (!players.isYourTurn(PactionID,Pplayer,prevActionID))   //~v@@7I~//~1122R~
            if (!AG.aPlayers.isYourTurn(PactionID,Pplayer,prevActionID))//~1122I~
            {                                                          //~v@@7I~//~1122I~
                return false;                                          //~v@@7I~//~1122I~
            }                                                          //~v@@7I~//~1122I~
		    Mock_UserAction_lastAction=PactionID;                  //~1125I~
            boolean rc=true;                                            //~v@@@R~//~1122I~
            swSendAll=true;                                      //~v@@@R~//~1122I~
            swRobot=true;                                              //~v@@@R~//~1122I~
            swKeepPrevActionID=false;                                       //~v@@@I~//~v@@7R~//~1122I~
            switch(PactionID)                                          //~v@@@R~//~1122I~
            {                                                          //~v@@@I~//~1122I~
            case GCM_TAKE:                                             //~v@@@I~//~1122I~
                currentActionID=PactionID;                             //~9623I~//~1122I~
                rc=UAT.takeOne(PswServer,swReceived,Pplayer,PintParm); //~v@@@R~//~1122I~
                swRobot=false;                                         //~0229I~//~1122I~
                break;                                                 //~v@@@I~//~1122I~
            case GCM_DISCARD:                                          //~v@@@M~//~1122I~
                currentActionID=PactionID;                             //~9623I~//~1122I~
                swRobot=false;                                         //~v@@@I~//~1122I~
                rc=UAD.discard(PswServer,swReceived,Pplayer,PintParm); //~v@@@R~//~1122I~
            	if (Dump.Y) Dump.println("Mock_UserAction.action Discard return reset sleep");//~1125R~
                setNextLine(false);                                //~1125I~
                break;                                                 //~v@@@M~//~1122I~
            case GCM_NEXT_PLAYER:   //client only                      //~v@@@R~//~1122I~
                swKeepPrevActionID=true;    //not set to PrevActionID          //~v@@@I~//~v@@7R~//~1122I~
                swRobot=false;                                         //~0229I~//~1122I~
                rc=UAD.nextPlayer(PswServer,Pplayer);   //UADiscard    //~v@@@R~//~v@@7R~//~1122I~
                break;                                                 //~v@@@I~//~1122I~
            case GCM_NEXT_PLAYER_PONKAN:    //client only              //~v@@7I~//~1122I~
                swKeepPrevActionID=true;    //not set to PrevActionID  //~v@@7I~//~1122I~
                swRobot=false;                                         //~0229I~//~1122I~
                rc=UAD.nextPlayerPonKan(PswServer,Pplayer);   //UADiscard//~v@@7R~//~1122I~
                break;                                                 //~v@@7I~//~1122I~
            case GCM_PON:                                              //~v@@@I~//~1122I~
    	        swRobot=false;                                     //~1127I~
                currentActionID=PactionID;                             //~9623I~//~1122I~
                rc=UAP.takePon(PswServer,swReceived,Pplayer,PintParm);//~v@@@R~//~1122I~
                break;                                                 //~v@@@I~//~1122I~
            case GCM_CHII:                                             //~v@@@I~//~1122I~
    	        swRobot=false;                                     //~1127I~
                currentActionID=PactionID;                             //~9623I~//~1122I~
                rc=UAC.takeChii(PswServer,swReceived,Pplayer,PintParm);//~v@@@I~//~1122I~
                break;                                                 //~v@@@I~//~1122I~
            case GCM_KAN:                                              //~v@@@I~//~1122I~
    	        swRobot=false;                                     //~1127I~
                currentActionID=PactionID;                             //~9623I~//~1122I~
                rc=UAK.takeKan(PswServer,swReceived,Pplayer,PintParm); //~v@@7I~//~1122I~
                break;                                                 //~v@@@I~//~1122I~
            case GCM_TIMEOUT_TO_TAKABLE_RINSHAN:                       //~0403I~//~1122I~
                swKeepPrevActionID=true;    //not set to PrevActionID  //~0403I~//~1122I~
                swRobot=false;                                         //~0403I~//~1122I~
                rc=UAK.clientTakableRinshan(Pplayer,PintParm);         //~0403I~//~1122I~
                break;                                                 //~0403I~//~1122I~
            case GCM_REACH:                                            //~v@@@I~//~1122I~
            case GCM_FORCE_REACH:                                      //~va27I~//~1122I~
                swRobot=false;                                         //~v@@7I~//~1122I~
                rc=UARE.reach(PswServer,swReceived,Pplayer,PintParm);   //~v@@7I~//~1122I~
                swKeepPrevActionID=true;    //not set to PrevActionID  //~v@@7I~//~1122I~
                break;                                                 //~v@@@I~//~1122I~
            case GCM_REACH_OPEN:                                       //~v@@7I~//~1122I~
            case GCM_FORCE_REACH_OPEN:                                 //~va27I~//~1122I~
                swRobot=false;                                         //~v@@7I~//~1122I~
                rc=UARE.reachOpen(PswServer,swReceived,Pplayer,PintParm);//~v@@7I~//~1122I~
                swKeepPrevActionID=true;    //not set to PrevActionID  //~v@@7I~//~1122I~
                break;                                                 //~v@@7I~//~1122I~
            case GCM_REACH_RESET:                                      //~9A30I~//~1122I~
                swRobot=false;                                         //~9A30I~//~1122I~
                rc=UARE.reachReset(PswServer,swReceived,Pplayer,PintParm);//~9A30I~//~1122I~
                swKeepPrevActionID=true;    //not set to PrevActionID  //~9A30I~//~1122I~
                break;                                                 //~9A30I~//~1122I~
            case GCM_REACH_OPEN_RESET:                                 //~9A30I~//~1122I~
                swRobot=false;                                         //~9A30I~//~1122I~
                rc=UARE.reachOpenReset(PswServer,swReceived,Pplayer,PintParm);//~9A30I~//~1122I~
                swKeepPrevActionID=true;    //not set to PrevActionID  //~9A30I~//~1122I~
                break;                                                 //~9A30I~//~1122I~
            case GCM_RON:                                              //~v@@@I~//~1122I~
                currentActionID=PactionID;                             //~9623I~//~1122I~
                swRobot=false;                                         //~v@@7R~//~1122I~
                rc=UAR.complete(PswServer,swReceived,Pplayer,PintParm);//~v@@7R~//~1122I~
                //TODO                                                 //~v@@@I~//~1122I~
                break;                                                 //~v@@@I~//~1122I~
            case GCM_OPEN:                                             //~v@@@I~//~1122I~
                swRobot=false;                                         //~v@@7I~//~1122I~
                rc=UARE.open(PswServer,swReceived,Pplayer,PintParm);    //UAReach//~v@@7R~//~1122I~
                swKeepPrevActionID=true;    //not set to PrevActionID  //~v@@7I~//~1122I~
                break;                                                 //~v@@@I~//~1122I~
//            case GCM_ENDGAME_DRAWN:                                    //~v@@7I~//~1122R~
//                swRobot=false;                                         //~v@@7I~//~1122R~
//                rc=UAEG.drawn(PswServer,swReceived,Pplayer,PintParm,PstrParm);//~v@@7R~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~v@@7I~//~1122R~
//                break;                                                 //~v@@7I~//~1122R~
//            case GCM_ENDGAME_SCORE:                                    //~v@@7I~//~1122R~
//                swRobot=false;                                         //~v@@7I~//~1122R~
//                setNoMsgToServer(); //for sendToServer()               //~v@@7I~//~1122R~
//                ScoreDlg.onReceived(PswServer,swReceived,Pplayer,PintParm);//~v@@7R~//~1122R~
//                rc=false;   //no resp msg send                         //~v@@7I~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~v@@7I~//~1122R~
//                break;                                                 //~v@@7I~//~1122R~
//            case GCM_ENDGAME_ACCOUNTS:                                 //~9322I~//~1122R~
//                swRobot=false;                                         //~9322I~//~1122R~
//                setNoMsgToServer(); //for sendToServer()               //~9322I~//~1122R~
//                AccountsDlg.onReceived(PswServer,swReceived,Pplayer,PintParm);//~9322I~//~1122R~
//                rc=false;   //no resp msg send                         //~9322I~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~9322I~//~1122R~
//                break;                                                 //~9322I~//~1122R~
//            case GCM_SUSPENDDLG:                                       //~9822R~//~1122R~
//                swRobot=false;                                         //~9822I~//~1122R~
//                setNoMsgToServer(); //for sendToServer()               //~9822I~//~1122R~
//                SuspendDlg.onReceived(PswServer,swReceived,Pplayer,PintParm);//~9822I~//~9826R~//~1122R~
//                rc=false;   //no resp msg send                         //~9822I~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~9822I~//~1122R~
//                break;                                                 //~9822I~//~1122R~
//            case GCM_SUSPENDDLG_IOERR:                                 //~9A21I~//~1122R~
//                swRobot=false;                                         //~9A21I~//~1122R~
//                setNoMsgToServer(); //for sendToServer()               //~9A21I~//~1122R~
//                SuspendIOErrDlg.onReceived(PswServer,swReceived,Pplayer,PintParm);//~9A21I~//~1122R~
//                rc=false;   //no resp msg send                         //~9A21I~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~9A21I~//~1122R~
//                break;                                                 //~9A21I~//~1122R~
//            case GCM_WAITON:                                           //~v@@7I~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~v@@7I~//~1122R~
//                rc=actionWait(GCM_WAITON,PswServer,swReceived,Pplayer,PintParm);//~v@@7R~//~1122R~
//                break;                                                 //~v@@7I~//~1122R~
//            case GCM_WAITOFF:                                          //~v@@7I~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~v@@7I~//~1122R~
//                rc=actionWait(GCM_WAITOFF,PswServer,swReceived,Pplayer,PintParm);//~v@@7R~//~1122R~
//                break;                                                 //~v@@7I~//~1122R~
//            case GCM_WAIT_RELEASE_ACTION:                          //~v@11I~//~9627I~//~1122R~
//                swSendAll=false;                                       //~9627I~//~1122R~
//                swRobot=false;                                         //~9627I~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~9627I~//~1122R~
//                setNoMsgToServer(); //for sendToServer()               //~9627I~//~1122R~
//                AG.aUADelayed.actionReleaseWait(Pplayer,PintParm);             //~v@11I~//~9627R~//~1122R~
//                rc=false;   //no resp msg send                         //~9627I~//~1122R~
//                break;                                             //~v@11I~//~9627I~//~1122R~
//            case GCM_TIMEOUT_STOPAUTO:                                 //~9701I~//~1122R~
//                swSendAll=false;                                       //~9701I~//~1122R~
//                swRobot=false;                                         //~9701I~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~9701I~//~1122R~
//                setNoMsgToServer(); //for sendToServer()               //~9701I~//~1122R~
//                AG.aUADelayed.stopAuto(PintParm);                       //~9701R~//~1122R~
//                rc=false;   //no resp msg send                         //~9701I~//~1122R~
//                break;                                                 //~9701I~//~1122R~
//            case GCM_2TOUCH:                                           //~9B18I~//~1122R~
//                swRobot=false;                                         //~9B24I~//~1122R~
//                swKeepPrevActionID=true;    //not set to PrevActionID  //~9B18I~//~1122R~
//                rc=UADL.action2Touch(PswServer,swReceived,Pplayer,PintParm);//~9B18I~//~1122R~
//                break;                                                 //~9B18I~//~1122R~
            }                                                          //~v@@@I~//~1122I~
            if (rc)                                                  //~v@@@R~//~v@@7R~//~1122I~
            {                                                        //~v@@@R~//~v@@7R~//~1122I~
                if (!swKeepPrevActionID)                                  //~v@@@R~//~v@@7R~//~1122I~
                    prevActionID=PactionID;                          //~v@@@R~//~v@@7R~//~1122I~
            }                                                        //~v@@@R~//~v@@7R~//~1122I~
            Mock_UserAction_prevActionID=prevActionID;             //~1126I~
            if (Dump.Y) Dump.println("Mock_UserAction.action rc="+rc+",prevAction="+prevActionID+",swKeepPrevActionID="+swKeepPrevActionID);      //~v@@@I~//~0229R~//~1122I~//~1123R~
            return rc;                                                 //~v@@@I~//~1122I~
        }                                                              //~v@@@I~//~1122I~
    }                                                              //~1112I~
//***********************************************                  //~1121I~
	class Mock_PointStick extends PointStick                       //~1121I~
    {                                                              //~1121I~
    	@Override                                                  //~1121I~
        public void reachDone(int Pplayer)                            //~v@@@I~//~1121I~
        {                                                          //~1121I~
            if (Dump.Y) Dump.println("Mock_PointStic reachDone");  //~1121I~
        }                                                          //~1121I~
    	public void complete(int Pplayer)                                   //~v@@@I~//~1131I~
        {                                                          //~1131I~
            if (Dump.Y) Dump.println("Mock_PointStic complete");   //~1131I~
        }                                                          //~1131I~
    }                                                              //~1121I~
//***********************************************                  //~1123I~
	class Mock_River extends River                                 //~1123I~
    {                                                              //~1123I~
    	@Override                                                  //~1123I~
        public void drawDiscarded()                     //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_River drawDiscarded");  //~1123I~
        }                                                          //~1123I~
    	@Override                                                  //~1123I~
        public void drawDiscarded(int Pplayer,TileData Ptd)        //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_River drawDiscarded");  //~1123I~
        }                                                          //~1123I~
    	@Override                                                  //~1125I~
		public void reach(int Pplayer)                          //~v@@@R~//~1125I~
        {                                                          //~1125I~
            if (Dump.Y) Dump.println("Mock_River reach");          //~1125I~
        }                                                          //~1125I~
    	public  void  complete(int Pplayer,int Pflag)                  //~v@@@I~//~1131I~
        {                                                          //~1131I~
            if (Dump.Y) Dump.println("Mock_River complete");       //~1131I~
        }                                                          //~1131I~
    }                                                              //~1123I~
//***********************************************                  //~1123I~
	class Mock_Hands extends Hands                                   //~1123I~
    {                                                              //~1123I~
    	public Mock_Hands()                                         //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_Hand constructor");     //~1124I~
	        AG.aHands=this;                                        //~1124R~
	        AG.aHandsTouch=new Mock_HandsTouch();                  //~1124I~
        }                                                          //~1123I~
    	@Override                                                  //~1123I~
        public void discard(int Pplayer, TileData Ptd)              //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_Hand   discarded");     //~1123I~
        }                                                          //~1123I~
    	@Override                                                  //~1123I~
	    public  void   takeOne(int Pplayer,TileData Ptd)               //~v@@@R~//~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_Hand   takeOne");       //~1123I~
        }                                                          //~1123I~
    	@Override                                                  //~1124I~
	    public  void   takeKan(int Pplayer,int PkanType)           //~1124I~
        {                                                          //~1124I~
            if (Dump.Y) Dump.println("Mock_Hand   takeOne");       //~1124I~
        }                                                          //~1124I~
	    public void drawHands(TileData[] Ptds)                         //~v@@@R~//~1124I~
        {                                                          //~1124I~
            if (Dump.Y) Dump.println("Mock_Hand   drawHands");     //~1124I~
        }                                                          //~1124I~
    	public  void  takePon(int Pplayer)                             //~v@@@I~//~1126I~
        {                                                          //~1126I~
            if (Dump.Y) Dump.println("Mock_Hand   takePon");       //~1126I~
        }                                                          //~1126I~
    	public  void  complete(int Pplayer,int Pflag)                  //~v@@@I~//~1131I~
        {                                                          //~1131I~
            if (Dump.Y) Dump.println("Mock_Hand   complete");      //~1131I~
        }                                                          //~1131I~
    }                                                              //~1123I~
//***********************************************                  //~1123I~
	class Mock_Stock extends Stock                                 //~1123I~
    {                                                              //~1123I~
    	@Override                                                  //~1123I~
        public void discard(int Pplayer)                           //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_Stock discarded");      //~1123I~
        }                                                          //~1123I~
    	@Override                                                  //~1123I~
        public void takeOne()                                      //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_Stock takeOne");        //~1123I~
        }                                                          //~1123I~
	    public void drawNextOneKan()                                   //~v@11I~//~1124I~
        {                                                          //~1124I~
            if (Dump.Y) Dump.println("Mock_Stock drawNextOneKan"); //~1124I~
        }                                                          //~1124I~
	    public void takeOneKan()                                       //~v@@@I~//~1125I~
        {                                                          //~1125I~
            if (Dump.Y) Dump.println("Mock_Stock.takeOneKan");     //~1125I~
        }                                                          //~1125I~
	    public void takeKan(int Pplayer,int Pkantype)                  //~v@@@I~//~1125I~
        {                                                          //~1125I~
            if (Dump.Y) Dump.println("Mock_Stock.takeKan");        //~1125I~
        }                                                          //~1125I~
	    public void drawDoraComplete()                                 //~9214I~//~1131I~
        {                                                          //~1131I~
            if (Dump.Y) Dump.println("Mock_Stock.drawDoraComplete");//~1131I~
        }                                                          //~1131I~
    }                                                              //~1123I~
//***********************************************                  //~1123I~
	class Mock_UADelayed2 extends UADelayed2                       //~1123I~
    {                                                              //~1123I~
		public Mock_UADelayed2()                                   //~1123I~
        {                                                          //~1123I~
        	AG.aUADelayed=this;                                    //~1123I~
        }                                                          //~1123I~
    	//@Override                                                  //~1123I~
        public void postDelayedPonKan(int Pplayer,TileData Ptd)    //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_Delayed2 postDelayedPonKan");//~1123I~
        }                                                          //~1123I~
    	//@Override                                                  //~1123I~
    	public int isYourTune(int PactionID,int Pplayer)               //~9C06R~//~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_Delayed2 isYourTurn");  //~1123I~
            return 0;                                              //~1123I~
        }                                                          //~1123I~
        public void postDelayedActionMsgIM(int Ptime/*milisec*/,int PactionID,int Pparm1,int Pparm2,int Pparm3)//~v@@@R~//~9624R~//~1123I~
        {                                                          //~1123I~
                if (Dump.Y) Dump.println("Mock_UADelayed.postDelayedActionMsgIM strp return by swStop");//~1123I~
        }                                                          //~1123I~
        public boolean chkSelectInfo2TouchRobot(int Pplayer,int[] PintParm)//~va60I~//~1611I~
        {                                                              //~va60I~//~1611I~
            if (Dump.Y) Dump.println("Mock_UADelayed2.chkSelectInfo2TouchRobot rc="+true);//~1611I~//~1612R~
            return true;                                          //~1611I~//~1612R~
        }                                                              //~va60I~//~1611I~
    }                                                              //~1123I~
//***********************************************                  //~1123I~
	class Mock_Sound  extends Sound                                             //~1123R~
    {                                                              //~1123I~
        public Mock_Sound()                           //~9C01I~//~1123R~
        {                                                              //~9C01I~//~1123I~
            if (Dump.Y) Dump.println("Mock_Sound constructor");    //~1123I~
        }                                                          //~1123I~
        @Override                                                  //~1123I~
        public void init()                                              //~1123I~
        {                                                          //~1123I~
        }                                                          //~1123I~
        @Override                                                  //~1123I~
        public void playSPL (int Psoundid,boolean Pbeep)           //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_Sound.playSPL");                              //~1123I~
        }                                                          //~1123I~
    }                                                              //~1123I~
////***********************************************                //~1123R~
//    class Mock_SPList extends SPList                             //~1123R~
//    {                                                            //~1123R~
//    private Sound SOUND;                                                      //~1327I~//~9C03R~//~1123R~

//    //******************************************************************//~9C03I~//~1123R~
//    public Mock_SPList(Sound Psound)                                   //~9C03I~//~1123R~
//    {                                                              //~9C03I~//~1123R~
//        if (Dump.Y) Dump.println("Mock_SPList.constructor");            //~9C03I~//~1123R~
//        SOUND = Psound;                                              //~9C03I~//~1123R~
//        init();                                                    //~9C03I~//~1123R~
//    }                                                              //~9C03I~//~1123R~

//    public void init()                                             //~9C03R~//~1123R~
//    {                                                              //~1327R~//~1123R~
//        if (Dump.Y) Dump.println("Mock_SPList.init");            //~1123R~
//    }                                                            //~1123R~

//    public synchronized void play(int Psoundid, boolean Pbeep)      //~9C03I~//~1123R~
//    {                                                              //~9C02R~//~1123R~
//        if (Dump.Y) Dump.println("Mock_SPList.play");            //~1123R~
//    }                                                            //~1123R~
//    }                                                            //~1123R~
    //******************************************************************//~1123I~
    class Mock_GMsg extends GMsg                                   //~1123I~
    {                                                              //~1123I~
        public Mock_GMsg()                                         //~1123I~
        {                                                          //~1123I~
        	msgbarSize=40;                                         //~1617I~
        	msgbarLen=800;                                         //~1617I~
            if (Dump.Y) Dump.println("Mock_GMsg.default constructor");//~1123I~
        }                                                          //~1123I~
        //*********************************************************    //~v@@@I~//~1123I~
        public void errorMsg(int Popt,String Ptext)                    //~v@@@I~//~1123I~
        {                                                              //~v@@@I~//~1123I~
            if (Dump.Y) Dump.println("Mock_GMsg.errorMsg reset sleep");//~1125I~
            setNextLine(false);                                    //~1125I~
        }                                                              //~v@@@I~//~1123I~
        //*********************************************************//~1617I~
        @Override                                                  //~1617I~
        public void drawMsgbarVertical(String Ptext)               //~1617R~
        {                                                          //~1617I~
            if (Dump.Y) Dump.println("Mock_GMsg.drawMsgBar msg="+Ptext);//~1617I~
        }                                                          //~1617I~
    }                                                              //~1123I~
    //******************************************************************//~1123I~
    class Mock_HandsTouch extends HandsTouch                       //~1123I~
    {                                                              //~1123I~
        public Mock_HandsTouch()                                   //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_GMsg.default constructor");//~1123I~
        }                                                          //~1123I~
		//*********************************************************    //~v@@@I~//~1123I~
    	public void enableMultiSelectionMode(boolean Penable)          //~v@21I~//~1123I~
    	{                                                              //~v@21I~//~1123I~
        	if (Dump.Y) Dump.println("Mock_HandsTouch.enableMultiSelectionMode enable="+Penable);//~v@21R~//~1123I~
        }                                                          //~1123I~
    }                                                              //~1123I~
    //******************************************************************//~1123I~
    class Mock_Tiles extends Tiles                                 //~1123I~
    {                                                              //~1123I~
        public Mock_Tiles()                                        //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_Tiles.default constructor");//~1123I~
        }                                                          //~1123I~
        public TileData getNext()                                      //~v@@@I~//~1123I~
        {                                                              //~v@@@I~//~1123I~
            if (Dump.Y) Dump.println("Mock_Tiles.getNext");        //~1127I~
			TileData td=getNextTile();                                //~1127I~
            return td;                                             //~1127I~
        }                                                              //~v@@@I~//~1123I~
    //******************************************************************//~1125I~
    //*give rinshan fromm test data                                //~1125I~
    //******************************************************************//~1125I~
        public TileData getNextKan()                                   //~v@@@I~//~1125I~
        {                                                              //~v@@@I~//~1125I~
            if (Dump.Y) Dump.println("Mock_Tiles.getNextKan");     //~1127R~
			TileData td=getNextTile();                                //~1127I~
            return td;                                                 //~v@@@I~//~1125I~
        }                                                              //~v@@@I~//~1125I~
        private TileData getNextTile()                             //~1127I~
        {                                                          //~1127I~
            int cp=AG.aPlayers.getCurrentPlayer();                //~1127I~
            int player=ITRADS.itsAutoTakePlayer[ITRADS.ctrAutoTakeRead];//~1127I~
            if (Dump.Y) Dump.println("Mock_Tiles.getNextTile currentPlayer="+cp+",player="+player+",ctrSaved="+ITRADS.ctrAutoTake);//~1127I~
//          if (player!=cp)                                        //~1127I~
//              AG.aPlayers.setCurrentPlayerWithoutLight(player);  //~1127I~
            TileData td = ITRADS.tdAutoTake[ITRADS.ctrAutoTakeRead++];//~1127I~
            if (Dump.Y) Dump.println("Mock_Tiles.getNextTile read="+ITRADS.ctrAutoTakeRead+",td="+TileData.toString(td));//~1127R~
            return td;                                             //~1127I~
        }                                                          //~1127I~
    }                                                              //~1123I~
    //******************************************************************//~1123I~
    class Mock_GC extends GC                                       //~1123I~
    {                                                              //~1123I~
        Mock_GameView gv;
        public Mock_GC()                                           //~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_GC.default constructor");//~1123I~
            AG.aGC=this;                                           //~1123I~
            gv=new Mock_GameView(AG.context);	                   //~1123R~
       }                                                          //~1123I~
	   public void highlightCompReq(boolean PswOn)                    //~va49I~//~1201I~
       {                                                           //~1201I~
            if (Dump.Y) Dump.println("Mock_GC.highlightCompReq");  //~1201I~
       }                                                           //~1201I~
    }                                                              //~1123I~
    //******************************************************************//~1131I~
    class Mock_NamePlate extends NamePlate                         //~1131I~
    {                                                              //~1131I~
        public Mock_NamePlate()                                    //~1131I~
        {                                                          //~1131I~
            if (Dump.Y) Dump.println("Mock_NamePlate.default constructor");//~1131I~
        }                                                          //~1131I~
	    public void complete(boolean PswReset,int Pplayer)             //~0303I~//~1131I~
        {                                                          //~1131I~
            if (Dump.Y) Dump.println("Mock_NamePlate complete");   //~1131I~
        }                                                          //~1131I~
	    public void showScore()                            //~9317I~   //~9318R~//~1131I~
        {                                                          //~1131I~
            if (Dump.Y) Dump.println("Mock_NamePlate showScore");  //~1131I~
        }                                                          //~1131I~
    }                                                              //~1131I~
    //******************************************************************//~1123I~
    class Mock_GameView extends GameView                           //~1123I~
    {                                                              //~1123I~
        GameViewHandler gvHandler;
        public Mock_GameView(Context Pcontext)                                     //~1123R~
        {
            super(Pcontext);//~1123I~
            if (Dump.Y) Dump.println("Mock_GameView.default constructor");//~1123I~
        }
        @Override//~1123I~
        public void init(Context Pcontext)                            //~v@@@R~//~1123I~
        {                                                          //~1123I~
            AG.aGameView=this;                                         //~v@@@R~//~1123I~
            if (Dump.Y) Dump.println("Mock_GameView.init");//~v@@@I~//~v@21R~//~1123I~
            try                                                    //~1123I~
            {                                                      //~1123I~
                setLayerType(LAYER_TYPE_SOFTWARE,null);//TODO TEST,For delayed draw edaler's 1st take//~v@21R~//~1123I~
                if (Dump.Y) Dump.println("GameView.init after setLayer HWAccelerator="+isHardwareAccelerated());//~v@21I~//~1123I~
 //             UView.setWillNotDraw(this,false);  //enable onDraw() callback     //~v@@@R~//~v@21R~//~1123I~
                WW = AG.scrWidthReal;                                  //~v@@@R~//~1123I~
                if (Dump.Y) Dump.println("GameView.init swPortrait="+AG.aGC.swPortrait+",scrWidthReal="+AG.scrWidthReal+",scrNavigationbarRightWidth="+AG.scrNavigationbarRightWidth+",scrWidth="+AG.scrWidth);//~v@21I~//~1123I~
                if (!AG.aGC.swPortrait) //landscape                    //~v@@@I~//~1123I~
                {                                                      //~v@@@I~//~1123I~
                    HH = AG.scrHeightReal;                             //~v@@@R~//~1123I~
                    WW-=AG.scrNavigationbarRightWidth;  //some device,navigationbar is onth right when landscape and could not hide//~v@21R~//~1123I~
                }                                                      //~v@@@I~//~1123I~
                else                                                   //~v@@@I~//~1123I~
                {                                                      //~v@@@I~//~1123I~
                    HH = AG.scrHeight;                                 //~v@@@I~//~1123I~
                }                                                      //~v@@@I~//~1123I~
//              table=new MJTable(WW,HH);                                //~v@@@R~//~1123I~
                prepareHandler();                                      //~v@21I~//~1123I~
                                                                       //~v@21I~//~1123I~
            }                                                      //~1123I~
            catch (Exception e)                                    //~1123I~
            {                                                      //~1123I~
                Dump.println(e, "Mock_GameView.init");             //~1123I~
            }                                                      //~1123I~
        }                                                          //~1123I~
        //*************************                                //~1123I~
        private void prepareHandler()                                  //~v@21R~//~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_GameView.prepareHandler");       //~v@@@R~//~1123I~
            HandlerThread handlerThread=new HandlerThread(Utils.getClassName(this));//~v@21I~//~1123I~
            handlerThread.start();        //do run()               //~1123I~
 //         gvHandler = GameViewHandler.newInstance(this,handlerThread);//~v@21I~//~1123R~
            gvHandler = newInstance(this,handlerThread);           //~1123I~
            gvHandler.onResume();   //open msgq                        //~v@@@R~//~1123R~
        }                                                          //~1123I~
        public  GameViewHandler newInstance(GameView Pgameview, HandlerThread Pthread)//~v@11R~//~1123I~
        {                                                              //~v@11I~//~1123I~
            if (Dump.Y) Dump.println("Mock_GameView.newInstance gameView="+Pgameview.toString()+",thread="+Pthread.toString());//~v@11I~//~1123R~
 //           resetHandler();                                            //~v@11I~//~1123I~
            Looper looper=Pthread.getLooper();                         //~v@11I~//~1123I~
            AG.aHandlerThread=Pthread;                                 //~v@11I~//~1123I~
//          GameViewHandler gvh=new GameViewHandler(Pgameview,looper); //~v@11I~//~1123R~
            GameViewHandler gvh=new Mock_GameViewHandler(looper,this);//~1123R~
            return gvh;                                            //~1123I~
        }                                                              //~v@11I~//~1123I~
	    public void paint()                                            //~v@21M~//~1123I~
        {                                                          //~1123I~
            if (Dump.Y) Dump.println("Mock_GameView.paint");
        }                                                          //~1123R~
    }                                                              //~1123I~
    //******************************************************************//~1123I~
    class Mock_GameViewHandler extends GameViewHandler             //~1123I~
    {                                                              //~1123I~
        public Mock_GameViewHandler(Looper Plooper,GameView Pgv)   //~1123R~
        {                                                          //~1123I~
            super(Plooper,Pgv);                                    //~1123R~
            if (Dump.Y) Dump.println("Mock_GameViewHandler Constructor");//~1123I~
            if (Dump.Y) Dump.println("Mock_GameViewHandler Constructor AG.aGAmeViewHandler="+Utils.toString(AG.aGameViewHandler));//~1123I~
        }                                                          //~1123I~
    }                                                              //~1123I~
}
