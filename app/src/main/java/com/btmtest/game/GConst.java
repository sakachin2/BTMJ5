//*CID://+vaaRR~: update#= 363;                                    //+vaaRR~
//**********************************************************************//~v101I~
//2021/07/18 vaaR (Bug)GCM_RON from client button may be overtaken by robot take+discard on Server.//+vaaRI~
//                Ron tile at Win call at client is no more lastDiscarded by robot Take+discard.//+vaaRI~
//                Consequently CompReqDlg shows Not ronable format.//+vaaRI~
//                Send current player at Ron and chk it on server  //+vaaRI~
//2021/03/15 va6i add BGM of eburishou kouka                       //~va6iI~
//2021/03/11 va6e add robot name over 3 robot                      //~va6eI~
//2021/02/10 va68 change robot name                                //~va68I~
//2020/04/27 va06:BGM                                              //~va06I~
//v@11 2019/02/02 TakeOne by touch                                 //~v@11I~
//**********************************************************************//~v@11I~
package com.btmtest.game;                                          //~@@@@R~

import android.graphics.Color;

import com.btmtest.AG;
import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.btmtest.StaticVars.*;

public class GConst                                                    //~v@@@R~//~@@@@R~//~va06R~
{                                                                  //~0914I~//~@@@@R~
	public static final int PLAYERS          =4;                   //~@@@@I~
    public static final int PLAYER_YOU       =0;                   //~@@@@I~
    public static final int PLAYER_RIGHT     =1;                   //~@@@@I~
    public static final int PLAYER_FACING    =2;                   //~@@@@I~
    public static final int PLAYER_LEFT      =3;                   //~@@@@I~
                                                                   //~@@@@I~
    public  static final int POINT_REACH=1000;                 //~9427I~//~v@11R~
                                                                   //~v@11I~
    public  static final int PIECE_TYPECTR_PLUS_RED =37;   //(man+pin+sou)*10+E,S,W,N,White,Green,Red//~v@@@M~//~@@@@R~
    public  static final int PIECE_TYPECTR=34; //without red//~v@@@I~//~@@@@R~
    public  static final int PIECE_DUPCTR          =4;             //~v@@@I~//~@@@@M~
    public  static final int PIECE_TILECTR         =PIECE_TYPECTR*PIECE_DUPCTR;    //34*4=136 pieces//~v@@@I~//~@@@@R~
    public  static final int TILECTR_KEEPLEFT   =14; //7*2         //~@@@@R~
    public  static final int MAXCTR_KAN    =4 ; //                 //~@@@@R~
    public  static final int PIECE_NUMBERCTR 			=9; 	//1-9      //~v@@@R~//~@@@@I~
    public  static final int PIECE_NUMBERCTR_PLUSRED    =10;	//Red,1-9  //~v@@@I~//~@@@@I~
    public  static final int PIECE_NUMBERTYPECTR        =3; 	//man,pin,sou//~v@@@M~//~@@@@I~
    public  static final int HANDCTR              =13; 	           //~@@@@R~
    public  static final int HANDCTR_TAKEN        =14;             //~@@@@R~
    public static final  int STOCKCTR_EACH              =17;       //~v@@@R~//~@@@@I~
    public static final  int STOCK_LAYER             =2;           //~@@@@R~
    public static final  int SET_GAMECTR=4;                        //~@@@@I~
	public static final  int PAIRCTR=3;                              //~v@@@I~//~@@@@I~
	public static final  int PAIRCTR_KAN=4;                          //~v@@@I~//~@@@@I~
	public static final  int PAIR_KAN_ADDPOS=3;                    //~@@@@R~
	public static final  int PAIRCTR_REMAIN=1;                     //~@@@@I~
	public static final  int PAIRPOS_RIVER_TAKEN     =2;	//river taken tile pos in the pair in msg exchanging//~@@@@I~
	public static final  int PAIRPOS_RIVER_TAKEN_KAN  =3;           //~@@@@I~
                                                                   //~@@@@I~
	public static final  int PAIRS_MAX=4;                          //~@@@@I~
	public static final  int COLOR_BG_TABLE=Color.argb(0xff,0x00,0x59,0x00);//~v@@@R~//~@@@@R~
	public static final int COLOR_EDITABLE          =R.color.editable;//~v@11I~
	public static final int COLOR_EDITABLE_DISABLED =R.color.markabletext; //editabledisabled;//~v@11I~
                                                                   //~v@11I~
    public static final double RATE_SMALLDEVICE_WIDTH=0.95;       //~9818I~//~v@11I~
    public static final double RATE_SMALLDEVICE_WIDTH_LANDSCAPE=0.7;//~v@11R~
    public static final double RATE_MAX_WIDTH=0.95;                //~v@11I~
                                                                   //~@@@@I~
	public static final int ISTOUCH_NONE=-1;                       //~v@@@I~//~@@@@M~
	public static final int ISTOUCH_SWIPED=100;                    //~v@11I~
	public static final int ISTOUCH_DICE=4;                        //~v@@@I~//~@@@@M~
	public static final int ISTOUCH_PLAYER_MASK=0x0f;              //~v@@@I~//~@@@@M~
	public static final int ISTOUCH_ALL=0x10;                      //~v@@@R~//~@@@@M~
	public static final int ISTOUCH_ANYONE=0x20;                   //~v@@@I~//~@@@@M~
	public static final int ISTOUCH_DISCARD_TIMEOUT =0x40;         //~v@11I~
	public static final int ISTOUCH_TAKEONE =0x80;                 //~v@11I~
	public static final int ISTOUCH_TAKEONE_KAN =0x0100;           //~v@11I~
                                                                   //~@@@@I~
	public static final int KAN_RIVER=1;                           //~@@@@R~
	public static final int KAN_TAKEN=2;                           //~@@@@R~
	public static final int KAN_ADD  =3;                           //~@@@@R~
                                                                   //~@@@@I~
	private static final int ECB_ACTION=1024;                      //~@@@@I~
	public  static final int ECB_ACTION_ENDGAME=ECB_ACTION+1;      //~@@@@I~
	public  static final int ECB_ORIENTATION=ECB_ACTION+2;         //~@@@@I~
	public  static final int ECB_COMPDLG_RESP =ECB_ACTION+3;       //~v@11I~
	public  static final int ECB_ACTION_STARTGAME=ECB_ACTION+4;    //~v@11I~
	public  static final int ECB_ACTION_RESUMEGAME=ECB_ACTION+5;   //~v@11I~
//  public  static final int ECB_COMPRESULT_RESP =ECB_ACTION+4;    //~v@11R~
//    public  static final int ECB_DRAWNHW_RESP =ECB_ACTION+5;     //~v@11R~
	public  static final int ECB_ACTION_REACH=ECB_ACTION+6;        //~v@11I~
                                                                   //~v@11I~
	public  final static int PARMPOS_PLAYER=0;                     //~v@@@I~//~v@11M~
	public  final static int PARMPOS_CTRFORTD=5;  //type/number/flag/ctrRemain/eswn//~v@@@I~//~v@11M~
	public  final static int PARMPOS_TD=1;                          //~v@@@I~//~v@11M~
	public  final static int PARMPOS_COMPLETE_LOOSER_ESWN=1;       //+vaaRI~
	public  final static int PARMPOS_OPEN_OPTION=1;                //~v@@7I~//~v@11M~
	public  final static int PARMPOS_OPEN_TDS=2;                   //~v@@7I~//~v@11M~
	public  final static int PARMPOS_TD_DORA=3;                    //~v@@@I~//~v@11M~
//  public  final static int PARMPOS_ADDITIONAL=4;                 //~v@@@R~//~v@11M~
    public  final static int PARMPOS_ADDITIONAL=PARMPOS_TD+PARMPOS_CTRFORTD;//~v@@@I~//~v@11M~
	public  final static int PARMPOS_SWKAN=PARMPOS_ADDITIONAL;     //~v@@@I~//~v@11M~
	public  final static int PARMPOS_PLAYER2=PARMPOS_ADDITIONAL+1; //~v@@@I~//~v@11M~
	public  final static int PARMPOS_PAIRTD=1;                     //~v@@@I~//~v@11M~
	public  final static int PARMPOS_WAITED_ACTION=1;              //~v@11R~
	public  final static int PARMPOS_WAITER=2;                     //~v@@7R~//~v@11I~
	public  final static int PARMPOS_DRAWN_TYPE=1;                 //~v@@7I~//~v@11M~
	public  final static int PARMPOS_DRAWN_REASON=2;               //~v@@7I~//~v@11M~
	public  final static int PARMPOS_DRAWN_NEXTGAME=3;             //~v@@7I~//~v@11M~
	public  final static int PARMPOS_DRAWN_CONFREQ_SUSPEND=3;      //~v@11I~
	public  final static int PARMPOS_DRAWN_ESWNREQUESTER=4;        //~v@11I~
    public  final static int PARMPOS_DRAWN_RESPSTAT=5;             //~v@11I~
//  public  final static int PARMPOS_DRAWN_RESP_NEXTGAME=0;             //~v@@7R~//~9426R~//~v@11R~
//  public  final static int PARMPOS_DRAWN_RESP_ERROR   =1;        //~v@11R~
    public  final static int PARMPOS_DRAWN_CONF_RESP_NEXTGAME =0;  //~v@11R~
    public  final static int PARMPOS_DRAWN_CONF_RESP_ESWNREQUESTER=1;//~v@11I~
//  public  final static int PARMPOS_DRAWN_CONF_RESP_ERROR =1;     //~v@11R~
    public  final static int PARMPOS_DRAWN_CONF_RESP_ERROR =2;     //~v@11I~
    public  final static int PARMPOS_DRAWN_CONF_RESP_NEXTGAME_RCV =4;//~v@11I~
    public  final static int PARMPOS_DRAWN_CONF_RESP_ESWNREQUESTER_RCV =5;//~v@11I~
//  public  final static int PARMPOS_DRAWN_CONF_RESP_ERROR_RCV =5; //~v@11R~
    public  final static int PARMPOS_DRAWN_CONF_RESP_ERROR_RCV =6; //~v@11R~
//  public  final static int PARMPOS_DRAWN_RESPSTAT=4;             //~9426I~//~v@11R~
//  public  final static int PARMPOS_DRAWN_HWCONFED_ERROR=8;       //~v@11R~
    public  final static int PARMPOS_DRAWN_HWCONFED_ERROR=9;       //~v@11I~
	public  final static int PARMPOS_DRAWN_HW_ERROR=4;             //~v@11I~
	public  final static int PARMPOS_DRAWN_RESPSTAT2=8;            //~v@@7I~//~v@11M~
	public  final static int PARMPOS_DRAWN_DIALOGDATA=2;           //~v@@7R~//~v@11M~
	public  final static int PARMPOS_DRAWN_LAST_SUSPEND=PARMPOS_DRAWN_DIALOGDATA+1;//~v@11I~
	public  final static int PARMPOS_DRAWN_DIALOGDATA_CTR=14;      //~v@@7R~//~v@11M~
                                                                   //~v@11I~
	public  final static int ESWN_E=0;                             //~v@11I~
	public  final static int ESWN_S=1;                             //~v@11I~
	public  final static int ESWN_W=2;                             //~v@11I~
	public  final static int ESWN_N=3;                             //~v@11I~
                                                                   //~v@11I~
    public static final String[] nameESWN=AG.resource.getStringArray(R.array.nameESWN);                                     //~v@@7R~//~v@11R~
    public static final String[] gameSeq=AG.resource.getStringArray(R.array.strGameSeq);                          //~v@11R~
//  public static final String[] robotYourNameDefault=AG.resource.getStringArray(R.array.Default_YourNameRobot);//~va6eI~
    public static final String[] robotYourNameDefaultConst=AG.resource.getStringArray(R.array.Default_YourNameRobot);//~va6eI~
    public static             String[] robotYourNameDefault=new String[PLAYERS];//~va6eR~
    public static final String   strCancel=AG.resource.getString(R.string.Cancel);//~va68I~
                                                                   //~v@11I~
	public static final String PREFKEY_YOURNAME="YourName";          //~v@@@I~//~v@11I~
	public static final String ENCODING= "UTF-8";  //~v@11I~
	public static final String TAB ="\t";                          //~v@11I~
	public static final String CRLF="\n";                          //~v@11I~
                                                                   //~v@11I~
    public static final String SOUND_DISCARD="discard";            //~v@11I~
    public static final String SOUND_TAKE="take";                   //~v@11I~
    public static final String SOUND_REACH="reach";                 //~v@11I~
    public static final String SOUND_RON="ron";                     //~v@11I~
    public static final String SOUND_PON="pon";                    //~v@11I~
    public static final String SOUND_KAN="kan";                    //~v@11I~
    public static final String SOUND_CHII="chii";                  //~v@11I~
                                                                   //~v@11I~
    public static final int SOUNDID_DISCARD=1;                     //~v@11I~
    public static final int SOUNDID_TAKE=2;                        //~v@11I~
    public static final int SOUNDID_REACH=3;                       //~v@11I~
    public static final int SOUNDID_RON=4;                         //~v@11I~
    public static final int SOUNDID_PON=5;                         //~v@11I~
    public static final int SOUNDID_KAN=6;                         //~v@11I~
    public static final int SOUNDID_CHII=7;                        //~v@11I~
    public static final int SOUNDID_DICE_ROLL=8;                   //~v@11I~
    public static final int SOUNDID_DICE_FIX=9;                    //~v@11I~
    public static final int SOUNDID_BGM=10;                        //~va06R~
    public static final int SOUNDID_BGM_TOP=11;                    //~va06R~
    public static final int SOUNDID_BGM_GAME1SLOW=12;              //~va06I~
    public static final int SOUNDID_BGM_GAME1FAST=13;              //~va06I~
    public static final int SOUNDID_BGM_GAME2SLOW=14;              //~va06I~
    public static final int SOUNDID_BGM_GAME2FAST=15;              //~va06I~
    public static final int SOUNDID_BGM_GAME3SLOW=16;              //~va06I~
    public static final int SOUNDID_BGM_GAME3FAST=17;              //~va06I~
    public static final int SOUNDID_BGM_GAME4SLOW=18;              //~va06I~
    public static final int SOUNDID_BGM_GAME4FAST=19;              //~va06I~
    public static final int SOUNDID_BGM_EBURISHOU=20;              //~va6iI~
    public static final int SOUNDID_BGM_MIZUCHUKOUKA=21;           //~va6iI~
    //*******************************************                  //~v@11I~
    public GConst()                                                //~v@11I~
    {                                                              //~v@11I~
    	init();                                                    //~v@11I~
    }                                                              //~v@11I~
    //*******************************************                  //~v@11I~
    private void init()                                            //~v@11I~
    {                                                              //~v@11I~
//        LocalDateTime today=LocalDateTime.now();                 //~va6eR~
//        int day=today.getDayOfYear();                            //~va6eR~
   	    Date date=new Date();	//current datetime                 //~va6eR~
        String strDay=new SimpleDateFormat("dd").format(date);      //~va6eR~
        int day= Utils.parseInt(strDay,1);                            //~va6eI~
        int idx=day%(robotYourNameDefaultConst.length-1);     //keep me//~va6eR~
        for (int ii=0,ctr=0;ii<robotYourNameDefaultConst.length;ii++)    //~va6eI~
        {                                                          //~va6eI~
        	if (ii==0 || ii!=idx+1)                                //~va6eI~
            {                                                      //~va6eI~
		    	robotYourNameDefault[ctr++]=robotYourNameDefaultConst[ii];//~va6eI~
                if (ctr==PLAYERS)                                  //~va6eI~
                	break;                                          //~va6eI~
            }                                                      //~va6eI~
        }                                                          //~va6eI~
        if (Dump.Y) Dump.println("GConst.init day="+day+",robotName="+ Arrays.toString(robotYourNameDefault));//~va6eI~
    }                                                              //~v@11I~
}//class GCMsgId                                                   //~@@@@R~
