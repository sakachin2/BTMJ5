//*CID://+vajhR~:                             update#=  179;       //~vajgR~//~vajhR~
//*****************************************************************//~v101I~
//2022/01/31 vajh over vajg/vage, try to allow startgame from client//~vajhI~
//2022/01/30 vajg over vajf, as a rule reject cancel on the menu at client//~vajfI~
//2022/01/30 vajf (bug)if canceled on orientation dialog on client at startgame. back button on server cause dump on client//~vajgI~
//2021/06/17 va9f correct reason of reverse orientation did not work(fix orientation was called)//~va9fI~
//                not work because onConfigurationChanged is not fired by RVERSE request//~va9fI~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import com.btmtest.BT.BTMulti;
import com.btmtest.R;

import static com.btmtest.game.GConst.*;
import static com.btmtest.dialog.PrefSetting.*;                    //~v@@@R~
import static com.btmtest.StaticVars.AG;                           //~vajgR~

import com.btmtest.utils.Dump;
import com.btmtest.utils.EventCB;
import com.btmtest.utils.UView;

public class OrientationMenuDlg                                    //~v@@@R~
		implements UMenuDlg.UMenuDlgI                              //~v@@@I~
{                                                                  //~2C29R~
	private int orientation=-1;                                            //~v@@@I~//+vajhR~
    private UMenuDlg dlg;                                                  //~vajgR~//+vajhR~
    private boolean swServerStartGame=true;                        //+vajhR~
//**********************************                               //~v@@@I~
    public OrientationMenuDlg()                  //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.constructor");//~v@@@I~
        AG.aOrientationMenuDlg=this;                                  //~vajgR~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public void show()                                             //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.show menuid");//~v@@@R~
//      UMenuDlg.show(this,0/*menuid no meaning,use index only*/,R.string.Settings,R.array.OrientationMenuDlg_Items,false/*singlechoice*/);//~vajgR~
    if (swServerStartGame)                                         //~vajhR~
      dlg=UMenuDlg.show(this,0/*menuid no meaning,use index only*/,R.string.Settings,R.array.OrientationMenuDlg_Items,false/*singlechoice*/,false/*customTheme*/,true/*swNoAutoDismiss*/);//~vajgR~
    else                                                           //~vajhI~
      dlg=UMenuDlg.show(this,0/*menuid no meaning,use index only*/,R.string.Settings,R.array.OrientationMenuDlg_Items,false/*singlechoice*/);//~vajhI~
    if (swServerStartGame)                                         //~vajhR~
      dlg.setCancelable(false);//reject Back button                //~vajfI~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@M~
	@Override                                                      //~v@@@I~
    public void menuItemSelected(int Pmenuid,int Pidx,String Pitem)//~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.menuSetting idx="+Pidx+",item="+Pitem);//~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
		    menuSelected(Pidx);                                 //~v@@@I~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"OrientationMenuDialog");               //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public boolean menuItemSelectedMulti(int Pmenuid,boolean[] Pselectedid)//~v@@@R~
    {                                                              //~v@@@I~
    //*not called if multipleChoceItem                             //~v@@@M~
    	return false;                                              //~v@@@I~
    }                                                              //~v@@@M~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.onDestroy orientation="+orientation);//~v@@@R~
        if (orientation>=0)                                        //~v@@@I~
			EventCB.postEvent(ECB_ORIENTATION,orientation,"");             //~v@@@I~
      	AG.aOrientationMenuDlg=null;                               //~vajgI~
    }                                                              //~v@@@I~
//**********************************                               //~vajgI~
    public void returnEndgame()                                    //~vajgI~
    {                                                              //~vajgI~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.returnEndgame");//~vajgI~
        orientation=-1;	//avoid recreation dialog                  //~vajgI~
    	dlg.dismiss();                                             //~vajgI~
    }                                                              //~vajgI~
//**********************************                               //~v@@@I~
    private void menuSelected(int Pidx)                            //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.menuSelected idx="+Pidx+",swTrainingMode="+AG.swTrainingMode);//~v@@@R~//~vajgR~
        int ori=0;                                                 //~v@@@I~
        boolean swDismiss=true;                                    //~vajgI~
        switch(Pidx) //tumo,pon,chii,kan,reach,ron                 //~v@@@R~
        {                                                          //~v@@@R~
        case 0: //portrait                                         //~v@@@R~
            ori=PS_ORIENTATION_PORTRAIT;                           //~v@@@R~
            break;                                                 //~v@@@R~
        case 1:                                                    //~v@@@R~
            ori=PS_ORIENTATION_LANDSCAPE;                          //~v@@@R~
            break;                                                 //~v@@@R~
        case 2:                                                    //~va9fI~
            ori=PS_ORIENTATION_LANDSCAPE_REVERSE;                  //~va9fI~
            break;                                                 //~va9fI~
        default:                                                   //~v@@@R~
          if (swServerStartGame)                                   //~vajhR~
          {                                                        //~vajhI~
            if (!AG.swTrainingMode && !BTMulti.isServerDevice())   //~vajgR~
            {                                                      //~vajgR~
    	    	UView.showToast(R.string.Err_SelectOrientation);   //~vajgR~
            	swDismiss=false;                                   //~vajgR~
                break;                                             //~vajgR~
            }                                                      //~vajgR~
//          return;	//cancel                                       //~v@@@R~//~vajgR~
            ori=-1;                                                //~vajgI~
          }                                                        //~vajhI~
          else                                                     //~vajhI~
          	return;                                                //~vajhI~
        }                                                          //~v@@@R~
        orientation=ori;                                           //~v@@@I~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.menuSetting orientation="+orientation+",swDismiss="+swDismiss);//~vajgI~
      if (swServerStartGame)                                       //~vajhR~
        if (swDismiss)                                             //~vajgR~
            dlg.dismiss();                                         //~vajgI~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
