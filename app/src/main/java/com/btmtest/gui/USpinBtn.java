//*CID://+vakQR~:                             update#=   43;       //+vakQR~
//**************************************************************************//~1B0bI~//~v1EjI~
//2022/03/19 vakQ notify update of rule when client received       //+vakQI~
//**************************************************************************//~1B0bI~//~v1EjI~
package com.btmtest.gui;                                           //~v1EjI~

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v1EjI~

public class USpinBtn implements View.OnClickListener              //~9408R~
{                                                                  //~1112I~//~v1EjI~
    private static final int ID_BTNUP=R.id.btnSpinUp;              //~9412I~
    private static final int ID_BTNDOWN=R.id.btnSpinDown;          //~9412R~
    private static final int ID_TEXTVIEW=R.id.tvSpinText;          //~9412I~
	private Button btnUp,btnDown;                                  //~9408R~
//  private TextView tvText;                                       //~9408I~//+vakQR~
    public  TextView tvText;                                       //+vakQI~
    private ArrayAdapter<CharSequence> adapter;                                  //~v1EjI~
    private int valMin,valMax,valInc,valInit;                      //~9408I~
    private int btnIDUp,btnIDDown;                                 //~9408I~
    private int valCurrent;                                        //~9408I~
//*******************************                                  //~v1EjI~
    public USpinBtn(Button Pup,Button Pdown,TextView Ptv,int Pmin,int Pmax,int Pinc,int Pinit)//~9408R~
    {                                                              //~1112I~//~v1EjI~
        if (Dump.Y) Dump.println("USpinBtn.constructor min="+Pmin+",max="+Pmax+",inc="+Pinc+",init="+Pinit);//~9412I~
		btnUp=Pup; btnDown=Pdown; tvText=Ptv;                      //~9408R~
    	valMin=Pmin; valMax=Pmax; valInc=Pinc; valInit=Pinit;      //~9408R~
        btnIDUp=btnUp.getId(); btnIDDown=btnDown.getId();           //~9408I~
        setButtonListener(btnUp);                                  //~9408I~
        setButtonListener(btnDown);                                //~9408I~
        valCurrent=valInit;                                        //~9408I~
        setText();                                                 //~9408R~
        if (Dump.Y) Dump.println("USpinBtn.constructor min="+Pmin+",max="+Pmax+",init="+Pinit+",inc="+Pinc);//~9408I~
    }                                                              //~1112I~//~v1EjI~
//*******************************                                  //~9408I~
    public static USpinBtn newInstance(View PView,int PidUp,int PidDown,int PidTextView,int Pmin,int Pmax,int Pinc,int Pinit)//~9408I~
    {                                                              //~9408I~
    	Button up=(Button)UView.findViewById(PView,PidUp);        //~9408I~
    	Button dn=(Button)UView.findViewById(PView,PidDown);      //~9408I~
    	TextView tv=(TextView)UView.findViewById(PView,PidTextView);//~9408I~
    	return new USpinBtn(up,dn,tv,Pmin,Pmax,Pinc,Pinit);       //~9408I~
    }                                                              //~9408I~
//*******************************                                  //~9412I~
    public static USpinBtn newInstance(View PView,int Pmin,int Pmax,int Pinc,int Pinit)//~9412I~
    {                                                              //~9412I~
	    return newInstance(PView,ID_BTNUP,ID_BTNDOWN,ID_TEXTVIEW,Pmin,Pmax,Pinc,Pinit);//~9412I~
    }                                                              //~9412I~
//*******************************                                  //~9412I~
    public void setMinEms(int PminEms)                             //~9412I~
    {                                                              //~9412I~
        if (Dump.Y) Dump.println("USpinBtn.setMinEms:"+PminEms);   //~9412I~
    	tvText.setMinEms(PminEms);                                     //~9412I~
    }                                                              //~9412I~
//*******************************                                  //~9408I~
    public int getVal()                                            //~9408I~
    {                                                              //~9408I~
        if (Dump.Y) Dump.println("USpinBtn.getVal rc="+valCurrent);//~9408I~
		return valCurrent;                                         //~9408I~
    }                                                              //~9408I~
//*******************************                                  //~9408I~
    public void setVal(int Pval)                                    //~9408I~
    {                                                              //~9408I~
        if (Dump.Y) Dump.println("USpinBtn.setVal val="+Pval);     //~9408I~
		valCurrent=Pval;                                           //~9408I~
        setText();                                                 //~9408I~
    }                                                              //~9408I~
//*******************************                                  //~9408I~
    public void setVal(int Pval,boolean PswFixed)                   //~9408I~
    {                                                              //~9408I~
        if (Dump.Y) Dump.println("USpinBtn.setVal val="+Pval+",swFixed="+PswFixed);//~9408I~
		setVal(Pval);                                              //~9408I~
        btnUp.setEnabled(!PswFixed);                               //~9408I~
        btnDown.setEnabled(!PswFixed);                             //~9408I~
    }                                                              //~9408I~
//*******************************                                  //~9408I~
    private void setText()                                          //~9408I~
    {                                                              //~9408I~
        if (Dump.Y) Dump.println("USpinBtn.setText val="+valCurrent);//~9408I~
        tvText.setText(Integer.toString(valCurrent));              //~9408I~
    }                                                              //~9408I~
//*******************************************************************//~9408I~
	public void setButtonListener(Button Pbutton)                  //~9408I~
    {                                                              //~9408I~
        if (Dump.Y) Dump.println("UButton:setButtonListener id="+Integer.toHexString(Pbutton.getId()));//+9408I~        Pbutton.setOnClickListener(this);                          //~9408I~
        Pbutton.setOnClickListener(this);                          //~9408I~
    }                                                              //~9408I~
//*******************************************************************//~9408I~
    @Override                                                      //~9408I~
    public void onClick(View Pv)                                   //~9408I~
    {                                                              //~9408I~
        try                                                        //~9408I~
        {                                                          //~9408I~
            onClickButton((Button)Pv);                             //~9408I~
        }                                                          //~9408I~
        catch(Exception e)                                         //~9408I~
        {                                                          //~9408I~
            Dump.println(e,"USpinBtn:OnClick");                    //~9408I~
        }                                                          //~9408I~
    }                                                              //~9408I~
//*******************************************************************//~9408I~
    private void onClickButton(Button Pbtn)                                     //~9408I~
    {                                                              //~9408I~
    	int dest=Pbtn.getId()==btnIDUp ? 1 : -1;                         //~9408I~
        int val=valCurrent+dest*valInc;                            //~9408I~
        if (val<valMin)                                            //~9408I~
        	val=valMin;                                            //~9408I~
        if (val>valMax)                                            //~9408I~
        	val=valMax;                                            //~9408I~
        valCurrent=val;                                            //~9408I~
        tvText.setText(Integer.toString(valCurrent));               //~9408I~
        if (Dump.Y) Dump.println("USpinBtn.onClickButton dest="+dest+",valCurrent="+valCurrent);//~9408I~
        CommonListener.onClickButtonUSB(Pbtn.getId());             //~9902M~
    }                                                              //~9408I~
}//class                                                           //~1112I~//~v1EjI~
