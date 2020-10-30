//*CID://+va1aR~: update#= 730;                                    //+va1aR~
//**********************************************************************//~v101I~
//2020/10/19 va1a drop ronchk option,1han constraint only          //+va1aI~
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
//  protected boolean swCheckRonable;                                //~0205I~//~va11R~//+va1aR~
    protected int ctrRecursive;                                      //~9C12I~//~va11R~
    protected int ctrTileAll;                                        //~9C12I~//~va11R~
    protected int ctrPair;                                         //~va11I~
    public  int player;                                            //~va11I~
    private boolean sw13_14NoPair,sw1stTake;                       //~va11R~
    public boolean swAllInHand;                           //~va11I~
//*************************                                        //~v@@@I~
	UARonChk()                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~9C11R~
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
//      swCheckRonable= RuleSettingOperation.isCheckRonable();     //~0205R~//+va1aR~
    }                                                              //~v@@@I~
	//*************************************************************************//~va11I~
    protected boolean isAllInHand()                                //~va11R~
    {                                                              //~va11I~
//  	boolean rc=ctrPair==0;                                     //~va11R~
		boolean rc=AG.aPlayers.isClosedHand(player);               //~va11I~
        if (Dump.Y) Dump.println("UARonChk.isAllInHand rc="+rc+",ctrPair="+ctrPair);//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~9C11I~
	//*                                                            //~va11R~
	//*************************************************************************//~9C11I~
    public boolean chkComplete(int Pplayer)                     //~9C11R~
    {                                                              //~9C11I~
    	boolean rc;                                                //~9C11I~
        player=Pplayer;                                            //~va11I~
        swAllInHand=isAllInHand();                                 //~va11I~
		ctrPair=AG.aPlayers.getCtrPair(Pplayer);       //including Ron tile//~va11I~
        if (Dump.Y) Dump.println("UARonChk.chkComplete player="+Pplayer+",ctrPair="+ctrPair);//~9C11I~//~0205R~//~va11R~//+va1aR~
        TileData[] tds=AG.aPlayers.getHands(Pplayer);		//including Ron tile//~9C11R~
        TileData tdRon=null;                                       //~9C11I~
        if (!Tiles.isTakenStatus(tds.length))                      //~9C11I~
        	tdRon=AG.aPlayers.getTileCompleteSelectInfoRon();              //~9C11I~
//        if ((TestOption.option2 & TestOption.TO2_RON_TEST)!=0) //TODO//~9C12M~//~va11R~
//        {                                                          //~9C12M~//~va11R~
////          ronTest();                                             //~9C12M~//~9C13R~//~va11R~
//            return true;                                           //~9C12M~//~va11R~
//        }                                                          //~9C12M~//~va11R~
//      if (!swCheckRonable)                                       //~0205I~//+va1aR~
//  		return true;                                           //~0205I~//+va1aR~
        sw1stTake=CompReqDlg.chk1stTake();                         //~va11I~
        sortTiles(tds,tdRon);                                      //~9C11R~
        if ((TestOption.option2 & TestOption.TO2_RON_TEST)!=0) //TODO//~va11I~
        {                                                          //~va11I~
          	int[][] testHand=UARonValue.getTestHandRonChk();   //by testcase//~va11I~
            if (testHand!=null)                                    //~va11I~
            	dupCtr=testHand;                                   //~va11I~
        }                                                          //~va11I~
	    rc=chkCompleteSub();
        return rc;//~9C12I~
    }                                                              //~9C12I~
	//*************************************************************************//~9C12I~
    private boolean chkCompleteSub()                               //~9C12I~
    {                                                              //~9C12I~
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
	//*************************************************************************//~9C11I~
	//*get ctr of each suit                                        //~9C11I~
	//*************************************************************************//~9C11I~
    protected void sortTiles(TileData[] Ptds,TileData PtdRon)        //~9C11R~//~va11R~
    {                                                              //~9C11I~
    	int type,num;                                              //~9C11I~
    //*******************************                              //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.sortTiles tds="+TileData.toString(Ptds)+",tdRon="+Utils.toString(PtdRon));//~9C11R~
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
	//*************************************************************************//~9C11I~
	//*search 2pair then chk pairing                               //~9C11I~
	//*************************************************************************//~9C11I~
    private boolean isStandardPairing()                            //~9C11I~
    {                                                              //~9C11I~
    	boolean rc=false;                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.isStandardPairng");      //~9C11I~
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
	//*from CompReqDlg when tenho/chiho                            //~va11I~
	//*************************************************************************//~va11I~
    public boolean chkRonnable()                                   //~va11R~
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
		        if (Dump.Y) Dump.println("UARonChk.makePairingNotNum @@@@err dupCtr="+num);//~va11I~
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
//      if (Dump.Y) Dump.println("UARonChk.makePairingNumSeq type="+Ptype+",pos="+Ppos+",Num="+Arrays.toString(PctrNum));//~va11R~
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
    protected boolean is13NoPair()                                   //~9C11I~//~va11R~
    {                                                              //~9C11I~
        sw1stTake= CompReqDlg.chk1stTake();                         //~va11I~
        if (Dump.Y) Dump.println("UARonChk.is13NoPair sw1stTake="+sw1stTake+",ctrTileAll="+ctrTileAll+",sw13Nopair="+sw13NoPair+",sw14NoPair="+sw14NoPair);//~9C11R~//~9C12R~//~va11R~
    	swIs14NoPair=false;                                        //~va11I~
    	boolean rc=true;                                           //~9C11I~
		if (!sw1stTake)                                            //~va11I~
            return false;                                          //~va11I~
		if (!sw13NoPair && !sw14NoPair)                            //~9C12I~
            return false;                                          //~9C12I~
//      if (ctrTileAll!=HANDCTR_TAKEN)                             //~9C12I~//~va11I~
//      {                                                          //~9C12I~//~va11I~
//          if (Dump.Y) Dump.println("UARonChk.is13NoPair return false not No Earth");//~9C12I~//~va11I~
//          return false;                                          //~9C12I~//~va11I~
//      }                                                          //~9C12I~//~va11I~
        if (!swAllInHand)                                          //~va11R~
        	return false;                                          //~va11I~
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
			if (!sw13NoPair)                                       //~9C11I~
	            rc=false;                                          //~9C11I~
        }                                                          //~9C11I~
        if (rc)                                                    //~9C11R~
        {                                                          //~9C11I~
        	if (p!=null)                                           //~9C11I~
	            dropPillow(p);                                     //~9C11R~
        	rc=chkNoPairingNotNum() && chkNoPairingNum();            //~9C11I~
        	if (p!=null)                                           //~va11I~
            	restorePillow(p);                                  //~va11I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UARonChk.is13NoPair rc="+rc);    //~9C11I~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
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
	//*************************************************************************//~9C12I~
	private void ronTest()                                         //~9C12I~
    {                                                              //~9C12I~
	    boolean rc;
        if (Dump.Y) Dump.println("UARonChk.ronTest");              //~9C12I~
		ctrTileAll=HANDCTR_TAKEN;                                  //~9C12I~
        dupCtr=new int[][]{   //4 anko                             //~9C12R~
        	{3,3,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
    	    { 3,3,0,0,  2,0,0, 0,0} };                         //7 //~9C12R~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-1 4Anko rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{           //7 pair                     //~9C12I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,2, 0,0,0, 2,0,2},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-2 7pair rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{           //7 pair err                 //~9C12I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-3 7pair err rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12R~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,4, 2,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-4 4seq  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12R~
        	{1,1,3, 4,3,1, 1,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-5 4seq-3  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{2,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-6 13All  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-7 13All err1  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,1,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-8 13All err2  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{3,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-9 13All err3  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-10 13NoPair       rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{2,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-11 13NoPair       rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,2,  1,0,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-12 13NoPair       rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-13 13NoPair err1  rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,1, 0,0,0, 0,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-14 13NoPair err2  rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 0,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  1,1,1, 0,0} };                         //7 //~9C12I~
		ctrTileAll=11;                                             //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
		ctrTileAll=HANDCTR_TAKEN;                                  //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-15 13NoPair err3  rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,1,1,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-16 14NoPair       rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,2,0,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-17 14NoPair err1  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,1, 0,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,1,1,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-18 14NoPair err2  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{        //34567    2.5.8  sou           //~9C12I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-21 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,2,1, 1,0,0},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-21 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,1,1, 1,1,0},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-21 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                 //2345 pin  2.5        //~9C12I~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-22 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,1,2, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-22 2              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                   //2345678 pin 2.5.8  //~9C12I~
        	{0,2,1, 1,1,1, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-23 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-23 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,1, 1,2,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-23 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{              //67888 gg    5.8.g       //~9C12I~
        	{2,0,0, 0,1,1, 1,3,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-24 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{2,0,0, 0,0,1, 1,4,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-24 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{3,0,0, 0,0,1, 1,3,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-24 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                     //5666 zou 4.5.6   //~9C12I~
        	{0,0,0, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-25 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 0,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-25 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 0,1,3, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-25 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                           //4666 sou 4.5.6//~9C12I~
        	{0,0,0, 2,0,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-26 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-26 1              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                     //2345666 pin 1.2.4.5.7//~9C12I~
        	{1,1,1, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,2,1, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 2,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,3, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 5              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                   //2345777 pin 2.5.6  //~9C12I~
        	{0,2,1, 1,1,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-28 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,2,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-28 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,1, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-28 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                         //2333456 pin   1.2.4.7//~9C12I~
        	{1,1,3, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-29 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,2,3, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-29 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,3, 2,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-29 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,3, 1,1,1, 1,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-29 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~9C12I~
        	{0,0,3, 1,1,1, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-30 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 1,1,1, 0,2,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-30 2              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~
        	{0,1,1, 1,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~
        	{0,0,1, 2,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~
        	{0,0,1, 1,3,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~
        	{0,0,1, 1,2,3, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                   //3455777 pin 2.5.6  //~9C12I~
        	{0,1,1, 1,2,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,3,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,2,1, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~9C12R~
        	{0,1,1, 2,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~9C12R~
        	{0,0,1, 3,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~9C12R~
        	{0,0,1, 2,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~9C12I~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-32 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~9C12I~
        	{0,0,1, 1,3,1, 1,1,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-32 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~9C12I~
        	{0,0,1, 1,2,1, 1,2,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-32 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,1,1, 1,1,1, 1,1,3},      //2345678999 man 1.2.4.5.7.8//~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,2,1, 1,1,1, 1,1,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 2,1,1, 1,1,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,2,1, 1,1,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,1, 2,1,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 5              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,1, 1,2,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 6              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                 //3334445 sou 3.4.5.6  //~9C12I~
        	{0,0,4, 3,1,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-34 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 4,1,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-34 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 3,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-34 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 3,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-34 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                 //3334555 sou 2.3.4.5.6//~9C12I~
        	{0,1,3, 1,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,4, 1,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 2,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 1,4,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 1,3,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 5              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{               //3335777 sou 4.5.6      //~9C12I~
        	{0,0,3, 1,1,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-36 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 0,2,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-36 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 0,1,1, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-36 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                  //3334455  sou 3.4.5.6//~9C12I~
        	{0,0,4, 2,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-37 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 3,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-37 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 2,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-37 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 2,2,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-37 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //3344455 sou  3.4.5       //~9C12I~
        	{0,0,3, 3,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-38 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,2, 4,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-38 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,2, 3,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-38 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{           //4555567  man   3.4.6.7     //~9C12I~
        	{0,0,1, 1,4,1, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-39 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 2,4,1, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-39 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,4,2, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-39 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,4,1, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-39 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{              //4555566 man    3.4.6.7  //~9C12I~
        	{0,0,1, 1,4,2, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-40 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 2,4,2, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-40 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,4,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-40 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,4,2, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-40 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                 //5555667 man    4.6.7 //~9C12I~
        	{0,0,0, 1,4,2, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-41 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 0,4,3, 1,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-41 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 0,4,2, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-41 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //5555677  man 4.6.7       //~9C12I~
        	{0,0,0, 1,4,1, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-42 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //5555677                  //~9C12I~
        	{0,0,0, 0,4,2, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-42 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //5555677                  //~9C12I~
        	{0,0,0, 0,4,1, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-42 1              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~9C12I~
        	{0,2,2, 2,4,2, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-51 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~9C12I~
        	{0,1,2, 2,4,3, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-51 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~9C12I~
        	{0,1,2, 2,4,2, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-51 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~9C12I~
        	{0,1,2, 2,4,2, 2,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-51 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,2,2, 2,1,1, 4,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,2, 2,2,1, 4,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,2, 2,1,1, 4,2,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,3, 2,1,1, 4,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,2, 2,1,2, 4,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 5              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,2, 2,1,1, 4,1,2},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 6              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~9C12I~
        	{2,2,2, 4,3,1, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-53 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~9C12I~
        	{1,2,2, 4,3,1, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-53 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~9C12I~
        	{1,2,2, 4,4,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-53 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~9C12I~
        	{1,2,2, 4,3,2, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-53 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{1,4,1, 1,1,4, 1,1,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{0,4,1, 2,1,4, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{0,4,1, 1,1,4, 2,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{0,4,1, 1,2,4, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{0,4,1, 1,1,4, 1,2,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 5              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,1, 3,2,2, 2,1,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,1, 2,2,2, 3,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,2, 2,2,2, 2,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,1, 2,2,3, 2,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,1, 2,2,2, 2,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 5              rc="+rc);//~9C12I~
                                                                   //~9C12I~
    }                                                              //~9C12I~
}//class                                                           //~v@@@R~
