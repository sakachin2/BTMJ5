//*CID://+@@@@R~: update#= 220;                                    //~1Aa7R~//~@@@@R~
//**********************************************************************//~1107I~
package com.btmtest.dialog;                                                   //~1A65I~//~1Aa7R~//~@@@@R~

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
                                                                   //~@@@@I~
import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;
import com.btmtest.gui.UButton;                                    //~@@@@I~

import static com.btmtest.StaticVars.AG;                           //~9721I~//~@@@@I~

//~v1B6I~
//**********************************************************************//~1107I~
public class AxeDlg extends UFDlg                              //~1830R~//~@@@@R~
    implements  UButton.UButtonI                                   //~@@@@I~
{                                                                  //~0914I~
	private static final int buttonGroup          = R.id.DialogButtons;       //~1528I~
    public  static final String NO_TITLE="NoTitle";                //~vagFI~
//  protected ViewGroup layoutView;    //of UFDlg                    //~1818R~//~@@@@R~
//	protected AxeList axeList;                                     //~1818R~//~v1B6R~
    protected int layoutId;                                          //~1818R~
    private TextView customTitle;                                  //~1A6JI~
    protected boolean swDismissAtPause=true;                       //~@@@@R~
    //**********************************                               //~1211I~
//  public AxeDlg(int Playoutid)                                //~1601R~//~@@@@R~
//  {                                                              //~1211I~//~@@@@R~
//      super(AG.context);                                         //~v1B6R~//~@@@@R~
//  	layoutId=Playoutid;                                        //~1601I~//~@@@@R~
//  }                                                              //~1211I~//~@@@@R~
    public AxeDlg()                                                //~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("AxeDlg.constructor");            //~@@@@I~
    }                                                              //~@@@@I~
    //******************************************                   //~v@@@R~//~@@@@I~
    public static AxeDlg newInstance(int Ptitleid,int Playoutid,String Phelpfile)                        //~9302I~//~9307R~//~@@@@I~
    {                                                              //~v@@@R~//~@@@@I~
        if (Dump.Y) Dump.println("AxeDlg.newInstance title="+ Utils.getStr(Ptitleid)+",layoutid="+Integer.toHexString(Playoutid)+",helpfile="+Phelpfile);//~@@@@I~
    	AxeDlg dlg=new AxeDlg();                                     //~v@@@I~//~9220R~//~9221R~//~9302R~//~9307R~//~@@@@I~
    	UFDlg.setBundle(dlg,Ptitleid,Playoutid,0/*Pflag*/,Ptitleid,Phelpfile);//~@@@@I~
        return dlg;                                                //~v@@@R~//~@@@@I~
    }                                                              //~v@@@R~//~@@@@I~
    //******************************************                   //~@@@@I~
    public static void newInstance(AxeDlg Pdlg,int Ptitleid,int Playoutid,String Phelpfile)//~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("AxeDlg.newInstance with AxeDlg title="+ Utils.getStr(Ptitleid)+",layoutid="+Integer.toHexString(Playoutid)+",helpfile="+Phelpfile);//~@@@@I~
    	UFDlg.setBundle(Pdlg,Ptitleid,Playoutid,0/*Pflag*/,Ptitleid,Phelpfile);//~@@@@R~
    }                                                              //~@@@@R~
    //******************************************                   //~v@@@M~//~@@@@I~
	@Override                                                      //~9221I~//~@@@@I~
    public void onPause()                                          //~9221I~//~@@@@I~
	{                                                              //~9221I~//~@@@@I~
        super.onPause();                                         //~9302R~//~@@@@I~
        if (Dump.Y) Dump.println("AxeDlg:onPause swDismissAtPause="+swDismissAtPause);//~@@@@R~
	    if (swDismissAtPause)                                      //~@@@@I~
	        dismiss();  //because hard to make Complete.Status.ammount to parcelable//~9221I~//~9302R~//~@@@@R~
    }                                                              //~9221I~//~@@@@I~
    //******************************************                   //~9303I~//~@@@@I~
    @Override                                                      //~@@@@I~
    protected void initLayout(View PView)                            //~v@@@I~//~@@@@I~
    {                                                              //~v@@@M~//~@@@@I~
        if (Dump.Y) Dump.println("AxeDlg.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9307R~//~@@@@I~
        androidDlg=getDialog();                                    //~v@@@I~//~9302R~//~9413M~//~@@@@I~
        super.initLayout(PView);                                   //~9302R~//~@@@@I~
        initLayoutAdditional(PView);                               //~9413I~//~@@@@I~
    }                                                              //~v@@@M~//~@@@@I~
    //******************************************                   //~@@@@I~
    protected void initLayoutAdditional(View PView)                //~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("AxeDlg.initLayoutAdditional");   //~@@@@I~
        setupDialogExtendPre(layoutView);	//before setContentView//~@@@@I~
        setupDialogExtend(layoutView);                             //~@@@@I~
        setButtonListenerAll(layoutView);	//bottom buttons       //~@@@@I~
    }                                                              //~@@@@I~
////**********************************                             //~@@@@R~
//    public void showDialog(String Ptitle)                         //~1602R~//~@@@@R~
//    {                                                              //~1528I~//~@@@@R~
//        if (Dump.Y) Dump.println("AxeDlg.showDialog title="+Utils.toString(Ptitle));//~@@@@R~
//        layoutView=(ViewGroup)(AG.inflater.inflate(layoutId,null));//~v1B6I~//~@@@@R~
//      if (Ptitle.equals(NO_TITLE))                                 //~vagFI~//~@@@@R~
//        androidDlg.requestWindowFeature(Window.FEATURE_NO_TITLE);  //must before setcontent//~vagFI~//~@@@@R~
//      else                                                         //~vagFI~//~@@@@R~
//      if (Ptitle!=null)                                          //~@@@@R~
//        androidDlg.setTitle(Ptitle);                                          //~vagFI~//~@@@@R~
//        setupDialogExtendPre(layoutView);   //before setContentView//~vagFI~//~@@@@R~
////      setContentView(layoutView);                                //~1830I~//~@@@@R~
////      switch(layoutId)                                            //~1601I~//~@@@@R~
////      {                                                          //~1601I~//~@@@@R~
////      default:                                                   //~1602I~//~@@@@R~
//            setupDialogExtend(layoutView);                          //~1602I~//~@@@@R~
////      }                                                          //~1601I~//~@@@@R~
//        setButtonListenerAll(layoutView);   //bottom buttons                          //~1602R~//~@@@@R~
////      androidDlg.setOnDismissListener(new dismissListener(this));   //~1215I~//~1410R~//~2111I~//~@@@@R~
//        show();                                                    //~1830R~//~@@@@R~
//    }                                                              //~1528I~//~@@@@R~
//**********************************                               //~@@@@I~
	public void showDialog(String Ptitle)                          //~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("AxeDlg.showDialog title="+Utils.toString(Ptitle));//~@@@@I~
      	if (Ptitle!=null)                                          //~@@@@M~
        {                                                          //~@@@@I~
      		if (Ptitle.equals(NO_TITLE))                           //~@@@@R~
	    		androidDlg.requestWindowFeature(Window.FEATURE_NO_TITLE);  //must before setcontent//~@@@@R~
      		else                                                   //~@@@@R~
        		androidDlg.setTitle(Ptitle);                       //~@@@@I~
        }                                                          //~@@@@I~
        super.show();     //UFDlg                                  //~@@@@R~
    }                                                              //~@@@@I~
//*********                                                        //~1A6JI~
    private void setCustomTitle(String Ptitle)                     //~1A6JI~
    {                                                              //~1A6JI~
		customTitle.setText(Ptitle);                               //~1A6JI~
		customTitle.setVisibility(View.VISIBLE);                   //~1A6JI~
    }                                                              //~1A6JI~
////*********                                                        //~1831I~//~@@@@R~
//    @Override                                                      //~1831I~//~@@@@R~
//    public void onWindowFocusChanged(boolean PhasFocus)         //~1831I~//~@@@@R~
//    {                                                              //~1831I~//~@@@@R~
//        if (Dump.Y) Dump.println("onwindowFocusChanged:"+PhasFocus+","+this.toString());//~1831R~//~@@@@R~
//    }                                                              //~1831I~//~@@@@R~
//*********                                                        //~1602I~
	protected void setupDialogExtend(View PlayoutView)          //~1602R~//~@@@@R~
    {                                                              //~1602I~
//override by Extender                                             //~1602I~
    }                                                              //~1602I~
//*********                                                        //~vagFI~
	protected void setupDialogExtendPre(View PlayoutView)     //~vagFI~//~@@@@R~
    {                                                              //~vagFI~
//override by Extender                                             //~vagFI~
    }                                                              //~vagFI~
//**********************************                               //~1528I~
	private void setButtonListenerAll(View Pview)             //~1602R~//~@@@@R~
    {                                                              //~1528I~
        ViewGroup vg=(ViewGroup)(Pview.findViewById(buttonGroup));//~1528I~
        int ctr=vg.getChildCount();                                //~1528I~
        for (int ii=0;ii<ctr;ii++)                                     //~1528I~
        {                                                          //~1528I~
        	Button btn=(Button)(vg.getChildAt(ii));                //~1529R~
        	int btnid=btn.getId();                                 //~1528I~
            if (Dump.Y) Dump.println("AxeDlg layout="+Integer.toHexString(Pview.getId())+",btnid="+Integer.toString(btnid));//~1528I~//~@@@@R~
            setButtonListener(btn);                                //~1602R~
        }                                                          //~1528I~
    }                                                              //~1528I~
//**********************************                               //~1A65I~
//*for specuific button group                                      //~@@@@I~
//**********************************                               //~@@@@I~
	protected void setButtonListenerAll(int Pbuttongroupid)	        //~1A65I~//~@@@@R~
    {                                                              //~1A65I~
        ViewGroup vg=(ViewGroup)(layoutView.findViewById(Pbuttongroupid));//~1A65I~
        int ctr=vg.getChildCount();                                //~1A65I~
        for (int ii=0;ii<ctr;ii++)                                 //~1A65I~
        {                                                          //~1A65I~
        	Button btn=(Button)(vg.getChildAt(ii));                //~1A65I~
        	int btnid=btn.getId();                                 //~1A65I~
            if (Dump.Y) Dump.println("AxeDlg SetButtonListenerAll btnid="+Integer.toString(btnid));//~1A65I~//~@@@@R~
            setButtonListener(btn);                                //~1A65I~
        }                                                          //~1A65I~
    }                                                              //~1A65I~
//*********************                                            //~1528I~//~@@@@R~
    protected void setButtonListener(Button Pbutton)                 //~1919R~//~@@@@R~
    {                                                              //~1528I~//~@@@@R~
//        ButtonListener cl=this.new ButtonListener(this);           //~1528I~//~@@@@R~
//        Pbutton.setOnClickListener(cl);                            //~1528I~//~@@@@R~
		UButton.bind(Pbutton,this);                                //~@@@@I~
    }                                                              //~1528I~//~@@@@R~
//************************                                         //~1602R~
//*dialog button Listener*                                         //~1602R~
//************************                                         //~1602R~
    @Override //UButtonI                                           //~@@@@I~
    public void onClickButton(Button Pbutton)                      //~@@@@I~
    {                                                              //~1528I~
//        boolean rc=true;                                           //~1528I~//~@@@@R~
        int id=Pbutton.getId();                                    //~@@@@I~
    	if (Dump.Y) Dump.println("AxeDlg onClick buttonid="+Integer.toHexString(id)+"="+Pbutton.getText());//+@@@@R~
    	switch(id)                                          //~1821I~//~@@@@R~
        {                                                          //~1821I~
        case R.id.OK:                                              //~@@@@I~
        	onClickOK();                                           //~@@@@R~
        	break;                                                 //~@@@@I~
        case R.id.Help:                                            //~1821I~
        	onClickHelp();                                      //~1821I~//~@@@@R~
        	break;                                                 //~1821I~
        case R.id.Cancel:                                          //~1821I~
        	onClickCancel();                                    //~1821I~//~@@@@R~
        	break;                                                 //~1821I~
        case R.id.Close:                                           //~1821I~
        	onClickClose();                                     //~1821I~//~@@@@R~
        	break;                                                 //~1821I~
        default:                                                   //~1821I~
//      	rc=onClickOther(PbuttonId);                            //~1821I~//~@@@@R~
        	onClickOther(id);                                      //~@@@@R~
        }                                                          //~1821I~
//        if (rc)                                                    //~1528I~//~@@@@R~
//            dismiss();                                             //~1830I~//~@@@@R~
    }                                                              //~1528I~
//************                                                     //~2111I~
//  protected void onDismiss()                                     //~2111I~//~@@@@I~
    public void onDismiss(DialogInterface Pdialog)                 //~v@@@I~//~@@@@I~
    {                                                              //~2111I~
        if (Dump.Y) Dump.println("AxeDlg:onDismiss");              //~@@@@I~
		super.onDismiss(Pdialog);                                         //~@@@@R~
    	return;                                                    //~2111I~
    }                                                              //~2111I~
////*********************                                            //~1528I~//~@@@@R~
//    public class ButtonListener implements View.OnClickListener         //~1528I~//~@@@@R~
//    {                                                              //~1528I~//~@@@@R~
//        AxeDlg axeDialog;                                       //~1528I~//~@@@@R~
//        public ButtonListener(AxeDlg PaxeDialog)                //~1528I~//~@@@@R~
//        {                                                          //~1528I~//~@@@@R~
//            axeDialog=PaxeDialog;                                  //~1528I~//~@@@@R~
//        }                                                          //~1528I~//~@@@@R~
//        @Override                                                  //~1831I~//~@@@@R~
//        public void onClick(View Pv)                               //~1528I~//~@@@@R~
//        {                                                          //~1528I~//~@@@@R~
//            try                                                    //~1831I~//~@@@@R~
//            {                                                      //~1831I~//~@@@@R~
//                axeDialog.onClickButtons(Pv.getId());              //~1831R~//~@@@@R~
//            }                                                      //~1831I~//~@@@@R~
//            catch(Exception e)                                     //~1831I~//~@@@@R~
//            {                                                      //~1831I~//~@@@@R~
//                Dump.println(e,"AxeDlg.OnClick");               //~1831I~//~@@@@R~
//            }                                                      //~1831I~//~@@@@R~
//        }                                                          //~1528I~//~@@@@R~
//    }                                                              //~1528I~//~@@@@R~
////************************************************                 //~1604I~//~@@@@R~
//    public void showDialogHelp(int PtitleResId,int PmsgResId)      //~1604I~//~@@@@R~
//    {                                                              //~1604I~//~@@@@R~
//        String title=AG.resource.getString(PtitleResId);         //~1A11I~//~v1B6R~//~@@@@R~
////      String helpmsg=Nls.getText(PmsgResId,title/*as textfile name*/);//~1A11I~//~v1B6R~//~@@@@R~
//        String helpmsg=AG.resource.getString(PmsgResId);   //~v1B6I~//~@@@@R~
//        int flag=Alert.BUTTON_CLOSE|Alert.SHOW_DIALOG;       //~1604I~//~v1B6R~//~1A65R~//~@@@@R~
//        Alert.simpleAlertDialog(null/*callback*/,title,helpmsg,flag);//~1A11R~//~v1B6R~//~1A65R~//~@@@@R~
//    }                                                              //~1604I~//~@@@@R~
//************************************************                 //~1604I~
	public void setEditTextEnable(EditText Pview,boolean Peditable)//~1604I~
    {                                                              //~1604I~
        Pview.setEnabled(Peditable); 	//currently cannot set editable=false  by setEnabled//~1604I~
        if (Peditable)                                             //~1604I~
	        Pview.setFocusableInTouchMode(true);                   //~1604I~
        else                                                       //~1604I~
	        Pview.setFocusable(false);                             //~1604I~
    }                                                              //~1604I~
////*******************************                                  //~1126M~//~1215M~//~2111I~//~@@@@R~
////*dismiss listener                                                //~1126I~//~1215M~//~2111I~//~@@@@R~
////*******************************                                  //~1126I~//~1215M~//~2111I~//~@@@@R~
//    public class dismissListener                                   //~1126M~//~1215M~//~2111I~//~@@@@R~
//            implements OnDismissListener                           //~1126M~//~1215M~//~2111I~//~@@@@R~
//    {                                                              //~1126M~//~1215M~//~2111I~//~@@@@R~
//        AxeDlg dlg;                                             //~2111I~//~@@@@R~
//        public dismissListener(AxeDlg Pdlg)                     //~2111I~//~@@@@R~
//        {                                                          //~2111I~//~@@@@R~
//            dlg=Pdlg;                                              //~2111I~//~@@@@R~
//        }                                                          //~2111I~//~@@@@R~
//        @Override                                                  //~1126M~//~1215M~//~2111I~//~@@@@R~
//        public void onDismiss(DialogInterface Pdialog)             //~1126M~//~1215M~//~2111I~//~@@@@R~
//        {                                                          //~1126M~//~1215M~//~2111I~//~@@@@R~
//            if (Dump.Y) Dump.println("AxeDlg onDismiss");        //~1127I~//~1506R~//~2111I~//~@@@@R~
//          try                                                      //~1A65I~//~@@@@R~
//          {                                                        //~1A65I~//~@@@@R~
//            dlg.onDismiss();                                       //~2111I~//~@@@@R~
//          }                                                        //~1A65I~//~@@@@R~
//          catch(Exception e)                                       //~1A65I~//~@@@@R~
//          {                                                        //~1A65I~//~@@@@R~
//            Dump.println(e,"AxeDlg:onDismiss");                 //~1A65I~//~@@@@R~
//          }                                                        //~1A65I~//~@@@@R~
//        }                                                          //~1126M~//~1215M~//~2111I~//~@@@@R~
//    }                                                              //~1126M~//~1215M~//~2111I~//~@@@@R~
//*****************************************************************************************//~1Aa7I~
//*set dialog widow width/height                                   //~1Aa7I~
//*use after setContentView                                        //~1Aa7I~
//*0:wrap_content,-1:match_parent,else percent                     //~1Aa7I~
//*****************************************************************************************//~1Aa7I~
	public void setAxeDlgWindowSize(int Pratew,int Prateh,boolean PminWH/*apply for min(Width,Height)*/)//~1Aa7I~//~@@@@R~
    {                                                              //~1Aa7I~
    	int ww,hh,scrw,scrh;                                       //~1Aa7I~
        scrw= AG.scrWidth;                                          //~1Aa7I~
        scrh=AG.scrHeight;                                         //~1Aa7I~
        if (PminWH)                                                //~1Aa7I~
        {                                                          //~1Aa7I~
        	scrw=Math.min(scrw,scrh);                              //~1Aa7I~
        	scrh=scrw;                                             //~1Aa7I~
        }                                                          //~1Aa7I~
                                                                   //~1Aa7I~
        if (Pratew==-1)                                            //~1Aa7I~
        	ww=ViewGroup.LayoutParams.MATCH_PARENT;                //~1Aa7I~
        else                                                       //~1Aa7I~
        if (Pratew==0)                                             //~1Aa7I~
			ww=ViewGroup.LayoutParams.WRAP_CONTENT;                //~1Aa7I~
        else                                                       //~1Aa7I~
        	ww=scrw*Math.min(Pratew,100)/100;                      //~1Aa7I~
        if (Prateh==-1)                                            //~1Aa7I~
        	hh=ViewGroup.LayoutParams.MATCH_PARENT;                //~1Aa7I~
        else                                                       //~1Aa7I~
        if (Prateh==0)                                             //~1Aa7I~
			hh=ViewGroup.LayoutParams.WRAP_CONTENT;                //~1Aa7I~
        else                                                       //~1Aa7I~
        	hh=scrh*Math.min(Prateh,100)/100;                      //~1Aa7I~
        if (Dump.Y) Dump.println("Dialog:setWindowSize parm w="+Pratew+",h="+Prateh+",pix w="+ww+",h="+hh+",scr w="+AG.scrWidth+",h="+AG.scrHeight+",PminWH="+PminWH);//~1Aa7I~
//      androidDialog.getWindow().setLayout(ww,hh);                //~1Aa7I~
        androidDlg.getWindow().setLayout(ww,hh);                              //~1Aa7I~
    }                                                              //~1Aa7I~
}//class AxeDlg                                                 //~1528R~//~@@@@R~
