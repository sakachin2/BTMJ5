//*CID://+var8R~: update#= 383;                                    //~var8R~
//**********************************************************************//~v101I~
//2022/09/24 var8 display profile icon                             //~var8I~
//2022/07/04 van1 hungle suuprt for Help                           //~van1I~
//2022/04/22 vamu move playalone option to preference from operation settings//~vamuI~
//2021/09/19 vae9 1ak2(access external audio file) for BTMJ        //~vae9I~
//2021/09/19 vae8 keep sharedPreference to external storage with PrefSetting item.//~vad1I~
//2021/08/24 vad1 game buttons layout for lefty                    //~vad1I~
//2021/06/17 va9f correct reason of reverse orientation did not work(fix orientation was called)//~va9fI~
//                not work because onConfigurationChanged is not fired by RVERSE request//~va9fI~
//2020/10/18 va18 option to diaplay WinAnyway button               //~va18I~
//2020/04/27 va06:BGM                                              //~va06I~
//**********************************************************************//~va06I~
package com.btmtest.dialog;                                          //~@@@@R~

import java.util.HashMap;
import java.util.Map;
import static com.btmtest.StaticVars.AG;                           //~9615I~

import com.btmtest.utils.Dump;

public class PrefSettingEnum                                       //~@@@@R~//~9404R~//~9405R~//~9412R~
{                                                                  //~0914I~//~@@@@R~
    //*********************************************************************//~9412I~
	static class PSEnumPair                                        //~9412I~
    {                                                              //~9412I~
    	int id;                                                    //~9412I~
        String key;                                                //~9412I~
    	public PSEnumPair(int Pid,String Pkey)                     //~9412I~
        {                                                          //~9412I~
        	id=Pid; key=Pkey;                                      //~9412I~
        }                                                          //~9412I~
    }                                                              //~9412I~
    //*********************************************************************//~9412I~
    public static final String PREFKEY_YOURNAME="YourName";        //~vad1I~
    public static final String PREFKEY_SAVED   ="Saved";           //~vad1I~
                                                                   //~vamuI~
    public static final int    DEFAULT_PLAY_ALONE_NOTIFY=1;        //~vamuR~
                                                                   //~vad1I~
    public static final int    PSID_ORIENTATION             =1;    //~9412I~
//  public static final int    PSID_ORIENTATION_PORT_REV    =2;    //~va9fR~
    public static final int    PSID_DEL_TILE_TAKEN          =10;   //~9412I~
    public static final int    PSID_NO_RELATED_RULE         =20;   //~9520I~
    public static final int    PSID_NOTAKE_BUTTON           =30;   //~9630I~
    public static final int    PSID_NODISCARD_BUTTON        =31;   //~9630I~
    public static final int    PSID_NOANYWAY_BUTTON         =32;   //~va18I~
    public static final int    PSID_NOSOUND                 =40;   //~9C01I~
    public static final int    PSID_BEEPONLY                =41;   //~9C01I~
    public static final int    PSID_VOLUME                  =42;   //~9C02I~
    public static final int    PSID_VOLUME_BGM              =43;   //~va06I~
    public static final int    PSID_BGM                     =44;   //~va06I~
    public static final int    PSID_LEFTY_PORTRAIT          =45;   //~vad1I~
    public static final int    PSID_LEFTY_LANDSCAPE         =46;   //~vad1I~
                                                                   //~vad1I~
    public static final int    PSID_YOURNAME                =51;   //~vad1I~
    public static final int    PSID_ALLOW_ROBOT_ALL_BTN     =60;   //~vamuR~
    public static final int    PSID_PLAY_ALONE_NOTIFY       =61;   //~vamuR~
    public static final int    PSID_CURRENT_LANG_HELP       =70;   //~van1I~
    public static final int    PSID_PROFILE_SHOW            =80;   //~var8I~
    public static final int    PSID_USERBGM0                =100;  //~vae9I~
    public static final int    PSID_USERBGM1                =101;  //~vae9I~
    public static final int    PSID_USERBGM2                =102;  //~vae9I~
    public static final int    PSID_USERBGM3                =103;  //~vae9I~
    public static final int    PSID_USERBGM4                =104;  //~vae9I~
    public static final int    PSID_USERBGM5                =105;  //~vae9I~
    public static final int    PSID_USERBGM6                =106;  //~vae9I~
    public static final int    PSID_USERBGM7                =107;  //~vae9I~
    public static final int    PSID_USERBGM8                =108;  //~vae9I~
    public static final int    PSID_USERBGM9                =109;  //~vae9I~
    public static final int    PSID_USERBGM_URI0            =110;  //~vae9I~
    public static final int    PSID_USERBGM_URI1            =111;  //~vae9I~
    public static final int    PSID_USERBGM_URI2            =112;  //~vae9I~
    public static final int    PSID_USERBGM_URI3            =113;  //~vae9I~
    public static final int    PSID_USERBGM_URI4            =114;  //~vae9I~
    public static final int    PSID_USERBGM_URI5            =115;  //~vae9I~
    public static final int    PSID_USERBGM_URI6            =116;  //~vae9I~
    public static final int    PSID_USERBGM_URI7            =117;  //~vae9I~
    public static final int    PSID_USERBGM_URI8            =118;  //~vae9I~
    public static final int    PSID_USERBGM_URI9            =119;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE0          =120;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE1          =121;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE2          =122;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE3          =123;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE4          =124;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE5          =125;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE6          =126;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE7          =127;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE8          =128;  //~vae9I~
    public static final int    PSID_USERBGM_TITLE9          =129;  //~vae9I~
                                                                   //~vae9I~
    public static final int    PSID_USERBGM_ROUND           =150;  //~vae9R~
    public static final int    PSID_USERBGM_SELECTION       =151;  //~vae9I~
    public static final int    PSID_USERBGM_UPICKER         =152;  //~vae9I~
                                                                   //~var8I~
    public static final int    PSID_PROFILE_ME_STRURI       =160;  //~var8R~
    public static final int    PSID_PROFILE_USE_MYOWN       =161;  //~var8I~
    public static final int    PSID_PROFILE_ME_DISPNAME     =162;  //~var8I~
    public static final int    PSID_PROFILE_ME_PATH         =163;  //~var8I~
    public static final int    PSID_PROFILE_ME_TIMESTAMP    =164;  //~var8I~
    public static final int    PSID_PROFILE_ME_SIZE         =165;  //~var8I~
    public static final int    PSID_PROFILE_ME_ID           =166;  //+var8I~
                                                                   //~vad1I~
    private static final PSEnumPair[] pairs=                       //~9412I~
    			{                                                  //~9412I~
    				new PSEnumPair(PSID_ORIENTATION          ,"Orientation"          ),//~9412I~
//  				new PSEnumPair(PSID_ORIENTATION_PORT_REV ,"OrientationPortRev"   ),//~va9fR~
    				new PSEnumPair(PSID_DEL_TILE_TAKEN       ,"DeleteRiverTileTaken" ),//~9412I~
    				new PSEnumPair(PSID_NO_RELATED_RULE      ,"NoRelatedRule"        ),//~9520I~
    				new PSEnumPair(PSID_NOTAKE_BUTTON        ,"NoTakeButton"         ),//~9630I~
    				new PSEnumPair(PSID_NODISCARD_BUTTON     ,"NoDiscardButton"      ),//~9630I~//~9C01R~
    				new PSEnumPair(PSID_NOANYWAY_BUTTON      ,"NoAnywayButton"       ),//~va18I~
    				new PSEnumPair(PSID_NOSOUND              ,"NoSound"              ),//~9C01I~
    				new PSEnumPair(PSID_BEEPONLY             ,"BeepOnly"             ),//~9C01I~
    				new PSEnumPair(PSID_VOLUME               ,"Volume"               ),//~9C02I~
    				new PSEnumPair(PSID_VOLUME_BGM           ,"BGMVol"               ),//~va06I~
    				new PSEnumPair(PSID_BGM                  ,"BGMType"              ),//~va06I~
    				new PSEnumPair(PSID_LEFTY_PORTRAIT       ,"LeftyPort"            ),//~vad1I~
    				new PSEnumPair(PSID_LEFTY_LANDSCAPE      ,"LeftyLand"            ),//~vad1I~
                                                                   //~vad1I~
    				new PSEnumPair(PSID_YOURNAME             ,PREFKEY_YOURNAME       ),//~vad1I~
                                                                   //~vamuI~
    				new PSEnumPair(PSID_ALLOW_ROBOT_ALL_BTN  ,"RobotPlayerAllBtnPref"),//~vamuR~
    				new PSEnumPair(PSID_PLAY_ALONE_NOTIFY    ,"PlayAloneNotifyPref" ),//~vamuR~
                                                                   //~vae9I~
    				new PSEnumPair(PSID_CURRENT_LANG_HELP    ,"LangHelp"            ),//~van1I~
                                                                   //~van1I~
    				new PSEnumPair(PSID_PROFILE_SHOW         ,"ProfileShow"         ),//~var8I~
                                                                   //~var8I~
    				new PSEnumPair(PSID_USERBGM0             ,"UserBGM0"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM1             ,"UserBGM1"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM2             ,"UserBGM2"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM3             ,"UserBGM3"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM4             ,"UserBGM4"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM5             ,"UserBGM5"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM6             ,"UserBGM6"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM7             ,"UserBGM7"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM8             ,"UserBGM8"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM9             ,"UserBGM9"             ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI0         ,"UserBGMUri0"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI1         ,"UserBGMUri1"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI2         ,"UserBGMUri2"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI3         ,"UserBGMUri3"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI4         ,"UserBGMUri4"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI5         ,"UserBGMUri5"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI6         ,"UserBGMUri6"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI7         ,"UserBGMUri7"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI8         ,"UserBGMUri8"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_URI9         ,"UserBGMUri9"          ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE0       ,"UserBGMTitle0"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE1       ,"UserBGMTitle1"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE2       ,"UserBGMTitle2"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE3       ,"UserBGMTitle3"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE4       ,"UserBGMTitle4"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE5       ,"UserBGMTitle5"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE6       ,"UserBGMTitle6"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE7       ,"UserBGMTitle7"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE8       ,"UserBGMTitle8"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_TITLE9       ,"UserBGMTitle9"        ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_ROUND        ,"UserBGMRound"       ),//~vae9R~
    				new PSEnumPair(PSID_USERBGM_SELECTION    ,"UserBGMSelection"     ),//~vae9I~
    				new PSEnumPair(PSID_USERBGM_UPICKER      ,"UserBGMUPicker"       ),//~vae9I~
                                                                   //~var8I~
    				new PSEnumPair(PSID_PROFILE_ME_STRURI    ,"ProfMe"               ),//~var8R~
    				new PSEnumPair(PSID_PROFILE_USE_MYOWN    ,"ProfUseMyOwn"         ),//~var8I~
    				new PSEnumPair(PSID_PROFILE_ME_DISPNAME  ,"ProfDispName"         ),//~var8I~
    				new PSEnumPair(PSID_PROFILE_ME_PATH      ,"ProfPath"             ),//~var8I~
    				new PSEnumPair(PSID_PROFILE_ME_TIMESTAMP ,"ProfTS"               ),//~var8I~
    				new PSEnumPair(PSID_PROFILE_ME_SIZE      ,"ProfSize"             ),//~var8I~
    				new PSEnumPair(PSID_PROFILE_ME_ID        ,"ProfID"               ),//+var8I~
                };                                                  //~9412I~
    //***********************************************************  //~@@@@I~//~9404R~
//  private static final Map<Integer,String> ID2Key=new HashMap<Integer,String>();//~@@@@I~//~9404R~//~9615R~
    private Map<Integer,String> ID2Key=new HashMap<Integer,String>();//~9615I~
    //***********************************************************  //~9404I~
	public PrefSettingEnum()                                       //~9615I~
    {                                                              //~9615I~
    	AG.aPrefSettingEnum=this;                                  //~9615I~
        init();                                                    //~9615I~
    }                                                              //~9615I~
//  static                                                         //~@@@@I~//~9615R~
    private void init()                                            //~9615I~
    {                                                              //~@@@@I~
    	for (PSEnumPair pair:pairs)                                //~9404R~//~9405R~//~9412R~
        {                                                          //~@@@@I~
        	ID2Key.put(pair.id,pair.key);                          //~9404R~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
    //**************************                                   //~@@@@I~
    public  static String getKeyPS(int Pid)                       //~@@@@R~//~9404R~//~9405R~//~9412R~
    {                                                              //~@@@@I~
//      return ID2Key.get(Pid);                                      //~@@@@I~//~9404R~//~9615R~
//      return AG.aPrefSettingEnum.ID2Key.get(Pid);                //~9615I~//~vamuR~
        String rc=AG.aPrefSettingEnum.ID2Key.get(Pid);             //~vamuI~
        if (Dump.Y) Dump.println("PrefSettingEnum.getKeyPS id="+Pid+",rc="+rc);//~vamuI~
        return rc;                                                 //~vamuI~
    }                                                              //~@@@@I~
}                                                                  //~@@@@I~
