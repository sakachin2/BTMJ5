//*CID://+vakhR~: update#= 807;                                    //~vakhR~
//**********************************************************************//~v101I~
//2022/02/20 vakh set kataagari err different from fix err         //~vakhI~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/04/20 va8i KataAgari chk for also Robot Take                //~va8iI~
//2021/04/18 va8f KataAgari chk                                    //~va8fI~
//2021/04/17 va8c for robot ron,2 hancontraint should ignore rinshan,haitei,one shot//~va8cI~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~va11R~

import com.btmtest.utils.Dump;

import static com.btmtest.game.UA.Rank.*;

public class RonResult                                             //~va11R~
{                                                                  //~0914I~
	public Rank longRank;                                          //~va11R~
	public Rank longRankFixErr;                                    //~va91I~
    public int amt,han,point;                                      //~va11R~
    public boolean swRonChkErr;                                    //~va11R~
    public int hanFixErr;       //accum of yaku fix err han        //~va91R~
    public int hanFixErrMultiWait;       //accum of kata-agari han of hanFixErr//~vakhR~
    public boolean swMultiWaitErr;                                 //+vakhI~
	//*************************************************************************//~va11I~
    public RonResult(int Pamt,int Phan,int Ppoint,Rank PlongRank)  //~va11R~
    {                                                              //~va11R~
    	amt=Pamt; han=Phan; point=Ppoint; longRank=PlongRank;      //~va11I~
        longRankFixErr=new Rank(0L);                               //~va91I~
        if (Dump.Y) Dump.println("RonResult.Constructor");         //~va11R~
    }                                                              //~va11R~
    public String toString()                                       //~va11R~
    {                                                              //~va11I~
        return "amt="+amt+",han="+han+",hanFixErr="+hanFixErr+",hanFixErrMultiwait="+hanFixErrMultiWait+",swMultiWaitErr="+swMultiWaitErr+",point="+point+",longRank="+Rank.toString(longRank)+"="+Rank.toStringName(longRank,true/*show honor*/)//~va11R~//~va91R~//+vakhR~
                +",longRankFixErr="+Rank.toString(longRankFixErr)+"="+Rank.toStringName(longRankFixErr,false/*NO show honor*/);//~va91I~
    }                                                              //~va11I~
    public void update(RonResult Pvalue)                         //~va11I~
    {                                                              //~va11I~
    	amt=Pvalue.amt;                                            //~va11I~
    	longRank=Pvalue.longRank;  //yaku                          //~va11I~
    	longRankFixErr=Pvalue.longRankFixErr;  //yaku              //~va91I~
    	han=Pvalue.han; //han                                      //~va11I~
    	point=Pvalue.point;   //fu                                 //~va11I~
        if (Dump.Y) Dump.println("RonResult.update new:"+toString());//~va11I~
    }                                                              //~va11I~
    public boolean isYakuman()                                     //~va60I~
    {                                                              //~va60I~
    	boolean rc=Rank.isYakuman(longRank);                       //~va60I~
        if (Dump.Y) Dump.println("RonResult.isYakuman rc="+rc);    //~va60I~
        return rc;
    }                                                              //~va60I~
    public int getHanExceptDora()                                  //~va60I~
    {                                                              //~va60I~
    	int ctrDora=Rank.getDora(longRank);                        //~va60I~
    	int rc=han-ctrDora;                                        //~va60I~
        if (Dump.Y) Dump.println("RonResult.getHanExceptDora rc="+rc);//~va60I~
        return rc;
    }                                                              //~va60I~
	//*************************************************************************//~va8cI~
	//*for 2 han constraint; ignore accidental yaku                //~va8cI~
	//*************************************************************************//~va8cI~
//  public int getHanExceptDoraConstraint(boolean PswIgnoreAccidental)//~va8cR~//~va8iR~
    public int getHanExceptDoraConstraint(boolean PswIgnoreAccidental,boolean PswFix2)//~va8iI~
    {                                                              //~va8cI~
    	int rc;                                                    //~va8cI~
        if (isYakuman())                                           //~va8cI~
        	rc=MIN_RANK_YAKUMAN;	//=13;    //kazoe yakuman      //~va8cI~
        else                                                       //~va8cI~
        {                                                          //~va8cI~
    		rc=getHanExceptDora();                                 //~va8cI~
            if (PswIgnoreAccidental)                               //~va8cR~
            {                                                      //~va8cR~
                if (longRank.isContains(RYAKU_REACH_JUST))         //~va8cR~
                    rc--;                                          //~va8cR~
                if (longRank.isContains(RYAKU_LAST_TAKEN))  //    =25; //haitei//~va8cR~
                    rc--;                                          //~va8cR~
                if (longRank.isContains(RYAKU_LAST_DISCARDED))  //=26; //hotei//~va8cR~
                    rc--;                                          //~va8cR~
                if (longRank.isContains(RYAKU_KAN_ADD))     //       =27; //chankan//~va8cR~
                    rc--;                                          //~va8cR~
                if (longRank.isContains(RYAKU_KAN_TAKEN))   //     =28; //rinshan//~va8cR~
                    rc--;                                          //~va8cR~
//              if (rc<0)                                          //~va8cI~//~va8iR~
//                  rc=0;                                          //~va8cI~//~va8iR~
            }                                                      //~va8cR~
            if (PswFix2)                                           //~va8iI~
            {                                                      //~va8iI~
            //*reach+take is a han for 2han constarint             //~va91I~
                if ((longRank.isContains(RYAKU_REACH) || longRank.isContains(RYAKU_REACH_DOUBLE))  			//20;//~va8iR~
                &&  longRank.isContains(RYAKU_TAKE_NOEARTH))    //24//~va8iI~
                    rc--;                                          //~va8iI~
            }                                                      //~va8iI~
            if (rc<0)                                              //~va8iI~
                rc=0;                                              //~va8iI~
        }                                                          //~va8cI~
        if (Dump.Y) Dump.println("RonResult.getHanExceptDoraConstraint rc="+rc+",swIgnoreAccidental="+PswIgnoreAccidental+",swFix2="+PswFix2);//~va8cI~//~va8iR~//~va91R~
        return rc;                                                 //~va8cI~
    }                                                              //~va8cI~
	//*************************************************************************//~va91I~
	//*Chk err of FixFirst/FixMiddle                               //~va91I~
	//*************************************************************************//~va91I~
    public int getHanExceptDoraConstraintChkFix(boolean PswIgnoreAccidental,boolean PswFix2)//~va91I~
    {                                                              //~va91I~
	    int rc=getHanExceptDoraConstraint(PswIgnoreAccidental,PswFix2);//~va91I~
        rc-=hanFixErr;                                             //~va91I~
        if (Dump.Y) Dump.println("RonResult.getHanExceptDoraConstraintChkFix rc="+rc+",hanFixErr="+hanFixErr+",hanFixErrMultiwai="+hanFixErrMultiWait);//~va91I~//~vakhR~
        return rc;                                                 //~va91I~
    }                                                              //~va91I~
	//*************************************************************************//~vakhI~
    public int getHanExceptDoraConstraintChkFixMultiWait(boolean PswIgnoreAccidental,boolean PswFix2)//~vakhI~
    {                                                              //~vakhI~
	    int rc=getHanExceptDoraConstraint(PswIgnoreAccidental,PswFix2);//~vakhI~
        rc-=hanFixErr;                                             //~vakhI~
        swMultiWaitErr=false;                                      //+vakhI~
        if (rc==0)                                                 //~vakhI~
        	if (hanFixErr==hanFixErrMultiWait)                     //~vakhI~
            	swMultiWaitErr=true;  //id of kataagari-err        //+vakhR~
        if (Dump.Y) Dump.println("RonResult.getHanExceptDoraConstraintChkFixMultiWait rc="+rc+",hanFixErr="+hanFixErr+",hanFixErrMultiwai="+hanFixErrMultiWait);//~vakhI~
        return rc;                                                 //~vakhI~
    }                                                              //~vakhI~
    public boolean isTakeAllInHand()                                   //~va8fI~
    {                                                              //~va8fI~
        boolean rc=longRank.isContains(RYAKU_TAKE_NOEARTH);	//  =24 x01000000//~va8fI~
        if (Dump.Y) Dump.println("RonResult.isTakeAllInHand rc="+rc+",longRank="+Rank.toString(longRank)+"="+Rank.toStringName(longRank,true/*show honor*/));//~va8fI~
        return rc;                                                 //~va8fI~
    }                                                              //~va8fI~
    public boolean isTakeAllInHandNoReach()                            //~va8fI~
    {                                                              //~va8fI~
        boolean rc=!longRank.isContains(RYAKU_REACH) //         =20;//~va8fI~
                && longRank.isContains(RYAKU_TAKE_NOEARTH);	//  =24 x01000000//~va8fI~
        if (Dump.Y) Dump.println("RonResult.isTakeAllInHandNoReach rc="+rc+",longRank="+Rank.toString(longRank)+"="+Rank.toStringName(longRank,true/*show honor*/));//~va8fI~
        return rc;                                                 //~va8fI~
    }                                                              //~va8fI~
}                                                                  //~va11I~
