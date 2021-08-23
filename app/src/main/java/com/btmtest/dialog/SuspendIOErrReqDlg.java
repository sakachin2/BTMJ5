//*CID://+vac5R~:                             update#=  771;       //+vac5R~
//*****************************************************************//~v101I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//+vac5I~
//2020/04/13 va02:At Server,BackButton dose not work when client app canceled by androiud-Menu button//~va02I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.app.Dialog;
import android.graphics.Rect;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btmtest.BT.BTIOThread;
import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.Accounts;
import com.btmtest.game.GConst;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.gui.UButton;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.wifi.CSI;
import com.btmtest.wifi.IPIOThread;
import com.btmtest.wifi.WDAR;

import java.util.Arrays;

import static com.btmtest.AG.*;
import static com.btmtest.StaticVars.AG;                           //~9303I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Status.*;
import static com.btmtest.game.UADelayed.*;
import static com.btmtest.utils.Alert.*;

public class SuspendIOErrReqDlg extends UFDlg                             //~v@@@R~//~9220R~//~9302R~//~9307R~//~9A18R~
{                                                                  //~2C29R~
    protected static final int LAYOUTID=R.layout.suspenddlgioereq; //~9A19R~
    protected static final int LAYOUTID_SMALLFONT=R.layout.suspenddlgioereq_theme;//+vac5I~
    protected static final int TITLEID=R.string.Title_SuspendIOErrReqDlg;//~9A19R~
    protected static final String HELPFILE="SuspendIOErrReqDlg";           //~9719R~//~9A19R~//~9A30R~//~0111R~
                                                                   //~9214I~
    protected Dialog androidDlg;                                     //~v@@@I~//~9303R~
    protected int reason;                                          //~9303R~
  	protected int currentEswn;                                           //~9302I~//~9303R~
    protected int gameField,gameSeq;               //~9212I~//~9213R~//~9219R~//~9303R~
    protected UAEndGame UAEG;                                      //~9303R~
    protected Accounts ACC;                                        //~9303I~
    private boolean swInitLayout;                                  //~9A19R~
    protected Button btnShowRule;                                  //~9417I~
	private Button btnGame;                                        //~0218I~
    private boolean swDismissBeforNew;                             //~9A18I~
    private boolean[] posIOErr;                                    //~9A18I~
    private int memberRole;                                        //~9A18I~
    private int ctrActive;                                         //~9A18I~
    private String[] accountNames;                                 //~9A19I~
    private int[] accountEswn;                                     //~9A19I~
    private TextView[]	tvsName,tvsEswn,tvsSC,tvsIOErr;            //~9A19I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public SuspendIOErrReqDlg()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9307R~//~9A18R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.defaultConstructor"); //~9221R~//~9302R~//~9307R~//~9A18R~
        AG.aSuspendIOErrReqDlg=this;                                //~9A18I~
        ACC=AG.aAccounts;                                          //~9B01I~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
    //*from menuInGame                                             //~9A19I~
    //******************************************                   //~9A19I~
    public static boolean newInstance()                        //~9302I~//~9307R~//~9A18R~//~9A19R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.newInstance testoption.ioerr="+Integer.toString(TestOption.ioerr));        //~9226I~//~9302R~//~9307R~//~9A18M~//~9A19R~
        if (TestOption.ioerr!=0)                                   //~9A19I~
        {                                                          //~9A19I~
        	int val=TestOption.ioerr;                              //~9A19I~
        	for (int ii=0;ii<PLAYERS;ii++)                         //~9A19I~
            {                                                      //~9A19I~
            	if ((val & 0x01)!=0)                               //~9A19I~
			    	Status.setSuspendByIOErr(ii);	//TODO test    //~9A19I~
                val>>=1;	                                       //~9A19I~
            }                                                      //~9A19I~
        }                                                          //~9A19I~
//      if (Status.getStatusRestart()==RESTART_NONE)               //~9A29I~//~0411R~
//      if (Status.getStatusRestart()==RESTART_NONE                //~0411I~//~va02R~
//  	&&  AG.aBTMulti.BTGroup.getConnectedCtr()!=0)              //~0411I~//~va02R~
        if (Status.getStatusRestart()==RESTART_NONE)               //~va02I~
        {                                                          //~9A29I~
//        if (!AG.aGC.isConnectionLost())                          //~va02R~
//        {                                                        //~va02R~
          	UView.showToast(R.string.Err_SuspendNoIOErr);                   //~9A29I~//~9B04R~//~9B07R~
//      	if (AG.activeSessionType==AST_BT)                      //~9B04R~
        		return false;   //exception may delayed when WD        //~9A29I~//~9B04R~//~9B07R~
//        }                                                        //~va02R~
//            boolean rc;                                            //~9B05R~//~9B07R~
//            if (true)              //TODO test                     //~9B05I~//~9B07R~
////              rc=sendKeepAlive();                                //~9B05R~//~9B07R~
//                rc= CSI.isOnlineWifi();                             //~9B05I~//~9B07R~
//            else                                                   //~9B05I~//~9B07R~
//                rc=confirmForceDisconnect();                       //~9B05I~//~9B07R~
//            return rc;            //swDismiss                                      //~9B04I~//~9B07R~
        }                                                          //~9A29I~
	    showDialog();                            //~9A18I~         //~9A19R~
        return true;                                               //~9A19I~
    }                                                              //~9A18I~
    //******************************************                   //~9A18I~
    //*from Main at IOException.endGame()                          //~9A19I~
    //******************************************                   //~9A19I~
    public static void newInstance(int Pidx/*idxMember*/)                       //~9A18I~//~9A27R~
    {                                                              //~9A18I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.newInstance idx="+Pidx);//~9A23I~
//      if (BTCDialog.isReconnecting())                            //~9A23M~//~0107R~
        if (BTCDialog.isReconnectingAny())                         //~0107I~
        {                                                          //~9A23M~
        	if (Dump.Y) Dump.println("SuspendIOErrReqDlg.newInstance disconnected from BTRDialog");//~9A23M~
            return;                                                //~9A23I~
        }                                                          //~9A23M~
        int pos=AG.aAccounts.searchAccountByDeviceName(AG.aBTMulti.BTGroup.getName(Pidx));//~9A18I~
        int eswn=AG.aAccounts.accounts[pos].ESWN;                         //at moved//~9A20I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.newInstance idx="+Pidx+",pos="+pos+",eswn="+eswn);//~9A18I~//~9A20R~
    	Status.setSuspendByIOErr(eswn);                             //~9A18I~//~9A20R~
	    showDialog();                            //~9A18I~         //~9A19R~
    }                                                              //~9A18I~
    //******************************************                   //~9A18I~
    public static void showDialog()                                //~9A18I~//~9A19R~//~0411R~
    {                                                              //~9A18I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.showDialog"); //~9A18I~
        SuspendIOErrReqDlg dlg=AG.aSuspendIOErrReqDlg;             //~9A18I~
        if (Utils.isShowingDialogFragment(dlg))                    //~9A18I~
        {                                                          //~9A18I~
            dlg.swDismissBeforNew=true;                            //~9A18I~
			dlg.dismiss();                                         //~9A18I~
        }                                                          //~9A18I~
        dlg=new SuspendIOErrReqDlg();                              //~9A18I~
//  	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9A19I~//+vac5R~
    	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//+vac5I~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_HELPBTN|FLAG_RULEBTN|FLAG_CLOSEBTN,//~9A19I~//~9A22R~
				TITLEID,HELPFILE);                                 //~9A19I~
        dlg.posIOErr=Status.getSuspendByIOErr();	//pos seq      //~9A18I~
        dlg.memberRole=AG.aBTMulti.BTGroup.getMemberRole();        //~9A18I~
        dlg.ctrActive=AG.aBTMulti.BTGroup.getConnectedCtr();                   //~9A18I~
//      dlg.stopAutoTakeDiscard();             //~9A20R~           //~9A27I~//~9A29R~
        dlg.show();                                                //~9A18I~
    }                                                              //~9A18I~
//    //*******************************************************      //~9A20I~//~9A29R~
//    private void stopAutoTakeDiscard()                      //~9A20I~//~9A27R~//~9A29R~
//    {                                                              //~9A20I~//~9A29R~
//        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.stopAutoTakeDiscard posIOErr="+Arrays.toString(posIOErr));//~9A20I~//~9A27R~//~9A29R~
//        for (int pos=0;pos<PLAYERS;pos++)                          //~9A27I~//~9A29R~
//            if (posIOErr[pos])                                     //~9A27I~//~9A29R~
//            {                                                      //~9A27I~//~9A29R~
//                int eswn=AG.aAccounts.getCurrentEswnByPosition(pos);//~9A27I~//~9A29R~
//                stopAutoTakeDiscard(eswn);           //~9A27I~   //~9A29R~
//            }                                                      //~9A27I~//~9A29R~
//    }                                                              //~9A20I~//~9A29R~
//    //*******************************************************      //~9A27I~//~9A29R~
//    private void stopAutoTakeDiscard(int PcurrentEswn)             //~9A27I~//~9A29R~
//    {                                                              //~9A27I~//~9A29R~
//        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.stopAutoTakeDiscard currentEswn="+PcurrentEswn);//~9A27I~//~9A29R~
////        if (Accounts.isServer())                                 //~9A27I~//~9A29R~
//            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,PcurrentEswn,1/*swOn*/,STOPAUTO_IOERR);//~9A27I~//~9A29R~
////        else                                                     //~9A27I~//~9A29R~
////        {                                                        //~9A27I~//~9A29R~
////            int player=Accounts.eswnToPlayer(PcurrentEswn);      //~9A27I~//~9A29R~
////            if (Dump.Y) Dump.println("SuspendIOErrReqDlg.stopAutoTakeDiscard client currentEswn="+PcurrentEswn);//~9A27I~//~9A29R~
////            AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,player,PswOn ? 1 : 0,STOPAUTO_IOERR,0);//~9A27I~//~9A29R~
////        }                                                        //~9A27I~//~9A29R~
//    }                                                              //~9A27I~//~9A29R~
//    //*******************************************************      //~9A27I~//~9A29R~
//    //*from BTRDialog by restartGame button                      //~9A29I~
//    //*******************************************************    //~9A29I~
//    public static void stopAutoTakeDiscardReset()                  //~9A27R~//~9A29R~
//    {                                                              //~9A27I~//~9A29R~
//        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.stopAutoTakeDiscardReset");//~9A27I~//~9A29R~
//        GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,0/*eswn:no meaning*/,0/*swOn*/,STOPAUTO_IOERR);//~9A27I~//~9A29R~
//    }                                                              //~9A27I~//~9A29R~
    //*******************************************************      //~9A18I~
    @Override                                                      //~9A18I~
    public void onDismissDialog()                                  //~9A18I~
    {                                                              //~9A18I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.onDismissDialog");   //~9A18I~//~9A20R~
        if (!swDismissBeforNew)                                    //~9A18I~
	        AG.aScoreDlg=null;                                     //~9A18I~
        swDismissBeforNew=false;                                   //~9A18I~
    }                                                              //~9A18I~
    //******************************************                   //~9811I~
//    @Override                                                      //~9811I~//~9B25R~
//    protected int getDialogWidth()                                 //~9811R~//~9B25R~
//    {                                                              //~9811I~//~9B25R~
//        int ww=(int)(getDialogWidthSmallDevicePortrait()*RATE_SMALLDEVICE_WIDTH);//~9819I~//~9B25R~
//        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.getDialogWidth swSmallDevice="+AG.swSmallDevice+",ww="+ww);//~9811R~//~9813R~//~9A18R~//~9B25R~
//        return ww;                                               //~9B25R~
//    }                                                              //~9811I~//~9B25R~
    //******************************************                   //~v@@@M~
	@Override                                                      //~9221I~
    public void onPause()                                          //~9221I~
	{                                                              //~9221I~
        super.onPause();                                         //~9302R~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg:onPause issue dismiss");//~9221R~//~9302R~//~9303R~//~9518R~//~9A18R~
        dismiss();  //because hard to make Complete.Status.ammount to parcelable//~9221I~//~9302R~
    }                                                              //~9221I~
    //******************************************                   //~9303I~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9307R~//~9A18R~
        androidDlg=getDialog();                                    //~v@@@I~//~9302R~//~9413M~//~9A19M~
        swInitLayout=true;                                         //~9A19I~
        layoutView=PView;                                          //~9A19I~
        super.initLayout(PView);                                   //~9A19I~
        initLayoutAdditional(PView);                               //~9A19I~
        swInitLayout=false;                                        //~9A19I~
    }                                                              //~v@@@M~
    //******************************************                   //~9A19I~
    protected void initLayoutAdditional(View PView)                //~9A19I~
    {                                                              //~9A19I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.initLayoutAdditional");//~9A19I~
    	setupValue();                                              //~9A19I~
        getRuleSetting();                                          //~9A19I~
	    showRuleSetting(PView);                                    //~9A19I~
        setupResult(PView);                                        //~9A19I~
        setAccountName();                                          //~9A19I~
        showResult();                                              //~9A19I~
        setButton(PView);                                               //~9A19I~
		setTitle();                                                //~9A19I~
    }                                                              //~9A19I~
    //******************************************                   //~9302I~//~9A19M~
    protected void setupValue()                                      //~9302I~//~9303R~//~9413R~//~9A19M~
    {                                                              //~9302I~//~9A19M~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.setUpValue");    //~9307I~//~9A18R~//~9A19M~
    	Rect r=Status.getGameSeq();                                     //~9302I~//~9A19M~
        gameField=r.left;                                          //~9302I~//~9A19M~
        gameSeq=r.top;                                             //~9302I~//~9A19M~
      	currentEswn=Accounts.getCurrentEswn();                         //~9302I~//~9303R~//~9A19M~
    	accountNames=AG.aAccounts.getAccountNamesByPosition();     //~9A19M~
    	accountEswn=AG.aAccounts.getCurrentEswnByPosition();	//Postion seq//~9A19I~
        memberRole=AG.aBTMulti.memberRole;                         //~9A19M~
    }                                                              //~9302I~//~9A19M~
    //******************************************                   //~9A19I~
    protected void setupResult(View PView)                         //~9A19I~
    {                                                              //~9A19I~
        LinearLayout[] lls=new LinearLayout[PLAYERS];              //~9A19I~
        lls[0]=(LinearLayout)    UView.findViewById(PView,R.id.llResult1);//~9A19I~
        lls[1]=(LinearLayout)    UView.findViewById(PView,R.id.llResult2);//~9A19I~
        lls[2]=(LinearLayout)    UView.findViewById(PView,R.id.llResult3);//~9A19I~
        lls[3]=(LinearLayout)    UView.findViewById(PView,R.id.llResult4);//~9A19I~
    	tvsName=new TextView[PLAYERS];                             //~9A19I~
    	tvsEswn=new TextView[PLAYERS];                             //~9A19I~
    	tvsSC=new TextView[PLAYERS];                               //~9A19I~
    	tvsIOErr=new TextView[PLAYERS];                            //~9A19I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9A19I~
        {                                                          //~9A19I~
        	LinearLayout ll=lls[ii];                               //~9A19I~
	        tvsEswn[ii]=(TextView)    UView.findViewById(ll,R.id.tvCurrentEswn);//~9A19I~
	        tvsSC[ii]=(TextView)      UView.findViewById(ll,R.id.tvServerClient);//~9A19I~
	        tvsIOErr[ii]=(TextView)      UView.findViewById(ll,R.id.tvIOErr);//~9A19I~
	        tvsName[ii]=(TextView)    UView.findViewById(ll,R.id.memberName);//~9A19I~
        }                                                          //~9A19I~
    }                                                              //~9A19I~
    //******************************************                   //~9A19I~
    private void setAccountName()                                  //~9A19I~
    {                                                              //~9A19I~
    	if (Dump.Y) Dump.println("SuspendIOErrReqDlg.setAccountName");//~9A19I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9A19I~
        {                                                          //~9A19I~
        	tvsName[ii].setText(accountNames[ii]);                 //~9A19R~
        }                                                          //~9A19I~
    }                                                              //~9A19I~
    //******************************************                   //~9A19I~
    private void showResult()                                      //~9A19I~
    {                                                              //~9A19I~
    	if (Dump.Y) Dump.println("SuspendIOErrReqDlg.showResult posIOErr="+ Arrays.toString(posIOErr));//~9A19I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9A19I~
        {                                                          //~9A19I~
	        tvsEswn[ii].setText(GConst.nameESWN[accountEswn[ii]]); //currentEswnByPosition//~9A19I~//~9B01R~
//          tvsIOErr[ii].setText(posIOErr[ii]?R.string.IPDisConnect:R.string.Null);//~9A19I~//~9B01R~
            int strid;                                             //~9B01I~
	        if (posIOErr[ii])                                      //~9B01I~
            {                                                      //~9B01I~
            	int idx=ACC.positionToMember(ii);                  //~9B01I~
//              if (AG.aBTMulti.isConnected(idx))                  //~9B01I~//~0116R~
                if (AG.aBTMulti.BTGroup.isConnected(idx))          //~0116I~
	            	strid=R.string.IPReconnected;                  //~9B01I~
                else                                               //~9B01I~
	            	strid=R.string.IPDisConnect;                   //~9B01R~
            }                                                      //~9B01I~
            else                                                   //~9B01I~
            	strid=R.string.Null;                               //~9B01I~
			tvsIOErr[ii].setText(strid);                           //~9B01I~
            int typ=AG.aAccounts.accountsByESWN[ii].type;	//initial eswn//~9A19I~
	        tvsSC[ii].setText(Accounts.getTypeStringID(typ));//~9A19R~//~9A20R~
        }                                                          //~9A19I~
    }                                                              //~9A19I~
    //******************************************                   //~9212I~
    protected void getRuleSetting()                                      //~9212I~//~9303R~
    {                                                              //~9212I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.getRuleSetting");//~9A19I~
    }                                                              //~9212I~
    //******************************************                   //~9A19I~
    protected void showRuleSetting(View PView)                     //~9A19I~
    {                                                              //~9A19I~
    	if (Dump.Y) Dump.println("SuspendIOErrReqDlg.showRuleSetting");//~9A19I~
	    RuleSettingOperation.setSuspendOption(PView,true/*swFixed*/);//~9A19I~
    }                                                              //~9A19I~
    //******************************************                   //~v@@@I~//~9220R~
    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~
    {                                                              //~v@@@I~//~9220R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(TITLEID));//~9306I~//~9311R~//~9A19R~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~
    }                                                              //~v@@@I~//~9220R~
    //*******************************************************      //~9302I~
    public void setButton(View PView)                                        //~9221I~
    {                                                              //~9221I~
        UButton.bind(PView,R.id.ShowRule,this);                    //~9A19I~
		btnGame= UButton.bind(PView,R.id.BTGame,this);       //~0218I~
        if (AG.aAccounts.isServer())                               //~0218I~
        {                                                          //~0218I~
    		Members members=AG.aBTMulti.BTGroup;                   //~0218I~
//      	boolean swReconnectedAll=members.isReconnectedAll();   //~0218R~
        	boolean swReconnectedAll=AG.aBTMulti.chkMemberReconnect();//~0218I~
			btnGame.setEnabled(swReconnectedAll);                  //~0218I~
        }                                                          //~0218I~
        else                                                       //~0218I~
			btnGame.setVisibility(View.GONE);                      //~0218I~
    }                                                              //~9221I~
    //*******************************************************      //~9303I~
    @Override                                                      //~v@@@I~//~9221M~
    public void onClickOK()     //reconnect                       //~v@@@R~//~9221M~//~0226R~
    {                                                              //~1602M~//~v@@@I~//~9221M~
        if (Dump.Y) Dump.println("onClickOK=Reconnect");                     //~v@@@I~//~9221M~//~9302R~//~9A20R~
	    reconnect();                                               //~9A20R~
		dismiss();                                                 //~9303I~
    }                                                              //~9221I~
    //*******************************************************      //~9A20I~
    @Override                                                      //~9A20I~
    public void onClickCancel()   //suspeend/gameover              //~9A20I~//~0226R~
    {                                                              //~9A20I~
        if (Dump.Y) Dump.println("onClickCancel=Suspend");         //~9A20I~
//	    suspend();                                                 //~9A20I~//~0218R~
  	  if (suspend())                                               //~0218I~
		dismiss();                                                 //~9A20I~
    }                                                              //~9A20I~
    //******************************************                   //~9417I~
    @Override                                                      //~9417I~
    public void onClickOther(int Pbuttonid)                        //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~9417I~//~9A18R~
        switch(Pbuttonid)                                          //~9417I~
        {                                                          //~9417I~
            case R.id.ShowRule:                                    //~9417R~
                onClickShowRule();                                 //~9417I~
                break;                                             //~9417I~
            case R.id.BTGame:                                      //~0218I~
				onClickGame();                                     //~0218I~
                break;                                             //~0218I~
        }                                                          //~9417I~
    }                                                              //~9417I~
    //******************************************                   //~0218I~
    //*on Server only                                              //~0218I~
    //******************************************                   //~0218I~
    private void onClickGame()                                     //~0218I~
    {                                                              //~0218I~
		if (Dump.Y) Dump.println("SuspendIOErrReqDlg:onClickGame");//~0218I~
        if (restartGame())                                         //~0218I~
	        dismiss();                                             //~0218I~
    }                                                              //~0218I~
    //******************************************                   //~0218I~
    private boolean restartGame()                                  //~0218I~
    {                                                              //~0218I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg:restartGame");//~0218I~
		if (!AG.aBTMulti.chkMemberReconnect())                     //~0218I~
        {                                                          //~0218I~
        	UView.showToast(R.string.Err_LackingMemberReconnect);  //~0218I~
            return false;                                          //~0218I~
        }                                                          //~0218I~
        if (Status.isStatusRestarted())                            //~0218I~
        {                                                          //~0218I~
        	UView.showToast(R.string.Err_AlreadyRestarted);         //~0218I~
            return false;                                          //~0218I~
        }                                                          //~0218I~
    	AG.aUARestart.stopAutoTakeDiscardReset();                  //~0218I~
//      UserAction.showInfoAll(0,R.string.Info_GameRestarted);     //~0218I~
		return true;                                               //~0218I~
    }                                                              //~0218I~
    //******************************************                   //~9417I~
    protected void onClickShowRule()                               //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.onClickShowRule");//~9417I~//~9A18R~
        showRule();                                                //~9417I~
    }                                                              //~9417I~
	//************************************************             //~9417I~
    protected void showRule()                                      //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.showRule");          //~9417I~//~9A19R~
        RuleSetting.showRuleInGame();                              //~9417I~
    }                                                              //~9417I~
	//************************************************             //~9A20I~
    protected static void reconnect()                                     //~9A20I~//~9B05R~
    {                                                              //~9A20I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.reconnect");  //~9A20I~
        if (AG.activeSessionType==AST_WD)                          //~9A19R~//~9B03I~
        {                                                          //~9A19I~//~9B03I~
        	if (Dump.Y) Dump.println("SuspendIOErrReqDlg:reconnect WD mode");//~9A19R~//~9B03I~
	        WDAR.showDialog();                                     //~9B03R~
            return;                                                //~9A19I~//~9B03I~
        }                                                          //~9A19I~//~9B03I~
        int memberRole=AG.aSuspendIOErrReqDlg.memberRole;          //~9B05I~
        BTRDialog dlg=BTRDialog.newInstance(memberRole);           //~9A21R~
        if (dlg!=null)                                             //~9A21I~
        	dlg.show();                                            //~9A21I~
    }                                                              //~9A20I~
	//************************************************             //~9A20I~
    protected boolean suspend()                                       //~9A20I~//~0218R~
    {                                                              //~9A20I~
    	boolean rc=true;                                           //~0218I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.suspend");    //~9A20I~
		if (AG.aAccounts.isServer()                                //~9A31I~
        ||  AG.aBTMulti.BTGroup.getConnectedCtr()==0)              //~9A31I~
//          SuspendIOErrDlg.newInstance().show();                      //~9A20I~//~9A31R~//~0110R~
            SuspendIOErrDlg.newInstance_Show();                    //~0110I~
        else                                                       //~9A31I~
        {                                                          //~0218I~
        	UView.showToast(R.string.Info_IOErrRecovered);         //~9A31I~
            rc=false;                                              //~0218I~
        }                                                          //~0218I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.suspend rc="+rc);//~0218I~
        return rc;                                                 //~0218I~
    }                                                              //~9A20I~
	//************************************************             //~9B04I~
    private static boolean confirmForceDisconnect()                //~9B04R~
    {                                                              //~9B04I~
        boolean rc = true;
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.conformForceDisconnect");//~9B04I~
        AlertIOErr alertIOErr=new AlertIOErr();                     //~9B04I~
        if (!AG.aAccounts.isServer())                              //~9B04I~
        {                                                          //~9B04I~
            int flag = BUTTON_POSITIVE | BUTTON_NEGATIVE;              //~9B04I~
            Alert.showAlert(TITLEID, R.string.Alert_SuspendNoIOErr, flag, alertIOErr);//~9B04R~
        }                                                          //~9B04I~
        else                                                       //~9B04R~
		{                                                          //~9B04I~
//          rc = false;                                            //~9B05R~
//          UView.showToast(R.string.Err_SuspendNoIOErrServer);    //~9B04I~//~9B05R~
            int flag = BUTTON_POSITIVE | BUTTON_NEGATIVE;          //~9B05I~
            Alert.showAlert(TITLEID, R.string.Alert_SuspendNoIOErrServer, flag, alertIOErr);//~9B05I~
        }
        return rc;
    }                                                              //~9B04R~
	//************************************************             //~9B04I~
	//************************************************             //~9B04I~
	//************************************************             //~9B04I~
	static class AlertIOErr implements Alert.AlertI                       //~9B04I~
	{                                                              //~9B04I~
    	public AlertIOErr()                                          //~9B04I~
        {                                                          //~9B04I~
        }                                                          //~9B04I~
        //*******************************************************  //~9B04R~
        @Override    //AlertI                                      //~9B04R~
        public int alertButtonAction(int Pbuttonid,int Prc)        //~9B04R~
        {                                                          //~9B04R~
            if (Dump.Y) Dump.println("SuspendIOErrReqDlg.alerButtonAction buttonID="+Pbuttonid+",rc="+Prc);//~9B04R~
            alertActionReceived(Pbuttonid,Prc);                    //~9B04R~
            return 0;                                              //~9B04R~
        }                                                          //~9B04R~
        //*******************************************************  //~9B04R~
        protected void alertActionReceived(int Pbuttonid,int Prc)  //~9B04R~
        {                                                          //~9B04R~
//          boolean swClosed=false;                                //~9B04R~//~9B05R~
            Thread t=null;                                         //~9B04R~
            Members members=AG.aBTMulti.BTGroup;                   //~9B04R~
            if (Dump.Y) Dump.println("SuependIOErrReqDlg.alerActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9B04R~
            if (Pbuttonid==BUTTON_POSITIVE)                        //~9B04R~
            {                                                      //~9B04R~
                if (Dump.Y) Dump.println("SuependIOErrReqDlg.alerActionReceived swServer="+!AG.aAccounts.isServer());//~9B05I~
                if (!AG.aAccounts.isServer())                      //~9B04R~
                {                                                  //~9B04R~
                    t=members.getThread(members.idxServer);        //~9B04R~
                    if (Dump.Y) Dump.println("SuependIOErrReqDlg.alerActionReceived idxServer="+members.idxServer+",thread="+Utils.toString(t));//~9B04R~
                    if (t!=null)                                   //~9B04R~
                    {                                              //~9B04R~
                        if (AG.activeSessionType==AST_BT)          //~9B04R~
                            ((BTIOThread)t).closeSocket();	//bluetoothSocket is closable on MainThread         //~9B04R~//~9B05R~
                        else                                       //~9B04R~
                        if (AG.activeSessionType==AST_WD)          //~9B04R~
                            ((IPIOThread)t).closeSocket();  //socket is closable on Mainthread       //~9B04R~//~9B05R~
//                      swClosed=true;                             //~9B04R~//~9B05R~
                    }                                              //~9B04R~
                }                                                  //~9B04R~
                else                                               //~9B05I~
                {                                                  //~9B05I~
                    reconnect();                                   //~9B05I~
                }                                                  //~9B05I~
            }                                                      //~9B04R~
        }                                                          //~9B04R~
	}//class AlertIOErr                                            //~9B04I~
	//************************************************             //~9B05I~
    private static boolean sendKeepAlive()                            //~9B05I~
    {                                                              //~9B05I~
        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.sendKeepAlive");//~9B05I~
        Members members=AG.aBTMulti.BTGroup;
        boolean rc=false;//~9B05I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9B05I~
        {                                                          //~9B05I~
	        if (Dump.Y) Dump.println("SuspendIOErrReqDlg.sendKeepAlive MD["+ii+"]="+members.MD[ii].toString());//~9B05I~
        	Thread t=members.getThread(ii);                        //~9B05I~
            if (t!=null)                                           //~9B05I~
                if (((BTIOThread)t).sendKeepAlive())
                    rc=true;//~9B05I~
        }
        return rc;//~9B05I~
    }                                                              //~9B05I~
}//class                                                           //~v@@@R~
