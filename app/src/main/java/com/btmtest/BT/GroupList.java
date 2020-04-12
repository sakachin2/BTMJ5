//*CID://+DATER~:                             update#=  442;       //~v001R~//~@002R~//~9210R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
//*****************************************************************//~v@@@I~
package com.btmtest.BT;                                        //~v@@@R~//~9722R~
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Color;                                     //~v@@@I~

import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.dialog.BTCDialog;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UListView;                                //~v@@@I~
import com.btmtest.gui.UEditText;                                  //~v@@@I~
import com.btmtest.utils.Utils;
import com.btmtest.wifi.WDA;

import static com.btmtest.AG.*;
import static com.btmtest.BT.BTMulti.*;
import static com.btmtest.BT.Members.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@002I~

public class GroupList                                             //~9722R~
{                                                                  //~2C29R~
                                                                   //~v@@@I~
    private static final int RID_GROUPLIST=R.id.GroupList;         //~9722I~
	private static final int COLOR_BG_GROUP_LIST =Color.rgb(0xc8,0xc8,0xff);  //orange//~9722I~
    private static final int BTROW_MEMB1=R.id.GroupMember1;        //~9722I~
    private static final int BTROW_MEMB2=R.id.GroupMember2;        //~9722I~
//  private static final int COLOR_CLIENT           =Color.rgb(0xff,0x6e, 0   ); //orange//~9722I~//~9816R~
//  private static final int COLOR_CLIENT           =Color.rgb(0x00,0x59, 0   ); //orange//~9816I~//~9904R~
	private static final int COLOR_CLIENT           =Color.rgb(0xff,0x6e, 0   ); //orange//~9904I~
    private static final int COLOR_SERVER           =Color.rgb(0xff,0x20, 0);  //orange//~9722I~
    private ListGL GL;   	//listview group list    //@@@@test parcelable//~v@@@R~
    private View layoutView;                                       //~9722I~
    private Members members;                                       //~v@@@I~
    private int connectedCtr;    //number of addr!=null, =1 for client//~9722I~
    private int memberCtr;       //number of yourname!=null        //~9722I~
    private UEditText etYourName;                                  //~9722I~
                                                                   //~1A6fI~
    //******************************************                   //~v@@@M~
	public GroupList()                                             //~v@@@M~//~9722R~
	{                                                              //~3105R~//~v@@@M~
        if (Dump.Y) Dump.println("GroupList.constructor");         //~9722R~
        init();                                                    //~9722I~
	}                                                              //~v@@@M~
    //******************************************                   //~9722I~
    public void init()                                             //~9722R~
    {                                                              //~9722I~
    }                                                              //~9722I~
    //******************************************                   //~9722I~
    public void init(View PlayoutView,UEditText PetYourName)       //~9722I~
    {                                                              //~9722I~
		members=AG.aBTMulti.BTGroup;                               //~9722I~
    	layoutView=PlayoutView;                                    //~9722I~
    	etYourName=PetYourName;                                    //~9722I~
    }                                                              //~9722I~
    //******************************************                   //~v@@@I~
    public  void updateDialog()                                    //~v@@@I~//~9722R~
    {                                                              //~v@@@I~
//        displayLocalDevice(); //local devicename                   //~v@@@R~//~9722R~
    	etYourName.setText(AG.YourName);                           //~9724I~
        displayGroup();  //group member list(connected)            //~v@@@I~
//        displayRemoteDevice(); //paired device list                //~v@@@R~//~9722R~
//        updateButtonStatus();                                      //~v001R~//~9722R~
	}                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    public void displayGroup()                                     //~v@@@I~//~9724R~
    {                                                              //~v@@@I~
        Members gl=members;                                        //~v@@@R~
        connectedCtr=gl.getConnectedCtr();                         //~v@@@I~
        memberCtr=gl.getMemberCtr();                               //~v@@@I~
        if (Dump.Y) Dump.println("GroupList displayGroup connectedctr="+connectedCtr+",memberctr="+memberCtr);//~v@@@R~//~9722R~
  		if (GL==null)                                              //~v@@@R~
        {                                                          //~v@@@I~
	        GL=new ListGL(layoutView,RID_GROUPLIST,R.layout.textrowlist_bt_group); //display member 2column/row//~v@@@R~
		    GL.setBackground(COLOR_BG_GROUP_LIST);                 //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@R~
        	GL.removeAll(false/*notifyChanged later*/);            //~v@@@R~
        int ctr=gl.getLength();                                    //~v@@@R~
      	boolean swNotConnectedToServer=!BTMulti.isServerDevice() && gl.idxServer!=-1 && gl.getThread(gl.idxServer)==null;	//parm to YNDN//~9B07R~
        if (Dump.Y) Dump.println("GroupList displayGroup gl.length="+ctr+",swNotConnectedToServer="+swNotConnectedToServer);//~9724I~//~9B07I~
        for (int ii=0;ii<ctr;ii+=2)                                //~v@@@I~
        {                                                          //~v@@@I~
        	if (ii+1>=ctr)                                         //~v@@@I~
	           GL.add(YNDN(swNotConnectedToServer,gl,ii),"",0);                           //~v@@@R~//~9B07R~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
	           GL.add(YNDN(swNotConnectedToServer,gl,ii),YNDN(swNotConnectedToServer,gl,ii+1),0);                //~v@@@R~//~9B07R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        GL.notifyChanged();                                           //~v@@@I~
	    showYourName();                                            //~v@@@I~
     }                                                              //~v@@@I~
    //******************************************                   //~9722I~
    private void showYourName()                                    //~9722I~
	{                                                              //~9722I~
        if (Dump.Y) Dump.println("GroupList.showYourName memberCtr="+memberCtr);//~9722I~
    	if (etYourName==null)                                      //~9722I~
        	return;                                                //~9722I~
        if (AG.activeSessionType==AST_BT)                          //+9B07I~
        BTCDialog.showYourName(etYourName,memberCtr);              //~9722I~
        else                                                       //+9B07I~
        WDA.showYourName(etYourName,memberCtr);                    //+9B07I~
    }                                                              //~9722I~
    //******************************************                   //~v@@@I~
    //*member name by YoutrName and DeviceName                     //~v@@@I~//~9722R~
    //******************************************                   //~v@@@I~
	private static String YNDN(boolean PswNotConnectedToServer,Members Pmembers,int Pidx)          //~v@@@R~//~9B07R~
    {                                                              //~v@@@I~
        boolean rc=AG.aBTMulti!=null && AG.aBTMulti.memberRole==ROLE_SERVER;//~9928I~
        if (Dump.Y) Dump.println("GroupList.YNDN role="+AG.aBTMulti.memberRole+",idx="+Pidx+",MD="+Pmembers.MD[Pidx].toString());//~9928R~
        String dn=Pmembers.getName(Pidx);	//devicename           //~v@@@I~
        if (dn.contains(AG.aBTMulti.memberNotConnectedId))         //~@002I~
        	return dn;                                             //~v@@@I~
      if (AG.aBTMulti.memberRole==ROLE_UNDEFINED)                  //~9928I~
	  	return AG.aBTMulti.memberNotConnectedId;                   //~9928I~
      else                                                         //~9928I~
      if (BTMulti.isServerDevice())                                //~9928I~
      {                                                            //~9928I~
        if (Pidx!=Pmembers.idxLocal && Pmembers.getThread(Pidx)==null)//~9928I~
	        return AG.aBTMulti.memberNotConnectedId;               //~9928I~
      }                                                            //~9928I~
      else    //client                                             //~9928I~
      {                                                            //~9928I~
      	if ((Pmembers.MD[Pidx].status & MS_REMOTECLIENT)==0)         //~9906R~//~9928R~//~9B07R~
        {                                                          //~9B07I~
        	if (Pidx!=Pmembers.idxLocal && Pmembers.getThread(Pidx)==null)//~9731R~//~9928R~//~9B07R~
	        	return AG.aBTMulti.memberNotConnectedId;               //~9731I~//~9B07R~
        }                                                          //~9B07I~
        else //remote client on client                             //~9B07I~
        if (PswNotConnectedToServer)	//disconnected other client when my dislog was dismissed//~9B07R~
        {                                                          //~9B07I~
	        return AG.aBTMulti.memberNotConnectedId;               //~9B07I~
        }                                                          //~9B07I~
      }                                                            //~9928I~
        String yn=Pmembers.getYourName(Pidx);                      //~v@@@R~
        if (yn==null||yn.equals(""))                               //~v@@@R~
	        yn=Utils.getStr(R.string.UnknownYourName);             //~v@@@I~
	    return yn;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**********************************************************************//~v@@@I~
    //*ListView GroupList *************************************************//~v@@@I~
    //**********************************************************************//~v@@@I~
    class ListGL extends UListView                                 //~@002R~
    {                                                              //~v@@@I~
    //*****************                                            //~v@@@I~
        ListGL(View Playout,int Plvid,int Prowresid)        //~v@@@R~
        {                                                          //~v@@@I~
            super(Playout,Plvid,Prowresid,null/*listener*/,UListView.CHOICEMODE_NONE);//~v@@@R~
        }                                                          //~v@@@I~
    //**********************************************************************//~v@@@I~
        @Override                                                  //~v@@@I~
        public View getViewCustom(int Ppos, View Pview,ViewGroup Pparent)//~v@@@I~
        {                                                          //~v@@@I~
        //*******************                                      //~v@@@I~
            if (Dump.Y) Dump.println("GroupList:getViewCustom Ppos="+Ppos+",CheckedItemPos="+((ListView)Pparent).getCheckedItemPosition());//~v@@@I~//~9722R~
            View v=Pview;                                          //~v@@@I~
            if (v == null)                                         //~v@@@I~
			{                                                      //~v@@@I~
                v=AG.inflater.inflate(rowId/*super*/,null);        //~v@@@I~
            }                                                      //~v@@@I~
            TextView v1=v.findViewById(BTROW_MEMB1);     //~v@@@I~
            TextView v2=v.findViewById(BTROW_MEMB2);     //~v@@@I~
            UListViewData ld=arrayData.get(Ppos);                  //~v@@@I~
            v1.setText(ld.itemtext);                               //~v@@@I~
            v2.setText(ld.itemtext2);                              //~v@@@I~
            v1.setTextColor(memberColor(ld.itemtext));             //~v@@@I~
            v2.setTextColor(memberColor(ld.itemtext2));            //~v@@@I~
            v1.setBackgroundColor(bgColor);                        //~v@@@I~
            v2.setBackgroundColor(bgColor);                        //~v@@@I~
            if (Dump.Y) Dump.println("GroupList:getViewCustom Ppos="+Ppos+",v1="+ld.itemtext+",v2="+ld.itemtext2);//~9722I~
            return v;                                              //~v@@@I~
        }                                                          //~v@@@I~
		//**********************************************************************//~v@@@I~
        private int memberColor(String Pyourname)                  //~v@@@R~
        {                                                          //~v@@@I~
        	int color=Color.BLACK;                                 //~v@@@I~
            if (Pyourname!=null)                                   //~v@@@R~
            {                                                      //~v@@@I~
				Members m=AG.aBTMulti.BTGroup;                     //~@002I~
                String devname=m.getName(Pyourname);          //~v@@@R~//~@002R~
                if (devname!=null)                                 //~v@@@I~
                {                                                  //~9816I~
		            if (Dump.Y) Dump.println("GroupList:memberColor yourname="+Pyourname+",devname="+devname+",serverdevname="+AG.aBTMulti.serverDeviceName);//~9816I~
                    if (devname.equals(AG.aBTMulti.serverDeviceName))//~v@@@R~
                        color=COLOR_SERVER;                        //~v@@@R~
                    else                                           //~v@@@R~
                        color=COLOR_CLIENT;                        //~v@@@R~
                }                                                  //~9816I~
            }
            return color;//~v@@@I~
        }                                                          //~v@@@I~
    }//class                                                       //~v@@@I~
}//class                                                           //~v@@@R~
