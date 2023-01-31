//*CID://+vav0R~: update#= 329;                                    //~vav0R~
//**********************************************************************//~1107I~
//2023/01/09 vav0 try xoshift as randomizer                        //~vav0I~
//**********************************************************************//~1107I~//~v106M~
package com.btmtest.utils;                                               //~1Ad8I~//~1Ac0I~//~v@@@R~
                                                                   //~v@@@I~
//**********************************************************************//~1107I~
public class URand                                                 //~vav0R~
{                                                                  //~0914I~
	long seed64,aa;                                                //+vav0R~
    int intMax;                                                    //~vav0I~
    //*************************************************            //~vav0I~
    public URand(long Pseed,int Pmax)                              //~vav0I~
    {                                                              //~vav0I~
    	seed64=Pseed;                                              //~vav0I~
    	intMax=Pmax;                                               //~vav0I~
        if (Pseed==0)                                              //~vav0I~
        seed64=System.currentTimeMillis();                         //~vav0I~
        aa=seed64;                                                 //~vav0I~
        if (Dump.Y) Dump.println("URand Pseed="+Pseed+",max="+Pmax+",aa="+aa);//~vav0I~
    }                                                              //~vav0I~
    //*************************************************            //~vav0I~
    public static URand initRandom64(long Pseed,int Pmax)          //~vav0I~
    {                                                              //~vav0I~
    	return new URand(Pseed,Pmax);                              //~vav0R~
    }                                                              //~vav0I~
    //*************************************************            //~vav0I~
    private void getNext()                                         //~vav0I~
    {                                                              //~vav0I~
    	long xx=aa;                                                //~vav0I~
        xx^=xx<<13;                                                //~vav0I~
        xx^=xx>>7;                                                 //~vav0I~
        xx^=xx<<17;                                                //~vav0I~
        aa=xx;                                                     //~vav0I~
    }                                                              //~vav0I~
    //*************************************************            //~vav0I~
    public int getRandom()                                         //~vav0I~
    {                                                              //~vav0I~
    	getNext();                                                 //~vav0I~
        int r=(int)Long.remainderUnsigned(aa,intMax);                   //+vav0R~
        if (Dump.Y) Dump.println("URand.getRandom rc="+r+",intMax="+intMax);//~vav0R~
        return r;                                                  //~vav0I~
    }                                                              //~vav0I~
    //*************************************************            //~vav0I~
    public int getRandom(int Pmax)                                 //~vav0I~
    {                                                              //~vav0I~
    	getNext();                                                 //~vav0I~
        int r=(int)(Long.remainderUnsigned(aa,Pmax));                     //+vav0R~
        if (Dump.Y) Dump.println("URand.getRandom rc="+r+",Pmax="+Pmax);//~vav0I~
        return r;                                                  //~vav0I~
    }                                                              //~vav0I~
}//class URand                                                     //~vav0R~
