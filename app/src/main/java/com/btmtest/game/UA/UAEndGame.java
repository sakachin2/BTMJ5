//*CID://+va60R~: update#= 720;                                    //~va60R~
//**********************************************************************//~v101I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~v@@@R~
import android.graphics.Point;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.dialog.DrawnDlgHW.*;                     //~9306I~

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.DrawnDlgHW;                              //~9307R~
import com.btmtest.dialog.DrawnDlgLast;
import com.btmtest.dialog.DrawnReqDlgLast;                         //~9307R~
import com.btmtest.dialog.DrawnReqDlgHW;
import com.btmtest.dialog.OKNGDlg;
import com.btmtest.game.Accounts;
import com.btmtest.game.RA.RoundStat;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.game.Status;
import com.btmtest.game.UserAction;

import java.util.Arrays;

public class UAEndGame //extends Handler                           //~v@@@R~//~9608R~
{                                                                  //~0914I~
	public  static final int ENDGAME_NONE=0;                       //~v@@@I~
	public  static final int ENDGAME_DRAWN_LAST=1;                 //~v@@@I~//~9307I~
	public  static final int ENDGAME_DRAWN_LAST_RESPONSE=2;             //~v@@@I~//~9307I~
	public  static final int ENDGAME_DRAWN_LAST_CONFIRM=3;         //~9307I~
	public  static final int ENDGAME_DRAWN_LAST_CONFIRM_REQUEST=4; //~9310I~
	public  static final int ENDGAME_DRAWN_LAST_CONFIRM_RESPONSE=5;//~9307I~//~9310R~
	public  static final int ENDGAME_DRAWN_LAST_CONFIRMED=6;       //~9307I~//~9310R~
	public  static final int ENDGAME_DRAWN_HALFWAY_REQUEST=11;             //~v@11I~//~v@@@M~//~9307R~//~9705R~
	public  static final int ENDGAME_DRAWN_HALFWAY_RESPONSE=12;    //~v@@@I~//~9307R~
	public  static final int ENDGAME_DRAWN_HALFWAY_CONFIRM=13;     //~v@@@I~//~9307R~
	public  static final int ENDGAME_DRAWN_HALFWAY_CONFIRM_REQUEST=15;//~v@@@I~//~9307R~//~9311R~//~9B14R~
	public  static final int ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE=16;//~9B14I~
	public  static final int ENDGAME_DRAWN_HALFWAY_CONFIRMED=17;    //~v@@@I~//~9307R~//~9311R~//~9B14R~
//  public  static final int ENDGAME_DRAWN_HALFWAY_RESPONSE_DELAYED=30;//~9518R~//~9519R~
//  public  static final int ENDGAME_DRAWN_HALFWAY_DELAYED         =31;//~9518I~//~9519R~
//********************************************************************//~9307I~
//* ReqDlgHW Send:--(HW_RESPONSE)-->Server--(HW_CONFIRM)-->Dealer  //~9307I~
//*                                                        Send    //~9307I~
//*                                                          |     //~9307I~
//* Client<--(HW_CONFIRMED)--Server<--(HW_CONFIRM_RESPONSE)--+     //~9307I~
//**********************                                           //~9307I~
//* UADelayed(On Server)-->DRAWN_LAST-->all Client:Send --(LAST_RESPONSE)-->Server//~9307I~
//*                                                                         when receaved All//~9307I~
//*                                                                         --(LAST_CONFIRM)-->Dealer//~9307I~
//*                                                                                              send//~9307I~
//* Server<---(CONFIRM_RESP)--------Client<--(CONFIRMED_REQ)--Server<--(LAST_CONFIRM_REQ)---------+//~9307I~//~9311R~
//* +--->(CONFIRMED)-->Dealer                                      //~9311I~
//********************************************************************//~9307I~
                                                                   //~v@@@I~
	public  static final int EGDR_NONE     =0;                     //~v@@@R~
	public  static final int EGDR_PENDING  =1;                     //~v@@@R~
	public  static final int EGDR_PENDINGNO=2;                     //~v@@@R~
	public  static final int EGDR_MANGAN   =3;                     //~v@@@I~
	public  static final int EGDR_4REACH   =4;                     //~v@@@R~
	public  static final int EGDR_4WIND    =5;                     //~v@@@R~
	public  static final int EGDR_4KAN     =6;                     //~v@@@R~
	public  static final int EGDR_3RON     =7;                     //~v@@@R~
	public  static final int EGDR_99TILE   =8;                     //~v@@@R~
//  public  static final int EGDR_ERROR    =9;                     //~v@@@R~//~9425R~
	public  static final int EGDR_OTHER    =10;                    //~9309I~//~9423R~
	public  static final int EGDR_MANGAN_PENDING   =11;                    //~v@@@R~//~9309R~//~9423I~
//  public  static final int EGDR_MANGAN_PENDING_BYSETTING   =12;//TODO delete this  //~9423I~//~9505R~//~9506R~
	public  static final int EGDR_MANGAN_RON=13;                //by setting like as Ron//~9505I~
	public  static final int EGDR_PENDING_REACH=14;                //~9521I~
	public  static final int EGDR_NORMAL    =50; //ron              //~9316I~//~9318R~
	public  static final int EGDR_DRAWN_LAST=51;                   //~9321R~
	public  static final int EGDR_DRAWN_HW  =52;                   //~9321R~
	public  static final int EGDR_RESET     =59;                  //~9703I~//~9704R~
	public  static final int EGDR_MINUSSTOP =60;                   //~9612I~
	public  static final int EGDR_FINALLAST_NOTPENDING =61;        //~9504I~
	public  static final int EGDR_FINALLAST_DRAWNMANGAN=62;        //~9504I~
	public  static final int EGDR_FINALLAST_HW         =63;        //~9504I~
	public  static final int EGDR_FINALLAST_EVENMINUS  =64;        //~9520I~
	public  static final int EGDR_FINALLAST_DECLARED   =65;        //~9522I~//~9523R~
	public  static final int EGDR_NG       =99;                    //~v@@@I~//~9311I~
	public  static final int EGDR_OK       =100;                   //~9311R~
	public  static final int EGDR_MASK      =0xff;                 //~9426I~
	public  static final int EGDR_SHIFT_ESWN=8;                    //~9426R~
//  public  static final int EGDR_SHIFT_NEXTGAME=8;                //~9426R~
                                                                   //~9318I~
	public  static final int NGTP_GAMEOVER   =-1;                     //~9401I~//~9526R~
	public  static final int NGTP_CONTINUE   =0;                       //~9318I~//~9526R~
	public  static final int NGTP_NEXT       =1;                       //~9318I~//~9526R~
//  public  static final int NGTP_NEXTROUND  =2; //additinal round //~9526I~//~9703R~//~9A12R~
    public  static final int NGTP_NEXTPLAYER =2; //continue this game//~9A12I~
	public  static final int NGTP_RESET      =3; //retry the round //~9704I~
    public  static final int NGTP_NEXTROUND  =4; //additinal round //~9A12I~
	public  static final int NGTP_AGREED     =9;                   //~9612I~
                                                                   //~9311I~
    public static final int LASTDT_REASON=1;                      //~9311I~
    public static final int LASTDT_DIALOG=2;                      //~9311I~
    public static final int LASTDT_OKNG=3;                        //~9311I~
                                                                   //~v@@@I~
	private Status status;                                         //~v@@@R~
    public DrawnReqDlgLast reqDlgLast;                                      //~v@@@I~//~9307R~
    public DrawnDlgHW dlgConfirmHW;                                //~v@@@I~
    public DrawnDlgLast dlgConfirmLast;                                   //~9307I~//~9308R~
    private int[] reasonResponse=new int[PLAYERS];                 //~v@@@I~
    private int[] confirmResponseLast=new int[PLAYERS];            //~9308I~
    private int[] reasonResponseHW=new int[PLAYERS];               //~v@@@I~
    private int[] dialogDataLast=new int[PARMPOS_DRAWN_DIALOGDATA_CTR];//~9310R~
    private boolean[] swsError= new boolean[PLAYERS];               //~9426I~
    private UserAction UA;                                         //~v@@@I~
    private boolean swResponsedAllPending,swFoundNG,swResponsedAllConfLast;                      //~v@@@R~//~9307R~//~9308R~//~9311R~
    private int reasonLast;                                        //~9308R~
    private int eswnRequester;                                     //~9705I~
//  private boolean swTestHW2=true;	//TODO                         //~9518I~//~9519R~
    private boolean swSuspend;                                     //~0306I~
//*************************                                        //~v@@@I~
	public UAEndGame()                                             //~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UAEndGame Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        AG.aUAEndGame=this;                                        //~v@@@R~
        UA=AG.aUserAction;                                         //~v@@@M~
        status=AG.aStatus;                                         //~v@@@I~
    }                                                              //~0914I~//~v@@@R~
//*************************                                        //~v@@@I~
	public void newGame()                                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAEndGame newGame");             //~v@@@I~
	    resetStatus();                                             //~9311I~
    }                                                              //~v@@@I~
//*************************                                        //~9311I~
    private void resetStatus()                                     //~9311I~
    {                                                              //~9311I~
        if (Dump.Y) Dump.println("UAEndGame.resetStatus");         //~9311I~
        Arrays.fill(reasonResponse,ENDGAME_NONE);                  //~v@@@I~//~9311M~
//      Arrays.fill(reasonResponseHW,EGDR_NONE);                //~v@@@I~//~9305R~//~9311M~//~9703R~
    	resetResponseHW();                                         //~9703I~
        Arrays.fill(confirmResponseLast,EGDR_NONE);                //~9308I~//~9311M~
        Arrays.fill(dialogDataLast,0);                             //~0309I~
        if (Dump.Y) Dump.println("UAEndGame.resetStatus dialogDataLast="+Arrays.toString(dialogDataLast));//~0331I~
   		swResponsedAllPending=false;                          //~9311I~
   		swResponsedAllConfLast=false;                         //~9311I~
   		swSuspend=false;                                           //~0306I~
    }                                                              //~9311I~
	//*************************************************************************//~v@@@I~
	//*normally sendtoserver for client msg                        //~v@@@I~
	//*but send ENDGAME_HALFWAY for dialog on client               //~v@@@I~
	//*************************************************************************//~v@@@I~
//  public boolean selectInfo(boolean PisServer,int Pplayer,int[] PintParm)//~v@@@I~//~9426R~
    public boolean selectInfo(boolean PisServer,int Pplayer,int[] PintParm,String[] PstrParm)//~9426I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAEndGame.selectInfo isServer="+PisServer+",player="+Pplayer+",intp="+Arrays.toString(PintParm)+",strParm="+Arrays.toString(PstrParm));//~v@@@I~//~9306R~//~9426R~
        if (PisServer)                                             //~v@@@I~
        	return true;                                           //~v@@@I~
    	int type=PintParm[PARMPOS_DRAWN_TYPE];                     //~v@@@I~
    	int reason=PintParm[PARMPOS_DRAWN_REASON]; //typeNextGame+reason//~v@@@R~//~9308R~
        if (Dump.Y) Dump.println("UAEndGame.selectInfo type="+type+",reason="+reason);//~9306R~
        switch (type)                                              //~9307I~
        {                                                          //~9307I~
                                                                   //~9307I~
        case ENDGAME_DRAWN_HALFWAY_RESPONSE:	//!dialog response//~v@@@R~//~9307R~
            if (!PisServer)                                        //~v@@@I~
            {                                                      //~v@@@I~
//      		UA.sendToServer(GCM_ENDGAME_DRAWN,Pplayer,ENDGAME_DRAWN_HALFWAY,reason,0);//~v@@@R~//~9426R~
				String strErr=DrawnDlgHW.makeMsgDataBoolean(swsError);//~9426I~
//      		UA.sendToServer(GCM_ENDGAME_DRAWN,Pplayer,ENDGAME_DRAWN_HALFWAY,reason,0,strErr);//~9426I~//~9705R~
        		UA.sendToServer(GCM_ENDGAME_DRAWN,Pplayer,ENDGAME_DRAWN_HALFWAY_REQUEST,reason,0,strErr);//~9705I~
        		return false;	//do not send normal msg to server //~v@@@R~
            }                                                      //~v@@@I~
            break;                                                 //~9307I~
        case ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE:	//dialog response//~v@@@I~//~9307R~//~9B14R~
        case ENDGAME_DRAWN_HALFWAY_CONFIRM_REQUEST:	    //dialog send//~9B14I~
            if (!PisServer)                                        //~v@@@I~
            {                                                      //~v@@@I~
//              UA.sendToServer(GCM_ENDGAME_DRAWN,Pplayer,ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE,reason,0);//~v@@@R~//~9305R~//~9426R~
//              UA.sendToServer(GCM_ENDGAME_DRAWN,Pplayer,ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE,reason,0,PstrParm[0]);//~9426I~//~9B14R~
//              UA.sendToServer(GCM_ENDGAME_DRAWN,Pplayer,type,reason,0,PstrParm[0]);//~9B14I~//~0306R~
				int suspendid=PintParm[PARMPOS_DRAWN_CONFREQ_SUSPEND];//~0306I~
                swSuspend=suspendid!=0;                            //~0306I~
                UA.sendToServer(GCM_ENDGAME_DRAWN,Pplayer,type,reason,suspendid,PstrParm[0]);//~0306I~
                return false;   //do not send normal msg to server //~v@@@R~
            }                                                      //~v@@@I~
            break;                                                 //~9307I~
        case ENDGAME_DRAWN_LAST_RESPONSE:	//!dialog response     //~9307I~
            if (!PisServer)                                        //~9307I~
            {                                                      //~9307I~
        		UA.sendToServer(GCM_ENDGAME_DRAWN,Pplayer,ENDGAME_DRAWN_LAST_RESPONSE,reason,0);//~9307I~
        		return false;	//do not send normal msg to server //~9307I~
            }                                                      //~9307I~
            break;                                                 //~9307I~
        case ENDGAME_DRAWN_LAST_CONFIRM_RESPONSE:	//!dialog response//~9308I~
            if (!PisServer)                                        //~9308I~
            {                                                      //~9308I~
        		UA.sendToServer(GCM_ENDGAME_DRAWN,Pplayer,ENDGAME_DRAWN_LAST_CONFIRM_RESPONSE,reason,0);//~9308I~//~9426R~
        		return false;	//do not send normal msg to server //~9308I~
            }                                                      //~9308I~
            break;                                                 //~9308I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UAEndGame.selectInfo return true");//~9310I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@M~
	//*from UserAction:GCM_ENDGAME_DRAWN                           //~v@@@R~
	//*************************************************************************//~v@@@I~
    public boolean drawn(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm,String[] PstrParm)//~v@@@R~//~9310R~
    {                                                              //~v@@@M~
    	int type=PintParm[PARMPOS_DRAWN_TYPE];    //1             //~v@@@R~//~0314R~
    	int reason=PintParm[PARMPOS_DRAWN_REASON];   //2           //~v@@@I~//~9308R~//~0314R~
    	int old=status.endGameType;                                //~v@@@R~
	    int typeNextGame=0;                                        //~9305R~
        int realPlayer;                                            //~9308I~
        boolean rc2;                                               //~9518I~
		Point p;                                                   //~9308I~
    //*****************************:                                                               //~v@@@I~//~9308R~
    	if (Dump.Y) Dump.println("UAEndGame.drawn swServer="+PswServer+",swReceived="+PswReceived+",player="+Pplayer+",type(msgid)="+type+",reason="+reason+",old="+old+",intp="+Arrays.toString(PintParm)+",strParm="+Arrays.toString(PstrParm));//~v@@@R~//~9305R~//~9426R~//~9610R~
        switch(type)                                               //~v@@@I~
        {                                                          //~v@@@I~
        case ENDGAME_DRAWN_HALFWAY_RESPONSE:	//by request dialog response at server(client treated by selectInfo)//~v@@@R~//~9305R~
  		  	resetResponseHW();                                     //~9703I~
            rc2=sendOpenOnlyToOtherClient(Pplayer,reason);         //~9518R~
//          if (rc2)                                               //~9518I~//~9519R~
//          if (rc2 && swTestHW2)                                  //~9519R~
//         		sendToCurrentDealerDelayed(PswServer,Pplayer,ENDGAME_DRAWN_HALFWAY_RESPONSE_DELAYED,reason); //send CONFIRM if dealer is on client//~9518I~//~9519R~
//          else                                                   //~9518I~//~9519R~
           		sendToCurrentDealer(PswServer,Pplayer,reason); //send CONFIRM if dealer is on client//~9307R~//~9518R~
          	UA.setNoMsgToClient();                                 //~v@@@I~
            break;                                                 //~v@@@I~
        case ENDGAME_DRAWN_HALFWAY_REQUEST:	//by client BTIIO msg at server at client "Send" button on ReqDlgHW//~9705I~
//      	Arrays.fill(reasonResponseHW,EGDR_NONE);               //~9608I~//~9703R~
    		if (Dump.Y) Dump.println("UAEndGame.drawn HALFWAY_REQUEST eswnRequester="+eswnRequester);//~9705I~//~0306R~
  		  	resetResponseHW();                                     //~9703I~
            rc2=sendOpenOnlyToOtherClient(Pplayer,reason);         //~9518R~
//          sendToCurrentDealer(PswServer,Pplayer,reason); //send CONFIRM if dealer is on client//~9307R~//~9426R~
		    setResponseHWError(PintParm,PARMPOS_DRAWN_HW_ERROR);         //~9426I~
//          if (rc2)                                               //~9518I~//~9519R~
//          if (rc2 && swTestHW2)                                  //~9519R~
//         		sendToCurrentDealerDelayed(PswServer,Pplayer,ENDGAME_DRAWN_HALFWAY_DELAYED,reason); //send CONFIRM if dealer is on client//~9518I~//~9519R~
//          else                                                   //~9518I~//~9519R~
	            sendToCurrentDealer(PswServer,Pplayer,reason,swsError); //send CONFIRM if dealer is on client//~9426I~//~9518R~
          	UA.setNoMsgToClient();                                 //~v@@@I~
            break;                                                 //~v@@@I~
        case ENDGAME_DRAWN_HALFWAY_CONFIRM:	//by client BTIIO msg at current dealer on client//~v@@@R~//~9307R~
	    	typeNextGame=PintParm[PARMPOS_DRAWN_NEXTGAME];         //~9305I~
	    	eswnRequester=PintParm[PARMPOS_DRAWN_ESWNREQUESTER];   //~9705I~
//      	Arrays.fill(reasonResponseHW,EGDR_NONE);               //~9608I~//~9703R~
  		  	resetResponseHW();                                     //~9703I~
    		if (Dump.Y) Dump.println("UAEndGame.drawn HALFWAY_CONFIRM reasonResponseHW="+Arrays.toString(reasonResponseHW));//~9608I~
//  	    showDrawnHWResultDlg(reason,typeNextGame);                          //~v@@@R~//~9518R~
//      	if (swTestHW)                                          //~9518R~
//  			showDrawnHWResultDlg(reason,typeNextGame);         //~9518I~//~9705R~
    			showDrawnHWResultDlg(reason,typeNextGame,eswnRequester);//~9705I~
//          else                                                   //~9518R~
//  	    showDrawnHWResultDlgReason(type,reason,typeNextGame);  //~9518R~
//no need   if (PswServer)                                         //~9307I~
//            	UA.setNoMsgToClient();                             //~9307I~
            break;                                                 //~v@@@I~
        case ENDGAME_DRAWN_HALFWAY_CONFIRM_REQUEST:	//on server by requester's DrawnDlGHW.send button//~9B14I~
	    	int suspendid;                                         //~0306I~
        	if (PswReceived)	//thru BTIO                        //~9B14I~
            {                                                      //~9B14I~
		    	suspendid=PintParm[PARMPOS_DRAWN_CONFREQ_SUSPEND]; //~0306I~
                typeNextGame=PintParm[PARMPOS_DRAWN_CONF_RESP_NEXTGAME_RCV];//~9B14I~
            	eswnRequester=PintParm[PARMPOS_DRAWN_CONF_RESP_ESWNREQUESTER_RCV];//~9B14I~
                setResponseHW(Pplayer,reason,PintParm,PARMPOS_DRAWN_CONF_RESP_ERROR_RCV);//~9B14I~
            }                                                      //~9B14I~
            else                                                   //~9B14I~
            {                                                      //~9B14I~
		    	suspendid=PintParm[PARMPOS_DRAWN_CONFREQ_SUSPEND]; //~0306I~
    			int[] strParm=pasreStrParm(PstrParm);              //~9B14I~
            	typeNextGame=strParm[PARMPOS_DRAWN_CONF_RESP_NEXTGAME];//~9B14I~
            	eswnRequester=strParm[PARMPOS_DRAWN_CONF_RESP_ESWNREQUESTER];//~9B14I~
            	setResponseHW(Pplayer,reason,strParm,PARMPOS_DRAWN_CONF_RESP_ERROR);//~9B14I~
            }                                                      //~9B14I~
            swSuspend=suspendid!=0;                                //~0306I~
	        if (Dump.Y) Dump.println("UAEndGame.drawn CONF_REQUEST typeNextGame="+typeNextGame+",eswnRequester="+eswnRequester+",suspendid="+suspendid);//~9B14I~//~0306R~
            if (PswServer)                                         //~9B14I~
//              sendConfirmRequestHWToOtherClientAll(type,Pplayer,reason,typeNextGame,eswnRequester,swsError); //HW_CONFIRMED//~9B14I~//~0306R~
                sendConfirmRequestHWToOtherClientAll(type,Pplayer,reason,typeNextGame,eswnRequester,suspendid,swsError); //HW_CONFIRMED//~0306I~
            showDrawnHWResultDlg(reason,typeNextGame,swsError,eswnRequester);   //update//~9B14R~
            UA.setNoMsgToClient();                                 //~9B14I~
            break;                                                 //~9B14I~
        case ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE:	//server   //~v@@@R~
            if (isUpdateAfterSend())                               //~0314I~
            {                                                      //~0314I~
		        UA.setNoMsgToClient();                             //~0314I~
                break;                                             //~0314I~
         	}                                                      //~0314I~
        	if (PswReceived)	//thru BTIO                            //~9426I~
            {                                                      //~9426I~
                typeNextGame=PintParm[PARMPOS_DRAWN_CONF_RESP_NEXTGAME_RCV];//~9426R~
            	eswnRequester=PintParm[PARMPOS_DRAWN_CONF_RESP_ESWNREQUESTER_RCV];//~9705I~
                setResponseHW(Pplayer,reason,PintParm,PARMPOS_DRAWN_CONF_RESP_ERROR_RCV);//~9426R~
            }                                                      //~9426I~
            else                                                   //~9426I~
            {                                                      //~9426I~
    			int[] strParm=pasreStrParm(PstrParm);              //~9426R~
            	typeNextGame=strParm[PARMPOS_DRAWN_CONF_RESP_NEXTGAME];//~9426R~
            	eswnRequester=strParm[PARMPOS_DRAWN_CONF_RESP_ESWNREQUESTER];//~9705I~
            	setResponseHW(Pplayer,reason,strParm,PARMPOS_DRAWN_CONF_RESP_ERROR);                         //~v@@@R~//~9426R~
            }                                                      //~9426I~
	        if (Dump.Y) Dump.println("UAEndGame.drawn CONF_RESP typeNextGame="+typeNextGame+",eswnRequester="+eswnRequester);//~9705I~
//          if (chkResponseHW())	//all responsed                                   //~9306I~//~9426R~//~9609R~
//              drawnHWConfirmed(); //confirmed msg to all         //~9306I~//~9609R~
//          if (PswServer)                                         //~9B14R~
//          {                                                      //~9B14R~
        	if (AG.aAccounts.getCurrentDealerReal()==PLAYER_YOU)	//server is requester//~9306I~
            {                                                      //~9306I~
            	if (Dump.Y) Dump.println("UAEndGame.drawn server is requester");//~9306I~
//          	showDrawnHWResultDlg(reason,typeNextGame);	//update//~9306I~//~9311R~
//              if (Pplayer==PLAYER_YOU)	//dialog response on server//~9306I~//~9B14R~
//              {                                                  //~9306I~//~9B14R~
	            	if (Dump.Y) Dump.println("UAEndGame.drawn msg from dialog");//~9306I~
    				showDrawnHWResultDlg(reason,typeNextGame,swsError,eswnRequester);	//update//~9705I~
//              }                                                  //~9311I~//~9B14R~
            }                                                      //~9306I~
            else    //server is not Dealer                         //~9306I~//~9311R~
            {                                                      //~9306I~
            	if (Dump.Y) Dump.println("UAEndGame.drawn server is NOT requester");//~9311I~
//          	if (Pplayer==PLAYER_YOU)	//dialog response on server//~9306I~//~9B14R~
//              {                                                  //~9306I~//~9B14R~
//              	if (Dump.Y) Dump.println("UAEndGame.drawn server is NOT requester and msg from Dialog");//~9306I~//~9B14R~
//                  sendConfirmedToCurrentDealerHW(Pplayer,reason,typeNextGame); //HW_CONFIRMED //~9306I~//~9308R~//~9311R~//~9426R~
//                  sendConfirmedToCurrentDealerHW(Pplayer,reason,typeNextGame,swsError); //HW_CONFIRMED//~9426I~//~9705R~
                    sendConfirmedToCurrentDealerHW(Pplayer,reason,typeNextGame,eswnRequester,swsError); //HW_CONFIRMED//~9705I~
//              }                                                  //~9306I~//~9B14R~
            }                                                      //~9306I~
            UA.setNoMsgToClient();                                 //~v@@@R~
            break;                                                 //~v@@@I~
        case ENDGAME_DRAWN_HALFWAY_CONFIRMED:	//all device       //~v@@@I~
            if (isUpdateAfterSend())                               //~0314M~
            {                                                      //~0314M~
		        UA.setNoMsgToClient();                             //~0314M~
                break;                                             //~0314M~
         	}                                                      //~0314M~
	    	typeNextGame=PintParm[PARMPOS_DRAWN_NEXTGAME];         //~9305I~
	    	eswnRequester=PintParm[PARMPOS_DRAWN_ESWNREQUESTER];   //~9705I~
        	System.arraycopy(PintParm,PARMPOS_DRAWN_RESPSTAT,reasonResponseHW,0,PLAYERS);//~v@@@I~
		    setResponseHWError(PintParm,PARMPOS_DRAWN_HWCONFED_ERROR);//~9426I~
//          if (reasonResponseHW[Accounts.getCurrentEswn()]==EGDR_NONE)//~9306R~
        	realPlayer=AG.aAccounts.getCurrentDealerReal();    //~9306I~//~9308R~
    		if (Dump.Y) Dump.println("UAEndGame.drawn CONFIRMED dealer typeNextGame="+typeNextGame+",eswnRequester="+eswnRequester+",realPlayer="+realPlayer+",reson="+Arrays.toString(reasonResponseHW)+",currentEswn="+Accounts.getCurrentEswn());//~9306I~//~9705R~
    		if (realPlayer==PLAYER_YOU                             //~9306I~
            ||  reasonResponseHW[Accounts.getCurrentEswn()]==EGDR_NONE//~9306I~//~9608R~
            ||  reasonResponseHW[Accounts.getCurrentEswn()]==EGDR_NG)//~9608I~
    	        showDrawnHWResultDlg(reason,typeNextGame,swsError,eswnRequester);//~9705I~
            break;                                                 //~v@@@I~
        case ENDGAME_DRAWN_LAST:	//Drawn_LAST on all            //~9307R~
//          UserAction.showInfoAllEswn(0/*opt*/,Utils.getStr(R.string.Info_EndGameDrawn_Last));//~9307I~//~0222R~
            UserAction.showInfo(0/*opt*/,Utils.getStr(R.string.Info_EndGameDrawn_Last));//~0222R~
            if (PswServer)                                         //~9307I~
	            AG.aUserAction.msgDataToClient=makeMsgDataToClient(Pplayer,type,reason);//~9307I~
        	Arrays.fill(reasonResponse,EGDR_NONE);                 //~9307I~
	    	AG.aComplete.setDrawnDelayLastTimeout();               //~9603I~
//          status.endGame(type);                                  //~9307I~//~9318R~
            showDlg();                                             //~9307I~
            break;                                                 //~v@@@M~
        case ENDGAME_DRAWN_LAST_RESPONSE:	//on Server Drawn_LAST on server sent by selectInfo//~9307R~//~9308R~
		    setPendingRobot();			    //pending of smartrobot on server//~va60I~
    		setResponseLast(Pplayer,reason);                       //~9307I~
            if (swResponsedAllPending)                                 //~9307I~//~9311R~
            {                                                      //~9309I~
				sendOpen(Pplayer,reason);                                    //~9308R~//~9309I~
//              if (PswServer)                                     //~va60R~
//  				sendOpenRobot(Pplayer);                        //~va60R~
    			sendToCurrentDealerLast(PswServer,Pplayer,reason); //show dlg on dealer or send CONFIRM to dealer//~9307R~//~9311M~
			}                                                      //~9309I~
          	UA.setNoMsgToClient();                                 //~9307I~
            break;                                                 //~9307I~
        case ENDGAME_DRAWN_LAST_CONFIRM:	//on Dealer            //~9310R~
        	System.arraycopy(PintParm,PARMPOS_DRAWN_RESPSTAT,reasonResponse,0,PLAYERS);//~9309I~
            if (Dump.Y) Dump.println("UAEndGame.drawn on dealer received LAST_CONFIRM responseReason="+Arrays.toString(reasonResponse));//~9309I~//~9310M~
//  	    showDrawnDlgLast(reason);           //~9307I~          //~9308R~//~9311R~
    	    showDlgLast(LASTDT_REASON,Pplayer);                    //~9311R~
            if (PswServer)                                          //~9307I~
	          	UA.setNoMsgToClient();                             //~9307I~//~9310M~
            break;                                                 //~9307I~
        case ENDGAME_DRAWN_LAST_CONFIRM_REQUEST:	//on server from dealer or GVH msg from dlg of server//~9311R~
        	if (!PswReceived)	//from client	                                   //~9310I~//~9311R~
            	getDialogParm(PstrParm);	                       //~9310I~
            else                                                   //~9310I~
            {                                                      //~9310I~
	        	System.arraycopy(PintParm,PARMPOS_DRAWN_DIALOGDATA,dialogDataLast,0,PARMPOS_DRAWN_DIALOGDATA_CTR);//~9310I~
	    		if (Dump.Y) Dump.println("UAEndGame.drawn CONFIRM_REQUEST dialogDataLast="+Arrays.toString(dialogDataLast));//~9310I~//~9311R~//~0331R~
            }                                                      //~9310I~
//  	    showDrawnDlgLast(reason,dialogDataLast);               //~9310R~//~9311R~
	    	if (Dump.Y) Dump.println("UAEndGame.drawn CONFIRM_REQUEST before clear confirmResponseLast="+Arrays.toString(confirmResponseLast));//~0331I~
        	Arrays.fill(confirmResponseLast,EGDR_NONE);            //~0331I~
			if (!(PswServer && !PswReceived))	//server is dealer and by Dialog send//~9311I~
	    	    showDlgLast(LASTDT_DIALOG,Pplayer);                //~9311R~
            if (PswServer)                                         //~9310I~
            {                                                      //~9310I~
    			sendConfirmReqLastToOtherClient(Pplayer,reason,typeNextGame,dialogDataLast); //LAST_CONFIRM_REQUEST//~9310I~
	          	UA.setNoMsgToClient();                             //~9310I~
            }                                                      //~9310I~
            break;                                                 //~9310I~
        case ENDGAME_DRAWN_LAST_CONFIRM_RESPONSE:	//on server	   //~9308I~
            if (isUpdateAfterSend())                               //~0309R~
            {                                                      //~0309I~
		        UA.setNoMsgToClient();                             //~0309I~
                break;                                             //~0309I~
         	}                                                      //~0309I~
            if (setResponseConfLast(Pplayer,reason))	//all responsed//~9308I~
                drawnLastConfirmed(); //confirmed msg to all       //~9308I~
            sendConfirmedLastToCurrentDealer(Pplayer,reason,typeNextGame);      //CONFIRMED + respstat//~9311R~
            if (PswServer)                                         //~9308I~
		        UA.setNoMsgToClient();                             //~9308I~
            break;                                                 //~9308I~
        case ENDGAME_DRAWN_LAST_CONFIRMED:	//all device           //~9308I~
            System.arraycopy(PintParm,PARMPOS_DRAWN_RESPSTAT,confirmResponseLast,0,PLAYERS);//~9311I~
    		if (Dump.Y) Dump.println("UAEndGame.drawn CONFIRMED rspstat="+Arrays.toString(confirmResponseLast));//~9311I~
            showDlgLast(LASTDT_OKNG,Pplayer);	//update ok/ng     //~9311R~
            break;                                                 //~9308I~
        default:                                                   //~v@@@R~
    		if (Dump.Y) Dump.println("UAEndGame.drawn unknown type");//~9308I~
		}//switch                                                  //~v@@@I~
    	if (Dump.Y) Dump.println("UAEndGame.drawn return true");   //~9308I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@M~
	//*************************************************************************//~v@@@I~
    public  String makeMsgDataToClient(int Pplayer,int Ptype,int Preason)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAEndGame.makeMsgDataToClient player="+Pplayer+",type="+Ptype+",reason="+Preason);//~v@@@R~
    	return UserAction.makeMsgDataToClient(Pplayer,Ptype,Preason);//~v@@@R~
    }                                                              //~v@@@I~
	//*************************************************************************//~9308I~
//  public  String makeMsgDataRespStat(int Pmsgid,int Preason,int PtypeNextGame,int[] PrespStat)//~9308I~//~9705R~
    public  String makeMsgDataRespStat(int Pmsgid,int Preason,int PtypeNextGame,int PeswnRequester,int[] PrespStat)//~9705I~
    {                                                              //~9308I~
    	String msg=Pmsgid+MSG_SEPAPP2+Preason+MSG_SEPAPP2+PtypeNextGame//~9308I~
    				+MSG_SEPAPP2+PeswnRequester                    //~9705I~
    				+MSG_SEPAPP2+PrespStat[0]                      //~9308I~
    				+MSG_SEPAPP+PrespStat[1]                       //~9308I~
    				+MSG_SEPAPP+PrespStat[2]                       //~9308I~
    				+MSG_SEPAPP+PrespStat[3];                      //~9308I~
        if (Dump.Y) Dump.println("UAEndGame.makeMsgDataRespStat str="+msg);//~9308I~
    	return msg;                                                //~9308I~
    }                                                              //~9308I~
	//*************************************************************************//~9310I~
    public  String makeMsgDialogData(int[] PdialogData)            //~9310I~
    {                                                              //~9310I~
    	StringBuffer sb=new StringBuffer();                         //~9310I~
        for (int ii=0;ii<PdialogData.length;ii++)                  //~9310I~
    		sb.append((ii==0 ? MSG_SEPAPP2 : MSG_SEPAPP)+PdialogData[ii]);//~9310I~
        String s=sb.toString();                                    //~9310I~
        if (Dump.Y) Dump.println("UAEndGame.makeMsgDialogData reason+respstat str="+s);//~9310I~
    	return s;                                                  //~9310I~
    }                                                              //~9310I~
	//*************************************************************************//~v@@@I~
	//*response of Pending/NoPending                               //~9308I~
	//*************************************************************************//~9308I~
    public void setResponseLast(int Pplayer,int Preason)               //~v@@@I~//~9307R~
    {                                                              //~v@@@I~
        int eswn=Accounts.playerToEswn(Pplayer);                    //~v@@@I~
    	reasonResponse[eswn]=Preason;                              //~v@@@I~
        if (Dump.Y) Dump.println("UAEndGame.setResponse player="+Pplayer+",reason="+Preason);//~v@@@R~
        if (chkResponse())                                         //~v@@@I~
        {                                                          //~v@@@I~
        	swResponsedAllPending=true;                                   //~v@@@I~//~9307R~//~9311R~
// 			showDrawnDlgLast(Preason);                       //~9307I~//~9308R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9308I~
	//*response of Confirm of Last                                 //~9308I~
	//*************************************************************************//~9308I~
    public boolean setResponseConfLast(int Pplayer,int Preason)    //~9308I~
    {                                                              //~9308I~
        int eswn=Accounts.playerToEswn(Pplayer);                   //~9308I~
	    confirmResponseLast[eswn]=Preason;                         //~9308I~
        boolean rc=chkResponseConfLast();                          //~9308I~
        if (rc)                                 //~9308I~          //~9311R~
        {                                                          //~9308I~
        	swResponsedAllConfLast=true;                           //~9308I~
        }                                                          //~9308I~
        if (Dump.Y) Dump.println("UAEndGame.setResponseConfLast rc="+rc+",player="+Pplayer+",eswn="+eswn+",reason="+Preason);//~9308I~
        return rc;                                                 //~9308I~
    }                                                              //~9308I~
	//*************************************************************************//~v@@@I~
    public void setResponseHW(int Pplayer,int Preason,int[] PintParm,int Ppos)             //~v@@@I~//~9426R~
    {                                                              //~v@@@I~
        int eswn=Accounts.playerToEswn(Pplayer);                   //~v@@@I~
    	reasonResponseHW[eswn]=Preason;                            //~v@@@I~
        setResponseHWError(PintParm,Ppos);                      //~9426R~
        if (Dump.Y) Dump.println("UAEndGame.setResponseHW player="+Pplayer+",eswn="+eswn+",reason="+Preason+",resons="+Arrays.toString(reasonResponseHW));//~v@@@I~//~9305R~//~9608R~
    }                                                              //~v@@@I~
	//*************************************************************************//~9426I~
    public void setResponseHWError(int [] PstrParmInt,int Ppos)    //~9426R~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("UAEndGame.setResponseHWError pos="+Ppos+",intp="+Arrays.toString(PstrParmInt));//~9426R~
    	int pos=Ppos;//PARMPOS_DRAWN_RESP_ERROR;                   //~9426R~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9426I~
        	swsError[ii]=PstrParmInt[pos++]!=0;                       //~9426I~
        if (Dump.Y) Dump.println("UAEndGame.setResponseHWError swsError="+Arrays.toString(swsError));//~9426I~//~9612R~
    }                                                              //~9426I~
	//*************************************************************************//~v@@@I~
	//*response of Pending/NoPending                               //~9308I~
	//*************************************************************************//~9308I~
    public boolean chkResponse()                                   //~v@@@R~
    {                                                              //~v@@@I~
    	int ctr=0;                                                 //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	if (reasonResponse[ii]!=ENDGAME_NONE)             //~v@@@I~
            	ctr++;                                             //~v@@@I~
            else                                                   //~v@@@I~
                if (AG.aAccounts.isDummyByCurrentEswn(ii))         //~v@@@I~
                	ctr++;                                         //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UAEndGame.chkResponse ctr="+ctr+",reasonResponse="+Arrays.toString(reasonResponse));//~v@@@R~
        return ctr==PLAYERS;                                       //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9308I~
	//*chk response of OK/NG for EndGame Last                      //~9308I~
	//*************************************************************************//~9308I~
    public boolean chkResponseConfLast()                           //~9308I~
    {                                                              //~9308I~
    	int ctr=0;                                                 //~9308I~
        swFoundNG=false;                                           //~9308I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9308I~
        {                                                          //~9308I~
        	if (confirmResponseLast[ii]!=EGDR_NONE)             //~9308I~
            {                                                      //~9308I~
	        	if (confirmResponseLast[ii]==EGDR_NG)           //~9308I~
                	swFoundNG=true;                                //~9308I~
            	ctr++;                                             //~9308I~
            }                                                      //~9308I~
            else                                                   //~9308I~
                if (AG.aAccounts.isDummyByCurrentEswn(ii))         //~9308I~
                	ctr++;                                         //~9308I~
        }                                                          //~9308I~
        if (Dump.Y) Dump.println("UAEndGame.chkResponseConfLast ctr="+ctr+",reasonResponseConfLast="+Arrays.toString(confirmResponseLast));//~9308I~
        return ctr==PLAYERS-1;                                       //~9308I~//~9311R~
    }                                                              //~9308I~
	//*************************************************************************//~v@@@I~
    public boolean chkNGConfLast()                                 //~9311I~
    {                                                              //~9311I~
        boolean sw=false;                                          //~9311I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9311I~
        {                                                          //~9311I~
	        if (confirmResponseLast[ii]==EGDR_NG)                  //~9311I~
            {                                                      //~9311I~
                sw=true;                                           //~9311I~
                break;                                             //~9311I~
            }                                                      //~9311I~
        }                                                          //~9311I~
        if (Dump.Y) Dump.println("UAEndGame.chkNGConfLast rc="+sw);//~9311I~
        return sw;                                                 //~9311I~
    }                                                              //~9311I~
	//*************************************************************************//~9311I~
    public boolean chkResponseHW()                                 //~v@@@I~
    {                                                              //~v@@@I~
    	int ctr=0;                                                 //~v@@@I~
        swFoundNG=false;                                           //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	if (reasonResponseHW[ii]!=EGDR_NONE)                   //~v@@@R~
            {                                                      //~v@@@I~
	        	if (reasonResponseHW[ii]==EGDR_NG)                 //~v@@@I~
                	swFoundNG=true;                                 //~v@@@I~
            	ctr++;                                             //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
                if (AG.aAccounts.isDummyByCurrentEswn(ii))         //~v@@@I~
                	ctr++;                                         //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UAEndGame.chkResponseHW ctr="+ctr+",reasonResponseHW="+Arrays.toString(reasonResponseHW));//~v@@@I~
        return ctr==PLAYERS;                                       //~9311R~
    }                                                              //~v@@@I~
	//*************************************************************************//~9311I~
    public boolean chkNGHW()                                       //~9311I~
    {                                                              //~9311I~
        boolean sw=false;                                          //~9311I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9311I~
        {                                                          //~9311I~
	        if (reasonResponseHW[ii]==EGDR_NG)                     //~9311I~
            {                                                      //~9311I~
                sw=true;                                           //~9311I~
                break;                                             //~9311I~
            }                                                      //~9311I~
        }                                                          //~9311I~
        if (Dump.Y) Dump.println("UAEndGame.chkNGHW rc="+sw);      //~9311I~
        return sw;                                                 //~9311I~
    }                                                              //~9311I~
	//*************************************************************************//~v@@@I~
//  public void showDrawnHWResultDlg(int Preason,int PtypeNextGame)//~v@@@R~//~9705R~
    public void showDrawnHWResultDlg(int Preason,int PtypeNextGame,int PeswnRequester)//~9705I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAEndGame.showDrawnResultDlg reason="+Preason+",typenextgame="+PtypeNextGame+",eswnRequester="+PeswnRequester+",dlg==null?"+(dlgConfirmHW==null));//~v@@@R~//~9705R~
        if (Utils.isShowingDialogFragment(dlgConfirmHW))           //~0308I~
        {                                                          //~0308I~
        	if (Dump.Y) Dump.println("UAEndGame.showDrawnResultDlg showing");//~0308I~
		    dlgConfirmHW.dismiss();                             //~0308I~
        }                                                          //~0308I~
        DrawnDlgHW.newInstance(Preason,PtypeNextGame,reasonResponseHW,PeswnRequester).show();//~0308I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9426I~
//  public void showDrawnHWResultDlg(int Preason,int PtypeNextGame,boolean[] PswsError)//~9426I~//~9705R~
    public void showDrawnHWResultDlg(int Preason,int PtypeNextGame,boolean[] PswsError,int PeswnRequester)//~9705I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("UAEndGame.showDrawnResultDlg dlg==null?"+(dlgConfirmHW==null)+",eswnRequester="+PeswnRequester+",swsError="+Arrays.toString(swsError));//~9426I~//~9705R~
      if (AG.aAccounts.getCurrentDealerReal()==PLAYER_YOU)    //server is requester//~0308I~
      {                                                            //~0308I~
//      if (dlgConfirmHW==null)                                    //~9426I~//~9611R~//~0308R~
        if (!Utils.isShowingDialogFragment(dlgConfirmHW))          //~9611I~//~0308R~
        {                                                          //~9426I~//~0308R~
        	if (Dump.Y) Dump.println("UAEndGame.showDrawnResultDlg new");//~0308I~
                DrawnDlgHW.newInstance(Preason,PtypeNextGame,reasonResponseHW,swsError,PeswnRequester,swSuspend).show();//~0306I~//~0308R~
        }                                                          //~9426I~//~0308R~
        else                                                       //~9426I~//~0308R~
        {                                                          //~9426I~//~0308R~
        	if (Dump.Y) Dump.println("UAEndGame.showDrawnResultDlg dup repaint");//~0308I~
            dlgConfirmHW.repaint(PtypeNextGame,reasonResponseHW,swsError,Preason,PeswnRequester,swSuspend);//~0306I~//~0308R~
        }                                                          //~9426I~//~0308R~
      }                                                            //~0308I~
      else                                                         //~0308I~
      {                                                            //~0308I~
        if (Utils.isShowingDialogFragment(dlgConfirmHW))           //~0308I~
        {                                                          //~0308I~
        	if (Dump.Y) Dump.println("UAEndGame.showDrawnResultDlg showing");//~0308I~
		    dlgConfirmHW.dismiss();                             //~0308I~
        }                                                          //~0308I~
        DrawnDlgHW.newInstance(Preason,PtypeNextGame,reasonResponseHW,swsError,PeswnRequester,swSuspend).show();//~0308I~
      }                                                            //~0308I~
    }                                                              //~9426I~
	//*************************************************************************//~9701I~
	//*from MenuInGameDlg                                          //~9701I~
	//*************************************************************************//~9701I~
	public static boolean showDlgFromMenu()                           //~9701I~//~9904R~
    {                                                              //~9701I~
        if (Dump.Y) Dump.println("UAEndGame.showDlgFromMenu");     //~9701I~
		boolean rc=showDlg(true/*PswFromMenu*/);                              //~9701I~//~9904R~
        if (Dump.Y) Dump.println("UAEndGame.showDlgFromMenu  rc="+rc);//~9904I~
        return rc;
    }                                                              //~9701I~
	private static void showDlg()                                   //~9701I~//~1223R~
    {                                                              //~9701I~
        if (Dump.Y) Dump.println("UAEndGame.showDlg");     //~9701I~//~9A20R~
		showDlg(false/*PswFromMenu*/);                             //~9701I~
    }                                                              //~9701I~
	//*************************************************************************//~v@@@I~//~9311M~
	//*by msg:ENDGAME_DRAWN_LAST from UADelayed                    //~9610R~
	//*and                                                         //~9610I~
	//*from GC,button click or selected from MenuInGame            //~9610I~
	//*************************************************************************//~9311I~
//  public static void showDlg()                                   //~v@@@M~//~9311M~//~9701R~
    private static boolean showDlg(boolean PswFromMenu)                //~9701I~//~9904R~//~1223R~
    {                                                              //~v@@@M~//~9311M~
        UAEndGame UAEG=AG.aUAEndGame;                              //~9311R~
        if (UAEG==null)                                            //~9311I~
        	return false;                                                //~9311I~//~9904R~
      if (!AG.aComplete.getDrawn3R())                              //~9B29I~
      {                                                            //~9B29I~
    	if (Status.isIssuedRon())                                    //~v@@@I~//~9603R~
        {                                                          //~9603I~
            UView.showToast(R.string.AE_AfterIssuedRonAlready);    //~9603R~
        	return false;                                                //~9603I~//~9904R~
        }                                                          //~9603I~
		if (AG.aComplete.isTotalAgreed())                          //~9612I~
        {                                                          //~9612I~
            UView.showToast(R.string.Err_TotalAgreedAlready);      //~9612I~
        	return false;                                             //~9612I~//~9904R~
        }                                                          //~9612I~
      }                                                            //~9B29I~
        if (Dump.Y) Dump.println("UAEndGame.showDlg swFromMenu="+PswFromMenu+",status.endgametype="+AG.aStatus.endGameType+",swResponsedAllPending="+UAEG.swResponsedAllPending+",swResponsedAllConfLast="+UAEG.swResponsedAllConfLast);//~v@@@M~//~9308R~//~9311R~//~9A11R~
        int player=PLAYER_YOU;                                     //~9311I~
        if (AG.aTiles.chkLast()                                  //~v@@@I~//~9311R~
        ||  (TestOption.option & TO_DRAWNREQDLG_LAST)!=0 // TODO TEST //~9311I~//~9417R~//~9420R~
        )                                                          //~9311I~
        {                                                          //~9306I~//~9311M~
        	if (Dump.Y) Dump.println("UAEndGame.showDlg DrawnLast TestOption:DrawnLast="+((TestOption.option & TO_DRAWNREQDLG_LAST)!=0));//~9A14I~//~1212R~
				DrawnReqDlgLast.newInstance().show();                      //~v@@@M~//~9308R~//~9311M~
        }                                                          //~9306I~//~9311M~
        else                                                       //~v@@@M~//~9311M~
        {                                                          //~v@@@I~//~9311M~
			DrawnReqDlgHW.newInstance().show();                    //~v@@@M~//~9311M~
        }                                                          //~v@@@I~//~9311M~
        return true;                                               //~9904I~
    }                                                              //~v@@@M~//~9311M~
	//*************************************************************************//~9311I~
    private void showDlgLast(int PdataType,int Pplayer)            //~9311R~
    {                                                              //~9311I~
        boolean swRequester=AG.aAccounts.getCurrentDealerReal()==PLAYER_YOU;	//dialer//~9307R~//~9311R~//~0308I~
        if (Dump.Y) Dump.println("UAEndGame.showDlgLast swRequester="+swRequester+",case="+PdataType+",player="+Pplayer);//~9311I~//~0308R~//~0331R~
        int eswn=Accounts.playerToEswn(Pplayer);                   //~9311I~
      if (swRequester)                                             //~0308I~
      {                                                            //~0308I~
//      if (dlgConfcrmLast==null)                                  //~9311I~//~9610R~//~9611R~
        if (!Utils.isShowingDialogFragment(dlgConfirmLast))                    //~v@@@I~//~9226R~//~9610I~
        {                                                          //~9311I~
//          dlgConfirmLast = DrawnDlgLast.newInstance(eswn,PdataType,reasonResponse,confirmResponseLast,dialogDataLast);//~9311R~//~9611R~
//          dlgConfirmLast.show();                                 //~9311I~//~9611I~
            DrawnDlgLast.newInstance(eswn,PdataType,reasonResponse,confirmResponseLast,dialogDataLast).show();//~9611I~
        }                                                          //~9311I~
        else                                                       //~9311I~
        {                                                          //~9311I~
//          UView.showToast("dup dialog");                         //~9311I~//~9902R~
	        dlgConfirmLast.repaint(eswn,PdataType,reasonResponse,confirmResponseLast,dialogDataLast);//~9311R~
        }                                                          //~9311I~
      }                                                            //~0308I~
      else                                                         //~0308I~
      {                                                            //~0308I~
        if (Utils.isShowingDialogFragment(dlgConfirmLast))         //~0308I~
        	dlgConfirmLast.dismiss();                              //~0308R~
      	DrawnDlgLast.newInstance(eswn,PdataType,reasonResponse,confirmResponseLast,dialogDataLast).show();//~0308I~
      }                                                            //~0308I~
    }                                                              //~9311I~
	//*************************************************************************//~v@@@I~
	//*by dialog response                                          //~v@@@I~
	//*************************************************************************//~v@@@I~
    private void  sendToCurrentDealer(boolean PswServer,int Pplayer,int Preason)//~v@@@R~//~9305R~
    {                                                              //~v@@@I~
        int realPlayer=AG.aAccounts.getCurrentDealerReal();        //~9306I~
        eswnRequester=Accounts.playerToEswn(Pplayer);              //~9705I~
        if (Dump.Y) Dump.println("UAEndGame.sendToCurrentDealer PswServer="+PswServer+",player="+Pplayer+",reason="+Preason+",eswnRequester="+eswnRequester);//~9705I~
    	if (realPlayer==PLAYER_YOU)                                //~v@@@R~
        {                                                          //~9705I~
//  	    showDrawnHWResultDlg(Preason,NEXTGAME_UNKNOWN/*typeNextGame*/);           //~v@@@R~//~9305R~//~9306R~//~9705R~
    	    showDrawnHWResultDlg(Preason,NEXTGAME_UNKNOWN/*typeNextGame*/,eswnRequester);//~9705I~
        }                                                          //~9705I~
	    else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//          String msg=ENDGAME_DRAWN_HALFWAY_CONFIRM+MSG_SEPAPP2+Preason+MSG_SEPAPP2+NEXTGAME_UNKNOWN;//~v@@@R~//~9305R~//~9306R~//~9705R~
            String msg=ENDGAME_DRAWN_HALFWAY_CONFIRM+MSG_SEPAPP2+Preason+MSG_SEPAPP2+NEXTGAME_UNKNOWN+MSG_SEPAPP2+eswnRequester;//~9705I~
//  		UA.sendToTheClient(realPlayer,GCM_ENDGAME_DRAWN,msg);//~v@@@R~//~9612R~
    		sendToTheClient(false/*swPswTransferToReal*/,realPlayer,msg);//~9612I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9518I~
    private void  sendToCurrentDealerDelayed(boolean PswServer,int Pplayer,int PmsgSubtype,int Preason)//~9518I~
    {                                                              //~9518I~
        if (Dump.Y) Dump.println("UAEndGame.sendToCurrentDealerDelayed PswServer="+PswServer+",player="+Pplayer+",msgtype="+PmsgSubtype+",reason="+Preason);//~9518I~
        GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN,PLAYER_YOU,PmsgSubtype,Preason);//~9426I~//~9518I~
    }                                                              //~9518I~
	//*************************************************************************//~9426I~
    private void  sendToCurrentDealer(boolean PswServer,int Pplayer,int Preason,boolean[] PswsError)//~9426R~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("UAEndGame.sendToCurrentDealer PswServer="+PswServer+",player="+Pplayer+",reason="+Preason+",swsError="+Arrays.toString(PswsError));//~9426R~
        eswnRequester=Accounts.playerToEswn(Pplayer);              //~9705I~
        int realPlayer=AG.aAccounts.getCurrentDealerReal();        //~9426I~
    	if (realPlayer==PLAYER_YOU)                                //~9426I~
//  	    showDrawnHWResultDlg(Preason,NEXTGAME_UNKNOWN/*typeNextGame*/,PswsError);//~9426R~//~9705R~
    	    showDrawnHWResultDlg(Preason,NEXTGAME_UNKNOWN/*typeNextGame*/,PswsError,eswnRequester);//~9705I~
	    else                                                       //~9426I~
        {                                                          //~9426I~
            String msg=ENDGAME_DRAWN_HALFWAY_CONFIRM+MSG_SEPAPP2+Preason+MSG_SEPAPP2+NEXTGAME_UNKNOWN;//~9426I~
	    	msg+=MSG_SEPAPP2+DrawnDlgHW.makeMsgDataBoolean(PswsError);//~9426I~
//  		UA.sendToTheClient(realPlayer,GCM_ENDGAME_DRAWN,msg);  //~9426I~//~9612R~
    		sendToTheClient(false/*swPswTransferToReal*/,realPlayer,msg);//~9612I~
        }                                                          //~9426I~
    }                                                              //~9426I~
	//*************************************************************************//~9426I~
//  private void  sendConfirmedToOtherClientHW(int Pplayer,int Preason,int PtypeNextGame,boolean[] PswsError)//~9426R~//~9705R~
    private void  sendConfirmedToOtherClientHW(int Pplayer,int Preason,int PtypeNextGame,int PeswnRequester,boolean[] PswsError)//~9705I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("UAEndGame.sendConfirmedToOtherClient player="+Pplayer+",reason="+Preason+",typeNextGame="+PtypeNextGame+",eswnRequester="+PeswnRequester);//~9426I~//~9705R~
//  	String msg=makeMsgDataRespStat(ENDGAME_DRAWN_HALFWAY_CONFIRMED,Preason,PtypeNextGame,reasonResponseHW);//~9426I~//~9705R~
    	String msg=makeMsgDataRespStat(ENDGAME_DRAWN_HALFWAY_CONFIRMED,Preason,PtypeNextGame,PeswnRequester,reasonResponseHW);//~9705I~
    	msg+=MSG_SEPAPP2+DrawnDlgHW.makeMsgDataBoolean(PswsError); //~9426I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9426I~
        {                                                          //~9426I~
            int cp=Accounts.eswnToPlayer(ii);                      //~9426I~
//  		UA.sendToTheClient(cp,GCM_ENDGAME_DRAWN,msg);          //~9426I~//~9612R~
//  		sendToTheClient(false/*PswTransferToReal*/,cp,msg);    //~9612I~//~9703R~
    		sendToTheClient(cp,msg);                               //~9703I~
        }                                                          //~9426I~
    }                                                              //~9426I~
	//*************************************************************************//~9B14I~
	//*requester->server->otherclient by HALFWAY_CONFIRMED_RESPONSE with reason!=OK nor NG//~9B14R~
	//*************************************************************************//~9B14I~
//  private void  sendConfirmRequestHWToOtherClientAll(int Pmsgtype,int Pplayer,int Preason,int PtypeNextGame,int PeswnRequester,boolean[] PswsError)//~9B14R~//~0306R~
    private void  sendConfirmRequestHWToOtherClientAll(int Pmsgtype,int Pplayer,int Preason,int PtypeNextGame,int PeswnRequester,int Psuspendid,boolean[] PswsError)//~0306I~
    {                                                              //~9B14I~
//  	String msg=ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE+MSG_SEPAPP2+Preason+MSG_SEPAPP2+"0"+MSG_SEPAPP2+PtypeNextGame+MSG_SEPAPP2+PeswnRequester;//~9B14R~
//  	String msg=Pmsgtype+MSG_SEPAPP2+Preason+MSG_SEPAPP2+"0"+MSG_SEPAPP2+PtypeNextGame+MSG_SEPAPP2+PeswnRequester;//~9B14I~//~0306R~
    	String msg=Pmsgtype+MSG_SEPAPP2+Preason+MSG_SEPAPP2+Psuspendid+MSG_SEPAPP2+PtypeNextGame+MSG_SEPAPP2+PeswnRequester;//~0306R~
    	msg+=MSG_SEPAPP2+DrawnDlgHW.makeMsgDataBoolean(PswsError); //~9B14I~
        if (Dump.Y) Dump.println("UAEndGame.sendConfirmeRequestHWToOtherClientAll player="+Pplayer+",reason="+Preason+",typeNextGame="+PtypeNextGame+",eswnRequester="+PeswnRequester+",suspendid="+Psuspendid+",msg="+msg);//~9B14R~//~0306R~
		UA.sendToTheClientOther(Pplayer/*skip*/,GCM_ENDGAME_DRAWN,msg);//~v@@@I~//~9B14I~
    }                                                              //~9B14I~
	//*************************************************************************//~9310I~
	//*when server received CONFIRM from Dealer                    //~9310I~
	//*************************************************************************//~9310I~
    private void  sendConfirmReqLastToOtherClient(int Pplayer,int Preason,int PtypeNextGame,int[] PdialogData)//~9310R~
    {                                                              //~9310I~
        if (Dump.Y) Dump.println("UAEndGame.sendConfirmLastToOtherClient player="+Pplayer+",reason="+Preason);//~9310I~
//  	String msg=makeMsgDataRespStat(ENDGAME_DRAWN_LAST_CONFIRM_REQUEST,Preason,PtypeNextGame,reasonResponse,confirmResponseLast);//~9310R~
//      msg+=makeMsgDialogData(PdialogData);                       //~9310R~
		String msg=ENDGAME_DRAWN_LAST_CONFIRM_REQUEST+makeMsgDialogData(PdialogData);//~9310R~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9310I~
        {                                                          //~9310I~
            int cp=Accounts.eswnToPlayer(ii);                      //~9310I~
            if (cp!=Pplayer)                                       //~9310I~
//      		UA.sendToTheClient(cp,GCM_ENDGAME_DRAWN,msg);      //~9310I~//~9612R~
//      		sendToTheClient(false/*PswTransferToReal*/,cp,msg);//~9612I~//~9703R~
        		sendToTheClient(cp,msg);                           //~9703I~
        }                                                          //~9310I~
    }                                                              //~9310I~
	//*************************************************************************//~9306I~
//  private void  sendConfirmedToCurrentDealerHW(int Pplayer,int Preason,int PtypeNextGame)//~9306I~//~9308R~//~9426R~
//  private void  sendConfirmedToCurrentDealerHW(int Pplayer,int Preason,int PtypeNextGame,boolean[] PswsError)//~9426I~//~9705R~
    private void  sendConfirmedToCurrentDealerHW(int Pplayer,int Preason,int PtypeNextGame,int PeswnRequester,boolean[] PswsError)//~9705I~
    {                                                              //~9306I~
        if (Dump.Y)
            Dump.println("UAEndGame.sendConfirmedToCurrentDealerHW player=" + Pplayer + ",reason=" + Preason + ",typeNextGame=" + PtypeNextGame);//~9306I~//~9310R~
        int realPlayer = AG.aAccounts.getCurrentDealerReal();        //~9306I~
        if (realPlayer == PLAYER_YOU)                                //~9306I~
//          showDrawnHWResultDlg(Preason, PtypeNextGame);           //~9306I~//~9426R~
//          showDrawnHWResultDlg(Preason, PtypeNextGame,PswsError);//~9426I~//~9705R~
            showDrawnHWResultDlg(Preason, PtypeNextGame,PswsError,PeswnRequester);//~9705R~
        else                                                       //~9306I~
        {                                                          //~9306I~
//          String msg = makeMsgDataToClientConfirmed(Preason, PtypeNextGame); //CONFIRMED//~9306I~//~9308R~
//  		String msg=makeMsgDataRespStat(ENDGAME_DRAWN_HALFWAY_CONFIRMED,Preason,PtypeNextGame,reasonResponseHW);//~9308R~//~9705R~
    		String msg=makeMsgDataRespStat(ENDGAME_DRAWN_HALFWAY_CONFIRMED,Preason,PtypeNextGame,PeswnRequester,reasonResponseHW);//~9705I~
	    	msg+=MSG_SEPAPP2+DrawnDlgHW.makeMsgDataBoolean(PswsError);//~9426I~
//          UA.sendToTheClient(realPlayer, GCM_ENDGAME_DRAWN, msg);  //~9306I~//~9612R~
            sendToTheClient(false/*PswTransferToReal*/,realPlayer,msg);//~9612I~
        }
    }//~9306I~
	//*************************************************************************//~9307I~//~9309M~
    private void  sendToCurrentDealerLast(boolean PswServer,int Pplayer,int Preason)//~9307I~//~9309M~
    {                                                              //~9307I~//~9309M~
        if (Dump.Y) Dump.println("UAEndGame.sendToCurrentDealerLast PswServer="+PswServer+",player="+Pplayer+",reason="+Preason);//~9307I~//~9309M~
//  	if (PswServer)                                             //~9307I~//~9308R~//~9309M~
//  		status.endGame(ENDGAME_DRAWN_LAST);                    //~9307I~//~9308R~//~9309M~
        int realPlayer=AG.aAccounts.getCurrentDealerReal();        //~9307I~//~9309M~
    	if (realPlayer==PLAYER_YOU)                                //~9307I~//~9309M~
//  	    showDrawnDlgLast(Preason);//~9307R~                    //~9308R~//~9309M~//~9311R~
    	    showDlgLast(LASTDT_REASON,Pplayer);                    //~9311R~
	    else                                                       //~9307I~//~9309M~
        {                                                          //~9307I~//~9309M~
//          String msg=ENDGAME_DRAWN_LAST_CONFIRM+MSG_SEPAPP2+Preason+MSG_SEPAPP2+NEXTGAME_UNKNOWN;//~9307I~//~9309I~
//  		String msg=makeMsgDataRespStat(ENDGAME_DRAWN_LAST_CONFIRM,Preason,NEXTGAME_UNKNOWN,reasonResponse);//~9309I~//~9705R~
    		String msg=makeMsgDataRespStat(ENDGAME_DRAWN_LAST_CONFIRM,Preason,NEXTGAME_UNKNOWN,0/*eswnRequester:no meaning on drawnLast*/,reasonResponse);//~9705I~
//  		UA.sendToTheClient(realPlayer,GCM_ENDGAME_DRAWN,msg);  //~9307I~//~9309M~//~9610R~
    		sendToTheClient(false/*PswTransferToReal*/,realPlayer,msg);//~9610I~
        }                                                          //~9307I~//~9309M~
    }                                                              //~9307I~//~9309M~
	//*************************************************************************//~9308I~
    private void  sendConfirmedLastToCurrentDealer(int Pplayer,int Preason,int PtypeNextGame)//~9308R~
    {                                                              //~9308I~
        if (Dump.Y) Dump.println("UAEndGame.sendConfirmLastToOtherDealer player=" + Pplayer + ",reason=" + Preason);//~9308I~//~9310R~
        int realPlayer = AG.aAccounts.getCurrentDealerReal();      //~9308I~
        if (realPlayer == PLAYER_YOU)                              //~9308I~
//          showDrawnDlgLast(Preason);                             //~9308I~//~9311R~
            showDlgLast(LASTDT_OKNG,Pplayer);                      //~9311R~
        else                                                       //~9308I~
        {                                                          //~9308I~
    		String msg=makeMsgDataRespStat(ENDGAME_DRAWN_LAST_CONFIRMED,Preason,PtypeNextGame,0/*eswnRequester:no meening for DrawnLast*/,confirmResponseLast);//~9705I~
            sendToTheClient(false/*transferToReal*/,realPlayer,msg);//~9610I~
        }                                                          //~9308I~
    }                                                              //~9308I~
	//*************************************************************************//~9308I~
    private void drawnLastConfirmed()                              //~9308I~
    {                                                              //~9308I~
        if (Dump.Y) Dump.println("UAEndGame.drawnLastConfirmed");  //~9308I~
//      UserAction.showInfoAllEswn(0/*opt*/,Utils.getStr(swFoundNG ? R.string.Info_DrawnLast_ConfirmedNG : R.string.Info_DrawnLast_Confirmed));//~9308I~//~0303R~
        UserAction.showInfoAll(0/*opt*/,(swFoundNG ? R.string.Info_DrawnLast_ConfirmedNG : R.string.Info_DrawnLast_Confirmed));//~0303R~
    }                                                              //~9308I~
    //*************************************************************************//~9308R~//~9309R~
    //*on Server send GCM_OPEN                                     //~9309I~
    //*************************************************************************//~9309I~
    private void sendOpen(int Pplayer,int Preason)                           //~9308R~//~9309R~//~9423R~
    {                                                            //~9308R~//~9309R~
        if (Dump.Y) Dump.println("UAEndGame.sendOpen reasonResponse="+Arrays.toString(reasonResponse));//~9309I~
		for (int eswn=0;eswn<PLAYERS;eswn++)                           //~9309I~
        {                                                          //~9309I~
	        if (AG.aAccounts.isDummyByCurrentEswn(eswn))           //~9309R~
              if (!AG.aRoundStat.swThinkRobot)                     //~va60I~
            	continue;                                          //~9309I~
	        int pl=AG.aAccounts.eswnToPlayer(eswn);                //~9309I~
	        boolean swReach=AG.aPlayers.getPosReach(pl)>=0;       //~9309I~
//  		if (swReach || reasonResponse[eswn]==EGDR_PENDING)                   //~9309I~//~9423R~
    		if (swReach || (reasonResponse[eswn]==EGDR_PENDING || reasonResponse[eswn]==EGDR_MANGAN_PENDING))//~9423I~
				UAReach.postOpenOnly(pl,swReach);                  //~9309I~
        }                                                          //~9309I~
    }                                                            //~9308R~//~9309R~
//    //*************************************************************************//+va60R~
//    //*on Server, send GCM_OPEN to dealer with tile of reach/pending robot//+va60R~
//    //*************************************************************************//+va60R~
//    private void sendOpenRobot(int Pplayer)                      //+va60R~
//    {                                                            //+va60R~
//        if (Dump.Y) Dump.println("UAEndGame.sendOpenRobot player="+Pplayer+",reasonResponse="+Arrays.toString(reasonResponse));//+va60R~
//        for (int eswn=0;eswn<PLAYERS;eswn++)//all robot          //+va60R~
//        {                                                        //+va60R~
//            if (!AG.aAccounts.isDummyByCurrentEswn(eswn))        //+va60R~
//                continue;                                        //+va60R~
//            int pl=AG.aAccounts.eswnToPlayer(eswn);   //robot    //+va60R~
//            int opt;                                             //+va60R~
//            if (AG.aPlayers.getPosReach(pl)>=0)                  //+va60R~
//                opt=OPT_OPEN_ONLY_REACH;                         //+va60R~
//            else                                                 //+va60R~
//            if (reasonResponse[eswn]==EGDR_PENDING)              //+va60R~
//                opt=OPT_OPEN_ONLY_PENDING;                       //+va60R~
//            else                                                 //+va60R~
//                opt=0;                                           //+va60R~
//            if (opt!=0)                                          //+va60R~
//                sendOpenRobotToClient(opt,Pplayer,pl,eswn);      //+va60R~
//        }                                                        //+va60R~
//    }                                                            //+va60R~
//    //*************************************************************************//+va60R~
//    //*on Server, send GCM_OPEN to dealer with tile of reach/pending robot//+va60R~
//    //*************************************************************************//+va60R~
//    private void sendOpenRobotToClient(int PoptOpen,int Pplayer,int PplayerRobot,int PeswnRobot)//+va60R~
//    {                                                            //+va60R~
//        if (Dump.Y) Dump.println("UAEndGame.sendOpenRobotToClient opetOpen="+PoptOpen+",player="+Pplayer+",playerRobot="+PplayerRobot+",eswnRobot="+PeswnRobot);//+va60R~
//        TileData[] tds=AG.aPlayers.getHands(PplayerRobot);       //+va60R~
//        int ctr=tds.length;                                      //+va60R~
////        for (int eswn=0;eswn<PLAYERS;eswn++)    //all real     //+va60R~
////        {                                                      //+va60R~
////            if (AG.aAccounts.isDummyByCurrentEswn(eswn))       //+va60R~
////                continue;                                      //+va60R~
////            int pl=AG.aAccounts.eswnToPlayer(eswn);            //+va60R~
////            String msgDataToClient=AG.aUAReach.makeMsgDataToClient(pl,PoptOpen,tds,ctr);//+va60R~
////            sendToClient(swSendAll,PactionID,Pplayer,msgDataToClient);//+va60R~
////        }                                                      //+va60R~
//          String msgDataToClient=UA.UARE.makeMsgDataToClient(Pplayer,PoptOpen,tds,ctr);//+va60R~
//          UA.sendToClient(true/*all*/,false/*robot*/,GCM_OPEN,Pplayer,msgDataToClient);//+va60R~
//    }                                                            //+va60R~
    //*************************************************************************//~9518I~
    //*on Server send GCM_OPEN                                     //~9518I~
    //*************************************************************************//~9518I~
    private boolean sendOpenOnlyToOtherClient(int Pplayer,int Preason)//~9518R~
    {                                                              //~9518I~
//      if (swTestHW2)                                             //~9519R~
//      	return false;                                          //~9519R~
    	boolean rc=false;                                          //~9518I~
        int r=DrawnDlgHW.getReasonFromEswnReason(Preason);         //~9518I~
	    int eswnSender=DrawnDlgHW.getEswnFromEswnReason(Preason);  //~9518I~
        if (r!=EGDR_99TILE)                                        //~9518I~
        	return rc;                                             //~9518R~
        if (Dump.Y) Dump.println("UAEndGame.sendOpenOnlyToOtherClient player="+Pplayer+",reason="+Integer.toHexString(Preason));//~9518I~
		for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9518I~
        {                                                          //~9518I~
	        if (AG.aAccounts.isDummyByCurrentEswn(eswn))           //~9518I~
            	continue;                                          //~9518I~
//          int pl=AG.aAccounts.eswnToPlayer(eswn);                //~9518R~
//          if (pl==Pplayer)                                       //~9518R~
            if (eswn==eswnSender)                                  //~9518I~
            	continue;                                          //~9518I~
//          int pl=AG.aAccounts.eswnToPlayer(eswn);                //~9518R~
            int pl=Pplayer;                                        //~9518R~
	        if (Dump.Y) Dump.println("UAEndGame.sendOpenOnlyToOtherClient sendTo eswn="+eswn+",player="+pl);//~9518R~
//  		UAReach.postOpenOnly(pl,false);                        //~9518I~//~9519R~
    		UAReach.postOpenOnly99(pl);                            //~9519I~
            rc=true;                                               //~9518I~
        }                                                          //~9518I~
        if (Dump.Y) Dump.println("UAEndGame.sendOpenOnlyToOtherClient rc="+rc);//~9518I~
        return rc;                                                 //~9518I~
    }                                                              //~9518I~
    //*******************************************************      //~9310I~
    //*data for LAST_CONFIRMED                                     //~9310I~
    //*******************************************************      //~9310I~
    private String getMsgDialogData()                              //~9310I~
    {                                                              //~9310I~
    	String s="";                                               //~9310I~
    	if (dlgConfirmLast==null)                                  //~9310I~
        {                                                          //~9310I~
	        if (Dump.Y) Dump.println("UAEndGame.getMsgDialogData dlgConfirmLast==null");//~9310I~
        	return s;                                              //~9310I~
        }                                                          //~9310I~
        s=dlgConfirmLast.getValueSendData();                       //~9310I~
	    if (Dump.Y) Dump.println("UAEndGame.getMsgDialogData dlgConfirmLast data="+s);//~9310I~
		return s;                                                  //~9310I~
    }                                                              //~9310I~
    //*******************************************************      //~9310I~
    private void getDialogParm(String[] PstrParm)                  //~9310I~
    {                                                              //~9310I~
	    int[] intp=pasreStrParm(PstrParm);                         //~9310I~
        if (intp==null)                                            //~9310I~
        	return;                                                //~9310I~
//      System.arraycopy(intp,PARMPOS_DRAWN_RESPSTAT,reasonResponse,0,PLAYERS);//~9310R~
//      System.arraycopy(intp,PARMPOS_DRAWN_RESPSTAT2,confirmResponseLast,0,PLAYERS);//~9310R~
	    System.arraycopy(intp,PARMPOS_DRAWN_DIALOGDATA,dialogDataLast,0,PARMPOS_DRAWN_DIALOGDATA_CTR);//~9310I~
//      if (Dump.Y) Dump.println("UAEndGame.getMsgDialogParm reasonResponse="+Arrays.toString(reasonResponse));//~9310R~
	    if (Dump.Y) Dump.println("UAEndGame.getMsgDialogParm dialogDataLast="+Arrays.toString(dialogDataLast));//~9310I~
    }                                                              //~9310I~
    //*******************************************************      //~9310I~
    public static int[] pasreStrParm(String[] PstrParm)                  //~9310I~//~9522R~
    {                                                              //~9310I~
        int[] pout=null;                                           //~9310I~
        if (PstrParm!=null && PstrParm[0]!=null)                   //~9310I~
        {                                                          //~9310I~
//            pout=ACAction.parseAppData(PstrParm[0]);               //~9310I~//~9522R~
			pout=UserAction.parseStrParm(PstrParm);                //~9522I~
	        if (Dump.Y) Dump.println("UAEndGame.parseAppData out="+Arrays.toString(pout));//~9310I~
        }                                                          //~9310I~
		return pout;                                               //~9310I~
    }                                                              //~9310I~
    //*******************************************************      //~9319I~
    public static int getNextGameType(boolean PswContinue)         //~9319I~
    {                                                              //~9319I~
        int rc=NGTP_CONTINUE;                                      //~9319I~
        if (!PswContinue)                                          //~9319I~
        {                                                          //~9319I~
        	int eswn=Status.getDrawnNextGameSeq();                 //~9319R~
	        if (eswn<0)	//not end of set                           //~9319I~
            	rc=NGTP_NEXT;                                      //~9319I~
            else                                                   //~9319I~
//          	rc=NGTP_NEXT+eswn;                                 //~9319I~//~9527R~
//          	rc=NGTP_NEXTROUND;                                 //~9527I~
            	rc=NGTP_NEXT;                                      //~9527I~
        }                                                          //~9319I~
	    if (Dump.Y) Dump.println("UAEndGame.getNextGameType swContinue="+PswContinue+",rc="+Integer.toHexString(rc));//~9527R~
        return rc;                                                 //~9319I~
    }                                                              //~9319I~
    //*******************************************************      //~9610I~
    private void sendToTheClient(boolean PswTransferToReal,int Pplayer/*sendTo*/,String Pmsg)//~9610I~
    {                                                              //~9610I~
	    if (Dump.Y) Dump.println("UAEndGame.sendToTheClient swTransferToReal="+PswTransferToReal+",player="+Pplayer+",msg="+Pmsg);//~9610I~
    	UA.sendToTheClient(PswTransferToReal,Pplayer,GCM_ENDGAME_DRAWN,Pmsg);//~9610I~
    }                                                              //~9610I~
    //*******************************************************      //~9703I~
    //*skip myself and robot at UBTIO by thread inactive           //~9703I~
    //*******************************************************      //~9703I~
    private void sendToTheClient(int Pplayer/*sendTo*/,String Pmsg)//~9703I~
    {                                                              //~9703I~
	    if (Dump.Y) Dump.println("UAEndGame.sendToTheClient player="+Pplayer+",msg="+Pmsg);//~9703I~
    	UA.sendToTheClient(Pplayer,GCM_ENDGAME_DRAWN,Pmsg);        //~9703I~
    }                                                              //~9703I~
    //*******************************************************      //~9703I~
    public void resetResponseHW()                                  //~9703I~
    {                                                              //~9703I~
	    if (Dump.Y) Dump.println("UAEndGame.resetResponseHW");     //~9703I~
        Arrays.fill(reasonResponseHW,EGDR_NONE);                   //~9703I~
    }                                                              //~9703I~
    //*******************************************************      //~0306I~
    public static boolean isDealer()                               //~0306R~
    {                                                              //~0306I~
        boolean rc= OKNGDlg.isDealer();                              //~0306R~
	    if (Dump.Y) Dump.println("UAEndGame.isDealer rc="+rc);     //~0306R~
        return rc;                                                  //~0306I~
    }                                                              //~0306I~
    //*******************************************************      //~0309I~
    public static boolean isUpdateAfterSend()                      //~0309I~
    {                                                              //~0309I~
    	boolean rc=false;                                          //~0309I~
        if (AG.aAccounts.getCurrentDealerReal()==PLAYER_YOU)//real dialer//~0309I~
            if (!AG.aComplete.swSent)                              //~0309R~
            {                                                      //~0309R~
                UView.showToastLong(R.string.Err_UpdateAfterSend); //~0309R~
                rc=true;                                           //~0309R~
            }                                                      //~0309R~
	    if (Dump.Y) Dump.println("UAEndGame.isUpdateAfterSend rc="+rc+",swSent="+AG.aComplete.swSent);//~0309R~
        return rc;
    }                                                              //~0309I~
    //*******************************************************      //~0314I~
    public static boolean isUpdateAfterSendServer()                //~0314I~
    {                                                              //~0314I~
    	boolean rc=false;                                          //~0314I~
//      if (AG.aAccounts.getCurrentDealerReal()==PLAYER_YOU)//real dialer//~0314I~
		if (AG.aUserAction.isServer)                               //~0314I~
            if (!AG.aComplete.swSent)                              //~0314I~
            {                                                      //~0314I~
                UView.showToastLong(R.string.Err_UpdateAfterSend); //~0314I~
                rc=true;                                           //~0314I~
            }                                                      //~0314I~
	    if (Dump.Y) Dump.println("UAEndGame.isUpdateAfterSendServer rc="+rc+",swSent="+AG.aComplete.swSent);//~0314I~
        return rc;                                                 //~0314I~
    }                                                              //~0314I~
    //******************************************************************//~va60I~
    //*for smartRobot, set robot tenpai status                     //~va60I~
    //******************************************************************//~va60I~
    private void setPendingRobot()                                 //~va66I~//~va60M~
    {                                                              //~va66I~//~va60M~
        if (Dump.Y) Dump.println("DrawnDlgLast.setPendingRobot");  //~va60I~
        if (!AG.aAccounts.isServer())                                   //~va66I~//~va60I~
        	return;                                                //~va66I~//~va60M~
        if (!AG.aRoundStat.swThinkRobot)                           //~va60I~
        	return;                                                //~va60I~
        RoundStat.RSPlayer RSP;                                    //~va66I~//~va60M~
        for (int ii=0;ii<PLAYERS;ii++) //current eswn              //~va66I~//~va60M~
        {                                                          //~va66I~//~va60M~
	        RSP=AG.aRoundStat.RSP[ii];                             //~va66I~//~va60M~
        	if (!RSP.swRobot)                                      //~va66I~//~va60M~
            	continue;                                          //~va66I~//~va60M~
    		reasonResponse[ii]=(RSP.getCurrentShanten()==0) ? EGDR_PENDING : EGDR_PENDINGNO;//~va60I~
        }                                                          //~va66I~//~va60M~
        if (Dump.Y) Dump.println("DrawnDlgLast.setPendingRobot reasonResponse="+Arrays.toString(reasonResponse));//~va66I~//~va60I~
    }                                                              //~va66I~//~va60M~
}//class UAEndGame                                                 //~dataR~//~@@@@R~//~v@@@R~
