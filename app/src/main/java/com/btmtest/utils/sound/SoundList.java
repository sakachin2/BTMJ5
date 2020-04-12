//*CID://+DATER~:                             update#=   89;       //~1Ah4R~//~9C01R~
//*************************************************************************//~v106I~
package com.btmtest.utils.sound;                                         //~9C01I~//~9C03R~

//import com.Asgts.Prop;                                           //~9C01R~
//import com.Asgts.AView;                                          //~9C01R~
//import com.Asgts.R;                                              //~9C01R~
//import com.Asgts.Tables;                                         //~9C01R~
//import com.Asgts.awt.Toolkit;                                      //~v106R~//~9C01R~
                                                                   //~9C01I~
import static com.btmtest.StaticVars.AG;                           //~9C01M~
import static com.btmtest.game.GConst.*;

import java.util.Arrays;
//~9C01M~
import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Tables;
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.Toolkit;

/**
This is a Sound class to play and store sounds from resources. The class
keeps a list of loaded sounds.
*/

//public class SoundList implements Runnable                       //~1A08R~
public class SoundList implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener //~1327I~
{                                                                  //~1327R~
//****************                                                 //~1A08I~
//  private static final Tables[] Ssoundtbl={                           //~1A08I~//~9C02R~
    private Tables[] Ssoundtbl={                                   //~9C02I~
					new Tables(SOUND_TAKE         ,              R.raw.take_click),//~1A08I~//~9C01R~
//  				new Tables(SOUND_DISCARD      ,              R.raw.discard_pieceup),//~1A08I~//~9C01R~
//  				new Tables(SOUND_DISCARD      ,              R.raw.discard_click),//~9C01R~
    				new Tables(SOUND_DISCARD      ,              R.raw.discard_stone),//~9C01I~
    				new Tables(SOUND_REACH        ,              R.raw.reach_check),//~9C01R~
					new Tables(SOUND_RON          ,              R.raw.ron_gameoverwin),//~9C01R~
					new Tables(SOUND_PON          ,              R.raw.pon),//+0408I~
					new Tables(SOUND_KAN          ,              R.raw.kan),//+0408I~
					new Tables(SOUND_CHII         ,              R.raw.chii),//+0408I~
                    };                                             //~1A08I~
    private static final int MAX_ERRCTR=4;                         //~1A3bI~//~9C02M~
//  int tblidx;                                                    //~1A08I~//~9C03R~
	boolean Busy;

	@SuppressLint("ParserError")
	private int playCtr,releaseCtr;                                 //~v106R~//~9C02R~
                                                          //~1327I~
	String Name,Queued;
    private float levelVolume; //0--1.0                      //~1327I~//~9C02R~
	private boolean[] swLevelChanged=new boolean[Ssoundtbl.length]; //~9C02R~
	private boolean[] swPrepared=new boolean[Ssoundtbl.length];    //~9C03I~
//  private MediaPlayer player=null;                                       //~1327I~//~9C03R~
    //******************************************************************//~9C03I~
	public SoundList ()
	{                                                              //~1327R~
    	Arrays.fill(swLevelChanged,true);                          //~9C02I~
//        for (MediaPlayer.Status s:MediaPlayer.Status.values())   //~9C03I~
//            if (Dump.Y) Dump.println("SoundList mediaplayerStatus:"+s);//~9C03I~
	}
	public void setVolume(float Plevel)                            //~9C02R~
	{                                                              //~9C02I~
        if (Dump.Y) Dump.println("SoundList setVolume level="+Plevel);//~9C02I~
        playCtr=0;   	//for newGame after endgameReturn by F1    //~9C02I~
        releaseCtr=0;                                              //~9C02I~
        Busy=false;                                                //~9C02R~//~9C03M~
//        for (int ii=0;ii<Ssoundtbl.length;ii++)                  //~9C02I~
//        {                                                        //~9C02I~
//            Ssoundtbl[tblidx].setObject(null);                   //~9C02I~
//        }                                                        //~9C02I~
//      release();                                                 //~9C02I~//~9C03R~
        if (levelVolume!=Plevel)                                   //~9C02I~
        {                                                          //~9C02I~
	    	levelVolume=Plevel;                                    //~9C02I~
            Arrays.fill(swLevelChanged,true);                                   //~9C02I~
        }                                                          //~9C02I~
	}                                                              //~9C02I~
	static synchronized public void beep ()
	{                                                              //~9C03R~
		Toolkit.getDefaultToolkit().beep();                        //~9C03I~
	}
//***************************                                      //~9C03I~
	public synchronized void play (String name)
	{                                                              //~9C02R~
        if (Dump.Y) Dump.println("SoundList.play name="+name+",isPlaying="+isPlaying(name));     //~9C02I~//~9C03R~
		if (busy())                                                //~9C02I~
		{                                                          //~1327R~
			return;
		}                                                           //~1327I~
		Name=name;
		playSound();//~1327I~
	}
	public boolean isPlaying(String Pname)                          //~9C03I~
	{                                                              //~9C03I~
    	MediaPlayer p=(MediaPlayer)Tables.find(Ssoundtbl,Pname,null);                  //~9C03I~
        if (p==null)                                               //~9C03I~
        	return false;                                          //~9C03I~
        boolean rc=p.isPlaying();                                  //~9C03I~
        if (Dump.Y) Dump.println("SoundList.isPlaying name="+Pname+",rc="+rc);//~9C03I~
		return rc;
	}                                                              //~9C03I~
//***************************                                      //~1A0aI~
	public boolean busy ()
    {                                                              //~v106I~
    	Busy=playCtr>releaseCtr;                                   //~v106M~
        if (Dump.Y) Dump.println("SoundList Busy="+Busy+",create="+playCtr+",release="+releaseCtr);//~1A08I~
		return Busy;                                               //~v106M~
	}
//***************************                                      //~1A0aI~
	public boolean busy (String Pfnm)                              //~1A0aI~
    {                                                              //~1A0aI~
    	Busy=playCtr>releaseCtr;                                   //~1A0aI~
        if (Dump.Y) Dump.println("SoundList fnm="+Pfnm+",Busy="+Busy+",create="+playCtr+",release="+releaseCtr);//~1A0aI~
        if (Busy)                                                  //~1A0aI~
           	synchronized(this)                                     //~9C03R~
			{                                                      //~9C03I~
				Queued=Pfnm;                                       //~9C03I~
		        if (Dump.Y) Dump.println("SoundList Qued="+Pfnm);  //~9C03I~
			}                                                      //~9C03I~
		return Busy;                                               //~1A0aI~
	}                                                              //~1A0aI~
//***************************                                      //~1327I~
//*Mediaplayer              *                                      //~1327I~
//***************************                                      //~1327I~
//  private static int errCtr;                                     //~1A3bI~//~9C02R~
    synchronized                                                   //~1327I~
    private void playSound()                           //~1327I~
    {                                                              //~1327I~
  	    if (Dump.Y) Dump.println("playSound start:"+Name+",volume="+levelVolume);        //~1A3bI~//~9C02R~//~9C03R~
//  	int id=getResourceId(Name);                               //~1327I~//~9C03R~
    	int idx=find(Name);                                        //~9C03I~
//      if (id<0)                                                  //~1327I~//~9C03R~
        if (idx<0)                                                 //~9C03I~
        	return;                                                //~1327I~
        if (levelVolume==(float)0.0)                                //~9C03I~
        	return;                                                //~9C03I~
        int id=Ssoundtbl[idx].getId();                             //~9C03I~
//      player=(MediaPlayer)(Ssoundtbl[tblidx].getObject());               //~1A08I~//~9C03R~
        MediaPlayer player=(MediaPlayer)(Ssoundtbl[idx].getObject());//~9C03R~
        try                                                        //~1327I~
        {                                                          //~1327I~
         if (player==null)                                         //~1A08I~
         {                                                         //~1A08I~
//          Uri sdfnmuri=chkSdcard(Name);                            //~1A08I~//~9C01R~
//          if (sdfnmuri!=null)                                      //~1A08I~//~9C01R~
//          {                                                        //~1A0aI~//~9C01R~
//          //*.wav or .mp3 exist                                    //~1A3bI~//~9C01R~
//            player=MediaPlayer.create(AG.context,sdfnmuri);           //~1A08I~//~9C01R~
//            if (Dump.Y) Dump.println("playSound file="+sdfnmuri.toString()+",player="+player);//~1A0aI~//~9C01R~
//            if (player==null                                       //~1A3bI~//~9C01R~
//            &&  !sdfnmuri.getPath().endsWith(".mp3"))               //~1A3bI~//~9C01R~
//                player=chkMp3(Name);          //~1A3bI~          //~9C01R~
//            if (player==null)                                      //~1A3bI~//~9C01R~
//            {                                                      //~1A3bI~//~9C01R~
//                errCtr++;                                          //~1A3bI~//~9C01R~
//                if (errCtr<MAX_ERRCTR)                             //~1A3bI~//~9C01R~
//                UView.showToast(Utils.getStr(R.string.ErrWaveFile,sdfnmuri.getPath()));//~1A3bR~//~9C01R~
//                return;                                            //~1A3bI~//~9C01R~
//            }                                                      //~1A3bI~//~9C01R~
//          }                                                        //~1A0aI~//~9C01R~
//          else      //no .wav, .mp3 exist                          //~1A3bI~//~9C01R~
        	player=MediaPlayer.create(AG.context,id);              //~1327I~
//          Ssoundtbl[tblidx].setObject(player);                   //~1A08I~//~9C03R~
            Ssoundtbl[idx].setObject(player);                      //~9C03I~
			swLevelChanged[idx]=true;	//setVolume                //~9C03I~
            swPrepared[idx]=true;                                  //~9C03R~
        	if (Dump.Y) Dump.println("Sound.playSound create player="+Utils.toString(player));//~9C02I~//~9C03M~
         }                                                         //~1A08I~
        if (Dump.Y) Dump.println("Sound.playSound player="+Utils.toString(player));//~9C02I~
        if (Dump.Y) Dump.println("Sound.playSound idx="+idx+",swLevelChanged="+Arrays.toString(swLevelChanged));//~9C02I~
//      if (swLevelChanged[tblidx])                                //~9C02R~//~9C03R~
        if (swLevelChanged[idx])                                   //~9C03I~
        {                                                          //~9C02R~
//       	swLevelChanged[tblidx]=false;                          //~9C02R~//~9C03R~
         	swLevelChanged[idx]=false;                             //~9C03I~
            player.setVolume(levelVolume,levelVolume);	//left and right volume//~9C02I~
        	if (Dump.Y) Dump.println("Sound.playSound setVolume level="+levelVolume);//~9C02I~
        }                                                          //~9C02R~
//          player.setOnCompletionListener(this);                  //~9C02R~
            playCtr++;                                           //~v106I~
  	     	if (Dump.Y) Dump.println("SoundList.playSound before prepare swPrepared="+swPrepared);//~9C03I~
            if (!swPrepared[idx]) //prepared after crweated        //~9C03R~
            {                                                      //~9C03I~
	        	player.prepare();                                  //~9C03R~
	            swPrepared[idx]=true;                              //~9C03I~
  	     		if (Dump.Y) Dump.println("SoundList.playSound after prepare");//~9C03R~
            }                                                      //~9C03I~
        	player.seekTo(0);                                      //~1327I~
  	     	if (Dump.Y) Dump.println("SoundList.playSound after seekTo");//~9C03I~
        	player.start();                                         //~1327I~
  	     	if (Dump.Y) Dump.println("SoundList.playSound after start");//~9C03I~
            player.setOnCompletionListener(this);   //it is said that i is tbe after start//~9C02R~
            player.setOnErrorListener(this);   //it is said that i is tbe after start//~9C03I~
  	     	if (Dump.Y) Dump.println("SoundList.playSound after setListener="+this.toString());//~9C03I~
  	     	if (Dump.Y) Dump.println("playSound end:"+Name+",player="+ Utils.toString(player));       //~v106I~//~9C03R~
        }                                                          //~1327I~
        catch(Exception e)                                          //~1327I~
        {                                                          //~1327I~
        	Dump.println(e,"Sound Play Exception ctr="+playCtr+",name="+Name);          //~1327I~//~1A08R~
        }                                                          //~1327I~
    }                                                              //~1327I~
////******************************************************           //~1A3bI~//~9C01R~
//    private  MediaPlayer chkMp3(String Pname)                      //~1A3bI~//~9C01R~
//    {                                                              //~1A3bI~//~9C01R~
//        MediaPlayer player=null;                                   //~1A3bI~//~9C01R~
//        int pos=Pname.lastIndexOf(".wav");                         //~1A3bI~//~9C01R~
//        if (pos>0)                                                 //~1A3bI~//~9C01R~
//        {                                                          //~1A3bI~//~9C01R~
//            Uri sdfnmurimp3=chkSdcard(Name.substring(0,pos)+".mp3");//~1A3bI~//~9C01R~
//            if (sdfnmurimp3!=null)                                 //~1A3bI~//~9C01R~
//            {                                                      //~1A3bI~//~9C01R~
//                player=MediaPlayer.create(AG.context,sdfnmurimp3); //~1A3bR~//~9C01R~
//                if (Dump.Y) Dump.println("playSound file="+sdfnmurimp3.toString()+",player="+player);//~1A3bR~//~9C01R~
//            }                                                      //~1A3bI~//~9C01R~
//        }                                                          //~1A3bI~//~9C01R~
//        return player;                                             //~1A3bI~//~9C01R~
//    }//mp3                                                         //~1A3bI~//~9C01R~
	//*******************                                          //~1327I~
    @Override                                                      //~1327I~
    public void onCompletion(MediaPlayer Pplayer)                  //~1327I~
    {                                                              //~1327I~
  	    if (Dump.Y) Dump.println("SoundList.onCompletion before stop sound player="+Utils.toString(Pplayer));//~9C03R~
//      stopSound();                                               //~1327I~//~9C03R~
        stopSound(Pplayer);                                        //~9C03I~
  	    if (Dump.Y) Dump.println("SoundList.onCompletion after stopSound");//~v106I~//~9C02R~
    }                                                              //~1327I~
	//*******************                                          //~9C03I~
    @Override                                                      //~9C03I~
    public boolean onError(MediaPlayer Pplayer,int Pwhat,int Pextra)//~9C03I~
    {                                                              //~9C03I~
  	    if (Dump.Y) Dump.println("SoundList.onError player="+Utils.toString(Pplayer)+",what="+Pwhat+",extra="+Pextra);//~9C03I~
  	    return false;	//callback to onCompletion                 //~9C03I~
    }                                                              //~9C03I~
	//*******************                                          //~1327I~
    synchronized                                                   //~1327I~
//  private void stopSound()                                       //~1327I~//~9C03R~
    private void stopSound(MediaPlayer Pplayer)                    //~9C03I~
    {                                                              //~1327I~
	    MediaPlayer player=Pplayer;                                //~9C03I~
        int idx=Tables.find(Ssoundtbl,Pplayer);	                       //~9C03I~
        if (Dump.Y) Dump.println("SoundList stopSound playCtr="+playCtr+",releaseCtr="+releaseCtr+",idx="+idx+",player="+Utils.toString(player));//~9C02R~//~9C03R~
    	if (player==null)                                          //~1327I~
        	return;                                                 //~1327I~
        try                                                        //~1327I~
        {                                                          //~1327I~
        	player.stop();                                         //~1327I~
        	if (Dump.Y) Dump.println("SoundList.stopSound after stop");//~9C03I~
        	player.prepare();                                      //~1A08M~//~9C03R~
            if (idx!=-1)                                           //~9C03I~
	        	swPrepared[idx]=true;                              //~9C03I~
        	if (Dump.Y) Dump.println("SoundList.stopSound after prepare");//~9C03I~
  /*@@@@                                                           //~1A08I~
        	player.reset();                                        //~v106I~
            player.setOnCompletionListener(null);                  //~1327I~
        	player.release();                                      //~1327I~
    @@@@*/                                                         //~1A08I~
            releaseCtr++;                                          //~v106I~
            player=null;                                           //~1327I~
            String qed=Queued;                                     //~1A0aI~
		    if (Dump.Y) Dump.println("SoundList.stopSound Qued="+Queued);//~9C03I~
            Queued=null;                                           //~1A0aI~
            if (qed!=null)                                         //~1A0aI~
            	play(qed);                                         //~1A0aI~
        }                                                          //~1327I~
        catch(Exception e)                                          //~1327I~
        {                                                          //~1327I~
        	Dump.println(e,Name+"Sound Stop Exception player="+Utils.toString(Pplayer));          //~1327I~//~9C03R~
        }                                                          //~1327I~
    }                                                              //~1327I~
	//*******************                                          //~1A08I~
    synchronized                                                   //~1A08I~
    public  void release()                                   //~1A08R~
    {                                                              //~1A08I~
        if (Dump.Y)	Dump.println("SoundList.release");             //~1A08I~//~9C02R~
        Arrays.fill(swPrepared,false);                             //~9C03I~
    	for (int ii=0;ii<Ssoundtbl.length;ii++)                    //~1A08I~
        {                                                          //~1A08I~
        	MediaPlayer p=(MediaPlayer)(Ssoundtbl[ii].getObject());//~1A08I~
        	if (Dump.Y)	Dump.println("SoundList:release name="+Ssoundtbl[ii].name+",player="+Utils.toString(p));//~1A08I~//~9C02I~
            if (p==null)                                           //~1A08I~
            	continue;                                          //~1A08I~
	        try                                                    //~1A08I~
    	    {                                                      //~1A08I~
        		p.reset();                                         //~1A08I~
            	p.setOnCompletionListener(null);                   //~1A08I~
        		p.release();                                       //~1A08I~
//            	Ssoundtbl[tblidx].setObject(null);                 //~9C02I~//~9C03R~
              	Ssoundtbl[ii].setObject(null);                     //~9C03I~
            }                                                      //~1A08I~
        	catch(Exception e)                                     //~1A08I~
        	{                                                      //~1A08I~
        		Dump.println(e,"SoundList:release Exception name="+Ssoundtbl[ii].name);//~1A08I~
        	}                                                      //~1A08I~
        }                                                          //~1A08I~
        if (Dump.Y)	Dump.println("SoundList release end");         //~1A08I~
    }                                                              //~1A08I~
//    //*******************                                          //~1327I~//~9C03R~
//    private int getResourceId(String Pname)                        //~1327I~//~9C03R~
//    {                                                              //~1327I~//~9C03R~
//        String basename=Pname;                                     //~1327I~//~9C03R~
//        int pos;                                                //~1327I~//~9C03R~
//    //*********                                                    //~1327I~//~9C03R~
////        pos=Pname.lastIndexOf('/');                                 //~1327I~//~9C02R~//~9C03R~
////        if (pos>0)                                                 //~1327I~//~9C02R~//~9C03R~
////            basename=basename.substring(pos+1);                    //~1327I~//~9C02R~//~9C03R~
////        pos=basename.lastIndexOf('.');                                 //~1327I~//~9C02R~//~9C03R~
////        if (pos>0)                                                 //~1327I~//~9C02R~//~9C03R~
////            basename=basename.substring(0,pos);                    //~1327I~//~9C02R~//~9C03R~
//        tblidx=Tables.find(Ssoundtbl,basename);                    //~1A08I~//~9C03R~
//        if (tblidx<0)                                              //~1A08I~//~9C03R~
//            return -1;                                             //~1A08I~//~9C03R~
//        return Ssoundtbl[tblidx].getId();                                //~1A08R~//~9C03R~
//    }                                                              //~1327I~//~9C03R~
	//*******************                                          //~9C03I~
    private int find(String Pname)                                 //~9C03I~
    {                                                              //~9C03I~
    	String basename=Pname;                                     //~9C03I~
    	int pos;                                                   //~9C03I~
    //*********                                                    //~9C03I~
        int idx=Tables.find(Ssoundtbl,basename);                   //~9C03I~
        if (Dump.Y) Dump.println("SoundList find name="+Pname+",rc="+idx);//~9C03I~
        return idx;                                                //~9C03I~
    }                                                              //~9C03I~
//    //*******************                                          //~1A08I~//~9C01R~
//    private Uri chkSdcard(String Pname)                            //~1A08I~//~9C01R~
//    {                                                              //~1A08I~//~9C01R~
//        String fpath=Pname;                                        //~1A08I~//~9C01R~
//        int pos=Pname.lastIndexOf('/');                            //~1A08I~//~9C01R~
//        if (pos>0)                                                 //~1A08I~//~9C01R~
//            fpath=fpath.substring(pos+1);                          //~1A08I~//~9C01R~
//        fpath=Prop.getSDPath(SOUND_DIR+"/"+fpath);                 //~1A08R~//~9C01R~
//        if (fpath==null)                                           //~1A08I~//~9C01R~
//            return null;                                           //~1A08I~//~9C01R~
//        if (Dump.Y) Dump.println("soundfile on sdcard="+fpath);    //~1A08I~//~9C01R~
//        File f=new File(fpath);                                    //~1A08I~//~9C01R~
//        if (!f.exists()||f.isDirectory())                          //~1A08I~//~9C01R~
//        {                                                          //~1A3bI~//~9C01R~
//            return chkSdcardMp3(Pname);                            //~1A3bI~//~9C01R~
//        }                                                          //~1A3bI~//~9C01R~
//        Uri soundfileUri=Uri.fromFile(f);                          //~1A08I~//~9C01R~
//        return soundfileUri;                                       //~1A08I~//~9C01R~
//    }                                                              //~1A08I~//~9C01R~
//    //*******************                                          //~1A3bI~//~9C01R~
//    private Uri chkSdcardMp3(String Pname)                         //~1A3bI~//~9C01R~
//    {                                                              //~1A3bI~//~9C01R~
//        String fpath=Pname;                                        //~1A3bI~//~9C01R~
//        int pos=Pname.lastIndexOf('/');                            //~1A3bI~//~9C01R~
//        if (pos>0)                                                 //~1A3bI~//~9C01R~
//            fpath=fpath.substring(pos+1);                          //~1A3bI~//~9C01R~
//        pos=fpath.lastIndexOf(".wav");                             //~1A3bI~//~9C01R~
//        if (pos<=0)                                                //~1A3bI~//~9C01R~
//            return null;                                           //~1A3bI~//~9C01R~
//        fpath=Prop.getSDPath(SOUND_DIR+"/"+fpath.substring(0,pos)+".mp3");//~1A3bI~//~9C01R~
//        if (fpath==null)                                           //~1A3bI~//~9C01R~
//            return null;                                           //~1A3bI~//~9C01R~
//        if (Dump.Y) Dump.println("soundfile on sdcard MP3="+fpath);//~1A3bI~//~9C01R~
//        File f=new File(fpath);                                    //~1A3bI~//~9C01R~
//        if (!f.exists()||f.isDirectory())                          //~1A3bI~//~9C01R~
//        {                                                          //~1A3bI~//~9C01R~
//            return null;                                           //~1A3bI~//~9C01R~
//        }                                                          //~1A3bI~//~9C01R~
//        Uri soundfileUri=Uri.fromFile(f);                          //~1A3bI~//~9C01R~
//        return soundfileUri;                                       //~1A3bI~//~9C01R~
//    }                                                              //~1A3bI~//~9C01R~
}

