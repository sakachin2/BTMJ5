//*CID://+vabmR~: update#= 320;                                    //~vabcR~//+vabmR~
//**********************************************************************
//2021/07/29 vabm skip reach for shanpon to change ryanmen         //+vabmI~
//2021/07/27 vabc defuse decision of chanta                        //~vabcI~
//2021/07/27 vabb evaluate value of allsame; more minus for trplet over pair//~vabbI~
//2021/07/25 vab9 gnore shanten Up/Down if once called pon/chii according intent//~vab9I~
//2021/07/27 vaba defuse decision of intent allsame                //~vabaI~
//2021/07/25 vab7 skip reach if other called open reach            //~vab7I~
//2021/07/17 vaaP (Bug of Vaad)at discard of reach, shanten was not set. miss notify of Ron at take if reach done//~vaaPI~
//2021/07/16 vaaM Intent tanyao value should be over shantendown after once called//~vaaMI~
//2021/07/11 vaaC set intent tanyao/chanta/samecolor for also 7pair//~vaaCI~
//2021/07/09 vaaw (Bug)Miss to set ALLSAME(INTENT_TREND_SAME is similar flag, us ALLSAME only)//~vaawI~
//2021/07/03 vaai strengthen robot; call pon/chii if become shanten 0 with a Yaku+dora>=2//~vaaiI~
//2021/06/19 va9h avoid reach when winList=1 except 7pair at eraly timimg//~va9hI~
//2021/01/07 va60 CalcShanten
//**********************************************************************
package com.btmtest.game.RA;

import com.btmtest.game.UA.Rank;

import static com.btmtest.game.GConst.*;

//********************************************************************************************
public class RAConst                                               //~1130R~
{
    public static final int TN1   =0;                              //~1222I~
    public static final int TN2   =1;                              //~1222I~
    public static final int TN3   =2;                              //~1222I~
    public static final int TN4   =3;                              //~1222I~
    public static final int TN5   =4;                              //~1222I~
    public static final int TN6   =5;                              //~1222I~
    public static final int TN7   =6;                              //~1222I~
    public static final int TN8   =7;                              //~1222I~
    public static final int TN9   =8;                              //~1222I~
                                                                   //~1222I~
    //* discard from smaller value  largest is selected
    public static final int DV_NOTDISCARDABLE =-9999999;        //9,999,999//~1218R~
//  public static final int DV_GIVEUP         =-8888888;           //~1217R~
    public static final int DV_REACH          = 3000000;		//3,000,000 discard for reach//~1122R~//~1218R~
    public static final int DV_DISCARD        =  100000;		//100,000 force DISCARD <=DV_MARK_OTHER//~1307R~
    public static final int DV_REACH_BYVALUE  =  100000;		//discard for reach//~1122I~
    public static final int DV_KEEP_SHANTEN0  = 2000000;		//2,000,000 shanten0 but do not reach//~1122I~//~1218R~
    public static final int DV_BASE           = 1000000;		//for minus by Dora//~1122I~
    public static final int DV_KEEP           =-1000000;
    public static final int DV_SHANTEN        =   -1000;           //~1215R~
    public static final int DV_SHANTEN_UP     =  100000;           //~1127R~//~1215R~
    public static final int DV_SHANTEN_UP_TILE=     100;           //~1218R~
    public static final int DV_SHANTEN_DOWN   = -100000;           //~1127I~//~1215R~
//  public static final int DV_BY_CTR_WINTILE =  100000;           //~1216R~//~1303R~
    public static final int DV_TRYNEXT_HANMAX = -100000;           //~1220I~
                                                                   //~1214I~
                                                                   //~1214M~
    public static final int DV_DORA           =    -600;           //~1213R~
    public static final int DV_DORA_NEAR1     =    -100;           //~1309I~
    public static final int DV_DORA_NEAR2     =     -50;           //~1309I~
    public static final int DV_EXPOSED        =      50;           //~1213R~
    public static final int DV_SAMECOLOR_WORD =  -10000;
    public static final int DV_NOT_SAMECOLOR  =  200000;   //counter value for shanten up//~1310R~
    public static final int DV_SAMECOLOR_OTHER=  -10000;
    public static final int DV_7PAIR          =  -30000;
    public static final int DV_7PAIR3         =  -20000;           //~1216I~
    public static final int DV_OTHER_SAMECOLOR=  -10000;	//*caution_level by other intent of samecolor//~1217R~
    public static final int DV_SET_OTHER_13ORPHAN   =0;				//set 0 when other player intend is 13orphan
    public static final int DV_13ORPHAN       =DV_KEEP ;
    public static final int DV_13ORPHAN_OTHER =  -30000;
    public static final int DV_NOT_TANYAO     =   10000;
//  public static final int DV_NOT_TANYAO_AFTER_CALL=20000; //once called pon/chii//~1219I~//~1221R~//~vaaMR~
//  public static final int DV_NOT_TANYAO_AFTER_CALL=110000;//not plus for chanta but minus for tanyao tile //once called pon/chii//~vaaMI~//~vab9R~
    public static final int DV_NOT_CHANTA     =   10000;           //~1217I~
    public static final int DV_WAIT1_CHANTA   =   10000;    //select 1/9/ji when tanki wait in Not Tanyao intent//~vaawI~
	public static final int DV_ALL_SAME       =  -20000;     //toitoi lager than tanyao/chanta//~1221R~
	public static final int DV_ALL_SAME3      =  -60000;     //toitoi lager than pair//~vabbI~
//  public static final int DV_NOT_CHANTA_AFTER_CALL=20000; //once called pon/chii//~1220I~//~1221R~//~vab9R~
                                                                   //~1220I~
    public static final int DV_MARK_OTHER_BY_LEVEL_CHILD        =100000;        //100,000 vs REACH 3,000,000//~1222R~//~1311R~
    public static final int DV_MARK_OTHER_BY_LEVEL_PARENT       =150000;        //150,000 vs REACH 3,000,000//~1311I~
    public static final int DV_MARK_OTHER_BY_LEVEL_ADD_BY_CUTPOS=100000;        //additional to parent or child//~1311I~
    public static final int DV_MARK_OTHER_LEVEL_ITSELF      = 50;  //5,000,000>REACH:skip reach if GIVEUP add discadable by DV_MARK_OTHER_BY_LEVEL:1000//~1220I~//~1222R~
    public static final int DV_MARK_OTHER_LEVEL_LINK        = 20;  //2,000,000 add discadable by DV_MARK_OTHER_BY_LEVEL:1000//~1220I~//~1222R~
    public static final int DV_MARK_OTHER_LEVEL_WARNING     = 10;  //1,000,000 if GIVEUP_WEAK add discadable by DV_MARK_OTHER_BY_LEVEL:1000//~1220R~//~1222R~
    public static final int DV_MARK_OTHER_LEVEL_WARNING2    =  2;  //  200,000 link tile discarded before reach//~1311I~
                                                                   //~1220I~
    public static final int DV_MARK_OTHER_ITSELF =  1000;          //~1220I~
    public static final int DV_MARK_OTHER_LINK   =  1500;          //~1216I~
//  public static final int[] DVS_DRAGON      ={800,300,DV_KEEP,DV_KEEP};    //by ctr orphan,pair,triple,quad
//  public static final int[] DVS_ROUND       ={800,300,DV_KEEP,DV_KEEP};
//  public static final int[] DVS_WIND        ={800,300,DV_KEEP,DV_KEEP};
//  public static final int[] DVS_ROUND_WIND  ={700,300,DV_KEEP,DV_KEEP};
//  public static final int[] DVS_OTHERWORD   ={900,200,DV_KEEP,DV_KEEP};
    public static final int[] DVS_WORD        ={950,50,DV_KEEP,DV_KEEP};
    public static final int DV_WORD_RANDOM_MAX=10; 	//add 1-9 by random number to avoid same pattern for save value//~1216R~
    public static final int DV_WORD_RANDOM    =1; 	//add 1-9 by random number to avoid same pattern for save value//~1216I~
    public static final int DV_WORD_YAKU      =-200; //your wind:*3, round:*2, dragon:*2//~1215R~//~1301R~//~1302R~
    public static final int DV_WORD_NOTEXPOSED=-90;                //~1216I~//~1302I~
    public static final int DV_WORD_ORPHAN    =950;                //~1217R~
    public static final int DVS_NUMBER_ORPHAN =700;                //~1217I~
    public static final int[] DVS_NUMBER_WEIGHT        ={40,30,20,10, 0,10,20,30,40};//~1225R~
    public static final int[] DVS_NUMBER_WEIGHT_CHANTA ={ 0,10,20,30,40,30,20,10, 0};//~1225I~
                                                                   //~1214I~
    public static final int DV_1SIDE          =     500;           //~1214M~
    public static final int DV_HOLE           =     300;           //~1214M~
    public static final int DV_ONSAME         =    -100;          //for ryanmen when CALLSTAT_SAME on//~1127R~//~1214M~
    public static final int DV_NUM_PAIR       =    -100;          //for pair when CALLSTAT_SAME off//~1127R~//~1214M~
                                                                   //~1214I~
        public static final int DV_NUM_TERMINAL    =100;           //~1214R~
                                                                   //~1214I~
        public static final int DV_NUM_SEQ3                =-600;          //~1214I~//~1219R~
        public static final int DV_NUM_SEQ3_PLUS           =-300;          //~1218I~//~1219R~
        public static final int DV_NUM_SEQ3_PLUS_KANCHAN   =-150;  //~1219I~
        public static final int DV_NUM_SEQ2                =-400;          //~1214I~//~1216R~//~1219R~
        public static final int DV_NUM_SEQ2_PLUS           =-200;          //~1218I~//~1219R~
        public static final int DV_NUM_SEQ2_PLUS_KANCHAN   =-50;   //~1219I~
        public static final int DV_NUM_SEQ32               =-300;          //~1217I~//~1219R~
        public static final int DV_NUM_SEQDUP              =-100;    //candidate of pillow//~1217R~//~1219R~
        public static final int DV_NUM_KANCHAN             =-300;          //~1214I~//~1216R~//~1219R~
//      public static final int DV_NUM_KANCHAN_SAME        =-200;          //~1214I~//~1216R~//~1217R~//~1219R~
        public static final int DV_NUM_SEQ2_TERM           =-100;  //penchan//~1214I~//~1216R~//~1219R~
        public static final int DV_NUM_ORPHAN_SAME3        =-600;          //~1214M~//~1215R~//~1219R~
        public static final int DV_NUM_ORPHAN_SAME2        =-400;          //~1215I~//~1219R~
        public static final int DV_NUM_DORA_MELD           =-110;  //for seq2,kanchan,term including dora//~1216R~//~1219R~
        public static final int DV_NUM_ORPHAN              =400;           //~1214R~//~1219R~
        public static final int DV_NUM_ORPHAN_LINK         =100;           //~1217I~//~1219R~
                                                                   //~1214I~
    //*number tile status
    public static final int NS_1SIDE     =0x01;  //penchan
    public static final int NS_HOLE      =0x02;  //kanchan
    public static final int NS_2SIDE     =0x04;  //ryanmen
    public static final int NS_SEQ       =0x08;
    public static final int NS_MAY_PILLOW=0x10;
    public static final int NS_PAIR      =0x20;
    public static final int NS_PON       =0x40;
    public static final int NS_KAN       =0x80;
    public static final int NS_ORPHAN  =0x0100;

    public static final int HV_SET_INTENT                   =5;	//determin target yaku befor ctrTaken<5
    public static final int HV_SET_INTENT_SAMECOLOR         =4;	//limit(other color count) of determin same color intent
    public static final int HV_SET_INTENT_SAMECOLOR_TAKEN   =6;	//other color limit is 3  when ctrTaken<=6//~1308I~
    public static final int HV_SET_INTENT_SAMECOLOR_OTHER1  =3;	//other color limit is <=3  when ctrTaken<=6//~1308I~
    public static final int HV_SET_INTENT_SAMECOLOR_OTHER2  =2;	//other color limit is <=2  when ctrTaken>6//~1308I~
    public static final int HV_SET_INTENT_3DRAGON           =6;	//dragon trplet*3+pair*2+orphan*1>=6//~1307I~
//  public static final int HV_SET_INTENT_ALLSAME_VALUE     =9;	    //limit of determin toitoi intent by VALUE_PAIR*pair+VALUE_PON*(pon+kan)>=9//~1308R~//~vabaR~
    public static final int HV_SET_INTENT_ALLSAME_VALUE     =8;	    //limit of determin toitoi intent by VALUE_PAIR*pair+VALUE_PON*(pon+kan)>=8//~vabaI~
    public static final int HV_SET_INTENT_ALLSAME_VALUE_PAIR=1;    //~1217R~
    public static final int HV_SET_INTENT_ALLSAME_VALUE_PON =3;
    public static final int HV_SET_INTENT_TAKE_13ORPHAM  =1;	//select 13 orphan at 1st take//~1121I~//~1213R~
    public static final int HV_SET_INTENT_MAX_SHANTEN_13ORPHAN=4;	//set intent 13 orphan if <=4 shanten//~1213I~//~1214R~
    public static final int HV_NOIGNORE_SHANTEN_DOWN        =4;	//not ignore shantenDown at discard if currentshanten<=4//~1302R~
    public static final int HV_SET_INTENT_TAKE_7PAIR        =4;	//select 7pair <=3 take//~1121I~//~1213R~
    public static final int HV_SET_INTENT_MAX_SHANTEN_7PAIR =3;	//select 7pair if <=3shanten//~1213I~//~1214R~
    public static final int HV_SET_INTENT_MAX_SHANTEN_7PAIR_ANYTIME =1;	//select 7pair if <=3shanten//~1217I~
    public static final int HV_CTR_UPTO_DRAW                   =4*4;   //giveup limit remaining tile ctr up to draw//~1311R~
    public static final int HV_SET_GIVEUP_REMAINING         =8;   //giveup at remaining<8 if shanten>0//~1311I~//~1314R~
    public static final int HV_GIVEUP_BY_MULTIPLE_REACH     =2;      //when other reach player>=2 set intent giveup//~1223R~
    public static final int HV_CHK_13ORPHAN                 =10;      //ctr discarded to chk 13orphan//~1223I~
    public static final int HV_MARK_CALL                    =3;      //mark if 3 earth pair//~1224R~
    public static final int HV_MARK_IGNORE_WINTILE          =4;      //ignore otherplayer if shanten0 & ctrWinTile>=4//~1216I~//~1223R~//~1307R~
    public static final int HV_MARK_NOTIGNORE_REACH_SHANTEN =1;      //not ignore other reach if shanten>1//~1307I~
    public static final int HV_MARK_IGNORE_REACH_ONESHOT_HAN=3;      //ignore other reach oneshot if han>=3//~1309I~
    public static final int HV_TREND_SAME_SAME              =2;   //if same>=2 ans pair>=3  trend is same//~1224R~
    public static final int HV_TREND_SAME_PAIR              =3;   //for trend_same//~1224I~
    public static final int HV_HAN_MARKOTHER                 =4;   //ignore other if han >= 4
    public static final int HV_DORA_MARKOTHER                =3;   //ignore other if dora>= 3//~1314I~
    public static final int HV_HAN_EARTHOTHER                =4;   //mark if opened han>=4//~1220I~
    public static final int HV_SHANTEN_TRYNEXT               =2;   //simulate Take if shanen=1 or 2
    public static final int HV_SHANTEN_TO_PON_WORD_ON_REACH  =2;   //if shanten<=2 call pon for value word at 2nd exposed//~1306I~
    public static final int HV_CTR_REMAINING_DISCARDABLE     =4;   //if <4 do not pon/chii//~1126R~
    public static final double HV_RATE_SAME_COLOR_OTHER     =0.2;			//(oter color)/(the color)
    public static final int  HV_CTR_DISCARD_SAMECOLOR_OTHER=8;		//other player samecolor chk from 8 discarded//~1215R~
//  public static final double HV_RATE_13ORPHAN             =0.9;			//9 tanyao discard of minimum 10 discard//~1215R~
    public static final int HV_CTR_CHK_OTHER_13ORPHAN       =8; 	//at 8 discard chk other 13orphan//~1215I~
    public static final int HV_CTR_OTHER_13ORPHAN           =1; 	//at 8 discard chk other 13orphan discard >1 19ji//~1215I~
    public static final int HV_TIME_TO_CALL                 =3;		//<=3 save to call up to 3 tiles take//~1206I~
    public static final int HV_PARENT_1STCALL_SHANTEN       =3;		//if parent shanten<=3, call PON at 1st discard//~1305I~

    public static final int HV_CTR_TO_WAIT_REACH_EARLY      =8;    // if ctrTaken<8 wait winning tile>=4//~1215I~//~1216R~//~1218R~
    public static final int HV_CTR_TO_WAIT_REACH_EARLY_WINLIST=10;  // if ctrTaken<10 wait winList!=1//~va9hI~
    public static final int HV_CTR_TO_CHK_WORD_STARTING     =6;    // count word tile of ctrTaken<6 for samecolor//~1216I~//~1217R~
    public static final int HV_CTR_WORD_STARTING_SAMECOLOR  =3;    // if word tile>=3 in first 6 discard it is not same color//~1224R~
    public static final int HV_CTR_DORA_FOR_PONCHII_OTHERREACH=0;  // if dora>=0 call pon/chii if shanten=1 and anyone called reach//~vaaiR~
    public static final int HV_CTR_WINTILE_FOR_PONCHII_OTHERREACH=3; // if dora>=0 call pon/chii if shanten=1 and anyone called reach//~vaaiI~
    public static final int HV_CTR_DORA_FOR_PONCHII_SHANTEN1=2;    // if dora>=2 call pon/chii if shanten=1//~vaaiI~
    public static final int HV_CTR_DORA_FOR_PONCHII_SHANTEN2=3;    // if dora>=3 call pon/chii if shanten=2//~vaaiR~
    public static final int HV_INTENT_SAMECOLOR_SHANTEN     =3;    // set intent if shanten<=3//~1218I~//~1308R~
    public static final int HV_CTRWIN_TO_REACH_EARLY         =4;    // if wintile>=4 do reach in early phase//~1215I~
    public static final int HV_CTRWIN_TO_REACH_ONE_TYPE      =4;    //wintile<4 for penchan kanchan tanki//~1224I~
    public static final int HV_CTRWIN_TO_REACH_SKIP_SHANPON  =4;    //wintile<4 for shanpon skip reach//+vabmI~
    public static final int HV_CTRWIN_TO_REACH               =2;    // if wintile>=2 do reach//~1215I~
    public static final int HV_CTRWIN_TO_REACH_2ND           =5;    // if wintile>=5 do reach if other player reached//~1306I~//~1309R~
    public static final int HV_CTRWIN_TO_REACH_7PAIR         =2;    // if wintile>=2 do reach//~1224I~
    public static final int HV_CTRWIN_TO_REACH_FORCE         =1;    // if wintile>=1 and yakuman do reach//~1215I~
    public static final int HV_HAN_TO_FORCE_REACH            = Rank.MIN_RANK_YAKUMAN;    //13 force reach even ctrWin=1 when yakuman//~1120R~//~1215I~
    public static final int HV_HAN_TO_REACH_IGNORE_OTHER     =6;    //ignore other player//~1215I~
    public static final int HV_HAN_TO_MARK_IGNORE_OTHER      =4;    //ignore other player conditionally//~1216I~
    public static final int HV_AVOID_REACH_BY_REMAINING_CTR  =4*3;    // if remainig<10 do not reach//~1215I~
    public static final int HV_DISCARD_GIVEUP                =15;     //half of round//~1217I~//~1302R~
    public static final int HV_SHANTEN_GIVEUP                =2;      //give up if discard>=15 && shanten>=2//~1217I~
    public static final int HV_DISCARD_GIVEUP_WEAK           =14;     //short before half of round//~1217I~//~1302R~
    public static final int HV_SHANTEN_GIVEUP_WEAK           =2;      //give up if discard>=15 && shanten>=2//~1217I~
    public static final int HV_SHANTEN_KAN_WORD              =2;      //issue kan for word tile if shanten<=2//~vaaiI~
//  public static final int HV_INTENT_CHANTA_MELD            =5;      //intent chanta if candidate>=5//~1217I~//~1221R~//~vabcR~
    public static final int HV_INTENT_CHANTA_MELD            =4;      //intent chanta if candidate>=4//~vabcI~
    public static final int HV_INTENT_CHANTA_MELD_WEIGHT2    =4;      //(meld candidate:4)+(sigle term:1)/4//~1221I~
    public static final int HV_INTENT_CHANTA_MELD_WEIGHT1    =1;      //(meld candidate:4)+(sigle term:1)/4//~1221I~
    public static final int HV_INTENT_TANYAO_TAKECTR         =8;     //when ctrTaken>=8//~1217R~
    public static final int HV_INTENT_TANYAO_TILECTR         =2;     //tanyao tile<=2//~1217I~
    public static final int HV_INTENT_TANYAO_TILECTR_EARLY   =3;     //else tanyao tile<=4//~1217I~//~1218R~
    public static final int HV_INTENT_TANYAO_MAX_CHANTA_MELD =2;     //allow chanta meld<=2 for tanyao//~1221I~
    public static final int HV_INTENT_TANYAO_SHANTEN_FORCE   =2;     //if shanten<2 ignore chanta meld & chanta dora//~1223I~
    public static final int HV_INTENT_CTR_CHANTA_7PAIR       =4;     //if chanta pairctr<=4 7pair chanta//~vaaCI~
    public static final int HV_INTENT_CTR_CHANTA_7PAIR_SINGLE=2;     //and hanta single chanta>=2//~vaaCI~
    public static final int HV_INTENT_SHANTEN_7PAIR_ONLY     =1;     //if shanten<=1 no chk standard form//~vaaCR~
                                                                   //~1217I~//~1221R~

	public static final int ERR_DISCARD_OPENREACH=1;                         //~va60I~//~1130M~
	public static final int ERR_DISCARD_PAO_3DRAGON=2;                       //~va60I~//~1130M~
	public static final int ERR_DISCARD_PAO_4WIND=3;                         //~va60I~//~1130M~
	public static final int ERR_DISCARD_PAO_4KAN=4;                          //~va60I~//~1130M~
    //*pairStatus                                                  //~1114I~//~1130M~
	public static final int PS_POSMASK      =0x3f; //  max34        //~1114I~//~1130M~
	public static final int PS_SEQ        =0x0100;                  //~1114I~//~1130M~
	public static final int PS_PON        =0x0200;                  //~1114I~//~1130M~
	public static final int PS_KAN        =0x0400;                  //~1114I~//~1130M~
	public static final int PS_ANKAN      =0x0800;                  //~1114I~//~1130M~
	public static final int PS_CHANKAN    =0x1000;                  //~1114I~//~1130M~
	public static final int PS_TANYAO   =0x010000;                  //~1114I~//~1130M~
	public static final int PS_CHANTA   =0x020000;                  //~1114I~//~1130M~
	public static final int PS_ALLSEQ   =0x040000;                  //~1114I~//~1130M~
	public static final int PS_ALLSAME  =0x080000;                 //~1122I~//~1130M~
	public static final int PS_NOTNUM   =0x100000;                  //~1114I~//~1122R~//~1130M~
                                                                   //~1114I~//~1130M~
	public static final int CSI_MAN     =0;                        //~1114I~//~1130M~
	public static final int CSI_PIN     =1;                        //~1114I~//~1130M~
	public static final int CSI_SOU     =2;                        //~1114I~//~1130M~
	public static final int CSI_WORD    =3;                        //~1114I~//~1130M~
	public static final int CSI_ALL     =4;                        //~1114I~//~1130M~
	public static final int CSI_TERMINAL=5;                        //~1114I~//~1130M~
	public static final int CSI_TANYAO  =6;                        //~1114I~//~1130M~
	public static final int CSI_SINGLE  =7;                        //~1114I~//~1130M~
	public static final int CSI_PAIR    =8;                        //~1114I~//~1130M~
	public static final int CSI_PON     =9;                        //~1114I~//~1130M~
	public static final int CSI_KAN     =10;                       //~1114I~//~1130M~
	public static final int CSI_CALL_PONKAN=11;//all expose including other player                     //~1115I~//~1116R~//~1130M~
	public static final int CSI_WORD_DUP=12;//all expose including other player//~1215I~
	public static final int CSI_WORD_STARTING=13;    //word tile ctr at first 6(HV_CTR_CHK_WORD_STARTING:6) take//~1224R~
	public static final int CSI_MAXENTRY   =14;                       //~1114I~//~1115R~//~1122R~//~1130M~//~1215R~//~1216R~
                                                                   //~1115I~//~1130M~
	public static final int INTENT_NOINTENT         =0x01;         //~1115R~//~1130M~
	public static final int INTENT_SAMECOLOR_MAN    =0x02; //honitsu//~1115R~//~1130M~
	public static final int INTENT_SAMECOLOR_PIN    =0x04; //honitsu//~1115I~//~1130M~
	public static final int INTENT_SAMECOLOR_SOU    =0x08; //honitsu//~1115I~//~1130M~
	public static final int INTENT_SAMECOLOR_ANY    =(INTENT_SAMECOLOR_MAN | INTENT_SAMECOLOR_PIN | INTENT_SAMECOLOR_SOU);//~1219R~
	public static final int INTENT_ALLSAME          =0x10; //toitoi//~1115R~//~1130M~
	public static final int INTENT_ALLSEQ           =0x20; //pinfu //~1115R~//~1130M~
//  public static final int INTENT_TREND_SAME       =0x40; //many seed of SAME//~1115I~//~1130M~//~vaawR~
	public static final int INTENT_TANYAO           =0x80;         //~1116I~//~1130M~
	public static final int INTENT_13ORPHAN       =0x0100; //13orpahn//~1115R~//~1130M~
	public static final int INTENT_7PAIR          =0x0200; //7pair //~1115R~//~1130M~
	public static final int INTENT_NORMAL         =0x0400; //normal//~1115I~//~1130M~
	public static final int INTENT_BY_SHANTEN     =0x0700; //to reset 13orphan,7pair,normal//~1115I~//~1121R~//~1130M~
	public static final int INTENT_GIVEUP_WEAK    =0x1000; //select num link//~1217I~
	public static final int INTENT_GIVEUP         =0x2000; //select genbutu//~1217I~
	public static final int INTENT_CHANTA         =0x4000;         //~1307R~
	public static final int INTENT_3DRAGON        =0x8000;         //~1307I~
                                                                   //~1115I~//~1130M~
	public static final int CALLSTAT_REACH              =0x01; //reach called//~1115R~//~1130M~
	public static final int CALLSTAT_SAME               =0x02; //many call PON/KAN//~1115R~//~1130M~
	public static final int CALLSTAT_REACH_ONESHOT      =0x04; //  //~1219I~
	public static final int CALLSTAT_REACH_SET_SHANTEN  =0x08; //  //~vaaPI~
	public static final int CALLSTAT_REACH_OPEN         =0x10; //reachOpen called//~vab7I~
                                                                   //~1115I~//~1130M~
    public static final int CTR_TILETYPE=34;                      //~1106I~//~va60R~//~1130M~
    public static final int OFFS_WORDTILE=3*9;                    //~1106I~//~va60R~//~1130M~
    public static final int OFFS_WORDTILE_DRAGON=OFFS_WORDTILE+4;  //~va60I~//~1130M~
    public static final int CTR_NUMSUIT=3;                        //~1106I~//~va60R~//~1130M~
    public static final int CTR_SUITTYPE=4;                        //~va60I~//~1130M~
    public static final int CTR_NUMBER_TILE=9;                    //~1106R~//~va60R~//~1130M~
    public static final int CTR_WORD_TILE=7;                       //~va60I~//~1130M~
    public static final int CTR_MAXHAND=HANDCTR_TAKEN;                       //~1106I~//~va60R~//~1130M~
    public static final int CTR_MAXMELD=4;                        //~1106I~//~va60R~//~1130M~
                                                                   //~va60I~//~1130M~
    public static final int CTR_SHANTENTYPE=3;	//normal,13orphan,7pair//~1106I~//~va60R~//~1130M~
    public static final int SHANTEN_STANDARD=0x01;  //don't change value//~va60R~//~1130M~
    public static final int SHANTEN_13ORPHAN=0x02;  //:            //~va60R~//~1130M~
    public static final int SHANTEN_7PAIR   =0x04;  //             //~va60R~//~1130M~
    public static final int MAX_SHANTEN               =13; //max for 13 orphan//~va60R~//~1130M~
                                                                   //~1218I~
    public static final int MELD_NUM_SEQ3              =-3; //max for 13 orphan//~1218I~
    public static final int MELD_NUM_SEQ2              =-2; //max for 13 orphan//~1218I~
    public static final int MELD_NUM_KANCHAN           =-1; //max for 13 orphan//~1218R~
    public static final int MELD_NUM_REDUNDANCY        =-4; //max for 13 orphan//~1218R~
    public static final int MELD_NUM_ORPHAN            =-5; //max for 13 orphan//~1218R~
                                                                   //~1219I~
    public static final int OTHER_MARK_EARTH                =0x01; //~1219R~
//  public static final int OTHER_MARK_COLOR                =0x02;  //~1219I~//~1221R~
//  public static final int OTHER_MARK_DRAWING              =0x04;  //~1219I~//~1224R~
    public static final int OTHER_MARK_REACH                =0x10;  //~1219I~
    public static final int OTHER_MARK_REACH_ONESHOT        =0x20;  //~1219I~//~1220R~
    public static final int OTHER_MARK_DORA                 =0x80; //~1220I~//~1223R~
                                                                   //~1222I~
}                                                                  //~1130I~
