//*CID://+vaaGR~:                             update#=  155;       //+vaaGR~
//*****************************************************************//~v101I~
//2021/07/13 vaaG set default BG/FG for HelpDlg                    //+vaaGI~
//2021/06/26 vaa0 support <img> in htmlText                        //~vaa0I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import android.annotation.TargetApi;
import android.graphics.Color;
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
import static android.text.Html.*;
import static com.btmtest.StaticVars.AG;

public class HelpDialog extends UFDlg                              //~v@@@R~
{                                                                  //~2C29R~
    public static final int HELP_BG_IMAGE=Color.argb(0xff,0xc0,0xd0,0xc0);//~vaa0I~
    public static final int HELP_FG_IMAGE=Color.argb(0xff,0x00,0x00,0x00);//~vaa0I~
    private static final int HELP_BG_IMAGE_DEFAULT=Color.argb(0xff,0xd0,0xe0,0xd0);//+vaaGI~
    private static final int HELP_FG_IMAGE_DEFAULT=Color.argb(0xff,0x00,0x00,0x00);//+vaaGI~
    private static final int TITLEID=R.string.Title_HistoryDlg;    //~v@@@R~
    private static final String DEFAULT_FILENAME="default.history";   //~v@@@R~
    private TextView tvMessage;                                    //~v@@@I~
//  private UFDlg ufdlg;                                           //~v@@@R~
    private String helpFilename;                                   //~v@@@I~
    private int colorBG,colorFG;                                   //~vaa0I~
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
        hdlg.colorBG=HELP_BG_IMAGE_DEFAULT;                        //+vaaGI~
        hdlg.colorFG=HELP_FG_IMAGE_DEFAULT;                        //+vaaGI~
    	return hdlg;                                               //~v@@@I~
     }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static HelpDialog newInstance(int Ptitleid,String Pfnm)//~v@@@I~
    {                                                              //~v@@@I~
    	return newInstance(Utils.getStr(Ptitleid),Pfnm);                  //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~vaa0I~
	public static HelpDialog newInstance(int Ptitleid,String Pfnm,int Pbg,int Pfg)//~vaa0I~
    {                                                              //~vaa0I~
    	if (Dump.Y) Dump.println("HelpDialog fnm="+Pfnm+",bg="+Integer.toHexString(Pbg)+",fg="+Integer.toHexString(Pfg));//~vaa0I~
    	HelpDialog dlg=newInstance(Utils.getStr(Ptitleid),Pfnm);   //~vaa0I~
        dlg.colorBG=Pbg;                                               //~vaa0I~
        dlg.colorFG=Pfg;                                               //~vaa0I~
        return dlg;                                                //~vaa0I~
    }                                                              //~vaa0I~
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
      if (colorBG!=colorFG)                                        //~vaa0I~
      {                                                            //~vaa0M~
        txt=htmltxt;                                               //~vaa0I~
        Spanned s=Utils.fromHtmlImage(txt);                        //~vaa0M~
        tvMessage.setBackgroundColor(colorBG);                     //~vaa0I~
        tvMessage.setTextColor(colorFG);                           //~vaa0I~
        tvMessage.setText(s);                                      //~vaa0M~
      }                                                            //~vaa0M~
      else                                                         //~vaa0M~
      {                                                            //~vaa0M~
        if (htmltxt!=null)                                         //~v@@@I~
        {                                                          //~v@@@I~
//      	txt=adjustHtml(htmltxt);                               //~v@@@R~
        	txt=htmltxt;                                           //~v@@@I~
//            Spanned s;                                           //~va40R~
//            if (AG.osVersion>=24) // Nougat:android 7.0          //~va40R~
//                s=getHtmlSpanned(txt);                           //~va40R~
//            else                                                 //~va40R~
//                s=Html.fromHtml(txt);                            //~va40R~
            Spanned s=Utils.fromHtml(txt);                         //~va40R~
            tvMessage.setText(s);                                  //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
		    txt=UFile.getHelpFileText(helpFilename);               //~v@@@R~
	    	tvMessage.setText(txt);                                //~v@@@R~
        }                                                          //~v@@@I~
      }                                                              //~v@@@M~//~vaa0R~
    }                                                              //~vaa0I~
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
////**********************************                             //~va40R~
//    @TargetApi(24) //android7 Nougat                             //~va40R~
//    public Spanned getHtmlSpanned(String Ptxt)                   //~va40R~
//    {                                                            //~va40R~
//        if (Dump.Y) Dump.println("HelpDialog getHtmlSpanned api>=24(Nogaut 7.0) string="+Ptxt);//~va40R~
////      int flag=TO_HTML_PARAGRAPH_LINES_INDIVIDUAL;             //~va40R~
//        int flag=FROM_HTML_MODE_LEGACY;                          //~va40R~
//        Spanned s=Html.fromHtml(Ptxt,flag);                      //~va40R~
//        return s;                                                //~va40R~
//    }                                                            //~va40R~
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
