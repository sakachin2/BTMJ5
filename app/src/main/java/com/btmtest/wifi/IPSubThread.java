//*CID://+v@11R~: update#= 410;                                    //~v@21R~//~v@11R~
//**********************************************************************//~v101I~
//v@11 2019/02/02 TakeOne by touch                                 //~v@11I~
//v@21  imageview                                                  //~v@21I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.wifi;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import static com.btmtest.game.GCMsgID.*;                          //~v@@@R~

import com.btmtest.utils.Dump;
import com.btmtest.utils.UHandler;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@21I~

public class IPSubThread extends UHandler                      //~v@@@R~//~v@11R~
{                                                                  //~0914I~
    private static final String IPST_IDXTHREAD="idxThread";        //~v@11I~
    private static final String IPST_MSGDATA="msgData";            //~v@11I~
                                                                   //~v@@@I~
    private Looper looper;                                         //~v@11I~
    private int msg_idxThread;                                     //~v@11I~
    private String msg_msgData;                                    //~v@11I~
	//*************************                                        //~v@@@I~//~v@11R~
    public IPSubThread(Looper Plooper)                             //~v@11I~
    {                                                              //~v@11I~
        super(Plooper);                                             //~v@11I~
        AG.aIPSubThread=this;                                      //~v@11I~
        if (Dump.Y) Dump.println("IPSubThread Constructor");       //~v@11I~
    }                                                              //~v@11I~
    //****************************************                     //~v@11I~
    public static void newInstance()                               //~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("IPSubThread.newInstance");       //~v@11I~
	    HandlerThread handlerThread=new HandlerThread(Utils.getClassName(AG.aIPMulti));//~v@11I~
        handlerThread.start();        //do run()                   //~v@11I~
	    Looper looper=handlerThread.getLooper();                          //~v@11I~
        IPSubThread st=new IPSubThread(looper);                //~v@11I~
	    st.onResume();                                             //~v@11I~
    }                                                              //~v@11I~
    //****************************************                 //~@003I~//~v@@@I~
    @Override	//UHandler                                                  //~@003I~//~v@@@I~
    public boolean storeMsg(Message Pmsg)                             //~@003I~//~v@@@I~
    {                                                          //~@003I~//~v@@@I~
        return true; //allow store when paused                 //~@003I~//~v@@@I~
    }                                                          //~@003I~//~v@@@I~
    //****************************************                     //~v@@@I~
    public void onResume()                                         //~v@@@I~
    {                                                              //~v@@@I~
    	super.onResume(AG.activity);	//UHandler                             //~v@@@R~
    }                                                              //~v@@@I~
    //****************************************                     //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
    	super.onDestroy();	//UHandler                 //~v@@@I~
    }                                                              //~v@@@I~
	//*************************                                        //~1109I~//~1111I~//~1122M~//~v@@@R~
    @Override   //UHandler                                         //~v@@@R~
	public void handleMsg(Message Pmsg)                            //~v@@@R~
    {                                                              //~1120I~//~1122M~
        try                                                        //~1109I~//~1120M~//~1122M~
        {                                                          //~1109I~//~1120M~//~1122M~
	    	handleMsgIP(Pmsg);                                     //~v@@@R~//~v@11R~
        }                                                          //~1109I~//~1120M~//~1122M~//~v@@@R~
        catch(Exception e)                                         //~1109I~//~1120M~//~1122M~
        {                                                          //~1109I~//~1120M~//~1122M~
    		Dump.println(e,"IPSubThread.handleMessage");       //~v@@@R~//~v@11R~
        }                                                          //~1109I~//~1120M~//~1122M~
    }                                                              //~v@@@I~
    //***********************************************************  //~v@11I~
    public void outOnSubThread(int Pmsgid,IPIOThread Pthread,String PmsgData)//~v@11R~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("IPSubThread.outOnSubthread msgid=" + Pmsgid +",idxServer="+Pthread.idxServer+ ",idxThread=" + Pthread.idxMember + ",msgData=" + PmsgData);//~v@11R~
        if (Dump.Y) Dump.println("IPSubThread.outOnSubthread Pthread="+Utils.toString(Pthread));//~v@11I~
        if (!AG.isMainThread())     //on subthred, out to stream direct//~v@11R~
        {                                                          //~v@11I~
	        if (Dump.Y) Dump.println("IPSubThread.outRequest req on subthread");//~v@11R~
        	Pthread.outOnSubThread(PmsgData);                      //~v@11R~
            return;                                                //~v@11I~
        }                                                          //~v@11I~
        Message msg = obtainMessage(Pmsgid);                             //~v@11I~
        Bundle bundle = msg.getData();                               //~v@11I~
        if (AG.aIPMulti.swServer)                                  //~v@11R~
	        bundle.putInt(IPST_IDXTHREAD,Pthread.idxMember);       //~v@11R~
        else                                                       //~v@11I~
	        bundle.putInt(IPST_IDXTHREAD,Pthread.idxServer);       //~v@11I~
        bundle.putString(IPST_MSGDATA, PmsgData);                   //~v@11I~
        sendMessage(msg);
    }//~v@11I~
    //***********************************************************  //~v@21I~
    private void getMsgData(Message Pmsg)             //~v@21I~    //+v@11R~
    {                                                              //~v@21I~
        Bundle bundle=Pmsg.getData();                              //~v@21I~
        msg_idxThread=bundle.getInt(IPST_IDXTHREAD,-1);                 //~v@21I~//~v@11R~
        msg_msgData=bundle.getString(IPST_MSGDATA,"");                 //~v@21I~//~v@11R~
        if (Dump.Y) Dump.println("IPSubThread.getMsgData msgid="+Pmsg.what+",idxThread="+msg_idxThread+",msgdata="+msg_msgData);//~v@11R~
    }                                                              //~v@21I~
	//*************************                                    //~v@@@I~
	private void handleMsgIP(Message Pmsg)                          //~v@@@R~//~v@11R~
    {                                                              //~v@@@I~
    	boolean rc=false;   //need call draw                       //~v@@@I~
        if (Dump.Y) Dump.println("IPSubThread.handleMsgIP what="+Pmsg.what);//~v@@@R~//~v@11R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
	        switch(Pmsg.what)                                      //~v@@@R~
    	    {                                                      //~v@@@I~
        	case GCM_IPOUT:                                        //~v@@@I~//~v@11R~
            	out(Pmsg);                               //~v@@@I~//~v@11R~
            	break;                                             //~v@@@I~
        	default:                                               //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
        	Dump.println(e,"IPSubThread.handleMsgIP");         //~v@@@I~//~v@11R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//**************************************************************//~v@@@I~
	private void out(Message Pmsg)                         //~v@@@I~    //~v@11R~
    {                                                              //~v@@@I~
	    getMsgData(Pmsg);                                          //~v@11I~
        if (msg_idxThread<0)                                       //~v@11I~
        	return;                                                //~v@11I~
        if (Dump.Y) Dump.println("IPSubThread.out idxMember="+msg_idxThread+",Member="+AG.aBTMulti.BTGroup.toString());//~v@11I~
        IPIOThread t=(IPIOThread)AG.aBTMulti.BTGroup.getThread(msg_idxThread);//~v@11I~
        if (Dump.Y) Dump.println("IPSubThread.out thread="+Utils.toString(t)+",msg="+msg_msgData);//~v@11R~
        if (t!=null)                                               //+v@11I~
	        t.outOnSubThread(msg_msgData);                         //+v@11R~
    }                                                              //~v@@@I~
	//**************************************************************//~v@21I~
}//class IPSubThread                                                 //~dataR~//~@@@@R~//~v@@@R~//~v@11R~
