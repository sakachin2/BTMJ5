//*CID://+DATER~: update#= 475;                                    //~@@@@R~//~9214R~
//**********************************************************************//~v101I~
package com.btmtest.dialog;                                          //~@@@@R~//~9412R~

import com.btmtest.R;

import static com.btmtest.StaticVars.AG;
import java.util.HashMap;
import java.util.Map;

public class RuleSettingEnum                                       //~@@@@R~//~9404R~
{                                                                  //~0914I~//~@@@@R~
//  public  static final int[]      initialScoreS=new  int[]{20000,25000,26000,27000,30000};//~9408I~//~9416R~//~9421R~
//  public  static final String[]   strInitialScore=new  String[]{"20000","25000","26000","27000","30000"};//~9416I~//~9421R~
    public  static final int[]      initialScoreS=new  int[]{20000,25000,26000,27000,30000};//~9421R~//~9422R~//~9427R~//~0322R~
    public  static final String[]   strInitialScore=new  String[]{"20000","25000","26000","27000","30000"};//~9421R~//~9422R~//~9427R~
    public  static final int DEFAULT_IDX_INITSCORE=1;	//25000//~9416I~//~9421R~
                                                                   //~9512I~
    public  static final int[]      dupPointS=new  int[]{300,1500};//~9512I~
    public  static final String[]   strDupPoint=new  String[]{"300","15000"};//~9512I~
                                                                   //~9421I~
    public  static final int[][] orderPrizeS=new int[][]{{0,0},{5,10},{10,20},{10,30},{20,30}};//~9407R~//~9408R~//~9421I~
                                                                   //~9421I~
    public  static final int PAY_BY_NOT_PENDING=3000;              //~9412I~//~9421I~
                                                                   //~9421I~
    public static final int[] rbIDPendingRank2=new int[]{R.id.rbPendingRank2No,R.id.rbPendingRank2Fix1};//+0330R~
    public static final int PENDING_RANK2_RANK0OK=0;               //+0330R~
    public static final int PENDING_RANK2_FIX1=1;                  //+0330I~
    public static final int PENDING_RANK2_DEFAULT=PENDING_RANK2_FIX1;//~0330I~
                                                                   //~0330I~
    public static final int[] rbIDMinusStopByErr=new int[]{R.id.rbMinusStopByErrNo,R.id.rbMinusStopByErrSprit,R.id.rbMinusStopByErrAll};//~9414I~
    public static final int MINUSSTOP_BYERR_NO=0;                  //~9414I~
    public static final int MINUSSTOP_BYERR_SPRIT=1;               //~9414I~
    public static final int MINUSSTOP_BYERR_ALL=2;                 //~9414M~
                                                                   //~9414I~
    public static final int[] rbIDMinusPay=new int[]{R.id.rbMinusPayYes,R.id.rbMinusPayNoGetPointAll,R.id.rbMinusPayNoGetPointLimited};//~9403I~//~9414R~
    public static final int MINUSPAY_YES=0;                       //~9404I~//~9408M~//~9414R~
    public static final int MINUSPAY_NO_PAY_ALL=1;                //~9404I~//~9408M~//~9414R~
    public static final int MINUSPAY_NO_PAY_LIMIT=2;              //~9404I~//~9408M~//~9414R~
                                                                   //~9416I~
    public static final int[] rbIDRobotPay=new int[]{R.id.rbRobotPaySprit,R.id.rbRobotPayDependingScore};//~9429I~
    public static final int ROBOTPAY_SPRIT=0;                      //~9429R~
    public static final int ROBOTPAY_BYSCORE=1;                    //~9429R~
                                                                   //~9429I~
    public static final int[] rbIDFinalLastDealerNotPending=new int[]{R.id.rbFLNP_Close,R.id.rbFLNP_NextRound};//~9501I~
    public static final int[] rbIDFinalLastDealerAllMinus=new int[]{R.id.rbFLAM_Close,R.id.rbFLAM_NextRound};//~9501I~
    public static final int FINALLAST_CLOSE=0;                     //~9501I~
    public static final int FINALLAST_NEXTROUND=1;                 //~9501I~
                                                                   //~9501I~
    public static final int[] rbIDDrawnMangan=new int[]{R.id.rbDrawnManganNo,R.id.rbDrawnManganAsRon,R.id.rbDrawnManganAsDrawn};//~9505I~
    public static final int DRAWN_MANGAN_NO=0;                     //~9505I~
    public static final int DRAWN_MANGAN_ASRON=1;                  //~9505I~
    public static final int DRAWN_MANGAN_ASDRAWN=2;                //~9505I~
    public static final int DRAWN_MANGAN_DEFAULT=2;                //~9505I~
                                                                   //~9513I~
    public static final int[] rbIDMultiRon=new int[]{R.id.rbMultiRonNo,R.id.rbMultiRonYesDupStickByEswn,R.id.rbMultiRonYesDupStickAll};//~9513I~
    public static final int MULTIRON_NO=0;                         //~9513I~
    public static final int MULTIRON_YES_DUPSTICK_BYESWN=1;        //~9513I~
    public static final int MULTIRON_YES_DUPSTICK_ALL=2;           //~9513I~
                                                                   //~9505I~
    public static final int[] rbIDBirdPayType=new int[]{R.id.rbBirdPayToPlace,R.id.rbBirdPayToEach};//~9602I~
    public static final int BIRD_PAYTYPE_PLACE=0;                  //~9602I~
    public static final int BIRD_PAYTYPE_EACH=1;                   //~9602I~
                                                                   //~9602I~
//  public static final int[] rbIDSuspendOption=new int[]{R.id.rbSuspendOptionGameover,R.id.rbSuspendOptionSuspend,R.id.rbSuspendOptionContinue};//~9820I~//~9822R~//~9B01R~
    public static final int[] rbIDSuspendOption=new int[]{R.id.rbSuspendOptionSuspend,R.id.rbSuspendOptionGameover,R.id.rbSuspendOptionContinue};//~9B01I~
    public static final int SUSPEND_SUSPEND=0;                     //~9820I~//~9B01R~
    public static final int SUSPEND_GAMEOVER=1;                    //~9820I~//~9B01I~
    public static final int SUSPEND_CONTINUE=2;                    //~9822I~
    public static final int[] intsSuspendPenalty=new  int[]{0,3000,6000,12000,24000,48000};//~9820I~
                                                                   //~9820I~
    public static final int[] rbIDDora=new int[]{R.id.rbDoraNext,R.id.rbDoraItself};//~9528I~
    public static final int DORA_NEXT=0;                           //~9528I~
    public static final int DORA_ITSELF=1;                         //~9528I~
    public static final int DORA_DEFAULT=DORA_NEXT;                //~9528I~
    public static final int[] rbIDDoraHidden=new int[]{R.id.rbDoraHiddenNo,R.id.rbDoraHiddenNext,R.id.rbDoraHiddenItself};//~9528I~
    public static final int DORA_HIDDEN_NO=0;                      //~9528I~
    public static final int DORA_HIDDEN_NEXT=1;                    //~9528I~
    public static final int DORA_HIDDEN_ITSELF=2;                  //~9528I~
    public static final int DORA_HIDDEN_DEFAULT=DORA_HIDDEN_NEXT;  //~9528I~
    public static final int[] rbIDKanDora=new int[]{R.id.rbKanDoraNo,R.id.rbKanDoraNext,R.id.rbKanDoraItself};//~9528I~
    public static final int DORA_KANDORA_NO=0;                     //~9528I~
    public static final int DORA_KANDORA_NEXT=1;                   //~9528I~
    public static final int DORA_KANDORA_ITSELF=2;                 //~9528I~
    public static final int DORA_KANDORA_DEFAULT=DORA_KANDORA_NEXT;//~9528I~
    public static final int[] rbIDKanDoraHidden=new int[]{R.id.rbKanDoraHiddenNo,R.id.rbKanDoraHiddenNext,R.id.rbKanDoraHiddenItself};//~9528I~
    public static final int DORA_KANDORA_HIDDEN_NO=0;              //~9528I~
    public static final int DORA_KANDORA_HIDDEN_NEXT=1;            //~9528I~
    public static final int DORA_KANDORA_HIDDEN_ITSELF=2;          //~9528I~
    public static final int DORA_KANDORA_HIDDEN_DEFAULT=DORA_KANDORA_HIDDEN_NEXT;//~9528I~
    public static final int[] rbIDMinKanDoraOpen=new int[]{R.id.rbMinKanDoraOpenExceptRon,R.id.rbMinKanDoraOpenJustDiscarded,R.id.rbMinKanDoraOpenJustNow};//~9529I~
    public static final int DORA_KANDORA_OPEN_EXCEPTRON=0;         //~9529I~
    public static final int DORA_KANDORA_OPEN_JUSTDISCARED=1;      //~9529I~
    public static final int DORA_KANDORA_OPEN_JUSTNOW=2;           //~9529I~
    public static final int DORA_KANDORA_OPEN_DEFAULT=DORA_KANDORA_OPEN_EXCEPTRON;//~9529I~
                                                                   //~9528I~
    public static final int S2P_NO=0;                              //~9416R~
    public static final int S2P_FLOOR=1;                           //~9416I~
    public static final int S2P_CEILING=2;                         //~9416R~
    public static final int S2P_45=3;      //cut <=4, up 5>=       //~9416R~
    public static final int S2P_56=4;      //cut <=5, up 6>=       //~9416R~
    public static final int S2P_PM=5;      //cut plus,up minus     //~9417R~
    public static final int[] rbIDScoreToPoint=new int[]{R.id.rbS2P_No,R.id.rbS2P_Floor,R.id.rbS2P_Ceiling,R.id.rbS2P_45,R.id.rbS2P_56,R.id.rbS2P_PM};//~9416R~//~9417M~
    public static final int[] IDScoreToPoint=new int[]{S2P_NO,S2P_FLOOR,S2P_CEILING,S2P_45,S2P_56,S2P_PM};//~9417I~
                                                                   //~9516I~
    public static final int Y8C_NO=0;                              //~9516I~
    public static final int Y8C_DEALER=1;                          //~9516I~
    public static final int Y8C_ANYONE=2;                          //~9516I~
    public static final int Y8C_STICK=3;                           //~9516I~
    public static final int Y8C_STICK_ANYONE=4;                    //~9517I~
    public static final int Y8C_DEFAULT=Y8C_DEALER;                //~9516I~
    public static final int[] rbs8Continue=new int[]{R.id.rb8ContNo,R.id.rb8ContDealer,R.id.rb8ContOnePlayer,R.id.rb8ContStick,R.id.rb8ContStickAnyOne};//~9516I~//~9517R~
    public static final int[] ID8Continue=new int[]{Y8C_NO,Y8C_DEALER,Y8C_ANYONE,Y8C_STICK,Y8C_STICK_ANYONE};//~9516I~//~9517R~
                                                                   //~9516I~
    public static final int[] rbsYakuFix=new int[]{R.id.rbYakuFixLast,R.id.rbYakuFixFirst};//~9516I~
    public static final int YAKUFIX_LAST=0;                        //~9516I~
    public static final int YAKUFIX_FIRST=1;                       //~9516I~
    public static final int YAKUFIX_DEFAULT=YAKUFIX_LAST;          //~9516I~
                                                                   //~9516I~
    public static final int[] rbsYakuFix2=new int[]{R.id.rbYakuFix2No,R.id.rbYakuFix2Stick4,R.id.rbYakuFix2Stick5};//~9516I~
    public static final int YAKUFIX2_NO=0;                         //~9516I~
    public static final int YAKUFIX2_STICK4=1;                     //~9516I~
    public static final int YAKUFIX2_STICK5=2;                     //~9516I~
    public static final int YAKUFIX2_DEFAULT=YAKUFIX2_NO;          //~9516I~
                                                                   //~9516I~
    public static final int[] rbsEatChange=new int[]{R.id.rbEatChangeNo,R.id.rbEatChangeExceptIt,R.id.rbEatChangeAllOK};//~9516I~
    public static final int EATCHANGE_NO=0;                        //~9516I~
    public static final int EATCHANGE_EXCEPTIT=1;                  //~9516I~
    public static final int EATCHANGE_ALLOK=2;                     //~9516I~
    public static final int EATCHANGE_DEFAULT=EATCHANGE_NO;        //~9516I~
                                                                   //~9517I~
    public static final int[] rbsYaku7Pair=new int[]{R.id.rbYaku7Pair50_1,R.id.rbYaku7Pair25_2};//~9517I~
    public static final int YAKU7PAIR_50_1=0;                      //~9517I~
    public static final int YAKU7PAIR_25_2=1;                      //~9517I~
    public static final int YAKU7PAIR_DEFAULT=YAKU7PAIR_50_1;      //~9517I~
                                                                   //~9C03I~
    public static final int[] rbsOpenReach=new int[]{R.id.rbOpenReachYakuman,R.id.rbOpenReachChombo};//~0329I~
    public static final int OPENREACH_YAKUMAN=0;                   //~0329I~
    public static final int OPENREACH_CHOMBO=1;                    //~0329I~
    public static final int OPENREACH_DEFAULT=OPENREACH_YAKUMAN;   //~0329I~
                                                                   //~0329I~
    public static final int[] rbsOpenReachRobot=new int[]{R.id.rbOpenReachRobotNone,R.id.rbOpenReachRobotSkip};//~0329R~
    public static final int OPENREACH_ROBOT_NONE=0;                //~0329I~
    public static final int OPENREACH_ROBOT_SKIP=1;                //~0329I~
    public static final int OPENREACH_ROBOT_DEFAULT=OPENREACH_ROBOT_NONE;//~0329I~
                                                                   //~0329I~
//    public static final int[] rbs2Touch=new int[]{R.id.rb2TouchNo,R.id.rb2TouchYes,R.id.rb2TouchWithCancel};//~9C03I~//~9C07R~
//    public static final int STOP2T_NO=0;                           //~9C03I~//~9C07R~
//    public static final int STOP2T_YES=1;                          //~9C03I~//~9C07R~
//    public static final int STOP2T_CANCELABLE=2;                    //~9C03I~//~9C07R~
//    public static final int[] rbs2Touch=new int[]{R.id.rb2TouchNo,R.id.rb2TouchYes,R.id.rb2TouchYesRonOnly};//~9C09I~//~9C10R~
//    public static final int STOP2T_NO=0;                           //~9C09I~//~9C10R~
//    public static final int STOP2T_YES=1;                          //~9C09I~//~9C10R~
//    public static final int STOP2T_YES_RONONLY=2;                  //~9C09I~//~9C10R~
    //******************************************************************//~9414I~
    public static final int 	DEFAULT_DEBT=30000;                //~v@@@I~//~9412I~//~9414M~
    public static final int		DEFAULT_MINUSPRIZE_MIN=0;          //~9408R~//~9412I~
    public static final int		DEFAULT_MINUSPRIZE_MAX=30000;      //~9408R~//~9412I~
    public static final int 	DEFAULT_MINUSPRIZE_INC=1000;       //~9408R~//~9412I~
    public static final int 	DEFAULT_MINUSPRIZE_INIT=0;         //~9408R~//~9412I~
                                                                   //~9412I~
    public static final int 	DEFAULT_ORDERPRIZE=1;	//5-10     //~9819I~
                                                                   //~9819I~
    public static final int 	DEFAULT_DELAY_PONKAN=2;		//sec  //~9412R~
    public static final int 	DEFAULT_DELAY_TAKE=2  ;		//sec  //~9412I~
    public static final int 	DEFAULT_DELAY_LAST=2;	//sec      //~9412R~
    public static final int 	DEFAULT_DELAY_DISCARD=0;           //~9622I~
    public static final int 	DEFAULT_DELAY_2TOUCH=2;            //~9B15I~
    public static final int 	DEFAULT_TIMEOUT_TAKE=0;            //~9622R~
    public static final int 	DEFAULT_TIMEOUT_TAKEROBOT=1;       //~9701I~
//  public static final int 	DEFAULT_TIMEOUT_TAKEKAN=0;         //~9623I~//~9625R~
                                                                   //~9622I~
    public static final int 	DEFAULT_DELAY_PONKAN_MIN=1;        //~9412I~
    public static final int 	DEFAULT_DELAY_PONKAN_MAX=100;      //~9412R~
    public static final int 	DEFAULT_DELAY_PONKAN_INC=1;        //~9412I~
                                                                   //~9622I~
    public static final int 	DEFAULT_DELAY_TAKE_MIN=1;          //~9412I~
    public static final int 	DEFAULT_DELAY_TAKE_MAX=100;        //~9412R~
    public static final int 	DEFAULT_DELAY_TAKE_INC=1;          //~9412I~
                                                                   //~9430I~
    public static final int 	DEFAULT_DELAY_LAST_MIN=1;          //~9412I~
    public static final int 	DEFAULT_DELAY_LAST_MAX=100;        //~9412R~
    public static final int 	DEFAULT_DELAY_LAST_INC=1;          //~9412I~
                                                                   //~9622I~
    public static final int 	DEFAULT_DELAY_DISCARD_MIN=0;       //~9622I~
    public static final int 	DEFAULT_DELAY_DISCARD_MAX=100;     //~9622I~
    public static final int 	DEFAULT_DELAY_DISCARD_INC=1;       //~9622I~
                                                                   //~9622I~
    public static final int 	DEFAULT_DELAY_2TOUCH_MIN=1;        //~9B15I~//~9C13R~
    public static final int 	DEFAULT_DELAY_2TOUCH_MAX=100;      //~9B15I~
    public static final int 	DEFAULT_DELAY_2TOUCH_INC=1;        //~9B15I~
                                                                   //~9B15I~
    public static final int 	DEFAULT_TIMEOUT_TAKE_MIN=0;        //~9622R~
    public static final int 	DEFAULT_TIMEOUT_TAKE_MAX=100;      //~9622R~
    public static final int 	DEFAULT_TIMEOUT_TAKE_INC=1;        //~9622R~
                                                                   //~9701I~
    public static final int 	DEFAULT_TIMEOUT_TAKEROBOT_MIN=1;   //~9701I~
    public static final int 	DEFAULT_TIMEOUT_TAKEROBOT_MAX=100; //~9701I~
    public static final int 	DEFAULT_TIMEOUT_TAKEROBOT_INC=1;   //~9701I~
                                                                   //~9430I~
//    public static final int     DEFAULT_TIMEOUT_TAKEKAN_MIN=0;     //~9623I~//~9625R~
//    public static final int     DEFAULT_TIMEOUT_TAKEKAN_MAX=100;   //~9623I~//~9625R~
//    public static final int     DEFAULT_TIMEOUT_TAKEKAN_INC=1;     //~9623I~//~9625R~
                                                                   //~9623I~
    public static final int 	DEFAULT_BIRD_MIN=0;                //~9430I~
    public static final int 	DEFAULT_BIRD_MAX=30000;            //~9430I~
    public static final int 	DEFAULT_BIRD_INC=1000;             //~9430I~
    public static final int 	DEFAULT_BIRD=0;                    //~9430I~
                                                                   //~9413I~
    public static final int[]   pointsDrawnMangan=new int[]{8000,12000,16000,24000,32000};//~9413I~
    public static final String[] rankDrawnMangan=AG.resource.getStringArray(R.array.DrawnManganRank);//~9413I~
    public static final String[] strsGameSetType=AG.resource.getStringArray(R.array.GameSetType);//~9501I~
    public static final int GST_E=0;                               //~9501I~
    public static final int GST_EE=1;                              //~9501I~
    public static final int GST_ES=2;                              //~9501I~
    public static final int GST_EN=3;                              //~9603I~
    public static final int GST_ESWN=4;                            //~9501I~//~9603R~
    public static final int[]  intsGameSetType=new int[]{GST_E,GST_EE,GST_ES,GST_EN,GST_ESWN};//~9501I~//~9603R~
                                                                   //~9517I~
    public static final String[] rankRenho=AG.resource.getStringArray(R.array.RenhoRank);//~9517I~
    public static final int RENHORANK_DEFAULT=rankRenho.length-1;  //~9517I~
                                                                   //~9412I~
    public static final int    RSID_SYNCDATE              =1;               //~@@@@I~//~9404R~
    public static final int    RSID_SYNCDATE_FORMATTED    =2;      //~9405I~
    public static final int    RSID_FILENAME              =3;      //~9405R~
    public static final int    RSID_IDNAME                =4;      //~9405I~
    public static final int    RSID_INITSCORE             =12;     //~9404R~
    public static final int    RSID_POINT_DUP             =13;     //~9512I~
    public static final int    RSID_INITSCORE_TESTE       =20;     //~9425I~
    public static final int    RSID_INITSCORE_TESTS       =21;     //~9425R~
    public static final int    RSID_INITSCORE_TESTW       =22;     //~9425R~
    public static final int    RSID_INITSCORE_TESTN       =23;     //~9425R~
    public static final int    RSID_MINUSSTOP             =100;    //~9404I~
    public static final int    RSID_MINUSSTOP_0           =101;    //~9404I~
    public static final int    RSID_MINUSSTOP_PAYTYPE     =103;    //~9404I~
    public static final int    RSID_MINUSSTOP_POINT       =104;    //~9408I~
    public static final int    RSID_MINUSSTOP_BYERR       =105;    //~9414I~
                                                                   //~9408I~
    public static final int    RSID_ORDERPRIZE_SAMEPOINT  =110;    //~9407I~
//  public static final int    RSID_ORDERPRIZE_TOP        =111;    //~9407R~
//  public static final int    RSID_ORDERPRIZE_2ND        =112;    //~9407R~
    public static final int    RSID_ORDERPRIZE            =113;    //~9407I~
                                                                   //~9408I~
    public static final int    RSID_MULTIRON              =120;    //~9408I~
//  public static final int    RSID_MULTIRON_STICKALL     =121;    //~9408I~//~9827R~
                                                                   //~9425I~
    public static final int    RSID_MULTIRON3DRAWN        =124;    //~9409I~//~9425R~
    public static final int    RSID_DRAWN_HW99            =125;    //~9425I~
    public static final int    RSID_DRAWN_HW4W            =126;    //~9425I~
    public static final int    RSID_DRAWN_HW4K            =127;    //~9425I~
    public static final int    RSID_DRAWN_HW4R            =128;    //~9425I~
                                                                   //~9412I~
    public static final int    RSID_DELAY_PONKAN          =130;    //~9412I~
    public static final int    RSID_DELAY_TAKE            =131;    //~9412I~
    public static final int    RSID_DELAY_LAST            =132;    //~9412I~
    public static final int    RSID_DELAY_DISCARD         =133;    //~9622I~
    public static final int    RSID_TIMEOUT_TAKE          =134;    //~9622I~
//  public static final int    RSID_TIMEOUT_TAKEKAN       =135;    //~9623I~//~9625R~
    public static final int    RSID_RULEWAIT              =136;    //~9629I~
    public static final int    RSID_TIMEOUT_TAKEROBOT     =137;    //~9701I~
                                                                   //~9408I~
//  public static final int    RSID_DRAWN_MANGAN_YN       =140;    //~9413I~//~9505R~
//  public static final int    RSID_DRAWN_MANGAN_PENDING  =141;    //~9413I~//~9422R~//~9505R~
    public static final int    RSID_DRAWN_MANGAN_TYPE     =140;    //~9505I~
    public static final int    RSID_DRAWN_MANGAN_RANK     =142;    //~9413I~
//  public static final int    RSID_DRAWN_MANGAN_NEXT     =143;    //~9422I~//~9424R~//~9505R~
//  public static final int    RSID_DRAWN_HW_NEXT         =145;    //~9425I~//~9B13R~
//  public static final int    RSID_DRAWN_MANGAN_DROPBIRD =146;    //~9430R~//~9505R~
                                                                   //~9416I~
    public static final int    RSID_SCORE_TO_POINT        =150;    //~9416I~
                                                                   //~9B13I~
    public static final int    RSID_MULTIRON3DRAWNC       =155;    //~9B13I~
    public static final int    RSID_DRAWN_HW99C           =156;    //~9B13I~
    public static final int    RSID_DRAWN_HW4WC           =157;    //~9B13I~
    public static final int    RSID_DRAWN_HW4KC           =158;    //~9B13I~
    public static final int    RSID_DRAWN_HW4RC           =159;    //~9B13I~
                                                                   //~9B13I~
                                                                   //~9413I~
    public static final int    RSID_REACH_OPEN            =160;    //~9427I~
    public static final int    RSID_REACH_MISSING         =161;    //~9427I~
    public static final int    RSID_ANKAN_AFTER_REACH     =162;    //~9530I~
    public static final int    RSID_OPENREACH_PAY         =163;    //~0329I~
    public static final int    RSID_OPENREACH_ROBOT       =164;    //~0329I~
                                                                   //~9427I~
    public static final int    RSID_MINUSSTOP_ROBOT       =170;     //~9404I~//~9429I~
    public static final int    RSID_ROBOT_PAY             =171;    //~9429I~
    public static final int    RSID_ALLOW_ROBOT           =172;    //~9607I~
                                                                   //~9414I~
    public static final int    RSID_BIRD                  =180;    //~9430I~
    public static final int    RSID_BIRD_PAY              =181;    //~9430I~
    public static final int    RSID_BIRD_PAYTYPE          =182;    //~9602I~
                                                                   //~9430I~
    public static final int    RSID_CLOSABLE_RON          =190;    //~9501I~
    public static final int    RSID_CLOSABLE_PENDING      =191;    //~9501I~
    public static final int    RSID_CLOSABLE_NOTTOP       =192;    //~9501I~
    public static final int    RSID_GAMESET_TYPE          =193;    //~9501I~
    public static final int    RSID_FL_NOTPENDING         =194;    //~9501I~
    public static final int    RSID_FL_ALLMINUS           =195;    //~9501I~
                                                                   //~9C03I~
    public static final int    RSID_DELAY_2TOUCH          =200;    //~9B15I~//~9C03R~
//    public static final int    RSID_2TOUCH_PON            =201;    //~9C03I~//~9C07R~//~9C09R~
//    public static final int    RSID_2TOUCH_TO_PON         =202;    //~9C03R~//~9C07R~//~9C09R~
//    public static final int    RSID_2TOUCH_RON            =203;    //~9C03R~//~9C07R~//~9C09R~
//    public static final int    RSID_2TOUCH_TO_RON         =204;    //~9C03R~//~9C07R~//~9C09R~
//  public static final int    RSID_2TOUCH                =201;    //~9C09I~//~9C10R~
    public static final int    RSID_2TOUCH_CANCELABLE_RON =201;    //~9C10I~
    public static final int    RSID_2TOUCH_TIMEOUT        =202;    //~9C09I~
    public static final int    RSID_CHECK_RONABLE         =203;    //~0205I~
//Yaku                                                                   //~9501I~//~9516R~
    public static final int    RSID_8CONTINUE             =300;    //~9516I~
    public static final int    RSID_8CONT_NONEEDYAKU      =301;    //~9516I~
    public static final int    RSID_8CONT_RESET           =302;    //~9516I~//~9517R~
    public static final int    RSID_8CONT_MULTI           =303;    //~9517I~
                                                                   //~9516I~
    public static final int    RSID_YAKUFIX               =310;    //~9516I~
    public static final int    RSID_YAKUFIX2              =311;    //~9516I~
//  public static final int    RSID_YAKUFIX2LAST          =312;    //~9516I~//~9B10R~
    public static final int    RSID_YAKUFIX_MULTIWAITOK   =313;    //~0208R~
    public static final int    RSID_YAKUFIX_MULTIWAITDRAWOK=314;   //~0208I~
                                                                   //~9516I~
    public static final int    RSID_EATCHANGE             =320;    //~9516I~
                                                                   //~9517I~
    public static final int    RSID_KUITAN                =330;    //~9404I~//~9517I~
    public static final int    RSID_PINFUTAKEN            =331;    //~9517I~
    public static final int    RSID_7PAIR                 =332;    //~9517I~
    public static final int    RSID_7PAIR4PAIR            =333;    //~9517I~
    public static final int    RSID_PENDING_RANK0         =334;    //~9517I~
    public static final int    RSID_PENDING_RANKNO        =335;    //~0330I~
    public static final int    RSID_PENDING_RANKEMPTY     =336;    //~0330I~
    public static final int    RSID_PENDING_RANKFURITEN   =337;    //~0330I~
    public static final int    RSID_PENDING_RANK2         =338;    //~0330I~
                                                                   //~9516I~
    public static final int    RSID_4ANKO1                =340;    //~9517I~
    public static final int    RSID_KOKUSI13              =341;    //~9517I~
    public static final int    RSID_9REN9                 =342;    //~9517I~
    public static final int    RSID_4WIND                 =343;    //~9517I~
                                                                   //~9517I~
    public static final int    RSID_ALLGREEN_NOBLUE       =350;    //~9517I~
    public static final int    RSID_9RENPINSOU            =351;    //~9517I~
    public static final int    RSID_NOPAIR13              =352;    //~9517I~
    public static final int    RSID_BIGRING               =353;    //~9517I~
    public static final int    RSID_RANK13                =354;    //~9517I~
    public static final int    RSID_RENHORANK             =355;    //~9517I~
    public static final int    RSID_RENHOMIX              =356;    //~9517I~
    public static final int    RSID_KOKUSI_ANKANRON       =357;    //~9517I~
//  public static final int    RSID_OPENREACHRON          =358;    //~9517I~//~0329R~
    public static final int    RSID_5THKAN                =359;    //~9517I~
                                                                   //~9C11I~
    public static final int    RSID_13NOPAIR              =360;    //~9C11I~
    public static final int    RSID_14NOPAIR              =361;    //~9C11I~
                                                                   //~9517I~
    public static final int    RSID_SPRITPOS              =370;    //~9528I~
    public static final int    RSID_RANKMUP               =371;    //~9528R~
                                                                   //~9528I~
    public static final int    RSID_DORA                  =380;    //~9528I~
    public static final int    RSID_DORA_HIDDEN           =381;    //~9528I~
    public static final int    RSID_KANDORA               =382;    //~9528I~
    public static final int    RSID_KANDORA_HIDDEN        =383;    //~9528I~
    public static final int    RSID_KANDORA_OPEN          =384;    //~9529I~
    public static final int    RSID_USERED5               =385;    //~9C01I~
                                                                   //~9709I~
    public static final int    RSID_PENDING_CONT          =390;    //~9709I~
                                                                   //~9528I~
    public static final int    RSID_POSITIONING           =400;    //~9710I~
                                                                   //~9710I~
    public static final int    RSID_SUSPEND               =410;    //~9820I~
    public static final int    RSID_SUSPEND_PENALTY       =411;    //~9820I~
    public static final int    RSID_SUSPEND_PENALTYIOERR  =412;    //~9A18I~
                                                                   //~9820I~
    private static final RSEnumPair[] pairs=                       //~9404I~//~9414R~
    			{                                                  //~9404I~
    				new RSEnumPair(RSID_SYNCDATE             ,"SyncDate"             ),//~9404R~
    				new RSEnumPair(RSID_SYNCDATE_FORMATTED   ,"SyncDateFormatted"    ),//~9405I~
    				new RSEnumPair(RSID_FILENAME             ,"FileName"                 ),//~9405R~
    				new RSEnumPair(RSID_IDNAME               ,"Name"                 ),//~9405I~
    				new RSEnumPair(RSID_INITSCORE            ,"InitScore"            ),//~9404I~
    				new RSEnumPair(RSID_INITSCORE_TESTE     ,"InitScoreTestE"       ),//~9425I~
    				new RSEnumPair(RSID_INITSCORE_TESTS     ,"InitScoreTestS"       ),//~9425I~
    				new RSEnumPair(RSID_INITSCORE_TESTW     ,"InitScoreTestW"       ),//~9425I~
    				new RSEnumPair(RSID_INITSCORE_TESTN     ,"InitScoreTestN"       ),//~9425I~
    				new RSEnumPair(RSID_POINT_DUP            ,"PointDup"            ),//~9512I~
                                                                   //~9512R~
    				new RSEnumPair(RSID_MINUSSTOP            ,"MinusStop"            ),//~9404I~
    				new RSEnumPair(RSID_MINUSSTOP_0          ,"MinusStop0"           ),//~9404I~
    				new RSEnumPair(RSID_MINUSSTOP_PAYTYPE    ,"MinusStopPayType"     ),//~9404I~
    				new RSEnumPair(RSID_MINUSSTOP_POINT      ,"MinusStopPoint"       ),//~9408I~
    				new RSEnumPair(RSID_MINUSSTOP_BYERR      ,"MinusStopByErr"       ),//~9414I~
                                                                   //~9408I~
    				new RSEnumPair(RSID_ORDERPRIZE_SAMEPOINT ,"SamePointOrder"       ),//~9407I~
//  				new RSEnumPair(RSID_ORDERPRIZE_TOP       ,"OrderPrizeTop"        ),//~9407R~
//  				new RSEnumPair(RSID_ORDERPRIZE_2ND       ,"OrderPrize2nd"        ),//~9407R~
    				new RSEnumPair(RSID_ORDERPRIZE           ,"OrderPrize"           ),//~9407I~
                                                                   //~9408I~
    				new RSEnumPair(RSID_MULTIRON             ,"MultiRon"             ),//~9408I~
//  				new RSEnumPair(RSID_MULTIRON_STICKALL    ,"MultiRonStickAll"     ),//~9408I~//~9827R~
    				new RSEnumPair(RSID_MULTIRON3DRAWN       ,"MultiRon3Drawn"       ),//~9409I~
    				new RSEnumPair(RSID_DRAWN_HW99           ,"DrawnHW99"            ),//~9425I~
    				new RSEnumPair(RSID_DRAWN_HW4W           ,"DrawnHW4W"            ),//~9425I~
    				new RSEnumPair(RSID_DRAWN_HW4K           ,"DrawnHW4K"            ),//~9425I~
    				new RSEnumPair(RSID_DRAWN_HW4R           ,"DrawnHW4R"            ),//~9425I~
                                                                   //~9412I~
    				new RSEnumPair(RSID_MULTIRON3DRAWNC      ,"MultiRon3DrawnC"      ),//~9B13I~
    				new RSEnumPair(RSID_DRAWN_HW99C          ,"DrawnHW99C"           ),//~9B13I~
    				new RSEnumPair(RSID_DRAWN_HW4WC          ,"DrawnHW4WC"           ),//~9B13I~
    				new RSEnumPair(RSID_DRAWN_HW4KC          ,"DrawnHW4KC"           ),//~9B13I~
    				new RSEnumPair(RSID_DRAWN_HW4RC          ,"DrawnHW4RC"           ),//~9B13I~
                                                                   //~9B13I~
    				new RSEnumPair(RSID_DELAY_PONKAN         ,"DelayPonKan"          ),//~9412I~
    				new RSEnumPair(RSID_DELAY_TAKE           ,"DelayTake"            ),//~9412I~
    				new RSEnumPair(RSID_DELAY_LAST           ,"DelayLastDrawn"       ),//~9412R~
    				new RSEnumPair(RSID_DELAY_DISCARD        ,"DelayDiscard"         ),//~9622R~
    				new RSEnumPair(RSID_DELAY_2TOUCH         ,"Delay2Touch"          ),//~9B15I~//~9C10R~
//  				new RSEnumPair(RSID_2TOUCH_PON           ,"Delay2TPon"          ),//~9C03I~//~9C09R~//~9C10R~
//  				new RSEnumPair(RSID_2TOUCH_RON           ,"Delay2TRon"           ),//~9C03I~//~9C09R~//~9C10R~
//  				new RSEnumPair(RSID_2TOUCH_TO_PON        ,"Delay2TTOPon"         ),//~9C03R~//~9C09R~//~9C10R~
//  				new RSEnumPair(RSID_2TOUCH_TO_RON        ,"Delay2TTORon"         ),//~9C03R~//~9C09R~//~9C10R~
//  				new RSEnumPair(RSID_2TOUCH               ,"2TouchCancelable"    ),//~9C09I~//~9C10R~
    				new RSEnumPair(RSID_2TOUCH_CANCELABLE_RON,"2TouchCancelableRon"    ),//~9C10I~
    				new RSEnumPair(RSID_CHECK_RONABLE        ,"CheckRonable"           ),//~0205I~
    				new RSEnumPair(RSID_2TOUCH_TIMEOUT       ,"2TouchTimeout"        ),//~9C09I~//~9C10R~
    				new RSEnumPair(RSID_TIMEOUT_TAKE         ,"TimeoutTake"          ),//~9622R~
    				new RSEnumPair(RSID_TIMEOUT_TAKEROBOT    ,"TimeoutTakeRobot"     ),//~9701I~
//  				new RSEnumPair(RSID_TIMEOUT_TAKEKAN      ,"TimeoutTakeKan"       ),//~9623I~//~9625R~
    				new RSEnumPair(RSID_RULEWAIT             ,"RuleWait"             ),//~9629I~
                                                                   //~9408I~
//  				new RSEnumPair(RSID_DRAWN_MANGAN_YN      ,"DrawnMangan"          ),//~9413I~//~9505R~
//  				new RSEnumPair(RSID_DRAWN_MANGAN_PENDING ,"DrawnManganPending"   ),//~9413I~//~9422R~//~9505R~
    				new RSEnumPair(RSID_DRAWN_MANGAN_TYPE    ,"DrawnManganType"      ),//~9505I~
    				new RSEnumPair(RSID_DRAWN_MANGAN_RANK    ,"DrawnManganRank"      ),//~9413I~
//  				new RSEnumPair(RSID_DRAWN_MANGAN_NEXT    ,"DrawnManganNext"      ),//~9422I~//~9424R~//~9505R~
//    				new RSEnumPair(RSID_DRAWN_HW_NEXT        ,"DrawnHWNext"          ),//~9425I~
//  				new RSEnumPair(RSID_DRAWN_MANGAN_DROPBIRD,"DrawnManganDropBird"  ),//~9430I~//~9505R~
                                                                   //~9416I~
    				new RSEnumPair(RSID_SCORE_TO_POINT       ,"ScoreToPoint"         ),//~9416I~
                                                                   //~9413I~
    				new RSEnumPair(RSID_REACH_OPEN           ,"OpenReach"            ),//~9427I~
    				new RSEnumPair(RSID_REACH_MISSING        ,"MissingReach"         ),//~9427I~
    				new RSEnumPair(RSID_ANKAN_AFTER_REACH    ,"AnkanReach"           ),//~9530I~
    				new RSEnumPair(RSID_OPENREACH_PAY        ,"OpenReachPay"         ),//~0329I~
    				new RSEnumPair(RSID_OPENREACH_ROBOT      ,"OpenReachRobot"        ),//~0329I~
                                                                   //~9427I~
    				new RSEnumPair(RSID_MINUSSTOP_ROBOT      ,"MinusStopRobot"       ),//~9404I~//~9429M~
    				new RSEnumPair(RSID_ROBOT_PAY            ,"RobotPay"             ),//~9429I~
    				new RSEnumPair(RSID_ALLOW_ROBOT          ,"RobotPlayer"          ),//~9607I~
                                                                   //~9429I~
    				new RSEnumPair(RSID_BIRD                 ,"Bird"                 ),//~9430I~
    				new RSEnumPair(RSID_BIRD_PAY             ,"BirdPay"              ),//~9430I~
    				new RSEnumPair(RSID_BIRD_PAYTYPE         ,"BirdPayType"          ),//~9602I~
                                                                   //~9430I~
    				new RSEnumPair(RSID_CLOSABLE_RON         ,"LastCloseRon"         ),//~9501I~
    				new RSEnumPair(RSID_CLOSABLE_PENDING     ,"LastClosePending"     ),//~9501I~
    				new RSEnumPair(RSID_CLOSABLE_NOTTOP      ,"LastCloseNotTop"      ),//~9501I~
    				new RSEnumPair(RSID_GAMESET_TYPE         ,"GameSet"              ),//~9501I~
    				new RSEnumPair(RSID_FL_NOTPENDING        ,"LastNotPending"       ),//~9501I~
    				new RSEnumPair(RSID_FL_ALLMINUS          ,"LastAllMinus"         ),//~9501I~
                                                                   //~9501I~
    				new RSEnumPair(RSID_8CONTINUE            ,"Ron8Cont"             ),//~9516I~
    				new RSEnumPair(RSID_8CONT_NONEEDYAKU     ,"Ron8ContNoYaku"       ),//~9516I~
    				new RSEnumPair(RSID_8CONT_RESET          ,"Ron8ContReset"     ),//~9516I~//~9517R~
    				new RSEnumPair(RSID_8CONT_MULTI          ,"Ron8ContMulti"     ),//~9517I~
                                                                   //~9516I~
    				new RSEnumPair(RSID_YAKUFIX              ,"YakuFix"              ),//~9516I~
    				new RSEnumPair(RSID_YAKUFIX2             ,"YakuFix2"             ),//~9516I~
//  				new RSEnumPair(RSID_YAKUFIX2LAST         ,"YakuFix2Last"         ),//~9516I~//~9B10R~
    				new RSEnumPair(RSID_YAKUFIX_MULTIWAITOK  ,"YakuFixMultiWaitOK"   ),//~0208I~
    				new RSEnumPair(RSID_YAKUFIX_MULTIWAITDRAWOK,"YakuFixMultiWaitDrawOK"),//~0208I~
                                                                   //~9516I~
    				new RSEnumPair(RSID_EATCHANGE            ,"EatChange"            ),//~9516I~
                                                                   //~9517I~
    				new RSEnumPair(RSID_KUITAN               ,"Kuitan"               ),//~9404I~//~9517M~
    				new RSEnumPair(RSID_PINFUTAKEN           ,"PinDumo"              ),//~9517I~
    				new RSEnumPair(RSID_7PAIR                ,"Pair7"                ),//~9517I~
    				new RSEnumPair(RSID_7PAIR4PAIR           ,"Pair7Kan"             ),//~9517I~
    				new RSEnumPair(RSID_PENDING_RANK0        ,"KeiTen"               ),//~9517I~
    				new RSEnumPair(RSID_PENDING_RANKNO       ,"KeiTenNo"             ),//~0330I~
    				new RSEnumPair(RSID_PENDING_RANKEMPTY    ,"KeiTenEmpty"          ),//~0330I~
    				new RSEnumPair(RSID_PENDING_RANKFURITEN  ,"KeiTenFuriten"        ),//~0330I~
    				new RSEnumPair(RSID_PENDING_RANK2        ,"KeiTenRank2"          ),//~0330I~
                                                                   //~9516I~
    				new RSEnumPair(RSID_4ANKO1               ,"Anko4_1"              ),//~9517I~
    				new RSEnumPair(RSID_KOKUSI13             ,"Kokusi13"             ),//~9517I~
    				new RSEnumPair(RSID_9REN9                ,"ChuRen8"              ),//~9517I~
    				new RSEnumPair(RSID_4WIND                ,"Daisusii"             ),//~9517I~
                                                                   //~9517I~
    				new RSEnumPair(RSID_ALLGREEN_NOBLUE      ,"GreenNoBlue"          ),//~9517I~
    				new RSEnumPair(RSID_9RENPINSOU           ,"ChuRenPinSou"         ),//~9517I~
    				new RSEnumPair(RSID_NOPAIR13             ,"NoPair13"             ),//~9517I~
    				new RSEnumPair(RSID_BIGRING              ,"BigRing"              ),//~9517I~
    				new RSEnumPair(RSID_RANK13               ,"Rank13"               ),//~9517I~
    				new RSEnumPair(RSID_RENHORANK            ,"RenhoRank"            ),//~9517I~
    				new RSEnumPair(RSID_RENHOMIX             ,"RenhoMix"             ),//~9517I~
    				new RSEnumPair(RSID_KOKUSI_ANKANRON      ,"KokusiAnkanRon"       ),//~9517I~
//  				new RSEnumPair(RSID_OPENREACHRON         ,"OpenReachRon"         ),//~9517I~//~0329R~
    				new RSEnumPair(RSID_5THKAN               ,"Kan5th"               ),//~9517I~
                                                                   //~9517I~
    				new RSEnumPair(RSID_13NOPAIR             ,"NoPair13"             ),//~9C11I~
    				new RSEnumPair(RSID_14NOPAIR             ,"NoPair14"             ),//~9C11I~
                                                                   //~9C11I~
    				new RSEnumPair(RSID_SPRITPOS             ,"SpritPos"             ),//~9528I~
    				new RSEnumPair(RSID_RANKMUP              ,"RankMUp"              ),//~9528I~
                                                                   //~9528I~
    				new RSEnumPair(RSID_DORA                 ,"Dora"                 ),//~9528I~
    				new RSEnumPair(RSID_DORA_HIDDEN          ,"DoraH"                ),//~9528I~
    				new RSEnumPair(RSID_KANDORA              ,"KanDora"              ),//~9528I~
    				new RSEnumPair(RSID_KANDORA_HIDDEN       ,"KanDoraH"             ),//~9528I~
    				new RSEnumPair(RSID_KANDORA_OPEN         ,"KanDoraO"             ),//~9529I~
    				new RSEnumPair(RSID_USERED5              ,"DoraRed5"             ),//~9C01I~
                                                                   //~9709I~
    				new RSEnumPair(RSID_PENDING_CONT         ,"PendCont"             ),//~9709I~
                                                                   //~9528I~
    				new RSEnumPair(RSID_POSITIONING          ,"PositioningSkip"      ),//~9710I~
                                                                   //~9710I~
    				new RSEnumPair(RSID_SUSPEND              ,"Suspend"              ),//~9820I~
    				new RSEnumPair(RSID_SUSPEND_PENALTY      ,"SuspendPenalty"       ),//~9820I~
    				new RSEnumPair(RSID_SUSPEND_PENALTYIOERR ,"SuspendPenaltyIOErr"  ),//~9A18I~
                                                                   //~9820I~
				};                                                 //~9404I~
    //***********************************************************  //~@@@@I~//~9404R~
//  private static final Map<Integer,String> ID2Key=new HashMap<Integer,String>();//~@@@@I~//~9404R~//~9615R~
    private Map<Integer,String> ID2Key=new HashMap<Integer,String>();//~9615I~
//  private static final Map<String,Integer> key2ID=new HashMap<String,Integer>();//~9404I~//~9407R~
    //*********************************************************************//~9615I~
	public RuleSettingEnum()                                      //~9615I~
    {                                                              //~9615I~
    	AG.aRuleSettingEnum=this;                                  //~9615I~
        init();                                                    //~9615I~
    }                                                              //~9615I~
    //***********************************************************  //~9404I~
//  static                                                         //~@@@@I~//~9615R~
    private void init()                                            //~9615I~
    {                                                              //~@@@@I~
    	for (RSEnumPair pair:pairs)                                //~9404R~
        {                                                          //~@@@@I~
        	ID2Key.put(pair.id,pair.key);                          //~9404R~
//      	key2ID.put(pair.key,pair.id);                          //~9404I~//~9407R~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
//    //**************************                                   //~@@@@I~//~9407R~
//    public static int getIDRS(String Pkey)                                        //~@@@@R~//~9404R~//~9407R~
//    {                                                              //~@@@@I~//~9407R~
//        return key2ID.get(Pkey);                                   //~9404I~//~9407R~
//    }                                                              //~@@@@I~//~9407R~
    //**************************                                   //~9407I~
    public  static String getKeyRS(int Pid)                       //~@@@@R~//~9404R~
    {                                                              //~@@@@I~
//      return ID2Key.get(Pid);                                      //~@@@@I~//~9404R~//~9615R~
        return AG.aRuleSettingEnum.ID2Key.get(Pid);                //~9615I~
    }                                                              //~@@@@I~
    //*********************************************************************//~9404I~
	static class RSEnumPair                                               //~9404I~
    {                                                              //~9404I~
    	int id;                                                    //~9404I~
        String key;                                                //~9404I~
    	public RSEnumPair(int Pid,String Pkey)                     //~9404I~
        {                                                          //~9404I~
        	id=Pid; key=Pkey;                                      //~9404I~
        }                                                          //~9404I~
    }                                                              //~9404I~
}                                                                  //~@@@@I~
