//*CID://+v@@@R~:                             update#=  139;       //~1Af6R~//~v@@@R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;                                    //~v@@@I~
import android.view.View;                                          //~v@@@I~

import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;                                    //~v@@@I~

public class SpriteDlg extends UFDlg                               //~v@@@R~
{                                                                  //~2C29R~
    private TextView tvMessage;                                    //~v@@@R~
//  private UFDlg ufdlg;                                           //+v@@@R~
    private String text;                                           //~v@@@R~
//**********************************                               //~v@@@I~
	public SpriteDlg()                                             //~v@@@R~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static SpriteDlg newInstance(String Ptitle,String Ptext)//~v@@@R~
    {                                                              //~v@@@I~
    	SpriteDlg hdlg=new SpriteDlg();                            //~v@@@R~
//  	hdlg.ufdlg=UFDlg.newInstance(hdlg,Ptitle,R.layout.spritedlg,UFDlg.FLAG_CLOSEBTN,0/*helptitleid*/,null/*helpfile*/);//+v@@@R~
    	UFDlg.setBundle(hdlg,Ptitle,R.layout.spritedlg,UFDlg.FLAG_CLOSEBTN,0/*helptitleid*/,null/*helpfile*/);//+v@@@I~
        hdlg.text=Ptext;                                           //~v@@@R~
    	return hdlg;                                               //~v@@@I~
     }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static SpriteDlg newInstance(int Ptitleid,String Ptext) //~v@@@R~
    {                                                              //~v@@@I~
    	return newInstance(Utils.getStr(Ptitleid),Ptext);          //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
	@Override                                                      //~v@@@M~
    public void initLayout(View Playoutview)                       //~v@@@R~
	{                                                              //~v@@@M~
    	super.initLayout(Playoutview);                             //~v@@@I~
        tvMessage=(TextView)UView.findViewById(Playoutview,R.id.Message); //~v@@@I~
        tvMessage.setText(text);                                   //~v@@@I~
        if (Dump.Y) Dump.println("SpriteDlg.initLayout text="+text);//~v@@@R~
        Dialog dlg=getDialog();                                    //~v@@@I~
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//~v@@@I~
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);         //~v@@@I~
    }                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
	public static void show(String Ptitle,String Ptext)            //~v@@@R~
	{                                                              //~v@@@I~
        newInstance(Ptitle,Ptext).show();                          //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
	public static void show(int Ptitleid,String Ptext)             //~v@@@R~
	{                                                              //~v@@@I~
        newInstance(Ptitleid,Ptext).show();                        //~v@@@R~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
