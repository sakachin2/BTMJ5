//*CID://+DATER~:                             update#= 402;        //~var8R~//~2A02R~
//**********************************************************************//~v@@@I~
//2022/09/24 var8 display profile icon                             //~var8I~
//**********************************************************************//~va46I~
package com.btmtest.game.gv;                                            //~v@@@R~

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.oldDiceWidth;
import static com.btmtest.game.GConst.*;
import static com.btmtest.StaticVars.AG;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.btmtest.BT.BTMulti;
import com.btmtest.BT.Members;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//****************************************************         //~1AbTI~//~v@@@R~
public class ProfileData                                           //~var8R~
{                                                              //~1AbTI~//~v@@@R~
	public static final int PDT_AVAIL        =0x01;                //~var8R~
	public static final int PDT_NOT_SHOWME   =0x02;                //~var8R~
	public static final int PDT_SAVED        =0x04;                //~var8R~
	public static final int PDT_NOTIFIED     =0x08; //notified to this remote device//~var8I~
	public static final int PDT_NOTIFY_RESP  =0x10; //notified response received//~2A02I~
                                                                   //~2A02I~
	public static final int PDT_MASK_RECEIVED=~(PDT_SAVED);		//reset for received PI//~2A02I~
                                                                   //~2A02I~
	private ProfileIcon aProfileIcon;                              //~var8R~
	private ProfileInfo piMe;                                      //~var8R~//~2A03R~
	private Map<String,ProfileInfo> profileMap=new HashMap<>();    //~var8R~
	private boolean swServer;                                     //~var8I~
	public int idxServer;                                          //~var8R~
	public int idxMe;                                              //~var8R~
    private ProfileInfo[] members=new ProfileInfo[PLAYERS];	//MD sequence//~var8R~
                                                                   //~2A02I~
    private int[][] statusSaved=new int[PLAYERS][PLAYERS];	//on server,status of client//~var8I~
	private static final int PDS_NEED_SEND     =0x01; //send required//~2A04R~
	private static final int PDS_SEND          =0x02;              //~2A02R~
	private static final int PDS_SENT          =0x04;              //~2A02I~
                                                                   //~2A02I~
    private BTMulti BTM;                                           //~var8I~
    private Members BTG;                                           //~var8I~
//*****************                                            //~1AbTI~//~v@@@R~
    public ProfileData(ProfileIcon PprofileIcon)                                 //~v@@@R~//~var8R~
    {                                                          //~1AbTI~//~v@@@R~
    	aProfileIcon=PprofileIcon;                                 //~var8R~
    }                                                          //~1AbTI~//~v@@@R~
    //*************************************************************//~var8I~
    public String toString()                                      //~var8I~
    {                                                              //~var8I~
    	String rc="";                                              //~var8I~
        rc+="Me="+piMe+"\nmembers=\n";                                        //~var8I~//~2A02R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~var8I~
        {                                                          //~var8I~
        	rc+="["+(members[ii]==null ? "" : members[ii].toString())+"]";//~2A02I~
        }                                                          //~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~2A02I~
    public String toStringMap()                                    //~2A02I~
    {                                                              //~2A02I~
    	String rc="";                                              //~2A02I~
    	for (ProfileInfo pi:profileMap.values())                   //~2A02I~
        {                                                          //~2A02I~
    		rc+=pi.toString()+"\n";                                 //~2A02I~
        }                                                          //~2A02I~
        return rc;                                                 //~2A02I~
    }                                                              //~2A02I~
    //*************************************************************//~var8I~
    public void putMe(boolean PswServer,String Pdevice,String PyourName,int PbmType,String PdisplayName,String Ptimestamp,String Psize,byte[] Pimage)//~var8R~//~2A03R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.putMe");             //~var8R~
        BTM=AG.aBTMulti;                                           //~var8I~
        BTG=BTM.BTGroup;                                           //~var8I~
        ProfileInfo pi=new ProfileInfo(Pdevice,PyourName,PbmType,PdisplayName,Ptimestamp,Psize);//~var8R~
        pi.compressedImage=Pimage;                                //~2A03I~
        if ((PbmType & PDT_AVAIL)!=0)                              //~var8R~
	        pi.bmType|=PDT_SAVED;                                  //~var8I~
        if (piMe!=null && (piMe.bmType & PDT_NOT_SHOWME)!=0)       //~2A05I~
            pi.bmType|=PDT_NOT_SHOWME;                             //~2A05I~
        piMe=pi;                                                   //~var8I~
		swServer=PswServer;                                        //~var8I~
        resetMember();                                             //~var8I~
    	putMap(true/*remove*/,piMe.yourName,piMe);                 //~2A02I~
        idxServer=BTG.idxServer;                                   //~var8R~//~2A02M~
        int idx=getMemberIdx(Pdevice);                             //~var8I~
        members[idx]=piMe;                                         //~var8I~
        idxMe=idx;                                                 //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.putMe idxMe="+idxMe+",idxServer="+idxServer+",members="+Utils.toString(members));//~var8I~//~2A05R~
    }                                                              //~var8I~
    //*************************************************************//~2A05I~
    public void updateTypeMe(boolean PswShowMe)                    //~2A05I~
    {                                                              //~2A05I~
    	if (Dump.Y) Dump.println("ProfileData.updateTypeMe swShowMe="+PswShowMe);//~2A05I~
        if (piMe!=null)                                            //~2A05I~
        	if (PswShowMe)                                         //~2A05I~
            	piMe.bmType&=~PDT_NOT_SHOWME;                      //~2A05I~
            else                                                   //~2A05I~
            	piMe.bmType|=PDT_NOT_SHOWME;                       //~2A05I~
    	if (Dump.Y) Dump.println("ProfileData.updateTypeMe piMe="+piMe);//~2A05I~
    }                                                              //~2A05I~
    //*************************************************************//~2A03I~
    public byte[] getCompressedMe()                                //~2A03I~
    {                                                              //~2A03I~
        byte[] image=piMe.compressedImage;                           //~2A03I~
        if (Dump.Y) Dump.println("ProfileData.getCompressedMe piMe="+piMe);//~2A03I~
        return image;                                              //~2A03I~
    }                                                              //~2A03I~
    //*************************************************************//~var8R~
    //*on Server by 76 (received client Image)                                            //~2A03I~//+2A06R~
    //*************************************************************//~2A03I~
    public void updateImageOther(int Pidx,byte[] Pimage)           //~var8R~
    {                                                              //~var8R~
        ProfileInfo pi=members[Pidx];                              //~var8R~
        if (Dump.Y) Dump.println("ProfileData.updateImageOther idx="+Pidx+",image="+Pimage+",old="+pi.compressedImage);//~var8I~//~2A03R~
        pi.compressedImage=Pimage;                                 //~var8R~
        if (Pimage==null)                                          //+2A06I~
	        pi.bmType&=~PDT_AVAIL;                                 //+2A06I~
        else                                                       //+2A06I~
	        pi.bmType|=PDT_SAVED;                                     //~var8I~//+2A06R~
        if (Dump.Y) Dump.println("ProfileData.updateImageOther pi="+pi);//+2A06I~
    }                                                              //~var8R~
    //*************************************************************//~2A02I~
    public void updateImageOtherOnClient(String PyourName,byte[] Pimage)//~2A02I~
    {                                                              //~2A02I~
    	int idx=getIndexByYourName(PyourName);                         //~2A02I~
        ProfileInfo pi=members[idx];                               //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.updateImageOtherOnClient idx="+idx+",yourName="+PyourName+",imageLen="+Pimage.length);//~2A02I~
        if (idx>=0)                                                //~2A02I~
        {                                                          //~2A02I~
       		pi.compressedImage=Pimage;                             //~2A02I~
        	pi.bmType|=PDT_SAVED;                                  //~2A02I~
	    	putMap(true/*remove*/,pi.yourName,pi);  //replace map  //~2A02I~
        }                                                          //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.updateImageOtherOnClient exit pi="+pi);//~2A02I~
    }                                                              //~2A02I~
    //*************************************************************//~var8I~
    public boolean  updateNotifyAllRespReceived(int Pidx)           //~var8I~
    {                                                              //~var8I~
        ProfileInfo pi=members[Pidx];                              //~var8I~
        if (Dump.Y) Dump.println("ProfileData.updateNotifyAllRespReceived idx="+Pidx+",pi="+pi);//~var8I~
        if (pi!=null)                                              //~var8I~
        	pi.bmType|=PDT_NOTIFIED;                               //~var8I~
	    boolean rc=isReceivedAllNotifyResp();                       //~var8I~
        if (Dump.Y) Dump.println("ProfileData.updateNotifyAllRespReceived rc="+rc);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    //*on Server ,at received 78                                                  //~var8I~//~2A05R~
    //*************************************************************//~var8I~
    public void updateOtherSaved(int Psender,String Psaved/*0:not saved,1:saved:,2:bmpMe=null*/,String PyourName)//~var8I~//~2A02R~//~2A05R~
    {                                                              //~var8I~
        if (Dump.Y) Dump.println("ProfileData.updateOtherSaved sender="+Psender+",saved="+Psaved+",yn="+PyourName);//~var8I~//~2A02R~
        if (Dump.Y) Dump.println("ProfileData.updateOtherSaved entry statusSaved="+Utils.toString(statusSaved));//~2A02I~
        int idx=getIndexByYourName(PyourName);                     //~2A02R~
        int needSend=Utils.parseInt(Psaved,0)==0 ? PDS_NEED_SEND : 0;//~2A02R~//~2A04R~//~2A06R~
        statusSaved[Psender][idx]=needSend;                           //~2A02I~//~2A04R~
        ProfileInfo pi=members[Psender];                           //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.updateOtherSaved   needSend="+needSend+",pi="+pi);//~2A02I~//~2A04R~
        if (pi!=null)                                              //~2A02I~
        	pi.bmType|=PDT_NOTIFY_RESP;                            //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.updateOtherSaved exit idx="+idx+",statusSaved="+Utils.toString(statusSaved)+",this="+toString());//~2A02R~
    }                                                              //~var8I~
    //*************************************************************//~2A02I~
    //*on Server,at received 78                                    //~2A05R~
    //*************************************************************//~2A02I~
    public boolean isNotifyRespReceivedAll()                       //~2A02I~
    {                                                              //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.isNotifyRespAllReceived this="+toString());//~2A02R~
        boolean rc=true;                                           //~2A02I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~2A02I~
        {                                                          //~2A02I~
        	if (ii==idxServer)                                     //~2A02I~
            	continue;                                          //~2A02I~
			ProfileInfo pi=members[ii];                            //~2A02I~
            if (pi!=null)                                          //~2A02I~
				if ((pi.bmType & PDT_NOTIFY_RESP)==0)               //~2A02I~
                {                                                  //~2A02I~
                    rc=false;                                      //~2A02I~
                    break;                                         //~2A02I~
                }                                                  //~2A02I~
        }                                                          //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.isNotifyRespAllReceived rc="+rc);//~2A02I~
        return rc;                                                 //~2A02I~
    }                                                              //~2A02I~
    //*************************************************************//~2A02I~
    //*on Server                                                   //~2A02I~
    //*************************************************************//~2A02I~
    public byte[] getImageS2C(Point Ps_and_c)                      //~2A02I~
    {                                                              //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.getImageS2C members="+Utils.toString(members));       //~2A02I~//~2A05R~
        byte[] image=null;                                         //~2A02I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~2A02I~
        {                                                          //~2A02I~
        	if (ii==idxMe)                                         //~2A02I~
            	continue;                                          //~2A02I~
			ProfileInfo pi=members[ii];                            //~2A02I~
            if (pi==null)                                          //~2A02I~
            	continue;                                          //~2A02I~
	        if (Dump.Y) Dump.println("ProfileData.getImageS2C statusSaved["+ii+"]="+Utils.toString(statusSaved[ii]));//~2A03I~
            boolean swFound=false;                                 //~2A04I~
            for (int jj=0;jj<PLAYERS;jj++)                             //~2A02I~
            {                                                      //~2A02I~
            	int status=statusSaved[ii][jj];                    //~2A02I~
				if ((status & PDS_NEED_SEND)!=0                    //~2A04R~
				&&  (status & PDS_SEND)==0)                        //~2A04I~
                {                                                  //~2A02I~
                	Ps_and_c.x=ii;                                 //~2A02I~
                	Ps_and_c.y=jj;                                 //~2A02I~
					ProfileInfo piToSend=members[jj];              //~2A03I~
                    image=piToSend.compressedImage;                      //~2A02I~//~2A03R~
                    if (image!=null)                               //~2A05I~
                    {                                              //~2A05I~
            			statusSaved[ii][jj]|=PDS_SEND;                 //~2A02I~//~2A05R~
                    	swFound=true;                                  //~2A04I~//~2A05R~
                    	break;                                         //~2A02I~//~2A05R~
                    }                                              //~2A05I~
                }                                                  //~2A02I~
            }                                                      //~2A02I~
            if (swFound)                                           //~2A04I~
            	break;                                             //~2A04I~
        }                                                          //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.getImageS2C s_andc="+Ps_and_c+",image="+image+",statusSaved="+Utils.toString(statusSaved));//~2A02I~//~2A04R~
        return image;                                              //~2A02I~
    }                                                              //~2A02I~
    //*************************************************************//~2A02I~
    //*on Server                                                   //~2A02I~
    //*************************************************************//~2A02I~
    public boolean isCompleteS2C()                                 //~2A02I~
    {                                                              //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.isCompleteS2C status="+Utils.toString(statusSaved));//~2A02I~
    	boolean rc=true;                                           //~2A02I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~2A02I~
        {                                                          //~2A02I~
            for (int jj=0;jj<PLAYERS;jj++)                         //~2A02I~
            {                                                      //~2A02I~
            	int status=statusSaved[ii][jj];                    //~2A02I~
				if ((status & PDS_NEED_SEND)!=0)                        //~2A02I~//~2A04R~
					if ((status & PDS_SENT)==0)                    //~2A04I~
	                {                                                  //~2A02I~//~2A04R~
	                	rc=false;                                  //~2A02I~//~2A04R~
    	            	break;                                     //~2A02I~//~2A04R~
        	        }                                                  //~2A02I~//~2A04R~
            }                                                      //~2A02I~
        }                                                          //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.isCompleteS2C rc="+rc);//~2A02I~
        return rc;                                                 //~2A02I~
    }                                                              //~2A02I~
    //*************************************************************//~2A02I~
    //*on Server                                                   //~2A02I~
    //*************************************************************//~2A02I~
    public void updateSendImageS2C(int Psender,String PyourName)   //~2A02I~
    {                                                              //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.updateSendImageS2C sender="+Psender+",yn="+PyourName+",status="+Utils.toString(statusSaved));//~2A02I~
        int idx=getIndexByYourName(PyourName);                     //~2A02I~
        statusSaved[Psender][idx]|=PDS_SENT;                       //~2A02I~
        if (Dump.Y) Dump.println("ProfileData.updateSendImageS2C exit status="+Utils.toString(statusSaved));//~2A02I~
    }                                                              //~2A02I~
    //*************************************************************//~var8I~
    public ProfileInfo getMember(int Pidx)                         //~var8I~
    {                                                              //~var8I~
        ProfileInfo pi=members[Pidx];                              //~var8I~
        if (Dump.Y) Dump.println("ProfileData.getMember idx="+Pidx+",pi="+pi);//~var8I~
        return pi;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~2A05I~
    public void setNotifySent(int Pidx,ProfileInfo Ppi)                     //~2A05I~//~2A06R~
    {                                                              //~2A05I~
        Ppi.bmType|=PDT_NOTIFIED;      //notified to this remote device//~2A05I~
		Ppi.bmType&=~PDT_NOTIFY_RESP;  //notified response received//~2A05I~
        Arrays.fill(statusSaved[Pidx],0);                          //~2A06I~
        if (Dump.Y) Dump.println("ProfileData.setNotifySent pi="+Ppi+".statusSaved="+Utils.toString(statusSaved));//~2A05I~//~2A06R~
    }                                                              //~2A05I~
    //*************************************************************//~var8I~
    private void resetMember()                                    //~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.resetMember");       //~var8I~
        Arrays.fill(members,null);                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
//  public String getMe(String PyourName,boolean PswShowMe)        //~var8R~//~2A02R~
    public String getMe(boolean PswShowMe)                         //~2A02I~
    {                                                              //~var8I~
        String rc;                                                 //~var8I~
//      if (!PswShowMe)                                            //~var8I~//~2A05R~
//          piMe.bmType|=PDT_NOT_SHOWME;                                  //~var8I~//~2A05R~
//      rc=makeMsgPI(PyourName,piMe);                                //~var8I~//~2A02R~
        rc=makeMsgPI(piMe);                                        //~2A02I~
    	if (Dump.Y) Dump.println("ProfileData.getMe swShowMe="+PswShowMe+",rc="+rc);//~var8R~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    public Bitmap.CompressFormat getCompressFormatMe()             //~var8I~
    {                                                              //~var8I~
	    Bitmap.CompressFormat fmt=Bitmap.CompressFormat.JPEG;      //~var8I~
        if (piMe!=null && piMe.displayName!=null)                  //~var8I~
        	if (piMe.displayName.indexOf(".png")>0)                //~var8I~
			    fmt=Bitmap.CompressFormat.PNG;//~var8I~
    	if (Dump.Y) Dump.println("ProfileData.getCompressFormatMe fmt="+fmt+",piMe="+piMe);//~var8I~//~2A05R~
        return fmt;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    private String makeMsgMemberProfile(int Pidx)                  //~var8R~
    {                                                              //~var8I~
        String rc=null;                                            //~var8I~
		ProfileInfo pi=members[Pidx];                                //~var8I~
        if (pi!=null)                                              //~var8I~
	        rc=makeMsgPI(pi);                                      //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.makeMsgmemberProfile idx="+Pidx+",rc="+rc+",members="+Utils.toString(members));//~var8R~//~2A05R~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    //*msg for 78                                                  //~var8I~
    //*************************************************************//~var8I~
    public String makeMsgClientStatus()                           //~var8I~//~2A02R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.makeMsgClientStatus members="+Utils.toString(members));//~2A02I~
        String rc="";                                              //~var8I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~var8I~
        {                                                          //~var8I~
			ProfileInfo pi=members[ii];                            //~var8I~
            if (pi!=null)                                          //~var8R~
            {                                                      //~2A05I~
//  			rc+=(pi.bmType & PDT_AVAIL)==0(isSaved(pi)?"1":"0")+MSG_SEPAPP+makeMsgPI(pi);//~2A05I~
				rc+=((pi.bmType & PDT_AVAIL)!=0 ? (isSaved(pi) ? "1" : "0") : "2")+MSG_SEPAPP+makeMsgPI(pi);//~2A05I~
            }                                                      //~2A05I~
            rc+=MSG_SEPAPP3;//split for each connections           //~var8R~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.makeMsgClientStatus rc="+rc);//~var8I~//~2A02R~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~2A02I~
    //*msg for 79      image S2C                                   //~2A02I~
    //*************************************************************//~2A02I~
    public String makeMsgImageS2C(int PidxOther,int Plen)          //~2A02I~
    {                                                              //~2A02I~
    	ProfileInfo pi=members[PidxOther];                                     //~2A02I~
    	String rc=Plen+MSG_SEP+makeMsgPI(pi);                      //~2A02R~
    	if (Dump.Y) Dump.println("ProfileData.makeMsgImageS2C rc="+rc);//~2A02I~
        return rc;                                                 //~2A02I~
    }                                                              //~2A02I~
    //*************************************************************//~2A02I~
    //*msg for 80      image S2CR                                  //~2A02I~
    //*************************************************************//~2A02I~
    public String makeMsgImageS2CR(String PyourName)               //~2A02I~
    {                                                              //~2A02I~
        int idx=getIndexByYourName(PyourName);                     //~2A02R~
    	ProfileInfo pi=members[idx];                                           //~2A02I~
    	String rc=makeMsgPI(pi);                                   //~2A02I~
    	if (Dump.Y) Dump.println("ProfileData.makeMsgImageS2CR rc="+rc);//~2A02I~
        return rc;                                                 //~2A02I~
    }                                                              //~2A02I~
    //*************************************************************//~var8I~
    public int selectClientToRequestImage()                        //~var8I~
    {                                                              //~var8I~
        int rc=-1;                                                 //~var8I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~var8I~
        {                                                          //~var8I~
        	if (ii==idxServer)                                     //~var8I~
            	continue;                                          //~var8I~
			ProfileInfo pi=members[ii];                            //~var8I~
            if (pi==null)                                          //~var8I~
            	continue;                                          //~var8I~
            if (isImageRequired(pi))                               //~var8I~
            {                                                      //~var8I~
            	rc=ii;                                             //~var8I~
                break;                                             //~var8I~
            }                                                      //~var8I~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.selectClientToRequestImage idx="+rc);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    public String makeMsgRequestImageToClient(int Pidx)            //~var8I~
    {                                                              //~var8I~
        String rc=null;                                            //~var8I~
    	if (Pidx>=0)                                               //~var8I~
        {                                                          //~var8I~
			ProfileInfo pi=members[Pidx];                          //~var8I~
        	rc=makeMsgPI(pi);                                      //~var8R~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.MakeMsgRequestToClient rc="+rc);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    //*msg for 76                                                  //~2A05I~
    //*************************************************************//~2A05I~
    public String makeMsgImageC2S()                                //~var8I~
    {                                                              //~var8I~
    	String rc=null;                                            //~var8I~
    	if (idxMe>=0)                                              //~var8I~
        {                                                          //~var8I~
			ProfileInfo piMe=members[idxMe];                       //~var8I~
            if (piMe!=null && piMe.size!=0L && (piMe.bmType & PDT_NOT_SHOWME)==0)                       //~var8M~//~2A05R~
        		rc=makeMsgPI(piMe);                                //~var8I~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.MakeMsgImageC2S rc="+rc);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    public String makeMsgPI(ProfileInfo Ppi)                       //~var8I~
    {                                                              //~var8I~
    	String rc=null;                                            //~var8I~
        if (Ppi!=null)                                             //~var8I~
		    rc=makeMsgPI(Ppi.yourName,Ppi);                        //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.MakeMsgPI rc="+rc);  //~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    public String makeMsgPI(String PyourName,ProfileInfo Ppi)      //~var8I~
    {                                                              //~var8I~
    	String rc=null;                                            //~var8I~
        if (Ppi!=null)                                             //~var8I~
        	rc=PyourName+MSG_SEPAPP+Ppi.bmType+MSG_SEPAPP+Ppi.displayName+MSG_SEPAPP+Ppi.timestamp+MSG_SEPAPP+Ppi.size;//~var8I~
    	if (Dump.Y) Dump.println("ProfileData.MakeMsgPI yourName="+PyourName+",rc="+rc);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    //*for msg 77                                                  //~2A05I~
    //*************************************************************//~2A05I~
    public String makeMsgAllProfile()                              //~var8R~
    {                                                              //~var8I~
        String rc="";                                              //~var8I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~var8I~
        {                                                          //~var8I~
			String md=makeMsgMemberProfile(ii);                    //~var8R~
            if (md==null)                                          //~var8I~
            	rc+=""+MSG_SEPAPP3;                                //~var8R~
            else                                                   //~var8I~
            	rc+=md+MSG_SEPAPP3;                                //~var8R~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.makeMsgAll rc="+rc);     //~var8R~//~2A05R~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    public void putOther(int Pidx,String PyourName,int PbmType,String PdisplayName,String Ptimestamp,String Psize)//~var8R~//~2A02R~
    {                                                              //~var8I~
        ProfileInfo piMap,piNew,piRcv;                             //~var8I~
        //*****************                                        //~2A02I~
    	if (Dump.Y) Dump.println("ProfileData.putOther idx="+Pidx+",yn="+PyourName);//~2A02R~
        int idx=Pidx;                                              //~2A02I~
        if (idx<0)                                                 //~2A02I~
			idx=getIndexByYourName(PyourName);                     //~2A02R~
        String device=BTG.getName(idx);                     //~var8R~//~2A02R~
        piMap=getMap(PyourName);                                   //~var8R~
        piRcv=new ProfileInfo(device,PyourName,(PbmType & PDT_MASK_RECEIVED),PdisplayName,Ptimestamp,Psize);//~var8I~//~2A02R~
        if (piMap==null)                                           //~var8I~
        {                                                          //~var8I~
	   		putMap(false/*remove*/,PyourName,piRcv);               //~var8I~
            piNew=piRcv;                                           //~var8I~
        }                                                          //~var8I~
        else                                                       //~var8I~
        {                                                          //~var8I~
	        if (isImageChanged(piMap,piRcv))                       //~var8I~
            {                                                      //~var8I~
		   		putMap(true/*remove*/,PyourName,piRcv);                      //~var8I~
                piNew=piRcv;                                       //~var8I~
            }                                                      //~var8I~
            else                                                   //~var8I~
            {                                                      //~var8I~
                piNew=piMap;                                       //~var8I~
                if ((piRcv.bmType & PDT_NOT_SHOWME)!=0)             //~2A05I~
                	piNew.bmType|=PDT_NOT_SHOWME;                  //~2A05I~
                else                                               //~2A05I~
                	piNew.bmType&=~PDT_NOT_SHOWME;                 //~2A05I~
            }                                                      //~var8I~
        }                                                          //~var8I~
        members[idx]=piNew;                                 //~var8R~//~2A02R~
    	if (Dump.Y) Dump.println("ProfileData.putOther piNew="+piNew+",\npiAll="+toString());//~var8R~//~2A02R~
    	if (Dump.Y) Dump.println("ProfileData.putOther map="+toStringMap());//~2A02I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    private int getMemberIdx(String Pdevice)                       //~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.getMemberIdx dev="+Pdevice);//~var8I~
        int rc=BTG.search(Pdevice);                                //~var8R~
    	if (Dump.Y) Dump.println("ProfileData.getMemberIdx rc="+rc);//~var8I~
        return rc;
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    public String getOther(String PyourName)                      //~var8I~
    {                                                              //~var8I~
        ProfileInfo pi=profileMap.get(PyourName);                  //~var8I~
		String rc=makeMsgPI(pi);                                    //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.getOther pi="+pi+",rc="+rc);//~var8R~
        return rc;                                                 //~var8R~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    //*on Server                                                   //~var8I~
    //*************************************************************//~var8I~
    public boolean isReceivedAllClientProfile()                    //~var8I~
    {                                                              //~var8I~
    	boolean rc=true;                                           //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.isReceivedAllClientProfile members="+ Utils.toString(members));//~var8I~
        Members btg=BTG;                                           //~var8R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~var8I~
        {                                                          //~var8I~
        	if (btg.isConnected(ii))                                //~var8I~
            	if (members[ii]==null)                             //~var8I~
                {                                                  //~var8I~
                	rc=false;                                      //~var8I~
                    break;                                         //~var8I~
                }                                                  //~var8I~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.isReceivedAllClientProfile rc="+rc);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    //*on Server                                                   //~var8I~
    //*************************************************************//~var8I~
    public boolean isReceivedAllNotifyResp()                       //~var8I~
    {                                                              //~var8I~
    	boolean rc=true;                                           //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.isReceivedAllNotifyResp members="+ Utils.toString(members));//~var8I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~var8I~
        {                                                          //~var8I~
        	if (ii==idxMe)                                         //~var8I~
            	continue;                                          //~var8I~
            ProfileInfo pi=members[ii];                            //~var8I~
            if (pi==null)                                          //~var8I~
            	continue;                                          //~var8I~
            if ((pi.bmType & PDT_NOTIFIED)==0)                     //~var8I~
            {                                                      //~var8I~
                rc=false;                                          //~var8I~
                break;                                             //~var8I~
            }                                                      //~var8I~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.isReceivedAllClientProfile rc="+rc);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    //*chk  having saved image of other player                     //~var8R~
    //*************************************************************//~var8I~
    private boolean isSaved(ProfileInfo Ppi)                       //~var8R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.isSaved Ppi="+Ppi);  //~var8R~
        boolean rc=false;                                          //~var8I~
    	ProfileInfo pi=getMap(Ppi.yourName);                       //~var8I~
        if (pi!=null)                                              //~var8R~
        {	                                                       //~var8I~
        	if (pi.displayName!=null && pi.displayName.equals(Ppi.displayName)//~var8R~
            &&  pi.size==Ppi.size                                  //~var8I~
            &&  pi.timestamp==Ppi.timestamp                        //~var8I~
            &&  (pi.bmType & PDT_SAVED)!=0                         //~var8I~
            )                                                      //~var8I~
            {                                                      //~var8I~
	        	Ppi.bmType|=PDT_SAVED;                             //~var8I~
                rc=true;                                           //~var8I~
		    	if (Dump.Y) Dump.println("ProfileData.isSaved setSaved yn="+Ppi.yourName);//~var8R~
            }                                                      //~var8I~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.isSaved rc="+rc+",Ppi="+Ppi);//~2A02R~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    private boolean isImageRequired(ProfileInfo Ppi)               //~var8I~
    {                                                              //~var8I~
    	int type=Ppi.bmType;                                       //~var8I~
        boolean rc=(type & PDT_AVAIL)!=0 //~var8I~
        		&& (type & PDT_SAVED)==0                           //~var8R~
        		&& (type & PDT_NOT_SHOWME)==0                      //~var8R~
        		&& (type & PDT_SAVED)==0;	                       //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.isImageRequired rc="+rc+",Ppi="+Ppi);//~var8I~
        return rc;
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    private boolean isImageChanged(ProfileInfo PpiMap, ProfileInfo PpiNew)//~var8I~
    {                                                              //~var8I~
    	boolean rc=true;                                           //~2A04R~
        if (PpiMap.displayName!=null && PpiMap.displayName.equals(PpiNew.displayName)//~var8I~
        &&  PpiMap.size==PpiNew.size                               //~var8I~
        &&  PpiMap.timestamp==PpiNew.timestamp                     //~var8I~
        )                                                          //~var8I~
        {                                                          //~var8I~
			rc=false;                                              //~var8I~//~2A04R~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.isImageChanged rc="+rc+",PpiMap="+PpiMap+",PpiNew="+PpiNew);//~var8I~
        return rc;
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    private ProfileInfo getMap(String PyourName)                   //~var8I~
    {                                                              //~var8I~
        ProfileInfo pi=profileMap.get(PyourName);                         //~var8M~
    	if (Dump.Y) Dump.println("ProfileData.getMap yourName="+PyourName+",pi="+pi);//~var8I~
        return pi;                                                 //~var8I~
    }                                                              //~var8I~
    //*************************************************************//~var8I~
    private void putMap(boolean PswRemove,String PyourName,ProfileInfo Ppi)//~var8I~
    {                                                              //~var8I~
    	if (PswRemove)                                             //~var8I~
	        profileMap.remove(PyourName);                          //~var8I~
        profileMap.put(PyourName,Ppi);                                    //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.putMap swRemove="+PswRemove+",yourName="+PyourName+",pi="+Ppi);//~var8I~
     }                                                              //~var8I~
//***************************************************************  //~var8M~
	public byte[] compressBmpMe(Bitmap Pbmp)                       //~var8I~
    {                                                              //~var8M~
        Bitmap.CompressFormat fmt=getCompressFormatMe();           //~var8I~
        ByteArrayOutputStream baos=new ByteArrayOutputStream();     //~var8I~
        Pbmp.compress(fmt,100/*quality/*0-100*/,baos);            //~var8I~
        byte[] ba=baos.toByteArray();                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileData.compressBmpMe byte[].len="+ba.length+",bmp"+Pbmp+",fmt="+fmt+",ww="+Pbmp.getWidth()+"+hh="+Pbmp.getHeight()+",bmp.bytecount="+Pbmp.getByteCount());//~var8R~//~2A05R~
        return ba;
    }                                                              //~var8M~
	private int getIndexByYourName(String PyourName)               //~2A02I~
    {                                                              //~2A02I~
        int idx=BTG.searchByYourname(PyourName);                   //~2A02I~
    	if (Dump.Y) Dump.println("ProfileData.getIndexByYourName yn="+PyourName+",idx="+idx);//~2A02I~
        return idx;                                                //~2A02I~
    }                                                              //~2A02I~
    //*************************************************************//~2A03I~
    public byte[] getImage(int Pidx)                               //~2A03I~
    {                                                              //~2A03I~
        byte[] buff=null;                                          //~2A03I~
        ProfileInfo pi=members[Pidx];                              //~2A03I~
        if (pi!=null && (pi.bmType & PDT_NOT_SHOWME)==0)           //~2A03I~
        {                                                          //~2A03I~
            buff=pi.compressedImage;                               //~2A03I~
        }                                                          //~2A03I~
        if (Dump.Y) Dump.println("ProfileData.getImage idx="+Pidx+",pi="+pi+",image buff="+buff);//~2A03I~//~2A05R~
        return buff;                                               //~2A03I~
    }                                                              //~2A03I~
//****************************************************************************************//~v@@@I~
//****************************************************************************************//~v@@@I~
//****************************************************************************************//~v@@@I~
    class ProfileInfo                                              //~var8R~
    {                                                              //~1AbTI~//~v@@@I~
	    private int bmType;                                        //~var8R~
	    private String deviceName,yourName;                        //~var8R~
        private String displayName;                                //~var8I~
        private long size,timestamp;                               //~var8I~
        private byte[] compressedImage;                            //~var8I~
        //*******************************************              //~v@@@I~
        ProfileInfo(String Pdev,String Pyn,int PbmType,String PdisplayName,String Pts,String Psize)//~var8R~
        {                                                          //~v@@@I~
	    	deviceName=Pdev; yourName=Pyn; displayName=PdisplayName; timestamp=Utils.parseLong(Pts,0); size=Utils.parseLong(Psize,0); bmType=PbmType;//~var8R~//~2A06R~
            size=Utils.parseLong(Psize,0);                            //~var8I~//~2A06R~
    		if (Dump.Y) Dump.println("ProfileData.constructor deviceName="+Pdev+",yourName="+Pyn+",displayName="+PdisplayName+",timestamp="+Pts+",size="+Psize+",bmType="+PbmType);//~var8R~
        }                                                          //~v@@@I~
        //*************************************************************//~9B25I~//~var8I~
        public String toString()                             //~9B25I~//~var8I~
        {                                                              //~9B25I~//~var8I~
            String s="deviceName="+deviceName+",yourName="+yourName+",bmType=0x"+Integer.toHexString(bmType)+",displayName="+displayName+",timestamp="+timestamp+",size="+size+",compressed="+compressedImage;//~var8R~//~2A03R~
            return s;                                                  //~9B25I~//~var8I~
        }                                                              //~9B25I~//~var8I~
        //*************************************************************//~var8I~
        public void updateYourName(String PyourName)                      //~var8I~
        {                                                          //~var8I~
            yourName=PyourName;                                    //~var8R~
    		if (Dump.Y) Dump.println("ProfileData.updateYourName yn="+PyourName);//~var8R~
        }                                                          //~var8I~
    }//class                                                       //~1AbTI~//~v@@@I~
}//class                                                       //~1AbTI~//~v@@@I~

