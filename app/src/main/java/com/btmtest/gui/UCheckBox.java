//*CID://+v1EjR~:                             update#=   31;       //~v1EjI~
//**************************************************************************//~v1EjI~
//**************************************************************************//~v1EjI~
package com.btmtest.gui;                                           //~1215R~//~v1EjI~
                                                                   //~v1EjI~
import android.view.View;
import android.widget.CheckBox;                                    //~v1EjI~
import android.widget.CompoundButton;

import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
//+v1EjI~
public class UCheckBox                                             //~v1EjI~
{                                                                  //~1215R~//~v1EjI~
	public CheckBox checkbox;                                      //~v1EjR~
    private UCheckBoxI listener;                                   //~v1EjI~
    private CompoundButton.OnCheckedChangeListener cbListener;     //~v1EjI~
    private int UCBParm;                                           //~v1EjI~
    private boolean swFixed,swFixedStatus;                         //~v1EjI~
//*******************************                                  //~v1EjI~
    public interface UCheckBoxI                                      //~v@@@I~//~v1EjI~
    {                                                              //~v@@@I~//~v1EjI~
        void onChangedUCB(CompoundButton Pbtn, boolean PisChecked,int UCBParm);                        //~v@@@R~//~v1EjR~
    }                                                              //~v@@@I~//~v1EjI~
//*******************************                                  //~v1EjI~
    public UCheckBox(View Playout, int Pid)                         //~v1EjI~
    {                                                              //~v1EjI~
		checkbox=(CheckBox)UView.findViewById(Playout,Pid);        //~v1EjI~
		setListener(createListener());                             //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.constructor chkbox=null?"+(checkbox==null)+",id="+Integer.toHexString(Pid));//~v1EjI~
    }                                                              //~v1EjI~
//*******************************                                  //~v1EjI~
    public UCheckBox(View Playout, int Pid,UCheckBoxI PUCBlistener)//~v1EjI~
    {                                                              //~v1EjI~
		checkbox=(CheckBox)UView.findViewById(Playout,Pid);        //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.constructor with listener parm chkbox=null?"+(checkbox==null)+",id="+Integer.toHexString(Pid));//~v1EjI~
    	setListener(PUCBlistener,-1/*Pparm*/);                      //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public boolean getState()                                      //~1215I~//~v1EjI~
    {                                                              //~1215I~//~v1EjI~
		boolean rc=checkbox.isChecked();                                        //~1215I~//~1328R~//~v1EjR~
        if (Dump.Y) Dump.println("UCheckBox.getState rc="+rc);     //~v1EjI~
		return rc;                                                 //~v1EjI~
    }                                                              //~1215I~//~v1EjI~
    //**********************************************               //~v1EjI~
    public int getStateInt()                                       //~v1EjI~
    {                                                              //~v1EjI~
		return (getState() ? 1 : 0);                               //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setState(boolean Pstat)                            //~1215I~//~v1EjI~
    {                                                              //~1215I~//~v1EjI~
		checkbox.setChecked(Pstat);                                  //~1215I~//~1328R~//~v1EjI~
    }                                                              //~1215I~//~v1EjI~
    //**********************************************               //~v1EjI~
    public void setState(boolean Pstat,boolean PswFixed)           //~v1EjI~
    {                                                              //~v1EjI~
		checkbox.setChecked(Pstat);                                //~v1EjI~
        fixStatus(Pstat,PswFixed);                                   //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setFixed(boolean PswFixed)                         //~v1EjI~
    {                                                              //~v1EjI~
		swFixed=PswFixed;                                          //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.setFixed swFixed="+PswFixed);//~v1EjR~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public boolean getFixed()                                      //~v1EjR~
    {                                                              //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.gsetFixed swFixed="+swFixed);//~v1EjI~
		return swFixed;                                            //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    private void fixStatus(boolean Pstat,boolean PswFixed)         //~v1EjI~
    {                                                              //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox fixStatus swFixed="+PswFixed+",status="+Pstat);//~v1EjR~
    	swFixed=PswFixed;                                          //~v1EjI~
    	swFixedStatus=Pstat;                                       //~v1EjI~
    	if (!swFixed)                                              //~v1EjI~
        	return;                                                //~v1EjI~
        if (cbListener==null)                                      //~v1EjI~
			setListener(createListener());                         //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setStateInt(int Pstat)                             //~v1EjR~
    {                                                              //~v1EjI~
		setState(Pstat!=0);                                        //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setStateInt(int Pstat,boolean PswFixed)            //~v1EjI~
    {                                                              //~v1EjI~
		setState(Pstat!=0,PswFixed);                               //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    private CompoundButton.OnCheckedChangeListener createListener() //~v1EjR~
    {                                                              //~v1EjI~
    	CompoundButton.OnCheckedChangeListener l=                  //~v1EjI~
    		new CompoundButton.OnCheckedChangeListener()           //~v1EjI~
        	{                                                      //~v1EjI~
            	@Override                                          //~v1EjI~
				public void onCheckedChanged(CompoundButton Pbtn,boolean PisChecked)//~v1EjI~
                {                                                  //~v1EjI~
                	try                                            //~v1EjI~
                    {                                              //~v1EjI~
                        if (Dump.Y) Dump.println("UCheckBox onCheckedChanged swFixed="+swFixed+",swFixedStatus="+swFixedStatus+",Pchecked="+PisChecked+",btn="+Integer.toHexString(Pbtn.getId())+",ischecked="+PisChecked+",listener="+(listener==null? "null" : listener.toString()));//~v1EjR~
                        if (swFixed)                               //~v1EjR~
                        {                                          //~v1EjR~
                            if (PisChecked!=swFixedStatus)         //~v1EjR~
                            {                                      //~v1EjR~
                                setState(swFixedStatus);           //~v1EjR~
                                UView.showToast(R.string.Err_StatusFixed);//~v1EjR~
                            }                                      //~v1EjR~
                        }                                          //~v1EjR~
                        else                                       //~v1EjR~
                        if (listener!=null)                        //~v1EjR~
                            listener.onChangedUCB(Pbtn,PisChecked,UCBParm);//~v1EjR~
                        else                                       //~v1EjI~
                        	CommonListener.onChangedCB(Pbtn,PisChecked);//~v1EjI~
                    }                                              //~v1EjI~
                    catch(Exception e)                             //~v1EjI~
                    {                                              //~v1EjI~
                        Dump.println(e,"UCheckBox.onCheckedChanged");//~v1EjI~
                    }                                              //~v1EjI~
                }                                                  //~v1EjI~
            };
        if (Dump.Y) Dump.println("UCheckBox.createListener");      //~v1EjI~
        cbListener=l;                                              //~v1EjI~
        return l;                                                  //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    private void setListener(CompoundButton.OnCheckedChangeListener Plistener)//~v1EjI~
    {                                                              //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.setListener native listener="+Plistener.toString());//~v1EjI~
    	checkbox.setOnCheckedChangeListener(Plistener);            //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setListener(UCheckBoxI PUCBlistener,int Pparm)     //~v1EjR~
    {                                                              //~v1EjI~
    	listener=PUCBlistener;                                     //~v1EjR~
        UCBParm=Pparm;                                             //~v1EjI~
        if (cbListener==null)                                      //~v1EjI~
			setListener(createListener());                         //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.setListener PUCBListener parm="+Pparm);//~v1EjR~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setEnabled(boolean Penable)                        //~v1EjI~
    {                                                              //~v1EjI~
    	checkbox.setEnabled(Penable);                              //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.setEnabled sw="+Penable);//~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public boolean isEnabled()                                     //~v1EjI~
    {                                                              //~v1EjI~
    	boolean rc=checkbox.isEnabled();                           //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.isEnabled rc="+rc);    //~v1EjI~
        return rc;                                                 //~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //~v1EjI~
    public void setStatusWithoutCallback(boolean PswChecked)       //~v1EjI~
    {                                                              //~v1EjI~
    	boolean oldstat=checkbox.isEnabled();                      //~v1EjI~
    	setEnabled(false);                                         //~v1EjI~
    	setState(PswChecked);                            //~v1EjI~
    	setEnabled(oldstat);                                       //~v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.setStatus sw="+PswChecked);//~v1EjI~
    }                                                              //~v1EjI~
    //**********************************************               //+v1EjI~
    public void setText(String Ptext)                              //+v1EjI~
    {                                                              //+v1EjI~
    	checkbox.setText(Ptext);                                   //+v1EjI~
        if (Dump.Y) Dump.println("UCheckBox.setText txt="+Ptext);  //+v1EjI~
    }                                                              //+v1EjI~
//    //**********************************************             //~v1EjR~
//    public void setListener(CompoundButton.OnCheckedChangeListener Plistener,UCheckBoxI PUCBlistener,int Pparm)//~v1EjR~
//    {                                                            //~v1EjR~
//        setListener(Plistener);                                  //~v1EjR~
//        setListener(PUCBlistener,Pparm);                         //~v1EjR~
//    }                                                            //~v1EjR~
}                                                                  //~v1EjI~
