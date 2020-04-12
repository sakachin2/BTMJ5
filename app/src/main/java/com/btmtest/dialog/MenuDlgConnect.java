//*CID://+v@@@R~:                             update#=  174;       //~1Af6R~//~v@@@R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import com.btmtest.BT.Members;
import com.btmtest.MainActivity;
import com.btmtest.MainView;
import com.btmtest.R;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.wifi.WDI;

import static com.btmtest.AG.*;
import static com.btmtest.StaticVars.AG;                           //~@@@@M~//~v@@@I~
import static com.btmtest.BT.Members.*;                            //~v@@@I~
import static com.btmtest.dialog.UMenuDlg.*;

public class MenuDlgConnect                                        //~v@@@R~
		implements UMenuDlg.UMenuDlgI                              //~v@@@I~
{                                                                  //~2C29R~
    private static final int TITLEID=R.string.Connect;             //~v@@@I~
    private static final int HELP_TITLEID=TITLEID;                 //~v@@@I~
    private static final String HELPFILE="MenuDlgConnect";         //+v@@@R~
                                                                   //~v@@@I~
    private static final int MENU_ITEMS=R.array.MenuDialog_Connect;//~v@@@I~
    private static final int MENU_BLUETOOTH=0;                    //~v@@@I~
    private static final int MENU_WIFIDIRECT=1;                   //~v@@@I~
    private static final int MENU_HELP=2;                         //~v@@@I~
    private static final int MENU_CANCEL=3;                       //~v@@@I~
                                                                   //~v@@@I~
	private UMenuDlg umdlg;                                        //~v@@@I~
	private UMenuDlg.UMenuDlgI listener;                                    //~v@@@I~
	private int menuid,titleid,itemsid;                                            //~v@@@I~
//**********************************                               //~v@@@I~
    public MenuDlgConnect()                                        //~v@@@R~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static MenuDlgConnect newInstance(int Ptitleid,int Pitemsid)//~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.newInstance strarrayid="+Integer.toHexString(Pitemsid));//~v@@@R~
    	MenuDlgConnect menuDialog=new MenuDlgConnect();            //~v@@@R~
        menuDialog.titleid=Ptitleid;                               //~v@@@I~
        menuDialog.itemsid=Pitemsid;                               //~v@@@I~
		return menuDialog;
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public void show(UMenuDlg.UMenuDlgI Plistener,int Pmenuid)               //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.show menuid="+Pmenuid);//~v@@@R~
        listener=Plistener;                                        //~v@@@I~
        umdlg=UMenuDlg.show(this,Pmenuid,titleid,itemsid);         //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
//*from Main                                                       //~v@@@I~
//**********************************                               //~v@@@I~
	public static void showMenu()                                  //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.showMenu");       //~v@@@R~
        MenuDlgConnect dlg=MenuDlgConnect.newInstance(TITLEID,MENU_ITEMS);//~v@@@R~
        dlg.show(null/*listener*/,MENUID_CONNECT);                 //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.onDestroy");      //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@M~
	@Override                                                      //~v@@@I~
    public boolean menuItemSelectedMulti(int Pmenuid,boolean[] PselectedId)//~v@@@R~
    {                                                              //~v@@@I~
    //*not called if singleselectitem                              //~v@@@I~
    	return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void menuItemSelected(int Pmenuid,int Pidx,String Pitem)//~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.menuItemSelected menuid="+Pmenuid+",item="+Pitem+",listener!=null="+(listener!=null));//~v@@@R~
        if (listener!=null)                                        //~v@@@I~
        {                                                          //~v@@@I~
        	listener.menuItemSelected(Pmenuid,Pidx,Pitem);         //~v@@@I~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
    	switch(Pmenuid)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case MENUID_CONNECT:                                       //~v@@@R~
        	menuConnect(Pidx);                                     //~v@@@R~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@M~
//**********************************                               //~v@@@I~
    private void menuConnect(int Pidx)                             //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.menuConnect idx="+Pidx);//~v@@@R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            switch(Pidx)                                           //~v@@@R~
            {                                                      //~v@@@R~
            case MENU_BLUETOOTH:                                   //~v@@@R~
                connectBluetooth();                                //~v@@@R~
                break;                                             //~v@@@R~
            case MENU_WIFIDIRECT:                                  //~v@@@I~
                connectWifiDirect();                               //~v@@@I~
                break;                                             //~v@@@I~
            case MENU_HELP:                                        //~v@@@R~
                help();                                            //~v@@@I~
                break;                                             //~v@@@M~
            default:	//Cancel                                   //~v@@@I~
				umdlg.dismiss();                                   //~v@@@I~
            }                                                      //~v@@@R~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"MenuDlgConnect:menuConnect idx="+Pidx);//~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void help()                                            //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.help");           //~v@@@I~
    	HelpDialog.newInstance(HELP_TITLEID,HELPFILE).show();      //+v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void connectBluetooth()                                //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.connectBluetooth");//~v@@@I~
        if (chkMixModeErr(CM_BT/*BT*/))                            //~v@@@R~
        	return;                                                //~v@@@I~
        AG.aBTMulti.onConnect();                                   //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void connectWifiDirect()                               //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.connectWifiDirect");//~v@@@I~
        if (!chkGranted())                                         //~v@@@I~
        	return;                                                //~v@@@I~
        if (chkMixModeErr(CM_WD/*WD*/))                            //~v@@@R~
        	return;                                                //~v@@@I~
//      new IPConnectionWD();                                      //~v@@@R~
        WDI.startRemoteIP();                                       //~v@@@I~
    }                                                              //~v@@@I~
//***************************************************************  //~v@@@I~
//*from MainActivity onRequestPermissionResult                     //~v@@@I~
//***************************************************************  //~v@@@I~
    private static void connectWifiDirectGranted()                 //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.connectWifiDirectGranted");//~v@@@I~
        if (chkMixModeErr(CM_WD/*WD*/))                            //~v@@@I~
        	return;                                                //~v@@@I~
        WDI.startRemoteIP();                                       //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private static boolean chkMixModeErr(int Pmode)                //~v@@@R~
    {                                                              //~v@@@I~
    	boolean rc=false;                                          //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDlgConnect.chkMixMode mode="+Pmode);//~v@@@I~
        Members m=AG.aBTMulti.BTGroup;                              //~v@@@I~
        if (m!=null)                                               //~v@@@I~
        {                                                          //~v@@@I~
        	int[] ctrs=new int[4];                                 //~v@@@I~
        	m.getConnectionModeCtr(ctrs);                          //~v@@@I~
            if (ctrs[CM_WD]!=0)                                    //~v@@@I~
		    	AG.activeSessionType=AST_WD;                           //~1A8gI~//~9719I~//~v@@@I~
            else                                                   //~v@@@I~
            if (ctrs[CM_BT]!=0)                                    //~v@@@I~
		    	AG.activeSessionType=AST_BT;                       //~v@@@I~
            else                                                   //~v@@@I~
		    	AG.activeSessionType=AST_NONE;                     //~v@@@I~
            switch(Pmode)                                          //~v@@@I~
            {                                                      //~v@@@I~
            case CM_BT:	//bt                                       //~v@@@R~
//          	if (ctrs[CM_WD]!=0)                                //~v@@@R~
		    	if (AG.activeSessionType==AST_WD)                  //~v@@@I~
                {                                                  //~v@@@I~
		        	UView.showToast(R.string.ErrMixedModeRemainWD); //~v@@@I~
                	rc=true;                                       //~v@@@I~
                }                                                  //~v@@@I~
		    	AG.activeSessionType=AST_BT;                      //~v@@@I~
            	break;                                             //~v@@@I~
            case CM_WD:	//wifidirect                               //~v@@@R~
//          	if (ctrs[CM_BT]!=0)                                //~v@@@R~
		    	if (AG.activeSessionType==AST_BT)                  //~v@@@I~
                {                                                  //~v@@@I~
		        	UView.showToast(R.string.ErrMixedModeRemainBT); //~v@@@I~
                	rc=true;                                       //~v@@@I~
                }                                                  //~v@@@I~
		    	AG.activeSessionType=AST_WD;                      //~v@@@I~
            	break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        return rc;
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private boolean chkGranted()                                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("MenuDialogConnect.chkGranted");  //~v@@@I~
//      boolean showToast=false;                                   //~v@@@R~
		boolean rc=UView.isPermissionGrantedLocation();                    //~v@@@I~
        if (!rc)                                                   //~v@@@I~
        {                                                           //~v@@@I~
//      	if (UView.isPermissionDeniedLocation())	//user replayed No//~v@@@R~
//          	showToast=true;                                    //~v@@@R~
		    UView.requestPermissionLocation(MainActivity.PERMISSION_LOCATION);//~v@@@I~
//          if (showToast)                                         //~v@@@R~
//  			UView.showToastLong(R.string.WifiDirectRequiresGranted);//~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("MenuDialogConnect rc="+rc);      //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    public static void grantedWifi(boolean PswGranted)             //~v@@@I~
    {                                                              //~v@@@I~
    	boolean rc;                                                //~v@@@I~
        if (Dump.Y) Dump.println("MenuDialogConnect PswGranted="+PswGranted);//~v@@@I~
        if (!PswGranted)                                           //~v@@@I~
        {                                                          //~v@@@I~
//        	MainView.drawMsg(R.string.WifiDirectNotGranted);       //~v@@@R~
// 			UView.showToastLong(R.string.WifiDirectRequiresGranted);//~v@@@R~
          	MainView.drawMsg(R.string.WifiDirectRequiresGranted);  //~v@@@I~
            return;                                                //~v@@@I~
        }	                                                       //~v@@@I~
		UView.showToast(R.string.WifiDirectGranted);               //~v@@@I~
    	connectWifiDirectGranted();                                //~v@@@I~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
