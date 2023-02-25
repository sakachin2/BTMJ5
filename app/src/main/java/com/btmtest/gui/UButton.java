//*CID://+vac5R~:                             update#=   94;       //~vac5R~
//**********************************************************************//~v105I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//get View & set Button OnClickListener                            //~v@@@R~
//**********************************************************************//~v105I~
package com.btmtest.gui;                                           //~v@@@R~
                                                                   //~v@@@I~
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
//~v@@@I~
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import static com.btmtest.StaticVars.AG;                           //~vac5I~
//~1112I~
public class UButton implements View.OnClickListener               //~v@@@R~
{                                                              //~1528I~//~v@@@I~
//**********************************                               //~v@@@I~
    public interface UButtonI                                      //~v@@@I~
    {                                                              //~v@@@I~
        void onClickButton(Button Pbutton);                        //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private UButtonI listener;                                             //~v@@@R~
//**********************************                               //~v@@@I~
    public UButton(UButtonI Plistener)                //~1528I~    //~v@@@R~
    {                                                          //~1528I~//~v@@@I~
        listener=Plistener;                                        //~v@@@I~
    }                                                          //~1528I~//~v@@@I~
    @Override                                                  //~1831I~//~v@@@I~
    public void onClick(View Pv)                               //~1528I~//~v@@@I~
    {                                                          //~1528I~//~v@@@I~
        if (Dump.Y) Dump.println("UButton:onClick listener="+listener.getClass().getSimpleName()+",id="+Integer.toHexString(Pv.getId()));//~v@@@R~//+vac5R~
        try                                                    //~1831I~//~v@@@I~
        {                                                      //~1831I~//~v@@@I~
            listener.onClickButton((Button)Pv);              //~1831R~     //~v@@@R~
        }                                                      //~1831I~//~v@@@I~
        catch(Exception e)                                     //~1831I~//~v@@@I~
        {                                                      //~1831I~//~v@@@I~
            Dump.println(e,"UButton:OnClick("+listener.getClass().getSimpleName()+")");               //~1831I~//~v@@@R~
        }                                                      //~1831I~//~v@@@I~
    }                                                              //~1528I~//~v@@@I~
//*******************************************************************                                            //~1528I~//~v@@@I~
	public static void setButtonListener(Button Pbutton,UButtonI Plistener)                 //~1919R~//~v@@@R~
    {                                                              //~1528I~//~v@@@I~
        if (Dump.Y) Dump.println("UButton:setButtonListener listener="+Plistener.getClass().getSimpleName()+",id="+Integer.toHexString(Pbutton.getId()));//~v@@@R~//+vac5R~
        UButton ubtn=new UButton(Plistener);           //~1528I~   //~v@@@R~
        Pbutton.setOnClickListener(ubtn);                            //~1528I~//~v@@@R~
    }                                                              //~1528I~//~v@@@I~
//*******************************************************************//~v@@@I~
	public static Button bind(View Playout,int Pid,UButtonI Plistener)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UButton:bind id="+Integer.toHexString(Pid));//~v@@@R~
		Button btn=(Button)UView.findViewById(Playout,Pid);        //~v@@@I~
        btn.setAllCaps(false);                                     //~v@@@I~
        if (Plistener!=null)                                       //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("UButton:bind listener="+Plistener.getClass().getSimpleName());//~v@@@R~
	        setButtonListener(btn,Plistener);                      //~v@@@R~
        }                                                          //~v@@@I~
        return btn;                                                //~v@@@I~
    }                                                              //~v@@@I~
//*******************************************************************//~v@@@I~
	public static void bind(Button Pbtn,UButtonI Plistener)        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UButton:getView id="+Integer.toHexString(Pbtn.getId()));//~v@@@I~
	    setButtonListener(Pbtn,Plistener);                         //~v@@@I~
    }                                                              //~v@@@I~
//*******************************************************************//~vac5I~
//*set size by dpi                                                 //~vac5I~
//*-1:fill_parent, -2:wrap_content                                 //~vac5I~
//*******************************************************************//~vac5I~
	public static void setHeight(Button Pbtn,int Pwidth,int Pheight,boolean PswDPI)//~vac5R~
    {                                                              //~vac5I~
        ViewGroup.LayoutParams p=Pbtn.getLayoutParams();           //~vac5R~
        if (Dump.Y) Dump.println("UButton:setSize swDPI="+PswDPI+",Button="+Pbtn.toString()+",width="+Pwidth+",height="+Pheight+",dip2pix="+AG.dip2pix+",getLayoutParams=("+p.width+","+p.height+")");//~vac5R~
        if (Pwidth!=0)                                             //~vac5I~
	        p.width=Pwidth<0 ? Pwidth : (PswDPI ? (int)(Pwidth*AG.dip2pix) : Pwidth);//~vac5R~
        if (Pheight!=0)                                            //~vac5I~
	        p.height=Pheight<0 ? Pheight : (PswDPI ? (int)(Pheight*AG.dip2pix) : Pheight);//~vac5R~
        if (Dump.Y) Dump.println("UButton:setSize layoutParam=("+p.width+","+p.height+")");//~vac5R~
        Pbtn.setLayoutParams(p);                                   //~vac5I~
    }                                                              //~vac5I~
//*******************************************************************//~vac5I~
	public static void setSize(View Pview,int PlayoutID,int Pwidth,int Pheight,boolean PswDPI)//~vac5R~
    {                                                              //~vac5I~
        if (Dump.Y) Dump.println("UButton:setSize View="+Pview.toString()+",layoutID="+Integer.toHexString(PlayoutID)+",width="+Pwidth+",height="+Pheight+",swDPI="+PswDPI);//~vac5R~
        ViewGroup vg=(ViewGroup)(Pview.findViewById(PlayoutID));   //~vac5I~
        if (vg==null)                                              //~vac5I~
        {                                                          //~vac5I~
	        if (Dump.Y) Dump.println("UButton:setSeize @@@@ buttons not found id="+Integer.toHexString(PlayoutID));//~vac5R~
        	return;                                                //~vac5I~
        }                                                          //~vac5I~
        int ctr=vg.getChildCount();                                //~vac5I~
	    if (Dump.Y) Dump.println("UButton:setSeize child ctr="+ctr);//~vac5I~
        for (int ii=0;ii<ctr;ii++)                                 //~vac5I~
        {                                                          //~vac5I~
        	View v=(View)(vg.getChildAt(ii));                      //~vac5I~
		    if (Dump.Y) Dump.println("UButton:setSeize child ii="+ii+",id="+Integer.toHexString(v.getId())+",view="+v.toString());//~vac5R~
            if (v instanceof CheckBox)                             //~vac5I~
            {                                                      //~vac5I~
			    if (Dump.Y) Dump.println("UButton:setSeize child is CheckBox");//~vac5I~
            }                                                      //~vac5I~
            else                                                   //~vac5I~
            if (v instanceof Button)                               //~vac5I~
            {                                                      //~vac5I~
        		Button btn=(Button)(v);                            //~vac5I~
				setHeight(btn,Pwidth,Pheight,PswDPI);              //~vac5R~
            }                                                      //~vac5I~
            else                                                   //~vac5I~
            if (v instanceof ViewGroup)                            //~vac5I~
            {                                                      //~vac5I~
			    if (Dump.Y) Dump.println("UButton:setSeize child is ViewGroup");//~vac5I~
				setSize(v,Pwidth,Pheight,PswDPI);                  //~vac5R~
            }                                                      //~vac5I~
        }                                                          //~vac5I~
    }                                                              //~vac5I~
//*******************************************************************//~vac5I~
	public static void setSize(View Pview,int Pwidth,int Pheight,boolean PswDPI)//~vac5I~
    {                                                              //~vac5I~
        if (Dump.Y) Dump.println("UButton:setSize for ViewGroup LayoutView=id="+Integer.toHexString(Pview.getId())+"="+Pview.toString()+",width="+Pwidth+",height="+Pheight+",swDPI="+PswDPI);//~vac5R~
        ViewGroup vg=(ViewGroup)Pview;                             //~vac5I~
        int ctr=vg.getChildCount();                                //~vac5I~
	    if (Dump.Y) Dump.println("UButton:setSeize child ctr="+ctr);//~vac5I~
        for (int ii=0;ii<ctr;ii++)                                 //~vac5I~
        {                                                          //~vac5I~
        	View v=(View)(vg.getChildAt(ii));                      //~vac5I~
		    if (Dump.Y) Dump.println("UButton:setSeize for ViewGroup child ii="+ii+"mid="+Integer.toHexString(v.getId())+",view="+v.toString());//~vac5R~
            if (v instanceof CheckBox)                             //~vac5I~
            {                                                      //~vac5I~
			    if (Dump.Y) Dump.println("UButton:setSeize for ViewGroup child is CheckBox");//~vac5R~
            }                                                      //~vac5I~
            else                                                   //~vac5I~
            if (v instanceof Button)                               //~vac5I~
            {                                                      //~vac5I~
        		Button btn=(Button)(v);                            //~vac5I~
				setHeight(btn,Pwidth,Pheight,PswDPI);              //~vac5I~
            }                                                      //~vac5I~
            else                                                   //~vac5I~
            if (v instanceof ViewGroup)                            //~vac5I~
            {                                                      //~vac5I~
			    if (Dump.Y) Dump.println("UButton:setSeize for ViewGroup child is ViewGroup");//~vac5R~
				setSize(v,Pwidth,Pheight,PswDPI);                  //~vac5R~
            }                                                      //~vac5I~
        }                                                          //~vac5I~
    }                                                              //~vac5I~
}//class                                                           //~1112I~
