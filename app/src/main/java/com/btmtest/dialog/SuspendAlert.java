//*CID://+DATER~:                             update#= 1090;       //~v@@@R~//~9211R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~

import com.btmtest.R;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.utils.Alert.*;

//****************************************************************************//~9316I~
public class SuspendAlert                                          //~0306R~
            implements Alert.AlertI                                //~0306R~
{                                                                  //~2C29R~
	private SuspendAlertI alertI;                                          //~0306I~//+0307R~
    private boolean swNextPlayer;                                  //+0307I~
//**********************************                               //~0306I~
	public interface SuspendAlertI                                 //~0306I~
	{                                                              //~0306I~
		public void suspendAlertAction(int Pbuttonid);             //~0306R~
	}//interface AjagoAlertI                                       //~0306I~
	//*************************************************************************//~0304I~//~0306M~
    public SuspendAlert(int Ptitleid,SuspendAlertI PalertI)                    //~0304I~//~0306R~
    {                                                              //~0304I~//~0306M~
    	if (Dump.Y) Dump.println("SuspendAlert.constructor titleid="+Utils.getStr(Ptitleid)+",alertI="+PalertI.toString());//~0306R~
        alertI=PalertI;                                            //~0306I~
        int msgid=R.string.Alert_CompleteDlgConfirmSuspend;        //~0306R~
		Alert.showAlert(Ptitleid,msgid, BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,this);//calback alertButtonAction//~0304I~//~0306I~
    }                                                              //~0304I~//~0306M~
	//*************************************************************************//+0307I~
    public static int newInstance(int Ptitleid,SuspendAlertI PalertI,int PtypeNextGame)//+0307I~
    {                                                              //+0307I~
    	if (Dump.Y) Dump.println("SuspendAlert.newInstance titleid="+Utils.getStr(Ptitleid)+",alertI="+PalertI.toString()+",typeNextGame="+PtypeNextGame);//+0307I~
        int rc=0;
        if (PtypeNextGame==NGTP_NEXTPLAYER)                        //+0307I~
        {                                                          //+0307I~
			UView.showToastLong(R.string.Alert_CompleteDlgConfirmSuspendErr);//+0307I~
            rc=-1;
        }                                                          //+0307I~
        else                                                       //+0307I~
        	new SuspendAlert(Ptitleid,PalertI);
        return rc;//+0307I~
    }                                                              //+0307I~
    //*******************************************************      //~9B29I~//~0306M~
    @Override   //AlertI                                           //~9B29I~//~0306M~
	public int alertButtonAction(int PbuttonID,int PactionID)      //~9B29I~//~0306M~
    {                                                              //~9B29I~//~0306M~
        if (Dump.Y) Dump.println("SuspendAlert.alertButtonAction buttonID="+PbuttonID+",actionID="+PactionID);//~9B29I~//~0106R~//~0304R~//~0306I~
        alertI.suspendAlertAction(PbuttonID);   //BUTTON_POSITIVE/BUTTON_NEGATIVE//~0306I~
        return 0;                                                  //~9B29I~//~0306M~
    }                                                              //~9B29I~//~0306M~
}//class                                                           //~v@@@R~
