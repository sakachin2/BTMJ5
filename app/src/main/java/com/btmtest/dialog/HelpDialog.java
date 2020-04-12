//*CID://+v@@@R~:                             update#=  147;       //~1Af6R~//~v@@@R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import android.annotation.TargetApi;
import android.os.Bundle;                                          //~v@@@I~
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;                                    //~v@@@I~
import android.view.ViewGroup;                                     //~v@@@I~
import android.view.LayoutInflater;                                //~v@@@I~
import android.view.View;                                          //~v@@@I~

import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UFile;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;                                    //~v@@@I~

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.Html.*;
import static com.btmtest.StaticVars.AG;

public class HelpDialog extends UFDlg                              //~v@@@R~
{                                                                  //~2C29R~
    private static final int TITLEID=R.string.Title_HistoryDlg;    //~v@@@R~
    private static final String DEFAULT_FILENAME="default.history";   //~v@@@R~
    private TextView tvMessage;                                    //~v@@@I~
//  private UFDlg ufdlg;                                           //~v@@@R~
    private String helpFilename;                                   //~v@@@I~
//**********************************                               //~v@@@I~
	public HelpDialog()                                              //~v@@@I~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static HelpDialog newInstance(String Ptitle,String Pfnm)//~v@@@I~
    {                                                              //~v@@@I~
    	HelpDialog hdlg=new HelpDialog();                          //~v@@@I~
//  	hdlg.ufdlg=UFDlg.newInstance(hdlg,Ptitle,R.layout.helpdialog,UFDlg.FLAG_CLOSEBTN,0/*helptitleid*/,null/*helpfile*/);//~v@@@R~
    	UFDlg.setBundle(hdlg,Ptitle,R.layout.helpdialog,UFDlg.FLAG_CLOSEBTN,0/*helptitleid*/,null/*helpfile*/);//~v@@@R~
        hdlg.helpFilename=Pfnm;                                    //~v@@@I~
    	return hdlg;                                               //~v@@@I~
     }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static HelpDialog newInstance(int Ptitleid,String Pfnm)//~v@@@I~
    {                                                              //~v@@@I~
    	return newInstance(Utils.getStr(Ptitleid),Pfnm);                  //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static HelpDialog newInstance()                         //~v@@@I~
    {                                                              //~v@@@I~
    	return newInstance(TITLEID,DEFAULT_FILENAME);              //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
	@Override                                                      //~v@@@M~
    public void initLayout(View Playoutview)                       //~v@@@R~
	{                                                              //~v@@@M~
		String txt,htmltxt;                                        //~v@@@I~
    //******************************                               //~v@@@I~
    	super.initLayout(Playoutview);                             //~v@@@I~
        tvMessage=(TextView)UView.findViewById(Playoutview,R.id.Message); //~v@@@I~
        htmltxt=UFile.getHelpFileExt(helpFilename,".htm",false);   //~v@@@R~
        if (htmltxt!=null)                                         //~v@@@I~
        {                                                          //~v@@@I~
//      	txt=adjustHtml(htmltxt);                               //+v@@@R~
        	txt=htmltxt;                                           //+v@@@I~
			Spanned s;                                             //~v@@@I~
        	if (AG.osVersion>=24) // Nougat:android 7.0            //~v@@@I~
			    s=getHtmlSpanned(txt);                             //~v@@@R~
            else                                                   //~v@@@I~
        		s=Html.fromHtml(txt);                              //~v@@@R~
            tvMessage.setText(s);                                  //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
		    txt=UFile.getHelpFileText(helpFilename);               //~v@@@R~
	    	tvMessage.setText(txt);                                //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@M~
//**********************************                               //~v@@@I~
    private String adjustHtml(String Ptxt)                                        //~v@@@I~
    {                                                              //~v@@@I~
        String txt;                                                //~v@@@I~
        txt=Ptxt.replaceAll(" ","&nbsp;");                      //~v@@@I~
//      Pattern p=Pattern.compile("([^>])(\n)");                   //~v@@@R~
  //    Pattern p=Pattern.compile("([^>])$",Pattern.MULTILINE);    //~v@@@R~
  //    Matcher m=p.matcher(txt);                                  //~v@@@R~
//      txt=m.replaceAll("$1<BR>$2");                              //~v@@@R~
  //    txt=m.replaceAll("$0<BR>");                                //~v@@@R~
//      txt=txt.replaceAll("^\n","<BR>\n");                        //~v@@@I~
    	if (Dump.Y) Dump.println("HelpDialog adjustHtml inp="+Ptxt+",out="+txt);//~v@@@I~
        return txt;
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	@TargetApi(24) //android7 Nougat                               //+v@@@R~
    public Spanned getHtmlSpanned(String Ptxt)                     //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("HelpDialog getHtmlSpanned api>=24(Nogaut 7.0) string="+Ptxt);//~v@@@I~
//      int flag=TO_HTML_PARAGRAPH_LINES_INDIVIDUAL;               //+v@@@R~
        int flag=FROM_HTML_MODE_LEGACY;                            //+v@@@I~
        Spanned s=Html.fromHtml(Ptxt,flag);                     //~v@@@I~
        return s;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
	public static void show(String Ptitle,String Pfnm)                  //~v@@@I~
	{                                                              //~v@@@I~
        newInstance(Ptitle,Pfnm).show();                           //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
	public static void show(int Ptitleid,String Pfnm)                   //~v@@@I~
	{                                                              //~v@@@I~
        newInstance(Ptitleid,Pfnm).show();                         //~v@@@I~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
