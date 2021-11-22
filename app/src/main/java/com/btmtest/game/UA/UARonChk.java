//*CID://+va95R~: update#= 756;                                    //~va95R~
//**********************************************************************//~v101I~
//2021/06/13 va95 (Bug)MakeParing misses NumSeq evaluation for 333 case(3-Same(3anko) and 3Seq(1peiko+)//~va95I~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/04/12 va83 (Bug)13/14 NoPair was not notified(Decided to Robot will not Ron by 13/14 NoPair)//~va83I~
//2021/04/12 va82 (Bug)issue "not ronnable" for 1st take Ron(tenho/chiiho) request if multiple pillow candidate exist//~va82I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/11/03 va27 Tenpai chk at Reach                              //~va27I~
//2020/11/01 va22 (BUG)is13NoPair is checking after drop pillow    //~va22I~
//2020/10/20 va20 use Junit for UARonchk                           //~va20I~
//2020/10/19 va1a drop ronchk option,1han constraint only          //~va1aI~
//2020/10/10 va14 (BUG)7pairwith Kan is err even optio allow it    //~va14I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Point;

import com.btmtest.TestOption;
import com.btmtest.dialog.CompReqDlg;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UA.Pair.*;
import static com.btmtest.game.gv.Pieces.*;
import static com.btmtest.StaticVars.AG;                           //~9C11I~
//****************************************************             //~9C11I~
public class UARonChk                                                //~v@@@R~//~9C11R~
{                                                                  //~0914I~
    protected Point posPillow;//~v@@@R~                            //~va11R~
//  private int[][] dupCtrNum=new int[PIECE_NUMBERTYPECTR][PIECE_NUMBERCTR];	//3*9//~9C11I~//~9C12R~
//  private int[] dupCtrNotNum=new int[PIECE_NOTNUM_CTR];                         //7//~9C11I~//~9C12R~
    public int[][] dupCtr=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR];	//4*9//~9C12I~//~va11R~
    protected boolean sw7Pair4Pair;                                  //~9C11I~//~va11R~
    protected boolean sw14NoPair,sw13NoPair;                         //~9C11R~//~va11R~
    protected boolean swIs14NoPair;                                //~va11I~
//  protected boolean swCheckRonable;                                //~0205I~//~va11R~//~va1aR~
    protected int ctrRecursive;                                      //~9C12I~//~va11R~
    protected int ctrTileAll;                                        //~9C12I~//~va11R~
    protected int ctrPair;                                         //~va11I~
    public  int player;                                            //~va11I~
    private boolean sw13_14NoPair,sw1stTake;                       //~va11R~
    public boolean swAllInHand;                           //~va11I~
    private boolean swNoPairPlayAlone;                             //~va83I~
//*************************                                        //~v@@@I~
	public UARonChk()                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~va1aR~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UARonChk Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~9C11R~
        init();                                                    //~v@@@I~//~va14R~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	protected void init()                                             //~v@@@I~//~va11R~//~va14R~
    {                                                              //~v@@@I~
        sw7Pair4Pair= RuleSettingYaku.is7Pair4Pair();               //~9C11I~
//      sw13NoPair= RuleSettingYaku.is13NoPair();                  //~9C11I~//~va11R~
//      sw14NoPair= RuleSettingYaku.is14NoPair();                  //~9C11I~//~va11R~
        sw13NoPair= RuleSettingYaku.isYakuman13NoPair();           //~va11I~
        sw14NoPair= RuleSettingYaku.isYakuman14NoPair();           //~va11I~
//      swCheckRonable= RuleSettingOperation.isCheckRonable();     //~0205R~//~va1aR~
    }                                                              //~v@@@I~
	//*************************************************************************//~va11I~
//  protected boolean isAllInHand()                                //~va11R~//~va60R~
    protected boolean isAllInHand(int Pplayer)                     //~va60I~
    {                                                              //~va11I~
//  	boolean rc=ctrPair==0;                                     //~va11R~
//  	boolean rc=AG.aPlayers.isClosedHand(player);               //~va11I~//~va60R~
    	boolean rc=AG.aPlayers.isClosedHand(Pplayer);              //~va60I~
        if (Dump.Y) Dump.println("UARonChk.isAllInHand rc="+rc+",ctrPair="+ctrPair+",player="+Pplayer);//~va11R~//~va60R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
//    //*************************************************************************//~9C11I~//~va60R~
//    //*No User                                                   //~va60R~
//    //*************************************************************************//~9C11I~//~va60R~
//    public boolean chkComplete(int Pplayer)                     //~9C11R~//~va60R~
//    {                                                              //~9C11I~//~va60R~
//        boolean rc;                                                //~9C11I~//~va60R~
//        player=Pplayer;                                            //~va11I~//~va60R~
//        swAllInHand=isAllInHand();                                 //~va11I~//~va60R~
//        ctrPair=AG.aPlayers.getCtrPair(Pplayer);       //including Ron tile//~va11I~//~va60R~
//        if (Dump.Y) Dump.println("UARonChk.chkComplete player="+Pplayer+",ctrPair="+ctrPair);//~9C11I~//~0205R~//~va11R~//~va1aR~//~va60R~
//        TileData[] tds=AG.aPlayers.getHands(Pplayer);       //including Ron tile//~9C11R~//~va60R~
//        TileData tdRon=null;                                       //~9C11I~//~va60R~
//        if (!Tiles.isTakenStatus(tds.length))                      //~9C11I~//~va60R~
//            tdRon=AG.aPlayers.getTileCompleteSelectInfoRon();              //~9C11I~//~va60R~
////        if ((TestOption.option2 & TestOption.TO2_RON_TEST)!=0) //TODO//~9C12M~//~va11R~//~va60R~
////        {                                                          //~9C12M~//~va11R~//~va60R~
//////          ronTest();                                             //~9C12M~//~9C13R~//~va11R~//~va60R~
////            return true;                                           //~9C12M~//~va11R~//~va60R~
////        }                                                          //~9C12M~//~va11R~//~va60R~
////      if (!swCheckRonable)                                       //~0205I~//~va1aR~//~va60R~
////          return true;                                           //~0205I~//~va1aR~//~va60R~
//        sw1stTake=CompReqDlg.chk1stTake();                         //~va11I~//~va60R~
//        sortTiles(tds,tdRon);                                      //~9C11R~//~va60R~
//        if ((TestOption.option2 & TestOption.TO2_RON_TEST)!=0) //TODO//~va11I~//~va60R~
//        {                                                          //~va11I~//~va60R~
//            int[][] testHand=UARonValue.getTestHandRonChk();   //by testcase//~va11I~//~va60R~
//            if (testHand!=null)                                    //~va11I~//~va60R~
//                dupCtr=testHand;                                   //~va11I~//~va60R~
//        }                                                          //~va11I~//~va60R~
//        rc=chkCompleteSub();                                     //~va60R~
//        return rc;//~9C12I~                                      //~va60R~
//    }                                                              //~9C12I~//~va60R~
	//*************************************************************************//~9C12I~
	//*chk 7pair/13all/13nopair and standard pairing in allin hand //+va95I~
	//*************************************************************************//+va95I~
//  private boolean chkCompleteSub()                               //~va20R~
//  protected boolean chkCompleteSub()                             //~va20I~//~va83R~
//  private   boolean chkCompleteSub()                             //~va83I~//~va91R~
    protected boolean chkCompleteSub()  //protected for ITUAxxx    //~va91I~
    {                                                              //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.chkCompleteSub entry"); //~va95I~
        boolean rc=isStandardPairing();                                     //~9C11I~//~9C12R~
        sw13_14NoPair=false;                                       //~va11I~
        if (!rc)                                                   //~9C11I~
        {                                                          //~9C11I~
        	if (is7Pair())                                         //~9C11I~
        		rc=true;                                           //~9C11I~
            else                                                   //~9C11I~
        	if (is13All())                                         //~9C11I~
        		rc=true;                                           //~9C11I~
            else                                                   //~9C11I~
	        if (is13NoPair())                                      //~9C11I~
            {                                                      //~va11I~
            	sw13_14NoPair=true;                                //~va11I~
        		rc=true;                                           //~9C11I~
            }                                                      //~va11I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkCompleteSub rc="+rc+",13_14NoPair="+sw13_14NoPair);     //~9C11I~//~9C12R~//~va11R~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~va60I~
    public int[][] setDupCtr(TileData[] Ptds,TileData PtdRon)      //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("UARonChk.setDupCtr");            //~va60I~
	    sortTiles(Ptds,PtdRon);                                    //~va60I~
        return dupCtr;                                             //~va60I~
    }                                                              //~va60I~
	//*************************************************************************//~9C11I~
	//*get ctr of each suit                                        //~9C11I~
	//*************************************************************************//~9C11I~
    protected void sortTiles(TileData[] Ptds,TileData PtdRon)        //~9C11R~//~va11R~
    {                                                              //~9C11I~
    	int type,num;                                              //~9C11I~
    //*******************************                              //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.sortTiles tds="+TileData.toString(Ptds)+",tdRonRiver="+Utils.toString(PtdRon));//~9C11R~//~va83R~
        for (int ii=0;ii<PIECE_TYPECTR_ALL;ii++)                   //~9C12I~
            Arrays.fill(dupCtr[ii],0);                             //~9C12I~
        ctrTileAll=Ptds.length;                                    //~9C12I~
        for (TileData td : Ptds)
        {                                                          //~9C11I~
            type = td.type;                                        //~9C11R~
            num = td.number;                                       //~9C11R~
            dupCtr[type][num]++;                                //~9C12I~
        }                                                          //~9C11I~
        if (PtdRon!=null)                                           //~9C11I~
        {                                                          //~9C11I~
            type = PtdRon.type;                                     //~9C11I~
            num = PtdRon.number;                                    //~9C11I~
            dupCtr[type][num]++;                                //~9C12I~
        }                                                          //~9C11I~
//      if (Dump.Y) Dump.println("UARonChk.sortTiles dupctrNum="+ Utils.toString(dupCtrNum));//~9C11I~//~9C12R~
//      if (Dump.Y) Dump.println("UARonChk.sortTiles dupctrNotNum="+Arrays.toString(dupCtrNotNum));//~9C11I~//~9C12R~
        if (Dump.Y) Dump.println("UARonChk.sortTiles dupctrNum="+ Utils.toString(dupCtr));//~9C12I~
    }                                                              //~9C11I~
	//*************************************************************************//~va27I~
	//from UAReachChk                                              //~va27I~
	//*************************************************************************//~va27I~
    public boolean isStandardPairing(int [][] PdupCtr)             //~va27R~
    {                                                              //~va27I~
        if (Dump.Y) Dump.println("UARonChk.isStandardPairing dupCtr="+ Utils.toString(PdupCtr));//~va27I~
        int[][] dupCtrSave=dupCtr;                                 //~va27I~
        dupCtr=PdupCtr;                                            //~va27I~
    	boolean rc=isStandardPairing();                            //~va27I~
        dupCtr=dupCtrSave;                                         //~va27I~
        return rc;
    }                                                              //~va27I~
	//*************************************************************************//~9C11I~
	//*search 2pair then chk pairing                               //~9C11I~
	//*************************************************************************//~9C11I~
    private boolean isStandardPairing()                            //~9C11I~
    {                                                              //~9C11I~
    	boolean rc=false;                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.isStandardPairing");      //~9C11I~//~va60R~
        posPillow=new Point(0,0);                                  //~9C11I~
        for(;;)                                                    //~9C11I~
        {                                                          //~9C11I~
        	Point p=selectPillow();	//type and number              //~9C11I~
            if (p==null)                                           //~9C11I~
            	break;                                             //~9C11I~
            dropPillow(p);                                         //~9C11I~
            if (chkPairing())                                       //~9C11I~//~va11R~
            {                                                      //~9C11I~
            	rc=true;                                           //~9C11I~
                break;                                             //~9C11I~
            }                                                      //~9C11I~
            restorePillow(p);                                      //~9C11I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.isStandardPairing rc="+rc);//~9C11I~
        return rc;
    }                                                              //~9C11I~
	//*************************************************************************//~9C11I~
    protected Point selectPillow()                                   //~9C11I~//~va11R~
    {                                                              //~9C11I~
    	Point pos=null;                                            //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.selectPillow posPillow="+posPillow.toString());//~9C11I~
        if (Dump.Y) Dump.println("UARonChk.selectPillow dupCtr="+Utils.toString(dupCtr));//~9C12I~
        int type=posPillow.x;                                      //~9C11I~
        int num=posPillow.y;                                       //~9C11I~
        for (int jj=type;jj<PIECE_TYPECTR_ALL;jj++)                //~9C12I~
        {                                                          //~9C12I~
            for (int ii=num;ii<PIECE_NUMBERCTR;ii++)                         //7//~9C12I~
                if (dupCtr[jj][ii]>=2)                          //~9C12I~
                {                                                  //~9C12I~
                    pos=new Point(jj,ii);                          //~9C12R~
                    if (ii+1==PIECE_NUMBERCTR)                     //~9C12I~
                        posPillow=new Point(jj+1,0);               //~9C12R~
                    else                                           //~9C12I~
                        posPillow=new Point(jj,ii+1);              //~9C12R~
                    break;                                         //~9C12I~
                }                                                  //~9C12I~
            if (pos!=null)                                         //~9C12I~
            	break;                                             //~9C12I~
            num=0;                                                 //~9C12I~
        }                                                          //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.selectPillow pos="+Utils.toString(pos)+",posPillow="+posPillow.toString());//~9C11I~
        return pos;
    }                                                              //~9C11I~
	//*************************************************************************//~9C11I~
    protected void dropPillow(Point Ppos)                            //~9C11I~//~va11R~
    {                                                              //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.dropPillow pos="+Ppos.toString());//~9C11I~
        int type=Ppos.x;                                      //~9C11I~//~9C12R~
        int num=Ppos.y;                                       //~9C11I~//~9C12R~
        dupCtr[type][num]-=2;                                   //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.dropPillow Num="+Arrays.toString(dupCtr[type]));//~9C12I~
    }                                                              //~9C11I~
	//*************************************************************************//~9C11I~
    protected void restorePillow(Point Ppos)                         //~9C11I~//~va11R~
    {                                                              //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.restorePillow pos="+Ppos.toString());//~9C11I~
        int type=Ppos.x;                                      //~9C11I~//~9C12R~
        int num=Ppos.y;                                       //~9C11I~//~9C12R~
    	dupCtr[type][num]+=2;                                   //~9C12I~
       	if (Dump.Y) Dump.println("UARonChk.restorePillow Num="+Arrays.toString(dupCtr[type]));//~9C12I~
    }                                                              //~9C11I~
	//*************************************************************************//~va11I~
	//*from UARonValue                                             //~va11I~
	//*************************************************************************//~va11I~
    protected boolean makePairing(UARonData PuardTop)              //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonChk.makePairing start");    //~va11R~
//      Pairs pairsTop=PuardTop.pairsTop;                          //~va11R~
        UAPair pairsTop=PuardTop.UAP;                              //~va11I~
        int[][] dupCtrClone=Utils.cloneArray2(dupCtr);             //~va11I~
        int[][] dupCtrSave=dupCtr;                                 //~va11I~
        dupCtr=dupCtrClone;                                        //~va11I~
        boolean rc=makePairingNotNum(pairsTop);                    //~va11R~
        if (rc)                                                    //~va11R~
	        rc=makePairingNum(pairsTop);                           //~va11R~
        dupCtr=dupCtrSave;                                         //~va11I~
//      if (rc)                                                    //~va11R~
//      	pairsTop.makeNumPatternHandAll();                      //~va11R~
        if (Dump.Y) Dump.println("UARonChk.makePairing rc="+rc+",pairing="+pairsTop.toStringPairAll());//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
	//*from UARonValue.chkRonValueSub to chk tenho/chiiho/13orphan/13nopair/7pair for Human player                            //~va11I~//~va83R~//+va95R~
	//*************************************************************************//~va11I~
//  public boolean chkRonnable()                                   //~va11R~//~va83R~
    protected boolean chkRonnable()                                //~va83I~
    {                                                              //~va11I~
        sw1stTake=CompReqDlg.chk1stTake();                         //~va11I~
        if (Dump.Y) Dump.println("UARonChk.chkRonnable sw1stTake="+sw1stTake);//~va11R~//~va14R~
        int[][] dupCtrClone=Utils.cloneArray2(dupCtr);            //~va11R~
        int[][] dupCtrSave=dupCtr;                                         //~va11I~
        dupCtr=dupCtrClone;                                        //~va11I~
	    boolean rc=chkCompleteSub();                               //~va11I~
        dupCtr=dupCtrSave;                                         //~va11I~
        if (Dump.Y) Dump.println("UARonChk.chkRonnable rc="+rc+",PdupCtr="+Utils.toString(dupCtr));//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~v@@@I~
    public boolean chkPairing()                                    //~9C11R~//~va11R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UARonChk.chkPairing");             //~9C11R~//~va11R~
        boolean rc=chkPairingNotNum() && chkPairingNum();            //~9C11I~//~va11R~
        if (Dump.Y) Dump.println("UARonChk.chkPairing rc="+rc);     //~9C11I~//~va11R~
        return rc;                                                 //~9C11I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9C11I~
    private boolean chkPairingNotNum()                              //~9C11I~//~va11R~
    {                                                              //~9C11I~
    	boolean rc=true;                                           //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkPairingNotNum NotNum="+Arrays.toString(dupCtr[TT_JI]));//~9C11I~//~va11R~
        int[] dupctr=dupCtr[TT_JI];                          //~9C12I~
        for (int ii=0;ii<PIECE_NOTNUM_CTR;ii++)                         //7//~9C11I~
        {                                                          //~9C11I~
//          int num=dupCtrNotNum[ii];                              //~9C11I~//~9C12R~
            int num=dupctr[ii];                                    //~9C12I~
            if (num!=0 && num!=PAIRCTR)                            //~9C11I~
            {                                                      //~9C11I~
            	rc=false;                                          //~9C11I~
            	break;                                             //~9C11I~
            }                                                      //~9C11I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkPairingNotNum rc="+rc);//~9C11I~//~va11R~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~va11I~
	//*add to UAPair                                               //~va11I~
	//*************************************************************************//~va11I~
//  private boolean makePairingNotNum(Pairs Puardp)                //~va11R~
    private boolean makePairingNotNum(UAPair Puardp)               //~va11I~
    {                                                              //~va11I~
    	boolean rc=true;                                           //~va11I~
        if (Dump.Y) Dump.println("UARonChk.makePairingNotNum NotNum="+Arrays.toString(dupCtr[TT_JI]));//~va11I~
        int[] dupctr=dupCtr[TT_JI]; //in Hand                      //~va11R~
        for (int ii=0;ii<PIECE_NOTNUM_CTR;ii++)     //7            //~va11I~
        {                                                          //~va11I~
            int num=dupctr[ii];                                    //~va11I~
            if (num!=0 && num!=PAIRCTR)                            //~va11I~
            {                                                      //~va11I~
            	rc=false;                                          //~va11I~
//  	        if (Dump.Y) Dump.println("UARonChk.makePairingNotNum @@@@err dupCtr="+num);//~va11I~//~va60R~
            	break;                                             //~va11I~
            }                                                      //~va11I~
            if (num!=0)                                            //~va11I~
	            Puardp.add(PT_NOTNUM,TT_JI/*type*/,ii/*number*/,true/*hand*/);//~va11R~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonChk.makePairingNotNum rc="+rc);//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~9C11I~
    private boolean chkPairingNum()                                 //~9C11I~//~va11R~
    {                                                              //~9C11I~
    	boolean rc=true;                                           //~9C11I~
//      if (Dump.Y) Dump.println("UARonChk.chkPairingNum Num="+Utils.toString(dupCtr));//~9C11I~//~9C12R~//~va11R~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                     //~9C11I~
        {                                                          //~9C11I~
		    if (!chkPairingNum(dupCtr[ii],0/*pos*/,ii))                      //~9C11I~//~9C12R~//~va11R~
            {                                                      //~9C11I~
            	rc=false;                                          //~9C11I~
                break;                                             //~9C11I~
            }                                                      //~9C11I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkPairingNum rc="+rc);  //~9C11I~//~va11R~
        return rc;
    }                                                              //~9C11I~
    //*************************************************************************//~9C12I~
    private boolean chkPairingNum(int[] PctrNum,int Ppos,int Ptype) //~9C12R~//~va11R~
    {                                                              //~9C12I~
        ctrRecursive++;                                            //~9C12I~
//      if (Dump.Y) Dump.println("UARonChk.chkPairingNum type="+Ptype+",ctrRecursive="+ctrRecursive+",Num="+Arrays.toString(PctrNum));//~9C12R~//~va11R~
        boolean rc=false;                                          //~9C12I~
        int[] dupCtrDrop=PctrNum;                                  //~9C12R~
        for (int ii=Ppos;ii<PIECE_NUMBERCTR;ii++)                         //7//~9C12I~
        {                                                          //~9C12I~
            if (dupCtrDrop[ii]>=3)                                 //~9C12I~
            {                                                      //~9C12I~
                dupCtrDrop[ii]-=3;                                 //~9C12I~
                if (chkPairingNum(dupCtrDrop,ii,Ptype))             //~9C12R~//~va11R~
                {                                                  //~9C12I~
                    rc=true;                                       //~9C12I~
                    break;                                         //~9C12I~
                }                                                  //~9C12I~
                dupCtrDrop[ii]+=3;                                 //~9C12I~
            }                                                      //~9C12I~
            if (rc)                                                //~9C12I~
            	break;                                             //~9C12I~
        }                                                          //~9C12I~
        if (!rc)                                                   //~9C12I~
			rc=chkPairingSeq(dupCtrDrop,Ptype);                     //~9C12R~//~va11R~
        if (Dump.Y) Dump.println("UARonChk.chkPairingNum rc="+rc+",ctrRecursive="+ctrRecursive);//~9C12I~//~va11R~
        ctrRecursive--;                                            //~9C12I~
        return rc;                                                 //~9C12I~
    }                                                              //~9C12I~
	//*************************************************************************//~9C11I~
    private boolean chkPairingSeq(int[] PctrNum,int Ptype)                    //~9C11I~//~9C12R~//~va11R~
    {                                                              //~9C11I~
//      if (Dump.Y) Dump.println("UARonChk.chkPairingSeq type="+Ptype+",Num="+Arrays.toString(PctrNum));//~9C11I~//~9C12R~//~va11R~
    	boolean rc=true;                                           //~9C11I~
    	int[] dupCtrDrop=PctrNum.clone();                       //~9C11I~
        for (int ii=0;ii<PIECE_NUMBERCTR-2;)                         //7//~9C11I~
        {                                                          //~9C11I~
            if (dupCtrDrop[ii]!=0 && dupCtrDrop[ii+1]!=0 && dupCtrDrop[ii+2]!=0)//~9C11I~//~9C12R~
            {                                                      //~9C11I~
	            dupCtrDrop[ii]--;                                 //~9C11I~
	            dupCtrDrop[ii+1]--;                               //~9C11I~
	            dupCtrDrop[ii+2]--;                               //~9C11I~
            }                                                      //~9C11I~
            else                                                   //~9C11I~
            	ii++;                                              //~9C11I~
        }                                                          //~9C11I~
        for (int ii=0;ii<PIECE_NUMBERCTR;ii++)                         //7//~9C11I~
        {                                                          //~9C11I~
            if (dupCtrDrop[ii]!=0)                                    //~9C11I~//~9C12R~
            {                                                      //~9C11I~
	            rc=false;                                          //~9C11I~
	            break;                                             //~9C11I~
            }                                                      //~9C11I~
        }                                                          //~9C11I~
//      if (Dump.Y) Dump.println("UARonChk.chkPairingSeq rc="+rc+",PctrNum="+Arrays.toString(PctrNum)+",dupCtrDrop="+Arrays.toString(dupCtrDrop));//~9C11I~//~9C12R~//~va11R~
        if (Dump.Y) Dump.println("UARonChk.chkPairingSeq rc="+rc); //~va11I~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~va11I~
//  private boolean makePairingNum(Pairs Puardp)                   //~va11R~
    private boolean makePairingNum(UAPair Puardp)                  //~va11I~
    {                                                              //~va11I~
    	boolean rc=true;                                           //~va11I~
        if (Dump.Y) Dump.println("UARonChk.makePairingNum");       //~va11R~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~va11I~
        {                                                          //~va11I~
//       	if (!makePairingNum(Puardp,dupCtr[ii],ii))	//to be add seq pair//~va11R~
         	if (!makePairingNum(Puardp,null/*parent*/,dupCtr[ii],ii))	//to be add seq pair//~va11I~
            {                                                      //~va11I~
	            rc=false;                                          //~va11I~
    	        break;                                             //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonChk.makePairingNum rc="+rc);//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
//  private boolean makePairingNum(Pairs Puardp,int[] PctrNum,int Ptype)//~va11R~
    private boolean makePairingNum(UAPair Puardp,Pair Pparent,int[] PctrNum,int Ptype)//~va11I~
    {                                                              //~va11I~
//      if (Dump.Y) Dump.println("UARonChk.makePairingNum type="+Ptype+",uardp="+Puardp.toString()+",PctrNum="+Arrays.toString(PctrNum));//~va11R~
        if (Dump.Y) Dump.println("UARonChk.makePairingNum Parent="+Utils.toString(Pparent));//~va11R~
        boolean rc=true;                                           //~va11R~
        for (int ii=0;ii<PIECE_NUMBERCTR;ii++)                         //7//~va11I~
        {                                                          //~va11I~
	        boolean rcSame,rcSeq;                                  //~va11I~
            if (PctrNum[ii]==0)                                    //~va11I~
            	continue;                                          //~va11I~
          if (false)                                               //~va95I~
          {                                                        //~va95I~
//          int[] numForSeq=PctrNum.clone();                       //~va11R~
            if (PctrNum[ii]>=3)                                    //~va11I~
            {                                                      //~va11I~
//          	rcSame=makePairingNumSame(Puardp,PctrNum,ii,Ptype);//~va11R~
            	rcSame=makePairingNumSame(Puardp,Pparent,PctrNum,ii,Ptype);//~va11I~
            }                                                      //~va11I~
            else                                                   //~va11I~
            	rcSame=false;                                      //~va11R~
//          rcSeq=makePairingNumSeq(Puardp,numForSeq,ii/*pos*/,Ptype);//~va11R~
//          rcSeq=makePairingNumSeq(Puardp,Pparent,numForSeq,ii/*pos*/,Ptype);//~va11R~
            rcSeq=makePairingNumSeq(Puardp,Pparent,PctrNum,ii/*pos*/,Ptype);//~va11I~
          }	//if false                                             //~va95I~
          else                                                     //~va95I~
          {                                                        //~va95I~
            if (PctrNum[ii]>=3)                                    //~va95I~
            {                                                      //~va95I~
                int[] numForSeq=PctrNum.clone();                   //~va95M~
            	rcSame=makePairingNumSame(Puardp,Pparent,PctrNum,ii,Ptype);//~va95I~
		        if (Dump.Y) Dump.println("UARonChk.makePairingNum@@@@ ctr>=3 after Same rcSame="+rcSame+",type="+Ptype+",num="+ii+",uardp="+Puardp.toStringPairAll());//~va95I~
				rcSeq=makePairingNumSeq(Puardp,Pparent,numForSeq,ii/*pos*/,Ptype);//~va95I~
		        if (Dump.Y) Dump.println("UARonChk.makePairingNum@@@@ ctr>=3 after Seq rcSeq="+rcSeq+",type="+Ptype+",num="+ii+",uardp="+Puardp.toStringPairAll());//~va95I~
            }                                                      //~va95I~
            else                                                   //~va95I~
            {                                                      //~va95I~
            	rcSame=false;                                      //~va95I~
            	rcSeq=makePairingNumSeq(Puardp,Pparent,PctrNum,ii/*pos*/,Ptype);//~va95I~
		        if (Dump.Y) Dump.println("UARonChk.makePairingNum@@@@ ctr<3 after Seq rcSeq="+rcSeq+",type="+Ptype+",num="+ii+",uardp="+Puardp.toStringPairAll());//~va95I~
            }                                                      //~va95I~
          }//if true                                               //~va95I~
            if (!rcSame && !rcSeq)                                 //~va11I~
            {                                                      //~va11I~
		        if (Dump.Y) Dump.println("UARonChk.makePairingNum @@@@ rcSame and rcSeq is null");//~va11I~
                rc=false;                                          //~va11I~
            	break;                                             //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonChk.makePairingNum rc="+rc+",uardp="+Puardp.toStringPairAll());//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************************//~va11M~
//  private boolean makePairingNumSame(Pairs Puardp,int[] PctrNum,int Ppos,int Ptype)//~va11R~
    private boolean makePairingNumSame(UAPair Puardp,Pair Pparent,int[] PctrNum,int Ppos,int Ptype)//~va11I~
    {                                                              //~va11M~
        if (Dump.Y) Dump.println("UARonChk.makePairingNumSame type="+Ptype+",ctrNum="+Arrays.toString(PctrNum));//~va11M~
        boolean rc=false;                                          //~va11I~
        PctrNum[Ppos]-=3;                                          //~va11M~
    	int[] dupClone=PctrNum.clone();                            //~va11I~
        if (chkPairingNum(dupClone,0/*pos*/,Ptype))                //~va11R~
        {                                                          //~va11M~
//  		Pairs uardp=Puardp.add(PT_NUMSAME,Ptype,Ppos,true);    //~va11R~
    		Pair pair=Puardp.add(Pparent,PT_NUMSAME,Ptype,Ppos,true);//~va11I~
		    rc=makePairingNum(Puardp,pair,PctrNum,Ptype);          //~va11R~
        }                                                          //~va11M~
        else                                                       //~va11M~
	        PctrNum[Ppos]+=3;                                      //~va11M~
        if (Dump.Y) Dump.println("UARonChk.makePairingNumSame rc="+rc+",ctrNum="+Arrays.toString(PctrNum));//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11M~
	//*************************************************************************//~va11I~
//  private boolean makePairingNumSeq(Pairs Puardp,int[] PctrNum,int Ppos,int Ptype)//~va11R~
    private boolean makePairingNumSeq(UAPair Puardp,Pair Pparent,int[] PctrNum,int Ppos,int Ptype)//~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonChk.makePairingNumSeq type="+Ptype+",pos="+Ppos+",Num="+Arrays.toString(PctrNum));//~va11R~//~va95R~
        boolean rc=false;                                          //~va11I~
        if (Ppos+2<PIECE_NUMBERCTR                                 //~va11I~
        &&  PctrNum[Ppos]!=0 && PctrNum[Ppos+1]!=0 && PctrNum[Ppos+2]!=0)//~va11I~
        {                                                          //~va11I~
	    	PctrNum[Ppos]--; PctrNum[Ppos+1]--; PctrNum[Ppos+2]--; //~va11I~
	    	int[] dupClone=PctrNum.clone();                        //~va11I~
            if (chkPairingNum(dupClone,0/*pos*/,Ptype))            //~va11R~
            {                                                      //~va11I~
//  			Pairs uardp=Puardp.add(PT_NUMSEQ,Ptype,Ppos,true); //~va11R~
    			Pair pair=Puardp.add(Pparent,PT_NUMSEQ,Ptype,Ppos,true);//~va11I~
//  		    rc=makePairingNum(uardp,PctrNum,Ptype);            //~va11R~
    		    rc=makePairingNum(Puardp,pair,PctrNum,Ptype);      //~va11I~
            }                                                      //~va11I~
            else                                                   //~va11I~
            {                                                      //~va11I~
	    		PctrNum[Ppos]++; PctrNum[Ppos+1]++; PctrNum[Ppos+2]++;//~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonChk.makePairingNumSeq rc="+rc+",PctrNum="+Arrays.toString(PctrNum));//~va11R~
        return rc;                                                 //~va11R~
    }                                                              //~va11I~
	//*************************************************************************//~9C11I~
    protected boolean is7Pair()                                      //~9C11I~//~va11R~
    {                                                              //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.is7Pair");               //~9C11I~
    	if (!swAllInHand)                                            //~va11R~
        	return false;                                          //~va11I~
        int ctrPair=0;                                             //~9C12I~
        int[] dupctr;                                              //~9C12I~
        dupctr=dupCtr[TT_JI];                                      //~9C12I~
        for (int ii=0;ii<PIECE_NOTNUM_CTR;ii++)                    //~9C11I~
        {                                                          //~9C11I~
//          int num=dupCtrNotNum[ii];                              //~9C11I~//~9C12R~
            int num=dupctr[ii];                                    //~9C12I~
//          if (num==2 || (num==4 && sw7Pair4Pair))                //~9C11I~//~va14R~
            if (num==2)                                            //~va14I~
                ctrPair++;                                         //~9C12I~
            else                                                   //~va14I~
            if (num==4 && sw7Pair4Pair)                            //~va14I~
                ctrPair+=2;                                        //~va14I~
        }                                                          //~9C11I~
        for (int jj=0;jj<PIECE_NUMBERTYPECTR;jj++)                 //~9C11I~
        {                                                          //~9C11I~
	        dupctr=dupCtr[jj];                                     //~9C12I~
            for (int ii=0;ii<PIECE_NUMBERCTR;ii++)                 //~9C11I~
            {                                                      //~9C11I~
//  			int num=dupCtrNum[jj][ii];                         //~9C11I~//~9C12R~
    			int num=dupctr[ii];                                //~9C12I~
//              if (num==2 || (num==4 && sw7Pair4Pair))            //~9C11I~//~va14R~
                if (num==2)                                        //~va14I~
    	            ctrPair++;                                     //~9C12I~
                else                                               //~va14I~
                if (num==4 && sw7Pair4Pair)                       //~va14I~
    	            ctrPair+=2;                                    //~va14I~
            }                                                      //~9C11I~
        }                                                          //~9C11I~
        boolean rc=ctrPair==7;                                     //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.is7Pair sw7Pair4Pair="+sw7Pair4Pair+",ctrPair="+ctrPair+",rc="+rc);       //~9C11I~//~9C12R~//~va14R~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~9C11I~
	//*Kokushi                                                     //~0202I~
	//*************************************************************************//~0202I~
    protected boolean is13All()                                      //~9C11I~//~va11R~
    {                                                              //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.is13All");              //~9C11R~
        if (!swAllInHand)                                          //~va11R~
        	return false;                                          //~va11I~
        int ctrPillow=0;                                           //~9C11I~
        int ctrSingle=0;                                           //~9C11I~
        int num;                                                   //~9C11I~
        int[] dupctr;                                              //~9C12I~
        dupctr=dupCtr[TT_JI];                                      //~9C12I~
        for (int ii=0;ii<PIECE_NOTNUM_CTR;ii++)                    //~9C11I~
        {                                                          //~9C11I~
//          num=dupCtrNotNum[ii];                                  //~9C11I~//~9C12R~
            num=dupctr[ii];                                        //~9C12I~
            if (num==2)                                            //~9C11I~
            	ctrPillow++;                                       //~9C11I~
            else                                                   //~9C11I~
            if (num==1)                                            //~9C11I~
            	ctrSingle++;                                       //~9C11I~
        }                                                          //~9C11I~
        for (int jj=0;jj<PIECE_NUMBERTYPECTR;jj++)                 //~9C11I~
        {                                                          //~9C11I~
	        dupctr=dupCtr[jj];                                     //~9C12I~
//      	num=dupCtrNum[jj][0];                                  //~9C11I~//~9C12R~
        	num=dupctr[0];                                         //~9C12I~
            if (num==2)                                            //~9C11I~
            	ctrPillow++;                                       //~9C11I~
            else                                                   //~9C11I~
            if (num==1)                                            //~9C11I~
            	ctrSingle++;                                       //~9C11I~
//      	num=dupCtrNum[jj][PIECE_NUMBERCTR-1];                  //~9C11I~//~9C12R~
        	num=dupctr[PIECE_NUMBERCTR-1];                         //~9C12I~
            if (num==2)                                            //~9C11I~
            	ctrPillow++;                                       //~9C11I~
            else                                                   //~9C11I~
            if (num==1)                                            //~9C11I~
            	ctrSingle++;                                       //~9C11I~
        }                                                          //~9C11I~
        boolean rc=ctrPillow==1 && ctrSingle==12;                  //~9C11I~//~9C12R~
        if (Dump.Y) Dump.println("UARonChk.13All rc="+rc+",ctrPillow="+ctrPillow+",ctrSingle="+ctrSingle);//~9C11R~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~9C11I~
    protected boolean is13NoPair()                                 //~va83I~
    {                                                              //~va83I~
        sw1stTake= CompReqDlg.chk1stTake();                        //~va83I~
        if (Dump.Y) Dump.println("UARonChk.is13NoPair sw1stTake="+sw1stTake);//~va83I~
		if (!sw1stTake)                                            //~va83I~
            return false;                                          //~va83I~
        boolean rc=is13NoPair1stTake();                             //~va83I~
        if (Dump.Y) Dump.println("UARonChk.is13NoPair rc="+rc);    //~va83I~
        return rc;
    }                                                              //~va83I~
	//*************************************************************************//~va83I~
    protected boolean is13NoPair(int Pplayer)                      //~va83I~
    {                                                              //~va83I~
        sw1stTake= UARonValue.chk1stTake(Pplayer);                 //~va83I~
        if (Dump.Y) Dump.println("UARonChk.is13NoPair player="+Pplayer+",sw1stTake="+sw1stTake);//~va83I~
		if (!sw1stTake)                                            //~va83I~
            return false;                                          //~va83I~
        boolean rc=is13NoPair1stTake();                             //~va83I~
        if (Dump.Y) Dump.println("UARonChk.is13NoPair rc="+rc);    //~va83I~
        return rc;
    }                                                              //~va83I~
	//*************************************************************************//~va83I~
//  protected boolean is13NoPair()                                   //~9C11I~//~va11R~//~va83R~
    private boolean is13NoPair1stTake()                            //~va83I~
    {                                                              //~9C11I~
//    if (swNoPairPlayAlone)                                       //~va83R~
//      sw1stTake=true;                                            //~va83R~
//    else                                                         //~va83R~
//      sw1stTake= CompReqDlg.chk1stTake();                         //~va11I~//~va83R~
        if (Dump.Y) Dump.println("UARonChk.is13NoPair1stTake swNoPairPlayAlone="+swNoPairPlayAlone+",sw1stTake="+sw1stTake+",ctrTileAll="+ctrTileAll+",sw13Nopair="+sw13NoPair+",sw14NoPair="+sw14NoPair);//~9C11R~//~9C12R~//~va11R~//~va83R~
    	swIs14NoPair=false;                                        //~va11I~
    	boolean rc=true;                                           //~9C11I~
//  	if (!sw1stTake)                                            //~va11I~//~va83R~
//          return false;                                          //~va11I~//~va83R~
		if (!sw13NoPair && !sw14NoPair)                            //~9C12I~
            return false;                                          //~9C12I~
//      if (ctrTileAll!=HANDCTR_TAKEN)                             //~9C12I~//~va11I~
//      {                                                          //~9C12I~//~va11I~
//          if (Dump.Y) Dump.println("UARonChk.is13NoPair return false not No Earth");//~9C12I~//~va11I~
//          return false;                                          //~9C12I~//~va11I~
//      }                                                          //~9C12I~//~va11I~
      if (!swNoPairPlayAlone)                                      //~va83I~
      {                                                            //~va83I~
        if (!swAllInHand)                                          //~va11R~
        	return false;                                          //~va11I~
      }                                                            //~va83I~
        posPillow=new Point(0,0);                                  //~9C11I~
        Point p=selectPillow();	//type and number                  //~9C11I~
        if (p==null)                                               //~9C11R~
        {                                                          //~9C11I~
			if (!sw14NoPair)                                       //~9C11I~
	            rc=false;                                          //~9C11R~
            else                                                   //~va11I~
    			swIs14NoPair=true;                                 //~va11I~
        }                                                          //~9C11I~
        else                                                       //~9C11I~
        {                                                          //~9C11I~
        	Point p2=selectPillow();	//type and number          //~va22I~
            if (p2!=null)          //2 pillow                      //~va22I~
            {                                                      //~va22I~
            	rc=false;                                          //~va22I~
//          	restorePillow(p2);                                 //~va22I~//~va82R~
            }                                                      //~va22I~
			if (!sw13NoPair)                                       //~9C11I~
	            rc=false;                                          //~9C11I~
        }                                                          //~9C11I~
        if (rc)                                                    //~9C11R~
        {                                                          //~9C11I~
        	if (p!=null)                                           //~9C11I~
//              dropPillow(p);                                     //~va22R~
                dupCtr[p.x][p.y]--; //drop 1/2 of pillow           //~va22I~
        	rc=chkNoPairingNotNum() && chkNoPairingNum();            //~9C11I~
        	if (p!=null)                                           //~va11I~
//          	restorePillow(p);                                  //~va22R~
                dupCtr[p.x][p.y]++; //restore 1/2 of pillow        //~va22I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.is13NoPair rc="+rc+",rule-swIs14NoPair="+swIs14NoPair);//~va22R~//~va95R~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~va83I~
	//*from RACall at human raken in playAlonNotfy mode  at 1stTake//~va83R~
	//*************************************************************************//~va83I~
    public boolean isNoPairPlayAlone(int[][] PdupCtr)              //~va83I~
    {                                                              //~va83I~
        if (Dump.Y) Dump.println("UARonChk.isNoPair dupCtr="+Utils.toString(PdupCtr));//~va83I~
        int[][] dupCtrSave=dupCtr;                                         //~va83I~
        dupCtr=PdupCtr;                                           //~va83I~
        swNoPairPlayAlone=true;                                    //~va83I~
//      boolean rc=is13NoPair();                                   //~va83R~
        boolean rc=is13NoPair1stTake();                            //~va83I~
        swNoPairPlayAlone=false;                                   //~va83I~
        dupCtr=dupCtrSave;                                         //~va83I~
        return rc;                                                 //~va83I~
    }                                                              //~va83I~
	//*************************************************************************//~9C11I~
    private boolean chkNoPairingNotNum()                            //~9C11I~
    {                                                              //~9C11I~
    	boolean rc=true;                                           //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkNoPairingNotNum NotNum="+Arrays.toString(dupCtr[TT_JI]));//~9C11I~
        int[] dupctr=dupCtr[TT_JI];                                      //~9C12I~
        for (int ii=0;ii<PIECE_NOTNUM_CTR;ii++)                         //7//~9C11I~
        {                                                          //~9C11I~
//          if (dupCtrNotNum[ii]>1)                                //~9C11I~//~9C12R~
            if (dupctr[ii]>1)                                      //~9C12I~
            {                                                      //~9C11I~
            	rc=false;                                          //~9C11I~
            	break;                                             //~9C11I~
            }                                                      //~9C11I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkNoPairingNotNum rc="+rc);//~9C11I~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~9C11I~
    private boolean chkNoPairingNum()                               //~9C11I~
    {                                                              //~9C11I~
    	boolean rc=true;                                           //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkNoPairingNum Num="+Utils.toString(dupCtr));//~9C11I~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~9C11I~
        {                                                          //~9C11I~
//  	    if (!chkNoPairingNum(dupCtrNum[ii]))                    //~9C11I~//~9C12R~
    	    if (!chkNoPairingNum(dupCtr[ii],ii))                    //~9C12R~
            {                                                      //~9C11I~
            	rc=false;                                          //~9C11I~
                break;                                             //~9C11I~
            }                                                      //~9C11I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkNoPairingNum rc="+rc);//~9C11I~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~9C11I~
    private boolean chkNoPairingNum(int[] PctrNum,int Ptype)                  //~9C11I~//~9C12R~
    {                                                              //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkNoPairingNum type="+Ptype+",Num="+Arrays.toString(PctrNum));//~9C11I~//~9C12R~
    	boolean rc=true;                                           //~9C11I~
        for (int ii=0;ii<PIECE_NUMBERCTR;ii++)                         //7//~9C11I~
        {                                                          //~9C11I~
            if (PctrNum[ii]>1)                                  //~9C11I~
            {                                                      //~9C11I~
            	rc=false;                                          //~9C11I~
            	break;                                             //~9C11I~
            }                                                      //~9C11I~
        }                                                          //~9C11I~
        if (rc)                                                    //~9C11I~
        {                                                          //~9C11I~
		    if (!chkNoPairingSeq(PctrNum,Ptype))                           //~9C11I~//~9C12R~
            	rc=false;                                          //~9C11I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkNoPairingNum rc="+rc);//~9C11I~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~9C11I~
    private boolean chkNoPairingSeq(int[] PctrNum,int Ptype)                  //~9C11I~//~9C12R~
    {                                                              //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkNoPairingSeq type="+Ptype+",Num="+Arrays.toString(PctrNum));//~9C11I~//~9C12R~
    	boolean rc=true;                                           //~9C11I~
        for (int ii=0;ii<PIECE_NUMBERCTR;)                         //7//~9C11I~
        {                                                          //~9C11I~
            if (PctrNum[ii]!=0)                                    //~9C11I~
            {                                                      //~9C11I~
	            if ((ii+1<PIECE_NUMBERCTR && PctrNum[ii+1]!=0)        //~9C11I~
                ||  (ii+2<PIECE_NUMBERCTR && PctrNum[ii+2]!=0)        //~9C11I~
                )                                                  //~9C11I~
                {                                                  //~9C11I~
                    rc=false;                                      //~9C11I~
                    break;                                         //~9C11I~
                }                                                  //~9C11I~
                ii+=3;                                             //~9C11I~
            }                                                      //~9C11I~
            else                                                   //~9C12I~
                ii++;                                              //~9C12I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.chkNoPairingSeq rc="+rc);//~9C11I~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
}//class                                                           //~v@@@R~
