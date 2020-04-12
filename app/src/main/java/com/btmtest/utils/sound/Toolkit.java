//*CID://+DATER~:                             update#=   13;       //~v104I~//~@@@@R~//~9C01R~
//*******************************************************          //~v104I~
package com.btmtest.utils.sound;                                         //~9C01I~//+9C03R~

import android.media.AudioManager;
import android.media.Image;
import android.media.ToneGenerator;
//+9C01I~
import com.btmtest.utils.Dump;

import static com.btmtest.StaticVars.AG;                           //~9C01I~

public class Toolkit                                        //~1213R~
{                                                                  //~1111I~
    static ToneGenerator tg=new ToneGenerator(AudioManager.STREAM_ALARM,ToneGenerator.MAX_VOLUME);//~v104I~
//**********************                                           //~1128I~
	public static Toolkit getToolkit()                             //~1213R~
    {                                                              //~1124I~
      try                                                          //~v104I~
      {                                                            //~v104I~
		if (tg==null)                                              //~v104I~
			tg=new ToneGenerator(AudioManager.STREAM_ALARM,ToneGenerator.MAX_VOLUME);//~v104I~
      }                                                            //~v104I~
      catch(Exception e)                                           //~v104I~
      {                                                            //~v104I~
          Dump.println(e,"getToolKit");                            //~v104I~
      }                                                            //~v104I~
    	return new Toolkit();                                               //~1213I~
    }
	public static Toolkit getDefaultToolkit()
	{
		return getToolkit();
	}
	public Dimension getScreenSize()                               //~1524R~
	{
		return new Dimension(AG.scrWidth,AG.scrHeight);//~1124I~
	}
    public String[] getFontList()                                  //~1331I~
    {                                                              //~1331I~
    	return null;	//fot GetFontSize, no additional font for android//~1331I~
        				//@@@@ android graphics has sans_seriff,serif,monospace only//~1331R~
    }                                                              //~1331I~
//***********                                                      //~1219I~
    public void beep()                                             //~1219I~
    {                                                              //~1219I~
      try                                                          //~v104I~
      {                                                            //~v104I~
//    	ToneGenerator tg=new ToneGenerator(AudioManager.STREAM_ALARM,ToneGenerator.MAX_VOLUME);//~v104R~
       if (tg!=null)                                               //~v104I~
       {                                                           //~v104I~
    	tg.startTone(ToneGenerator.TONE_PROP_BEEP);   //Beep:35ms   //~1219R~
    	tg.stopTone();                                             //~v104I~
//  	tg.release();                                              //~v104R~
       }                                                           //~v104I~
      }                                                            //~v104I~
      catch(Exception e)                                           //~v104I~
      {                                                            //~v104I~
          Dump.println(e,"beep");                                  //~v104I~
      }                                                            //~v104I~
    }                                                              //~1219I~
//***********                                                      //~1416I~
    public void sync()      //force update screen                  //~1416I~
    {                                                              //~1416I~
    }                                                              //~1416I~
//***********                                                      //~1417I~
//    public Image createImage(byte[] Pbyte, int Poffs, int Plen)      //~1417R~//~9C01R~
//    {                                                              //~1417I~//~9C01R~
//        return new Image(0,0);                                     //~1417I~//~9C01R~
//    }                                                              //~1417I~//~9C01R~
    //*********************************************************    //~9C01I~
    //*********************************************************    //~9C01I~
    //*********************************************************    //~9C01I~
    public class Dimension                                             //~1117R~//~9C01I~
    {                                                                  //~1112I~//~9C01I~
        public int height=0,width=0;                                   //~1117R~//~9C01I~
        public Dimension()                                        //~1117I~//~1120R~//~9C01I~
        {                                                              //~1117I~//~9C01I~
        }                                                              //~1117I~//~9C01I~
        public Dimension(int Pw,int Ph)                                //~1120I~//~9C01I~
        {                                                              //~1120I~//~9C01I~
            height=Ph;                                                 //~1120I~//~9C01I~
            width=Pw;                                                  //~1120I~//~9C01I~
        }                                                              //~1120I~//~9C01I~
        public void setSize(int Pw,int Ph)                                     //~1117I~//~9C01I~
        {                                                              //~1117I~//~9C01I~
            height=Ph;                                                 //~1117I~//~9C01I~
            width=Pw;                                                  //~1117I~//~9C01I~
        }                                                              //~1117I~//~9C01I~
                            //~1117I~                              //~9C01I~
    }//class                                                           //~1112I~//~9C01I~
}
