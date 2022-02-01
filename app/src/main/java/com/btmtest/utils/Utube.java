//*CID://+vaikR~:                             update#=    6;       //~vaikI~
//**********************************************************************//~1107I~
//2022/01/11 vaik Youtube movie as help                            //~vaikI~
//**********************************************************************//~1107I~//~v106M~
package com.btmtest.utils;                                               //~1Ad8I~//~1Ac0I~//~v@@@R~
import com.google.android.youtube.player.YouTubeIntents;           //~vaikI~
import android.annotation.TargetApi;
import android.content.Context;                                    //~v107R~
import android.content.Intent;
import android.net.Uri;
import static com.btmtest.StaticVars.AG;
import static com.btmtest.AG.*;//~v@21I~//~@@01I~

//**********************************************************************//~1107I~
public class Utube                                                 //~vaikR~
{                                                                  //~0914I~
//**********************************                               //~@@@@I~
	public static boolean chkIntentPlayVideo(Context Pcontext)     //+vaikR~
    {                                                              //~@@@2I~
    	boolean rc=false;                                          //~vaikI~
        try                                                        //~vaikI~
        {                                                          //~@@@2I~
	       	rc=YouTubeIntents.canResolvePlayVideoIntent(Pcontext); //+vaikR~
        }                                                          //~@@@2I~
        catch (Exception e)                                               //~@@@2I~
        {                                                          //~@@@2I~
        	Dump.println(e,"chkIntentPlayVideo");                          //~@@@2I~//+vaikR~
        }                                                          //~@@@2I~
    	if (Dump.Y) Dump.println("Utube.chkIntentPlayVideo rc="+rc);         //~@@@2I~//~v@@@R~//+vaikR~
        return rc;                                                 //~vaikI~
    }                                                              //~@@@2I~
//**********************************                               //+vaikI~
	public static Intent createIntentPlayVideo(Context Pcontext,String PvideoID)//+vaikI~
    {                                                              //+vaikI~
    	Intent intent=null;                                        //+vaikI~
        try                                                        //+vaikI~
        {                                                          //+vaikI~
	       	intent=YouTubeIntents.createPlayVideoIntent(Pcontext,PvideoID);//+vaikI~
        }                                                          //+vaikI~
        catch (Exception e)                                        //+vaikI~
        {                                                          //+vaikI~
        	Dump.println(e,"createIntentPlayVideo videoID="+PvideoID);//+vaikI~
        }                                                          //+vaikI~
    	if (Dump.Y) Dump.println("Utube.createIntentPlayVideo videoID="+PvideoID+",intent="+Utils.toString(intent));//+vaikI~
        return intent;                                             //+vaikI~
    }                                                              //+vaikI~
//**********************************                               //+vaikI~
	public static boolean chkIntentPlayPlaylist(Context Pcontext)  //+vaikI~
    {                                                              //+vaikI~
    	boolean rc=false;                                          //+vaikI~
        try                                                        //+vaikI~
        {                                                          //+vaikI~
	       	rc=YouTubeIntents.canResolvePlayPlaylistIntent(Pcontext);//+vaikI~
        }                                                          //+vaikI~
        catch (Exception e)                                        //+vaikI~
        {                                                          //+vaikI~
        	Dump.println(e,"chkIntentPlayPlaylist");               //+vaikI~
        }                                                          //+vaikI~
    	if (Dump.Y) Dump.println("Utube.chkIntentPlayPlaylist rc="+rc);//+vaikI~
        return rc;                                                 //+vaikI~
    }                                                              //+vaikI~
//**********************************                               //+vaikI~
	public static boolean chkIntentOpenPlaylist(Context Pcontext)  //~vaikI~
    {                                                              //~vaikI~
    	boolean rc=false;                                          //~vaikI~
        try                                                        //~vaikI~
        {                                                          //~vaikI~
	       	rc=YouTubeIntents.canResolveOpenPlaylistIntent(Pcontext);//~vaikI~
        }                                                          //~vaikI~
        catch (Exception e)                                        //~vaikI~
        {                                                          //~vaikI~
        	Dump.println(e,"chkIntentOpenPlaylist");               //~vaikI~
        }                                                          //~vaikI~
    	if (Dump.Y) Dump.println("Utube.chkIntentOpenPlaylist rc="+rc);//~vaikI~
        return rc;                                                 //~vaikI~
    }                                                              //~vaikI~
//**********************************                               //~vaikI~
	public static Intent createIntentPlayPlaylist(Context Pcontext,String PlistID)//~vaikI~
    {                                                              //~vaikI~
    	Intent intent=null;                                        //~vaikI~
        try                                                        //~vaikI~
        {                                                          //~vaikI~
	       	intent=YouTubeIntents.createPlayPlaylistIntent(Pcontext,PlistID);//~vaikI~
        }                                                          //~vaikI~
        catch (Exception e)                                        //~vaikI~
        {                                                          //~vaikI~
        	Dump.println(e,"createIntentPlayPlaylist PlaylistID="+PlistID);//~vaikI~
        }                                                          //~vaikI~
    	if (Dump.Y) Dump.println("Utube.createIntentPlayPlaylist PlayListID="+PlistID+",intent="+Utils.toString(intent));//~vaikI~
        return intent;                                             //~vaikI~
    }                                                              //~vaikI~
//**********************************                               //~vaikI~
	public static Intent createIntentOpenPlaylist(Context Pcontext,String PlistID)//~vaikI~
    {                                                              //~vaikI~
    	Intent intent=null;                                        //~vaikI~
        try                                                        //~vaikI~
        {                                                          //~vaikI~
	       	intent=YouTubeIntents.createOpenPlaylistIntent(Pcontext,PlistID);//~vaikI~
        }                                                          //~vaikI~
        catch (Exception e)                                        //~vaikI~
        {                                                          //~vaikI~
        	Dump.println(e,"createIntentOpenPlaylist PlaylistID="+PlistID);//~vaikI~
        }                                                          //~vaikI~
    	if (Dump.Y) Dump.println("Utube.createIntentOpenPlaylist PlaylistID="+PlistID+",intent="+Utils.toString(intent));//~vaikI~
        return intent;                                             //~vaikI~
    }                                                              //~vaikI~
//**********************************                               //~vaikI~
	public static boolean requestToBrowser(Context Pcontext,Uri Puri)//~vaikI~
    {                                                              //~vaikI~
    	boolean rc=false;                                          //~vaikI~
        try                                                        //~vaikI~
        {                                                          //~vaikI~
	       	Intent intent=new Intent(Intent.ACTION_VIEW,Puri);     //~vaikI~
            Pcontext.startActivity(intent);                        //~vaikI~
            rc=true;                                               //~vaikI~
        }                                                          //~vaikI~
        catch (Exception e)                                        //~vaikI~
        {                                                          //~vaikI~
        	Dump.println(e,"requestToBrowser uri="+Puri.toString());//~vaikI~
        }                                                          //~vaikI~
    	if (Dump.Y) Dump.println("Utube.requestToBrowser uri=="+Puri+",rc="+rc);//~vaikI~
        return rc;                                                 //~vaikI~
    }                                                              //~vaikI~
//**********************************                               //~vaikI~
	public static Uri getUriVideo(String PvideoID)                  //~vaikI~
    {                                                              //~vaikI~
    	String strUri="https://www.youtube.com/watch?v="+PvideoID;//~vaikI~
    	Uri uri=Uri.parse(strUri);                                 //~vaikI~
    	if (Dump.Y) Dump.println("Utube.getUriPlaylist videoID="+PvideoID+",uri="+uri.toString());//~vaikI~
        return uri;                                                //~vaikI~
    }                                                              //~vaikI~
//**********************************                               //~vaikI~
	public static Uri getUriPlaylist(String PlistID)               //~vaikI~
    {                                                              //~vaikI~
    	String strUri="https://www.youtube.com/playlist?list="+PlistID;//~vaikI~
    	Uri uri=Uri.parse(strUri);                                 //~vaikI~
    	if (Dump.Y) Dump.println("Utube.getUriPlaylist PlaylistID="+PlistID+",uri="+uri.toString());//~vaikI~
        return uri;                                                //~vaikI~
    }                                                              //~vaikI~
//**********************************                               //~vaikI~
	public static boolean openPlaylist(Context Pcontext,String PlistID)//~vaikI~
    {                                                              //~vaikI~
    	if (Dump.Y) Dump.println("Utube.openPlaylist PlaylistID="+PlistID);//~vaikI~
        boolean rc=false;                                          //~vaikI~
		if (chkIntentOpenPlaylist(Pcontext))                       //~vaikI~
        {                                                          //~vaikI~
	        Intent intent=createIntentOpenPlaylist(Pcontext,PlistID);//~vaikI~
            if (intent!=null)                                      //~vaikI~
            {                                                      //~vaikI~
	            Pcontext.startActivity(intent);                    //~vaikI~
                rc=true;                                           //~vaikI~
            }                                                      //~vaikI~
        }                                                          //~vaikI~
        else                                                       //~vaikI~
        {                                                          //~vaikI~
        	Uri uri=getUriPlaylist(PlistID);                           //~vaikI~
			rc=requestToBrowser(Pcontext,uri);                     //~vaikR~
        }                                                          //~vaikI~
    	if (Dump.Y) Dump.println("Utube.openPlaylist PlaylistID="+PlistID+",rc="+rc);//~vaikI~
        return rc;                                                 //~vaikI~
    }                                                              //~vaikI~
//**********************************                               //+vaikI~
	public static boolean playVideo(Context Pcontext,String PvideoID)//+vaikI~
    {                                                              //+vaikI~
    	if (Dump.Y) Dump.println("Utube.playVideo videoID="+PvideoID);//+vaikI~
        boolean rc=false;                                          //+vaikI~
		if (chkIntentPlayVideo(Pcontext))                          //+vaikI~
        {                                                          //+vaikI~
	        Intent intent=createIntentPlayVideo(Pcontext,PvideoID); //+vaikI~
            if (intent!=null)                                      //+vaikI~
            {                                                      //+vaikI~
	            Pcontext.startActivity(intent);                    //+vaikI~
                rc=true;                                           //+vaikI~
            }                                                      //+vaikI~
        }                                                          //+vaikI~
        else                                                       //+vaikI~
        {                                                          //+vaikI~
        	Uri uri=getUriVideo(PvideoID);                          //+vaikI~
			rc=requestToBrowser(Pcontext,uri);                     //+vaikI~
        }                                                          //+vaikI~
    	if (Dump.Y) Dump.println("Utube.playVideo videoID="+PvideoID+",rc="+rc);//+vaikI~
        return rc;                                                 //+vaikI~
    }                                                              //+vaikI~
}//class Utube                                                     //~vaikR~
