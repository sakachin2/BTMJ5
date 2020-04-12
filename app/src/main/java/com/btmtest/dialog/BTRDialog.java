//*CID://+DATER~:                             update#=  491;       //~v001R~//~@002R~//~9210R~
//*****************************************************************//~v101I~
//*BlietoothRecobectDialog                                       //~v@@@I~//~9A21R~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~

import android.view.View;
import android.widget.Button;                                      //~v@@@I~
import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.game.Status;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.GMsg;
import com.btmtest.gui.UButton;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.BT.Members;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@002I~
import static com.btmtest.StaticVars.connectionStatus;             //~@002I~

public class BTRDialog extends BTCDialog                           //~9A21R~
{                                                                  //~2C29R~
	private static final int BTNID_CONTINUE_GAME=R.id.BTGame;      //~9A21I~
	private static final int BTNID_SUSPEND_GAME=R.id.BTSuspendGame;//~9A30I~
                                                                   //~v@@@I~
                                                                   //~v@@@I~
	private Button btnGame,btnSuspendGame;                                    //~v@@@R~//~9A30R~
                                                                   //~v@@@I~
    public  boolean swServer;                                      //~9A24R~
                                                                   //~1A6fI~
    //******************************************                   //~v@@@M~
	public BTRDialog()                                             //~v@@@M~//~9A21R~
	{                                                              //~3105R~//~v@@@M~
        if (Dump.Y) Dump.println("BTRDialog.constructor connectionStatus="+connectionStatus+",connectionType="+connectionType);//~@002I~//~9A21R~
//        AG.aBTRDialog=this; //used when PartnerThread detected err//~9A21I~
	}                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
    public static BTRDialog newInstance(int PmemberRole)           //~v@@@R~//~9A21R~
    {                                                              //~v@@@I~
        if (isShowing())                                           //~9709I~
        {	                                                       //~9709I~
        	if (Dump.Y) Dump.println("BTRDialog:newInstance Dup"); //~9709I~//~9A21R~
        	return null;                                           //~9709I~
        }                                                          //~9709I~
		BTRDialog dlg=new BTRDialog();                             //~v@@@I~//~9A21R~
    	UFDlg.setBundle(dlg,R.string.Title_BluetoothReconnect,R.layout.btrdialog,//~9227R~//~9A21R~
				UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN,            //~v@@@I~
//  			R.string.Title_BluetoothReconnect,"BTRDialog");             //~v@@@I~//~9A21R~//~9C13R~
    			R.string.Title_BluetoothReconnect,HELPFILE);       //~9C13I~
        dlg.connectionType=PmemberRole;                            //~9620I~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~9A23I~
    @Override                                                      //~9A23I~
    protected void getComponent(View PView)                        //~9A23I~
    {                                                              //~9A23I~
		if (Dump.Y) Dump.println("BTRDialog:getComponent connectionType="+connectionType);        //~9A23I~//~9A24R~
		swServer=AG.aAccounts.isServer();                          //~9A24I~
	    super.getComponent(PView);                                 //~9A23I~//~9A30M~
		btnGame= UButton.bind(PView,BTNID_CONTINUE_GAME,this);              //~9A23I~//~9A30R~
		btnSuspendGame= UButton.bind(PView,BTNID_SUSPEND_GAME,this);   //~9A30I~
        if (swServer)                                             //~9A24I~//~9A30M~
//      	btnConnect.setEnabled(false);                          //~9A30R~
        	btnConnect.setVisibility(View.GONE);                   //~9A30I~
        else                                                       //~9A30M~
        {                                                          //~9A30I~
        	btnAccept.setEnabled(false);                           //~9A30M~
    		btnGame.setVisibility(View.GONE);                      //~9A30I~//~0108R~
//  		btnGame.setEnabled(false);                             //~0108R~
        }                                                          //~9A30I~
    }                                                              //~9A23I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~9A21I~
    protected void init()                                            //~v@@@R~//~9A21R~
    {                                                              //~v@@@I~
        super.init();                                              //~9A21I~
        AG.aBTRDialog=this;                                        //~9A21I~
//      AG.aBTCDialog=null;                                        //~9A21I~//~9A23R~
	}                                                              //~v@@@I~
    //******************************************                   //~9817I~
    @Override                                                      //~9A21I~
    protected void resetMembersMode(Members Pmembers)                //~9817I~//~9A21R~
    {                                                              //~9817I~
        if (Dump.Y) Dump.println("BTRDialog:resetMembersMode NOP");    //~9817I~//~9A21R~
//      Pmembers.resetMode(CM_BT);                                 //~9817I~//~9A21R~
    }                                                              //~9817I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onDestroy() {                                      //~v@@@I~
        if (Dump.Y) Dump.println("BTRDialog:onDestroy");//~v@@@I~  //~9A21R~
        super.onDestroy();                                         //~v@@@I~
        AG.aBTRDialog=null;                                        //~v@@@I~//~9A21R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onClickOther(int Pbuttonid)                       //~v@@@R~
	{                                                              //~v@@@I~
	    GMsg.clearMsgbar();	//MainView and GameView                //~0113I~
//        waitingDialog=0;                                           //~v@@@I~//~9A21R~
//        if (Dump.Y) Dump.println("BTRDialog:onClickButton id="+Pbuttonid);//~v@@@R~//~9A21R~
//        switch(Pbuttonid)                                    //~v@@@R~//~9A21R~
//        {                                                          //~v@@@R~//~9A21R~
//            case R.id.BTSettings:                                  //~v@@@R~//~9817R~//~9A21R~
//                onClickSettings();                                 //~v@@@R~//~9817R~//~9A21R~
//                break;                                             //~v@@@R~//~9817R~//~9A21R~
//            case R.id.BTAccept:                                    //~v@@@R~//~9A21R~
//                onClickAccept();                                   //~v@@@R~//~9A21R~
//                break;                                             //~v@@@R~//~9A21R~
//            case R.id.BTStopAccept:                                //~v@@@R~//~9A21R~
//                onClickStopAccept();                               //~v@@@R~//~9A21R~
//                break;                                             //~v@@@R~//~9A21R~
//            case R.id.BTConnect:                                   //~v@@@R~//~9A21R~
//                onClickConnect();                                  //~v@@@R~//~9A21R~
//                break;                                             //~v@@@R~//~9A21R~
//            case R.id.BTDisconnect:                                //~v@@@R~//~9A21R~
//                onClickDisconnect();                               //~v@@@R~//~9A21R~
//                break;                                             //~v@@@R~//~9A21R~
//            case R.id.BTUnpair:                                    //~9210I~//~9A21R~
//                onClickUnpair();                                   //~9210I~//~9A21R~
//                break;                                             //~9210I~//~9A21R~
//            case R.id.Discoverable:                                //~v@@@R~//~9A21R~
//                onClickDiscoverable();                             //~v@@@R~//~9A21R~
//                break;                                             //~v@@@R~//~9A21R~
//            case R.id.Discover:                                    //~v@@@R~//~9A21R~
//                onClickDiscover();                                 //~v@@@R~//~9A21R~
//                break;                                             //~v@@@R~//~9A21R~
//            case R.id.Delete:                                      //~v@@@R~//~9A21R~
//                onClickDelete();                                   //~v@@@R~//~9A21R~
//                break;                                             //~v@@@R~//~9A21R~
////                case R.id.Close:                                 //~v@@@R~//~9A21R~
////                    onClickClose();                              //~v@@@R~//~9A21R~
////                    break;                                       //~v@@@R~//~9A21R~
////            case R.id.BTGame:                                      //~v@@@R~//~@002R~//~9A21R~
////                onClickGame();                                     //~v@@@R~//~@002R~//~9A21R~
////                break;                                             //~v@@@R~//~@002R~//~9A21R~
////                case R.id.Help:                                  //~v@@@R~//~9A21R~
////                    onClickHelp();                               //~v@@@R~//~9A21R~
////                    break;                                       //~v@@@R~//~9A21R~
//            default:                                               //~v@@@R~//~9A21R~
//                if (Dump.Y) Dump.println("BTRDialog:onClick unknown");//~v@@@R~//~9A21R~
//        }                                                          //~v@@@R~//~9A21R~
        if (Dump.Y) Dump.println("BTRDialog:onClickButton id="+Pbuttonid);//~9A21I~
//  	if (Pbuttonid!=BTNID_CONTINUE_GAME)                        //~9A21I~//~9A30R~
    	if (Pbuttonid==BTNID_CONTINUE_GAME)                        //~9A30I~
			onClickGame();                                         //~9A30I~
        else                                                       //~9A30I~
        if (Pbuttonid==BTNID_SUSPEND_GAME)                         //~9A30I~
			onClickSuspendGame();                                  //~9A30I~
        else                                                       //~9A30I~
        {                                                          //~9A21I~
        	super.onClickOther(Pbuttonid);                        //~9A21I~
//          return;                                                //~9A21I~//~9A30R~
        }                                                          //~9A21I~
//  	onClickGame();                                             //~9A21I~//~9A30R~
//  	waitingDialog=0;                                           //~9A21I~//~9A30R~
//      showWaitingMsg();                                          //~v@@@R~//~9A30R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~//~@002R~//~9A21R~
    //*on Server only                                              //~9A26I~
    //******************************************                   //~9A26I~
    private void onClickGame()                                     //~v@@@R~//~@002R~//~9A21R~
    {                                                              //~v@@@I~//~@002R~//~9A21R~
		if (Dump.Y) Dump.println("BTRDialog:onClickGame");         //~9A21I~
//      restartGame();                                               //~9A21R~//~9A24R~//~0218R~
      if (restartGame())                                           //~0218I~
        dismiss();                                     //~v@@@R~//~@002R~//~9A21R~
    }                                                              //~v@@@I~//~@002R~//~9A21R~
    //******************************************                   //~9A30I~
    //*on Server only                                              //~9A30I~
    //******************************************                   //~9A30I~
    private void onClickSuspendGame()                                   //~9A30I~
    {                                                              //~9A30I~
		if (Dump.Y) Dump.println("BTRDialog:onClickSuspendGame");  //~9A30I~
        suspendGame();                                         //~9A30I~
        dismiss();                                                 //~9A30I~
    }                                                              //~9A30I~
    //******************************************                   //~3201I~//~@002R~//~9A21R~
//  private void restartGame()                                    //~3201I~//~@002R~//~9A21R~//~9A24R~//~0218R~
    private boolean restartGame()                                  //~0218I~
    {                                                              //~3201I~//~@002R~//~9A21R~
        if (Dump.Y) Dump.println("BTRDialog:restartGame");         //~9A24I~
		if (!AG.aBTMulti.chkMemberReconnect())                     //~0218I~
        {                                                          //~0218I~
        	UView.showToast(R.string.Err_LackingMemberReconnect);  //~0218I~
            return false;                                          //~0218I~
        }                                                          //~0218I~
        if (Status.isStatusRestarted())                            //+0218I~
        {                                                          //+0218I~
        	UView.showToast(R.string.Err_AlreadyRestarted);        //+0218I~
            return false;                                          //+0218I~
        }                                                          //+0218I~
//  	SuspendIOErrReqDlg.stopAutoTakeDiscardReset();//~9A24R~//~9A26R~//~9A27R~//~9A29R~
    	AG.aUARestart.stopAutoTakeDiscardReset();                  //~9A29I~
//      UserAction.showInfoAll(0,R.string.Info_GameRestarted);     //~9A24I~//~0115R~
		return true;                                               //~0218I~
    }                                                              //~3201I~//~@002R~//~9A21R~
    //******************************************                   //~9A30I~
    private void suspendGame()                                     //~9A30I~
    {                                                              //~9A30I~
        if (Dump.Y) Dump.println("BTRDialog:restartGame");         //~9A30I~
		if (AG.aAccounts.isServer()                                //~9A31I~
        ||  AG.aBTMulti.BTGroup.getConnectedCtr()==0)              //~9A31I~
//      	SuspendIOErrDlg.newInstance().show();                      //~9A30I~//~9A31R~//~0110R~
        	SuspendIOErrDlg.newInstance_Show();                    //~0110I~
        else    	                                               //~9A31I~
        	UView.showToast(R.string.Info_IOErrRecovered);         //~9A31I~
    }                                                              //~9A30I~
    //******************************************                   //~9A23I~
    protected void afterDismiss(int Pwaiting)                      //~9A23I~
    {                                                              //~9A23I~
        if (Dump.Y) Dump.println("BTRDialog:afterDismiss");        //~9A23I~
        super.afterDismiss(Pwaiting);                              //~9A23I~
        AG.aBTRDialog=null;                                        //~9A23I~
    }                                                              //~9A23I~
    //******************************************                   //~v@@@I~//~9731M~//~9A21R~//~9A23R~
    public static boolean isShowing()                             //~v@@@R~//~9A21R~//~9A23R~
    {                                                              //~v@@@I~//~9A21R~//~9A23R~
        BTRDialog dlg=AG.aBTRDialog;                               //~v@@@I~//~9A21R~//~9A23R~
        boolean rc= Utils.isShowingDialogFragment(dlg);             //~9709I~//~9A21R~//~9A23R~
        if (Dump.Y) Dump.println("BTRDialog.isShowing rc="+rc);    //~9709I~//~9A21R~//~9A23R~
        return rc;                                                 //~9709R~//~9A21R~//~9A23R~
    }                                                              //~v@@@I~//~9A21R~//~9A23R~
    //******************************************                   //~v@@@I~//~9A21R~//~9C23R~
    public static void onConnected(String Pdevicename,String Paddr,boolean Pswclient)//~v@@@I~//~9A21R~//~9A23R~
    {                                                              //~v@@@I~//~9A21R~//~9A23R~
    //************************                                     //~v@@@I~//~9A21R~//~9A23R~
        if (Dump.Y) Dump.println("BTRDialog.onConnected name="+Pdevicename+",addr="+Paddr+",swclient="+Pswclient);//~v@@@I~//~9A21R~//~9A23R~
        if (!isShowing())                                          //~v@@@I~//~9A21R~//~9A23R~
            return;                                                //~v@@@I~//~9A21R~//~9A23R~
        BTRDialog dlg=AG.aBTRDialog;                               //~v@@@I~//~9A21R~//~9A23R~
        dlg.onConnected(0,Pdevicename,Paddr,Pswclient);            //~v@@@I~//~9A21R~//~9A23R~
//      if (dlg.swServer)                                          //~9A24R~
//      {                                                          //~9A24R~
//      	int idx=AG.aAccounts.searchAccountByDeviceName(Pdevicename);//~9A24I~
//          int eswn=AG.aAccounts.getCurrentEswnByAccount(idx);    //~9A24I~
//          if (Dump.Y) Dump.println("BTRDialog.onConnected idxAccounts="+idx+",currentEswn="+eswn);//~9A24I~
//          UserAction.showInfoAll(0,Utils.getStr(R.string.Info_Reconnected,yn));//~9A24R~
//      }                                                          //~9A24R~
    }                                                              //~v@@@I~//~9A21R~//~9A23R~
    //******************************************                   //~9A23I~
    @Override                                                      //~9A23I~
    protected void updateButtonStatus()                            //~9A23I~
    {                                                              //~9A23I~
	    if (Dump.Y) Dump.println("BTRDialog.updateButtonStatus");  //~9A23I~
		super.updateButtonStatus();                                //~9A23I~
        updateButtonStatusGame();                                  //~9A23I~
    }                                                              //~9A23I~
    //************************************************************ //~9A30I~
    @Override                                                      //~9A30I~
    protected void setButtonStatusDefault(int PconnectedCtr)       //~9A30I~
    {                                                              //~9A30I~
        if (Dump.Y) Dump.println("BTCDialog.setButtonStatusDefault");//~9A30I~
        if (swServer)                                              //~9A30I~
        {                                                          //~9A30I~
        	btnAccept.setEnabled(true);                            //~9A30I~
	        btnConnect.setEnabled(false);                          //~9A30I~
        }                                                          //~9A30I~
        else                                                       //~9A30I~
        {                                                          //~9A30I~
        	btnAccept.setEnabled(false);                           //~9A30I~
	        btnConnect.setEnabled(true);                           //~9A30I~
        }                                                          //~9A30I~
        btnStopAccept.setEnabled(false);                           //~9A30I~
        btnDisconnect.setEnabled(PconnectedCtr!=0);                //~9A30I~
    }                                                              //~9A30I~
    //******************************************                   //~9A23I~
    private void updateButtonStatusGame()                          //~9A23I~
    {                                                              //~9A23I~
//      boolean swReconnectedAll=members.isReconnectedAll();       //~9A23I~//~0218R~
        boolean swReconnectedAll=AG.aBTMulti.chkMemberReconnect(); //~0218I~
	    if (Dump.Y) Dump.println("BTRDialog.updateButtonStatusGame swServer="+swServer+",connectionType="+connectionType+",swReconnectedAll="+swReconnectedAll);//~9A23I~//~9A24R~
        if (swServer)                                              //~9A24I~
        {                                                          //~9A30I~
			btnGame.setEnabled(swReconnectedAll);                       //~9A23I~//~9A24R~
        }                                                          //~9A30I~
        else                                                       //~9A30I~
        {                                                          //~9A30I~
//  		if (swReconnectedAll)                                  //~9A30I~//~0218R~
//          	btnConnect.setVisibility(View.GONE);               //~9A30I~//~0218R~
//          else                                                   //~9B07I~//~0218R~
//          	btnConnect.setVisibility(View.VISIBLE);            //~9B07I~//~0218R~
			btnConnect.setEnabled(!swReconnectedAll);              //~0218I~
        }                                                          //~9A30I~
        btnSuspendGame.setEnabled(swReconnectedAll);               //~9A30I~
    }                                                              //~9A23I~
}//class                                                           //~v@@@R~
