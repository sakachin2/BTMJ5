//*CID://+v@@@R~:                             update#=  164;       //~1Af6R~//~v@@@R~
//*****************************************************************//~v101I~
//*for Test purpos to emulate action button                        //+v@@@I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~

import com.btmtest.R;

import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import com.btmtest.utils.Dump;
import com.btmtest.game.gv.GameViewHandler;//~v@@@R~

public class ActionMenuDlg                                         //~v@@@R~
		implements UMenuDlg.UMenuDlgI                              //~v@@@I~
{                                                                  //~2C29R~
//**********************************                               //~v@@@I~
    public ActionMenuDlg()                                         //~v@@@R~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	public void show()                                             //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("ActionMenuDlg.show menuid");     //~v@@@R~
        UMenuDlg.show(this,0/*menuid no meaning,use index only*/,R.string.Settings,R.array.ActionMenuDlg_Items,true/*multichoice*/);//~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@M~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("ActionMenuDlg.onDismiss");       //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public void menuItemSelected(int Pmenuid,int Pidx,String Pitem)//~v@@@I~
    {                                                              //~v@@@I~
    //*not called if multipleChoceItem                             //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
	@Override                                                      //~v@@@I~
    public boolean menuItemSelectedMulti(int Pmenuid,boolean[] Pselectedid)//~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("ActionMenuDlg.menuItemSelectedMulti menuid="+Pmenuid);//~v@@@R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	int ii;
            for (ii=0;ii<PLAYERS;ii++)                         //~v@@@I~
            {                                                      //~v@@@I~
            	if (Pselectedid[ii])                               //~v@@@I~
                	break;                                         //~v@@@I~
            }                                                      //~v@@@I~
            if (ii==PLAYERS)                                       //~v@@@I~
            	return false;                                      //~v@@@I~
            int player=ii;                                         //~v@@@I~
        	for (ii=PLAYERS;ii<Pselectedid.length;ii++)        //~v@@@I~
            {                                                      //~v@@@I~
            	if (Pselectedid[ii])                               //~v@@@I~
                	break;                                         //~v@@@I~
            }                                                      //~v@@@I~
            if (ii==Pselectedid.length)                            //~v@@@I~
            	return false;                                      //~v@@@I~
        	menuSelected(player,ii-PLAYERS);                     //~v@@@R~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@M~
        {                                                          //~v@@@M~
            Dump.println(e,"ActionMenuDialog");          //~v@@@I~
        }
        return true;//~v@@@M~
    }                                                              //~v@@@M~
//**********************************                               //~v@@@I~
    private void menuSelected(int Pplayer,int Pidx)                //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("ActionMenuDlg.menuSetting player="+Pplayer+",idx="+Pidx);//~v@@@R~
        int msgid;                                                 //~v@@@I~
        switch(Pidx) //tumo,pon,chii,kan,reach,ron                 //~v@@@R~
        {                                                          //~v@@@R~
        case 0:                                                    //~v@@@R~
            msgid=GCM_TAKE;                                        //~v@@@R~
            break;                                                 //~v@@@R~
        case 1:                                                    //~v@@@R~
            msgid=GCM_PON;                                         //~v@@@R~
            break;                                                 //~v@@@R~
        case 2:                                                    //~v@@@R~
            msgid=GCM_CHII;                                        //~v@@@R~
            break;                                                 //~v@@@R~
        case 3:                                                    //~v@@@R~
            msgid=GCM_KAN;                                         //~v@@@R~
            break;                                                 //~v@@@R~
        case 4:                                                    //~v@@@R~
            msgid=GCM_REACH;                                       //~v@@@R~
            break;                                                 //~v@@@R~
        case 5:                                                    //~v@@@R~
            msgid=GCM_RON;                                         //~v@@@R~
            break;                                                 //~v@@@R~
        case 6:                                                    //~v@@@I~
            msgid=GCM_DISCARD;                                     //~v@@@I~
            break;                                                 //~v@@@I~
        case 7:                                                    //~v@@@I~
            msgid=GCM_OPEN;                                        //~v@@@I~
            break;                                                 //~v@@@I~
        case 8:                                                    //~v@@@I~
            msgid=GCM_REMOTE_DICE;                                 //~v@@@R~
            break;                                                 //~v@@@I~
        default:                                                   //~v@@@R~
            msgid=-1;                                              //~v@@@R~
        }                                                          //~v@@@R~
        if (msgid!=-1)                                             //~v@@@R~
            GameViewHandler.sendMsg(msgid,Pplayer,0,0);            //~v@@@R~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
