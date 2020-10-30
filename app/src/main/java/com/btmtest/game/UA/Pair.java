//*CID://+va11R~: update#= 793;                                    //~va11R~
//**********************************************************************//~v101I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~va11R~

import com.btmtest.utils.Dump;

import static com.btmtest.game.GConst.*;
import static com.btmtest.game.TileData.*;

//****************************************************             //~9C11I~
public class Pair                                                  //~va11R~
{                                                                  //~0914I~
	public static final int PT_DUMMY_TOP=0;                        //~va11M~
	public static final int PT_NOTNUM =1;                          //~va11M~
	public static final int PT_NUMSAME=2;                          //~va11M~
	public static final int PT_NUMSEQ =3;                          //~va11M~
                                                                   //~va11I~
    public  int typePair,type,number,flag,dupCtr;                  //~va11R~
    public  boolean swHand;                                        //~va11R~
    public  Pair pairSame,pairSeq,parent;                          //~va11R~
   //**********************************************************   //~va11I~
    public Pair(int PtypePair,int Ptype,int Pnumber,boolean PswHand)//~va11R~
    {                                                              //~va11R~
        typePair=PtypePair; type=Ptype; number=Pnumber; swHand=PswHand;//~va11R~
        dupCtr=PAIRCTR;                                            //~va11I~
        if (Dump.Y) Dump.println("Pair.constructor "+toString(this));//~va11R~
    }                                                              //~va11R~
    //** for Earth ***********************                         //~va11I~
    public Pair(int PtypePair,int Ptype,int Pnumber,int PdupCtr,int Pflag)//for Earth//~va11R~
    {                                                              //~va11I~
	    this(PtypePair,Ptype,Pnumber,(Pflag & TDF_KAN_TAKEN)!=0);    //+va11R~
        dupCtr=PdupCtr; flag=Pflag;                                //~va11R~
                                                                   //+va11I~
        if (Dump.Y) Dump.println("Pair.constructor "+toString(this));//~va11R~
    }                                                              //~va11I~
   //**********************************************************    //~va11I~
    public static String toString(Pair Ppair)                      //~va11R~
    {                                                              //~va11R~
    	if (Ppair==null)                                           //~va11I~
        	return "null";                                         //~va11I~
        return "typePair="+Ppair.typePair+",type="+Ppair.type+",number="+Ppair.number+",dupCtr="+Ppair.dupCtr+",flag="+Ppair.flag+",swHand="+Ppair.swHand+"\n"//~va11R~
        		+",parent="+(Ppair.parent==null?"null":"type="+Ppair.parent.type+",number="+Ppair.parent.number)+"\n"//~va11R~
        		+",pairSame="+(Ppair.pairSame==null?"null":"type="+Ppair.pairSame.type+",number="+Ppair.pairSame.number)+"\n"//~va11R~
        		+",pairSeq="+(Ppair.pairSeq==null?"null":"type="+Ppair.pairSeq.type+",number="+Ppair.pairSeq.number);//~va11I~
    }                                                              //~va11R~
   //**********************************************************    //~va11I~
    public static String toString(Pair[] PpairS)                   //~va11I~
    {                                                              //~va11I~
    	if (PpairS==null)                                          //~va11I~
        	return "null";                                         //~va11I~
        int ctr=0;                                                 //~va11I~
        StringBuffer sb=new StringBuffer();                        //~va11I~
        for (Pair pair:PpairS)                                     //~va11I~
        {                                                          //~va11I~
        	sb.append("["+ctr+"]="+toString(pair)+"\n");	       //~va11R~
            ctr++;                                                 //~va11I~
        }                                                          //~va11I~
        return sb.toString();                                      //~va11I~
    }                                                              //~va11I~
   //**********************************************************    //~va11I~
    public static String toString(Pair[][] PpairSS)                //~va11I~
    {                                                              //~va11I~
    	if (PpairSS==null)                                         //~va11I~
        	return "null";                                         //~va11I~
        int ctr=0;                                                 //~va11I~
        StringBuffer sb=new StringBuffer();                        //~va11I~
        for (Pair[] pairS:PpairSS)                                 //~va11I~
        {                                                          //~va11I~
        	sb.append("["+ctr+"][]="+"\n"+toString(pairS)+"\n");   //~va11R~
            ctr++;                                                 //~va11I~
        }                                                          //~va11I~
        return sb.toString();                                      //~va11I~
    }                                                              //~va11I~
   //**********************************************************    //~va11I~
    public static String toString(Pair[][][] PpairSSS)             //~va11I~
    {                                                              //~va11I~
    	if (PpairSSS==null)                                         //~va11I~
        	return "null";                                         //~va11I~
        int ctr=0;                                                 //~va11I~
        StringBuffer sb=new StringBuffer();                        //~va11I~
        for (Pair[][] pairSS:PpairSSS)                             //~va11I~
        {                                                          //~va11I~
        	sb.append("["+ctr+"][][]="+"\n"+toString(pairSS)+"\n");//~va11R~
            ctr++;                                                 //~va11I~
        }                                                          //~va11I~
        return sb.toString();                                      //~va11I~
    }                                                              //~va11I~
}//class                                                           //~v@@@R~
