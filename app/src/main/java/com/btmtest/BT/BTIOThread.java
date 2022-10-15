//*CID://+vas7R~:                                   update#=  405; //~vas7R~
//*************************************************************************//~@002R~
//*Blutooth msg IO thread                                          //~@002I~
//*************************************************************************//~@002I~
//2022/10/13 vas7 btio read loop when diconnected                  //~vas7I~
//2022/10/06 vara chk appversion unmatch other than rule version unmatch//~varaI~
//2022/09/24 var8 display profile icon(read text and byte)         //~var8R~
//2022/09/03 var0 summary rule setting dialog                      //~var0I~
//@002:20181103 use enum                                           //~@002I~
//****************************************************************************//~@@@1I~
package com.btmtest.BT;                                            //~1AecR~
                                                                   //~1AecI~
import com.btmtest.R;
import com.btmtest.dialog.BTCDialog;
import com.btmtest.dialog.HistoryDlg;
import com.btmtest.dialog.ResumeDlg;
import com.btmtest.dialog.RuleSettingSumm;
import com.btmtest.game.ACAction;
import com.btmtest.EventMsg;
import com.btmtest.game.Status;
import com.btmtest.game.gv.GMsg;
import com.btmtest.utils.Dump;                                     //~1AecR~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.wifi.IPIOThread;
import com.btmtest.game.UA.UARestart;                                   //~9A28I~

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;//~@002R~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@002I~

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import android.bluetooth.BluetoothSocket;


//**************************************************************   //~1AecI~
public class BTIOThread extends Thread                             //~1AecR~
{                                                                  //~1AecR~
    protected static final int MSGBUFFSZ=1024;                     //~@@@@R~
    protected static final int SENDBYTESZ=990;   //for bluetooth,wiWD:1024 OK//~var8R~
    protected static final String EOF_PROP="#EOF";                   //~9404I~//~@@@@R~
    private BluetoothSocket ioSocket;                              //~1AecR~
//  private InputStream In;                                        //~1AecR~
//  protected BufferedReader In;                                     //~1AecI~//~@@@@R~//~var8R~
    protected InputStream In;                                      //~var8I~
//  private OutputStream Out;                                      //~1AecR~
//  protected PrintWriter Out;                                      //~1AecI~//~@@@@R~//~var8R~
    protected OutputStream Out;                                    //~var8R~
//  protected InputStream InputStream;                             //~var8I~
//  protected OutputStream OutputStream;                           //~var8I~
    public  String remoteDeviceName;                               //~@002R~
    public  String localDeviceName;                                //~1AecI~//~@002R~
    protected boolean swServer;                                    //~@@@@R~
    protected boolean swClose;//~1AecI~                            //~@@@@R~
    protected int msgCtr=0;                                          //~1AecI~//~@@@@R~
    protected boolean swReadProp;                                    //~9404I~//~@@@@R~
    protected boolean swReadPropHistory;                           //~9826I~
    protected boolean swReadHistory;                               //~9825I~
    protected boolean swReadHistoryResume;                         //~9828I~
    protected String propSenderYourName;                             //~9405I~//~@@@@R~
    protected StringBuffer readProp;                                 //~9404I~//~@@@@R~
    protected StringBuffer readHistory;                            //~9825I~
    protected BTMulti BTM;                                         //~9A27I~
    private boolean swForce;                                       //~9B30I~
    public int idxMember;	//On server:client thread index, On Client:server thread index, it changed after all connection completed//~0221R~
//  public int idxMemberClientLocal;                               //~0221I~
//******************************************************************************************//~@@@@I~
    public BTIOThread()                                            //~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("BTIOThread.default constructor");//~@@@@I~
        BTM=AG.aBTMulti;                                           //~9A27I~
    }                                                              //~@@@@I~
    public static BTIOThread newBTIOThread(BluetoothSocket Psocket,String Plocalname,String Premotename,boolean Pserver)//~1AecI~
    {                                                              //~1AecI~
    	BTIOThread t=new BTIOThread(Psocket,Plocalname,Premotename,Pserver);//~1AecI~
        if (t.In==null || t.Out==null)                                 //~1AecI~
        	return null;                                           //~1AecI~
        return t;                                                  //~1AecI~
    }                                                              //~1AecI~
    public BTIOThread(BluetoothSocket Psocket,String Plocalname,String Premotename,boolean Pserver)//~1AecR~
    {                                                              //~1AecR~
    	ioSocket=Psocket; swServer=Pserver; remoteDeviceName=Premotename; localDeviceName=Plocalname;//~1AecR~
//		In=BTService.getBTInputStream(ioSocket);                   //~1AecI~
//  	In=new BufferedReader(new InputStreamReader(BTService.getBTInputStream(ioSocket)));//~1AecR~//~var8R~
  		In=BTService.getBTInputStream(ioSocket);                   //~var8I~
//		Out=BTService.getBTOutputStream(ioSocket);                 //~1AecR~
//  	Out=new PrintWriter(BTService.getBTOutputStream(ioSocket),true/*auto flush*/);//~1AecI~//~var8R~
  		Out=BTService.getBTOutputStream(ioSocket);                 //~var8I~
//      InputStream=BTService.getBTInputStream(ioSocket);          //~var8R~
//      OutputStream=BTService.getBTOutputStream(ioSocket);        //~var8R~
    }                                                              //~1AecR~
    public void run ()                                             //~1AecR~
    {                                                              //~1A8gI~//~1AecR~
        byte[] buffer = new byte[MSGBUFFSZ];                       //~1AecR~
        int bytes;
        if (Dump.Y) Dump.println("BTIOThread.run started swServer="+swServer);//~@@@9I~//~1AecR~//~@@@@R~
		getMySyncDate(true/*PswSave*/);	//save syncdate to Members //~9619I~
            if (swServer)                                          //~1AecI~
            {                                                      //~0107I~
//            	out(MSGQ_NAME);	//request yourname                 //~1AecR~//~9619R~
//            	outName();	//request yourname                     //~9619I~//~0107R~
              	outName(BTCDialog.isReconnectingAny());	//request yourname//~0107R~
//            	outQueryProfile();  do at SYNCOK                   //~var8R~
            }                                                      //~0107I~
        while (true)                                           //~@@@9I~//~1AecR~//~@002R~
        {                                                      //~3207R~//~1AecR~//~@002R~
            try                                                    //~@002I~
            {                                                      //~@002I~
//                bytes = In.read(buffer);                         //~1AecR~
//                String msg=byte2Str(buffer,bytes);  //for the case read multiple out//~1AecR~
//                String[] lines=msg.split("\n",0);                //~1AecR~
//                for (String s:lines)                             //~1AecR~
//                {                                                //~1AecR~
//  				String s=In.readLine();                            //~3207I~//~@@@9R~//~1AecI~//~var8R~
    				String s=readLine(buffer,MSGBUFFSZ);           //~var8R~
		            if (Dump.Y) Dump.println("BTIOThread.run readLine:"+Utils.toString(s));//~1AecI~//~@@@@R~
		            if (Dump.Y) Dump.println("BTIOThread.run swReadProp="+swReadProp+",swReadHistory="+swReadHistory+",swReadHistoryResume="+swReadHistoryResume);//~0212I~
					if (s==null)                                   //~9B04R~
                    {                                              //~9B04I~
			            if (Dump.Y) Dump.println("BTIOThread.run readLine NULL, throw IOException");//~9B04I~
						throw new IOException();                   //~9B04I~
                    }                                              //~9B04I~
                    if (swReadProp)                                               //~1AecI~//~9404R~
                    {                                              //~9404I~
                    	if (s.startsWith(EOF_PROP))                //~9404I~
                        {                                          //~9404I~
                        	swReadProp=false;                      //~9404I~
                            receivedProp();                        //~9404I~//~9826R~
                        	swReadPropHistory=false;               //~9826I~
                        }                                          //~9404I~
                        else                                       //~9404I~
                        	readProp.append(s+"\n");               //~9404R~
//  		            if (Dump.Y) Dump.println("BTIOThread.run readProp="+readProp.toString());//~0212I~//~varaR~
                        continue;                                  //~9404I~
                    }                                              //~9404I~
                    else                                           //~9825I~
                    if (swReadHistory)                             //~9825I~
                    {                                              //~9825I~
                    	if (s.startsWith(EOF_PROP))                //~9825I~
                        {                                          //~9825I~
                        	swReadHistory=false;                   //~9825I~
//                          receivedHistory();                     //~9825I~//~9828R~
                            receivedHistory(swReadHistoryResume);  //~9828I~
                            swReadHistoryResume=false;             //~9828I~
                        }                                          //~9825I~
                        else                                       //~9825I~
                        	readHistory.append(s+"\n");            //~9825I~
			            if (Dump.Y) Dump.println("BTIOThread.run readHistory="+readHistory.toString());//~0212I~
                        continue;                                  //~9825I~
                    }                                              //~9825I~
                    if (s.startsWith(MSGQ_APPSEQNO)) //@@!@        //~0220I~
                    {                                              //~0220I~
                    	if (receivedAppMsgSeqNo(s))	//             //~0220I~
		                    out(respMsgWithoutAppData(s));         //~0220I~
                    }                                              //~0220I~
                    else                                           //~0220I~
                    if (s.startsWith(MSGQ_APP)) //@@!!                    //~1AecR~//~9817R~
                    {                                              //~1AecR~
                    	if (receivedAppMsg(s.substring(MSGQ_APP.length())))	//not delayed resp //TODO change to no delayed response//~@002R~//~0322R~
		                    out(respMsgWithoutAppData(s));                           //~1AecR~//~@002R~//~9522R~
                    }                                              //~1AecR~
                    else                                           //~1AecR~
                    if (s.startsWith(MSGR_APPSEQNO))  //#@!@       //~0220I~
                    {                                              //~0220I~
                    	receivedAppMsgResponseSeqNo(s);            //~0220I~
                    }                                              //~0220I~
                    else                                           //~0220I~
                    if (s.startsWith(MSGR_APP))  //#@!!                  //~@002I~//~9817R~
                    {                                              //~@002I~
                    	receivedAppMsgResponse(s.substring(MSGQ_APP.length()));//~@002I~
                    }                                              //~@002I~
                    else                                           //~@002I~
                    if (s.startsWith(MSGQ_CLOSE))                  //~1AecI~
                    {                                              //~1AecI~
                        out(respMsg(s));                           //~1AecI~
                        swClose=true;                              //~1AecI~
                    	if (Dump.Y) Dump.println("BTIOThread.run swClose="+swClose);//~0112I~
                        close(In);                                 //~1AecI~
                        In=null;                                   //~1AecI~
//                      close(InputStream);                        //~var8R~
//                      InputStream=null;                          //~var8R~
                        showClosed();                              //~9622I~
                        break;                                     //~1AecI~
                    }                                              //~1AecI~
                    else                                           //~1AecI~
                    if (s.startsWith(MSGR_CLOSE))                  //~1AecR~
                    {                                              //~1AecR~
                        swClose=true;                              //~1AecR~
                    	if (Dump.Y) Dump.println("BTIOThread.run swClose="+swClose);//~0112I~
                        close(In);                                 //~1AecR~
                        In=null;                                   //~1AecR~
//                      close(InputStream);                        //~var8R~
//                      InputStream=null;                          //~var8R~
                        break;                                     //~1AecR~
                    }                                              //~1AecR~
                    else                                           //~1AecR~
                    if (s.startsWith(MSGQ_NAME))                   //~1AecR~
                    {                                              //~1AecR~
                        EventMsg.postReadMsg(MSGID_QNAME,s.substring(MSGQ_NAME.length()));//Main:ACTION_GETCTLMSG//~9619I~
//                      out(MSGR_NAME,localDeviceName,AG.YourName);//~1AecR~//~9619R~
					 	String syncDate=AG.ruleProp.getParameter(getKeyRS(RSID_SYNCDATE),"");//~9616I~//~9619I~
//                      out(MSGR_NAME,localDeviceName,AG.YourName,syncDate);//~9619I~//~@@@@R~
                        outNameR(localDeviceName,AG.YourName,syncDate);//~@@@@R~
                    }                                              //~1AecR~
                    else                                           //~1AecR~
                    if (s.startsWith(MSGR_NAME))                   //~1AecR~
                    {                                              //~1AecR~
//                      EventMsg.postReadMsg(BTMulti.MSGID_NAME,s.substring(MSGR_NAME.length()));//~1AecR~//~@002R~
//                      EventMsg.postReadMsg(MSGID_NAME,s.substring(MSGR_NAME.length()));//Main:ACTION_GETCTLMSG//~@002R~//~@@@@R~
                        receivedNameR(s.substring(MSGR_NAME.length()));//Main:ACTION_GETCTLMSG//~@@@@I~
                    }                                              //~1AecR~
                    else                                           //~1AecR~
                    if (s.startsWith(MSGQ_MEMBER_ADD))             //~1AecR~
                    {                                              //~1AecR~
                        out(respMsg(s));                           //~1AecR~
//                      EventMsg.postReadMsg(BTMulti.MSGID_MEMBER_ADD,s.substring(MSGQ_MEMBER_ADD.length()));//~1AecR~//~@002R~
//                      EventMsg.postReadMsg(MSGID_MEMBER_ADD,s.substring(MSGQ_MEMBER_ADD.length()));//~@002I~//~9815R~
                        receivedNameAdd(s.substring(MSGR_NAME.length()));//~9815I~
                    }                                              //~1AecR~
                    else                                           //~1AecR~
                    if (s.startsWith(MSGQ_MEMBER_DELETE))          //~1AecR~
                    {                                              //~1AecR~
                        out(respMsg(s));                           //~1AecR~
//                      EventMsg.postReadMsg(BTMulti.MSGID_MEMBER_DELETE,s.substring(MSGQ_MEMBER_ADD.length()));//~1AecR~//~@002R~
//                      EventMsg.postReadMsg(MSGID_MEMBER_DELETE,s.substring(MSGQ_MEMBER_DELETE.length()));//~@002I~//~9B07R~
                        receivedNameDel(s.substring(MSGQ_MEMBER_DELETE.length()));//~9B07I~
                    }                                              //~1AecR~
                    else                                           //~1AecI~
                    if (s.startsWith(MSGQ_NEWNAME))                //~1AecI~
                    {                                              //~1AecI~
//                      out(MSGQ_NAME);                            //~1AecI~//~9619R~
              			outName();	//request yourname             //~9619I~
                    }                                              //~1AecI~
                    else                                           //~9B05I~
                    if (s.startsWith(MSGQ_KEEPALIVE))              //~9B05I~
                    {                                              //~9B05I~
                    	if (Dump.Y) Dump.println("BTIOThread.run received KeepAlive request");//~9B05I~
                        sendKeepAliveResponse();                   //~9B05R~
                    }                                              //~9B05I~
                    else                                           //~9B05I~
                    if (s.startsWith(MSGR_KEEPALIVE))              //~9B05I~
                    {                                              //~9B05I~
                    	if (Dump.Y) Dump.println("BTIOThread.run received KeepAlive response");//~9B05I~
                    }                                              //~9B05I~
//                    else                                         //~var8R~
//                    if (s.startsWith(MSGQ_PROFILE))              //~var8R~
//                    {                                            //~var8R~
//                        if (Dump.Y) Dump.println("BTIOThread.run received Profile request");//~var8R~
//                        receivedProfileReq(s.substring(MSGQ_PROFILE.length())); //not delayed resp //TODO change to no delayed response//~var8R~
//                    }                                            //~var8R~
//                    else                                         //~var8R~
//                    if (s.startsWith(MSGR_PROFILE))              //~var8R~
//                    {                                            //~var8R~
//                        if (Dump.Y) Dump.println("BTIOThread.run received Profile reqponse");//~var8R~
//                        receivedProfileResp(s.substring(MSGR_PROFILE.length()));    //not delayed resp //TODO change to no delayed response//~var8R~
//                    }                                            //~var8R~
//                } //multi line                                   //~1AecR~
//                if (In==null)   //closed                         //~1AecR~
//                    break;                                       //~1AecR~
            }                                                          //~1AecR~//~@002I~
            catch (IOException e)                                      //~1AecR~//~@002I~
            {                                                          //~3201I~//~1AecR~//~@002I~
                if (Dump.Y) Dump.println("BTIOThread.run Exception swClose="+swClose);//~0112I~//~0113R~
                if (swClose)                                           //~1AecI~//~@002I~
                {                                                  //~0112I~
//                  Dump.println(e,"BTIOThread input stream by close() remote="+remoteDeviceName);     //~3201I~//~1AecR~//~@002I~//~0112R~
                    if (Dump.Y) Dump.println("BTIOThread.run IOException by close remote="+remoteDeviceName+",ex="+e.toString());//~0112I~
        			String msg=Utils.getStr(R.string.InfoDisconnectedClose,remoteDeviceName);//~0112I~
        			GMsg.drawMsgbar(msg);                          //~0112I~
        			UView.showToastLong(msg);                      //~0112I~
                }                                                  //~0112I~
                else                                                   //~1AecI~//~@002I~
                {                                                  //~@002I~
//                  Dump.println(e,"BTIOThread input stream unexpected remote="+remoteDeviceName);//~1AecR~//~@002R~
//      			UView.showToastLong(Utils.getStr(R.string.InfoDisconnected,remoteDeviceName));//~@002R~//~9811R~
        			String msg=Utils.getStr(R.string.InfoDisconnected,remoteDeviceName);//~9811I~
        			GMsg.drawMsgbar(msg);                          //~9811I~
        			UView.showToastLong(msg);                      //~9811I~
                    if (Dump.Y) Dump.println("BTIOThread unexpected IOException remote="+remoteDeviceName+",ex="+e.toString());//~@002I~
                    UARestart.setIOException();               //~9A28I~//~9A29R~//~0220R~
                }                                                  //~@002I~
                break;                                             //~@002I~
            }                                                          //~1AecR~//~@002I~
            catch (Exception e)                                    //~@002I~
            {                                                      //~@002I~
                Dump.println(e,"BTIOThread.run");                  //~@002I~
                break;                                             //~vas7I~
            }                                                      //~@002I~
        } //while                                              //~1AecR~//~@002R~
        endThread();                                               //~@@@@I~
    }                                                              //~@@@@I~
//***************************************************************  //~@@@@I~
    protected void endThread()                                     //~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("BTIOThread.endThread===== Run Terminated swclose="+swClose);  //~3120I~//~@@@9R~//~1AecR~//~@@@@R~
//      new CloseConnection(ioSocket,null/*inputstream*/,Out).start();//~1AecR~//~var8R~
//      new CloseConnection(ioSocket,null/*inputstream*/,Out,InputStream,OutputStream).start();//~var8R~
        new CloseConnection(ioSocket,null/*inputstream*/,Out).start();//~var8I~
        In=null;                                                   //~1AecM~
        Out=null;                                                   //~1AecR~
//      InputStream=null;                                          //~var8R~
//      OutputStream=null;                                         //~var8R~
//      AG.aBTMulti.connectionLost(remoteDeviceName);              //~1AecR~//~@@@@R~
//      connectionStatus=CS_DISCONNECTED;                      //~v@@@I~//~9A22I~//~0123R~
	    EventMsg.postReadMsg(MSGID_IOERR,remoteDeviceName); //to MainActivity.ACTION_GETCTLMSG-->BTMulti.msgRead//~@@@@R~
    }                                                              //~1AecR~
//***************************************************************  //~9B04I~
    public void closeSocket()                                      //~9B04I~
    {                                                              //~9B04I~
        if (Dump.Y) Dump.println("BTIOThread.closeSocket ioScoket="+Utils.toString(ioSocket));//~9B04I~
        close(ioSocket);                                          //~9B04I~
    }                                                              //~9B04I~
////***************************************************************//~1AecR~
//    private static String byte2Str(byte[] Pbuff,int Plen)        //~1AecR~
//    {                                                            //~1AecR~
//        String s;                                                //~1AecR~
//        try                                                      //~1AecR~
//        {                                                        //~1AecR~
//            s=new String(Pbuff,0,Plen,"UTF-8");                  //~1AecR~
//        }                                                        //~1AecR~
//        catch(UnsupportedEncodingException e)                      //~1AecI~+//~1AecR~
//        {                                                        //~1AecR~
//            Dump.println(e,"BTIOThread byte2Str");               //~1AecR~
//            s="UTF8 is UnSupportedEncodingException";            //~1AecR~
//        }                                                        //~1AecR~
//        if (Dump.Y) Dump.println("BTIOThread byte2Str="+s);      //~1506R~//~@@@9R~//~1AecR~
//        return s;                                                //~1AecR~
//    }                                                            //~1AecR~
//***************************************************************  //~var8I~
    private String readLine(byte[] Pbuff,int Pbuffsz) throws IOException//~var8R~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread readLine buffsz="+Pbuffsz);//~var8I~
        String s;                                                  //~var8I~
        int ctrRead=0;                                             //~var8I~
        for (;;)                                                   //~var8I~
        {                                                          //~var8I~
			int b=In.read();      //-1 or 0-255                 //~var8I~
            if (b==-1)                                             //~vas7I~
            {                                                      //~vas7I~
	    		if (Dump.Y) Dump.println("BTIOThread.readLine read get -1(EOF) throw ioException ctrRead="+ctrRead);//+vas7R~
				throw new IOException();                           //+vas7I~
            }                                                      //~vas7I~
            if (b=='\n')                                        //~var8I~
            	break;                                             //~var8I~
            Pbuff[ctrRead++]=(byte)b;                                     //~var8I~
    	}                                                          //~var8I~
        try                                                        //~var8I~
        {                                                          //~var8I~
            s=new String(Pbuff,0,ctrRead,"UTF-8");                 //~var8I~
        }                                                          //~var8I~
        catch(UnsupportedEncodingException e)                      //~1AecI~+//~var8I~
        {                                                          //~var8I~
            Dump.println(e,"BTIOThread.readLine");                 //~var8I~
            s="UTF8 is UnSupportedEncodingException";              //~var8I~
        }                                                          //~var8I~
        if (Dump.Y) Dump.println("BTIOThread ctrRead="+ctrRead+",text="+s);//~var8R~
        return s;                                                  //~var8I~
    }                                                              //~var8I~
//***************************************************************  //~1AecI~
	public static String[] parse(String Pmsg)                     //~1AecI~
    {                                                              //~1AecI~
        return Pmsg.split(MSG_SEP,0);                              //~1AecI~
    }                                                              //~1AecI~
//***************************************************************  //~1AecI~
	public static String respMsg(String Pmsg)                    //~1AecI~
    {                                                              //~1AecI~
        return MSG_RESPID+Pmsg.substring(1);                       //~1AecI~
    }                                                              //~1AecI~
//***************************************************************  //~9522I~
	public static String respMsgWithoutAppData(String Pmsg)        //~9522I~
    {                                                              //~9522I~
    	int pos=Pmsg.indexOf(MSG_SEPAPP2);                         //~9522I~
        String s;                                                  //~9522I~
        if (pos>1)                                                 //~9522I~
        	s=MSG_RESPID+Pmsg.substring(1,pos);                    //~9522I~
        else                                                       //~9522I~
        	s=MSG_RESPID+Pmsg.substring(1);                        //~9522I~
	    if (Dump.Y) Dump.println("BTIOThread.respMsgWithoutAppData Pmsg="+Pmsg+",return="+s);//~9522I~
        return s;                                                  //~9522I~
    }                                                              //~9522I~
//***************************************************************  //~1AecI~
    public void close()                                            //~1AecR~
    {                                                              //~1AecI~
	    if (Dump.Y) Dump.println("BTIOThread.close remote="+remoteDeviceName+",swClose="+swClose);//~1AecR~
        if (this.isAlive())                                        //~1AecR~
        {                                                          //~1AecI~
            if (Out!=null)                                         //~1AecI~
            {                                                      //~1AecI~
                out(MSGQ_CLOSE); //partnerthread chk this and throw IOException//TODO//~1AecR~
            }                                                      //~1AecI~
            if (swClose)  //2nd time                               //~1AecI~
            {                                                      //~1AecI~
	            close(In);                                         //~1AecI~
            	In=null;                                           //~1AecI~
//              close(InputStream);                                //~var8R~
//              InputStream=null;                                  //~var8R~
            }                                                      //~1AecI~
            else                                                   //~1AecI~
            	swClose=true;                                      //~1AecR~
            if (Dump.Y) Dump.println("BTIOThread.close swClose="+swClose);//~0112I~
        }                                                          //~1AecI~
    }                                                              //~1AecI~
//***************************************************************  //~1AecI~
//  public void close(InputStream Pis)                             //~1AecR~
//  public void close(BufferedReader Pis)                          //~1AecI~//~var8R~
    public void close(InputStream Pis)                             //~var8I~
    {                                                              //~1AecI~
	    if (Dump.Y) Dump.println("BTIOThread.close InputStream") ;  //~1AecI~//~var8R~
        if (Pis!=null)                                             //~1AecI~
        {                                                          //~1AecI~
        	try                                                    //~1AecI~
            {                                                      //~1AecI~
                Pis.close();
                //~1AecI~
            }                                                      //~1AecI~
        	catch (IOException e)                                  //~1AecI~
        	{                                                      //~1AecI~
            	Dump.println(e,"BTIOThread close InputStream");    //~1AecI~//~var8R~
	        }                                                      //~1AecI~
		    if (Dump.Y) Dump.println("BTIOThread.close InputStream closed") ;//~1AecI~//~var8R~
        }                                                          //~1AecI~
    }                                                              //~1AecI~
////***************************************************************//~var8R~
//    public void close(InputStream Pis)                           //~var8R~
//    {                                                            //~var8R~
//        if (Dump.Y) Dump.println("BTIOThread.close InputStream") ;//~var8R~
//        if (Pis!=null)                                           //~var8R~
//        {                                                        //~var8R~
//            try                                                  //~var8R~
//            {                                                    //~var8R~
//                Pis.close();                                     //~var8R~
//            }                                                    //~var8R~
//            catch (IOException e)                                //~var8R~
//            {                                                    //~var8R~
//                Dump.println(e,"BTIOThread close Inputstream");  //~var8R~
//            }                                                    //~var8R~
//            if (Dump.Y) Dump.println("BTIOThread.close InputStream closed") ;//~var8R~
//        }                                                        //~var8R~
//    }                                                            //~var8R~
//***************************************************************  //~9B04I~
    public void close(BluetoothSocket Psocket)                     //~9B04I~
    {                                                              //~9B04I~
	    if (Dump.Y) Dump.println("BTIOThread.close Socket") ;      //~9B04I~
        if (Psocket!=null)                                         //~9B04I~
        {                                                          //~9B04I~
        	try                                                    //~9B04I~
            {                                                      //~9B04I~
                Psocket.close();                                   //~9B04I~
            }                                                      //~9B04I~
        	catch (IOException e)                                  //~9B04I~
        	{                                                      //~9B04I~
            	Dump.println(e,"BTIOThread close Socket");         //~9B04I~
	        }                                                      //~9B04I~
		    if (Dump.Y) Dump.println("BTIOThread.close Socket closed") ;//~9B04I~
        }                                                          //~9B04I~
    }                                                              //~9B04I~
//***************************************************************  //~9619I~
	private boolean outName()                                      //~9619R~
    {                                                              //~9619I~
		String syncDate=getMySyncDate(false/*PswSave*/);           //~9619R~
		return out(MSGQ_NAME,localDeviceName,syncDate);            //~9619R~
	}                                                              //~9619I~
//***************************************************************  //~0107I~
//  chk both side reconnecting status                              //~0107I~
//***************************************************************  //~0107I~
	private boolean outName(boolean PswReconnecting)               //~0107I~
    {                                                              //~0107I~
		if (Dump.Y) Dump.println("BTIOThread.outName swReconnecting="+PswReconnecting);//~0107I~
		String syncDate=getMySyncDate(false/*PswSave*/);           //~0107I~
//  	return out(MSGQ_NAME,localDeviceName,syncDate,(PswReconnecting ? "1" : "0"));//~0107I~//~varaR~
    	String data=(PswReconnecting ? "1" : "0")+MSG_SEP+AG.appVersion;//~varaR~
    	return out(MSGQ_NAME,localDeviceName,syncDate,data);       //~varaI~
	}                                                              //~0107I~
//***************************************************************  //~@@@@I~
//*write name response for Bluetooth,IPIOThread will override      //~@@@@I~
//***************************************************************  //~@@@@I~
	protected void outNameR(String PlocalDeviceName,String Pyourname,String PsyncDate)//~@@@@R~
    {                                                              //~@@@@I~
		if (Dump.Y) Dump.println("BTIOThread.outNameR");           //~@@@@I~
//  	out(MSGR_NAME,PlocalDeviceName,Pyourname,PsyncDate);       //~@@@@R~//~0107R~
        boolean swReconnecting=BTCDialog.isReconnecting();	//request yourname//~0107I~
//  	out(MSGR_NAME,PlocalDeviceName,Pyourname,PsyncDate,(swReconnecting ? "1" : "0"));//~0107R~//~varaR~
    	String data=(swReconnecting ? "1" : "0")+MSG_SEP+AG.appVersion;//~varaR~
    	out(MSGR_NAME,PlocalDeviceName,Pyourname,PsyncDate,data); //~varaI~
	}                                                              //~@@@@I~
	protected void receivedNameR(String Ptxt)                     //~@@@@I~
    {                                                              //~@@@@I~
		if (Dump.Y) Dump.println("BTIOThread.receivedNameR txt="+Ptxt);//~@@@@I~
		EventMsg.postReadMsg(MSGID_NAME,Ptxt);//Main:ACTION_GETCTLMSG-->BTM.msgRead//~0323R~
	}                                                              //~@@@@I~
	protected void receivedNameAdd(String Ptxt)                    //~9815I~
    {                                                              //~9815I~
		if (Dump.Y) Dump.println("BTIOThread.receivedNameAdd txt="+Ptxt);//~9815I~
		EventMsg.postReadMsg(MSGID_MEMBER_ADD,Ptxt);               //~9815I~
	}                                                              //~9815I~
	protected void receivedNameDel(String Ptxt)                    //~9B07I~
    {                                                              //~9B07I~
		if (Dump.Y) Dump.println("BTIOThread.receivedNameDel txt="+Ptxt);//~9B07I~
		EventMsg.postReadMsg(MSGID_MEMBER_DELETE,Ptxt);            //~9B07I~
	}                                                              //~9B07I~
//***************************************************************  //~9619I~
//    private boolean outQueryProfile()                            //~var8R~
//    {                                                            //~var8R~
//        String opt=AG.aProfileIcon.makeMsgProfile(0/*query*/);   //~var8R~
//        return out(MSGQ_PROFILE,localDeviceName,opt);            //~var8R~
//    }                                                            //~var8R~
//    private void receivedProfileReq(String Ptxt)                 //~var8R~
//    {                                                            //~var8R~
////        String[] words=parse(Ptxt);     // by ";"              //~var8R~
////        if (Dump.Y) Dump.println("BTIOThread.receivedProfileReq txt="+Ptxt+",words="+Utils.toString(words));//~var8R~
////        AG.aBTMulti.updateProfile(words[0],Utils.parseInt(words[1],0));//~var8R~
////        String opt=AG.aProfileIcon.makeMsgProfile(1/*resp*/);  //~var8R~
////        out(MSGR_PROFILE,localDeviceName,opt);                 //~var8R~
//    }                                                            //~var8R~
//    private void receivedProfileResp(String Ptxt)                //~var8R~
//    {                                                            //~var8R~
////        String[] words=parse(Ptxt);     // by ";"              //~var8R~
////        if (Dump.Y) Dump.println("BTIOThread.receivedProfileResp txt="+Ptxt+",words="+Utils.toString(words));//~var8R~
////        AG.aBTMulti.updateProfile(words[0],Utils.parseInt(words[1],0));//~var8R~
//    }                                                            //~var8R~
//***************************************************************  //~var8I~
	private String getMySyncDate(boolean PswSave)                  //~9619I~
    {                                                              //~9619I~
		String syncDate=AG.ruleProp.getParameter(getKeyRS(RSID_SYNCDATE),"");//~9619I~
		if (Dump.Y) Dump.println("BTIOThread.getMySyncDate synchDate="+syncDate+",swSave="+PswSave);//~9619I~//~0323R~
        if (PswSave)                                                //~9619I~
	        AG.aBTMulti.updateMember(localDeviceName,syncDate); //save sender syncdate//~9619I~
		return syncDate;                                           //~9619I~
	}                                                              //~9619I~
//***************************************************************  //~1AecI~
	public boolean out(String Pmsgid,String Ptext)                 //~1AecI~
	{                                                              //~1AecI~
        return out(Pmsgid+Ptext);                                  //~1AecR~
	}                                                              //~1AecI~
	public boolean outForce(String Pmsgid,String Ptext)            //~9B30I~
	{                                                              //~9B30I~
        swForce=true;                                              //~9B30I~
        boolean rc=out(Pmsgid+Ptext);                              //~9B30I~
        swForce=false;                                             //~9B30I~
        return rc;                                                 //~9B30I~
	}                                                              //~9B30I~
//***************************************************************  //~1AecI~
	public boolean out(String Pmsgid,String Ptext1,String Ptext2)  //~1AecI~
	{                                                              //~1AecI~
        return out(Pmsgid+Ptext1+MSG_SEP+Ptext2);                  //~1AecR~
	}                                                              //~1AecI~
//***************************************************************  //~9619I~
	public boolean out(String Pmsgid,String Ptext1,String Ptext2,String Ptext3)//~9619I~
	{                                                              //~9619I~
        return out(Pmsgid+Ptext1+MSG_SEP+Ptext2+MSG_SEP+Ptext3);   //~9619I~
	}                                                              //~9619I~
//***************************************************************  //~0107I~
	private boolean out(String Pmsgid,String Ptext1,String Ptext2,String Ptext3,String Ptext4)//~0107I~
	{                                                              //~0107I~
        return out(Pmsgid+Ptext1+MSG_SEP+Ptext2+MSG_SEP+Ptext3+MSG_SEP+Ptext4);//~0107I~
	}                                                              //~0107I~
//***************************************************************  //~9B30I~
	public boolean outForce(String Pmsg)                           //~9B30I~
	{                                                              //~9B30I~
        if (Dump.Y) Dump.println("BTIOThread.outForce msg="+Pmsg); //~9B30I~
        swForce=true;                                              //~9B30I~
		boolean rc=out(Pmsg);                                //~9B30I~
        swForce=false;                                             //~9B30I~
        return rc;                                                 //~9B30I~
    }                                                              //~9B30I~
//***************************************************************  //~0219I~
	public static boolean flush(int PidxNumber)                    //~0219I~
    {                                                              //~0219I~
        if (Dump.Y) Dump.println("BTIOThread.flush idxNumber="+PidxNumber);//~0219I~
    	boolean rc=false;                                          //~0219I~
        BTIOThread thread=getThread(PidxNumber);                   //~0219I~
    	if (thread!=null)                                          //~0219I~
        	thread.flush();                                        //~0219I~
        return false;                                               //~0219I~
    }                                                              //~0219I~
//***************************************************************  //~0219I~
	public boolean flush()                                         //~0219I~
	{                                                              //~0219I~
    	boolean rc=true;                                           //~0219I~
        if (Dump.Y) Dump.println("BTIOThread.flush");              //~0219I~
		if (this instanceof IPIOThread)                            //~0219I~
        {                                                          //~0219I~
	        if (!AG.isMainThread())     //on subthred, out to stream direct//~v@11R~//~0219I~
            {                                                      //~0219I~
		        if (Dump.Y) Dump.println("BTIOThread.flush WifiDirect subthread");//~0219I~
//  			Out.flush();	//printwriret flushoption=true but //~0219I~//~var8R~
    			flush(Out);	//printwriret flushoption=true but     //~var8I~
            }                                                      //~0219I~
            else                                                   //~0219I~
            {                                                      //~0219I~
            	rc=false;                                          //~0219I~
		        if (Dump.Y) Dump.println("BTIOThread.flush skip by WifiDirect");//~0219I~
            }                                                      //~0219I~
        }                                                          //~0219I~
        else                                                       //~0219I~
        {                                                          //~0219I~
	        if (Dump.Y) Dump.println("BTIOThread.flush Blusetooth");//~0219I~
//  		Out.flush();	//printwriret flushoption=true but     //~0219R~//~var8R~
    		flush(Out); 	//printwriret flushoption=true but     //~var8I~
        }                                                          //~0219I~
        return rc;                                                 //~0219I~
    }                                                              //~0219I~
//***************************************************************  //~var8I~
	public boolean flush(OutputStream POut)                        //~var8I~
	{                                                              //~var8I~
    	boolean rc=true;                                           //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.flush outputStream="+POut);//~var8I~
        try                                                        //~var8I~
        {                                                          //~var8I~
			POut.flush();	//printwriret flushoption=true but     //~var8I~
        }                                                          //~var8I~
		catch (IOException e)                                      //~var8I~
		{                                                          //~var8I~
        	rc=false;                                              //~var8I~
        	Dump.println(e,"BTIOThread flush out="+POut);          //~var8I~
        }                                                          //~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
//***************************************************************
	public boolean out(String Pmsg)                                //~1AecR~
	{                                                              //~@@@2R~//~1AecI~
    	if (Out==null)                                             //~1AecI~
        	return false;                                          //~1AecI~
      if (!swForce)                                                //~9B30I~
        if (Status.isEndgameSomeone())                             //~9B20I~
        {                                                          //~9B20I~
        	if (Dump.Y) Dump.println("BTIOThread.out skip by swEndgameSomeone to="+remoteDeviceName+",writeLine:"+Pmsg);//~9B20I~
        	return false;                                          //~9B20I~
        }                                                          //~9B20I~
    	boolean rc=true;                                           //~1AecI~
        if (Dump.Y) Dump.println("BTIOThread.out to="+remoteDeviceName+",writeLine:"+Pmsg);          //~1AecI~//~@002R~//~9409R~//~9619R~
//        try                                                        //~@@@2I~//~1AecR~
//        {                                                          //~@@@2I~//~1AecR~
//            if (Dump.Y) Dump.println("BTIOThread out="+Pmsg);//~1A37I~//~1AecR~
//            byte[] send = (Pmsg+"\n").getBytes();                //~1AecR~
//            synchronized(Out)                                    //~1AecR~
//            {                                                    //~1AecR~
//                Out.write(send);                                 //~1AecR~
//            }                                                    //~1AecR~
//        }                                                          //~@@@2I~//~1AecR~
//        catch(Exception e)                                         //~@@@2I~//~1AecR~
//        {                                                          //~@@@2I~//~1AecI~-//~1AecR~
//            Dump.println(e,"BTIOThread out():"+Pmsg);               //~@@@2I~//~1AecR~
//            rc=false;                                            //~1AecR~
//        }                                                          //~@@@2I~//~1AecR~
		if (this instanceof IPIOThread)                            //~9A02I~
        	AG.aIPSubThread.outOnSubThread(GCM_IPOUT,(IPIOThread)this,Pmsg);//~9A02R~
        else                                                       //~9A02I~
//  	Out.println(Pmsg);	//flush by plintln with linefeed       //~1AecI~//~9404R~//~var8R~
			writeLine(Pmsg);                                       //~var8I~
        return rc;                                                 //~1AecI~
	}                                                              //~1AecI~
//***************************************************************  //~var8I~
	private void writeLine(String Pmsg)                            //~var8I~
    {                                                              //~var8I~
        try                                                        //~var8I~
        {                                                          //~var8I~
            if (Dump.Y) Dump.println("BTIOThread writeLine=" + Pmsg);//~var8I~
            byte[] send = (Pmsg + "\n").getBytes();                  //~var8I~
            synchronized (Out)                                      //~var8I~
            {                                                      //~var8I~
                Out.write(send);                                   //~var8I~
            }                                                      //~var8I~
        }                                                          //~var8I~
        catch (Exception e)                                         //~var8I~
        {                                                          //~@@@2I~//~1AecI~-//~var8I~
            Dump.println(e, "BTIOThread writeLine msg=" + Pmsg);      //~var8I~
        }
    }//~var8I~
//***************************************************************  //~9B05I~
	public boolean sendKeepAlive()                                 //~9B05I~
    {                                                              //~9B05I~
        String msg=MSGQ_KEEPALIVE+localDeviceName;                        //~9B05I~
        if (Dump.Y) Dump.println("BTIOThread.sendKeepAlive to="+remoteDeviceName+",msg="+msg);//~9B05I~
		boolean rc=out(msg);                                       //~9B05I~
        return rc;                                                 //~9B05I~
    }                                                              //~9B05I~
//***************************************************************  //~9B05I~
	public boolean sendKeepAliveResponse()                         //~9B05I~
    {                                                              //~9B05I~
        String msg=MSGR_KEEPALIVE+localDeviceName;                 //~9B05I~
        if (Dump.Y) Dump.println("BTIOThread.sendKeepAliveResponse to="+remoteDeviceName+",msg="+msg);//~9B05I~
		boolean rc=out(msg);                                       //~9B05I~
        return rc;                                                 //~9B05I~
    }                                                              //~9B05I~
//***************************************************************  //~9A02I~
//*from IPSubthred.outOnSubThread if out is on !Mainthread         //~var8I~
//***************************************************************  //~var8I~
	public boolean outOnSubThread(String Pmsg)                     //~9A02I~
	{                                                              //~9A02I~
    	if (Out==null)                                             //~9A02I~
        	return false;                                          //~9A02I~
    	boolean rc=true;                                           //~9A02I~
        if (Dump.Y) Dump.println("BTIOThread.outOnSubThreasd to="+remoteDeviceName+",writeLine:"+Pmsg);//~9A02I~
//		Out.println(Pmsg);	//flush by plintln with linefeed       //~9A02I~//~var8R~
  		writeLine(Pmsg);	//flush by plintln with linefeed       //~var8I~
        return rc;                                                 //~9A02I~
	}                                                              //~9A02I~
//***************************************************************  //~9404I~
	public boolean outProp(String Pmsg,String Pprops)              //~9404R~
	{                                                              //~9404I~
    	if (Out==null)                                             //~9404I~
        	return false;                                          //~9404I~
    	boolean rc=true;                                           //~9404I~
        if (Dump.Y) Dump.println("BTIOThread writeLine:"+Pmsg);    //~9404I~
//  	out(Pmsg);	                                               //~9404R~//~0213R~
//  	out(Pprops);                                               //~9404R~//~0213R~
//  	out(EOF_PROP);                                             //~9404I~//~0213R~
    	out(Pmsg+"\n"+Pprops+"\n"+EOF_PROP);	//avoid mix        //~0213I~
        return rc;                                                 //~9404I~
	}                                                              //~9404I~
//***************************************************************  //~var8I~
	public boolean sendByte(byte[] Pbyte)                          //~var8I~
	{                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.writeLine: sendByte Pbyte+"+Pbyte+",len="+Pbyte.length);//~var8R~
//  	if (OutputStream==null)                                    //~var8R~
    	if (Out==null)                                             //~var8I~
        	return false;                                          //~var8I~
        boolean rc=true;                                           //~var8I~
        try                                                        //~var8I~
        {                                                          //~var8I~
        	int offs=0;                                            //~var8I~
	        int reslen=Pbyte.length;                               //~var8I~
            int ctrWrite=0;                                        //~var8I~
        	for (;reslen>0;)                                        //~var8I~
            {                                                      //~var8I~
            	ctrWrite++;                                        //~var8I~
            	int reqlen=Math.min(reslen, SENDBYTESZ);//~var8I~
		        if (Dump.Y) Dump.println("BTIOThread writeLine: sendByte ctrWrite="+ctrWrite+",reqlen="+reqlen+",offs="+offs+",reslen="+reslen);//~var8I~
//  			OutputStream.write(Pbyte,offs,reqlen);             //~var8R~
    			Out.write(Pbyte,offs,reqlen);                      //~var8I~
                reslen-=reqlen;                                    //~var8R~
                offs+=reqlen;                                      //~var8I~
//              OutputStream.flush();                              //~var8R~
//              Out.flush();                                       //~var8R~
                flush(Out);                                        //~var8I~
            }                                                      //~var8I~
        }                                                          //~var8I~
        catch(Exception e)                                         //~var8I~
        {                                                          //~@@@2I~//~1AecI~-//~var8I~
            Dump.println(e,"BTIOThread sendByte()");               //~var8I~
            rc=false;                                              //~var8I~
        }                                                          //~var8I~
        if (Dump.Y) Dump.println("BTIOThread writeLine: after flush() rc="+rc);//~var8R~
        return rc;                                                 //~var8R~
	}                                                              //~var8I~
//***************************************************************  //~var8I~
	public byte[] receiveByte(int Plen)                            //~var8I~
	{                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread readline: receiveByte len="+Plen);//~var8I~
    	AG.aProfileIcon.msgImageExchanging(!swServer);             //~var8I~
    	byte[] buff=new byte[Plen];                            //~@@@2I~//~var8I~
        int lenTotal=0;                                            //~@@@2I~//~var8I~
        int readctr=0;                                             //~var8I~
    	try                                                            //~@@@2I~//~var8I~
        {                                                              //~@@@2I~//~var8I~
            for(;;)                                                    //~@@@2I~//~var8I~
            {                                                          //~@@@2I~//~var8I~
                int reqlen=Plen-lenTotal;                             //~@@@2I~//~var8I~
                reqlen=Math.min(reqlen,SENDBYTESZ);                //~var8I~
//              int len=InputStream.read(buff,lenTotal/*offs*/,reqlen);                   //~@@@2I~//~var8R~
                int len=In.read(buff,lenTotal/*offs*/,reqlen);     //~var8I~
                readctr++;                                             //~@@@2M~//~var8I~
		        if (Dump.Y) Dump.println("BTIOThread readline: receiveByte readctr="+readctr+",reqlen="+reqlen+",offs="+lenTotal+",readLen="+len);//~var8I~
                lenTotal+=len;                                  //~@@@2I~//~var8I~
                if (lenTotal>=Plen)                                //~var8I~
                	break;                                         //~var8I~
            }                                                          //~@@@2I~//~var8I~
        }                                                          //~var8I~
		catch (IOException e)                                      //~var8I~
		{                                                          //~var8I~
        	Dump.println(e,"BTIOThread receiveByte");              //~var8I~
        }                                                              //~@@@2I~//~var8I~
        if (Dump.Y) Dump.println("BTIOThread readline: receiveByte readctr="+readctr+",len="+Plen+",buff="+buff);//~var8I~
        return buff;                                            //~@@@2I~//~var8I~
    }                                                              //~@@@2I~//~var8I~
//***************************************************************  //~1AecI~
	class CloseConnection extends Thread                           //~1AecI~
	{                                                              //~1AecI~
		BluetoothSocket S;                                                  //~1AecI~
//  	InputStream is;                                            //~1AecR~
//  	OutputStream os;                                           //~1AecR~
//  	InputStream inps;                                          //~var8R~
//  	OutputStream outs;                                         //~var8R~
//  	BufferedReader is;                                         //~1AecI~//~var8R~
    	InputStream is;                                            //~var8I~
//  	PrintWriter  os;                                           //~1AecI~//~var8R~
    	OutputStream os;                                           //~var8I~
//  	public CloseConnection (BluetoothSocket Psocket,InputStream Pin,OutputStream Pout)//~1AecR~
//  	public CloseConnection (BluetoothSocket Psocket,BufferedReader Pin,PrintWriter Pout)//~1AecI~//~var8R~
//  	public CloseConnection(BluetoothSocket Psocket,BufferedReader Pin,PrintWriter Pout,InputStream PinpS,OutputStream PoutS)//~var8R~
    	public CloseConnection (BluetoothSocket Psocket,InputStream Pin,OutputStream Pout)//~var8I~
		{                                                          //~1AecI~
			S=Psocket; is=Pin; os=Pout;                            //~1AecI~
//  		inps=PinpS; outs=PoutS;                                //~var8R~
            if (Dump.Y) Dump.println("BTIOThread.closeConnection constructor socket="+S+",bufferedReader in="+Pin+",PrintWriter Pout="+Pout);    //~@@@@I~//~var8R~
		}                                                          //~1AecI~
		public void run ()                                         //~1AecI~
		{	try                                                    //~1AecI~
			{                                                      //~1AecI~
//  			if (is!=null)                                      //~1AecR~//~@@@@R~
//  				is.close();                                    //~1AecI~//~@@@@R~
				if (os!=null)                                      //~1AecI~
                {                                                  //~1AecI~
                	synchronized(os)                               //~1AecI~
                    {                                              //~1AecI~
						os.close();                                //~1AecR~
                    }                                              //~1AecI~
				    if (Dump.Y) Dump.println("BTIOThread.CloseConnection PrintWriter closed");//~1AecI~//~var8R~
                }                                                  //~1AecI~
//  			if (outs!=null)                                    //~var8R~
//              {                                                  //~var8R~
//              	synchronized(outs)                             //~var8R~
//                  {                                              //~var8R~
//  					outs.close();                              //~var8R~
//                  }                                              //~var8R~
//  			    if (Dump.Y) Dump.println("BTIOThread.CloseConnection OutputStream closed");//~var8R~
//              }                                                  //~var8R~
				if (S!=null)                                       //~1AecR~
                {                                                  //~1AecI~
					S.close();                                     //~1AecI~
				    if (Dump.Y) Dump.println("BTIOThread.CloseConnection SOCKET closed");//~1AecI~
                }                                                  //~1AecI~
    			if (is!=null)                                      //~@@@@I~
                {                                                  //~var8I~
    				is.close();                                    //~@@@@I~
				    if (Dump.Y) Dump.println("BTIOThread.CloseConnection BufferedReader closed");//~var8I~
                }                                                  //~var8I~
//  			if (inps!=null)                                    //~var8R~
//              {                                                  //~var8R~
//  				inps.close();                                  //~var8R~
//  			    if (Dump.Y) Dump.println("BTIOThread.CloseConnection InputStream closed");//~var8R~
//              }                                                  //~var8R~
			}                                                      //~1AecI~
			catch (Exception e)                                    //~1AecI~
			{                                                      //~1AecI~
			    if (Dump.Y) Dump.println(e,"BTIOThread.CloseConnection");//~1AecI~
			}                                                      //~1AecI~
	        if (Dump.Y) Dump.println("BTIOThread.closeConnection run terminate");//~@@@@I~
        }                                                          //~1AecI~
	}                                                              //~1AecI~
    //***************************************************************//~1AecI~
//  public static void sendMsg(BTIOThread Pthread,boolean Pswapp,int Pmsgid,String Ptext)//~1AecR~//~9A27R~
//  public static void sendMsg(int PidxMember,boolean Pswapp,int Pmsgid,String Ptext)//~9A27I~//~0110R~
    public static boolean sendMsg(int PidxMember,boolean Pswapp,int Pmsgid,String Ptext)//~0110I~
    {                                                              //~1AecI~
        if (Dump.Y) Dump.println("BTIOThread.sendMsg idxMember="+PidxMember+",Pswapp="+Pswapp+",msgid="+Pmsgid+",text="+Ptext);//~9A27R~
//  	if (!Utils.isAlive(Pthread))                              //~1AecI~//~9A27R~
//      	return;                                                //~1AecI~//~9A27R~
        BTIOThread thread=getThread(PidxMember);                   //~0304I~
      	boolean swSend=!(thread==null || UARestart.isIOExceptionInGaming());//~0304I~
    	String msg;                                                //~1AecI~
        if (Pswapp)                                                //~1AecI~
        {                                                          //~0220I~
//      	msg=makeMsgApp(thread.localDeviceName,Pmsgid,Ptext);  //~1AecM~//~9A27R~
//      	msg=makeMsgApp(AG.aBTMulti.localDeviceName,Pmsgid,Ptext);//~9A27I~//~0220R~
          if (Pmsgid==GCM_ERRMSG_NOLANG)                           //~0304I~
          {                                                        //~0304I~
        	msg=makeMsgApp(AG.aBTMulti.localDeviceName,Pmsgid,Ptext);//~0304I~
            if (thread!=null)   //force send in ioerr status of other remote//~0304I~
            	swSend=true;                                       //~0304I~
          }                                                        //~0304I~
          else                                                     //~0304I~
          {                                                        //~0304I~
//      	int msgSeqNo=AG.getMsgSeqNo();                         //~0220I~//~0221R~
//*PidxMember: client on server, server on client                  //~0221I~
			int idx=getThreadIdx(PidxMember);                      //~0221I~
        	long msgSeqNo=AG.getMsgSeqNo(idx);                      //~0221R~//~0222R~
        	msg=makeMsgAppMsgSeqNo((int)(msgSeqNo&0xffffffff),AG.aBTMulti.localDeviceName,Pmsgid,Ptext);//~0220I~//~0222R~
//      	UARestart.saveMsg(PidxMember,msgSeqNo,msg);            //~0220M~//~0221R~
        	UARestart.saveMsg(idx,msgSeqNo,msg);  //local entry for client//~0221R~
          }                                                        //~0304I~
        }                                                          //~0220I~
        else                                                       //~1AecI~
        	msg=makeMsgCtl(Pmsgid,Ptext);                          //~1AecM~
        if (msg==null)                                             //~1AecI~
//      	return;                                                //~1AecI~//~0110R~
        	return false;                                          //~0110I~
//      BTIOThread thread=getThread(PidxMember);               //~9A27I~//~9A28R~//~0304R~
//    	if (thread==null || UARestart.isIOExceptionInGaming())                                       //~9A27M~//~9A28R~//~9A29R~//~0115R~//~0220R~//~0221R~//~0304R~
      	if (!swSend)                                               //~0304I~
//      	if (thread==null || UARestart.isIOExceptionInGamingSendBlocked())//~0115I~
        {                                                          //~9A28I~
//            boolean send=true;                                      //~0218I~//~0220R~
//            if (Status.isGamingNow())                              //~9A28I~//~0220R~
////              AG.aUARestart.savePendingMsg(PidxMember,Pmsgid,Ptext,msg);//~9A28R~//~0218R~//~0220R~
//                send=AG.aUARestart.savePendingMsg(PidxMember,Pmsgid,Ptext,msg);//~0218I~//~0220R~
//            if (thread==null)                                      //~0218I~//~0220R~
//                send=false;                                        //~0218I~//~0220R~
////          return;                                                //~9A27M~//~9A28I~//~0110R~//~0220R~
//            if (Dump.Y) Dump.println("BTIOThread ioerr send="+send+",outmsg="+msg);//~0218I~//~0220R~
//          if (!send)                                               //~0218I~//~0220R~
            if (Dump.Y) Dump.println("BTIOThread.sendMsg @@@@ thread=null or ioexception detected idxMember="+PidxMember+",outmsg="+msg);//~0220I~//~0221R~//~0223R~
            return false;                                          //~0110I~
        }                                                          //~9A28I~
        if (Dump.Y) Dump.println("BTIOThread.sendMsg outmsg="+msg+",local="+thread.localDeviceName+",remote="+thread.remoteDeviceName);//~1AecR~//~9A27R~//~0220R~
        thread.out(msg);                                           //~1AecR~//~9A27R~
        return true;                                               //~0110I~
    }                                                              //~1AecI~
    //***************************************************************//~var8I~
    public static boolean sendByte(int PidxMember,byte[] Pbuff)    //~var8I~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.sendByte idxMember="+PidxMember+",buff="+Pbuff+",len="+Pbuff.length);//~var8I~
        BTIOThread thread=getThread(PidxMember);                   //~var8I~
        boolean rc=thread.sendByte(Pbuff);                         //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.sendByte rc="+rc);    //~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //***************************************************************//~var8I~
    public boolean sendMsgProfile(int Pmsgid,String Ptext)  //~var8I~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.sendMsg idxMember="+idxMember+",msgid="+Pmsgid+",text="+Ptext);//~var8I~
    	String msg;                                                //~var8I~
        long msgSeqNo=AG.getMsgSeqNo(idxMember);                   //~var8I~
        msg=makeMsgAppMsgSeqNo((int)(msgSeqNo&0xffffffff),localDeviceName,Pmsgid,Ptext);//~var8I~
        if (Dump.Y) Dump.println("BTIOThread.sendMsgProfile local="+localDeviceName+",remote="+remoteDeviceName+",msg="+msg);//~var8I~
        out(msg);                                                  //~var8I~
        return true;                                               //~var8I~
    }                                                              //~var8I~
    //***************************************************************//~0221I~
    private static int getThreadIdx(int PidxMember)                //~0221I~
    {                                                              //~0221I~
    	int rc=PidxMember;                                         //~0221I~
//        if (AG.aBTMulti.memberRole==ROLE_CLIENT)                 //~0221R~
////          rc=AG.aBTMulti.BTGroup.idxLocal;                     //~0221R~
//            rc=AG.aBTMulti.BTGroup.idxServer;                    //~0221I~
        if (Dump.Y) Dump.println("BTIOThread.getThreadIdx rc="+rc+",memberRol="+AG.aBTMulti.memberRole);//~0221I~
        return rc;                                                 //~0221I~
    }                                                              //~0221I~
    //***************************************************************//~9A27I~
    private static BTIOThread getThread(int PidxMember)//~9A27R~   //~9A28R~
    {                                                              //~9A27I~
        BTIOThread thread=(BTIOThread)AG.aBTMulti.BTGroup.getThread(PidxMember);//~9A27R~
    	if (!Utils.isAlive(thread))                                //~9A27I~
        {                                                          //~9A27I~
            if (Dump.Y) Dump.println("BTIOThread.getThread thread inactive");//~9A27I~
//          if (AG.aBTMulti.BTGroup.isIOErr(PidxMember))           //~9A27R~//~9A28R~
//          	AG.aBTMulti.BTGroup.savePendingMsg(PidxMember,Pmsg);       //~9A27R~//~9A28R~
        	return null;                                           //~9A27I~
        }                                                          //~9A27I~
        if (Dump.Y) Dump.println("BTIOThread.getThread thread="+thread.toString());//~9A27R~//~9A28R~
        return thread;                                             //~9A27I~
    }                                                              //~9A27I~
    //***************************************************************//~@002I~
    //*Properties and History Data                                 //~9825I~
    //***************************************************************//~9825I~
//  public static void sendMsgProp(BTIOThread Pthread,int Pmsgid,String Pprops)//~@002I~//~9404R~//~9A27R~
    public static boolean sendMsgProp(int PidxMember,int Pmsgid,String Pprops)//~9A27I~//~9A29R~
    {                                                              //~@002I~
        if (Dump.Y) Dump.println("BTIOThread.sendMsgProp idxMember="+PidxMember+",msgid="+Pmsgid+",props="+Pprops);//~@002I~//~9404R~//~9A27R~
//  	if (!Utils.isAlive(Pthread))                               //~@002I~//~9A27R~
//      	return;                                                //~@002I~//~9A27R~
    	String msg;                                                //~@002I~
//      msg=makeMsgApp(Pthread.localDeviceName,Pmsgid,null);      //~@002I~//~9404R~//~9A27R~
        msg=makeMsgApp(AG.aBTMulti.localDeviceName,Pmsgid,null);   //~9A27I~
        BTIOThread thread=getThread(PidxMember);               //~9A27R~//~9A28R~
    	if (thread==null)                                          //~9A27I~
        {                                                          //~9A28I~
//    		AG.aUARestart.savePendingMsg(PidxMember,Pmsgid,Pmsg);  //~9A28R~
        	return false;                                                //~9A27I~//~9A29R~
        }                                                          //~9A28I~
        if (Dump.Y) Dump.println("BTIOThread.sendMsgProp outmsg="+msg+",local="+thread.localDeviceName+",remote="+thread.remoteDeviceName);//~@002I~//~0220R~
        thread.outProp(msg,Pprops);                                          //~@002I~//~9404R~
        return true;                                               //~9A29I~
    }                                                              //~@002I~
    //***************************************************************//~0220I~
    //*restart ctl msg(no seqno)                                   //~0220I~
    //***************************************************************//~0220I~
    public static boolean sendRestartGame(int PidxMember,int Pmsgid,String Pmsg)//~0220I~
    {                                                              //~0220I~
        if (Dump.Y) Dump.println("BTIOThread.sendRestartGame idxMember="+PidxMember+",msgid="+Pmsgid+",msg="+Pmsg);//~0220I~//~0221R~
    	String msg;                                                //~0220I~
        msg=makeMsgApp(AG.aBTMulti.localDeviceName,Pmsgid,Pmsg);   //~0220I~
        BTIOThread thread=getThread(PidxMember);                   //~0220I~
    	if (thread==null)                                          //~0220I~
        {                                                          //~0220I~
        	return false;                                          //~0220I~
        }                                                          //~0220I~
        if (Dump.Y) Dump.println("BTIOThread.sendRestartGame outmsg="+msg+",local="+thread.localDeviceName+",remote="+thread.remoteDeviceName);//~0220I~
        thread.out(msg);                                           //~0220I~
        return true;                                               //~0220I~
    }                                                              //~0220I~
    //***************************************************************//~9A27I~
    public static void sendRestartMsg(int PidxMember,String Pmsg)  //~9A27I~
    {                                                              //~9A27I~
        if (Dump.Y) Dump.println("BTIOThread.sendMsg idxMember="+PidxMember+",msg="+Pmsg);//~9A27I~
        BTIOThread thread=getThread(PidxMember);                   //~9A28R~
    	if (thread==null)                                          //~9A27I~
        {                                                          //~0220I~
	        if (Dump.Y) Dump.println("BTIOThread.sendRestartMsg @@@@ thread=null");//~0220I~
        	return;                                                //~9A27I~
        }                                                          //~0220I~
        if (Dump.Y) Dump.println("BTIOThread.sendRestartMsg outmsg="+Pmsg+",local="+thread.localDeviceName+",remote="+thread.remoteDeviceName);//~9A27I~//~0220R~
        thread.out(Pmsg);                                          //~9A27I~
    }                                                              //~9A27I~
//    //***************************************************************//~@002I~//~0220R~
////  public static void sendMsgDelayedAppResponse(BTIOThread Pthread,int Pmsgid,String Ptext)//~@002I~//~9A27R~//~0220R~
//    public static void sendMsgDelayedAppResponse(int PidxMember,int Pmsgid,String Ptext)//~9A27I~//~0220R~
//    {                                                              //~@002I~//~0220R~
//        if (Dump.Y) Dump.println("BTIOThread.sendMsgDelayedAppResponse idxMember="+PidxMember+",msgid="+Pmsgid+",text="+Ptext);//~@002I~//~9A27R~//~0220R~
////      if (!Utils.isAlive(Pthread))                               //~@002I~//~9A27R~//~0220R~
////      {                                                          //~@002I~//~9A27R~//~0220R~
////          if (Dump.Y) Dump.println("BTIOThread.sendMsgDelayedAppResponse thread inactive");//~@002I~//~9A27R~//~0220R~
////          return;                                                //~@002I~//~9A27R~//~0220R~
////      }                                                          //~@002I~//~9A27R~//~0220R~
//        String msg;                                                //~@002I~//~0220R~
////      msg=makeMsgAppResponse(Pthread.localDeviceName,Pmsgid,Ptext);//~@002I~//~9A27R~//~0220R~
//        msg=makeMsgAppResponse(AG.aBTMulti.localDeviceName,Pmsgid,Ptext);//~9A27I~//~0220R~
//        BTIOThread thread=getThread(PidxMember);               //~9A27R~//~9A28R~//~0220R~
//        if (thread==null)                                          //~9A27I~//~0220R~
//        {                                                          //~9A28I~//~0220R~
////          AG.aUARestart.savePendingMsg(PidxMember,Pmsgid,Pmsg);  //~9A28R~//~0220R~
//            return;                                                //~9A27I~//~0220R~
//        }                                                          //~9A28I~//~0220R~
//        if (Dump.Y) Dump.println("BTIOThread outmsg="+msg+",local="+thread.localDeviceName+",remote="+thread.remoteDeviceName);//~@002I~//~0220R~
//        thread.out(msg);                                          //~@002I~//~0220R~
//    }                                                              //~@002I~//~0220R~
    //***************************************************************//~1AecI~
    public static String makeMsgCtl(int Pmsgid,String Ptext)       //~1AecI~
    {                                                              //~1AecI~
    	String msg=null;                                           //~1AecI~
        switch(Pmsgid)                                             //~1AecI~
        {                                                          //~1AecI~
//      case BTMulti.MSGID_NEWNAME:    //name changed              //~1AecI~//~@002R~
        case MSGID_NEWNAME:    //name changed                      //~@002I~
            msg=MSGQ_NEWNAME;                                      //~1AecI~
        }                                                          //~1AecI~
        return msg;                                                //~1AecI~
    }                                                              //~1AecI~
    //***************************************************************//~1AecI~
    private static String makeMsgApp(String Plocaldevicename,int Pmsgid,String Ptext)//~1AecI~
    {                                                              //~1AecI~
    	String msg;
        if (Ptext==null)                                           //~9404I~
    		msg=MSGQ_APP+Plocaldevicename+MSG_SEP+Pmsgid;   //~9404I~
        else                                                       //~9404I~
    		msg=MSGQ_APP+Plocaldevicename+MSG_SEP+Pmsgid+MSG_SEP+Ptext;//~1AecR~//~9404R~
        if (Dump.Y) Dump.println("BTIOThread.makeMsgApp msg="+msg);//~0213I~
        return msg;                                                //~1AecI~
    }                                                              //~1AecI~
    //***************************************************************//~0220I~
    private static String makeMsgAppMsgSeqNo(int PseqNo,String Plocaldevicename,int Pmsgid,String Ptext)//~0220I~
    {                                                              //~0220I~
    	String msg;                                                //~0220I~
        if (Ptext==null)                                           //~0220I~
    		msg=MSGQ_APPSEQNO+PseqNo+MSG_SEP+Plocaldevicename+MSG_SEP+Pmsgid;//~0220I~
        else                                                       //~0220I~
    		msg=MSGQ_APPSEQNO+PseqNo+MSG_SEP+Plocaldevicename+MSG_SEP+Pmsgid+MSG_SEP+Ptext;//~0220I~
        if (Dump.Y) Dump.println("BTIOThread.makeMsgApp msg="+msg);//~0220I~
        return msg;                                                //~0220I~
    }                                                              //~0220I~
    //***************************************************************//~@002I~
    private static String makeMsgAppResponse(String Plocaldevicename,int Pmsgid,String Ptext)//~@002I~
    {                                                              //~@002I~
    	String msg=MSGR_APP+Plocaldevicename+MSG_SEP+Pmsgid+MSG_SEP+Ptext;//~@002I~//~9522R~
        if (Dump.Y) Dump.println("BTIOThread.makeMsgAppResponse Ptext="+Ptext+",msg="+msg);//~9522I~
        return msg;                                                //~@002I~
    }                                                              //~@002I~
    //***************************************************************//~0220I~
    //*received @@!@seqno;sender-device;msgid...                   //~0220I~
    //***************************************************************//~0220I~
    public boolean receivedAppMsgSeqNo(String Pappmsg)             //~0220I~
    {                                                              //~0220I~
	    if (Dump.Y) Dump.println("BTIOThread.receivedAppMsgSeqNo msg="+Pappmsg);//~0220I~
    	int pos=MSGQ_APPSEQNO.length();                            //~0220I~
        int pos2=Pappmsg.indexOf(MSG_SEP,pos);          //~0220I~
        if (pos2<=pos)                                             //~0220I~
        {                                                          //~0220I~
	        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsgSeqNo @@@@ no msgno");//~0220I~//~0224R~
        	return false;                                          //~0220I~
        }                                                          //~0220I~
        int seqNo=Utils.parseInt(Pappmsg.substring(pos,pos2),-1);  //~0220I~
        if (seqNo==-1)                                             //~0220I~
        {                                                          //~0220I~
	        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsgSeqNo @@@@ msgno err");//~0220I~//~0224R~
        	return false;                                          //~0220I~
        }                                                          //~0220I~
        if (AG.aUARestart!=null)                                   //~0220I~
	        if (!AG.aUARestart.receivedRequestSeqNo(swServer,idxMember,seqNo,Pappmsg,pos2+1))	//chk seqnoOverrun//~0220R~//~0224R~
            	return false;                                      //~0220I~
    	boolean rc=receivedAppMsg(Pappmsg.substring(pos2+1));      //~0220I~
        return rc;                                                 //~0220I~
    }                                                              //~0220I~
    //***************************************************************//~1AecI~
    //*rc:true: out response msg(#@)                               //~9621I~
    //***************************************************************//~9621I~
    public boolean receivedAppMsg(String Pappmsg)                     //~1AecI~//~@002R~
    {                                                              //~1AecI~
    	swReadProp=false;                                           //~9404I~
    	swReadPropHistory=false;                                   //~9826I~
    	swReadHistory=false;                                       //~9825I~
    	swReadHistoryResume=false;                                 //~9828I~
    	boolean rc=true;                                           //~@002I~
        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsg msg="+Pappmsg);//~@002I~
    	String[] words=parse(Pappmsg);     // by ";"                        //~1AecI~//~9817R~
        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsg words="+Arrays.toString(words));//~0124I~
        String remotename=words[0]; //it is server or other client for info msg from server//~1AecI~
        int threadIdx=AG.aBTMulti.getMemberIndex(remotename);      //~1AecI~
        if (threadIdx<0)                                           //~1AecI~
        {                                                          //~1AecI~
        	if (Dump.Y) Dump.println("BTIOThread receivedAppMsg remote="+remotename+" not found");//~1AecI~
        	return false;                                               //~1AecI~
        }                                                          //~1AecI~
        int msgid=Integer.parseInt(words[1]);                      //~1AecI~
        String msg1=(words.length>2 ? words[2]:"");                                       //~1AecI~//~@002R~
        String msg2=(words.length>3 ? words[3]:"");                //~@002I~
        String msg3=(words.length>4 ? words[4]:"");                //~@002I~
                                                                   //~1AecI~
//      EventMsg.postReceivedMsg(BTMulti.idx2tid(threadIdx),msgid,msg);//~1AecR~//~@002R~
		boolean swDoAccount=true;                                  //~@002I~
        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsg msgid="+msgid+",data1="+msg1+",data2="+msg2+",data3="+msg3);//~@002R~
        byte[] byteReceived=null;                                  //~var8I~
        switch(msgid)                                              //~@002I~
        {                                                          //~@002I~
            case GCM_SETUPEND:                                     //~@002R~
//              swDoAccount=false;                                 //~@002R~
//                int rc=BTMulti.setupEnd(remoteDeviceName); //client-->server msg//~@002R~
//                if (rc==0)                                       //~@002R~
//                    GameViewHandler.sendMsg(GCM_SETUPEND_ALLCLIENT,"");//~@002R~
//                else                                             //~@002R~
//                    UView.showToast(Utils.getStr(R.string.Info_WaitingAllMemberSetup,rc));//~@002R~
                BTMulti.setupEnd(remoteDeviceName); //client-->server msg//~@002R~
                if (ACAction.chkSetupEndBT()!=0)	//remainig count           //~v@@@I~//~@002I~
					swDoAccount=false;     //avoid crash by Server not yet initialized AG.aACAction GameView Handler etc//~@002I~
                break;                                             //~@002R~
            case GCM_ENDGAME:                                      //~@002I~
            case GCM_ENDGAME_RETURN:                               //~9B21I~
              	swDoAccount=false;                                 //~@002I~
//              BTMulti.endGame(threadIdx,true/*swReceived*/); //client-->server msg//~@002I~//~9B19R~//~9B21R~
                BTMulti.endGame(threadIdx,true/*swReceived*/,msgid==GCM_ENDGAME_RETURN); //svr->client//~9B21I~
                String msg=Utils.getStr(R.string.Info_EndGameReceived,remoteDeviceName);//~@002I~//~9811R~
//              UView.showToast(Utils.getStr(R.string.Info_EndGameReceived,remoteDeviceName));//~9811I~
        		GMsg.drawMsgbar(msg);                              //~9811I~
        		UView.showToast(msg);                              //~9811I~
                break;                                             //~@002I~
            case GCM_TEMPSTARTER_DECIDED:                          //~@002I~
                if (!swServer)                                     //~@002I~
	            	rc=false;	//delayed response                 //~@002I~
                break;                                             //~9406I~
            case GCM_ERRMSG: //503                                 //~9B13I~
            case GCM_ERRMSG_NOLANG: //506                          //~0215I~
            case GCM_ERRMSG_ANDTOAST: //505                        //~9B27I~
//              rc=false;	//no need response mag  //response without data//~0220I~
                break;                                             //~9B13I~
            case GCM_SETTING:                                      //~9404I~
			    Status.resetEndgameSomeone();   //restart send/receive//~9B21I~
            	swReadProp=true;                                   //~9404I~
            	propSenderYourName=AG.aBTMulti.getYourName(threadIdx);//~9405I~
                readProp=new StringBuffer();                       //~9404I~
				swDoAccount=false;                                 //~9404I~
	            rc=false;	//delayed response by RuleSetting      //~9404R~
                break;                                             //~9406I~
            case GCM_SETTING_HISTORY:                              //~9826I~
            	swReadProp=true;                                   //~9826I~
            	swReadPropHistory=true;                            //~9826I~
                readProp=new StringBuffer();                       //~9826I~
				swDoAccount=false;                                 //~9826I~
	            rc=false;	//delayed response by RuleSetting      //~9826I~
                break;                                             //~9826I~
            case GCM_HISTORY:                                      //~9825I~
            	swReadHistory=true;                                //~9825I~
            	propSenderYourName=AG.aBTMulti.getYourName(threadIdx);//~9825I~
                readHistory=new StringBuffer();                    //~9825I~
				swDoAccount=false;                                 //~9825I~
	            rc=false;	//delayed response by RuleSetting      //~9825I~
                break;                                             //~9825I~
            case GCM_HISTORY_RESUME:    //client to server send history for resume//~9828I~
            	swReadHistory=true;                                //~9828I~
            	swReadHistoryResume=true;                          //~9828I~
            	propSenderYourName=AG.aBTMulti.getYourName(threadIdx);//~9828I~
                readHistory=new StringBuffer();                    //~9828I~
				swDoAccount=false;                                 //~9828I~
	            rc=false;	//delayed response by RuleSetting      //~9828I~
                break;                                             //~9828I~
            case GCM_SETTING_RESP:                                 //~9406I~
                receivedPropReply(threadIdx,words);                //~9406I~
				swDoAccount=false;                                 //~9406I~
	            rc=false;	//no response required                 //~9406I~
                break;                                             //~9406I~
            case GCM_SETTING_SYNC:                                 //~9406I~
                receivedPropSync(threadIdx,words);                 //~9406I~
				swDoAccount=false;                                 //~9406I~
	            rc=false;	//no response required                 //~9406I~
                break;                                             //~9406I~
            case GCM_FIRSTDICE_SETUP:                              //~9407R~
            case GCM_FIRSTDICE_SETUP_RESUME:                       //~9902I~
//              if (Status.isGaming())                             //~9407R~//~@@@@R~
                if (Status.isGamingNow())                          //~@@@@I~
                {                                                  //~9407R~
                    GMsg.drawMsgbar(R.string.Err_StartGameReqInGaming);//~9407R~
                    AG.aGameView.paint();                          //~9407I~
                    swDoAccount=false;                             //~9407R~
                    rc=false;                                      //~9407R~
                }                                                  //~9407R~
                break;                                             //~9407R~
            case GCM_DICE_CASTREQ:                                 //~9407I~
//                if (Status.isGaming())  //enable dice is controlled//~9502R~
//                {                                                  //~9407I~//~9502R~
//                    GMsg.drawMsgbar(R.string.Err_StartGameReqInGaming);//~9407I~//~9502R~
//                    AG.aGameView.paint();                          //~9407I~//~9502R~
//                    swDoAccount=false;                             //~9407I~//~9502R~
//                    rc=false;                                      //~9407I~//~9502R~
//                }                                                //~9502R~
                break;                                             //~9407I~
            case GCM_SETTING_SYNC_QUERY:                           //~9621I~
                receivedSyncQuery(threadIdx,words);                //~9621I~
				swDoAccount=false;                                 //~9621I~
	            rc=false;	//no response required                 //~9621I~
                break;                                             //~9621I~
            case GCM_SETTING_SYNC_RESP:                            //~9621I~
                receivedSyncQueryResp(threadIdx,words);            //~9621I~
				swDoAccount=false;                                 //~9621I~
	            rc=false;	//no response required                 //~9621I~
                break;                                             //~9621I~
            case GCM_SETTING_CHANGED:                              //~9621I~
                receivedSettingChanged(threadIdx,words);           //~9621I~
				swDoAccount=false;                                 //~9621I~
	            rc=false;	//no response required                 //~9621I~
                break;                                             //~9621I~
            case GCM_SETTING_NOTIFY_SYNCOK:                        //~9621I~
                receivedSyncOK(threadIdx,words);                   //~9621I~
				swDoAccount=false;                                 //~9621I~
	            rc=false;	//no response required                 //~9621I~
                break;                                             //~9621I~
            case GCM_PROFILE_STARTSYNC:     //On Server,client to server at syncOK//~var8I~
                swDoAccount=true;    //pass to ACAction            //~var8I~
                rc=false;   //no response required                 //~var8I~
                break;                                             //~var8I~
            case GCM_PROFILE_GETIMAGE_C2S:     //on Client,Server to client at received STARTSYNC all//~var8I~
                receivedGetImageC2S(threadIdx,words);              //~var8I~
				swDoAccount=false;                   //GVH is not yet initialized//~var8I~
	            rc=false;	//no response required                 //~var8I~
                break;                                             //~var8I~
            case GCM_PROFILE_GETIMAGE_C2SR:     //on Server received BMP//~var8I~
                byteReceived=receivedGetImageC2SR(threadIdx,words);//~var8R~
				swDoAccount=true;                   //GVH is not yet initialized//~var8R~
	            rc=false;	//no response required                 //~var8I~
                break;                                             //~var8I~
            case GCM_PROFILE_NOTIFY_ALL:        //sever to client  //~var8I~
                receivedProfileNotifyAll(threadIdx,words);         //~var8I~
                swDoAccount=false;    //pass to ACAction           //~var8I~
                rc=false;   //no response required                 //~var8I~
                break;                                             //~var8I~
            case GCM_PROFILE_NOTIFY_ALL_RESP:     //client to server at syncOK//~var8I~
                swDoAccount=true;    //pass to ACAction            //~var8I~
                rc=false;   //no response required                 //~var8I~
                break;                                             //~var8I~
            case GCM_PROFILE_SENDIMAGE_S2C:      //on Client,received other image//~var8I~
                receivedSendImageS2C(words);                       //~var8I~
                swDoAccount=false;    //NOT pass to ACAction       //~var8I~
                rc=false;   //no response required                 //~var8I~
                break;                                             //~var8I~
            case GCM_PROFILE_SENDIMAGE_S2CR:      //on Server      //~var8I~
                swDoAccount=true;    //pass to ACAction            //~var8I~
                rc=false;   //no response required                 //~var8I~
                break;                                             //~var8I~
            case GCM_PROFILE_SYNC_COMP:      //on Server           //~var8I~
                receivedProfileSyncComp();                         //~var8I~
                swDoAccount=false;    //pass to ACAction           //~var8I~
                rc=false;   //no response required                 //~var8I~
                break;                                             //~var8I~
            case GCM_RESUMEDLG:                                    //~9831I~
                receivedResumeDlg(threadIdx,words);                //~9831I~
				swDoAccount=false;                                 //~9831I~
	            rc=false;	//no response required                 //~9831I~
                break;                                             //~9831I~
        }                                                          //~@002I~
        if (swDoAccount)                                           //~@002I~
        {                                                          //~@002I~
          if (byteReceived!=null)                                  //~var8I~
	        AG.aACAction.receivedAppMsg(threadIdx,msgid,msg1,msg2,byteReceived);//~var8I~
          else                                                     //~var8I~
	        AG.aACAction.receivedAppMsg(threadIdx,msgid,msg1,msg2,msg3);//~@002R~
        }                                                          //~@002I~
        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsg rc="+rc+",swDoAccount="+swDoAccount);//~@002I~//~var8R~
        return rc;                                                 //~@002I~
    }                                                              //~1AecI~
    //***************************************************************//~0220I~
    private void receivedAppMsgResponseSeqNo(String Pappmsg)       //~0220I~
    {                                                              //~0220I~
        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsgResponseSeqNo msg="+Pappmsg);//~0220I~
    	int pos=MSGR_APPSEQNO.length();                            //~0220I~
        int pos2=Pappmsg.indexOf(MSG_SEP,pos);          //~0220I~
        if (pos2<=pos)                                             //~0220I~
        {                                                          //~0220I~
	        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsgResponseSeqNo no msgno");//~0220I~
        	return;                                          //~0220I~
        }                                                          //~0220I~
        int seqNo=Utils.parseInt(Pappmsg.substring(pos,pos2),-1);  //~0220I~
        if (seqNo==-1)                                             //~0220I~
        {                                                          //~0220I~
	        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsgResponseSeqNo msgno err");//~0220I~
        	return;                                          //~0220I~
        }                                                          //~0220I~
        AG.aUARestart.receivedResponseSeqNo(swServer,idxMember,seqNo);//~0220R~
    	receivedAppMsgResponse(Pappmsg.substring(pos2+1));      //~0220I~
    }                                                              //~0220I~
    //***************************************************************//~@002I~
    private void receivedAppMsgResponse(String Pappmsg)             //~@002I~//~0220R~
    {                                                              //~@002I~
        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsgResponse remotename="+remoteDeviceName);//~@002I~
//        String[] words=parse(Pappmsg);                           //~@002R~
////      String remotename=words[0]; //it is server or other client for info msg from server//~@002R~
//        String remotename=remoteDeviceName; //it is server or other client for info msg from server//~@002R~
//        int threadIdx=AG.aBTMulti.getMemberIndex(remotename);    //~@002R~
//        if (Dump.Y) Dump.println("BTIOThread.receivedAppMsgResponse remotename="+remotename+",idx="+threadIdx+",msg="+Pappmsg);//~@002R~
//        if (threadIdx<0)                                         //~@002R~
//        {                                                        //~@002R~
//            if (Dump.Y) Dump.println("BTIOThread receivedAppMsgResponse remote="+remotename+" not found");//~@002R~
//            return;                                              //~@002R~
//        }                                                        //~@002R~
//        int msgid=Integer.parseInt(words[1]);                    //~@002R~
//        String msg=words.length>2?words[2]:"";                   //~@002R~
//        AG.aACAction.receivedAppMsgResponse(threadIdx,msgid,msg);//~@002R~
    }                                                              //~@002I~
    //***************************************************************//~9825I~
//  private void receivedHistory()                                 //~9825I~//~9828R~
    private void receivedHistory(boolean PswResume)                //~9828I~
    {                                                              //~9825I~
    	String hds=readHistory.toString();                         //~9825I~
        if (Dump.Y) Dump.println("BTIOThread.receivedHistory swResume="+PswResume+",hds="+hds);//~9825I~//~9828R~
//      HistoryDlg.received(propSenderYourName,hds);                //~9825I~//~9828R~
        HistoryDlg.onReceived(PswResume,propSenderYourName,hds);   //~9828I~
    }                                                              //~9825I~
    //***************************************************************//~9404I~
    private void receivedProp()                                    //~9404I~
    {                                                              //~9404I~
        if (Dump.Y) Dump.println("BTIOThread.receivedProp props="+readProp.toString());//~9404I~//~9827R~
        if (swReadPropHistory)                                     //~9826I~
        {                                                          //~9827I~
        	AG.aHistory.saveReceivedRule(readProp);                   //~9826R~//~9827R~
        }                                                          //~9827I~
        else                                                       //~9826I~
        {                                                          //~9827I~
	    	String props=readProp.toString();                          //~9404I~//~9827I~
//      	RuleSetting.received(propSenderYourName,props);                               //~9404I~//~9405R~//~9827R~//~var0R~
        	RuleSettingSumm.received(propSenderYourName,props);    //~var0I~
        }                                                          //~9827I~
    }                                                              //~9404I~
    //***************************************************************//~9406I~
    //*on Server                                                   //~0323I~
    //***************************************************************//~0323I~
    private void receivedPropReply(int PthreadIdx,String[] Pwords) //~9406I~
    {                                                              //~9406I~
        if (Dump.Y) Dump.println("BTIOThread.receivedPropReply threadIdx="+PthreadIdx+",words="+Arrays.toString(Pwords));//~9406I~//~0323R~
//      RuleSetting.receivedReply(PthreadIdx,Pwords[2].equals("1")/*OK-NG*/,Pwords[3]/*syncDate*/);//~9406I~//~var0R~
        RuleSettingSumm.receivedReply(PthreadIdx,Pwords[2].equals("1")/*OK-NG*/,Pwords[3]/*syncDate*/);//~var0I~
    }                                                              //~9406I~
    //***************************************************************//~9406I~
    private void receivedPropSync(int PthreadIdx,String[] Pwords)  //~9406I~
    {                                                              //~9406I~
        if (Dump.Y) Dump.println("BTIOThread.receivedPropSync threadIdx="+PthreadIdx+",words="+Arrays.toString(Pwords));//~9406I~
//      RuleSetting.receivedSyncAll(PthreadIdx,Pwords[2].equals("1")/*OK-NG*/,Pwords[3]/*syncDate*/);//~9406I~//~var0R~
        RuleSettingSumm.receivedSyncAll(PthreadIdx,Pwords[2].equals("1")/*OK-NG*/,Pwords[3]/*syncDate*/);//~var0I~
    }                                                              //~9406I~
    //***************************************************************//~9621I~
    //*on client                                                   //~9621I~
    //***************************************************************//~9621I~
    private void receivedSyncQuery(int PthreadIdx,String[] Pwords) //~9621I~
    {                                                              //~9621I~
        String syncDate=Pwords[2];                                 //~9621R~
	    Status.resetEndgameSomeone();   //restart send/receive     //~9B20I~
        AG.aBTMulti.updateServer(syncDate);                        //~9621I~
        String mySyncDate=getMySyncDate(false/*PswSave*/);         //~9621I~
        if (Dump.Y) Dump.println("BTIOThread.receivedSyncQuery threadIdx="+PthreadIdx+",words="+Arrays.toString(Pwords)+",mySyncDate="+Utils.toString(mySyncDate));//~9621I~
        AG.aBTMulti.sendMsgToServer(true/*appMsg*/,GCM_SETTING_SYNC_RESP,mySyncDate);//~9621I~
    }                                                              //~9621I~
    //***************************************************************//~9621I~
    //*on server                                                   //~9621I~
    //***************************************************************//~9621I~
    private void receivedSyncQueryResp(int PthreadIdx,String[] Pwords)//~9621I~
    {                                                              //~9621I~
        String syncDate=Pwords[2];                                 //~9621R~
        String sender=Pwords[0];                                   //~9621I~
        if (Dump.Y) Dump.println("BTIOThread.receivedSyncQueryResp threadIdx="+PthreadIdx+",words="+Arrays.toString(Pwords)+",syncDate="+Utils.toString(syncDate));//~9621I~//~0119R~
        AG.aBTMulti.receivedSyncQueryResp(sender,syncDate);        //~9621R~
    }                                                              //~9621I~
    //***************************************************************//~9621I~
    //*on server/client                                            //~9621I~
    //***************************************************************//~9621I~
    private void receivedSettingChanged(int PthreadIdx,String[] Pwords)//~9621I~
    {                                                              //~9621I~
        String syncDate=Pwords[2];                                 //~9621I~
        String sender=Pwords[0];                                   //~9621I~
        if (Dump.Y) Dump.println("BTIOThread.receivedSettingChanged threadIdx="+PthreadIdx+",words="+Arrays.toString(Pwords)+",syncDate="+Utils.toString(syncDate));//~9621I~
        AG.aBTMulti.receivedSettingChanged(PthreadIdx,sender,syncDate);//~9621I~
    }                                                              //~9621I~
    //***************************************************************//~9621I~
    //*on client, received SYNCOK,do startgame                     //~9621I~
    //***************************************************************//~9621I~
    private void receivedSyncOK(int PthreadIdx,String[] Pwords)    //~9621I~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("BTIOThread.receivedSyncOK idx="+PthreadIdx+",words="+Arrays.toString(Pwords));//~9621I~
//      new EventCB(ECB_ACTION_STARTGAME).postEvent();//moved to ProfileIcon  //~v@@@I~//~9621I~//~var8R~
        AG.aProfileIcon.startSyncProfile(this,Pwords);                  //~var8R~
    }                                                              //~9621I~
    //***************************************************************//~var8I~
    //*on Server, received 75 client image                         //~var8R~
    //***************************************************************//~var8I~
    private void receivedGetImageC2S(int PthreadIdx,String[] Pwords)//~var8I~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.receivedGetImageC2S idx="+PthreadIdx+",words="+Arrays.toString(Pwords));//~var8R~
        AG.aProfileIcon.receivedRequestImageC2S(PthreadIdx,Pwords);//~var8I~
    }                                                              //~var8I~
    //***************************************************************//~var8I~
    //*on client, received NOTIFY_ALL                              //~var8I~
    //***************************************************************//~var8I~
    private void receivedProfileNotifyAll(int PthreadIdx,String[] Pwords)//~var8I~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.receivedProfileNotifyAll idx="+PthreadIdx+",words="+Arrays.toString(Pwords));//~var8I~
        AG.aProfileIcon.receivedProfileNotifyAll(PthreadIdx,Pwords);//~var8I~
    }                                                              //~var8I~
    //***************************************************************//~var8I~
    //*on Server, received GETIMAGE_C2SR                           //~var8I~
    //***************************************************************//~var8I~
    private byte[] receivedGetImageC2SR(int PthreadIdx,String[] Pwords)//~var8R~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.receivedGetImageC2SR idx="+PthreadIdx+",words="+Arrays.toString(Pwords));//~var8I~
        int sz=Integer.parseInt(Pwords[2]);                        //~var8I~
	    byte[] buff=null;                                          //~var8I~
        if (sz!=0)                                                 //~var8I~
	        buff=receiveByte(sz);                                   //~var8I~
//      AG.aProfileIcon.receivedRequestImageC2SR(PthreadIdx,Pwords,buff);//~var8R~
        return buff;
    }                                                              //~var8I~
    //***************************************************************//~var8I~
    //*on client, received other Image   79                        //~var8R~
    //***************************************************************//~var8I~
    private void receivedSendImageS2C(String[] Pwords/*sender,msgid,len,pidata*/)//~var8R~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.receivedGetImageS2C words="+Arrays.toString(Pwords));//~var8R~
        int sz=Integer.parseInt(Pwords[2]);                        //~var8I~
	    byte[] buff=null;                                          //~var8I~
        if (sz!=0)                                                 //~var8I~
	        buff=receiveByte(sz);                                  //~var8I~
        AG.aProfileIcon.receivedSendImageS2C(Pwords[3],buff);      //~var8R~
    }                                                              //~var8I~
    //***************************************************************//~var8I~
    //*on client, received profile sync com                        //~var8I~
    //***************************************************************//~var8I~
    private void receivedProfileSyncComp()                         //~var8I~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println("BTIOThread.receivedProfileSyncComp");//~var8I~
        AG.aProfileIcon.receivedProfileSyncComp();                 //~var8I~
    }                                                              //~var8I~
//    private void receivedProfileStartSync(int PthreadIdx,String[] Pwords)//~var8R~
//    {                                                            //~var8R~
//        if (Dump.Y) Dump.println("BTIOThread.receivedProfileStartSync idx="+PthreadIdx+",words="+Arrays.toString(Pwords));//~var8R~
//        AG.aProfileIcon.startSyncProfileReceived(this,Pwords);   //~var8R~
//    }                                                            //~var8R~
//    //***************************************************************//~var8R~
//    //*on client                                                 //~var8R~
//    //***************************************************************//~var8R~
//    private void receivedProfileNotifyAll(int PthreadIdx,String[] Pwords)//~var8R~
//    {                                                            //~var8R~
//        if (Dump.Y) Dump.println("BTIOThread.receivedProfileNotifyll idx="+PthreadIdx+",words="+Arrays.toString(Pwords));//~var8R~
//        AG.aProfileIcon.receivedProfileNotifyAll(this,Pwords);   //~var8R~
//    }                                                            //~var8R~
//    //***************************************************************//~var8R~
//    //*on server                                                 //~var8R~
//    //***************************************************************//~var8R~
//    private void receivedProfileNotifyAllResp(int PthreadIdx,String[] Pwords)//~var8R~
//    {                                                            //~var8R~
//        if (Dump.Y) Dump.println("BTIOThread.receivedProfileNotifyllResp idx="+PthreadIdx+",words="+Arrays.toString(Pwords));//~var8R~
//        AG.aProfileIcon.receivedProfileNotifyAllResp(this,Pwords);//~var8R~
//    }                                                            //~var8R~
    //***************************************************************//~9831I~
    //*on client, received RESUMEDLG on resume                     //~9831I~
    //***************************************************************//~9831I~
    private void receivedResumeDlg(int PthreadIdx,String[] Pwords) //~9831I~
    {                                                              //~9831I~
        String senderYourName=AG.aBTMulti.getYourName(PthreadIdx); //~9831I~
        if (Dump.Y) Dump.println("BTIOThread.receivedResumeDlg idx="+PthreadIdx+",senderYourname="+senderYourName+",words="+Arrays.toString(Pwords));//~9831I~
        ResumeDlg.onReceived(PthreadIdx,senderYourName,Pwords);    //~9831R~
    }                                                              //~9831I~
    //***************************************************************//~9622I~
    //*on client, received SYNCOK,do startgame                     //~9622I~
    //***************************************************************//~9622I~
    private void showClosed()                                      //~9622I~
    {                                                              //~9622I~
        if (Dump.Y) Dump.println("BTIOThread.showClosed");         //~9622I~
        String remoteName=AG.aBTMulti.getYourNameName(remoteDeviceName);//~9622I~
        String msg=Utils.getStr(R.string.Info_ConnectionClosed,remoteName);//~9811I~
//      UView.showToast(Utils.getStr(R.string.Info_ConnectionClosed,remoteName));//~9622I~//~9811R~
        GMsg.drawMsgbar(msg);                                      //~9811I~
        UView.showToast(msg);                                      //~9811I~
    }                                                              //~9622I~
}                                                                  //~1AecI~
