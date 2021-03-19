//*CID://+dateR~:                             update#=   16;       //~1108I~
//******************************************************************//~0B03I~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//******************************************************************//~0B03I~
//*test data:androidTest\assets\calshan_input1                     //~1108I~
//*tablefile:main/assets\calshan_bytetbl.zip                       //~1108I~
//*result Dump(adbhw3pulldump)                                     //~1108I~
//******************************************************************//~1108I~
package com.btmtest;
//******************************************************************//~0B01R~
import android.content.Context;
import android.content.res.Resources;

import androidx.test.platform.app.InstrumentationRegistry;               //~0A31R~
import com.btmtest.utils.Dump;                                     //~0A31M~
import com.btmtest.utils.UFile;
import static com.btmtest.StaticVars.AG;                           //~1109I~
                                                                   //~1109I~
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ITData                                          //~0B03R~//~1108R~//~1109R~
{                                                                  //~0A31I~
    static BufferedReader reader;                                         //~1109I~
    static String fileName;                                               //~1109I~
    static int ctrLine=0;                                             //~1108I~//~1109I~
//***********************************************                  //~1108I~
	public static boolean open(String Pfnm)    //~1108R~           //~1109R~
    {                                                              //~1108I~
        fileName=Pfnm;                                             //~1109I~
	    Resources resourceMain=AG.resource;                       //~1108I~
        Context testContext=InstrumentationRegistry.getInstrumentation().getContext();//~1108I~
        AG.resource=testContext.getResources();	//for IT..\asset                    //~1108I~//+1121R~
        InputStream is=UFile.openAssetFile(Pfnm,true);             //~1108I~//~1109I~
	    AG.resource=resourceMain;	//recover                      //+1121R~
        if (is==null)                                              //~1109I~
        {                                                          //~1109I~
        	if (Dump.Y) Dump.println("ITData.open failed file="+Pfnm);//~1109I~
            return false;                                          //~1109I~
        }                                                          //~1109I~
        InputStreamReader isr=new InputStreamReader(is);           //~1109I~
        reader=new BufferedReader(isr);              //~1108I~      //~1109I~
        ctrLine=0;                                                 //~1109I~
        return true;                                               //~1109R~
    }                                                              //~1108I~
//***********************************************                  //~1108I~
	public static String readLine()               //~1108I~        //~1109R~
    {                                                              //~1108I~
    	ctrLine++;                                                 //~1109I~
        String line;                                               //~1109I~
        try                                                        //~1108I~
		{                                                          //~1108I~
            line=reader.readLine();                         //~1108I~//~1109R~
            if (line==null)                                    //~1108I~//~1109R~
            {                                                      //~1109I~
	            reader.close();                                            //~1108I~//~1109I~
	            if (Dump.Y) Dump.println("ITData.readLine EOF total line="+ctrLine);                //~1108I~//~1109R~
            }                                                      //~1109I~
        }                                                          //~1108I~
        catch(Exception e)                                         //~1108I~
        {                                                          //~1108I~
        	if (Dump.Y) Dump.println("ITData.readLine failed file="+fileName+","+e.toString());//~1109R~
            line=null;                                             //~1109I~
        }                                                          //~1108I~
        return line;                                               //~1109I~
    }                                                              //~1108I~
}
