//*CID://+va27R~: update#= 418;                                    //~v@21R~//+va27R~
//**********************************************************************//~v101I~
//2020/11/03 va27 Tenpai chk at Reach                              //+va27I~
//v@11 2019/02/02 TakeOne by touch                                 //~v@11I~
//v@21  imageview                                                  //~v@21I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;

import static com.btmtest.game.GCMsgID.*;                          //~v@@@R~

import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UARestart;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UHandler;
import com.btmtest.utils.Utils;

import static com.btmtest.game.Status.*;
import static com.btmtest.game.GConst.*;//~v@@@R~
import static com.btmtest.StaticVars.AG;                           //~v@21I~

public class GameViewHandler extends UHandler                      //~v@@@R~
{                                                                  //~0914I~
	public static final String GVPARM1="StrParm1";                 //~v@@@M~
	public static final String GVPARM2="StrParm2";                 //~v@21I~
	public static final String GVPARM3="StrParm3";                 //~v@21I~
	public static final String GVPARM_INT1="IntParm1";             //~v@@@I~
	public static final String GVPARM_INT2="IntParm2";             //~v@@@I~
	public static final String GVPARM_INT3="IntParm3";             //~v@@@I~
	public static final String GVPARM_INT4="IntParm4";             //~v@11I~
	public static final String GVPARM_INT5="IntParm5";             //~v@11I~
                                                                   //~v@@@I~
//    private SurfaceHolder holder;                                  //~v@@@I~//~v@21R~
    private Canvas canvas;                                         //~v@@@I~
    private GCanvas gCanvas;                                       //~v@@@R~
    private GameView gameView;                                     //~v@@@I~
    private int WW,HH;                                            //~v@@@I~
    private MJTable table;                                         //~v@@@I~
//    private static GameViewHandler gameViewHandler;                       //~v@@@I~//~v@21R~
//*********************************************************************************//~v@11R~
    public static GameViewHandler newInstance(GameView Pgameview, HandlerThread Pthread)//~v@11R~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.newInstance gameView="+Pgameview.toString()+",thread="+Pthread.toString());//~v@11I~
        resetHandler();                                            //~v@11I~
		Looper looper=Pthread.getLooper();                         //~v@11I~
        AG.aHandlerThread=Pthread;                                 //~v@11I~
	    GameViewHandler gvh=new GameViewHandler(Pgameview,looper); //~v@11I~
        return gvh;
    }                                                              //~v@11I~
//*********************************************************************************//~v@11I~
    public static void resetHandler()                              //~v@11R~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.resetHandler AG.aGameViewhandler="+Utils.toString(AG.aGameViewHandler)+",AG.aHandlerThread="+Utils.toString(AG.aHandlerThread));//~v@11I~
    	if (AG.aGameViewHandler!=null)                             //~v@11I~
        {                                                          //~v@11I~
	        if (Dump.Y) Dump.println("GameViewHandler.resetHandler removeCallbacksAndMessages");//~v@11I~
	        AG.aGameViewHandler.removeCallbacksAndMessages(null);                       //~v@@@I~//~v@11I~
	        AG.aGameViewHandler=null;                               //~v@11I~
        }                                                          //~v@11I~
    	if (AG.aHandlerThread!=null)                               //~v@11I~
        {                                                          //~v@11I~
	        if (Dump.Y) Dump.println("GameViewHandler.resetHandler HandlerThread.quit()");//~v@11I~
	        AG.aHandlerThread.quit();                              //~v@11I~
	        AG.aHandlerThread=null;                                //~v@11I~
        }                                                          //~v@11I~
    }                                                              //~v@11I~
//*********************************************************************************//~v@11I~
//  public GameViewHandler(GameView Pgameview,Looper Plooper,SurfaceHolder Pholder)//~v@11I~
    public GameViewHandler(GameView Pgameview,Looper Plooper)      //~v@11I~
    {                                                              //~0914I~
        super(Plooper);
        if (Dump.Y) Dump.println("GameViewHandler Constructor gameView="+Pgameview.toString());         //~1506R~//~@@@@R~//~v@@@M~//~v@21R~
        if (Dump.Y) Dump.println("GameViewHandler Constructor AG.aGAmeViewHandler="+Utils.toString(AG.aGameViewHandler));//~v@11I~
//        gameViewHandler=this;                                      //~v@@@I~//~v@21R~
        AG.aGameViewHandler=this;                                  //~v@21R~
        gameView=Pgameview;                                        //~v@@@I~
//      holder=Pholder;                                            //~v@@@I~//~v@21R~
        WW=gameView.WW;                                            //~v@@@I~
        HH=gameView.HH;                                            //~v@@@I~
        table=gameView.table;                                      //~v@@@I~
//      gCanvas=new GCanvas(holder,table,WW,HH);                   //~v@@@R~//~v@21R~
        gCanvas=new GCanvas(table,WW,HH);                          //~v@21I~
    }                                                              //~0914I~//~v@@@R~
    //****************************************                 //~@003I~//~v@@@I~
    @Override	//UHandler                                                  //~@003I~//~v@@@I~
    public boolean storeMsg(Message Pmsg)                             //~@003I~//~v@@@I~
    {                                                          //~@003I~//~v@@@I~
        return true; //allow store when paused                 //~@003I~//~v@@@I~
    }                                                          //~@003I~//~v@@@I~
//    //****************************************                     //~v@@@I~//~v@21R~
//    //*frm GameView                                                //~v@@@I~//~v@21R~
//    //****************************************                     //~v@@@I~//~v@21R~
//    public void surfaceCreated(SurfaceHolder Pholder)              //~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        gCanvas.surfaceCreated(Pholder);                           //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //****************************************                     //~v@@@I~
    public void onResume()                                         //~v@@@I~
    {                                                              //~v@@@I~
    	super.onResume(AG.activity);	//UHandler                             //~v@@@R~
    }                                                              //~v@@@I~
    //****************************************                     //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
    	gCanvas.onDestroy();    //to clear bmShadow                //~v@@@I~//~v@21R~
    	super.onDestroy();	//UHandler                 //~v@@@I~
    }                                                              //~v@@@I~
	//*************************                                        //~1109I~//~1111I~//~1122M~//~v@@@R~
    @Override   //UHandler                                         //~v@@@R~
	public void handleMsg(Message Pmsg)                            //~v@@@R~
    {                                                              //~1120I~//~1122M~
        try                                                        //~1109I~//~1120M~//~1122M~
        {                                                          //~1109I~//~1120M~//~1122M~
	    	gvHandleMsg(Pmsg);                                     //~v@@@R~
	        if (Dump.Y) Dump.println("GameViewHandler.handleMsg postInvalidate gameView="+gameView.toString());//~v@21R~
	        if (Dump.Y) Dump.println("GameViewHandler.handleMsg gameView getVisibility="+gameView.getVisibility());//~v@21I~
//          gameView.postInvalidate();   //call onDraw             //~v@21R~
            gameView.paint();                                      //~v@21R~
//TODO  	new EventCB(ECB_INVALIDATE).postEvent();               //~v@21R~
        }                                                          //~1109I~//~1120M~//~1122M~//~v@@@R~
        catch(Exception e)                                         //~1109I~//~1120M~//~1122M~
        {                                                          //~1109I~//~1120M~//~1122M~
    		Dump.println(e,"GameViewHandler.handleMessage");       //~v@@@R~
        }                                                          //~1109I~//~1120M~//~1122M~
    }                                                              //~v@@@I~
	//*************************                                    //~v@11I~
	public static void handleMsgDirect(Message Pmsg)               //~v@11I~
    {                                                              //~v@11I~
	    if (Dump.Y) Dump.println("GameViewHandler.handleMsgDirect msg="+Pmsg.toString());//~v@11I~
    	if (AG.aGameViewHandler!=null)                             //~v@11I~
	    	AG.aGameViewHandler.handleMsg(Pmsg);                   //~v@11I~
    }                                                              //~v@11I~
    //***********************************************************  //~v@@@I~
    public static Message obtainMsg(int Pmsgid)                    //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GameViewHandler:obtainMsg msgid="+Pmsgid);//~v@@@R~
        Message msg = AG.aGameViewHandler.obtainMessage(Pmsgid);       //~v@@@R~//~v@21R~
        Bundle bundle = new Bundle();                              //~v@@@I~
        msg.setData(bundle);                                       //~v@@@I~
        return msg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //***********************************************************  //~v@21I~
    public static void sendMsg(int Pmsgid)                         //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("GameViewHandler.sendMsg msgid="+Pmsgid);//~v@21I~
	    sendMsg(Pmsgid,null);                                   //~v@21I~
    }                                                              //~v@21I~
    //***********************************************************  //~v@@@I~
    public static void sendMsg(int Pmsgid,String Pmsgdata)         //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GameViewHandler.sendMsg msgid="+Pmsgid+",msg="+Pmsgdata);//~v@@@R~
        if (AG.aGameViewHandler==null)                             //~v@11I~
        	return;                                                //~v@11I~
//        Message msg=obtainMsg(Pmsgid);                             //~v@@@I~//~v@11R~
//        Bundle bundle=msg.getData();                               //~v@@@I~//~v@11R~
//        bundle.putString(GVPARM1,Pmsgdata==null?"":Pmsgdata);                        //~v@@@I~//~v@21R~//~v@11R~
		Message msg=obtainMsg(Pmsgid,Pmsgdata);                            //~v@11I~
        AG.aGameViewHandler.sendMessage(msg);                                          //~v@@@I~//~v@21R~
    }                                                              //~v@@@I~
    //***********************************************************  //~v@@@I~
    public static void sendMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GameViewHandler.sendMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3);//~v@@@I~
        if (AG.aGameViewHandler==null)                             //~v@11I~
        	return;                                                //~v@11I~
//        Message msg=obtainMsg(Pmsgid);                             //~v@@@I~//~v@11R~
//        Bundle bundle=msg.getData();                               //~v@@@I~//~v@11R~
//        bundle.putInt(GVPARM_INT1,Pparm1);                         //~v@@@I~//~v@11R~
//        bundle.putInt(GVPARM_INT2,Pparm2);                         //~v@@@I~//~v@11R~
//        bundle.putInt(GVPARM_INT3,Pparm3);                         //~v@@@I~//~v@11R~
    	Message msg=obtainMsg(Pmsgid,Pparm1,Pparm2,Pparm3);        //~v@11I~
        AG.aGameViewHandler.sendMessage(msg);                                          //~v@@@I~//~v@21R~
    }                                                              //~v@@@I~
    //***********************************************************  //~v@11I~
    public static void sendMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3,int Pparm4)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.sendMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3+",Pparm4="+Pparm4);//~v@11I~
        if (AG.aGameViewHandler==null)                             //~v@11I~
        	return;                                                //~v@11I~
    	Message msg=obtainMsg(Pmsgid,Pparm1,Pparm2,Pparm3,Pparm4); //~v@11I~
        AG.aGameViewHandler.sendMessage(msg);                      //~v@11I~
    }                                                              //~v@11I~
    //***********************************************************  //~v@11I~
    public static void sendMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3,int Pparm4,int Pparm5)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.sendMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3+",Pparm4="+Pparm4+",Pparm5="+Pparm5);//~v@11I~
        if (AG.aGameViewHandler==null)                             //~v@11I~
        	return;                                                //~v@11I~
    	Message msg=obtainMsg(Pmsgid,Pparm1,Pparm2,Pparm3,Pparm4,Pparm5);//~v@11I~
        AG.aGameViewHandler.sendMessage(msg);                      //~v@11I~
    }                                                              //~v@11I~
    //***********************************************************  //~v@11I~
    public static void sendMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3,String PstrParm)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.sendMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3+",strparm="+PstrParm);//~v@11I~
        if (AG.aGameViewHandler==null)                             //~v@11I~
        	return;                                                //~v@11I~
    	Message msg=obtainMsg(Pmsgid,Pparm1,Pparm2,Pparm3);        //~v@11I~
        Bundle bundle=msg.getData();                               //~v@11I~
	    bundle.putString(GVPARM1,PstrParm);                        //~v@11I~
        AG.aGameViewHandler.sendMessage(msg);                      //~v@11I~
    }                                                              //~v@11I~
    //***********************************************************  //~v@11I~
    public static void sendMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3,int Pparm4,String PstrParm)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.sendMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3+",parm4="+Pparm4+",strparm="+PstrParm);//~v@11I~
        if (AG.aGameViewHandler==null)                             //~v@11I~
        	return;                                                //~v@11I~
    	Message msg=obtainMsg(Pmsgid,Pparm1,Pparm2,Pparm3,Pparm4); //~v@11I~
        Bundle bundle=msg.getData();                               //~v@11I~
	    bundle.putString(GVPARM1,PstrParm);                        //~v@11I~
        AG.aGameViewHandler.sendMessage(msg);                      //~v@11I~
    }                                                              //~v@11I~
    //***********************************************************  //~v@11M~
    public static Message obtainMsg(int Pmsgid,String Pmsgdata)    //~v@11M~
    {                                                              //~v@11M~
        if (Dump.Y) Dump.println("GameViewHandler.obtainMsg msgid="+Pmsgid+",msg="+Pmsgdata);//~v@11M~
    	Message msg=obtainMsg(Pmsgid);                             //~v@11M~
        Bundle bundle=msg.getData();                               //~v@11M~
        bundle.putString(GVPARM1,Pmsgdata==null?"":Pmsgdata);      //~v@11M~
        return msg;                                                //~v@11M~
    }                                                              //~v@11M~
    //***********************************************************  //~v@11I~
    public static Message obtainMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.obtainMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3);//~v@11I~
    	Message msg=obtainMsg(Pmsgid);                             //~v@11I~
        Bundle bundle=msg.getData();                               //~v@11I~
        bundle.putInt(GVPARM_INT1,Pparm1);                         //~v@11I~
        bundle.putInt(GVPARM_INT2,Pparm2);                         //~v@11I~
        bundle.putInt(GVPARM_INT3,Pparm3);                         //~v@11I~
        return msg;
    }                                                              //~v@11I~
    //***********************************************************  //~v@11I~
    public static Message obtainMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3,int Pparm4)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.obtainMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3);//~v@11I~
    	Message msg=obtainMsg(Pmsgid);                             //~v@11I~
        Bundle bundle=msg.getData();                               //~v@11I~
        bundle.putInt(GVPARM_INT1,Pparm1);                         //~v@11I~
        bundle.putInt(GVPARM_INT2,Pparm2);                         //~v@11I~
        bundle.putInt(GVPARM_INT3,Pparm3);                         //~v@11I~
        bundle.putInt(GVPARM_INT4,Pparm4);                         //~v@11I~
        return msg;                                                //~v@11I~
    }                                                              //~v@11I~
    //***********************************************************  //~v@11R~
    public static Message obtainMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3,int Pparm4,int Pparm5)//~v@11R~
    {                                                              //~v@11R~
        if (Dump.Y) Dump.println("GameViewHandler.obtainMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3);//~v@11R~
        Message msg=obtainMsg(Pmsgid);                             //~v@11R~
        Bundle bundle=msg.getData();                               //~v@11R~
        bundle.putInt(GVPARM_INT1,Pparm1);                         //~v@11R~
        bundle.putInt(GVPARM_INT2,Pparm2);                         //~v@11R~
        bundle.putInt(GVPARM_INT3,Pparm3);                         //~v@11R~
        bundle.putInt(GVPARM_INT4,Pparm4);                         //~v@11R~
        bundle.putInt(GVPARM_INT5,Pparm5);                         //~v@11R~
        return msg;                                                //~v@11R~
    }                                                              //~v@11R~
    //***********************************************************  //~v@11I~
    public static Message obtainMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3,String PstrParm)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.obtainMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3+",strParm="+PstrParm);//~v@11I~
    	Message msg=obtainMsg(Pmsgid,Pparm1,Pparm2,Pparm3);        //~v@11I~
        Bundle bundle=msg.getData();                               //~v@11I~
	    bundle.putString(GVPARM1,PstrParm);                        //~v@11I~
        return msg;                                                //~v@11I~
    }                                                              //~v@11I~
    //***********************************************************  //~v@21I~
    //*for BTIOThread Msg                                          //~v@21I~
    //***********************************************************  //~v@21I~
    public static void sendMsg(int Pmsgid,int Pappmsgid,int Psender,String Pparm1,String Pparm2,String Pparm3)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("GameViewHandler.sendMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3);//~v@21I~
        if (AG.aGameViewHandler==null)                             //~v@11I~
        	return;                                                //~v@11I~
//        Message msg=obtainMsg(Pmsgid);                             //~v@21I~//~v@11R~
//        Bundle bundle=msg.getData();                               //~v@21I~//~v@11R~
//        bundle.putInt(GVPARM_INT1,Pappmsgid);                      //~v@21I~//~v@11R~
//        bundle.putInt(GVPARM_INT2,Psender);                        //~v@21I~//~v@11R~
//        bundle.putString(GVPARM1,Pparm1);                          //~v@21I~//~v@11R~
//        bundle.putString(GVPARM2,Pparm2);                          //~v@21I~//~v@11R~
//        bundle.putString(GVPARM3,Pparm3);                          //~v@21I~//~v@11R~
	    Message msg=obtainMsg(Pmsgid,Pappmsgid,Psender,Pparm1,Pparm2,Pparm3);//~v@11I~
        AG.aGameViewHandler.sendMessage(msg);                       //~v@21I~
    }                                                              //~v@21I~
    //***********************************************************  //~v@11I~
    public static Message obtainMsg(int Pmsgid,int Pappmsgid,int Psender,String Pparm1,String Pparm2,String Pparm3)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.obtainMag msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",Pparm3="+Pparm3);//~v@11I~
    	Message msg=obtainMsg(Pmsgid);                             //~v@11I~
        Bundle bundle=msg.getData();                               //~v@11I~
        bundle.putInt(GVPARM_INT1,Pappmsgid);                      //~v@11I~
        bundle.putInt(GVPARM_INT2,Psender);                        //~v@11I~
        if (Pparm1!=null)                                          //~v@11I~
	        bundle.putString(GVPARM1,Pparm1);                      //~v@11I~
        if (Pparm2!=null)                                          //~v@11I~
	        bundle.putString(GVPARM2,Pparm2);                      //~v@11I~
        if (Pparm3!=null)                                          //~v@11I~
    	    bundle.putString(GVPARM3,Pparm3);
        return msg;//~v@11I~
    }                                                              //~v@11I~
    //***********************************************************  //~v@11I~
    public static void sendMsgDelayed(Message Pmsg,int Pmilisec)   //~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.sendMsgDelayed milisec="+Pmilisec+",msg="+Pmsg.toString());//~v@11R~
        if (AG.aGameViewHandler==null)                             //~v@11I~
        	return;                                                //~v@11I~
        AG.aGameViewHandler.sendMessageDelayed(Pmsg,Pmilisec);     //~v@11I~
    }                                                              //~v@11I~
    //***********************************************************  //~v@@@I~
    public static String getMsgData(Message Pmsg)                  //~v@@@I~
    {                                                              //~v@@@I~
        Bundle bundle=Pmsg.getData();                              //~v@@@I~
        String data=bundle.getString(GVPARM1,null);                //~v@@@I~
        if (Dump.Y) Dump.println("GameView.getMsgData msgid="+Pmsg.what+",data="+data);//~v@@@I~
        return data;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //***********************************************************  //~v@21I~
    public static String[] getMsgStrData(Message Pmsg)             //~v@21I~
    {                                                              //~v@21I~
        Bundle bundle=Pmsg.getData();                              //~v@21I~
        String data1=bundle.getString(GVPARM1,"");                 //~v@21I~
        String data2=bundle.getString(GVPARM2,"");                 //~v@21I~
        String data3=bundle.getString(GVPARM3,"");                 //~v@21I~
        if (Dump.Y) Dump.println("GameView.getMsgStrData msgid="+Pmsg.what+",data1="+data1+",data2="+data2+",data3="+data3);//~v@21I~
        String[] datas=new String[3];                              //~v@21I~
        datas[0]=data1; datas[1]=data2; datas[2]=data3;               //~v@21I~
        return datas;                                               //~v@21I~
    }                                                              //~v@21I~
    //***********************************************************  //~v@@@I~
    public static int[] getMsgIntData(Message Pmsg)                //~v@@@I~
    {                                                              //~v@@@I~
        Bundle bundle=Pmsg.getData();                              //~v@@@I~
        int p1=bundle.getInt(GVPARM_INT1,0);                       //~v@@@I~
        int p2=bundle.getInt(GVPARM_INT2,0);                       //~v@@@I~
        int p3=bundle.getInt(GVPARM_INT3,0);                       //~v@@@I~
        int p4=bundle.getInt(GVPARM_INT4,0);                       //~v@11I~
        int p5=bundle.getInt(GVPARM_INT5,0);                       //~v@11R~
        if (Dump.Y) Dump.println("GameView.getMsgIntData msgid="+Pmsg.what+",intdata1="+p1+",data2="+p2+",data3="+p3+",data4="+p4+",data5="+p5);//~v@11R~
//      int[] intp=new int[3];                                           //~v@@@I~//~v@11R~
        int[] intp=new int[5];                                     //~v@11R~
		intp[0]=p1; intp[1]=p2; intp[2]=p3;                        //~v@@@I~
		intp[3]=p4; intp[4]=p5;                                    //~v@11R~
        return intp;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*************************                                    //~v@@@I~
	public void gvHandleMsg(Message Pmsg)                          //~v@@@R~
    {                                                              //~v@@@I~
    	boolean rc=false;   //need call draw                       //~v@@@I~
        if (Dump.Y) Dump.println("GameViewHandler.handleMsg what="+Pmsg.what);//~v@@@R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
	        switch(Pmsg.what)                                      //~v@@@R~
    	    {                                                      //~v@@@I~
        	case GCM_TOUCH:                                        //~v@@@I~
            	rc=touchEvent(Pmsg);                               //~v@@@I~
            	break;                                             //~v@@@I~
        	case GCM_DEAL:                                         //~v@@@I~
            	rc=deal(Pmsg);                                     //~v@@@I~
            	break;                                             //~v@@@I~
//            case GCM_DICE_CASTED:                                  //~v@@@I~//~v@21R~
//                rc=diceCasted(Pmsg);                                     //~v@@@I~//~v@21R~
//                break;                                             //~v@@@I~//~v@21R~
        	case GCM_SETUP:                                        //~v@@@I~
            	rc=setup(Pmsg);                                    //~v@@@I~
            	break;                                             //~v@@@I~
        	case GCM_SETUPEND:                                     //~v@21I~
            	setupEnd(Pmsg);                                    //~v@21R~
            	break;                                             //~v@21I~
        	case GCM_RECEIVED_APPMSG:       //from BTIOThread-->Action-->//~v@21R~
              	AG.aACAction.receivedAppMsg(Pmsg);                 //~v@21I~
            	break;                                             //~v@21I~
            case GCM_FIRSTDICE_SETUP:       //at SETUPENDALL       //~v@21R~
                AG.aACAction.firstDice_Setup();                    //~v@21R~
                break;                                             //~v@21R~
            case GCM_FIRSTDICE_SETUP_RESUME:       //at SETUPENDALL//~v@11I~
                AG.aACAction.firstDice_Setup_Resume();             //~v@11I~
                break;                                             //~v@11I~
            case GCM_DICE_CASTED:       	//at SETUPENDALL       //~v@21I~
                AG.aACAction.dice_Casted(Pmsg);                    //~v@21I~
                break;                                             //~v@21I~
//            case GCM_SHOOT_FOR_TEMPSTARTER:     //client reqested to server to shoot for tempstarter//~v@21R~
//                AG.aACAction.shootForTempStarterServer();        //~v@21R~
//                rc=true;                                         //~v@21R~
//                break;                                           //~v@21R~
//            case GCM_DICE_TEMPSTARTER:  //for Client             //~v@21R~
//                AG.aACAction.tempStarterClient(getMsgData(Pmsg));//~v@21R~
//                rc=true;                                         //~v@21R~
//                break;                                           //~v@21R~
//            case GCM_TEMPSTARTER_DECIDED:  //Client notified     //~v@21R~
//                tempStarterDecided(Pmsg);                        //~v@21R~
//                rc=true;                                         //~v@21R~
//                break;                                           //~v@21R~
//          	case GCM_TEMPSTARTER_DECIDED_ALL:  //server receved from all client//~v@21I~
//              	AG.aACAction.tempStarterDecidedAll();              //~v@21I~
//                rc=true;                                           //~v@21I~
//              	break;                                             //~v@21I~
//            case GCM_TEMPSTARTER_SHOOT:  //Client received request//~v@21R~
//                tempStarterShoot(Pmsg);                          //~v@21R~
//                rc=true;                                         //~v@21R~
//                break;                                           //~v@21R~
        	case GCM_REMOTE_DICE:                                  //~v@@@I~
            	rc=diceCastedRemote(Pmsg);                         //~v@@@I~
            	break;                                             //~v@@@I~
//        	case GCM_SETUPEND_ALLCLIENT:                           //~v@21R~
//            	rc=setupEndAllClient();                            //~v@21R~
//            	break;                                             //~v@21I~
//        	case GCM_TEMPSTARTER:    //issued on server            //~v@21R~
//            	rc=tempStarter();                                  //~v@21I~
//            	break;                                             //~v@21I~
        	case GCM_TAKE:                                         //~v@@@I~
        	case GCM_PON:                                          //~v@@@I~
//            case GCM_PON_C:                                      //~v@11R~
        	case GCM_CHII:                                         //~v@@@I~
        	case GCM_KAN:                                          //~v@@@I~
        	case GCM_REACH:                                        //~v@@@I~
        	case GCM_REACH_OPEN:                                   //~v@11I~
        	case GCM_REACH_RESET:                                  //~v@11I~
        	case GCM_REACH_OPEN_RESET:                             //~v@11I~
        	case GCM_FORCE_REACH:                                  //+va27I~
        	case GCM_FORCE_REACH_OPEN:                             //+va27I~
        	case GCM_RON:                                          //~v@@@I~
        	case GCM_RON_ANYWAY:                                   //~v@11I~
        	case GCM_DISCARD:                                      //~v@@@I~
        	case GCM_NEXT_PLAYER:                                  //~v@21R~
        	case GCM_ENDGAME_DRAWN:                                //~v@11I~
        	case GCM_LASTGAME:                                     //~v@11I~
        	case GCM_NEXT_PLAYER_PONKAN:                           //~v@11I~
        	case GCM_OPEN:                                         //~v@@@I~
        	case GCM_WAITON:                                       //~v@11I~
//            case GCM_WAITON2:                                    //~v@11R~
        	case GCM_WAITOFF:                                      //~v@11I~
//            case GCM_WAITOFF2:                                   //~v@11R~
        	case GCM_2TOUCH:                                       //~v@11I~
            	rc=userAction(Pmsg);                               //~v@@@R~
            	break;                                             //~v@@@I~
//            case GCM_SURFACE_CHG:                                  //~v@@@I~//~v@21R~
//                rc=surfaceChanged(Pmsg);                           //~v@@@I~//~v@21R~
//                break;                                             //~v@@@I~//~v@21R~
//      	case GCM_DISCARD_TIMEOUT:                              //~v@11R~
        	case GCM_TIMEOUT_AUTODISCARD:                          //~v@11I~
            	AG.aUADelayed.autoDiscardTimeout(Pmsg);            //~v@11R~
                break;                                             //~v@11I~
//      	case GCM_DISCARD_TAKE_TIMEOUT:                         //~v@11R~
        	case GCM_TIMEOUT_AUTOTAKE:                             //~v@11I~
            	AG.aUADelayed.autoTakeTimeout(Pmsg);               //~v@11R~
                break;                                             //~v@11I~
//      	case GCM_TAKEKAN_TIMEOUT:                              //~v@11R~
        	case GCM_TIMEOUT_AUTOTAKE_KAN:                         //~v@11I~
            	AG.aUADelayed.autoTakeKanTimeout(Pmsg);            //~v@11R~
                break;                                             //~v@11I~
        	case GCM_TIMEOUT_TO_PONKAN:                            //~v@11I~
            	AG.aUADelayed.timeoutToPonKan(Pmsg);               //~v@11I~
                break;                                             //~v@11I~
        	case GCM_TIMEOUT_TO_TAKABLE_RINSHAN:                   //~v@11R~
            	AG.aUADelayed.timeoutToTakableRinshan(Pmsg);       //~v@11R~
                break;                                             //~v@11I~
        	case GCM_TIMEOUT_TO_TAKE:                              //~v@11I~
            	AG.aUADelayed.timeoutToTake(Pmsg);                 //~v@11I~
                break;                                             //~v@11I~
        	case GCM_TIMEOUT_TO_LASTDRAWN:                         //~v@11I~
            	AG.aUADelayed.timeoutToLastDrawn(Pmsg);            //~v@11I~
                break;                                             //~v@11I~
        	case GCM_TIMEOUT_STOPAUTO:                             //~v@11I~
            	AG.aUADelayed.stopAuto(Pmsg);                      //~v@11R~
                break;                                             //~v@11I~
        	case GCM_TIMEOUT_BLOCK:                                //~v@11I~
            	AG.aUADelayed.timeoutBlock(Pmsg);                  //~v@11I~
                break;                                             //~v@11I~
        	default:                                               //~v@@@I~
            }                                                      //~v@@@I~
//          if (!rc)                                                //~v@@@I~//~v@21R~
//          	gCanvas.draw(Pmsg);   //redo for GCM_SETUP and GCM_DICE//~v@21R~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
        	Dump.println(e,"GameViewHandler.gvHandleMsg");         //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//**************************************************************//~v@@@I~
	//*return false:call gCanvas.draw later                        //~v@@@R~
	//**************************************************************//~v@@@I~
	public boolean touchEvent(Message Pmsg)                         //~v@@@I~
    {                                                              //~v@@@I~
    	boolean rc=true;   //need not call draw                    //~v@@@I~
        int[] intp=getMsgIntData(Pmsg);                            //~v@@@I~
        int action=intp[0];                                        //~v@@@I~
        int xx=intp[1];                                            //~v@@@I~
        int yy=intp[2];                                            //~v@@@I~
        int xxDown=intp[3];                                        //~v@11I~
        int yyDown=intp[4];                                        //~v@11I~
        if (Dump.Y) Dump.println("GameViewHandler.touchEvent action="+action+",x="+xx+",y="+yy+",xxDown="+xxDown+",yyDown="+yyDown);//~v@@@I~//~v@11R~
        switch(action)                                             //~v@@@I~
        {                                                          //~v@@@I~
        case MotionEvent.ACTION_DOWN:                              //~v@@@I~
            break;                                                 //~v@@@I~
        case MotionEvent.ACTION_UP:                                //~v@@@I~
//            if (gCanvas.diceBox.isTouched(xx,yy))                //~v@@@R~
//                gCanvas.drawDiceCasting(false); //TODO           //~v@@@R~
//  		touchEvent(xx,yy);                                     //~v@@@I~//~v@11R~
    		touchEvent(xx,yy,xxDown,yyDown);                       //~v@11I~
            break;                                                 //~v@@@I~
        case MotionEvent.ACTION_MOVE:                              //~v@@@I~
            break;                                                 //~v@@@I~
        case MotionEvent.ACTION_CANCEL:                            //~v@@@I~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
        return rc;  //true:do not call gCanvas                     //~v@@@R~
    }                                                              //~v@@@I~
	//**************************************************************//~v@@@I~
//  public void touchEvent(int Pxx,int Pyy)                        //~v@@@I~//~v@11R~
    private void touchEvent(int Pxx,int Pyy,int PxxDown,int PyyDown)//~v@11I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GameViewHandler.touchEvent xx="+Pxx+",yy="+Pyy+",PxxDown="+PxxDown+",yyDown="+PyyDown);//~v@@@I~//~v@11R~
        int rc=AG.aDiceBox.isTouched(Pxx,Pyy);                   //~v@@@I~//~v@21R~
        if (rc==ISTOUCH_DICE)                                      //~v@@@R~
//  		gCanvas.drawDiceCasting(false);                        //~v@@@I~//~v@21R~
    		AG.aACAction.touchDice();                                //~v@21R~
        else                                                       //~v@@@I~
        if (rc!=ISTOUCH_NONE)                                      //~v@@@R~//~v@21R~
        {                                                          //~v@21I~
//    		AG.aGC.touchEvent(rc);                                 //~v@@@R~//~v@21R~
            if ((rc & ISTOUCH_DISCARD_TIMEOUT)!=0)                      //~v@11I~
	      		takeOneByTouch(rc & ISTOUCH_PLAYER_MASK,false/*PswKan*/);          //~v@11R~
            else                                                   //~v@11I~
	      		AG.aACAction.touchLight(rc & ISTOUCH_PLAYER_MASK);     //~v@21R~//~v@11R~
        }                                                          //~v@21I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//	        rc=AG.aHands.isTouch(Pxx,Pyy);                       //~v@@@I~//~v@11R~
  	        rc=AG.aHands.isTouch(Pxx,Pyy,PxxDown,PyyDown);         //~v@11I~
	        if (rc!=ISTOUCH_NONE)                             //~v@@@I~
	    		AG.aGC.touchEventTile(rc);                         //~v@@@R~
            else                                                   //~v@21I~
            {                                                      //~v@11I~
	        	rc=AG.aStock.isTouch(Pxx,Pyy);                     //~v@11I~
		        if (rc!=ISTOUCH_NONE)                              //~v@11I~
		      		takeOneByTouch(rc & ISTOUCH_PLAYER_MASK,(rc & ISTOUCH_TAKEONE_KAN)!=0);//~v@11I~
                else                                               //~v@11I~
	    			AG.aGMsg.clearIfTouched(Pxx,Pyy);                  //~v@21R~//~v@11R~
            }                                                      //~v@11I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************************//~v@11I~
    private void takeOneByTouch(int Pplayer,boolean PswKan)        //~v@11R~
    {                                                              //~v@11I~
    	if (Dump.Y) Dump.println("GameViewhandler.takeOneByTouch player="+Pplayer+",swKan="+PswKan);//~v@11R~
		if (UARestart.isIOExceptionRestarting())                   //~v@11I~
        	return;                                                //~v@11I~
        sendMsg(GCM_TAKE,null);                                    //~v@11I~
    }                                                              //~v@11I~
//    //**************************************************************//~v@21R~
//    private void touchDice()                                     //~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("GameViewHandler.touchDice");   //~v@21R~
//        if (Accounts.isServer())                                 //~v@21R~
////          gCanvas.drawDiceCasting(false/*enableafter*/);       //~v@21R~
//            DiceBox.cast(false/*enableafter*/);                  //~v@21R~
//        else                                                     //~v@21R~
//        {                                                        //~v@21R~
////            int status=Status.getGameStatus();                 //~v@21R~
////            switch(status)                                     //~v@21R~
////            {                                                  //~v@21R~
////            case GS_SETUPEND:                                  //~v@21R~
////                AG.aAccounts.sendToServer(GCM_SHOOT_FOR_TEMPSTARTER,"");    //request to server to roll dice to decide tempstarter//~v@21R~
////                break;                                         //~v@21R~
////            }                                                  //~v@21R~
//            AG.aAccounts.sendToServer(GCM_DICE_CASTREQ);    //request to server to roll dice//~v@21R~
//        }                                                        //~v@21R~
//    }                                                            //~v@21R~
	//**************************************************************//~v@@@I~
	//*shuffle and deal                                            //~v@@@I~
	//**************************************************************//~v@@@I~
	private boolean deal(Message Pmsg)                              //~v@@@I~//~v@11R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GameViewHandler.deal");          //~v@@@I~
    	boolean rc=true;   //no need call draw                     //~v@@@I~
        AG.aTiles.shuffle();                                       //~v@@@I~
        Status.setGameStatus(GS_BEFORE_DEAL);                      //~v@@@R~
        DiceBox.enable(true);                                          //~v@@@I~
        return rc;  //true:do not call gCanvas                     //~v@@@R~
    }                                                              //~v@@@I~
//    //**************************************************************//~v@@@R~
//    //*shuffle and deal                                          //~v@@@R~
//    //**************************************************************//~v@@@R~
//    public boolean takeOne(Message Pmsg)                         //~v@@@R~
//    {                                                            //~v@@@R~
//        boolean rc=true;   //no need call draw                   //~v@@@R~
//        if (Dump.Y) Dump.println("GameViewHandler.takeOne");     //~v@@@R~
//        AG.aGC.takeOne();                                        //~v@@@R~
//        return rc;  //true:do not call gCanvas                   //~v@@@R~
//    }                                                            //~v@@@R~
	//**************************************************************//~v@@@I~
	public boolean userAction(Message Pmsg)                        //~v@@@R~
    {                                                              //~v@@@I~
        boolean rc=true;   //no need call draw                     //~v@@@I~
        if (Dump.Y) Dump.println("GameViewHandler.userAction actionid="+Pmsg.what);//~v@@@R~
    	AG.aGC.userAction(Pmsg);                                   //~v@@@R~
        return rc;  //true:do not call gCanvas                     //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************************************//~v@@@I~//~v@21R~
//    public boolean diceCasted(Message Pmsg)                        //~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        return AG.aGC.diceCasted(Pmsg);                            //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
	//**************************************************************//~v@@@I~
	//*simulate dice casted at remote                              //~v@@@I~
	//**************************************************************//~v@@@I~
	public boolean diceCastedRemote(Message Pmsg)                  //~v@@@I~
    {                                                              //~v@@@I~
        boolean rc=true;   //no need call draw                     //~v@@@I~
    	AG.aGC.diceCastedRemote(Pmsg);                             //~v@@@I~
        return rc;  //true:do not call gCanvas                     //~v@@@I~
    }                                                              //~v@@@I~
	//**************************************************************//~v@@@I~
	private boolean setup(Message Pmsg)                             //~v@@@I~//~v@21R~
    {                                                              //~v@@@I~
        boolean rc=true;   //no need call draw                     //~v@@@I~
        if (Dump.Y) Dump.println("GameViewHandler.setup actionid="+Pmsg.what);//~v@@@I~
        Status.setGameStatus(GS_SETUP);                            //~v@@@I~
	    gCanvas.draw(Pmsg);                                        //~v@@@I~
//  	ACAction.setup();                                            //~v@@@R~//~v@21R~
        sendMsg(GCM_SETUPEND,null);                                //~v@21I~
        return rc;  //true:do not call gCanvas                     //~v@@@I~
    }                                                              //~v@@@I~
	//**************************************************************//~v@21I~
	private void setupEnd(Message Pmsg)                            //~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("GameViewHandler.setupEnd status="+Status.getGameStatus());      //~v@21I~//~v@11R~
//      gCanvas.draw(Pmsg);                                        //~v@21R~
        if (Status.getGameStatus()!=GS_SETUP)                      //~v@11I~
        {                                                          //~v@11I~
	        if (Dump.Y) Dump.println("GameViewHandler.setupEnd ignored by status");//~v@11I~
        	return;                                                //~v@11I~
        }                                                          //~v@11I~
    	AG.aACAction.setupEndMember();                             //~v@21R~
    }                                                              //~v@21I~
	//**************************************************************//~v@21I~
//	public boolean setupEndAllClient()                             //~v@21R~
//    {                                                              //~v@21I~
//        boolean rc=true;   //no need call draw                     //~v@21I~
//        if (Dump.Y) Dump.println("GameViewHandler.setupEndAllClient");//~v@21R~
//    	ACAction.setupEndAllClient();                              //~v@21R~
//        return rc;  //true:do not call gCanvas                     //~v@21I~
//    }                                                              //~v@21I~
//    //**************************************************************//~v@21R~
//    public boolean tempStarter()                                 //~v@21R~
//    {                                                            //~v@21R~
//        boolean rc=true;   //no need call draw                   //~v@21R~
//        if (Dump.Y) Dump.println("GameViewHandler.tempStarter"); //~v@21R~
//        AG.aGC.tempStarter();                                    //~v@21R~
//        return rc;  //true:do not call gCanvas                   //~v@21R~
//    }                                                            //~v@21R~
//    //**************************************************************//~v@21R~
//    public void tempStarterDecided(Message Pmsg)                 //~v@21R~
//    {                                                            //~v@21R~
//        int[] intp=getMsgIntData(Pmsg);                          //~v@21R~
//        if (Dump.Y) Dump.println("GameViewHandler.tempStarterDecided idx="+intp[0]+",roll1="+intp[1]+",roll2="+intp[2]);//~v@21R~
//        AG.aACAction.tempStarterDecided(intp[0],intp[1],intp[2]);//~v@21R~
//    }                                                            //~v@21R~
	//**************************************************************//~v@21I~
//    public void tempStarterShoot(Message Pmsg)                   //~v@21R~
//    {                                                            //~v@21R~
//        int[] intp=getMsgIntData(Pmsg);                          //~v@21R~
//        int starter=intp[0];                                     //~v@21R~
//        if (Dump.Y) Dump.println("GameViewHandler.tempStarterShoot starter="+starter);//~v@21R~
//        AG.aACAction.tempStarterShoot(starter);                  //~v@21R~
//    }                                                            //~v@21R~
//    //**************************************************************//~v@@@I~//~v@21R~
//    public boolean surfaceChanged(Message Pmsg)                    //~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        boolean rc=true;   //no need call draw                     //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("GameViewHandler.surfaceChanged GC?SURFACE_CHG msg process");//~v@@@I~//~v@21R~
//        Graphics.setShadow();                                      //~v@@@I~//~v@21R~
//        return rc;  //true:do not call gCanvas                     //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
}//class GameViewHandler                                                 //~dataR~//~@@@@R~//~v@@@R~
