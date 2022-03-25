//*CID://+vakQR~:                             update#=   37;       //~vakQR~
//**************************************************************************//~1B0bI~//~v1EjI~
//2022/03/19 vakQ notify update of rule when client received       //~vakQI~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//**************************************************************************//~1B0bI~//~v1EjI~
package com.btmtest.gui;                                           //~v1EjI~

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v1EjI~

public class USpinner                                              //~v1EjI~
{                                                                  //~1112I~//~v1EjI~
//  private Spinner spinner;                                       //~1219I~//~v1EjI~//~vakQR~
    public  Spinner spinner;                                       //~vakQI~
    private ArrayAdapter<CharSequence> adapter;                                  //~v1EjI~
//  private static final int itemlayout=android.R.layout.simple_spinner_item;//~v1EjI~
    private static final int itemlayout=R.layout.spinner_item;     //~v1EjI~
    private static final int itemlayout_smallfont=R.layout.spinner_item_theme;//~vac5I~
//  private static final int dropdownlayout=android.R.layout.simple_spinner_dropdown_item;//~v1EjI~
    private static final int dropdownlayout=R.layout.spinner_dropdown;//~v1EjI~
    private static final int dropdownlayout_smallfont=R.layout.spinner_dropdown_theme;//~vac5I~
    private int USParm;                                            //~v1EjI~
    private USpinnerI listener;
    private OnItemSelectedListener spListener;//~v1EjI~
    private boolean swFixed;                                       //~9226I~
    private int idxFixed;                                          //~9226I~
    private boolean swSetBG;                                       //~vakQI~
    private int colorBG;                                           //~vakQI~
//*******************************                                  //~v1EjI~
    public interface USpinnerI                                     //~v1EjI~
    {                                                              //~v1EjI~
        void onItemSelectedUS(int PviewID,int Ppos,int Pparm);                 //~v1EjR~//~9228R~
        void onNothingSelectedUS(int Pparm);                            //~v1EjI~
    }                                                              //~v1EjI~
//*******************************                                  //~1216I~//~v1EjI~
    public USpinner(View Playout, int Pid)                                                 //~1112R~//~1219R~//~v1EjR~
    {                                                              //~1112I~//~v1EjI~
		spinner=(Spinner)UView.findViewById(Playout,Pid);        //~v@@@I~//~v1EjI~
//  	adapter=new ArrayAdapter(AG.context,itemlayout);           //~v1EjR~//~vac5R~
    	adapter=new ArrayAdapter(AG.context,(AG.swSmallFont ? itemlayout_smallfont : itemlayout));//~vac5I~
//  	adapter.setDropDownViewResource(dropdownlayout);           //~v1EjR~//~vac5R~
    	adapter.setDropDownViewResource(AG.swSmallFont ? dropdownlayout_smallfont : dropdownlayout);//~vac5I~
        spinner.setAdapter(adapter);                               //~v1EjI~
//  	setListener(null);                                         //~9228I~//~9902R~
		setListener(createListener());                             //~9902I~
    }                                                              //~1112I~//~v1EjI~
//***************                                                  //~1220I~//~v1EjI~
	public void add(String Pentry)                                 //~1220I~//~v1EjI~
    {                                                              //~1220I~//~v1EjI~
    	adapter.add(Pentry);                                       //~1220I~//~v1EjR~
    }                                                              //~1220I~//~v1EjI~
//***************                                                  //~1220I~//~v1EjI~
	public String getSelectedItem()                                //~1220I~//~v1EjI~
    {                                                              //~1220I~//~v1EjI~
    	String val=null;                                              //~v1EjI~
    	int idx=getSelectedIndex();                                //~v1EjI~
    	if (idx>=0 && idx<getItemCount())                          //~v1EjI~
	    	val=getItem(idx);                                      //~v1EjR~
        return val;                                                //~v1EjI~
    }                                                              //~1220I~//~v1EjI~
//***************                                                  //~1220I~//~v1EjI~
	public int getItemCount()                                      //~1220I~//~v1EjI~
    {                                                              //~1220I~//~v1EjI~
    	return adapter.getCount();                                    //~1220I~//~v1EjI~
    }                                                              //~1220I~//~v1EjI~
//***************                                                  //~1220I~//~v1EjI~
	public String getItem(int Ppos)                                //~1220I~//~v1EjI~
    {                                                              //~1220I~//~v1EjI~
    	return (String)adapter.getItem(Ppos);                                     //~1220I~//~v1EjI~
    }                                                              //~1220I~//~v1EjI~
//***************                                                  //~1306I~//~v1EjI~
    public void select(int Pindex)                                 //~1306I~//~v1EjI~
    {                                                              //~1306I~//~v1EjI~
        if (Dump.Y) Dump.println("USpinner.select idx="+Pindex+",spListener==null:"+(spListener==null));//~9228I~
//        if (spListener==null)                                    //~9228R~
//            spinner.setSelection(Pindex,false);                  //~9228R~
//        else                                                     //~9228R~
	    	spinner.setSelection(Pindex);                   //~1306R~  //~v1EjI~//~9228R~
    }                                                              //~1306I~//~v1EjI~
//*************************************************************    //~9228I~
//*with option to do not call onItemSelected                       //~9228I~
//*************************************************************    //~9228I~
    public void selectNoListen(int Pindex)                         //~9228R~
    {                                                              //~9228I~
        if (Dump.Y) Dump.println("USpinner.selectNoListen splistener==null?"+(spListener==null)+",idx="+Pindex+",id="+Integer.toHexString(spinner.getId()));//~9228R~
		if (spListener!=null)                                      //~9228I~
	    	setListener(null);                                     //~9228I~
	    spinner.setSelection(Pindex,false);                        //~9228R~
		if (spListener!=null)                                      //~9228I~
	    	setListener(spListener);                               //~9228I~
    }                                                              //~9228I~
//***************                                                  //~9226I~
    public void select(int Pindex,boolean PswFixed)                //~9226I~
    {                                                              //~9226I~
        if (Dump.Y) Dump.println("USpinner.select idx="+Pindex+",swfixed="+PswFixed+",id="+Integer.toHexString(spinner.getId()));//~9226I~//~9228R~
    	spinner.setSelection(Pindex);                              //~9226I~
        swFixed=PswFixed;                                          //~9226I~
        idxFixed=Pindex;                                        //~9226I~
        if (PswFixed)                                              //~9226I~
        {                                                          //~9226I~
	        if (spListener==null)                                  //~9226I~
				setListener(createListener());                     //~9226I~
        }                                                          //~9226I~
    }                                                              //~9226I~
//***************                                                  //~1306I~//~v1EjI~
    public int getSelectedIndex()                                  //~1306R~//~v1EjI~
    {                                                              //~1306I~//~v1EjI~
    	return spinner.getSelectedItemPosition();                  //~1306R~//~v1EjI~
    }                                                              //~1306I~//~v1EjI~

//*****************************************************************************//~v1EjI~
    public void setArray(int Presid)                               //~v1EjI~
    {                                                              //~v1EjI~
        if (Dump.Y) Dump.println("USpinner.setArray id="+Integer.toHexString(Presid));//~9413I~
	    String[] sa=AG.resource.getStringArray(Presid);            //~v1EjI~
	    setArray(sa);                                               //~9413I~
    }                                                              //~v1EjI~
//*****************************************************************************//~9413I~
    public void setArray(String[] Pitems)                          //~9413I~
    {                                                              //~9413I~
        if (Dump.Y) Dump.println("USpinner.setArray ="+Arrays.toString(Pitems));//~9413I~
        for (int ii=0;ii<Pitems.length;ii++)                       //~9413I~
		{                                                          //~9413I~
        	add(Pitems[ii]);                                       //~9413I~
        }                                                          //~9413I~
    }                                                              //~9413I~
//*****************************************************************************//~v1EjI~
    public OnItemSelectedListener createListener()                 //~v1EjI~
    {                                                              //~v1EjI~
	    OnItemSelectedListener l=                                  //~v1EjI~
            new OnItemSelectedListener()                             //~v1EjI~
            {                                                      //~v1EjI~
    	        @Override                                          //~v1EjI~
                public void onItemSelected(AdapterView<?> Pparent,View Pview,int Ppos,long Pid)//~v1EjI~
                {                                                  //~v1EjI~
                    try                                            //~9228I~
                    {                                              //~9228I~
				        if (Dump.Y) Dump.println("USpinner.onItemSelected swFixed="+swFixed+",idxFixed="+idxFixed+",pos="+Ppos+",parm="+USParm+",id="+Long.toHexString(Pid)+",viewid="+Integer.toHexString(Pview.getId())+",spinnerID="+Integer.toHexString(spinner.getId()));//~v1EjR~//~9226R~//~9228I~
                        if (swFixed)                                   //~9226I~//~9228R~
                        {                                              //~9226I~//~9228R~
                            if (Ppos!=idxFixed)                        //~9226I~//~9228R~
                            {                                          //~9226I~//~9228R~
                                select(idxFixed);                       //~9226I~//~9228R~
                                UView.showToast(R.string.Err_StatusFixed);//~v1EjI~//~9226I~//~9228R~
                            }                                          //~9226I~//~9228R~
                        }                                              //~9226I~//~9228R~
                        else                                           //~9226I~//~9228R~
                        {                                          //~9903I~
                        	if (listener!=null)                        //~9902I~//~9903R~
	                        	listener.onItemSelectedUS(spinner.getId(),Ppos,USParm);        //~v1EjR~//~9228R~//~9903R~
                        	else                                       //~9902I~//~9903R~
                            {                                      //~9903I~
	                            if (Ppos!=idxFixed)	//changed from setting by dialog//~9903I~
    	                        	CommonListener.onItemSelectedUS(spinner.getId(),Ppos);//~9902I~//~9903R~
                            }                                      //~9903I~
                        }                                          //~9903I~
						if (Dump.Y) Dump.println("USpinner.createListener swSetBG="+swSetBG+",view="+Pview+",Pid="+Pid);//~vakQI~
                        if (swSetBG)                               //~vakQI~
                        {                                          //~vakQI~
							if (Dump.Y) Dump.println("USpinner.createListener setBackGround pos="+Ppos);//~vakQI~
                        	Pview.setBackgroundColor(colorBG);     //~vakQI~
                        }                                          //~vakQI~
                        	                                       //~vakQI~
                    }                                              //~9228I~
                    catch(Exception e)                             //~9228I~
                    {                                              //~9228I~
                        Dump.println(e,"USpinner.onItemSelected"); //~9228I~
                    }                                              //~9228I~
                }                                                  //~v1EjI~
    	        @Override                                          //~v1EjI~
                public void onNothingSelected(AdapterView<?> Pparent)   //~v1EjI~
                {                                                  //~v1EjI~
                    try                                            //~9228I~
                    {                                              //~9228I~
                        if (Dump.Y) Dump.println("USpinner.onNothingSelected parm="+USParm);//~v1EjI~//~9228I~
                        if (listener!=null)                        //~9902I~
                        	listener.onNothingSelectedUS(USParm);                        //~v1EjI~//~9228R~//~9902R~
                        else                                       //~9902I~
                            CommonListener.onNothingSelectedUS();  //~9902I~
                    }                                              //~9228I~
                    catch(Exception e)                             //~9228I~
                    {                                              //~9228I~
                        Dump.println(e,"USpinner.onNothingSelected");//~9228I~
                    }                                              //~9228I~
                }                                                  //~v1EjI~
            };                                                     //~v1EjI~
        spListener=l;                                              //~v1EjI~
		if (Dump.Y) Dump.println("USpinner.createListener listener="+l.toString());//~v1EjI~
        return l;
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    private void setListener(OnItemSelectedListener Plistener)     //~v1EjI~
    {                                                              //~v1EjI~
    	spinner.setOnItemSelectedListener(Plistener);              //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setListener(USpinnerI PUSlistener,int Pparm)       //~v1EjI~
    {                                                              //~v1EjI~
    	listener=PUSlistener;                                      //~v1EjI~
        USParm=Pparm;                                              //~v1EjI~
        if (spListener==null)                                      //~v1EjI~
			setListener(createListener());                         //~v1EjI~
        if (Dump.Y) Dump.println("USpinner.setListener parm="+Pparm);//~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setEnabled(boolean Penable)                         //~v1EjI~
    {                                                              //~v1EjI~
    	spinner.setEnabled(Penable);                                //~v1EjI~
        if (Dump.Y) Dump.println("USpinner.setEnable sw="+Penable);//~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setVisibility(int Pvisibility)                     //~v1EjI~
    {                                                              //~v1EjI~
    	spinner.setVisibility(Pvisibility);                        //~v1EjI~
        if (Dump.Y) Dump.println("USpinner.setVisibility ="+Pvisibility);//~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~9228I~
    public int getId()                                             //~9228I~
    {                                                              //~9228I~
    	int id=spinner.getId();                                    //~9228I~
        if (Dump.Y) Dump.println("USpinner.getId id="+Integer.toHexString(id));//~9228R~
        return id;                                                 //~9228I~
    }                                                              //~9228I~
    //**********************************************               //~vakQI~
    public void setBackgroundColor(int Pcolor)                     //~vakQI~
    {                                                              //~vakQI~
    	swSetBG=true;                                              //~vakQI~
    	colorBG=Pcolor;                                            //~vakQI~
    	int pos=getSelectedIndex();                                //~vakQI~
        if (Dump.Y) Dump.println("USpinner.setBackgroundColor pos="+pos+",color="+Integer.toHexString(Pcolor));//~vakQI~
    	select(pos,true/*PswFixed*/);                               //~vakQI~
    }                                                              //~vakQI~
}//class                                                           //~1112I~//~v1EjI~
