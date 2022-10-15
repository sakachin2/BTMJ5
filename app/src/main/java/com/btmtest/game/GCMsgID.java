//*CID://+var8R~: update#= 400;                                    //~va70R~//~var8R~
//**********************************************************************//~v101I~
//2022/09/24 var8 display profile icon                             //~var8I~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2020/11/03 va27 Tenpai chk at Reach                              //~va27I~
//**********************************************************************//~va27I~
package com.btmtest.game;                                          //~@@@@R~

import java.util.HashMap;
import java.util.Map;

public enum GCMsgID                                                //~@@@@R~
{                                                                  //~0914I~//~@@@@R~
//    GCMENUM_TEST       (GCM_TEST         ),                      //~@@@@R~
//    GCMENUM_INIT       (GCM_INIT         ),                      //~@@@@R~
//    GCMENUM_DICE       (GCM_DICE         ),                      //~@@@@R~
//    GCMENUM_TOUCH      (GCM_TOUCH        ),                      //~@@@@R~
//    GCMENUM_DEAL       (GCM_DEAL         ),                      //~@@@@R~
//    GCMENUM_DICE_CASTED(GCM_DICE_CASTED  ),                      //~@@@@R~
//    GCMENUM_USERACTION (GCM_USERACTION   ),                      //~@@@@R~
//    GCMENUM_TAKE       (GCM_TAKE         ),                      //~@@@@R~
//    GCMENUM_PON        (GCM_PON          ),                      //~@@@@R~
//    GCMENUM_CHII       (GCM_CHII         ),                      //~@@@@R~
//    GCMENUM_KAN        (GCM_KAN          ),                      //~@@@@R~
//    GCMENUM_REACH      (GCM_REACH        ),                      //~@@@@R~
//    GCMENUM_RON        (GCM_RON          ),                      //~@@@@R~
//    GCMENUM_DISCARD    (GCM_DISCARD      ),                      //~@@@@R~
//    ;                                                            //~@@@@I~
    GCMENUM_TEST       ,                                           //~@@@@I~
    GCMENUM_INIT       ,                                           //~@@@@I~
    GCMENUM_DICE       ,                                           //~@@@@I~
    GCMENUM_TOUCH      ,                                           //~@@@@I~
    GCMENUM_DEAL       ,                                           //~@@@@I~
    GCMENUM_DICE_CASTED,                                           //~@@@@I~
    GCMENUM_SETUP      ,                                           //~@@@@I~
    GCMENUM_REMOTE_DICE,                                           //~@@@@I~
    GCMENUM_USERACTION ,                                           //~@@@@I~
    GCMENUM_TAKE       ,                                           //~@@@@I~
    GCMENUM_PON        ,                                           //~@@@@I~
    GCMENUM_CHII       ,                                           //~@@@@I~
    GCMENUM_KAN        ,                                           //~@@@@I~
    GCMENUM_REACH      ,                                           //~@@@@I~
    GCMENUM_RON        ,                                           //~@@@@I~
    GCMENUM_DISCARD    ,                                           //~@@@@I~
    GCMENUM_OPEN       ,                                           //~@@@@I~
    GCMENUM_NEXT_PLAYER ,                                          //~@@@@R~
    GCMENUM_NEXT_PLAYER_PONKAN ,                                   //~@@@@I~
    GCMENUM_SURFACE_CHG,                                           //~@@@@I~
    ;                                                              //~@@@@I~
    //**************                                               //~@@@@M~
    public static final int    GCM_TEST         = 1;               //~@@@@I~
    public static final int    GCM_INIT         = 2;               //~@@@@I~
    public static final int    GCM_DICE         = 3;               //~@@@@I~
    public static final int    GCM_TOUCH        = 4;               //~@@@@I~
    public static final int    GCM_DEAL         = 5;               //~@@@@I~
//    public static final int    GCM_DORA         = 6;               //~@@@@R~//~9214R~
//    public static final int    GCM_DICE_CASTED  = 6;               //~@@@@I~
    public static final int    GCM_SETUP        = 7;               //~@@@@I~
    public static final int    GCM_REMOTE_DICE  = 8;               //~@@@@I~
    public static final int    GCM_USERACTION   =10;               //~@@@@I~
    public static final int    GCM_TAKE         =11;               //~@@@@I~
    public static final int    GCM_PON          =12;               //~@@@@I~
    public static final int    GCM_CHII         =13;               //~@@@@I~
    public static final int    GCM_KAN          =14;               //~@@@@I~
    public static final int    GCM_REACH        =15;               //~@@@@I~
    public static final int    GCM_RON          =16;               //~@@@@I~
    public static final int    GCM_DISCARD      =17;               //~@@@@I~
    public static final int    GCM_KAN_OR_PON   =18;  //used wjen playaloneNotify mode to highlight btn both Kan and Pon//~va70I~
    public static final int    GCM_REACH_OPEN   =20;               //~9301I~
    public static final int    GCM_OPEN         =21;               //~@@@@I~//~9301I~
    public static final int    GCM_RON_ANYWAY   =24;               //~0205I~
    public static final int    GCM_REACH_RESET  =25;               //~9A30I~
    public static final int    GCM_REACH_OPEN_RESET=26;            //~9A30I~
    public static final int    GCM_FORCE_REACH         =27;        //~va27I~
    public static final int    GCM_FORCE_REACH_OPEN    =28;        //~va27I~
    public static final int    GCM_FORCE_REACH_ENABLE  =29;        //~va27R~
    public static final int    GCM_2TOUCH         =30;             //~9B18I~
//    public static final int    GCM_PON_C        =32;             //~9B16R~//~9B17R~//~9B18R~
//    public static final int    GCM_CHII_C       =33;             //~9B16R~//~9B17R~//~9B18R~
//    public static final int    GCM_KAN_C        =34;             //~9B16R~//~9B17R~//~9B18R~
//    public static final int    GCM_RON_C        =36;             //~9B16R~//~9B17R~//~9B18R~
    public static final int    GCM_SURFACE_CHG =40;               //~@@@@I~
                                                                   //~@@@@I~
    public static final int    GCM_SETTING                = 60;    //~9404I~
    public static final int    GCM_SETTING_RESP           = 61;    //~9406I~
    public static final int    GCM_SETTING_SYNC           = 62;    //~9406R~
    public static final int    GCM_SETTING_SYNC_QUERY     = 70;    //~9621I~
    public static final int    GCM_SETTING_SYNC_RESP      = 71;    //~9621I~
    public static final int    GCM_SETTING_CHANGED        = 72;    //~9621I~
    public static final int    GCM_SETTING_NOTIFY_SYNCOK  = 73;    //~9621I~
    public static final int    GCM_PROFILE_STARTSYNC      = 74;    //~var8I~
    public static final int    GCM_PROFILE_GETIMAGE_C2S   = 75;    //~var8R~
    public static final int    GCM_PROFILE_GETIMAGE_C2SR  = 76;    //~var8I~
    public static final int    GCM_PROFILE_NOTIFY_ALL     = 77;    //~var8R~
    public static final int    GCM_PROFILE_NOTIFY_ALL_RESP= 78;    //~var8I~
    public static final int    GCM_PROFILE_SENDIMAGE_S2C  = 79;    //~var8I~
    public static final int    GCM_PROFILE_SENDIMAGE_S2CR = 80;    //+var8I~
    public static final int    GCM_PROFILE_SYNC_COMP      = 81;    //+var8I~
//  public static final int    GCM_SETTING_HISTORY        = 80;    //~9826I~//+var8R~
    public static final int    GCM_SETTING_HISTORY        = 90;    //+var8I~
                                                                   //~9404I~
    public static final int    GCM_SHOOT_FOR_TEMPSTARTER  =102;  //request shoot dice to determin tempstarter//~@@@@R~//~9404M~
//    public static final int    GCM_TEMPSTARTER          =103;  //request shoot dice to determin player to determin tempstarter//~@@@@I~//~9404I~
    public static final int    GCM_DICE_TEMPSTARTER       =104;  //1st dice shooter rolled//~@@@@R~//~9404M~
    public static final int    GCM_TEMPSTARTER_DECIDED    =105;  //notify tempstarter determined//~@@@@R~//~9404M~
    public static final int    GCM_TEMPSTARTER_DECIDED_ALL=106;  //notify tempstarter determined//~@@@@I~//~9404M~
    public static final int    GCM_TEMPSTARTER_SHOOT      =107;  //request shoot by tempstarter//~@@@@R~//~9404M~
    public static final int    GCM_SETUPEND                   =200;//~@@@@R~
    public static final int    GCM_RECEIVED_APPMSG            =201;//~@@@@I~
//    public static final int    GCM_RECEIVED_APPMSG_RESP     =202;//~@@@@I~//~9404R~
    public static final int    GCM_DICE_CASTREQ               =203;//~@@@@I~
    public static final int    GCM_DICE_CASTED                =204;//~@@@@I~
    public static final int    GCM_DICE_CASTEDRESP            =205;//~@@@@R~
                                                                   //~@@@@I~//~9404M~
    public static final int    GCM_FIRSTDICE                  =210;//~@@@@I~//~9404M~
    public static final int    GCM_FIRSTDICE_SETUP            =211;//~@@@@R~//~9404M~
//  public static final int    GCM_FIRSTDICE_CASTREQ          =212;//~@@@@R~//~9404M~
//  public static final int    GCM_FIRSTDICE_CASTED           =213;//~@@@@R~//~9404M~
//  public static final int    GCM_FIRSTDICE_CASTEDRESP       =214;//~@@@@R~//~9404M~
    public static final int    GCM_FIRSTDICE_SETUP_RESUME     =215;//~9902I~
    public static final int    GCM_TEMPSTARTERDICE            =220;//~@@@@I~//~9404M~
    public static final int    GCM_TEMPSTARTER_SETUP          =221;//~@@@@I~//~9404M~
//  public static final int    GCM_TEMPSTARTER_CASTREQ        =222;//~@@@@R~//~9404M~
//  public static final int    GCM_TEMPSTARTER_CASTED         =223;//~@@@@R~//~9404M~
//  public static final int    GCM_TEMPSTARTER_CASTEDRESP     =224;//~@@@@R~//~9404M~
    public static final int    GCM_TEMPSTARTER_CASTED         =231;//~@@@@I~//~9404M~
    public static final int    GCM_SETUP_MOVE                 =241;//~@@@@R~//~9404M~
    public static final int    GCM_SETUP_MOVED                =242;//~@@@@I~//~9404M~
    public static final int    GCM_POSITION_MOVED             =243;//~@@@@R~//~9404M~
//    public static final int    GCM_POSITION_MOVE            =242;//~@@@@I~//~9404I~
//    public static final int    GCM_POSITION_MOVE_RESP       =243;//~@@@@R~//~9404I~
    public static final int    GCM_STARTGAME                  =250;//~@@@@I~//~9404M~
    public static final int    GCM_STARTGAME_NEXT             =251;//~9502I~
    public static final int    GCM_ENDGAME                    =260;//~@@@@I~//~9404M~
    public static final int    GCM_ENDGAME_DRAWN              =261;//~9303I~//~9404M~
    public static final int    GCM_ENDGAME_SCORE              =262;//~9318I~//~9404M~
    public static final int    GCM_ENDGAME_ACCOUNTS           =263;//~9322I~//~9404M~
    public static final int    GCM_ENDGAME_RETURN             =264;//~9B21I~
    public static final int    GCM_SUSPENDDLG                 =270;//~9822R~
    public static final int    GCM_SUSPENDDLG_IOERR           =271;//~9A21I~
    public static final int    GCM_RESUMEDLG                  =275;//~9830I~
                                                                   //~@@@@I~
    public static final int    GCM_LIGHT_TOUCHED              =301;//~@@@@R~
    public static final int    GCM_LIGHT_TOUCHED_RESP         =302;//~@@@@R~
//  public static final int    GCM_LIGHT_ENABLE               =303;//~@@@@R~
    public static final int    GCM_NEXT_PLAYER                =304;//~@@@@R~//~9404R~
    public static final int    GCM_NEXT_PLAYER_PONKAN         =305;//~@@@@I~//~9404R~
    public static final int    GCM_TIMEOUT_AUTODISCARD        =306;//~9624I~
    public static final int    GCM_TIMEOUT_AUTOTAKE           =307;//~9624I~
    public static final int    GCM_TIMEOUT_AUTOTAKE_KAN       =308;//~9624I~
    public static final int    GCM_TIMEOUT_TO_PONKAN          =310;//~9623I~
    public static final int    GCM_TIMEOUT_TO_TAKE            =311;//~9623I~//~9624R~
    public static final int    GCM_TIMEOUT_TO_LASTDRAWN       =312;//~9624I~
    public static final int    GCM_TIMEOUT_TO_TAKABLE_RINSHAN =313;//~0403R~
    public static final int    GCM_TIMEOUT_AUTOTAKE0          =320;//~9630I~
    public static final int    GCM_TIMEOUT_AUTOTAKE_KAN0      =321;//~9630I~
    public static final int    GCM_TIMEOUT_STOPAUTO           =322;//~9701I~
    public static final int    GCM_TIMEOUT_BLOCK              =323;//~9C05I~
    public static final int    GCM_RESTARTGAME                =330;//~9A26I~
                                                                   //~@@@@I~
    public static final int    GCM_STATUS_CHANGE              =401;//~@@@@I~
    public static final int    GCM_USER_ACTION                =501;//~@@@@I~
    public static final int    GCM_USER_ACTION_RESP           =502;//~@@@@I~
    public static final int    GCM_ERRMSG                     =503;//~@@@@I~
    public static final int    GCM_ERRMSG_ALL                 =504;//client to server, sever will send GCM_ERRMSG//~9A28R~//~0215R~
    public static final int    GCM_ERRMSG_ANDTOAST            =505;//also toast//~9B27I~
    public static final int    GCM_ERRMSG_NOLANG              =506;//language independent;send msgid//~0215I~
    public static final int    GCM_ERRMSG_ALL_NOLANG          =507;//   ://~0215I~
    public static final int    GCM_ERRMSG_ANDTOAST_NOLANG     =508;//   ://~0215I~
    public static final int    GCM_WAITON                     =510;//~@@@@I~
    public static final int    GCM_WAITOFF                    =511;//~@@@@I~
    public static final int    GCM_WAIT_RELEASE_ACTION        =512;//~9627I~
    public static final int    GCM_WAITON2                    =513;//~9B16I~
    public static final int    GCM_WAITOFF2                   =514;//~9B16I~
    public static final int    GCM_COMPDLG_REQ                =520;//~9221R~
    public static final int    GCM_COMPDLG_RESP               =521;//~9221I~
    public static final int    GCM_COMPRESULT_REQ             =522;//~9225I~
    public static final int    GCM_COMPRESULT_RESP            =523;//~9225I~
    public static final int    GCM_DRAWNGAME_REQ              =524;//~9302I~
    public static final int    GCM_DRAWNGAME_RESP             =525;//~9302I~
    public static final int    GCM_COMPDLG_NEXTPLAYER         =528;//~9A12R~
                                                                   //~@@@@I~
    public static final int    GCM_LASTGAME                   =530;//~9522I~//~9523I~
    public static final int    GCM_SUSPENDGAME                =540;//~9817I~
    public static final int    GCM_HISTORY                    =550;//~9825R~
    public static final int    GCM_HISTORY_RESUME             =551;//for resume request from client to server //~9828I~//~9831R~
                                                                   //~9A02I~
    public static final int    GCM_IPOUT                      =600;	//IP msg write from subthread//~9A02I~
                                                                   //~9522I~//~9523M~
    //**************************                                   //~@@@@I~
    private static final Map<Integer,GCMsgID> enumMap=new HashMap<Integer,GCMsgID>();//~@@@@I~
    static                                                         //~@@@@I~
    {                                                              //~@@@@I~
        GCMENUM_TEST       .intVal=  GCM_TEST        ;             //~@@@@I~
        GCMENUM_INIT       .intVal=  GCM_INIT        ;             //~@@@@I~
        GCMENUM_DICE       .intVal=  GCM_DICE        ;             //~@@@@I~
        GCMENUM_TOUCH      .intVal=  GCM_TOUCH       ;             //~@@@@I~
        GCMENUM_DEAL       .intVal=  GCM_DEAL        ;             //~@@@@I~
        GCMENUM_DICE_CASTED.intVal=  GCM_DICE_CASTED ;             //~@@@@I~
        GCMENUM_SETUP      .intVal=  GCM_SETUP       ;             //~@@@@I~
        GCMENUM_REMOTE_DICE.intVal=  GCM_REMOTE_DICE ;             //~@@@@I~
        GCMENUM_USERACTION .intVal=  GCM_USERACTION  ;             //~@@@@I~
        GCMENUM_TAKE       .intVal=  GCM_TAKE        ;             //~@@@@I~
        GCMENUM_PON        .intVal=  GCM_PON         ;             //~@@@@I~
        GCMENUM_CHII       .intVal=  GCM_CHII        ;             //~@@@@I~
        GCMENUM_KAN        .intVal=  GCM_KAN         ;             //~@@@@I~
        GCMENUM_REACH      .intVal=  GCM_REACH       ;             //~@@@@I~
        GCMENUM_RON        .intVal=  GCM_RON         ;             //~@@@@I~
        GCMENUM_DISCARD    .intVal=  GCM_DISCARD     ;             //~@@@@I~
        GCMENUM_OPEN       .intVal=  GCM_OPEN        ;             //~@@@@I~
        GCMENUM_NEXT_PLAYER.intVal=  GCM_NEXT_PLAYER ;             //~@@@@R~
        GCMENUM_NEXT_PLAYER_PONKAN.intVal=  GCM_NEXT_PLAYER_PONKAN ;//~@@@@I~
        GCMENUM_SURFACE_CHG.intVal=  GCM_SURFACE_CHG ;             //~@@@@I~
                                                                   //~@@@@I~
    	for (GCMsgID msgid:GCMsgID.values())                         //~@@@@I~
        {                                                          //~@@@@I~
        	enumMap.put(msgid.intVal,msgid);                       //~@@@@I~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
    //**************************                                   //~@@@@I~
    private int intVal;                                            //~@@@@R~
//    private GCMsgID(int Pval)                                    //~@@@@R~
//    {                                                            //~@@@@R~
//        intVal=Pval;                                             //~@@@@R~
//    }                                                            //~@@@@R~
    public  int getIntVal()                                        //~@@@@R~
    {                                                              //~@@@@I~
        return intVal;                                             //~@@@@I~
    }                                                              //~@@@@I~
    public  static GCMsgID getEnum(int Pval)                       //~@@@@R~
    {                                                              //~@@@@I~
        return enumMap.get(Pval);                                      //~@@@@I~
    }                                                              //~@@@@I~
}                                                                  //~@@@@I~
