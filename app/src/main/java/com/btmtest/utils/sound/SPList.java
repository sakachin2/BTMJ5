//*CID://+vat1R~:                             update#=  112;       //~vae8R~//+vat1R~
//*************************************************************************//~v106I~
//2022/10/16 vat1 deprecated; Java9 new Integer,Boolean,Double-->valueOf//+vat1I~
//2021/09/19 vae8 keep sharedPreference to external storage with PrefSetting item.//~vae8I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//*************************************************************************//~va40I~
package com.btmtest.utils.sound;                                         //~9C01I~//~9C03R~

                                                                   //~9C01I~
import static com.btmtest.StaticVars.AG;                           //~9C01M~
import static com.btmtest.game.GConst.*;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.btmtest.R;
import com.btmtest.dialog.PrefSetting;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Tables;

/**
This is a Sound class to play and store sounds from resources. The class
keeps a list of loaded sounds.
*/

//*******************************************************************//~9C03I~
//*by SoundPool                                                    //~9C03I~
//*******************************************************************//~9C03I~
public class SPList                                                //~9C03R~
{                                                                  //~1327R~
//****************                                                 //~1A08I~
    private Tables[] Ssoundtbl={                                   //~9C02I~
					new Tables(SOUNDID_TAKE         ,              R.raw.take_click),//~1A08I~//~9C01R~//~9C03R~
//  				new Tables(SOUNDID_DISCARD      ,              R.raw.discard_pieceup),//~1A08I~//~9C01R~//~9C03R~
//  				new Tables(SOUNDID_DISCARD      ,              R.raw.discard_click),//~9C01R~//~9C03R~
//  				new Tables(SOUNDID_DISCARD      ,              R.raw.discard_stone),//~9C01I~//~9C03R~//~0410R~
//  				new Tables(SOUNDID_DISCARD      ,              R.raw.discard_cup),//~0410R~
    				new Tables(SOUNDID_DISCARD      ,              R.raw.discard_pen),//~0410I~
    				new Tables(SOUNDID_REACH        ,              R.raw.reach_check),//~9C01R~//~9C03R~
//  				new Tables(SOUNDID_RON          ,              R.raw.ron_gameoverwin),//~9C01R~//~9C03R~//~0410R~
    				new Tables(SOUNDID_RON          ,              R.raw.ron_answer),//~0410I~
					new Tables(SOUNDID_PON          ,              R.raw.pon),//~0408R~
					new Tables(SOUNDID_KAN          ,              R.raw.kan),//~0408R~
					new Tables(SOUNDID_CHII         ,              R.raw.chii),//~0408R~
					new Tables(SOUNDID_DICE_ROLL    ,              R.raw.dice_roll),//~0410I~
					new Tables(SOUNDID_DICE_FIX     ,              R.raw.dice_fix3),//~0410R~
//  				new Tables(SOUNDID_BGM_JPA04    ,              R.raw.gb_jp_a04_2_loop),//use MediaPlayer for BGM(long audio)//~va06I~//~0427R~
                    };                                             //~1A08I~
    private Sound SOUND;                                                      //~1327I~//~9C03R~
    private float volume; //0--1.0                      //~1327I~//~9C02R~//~9C03R~
    private boolean swNoSound,swBeep;                              //~9C03I~
    private SoundPool pool;//~9C03I~
    private int currentStreamID;                                   //~va06I~
    //******************************************************************//~va60I~
	public SPList() //for IT                                       //~va60I~
	{                                                              //~va60I~
        if (Dump.Y) Dump.println("SPList.default constructor");    //~va60I~
	}                                                              //~va60I~
    //******************************************************************//~9C03I~
	public SPList (Sound Psound)                                   //~9C03I~
	{                                                              //~9C03I~
        if (Dump.Y) Dump.println("SPList.constructor");            //~9C03I~
    	SOUND=Psound;                                              //~9C03I~
    	init();                                                    //~9C03I~
	}                                                              //~9C03I~
    //******************************************************************//~va40I~
	@SuppressWarnings("deprecation")                               //~va40I~
    private SoundPool SoundPool_Bellow21()                         //~va40I~
    {                                                              //~va40I~
		return new SoundPool(Ssoundtbl.length,AudioManager.STREAM_MUSIC,0/*srcQuality*/);//~va40I~
	}                                                              //~va40I~
    //******************************************************************//~va40I~
	public void init()                                             //~9C03R~
	{                                                              //~1327R~
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP)   //~9C03I~
//          pool=new SoundPool(Ssoundtbl.length,AudioManager.STREAM_MUSIC,0/*srcQuality*/);//~9C03I~//~va40R~
            pool=SoundPool_Bellow21();                             //~va40I~
        else                                                       //~9C03I~
        {                                                          //~9C03I~
    		AudioAttributes.Builder abuilder=new AudioAttributes.Builder();//~9C03R~
	       	abuilder.setUsage(AudioAttributes.USAGE_GAME);         //~9C03R~
    	   	abuilder.setContentType(AudioAttributes.CONTENT_TYPE_SPEECH);//~9C03R~
		    AudioAttributes attr=abuilder.build();                 //~9C03I~
			SoundPool.Builder sbuilder=new SoundPool.Builder();    //~9C03I~
        	sbuilder.setAudioAttributes(attr);                       //~9C03R~
            sbuilder.setMaxStreams(Ssoundtbl.length);
            pool=sbuilder.build();                                 //~9C03R~
        }                                                          //~9C03I~
        load();                                                    //~9C03I~
	}
    //******************************************************************//~9C03M~
	private void load()                                            //~9C03M~
	{                                                              //~9C02I~//~9C03M~
        if (Dump.Y) Dump.println("SPList.load");                   //~9C03M~
    	for (int ii=0;ii<Ssoundtbl.length;ii++)                    //~9C03M~
        {                                                          //~9C03M~
        	int rawid=Ssoundtbl[ii].getId();                       //~9C03M~
//      	Integer poolid=new Integer(pool.load(AG.activity,rawid,1));//~9C03R~
//          Ssoundtbl[ii].setObject(poolid);                       //~9C03I~
//      	int poolid=new Integer(pool.load(AG.activity,rawid,1));//~9C03I~//+vat1R~
        	int poolid=Integer.valueOf(pool.load(AG.activity,rawid,1));//+vat1I~
            Ssoundtbl[ii].setNumValue(poolid);                     //~9C03I~
	        if (Dump.Y) Dump.println("SPList.load rowid="+Integer.toHexString(rawid)+",poolid="+Integer.toHexString(poolid));//~va06I~
        }                                                          //~0427R~
        pool.setOnLoadCompleteListener(                            //~9C03M~
        	new SoundPool.OnLoadCompleteListener()                 //~9C03M~
            {                                                      //~9C03M~
            	@Override                                          //~9C03M~
                public void onLoadComplete(SoundPool Ppool,int Pid/*sampleID*/,int Pstat)//~9C03M~//~0427R~
                {                                                  //~9C03M~
                	if (Dump.Y) Dump.println("SPList.onLoadComplete id="+Pid+",stat="+Pstat+",pool="+pool.toString());//~9C03M~
                }                                                  //~9C03M~
            });                                                    //~9C03M~
    }                                                              //~9C03M~
    //******************************************************************//~9C03I~
    public void resetOption()                                      //~9C03I~
    {                                                              //~9C03I~
        swNoSound= PrefSetting.isNoSound();                         //~9C03I~
        swBeep=PrefSetting.isBeepOnly();                           //~9C03I~
        volume=PrefSetting.getSoundVolume();                       //~9C03I~
    	if (Dump.Y) Dump.println("Sound.resetOption swNoSound="+swNoSound+",volume="+volume);//~9C03I~
    }                                                              //~9C03I~
    //******************************************************************//~vae8I~
    public void recoverOption()                                    //~vae8I~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("Sound.recoverOption OLD swNoSound="+swNoSound+",volume="+volume);//~vae8I~
        resetOption();                                             //~vae8I~
    }                                                              //~vae8I~
    //******************************************************************//~9C03I~
	public void setVolume(float Pvolume)                           //~9C03I~
    {                                                              //~9C03I~
    	volume=Pvolume;                                            //~9C03I~
    }                                                              //~9C03I~
//***************************                                      //~9C03I~
//  public synchronized void play(String Pname,boolean Pbeep)      //~9C03R~
    public synchronized void play(int Psoundid,boolean Pbeep)      //~9C03I~
	{                                                              //~9C02R~
        if (Dump.Y) Dump.println("SPList.play swNoSound="+swNoSound+",beep="+Pbeep+",soundid="+Psoundid);//~9C03R~
    	if (swNoSound)                                             //~9C03I~
        	return;                                                //~9C03I~
//  	Integer poolid=(Integer)Tables.find(Ssoundtbl,Pname,null); //~9C03R~
//      if (poolid==null)                                          //~9C03I~
//      int id=poolid;                                             //~9C03I~
    	int id=Tables.findNumValue(Ssoundtbl,Psoundid,-1);         //~9C03I~
        if (id==-1)                                                //~9C03R~
        	return;                                                //~9C03I~
        if (Dump.Y) Dump.println("SPList.play before play id="+id);//~9C03I~
        pool.play(id,volume/*left*/,volume/*right*/,0/*priority*/,0/*loop*/,1/*playSpeed*/);//~9C03I~
	}
}

