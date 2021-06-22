//*CID://+va9eR~:                             update#=  216;       //+va9eR~
//*****************************************************************//~v101I~
//2021/06/17 va9e del va9c because reach call is expanded to other player. alternatively add force-reach to menu item//+va9eI~
//2020/11/21 va48 (Bug)gameover rejected on client after suspendgame//~va48I~
//2020/05/08 va07:close menu in game when preference selected      //~va07I~
//2020/04/13 va02:At Server,BackButton dose not work when client app canceled by androiud-Menu button//~va02R~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import android.widget.Button;

import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.UA.UAReach;
import com.btmtest.game.UA.UARon;
import com.btmtest.gui.UButton;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.dialog.UMenuDlg.*;
import static com.btmtest.game.Status.*;

public class MenuInGameDlg                                         //~v@@@R~
		implements UMenuDlg.UMenuDlgI                              //~v@@@I~
        , UButton.UButtonI                                          //~9807I~
{                                                                  //~2C29R~
    private static final int TITLEID=R.string.Title_MenuInGame;//~v@@@I~
    private static final int ITEMSID=R.array.Items_MenuInGame;     //~v@@@I~
    private static final int HELP_TITLEID=TITLEID;                 //~v@@@I~
    private static final String HELPFILE_MENU="MenuInGameDlg";//~v@@@R~//~9C13R~
    private static final String HELPFILE="GameBoard";           //~v@@@I~//~9C13R~
                                                                   //~v@@@I~
	public static final int ITEMID_BASE                 =0;        //~v@@@R~
//  public static final int ITEMID_COMP                 =ITEMID_BASE+0;//~v@@@I~//~0206R~
	public static final int ITEMID_COMP_RESULT          =ITEMID_BASE+0;//~v@@@R~//~0206R~
	public static final int ITEMID_DRAWN_GAME           =ITEMID_BASE+1;//~v@@@R~//~0206R~
	public static final int ITEMID_FINAL_GAME           =ITEMID_BASE+2;//~v@@@R~//~0206R~
	public static final int ITEMID_MINUS_STOP           =ITEMID_BASE+3;//~v@@@R~//~0206R~
	public static final int ITEMID_END_SCORE            =ITEMID_BASE+4;//~v@@@R~//~0206R~
//  public static final int ITEMID_SUSPEND              =ITEMID_BASE+5;//~9817I~//~0206R~//~0304R~
//  public static final int ITEMID_SUSPEND_RESET        =ITEMID_BASE+6;//~9817I~//~0206R~//~0304R~
	public static final int ITEMID_SUSPEND_DLG          =ITEMID_BASE+5;//~9823I~//~0206R~//~0304R~
	public static final int ITEMID_RETURN               =ITEMID_BASE+6;//~9903I~//~9A29R~//~9C04R~//~0206R~//~0304R~
	public static final int ITEMID_IOERR                =ITEMID_BASE+7 ;//~9A18I~//~9A29R~//~9C04R~//~0206R~//~0304R~
    public static final int ITEMID_WIN_ANYWAY           =ITEMID_BASE+8 ;//~9C04R~//~0205R~//~0206R~//~0304R~
    public static final int ITEMID_REACH_ANYWAY         =ITEMID_BASE+9 ;//+va9eI~
    public static final int ITEMID_PREF_SETTING         =ITEMID_BASE+10;//~0205I~//~0206R~//~0304R~//+va9eR~
	public static final int ITEMID_MENU_HELP            =ITEMID_BASE+11;//~v@@@I~//~9817R~//~9823R~//~9903R~//~9A18R~//~9A29R~//~9C04I~//~0205R~//~0206R~//~0304R~//+va9eR~
	public static final int ITEMID_HELP                 =ITEMID_BASE+12;//~v@@@R~//~9817R~//~9823R~//~9903R~//~9A18R~//~9A29R~//~9C04R~//~0205R~//~0206R~//~0304R~//+va9eR~
	public static final int ITEMID_CLOSE                =ITEMID_BASE+13;//~v@@@R~//~9817R~//~9823R~//~9903R~//~9A18R~//~9A29R~//~9C04R~//~0205R~//~0206R~//~0304R~//+va9eR~
                                                                   //~v@@@I~
	private UMenuDlg umdlg;                                        //~v@@@I~
	private UMenuDlg.UMenuDlgI listener;                                    //~v@@@I~
	private int menuid,titleid,itemsid;                                            //~v@@@I~
	private boolean swDismiss;                                     //~9903I~
//**********************************                               //~v@@@I~
    public MenuInGameDlg()                                         //~v@@@R~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public static MenuInGameDlg newInstance(int Ptitleid,int Pitemsid)//~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.newInstance strarrayid="+Integer.toHexString(Pitemsid));//~v@@@R~
    	MenuInGameDlg menuDialog=new MenuInGameDlg();              //~v@@@R~
        menuDialog.titleid=Ptitleid;                               //~v@@@I~
        menuDialog.itemsid=Pitemsid;                               //~v@@@I~
		return menuDialog;
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public void show(UMenuDlg.UMenuDlgI Plistener,int Pmenuid)               //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.show menuid="+Pmenuid);//~v@@@R~
        listener=Plistener;                                        //~v@@@I~
//      umdlg=UMenuDlg.show(this,Pmenuid,titleid,itemsid);         //~v@@@R~//~9807R~
        umdlg=UMenuDlg.show(this,Pmenuid,titleid,itemsid,R.layout.menuingame,true/*swNoAutoDismiss*/);//~9807I~//~9903R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
//*from Main/GC                                                    //~v@@@R~
//**********************************                               //~v@@@I~
	public static void showSettingMenu()                           //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.showSettingMenu"); //~v@@@R~
        MenuInGameDlg dlg=MenuInGameDlg.newInstance(TITLEID,ITEMSID);//~v@@@R~
        dlg.show(null/*listener*/,MENUID_INGAME);                  //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.onDestroy");       //~v@@@R~
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
    	if (Dump.Y) Dump.println("MenuInGameDlg.menuItemSelected menuid="+Pmenuid+",item="+Pitem+",listener!=null="+(listener!=null));//~v@@@R~
        if (listener!=null)                                        //~v@@@I~
        {                                                          //~v@@@I~
        	listener.menuItemSelected(Pmenuid,Pidx,Pitem);         //~v@@@I~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
    	switch(Pmenuid)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case MENUID_INGAME:                                        //~v@@@R~
        	doMenuItem(Pidx);                                      //~v@@@R~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@M~
//**********************************                               //~0206I~
	private boolean isGaming()                                    //~0206I~//~0209R~
    {                                                              //~0206I~
    	boolean rc;
//      if (!Status.isGamingForMenuInGame())                       //~0206I~//~va02R~
        if (!Status.isGamingForMenuInGameAndInterRound())          //~va02I~
        {                                                          //~0206I~
        	UView.showToast(R.string.Err_GameStatusForMenuInGame); //~0206I~
            rc=false;                                              //~0206I~
        }                                                          //~0206I~
        else                                                       //~0206I~
        	rc=true;                                               //~0206I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.isGaming rc="+rc); //~0206I~
        return rc;                                                 //~0206I~
    }                                                              //~0206I~
//**********************************                               //~va02I~
	private boolean isGaming(int Pmsgid)                           //~va02I~
    {                                                              //~va02I~
    	boolean rc;                                                //~va02I~
    	if (isGameOver())                                          //~va02I~
        {                                                          //~va02I~
        	UView.showToast(R.string.Info_GameEnded);              //~va02I~
            rc=false;                                              //~va02I~
        }                                                          //~va02I~
        else                                                       //~va02I~
//      if (!Status.isGamingForMenuInGame())                       //~va02R~
        if (!Status.isGamingForMenuInGameAndInterRound())          //~va02I~
        {                                                          //~va02I~
        	UView.showToast(Pmsgid);                               //~va02I~
            rc=false;                                              //~va02I~
        }                                                          //~va02I~
        else                                                       //~va02I~
        	rc=true;                                               //~va02I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.isGaming rc="+rc); //~va02I~
        return rc;                                                 //~va02I~
    }                                                              //~va02I~
//**********************************                               //~v@@@I~
    private void doMenuItem(int Pidx)                              //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doMenuItem idx="+Pidx);//~v@@@R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
	        swDismiss=true;                                        //~9903I~
            switch(Pidx)                                           //~v@@@R~
            {                                                      //~v@@@R~
//  		case ITEMID_COMP:                                      //~v@@@I~//~0206R~
//          	doComp();                                          //~v@@@I~//~0206R~
//              break;                                             //~v@@@I~//~0206R~
			case ITEMID_COMP_RESULT:                               //~v@@@I~
            	if (!isGaming())                                   //~0206I~
                	break;                                         //~0206I~
            	doCompResult();                                    //~v@@@I~
                break;                                             //~v@@@R~
			case ITEMID_DRAWN_GAME:                                //~v@@@I~
            	if (!isGaming())                                   //~0206I~
                {
	        		swDismiss=false;
                	break;                                         //~0206I~
                }
            	doDrawnGame();                                     //~v@@@I~
                break;                                             //~v@@@I~
			case ITEMID_FINAL_GAME:                                //~v@@@I~
            	if (!isGaming())                                   //~0206I~
                	break;                                         //~0206I~
            	doFinalGame();                                     //~v@@@I~
                break;                                             //~v@@@I~
			case ITEMID_MINUS_STOP:                                //~v@@@I~
            	if (!isGaming())                                   //~0206I~
                	break;                                         //~0206I~
            	doMinusStop();                                     //~v@@@I~
                break;                                             //~v@@@I~
			case ITEMID_END_SCORE:                                 //~v@@@I~
            	if (!isGaming())                                   //~0206I~
                	break;                                         //~0206I~
            	doEndScore();                                      //~v@@@I~
                break;                                             //~v@@@R~
//            case ITEMID_SUSPEND:                                   //~9817I~//~0304R~
//                if (!isGaming())                                   //~0206I~//~0304R~
//                    break;                                         //~0206I~//~0304R~
//                doSuspend();                                       //~9817I~//~0304R~
//                break;                                             //~9817I~//~0304R~
//            case ITEMID_SUSPEND_RESET:                             //~9817I~//~0304R~
//                if (!isGaming())                                   //~0206I~//~0304R~
//                    break;                                         //~0206I~//~0304R~
//                doSuspendReset();                                  //~9817I~//~0304R~
//                break;                                             //~9817I~//~0304R~
			case ITEMID_SUSPEND_DLG:                               //~9823I~
            	if (!isGaming())                                   //~0206I~
                	break;                                         //~0206I~
            	doSuspendDlg();                                    //~9823I~
                break;                                             //~9823I~
    		case ITEMID_PREF_SETTING:                              //~v@@@I~//~9A29R~//~9C04R~
            	doPrefSetting();                                   //~v@@@I~//~9A29R~//~9C04R~
	        	swDismiss=true;                                   //~0206I~//~va07R~
                break;                                             //~v@@@I~//~9A29R~//~9C04R~
			case ITEMID_RETURN:                                    //~9903I~
            	doReturn();                                        //~9903I~
                break;                                             //~9903I~
			case ITEMID_IOERR:                                     //~9A18I~
//          	if (!isGaming())                                   //~0206I~//~va02R~
            	if (!isGaming(R.string.Err_GameStatusForMenuInGameUseBackButton))//~va02I~
                	break;                                         //~0206I~
//           	if (!AG.aGC.isConnectionLost())                    //~va02R~
//              	break;                                         //~va02R~
            	doIOErr();                                         //~9A18I~
                break;                                             //~9A18I~
			case ITEMID_WIN_ANYWAY:                                //~0205I~
            	if (!isGaming())                                   //~0206I~
                	break;                                         //~0206I~
            	doWinAnyway();                                     //~0205I~
                break;                                             //~0205I~
			case ITEMID_REACH_ANYWAY:                              //+va9eI~
            	if (!isGaming())                                   //+va9eI~
                	break;                                         //+va9eI~
            	doReachAnyway();                                   //+va9eI~
                break;                                             //+va9eI~
			case ITEMID_MENU_HELP:                                 //~v@@@R~
            	doMenuHelp();                                      //~v@@@R~
                break;                                             //~v@@@I~
			case ITEMID_HELP:                                      //~v@@@I~
            	doHelp();                                          //~v@@@I~
                break;                                             //~v@@@I~
			case ITEMID_CLOSE:                                     //~v@@@I~
            	umdlg.dismiss();                                   //~v@@@R~
                break;                                             //~v@@@I~
            default:                                               //~v@@@I~
	            if (Dump.Y) Dump.println("MenuInGameDlg.domenuItem unknown idx="+Pidx);//~v@@@I~
            }                                                      //~v@@@R~
	        if (Dump.Y) Dump.println("MenuInGameDlg.domenuItem swDismiss="+swDismiss);//~9903I~
            if (swDismiss)                                         //~9903I~
            	umdlg.dismiss();                                         //~9903I~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"MenuInGameDlg idx="+Pidx);             //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
//    private void doComp()                                          //~v@@@I~//~0206R~
//    {                                                              //~v@@@I~//~0206R~
//        if (Dump.Y) Dump.println("MenuInGameDlg.doComp");          //~v@@@I~//~0206R~
//        swDismiss=CompReqDlg.showDismissed();                                //~v@@@I~//~9903R~//~0206R~
//    }                                                              //~v@@@I~//~0206R~
//**********************************                               //~v@@@I~
    private void doCompResult()                                    //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doCompResult");    //~v@@@R~
    	swDismiss=CompleteDlg.showDismissed();                               //~v@@@I~//~9904R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void doDrawnGame()                                     //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("MenuInGameDlg.doDrawnGame");     //~v@@@I~
//  	UAEndGame.showDlg();                                       //~v@@@R~
        swDismiss=UAEndGame.showDlgFromMenu();                     //~9904R~
    }//~v@@@I~
//**********************************                               //~v@@@I~
    private void doFinalGame()                                     //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doFinalGame");     //~v@@@I~
    	swDismiss=FinalGameDlg.showDismissed();                              //~v@@@R~//~9904R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void doMinusStop()                                     //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doMinusStop");     //~v@@@I~
    	swDismiss=ScoreDlg.showDismissed();                                  //~v@@@I~//~9904R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void doEndScore()                                      //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doEndScore");      //~v@@@I~
    	swDismiss=AccountsDlg.showDismissed();                               //~v@@@I~//~9904R~
    }                                                              //~v@@@I~
////**********************************                               //~9817I~//~0307R~
//    private void doSuspend()                                       //~9817I~//~0307R~
//    {                                                              //~9817I~//~0307R~
//        if (Dump.Y) Dump.println("MenuInGameDlg.doSuspend");       //~9817I~//~0307R~
//        AG.aGC.suspendGame();                                      //~9817I~//~9904R~//~0307R~
//    }                                                              //~9817I~//~0307R~
////**********************************                               //~9817I~//~0307R~
//    private void doSuspendReset()                                  //~9817I~//~0307R~
//    {                                                              //~9817I~//~0307R~
//        if (Dump.Y) Dump.println("MenuInGameDlg.doSuspendReset");  //~9817I~//~0307R~
//        AG.aGC.suspendGameReset();                                 //~9817I~//~0307R~
//    }                                                              //~9817I~//~0307R~
//**********************************                               //~9823I~
    private void doSuspendDlg()                                    //~9823I~
    {                                                              //~9823I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doSuspendDlg");    //~9823I~
    	swDismiss=SuspendDlg.showDismissed();                                //~9823I~//~9904R~
    }                                                              //~9823I~
//**********************************                               //~v@@@I~//~9A29R~//~9C04R~
    private void doPrefSetting()                                   //~v@@@I~//~9A29R~//~9C04R~
    {                                                              //~v@@@I~//~9A29R~//~9C04R~
        if (Dump.Y) Dump.println("MenuInGameDlg.doPrefSetting");   //~v@@@I~//~9A29R~//~9C04R~
        PrefSetting.newInstance(true/*PswFixedParm*/).show();      //~v@@@I~//~9A29R~//~9C04R~
    }                                                              //~v@@@I~//~9A29R~//~9C04R~
//**********************************                               //~0205I~
    private void doWinAnyway()                                     //~0205I~
    {                                                              //~0205I~
        if (Dump.Y) Dump.println("MenuInGameDlg.doWinAnyway");     //~0205I~
        swDismiss= UARon.winAnyway();                               //~0205I~
    }                                                              //~0205I~
//**********************************                               //+va9eI~
    private void doReachAnyway()                                   //+va9eI~
    {                                                              //+va9eI~
        if (Dump.Y) Dump.println("MenuInGameDlg.doReachAnyway");   //+va9eI~
        swDismiss= UAReach.reachAnyway();                          //+va9eI~
    }                                                              //+va9eI~
//**********************************                               //~9903I~
    private void doReturn()                                        //~9903I~
    {                                                              //~9903I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doReturn");        //~9903I~
//        if (!Accounts.isServer())                                  //~9B20I~//~va02R~
//        {                                                          //~9B20I~//~va02R~
////          if (Status.getStatusRestart()==RESTART_ONCE_IOERR)     //~9B25I~//~0411R~//~va02R~
//            if (Status.getStatusRestart()!=RESTART_NONE)         //~va02R~
//            {                                                    //~va02R~
//                UView.showToast(R.string.Err_SuspendNoIOErrExitOnGameView); //do connection failure//~va02R~
//                swDismiss=false;                                 //~va02R~
//                return;                                          //~va02R~
//            }                                                    //~va02R~
//            if (AG.aBTMulti.BTGroup.getConnectedCtr()==0)          //~0411R~//~va02R~
//            {                                                      //~9B25I~//~va02R~
//                AG.aGC.confirmEndGameReturn();                         //~9B25I~//~0411R~//~va02R~
//                swDismiss=true;                                    //~9B25I~//~va02R~
//                return;                                            //~9B25I~//~va02R~
//            }                                                      //~9B25I~//~va02R~
//            UView.showToast(R.string.Err_TryEndgameFromServer);    //~9B20I~//~va02R~
//            swDismiss=false;                                       //~9B20I~//~va02R~
//            return;                                                //~9B20I~//~va02R~
//        }                                                          //~9B20I~//~va02R~
//    //*Server                                                    //~va02R~
//        if (Status.getStatusRestart()!=RESTART_NONE)             //~va02R~
//        {                                                        //~va02R~
//            UView.showToast(R.string.Err_SuspendNoIOErrExitOnGameView);  //do connection failure//~va02R~
//            swDismiss=false;                                     //~va02R~
//            return;                                              //~va02R~
//        }                                                        //~va02R~
//        if (!isGaming(R.string.Err_GameStatusForMenuInGameUseBackButton))//~va02R~
//        {                                                        //~va02R~
//            swDismiss=false;                                     //~va02R~
//            return;                                              //~va02R~
//        }                                                        //~va02R~
//        if (AG.aGC.isConnectionLost())                           //~va02R~
//        {                                                        //~va02R~
//            AG.aGC.confirmEndGameReturn();                       //~va02R~
//            swDismiss=true;                                      //~va02R~
//            return;                                              //~va02R~
//        }                                                        //~va02R~
		if (Status.isGameOver() || Status.isGameSuspended())       //~va02R~
			if (!Accounts.isServer())                              //~va02I~
            {                                                      //~va02I~
			  if (AG.aBTMulti.BTGroup.getConnectedCtr()!=0)        //~va48I~
              {                                                    //~va48I~
                UView.showToast(R.string.Err_TryEndgameFromServer);//~va02I~
                swDismiss=false;                                   //~va02I~
                return;                                            //~va02I~
              }                                                    //~va48I~
            }                                                      //~va02I~
    	swDismiss=AG.aGC.endGameReturn();                          //~9903R~
    }                                                              //~9903I~
//**********************************                               //~9A18I~
    private void doIOErr()                                         //~9A18I~
    {                                                              //~9A18I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doIOErr");         //~9A18I~
    	swDismiss=SuspendIOErrReqDlg.newInstance();                             //~9A18I~//~9A19R~
    }                                                              //~9A18I~
//**********************************                               //~v@@@I~
    private void doMenuHelp()                                      //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doHelp");          //~v@@@I~
    	HelpDialog.newInstance(HELP_TITLEID,HELPFILE_MENU).show();//~v@@@R~//~9C13R~
        swDismiss=false;                                           //~9903I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void doHelp()                                          //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("MenuInGameDlg.doHelp");          //~v@@@I~
    	HelpDialog.newInstance(HELP_TITLEID,HELPFILE).show(); //~v@@@I~//~9C13R~
    }                                                              //~v@@@I~
    //******************************************                   //~9807I~
    @Override //UButtonI                                           //~9807I~
    public void onClickButton(Button Pbutton)                      //~9807I~
	{                                                              //~9807I~
    	boolean rc=true;                                           //~9807I~
        if (Dump.Y) Dump.println("MenuInGameDlg:onClickButton"+Pbutton.getText());//~9807I~
    	try                                                        //~9807I~
        {                                                          //~9807I~
        	int id=Pbutton.getId();                                //~9807I~
        	switch(id)                                             //~9807I~
            {                                                      //~9807I~
            case R.id.Cancel:                                      //~9807I~
            	umdlg.dismiss();                                   //~9807I~
                break;                                             //~9807I~
            case R.id.Help:                                        //~9807I~
                doHelp();                                          //~9807I~
                break;                                             //~9807I~
            default:                                               //~9807I~
//                onClickOther(id);                                //~9807I~
            }                                                      //~9807I~
        }                                                          //~9807I~
        catch(Exception e)                                         //~9807I~
        {                                                          //~9807I~
            Dump.println(e,"MenuInGameDlg:"+Pbutton.getText());    //~9807I~
        }                                                          //~9807I~
    }                                                              //~9807I~
}//class                                                           //~v@@@R~
