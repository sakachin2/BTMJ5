//*CID://+DATER~: update#= 598;                                    //~v@@@R~//~9301R~
//**********************************************************************//~v101I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.R;
import com.btmtest.dialog.FinalGameDlg;                            //~9520R~
import com.btmtest.dialog.OKNGDlg;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.dialog.ScoreDlg;
import com.btmtest.utils.Dump;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.dialog.FinalGameDlg.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import com.btmtest.dialog.RuleSetting;                             //~9412I~
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.game.UA.UAEndGame.*;

import android.graphics.Rect;

import java.util.Arrays;

public class LastGame                                              //~9504R~
{                                                                  //~0914I~
    public static final int LASTGAME_GAMEOVER=1;                   //~9522I~
    public static final int LASTGAME_CONTINUE=2;                   //~9522I~
    public static final int LASTGAME_DECLARED=3;                   //~9523I~
    public static final int LASTGAME_REPLY=4;                      //~9523I~
    public static final int LASTGAME_FIXED=5;                      //~9524I~
    public static final int LASTGAME_FIXED_NEXTGAME=6;               //~9524I~
    public static final int LASTGAME_NEXTROUND=7;                  //~9526I~
    public static final int LASTGAME_FIXED_GAMEOVER=8;             //~9605R~
                                                                   //~9522I~
    private static final int PARMPOS_SENDER_ESWN=0;                //~9522I~//~9523R~
    private static final int PARMPOS_MSGID=1;                      //~9523I~
    private static final int PARMPOS_TYPECLOSE=2;                  //~9522I~
    private static final int PARMPOS_ENDGAMETYPE=3;                //~9523I~
    private static final int PARMPOS_NEXTGAMETYPE=4;               //~9523I~
    private static final int PARMPOS_RESP_OKNG=5;                  //~9523I~
                                                                   //~9524I~
    private static final int PARMPOS_GVH_MSGID=1;                  //~9524I~
    private static final int PARMPOS_GVH_NEXTGAME=2;               //~9524I~
                                                                   //~9522I~
    private boolean swClosableRon,swClosablePending,swClosableNotTop,swCloseIfDealerNotPending,swCloseIfAllMinus;//~9504I~
//  private boolean swDrawnManganNext;                             //~9504I~//~9505R~
    private boolean swDrawnManganAsDrawn;                          //~9505I~
//  private boolean swDrawnHWNext;                                 //~9819I~//~9B13R~
//  private int gameType;                                          //~9504I~//~9526R~
    private Complete CMP;                                          //~9504I~
    private int myPosition;                                        //~9504I~
    private int nextRoundEswn;                                     //~9504I~
    public int endgameType,nextgameType;                           //~9522R~
    public int typeClosable=CLOSABLE_NONE;                         //~9521R~
    public int[] respOKNG=new int[PLAYERS];                        //~9523I~
    public int[] endgameScore=new int[PLAYERS];                    //~9524I~
    private int prevClosable=CLOSABLE_NONE;                            //~9526I~
    public boolean swFixed;                                        //~0111I~
    //*****************************************************************//~v@@@I~//~9520R~
    //*************************                                    //~9520I~
    //**from GC.init                                               //~9520I~
    //*************************                                    //~9520I~
	public LastGame()                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~9504R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("LastGame Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~9504R~
        AG.aLastGame=this;                                         //~9506I~
		getRuleSetting();                                          //~9522I~
    }                                                              //~0914I~
    //******************************************                   //~9504I~
    private void getRuleSetting()                                  //~9504I~
    {                                                              //~9504I~
    	swClosableRon=RuleSetting.isFinalLastClosableRon();        //~9504I~
    	swClosablePending=RuleSetting.isFinalLastClosablePending();//~9504I~
    	swClosableNotTop=RuleSetting.isFinalLastClosableNotTop();  //~9504I~
    	swCloseIfDealerNotPending=RuleSetting.isCloseGameFinalLastDealerNotPending();//~9504I~
    	swCloseIfAllMinus=RuleSetting.isCloseGameFinalLastAllMinus();//~9504I~
//  	swDrawnManganNext=RuleSetting.isDrawnManganNext();         //~9504I~//~9505R~
    	swDrawnManganAsDrawn=RuleSettingYaku.isDrawnManganAsDrawn();   //~9505I~
//  	gameType=RuleSetting.getGameSetType();                                 //~9504I~//~9526R~
//      swDrawnHWNext=RuleSetting.isDrawnHWNext();                 //~9819I~//~9B13R~
        if (Dump.Y) Dump.println("LastGame:getRuleSetting swClosableRon="+swClosableRon+",swClosablePending="+swClosablePending+",swClosableNotTop="+swClosableNotTop);//~9504I~
        if (Dump.Y) Dump.println("LastGame:getRuleSetting swCloseIfNotPending="+swCloseIfDealerNotPending+",swCloseIfAllMinus="+swCloseIfAllMinus);//~9504I~//~9819R~//~9B13R~
//      if (Dump.Y) Dump.println("LastGAme:getRuleSetting gameTye="+gameType);//~9504I~//~9526R~
    }                                                              //~9504I~
	//*************************************************************************//~v@@@I~//~9504M~
	//*from Accounts.nextGame                                      //~9504I~
	//*rc=false:continue to next game                              //~9504I~
	//*************************************************************************//~v@@@I~//~9504M~
    public boolean chkFinalGame(int PendgameType,int PnextgameType)//~v@@@I~//~9504I~//~9520R~//~9522R~
    {                                                              //~v@@@I~//~9504M~
    	endgameType=PendgameType;                                  //~9522I~
    	nextgameType=PnextgameType;                                //~9522I~
    	boolean rc=false;                                          //~v@@@I~//~9504M~
        CMP=AG.aComplete;                                          //~9504I~
        myPosition=AG.aAccounts.yourESWN;                          //~9504I~
        if (Dump.Y) Dump.println("LastGame.chkFinalGame endgameType="+PendgameType+",nextgameType="+PnextgameType+",myPosition="+myPosition);//~v@@@I~//~9504R~//~9520R~
		rc=Status.isFinalGame();                                   //~9513I~
        if (!rc)                                                   //~9504I~
        {                                                          //~9504I~
	        if (Dump.Y) Dump.println("LastGame.chkFinalGame return false");//~9504I~//~9513R~//~9520R~
        	return false;                                          //~9504I~
        }                                                          //~9504I~
        showFinalGame();                                           //~9520I~
//      rc=chkRuleOption(PendgameType);    //may show FinalGameDlg                            //~9504I~//~9522R~//~9817R~//~9B13R~
        rc=chkRuleOption(PendgameType,PnextgameType);    //may show FinalGameDlg//~9B13I~
        if (Dump.Y) Dump.println("LastGame.chkFinalGame rc="+rc);  //~9520I~
		return rc;                                                 //~v@@@I~//~9504M~
    }                                                              //~v@@@I~//~9504M~
	//*************************************************************************//~9504I~
    private void showFinalGame()                                //~9504R~//~9520R~
    {                                                              //~9504I~
        String msg=Utils.getStr(R.string.Info_FinalGameEnd,Status.getStringGameSeq());//~9520R~
	    if (Dump.Y) Dump.println("LastGame.showFinalGame msg="+msg);//~9520I~
        AG.aGMsg.drawMsgbar(msg);                                  //~9520I~
    }                                                              //~9520I~
	//*************************************************************************//~9520I~
	//*rc=false:continue to next game                              //~9526I~
	//*************************************************************************//~9526I~
//  private boolean chkRuleOption(int PendgameType)                                //~9520I~//~9522R~//~9B13R~
    private boolean chkRuleOption(int PendgameType,int PnextgameType)//~9B13I~
    {                                                              //~9520I~
    	boolean rc=false;                                          //~9504I~
        int closable=CLOSABLE_NONE;                                            //~9504R~//~9520R~
        boolean swTop=CMP.isCompletedDealerTop();                  //~9521I~
        boolean swSkipDialog=false;                                 //~9526I~
        if (Dump.Y) Dump.println("LastGame.chkRuleOption PendgameType="+PendgameType+",endgameType="+endgameType+",PnextgameType="+PnextgameType+",swTop="+swTop+",swClosableRon="+swClosableRon+",swClosablePending="+swClosablePending+",swClosableNotTop="+swClosableNotTop);        //~9504I~//~9520R~//~9521I~//~9B13R~
        if (Dump.Y) Dump.println("LastGame.chkRuleOption swCloseIfDealerNotPending="+swCloseIfDealerNotPending+",swCloseIfAllMinus="+swCloseIfAllMinus);//~9521R~
        if (CMP.isCompletedRon()) //contains also DrawnManganAsRon //~9521I~
        {                                                          //~9521I~
	        if (CMP.isCompletedDealerRon())                        //~9521I~
    	    {                                                      //~9521I~
        		if (swClosableRon && (swTop || swClosableNotTop))   //~9521I~
            		closable=CLOSABLE_QUERY_DEALER_RON;             //~9521I~
                else                                               //~9521I~
                {                                                  //~9526I~
            		closable=CLOSABLE_CONTINUE_DEALER_RON;         //~9521I~
                	swSkipDialog=chkSkipDialog(closable);          //~9526I~
                }                                                  //~9526I~
        	}                                                      //~9521I~
            else                                                   //~9521I~
            	closable=CLOSABLE_GAMEOVER_NONDEALER_RON;           //~9521I~
        }                                                          //~9521I~
        else	//Drawn                                            //~9521I~
        if (endgameType==EGDR_DRAWN_HW)                            //~9819I~
        {                                                          //~9819I~
//      	if (swDrawnHWNext)	//switch dealer                    //~9819I~//~9B13R~
        	if (PnextgameType==NGTP_NEXT)	//switch dealer        //~9B13I~
    			if (swCloseIfDealerNotPending)	//close if next    //~9819I~
	            	closable=CLOSABLE_GAMEOVER_DRAWNHW;            //~9819I~
                else                                               //~9819I~
		            closable=CLOSABLE_CONTINUE_DRAWNHW_NEXT;       //~9819I~
            else	//renchann                                     //~9819I~
	            closable=CLOSABLE_CONTINUE_DRAWNHW_SAME;           //~9819I~
        }                                                          //~9819I~
        else                                                       //~9819I~
//      if (CMP.isCompletedDealerPending())                        //~9521I~//~9709R~
        if (CMP.isCompletedDealerPendingCont())                    //~9709I~
        {                                                          //~9521I~
            if (swClosablePending && (swTop || swClosableNotTop))   //~9521I~
                closable=CLOSABLE_QUERY_DEALER_PENDING;            //~9521I~
            else                                                   //~9521I~
            {                                                      //~9526I~
                closable=CLOSABLE_CONTINUE_DEALER_PENDING;         //~9521I~
                swSkipDialog=chkSkipDialog(closable);              //~9526I~
            }                                                      //~9526I~
        }                                                          //~9521I~
        else    //not dealer pending                               //~9521I~
        if (swCloseIfDealerNotPending)                             //~9521I~
        {                                                          //~9521I~
            closable=CLOSABLE_GAMEOVER_DEALER_NOTPENDING;          //~9521I~
        }                                                          //~9521I~
        else                                                       //~9521I~
            closable=CLOSABLE_CONTINUE_DRAWN_DEALER_NOTPENDING;    //~9521R~
                                                                   //~9521I~
        if ((closable & CLOSABLE_GAMEOVER)!=0)   //CLOSABLE_GAMEOVER_NONDEALER_RON and CLOSABLE_GAMEOVER_DEALER_NOTPENDING;//~9708R~
        {                                                          //~9521I~
    		if (!swCloseIfAllMinus && CMP.isCompletedAllMinus())   //~9521I~
            	closable=CLOSABLE_CONTINUE_ALLMINUS;               //~9521R~
        }                                                          //~9521I~
        if (Dump.Y) Dump.println("LastGame.chkRuleOption intsCompType="+Arrays.toString(CMP.intsCompType));//~9521I~
        if (swSkipDialog)                                          //~9526I~
        	return false;	//continue nextgame                    //~9526I~
	    showClosable(closable,PendgameType);                              //~9520I~//~9521R~//~9522R~//~9526R~
		return true;                                               //~9520R~
    }                                                              //~9504I~
	//*************************************************************************//~9526I~
    private boolean chkSkipDialog(int Pclosable)                   //~9526I~
    {                                                              //~9526I~
    	boolean rc=Pclosable==prevClosable;                        //~9526I~
        if (Dump.Y) Dump.println("LastGame.chkSkipDialog closable="+Pclosable+",prev="+prevClosable+",rc="+rc);//~9526I~
        prevClosable=Pclosable;                                     //~9526I~
        return rc;                                                 //~9526I~
    }                                                              //~9526I~
	//*************************************************************************//~9504I~
	//*0:end by Ron,1:end by Pending                               //~9520I~
	//*************************************************************************//~9520I~
    private void showClosable(int Ptype,int PendgameType)                                    //~9504R~//~9520R~//~9522R~
    {                                                              //~9504I~
        if (Dump.Y) Dump.println("LastGame.showClosable type="+Integer.toHexString(Ptype)+",myposition="+myPosition);//~9504R~//~9520R~//~9521R~//~9526R~
        typeClosable=Ptype;      	//for FinalGameDlg showDismiss                                  //~9521I~//~9522R~
        FinalGameDlg.newInstance(Ptype,PendgameType).show();                                 //~9504R~//~9520R~//~9522R~
    }                                                              //~9504I~
//    //*************************************************************************//~9522I~//~9524R~
//    //*from USerAction                                           //~9524R~
//    //*************************************************************************//~9522I~//~9524R~
//    public boolean onReceived(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm,String[] PstrParm)//~9522R~//~9524R~
//    {                                                              //~9522I~//~9524R~
//        if (Dump.Y) Dump.println("LastGame.onReceived swServer="+PswServer+",swReceived="+PswReceived+",Pplayer="+Pplayer+",intp="+Arrays.toString(PintParm)+",strParm="+Arrays.toString(PstrParm));//~9522R~//~9524R~
//        int msgid=PintParm[PARMPOS_MSGID];                         //~9522I~//~9524R~
////        typeClosable=PintParm[PARMPOS_TYPECLOSE];                  //~9522R~//~9524R~
////        int[] strParm=UserAction.parseStrParm(PstrParm);           //~9522I~//~9524R~
////        if (Dump.Y) Dump.println("LastGame.onReceived msgid="+msgid+",typeClosable="+typeClosable+",endgameType="+endgameType);//~9522I~//~9524R~
////        switch(msgid)                                              //~9522I~//~9524R~
////        {                                                          //~9522I~//~9524R~
////        case LASTGAME_GAMEOVER:                                    //~9522I~//~9524R~
////            endgameType=strParm[0];                                    //~9522I~//~9523I~//~9524R~
////            gameOver(endgameType);                                 //~9522R~//~9524R~
////            break;                                                 //~9522I~//~9524R~
////        case LASTGAME_CONTINUE:                                    //~9522I~//~9524R~
////            endgameType=strParm[0];                                //~9523I~//~9524R~
////            gameContinue(PswServer,endgameType,nextgameType,typeCloseable);      //~9522I~//~9524R~
////            break;                                                 //~9522I~//~9524R~
////        }                                                          //~9522I~//~9524R~
//        if (Dump.Y) Dump.println("LastGame.onReceived msgid="+msgid);//~9524R~
//        switch(msgid)                                            //~9524R~
//        {                                                        //~9524R~
//        case LASTGAME_FIXED:                                     //~9524R~
//            int nextgameTypeDeclared=PintParm[PARMPOS_GVH_NEXTGAME];//~9524R~
//            gotoNextGame(typeClosable,endgameType,nextgameTypeDeclared);//~9524R~
//            break;                                               //~9524R~
//        }                                                        //~9524R~
//        return true;                                               //~9522I~//~9524R~
//    }                                                              //~9522I~//~9524R~
	//*************************************************************************//~9523I~
	//*msg thru BTIO                                               //~9523I~
	//*************************************************************************//~9523I~
    public static boolean onReceivedAppMsg(int Pmsgid,int Psender,String PmsgData)//~9523I~
    {                                                              //~9523I~
        int[] intp=ACAction.parseAppData(PmsgData);                //~9225R~//~9523I~
		int curEswn=Accounts.getCurrentEswn();                     //~9430I~//~9523I~
        int senderEswn=intp[PARMPOS_SENDER_ESWN];                  //~9524M~
        int msgid=intp[PARMPOS_MSGID];                             //~9524M~
        if (Dump.Y) Dump.println("LastGame.onReceivedAppMsg msgid="+Pmsgid+",submsgid="+msgid+",senderEswn="+senderEswn+",curEswn="+curEswn+",msgData="+PmsgData);//~9523I~//~9524I~//~0217R~
        int typeClose=intp[PARMPOS_TYPECLOSE];                     //~9524M~
        int endgameType=intp[PARMPOS_ENDGAMETYPE];                 //~9523I~
        int nextgameType=intp[PARMPOS_NEXTGAMETYPE];               //~9523I~
        boolean swServer=Accounts.isServer();                              //~9523I~
        switch(msgid)                                              //~9524I~
        {                                                          //~9524I~
        case LASTGAME_REPLY:                                       //~9524I~
            int okng=intp[PARMPOS_RESP_OKNG];                      //~9524I~
            AG.aLastGame.respOKNG[senderEswn]=(okng!=0 ? EGDR_OK : EGDR_NG);    //~9524I~
          if (OKNGDlg.isDealer())	//dealer                               //~0217R~
            FinalGameDlg.repaintOKNG(typeClose,endgameType,nextgameType,senderEswn,AG.aLastGame.respOKNG);//~9524I~
          else                                                     //~0217I~
			if (swServer)  //received from dealer                  //~0217I~
	            sendToClientExceptSender(senderEswn,msgid,typeClose,endgameType,nextgameType,okng);//~0217R~
        	break;                                                 //~9524I~
        case LASTGAME_DECLARED:                                    //~9524I~
			if (swServer)  //received from dealer                  //~9524I~
	            sendToClientExceptSender(senderEswn,msgid,typeClose,endgameType,nextgameType);//~9524R~
	        FinalGameDlg.repaintQuery(typeClose,endgameType,nextgameType);//~9524I~
        	break;                                                 //~9524I~
        case LASTGAME_FIXED:	//on server                        //~9524I~
        	AG.aLastGame.gotoNextGame(swServer,typeClose,endgameType,nextgameType);//~9524R~
        	break;                                                 //~9524I~
        case LASTGAME_FIXED_NEXTGAME:	//svr and client           //~9524I~
        	AG.aLastGame.gameContinueNextGame(swServer,typeClose,endgameType,nextgameType);//~9524I~
        	break;                                                 //~9524I~
        case LASTGAME_GAMEOVER:	//svr and client                   //~9605I~
        	AG.aLastGame.gameContinueNextGame(swServer,typeClose,endgameType,nextgameType);//~9605I~
        	break;                                                 //~9605I~
        case LASTGAME_FIXED_GAMEOVER:	//svr and client,open accountsDlg//~9605R~
        	AG.aLastGame.gameOverFixed(swServer,typeClose,endgameType,nextgameType);//~9605R~
        	break;                                                 //~9605I~
        }                                                          //~9524I~
//        if (swServer)  //received from dealer                      //~9523I~//~9524R~
//        {                                                        //~9524I~
//            FinalGameDlg.sendToAll(msgid,typeClose,endgameType,nextgameType);//~9523R~//~9524R~
//        }                                                        //~9524I~
//        if (msgid==LASTGAME_REPLY)                                 //~9523I~//~9524R~
//        {                                                          //~9523I~//~9524R~
//            int senderEswn=intp[PARMPOS_SENDER_ESWN];              //~9523I~//~9524R~
//            int okng=intp[PARMPOS_RESP_OKNG];                      //~9523I~//~9524R~
//            FinalGameDlg.repaintOKNG(typeClose,endgameType,nextgameType,senderEswn,okng);//~9523I~//~9524R~
//        }                                                          //~9523I~//~9524R~
//        else                                                       //~9523I~//~9524R~
//            FinalGameDlg.repaintQuery(typeClose,endgameType,nextgameType);//~9523R~//~9524R~
        return true;                                               //~9523I~
    }                                                              //~9523I~
//    //*************************************************************************//~9523R~
//    private void updateResponse(int Psender,int Pokng)           //~9523R~
//    {                                                            //~9523R~
//        if (Dump.Y) Dump.println("LastGame.gameOver");           //~9523R~
//        ScoreDlg.endGameOverByLastGame(PendgameType,typeClosable);//~9523R~
//    }                                                            //~9523R~
	//*************************************************************************//~9524I~
	//*on dealer by LASTGAME_FIXED                                 //~9525R~
	//*************************************************************************//~9524I~
    private void gotoNextGame(boolean PswServer,int PtypeClose,int PendgameType,int PnextgameType)//~9524R~
    {                                                              //~9524I~
        if (Dump.Y) Dump.println("LastGame.gotoNextGame nextgameType="+PnextgameType);//~9524I~
        if (PnextgameType==LASTGAME_GAMEOVER)                      //~9524I~
//  	    gameOver(endgameType);                                 //~9524I~//~9605R~
    	    gameOver(PswServer,endgameType);                       //~9605I~
        else                                                       //~9524I~
        if (PnextgameType==LASTGAME_NEXTROUND)                     //~9526I~
		    gameNextRound(PswServer,endgameType,PtypeClose);       //~9526I~
        else                                                       //~9526I~
		    gameContinue(PswServer,endgameType,PtypeClose);      //~9524R~
    }                                                              //~9524I~
	//*************************************************************************//~9522I~
//  private void gameOver(int PendgameType)                        //~9522R~//~9605R~
    private void gameOver(boolean PswServer,int PendgameType)      //~9605I~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("LastGame.gameOver");             //~9522I~
        if (!PswServer)                                            //~9605I~
        {                                                          //~9605I~
	    	sendToAll(LASTGAME_FIXED_GAMEOVER,typeClosable,PendgameType,NGTP_GAMEOVER);//~9605I~
            return;                                                //~9605I~
        }                                                          //~9605I~
      if (AG.aAccounts.isFirstDealerReal())                        //~9605I~
        ScoreDlg.endGameOverByLastGame(PendgameType,typeClosable,endgameScore); //~9522R~//~9524R~
      else                                                         //~9605I~
	    sendToAll(LASTGAME_FIXED_GAMEOVER,typeClosable,PendgameType,NGTP_GAMEOVER);//~9605R~
    }                                                              //~9522I~
	//*************************************************************************//~9605I~
    private void gameOverFixed(boolean PswServer,int PtypeClose,int PendgameType,int PnextgameType)//~9605R~
    {                                                              //~9605I~
        if (Dump.Y) Dump.println("LastGame.gameOverFixed");        //~9605R~
      	if (AG.aAccounts.isFirstDealerReal())                      //~9605I~
        	ScoreDlg.endGameOverByLastGame(PendgameType,typeClosable,endgameScore);//~9605I~
        else                                                       //~9605I~
        if (PswServer)                                             //~9605I~
	    	sendToAll(LASTGAME_FIXED_GAMEOVER,typeClosable,PendgameType,NGTP_GAMEOVER);//~9605I~
    }                                                              //~9605I~
	//*************************************************************************//~9522I~
    private void gameContinue(boolean PswServer,int PendgameType,int PtypeClose)//~9522I~//~9524R~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("LastGame.gameContinue swServer="+PswServer+",endgameType="+PendgameType+",typeClose="+PtypeClose);         //~9522I~//~9524R~//~9526I~
        swFixed=true;	//on dealer                                //~0111I~
	    sendToAll(LASTGAME_FIXED_NEXTGAME,PtypeClose,PendgameType,NGTP_CONTINUE);//~9523I~//~9524I~
        if (PswServer)                                             //~9524I~
	    	ScoreDlg.nextGameByLastGame(PswServer,PendgameType,NGTP_CONTINUE,PtypeClose);//~9524I~
    }                                                              //~9524I~
	//*************************************************************************//~9526I~
    private void gameNextRound(boolean PswServer,int PendgameType,int PtypeClose)//~9526I~
    {                                                              //~9526I~
        if (Dump.Y) Dump.println("LastGame.gameNextRound swServer="+PswServer+",endgameType="+PendgameType+",typeClose="+PtypeClose);//~9526I~//~9819R~
        swFixed=true; //on dealer                                  //~0111I~
	    sendToAll(LASTGAME_FIXED_NEXTGAME,PtypeClose,PendgameType,NGTP_NEXTROUND);//~9526R~
        if (PswServer)                                             //~9526I~
	    	ScoreDlg.nextGameByLastGame(PswServer,PendgameType,NGTP_NEXTROUND,PtypeClose);//~9526R~
    }                                                              //~9526I~
	//*************************************************************************//~9524I~
	//*by LASTGAME_FIXED_NEXTGAME                                  //~9526R~
	//*************************************************************************//~9524I~
    private void gameContinueNextGame(boolean PswServer,int PtypeClose,int PendgameType,int PnextgameType)//~9524R~
    {                                                              //~9524I~
        if (Dump.Y) Dump.println("LastGame.gameContinueNextGame swServer="+PswServer+",endgameType="+PendgameType+",nextgameType="+PnextgameType);//~9524I~//~9526R~
        swFixed=true; //at received                                //~0111R~
        if (PswServer)                                             //~9524I~
//  	    sendToAll(LASTGAME_FIXED_NEXTGAME,PtypeClose,PendgameType,NGTP_CONTINUE);//~9524I~//~9526R~
    	    sendToAll(LASTGAME_FIXED_NEXTGAME,PtypeClose,PendgameType,PnextgameType);//~9526I~
//  	ScoreDlg.nextGameByLastGame(PswServer,PendgameType,NGTP_CONTINUE,PtypeClose);//~9522I~//~9524R~//~9526R~
    	ScoreDlg.nextGameByLastGame(PswServer,PendgameType,PnextgameType,PtypeClose);//~9526I~
    }                                                              //~9522I~
	//*************************************************************************//~9524I~
    public void resetRespStat()                                    //~9524I~
    {                                                              //~9524I~
        if (Dump.Y) Dump.println("LastGame.resetRespStat old="+Arrays.toString(respOKNG));//~9524I~
    	Arrays.fill(respOKNG,0);                                   //~9524I~
    }                                                              //~9524I~
    //**************************************************************************//~9523I~//~9524I~
    //*send to AllClient if server,else to Server                  //~9523I~//~9524I~
    //**************************************************************************//~9523I~//~9524I~
    public static void sendToAll(int Pmsgid,int PtypeClose,int PendgameType,int PnextgameType)//~9523I~//~9524I~
    {                                                              //~9523I~//~9524I~
        if (Dump.Y) Dump.println("FinalGameDlg:sendToAll msgid="+Pmsgid+",endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(PtypeClose));//~9523I~//~9524I~
        LastGame.sendToAll(Pmsgid,PtypeClose,PendgameType,PnextgameType,0/*okng*/);//~9523I~//~9524I~
    }                                                              //~9523I~//~9524I~
    //**************************************************************************//~9523I~//~9524I~
    //*sendto all by reply ok/ng                                   //~9525I~
    //**************************************************************************//~9525I~
    public static void sendToAll(int Pmsgid,int PtypeClose,int PendgameType,int PnextgameType,int Pokng)//~9523I~//~9524I~
    {                                                              //~9523I~//~9524I~
        if (Dump.Y) Dump.println("FinalGameDlg:sendToAll msgid="+Pmsgid+",endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(PtypeClose)+",okng="+Pokng);//~9523I~//~9524I~
        String msgData=Accounts.getCurrentEswn()+MSG_SEPAPP2+Pmsgid+MSG_SEPAPP2+PtypeClose+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+Pokng;//~9523I~//~9524I~
        if (Dump.Y) Dump.println("FinalGameDlg.sendToAll msgData="+msgData);//~9523I~//~9524I~
        AG.aAccounts.sendToAll(GCM_LASTGAME,msgData);   //sendToServer if Client//~0111R~
    }                                                              //~9523I~//~9524I~
    //**************************************************************************//~9524I~
    private static void sendToClientExceptSender(int PsenderEswn,int Pmsgid,int PtypeClose,int PendgameType,int PnextgameType)//~9524I~//+0226R~
    {                                                              //~9524I~
        if (Dump.Y) Dump.println("FinalGameDlg:sendToAllExceptSender senderEswn="+PsenderEswn+",msgid="+Pmsgid+",endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(PtypeClose));//~9524I~
        String msgData=Accounts.getCurrentEswn()+MSG_SEPAPP2+Pmsgid+MSG_SEPAPP2+PtypeClose+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType;//~9524I~
        if (Dump.Y) Dump.println("FinalGameDlg.sendToAllExceptSender msgData="+msgData);//~9524I~
        AG.aAccounts.sendToClientSkipByEswn(false/*swRobot*/,PsenderEswn,GCM_LASTGAME,msgData);//~9524I~
    }                                                              //~9524I~
    //**************************************************************************//~0217I~
    private static void sendToClientExceptSender(int PsenderEswn,int Pmsgid,int PtypeClose,int PendgameType,int PnextgameType,int Pokng)//~0217I~//+0226R~
    {                                                              //~0217I~
        if (Dump.Y) Dump.println("FinalGameDlg:sendToAllExceptSender senderEswn="+PsenderEswn+",msgid="+Pmsgid+",endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(PtypeClose)+",okng="+Pokng);//~0217I~
        String msgData=PsenderEswn+MSG_SEPAPP2+Pmsgid+MSG_SEPAPP2+PtypeClose+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+Pokng;//~0217R~
        if (Dump.Y) Dump.println("FinalGameDlg.sendToAllExceptSender msgData="+msgData);//~0217I~
        AG.aAccounts.sendToClientSkipByEswn(false/*swRobot*/,PsenderEswn/*skip*/,GCM_LASTGAME,msgData);//~0217R~
    }                                                              //~0217I~
}//class LastGame                                                 //~dataR~//~@@@@R~//~v@@@R~//~9504R~
