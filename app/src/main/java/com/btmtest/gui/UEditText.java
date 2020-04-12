//*CID://+v1EjR~:                             update#=   23;       //~v1EjI~
//**************************************************************************//~v1EjI~
//**************************************************************************//~v1EjI~
package com.btmtest.gui;                                           //~1215R~//~v1EjI~
                                                                   //~v1EjI~
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;                                    //~v1EjR~

import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

public class UEditText implements TextWatcher                      //~v1EjR~
{                                                                  //~1215R~//~v1EjI~
	private UEditTextI listener;//~v1EjR~
	public EditText editText;                                      //~v1EjR~
	private boolean swSetText;                                     //~v1EjI~
	private boolean swLostFocus;	//callback only when lostfocus //~v1EjR~
	private boolean swSetListener;                                 //+v1EjI~
//**************************************************************** //~v1EjI~
	public interface UEditTextI                                    //~v1EjI~
	{                                                              //~v1EjI~
    	void onTextChanged(UEditText Pedittext,String Ptext);      //~v1EjR~
	}                                                              //~v1EjI~
//***********************************************************      //~v1EjI~
    private UEditText(EditText Pedittext,UEditTextI Plistener)     //~v1EjR~
    {                                                              //~v1EjI~
        listener=Plistener;                                        //~v1EjI~
        editText=Pedittext;                                        //~v1EjI~
    }                                                              //~v1EjI~
//***********************************************************      //~v1EjR~
    public static UEditText bind(View Playout,int Pid,UEditTextI Plistener)//~v1EjR~
    {                                                              //~v1EjI~
		EditText editText=(EditText)UView.findViewById(Playout,Pid);        //~v1EjR~
        return setListener(editText,Plistener);                           //~v1EjI~
    }                                                              //~v1EjI~
//***********************************************************      //~v1EjI~
    private static UEditText setListener(EditText Pedittext,UEditTextI Plistener)//~v1EjR~
    {                                                              //~v1EjI~
	    UEditText uet=new UEditText(Pedittext,Plistener);          //~v1EjR~
        Pedittext.addTextChangedListener(uet);                     //~v1EjI~
        return uet;                                                //~v1EjI~
    }                                                              //~v1EjI~
//***********************************************************      //~v1EjI~
    public String getText()                                        //~v1EjI~
    {                                                              //~v1EjI~
        return editText.getText().toString();                             //~v1EjI~
    }                                                              //~v1EjI~
//***********************************************************      //~v1EjI~
    public void setText(String Ptext)                              //~v1EjR~
    {                                                              //~v1EjI~
    	swSetText=true;                                            //~v1EjI~
        editText.setText(Ptext);                                   //~v1EjI~
    	swSetText=false;                                           //~v1EjI~
    }                                                              //~v1EjI~
//***********************************************************      //~v1EjI~
    public void setText(String Ptext,boolean PswLostFocus)         //~v1EjI~
    {                                                              //~v1EjI~
		if (Dump.Y) Dump.println("UEditText.setText text="+Ptext+",swLostFocus="+PswLostFocus);//~v1EjI~
    	swSetText=true;                                            //~v1EjI~
        editText.setText(Ptext);                                   //~v1EjI~
    	swSetText=false;                                           //~v1EjI~
        swLostFocus=PswLostFocus;                                  //~v1EjI~
        if (swLostFocus)                                           //~v1EjI~
        {                                                          //~v1EjI~
        	setFocusListener();                                    //~v1EjI~
        }                                                          //~v1EjI~
    }                                                              //~v1EjI~
//***********************************************************      //~v1EjI~
    private void setFocusListener()                                 //~v1EjI~
    {                                                              //~v1EjI~
		if (Dump.Y) Dump.println("UEditText.setFocusListener");    //~v1EjI~
        if (swSetListener)                                         //+v1EjI~
            return;                                                //+v1EjI~
        swSetListener=true;                                        //+v1EjI~
        editText.setOnFocusChangeListener(                           //~v1EjI~
        	new View.OnFocusChangeListener()                            //~v1EjI~
            {                                                      //~v1EjI~
            	@Override                                          //~v1EjI~
                public void onFocusChange(View v,boolean hasFocus) //~v1EjI~
                {                                                  //~v1EjI~
			        if (Dump.Y) Dump.println("UEditText.onFocusChanged hasFocus="+hasFocus);//~v1EjI~
                	if (!hasFocus)                                 //~v1EjI~
                    {                                              //~v1EjI~
                    	String txt=getText();                      //~v1EjI~
				        if (Dump.Y) Dump.println("UEditText.onFocusChanged lostFocus text="+txt);//~v1EjI~
                        textChanged(txt);                          //~v1EjI~
                    }                                              //~v1EjI~
                }                                                  //~v1EjI~
            });                                                    //~v1EjI~
    }                                                              //~v1EjI~
//***********************************************************      //~v1EjI~
    public void setEnabled(boolean Penable)                        //~v1EjI~
    {                                                              //~v1EjI~
        editText.setEnabled(Penable);                              //~v1EjI~
    }                                                              //~v1EjI~
//***********************************************************      //~v1EjI~
    private void textChanged(String Ptext)
    {
    	try                                                        //~v1EjI~
        {                                                          //~v1EjI~
			if (Dump.Y) Dump.println("UEditText.textChanged swSetText="+swSetText+",listener="+ Utils.toString(listener));//~v1EjI~
        	if (!swSetText)                                        //~v1EjR~
            	if (listener!=null)                                //~v1EjI~
		        	listener.onTextChanged(this,Ptext);            //~v1EjR~
                else                                               //~v1EjI~
                	CommonListener.onTextChanged(editText,Ptext);  //~v1EjI~
        }                                                          //~v1EjI~
        catch(Exception e)                                         //~v1EjI~
        {                                                          //~v1EjI~
            Dump.println(e,"UEditText.textChanged");               //~v1EjI~
        }                                                          //~v1EjI~
    }//~v1EjM~
//***********************************************************      //~v1EjI~
    @Override //TextWatcher                                        //~v1EjR~
    public void beforeTextChanged(CharSequence Pcs,int Pstart,int Pctr,int Pacter)//~v1EjR~
    {                                                              //~v1EjR~
    }                                                              //~v1EjR~
    @Override //TextWatcher                                        //~v1EjI~
    public void onTextChanged(CharSequence Pcs,int Pstart,int Pctr,int Pacter)//~v1EjR~
    {                                                              //~v1EjR~
    }                                                              //~v1EjR~
    @Override //TextWatcher                                        //~v1EjI~
    public void afterTextChanged(Editable Peditable)               //~v1EjR~
    {                                                              //~v1EjR~
        String text=Peditable.toString();                          //~v1EjR~
        if (Dump.Y) Dump.println("UEditText onTextChanged afterTextChanged text="+text+",swLostFocus="+swLostFocus);//~v1EjR~
        if (!swLostFocus)                                          //~v1EjR~
		    textChanged(text);                                     //~v1EjR~
    }                                                              //~v1EjR~
}                                                                  //~v1EjI~
