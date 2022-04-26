//*CID://+vamhR~: update#= 759;                                    //~vamhR~
//**********************************************************************//~v101I~
//2022/04/07 vamh Animation. for Pon/Chii/Kan                      //~vamhI~
//2022/03/19 vakL issue DrawKan msg on client and if 4kandrawn     //~vakLI~
//2022/03/18 vakJ match mode;sendmag:14 is rejected at client by stopAuto sent by chk4KanDrawn//~vakJI~
//2022/03/17 vakH chk4KanDrawn, it should be before autotakeKanTimeout(time of ron+pon timeout expired)//~vakHI~
//2022/03/15 vakD No need to wait ronTime to take Rinshan for Ankan if tile is not candidate of 13 orphan//~vakDI~
//2022/03/15 vakC (Bug)Human on PAN mode; Autotake kan doid not work.//~vakCI~
//2022/03/14 vaky skip send autotake_kan when 4kanDrawn            //~vakyI~
//2022/03/09 vakw In PAN mode,rinshan was not taken automatically when canceled 4KanDrawn//~vakwI~
//2022/03/09 vakt issue msg "take Rinshan"                         //~vaktI~
//2022/03/05 vakq (Bug)PAN mode; DrawnHW by 4kan fail by GCM_TAKE by Take button overtake postDelayedAutoTakeKan//~vakqI~
//2022/03/01 vakm auto popup darwnDlgHW for 4 wind,4 kan, 4 reach  //~vakmI~
//                4kan exceutes at called 4'th kan, can not take rinshan.//~vakmI~
//2022/01/23 vajb (bug)err FuretenAfterReach was not set for chankan//~vajbI~
//2021/07/23 vaaW PlayAlone mode;hung 13orphan if not ankan ronable//~vaaWI~
//2021/07/23 vaaV PlayAlone mode;win btn after chankan cause err NotYourTurn//~vaaVI~
//2021/07/19 vaaU chankan was not notified                         //~vaaUI~
//2021/06/28 vaa5 (Bug)Dump at canceled Kan at 1st take because lastDiscarded is null//~vaa5I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.DrawnReqDlgHW;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.GC;
import com.btmtest.game.Players;
import com.btmtest.game.RA.RAUtils;
import com.btmtest.game.Robot;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.game.UADelayed2;
import com.btmtest.game.UserAction;                                //~v@@@I~
import com.btmtest.game.gv.GMsg;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.game.gv.Hands;
import com.btmtest.game.gv.River;
import com.btmtest.game.gv.Stock;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.Sound;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Players.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.game.UA.UAReach.*;
import static com.btmtest.BT.enums.MsgIDConst.*;                   //~v@@@I~
import static com.btmtest.game.GCMsgID.*;
                                                                   //~v@@@I~
public class UAKan                                                 //~v@@@R~//~v@@6R~
{                                                                  //~0914I~
    private UserAction UA;                                         //~v@@@I~
    private ACAction ACA;                                          //~v@@@I~
    private Accounts ACC;                                          //~v@@@I~
    private Players PLS;                                           //~v@@@R~
//  private UADelayed UADL;                                        //~9B16I~//~9B17R~
    private UADelayed2 UADL;                                       //~9B17I~
    private boolean isServer;                                      //~v@@@I~
    private Tiles tiles;                                           //~v@@@I~
    private Hands hands;                                           //~v@@@I~
    private Stock stock;                                           //~v@@@I~
    private River river;                                           //~v@@@R~
    private TileData[] tdsPair;                                    //~v@@@I~
    private TileData[] tdsAddKan;                                  //~0407R~
    private TileData[] tds4KanDrawn;                               //~vakJI~
    private boolean swSelected;     //single selection for kan                          //~v@@6R~//~0407R~
    private int kanType;                                           //~v@@6I~
    private TileData selectedTile;                                 //~v@@6I~
    private TileData resetTile;	//selection reset by dup candidate,next time clear swSelectedRequested if changed//~v@@6I~
    private int selectedTilePos;                                   //~v@@6I~
    private boolean swSelectRequested;                             //~v@@6I~
//    private int timeout;                                           //~9623I~
//  private boolean swAnkanRon;                                            //~0405I~//~vaaWR~
    private boolean swAnkanRon;                                    //~vakDI~
    private boolean swManualRobot;    //take by button in training mode//~va66R~
    private boolean swDrawHW4KanAtCall=true;    //drawHW for 4Kan at Kan Called//~vakmI~
    private boolean swSkipOpenDlg;    //drawHW for 4Kan at Kan Called//~vakyI~
    private boolean sw4KanDrawn;                                   //~vakJI~
//*************************                                        //~v@@@I~
	public UAKan(UserAction PuserAction)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~v@@6R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UAKan.Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~v@@6R~
        UA=PuserAction;                                            //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	public void init()                                             //~v@@@I~
    {                                                              //~v@@@I~
        PLS=AG.aPlayers;                                           //~v@@@R~
        ACC=AG.aAccounts;                                          //~v@@@R~
        ACA=AG.aACAction;                                          //~v@@@I~
        UADL=AG.aUADelayed;                                        //~9B16I~
        tiles=AG.aTiles;                                           //~v@@@R~
        hands=AG.aHands;                                           //~v@@@I~
        river=AG.aRiver;                                           //~v@@@R~
        stock=AG.aStock;                                           //~v@@@R~
//        accounts=AG.aAccounts;                                   //~v@@@I~
//        acaction=AG.aACAction;                                   //~v@@@I~
        isServer=Accounts.isServer();                              //~v@@@R~
//      delayTake=OperationSetting.getDelayKanTake();              //~v@@6I~//~9209R~
//        timeout=RuleSetting.getTimeoutTakeKan();                   //~9623I~
//      swAnkanRon= RuleSettingYaku.isAvailableAnkanRon();          //~0405I~//~vaaWR~
        swAnkanRon= RuleSettingYaku.isAvailableAnkanRon();         //~vakDI~
        if (AG.swTrainingMode)                                     //~va66I~
	    	swManualRobot= RuleSettingOperation.isAllowRobotAllButton();//~va66R~
        if (Dump.Y) Dump.println("UAKan init isServer="+isServer); //~v@@@R~//~v@@6R~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@6R~
//    public boolean takeKan(int Pplayer)                          //~v@@6R~
//    {                                                            //~v@@6R~
//        if (Dump.Y) Dump.println("UserAction.takeKan player="+Pplayer);//~v@@6R~
////        if (!players.isYourTurn(actionID,Pplayer))             //~v@@6R~
////            return;                                            //~v@@6R~
////        TileData td = tiles.getNextKan(Pplayer); //TDF_TAKEN   //~v@@6R~
////        if (td == null)                                        //~v@@6R~
////            return;                                            //~v@@6R~
////        int rc=players.takeKan(Pplayer,td);                    //~v@@6R~
//        int rc=PLS.takeKan(Pplayer);                             //~v@@6R~
//        if (rc<0)                                                //~v@@6R~
//            return false;                                        //~v@@6R~
////      Status.setTaken(td,Pplayer);                             //~v@@6R~
////      stock.takeOneKan();                                      //~v@@6R~
//        hands.takeKan(Pplayer,rc);                               //~v@@6R~
//        river.takeKan(Pplayer,rc);                               //~v@@6R~
//        stock.takeKan(Pplayer,rc);                               //~v@@6R~
//        return true;                                             //~v@@6R~
//    }                                                            //~v@@6R~
    //*************************************************************************//~v@@6R~
    //* it is required to reselect when meld is constructed by just taken tile//~0407I~
    //* rc=false if taken tile is selected and not selectRequested //~0407I~
    //*************************************************************************//~0407I~
    private boolean chkSelected(TileData[] Ptds)                   //~v@@6R~
    {                                                              //~v@@6R~
        boolean rc=false;                                              //~v@@6R~
        if (Dump.Y) Dump.println("UAKan.chkSelected selectedTile="+TileData.toString(selectedTile)+",tds="+TileData.toString(Ptds));//~0407R~
        if (selectedTile!=null)                                    //~v@@6I~
        {                                                          //~v@@6I~
            rc=TileData.TDCompareTN(Ptds[0],selectedTile);         //~v@@6R~
            if (rc)                                                //~v@@6I~
				if (!swSelectRequested && TileData.TDCompare(selectedTile,PLS.getCurrentTaken(),true/*compareCtr*/)==0)//~v@@6I~
	            	rc=false;	//request select at first if current taken is selected//~v@@6I~
        }                                                          //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.chkSelected rc="+rc+",swSelectRequested="+swSelectRequested+",Ptd:"+TileData.toString(Ptds));//~v@@6R~
        return rc;                                                 //~v@@6R~
    }                                                              //~v@@6R~
    //*************************************************************************//~0407I~
    //*compare type and number                                     //~0407I~
    //*rc:match:true                                               //~0407I~
    //*************************************************************************//~0407I~
    private boolean chkSelected(TileData[] Ptds,TileData PselectedTile)//~0407I~
    {                                                              //~0407I~
        boolean rc=false;                                          //~0407I~
        if (Dump.Y) Dump.println("UAKan.chkSelected PselectedTile="+TileData.toString(PselectedTile)+",tds="+TileData.toString(Ptds));//~0407I~
        rc=TileData.TDCompareTN(Ptds[0],PselectedTile);            //~0407I~
        if (Dump.Y) Dump.println("UAKan.chkSelected rc="+rc);      //~0407I~
        return rc;                                                 //~0407I~
    }                                                              //~0407I~
    //*************************************************************************//~v@@6I~
    private void resetSelection()                               //~v@@6I~
    {                                                              //~v@@6I~
    	swSelectRequested=true;                                    //~v@@6I~
        resetTile=PLS.getCurrentTaken();                           //~v@@6I~
        int posOld=PLS.getTileSelectedPos(PLAYER_YOU);             //~v@@6I~
        PLS.setTileSelected(PLAYER_YOU,-1);                        //~v@@6I~
        AG.aHandsTouch.resetSelection(posOld);                      //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.resetSelectio swSelectedRequest="+swSelectRequested+",posOld="+posOld);//~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@@I~
	//*from userAction                                             //~0407I~
	//*************************************************************************//~0407I~
//  public boolean selectInfo(boolean PswServer,int Pplayer)       //~v@@@R~//~9B18R~
    public boolean selectInfo(boolean PswServer,int Pplayer,int[] PintParm)//~9B18I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAKan.selectInfo");         //~v@@@R~//~9B23R~
        int rc;                                                    //~v@@@I~
	    if (isLocked(Pplayer))                               //~9209I~
        	return false;                                          //~9209I~
//      if (!UADL.chkAction2Touch(PswServer,GCM_KAN,Pplayer))      //~9B17I~//~9B18R~
//      if (!UADL.chkSelectInfo2Touch(PswServer,GCM_KAN,Pplayer,PintParm))//~9B18I~//~9B30R~
//          return false;                                          //~9B17I~//~9B30R~
        if (AG.aTiles.addCtrKan(false/*swUpdate*/)<0)	//err when ctrKan>4              //~v@@6R~//~9B24R~
        	return false;                                          //~v@@6I~
//      TileData td=PLS.getLastDiscarded(); //not used             //~v@@@R~//~vaa5R~
//      TileData[] tds=Accounts.getHands();                        //~v@@@I~//~v@@5R~
        TileData[] tds=AG.aHands.getHands(PLAYER_YOU);            //~v@@5I~
        selectedTile=PLS.getTileSelected(PLAYER_YOU);	//GC.touchEventTile set it                  //~v@@@R~//~v@@6I~
        selectedTilePos=PLS.getTileSelectedPos(PLAYER_YOU);	//GC.touchEventTile set it//~v@@6I~
        if (resetTile!=null & selectedTile!=null)                  //~v@@6I~
        {                                                          //~v@@6I~
	        if (TileData.TDCompare(resetTile,selectedTile,true/*swcompareCtr*/)!=0)//~v@@6I~
            	swSelectRequested=false;                        //~v@@6I~
        }                                                          //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.selectInfo swSelectionRequested="+swSelectRequested+",testoption="+Integer.toHexString(TestOption.option));//~v@@6I~//~9B23R~//~0401R~
//      if ((TestOption.option & TestOption.TO_KAN_TEST)!=0) //TODO           //~v@@@I~//~v@@6R~//~0A09R~
        if ((TestOption.option & (TestOption.TO_KAN_TEST|TestOption.TO_KAN_CHANKAN))!=0) //TODO//~0A09I~
        {                                                          //~9B30I~
            rc=selectInfoTest(Pplayer);                             //~v@@@I~//~v@@6R~
        	if (!UADL.chkSelectInfo2Touch(PswServer,GCM_KAN,Pplayer,PintParm))//~9B30I~
            {                                                      //~9B30I~
            	resetSelection();                                  //~9B30I~
            	return false;                                      //~9B30I~
            }                                                      //~9B30I~
        }                                                          //~9B30I~
        else                                                       //~v@@@I~
        {                                                          //~9B30I~
        	rc=selectInfo(Pplayer);                                 //~v@@@R~//~v@@6R~
            if (rc!=-1)                                            //~9B30I~
        		if (!UADL.chkSelectInfo2Touch(PswServer,GCM_KAN,Pplayer,PintParm))//~9B30M~
            		return false;                                  //~9B30M~
			if (rc==0)                                             //~9B30I~
				setKanType();                                      //~9B30I~
        }                                                          //~9B30I~
        if (rc!=0)                                                 //~v@@@I~//~v@@6R~
        {                                                          //~v@@@I~
        	if (rc==1)	//dup                                      //~v@@6I~
            {                                                      //~v@@6I~
            	resetSelection();                                  //~v@@6I~
	            AG.aHandsTouch.enableMultiSelectionMode(false,true/*swKan*/);//~v@@6I~
                rc=2;	//kan select msg                           //~v@@6I~
            }                                                      //~v@@6I~
        	UA.selectionErr(rc); //-1:err,1:ambiguous                                  //~v@@@I~//~v@@6R~
        	return false;                                          //~v@@@I~
        }                                                          //~v@@@I~
//      td.setTakenRiver();	//requred at drawRiver, server msg miss it.//~v@@@I~//~v@@6R~
//      tdsPair[PAIRPOS_RIVER_TAKEN_KAN]=td;                           //~v@@@R~//~v@@6R~
        if (!PswServer)                                            //~v@@@I~
	        UA.msgDataToServer=makeMsgDataToServer(Pplayer,tdsPair,PAIRCTR_KAN);//~v@@@R~//~v@@6R~
//      else                                                       //~v@@6I~//~9209R~
//      	postNextPlayer(Pplayer,td);	//switch to next player after delay a moment//~v@@6I~//~9209R~//~9226R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
	//*rc=-1:err,0:selected 4 tiles,1:dup                          //~v@@6R~
	//*************************************************************************//~v@@@I~
    private int selectInfo(int Pplayer)            //~v@@@I~//~v@@6R~//~0407R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAKan.selectInfo player="+Pplayer);//~v@@@R~//~v@@6R~
        int rc=0;                                                  //~v@@6M~
//      TileData[] tds=new TileData[PAIRCTR_KAN];                  //~v@@6I~//~0407R~
//      TileData[] tdsAdd=PLS.getKanAddEarth(Pplayer,false/*swRep Earth*/); //TDF_KAN_ADD wille be set//~v@@6R~//~0407R~
//        if (tdsAdd!=null)   //kan-add                              //~v@@6R~//~0407R~
//        {                                                          //~v@@6I~//~0407R~
//            if (!chkSelected(tdsAdd))                              //~v@@6R~//~0407R~
//            {                                                      //~v@@6I~//~0407R~
//                if (getKanTiles(Pplayer)>=0)    //ankan/minkan     //~v@@6R~//~0407R~
//                {                                                  //~v@@6I~//~0407R~
//                    tdsAdd=null;                                   //~v@@6I~//~0407R~
//                    if (swSelected)                                //~v@@6R~//~0407R~
//                        tdsAdd=null;    //use tds of getKanTile    //~v@@6I~//~0407R~
//                    else                                           //~v@@6I~//~0407R~
//                        rc=1;                                      //~v@@6R~//~0407R~
//                }                                                  //~v@@6I~//~0407R~
//            }                                                      //~v@@6I~//~0407R~
//            if (tdsAdd!=null)                                      //~v@@6R~//~0407R~
//            {                                                      //~v@@6I~//~0407R~
//                kanType=KAN_ADD;                                   //~v@@6I~//~0407R~
//                tdsPair=tdsAdd;                                   //~v@@6I~//~0407R~
//            }                                                      //~v@@6I~//~0407R~
//        }                                                          //~v@@6I~//~0407R~
//        else                                                       //~v@@6I~//~0407R~
//        {                                                          //~v@@6I~//~0407R~
//            rc=getKanTiles(Pplayer);    //ankan/minkan             //~v@@6R~//~0407R~
//        }                                                          //~v@@6I~//~0407R~
//      if (rc==0)                                                 //~v@@6I~//~9B30R~
//      	setKanType();                                          //~v@@6I~//~9B30R~
        int rcEarth=chkEarth(Pplayer);	//candidate on earth       //~0407M~
        if (swSelected)	//chankan not selected                     //~0407I~
        {                                                          //~0407I~
        	rc=rcEarth;                                            //~0407I~
            tdsPair=tdsAddKan;                                     //~0407I~
            kanType=KAN_ADD;                                       //~0407I~
        }                                                          //~0407I~
        else                                                       //~0407I~
        {                                                          //~0407I~
            rc=getKanTiles(Pplayer);    //ankan/minkan             //~0407I~
            if (!swSelected)                                       //~0407I~
            {                                                      //~0407I~
                if (rc==0)                                         //~0407R~
                {                                                  //~0407R~
                    if (rcEarth!=-1)                               //~0407R~
                        rc=1;                                      //~0407R~
                }                                                  //~0407R~
                else                                               //~0407R~
                if (rc==-1)                                        //~0407R~
                {                                                  //~0407R~
                    rc=rcEarth;                                    //~0407R~
                    if (rc==0)                                     //~0407R~
                    {                                              //~0407I~
                        tdsPair=tdsAddKan;                         //~0407R~
			            kanType=KAN_ADD;                           //~0407I~
                    }                                              //~0407R~
                }                                                  //~0407R~
            }                                                      //~0407I~
        }                                                          //~0407I~
        if (Dump.Y) Dump.println("UAKan.selectInfo rc="+rc+",kanType="+kanType+",tdsPair="+TileData.toString(tdsPair));//~v@@@I~//~v@@6R~//~0407R~
        return rc;
    }                                                              //~v@@@I~
	//*************************************************************************//~0407I~
	//*rc>0:multiple,0:match on a Pon,-1:no match with pon         //~0407I~
	//*************************************************************************//~0407I~
//  public int chkEarth(int Pplayer)                               //~0407I~//~vajbR~
    private int chkEarth(int Pplayer)                              //~vajbI~
    {                                                              //~0407I~
        if (Dump.Y) Dump.println("UAKan.chkEarth player="+Pplayer+",selected="+TileData.toString(selectedTile));//~0407I~
        int rc=-1;                                                 //~0407I~
        TileData[][] tdssPon=PLS.getPonsEarth(Pplayer);	//pon on earth//~0407I~
        TileData[] tdsH=AG.aHands.getHands(PLAYER_YOU);            //~0407I~
        TileData[] tdsPon=null;                                    //~0407I~
        TileData tdAdd=null;                                       //~0407I~
        swSelected=false;                                          //~0407I~
        if (tdssPon!=null)                                         //~0407I~
        {                                                          //~0407I~
        	for (TileData[] tds:tdssPon)                           //~0407I~
            {                                                      //~0407I~
            	if (tds==null)                                     //~0407I~
                	break;                                         //~0407I~
        		if (Dump.Y) Dump.println("UAKan.chkEarth tdsPon="+TileData.toString(tds));//~0407I~
                for (TileData tdH:tdsH)                            //~0407R~
                {                                                  //~0407R~
                    if (!chkSelected(tds,tdH))	//make quad        //~0407R~
                    	continue;                                  //~0407I~
                    tdsPon=tds;                                    //~0407I~
                    tdAdd=tdH;                                     //~0407I~
                    rc++;                                          //~0407I~
					if (chkSelected(tds)) //consider selected=just taken//~0407I~
                    {                                              //~0407I~
                    	swSelected=true;                           //~0407I~
                        rc=0;                                      //~0407I~
                    	break;                                     //~0407I~
                    }                                              //~0407I~
                }                                                  //~0407I~
                if (swSelected)                                    //~0407I~
                	break;                                         //~0407I~
            }                                                      //~0407I~
        }                                                          //~0407I~
        if (rc==0)                                                 //~0407I~
        {                                                          //~0407I~
			TileData[] pair4=new TileData[PAIRCTR_KAN];            //~0407I~
            System.arraycopy(tdsPon,0,pair4,0,PAIRCTR);            //~0407I~
            pair4[PAIR_KAN_ADDPOS]=tdAdd;                          //~0407I~
    		tdsAddKan=pair4;                                       //~0407I~
        }                                                          //~0407I~
        if (Dump.Y) Dump.println("UAKan.selectInfo rc="+rc+",swSelected="+swSelected+",kanType="+kanType+",tdsAddKan="+TileData.toString(tdsAddKan));//~0407R~
        return rc;                                                 //~0407I~
    }                                                              //~0407I~
//    //*********************************************************************//~v@@6R~
//    //*search 4 tiles in Hands                                   //~v@@6R~
//    //*********************************************************************//~v@@6R~
//    private int searchKanTiles(TileData[] Ptds,TileData Ptd,int Ppos)//~v@@6R~
//    {                                                            //~v@@6R~
//        int pos=-1,ctr=0;                                        //~v@@6R~
//        for (int ii=Ppos;ii<Ptds.length-(PAIRCTR_KAN-1);ii++)    //~v@@6R~
//        {                                                        //~v@@6R~
//            if (!TileData.TDCompareTN(Ptds[ii],Ptd))             //~v@@6R~
//                break;                                           //~v@@6R~
//            ctr++;                                               //~v@@6R~
//            if (ctr==PAIRCTR_KAN-1)                              //~v@@6R~
//            {                                                    //~v@@6R~
//                pos=ii-(ctr-1);                                  //~v@@6R~
//                break;                                           //~v@@6R~
//            }                                                    //~v@@6R~
//        }                                                        //~v@@6R~
//        if (Dump.Y) Dump.println("UAKan.searchKanTiles Ppos="+Ppos+",pos="+pos+",Ptd="+Ptd.toString());//~v@@6R~
//        return pos;                                              //~v@@6R~
//    }                                                            //~v@@6R~
    //*********************************************************************//~v@@6I~
    //*search 3 tiles in Hands                                     //~v@@6I~
    //*********************************************************************//~v@@6I~
    private boolean searchKanTiles(TileData[] Ptds,TileData Ptd,int Ppos,int PposEnd,TileData[] PtdsOut,boolean PswAll)//~v@@6R~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.searchKanTiles Ppos="+Ppos+",PposEnd="+PposEnd+",swTaken="+PswAll+",Ptd="+Ptd.toString());//~v@@6R~
    	if (PswAll)                                                //~v@@6R~
		    return searchKanTiles(Ptds,Ptd,Ppos,PposEnd,PtdsOut);  //~v@@6I~
        int ctr=0;                                                 //~v@@6I~
        boolean rc=false;                                          //~v@@6I~
        for (int ii=Ppos;ii<PposEnd;ii++)                          //~v@@6I~
        {                                                          //~v@@6I~
            if (!TileData.TDCompareTN(Ptds[ii],Ptd))               //~v@@6I~
                break;                                             //~v@@6I~
            PtdsOut[ctr++]=Ptds[ii];                               //~v@@6I~
        }                                                          //~v@@6I~
        if (ctr==PAIRCTR) //3                                      //~v@@6I~
        {                                                          //~v@@6I~
            PtdsOut[ctr]=Ptd;                                      //~v@@6I~
            rc=true;                                               //~v@@6I~
        }                                                          //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.searchKanTiles rc="+rc+",out:"+(!rc?"none":TileData.toString(PtdsOut)));//~v@@6R~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
    //*********************************************************************//~v@@6I~
    //*search 3 tiles for current Taken                            //~v@@6I~
    //*********************************************************************//~v@@6I~
    private boolean searchKanTiles(TileData[] Ptds,TileData Ptd,int Ppos,int PposEnd,TileData[] PtdsOut)//~v@@6I~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.searchKanTiles for current taken Ppos="+Ppos+",PposEnd="+PposEnd+",Ptd="+Ptd.toString());//~v@@6I~
        int ctr=0;                                                 //~v@@6I~
        boolean rc=false;                                          //~v@@6I~
        for (int ii=Ppos;ii<PposEnd;ii++)                          //~v@@6I~
        {                                                          //~v@@6I~
            if (TileData.TDCompareTN(Ptds[ii],Ptd))                //~v@@6I~
            {                                                      //~v@@6I~
            	PtdsOut[ctr++]=Ptds[ii];                           //~v@@6I~
            }                                                      //~v@@6I~
        }                                                          //~v@@6I~
        if (ctr==PAIRCTR) //3                                      //~v@@6I~
        {                                                          //~v@@6I~
            PtdsOut[ctr]=Ptd;                                      //~v@@6I~
            rc=true;                                               //~v@@6I~
        }                                                          //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.searchKanTiles rc="+rc+",out:"+(rc?"none":TileData.toString(PtdsOut)));//~v@@6I~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@6I~
	//*rc=0:single pair,1:multiple pair,-1:no pair                 //~9B30I~
	//*************************************************************************//~9B30I~
    public int getKanTiles(int Pplayer)                            //~v@@6I~
    {                                                              //~v@@6I~
        TileData[] tdsH=AG.aHands.getHands(PLAYER_YOU);            //~v@@5I~//~v@@6R~
	    TileData[] tdsKan=new TileData[PAIRCTR_KAN];                 //~v@@6I~
	    TileData[] tdsWK=new TileData[PAIRCTR_KAN];                //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.getkanTiles player="+Pplayer+",hands:"+TileData.toString(tdsH));//~v@@6I~
        TileData td;                                               //~v@@6I~
        int ctr4Pair=0;                                        //~v@@6R~
        int rc=-1,rc2;                                             //~v@@6R~
        swSelected=false;                                          //~v@@6I~
        if (Pplayer!=PLS.getCurrentPlayer())	//minkan           //~v@@6R~
        {                                                          //~v@@6I~
	        td=PLS.getLastDiscarded();                        //~v@@@R~//~v@@6I~
            if (searchKanTiles(tdsH,td,0,tdsH.length,tdsKan,true/*swAll*/))//~v@@6R~
            {                                                      //~v@@6I~
//              td.setTakenRiver();                                //~v@@6R~
//              Tiles.setFlag(tdsKan,TDF_KAN_RIVER);                             //~v@@@I~//~v@@6R~
                kanType=KAN_RIVER;   //minkan                      //~v@@6R~
                rc=0;                                              //~v@@6I~
            }                                                      //~v@@6I~
	        swSelected=true;                                       //~v@@6I~
        }                                                          //~v@@6I~
        else                                                       //~v@@6I~
        {                                                          //~v@@6I~
            for (int ii=0;ii<tdsH.length-PAIRCTR_KAN;ii++)         //~v@@6R~
            {                                                      //~v@@6I~
				if (searchKanTiles(tdsH,tdsH[ii],ii+1,tdsH.length-1,tdsWK,false/*PswAll*/))//~v@@6R~
                {                                                  //~v@@6I~
                	ctr4Pair++;                                    //~v@@6I~
                    System.arraycopy(tdsWK,0,tdsKan,0,PAIRCTR_KAN);//~v@@6M~
					if (chkSelected(tdsWK))                        //~v@@6R~
                    {                                              //~v@@6I~
                    	swSelected=true;                           //~v@@6I~
                    	break;                                     //~v@@6I~
                    }                                              //~v@@6I~
                }                                                  //~v@@6I~
            }                                                      //~v@@6I~
            if (!swSelected)                                       //~v@@6I~
            {                                                      //~v@@6I~
                if (searchKanTiles(tdsH,tdsH[tdsH.length-1]/*takenTile*/,0,tdsH.length-1,tdsWK,true/*PswAll*/))//~v@@6R~//~0407R~
                {                                                  //~v@@6R~
                    ctr4Pair++;                                    //~v@@6R~
                    System.arraycopy(tdsWK,0,tdsKan,0,PAIRCTR_KAN);//~v@@6R~
                    if (chkSelected(tdsWK))                        //~v@@6I~
                    {                                              //~v@@6I~
                        swSelected=true;                           //~v@@6I~
                    }                                              //~v@@6I~
                }                                                  //~v@@6R~
            }                                                      //~v@@6I~
            if (swSelected || ctr4Pair==1)                         //~v@@6R~
            {                                                      //~v@@6I~
                rc=0;                                              //~v@@6I~
    	        kanType=KAN_TAKEN;   //ankan                      //~v@@6I~//~0403R~
//              Tiles.setFlag(tdsKan,TDF_KAN_TAKEN);               //~v@@6R~
            }                                                      //~v@@6I~
            else                                                   //~v@@6I~
            if (ctr4Pair>1)                                        //~v@@6I~
                rc=1;                                              //~v@@6I~
        }                                                          //~v@@6I~
	    tdsPair=null;                                              //~v@@6I~
        if (rc==0)                                                 //~v@@6I~
            tdsPair=tdsKan;                                        //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.getkanTiles rc="+rc+",swSelected="+swSelected+",ctrPair="+ctr4Pair+",out="+(rc!=0?"null":TileData.toString(tdsPair)));//~v@@6R~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@6I~
    private void setKanType()                                      //~v@@6I~
    {                                                              //~v@@6I~
        if (Dump.Y) Dump.println("UAKan.setKanType kanType="+kanType);//~0401I~//~vaaUR~
	    TileData td;                                               //~v@@6I~
    	switch(kanType)                                            //~v@@6I~
        {                                                          //~v@@6I~
        case KAN_ADD:                                              //~v@@6I~
            td=tdsPair[PAIR_KAN_ADDPOS];                           //~v@@6I~
	        td.setKanAddedTile();                                      //~v@@6I~
	        Tiles.setFlag(tdsPair,TDF_KAN_ADD);                    //~v@@6I~
        	break;                                                 //~v@@6I~
        case KAN_RIVER:                                            //~v@@6I~
	        td=PLS.getLastDiscarded();                             //~v@@6I~
            td.setTakenRiver();                                    //~v@@6I~
	        Tiles.setFlag(tdsPair,TDF_KAN_RIVER);                  //~v@@6I~
        	break;                                                 //~v@@6I~
        default:                                                   //~v@@6I~
            Tiles.setFlag(tdsPair,TDF_KAN_TAKEN);                  //~v@@6I~
        }                                                          //~v@@6I~
    }                                                              //~v@@6I~
	//*************************************************************************//~v@@@I~
    public boolean takeKan(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~v@@@R~//~v@@6R~
    {                                                              //~v@@@I~
    	boolean swDraw;                                            //~v@@@R~
        TileData[] tds;                                            //~v@@@R~
        int rc;                                                    //~v@@6I~
//        boolean swAdjustOpen=false;                                //~9301I~//~9302R~
    //***********************                                      //~v@@@I~
        sw4KanDrawn=false;                                         //~vakJI~
    	tds4KanDrawn=null;	//to be sent                           //~vakJI~
        if (Dump.Y) Dump.println("UAKan.takeKan swServer="+PswServer+",player="+Pplayer+",swReceived="+PswReceived+",intp="+Arrays.toString(PintParm));//~v@@@R~//~v@@6R~//~0406R~//~1124R~
//      AG.aUADelayed.actionDone(GCM_KAN,PswServer,PswReceived,Pplayer);//~9B23I~//~9B27R~
        if (!PswReceived)                                          //~v@@@I~
        {                                                          //~v@@@I~
            tds=tdsPair;                                           //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            tds=getReceivedPair(PintParm,PARMPOS_TD,PAIRCTR_KAN);      //~v@@@I~//~v@@6R~
        }                                                          //~v@@@I~
//    if (true) //TODO test                                        //~vakJR~
//  	tds4KanDrawn=tds;                                          //~vakJR~
        if (Dump.Y) Dump.println("UAKan.takeKan tds="+TileData.toString(tds));//~v@@@I~//~v@@6R~
//      UADiscard.setDiscardedPlayer(tds[PAIRPOS_RIVER_TAKEN]);    //~v@@@R~
        if (PswServer)                                             //~v@@@I~
        {                                                          //~v@@@I~
//          if (PLS.getKanType()!=KAN_RIVER)                       //~9218I~//~9529R~
            if (PLS.getKanType()==0)	//after discarded          //~9529I~
			    stock.drawPendingOpenDora(Pplayer,GCM_KAN);        //~9529R~
	        rc=PLS.takeKan(Pplayer,tds);    //insert into Hands       //~v@@@R~//~v@@6R~
            if (rc<0)                                              //~v@@6I~
            {                                                      //~v@@6I~
        		if (Dump.Y) Dump.println("UAKan.takeKan PLS.takekan rc="+rc);//~v@@6I~
                return false;                                      //~v@@6I~
            }                                                      //~v@@6I~
//          unlockRiverTile(rc);    //allow rinshan take at UATake//~v@@6I~//~0402R~
//          UserAction.showInfoAllEswn(0/*opt*/,Pplayer,Utils.getStr(R.string.UserAction_Kan));//~v@@6R~//~9C02R~
//      	UA.msgDataToClient=makeMsgDataToClient(Pplayer,tds,PAIRCTR_KAN);//~v@@@R~//~v@@6R~//~vakqR~
//      	AG.aUADelayed.resetWait(Pplayer);	//switch to next player after delay a moment//~v@@6I~//~9226R~//~9B27R~
        	UADL.resetWait(Pplayer);	//switch to next player after delay a moment//~9B27I~
	        UADL.resetWait2Touch(PswServer,GCM_KAN,Pplayer);	//switch to next player after delay a moment//~9B27R~
//            swAdjustOpen=Pplayer!=PLAYER_YOU;                      //~9301I~//~9302R~
            sendHands(Pplayer);                                    //~9302I~
//        if (false) //TODO test                                   //~vakJR~
//          if (!AG.swTrainingMode && !PswReceived)                //~vakJR~
            if (!AG.swTrainingMode)                                //~vakJI~
		    	tds4KanDrawn=tds;	//to be sent to client         //~vakJR~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            if (PswReceived)                                       //~9218I~
            {                                                      //~9218I~
//              if (PLS.getKanType()!=KAN_RIVER)                   //~9218I~//~9529R~
	            if (PLS.getKanType()==0)    //afterdiscarded       //~9529I~
				    stock.drawPendingOpenDora(Pplayer,GCM_KAN);    //~9529R~
//                swAdjustOpen=Pplayer!=PLAYER_YOU;                  //~9301I~//~9302R~
            }                                                      //~9218I~
			boolean swShadow=Pplayer!=PLAYER_YOU;                  //~v@@@I~
	        rc=PLS.takeKanOtherOnClient(swShadow,Pplayer,tds);    //insert into Hands//~v@@@R~//~v@@6R~
            if (PswReceived)                                       //~v@@6I~
            {                                                      //~v@@6I~
//      		AG.aUADelayed.resetWait(Pplayer);	//switch to next player after delay a moment//~v@@6I~//~9226R~//~9B27R~
        		UADL.resetWait(Pplayer);	//switch to next player after delay a moment//~9B27I~
	        	UADL.resetWait2Touch(PswServer,GCM_KAN,Pplayer);	//switch to next player after delay a moment//~9B27R~
//          	unlockRiverTile(rc);    //chk minkan at UATake to enable rinshan take            //~v@@6I~//~0402R~
            }                                                      //~v@@6I~
        }                                                          //~v@@@I~
//        if (swAdjustOpen)   //if opened remove kan pair            //~9301I~//~9302R~
//            adjustOpen(PswServer,Pplayer,tds);                     //~9301R~//~9302R~
//		if (Pplayer==PLAYER_YOU)                                   //~v@@@R~
	    	hands.takeKan(Pplayer,rc);  //draw Hands                  //~v@@@R~//~v@@6R~
        if (Dump.Y) Dump.println("UAKan.takeKan chk kan river tds="+TileData.toString(tds));//~0403I~
        if (tds[0].isKanRiver())                                   //~v@@6I~
        {                                                          //~v@@6I~
        	int playerDiscarded=PLS.getLastDiscardedPlayer();          //~v@@@I~//~v@@6R~
        	river.takeKan(playerDiscarded,rc);                                     //~v@@@R~//~v@@6R~
	        AG.aAnim.calledPonKanChii(TDF_KAN_RIVER,AG.aEarth.playerDrawEarth,AG.aEarth.rectTileCalled,AG.aEarth.tdOnEarth,AG.aEarth.bmOnEarth,river.playerDiscarded,river.rectTileCalled,river.bmCalledOnRiver);//+vamhR~
        }                                                          //~v@@6I~
        else                                                       //~vamhI~
        	AG.aAnim.calledPonKanChii(TDF_KAN_TAKEN,AG.aEarth.playerDrawEarth,AG.aEarth.rectTileCalled,AG.aEarth.tdOnEarth,AG.aEarth.bmOnEarth,-1,null,null);//~vamhR~
        AG.aTiles.addCtrKan(true/*swUpdate*/);                     //~v@@6I~
//      stock.takeKan(Pplayer,rc);	//draw dora                    //~v@@6R~//~9209R~
//      stock.drawNextOneKan();		//mark wanpai                  //~v@@6I~//~0403R~
//      setTimeout(PswServer,Pplayer);    //timeout to take wanpai //~9623I~//~0403R~
        if (TestOption.getTimingBTIOErr()==TestOption.BTIOE_AFTER_KAN)//~9A28I~
          	TestOption.disableBT();                                //~9A28I~
        if (Dump.Y) Dump.println("UAKan.takeKan kanType="+PLS.getKanType()+",swAnkanRon="+swAnkanRon);//~0402I~//~va60R~//~vaaWR~//~vakDR~
//      if (!tds[0].isKanRiver())                                  //~0403I~//~0405R~
        if (tds[0].isKanAdd()                                      //~0405I~
//      ||  (tds[0].isKanTaken() && swAnkanRon)                    //~0405R~//~vaaWR~
//      ||  (tds[0].isKanTaken())                                  //~vaaWI~//~vakDR~
        ||  (tds[0].isKanTaken() && swAnkanRon && RAUtils.isTerminal(RAUtils.getPosTile(tds[0])))//~vakDI~
           )                                                       //~0405I~
        {                                                          //~0403I~
        	UADL.setRonable(true);     	//for dup ron availability,reset at take//~0402I~
	        if (PswServer)                                         //~0403I~
//  	    	postDelayedTakableKan(Pplayer,tds[0]);	//swith to next player after delay a moment//~0403I~//~vaaUR~
    	    	postDelayedTakableKan(Pplayer);	//swith to next player after delay a moment//~vaaUI~
            setTakable(false/*swTakable*/,Pplayer);                //~0403I~
        }                                                          //~0403I~
        else                                                       //~0403I~
        {                                                          //~0403I~
        	setTimeout(false/*timeout*/,PswServer,Pplayer);    //timeout to take wanpai//~0403I~
			stock.disableNextMark();                               //~0403I~
        }                                                          //~0403I~
        if (PswServer||PswReceived)                                  //~vaaUI~
    	    AG.aRoundStat.calledKan(PswServer,Pplayer,PLS.getKanType(),tds[0]);    //notify ronable//~vaaUR~
        if (PswServer) //after setTakable,setRonable               //~vakqI~
        {                                                          //~vakJI~
          if (sw4KanDrawn)                                         //~vakJI~
        	UA.setNoMsgToClient(); //already sent before send stopauto//~vakJI~
          else                                                     //~vakJI~
        	UA.msgDataToClient=makeMsgDataToClient(Pplayer,tds,PAIRCTR_KAN);//~vakqI~
        }                                                          //~vakJI~
//      GMsg.showHL(0,GCM_KAN);                                    //~9C02I~//~0401R~
        GMsg.showHL(0,GCM_KAN,Pplayer);                            //~0401I~
    	Sound.play(SOUNDID_KAN,false/*not change to beep when beeponly option is on*/);//~0408I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~9301I~//~9302R~
//    //*for player!=0                                               //~9301I~//~9302R~
//    //*************************************************************************//~9301I~//~9302R~
//    private void adjustOpen(boolean PswServer,int Pplayer,TileData[] Ptds)//~9301R~//~9302R~
//    {                                                              //~9301I~//~9302R~
//        if (Dump.Y) Dump.println("UAKan.adjustOpen player="+Pplayer);//~9301I~//~9302R~
//        if (PLS.getHands(Pplayer)!=null)                        //~9301R~//~9302R~
//        {                                                          //~9301I~//~9302R~
//            PLS.removePair(Pplayer,Ptds);                               //~9301I~//~9302R~
//            if (PLS.isOpenReach(Pplayer))                        //~9302R~
//                AG.aHands.open(Pplayer);                                   //~9301I~//~9302R~
//        }                                                          //~9301I~//~9302R~
//    }                                                              //~9301I~//~9302R~
	//*************************************************************************//~9302I~
    private void sendHands(int Pplayer)                            //~9302I~
    {                                                              //~9302I~
        boolean swReach=PLS.getReachStatus(Pplayer)==REACH_DONE;   //~9302I~
        if (Dump.Y) Dump.println("UKan.sendHands player="+Pplayer+",swReach="+swReach);//~9302I~//~vaaVR~
        if (swReach)                                               //~9302I~
	        GameViewHandler.sendMsg(GCM_OPEN,Pplayer,OPT_OPEN_KAN,0);//~9302I~
    }                                                              //~9302I~
	//*************************************************************************//~9209I~
    private boolean isLocked(int Pplayer)                          //~9209I~
    {                                                              //~9209I~
	    if (Dump.Y) Dump.println("UAKan.isLocked player="+Pplayer);//~9629I~
//        boolean rc=false;                                          //~9209I~//~9629R~
//        TileData td=PLS.getLastDiscarded();                        //~9209I~//~9629R~
//        if (td!=null && td.isLockedPonKan())                       //~9209I~//~9629R~
//        {                                                          //~9209I~//~9629R~
//            if (Dump.Y) Dump.println("UAKan.isLocked ignore Pon by Locked td="+td.toString());//~9209I~//~9629R~
//            UserAction.sendErr(0,Pplayer,R.string.Info_WaitDiscardTimeoutPonKan);//~9209I~//~9629R~
//            rc=true;                                               //~9209I~//~9629R~
//        }                                                          //~9209I~//~9629R~
		boolean rc=UA.UAP.isLocked(Pplayer);                               //~9629I~
	    if (Dump.Y) Dump.println("UAKan.isLocked rc="+rc);         //~9209I~
        return rc;                                                 //~9209I~
    }                                                              //~9209I~
//    //*************************************************************************//~v@@6I~//~0402R~
//    //*to allow kanTake immediately                                //~v@@6I~//~0402R~
//    //*************************************************************************//~v@@6I~//~0402R~
//    private void unlockRiverTile(int PkanType)                     //~v@@6I~//~0402R~
//    {                                                              //~v@@6I~//~0402R~
//        if (Dump.Y) Dump.println("UAKan.unlockRiverTile kantype="+PkanType);//~v@@6I~//~0402R~
//      if (false) //TODO test                                     //~0402I~
//        if (PkanType==KAN_RIVER)                                   //~v@@6I~//~0402R~
//        {                                                          //~v@@6I~//~0402R~
//            TileData td=PLS.getLastDiscarded();                    //~v@@6I~//~0402R~
//            td.setLock(false);                                     //~v@@6I~//~0402R~
//        }                                                          //~v@@6I~//~0402R~
//    }                                                              //~v@@6I~//~0402R~
	//*************************************************************************//~0403I~
	//*lock when ankan/minkan                                      //~0403I~
	//*************************************************************************//~0403I~
    private void lockTaken(boolean PswOn)                           //~0403I~
    {                                                              //~0403I~
      if (false)	//TODo test                                    //~vakqR~
      {                                                            //~vakqR~
        TileData td=PLS.getCurrentTaken();                            //~0403I~
        if (Dump.Y) Dump.println("UAKan.lockTaken sw="+PswOn+",lastTaken="+td.toString());//~0403I~//~0404R~
//        if (!td.isKanRiver())                                      //~0403I~//~0404R~
//        {                                                          //~0403I~//~0404R~
//                                                                   //~0403I~//~0404R~
//            td.setLock(false);                                     //~0403I~//~0404R~
//        }                                                          //~0403I~//~0404R~
        td.setLock(PswOn);                                         //~0404I~
      }                                                            //~vakqR~
      else                                                         //~vakqR~
      {                                                            //~vakqR~
        TileData td=PLS.getCurrentTile();                          //~vakqR~
        if (Dump.Y) Dump.println("UAKan.lockTaken sw="+PswOn+",currentTile="+ Utils.toString(td));//~vakqR~
        if (td!=null)                                              //~vakqR~
        {                                                          //~vakqI~
			if (Dump.Y) Dump.println("UAKan.lockTaken currentTile objectID="+td.hashCode());//~vakqI~
	        td.setLockKanTake(PswOn);                              //~vakqR~
        }                                                          //~vakqI~
      }                                                            //~vakqR~
    }                                                              //~0403I~
	//*************************************************************************//~v@@@I~
    public  String makeMsgDataToServer(int Pplayer,TileData[] Ptds,int Pctr)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAKan.makeMsgDataToServer");//~v@@@R~//~v@@6R~
        return UAPon.makeMsgDataToServer(Pplayer,Ptds,Pctr);       //~v@@6I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public  String makeMsgDataToClient(int Pplayer,TileData[] Ptds,int Pctr)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAKan.makeMsgDataToClient");//~v@@@I~//~v@@6R~
    	return UAPon.makeMsgDataToClient(Pplayer,Ptds,Pctr);       //~v@@6I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    private TileData[] getReceivedPair(int[] PintParm,int Ppos,int PpairCtr)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UAKan.getReceivedPair ctr="+PpairCtr);//~v@@@I~//~v@@6R~
	    return UAPon.getReceivedPair(PintParm,Ppos,PpairCtr);      //~v@@6I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@6I~//~9209R~
//    //*at Kan,take timeing take for ron by chankan and kokusi for ankan//~v@@6I~//~9209R~
//    //*************************************************************************//~v@@6I~//~9209R~
//    public void postNextPlayer(int Pplayer,TileData Ptd)           //~v@@6I~//~9209R~
//    {                                                              //~v@@6I~//~9209R~
//        if (Dump.Y) Dump.println("UAKan.postNextPlayer player="+Pplayer);//~v@@6I~//~9209R~
//        UA.UADL.postDelayed(GCM_KAN,delayTake,GCM_NEXT_PLAYER,Pplayer,Ptd);//~v@@6I~//~9209R~
//    }                                                              //~v@@6I~//~9209R~
	//*************************************************************************//~v@@@M~
	//*Test TODO                                                   //~v@@@M~
	//*************************************************************************//~v@@@M~
    private int  selectInfoTest(int Pplayer)     //~v@@@M~                    //~v@@6R~
    {                                                              //~v@@@M~
        TileData[] tdsh=AG.aHands.getHands(PLAYER_YOU);            //~v@@5I~//~v@@6I~
        if (Dump.Y) Dump.println("UAKan.selectInfoTest player="+Pplayer+",hands:"+TileData.toString(tdsh ));//~v@@@M~//~v@@6R~
        TileData[] tds=new TileData[PAIRCTR_KAN];                      //~v@@@M~//~v@@6R~

        tds[0]=tdsh[0];                                            //~v@@@M~
        tds[1]=tdsh[1];                                            //~v@@@M~
        tds[2]=tdsh[2];                                            //~v@@6I~
        if (Pplayer!=PLS.getCurrentPlayer())                       //~v@@6I~
        {                                                          //~v@@6I~
	        tds[3]=PLS.getLastDiscarded();                         //~v@@6I~
            tds[3].setTakenRiver();                                //~v@@6I~
	        Tiles.setFlag(tds,TDF_KAN_RIVER);                      //~v@@6I~
        }                                                          //~v@@6I~
        else                                                       //~v@@6I~
        {                                                          //~v@@6I~
	        tds[3]=tdsh[3];                                        //~v@@6I~
        	if ((TestOption.option & TestOption.TO_KAN_ADD)!=0)               //~v@@6I~
	    		Tiles.setFlag(tds,TDF_KAN_ADD);                    //~v@@6I~
            else                                                   //~v@@6I~
	    		Tiles.setFlag(tds,TDF_KAN_TAKEN);                  //~v@@6R~
        }                                                          //~v@@6I~
        tdsPair=tds;                                               //~v@@@M~
        if (Dump.Y) Dump.println("UAKan.selectInfoTest currentPlayer="+PLS.getCurrentPlayer()+",out:"+TileData.toString(tdsPair));//~v@@6R~
        return 0;                                                  //~v@@@M~
     }                                                             //~v@@@M~
	//*************************************************************************//~9623I~
	//*On Server; minkan or ron time timeout of ankan,chankan, schedule auto take timeout//~0403R~
	//*also from GC at reset waiting win                           //~vaaVI~
	//*************************************************************************//~9623I~
//  private void setTimeout(boolean PswTimeout,boolean PswServer,int Pplayer)          //~9623I~//~0403R~//~va60R~//~vaaVR~
//  public  void setTimeout(boolean PswTimeout,boolean PswServer,int Pplayer)//~vaaVI~//~vakqR~
    private  void setTimeout(boolean PswTimeout,boolean PswServer,int Pplayer)//~vakqI~
    {                                                              //~9623I~
        if (Dump.Y) Dump.println("UAKan.setTimeout swManualRobot="+swManualRobot+",swTimeout="+PswTimeout+",swServer="+PswServer+",player="+Pplayer+",ctrTakenAll="+PLS.ctrTakenAll);//~9623R~//~9624R~//~0403R~//~vakmR~
        setTakable(true/*swTakable*/,Pplayer);                     //~0403I~
//      if (swManualRobot)                                         //~va66R~//~vakCR~
        if (swManualRobot && AG.aAccounts.isDummyPlayer(Pplayer))  //~vakCI~
        {                                                          //~va66R~
//      	chk4KanDrawn(true/*PswAtCall*/,Pplayer);               //~vakmR~//~vakwR~
        	if (!chk4KanDrawn(true/*PswAtCall*/,Pplayer))          //~vakwI~
            	infoMsgTakeRinshan(Pplayer);                       //~vakwI~
            if (Dump.Y) Dump.println("UAKan.setTimeout return by manualRobot in training mode");//~va66R~
            return;                                                //~va66R~
        }                                                          //~va66R~
//      UA.UADL.postDelayedAutoTakeKan(PswServer,Pplayer,PLS.ctrTakenAll,PLS.ctrDiscardedAll);//~9623R~//~9627R~//~0403R~//~0406R~//~vakqR~
//* if PswTimeout==false,callback autoTakeKanTimeout_0 if Server   //~vakqI~
        UA.UADL.postDelayedAutoTakeKan(PswServer,Pplayer,PLS.ctrTakenAll,PLS.ctrDiscardedAll,PswTimeout);//~vakqI~
        if (PswTimeout)	//!minkan	                               //~0403I~
        {                                                          //~0403I~
	    	int eswn=AG.aAccounts.playerToEswn(Pplayer);           //~0403I~
		    String msg=eswn+MSG_SEPAPP2+PLS.ctrTakenAll+MSG_SEPAPP2+PLS.ctrDiscardedAll;//~0403R~//~0404R~
	        UA.sendToClient(true/*PswSendAll*/,false/*PswRobot*/,GCM_TIMEOUT_TO_TAKABLE_RINSHAN,Pplayer,msg);//~v@@@R~//~9624M~//~0403R~
        }                                                          //~0403I~
    }                                                              //~9623I~
	//*************************************************************************//~0403I~
	//*On Client By GCM_TIMEOUT_TO_TAKABLE_RINSHAN                 //~0403R~
	//*************************************************************************//~0403I~
    public boolean clientTakableRinshan(int Pplayer,int[] PintParm)//~0403R~
    {                                                              //~0403I~
    	boolean rc=true;                                           //~0403I~
        if (Dump.Y) Dump.println("UAKan.clientTakableRinshan player="+Pplayer+",intP="+Arrays.toString(PintParm));//~0403I~
        int ctrTaken=PintParm[1];                                  //~0403I~
        int ctrDiscarded=PintParm[2];                              //~0403I~
        if  (AG.aPlayers.isActionDoneExceptRon(Pplayer,ctrTaken,ctrDiscarded))//~0403I~//~0404R~
        {                                                          //~0403I~
        	if (Dump.Y) Dump.println("UAKan.clientTakableRinshan tile intercepted or take advanced");//~0403I~
            return false;                                          //~0403I~
        }                                                          //~0403I~
        setTakable(true/*swTakable*/,Pplayer);                     //~0403I~
      if (UADL.isPendingHW())                                      //~vakLI~
      {                                                            //~vakLI~
        if (Dump.Y) Dump.println("UAKan.clientTakableRinshan @@@@ skip postDelayedAutoTakeKan by stopAutoCtr");//~vakLI~
	    int eswn=AG.aAccounts.playerToEswn(Pplayer);               //~vakLI~
    	UA.showInfoAllEswnEswn(0,eswn,R.string.Info_StopAutoByDrawnHW);//~0225I~//~vakLI~
      }                                                            //~vakLI~
      else                                                         //~vakLI~
      {                                                            //~vakLI~
	    infoMsgTakeRinshan( Pplayer);                              //~vakLR~
//      AG.aRoundStat.timeoutRinshanTakableClient(Pplayer);        //~vaaUR~
//      UA.UADL.postDelayedAutoTakeKan(false/*PswServer*/,Pplayer,ctrTaken,ctrDiscarded);//~0403I~//~0406R~//~vakqR~
        UA.UADL.postDelayedAutoTakeKan(false/*PswServer*/,Pplayer,ctrTaken,ctrDiscarded,true/*PswTimeout for KAN_TAKE/KAN_ADD*/);//~vakqI~
      }                                                            //~vakLI~
        return rc;                                                 //~0403I~
    }                                                              //~0403I~
	//*************************************************************************//~va60I~
	//*from UADelayed delayedRinshanTakable                        //~vaa5I~
	//*************************************************************************//~vaa5I~
    public void timeoutRinshanTakable(int Pplayer)                 //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("UAKan.timeoutRinshanTakable player="+Pplayer+",swTrainingMode="+AG.swTrainingMode);//~va60I~//~vaaVR~
        if (AG.aRoundStat.timeoutRinshanTakable(Pplayer))    //issued chankan Ron//~va60R~
        {                                                          //~va60R~
            if (Dump.Y) Dump.println("UAKan.timeoutRinshanTakable issued chankan player="+Pplayer);//~va60R~
            return;                                                //~va60R~
        }                                                          //~va60R~
    	if (AG.swTrainingMode)                                     //~vaaVM~
        {                                                          //~vaaVM~
        	if (AG.aGC.getStatusPlayAlone()==GCM_RON)	//waiting human Ron button push)//~vaaVM~
            {                                                      //~vaaVM~
        		if (Dump.Y) Dump.println("UAkan.timeoutRinshanTakable@@@@ ignore RinshanTake by statusPlayAlone=GCM_RON");//~vaaVR~
            	return; //skip call delayedRinshanTakable()        //~vaaVM~
            }                                                      //~vaaVM~
//        	if (swManualRobot)                                     //~vaktR~
          	if (swManualRobot || Pplayer==PLAYER_YOU)              //~vaktR~
            	infoMsgTakeRinshan(Pplayer);                              //~vaktI~
        }                                                          //~vaaVM~
        else                                                       //~vaktI~
        	if (Pplayer==PLAYER_YOU)                                //~vaktI~
            	infoMsgTakeRinshan(Pplayer);                              //~vaktI~
//      UA.UAK.timeoutRinshanTakable(Pplayer);	//issue autotake timeout//~va60R~
        setTimeout(true/*timeout*/,true/*swServer*/,Pplayer);	//autotake timeout//~va60I~
    }                                                              //~va60I~
	//*************************************************************************//~vaktI~
//  private void infoMsgTakeRinshan(int Pplayer)                              //~vaktI~//~vakDR~
//  public  void infoMsgTakeRinshan(int Pplayer)                   //~vakDI~//~vakJR~
    public  static void infoMsgTakeRinshan(int Pplayer)            //~vakJI~
    {                                                              //~vaktI~
    	int errmsgid= R.string.AE_TakeKanAdditional;                //~vaktI~
		GC.actionError(0,Pplayer,errmsgid);                        //~vaktI~
    }                                                              //~vaktI~
	//*************************************************************************//~9627I~
    public boolean isActiveTakeKanTimeout(int Pplayer,int PctrTakenAll,int PctrDiscarded)//~9627R~//~0406R~
    {                                                              //~9627I~
        if (Dump.Y) Dump.println("UAKan.isActiveTakeKanTimeout player="+Pplayer+",currentActionID="+AG.aUserAction.currentActionID+",PctrTakenAll="+PctrTakenAll+",PLS.ctrTakenAll="+PLS.ctrTakenAll);//~9627I~//~vakmR~
        if (Dump.Y) Dump.println("UAKan.isActiveTakeKanTimeout PctrDiscarded="+PctrDiscarded+",PLS.ctrDiscardedAll="+PLS.ctrDiscardedAll);//~9627I~//~0406R~//~vakmR~
//      boolean rc=(Pplayer==PLS.getCurrentPlayer() && PctrTakenAll==PLS.ctrTakenAll && AG.aUserAction.currentActionID==GCM_KAN//~9627I~//~0406R~
//      			&& PctrKan==AG.aTiles.ctrKan);                  //~9627I~//~0406R~
        boolean rc=(Pplayer==PLS.getCurrentPlayer() && PctrTakenAll==PLS.ctrTakenAll && PctrDiscarded==PLS.ctrDiscardedAll);//~0406I~
        if (Dump.Y) Dump.println("UAKan.isActiveTakeKanTimeout rc="+rc);//~9627I~
        return rc;                                                 //~9627I~
    }                                                              //~9627I~
	//*************************************************************************//~9623I~
    public void autoTakeKanTimeout(int Pplayer,int PctrTakenAll,int PctrKan)//~9623R~//~9627R~
    {                                                              //~9623I~
        if (Dump.Y) Dump.println("UAKan.autoTakeKanTimeout player="+Pplayer+",currentActionID="+AG.aUserAction.currentActionID+",PctrTakenAll="+PctrTakenAll+",ctrTakenAll="+PLS.ctrTakenAll);//~9623R~//~9B23R~
        if (Dump.Y) Dump.println("UAKan.autoTakeKanTimeout PctrKan="+PctrKan+",ctrKan="+AG.aTiles.ctrKan);//~9623I~//~9B23R~
//        if (Pplayer==PLS.getCurrentPlayer() && PctrTakenAll==PLS.ctrTakenAll && AG.aUserAction.currentActionID==GCM_KAN//~9623R~//~9627R~
//        && PctrKan==AG.aTiles.ctrKan)                              //~9623I~//~9627R~
//      	AG.aGC.sendMsg(GCM_TAKE,PLAYER_YOU);	//simulate take button//~9623I~//~9627R~
//        if (chk4KanDrawn(true/*PswAtCall*/,Pplayer))               //~vakmR~//~vakwR~//~vakHR~
//        {                                                          //~vakmR~//~vakwR~//~vakHR~
//            if (AG.aAccounts.isDummyPlayer(Pplayer))               //~vakwR~//~vakHR~
//                Robot.autoTakeTimeoutKan(Pplayer);                 //~vakwR~//~vakHR~
//            if (Dump.Y) Dump.println("UAKan.autoTakeKanTimeout @@@@return by Drawn by 4K");//~vakmR~//~vakwR~//~vakHR~
//            return;                                                //~vakmR~//~vakwR~//~vakHR~
//        }                                                          //~vakmR~//~vakwR~//~vakHR~
		if (Pplayer==PLAYER_YOU)                                   //~9627I~
        	AG.aGC.sendMsg(GCM_TAKE,Pplayer);		//simulate take button//~9627I~
        else                                                       //~9B23I~
        {                                                          //~va60R~
          if (AG.aAccounts.isDummyPlayer(Pplayer))                 //~va60R~
           	Robot.autoTakeTimeoutKan(Pplayer);                     //~va60R~
          else                                                     //~va60R~
			UA.UADL.sendMsgEmulatedToClient(GCM_TAKE,Pplayer);	//simulate take button//~9B23I~
        }                                                          //~va60R~
    }                                                              //~9623I~
	//*************************************************************************//~vakmI~
	//*from UADelayed.postDelayedAuotoTakeKan if timeoutAutoTake=0 //~vakmI~
	//*************************************************************************//~vakmI~
    public boolean autoTakeKanTimeout_0(int Pplayer,int PctrTaken,int PctrDiscarded)//~vakmI~
    {                                                              //~vakmI~
        if (Dump.Y) Dump.println("UAKan.autoTakeKanTimeout_0 player="+Pplayer+",PctrTaken="+PctrTaken+",ctrDiscarded="+PctrDiscarded);//~vakmI~
        boolean rc=chk4KanDrawn(true/*PswAtCall*/,Pplayer);        //~vakmR~
        return rc;                                                 //~vakmI~
    }                                                              //~vakmI~
	//*************************************************************************//~vakHI~
	//*from UADelayed.postDelayedAuotoTakeKan before delay autoTakeKan timeout//~vakHI~
	//*************************************************************************//~vakHI~
    public boolean chk4KanDrawnBeforeAutoTakeKan(int Pplayer,int PctrTaken,int PctrDiscarded)//~vakHI~
    {                                                              //~vakHI~
        if (Dump.Y) Dump.println("UAKan.chk4KanDrawnBeforeAutoTakeKan player="+Pplayer+",PctrTaken="+PctrTaken+",ctrDiscarded="+PctrDiscarded);//~vakHR~
        boolean rc=chk4KanDrawn(true/*PswAtCall*/,Pplayer);        //~vakHI~
        return rc;                                                 //~vakHI~
    }                                                              //~vakHI~
//    //*************************************************************************//~vakyI~//~vakHR~
//    //*from UADelayed.postDelayedAuotoTakeKan if timeoutAutoTake=0 //~vakyI~//~vakHR~
//    //*NOT Used                                                  //~vakHI~
//    //*************************************************************************//~vakyI~//~vakHR~
//    public boolean chk4KanDrawnWithoutOpenDlg(int Pplayer,int PctrTaken,int PctrDiscarded)//~vakyI~//~vakHR~
//    {                                                              //~vakyI~//~vakHR~
//        if (Dump.Y) Dump.println("UAKan.chk4KanDrawnWithoutOpenDlg player="+Pplayer+",PctrTaken="+PctrTaken+",ctrDiscarded="+PctrDiscarded);//~vakyI~//~vakHR~
//        swSkipOpenDlg=true;                                //~vakyI~//~vakHR~
//        boolean rc=chk4KanDrawn(true/*PswAtCall*/,Pplayer);        //~vakyI~//~vakHR~
//        swSkipOpenDlg=false;                                //~vakyI~//~vakHR~
//        if (Dump.Y) Dump.println("UAKan.chk4KanDrawnWithoutOpenDlg rc="+rc);//~vakyI~//~vakHR~
//        return rc;                                                 //~vakyI~//~vakHR~
//    }                                                              //~vakyI~//~vakHR~
	//*************************************************************************//~9627I~
	//*on Client,by GCM_WAITOFF at waitingAutoTakeKan              //~9627I~
	//*************************************************************************//~9627I~
    public boolean autoTakeKanTimeoutClient(int Pplayer)           //~9627R~
    {                                                              //~9627I~
    	boolean rc=false;                                          //~9627I~
        if (Dump.Y) Dump.println("UATake.autoTakeKanTimeoutClient player="+Pplayer);//~9627I~
		if (Pplayer==PLAYER_YOU)                                   //~9627I~
        {                                                          //~9627I~
			AG.aGC.sendMsg(GCM_TAKE,Pplayer);	//simulate discard button//~9627I~
            rc=true;                                               //~9627I~
        }                                                          //~9627I~
        if (Dump.Y) Dump.println("UATake.autoTakeKanTimeoutClient rc="+rc);//~9627I~
        return rc;                                                 //~9627I~
    }                                                              //~9627I~
	//*************************************************************************//~0403I~
	//*On Server;Ankan/Chankan set ronable time,if exausted rinshan takable//~0403I~
	//*public from GC                                              //~vaaUI~
	//*************************************************************************//~0403I~
//  private void postDelayedTakableKan(int Pplayer,TileData Ptd)   //~0403I~//~vaaVR~//~vaaUR~
    public void postDelayedTakableKan(int Pplayer)                 //~vaaUI~
    {                                                              //~0403I~
        if (Dump.Y) Dump.println("UAKan.postDelayedTakableKan player="+Pplayer);//~vaaUR~
		UADL.postDelayedTakableKan(Pplayer);	//simulate take button//~0403R~
    }                                                              //~0403I~
	//*************************************************************************//~0403I~
    private void setTakable(boolean PswTakable,int Pplayer)        //~0403I~
    {                                                              //~0403I~
        if (Dump.Y) Dump.println("UAKan.setTakable swTakable="+PswTakable+",player="+Pplayer);//~0403R~
	    if (PswTakable)   //minkan or rontimeout of ankan/chankan  //~0403I~//~0404R~
        {                                                          //~0403I~
			stock.drawNextOneKan();		//mark wanpai              //~0403I~
            lockTaken(false); 	//allow rinshan take               //~0403I~
        	UADL.setRonable(false);    	//for dup ron,set delayed ron//~0404I~
			boolean swShadow=Pplayer!=PLAYER_YOU;                  //~0403I~
		    AG.aDiceBox.drawLightKanTakable(Pplayer,swShadow);	//green//~0403R~
        }                                                          //~0403I~
        else    //just declared ankan/chankan                     //~0403I~//~0404R~
        {                                                          //~0403I~
            lockTaken(true);                                       //~0403I~
		    AG.aDiceBox.drawLightDiscard(Pplayer);	//orange       //~0403I~
        }                                                          //~0403I~
    }                                                              //~0403I~
	//*************************************************************************//~vakmI~
	//*at 4th kan called and after delay to take rinshan           //~vakmI~
	//*************************************************************************//~vakmI~
    private boolean chk4KanDrawn(boolean PswAtCall,int Pplayer)    //~vakmR~
    {                                                              //~vakmI~
        if (Dump.Y) Dump.println("UAKan.chk4KanDrawn player="+Pplayer+",Tiles.ctrKan="+AG.aTiles.ctrKan+",swSkipOpenDlg="+swSkipOpenDlg);//~vakmR~//~vakyR~
    	if (AG.aTiles.ctrKan!=4)                                   //~vakmI~
        {                                                          //~vakmI~
	        if (Dump.Y) Dump.println("UAKan.chk4KanDrawn return false by ctrKan!=4");//~vakmR~
        	return false;                                          //~vakmI~
        }                                                          //~vakmI~
    	int ctrKan=PLS.getCtrKan(Pplayer);                         //~vakmM~
        if (Dump.Y) Dump.println("UAKan.chk4KanDrawn player="+Pplayer+",Player-ctrKan="+ctrKan);//~vakmR~
        if (ctrKan==4)                                             //~vakmI~
        {                                                          //~vakmI~
	        if (Dump.Y) Dump.println("UAKan.chk4KanDrawn return 4kan yakuman");//~vakmR~
        	return false;                                          //~vakmI~
        }                                                          //~vakmI~
    	boolean rc=false;	//not DrawnHW                          //~vakmM~
 		if ( PswAtCall &&  swDrawHW4KanAtCall                      //~vakmI~
        ||  !PswAtCall && !swDrawHW4KanAtCall)                     //~vakmI~
        {                                                          //~vakmI~
          if (!swSkipOpenDlg)                                      //~vakyI~
          {                                                        //~vakyI~
            sw4KanDrawn=true;                                      //~vakJM~
            sendToClient4KanDrawn(Pplayer);                        //~vakJM~
//  	    DrawnReqDlgHW.newInstance(EGDR_4KAN).show();           //~vakmR~//~vakwR~//~vakyR~
            DrawnReqDlgHW.newInstance(EGDR_4KAN,true/*stopAuto at First*/).show();//~vakwR~//~vakyR~
          }                                                        //~vakyI~
            rc=true;                                               //~vakmI~
        }                                                          //~vakmI~
        if (Dump.Y) Dump.println("UAKan.chk4KanDrawn rc="+rc+",player="+Pplayer+",ctrKan="+ctrKan);//~vakmR~
        return rc;                                                 //~vakmI~
    }                                                              //~vakmI~
	//*************************************************************************//~vakJI~
    private void sendToClient4KanDrawn(int Pplayer)                //~vakJI~
    {                                                              //~vakJI~
        if (Dump.Y) Dump.println("UAKan.sendToClientchk4KanDrawn player="+Pplayer+",tds4KanDrawn="+TileData.toString(tds4KanDrawn));//~vakJR~
    	if (tds4KanDrawn!=null)	//on server                        //~vakJR~
        {                                                          //~vakJI~
       		String msgDataToClient=makeMsgDataToClient(Pplayer,tds4KanDrawn,PAIRCTR_KAN);//~vakJI~
		    UA.sendToClient(true/*swSendAll*/,false/*swRobot*/,GCM_KAN,Pplayer,msgDataToClient);//~vakJI~
	    	tds4KanDrawn=null;                                     //~vakJI~
       	}                                                          //~vakJI~
        if (Dump.Y) Dump.println("UAKan.sendToClientchk4KanDrawn exit player="+Pplayer);//~vakJI~
    }                                                              //~vakJI~
}//class                                                           //~v@@@R~
