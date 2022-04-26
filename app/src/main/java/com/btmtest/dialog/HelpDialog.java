//*CID://+vamfR~:                             update#=  166;       //~vamfR~
//*****************************************************************//~v101I~
//2022/04/04 vamf TextView Zooming for HelpDialog by Button        //~vamfI~
//2022/04/04 vame TextView Zooming for HelpDialog                  //~vameI~
//2022/01/11 vaik Youtube movie as help                            //~vaikI~
//2021/07/13 vaaG set default BG/FG for HelpDlg                    //~vaaGI~
//2021/06/26 vaa0 support <img> in htmlText                        //~vaa0I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Bundle;                                          //~v@@@I~
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;                                    //~v@@@I~
import android.view.ViewGroup;                                     //~v@@@I~
import android.view.LayoutInflater;                                //~v@@@I~
import android.view.View;                                          //~v@@@I~

import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.gui.UButton;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UFile;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;                                    //~v@@@I~
import com.btmtest.utils.Utube;

import static android.text.Html.*;
import static com.btmtest.StaticVars.AG;

public class HelpDialog extends UFDlg                              //~v@@@R~
{                                                                  //~2C29R~
    public static final int HELP_BG_IMAGE=Color.argb(0xff,0xc0,0xd0,0xc0);//~vaa0I~
    public static final int HELP_FG_IMAGE=Color.argb(0xff,0x00,0x00,0x00);//~vaa0I~
    private static final int HELP_BG_IMAGE_DEFAULT=Color.argb(0xff,0xd0,0xe0,0xd0);//~vaaGI~
    private static final int HELP_FG_IMAGE_DEFAULT=Color.argb(0xff,0x00,0x00,0x00);//~vaaGI~
    private static final int TITLEID=R.string.Title_HistoryDlg;    //~v@@@R~
    private static final String DEFAULT_FILENAME="default.history";   //~v@@@R~
    private static final int ZOOM_STEP=2;                          //~vamfI~
    private TextView tvMessage;                                    //~v@@@I~
//  private UFDlg ufdlg;                                           //~v@@@R~
    private String helpFilename;                                   //~v@@@I~
    private int colorBG,colorFG;                                   //~vaa0I~
    private String playlistID,videoID;                             //~vaikI~
    private Button btnMovie;                                       //~vaikI~
	private Button btnZoomUp,btnZoomDown;                          //~vamfI~
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
        hdlg.colorBG=HELP_BG_IMAGE_DEFAULT;                        //~vaaGI~
        hdlg.colorFG=HELP_FG_IMAGE_DEFAULT;                        //~vaaGI~
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
      setBtnMovie(Playoutview);                                    //~vaikI~
//    initZoom(tvMessage);                                         //~vameI~//~vamfR~
      initZoomButton(Playoutview,tvMessage);                                   //~vamfI~
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
    //******************************************                   //~vaikI~
	public void showPlaylist(String PlistID)                       //~vaikI~
	{                                                              //~vaikI~
        if (Dump.Y) Dump.println("HelpDialog.showPlaylist playlistID="+PlistID);//~vaikI~
    	playlistID=PlistID;                                        //~vaikI~
        show();                                                    //~vaikI~
    }                                                              //~vaikI~
    //******************************************                   //~vaikI~
	public void showVideo(String PvideoID)                         //~vaikI~
	{                                                              //~vaikI~
        if (Dump.Y) Dump.println("HelpDialog.showVideo videoID="+PvideoID);//~vaikI~
    	videoID=PvideoID;                                          //~vaikI~
        show();                                                    //~vaikI~
    }                                                              //~vaikI~
    //******************************************                   //~vaikI~
    private void setBtnMovie(View PView)                             //~vaikR~
    {                                                              //~vaikI~
        if (Dump.Y) Dump.println("HelpDialog.setBtnMovie videoID="+videoID+",playlistID="+playlistID);//~vaikR~
    	super.initLayout(PView);                                   //~vaikI~
    	if (videoID!=null || playlistID!=null)                     //~vaikI~
        {                                                          //~vaikI~
	    	btnMovie=UButton.bind(PView,R.id.Cancel,this);         //~vaikI~
        	btnMovie.setVisibility(View.VISIBLE);                  //~vaikI~
        }                                                          //~vaikI~
    }                                                              //~vaikI~
    //******************************************                   //~vaikI~
    //*use cancel as open youtube video                            //~vaikI~
    //******************************************                   //~vaikI~
    @Override                                                      //~vaikI~
	public void onClickCancel()                                    //~vaikI~
	{                                                              //~vaikI~
        if (Dump.Y) Dump.println("HelpDialog.onClickCancel playlist="+playlistID+",videoID="+videoID);//~vaikI~
        boolean rc=false;                                          //~vaikI~
        if (playlistID!=null)                                      //~vaikI~
        {                                                          //~vaikI~
        	rc= Utube.openPlaylist(AG.context,playlistID);          //~vaikR~
        }                                                          //~vaikI~
        else                                                       //~vaikI~
        if (videoID!=null)                                         //~vaikI~
        {                                                          //~vaikI~
        	rc=Utube.playVideo(AG.context,videoID);                //~vaikR~
        }                                                          //~vaikI~
        if (Dump.Y) Dump.println("HelpDialog.onClickCancel rc="+rc);//~vaikI~
    }                                                              //~vaikI~
    //******************************************                   //~vamfI~
    @Override                                                      //~vamfI~
    public void onClickOther(int Pbuttonid)                        //~vamfI~
	{                                                              //~vamfI~
        if (Dump.Y) Dump.println("HelpDialog:onClickOthern id="+Pbuttonid);//~vamfI~
        switch(Pbuttonid)                                          //~vamfI~
        {                                                          //~vamfI~
            case R.id.ZoomUp:                                      //~vamfI~
                onClickZoom(tvMessage,1);                          //~vamfI~
                break;                                             //~vamfI~
            case R.id.ZoomDown:                                    //~vamfI~
                onClickZoom(tvMessage,-1);                         //~vamfI~
                break;                                             //~vamfI~
        }                                                          //~vamfI~
    }                                                              //~vamfI~
    //*****************************************************        //~vamfI~
    private void initZoomButton(View Playoutview,TextView PtextView)                //~vamfI~
    {                                                              //~vamfI~
        btnZoomUp=UButton.bind(Playoutview,R.id.ZoomUp,this);      //~vamfI~
        btnZoomDown=UButton.bind(Playoutview,R.id.ZoomDown,this);  //~vamfI~
    	pixTextSize0=(int)PtextView.getTextSize();                 //~vamfI~
    	pixTextSize=pixTextSize0;                                  //~vamfI~
		if (Dump.Y) Dump.println("HelpDialog.initZoomButton pixTextSize0="+pixTextSize0);//~vamfI~
    }                                                              //~vamfI~
    //*****************************************************        //~vamfI~
    private void onClickZoom(TextView PtextView,int Pinc)          //~vamfI~
    {                                                              //~vamfI~
		if (Dump.Y) Dump.println("HelpDialog.onClickZoom Pinc="+Pinc+",old="+pixTextSize);//~vamfI~
    	int szNew=pixTextSize+Pinc*ZOOM_STEP;                      //~vamfI~
        if (szNew<pixTextSize0)                                    //~vamfI~
        	szNew=pixTextSize0;                                    //~vamfI~
        PtextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,szNew); //sp//~vamfI~
		if (Dump.Y) Dump.println("HelpDialog.onClickZoom setTextSize szNew=+"+szNew+",pixTextSize0="+pixTextSize0);//~vamfI~
    	pixTextSize=szNew;                                         //+vamfI~
    }                                                              //~vamfI~
    //*****************************************************        //~2403I~//~vameI~
    private static final int NONE = 0;                             //~2403I~//~vameI~
    private static final int DRAG = 1;                             //~2403I~//~vameI~
    private static final int ZOOM = 2;                             //~2403I~//~vameI~
    private int mode = NONE;                                       //~2403I~//~vameI~
    private float oldDist = 1f;                                            //~2403I~//~@@@1R~//~vameI~
    private int pixTextSize,pixTextSize0;                              //~vameI~
    //*****************************************************        //~2403I~//~vameI~
    private void initZoom(TextView PtextView)                      //~2403I~//~vameI~
    {                                                              //~2403I~//~vameI~
    	pixTextSize0=(int)PtextView.getTextSize();                          //~vameI~
		if (Dump.Y) Dump.println("HelpDialog.initZoom pixTextSize0="+pixTextSize0+",textView="+PtextView);//~vameR~
        PtextView.setMovementMethod(new ScrollingMovementMethod());//~@@@1I~//~vameI~
        PtextView.setOnTouchListener(new View.OnTouchListener()    //~2403I~//~vameI~
        {                                                          //~vameI~
            @Override                                              //~2403I~//~vameI~
            public boolean onTouch(View Pview, MotionEvent Pevent) //~2403R~//~vameI~
			{                                                      //~2403I~//~vameI~
                                                                   //~2403I~//~vameI~
				try                                                //~2403I~//~vameI~
                {                                                  //~2403I~//~vameI~
			        if (Dump.Y) Dump.println("HelpDialog.onTouch event="+Pevent);//~vameI~
                	onTouchHelpText((TextView)Pview,Pevent);            //~@@@1I~//~vameI~
                }                                                  //~2403I~//~vameI~
                catch(Exception e)                                 //~2403I~//~vameI~
                {                                                  //~2403I~//~vameI~
			        Dump.println(e,"HelpDialog.onTouch");          //~vameI~
                }                                                  //~2403I~//~vameI~
                return false;	//scoll by system                  //~vameI~
            }                                                      //~2403I~//~vameI~
        });                                                        //~2403I~//~vameI~
	}                                                              //~2403I~//~vameI~
    //*****************************************************        //~@@@1I~//~vameI~
    private void onTouchHelpText(TextView PtextView,MotionEvent Pevent)//~@@@1I~//~vameI~
	{                                                              //~@@@1I~//~vameI~
		if (Dump.Y) Dump.println("HelpDialog.onTouchTextView event="+Pevent);//~vameI~
        switch (Pevent.getAction() & MotionEvent.ACTION_MASK)      //~@@@1I~//~vameI~
		{                                                          //~@@@1I~//~vameI~
        case MotionEvent.ACTION_DOWN:                              //~@@@1I~//~vameI~
			if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_DOWN textSize="+pixTextSize);//~vameI~
            mode = DRAG;                                           //~@@@1I~//~vameI~
            break;                                                 //~@@@1I~//~vameI~
        case MotionEvent.ACTION_POINTER_DOWN:                      //~@@@1I~//~vameI~
    		pixTextSize=(int)PtextView.getTextSize();                       //~vameI~
            oldDist = spacing(Pevent);                             //~@@@1I~//~vameI~
			if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_POINTER_DOWN oldDist="+oldDist);//~vameI~
            if (oldDist > 10f)                                     //~vameI~
 			{                                                      //~vameI~
                mode = ZOOM;                                       //~@@@1I~//~vameI~
				if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_POINTER_DOWN set mode=ZOOM");//~vameI~
            }                                                      //~@@@1I~//~vameI~
            break;                                                 //~@@@1I~//~vameI~
        case MotionEvent.ACTION_UP:                                //~@@@1I~//~vameI~
			if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_UP");//~vameI~
            break;                                                 //~vameI~
        case MotionEvent.ACTION_OUTSIDE:                           //~@@@1I~//~vameI~
			if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_OUTSIZE");//~vameI~
            mode = NONE;                                           //~@@@1I~//~vameI~
        case MotionEvent.ACTION_POINTER_UP:                        //~@@@1I~//~vameI~
			if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_POINTER_UP");//~vameI~
            mode = NONE;                                           //~@@@1I~//~vameI~
            break;                                                 //~@@@1I~//~vameI~
        case MotionEvent.ACTION_MOVE:                              //~@@@1I~//~vameI~
			if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_MOVE mode="+mode);//~vameI~
            if (mode == ZOOM && Pevent.getPointerCount() == 2) //~@@@1I~//~vameI~
            {                                                  //~@@@1I~//~vameI~
                float newDist = spacing(Pevent);              //~@@@1I~//~vameI~
				if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_MOVE mode=ZOOM and pointer=2 newDist1="+newDist);//~vameI~
                if (newDist > 10f)                            //~@@@5R~//~vameI~
                {                                              //~@@@5I~//~vameI~
                	float scale = newDist / oldDist * PtextView.getScaleX();//~@@@1I~//~vameI~
					if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_MOVE scale="+scale+",newDist1="+newDist+",oldDist1="+oldDist);//~vameI~
            		int szNew=pixTextSize+(scale>1.0 ? 1 : -1);                       //~@@@5R~//~vameI~
                    if (szNew<pixTextSize0)                        //~vameI~
                    	szNew=pixTextSize0;                        //~vameI~
            		PtextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,szNew); //sp//~@@@5I~//~vameI~
					if (Dump.Y) Dump.println("HelpDialog.onTouchTextView ACTION_MOVE mode=ZOOM setTextSize pixel szNew="+szNew+",old="+pixTextSize+",org="+pixTextSize0);//~vameI~
                    pixTextSize=szNew;                             //~vameI~
                }                                                  //~@@@1I~//~vameI~
            }                                                      //~vameI~
            break;                                                 //~@@@1I~//~vameI~
        }                                                          //~@@@1I~//~vameI~
		if (Dump.Y) Dump.println("HelpDialog.onTouchTextView exit");//~vameI~
    }                                                              //~@@@1I~//~vameI~
    //*****************************************************        //~2403I~//~vameI~
    private float spacing(MotionEvent event)                       //~2403I~//~vameI~
    {                                                              //~2403I~//~vameI~
        float x = event.getX(0) - event.getX(1);                   //~2403I~//~vameI~
        float y = event.getY(0) - event.getY(1);                   //~2403I~//~vameI~
        float rc=(float)Math.sqrt(x * x + y * y);                  //~2403I~//~vameI~
		if (Dump.Y) Dump.println("HelpDialog.spacing rc="+rc);     //~vameI~
        return rc;                                                 //~2403I~//~vameI~
    }                                                              //~2403I~//~vameI~
}//class                                                           //~v@@@R~//~vameM~
