//*CID://+DATER~:                             update#=  530;       //~v@@@R~//~9211R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.view.View;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;

public class CompDlgReacher                                         //~9219R~//~9228R~
{                                                                  //~2C29R~     //~9214I~
    private TextView tvReacherEswn;                                 //~9222I~
                                                                   //~1A6fI~
    protected UFDlg ufdlg;                                         //~v@@@R~
    private int eswnReacher;                                      //~9223I~//~9309R~
    private boolean swPending=false;                               //~9309I~
    public int widthTileImage;                                     //+9927I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public CompDlgReacher(UFDlg PcompDlg,View Pparent,int PeswnReacher)//~9228R~//~9307R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("CompDlgReacher.Constructor layoutid="+Integer.toHexString(Pparent.getId()));//~9219R~//~9228R~
        eswnReacher=PeswnReacher;                                  //~9228I~
        initLayout(Pparent);                                 //~9219I~//~9222R~
    }                                                              //~v@@@R~
    //*************************************************************************//~9309I~
    public CompDlgReacher(UFDlg PcompDlg,View Pparent,int PeswnReacher,boolean PswPending)//~9309R~
    {                                                              //~9309I~
        if (Dump.Y) Dump.println("CompDlgReacher.Constructor swPending="+PswPending+",layoutid="+Integer.toHexString(Pparent.getId()));//~9309I~
        eswnReacher=PeswnReacher;                                  //~9309I~
        swPending=PswPending;                                      //~9309I~
        initLayout(Pparent);                                       //~9309I~
    }                                                              //~9309I~
    //******************************************                   //~v@@@M~
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("CompDlgReacher.initLayout");        //~v@@@R~//~9219R~//~9228R~
        PView.setVisibility(View.VISIBLE);                         //~9228I~
        tvReacherEswn         =(TextView)    UView.findViewById(PView,R.id.tvReacherEswn);//~9222I~//~9228R~
        tvReacherEswn.setText(GConst.nameESWN[eswnReacher]);        //~9228I~
    	if ((TestOption.option & TestOption.TO_COMPDLG_LAYOUT)==0) //TODO TEST//~9219I~//~9228R~
        {                                                          //+9927I~
//  		CompDlgTiles.setImageLayout(PView,eswnReacher,swPending);                        //~9217R~//~9218R~//~9219R~//~9228R~//~9301R~//~9309R~//+9927R~
    		CompDlgTiles dlg=CompDlgTiles.setImageLayout(PView,eswnReacher,swPending);//+9927I~
            widthTileImage=dlg.widthTileImage;                     //+9927I~
	        if (Dump.Y) Dump.println("CompDlgReacher.initLayout widthTileImage="+widthTileImage);//+9927I~
        }                                                          //+9927I~
    }                                                              //~v@@@M~
}//class                                                           //~v@@@R~
