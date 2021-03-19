//*CID://+va66R~:                                   update#=  261; //~va66R~
//*************************************************************************//~@002R~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/01/07 va60 CalcShanten                                      //~va60I~
//*************************************************************************//~va60I~
//*Dummy client msg processing                                     //~@002R~
//*************************************************************************//~@002I~
package com.btmtest.game;                                            //~1AecR~//~@002R~
                                                                   //~1AecI~
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.utils.Dump;                                     //~1AecR~
import com.btmtest.utils.Utils;
//~@002I~
import java.util.Arrays;

import static com.btmtest.TestOption.*;
import static com.btmtest.game.GCMsgID.*;                          //~@002I~
import static com.btmtest.game.GConst.*;
import static com.btmtest.StaticVars.AG;
import static com.btmtest.BT.enums.MsgIDConst.*;
//**************************************************************   //~1AecI~
//*Action is always on Server                                      //~@002I~
//**************************************************************   //~@002I~
//public class Robot implements URunnable.URunnableI               //~@002R~
public class Robot                                                 //~@002I~
{                                                                  //~1AecR~
                                                                   //~@002I~
//  private static final int delayRobot=200; //miliseconds,after this sendmsg to after process//~@002R~//~0228R~//~0229R~
    private static final int delayRobot=0; //miliseconds,after this sendmsg to after process//~0229I~
//  private static final int delayRobot=5200;//TODO test //miliseconds,after this sendmsg to after process//~0228R~
//  private String remoteDeviceName;                               //~@002R~
//  private String localDeviceName;                                //~1AecI~//~@002R~
//  private Accounts.Account account;                                                               //~1AecI~//~@002R~//~va60R~
    protected Accounts.Account account;	//protected for IT_Mock    //~va60I~
    private Accounts accounts;                                     //~@002I~
    private TileData tdTaken;                                      //~@002I~
    private TileData tdSelectedDiscard;                            //~va66I~
    private int ctrSelectedDiscardTaken=-1;                        //~va66I~
//    private int delay;                                           //~@002R~
    private int initialEswn;                                       //~@002I~
    private int yourEswn=-1;                                       //~@002I~
    private int currentEswn=-1;                                    //~@002I~
//	private int	timeoutAutoTake;                                   //~9630I~//~9702R~
//*********************************************************************//~@002I~
    public static Robot createNewInstance(Accounts.Account Paccount)//~@002R~
    {                                                              //~1AecI~//~@002M~
        if (Dump.Y) Dump.println("Robot.newInstance");             //~@002R~
        Robot r= new Robot();                                      //~@002I~
    	r.account=Paccount;                                        //~@002I~
        r.init();                                                  //~@002I~
        return r;                                                  //~@002I~
    }                                                              //~1AecI~//~@002M~
//*********************************************************************//~@002I~
    public Robot()                                                 //~@002I~
    {                                                              //~@002I~
        if (Dump.Y) Dump.println("Robot Constructor");             //~@002I~
    }                                                              //~@002I~
//*********************************************************************//~@002I~
    public void init()                                             //~@002I~
    {                                                              //~@002I~
        if (Dump.Y) Dump.println("Robot.init");                    //~@002R~
        accounts=AG.aAccounts;	                                   //~@002I~
//      localDeviceName="Robot"+account.name;                      //~@002R~
//      remoteDeviceName=serverData.getName();                     //~@002R~
	//	delay=OperationSetting.delayRobot;                         //~@002I~
        initialEswn=account.idxMembers;                             //~@002I~
//		timeoutAutoTake=RuleSettingOperation.getTimeoutTake(); //~9624I~//~9630I~//~9702R~
    }                                                              //~@002I~
	//**************************************************************//~@002I~
	//*for robot                                                   //~@002I~
	//**************************************************************//~@002I~
	public int getCurrentEswnRobot()                               //~@002R~
    {                                                              //~@002I~
//      int eswn=accounts.getCurrentEswnOfAccount(account);        //~@002R~
        int eswn=accounts.idxToCurrentEswn(account.idxMembers);    //~@002I~
        if (Dump.Y) Dump.println("Robot.getCurrentEswnRobot="+eswn);//~@002R~
        return eswn;                                               //~@002I~
    }                                                              //~@002I~
	//**************************************************************//~@002I~
	//*thru sendToClient on Server                                  //~@002R~//~va60R~
	//**************************************************************//~@002I~
	public boolean action(int PactionID,String PmsgData)            //~@002I~
    {                                                              //~@002I~
    	int[] ints=UserAction.parseMsgDataToClient(PmsgData);      //~@002I~
        if (Dump.Y) Dump.println("Robot.action eswnRobot="+getCurrentEswnRobot()+",id="+PactionID+"="+GCMsgID.getEnum(PactionID)+",msg="+PmsgData+",ints="+ Arrays.toString(ints));//~@002R~//~0222I~
        int eswn=ints[0];                                          //~@002I~
        if (eswn!=getCurrentEswnRobot())	//not msg to me                //~@002I~
        {                                                          //~@002I~
	        if (Dump.Y) Dump.println("Robot.action @@@@ skip by current eswn="+eswn+",current="+getCurrentEswnRobot()+",action="+PactionID);//~@002R~//~0222R~//~0229R~
        	return false;                                          //~@002I~
        }                                                          //~@002I~
        boolean rc=false;                                                //~@002I~
        switch(PactionID)                                          //~@002I~
        {                                                          //~@002I~
	 	case GCM_TAKE:                                             //~@002I~
    		ints=UserAction.parseMsgDataToClient(PmsgData);        //~@002I~
            rc=takeOne(ints);                                      //~@002R~
            if (rc)                                                //~@002I~
        		afterTakeOne();                                    //~@002I~
        	break;                                                 //~@002I~
	 	case GCM_NEXT_PLAYER:                                      //~@002R~
//  		rc=nextPlayer(ints);                                   //~@002R~
//          afterNextPlayer(eswn);  //duplicated func with UADiscard.settimeout//~0222R~
        	break;                                                 //~@002I~
	 	case GCM_NEXT_PLAYER_PONKAN:                               //~@002I~
        	break;                                                 //~@002I~
	 	case GCM_DISCARD:                                          //~@002I~
            //noting to do because getTaken() was called from actionOnServer()//~@002R~
        	break;                                                 //~@002I~
        }                                                          //~@002I~
        if (Dump.Y) Dump.println("Robot.action rc="+rc);           //~@002I~
        return rc;                                                 //~@002I~
    }                                                              //~@002I~
//    //**************************************************************//~@002R~
//    //*on Subthread after delayed                                //~@002R~
//    //**************************************************************//~@002R~
//    public boolean action(RFParm Prfp)                           //~@002R~
//    {                                                            //~@002R~
//        int actionID=Prfp.actionID;                              //~@002R~
//        int player=Prfp.player;                                  //~@002R~
//        TileData td=Prfp.td;                                     //~@002R~
//        if (Dump.Y) Dump.println("Robot.action delayed ="+actionID+"="+GCMsgID.getEnum(actionID)+",player="+player);//~@002R~
//        boolean rc=false;                                        //~@002R~
//        switch(actionID)                                         //~@002R~
//        {                                                        //~@002R~
//        case GCM_TAKE:                                           //~@002R~
//            rc=takeOneDelayed(player,td,Prfp);                   //~@002R~
//            break;                                               //~@002R~
//        }                                                        //~@002R~
//        if (Dump.Y) Dump.println("Robot.action delayed rc="+rc); //~@002R~
//        return rc;                                               //~@002R~
//    }                                                            //~@002R~
//    //**************************************************************//~@002R~
//    //*not send discred from takeOne but after sended TakeOne to client//~@002R~
//    //**************************************************************//~@002R~
//    public void afterSendToClient(int PactionID)                 //~@002R~
//    {                                                            //~@002R~
//        if (Dump.Y) Dump.println("Robot.afterSendToClient id="+PactionID+"="+GCMsgID.getEnum(PactionID));//~@002R~
//        switch(PactionID)                                        //~@002R~
//        {                                                        //~@002R~
//        case GCM_TAKE:                                           //~@002R~
//            afterTakeOne();                                      //~@002R~
//            break;                                               //~@002R~
//        case GCM_NEXT_PLAYER:                                    //~@002R~
//            afterNextPlayer();                                   //~@002R~
//            break;                                               //~@002R~
//        }                                                        //~@002R~
//    }                                                            //~@002R~
	//**************************************************************//~@002I~
    private boolean takeOne(int[] Pintp)                           //~@002R~
    {                                                              //~@002I~
    	int eswn=Pintp[PARMPOS_PLAYER];                            //~@002R~
		if (Dump.Y) Dump.println("Robot.TakeOne eswn="+eswn);      //~@002I~
        int player=accounts.eswnToPlayer(eswn);                     //~@002I~
                                                                   //~@002I~
    	TileData td=new TileData(true/*swEswnToPlayer*/,Pintp,PARMPOS_TD);                //~@002R~
        tdTaken=td;                                                //~@002I~
//        boolean swKan=Pintp[PARMPOS_SWKAN]!=0;    //robot never do Kan//~@002R~
//        RFParm prm=new RFParm(GCM_TAKE,player,td,swKan);         //~@002R~
//        URunnable.setRunFuncSubthread(this/*RunnableI*/,delay,prm/*parmObj*/,0/*parmintt*/);//~@@@@R~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~//~@002R~
//      discard(player);	//sendMsg                              //~@002R~
//      discarded(eswn,td);	//sendMsg                              //~@002R~
        return true;                                               //~@002I~
    }                                                              //~@002I~
	//**************************************************************//~0229I~
	//*from UATake.takeOne on Server to discard for Robot                                       //~0229I~//~va60R~
	//**************************************************************//~0229I~
    public void takeOne(int Pplayer,TileData Ptd)                  //~0229R~
    {                                                              //~0229I~
		if (Dump.Y) Dump.println("Robot.TakeOne player="+Pplayer+",td="+Ptd.toString());//~0229I~
        tdTaken=Ptd;                                                //~0229I~
      if (!Ptd.isRon())  //no discard id robot Ron                 //~va60I~
        afterTakeOne();	//send msg to discard                      //~0229I~
    }                                                              //~0229I~
	//**************************************************************//~va60I~
	//*from UAPon on server, send DISCARD                          //~va60R~
	//**************************************************************//~va60I~
    public void takePon(int Pplayer)                               //~va60R~
    {                                                              //~va60I~
		if (Dump.Y) Dump.println("Robot.takePon player="+Pplayer); //~va60R~
		int eswn=getCurrentEswnRobot();	//not msg to me            //~va60I~
		TileData tdDiscard=AG.aRADiscard.selectDiscard(eswn,null); //~va60R~
        String data=ACAction.strTD(tdDiscard);                     //~va60I~
        sendToServer(false/*swWaiterBlock*/,GCM_DISCARD,eswn,data);//~va60I~
    }                                                              //~va60I~
	//**************************************************************//~@002I~
	//*discard just after taken;send taken data to be discarded    //~@002R~
	//**************************************************************//~@002I~
    private void afterTakeOne()                                    //~@002I~
    {                                                              //~@002I~
		if (Dump.Y) Dump.println("Robot.afterTakeOne");             //~va66R~
		int eswn=getCurrentEswnRobot();	//not msg to me            //~@002R~
//      String data=UserAction.makeMsgDataToServer(tdTaken);       //~va60R~
//      String data=ACAction.strTD(tdTaken);                       //~va60R~
		                                                           //~va66I~
//  	TileData tdDiscard=AG.aRADiscard.selectDiscard(eswn,tdTaken);//~va60R~//~va66R~
    	TileData tdDiscard=null;                                   //~va66I~
        boolean sw2ndCall=false;                                   //~va66I~
        if ((TestOption.option2 & TO2_ROBOT_DISCARD_BUTTON)!=0)    //~va66I~
        {	                                                       //~va66I~
		    if (ctrSelectedDiscardTaken==AG.aPlayers.ctrTakenAll)	//2nd call//~va66I~
            {                                                      //~va66I~
            	tdDiscard=tdSelectedDiscard;                       //~va66I~
                sw2ndCall=true;                                    //~va66I~
            }                                                      //~va66I~
        }                                                          //~va66I~
        if (tdDiscard==null)	//1st call                         //~va66I~
        {                                                          //~va66I~
			tdDiscard=AG.aRADiscard.selectDiscard(eswn,tdTaken);   //~va66I~
		    ctrSelectedDiscardTaken=AG.aPlayers.ctrTakenAll;	   //~va66I~
        }                                                          //~va66I~
      if (tdDiscard==null)	//Kan Called                           //~va60R~
        calledKanAtTake();                                         //~va60I~
      else                                                         //~va60I~
      {                                                            //~va60I~
        int player=accounts.eswnToPlayer(eswn);                    //~va66I~
        AG.aPlayers.setTileSelected(player,tdDiscard);                    //~1204I~//~va66I~
        if ((TestOption.option2 & TO2_ROBOT_DISCARD_BUTTON)!=0 && !sw2ndCall)//~va66I~
        {                                                          //~va66M~
			if (Dump.Y) Dump.println("Robot.afterTakeOne return by TO2_ROBOT_DISCARD_BY_BUTTON");//~va66M~
        	return;                                                //~va66M~
        }                                                          //~va66M~
        String data=ACAction.strTD(tdDiscard);                     //~va60R~
        if (TestOption.getTimingBTIOErr()==TestOption.BTIOE_AFTER_ROBOTTAKE)//~9A28I~//~9A30I~
          	TestOption.disableBT();                                //~9A28I~//~9A30I~
        sendToServer(false/*swWaiterBlock*/,GCM_DISCARD,eswn,data);//~@002R~
      }                                                            //~va60I~
    }                                                              //~@002I~
	//**************************************************************//~va60I~
    protected void calledKanAtTake()                               //~va60I~
    {                                                              //~va60I~
		if (Dump.Y) Dump.println("Robot.calledKanAtTake");         //~va60I~
    }                                                              //~va60I~
	//**************************************************************//~0228I~
	//*from DrawnReqdlgHW.setDelayedTimeout at continue game to nextplayer//~0229R~
	//*discard just after taken;send taken data to be discarded    //~0229I~
	//**************************************************************//~0228I~
    public void afterTakeOneDrawnHW()                              //~0228R~
    {                                                              //~0228I~
		if (Dump.Y) Dump.println("Robot.afterTakeOneDrawnHW tdTaken="+ Utils.toString(tdTaken));//~0228R~
		int eswn=getCurrentEswnRobot();	//not msg to me            //~0228I~
        String data=ACAction.strTD(tdTaken);                       //~0228I~
        sendToServer(false/*swWaiterBlock*/,GCM_DISCARD,eswn,data);//~0228I~
    }                                                              //~0228I~
	//**************************************************************//~@002I~
//    private boolean takeOneDelayed(int Pplayer,TileData Ptd,RFParm Prfp)//~@002R~
//    {                                                              //~@002I~
//		if (Dump.Y) Dump.println("Robot.TakeOne delayed player="+Pplayer+",td="+Ptd.type+",num="+Ptd.number+",flag="+Ptd.flag+",swKan="+Prfp.swKan);//~@002R~
//        GameViewHandler.sendMsg(GCM_DISCARD,Pplayer,Prfp.swKan?1:0,0);//~v@@@R~//~@002R~
//        return true;                                               //~@002I~
//    }                                                              //~@002I~
//    //**************************************************************//~@002R~
//    private boolean nextPlayer(int[] Pintp)                      //~@002R~
//    {                                                            //~@002R~
//        int eswn=Pintp[PARMPOS_PLAYER];                          //~@002R~
//        if (Dump.Y) Dump.println("Robot.nextPlayer eswn="+eswn); //~@002R~
////        if (Players.nextPlayer(eswn)!=getCurrentEswnRobot())    //not msg to me//~@002R~
////        {                                                      //~@002R~
////            if (Dump.Y) Dump.println("Robot.nextPlayer next is not me");//~@002R~
////            return false;                                      //~@002R~
////        }                                                      //~@002R~
//        sendTake(eswn);                                          //~@002R~
//        return true;                                             //~@002R~
//    }                                                            //~@002R~
//    //**************************************************************//~@002R~//~0222R~
//    //*after players.currentPlayer was updated                     //~@002R~//~0222R~
//    //**************************************************************//~@002R~//~0222R~
//    private void afterNextPlayer(int Peswn)                                 //~@002R~//~9630R~//~0222R~
//    {                                                              //~@002R~//~0222R~
////      int timeoutAutoTake=AG.aUADelayed.timeoutAutoTake;          //~9702I~//~9B27R~//~0222R~
//        if (Dump.Y) Dump.println("Robot.afterNextPlayer eswn="+Peswn+",timeoutAutoTakeRobot="+AG.aUADelayed.timeoutAutoTakeRobot);         //~@002R~//~9630R~//~9B27R~//~0222R~
//        AG.aUserAction.UAD.setTimeout(true,Peswn/*eswn=player,nextPlayer*/);    //set AutotakeTimeout//~9B27R~//~0222R~
////      if (timeoutAutoTake==0)                                    //~9630I~//~9B27R~//~0222R~
////          sendTake(Peswn);                                            //~@002R~//~9630R~//~9B27R~//~0222R~
//    }                                                              //~@002R~//~0222R~
    //**************************************************************//+va66I~
    //*from UADiscard at GCM_NEXT_PLAYER at manual mode(not auto take)//+va66I~
    //**************************************************************//+va66I~
    public static void nextPlayerManual(int Pplayer)               //+va66I~
    {                                                              //+va66I~
        Robot r=AG.aAccounts.getRobot(Pplayer);                    //+va66I~
        int eswn=r.getCurrentEswnRobot(); //not msg to me          //+va66I~
        if (Dump.Y) Dump.println("Robot.nextPlayerManual player="+Pplayer+",eswn="+eswn);//+va66I~
        AG.aRoundStat.autoTakeTimeout(eswn);	//issued Chii      //+va66I~
        if (Dump.Y) Dump.println("Robot.nextPlayerManual exit");   //+va66I~
    }                                                              //+va66I~
    //**************************************************************//~9630I~
    //*from UATake.autoDiscardTimeout                              //~va60I~
    //*  issue Chii(Discard will be done by UAT.setAutoDiscardTimeout)//~va60I~
    //*  ir sendTake                                               //~va60I~
    //**************************************************************//~va60I~
    public static void autoTakeTimeout(int Pplayer)                     //~9630I~//+va66R~
    {                                                              //~9630I~
		if (Dump.Y) Dump.println("Robot.autoTakeTimeout player="+Pplayer);//~va60I~
        Robot r=AG.aAccounts.getRobot(Pplayer);                    //~9630I~
        int eswn=r.getCurrentEswnRobot(); //not msg to me          //~9630I~
        if (Dump.Y) Dump.println("Robot.autoTakeTimeout player="+Pplayer+",eswn="+eswn);//~9630I~
        if (AG.aRoundStat.autoTakeTimeout(eswn))	//issued Chii  //~va60R~
        {                                                          //~va60I~
			if (Dump.Y) Dump.println("Robot.autoTakeTimeout skip send by issueChii");//~va60I~
        	return;                                                //~va60I~
        }                                                          //~va60I~
        r.sendTake(eswn);                                          //~9630R~
    }                                                              //~9630I~
    //**************************************************************//~va60I~
    //*from UAKan                                                  //~va60I~
    //**************************************************************//~va60I~
    public static void autoTakeTimeoutKan(int Pplayer)             //~va60I~
    {                                                              //~va60I~
		if (Dump.Y) Dump.println("Robot.autoTakeTimeoutKan player="+Pplayer);//~va60I~
        Robot r=AG.aAccounts.getRobot(Pplayer);                    //~va60I~
        int eswn=r.getCurrentEswnRobot(); //msg to me              //~va60I~
        r.sendTake(eswn);                                          //~va60I~
    }                                                              //~va60I~
    //**************************************************************//~va60I~
    //*From UATake.autoDiscardTimeout                              //~va60I~
    //**************************************************************//~va60I~
    public void autoDiscardTimeout(int Pplayer)                    //~va60R~
    {                                                              //~va60I~
        int eswn=getCurrentEswnRobot(); //not msg to me          //~va60I~
        if (Dump.Y) Dump.println("Robot.autoDiscardTimeout player="+Pplayer+",eswn="+eswn);//~va60I~
		TileData tdDiscard=AG.aRADiscard.selectDiscard(eswn,null); //~va60R~
        String data=ACAction.strTD(tdDiscard);                     //~va60I~
        sendToServer(false/*swWaiterBlock*/,GCM_DISCARD,eswn,data);//~va60I~
        if (Dump.Y) Dump.println("Robot.autoDiscardTimeout exit"); //~va60I~
    }                                                              //~va60I~
    //**************************************************************//~@002I~
    //*take after prev player discarded with some delay            //~@002I~
    //**************************************************************//~@002I~
    private void sendTake(int Peswn)                               //~@002R~
    {                                                              //~@002I~
		if (Dump.Y) Dump.println("Robot.sendTake eswn="+Peswn);    //~@002R~
        String data="";                                            //~@002I~
        sendToServer(true/*swWaiterBlock*/,GCM_TAKE,Peswn,data);   //~@002R~
    }                                                              //~@002I~
//    //**************************************************************//~@002R~
//    private void discard(int Pplayer)                            //~@002R~
//    {                                                            //~@002R~
//        if (Dump.Y) Dump.println("Robot.discard player="+Pplayer);//~@002R~
//        GameViewHandler.sendMsg(GCM_DISCARD,Pplayer,0,0);        //~@002R~
//    }                                                            //~@002R~
//    //**************************************************************//~@002R~
//    //*emulate discarded(send msg to server)                     //~@002R~
//    //**************************************************************//~@002R~
//    private void discarded(int Peswn,TileData Ptd)               //~@002R~
//    {                                                            //~@002R~
//        if (Dump.Y) Dump.println("Robot.discarded eswn="+Peswn); //~@002R~
//        String data=UserAction.makeMsgDataToServer(Ptd);         //~@002R~
//        sendToServer(GCM_DISCARD,Peswn,data);                    //~@002R~
//    }                                                            //~@002R~
//    //**************************************************************//~@002R~
//    public static TileData getTaken(int Pplayer)                 //~@002R~
//    {                                                            //~@002R~
//        Robot r=AG.aAccounts.getRobotPlayer(Pplayer);            //~@002R~
//        TileData td=r.tdTaken;                                   //~@002R~
//        if (Dump.Y) Dump.println("Robot.getTaken player="+Pplayer+",type="+td.type+",num="+td.number+",flag="+td.flag);//~@002R~
//        return td;                                               //~@002R~
//    }                                                            //~@002R~
	//*************************************************************************//~v@@@I~//~@002I~
    public  void sendToServer(boolean PswWaiterBlock,int PactionID,int Peswn,String Pdata)//~v@@@R~//~@002R~
    {                                                              //~v@@@I~//~@002I~
        if (Dump.Y) Dump.println("Robot.sendToServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",eswn="+Peswn+",initialEswn="+initialEswn+",msgDataToServer="+Pdata);//~@002I~//~va60R~
        String msg=Peswn+MSG_SEPAPP2+Pdata;//~v@@@I~               //~@002R~
//      AG.aUserAction.actionReceived(PactionID,msg);              //~@002R~
//      AG.aUserAction.UADL.postDelayed(delayRobot,PactionID,msg);	//after current action process returned//~@002R~
        UADelayed.postDelayedRobotMsg(PswWaiterBlock,delayRobot,PactionID,initialEswn,msg);	//after current action process returned//~@002R~//~9624R~
    }                                                              //~v@@@I~//~@002I~
////*************************                                        //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~//~@002R~
////*callback from Runnable *                                        //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~//~@002R~
////*************************                                        //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~//~@002R~
//    public void URunnableCB(Object Pparmobj,int Pphase)                //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~//~@002R~
//    {                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~//~@002R~
//        int wait2=100;                                   //~@@@2I~//~@@@@R~//~v@@@R~//~@@@@I~//~@002R~
//    //*********************                                        //~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~//~@002R~
//        if (Dump.Y) Dump.println("Robot.URunnableCB");   //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@R~//~@@@@I~//~@002R~
//        RFParm rfParm=(RFParm)Pparmobj;                          //~@002R~
//        action(rfParm);                                          //~@002R~
//    }                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~//~@002R~
//    //*****************************************************************//~@002R~
//    //*****************************************************************//~@002R~
//    //*****************************************************************//~@002R~
//    class RFParm                                                 //~@002R~
//    {                                                            //~@002R~
//        int player,actionID;                                     //~@002R~
//        boolean swKan;                                           //~@002R~
//        TileData td;                                             //~@002R~
//        public RFParm(int PactionID,int Pplayer,TileData Ptd,boolean PswKan)//~@002R~
//        {                                                        //~@002R~
//            actionID=PactionID; player=Pplayer; td=Ptd;          //~@002R~
//            swKan=PswKan;                                        //~@002R~
//            if (Dump.Y) Dump.println("Robot.RFParm actionid="+actionID+",player="+player+",swKan="+PswKan);//~@002R~
//        }                                                        //~@002R~
//    }                                                            //~@002R~
}                                                                  //~1AecI~
