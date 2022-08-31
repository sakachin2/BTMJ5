//*CID://+vaqiR~:                             update#=  136;       //+vaqiR~
//*************************************************************************//~v106I~
//2022/08/25 vaqi real cause of vapb is call paly at resume after permission granted even noBGM on.//+vaqiI~
//2021/09/19 vaea stop also non userBGM when pause                 //~vae8I~
//2021/09/19 vae9 1ak2(access external audio file) for BTMJ        //~vae9I~
//2021/09/19 vae8 keep sharedPreference to external storage with PrefSetting item.//~vae8R~
//2021/08/11 vac3 add BGM kouka                                    //~vac3I~
//2021/03/15 va6i add BGM of eburishou kouka                       //~va6iI~
//2021/03/15 va6h (BUG)BGM not stop iby MenuInGame                 //~va6hI~
//2020/04/27 va06:BGM                                              //~va06I~
//*************************************************************************//~va06I~
package com.btmtest.utils.sound;                                         //~9C01I~//~9C03R~

                                                                   //~9C01I~
import static com.btmtest.StaticVars.AG;                           //~9C01M~
import static com.btmtest.dialog.PrefSetting.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Status.*;

import java.util.Arrays;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.btmtest.R;
import com.btmtest.dialog.PrefSetting;
import com.btmtest.game.Status;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Tables;
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.Toolkit;

//public class BGMList implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener //~1327I~//~va06R~
public class BGMList                                               //~va06I~
{                                                                  //~1327R~
//****************                                                 //~1A08I~
    private Tables[] Ssoundtbl={                                   //~9C02I~
    				new Tables(SOUNDID_BGM_TOP,              R.raw.bgm_top),//use MediaPlayer for BGM(long audio)//~va06R~
    				new Tables(SOUNDID_BGM_GAME1SLOW,         R.raw.bgm_in_game_haru_slow),//use MediaPlayer for BGM(long audio)//~va06I~
    				new Tables(SOUNDID_BGM_GAME1FAST,         R.raw.bgm_in_game_haru_fast),//use MediaPlayer for BGM(long audio)//~va06I~
    				new Tables(SOUNDID_BGM_GAME2SLOW,         R.raw.bgm_in_game_natu_slow),//use MediaPlayer for BGM(long audio)//~va06I~
    				new Tables(SOUNDID_BGM_GAME2FAST,         R.raw.bgm_in_game_natu_fast),//use MediaPlayer for BGM(long audio)//~va06I~
    				new Tables(SOUNDID_BGM_GAME3SLOW,         R.raw.bgm_in_game_aki_slow),//use MediaPlayer for BGM(long audio)//~va06I~
    				new Tables(SOUNDID_BGM_GAME3FAST,         R.raw.bgm_in_game_aki_fast),//use MediaPlayer for BGM(long audio)//~va06I~
    				new Tables(SOUNDID_BGM_GAME4SLOW,         R.raw.bgm_in_game_fuyu_slow),//use MediaPlayer for BGM(long audio)//~va06I~
    				new Tables(SOUNDID_BGM_GAME4FAST,         R.raw.bgm_in_game_fuyu_fast),//use MediaPlayer for BGM(long audio)//~va06I~
    				new Tables(SOUNDID_BGM_EBURISHOU,         R.raw.bgm_eburisho_kouka),//~va6iI~
    				new Tables(SOUNDID_BGM_MIZUCHUKOUKA,      R.raw.bgm_mizuchu_kouka),//~va6iI~
    				new Tables(SOUNDID_BGM_TOUCHIKUKOUKA,     R.raw.bgm_touchiku_kouka180),//~vac3I~
    				new Tables(SOUNDID_BGM_KYOUTO,            R.raw.bgm_3koryoka60),//~vac3R~
                    };                                             //~1A08I~
    private static final int MAX_ERRCTR=4;                         //~1A3bI~//~9C02M~
//  boolean Busy;                                                  //~va06R~

	@SuppressLint("ParserError")
//  private int playCtr,releaseCtr;                                 //~v106R~//~9C02R~//~va06R~
    public boolean swNoSound;                                     //~va06I~//~vae9R~
                                                          //~1327I~
//  String Name,Queued;                                            //~va06R~
    private MediaPlayer currentPlayer;                             //~va06R~
    private MediaPlayer pausedPlayer;                              //~vaeaI~
    private int currentID;                                         //~va06R~
    public float levelVolume=(float)0.0; //0--1.0                      //~1327I~//~9C02R~//~va06R~//~vae9R~
//  private boolean[] swLevelChanged=new boolean[Ssoundtbl.length]; //~9C02R~//~va06R~
	private boolean[] swPrepared=new boolean[Ssoundtbl.length];    //~9C03I~
	private int typeBGM;                                           //~va06I~
	public boolean swVolChanged;                                  //~va06R~//~vae9R~
	public boolean swUserBGM;                                      //~vae8R~//~vaeaR~//~vae9R~
    //*****************************************************************//~9C03I~//~va06R~
	public BGMList()                                               //~va06R~
	{                                                              //~1327R~
        if (Dump.Y) Dump.println("BGMList constructor");           //~va06I~
//  	Arrays.fill(swLevelChanged,true);                          //~9C02I~//~va06R~
    	resetOption();                                             //~va06I~
	}
    //******************************************************************//~va06I~
    //*from Sound                                                  //~vae8R~
    //******************************************************************//~vae8R~
    public void resetOption()                                      //~va06I~
    {                                                              //~va06I~
        typeBGM=PrefSetting.getBGMType();                          //~vaeaI~
        swUserBGM=typeBGM==PS_BGM_USER;           //~vae8R~        //~vaeaR~
//      swNoSound= PrefSetting.isNoSound();                        //~va06R~
        swNoSound=PrefSetting.isNoBGM();                           //~va06I~
//      swBeep=PrefSetting.isBeepOnly();                           //~va06I~
        float v=PrefSetting.getBGMVolume();                              //~va06R~
        swVolChanged=v!=levelVolume;                               //~va06I~
        levelVolume=v;                                             //~va06I~
        AG.aUMediaStore.resetOption();                             //~vae8R~
        if (swNoSound)                                             //~va6hI~
    		stopAll();                                             //~va6hI~
    	if (Dump.Y) Dump.println("BGMList.resetOption swNoSound="+swNoSound+",volume="+levelVolume+",oldVol="+v);//~va06R~
    }                                                              //~va06I~
    //******************************************************************//~vae8R~
    //*from PrefSetting at reinstalation recovery                  //~vae8R~
    //******************************************************************//~vae8R~
    public void recoverOption()                                    //~vae8R~
    {                                                              //~vae8R~
    	if (Dump.Y) Dump.println("BGMList.recoverOption OLD swNoSound="+swNoSound+",volume="+levelVolume);//~vae8R~
        boolean swNoSoundOld=swNoSound;                            //~vae8R~
        swNoSound=PrefSetting.isNoBGM();                           //~vae8R~
        float v=PrefSetting.getBGMVolume();                        //~vae8R~
//      swVolChanged=v!=levelVolume;                               //~vae8R~
        levelVolume=v;                                             //~vae8R~
    	if (Dump.Y) Dump.println("BGMList.recoverOption NEW swNoSound="+swNoSound+",volume="+levelVolume);//~vae8R~
        AG.aUMediaStore.recoverOption();                           //~vae8R~
        if (swNoSound!=swNoSoundOld)                               //~vae8R~
        {                                                          //~vae8R~
        	if (swNoSound)                                         //~vae8R~
    			stopAll();                                         //~vae8R~
        }                                                          //~vae8R~
    }                                                              //~vae8R~
    //******************************************************************//~vaeaR~
    public void onResume()                                         //~vaeaR~
    {                                                              //~vaeaR~
    	if (Dump.Y) Dump.println("BGMList.onResume swNoSound="+swNoSound);//~vaeaR~
        if (swNoSound)                                             //+vaqiI~
        {                                                          //+vaqiI~
	    	if (Dump.Y) Dump.println("BGMList.onResume return by swNoSound");//+vaqiI~
        	return;                                                //+vaqiI~
        }                                                          //+vaqiI~
        try                                                        //~vaeaI~
        {                                                          //~vaeaI~
            if (pausedPlayer!=null)                                //~vaeaI~
            {                                                      //~vaeaI~
                pausedPlayer.start();                              //~vaeaI~
                pausedPlayer=null;                                 //~vaeaI~
            }                                                      //~vaeaI~
            else                                                   //~vaeaI~
            {                                                      //~vaeaI~
                int bgmID=PrefSetting.getSoundID();                               //~vaeaR~
                if (bgmID>MAX_USERBGM)    //No sound or UserBGM              //~vaeaI~
                    play(bgmID);                                 //~vaeaI~
            }                                                      //~vaeaI~
        }                                                          //~vaeaI~
        catch(Exception e)                                         //~vaeaI~
        {                                                          //~vaeaI~
        	Dump.println(e,"BGMList.onResume");                    //~vaeaI~
        }                                                          //~vaeaI~
    }                                                              //~vaeaR~
    //******************************************************************//~vaeaI~
    public void onPause()                                          //~vaeaI~
    {                                                              //~vaeaI~
    	if (Dump.Y) Dump.println("BGMList.onPause swNoSound="+swNoSound);//~vaeaR~
        try                                                        //~vaeaI~
        {                                                          //~vaeaI~
            if (currentPlayer!=null)                               //~vaeaI~
            {                                                      //~vaeaI~
                currentPlayer.pause();                             //~vaeaI~
            }                                                      //~vaeaI~
	    	pausedPlayer=currentPlayer;                            //~vaeaI~
        }                                                          //~vaeaI~
        catch(Exception e)                                         //~vaeaI~
        {                                                          //~vaeaI~
        	Dump.println(e,"BGMList.onPause");                     //~vaeaI~
        }                                                          //~vaeaI~
    }                                                              //~vaeaI~
    //******************************************************************//~vae9I~
    public void playBGMTop()                                       //~vae9I~
    {                                                              //~vae9I~
    	if (Dump.Y) Dump.println("BGMList.playBGMTop swNoSound="+swNoSound);//~vae9R~
    	if (swNoSound)                                             //~vae9I~
        	return;                                                //~vae9I~
        if (swUserBGM)                                             //~vae9I~
			AG.aUMediaStore.playBGMTop();                          //~vae9I~
        else                                                       //~vae9I~
  			play(SOUNDID_BGM_TOP);                                 //~vae9I~
    }                                                              //~vae9I~
//    //******************************************************************//~va06R~
//    public void setVolume(float Plevel)                            //~9C02R~//~va06R~
//    {                                                              //~9C02I~//~va06R~
//        if (Dump.Y) Dump.println("BGMList setVolume level="+Plevel);//~9C02I~//~va06R~
////      playCtr=0;      //for newGame after endgameReturn by F1    //~9C02I~//~va06R~
////      releaseCtr=0;                                              //~9C02I~//~va06R~
////      Busy=false;                                                //~9C02R~//~9C03M~//~va06R~
//        if (levelVolume!=Plevel)                                   //~9C02I~//~va06R~
//        {                                                          //~9C02I~//~va06R~
//            levelVolume=Plevel;                                    //~9C02I~//~va06R~
//            Arrays.fill(swLevelChanged,true);                                   //~9C02I~//~va06R~
//        }                                                          //~9C02I~//~va06R~
//    }                                                              //~9C02I~//~va06R~
//***************************                                      //~9C03I~
//	public synchronized void play(String name)                     //~va06R~
  	public synchronized void play(int Psoundid)                    //~va06I~
	{                                                              //~9C02R~
        if (Dump.Y) Dump.println("BGMList.play swVolChanged="+swVolChanged+",currentID="+currentID+",swNosound="+swNoSound+",id="+Psoundid+",currentID="+currentID+",isPlaying="+isPlaying(Psoundid));     //~9C02I~//~9C03R~//~va06R~//~vae9R~
        if (swNoSound || Psoundid<0) //BGM for before startgame    //~va06R~
        {                                                          //~va06I~
	    	stopSound();                                           //~va06I~
        	return;                                                //~va06I~
        }                                                          //~va06I~
//  	if (busy())                                                //~9C02I~//~va06R~
//  	{                                                          //~1327R~//~va06R~
//  		return;                                                //~va06R~
//  	}                                                           //~1327I~//~va06R~
//  	Name=name;                                                 //~va06R~
//  	playSound();//~1327I~                                      //~va06R~
		if (Psoundid!=currentID || swVolChanged || currentPlayer==null || !currentPlayer.isPlaying())//~va06R~
        {                                                          //~va06I~
    		stopAll();      //also UserBGM                                     //~va06R~//~vae9R~
    		playSound(Psoundid);                                   //~va06R~
        }                                                          //~va06I~
	}
//***************************                                      //~va06I~
//  public boolean isPlaying(String Pname)                          //~9C03I~//~va06R~
    public boolean isPlaying(int Psoundid)                         //~va06I~
	{                                                              //~9C03I~
//    	MediaPlayer p=(MediaPlayer)Tables.find(Ssoundtbl,Pname,null);                  //~9C03I~//~va06R~
      	MediaPlayer p=(MediaPlayer)Tables.find(Ssoundtbl,Psoundid,null);//~va06I~
        if (p==null)                                               //~9C03I~
        	return false;                                          //~9C03I~
        boolean rc=p.isPlaying();                                  //~9C03I~
        if (Dump.Y) Dump.println("BGMList.isPlaying soundid="+Psoundid+",rc="+rc);//~9C03I~//~va06R~
		return rc;
	}                                                              //~9C03I~
//***************************                                      //~1A0aI~
//    public boolean busy ()                                       //~va06R~
//    {                                                              //~v106I~//~va06R~
//        Busy=playCtr>releaseCtr;                                   //~v106M~//~va06R~
//        if (Dump.Y) Dump.println("BGMList Busy="+Busy+",create="+playCtr+",release="+releaseCtr);//~1A08I~//~va06R~
//        return Busy;                                               //~v106M~//~va06R~
//    }                                                            //~va06R~
////***************************                                      //~1A0aI~//~va06R~
//    public boolean busy (String Pfnm)                              //~1A0aI~//~va06R~
//    {                                                              //~1A0aI~//~va06R~
//        Busy=playCtr>releaseCtr;                                   //~1A0aI~//~va06R~
//        if (Dump.Y) Dump.println("BGMList fnm="+Pfnm+",Busy="+Busy+",create="+playCtr+",release="+releaseCtr);//~1A0aI~//~va06R~
//        if (Busy)                                                  //~1A0aI~//~va06R~
//            synchronized(this)                                     //~9C03R~//~va06R~
//            {                                                      //~9C03I~//~va06R~
//                Queued=Pfnm;                                       //~9C03I~//~va06R~
//                if (Dump.Y) Dump.println("BGMList Qued="+Pfnm);  //~9C03I~//~va06R~
//            }                                                      //~9C03I~//~va06R~
//        return Busy;                                               //~1A0aI~//~va06R~
//    }                                                              //~1A0aI~//~va06R~
//***************************                                      //~1327I~
//*Mediaplayer              *                                      //~1327I~
//***************************                                      //~1327I~
//  private static int errCtr;                                     //~1A3bI~//~9C02R~
    synchronized                                                   //~1327I~
//  private void playSound()                           //~1327I~   //~va06R~
    private void playSound(int Psoundid)                           //~va06I~
    {                                                              //~1327I~
  	    if (Dump.Y) Dump.println("BGMList.playSound start: id="+Psoundid+",volume="+levelVolume);        //~1A3bI~//~9C02R~//~9C03R~//~va06R~
//  	int idx=find(Name);                                        //~9C03I~//~va06R~
    	int idx=Tables.findByNumKey(Ssoundtbl,Psoundid);                            //~va06I~
        if (idx<0)                                                 //~9C03I~
        {                                                          //~vae8R~
        	stopAll();	//also UserBGM                             //~vae8R~
            AG.aUMediaStore.playSound(Psoundid);                   //~vae8R~
        	return;                                                //~1327I~
        }                                                          //~vae8R~
        if (levelVolume==(float)0.0)                                //~9C03I~
        	return;                                                //~9C03I~
        int id=Ssoundtbl[idx].getId();	//resource id                             //~9C03I~//~va06R~
        MediaPlayer player=(MediaPlayer)(Ssoundtbl[idx].getObject());//~9C03R~
        try                                                        //~1327I~
        {                                                          //~1327I~
         if (player==null)                                         //~1A08I~
         {                                                         //~1A08I~
        	player=MediaPlayer.create(AG.context,id);              //~1327I~
            Ssoundtbl[idx].setObject(player);                      //~9C03I~
//          swLevelChanged[idx]=true;   //setVolume                //~9C03I~//~va06R~
            swVolChanged=true;                                     //~va06I~
            swPrepared[idx]=true;                                  //~9C03R~
        	if (Dump.Y) Dump.println("Sound.playSound create player="+Utils.toString(player));//~9C02I~//~9C03M~
         }                                                         //~1A08I~
        if (Dump.Y) Dump.println("Sound.playSound player="+Utils.toString(player));//~9C02I~
//      if (Dump.Y) Dump.println("Sound.playSound idx="+idx+",swLevelChanged="+Arrays.toString(swLevelChanged));//~9C02I~//~va06R~
//      if (swLevelChanged[idx])                                   //~9C03I~//~va06R~
        if (swVolChanged)                                          //~va06I~
        {                                                          //~9C02R~
//       	swLevelChanged[idx]=false;                             //~9C03I~//~va06R~
            player.setVolume(levelVolume,levelVolume);	//left and right volume//~9C02I~
        	if (Dump.Y) Dump.println("Sound.playSound setVolume level="+levelVolume);//~9C02I~
        }                                                          //~9C02R~
//          playCtr++;                                           //~v106I~//~va06R~
  	     	if (Dump.Y) Dump.println("BGMList.playSound before prepare swPrepared="+swPrepared);//~9C03I~//~va06R~
            if (!swPrepared[idx]) //prepared after crweated        //~9C03R~
            {                                                      //~9C03I~
	        	player.prepare();                                  //~9C03R~
	            swPrepared[idx]=true;                              //~9C03I~
  	     		if (Dump.Y) Dump.println("BGMList.playSound after prepare");//~9C03R~//~va06R~
            }                                                      //~9C03I~
        	player.seekTo(0);                                      //~1327I~
  	     	if (Dump.Y) Dump.println("BGM.playSound after seekTo");//~9C03I~//~va06R~
        	player.start();                                         //~1327I~
  	     	if (Dump.Y) Dump.println("BGMList.playSound after start");//~9C03I~//~va06R~
//          player.setOnCompletionListener(this);   //it is said that i is tbe after start//~9C02R~//~va06R~
//          player.setOnErrorListener(this);   //it is said that it is after start//~9C03I~//~va06R~
        	player.setLooping(true);                               //~va06M~
  	     	if (Dump.Y) Dump.println("BGMList.playSound after setListener="+this.toString());//~9C03I~//~va06R~
  	     	if (Dump.Y) Dump.println("playSound end:id="+Psoundid+",player="+ Utils.toString(player));       //~v106I~//~9C03R~
    		currentPlayer=player;                                  //~va06I~
    		currentID=Psoundid;                                    //~va06I~
        }                                                          //~1327I~
        catch(Exception e)                                          //~1327I~
        {                                                          //~1327I~
        	Dump.println(e,"BGMList.playSound Exception id="+Psoundid);          //~1327I~//~1A08R~//~va06R~
        }                                                          //~1327I~
    }                                                              //~1327I~
//    //*******************                                          //~1327I~//~va06R~
//    @Override                                                      //~1327I~//~va06R~
//    public void onCompletion(MediaPlayer Pplayer)                  //~1327I~//~va06R~
//    {                                                              //~1327I~//~va06R~
//        if (Dump.Y) Dump.println("BGMList.onCompletion before stop sound player="+Utils.toString(Pplayer));//~9C03R~//~va06R~
//        stopSound(Pplayer);                                        //~9C03I~//~va06R~
//        if (Dump.Y) Dump.println("BGMList.onCompletion after stopSound");//~v106I~//~9C02R~//~va06R~
//    }                                                              //~1327I~//~va06R~
//    //*******************                                          //~9C03I~//~va06R~
//    @Override                                                      //~9C03I~//~va06R~
//    public boolean onError(MediaPlayer Pplayer,int Pwhat,int Pextra)//~9C03I~//~va06R~
//    {                                                              //~9C03I~//~va06R~
//        if (Dump.Y) Dump.println("BGMList.onError player="+Utils.toString(Pplayer)+",what="+Pwhat+",extra="+Pextra);//~9C03I~//~va06R~
//        return false;   //callback to onCompletion                 //~9C03I~//~va06R~
//    }                                                              //~9C03I~//~va06R~
    //*******************                                          //~va06I~
    public void stopAll()                                          //~va06I~
    {                                                              //~va06I~
        if (Dump.Y) Dump.println("BGMList.stopAll");               //~va06I~
        try                                                        //~va06I~
        {                                                          //~va06I~
            for (int ii=0;ii<Ssoundtbl.length;ii++)                //~va06I~
            {                                                      //~va06I~
                MediaPlayer player=(MediaPlayer)(Ssoundtbl[ii].getObject());//~va06I~
                if (player!=null)                                  //~va06I~
                    stopSound(player);                             //~va06I~
            }                                                      //~va06I~
	        AG.aUMediaStore.stopBGM(false/*pause*/);               //~vae8R~
        }                                                          //~va06I~
        catch(Exception e)                                         //~va06I~
        {                                                          //~va06I~
        	Dump.println(e,"BGMList.stopAll");                      //~va06R~
        }                                                          //~va06I~
    }                                                              //~va06I~
	//*******************                                          //~1327I~
    private void stopSound()                    //~9C03I~          //~va06R~
    {                                                              //~1327I~
        if (Dump.Y) Dump.println("BGMList stopSound currentPlayer="+Utils.toString(currentPlayer));//~va06I~
    	if (currentPlayer!=null && currentPlayer.isPlaying())     //~va06I~
			stopSound(currentPlayer);                              //~va06I~
    }                                                              //~va06I~
	//*******************                                          //~va06I~
    synchronized                                                   //~va06I~
    private void stopSound(MediaPlayer Pplayer)                    //~va06I~
    {                                                              //~va06I~
        currentID=0;                                               //~va06I~
	    MediaPlayer player=Pplayer;                                //~9C03I~
        int idx=Tables.find(Ssoundtbl,Pplayer);	                       //~9C03I~
        if (Dump.Y) Dump.println("BGMList stopSound idx="+idx+",player="+Utils.toString(player));//~9C02R~//~9C03R~//~va06R~
//  	if (player==null)                                          //~1327I~//~va06R~
    	if (player==null || !player.isPlaying())                  //~va06I~
        	return;                                                 //~1327I~
        try                                                        //~1327I~
        {                                                          //~1327I~
        	player.stop();                                         //~1327I~
        	if (Dump.Y) Dump.println("BGMList.stopSound after stop");//~9C03I~//~va06R~
        	player.prepare();                                      //~1A08M~//~9C03R~
            if (idx!=-1)                                           //~9C03I~
	        	swPrepared[idx]=true;                              //~9C03I~
        	if (Dump.Y) Dump.println("BGMList.stopSound after prepare");//~9C03I~//~va06R~
/*@@@@                                                           //~1A08I~
        	player.reset();                                        //~v106I~
            player.setOnCompletionListener(null);                  //~1327I~
        	player.release();                                      //~1327I~
        	if (Dump.Y) Dump.println("BGMList.stopSound reset and release");
  @@@@*/                                                         //~1A08I~
//          releaseCtr++;                                          //~v106I~//~va06R~
//          player=null;                                           //~1327I~//~va06R~
//          String qed=Queued;                                     //~1A0aI~//~va06R~
//  	    if (Dump.Y) Dump.println("BGMList.stopSound Qued="+Queued);//~9C03I~//~va06R~
//          Queued=null;                                           //~1A0aI~//~va06R~
//          if (qed!=null)                                         //~1A0aI~//~va06R~
//          	play(qed);                                         //~1A0aI~//~va06R~
        }                                                          //~1327I~
        catch(Exception e)                                          //~1327I~
        {                                                          //~1327I~
        	Dump.println(e,"id="+Ssoundtbl[idx]+"+Sound Stop Exception player="+Utils.toString(Pplayer));          //~1327I~//~9C03R~
        }                                                          //~1327I~
    }                                                              //~1327I~
//    //*******************                                          //~1A08I~//~va06R~
//    synchronized                                                   //~1A08I~//~va06R~
//    public  void release()                                   //~1A08R~//~va06R~
//    {                                                              //~1A08I~//~va06R~
//        if (Dump.Y) Dump.println("BGMList.release");             //~1A08I~//~9C02R~//~va06R~
//        Arrays.fill(swPrepared,false);                             //~9C03I~//~va06R~
//        for (int ii=0;ii<Ssoundtbl.length;ii++)                    //~1A08I~//~va06R~
//        {                                                          //~1A08I~//~va06R~
//            MediaPlayer p=(MediaPlayer)(Ssoundtbl[ii].getObject());//~1A08I~//~va06R~
//            if (Dump.Y) Dump.println("BGMList:release name="+Ssoundtbl[ii].name+",player="+Utils.toString(p));//~1A08I~//~9C02I~//~va06R~
//            if (p==null)                                           //~1A08I~//~va06R~
//                continue;                                          //~1A08I~//~va06R~
//            try                                                    //~1A08I~//~va06R~
//            {                                                      //~1A08I~//~va06R~
//                p.reset();                                         //~1A08I~//~va06R~
//                p.setOnCompletionListener(null);                   //~1A08I~//~va06R~
//                p.release();                                       //~1A08I~//~va06R~
//                Ssoundtbl[ii].setObject(null);                     //~9C03I~//~va06R~
//            }                                                      //~1A08I~//~va06R~
//            catch(Exception e)                                     //~1A08I~//~va06R~
//            {                                                      //~1A08I~//~va06R~
//                Dump.println(e,"BGMList:release Exception name="+Ssoundtbl[ii].name);//~1A08I~//~va06R~
//            }                                                      //~1A08I~//~va06R~
//        }                                                          //~1A08I~//~va06R~
//        if (Dump.Y) Dump.println("BGMList release end");         //~1A08I~//~va06R~
//    }                                                              //~1A08I~//~va06R~
//    //*******************                                          //~9C03I~//~va06R~
//    private int find(String Pname)                                 //~9C03I~//~va06R~
//    {                                                              //~9C03I~//~va06R~
//        String basename=Pname;                                     //~9C03I~//~va06R~
//        int pos;                                                   //~9C03I~//~va06R~
//    //*********                                                    //~9C03I~//~va06R~
//        int idx=Tables.find(Ssoundtbl,basename);                   //~9C03I~//~va06R~
//        if (Dump.Y) Dump.println("BGMList find name="+Pname+",rc="+idx);//~9C03I~//~va06R~
//        return idx;                                                //~9C03I~//~va06R~
//    }                                                              //~9C03I~//~va06R~
}

