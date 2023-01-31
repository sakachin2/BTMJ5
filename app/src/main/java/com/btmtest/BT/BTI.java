//*CID://+vau1R~:                             update#=  196;       //+vau1R~
//**********************************************************************//~v107I~
//2022/10/31 vau1 Ahsv-1amr 2022/10/31 unkown permission on ndroid6(api23); BLUETOOTH_CONNECT/BLUETOOTH_SCAN/BLUETOOTH_ADVERTIZE is from API31//+vau1I~
//                BTI                                              //+vau1I~
//2022/10/18 vatd Api31 recommend not ACCESS_FINE_LOCATION but ACCESS_COARSE_LOCATION. but api29/30 denied by no permission of FINE_LOCATION//~vatdI~
//2022/10/12 vas6 bluetooth scan failes. Api31(Android12) bluetooth permission?//~vas6I~
//2022/03/29 vam8 android12(api31) Bluetooth permission is runtime permission//~vam8I~
//@003:20181103 dismiss aler dialog when interrupted by other app  //~@003I~
//**********************************************************************//~v107I~
package com.btmtest.BT;                                               //~1AedI~

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.bluetooth.BluetoothSocket;                          //~v107I~

import com.btmtest.MainActivity;
import com.btmtest.utils.Alert;
import com.btmtest.utils.UView;
import com.btmtest.R;//~1AedI~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@003I~

import com.btmtest.utils.Dump;                                            //~v107I~//~1AedR~
import com.btmtest.utils.Utils;

import java.util.ArrayList;

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
        AG.aBTI=this;                                              //~2329I~
		swDestroy=false;               //static clear for after finish()//~@@@2I~
      if (AG.swGrantedBluetooth)                                   //~vam8I~
      {                                                            //~vam8I~
	    mBTC=new BTControl();                                      //~v107R~
	    mBTD=new BTDiscover();                                     //~@@@2I~
      }                                                            //~vam8I~
      else                                                         //~vas6I~
      if (AG.swGrantBluetoothFailed)                               //~vas6I~
      {                                                            //~vas6I~
	    mBTC=new BTControl();                                      //~vas6I~
	    mBTD=new BTDiscover();                                     //~vas6I~
      }                                                            //~vas6I~
    }
//********************************************************************//~vam8I~
//*from BTMulti.constructor                                        //~vam8I~
//********************************************************************//~vam8I~
    public static BTI createBTI()                                  //~v107R~//~v@@@R~
    {                                                              //~v107I~
		BTI inst=null;                                             //~v107R~//~v@@@R~
		if (Dump.Y) Dump.println("BTI.createBTI entry swGrantedBluetooth="+AG.swGrantedBluetooth+",swGrantBluetoothFailed="+AG.swGrantBluetoothFailed);//~vas6I~
        try                                                        //~v107I~
        {                                                          //~v107I~
          if (AG.swGrantBluetoothFailed)                           //~vas6R~
          {                                                        //~vas6I~
		    inst=new BTI();                                        //~vas6M~
          }                                                        //~vas6I~
          else                                                     //~vas6I~
          {                                                        //~vas6I~
            chkGrantedPermission();                                //~vam8M~
		    inst=new BTI();                                        //~v107R~//~v@@@R~
		    if (!AG.swGrantedBluetooth)                            //~vam8I~
            {                                                      //~vam8M~
            	//*wait onRequestPermissionResult callback         //~vas6I~
            	return null;                                       //~vam8M~
            }                                                      //~vam8M~
          }                                                        //~vas6I~
            if (!inst.mBTC.BTCreate())	//default adapter chk      //~v107R~
            	inst.mBTC=null;                                    //~v107R~
            else                                                   //~@@@@I~//~@@@2R~
            {                                                      //~@@@2I~
	    		AG.LocalDeviceName=inst.mBTC.getDeviceName();           //~@@@2I~
            }                                                      //~@@@2I~
        }                                                          //~v107I~
        catch(Exception e)                                         //~2329I~
        {                                                          //~2329I~
            Dump.println(e,"BTI.createBTI");                       //~2329I~
        }                                                          //~2329I~
        catch(Throwable e)                                         //~v107R~
        {                                                          //~v107I~
//          Dump.println(e.toString());                            //~v107R~//~2329R~
            Dump.println("BTI.createBTI Throwable="+e.toString()); //~2329I~
        }                                                          //~v107I~
		if (Dump.Y) Dump.println("BTI.createBTI exit inst="+inst); //~2329R~
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
//*from BTMulti                                                    //~vam8R~
//********************************************************************//~@003I~
    public void destroy()                                          //~v107I~
    {                                                              //~v107I~
		if (Dump.Y) Dump.println("BTI:destroy swGrantedBluetooth="+AG.swGrantedBluetooth);                   //~@@@2I~//~v@@@R~//~vam8R~
        if (!AG.swGrantedBluetooth)                                //~vam8I~
        {                                                          //~vam8I~
	        if (Dump.Y) Dump.println("BTI.destroy return NOP by swGrantedBluetooth");//~vam8I~
        	return;                                                //~vam8I~
        }                                                          //~vam8I~
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
        if (Dump.Y) Dump.println("BTI.onResume swGrantedBluetooth="+AG.swGrantedBluetooth);                 //~1A6bI~//~v@@@R~//~vam8R~
        if (!AG.swGrantedBluetooth)                                //~vam8I~
        {                                                          //~vam8I~
	        if (Dump.Y) Dump.println("BTI.onResume return NOP by swGrantedBluetooth");//~vam8I~
        	return;                                                //~vam8I~
        }                                                          //~vam8I~
	    BTDiscover.register();                                          //~1A6bI~//~v@@@R~
        if (mBTC!=null)                                            //~@003I~
	        mBTC.onResume();	//handle Message                   //~@003I~
    }                                                              //~1A6bI~
//***************************************************************************//~vam8I~
    public void onPause()                                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("BTI.onPause swGrantedBluetooth="+AG.swGrantedBluetooth);//~vam8I~
        if (!AG.swGrantedBluetooth)                                //~vam8I~
        {                                                          //~vam8I~
	        if (Dump.Y) Dump.println("BTI.onPause return NOP by swGrantedBluetooth");//~vam8I~
        	return;                                                //~vam8I~
        }                                                          //~vam8I~
        BTDiscover.cancelDiscover();                               //~v@@@I~
	    BTDiscover.unregister();                                   //~v@@@I~
        if (mBTC!=null)                                            //~@003I~
	        mBTC.onPause();	//handle Message                       //~@003I~
    }                                                              //~v@@@I~
//***************************************************************************//~vam8I~
    public void onDestroy()                                        //~@003I~
    {                                                              //~@003I~
        if (Dump.Y) Dump.println("BTI.onDestroy swGrantedBluetooth="+AG.swGrantedBluetooth);//~vam8R~
        if (!AG.swGrantedBluetooth)                                //~vam8I~
        {                                                          //~vam8I~
	        if (Dump.Y) Dump.println("BTI.onDestroy return NOP by swGrantedBluetooth");//~vam8I~
        	return;                                                //~vam8I~
        }                                                          //~vam8I~
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
//********************************************************************//~0123I~
    public static void onDisconnectedIP(String Pdevname,boolean PswServer)//~0123I~
    {                                                              //~0123I~
		if (Dump.Y) Dump.println("BTI:onDisconnectedIP dev="+Pdevname+",swServer="+PswServer);//~0123I~
		AG.aBTI.mBTC.onDisconnectedIP(Pdevname,PswServer);                 //~0123I~
                                                                   //~0123I~
    }                                                              //~0123I~
//********************************************************************//~vam8I~
    private static boolean chkGrantedPermission()                  //~vam8R~
    {                                                              //~vam8I~
        if (Dump.Y) Dump.println("BTI.chkGrantedPermission osVerison="+AG.osVersion+",AG.swGrantedBluetooth="+AG.swGrantedBluetooth);//~vam8R~
        if (AG.osVersion>=31)                                      //~vam8I~
        {                                                          //~vam8I~
			if (!AG.swGrantedBluetooth)                            //~vam8I~
				AG.swGrantedBluetooth=chkGrantedPermission_from31();//~vam8R~
        }                                                          //~vam8I~
        else                                                       //~vam8I~
        {                                                          //~vas6I~
			if (!AG.swGrantedBluetooth)                            //~vas6I~
				AG.swGrantedBluetooth=chkGrantedPermission_upto30();//~vas6I~
        	AG.swGrantedBluetooth=true;                            //~vam8I~
        }                                                          //~vas6I~
        boolean rc=AG.swGrantedBluetooth;                          //~vam8R~
        if (Dump.Y) Dump.println("BTI.chkGrantedPermission exit rc="+rc);//~vam8R~
        return rc;                                                 //~vam8I~
    }                                                              //~vam8I~
//********************************************************************//~vam8I~
    @TargetApi(31)                                                 //~vam8I~
    private static boolean chkGrantedPermission_from31()           //~vam8R~
    {                                                              //~vam8I~
        if (Dump.Y) Dump.println("BTI.chkGrantedPermission_from31");//~vam8R~
        ArrayList<String> requestList=new ArrayList<>();           //~vas6I~
        boolean swGrantedConnect=UView.isPermissionGranted(Manifest.permission.BLUETOOTH_CONNECT);//~vam8I~
        boolean swGrantedScan=UView.isPermissionGranted(Manifest.permission.BLUETOOTH_SCAN);//~vam8I~
                                                                   //~vas6I~
        if (!swGrantedConnect)                                     //~vas6I~
        	requestList.add(Manifest.permission.BLUETOOTH_CONNECT);//~vas6I~
        if (!swGrantedScan)                                        //~vas6I~
        	requestList.add(Manifest.permission.BLUETOOTH_SCAN);   //~vas6I~
        if (!UView.isPermissionGranted(Manifest.permission.BLUETOOTH_ADVERTISE))//~vas6I~
        	requestList.add(Manifest.permission.BLUETOOTH_ADVERTISE);//~vas6I~
//      if (!UView.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION))//~vatdR~
//      	requestList.add(Manifest.permission.ACCESS_FINE_LOCATION);//~vatdR~
        UView.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION);    //chk only//~vatdI~
        if (!UView.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION))//~vatdR~
        	requestList.add(Manifest.permission.ACCESS_COARSE_LOCATION);//~vatdR~
//      if (Dump.Y) Dump.println("BTI.chkGrnatedPermission_from31 isGranted FINE_LOCATION="+UView.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION));//~vas6R~
//      boolean rc=swGrantedConnect && swGrantedScan;              //~vam8I~//~vas6R~
//      if (!rc)                                                   //~vam8I~//~vas6R~
//      {                                                          //~vam8I~//~vas6R~
//      	String[] types=new String[]{Manifest.permission.BLUETOOTH_CONNECT,Manifest.permission.BLUETOOTH_SCAN};//~vam8I~//~vas6R~
//      	UView.requestPermission(types, MainActivity.PERMISSION_BLUETOOTH);//~vam8I~//~vas6R~
//      }                                                          //~vam8I~//~vas6R~
        boolean rc=true;                                           //~vas6I~
        if (!requestList.isEmpty())                                //~vas6I~
        {                                                          //~vas6I~
	      	String[] reqs=(String[])(requestList.toArray(new String[requestList.size()]));//~vas6R~
	        if (Dump.Y) Dump.println("BTI.chkGrnatedPermission_from31 requestPermission list="+Utils.toString(reqs));//~vas6I~
	      	UView.requestPermission(reqs,MainActivity.PERMISSION_BLUETOOTH);//~vas6I~
            rc=false;                                              //~vas6I~
        }                                                          //~vas6I~
        if (Dump.Y) Dump.println("BTI.chkGrnatedPermission_from31 exit");//~vas6R~
        return rc;
    }                                                              //~vam8I~
//********************************************************************//~vas6I~
    private static boolean chkGrantedPermission_upto30()           //~vas6I~
    {                                                              //~vas6I~
        if (Dump.Y) Dump.println("BTI.chkGrantedPermission_upto30");//~vas6I~
        ArrayList<String> requestList=new ArrayList<>();           //~vas6I~
//      UView.isPermissionGranted(Manifest.permission.BLUETOOTH_CONNECT);   //for debug//~vas6R~//+vau1R~
//      UView.isPermissionGranted(Manifest.permission.BLUETOOTH_SCAN);      //for debug//~vas6R~//+vau1R~
//      UView.isPermissionGranted(Manifest.permission.BLUETOOTH_ADVERTISE);	//for debug//~vas6R~//+vau1R~
        if (!UView.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION))//~vas6R~
        {                                                          //~vas6I~//~vatdR~
        	if (AG.osVersion>=29) 	//android10,11                 //~vas6R~//~vatdR~
            	requestList.add(Manifest.permission.ACCESS_FINE_LOCATION);//~vas6R~
        }                                                          //~vas6I~
        if (!UView.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION))//~vatdI~
        {                                                          //~vatdI~
            requestList.add(Manifest.permission.ACCESS_COARSE_LOCATION);//~vatdR~
        }                                                          //~vatdI~
        boolean rc=true;                                           //~vas6I~
        if (!requestList.isEmpty())                                //~vas6I~
        {                                                          //~vas6I~
	      	String[] reqs=(String[])(requestList.toArray(new String[requestList.size()]));//~vas6I~
	        if (Dump.Y) Dump.println("BTI.chkGrnatedPermission_upto30 requestPermission list="+Utils.toString(reqs));//~vas6R~
          	UView.requestPermission(reqs,MainActivity.PERMISSION_BLUETOOTH); //TODO test//~vas6R~
            rc=false;                                              //~vas6I~
        }                                                          //~vas6I~
        if (Dump.Y) Dump.println("BTI.chkGrnatedPermission_upto30 exit");//~vas6I~
        return rc;                                                 //~vas6I~
    }                                                              //~vas6I~
//********************************************************************//~vam8I~
//*from MAinActivity                                               //~vam8I~
//********************************************************************//~vam8I~
//    public static void grantedPermission(boolean PswGranted)       //~vam8I~//~vas6R~
//    {                                                              //~vam8I~//~vas6R~
//        if (Dump.Y) Dump.println("BTI.grantedPermission swGranted="+PswGranted);//~vam8I~//~vas6R~
//        if (!PswGranted)                                           //~vam8I~//~vas6R~
//        {                                                          //~vam8I~//~vas6R~
//            UView.showToastLong(R.string.failedBluetoothPermission);//~vam8I~//~vas6R~
//            return;                                                //~vam8I~//~vas6R~
//        }                                                          //~vam8I~//~vas6R~
//        AG.swGrantedBluetooth=true;                                //~vam8I~//~vas6R~
//        createBTI();                                               //~vam8I~//~vas6R~
//    }                                                              //~vam8I~//~vas6R~
    public static void grantedPermission(String[] Pname,int[] Presults/*0:granted,denied*/)//~vas6R~
    {                                                              //~vas6I~
        if (Dump.Y) Dump.println("BTI.grantedPermission name="+ Utils.toString(Pname)+",result="+Utils.toString(Presults));//~vas6I~
        boolean ok=true;                                           //~vas6I~
        boolean swFailedAdvertize=false;                           //~vas6I~
        for (int ii=0;ii<Pname.length;ii++)                        //~vas6I~
        {                                                          //~vas6I~
        	if (!UView.isPermissionGranted(Presults[ii]))           //~vas6I~
            {                                                      //~vas6I~
                if (Pname[ii].equals(Manifest.permission.BLUETOOTH_ADVERTISE))//~vas6I~
                {                                                  //~vas6I~
                    AG.swFailedGrantBluetoothAdvertize=true;       //~vas6R~
//	            	without this permission, pairing available     //~vas6I~
//	            	UView.showToastLong(Utils.getStr(R.string.failedBluetoothPermissionTypeAdvertize,Pname[ii]));//~vas6R~
                }                                                  //~vas6I~
                else                                               //~vas6I~
                {                                                  //~vas6I~
            		ok=false;                                      //~vas6R~
	            	UView.showToastLong(Utils.getStr(R.string.failedBluetoothPermissionType,Pname[ii]));//~vas6R~
                }                                                  //~vas6I~
            }                                                      //~vas6I~
        }                                                          //~vas6I~
        if (ok)                                                    //~vas6I~
        	AG.swGrantedBluetooth=true;                            //~vas6I~
        else                                                       //~vas6I~
        	AG.swGrantBluetoothFailed=true;                        //~vas6I~
        createBTI();                                               //~vas6I~
        	                                                       //~vas6I~
    }                                                              //~vas6I~
}//class                                                           //~1109R~