//*CID://+va24R~: update#= 896;                                    //~va24R~
//**********************************************************************//~v101I~
//2020/11/02 va24 chkCompleteSub may cause stackoverflow           //~va24I~
//2020/11/02 va23 use Junit for UARonValue                         //~va23I~
//2020/10/20 va20 use Junit for UARonchk                           //~va20I~
//2020/10/19 va1a drop ronchk option,1han constraint only          //~va1aI~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~va11R~

import android.graphics.Point;

import com.btmtest.TestOption;
import com.btmtest.dialog.CompReqDlg;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.game.GConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UA.Rank.*;
import static com.btmtest.game.gv.Pieces.*;
import static com.btmtest.StaticVars.AG;                           //~9C11I~
import static com.btmtest.dialog.CompReqDlg.*;                       //~va11I~
//****************************************************             //~9C11I~
public class UARonValue extends UARonChk                               //~v@@@R~//~9C11R~//~0925R~
{                                                                  //~0914I~
	public static final int RV_YAKUMAN     =8000*4;                //~va11I~
	public static final int RV_YAKUMAN2    =RV_YAKUMAN*2;          //~va11I~
                                                                   //~va11I~
	private static final int RANK_19JI     =RANKID_YAKUMAN;        //~va11I~
//    private Point posPillow;//~v@@@R~                            //~va11R~
//    private int[][] dupCtr=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR]; //4*9//~9C12I~//~va11R~
//    private boolean sw7Pair4Pair;                                  //~9C11I~//~va11R~
//    private boolean sw14NoPair,sw13NoPair;                         //~9C11R~//~va11R~
//    private boolean swCheckRonable;                                //~0205I~//~va11R~
//    private int ctrRecursive;                                      //~9C12I~//~va11R~
//    private int ctrTileAll;                                        //~9C12I~//~va11R~
    public int[][] dupCtrAll=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR];//hand and earth //4*9//~va11R~
//  private Rect ronValue;  //(value,yaky,han,fu)                  //~va11R~
//  private RonResult ronResult;                                   //~va23R~
    protected RonResult ronResult;                                 //~va23I~
    protected TileData tdRonLast;                                  //~va24R~
    public  TileData tdRonRiver;		//river ronTile when not taken(not on tdsHand)//~va11R~
    public boolean sw13WaitAll;                                 //~va24R~
    private boolean sw7Pair50,sw7Pair30,swAllGreenNoDragon;       //~va24I~
    public boolean sw4WindDouble,sw9GateDouble;                    //~va24R~
    private boolean sw9GateManOnly,swRankMUp;                      //~va24R~
    private UARonDataTree UARDT;                                   //~va11I~
    public  TileData[][] tdssEarth;                                //~va11R~
    public  TileData[] tdsHand;                                    //~va11I~
//  public  int player;                                            //~va11R~
    public int ronType,ronNumber;                                  //~va11R~
    private boolean swYakuman,swTanyao;                            //~va11R~
    public  boolean swTaken;                                       //~va11I~
//  public  boolean swAllHand;                                     //~va11R~
//  private CompReqDlg compReqDlg;                                 //~va11R~
    public boolean sw7P;                                           //~va11I~
    protected boolean sw1stTake;                                   //~va24R~
//  private int ctrAnkan;                                          //~va23R~
    protected int ctrAnkan;	//for androidTest                      //~va23I~
    public boolean swChkRank;                                      //~va11I~
    public Pair[] pairEarth;                                       //~va11I~
    private boolean swRonnable;                                    //~va11I~
    private boolean swYakuOtherEnvironment;                        //~va11I~
    //*************************                                        //~v@@@I~
	public UARonValue()                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~9C11R~//~0925R~//~va11R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UARonValue Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~9C11R~//~0925R~
    }                                                              //~0914I~
//    public UARonValue(CompReqDlg PcompReqDlg)                    //~va11R~
//    {                                                            //~va11R~
//        if (Dump.Y) Dump.println("UARonValue Constructor with CompReqDlg");//~va11R~
//        compReqDlg=PcompReqDlg;                                  //~va11R~
//    }                                                            //~va11R~
	//*************************************************************************//~v@@@I~
    @Override                                                      //~va11I~
	protected void init()                                             //~v@@@I~//~va11R~
    {                                                              //~v@@@I~
        super.init();                                              //~va11R~
        sw13WaitAll=RuleSettingYaku.is13WaitAll();                 //~va11I~
        sw7Pair50=RuleSettingYaku.is7Pair50();                     //~va11I~
        sw7Pair30=RuleSettingYaku.is7Pair30();                     //~va11I~
//  	swRankMUp=RuleSetting.isRankMUp();   //kiriage mangan      //~9212I~//~va11R~
    	swAllGreenNoDragon=RuleSettingYaku.isAllGreenNoDragon();       //~va11I~
    	sw4WindDouble=RuleSettingYaku.is4WindDouble();             //~va11I~
    	sw9GateManOnly=RuleSettingYaku.is9GateManOnly();           //~va11I~
    	sw9GateDouble=RuleSettingYaku.is9GateDouble();             //~va11I~
    	swRankMUp=RuleSetting.isRankMUp();   //kiriage mangan      //~va11I~
//      subUARV=new UARonValueSub(this);                           //~va11R~
    }                                                              //~v@@@I~
    //*************************************************************************//~va11I~
    //*rc=-1:not ronnable,1:1han constraint,0 ok                   //~va11R~
    //*************************************************************************//~va11I~
    public int chkRank(UARon Puaron,int Pplayer)                   //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkRank player="+Pplayer);//~va11I~
        int rc=0;                                                  //~va11I~
        swChkRank=true;                                            //~va11R~
	    getValue(Pplayer);                                         //~va11R~
        if (!swRonnable)                                           //~va11I~
        	rc=-1;                                                 //~va11I~
        else                                                       //~va11I~
    		if (ronResult.han==0)                                  //~va11R~
            	rc=1;                                              //~va11I~
            else                                                   //~va1aI~
        		if (ronResult.longRank.getDora()!=0 && !ronResult.longRank.isContainsAnyYakuExceptDora())//han is dora only,all 0 is valid(widthout setting longRank)
                	rc=1;                                          //~va1aI~
        swChkRank=false;                                           //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkRank rc="+rc+",han="+ronResult.han+",swRonnable="+swRonnable);//~va11R~
        return rc;
    }                                                              //~va11I~
    //*************************************************************************//~va11I~
    public RonResult getValue(int Pplayer)                         //~va11R~
//  public RonResult getValue(CompReqDlg PcompReqDlg,int Pplayer)  //~va11R~
    {                                                              //~va11I~
        if ((TestOption.option2 & TestOption.TO2_RONVALUE_TEST)!=0)//~va11M~
        {                                                          //~va11M~
//          RonResult r=ronTest(PcompReqDlg,Pplayer);              //~va11R~
            RonResult r=ronTest(Pplayer);                          //~va11I~
            return r;                                              //~va11M~
        }                                                          //~va11M~
        swRonnable=true;                                           //~va11I~
//      compReqDlg=PcompReqDlg;                                    //~va11R~
        if (Dump.Y) Dump.println("UARonValue.getValue player="+Pplayer+",swTaken="+swTaken);//~va11R~
    	swAllInHand=isAllInHand();	//UARonChk, earth is ankan only //~va11I~//~va1aR~
        sw1stTake=CompReqDlg.chk1stTake();	//parent 1st take or child 1st take//~va11R~
//      swTaken=compReqDlg.swTake;                                 //~va11R~
        player=Pplayer;                                            //~va11I~
		ctrPair=AG.aPlayers.getCtrPair(Pplayer);       //including Ron tile//~va11I~
        TileData[] tds=AG.aPlayers.getHands(Pplayer);       //including Ron tile//~va11I~
        tdsHand=tds;                                               //~va11I~
        tdssEarth=AG.aPlayers.getEarth(Pplayer);                   //~va11I~
        if (Dump.Y) Dump.println("UARonValue.getValue hand="+TileData.toString(tds));//~va11R~
        if (Dump.Y) Dump.println("UARonValue.getValue earth="+TileData.toString(tdssEarth));//~va11I~
        tdRonLast=AG.aPlayers.getTileCompleteSelectInfoRon();   //to calc Fu//~va11I~
        ronType=tdRonLast.type;                                    //~va11I~
        ronNumber=tdRonLast.number;                                //~va11I~
//      TileData tdRon=null;	//to be added to hands             //~va11R~
        if (!Tiles.isTakenStatus(tds.length))                      //~va11I~
        {
//          tdRon=AG.aPlayers.getTileCompleteSelectInfoRon();      //~va11I~
            tdRonRiver=tdRonLast;                                  //~va11R~
			swTaken=false;
        }
        else                                                       //~va11I~
        {                                                          //~va1aI~
            tdRonRiver=null;                                       //~va1aI~
			swTaken=true;                                          //~va11I~
        }                                                          //~va1aI~
                                                                   //~va11I~
        if (Dump.Y) Dump.println("UARonValue.getValue testOption2="+Integer.toHexString(TestOption.option2));//~va11I~
        sortTiles(tds,tdRonRiver);                                 //~va11R~
        addEarth();                                                //~va11M~
        getPairEarth();                                            //~va11I~
        if ((TestOption.option2 & TestOption.TO2_RONVALUE_TESTSUB)!=0)//~va11I~
        {                                                          //~va11I~
        	dupCtr=testDupCtr;                                     //~va11I~
        	dupCtrAll=testDupCtrAll;                               //~va11I~
        	ctrAnkan=testCtrAnkan;                                 //~va11I~
        	ronType=testRonType; ronNumber=testRonNumber;        //~va11I~
        	pairEarth=testPairEarth;                               //~va11I~
        	swAllInHand=testSwAllHand;                             //~va11R~
        }                                                          //~va11I~
//      boolean rc=chkCompleteSub();                               //~va24R~
        boolean rc=chkRonValueSub();                               //~va24I~
        if (Dump.Y) Dump.println("UARonValue.getValue rc="+rc+",ronvalue="+ronResult.toString());//~va11R~
        return ronResult;                                          //~va11R~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
	//*TODO test                                                   //~va11I~
	//*************************************************************************//~va11I~
    private static int[][] testDupCtr,testDupCtrAll;               //~va11R~
    private static int testRonType,testRonNumber,testCtrAnkan;     //~va11R~
    private static boolean testSwAllHand;                           //~va11I~
    private static Pair[]  testPairEarth;                          //~va11I~
    private static boolean testSkip1314NoPair;                     //~va11I~
//  private static CompReqDlg CRD;                                 //~va11R~
    public RonResult ronTestSub(int[][] PdupCtr,int PronType,int PronNumber)//~va11R~
    {                                                              //~va11I~
        int[][] dupCtrAll=Utils.cloneArray2(PdupCtr);              //~va11I~
	    return ronTestSub(PdupCtr,dupCtrAll,PronType,PronNumber,0,true,null);//~va11R~
    }                                                              //~va11I~
    public RonResult ronTestSub(int[][] PdupCtr,int PronType,int PronNumber,boolean PswAllHand)//~va11R~
    {                                                              //~va11I~
        int[][] dupCtrAll=Utils.cloneArray2(PdupCtr);              //~va11I~
	    return ronTestSub(PdupCtr,dupCtrAll,PronType,PronNumber,0,PswAllHand,null);//~va11I~
    }                                                              //~va11I~
    public RonResult ronTestSub(int[][] PdupCtr,int[][] PdupCtrAll,int PronType,int PronNumber,int PctrAnkan,boolean PswAllHand,Pair[] PpairEarth)//~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.ronTestSub");         //~va11I~
    	if (swGetTestHand)                                         //~va11I~
        {                                                          //~va11I~
        	testHand=PdupCtr;                                      //~va11I~
	        if (Dump.Y) Dump.println("UARonValue.ronTestSub getTestHand="+Utils.toString(testHand));//~va11I~
            return new RonResult(0,0,0,new Rank());	//dummy        //~va11I~
        }                                                          //~va11I~
        TestOption.option2 &= ~TestOption.TO2_RONVALUE_TEST;      //~va11I~
        TestOption.option2 |= TestOption.TO2_RONVALUE_TESTSUB;     //~va11R~
        int ctrTile=0;                                             //~va11I~
        for (int ii=0;ii<4;ii++)                                   //~va11I~
        	for (int jj=0;jj<9;jj++)                                   //~va11I~
            	if (PdupCtr[ii][jj]!=0)                               //~va11I~
                	ctrTile++;                                     //~va11I~
        if (Dump.Y) Dump.println("UARonValue.ronTestSub ctrTile="+ctrTile);//~va11I~
//      if (ctrTile<HANDCTR)                                       //~va11R~
//      	testSkip1314NoPair=true;                               //~va11R~
        testDupCtr=PdupCtr;                                        //~va11I~
        testDupCtrAll=PdupCtrAll;                                  //~va11I~
        testSwAllHand=PswAllHand;                                  //~va11I~
        testCtrAnkan=PctrAnkan;                                    //~va11I~
        testRonType=PronType; testRonNumber=PronNumber;            //~va11I~
        testPairEarth=PpairEarth;                                 //~va11I~
        getValue(PLAYER_YOU);	//left:amt,top:yaku,right:rank,bottom:point//~va11R~
        if (Dump.Y) Dump.println("UARonValue.ronTestSub ronResult="+ronResult.toString()+"ronType="+PronType+",ronNumber="+PronNumber+"\n,dupCtr="+Utils.toString(PdupCtr));//~va11I~
        TestOption.option2 |= TestOption.TO2_RONVALUE_TEST;        //~va11I~
        TestOption.option2 &= ~TestOption.TO2_RONVALUE_TESTSUB;    //~va11I~
        return ronResult;                                          //~va11R~
    }                                                              //~va11I~
	//*************************************************************************//~va11M~
//  private boolean chkCompleteSub()                               //~va20R~
//  protected boolean chkCompleteSub()                             //~va24R~
    protected boolean chkRonValueSub()                             //~va24I~
    {
        int rank;//~va11M~
    	ronResult=new RonResult(0,0,0,new Rank());                                 //~va11R~
    	swYakuman=false;                                           //~va11I~
		swTanyao=false;                                            //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkRonValueSub dupctr="+Utils.toString(dupCtr));//~va24R~
//        if (!swCheckRonable)   //ronchk not done                 //~va11R~
//        {                                                        //~va11R~
//            swRonnable=chkRonnable();                            //~va11R~
//            if (Dump.Y) Dump.println("UARonValue.chkRonValueSub swRonnable="+swRonnable);//~va24R~
//        }                                                        //~va11R~
        UARDT=new UARonDataTree(this,player,dupCtr);	//chk dora //~va11R~
        if (chk13NoPair())  //13/14 avoid dup with tenho,chiiho    //~va11R~
        {                                                          //~va11R~
            UARDT.setResultYakuman(ronResult);                     //~va11R~
            return true;                                           //~va11R~
        }                                                          //~va11R~
//      if (swChkRank)	//1han constraint chk                      //~va11R~
//      {                                                          //~va11R~
//      	if (!swCheckRonable)   //ronchk not done               //~va11I~//~va1aR~
//          {                                                      //~va11I~//~va1aR~
                swRonnable=chkRonnable();                          //~va11I~
                if (Dump.Y) Dump.println("UARonValue.chkRonValueSub swChkRank="+swChkRank+",swRonnable="+swRonnable);//~va24R~
                if (!swRonnable)                                   //~va11I~
                {                                                  //~va11I~
                    ronResult.swRonChkErr=true;                    //~va11R~
                    return false;                                  //~va11I~
                }                                                  //~va11I~
//          }                                                      //~va11I~//~va1aR~
//      }                                                          //~va11R~
//        if (!swRonnable)                                         //~va11R~
//            return false;                                        //~va11R~
        chkEnvironmentYaku();       //reach,tenho... regardless hand pattern//~va11R~
        if (swYakuOtherEnvironment)  //rank regardless hand pattern//~va11R~
        {                                                          //~va11I~
            if (swChkRank)  //1han constraint chk                  //~va11R~
            {                                                      //~va11R~
                if (Dump.Y) Dump.println("UARonValue.chkRonValueSub Chkrank rc=true by swYakuOtherEnvironment longRankOther="+UARDT.longRankOther.toStringName());//~va24R~
                ronResult.han=1;    //at least                     //~va11R~
                return true;                                       //~va11R~
            }                                                      //~va11R~
        }                                                          //~va11I~
//      if (!swCheckRonable)   //ronchk not done                   //~va11R~
//      {                                                          //~va11R~
//          swRonnable=chkRonnable();                              //~va11R~
//          if (Dump.Y) Dump.println("UARonValue.chkRonValueSub swRonnable="+swRonnable);//~va24R~
//          if (!swRonnable)                                       //~va11R~
//              return false;                                      //~va11R~
//      }                                                          //~va11R~
        swTanyao=UARDT.chkTanyao();                                //~va11I~
        sw7P=chk7Pair();                                           //~va11R~
        if (!sw7P)                                                 //~va11R~
        {                                                          //~va11R~
            chk13All();                                            //~va11R~
        }                                                          //~va11R~
//      boolean swRonChkDone=swCheckRonable;                       //~va11R~
//        if (!swYakuman)    //chk already 13all,13/14nopair       //~va11R~
//            if (!swCheckRonable)   //ronchk not done             //~va11R~
//            {                                                    //~va11R~
//                swRonnable=chkRonnable();                        //~va11R~
//                if (Dump.Y) Dump.println("UARonValue.chkRonValueSub swRonnable="+swRonnable);//~va24R~
//                if (!swRonnable)                                 //~va11R~
//                    return false;                                //~va11R~
//                swRonChkDone=true;                               //~va11R~
//            }                                                    //~va11R~
    	chkYakumanStandard();                                      //~va11I~
        if (swYakuman)                                             //~va11I~
        {                                                          //~va11I~
//            if (!swRonChkDone)   //ronchk not done               //~va11R~
//            {                                                    //~va11R~
//                swRonnable=chkRonnable();                        //~va11R~
//                if (Dump.Y) Dump.println("UARonValue.chkRonValueSub swYakuman do ronChk");//~va24R~
//                if (!swRonnable)                                 //~va11R~
//                    return false;                                //~va11R~
//                swRonChkDone=true;                               //~va11R~
//            }                                                    //~va11R~
	    	UARDT.setResultYakuman(ronResult);                      //~va11R~
            return true;                                           //~va11I~
        }                                                          //~va11I~
		boolean rc=chkYakuStandard();                              //~va11R~
        if (rc | sw7P)                                             //~va11R~
        {                                                          //~va11I~
        	UARDT.getAmmount(ronResult,rc/*makePair rc*/);         //~va11R~
        }                                                          //~va11I~
        rc|=sw7P;                                                  //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkRonValueSub rc="+rc+",ronResult="+ronResult.toString());//~va24R~
        return rc;                                                 //~va11I~
    }                                                              //~9C11I~//~va11M~
    //*************************************************************************//~va11I~
    //*get ctr of each suit                                        //~va11I~
    //*************************************************************************//~va11I~
    protected void addEarth()                                      //~va11R~
    {                                                              //~va11I~
        int type,num;                                              //~va11I~
    //*******************************                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.addEarth tdSSEarth="+TileData.toString(tdssEarth));//~va11R~
        dupCtrAll=Utils.cloneArray2(dupCtr);     //including earth //~va11I~
//      swAllHand=true;                                            //~va11R~
        int ctrEarth=0;                                            //~va11I~
        ctrAnkan=0;                                                //~va1aI~
        for (int ii=0;ii<tdssEarth.length;ii++)                    //~va11I~
        {                                                          //~va11I~
        	if (tdssEarth[ii]==null)                               //~va11I~
            	break;                                             //~va11I~
            for (TileData td : tdssEarth[ii])                      //~va11I~
            {                                                      //~va11I~
                type = td.type;                                    //~va11I~
                num = td.number;                                   //~va11I~
                dupCtrAll[type][num]++;                            //~va11I~
//              if ((td.flag & TDF_KAN_TAKEN)==0)                  //~va11R~
//              	swAllHand=false;                               //~va11R~
                if ((td.flag & TDF_KAN_TAKEN)==0)                  //~va11I~
                	ctrAnkan++;                                    //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonValue.addEarth dupCtr="+ Utils.toString(dupCtr));//~va11R~
        if (Dump.Y) Dump.println("UARonValue.addEarth dupCtrAll="+ Utils.toString(dupCtrAll));//~va11R~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    //*before ronTest for test                                     //~va11I~
    //******************************************                   //~va11I~
    private void getPairEarth()                                    //~va11I~
    {                                                              //~va11I~
        pairEarth=UARonData.getPairEarth(tdssEarth);                        //~va11I~
    }                                                              //~va11I~
//    //*************************************************************************//~va11R~
//    private boolean setHigher(RonResult Pvalue)                  //~va11R~
//    {                                                            //~va11R~
//        boolean rc=false;                                        //~va11R~
//        Pvalue.amt=calcPoint(Pvalue.han,Pvalue.point);           //~va11R~
//        if (ronResult.amt<Pvalue.amt)                            //~va11R~
//        {                                                        //~va11R~
//            ronResult.update(Pvalue);                            //~va11R~
//            rc=true;                                             //~va11R~
//        }                                                        //~va11R~
//        if (Dump.Y) Dump.println("UARonValue.setHigher rc="+rc+",Pvalue="+Pvalue.toString()+",ronResult="+ronResult.toString());//~va11R~
//        return rc;                                               //~va11R~
//    }                                                            //~va11R~
	//*************************************************************************//~va11I~
//    private boolean isRonTile(TileData Ptd)                      //~va11R~
//    {                                                            //~va11R~
//        boolean rc=Ptd.type==tdRonLast.type                      //~va11R~
//                && Ptd.number==tdRonLast.number;                    //+va11I~//~va11R~
//        if (Dump.Y) Dump.println("UARonValue.isRonTile rc="+rc+",Ptd="+Ptd.toString()+",ronTile="+tdRonLast.toString());//~va11R~
//        return rc;                                               //~va11R~
//    }                                                            //~va11R~
//    //*************************************************************************//~va11R~
//    public boolean isRonTile(int Ptype,int Pnumber)              //~va11R~
//    {                                                            //~va11R~
//        boolean rc=Ptype==tdRonLast.type                         //~va11R~
//                && Pnumber==tdRonLast.number;                    //~va11R~
//        if (Dump.Y) Dump.println("UARonValue.isRonTile rc="+rc+",Ptype="+Ptype+",Pnumber="+Pnumber+",ronTile="+tdRonLast.toString());//~va11R~
//        return rc;                                               //~va11R~
//    }                                                            //~va11R~
	//*************************************************************************//~va11I~
    private int calcPoint(int Phan,int Pfu)                        //~va11I~
    {                                                              //~va11I~
    	int pt=CompReqDlg.calcPointBase(Pfu,Phan,swRankMUp);        //~va11I~
        if (Dump.Y) Dump.println("UARonValue.calcPoint han="+Phan+",fu="+Pfu+",rc="+pt);//~va11R~
        return pt;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~9C11I~//~va11M~
    private boolean chk7Pair()                                      //~9C11I~//~va11R~
    {                                                              //~9C11I~//~va11M~
        boolean rc=super.is7Pair();                                //~va11M~
//      if (rc)                                                    //~va11R~
//          evaluate7Pair();                                       //~va11R~
        if (Dump.Y) Dump.println("UARonValue.chk7Pair rc="+rc);    //~va11R~
        return rc;
    }                                                              //~9C11I~//~va11M~
	//*************************************************************************//~va11I~
	//*from UARDT.getAmmount                                       //~va11I~
	//*************************************************************************//~va11I~
//  private void evaluate7Pair()                                   //~va11R~
    public void evaluate7Pair(int PotherRank,Rank PlongRank7)      //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.evaluate7Pair sw7Pair50="+sw7Pair50+",is7Pair30="+sw7Pair30+",PotherRank="+PotherRank+",Rank7="+PlongRank7.toStringName());//~va11R~
        int han,fu;                                                //~va11I~
        if (sw7Pair50)                                             //~va11I~
        {                                                          //~va11I~
        	han=RANK_7PAIR; fu=POINT_7PAIR;                        //~va11R~
        }                                                          //~va11I~
        else                                                       //~va11I~
        if (sw7Pair30)                                             //~va11I~
        {                                                          //~va11I~
        	han=RANK_7PAIR2; fu=POINT_7PAIR2_LARGE;                //~va11R~
        }                                                          //~va11I~
        else  //25fu 2han                                          //~va11I~
        {                                                          //~va11I~
        	han=RANK_7PAIR2; fu=POINT_7PAIR2;                      //~va11R~
        }                                                          //~va11I~
//      han=addYaku7Pair(han);                                     //~va11R~
//      if (!swYakuman)                                            //~va11R~
//      {                                                          //~va11R~
			han+=PotherRank;                                       //~va11I~
        	int amt=calcPoint(han,fu);                             //~va11R~
        	UARDT.setValue7Pair(amt,han,fu,PlongRank7);            //~va11R~
//      }                                                          //~va11R~
        if (Dump.Y) Dump.println("UARonValue.evaluate7Pair exit"); //~va11R~
    }                                                              //~va11I~
//    //*************************************************************************//~va11R~
//    private int addYaku7Pair(int Prank)                          //~va11R~
//    {                                                            //~va11R~
//        int rank=Prank;                                          //~va11R~
//        if (!is7PairAllPin28())                                  //~va11R~
//        {                                                        //~va11R~
//            int add=chkYakuStandard();  //tanyao etc             //~va11R~
//            if (!swYakuman)                                      //~va11R~
//                rank+=add;                                       //~va11R~
//        }                                                        //~va11R~
//        if (Dump.Y) Dump.println("UARonValue.addYaku7Pair rank="+Prank+",rc="+rank);//~va11R~
//        return rank;                                             //~va11R~
//    }                                                            //~va11R~
	//*************************************************************************//~va11I~
    private void addYakuman(int Pyaku,boolean PswDouble)          //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.addYakuman yaku="+Pyaku+",dowble="+PswDouble);//~va11I~
        UARDT.addYakuman(Pyaku,PswDouble);                         //~va11I~
        swYakuman=true;                                            //~va11I~
        if ((TestOption.option2 & TestOption.TO2_CHKRANK)!=0)      //~va11I~
        {                                                          //~va11I~
        	UView.showToast("addYakuman ="+Rank.toStringName(Pyaku)+",double="+PswDouble);//~va11I~
        }                                                          //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~9C11I~//~va11M~
	//*Kokushi                                                     //~0202I~//~va11M~
	//*************************************************************************//~0202I~//~va11M~
    protected boolean chk13All()                                      //~9C11I~//~va11M~
    {                                                              //~9C11I~//~va11M~
        if (Dump.Y) Dump.println("UARonValue.chk13All swTanyao="+swTanyao);              //~9C11R~//~0925R~//~va11R~
        if (swTanyao)                                              //~va11I~
        	return false;                                          //~va11I~
        boolean rc=super.is13All();                                //~va11M~
        if (rc)                                                    //~va11M~
        {                                                          //~va11M~
            boolean swAll=dupCtr[ronType][ronNumber]==2;           //~va11R~
	        boolean swDouble=swAll && sw13WaitAll;          //~va11I~
            if (swDouble && RuleSettingYaku.is13WaitAll())         //~va11R~
		    	addYakuman(RYAKU_13ALL2,swDouble/*double*/);       //~va11R~
            else                                                   //~va11I~
		    	addYakuman(RYAKU_13ALL,swDouble/*double*/);        //~va11I~
        }                                                          //~va11M~
        if (Dump.Y) Dump.println("UARonValue.chk13All rc="+rc+",ronType="+ronType+",ronNumber="+ronNumber+",sw13WaitAll="+sw13WaitAll);//~va24R~
        return rc;                                                 //~9C11I~//~va11M~
    }                                                              //~9C11I~//~va11M~
	//*************************************************************************//~9C11I~//~va11M~
    protected boolean chk13NoPair()                                   //~9C11I~//~va11M~
    {                                                              //~9C11I~//~va11M~
        if (Dump.Y) Dump.println("UARonValue.chk13NoPair sw1stTake="+sw1stTake+",swTanyao="+swTanyao+",ctrTileAll="+ctrTileAll+",Rule.sw13Nopair="+sw13NoPair+",Rule.sw14NoPair="+sw14NoPair);//~va11R~
        if (!sw1stTake)                                            //~va11R~
        	return false;                                          //~va11I~
//      if ((TestOption.option2 & (TestOption.TO2_RONVALUE_TEST|TestOption.TO2_RONVALUE_TESTSUB))!=0)//~va11R~
//      	if (testSkip1314NoPair)                                //~va11R~
///         	return false;                                      //~va11R~
    	if (!RuleSettingYaku.isYakuman13NoPair())                      //~va11I~
        	return false;                                          //~va11I~
        if (swTanyao)                                              //~va11I~
        	return false;                                          //~va11I~
        boolean rc=super.is13NoPair();                             //~va11M~
        if (rc)                                                    //~va11M~
        {                                                          //~va11I~
	        if (super.is13All())	//kokusi can be mixed tenho/chiho//~va11R~
            	rc=false;                                          //~va11I~
            else                                                   //~va11I~
            {                                                      //~va11I~
        	if (swIs14NoPair)                                      //~va11I~
	    		addYakuman(RYAKU_14NOPAIR,false/*double*/);        //~va11I~
            else                                                   //~va11I~
	    		addYakuman(RYAKU_13NOPAIR,false/*double*/);        //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonValue.is13NoPair rc="+rc);    //~9C11I~//~0925R~//~va11M~
        return rc;                                                 //~9C11I~//~va11M~
    }                                                              //~9C11I~//~va11M~
	//*************************************************************************//~va11I~
    private boolean chkYakuStandard()                              //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkYakuStandard");    //~va11I~
//  	if (chkYakumanStandard())                                  //~va11R~
//      	return true;	//no need calc ammount                 //~va11R~
    	boolean rc=makePairing();                                  //~va11R~
//		int rank=subUARV.chkYakuStandard(UARDT,dupCtr,dupCtrAll);  //~va11R~
//      if (Dump.Y) Dump.println("UARonValue.chkYakuStandard rc="+rank);//~va11R~
//      return rank;                                               //~va11R~
		return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11M~
    private boolean makePairing()                                  //~va11M~
    {                                                              //~va11M~
        if (Dump.Y) Dump.println("UARonValue.makeParing");         //~va11M~
        boolean rc=false;                                          //~va11M~
        posPillow=new Point(0,0);                                  //~va11M~
        for(;;)                                                    //~va11M~
        {                                                          //~va11M~
        	Point p=selectPillow();	//->UARonChk,type and number   //~va11R~
            if (p==null)                                           //~va11M~
            	break;                                             //~va11M~
            UARonData uardTop=UARDT.addPillow(p);                  //~va11R~
            dropPillow(p);                                         //~va11M~
        	if (Dump.Y) Dump.println("UARonValue.makePairing before makepairing dupCtr="+Utils.toString(dupCtr));//~va11I~
                                                                   //~va11I~
            if (!makePairing(uardTop))	//UARonChk,makePairing,callback to uardTop.setPaired()//~va11R~
            {                                                      //~va11M~
				UARDT.delPillow();                             //~va11M~
            }                                                      //~va11M~
            else                                                   //~va11I~
            	rc=true;                                           //~va11I~
            restorePillow(p);                                      //~va11M~
        	if (Dump.Y) Dump.println("UARonValue.makePairing after makepairing dupCtr="+Utils.toString(dupCtr));//~va11R~
        }                                                          //~va11M~
        if (Dump.Y) Dump.println("UARonValue.makePairing return rc="+rc);//~va11R~
        return rc;                                                 //~va11M~
    }                                                              //~va11M~
	//*************************************************************************//~va11I~
	//*other than 13all,13nopair,7pairPin28                        //~va11I~
    //AllJi,all19,3Dragon,4kan                                     //~va11I~
	//*4anko(+13wait),9gate(+9wait,-not Man),4Wind(+Big),All Green (-no GreenDragon)//~va11I~
	//*************************************************************************//~va11I~
    private boolean chkYakumanStandard()                           //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkYakumanStandard"); //~va11R~
        if (chkAllHonor())	//tsuiiso                              //~va11R~
        	;                                                      //~va11R~
        else                                                       //~va11I~
        	chkAll19();     //chinrao                              //~va11R~
                                                                   //~va11I~
        if (chk3Dragon())   //daisangen                            //~va11R~
        	;                                                      //~va11R~
        else                                                       //~va11I~
        if (chkAllGreen())  //ryuiiso                              //~va11R~
        	;                                                      //~va11R~
        else                                                       //~va11I~
        {                                                          //~va11I~
        	chk4Wind();     //big/small susiho                     //~va11R~
	        chk9gate();     //churenpaoto                          //~va11R~
        	chk7PairAllPin28();                                    //~va11I~
        }                                                          //~va11I~
        chk4Kan();          //sukantsu                             //~va11R~
        chk4Same();         //4anko                                //~va11R~
        if (Dump.Y) Dump.println("UARonValue.chkYakumanStandard rc="+swYakuman);//~va11R~
        return swYakuman;                                          //~va11R~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private boolean chkAllHonor()        //tsuiisou                //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkAllHonor swTanyao="+swTanyao);//~va11I~
        if (swTanyao)                                              //~va11I~
        	return false;                                          //~va11I~
    	boolean rc=true;                                           //~va11M~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~va11I~
	        for (int jj=0;jj<PIECE_NUMBERCTR;jj++)                 //~va11R~
	        	if (dupCtrAll[ii][jj]!=0)                          //~va11R~
            	{                                                  //~va11I~
            		rc=false;                                      //~va11I~
            		break;                                         //~va11I~
            	}                                                  //~va11I~
        if (rc)                                                    //~va11I~
	    	addYakuman(RYAKU_ALLHONOR,false/*double*/);            //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkAllHonor rc="+rc); //~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private boolean chkAll19()                                      //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkAll19 swTanyao="+swTanyao);//~va11I~
        if (swTanyao)                                              //~va11I~
        	return false;                                          //~va11I~
    	boolean rc=true;                                           //~va11I~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~va11I~
	        for (int jj=1;jj<PIECE_NUMBERCTR-1;jj++)               //~va11R~
	        	if (dupCtrAll[ii][jj]!=0)                          //~va11R~
            	{                                                  //~va11I~
            		rc=false;                                      //~va11I~
            		break;                                         //~va11I~
            	}                                                  //~va11I~
        if (rc)                                                    //~va11I~
	        for (int jj=0;jj<PIECE_NOTNUM_CTR;jj++)                //~va11I~
	        	if (dupCtrAll[TT_JI][jj]!=0)                       //~va11R~
            	{                                                  //~va11I~
            		rc=false;                                      //~va11I~
            		break;                                         //~va11I~
            	}                                                  //~va11I~
        if (rc)                                                    //~va11I~
	    	addYakuman(RYAKU_ALL19,false/*double*/);               //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkAll19 rc="+rc);    //~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private boolean chk3Dragon()                                    //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk3Dragon swTanyao="+swTanyao);//~va11I~
        if (swTanyao)                                              //~va11I~
        	return false;                                          //~va11I~
    	boolean rc=dupCtrAll[TT_JI][TT_4ESWN_CTR+TT_3WGR_W]>=3     //~va11R~
    	        && dupCtrAll[TT_JI][TT_4ESWN_CTR+TT_3WGR_G]>=3     //~va11R~
    	        && dupCtrAll[TT_JI][TT_4ESWN_CTR+TT_3WGR_R]>=3;    //~va11R~
        if (rc)                                                    //~va11I~
	    	addYakuman(RYAKU_3DRAGON,false/*double*/);             //~va11I~
        if (Dump.Y) Dump.println("UARonValue.is3Dragon rc="+rc);   //~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private boolean chkAllGreen()                                   //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk3AllGreen swTanyao="+swTanyao);//~va11I~
    	boolean rc=true;                                           //~va11I~
        for (int jj=0;jj<PIECE_NUMBERCTR;jj++)	//man              //~va11I~
            if (dupCtrAll[TT_MAN][jj]!=0)                          //~va11R~
            {                                                      //~va11I~
                rc=false;                                          //~va11I~
                break;                                             //~va11I~
            }                                                      //~va11I~
        if (rc)                                                    //~va11I~
            for (int jj=0;jj<PIECE_NUMBERCTR;jj++)   //pin         //~va11I~
                if (dupCtrAll[TT_PIN][jj]!=0)                      //~va11R~
                {                                                  //~va11I~
                    rc=false;                                      //~va11I~
                    break;                                         //~va11I~
                }                                                  //~va11I~
        if (rc)                                                    //~va11I~
            for (int jj=0;jj<PIECE_NUMBERCTR;jj++)    //sou        //~va11I~
                if (dupCtrAll[TT_SOU][jj]!=0)                      //~va11R~
                {                                                  //~va11I~
                	if (jj==0 || jj==4 || jj==6 || jj==8)            //~va11R~
                    {                                              //~va11I~
                    	rc=false;                                  //~va11I~
                    	break;                                     //~va11I~
                    }                                              //~va11I~
                }                                                  //~va11I~
        boolean swDragon=false;                                    //~va11I~
        if (rc)                                                    //~va11I~
            for (int jj=0;jj<PIECE_NOTNUM_CTR;jj++)    //sou       //~va11I~
                if (dupCtrAll[TT_JI][jj]!=0)                       //~va11I~
                {                                                  //~va11I~
                	if (jj==TT_4ESWN_CTR+TT_3WGR_G)                //~va11I~
                    	swDragon=true;                             //~va11I~
                    else                                           //~va11I~
                    {                                              //~va11I~
                    	rc=false;                                  //~va11I~
                    	break;                                     //~va11I~
                    }                                              //~va11I~
                }                                                  //~va11I~
        if (rc)                                                    //~va11I~
        {                                                          //~va11I~
            if (!swDragon)                                         //~va11I~
            	if (!swAllGreenNoDragon)                           //~va11I~
        	    	rc=false;                                      //~va11I~
	        if (Dump.Y) Dump.println("UARonValue.chkAllGreen swDragon="+swDragon+",swAllGreenNoDragon="+swAllGreenNoDragon);//~va11I~
        }                                                          //~va11I~
        if (rc)                                                    //~va11I~
	    	addYakuman(RYAKU_ALLGREEN,false/*double*/);            //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkAllGreen rc="+rc+",dupCtrAll="+Utils.toString(dupCtrAll));//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private  boolean chk4Wind()                                     //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk4Wind swTanyao="+swTanyao);//~va11I~
        if (swTanyao)                                              //~va11I~
        	return false;                                          //~va11I~
    	int rc=0,ctrPillow=0,ctrNonPillow=0;                       //~va11I~
        for (int jj=0;jj<TT_4ESWN_CTR;jj++)      //ji              //~va11I~
        {                                                          //~va11I~
            int ctr=dupCtrAll[TT_JI][jj];                          //~va11R~
            if (ctr==2)                                            //~va11I~
            	ctrPillow++;                                       //~va11I~
            else                                                   //~va11I~
            if (ctr>2)                                             //~va11I~
            	ctrNonPillow++;                                     //~va11I~
            else                                                   //~va11I~
            	break;                                             //~va11I~
        }                                                          //~va11I~
        if (ctrNonPillow==4)                                        //~va11I~
        	rc=sw4WindDouble ? 2 :1;                               //~va11I~
        else                                                       //~va11I~
        if (ctrNonPillow==3 && ctrPillow==1)                        //~va11I~
        	rc=1;                                                  //~va11I~
        if (rc!=0)                                                    //~va11I~
        {                                                          //~va11I~
	        if (ctrNonPillow==4)                                   //~va11I~
		    	addYakuman(RYAKU_4WIND,rc==2/*double*/);           //~va11R~
            else                                                   //~va11I~
		    	addYakuman(RYAKU_4WINDSMALL,rc==2/*double*/);      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk4Wind rc="+rc+",sw4WindDouble="+sw4WindDouble+",ctrPillow="+ctrPillow+",ctrNonPillow="+ctrNonPillow);//~va24R~
        return rc!=0;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private boolean chk9gate()                                         //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk9Gate swTanyao="+swTanyao+",swAllHand="+swAllInHand);//~va11R~
        if (swTanyao)                                              //~va11I~
        	return false;                                          //~va11I~
    	if (!swAllInHand)                                          //~va11R~
        	return false;                                              //~va11I~
    	int rc=0;                                                  //~va11R~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~va11I~
        {                                                          //~va11I~
        	if (ii!=TT_MAN && sw9GateManOnly)                     //~va11I~
            	continue;                                          //~va11I~
            int ctrOver=0;                                         //~va11R~
            boolean swWait9=false;                                  //~va11R~
            boolean swErr=false;                                   //~va11I~
            rc=0;                                                  //~va11I~
	        for (int jj=0;jj<PIECE_NUMBERCTR;jj++)                 //~va11R~
            {                                                      //~va11I~
            	int ctr=dupCtr[ii][jj];                            //~va11I~
                int ctr2=ctr-((ii==ronType && jj==ronNumber) ? 1 : 0);//~va11R~
                if (jj==0 || jj==PIECE_NUMBERCTR-1)                //~va11I~
                {                                                  //~va11I~
                    if (ctr<3)    //1 or 9 is 3 or 4               //~va11I~
                    {                                              //~va11M~
                    	swErr=true;                                //~va11M~
                    	break;                                     //~va11M~
                    }                                              //~va11M~
                	if (ctr==4 && ctr2==3)                         //~va11R~
                		swWait9=true;                              //~va11R~
                    if (ctr==4)                                    //~va11I~
                    	ctrOver++;                                 //~va11I~
                }                                                  //~va11I~
                else                                               //~va11I~
                {                                                  //~va11I~
                    if (ctr!=1 && ctr!=2) //2 or 8 is 1 or 2       //~va11R~
                    {                                              //~va11I~
                    	swErr=true;                                //~va11I~
                    	break;                                     //~va11I~
                    }                                              //~va11I~
                	if (ctr==2 && ctr2==1)                         //~va11R~
                		swWait9=true;                              //~va11R~
                    if (ctr==2)                                    //~va11I~
                    	ctrOver++;                                 //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
	        if (Dump.Y) Dump.println("UARonValue.chk9Gate ii="+ii+",swErr="+swErr+",ctrOver="+ctrOver+",sw9GateDoublde="+sw9GateDouble);//~va11R~
            if (!swErr && ctrOver==1)                              //~va11R~
            {                                                      //~va11M~
            	rc=1;                                              //~va11M~
                if (swWait9 && sw9GateDouble)                      //~va11M~
                	rc=2;                                          //~va11M~
                break;                                             //~va11M~
            }                                                      //~va11M~
        }                                                          //~va11I~
        if (rc!=0)                                                    //~va11I~
        	if (rc==2)                                             //~va11I~
		    	addYakuman(RYAKU_9GATE2,true/*double*/);           //~va11I~
            else                                                   //~va11I~
	    		addYakuman(RYAKU_9GATE,false/*double*/);             //~va11R~
        if (Dump.Y) Dump.println("UARonValue.chk9Gate rc="+rc+",sw9GateDouble="+sw9GateDouble+",sw9GateManOnly="+sw9GateManOnly);//+va24R~
        return rc!=0;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************
    private boolean chk4Kan()                                       //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk4Kan swTanyao="+swTanyao+",dupctr="+ Utils.toString(dupCtr)+"\n,DupCtrAll="+Utils.toString(dupCtrAll));//~va11R~
    	boolean rc,swErr=false;                                                //~va11I~
        int ctrKan=0;                                              //~va11I~
        for (int ii=0;ii<PIECE_TYPECTR_ALL;ii++)                    //~va11I~
        {                                                          //~va11I~
	        for (int jj=0;jj<PIECE_NUMBERCTR;jj++)                 //~va11R~
            {                                                      //~va11I~
            	int ctr=dupCtrAll[ii][jj];                         //~va11R~
                if (ctr==0)                                        //~va11I~
                	continue;                                      //~va11I~
            	if (ctr==4 && dupCtr[ii][jj]==0)	//on Earth     //~va11R~
                	ctrKan++;                                      //~va11I~
                else                                               //~va11I~
            	if (ctr!=2)     //pillow                           //~va11I~
                {                                                  //~va11I~
                	swErr=true;                                    //~va11I~
                	break;                                         //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
	        if (swErr)                                             //~va11R~
            	break;                                             //~va11I~
        }                                                          //~va11I~
        rc=ctrKan==4;                                              //~va11I~
        if (rc)                                                    //~va11I~
	    	addYakuman(RYAKU_4KAN,false/*double*/);                //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk4Kan rc="+rc+",swErr="+swErr+",ctrKan="+ctrKan);//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
	//*4anko                                                       //~va11I~
	//*************************************************************************//~va11I~
    private boolean chk4Same()                                     //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk4Same swTaken="+swTaken+",swAllInHand="+swAllInHand+",ronType="+ronType+",ronNumber="+ronNumber);//~va11R~//~va1aR~
    	if (!swAllInHand)                                          //~va11R~
        	return false;                                          //~va11I~
    	int rc;                                                    //~va11I~
        int ctrAnko=0;                                             //~va1aR~
        boolean swDouble=false;                                    //~va11I~
        for (int ii=0;ii<PIECE_TYPECTR_ALL;ii++)                   //~va11I~
        {                                                          //~va11I~
        	boolean swErr=false;                                   //~va11I~
	        for (int jj=0;jj<PIECE_NUMBERCTR;jj++)                 //~va11R~
            {                                                      //~va11I~
            	int ctr=dupCtrAll[ii][jj];                         //~va11R~//~va1aR~
            	if (ctr>=3)                                        //~va11I~//~va1aR~
                {                                                  //~va11I~
                	if (ii==ronType && jj==ronNumber)              //~va11I~
                    {                                              //~va11I~
                    	if (swTaken)                               //~va11I~
		                	ctrAnko++;                             //~va11R~
                    }                                              //~va11I~
                    else                                           //~va11I~
		                ctrAnko++;                                 //~va11I~
                }                                                  //~va11I~
                else                                               //~va11I~
            	if (ctr==2)     //pillow                           //~va11R~
                	swDouble=(ii==ronType && jj==ronNumber);        //~va11I~
                else                                               //~va11I~
                if (ctr!=0)                                        //~va11I~
                {                                                  //~va11I~
                	swErr=true;                                    //~va11I~
                	break;                                         //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
	        if (swErr)                                             //~va11I~
            	break;                                             //~va11I~
        }                                                          //~va11I~
        rc=ctrAnko==4 ? 1 : 0;                                     //~va11I~
        if (rc!=0)                                                    //~va11I~
        {                                                          //~va11I~
            if (!RuleSettingYaku.is4SameDouble())                  //~va11R~
                swDouble=false;                                    //~va11I~
            if (swDouble)                                          //~va11I~
		    	addYakuman(RYAKU_4SAME2,swDouble/*double*/);       //~va11R~
            else                                                   //~va11I~
		    	addYakuman(RYAKU_4SAME,swDouble/*double*/);        //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk4Same rc="+rc+",ctrAnko="+ctrAnko+",swDouble="+swDouble+",dupCtrAll="+Utils.toString(dupCtrAll));//~va11R~
        return rc!=0;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11M~
    private boolean chk7PairAllPin28()                             //~va11R~
    {                                                              //~va11M~
    	if (!RuleSettingYaku.isYakumanChariot())                       //~va11I~
        	return false;                                          //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chk7PairAllPin28 swTanyao="+swTanyao+",dupctr="+Utils.toString(dupCtr));//~va11I~
        if (!swTanyao)                                             //~va11R~
        	return false;                                          //~va11M~
    	if (!swAllInHand)                                          //~va11R~
        	return false;                                          //~va11I~
    	boolean rc=true;                                           //~va11M~
        for (int ii=1;ii<8;ii++)     //num:2--8                    //~va11M~
	        if (dupCtr[TT_PIN][ii]!=2)                             //~va11M~//~va1aR~
            {                                                      //~va11M~
            	rc=false;                                          //~va11M~
            	break;                                             //~va11M~
            }                                                      //~va11M~
        if (rc)                                                    //~va11M~
        	addYakuman(RYAKU_7PAIR28,false/*double*/);             //~va11M~
        if (Dump.Y) Dump.println("UARonValue.chk7PairPin28 rc="+rc);//~va11I~
        return rc;                                                 //~va11M~
    }                                                              //~va11M~
	//*************************************************************************//~va11I~
	//*already chked ronnable for also 13/noPair etc illeagal format//~va11I~
	//*************************************************************************//~va11I~
	private void chkEnvironmentYaku()                              //~va11R~
    {                                                              //~va11I~
	    swYakuOtherEnvironment=false;                              //~va11I~
//  	AG.aUARon.chkEnvironmentYaku(swAllInHand);                 //~va11R~
    	AG.aUARon.chkEnvironmentYaku(swAllInHand,tdRonLast);       //~va11I~
		if (Dump.Y) Dump.println("UARonValue.chkEnvironmentYaku return swYakuOtherEnvironment="+swYakuOtherEnvironment);//~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
	//*callback from CompReqDlg.chkTimingYakuman                   //~va11R~
	//*************************************************************************//~va11I~
	public void addTimingYakuman(int Pyakuman,int Prank,int Pamt) //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonDataValue.addTimingYakuman Pyakuman="+Pyakuman+",rank="+Prank+",amt="+Pamt);//~va11I~
    	if (Pyakuman==RYAKU_CHILDRON)                              //~va11I~
        {                                                          //~va11I~
//            if (Pamt==0)    //yakuman                            //~va11R~
                  addYakuman(Pyakuman,Prank==2);                   //~va11R~
//            else                                                 //~va11R~
//                UARDT.addSpecialValue(Pyakuman,Prank,Pamt);      //~va11R~
        }                                                          //~va11I~
        else                                                       //~va11I~
    	if (Pyakuman==RYAKU_CHILDRON_NY) //renho not yakuman       //~va11I~
            UARDT.addSpecialValue(Pyakuman,Prank,Pamt);            //~va11I~
        else                                                       //~va11I~
        	addYakuman(Pyakuman,Prank==2);                         //~va11R~
	    swYakuOtherEnvironment=true;    //retun without getAmmount if chkRank//~va11I~
        if ((TestOption.option2 & TestOption.TO2_CHKRANK)!=0)      //~va11I~
        {                                                          //~va11I~
        	UView.showToast("addTimingYakuman ="+Rank.toStringName(Pyakuman)+",rank="+Prank);//~va11I~
        }                                                          //~va11I~
    }                                                              //~va11I~
    //***************************************************************************//~va11I~
    //*from UARon; reach,tsumo,one-shothaitei,hoteichankan,rinshan(not included Dora and WGR)//~va11I~
    //***************************************************************************//~va11I~
    public void addOtherYaku(int Pyaku,int Prank)  //reach,open,...//~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonDataValue.addOtherYaku Pyaku="+Pyaku+",rank="+Prank);//~va11I~
	    UARDT.addYakuOther(Pyaku,Prank);                        //~va11I~
        swYakuOtherEnvironment=true;                               //~va11I~
        if ((TestOption.option2 & TestOption.TO2_CHKRANK)!=0)      //~va11I~
        {                                                          //~va11I~
        	UView.showToast("addOtherYaku Yaku="+Rank.toStringName(Pyaku)+",rank="+Prank);//~va11I~
        }                                                          //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~9C12I~
	//* TODO TEST                                                  //~va11I~
	//*************************************************************************//~va11I~
    static CompReqDlg testDlg; static int testPlayer;              //~va11I~
//  private RonResult ronTest(CompReqDlg PcompReqDlg,int Pplayer)  //~va11R~
    private RonResult ronTest(int Pplayer)                         //~va11I~
    {                                                              //~9C12I~
//    	testDlg=compReqDlg;                                        //~va11R~
 		testPlayer=Pplayer;                                        //~va11I~
//  	CRD=PcompReqDlg;                                           //~va11R~
      	RonResult rc=(new UARonValueTest(this)).ronTestValue();        //~va11R~
      	return rc;//~va11R~
    }                                                              //~9C12I~
    //*************************************************************************//~va11I~
    //* from UARonChk when TO2_RON_TEST                            //~va23R~
    //*************************************************************************//~va11I~
    private static boolean swGetTestHand;                          //~va11I~
    private static int[][] testHand;                               //~va11I~
    public static int[][] getTestHandRonChk()                      //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.getTestHandRonChk");  //~va11I~
	    swGetTestHand=true;                                        //~va11I~
      	RonResult rc=(new UARonValueTest(AG.aUARonValue)).ronTestValue();	//callback ronTestSub//~va11I~
	    swGetTestHand=false;                                       //~va11I~
        return testHand;                                           //~va11I~
    }                                                              //~va11I~
}//class                                                           //~v@@@R~
