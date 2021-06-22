//*CID://+va9fR~:                             update#=  167;       //~1Af6R~//~v@@@R~//+va9fR~
//*****************************************************************//~v101I~
//2021/06/17 va9f correct reason of reverse orientation did not work(fix orientation was called)//+va9fI~
//                not work because onConfigurationChanged is not fired by RVERSE request//+va9fI~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import com.btmtest.R;

import static com.btmtest.game.GConst.*;
import static com.btmtest.dialog.PrefSetting.*;                    //~v@@@R~

import com.btmtest.utils.Dump;
import com.btmtest.utils.EventCB;

public class OrientationMenuDlg                                    //~v@@@R~
		implements UMenuDlg.UMenuDlgI                              //~v@@@I~
{                                                                  //~2C29R~
	int orientation=-1;                                            //~v@@@I~
//**********************************                               //~v@@@I~
    public OrientationMenuDlg()                  //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.constructor");//~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public void show()                                             //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.show menuid");//~v@@@R~
        UMenuDlg.show(this,0/*menuid no meaning,use index only*/,R.string.Settings,R.array.OrientationMenuDlg_Items,false/*singlechoice*/);//~v@@@R~
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
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    private void menuSelected(int Pidx)                            //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("OrientationMenuDlg.menuSetting idx="+Pidx);//~v@@@R~
        int ori=0;                                                 //~v@@@I~
        switch(Pidx) //tumo,pon,chii,kan,reach,ron                 //~v@@@R~
        {                                                          //~v@@@R~
        case 0: //portrait                                         //~v@@@R~
            ori=PS_ORIENTATION_PORTRAIT;                           //~v@@@R~
            break;                                                 //~v@@@R~
        case 1:                                                    //~v@@@R~
            ori=PS_ORIENTATION_LANDSCAPE;                          //~v@@@R~
            break;                                                 //~v@@@R~
        case 2:                                                    //+va9fI~
            ori=PS_ORIENTATION_LANDSCAPE_REVERSE;                  //+va9fI~
            break;                                                 //+va9fI~
        default:                                                   //~v@@@R~
            return;	//cancel                                       //~v@@@R~
        }                                                          //~v@@@R~
        orientation=ori;                                           //~v@@@I~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
