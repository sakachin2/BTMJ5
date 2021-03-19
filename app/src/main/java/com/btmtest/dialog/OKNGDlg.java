//*CID://+va66R~:                             update#=  786;       //~va66R~
//*****************************************************************//~v101I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.app.Dialog;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.btmtest.StaticVars.AG;                           //~9305I~
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.GConst;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.utils.Alert.*;

public class OKNGDlg extends UFDlg implements Alert.AlertI                                 //~9314R~//~9608R~
{                                                                  //~2C29R~
    protected static final int COLOR_RESPNONE=Color.argb(0xff,0xff,0xff,0x00);//yellow//~9305I~//~9828R~
    protected static final int COLOR_RESPOK=Color.argb(0xff,0x00,0xff,0x00);  //green//~9305I~//~9828R~
    protected static final int COLOR_RESPNG=Color.argb(0xff,0xff,0x66,0x00);//orange//~9305I~//~9828R~
    protected static final int COLOR_RESPBOT=Color.argb(0xff,0xc0,0xc0,0xc0);//~9228I~//~9305I~//~9828R~
    protected static final int COLOR_YOU=Color.argb(0xff,0x00,0xbf,0xff); //deep sky blue//~9305I~//~9828R~
                                                                   //~9315I~
    protected static final String strOK=Utils.getStr(R.string.OK);                           //~9305I~//~9314M~//~9315I~
    protected static final String strNG=Utils.getStr(R.string.NG);                           //~9305I~//~9314M~//~9315I~
    protected static final String strNoReply=Utils.getStr(R.string.NoReply);                //~9305I~//~9314M~//~9315I~
    protected static final String strNotConnected=Utils.getStr(R.string.Disconnected);//~9A21R~
    protected static final String strBot=Utils.getStr(R.string.YournameRobot);               //~9305I~//~9314M~//~9315I~
    protected static final String strDealer=Utils.getStr(R.string.Label_DealerNow);          //~9311I~//~9314M~//~9315I~
    protected static final String strSend=Utils.getStr(R.string.Send);                       //~9306I~//~9314M~//~9315I~
                                                                   //~9214I~
    protected URadioGroup rgNextGame;                           //~9303R~//~9305R~//~9314R~
    protected int currentEswn;                                     //~9315R~
    protected int[] respStat=new int[PLAYERS];                     //~9314R~
    protected TextView[] tvsResp;                                  //~9305R~//~9314R~
    protected TextView[] tvsRespESWN;                              //~9305I~//~9314R~
    protected View layoutView;                                     //~9314I~
    protected Dialog androidDlg;                                   //~9314I~
    protected boolean swRequester;                                 //~9314I~
    protected boolean swAllOK;                                     //~9316I~
    protected int eswnRequester;                                   //~9315I~
    protected boolean isServer;                                    //~9321I~
    public boolean swByPosition;                                   //~9606I~
    private boolean swIgnoreAlertResponse;                         //~9608I~
    public OKNGDlg dlgForAlert;                                    //~9608I~
    protected int ctrNG,ctrNone;
    protected int ctrDisconnected;                                 //~9A21I~
    protected LinearLayout llEswnResponse;//~9608I~                //~9828R~
    private boolean swInitLayout;                                  //~9813I~
    protected int serverEswn;                                      //~9A31I~
    private boolean swHideResponseEswn;                            //~0217I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public OKNGDlg()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9314R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("OKNGDlg.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9314R~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@M~//~9303R~//~9314R~
    @Override                                                      //~9221I~//~9303R~//~9314R~
    public void onPause()                                          //~9221I~//~9303R~//~9314R~
    {                                                              //~9221I~//~9303R~//~9314R~
        super.onPause();                                         //~9302R~//~9303R~//~9314R~
        if (Dump.Y) Dump.println("OKNGDlg:onPause issue dismiss");//~9221R~//~9302R~//~9303R~//~9304R~//~9314R~//~9316R~
        dismiss();  //because hard to make Complete.Status.ammount to parcelable//~9221I~//~9302R~//~9303R~//~9314R~
    }                                                              //~9221I~//~9303R~//~9314R~
    //******************************************                 //~9303R~//~9314R~
    @Override                                                    //~9303R~//~9305R~
    protected void initLayout(View PView)                            //~v@@@I~//~9303R~//~9305R~
    {                                                              //~v@@@M~//~9303R~//~9305R~
        if (Dump.Y) Dump.println("OKNGDlg.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~//~9304R~//~9305R~//~9314R~
        swInitLayout=true;                                         //~9813I~
        layoutView=PView;                                          //~9314I~
        super.initLayout(PView);                                   //~9302R~//~9303R~//~9305R~
        setupValueOKNG();                                     //~9314I~
        setupValueAdditional(PView);                               //~9314I~
        initLayoutOKNG(PView);                                      //~9305M~//~9307R~//~9314I~
        initLayoutAdditional(PView);                                //~9314I~
        updateOKNG();                                                  //~9305M~//~9314I~
        updateAdditional(PView);                                   //~9314I~
        setButton();                                               //~9314I~
        swInitLayout=false;                                        //~9813I~
    }                                                              //~v@@@M~//~9303R~//~9305R~
    //******************************************                   //~9314I~
    //*Override this                                               //~9314I~
    //******************************************                   //~9314I~
    protected void initLayoutAdditional(View PView)                //~9314I~
    {                                                              //~9314I~
        if (Dump.Y) Dump.println("OKNGDlg.initLayoutAdditional");  //~9314I~
    }                                                              //~9314I~
//    //******************************************                 //~9818R~
//    @Override //UFDlg                                            //~9818R~
//    protected int getDialogWidth()                               //~9818R~
//    {                                                            //~9818R~
//        if (Dump.Y) Dump.println("OKNGDlg.getDialogWidth nop");  //~9818R~
//    }                                                            //~9818R~
    //******************************************                   //~9314I~
    //*Override this                                               //~9314I~
    //******************************************                   //~9314I~
    protected void setupValueAdditional(View PView)                //~9314I~
    {                                                              //~9314I~
        if (Dump.Y) Dump.println("OKNGDlg.setupValueAdditional");  //~9314R~
    }                                                              //~9314I~
    //******************************************                   //~9314I~
    //*Override this                                               //~9314I~
    //******************************************                   //~9314I~
    protected void updateAdditional(View Pview)                    //~9314I~
    {                                                              //~9314I~
        if (Dump.Y) Dump.println("OKNGDlg.updateAdditional");      //~9314I~
    }                                                              //~9314I~
    //******************************************                   //~9302I~//~9303R~//~9304R~//~9314M~
    protected void setupValueOKNG()                                      //~9302I~//~9303R~//~9304R~//~9314I~
    {                                                              //~9302I~//~9303R~//~9304R~//~9314M~
        if (Dump.Y) Dump.println("OKNGDlg.setupValueOKNG");//~9304I~//~9314I~
        androidDlg=getDialog();                                    //~9314M~
    	currentEswn=Accounts.getCurrentEswn();                     //~9305I~//~9314M~
        serverEswn=AG.aAccounts.getCurrentServerEswn();            //~9A31I~
        eswnRequester=getEswnRequester(currentEswn);    //~9314I~  //~9315R~
        swRequester=currentEswn==eswnRequester;                    //~9315I~
        isServer=AG.aAccounts.isServer();                          //~9321I~
        llEswnResponse=(LinearLayout)UView.findViewById(layoutView,R.id.eswnResponseLine);//~9813I~
    }                                                              //~9302I~//~9303R~//~9304R~//~9314M~
    //*********************************************************                   //~9305I~//~9314R~
    //*override this to change response requester                 //~9314I~//~9321R~
    //*********************************************************    //~9314I~
    protected int getEswnRequester(int PcurrentEswn)               //~9314I~
    {                                                              //~9314I~
        int rc=AG.aAccounts.getCurrentDealerRealEswn();	//current dealerEswn//~9314I~//~9315R~
        if (Dump.Y) Dump.println("OKNGDlg.getEswnRequester rc="+rc);//~9314I~
        return rc;                                                 //~9314I~
    }                                                              //~9314I~
    //**************************************************************************//~9321I~
    public static boolean isDealer()                            //~9321I~//~0217R~
    {                                                              //~9321I~
    	int currEswn=Accounts.getCurrentEswn();                    //~9321I~
        int dealerEswn=AG.aAccounts.getCurrentDealerRealEswn();	//current dealerEswn//~9321I~
        boolean rc=currEswn==dealerEswn;                           //~9321I~
        if (Dump.Y) Dump.println("OKNGDlg.isDealer rc="+rc+",currEswn="+currEswn+",dealerEswn="+dealerEswn);       //~9321I~//~9401R~
        return rc;                                                 //~9321I~
    }                                                              //~9321I~
    //**************************************************************************//~0217I~
    //*from initLayoutAdditional                                   //~0217I~
    //**************************************************************************//~0217I~
    protected void hideResponseEswn(boolean PswHide)               //~0217R~
    {                                                              //~0217I~
        if (Dump.Y) Dump.println("OKNGDlg.hideEswnResponse sw="+PswHide);//~0217I~
    	swHideResponseEswn=PswHide;                                //~0217I~
        if (PswHide)                                               //~0217I~
    	    llEswnResponse.setVisibility(View.GONE);               //~0217R~
    }                                                              //~0217I~
    //******************************************                   //~9314I~
    protected void initLayoutOKNG(View PView)                                  //~9305I~//~9307R~//~9314R~
    {                                                              //~9305I~
      	TextView tvResp1,tvResp2,tvResp3,tvResp4;                  //~9305I~
      	TextView tvRespE,tvRespS,tvRespW,tvRespN;                  //~9305I~
        if (Dump.Y) Dump.println("OKNGDlg.setupTextView");      //~9305I~//~9314R~
        tvResp1         =(TextView)    UView.findViewById(PView,R.id.ReplyE);//~9305I~
        tvResp2         =(TextView)    UView.findViewById(PView,R.id.ReplyS);//~9305R~
        tvResp3         =(TextView)    UView.findViewById(PView,R.id.ReplyW);//~9305I~
        tvResp4         =(TextView)    UView.findViewById(PView,R.id.ReplyN);//~9305I~
        tvRespE         =(TextView)    UView.findViewById(PView,R.id.LabelReplyE);//~9305I~
        tvRespS         =(TextView)    UView.findViewById(PView,R.id.LabelReplyS);//~9305I~
        tvRespW         =(TextView)    UView.findViewById(PView,R.id.LabelReplyW);//~9305I~
        tvRespN         =(TextView)    UView.findViewById(PView,R.id.LabelReplyN);//~9305I~
        tvsResp=new TextView[]{tvResp1,tvResp2,tvResp3,tvResp4};   //~9305I~
        tvsRespESWN=new TextView[]{tvRespE,tvRespS,tvRespW,tvRespN};//~9305I~
    }                                                              //~9305I~
    //*******************************************************      //~9305I~
    public void updateOKNG()                                           //~9305R~//~9314R~
    {                                                              //~9305I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNG swHideResponseEswn="+swHideResponseEswn);//~0217I~
    	if (swHideResponseEswn)                                    //~0217I~
        	return;                                                //~0217I~
        int llrespW=llEswnResponse.getWidth();                     //~9813I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNG llRespWidth="+llrespW+",H="+llEswnResponse.getHeight());//~9813I~
        LinearLayout.LayoutParams llp=(LinearLayout.LayoutParams)llEswnResponse.getLayoutParams();//~9813I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNG llp W="+llp.width+",H="+llp.height);//~9813I~
        if (!swInitLayout && llrespW>0)                            //~9813I~
        {                                                          //~9813I~
        	llp.width=llrespW;                                     //~9813R~
      		llEswnResponse.setLayoutParams(llp);                   //~9813R~
        }                                                          //~9813I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNG currentEswn="+currentEswn+",respStat="+Arrays.toString(respStat));//~9305I~//~9314R~//~9A31R~
        if (swByPosition)                                          //~9606I~
        {                                                          //~9606I~
    		updateOKNGByPosition();                                //~9606I~
            return;                                                //~9606I~
        }                                                          //~9606I~
        String stat="";                                               //~9305I~
        int color;                                                 //~9305I~
        ctrNone=0; ctrNG=0;                                     //~9316I~//~9608R~
        ctrDisconnected=0;                                         //~9A21I~
//      boolean swNoConnectionToServer=!isServer && !isConnected(ESWN_E);//~9A31I~//~9B10R~
        boolean swNoConnectionToServer=!isServer && !isConnected(serverEswn);//~9B10I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNG serverEswn="+serverEswn+",swNoConnectionToServer="+swNoConnectionToServer);//~9B10I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9305I~
        {                                                          //~9305I~
        	color=0;                                               //~9A31I~
        	if (AG.aAccounts.isDummyByCurrentEswn(ii))             //~9305I~
            {                                                      //~9305I~
            	stat=strBot;                                       //~9305I~
        		color=COLOR_RESPBOT;                               //~9305R~
            }                                                      //~9305I~
            else                                                   //~9A31I~
            if (!isConnected(ii/*eswn*/))                          //~9A31I~
            {                                                      //~9A31I~
            	if ((isServer && ii!=currentEswn)                  //~9A31I~
//              ||  (swNoConnectionToServer &&  (ii==serverEswn||ii==currentEswn))//~9A31I~//~0219R~
                ||  (swNoConnectionToServer &&  ii==serverEswn)    //~0219I~
                )                                                  //~9A31I~
                {                                                  //~9A31I~
			        if (Dump.Y) Dump.println("OKNGDlg.updateOKNG ii="+ii+",isServer="+isServer+",currentEswn="+currentEswn);//~9B10I~
                    stat=strNotConnected;                          //~9A31I~
                    ctrDisconnected++;                             //~9A31I~
	            	color=COLOR_RESPNG;                            //~9B01I~
                }                                                  //~9A31I~
            }                                                      //~9A31I~
            if (color!=0)                                          //~9A31I~
                ;                                                  //~9A31I~
            else                                                   //~9A31I~
        	if (ii==eswnRequester)  //~9311I~                      //~9314R~//~9315R~
            {                                                      //~9311I~
                stat=strDealer;                                    //~9311I~
                color=COLOR_RESPBOT;                               //~9311I~
            }                                                      //~9311I~
            else                                                   //~9311I~
            if (respStat[ii]==EGDR_NG) //no good                   //~9305R~
            {                                                      //~9305I~
            	stat=strNG;                                        //~9305I~
            	color=COLOR_RESPNG;                                //~9305I~
                ctrNG++;                                           //~9316I~
            }                                                      //~9305I~
            else                                                   //~9305I~
            if (respStat[ii]!=0)                                   //~9305R~
            {                                                      //~9305I~
            	stat=strOK;                                        //~9305I~
            	color=COLOR_RESPOK;                                //~9305I~
            }                                                      //~9305I~
            else                                                   //~9305I~
            {                                                      //~9305I~
//              if (!isConnected(ii/*eswn*/))                      //~9A21R~//~9A31R~
//              {                                                  //~9A21I~//~9A31R~
//      	    	stat=strNotConnected;                          //~9A21I~//~9A31R~
//                  ctrDisconnected++;                             //~9A21I~//~9A31R~
//              }                                                  //~9A21I~//~9A31R~
//  	        else                                               //~9A21I~//~9A31R~
//              {                                                  //~9A21I~//~9A31R~
        	    	stat=strNoReply;                                   //~9305I~//~9A21R~
	                ctrNone++;                                         //~9316I~//~9A21I~
//              }                                                  //~9A21I~//~9A31R~
        		color=COLOR_RESPNONE;                              //~9305I~
            }                                                      //~9305I~
	        if (Dump.Y) Dump.println("OKNGDlg.update ii="+ii+",stat="+stat+",resp="+respStat[ii]);//~9305I~//~9314R~//~9A31R~
            tvsResp[ii].setText(stat);                             //~9305I~
            tvsResp[ii].setBackgroundColor(color);                 //~9305I~
        }                                                          //~9305I~
        tvsRespESWN[currentEswn].setBackgroundColor(COLOR_YOU);         //~9305I~
        swAllOK=ctrNone==0 && ctrNG==0;                             //~9316I~
        updateOKNGAdditional(ctrNone,ctrNG,ctrDisconnected,swAllOK);//~0119R~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNG ctrNG="+ctrNG+",ctrNone="+ctrNone+",ctrDisconnected="+ctrDisconnected+",swAllOK="+swAllOK);//~9316I~//~9A21R~
    }                                                              //~9305I~
    //***********************************************************************************//~9606I~
    //*response is by current Eswn, arrange it by initial position //~9606I~
    //***********************************************************************************//~9606I~
    public void updateOKNGByPosition()                             //~9606I~
    {                                                              //~9606I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNGByPosition swHideResponseEswn="+swHideResponseEswn);//~0217I~
    	if (swHideResponseEswn)                                    //~0217I~
        	return;                                                //~0217I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNGByPosition respStat="+Arrays.toString(respStat));//~9606I~
        String stat="";                                               //~9606I~
        int color;                                                 //~9606I~
        int ctrNone=0,ctrNG=0;                                     //~9606I~
        int ctrDisconnected=0;                                     //~9A21I~
        int requester=AG.aAccounts.getFirstDealerPosReal();  //@@@@add diff from updateOKNG      //~9606R~//~9B10R~
//      boolean swNoConnectionToServer=!isServer && !isConnected(ESWN_E);//~9A31I~//~9B10R~
        boolean swNoConnectionToServer=!isServer && !isConnected(serverEswn);//~9B10I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNGByPosition serverEswn="+serverEswn+",swNoConnectionToServer="+swNoConnectionToServer);//~9B10I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9606I~
        {                                                          //~9606I~
        	int pos=AG.aAccounts.currentEswnToPosition(ii);  //@@@@add                   //~9606I~//~9B10R~
        	color=0;                                               //~9A31I~
        	if (AG.aAccounts.isDummyByCurrentEswn(ii))             //~9606I~
            {                                                      //~9606I~
            	stat=strBot;                                       //~9606I~
        		color=COLOR_RESPBOT;                               //~9606I~
            }                                                      //~9606I~
            else                                                   //~9606I~
            if (!isConnected(ii/*eswn*/))                          //~9A31R~
            {                                                      //~9A31I~
            	if ((isServer && ii!=currentEswn)                  //~9A31I~
//              ||  (swNoConnectionToServer &&  (ii==serverEswn||ii==currentEswn))//~9A31R~//~0219R~
                ||  (swNoConnectionToServer &&  ii==serverEswn)    //~0219I~
                )                                                  //~9A31I~
                {                                                  //~9A31I~
			        if (Dump.Y) Dump.println("OKNGDlg.updateOKNGByPosition ii="+ii+",isServer="+isServer+",currentEswn="+currentEswn);//~9B10I~
                    stat=strNotConnected;                          //~9A31I~
                    ctrDisconnected++;                             //~9A31I~
	            	color=COLOR_RESPNG;                            //~9B01I~
                }                                                  //~9A31I~
            }                                                      //~9A31I~
            if (color!=0)                                          //~9A31I~
            	;                                                  //~9A31R~
            else                                                   //~9A31I~
//      	if (ii==eswnRequester)   //@@@@del                              //~9606I~//~9B10R~
        	if (pos==requester)      //@@@@add                              //~9606R~//~9B10R~
            {                                                      //~9606I~
                stat=strDealer;                                    //~9606I~
                color=COLOR_RESPBOT;                               //~9606I~
            }                                                      //~9606I~
            else                                                   //~9606I~
            if (respStat[ii]==EGDR_NG) //no good                   //~9606I~
            {                                                      //~9606I~
            	stat=strNG;                                        //~9606I~
            	color=COLOR_RESPNG;                                //~9606I~
                ctrNG++;                                           //~9606I~
            }                                                      //~9606I~
            else                                                   //~9606I~
            if (respStat[ii]!=0)                                   //~9606I~
            {                                                      //~9606I~
            	stat=strOK;                                        //~9606I~
            	color=COLOR_RESPOK;                                //~9606I~
            }                                                      //~9606I~
            else                                                   //~9606I~
            {                                                      //~9606I~
//              if (!isConnected(ii/*eswn*/))                      //~9A21R~//~9A31R~
//              {                                                  //~9A21I~//~9A31R~
//      	    	stat=strNotConnected;                          //~9A21I~//~9A31R~
//                  ctrDisconnected++;                             //~9A21I~//~9A31R~
//              }                                                  //~9A21I~//~9A31R~
//  	        else                                               //~9A21I~//~9A31R~
//              {                                                  //~9A21I~//~9A31R~
	            	stat=strNoReply;                                   //~9606I~//~9A21R~
	                ctrNone++;                                         //~9606I~//~9A21I~
//              }                                                  //~9A21I~//~9A31R~
        		color=COLOR_RESPNONE;                              //~9606I~
            }                                                      //~9606I~
	        if (Dump.Y) Dump.println("OKNGDlg.updateOKNGByPosition currentEswn="+ii+",startingPos="+pos+",stat="+stat+",resp="+respStat[ii]);//~9606R~
//          tvsResp[ii].setText(stat);                //@@@@del    //~9B10R~
//          tvsResp[ii].setBackgroundColor(color);    //@@@@del    //~9B10R~
            tvsResp[pos].setText(stat);               //@@@@add             //~9606I~//~9B10R~
            tvsResp[pos].setBackgroundColor(color);   //@@@@add             //~9606I~//~9B10R~
        }                                                          //~9606I~
//      tvsRespESWN[currentEswn].setBackgroundColor(COLOR_YOU); //@@@@del   //~9606I~//~9B10R~
        tvsRespESWN[AG.aAccounts.yourESWN].setBackgroundColor(COLOR_YOU); //@@@@add//~9606I~//~9B10R~
        swAllOK=ctrNone==0 && ctrNG==0;                            //~9606I~
        updateOKNGAdditional(ctrNone,ctrNG,ctrDisconnected,swAllOK);       //~0119R~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNGByPosition ctrNG="+ctrNG+",ctrNone="+ctrNone+",ctrDisconnected="+ctrDisconnected+",swAllOK="+swAllOK+",yourEswn="+AG.aAccounts.yourESWN);//~9606R~//~9A21R~
    }                                                              //~9606I~
    //*******************************************************      //~9A21I~
	private boolean isConnected(int Peswn/*currentEswn*/)          //~9A21I~
    {                                                              //~9A21I~
    	int idxAccount=AG.aAccounts.currentEswnToMember(Peswn);                 //~9A21I~
        if (Dump.Y) Dump.println("OKNGDlg.isConnected Peswn="+Peswn+",idxAccount="+idxAccount);//~9A21I~//~9A31R~
        boolean rc=AG.aAccounts.accounts[idxAccount].memberData.getThread()!=null;//~9A21I~
        if (Dump.Y) Dump.println("OKNGDlg.isConnected rc="+rc+",account="+AG.aAccounts.accounts[idxAccount].toString());//~9A21I~
        return rc;                                                 //~9A21I~
    }                                                              //~9A21I~
    //*******************************************************      //~9316I~
    //*Will be Overridden                                          //~9316I~
    //*******************************************************      //~9316I~
//    protected void updateOKNGAdditional(int PctrNone,int PctrNG,boolean PswAllOK)//~9316I~//~0119R~
//    {                                                              //~9316I~//~0119R~
//        if (Dump.Y) Dump.println("OKNGDlg.updateOKNGAdditional ctrNG="+PctrNG+",ctrNone="+PctrNone+",swAllOK="+PswAllOK);//~9316I~//~0119R~
//    }                                                              //~9316I~//~0119R~
    protected void updateOKNGAdditional(int PctrNone,int PctrNG,int ctrDisconnected,boolean PswAllOK)//~0119I~
    {                                                              //~0119I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNGAdditional ctrNG="+PctrNG+",ctrNone="+PctrNone+",ctrDisconnected="+ctrDisconnected+",swAllOK="+PswAllOK);//~0119I~
    }                                                              //~0119I~
    //*******************************************************      //~9314I~
    public void setButton()                                        //~9314I~
    {                                                              //~9314I~
        if (AG.swTrainingMode)                                 //~1207I~//~va66I~
        {                                                          //~va66I~
            btnOK.setVisibility(View.GONE);                        //~va66I~
            btnCancel.setText(GConst.strCancel);                   //+va66I~
        }                                                          //~va66I~
        else                                                       //~va66I~
        if (swRequester)                                           //~9314I~
        {                                                          //~9314I~
            btnOK.setText(strSend);                                //~9314I~
            btnCancel.setVisibility(View.GONE);                    //~9314I~
        }                                                          //~9314I~
        else                                                       //~9314I~
        {                                                          //~9314I~
            btnOK.setText(strOK);                                  //~9314I~
        }                                                          //~9314I~
        if (Dump.Y) Dump.println("OKNGDlg.setButton swRequester="+swRequester+",swTrainingMode="+AG.swTrainingMode);//~9315I~//~va66R~
    }                                                              //~9314I~
    //*******************************************************      //~9314I~
    public void repaintOKNG(int PeswnSender,boolean PswOK)         //~9314I~
    {                                                              //~9314I~
    	respStat[PeswnSender]=PswOK?EGDR_OK:EGDR_NG;            //~9314I~
	    repaintOKNG(respStat);                                     //~9314I~
    }                                                              //~9314I~
    //*******************************************************      //~0119I~
    public void resetRespStat()                                    //~0119I~
    {                                                              //~0119I~
        if (Dump.Y) Dump.println("OKNGDlg.resetRespStat old="+Arrays.toString(respStat));//~0119I~
    	Arrays.fill(respStat,EGDR_NONE);                          //~0119I~
    }                                                              //~0119I~
    //*******************************************************      //~0119I~
    public void repaintOKNG()                                      //~0119I~
    {                                                              //~0119I~
       	if (Dump.Y) Dump.println("OKNGDlg.repaintOKNG no parm");   //~0119I~
 	   	repaintOKNG(respStat);                                     //~0119I~
    }                                                              //~0119I~
    //*******************************************************      //~9305I~
    public void repaintOKNG(int[] PrespStat)         //~9305R~     //~9314R~
    {                                                              //~9305I~
        if (Dump.Y) Dump.println("OKNGDlg.repaint respstat="+Arrays.toString(PrespStat));//~9305I~//~9314R~
        respStat=PrespStat;                                        //~9305I~
        AG.activity.runOnUiThread(                                 //~9305I~
            new Runnable()                                         //~9305I~
            {                                                      //~9305I~
                @Override                                          //~9305I~
                public void run()                                       //~9305I~
                {                                                  //~9305I~
                    try                                            //~9305I~
                    {                                              //~9305I~
    				    if (Dump.Y) Dump.println("OKNGDlg.repaint runonUiThread.run");//~9305I~//~9314R~
                    	updateOKNG();                                  //~9305I~//~9314R~
                    }                                              //~9305I~
                    catch(Exception e)                             //~9305I~
                    {                                              //~9305I~
                        Dump.println(e,"OKNGDlg:repaint.run");  //~9305I~//~9314R~
                    }                                              //~9305I~
                }                                                  //~9305I~
            }                                                      //~9305I~
                                  );                               //~9305I~
    }                                                              //~9305I~
    //*******************************************************      //~9608I~
    //*query force to GO even NG response received                 //~9608R~
    //*******************************************************      //~9608I~
    protected void alertToForceOK(OKNGDlg PokngDlg,int Ptitleid,int Pmsgid)//~9608I~
    {                                                              //~9608I~
        if (Dump.Y) Dump.println("OKNGDlg.alerToForceOK");          //~9608I~
        dlgForAlert=PokngDlg;                                      //~9608I~
        swIgnoreAlertResponse=false;                               //~9608I~
        int flag=BUTTON_POSITIVE|BUTTON_NEGATIVE;                  //~9608I~
        Alert.showAlert(Ptitleid,Pmsgid,flag,this);                //~9608I~
    }                                                              //~9608I~
    //*******************************************************      //~9608I~
    @Override    //AlertI                                          //~9608I~//~9828R~
	public int alertButtonAction(int Pbuttonid,int Prc)       //~v@@@R~//~9608I~
    {                                                              //~9608I~
        if (Dump.Y) Dump.println("OKNGDlg.alerButtonAction buttonID="+Pbuttonid+",rc="+Prc+",swIgnore="+swIgnoreAlertResponse);//~9608R~//~0225R~
        if (!swIgnoreAlertResponse)                                //~9608I~
	        alertActionReceived(Pbuttonid,Prc);                    //~9608R~
        return 0;
    }                                                              //~9608I~
    //*******************************************************      //~9608I~
	protected void alertActionReceived(int Pbuttonid,int Prc)       //~9608I~
    {                                                              //~9608I~
        if (Dump.Y) Dump.println("OKNGDlg.alerActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9608I~
    }                                                              //~9608I~
    //*******************************************************      //~9608I~
    //*query force to GO                                           //~9608I~
    //*******************************************************      //~9608I~
    protected void alertForNG(OKNGDlg PokngDlg,int Ptitleid,int Pmsgid)//~9608I~
    {                                                              //~9608I~
        if (Dump.Y) Dump.println("OKNGDlg.alerForNG");             //~9608I~
        dlgForAlert=PokngDlg;                                      //~9608I~
        int flag=BUTTON_POSITIVE;                                  //~9608I~
        swIgnoreAlertResponse=true;                                //~9608I~
        Alert.showAlert(Ptitleid,Pmsgid,flag,this);                //~9608I~
    }                                                              //~9608I~
}//class                                                           //~v@@@R~
