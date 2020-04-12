//*CID://+v@@@R~: update#= 200;                                    //~1Ac0R~//~v@@@R~
//**********************************************************************//~1107I~
//**********************************************************************//~1107I~//~v106M~
package com.btmtest.utils;                                               //~1Ad8I~//~1Ac0I~//~v@@@R~

import de.greenrobot.event.EventBus;                               //~v@@@I~
                                                                   //~v@@@I~
import android.os.Bundle; //~v@@@I~

//**********************************************************       //~v@@@I~
public class EventCB                                               //~v@@@R~
{                                                                  //~v@@@R~
	private static final String PARM_INT1="parmInt1";              //~v@@@R~
	private static final String PARM_INT2="parmInt2";              //~v@@@I~
	private static final String PARM_INT3="parmInt3";              //~v@@@R~
	private static final String PARM_STR="parmStr";                //~v@@@I~
    public int action;                                            //~v@@@R~
    public Bundle bundle;                                          //~v@@@I~
//**********************************************************       //~v@@@I~
    public EventCB()                                               //~v@@@R~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
    public EventCB(int Paction)                                    //~v@@@I~
    {                                                              //~v@@@I~
    	this(Paction,null);                                        //~v@@@I~
    }                                                              //~v@@@I~
    public EventCB(int Paction,Bundle Pbundle)                     //~v@@@I~
    {                                                              //~v@@@I~
    	action=Paction; bundle=Pbundle;                             //~v@@@I~
    }                                                              //~v@@@I~
    public static Bundle newBundle(String Pintkey1,int Pintval1,String Pintkey2,int Pintval2,String Pintkey3,int Pintval3,String Pstrkey,String Pstrval)//~v@@@R~
    {                                                              //~v@@@I~
    	Bundle b=new Bundle();                                     //~v@@@I~
        if (Pintkey1!=null)                                        //~v@@@R~
        	b.putInt(Pintkey1,Pintval1);                           //~v@@@R~
        if (Pintkey2!=null)                                        //~v@@@I~
        	b.putInt(Pintkey2,Pintval2);                           //~v@@@I~
        if (Pintkey3!=null)                                        //~v@@@I~
        	b.putInt(Pintkey3,Pintval3);                           //~v@@@I~
        if (Pstrkey!=null)                                         //~v@@@R~
        	b.putString(Pstrkey,Pstrval);                          //~v@@@R~
        return b;//~v@@@I~
    }                                                              //~v@@@I~
    public static Bundle newBundle(String Pintkey1,int Pintval1,String Pintkey2,int Pintval2,String Pstrkey,String Pstrval)//~v@@@I~
    {                                                              //~v@@@I~
	    return newBundle(Pintkey1,Pintval1,Pintkey2,Pintval2,null,0,Pstrkey,Pstrval);//~v@@@I~
    }                                                              //~v@@@I~
    public static Bundle newBundle(String Pintkey1,int Pintval1,String Pstrkey,String Pstrval)//~v@@@I~
    {                                                              //~v@@@I~
	    return newBundle(Pintkey1,Pintval1,null,0,null,0,Pstrkey,Pstrval);//~v@@@I~
    }                                                              //~v@@@I~
    public static Bundle newBundle(String Pintkey1,int Pintval1)   //~v@@@I~
    {                                                              //~v@@@I~
	    return newBundle(Pintkey1,Pintval1,null,0,null,0,null,null);//~v@@@I~
    }                                                              //~v@@@I~
    public static Bundle newBundle(int Pintval1)                   //~v@@@I~
    {                                                              //~v@@@I~
	    return newBundle(PARM_INT1,Pintval1,null,0,null,0,null,null);//~v@@@I~
    }                                                              //~v@@@I~
    //************************************************************************//~v@@@I~
    //*EventMsg override this                                      //~v@@@R~
    //************************************************************************//~v@@@I~
    public void postEvent()                                        //~v@@@R~
    {                                                              //~v@@@I~
    	EventBus.getDefault().post(this);                          //~v@@@R~
    }                                                              //~v@@@I~
    //************************************************************************//~v@@@I~
    public static void postEvent(int Pactionid,int Pintval1,int Pintval2,int Pintval3,String Pstrval)//~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("EventCB postEvent intval1="+Pintval1+",intval2="+Pintval2+",intval3="+Pintval3+",strval="+Pstrval);//~v@@@I~
    	Bundle b=newBundle(PARM_INT1,Pintval1,PARM_INT2,Pintval2,PARM_INT3,Pintval3,PARM_STR,Pstrval);//~v@@@I~
    	new EventCB(Pactionid,b).postEvent();                      //~v@@@R~
    }                                                              //~v@@@I~
    public static void postEvent(int Pactionid,int Pintval1,int Pintval2,String Pstrval)//~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("EventCB postEvent intval1="+Pintval1+",intval2="+Pintval2+",strval="+Pstrval);//~v@@@I~
    	Bundle b=newBundle(PARM_INT1,Pintval1,PARM_INT2,Pintval2,PARM_STR,Pstrval);//~v@@@I~
    	new EventCB(Pactionid,b).postEvent();                      //~v@@@I~
    }                                                              //~v@@@I~
    public static void postEvent(int Pactionid,int Pintval1)       //+v@@@I~
    {                                                              //+v@@@I~
    	if (Dump.Y) Dump.println("EventCB postEvent actionid="+Pactionid+",intval1="+Pintval1);//+v@@@I~
    	Bundle b=newBundle(PARM_INT1,Pintval1);                    //+v@@@I~
    	new EventCB(Pactionid,b).postEvent();                      //+v@@@I~
    }                                                              //+v@@@I~
    public static void postEvent(int Pactionid,int Pintval1,String Pstrval)//~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("EventCB postEvent intval1="+Pintval1+",strval="+Pstrval);//~v@@@I~
    	Bundle b=newBundle(PARM_INT1,Pintval1,PARM_STR,Pstrval);   //~v@@@I~
    	new EventCB(Pactionid,b).postEvent();                      //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    public int getParmInt1()                                        //~v@@@I~
    {                                                              //~v@@@I~
    	int ii=bundle.getInt(PARM_INT1,-1);                         //~v@@@R~
    	if (Dump.Y) Dump.println("EventCB getParmInt intval="+ii); //~v@@@I~
    	return ii;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public int getParmInt2()                                       //~v@@@I~
    {                                                              //~v@@@I~
    	int ii=bundle.getInt(PARM_INT2,-1);                        //~v@@@I~
    	if (Dump.Y) Dump.println("EventCB getParmInt2 intval="+ii);//~v@@@I~
    	return ii;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public String getParmStr()                                     //~v@@@I~
    {                                                              //~v@@@I~
    	String str=bundle.getString(PARM_STR,null);                   //~v@@@I~
    	if (Dump.Y) Dump.println("EventCB getParmInt strval="+(str==null?"null":str));//~v@@@I~
    	return str;                                                //~v@@@I~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
