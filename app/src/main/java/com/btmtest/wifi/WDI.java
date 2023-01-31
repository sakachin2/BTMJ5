//*CID://+vaf0R~:                             update#=  124;       //~vaf0R~
//*************************************************************************//~1A65I~
//2021/10/21 vaf0 Play console crash report "IllegalStateException" at FragmentManagerImple.1536(checkStateLoss)//~vaf0I~
//*************************************************************************//~vaf0I~
//*like as BTI, interfase to WifiDirect                            //+vaf0I~
//*************************************************************************//+vaf0I~
package com.btmtest.wifi;                                               //~v@@@I~//~9719I~

import static com.btmtest.AG.*;
import static com.btmtest.StaticVars.AG;                           //~@@@@M~//~9719I~

import com.btmtest.R;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

public class WDI                                                   //~9719R~
{                                                                  //~1A65I~
    public  static final int NFC_SERVER=1;    //identify connection type//~1A6BI~//~9719I~
    public  static final int NFC_CLIENT=2;                         //~1A6BI~//~9719I~
    public  static final int WD_SERVER=3;                          //~1A6BI~//~9719I~
    public  static final int WD_CLIENT=4;                          //~1A6BI~//~9719I~
    public  static final String PKEY_SERVER_PORT="ServerPort";     //~9719I~
    public  static final String WDCLIENT_IPPREFIX="ClientOf-";     //~9719I~
                                                                   //~0113I~
    private WDIReceiver WDIR;                                      //~9722I~//~0113R~
    //***********************************************************************//~v101I~//~9719I~
    private WDI()                                                   //~9722I~
    {                                                              //~9722I~
        if (Dump.Y) Dump.println("WDI:constructor");               //~0113I~
        AG.aWDI=this;                                              //~9728I~
        WDIR=new WDIReceiver();  //BroadcastReceiver active when WDA is not opened//~0113R~
    }                                                              //~9722I~
    //***********************************************************************//~9722I~
    //*from MenuDlgConnect                                         //~9722I~
    //***********************************************************************//~9722I~
    public static void startRemoteIP()                                   //~v101I~//~9719I~//~9722R~
    {                                                              //~v101I~//~9719I~
        if (Dump.Y) Dump.println("WDI:startRemoteIP remoteStatus="+AG.RemoteStatus);//~9719I~//~9722M~
    	if (AG.aWDI==null)                                         //~9722I~
        	new WDI();                                     //~9722I~//~9728R~
//        if ((AG.RemoteStatus & RS_BT)!=0) //chked alread atMenuDialogConnection//~0118R~
//        {                                                          //~9719I~//~0118R~
////          new Message(this,R.string.ErrNowBTConnected);          //~9719R~//~0118R~
//            Alert.showMessage(0/*title*/,R.string.ErrNowBTConnected);//~9719I~//~0118R~
//            return;                                                //~9719I~//~0118R~
//        }                                                          //~9719I~//~0118R~
    	if (isAliveOtherSession(AST_WD,false/*dupok*/))         //~1A8gR~//~9719I~//~9722R~
            return;                                                //~1A8gR~//~9719I~
		new IPConnectionWD();	//open dialog              //~1A90I~   //~9719R~//~9722R~
    }                                                              //~1A90I~//~9719I~
//*****************************************************************************//~1A8gI~//~9719I~
//*sessiontype:1:LAN,2:WifiDirect,3:BT                             //~1A8gI~//~9719I~
//*****************************************************************************//~1A8gI~//~9719I~
    private static boolean isAliveOtherSession(int Psessiontype,boolean Pduperr)//~1A8gI~//~9719I~
    {                                                              //~1A8gI~//~9719I~
    	int active=AG.activeSessionType;                           //~1A8gI~//~9719I~
        if (active!=0)                                             //~1A8gI~//~9719I~
            if (active!=Psessiontype||Pduperr)                     //~1A8gI~//~9719I~
            {                                                      //~1A8gI~//~9719I~
		        String session="";                                 //~1A91R~//~9719I~
                switch(active)                                     //~1A91R~//~9719I~
                {                                                  //~1A91R~//~9719I~
                case AST_IP:                                    //~1A91R~//~9719R~
                    session=AG.resource.getString(R.string.RemoteIP);//~1A91R~//~9719R~
                	break;                                         //~1A91R~//~9719R~
                case AST_WD:                                    //~1A91R~//~9719I~
//                  session=AG.resource.getString(R.string.WiFiDirect)+"("+AG.resource.getString(R.string.WiFiNFCButton)+")";//~1A91R~//~9719R~
                    session=AG.resource.getString(R.string.WifiDirect);//~9719I~
                	break;                                         //~1A91R~//~9719I~
                case AST_BT:                                    //~1A91R~//~9719I~
                    session=AG.resource.getString(R.string.Bluetooth);//~1A91R~//~9719I~
                	break;                                         //~1A91R~//~9719I~
                }                                                  //~1A91R~//~9719I~
                String msg=AG.resource.getString(R.string.ErrOtherActiveSession,session);//~1A91R~//~9719I~
//  	    	int flag= Alert.BUTTON_POSITIVE;                    //~1AbdI~//~9719R~
//  			Alert.simpleAlertDialog(null/*callback*/,null/*title*/,msg,flag);//~1314I~//~@@@@R~//~1AbdI~//~9719R~
	    		Alert.showMessage(""/*title*/,msg);                //~9719I~
                return true;                                       //~1A8gI~//~9719I~
            }                                                      //~1A8gI~//~9719I~
                                                                   //~1A8gI~//~9719I~
        return false;                                              //~1A8gI~//~9719I~
  	}                                                              //~1A8gI~//~9719I~
    //******************************************                   //~1AbBI~//~9719I~
    public static String maskMacAddr(String Pmacaddr)              //~1AbBI~//~9719I~
    {                                                              //~1AbBI~//~9719I~
    	if (Pmacaddr==null)                                        //~1AbBI~//~9719I~
        	return "";                                             //~1AbBI~//~9719I~
        if (Pmacaddr.length()!=17)                                 //~1AbBI~//~9719I~
        	return Pmacaddr;                                       //~1AbBI~//~9719I~
    	return Pmacaddr.substring(0,6)+"...";                      //~1AbBI~//~9719I~
    }                                                              //~1AbBI~//~9719I~
    //******************************************                   //~0113I~
    public static void onResume()                                  //~0113I~
    {                                                              //~0113I~
    	if (AG.aWDI==null)                                         //~0113I~
        	return;                                                //~0113I~
        if (Dump.Y) Dump.println("WDI.onResume WDIR="+ Utils.toString(AG.aWDI.WDIR));//~0113R~
    	if (AG.aWDI.WDIR!=null)                                    //~0113R~
    		AG.aWDI.WDIR.onResume();                               //~0113R~
    }                                                              //~0113I~
    //******************************************                   //~0113I~
    public static void onPause()                                   //~0113I~
    {                                                              //~0113I~
    	if (AG.aWDI==null)                                         //~0113I~
        	return;                                                //~0113I~
        if (Dump.Y) Dump.println("WDI.onPause WDIR="+Utils.toString(AG.aWDI.WDIR));//~0113I~
    	if (AG.aWDI.WDIR!=null)                                    //~0113R~
    		AG.aWDI.WDIR.onPause();                                //~0113R~
    }                                                              //~0113I~
    //******************************************                   //~0113I~
    //*from BTCDialog constructor                                  //~0113R~
    //******************************************                   //~0113I~
    public static void shownBTCD()                                 //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDI.shownBTCD AG="+Utils.toString(AG));                 //~0113I~//~vaf0R~
        if (AG==null)                                              //~vaf0I~
        	return;                                                //~vaf0I~
    	if (AG.aWDI!=null && AG.aWDI.WDIR!=null)                   //~0113I~
    		AG.aWDI.WDIR.shownBTCD();                              //~0113I~
    }                                                              //~0113I~
    //******************************************                   //~0113I~
    //*from WDA after set AG.aWDA                                  //~0113R~
    //******************************************                   //~0113I~
    public static void shownWDA()                                  //~0113R~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDI.shownWDA");                  //~0113R~
    	if (AG.aWDI.WDIR!=null)                                    //~0113R~
    		AG.aWDI.WDIR.shownWDA();                               //~0113R~
    }                                                              //~0113I~
    //******************************************                   //~0113I~
    //*from WDA after set AG.aWDA=null                             //~0113R~
    //******************************************                   //~0113I~
    public static void dismissedWDA()                              //~0113R~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDI.dismissedWDA");              //~0113R~
    	if (AG.aWDI.WDIR!=null)                                    //~0113R~
    		AG.aWDI.WDIR.dismissedWDA();                           //~0113I~
    }                                                              //~0113I~
}
