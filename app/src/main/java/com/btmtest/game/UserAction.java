//*CID://+va66R~: update#= 790;                                    //~va66R~
//**********************************************************************//~v101I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/01/23 va62 no need to send to robot; GCM_PON,GCM_CHII,GCM_KAN(Rovbot ignores it but)//~va62I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/11/03 va27 Tenpai chk at Reach                              //~va27I~
//v@@7 190130 isyourtturn                                          //~v@@7I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.os.Message;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.AccountsDlg;
import com.btmtest.dialog.ScoreDlg;
import com.btmtest.dialog.SuspendDlg;
import com.btmtest.dialog.SuspendIOErrDlg;
import com.btmtest.game.UA.UAChii;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.UA.UAKan;
import com.btmtest.game.UA.UAReach;
import com.btmtest.game.UA.UARon;
import com.btmtest.game.gv.GMsg;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.game.gv.Hands;
import com.btmtest.game.gv.River;
import com.btmtest.game.gv.Stock;
import com.btmtest.game.UA.UATake;                                 //~v@@@I~
import com.btmtest.game.UA.UADiscard;                              //~v@@@I~
import com.btmtest.game.UA.UAPon;                                  //~v@@@I~
import com.btmtest.game.UA.UARestart;                              //~9A28I~
import com.btmtest.utils.Dump;
import com.btmtest.utils.EventCB;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;                   //~v@@@M~
import static com.btmtest.game.GCMsgID.*;                          //~v@@@M~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GConst.*;                           //~v@@@I~
import static com.btmtest.game.gv.GMsg.*;
import static com.btmtest.TestOption.*;                         //~va60I~

//~v@@@I~
public class UserAction       //~v@@@R~
{                                                                  //~0914I~
                                                                   //~v@@7I~
//  public  final static int IMO_CLIENT=0x01;                      //~v@@7R~
//  public  final static int IMO_CLIENTSELF=0x0100;	//issue on the client if sendToServer Failed//~0110I~//~0224R~
                                                                   //~v@@7I~
	private Players players;                                       //~v@@@R~
	private Tiles tiles;                                           //~v@@@R~
	private Hands hands;                                           //~v@@@R~
	private River river;                                           //~v@@@R~
	private Stock stock;                                           //~v@@@R~
//    private boolean swInit=false;                                //~v@@@R~
//  private int actionID;                                          //~v@@@R~
	private int prevActionID;                                      //~v@@@I~
	private Accounts accounts;                                     //~v@@@I~
    private ACAction acaction;                                     //~va60R~
//  private TileData tempTD;                                       //~v@@@R~
//    private TileData infoSelectedTD;                             //~v@@@R~
    public boolean isServer;                                       //~v@@7R~
    public  String msgDataToClient;                                //~v@@@R~
    public  String msgDataToServer;                                //~v@@@R~
    private String msgNoData=new String("<NoResponse>");           //~v@@7R~
    protected boolean swSendAll; //protected for IT Mock                                     //~v@@@R~//~va60R~
    protected boolean swRobot;	//protected for IT Mock                                       //~v@@@R~//~va60R~
    private boolean swKeepPrevActionID;                                 //~v@@@I~//~v@@7R~
    protected boolean swReceived;	//action by received Msg  protected for IT Mock         //~v@@@I~//~va60R~
    private int ctrResponse,ctrRequest;                            //~v@@@I~
//  public UADelayed UADL;                                    //~v@@@R~//~9B17R~
    public UADelayed2 UADL;                                        //~9B17I~
    public  UATake    UAT;                                         //~v@@@I~//~9622R~
    public  UADiscard UAD;                                         //~v@@@I~//~v@@7R~
    public  UAPon     UAP;                                         //~v@@@I~//~9629R~
    public  UAChii  UAC;     //public for ITMock                 //~v@@@I~//~v@@7R~//~va60R~//~va62R~
    public  UAKan   UAK;                                           //~v@@7R~//~9623R~
    public  UAReach UARE;   //public for IT Mock                      //~v@@7I~//~va60R~//+va66R~
    protected  UARon UAR;      //publc for IT Mock                  //~v@@7I~//~va60R~
    private UAEndGame UAEG;                                        //~v@@7I~
    public int currentActionID;                                    //~9623R~
//*************************                                        //~v@@@I~
	public UserAction()                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UserAction Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        AG.aUserAction=this;                                       //~v@@@I~
    }                                                              //~0914I~
//**************************************************************** //~v@@@I~
//*   Btn hit (pos=player)                                         //~v@@@I~
//*      if client --(eswn)-->received(eswn->player)---+           //~v@@@I~
//*                                                    |           //~v@@@I~
//*                                                    V           //~v@@@I~
//*      if server----------------------------------->ActionOnServer(player)//~v@@@I~
//*                                                   :            //~v@@@I~
//*      received(eswn->player)<---(msgdata;eswn)-----sendToClient //~v@@@I~
//*      |                                                         //~v@@@I~
//*      V                                                         //~v@@@I~
//*      actionOnClient(player)                                    //~v@@@I~
//**************************************************************** //~v@@@I~
//*************************                                        //~v@@@I~
//*from GC at created                                        //~v@@@I~//~0220R~
//*************************                                        //~v@@@I~
	public void init()                                             //~v@@@R~
    {                                                              //~v@@@I~
        players=AG.aPlayers;                                       //~v@@@I~
        tiles=AG.aTiles;                                           //~v@@@I~
        hands=AG.aHands;                                           //~v@@@I~
        river=AG.aRiver;                                           //~v@@@I~
        stock=AG.aStock;                                           //~v@@@I~
        accounts=AG.aAccounts;                                      //~v@@@I~
        acaction=AG.aACAction;                                     //~v@@@I~
        isServer=Accounts.isServer();                              //~v@@@I~
//      UADL=new UADelayed(this);                             //~v@@@I~//~9B17R~
        UADL=new UADelayed2(this);                                 //~9B17I~
        UAT=new UATake(this);                                      //~v@@@I~
        UAD=new UADiscard(this);                                   //~v@@@I~
        UAP=new UAPon(this);                                       //~v@@@I~
        UAC=new UAChii(this);                                      //~v@@@I~
        UAK=new UAKan(this);                                       //~v@@7I~
        UAR=new UARon(this);                                       //~v@@7I~
        UARE=new UAReach(this);                                    //~v@@7I~
        UAEG=new UAEndGame();                                             //~v@@@I~//~v@@7I~
      if ((TestOption.option2 & TO2_IT)==0) //instrumented test            //~1112M~//~va60R~
      {                                                            //~va60I~
        new UARestart(this);                                       //~9A28R~
      }                                                            //~va60I~
        if (Dump.Y) Dump.println("UserAction init isServer="+isServer);//~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~va66I~
    public void newGame()                                          //~va66I~
    {                                                              //~va66I~
        if (Dump.Y) Dump.println("UserAction.newGame");            //~va66I~
    	prevActionID=0;                                            //~va66I~
    	currentActionID=0;                                         //~va66I~
    	ctrResponse=0; ctrRequest=0;                               //~va66I~
    }                                                              //~va66I~
	//*************************************************************************//~v@@@I~
    private int addCtrRequest()                                    //~v@@@I~
    {                                                              //~v@@@I~
    	ctrRequest++;                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.addCtrRequest req="+ctrRequest+",resp="+ctrResponse);//~v@@@I~
        return ctrRequest;                                         //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    private int addCtrRequest(int Pctr)                            //~v@@@I~
    {                                                              //~v@@@I~
    	ctrRequest+=Pctr;                                          //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.addCtrRequest parm ctr="+Pctr+",req="+ctrRequest+",resp="+ctrResponse);//~v@@@I~
        return ctrRequest;                                         //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@I~//~9B20R~
//    private boolean addCtrResponse()                               //~v@@@I~//~9B20R~
//    {                                                              //~v@@@I~//~9B20R~
//        ctrResponse++;                                             //~v@@@I~//~9B20R~
//        boolean rc=ctrResponse==ctrRequest;                        //~v@@@I~//~9B20R~
//        if (Dump.Y) Dump.println("UserAction.addCtrResponse req="+ctrRequest+",resp="+ctrResponse+",rc="+rc);//~v@@@I~//~9B20R~
//        return rc;                                                 //~v@@@I~//~9B20R~
//    }                                                              //~v@@@I~//~9B20R~
	//*************************************************************************//~v@@@I~
    private void resetCtr()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.resetCtr req="+ctrRequest+",resp="+ctrResponse);//~v@@@I~
        ctrRequest=0; ctrResponse=0;                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*player on msg data is eswn of the player                    //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static String makeMsgDataToClient(int Pplayer)          //~v@@@R~
    {                                                              //~v@@@I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);                   //~v@@@I~
        String str=Integer.toString(eswn);                          //~v@@@R~
        if (Dump.Y) Dump.println("UserAction.makeMsgDataToClient player="+Pplayer+",eswn="+eswn+",str="+str);//~v@@@R~
        return str;                                                //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@7I~
    public static String makeMsgDataToClient(int Pplayer,int Pparm1,int Pparm2)//~v@@7I~
    {                                                              //~v@@7I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@7I~
        String str=eswn+MSG_SEPAPP2+Pparm1+MSG_SEPAPP2+Pparm2;    //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.makeMsgDataToClient player="+Pplayer+",eswn="+eswn+",str="+str);//~v@@7I~
        return str;                                                //~v@@7I~
    }                                                              //~v@@7I~
	//*************************************************************************//~9B28I~
    public static String makeMsgDataToClient(int Pplayer,String PstrParm)//~9B28I~
    {                                                              //~9B28I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~9B28I~
        String str=eswn+MSG_SEPAPP2+PstrParm;                      //~9B28I~
        if (Dump.Y) Dump.println("UserAction.makeMsgDataToClient player="+Pplayer+",eswn="+eswn+",str="+str);//~9B28I~
        return str;                                                //~9B28I~
    }                                                              //~9B28I~
	//*************************************************************************//~9B22I~
    public static String makeMsgDataToClient(int Pplayer,int Pparm1,int Pparm2,int Pparm3)//~9B22I~
    {                                                              //~9B22I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~9B22I~
        String str=eswn+MSG_SEPAPP2+Pparm1+MSG_SEPAPP2+Pparm2+MSG_SEPAPP2+Pparm3;//~9B22I~
        if (Dump.Y) Dump.println("UserAction.makeMsgDataToClient player="+Pplayer+",eswn="+eswn+",str="+str);//~9B22I~
        return str;                                                //~9B22I~
    }                                                              //~9B22I~
	//*************************************************************************//~v@@@I~
    public static String makeMsgDataToClient(int Pplayer,TileData Ptd,int Padditional)//~v@@@R~
    {                                                              //~v@@@I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@@I~
        String str=eswn+MSG_SEPAPP2+ACAction.strTD(Ptd)+MSG_SEPAPP2+Padditional;//~v@@@I~
        if (Dump.Y) Dump.println("UserAction.makeMsgDataToClient player="+Pplayer+",eswn="+eswn+",str="+str+",additional="+Padditional);//~v@@@I~
        return str;                                                //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@7I~
    public static String makeMsgDataToClient(int Pplayer,TileData Ptd)//~v@@7I~
    {                                                              //~v@@7I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@7I~
        String str=eswn+MSG_SEPAPP2+ACAction.strTD(Ptd);           //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.makeMsgDataToClient player="+Pplayer+",eswn="+eswn+",str="+str);//~v@@7I~
        return str;                                                //~v@@7I~
    }                                                              //~v@@7I~
	//*************************************************************************//~v@@@I~
	//*player on msg data is eswn of the player                    //~v@@@I~
	//*************************************************************************//~v@@@I~
    public  static String makeMsgDataToClient(int Pplayer,TileData Ptd,int Padditional,int Padditional2)//~v@@@R~
    {                                                              //~v@@@I~
        String str=makeMsgDataToClient(Pplayer,Ptd,Padditional)+MSG_SEPAPP2+Padditional2;//~v@@@R~
        if (Dump.Y) Dump.println("UserAction.makeMsgDataToClient player="+Pplayer+",additional2="+Padditional2+",str="+str);//~v@@@R~
        return str;                                                //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@R~
//    //*msgdata to server,data after eswn(eswn added at sendToServer()~v@@@R~//~v@@@R~
//    //*************************************************************************//~v@@@R~
//    public static String makeMsgDataToServer(int Pplayer,TileData Ptd)//~v@@@R~
//    {                                                            //~v@@@R~
//        int eswn=AG.aAccounts.playerToEswn(Pplayer);             //~v@@@R~
////      String str=eswn+MSG_SEPAPP2+ACAction.strTD(Ptd);         //~v@@@R~
//        String str=eswn+MSG_SEPAPP2+ACAction.strTD(Ptd);         //~v@@@I~
//        if (Dump.Y) Dump.println("UserAction.makeMsgDataToServer str="+str);//~v@@@R~
//        return str;                                              //~v@@@R~
//    }                                                            //~v@@@R~
	//*************************************************************************//~v@@@I~
    public static int[] parseMsgDataToClient(String Pdata)         //~v@@@I~
    {                                                              //~v@@@I~
        int[] ints=ACAction.parseAppData(Pdata);                   //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.parseMsgDataToClient Pdata="+Pdata+",parmsz="+ints.length+",player="+ints[0]);//~v@@@R~
        if (Dump.Y) Dump.println("UserAction.parseMsgDataToClient ints="+Arrays.toString(ints));//~9826I~
        return ints;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@M~
	//*send msgData to Server                                      //~v@@7I~
	//*************************************************************************//~v@@7I~
    private void sendToServer(boolean PswRequest,int PactionID,int Pplayer)//~v@@@M~//~9B21R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("UserAction.sendToServer swRequest="+PswRequest+",actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",msgDataToServer="+msgDataToServer);//~v@@@M~
        if (msgDataToServer==msgNoData)                            //~v@@7I~
        {                                                          //~v@@7I~
	        if (Dump.Y) Dump.println("UserAction.sendToServer bypass by setNoMsgdataToServer");//~v@@7I~
            return;                                                //~v@@7I~
        }                                                          //~v@@7I~
        int msgid=PswRequest?GCM_USER_ACTION:GCM_USER_ACTION_RESP; //~v@@@M~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@@M~
//      String msgdata=PactionID+MSG_SEP+Pplayer+msgDataToServer;  //~v@@@M~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+msgDataToServer;//~v@@@M~
    	accounts.sendToServer(msgid,msgdata);                      //~v@@@M~
    }                                                              //~v@@@M~
	//*************************************************************************//~v@@7I~
	//*send intparm to Server                                      //~v@@7I~
	//*************************************************************************//~v@@7I~
    public  void sendToServer(int PactionID,int Pplayer,int Pparm1,int Pparm2,int Pparm3)//~v@@7I~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.sendToServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",parm="+Pparm1+","+Pparm2+","+Pparm3);//~v@@7I~
        int msgid=GCM_USER_ACTION; //~v@@7I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@7I~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pparm1+MSG_SEPAPP2+Pparm2+MSG_SEPAPP2+Pparm3;//~v@@7I~
    	accounts.sendToServer(msgid,msgdata);                      //~v@@7I~
    }                                                              //~v@@7I~
	//*************************************************************************//~9B28I~
    public  void sendToServer(int PactionID,int Pplayer,int Pparm1,int Pparm2,int Pparm3,int Pparm4)//~9B28I~
    {                                                              //~9B28I~
        if (Dump.Y) Dump.println("UserAction.sendToServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",parm="+Pparm1+","+Pparm2+","+Pparm3);//~9B28I~
        int msgid=GCM_USER_ACTION;                                 //~9B28I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~9B28I~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pparm1+MSG_SEPAPP2+Pparm2+MSG_SEPAPP2+Pparm3+MSG_SEPAPP2+Pparm4;//~9B28I~
    	accounts.sendToServer(msgid,msgdata);                      //~9B28I~
    }                                                              //~9B28I~
	//*************************************************************************//~v@@7I~
	//*send intparm+strparm to Server                              //~v@@7I~
	//*************************************************************************//~v@@7I~
    public  void sendToServer(int PactionID,int Pplayer,int Pparm1,int Pparm2,int Pparm3,String PstrParm)//~v@@7I~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.sendToServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",parm="+Pparm1+","+Pparm2+","+Pparm3+",strparm="+PstrParm);//~v@@7I~
        int msgid=GCM_USER_ACTION;                                 //~v@@7I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@7I~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pparm1+MSG_SEPAPP2+Pparm2+MSG_SEPAPP2+Pparm3+MSG_SEPAPP2+PstrParm;//~v@@7I~
    	accounts.sendToServer(msgid,msgdata);                      //~v@@7I~
    }                                                              //~v@@7I~
	//*************************************************************************//~v@@7I~
	//*send intparm+strparm to Server                              //~v@@7I~
	//*************************************************************************//~v@@7I~
    public  void sendToServer(int PactionID,int Pplayer,int Pparm1,String PstrParm)//~v@@7I~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.sendToServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",parm="+Pparm1+",strparm="+PstrParm);//~v@@7I~
        int msgid=GCM_USER_ACTION;                                 //~v@@7I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@7I~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pparm1+MSG_SEPAPP2+PstrParm;//~v@@7I~
    	accounts.sendToServer(msgid,msgdata);                      //~v@@7I~
    }                                                              //~v@@7I~
	//*************************************************************************//~v@@@I~
//  private void sendToClient(boolean PswRobot,int PactionID,int Pplayer)//~v@@@R~
    private void sendToClient(int PactionID,int Pplayer)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.sendToClient actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",msgDataToClient="+msgDataToClient);//~v@@@R~//~9B24R~
        if (Dump.Y) Dump.println("UserAction.sendToClient msgDataToClient="+((Object)msgDataToClient).toString());//~v@@7R~//~9B24R~
        if (msgDataToClient==msgNoData)                            //~v@@7R~
        {                                                          //~v@@7I~
	        if (Dump.Y) Dump.println("UserAction.sendToClient bypass by setNoMsgdataToClient");//~v@@7I~
        }                                                          //~v@@7I~
        else                                                       //~v@@7I~
		    sendToClient(swSendAll,PactionID,Pplayer,msgDataToClient); //~v@@@I~//~v@@7R~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    private void sendToClient(boolean PswSendAll,int PactionID,int Pplayer,String Pdata)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.sendToClient actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",swSendAll="+PswSendAll+",data="+Pdata);//~v@@@I~//~9B24R~
	    sendToClient(PswSendAll,swRobot,PactionID,Pplayer,Pdata);  //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public void sendToClient(boolean PswSendAll,boolean PswRobot,int PactionID,int Pplayer,String Pdata)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.sendToClient actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",swRobot="+PswRobot+",swSendAll="+PswSendAll+",data="+Pdata);//~v@@@I~
    	resetCtr();                                                //~v@@@I~
        if (PswSendAll)                                            //~v@@@I~
        {                                                          //~v@@@I~
    		int sendctr=acaction.sendActionData(PswRobot,PactionID,Pdata);//~v@@@I~
        	addCtrRequest(sendctr);                                //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
	    	if (acaction.sendActionDataPlayer(true/*swRobot*/,Pplayer,PactionID,Pdata))//not dummy//~v@@@I~
	        	addCtrRequest();                                   //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@I~//~v@@7R~
//    public void sendToTheClient(int Pplayer,int PactionID,String Pmsg)//~v@@@I~//~v@@7R~
//    {                                                              //~v@@@I~//~v@@7R~
//        if (Dump.Y) Dump.println("UserAction.sendToTheClient actionID="+PactionID+",player="+Pplayer+",msg="+Pmsg);//~v@@@I~//~v@@7R~
//        int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@@I~//~v@@7R~
//        String msgdata=eswn+MSG_SEPAPP2+Pmsg;    //~v@@@I~       //~v@@7R~
//        accounts.sendToTheClient(Pplayer,PactionID,msgdata);                 //~v@@@I~//~v@@7R~
//    }                                                              //~v@@@I~//~v@@7R~
	//*************************************************************************//~v@@7I~
	//*data:UserAction Data                                        //~v@@7I~
	//*************************************************************************//~v@@7I~
    public void sendToTheClient(int Pplayer,int PactionID,String Pmsg)//~v@@7R~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.sendToTheClient actionID="+PactionID+",player="+Pplayer+",msg="+Pmsg);//~v@@7R~
//  	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@7I~//~9612R~
    	int eswn=accounts.getCurrentEswn();                        //~9612I~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pmsg;    //~v@@7R~
    	accounts.sendToTheClient(Pplayer/*to Account getIndex*/,GCM_USER_ACTION,msgdata);//~v@@7R~
    }                                                              //~v@@7I~
	//*************************************************************************//~9610I~
	//*data:UserAction Data,prev sendToTheClient is buggy, correct by this one-->bug was corrected//~9610I~//~9612R~
	//*************************************************************************//~9610I~
    public void sendToTheClient(boolean PswTransferToReal,int Pplayer,int PactionID,String Pmsg)//~9610I~
    {                                                              //~9610I~
        if (Dump.Y) Dump.println("UserAction.sendToTheClient swTransferToReal="+PswTransferToReal+",actionID="+PactionID+",player="+Pplayer+",msg="+Pmsg);//~9610I~
//  	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~9610I~
    	int eswn=accounts.getCurrentEswn();                        //~9610I~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pmsg;    //~9610I~
        int pl=Pplayer;                                            //~9610I~
        if (!PswTransferToReal)                                    //~9610I~
        	pl=AG.aAccounts.getRealPlayer(pl);                     //~9610I~
    	accounts.sendToTheClient(pl,GCM_USER_ACTION,msgdata);      //~9610I~
    }                                                              //~9610I~
	//*************************************************************************//~v@@7I~
	//*data:UserAction Data                                        //~v@@7I~
	//*************************************************************************//~v@@7I~
    public void sendToTheClientDealer(int PactionID,String Pmsg)   //~v@@7I~
    {                                                              //~v@@7I~
//  	int eswn=AG.aAccounts.getCurrentDealerRealEswn();          //~v@@7I~//~9321R~
    	int eswn=accounts.getCurrentEswn();                        //~9321I~
    	int player=AG.aAccounts.getCurrentDealerReal();            //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.sendToTheClientDealer actionID="+PactionID+",dealer-player="+player+",current-eswn="+eswn+",msg="+Pmsg);//~v@@7I~//~9430R~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pmsg;    //~v@@7I~
    	accounts.sendToTheClient(player/*to Account getIndex*/,GCM_USER_ACTION,msgdata);//~v@@7I~
    }                                                              //~v@@7I~
	//*************************************************************************//~9606I~
	//*data:UserAction Data                                        //~9606I~
	//*************************************************************************//~9606I~
    public void sendToTheClientDealerFirstStarter(int PactionID,String Pmsg)//~9606I~
    {                                                              //~9606I~
    	int eswn=accounts.getCurrentEswn();                        //~9606I~
    	int player=AG.aAccounts.getFirstDealerPlayerReal();        //~9606I~
        if (Dump.Y) Dump.println("UserAction.sendToTheClientDealerFirstStarter actionID="+PactionID+",first-dealer-player="+player+",current-eswn="+eswn+",msg="+Pmsg);//~9606I~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pmsg;    //~9606I~
    	accounts.sendToTheClient(player/*to Account getIndex*/,GCM_USER_ACTION,msgdata);//~9606I~
    }                                                              //~9606I~
	//*************************************************************************//~9430I~
    public void sendToTheClientDealerWithSourceEswn(int PactionID,int Peswn,String Pmsg)//~9430I~
    {                                                              //~9430I~
    	int eswn=Peswn;                                            //~9430I~
    	int player=AG.aAccounts.getCurrentDealerReal();            //~9430I~
        if (Dump.Y) Dump.println("UserAction.sendToTheClientDealerWithSourceEswn actionID="+PactionID+",dealer-player="+player+",srcEswn="+Peswn+",msg="+Pmsg);//~9430R~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pmsg;    //~9430I~
    	accounts.sendToTheClient(player/*to Account getIndex*/,GCM_USER_ACTION,msgdata);//~9430I~
    }                                                              //~9430I~
	//*************************************************************************//~9606I~
    public void sendToTheClientDealerWithSourceEswnFirstStarter(int PactionID,int Peswn,String Pmsg)//~9606R~
    {                                                              //~9606I~
    	int eswn=Peswn;                                            //~9606I~
    	int player=AG.aAccounts.getFirstDealerPlayerReal();        //~9606I~
        if (Dump.Y) Dump.println("UserAction.sendToTheClientDealerWithSourceEswnFirstStarter actionID="+PactionID+",dealer-player="+player+",srcEswn="+Peswn+",msg="+Pmsg);//~9606I~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pmsg;    //~9606I~
    	accounts.sendToTheClient(player/*to Account getIndex*/,GCM_USER_ACTION,msgdata);//~9606I~
    }                                                              //~9606I~
	//*************************************************************************//~v@@7I~
	//*data:UserAction Data                                        //~v@@7I~
	//*************************************************************************//~v@@7I~
    public void sendToTheClientOther(int Pplayer,int PactionID,String Pmsg)//~v@@7I~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.sendToTheClientOther actionID="+PactionID+",player="+Pplayer+",msg="+Pmsg);//~v@@7R~
        int idxAccount=AG.aAccounts.playerToMember(Pplayer);       //~v@@7I~
    	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@7I~
        String msgdata=PactionID+MSG_SEP+eswn+MSG_SEPAPP2+Pmsg;    //~v@@7I~
		accounts.sendToClient(false/*PswRobot*/,idxAccount/*Pskip*/,GCM_USER_ACTION,msgdata);//~v@@7R~
    }                                                              //~v@@7I~
	//*************************************************************************//~v@@@I~
    public static void sendErr(int Popt,int Pplayer,String Pmsg)   //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.sendErr player="+Pplayer+",msg="+Pmsg);//~v@@@I~
        if (Pplayer==PLAYER_YOU)                                   //~v@@@I~
        {                                                          //~v@@@I~
        	ACAction.showErrmsg(Popt,Pmsg);                        //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~va66I~
        if (AG.swTrainingMode)                                     //~va66I~
        {                                                          //~va66I~
//            int eswn=Accounts.playerToEswn(Pplayer);                   //~9830I~//~va66R~
//            String strEswn=GConst.nameESWN[eswn];                //~va66R~
//            if (Pmsg.startsWith(strEswn))                        //~va66R~
//                ACAction.showErrmsg(Popt,Pmsg);                  //~va66R~
//            else                                                 //~va66R~
//	        	ACAction.showErrmsg(Popt,strEswn+":"+Pmsg);        //~va66I~
            ACAction.showErrmsg(Popt,Pmsg);                        //~v@@@R~
        }                                                          //~va66I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//      	AG.aUserAction.sendToTheClient(Pplayer,GCM_ERRMSG,Pmsg);//~v@@@R~
	    	AG.aAccounts.sendToTheClient(Pplayer,GCM_ERRMSG,Pmsg); //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@7I~
    public static void sendErr(int Popt,int Pplayer,int Pmsgid)    //~v@@7I~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.sendErr player="+Pplayer+",msgid="+Integer.toHexString(Pmsgid)+"="+Utils.getStr(Pmsgid));//~va62R~
//  	sendErr(Popt,Pplayer,Utils.getStr(Pmsgid));                //~v@@7I~//~0215R~
        if (Pplayer==PLAYER_YOU)                                   //~0215I~
        {                                                          //~0215I~
			sendErr(Popt,Pplayer,Utils.getStr(Pmsgid)); //showErrmsg           //~0215I~//~0224R~
        }                                                          //~0215I~
        else                                                       //~va66I~
        if (AG.swTrainingMode)                                     //~va66I~
        {                                                          //~va66I~
			sendErr(Popt,Pplayer,Utils.getStr(Pmsgid)); //showErrmsg//~va66I~
        }                                                          //~va66I~
        else                                                       //~0215I~
        {                                                          //~0215I~
        	String msg=GMsg.makeSendMsg(Popt,Pmsgid);              //~0224I~
	    	AG.aAccounts.sendToTheClient(Pplayer,GCM_ERRMSG_NOLANG,msg);//~0215I~//~0224R~
        }                                                          //~0215I~
    }                                                              //~v@@7I~
	//*************************************************************************//~v@@7I~
//  public static void showInfoAll(int Popt,String Pmsg)           //~v@@7R~//~9A25R~
    public static void showInfoAll(int Popt,int PidxSkip,String Pmsg)//~9A25I~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.showInfoAll idxSkip="+PidxSkip+",msg="+Pmsg);//~v@@7I~//~9A25R~
//      if ((Popt & IMO_CLIENT)!=0)                                //~v@@7R~
        if (!AG.aUserAction.isServer)                                             //~v@@7I~
        {                                                          //~v@@7I~
//      	AG.aAccounts.sendToServer(GCM_ERRMSG_ALL,Pmsg);	//server will callback//~v@@7R~//~0110R~
        	if (!AG.aAccounts.sendToServer(GCM_ERRMSG_ALL,Pmsg))	//server server failed//~0110I~
//  			if ((Popt & IMO_CLIENTSELF)!=0)                    //~0110I~//~0224R~
    			if ((Popt & GMSGOPT_CLIENTSELF)!=0)                //~0224I~
//  		        ACAction.showErrmsg((Popt & ~IMO_CLIENTSELF),Pmsg);//~0110I~//~0224R~
    		        ACAction.showErrmsg((Popt & ~GMSGOPT_CLIENTSELF),Pmsg);//~0224I~
        }                                                          //~v@@7I~
        else                                                       //~v@@7I~
        {                                                          //~v@@7I~
	        ACAction.showErrmsg(Popt,Pmsg);                        //~v@@7I~
//      	AG.aAccounts.sendToClient(false/*swRobot*/,-1/*skip*/,GCM_ERRMSG,Pmsg);//~v@@7R~//~9A25R~
        	AG.aAccounts.sendToClient(false/*swRobot*/,PidxSkip,GCM_ERRMSG,Pmsg);//~9A25I~
        }                                                          //~v@@7I~
    }                                                              //~v@@7I~
	//*************************************************************************//~0224I~
    public static void showInfoAll(int Popt,int PidxSkip,int Pmsgid)//~0225R~
    {                                                              //~0225I~
        if (Dump.Y) Dump.println("UserAction.showInfoAll opt="+Integer.toHexString(Popt)+",idxSkip="+PidxSkip+",msgid="+Integer.toHexString(Pmsgid));//~0225I~
	    showInfo(Popt,Pmsgid);                                     //~0225I~
        String msg;                                                //~0224I~
        if (!AG.aUserAction.isServer)                              //~0224I~
        {                                                          //~0224I~
        	if (PidxSkip==-1)	//not received                     //~0225I~
            {                                                      //~0225I~
	        	msg=GMsg.makeSendMsg(Popt|GMSGOPT_ALL,Pmsgid);         //~0224I~//~0225R~
	        	AG.aAccounts.sendToServer(GCM_ERRMSG_NOLANG,msg);	//server server failed//~0224I~//~0225R~
            }                                                      //~0225I~
        }                                                          //~0224I~
        else                                                       //~0224I~
        {                                                          //~0224I~
	        msg=GMsg.makeSendMsg(Popt,Pmsgid);                     //~0224I~
        	AG.aAccounts.sendToClient(false/*swRobot*/,PidxSkip,GCM_ERRMSG_NOLANG,msg);//~0224I~
        }                                                          //~0224I~
    }                                                              //~0224I~
	//*************************************************************************//~0303I~
    public static void showInfoAll(int Popt,int PidxSkip,int Pmsgid,String Pdata)//~0303I~
    {                                                              //~0303I~
        if (Dump.Y) Dump.println("UserAction.showInfoAll opt="+Integer.toHexString(Popt)+",idxSkip="+PidxSkip+",msgid="+Integer.toHexString(Pmsgid)+",data="+Pdata);//~0303I~
	    showInfo(Popt,Pmsgid,Pdata);                                     //~0303I~//~0304R~
        String msg;                                                //~0303I~
        if (!AG.aUserAction.isServer)                              //~0303I~
        {                                                          //~0303I~
        	if (PidxSkip==-1)	//not received                     //~0303I~
            {                                                      //~0303I~
	        	msg=GMsg.makeSendMsg(Popt|GMSGOPT_ALL,Pmsgid,Pdata);//~0303R~
	        	AG.aAccounts.sendToServer(GCM_ERRMSG_NOLANG,msg);	//server server failed//~0303I~
            }                                                      //~0303I~
        }                                                          //~0303I~
        else                                                       //~0303I~
        {                                                          //~0303I~
	        msg=GMsg.makeSendMsg(Popt,Pmsgid,Pdata);               //~0303R~
        	AG.aAccounts.sendToClient(false/*swRobot*/,PidxSkip,GCM_ERRMSG_NOLANG,msg);//~0303I~
        }                                                          //~0303I~
    }                                                              //~0303I~
	//*************************************************************************//~9B27I~
    public static void showInfoToThePlayer(int Popt,int Pmsgid,int Pplayer)//~9B27I~
    {                                                              //~9B27I~
	    showInfoToThePlayer(Popt,Utils.getStr(Pmsgid),Pplayer);    //~9B27R~
    }                                                              //~9B27I~
	//*************************************************************************//~9B27I~
    public static void showInfoToThePlayer(int Popt,String Pmsg,int Pplayer)//~9B27I~
    {                                                              //~9B27I~
        if (Dump.Y) Dump.println("UserAction.showInfoToThePlayer opt="+Popt+",player="+Pplayer+",msg="+Pmsg);//~9B27R~
        if (AG.aUserAction.isServer && Pplayer==PLAYER_YOU)        //~9B27R~
	        ACAction.showErrmsg(Popt,Pmsg);                        //~9B27I~
        else                                                       //~9B27I~
	    	AG.aAccounts.sendToTheClient(Pplayer,(Popt & GMSGOPT_ANDTOAST)!=0 ? GCM_ERRMSG_ANDTOAST : GCM_ERRMSG,Pmsg);//~9B27R~
    }                                                              //~9B27I~
	//*************************************************************************//~9B11I~
    public static void showInfo(int Popt,String Pmsg)              //~9B11I~
    {                                                              //~9B11I~
        if (Dump.Y) Dump.println("UserAction.showInfo msg="+Pmsg); //~9B11I~
	    ACAction.showErrmsg(Popt,Pmsg);                            //~9B11I~
    }                                                              //~9B11I~
	//*************************************************************************//~9B26I~
    public static void showInfo(int Popt,int Pmsgid)               //~9B26I~
    {                                                              //~9B26I~
	    ACAction.showErrmsg(Popt,Utils.getStr(Pmsgid));            //~9B26I~
    }                                                              //~9B26I~
	//*************************************************************************//~0303I~
    public static void showInfo(int Popt,int Pmsgid,String Pdata)  //~0303I~
    {                                                              //~0303I~
	    ACAction.showErrmsg(Popt,Utils.getStr(Pmsgid,Pdata));      //~0303I~
    }                                                              //~0303I~
	//*************************************************************************//~9A25I~
    public static void showInfoAll(int Popt,int Pmsgid)            //~9703I~
    {                                                              //~9703I~
//      showInfoAll(Popt,Utils.getStr(Pmsgid));                    //~9703I~//~9A25R~//~0224R~
        showInfoAll(Popt,-1/*skip*/,Pmsgid);                       //~0224I~
    }                                                              //~9703I~
	//*************************************************************************//~9A25I~
    public static void showInfoAll(int Popt,String Pmsg)           //~9A25I~
    {                                                              //~9A25I~
        showInfoAll(Popt,-1/*skip*/,Pmsg);                         //~9A25I~
    }                                                              //~9A25I~
	//*************************************************************************//~9830I~
	//*msg prepended ESWN name                                     //~9830I~
	//*************************************************************************//~9830I~
    public static void showInfoEswn(int Popt,int Pplayer,String Pmsg)//~9830I~
    {                                                              //~9830I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~9830I~
        if (Dump.Y) Dump.println("UserAction.showInfoEswn player="+Pplayer+",eswn="+eswn+",msg="+Pmsg);//~9830I~//~0222R~
        String msg=AG.resource.getString(R.string.Info_ActionESWN,GConst.nameESWN[eswn],Pmsg);//~9830I~
        ACAction.showErrmsg(Popt,msg);                             //~9830I~
    }                                                              //~9830I~
	//*************************************************************************//~0225I~
	//*msg prepended ESWN name                                     //~0225I~
	//*************************************************************************//~0225I~
    public static void showInfoEswn(int Popt,int Peswn,int Pmsgid)//~0225I~
    {                                                              //~0225I~
        String text=Utils.getStr(Pmsgid);                          //~0225I~
        String msg=AG.resource.getString(R.string.Info_ActionESWN,GConst.nameESWN[Peswn],text);//~0225I~
        if (Dump.Y) Dump.println("UserAction.showInfoEswn eswn="+Peswn+",msgid="+Integer.toHexString(Pmsgid)+",msg="+msg);//~0225I~
        ACAction.showErrmsg(Popt,msg);                             //~0225I~
    }                                                              //~0225I~
	//*************************************************************************//~0303I~
    public static void showInfoEswn(int Popt,int Peswn,int Pmsgid,String Pdata)//~0303I~
    {                                                              //~0303I~
        String text=Utils.getStr(Pmsgid,Pdata);                    //~0303I~
        String msg=AG.resource.getString(R.string.Info_ActionESWN,GConst.nameESWN[Peswn],text);//~0303I~
        if (Dump.Y) Dump.println("UserAction.showInfoEswn eswn="+Peswn+",msgid="+Integer.toHexString(Pmsgid)+",data="+Pdata+",msg="+msg);//~0303I~
        ACAction.showErrmsg(Popt,msg);                             //~0303I~
    }                                                              //~0303I~
	//*************************************************************************//~v@@7I~
	//*msg prepended ESWN name                                     //~v@@7R~
	//*************************************************************************//~v@@7I~
    public static void showInfoAllEswn(int Popt,int Pplayer,String Pmsg)//~v@@7R~
    {                                                              //~v@@7I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~v@@7R~
        if (Dump.Y) Dump.println("UserAction.showInfoAllEswn player="+Pplayer+",eswn="+eswn+",msg="+Pmsg);//~v@@7R~//~9818R~
        String msg=AG.resource.getString(R.string.Info_ActionESWN,GConst.nameESWN[eswn],Pmsg);//~v@@7R~
        ACAction.showErrmsg(Popt,msg);                             //~v@@7I~
	    AG.aAccounts.sendToClient(false/*swRobot*/,-1/*skip*/,GCM_ERRMSG,msg);//~v@@7I~
    }                                                              //~v@@7I~
	//*************************************************************************//~0225I~
	//*msg prepended ESWN name                                     //~0225I~
	//*************************************************************************//~0225I~
    public static void showInfoAllEswn(int Popt,int Pplayer,int Pmsgid)//~0225R~
    {                                                              //~0225I~
    	int eswn=Accounts.playerToEswn(Pplayer);                   //~0225R~
        if (Dump.Y) Dump.println("UserAction.showInfoAllEswn opt="+Integer.toString(Popt)+",player="+Pplayer+",eswn="+eswn+",msgid="+Integer.toHexString(Pmsgid));//~0225I~
//        String text=Utils.getStr(Pmsgid);              //~v@@5I~ //~0225R~
//        String msg=AG.resource.getString(R.string.Info_ActionESWN,GConst.nameESWN[eswn],text);//~0225R~
//        ACAction.showErrmsg(Popt,msg);                           //~0225R~
//        String sendmsg=GMsg.makeSendMsgEswn(Popt,Pmsgid,eswn);   //~0225R~
//        AG.aAccounts.sendToClient(false/*swRobot*/,-1/*skip*/,GCM_ERRMSG_NOLANG,sendmsg);//~0225R~
    	showInfoAllEswnEswn(Popt,eswn,Pmsgid);                     //~0225I~
    }                                                              //~0225I~
	//*************************************************************************//~9818I~
	//*msg prepended ESWN name                                     //~9818I~
	//*************************************************************************//~9818I~
    public static void showInfoAllEswnEswn(int Popt,int Peswn,String Pmsg)//~9818I~
    {                                                              //~9818I~
        if (Dump.Y) Dump.println("UserAction.showInfoAllEswnEswn eswn="+Peswn+",msg="+Pmsg);//~9818I~
        String msg=AG.resource.getString(R.string.Info_ActionESWN,GConst.nameESWN[Peswn],Pmsg);//~9818I~
        showInfoAllEswn(Popt,msg);                                 //~9818I~
    }                                                              //~9818I~
	//*************************************************************************//~0225I~
	//*msg prepended ESWN by parm eswn                             //~0225I~
	//*************************************************************************//~0225I~
    public static void showInfoAllEswnEswn(int Popt,int Peswn,int Pmsgid)//~0225I~
    {                                                              //~0225I~
    	showInfoAllEswnEswn(Popt,-1/*skipIdx*/,Peswn,Pmsgid);      //~0225R~
    }                                                              //~0225I~
    public static void showInfoAllEswnEswn(int Popt,int PidxSkip,int Peswn,int Pmsgid)//~0225R~
    {                                                              //~0225I~
        if (Dump.Y) Dump.println("UserAction.showInfoAllEswnEswn opt="+Integer.toHexString(Popt)+",eswn="+Peswn+",msgid="+Integer.toHexString(Pmsgid)+",idxSkip="+PidxSkip);//~0225R~
        showInfoEswn(Popt,Peswn,Pmsgid);    //on the issuer        //~0225I~
        String msg;                                                //~0225I~
        if (!AG.aUserAction.isServer)                              //~0225I~
        {                                                          //~0225I~
        	if (PidxSkip==-1)	//not received                     //~0225I~
            {                                                      //~0225I~
	        	msg=GMsg.makeSendMsgEswn(Popt|GMSGOPT_ALL,Pmsgid,Peswn);  //GMSGOPT_ESWN will be set//~0225I~
        		AG.aAccounts.sendToServer(GCM_ERRMSG_NOLANG,msg);	//server server failed//~0225I~
            }                                                      //~0225I~
        }                                                          //~0225I~
        else    //svr                                              //~0225I~
        {                                                          //~0225I~
	        msg=GMsg.makeSendMsgEswn(Popt,Pmsgid,Peswn); //with GMSGOPT_ESWN//~0225R~
        	AG.aAccounts.sendToClient(false/*swRobot*/,PidxSkip,GCM_ERRMSG_NOLANG,msg);//~0225I~
        }                                                          //~0225I~
    }                                                              //~0225I~
    public static void showInfoAllEswnEswn(int Popt,int PidxSkip,int Peswn,int Pmsgid,String Pdata)//~0303I~
    {                                                              //~0303I~
        if (Dump.Y) Dump.println("UserAction.showInfoAllEswnEswn opt="+Integer.toHexString(Popt)+",eswn="+Peswn+",msgid="+Integer.toHexString(Pmsgid)+",idxSkip="+PidxSkip+",data="+Pdata);//~0303I~
        showInfoEswn(Popt,Peswn,Pmsgid,Pdata);    //on the issuer  //~0303I~
        String msg;                                                //~0303I~
        if (!AG.aUserAction.isServer)                              //~0303I~
        {                                                          //~0303I~
        	if (PidxSkip==-1)	//not received                     //~0303I~
            {                                                      //~0303I~
	        	msg=GMsg.makeSendMsgEswn(Popt|GMSGOPT_ALL,Pmsgid,Peswn,Pdata);  //GMSGOPT_ESWN will be set//~0303I~
        		AG.aAccounts.sendToServer(GCM_ERRMSG_NOLANG,msg);	//server server failed//~0303I~
            }                                                      //~0303I~
        }                                                          //~0303I~
        else    //svr                                              //~0303I~
        {                                                          //~0303I~
	        msg=GMsg.makeSendMsgEswn(Popt,Pmsgid,Peswn,Pdata); //with GMSGOPT_ESWN//~0303I~
        	AG.aAccounts.sendToClient(false/*swRobot*/,PidxSkip,GCM_ERRMSG_NOLANG,msg);//~0303I~
        }                                                          //~0303I~
    }                                                              //~0303I~
	//*************************************************************************//~v@@7I~
	//*on Server                                                   //~v@@7I~
	//*************************************************************************//~v@@7I~
    public static void showInfoAllEswn(int Popt,String Pmsg)       //~v@@7I~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.showInfoAll msg="+Pmsg);//~v@@7I~
        ACAction.showErrmsg(Popt,Pmsg);                            //~v@@7I~
	    AG.aAccounts.sendToClient(false/*swRobot*/,-1/*skip*/,GCM_ERRMSG,Pmsg);//~v@@7I~
    }                                                              //~v@@7I~
    //*************************************************************************//~0225I~
    public static void showInfoAllEswn(int Popt,int Pmsgid)        //~0225I~
    {                                                              //~0225I~
        if (Dump.Y) Dump.println("UserAction.showInfoAll opt="+Popt+",msgid="+Integer.toHexString(Pmsgid));//~0225I~
    	showInfoAllEswn(Popt,PLAYER_YOU,Pmsgid);                   //~0225I~
    }                                                              //~0225I~
//    //*************************************************************************//~v@@@I~
//    public void action(Message Pmsg)                             //~v@@@I~
//    {                                                            //~v@@@I~
//        boolean rc=false;                                        //~v@@@I~
//        if (!swInit)                                             //~v@@@I~
//        {                                                        //~v@@@I~
//            init();                                              //~v@@@I~
//            swInit=true;                                         //~v@@@I~
//        }                                                        //~v@@@I~
//        AG.aGC.resetActionError();                               //~v@@@I~
//        actionID=Pmsg.what;                                      //~v@@@I~
//        int[] parm=GameViewHandler.getMsgIntData(Pmsg);          //~v@@@I~
//        if (Dump.Y) Dump.println("UserAction.action id="+actionID+"="+GCMsgID.getEnum(actionID)+",parm1="+parm[0]+",parm2="+parm[1]);//~v@@@I~
//        int player=parm[0];                                      //~v@@@I~
//        if (!players.isYourTurn(actionID,player,prevActionID))   //~v@@@I~
//            return;                                              //~v@@@I~
//        switch(actionID)                                         //~v@@@I~
//        {                                                        //~v@@@I~
//        case GCM_TAKE:                                           //~v@@@I~
//            rc=takeOne(player);                                  //~v@@@I~
//            break;                                               //~v@@@I~
//        case GCM_PON:                                            //~v@@@I~
//            rc=takePon(player);                                  //~v@@@I~
//            break;                                               //~v@@@I~
//        case GCM_CHII:                                           //~v@@@I~
//            rc=takeChii(player);                                 //~v@@@I~
//            break;                                               //~v@@@I~
//        case GCM_KAN:                                            //~v@@@I~
//            rc=takeKan(player);                                  //~v@@@I~
//            break;                                               //~v@@@I~
//        case GCM_REACH:                                          //~v@@@I~
//            rc=reach(player);                                    //~v@@@I~
//            break;                                               //~v@@@I~
//        case GCM_RON:                                            //~v@@@I~
//            rc=complete(player);                                 //~v@@@I~
//            //TODO                                               //~v@@@I~
//            break;                                               //~v@@@I~
//        case GCM_DISCARD:                                        //~v@@@I~
//            rc=discard(player);                                  //~v@@@I~
//            break;                                               //~v@@@I~
//        case GCM_OPEN:                                           //~v@@@I~
//            rc=open(player);                                     //~v@@@I~
//            break;                                               //~v@@@I~
//        }                                                        //~v@@@I~
//        if (rc)                                                  //~v@@@I~
//        {                                                        //~v@@@I~
//            prevActionID=actionID;                               //~v@@@I~
//            AG.aAccounts.propagate(player,actionID);             //~v@@@I~
//        }                                                        //~v@@@I~
//    }                                                            //~v@@@I~
    //*************************************************************************//~v@@@I~
    //*Button action from GameViewHandler                          //~v@@@I~
    //*************************************************************************//~v@@@I~
    public void action(Message Pmsg)                               //~v@@@I~
    {                                                              //~v@@@I~
    	if (!Status.isGaming())                                    //~v@@7I~
        {                                                          //~v@@7I~
        	return;                                                //~v@@7I~
        }                                                          //~v@@7I~
        boolean rc=false;                                          //~v@@@I~
//        if (!swInit)                                             //~v@@@R~
//        {                                                        //~v@@@R~
//            init();                                              //~v@@@R~
//            swInit=true;                                         //~v@@@R~
//        }                                                        //~v@@@R~
//      AG.aGMsg.drawMsgbar("");                                   //~v@@@I~//~9626R~
//      AG.aGMsg.reset();                                          //~9626I~//~9629R~
        msgDataToServer="";	//for also robot                                        //~v@@@I~//~v@@7I~
        int actionID=Pmsg.what;                                    //~v@@@I~
        resetGmsg(actionID);                                       //~9629I~
        int[] parm=GameViewHandler.getMsgIntData(Pmsg);            //~v@@@I~
        String[] strParm=GameViewHandler.getMsgStrData(Pmsg);         //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.action id="+actionID+"="+GCMsgID.getEnum(actionID)+",int parm="+Arrays.toString(parm)+",strparm="+Utils.toString(strParm));//~9B16R~
        int player=parm[PARMPOS_PLAYER]; //PLAYER_YOU              //~v@@@R~
//      if (player=-1) //starter                                   //~v@@@R~
//      	player=accounts.getCurrentStarterPos();                //~v@@@R~
//      else                                                       //~v@@@R~
//      	player=accounts.getEswnRelativePos(eswn);              //~v@@@R~
        if (accounts.isDummyPlayer(player))                       //~v@@@I~
        	swReceived=true;                                       //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			swReceived=false;                                      //~v@@@I~
//      	if (!getActionInfo(actionID,player,parm))                 //~v@@@I~//~v@@7R~//~9426R~
        	if (!getActionInfo(actionID,player,parm,strParm))      //~9426I~
        		return;                                      //~v@@@I~
            if (actionID==GCM_RON_ANYWAY)  //bypass rochk done     //~0205I~
                actionID=GCM_RON;                                  //~0205I~
        }                                                          //~v@@@I~
        if (isServer)                                              //~v@@@I~
        {                                                          //~v@@@I~
//  	    actionOnServer(actionID,player,null);                  //~v@@@R~//~v@@7R~
//  	    actionOnServer(actionID,player,parm/*may contain optional parm,server only*/);//~v@@7R~
    	    actionOnServer(actionID,player,parm/*may contain optional parm,server only*/,strParm);//~v@@7I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//  		int eswn=accounts.getCurrentEswn();                    //~v@@@R~
//          sendToServer(true/*PswRequest*/,actionID,eswn);        //~v@@@R~
//          sendToServer(true/*PswRequest*/,actionID,player);      //~v@@@I~//~v@@7R~
            sendToServer(true/*PswRequest*/,actionID,player);//~v@@7I~
        }                                                          //~v@@@I~
        if (delayedGMsgTxt!=null)                                  //~9627M~
        {                                                          //~9627M~
        	showDelayedGMsg();                                     //~9627M~
        }                                                          //~9627M~
    }                                                              //~v@@@I~
    //*************************************************************************//~va66I~
    //*return robot player if currentPlayer is robot in trainingMode//~va66I~
    //*************************************************************************//~va66I~
    private int chkRobotTakenInTrainingMode(int PprevActionID)     //~va66R~
    {                                                              //~va66I~
    	int rc=-1;                                                 //~va66I~
    	if (AG.swTrainingMode)                                     //~va66I~
        {                                                          //~va66I~
        	int cp=AG.aPlayers.getCurrentPlayer();                 //~va66I~
	        int np;                                                //~va66R~
	        if (PprevActionID==GCM_KAN)                            //~va66R~
	        	np=cp;                                             //~va66I~
            else                                                   //~va66I~
	        if (PprevActionID==GCM_TAKE)                           //~va66R~
	        	np=cp;                                             //~va66I~
            else                                                   //~va66I~
	        if (PprevActionID==0)   //1st parent take              //~va66I~
	        	np=cp;                                             //~va66I~
            else                                                   //~va66I~
	        	np=Players.nextPlayer(cp);                         //~va66R~
            if (np>=0 && AG.aAccounts.isRobotPlayer(np))           //~va66R~
                rc=np;                                             //~va66R~
        }                                                          //~va66I~
        if (Dump.Y) Dump.println("UserAction.chkRobotTakenInTrainingMode swTrainingMode="+AG.swTrainingMode+",PprevActionID="+PprevActionID+",rc="+rc);//~va66R~
        return rc;                                                 //~va66I~
    }                                                              //~va66I~
    //*************************************************************************//~va66I~
    //*player is not always PLAYER_YOU                             //~va66R~
    //*return robot player if currentPlayer is robot in TEST option of ROBOT_DISCARD_BUTTON//~va66I~
    //*************************************************************************//~va66I~
    private int chkRobotDiscardTestOption()                        //~va66I~
    {                                                              //~va66I~
    	int rc=-1;                                                 //~va66I~
    	if ((TestOption.option2 & TO2_ROBOT_DISCARD_BUTTON)!=0)    //~va66I~
        {                                                          //~va66I~
        	int cp=AG.aPlayers.getCurrentPlayer();                 //~va66I~
            if (AG.aAccounts.isRobotPlayer(cp))                    //~va66I~
            {                                                      //~va66I~
		        if (Dump.Y) Dump.println("UserAction.chkRobotTakenTestOption Test mode rc=currentPlayerRobot="+cp);//~va66R~
                rc=cp;                                             //~va66I~
            }                                                      //~va66I~
        }                                                          //~va66I~
		if (Dump.Y) Dump.println("UserAction.chkRobotTakenTestOption Test mode rc="+rc);//~va66I~
        return rc;                                                 //~va66I~
    }                                                              //~va66I~
    //*************************************************************************//~v@@@R~
    //*On Server/Clinet, get info when button pushed                //~v@@@R~//~v@@7R~
    //*************************************************************************//~v@@@R~
//  public boolean getActionInfo(int PactionID,int Pplayer,int[] PintParm)        //~v@@@R~//~v@@7R~//~9426R~
    public boolean getActionInfo(int PactionID,int Pplayer,int[] PintParm,String[] PstrParm)//~9426I~
    {                                                              //~v@@@R~
        boolean rc=true;                                           //~v@@@R~
        int playerTakeDiscardRobot=-1;                             //~va66R~
        if (Dump.Y) Dump.println("UserAction.getActionInfo id="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",intparm="+Arrays.toString(PintParm)+",strParm="+Arrays.toString(PstrParm));//~v@@@R~//~9426I~//~9B16I~//~9B17R~
        switch(PactionID)                                          //~9629I~
        {                                                          //~9629I~
        case GCM_TAKE:                                             //~9629I~
        case GCM_PON:                                              //~9629I~
        case GCM_CHII:                                             //~9629I~
        case GCM_KAN:                                              //~9629I~
            if (PactionID==GCM_TAKE)                               //~va66I~
                playerTakeDiscardRobot=chkRobotTakenInTrainingMode(prevActionID);//~va66R~
            if (playerTakeDiscardRobot>=0)                         //~va66R~
            {                                                      //~va66R~
                if (isWaitingOtherThanYou(PactionID,playerTakeDiscardRobot))//~va66R~
                    return false;                                  //~va66R~
                PintParm[PARMPOS_PLAYER]=playerTakeDiscardRobot;   //~va66R~
                break;                                             //~va66R~
            }                                                      //~va66R~
        	if (isWaitingOtherThanYou(PactionID,Pplayer))          //~9629I~
            	return false;                                      //~9629I~
            break;                                                 //~9629I~
        case GCM_DISCARD:                                          //~va66I~
          	playerTakeDiscardRobot=chkRobotDiscardTestOption();    //~va66I~
            break;                                                 //~va66I~
//        case GCM_PON_C:                                          //~9B16R~//~9B17M~//~9B18R~
//            break;                                                 //~9B17I~//~9B18R~
        }                                                          //~9629I~
        if (playerTakeDiscardRobot>=0)                                   //~va66I~
        {                                                          //~va66I~
            if (!players.isYourTurn(PactionID,playerTakeDiscardRobot,prevActionID))//~va66I~
            {                                                      //~va66I~
                return false;                                      //~va66I~
            }                                                      //~va66I~
        }                                                          //~va66I~
        else                                                       //~va66I~
        if (!players.isYourTurn(PactionID,Pplayer,prevActionID)) //~v@@@R~//~v@@7R~
        {                                                        //~v@@@R~//~v@@7R~
            return false;                                        //~v@@@R~//~v@@7R~
        }                                                        //~v@@@R~//~v@@7R~
        switch(PactionID)                                           //~v@@@R~
        {                                                          //~v@@@R~
        case GCM_TAKE:                                             //~v@@@R~
        	if (playerTakeDiscardRobot>=0)                               //~va66I~
            {                                                      //~va66I~
            	rc=UAT.selectInfo(isServer,playerTakeDiscardRobot);      //~va66I~
            	break;                                             //~va66I~
            }                                                      //~va66I~
            rc=UAT.selectInfo(isServer,Pplayer);                   //~v@@7I~
            break;                                                 //~v@@@R~
        case GCM_DISCARD:                                          //~v@@@R~
          if (playerTakeDiscardRobot>=0)                           //~va66I~
            rc=UAD.selectInfo(isServer,playerTakeDiscardRobot);    //~va66I~
          else                                                     //~va66I~
            rc=UAD.selectInfo(isServer,Pplayer);                  //~v@@@R~//~9B23R~
            if (rc)                                                //~9A30R~//~9A31R~
    		    updateButtonStatusReach(0/*back to default*/);//~9A30R~//~9A31R~
            break;                                                 //~v@@@R~
        case GCM_PON:                                              //~v@@@R~
//          rc=UAP.selectInfo(isServer,Pplayer);                   //~v@@@R~//~9B18R~
            rc=UAP.selectInfo(isServer,Pplayer,PintParm);          //~9B18R~
            break;                                                 //~v@@@R~
//        case GCM_PON_C:                                          //~9B16R~//~9B17M~//~9B18R~
//            rc=UADL.selectInfo2Touch(PactionID,isServer,Pplayer,PintParm);//~9B17I~//~9B18R~
//            break;                                                 //~9B17I~//~9B18R~
        case GCM_CHII:                                             //~v@@@R~
//          rc=UAC.selectInfo(isServer,Pplayer);                   //~v@@@R~//~9B23R~
            rc=UAC.selectInfo(isServer,Pplayer,PintParm);          //~9B23I~
            break;                                                 //~v@@@R~
        case GCM_KAN:                                              //~v@@@R~
//          rc=takeKan(Pplayer);                                    //~v@@@R~//~v@@7R~
//          rc=UAK.selectInfo(isServer,Pplayer);                   //~v@@7I~//~9B18R~
            rc=UAK.selectInfo(isServer,Pplayer,PintParm);          //~9B18I~
            break;                                                 //~v@@@R~
        case GCM_REACH:                                            //~v@@@R~//~v@@7R~
        case GCM_REACH_OPEN:                                       //~v@@7I~
        case GCM_FORCE_REACH:                                      //~va27I~
        case GCM_FORCE_REACH_OPEN:                                 //~va27I~
//          rc=UARE.selectInfo(isServer,Pplayer,);                 //~va27R~
            rc=UARE.selectInfo(isServer,Pplayer,PactionID);        //~va27I~
            if (rc)                                                //~9A30R~//~9A31R~
    		    updateButtonStatusReach(PactionID);         //~9A30R~//~9A31R~
            break;                                                 //~v@@@R~//~v@@7R~
        case GCM_REACH_RESET:                                      //~9A30R~//~9A31R~
        case GCM_REACH_OPEN_RESET:                                 //~9A30R~//~9A31R~
    		updateButtonStatusReach(PactionID);             //~9A30R~//~9A31R~
            break;                                                 //~9A30R~//~9A31R~
        case GCM_RON:                                              //~v@@@R~
        case GCM_RON_ANYWAY:                                       //~0205I~
//          rc=complete(Pplayer);                                   //~v@@@R~//~v@@7R~
//          rc=UAR.selectInfo(isServer,Pplayer,PintParm);                   //~9B23I~//~0205R~
            rc=UAR.selectInfo(PactionID,isServer,Pplayer,PintParm);//~0205I~
            break;                                                 //~v@@@R~
        case GCM_OPEN:                                             //~v@@@R~
//          rc=open(Pplayer);                                       //~v@@@R~//~v@@7R~
            break;                                                 //~v@@@R~
        case GCM_ENDGAME_DRAWN:                                    //~v@@7I~
//          rc=UAEG.selectInfo(isServer,Pplayer,PintParm);         //~v@@7I~//~9426R~
            rc=UAEG.selectInfo(isServer,Pplayer,PintParm,PstrParm);//~9426I~
            break;                                                 //~v@@7I~
        case GCM_WAITON:                                           //~9626I~
        case GCM_WAITOFF:                                          //~9626I~
        	rc=UADL.selectInfo(PactionID,isServer,Pplayer);         //~9626I~
            break;                                                 //~9701I~
//        case GCM_WAITON2:                                          //~9B16M~//~9C04R~
//        case GCM_WAITOFF2:                                         //~9B16M~//~9C04R~
//            rc=UADL.selectInfo2(PactionID,isServer,Pplayer);       //~9B16I~//~9C04R~
//            break;                                                 //~9B16I~//~9C04R~
//      case GCM_TIMEOUT_STOPAUTO:                                 //~9701R~
//      	UADL.selectInfo(PactionID,isServer,Pplayer);           //~9701R~
//          rc=false;                                              //~9701R~
//          break;                                                 //~9701R~
        case GCM_2TOUCH:                                           //~9B18I~
            rc=UADL.selectInfo2Touch(isServer,Pplayer,PintParm);   //~9B18I~
            break;                                                 //~9B18I~
        }                                                          //~v@@@R~
        if (Dump.Y) Dump.println("UserAction.getActionInfo rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@R~
	//*************************************************************************//~9A31I~
//  private void updateButtonStatusReach(int Pactionid)            //~va27R~
    public  void updateButtonStatusReach(int Pactionid)            //~va27I~
    {                                                              //~9A31I~
        if (Dump.Y) Dump.println("UserAction.updateButtonStatusReach actionid="+Pactionid);//~9A31I~
		EventCB.postEvent(ECB_ACTION_REACH,Pactionid);	//to GC.updateButtonStatusReach onMainThread//~9A31I~
	}                                                              //~9A31I~
	//*************************************************************************//~v@@@I~
	//*player:if received,pos of triger player                     //~v@@@R~
	//*       else PLAYER_YOU by touch                             //~v@@@R~
	//*************************************************************************//~v@@@I~
    private void actionOnServer(int PactionID,int Pplayer/*player relative pos on server*/,int[] PintParm,String[] PstrParm)//~v@@@R~//~v@@7R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.actionOnServer actionid="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer);//~v@@@R~
//        if (!players.isYourTurn(PactionID,Pplayer,prevActionID))   //~v@@@R~//~v@@7R~
//        {                                                          //~v@@@R~//~v@@7R~
//            return;                                                //~v@@@R~//~v@@7R~
//        }                                                          //~v@@@R~//~v@@7R~
        msgDataToClient="";                                                //~v@@@I~
        swSendAll=false;                                           //~v@@@R~
//      swKeepPrevActionID=false;                                       //~v@@@R~//~v@@7R~
//      swRobot=false;                                             //~v@@@R~
//        boolean swDraw=Pplayer==accounts.getCurrentESWN();       //~v@@@R~
        boolean rc=action(true/*swServer*/,PactionID,Pplayer,PintParm,PstrParm);//~v@@@R~//~v@@7R~
        if (rc)                                                    //~v@@@I~
        {                                                          //~v@@@I~
//          sendToClient(true/*swRobot*/,PactionID,Pplayer);       //~v@@@R~
            sendToClient(PactionID,Pplayer);	//send actionID,player with msgDataToClient                       //~v@@@I~//~v@@7R~
//            Robot robot=accounts.getRobotPlayer(Pplayer);        //~v@@@R~
//            if (robot!=null)                                     //~v@@@R~
//                robot.afterSendToClient(PactionID);              //~v@@@R~
          if (!swKeepPrevActionID)                                      //~v@@@R~//~v@@7R~
              prevActionID=PactionID;                              //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*Server/Client received                                      //~v@@@I~
	//*************************************************************************//~v@@@I~
    public void actionReceived(int PactionID,String Pdata)         //~v@@@R~
    {                                                              //~v@@@I~
	    int[] ints=parseMsgDataToClient(Pdata);                    //~v@@@I~
        int eswn=ints[PARMPOS_PLAYER];                             //~v@@@R~
	    int player=accounts.eswnToPlayer(eswn);                             //~v@@@R~
        if (Dump.Y) Dump.println("UserAction.actionReceived actionID="+PactionID+"="+GCMsgID.getEnum(PactionID)+",eswn="+eswn+",data="+Pdata);//~v@@@R~
		swReceived=true;                                           //~v@@@I~
//      AG.aGMsg.reset();                                          //~9626I~//~9629R~
        resetGmsg(PactionID);                                      //~9629I~
        if (isServer)	                                           //~v@@@I~
        {                                                          //~v@@@I~
        	actionOnServer(PactionID,player,ints,null);                 //~v@@@R~//~v@@7R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	actionOnClient(PactionID,player,ints,null);                 //~v@@@R~//~v@@7R~
//          sendToServer(false/*PswRequest*/,PactionID,player);//no need to send GCM_USER_ACTION_RESP(502)    //~v@@@R~//~9B21R~
        }                                                          //~v@@@I~
        if (delayedGMsgTxt!=null)                                  //~9628I~
        {                                                          //~9628I~
        	showDelayedGMsg();                                     //~9628I~
        }                                                          //~9628I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*Server received  USER_ACTION_RESP                           //~v@@@I~
	//*************************************************************************//~v@@@I~
    public void actionReceivedResp(int PactionID)                  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.actionReceived actionID="+PactionID+"="+GCMsgID.getEnum(PactionID));//~v@@@I~
        addCtrRequest();                                           //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*client received from server                                 //~v@@@R~
	//*player:relative pos from PLAYER_YOU of triger player's eswn //~v@@@I~
	//*************************************************************************//~v@@@I~
    public void actionOnClient(int PactionID,int Pplayer,int[] PintParm,String[] PstrParm)//~v@@@R~//~v@@7R~
    {                                                              //~v@@@I~
        boolean swDraw=Pplayer==accounts.getCurrentEswn();         //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.actionOnClient actionID="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer+",swDraw="+swDraw);//~v@@@R~
        msgDataToServer="";                                        //~v@@7I~
//      boolean rc=action(false/*swServer*/,PactionID,Pplayer,PintParm,PstrParm);  //~v@@@I~//~v@@7R~//~9B20R~
        action(false/*swServer*/,PactionID,Pplayer,PintParm,PstrParm);//~9B20I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*on server(set data and draw) and client(draw)               //~v@@@I~
	//*rc:true:send to client when server                          //~9B20I~
	//*************************************************************************//~v@@@I~
    public boolean action(boolean PswServer,int PactionID,int Pplayer/*relative pos on the device*/,int[] PintParm,String[] PstrParm)//~v@@@R~//~v@@7R~
    {                                                              //~v@@@I~
        int playerTakeDiscardRobot=-1;                             //~va66R~
        if (Dump.Y) Dump.println("UserAction.action actionID="+PactionID+"="+GCMsgID.getEnum(PactionID)+",player="+Pplayer);//~v@@@R~
        if (Dump.Y) Dump.println("UserAction.action intParm="+Arrays.toString(PintParm));//~v@@7I~
        if (Dump.Y) Dump.println("UserAction.action strParm="+Arrays.toString(PstrParm));//~9826I~
	 	if (PactionID==GCM_TAKE)                              //~va66I~
            playerTakeDiscardRobot=chkRobotTakenInTrainingMode(prevActionID);//~va66R~
        else                                                       //~va66I~
        if (PactionID==GCM_DISCARD)                                //~va66I~
        	playerTakeDiscardRobot=chkRobotDiscardTestOption();    //~va66I~
        if (playerTakeDiscardRobot>=0)                             //~va66R~
        {                                                          //~va66I~
	        if (!players.isYourTurn(PactionID,playerTakeDiscardRobot,prevActionID))//~va66R~
    	    {                                                      //~va66I~
        	    return false;                                      //~va66I~
        	}                                                      //~va66I~
            PintParm[PARMPOS_PLAYER]=playerTakeDiscardRobot;       //~va66R~
        }                                                          //~va66I~
        else                                                       //~va66I~
        if (!players.isYourTurn(PactionID,Pplayer,prevActionID))   //~v@@7I~
        {                                                          //~v@@7I~
            return false;                                          //~v@@7I~
        }                                                          //~v@@7I~
        boolean rc=true;                                            //~v@@@R~
        swSendAll=true;                                      //~v@@@R~
        swRobot=true;                                              //~v@@@R~
        swKeepPrevActionID=false;                                       //~v@@@I~//~v@@7R~
        switch(PactionID)                                          //~v@@@R~
        {                                                          //~v@@@I~
	 	case GCM_TAKE:                                             //~v@@@I~
	        currentActionID=PactionID;                             //~9623I~
          if (playerTakeDiscardRobot>=0)                                 //~va66I~
            rc=UAT.takeOne(PswServer,swReceived,playerTakeDiscardRobot,PintParm);//~va66I~
          else                                                     //~va66I~
            rc=UAT.takeOne(PswServer,swReceived,Pplayer,PintParm); //~v@@@R~
	        swRobot=false;                                         //~0229I~
        	break;                                                 //~v@@@I~
	 	case GCM_DISCARD:                                          //~v@@@M~
	        currentActionID=PactionID;                             //~9623I~
        	swRobot=false;                                         //~v@@@I~
          if (playerTakeDiscardRobot>=0)                           //~va66I~
            rc=UAD.discard(PswServer,swReceived,playerTakeDiscardRobot,PintParm);//~va66I~
          else                                                     //~va66I~
            rc=UAD.discard(PswServer,swReceived,Pplayer,PintParm); //~v@@@R~
        	break;                                                 //~v@@@M~
	 	case GCM_NEXT_PLAYER:	//client only                      //~v@@@R~
        	swKeepPrevActionID=true;	//not set to PrevActionID          //~v@@@I~//~v@@7R~
//      	swRobot=false;                                         //~v@@@R~
        	swRobot=false;                                         //~0229I~
            rc=UAD.nextPlayer(PswServer,Pplayer);   //UADiscard    //~v@@@R~//~v@@7R~
        	break;                                                 //~v@@@I~
	 	case GCM_NEXT_PLAYER_PONKAN:	//client only              //~v@@7I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~v@@7I~
//      	swRobot=false;                                         //~v@@7I~
        	swRobot=false;                                         //~0229I~
            rc=UAD.nextPlayerPonKan(PswServer,Pplayer);   //UADiscard//~v@@7R~
        	break;                                                 //~v@@7I~
	 	case GCM_PON:                                              //~v@@@I~
        	swRobot=false;                                         //~va62R~
	        currentActionID=PactionID;                             //~9623I~
            rc=UAP.takePon(PswServer,swReceived,Pplayer,PintParm);//~v@@@R~
        	break;                                                 //~v@@@I~
//        case GCM_PON_C:                                            //~9B17I~//~9B18R~
//            swKeepPrevActionID=true;    //not set to PrevActionID  //~9B17I~//~9B18R~
//            rc=UADL.action2Touch(PactionID,PswServer,swReceived,Pplayer,PintParm);//~9B17R~//~9B18R~
//            break;                                                 //~9B17I~//~9B18R~
	 	case GCM_CHII:                                             //~v@@@I~
        	swRobot=false;                                         //~va62R~
	        currentActionID=PactionID;                             //~9623I~
//          rc=takeChii(Pplayer);                                  //~v@@@R~
            rc=UAC.takeChii(PswServer,swReceived,Pplayer,PintParm);//~v@@@I~
        	break;                                                 //~v@@@I~
	 	case GCM_KAN:                                              //~v@@@I~
        	swRobot=false;                                         //~va62R~
	        currentActionID=PactionID;                             //~9623I~
//          rc=takeKan(Pplayer);                                   //~v@@@R~//~v@@7R~
            rc=UAK.takeKan(PswServer,swReceived,Pplayer,PintParm); //~v@@7I~
        	break;                                                 //~v@@@I~
	 	case GCM_TIMEOUT_TO_TAKABLE_RINSHAN:                       //~0403I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~0403I~
        	swRobot=false;                                         //~0403I~
            rc=UAK.clientTakableRinshan(Pplayer,PintParm);         //~0403I~
        	break;                                                 //~0403I~
	 	case GCM_REACH:                                            //~v@@@I~
	 	case GCM_FORCE_REACH:                                      //~va27I~
	        swRobot=false;                                         //~v@@7I~
//          rc=reach(Pplayer);                                     //~v@@@R~//~v@@7R~
            rc=UARE.reach(PswServer,swReceived,Pplayer,PintParm);   //~v@@7I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~v@@7I~
        	break;                                                 //~v@@@I~
	 	case GCM_REACH_OPEN:                                       //~v@@7I~
	 	case GCM_FORCE_REACH_OPEN:                                 //~va27I~
	        swRobot=false;                                         //~v@@7I~
            rc=UARE.reachOpen(PswServer,swReceived,Pplayer,PintParm);//~v@@7I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~v@@7I~
        	break;                                                 //~v@@7I~
	 	case GCM_REACH_RESET:                                      //~9A30I~
	        swRobot=false;                                         //~9A30I~
            rc=UARE.reachReset(PswServer,swReceived,Pplayer,PintParm);//~9A30I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~9A30I~
        	break;                                                 //~9A30I~
	 	case GCM_REACH_OPEN_RESET:                                 //~9A30I~
	        swRobot=false;                                         //~9A30I~
            rc=UARE.reachOpenReset(PswServer,swReceived,Pplayer,PintParm);//~9A30I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~9A30I~
        	break;                                                 //~9A30I~
	 	case GCM_RON:                                              //~v@@@I~
	        currentActionID=PactionID;                             //~9623I~
	        swRobot=false;                                         //~v@@7R~
//          rc=complete(Pplayer);                                  //~v@@@R~//~v@@7R~
            rc=UAR.complete(PswServer,swReceived,Pplayer,PintParm);//~v@@7R~
            //TODO                                                 //~v@@@I~
        	break;                                                 //~v@@@I~
	 	case GCM_OPEN:                                             //~v@@@I~
	        swRobot=false;                                         //~v@@7I~
//          rc=open(Pplayer);                                      //~v@@@R~//~v@@7R~
            rc=UARE.open(PswServer,swReceived,Pplayer,PintParm);	//UAReach//~v@@7R~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~v@@7I~
        	break;                                                 //~v@@@I~
	 	case GCM_ENDGAME_DRAWN:                                    //~v@@7I~
	        swRobot=false;                                         //~v@@7I~
            rc=UAEG.drawn(PswServer,swReceived,Pplayer,PintParm,PstrParm);//~v@@7R~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~v@@7I~
        	break;                                                 //~v@@7I~
	 	case GCM_ENDGAME_SCORE:                                    //~v@@7I~
	        swRobot=false;                                         //~v@@7I~
			setNoMsgToServer();	//for sendToServer()               //~v@@7I~
            ScoreDlg.onReceived(PswServer,swReceived,Pplayer,PintParm);//~v@@7R~
            rc=false;	//no resp msg send                         //~v@@7I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~v@@7I~
        	break;                                                 //~v@@7I~
	 	case GCM_ENDGAME_ACCOUNTS:                                 //~9322I~
	        swRobot=false;                                         //~9322I~
			setNoMsgToServer();	//for sendToServer()               //~9322I~
            AccountsDlg.onReceived(PswServer,swReceived,Pplayer,PintParm);//~9322I~
            rc=false;	//no resp msg send                         //~9322I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~9322I~
        	break;                                                 //~9322I~
	 	case GCM_SUSPENDDLG:                                       //~9822R~
	        swRobot=false;                                         //~9822I~
			setNoMsgToServer();	//for sendToServer()               //~9822I~
            SuspendDlg.onReceived(PswServer,swReceived,Pplayer,PintParm);//~9822I~//~9826R~
            rc=false;	//no resp msg send                         //~9822I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~9822I~
        	break;                                                 //~9822I~
	 	case GCM_SUSPENDDLG_IOERR:                                 //~9A21I~
	        swRobot=false;                                         //~9A21I~
			setNoMsgToServer();	//for sendToServer()               //~9A21I~
            SuspendIOErrDlg.onReceived(PswServer,swReceived,Pplayer,PintParm);//~9A21I~
            rc=false;	//no resp msg send                         //~9A21I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~9A21I~
        	break;                                                 //~9A21I~
//        case GCM_RESUMEDLG:                                        //~9830I~//~9831R~
//            swRobot=false;                                         //~9830I~//~9831R~
//            setNoMsgToServer(); //for sendToServer()               //~9830I~//~9831R~
//            ResumeDlg.onReceived(PswServer,swReceived,Pplayer,PintParm);//~9830I~//~9831R~
//            rc=false;   //no resp msg send                         //~9830I~//~9831R~
//            swKeepPrevActionID=true;    //not set to PrevActionID  //~9830I~//~9831R~
//            break;                                                 //~9830I~//~9831R~
//        case GCM_LASTGAME:                                         //~9522I~//~9524R~
//            swRobot=false;                                         //~9522I~//~9524R~
//            setNoMsgToServer(); //for sendToServer()               //~9522I~//~9524R~
//            rc=AG.aLastGame.onReceived(PswServer,swReceived,Pplayer,PintParm,PstrParm);//~9522R~//~9524R~
//            swKeepPrevActionID=true;    //not set to PrevActionID  //~9522I~//~9524R~
//            break;                                                 //~9522I~//~9524R~
	 	case GCM_WAITON:                                           //~v@@7I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~v@@7I~
            rc=actionWait(GCM_WAITON,PswServer,swReceived,Pplayer,PintParm);//~v@@7R~
        	break;                                                 //~v@@7I~
	 	case GCM_WAITOFF:                                          //~v@@7I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~v@@7I~
            rc=actionWait(GCM_WAITOFF,PswServer,swReceived,Pplayer,PintParm);//~v@@7R~
        	break;                                                 //~v@@7I~
//        case GCM_WAITON2:                                          //~9B16M~//~9C04R~
//            swKeepPrevActionID=true;    //not set to PrevActionID  //~9B16I~//~9C04R~
//            rc=actionWait2(GCM_WAITON,PswServer,swReceived,Pplayer,PintParm);//~9B16I~//~9C04R~
//            break;                                                 //~9B16I~//~9C04R~
//        case GCM_WAITOFF2:                                         //~9B16I~//~9C04R~
//            swKeepPrevActionID=true;    //not set to PrevActionID  //~9B16I~//~9C04R~
//            rc=actionWait2(GCM_WAITOFF,PswServer,swReceived,Pplayer,PintParm);//~9B16R~//~9C04R~
//            break;                                                 //~9B16I~//~9C04R~
        case GCM_WAIT_RELEASE_ACTION:                          //~v@11I~//~9627I~
	        swSendAll=false;                                       //~9627I~
    	    swRobot=false;                                         //~9627I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~9627I~
			setNoMsgToServer();	//for sendToServer()               //~9627I~
            AG.aUADelayed.actionReleaseWait(Pplayer,PintParm);             //~v@11I~//~9627R~
            rc=false;	//no resp msg send                         //~9627I~
            break;                                             //~v@11I~//~9627I~
        case GCM_TIMEOUT_STOPAUTO:                                 //~9701I~
	        swSendAll=false;                                       //~9701I~
    	    swRobot=false;                                         //~9701I~
        	swKeepPrevActionID=true;	//not set to PrevActionID  //~9701I~
			setNoMsgToServer();	//for sendToServer()               //~9701I~
            AG.aUADelayed.stopAuto(PintParm);                       //~9701R~
            rc=false;	//no resp msg send                         //~9701I~
            break;                                                 //~9701I~
        case GCM_2TOUCH:                                           //~9B18I~
    	    swRobot=false;                                         //~9B24I~
            swKeepPrevActionID=true;    //not set to PrevActionID  //~9B18I~
            rc=UADL.action2Touch(PswServer,swReceived,Pplayer,PintParm);//~9B18I~
            break;                                                 //~9B18I~
        }                                                          //~v@@@I~
        if (rc)                                                  //~v@@@R~//~v@@7R~
        {                                                        //~v@@@R~//~v@@7R~
            if (!swKeepPrevActionID)                                  //~v@@@R~//~v@@7R~
                prevActionID=PactionID;                          //~v@@@R~//~v@@7R~
        }                                                        //~v@@@R~//~v@@7R~
        if (Dump.Y) Dump.println("UserAction.action rc="+rc+",prevAction="+prevActionID+",swKeepPrevActionID="+swKeepPrevActionID);      //~v@@@I~//~0229R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@R~
//    public TileData selectTile(int Pplayer)                      //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UserAction.selectTile");       //~v@@@R~
//        TileData td=players.getTileSelected(Pplayer);            //~v@@@R~
//        return td;                                               //~v@@@R~
//    }                                                            //~v@@@R~
//    //*************************************************************************//~v@@@I~
//    private int getSelectPos(int Pplayer)                        //~v@@@I~
//    {                                                            //~v@@@I~
//        int pos=players.getTileSelectedPos(Pplayer);             //~v@@@I~
//        if (Dump.Y) Dump.println("UserAction.getSelectedPos pos="+pos);//~v@@@I~
//        return pos;                                              //~v@@@I~
//    }                                                            //~v@@@I~
//    //*************************************************************************//~v@@@R~
//    public String strTempTD(boolean PswFree)                     //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UserAction.strTempTD swFree="+PswFree);//~v@@@R~
//        String str=acaction.strTD(tempTD);                       //~v@@@R~
//        if (PswFree)                                             //~v@@@R~
//            tempTD=null;                                         //~v@@@R~
//        return str;                                              //~v@@@R~
//    }                                                            //~v@@@R~
//    public boolean takeKan(int Pplayer)                               //~v@@@I~//~v@@7R~
//    {                                                              //~v@@@I~//~v@@7R~
//        if (Dump.Y) Dump.println("UserAction.takeKan player="+Pplayer);//~v@@@R~//~v@@7R~
////        if (!players.isYourTurn(actionID,Pplayer))               //~v@@@R~//~v@@7R~
////            return;                                              //~v@@@R~//~v@@7R~
////        TileData td = tiles.getNextKan(Pplayer); //TDF_TAKEN     //~v@@@R~//~v@@7R~
////        if (td == null)                                          //~v@@@R~//~v@@7R~
////            return;                                              //~v@@@R~//~v@@7R~
////        int rc=players.takeKan(Pplayer,td);                      //~v@@@R~//~v@@7R~
//        int rc=players.takeKan(Pplayer);                           //~v@@@I~//~v@@7R~
//        if (rc<0)                                                  //~v@@@I~//~v@@7R~
//            return false;                                          //~v@@@R~//~v@@7R~
////      Status.setTaken(td,Pplayer);                               //~v@@@R~//~v@@7R~
////      stock.takeOneKan();                                        //~v@@@R~//~v@@7R~
//        hands.takeKan(Pplayer,rc);                                 //~v@@@R~//~v@@7R~
//        river.takeKan(Pplayer,rc);                                 //~v@@@R~//~v@@7R~
//        stock.takeKan(Pplayer,rc);                                 //~v@@@I~//~v@@7R~
//        return true;                                               //~v@@@I~//~v@@7R~
//    }                                                              //~v@@@I~//~v@@7R~
//    //*************************************************************************//~v@@@M~//~v@@7R~
//    public boolean reach(int Pplayer)                              //~v@@@R~//~v@@7R~
//    {                                                              //~v@@@M~//~v@@7R~
//        if (Dump.Y) Dump.println("UserAction.reach player="+Pplayer);//~v@@@I~//~v@@7R~
////        if (!players.isYourTurn(actionID,Pplayer))               //~v@@@R~//~v@@7R~
////            return ;                                             //~v@@@R~//~v@@7R~
//        if (!players.reach(Pplayer))                               //~v@@@M~//~v@@7R~
//            return false;                                          //~v@@@R~//~v@@7R~
//        river.reach(Pplayer);                                      //~v@@@M~//~v@@7R~
//        return true;                                               //~v@@@I~//~v@@7R~
//    }                                                              //~v@@@M~//~v@@7R~
//    //*************************************************************************//~v@@@I~//~9503R~
//    public boolean complete(int Pplayer)                           //~v@@@R~//~9503R~
//    {                                                              //~v@@@I~//~9503R~
//        if (true)                                                //~9503R~
//        return false;   //TODO test                                //~v@@@I~//~9503R~
//        if (Dump.Y) Dump.println("UserAction.complete player="+Pplayer);//~v@@@I~//~9503R~
////        if (!players.isYourTurn(actionID,Pplayer))               //~v@@@R~//~9503R~
////            return;                                              //~v@@@R~//~9503R~
//        Status.setGameStatusGameCompleteReq(Pplayer);              //~v@@@I~//~9503R~
//        int rc=players.complete(Pplayer);                          //~v@@@R~//~9503R~
//        AG.aHands.complete(Pplayer,rc);  //draw taken tile when taken//~v@@@R~//~9503R~
//        AG.aRiver.complete(Pplayer,rc);  //draw discarded tile when river taken//~v@@@R~//~9503R~
//        AG.aDiceBox.setWaitingResponseAll();                       //~v@@@I~//~9503R~
//        AG.aGMsg.drawMsgbar(R.string.Msg_AcceptComplete); //TDO test//~v@@@I~//~9503R~
//        Status.setGameStatus(GS_COMPLETION_ACCEPTING);             //~v@@@I~//~9503R~
//        return true;                                               //~v@@@I~//~9503R~
//    }                                                              //~v@@@I~//~9503R~
//    //*************************************************************************//~v@@@I~//~v@@7R~
//    public boolean open(int Pplayer)                                  //~v@@@I~//~v@@7R~
//    {                                                              //~v@@@I~//~v@@7R~
//        if (Dump.Y) Dump.println("UserAction.open player="+Pplayer);//~v@@@I~//~v@@7R~
////        if (!players.isYourTurn(actionID,Pplayer))                 //~v@@@I~//~v@@7R~
////          return false;                                          //~v@@@R~//~v@@7R~
//        players.open(Pplayer);                                     //~v@@@I~//~v@@7R~
//        AG.aHands.open(Pplayer);  //show all tiles in hands     //~v@@@I~//~v@@7R~
//        return true;                                               //~v@@@I~//~v@@7R~
//    }                                                              //~v@@@I~//~v@@7R~
//    //*************************************************************************//~v@@@R~
//    //*at discard, set takable some seconds later                //~v@@@R~
//    //*************************************************************************//~v@@@R~
//    public void postNextPlayer(int Pplayer,TileData Ptd)         //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UserAction.postNextPlayer player="+Pplayer);//~v@@@R~
//        int player=players.nextPlayer(Pplayer);//send to next player//~v@@@R~
//        UAD.postDelayed(OperationSetting.delayTake,GCM_NEXT_PLAYER,player,Ptd);//~v@@@R~
//    }                                                            //~v@@@R~
//    //*************************************************************************//~v@@@R~
//    // server;fromUADelayed, client:actionReceived-actionOnClient//~v@@@R~
//    //*************************************************************************//~v@@@R~
//    public boolean nextPlayer(boolean PswServer,int Pplayer)     //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UserAction.nextPlayer swServer="+PswServer+",player="+Pplayer);//~v@@@R~
////      boolean swShadow=!accounts.isDrawThisDevice(Pplayer);    //~v@@@R~
////      players.setNextPlayer(Pplayer,swShadow);                 //~v@@@R~
////      players.setNextPlayer(Pplayer);                          //~v@@@R~
//        int player=players.prevPlayer(Pplayer);                  //~v@@@R~
//        boolean swShadow=Pplayer!=PLAYER_YOU;                    //~v@@@R~
//        players.setNextPlayer(player,swShadow); //color of waite to take//~v@@@R~
////        Robot r=Accounts.getRobotPlayer(Pplayer)               //~v@@@R~
////        if (r!=null)                                           //~v@@@R~
////        {                                                      //~v@@@R~
////            if (Dump.Y) Dump.println("UserAction.nextPlayer is robot");//~v@@@R~
////        }                                                      //~v@@@R~
//        msgDataToClient=makeMsgDataToClient(Pplayer);            //~v@@@R~
//        return true;                                             //~v@@@R~
//    }                                                            //~v@@@R~
	//*************************************************************************//~v@@@I~
    public void selectionErr(int Perrid)                           //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.selectionErr errid="+Perrid);//~v@@@I~
        String msg=null;                                                //~v@@@I~//~v@@7R~
        switch(Perrid)                                             //~v@@@I~
        {                                                          //~v@@@I~
        case -1:                                                   //~v@@@I~
        	msg=Utils.getStr(R.string.Err_CouldNotMakePair);       //~v@@@I~
        	break;                                                 //~v@@@I~
        case 1:                                                    //~v@@@I~
        	msg=Utils.getStr(R.string.Err_AmbiguousMakePair);      //~v@@@I~
        	break;                                                 //~v@@@I~
        case 2:                                                    //~v@@7I~
        	msg=Utils.getStr(R.string.Err_AmbiguousMakePairKan);   //~v@@7I~
        	break;                                                 //~v@@7I~
        }                                                          //~v@@@I~
        if (msg!=null)                                             //~v@@7I~
        	acaction.showErrmsg(0,msg);                                 //~v@@@I~//~v@@7M~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@7I~
    private boolean actionWait(int Pmsgid,boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@7R~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.actionWait msgid="+Pmsgid);//~v@@7R~
        boolean rc=UADL.actionWait(Pmsgid,PswServer,swReceived,Pplayer,PintParm);//~v@@7R~
        return rc;                                                 //~v@@7R~
    }                                                              //~v@@7I~
//    //*************************************************************************//~9B17I~//~9C05R~
//    private boolean actionWait2(int Pmsgid,boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9B17I~//~9C05R~
//    {                                                              //~9B17I~//~9C05R~
//        if (Dump.Y) Dump.println("UserAction.actionWait2 msgid="+Pmsgid+",swServer="+PswServer+",swReceived="+PswReceived+",player="+Pplayer);//~9B17I~//~9C05R~
//        boolean rc=UADL.actionWait2(Pmsgid,PswServer,swReceived,Pplayer,PintParm);//~9B17I~//~9C05R~
//        return rc;                                                 //~9B17I~//~9C05R~
//    }                                                              //~9B17I~//~9C05R~
	//*************************************************************************//~v@@7I~
    private boolean isWaitingOtherThanYou(int Pplayer)             //~v@@7R~
    {                                                              //~v@@7I~
//  	int rc=AG.aUADelayed.isWaiting(Pplayer);                   //~v@@7R~//~9629R~
    	int rc=UADL.isWaiting(Pplayer);                            //~9629I~
        if (rc>1)                                                  //~v@@7R~
        	acaction.showErrmsg(0,R.string.Info_PleaseWait);       //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.isWaitingOtherThanYou player="+Pplayer+",rc="+rc);//~v@@7R~
    	return rc>1;                                                 //~v@@7I~
    }                                                              //~v@@7I~
	//*************************************************************************//~9629I~
    private boolean isWaitingOtherThanYou(int PactionID,int Pplayer)//~9629I~
    {                                                              //~9629I~
//  	int rc=AG.aUADelayed.isWaiting(Pplayer);                   //~9629I~
    	String err=UADL.isWaiting(PactionID,Pplayer);              //~9629I~
        if (err!=null)                                             //~9629I~
        	acaction.showErrmsg(0,err);                            //~9629I~
        if (Dump.Y) Dump.println("UserAction.isWaitingOtherThanYou actionID="+PactionID+",player="+Pplayer+",err="+err);//~9629I~
    	return err!=null; 	                                       //~9629I~
    }                                                              //~9629I~
	//*************************************************************************//~v@@7I~
    public void setNoMsgToClient()                                 //~v@@7I~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.setNoMsgToClient");   //~v@@7I~
        msgDataToClient=msgNoData;                                 //~v@@7R~
    }                                                              //~v@@7I~
	//*************************************************************************//~v@@7I~
    public void setNoMsgToServer()                                 //~v@@7I~
    {                                                              //~v@@7I~
        if (Dump.Y) Dump.println("UserAction.setNoMsgToServer");   //~v@@7I~
        msgDataToServer=msgNoData;                                 //~v@@7I~
    }                                                              //~v@@7I~
    //*******************************************************      //~9522I~
    public static int[] parseStrParm(String[] PstrParm)            //~9522R~
    {                                                              //~9522I~
        int[] pout;                                                //~9522I~
        if (PstrParm!=null && PstrParm[0]!=null)                   //~9522I~
        {                                                          //~9522I~
        	pout=ACAction.parseAppData(PstrParm[0]);               //~9522I~
	        if (Dump.Y) Dump.println("UAEndGame.parseAppData out="+Arrays.toString(pout));//~9522I~
        }                                                          //~9522I~
        else                                                       //~9522I~
        	pout=new int[0];                                       //~9522I~
		return pout;                                               //~9522I~
    }                                                              //~9522I~
    //*******************************************************      //~9627I~
    private boolean  delayedGMsgAllEswn;                           //~9627I~
    private int delayedGMsgPlayer;                                 //~9627I~
    private String delayedGMsgTxt;                                 //~9627I~
    //*******************************************************      //~9627I~
    public void setDelayedGMsg(int Popt,boolean PswAllEswn,int Pplayer,String Ptxt)//~9627R~
    {                                                              //~9627I~
	    if (Dump.Y) Dump.println("UserAction.setDelayedGMsg alleswn="+PswAllEswn+",player="+Pplayer+",txt="+Ptxt);//~9627I~
        delayedGMsgAllEswn=PswAllEswn;                             //~9627I~
        delayedGMsgPlayer=Pplayer;                                 //~9627I~
        delayedGMsgTxt=Ptxt;                                       //~9627I~
    }                                                              //~9627I~
    //*******************************************************      //~9627I~
    private void showDelayedGMsg()                                 //~9627I~//~0305R~
    {                                                              //~9627I~
	    if (Dump.Y) Dump.println("UserAction.showDelayedGMsg alleswn="+delayedGMsgAllEswn+",player="+delayedGMsgPlayer+",txt="+delayedGMsgTxt);//~9627I~
        if (delayedGMsgAllEswn)                                    //~9627I~
			showInfoAllEswn(0/*opt*/,delayedGMsgPlayer,delayedGMsgTxt);//~9627I~
        else                                                       //~9627I~
			GMsg.drawMsgbar(delayedGMsgTxt);                     //~9627I~
        delayedGMsgTxt=null;                                       //~9628I~
    }                                                              //~9627I~
    //*******************************************************      //~9629I~
    private void resetGmsg(int PactionID)                           //~9629I~//~0226R~
    {                                                              //~9629I~
	    if (Dump.Y) Dump.println("UserAction.resetGmsg actionID="+PactionID);//~9629I~
        switch(PactionID)                                          //~9629I~
        {                                                          //~9629I~
	 	case GCM_NEXT_PLAYER:                                      //~9629I~
	 	case GCM_NEXT_PLAYER_PONKAN:                               //~9629I~
	 	case GCM_TIMEOUT_STOPAUTO:                                 //~9B29I~
	 	case GCM_2TOUCH:                                           //~9C04I~
	    	if (Dump.Y) Dump.println("UserAction.resetGmsg bypass reset");//~9629I~
        	break;                                                 //~9629I~
        default:                                                   //~9629I~
			AG.aGMsg.reset();                                      //~9629I~
        }                                                          //~9629I~
    }                                                              //~9629I~
	//*************************************************************************//~9A12I~
	//*from CompleteDlg                                            //~9A13I~
	//*************************************************************************//~9A13I~
    public void resetComplete(boolean PswServer,boolean PswReceived,int[] PcompIndexNextPlayer)          //~9A12R~//~9A13R~
    {                                                              //~9A12I~
        if (Dump.Y) Dump.println("UserAction.resetComplete swServer="+PswServer+",swReceived="+PswReceived+",actionBeforeRon="+AG.aPlayers.actionBeforeRon);//~9A12R~//~9A13R~
        AG.aGC.suspendGameResetComplete();                         //~9A12I~
        prevActionID=AG.aPlayers.actionBeforeRon;                  //~9A12I~
	    currentActionID=prevActionID;                              //~9A13I~
	    AG.aPlayers.swLastActionIsDiscard=prevActionID==GCM_DISCARD;//~9A13I~
        if (Dump.Y) Dump.println("UserAction.resetComplete Players.swLastActionIsDiscard="+AG.aPlayers.swLastActionIsDiscard+",currentActionID="+currentActionID);//~9A13I~
        UAR.resetComplete(PswServer,PswReceived,PcompIndexNextPlayer);                   //~9A12R~//~9A13M~
    }                                                              //~9A12I~
}//class                                                           //~v@@@R~
