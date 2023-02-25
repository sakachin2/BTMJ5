//*CID://+vaq2R~:                             update#=  742;       //~vaq2R~
//*****************************************************************//~v101I~
//2022/08/11 vaq2 (Bug of vap1)currentEswn was not yet set at initLayoutAdditional//~vaq2I~
//2022/08/06 vapx add Psuedo-Tennpai:No option                     //~vapxI~
//2022/08/03 vapp Psuedo tenpai; drop allNo(chk tenpai required for repeat/next round)//~vapmI~
//2022/08/02 vapm itsHand contension of main Thraed(DrawnReqDlgLast) and msgHandler Thread(Tand and discard by Robot)//~vapmI~
//2022/07/30 vapk implements keishiki tenpai                       //~vapkI~
//2022/07/18 vap1 Chk NagasiMangan on drawnreqdlgLast               //~vap1I~//+vaq2R~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/04/17 va8b add YakuFix1/2 to related of drawnReqDlgLast     //~va8bI~
//2021/02/12 va6b show keishiki tenpan on DrawnReqDlgLast DrawDlgLast//~va6bI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.app.Dialog;
import android.graphics.Rect;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.Accounts;
import com.btmtest.game.GConst;
import com.btmtest.game.RA.RAUtils;
import com.btmtest.game.RA.RoundStat;
import com.btmtest.game.RA.Shanten;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.URadioGroup;                                //~9303I~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~9303I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;
import static com.btmtest.game.RA.RAReach.*;
import static com.btmtest.game.UA.UAEndGame.*;

public class DrawnReqDlgLast extends UFDlg                             //~v@@@R~//~9220R~//~9302R~//~9307R~
{                                                                  //~2C29R~
    protected static final int LAYOUTID=R.layout.drawnreqdlglast;      //~9220I~//~9302R~//~9303R~//~9413R~
    protected static final int LAYOUTID_SMALLFONT=R.layout.drawnreqdlglast_theme;//~vac5I~
    protected static final int TITLEID=R.string.Title_DrawnReqDlgLast;//~9220I~//~9302R~//~9303R~//~9307R~
    protected static final String HELPFILE="DrawnReqDlgLast";           //~9719R~//~9C13R~
                                                                   //~9214I~
    protected Dialog androidDlg;                                     //~v@@@I~//~9303R~
    protected int[] rbIDs={R.id.rbDrawnPendingNo,                      //~9302I~//~9303R~
    					 R.id.rbDrawnPending,                    //~9302I~//~9303R~
//  					 R.id.rbDrawnMangan,                       //~9302I~//~9422R~
//  					 R.id.rbDrawnManganPending,                //~9310I~//~9422R~
                         };                                        //~9302I~
    protected int[] drawnReason={EGDR_PENDINGNO,                   //~9303R~
    					 	   EGDR_PENDING,                       //~9303R~
//  					 	   EGDR_MANGAN,                //~9303I~//~9422R~
//  					 	   EGDR_MANGAN_PENDING,                //~9310I~//~9422R~
		                      };                                   //~9303I~
//    private static final int[] colorReply={                      //~9303R~
//                                            COLOR_REPLY_NORESP,  //~9303R~
//                                            COLOR_REPLY_PENDING_NO,//~9303R~
//                                            COLOR_REPLY_PENDING, //~9303M~
//                                            COLOR_REPLY_MANGAN,  //~9303R~
//                                        };                       //~9303R~
    protected int reason;                                          //~9303R~
    protected URadioGroup rgDrawnType;                             //~9303R~
  	protected int currentEswn;                                           //~9302I~//~9303R~
    protected int gameField,gameSeq;               //~9212I~//~9213R~//~9219R~//~9303R~
    protected UAEndGame UAEG;                                      //~9303R~
    protected Accounts ACC;                                        //~9303I~
//  private TextView tvDrawnMangan;                                //~9413I~//~9422R~
//  private UCheckBox  cbDrawnManganYN,cbDrawnManganSameAsRon;     //~9413I~//~9422R~
    private UCheckBox  cbDrawnMangan;                              //~9422I~
//  private String rankDrawnMangan;                                //~9413I~//~9422R~
    private boolean swDrawnManganAvailable,swDrawnMangan;//,swDrawnManganPending;//swDrawnManganRon;       //~9413I~//~9422R~//~9505R~
//  private boolean swDrawnManganAsRon;                            //~9505I~//~9506R~
    protected Button btnShowRule;                                  //~9417I~
    private int errPsuedoPending;                                  //~vapkI~
    private TextView tvReasonNoten;                                //~vapkI~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public DrawnReqDlgLast()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9307R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.defaultConstructor"); //~9221R~//~9302R~//~9307R~
        UAEG=AG.aUAEndGame;                                        //~9303I~
        ACC=AG.aAccounts;                                          //~9303I~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
    public static DrawnReqDlgLast newInstance()                        //~9302I~//~9307R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.newInstance");        //~9226I~//~9302R~//~9307R~
        if (AG.aUAEndGame.reqDlgLast!=null)                            //~9304I~
        {                                                          //~9308I~
	        if (Dump.Y) Dump.println("DrawnReqDlgLast.newInstance AG.aUAEndGame.reqDlgLast==null");//~9308I~//~9A20R~
            return null;                                           //~9304I~
        }                                                          //~9308I~
    	DrawnReqDlgLast dlg=new DrawnReqDlgLast();                                     //~v@@@I~//~9220R~//~9221R~//~9302R~//~9307R~
//    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~//~vac5R~
      	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT  : LAYOUTID),//~vac5I~
    			UFDlg.FLAG_OKBTN|UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN|UFDlg.FLAG_RULEBTN,//~v@@@I~//~9220R~//~9708R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~
        AG.aUAEndGame.reqDlgLast=dlg;                                          //~9304I~//~9308M~
	    if (Dump.Y) Dump.println("DrawnReqDlgLast.newInstance set AG.aUAEndGAme.reqDlgLast");//~9308I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
    //******************************************                   //~9811I~
	@Override                                                      //~9811I~
    protected int getDialogWidth()                                 //~9811R~
    {                                                              //~9811I~
    	int ww=(int)(getDialogWidthSmallDevicePortrait()*RATE_SMALLDEVICE_WIDTH);//~9819I~
    	if (Dump.Y) Dump.println("DrawnReqDlgLast.getDialogWidth swSmallDevice="+AG.swSmallDevice+",ww="+ww);//~9811R~//~9813R~
        return ww;
    }                                                              //~9811I~
//    //******************************************                 //~9811R~
//    @Override                                                    //~9811R~
//    public void onStart()                                        //~9811R~
//    {                                                            //~9811R~
//        super.onStart();                                         //~9811R~
//        if (Dump.Y) Dump.println("DrawnReqDlgLast.onStart setLayout FP and MC");//~9811R~
//        UView.setDialogWidthMatchParent(androidDlg);             //~9811R~
//    }                                                            //~9811R~
    //******************************************                   //~v@@@M~
	@Override                                                      //~9221I~
    public void onPause()                                          //~9221I~
	{                                                              //~9221I~
        super.onPause();                                         //~9302R~
        if (Dump.Y) Dump.println("DrawnReqDlgLast:onPause issue dismiss");//~9221R~//~9302R~//~9303R~//~9518R~
        dismiss();  //because hard to make Complete.Status.ammount to parcelable//~9221I~//~9302R~
    }                                                              //~9221I~
    //******************************************                   //~9303I~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9307R~
        androidDlg=getDialog();                                    //~v@@@I~//~9302R~//~9413M~
        getRuleSetting();                                                           //~v@@@I~//~9212R~//~9302M~//~9413M~
        super.initLayout(PView);                                   //~9302R~
                                                                   //~9417I~
        btnShowRule     =              UButton.bind(PView,R.id.ShowRule,this);//~9417I~
                                                                   //~9417I~
        setupValue();                                              //~vaq2I~
        initLayoutAdditional(PView);                               //~9413I~
//      setupValue();                                              //~9212I~//~9219M~//~9302R~//~9413R~//~vaq2R~
        setButton();                                               //~9221I~//~9302R~
        setRadioGroup(PView);                                      //~9302I~//~9307R~//~9308R~
        setTitle();                                                //~v@@@I~//~9220R~//~9302R~
//        setReplyText();                                          //~9303R~
    }                                                              //~v@@@M~
    //******************************************                   //~9212I~
    protected void getRuleSetting()                                      //~9212I~//~9303R~
    {                                                              //~9212I~
//        swRankMUp=RuleSetting.isRankMUp();   //kiriage mangan      //~9212I~//~9302R~
//    	rankDrawnMangan=RuleSetting.getDrawnManganRank();          //~9413I~//~9422R~
    	swDrawnManganAvailable=RuleSettingYaku.isDrawnManganAvailable();//~9413I~//~9505R~
//  	swDrawnManganRon=RuleSetting.isDrawnManganRon();           //~9413I~//~9422R~
//      swDrawnManganPending=RuleSetting.isDrawnManganPending();    //~9422I~//~9505R~
//      swDrawnManganAsRon=RuleSetting.isDrawnManganAsRon();       //~9505I~//~9506R~
    }                                                              //~9212I~
    //******************************************                   //~9302I~
    protected void setupValue()                                      //~9302I~//~9303R~//~9413R~
    {                                                              //~9302I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.setUpValue");    //~9307I~
    	Rect r=Status.getGameSeq();                                     //~9302I~
        gameField=r.left;                                          //~9302I~
        gameSeq=r.top;                                             //~9302I~
	    if ((TestOption.option & TestOption.TO_DRAWNLAST_LAYOUT)!=0) //TODO TEST//~9307I~
        {                                                          //~9307I~
        }                                                          //~9307I~
        else                                                       //~9307I~
        {                                                          //~9307I~
      	currentEswn=Accounts.getCurrentEswn();                         //~9302I~//~9303R~
        }                                                          //~9307I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.currentEswn="+currentEswn);//~vaq2I~
    }                                                              //~9302I~
    //******************************************                   //~9413I~
    protected void initLayoutAdditional(View PView)                //~9413I~
    {                                                              //~9413I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.initLayoutAdditional");//~9413I~
//      UCheckBox cbDrawnManganYN       =new UCheckBox(PView,R.id.cbDrawnManganYN);//~9413M~//~9422R~
//      UCheckBox cbDrawnManganSameAsRon=new UCheckBox(PView,R.id.cbDrawnManganSameAsRon);//~9413M~//~9422R~
//      TextView tvDrawnManganRank     =(TextView)UView.findViewById(PView,R.id.tvDrawnManganRank);//~9413M~//~9422R~
        tvReasonNoten  =(TextView)UView.findViewById(PView,R.id.tvReasonNoten);//~vapkR~
                                                                   //~9413M~
//      cbDrawnManganYN.setState(swDrawnManganAvailable,true/*fixed*/);//~9413M~//~9422R~//~9505R~
//      cbDrawnManganSameAsRon.setState(swDrawnManganRon,true/*fixed*/);//~9413M~//~9422R~
//      tvDrawnManganRank.setText(rankDrawnMangan);                //~9413M~//~9422R~
//  	if (PrefSetting.isNoRelatedRule())                         //~9520I~//~9708R~
//      	((LinearLayout)UView.findViewById(PView,R.id.llRelatedRule)).setVisibility(View.GONE);//~9520I~//~9708R~
//      else                                                       //~9520I~//~9708R~
//      {                                                          //~9520I~//~9708R~
            RuleSetting.setPendingCont(PView,true/*swFixed*/);    //~9422I~//~9520R~//~9709R~
            RuleSettingYaku.setDrawnMangan(PView,true/*swFixed*/); //~9709I~
            RuleSetting.setYakuFix(PView,true/*swFixed*/);         //~va8bI~
            RuleSetting.setYakuFix2(PView,true/*swFixed*/);        //~va8bI~
            if (swDrawnManganAvailable)                            //~9520R~
                RuleSetting.setMultiRon(PView,true/*swFixed*/);    //~9520R~
            else                                                   //~9520R~
                ((LinearLayout)UView.findViewById(PView,R.id.llRuleMultiRon)).setVisibility(View.GONE);//~9520R~
//      }                                                          //~9520I~//~9708R~
                                                                   //~9422I~
//      RadioButton rbDrawnMangan      =(RadioButton)UView.findViewById(PView,R.id.rbDrawnMangan);//~9413M~//~9422R~
//      RadioButton rbDrawnManganPend  =(RadioButton)UView.findViewById(PView,R.id.rbDrawnManganPending);//~9413M~//~9422R~
        cbDrawnMangan         =new UCheckBox(PView,R.id.cbDrawnMangan);//~9422R~
        if (!swDrawnManganAvailable)                               //~9413M~//~9505R~
        {                                                          //~9413M~
//      	rbDrawnMangan.setEnabled(false);                       //~9413M~//~9422R~
//      	rbDrawnManganPend.setEnabled(false);                   //~9413M~//~9422R~
        	cbDrawnMangan.setEnabled(false);                       //~9422I~
        }                                                          //~9413M~
        else                                                       //~vap1I~
        {                                                          //~vap1I~
        	cbDrawnMangan.setState(isDrawnMangan(currentEswn));    //~vap1I~
        }                                                          //~vap1I~
        RuleSettingYaku.setKeiten(PView,true/*swFixed*/);          //~va6bR~
    }                                                              //~9413I~
    //******************************************                   //~v@@@I~//~9220R~
    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~
    {                                                              //~v@@@I~//~9220R~
//      String s=Utils.getStr(TITLEID)+":"+GConst.nameESWN[gameField]+GConst.gameSeq[gameSeq];    //~v@@@I~//~9212R~//~9220R~//~9302R~//~9306R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(TITLEID)+":"+GConst.nameESWN[currentEswn]);//~9306I~//~9311R~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~
    }                                                              //~v@@@I~//~9220R~
    //*******************************************************      //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onDismissDialog()                                  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.onDismissDialog");               //~v@@@I~//~9308R~
        UAEG.reqDlgLast=null;                                        //~9303R~//~9304R~
    }                                                              //~v@@@I~
    //*******************************************************      //~9302I~
    public void setButton()                                        //~9221I~
    {                                                              //~9221I~
    }                                                              //~9221I~
    //*******************************************************      //~9302I~
    protected void setRadioGroup(View PView)                          //~9302I~//~9307R~//~9308R~
    {                                                              //~9302I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.setRadioGroup");                 //~9303I~//~9307R~//~9308R~
//      rgDrawnType  = new URadioGroup(PView,R.id.rgDrawnType,0);   //~9303R~//~9422R~
        rgDrawnType  = new URadioGroup(PView,R.id.rgDrawnType,0,rbIDs);//~9422I~
        int pending=AG.aPlayers.getPosReach(PLAYER_YOU)<0 ? 0/*no pending*/ : 1;//~0329I~
//      if (pending==0) //not reach issued                         //~vapkR~
//          pending=isTenpai(currentEswn) ? 1 : 0;                 //~vapkR~
            pending=isTenpai(currentEswn,pending) ? 1 : 0;         //~vapkI~
        showPsuedoPending(errPsuedoPending);                       //~vapkM~
        rgDrawnType.setCheckedID(pending,false/*swFixed*/);                          //~9303I~//~9422R~//~0329R~
    }                                                              //~9302I~
    //*******************************************************      //~9303I~
    @Override                                                      //~v@@@I~//~9221M~
    public void onClickOK()                                       //~v@@@R~//~9221M~
    {                                                              //~1602M~//~v@@@I~//~9221M~
        if (Dump.Y) Dump.println("onClickOK=Send");                     //~v@@@I~//~9221M~//~9302R~
	    getSetting();                                              //~9303I~
//        UAEG.setRply(currentEswn,reason);                        //~9303R~
//        setReplyText();                                          //~9303R~
    	sendResponse(reason);                                            //~9303R~//~9308R~
//        btnOK.setEnabled(false);                                 //~9303R~
		dismiss();                                                 //~9303I~
    }                                                              //~9221I~
    //******************************************                   //~9417I~
    @Override                                                      //~9417I~
    public void onClickOther(int Pbuttonid)                        //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~9417I~
        switch(Pbuttonid)                                          //~9417I~
        {                                                          //~9417I~
            case R.id.ShowRule:                                    //~9417R~
                onClickShowRule();                                 //~9417I~
                break;                                             //~9417I~
        }                                                          //~9417I~
    }                                                              //~9417I~
    //******************************************                   //~9417I~
    protected void onClickShowRule()                               //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.onClickShowRule");//~9417I~
        showRule();                                                //~9417I~
    }                                                              //~9417I~
	//************************************************             //~9417I~
    protected void showRule()                                      //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("DrawnReqDlg.showRule");          //~9417I~
        RuleSetting.showRuleInGame();                              //~9417I~
    }                                                              //~9417I~
    //******************************************                   //~9220I~//~9303M~
    protected boolean getSetting()                                   //~9220I~//~9302R~//~9303R~//~9306R~
    {                                                              //~9220I~//~9303M~
//  	int id=rgDrawnType.getChecked();                           //~9303M~//~9422R~
    	int id=rgDrawnType.getCheckedID();                         //~9422I~
//      reason=EGDR_NONE;                                          //~9303M~//~9423R~
//        for (int ii=0;ii<rbIDs.length;ii++)                        //~9303M~//~9422R~
//        {                                                          //~9303M~//~9422R~
//            if (id==rbIDs[ii])                                     //~9303M~//~9422R~
//            {                                                      //~9303M~//~9422R~
//                reason=drawnReason[ii];                            //~9303M~//~9422R~
//                break;                                             //~9303M~//~9422R~
//            }                                                      //~9303M~//~9422R~
//        }                                                          //~9303M~//~9422R~
        reason=drawnReason[id];                                    //~9422I~
        swDrawnMangan=cbDrawnMangan.getState();                    //~9422I~
        if (swDrawnMangan)                                         //~9422I~
        {                                                          //~9422I~
//          if (swDrawnManganAsRon)                                //~9505I~//~9506R~
//  			reason=EGDR_MANGAN_RON;                            //~9505I~//~9506R~
//          else                                                   //~9505I~//~9506R~
//          if (reason==EGDR_PENDING || swDrawnManganPending)       //~9422I~//~9423R~
            if (reason==EGDR_PENDING)                              //~9423I~
				reason=EGDR_MANGAN_PENDING;                        //~9422I~
//          if (swDrawnManganPending)                              //~9423I~//~9505R~
//  			reason=EGDR_MANGAN_PENDING_BYSETTING;              //~9423I~//~9505R~
            else                                                   //~9422I~
	            reason=EGDR_MANGAN;                                //~9422I~
        }                                                          //~9422I~
        return true;                                               //~9306I~
    }                                                              //~9220I~//~9303M~
//    //*******************************************************************//~9303R~
//    public  void setReplyText()                                  //~9303R~
//    {                                                            //~9303R~
//        if (Dump.Y) Dump.println("DrawnReqDlgLast.setReplyText");    //~9303R~//~9307R~
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
    //*******************************************************      //~9303I~
    public void sendResponse(int Preason)                                     //~9303I~//~9308R~
    {                                                              //~9303I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.sendResponse reason="+Preason);      //~9303I~//~9307R~//~9308R~
//        if (Accounts.isServer())                                   //~9303I~//~9307R~
////          UAEG.setResponse(PLAYER_YOU,reason);                    //~9303R~//~9304R~//~9307R~
//            setResponseOnServer(reason);                           //~9304I~//~9307R~
//        else                                                       //~9303I~//~9307R~
//            AG.aUserAction.sendToServer(GCM_ENDGAME_DRAWN,PLAYER_YOU,ENDGAME_DRAWN_RESPONSE,reason,0);//~9303R~//~9307R~
	    sendMsg(Preason);                                           //~9307I~//~9308R~
    }                                                              //~9303I~
    //*******************************************************      //~9307I~
    public void sendMsg(int Preason)                               //~9307I~
    {                                                              //~9307I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.sendMsg reason="+Preason);//~9307R~//~9904R~
        GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN,PLAYER_YOU,ENDGAME_DRAWN_LAST_RESPONSE,Preason);//~9307I~
    }                                                              //~9307I~
    //*******************************************************      //~9304I~//~9307R~//~va66R~
    //*set tenpai without considering keiten,2han constriant       //~va66I~
    //*******************************************************      //~va66I~
//  private boolean isTenpai(int Peswn)                            //~vapkR~
    private boolean isTenpai(int Peswn,int Ppending/*1:reach issued*/)//~vapkI~
    {                                                              //~va66I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.isTenpai eswn="+Peswn+",reach issued="+Ppending);//~vapkR~
    	errPsuedoPending=0;                                        //~vapkI~
//        if (RuleSettingYaku.isPendingRankNo())                   //~vapmR~
//        {                                                        //~vapmR~
//            if (Dump.Y) Dump.println("DrawnReqDlgLast.isTenpai eswn="+Peswn+",rc=false by allNo option");//~vapmR~
//            errPsuedoPending=ERR_PSUEDO_PENDING_NO_OPTION;       //~vapmR~
//            return false;   //no keishiki tenpai allowed         //~vapmR~
//        }                                                        //~vapmR~
        int[] itsHand=new int[CTR_TILETYPE];            //for Shanten calc//~va66R~
        int ctrHand=RAUtils.setItsHand(PLAYER_YOU,itsHand);        //~va66R~
//      int shanten=AG.aShanten.getShantenMin(itsHand,ctrHand);    //~vapmR~
        int shanten= Shanten.newInstanceMainThread().getShantenMin(itsHand,ctrHand);//~vapmI~
//      boolean rc=shanten==0;                                     //~vapkR~
        if (shanten!=0)                                            //~vapkI~
        {                                                          //~vapkI~
        	if (Dump.Y) Dump.println("DrawnReqDlgLast.isTenpai eswn="+Peswn+",rc=false by shanten=0");//~vapkI~
    		errPsuedoPending=ERR_PSUEDO_PENDING_NOT_SHANTEN0;      //~vapkI~
        	return false;                                          //~vapkI~
        }                                                          //~vapkI~
        boolean rc=chkPsuedoTenpai(PLAYER_YOU,Peswn,Ppending==1,itsHand,ctrHand);//~vapkR~
	    if (Dump.Y) Dump.println("DrawnReqDlgLast.isTenpai eswn="+Peswn+",return rc="+rc+",shanten="+shanten);//~va66R~
        return rc;                                                 //~va66R~
    }                                                              //~va66I~
    //*******************************************************      //~vap1I~
    //*requested only PLAYER_YOU                                   //~vap1R~
    //*chk DrawnMangan,not yet called once,disscard is all 1,9,ji  //~vap1I~
    //*call myself is allowed                                      //~vap1I~
    //*******************************************************      //~vap1I~
    private boolean isDrawnMangan(int Peswn/*PLAYER_YOU*/)         //~vap1R~
    {                                                              //~vap1I~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.isDrawnMangan eswn="+Peswn);//~vap1I~
        boolean rc=false;                                          //~vap1I~
        if (AG.aRoundStat.getCalledStatus(Peswn)==0)               //~vap1I~
	        rc=AG.aRoundStat.chkNagashiManganDiscard(Peswn);       //~vap1I~
	    if (Dump.Y) Dump.println("DrawnReqDlgLast.isDrawnMangan eswn="+Peswn+",rc="+rc);//~vap1I~
        return rc;                                                 //~vap1I~
    }                                                              //~vap1I~
    //*******************************************************      //~vapkI~
    //*under shanten=0                                             //~vapmR~
    //*******************************************************      //~vapkI~
    private boolean chkPsuedoTenpai(int Pplayer,int Peswn,boolean PswReach,int[] PitsHand,int PctrHand)//~vapkR~
    {                                                              //~vapkI~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.chkPsuedoTenpai player="+Pplayer+",eswn="+Peswn+",swReach="+PswReach+",itsHand="+Utils.toString(PitsHand,9));//~vapkR~
        boolean rc=AG.aRAReach.chkPsuedoTenpai(Pplayer,Peswn,PswReach,PitsHand,PctrHand);//~vapkR~
    	errPsuedoPending=AG.aRAReach.errPsuedoPending;              //~vapkI~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.chkPsuedoTenpai eswn="+Peswn+",rc="+rc);//~vapkR~
        return rc;
    }                                                              //~vapkI~
    //*******************************************************      //~vapkI~
    private void showPsuedoPending(int PerrPsuedoPending)          //~vapkI~
    {                                                              //~vapkI~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.showPsuedoPending err="+PerrPsuedoPending);//~vapkI~
    	int msgid=0;                                               //~vapkI~
    	switch(PerrPsuedoPending)                                  //~vapkI~
        {                                                          //~vapkI~
   		case ERR_PSUEDO_PENDING_NOT_SHANTEN0:	//=1;              //~vapkI~
        	msgid=R.string.Err_PsuedoPending_Not_Shanten0;         //~vapkI~
        	break;                                                 //~vapkI~
    	case ERR_PSUEDO_PENDING_EMPTY:			//=2;              //~vapkI~
        	msgid=R.string.Err_PsuedoPending_Empty;                //~vapkI~
        	break;                                                 //~vapkI~
    	case ERR_PSUEDO_PENDING_FURITEN:		//=3;              //~vapkI~
        	msgid=R.string.Err_PsuedoPending_Furiten;              //~vapkI~
        	break;                                                 //~vapkI~
    	case ERR_PSUEDO_PENDING_FURITEN_REACH:	//=4;              //~vapkI~
        	msgid=R.string.Err_PsuedoPending_Furiten_Reach;        //~vapkI~
        	break;                                                 //~vapkI~
    	case ERR_PSUEDO_PENDING_FIX1:			//=5;              //~vapkI~
        	msgid=R.string.Err_PsuedoPending_Fix1;                 //~vapkI~
        	break;                                                 //~vapkI~
    	case ERR_PSUEDO_PENDING_FIX2:			//=6;              //~vapkI~
        	msgid=R.string.Err_PsuedoPending_Fix2;                 //~vapkI~
        	break;                                                 //~vapkI~
//  	case ERR_PSUEDO_PENDING_USER:			//=6;              //~vapkI~
//      	msgid=R.string.Err_PsuedoPending_User;                 //~vapkI~
//      	break;                                                 //~vapkI~
    	case ERR_PSUEDO_PENDING_UNKNOWN:		                   //~vapmI~
        	msgid=R.string.Err_PsuedoPending_Unknown;              //~vapmI~
        	break;                                                 //~vapmI~
    	case ERR_PSUEDO_PENDING_MULTIPLE:   	//=7;              //~vapmI~
        	msgid=R.string.Err_PsuedoPending_Multiple;             //~vapmI~
        	break;                                                 //~vapmI~
    	case ERR_PSUEDO_PENDING_MULTIPLE_FIX2: 	//=10;             //~vapxI~
        	msgid=R.string.Err_PsuedoPending_Multiple_Fix2;        //~vapxI~
        	break;                                                 //~vapxI~
    	case ERR_PSUEDO_PENDING_FIX:   	//=8;                      //~vapmI~
        	msgid=R.string.Err_PsuedoPending_Fix;                  //~vapmI~
        	break;                                                 //~vapmI~
//    	case ERR_PSUEDO_PENDING_NO_OPTION:		//=9;              //~vapkI~
//        	msgid=R.string.Err_PsuedoPending_No_Option;            //~vapkI~
//        	break;                                                 //~vapkI~
        default:                                                   //~vapmI~
        	msgid=R.string.Err_PsuedoPending_Tenpai;               //~vapmI~
        }                                                          //~vapkI~
        if (Dump.Y) Dump.println("DrawnReqDlgLast.showPsuedoPending msgid="+Integer.toHexString(msgid)+",tvReasonNoten="+tvReasonNoten);//~vapkI~
        if (msgid!=0)                                              //~vapkI~
        	tvReasonNoten.setText(Utils.getStr(msgid));             //~vapkI~
    }                                                              //~vapkI~
}//class                                                           //~v@@@R~
