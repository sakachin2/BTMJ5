//*CID://+v@@@R~: update#= 207;                                    //~1Ac0R~//~v@@@R~
//**********************************************************************//~1107I~
//**********************************************************************//~1107I~//~v106M~
package com.btmtest;                                               //~v@@@I~
import com.btmtest.utils.Dump;                                     //~v@@@I~
import com.btmtest.utils.EventCB;                                  //~v@@@I~

//**********************************************************       //~v@@@I~
public class EventMsg extends EventCB                              //~v@@@R~
{                                                                  //~v@@@R~
	public static final int ACTION_SETTINGS=1;                     //~v@@@I~
	public static final int ACTION_GETCTLMSG=2;                    //~v@@@R~
	public static final int ACTION_PUTCTLMSG=3;                    //~v@@@I~
	public static final int ACTION_RECEIVED =4;                    //~v@@@R~
	public static final int ACTION_SEND     =5;                    //~v@@@R~
                                                                   //~v@@@I~
//**********************************************************       //~v@@@I~
	//**********************************************************   //~v@@@I~
    public static void postReadMsg(int Pintval,String Pstrval)     //~v@@@R~
    {                                                              //~v@@@I~
    	postEvent(ACTION_GETCTLMSG,Pintval,Pstrval);               //~v@@@R~
    }                                                              //~v@@@I~
    public static void postReceivedMsg(int Pthreadid,int Pmsgid,String Pmsg)//~v@@@R~
    {                                                              //~v@@@I~
    	postEvent(ACTION_RECEIVED,Pthreadid,Pmsgid,Pmsg);          //~v@@@I~
    }                                                              //~v@@@I~
    public static void postSendMsg(int Pthreadid,int Pmsgid,String Pmsg)//~v@@@I~
    {                                                              //~v@@@I~
    	postEvent(ACTION_SEND,Pthreadid,Pmsgid,Pmsg);              //~v@@@I~
    }                                                              //~v@@@I~
    public static int getThreadid(EventCB Pevent)                  //~v@@@R~
    {                                                              //~v@@@I~
    	int ii=Pevent.getParmInt1();                               //~v@@@R~
    	if (Dump.Y) Dump.println("EventMsg getThreadid="+ii);      //~v@@@I~
    	return ii;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public static int getMsgid(EventCB Pevent)                     //~v@@@R~
    {                                                              //~v@@@I~
    	int ii=Pevent.getParmInt2();                               //~v@@@R~
    	if (Dump.Y) Dump.println("EventMsg getMsgid="+ii);         //~v@@@I~
    	return ii;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public static String getMsg(EventCB Pevent)                    //~v@@@R~
    {                                                              //~v@@@I~
    	String msg=Pevent.getParmStr();                            //~v@@@R~
    	if (Dump.Y) Dump.println("EventMsg getMsg="+msg);          //~v@@@I~
    	return msg;                                                 //~v@@@I~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
