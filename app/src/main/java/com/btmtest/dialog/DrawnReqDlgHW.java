                            //*CID://+vakPR~:                             update#=  738;       //~vakPR~
//*****************************************************************//~v101I~
//2022/03/19 vakP (Bug)4ReachDrawn; if last is robot Cancel on ReqDlgHW did not work(did not continue the round)//~vakPI~
//2022/03/15 vakF in PAN mode,if option is button take, for also rinshan take wait btton push at 4kanDrawn canceled//~vakFI~
//2022/03/14 vakB (Bug)DrawnReqDlgHW; rule button was not effective//~vakBI~
//2022/03/14 vakx Dump.e when canceled on DrawnDlagHW              //~vakxI~
//2022/03/09 vakw In PAN mode,rinshan was not taken automatically when canceled 4KanDrawn//~vakwI~
//2022/03/01 vakm auto popup darwnDlgHW for 4 wind,4 kan, 4 reach  //~vakmI~
//2022/02/27 vakj (Bug)hung if drawnRegDlgHW was canceled at the timeing robot discard(discard msg was ignored by swStop)//~vakjI~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Point;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.GConst;
import com.btmtest.game.Players;
import com.btmtest.game.Robot;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.UA.UAKan;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UButtonRG;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~9303I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.game.UADelayed.*;

public class DrawnReqDlgHW extends DrawnReqDlgLast                     //~9303R~//~9307R~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.drawnreqdlghw;      //~9220I~//~9302R~//~9303R~
    private static final int LAYOUTID_SMALLFONT=R.layout.drawnreqdlghw_theme;//~vac5I~
    private static final int TITLEID=R.string.Title_DrawnReqDlgHW;//~9220I~//~9302R~//~9303R~
    private static final String HELPFILE="DrawnReqDlgHW";                //~9220I~//~9302R~//~9303R~
                                                                   //~9214I~
//  private Dialog androidDlg;                                     //~v@@@I~//~9303R~
//    private int[] rbIDs={R.id.rbDrawnPendingNo,                      //~9302I~//~9303R~
//                         R.id.rbDrawnPending,                    //~9302I~//~9303R~
//                         R.id.rbDrawnMangan,                       //~9302I~//~9303R~
//                         };                                        //~9302I~//~9303R~
    protected int[] rbIDs={                                        //~9303I~
    					 R.id.rb4Reach,                            //~9303I~//~0217R~
    					 R.id.rb4Wind,                             //~9303I~
    					 R.id.rb4Kan,                              //~9303I~
    					 R.id.rb3Ron,                              //~9303I~
    					 R.id.rb99Tile,                            //~9303I~
//  					 R.id.rbError,                             //~9303I~//~9425R~
    					 R.id.rbOther                              //~9303I~
                         };                                        //~9303I~
    protected int[] drawnReason={EGDR_4REACH,   //4                  //~9303R~//~9304R~//~0217R~
    					 	   EGDR_4WIND,      //5                //~9303R~//~0217R~
    					 	   EGDR_4KAN,       //6                //~9303R~//~0217R~
    					 	   EGDR_3RON,       //7                //~9303I~//~0217R~
    					 	   EGDR_99TILE,     //8                //~9303I~//~0217R~
//  					 	   EGDR_ERROR,                         //~0217R~
    					 	   EGDR_OTHER,      //10               //~9303I~//~0217R~
		                      };                                   //~9303I~
//    protected int reason;                                        //~9303R~
//    protected URadioGroup rgDrawnType;                           //~9303R~
//    protected int gameField,gameSeq;               //~9212I~//~9213R~//~9219R~//~9303R~
//    protected UAEndGame UAEG;                                    //~9303R~
    protected UButtonRG UBRG;                                      //~9304R~//~9308R~
    private Button btnDiscard;                                     //~9B14I~
    protected UserAction UA;//~9303I~                              //~9304R~
//  private boolean swDrawnNext;                                   //~9702I~
    private boolean sw99,sw4w,sw4k,sw3r,sw4r;                      //~9702I~
    private int parmDrawnType;                                     //~vakmI~
    private boolean parmSwStopAuto;                                //~vakwI~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public DrawnReqDlgHW()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.defaultConstructor"); //~9221R~//~9302R~//~9303R~
        UA=AG.aUserAction;                                         //~9303R~
        UAEG=AG.aUAEndGame;                                        //~9303I~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
    public static DrawnReqDlgHW newInstance()                        //~9302I~//~9303R~//~9304R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.newInstance");        //~9226I~//~9302R~//~9303R~
    	DrawnReqDlgHW dlg=new DrawnReqDlgHW();                                     //~v@@@I~//~9220R~//~9221R~//~9302R~//~9303R~
//    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~//~vac5R~
      	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//~vac5I~
    			UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN|UFDlg.FLAG_RULEBTN,//~v@@@I~//~9220R~//~9701R~//~9708R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
    //******************************************                   //~vakmI~
    public static DrawnReqDlgHW newInstance(int PdrawnType)        //~vakmI~
    {                                                              //~vakmI~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.newInstance drawnType="+PdrawnType);//~vakmI~
    	DrawnReqDlgHW dlg=new DrawnReqDlgHW();                     //~vakmI~
      	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//~vakmI~
    			UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN|UFDlg.FLAG_RULEBTN,//~vakmI~
				TITLEID,HELPFILE);                                 //~vakmI~
        dlg.parmDrawnType=PdrawnType;                                  //~vakmI~
        return dlg;                                                //~vakmI~
    }                                                              //~vakmI~
    //******************************************                   //~vakwI~
    public static DrawnReqDlgHW newInstance(int PdrawnType,boolean PswStopAuto)//~vakwI~
    {                                                              //~vakwI~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.newInstance drawnType="+PdrawnType+",swStopAuto="+PswStopAuto);//~vakwI~
	    DrawnReqDlgHW.stopAutoDrawnHW();                           //~vakwI~
    	DrawnReqDlgHW dlg=newInstance(PdrawnType);           //~vakwI~
        dlg.parmSwStopAuto=PswStopAuto;                            //~vakwI~
        return dlg;                                                //~vakwI~
    }                                                              //~vakwI~
//    //******************************************                   //~v@@@M~//~9303R~
//    @Override                                                      //~9221I~//~9303R~
//    public void onPause()                                          //~9221I~//~9303R~
//    {                                                              //~9221I~//~9303R~
//        super.onPause();                                         //~9302R~//~9303R~
//        if (Dump.Y) Dump.println("DrawnReqDld:onPause issue dismiss");//~9221R~//~9302R~//~9303R~
//        dismiss();  //because hard to make Complete.Status.ammount to parcelable//~9221I~//~9302R~//~9303R~
//    }                                                              //~9221I~//~9303R~
//    //******************************************                 //~9303R~
//    @Override                                                    //~9303R~
//    protected void initLayout(View PView)                            //~v@@@I~//~9303R~
//    {                                                              //~v@@@M~//~9303R~
//        if (Dump.Y) Dump.println("DrawnReqDlgHW.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~
//        super.initLayout(PView);                                   //~9302R~//~9303R~
//        androidDlg=getDialog();                                    //~v@@@I~//~9302R~//~9303R~
//        getRuleSetting();                                                           //~v@@@I~//~9212R~//~9302M~//~9303R~
//        setupValue();                                              //~9212I~//~9219M~//~9302R~//~9303R~
//        setButton();                                               //~9221I~//~9302R~//~9303R~
//        setRadioGroup(PView);                                      //~9302I~//~9303R~
//        setTitle();                                                //~v@@@I~//~9220R~//~9302R~//~9303R~
//    }                                                              //~v@@@M~//~9303R~
    //******************************************                   //~9413I~
    @Override                                                      //~9413I~
    protected void initLayoutAdditional(View PView)                //~9413I~
    {                                                              //~9413I~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.initLayoutAdditional");//~9413I~
        btnDiscard= UButton.bind(PView,R.id.SendDiscard,this);     //~9B14I~
//  	if (PrefSetting.isNoRelatedRule())                         //~9520I~//~9708R~
//      	((LinearLayout)UView.findViewById(PView,R.id.llRelatedRule)).setVisibility(View.GONE);//~9520I~//~9708R~
//      else                                                       //~9520I~//~9708R~
//      {                                                          //~9520I~//~9708R~
    		RuleSetting.setDrawnHW(PView,true);                        //~9425I~//~9520R~
//      }                                                          //~9520I~//~9708R~
      if (!parmSwStopAuto)                                         //~vakwI~
		stopAutoTakeDiscard();                                  //~9A20I~//~9A24R~
    }                                                              //~9413I~
    //******************************************                   //~9212I~//~9303R~//~9702R~
    @Override
    protected void getRuleSetting()                                      //~9212I~//~9303R~//~9702R~
    {                                                              //~9212I~//~9303R~//~9702R~
//  	swDrawnNext=RuleSetting.isDrawnHWNext();                   //~9702I~
    	sw3r=RuleSetting.isDrawnHW3R();                            //~9702I~
    	sw99=RuleSetting.isDrawnHW99();                            //~9702I~
    	sw4w=RuleSetting.isDrawnHW4W();                            //~9702I~
    	sw4k=RuleSetting.isDrawnHW4K();                            //~9702I~
    	sw4r=RuleSetting.isDrawnHW4R();                            //~9702I~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.getRuleSetting 3r="+sw3r+",99="+sw99+",4W="+sw4w+",4K="+sw4k+",4R="+sw4r);//~9702I~
    }                                                              //~9212I~//~9303R~//~9702R~
//    //******************************************                   //~9302I~//~9303R~
//    private void setupValue()                                      //~9302I~//~9303R~
//    {                                                              //~9302I~//~9303R~
//        Rect r=Status.getGameSeq();                                     //~9302I~//~9303R~
//        gameField=r.left;                                          //~9302I~//~9303R~
//        gameSeq=r.top;                                             //~9302I~//~9303R~
////      currentEswn=Accounts.getCurrentEswn();                         //~9302I~//~9303R~
//    }                                                              //~9302I~//~9303R~
    @Override                                                      //~9306I~
    //******************************************                   //~v@@@I~//~9220R~//~9303R~//~9306R~
    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~//~9306R~
    {                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
//      String s=Utils.getStr(TITLEID)+":"+GConst.nameESWN[gameField]+GConst.gameSeq[gameSeq];    //~v@@@I~//~9212R~//~9220R~//~9302R~//~9303R~//~9306R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(TITLEID));//~9306I~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~//~9303R~//~9306R~
    }                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
    //******************************************                   //~9811I~//~9813I~
	@Override //drawnReqDlgLast                                                      //~9811I~//~9813I~
    protected int getDialogWidth()                                 //~9811R~//~9813I~
    {                                                              //~9811I~//~9813I~
    	if (Dump.Y) Dump.println("DrawnReqDlgHW.getDialogWidth ww=0");//~9813I~//~0305R~
        return 0;       //do not set dislogwidth by portrait realwidth on UFDlg//~9813I~
    }                                                              //~9811I~//~9813I~
//    //*******************************************************      //~v@@@I~//~9303R~
//    @Override                                                      //~v@@@I~//~9303R~
//    public void onDismissDialog()                                  //~v@@@I~//~9303R~
//    {                                                              //~v@@@I~//~9303R~
//        if (Dump.Y) Dump.println("onDismissDialog");               //~v@@@I~//~9303R~
////        UAEG.reqDlg=null;                                      //~9303R~
//    }                                                              //~v@@@I~//~9303R~
//    //*******************************************************      //~9302I~//~9303R~
//    public void setButton()                                        //~9221I~//~9303R~
//    {                                                              //~9221I~//~9303R~
//    }                                                              //~9221I~//~9303R~
    //*******************************************************      //~9302I~//~9303R~
    @Override                                                      //~9303I~
    protected void setRadioGroup(View PView)                          //~9302I~//~9303R~//~9307R~//~9308R~
    {                                                              //~9302I~//~9303R~
        if (Dump.Y) Dump.println("setRadioGroup");                 //~9303R~//~9307R~//~9308R~
        UBRG=new UButtonRG((ViewGroup)PView,rbIDs.length);         //~9303I~
	    for (int ii=0;ii<rbIDs.length;ii++)                        //~9303I~
			UBRG.add(drawnReason[ii],rbIDs[ii]);                   //~9303R~
        UBRG.setEnabled(0,sw4r);                                   //~9702I~
        UBRG.setEnabled(1,sw4w);                                   //~9702I~
        UBRG.setEnabled(2,sw4k);                                   //~9702I~
        UBRG.setEnabled(3,sw3r);                                   //~9702I~
        UBRG.setEnabled(4,sw99);                                   //~9702I~
        if (parmDrawnType!=0)                                      //~vakmR~
        	UBRG.setDefaultChk(parmDrawnType);                     //~vakmR~
        else                                                       //~vakmI~
        if (AG.aComplete.getDrawn3R())                              //~9B29I~
        UBRG.setDefaultChk(EGDR_3RON);                             //~9B29I~
        else                                                       //~9B29I~
        UBRG.setDefaultChk(EGDR_OTHER);                            //~9303R~
    }                                                              //~9302I~//~9303R~
    //*******************************************************      //~9303R~
    @Override                                                      //~v@@@I~//~9221M~//~9303R~
    public void onClickOK()                                       //~v@@@R~//~9221M~//~9303R~
    {                                                              //~1602M~//~v@@@I~//~9221M~//~9303R~
        if (Dump.Y) Dump.println("onClickOK=Send");                     //~v@@@I~//~9221M~//~9302R~//~9303R~
        getSetting();                                              //~9303R~
        sendResponse(reason);                                            //~9303R~//~9308R~
        dismiss();                                                 //~9303R~
    }                                                              //~9221I~//~9303R~
    //*******************************************************      //~9701I~
    @Override                                                      //~9701I~
    public void onClickCancel()                                    //~9701I~
    {                                                              //~9701I~
        if (Dump.Y) Dump.println("onClickCancel");                 //~9701I~
	    releaseStopAutoTakeDiscard();                                   //~9701I~//~9A24R~
        dismiss();                                                 //~9701I~
    }                                                              //~9701I~
    //*******************************************************      //~9B14I~
    @Override                                                      //~9B14I~
    public void onClickOther(int Pbuttonid)                        //~9B14I~
	{                                                              //~9B14I~
        if (Dump.Y) Dump.println("DrawReqDlgHW:onClickOther id="+Integer.toHexString(Pbuttonid));//~9B14I~
        switch(Pbuttonid)                                          //~9B14I~
        {                                                          //~9B14I~
            case R.id.SendDiscard:                                 //~9B14I~
            	discardAndSend();                                  //~9B14I~
            	break;                                             //~9B14I~
            case R.id.ShowRule:                                    //~vakBI~
                onClickShowRule();                                 //~vakBI~
                break;                                             //~vakBI~
            default:                                               //~9B14I~
                if (Dump.Y) Dump.println("DrawReqDlgHW:onClickOther unknown");//~9B14I~
        }                                                          //~9B14I~
    }                                                              //~9B14I~
    //*******************************************************      //~9B14I~
    //*discard then send                                           //~9B14I~
    //*******************************************************      //~9B14I~
    private void discardAndSend()                                  //~9B14I~
	{                                                              //~9B14I~
        if (Dump.Y) Dump.println("DrawReqDlgHW:discardAndSend");   //~9B14R~
        getSetting();                                              //~9B14I~
        if (reason==EGDR_3RON)                                     //~9B14I~
        {                                                          //~9B14I~
        	UView.showToast(R.string.AE_DrawnHWDoNotDiscard);      //~9B14I~
        }                                                          //~9B14I~
        else                                                       //~9B14I~
        if (AG.aPlayers.getCurrentPlayer()==PLAYER_YOU && AG.aPlayers.getCurrentAction()==GCM_TAKE )//~9B14R~
        {                                                          //~9B14I~
            if (reason==EGDR_99TILE)                               //~9B14I~
            {                                                      //~9B14I~
                UView.showToast(R.string.AE_DrawnHWDoNotDiscard);  //~9B14I~
            }                                                      //~9B14I~
            else                                                   //~9B14I~
            {                                                      //~9B14I~
        		AG.aGC.sendMsg(GCM_DISCARD,PLAYER_YOU);            //~9B14R~
	    		onClickOK();                                       //~9B14R~
            }                                                      //~9B14I~
        }                                                          //~9B14I~
        else                                                       //~9B14I~
        {                                                          //~9B14I~
        	UView.showToast(R.string.AE_DrawnHWNotTaken);          //~9B14I~
        }                                                          //~9B14I~
    }                                                              //~9B14I~
//    //*******************************************************      //~9A20I~//~9B12R~
//    public static void stopAutoTakeDiscardFix()                    //~9A20I~//~9B12R~
//    {                                                              //~9A20I~//~9B12R~
//        if (Dump.Y) Dump.println("DrawnReqDlgHW.stopAutoTakeDiscardFix");//~9A20I~//~9B12R~
//        if (Accounts.isServer())                                 //~9A20R~//~9A24R~//~9B12R~
////          GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),0/*On*/,1/*PswStopFix*/);//TODO bug? 0-->1//~9A20R~//~9A24R~//~9B12R~
////          GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),1/*On*/,1/*PswStopFix*/);//~9A20R~//~9A24R~//~9B12R~
//            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),1/*On*/,STOPAUTO_FIX/*PswStopFix*/);//~9A24I~//~9B12R~
//        else                                                     //~9A20R~//~9A24R~//~9B12R~
////          AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,1/*On*/,1/*PswFix*/,0);//~9A20R~//~9A24R~//~9B12R~
//            AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,1/*On*/,STOPAUTO_FIX/*PswFix*/,0);//~9A24I~//~9B12R~
//    }                                                              //~9A20I~//~9B12R~
    //*******************************************************      //~9A24I~
    private static void stopAutoTakeDiscard()                       //~9A24I~//~9B12R~
    {                                                              //~9A24I~
	    if (Dump.Y) Dump.println("DrawnReqDlgHW.stopAutoTakeDiscard");//~9A24I~
        if (Accounts.isServer())                                   //~9A24I~
        {                                                          //~vakPI~
			int player=AG.aPlayers.getCurrentPlayer();             //~vakPI~
		  if (AG.aAccounts.isRobotPlayer(player))                  //~vakPI~
          {                                                        //~vakPI~
//          if (Dump.Y) Dump.println("DrawnReqDlgHW.stopAutoTakeDiscar Robot eswn="+eswn+",player="+player);//~vakPI~
			int eswn=Accounts.playerToEswn(player);                    //~vakPI~
            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,eswn,1/*On*/,STOPAUTO_DRAWNHW/*PswStopFix*/);//~vakPI~
          }                                                        //~vakPI~
          else                                                     //~vakPI~
          {                                                        //~vakPI~
//          GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),1/*On*/,0/*PswStopFix*/);//~9A24R~
            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),1/*On*/,STOPAUTO_DRAWNHW/*PswStopFix*/);//~9A24I~
          }                                                        //~vakPI~
        }                                                          //~vakPI~
        else                                                       //~9A24I~
//          AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,1/*On*/,0/*PswFix*/,0);//~9A24R~
            AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,1/*On*/,STOPAUTO_DRAWNHW/*PswFix*/,0);//~9A24I~
    }                                                              //~9A24I~
//    //*******************************************************      //~9A24I~//~vakPR~
////  public static void stopAutoTakeDiscard(boolean PswOn,int PoptStop)//~9A24I~//~vakwR~//~vakPR~
//    private static void stopAutoTakeDiscard(boolean PswOn,int PoptStop)//~vakwI~//~vakPR~
//    {                                                              //~9A24I~//~vakPR~
//        if (Dump.Y) Dump.println("DrawnReqDlgHW.stopAutoTakeDiscard sw="+PswOn+",opt="+PoptStop);//~9A24I~//~vakPR~
//        if (Accounts.isServer())                                   //~9A24I~//~vakPR~
//            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),PswOn ? 1 : 0,PoptStop);//~9A24I~//~vakPR~
//        else                                                       //~9A24I~//~vakPR~
//            AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,PswOn ? 1 : 0,PoptStop,0);//~9A24I~//~vakPR~
//    }                                                              //~9A24I~//~vakPR~
    //*******************************************************      //~vakwI~
    private static void stopAutoDrawnHW()                          //~vakwI~
    {                                                              //~vakwI~
	    if (Dump.Y) Dump.println("DrawnReqDlgHW.stopAutoDrawnHW"); //~vakwI~
		int player=AG.aPlayers.getCurrentPlayer();                 //~vakwR~
		int eswn=Accounts.playerToEswn(player);                    //~vakwI~
        int[] intp=new int[]{eswn,1/*swOn*/,STOPAUTO_DRAWNHW};     //~vakwI~
        if (Accounts.isServer())                                   //~vakwI~
            AG.aUADelayed.stopAuto(intp);                          //~vakwI~
	    if (Dump.Y) Dump.println("DrawnReqDlgHW.stopAutoDrawnHW exit");//~vakwI~
    }                                                              //~vakwI~
    //*******************************************************      //~9701I~
//  public static void releaseStopAutoTakeDiscard()                          //~9701R~//~9A20R~//~9A24R~//~vac5R~
    private static void releaseStopAutoTakeDiscard()               //~vac5I~
    {                                                              //~9701I~
	    if (Dump.Y) Dump.println("DrawnReqDlgHW.releaseStopAutoTakeDiscard");//~9701I~//~9A24R~
    	if (Accounts.isServer())                                   //~9701I~
        {	                                                       //~vakxI~
//          GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),0/*StopOff*/,0);//~9701I~//~9702R~//~9A20R~//~9A24R~
			int player=AG.aPlayers.getCurrentPlayer();             //~vakxI~
		  if (AG.aAccounts.isRobotPlayer(player))                  //~vakxI~
          {                                                        //~vakxI~
			int eswn=Accounts.playerToEswn(player);                //~vakxI~
            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,eswn,0/*StopOff*/,STOPAUTO_DRAWNHW);//~vakxI~
          }                                                        //~vakxI~
          else                                                     //~vakxI~
          {                                                        //~vakxI~
           if (AG.aPlayers.getCurrentAction()==GCM_KAN)              //~vakxI~
           {                                                       //~vakxI~
	    	if (Dump.Y) Dump.println("DrawnReqDlgHW.releaseStopAutoTakeDiscard for 4KanDrawn");//~vakxR~
			int eswn=Accounts.playerToEswn(player);                //~vakxI~
            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,eswn,0/*StopOff*/,STOPAUTO_DRAWNHW);//~vakxI~
           }                                                       //~vakxI~
           else                                                    //~vakxI~
            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),0/*StopOff*/,STOPAUTO_DRAWNHW);//~9A24I~
          }                                                        //~vakxI~
        }                                                          //~vakxI~
        else                                                       //~9701I~
//      	AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,0/*StopOff*/,0,0);//~9701I~//~9A20R~//~9A24R~
        	AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,0/*StopOff*/,STOPAUTO_DRAWNHW,0);//~9A24I~
        AG.aRoundStat.sw4ReachCanceled=true;                       //~vakmI~
	    if (Dump.Y) Dump.println("DrawnReqDlgHW.releaseStopAutoTakeDiscard sw4ReachCanceled="+AG.aRoundStat.sw4ReachCanceled);//~vakmI~
    }                                                              //~9701I~
    //*******************************************************      //~9A24I~
    //*from DrawnDlgHW.continueThisRound                           //~vakmI~//+2320R~
    //*******************************************************      //~vakmI~
    public static void releaseStopAutoTakeDiscardAll()             //~9A24I~
    {                                                              //~9A24I~
	    if (Dump.Y) Dump.println("DrawnReqDlgHW.releaseStopAutoTakeDiscardAll");//~9A24I~
    	if (Accounts.isServer())                                   //~9A24I~
//          GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),0/*StopOff*/,2);//~9A24R~
            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),0/*StopOff*/,STOPAUTO_RESETALL);//~9A24I~
        else                                                       //~9A24I~
//      	AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,0/*StopOff*/,2,0);//~9A24R~
        	AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,0/*StopOff*/,STOPAUTO_RESETALL,0);//~9A24R~
        AG.aRoundStat.sw4ReachCanceled=true;                       //~vakmI~
	    if (Dump.Y) Dump.println("DrawnReqDlgHW.releaseStopAutoTakeDiscardAll exit sw4ReachiCanceled="+AG.aRoundStat.sw4ReachCanceled);//~vakmI~
    }                                                              //~9A24I~
    //******************************************                   //~9220I~//~9303R~
    @Override                                                      //~9306I~
    protected boolean getSetting()                                   //~9220I~//~9302R~//~9303R~//~9306R~
    {                                                              //~9220I~//~9303R~
    	reason=UBRG.getChecked();                                  //~9303R~
//      if (reason==EGDR_99TILE)                                   //~9425R~
//      	reason=(Accounts.getCurrentEswn()<<EGDR_99ESWN_SHIFT)|EGDR_99TILE;//~9425R~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.getSetting reason="+reason);//~vakmI~
        return true;                                               //~9306I~
    }                                                              //~9220I~//~9303R~
//    //*******************************************************************//~9303R~
//    public  void setReplyText()                                  //~9303R~
//    {                                                            //~9303R~
//        if (Dump.Y) Dump.println("DrawnReqDlgHW.setReplyText");  //~9303R~
//        int[] replys=UAEG.getReply();                            //~9303R~
//        for (int ii = 0; ii < PLAYERS; ii++)                     //~9303R~
//        {                                                        //~9303R~
//            int reply=replys[ii];                                //~9303R~
//            int color=colorReply[reply];                         //~9303R~
//            tvsReply[ii].setBackgroundColor(color);              //~9303R~
//            String s = replayText[reply];                        //~9303R~
//            tvsReply[ii].setText(s);                             //~9303R~
//        }                                                        //~9303R~
//    }                                                            //~9303R~
    //*******************************************************      //~9303R~
    @Override                                                      //~9303I~
    public void sendResponse(int Preason)                                     //~9303R~//~9308R~
    {                                                              //~9303R~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.sendResponse reason="+Preason);    //~9303R~//~9609R~
//        if (Accounts.isServer())                                   //~9303R~//~9304R~
//        {                                                          //~9303I~//~9304R~
////          UAEG.setResponse(PLAYER_YOU,reason);                   //~9303R~//~9304R~
////          String msg=GCM_ENDGAME_DRAWN+MSG_SEPAPP+ENDGAME_DRAWN_HALFWAY+MSG_SEPAPP+reason;//~9303R~//~9304R~
////          String msg=ENDGAME_DRAWN_HALFWAY+MSG_SEPAPP+reason;  //~9304R~
////          UA.sendToClient(true/*swAll*/,false/*swRobot*/,0/*Pplayer*/,GCM_ENDGAME_DRAWN,msg);//~9303R~//~9304R~
//            setResponseOnServer(reason);                         //~9304R~
//        }                                                          //~9303I~//~9304R~
//        else                                                       //~9303R~//~9304R~
//            AG.aUserAction.sendToServer(GCM_ENDGAME_DRAWN,PLAYER_YOU,ENDGAME_DRAWN_HALFWAY,reason,0);//~9303R~//~9304R~
	    sendMsg(Preason);                                            //~9304I~//~9308R~
    }                                                              //~9303R~
    //*******************************************************      //~9304R~
    @Override                                                      //~9518I~
    public void sendMsg(int Preason)                               //~9304R~
    {                                                              //~9304R~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.sendMsg reason="+Preason);//~9304R~
//      GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN,PLAYER_YOU,ENDGAME_DRAWN_HALFWAY_RESPONSE,Preason);//~9304R~//~9426R~
		int eswn=Accounts.getCurrentEswn();                        //~9426I~
        GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN,PLAYER_YOU,ENDGAME_DRAWN_HALFWAY_RESPONSE,makeEswnReason(eswn,Preason));//~9426I~
    }                                                              //~9304R~
    //*******************************************************      //~9426I~
    public static int getEswnFromEswnReason(int Preason)               //~9426I~
    {                                                              //~9426I~
    	int rc=Preason>>EGDR_SHIFT_ESWN;                           //~9426I~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.getEswnFromEswnReason reason="+Preason+",rc="+rc);//~9426I~
        return rc;
    }                                                              //~9426I~
    //*******************************************************      //~9426I~
    public static int getReasonFromEswnReason(int Preason)             //~9426I~
    {                                                              //~9426I~
    	int rc=Preason & EGDR_MASK;                                //~9426I~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.getReasonFromEswnReason reason="+Preason+",rc="+rc);//~9426I~
        return rc;
    }                                                              //~9426I~
    //*******************************************************      //~9426I~
    public static int makeEswnReason(int Peswn,int Preason)                //~9426I~
    {                                                              //~9426I~
    	int rc=Preason;                                            //~9426I~
        if (Preason==EGDR_99TILE)                                  //~9426I~
	    	rc|=(Peswn<<EGDR_SHIFT_ESWN);                          //~9426I~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.getReasonFromEswnReason reason="+Preason+",rc="+rc);//~9426I~
        return rc;
    }                                                              //~9426I~
    //*******************************************************************//~0228I~
    //*from UADelayed.stopAuto                                     //~0228I~
    //*******************************************************************//~0228I~
    public static void setDelayedTimer(boolean PswServer)          //~0228I~
    {                                                              //~0228I~
    	if (Dump.Y) Dump.println("DrawnReqDlg.setDelayedTimer swServer="+PswServer);//~0228I~
        Players PLS=AG.aPlayers;                                   //~0228I~
        Point p=PLS.getActionBeforeDrawnHW();                      //~0228I~
        int action=p.x; int player=p.y;                            //~0228I~
        if (action==GCM_KAN)                                       //~vakxI~
        {                                                          //~vakxI~
//            int kanType=PLS.getKanType();                        //~vakxR~
//            if (kanType==KAN_RIVER)                              //~vakxR~
//                action=GCM_DISCARD;                              //~vakxR~
//            else                                                 //~vakxR~
//                action=GCM_TAKE; //take rinshan                  //~vakxR~
	    	if (Dump.Y) Dump.println("DrawnReqDlg.setDelayedTimer @@@@ return action=KAN player="+player);//~vakxR~
            return;                                                //~vakxI~
        }                                                          //~vakxI~
        if (action==GCM_DISCARD)                                   //~0228I~
        {                                                          //~0228I~
        	if (PswServer)                                         //~0228I~
            {                                                      //~0228I~
        		TileData td=PLS.tileLastDiscarded;                 //~0228I~
            	AG.aUserAction.UAD.postNextPlayerPonKan(player,td);//~0228I~
            }                                                      //~0228I~
        }                                                          //~0228I~
        else                                                       //~0228I~
        {                                                          //~0228I~
        	Robot r=AG.aAccounts.getRobot(player);                 //~0228I~
        	if (r!=null)                                           //~0228I~
            	r.afterTakeOneDrawnHW();                           //~0228I~
            else                                                   //~0228I~
	        	AG.aUserAction.UAT.setAutoDiscardTimeout(PswServer,player,action);//~0228I~
        }                                                          //~0228I~
    }                                                              //~0228I~
//    //*******************************************************************//~vakjI~//~vakFR~
//    //*from UADelayed.rescheduleRobotAction on server for robot    //~vakjI~//~vakFR~
//    //*NOT Used                                                    //~vakxI~//~vakFR~
//    //*******************************************************************//~vakjI~//~vakFR~
//    public static void rescheduleRobotAction(int Pplayer,int PlastActionID)//~vakjI~//~vakFR~
//    {                                                              //~vakjI~//~vakFR~
//        if (Dump.Y) Dump.println("DrawnReqDlg.rescheduleRobotAction player="+Pplayer+",lastActionID="+PlastActionID);//~vakjI~//~vakFR~
//        switch(PlastActionID)                                      //~vakjI~//~vakFR~
//        {                                                          //~vakjI~//~vakFR~
////        case GCM_DISCARD:   //currentPlayer is discarder         //~vakjR~//~vakFR~
////            TileData td=AG.aPlayers.tileLastDiscarded;           //~vakjR~//~vakFR~
////            if (Dump.Y) Dump.println("DrawnReqDlg.rescheduleRobotAction lastDiscarded="+td.toString());//~vakjR~//~vakFR~
////            AG.aUserAction.UAD.postNextPlayerPonKan(Pplayer,td); //~vakjR~//~vakFR~
////            break;                                               //~vakjR~//~vakFR~
//        case GCM_TAKE:      //currentplayer is taker               //~vakjI~//~vakFR~
//            Robot r=AG.aAccounts.getRobot(Pplayer);                //~vakjI~//~vakFR~
//            if (r!=null)                                           //~vakjI~//~vakFR~
//                r.sendTakeDrawnHWReleased();                       //~vakjR~//~vakFR~
//            else                                                   //~vakjI~//~vakFR~
//            {                                                      //~vakjI~//~vakFR~
//                if (Dump.Y) Dump.println("DrawnReqDlg.rescheduleRobotAction @@@@ getRobot=null");//~vakjI~//~vakFR~
//            }                                                      //~vakjI~//~vakFR~
//            break;                                                 //~vakjI~//~vakFR~
//        }                                                          //~vakjI~//~vakFR~
//        if (Dump.Y) Dump.println("DrawnReqDlg.rescheduleRobotAction exit");//~vakjI~//~vakFR~
//    }                                                              //~vakjI~//~vakFR~
    //*******************************************************************//~vakxI~
    //*from UADelayed.rescheduleRobotAction on server   Human and Robot//~vakxI~
    //*******************************************************************//~vakxI~
    public static void rescheduleKanDrawnRelease(int Pplayer/*currentPlayer*/)      //~vakxI~
    {                                                              //~vakxI~
		int timeoutAutoTake=timeoutAutoTake=RuleSettingOperation.getTimeoutTake();//~vakFR~
    	if (Dump.Y) Dump.println("DrawnReqDlg.rescheduleKanDrawnRelease player="+Pplayer+",timeoutAutoTake="+timeoutAutoTake);//~vakxI~//~vakFI~
		if (Pplayer==PLAYER_YOU)                                   //~vakxI~
        {                                                          //~vakFI~
        	if (timeoutAutoTake!=0)                                //~vakFI~
	        	AG.aGC.sendMsg(GCM_TAKE,Pplayer);		//simulate take button//~vakxI~//~vakFR~
            else                                                   //~vakFI~
//  			AG.aUserAction.UAK.infoMsgTakeRinshan(Pplayer);    //~vakFI~//~vakxR~
    			UAKan.infoMsgTakeRinshan(Pplayer);                 //~vakxI~
        }                                                          //~vakFI~
        else                                                       //~vakxI~
        {                                                          //~vakxI~
          	if (AG.aAccounts.isDummyPlayer(Pplayer))               //~vakxI~
            {                                                      //~vakFI~
            	boolean swManualRobot=AG.swTrainingMode && RuleSettingOperation.isAllowRobotAllButton();//~vakFI~
              if (swManualRobot)                                         //~va66R~//~vakCR~//~vakFI~
              {                                                    //~vakFI~
//  			AG.aUserAction.UAK.infoMsgTakeRinshan(Pplayer);    //~vakFI~//~vakxR~
    			UAKan.infoMsgTakeRinshan(Pplayer);                 //~vakxI~
		    	if (Dump.Y) Dump.println("DrawnReqDlg.rescheduleKanDrawn skip send GCM_TAKE by manualRobot option");//~vakFI~
              }                                                    //~vakFI~
              else                                                 //~vakFI~
           		Robot.autoTakeTimeoutKan(Pplayer);                 //~vakxI~
            }                                                      //~vakFI~
          	else                                                   //~vakxI~
            {                                                      //~vakFI~
	        	if (timeoutAutoTake!=0)                            //~vakFI~
					AG.aUserAction.UADL.sendMsgEmulatedToClient(GCM_TAKE,Pplayer);	//simulate take button//~vakxR~//~vakFR~
                else                                               //~vakxI~
	    			UserAction.sendErr(0/*Popt*/,Pplayer,R.string.AE_TakeKanAdditional);//~vakxI~
            }                                                      //~vakFI~
        }                                                          //~vakxI~
    }                                                              //~vakxI~
}//class                                                           //~v@@@R~
