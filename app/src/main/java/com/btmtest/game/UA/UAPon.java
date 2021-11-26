//*CID://+vah0R~: update#= 616;                                    //~vah0R~
//**********************************************************************//~v101I~
//2021/11/18 vah0 delete unused UnitTest data statement            //~vah0I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Robot;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.game.UADelayed2;
import com.btmtest.game.UserAction;                                //~v@@@I~
import com.btmtest.game.gv.GMsg;
import com.btmtest.game.gv.Hands;
import com.btmtest.game.gv.River;
import com.btmtest.game.gv.Stock;
import com.btmtest.utils.Dump;

import com.btmtest.utils.sound.Sound;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UserAction.*;//~v@@@I~
import static com.btmtest.BT.enums.MsgIDConst.*;                   //~v@@@I~
                                                                   //~v@@@I~
public class UAPon                                                 //~v@@@R~
{                                                                  //~0914I~
    private UserAction UA;                                         //~v@@@I~
    private ACAction ACA;                                          //~v@@@I~
    private Accounts ACC;                                          //~v@@@I~
    private Players PLS;                                           //~v@@@R~
//  private UADelayed UADL;                                        //~v@@6R~
    private UADelayed2 UADL;                                       //~v@@6I~
    private boolean isServer;                                      //~v@@@I~
    private Tiles tiles;                                           //~v@@@I~
    private Hands hands;                                           //~v@@@I~
    private Stock stock;                                           //~v@@@I~
    private River river;                                           //~v@@@R~
    private TileData[] tdsPair;                                    //~v@@@I~
    private boolean[] swSelectedMulti;                             //~v@@6I~
//*************************                                        //~v@@@I~
	public UAPon(UserAction PuserAction)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UAPon Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        UA=PuserAction;                                            //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	public void init()                                             //~v@@@I~
    {                                                              //~v@@@I~
        PLS=AG.aPlayers;                                           //~v@@@R~
        ACC=AG.aAccounts;                                          //~v@@@R~
        ACA=AG.aACAction;                                          //~v@@@I~
        UADL=AG.aUADelayed;                                        //~v@@6I~
        tiles=AG.aTiles;                                           //~v@@@R~
        hands=AG.aHands;                                           //~v@@@I~
        river=AG.aRiver;                                           //~v@@@R~
        stock=AG.aStock;                                           //~v@@@R~
//        accounts=AG.aAccounts;                                   //~v@@@I~
//        acaction=AG.aACAction;                                   //~v@@@I~
        isServer=Accounts.isServer();                              //~v@@@R~
        if (Dump.Y) Dump.println("UAPon init isServer="+isServer); //~v@@@R~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@I~//~0322R~
//    public boolean takePon(int Pplayer)                            //~v@@@I~//~0322R~
//    {                                                              //~v@@@I~//~0322R~
//        if (Dump.Y) Dump.println("UAPos.takePon player="+Pplayer); //~v@@@R~//~0322R~
////      int player=Status.getCurrentPlayer();                      //~v@@@I~//~0322R~
////        if (!players.isYourTurn(actionID,Pplayer))               //~v@@@I~//~0322R~
////            return;                                              //~v@@@I~//~0322R~
//        if (!PLS.takePon(Pplayer))                                 //~v@@@R~//~0322R~
//            return false;                                          //~v@@@I~//~0322R~
//        hands.takePon(Pplayer);                                    //~v@@@I~//~0322R~
//        river.takePon(Pplayer);                                    //~v@@@I~//~0322R~
//        return true;                                               //~v@@@I~//~0322R~
//    }                                                              //~v@@@I~//~0322R~
	//*************************************************************************//~v@@@I~
//  public boolean selectInfo(boolean PswServer,int Pplayer)       //~v@@@R~//~v@@6R~
    public boolean selectInfo(boolean PswServer,int Pplayer,int[] PintParm)//~v@@6I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAPon.selectInfo swServer="+PswServer+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~v@@6R~//~9B18R~
        int rc;                                                    //~v@@@I~
	    if (isLocked(Pplayer))                               //~v@@6I~
        	return false;                                          //~v@@6I~
//      if (!UADL.chkSelectInfo2Touch(PswServer,GCM_PON,Pplayer,PintParm))//~v@@6R~//~9B24R~
//          return false;                                          //~v@@6M~//~9B24R~
        TileData td=PLS.getLastDiscarded();                        //~v@@@R~
//      TileData[] tds=Accounts.getHands();                        //~v@@@I~//~v@@5R~
        TileData[] tds=AG.aHands.getHands(PLAYER_YOU);            //~v@@5I~
	    getTouchSelection();                                       //~9C05I~
//        if ((TestOption.option & TestOption.TO_PON_TEST)!=0) //TODO           //~v@@@I~//~9C04R~//~vah0R~
//        {                                                          //~9C04R~//~vah0R~
//            rc=selectInfoTest(tds,td);                             //~v@@@I~//~9C04R~//~vah0R~
//        }                                                          //~9C04R~//~vah0R~
//        else                                                       //~v@@@I~//~9C04R~//~vah0R~
        	rc=selectInfo(tds,td);                                 //~v@@@R~
        if (Dump.Y) Dump.println("UAPon.selectInfo rc="+rc);       //~9C05I~
        if (rc!=-1) //could not make pair                          //~9B24I~
        {                                                          //~9B24I~
   	    	if (!UADL.chkSelectInfo2Touch(PswServer,GCM_PON,Pplayer,PintParm))//~9B24I~
            	return false;                                      //~9B24I~
        }                                                          //~9B24I~
        if (rc==0)                                                 //~v@@@I~//~v@@6R~
//          AG.aHandsTouch.enableMultiSelectionMode(false);        //~v@@6R~
            ;                                                      //~v@@6I~
        else                                                       //~v@@6I~
        {                                                          //~v@@@I~
        	UA.selectionErr(rc);                                   //~v@@@I~
            if (rc==1)  //redundant                                //~v@@6I~
            {                                                      //~9C04I~
//              AG.aHandsTouch.enableMultiSelectionMode(true);     //~v@@6I~//~9C01R~
                AG.aHandsTouch.enableMultiSelectionModeResettingSingleMode();//~9C01I~
                UADL.chkSelectInfoMultiple(PswServer,GCM_PON,Pplayer,PintParm);//~9C04R~
            }                                                      //~9C04I~
        	return false;                                          //~v@@@I~
        }                                                          //~v@@@I~
        AG.aHandsTouch.enableMultiSelectionMode(false);            //~v@@6I~
        td.setTakenRiver();	//requred at drawRiver, server msg miss it.//~v@@@I~
        tdsPair[PAIRPOS_RIVER_TAKEN]=td;                           //~v@@@R~
        if (!PswServer)                                            //~v@@@I~
	        UA.msgDataToServer=makeMsgDataToServer(Pplayer,tdsPair,PAIRCTR);//~v@@@R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@6I~//~9B18R~
//    //*by popupdialog button OK                                    //~v@@6I~//~9B18R~
//    //*************************************************************************//~v@@6I~//~9B18R~
//    public boolean selectInfo2Touch(boolean PswServer,int Pplayer) //~v@@6I~//~9B18R~
//    {                                                              //~v@@6I~//~9B18R~
//        if (Dump.Y) Dump.println("UserAction.selectInfo swServer="+PswServer+",player="+Pplayer);//~v@@6I~//~9B18R~
//        int rc;                                                    //~v@@6I~//~9B18R~
//        if (isLocked(Pplayer))                                     //~v@@6I~//~9B18R~
//            return false;                                          //~v@@6I~//~9B18R~
//        if (!UADL.chkAction2Touch(PswServer,GCM_PON,Pplayer))      //~v@@6I~//~9B18R~
//            return false;                                          //~v@@6I~//~9B18R~
//        TileData td=PLS.getLastDiscarded();                        //~v@@6I~//~9B18R~
//        TileData[] tds=AG.aHands.getHands(PLAYER_YOU);             //~v@@6I~//~9B18R~
//        if ((TestOption.option & TestOption.TO_PON_TEST)!=0) //TODO//~v@@6I~//~9B18R~
//        {                                                          //~v@@6I~//~9B18R~
//            rc=selectInfoTest(tds,td);                             //~v@@6I~//~9B18R~
//        }                                                          //~v@@6I~//~9B18R~
//        else                                                       //~v@@6I~//~9B18R~
//            rc=selectInfo(tds,td);                                 //~v@@6I~//~9B18R~
//        if (rc==0)                                                 //~v@@6I~//~9B18R~
//            ;                                                      //~v@@6I~//~9B18R~
//        else                                                       //~v@@6I~//~9B18R~
//        {                                                          //~v@@6I~//~9B18R~
//            UA.selectionErr(rc);                                   //~v@@6I~//~9B18R~
//            if (rc==1)  //redundant                                //~v@@6I~//~9B18R~
//                AG.aHandsTouch.enableMultiSelectionMode(true);     //~v@@6I~//~9B18R~
//            return false;                                          //~v@@6I~//~9B18R~
//        }                                                          //~v@@6I~//~9B18R~
//        AG.aHandsTouch.enableMultiSelectionMode(false);            //~v@@6I~//~9B18R~
//        td.setTakenRiver(); //requred at drawRiver, server msg miss it.//~v@@6I~//~9B18R~
//        tdsPair[PAIRPOS_RIVER_TAKEN]=td;                           //~v@@6I~//~9B18R~
//        if (!PswServer)                                            //~v@@6I~//~9B18R~
//            UA.msgDataToServer=makeMsgDataToServer(Pplayer,tdsPair,PAIRCTR);//~v@@6I~//~9B18R~
//        return true;                                               //~v@@6I~//~9B18R~
//    }                                                              //~v@@6I~//~9B18R~
	//*************************************************************************//~v@@@I~
	//*rc=-1:err,0:no redundancy,1:redundant, required user selection//~v@@@I~
	//*************************************************************************//~v@@@I~
    public int selectInfo(TileData[] Ptds, TileData Ptd)            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAPon.selectInfo river td "+Ptd.toString());//~v@@@R~
        TileData[] tds=new TileData[PAIRCTR];                      //~v@@@I~
        TileData[] tdsR5=new TileData[PAIRCTR];                    //~v@@@I~
        TileData[] tdsSelected=new TileData[PAIRCTR];              //~v@@@I~
//      getTouchSelection();                                       //~v@@6I~//~9C05R~
        int rc;                                                    //~v@@@R~
        int ctr=0,ctrAll=0;                                        //~v@@@R~
        int ctrR5=0,ctrSelected=0;                                 //~v@@@R~
        int pos=0;                                                 //~v@@6I~
        for (TileData td:Ptds)                                     //~v@@@I~
        {                                                          //~v@@@I~
        	boolean rcComp=TileData.TDCompareTN(td,Ptd);           //~v@@@R~
        	if (Dump.Y) Dump.println("UAPon.selectInfo compare TD rc="+rcComp+",td="+td.toString());//~v@@@R~
            if (rcComp)                                            //~v@@@R~
            {                                                      //~v@@@I~
//              if (td.isSelected())                               //~v@@@R~//~v@@6R~
                if (swSelectedMulti!=null && swSelectedMulti[pos]) //~v@@6R~
                    tdsSelected[ctrSelected++]=td;                 //~v@@@R~
                else                                               //~v@@@I~
	            if (td.isRed5())                                   //~v@@@I~
                    tdsR5[ctrR5++]=td;       //not selected red5   //~v@@@M~
                else                                               //~v@@@I~
                    tds[ctr++]=td;                                 //~v@@@I~
                ctrAll++;                                          //~v@@@I~
            }                                                      //~v@@@I~
            pos++;                                                 //~v@@6I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UAPon.selectInfo compare TD ctrSelected=="+ctrSelected+",not slected Red5="+ctrR5+",not selected other="+ctr+",ctr match all="+ctrAll);//~9B30I~
        if (ctrSelected==PAIRCTR-1)                                //~v@@@I~
        	rc=0;                                                  //~v@@@I~
        else                                                       //~v@@@I~
        if (ctrSelected>=PAIRCTR)                                  //~v@@@I~
        	rc=-1;                                                 //~v@@@I~
        else	//ctrSelcted<PAIRCTR-1	                           //~v@@@I~
        {                                                          //~v@@@I~
        	if (ctrAll<PAIRCTR-1)                                  //~v@@@R~
	            rc=-1;                                             //~v@@@R~
    	    else                                                   //~v@@@R~
        	if (ctrAll==PAIRCTR-1)                                 //~v@@@R~
	        {                                                      //~v@@@I~
    	        for (int ii=0;ii<ctr;ii++)                         //~v@@@I~
        	        tdsSelected[ctrSelected++]=tds[ii];            //~v@@@R~
    	        for (int ii=0;ii<ctrR5;ii++)                       //~v@@@I~
        	        tdsSelected[ctrSelected++]=tdsR5[ii];          //~v@@@R~
            	rc=0;                                              //~v@@@I~
            }                                                      //~v@@@I~
            else	//ctrAll>=PAIRCTR                              //~v@@@I~
            {                                                      //~v@@@I~
//                if (ctrR5==0)                                      //~v@@@I~//~9C05R~
//                {                                                  //~v@@@I~//~9C05R~
//                    for (int ii=0;ii<ctr;ii++)                     //~v@@@I~//~9C05R~
//                    {                                              //~v@@@I~//~9C05R~
//                        if (ctrSelected==PAIRCTR-1)                //~v@@@I~//~9C05R~
//                            break;                                 //~v@@@I~//~9C05R~
//                        tdsSelected[ctrSelected++]=tds[ii];        //~v@@@R~//~9C05R~
//                    }                                              //~v@@@I~//~9C05R~
//                    rc=0;                                          //~v@@@I~//~9C05R~
//                }                                                  //~v@@@I~//~9C05R~
//                else                                               //~v@@@I~//~9C05R~
//                    rc=1;   //need selection                       //~v@@@I~//~9C05R~
//                int ctrRemain=0;                                 //~9C05R~
//                for (int ii=0;ii<ctr;ii++)                       //~9C05R~
//                {                                                //~9C05R~
//                    if (ctrSelected<PAIRCTR-1)                   //~9C05R~
//                        tdsSelected[ctrSelected++]=tds[ii];      //~9C05R~
//                    else                                         //~9C05R~
//                        ctrRemain++;                             //~9C05R~
//                }                                                //~9C05R~
//                int ctrRemainR5=0;                               //~9C05R~
//                for (int ii=0;ii<ctrR5;ii++)                     //~9C05R~
//                {                                                //~9C05R~
//                    if (ctrSelected<PAIRCTR-1)                   //~9C05R~
//                        tdsSelected[ctrSelected++]=tdsR5[ii];    //~9C05R~
//                    else                                         //~9C05R~
//                        ctrRemainR5++;                           //~9C05R~
//                }                                                //~9C05R~
//                if (ctrRemain!=0 && ctrRemainR5!=0)              //~9C05R~
//                    rc=1;                                        //~9C05R~
//                else                                             //~9C05R~
//                    rc=0;                                        //~9C05R~
//                if (Dump.Y) Dump.println("UAPon.selectInfo rc="+rc+",ctr="+ctr+",ctrR5="+ctrR5+"ctrRemain="+ctrRemain+",ctrRemainR5="+ctrRemainR5);//~9C05I~
				if (ctr!=0 && ctrR5!=0)                            //~9C05I~
                	rc=1;                                          //~9C05I~
                else                                               //~9C05I~
                {                                                  //~9C05I~
                    for (int ii=0;ii<ctr;ii++)                     //~9C05I~
                    {                                              //~9C05I~
                        if (ctrSelected==PAIRCTR-1)                //~9C05I~
                            break;                                 //~9C05I~
                        tdsSelected[ctrSelected++]=tds[ii];        //~9C05I~
                    }                                              //~9C05I~
                    for (int ii=0;ii<ctrR5;ii++)                   //~9C05I~
                    {                                              //~9C05I~
                        if (ctrSelected==PAIRCTR-1)                //~9C05I~
                            break;                                 //~9C05I~
                        tdsSelected[ctrSelected++]=tdsR5[ii];      //~9C05I~
                    }
                    rc=0;//~9C05I~
                }                                                  //~9C05I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        tdsPair=tdsSelected;                                       //~v@@@R~
        if (Dump.Y) Dump.println("UAPon.selectInfo rc="+rc+",ctr="+ctr+",ctr nonselected R5="+ctrR5);//~v@@@R~
        if (Dump.Y) Dump.println("UAPon.selectInfo tdsPair:"+TileData.toString(tdsPair));//~v@@@I~
        return rc;
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public boolean takePon(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@@R~
    {                                                              //~v@@@I~
    	boolean swDraw;                                            //~v@@@R~
        TileData[] tds;                                            //~v@@@R~
    //***********************                                      //~v@@@I~
        if (Dump.Y) Dump.println("UAPon.takePon swServer="+PswServer+",swReceived="+PswReceived+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~v@@@R~//~9B21R~//+vah0R~
//      AG.aUADelayed.actionDone(GCM_PON,PswServer,PswReceived,Pplayer);//~9B21I~//~9B27R~
        if (!PswReceived)                                          //~v@@@I~
        {                                                          //~v@@@I~
            tds=tdsPair;                                           //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            tds=getReceivedPair(PintParm,PARMPOS_TD,PAIRCTR);      //~v@@@I~
            tds[PAIRPOS_RIVER_TAKEN]=PLS.getLastDiscarded();       //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UAPon.takePon tds="+TileData.toString(tds));//~v@@@I~
//      UADiscard.setDiscardedPlayer(tds[PAIRPOS_RIVER_TAKEN]);    //~v@@@R~
        if (PswServer)                                             //~v@@@I~
        {                                                          //~v@@@I~
	        PLS.takePon(Pplayer,tds);    //insert into Hands       //~v@@@R~
//          UserAction.showInfoAllEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Pon));//~v@@6R~//~9C02R~
        	UA.msgDataToClient=makeMsgDataToClient(Pplayer,tds,PAIRCTR);//~v@@@R~
//        	AG.aUADelayed.resetWait(Pplayer);	//switch to next player after delay a moment//~v@@6R~//~9B27R~
          	UADL.resetWait(Pplayer);	//switch to next player after delay a moment//~9B27I~
        	UADL.resetWait2Touch(PswServer,GCM_PON,Pplayer);	//switch to next player after delay a moment//~9B27R~
            stock.drawPendingOpenDora(Pplayer,GCM_PON);            //~v@@6R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			boolean swShadow=Pplayer!=PLAYER_YOU;                  //~v@@@I~
	        PLS.takePonOtherOnClient(swShadow,Pplayer,tds);    //insert into Hands//~v@@@R~
            if (PswReceived)                                       //~v@@6I~
            {                                                      //~v@@6I~
//          	AG.aUADelayed.resetWait(Pplayer);	//switch to next player after delay a moment//~v@@6R~//~9B27R~
            	UADL.resetWait(Pplayer);	//switch to next player after delay a moment//~9B27I~
	        	UADL.resetWait2Touch(PswServer,GCM_PON,Pplayer);	//switch to next player after delay a moment//~9B27R~
		        stock.drawPendingOpenDora(Pplayer,GCM_PON);        //~v@@6R~
            }                                                      //~v@@6I~
        }                                                          //~v@@@I~
//		if (Pplayer==PLAYER_YOU)                                   //~v@@@R~
	    	hands.takePon(Pplayer);  //draw Hands                  //~v@@@R~
        int playerDiscarded=PLS.getLastDiscardedPlayer();          //~v@@@I~
        river.takePon(playerDiscarded);                                     //~v@@@R~
//      UA.UAT.setTimeout(Pplayer,GCM_PON);	//autodiscard timeout  //~v@@6R~
        Robot r=null;                                              //~va60I~
      	if (PswServer)                                             //~va60I~
        	r=ACC.getRobot(Pplayer);                               //~va60I~
      if (r!=null)                                                 //~va60I~
        r.takePon(Pplayer);	//do discrd                            //~va60R~
      else                                                         //~va60I~
        UA.UAT.setAutoDiscardTimeout(PswServer,Pplayer,GCM_PON);	//autodiscard timeout//~v@@6I~
        if (TestOption.getTimingBTIOErr()==TestOption.BTIOE_AFTER_PON)//~v@@6I~
          	TestOption.disableBT();                                //~v@@6I~
//      Gmsg.showHLEswn(0,Pplayer,Utils.getStr(R.string.UserAction_Pon));//~9C02I~
        drawLightToDiscard(Pplayer);                               //~9C06I~
//      GMsg.showHL(0,GCM_PON);                                    //~9C02I~//~0401R~
        GMsg.showHL(0,GCM_PON,Pplayer);                            //~0401I~
    	Sound.play(SOUNDID_PON,false/*not change to beep when beeponly option is on*/);//~0408I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9C06I~
	//*also from UAKan                                             //~9C06I~
	//*************************************************************************//~9C06I~
    public  void drawLightToDiscard(int Pplayer)                   //~9C06I~
    {                                                              //~9C06I~
	    if (Dump.Y) Dump.println("UAPon.drawLightToDiscard player="+Pplayer);//~9C06I~
        boolean swShadow=Pplayer!=PLAYER_YOU;                      //~9C06I~
        AG.aDiceBox.drawLightTakeOne(Pplayer,swShadow);            //~9C06I~
    }                                                              //~9C06I~
	//*************************************************************************//~v@@6I~
	//*also from UAKan                                             //~v@@6I~
	//*************************************************************************//~v@@6I~
    public  boolean isLocked(int Pplayer)                          //~v@@6R~
    {                                                              //~v@@6I~
        boolean rc=false;                                          //~v@@6I~
	    TileData td=PLS.getLastDiscarded();                        //~v@@6I~
		if (td!=null && td.isLockedPonKan())                       //~v@@6I~
		{                                                          //~v@@6I~
	    	if (Dump.Y) Dump.println("UAPon.isLocked ignore Pon by Locked td="+td.toString());//~v@@6I~
//  		if (UA.UADL.isWaiting(Pplayer)>0)                      //~v@@6R~
//          UserAction.sendErr(0,Pplayer,R.string.Info_WaitDiscardTimeoutPonKanAndWaiting);//~v@@6R~
//          else                                                   //~v@@6R~
	        UserAction.sendErr(0,Pplayer,R.string.Info_WaitDiscardTimeoutPonKan);//~v@@6I~
            rc=true;                                               //~v@@6I~
        }                                                          //~v@@6I~
	    if (Dump.Y) Dump.println("UAPon.isLocked rc="+rc);         //~v@@6I~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@@I~
    public  static String makeMsgDataToServer(int Pplayer,TileData[] Ptds,int Pctr)//~v@@@R~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAPon.makeMsgDataToServer ctr="+Pctr);//~v@@@R~
//  	int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~v@@@R~
        StringBuffer sb=new StringBuffer();                        //~v@@@I~
//      sb.append(eswn+MSG_SEPAPP2);                               //~v@@@R~
        sb.append(Pctr+MSG_SEPAPP2);                               //~v@@6R~
		for (int ii=0;ii<Pctr;ii++)                                //~v@@@I~
        {                                                          //~v@@@I~
	        sb.append(ACAction.strTD(Ptds[ii])+MSG_SEPAPP2);                 //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UAPon.makeMsgDataToServer sb="+sb);//~v@@@R~
        return sb.toString();                                      //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public  static String makeMsgDataToClient(int Pplayer,TileData[] Ptds,int Pctr)//~v@@@I~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAPon.makeMsgDataToClient ctr="+Pctr);//~v@@@I~
		int eswn=Accounts.playerToEswn(Pplayer);                        //~v@@@I~
//      String data=eswn+MSG_SEPAPP2+makeMsgDataToServer(Pplayer,Ptds,Pctr);//~v@@@I~//~v@@6R~
        String data=eswn+MSG_SEPAPP2+makeMsgDataToServer(Pplayer,Ptds,Pctr);//~v@@6R~
        if (Dump.Y) Dump.println("UAPon.makeMsgDataToClientr data="+data);//~v@@@I~
        return data;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static TileData[] getReceivedPair(int[] PintParm,int Ppos,int PpairCtr)//~v@@@I~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAPon.getReceivedPair ctr="+PpairCtr);//~v@@@I~
        int pairCtr;
        if (PpairCtr<0)                                            //~v@@6I~
        	pairCtr=PintParm[Ppos];                                //~v@@6I~
        else                                                       //~v@@6I~
        	pairCtr=PpairCtr;                                      //~v@@6I~
        TileData[] tds=new TileData[pairCtr];                     //~v@@@I~//~v@@6I~
    	for (int ii=0,pos=Ppos+1;ii<pairCtr;ii++,pos+=PARMPOS_CTRFORTD)//~v@@@I~//~v@@6R~
        {                                                          //~v@@@I~
        	tds[ii]=new TileData(true/*swEswnToPlayer*/,PintParm,pos);                        //~v@@@I~
        }
        return tds;//~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@I~//~v@@6R~
//    public static void setDiscardedPlayer(TileData[] Ptds)         //~v@@@R~//~v@@6R~
//    {                                                              //~v@@@I~//~v@@6R~
//        if (Dump.Y) Dump.println("UAPon.setDiscardedPlayer in ="+TileData.toString(Ptds));//~v@@@I~//~v@@6R~
//        for (TileData td:Ptds)                                     //~v@@@I~//~v@@6R~
//        {                                                          //~v@@@I~//~v@@6R~
//            if (td.isDiscarded())                                  //~v@@@I~//~v@@6R~
//                UADiscard.setDiscardedPlayer(td);                  //~v@@@I~//~v@@6R~
//        }                                                          //~v@@@I~//~v@@6R~
//    }                                                              //~v@@@I~//~v@@6R~
    //*************************************************************************//~v@@6I~
    private void getTouchSelection()                               //~v@@6I~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UAPon.getTouchSelection");      //~v@@6I~//~9B30R~
        swSelectedMulti=AG.aHandsTouch.getSelectedMulti();         //~v@@6I~
        if (Dump.Y) Dump.println("UAPon.getTouchSelection swSelectMulti="+Arrays.toString(swSelectedMulti));//~v@@6I~
    }                                                              //~v@@6I~
//    //*************************************************************************//~v@@@M~//~vah0R~
//    //*Test TODO                                                   //~v@@@M~//~vah0R~
//    //*************************************************************************//~v@@@M~//~vah0R~
//    private int  selectInfoTest(TileData[] Ptds, TileData Ptd)     //~v@@@M~//~vah0R~
//    {                                                              //~v@@@M~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.selectInfoTest river td="+Ptd.toString());//~v@@@M~//~vah0R~
//        utPon();                                                   //~v@@@M~//~vah0R~
//        TileData[] tds=new TileData[PAIRCTR];                      //~v@@@M~//~vah0R~
//        tds[0]=Ptds[0];                                            //~v@@@M~//~vah0R~
//        tds[1]=Ptds[1];                                            //~v@@@M~//~vah0R~
//        tdsPair=tds;                                               //~v@@@M~//~vah0R~
//        int rc=0;                                                  //~9C05I~//~vah0R~
//        if ((TestOption.option2 & TestOption.TO2_WAITSELECT_PON)!=0) //TODO//~9C05I~//~9C12R~//~vah0R~
//        {                                                          //~9C05I~//~vah0R~
////          getTouchSelection();                                   //~9C05R~//~vah0R~
//            if (AG.aHandsTouch.getPosOld()==1)                     //~9C07I~//~vah0R~
//                rc=0;                                              //~9C07I~//~vah0R~
//            else                                                   //~9C07I~//~vah0R~
//            if (swSelectedMulti!=null && !swSelectedMulti[0])      //~9C05R~//~vah0R~
//                rc=1;   //wait multi selecion                      //~9C05R~//~vah0R~
//        }                                                          //~9C05I~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.selectInfoTest rc="+rc+",testoption2="+Integer.toHexString(TestOption.option2));//~9C05I~//~vah0R~
//        return rc;                                                  //~v@@@M~//~9C05R~//~vah0R~
//     }                                                             //~v@@@M~//~vah0R~
//    //*************************************************************************//~v@@@M~//~vah0R~
//    //*For UnitTest                                                //~v@@@M~//~vah0R~
//    //*************************************************************************//~v@@@M~//~vah0R~
//    public void utPon()                                            //~v@@@M~//~vah0R~
//    {                                                              //~v@@@M~//~vah0R~
//        boolean swDraw;                                            //~v@@@M~//~vah0R~
//        TileData[] tds;                                          //~vah0R~
//        int rc;//~v@@@M~                                         //~vah0R~
//        TileData td=new TileData(0,4);                             //~v@@@M~//~vah0R~
//    //***********************                                      //~v@@@M~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon swSelectedMulti="+Arrays.toString(swSelectedMulti));//~9C05I~//~vah0R~
//        boolean[] swSelectedMultiSV=swSelectedMulti;               //~9C05I~//~vah0R~
//        swSelectedMulti=new boolean[HANDCTR];                      //~v@@6I~//~vah0R~
//        Arrays.fill(swSelectedMulti,false);                        //~v@@6I~//~vah0R~
////*1                                                               //~v@@6I~//~vah0R~
//        tds=new TileData[3];                                       //~v@@@M~//~vah0R~
//        tds[0]=new TileData(0,4,false,0);                          //~v@@@M~//~vah0R~
//        tds[1]=new TileData(0,4,false,1);                          //~v@@@M~//~vah0R~
//        tds[2]=new TileData(0,5,false,2);                          //~v@@@M~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-1 rc="+rc);          //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*2                                                               //~v@@6I~//~vah0R~
//        tds[0]=new TileData(0,4,false,0);                          //~v@@@M~//~vah0R~
//        tds[1]=new TileData(0,4,true, 1);                          //~v@@@M~//~vah0R~
//        tds[2]=new TileData(0,5,false,2);                          //~v@@@M~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-2 rc="+rc);          //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*3                                                               //~v@@6I~//~vah0R~
//        tds[0]=new TileData(0,4,true, 0);                          //~v@@@M~//~vah0R~
//        tds[1]=new TileData(0,4,true, 1);                          //~v@@@M~//~vah0R~
//        tds[2]=new TileData(0,5,false,2);                          //~v@@@M~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-3 rc="+rc);          //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*4                                                               //~v@@6I~//~vah0R~
//        tds[0]=new TileData(0,4,false,0);                          //~v@@@M~//~vah0R~
//        tds[1]=new TileData(0,4,false,1);                          //~v@@@M~//~vah0R~
//        tds[2]=new TileData(0,4,false,2);                          //~v@@@M~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-4 rc="+rc);          //~v@@6I~//~9C05R~//~vah0R~
////*5                                                                   //~v@@@M~//~v@@6R~//~vah0R~
//        tds[0]=new TileData(0,4,true ,0);                          //~v@@@M~//~vah0R~
//        tds[1]=new TileData(0,4,false,1);                          //~v@@@M~//~vah0R~
//        tds[2]=new TileData(0,4,false,2);                          //~v@@@M~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-5 rc="+rc);          //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*6                                                               //~v@@6I~//~vah0R~
//        tds[0]=new TileData(0,4,true ,0);                          //~v@@@M~//~vah0R~
//        tds[1]=new TileData(0,4,false,1);                          //~v@@@M~//~vah0R~
//        tds[2]=new TileData(0,4,true ,2);                          //~v@@@M~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-6 rc="+rc);          //~v@@6I~//~9C05R~//~vah0R~
////*7                                                               //~v@@6I~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
//        tds[0]=new TileData(0,4,true ,0);                          //~v@@@M~//~vah0R~
//        tds[1]=new TileData(0,4,false,1);                          //~v@@@M~//~vah0R~
//        tds[2]=new TileData(0,4,false,2);                          //~v@@@M~//~vah0R~
//        rc=selectInfo(tds,td);                                     //~v@@6I~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-7 rc="+rc);          //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*8                                                               //~v@@6I~//~vah0R~
////      tds[0].setSelected(true);                                  //~v@@@M~//~v@@6R~//~vah0R~
////      tds[1].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
////      tds[2].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
//        swSelectedMulti[0]=true;                                   //~v@@6R~//~vah0R~
//        swSelectedMulti[1]=false;                                  //~v@@6R~//~vah0R~
//        swSelectedMulti[2]=false;                                  //~v@@6R~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-8 rc="+rc);          //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*9                                                               //~v@@6I~//~vah0R~
////      tds[0].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
////      tds[1].setSelected(true);                                  //~v@@@M~//~v@@6R~//~vah0R~
////      tds[2].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
//        swSelectedMulti[0]=false;                                  //~v@@6R~//~vah0R~
//        swSelectedMulti[1]=true;                                   //~v@@6R~//~vah0R~
//        swSelectedMulti[2]=false;                                  //~v@@6R~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-9 rc="+rc);          //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*10                                                              //~v@@6I~//~vah0R~
////        tds[0].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
////        tds[1].setSelected(true);                                  //~v@@@M~//~v@@6R~//~vah0R~
////        tds[2].setSelected(true);                                  //~v@@@M~//~v@@6R~//~vah0R~
//        swSelectedMulti[0]=false;                                  //~v@@6R~//~vah0R~
//        swSelectedMulti[1]=true;                                   //~v@@6R~//~vah0R~
//        swSelectedMulti[2]=true;                                   //~v@@6R~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-10 rc="+rc);         //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*11                                                              //~v@@6I~//~vah0R~
//        tds[0]=new TileData(0,4,false,0);                          //~v@@@M~//~vah0R~
//        tds[1]=new TileData(0,4,true ,1);                          //~v@@@M~//~vah0R~
//        tds[2]=new TileData(0,4,true ,2);                          //~v@@@M~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-11 rc="+rc);         //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*12                                                              //~v@@6I~//~vah0R~
////        tds[0].setSelected(true);                                  //~v@@@M~//~v@@6R~//~vah0R~
////        tds[1].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
////        tds[2].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
//        swSelectedMulti[0]=true;                                   //~v@@6R~//~vah0R~
//        swSelectedMulti[1]=false;                                  //~v@@6R~//~vah0R~
//        swSelectedMulti[2]=false;                                  //~v@@6R~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-12 rc="+rc);         //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*13                                                              //~v@@6I~//~vah0R~
////      tds[0].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
////      tds[1].setSelected(true );                                 //~v@@@M~//~v@@6R~//~vah0R~
////      tds[2].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
//        swSelectedMulti[0]=false;                                  //~v@@6I~//~vah0R~
//        swSelectedMulti[1]=true;                                   //~v@@6I~//~vah0R~
//        swSelectedMulti[2]=false;                                  //~v@@6I~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-13 rc="+rc);         //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////*14                                                              //~v@@6I~//~vah0R~
////        tds[0].setSelected(true);                                  //~v@@@M~//~v@@6R~//~vah0R~
////        tds[1].setSelected(true);                                  //~v@@@M~//~v@@6R~//~vah0R~
////        tds[2].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
//        swSelectedMulti[0]=true;                                   //~v@@6R~//~vah0R~
//        swSelectedMulti[1]=true;                                   //~v@@6R~//~vah0R~
//        swSelectedMulti[2]=false;                                  //~v@@6R~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-14 rc="+rc);         //~v@@6I~//~9C05R~//~vah0R~
//                                                                   //~v@@@M~//~vah0R~
////        tds[0].setSelected(false);                                 //~v@@@M~//~v@@6R~//~vah0R~
////        tds[1].setSelected(true);                                  //~v@@@M~//~v@@6R~//~vah0R~
////        tds[2].setSelected(true);                                  //~v@@@M~//~v@@6R~//~vah0R~
//        swSelectedMulti[0]=false;                                  //~v@@6R~//~vah0R~
//        swSelectedMulti[1]=true;                                   //~v@@6R~//~vah0R~
//        swSelectedMulti[2]=true;                                   //~v@@6R~//~vah0R~
//        rc=selectInfo(tds,td);                                        //~v@@@M~//~v@@6R~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon-15 rc="+rc);         //~v@@6I~//~9C05R~//~vah0R~
//        swSelectedMulti=swSelectedMultiSV;                         //~9C05I~//~vah0R~
//        if (Dump.Y) Dump.println("UAPon.utPon exit swSelectedMulti="+Arrays.toString(swSelectedMulti));//~9C05I~//~vah0R~
//    }                                                              //~v@@@M~//~vah0R~
}//class                                                           //~v@@@R~
