//*CID://+vayWR~:                             update#=  127;       //+vayWR~
//************************************************************************//~v102I~
//2025/02/28 vayW drop redundant toast                             //+vayWI~
//2022/07/04 van1 hungle suuprt for Help                           //~van1I~
//2021/12/24 vaie Scoped device->sdcard device;History rule send fails.//~vaieI~
//2021/09/19 vae8 keep sharedPreference to external storage with PrefSetting item.//~vae8I~
//2021/09/17 vae7 Scoped for BTMJ5, SDcard data transfer           //~vae7I~
//2021/08/25 vae0 Scped for BTMJ5                                  //~vae0I~
//1ak2 2021/09/04 access external audio file                       //~1ak2I~
//1ak1 2021/08/27 write Dump.txt to internal cache, it ca be pull by run-as cmd//~1ak1I~
//2021/08/25 vad5 move Dump.txt to cache to avoid /sdcard          //~vad5I~
//2020/11/19 va42 At Android10, mkdir /sdcard/eMahjong fails       //~va42I~
//                manifest: application-->requestLagacyExternalStorage="true" (ignored when target=androd11)//~va42I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//************************************************************************//~v102I~
package com.btmtest.utils;                                        //~1110I~//~v107R~//~1Ad2R~
                                                                   //~1110I~
                                                                   //~1110I~
import com.btmtest.AG;
import com.btmtest.MainActivity;
import com.btmtest.MainView;
import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.TestOption;

import static android.os.Environment.*;
import static com.btmtest.AG.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GConst.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;                           //~v@@@I~
import android.os.Environment;
                                                                   //~1110I~
public class UFile                                                 //~v@@@R~
{                                                                  //~1110I~
    public static final String GAMEFILE="GameFileFolder";          //~1402I~
                                                                   //~1402I~
	public static final String DATAFILE_PREFIX="save";             //~1309I~//~1313R~
	public static final String SDFILE_PREFIX="rename";             //~1423I~
	private static final int BUFFSZ=1024;                          //~1511R~
	private static final int BUFFSZ2=32760;                        //~1511I~
//    private static boolean swSDAvailable=true;                  //~1313I~//~v@@@R~
//    private static String dirSD;                              //~1425R~//~v@@@R~
//    private static int rawFileId;                                  //~1425R~//~v@@@R~
	private static boolean swLegacy;                               //~vae7I~
//********************************************************         //~1401I~
//*to intercept FileOuputStream of Global                          //~1401I~
//* return FileOutputStream for cfgfile on /data/data              //~1401I~
//********************************************************         //~1401I~
//    public static String getOutputFilenameData(String Pfilename)   //~1401I~//~v@@@R~
//    {                                                              //~1401I~//~v@@@R~
//        String path,filename;                                      //~1401R~//~v@@@R~
//                                           //~1401I~             //~v@@@R~
//    //***************************                                  //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("getFileOutputStreamData:"+Pfilename);//~1506R~//~v@@@R~
//        filename=getConfigFilename(Pfilename);                     //~1401R~//~v@@@R~
//        path=AG.context.getFilesDir()+"/"+filename;                //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("GetFileOutputStreamData path="+path);//~1506R~//~v@@@R~
//        return path;                                               //~1401R~//~v@@@R~
//    }                                                              //~1401I~//~v@@@R~
////********************************************************         //~1329I~//~v@@@R~
////*to intercept FileInputStream of Global                          //~1329I~//~v@@@R~
////* return filename for  cfgfile                                   //~1329I~//~v@@@R~
////*   save.xxx.cfg on /data/data                                   //~1329I~//~v@@@R~
////*   if not save copy from /res/raw to /data/data                 //~1329I~//~v@@@R~
////    for other than cfg file return /data/data/fnm                //~1329I~//~v@@@R~
////* return helpfilename on SDcard                                  //~1412I~//~v@@@R~
////********************************************************         //~1329I~//~v@@@R~
//    public static String getInputFilenameDataRaw(String Pfilename) //~1329R~//~v@@@R~
//        throws NotFoundException                                   //~1329I~//~v@@@R~
//    {                                                              //~1329I~//~v@@@R~
//        String path,filename;                 //~1329I~            //~1401R~//~v@@@R~
//    //***************************                                  //~1329I~//~v@@@R~
//        if (Dump.Y) Dump.println("getFileInputFilenameDataRaw:"+Pfilename);//~1506R~//~v@@@R~
//        if ((path=getHelpFilename(Pfilename))!=null)               //~1412I~//~v@@@R~
//            return path;                                           //~1412I~//~v@@@R~
//        filename=getConfigFilename(Pfilename);                     //~1401R~//~v@@@R~
//        if (Dump.Y) Dump.println("GetInputFilenameDataRaw filename="+filename);//~1329I~//~1506R~//~v@@@R~
//        path=AG.context.getFilesDir()+"/"+filename;                //~1401I~//~v@@@R~
//        if (rawFileId==0)   //not cfg file                                 //~1329I~//~1401R~//~v@@@R~
//            return path;                                       //~1329I~//~1401R~//~v@@@R~
//        if ((new File(path)).exists())  //file on /data/data   //~1329I~//~1423R~//~v@@@R~
//        {                                                          //~1423R~//~v@@@R~
//            if (Dump.Y) Dump.println("GetFileInputFilenameDataRaw exist path="+path);//~1329I~//~1506R~//~v@@@R~
//            return path;                                       //~1329I~//~1423R~//~v@@@R~
//        }                                                          //~1423R~//~v@@@R~
//        copyToDataDir(rawFileId,filename);                         //~1423R~//~v@@@R~
//        return path;                                           //~1329I~//~1401R~//~v@@@R~
//    }                                                              //~1329I~//~v@@@R~
////********************************************************         //~1419R~//~v@@@R~
////*to intercept FileInputStream of Global                          //~1419R~//~v@@@R~
////* return inputStream for cfg/help file                           //~1419R~//~v@@@R~
////*   save.xxx.cfg on /data/data                                   //~1419R~//~v@@@R~
////*   if not save copy from /res/raw to /data/data                 //~1419R~//~v@@@R~
////    for other than cfg file return    /data/data/fnm             //~1419R~//~v@@@R~
////* return helpfile InputSream on SDcardm,if not found of asset/   //~1419R~//~v@@@R~
////********************************************************         //~1419R~//~v@@@R~
//    public static InputStream getInputStreamDataRaw(String Pfilename)//~1419R~//~v@@@R~
//        throws FileNotFoundException                               //~1419R~//~v@@@R~
//    {                                                              //~1419R~//~v@@@R~
//        String path,filename;                                      //~1419R~//~v@@@R~
//        InputStream is;                                            //~1419R~//~v@@@R~
//    //***************************                                  //~1419R~//~v@@@R~
//        if (Dump.Y) Dump.println("getInputStreamDataRaw:"+Pfilename);//~1506R~//~v@@@R~
//        if ((is=getInputStreamHelpFile(Pfilename))!=null)          //~1419R~//~v@@@R~
//            return is;                                             //~1419R~//~v@@@R~
//        filename=getConfigFilename(Pfilename);                     //~1419R~//~v@@@R~
//        if (Dump.Y) Dump.println("getFileInputStreamDataRaw filename="+filename);//~1506R~//~v@@@R~
//        path=AG.context.getFilesDir()+"/"+filename;                //~1419R~//~v@@@R~
//        if (rawFileId==0)   //not cfg file                         //~1419R~//~v@@@R~
//            return new FileInputStream(path);                      //~1419R~//~v@@@R~
//        if (!chkConfigOnSD(filename))   //chk new file on SDcard   //~1423R~//~v@@@R~
//        {                                                          //~1423M~//~v@@@R~
//            if ((new File(path)).exists())  //file on /data/data   //~1423R~//~v@@@R~
//            {                                                      //~1423R~//~v@@@R~
//                if (Dump.Y) Dump.println("getInputStreamDataRaw exist path="+path);//~1506R~//~v@@@R~
//                return new FileInputStream(path);                  //~1423R~//~v@@@R~
//            }                                                      //~1423R~//~v@@@R~
//            copyToDataDir(rawFileId,filename);                     //~1423R~//~v@@@R~
//        }                                                          //~1423M~//~v@@@R~
//        return new FileInputStream(path);                          //~1419R~//~v@@@R~
//    }                                                              //~1419R~//~v@@@R~
////********************************************************         //~1401I~//~v@@@R~
////*get real filename                                               //~1401I~//~v@@@R~
////********************************************************         //~1401I~//~v@@@R~
//    public static String getConfigFilename(String Pfilename)       //~1401I~//~v@@@R~
//    {                                                              //~1401I~//~v@@@R~
//        String filename,home;            //~1401I~               //~v@@@R~
//        int id=0;                                                  //~1401I~//~v@@@R~
//    //***************************                                  //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("getConfigFilename:"+Pfilename);  //~1506R~//~v@@@R~
//        home=Global.home();                                        //~1401I~//~v@@@R~
//        if (Pfilename.equals(home+".go.cfg"))                      //~1401I~//~v@@@R~
//            id=R.raw.go;                                           //~1401I~//~v@@@R~
//        else                                                       //~1401I~//~v@@@R~
//        if (Pfilename.equals(home+".filter.cfg"))                  //~1401I~//~v@@@R~
//            id=R.raw.filter;                                       //~1401I~//~v@@@R~
//        else                                                       //~1401I~//~v@@@R~
//        if (Pfilename.equals(home+".server.cfg"))                  //~1401I~//~v@@@R~
//            id=R.raw.server;                                       //~1401I~//~v@@@R~
//        else                                                       //~1401I~//~v@@@R~
//        if (Pfilename.equals(home+".partner.cfg"))                 //~1401I~//~v@@@R~
//            id=R.raw.partner;                                      //~1401I~//~v@@@R~
//        if (id!=0)                                                 //~1401I~//~v@@@R~
//            filename=DATAFILE_PREFIX+Pfilename.substring(home.length());//~1401I~//~v@@@R~
//        else                                                       //~1401I~//~v@@@R~
//            filename=Pfilename;                                    //~1401I~//~v@@@R~
//        rawFileId=id;                                              //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("getConfigFilename  filename="+filename+",id="+Integer.toHexString(id));//~1506R~//~v@@@R~
//        return filename;                                           //~1401R~//~v@@@R~
//    }                                                              //~1401I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
////*chk new cfg file on SDcard                                      //~1423I~//~v@@@R~
////* if exit cpy to data/data then rename                           //~1423I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
//    public static boolean chkConfigOnSD(String Pfilename)          //~1423R~//~v@@@R~
//    {                                                              //~1423I~//~v@@@R~
//    //***************************                                  //~1423I~//~v@@@R~
//        if (Dump.Y) Dump.println("chkConfigOnSD:"+Pfilename);      //~1506R~//~v@@@R~
//        String filenameSD=Pfilename.substring(DATAFILE_PREFIX.length()+1); //last name//~1423R~//~v@@@R~
//        String cfgSDfile=getSDPath(filenameSD);   //path           //~1423R~//~v@@@R~
//        if (cfgSDfile==null)                                       //~v102I~//~v@@@R~
//            return false;                                          //~v102I~//~v@@@R~
//        if (!(new File(cfgSDfile)).exists())    //file on /data/data//~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        if (Dump.Y) Dump.println("chkConfigOnSD exist path="+cfgSDfile);//~1506R~//~v@@@R~
//        if (!copyToDataDir(filenameSD,Pfilename))                  //~1423R~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        if (renameFile(cfgSDfile))                             //~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        return true;                                               //~1423I~//~v@@@R~
//    }                                                              //~1423I~//~v@@@R~
//********************************************************         //~1412I~
//*return SDCARD/jagoclient/hexlptext/subjext.txt if exist         //~1418R~
//********************************************************         //~1412I~
////********************************************************         //~1419I~//~v@@@R~
////*return InputStream for helpfile; SDCARD or asset folder         //~1419R~//~v@@@R~
////********************************************************         //~1419I~//~v@@@R~
//    public static InputStream getInputStreamHelpFile(String Pfilename)//~v@@@R~
//        throws FileNotFoundException                             //~v@@@R~
//    {                                                              //~1419I~//~v@@@R~
//        InputStream is;                                            //~1419I~//~v@@@R~
//        String filename,home;                                           //~1419I~//~v@@@R~
//    //***************************                                  //~1419I~//~v@@@R~
//        if (Dump.Y) Dump.println("getHelpFilename:"+Pfilename);    //~1506R~//~v@@@R~
//        home=AG.home();//~1419I~                                 //~v@@@R~
//        if (!Pfilename.startsWith(home+"helptexts"))               //~1Ad2I~//~v@@@R~
//            return null;                                           //~1419I~//~v@@@R~
//        filename=Pfilename.substring(home.length());                //~1419I~//~v@@@R~
//        is=openInputSD(filename);   //check SD card                //~1419I~//~v@@@R~
//        if (is==null)                                              //~1419I~//~v@@@R~
//            try                                                  //~v@@@R~
//            {                                                    //~v@@@R~
//                is=AG.resource.getAssets().open(filename);             //~1419I~//~v@@@R~
//            }                                                    //~v@@@R~
//            catch(Exception e)                                   //~v@@@R~
//            {                                                    //~v@@@R~
//                throw new FileNotFoundException();               //~v@@@R~
//            }                                                    //~v@@@R~
//        return is;                                                 //~1419R~//~v@@@R~
//    }                                                              //~1419I~//~v@@@R~
//********************************************************         //~v@@@I~
//*get help file text                                              //~v@@@I~
//********************************************************         //~v@@@I~
	public static String getHelpFileText(String Pfilename)         //~v@@@I~
	{                                                              //~v@@@I~
        String txt;                                                //~v@@@I~
    	InputStream is;                                            //~v@@@I~
    //***************************                                  //~v@@@I~
        if (Dump.Y) Dump.println("UFile.getHelpFileText:"+Pfilename);    //~v@@@I~//~vae0R~
    	String fnm="helptexts/"+Pfilename+AG.helpFileSuffix+".txt";//~v@@@R~
//  	String txt=loadAssetTextFile(fnm,null/*encoding=utf8*/);   //~v@@@R~
    	if (AG.helpFileSuffix.equals(""))                          //~v@@@I~
			txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,true); //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,false);//~v@@@I~
            if (txt==null)                                         //~v@@@I~
            {                                                      //~v@@@I~
		    	fnm="helptexts/"+Pfilename+".txt";                 //~v@@@I~
				txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,true);//~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
		return txt;                                                //~v@@@I~
	}                                                              //~v@@@I~
//********************************************************         //~v@@@I~
//*get help file text extension specified                          //~v@@@I~
//********************************************************         //~v@@@I~
	public static String getHelpFileExt(String Pfilename,String Pextension,boolean PswNFMsg)//~v@@@I~
	{                                                              //~v@@@I~
    	InputStream is;                                            //~v@@@I~
        String txt;                                                //~v@@@I~
    //***************************                                  //~v@@@I~
        if (Dump.Y) Dump.println("UFile.getHelpFileText:"+Pfilename);    //~v@@@I~//~vae0R~
    	String fnm="helptexts/"+Pfilename+AG.helpFileSuffix+Pextension;//~v@@@I~
//  	String txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,PswNFMsg);//~v@@@I~
    	if (AG.helpFileSuffix.equals(""))                          //~v@@@I~
			txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,PswNFMsg);//~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
	    	txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,false);//~v@@@I~
            if (txt==null)                                         //~v@@@I~
            {                                                      //~v@@@I~
		    	fnm="helptexts/"+Pfilename+Pextension;             //~v@@@I~
	    		txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,PswNFMsg);//~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
		return txt;                                                //~v@@@I~
	}                                                              //~v@@@I~
//********************************************************         //~van1I~
//*get help file text extension specified for Lang=ko              //~van1I~
//********************************************************         //~van1I~
	public static String getHelpFileExtKO(String Pfilename,String Pextension,boolean PswNFMsg)//~van1I~
	{                                                              //~van1I~
    	InputStream is;                                            //~van1I~
        String txt,suffix="";                                         //~van1I~
    //***************************                                  //~van1I~
        if (Dump.Y) Dump.println("UFile.getHelpFileTextKO fnm="+Pfilename+",ext="+Pextension+"AG.isLangKO="+AG.isLangKO);//~van1I~
        switch(AG.currentHelpLang)                                 //~van1I~
        {                                                          //~van1I~
        case CHL_EN:    //english                               //~van1I~
        	suffix="";                                             //~van1I~
        	break;                                                 //~van1I~
        case CHL_JP:    //japanese                              //~van1I~
        	suffix="_ja";                                          //~van1I~
        	break;                                                 //~van1I~
        case CHL_KO_FROM_EN: //translate en to ko               //~van1I~
        	suffix=AG.helpFileSuffixKO;                           //~van1I~
        	break;                                                 //~van1I~
//      case CHL_KO_FROM_JP: //translate en to ko                  //~van1R~
//      	suffix=AG.helpFileSuffixKO+"_ja";                      //~van1R~
//      	break;                                                 //~van1R~
        }                                                          //~van1I~
    	String fnm="helptexts/"+Pfilename+suffix+Pextension;       //~van1I~
	    txt=loadAssetTextFile(fnm,null/*encoding=utf8*/,false);    //~van1I~
		return txt;                                                //~van1I~
	}                                                              //~van1I~
//********************************************************         //~v@@@I~
//*get asset file text                                             //~v@@@I~
//********************************************************         //~v@@@I~
	public static String loadAssetTextFile(String Pfnm,String Pencoding)//~v@@@I~
	{                                                              //~v@@@I~
		return loadAssetTextFile(Pfnm,Pencoding,true);             //~v@@@I~
    }                                                              //~v@@@I~
	public static String loadAssetTextFile(String Pfnm,String Pencoding,boolean PswNFMsg)//~v@@@I~
	{                                                              //~v@@@I~
    	String txt=null;                                           //~v@@@R~
    //***************************                                  //~v@@@I~
    	String encoding=Pencoding;                                  //~v@@@I~
        if (encoding==null)                                        //~v@@@I~
        	encoding="UTF-8";                                      //~v@@@I~
        if (Dump.Y) Dump.println("UFile.loadAssetTextFile:"+Pfnm);       //~v@@@I~//~vae0R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	final AssetManager mgr=AG.context.getAssets();          //~v@@@I~
            InputStream is=mgr.open(Pfnm);                         //~v@@@I~
            txt=loadText(is,encoding);                             //~v@@@I~
        }                                                          //~v@@@I~
		catch(FileNotFoundException e)                             //~v@@@I~
        {                                                          //~v@@@I~
        	if (PswNFMsg)                                          //~v@@@I~
	        	UView.showToastLong(R.string.ErrAssetFileNotFound,Pfnm);//~v@@@R~
        }                                                          //~v@@@I~
		catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
        	UView.showToastLong(R.string.ErrAssetFileRead,Pfnm);   //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("loadAssetFile filename="+Pfnm+",txt="+txt);//~van1I~
		return txt;                                                //~v@@@I~
	}                                                              //~v@@@I~
    private static String loadText(InputStream Pis,String Pencoding) throws IOException//~v@@@I~
    {                                                              //~v@@@I~
    	return new String(readStream(Pis,32768),Pencoding);       //~v@@@I~
    }                                                              //~v@@@I~
    private static byte[] readStream(InputStream Pis,int Pbuffsz) throws IOException//~v@@@I~
    {                                                              //~v@@@I~
    	ByteArrayOutputStream bs= new ByteArrayOutputStream(Pbuffsz);//~v@@@I~
        byte[] bytes=new byte[Pbuffsz];                            //~v@@@I~
        BufferedInputStream bis=new BufferedInputStream(Pis,Pbuffsz);//~v@@@I~
        int len=0;                                                 //~v@@@I~
        while ((len=bis.read(bytes,0,Pbuffsz))>0)                  //~v@@@I~
        {                                                          //~v@@@I~
            bs.write(bytes,0,len);                                 //~v@@@I~
        }                                                          //~v@@@I~
        byte[] ba=bs.toByteArray();                                //~v@@@I~
        bs.reset();                                                //~v@@@I~
        bis.close();                                               //~v@@@I~
        return ba;                                                 //~v@@@R~
    }                                                              //~v@@@I~
////********************************************************         //~1511I~//~v@@@R~
////*open Assetfile for Agnugo copy                                  //~1511I~//~v@@@R~
////********************************************************         //~1511I~//~v@@@R~
//    public static AssetFileDescriptor openAssetFileFD(String Pfilename)//~1511I~//~v@@@R~
//    {                                                              //~1511I~//~v@@@R~
//        AssetFileDescriptor fd;                                    //~1511I~//~v@@@R~
//    //***************************                                  //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("openAssetFileFD:"+Pfilename);    //~1511I~//~v@@@R~
//        try                                                        //~1511I~//~v@@@R~
//        {                                                          //~1511I~//~v@@@R~
//            fd=AG.resource.getAssets().openFd(Pfilename);          //~1511I~//~v@@@R~
//        }                                                          //~1511I~//~v@@@R~
//        catch(Exception e)                                         //~1511I~//~v@@@R~
//        {                                                          //~1511I~//~v@@@R~
//            if (Dump.Y) Dump.println("openAssetFileFD failed:"+Pfilename);//~1511I~//~v@@@R~
//            fd=null;                                               //~1511I~//~v@@@R~
//        }                                                          //~1511I~//~v@@@R~
//        return fd;                                                 //~1511I~//~v@@@R~
//    }                                                              //~1511I~//~v@@@R~
//********************************************************         //~1511I~//~v@@@R~
//*open Assetfile for Agnugo copy                                  //~1511I~//~v@@@R~
//********************************************************         //~1511I~//~v@@@R~
    public static InputStream openAssetFile(String Pfilename,boolean Pshowexception)      //~1511I~//~v@@@R~
    {                                                              //~1511I~//~v@@@R~
        InputStream is;                                            //~1511I~//~v@@@R~
    //***************************                                  //~1511I~//~v@@@R~
        if (Dump.Y) Dump.println("UFile.openAssetFile:"+Pfilename);      //~1511I~//~v@@@R~
        try                                                        //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            is=AG.resource.getAssets().open(Pfilename);            //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        catch(Exception e)                                         //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            if (Dump.Y) Dump.println("UFile.openAssetFile failed:"+Pfilename);//~1511I~//~v@@@I~//~vae0R~
        	if (Pshowexception)                                    //~v@@@I~
	            Dump.println(e,"UFile.openAssetFile:"+Pfilename);        //~v@@@I~//~vae0R~
            is=null;                                               //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        return is;                                                 //~1511I~//~v@@@R~
    }                                                              //~1511I~//~v@@@R~
////********************************************************         //~1511I~//~v@@@R~
////*open Assetfile for Agnugo copy                                  //~1511I~//~v@@@R~
////********************************************************         //~1511I~//~v@@@R~
//    public static long getAssetFileSize(String Pfilename)          //~1511I~//~v@@@R~
//    {                                                              //~1511I~//~v@@@R~
//        AssetFileDescriptor fd;                                    //~1511I~//~v@@@R~
//        long sz;                                                   //~1511I~//~v@@@R~
//    //***************************                                  //~1511I~//~v@@@R~
//        fd=openAssetFileFD(Pfilename);                             //~1511I~//~v@@@R~
//        if (fd==null)                                              //~1511I~//~v@@@R~
//            sz=-1;                                                 //~1511I~//~v@@@R~
//        else                                                       //~1511I~//~v@@@R~
//            sz=fd.getLength();                                      //~1511I~//~v@@@R~
//        try                                                      //~v@@@R~
//         {                                                       //~v@@@R~
//            fd.close();                                          //~v@@@R~
//         }                                                       //~v@@@R~
//        catch (IOException e)                                    //~v@@@R~
//         {                                                       //~v@@@R~
//            Dump.println(e,"xception Asset openFD"+Pfilename);   //~v@@@R~
//            e.printStackTrace();                                 //~v@@@R~
//        }                                                //~1511I~//~v@@@R~
//        return sz;                                                 //~1511I~//~v@@@R~
//    }                                                              //~1511I~//~v@@@R~
////********************************************************         //~1327I~//~v@@@R~
////*copy /res/raw file to /data/data private dir                    //~1329I~//~v@@@R~
////*retur success/false                                             //~1329I~//~v@@@R~
////********************************************************         //~1329I~//~v@@@R~
//    public static boolean copyToDataDir(int Prawresid,String Pfname)//~1327I~//~v@@@R~
//    {                                                              //~1327I~//~v@@@R~
//        InputStream is;                                       //~1327I~//~v@@@R~
//        FileOutputStream fos;                                      //~1327I~//~v@@@R~
//        int len;                                                   //~1327I~//~v@@@R~
//        boolean success=true;                                      //~1327I~//~v@@@R~
//        byte [] buff;                                              //~1327I~//~v@@@R~
//    //*********************                                        //~1327I~//~v@@@R~
//        if (Dump.Y) Dump.println("copyToDataDir:"+Pfname);         //~1506R~//~v@@@R~
//        is=AG.resource.openRawResource(Prawresid);  //read from res/raw//~1327I~//~v@@@R~
//        if (is==null)  //1st time    no save.xxx on data/data     //~1327I~//~v@@@R~
//            return false;                                          //~1327I~//~v@@@R~
//        fos=openOutputData(Pfname); ///data/data (no search SD)    //~1329R~//~v@@@R~
//        if (fos==null)                                             //~1327I~//~v@@@R~
//            return false;                                          //~1327I~//~v@@@R~
//        buff=new byte[BUFFSZ];                                     //~1327I~//~v@@@R~
//        try                                                        //~1327I~//~v@@@R~
//        {                                                          //~1327I~//~v@@@R~
//            for (;;)                                               //~1327I~//~v@@@R~
//            {                                                      //~1327I~//~v@@@R~
//                len=is.read(buff);                                 //~1327I~//~v@@@R~
//                if (len<0)                                         //~1327I~//~v@@@R~
//                    break;                                         //~1327I~//~v@@@R~
//                fos.write(buff,0,len);                             //~1327I~//~v@@@R~
//            }                                                      //~1327I~//~v@@@R~
//        }                                                          //~1327I~//~v@@@R~
//        catch (Exception e)                                        //~1327I~//~v@@@R~
//        {                                                          //~1327I~//~v@@@R~
//            success=false;                                         //~1327I~//~v@@@R~
//            Dump.println(e,"copyToDataDir output exception "+Pfname);//~1327I~//~v@@@R~
//        }//catch                                                   //~1327I~//~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            is.close();                                               //~1327I~//~v@@@R~
//            fos.close();                                         //~v@@@R~
//            if (Dump.Y) Dump.println("close copy to SDfailed"+Pfname);//~1506R~//~v@@@R~
//        }                                                        //~v@@@R~
//        catch(IOException e)                                     //~v@@@R~
//        {                                                        //~v@@@R~
//            success=false;                                       //~v@@@R~
//            Dump.println(e,"close failed"+Pfname);//~1327I~      //~v@@@R~
//        }                                                        //~v@@@R~
//        return success;                                            //~1327I~//~v@@@R~
//    }                                                              //~1327I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
////*copy /sdcard/Ajagoc/cfg file to /data/data private dir          //~1423I~//~v@@@R~
////*retur success/false                                             //~1423I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
//    public static boolean copyToDataDir(String Pfname,String PfnameData)//~1423I~//~v@@@R~
//    {                                                              //~1423I~//~v@@@R~
//        InputStream is;                                            //~1423I~//~v@@@R~
//        FileOutputStream fos;                                      //~1423I~//~v@@@R~
//        int len;                                                   //~1423I~//~v@@@R~
//        boolean success=true;                                      //~1423I~//~v@@@R~
//        byte [] buff;                                              //~1423I~//~v@@@R~
//    //*********************                                        //~1423I~//~v@@@R~
//        if (Dump.Y) Dump.println("copyToDataDir:SD="+Pfname+",data="+PfnameData);//~1506R~//~v@@@R~
//        is=openInputSD(Pfname);                  //~1423I~       //~v@@@R~
//        if (is==null)                                             //~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        fos=openOutputData(PfnameData); ///data/data (no search SD)//~1423R~//~v@@@R~
//        if (fos==null)                                             //~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        buff=new byte[BUFFSZ];                                     //~1423I~//~v@@@R~
//        try                                                        //~1423I~//~v@@@R~
//        {                                                          //~1423I~//~v@@@R~
//            for (;;)                                               //~1423I~//~v@@@R~
//            {                                                      //~1423I~//~v@@@R~
//                len=is.read(buff);                                //~1423I~//~v@@@R~
//                if (len<0)                                         //~1423I~//~v@@@R~
//                    break;                                         //~1423I~//~v@@@R~
//                fos.write(buff,0,len);                             //~1423I~//~v@@@R~
//            }                                                      //~1423I~//~v@@@R~
//        }                                                          //~1423I~//~v@@@R~
//        catch (Exception e)                                        //~1423I~//~v@@@R~
//        {                                                          //~1423I~//~v@@@R~
//            success=false;                                         //~1423I~//~v@@@R~
//            Dump.println(e,"copyToDataDir output exception "+Pfname);//~1423I~//~v@@@R~
//        }//catch                                                   //~1423I~//~v@@@R~
//        try                                                        //~1423I~//~v@@@R~
//        {                                                          //~1423I~//~v@@@R~
//            is.close();                                           //~1423I~//~v@@@R~
//            fos.close();                                           //~1423I~//~v@@@R~
//            if (Dump.Y) Dump.println("close copy to Data failed"+Pfname);//~1506R~//~v@@@R~
//        }                                                          //~1423I~//~v@@@R~
//        catch(IOException e)                                       //~1423I~//~v@@@R~
//        {                                                          //~1423I~//~v@@@R~
//            success=false;                                         //~1423I~//~v@@@R~
//            Dump.println(e,"close failed"+Pfname);                 //~1423I~//~v@@@R~
//        }                                                          //~1423I~//~v@@@R~
//        return success;                                            //~1423I~//~v@@@R~
//    }                                                              //~1423I~//~v@@@R~
//********************************************************         //~v@@@I~
    public static FileInputStream newFileInputStream(String Pfnm)  //~v@@@I~
    {                                                              //~v@@@I~
		FileInputStream fis=null;                                  //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
			fis=new FileInputStream(Pfnm);                         //~v@@@I~
        }                                                          //~v@@@I~
	    catch (Exception e) //throw FileNotFoundException          //~v@@@I~
    	{                                                          //~v@@@I~
        	Dump.println(false/*toast*/,e,"new FileInputStream name="+Pfnm);//~v@@@I~
        }                                                          //~v@@@I~
		return fis;                                                //~v@@@I~
    }                                                              //~v@@@I~
//********************************************************         //~v@@@I~
    public static FileOutputStream newFileOutputStream(String Pfnm)//~v@@@I~
    {                                                              //~v@@@I~
		FileOutputStream fos=null;                                 //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
			fos=new FileOutputStream(Pfnm);                        //~v@@@I~
        }                                                          //~v@@@I~
	    catch (Exception e)  //throw FileNotFoundException         //~v@@@I~
    	{                                                          //~v@@@I~
        	Dump.println(false/*toast*/,e,"new FileOutputStream name="+Pfnm);//~v@@@I~
        }                                                          //~v@@@I~
		return fos;                                                //~v@@@I~
    }                                                              //~v@@@I~
//********************************************************         //~v@@@I~
//*copy file /sdcard <-->/data/data/<pkg>                          //~v@@@I~
//*retur success/false                                             //~v@@@I~
//********************************************************         //~v@@@I~
    public static boolean copyFile(String Psrc,String Ptgt)        //~v@@@I~
    {                                                              //~v@@@I~
        int len;                                                   //~v@@@I~
        boolean success=true;                                      //~v@@@I~
        byte [] buff;                                              //~v@@@I~
    //*********************                                        //~v@@@I~
        if (Dump.Y) Dump.println("UFile.copyFile:src="+Psrc+",tgt="+Ptgt);//~v@@@I~//~vae0R~
                                                                   //~v@@@I~
		FileInputStream fis=newFileInputStream(Psrc);              //~v@@@I~
        if (fis==null)                                             //~v@@@I~
            return false;                                          //~v@@@I~
        FileOutputStream fos=newFileOutputStream(Ptgt);            //~v@@@I~
        if (fos==null)                                             //~v@@@I~
            return false;                                          //~v@@@I~
        buff=new byte[BUFFSZ];                                     //~v@@@I~
        for (;;)                                                   //~v@@@I~
        {                                                          //~v@@@I~
        	try                                                    //~v@@@I~
        	{                                                      //~v@@@I~
                len=fis.read(buff);                                //~v@@@I~
            }                                                      //~v@@@I~
	        catch (Exception e) //IOException                      //~v@@@I~
    	    {                                                      //~v@@@I~
            	Dump.println(false,e,"copyFile read name="+Psrc);  //~v@@@I~
            	success=false;                                     //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
            if (len<0)                                             //~v@@@I~
            	break;                                             //~v@@@I~
        	try                                                    //~v@@@I~
        	{                                                      //~v@@@I~
                fos.write(buff,0,len);                             //~v@@@I~
            }                                                      //~v@@@I~
	        catch (Exception e)                                    //~v@@@I~
    	    {                                                      //~v@@@I~
            	Dump.println(false,e,"copyFile write name="+Ptgt); //~v@@@I~
            	success=false;                                     //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            fis.close();                                           //~v@@@I~
	        if (Dump.Y) Dump.println("UFile.closed src="+Psrc);          //~v@@@I~//~vae0R~
        }                                                          //~v@@@I~
        catch(IOException e)                                       //~v@@@I~
        {                                                          //~v@@@I~
            success=false;                                         //~v@@@I~
            Dump.println(false,e,"close failed:"+Psrc);            //~v@@@I~
        }                                                          //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            fos.close();                                           //~v@@@I~
	        if (Dump.Y) Dump.println("UFile.closed tgt="+Ptgt);          //~v@@@I~//~vae0R~
        }                                                          //~v@@@I~
        catch(IOException e)                                       //~v@@@I~
        {                                                          //~v@@@I~
            success=false;                                         //~v@@@I~
            Dump.println(false,e,"close failed:"+Ptgt);            //~v@@@I~
        }                                                          //~v@@@I~
        if (success)                                               //~v@@@I~
        	copyFileTimestamp(Psrc,Ptgt);                          //~v@@@I~
        return success;                                            //~v@@@I~
    }                                                              //~v@@@I~
//********************************************************         //~v@@@I~
//*copy lastModified                                               //~v@@@I~
//********************************************************         //~v@@@I~
    public static boolean copyFileTimestamp(String Psrc,String Ptgt)//~v@@@I~
    {                                                              //~v@@@I~
    	boolean success;                                           //~v@@@I~
        if (Dump.Y) Dump.println("UFile.copyFileTimestamp:src="+Psrc+",tgt="+Ptgt);//~v@@@I~//~vae0R~
		File src=new File(Psrc);                                   //~v@@@I~
		File tgt=new File(Ptgt);                                   //~v@@@I~
        if (Dump.Y) Dump.println("UFile.copyFileTimestamp timestamp before src="+src.lastModified()+",tgt="+tgt.lastModified());//~v@@@I~//~vae0R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
	        success=tgt.setLastModified(src.lastModified());         //~v@@@I~
        }                                                          //~v@@@I~
	    catch (Exception e) //IOException                          //~v@@@I~
    	{                                                          //~v@@@I~
            Dump.println(false,e,"copyFileTimestamp src="+Psrc+",tgt="+Ptgt);//~v@@@I~
            success=false;                                         //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UFile.copyFileTimestamp rc="+success+",src="+Psrc+",tgt="+Ptgt);//~v@@@I~//~vae0R~
        if (Dump.Y) Dump.println("UFile.copyFileTimestamp timestamp after src="+src.lastModified()+",tgt="+tgt.lastModified());//~v@@@I~//~vae0R~
        return success;                                            //~v@@@I~
    }                                                              //~v@@@I~
//********************************************************         //~1511I~//~v@@@R~
//*copy asset file to /data/data/files                             //~v@@@R~
//*retur success/false                                             //~1511I~//~v@@@R~
//********************************************************         //~1511I~//~v@@@R~
    public static boolean copyAssetToDataDir(String Pfname,String PfnameData)//~1511I~//~v@@@R~
    {                                                              //~1511I~//~v@@@R~
        InputStream is;                                            //~1511I~//~v@@@R~
        FileOutputStream fos;                                      //~1511I~//~v@@@R~
        int len;                                                   //~1511I~//~v@@@R~
        boolean success=true;                                      //~1511I~//~v@@@R~
        byte [] buff;                                              //~1511I~//~v@@@R~
    //*********************                                        //~1511I~//~v@@@R~
        if (Dump.Y) Dump.println("UFile.copyToDataDir:SD="+Pfname+",data="+PfnameData);//~1511I~//~v@@@R~//~vae0R~
        is=openAssetFile(Pfname,true);                                  //~1511I~//~v@@@R~
        if (is==null)                                              //~1511I~//~v@@@R~
            return false;                                          //~1511I~//~v@@@R~
        fos=openOutputData(PfnameData); // to /data/data (no search SD)//~1511I~//~v@@@R~
        if (fos==null)                                             //~1511I~//~v@@@R~
            return false;                                          //~1511I~//~v@@@R~
        buff=new byte[BUFFSZ2];                                    //~1511I~//~v@@@R~
        try                                                        //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            for (;;)                                               //~1511I~//~v@@@R~
            {                                                      //~1511I~//~v@@@R~
                len=is.read(buff);                                 //~1511I~//~v@@@R~
                if (len<0)                                         //~1511I~//~v@@@R~
                    break;                                         //~1511I~//~v@@@R~
                fos.write(buff,0,len);                             //~1511I~//~v@@@R~
            }                                                      //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        catch (Exception e)                                        //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            success=false;                                         //~1511I~//~v@@@R~
            Dump.println(e,"copyToDataDir output exception "+Pfname);//~1511I~//~v@@@R~
        }//catch                                                   //~1511I~//~v@@@R~
        try                                                        //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            is.close();                                            //~1511I~//~v@@@R~
            fos.close();                                           //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        catch(IOException e)                                       //~1511I~//~v@@@R~
        {                                                          //~1511I~//~v@@@R~
            success=false;                                         //~1511I~//~v@@@R~
            Dump.println(e,"close failed"+Pfname);                 //~1511I~//~v@@@R~
        }                                                          //~1511I~//~v@@@R~
        return success;                                            //~1511I~//~v@@@R~
    }                                                              //~1511I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
////*rename cfg on sdcard after copyed to data/data                  //~1423I~//~v@@@R~
////*retur success/false                                             //~1423I~//~v@@@R~
////********************************************************         //~1423I~//~v@@@R~
//    public static boolean renameFile(String Pfname)                //~1423R~//~v@@@R~
//    {                                                              //~1423I~//~v@@@R~
//        boolean success=true;                                      //~1423I~                                            //~1423I~//~v@@@R~
//    //*********************                                        //~1423I~//~v@@@R~
//        if (Dump.Y) Dump.println("renameFile="+Pfname);            //~1506R~//~v@@@R~
//        File oldfile=new File(Pfname);                             //~1423R~//~v@@@R~
//        if (!oldfile.exists())  //file on /data/data               //~1423I~//~v@@@R~
//            return false;                                          //~1423I~//~v@@@R~
//        String ts=Utils.getTimeStamp(Utils.TS_DATE_TIME);            //~1425R~//~@@@@R~//~v@@@R~
//        String newname=Pfname+"."+SDFILE_PREFIX+"."+ts;            //~1423R~//~v@@@R~
//        File newfile=new File(newname);                            //~1423I~//~v@@@R~
//        success=oldfile.renameTo(newfile);                              //~1423I~//~v@@@R~
//        return success;                                            //~1423I~//~v@@@R~
//    }                                                              //~1423I~//~v@@@R~
////*******************************************                      //~1423R~//~v@@@R~
//    public static FileInputStream openInputData(String Pfname)        //~1309I~//~1312R~//~1327R~//~v@@@R~
//    {                                                              //~1309I~//~v@@@R~
//        FileInputStream in=null;                                       //~1309I~//~1327R~//~v@@@R~
//        if (Dump.Y) Dump.println("openInputStream:"+Pfname);       //~1506R~//~v@@@R~
//        try                                                        //~1309I~//~v@@@R~
//        {                                                          //~1309I~//~v@@@R~
//            in=AG.context.openFileInput(Pfname);                   //~1309I~//~v@@@R~
//            File f=new File(Pfname);                               //~1309I~//~v@@@R~
//            if (Dump.Y) Dump.println("OpenInputData:file="+Pfname+",fullpath="+f.getAbsolutePath());//~1309I~//~1506R~//~v@@@R~
//            if (Dump.Y) Dump.println("OpenInputData:file="+Pfname+",fullpathname="+f.getAbsoluteFile());//~1309I~//~1506R~//~v@@@R~
//            if (Dump.Y) Dump.println("Test isiexst by File()="+f.exists());             //~1309I~//~1506R~//~v@@@R~
//            if (Dump.Y) Dump.println("Test tiimestamp="+Long.toHexString(f.lastModified()));//~1309I~//~1506R~//~v@@@R~
//            f=Environment.getDataDirectory();                      //~1309I~//~v@@@R~
//            if (Dump.Y) Dump.println("DataDir Path="+f.getAbsolutePath());//~1309I~//~1506R~//~v@@@R~
//            f=new File(AG.context.getFilesDir(),Pfname);    //android.app.contextImple//~1309I~//~v@@@R~
//            if (Dump.Y) Dump.println(f.toString()+":isiexst="+f.exists());//~1309I~//~1506R~//~v@@@R~
//            if (Dump.Y) Dump.println("tiimestamp="+Long.toHexString(f.lastModified()));//~1309I~//~1506R~//~v@@@R~
//        }                                                          //~1309I~//~v@@@R~
//        catch(FileNotFoundException e)                             //~1309I~//~v@@@R~
//        {                                                          //~1309I~//~v@@@R~
////          Dump.println(e,"openInputData failed "+Pfname);  //~1309I~//~1312R~//~1329R~//~v107R~//~v@@@R~
//            Dump.println("openInputData NotFound:"+Pfname);        //~v107I~//~v@@@R~
//        }                                                          //~1309I~//~v@@@R~
//        catch (Exception e)                                        //~1309I~//~v@@@R~
//        {                                                          //~1309I~//~v@@@R~
//            Dump.println(e,"openInputData exception "+Pfname);              //~1309I~//~1312R~//~1329R~//~v@@@R~
//        }//catch                                                   //~1309I~//~v@@@R~
//        return in;                                                 //~1309I~//~v@@@R~
//    }                                                              //~1309I~//~v@@@R~
//*********************************************                                                       //~1309I~//~1329R~//~v@@@R~//~van1R~
//*output to SD if available else private *****                    //~1329I~//~v@@@R~//~van1R~
//*********************************************                    //~1329I~//~v@@@R~//~van1R~
    public static FileOutputStream openOutputSD(String Pdir,String Pfname)//~1313I~//~v@@@R~
    {                                                              //~1313I~//~v@@@R~
        String fnm,path;                                           //~1313I~//~v@@@R~
        if (Dump.Y) Dump.println("UFile.openOutputSD swScoped="+AG.swScoped+",dir="+Pdir+",file="+Pfname);//~1313I~//~1506R~//~v@@@R~//~1ak1R~//~vae0R~
      if (AG.swScoped) //android11 api30                           //~1ak1I~
      {                                                            //~1ak1I~
        if (Dump.Y) Dump.println("Ufile.openOutputSD@@@@ not avilable for Android 11,use UScoped.openOutputSD");//~1ak1I~//~vae0R~
        path=null;                                                 //~1ak1I~
      }                                                            //~1ak1I~
      else                                                         //~1ak1I~
        path=getSDPath(Pdir);                                      //~1313R~//~v@@@R~
        if (path==null) //no SDCard available                          //~1313I~//~v@@@R~
            return openOutputData(Pfname);    //to /data/data/<pkg>/files                     //~1313I~//~v@@@R~
        fnm=path+AG.dirSep+Pfname;      //~1313R~                  //~v@@@R~
        if (Dump.Y) Dump.println("UFile.openoutputDataSD on SDcard fnm="+fnm);                       //~1313I~//~1506R~//~v@@@R~//~vae0R~
        FileOutputStream out=null;                                 //~v@@@R~
        try {                                                      //~v@@@R~
            out = new FileOutputStream(fnm);                       //~v@@@R~
        } catch (Exception e)                                      //~1329R~//~v@@@R~
        {                                                          //~v@@@R~
            Dump.println(e,"openOutputSD:"+fnm);       //~1329R~   //~v@@@R~
        }                //~1313I~                                 //~v@@@R~
        return out;                                                //~1313I~//~v@@@R~
    }                                                              //~1313I~//~v@@@R~
////*********************************************                    //~1511I~//~v@@@R~
////*get private data file path                                      //~1511I~//~v@@@R~
////*********************************************                    //~1511I~//~v@@@R~
//    public static String getDataFileFullpath(String Pfname)        //~1511I~//~v@@@R~
//    {                                                              //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("getDataFileFullpath:"+Pfname);   //~1511I~//~v@@@R~
//        String path=AG.context.getFilesDir()+"/"+Pfname;                      //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("getDataFileFullpath:"+path);     //~1511I~//~v@@@R~
//        return path;                                               //~1511I~//~v@@@R~
//    }                                                              //~1511I~//~v@@@R~
////*********************************************                    //~1511I~//~v@@@R~
////*get private data file size                                      //~1511I~//~v@@@R~
////*********************************************                    //~1511I~//~v@@@R~
//    public static long getDataFileSize(String Pfname)              //~1511I~//~v@@@R~
//    {                                                              //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("getDataFileSize:"+Pfname);       //~1511I~//~v@@@R~
//        String path=AG.context.getFilesDir()+"/"+Pfname;                      //~1511I~//~v@@@R~
//        File f=new File(path);                                     //~1511I~//~v@@@R~
//        long sz;                                                   //~1511I~//~v@@@R~
//        if (f.exists())                                            //~1511I~//~v@@@R~
//            sz=f.length();                                         //~1511I~//~v@@@R~
//        else                                                       //~1511I~//~v@@@R~
//            sz=-1;                                                 //~1511I~//~v@@@R~
//        if (Dump.Y) Dump.println("getDataFileSize:"+path+",sz="+sz);//~1511I~//~v@@@R~
//        return sz;                                                 //~1511I~//~v@@@R~
//    }                                                              //~1511I~//~v@@@R~
//************************************************************************                                                       //~1401I~//~v@@@R~
    public static InputStream openInputSD(String Pfname,boolean Pshowexception)           //~1327I~//~v@@@R~
    {                                                              //~1327I~//~v@@@R~
        String fnm;                                           //~1327I~//~v@@@R~
        if (Dump.Y) Dump.println("UFile.openInputSD swScoped="+AG.swScoped+",fnm="+Pfname);         //~1506R~//~v@@@R~//~1ak1R~//~vae0R~
        if (AG.swScoped) //android11 api30                         //~1ak1I~
        {                                                          //~1ak1I~
        	return UScoped.openInputSD(Pfname);                    //~1ak1I~
        }                                                          //~1ak1I~
        fnm=getSDPath(Pfname);                                      //~1327I~//~v@@@R~
        if (fnm==null)  //no SDCard available                      //~1327I~//~v@@@R~
            return null;                                           //~1327I~//~v@@@R~
                                        //~1327I~                  //~v@@@R~
        if (Dump.Y) Dump.println("UFile.openInputSD fnm="+fnm);                       //~1327I~//~1506R~//~v@@@R~//~vae0R~
        FileInputStream is=null;                                   //~1327I~//~v@@@R~
        try                                                        //~1327I~//~v@@@R~
        {                                                          //~1327I~//~v@@@R~
            is=new FileInputStream(fnm);                           //~1327I~//~v@@@R~
        }                                                          //~1327I~//~v@@@R~
        catch (Exception e)                            //~1327I~   //~v@@@R~
        {                                                          //~1327I~//~v@@@R~
            if (Dump.Y) Dump.println("UFile.openInputSD:"+fnm);//~v107I~ //~v@@@I~//~vae0R~
        	if (Pshowexception)                                    //~v@@@I~
	            Dump.println(e,"openInputSD:"+fnm);                //~v@@@R~
        }                                                          //~1327I~//~v@@@R~
        return (InputStream)is;                                    //~1327I~//~v@@@R~
    }                                                              //~1327I~//~v@@@R~
//************************************************************************//~v@@@I~
//* file in data/data/<pkg>/files                                  //~v@@@I~
//************************************************************************//~v@@@I~
//    public static InputStream openInputOnFiles(String Pfname,boolean Pshowexception)//~v@@@R~
//    {                                                            //~v@@@R~
//        String fnm=getFilesPath(Pfname);                         //~v@@@R~
//        if (Dump.Y) Dump.println("openInputOnFiles fnm="+fnm);   //~v@@@R~
//        FileInputStream is=null;                                 //~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            is=new FileInputStream(fnm);                         //~v@@@R~
//        }                                                        //~v@@@R~
//        catch (Exception e)                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            if (Dump.Y) Dump.println("openInputOnFiles:"+fnm);   //~v@@@R~
//            if (Pshowexception)                                  //~v@@@R~
//                Dump.println(e,"openInputOnFiles:"+fnm);         //~v@@@R~
//        }                                                        //~v@@@R~
//        return (InputStream)is;                                  //~v@@@R~
//    }                                                            //~v@@@R~
//*********************************************                    //~1329I~
//*output to private data/data        *********                    //~1329I~//~1401R~
//*********************************************                    //~1329I~
//***********************************                              //~1401I~
//*from FileOutputStream                                           //~v@@@R~
//***********************************                              //~1401I~
//    public static FileOutputStream openOutputDataCfg(String Pfname)//~1401I~//~v@@@R~
//    {                                                              //~1401I~//~v@@@R~
//        if (Dump.Y) Dump.println("open Output private file from FileOutputStream="+Pfname);//~1506R~//~v@@@R~
//        String home=AG.home();                                 //~1401I~//~v@@@R~
//        String filename=DATAFILE_PREFIX+Pfname.substring(home.length());//~1401I~//~v@@@R~
//        return openOutputData(filename);                           //~1401I~//~v@@@R~
//    }                                                              //~1401I~//~v@@@R~
//*****************************************************************//~1401I~//~v@@@R~
//*output in /data/data/<pkg>/files                                //~v@@@I~
//*****************************************************************//~v@@@I~
    public static FileOutputStream openOutputData(String Pfname)      //~1309I~//~v@@@R~
    {                                                          //~1309I~//~v@@@R~
	    return openOutputData(Pfname,Context.MODE_PRIVATE);        //~v@@@R~
    }                                                              //~1309I~
//***********************************                              //~v@@@I~
    public static FileOutputStream openOutputData(String Pfname,int Pmode)//~v@@@R~
    {                                                              //~v@@@I~
        String member=getMemberName(Pfname);                       //~vae8R~
    	if (Dump.Y) Dump.println("UFile.openOutputData file="+Pfname+",mode="+Pmode+",member="+member);//~v@@@R~//~vae8R~
	    FileOutputStream out=null;	//FileOutputStream extend OutputStream//~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
//          out=AG.context.openFileOutput(Pfname,Pmode);           //~v@@@I~//~vae8R~
            out=AG.context.openFileOutput(member,Pmode);           //~vae8I~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"UFile.openOutputData:"+Pfname+",mode="+Pmode+",member="+member);//~v@@@R~//~vae8R~
        }//catch                                                   //~v@@@I~
    	return out;                                                //~v@@@I~
    }                                                              //~v@@@I~
//**********                                                       //~1312I~
    public static boolean writeOutputFiles(String Pfname,byte[] Pbytedata)//~1511R~//~v@@@R~
    {                                                              //~1312I~
    	boolean rc=false;                                          //~1511I~
    	if (Dump.Y) Dump.println("UFile.writeOutputFiles file="+Pfname);//~1506R~//~v@@@R~//~vae0R~
		FileOutputStream os=openOutputData(Pfname);                    //~1312I~//~v@@@R~
        if (os==null)                                              //~1312I~
        	return rc;                                             //~1511R~
        try                                                        //~1312I~
        {                                                          //~1312I~
            os.write(Pbytedata,0,Pbytedata.length);              //~1312I~
            os.close();                                            //~1312I~
            rc=true;                                               //~1511I~
        }                                                          //~1312I~
        catch (Exception e)                                        //~1312I~
        {                                                          //~1312I~
            Dump.println(e,"writeOutputFiles:"+Pfname);      //~1312I~//~v@@@R~
        }//catch                                                   //~1312I~
        return rc;                                                 //~1511I~
    }                                                              //~1312I~
//***********************************                              //~1ak1I~//~vad5I~
    public static FileOutputStream openOutputDataCacheDir(String Pfname)//~1ak1R~//~vad5I~
    {                                                              //~1ak1I~//~vad5I~
    	if (Dump.Y) Dump.println("UFile.openOutputDataCacheDir file="+Pfname);//~1ak1R~//~vad5I~
    	File fd;                                                   //~1ak1I~//~vad5I~
    	fd=AG.context.getCacheDir();                               //~1ak1R~//~vad5I~
    	if (Dump.Y) Dump.println("UFile.openOutputDataCacheDir cacheDir="+fd.getAbsolutePath()+",file="+Pfname);//~1ak1I~//~vad5I~
	    FileOutputStream out=null;	//FileOutputStream extend OutputStream//~1ak1I~//~vad5I~
        try                                                        //~1ak1I~//~vad5I~
        {                                                          //~1ak1I~//~vad5I~
        	File f= new File(fd,Pfname);                          //~1ak1I~//~vad5I~
            out=new FileOutputStream(f);                           //~1ak1I~//~vad5I~
        }                                                          //~1ak1I~//~vad5I~
        catch (Exception e)                                        //~1ak1I~//~vad5I~
        {                                                          //~1ak1I~//~vad5I~
            Dump.println(e,"UFile.openOutputDataCacheDir "+fd.getAbsolutePath()+"/"+Pfname);//~1ak1I~//~vad5I~
        }//catch                                                   //~1ak1I~//~vad5I~
    	return out;                                                //~1ak1I~//~vad5I~
    }                                                              //~1ak1I~//~vad5I~
//*******************************                                  //~1313I~
//*SD card                      *                                  //~1313I~
//*Manifest setting                                                //~1313I~
//* <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>//~1313I~
//*******************************                                  //~1313I~
    public static boolean isSDMounted()                            //~1313I~
    {                                                              //~1313I~
        if (Dump.Y) Dump.println("UFile.isSDMounted swLegacy="+swLegacy+",swScoped="+AG.swScoped);//~vae7I~
//  	return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);//~1313I~//~1ak1R~
        boolean rc;                                                //~1ak1I~
//      if (AG.swScoped) //android11 api30                         //~1ak1I~//~vae7R~
        if (!swLegacy && AG.swScoped) //android11 api30            //~vae7I~
        	rc=UScoped.isSDMounted();                              //~1ak1I~
        else                                                       //~1ak1I~
    		rc=Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);//~1ak1I~
        if (Dump.Y) Dump.println("UFile.isSDMounted rc="+rc);      //~1ak1I~
        return rc;                                                 //~1ak1I~
    }                                                              //~1313I~
//*******************************                                  //~v@@@I~//~va40R~//~va42R~
	@SuppressWarnings("deprecation")                               //~va42I~
    @TargetApi(19)     //KitKat                                    //~v@@@I~//~va40R~//~va42R~
    private static String getPublicPath19()                         //~v@@@I~//~va40R~//~va42R~
    {                                                              //~v@@@I~//~va40R~//~va42R~
        String path=Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS).getPath();//~v@@@I~//~va40R~//~va42R~
        if (Dump.Y) Dump.println("UFile.getPublicPath19 ="+path);  //~v@@@I~//~va40R~//~va42R~
        return path;                                               //~v@@@I~//~va40R~//~va42R~
    }                                                              //~v@@@I~//~va40R~//~va42R~
	@SuppressWarnings("deprecation")                               //~va42I~
    private static String getPublicPath()                           //~v@@@I~//~va40R~//~va42R~
    {                                                              //~v@@@I~//~va40R~//~va42R~
        String path=Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM).getPath();//traditional picture and video//~v@@@I~//~va40R~//~va42R~
        if (Dump.Y) Dump.println("UFile.getPublicPath ="+path);    //~v@@@I~//~va40R~//~va42R~
        return path;                                               //~v@@@I~//~va40R~//~va42R~
    }                                                              //~v@@@I~//~va40R~//~va42R~
	@SuppressWarnings("deprecation")                               //~va42I~
    private static String getExternalPath()                        //~va42I~
    {                                                              //~va42I~
        String path=Environment.getExternalStorageDirectory().getPath();//~va42I~
        if (Dump.Y) Dump.println("UFile.getExternbalPath="+path);  //~va42I~
        return path;                                               //~va42I~
    }                                                              //~va42I~
//**************************************************************** //~v@@@I~
//  public static String[] getSDPaths()                            //~v@@@I~//~vae0R~
    private static String[] getSDPaths()                           //~vae0I~
    {                                                              //~v@@@I~
        String path;                                               //~v@@@I~
    	List<String> list=new ArrayList<>();                       //~v@@@I~
//        path=Environment.getExternalStorageDirectory().getPath();  //~v@@@I~//~va40R~
//        list.add(path);                                            //~v@@@I~//~va40R~
//        if (AG.osVersion>=19) // Kitkat android 4.4                //~v@@@I~//~va40R~
//            path=getPublicPath19();                                //~v@@@I~//~va40R~
//        else                                                       //~v@@@I~//~va40R~
//            path=getPublicPath();                                  //~v@@@I~//~va40R~
//        list.add(path);                                            //~v@@@I~//~va40R~
        list.add("/sdcard");                                       //~v@@@I~
        if (AG.osVersion<=29) //allow on 29:android10 by manifest: application-->requestLagacyExternalStorage="true" (ignored when target=androd11)//~va42I~
        {                                                          //~va42I~
        	path=getExternalPath();                                 //~va42R~
        	list.add(path);                                        //~va42R~
	        if (Dump.Y) Dump.println("UFile.getSDPaths getExternalStorageDirectory="+path);//~va42I~
        }                                                          //~va42I~
        if (AG.osVersion>=19) // Kitkat android 4.4                //~va42M~
            path=getPublicPath19();                                //~va42M~
        else                                                       //~va42M~
            path=getPublicPath();                                  //~va42M~
        if (Dump.Y) Dump.println("UFile.getSDPaths getExternalStoragePublicDirectory="+path);//~va42M~
        list.add(path);                                            //~va42M~
                                                                   //~v@@@I~
        String[] ar=new String[list.size()];                       //~v@@@I~
        list.toArray(ar);                                          //~v@@@I~
        if (Dump.Y) Dump.println("UFile.getSDPaths ="+ Arrays.toString(ar));//~v@@@I~
		return ar;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**************************************************************** //~v@@@I~
    public static String getSDPath(String Pfile)                   //~1313R~
    {                                                              //~1313I~
		String path;                                               //~1313I~
    //************                                                 //~1313I~
        if (Dump.Y) Dump.println("UFile.getSDPath swLegacy="+swLegacy+",swScoped="+AG.swScoped+",swSDAvailable="+AG.swSDAvailable+",fnm="+Pfile);//~1ak1I~//~vae0R~//~vae7R~
      if (!swLegacy)                                               //~vae7I~
      {                                                            //~vae7I~
        if (AG.swScoped) //android11 api30                         //~1ak1I~
        {                                                          //~1ak1I~
        	return UScoped.getSDPath(Pfile);                       //~1ak1I~
        }                                                          //~1ak1I~
      }                                                            //~vae7I~
        if (!AG.swSDAvailable)                                     //~1313I~//~v@@@R~
        	return null;                                           //~1313I~
        if (Dump.Y) Dump.println("UFile.getSDPath AG.dirSD="+AG.dirSD);//~vae0I~
        path=AG.dirSD;                                           //~1313I~//~v@@@R~
        if (path==null)                                            //~1313I~
        {                                                          //~1313I~
            if (!isSDMounted())                                    //~1313I~
            {                                                      //~1313I~
                AG.swSDAvailable=false;                            //~1313I~//~v@@@R~
                return null;                                       //~1313I~
            }                                                      //~1313I~
//  		String approot=AG.appName;//~1323I~                    //~1402R~//~v@@@R~
    		String approot=AG.appNameE;                            //~v@@@I~
//      	path=Environment.getExternalStorageDirectory().getPath()+System.getProperty("file.separator")+approot;//~1323R~//~v@@@R~
            String[] paths=getSDPaths();                           //~v@@@I~
            boolean swOK=false;                                    //~v@@@R~
          for (int ii=0;ii<paths.length;ii++)                      //~v@@@I~
          {                                                        //~v@@@I~
//      	path=Environment.getExternalStorageDirectory().getPath()+System.getProperty("file.separator")+approot;//~v@@@I~
        	path=paths[ii]+System.getProperty("file.separator")+approot;//~v@@@I~
        	if (Dump.Y) Dump.println("UFile.getSDPath path="+path);//~v@@@I~
            File f=new File(path);
        	if (!f.exists())	                                   //~1313I~
            {                                                      //~1313I~
	        	if (Dump.Y) Dump.println("UFile.getSDPath not Exist="+path);//~v@@@I~
            	if (!f.mkdir())                                 //~1313I~
                {                                                  //~1313I~
//  	        	AG.swSDAvailable=false;                        //~1313I~//~v@@@R~
			        if (Dump.Y) Dump.println("UFile.getSDpath mkdir failed:"+path);//~1506R~//~vae0R~
//      			return null;                                   //~1313I~//~v@@@R~
                    continue;                                      //~v@@@I~
                }                                                  //~1313I~
			    if (Dump.Y) Dump.println("UFile.getSDpath mkdir OK:"+path);//~va42I~//~vae0R~
	            swOK=true;                                         //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~1313I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
	        	if (Dump.Y) Dump.println("UFile.getSDPath Exist="+path);//~v@@@I~
	            swOK=true;                                         //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
          }                                                        //~v@@@I~
            if (!swOK)                                             //~v@@@I~
            {                                                      //~v@@@I~
				AG.swSDAvailable=false;                            //~v@@@I~
				return null;                                       //~v@@@I~
            }                                                      //~v@@@I~
        	AG.dirSD=path;                                       //~1313I~//~v@@@R~
            if (Dump.Y) Dump.println("UFile.getSDpath SDcard dir="+path);                       //~1313I~//~1506R~//~vae0R~
		}                                                          //~1313I~
        if (!Pfile.equals(""))                                     //~1313I~
        	if (Pfile.startsWith(AG.dirSep))                            //~1412I~//~v@@@R~
        		path+=Pfile;                                       //~1412I~
            else                                                   //~1412I~
        		path+=AG.dirSep+Pfile;                                   //~1412R~//~v@@@R~
        if (Dump.Y) Dump.println("UFile.GetSDpath:"+path);               //~1506R~//~va42R~
        return path;                                               //~1313R~
    }                                                              //~1313I~
//**********************************************************************//~1402I~
//*mkdir                                                           //~v@@@R~
//**********************************************************************//~1402I~
    public static boolean makePath(String Ppath)                   //~1402I~
    {                                                              //~1402I~
        File f;                                                    //~1402I~
        boolean rc;                                                //~1402I~
    //**********************                                       //~1402I~
    	if (Dump.Y) Dump.println("UFile.makePath:"+Ppath);               //~1506R~//~vae0R~
        f=new File(Ppath);                                             //~1402I~
        if (f.exists())                                            //~1402I~
        	if (f.isDirectory())                                   //~1402I~
        		rc=true;                                           //~1402R~
            else                                                   //~1402I~
            	rc=false;                                          //~1402R~
		else                                                       //~1402I~
        {                                                          //~1402I~
        	f.mkdirs();	//create also parent path                  //~1402R~
            rc=true;                                               //~1402I~
        }                                                          //~1402I~
    	if (Dump.Y) Dump.println("UFile.makePath:rc"+rc);                //~1506R~//~vae0R~
        return rc;                                                 //~1402R~
    }                                                              //~1402I~
//**********************************************************************//~v@@@I~
//*makePathName                                                    //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String makePathName(String Pfullpath,String Ptgtdir)//~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("UFile.makePathName fullpath="+Pfullpath+",tgtdir="+Ptgtdir);//~v@@@I~//~vae0R~
        File f=new File(Pfullpath);                                //~v@@@I~
        String fnm=f.getName();                                    //~v@@@I~
        fnm=Ptgtdir+AG.dirSep+fnm;                                 //~v@@@I~
    	if (Dump.Y) Dump.println("UFile.makePathName fullpath="+Pfullpath+",tgtdir="+Ptgtdir+",out="+fnm);//~v@@@I~//~vae0R~
        return fnm;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
	public static String makeFullpath(String Ppath,String Pfile,String Pext)//~v@@@I~
    {                                                              //~v@@@I~
    	String path=Ppath+AG.dirSep+Pfile+Pext;                   //~v@@@I~
	    if (Dump.Y) Dump.println("UFile.makeFullpath="+path+",parmDir="+Ppath+",file="+Pfile+",ext="+(Pext==null?"null":Pext));//~v@@@I~//~vae0R~
    	return path;                                               //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*return path:/data/data/<pkg>/files                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String getDataDirPath(String Pfilename)          //~v@@@R~
    {                                                              //~v@@@I~
        String path=null;                                          //~v@@@R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	path=AG.context.getFilesDir().getAbsolutePath();       //~v@@@I~
	        if (Pfilename!=null)                                   //~v@@@I~
    	    	path+=AG.dirSep+Pfilename;                         //~v@@@R~
	    	if (Dump.Y) Dump.println("UFile.getFilesPath:"+path);        //~v@@@I~//~vae0R~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println("UFile.getFilesPath"+Pfilename);                   //~v@@@I~//~vae0R~
        }//catch                                                   //~v@@@I~
        return path;                                               //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*chk file in path:/data/data/<pkg>/files                         //~v@@@I~
//**********************************************************************//~v@@@I~
    public static boolean isExistInFiles(String Pfilename)         //~v@@@R~
    {                                                              //~v@@@I~
    	boolean rc=false;                                           //~v@@@I~
        String member=getMemberName(Pfilename);                    //~vae8R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
//      	FileInputStream fis=AG.context.openFileInput(Pfilename);//~v@@@I~//~vae8R~
        	FileInputStream fis=AG.context.openFileInput(member);  //~vae8I~
            fis.close();
            rc=true;//~v@@@I~
        }                                                          //~v@@@I~
        catch (FileNotFoundException e)                            //~v@@@I~
        {                                                          //~v@@@I~
        }//catch
        catch (Exception e)
        {
            Dump.println(e,"isExistInDataDir:"+Pfilename+",member="+member);// ~v@@@I~//~vae8R~
        }
        if (Dump.Y) Dump.println("UFile.isExistInDataDir:"+Pfilename+"="+rc+",member="+member);//~v@@@I~//~vae0R~//~vae8R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*open file in path:/data/data/<pkg>/files                        //~v@@@R~
//**********************************************************************//~v@@@I~
    public static FileInputStream openInputData(String Pfilename)  //~v@@@R~
    {                                                              //~v@@@I~
        String member=getMemberName(Pfilename);                    //~vae8R~
        if (Dump.Y) Dump.println("UFile.openInputData:"+Pfilename+",member="+member);      //~v@@@R~//~vae0R~//~vae8R~
        FileInputStream fis=null;                                  //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
//      	fis=AG.context.openFileInput(Pfilename);               //~v@@@I~//~vae8R~
        	fis=AG.context.openFileInput(member);                  //~vae8I~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"openInputData:"+Pfilename+",member="+member);            //~v@@@R~//~vae8R~
        }//catch                                                   //~v@@@I~
        return fis;                                                //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*get lastmodified                                                //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String getLastModified(File Pfile,String Pfmt)   //~v@@@R~
    {                                                              //~v@@@I~
	    long t=0;                                                  //~v@@@I~
        String ts="Unknown";                                       //~v@@@R~
        if (Dump.Y) Dump.println("UFile.getLastModified file="+Pfile.getAbsolutePath());//~v@@@I~//~vae0R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
	        t=Pfile.lastModified();                                //~v@@@R~
            ts=Utils.getTimeStamp(Pfmt,t);                         //~v@@@R~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"UFile.getLastModified:"+Pfile.getAbsolutePath());//~v@@@R~
        }//catch                                                   //~v@@@I~
        return ts;                                                 //~v@@@R~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*get lastmodified                                                //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String getBaseName(String Pfpath)                //~v@@@I~
    {                                                              //~v@@@I~
    	String bn;                                                 //~v@@@I~
    	String nm=new File(Pfpath).getName();                      //~v@@@I~
        int pos=nm.lastIndexOf(".");                               //~v@@@I~
        if (pos<0)                                                 //~v@@@I~
        	bn=nm;                                                 //~v@@@I~
        else                                                       //~v@@@I~
        	bn=nm.substring(0,pos);                                //~v@@@I~
        if (Dump.Y) Dump.println("UFile.getBaseName path="+Pfpath+",basename="+bn);//~v@@@R~
	    return bn;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
//*textfile to string                                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static String fileToString(String Pfpath)               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFile.fileToString fpath="+Pfpath);//~v@@@R~
        StringBuffer sb=fileToStringBuffer(Pfpath);                //~v@@@I~
        String str=null;                                           //~v@@@I~
        if (sb!=null)                                              //~v@@@I~
        	str=sb.toString();                                     //~v@@@I~
        if (Dump.Y) Dump.println("UFile.fileToString fpath="+Pfpath+",out="+str);//~v@@@R~
        return str;                                                //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static StringBuffer fileToStringBuffer(String Pfpath)         //~v@@@I~
    {                                                              //~v@@@I~
        StringBuffer sb=new StringBuffer();                        //~v@@@R~
        if (Dump.Y) Dump.println("UFile.fileToStringBuffer fpath="+Pfpath);//~v@@@R~
      if (AG.swScoped) //android11 api30                           //~vaieI~
      {                                                            //~vaieI~
      	sb=AG.aUScoped.fileToStringBuffer(Pfpath);                  //~vaieI~
      }                                                            //~vaieI~
      else                                                         //~vaieI~
      {                                                            //~vaieI~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	BufferedReader r=new BufferedReader(new FileReader(Pfpath));//~v@@@I~
	        String line;                                           //~v@@@I~
            while((line=r.readLine())!=null)                       //~v@@@I~
            {                                                      //~v@@@I~
            	sb.append(line);                                   //~v@@@I~
            	sb.append("\n");                                   //~v@@@I~
            }                                                      //~v@@@I~
            r.close();                                             //~v@@@I~
        }                                                          //~v@@@I~
        catch(Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
	        Dump.println(e,"UFile.fileToString fnm="+Pfpath);      //~v@@@R~
            sb=null;                                               //~v@@@I~
        }                                                          //~v@@@I~
      }                                                            //~vaieI~
        if (Dump.Y) Dump.println("UFile.fileToStringBuffer sb="+sb.toString());//~v@@@R~
        return sb;                                                 //~v@@@R~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static boolean setLastModified(String Pfpath,Long PtimeMillis)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFile.setLastModified fpath="+Pfpath+",time="+Long.toHexString(PtimeMillis));//~v@@@R~
        boolean rc;                                                //~v@@@R~
        File fh=new File(Pfpath);                                  //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	rc=fh.setLastModified((PtimeMillis/1000)*1000);        //~v@@@R~
	        if (Dump.Y) Dump.println("UFile.setLastModified rc="+rc+",result="+Long.toHexString(fh.lastModified()));//~v@@@I~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
        	rc=false;                                              //~v@@@I~
	        Dump.println(e,"UFile.setLastModified fnm="+Pfpath);   //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UFile.setLastModified rc="+rc);  //~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~v@@@I~
    public static boolean chkWritableSD()                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFile.chkWritableSD swScoped="+AG.swScoped);            //~v@@@I~//~1ak1R~
//      if (UScoped.isScoped())                                    //~1ak1I~//~vae0R~
        int stat=UScoped.chkScoped();                              //~vae0I~
        if (stat>=0)	//api30                                    //~vae0I~
        {                                                          //~1ak2R~
//      	return UScoped.chkWritableSD();                        //~1ak1I~//~1ak2R~
//      	boolean rc2=UScoped.chkWritableSD();                   //~1ak2R~//~vae0R~
//          if (Dump.Y) Dump.println("UFile.chkWritableSD rc2="+rc2);//~1ak2R~//~vae0R~
//          return rc2; TODO test      //permission not required for scoped storage//~1ak2R~
        	UScoped.chkWritableSD();                               //~vae0I~
            return stat>0;     //0:grant request not yet completed //~vae0I~
        }                                                          //~1ak2R~
        if (!chkGrantedSD())                                       //~v@@@I~
        {                                                          //~vae0I~
	        AG.swSDAvailable=false;  //no writepermission          //~vae0I~
        	return false;                                          //~v@@@I~
        }                                                          //~vae0I~
        AG.swSDAvailable=true;                                     //~v@@@I~
	    String path=getSDPath("");                                 //~v@@@I~
        boolean rc=path!=null;                                     //~v@@@I~
        if (!rc)                                                   //~vae0I~
	        AG.swSDAvailable=false;                                //~vae0I~
        if (Dump.Y) Dump.println("UFile.chkWritableSD swSDAvailable="+AG.swSDAvailable+",rc="+rc+",path="+Utils.toString(path));//~v@@@I~//~vae0R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************************//~vae0I~
//*from Uscoped at granted scoped storage                          //~vae0I~
//**********************************************************************//~vae0I~
    public static boolean chkExternalStoragePermissionOnScoped()      //~vae0I~//~vae7R~
    {                                                              //~vae0I~
        if (Dump.Y) Dump.println("UFile.chkExternalStoragePermissionOnScoped swScoped="+AG.swScoped);//~vae0I~//~van1R~
        boolean rc=UView.isPermissionGrantedExternalStorageRead(); //~vae0I~
        AG.swGrantedExternalStorageRead=rc;                        //~vae0I~
        if (!rc)                                                   //~vae0I~
		    UView.requestPermissionExternalStorageRead(MainActivity.PERMISSION_EXTERNAL_STORAGE_READ);//~vae0I~
        return rc;                                                 //~vae7I~
    }                                                              //~vae0I~
//**********************************                               //~v@@@I~
    private static boolean chkGrantedSD()                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFile.chkGrantedSD");            //~v@@@I~
		boolean rc=UView.isPermissionGrantedExternalStorage();     //~v@@@I~
        boolean rc2=UView.isPermissionGrantedExternalStorageRead();//~1ak2I~
        AG.swGrantedExternalStorageWrite=rc;                       //~1ak2I~
        AG.swGrantedExternalStorageRead=rc2;                       //~1ak2I~
//      boolean swDenied=UView.isPermissionDeniedExternalStorage();//once denied request callback NG without popup dialog,so always issue request//~vae0R~
      	if (AG.osVersion>=30) //scoped storage	//no chk write permission for scoped storage//~1ak2I~
        {                                                          //~vae0I~
//            swDenied=UView.isPermissionDeniedExternalStorageRead();//~vae0I~
        	rc=rc2;                                                //~1ak2I~
        }                                                          //~vae0I~
//      else                                                       //~1ak2I~//~vae0R~
//    	if (AG.osVersion>=29) //scoped storage	//no chk write permission for scoped storage//~1ak2I~//~vae0R~
//      {                                                          //~vae0I~
//          swDenied=UView.isPermissionDeniedExternalStorageRead();//~vae0I~
//      	rc=rc2;	//chk read only                                //~1ak2I~//~vae0R~
//      }                                                          //~vae0I~
//      else            //Write permission means read permission   //~1ak2I~//~vae0R~
//      	rc=rc & rc2;                                           //~1ak2I~//~vae0R~
        if (!rc)                                                   //~v@@@I~
        {                                                          //~v@@@I~
          if (AG.osVersion>=30) //scoped storage	//no chk write permission for scoped storage//~vae0I~
          {                                                        //~vae0I~
//          if (swDenied)                                          //~vae0R~
//      	  	UView.showToastLong(R.string.ScopedExternalStorageDeniedRead);//~vae0R~
//          else                                                   //~vae0R~
		    	UView.requestPermissionExternalStorageRead(MainActivity.PERMISSION_EXTERNAL_STORAGE_READ);//~vae0I~
          }                                                        //~vae0I~
          else                                                     //~vae0I~
          {                                                        //~vae0I~
//          if (swDenied)                                          //~vae0R~
//      	  	UView.showToastLong(R.string.ScopedExternalStorageDeniedWrite);//~vae0R~
//          else                                                   //~vae0R~
		    UView.requestPermissionExternalStorage(MainActivity.PERMISSION_EXTERNAL_STORAGE);//~v@@@I~
          }                                                        //~vae0I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UFile.chkGrantedSD swGrantedExternalStorage=rc="+rc);//~1ak2I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//*************************************************************************//~v@@@I~
//* from Main.onRequestPermissionResult                            //~v@@@I~
//* for WRITE permission                                           //~vae0I~
//*************************************************************************//~v@@@I~
    public static void grantedExternalStorage(boolean PswGranted)  //~v@@@I~
    {                                                              //~v@@@I~
    	boolean rc;                                                //~v@@@I~
        if (Dump.Y) Dump.println("UFile.grantedExternalStorage PswGranted="+PswGranted);//~v@@@I~
        if (!PswGranted)                                           //~v@@@I~
        {                                                          //~v@@@I~
          	MainView.drawMsg(R.string.ExternalStorageForSDRequiresGranted);//~v@@@I~
            return;                                                //~v@@@I~
        }	                                                       //~v@@@I~
		UView.showToast(R.string.ExternalStorageForSDGranted);     //~v@@@I~
//  	chkWritableSD();                                           //~v@@@I~//~vae0R~
        AG.swSDAvailable=true;                                     //~vae0I~
    }                                                              //~v@@@I~
//    public static void grantedExternalStorage(boolean PswGranted,boolean PswGrantedRead)//~1ak2I~//~vae0R~
//    {                                                              //~1ak2I~//~vae0R~
//        boolean rc;                                                //~1ak2I~//~vae0R~
//        if (Dump.Y) Dump.println("UFile.grantedExternalStorage osVersion="+AG.osVersion+",PswGranted="+PswGranted+",PswGrantedRead="+PswGrantedRead);//~1ak2I~//~vae0R~
//        if (AG.osVersion>=30) //scoped storage                     //~1ak2I~//~vae0R~
//        {                                                          //~1ak2I~//~vae0R~
//            if (PswGrantedRead)                                    //~1ak2I~//~vae0R~
//            {                                                      //~1ak2I~//~vae0R~
//                UView.showToastLong(R.string.ExternalStorageForSDRequiresGrantedRead);//~1ak2I~//~vae0R~
//                return;                                            //~1ak2I~//~vae0R~
//            }                                                      //~1ak2I~//~vae0R~
//        }                                                          //~1ak2I~//~vae0R~
//        else                                                       //~1ak2I~//~vae0R~
//        {                                                          //~1ak2I~//~vae0R~
//            if (!PswGrantedRead)                                   //~1ak2I~//~vae0R~
//            {                                                      //~1ak2I~//~vae0R~
//                UView.showToastLong(R.string.ExternalStorageForSDRequiresGrantedRead);//~1ak2I~//~vae0R~
//                return;                                            //~1ak2I~//~vae0R~
//            }                                                      //~1ak2I~//~vae0R~
//            if (!PswGranted)                                       //~1ak2I~//~vae0R~
//            {                                                      //~1ak2I~//~vae0R~
//              if (AG.osVersion<29) //allow on 29:android10 by manifest: application-->requestLagacyExternalStorage="true" (ignored when target=androd11)//~1ak2I~//~vae0R~
//              {                                                    //~1ak2I~//~vae0R~
//                UView.showToastLong(R.string.ExternalStorageForSDRequiresGranted);//~1ak2I~//~vae0R~
//                return;                                            //~1ak2I~//~vae0R~
//              }                                                    //~1ak2I~//~vae0R~
//            }                                                      //~1ak2I~//~vae0R~
//        }                                                          //~1ak2I~//~vae0R~
//        UView.showToast(R.string.ExternalStorageForSDGranted);     //~1ak2I~//~vae0R~
//        chkWritableSD();                                           //~1ak2I~//~vae0R~
//    }                                                              //~1ak2I~//~vae0R~
//*************************************************************************//~vae0I~
//* from Main.onRequestPermissionResult                            //~vae0I~
//* for READ permission                                            //~vae0I~
//*************************************************************************//~vae0I~
    public static void grantedExternalStorageRead(boolean PswGranted)//~1ak2I~
    {                                                              //~1ak2I~
    	boolean rc;                                                //~1ak2I~
        if (Dump.Y) Dump.println("UFile.grantedExternalStorageRead PswGranted="+PswGranted);//~1ak2I~
        if (!PswGranted)                                           //~1ak2I~
        {                                                          //~1ak2I~
//        	UView.showToastLong(R.string.ExternalStorageForSDRequiresGranted);//~1ak2I~//~vae0R~
//         	UView.showToastLong(R.string.ExternalStorageReadRequiresGranted);//~vae0I~//+vayWR~
            return;                                                //~1ak2I~
        }                                                          //~1ak2I~
//  	UView.showToast(R.string.ExternalStorageForSDGranted);     //~1ak2I~//~vae0R~
//    	UView.showToast(R.string.ExternalStorageReadGranted);      //~vae0I~//+vayWR~
//  	chkWritableSD();                                           //~1ak2I~//~vae0R~
        AG.swGrantedExternalStorageRead=true;                      //~vae0I~
    }                                                              //~1ak2I~
//*************************************************************************//~vae7I~
    public static void transferSDToScoped()                        //~vae7I~
    {                                                              //~vae7I~
		boolean xfered=Utils.getPreference(PREFKEY_SD_XFER,false);   //~@@01I~//~vae7I~
        if (Dump.Y) Dump.println("UFile.transferSDToScoped preference SD_XFER="+xfered);//~vae7R~
        if (xfered)                                             //~vae7I~
        	return;                                                //~vae7I~
        boolean rc=true;                                           //~vae7M~
      try                                                          //~vae7I~
      {                                                            //~vae7I~
        String path=getSDPathLegacy();                             //~vae7I~
    	File[] filelist=listFiles(path);                           //~vae7I~
        if (filelist==null)                                        //~vae7I~
        	return;                                                //~vae7I~
//    if (true)   //TODO test                                      //~vae7R~
//    	rc&=transferToScoped(filelist[0]);                         //~vae7R~
//    else                                                         //~vae7R~
        int ctrOK=0;                                               //~vae7I~
        int ctrlist=0;                                             //~vae7I~
        for (File f:filelist)                                      //~vae7I~
        {                                                          //~vae7I~
        	if (Dump.Y) Dump.println("UFile.transferSDToScoped name="+f.getName());//~vae7I~
            boolean rc2=transferToScoped(f);                       //~vae7R~
            ctrlist++;                                             //~vae7I~
            if (rc2)                                               //~vae7I~
            	ctrOK++;                                           //~vae7I~
            rc&=rc2;                                               //~vae7I~
        }                                                          //~vae7I~
        UView.showToastLong(Utils.getStr(R.string.Info_Transfered,ctrOK,ctrlist));//~vae7I~
      }                                                            //~vae7I~
	  catch(Exception e)                                           //~vae7I~
	  {                                                            //~vae7I~
        Dump.println(e,"UFile.transferSDScoped");                  //~vae7I~
      }                                                            //~vae7I~
		Utils.putPreference(PREFKEY_SD_XFER,true); //only one even failed//~vae7R~
        if (Dump.Y) Dump.println("UFile.transferSDToScoped exit rc="+rc);//~vae7R~
    }                                                              //~vae7I~
//**************************************************************** //~vae7I~
    private static String getSDPathLegacy()                        //~vae7I~
    {                                                              //~vae7I~
    //************                                                 //~vae7I~
        if (Dump.Y) Dump.println("UFile.getSDPathLegacy");         //~vae7I~
        swLegacy=true;                                             //~vae7I~
    	String path=getSDPath("");                                  //~vae7I~
        swLegacy=false;                                            //~vae7I~
        if (Dump.Y) Dump.println("UFile.getSDPathLegacy path="+path);//~vae7I~
        return path;                                               //~vae7I~
    }                                                              //~vae7I~
//*************************************************************************//~vae7I~
//*if uninstalled at api30, you can not list the sdcard            //~vae7I~
//*************************************************************************//~vae7I~
    private static File[] listFiles(String Ppath)                  //~vae7I~
    {                                                              //~vae7I~
        if (Dump.Y) Dump.println("UFile.listFiles path="+Ppath);   //~vae7I~
        File dir=new File(Ppath);                                  //~vae7I~
		File[] filelist=null;                                      //~vae7I~
        try                                                        //~vae7I~
		{                                                          //~vae7I~
		    if (Dump.Y) Dump.println("UFile.listFiles isExist="+dir.exists()+",isDir="+dir.isDirectory()+",isFile="+dir.isFile()+",canonicalPath="+dir.getCanonicalPath());//~vae7R~
			filelist=dir.listFiles();                       //~vae7I~
        }                                                          //~vae7I~
        catch(SecurityException e)                                 //~vae7I~
        {                                                          //~vae7I~
        	Dump.println(e,"UFile.listFiles");                     //~vae7I~
        }                                                          //~vae7I~
		catch(Exception e)                                         //~vae7I~
		{                                                          //~vae7I~
        	Dump.println(e,"UFile.listFiles");                     //~vae7I~
        }                                                          //~vae7I~
        if(filelist==null||filelist.length==0)                     //~vae7I~
        {                                                          //~vae7I~
		    if (Dump.Y) Dump.println("UFile.listFiles list="+Utils.toString(filelist)+",length="+(filelist==null ? 0 : filelist.length));//~vae7R~
			return null;                                           //~vae7I~
        }                                                          //~vae7I~
		if (Dump.Y) Dump.println("UFile.listFiles ctr="+filelist.length);//~vae7I~
        return filelist;                                           //~vae7I~
    }                                                              //~vae7I~
//*************************************************************************//~vae7I~
    private static boolean transferToScoped(File Pfile)            //~vae7I~
    {                                                              //~vae7I~
    	boolean rc=false;                                          //~vae7I~
//      if (true)  //TODO test                                     //~vae7R~
//      {                                                          //~vae7R~
//  	    if (Dump.Y) Dump.println("UFile.trabsferToScoped return false for TEST");//~vae7R~
//          return false;                                          //~vae7R~
//      }                                                          //~vae7R~
		if (Dump.Y) Dump.println("UFile.transferToScoped file="+Pfile.getName()+",abs="+Pfile.getAbsolutePath());//~vae7I~
		if (Dump.Y) Dump.println("UFile.transferToScoped canRead="+Pfile.canRead()+",length="+Pfile.length());//~vae7I~
        int rc2=AG.aUScoped.writeDocumentFromFile(Pfile.getAbsolutePath());  //copy if not exist; exist means picker setected sdcard//~vae7R~
        if (Dump.Y) Dump.println("UFile.trabsferToScoped rc="+rc);
        if (rc2==0) //copyed                                       //~vae7R~
        {                                                          //~vae7I~
            try                                                    //~vae7I~
            {                                                      //~vae7I~
                Pfile.delete();                                    //~vae7R~
        		if (Dump.Y) Dump.println("UFile.trabsferToScoped deleted fpath="+Pfile.getName());//~vae7R~
            }                                                      //~vae7I~
            catch(Exception e)                                     //~vae7I~
            {                                                      //~vae7I~
                Dump.println(e,"UFile.transferToScoped delete Old="+Pfile.getName());//~vae7R~
            }                                                      //~vae7I~
            rc=true;                                               //~vae7I~
        }                                                          //~vae7I~
        else                                                       //~vae7I~
        if (rc2==1)//exist                                         //~vae7I~
            rc=true;                                               //~vae7I~
		if (Dump.Y) Dump.println("UFile.transferToScoped rc="+rc+",file="+Pfile.getName());//~vae7I~
        return rc;
    }                                                              //~vae7I~
//*************************************************************************//~vae8I~
    private static String getMemberName(String Ppath)              //~vae8I~
    {                                                              //~vae8I~
        int pos=Ppath.lastIndexOf("/");                            //~vae8I~
    	String member=Ppath.substring(pos+1);                      //~vae8I~
		if (Dump.Y) Dump.println("UFile.getMemberName path="+Ppath+",member="+member);//~vae8I~
        return member;                                             //~vae8I~
    }                                                              //~vae8I~
}//class                                                           //~1110I~
