//*CID://+va49R~:                             update#=   68;       //~va49R~
//**********************************************************************//~v105I~
//2020/11/21 va49 highlight compreqdlg button when Ron             //~va49I~
//**********************************************************************//~v105I~
package com.btmtest.utils;                                         //~v@@@R~
                                                                   //~v@@@I~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.app.Dialog;                                         //~v@@@I~
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.BaseAdapter;                                 //~@@@@I~
import android.widget.ListAdapter;                                 //~@@@@I~
import android.widget.ListView;                                    //~@@@@I~
import android.widget.Button;                                      //~v@@@I~
                                                                   //~1112I~
public class UiFunc// extends LinearLayout                        //~1120R~//~1216R~//~v@@@R~
	implements UiThread.UiThreadI                                           //~v@@@R~
{                                                                  //~1112I~
	private int funcid;                                      //~1425R~//~v@@@R~
    private final static int FUNC_APPEND      =1;                  //~1221I~//~v@@@R~
    private final static int FUNC_SETTEXT     =2;                  //~1221R~//~v@@@R~
    private final static int FUNC_SHOWBOTTOM  =3;                  //~1221I~//~@@@@R~//~v@@@R~
    private final static int FUNC_TITLE       =4;                  //~1221I~//~v@@@R~
    private final static int FUNC_BGCOLOR     =5;                  //~1310I~//~v@@@R~
    private final static int FUNC_FONT        =6;                  //~1310I~//~v@@@R~
    private final static int FUNC_BGFGCOLOR   =7;                  //~1312I~//~v@@@R~
 //   private final static int FUNC_SETICON     =8;                  //~1313I~//~v@@@R~
    private final static int FUNC_ENABLE      =9;                  //~1322I~//~v@@@R~
    private final static int FUNC_SETCHECK    =10;                 //~1322I~//~v@@@R~
    private final static int FUNC_FOCUS       =11;                 //~1405I~//~v@@@R~
    private final static int FUNC_SETITEMCHECKED=12;               //~v105I~//~@@@@R~//~v@@@R~
    private final static int FUNC_FOCUSTOUCH   =13;                //~v108I~//~v@@@R~
    private final static int FUNC_SETVISIBILITY=14;                //~@@@@I~//~v@@@R~
    private final static int FUNC_SETIMAGEBITMAP=15;                  //~@@@@I~//~v@@@R~
    private final static int FUNC_SETHEIGHT=16;                    //~@@@@I~//~v@@@R~
    private final static int FUNC_SETLAYOUTHEIGHT=17;              //~@@@@I~//~v@@@R~
    private final static int FUNC_APPENDSPAN=18;                   //~v101I~//~v@@@R~
    private final static int FUNC_BGDRAWABLE  =19;                 //~va49I~
                                                                   //~v101I~
    private final static int DEFAULT_TEXT_SIZE=48;                 //~@@@@I~
    private String line;                                              //~1221R~//~1425R~
    private SpannableString spanline;                              //~v101I~
    private int  color;                                              //~1221R~//~1425R~
    private int   iconresid;                                      //~1313I~
                                                                   //~1310I~
    public  int  componentType=0;        //control UI sync/async   //~1310R~
    public  static final int COMPONENT_FRAME=1;                    //~1310I~
    public  static final int COMPONENT_DIALOG=2;                   //~1310I~
    public  Dialog dialog;                                         //~1310I~
                                                                   //~1310I~
//    protected Font  font;                                            //~1310I~
    private int bgcolor,fgcolor;                                         //~1310R~//~v@@@R~
    private Drawable bgDrawable;                                   //~va49I~
    private int imagebgcolor;                                    //~@@@@I~//~v@@@R~
    ScrollView scrollview;                                         //~1425R~
    int pos=0;                                                     //~1221I~
                                                                   //~1221I~
	public View layout;                                            //~1425R~
	public View componentView;	//frame,textfield,button for requestFocus//~1425R~
    private boolean buttonState;
    private boolean listItemState;                                 //~v105I~//~@@@@R~
    private int drawableResourceId; 
    private int visibility;//~2B13I~
    private int imagevisibility;                                //~@@@@I~
    private int viewHeight,viewWidth;                                        //~@@@@I~
    private boolean setTextSize;                                   //~@@@@I~
    private Bitmap bitmap;                                         //~@@@@I~
//*************                                                    //~1310I~
    public UiFunc()                                                //~v@@@I~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//from TextComponent                                               //~1310R~
    public void setBackground(View Pview,int Pcolor)             //~1310I~
    {                                                              //~1310I~
    	bgcolor=Pcolor;                                            //~1310I~
    	runOnUiThread(FUNC_BGCOLOR,Pview);   //by Component        //~1310I~//~v@@@R~
    }                                                              //~1310I~
    public void setBackground(View Pview,int Pcolor,int Ptextcolor)//~1312I~
    {                                                              //~1312I~
    	bgcolor=Pcolor;                                            //~1312I~
    	fgcolor=Ptextcolor;                                        //~1312I~
    	runOnUiThread(FUNC_BGFGCOLOR,Pview);   //by Component      //~1312I~//~v@@@R~
    }                                                              //~1312I~
    public void setBackgroundUI(Object Pparm)                     //~1310I~
    {                                                              //~1310I~
        ((View)Pparm).setBackgroundColor(bgcolor);                        //~1310I~
    }                                                              //~1310I~
    public void setBackgroundDrawable(View Pview,Drawable Pdrawable)//~va49I~
    {                                                              //~va49I~
    	bgDrawable=Pdrawable;                                      //~va49I~
    	runOnUiThread(FUNC_BGDRAWABLE,Pview);   //by Component     //~va49I~
    }                                                              //~va49I~
    public void setBackgroundDrawableUI(Object Pparm)              //~va49I~
    {                                                              //~va49I~
        ((View)Pparm).setBackground(bgDrawable);              //~va49I~
    }                                                              //~va49I~
    public void setBGFGUI(Object Pparm)                            //~1312I~
    {                                                              //~1312I~
    	((View)Pparm).setBackgroundColor(bgcolor);                        //~1312I~
    	((TextView)Pparm).setTextColor(fgcolor);                         //~1312I~
    }                                                              //~1312I~
//    public void setFont(View Pview,Font Pfont)                     //~1310I~//~v@@@R~
//    {                                                              //~1310I~//~v@@@R~
//        font=Pfont;                                                //~1310I~//~v@@@R~
//        runOnUiThread(FUNC_FONT,Pview);   //by Component           //~1310I~//~v@@@R~
//    }                                                              //~1310I~//~v@@@R~
//    public void setFontUI(Object Pparm)                            //~1310I~//~v@@@R~
//    {                                                              //~1310I~//~v@@@R~
//        font.setFont((TextView)Pparm);                                 //~1310I~//~v@@@R~
//    }                                                              //~1310I~//~v@@@R~
    public void setHeight(View Pview,int Pheight)                  //~@@@@I~
    {                                                              //~@@@@I~
    	viewHeight=Pheight;                                        //~@@@@I~
    	runOnUiThread(FUNC_SETHEIGHT,Pview);   //by Component      //~@@@@I~//~v@@@R~
    }                                                              //~@@@@I~
    public void setHeightUI(Object Pparm)                          //~@@@@I~
    {                                                              //~@@@@I~
    	((TextView)Pparm).setHeight(viewHeight);                   //~@@@@I~
    }                                                              //~@@@@I~
    public void setLayoutHeight(View Pview,int Pheight,boolean Psettextsize)//~@@@@R~
    {                                                              //~@@@@I~
    	viewHeight=Pheight;	//height in pixel                      //~@@@@R~
    	viewWidth=0;	//height in pixel                          //~@@@@I~
        setTextSize=Psettextsize;                                  //~@@@@I~
    	runOnUiThread(FUNC_SETLAYOUTHEIGHT,Pview);   //by Component//~@@@@I~//~v@@@R~
    }                                                              //~@@@@I~
//    public void setLayoutWidthHeight(View Pview,int Pwidth,int Pheight,boolean Psettextsize)//~@@@@I~//~v@@@R~
//    {                                                              //~@@@@I~//~v@@@R~
//        viewHeight=Pheight; //height in pixel                      //~@@@@I~//~v@@@R~
//        viewWidth=Pwidth;   //height in pixel                      //~@@@@I~//~v@@@R~
//        setTextSize=Psettextsize;                                  //~@@@@I~//~v@@@R~
//        runOnUiThread(FUNC_SETLAYOUTHEIGHT,Pview);   //by Component//~@@@@I~//~v@@@R~
//    }                                                              //~@@@@I~//~v@@@R~
//    public void setLayoutHeightUI(Object Pparm)                    //~@@@@I~//~v@@@R~
//    {                                                              //~@@@@I~//~v@@@R~
//        View v=(View)Pparm;                                        //~@@@@I~//~v@@@R~
//        ViewGroup.LayoutParams params=v.getLayoutParams();         //~@@@@I~//~v@@@R~
//        params.height=viewHeight;   //pixel                        //~@@@@R~//~v@@@R~
//        if (viewWidth!=0)                                          //~@@@@I~//~v@@@R~
//            params.width=viewWidth; //pixel                    //~@@@@I~//~v@@@R~
//        v.setLayoutParams(params);                                 //~@@@@I~//~v@@@R~
//        if (setTextSize)                                       //~@@@@R~//~v@@@R~
//        {                                                          //~@@@@I~//~v@@@R~
//            TextView tv=(TextView)Pparm;                           //~@@@@I~//~v@@@R~
//            tv.setTextSize((float)AG.smallTextSize/*sp*/);         //~@@@@R~//~v@@@R~
//        }                                                          //~@@@@I~//~v@@@R~
//    }                                                              //~@@@@I~//~v@@@R~
    public void setVisibility(View Pview,int Pvisibility)          //~@@@@I~
    {                                                              //~@@@@I~
    	visibility=Pvisibility;                                    //~@@@@I~
    	runOnUiThread(FUNC_SETVISIBILITY,Pview);   //by Component  //~@@@@I~//~v@@@R~
    }                                                              //~@@@@I~
    public void setImageBitmap(View Pview,Bitmap Pbitmap,int Pvisibility)//~@@@@I~
    {                                                              //~@@@@I~
    	bitmap=Pbitmap;                                            //~@@@@I~
        imagebgcolor=-1;                                         //~@@@@I~
    	imagevisibility=Pvisibility;                               //~@@@@I~
    	runOnUiThread(FUNC_SETIMAGEBITMAP,Pview);                  //~@@@@I~//~v@@@R~
    }                                                              //~@@@@I~
    public void setImageBitmap(View Pview,Bitmap Pbitmap,int Pvisibility,int Pbgcolor)//~@@@@I~
    {                                                              //~@@@@I~
    	bitmap=Pbitmap;                                            //~@@@@I~
        imagebgcolor=Pbgcolor;                                     //~@@@@I~
    	imagevisibility=Pvisibility;                               //~@@@@I~
    	runOnUiThread(FUNC_SETIMAGEBITMAP,Pview);                  //~@@@@I~//~v@@@R~
    }                                                              //~@@@@I~
//***                                                              //~1310I~
//    public Toolkit getToolkit()                                       //~1213I~//~v@@@R~
//    {                                                              //~1213I~//~v@@@R~
//        return new Toolkit();                                      //~1213I~//~v@@@R~
//    }                                                              //~1213I~//~v@@@R~
//    public void addMouseListener(MouseListener Pml)                //~1213I~//~v@@@R~
//    {                                                              //~1213I~//~v@@@R~
//        if (Pml instanceof DoActionListener)                     //~v@@@R~
//         {                                                       //~v@@@R~
//        }                                                        //~v@@@R~
//    }                                                              //~1213I~//~v@@@R~
//****************                                                 //~1405I~
//    public void requestFocus()  //for Board ,moved to Canvas       //~1405R~//~v@@@R~
//    {                                                              //~1405R~//~v@@@R~
//        Frame f=AG.getCurrentFrame();                              //~v108R~//~v@@@R~
//        if (f!=null)                                               //~v108R~//~v@@@R~
//        {                                                          //~v108I~//~v@@@R~
//        }                                                          //~v108I~//~v@@@R~
//        if (componentView!=null)                                   //~1405R~//~v@@@R~
//            runOnUiThread(FUNC_FOCUS,null);                        //~1405I~//~v@@@R~
//    }                                                              //~1405R~//~v@@@R~
//****************                                                 //~v108I~
    public void requestFocus(View Pview)  //for connectedGoFrame   //~v108R~
    {                                                              //~v108I~
        if (Pview!=null)                                           //~v108R~
	    	runOnUiThread(FUNC_FOCUS,Pview);                       //~v108R~//~v@@@R~
    }                                                              //~v108I~
//****************                                                 //~v108I~
    public void requestFocusFromTouch()                            //~v108I~
    {                                                              //~v108I~
        if (componentView!=null)                                   //~v108I~
	    	runOnUiThread(FUNC_FOCUSTOUCH,null);                   //~v108I~//~v@@@R~
    }                                                              //~v108I~
//****************                                                 //~v108I~
    private void requestFocusUI(Object Pparm)                      //~1405I~
    {                                                              //~1405I~
    	if (Pparm!=null)                                           //~v108I~
        {                                                          //~v108I~
	    	if (Dump.Y) Dump.println("Componet.requestFocus view="+Pparm.toString());//~v108I~
        	((View)Pparm).requestFocus();                          //~v108I~
        }                                                          //~v108I~
    	if (Dump.Y) Dump.println("Componet.requestFocus view="+componentView.toString());//~1506R~
        if (Dump.Y) Dump.println("focusable="+componentView.isFocusable()+",touch="+componentView.isFocusableInTouchMode()+",isfocused="+componentView.isFocused());//~1506R~
        componentView.requestFocus();                              //~1405I~
        if (Dump.Y) Dump.println("after focusable="+componentView.isFocusable()+",touch="+componentView.isFocusableInTouchMode()+",isfocused="+componentView.isFocused());//~1506R~
    }                                                              //~1405I~
//****************                                                 //~v108I~
    private void requestFocusFromTouchUI(Object Pparm)                 //~v108I~
    {                                                              //~v108I~
    	if (Dump.Y) Dump.println("Componet.requestFocusFromTouch view="+componentView.toString());//~v108I~
        componentView.requestFocusFromTouch();                     //~v108I~
    }                                                              //~v108I~
//********************************                                 //~1221I~
//* support All runOnUiThread                                      //~1221I~//~v@@@R~
//********************************                                 //~1221I~
    public void runOnUiThread(int Pcase,Object Pparm)              //~1221I~//~v@@@R~
    {                                                              //~1221I~
    	funcid=Pcase;                                        //~1221I~//~v@@@R~
        boolean wait=waitmode(Pcase);                                   //~1310I~
		UiThread.runOnUiThread(wait,this,Pparm);               //~1221I~//~1310R~//~@@@@R~//~v@@@R~
    }                                                              //~1221I~
    boolean waitmode(int Pcase)                                    //~1310I~
    {                                                              //~1310I~
    	boolean waitmode=true;                                     //~1310I~
        return waitmode;                                           //~1310I~
    }                                                              //~1310I~
//************                                                     //~1310I~
	@Override                                                      //~1221I~
    public void runOnUiThread(Object Pparm)                        //~1221I~//~v@@@R~
    {                                                              //~1221I~
        if (Dump.Y) Dump.println("UiFunc.runOnUiThread case="+funcid);//~1506R~//~v@@@R~//+va49I~
        switch(funcid)                                       //~1221I~//~v@@@R~
        {                                                          //~1221I~
        case FUNC_APPEND:                                          //~1221I~//~v@@@R~
        	appendUI(Pparm);                                       //~1221I~
            break;                                                 //~1221I~
        case FUNC_SETTEXT:                                         //~1221I~//~v@@@R~
        	setTextUI(Pparm);                                      //~1221I~
            break;                                                 //~1221I~
        case FUNC_SHOWBOTTOM:                                      //~1221I~//~@@@@R~//~v@@@R~
            showBottomUI(Pparm);                                   //~1221I~//~@@@@R~
            break;                                                 //~1221I~//~@@@@R~
        case FUNC_TITLE:                                           //~1221I~//~v@@@R~
        	setTitleUI(Pparm);                                     //~1221I~
            break;                                                 //~1221I~
        case FUNC_BGCOLOR:                                         //~1310I~//~v@@@R~
        	setBackgroundUI(Pparm);                                //~1310I~
            break;                                                 //~1310I~
        case FUNC_BGFGCOLOR:                                       //~1312I~//~v@@@R~
        	setBGFGUI(Pparm);                                      //~1312I~
            break;                                                 //~1312I~
        case FUNC_FONT:                                            //~v108I~//~@@@@I~//~v@@@R~
//          setFontUI(Pparm);   //use android default button text size//~@@@@R~
            break;                                                 //~v108I~//~@@@@I~
//        case FUNC_SETICON:                                         //~1313I~//~v@@@R~
//            setIconImageUI(Pparm);                                 //~1417R~//~v@@@R~
//            break;                                                 //~1313I~//~v@@@R~
        case FUNC_ENABLE:                                          //~1322I~//~v@@@R~
        	setEnabledUI(Pparm);                                   //~1322I~
            break;                                                 //~1322I~
        case FUNC_SETCHECK:                                        //~1322I~//~v@@@R~
        	setCheckedUI(Pparm);                                   //~1322I~
            break;                                                 //~1322I~
        case FUNC_FOCUS:                                        //~1405I~//~v@@@R~
        	requestFocusUI(Pparm);                                 //~1405I~
            break;                                                 //~1405I~
        case FUNC_FOCUSTOUCH:                                      //~v108I~//~v@@@R~
        	requestFocusFromTouchUI(Pparm);                        //~v108I~
            break;                                                 //~v108I~
        case FUNC_SETVISIBILITY:                                   //~@@@@I~//~v@@@R~
        	setVisibilityUI(Pparm);                                //~@@@@I~
            break;                                                 //~@@@@I~
        case FUNC_SETIMAGEBITMAP:                                  //~@@@@I~//~v@@@R~
        	setImageBitmapUI(Pparm);                               //~@@@@I~
            break;                                                 //~@@@@I~
        case FUNC_SETHEIGHT:                                       //~@@@@I~//~v@@@R~
        	setHeightUI(Pparm);                                    //~@@@@I~
            break;                                                 //~@@@@I~
//        case FUNC_SETLAYOUTHEIGHT:                                 //~@@@@I~//~v@@@R~
//            setLayoutHeightUI(Pparm);                              //~@@@@I~//~v@@@R~
//            break;                                                 //~@@@@I~//~v@@@R~
        case FUNC_SETITEMCHECKED:                                  //~v105I~//~@@@@R~//~v@@@R~
            setItemCheckedUI(Pparm);                               //~v105I~//~@@@@R~
            break;                                                 //~v105I~//~@@@@R~
        case FUNC_APPENDSPAN:                                      //~v101I~//~v@@@R~
            appendSpanUI(Pparm);                                   //~v101I~
            break;                                                 //~v101I~
        case FUNC_BGDRAWABLE:                                      //~va49I~
        	setBackgroundDrawableUI(Pparm);                        //~va49I~
            break;                                                 //~va49I~
        }                                                          //~1221I~
    }                                                              //~1221I~
//*****************                                                //~1221I~
    public void append(TextView Ptextview,ScrollView Pscrollview,String Pline,int Pcolor)//~1221R~//~@@@@R~
    {                                                              //~1221I~//~@@@@R~
        line=Pline;                                                //~1221I~//~@@@@R~
        color=Pcolor;                                              //~1221I~//~@@@@R~
        scrollview=Pscrollview;                                    //~1221I~//~@@@@R~
        runOnUiThread(FUNC_APPEND,Ptextview);                      //~1221I~//~@@@@R~//~v@@@R~
    }                                                              //~1221I~//~@@@@R~
    private void appendUI(Object Pparm)                            //~1221I~//~@@@@R~
    {                                                              //~1221I~//~@@@@R~
        TextView textview=(TextView)Pparm;                         //~1221I~//~@@@@R~
        textview.append(line);                                     //~1221I~//~@@@@R~
        if (color>=0)                                           //~1221I~//~@@@@R~
        {                                                          //~1221I~//~@@@@R~
            textview.setTextColor(color);                 //~1221I~//~@@@@R~
        }                                                          //~1221I~//~@@@@R~
        if (scrollview!=null)                                     //~1221I~//~@@@@R~
        {                                                          //~1221I~//~@@@@R~
            scrollview.scrollTo(0/*x*/,32760/*y:Bottom*/);         //~1221I~//~@@@@R~
        }                                                          //~1221I~//~@@@@R~
    }                                                              //~1221I~//~@@@@R~
//*****************                                                //~v101I~
    public void append(TextView Ptextview,ScrollView Pscrollview,SpannableString Pspanline)//~v101I~
    {                                                              //~v101I~
        spanline=Pspanline;                                        //~v101I~
        scrollview=Pscrollview;                                    //~v101I~
        runOnUiThread(FUNC_APPENDSPAN,Ptextview);                  //~v101I~//~v@@@R~
    }                                                              //~v101I~
//*****************                                                //~v101I~
    private void appendSpanUI(Object Pparm)                        //~v101I~
    {                                                              //~v101I~
    	if (Dump.Y)Dump.println("TextComponent appendSpanUI"); 
    	TextView textview=(TextView)Pparm;                         //~v101I~
        textview.append(spanline);                                 //~v101I~
        if (scrollview!=null)                                      //~v101I~
        {                                                          //~v101I~
            scrollview.scrollTo(0/*x*/,32760/*y:Bottom*/);         //~v101I~
        }                                                          //~v101I~
    }                                                              //~v101I~
//*****************                                                //~1221I~
    public void setText(TextView Ptextview,String Pline)           //~1221I~
    {                                                              //~1221I~
    	line=Pline;                                                //~1221I~
    	runOnUiThread(FUNC_SETTEXT,Ptextview);                //~1221I~//~v@@@R~
    }                                                              //~1221I~
    private void setTextUI(Object Pparm)                           //~1221I~
    {                                                              //~1221I~
    	TextView textview=(TextView)Pparm;                         //~1221I~
        textview.setText(line);                                    //~1221I~
    }                                                              //~1221I~
//*****************                                                //~1221I~
    public void showList(ListView Plistview,int Ppos)            //~1221R~//~@@@@R~
    {                                                              //~1221I~//~@@@@R~
        if (Dump.Y) Dump.println("Component:showBottom pos="+pos); //~1506R~//~@@@@R~
        pos=Ppos;                                                  //~1221I~//~@@@@R~
        runOnUiThread(FUNC_SHOWBOTTOM,Plistview);                  //~1221I~//~@@@@R~//~v@@@R~
    }                                                              //~1221I~//~@@@@R~
    private void showBottomUI(Object Pparm)                        //~1221I~//~@@@@R~
    {                                                              //~1221I~//~@@@@R~
        if (Dump.Y) Dump.println("Component:showBottomUI pos="+pos);//~1506R~//~@@@@R~
        ListView listview=(ListView)Pparm;                         //~1221I~//~@@@@R~
        ListAdapter adapter=listview.getAdapter();                 //~1221I~//~@@@@R~
        ((BaseAdapter)adapter).notifyDataSetChanged();             //~@@@@R~
        if (pos<0) //keep currenttop                               //~@@@@R~
            pos=listview.getFirstVisiblePosition();//~1221R~       //~@@@@R~
        listview.setSelectionFromTop(pos,0);                       //~1221I~//~@@@@R~
    }                                                              //~1221I~//~@@@@R~
//*****************                                                //~v105I~
    public void setItemChecked(ListView Plistview,int Ppos,boolean Pstate)//~v105I~//~@@@@R~
    {                                                              //~v105I~//~@@@@R~
        if (Dump.Y) Dump.println("Component:setItemChecked pos="+Ppos);//~v105I~//~@@@@R~
        pos=Ppos;                                                  //~v105I~//~@@@@R~
        listItemState=Pstate;                                      //~v105I~//~@@@@R~
        runOnUiThread(FUNC_SETITEMCHECKED,Plistview);              //~v105I~//~@@@@R~//~v@@@R~
    }                                                              //~v105I~//~@@@@R~
    private void setItemCheckedUI(Object Pparm)                    //~v105I~//~@@@@R~
    {                                                              //~v105I~//~@@@@R~
        if (Dump.Y) Dump.println("Component:setItemChecked pos="+pos);//~v105I~//~@@@@R~
        ListView listview=(ListView)Pparm;                         //~v105I~//~@@@@R~
        listview.setItemChecked(pos,listItemState);                //~v105I~//~@@@@R~
    }                                                              //~v105I~//~@@@@R~
//*****************                                                //~1221I~
    public void setTitle(String Ptitle)                            //~1221I~
    {                                                              //~1221I~
    	runOnUiThread(FUNC_TITLE,Ptitle);                          //~1221I~//~v@@@R~
    }                                                              //~1221I~
    private void setTitleUI(Object Pparm)                          //~1221I~
    {                                                              //~1221I~
	    AG.activity.setTitle((String)Pparm);                       //~1221I~
    }                                                              //~1221I~
////************************                                         //~1417I~//~v@@@R~
////*from CloseFrame:seticon, through Hashtable                      //~1417I~//~v@@@R~
////************************                                         //~1417I~//~v@@@R~
//    public void setIconImage(Image Pimage)                         //~1417I~//~v@@@R~
//    {                                                              //~1417I~//~v@@@R~
//        this.seticonImage(Pimage.iconFilename);                    //~1417R~//~v@@@R~
//    }                                                              //~1417I~//~v@@@R~
//************************                                                //~1221I~//~1312R~
//*set title bar  icon                                             //~1312I~
//************************                                         //~1312I~
//    public void seticonImage(String Pfilename)                     //~1417R~//~v@@@R~
//    {                                                              //~1312I~//~v@@@R~
//        int resid;                                                 //~1326I~//~v@@@R~
//        if (Pfilename==null)                             //~1312I~ //~1326R~//~v@@@R~
//            resid=iconresid;    //restore                          //~1326I~//~v@@@R~
//        else                                                       //~1326I~//~v@@@R~
//            resid=AG.findIconId(Pfilename);                             //~1312I~//~1326R~//~v@@@R~
//        if (resid<=0)                                              //~1312I~//~v@@@R~
//            return;                                                //~1312I~//~v@@@R~
//        iconresid=resid;                                           //~1313I~//~v@@@R~
//        runOnUiThread(FUNC_SETICON,null);                          //~1313I~//~v@@@R~
//    }                                                              //~1312I~//~v@@@R~
//    public void setIconImageUI(Object Pparm)                       //~1417R~//~v@@@R~
//    {                                                              //~1313I~//~v@@@R~
//        android.view.Window w;                                   //~v@@@R~
//                   //~0914R~//~0915R~//~0A09R~//~1312I~//~1329R~//~1331I~//~v@@@R~
//        w=AG.activity.getWindow();                                 //~1313I~//~v@@@R~
//        w.setFeatureDrawableResource(android.view.Window.FEATURE_LEFT_ICON,iconresid);//~1313I~//~v@@@R~
//    }                                                              //~1313I~//~v@@@R~
//*************************                                        //~1322I~
//*IconBar Button                                                  //~1322I~
//************************                                         //~1322I~
//*setEnabled requires Mainthread*                                 //~1425I~
    public void setEnabled(Button Pbutton,boolean Pstate)          //~1322I~//~@@@@R~
    {                                                              //~1322I~//~@@@@R~
        if (Dump.Y) Dump.println("Component setenabled="+Pbutton.toString());//~1506R~//~@@@@R~
        buttonState=Pstate;                                        //~1322I~//~@@@@R~
        runOnUiThread(FUNC_ENABLE,Pbutton);                        //~1425R~//~@@@@R~//~v@@@R~
    }                                                              //~1322I~//~@@@@R~
    private void setEnabledUI(Object Pparm)                           //~1322I~//~@@@@R~
    {                                                              //~1322I~//~@@@@R~
        Button button=(Button)Pparm;                               //~1322I~//~@@@@R~
        button.setEnabled(buttonState);                            //~1322I~//~@@@@R~
    }                                                              //~1322I~//~@@@@R~
//************                                                     //~2B13I~
    public void setChecked(android.widget.Button Pbutton,int PdrawableId)//Iconbar Toggle button//~2B13R~
    {                                                              //~2B13I~
    	if (PdrawableId==0)                                        //~2B13I~
        	return;                                                //~2B13I~
    	drawableResourceId=PdrawableId;                            //~2B13I~
    	runOnUiThread(FUNC_SETCHECK,Pbutton);                      //~2B13I~//~v@@@R~
    }                                                              //~2B13I~
    private void setCheckedUI(Object Pparm)                          //~1322I~
    {                                                              //~1322I~
	    android.widget.Button button=(android.widget.Button)Pparm; //~2B13I~
        button.setBackgroundResource(drawableResourceId);          //~2B13I~
    }                                                              //~1322I~
//*****************                                                //~@@@@I~
    private void setVisibilityUI(Object Pparm)                       //~@@@@I~
    {                                                              //~@@@@I~
	    View v=(View)Pparm;                                        //~@@@@I~
        v.setVisibility(visibility);                               //~@@@@I~
    }                                                              //~@@@@I~
//*****************                                                //~@@@@I~
    private void setImageBitmapUI(Object Pparm)                      //~@@@@I~
    {                                                              //~@@@@I~
	    ImageView v=(ImageView)Pparm;                              //~@@@@I~
        if (imagebgcolor>=0)                                    //~@@@@I~
        	v.setBackgroundColor(imagebgcolor);           //~@@@@I~
        v.setImageBitmap(bitmap);                                  //~@@@@I~
        v.setVisibility(imagevisibility);                          //~@@@@I~
    }                                                              //~@@@@I~
}//class                                                           //~1112I~
