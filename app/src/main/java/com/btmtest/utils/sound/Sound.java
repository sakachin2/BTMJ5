//*CID://+va06R~:                             update#=   46;       //~va06R~
//****************************************************************************//~1A0aI~
//2020/04/27 va06:BGM                                              //~va06I~
//****************************************************************************//~va06I~
package com.btmtest.utils.sound;                                         //~9C01I~//~9C03R~

import android.content.Context;
import android.media.AudioManager;
import android.view.SoundEffectConstants;
import android.os.Handler;

import com.btmtest.dialog.PrefSetting;
import com.btmtest.utils.Dump;

import static com.btmtest.StaticVars.AG;                           //~9C01I~
import static com.btmtest.game.GConst.*;

public class Sound                                                 //~9C01R~
{                                                                  //~9C01R~
	private SoundList SL;                                          //~9C03R~
    private boolean swNoSound,swBeep;                                      //~9C01R~//~9C02R~
    private boolean swSoundEffect=false;                           //~9C02R~
    private AudioManager mgr;                                      //~9C02I~
    private boolean swSoundPool=true;                                      //~9C03I~
    private SPList SPL;                                            //~9C03I~
    private BGMList BGM;                                           //~va06I~
    public Sound()                                                 //~9C01I~
    {                                                              //~9C01I~
        AG.aSound=this;                                            //~9C01I~
        if (swSoundPool)                                           //~9C03I~
        {                                                          //~9C03I~
        	SPL=new SPList(this);                                  //~9C03I~
        	BGM=new BGMList();                                     //~va06I~
            return;                                                //~9C03I~
        }                                                          //~9C03I~
		SL=new SoundList();                                        //~9C03I~
 //       swSoundEffect= SoundControl.isSoundEffectEnabled();                //~9C02I~
        mgr=(AudioManager)AG.context.getSystemService(Context.AUDIO_SERVICE);//~9C02I~
    	if (Dump.Y) Dump.println("Sound constructor isSoundEffectsEnabled="+swSoundEffect);             //~9C01I~//~9C02I~
    }                                                              //~9C01I~
//****************************                                     //~va06I~
    public void resetOption()                                      //~9C01I~
    {                                                              //~9C01I~
        if (swSoundPool)                                           //~9C03I~
        {                                                          //~9C03I~
        	SPL.resetOption();                                     //~9C03I~
//      	BGM.resetOption();                                     //~va06R~
            return;                                                //~9C03I~//~va06R~
        }                                                          //~9C03I~
        swNoSound=PrefSetting.isNoSound();                         //~9C01I~
        swBeep=PrefSetting.isBeepOnly();                           //~9C01I~
        float vol=PrefSetting.getSoundVolume();                    //~9C02R~
    	if (Dump.Y) Dump.println("Sound.resetOption swNoSound="+swNoSound+",volume="+vol);             //~9C01I~//~9C02I~
        SL.setVolume(vol);                                         //~9C02R~//~9C03R~
    }                                                              //~9C01I~
//****************************                                     //~va06I~
    public void resetOptionBGM()                                   //~va06I~
    {                                                              //~va06I~
    	if (Dump.Y) Dump.println("Sound.resetOptionBGM");          //~va06I~
        BGM.resetOption();                                         //~va06I~
    }                                                              //~va06I~
//****************************                                     //~va06I~
	public void stopAll()                                          //~va06I~
    {                                                              //~va06I~
    	if (Dump.Y) Dump.println("Sound.stopAll");                 //~va06I~
		BGM.stopAll();                                             //~va06I~
    }                                                              //~va06I~
//*always use simple sound(2nd parameter)***************************//~@@@@I~
	static synchronized public void play 
		(String file, String simplefile, boolean beep)
    {                                                              //~@@@@R~
    	if (Dump.Y) Dump.println("Sound.play file="+file+",simplefile="+simplefile+",beep="+beep);//~@@@@I~//~9C01R~//~9C03R~//~0408R~
//      if ((AG.Options & AG.OPTIONS_NOSOUND)!=0) return;          //~@@@@I~//~9C01R~
        if (AG.aSound.swNoSound)                                   //~9C01R~
			return;                                                //~9C01I~
//      if ((AG.Options & AG.OPTIONS_BEEP_ONLY)!=0)                //~@@@@I~//~9C01R~
        if (beep && AG.aSound.swBeep)                                      //~9C01R~//~9C02R~
    	{	if (beep) SoundList.beep();                            //~9C01R~
			return;
		}
        if (AG.aSound.swSoundEffect)                                         //~9C02I~
        	if (AG.aSound.playSoundEffect(simplefile))                       //~9C02I~
            {                                                      //~9C02I~
            	return;                                            //~9C02I~
            }                                                      //~9C02I~
        file=simplefile;                                           //~@@@@I~
        if (file.equals("")) return;                               //~@@@@R~
        if (AG.aSound.SL.busy(file)) return;                                 //~1A16I~//~9C01R~
//          AG.aSound.SL.play("/jagoclient/au/"+file+".wav");                //~@@@@R~//~9C01R~//~9C02R~
            AG.aSound.SL.play(file);                               //~9C02I~
	}
    static public void play (String file)                          //~@@@@R~
    {                                                              //~1A16I~
        if (AG.aSound.SL.busy(file)) return;                                 //~1A16I~//~9C01R~
        play(file,"wip",false);                                    //~@@@@R~
    }                                                              //~@@@@R~
//********************************************************         //~@@@@I~
    static public void play (String file,boolean beep)             //~@@@@I~
    {                                                              //~@@@@I~
//        if (AG.aSound.swSoundPool)                                 //~9C03I~
//        {                                                          //~9C03I~
//        	AG.aSound.SPL.play(file,beep);                          //~9C03I~
//            return;                                                //~9C03I~
//        }                                                          //~9C03I~
        play(file,file,beep);                                      //~@@@@I~
    }                                                              //~@@@@I~
//********************************************************         //~9C03I~
    static public void play (int Psoundid,boolean Pbeep)           //~9C03I~
    {                                                              //~9C03I~
        AG.aSound.SPL.play(Psoundid,Pbeep);                         //~9C03I~
    }                                                              //~9C03I~
//********************************************************         //~va06I~
//    public static void playDelayed(int PdelayTime/*ms*/,int Psoundid,boolean Pbeep)//~va06R~
//    {                                                              //~v@@@I~//~va06R~
//        if (Dump.Y) Dump.println("Sound.playDelayed delayTime="+PdelayTime+",soundid="+Psoundid);//~va06R~
//        final int soundid=Psoundid;                              //~va06R~
//        final boolean beep=Pbeep;                                //~va06R~
//        AG.activity.runOnUiThread(                                 //~v@@@I~//~va06R~
//            new Runnable()                                         //~v@@@I~//~va06R~
//            {                                                      //~v@@@I~//~va06R~
//                @Override                                          //~v@@@I~//~va06R~
//                public void run()                                  //~v@@@I~//~va06R~
//                {                                                  //~v@@@I~//~va06R~
//                    try                                            //~v@@@I~//~va06R~
//                    {                                              //~v@@@I~//~va06R~
//                        if (Dump.Y) Dump.println("Sound.playDelayed run");//~v@@@R~//~va06R~
//                        play(soundid,beep);                      //~va06R~
//                    }                                              //~v@@@I~//~va06R~
//                    catch(Exception e)                             //~v@@@I~//~va06R~
//                    {                                              //~v@@@I~//~va06R~
//                        Dump.println(e,"Sound.playDelayed run");//~v@@@R~//~va06R~
//                    }                                              //~v@@@I~//~va06R~
//                }                                                  //~v@@@I~//~va06R~
//            }                                                      //~v@@@I~//~va06R~
//                                  );                               //~v@@@I~//~va06R~
//                                                                   //~v@@@I~//~va06R~
//    }                                                              //~v@@@I~//~va06R~
//********************************************************         //+va06I~
    public static void playDelayed(int PdelayTime/*ms*/,int Psoundid,boolean Pbeep)//+va06I~
    {                                                              //+va06I~
        if (Dump.Y) Dump.println("Sound.playDelayed delayTime="+PdelayTime+",soundid="+Psoundid);//+va06I~
        final int soundid=Psoundid;                                //+va06I~
        final boolean beep=Pbeep;                                  //+va06I~
        //*****                                                    //+va06I~
        final Runnable runnable=new Runnable()                      //+va06I~
        {                                                          //+va06I~
        	@Override                                              //+va06I~
            public void run()                                      //+va06I~
            {                                                      //+va06I~
                try                                                //+va06I~
                {                                                  //+va06I~
                    if (Dump.Y) Dump.println("Sound.playDelayed run");//+va06I~
                    play(soundid,beep);                            //+va06I~
                }                                                  //+va06I~
                catch(Exception e)                                 //+va06I~
                {                                                  //+va06I~
                    Dump.println(e,"Sound.playDelayed run");       //+va06I~
                }                                                  //+va06I~
            }                                                      //+va06I~
        };                                                          //+va06I~
        final Handler handler=new Handler();                       //+va06I~
        handler.postDelayed(runnable,PdelayTime);                  //+va06I~
    }                                                              //+va06I~
//********************************************************         //~va06I~
    static public void playBGM(int Psoundid)                       //~va06I~
    {                                                              //~va06I~
        AG.aSound.BGM.play(Psoundid);                              //~va06R~
    }                                                              //~va06I~
//********************************************************         //~9C02I~
    private boolean playSoundEffect(String Pfile)            //~9C02I~
    {                                                              //~9C02I~
    	boolean rc=true;                                           //~9C02I~
    	if (Pfile.equals(SOUND_TAKE))                              //~9C02I~
        {                                                          //~9C02I~
        	mgr.playSoundEffect(SoundEffectConstants.CLICK);       //~9C02R~
        }                                                          //~9C02I~
        else                                                       //~9C02I~
    	if (Pfile.equals(SOUND_DISCARD))                           //~9C02I~
        {                                                          //~9C02I~
        	mgr.playSoundEffect(SoundEffectConstants.NAVIGATION_DOWN);//~9C02R~
        }                                                          //~9C02I~
        else                                                       //~9C02I~
        	rc=false;                                              //~9C02I~
       if (Dump.Y) Dump.println("Sound.playSoundEffects file="+Pfile+",rc="+rc);//~9C02I~
       return rc;                                                  //~9C02I~
    }                                                              //~9C02I~
////********************************************************         //~1A0aI~//~9C01R~
//    static synchronized public void play                           //~1A0aI~//~9C01R~
//        (String file, boolean beep,boolean Penq)                   //~1A0aI~//~9C01R~
//    {                                                              //~1A0aI~//~9C01R~
//        if (Dump.Y) Dump.println("Sound file="+file+",beep="+beep+",enq="+Penq+",AGopt="+Integer.toHexString(AG.Options));//~1A0aI~//~9C01R~
////      if ((AG.Options & AG.OPTIONS_NOSOUND)!=0) return;          //~1A0aI~//~9C01R~
//        if (AG.aSound.swNoSound)                                 //~9C01R~
//            return;                                              //~9C01R~
////      if ((AG.Options & AG.OPTIONS_BEEP_ONLY)!=0)                //~1A0aI~//~9C01R~
//        if (AG.aSound.swBeep)                                    //~9C01R~
//        {                                                          //~1A0aI~//~9C01R~
//            return;                                                //~1A0aI~//~9C01R~
//        }                                                          //~1A0aI~//~9C01R~
//        if (file.equals("")) return;                               //~1A0aI~//~9C01R~
//        if (AG.aSound.SL.busy(file)) return;                                 //~1A0aI~//~9C01R~
//            AG.aSound.SL.play("/jagoclient/au/"+file+".wav");                //~1A0aI~//~9C01R~
//    }                                                              //~1A0aI~//~9C01R~
}

