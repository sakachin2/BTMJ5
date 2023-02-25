//*CID://+vax1R~: update#= 900;                                    //~vax1R~
//**********************************************************************//~v101I~
//2023/02/22 vax1 add local 3DupSeq(Pure Triple Chow)              //~vax1I~
//2023/02/22 vax0 add local 3Wind:2han                             //~vax0I~
//2023/02/22 vawz 3WindNoHonor; optionally 3/2 han allow RYAKU_ROUND//~vawiI~
//2023/02/14 vawi for future extendability, use valiable for local yaku ID. Rank.rank2//~vawiI~
//2023/02/13 vawh LOcal yaku id should be not paired with any other yaku. ex, SINGLE can be occured with all other yaku//~vawhI~
//2023/02/11 vawc (Bug)Honro is not 4han but 2han                  //~vawcI~
//2023/02/02 vaw3 add local yaku.                                  //~vaw3I~
//		          3SEQNUM=LOCAL+SAMENUM, 3WIND_NONHONOR=LOCAL+3KAN, STRAIGHT3=LOCAL+ALLSAMESEQ, SINGLE=LOCAL+1SAMESEQ//~vaw3R~//~vawcR~
//		          7PAIR28_MAN=LOCAL_+7PAIR28, 7PAIR28_SOU=LOCAL+9GATE, 4SEQNUM=LOCAL+3DRAGON, 7STAR=LOCAL+13ALL//~vaw3R~
//2022/07/23 vap3 Yakuman for discarding OpenReach winning tile    //~vap3I~
//2022/02/20 vakc (Bug)resetWGR did not clear WGR ctr(no user until now)//~vakaI~
//2022/02/20 vaka apply kataagari tsumo option                     //~vakaI~
//2021/07/25 vab6 change Yaku Name display seq on CompReqDlg       //~vab8I~
//2021/04/29 va8u (Bug)ignore furiten/kataagari Take not AllInHand,chk skazuke condition only//~va8uI~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~va11R~

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~//~va11I~

public class Rank                                                  //~va11R~
{                                                                  //~0914I~
//standard                                                         //~va11I~
	public static final int RYAKU_7PAIR    =0;                     //~va11R~
	public static final int RYAKU_TANYAO   =1;                     //~va11R~
	public static final int RYAKU_PINFU    =2;                     //~va11R~
//  public static final int RYAKU_WGR      =3;                     //~va11R~
    public static final int RYAKU_RSV1     =3;                     //~va11I~
	public static final int RYAKU_WIND     =4;                     //~va11R~
	public static final int RYAKU_ROUND    =5;                     //~va11R~
	public static final int RYAKU_1SAMESEQ =6;    //1peiko         //~va11R~
	public static final int RYAKU_3SAMESEQ =7;    //3shiki         //~va11I~
	public static final int RYAKU_3SAMENUM =8;    //3tonko         //~va11I~
	public static final int RYAKU_3SAMEHAND=9;    //3anko          //~va11I~
	public static final int RYAKU_STRAIGHT =10;   //itsu           //~va11R~
	public static final int RYAKU_ALLSAME  =11;   //toitoi         //~va11I~
	public static final int RYAKU_19SEQMIX =12;   //honchan        //~va11I~
	public static final int RYAKU_3KAN     =13;                    //~va11I~
	public static final int RYAKU_2SAMESEQ =14;   //2peiko         //~va11R~
	public static final int RYAKU_19SEQ    =15;   //junchan        //~va11R~
	public static final int RYAKU_FLASHMIX =16;   //honitu         //~va11R~
	public static final int RYAKU_3DRAGONSMALL=17;                 //~va11R~
	public static final int RYAKU_19SAMEMIX=18;   //honro          //~va11R~
	public static final int RYAKU_FLASH    =19;   //chinitu        //~va11R~
                                                                   //~va11I~
	public static final int RYAKU_REACH         =20;               //~va11R~
	public static final int RYAKU_REACH_DOUBLE  =21;               //~va11R~
	public static final int RYAKU_REACH_OPEN    =22;               //~va11R~
	public static final int RYAKU_REACH_JUST    =23; //ippatsu     //~va11R~
	public static final int RYAKU_TAKE_NOEARTH  =24;               //~va11R~
	public static final int RYAKU_LAST_TAKEN    =25; //haitei      //~va11R~
	public static final int RYAKU_LAST_DISCARDED=26; //hotei       //~va11R~
	public static final int RYAKU_KAN_ADD       =27; //chankan     //~va11R~
	public static final int RYAKU_KAN_TAKEN     =28; //rinshan     //~va11R~
//  public static final int RYAKU_DORA          =29; //dora        //~va11R~
//  public static final int RYAKU_RSV2          =29;               //~va11R~//~vaw3R~//~vawhR~
//  public static final int RYAKU_SINGLE        =29;               //~vawhI~//~vawiR~
    public static final int RYAKU_RSV2          =29;               //~vawiI~
//  public static final int RYAKU_LOCAL         =29;               //~vaw3R~
	public static final int RYAKU_NAGASI        =30; //nagasi mangan//~va11R~
	public static final int RYAKU_CHILDRON_NY   =31; //!keep last for ShowYakuDlg append get high or/summ up//~va11R~
                                                                   //~vaw3I~
                                                                   //~vaw3I~
//yakuman                                                          //~va11I~
	public static final int RYAKU_YAKUMAN   =32;                   //~va11R~
	public static final int RYAKU_4SAME     =RYAKU_YAKUMAN;   //4anko//~va11R~
	public static final int RYAKU_13ALL     =RYAKU_YAKUMAN+1;   //kokushi//~va11R~
	public static final int RYAKU_9GATE     =RYAKU_YAKUMAN+2;   //13poto//~va11R~
	public static final int RYAKU_7PAIR28   =RYAKU_YAKUMAN+3;   //daisharin//~va11R~
	public static final int RYAKU_13NOPAIR  =RYAKU_YAKUMAN+4;   //13puto//~va11R~
	public static final int RYAKU_14NOPAIR  =RYAKU_YAKUMAN+5;   //14puto//~va11R~
	public static final int RYAKU_3DRAGON   =RYAKU_YAKUMAN+6;   //daisangen//~va11R~
	public static final int RYAKU_ALLHONOR  =RYAKU_YAKUMAN+7;   //tuiiso//~va11R~
	public static final int RYAKU_4WIND     =RYAKU_YAKUMAN+8;   //4siho big//~va11R~
	public static final int RYAKU_4WINDSMALL=RYAKU_YAKUMAN+9;   //4shiho small//~va11R~
	public static final int RYAKU_ALLGREEN  =RYAKU_YAKUMAN+10;   //ryuiso//~va11R~
	public static final int RYAKU_ALL19     =RYAKU_YAKUMAN+11;  //chinraoyo//~va11R~
	public static final int RYAKU_4KAN      =RYAKU_YAKUMAN+12;  //4kan//~va11R~
                                                                   //~va11I~
	public static final int RYAKU_PARENTTAKE=RYAKU_YAKUMAN+13;  //tenho//~va11R~
	public static final int RYAKU_CHILDTAKE =RYAKU_YAKUMAN+14;  //chiiho//~va11R~
	public static final int RYAKU_CHILDRON  =RYAKU_YAKUMAN+15;  //renho//~va11R~
	public static final int RYAKU_BYRANK    =RYAKU_YAKUMAN+16;  //kazoe yakuman//~va11R~
	public static final int RYAKU_8CONT     =RYAKU_YAKUMAN+17;  //8 renchan//~va11R~
	public static final int RYAKU_4SAME2    =RYAKU_YAKUMAN+18;   //4anko tanki//~va11I~
	public static final int RYAKU_13ALL2    =RYAKU_YAKUMAN+19;  //kokushi 13wait//~va11I~
	public static final int RYAKU_9GATE2    =RYAKU_YAKUMAN+20;  //52;9gate  9wait//~va11R~
	public static final int RYAKU_OPENREACH_DISCARD=RYAKU_YAKUMAN+21;  //discarded winning tile for openreach//~vap3R~
	public static final int RYAKU_LOCAL     =RYAKU_YAKUMAN+23;     //~vaw3R~
                                                                   //~vaw3I~
    public static final int RYAKU_CTR_DORA      =64; //not bit but ctr only//~va11I~
    public static final int RYAKU_CTR_WGR       =65;  //no bit use  //~va11I~
                                                                   //~va11I~
//  private static final int RYAKU_CTRREMAINING=3;                 //~va11R~
    private static final int DORA_SHIFTIN=56;       //56=(3+4)*8   //~va11R~
//  private static final int CTR_ALL=DORA_SHIFTIN;	//56=(3+4)*8   //~va11I~//~vaw3R~
    private static final int CTR_ALL=DORA_SHIFTIN-1;	//56=(3+4)*8 ,-1:MASK_LOCAL//~vaw3I~
    private static final int CTR_ALL_RANK2=31;	//bit size of int  //~vawiI~
    private static final int DORA_MASK=0x0F;     //max 15, actually 13 else kazoe yakuman//~va11R~
    private static final int WGR_SHIFTIN=60;                       //~va11I~
    private static final int WGR_MASK=0x03;      //max 3,actually 2, else big dragon yakuman//~va11R~
    private static final long DORA_MASK_RESET=0xF0FFFFFFFFFFFFFFL; //~va11M~
//  private static final long WGR_MASK_RESET =0x3FFFFFFFFFFFFFFFL; //~va11R~//~vakcR~
    private static final long WGR_MASK_RESET =0xCFFFFFFFFFFFFFFFL; //~vakaI~
    private static final long MASK_STANDARD  =0x00000000FFFFFFFFL; //~va11R~
    private static final long MASK_LOW       =0x00000000FFFFFFFFL; //~va11R~
//  private static final long MASK_YAKUMAN   =0x00FFFFFF00000000L; //~va11I~//~vaw3R~
    private static final long MASK_YAKUMAN   =0x007FFFFF00000000L; //~vaw3I~
    private static final long MASK_LOCAL     =0x0080000000000000L; //~vaw3I~
//  private static final long MASK_YAKU      =0x30FFFFFFFFFFFFFFL; //except dora//~va11R~//~vaw3R~
    private static final long MASK_YAKU      =0x307FFFFFFFFFFFFFL; //except dora//~vaw3I~
    private static final long BIT_BYRANK=(1L<<RYAKU_BYRANK);       //~va11I~
                                                                   //~vab8I~
//*local yaku                                                                   //~vab8I~//~vaw3I~
//*carefull select pair yaku never happen occurs sametime and do not select contained in YAKUID_FIX//~vaw3I~
//	public static final int RYAKU_3SEQNUM       =RYAKU_LOCAL+RYAKU_3SAMENUM;//~vaw3R~//~vawhR~
//	public static final int RYAKU_3SEQNUM       =RYAKU_LOCAL+RYAKU_7PAIR;//~vawhI~//~vawiR~
  	public static final int RYAKU_3SEQNUM       =RYAKU_LOCAL+0;    //~vawiI~
//	public static final int RYAKU_3WIND_NOHONOR =RYAKU_LOCAL+RYAKU_3KAN;//~vaw3I~//~vawhR~
//	public static final int RYAKU_3WIND_NOHONOR =RYAKU_LOCAL+RYAKU_NAGASI;//~vawhI~//~vawiR~
  	public static final int RYAKU_3WIND_NOHONOR =RYAKU_LOCAL+1;    //~vawiI~
//	public static final int RYAKU_STRAIGHT3     =RYAKU_LOCAL+RYAKU_ALLSAME;//~vaw3R~//~vawcR~//~vawhR~
//	public static final int RYAKU_STRAIGHT3     =RYAKU_LOCAL+RYAKU_3DRAGONSMALL;//~vawhI~//~vawiR~
  	public static final int RYAKU_STRAIGHT3     =RYAKU_LOCAL+2;    //~vawiI~
//	public static final int RYAKU_SINGLE        =RYAKU_LOCAL+RYAKU_1SAMESEQ;//~vaw3I~//~vawhR~
  	public static final int RYAKU_SINGLE        =RYAKU_LOCAL+3;    //~vawiI~
  	public static final int RYAKU_3WIND         =RYAKU_LOCAL+4;    //~vax0I~
  	public static final int RYAKU_3DUPSEQ       =RYAKU_LOCAL+5;    //~vax1I~
//*local yakuman                                                   //~vaw3I~
//*carefull select pair yaku never happen occurs sametime          //~vaw3I~
//  public static final int RYAKU_7PAIR28_MAN   =RYAKU_LOCAL+RYAKU_7PAIR28;//~vaw3I~//~vawiR~
//  public static final int RYAKU_7PAIR28_SOU   =RYAKU_LOCAL+RYAKU_9GATE;//~vaw3I~//~vawiR~
//  public static final int RYAKU_4SEQNUM       =RYAKU_LOCAL+RYAKU_3DRAGON;//~vaw3I~//~vawiR~
//  public static final int RYAKU_7STAR         =RYAKU_LOCAL+RYAKU_13ALL;//~vaw3I~//~vawiR~
    public static final int RYAKU_LOCAL_YAKUMAN =RYAKU_LOCAL+16;    //2nd half of rank2//~vawiI~
    public static final int MASK_LOCAL_YAKUMAN  =0x0fff0000;       //~vawiI~
    public static final int RYAKU_7PAIR28_MAN   =RYAKU_LOCAL_YAKUMAN+0;//~vawiI~
    public static final int RYAKU_7PAIR28_SOU   =RYAKU_LOCAL_YAKUMAN+1;//~vawiI~
    public static final int RYAKU_4SEQNUM       =RYAKU_LOCAL_YAKUMAN+2;//~vawiI~
    public static final int RYAKU_7STAR         =RYAKU_LOCAL_YAKUMAN+3;//~vawiI~
                                                                   //~vaw3I~
    public static final int[] SlistLocalYaku={                     //~vaw3R~
		RYAKU_3SEQNUM,                                             //~vaw3I~
		RYAKU_3WIND_NOHONOR,                                       //~vaw3I~
		RYAKU_STRAIGHT3,                                           //~vaw3I~
		RYAKU_SINGLE,                                              //~vaw3I~
		RYAKU_3WIND,                                               //~vax0I~
		RYAKU_3DUPSEQ,                                             //~vax1I~
                                                                   //~vaw3I~
		RYAKU_7PAIR28_MAN,                                         //~vaw3I~
		RYAKU_7PAIR28_SOU,                                         //~vaw3I~
		RYAKU_4SEQNUM,                                             //~vaw3I~
		RYAKU_7STAR,                                               //~vaw3I~
    };                                                             //~vaw3R~
                                                                   //~vaw3I~
    private static final int[] SdisplayTop={                       //~vab8I~
	 RYAKU_REACH,                                                  //~vab8I~
	 RYAKU_REACH_DOUBLE,                                           //~vab8I~
	 RYAKU_REACH_OPEN,                                             //~vab8I~
	 RYAKU_REACH_JUST,                                             //~vab8I~
	 RYAKU_TAKE_NOEARTH,                                           //~vab8I~
	 RYAKU_TANYAO,                                                 //~vab8I~
	 RYAKU_PINFU,                                                  //~vab8I~
	 RYAKU_7PAIR,                                                  //~vab8I~
    	};                                                         //~vab8I~
                                                                   //~va11I~
                                                                   //~va11I~
//  private static final long OVER_YAKUMAN=(1L<<RYAKU_YAKUMAN);    //~va11R~
	public static final int  MIN_RANK_YAKUMAN=13;    //kazoe yakuman//~va11R~
//*rank of yaku                                                    //~va11I~
    public static final int RANK_7PAIR    =1;                      //~va11R~
//  public static final int RANK_7PAIR2   =2;                      //~va11R~
	public static final int RANK_TANYAO   =1;                      //~va11I~
    public static final int RANK_PINFU    =1;                      //~va11R~
    public static final int RANK_RSV1     =1;                      //~va11I~
	public static final int RANK_WIND     =1;                      //~va11I~
	public static final int RANK_ROUND    =1;                      //~va11I~
	public static final int RANK_1SAMESEQ =1;    //1peiko          //~va11I~
	public static final int RANK_3SAMESEQ =2;    //3shiki          //~va11I~
	public static final int RANK_3SAMENUM =2;    //3tonko          //~va11I~
	public static final int RANK_3SAMEHAND=2;    //3anko           //~va11I~
	public static final int RANK_STRAIGHT =2;   //itsu             //~va11R~
	public static final int RANK_ALLSAME  =2;   //toitoi           //~va11I~
	public static final int RANK_19SEQMIX =2;   //honchan          //~va11I~
	public static final int RANK_3KAN     =2;                      //~va11I~
	public static final int RANK_2SAMESEQ =3;   //2peiko           //~va11I~
	public static final int RANK_19SEQ    =3;   //junchan          //~va11I~
	public static final int RANK_FLASHMIX =3;   //honitu           //~va11R~
	public static final int RANK_3DRAGONSMALL=2; //4 with WGR      //~va11R~
//  public static final int RANK_19SAMEMIX=4;   //honro            //~va11I~//~vawcR~
    public static final int RANK_19SAMEMIX=2;   //honro            //~vawcI~
	public static final int RANK_FLASH    =6;   //chinitu          //~va11I~
                                                                   //~vaw3I~
	public static final long YAKUID_FIX=(  0L                          //~va8uI~
//        |  (1L<< RYAKU_7PAIR            )                        //~va8uR~
          |  (1L<< RYAKU_TANYAO           )   //ON only when rulesetting kuitan:Yes//~va8uR~
//        |  (1L<< RYAKU_PINFU            )                        //~va8uR~
//        |  (1L<< RYAKU_RSV1             )                        //~va8uR~
//        |  (1L<< RYAKU_WIND             )                        //~va8uR~
//        |  (1L<< RYAKU_ROUND            )                        //~va8uR~
//        |  (1L<< RYAKU_1SAMESEQ         )         //1peiko       //~va8uR~
//        |  (1L<< RYAKU_3SAMESEQ         )         //3shiki       //~va8uR~
//        |  (1L<< RYAKU_3SAMENUM         )         //3tonko       //~va8uR~
          |  (1L<< RYAKU_3SAMEHAND        )         //3anko        //~va8uR~
//        |  (1L<< RYAKU_STRAIGHT         )        //itsu          //~va8uR~
          |  (1L<< RYAKU_ALLSAME          )        //toitoi        //~va8uR~
          |  (1L<< RYAKU_19SEQMIX         )        //honchan       //~va8uR~
//        |  (1L<< RYAKU_3KAN             )                        //~va8uR~
//        |  (1L<< RYAKU_2SAMESEQ         )        //2peiko        //~va8uR~
          |  (1L<< RYAKU_19SEQ            )        //junchan       //~va8uR~
          |  (1L<< RYAKU_FLASHMIX         )        //honitu        //~va8uR~
//        |  (1L<< RYAKU_3DRAGONSMALL     )        //4 with WGR    //~va8uR~
          |  (1L<< RYAKU_19SAMEMIX        )        //honro         //~va8uR~
          |  (1L<< RYAKU_FLASH            )        //chinitu       //~va8uR~
                                     );                            //~va8uI~
                                                                   //~va11I~
	public static final int RANK_REACH         =1;                 //~va11I~
	public static final int RANK_REACH_DOUBLE  =2; //daburii       //~va11I~
	public static final int RANK_REACH_OPEN    =1;                 //~va11I~
	public static final int RANK_REACH_JUST    =1; //ippatsu       //~va11I~
	public static final int RANK_TAKE_NOEARTH  =1; //tsumo         //~va11I~
	public static final int RANK_LAST_TAKEN    =1; //haitei        //~va11I~
	public static final int RANK_LAST_DISCARDED=1; //hotei         //~va11I~
	public static final int RANK_KAN_ADD       =1; //chankan       //~va11I~
	public static final int RANK_KAN_TAKEN     =1; //rinshan       //~va11I~
	public static final int RANK_RSV2          =1; //              //~va11I~
	public static final int RANK_NAGASHI       =4; //nagashi mangan//~va11I~
	public static final int RANK_CHILDONLY_NY  =4; //renho not yakuman//~va11I~
                                                                   //~va11I~
    public static final int RANK_7PAIR2   =2;                      //~va11I~
    public static final int POINT_7PAIR2=25;                       //~va11I~
    public static final int POINT_7PAIR2_LARGE=30;                 //~va11I~
    public static final int POINT_7PAIR=50;                        //~va11I~
    public static final int POINT_PINFU=20;                        //~va11I~
    public static final int POINT_ALLHAND   =30;                   //~va11I~
    public static final int POINT_NOTALLHAND=20;                   //~va11I~
    public static final int RANK_WGR      =1;                      //~va11I~
    public static final int RANK_DORA     =1;                      //~va11I~
                                                                   //~va11I~
    //rank list                                                    //~va11I~
    public static final int[] ShanS={                            //~va11I~
         RANK_7PAIR                                                //~va11I~
        ,RANK_TANYAO                                               //~va11I~
        ,RANK_PINFU                                                //~va11I~
        ,RANK_RSV1                                                 //~va11I~
        ,RANK_WIND                                                 //~va11I~
        ,RANK_ROUND                                                //~va11I~
        ,RANK_1SAMESEQ     //1peiko                                //~va11I~
        ,RANK_3SAMESEQ     //3shiki                                //~va11I~
        ,RANK_3SAMENUM     //3tonko                                //~va11I~
        ,RANK_3SAMEHAND    //3anko                                 //~va11I~
        ,RANK_STRAIGHT    //itsu                                   //~va11I~
        ,RANK_ALLSAME     //toitoi                                 //~va11I~
        ,RANK_19SEQMIX    //honchan                                //~va11I~
        ,RANK_3KAN                                                 //~va11I~
        ,RANK_2SAMESEQ    //2peiko                                 //~va11I~
        ,RANK_19SEQ       //junchan                                //~va11I~
        ,RANK_FLASHMIX    //honitu                                 //~va11I~
        ,RANK_3DRAGONSMALL                                         //~va11I~
        ,RANK_19SAMEMIX   //honro                                  //~va11I~
        ,RANK_FLASH       //chinitu                                //~va11I~
        ,RANK_REACH                                                //~va11I~
        ,RANK_REACH_DOUBLE   //daburii                             //~va11I~
        ,RANK_REACH_OPEN                                           //~va11I~
        ,RANK_REACH_JUST     //ippatsu                             //~va11I~
        ,RANK_TAKE_NOEARTH   //tsumo                               //~va11I~
        ,RANK_LAST_TAKEN     //haitei                              //~va11I~
        ,RANK_LAST_DISCARDED //hotei                               //~va11I~
        ,RANK_KAN_ADD        //chankan                             //~va11I~
        ,RANK_KAN_TAKEN      //rinshan                             //~va11I~
	    ,RANK_RSV2           //                                    //~va11I~
	    ,RANK_NAGASHI        //nagashi mangan                      //~va11I~
	    ,RANK_CHILDONLY_NY   //renho not yakuman                   //~va11I~
    };                                                             //~va11I~
    //rank list Local                                              //~vaw3I~
	public static final int RANK_3SEQNUM       =2;                 //~vaw3I~
	public static final int RANK_3WIND_NOHONOR =2;                 //~vaw3I~
	public static final int RANK_STRAIGHT3     =2;                 //~vaw3I~
	public static final int RANK_SINGLE        =1;                 //~vaw3I~
	public static final int RANK_3WIND         =2;                 //~vax0I~
	public static final int RANK_3DUPSEQ       =3;                 //~vax1I~
                                                                   //~vaw3I~
    public  static final int[] ShanSLocal={                        //~vaw3R~//~vawiR~
		RANK_3SEQNUM,                                              //~vaw3I~
		RANK_3WIND_NOHONOR,                                        //~vaw3I~
		RANK_STRAIGHT3,                                            //~vaw3I~
		RANK_SINGLE,                                               //~vaw3I~
		RANK_3WIND,                                                //~vax0I~
		RANK_3DUPSEQ,                                              //~vax1I~
    };                                                             //~vaw3I~
    public static final int YCOND_NODOWN  =0;     //"n"            //~va11I~
    public static final int YCOND_DOWN    =1;     //"n/m"          //~va11I~
    public static final int YCOND_MENZEN  =2;     //"n/0"          //~va11I~
    public static final int YCOND_SETTING =3;     //x              //~va11I~
    public static final int YCOND_OUTOF   =4;     //(no info)      //~va11I~
    public static int[] SconditionS={   //0:nodown, 1:down, 2:menzen only 3: by setting, 4:out of consideration//~va11I~
         YCOND_SETTING   //RANK_7PAIR                              //~va11I~
        ,YCOND_SETTING   //RANK_TANYAO                             //~va11I~
        ,YCOND_MENZEN    //RANK_PINFU                              //~va11I~
        ,YCOND_OUTOF     //RANK_RSV1                               //~va11I~
        ,YCOND_NODOWN    //RANK_WIND                               //~va11R~
        ,YCOND_NODOWN    //RANK_ROUND                              //~va11R~
        ,YCOND_MENZEN    //RANK_1SAMESEQ     //1peiko              //~va11I~
        ,YCOND_DOWN      //RANK_3SAMESEQ     //3shiki              //~va11I~
        ,YCOND_NODOWN    //RANK_3SAMENUM     //3tonko              //~va11I~
        ,YCOND_NODOWN    //RANK_3SAMEHAND    //3anko               //~va11I~
        ,YCOND_DOWN      //RANK_STRAIGHT    //itsu                 //~va11I~
        ,YCOND_NODOWN    //RANK_ALLSAME     //toitoi               //~va11I~
        ,YCOND_DOWN      //RANK_19SEQMIX    //honchan              //~va11I~
        ,YCOND_NODOWN    //RANK_3KAN                               //~va11I~
        ,YCOND_MENZEN    //RANK_2SAMESEQ    //2peiko               //~va11I~
        ,YCOND_DOWN      //RANK_19SEQ       //junchan              //~va11I~
        ,YCOND_DOWN      //RANK_FLASHMIX    //honitu               //~va11I~
        ,YCOND_NODOWN    //RANK_3DRAGONSMALL                       //~va11I~
        ,YCOND_NODOWN    //RANK_19SAMEMIX   //honro                //~va11I~
        ,YCOND_DOWN      //RANK_FLASH       //chinitu              //~va11I~
        ,YCOND_OUTOF     //RANK_REACH                              //~va11I~
        ,YCOND_OUTOF     //RANK_REACH_DOUBLE   //daburii           //~va11I~
        ,YCOND_OUTOF     //RANK_REACH_OPEN                         //~va11I~
        ,YCOND_SETTING   //RANK_REACH_JUST     //ippatsu           //~va11R~
        ,YCOND_OUTOF     //RANK_TAKE_NOEARTH   //tsumo             //~va11I~
        ,YCOND_NODOWN    //RANK_LAST_TAKEN     //haitei            //~va11I~
        ,YCOND_NODOWN    //RANK_LAST_DISCARDED //hotei             //~va11I~
        ,YCOND_NODOWN    //RANK_KAN_ADD        //chankan           //~va11I~
        ,YCOND_NODOWN    //RANK_KAN_TAKEN      //rinshan           //~va11I~
	    ,YCOND_OUTOF     //RANK_RSV2                               //~va11I~
	    ,YCOND_OUTOF     //RANK_NAGASHI        //nagashi mangan    //~va11I~
	    ,YCOND_SETTING   //RANK_CHILDONLY_NY   //renho not yakuman //~va11I~
    };                                                             //~va11I~
    public  static int[] SconditionSLocal={   //0:nodown, 1:down, 2:menzen only 3: by setting, 4:out of consideration//~vaw3R~//~vawiR~
		YCOND_NODOWN,	//RANK_3SEQNUM 3renpon,                    //~vaw3I~
//  	YCOND_NODOWN,	//RANK_3WIND_NOHONOR,                      //~vaw3I~//~vawzR~
    	YCOND_SETTING,  //RANK_3WIND_NOHONOR,                      //~vawzR~
		YCOND_DOWN,		//RANK_STRAIGHT3,                          //~vaw3I~
		YCOND_NODOWN,	//RANK_SINGLE,                             //~vaw3I~
		YCOND_NODOWN,	//RANK_3WIND,                              //~vax0I~
		YCOND_SETTING,  //RANK_3DUPSEQ,                            //~vax1I~
    };                                                             //~vaw3I~
                                                                   //~va11I~
	public static final int POINT_YAKUMAN=-1;	//id of yakuman    //~va11I~
    private long rank;                                    //~va11R~
    private int  rank2;                                            //~vawiI~
	public static String[] SyakuNameS;                             //~va11R~
	public static String[] SyakuNameSLocal;                        //~vaw3R~
	public static boolean swZero; //for Test                       //~va11I~
	//*************************************************************************//~va11I~
    static                                                         //~va11I~
    {                                                              //~va11I~
        SyakuNameS=AG.resource.getStringArray(R.array.listYakuAll);//~va11I~
        SyakuNameSLocal=AG.resource.getStringArray(R.array.listYakuAllLocal);//~vaw3I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public Rank()                                                  //~va11R~
    {                                                              //~va11R~
        if (Dump.Y) Dump.println("Rank.Constructor");              //~va11R~
    }                                                              //~va11R~
    public Rank(long Plong)                                        //~va11I~
    {                                                              //~va11I~
    	rank=Plong;                                                //~va11I~
        if (Dump.Y) Dump.println("Rank.Constructor by long "+toString(this));//~va11I~
    }                                                              //~va11I~
    private Rank(long Plong,int Prank2)                            //~vawiI~
    {                                                              //~vawiI~
    	rank=Plong;                                                //~vawiI~
    	rank2=Prank2;                                              //~vawiI~
        if (Dump.Y) Dump.println("Rank.Constructor by long "+toString(this));//~vawiI~
    }                                                              //~vawiI~
    public long getRank()                                          //~vaw3I~
    {                                                              //~vaw3I~
        if (Dump.Y) Dump.println("Rank.getRank rank="+rankToString(rank));//~vaw3I~
    	return rank;                                               //~vaw3I~
    }                                                              //~vaw3I~
    public String toString()                                       //~va11R~
    {                                                              //~va11I~
        return toString(this);                                     //~va11R~
    }                                                              //~va11I~
//  public static String rankToString(long Prank)                  //~va11I~//~vawiR~
    private static String rankToString(long Prank)                 //~vawiI~
    {                                                              //~va11I~
        int low =(int)(Prank & MASK_LOW);                          //~va11I~
        int high=(int)((Prank>>32) & MASK_LOW);                    //~va11I~
        return Integer.toHexString(high)+"-"+Integer.toHexString(low);//~va11I~
    }                                                              //~va11I~
    private static String rankToString(long Prank,int Prank2)      //~vawiI~
    {                                                              //~vawiI~
        int low =(int)(Prank & MASK_LOW);                          //~vawiI~
        int high=(int)((Prank>>32) & MASK_LOW);                    //~vawiI~
        return Integer.toHexString(high)+"-"+Integer.toHexString(low)+":"+Integer.toHexString(Prank2);//~vawiI~
    }                                                              //~vawiI~
//  public static Rank intToRank(int Phigh,int Plow)               //~va11I~//~vawiR~
    public static Rank intToRank(int Phigh,int Plow,int Prank2)    //~vawiI~
    {                                                              //~va11I~
        long high=(long)Phigh<<32;                                 //~va11I~
        high|=(long)Plow & MASK_LOW;                               //~va11R~
//      Rank r=new Rank(high);                                     //~va11I~//~vawiR~
        Rank r=new Rank(high,Prank2);                              //~vawiI~
        if (Dump.Y) Dump.println("Rank.intToRank high="+Integer.toHexString(Phigh)+",low="+Integer.toHexString(Plow)+",rank2="+Integer.toHexString(Prank2)+",rank="+r.toString());//~va11R~//~vawiR~
        return r;                                                  //~va11I~
    }                                                              //~va11I~
    public static Rank intSToRank(int[] PhighLow)                  //~va11I~
    {                                                              //~va11I~
//      return intToRank(PhighLow[0],PhighLow[1]);                 //~va11I~//~vawiR~
        return intToRank(PhighLow[0],PhighLow[1],PhighLow[2]);     //~vawiI~
    }                                                              //~va11I~
    public int[] rankToIntS()                                      //~va11R~
    {                                                              //~va11I~
        int low =(int)(rank & MASK_LOW);                          //~va11I~
        int high=(int)((rank>>32) & MASK_LOW);                    //~va11I~
        if (Dump.Y) Dump.println("Rank.rankToIntS high="+Integer.toHexString(high)+",low="+Integer.toHexString(low)+",rank2="+Integer.toHexString(rank2)+",rank="+toString());//~va11R~//~vawiR~
//      return new int[]{high,low};                                //~va11I~//~vawiR~
        return new int[]{high,low,rank2};                          //~vawiI~
    }                                                              //~va11I~
    public static String toString(Rank Prank)                      //~va11I~
    {                                                              //~va11I~
        if (Prank==null)                                           //~va11I~
            return "null";                                         //~va11I~
//      return rankToString(Prank.rank);                           //~va11R~//~vawiR~
        return rankToString(Prank.rank,Prank.rank2);               //~vawiI~
    }                                                              //~va11I~
    public static String toString(Rank[] PrankS)                   //~va11I~
    {                                                              //~va11I~
        if (PrankS==null)                                           //~va11I~
            return "null";                                         //~va11I~
        StringBuffer sb=new StringBuffer();                        //~va11I~
        int ctr=0;                                                 //~va11I~
        for (Rank r:PrankS)                                        //~va11I~
        {                                                          //~va11I~
            if (ctr!=0)                                            //~vax1I~
        		sb.append(",");                                    //~vax1I~
        	sb.append("["+ctr+"]="+toString(r));                    //~va11I~
            ctr++;                                                 //~va11I~
        }                                                          //~va11I~
        return "rankS="+sb.toString();                             //~va11I~
    }                                                              //~va11I~
    public static String toStringName(Rank[] PrankS)               //~vaw3I~
    {                                                              //~vaw3I~
        if (PrankS==null)                                          //~vaw3I~
            return "null";                                         //~vaw3I~
        StringBuffer sb=new StringBuffer();                        //~vaw3I~
        int ctr=0;                                                 //~vaw3I~
        for (Rank r:PrankS)                                        //~vaw3I~
        {                                                          //~vaw3I~
            if (ctr!=0)                                            //+vax1I~
        		sb.append(",");                                    //+vax1I~
        	sb.append("["+ctr+"]="+toStringName(r));               //~vaw3I~
            ctr++;                                                 //~vaw3I~
        }                                                          //~vaw3I~
        return "rankS="+sb.toString();                             //~vaw3I~
    }                                                              //~vaw3I~
	//*************************************************************************//~va11I~
    public String toStringName()                                   //~va11I~
    {                                                              //~va11I~
        return toStringName(this,false);                           //~va11R~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public String toStringName(boolean PswHonor)                   //~va11R~
    {                                                              //~va11I~
        return toStringName(this,PswHonor);                        //~va11R~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public String toStringName(boolean PswHonor,boolean PswZero)   //~va11I~
    {                                                              //~va11I~
    	swZero=true;                                               //~va11I~
        String rc=toStringName(this,PswHonor);                     //~va11I~
        swZero=false;                                              //~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public static String toStringName(Rank Prank)                  //~va11I~
    {                                                              //~va11I~
	    return Rank.toStringName(Prank,false);                      //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public static String toStringName(int Prank)                   //~va11I~
    {                                                              //~va11I~
        if (Prank>=RYAKU_LOCAL)                                    //~vaw3R~
        	return toStringNameLocal(Prank);                       //~vaw3R~
	    return SyakuNameS[Prank];                                  //~va11I~
    }                                                              //~va11I~
    public static String toStringNameLocal(int Prank)              //~vaw3R~
    {                                                              //~vaw3I~
    	int idx=getIdxLocal(Prank);                                //~vaw3I~
        String rc=idx>=0 ? SyakuNameSLocal[idx] : "Unknown";       //~vaw3I~
        if (Dump.Y) Dump.println("Rank.rankToStringNameLocal rc="+rc+",idx="+idx+",Prank="+Prank+",Prank-RYAKU_LOCAL="+(Prank-RYAKU_LOCAL));//~vaw3R~
        return rc;                                                 //~vaw3I~
    }                                                              //~vaw3I~
	//*************************************************************************//~vawiI~
    public static String toStringNameRank2(int Prank2)             //~vawiI~
    {                                                              //~vawiI~
    	StringBuffer sb=new StringBuffer();                        //~vawiI~
    	int r2=Prank2;                                             //~vawiI~
        int ctr=0;                                                 //~vawiI~
        for (int ii=0;ii<CTR_ALL_RANK2;ii++,r2>>=1)                //~vawiI~
        {                                                          //~vawiI~
        	if ((r2 & 0x01)!=0)                                    //~vawiI~
            {                                                      //~vawiI~
                if (ctr++!=0)                                      //~vawiI~
	            	sb.append(",");                                //~vawiI~
				sb.append(toStringNameLocal(ii+RYAKU_LOCAL));      //~vawiI~
            }                                                      //~vawiI~
        }                                                          //~vawiI~
        String rc=sb.toString();                                   //~vawiI~
        if (Dump.Y) Dump.println("Rank.toStringNameRank2 rc="+rc+",Prank2="+Integer.toHexString(Prank2));//~vawiI~
        return rc;                                                 //~vawiI~
    }                                                              //~vawiI~
    //*************************************************************************//~vaw3R~
    public static int getIdxLocal(int Prank)                       //~vaw3R~
    {                                                              //~vaw3I~
    	int idx=-1;                                                //~vaw3I~
        for (int ii=0;ii<SlistLocalYaku.length;ii++)               //~vaw3I~
        {                                                          //~vaw3I~
        	if (SlistLocalYaku[ii]==Prank)                         //~vaw3I~
            {                                                      //~vaw3I~
            	idx=ii;                                            //~vaw3I~
                break;                                             //~vaw3I~
            }                                                      //~vaw3I~
        }                                                          //~vaw3I~
        return idx;                                                //~vaw3I~
    }                                                              //~vaw3I~
	//*************************************************************************//~va11I~
    public static String toStringName(Rank Prank,boolean PswHonor) //~va11R~
    {                                                              //~va11I~
        if (Prank==null)                                           //~va11I~
            return "null";                                         //~va11I~
        long lr=Prank.rank;                                        //~va11I~
//	    return toStringName(lr,PswHonor);                           //~vaw3I~//~vawiR~
  	    return toStringName(lr,PswHonor,Prank.rank2);              //~vawiI~
    }                                                              //~vaw3I~
//  public static String toStringName(long PlongRank)              //~vaw3I~//~vawiR~
    private static String toStringName(long PlongRank,int Prank2)  //~vawiI~
    {                                                              //~vaw3I~
//    	return toStringName(PlongRank,true/*PswHonor*/);           //~vaw3I~//~vawiR~
    	return toStringName(PlongRank,true/*PswHonor*/,Prank2);    //~vawiI~
    }                                                              //~vaw3I~
//  public static String toStringName(long Prank,boolean PswHonor) //~vaw3I~//~vawiR~
    private static String toStringName(long Prank,boolean PswHonor,int Prank2)//~vawiI~
    {                                                              //~vaw3I~
    	StringBuffer sb=new StringBuffer();                        //~va11I~//~vaw3M~
        long lr=Prank;                                             //~vaw3I~
//      sb.append(rankToString(lr)+"=");  //hex value              //~vaw3R~
        int ctr=0;                                                 //~va11I~
        for (int ii=0;ii<SdisplayTop.length;ii++)                  //~vab8I~
        {                                                          //~vab8I~
            int yakuID=SdisplayTop[ii];                            //~vab8I~
        	if ((lr & (0x01<<yakuID))!=0)                          //~vab8I~
            {                                                      //~vab8I~
                if (ctr++!=0)                                      //~vab8I~
	            	sb.append(",");                                //~vab8I~
            	sb.append(SyakuNameS[yakuID]);                     //~vab8I~
            }                                                      //~vab8I~
        }                                                          //~vab8I~
        boolean swLocal=(Prank & MASK_LOCAL)!=0;              //~vaw3I~
        for (int ii=0;ii<CTR_ALL;ii++,lr>>=1)                       //~va11R~
        {                                                          //~va11I~
        	if (!PswHonor)                                         //~va11I~
            	if (ii==RYAKU_ROUND||ii==RYAKU_WIND)                //~va11I~
                	continue;                                      //~va11I~
            boolean swTop=false;                                   //~vab8I~
            for (int jj=0;jj<SdisplayTop.length;jj++)              //~vab8I~
            {                                                      //~vab8I~
                if (ii==SdisplayTop[jj])                           //~vab8I~
                {                                                  //~vab8I~
                    swTop=true;                                    //~vab8I~
                    break;                                         //~vab8I~
                }                                                  //~vab8I~
            }                                                      //~vab8I~
            if (swTop)                                             //~vab8I~
                continue;                                          //~vab8I~
        	if ((lr & 0x01)!=0)                                    //~va11I~
            {                                                      //~va11I~
                if (ctr++!=0)                                      //~va11I~
	            	sb.append(",");                                //~va11R~
//            if (swLocal && getIdxLocal(ii+RYAKU_LOCAL)!=-1)     //~vaw3I~//~vawiR~
//            	sb.append(toStringName(ii+RYAKU_LOCAL));           //~vaw3R~//~vawiR~
//            else                                                 //~vaw3I~//~vawiR~
            	sb.append(SyakuNameS[ii]);                         //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (swLocal)                                               //~vawiI~
        {                                                          //~vawiI~
            if (ctr++!=0)                                          //~vawiI~
	       		sb.append(",");                                    //~vawiI~
            sb.append(toStringNameRank2(Prank2));                  //~vawiI~
        }                                                          //~vawiI~
        if (PswHonor)                                              //~va11R~
        {                                                          //~va11I~
        	int ctr2=getDora(Prank);                               //~va11R~
        	ctr+=ctr2;                                             //~va11R~
        	if (ctr2!=0 || swZero)                                 //~va11R~
            	sb.append(" "+Utils.getStr(R.string.Label_Dora)+"="+ctr2);//~va11R~
        	int ctr3=getWGR(Prank);                                //~va11R~
        	ctr+=ctr3;                                             //~va11R~
        	if (ctr3!=0 || swZero)                                 //~va11R~
            	sb.append(" "+Utils.getStr(R.string.Label_Dragon)+"="+ctr3);//~va11R~
        }                                                          //~va11I~
        if (ctr==0)                                                //~va11I~
        	return "";                                             //~va11I~//~vaw3R~
//          sb.append("None");                                     //~vaw3R~
        return sb.toString();                                      //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public void addYaku(int Pyaku)                                 //~va11R~
    {                                                              //~va11I~
	    addYaku(this,Pyaku);                                       //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
	//*for yaku and yakuman                                        //~vaw3I~
	//*************************************************************************//~vaw3I~
    public static void addYaku(Rank Prank,int Pyaku)               //~va11I~
    {                                                              //~va11I~
        if ((TestOption.option2 & TestOption.TO2_CHKRANK)!=0)      //~va11I~
	        UView.showToastLong("UARank.addYaku ="+Rank.toStringName(Pyaku))
                    ;//~va11I~
//    	long old=Prank.rank;                                       //~va11I~//~vawiR~
        if (Dump.Y) Dump.println("Rank.addYaku Pyaku(old)="+toStringName(Pyaku));//~vawiI~
      	if (Pyaku>=RYAKU_LOCAL)                                    //~vaw3R~
        {                                                          //~vaw3I~
			addYakuLocal(Prank,Pyaku);                             //~vaw3R~
            return;                                                //~vaw3I~
        }                                                          //~vaw3I~
    	Prank.rank|=(1L<<Pyaku);                                   //~va11I~
        if (Dump.Y) Dump.println("Rank.addYaku Pyaku(new)="+toStringName(Pyaku));//~vawiR~
    }                                                              //~va11I~
	//*************************************************************************//~vaw3I~
    private static void addYakuLocal(Rank Prank,int Pyaku)         //~vaw3R~
    {                                                              //~vaw3I~
        if (Dump.Y) Dump.println("Rank.addYakuLocal Pyaku(old)="+toStringName(Pyaku));//~vawiI~
//    	long old=Prank.rank;                                       //~vaw3I~//~vawiR~
//    	addYaku(Prank,Pyaku-RYAKU_LOCAL);                          //~vaw3I~//~vawiR~
		Prank.rank2|=1<<(Pyaku-RYAKU_LOCAL);                       //~vawiI~
    	Prank.rank|=MASK_LOCAL;                                    //~vaw3R~
        if (Dump.Y) Dump.println("Rank.addYakuLocal Pyaku(new)="+toStringName(Pyaku));//~vawiR~
    }                                                              //~vaw3I~
	//*************************************************************************//~vaw3I~
    public static boolean isContainsYakuLocal(Rank Prank)          //~vaw3R~
    {                                                              //~vaw3I~
    	boolean rc=(Prank.rank & MASK_LOCAL)!=0;                   //~vaw3I~
        if (Dump.Y) Dump.println("Rank.isContainsYakuLocal rc="+rc+",Rank.rank=="+rankToString(Prank.rank));//~vaw3R~
        return rc;
    }                                                              //~vaw3I~
//    //*************************************************************************//~vaw3I~//~vawiR~
//    public static boolean isContainsYakuLocal(long PlongRank) //No User   //~vaw3R~//~vawiR~
//    {                                                              //~vaw3I~//~vawiR~
//        boolean rc=(PlongRank & MASK_LOCAL)!=0;                    //~vaw3I~//~vawiR~
//        if (Dump.Y) Dump.println("Rank.isContainsYakuLocal longRank="+toStringName(PlongRank)+",rc="+rc);//~vaw3I~//~vawiR~
//        return rc;                                                 //~vaw3I~//~vawiR~
//    }                                                              //~vaw3I~//~vawiR~
	//*************************************************************************//~vakaI~
    public static void resetYaku(Rank Prank,int Pyaku)             //~vakaI~
    {                                                              //~vakaI~
        if (Dump.Y) Dump.println("Rank.resetYaku Pyaku="+Integer.toHexString(Pyaku)+",Prank(old)="+toStringName(Prank));//~vawiI~
//    	long old=Prank.rank;                                       //~vakaI~//~vawiR~
      	if (Pyaku>=RYAKU_LOCAL)                                    //~vaw3R~
        {                                                          //~vaw3I~
			resetYakuLocal(Prank,Pyaku);                           //~vaw3R~
            return;                                                //~vaw3I~
        }                                                          //~vaw3I~
    	Prank.rank&=~(1L<<Pyaku);                                  //~vakaR~
        if (Dump.Y) Dump.println("Rank.resetYaku Pyaku="+Integer.toHexString(Pyaku)+"Prank(new)="+toStringName(Prank));//~vawiR~
    }                                                              //~vakaI~
	//*************************************************************************//~vaw3I~
    private static void resetYakuLocal(Rank Prank,int Pyaku)       //~vaw3R~
    {                                                              //~vaw3I~
        if (Dump.Y) Dump.println("Rank.resetYakuLocal Pyaku="+Integer.toHexString(Pyaku)+",Prank(old)="+toStringName(Prank));//~vawiI~
//    	long old=Prank.rank;                                       //~vaw3I~//~vawiR~
//      resetYaku(Prank,Pyaku-RYAKU_LOCAL);                        //~vaw3I~//~vawiR~
    	Prank.rank2&=~(1<<(Pyaku-RYAKU_LOCAL));                          //~vawiI~
      if (Prank.rank2==0)                                                //~vawiI~
    	Prank.rank&=~MASK_LOCAL;                                   //~vaw3R~
        if (Dump.Y) Dump.println("Rank.resetYakuLocal Pyaku="+Integer.toHexString(Pyaku)+",Prank(new)="+toStringName(Prank));//~vawiI~
    }                                                              //~vaw3I~
	//*************************************************************************//~vakaI~
    public static void resetYakuWGR(Rank Prank)                    //~vakaI~
    {                                                              //~vakaI~
    	resetCtr(Prank,RYAKU_CTR_WGR);                             //~vakaI~
    }                                                              //~vakaI~
	//*************************************************************************//~va11I~
    public void addYaku(int Pyaku,int Pctr)                        //~va11I~
    {                                                              //~va11I~
  	    addYaku(this,Pyaku,Pctr);                                  //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
//  public static void addYaku(Rank Prank,int Pyaku,int Pctr)      //~va11I~//~va8uR~
    private static void addYaku(Rank Prank,int Pyaku,int Pctr)     //~va8uI~
    {                                                              //~va11I~
//	    addYaku(Prank,Pyaku);                                      //~va11R~
        setCtr(Prank,Pyaku,Pctr);                                  //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public boolean isYakuman()                              //~va11I~
    {                                                              //~va11I~
        return isYakuman(this);                                    //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public boolean isYakumanOnlyByRank()                           //~va11I~
    {                                                              //~va11I~
    	long y=getYakuman(); 	// =0x00FFFFFF00000000L;       //~va11I~
    	boolean rc=(y & BIT_BYRANK)!=0 && (y-BIT_BYRANK)==0;      //~va11I~
        if (isYakumanLocal(this))                                  //~vawiI~
        	rc=false;                                              //~vawiI~
    	if (Dump.Y) Dump.println("Rank.isYakumanOnlyByRank rc="+rc+",rank="+toString(this));//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public boolean isYakumanExceptByRank()                         //~va11I~
    {                                                              //~va11I~
    	long y=getYakuman(); 	// =0x00FFFFFF00000000L;       //~va11I~
    	boolean rc=(y!=0) && (y & BIT_BYRANK)==0;                     //~va11I~
        if (isYakumanLocal(this))                                  //~vawiI~
        	rc=true;                                               //~vawiI~
    	if (Dump.Y) Dump.println("Rank.isYakumanExceptByRank rc="+rc+",rank="+toString(this));//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
//  public long getYakuman()                                       //~va11I~//~vaw3R~
    private long getYakuman()                                      //~vaw3I~
    {                                                              //~va11I~
    	return (rank & MASK_YAKUMAN);                              //~va11I~
	}                                                              //~va11I~
    //*************************************************************//~va11I~
    public static boolean isYakuman(Rank Prank)                    //~va11R~
    {                                                              //~va11I~
//  	boolean rc=Prank.rank>=OVER_YAKUMAN;                       //~va11R~
        boolean rc=(Prank.rank & MASK_YAKUMAN)!=0;//   =0x00FFFFFF00000000L;//~va11I~
        rc|=isYakumanLocal(Prank);                                      //~vawiI~
//      if (rc)                                                    //~va11R~
//      	if (isContains(Prank,RYAKU_CHILDRON))                  //~va11R~
//          	if (!RuleSettingYaku.isYakumanRank1stChildRon())   //~va11R~
//              	rc=false;                                      //~va11R~
    	if (Dump.Y) Dump.println("Rank.isYakuman rc="+rc+",rank="+toString(Prank));//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~vawiI~
    public static boolean isYakumanLocal(Rank Prank)               //~vawiI~
    {                                                              //~vawiI~
        boolean rc=(Prank.rank & MASK_LOCAL)!=0 && (Prank.rank2 & MASK_LOCAL_YAKUMAN)!=0;//~vawiI~
    	if (Dump.Y) Dump.println("Rank.isYakumanLocal rc="+rc+",rank="+toStringName(Prank));//~vawiI~
        return rc;                                                 //~vawiI~
    }                                                              //~vawiI~
    //*************************************************************//~va11I~
    public boolean isContains(int Pyaku)                           //~va11I~
    {                                                              //~va11I~
//      if (Pyaku>=RYAKU_LOCAL)                                    //~vaw3R~//~vawiR~
//        	return isContainsLocal(this,Pyaku-RYAKU_LOCAL);        //~vaw3I~//~vawiR~
        return isContains(this,Pyaku);                             //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va8uI~
    public boolean isContainsFixFirst()                            //~va8uI~
    {                                                              //~va8uI~
        return isContainsFixFirst(this);                     //~va8uI~
    }                                                              //~va8uI~
    //*************************************************************//~va11I~
    public boolean isContainsAnyYakuExceptDora()                   //~va11R~
    {                                                              //~va11I~
        boolean rc=(rank & MASK_YAKU)!=0;    //  =0x03FFFFFFFFFFFFFFL;//~va11R~
    	if (Dump.Y) Dump.println("Rank.isContainsAnyYakuExceptDora rc="+rc+",rank="+toString(this));//~va11R~
        return rc;
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public static boolean isContains(Rank Prank,int Pyaku)         //~va11R~
    {                                                              //~va11I~
    	if (Pyaku>=RYAKU_LOCAL)                  //~va11R~         //~vaw3R~
            return isContainsLocal(Prank,Pyaku);                   //~vaw3I~
    	boolean rc=(Prank.rank & (1L<<Pyaku))!=0;                  //~vaw3I~
    	if (Dump.Y) Dump.println("Rank.isContains rc="+rc+",yaku="+Pyaku+"="+SyakuNameS[Pyaku]+",rank="+toString(Prank));//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~vaw3I~
    private static boolean isContainsLocal(Rank Prank,int Pyaku)   //~vaw3I~
    {                                                              //~vaw3I~
    	boolean rc=false;                                          //~vaw3I~
    	if ((Prank.rank & MASK_LOCAL)!=0)                          //~vaw3I~
//          rc=isContains(Prank,Pyaku-RYAKU_LOCAL);                //~vaw3R~//~vawiR~
            rc=(Prank.rank2 & (1<<(Pyaku-RYAKU_LOCAL)))!=0;         //~vawiI~
    	if (Dump.Y) Dump.println("Rank.isContains rc="+rc+",Pyaku="+Integer.toHexString(Pyaku)+"="+",rank="+toStringName(Prank));//~vaw3I~//~vawiR~
        return rc;                                                 //~vaw3I~
    }                                                              //~vaw3I~
    //*************************************************************//~va8uI~
    public static boolean isContainsFixFirst(Rank Prank)           //~va8uR~
    {                                                              //~va8uI~
    	boolean rc=isYakuman(Prank);                               //~va8uR~
    	if (!rc)                                                   //~va8uR~
			rc=(Prank.rank & YAKUID_FIX)!=0;                         //~va8uI~
    	if (Dump.Y) Dump.println("Rank.isContainsFixFirst rc="+rc+",Rank="+Prank.toString()+",YAKU_FIX="+rankToString(YAKUID_FIX));//~va8uR~
        return rc;                                                 //~va8uI~
    }                                                              //~va8uI~
    //*************************************************************//~va11I~
    public void reset(int Pyaku)                                   //~va11R~
    {                                                              //~va11I~
	    reset(this,Pyaku);                                         //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public static void reset(Rank Prank,int Pyaku)                 //~va11I~
    {                                                              //~va11I~
//    	long old=Prank.rank;                                       //~va11I~//~vawiR~
    	if (Dump.Y) Dump.println("Rank.reset Pyaku="+Integer.toHexString(Pyaku)+",Prank(old)="+toStringName(Prank));//~vawiI~
      if (Pyaku>=RYAKU_LOCAL)                                      //~vaw3I~
      {                                                            //~vaw3I~
//      reset(Prank,Pyaku-RYAKU_LOCAL);                            //~vaw3R~//~vawiR~
//    	Prank.rank &= ~MASK_LOCAL;                                 //~vaw3I~//~vawiR~
	    resetYakuLocal(Prank,Pyaku);                               //~vawiI~
      }                                                            //~vaw3I~
      else                                                         //~vaw3I~
    	Prank.rank &= ~(1L<<Pyaku);                                //~va11I~
    	if (Dump.Y) Dump.println("Rank.reset Pyaku="+Integer.toHexString(Pyaku)+",Prank(new)="+toStringName(Prank));//~vawiI~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public void mix(Rank Prank2)                                   //~va11R~
    {                                                              //~va11I~
	    mix(this,Prank2);                                     //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public static void mix(Rank Prank1,Rank Prank2)                //~va11I~
    {                                                              //~va11I~
//    	long old=Prank1.rank;                                      //~va11I~//~vawiR~
    	if (Dump.Y) Dump.println("Rank.mix Prank1="+toStringName(Prank1)+",Prank2="+toStringName(Prank2));//~vawiI~
    	Prank1.rank|=Prank2.rank;                                  //~va11I~
    	Prank1.rank2|=Prank2.rank2;                                //~vawiI~
    	if (Dump.Y) Dump.println("Rank.mix Prank1(new)="+toStringName(Prank1));//~vawiR~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public void setDora(int PctrDora)                              //~va11R~
    {                                                              //~va11I~
	    setDora(this,PctrDora);                                    //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public static void setDora(Rank Prank,int PctrDora)            //~va11I~
    {                                                              //~va11I~
    	long old=Prank.rank;                                       //~va11I~
    	resetDora(Prank);                                           //~vakcI~
    	Prank.rank|=((long)PctrDora<<DORA_SHIFTIN);                //~va11I~
    	if (Dump.Y) Dump.println("Rank.setDora PctrDora="+PctrDora+",old="+rankToString(old)+",new="+toString(Prank));//~va11R~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public void setWGR(int PctrDora)                               //~va11R~
    {                                                              //~va11I~
    	setWGR(this,PctrDora);                                     //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public static void setWGR(Rank Prank,int PctrDora)             //~va11I~
    {                                                              //~va11I~
    	long old=Prank.rank;                                       //~va11I~
    	resetWGR(Prank);                                       //~vakaI~//~vakcI~
    	Prank.rank|=((long)PctrDora<<WGR_SHIFTIN);                 //~va11I~
    	if (Dump.Y) Dump.println("Rank.setWGR PctrDora="+PctrDora+",old="+rankToString(old)+",new="+toString(Prank));//~va11R~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public int getDora()                                           //~va11R~
    {                                                              //~va11I~
        return getDora(this);                                      //~va11R~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public static int getDora(Rank Prank)                          //~va11I~
    {                                                              //~va11I~
    	int ctr=(int)(Prank.rank>>DORA_SHIFTIN);                   //~va11I~
        ctr&=DORA_MASK;                                            //~va11I~
//  	if (Dump.Y) Dump.println("Rank.getDora rc="+ctr+",rank="+toString(Prank));//~va11R~
        return ctr;                                                //~va11I~
    }                                                              //~va11I~
    //*************************************************************
    private static int getDora(long Prank)                         //~vaw3R~
    {
    	int ctr=(int)(Prank>>DORA_SHIFTIN);                        //~vaw3I~
        ctr&=DORA_MASK;
        return ctr;
    }
    //*************************************************************//~va11I~
    public int getWGR()                                            //~va11R~
    {                                                              //~va11I~
        return getWGR(this);                                       //~va11R~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public static int getWGR(Rank Prank)                           //~va11I~
    {                                                              //~va11I~
    	int ctr=(int)(Prank.rank>>WGR_SHIFTIN);                    //~va11I~
        ctr&=WGR_MASK;                                             //~va11I~
//  	if (Dump.Y) Dump.println("Rank.getWGR rc="+ctr+",rank="+toString(Prank));//~va11R~
        return ctr;                                                //~va11I~
    }                                                              //~va11I~
    //*************************************************************
    private static int getWGR(long Prank)                          //~vaw3R~
    {
    	int ctr=(int)(Prank>>WGR_SHIFTIN);                         //~vaw3I~
        ctr&=WGR_MASK;
        return ctr;
    }
    //*************************************************************//~va11I~
    public void resetDora()                                        //~va11R~
    {                                                              //~va11I~
    	resetDora(this);                                           //~va11R~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public static void resetDora(Rank Prank)                       //~va11I~
    {                                                              //~va11I~
    	long old=Prank.rank;                                       //~va11I~
    	Prank.rank=(old & DORA_MASK_RESET);                        //~va11I~
    	if (Dump.Y) Dump.println("Rank.resetDora old="+rankToString(old)+",new="+toString(Prank));//~va11R~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public void resetWGR()                                         //~va11R~
    {                                                              //~va11I~
	    resetWGR(this);                                            //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    public static void resetWGR(Rank Prank)                        //~va11I~
    {                                                              //~va11I~
    	long old=Prank.rank;                                       //~va11I~
    	Prank.rank=(old & WGR_MASK_RESET);                         //~va11I~
    	if (Dump.Y) Dump.println("Rank.resetWGR old="+rankToString(old)+",new="+toString(Prank));//~va11R~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
//  public void setCtr(int Pyaku,int Pctr)                         //~va11R~//~va8uR~
    private void setCtr(int Pyaku,int Pctr)                        //~va8uI~
    {                                                              //~va11I~
	    setCtr(this,Pyaku,Pctr);                                   //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~vakaI~
    public  void resetCtr(int Pyaku)                               //~vakaI~
    {                                                              //~vakaI~
	    resetCtr(this,Pyaku);                                      //~vakaR~
    }                                                              //~vakaI~
    //*************************************************************//~va11I~
//  public static void setCtr(Rank Prank,int Pyaku,int Pctr)       //~va11I~//~va8uR~
    private static void setCtr(Rank Prank,int Pyaku,int Pctr)      //~va8uI~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("Rank.setCtr yaku="+Pyaku+",ctr="+Pctr);//~va11R~
    	if (Pyaku==RYAKU_CTR_DORA)                                 //~va11I~
    		setDora(Prank,Pctr);                                   //~va11I~
        else                                                       //~va11I~
    	if (Pyaku==RYAKU_CTR_WGR)                                  //~va11I~
    		setWGR(Prank,Pctr);                                    //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~vakaI~
    private static void resetCtr(Rank Prank,int Pyaku)             //~vakaI~
    {                                                              //~vakaI~
    	if (Dump.Y) Dump.println("Rank.resetCtr yaku="+Pyaku+",old rank="+Prank.toString());//~vakaR~
    	if (Pyaku==RYAKU_CTR_DORA)                                 //~vakaI~
    		resetDora(Prank);                                      //~vakaI~//~vakcR~
        else                                                       //~vakaI~
    	if (Pyaku==RYAKU_CTR_WGR)                                  //~vakaI~
    		resetWGR(Prank);                                       //~vakaI~//~vakcR~
    	if (Dump.Y) Dump.println("Rank.resetCtr yaku="+Pyaku+",new rank="+Prank.toString());//~vakaI~
    }                                                              //~vakaI~
}                                                                  //~va11I~
