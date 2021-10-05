//*CID://+vae9R~:                             update#=  132;       //~vae9R~
//************************************************************************
//2021/09/19 vae9 1ak2(access external audio file) for BTMJ        //~vae9I~
//2021/09/13 vae2 BGM for BTMJ5                                    //~vae2I~
//1ak2 2021/09/04 access external audio file
//************************************************************************
package com.btmtest.utils;


import static com.btmtest.StaticVars.AG;                           //~1aK2I~
import static com.btmtest.dialog.PrefSetting.*;
import static com.btmtest.game.GConst.*;                           //~1aK2I~
import com.btmtest.R;                                              //~1aK2I~
import com.btmtest.dialog.UPicker;
import com.btmtest.dialog.PrefSetting;                             //~1aK2I~

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import static android.app.Activity.*;

//~1110I~
@TargetApi(30)
public class UMediaStore
        implements UPicker.UPickerI
{
    private static final Uri uriAudioMedia=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI; //sdcard/Music or /SDcard/Download from API-1
//  private static final Uri uriDownloadMedia=MediaStore.Downloads.EXTERNAL_CONTENT_URI;//from api29
    private   Uri uriDownloadMedia;
	private static final String CN="UMediaStore.";
    private static final int COL_ID=0;
    private static final int COL_TITLE=1;
    private static final int COL_ARTIST=2;
    private static final int COL_ISMUSIC=3;
    private static final int COL_MIMETYPE=4;
    private static final int COL_SIZE=5;
    private static final int COL_DURATION=6;
    private static final String[] COLUMNS_AUDIO=
					{MediaStore.Audio.Media._ID,
                     MediaStore.Audio.Media.TITLE,
                     MediaStore.Audio.Media.ARTIST,
                     MediaStore.Audio.Media.IS_MUSIC,
                     MediaStore.Audio.Media.MIME_TYPE,
                     MediaStore.Audio.Media.SIZE,
                     MediaStore.Audio.Media.DURATION,
                    };
    private static final int COL_ID_DOWNLOAD=0;
    private static final int COL_TITLE_DOWNLOAD=1;
    private static final int COL_MIMETYPE_DOWNLOAD=2;
    private static final int COL_SIZE_DOWNLOAD=3;
    private static final String[] COLUMNS_DOWNLOAD=
					{MediaStore.MediaColumns._ID,
					 MediaStore.MediaColumns.TITLE,
					 MediaStore.MediaColumns.MIME_TYPE,
					 MediaStore.MediaColumns.SIZE,
                    };

    private String[] colsAudio;
	private ContentResolver CR;
//  private boolean swTest=false;	//TODO test
    private boolean swPaused;
    private ArrayList<AudioFile> audioFiles;
//  private Uri uriPicked,uriStarted,uriClickedUPicker;            //~vae9R~
    private Uri           uriStarted,uriClickedUPicker;            //~vae9I~
//  private AudioFile afPicked;                                    //~vae9R~
    private MediaPlayer MP;
//  private MainFrameOptions MFO;                                  //~1aK2R~
//  private PrefSetting      MFO;                                  //~1aK2I~//~vae2R~
    private UMediaStoreI      callback;                             //~vae2I~
    private String[] strsUserBGMUri=new String[MAX_USERBGM];       //~vae9R~//~vae2R~
    private String[] strsUserBGMTitle=new String[MAX_USERBGM];     //~vae9R~//~vae2R~
    private Uri[]    urisUserBGM=new Uri[MAX_USERBGM];             //~vae2R~
//**********************************                               //~v@@@I~//~1ak3I~//~vae2I~
    public interface UMediaStoreI                                   //~v@@@R~//~1ak3I~//~vae2R~
    {                                                              //~v@@@I~//~1ak3I~//~vae2I~
		void BGMSelected(Uri PitemUri,AudioFile PaudioFile);  //~v@@@R~                //~1ak3R~//~vae2R~
    }                                                              //~v@@@I~//~1ak3I~//~vae2I~
//********************************************************
    public class AudioFile
    {
        public String title,artist,mimeType;
        public long id;
        public boolean isMusic;
        public long size;
        public int min,sec,duration;
        public AudioFile(String Pid,String Ptitle,String Partist,String PisMusic,String PmimeType,Long Psize,String Pduration)
        {
	        title=Ptitle; artist=Partist; mimeType=PmimeType;
            isMusic=Utils.parseInt(PisMusic,0)!=0;
            id=Utils.parseLong(Pid,0L);
            size=Psize;
            duration=Utils.parseInt(Pduration,0); //millisec
            int s=duration/1000;	//sec
            min=s/60;
            sec=s%60;
        }
        public String toString()
        {
	        return "id="+id+",title="+title+",artist="+artist+",isMusic="+isMusic+",mimeType="+mimeType+",size="+size+",duration="+min+"."+sec;
        }
    }
//********************************************************
    public UMediaStore()
    {
        if (Dump.Y) Dump.println(CN+"constructor osVersion="+AG.osVersion);
        AG.aUMediaStore=this;
//      if (swTest
//      ||  AG.osVersion>= Build.VERSION_CODES.R) //>=Android-11 api30
        try
        {
	        init();
        }
        catch(NoClassDefFoundError e)
        {
            Dump.println(e,CN+"constructor NoClassDefFoundError");
        }
    }
//********************************************************
    private static UMediaStore getInstance()
    {
        UMediaStore ms=AG.aUMediaStore;
	    if (Dump.Y) Dump.println(CN+"getInstance AG.aUMediaStore="+Utils.toString(ms));
        if (ms==null)
        	ms=new UMediaStore();
        return ms;
    }
//********************************************************
    private void init()
    {
        if (Dump.Y) Dump.println(CN+"init");
        if (AG.osVersion>= 29) //Android10
			uriDownloadMedia=MediaStore.Downloads.EXTERNAL_CONTENT_URI;//from api29
	    CR=AG.context.getContentResolver();
        colsAudio=COLUMNS_AUDIO;
//      String strUri= Prop.getPreference(AG.PKEY_BGM_STRURI,"");  //~1aK2R~
//      String strUri= Utils.getPreference(PREFKEY_BGM_STRURI,""); //~1aK2I~//~vae2R~
//      if (strUri.compareTo("")!=0)                               //~vae2R~
//      	uriPicked=Uri.parse(strUri);                           //~vae2R~
    	setupUserBGM();                                            //~vae2I~
    }
//********************************************************         //~vae2I~
    private void setupUserBGM()                                    //~vae2I~
    {                                                              //~vae2I~
        if (Dump.Y) Dump.println(CN+"setUpUserBGM");               //~vae2I~
        PrefSetting.setupUserBGM(AG.prefProp,strsUserBGMUri,strsUserBGMTitle);	//get effective prefProp value after PrefSetting Closed//~vae2R~
        for (int ii=0;ii<MAX_USERBGM;ii++)                         //~vae2I~
        {                                                          //~vae2I~
    		urisUserBGM[ii]=Uri.parse(strsUserBGMUri[ii]);                  //~vae2I~
        }                                                          //~vae2I~
    }                                                              //~vae2I~
//********************************************************         //~vae2I~
//* fromBGMList                                                    //~vae2I~
//********************************************************         //~vae2I~
    public void resetOption()                                      //~vae2R~
    {                                                              //~vae2I~
        if (Dump.Y) Dump.println(CN+"resetOption");                //~vae2R~
        setupUserBGM();                                            //~vae2I~
    }                                                              //~vae2I~
//********************************************************         //~vae2I~
//* fromBGMList                                                    //~vae2I~
//********************************************************         //~vae2I~
    public void recoverOption()                                    //~vae2I~
    {                                                              //~vae2I~
        if (Dump.Y) Dump.println(CN+"recoverOption");              //~vae2I~
        setupUserBGM();                                            //~vae2I~
    }                                                              //~vae2I~
//********************************************************
    public void testTest()
    {
        if (Dump.Y) Dump.println(CN+"test");
	    audioFiles=listAudioFile(uriAudioMedia);
        openAudioFiles(audioFiles);
        if (audioFiles.size()!=0)
        {
        	int pos=audioFiles.size()-1;
        	Uri uri= ContentUris.withAppendedId(uriAudioMedia,audioFiles.get(pos).id);
//          play(uri);
        }
//      requestPickup();
    }
//********************************************************
	private ArrayList<AudioFile> listAudioFile(Uri PuirDir)
    {
    	int ctrLine=0;
	    if (Dump.Y) Dump.println(CN+"listAudioFile");
        int[] idxCol=new int[colsAudio.length];
//      String selection="${MediaStore.Audio.Media.IS_MUSIC} > 0}";
        String selection=null;
        Cursor cursor=CR.query(uriAudioMedia,colsAudio,selection,null,null);
        if (Dump.Y) Dump.println(CN+"listAudioFile uriAudioMedia="+uriAudioMedia+",cursor="+Utils.toString(cursor));
        ArrayList<AudioFile> members=new ArrayList<AudioFile>(0/*initial ctr*/);
        if (cursor!=null && cursor.moveToFirst())
        {
//  	    String path=cursor.getString(0);
//  	    if (Dump.Y) Dump.println(CN+"listAudioFile path="+Utils.toString(path));
//          File f=new File(path);
//  	    if (Dump.Y) Dump.println(CN+"listAudioFile File="+f);
        	for (int ii=0;ii<colsAudio.length;ii++)
            {
            	idxCol[ii]=cursor.getColumnIndex(colsAudio[ii]);
		        if (Dump.Y) Dump.println(CN+"listAudioFile colidx ii="+ii+",col="+colsAudio[ii]+",idx="+idxCol[ii]);
            }
            ctrLine=cursor.getCount();
        	members=new ArrayList<AudioFile>(ctrLine);
		    if (Dump.Y) Dump.println(CN+"listAudioFile ctrLine="+ctrLine);
            for (int ii=0;ii<ctrLine;ii++)
            {
                if (Dump.Y) Dump.println(CN+"listAudioFile ii="+ii+",title="+cursor.getString(idxCol[COL_TITLE])+",isMusic="+cursor.getString(idxCol[COL_ISMUSIC]));
                boolean isMusic=Utils.parseInt(cursor.getString(idxCol[COL_ISMUSIC]),0)!=0;
                if (isMusic)
                {
                	AudioFile af=newAudioFile(cursor,idxCol);
	                members.add(af);
                }
                cursor.moveToNext();
            }
        }
        if (cursor!=null)
            cursor.close();
//        if (members.size()>0)   //TODO test
//        {
//            uriPicked=ContentUris.withAppendedId(uriAudioMedia,members.get(0).id);
//            if ((AG.Options & AG.OPTIONS_BGM)!=0)
//                startBGM();
//        }
		if (Dump.Y) Dump.println(CN+"listAudioFile exit members size="+members.size());
        return members;
    }
//********************************************************
	public static Uri getMemberUri(Uri PuriDir,long Pid)
    {
    	Uri uri=ContentUris.withAppendedId(PuriDir,Pid);
		if (Dump.Y) Dump.println(CN+"getMemberUri uriDir="+PuriDir+",id="+Pid+",memberUri="+uri);
        return uri;
    }
//********************************************************
//*TODO test
//********************************************************
	private void listDownloadFile()
    {
    	int ctrLine=0;
	    if (Dump.Y) Dump.println(CN+"listDownloadFile uriDownload="+uriDownloadMedia);
        if (uriDownloadMedia==null)
        	return;
        String[] cols=COLUMNS_DOWNLOAD;
        int[] idxCol=new int[cols.length];
        String selection=null;
        Cursor cursor=CR.query(uriDownloadMedia,cols,selection,null,null);
        if (Dump.Y) Dump.println(CN+"listDownloadFile uriDownloadMedia="+uriDownloadMedia+",cursor="+Utils.toString(cursor));
        ArrayList<AudioFile> members=new ArrayList<AudioFile>(0/*initial ctr*/);
        if (cursor!=null && cursor.moveToFirst())
        {
        	for (int ii=0;ii<colsAudio.length;ii++)
            {
            	idxCol[ii]=cursor.getColumnIndex(cols[ii]);
		        if (Dump.Y) Dump.println(CN+"listDownloadFile colidx ii="+ii+",col="+cols[ii]+",idx="+idxCol[ii]);
            }
            ctrLine=cursor.getCount();
        	members=new ArrayList<AudioFile>(ctrLine);
		    if (Dump.Y) Dump.println(CN+"listDownloadFile ctrLine="+ctrLine);
            for (int ii=0;ii<ctrLine;ii++)
            {
                if (Dump.Y) Dump.println(CN+"listDownloadFile ii="+ii+",title="+cursor.getString(idxCol[COL_TITLE_DOWNLOAD])+",mimeType="+cursor.getString(idxCol[COL_MIMETYPE_DOWNLOAD]));
                cursor.moveToNext();
            }
        }
        if (cursor!=null)
            cursor.close();
    }
//********************************************************
	private AudioFile newAudioFile(Cursor Pcursor,int[] PidxCol)
    {
        if (Dump.Y) Dump.println(CN+"newAudioFile id="+Pcursor.getString(PidxCol[COL_ID])+
					",title="+Pcursor.getString(PidxCol[COL_TITLE])+	//title
					",artist="+Pcursor.getString(PidxCol[COL_ARTIST])+	//artist
					",isMusic="+Pcursor.getString(PidxCol[COL_ISMUSIC])+	//isMusic
					",mimetype="+Pcursor.getString(PidxCol[COL_MIMETYPE])+   //mime_type
					",size="+Pcursor.getLong(PidxCol[COL_SIZE])+   //mime_type
					",duration="+Pcursor.getString(PidxCol[COL_DURATION]));   //mime_type
        AudioFile af=new AudioFile(
					Pcursor.getString(PidxCol[COL_ID]),	//id
					Pcursor.getString(PidxCol[COL_TITLE]),	//title
					Pcursor.getString(PidxCol[COL_ARTIST]),	//artist
					Pcursor.getString(PidxCol[COL_ISMUSIC]),	//isMusic
					Pcursor.getString(PidxCol[COL_MIMETYPE]),   //mime_type
					Pcursor.getLong(PidxCol[COL_SIZE]),   //mime_type
					Pcursor.getString(PidxCol[COL_DURATION])   //mime_type
					);
        if (Dump.Y) Dump.println(CN+"newAudioFile AudioFile="+af.toString());
        return af;
    }
//********************************************************
	private AudioFile getAudioInfo(Uri Puri)
    {
	    if (Dump.Y) Dump.println(CN+"getAudioInfo Puri="+Puri);
        int[] idxCol=new int[colsAudio.length];
        Cursor cursor=CR.query(Puri,colsAudio,null,null,null);
        AudioFile af=null;
        if (Dump.Y) Dump.println(CN+"getAudioInfo cursor="+Utils.toString(cursor));
        if (cursor!=null && cursor.moveToFirst())
        {
        	for (int ii=0;ii<colsAudio.length;ii++)
            {
            	idxCol[ii]=cursor.getColumnIndex(colsAudio[ii]);
		        if (Dump.Y) Dump.println(CN+"listAudioInfo colidx ii="+ii+",col="+colsAudio[ii]+",idx="+idxCol[ii]);
            }
		    if (Dump.Y) Dump.println(CN+"getAudioInfo ctrLine="+cursor.getCount());
            af=newAudioFile(cursor,idxCol);
        }
        if (cursor!=null)
            cursor.close();
        return af;
    }
//********************************************************
	private void openAudioFiles(ArrayList<AudioFile> Plist)
    {
	    if (Dump.Y) Dump.println(CN+"openAudioFiles");
        for (AudioFile af:Plist)
        {
            if (Dump.Y) Dump.println(CN+"openAudioFiles AudioFile="+af.toString());
        	if (!af.isMusic)
            	continue;
        	Uri uri= ContentUris.withAppendedId(uriAudioMedia,af.id);
            openAudioFile(uri);
        }
    }
//********************************************************
	private void openAudioFile(Uri Puri)
    {
	    if (Dump.Y) Dump.println(CN+"openAudioFile uri="+Puri);
        try
        {
        	InputStream is=CR.openInputStream(Puri);
        	InputStreamReader isr=new InputStreamReader(is);
        	BufferedReader br=new BufferedReader(isr);
            br.close();
        }
        catch(FileNotFoundException e)
        {
        	Dump.println(e,CN+"openAudioFile FileNotFound:"+Puri);
        }
        catch(IOException e)
        {
        	Dump.println(e,CN+"openAudioFile IOException:"+Puri);
        }
	    if (Dump.Y) Dump.println(CN+"openAudioFile exit");
    }
//********************************************************         //~vae9I~
	public void playBGMTop()                                       //~vae9I~
    {                                                              //~vae9I~
	    if (Dump.Y) Dump.println(CN+"playBGMTop");                 //~vae9I~
        int soundid=PrefSetting.getSoundIDUser(0);                 //~vae9I~
        if (soundid>=0)                                            //~vae9I~
			playSound(soundid);                                    //+vae9R~
    }                                                              //~vae9I~
//********************************************************         //~vae2I~
//*soundid=MusicNo=idx of urisUserBGM                              //~vae9I~
//********************************************************         //~vae9I~
	public void playSound(int Psoundid)                            //~vae2I~
    {                                                              //~vae2I~
	    if (Dump.Y) Dump.println(CN+"playSound soundid="+Psoundid);//~vae2I~
        if (Psoundid>=0 && Psoundid<MAX_USERBGM)                   //~vae2I~
        {                                                          //~vae2I~
    		Uri uri=urisUserBGM[Psoundid];                         //~vae2I~
            if (uri!=null && !uri.equals(""))                      //~vae2R~
				play(uri);                                         //~vae2I~
        }                                                          //~vae2I~
	    if (Dump.Y) Dump.println(CN+"playSound exit");             //~vae2I~
    }                                                              //~vae2I~
//********************************************************
	private void play(Uri Puri)
    {
	    if (Dump.Y) Dump.println(CN+"play swVolChaned="+AG.aSound.BGM.swVolChanged+",uri="+Puri+",uriStarted="+uriStarted+",MP="+Utils.toString(MP));//~vae9R~
        if (MP!=null & uriStarted!=null)                           //~vae9R~
        {                                                          //~vae9I~
			if (!AG.aSound.BGM.swVolChanged && uriStarted.equals(Puri) )//~vae9R~
            	return;                                             //~vae9I~
            else                                                   //~vae9I~
            	stopBGM(false/*swpause*/);                         //~vae9I~
        }                                                          //~vae9I~
        float vol=AG.aSound.BGM.levelVolume;                       //~vae9I~
	    if (Dump.Y) Dump.println(CN+"play vol="+vol);              //~vae9I~
        if (vol==(float)0.0)                                       //~vae9I~
        	return;                                                //~vae9I~
        MediaPlayer mp=new MediaPlayer();
        MP=mp;
        final Uri uriPlay=Puri;
//      mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        setStreamType(mp);
        try
        {
            mp.setVolume(vol,vol);	//left and right volume        //~vae9I~
            mp.setDataSource(AG.context,Puri); //IOException if FileNotFound
//         	mp.setDataSource(AG.aMain.getApplicationContext(),Puri); //IOException if FileNotFound
//        	mp.prepare();
          	mp.setLooping(true);
        	mp.setOnPreparedListener(
					new MediaPlayer.OnPreparedListener()
                    {
                    	@Override
                        public void onPrepared(MediaPlayer Pmp)
                        {
	    					if (Dump.Y) Dump.println(CN+"play.onPrepared mp.start()");
        					Pmp.seekTo(0);
                        	Pmp.start();
                            uriStarted=uriPlay;
                        }
                    });
        	mp.setOnErrorListener(
					new MediaPlayer.OnErrorListener()
                    {
                    	@Override
                        public boolean onError(MediaPlayer Pmp,int Pwhat,int Pextra)
                        {
	    					if (Dump.Y) Dump.println(CN+"play.onError@@@@ what="+Pwhat+",extra="+Pextra);
                            resetMP(Pmp,true/*swStop*/);
                            boolean rc=true;	//app processed
                            return rc;
                        }
                    });
        	mp.setOnCompletionListener(
					new MediaPlayer.OnCompletionListener()
                    {
                    	@Override
                        public void onCompletion(MediaPlayer Pmp)
                        {
	    					if (Dump.Y) Dump.println(CN+"play.onCompletion");
                            resetMP(Pmp,false/*swStop*/);
                        }
                    });
        	mp.prepareAsync();
        }
        catch(IllegalArgumentException e)
        {
        	Dump.println(e,CN+"play IlleagalArgumentException:"+Puri);
        }
        catch(IllegalStateException e)
        {
        	Dump.println(e,CN+"play IlleagalStateException:"+Puri);
        }
        catch(IOException e)
        {
        	Dump.println(e,CN+"play IOException:"+Puri);
        }
//      mp.start();
	    if (Dump.Y) Dump.println(CN+"play exit");
    }
//********************************************************
    private void resetMP(MediaPlayer Pmp,boolean PswStop)
    {
	    if (Dump.Y) Dump.println(CN+"resetMP uriStarted="+Utils.toString(uriStarted)+",MP="+Pmp);
        if (Pmp==null)
        {
		    if (Dump.Y) Dump.println(CN+"resetMP return by MP=null");
        	return;
        }
        Uri uri=uriStarted;
        try
        {
		    if (Dump.Y) Dump.println(CN+"resetMP stop");
        	if (PswStop)
        		Pmp.stop();
        }
        catch(IllegalArgumentException e)
        {
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalArgumentException:"+Utils.toString(uri));
        }
        catch(IllegalStateException e)
        {
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalStateException:"+Utils.toString(uri));
        }
        try
        {
		    if (Dump.Y) Dump.println(CN+"resetMP reset");
        	Pmp.reset();
        }
        catch(IllegalArgumentException e)
        {
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalArgumentException:"+Utils.toString(uri));
        }
        catch(IllegalStateException e)
        {
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalStateException:"+Utils.toString(uri));
        }
        try
        {
		    if (Dump.Y) Dump.println(CN+"resetMP release");
        	Pmp.release();
        }
        catch(IllegalArgumentException e)
        {
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalArgumentException:"+Utils.toString(uri));
        }
        catch(IllegalStateException e)
        {
        	if (Dump.Y) Dump.println(e,CN+"play IlleagalStateException:"+Utils.toString(uri));
        }
        MP=null;
    }
//********************************************************
	private void setStreamType(MediaPlayer Pplayer)
    {
        if (AG.osVersion>= 26) //Android8.0 Oreo=Api26
        	setStreamType_26(Pplayer);
        else
        	setStreamType_25(Pplayer);
    }
    @SuppressWarnings("deprecation")
	private void setStreamType_25(MediaPlayer Pplayer)
    {
	    if (Dump.Y) Dump.println(CN+"setStreamPlayer_25");
        Pplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }
	@TargetApi(26)     //KitKat
	private void setStreamType_26(MediaPlayer Pplayer)
    {
	    if (Dump.Y) Dump.println(CN+"setStreamPlayer_26");
        Pplayer.setAudioAttributes(
            new AudioAttributes
                .Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());
//        AudioAttributes.Builder ab=new AudioAttributes.Builder();
//        ab.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC);
//        ab.setUsage(AudioAttributes.USAGE_MEDIA);
//        AudioAttributes aa=ab.build();
//        Pplayer.setAudioAttributes(aa);
    }
//********************************************************
	private void requestPickup(boolean PswUPicker)                 //~vae9R~
    {
	    if (Dump.Y) Dump.println(CN+"requestPickup osVersion="+AG.osVersion+",swUPicker="+PswUPicker);//~vae9R~
//      if (AG.osVersion>= 30) Android-11(R)                       //~vae9R~
        if (PswUPicker)                                            //~vae9R~
        {
        	UPicker.newInstance(uriAudioMedia,this).showDialog();
            return;
        }
      if (false)//TODO test
      {
        Intent intent2=new Intent(Intent.ACTION_OPEN_DOCUMENT);   //can not play on picker,invalid cusrsor data for GetString()
        intent2.addCategory(Intent.CATEGORY_OPENABLE);
//      intent2.setType("*/*");
        intent2.setType("audio/*");
        AG.activity.startActivityForResult(intent2,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);
      }
      else
      if (false)//TODO test
      {
        Intent intent2=new Intent(Intent.ACTION_GET_CONTENT);    //can not play on picker,invalid cusrsor data for GetString()
        intent2.setType("*/*");
        AG.activity.startActivityForResult(intent2,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);
      }
      else
      if (false) //TODO test
      {
        Intent intent2=new Intent(Intent.ACTION_GET_CONTENT);    //can not play on picker,invalid cusrsor data
        intent2.setType("audio/*");
        AG.activity.startActivityForResult(intent2,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);
      }
      else
      {
        Intent intent=new Intent(Intent.ACTION_PICK,uriAudioMedia);// can play on picker to select music
//      intent.setType("audio/*");
        AG.activity.startActivityForResult(intent,AG.ACTIVITY_REQUEST_PICKUP_AUDIO);
      }
	    if (Dump.Y) Dump.println(CN+"requestPickup exit");
    }
//********************************************************
//*from AMain
//********************************************************
	public static void onActivityResult(int Prequest,int Presult,Intent Pintent)
    {
	    if (Dump.Y) Dump.println(CN+"onActivityResult request="+Prequest+",result="+Presult);
        UMediaStore ums=getInstance();
        if (Presult==RESULT_OK)
        {
        	Uri uri=Pintent.getData();
    		AudioFile af=ums.getAudioInfo(uri);
            if (af==null)
            {
            	invalidSelection(uri);
            	return;
            }
            if (!af.isMusic)
            {
		    	if (Dump.Y) Dump.println(CN+"onActivityResult OK but not Music="+af.toString());
            }
//  		ums.afPicked=af;                                       //~vae9R~
//          ums.uriPicked=uri;                                     //~vae9R~
////          if ((AG.Options & AG.OPTIONS_BGM)!=0)                  //~1aK2R~//~vae2R~
//            if (!PrefSetting.isNoBGM())                            //~1aK2I~//~vae2R~
//            {                                                    //~vae2R~
//                ums.play(uri);                                   //~vae2R~
//            }                                                    //~vae2R~
//            if (ums.MFO!=null)                                   //~vae2R~
//            {                                                    //~vae2R~
//                ums.MFO.setBGMTitle(ums.afPicked.title,uri.toString()/*save to preference by OK button*/);//~vae2R~
//            }                                                    //~vae2R~
			if (ums.callback!=null)                                //~vae2I~
            	ums.callback.BGMSelected(uri,af);                  //~vae2R~
		    if (Dump.Y) Dump.println(CN+"onActivityResult OK uri="+uri);
        }
//      ums.MFO=null;                                              //~vae2R~
	    if (Dump.Y) Dump.println(CN+"onActivityResult exit");
    }
//********************************************************
	@Override //UPickerI
	public void itemSelected(Uri PitemUri)
    {
	    if (Dump.Y) Dump.println(CN+"itemSelected PitemUri="+PitemUri);
//        resetMP(MP,true/*PswStop*/);                             //~vae2R~
//        Uri uri=PitemUri;                                        //~vae2R~
//        uriPicked=uri;                                           //~vae2R~
//        afPicked=getAudioInfo(uri);                              //~vae2R~
//        if (afPicked==null)                                      //~vae2R~
//        {                                                        //~vae2R~
//            invalidSelection(uri);                               //~vae2R~
//            return;                                              //~vae2R~
//        }                                                        //~vae2R~
////      if ((AG.Options & AG.OPTIONS_BGM)!=0)                      //~1aK2R~//~vae2R~
//        if (!PrefSetting.isNoBGM())                                //~1aK2I~//~vae2R~
//        {                                                        //~vae2R~
//            play(uri);                                           //~vae2R~
//        }                                                        //~vae2R~
//        if (MFO!=null)                                           //~vae2R~
//        {                                                        //~vae2R~
//            MFO.setBGMTitle(afPicked.title,uri.toString()/*save to preference by OK button*/);//~vae2R~
//        }                                                        //~vae2R~
//        MFO=null;                                                //~vae2R~
        Uri uri=PitemUri;                                          //~vae2I~
		if (callback!=null)                                        //~vae2I~
        {                                                          //~vae2I~
    		AudioFile af=getAudioInfo(uri);                        //~vae2I~
            callback.BGMSelected(uri,af);                          //~vae2R~
        }                                                          //~vae2I~
	    if (Dump.Y) Dump.println(CN+"itemSelected exit");
    }
//********************************************************
//*from UPicker for API30
//********************************************************
	public void itemClicked(Uri PitemUri)
    {
	    if (Dump.Y) Dump.println(CN+"itemSelected PitemUri="+PitemUri);
		Uri uri=PitemUri;
        uriClickedUPicker=uri;
		AudioFile af=getAudioInfo(uri);
        if (af==null)
        {
            invalidSelection(uri);
            return;
        }
    	resetMP(MP,true/*PswStop*/);
        play(uri);
	    if (Dump.Y) Dump.println(CN+"itemClicked exit");
    }
	public void itemCanceled()
    {
	    if (Dump.Y) Dump.println(CN+"itemCanceled");
    	resetMP(MP,true/*PswStop*/);
	    if (Dump.Y) Dump.println(CN+"itemCanceled exit");
    }
////********************************************************       //~vae9R~
////*from MainFrameOptions                                         //~vae9R~
////********************************************************       //~vae9R~
//    public static void startBGM()                                //~vae9R~
//    {                                                            //~vae9R~
//        UMediaStore ums=getInstance();                           //~vae9R~
//        if (Dump.Y) Dump.println(CN+"startBGM swpaused="+ums.swPaused+",uriPicked="+Utils.toString(ums.uriPicked)+",MP="+Utils.toString(ums.MP));//~vae9R~
//        if (ums.uriPicked!=null)                                 //~vae9R~
//        {                                                        //~vae9R~
//            if (ums.MP==null)                                    //~vae9R~
//                ums.play(ums.uriPicked);                         //~vae9R~
//            else                                                 //~vae9R~
//                if (ums.swPaused)                                //~vae9R~
//                    ums.MP.start();                              //~vae9R~
//        }                                                        //~vae9R~
//        else                                                     //~vae9R~
//        {                                                        //~vae9R~
//            if (Dump.Y) Dump.println(CN+"startBGM no uri picked yet");//~vae9R~
//        }                                                        //~vae9R~
//    }                                                            //~vae9R~
//********************************************************         //~vae9I~
//*from MainActivity at Resume                                     //~vae9I~
//********************************************************         //~vae9I~
    private void startBGM()                                        //~vae9I~
    {                                                              //~vae9I~
        if (Dump.Y) Dump.println(CN+"startBGM swNoSound="+AG.aSound.BGM.swNoSound+",swUserBGN="+AG.aSound.BGM.swUserBGM+",uriStarted="+Utils.toString(uriStarted));//~vae9I~
        if (AG.aSound.BGM.swNoSound)                               //~vae9I~
        	return;                                                //~vae9I~
        if (!AG.aSound.BGM.swUserBGM)                              //~vae9I~
        	return;                                                //~vae9I~
        if (uriStarted!=null)                                      //~vae9I~
        {                                                          //~vae9I~
            if (MP==null)                                          //~vae9I~
                play(uriStarted);                                  //~vae9I~
            else                                                   //~vae9I~
                if (swPaused)                                      //~vae9I~
                    MP.start();                                    //~vae9I~
        }                                                          //~vae9I~
        else                                                       //~vae9I~
        {                                                          //~vae9I~
        	int soundID=PrefSetting.getSoundIDUser();              //~vae9I~
			playSound(soundID);                                    //~vae9I~
        }                                                          //~vae9I~
    }                                                              //~vae9I~
//********************************************************
	private void pauseBGM()
    {
        try
        {
		    if (Dump.Y) Dump.println(CN+"pauseBGM MP="+Utils.toString(MP));
            if (MP!=null)
            {
	        	MP.pause();
                swPaused=true;
            }
        }
        catch(Exception e)
        {
        	if (Dump.Y) Dump.println(e,CN+"pause");
        }
    }
//********************************************************
	public static void stopBGM(boolean PswPause)
    {
        UMediaStore ums=getInstance();
	    if (Dump.Y) Dump.println(CN+"stopBGM swPause="+PswPause+",MP="+Utils.toString(ums.MP));//~vae9R~
        if (ums.MP!=null)
        {
        	if (PswPause)
            	ums.pauseBGM();
            else
		        ums.resetMP(ums.MP,true/*swStop*/);
        }
        else
        {
		    if (Dump.Y) Dump.println(CN+"stopBGM MP=null");
        }
    }
//********************************************************
//  public static void changeBGM(MainFrameOptions Pmfo)            //~1aK2R~
//  public static void changeBGM(PrefSetting Pmfo)                 //~1aK2I~//~vae2R~
    public static void changeBGM(UMediaStoreI Pcallback,boolean PswUPicker)           //~vae2I~//~vae9R~
    {
        UMediaStore ums=getInstance();
	    if (Dump.Y) Dump.println(CN+"changeBGM AG.swGrantedExternalStorageRead="+AG.swGrantedExternalStorageRead+",MP="+Utils.toString(ums.MP));//~vae9R~
        if (!AG.swGrantedExternalStorageRead)
        {
	        UView.showToast(R.string.ErrNoExternalStoragePermission);
        	return;
        }
        stopBGM(false/*swPause*/);
//      ums.MFO=Pmfo;                                              //~vae2R~
        ums.callback=Pcallback;                                    //~vae2I~
        ums.requestPickup(PswUPicker);                             //~vae9R~
    }
////********************************************************       //~vae9R~
//    public static String getCurrentTitle()                       //~vae9R~
//    {                                                            //~vae9R~
//        UMediaStore ums=getInstance();                           //~vae9R~
//        if (Dump.Y) Dump.println(CN+"getCurrentTitle MP="+Utils.toString(ums.MP)+",AudioFile="+Utils.toString(ums.afPicked));//~vae9R~
//        String rc="";                                            //~vae9R~
//        if (ums.afPicked!=null)                                  //~vae9R~
//            rc=ums.afPicked.title;                               //~vae9R~
//        if (Dump.Y) Dump.println(CN+"getCurrentTitle rc="+rc);   //~vae9R~
//        return rc;                                               //~vae9R~
//    }                                                            //~vae9R~
//********************************************************
	public static ArrayList<AudioFile> getMemberList(Uri PuriDir)
    {
        UMediaStore ums=getInstance();
	    if (Dump.Y) Dump.println(CN+"getMemberList uriDir="+PuriDir);
		ArrayList<AudioFile> list=ums.listAudioFile(PuriDir);
        return list;
    }
//********************************************************
	public static void onPause()
    {
	    if (Dump.Y) Dump.println(CN+"onPause");
    	stopBGM(true/*pause*/);                                    //~vae2R~
    }
//********************************************************         //~vae2I~
	public static void onDestroy()                                 //~vae2I~
    {                                                              //~vae2I~
	    if (Dump.Y) Dump.println(CN+"onDestropy");                 //~vae2I~
    	stopBGM(false/*pause*/);                                   //~vae2R~
    }                                                              //~vae2I~
//********************************************************
	public static void onResume()
    {
	    if (Dump.Y) Dump.println(CN+"onResume AG.swGrantedExternalStorageRead="+AG.swGrantedExternalStorageRead+",AG.Options="+Integer.toHexString(AG.Options));
        if (!AG.swGrantedExternalStorageRead)
        	return;
		UMediaStore ums=getInstance();
//  	ums.audioFiles=ums.listAudioFile(ums.uriAudioMedia);	//TODO test
//  	getInstance().listDownloadFile();		//TODO test
//      if ((AG.Options & AG.OPTIONS_BGM)!=0)                      //~1aK2R~
//      if (!PrefSetting.isNoBGM())                                //~1aK2I~//~vae9R~
//      	startBGM();                                            //~vae9R~
        ums.startBGM();                                            //~vae9I~
    }
//********************************************************
    private static void invalidSelection(Uri Puri)
    {
		UView.showToast(Utils.getStr(R.string.ErrInvalidAudioSelection," "+Puri));
    }
}//class
