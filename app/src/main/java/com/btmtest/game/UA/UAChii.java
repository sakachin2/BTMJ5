//*CID://+va60R~: update#= 624;                                    //~9B30R~//+va60R~
//**********************************************************************//~v101I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //+va60I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.game.UADelayed2;                                //~v@@@I~
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
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UserAction.*;//~v@@@I~
import static com.btmtest.BT.enums.MsgIDConst.*;                   //~v@@@I~
                                                                   //~v@@@I~
public class UAChii                                                //~v@@@R~
{                                                                  //~0914I~
    private UserAction UA;                                         //~v@@@I~
    private ACAction ACA;                                          //~v@@@I~
    private Accounts ACC;                                          //~v@@@I~
    private Players PLS;                                           //~v@@@R~
    private UADelayed2 UADL;                                       //~v@@@I~
    private boolean isServer;                                      //~v@@@I~
    private Tiles tiles;                                           //~v@@@I~
    private Hands hands;                                           //~v@@@I~
    private Stock stock;                                           //~v@@@I~
    private River river;                                           //~v@@@R~
    private TileData[] tdsPair;                                    //~v@@@I~
    private boolean[] swSelectedMulti;                               //~v@@@R~
//  private int delayChii;                                         //~v@@@R~
//*************************                                        //~v@@@I~
	public UAChii(UserAction PuserAction)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UAChii Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        UA=PuserAction;                                            //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	public void init()                                             //~v@@@I~
    {                                                              //~v@@@I~
        PLS=AG.aPlayers;                                           //~v@@@R~
        ACC=AG.aAccounts;                                          //~v@@@R~
        ACA=AG.aACAction;                                          //~v@@@I~
        UADL=AG.aUADelayed;                                        //~v@@@I~
        tiles=AG.aTiles;                                           //~v@@@R~
        hands=AG.aHands;                                           //~v@@@I~
        river=AG.aRiver;                                           //~v@@@R~
        stock=AG.aStock;                                           //~v@@@R~
//        accounts=AG.aAccounts;                                   //~v@@@I~
//        acaction=AG.aACAction;                                   //~v@@@I~
        isServer=Accounts.isServer();                              //~v@@@R~
//      delayChii=OperationSetting.getDelayChii();                 //~v@@@R~
        if (Dump.Y) Dump.println("UAChii init isServer="+isServer);//~v@@@R~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@M~//~0322R~
//    public boolean takeChii(int Pplayer)                           //~v@@@M~//~0322R~
//    {                                                              //~v@@@M~//~0322R~
////      int player=Status.getCurrentPlayer();                      //~v@@@M~//~0322R~
//        if (Dump.Y) Dump.println("UserAction.takeChii player="+Pplayer);//~v@@@M~//~0322R~
////        if (!players.isYourTurn(actionID,Pplayer))               //~v@@@M~//~0322R~
////            return;                                              //~v@@@M~//~0322R~
//        if (!PLS.takeChii(Pplayer))                            //~v@@@M~//~0322R~
//            return false;                                          //~v@@@M~//~0322R~
//        hands.takeChii(Pplayer);                                   //~v@@@M~//~0322R~
//        river.takeChii(Pplayer);                                   //~v@@@M~//~0322R~
//        return true;                                               //~v@@@M~//~0322R~
//    }                                                              //~v@@@M~//~0322R~
	//*************************************************************************//~v@@@M~
	//*************************************************************************//~v@@@I~
//  public boolean selectInfo(boolean PswServer,int Pplayer)       //~v@@@R~
    public boolean selectInfo(boolean PswServer,int Pplayer,int[] PintParm)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UserAction.selectInfo PswServer="+PswServer+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~v@@@R~
        int rc;                                                    //~v@@@I~
	    if (isLocked(Pplayer))                                     //~v@@@I~
        	return false;                                          //~v@@@I~
//      if (!UADL.chkSelectInfo2Touch(PswServer,GCM_CHII,Pplayer,PintParm))//~v@@@R~
//          return false;                                          //~v@@@R~
        TileData td=PLS.getLastDiscarded();                        //~v@@@R~
        TileData[] tds=AG.aHands.getHands(PLAYER_YOU);            //~v@@5I~
	    getTouchSelection();                                       //~v@@@I~
        if ((TestOption.option & TestOption.TO_CHII_TEST)!=0) //TODO//~v@@@R~
        {
            rc=selectInfoTest(tds, td);                       //~v@@@R~
        }
        else                                                       //~v@@@I~
        	rc=selectInfo(tds,td);                           //~v@@@R~
        if (rc!=-1)                                                //~v@@@I~
        {                                                          //~v@@@I~
			if (!UADL.chkSelectInfo2Touch(PswServer,GCM_CHII,Pplayer,PintParm))//~v@@@I~
				return false;                                      //~v@@@I~
        }                                                          //~v@@@I~
//        boolean swRetried=false;                                   //~v@@@I~//~9B30R~
//        if (rc==1 && AG.aHandsTouch.enableMultiSelectionModeWithPreTouched())//~v@@@I~//~9B30R~
//        {                                                          //~v@@@I~//~9B30R~
//            rc=selectInfo(tds,td);  //retry with pre-touched tile  //~v@@@I~//~9B30R~
//            swRetried=true;                                        //~v@@@I~//~9B30R~
//        }                                                          //~v@@@I~//~9B30R~
        if (rc==0)  //selected                                     //~v@@@I~
        {                                                          //~v@@@I~
            AG.aHandsTouch.enableMultiSelectionMode(false);        //~v@@@I~
//            if (!td.isChiiDeclared())                            //~v@@@R~
//            {                                                    //~v@@@R~
//                declare(PswServer,td);                           //~v@@@R~
//                return false;   //scheduled later                //~v@@@R~
//            }                                                    //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@R~
        {                                                          //~v@@@I~
        	UA.selectionErr(rc);                                   //~v@@@I~
            if (rc==1)  //redundant                                //~v@@@I~
            {                                                      //~9C04I~
//              if (!swRetried)                                      //~v@@@I~//~9B30R~
//              AG.aHandsTouch.enableMultiSelectionMode(true);    //~v@@@I~//~9B30R~
                AG.aHandsTouch.enableMultiSelectionModeResettingSingleMode();//~9B30I~
                UADL.chkSelectInfoMultiple(PswServer,GCM_CHII,Pplayer,PintParm);//~9C04R~
            }                                                      //~9C04I~
        	return false;                                          //~v@@@I~
        }                                                          //~v@@@I~
        td.setTakenRiver();	//requred at drawRiver, server msg miss it.//~v@@@I~
//      tdsPair[PAIRPOS_RIVER_TAKEN]=td;    //Pon only,chii set already//~v@@@R~
		if (!PswServer)                                            //~v@@@R~
	        UA.msgDataToServer=makeMsgDataToServer(Pplayer,tdsPair,PAIRCTR);//~v@@@R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*rc=-1:err,0:no redundancy,1:redundant, required user selection//~v@@@I~
	//*************************************************************************//~v@@@I~
    public int selectInfo(TileData[] Ptds, TileData Ptd)            //~v@@@I~
    {                                                              //~v@@@I~
        int selectStart,selectEnd;                                 //~v@@@I~
    	int dupctr,pairctr,pairStart,pairEnd,selectCtr=0,type,number;//~v@@@R~
        TileData td;                                               //~v@@@I~
    //*******************                                          //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.selectInfo river td "+Ptd.toString());//~v@@@R~
        type=Ptd.type;                                             //~v@@@I~
        number=Ptd.number;                                         //~v@@@I~
        if (type==TT_JI)                                           //~v@@@I~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("UAChii.selectInfo tile type err");//~v@@@I~
        	return -1;                                             //~v@@@I~
        }                                                          //~v@@@I~
        TileData[][] tds=new TileData[PIECE_NUMBERCTR][PIECE_DUPCTR];//~v@@@I~
        TileData[] tdsOut=new TileData[PAIRCTR];                   //~v@@@I~
        int [] dupCtr=new int[PIECE_NUMBERCTR];                    //~v@@@I~
        boolean[] swSelect=new boolean[PIECE_NUMBERCTR];           //~v@@@I~
        Arrays.fill(dupCtr,0);                                      //~v@@@I~
        Arrays.fill(swSelect,false);                               //~v@@@I~
        int sz=Ptds.length;                                        //~v@@@R~
     //*pickup candidate                                           //~v@@@I~
        boolean swType=false;
        boolean swDupSelect=false;                                 //~9C01I~
        for (int ii=0;ii<sz;ii++)             //~v@@@I~
        {                                                          //~v@@@I~
        	td=Ptds[ii];                                           //~v@@@I~
        	if (td.type==type)                                     //~v@@@I~
            {                                                      //~v@@@I~
            	swType=true;                                       //~v@@@I~
            	int num=td.number;                                 //~v@@@I~
            	if (num>=number-(PAIRCTR-1)                        //~v@@@I~
                &&  num<=number+(PAIRCTR-1)                        //~v@@@I~
                &&  num!=number)                                   //~v@@@I~
                {                                                  //~v@@@I~
                    boolean swselected=(swSelectedMulti!=null && swSelectedMulti[ii]);//~v@@@I~//~9B30R~
////                  if (dupCtr[num]==0 || !tds[num][0].isSelected())//~v@@@R~//~9B30R~
//                    if (dupCtr[num]==0 || !swselected)             //~v@@@I~//~9B30R~
//                    {                                              //~v@@@I~//~9B30R~
////                      if (td.isSelected())     //set at top      //~v@@@R~//~9B30R~
////                      if (swSelectedMulti!=null && swSelectedMulti[ii])//~v@@@R~//~9B30R~
//                        if (swselected)                             //~v@@@I~//~9B30R~
//                        {                                          //~v@@@I~//~9B30R~
//                            tds[num][0]=td;                        //~v@@@I~//~9B30R~
//                            dupCtr[num]=1;                         //~v@@@R~//~9B30R~
//                            swSelect[num]=true;                    //~v@@@I~//~9B30R~
//                        }                                          //~v@@@I~//~9B30R~
//                        else                                       //~v@@@R~//~9B30R~
//                        {                                          //~v@@@I~//~9B30R~
//                            tds[num][dupCtr[num]]=td;              //~v@@@R~//~9B30R~
//                            dupCtr[num]++;                         //~v@@@R~//~9B30R~
//                        }                                          //~v@@@I~//~9B30R~
//                    }                                              //~v@@@I~//~9B30R~
                    int ctr=dupCtr[num];                               //~9B30I~
                    tds[num][ctr]=td;                              //~9B30I~
                    if (swselected)                                //~9C01R~
                    {                                              //~9C01I~
                    	if (swSelect[num])	//dup selection        //~9C01I~
                        	swDupSelect=true;                      //~9C01I~
                        else                                       //~9C01I~
                        if (ctr!=0)                                //~9C01I~
                        {                                              //~9B30I~//~9C01R~
                            TileData td0=tds[num][0];                  //~9B30I~//~9C01R~
                            tds[num][0]=td;        //move selected to top//~9B30I~//~9C01R~
                            tds[num][ctr]=td0;                         //~9B30I~//~9C01R~
                        }                                              //~9B30I~//~9C01R~
        	            swSelect[num]=true;                        //~9C01I~
                    }                                              //~9C01I~
                    dupCtr[num]=ctr+1;                             //~9B30I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
            	if (swType)                                        //~v@@@I~
                	break;                                         //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.selectInfo tdss="+TileData.toString(tds));//~v@@@I~
        if (Dump.Y) Dump.println("UAChii.selectInfo dupctr="+Arrays.toString(dupCtr)+",swSelect="+Arrays.toString(swSelect));//~v@@@I~//~9B30R~
        if (swDupSelect)                                           //~9C01I~
        {                                                          //~9C01I~
	        if (Dump.Y) Dump.println("UAChii.selectInfo dup select of same Number");//~9C01I~
			return -1;		//could not make pair                  //~9C01I~
        }                                                          //~9C01I~
     //*chk pair                                                   //~v@@@I~
        tds[number][0]=Ptd;	//discarded                            //~v@@@I~
        dupCtr[number]=1;	//discarded                            //~v@@@I~
        swSelect[number]=true;	//discarded                    //~v@@@I~
        pairctr=0;                                                 //~v@@@I~
        pairStart=-1;                                              //~v@@@I~
        selectCtr=0;                                               //~v@@@I~
        selectStart=PIECE_NUMBERCTR;                              //~v@@@I~
        selectEnd=-1;                                              //~v@@@I~
//      boolean swNotContinued=false;                              //~v@@@I~//~9B30R~
//      boolean swSelectionErr=false;                               //~v@@@I~//~9B30R~
        for (int ii=0;ii<PIECE_NUMBERCTR;ii++)                     //~v@@@I~
        {                                                          //~v@@@I~
        	dupctr=dupCtr[ii];                                     //~v@@@I~
            if (dupctr!=0)                                         //~v@@@I~
            {                                                      //~v@@@I~
            	pairctr=0;                                         //~v@@@I~
	            pairStart=ii;                                      //~v@@@I~
        		selectStart=PIECE_NUMBERCTR;                      //~v@@@I~
        		selectEnd=-1;                                      //~v@@@I~
            	for (int jj=ii;jj<PIECE_NUMBERCTR;jj++)            //~v@@@I~
                {                                                  //~v@@@I~
		        	if (dupCtr[jj]==0)                             //~v@@@R~
                    {                                              //~v@@@I~
//                      swNotContinued=true;                       //~v@@@I~//~9B30R~
//                      continue;                                  //~v@@@I~//~9B30R~
                    	break;                                     //~v@@@I~
                    }                                              //~v@@@I~
                    pairctr++;                                     //~v@@@R~
                    if (swSelect[jj])                              //~v@@@I~
                    {                                              //~v@@@I~
//                      if (swNotContinued)                        //~v@@@I~//~9B30R~
//                          swSelectionErr=true;                   //~v@@@I~//~9B30R~
                    	selectStart=Math.min(selectStart,jj);      //~v@@@I~
                    	selectEnd=Math.max(selectEnd,jj);          //~v@@@I~
                        selectCtr++;	//contains discarded       //~v@@@I~
                    }                                              //~v@@@I~
                }                                                  //~v@@@I~
                if (pairctr<PAIRCTR)                                //~v@@@I~
                	continue;                                      //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        pairEnd=pairStart+pairctr;                                 //~v@@@I~
//      if (Dump.Y) Dump.println("UAChii.selectInfo swSelectionErr="+swSelectionErr+",pairCtr="+pairctr+",selectCtr="+selectCtr+",selectStart="+selectStart+",selectEnd="+selectEnd);//~v@@@I~//~9B30R~
        if (Dump.Y) Dump.println("UAChii.selectInfo pairCtr="+pairctr+",selectCtr="+selectCtr+",selectStart="+selectStart+",selectEnd="+selectEnd);//~9B30I~
        if (pairctr<PAIRCTR)                                       //~v@@@R~//~9B30R~
//      if (pairctr<PAIRCTR || swSelectionErr)                     //~v@@@I~//~9B30R~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("UAChii.selectInfo make pair err pairctr="+pairctr);//~v@@@I~
			return -1;		//could not make pair                  //~v@@@I~
        }                                                          //~v@@@I~
        if (pairctr>PAIRCTR)	                                   //~v@@@I~
        {                                                          //~v@@@I~
        	if ((selectEnd-selectStart)>PAIRCTR-1)                 //~9C01I~
            {                                                      //~9C01I~
	        	if (Dump.Y) Dump.println("UAChii.selectInfo selected on both edge redundant need selection pairctr="+pairctr);//~9C01I~
                return 1;                                          //~9C01I~
            }                                                      //~9C01I~
            else                                                   //~9C01I~
        	if ((selectEnd-selectStart)==PAIRCTR-1)                //~v@@@R~
            {                                                      //~v@@@I~
            	pairStart=selectStart;                             //~v@@@R~
            	pairEnd=selectEnd+1;                               //~v@@@R~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@M~
                if (selectStart==pairStart)                        //~v@@@I~
//              	pairEnd=pairStart+PAIRCTR-1;                   //~v@@@I~//~9C01R~
                	pairEnd=pairStart+PAIRCTR;                     //~9C01I~
                else                                               //~v@@@I~
//              if (selectEnd==pairEnd)                            //~v@@@I~//~9C01R~
                if (selectEnd+1==pairEnd)                          //~9C01I~
//              	pairStart=pairEnd-(PAIRCTR-1);                 //~v@@@I~//~9C01R~
                	pairStart=pairEnd-PAIRCTR;                     //~9C01I~
                else                                               //~v@@@I~
                {                                                  //~v@@@I~
	        		if (Dump.Y) Dump.println("UAChii.selectInfo redundant need selection pairctr="+pairctr);//~v@@@I~
                	return 1;                                      //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@M~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("UAChii.selectInfo pairStart="+pairStart+",pairEnd="+pairEnd);//~v@@@I~
        //*pairctr=PAIRCTR                                         //~v@@@I~
        for (int ii=pairStart;ii<pairEnd;ii++)                     //~v@@@R~
        {                                                          //~v@@@I~
        	dupctr=dupCtr[ii];                                     //~v@@@I~
			td=tds[ii][0];                                         //~v@@@I~
            tdsOut[ii-pairStart]=td;                               //~v@@@I~
//          if (dupctr>1)                                          //~v@@@I~//~9B30R~
//          if (dupctr>1 && !td.isRed5() && !swSelect[ii])  //not selected red5     //~9B30I~//~9C01R~
            if (dupctr>1 && !swSelect[ii])  //not selected         //~9C01I~
            {                                                      //~v@@@I~
                int ctrR5=0,ctrNR5=0;                              //~9C04I~
                for (int jj=0;jj<dupctr;jj++)                      //~v@@@R~//~9B30R~//~9C01R~
                {                                                  //~v@@@R~
                    if (tds[ii][jj].isRed5())                      //~v@@@R~
                    {                                              //~v@@@R~
//                      if (Dump.Y) Dump.println("UAChii.selectInfo red5 need selection td="+tds[ii][jj].toString());//~v@@@R~//~9C04R~
//                      return 1;       //redundant                //~v@@@R~//~9C04R~
						ctrR5++;                                   //~9C04I~
                    }                                              //~v@@@R~
                    else                                           //~9C04I~
                    	ctrNR5++;                                  //~9C04I~
                }                                                  //~v@@@R~
                if (ctrR5!=0 && ctrNR5!=0)                         //~9C04I~
                {                                                  //~9C04I~
					if (Dump.Y) Dump.println("UAChii.selectInfo red5 need selection");//~9C04I~
                    return 1;       //redundant                    //~9C04I~
                }                                                  //~9C04I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        tdsPair=tdsOut;                                            //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.selectInfo rc=0 pairctr="+pairctr+",selectctr="+selectCtr+",tdsPair:"+TileData.toString(tdsPair));//~v@@@R~
        return 0;                                                  //~v@@@R~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public boolean takeChii(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@@R~
    {                                                              //~v@@@I~
    	boolean swDraw;                                            //~v@@@R~
        TileData[] tds;                                            //~v@@@R~
    //***********************                                      //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.takeChii swServer="+PswServer+",swReceived="+PswReceived+",player="+Pplayer);//~v@@@R~
//      AG.aUADelayed.actionDone(GCM_CHII,PswServer,PswReceived,Pplayer);//~v@@@R~
        if (!PswReceived)                                          //~v@@@I~
        {                                                          //~v@@@I~
//            TileData td=PLS.getLastDiscarded();                  //~v@@@R~
//            if (Dump.Y) Dump.println("UAChii.takeChii lastDiscardedTile="+TileData.toString(td));//~v@@@R~
//            if (td!=null && td.isLocked())                       //~v@@@R~
//            {                                                    //~v@@@R~
//                if (Dump.Y) Dump.println("UATake.takeChii ignore Chii by Locked");//~v@@@R~
//                ACAction.showErrmsg(0,R.string.Info_WaitDiscardTimeout);//~v@@@R~
//                return false;                                    //~v@@@R~
//            }                                                    //~v@@@R~
            tds=tdsPair;                                           //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            tds=getReceivedPair(PintParm,PARMPOS_TD,PAIRCTR);      //~v@@@I~
//          tds[PAIRPOS_RIVER_TAKEN]=PLS.getLastDiscarded();       //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.takeChii tds="+TileData.toString(tds));//~v@@@R~
//      UAPon.setDiscardedPlayer(tds);                            //~v@@@R~//~v@@6R~
        if (PswServer)                                             //~v@@@I~
        {                                                          //~v@@@I~
//            if (isLocked(Pplayer))                               //~v@@@R~
//                return false;                                    //~v@@@R~
	        PLS.takeChii(Pplayer,tds);    //insert into Hands      //~v@@@R~
//          UserAction.showInfoAllEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Chii));//~v@@@R~//~9C02R~
        	UA.msgDataToClient=makeMsgDataToClient(Pplayer,tds,PAIRCTR);//~v@@@R~
//          AG.aUADelayed.resetWait(Pplayer);   //swith to next player after delay a moment//~v@@@R~
            UADL.resetWait(Pplayer);   //swith to next player after delay a moment//~v@@@I~
	        UADL.resetWait2Touch(PswServer,GCM_CHII,Pplayer);	//switch to next player after delay a moment//~v@@@I~
            stock.drawPendingOpenDora(Pplayer,GCM_CHII);           //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			boolean swShadow=Pplayer!=PLAYER_YOU;                  //~v@@@I~
	        PLS.takeChiiOtherOnClient(swShadow,Pplayer,tds);    //insert into Hands//~v@@@R~
            if (PswReceived)                                       //~v@@@I~
            {                                                      //~v@@@I~
//	        	AG.aUADelayed.resetWait(Pplayer);	//swith to next player after delay a moment//~v@@@R~
 	        	UADL.resetWait(Pplayer);	//swith to next player after delay a moment//~v@@@I~
		        UADL.resetWait2Touch(PswServer,GCM_CHII,Pplayer);	//switch to next player after delay a moment//~v@@@I~
	            stock.drawPendingOpenDora(Pplayer,GCM_CHII);       //~v@@@R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
//		if (Pplayer==PLAYER_YOU)                                   //~v@@@R~
	    	hands.takeChii(Pplayer);  //draw Hands                 //~v@@@R~
        int playerDiscarded=PLS.getLastDiscardedPlayer();          //~v@@@I~
        river.takeChii(playerDiscarded);                           //~v@@@R~
//      UA.UAT.setTimeout(Pplayer,GCM_CHII);	//autodiscard timeout//~v@@@R~
        UA.UAT.setAutoDiscardTimeout(PswServer,Pplayer,GCM_CHII);	//autodiscard timeout//~v@@@I~
    	UA.UAP.drawLightToDiscard(Pplayer);                 //~9C06I~
//      GMsg.showHL(0,GCM_CHII);                                   //~9C02I~//~0401R~
        GMsg.showHL(0,GCM_CHII,Pplayer);                           //~0401I~
    	Sound.play(SOUNDID_CHII,false/*not change to beep when beeponly option is on*/);//~0408I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public boolean isLocked(int Pplayer)                          //~v@@@I~//~1128R~
    {                                                              //~v@@@I~
        boolean rc=false;                                          //~v@@@I~
	    TileData td=PLS.getLastDiscarded();                        //~v@@@I~
		if (td!=null && td.isLocked())                             //~v@@@I~
		{                                                          //~v@@@I~
	    	if (Dump.Y) Dump.println("UAChii.isLocked ignore Chii by Locked td="+td.toString());//~v@@@R~
	        UserAction.sendErr(0,Pplayer,R.string.Info_WaitDiscardTimeout);//~v@@@I~
            rc=true;                                               //~v@@@I~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("UAChii.isLocked rc="+rc);        //~v@@@I~//~1128R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public   String makeMsgDataToServer(int Pplayer,TileData[] Ptds,int Pctr)//~v@@@R~//+va60R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.makeMsgDataToServer ctr="+Pctr);//~v@@@R~
////      int eswn=AG.aAccounts.playerToEswn(Pplayer);             //~v@@@R~
//        StringBuffer sb=new StringBuffer();                      //~v@@@R~
////      sb.append(eswn+MSG_SEPAPP2);                             //~v@@@R~
//        for (int ii=0;ii<Pctr;ii++)                              //~v@@@R~
//        {                                                        //~v@@@R~
//            sb.append(ACA.strTD(Ptds[ii])+MSG_SEPAPP2);          //~v@@@R~
//        }                                                        //~v@@@R~
//        if (Dump.Y) Dump.println("UAChii.makeMsgDataToServer sb="+sb);//~v@@@R~
//        return sb.toString();                                    //~v@@@R~
	    return UAPon.makeMsgDataToServer(Pplayer,Ptds,Pctr);       //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    private  String makeMsgDataToClient(int Pplayer,TileData[] Ptds,int Pctr)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.makeMsgDataToClient ctr="+Pctr);//~v@@@R~
//        int eswn=ACC.playerToEswn(Pplayer);                      //~v@@@R~
//        String data=eswn+MSG_SEPAPP2+makeMsgDataToServer(Pplayer,Ptds,Pctr);//~v@@@R~
//        if (Dump.Y) Dump.println("UAChii.makeMsgDataToClientr data="+data);//~v@@@R~
//        return data;                                             //~v@@@R~
		return UAPon.makeMsgDataToClient(Pplayer,Ptds,Pctr);       //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    private TileData[] getReceivedPair(int[] PintParm,int Ppos,int PpairCtr)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.getReceivedPair ctr="+PpairCtr);//~v@@@R~
//        TileData[] tds=new TileData[PpairCtr];                   //~v@@@R~
//        for (int ii=0,pos=Ppos;ii<PpairCtr;ii++,pos+=PARMPOS_CTRFORTD)//~v@@@R~
//        {                                                        //~v@@@R~
//            tds[ii]=new TileData(true/*swEswnToPlayer*/,PintParm,pos);//~v@@@R~
//        }                                                        //~v@@@R~
//        return tds;                                              //~v@@@R~
    	return UAPon.getReceivedPair(PintParm,Ppos,PpairCtr);      //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@R~
//    private static void setDiscardedPlayer(TileData Ptd)         //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UAChii.setDiscardedPlayer in ="+Ptd.toString());//~v@@@R~
//        int player=PLS.getLastDiscardedPlayer();                 //~v@@@R~
//        Ptd.player=player;                                       //~v@@@R~
//        Ptd.eswn=Accounts.playerToEswn(player);                  //~v@@@R~
//        if (Dump.Y) Dump.println("UAChii.setDiscardedPlayer out="+Ptd.toString());//~v@@@R~
//    }                                                            //~v@@@R~
    //*************************************************************************//~v@@@I~
    private void getTouchSelection()                               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.getTouchSelection");      //~v@@@R~
        swSelectedMulti=AG.aHandsTouch.getSelectedMulti();         //~v@@@R~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@R~
//    private  void declare(boolean PswServer,TileData Ptd)        //~v@@@R~
//    {                                                            //~v@@@R~
//        Ptd.setChiiDeclared();                                   //~v@@@R~
//        int opt=0;                                               //~v@@@R~
//        if (!PswServer)                                          //~v@@@R~
//            opt|=IMO_CLIENT;                                     //~v@@@R~
//        UserAction.showInfoAllEswn(opt,Utils.getStr(R.string.UserAction_Chii));//~v@@@R~
//        UADL.postDelayedActionMsg(delayChii,GCM_CHII,null);      //~v@@@R~
//    }                                                            //~v@@@R~
	//*************************************************************************//~v@@@M~
	//*Test TODO                                                   //~v@@@M~
	//*************************************************************************//~v@@@M~
    private int selectInfoTest(TileData[] Ptds, TileData Ptd)      //~v@@@M~
    {                                                              //~v@@@M~
    	utChii();                                                  //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.selectInfoTest river td="+Ptd.toString());//~v@@@M~
        TileData[] tds=new TileData[PAIRCTR];                      //~v@@@M~
        tds[0]=Ptds[0];                                            //~v@@@M~
        tds[1]=Ptds[1];                                            //~v@@@M~
        tds[2]=Ptd;                                                //~v@@@M~
        int rc=0;                                                  //~v@@@M~
        tdsPair=tds;                                               //~v@@@M~
        if ((TestOption.option2 & TestOption.TO2_WAITSELECT_CHII)!=0) //TODO//~9C05I~//~9C12R~
        {                                                          //~9C05I~
//      	getTouchSelection();                                   //~9C05R~
//          if (true)   //TEST                                     //~9C07I~//~9C12R~
//              rc=0;                                              //~9C07I~//~9C12R~
//          else                                                   //~9C07I~//~9C12R~
			if (AG.aHandsTouch.getPosOld()==1)                     //~9C07I~
            	rc=0;                                              //~9C07I~
            else                                                   //~9C07I~
            if (swSelectedMulti!=null && !swSelectedMulti[0])      //~9C05R~
	        	rc=1;	//wait multi selecion                      //~9C05R~
        }                                                          //~9C05I~
        if (Dump.Y) Dump.println("UAChii.selectInfoTest rc="+rc+",testoption2="+Integer.toHexString(TestOption.option2));//~9C05I~
        return rc;                                                 //~v@@@M~
    }                                                              //~v@@@M~
	//*************************************************************************//~v@@@M~
	//*For UnitTest                                                //~v@@@M~
	//*************************************************************************//~v@@@M~
    public void utChii()                                           //~v@@@M~
    {                                                              //~v@@@M~
    	boolean swDraw;                                            //~v@@@M~
        TileData[] tds=new TileData[8];                            //~v@@@M~
        TileData td;                                               //~v@@@M~
        int rc;                                                    //~v@@@M~
    //***********************                                      //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii swSelectedMulti="+Arrays.toString(swSelectedMulti));//~9C05I~
    	boolean[] swSelectedMultiSV=swSelectedMulti;               //~9C05I~
    	swSelectedMulti=new boolean[HANDCTR];                      //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
    //*1                                                           //~v@@@M~
        td=new TileData(1,0,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(2,0,false,2);                          //~v@@@M~
        tds[3]=new TileData(2,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(3,0,false,4);                          //~v@@@M~
        tds[5]=new TileData(3,0,false,4);                          //~v@@@M~
        tds[6]=new TileData(3,0,false,4);                          //~v@@@M~
        tds[7]=new TileData(3,0,false,4);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-1 rc="+rc);         //~v@@@M~//~9C05R~
    //*2                                                           //~v@@@M~
        td=new TileData(1,0,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,4,false,2);                          //~v@@@M~
        tds[3]=new TileData(2,0,false,3);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-2 rc="+rc);         //~v@@@M~//~9C05R~
    //*3                                                           //~v@@@M~
        td=new TileData(1,0,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,1,false,2);                          //~v@@@M~
        tds[3]=new TileData(2,0,false,3);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-3 rc="+rc);         //~v@@@M~//~9C05R~
    //*4                                                           //~v@@@M~
        td=new TileData(1,0,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(2,0,false,3);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-4 rc="+rc);         //~v@@@M~//~9C05R~
    //*5                                                           //~v@@@M~
        td=new TileData(1,8,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,6,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,7,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,8,false,2);                          //~v@@@M~
        tds[3]=new TileData(2,0,false,3);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-5 rc="+rc);         //~v@@@M~//~9C05R~
    //*6                                                           //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(2,0,false,3);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-6 rc="+rc);         //~v@@@M~//~9C05R~
    //*7                                                           //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,3,false,3);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-7 rc="+rc);         //~v@@@M~//~9C05R~
    //*8                                                           //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,3,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,4,false,4);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-8 rc="+rc);         //~v@@@M~//~9C05R~
    //*9                                                           //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,1,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,2,false,4);                          //~v@@@M~
        tds[5]=new TileData(2,3,false,5);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-9 rc="+rc);         //~v@@@M~//~9C05R~
    //*10                                                          //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,1,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,2,false,4);                          //~v@@@M~
        tds[5]=new TileData(1,3,false,5);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-10 rc="+rc);        //~v@@@M~//~9C05R~
    //*11                                                          //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,true ,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,true ,2);                          //~v@@@M~
        tds[3]=new TileData(2,3,false,3);                          //~v@@@M~
        tds[4]=new TileData(2,3,false,4);                          //~v@@@M~
        tds[5]=new TileData(2,4,false,5);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-11 rc="+rc);        //~v@@@M~//~9C05R~
    //*12                                                          //~v@@@M~
        td=new TileData(1,1,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,true ,2);                          //~v@@@M~
        tds[3]=new TileData(2,3,false,3);                          //~v@@@M~
        tds[4]=new TileData(2,3,false,4);                          //~v@@@M~
        tds[5]=new TileData(2,4,false,5);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-12 rc="+rc);        //~v@@@M~//~9C05R~
    //*13                                                          //~v@@@M~
        td=new TileData(1,1,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,true ,2);                          //~v@@@M~
        tds[3]=new TileData(2,3,false,3);                          //~v@@@M~
        tds[4]=new TileData(2,3,false,4);                          //~v@@@M~
        tds[5]=new TileData(2,4,false,5);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[0].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[0]=true;                                   //~v@@@I~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.utChii-13 rc="+rc);        //~v@@@M~//~9C05R~
    //*13-2                                                        //~v@@@M~
        td=new TileData(1,1,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,true ,2);                          //~v@@@M~
        tds[3]=new TileData(2,3,false,3);                          //~v@@@M~
        tds[4]=new TileData(2,3,false,4);                          //~v@@@M~
        tds[5]=new TileData(2,4,false,5);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[1].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@I~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.utChii-13-2 rc="+rc);      //~v@@@M~//~9C05R~
    //*14                                                          //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,0,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,2,true ,4);                          //~v@@@M~
        tds[5]=new TileData(2,3,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[1].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-14 rc="+rc);        //~v@@@M~//~9C05R~
    //*15                                                          //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,0,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,2,true ,4);                          //~v@@@M~
        tds[5]=new TileData(1,3,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[1].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-15 rc="+rc);        //~v@@@M~//~9C05R~
    //*16                                                          //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,0,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,2,true ,4);                          //~v@@@M~
        tds[5]=new TileData(1,3,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[1].setSelected(true);                                  //~v@@@R~
//      tds[5].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
        swSelectedMulti[5]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-16 rc="+rc);        //~v@@@M~//~9C05R~
    //*17                                                          //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,0,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,2,true ,4);                          //~v@@@M~
        tds[5]=new TileData(2,3,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[0].setSelected(true);                                  //~v@@@R~
//      tds[1].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[0]=true;                                   //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-17 rc="+rc);        //~v@@@M~//~9C05R~
    //*18                                                          //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,0,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,2,true ,4);                          //~v@@@M~
        tds[5]=new TileData(2,3,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[1].setSelected(true);                                  //~v@@@R~
//      tds[2].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
        swSelectedMulti[2]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-18 rc="+rc);        //~v@@@M~//~9C05R~
    //*19                                                          //~v@@@M~
        td=new TileData(1,2,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,0,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,2,true ,4);                          //~v@@@M~
        tds[5]=new TileData(2,3,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[1].setSelected(true);                                  //~v@@@R~
//      tds[2].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
        swSelectedMulti[2]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-19 rc="+rc);        //~v@@@M~//~9C05R~
    //*20                                                          //~v@@@M~
        td=new TileData(1,4,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,2,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,3,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,6,false,3);                          //~v@@@M~
        tds[4]=new TileData(2,1,true ,4);                          //~v@@@M~
        tds[5]=new TileData(2,3,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-20 rc="+rc);        //~v@@@M~//~9C05R~
                                                                   //~v@@@M~
    //*21                                                          //~v@@@M~
        td=new TileData(1,3,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,4,false,3);                          //~v@@@M~
        tds[4]=new TileData(2,1,true ,4);                          //~v@@@M~
        tds[5]=new TileData(2,3,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[1].setSelected(true);                                  //~v@@@R~
//      tds[2].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
        swSelectedMulti[2]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-21 rc="+rc);        //~v@@@M~//~9C05R~
                                                                   //~v@@@M~
    //*22                                                          //~v@@@M~
        td=new TileData(1,3,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,3,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,4,true ,4);                          //~v@@@M~
        tds[5]=new TileData(1,5,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[0].setSelected(true);                                  //~v@@@R~
//      tds[1].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[0]=true;                                   //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-22 rc="+rc);        //~v@@@M~//~9C05R~
    //*23                                                          //~v@@@M~
        td=new TileData(1,1,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,0,true ,0);                          //~v@@@M~
        tds[1]=new TileData(1,0,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,0,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,1,false,3);                          //~v@@@M~
        tds[4]=new TileData(1,3,true ,4);                          //~v@@@M~
        tds[5]=new TileData(2,1,false,5);                          //~v@@@M~
        tds[6]=new TileData(2,3,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[1].setSelected(true);                                  //~v@@@R~
//      tds[2].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
        swSelectedMulti[2]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-23 rc="+rc);        //~v@@@M~//~9C05R~
    //*24                                                          //~v@@@M~
        td=new TileData(1,3,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,1,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,4,true ,3);                          //~v@@@M~
        tds[4]=new TileData(1,4,false,4);                          //~v@@@M~
        tds[5]=new TileData(1,4,false,5);                          //~v@@@M~
        tds[6]=new TileData(1,8,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
    	rc=selectInfo(tds,td);                                     //~v@@@M~
        if (Dump.Y) Dump.println("UAChii.utChii-24 rc="+rc);        //~v@@@M~//~9C05R~
    //*25                                                          //~v@@@M~
        td=new TileData(1,3,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,1,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,4,true ,3);                          //~v@@@M~
        tds[4]=new TileData(1,4,false,4);                          //~v@@@M~
        tds[5]=new TileData(1,4,false,5);                          //~v@@@M~
        tds[6]=new TileData(1,8,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[3].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[3]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-25 rc="+rc);        //~v@@@M~//~9C05R~
                                                                   //~v@@@M~
    //*26                                                          //~v@@@M~
        td=new TileData(1,3,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,1,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,1,false,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,4,true ,3);                          //~v@@@M~
        tds[4]=new TileData(1,4,false,4);                          //~v@@@M~
        tds[5]=new TileData(1,4,false,5);                          //~v@@@M~
        tds[6]=new TileData(1,8,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[0].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[0]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-26 rc="+rc);        //~v@@@M~//~9C05R~
    //*27                                                          //~v@@@M~
        td=new TileData(1,3,false,8);                              //~v@@@M~
        tds[0]=new TileData(1,1,false,0);                          //~v@@@M~
        tds[1]=new TileData(1,2,true ,1);                          //~v@@@M~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@M~
        tds[3]=new TileData(1,4,true ,3);                          //~v@@@M~
        tds[4]=new TileData(1,4,false,4);                          //~v@@@M~
        tds[5]=new TileData(1,4,false,5);                          //~v@@@M~
        tds[6]=new TileData(1,8,false,6);                          //~v@@@M~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@M~
                                                                   //~v@@@M~
//      tds[0].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[0]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-27 rc="+rc);        //~v@@@M~//~9C05R~
    //*28                                                          //~v@@@I~
        td=new TileData(1,3,false,8);                              //~v@@@I~
        tds[0]=new TileData(1,1,false,0);                          //~v@@@I~
        tds[1]=new TileData(1,2,true ,1);                          //~v@@@I~
        tds[2]=new TileData(1,4,false,2);                          //~v@@@I~
        tds[3]=new TileData(1,5,true ,3);                          //~v@@@I~
        tds[4]=new TileData(1,6,false,4);                          //~v@@@I~
        tds[5]=new TileData(1,7,false,5);                          //~v@@@I~
        tds[6]=new TileData(1,8,false,6);                          //~v@@@I~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@I~
                                                                   //~v@@@I~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        if (Dump.Y) Dump.println("UAChii.utChii-28 rc="+rc);        //~v@@@I~//~9C05R~
    //*29                                                          //~v@@@I~
        td=new TileData(1,3,false,8);                              //~v@@@I~
        tds[0]=new TileData(1,1,false,0);                          //~v@@@I~
        tds[1]=new TileData(1,2,true ,1);                          //~v@@@I~
        tds[2]=new TileData(1,4,false,2);                          //~v@@@I~
        tds[3]=new TileData(1,5,true ,3);                          //~v@@@I~
        tds[4]=new TileData(1,6,false,4);                          //~v@@@I~
        tds[5]=new TileData(1,7,false,5);                          //~v@@@I~
        tds[6]=new TileData(1,8,false,6);                          //~v@@@I~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@I~
                                                                   //~v@@@I~
//      tds[0].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[0]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-29 rc="+rc);        //~v@@@I~//~9C05R~
    //*29-2                                                        //~v@@@I~
        td=new TileData(1,3,false,8);                              //~v@@@I~
        tds[0]=new TileData(1,1,false,0);                          //~v@@@I~
        tds[1]=new TileData(1,2,true ,1);                          //~v@@@I~
        tds[2]=new TileData(1,2,false,2);                          //~v@@@I~
        tds[3]=new TileData(1,4,false,3);                          //~v@@@I~
        tds[4]=new TileData(1,5,true ,4);                          //~v@@@I~
        tds[5]=new TileData(1,6,false,5);                          //~v@@@I~
        tds[6]=new TileData(1,7,false,6);                          //~v@@@I~
        tds[7]=new TileData(1,8,false,7);                          //~v@@@I~
                                                                   //~v@@@I~
//      tds[0].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[0]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-29-2 rc="+rc);      //~v@@@I~//~9C05R~
    //*30                                                          //~v@@@I~
        td=new TileData(1,3,false,8);                              //~v@@@I~
        tds[0]=new TileData(1,1,false,0);                          //~v@@@I~
        tds[1]=new TileData(1,2,true ,1);                          //~v@@@I~
        tds[2]=new TileData(1,4,false,2);                          //~v@@@I~
        tds[3]=new TileData(1,6,true ,3);                          //~v@@@I~
        tds[4]=new TileData(1,7,false,4);                          //~v@@@I~
        tds[5]=new TileData(1,8,false,5);                          //~v@@@I~
        tds[6]=new TileData(1,9,false,6);                          //~v@@@I~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@I~
                                                                   //~v@@@I~
//      tds[2].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[2]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-30 rc="+rc);        //~v@@@I~//~9C05R~
    //*31                                                          //~v@@@I~
        td=new TileData(1,3,false,8);                              //~v@@@I~
        tds[0]=new TileData(1,1,false,0);                          //~v@@@I~
        tds[1]=new TileData(1,2,true ,1);                          //~v@@@I~
        tds[2]=new TileData(1,4,false,2);                          //~v@@@I~
        tds[3]=new TileData(1,6,true ,3);                          //~v@@@I~
        tds[4]=new TileData(1,7,false,4);                          //~v@@@I~
        tds[5]=new TileData(1,8,false,5);                          //~v@@@I~
        tds[6]=new TileData(1,9,false,6);                          //~v@@@I~
        tds[7]=new TileData(2,4,false,7);                          //~v@@@I~
                                                                   //~v@@@I~
//      tds[1].setSelected(true);                                  //~v@@@R~
//      tds[2].setSelected(true);                                  //~v@@@R~
        swSelectedMulti[1]=true;                                   //~v@@@R~
        swSelectedMulti[2]=true;                                   //~v@@@R~
    	rc=selectInfo(tds,td);                                     //~v@@@I~
        Arrays.fill(swSelectedMulti,false);                        //~v@@@R~
        if (Dump.Y) Dump.println("UAChii.utChii-31 rc="+rc);        //~v@@@I~//~9C05R~
    //*32                                                          //~9C04I~
        td=new TileData(1,3,false,8);                              //~9C04I~
        tds[0]=new TileData(1,2,false,1);                          //~9C04R~
        tds[1]=new TileData(1,2,false,1);                          //~9C04R~
        tds[2]=new TileData(1,2,false,1);                          //~9C04R~
        tds[3]=new TileData(1,3,false,1);                          //~9C04R~
        tds[4]=new TileData(1,4,true,2);                           //~9C04R~
        tds[5]=new TileData(1,4,true,2);                           //~9C04R~
        tds[6]=new TileData(2,6,true ,3);                          //~9C04R~
        tds[7]=new TileData(2,6,true ,3);                          //~9C04I~
    	rc=selectInfo(tds,td);                                     //~9C04I~
        Arrays.fill(swSelectedMulti,false);                        //~9C04I~
        if (Dump.Y) Dump.println("UAChii.utChii-32 rc="+rc);        //~9C04I~//~9C05R~
    //*33                                                          //~9C04I~
        td=new TileData(1,3,false,8);                              //~9C04I~
        tds[0]=new TileData(1,2,false,1);                          //~9C04I~
        tds[1]=new TileData(1,2,false,1);                          //~9C04I~
        tds[2]=new TileData(1,2,false,1);                          //~9C04I~
        tds[3]=new TileData(1,3,false,1);                          //~9C04I~
        tds[4]=new TileData(1,4,true,2);                           //~9C04I~
        tds[5]=new TileData(1,4,false,2);                          //~9C04I~
        tds[6]=new TileData(2,6,true ,3);                          //~9C04I~
        tds[7]=new TileData(2,6,true ,3);                          //~9C04I~
    	rc=selectInfo(tds,td);                                     //~9C04I~
        Arrays.fill(swSelectedMulti,false);                        //~9C04I~
        if (Dump.Y) Dump.println("UAChii.utChii-33 rc="+rc);        //~9C04R~//~9C05R~
                                                                   //~9C05I~
    	swSelectedMulti=swSelectedMultiSV;                         //~9C05I~
        if (Dump.Y) Dump.println("UAChii.utChii exit swSelectedMulti="+Arrays.toString(swSelectedMulti));//~9C05I~
    }                                                              //~v@@@M~
}//class                                                           //~v@@@R~
