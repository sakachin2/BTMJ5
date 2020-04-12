//*CID://+DATER~: update#= 354;                                    //~@@@@R~//~9214R~
//**********************************************************************//~v101I~
package com.btmtest.dialog;                                          //~@@@@R~

import java.util.HashMap;
import java.util.Map;
import static com.btmtest.StaticVars.AG;                           //~9615I~

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
    public static final int    PSID_ORIENTATION             =1;    //~9412I~
    public static final int    PSID_DEL_TILE_TAKEN          =10;   //~9412I~
    public static final int    PSID_NO_RELATED_RULE         =20;   //~9520I~
    public static final int    PSID_NOTAKE_BUTTON           =30;   //~9630I~
    public static final int    PSID_NODISCARD_BUTTON        =31;   //~9630I~
    public static final int    PSID_NOSOUND                 =40;   //~9C01I~
    public static final int    PSID_BEEPONLY                =41;   //~9C01I~
    public static final int    PSID_VOLUME                  =42;   //+9C02I~
    private static final PSEnumPair[] pairs=                       //~9412I~
    			{                                                  //~9412I~
    				new PSEnumPair(PSID_ORIENTATION          ,"Orientation"          ),//~9412I~
    				new PSEnumPair(PSID_DEL_TILE_TAKEN       ,"DeleteRiverTileTaken" ),//~9412I~
    				new PSEnumPair(PSID_NO_RELATED_RULE      ,"NoRelatedRule"        ),//~9520I~
    				new PSEnumPair(PSID_NOTAKE_BUTTON        ,"NoTakeButton"         ),//~9630I~
    				new PSEnumPair(PSID_NODISCARD_BUTTON     ,"NoDiscardButton"      ),//~9630I~//~9C01R~
    				new PSEnumPair(PSID_NOSOUND              ,"NoSound"              ),//~9C01I~
    				new PSEnumPair(PSID_BEEPONLY             ,"BeepOnly"             ),//~9C01I~
    				new PSEnumPair(PSID_VOLUME               ,"Volume"               ),//+9C02I~
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
        return AG.aPrefSettingEnum.ID2Key.get(Pid);                //~9615I~
    }                                                              //~@@@@I~
}                                                                  //~@@@@I~
