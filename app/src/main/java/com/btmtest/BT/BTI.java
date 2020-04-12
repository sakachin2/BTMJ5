//*CID://+DATER~:                             update#=  158;       //~@003R~//~9210R~
//**********************************************************************//~v107I~
//@003:20181103 dismiss aler dialog when interrupted by other app  //~@003I~
//**********************************************************************//~v107I~
package com.btmtest.BT;                                               //~1AedI~

import android.content.Intent;
import android.bluetooth.BluetoothSocket;                          //~v107I~

import com.btmtest.utils.Alert;
import com.btmtest.utils.UView;
import com.btmtest.R;//~1AedI~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@003I~

import com.btmtest.utils.Dump;                                            //~v107I~//~1AedR~

//********************************************************************//~1212I~
//* Interface to BT                                                //~v107R~
//********************************************************************//~1212I~
public class BTI                                               //~1122R~//~v107R~//~v@@@R~
//         implements DoActionListener                             //~v107R~
{                                                                  //~1109I~
	public BTControl mBTC;                                        //~v107R~
	public  BTDiscover mBTD;                                       //~@@@2I~
	private BluetoothSocket mBTSocket;                             //~v107R~
//  	private PartnerFrame partnerFrame;                             //~v107I~
    private String mDeviceName;                                    //~v107I~
    private String requestDeviceName="Unknown";                    //~@@@2I~
    public  boolean swConnect;                                     //~1AbcI~//~1Ac5I~
	public boolean swDestroy;                                      //~@@@2R~
	private boolean swSecureConnect;                               //~1AedI~
    public boolean swNFC,swSecureNFC;                              //~1AbbI~
    public boolean swSecureNonNFCAccept;                           //~1AbtI~
    public BTI()                                                   //~v107R~//~v@@@R~
    {                                                              //~1329I~
        if (Dump.Y) Dump.println("===========BTI start============");//~@@@2I~//~v@@@R~
		swDestroy=false;               //static clear for after finish()//~@@@2I~
	    mBTC=new BTControl();                                      //~v107R~
	    mBTD=new BTDiscover();                                     //~@@@2I~
    }
    public static BTI createBTI()                                  //~v107R~//~v@@@R~
    {                                                              //~v107I~
		BTI inst=null;                                             //~v107R~//~v@@@R~
        try                                                        //~v107I~
        {                                                          //~v107I~
		    inst=new BTI();                                        //~v107R~//~v@@@R~
            if (!inst.mBTC.BTCreate())	//default adapter chk      //~v107R~
            	inst.mBTC=null;                                    //~v107R~
            else                                                   //~@@@@I~//~@@@2R~
            {                                                      //~@@@2I~
	    		AG.LocalDeviceName=inst.mBTC.getDeviceName();           //~@@@2I~
            }                                                      //~@@@2I~
        }                                                          //~v107I~
        catch(Throwable e)                                         //~v107R~
        {                                                          //~v107I~
            Dump.println(e.toString());                            //~v107R~
        }                                                          //~v107I~
        return inst;                                               //~v107I~
    }                                                              //~v107I~
//********************************************************************//~@@@2I~
    public static boolean startListen()                        //~@@@2R~
    {                                                              //~@@@2I~
    	boolean rc=false;                                          //~@@@2I~
		BTI inst=AG.aBTI;                                           //~@@@2I~//~v@@@R~
        if (inst.mBTC==null)                                       //~@@@2I~
        {                                                          //~@@@2I~
			return BTNotAvailable();                               //~1AbRI~
        }                                                          //~@@@2I~
        try                                                        //~@@@2I~
        {                                                          //~@@@2I~
        	rc=inst.startServer();                                    //~@@@2I~//~v101R~
        }                                                          //~@@@2I~
        catch(Throwable e)                                         //~@@@2I~
        {                                                          //~@@@2I~
            Dump.println(e.toString());                            //~@@@2I~
			BTNotAvailable();                                      //~1AbRI~
        }                                                          //~@@@2I~
//        AG.isNFCBT=false;                                          //~1AbgR~
        return rc;                                                 //~@@@2I~
    }                                                              //~@@@2I~
//********************************************************************//~1AbbI~
//* NFCBT:startServer with secure option                           //~1AbbI~
//********************************************************************//~1AbbI~
    public static boolean startListen(boolean Psecure)             //~1AbbI~
    {                                                              //~1AbbI~
		BTI inst=AG.aBTI;                                           //~1AbbI~//~v@@@R~
        inst.swNFC=true; //parm to BTstartServer()-->BTService.start()//~1AbbI~
    	inst.swSecureNFC=Psecure;                                  //~1AbbI~
	    boolean rc=startListen();                                  //~1AbbI~
	    return rc;                                                 //~1AbbI~
    }                                                              //~1AbbI~
//********************************************************************//~1AbtI~
//* from Bluetooth                                                 //~1AbtI~
//* strtServer with secure option                                  //~1AbtI~
//********************************************************************//~v107I~
    public void resume()                                           //~v107I~
    {                                                              //~v107I~
        try                                                        //~v107I~
        {                                                          //~v107I~
        	if (mBTC!=null)                                              //~v107I~
			    mBTC.BTResume();                                   //~v107R~
        }                                                          //~v107I~
        catch(Exception e)                                         //~v107R~
        {                                                          //~v107I~
            Dump.println(e,"BTI:resume");                          //~v107R~//~v@@@R~
            UView.showToast(R.string.failedBluetooth);             //~v107R~//~v@@@R~
        }                                                          //~v107I~
    }                                                              //~v107I~
//********************************************************************//~v107I~
//*from Main by finish, onDestry                                   //~@003I~
//********************************************************************//~@003I~
    public void destroy()                                          //~v107I~
    {                                                              //~v107I~
		if (Dump.Y) Dump.println("BTI:destroy");                   //~@@@2I~//~v@@@R~
    	swDestroy=true;                                            //~@@@2I~
        try                                                        //~v107I~
        {                                                          //~v107I~
        	if (mBTC!=null)                                              //~v107I~
            {                                                      //~@003I~
		    	mBTC.BTDestroy();                                  //~v107R~
                mBTC=null;                                         //~@003I~
            }                                                      //~@003I~
            if (mBTSocket!=null)                                   //~@@@2I~
            {                                                      //~@@@2I~
				if (Dump.Y) Dump.println("BTI:destroy:close mBTSocket="+mBTSocket.toString());//~@@@2R~//~v@@@R~
            	mBTSocket.close();                                 //~@@@2I~
                mBTSocket=null;                                     //~@@@2I~
            }                                                      //~@@@2I~
        }                                                          //~v107I~
        catch(Exception e)                                         //~v107I~
        {                                                          //~v107I~
            Dump.println(e,"BTI:destroy");                         //~v107R~//~v@@@R~
            UView.showToast(R.string.failedBluetooth);             //~v107R~//~v@@@R~
        }                                                          //~v107I~
    }                                                              //~v107I~
//*************************************************************************//~v107I~
    public void activityResult(int requestCode, int resultCode, Intent data)//~v107I~
    {                                                              //~v107I~
        try                                                        //~v107I~
        {                                                          //~v107I~
	    	mBTC.BTActivityResult(requestCode,resultCode,data);    //~v107R~
        }                                                          //~v107I~
        catch(Exception e)                                         //~v107I~
        {                                                          //~v107I~
            Dump.println(e,"BTI:activityResult");                  //~v107R~//~v@@@R~
            UView.showToast(R.string.failedBluetooth);             //~v107R~//~1AedR~//~v@@@R~
        }                                                          //~v107I~
    }                                                              //~v107I~
//***************************************************************************//~v107I~
    public void setDiscoverable()                                  //~v107R~
    {                                                              //~v107I~
    	if (Dump.Y) Dump.println("BTI:setDiscoverable");           //~v107R~//~v@@@R~
        if (mBTC==null)                                            //~v107I~
        {                                                          //~1AbRI~
			BTNotAvailable();                                      //~1AbRI~
            return;                                                //~v107I~
        }                                                          //~1AbRI~
        if (mBTC.BTensureDiscoverable(true/*request discoverable if not*/)==0)	//already discoverable//~v107R~
            UView.showToast(R.string.nowDiscoverable);             //~v107R~//~1AedR~//~v@@@R~
    }                                                              //~v107I~
//***************************************************************************//~@@@@I~//~v107M~
    public boolean startServer()                                      //~v107R~//~v101R~
    {                                                              //~v107I~
        boolean rc=false;                                          //~v101I~
    //*******************                                          //~v107I~
    	if (Dump.Y) Dump.println("BTI:startServer mBTC!=null="+(mBTC!=null)+",mBTSocket!=null="+(mBTSocket!=null));               //~v107R~//~v@@@R~
        if (mBTC==null)                                            //~v107I~
            return false;                                                //~v107I~//~v101R~
//        if (MainFrame.isAliveOtherSession(AG.AST_BT,true/*duper*/))//~1A8gI~//~1AedR~
//            return false;                                          //~1Aa0R~//~1AedR~
        rc=mBTC.BTstartServer();	//true if accept issued    //~v101I~//~v@@@R~
        return rc;                                                 //~v101I~
    }                                                              //~v107I~
//***************************************************************************//~@@@2I~
//* device addr specified connect                                  //~@@@2I~
//***************************************************************************//~@@@2I~
    public Boolean connect(String Pname,String Paddr,boolean Psecure) //~1A60I~//~v@@@R~
    {                                                              //~@@@2I~
    	int rc;                                                    //~@@@2I~
    //**********************                                       //~@@@2I~
    	swSecureConnect=Psecure;                                   //~1AedI~
	    requestDeviceName=Pname;                                   //~@@@2I~
    	if (Dump.Y) Dump.println("BTI:connect device="+Pname+",addr="+Paddr);//~@@@2R~//~v@@@R~
        if (mBTC==null)                                            //~@@@2I~
            return false;                                                 //~@@@2I~//~v@@@R~
        swConnect=true;                                            //~@@@2I~
        rc=mBTC.BTconnect(Paddr,Psecure);                          //~1A60I~
        if (rc==1)	//connecting                                   //~@@@2I~
        {                                                          //~@@@2I~
    		UView.showToastLong(R.string.Already_trying_this_connection);//~1A63I~//~1AedR~//~v@@@R~
	        swConnect=false;                                       //~v@@@I~
            return false;                                          //~v@@@I~
  		}                                                          //~@@@2I~
        return true;                                               //~v@@@I~
    }                                                              //~@@@2I~
//***************************************************************************//~9210I~
    public Boolean unpair(String Pname,String Paddr)               //~9210I~
    {                                                              //~9210I~
    	boolean rc;                                                    //~9210I~
    //**********************                                       //~9210I~
	    requestDeviceName=Pname;                                   //~9210I~
    	if (Dump.Y) Dump.println("BTI:unpair device="+Pname+",addr="+Paddr);//~9210I~
        if (mBTC==null)                                            //~9210I~
            return false;                                          //~9210I~
        rc=mBTC.BTunpair(Pname,Paddr);                             //~9210R~
        return rc;                                                 //~9210I~
    }                                                              //~9210I~
//***************************************************************************//~v107I~
//*from BTControl;after request enable completed                   //~v107R~
//**************************************************************************//~v107I~
    public void enabled()                                          //~v107R~
    {                                                              //~v107I~
    	if (Dump.Y) Dump.println("BTI:enabled swServer="+swConnect);//~v107R~//~@@@@R~//~v@@@R~
		    Alert.showAlert(0,R.string.InfoBTEnabled,Alert.BUTTON_CLOSE,null/*callback*/);//~v101I~
    }                                                              //~v107I~
//***************************************************************************//~v107I~
//*from BTControl;on MainThread                                    //~v107I~
//***************************************************************************//~v107I~
    public void connected(BluetoothSocket Psocket,String Pdevicename)//~v107I~
    {                                                              //~v107I~
    	if (Dump.Y) Dump.println("BTI:connected swConnect="+swConnect+",device="+Pdevicename);//~v107R~//~@@@@R~//~1AbRI~//~v@@@R~
    	mBTSocket=Psocket;                                         //~v107I~
    	mDeviceName=Pdevicename;                                   //~v107I~
        AG.aBTMulti.onConnected(Psocket,Pdevicename,mBTC.getDeviceName(),swConnect);//~v@@@I~
        swConnect=false;                                           //~@@@2I~
    }                                                              //~v107I~
//***************************************************************************//~@@@@I~
    public boolean isConnectionAlive()                                //~@@@@I~
    {                                                              //~@@@@I~
    	return (mBTC!=null)&&mBTC.isConnectionAlive();             //~@@@@I~
    }                                                              //~@@@@I~
//***************************************************************************//~@@@2I~
    public boolean stopListen()                                    //~@@@2I~
    {                                                              //~@@@2I~
    	return (mBTC!=null)&&mBTC.stopListen();                    //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~v@@@I~
    public boolean stopConnect()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	return (mBTC!=null)&&mBTC.stopConnect();                   //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************************//~@@@2I~
//*BTService-->BTControl-->                                        //~@@@2I~
//***************************************************************************//~@@@2I~
    public void connFailed(int Pflag)                               //~@@@2I~//~v@@@R~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("BTI:connFailed "+Pflag);          //~@@@2I~//~v@@@R~
        swConnect=false;                                           //~@@@2I~
        AG.aBTMulti.onConnectionFailed(Pflag,AG.LocalDeviceName);                   //~v@@@I~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public void acceptFailed(String Psecure)                       //~@@@2I~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("BTI:acceptFailed "+Psecure);     //~@@@2I~//~v@@@R~
        AG.aBTMulti.onAcceptFailed(Psecure);                    //~v@@@I~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public String[] getPairDeviceList()                            //~@@@2R~
    {                                                              //~@@@2I~
        if (mBTC==null)                                            //~@@@2I~
            return null;                                           //~@@@2I~
        if (!mBTC.BTEnable(false/*not resume*/))                                      //~v101I~
            return null;                                           //~v101I~
        return BTDiscover.getPairDeviceList();                     //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public boolean discover()                 //~@@@2R~
    {                                                              //~@@@2I~
        if (mBTC==null)                                            //~@@@2I~
			return BTNotAvailable();                               //~1AbRI~
        return mBTC.discover();                              //~@@@2R~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public boolean cancelDiscover()                                //~@@@2I~
    {                                                              //~@@@2I~
        if (mBTC==null)                                            //~@@@2I~
            return false;                                          //~@@@2I~
        return mBTC.cancelDiscover();                              //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~@@@2I~
    public String[] getNewDevice()                                 //~@@@2I~
    {                                                              //~@@@2I~
        return BTDiscover.newDevice;                             //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~1A6bI~
//*from AMain                                                      //~1A6bI~
//***************************************************************************//~1A6bI~
    public void onResume()                                        //~1A6bI~//~v@@@R~
    {                                                              //~1A6bI~
        if (Dump.Y) Dump.println("BTI onResume");                 //~1A6bI~//~v@@@R~
	    BTDiscover.register();                                          //~1A6bI~//~v@@@R~
        if (mBTC!=null)                                            //~@003I~
	        mBTC.onResume();	//handle Message                   //~@003I~
    }                                                              //~1A6bI~
    public void onPause()                                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("BTI onPause");                   //~v@@@I~
        BTDiscover.cancelDiscover();                               //~v@@@I~
	    BTDiscover.unregister();                                   //~v@@@I~
        if (mBTC!=null)                                            //~@003I~
	        mBTC.onPause();	//handle Message                       //~@003I~
    }                                                              //~v@@@I~
    public void onDestroy()                                        //~@003I~
    {                                                              //~@003I~
        if (Dump.Y) Dump.println("BTI onDestroy");                 //~@003I~
        destroy();                                                 //~@003I~
    }                                                              //~@003I~
//***************************************************************************//~1AbRI~
	public static boolean BTNotAvailable()                         //~1AbRI~
    {                                                              //~1AbRI~
        UView.showToast(R.string.BTNotAvalable);                   //~1AbRI~//~1AedR~//~v@@@R~
        return false;                                              //~1AbRI~
    }                                                              //~1AbRI~
//***************************************************************************//~1Ac5I~
	public static boolean BTisDiscoverable()                       //~1Ac5I~
    {                                                              //~1Ac5I~
        return AG.aBTI.mBTC.BTisDiscoverable()==1;                  //~1Ac5I~//~v@@@R~
    }                                                              //~1Ac5I~
//***************************************************************************//~1AedI~
	public void notifyStatusChanged(int Pstatus)                   //~v@@@R~
    {                                                              //~1AedI~
        if (Dump.Y) Dump.println("BTI notifyStatusChanged");                   //~1AedI~//~v@@@R~
        if (mBTC!=null)                                            //~v@@@I~
        	mBTC.notifyStatusChanged(Pstatus);                     //~v@@@R~
    }                                                              //~1AedI~
//***************************************************************************//~v@@@I~
	public void stopAll()                                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("BTI stopAll");                   //~v@@@I~
        if (mBTC!=null)                                            //~v@@@I~
        	mBTC.stopAll();                                        //~v@@@I~
    }                                                              //~v@@@I~
//********************************************************************//~1AedI~
    public void closeStream()                                      //~1AedI~
    {                                                              //~1AedI~
		if (Dump.Y) Dump.println("BTI:closeStream");               //~1AedI~//~v@@@R~
    }                                                              //~1AedI~
//********************************************************************//+0123I~
    public static void onDisconnectedIP(String Pdevname,boolean PswServer)//+0123I~
    {                                                              //+0123I~
		if (Dump.Y) Dump.println("BTI:onDisconnectedIP dev="+Pdevname+",swServer="+PswServer);//+0123I~
		AG.aBTI.mBTC.onDisconnectedIP(Pdevname,PswServer);                 //+0123I~
                                                                   //+0123I~
    }                                                              //+0123I~
}//class                                                           //~1109R~