//*CID://+va11R~: update#= 786;                                    //~va11R~
//**********************************************************************//~v101I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~va11R~

import com.btmtest.utils.Dump;

public class RonResult                                             //~va11R~
{                                                                  //~0914I~
	public Rank longRank;                                          //~va11R~
    public int amt,han,point;                                      //~va11R~
    public boolean swRonChkErr;                                    //+va11R~
	//*************************************************************************//~va11I~
    public RonResult(int Pamt,int Phan,int Ppoint,Rank PlongRank)  //~va11R~
    {                                                              //~va11R~
    	amt=Pamt; han=Phan; point=Ppoint; longRank=PlongRank;      //~va11I~
        if (Dump.Y) Dump.println("RonResult.Constructor");         //~va11R~
    }                                                              //~va11R~
    public String toString()                                       //~va11R~
    {                                                              //~va11I~
        return "amt="+amt+",han="+han+",point="+point+",longRank="+Rank.toString(longRank)+"="+Rank.toStringName(longRank);//~va11R~
    }                                                              //~va11I~
    public void update(RonResult Pvalue)                         //~va11I~
    {                                                              //~va11I~
    	amt=Pvalue.amt;                                            //~va11I~
    	longRank=Pvalue.longRank;  //yaku                          //~va11I~
    	han=Pvalue.han; //han                                      //~va11I~
    	point=Pvalue.point;   //fu                                 //~va11I~
        if (Dump.Y) Dump.println("RonResult.update new:"+toString());//~va11I~
    }                                                              //~va11I~
}                                                                  //~va11I~
