//*CID://+v@@@R~:                             update#=  162;       //~1Af6R~//~v@@@R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import com.btmtest.R;
//import com.btmtest.game.OperationSetting;
import com.btmtest.dialog.PrefSetting;                             //~v@@@I~
import com.btmtest.utils.Dump;                                     //~v@@@R~

import static com.btmtest.dialog.UMenuDlg.*;
import static com.btmtest.StaticVars.AG;                           //+v@@@I~

public class MenuDialog                                            //~v@@@R~
		implements UMenuDlg.UMenuDlgI                              //~v@@@I~
{                                                                  //~2C29R~
                                                                   //~v@@@I~
	private UMenuDlg umdlg;                                        //~v@@@I~
	private UMenuDlg.UMenuDlgI listener;                                    //~v@@@I~
	private int menuid,titleid,itemsid;                                            //~v@@@I~
//**********************************                               //~v@@@I~
    public MenuDialog()                                            //~v@@@R~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static MenuDialog newInstance(int Ptitleid,int Pitemsid)//~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDialog.newInstance strarrayid="+Integer.toHexString(Pitemsid));//~v@@@I~
    	MenuDialog menuDialog=new MenuDialog();                               //~v@@@I~
        menuDialog.titleid=Ptitleid;                               //~v@@@I~
        menuDialog.itemsid=Pitemsid;                               //~v@@@I~
		return menuDialog;
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public void show(UMenuDlg.UMenuDlgI Plistener,int Pmenuid)               //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDialog.show menuid="+Pmenuid);//~v@@@I~
        listener=Plistener;                                        //~v@@@I~
        umdlg=UMenuDlg.show(this,Pmenuid,titleid,itemsid);         //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
//*from Main                                                       //~v@@@I~
//**********************************                               //~v@@@I~
	public static void showSettingMenu()                           //~v@@@I~
    {                                                              //~v@@@I~
        MenuDialog dlg;                                            //+v@@@I~
    	if (Dump.Y) Dump.println("MenuDialog.showSettingMenu");    //~v@@@I~
        if (AG.isDebuggable)                                       //~v@@@I~
        	dlg=MenuDialog.newInstance(R.string.Settings,R.array.MenuDialog_Setting_Debug);//+v@@@R~
        else                                                       //~v@@@I~
        	dlg=MenuDialog.newInstance(R.string.Settings,R.array.MenuDialog_Setting);//+v@@@R~
        dlg.show(null/*listener*/,MENUID_SETTING);                 //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDialog.onDestroy");          //~v@@@I~
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
    	if (Dump.Y) Dump.println("MenuDialog.menuItemSelected menuid="+Pmenuid+",item="+Pitem+",listener!=null="+(listener!=null));//~v@@@I~
        if (listener!=null)                                        //~v@@@I~
        {                                                          //~v@@@I~
        	listener.menuItemSelected(Pmenuid,Pidx,Pitem);         //~v@@@I~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
    	switch(Pmenuid)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case MENUID_SETTING:                                       //~v@@@I~
        	menuSetting(Pidx);                                     //~v@@@I~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@M~
//**********************************                               //~v@@@I~
    private void menuSetting(int Pidx)                            //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDialog.menuSetting idx="+Pidx);//~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            switch(Pidx)                                           //~v@@@R~
            {                                                      //~v@@@R~
            case 0: //rule setting                                 //~v@@@R~
                settingRule();                                     //~v@@@R~
                break;                                             //~v@@@R~
            case 1: //preference                                   //~v@@@R~
                settingPreference();                               //~v@@@I~
                break;                                             //~v@@@I~
            case 2: //operation setting                            //~v@@@I~
                settingOperation();                                //~v@@@M~
                break;                                             //~v@@@M~
            default:	//Cancel                                   //~v@@@I~
				umdlg.dismiss();                                   //~v@@@I~
            }                                                      //~v@@@R~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"MenuDialog:menuSetting idx="+Pidx);    //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void settingOperation()                               //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDialog.settingOperation");   //~v@@@I~
//  	OperationSetting.newInstance().show();                     //~v@@@R~
    	TODlg.newInstance().show(); //TODO                         //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void settingRule()                                    //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDialog.settingRule");        //~v@@@I~
    	RuleSetting.newInstance().show();                          //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void settingPreference()                              //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuDialog.settingPreference");  //~v@@@I~
    	PrefSetting.newInstance().show();                          //~v@@@I~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
