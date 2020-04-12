//*CID://+DATER~:                             update#= 1270;       //~v@@@R~//~9211R~//~v@01R~//~0218R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.app.Dialog;
import android.graphics.Point;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~9305I~
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.History;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.gv.GCanvas;
import com.btmtest.game.gv.MJTable;
import com.btmtest.gui.UCheckBox;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.dialog.CompDlgTiles.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.game.gv.MJTable.*;
import static com.btmtest.utils.Alert.*;

public class TestLayout  extends UFDlg                             //~0316R~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.testlayout;         //~0316R~
    private static final int TITLEID=R.string.Title_SuspendIOErrDlg;//~9307I~//~9312R~//~9322R~//~9818R~//~9822R~//~v@01R~
    private static final int TITLEID_RESP=R.string.Title_SuspendIOErrDlgResp;//~v@01I~
    private static final String HELPFILE="SuspendIOErrDlg";                //~9220I~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9818R~//~v@01R~
                                                                   //~v@01I~
    public  static final int SUSPENDIOERR_PENALTY_PAO=8000*3;      //~v@01I~
    private static final int UCBP_PAO=6;                           //~v@01I~
    private static final int UCBP_SUSPENDER=7;// 7-->10 (7+wind seq)//~v@01I~
	private static final int HANDS_POSX=SPACING_X;                 //~0325I~
                                                                   //~9322I~
    private int suspendPenaltyIOErr;                               //~v@01I~
    private boolean swDismissBeforNew;                             //~v@01I~
    private boolean swServer;                                      //~v@01I~
    private TextView tvPenaltyIOErr;                               //~v@01I~
    private UCheckBox cbPenaltyPao;                                //~v@01R~
    private boolean suspendOptionPao;                              //~v@01I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public TestLayout()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9818R~//~v@01R~//~0316R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("TestLayout.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9818R~//~v@01R~//~0316R~
    }                                                              //~v@@@R~
    //******************************************                   //~9818I~
    //On Server                                                    //~9829I~
    //******************************************                   //~9820I~
    public static TestLayout newInstance()                         //~9818I~//~9823R~//~v@01R~//~0316R~
    {                                                              //~9818I~
        if (Dump.Y) Dump.println("TestLayout.newInstance");        //~9818I~//~v@01R~//~0316R~
    	TestLayout dlg=new TestLayout();                           //~9818I~//~v@01R~//~0316R~
    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9818I~
//  			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~9818I~//~0317R~
//  			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~0317I~//~0325R~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_HELPBTN|FLAG_CLOSEBTN,//~0325R~
				TITLEID,HELPFILE);                                 //~9818I~
        return dlg;                                                //~9818I~
    }                                                              //~9818I~
    //******************************************                   //~0316I~
    @Override                                                      //~0316I~
    protected void setWidthOnResume(Dialog Pdlg)                   //~0316I~
    {                                                              //~0316I~
//  	int ww=getDialogWidth();                                   //~0316I~
        	int ww=AG.scrWidthReal;                                //~0316I~
        	int hh=AG.scrHeightReal;                               //~0316I~
    	if (Dump.Y) Dump.println("TestLayout.setWidthOnResume ww="+ww+",hh="+hh);//~0316R~
        super.setWidthOnResume(Pdlg);                              //~0325R~
                                                                   //~0325I~
//      if (ww!=0)                                                 //~0316I~
//      	UView.setDialogWidth(Pdlg,ww);                         //~0316I~
//      else                                                       //~0316I~
//	    	UView.setDialogWidthMatchParent(Pdlg);	//HW MediaPad T5(android8) expand dialog height,so set hight=wrap_content required//~0316I~
//	    	UView.setDialogWidthWrapContent(Pdlg);	//HW MediaPad T5(android8) expand dialog height,so set hight=wrap_content required//~0316I~
//	    	UView.setDialogWidthMatchParentPortrait(Pdlg);	//HW MediaPad T5(android8) expand dialog height,so set hight=wrap_content required//~0316R~
//            hh/=2;                                               //~0316I~
//              WindowManager.LayoutParams p=Pdlg.getWindow().getAttributes();//~0316I~
//              p.width=LayoutParams.WRAP_CONTENT;                 //~0316R~
//              p.height=LayoutParams.WRAP_CONTENT;                //~0316I~
//              Pdlg.getWindow().setAttributes(p);                 //~0316R~
//      UView.setDialogWidthWrapContent(Pdlg);                     //~0325I~
    }                                                              //~0316I~
    //******************************************                   //~0325I~
    @Override                                                      //~0325I~
    protected int getDialogWidth()                                 //~0325I~
    {                                                              //~0325I~
    	if (Dump.Y) Dump.println("TestLayout.getDialogWidth ww=0");//~0325I~
//  	return 800;                                                //~0325R~
    	return 0;                                                  //~0325I~
    }                                                              //~0325I~
    //******************************************                   //~0325I~
    @Override                                                      //~0325I~
    protected void initLayout(View PView)                          //~0325I~
    {                                                              //~0325I~
    	if (Dump.Y) Dump.println("TestLayout.initLayout");         //~0325I~
        super.initLayout(PView);                                   //~0325I~
        initLayoutAdditional(PView);                               //~0325I~
    }                                                              //~0325I~
    //******************************************                   //~0325I~
    protected void initLayoutAdditional(View PView)                //~0325I~
    {                                                              //~0325I~
        if (Dump.Y) Dump.println("TestLayout.initLayoutAdditional");//~0325I~
        if (!Status.isGamingForMenuInGame())                       //~0325I~
        {                                                          //~0325I~
            setImageLayout(PView);                                 //~0325R~
            return;                                                //~0325I~
        }                                                          //~0325I~
        LinearLayout llcomptile=(LinearLayout)UView.findViewById(PView,R.id.LLCompTiles);//~0325I~
        int hh,ww;                                                 //~0325I~
        hh=LinearLayout.LayoutParams.WRAP_CONTENT;                 //~0325I~
        hh=80;                                                     //~0325I~
        ww=600;                                                    //~0325I~
        LinearLayout.LayoutParams llp=new  LinearLayout.LayoutParams(ww,hh);//~0325I~
        llcomptile.setLayoutParams(llp);                           //~0325I~
    }                                                              //~0325I~
//    //******************************************                 //~0325I~
//    public static CompDlgTiles setImageLayout(View PView,int PeswnReach,boolean PswPending,Complete.Status PcompStat)//~0325I~
//    {                                                            //~0325I~
//        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout eswnReach="+PeswnReach+",PView="+PView.toString());//~0325I~
//        GCanvas gcanvas = AG.aGCanvas;                           //~0325I~
//        MJTable table = gcanvas.table;                           //~0325I~
//        int pieceH=table.handPieceH;                             //~0325I~
//        Point p=table.getPairPieceSize();                        //~0325I~
//        int pairPieceH=p.y;                                      //~0325I~
//        int pairPieceW=p.x;                                      //~0325I~
//        int pairctr=getPairCtr(PeswnReach,PcompStat);            //~0325I~
//        LinearLayout llImage    =(LinearLayout)    UView.findViewById(PView,R.id.LLCompTiles);//~0325I~
//        ViewGroup.LayoutParams llp=llImage.getLayoutParams();    //~0325I~
//        boolean swReacher=PeswnReach!=-1;                        //~0325I~
//        int heightTileImage=pieceH+CompDlgTiles.SPACING_Y*2;     //~0325I~
//        int widthTileImage=getHandsMaxWidth(PeswnReach==-1/*swComp*/,pairctr);//~0325I~
//        llp.width=widthTileImage;                                //~0325I~
//        llp.height=heightTileImage;                              //~0325I~
//        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout llImage llp w="+llp.width+",h="+llp.height);//~0325I~
//        llImage.setLayoutParams(llp);                            //~0325I~
//        LinearLayout.LayoutParams ivp=new LinearLayout.LayoutParams(widthTileImage,heightTileImage);//~0325I~
//        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout pieceH="+pieceH+",pairPieceH="+pairPieceH+",layoutparm w="+llp.width+",h="+llp.height);//~0325I~
//        CompDlgTiles v=new CompDlgTiles(AG.context);             //~0325I~
//        v.parmCompStat=PcompStat;                                //~0325I~
//        v.eswnReach=PeswnReach;                                  //~0325I~
//        v.swReacher=swReacher;                                   //~0325I~
//        v.pairHH=pairPieceH;                                     //~0325I~
//        v.pairWW=pairPieceW;                                     //~0325I~
//        v.swPending=PswPending; //drawnLast open Pending         //~0325I~
//        v.widthTileImage=widthTileImage;                         //~0325I~
//        llImage.addView(v,ivp);                                  //~0325I~
//        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout CompDlgTiles ivp w="+ivp.width+",h="+ivp.height);//~0325I~
//        return v;                                                //~0325I~
//    }                                                            //~0325I~
    //******************************************                   //~0325I~
    private void setImageLayout(View PView)                        //~0325R~
    {                                                              //~0325I~
        if (Dump.Y) Dump.println("TestLayout.setImageLayout PView="+PView.toString());//~0325I~
        GCanvas gcanvas = AG.aGCanvas;                             //~0325I~
        if (gcanvas==null)                                         //+0403I~
        {                                                          //+0403I~
            UView.showToast("one start game to setup pieces");     //+0403I~
            return;                                                //+0403I~
        }                                                          //+0403I~
        MJTable table = gcanvas.table;                             //~0325I~
        int pieceH=table.handPieceH;                               //~0325I~
        Point p=table.getPairPieceSize();                          //~0325I~
        int pairPieceH=p.y;                                        //~0325I~
        int pairPieceW=p.x;                                        //~0325I~
//  	int pairctr=getPairCtr(PeswnReach,PcompStat);              //~0325R~
    	int pairctr=0;                                             //~0325I~
        LinearLayout llImage;                                      //~0325R~
        llImage    =(LinearLayout)    UView.findViewById(PView,R.id.LLCompTiles);//~0325I~
        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout llImage="+Utils.toString(llImage));//~0325I~
        ViewGroup.LayoutParams llp=llImage.getLayoutParams();      //~0325I~
        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout llImage llp="+Utils.toString(llp));//~0325I~
//      boolean swReacher=PeswnReach!=-1;                          //~0325R~
        boolean swReacher=false;                                   //~0325I~
        int heightTileImage=pieceH+CompDlgTiles.SPACING_Y*2;       //~0325I~
//      int widthTileImage=getHandsMaxWidth(PeswnReach==-1/*swComp*/,pairctr);//~0325R~
        int widthTileImage=getHandsMaxWidth(false,pairctr);        //~0325I~
        llp.width=widthTileImage;                                  //~0325I~
        llp.height=heightTileImage;                                //~0325I~
        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout llImage llp w="+llp.width+",h="+llp.height);//~0325I~
      	llImage.setLayoutParams(llp);                              //~0325I~
//        LinearLayout.LayoutParams ivp=new LinearLayout.LayoutParams(widthTileImage,heightTileImage);//~0325I~
//        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout pieceH="+pieceH+",pairPieceH="+pairPieceH+",layoutparm w="+llp.width+",h="+llp.height);//~0325I~
//        CompDlgTiles v=new CompDlgTiles(AG.context);             //~0325I~
//        v.parmCompStat=PcompStat;                                //~0325I~
//        v.eswnReach=PeswnReach;                                  //~0325I~
//        v.swReacher=swReacher;                                   //~0325I~
//        v.pairHH=pairPieceH;                                     //~0325I~
//        v.pairWW=pairPieceW;                                     //~0325I~
//        v.swPending=PswPending; //drawnLast open Pending         //~0325I~
//        v.widthTileImage=widthTileImage;                         //~0325I~
//        llImage.addView(v,ivp);                                  //~0325I~
//        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout CompDlgTiles ivp w="+ivp.width+",h="+ivp.height);//~0325I~
//        return v;                                                //~0325I~
    }                                                              //~0325I~
    //***********************************************************  //~0325I~
    public  static int getHandsMaxWidth(boolean PswComp,int Ppairctr)//~0325I~
    {                                                              //~0325I~
    	int xx;                                                    //~0325I~
        if (Dump.Y) Dump.println("CompDlgTiles.getHandsMaxWidth swComp="+PswComp+",pairctr="+Ppairctr);//~0325I~
        if (Ppairctr==0)                                           //~0325I~
        {                                                          //~0325I~
        	int hpw=AG.aMJTable.handPieceW;                        //~0325I~
      	  if (!PswComp)                                            //~0325I~
            xx=HANDS_POSX+(hpw+PIECE_SPACING)*HANDCTR+HANDS_POSX;  //~0325I~
          else                                                     //~0325I~
			xx=HANDS_POSX+(hpw+PIECE_SPACING)*HANDCTR+PIECE_SPACING_TAKEN+hpw+HANDS_POSX;//~0325I~
	        if (Dump.Y) Dump.println("CompDlgTiles.getHandsMaxWidth pairctr=0 pieceW="+hpw+",width="+xx);//~0325I~
        }                                                          //~0325I~
        else                                                       //~0325I~
        {                                                          //~0325I~
        	int left=AG.aHands.getHandsLeftYou();                  //~0325I~
        	int right=AG.aEarth.getEarthRightYou();                //~0325I~
        	xx=right-left+HANDS_POSX;                              //~0325I~
        	if (Dump.Y) Dump.println("CompDlgTiles.getHandsMaxWidth comp pairctr="+Ppairctr+",left="+left+",right="+right+",width="+xx);//~0325I~
        }                                                          //~0325I~
        return xx;                                                 //~0325I~
    }                                                              //~0325I~
}//class                                                           //~v@@@R~
