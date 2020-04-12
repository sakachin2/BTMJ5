//*CID://+DATER~:                             update#=  740;       //~v@@@R~//~9211R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.URadioGroup;
import com.btmtest.gui.USpinner;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.game.Complete.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAReach.*;

public class CompReqDlg extends UFDlg                             //~v@@@R~//~9220R~
            implements USpinner.USpinnerI                          //~9220I~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.compreqdlg;      //~9220I~
    private static final int TITLEID=R.string.Title_CompReqDlg;//~9220I~
    private static final String HELPFILE="CompReqDlg";                //~9220I~
    private static final int MULTIWINDOW_SHIFT=50;                 //~9403I~
                                                                   //~9214I~
    private static final int USPP_POINT=1;                         //~9214I~//~9220R~
    private static final int USPP_RANK=2;                          //~9214I~//~9220R~
                                                                   //~9213I~
    private static final int POINT_RANKM  =8000;
    private static final int POINT_RANKM_SHORT=7680;//~9212I~      //~9213R~
    private static final int POINT_RANK_YM=40;                     //~9224I~
    private static final int[] intsPoint={20,25,30,40,50,60,70,80,90,100,110};//~9212R~
    private static final int[] intsRank={1,2,3,4,10,15,20,30,POINT_RANK_YM/*40*/,60,80,120};//~9212I~//~9219R~//~9224R~
    private static final int COLOR_YOU=Color.argb(0xff,0x00,0xbf,0xff); //deep sky blue//~9311I~
    private static final int COLOR_REPLY_BEFORESEND=Color.argb(0xff,0x1b,0xa4,0xd7);//sky blue//~9227R~
    private static final int COLOR_REPLY_OK=Color.argb(0xff,0x00,0xff,0x00);//~9227I~
    private static final int COLOR_REPLY_NG=Color.argb(0xff,0xff,0x66,0x00);//orange//~9227R~
    private static final int COLOR_REPLY_NORESP=Color.argb(0xff,0xff,0xff,0x00);//~9221R~
    private static final int COLOR_REPLY_OTHER=Color.argb(0xff,0xc0,0xc0,0xc0);//~9221I~
    private static final int[] colorReply={COLOR_REPLY_BEFORESEND,//~9227I~
    										COLOR_REPLY_NORESP,    //~9227I~
    										COLOR_REPLY_OK,        //~9227I~
    										COLOR_REPLY_NG,        //~9227I~
    										COLOR_REPLY_OTHER,   //YOU//~9227I~
    										COLOR_REPLY_OTHER};  //ROBOT//~9227I~
//  public static final double RATE_IN_SCR=0.95;                   //~9410R~//~9806R~
    private USpinner spnPoint,spnRank;
    private UCheckBox cbDealer;                 //~v@@@I~//~9213R~
    private TextView tvPoint,tvPointCalc;                          //~9220R~
    private TextView tvCompType,tvBasePoint,tvRank;                                   //~9220I~//~9221R~
    private TextView tvWinnerEswn,tvLooserEswn;                    //~9531I~
    private TextView tvSpritEswn;                                  //~9530I~
    private LinearLayout llSpritEswn;                              //~9530I~
    private LinearLayout llCompTypeLooser;                         //~9531I~
    private TextView tvReplyE,tvReplyS,tvReplyW,tvReplyN;          //~9221I~
    private TextView[] tvsReply;                                   //~9221I~
    private TextView[] tvsReplyLabel;                              //~9311I~
                                                                   //~1A6fI~
//  protected UFDlg ufdlg;                                         //~v@@@R~//~9227R~
    private Dialog androidDlg;                                     //~v@@@I~
    public  int completeEswn,completeType,completeEswnLooser;      //~9212I~//~9217R~
    public int gameField,gameSeq,gameDup,gameReach,pointReach,pointDup;               //~9212I~//~9213R~//~9219R~
    private int idxPoint=2/*default:30Point*/,idxRank=0;                              //~9220R~//~9221R~
    private boolean swRankMUp,swDealer;                            //~9217R~
    public  boolean swTake;                                        //~9217I~
    private boolean	swInitLayout;                                  //~9214R~
    private Complete.Status compStat;                              //~9217I~
    public TileData completeTD,completeKanTakenTD;                 //~9217I~
    public CompDlgTiles ivTiles;                                   //~9218I~
    public CompDlgDora ivDora;                                     //~9219I~
//  public String title;                                           //~9219I~//~9220R~
    private int[] calcOut=new int[CALC_AMT_MAXCTR];                 //~9220I~
    private View layoutView;                                       //~9220I~
    private URadioGroup rgCompType,rgWinner;	//for TEST calc            //~9220I~//~9402R~
	private Button btnNG,btnSend,btnShowRule;                      //~9417R~
	private Accounts ACC;                                          //~9221I~
	private boolean swReceived;                                            //~9221I~
	private static final String[] replayText=AG.resource.getStringArray(R.array.compReplyText);//~9221I~
	private int currentEswn;                                       //~9221I~
	private int repaintEswn;                                       //~9306I~
    private boolean swDismissBeforNew;                             //~9322R~
    private int ctrShift;                                          //~9403I~
    private int cutEswn;                                           //~9530I~
    private int widthTileImage;                                    //~9815I~
//  public  LinearLayout llDialog;                                 //~9927R~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public CompReqDlg()                                           //~v@@@R~//~9220R~//~9221R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("CompReqDlg.defaultConstructor"); //~9221R~
        ACC=AG.aAccounts;                                          //~9221I~
        currentEswn=ACC.getCurrentEswn();                          //~9221I~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
    public static CompReqDlg newInstance(Complete.Status Pstat)                        //~v@@@R~//~9220R~//~9221R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("CompReqDlg.newInstance");        //~9226I~
    	CompReqDlg dlg=new CompReqDlg();                                     //~v@@@I~//~9220R~//~9221R~
//  	dlg.ufdlg=UFDlg.newInstance(dlg,TITLEID,LAYOUTID,//~v@@@I~ //~9220R~//~9227R~
    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~
    			UFDlg.FLAG_OKBTN|UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN|FLAG_RULEBTN,//~v@@@I~//~9220R~//~9708R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~
//      dlg.ufdlg.tagSuffix=Integer.toString(Pstat.completeEswn);	//for multiple instance//~9222I~//~9227R~
        dlg.tagSuffix=Integer.toString(Pstat.completeEswn);	//for multiple instance//~9227I~
        if (Dump.Y) Dump.println("CompReqDld:newInstance tag="+dlg.tagSuffix);//~9226I~//~9227R~
        dlg.compStat=Pstat;                                        //~9221I~
        if (Dump.Y) Dump.println("CompReqDld:newInstance completeEswn="+Pstat.completeEswn);//~9519I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
    //*******************************************************************//~9403M~
    //*for multiple dialog ,shift display position                 //~9403M~
    //*******************************************************************//~9403M~
    private void show(int Pctr)                                    //~9403M~
    {                                                              //~9403M~
		if (Dump.Y) Dump.println("CompReqDlg.show ctr="+Pctr);     //~9403M~
        ctrShift=Pctr;                                             //~9403I~
        show();                                                    //~9403I~
    }                                                              //~9403I~
    //*******************************************************************//~9403I~
    private void shiftDialog()                                     //~9403I~
    {                                                              //~9403I~
		if (Dump.Y) Dump.println("CompReqDlg.shiftDialog ctr="+ctrShift);//~9403I~
        if (ctrShift!=0)                                           //~9403I~
        {                                                          //~9403M~
            WindowManager.LayoutParams lp=androidDlg.getWindow().getAttributes();//~9403M~
            lp.y=ctrShift*MULTIWINDOW_SHIFT;                           //~9403M~
        }                                                          //~9403M~
//        expandDialogWidth(androidDlg);                           //~9410R~
    }                                                              //~9403M~
//    //*******************************************************************//~9410R~
//    public static void expandDialogWidth(Dialog Pdlg)            //~9410R~
//    {                                                            //~9410R~
//        if (Dump.Y) Dump.println("expandDialogWidth");           //~9410R~
////        WindowManager.LayoutParams lp=Pdlg.getWindow().getAttributes();//~9410R~
////        lp.width=WindowManager.LayoutParams.MATCH_PARENT;      //~9410R~
//                                                                 //~9410R~
////        WindowManager.LayoutParams lp=new WindowManager.LayoutParams();//~9410R~
////        lp.copyFrom(Pdlg.getWindow().getAttributes());         //~9410R~
////        lp.width=WindowManager.LayoutParams.MATCH_PARENT;      //~9410R~
////        Pdlg.getWindow().setAttributes(lp);                    //~9410R~
////        UView.setDialogWidth(Pdlg,RATE_IN_SCR); //0.9          //~9410R~
//    }                                                            //~9410R~
    //******************************************                   //~9410I~
	@Override                                                      //~9403I~
    public void onPause()                                          //~9403I~
	{                                                              //~9403I~
        super.onPause();                                           //~9403I~
        if (Dump.Y) Dump.println("CompReqDlg:onPause issue dismiss");//~9403I~//~9810R~
		dismiss();	//because hard to make Complete.Status.ammount to parcelable//~9403I~
    }                                                              //~9403I~
    //******************************************                   //~9410I~
	@Override                                                      //~9410I~
    public void onStart()                                          //~9410I~
	{                                                              //~9410I~
        super.onStart();                                           //~9410I~
        if (Dump.Y) Dump.println("CompReqDlg:onStart");            //~9410I~//~9810R~//~9812M~
        Dialog dlg=getDialog();                                    //~9410R~//~9411R~
        if (dlg!=null)                                             //~9410R~//~9411R~
        {                                                          //~9411R~
//      	UView.setDialogWidthWrapContent(dlg); //TODO test      //~9812I~
      		if (true) //expanded by related rule //TODO                      //~9810R~//~9812I~
            {                                                      //~9812I~
        		if (Dump.Y) Dump.println("CompReqDlg:onStart no set dialog width");//~9812I~
            	return;         //                                     //~9411I~//~9810R~//~9812I~//~9925R~
            }                                                      //~9812I~
//          UView.setDialogWidth(dlg,RATE_IN_SCR/*0.95*/,ww);      //~9410R~//~9411R~
//          dlg.getWindow().getLayoutParams().width=ww;            //~9411R~
//          ivTiles.getLayoutParams().width=getTilesWidth();       //~9411R~//~9810R~
            int ww=getTilesWidth();                                //~9810I~
//          ww+=20; //TODO test                                    //~9810I~//~9812I~//~9815R~
            if (true)                                              //~9812I~
	        	ww=measureTileLineWidth(layoutView,ww);            //~9812I~
            if (Dump.Y) Dump.println("CompReqDlg:onStart ww="+ww+",scrWidth="+AG.scrWidth);//~9810R~
            UView.setDialogWidth(dlg,ww);    //TODO test,repaint shrink dialog width on Hurwei           //~9810I~//~9815R~
//          dlg.requestLayout();                                   //~9411R~
            ivTiles.requestLayout();                               //~9411R~
        }                                                          //~9411R~
    }                                                              //~9410I~
    //******************************************                   //~9812I~
    //*at onResume                                                 //~9812I~
    //******************************************                   //~9812I~
    @Override                                                    //~9812R~//~9925R~
    protected int getDialogWidth()                               //~9812R~//~9925R~
    {                                                            //~9812R~//~9925R~
////      int ww=getTilesWidth();                                  //~9812R~//~9925R~//~9927R~
////      ww=measureTileLineWidth(layoutView,ww);                  //~9812R~//~9925R~//~9927R~
//        int ww;                                                    //~9925I~//~9927R~
//        if (AG.portrait)                                           //~9925I~//~9927R~
//        {                                                        //~9927R~
//            ww=0;   //UFFlg.machparentPortrait                     //~9925I~//~9927R~
//            ww=CompDlgTiles.getHandsMaxWidth(true/*swComp*/,0/*pairctr*/);//+88;//TODO test//~9927R~
//        }                                                        //~9927R~
//        else                                                       //~9925I~//~9927R~
//        {                                                          //~9925I~//~9927R~
//            ww=CompDlgTiles.getHandsMaxWidth(true/*swComp*/,0/*pairctr*/);//+88;//TODO test//~9925R~//~9927R~
////          ww=(int)(ww/0.95);  //HW shrink width    TODO test     //~9925R~//~9926R~//~9927R~
////          ww+=100;            //shrink width when landscape TODO test//~9925R~//~9927R~
////          ww+=22;            //20+2                            //~9927R~
//            ww=0;            //20+2                              //~9927R~
//        }                                                          //~9925I~//~9927R~
//        if (AG.portrait)                                         //~9927R~
//            ww=0;   //UFFlg.machparentPortrait                   //~9927R~
//        else                                                     //~9927R~
//        {                                                        //~9927R~
        	int ww=getTilesWidth();                                //~9927R~
        	ww+=AG.dialogPaddingHorizontal; //dialog shrink by padding,add to fully include tiles//~9927R~
//        }                                                        //~9927R~
        if (Dump.Y) Dump.println("CompReqDlg.getDialogWidth swPortrait="+AG.portrait+",ww="+ww);//~9812R~//~9925R~
        return ww;                                               //~9812R~//~9925R~
    }                                                            //~9812R~//~9925R~
    //******************************************                   //~9410I~
//  public static int getTilesWidth()                              //~9410R~//~9815R~
    public int getTilesWidth()                                     //~9815I~
    {                                                              //~9410I~
//        int ww1=AG.aHands.getLengthHands(HANDCTR);                        //~9410I~//~9411R~
//        int ww2=AG.aHands.getLengthHands(HANDCTR_TAKEN);                  //~9410I~//~9411R~
//        int ww=ww2+(ww2-ww1);   //one tile redundancy              //~9410I~//~9411R~
//      int ww=CompDlgTiles.getHandsMaxWidth();                    //~9411I~//~9815R~
//      int ww=CompDlgTiles.getHandsMaxWidth(true/*swComp*/);      //~9815R~
        int ww=widthTileImage;                                     //~9815I~
        if (Dump.Y) Dump.println("CompReqDlg:getTilesWidth ww="+ww);//~9410I~//~9411R~
        return ww;                                                 //~9410I~
    }                                                              //~9410I~
    //******************************************                   //~9812I~
    public int measureTileLineWidth(View PView,int Pww)            //~9812I~
    {                                                              //~9812I~
    	View ll=(LinearLayout)UView.findViewById(PView,R.id.LLCompTiles);//~9812I~
        int wm=ViewGroup.LayoutParams.WRAP_CONTENT;                //~9812I~
        int mode=View.MeasureSpec.UNSPECIFIED;                     //~9812I~
        int msw= View.MeasureSpec.makeMeasureSpec(Pww/*size*/,mode);//~9812R~
        int msh= View.MeasureSpec.makeMeasureSpec(0/*size*/,mode);//~9812I~
        ll.measure(msw,msh);                                       //~9812R~
        int ww=ll.getMeasuredWidth();                              //~9812I~
        int hh=ll.getMeasuredHeight();                             //~9812I~
        if (Dump.Y) Dump.println("CompReqDlg:measureTileLineWidth ww="+ww+",hh="+hh);//~9812I~
        if (ww==0)                                                 //~9812I~
        	ww=Pww;                                                //~9812I~
        return ww;                                                 //~9812I~
    }                                                              //~9812I~
    //******************************************                   //~9403I~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("CompReqDlg.initLayout");        //~v@@@R~//~9220R~
        super.initLayout(PView);
        androidDlg=getDialog();                                    //~v@@@I~
        shiftDialog();                                             //~9403I~
        layoutView=PView;                                                           //~9219I~//~9220R~
//    	llDialog=(LinearLayout)UView.findViewById(PView,R.id.DialogContainer);  //TODO test//~9927R~
        setupValue();                                              //~9212I~//~9219M~
                                                                   //~9219I~
        swInitLayout=true;                                         //~9214I~
                                                                   //~9221I~
        setButton();                                               //~9221I~
                                                                   //~9221I~
        setTextView(PView);                                        //~9221I~
                                                                   //~v@@@I~
    	if ((TestOption.option & TestOption.TO_COMPREQDLG_LAYOUT)==0) //TODO TEST//~9219I~//~9220R~
			ivDora=CompDlgDora.setImageLayout(PView);              //~9219R~
    	if ((TestOption.option & TestOption.TO_COMPREQDLG_LAYOUT)==0) //TODO TEST//~9220R~
        {                                                          //~9815I~
//  		ivTiles=CompDlgTiles.setImageLayout(PView);            //~9220I~//~9519R~
    		ivTiles=CompDlgTiles.setImageLayout(PView,compStat);   //~9519I~
            widthTileImage=ivTiles.widthTileImage;                 //~9815I~
    		if (Dump.Y) Dump.println("CompReqDlg.initLayout widthTileImage="+widthTileImage);//~9815I~
        }                                                          //~9815I~
                                                                   //~9219I~
      	setUCheckBox(PView);	//checkbox                         //~9213I~//~9214I~//~9220R~
	    setUSpinner(PView);                                        //~9214I~
//  	if (PrefSetting.isNoRelatedRule())                         //~9529I~//~9708R~
//      	((LinearLayout)UView.findViewById(PView,R.id.llRelatedRule)).setVisibility(View.GONE);//~9529I~//~9708R~
//      else                                                       //~9529I~//~9708R~
//      {                                                          //~9529I~//~9708R~
	        RuleSetting.setDora(PView,true/*swFixed*/);            //~9529I~
	        RuleSetting.setSpritPos(PView,true/*swFixed*/);        //~9530I~
//      }                                                          //~9529I~//~9708R~
        getRuleSetting();                                                           //~v@@@I~//~9212R~
        setTitle();                                                //~v@@@I~//~9220R~
        setCompType();                                             //~9218I~
        setNormalPoint();                                          //~9212I~
        setReplyText();                                            //~9221I~
        swInitLayout=false;                                        //~9214I~
    }                                                              //~v@@@M~
    //******************************************                   //~9212I~
    private void getRuleSetting()                                      //~9212I~
    {                                                              //~9212I~
    	swRankMUp=RuleSetting.isRankMUp();   //kiriage mangan      //~9212I~
    	cutEswn=ACC.getCutEswn();                                       //~9530I~
    }                                                              //~9212I~
    //******************************************                   //~9212I~
    private void setupValue()                                      //~9212I~
    {                                                              //~9212I~
    	Rect r;                                                    //~9212I~
//        if ((TestOption.option & TestOption.TO_COMPREQDLG_LAYOUT)!=0)            //~9212I~//~9220R~//~9810R~
//        {                                                          //~9212I~//~9810R~
//            setupValueTest();                                      //~9212I~//~9810R~
//            return;                                                //~9212I~//~9810R~
//        }                                                          //~9212I~//~9810R~
    //*                                                            //~9212I~
    	r=Status.getGameSeq();                                     //~9212R~
        gameField=r.left;                                          //~9212I~
        gameSeq=r.top;                                             //~9212I~
        gameDup=r.right;                                           //~9212I~
        gameReach=r.bottom;                                        //~9212I~
    //*                                                            //~9212I~
//      compStat=Status.getCompleteStatus(); //eswn+type       //~9219R~//~9220I~//~9221R~
    	completeType=compStat.completeType;                        //~9217M~
    	completeEswn=compStat.completeEswn;                        //~9217R~
    	completeEswnLooser=compStat.completeEswnLooser;            //~9217R~
    	completeTD=compStat.completeTD;                            //~9217I~
    	completeKanTakenTD=compStat.completeKanTakenTD;            //~9217I~
        swDealer=completeEswn==0;                                  //~9221I~
        swReceived=completeEswn!=currentEswn;                      //~9221R~
        if (swReceived)                                            //~9221I~
        {                                                          //~9221I~
        	calcOut=compStat.ammount;                              //~9221I~
        	idxPoint=calcOut[CALC_AMT_IDXPOINT];                   //~9221I~
        	idxRank=calcOut[CALC_AMT_IDXRANK];                     //~9221I~
        }                                                          //~9221I~
        compStat.setDlg(this);                                     //~9222I~//~9226M~
    }                                                              //~9212I~
//    //******************************************                   //~9212I~//~9810R~
//    private void setupValueTest()                                  //~9212I~//~9810R~
//    {                                                              //~9212I~//~9810R~
//        LinearLayout llTest=(LinearLayout)    UView.findViewById(layoutView,R.id.LLTest);//~9220I~//~9810R~
//        llTest.setVisibility(View.VISIBLE);                      //~9810R~
//        rgCompType=new URadioGroup(layoutView,R.id.rgCompType,0);      //~9220I~//~9810R~
//        rgWinner=new URadioGroup(layoutView,R.id.rgWinner,0);          //~9220I~//~9810R~
//        idxPoint=2;                                                //~9212I~//~9810R~
//        idxRank=2;                                                 //~9212R~//~9810R~
//    //*                                                            //~9212I~//~9810R~
//        gameField=2;                                               //~9212I~//~9810R~
//        gameSeq=3;                                                 //~9212I~//~9810R~
//        gameDup=2;                                                 //~9212I~//~9810R~
//        gameReach=3;                                               //~9212I~//~9810R~
//    //*                                                            //~9212I~//~9810R~
//        completeEswn=1;                                            //~9220R~//~9810R~
//        completeType=COMPLETE_TAKEN;                                //~9220I~//~9810R~
//        completeEswnLooser=3;                                      //~9212I~//~9810R~
//    }                                                              //~9212I~//~9810R~
//    //******************************************                   //~9220I~//~9811R~
//    private void getTestOption()                                   //~9220I~//~9811R~
//    {                                                              //~9220I~//~9811R~
//                                                                   //~9220I~//~9811R~
//        int rbid;                                                  //~9220I~//~9811R~
//        rbid=rgCompType.getChecked();                              //~9220I~//~9811R~
//        switch(rbid)                                               //~9220I~//~9811R~
//        {                                                          //~9220I~//~9811R~
//        case R.id.rbTestCompTake:                                  //~9220I~//~9811R~
//            completeType=COMPLETE_TAKEN;                           //~9220I~//~9811R~
//            swTake=true;                                           //~9220I~//~9811R~
//            break;                                                 //~9220I~//~9811R~
//        case R.id.rbTestCompRiver:                                 //~9220I~//~9811R~
//            completeType=COMPLETE_RIVER;                           //~9220I~//~9811R~
//            swTake=false;                                          //~9220I~//~9811R~
//            break;                                                 //~9220I~//~9811R~
//        }                                                          //~9220I~//~9811R~
//        rbid=rgWinner.getChecked();                                //~9220I~//~9811R~
//        switch(rbid)                                               //~9220I~//~9811R~
//        {                                                          //~9220I~//~9811R~
//        case R.id.rbWinE:                                          //~9220I~//~9811R~
//            completeEswn=0;                                        //~9220I~//~9811R~
//            break;                                                 //~9220I~//~9811R~
//        case R.id.rbWinS:                                          //~9220I~//~9811R~
//            completeEswn=1;                                        //~9220I~//~9811R~
//            break;                                                 //~9220I~//~9811R~
//        case R.id.rbWinW:                                          //~9220I~//~9811R~
//            completeEswn=2;                                        //~9220I~//~9811R~
//            break;                                                 //~9220I~//~9811R~
//        case R.id.rbWinN:                                          //~9220I~//~9811R~
//            completeEswn=3;                                        //~9220I~//~9811R~
//            break;                                                 //~9220I~//~9811R~
//        }                                                          //~9220I~//~9811R~
//    }                                                              //~9220I~//~9811R~
    //******************************************                   //~v@@@I~//~9220R~
    private void setTitle()                                        //~v@@@I~//~9220R~
    {                                                              //~v@@@I~//~9220R~
//      String s=Utils.getStr(TITLEID)+":"+GConst.nameESWN[gameField]+GConst.gameSeq[gameSeq];    //~v@@@I~//~9212R~//~9220R~//~9306R~
//      Spanned s=Status.getSpannedGameTitle(Utils.getStr(TITLEID));//~9306I~//~0218R~
		String yn=AG.aAccounts.currentEswnToAccountName(completeEswn);//~0218I~
        Spanned s=Status.getSpannedGameTitleWithName(Utils.getStr(TITLEID),yn);//~0218I~
//      title=s;                                                   //~9219I~//~9220R~
        androidDlg.setTitle(s);                                 //~v@@@R~//~9220R~//~9927R~//~0322R~
    }                                                              //~v@@@I~//~9220R~
    //******************************************                   //~9218I~
    private void setCompType()                                     //~9218I~
    {                                                              //~9218I~
    	String winner;                                 //~9218R~//~9219R~
    	Spanned txt;             //~9219I~
        boolean swLooser=true;                                     //~9218R~
    //******************************                               //~9218I~
    	if (Dump.Y) Dump.println("CompReqDlg.setCompType");       //~9218I~//~9220R~
        swTake=(completeType & (COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0;//~9220I~
    	winner=GConst.nameESWN[completeEswn];                      //~9218I~
        int compid;                                                //~9218I~
        if ((completeType & COMPLETE_KAN_TAKEN_OTHER)!=0)	//minkan ron       //~9218I~
        	compid=R.string.Info_CompType_Ron_Ankan;               //~9218I~
        else                                                       //~9218I~
        if ((completeType & COMPLETE_KAN_RIVER)!=0)	//minkan ron   //~9218R~
        	compid=R.string.Info_CompType_Minkan_RinshanKaiho;     //~9218R~
        else                                                       //~9218I~
        if ((completeType & COMPLETE_KAN_ADD)!=0)	//chankan      //~9218R~
        	compid=R.string.Info_CompType_Ron_Chankan;                 //~9218I~
        else                                                       //~9218I~
        if ((completeType & COMPLETE_KAN_TAKEN)!=0)	//minkan ron   //~9218I~
        {                                                          //~9218I~
            swLooser=false;                                        //~9218I~
        	compid=R.string.Info_CompType_Ankan_RinshanKaiho;      //~9218I~
        }                                                          //~9218I~
        else                                                       //~9218I~
        if ((completeType & COMPLETE_TAKEN)!=0)                    //~9218R~
        {                                                          //~9218I~
            swLooser=false;                                        //~9218I~
//      	compid=R.string.Info_CompTypeTake;                     //~9218R~//~9531R~
        	compid=R.string.Label_Take;                            //~9531I~
        }                                                          //~9218I~
        else                                                       //~9218I~
        	compid=R.string.Info_CompType_Ron;                     //~9218I~//~9531R~
////      String seq=GConst.nameESWN[gameField]+GConst.gameSeq[gameSeq];//~9220R~//~9531R~
//        if (swLooser)                                              //~9218R~//~9531R~
////          txt=Html.fromHtml(seq+" : "+AG.resource.getString(R.string.Info_CompTypeRon,winner,Utils.getStr(compid),GConst.nameESWN[completeEswnLooser]));//~9219R~//~9220R~//~9531R~
//            txt=Html.fromHtml(AG.resource.getString(R.string.Info_CompTypeRon,winner,Utils.getStr(compid),GConst.nameESWN[completeEswnLooser]));//~9220I~//~9531R~
//        else                                                       //~9218I~//~9531R~
////          txt=Html.fromHtml(seq+" : "+Utils.getStr(compid,winner));                       //~9218R~//~9219R~//~9220R~//~9531R~
//            txt=Html.fromHtml(Utils.getStr(compid,winner));        //~9220I~//~9531R~
//        if (Dump.Y) Dump.println("CompReqDlg.setCompType  txt="+txt);//~9218I~//~9220R~//~9531R~
////      androidDlg.setTitle(txt);                                  //~9219I~//~9220R~//~9531R~
//        tvCompType.setText(txt);                                   //~9220I~//~9531R~
		tvWinnerEswn.setText(winner);                               //~9531I~
        tvCompType.setText(Utils.getStr(compid));                   //~9531I~
        if (swLooser)                                              //~9531I~
        {                                                          //~9531I~
			tvLooserEswn.setText(nameESWN[completeEswnLooser]);    //~9531I~
        }                                                          //~9531I~
        else                                                       //~9531I~
        	llCompTypeLooser.setVisibility(View.GONE);             //~9531I~
                                                                   //~9531I~
		if (cutEswn>=0)                                            //~9530I~
        {                                                          //~9530I~
        	llSpritEswn.setVisibility(View.VISIBLE);               //~9530I~
            tvSpritEswn.setText(nameESWN[cutEswn]);                //~9530I~
        }                                                          //~9530I~
    }                                                              //~9218I~
    //******************************************
    private void setNormalPoint()                                  //~9212I~
    {
    	int net,netup;
        if (Dump.Y) Dump.println("CompReqDlg.setNormalPoint idxPoint=" + idxPoint + ",idxRank=" + idxRank);//~9214R~//~9220R~
//      cbDealer.setState(swDealer);                               //~9213I~//~9226R~
        cbDealer.setState(swDealer,true/*swFixed*/);               //~9226I~
        if (swReceived)                                            //~9221I~
        {                                                          //~9221I~
            net=calcOut[CALC_AMT_NET];                             //~9221I~
            netup=calcOut[CALC_AMT_NETUP];                         //~9221I~
        }                                                          //~9221I~
        else                                                       //~9221I~
        {                                                          //~9221I~
            int point=intsPoint[idxPoint];                             //~9212I~//~9221R~
            int rank=intsRank[idxRank];                                //~9212I~//~9221R~
//          calcPointTestCutPos(swDealer,swTake,point,rank,swRankMUp,calcOut);//TODO unit TEST//~9530R~
            calcPoint(swDealer,swTake,point,rank,swRankMUp,calcOut);                          //~9212I~//~9213R~//~9220R~//~9221R~
            netup=calcOut[CALC_AMT_NETUP];                                          //~9220R~//~9221R~
            net=calcOut[CALC_AMT_NET];                                             //~9220R~//~9221R~
            calcOut[CALC_AMT_IDXPOINT]=idxPoint;                   //~9221I~
            calcOut[CALC_AMT_IDXRANK]=idxRank;                     //~9221I~
//          spnPoint.select(idxPoint);                                 //~9212I~//~9221R~
//          spnRank.select(idxRank);                                   //~9212I~//~9221R~
        }                                                          //~9221I~
        tvPointCalc.setText(Integer.toString(net));                //~9220R~
        tvPoint.setText(Integer.toString(netup));           //~9212I~//~9220R~
    }
    //******************************************                   //~9214I~
    private boolean getNormalPoint()                               //~9214I~
    {                                                              //~9214I~
        idxPoint=spnPoint.getSelectedIndex();                      //~9214I~
        idxRank=spnRank.getSelectedIndex();                        //~9214I~
//        if ((TestOption.option & TestOption.TO_COMPREQDLG_LAYOUT)!=0)//~9220R~//~9811R~
//            getTestOption();                                       //~9220I~//~9811R~
    	if (Dump.Y) Dump.println("CompReqDlg.getNormalPoint idxPoint="+idxPoint+",idxRank="+idxRank);//~9214I~//~9220R~
        return true;                                               //~9214I~
    }                                                              //~9214I~
    //******************************************                   //~9530I~
    //*TODO TEST                                                   //~0322I~
    //******************************************                   //~0322I~
    private void calcPointTestCutPos(boolean PswDealer,boolean PswTake, int Ppoint,int Prank,boolean PswRankMUp,int[] PintOut)//~9530I~
    {                                                              //~9530I~
    	int cmp=completeEswn; int looser=completeEswnLooser;  int cut=cutEswn;//~9530I~
        int[] intOut=PintOut.clone();                              //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=0; completeEswnLooser=1; cutEswn=0;           //~9530I~
		calcPoint(true/*PswDealer*/,true/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=0; completeEswnLooser=1; cutEswn=1;           //~9530I~
		calcPoint(true/*PswDealer*/,true/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=0; completeEswnLooser=1; cutEswn=2;           //~9530I~
		calcPoint(true/*PswDealer*/,true/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=0; completeEswnLooser=1; cutEswn=0;           //~9530I~
		calcPoint(true/*PswDealer*/,false/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=0; completeEswnLooser=1; cutEswn=1;           //~9530I~
		calcPoint(true/*PswDealer*/,false/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=0; completeEswnLooser=1; cutEswn=2;           //~9530I~
		calcPoint(true/*PswDealer*/,false/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=1; completeEswnLooser=0; cutEswn=0;           //~9530I~
		calcPoint(false/*PswDealer*/,true/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=1; completeEswnLooser=0; cutEswn=1;           //~9530I~
		calcPoint(false/*PswDealer*/,true/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=1; completeEswnLooser=0; cutEswn=2;           //~9530I~
		calcPoint(false/*PswDealer*/,true/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=1; completeEswnLooser=0; cutEswn=0;           //~9530I~
		calcPoint(false/*PswDealer*/,false/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=1; completeEswnLooser=0; cutEswn=1;           //~9530I~
		calcPoint(false/*PswDealer*/,false/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	Arrays.fill(intOut,0);                                     //~9530I~
        completeEswn=1; completeEswnLooser=0; cutEswn=2;           //~9530I~
		calcPoint(false/*PswDealer*/,false/*PswTake*/,30/*Ppoint*/,4/*Prank*/,false/*PswRankMUp*/,intOut);//~9530R~
                                                                   //~9530I~
    	completeEswn=cmp; completeEswnLooser=looser; cutEswn=cut;  //~9530I~
    }                                                              //~9530I~
    //*********************************************************************//~9602R~
    //*ammountNet:original total when cutPlayer option             //~9602I~
    //*********************************************************************//~9602I~
    private void calcPoint(boolean PswDealer,boolean PswTake, int Ppoint,int Prank,boolean PswRankMUp,int[] PintOut)  //~9212R~//~9213R~//~9220R~
    {
    	int val,valup,valp,valc,ammount,ammountNet;                                   //~9212R~//~9213R~//~9220R~
    	int amtLooseDealer=0,amtLooseNonDealer=0;                  //~9220I~
    	int amtLooseNonDealerCutPlayer=0;                          //~9530I~
    	if (Dump.Y) Dump.println("CompReqDlg.calcPoint comp="+completeEswn+",looser="+completeEswnLooser+",cutEswn="+cutEswn);//~9530R~
    	if (Prank>=10)                                             //~9212I~
        {                                                          //~9212I~
        	val=POINT_RANKM*Prank/10;                              //~9212R~
            valup=val;                                             //~9212I~
        }                                                          //~9212I~
        else                                                       //~9212I~
        {                                                          //~9212I~
        	val=Ppoint*8*4/*player*/;                              //~9213R~
            for (int ii=2;ii<=Prank;ii++)                          //~9212R~
            	val*=2;                                            //~9212I~
            if (PswRankMUp)                                         //~9212M~//~9213M~//~9220R~
		    	if (val==POINT_RANKM_SHORT)    //7680                  //~9212M~//~9213I~
        	    	val=POINT_RANKM; 		  //8000               //~9213I~
            if (val>POINT_RANKM)	//8000                         //~9213I~
            	val=POINT_RANKM;                                   //~9213I~
            valup=Utils.roundUp(val,100);                          //~9213I~
        }                                                          //~9212I~
        if (PswDealer)                                             //~9212I~
        {                                                          //~9212I~
        	valup=(val*15)/10;                                     //~9212R~
        	valup=Utils.roundUp(valup,100);                        //~9213I~
            ammountNet=valup;                                      //~9220I~
        	valp=valup;                                            //~9220I~
        	valc=Utils.roundUp(valup/3,100);                       //~9213I~
            if (PswTake)                                           //~9213I~
            {                                                      //~9213I~
            	ammount=valc*3;                                    //~9213I~
                amtLooseNonDealer=valc;                        //~9213I~//~9220R~
            }                                                      //~9213I~
            else                                                   //~9213I~
            {                                                      //~9213I~
            	ammount=valup;                                     //~9213R~
            }                                                      //~9213I~
            if (cutEswn>=0)                                        //~9530I~
            {                                                      //~9530I~
                if (PswTake)                                       //~9530I~
                {                                                  //~9530I~
    	            if (cutEswn==completeEswn) //=ESWN_E           //~9530I~
                    {                                              //~9530I~
//                  	ammountNet*=2;                             //~9530I~//~9602R~
                    	ammount*=2;                                //~9530I~
    	            	amtLooseNonDealer*=2;                      //~9530I~
                    }                                              //~9530I~
					else                                           //~9530I~
                    {                                              //~9530I~
//                  	ammountNet+=valc;                          //~9530I~//~9602R~
                    	ammount+=valc;                             //~9530I~
    	            	amtLooseNonDealerCutPlayer=amtLooseNonDealer*2; //cutplayer pay of the case dealer take//~9531R~
                    }                                              //~9530I~
                }                                                  //~9530I~
                else //ron                                         //~9530I~
                {                                                  //~9530I~
    	            if (cutEswn==completeEswn||cutEswn==completeEswnLooser)//~9530I~
                    {                                              //~9530I~
//                  	ammountNet*=2;                             //~9530I~//~9602R~
                    	ammount*=2;                                //~9530I~
                    }                                              //~9530I~
                }                                                  //~9530I~
            }                                                      //~9530I~
		}                                                          //~9212I~
        else	//non dealer                                                       //~9212I~//~9213R~
        {                                                          //~9212I~
            ammountNet=valup;                                      //~9220I~
          	valp=Utils.roundUp(valup/2,100);                       //~9213I~
          	valc=Utils.roundUp(valup/4,100);                       //~9213I~
            if (PswTake)                                           //~9213I~
            {                                                      //~9213I~
            	ammount=valp+valc*2;                               //~9213I~
                amtLooseDealer=valp;                           //~9213I~//~9220R~
                amtLooseNonDealer=valc;                        //~9213I~//~9220R~
            }                                                      //~9213I~
            else                                                   //~9213I~
            {                                                      //~9213I~
            	ammount=valup;                                     //~9213I~
            }                                                      //~9213I~
            if (cutEswn>=0)                                        //~9530I~
            {                                                      //~9530I~
                if (PswTake)                                       //~9530I~
                {                                                  //~9530I~
    	            if (cutEswn==completeEswn)    //non dealer=cutPlayer taken//~9530I~
                    {                                              //~9530I~
//                  	ammountNet*=2;                             //~9530I~//~9602R~
                    	ammount*=2;                                //~9530I~
    	            	amtLooseDealer*=2;                         //~9530I~
    	            	amtLooseNonDealer*=2;                      //~9530I~
                    }                                              //~9530I~
                    else                                           //~9530I~
                    if (cutEswn==ESWN_E)	//dealer is cut player //~9530I~
                    {                                              //~9530I~
//                  	ammountNet+=valp;                          //~9530I~//~9602R~
                    	ammount+=valp;                             //~9530I~
    	            	amtLooseDealer*=2;                         //~9530I~
                    }                                              //~9530I~
					else	//non dealer(!=cutplayer taken) and cutplayer is not dealer//~9530I~
                    {                                              //~9530I~
//                  	ammountNet+=valc;                          //~9530I~//~9602R~
                    	ammount+=valc;                             //~9530I~
    	            	amtLooseNonDealerCutPlayer=valc*2;                      //~9530I~//~9531R~
                    }                                              //~9530I~
                }                                                  //~9530I~
                else //ron                                         //~9530I~
                {                                                  //~9530I~
    	            if (cutEswn==completeEswn||cutEswn==completeEswnLooser)//~9530I~
                    {                                              //~9530I~
//                  	ammountNet*=2;                             //~9530I~//~9602R~
                    	ammount*=2;                                //~9530I~
                    }                                              //~9530I~
                }                                                  //~9530I~
            }                                                      //~9530I~
        }                                                          //~9212I~
    	if (Dump.Y) Dump.println("CompReqDlg.calcPoint dealer="+PswDealer+",PswTake="+PswTake+",ammount="+ammount);//~9213R~//~9220R~
    	if (Dump.Y) Dump.println("CompReqDlg.calcPoint point="+Ppoint+",rank="+Prank+",val="+val+",ammount="+valup+",p="+valp+",c="+valc);//~9213I~//~9220R~
    	if (Dump.Y) Dump.println("CompReqDlg.calcPoint loose dealer="+amtLooseDealer+",nondealer="+amtLooseNonDealer);//~9213I~//~9220R~
        PintOut[CALC_AMT_NETUP]    =ammount;                       //~9220I~
        PintOut[CALC_AMT_NET  ]    =ammountNet;                    //~9220I~
        PintOut[CALC_AMT_DEALER]   =amtLooseDealer;     //pay of dealer when take           //~9220I~//~9531R~
        PintOut[CALC_AMT_NONDEALER]=amtLooseNonDealer;  //pay of nondealer when take//~9531R~
        PintOut[CALC_AMT_NONDEALER_CUTPOS]=amtLooseNonDealerCutPlayer;//cutplayer pay when dealer/nondealer taken//~9531R~
    	if (Dump.Y) Dump.println("CompReqDlg.calcPoint  out="+Arrays.toString(PintOut));//~9220I~
    }
    //******************************************                   //~9212I~
    //*TODO test                                                   //~9212I~
    //******************************************                   //~9212I~
    private void calcTest()                                        //~9212I~
    {                                                              //~9212I~
        for (int jj=0;jj<intsRank.length;jj++)                     //~9212I~
            for (int ii=0;ii<intsPoint.length;ii++)                //~9212R~
			    calcPoint(false,false,intsPoint[ii],intsRank[jj],swRankMUp,calcOut);       //~9212R~
        for (int jj=0;jj<intsRank.length;jj++)                     //~9212I~
            for (int ii=0;ii<intsPoint.length;ii++)                //~9212I~
			    calcPoint(true,false,intsPoint[ii],intsRank[jj],swRankMUp,calcOut);        //~9212I~
    }                                                              //~9212I~
    //******************************************                   //~9220I~
    private boolean getSetting()                                   //~9220I~
    {                                                              //~9220I~
        boolean rc=getComplete();                                          //~9220I~
        return rc;                                                 //~9220I~
    }                                                              //~9220I~
    //******************************************                   //~9220I~
    private boolean getComplete()                                  //~9220I~
    {                                                              //~9220I~
        boolean rc;
        if (Dump.Y) Dump.println("CompReqDlg.getComplete start idxPoint=" + idxPoint + ",idxRank=" + idxRank);//~9220I~
        if (Dump.Y) Dump.println("CompReqDlg.getComplete2 start idxPoint=" + idxPoint + ",idxRank=" + idxRank);//~9220I~
        if (Dump.Y) Dump.println("CompReqDlg.getComplete start idxPoint=" + idxPoint + ",idxRank=" + idxRank);//~9220I~
//      swDealer=completeEswn==0;                                  //~9220I~//~9221R~
//      cbDealer.setState(swDealer);                               //~9220I~//~9221R~
        rc=getNormalPoint();                                     //~9220I~
        if (Dump.Y) Dump.println("CompReqDlg.getComplete rc="+rc+",completeEswn="+completeEswn+",looser="+completeEswnLooser+",swDealer="+swDealer+",swTake="+swTake);//~9220I~
        return rc;                                                 //~9220I~
    }                                                              //~9220I~
    //*******************************************************      //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onDismissDialog()                                  //~v@@@I~
    {                                                              //~v@@@I~
//      if (Dump.Y) Dump.println("onDismissDialog llDialog width="+llDialog.getWidth()+",measure dialogWidth="+measureTileLineWidth(layoutView,10/*test*/));//~9925R~//~9927R~
//      if (Dump.Y) Dump.println("onDismissDialog llDialog padding="+llDialog.getPaddingRight()+",layout padding="+layoutView.getPaddingRight());//~9927R~
        compStat.dismissDlg(swDismissBeforNew);                    //~9322R~
        swDismissBeforNew=false;                                   //~9322I~
    }                                                              //~v@@@I~
    //*******************************************************      //~9221I~
    private void setTextView(View PView)                           //~9221I~
    {                                                              //~9221I~
        tvBasePoint     =(TextView)    UView.findViewById(PView,R.id.tvBasePoint);//~9221M~
        tvRank          =(TextView)    UView.findViewById(PView,R.id.tvRank);//~9221M~
        tvCompType      =(TextView)    UView.findViewById(PView,R.id.CompType);//~9212R~//~9220R~//~9221M~
        tvSpritEswn     =(TextView)    UView.findViewById(PView,R.id.tvSpritEswn);//~9530I~
        tvWinnerEswn    =(TextView)    UView.findViewById(PView,R.id.tvWinnerEswn);//~9531I~
        tvLooserEswn    =(TextView)    UView.findViewById(PView,R.id.tvLooserEswn);//~9531I~
        llCompTypeLooser=(LinearLayout)UView.findViewById(PView,R.id.llCompTypeLooser);//~9530I~//~9531R~
        llSpritEswn     =(LinearLayout)UView.findViewById(PView,R.id.llSpritEswn);//~9531I~
        tvPoint         =(TextView)    UView.findViewById(PView,R.id.pointAmmount);//~9220I~//~9221M~
        tvPointCalc     =(TextView)    UView.findViewById(PView,R.id.pointCalc);//~9220R~//~9221M~
                                                                   //~9221I~
        tvReplyE        =(TextView)    UView.findViewById(PView,R.id.ReplyE);//~9221I~
        tvReplyS        =(TextView)    UView.findViewById(PView,R.id.ReplyS);//~9221I~
        tvReplyW        =(TextView)    UView.findViewById(PView,R.id.ReplyW);//~9221I~
        tvReplyN        =(TextView)    UView.findViewById(PView,R.id.ReplyN);//~9221I~
        tvsReply=new TextView[]{tvReplyE,tvReplyS,tvReplyW,tvReplyN};//~9221I~
                                                                   //~9311I~
        TextView tvE    =(TextView)    UView.findViewById(PView,R.id.LabelReplyE);//~9311I~
        TextView tvS    =(TextView)    UView.findViewById(PView,R.id.LabelReplyS);//~9311I~
        TextView tvW    =(TextView)    UView.findViewById(PView,R.id.LabelReplyW);//~9311I~
        TextView tvN    =(TextView)    UView.findViewById(PView,R.id.LabelReplyN);//~9311I~
        tvsReplyLabel=new TextView[]{tvE,tvS,tvW,tvN};             //~9311I~
    }                                                              //~9221I~
    //*******************************************************      //~9220I~
    private void setUCheckBox(View PView)                        //~9213I~//~9214R~//~9220R~
    {                                                              //~9213I~//~9220R~
        cbDealer        =          new UCheckBox(PView,R.id.cbDealer);//~9219I~//~9220R~
//      cbDealer.setEnabled(false);                                //~9214I~//~9220R~//~9226R~
    }                                                              //~9213I~//~9220R~
    //*******************************************************      //~9214I~
    private void setUSpinner(View PView)                            //~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("CompReqDlg.setUSistener");      //~9214I~//~9220R~
                                                                   //~9214I~
        spnPoint        =          new USpinner(PView,R.id.spnPoint);//~v@@@I~//~9214M~
        spnRank         =          new USpinner(PView,R.id.spnRank );//~v@@@I~//~9214M~
                                                                   //~9214I~
        if (swReceived)                                            //~9221I~
        {                                                          //~9221I~
        	idxPoint=calcOut[CALC_AMT_IDXPOINT];                   //~9221R~
        	idxRank=calcOut[CALC_AMT_IDXRANK];                     //~9221I~
//            spnPoint.select(idxPoint);                           //~9221R~
//            spnRank.select(idxRank);                             //~9221R~
//            spnPoint.setEnabled(false);                          //~9221R~
//            spnRank.setEnabled(false);                           //~9221R~
//            spnPoint.setBackGround(Color.WHITE);                 //~9221I~
//            spnRank.setBackbround(Color.WHITE);                  //~9221I~
//			spnPoint.setVisibility(View.GONE);                     //~9221I~//~9222R~
//			spnRank.setVisibility(View.GONE);                      //~9221I~//~9222R~
//  		tvBasePoint.setVisibility(View.VISIBLE);               //~9221I~//~9222R~
//  		tvRank.setVisibility(View.VISIBLE);                    //~9221I~//~9222R~
			String[] strsPoint=AG.resource.getStringArray(R.array.NormalPoint);//~9221I~
			String[] strsRank=AG.resource.getStringArray(R.array.NormalRank);//~9221I~
            tvBasePoint.setText(strsPoint[idxPoint]);               //~9221I~
            tvRank.setText(strsRank[idxRank]);                      //~9221I~
        }                                                          //~9221I~
        else                                                       //~9221I~
        {                                                          //~9221I~
  			spnPoint.setVisibility(View.VISIBLE);                  //~9222I~
  			spnRank.setVisibility(View.VISIBLE);                   //~9222I~
            tvBasePoint.setVisibility(View.GONE);                  //~9222I~
            tvRank.setVisibility(View.GONE);                       //~9222I~
                                                                   //~9222I~
            spnPoint.setArray(R.array.NormalPoint);                    //~v@@@I~//~9214M~//~9221I~
            spnRank.setArray(R.array.NormalRank);                      //~v@@@I~//~9214M~//~9221I~
                                                                       //~9214I~//~9221I~
            spnPoint.selectNoListen(idxPoint);                             //~9221I~//~9228M~
            spnRank.selectNoListen(idxRank);                               //~9221I~//~9228M~
            spnPoint.setListener(this,USPP_POINT);                      //~9214I~//~9221I~
            spnRank.setListener(this,USPP_RANK);                        //~9214I~//~9221I~
        }                                                          //~9221I~
                                                                   //~9214I~
    }                                                              //~9214I~
    //*******************************************************      //~9214I~
    //*USpinnerI                                                   //~9214I~
    //*******************************************************      //~9214I~
    @Override                                                      //~9214I~
    public void onItemSelectedUS(int PViewID,int Ppos,int Pparm)               //~9220I~
    {                                                              //~9220I~
        if (Dump.Y) Dump.println("CompReqDlg.onItemSelected swInitLayout="+swInitLayout+",parm="+Pparm+",pos="+Ppos);//~9220I~
        if (swInitLayout)                                          //~9220I~
            return;                                                //~9220I~
        boolean swComp=false;                                       //~9220I~
        switch (Pparm)                                             //~9220I~
        {                                                          //~9220I~
        case USPP_POINT:                                           //~9220I~
        case USPP_RANK:                                            //~9220I~
            swComp=true;                                           //~9220I~
            break;                                                 //~9220I~
        }                                                          //~9220I~
        if (swComp)                                                //~9220I~
        {                                                          //~9220I~
            if (getSetting())                                      //~9220I~
    			setNormalPoint();                                   //~9220I~
        }                                                          //~9220I~
    }                                                              //~9220I~
    //*******************************************************      //~9221I~
    public void setButton()                                        //~9221I~
    {                                                              //~9221I~
        btnNG           =              UButton.bind(layoutView,R.id.CompleteNG,this);//~9221I~
        btnSend         =              UButton.bind(layoutView,R.id.Send,this);//~9221I~
        btnShowRule     =              UButton.bind(layoutView,R.id.ShowRule,this);//~9417I~
        if (completeEswn==currentEswn)                             //~9221R~
        {                                                          //~9221I~
        	btnNG.setVisibility(View.GONE);                        //~9221R~
        	btnOK.setVisibility(View.GONE);                        //~9221I~
        }                                                          //~9221I~
        else                                                       //~9221I~
        {                                                          //~9221I~
        	btnSend.setVisibility(View.GONE);                      //~9221I~
        }                                                          //~9221I~
    }                                                              //~9221I~
    //*******************************************************      //~9214I~
    @Override                                                      //~9214I~
    public void onNothingSelectedUS(int Pparm)                     //~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("CompReqDlg.onNothingSelected parm="+Pparm);//~9214I~//~9220R~
    }                                                              //~9214I~
    //******************************************                   //~9221I~
    @Override                                                      //~9221I~
    public void onClickOther(int Pbuttonid)                        //~9221I~
	{                                                              //~9221I~
    	if (Dump.Y) Dump.println("CompReqDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~9221I~//~9315R~
        switch(Pbuttonid)                                          //~9221I~
        {                                                          //~9221I~
            case R.id.CompleteNG:                                  //~9221I~
                onClickNG();                                       //~9221R~
                break;                                             //~9221I~
            case R.id.Send:                                        //~9221I~
                onClickSend();                                     //~9221I~
                break;                                             //~9221I~
            case R.id.ShowRule:                                    //~9417I~
                onClickShowRule();                                 //~9417I~
                break;                                             //~9417I~
            default:                                               //~9221I~
        }                                                          //~9221I~
    }                                                              //~9221I~
    //******************************************                   //~9221M~
    @Override                                                      //~v@@@I~//~9221M~
    public void onClickOK()                                       //~v@@@R~//~9221M~
    {                                                              //~1602M~//~v@@@I~//~9221M~
        if (Dump.Y) Dump.println("onClickOK");                     //~v@@@I~//~9221M~
        compStat.setOK(currentEswn,true);                              //~9221R~
        sendReply(true);                                           //~9221I~
        if (compStat.swReplyAll)                                   //~9410I~
	    	 CompleteDlg.showResult(compStat.completeEswn);        //~9410R~
	    sendOpen();                                                //~0329I~
        dismiss();                                                 //~9221I~
    }                                                              //~1602M~//~v@@@I~//~9221M~
    //******************************************                   //~9221I~
    public void onClickNG()                                        //~9221R~
    {                                                              //~9221I~
        if (Dump.Y) Dump.println("onClickNG");                     //~9221R~
        compStat.setOK(currentEswn,false);                             //~9221I~
        sendReply(false);                                          //~9221I~
        if (compStat.swReplyAll)                                   //~9410I~
	    	 CompleteDlg.showResult(compStat.completeEswn);        //~9410I~
        dismiss();                                                 //~9221I~
    }                                                              //~9221I~
    //******************************************                   //~9221I~
    public void onClickSend()                                      //~9221I~
    {                                                              //~9221I~
        if (Dump.Y) Dump.println("CompReqDlg.onClickSend");                   //~9221R~//~0119R~
        compStat.setAmmount(calcOut);                              //~9221M~
        compStat.requestSent(); //fill all by NORESP status                  //~9222I~//~9227R~
    	setReplyText();	//draw after reset                         //~9222I~
        String msg=compStat.getAmmountMsgText();                   //~9221R~
        ACC.sendToAll(GCM_COMPDLG_REQ,msg);                        //~9221R~
//      btnSend.setEnabled(false);                                 //~9222M~//~9320R~
        dismiss();                                                 //~9221I~//~9222R~//+0401R~
    }                                                              //~9221I~
    //******************************************                   //~9417I~
    public void onClickShowRule()                                  //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("CompReqDlg.onClickShowRule");    //~9417I~
        showRule();                                                //~9417I~
    }                                                              //~9417I~
    //*******************************************************************//~9221I~
    //*From ACAction when received GCM_COMPDLG_REQ                 //~9221I~
    //*******************************************************************//~9221I~
    public static void onReceivedRequest(int PmsgID,int Psender,String PmsgData)//~9221R~
    {                                                              //~9221I~
    	int skipEswn;                                              //~9227I~
        int[] amts=ACAction.parseAppData(PmsgData);                //~9221I~
        int srcEswn=amts[0];	//completeEswn                                       //~9221I~//~9227R~
        if (Dump.Y) Dump.println("CompReqDlg.onReceivedRequest sender="+Psender+",msgdata="+PmsgData+",srcEswn="+srcEswn);//~9221R~
        Complete.Status stat=AG.aComplete.getStatus(srcEswn);      //~9221R~
        if (stat==null)                                            //~9226I~
        {                                                          //~9226I~
	        if (Dump.Y) Dump.println("CompReqDlg.onReceivedRequest no status found eswn="+srcEswn);//~9226I~
            return;                                                //~9226I~
        }                                                          //~9226I~
        if (PmsgID==GCM_COMPDLG_REQ)                               //~9221R~
        {                                                          //~9221R~
        	if (Utils.isShowingDialogFragment(stat.compReqDlg))    //~9226I~
            {                                                      //~9226I~
            	UView.showToast(R.string.Err_DuplicatedDialog);    //~9226I~
//          	return;                                            //~9226I~//~9322I~
                stat.compReqDlg.swDismissBeforNew=true;            //~9322I~
                stat.compReqDlg.dismiss();                         //~9322I~
            }                                                      //~9226I~
            Complete.setAmmount(amts,CALC_AMT_POS,stat);           //~9221R~
        	stat.requestSent();                                    //~9222I~//~9227R~
            newInstance(stat).show();                              //~9221R~
            skipEswn=srcEswn;                                      //~9227I~
        }                                                          //~9221R~
        else   //GCM_COMPDLG_RESP                               //~9221R~//~0218R~
        {                                                          //~9221R~
        	int replyEswn=amts[1];   //replay sender                                  //~9221I~//~9227R~
            stat.setOK(replyEswn,amts[2]!=0);                        //~9221R~//~9227R~
            UView.showToast(AG.resource.getString(R.string.Info_ReplyToCompReq,GConst.nameESWN[replyEswn],//~9227I~
            			Utils.getStr(amts[2]!=0 ? R.string.OK : R.string.NG)));//~9227I~
            CompReqDlg dlg=stat.compReqDlg;                        //~9306I~
            if (dlg!=null)                                         //~9306I~
            	dlg.repaint(stat);                                         //~9222I~//~9306R~
            if (stat.swReplyAll)                                  //~9227I~
            {                                                      //~0301I~
//          	stat.setInvalid(false);    //for the case re-request//~0301R~
//          	stat.setErr(false);                                //~0301R~
	            CompleteDlg.showResult(srcEswn);                   //~9227R~
            }                                                      //~0301I~
            skipEswn=replyEswn;                                   //~9227I~
        }                                                          //~9221R~
        if (AG.aAccounts.isServer())                               //~9221M~
        {                                                          //~9221I~
	        int idxAccount=AG.aAccounts.currentEswnToMember(skipEswn);           //~9221R~//~9227I~
			AG.aAccounts.sendToClient(false/*PswRobot*/,idxAccount/*Pskip*/,PmsgID,PmsgData);//~9221M~
        }                                                          //~9221I~
    }                                                              //~9221I~
    //*******************************************************************//~9221I~
    private void sendReply(boolean PswOK)                    //~9221I~//~9227R~
    {                                                              //~9221I~
        if (Dump.Y) Dump.println("CompReqDlg.sendReply OK="+PswOK);//~9221R~
        String msg=completeEswn+MSG_SEPAPP+currentEswn+MSG_SEPAPP+(PswOK ? "1" : "0");//~9221R~
        ACC.sendToAll(GCM_COMPDLG_RESP,msg);                       //~9221I~
    }                                                              //~9221I~
//    //*******************************************************      //~9222I~//~9306R~
//    private static void repaint(Complete.Status Pstat)                    //~9222I~//~9306R~
//    {                                                              //~9222I~//~9306R~
//        CompReqDlg dlg=Pstat.compReqDlg;                            //~9227I~//~9306R~
//        if (dlg!=null)                                             //~9227I~//~9306R~
//            new EventCB(ECB_COMPDLG_RESP,EventCB.newBundle(Pstat.completeEswn)).postEvent();//~9222I~//~9227R~//~9306R~
//        if (Dump.Y) Dump.println("CompReqDlg.repaint dlg=null ? "+(dlg==null));//~9227I~//~9306R~
//    }                                                              //~9222I~//~9306R~
//    //*******************************************************      //~9222I~//~9306R~
//    //*from MainActivity on UIThread                               //~9222I~//~9306R~
//    //*******************************************************      //~9222I~//~9306R~
//    public static void repaintUI(EventCB PeventCB)                 //~9222R~//~9306R~
//    {                                                              //~9222I~//~9306R~
//        try                                                        //~9222I~//~9306R~
//        {                                                          //~9222I~//~9306R~
//            int eswn=PeventCB.getParmInt1();                       //~9222R~//~9306R~
//            if (Dump.Y) Dump.println("CompReqDlg.repaintUI eswn="+eswn);//~9222R~//~9306R~
//            Complete.Status stat=AG.aComplete.getStatus(eswn);     //~9222R~//~9306R~
//            CompReqDlg dlg=stat.compReqDlg;                        //~9222R~//~9306R~
//            if (dlg!=null)                                         //~9222R~//~9306R~
//                dlg.setReplyText();                                //~9222R~//~9306R~
//        }                                                          //~9222I~//~9306R~
//        catch(Exception e)                                         //~9222I~//~9306R~
//        {                                                          //~9222I~//~9306R~
//            Dump.println(e,"CompReqDlg:repaintUI");               //~9222I~//~9227R~//~9306R~
//        }                                                          //~9222I~//~9306R~
//    }                                                              //~9222I~//~9306R~
    //*******************************************************      //~9306I~
    public void repaint(Complete.Status Pstat)                     //~9306I~
    {                                                              //~9306I~
        if (Dump.Y) Dump.println("CompReqDlg.repaint compEswn="+Pstat.completeEswn);//~9306I~
        repaintEswn=Pstat.completeEswn;                            //~9306I~
        AG.activity.runOnUiThread(                                 //~9306I~
            new Runnable()                                         //~9306I~
            {                                                      //~9306I~
                @Override                                          //~9306I~
                public void run()                                  //~9306I~
                {                                                  //~9306I~
                    try                                            //~9306I~
                    {                                              //~9306I~
//                      int eswn=PeventCB.getParmInt1();           //~9306I~
                        int eswn=repaintEswn;                      //~9306I~
                        if (Dump.Y) Dump.println("CompReqDlg.runOnUiThread.run eswn="+eswn);//~9306I~
                        Complete.Status stat=AG.aComplete.getStatus(eswn);//~9306I~
                        CompReqDlg dlg=stat.compReqDlg;            //~9306I~
                        if (dlg!=null)                             //~9306I~
                            dlg.setReplyText();                    //~9306I~
                    }                                              //~9306I~
                    catch(Exception e)                             //~9306I~
                    {                                              //~9306I~
                        Dump.println(e,"DrawnDlgHW:repaint.run");  //~9306I~
                    }                                              //~9306I~
                }                                                  //~9306I~
            }                                                      //~9306I~
                                  );                               //~9306I~
                                                                   //~9306I~
    }                                                              //~9306I~
    //*******************************************************************//~9221I~
    public  void setReplyText()                                    //~9221I~
    {                                                              //~9221I~
        if (Dump.Y) Dump.println("CompReqDlg.setReplyText");       //~9221I~
        compStat.chkOK();                                          //~9221I~
        int[] intsReply = compStat.replyOK;                          //~9221I~
        for (int ii = 0; ii < PLAYERS; ii++)                             //~9221I~
        {                                                          //~9221I~
            int reply = intsReply[ii];                               //~9221I~
            int color=colorReply[reply];                                             //~9221I~//~9227R~
//            switch (reply)                                          //~9221I~//~9227R~
//            {                                                      //~9221I~//~9227R~
//                case COMPREPLY_OK:                                    //~9221I~//~9227R~
//                    color = COLOR_REPLY_OK;                              //~9221I~//~9227R~
//                    break;                                              //~9221I~//~9227R~
//                case COMPREPLY_NG:                                    //~9221I~//~9227R~
//                    color = COLOR_REPLY_NG;                              //~9221I~//~9227R~
//                    break;                                              //~9221I~//~9227R~
//                case COMPREPLY_NORESP:                                //~9221I~//~9227R~
//                    color = COLOR_REPLY_NORESP;                          //~9221I~//~9227R~
//                    break;                                              //~9221I~//~9227R~
//                case COMPREPLY_BEFORESENDP:                      //~9227I~
//                    color = COLOR_REPLY_BEFORESEND;              //~9227I~
//                    break;                                       //~9227I~
//                default:                                               //~9221I~//~9227R~
//                    color = COLOR_REPLY_OTHER;                           //~9221I~//~9227R~
//            }                                                      //~9221I~//~9227R~
            tvsReply[ii].setBackgroundColor(color);                    //~9221I~
            String s = replayText[reply];                            //~9221I~
            tvsReply[ii].setText(s);                               //~9221I~
        }
        tvsReplyLabel[currentEswn].setBackgroundColor(COLOR_YOU);  //~9311I~
    }//~9221I~
    //*******************************************************************//~9224I~
    public static boolean isRank_YM(int PidxRank)                  //~9224I~
    {                                                              //~9224I~
    	boolean rc=intsRank[PidxRank]>=POINT_RANK_YM;              //~9224I~
        if (Dump.Y) Dump.println("CompReqDlg.isRankYM idx="+PidxRank+",rc="+rc);//~9224I~
        return rc;
    }                                                              //~9221I~
	//************************************************             //~9417I~
    private void showRule()                                        //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("CompReqDlg.showRule");           //~9417I~
        RuleSetting.showRuleInGame();                              //~9417I~
    }                                                              //~9417I~
    //*******************************************************************//~9403I~
    public static boolean showDismissed()                             //~9403I~//~9903R~
    {                                                              //~9403I~
        if (Dump.Y) Dump.println("CompReqDlg.showDismissed");      //~9403I~
        boolean rc=true;                                           //~9903I~
        if (showComplete()==0)                                      //~9403R~//~9410R~
        {                                                          //~9903I~
//          UView.showToast(R.string.Err_NoneCompleted);           //~9403I~//~0106R~
            rc=false;                                              //~9903I~
        }                                                          //~9903I~
        if (Dump.Y) Dump.println("CompReqDlg.showDismissed rc="+rc);//~9903I~
        return rc;                                                 //~9903I~
    }                                                              //~9403I~
    //*******************************************************************//~9403I~
    private static int showComplete()                                      //~9403R~//~9410R~
    {                                                              //~9403I~
//        Complete.Status ss[]=AG.aComplete.statusS;               //~9403I~
//        int ctr=0;                                               //~9403I~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~9403I~
//        {                                                        //~9403I~
//            if (ss[ii]==null)                                    //~9403I~
//                 continue;                                       //~9403I~
//            if (PswReqDlg)                                       //~9403I~
//            {                                                    //~9403I~
//                if (ss[ii].isShowable())                         //~9403I~
//                    CompReqDlg.newInstance(ss[ii]).show();       //~9403I~
//                else                                             //~9403I~
//                    UView.showToast(R.string.Err_CompReqNotShowable);//~9403I~
//            }                                                    //~9403I~
//            ctr++;                                               //~9403I~
//        }                                                        //~9403I~
		if (Status.isGameOver())                                   //~9606I~
        	return 0;                                              //~9606I~
		if (AG.aComplete==null)                                    //~9410I~
        	return 0;                                              //~9410I~
		if (AG.aAccounts==null)                                    //~9410I~
        	return 0;                                              //~9410I~
		if (AG.aComplete.isTotalAgreed())                          //~9612I~
        {                                                          //~9612I~
            UView.showToast(R.string.Err_TotalAgreedAlready);      //~9612I~
        	return -1;                                             //~9612R~
        }                                                          //~9612I~
        Complete.Status ss[]=AG.aComplete.sortStatusS();           //~9403I~
        int ctr;                                                   //~9403I~
        if (ss==null)                                              //~9403I~
            ctr=0;                                                 //~9403I~
        else                                                       //~9403I~
            ctr=ss.length;                                         //~9403R~
        if (ctr==0)                                                //~0106I~
        {                                                          //~0106I~
            UView.showToast(R.string.Err_NoneCompleted);           //~0106I~
        	return 0;                                              //~0106I~
        }                                                          //~0106I~
        int curEswn=AG.aAccounts.getCurrentEswn();                 //~9410I~
        int rc=0;                                                  //~9903I~
        boolean swYou=false;                                       //~0106I~
        for (int ii=0;ii<ctr;ii++)                                 //~9403I~
        {                                                          //~9403I~
            if (ss[ii].completeEswn==curEswn)                      //~0106I~
                swYou=true;                                        //~0106I~
//          if (ss[ii].isShowable())                               //~9403I~//~9410R~
            if (ss[ii].isShowable() && ss[ii].completeEswn==curEswn)//~9410I~
            {                                                      //~9903I~
                CompReqDlg.newInstance(ss[ii]).show(ii);          //~9403R~//~9408R~
                rc++;                                              //~9903I~
            }                                                      //~9903I~
            else                                                   //~9403I~
            if (ss[ii].completeEswn==curEswn)                      //~9B11I~
                UView.showToast(R.string.Err_CompReqRequestResend);  //~9403I~//~9603R~
//            else    //for multiron                                 //~9B11I~//~0106R~
//            if (!(ss[ii].swInvalid || ss[ii].swErr))               //~9B11I~//~0106R~
//                UView.showToast(R.string.Err_CompReqMultipleRon);     //~9B11I~//~0106R~
        }                                                          //~9403I~
        if (!swYou)                                                //~0106I~
        {                                                          //~0106I~
        	UView.showToast(R.string.Err_YouAreNotCompleted);      //~0106I~
            return 0;                                              //~0106I~
        }                                                          //~0106I~
        for (int ii=0;ii<ctr;ii++)                                 //~0106I~
        {                                                          //~0106I~
//            if (ss[ii].isShowable() && ss[ii].completeEswn==curEswn)//~0106I~
//            {                                                    //~0106I~
//                CompReqDlg.newInstance(ss[ii]).show(ii);         //~0106I~
//                rc++;                                            //~0106I~
//            }                                                    //~0106I~
//            else                                                 //~0106I~
//            if (ss[ii].completeEswn==curEswn)                    //~0106I~
//                UView.showToast(R.string.Err_CompReqRequestResend);//~0106I~
//            else    //for multiron                               //~0106I~
            if (ss[ii].completeEswn==curEswn)                      //~0106I~
            	continue;                                          //~0106I~
            if (!(ss[ii].swInvalid || ss[ii].swErr))               //~0106I~
                UView.showToast(R.string.Err_CompReqMultipleRon);  //~0106I~
                                                                   //~0106I~
        }                                                          //~0106I~
		if (Dump.Y) Dump.println("CompReqDlg.shwComplete ctr="+ctr+",rc="+rc);//~9403I~//~9903R~//~0106R~
        return rc;                                                //~9403I~//~9903R~
    }                                                              //~9403I~
    //*************************************************************************//~0329I~
    //*on Server send GCM_OPEN to reacher                          //~0329I~
    //*************************************************************************//~0329I~
    private void sendOpen()                                        //~0329I~
    {                                                              //~0329I~
    	boolean swReach=AG.aPlayers.getPosReach(PLAYER_YOU)>=0;    //~0329I~
        if (Dump.Y) Dump.println("CompReqDlg.sendOpen swReach="+swReach);//~0329I~
        if (swReach)                                               //~0329I~
        {                                                          //~0329I~
        	if (AG.aAccounts.isServer())                           //~0329I~
	        	GameViewHandler.sendMsg(GCM_OPEN,PLAYER_YOU,OPT_OPEN_ONLY_REACH,0);//~0329R~
            else                                                   //~0329I~
        		AG.aUserAction.sendToServer(GCM_OPEN,PLAYER_YOU,OPT_OPEN_ONLY_REACH,0/*p2*/,0/*p3*/);//~0329I~
        }                                                          //~0329I~
    }                                                              //~0329I~
}//class                                                           //~v@@@R~
