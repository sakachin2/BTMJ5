//*CID://+v@@6R~: update#= 379;                                    //~v@@@R~//~v@@6R~
//**********************************************************************//~v101I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//reset tile to new game                                           //~v@@@R~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
import com.btmtest.utils.Dump;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.game.Tiles.*;                        //~v@@@I~
import static com.btmtest.game.GConst.*;
//*************************                                        //~v@@@I~
public class TileData                                              //~v@@@R~
{                                                                  //~v@@@R~
    public int number;	//digit of man,pin,sou                     //~v@@@R~
    public int type;    //man,pin,sou,eswn,wgr                     //~v@@@R~
    public int dora;    //may be doubled                           //~v@@@R~
    public  int ctrRemain;   //may be doubled                      //~v@@@R~
    public int flag;                                              //~v@@@R~
    public int player;	//taken by(at takeOne and takeKan)         //~v@@@R~
    public int eswn;	//deal and taken                           //~v@@@I~
    public static final int TDF_LAST       =0x01;   //haitei       //~v@@@R~
    public static final int TDF_RED5       =0x02;   //man5,pin5,sou5//~v@@@R~
    public static final int TDF_DISCARDED  =0x04;  //discarded (pon,chii,minkan)//~v@@@R~
    public static final int TDF_TAKEN      =0x08;  //taken from stock(include  ankan)//~v@@@R~
    public static final int TDF_TAKEN_RIVER=0x10;  //taken from river//~v@@@R~
    public static final int TDF_PON             =0x20;  //pair by PON//~v@@@I~
    public static final int TDF_CHII            =0x40;  //pair by CHII//~v@@@I~
    public static final int TDF_RON             =0x80;             //~v@@6R~
    public static final int TDF_KAN_TAKEN       =0x0100;//pair by KAN_TAKEN//~v@@@R~//~v@@6R~
    public static final int TDF_KAN_RIVER       =0x0200;//pair by RIVER taken//~v@@@R~
    public static final int TDF_KAN_ADD         =0x0400;//pair by add KAN on Pon pair//~v@@@R~
    public static final int TDF_KAN_ADDED_TILE  =0x0800;//         //~v@@@R~
    public static final int TDF_KAN_FACEDOWN    =0x1000;//         //~v@@@R~
    public static final int TDF_REACH           =0x2000;//tile at reach//~v@@@R~
//  public static final int TDF_SELECTED        =0x4000;//touch selection//~v@@@I~//~v@@6R~
    public static final int TDF_LOCKED          =0x8000;//delayed take//~v@@@I~
//  public static final int TDF_CHII_DECLARED   =0x010000;  //declared,executed after some delay if if not intercepted Pon,Kan,Ron//~v@@6R~
    public static final int TDF_KAN_RINSHAN     =0x010000;  //taken from wanpai at kan//~v@@6I~
    public static final int TDF_LOCKED_PONKAN   =0x020000;         //~v@@6I~
                                                                   //~v@@@I~
    public static final int TDF_INTERCEPTED=(TDF_PON | TDF_CHII | TDF_RON | TDF_KAN_RIVER);//~v@@@I~
                                                                   //~v@@@I~
    public static final int RED5CTR=2;                             //~v@@@I~
    //*****************************************************        //~v@@@I~
//  public TileData(int Pnumber,int Ptype)                         //~v@@@R~
    public TileData(int Ptype,int Pnumber)                         //~v@@@I~
    {                                                              //~v@@@R~
    	number=Pnumber; type=Ptype;                                //~v@@@R~
        ctrRemain=PIECE_DUPCTR;                                    //~v@@@R~
    }                                                              //~v@@@R~
    //*****************************************************        //~v@@@I~
//  public TileData(int Pnumber,int Ptype,boolean Pdora)           //~v@@@R~
    public TileData(int Ptype,int Pnumber,boolean Pdora)           //~v@@@I~
    {                                                              //~v@@@I~
    	number=Pnumber; type=Ptype; dora=Pdora?1:0;                //~v@@@I~
        ctrRemain=PIECE_DUPCTR;                                    //~v@@@I~
        if (Pdora)                                                 //~v@@@I~
	        flag=TDF_RED5;                                         //~v@@@I~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
    //*for UnitTest                                                //~v@@@I~
    //*****************************************************        //~v@@@I~
//  public TileData(int Pnumber,int Ptype,boolean Pdora,int Pplayer)//~v@@@R~
    public TileData(int Ptype,int Pnumber,boolean Pdora,int Pplayer)//~v@@@I~
    {                                                              //~v@@@I~
    	number=Pnumber; type=Ptype; dora=Pdora?1:0;                //~v@@@I~
        ctrRemain=PIECE_DUPCTR;                                    //~v@@@I~
        if (Pdora)                                                 //~v@@@I~
	        flag=TDF_RED5;                                         //~v@@@I~
        player=Pplayer;                                            //~v@@@I~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@6I~
    public TileData(int Ptype,int Pnumber,int Pflag,int PctrRemain,int Peswn)//~v@@6I~
    {                                                              //~v@@6I~
    	number=Pnumber; type=Ptype; flag=Pflag; ctrRemain=PctrRemain; eswn=Peswn;//~v@@6I~
    }                                                              //~v@@6I~
    //*****************************************************        //~v@@@I~
    public TileData(boolean PswEswnToPlayer,int[] Pintp/*type,num,red5*/,int Ppos/*type pos*/)//~v@@@I~//~v@@6R~
    {                                                              //~v@@@I~
//      number=Pintp[Ppos+1]; type=Pintp[Ppos]; flag=Pintp[Ppos+2];//~v@@@R~//~v@@6R~
        this(Pintp[Ppos]/*type*/,Pintp[Ppos+1]/*number*/,Pintp[Ppos+2]/*flag*/,Pintp[Ppos+3]/*ctrRemain*/,Pintp[Ppos+4]/*eswn*/);//~v@@6I~
        if (PswEswnToPlayer)                                       //~v@@6I~
        	player=Accounts.eswnToPlayer(eswn);                    //~v@@6I~
        if (Dump.Y) Dump.println("TileData constructor by int[] pos="+Ppos+",swEswnToPlayer="+PswEswnToPlayer+"td:"+toString());//~v@@6R~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
    public TileData(int[] Pintp/*type,num,red5*/,int Ppos/*type pos*/,boolean Pdora)//~v@@@I~
    {                                                              //~v@@@I~
//      this(Pintp[Ppos+1],Pintp[Ppos],Pdora);                     //~v@@@R~
        this(Pintp[Ppos],Pintp[Ppos+1],Pdora);                     //~v@@@I~
        if (Dump.Y) Dump.println("TileData constructor int[]+dora pos="+Ppos+",type="+type+",number="+number+",flag="+flag);//~v@@@I~
    }                                                              //~v@@@I~
//    //*****************************************************      //~v@@@R~
//    public TileData(int[] Pintp/*type,num,red5*/,int Ppos/*type pos*/,boolean PswDora)//~v@@@R~
//    {                                                            //~v@@@R~
//        this(Pintp[Ppos+1]/*number*/,Pintp[Ppos]/*type*/,PswDora);//~v@@@R~
//    }                                                            //~v@@@R~
    //*****************************************************        //~v@@@I~
    //* for clone                                                  //~v@@@I~
    //*****************************************************        //~v@@@I~
     public TileData(TileData Ptd)                                 //~v@@@R~
     {                                                             //~v@@@R~
        number=Ptd.number; type=Ptd.type; dora=Ptd.dora;           //~v@@@R~
        ctrRemain=Ptd.ctrRemain;                                   //~v@@@R~
     	flag=Ptd.flag;  player=Ptd.player;                         //~v@@@R~
     }                                                             //~v@@@R~
    //*****************************************************        //~v@@@I~
     public String toString()                                      //~v@@@I~
     {                                                             //~v@@@I~
        return (" t="+type+",n="+number+",f=0x"+Integer.toHexString(flag)+",c="+ctrRemain+",p="+player+",e="+eswn);//~v@@@I~//~v@@6R~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@6I~
     public static String toString(TileData Ptd)                   //~v@@6I~
     {                                                             //~v@@6I~
        return (Ptd==null ? "null" : Ptd.toString());              //~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@@I~
     public static String toString(TileData[] Ptds)                //~v@@@I~
     {                                                             //~v@@@I~
     	if (Ptds==null)                                            //~v@@6I~
        	return "null";                                         //~v@@6I~
     	StringBuffer sb=new StringBuffer();                        //~v@@@I~
        int ii=0;                                                  //~v@@@I~
     	for (TileData td:Ptds)                                     //~v@@@I~
        {                                                          //~v@@@I~
            sb.append("\n["+ii+"]:");                              //~v@@@I~
        	if (td==null)                                          //~v@@@I~
            	sb.append("null");                                 //~v@@@I~
            else                                                   //~v@@@I~
            	sb.append(td.toString());                          //~v@@@I~
            ii++;                                                  //~v@@@I~
        }                                                          //~v@@@I~
        return sb.toString();                                      //~v@@@I~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@6I~
     public static String toString(TileData[][] Ptdss)             //~v@@6I~
     {                                                             //~v@@6I~
     	StringBuffer sb=new StringBuffer();                        //~v@@6I~
     	if (Ptdss==null)                                           //~v@@6I~
        	return "null";                                         //~v@@6I~
        int ctr=0;                                                 //~v@@6I~
        for (TileData[] ptds:Ptdss)                                //~v@@6I~
        {                                                          //~v@@6I~
            sb.append("\n[["+ctr+"]]:");                           //~v@@6I~
        	if (ptds!=null)                                        //~v@@6R~
	            sb.append(toString(ptds));                         //~v@@6I~
            ctr++;                                                 //~v@@6I~
        }                                                          //~v@@6I~
        return sb.toString();                                      //~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@6I~
     public static String toString(TileData[] Ptds,int Ppos,int Pctr)//~v@@6I~
     {                                                             //~v@@6I~
     	StringBuffer sb=new StringBuffer();                        //~v@@6I~
        int ctr=0;                                                 //~v@@6I~
     	for (int ii=Ppos;ii<Ppos+Pctr;ii++)                        //~v@@6I~
        {                                                          //~v@@6I~
            sb.append("\n["+ctr+"]:");                             //~v@@6I~
        	if (Ptds[ii]==null)                                      //~v@@6I~
            	sb.append("null");                                 //~v@@6I~
            else                                                   //~v@@6I~
            	sb.append(Ptds[ii].toString());                      //~v@@6I~
            ctr++;                                                 //~v@@6I~
        }                                                          //~v@@6I~
        return sb.toString();                                      //~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@@I~
    //* for clone                                                  //~v@@@I~
    //*****************************************************        //~v@@@I~
     public void setRed5(boolean Ptrue)                            //~v@@@I~
     {                                                             //~v@@@I~
       	if (Ptrue)                                                 //~v@@@I~
       	{                                                          //~v@@@I~
       		flag|=TDF_RED5;                                        //~v@@@I~
       		dora=1;                                                //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
       	{                                                          //~v@@@I~
       		flag&=~TDF_RED5;                                       //~v@@@I~
       		dora=0;                                                //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("TileData.setRed5 parm="+Ptrue);  //~v@@6I~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@@I~
    //* for clone                                                  //~v@@@I~
    //*****************************************************        //~v@@@I~
     public boolean isRed5()                                       //~v@@@I~
     {                                                             //~v@@@I~
        if (Dump.Y) Dump.println("TileData.isRed5="+((flag & TDF_RED5)!=0));//~v@@@I~
       	return (flag & TDF_RED5)!=0;                               //~v@@@I~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@@I~
    public int setDora()                                           //~v@@@R~
    {                                                              //~v@@@R~
        dora++;                                                    //~v@@@R~
        return dora;                                               //~v@@@R~
    }                                                              //~v@@@R~
    //*****************************************************        //~v@@@I~
    //* taken from stock                                           //~v@@@R~
    //*****************************************************        //~v@@@I~
//     public void setTaken(int Pplayer)                           //~v@@@R~
//     {                                                           //~v@@@R~
//        flag|=TDF_TAKEN;                                         //~v@@@R~
//        player=Pplayer;                                          //~v@@@R~
//        if (Dump.Y) Dump.println("TileData.setTaken player="+Pplayer+",td:"+toString());//~v@@6R~
//     }                                                           //~v@@@R~
    //*****************************************************        //~v@@@I~
     public void setTaken()                                        //~v@@@I~
     {                                                             //~v@@@I~
        flag|=TDF_TAKEN;                                           //~v@@@I~
        if (Dump.Y) Dump.println("TileData.setTaken td:"+toString());//~v@@6R~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@6I~
     public boolean isTaken()                                      //~v@@6R~
     {                                                             //~v@@6I~
        boolean rc=(flag & TDF_TAKEN)!=0;                          //~v@@6I~
        if (Dump.Y) Dump.println("TileData.isTaken rc="+rc+",td:"+toString());//~v@@6I~
        return rc;                                                 //~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************      //~v@@@R~//~v@@6R~
     public void setReach()                                      //~v@@@R~//~v@@6R~
     {                                                           //~v@@@R~//~v@@6R~
        flag|=TDF_REACH;                                         //~v@@@R~//~v@@6R~
        if (Dump.Y) Dump.println("TileData.setReach td:"+toString());//~v@@6R~
     }                                                           //~v@@@R~//~v@@6R~
    //*****************************************************        //~v@@6I~
     public void resetReach()                                      //~v@@6I~
     {                                                             //~v@@6I~
        flag&=~TDF_REACH;                                          //~v@@6I~
        if (Dump.Y) Dump.println("TileData.resetReach td:"+toString());//~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@6I~
     public boolean isReached()                                    //~v@@6I~
     {                                                             //~v@@6I~
        boolean rc=(flag & TDF_REACH)!=0;                          //~v@@6I~
        if (Dump.Y) Dump.println("TileData.isReached rc="+rc+",td:"+toString());//~v@@6I~
        return rc;
     }                                                             //~v@@6I~
//    //*****************************************************      //~v@@6R~
//     public void setChiiDeclared()                               //~v@@6R~
//     {                                                           //~v@@6R~
//        flag|=TDF_CHII_DECLARED;                                 //~v@@6R~
//        if (Dump.Y) Dump.println("TileData.setChiiDeclared td:"+toString());//~v@@6R~
//     }                                                           //~v@@6R~
//    //*****************************************************      //~v@@6R~
//     public boolean isChiiDeclared()                             //~v@@6R~
//     {                                                           //~v@@6R~
//        boolean rc=(flag & TDF_CHII_DECLARED)!=0;                //~v@@6R~
//        if (Dump.Y) Dump.println("TileData.isChiiDeclared rc="+rc+",td:"+toString());//~v@@6R~
//        return rc;                                               //~v@@6R~
//     }                                                           //~v@@6R~
    //*****************************************************        //~v@@6I~
     public void setKanAddedTile()                                 //~v@@6R~
     {                                                             //~v@@6I~
       	flag|=TDF_KAN_ADDED_TILE;                                  //~v@@6I~
        if (Dump.Y) Dump.println("TileData.setKanAddedTile td:"+toString());//~v@@6R~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@6I~
     public boolean isKanAddedTile()                               //~v@@6R~
     {                                                             //~v@@6I~
       	boolean rc=(flag & TDF_KAN_ADDED_TILE)!=0;                 //~v@@6I~
        if (Dump.Y) Dump.println("TileData.isKanAddedTile td:"+toString());//~v@@6I~
        return rc;                                                 //~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@@I~
    //* discarded was taken retur true if it is reach tile         //~v@@@R~
    //*****************************************************        //~v@@@I~
     public void setTakenRiver()                                   //~v@@@R~
     {                                                             //~v@@@I~
       	flag|=TDF_TAKEN_RIVER;                                      //~v@@@I~
        if (Dump.Y) Dump.println("TileData.setTakenRiver td:"+toString());//~v@@6R~
     }                                                             //~v@@@I~
    //*************************************************************************//~v@@@I~
    public static void setTakenRiver(TileData[] Ptds)              //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TileData.setTakenRiver in ="+TileData.toString(Ptds));//~v@@@I~
        for (TileData td:Ptds)                                     //~v@@@I~
        {                                                          //~v@@@I~
        	if (td.isDiscarded())                                  //~v@@@I~
				td.setTakenRiver();                                //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
    //* set Discarded                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
     public void setDiscarded()                                    //~v@@@I~
     {                                                             //~v@@@I~
        flag|=TDF_DISCARDED;                                       //~v@@@R~
        flag &= ~(TDF_TAKEN | TDF_KAN_RINSHAN);                     //~v@@6I~
        setLock(true);        		//take available after some delayed  //~v@@@R~//~v@@6R~
        setLockPonKan(true);        //Pon available after some delayed//~v@@6I~
        if (Dump.Y) Dump.println("TileData.setDiscarded yd:"+toString());//~v@@6R~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@@I~
     public boolean isDiscarded()                                  //~v@@@I~
     {                                                             //~v@@@I~
        boolean rc=(flag & TDF_DISCARDED)!=0;                      //~v@@@I~
        if (Dump.Y) Dump.println("TileData.isDiscarded rc="+rc+",td:"+toString());//~v@@6R~
        return rc;                                                 //~v@@@I~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@6I~
     public void setRon()                                          //~v@@6I~
     {                                                             //~v@@6I~
        flag|=TDF_RON;                                             //~v@@6I~
        if (Dump.Y) Dump.println("TileData.setRon td:"+toString());//~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@6I~
     public void resetRon()                                        //~v@@6I~
     {                                                             //~v@@6I~
        flag&=~TDF_RON;                                            //~v@@6I~
        if (Dump.Y) Dump.println("TileData.resetRon td:"+toString());//~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@6I~
     public boolean isRon()                                        //~v@@6I~
     {                                                             //~v@@6I~
        boolean rc=(flag & TDF_RON)!=0;                            //~v@@6I~
        if (Dump.Y) Dump.println("TileData.isRon rc="+rc+",td:"+toString());//~v@@6I~
        return rc;                                                 //~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@@I~
    //*block take/chii                                             //~v@@6I~
    //*****************************************************        //~v@@6I~
     public boolean setLock(boolean PswOn)                         //~v@@@I~
     {                                                             //~v@@@I~
     	boolean rc=isLocked();                                      //~v@@@I~
     	if (PswOn)                                                 //~v@@@I~
        	flag |= TDF_LOCKED;                                    //~v@@@R~
        else                                                       //~v@@@I~
        	flag &= ~TDF_LOCKED;                                   //~v@@@R~
        if (Dump.Y) Dump.println("TileData.setLock oldLockStatus="+rc+",req="+PswOn+",td:"+toString());//~v@@@I~//~v@@6R~
        return rc;                                                 //~v@@@I~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@6I~
    //*block pon/kan                                               //~v@@6I~
    //*****************************************************        //~v@@6I~
     public boolean setLockPonKan(boolean PswOn)                   //~v@@6I~
     {                                                             //~v@@6I~
     	boolean rc=isLockedPonKan();                               //~v@@6I~
     	if (PswOn)                                                 //~v@@6I~
        	flag |= TDF_LOCKED_PONKAN;                             //~v@@6I~
        else                                                       //~v@@6I~
        	flag &= ~TDF_LOCKED_PONKAN;                            //~v@@6I~
        if (Dump.Y) Dump.println("TileData.setLockPonKan oldLockStatus="+rc+",req="+PswOn+",td:"+toString());//~v@@6I~
        return rc;                                                 //~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@@I~
    //*block take/chii                                             //~v@@6I~
    //*****************************************************        //~v@@6I~
     public boolean isLocked()                                     //~v@@@I~
     {                                                             //~v@@@I~
     	boolean rc=(flag & TDF_LOCKED)!=0;                         //~v@@@R~
        if (Dump.Y) Dump.println("TileData.isLocked LockStatus="+rc+",td:"+toString());//~v@@@I~//~v@@6R~
        return rc;                                                 //~v@@@I~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@6I~
    //*block pon/kan                                               //~v@@6I~
    //*****************************************************        //~v@@6I~
     public boolean isLockedPonKan()                               //~v@@6I~
     {                                                             //~v@@6I~
     	boolean rc=(flag & TDF_LOCKED_PONKAN)!=0;                  //~v@@6I~
        if (Dump.Y) Dump.println("TileData.isLockPonKan LockStatus="+rc+",td:"+toString());//~v@@6R~
        return rc;                                                 //~v@@6I~
     }                                                             //~v@@6I~
    //*****************************************************        //~v@@@I~
    //* set Kan by taken tile(minkan) to draw upside down          //~v@@@I~
    //*****************************************************        //~v@@@I~
     public void setKanTakenRinshan()                                     //~v@@@I~//~v@@6R~
     {                                                             //~v@@@I~
//      flag|=TDF_KAN_TAKEN;                                       //~v@@@I~//~v@@6R~
    	flag|=(TDF_TAKEN | TDF_KAN_RINSHAN);//     =0x010000;  //taken from wanpai at kan//~v@@6I~
        if (Dump.Y) Dump.println("TileData.setKanTakenRinshan td:"+toString());//~v@@6R~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@@I~
    //* set Kan by taken tile(minkan) to draw upside down          //~v@@@I~
    //*****************************************************        //~v@@@I~
     public void setKanFaceDown()                                  //~v@@@I~
     {                                                             //~v@@@I~
        flag|=TDF_KAN_FACEDOWN;                                    //~v@@@I~
        if (Dump.Y) Dump.println("TileData.setFaceDown td:"+toString());//~v@@6R~
     }                                                             //~v@@@I~
    //*****************************************************        //~v@@@I~
    //*make clone                                                  //~v@@@I~
    //*****************************************************        //~v@@@I~
    public static TileData newInstance(TileData Ptd)               //~v@@@I~
    {                                                              //~v@@@I~
        return new TileData(Ptd);                                  //~v@@@R~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
    public void addFlag(int Pflag)                                     //~v@@@I~
    {                                                              //~v@@@I~
        flag|=Pflag;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
    public void setPlayer(int Pplayer)                             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TileData.setPlayer player="+Pplayer);//~v@@6R~
	    setPlayer(Pplayer,Pplayer);                                //~v@@@I~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
    public void setPlayer(int Pplayer,int Peswn)                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TileData.setPlayer Pplayer="+Pplayer+",Peswn="+Peswn+"td:"+toString());//~v@@6R~
        player=Pplayer;                                            //~v@@@I~
        eswn=Peswn;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
    public int  getPlayer()                                        //~v@@@I~
    {                                                              //~v@@@I~
        return player;                                             //~v@@@I~
    }                                                              //~v@@@I~
//    //*****************************************************        //~v@@@I~//~v@@6R~
//    public boolean isSelected()                                    //~v@@@R~//~v@@6R~
//    {                                                              //~v@@@I~//~v@@6R~
//        boolean rc=(flag & TDF_SELECTED)!=0;                       //~v@@@I~//~v@@6R~
//        if (Dump.Y) Dump.println("TileData.isSelected="+rc+",td:"+toString());//~v@@@R~//~v@@6R~
//        return rc;                                                 //~v@@@I~//~v@@6R~
//    }                                                              //~v@@@I~//~v@@6R~
    //*****************************************************        //~v@@6I~
    public boolean isKanRiver()                                    //~v@@6I~
    {                                                              //~v@@6I~
        boolean rc=(flag & TDF_KAN_RIVER)!=0;	//       =0x0200;//pair by RIVER taken//~v@@6I~
        if (Dump.Y) Dump.println("TileData.isKanRiver="+rc+",td:"+toString());//~v@@6I~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
    //*****************************************************        //~v@@6I~
    public boolean isKanAdd()                                      //~v@@6I~
    {                                                              //~v@@6I~
        boolean rc=(flag & TDF_KAN_ADD)!=0;	//       =0x0200;//pair by RIVER taken//~v@@6I~
        if (Dump.Y) Dump.println("TileData.isKanAdd="+rc+",td:"+toString());//~v@@6I~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
    //*****************************************************        //~v@@6I~
    public boolean isKanTaken()                                    //~v@@6I~
    {                                                              //~v@@6I~
        boolean rc=(flag & TDF_KAN_TAKEN)!=0;	//       =0x0200;//pair by RIVER taken//~v@@6I~
        if (Dump.Y) Dump.println("TileData.isKanTaken="+rc+",td:"+toString());//~v@@6I~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
    //*****************************************************        //~v@@6I~
//  public boolean isKanTaken()                                    //~v@@6R~
    public boolean isKanTakenRinshan()                             //~v@@6I~
    {                                                              //~v@@6I~
//      boolean rc=(flag & TDF_KAN_TAKEN)!=0;	//       =0x0200;//pair by RIVER taken//~v@@6R~
        boolean rc=(flag & TDF_KAN_RINSHAN)!=0;	//       =0x0200;//pair by RIVER taken//~v@@6I~
        if (Dump.Y) Dump.println("TileData.isKanTakenRinshan="+rc+",td:"+toString());//~v@@6R~
        return rc;                                                 //~v@@6I~
    }                                                              //~v@@6I~
//    //*****************************************************        //~v@@@I~//~v@@6R~
//    public void setSelected(boolean PswSelected)                     //~v@@@I~//~v@@6R~
//    {                                                              //~v@@@I~//~v@@6R~
//        if (Dump.Y) Dump.println("TileData.setSelected td:"+toString());//~v@@@R~//~v@@6R~
//        if (PswSelected)                                           //~v@@@I~//~v@@6R~
//            flag |= TDF_SELECTED;                                  //~v@@@I~//~v@@6R~
//        else                                                       //~v@@@I~//~v@@6R~
//            flag &= ~TDF_SELECTED;                                 //~v@@@I~//~v@@6R~
//        if (Dump.Y) Dump.println("TileData.setSelected new flag="+flag);//~v@@@I~//~v@@6R~
//    }                                                              //~v@@@I~//~v@@6R~
    //*****************************************************        //~v@@@I~
    public static int TDCompare(TileData Ptd1,TileData Ptd2)       //~v@@@R~
    {                                                              //~v@@@I~
	    return TDCompare(Ptd1,Ptd2,false/*PswCtr*/);              //~v@@@I~//~v@@6R~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
    //*compare type and number only                                //+v@@6I~
    //*rc:true:match                                               //+v@@6I~
    //*****************************************************        //+v@@6I~
    public static boolean  TDCompareTN(TileData Ptd1,TileData Ptd2)     //~v@@@I~
    {                                                              //~v@@@I~
        boolean rc=(Ptd1.type==Ptd2.type) && (Ptd1.number==Ptd2.number);//~v@@@I~
        if (Dump.Y) Dump.println("TileData.TDCompareTN rc="+rc+",td1:"+Ptd1.toString()+",td2:"+Ptd2.toString());   //~v@@@I~//~v@@6R~
        return rc;
    }                                                              //~v@@@I~
    //*****************************************************************************        //~v@@@I~//~v@@6R~
    //*compare type/number/red5/ctrRemain                          //~v@@6I~
    //*****************************************************************************//~v@@6I~
    public static int TDCompare(TileData Ptd1,TileData Ptd2,boolean PswCtr/*compare ctrRemain*/)//~v@@@I~//~v@@6R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TileData.TDCompareTN swCtr="+PswCtr+",td1:"+Ptd1.toString()+",td2:"+Ptd2.toString());//~v@@6I~
        if (Ptd1.type!=Ptd2.type)                                  //~v@@@I~
            return Ptd1.type-Ptd2.type;                            //~v@@@I~
        if (Ptd1.number!=Ptd2.number)                              //~v@@@I~
            return Ptd1.number-Ptd2.number;                        //~v@@@I~
        if ((Ptd1.flag & TDF_RED5)!=(Ptd2.flag & TDF_RED5))        //~v@@@I~
            return -((Ptd1.flag & TDF_RED5)-(Ptd2.flag & TDF_RED5));//~v@@@I~
        if (!PswCtr)                                              //~v@@@I~//~v@@6R~
        	return 0;                                              //~v@@@I~
        return Ptd1.ctrRemain-Ptd2.ctrRemain;                      //~v@@@I~
    }                                                              //~v@@@I~
    //*****************************************************        //~v@@@I~
    //*sort generate shallow copy                                  //~v@@@I~
    //*****************************************************        //~v@@@I~
    public static void sort(TileData[] Ptd,int Pfrom,int Pto)      //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y)                                                //~v@@6R~
        {                                                          //~v@@@R~
            for (int ii=Pfrom;ii<Pto;ii++)                         //~v@@@R~
            {                                                      //~v@@@R~
                TileData td=Ptd[ii];                               //~v@@@R~
                Dump.println("TileData sort before ctrRemain="+td.ctrRemain+",td:"+td.toString());//~v@@@R~
            }                                                      //~v@@@R~
        }                                                          //~v@@@R~
		Arrays.sort(Ptd,Pfrom,Pto,new TDComp());  //~v@@@I~
        if (Dump.Y)                                                //~v@@6R~
        {                                                          //~v@@@R~
            for (int ii=Pfrom;ii<Pto;ii++)                         //~v@@@R~
            {                                                      //~v@@@R~
                TileData td=Ptd[ii];                               //~v@@@R~
                Dump.println("TileData sort after ctrRemain="+td.ctrRemain+",td:"+td.toString());//~v@@@R~
            }                                                      //~v@@@R~
        }                                                          //~v@@@R~
    }                                                              //~v@@@I~
    static class TDComp implements Comparator<TileData>                   //~v@@@I~
    {                                                              //~v@@@I~
    	@Override                                                  //~v@@@I~
        public int compare(TileData Ptd1,TileData Ptd2)            //~v@@@I~
        {                                                          //~v@@@I~
        	int rc=TDCompare(Ptd1,Ptd2,true);                      //~v@@@R~
            return rc;                                             //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@@I~
    //*copy between search found(match with type,number,red5)      //~v@@@I~
    //*********************************************************************//~v@@@I~
	public static void copyTD(TileData PtdTo,TileData PtdFrom)     //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Players.copyTD in to:"+PtdTo.toString()+",from:"+PtdFrom.toString());//~v@@@I~
        PtdTo.ctrRemain=PtdFrom.ctrRemain;                         //~v@@@I~
        PtdTo.flag=PtdFrom.flag;                                   //~v@@@I~
        PtdTo.player=PtdFrom.player;                               //~v@@@I~
        PtdTo.eswn=PtdFrom.eswn;                                   //~v@@@I~
        if (Dump.Y) Dump.println("Players.copyTD out to:"+PtdTo.toString());//~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************************//~v@@6I~
    //*copy between search found(match with type,number,red5)      //~v@@6I~
    //*********************************************************************//~v@@6I~
	public static String toSendText(TileData Ptd)                  //~v@@6I~
    {                                                              //~v@@6I~
        String str=Ptd.type+MSG_SEPAPP+Ptd.number+MSG_SEPAPP+Ptd.flag+MSG_SEPAPP+Ptd.ctrRemain+MSG_SEPAPP+Ptd.eswn;//~v@@6I~
		if (Dump.Y) Dump.println("TileData.strTD str="+str);       //~v@@6R~
        return str;                                                //~v@@6I~
    }                                                              //~v@@6I~
}//class TileData                                                  //~v@@@I~
