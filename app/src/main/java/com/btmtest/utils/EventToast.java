//*CID://+vaf0R~: update#= 187;                                    //+vaf0R~
//**********************************************************************//~1107I~
//2021/10/21 vaf0 Play console crash report "IllegalStateException" at FragmentManagerImple.1536(checkStateLoss)//+vaf0I~
//@@01 20181105 for BTMJ3                                            //~v@@@I~
//**********************************************************************//~1107I~//~v106M~
package com.btmtest.utils;                                               //~1Ad8I~//~1Ac0I~//~v@@@R~

import android.content.Context;
import android.widget.Toast;                                       //~v@@@I~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@01I~

//**********************************************************       //~v@@@I~
public class EventToast	                                           //~v@@@R~
{                                                                  //~v@@@R~
    	private String msg;                                        //~v@@@I~
    	private Boolean swLong;                                    //~v@@@I~
    	public EventToast(String Pmsg,Boolean PswLong)             //~v@@@I~
        {                                                          //~v@@@I~
        	msg=Pmsg; swLong=PswLong;	                           //~v@@@I~
        }                                                          //~v@@@I~
    	public void showToast()                                         //~v@@@I~
        {                                                          //~v@@@I~
            if (swLong)                                            //~1514I~//~v@@@I~
		        Toast.makeText(AG.context,msg,Toast.LENGTH_LONG).show();//~1514I~//~v@@@I~
            else                                                   //~1514I~//~v@@@I~
		        Toast.makeText(AG.context,msg,Toast.LENGTH_SHORT).show();//~1514R~//~v@@@I~
        }                                                          //~v@@@I~
    	public void showToast(Context Pcontext)                    //+vaf0I~
        {                                                          //+vaf0I~
            if (swLong)                                            //+vaf0I~
		        Toast.makeText(Pcontext,msg,Toast.LENGTH_LONG).show();//+vaf0I~
            else                                                   //+vaf0I~
		        Toast.makeText(Pcontext,msg,Toast.LENGTH_SHORT).show();//+vaf0I~
        }                                                          //+vaf0I~
    	public String toString()                                   //~v@01I~
        {                                                          //~v@01I~
		    return "swLong="+swLong+",msg="+msg;                  //~v@01I~
        }                                                          //~v@01I~
}//class                                                           //~v@@@R~
