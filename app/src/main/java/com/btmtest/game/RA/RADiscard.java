//*CID://+vak2R~: update#=  76;                                    //~vak2R~
//**********************************************************************//~v101I~
//2022/02/15 vak2 Test option, specify discard tile                //~vak2I~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//**********************************************************************//~1107I~
package com.btmtest.game.RA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
import android.graphics.Point;

import com.btmtest.TestOption;
import com.btmtest.game.Accounts;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.TestOption.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~

//********************************************************************************************//~v@@5R~
public class RADiscard                                               //~v@@@R~//~va60R~
{                                                                  //~0914I~
	private RoundStat RS;//~va60I~                                 //~1113R~
    private RADSmart RADS;                                         //~1113M~
    private boolean[] btsOrphan;                                   //~1111I~//~1113R~//~1116R~
    private int ctrOrphan;                                         //~1111I~//~1113R~//~1116R~
//  private TileData[][] tdssHand;                                 //~1111I~//~1112R~
    public  TileData[] tdsHand;      //max 14 entry                //~1115R~
    private  int ctrHand;                                               //~1115R~
//    public  int[] itsHand,itsHandRed;   //34 entry                             //~1112I~//~1113R~//~1129R~
    public  int[] itsHand;   //34 entry                            //~1129I~
    public  int playerDiscard;                                     //~1112I~//~1113R~
//*************************                                        //~v@@@I~
	public RADiscard()                                               //~v@@@R~//~va60R~//~1113R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("RADiscard.Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~va60R~
        AG.aRADiscard=this;                                          //~v@@@I~//~va60R~
        init();                                                    //~va60I~
    }                                                              //~0914I~//~v@@@R~
    //*********************************************************    //~va60I~
    private void  init()                                           //~va60I~
    {                                                              //~va60I~
    	RS=AG.aRoundStat;                                          //~1111I~
    	RADS=new RADSmart();                                       //~1113I~
    }                                                              //~va60I~
    //*********************************************************    //~va60I~
    //*from Robot.afterTakeOne                                     //~1113I~
    //*********************************************************    //~1113I~
    public  TileData selectDiscard(int Peswn,TileData PtdTaken/*null after when Pon,Chii*/)              //~va60R~
    {                                                              //~va60I~
        TileData tdDiscard;                                        //~va60I~
        //********************                                     //~1111I~
        playerDiscard= Accounts.eswnToPlayer(Peswn);                 //~1112I~
        if (Dump.Y) Dump.println("RADiscard.selectDiscard swThinkRobot="+RS.swThinkRobot+",eswn="+Peswn+",player="+playerDiscard+",tdTaken="+Utils.toString(PtdTaken));//~va60R~//~1112R~//~1126R~
        itsHand=RS.getItsHandEswn(Peswn);   //including taken      //~1111I~
//        itsHandRed=RS.getItsHandRedEswn(Peswn); //including taken  //~1112I~//~1113M~//~1129R~
        tdsHand=AG.aPlayers.getHands(playerDiscard);                       //~1115I~//~1119R~
        ctrHand=tdsHand.length;                                    //~1115I~
//      tdssHand=RS.getTdssHandEswn(Peswn); //including taken      //~1111I~//~1112R~
                                                                   //~1111I~
        try                                                        //~1126I~
        {                                                          //~1126I~
            if ((TestOption.option5 & TO5_SETDISCARD)!=0)           //~vak2I~
                tdDiscard=selectTestDiscard(Peswn,PtdTaken,tdsHand);//~vak2R~
            else                                                   //~vak2I~
                tdDiscard=null;                                    //~vak2I~
          if (tdDiscard==null)                                     //~vak2R~
          {                                                        //~vak2I~
            if (!RS.swThinkRobot)                                         //~va60I~//~1111R~//~1126R~
                tdDiscard=selectDull(Peswn,PtdTaken);                    //~va60I~//~1111R~//~1116R~//~1126R~
            else                                                       //~va60I~//~1126R~
                tdDiscard=selectSmart(Peswn,PtdTaken); //null if Kan called            //~va60I~//~1116R~//~1124R~//~1126R~
          }                                                        //~vak2I~
        }                                                          //~1126I~
        catch(Exception e)                                         //~v@@@I~//~1123I~//~1126I~
        {                                                          //~v@@@I~//~1123I~//~1126I~
        	Dump.println(e,"RADiscard.selectDiscard");         //~v@@@I~     //~1123I~//~1126I~
            tdDiscard=PtdTaken;                                    //~1126I~
            if (tdDiscard==null)                                   //~1126I~
	        	tdDiscard=tdsHand[0];                              //~1126I~
        }                                                          //~1126I~
        if (Dump.Y) Dump.println("RADiscard.selectDiscard tdDiscard="+TileData.toString(tdDiscard));//~va60I~//~1124R~
        tdsHand=null;	//java free                                //~1115I~
        itsHand=null;                                               //~1122I~
//        itsHandRed=null;                                            //~1122I~//~1129R~
        return tdDiscard;
    }                                                              //~va60I~
    //***********************************************************************//~vak2I~
    private TileData selectTestDiscard(int Peswn,TileData PtdTaken,TileData[] PtdsHand)//~vak2R~
    {                                                              //~vak2I~
        if (Dump.Y) Dump.println("RADiscard.selectTestDiscard eswn="+Peswn+",PtdTaken="+TileData.toString(PtdTaken));//~vak2I~
        if ((RS.RSP[Peswn].callStatus & CALLSTAT_REACH)!=0) //reach called//~vak2I~
        {                                                          //~vak2I~
        	if (Dump.Y) Dump.println("RADiscard.selectTestDiscard return null by reach called");//~vak2I~
        	return null;                                           //~vak2I~
        }                                                          //~vak2I~
		int type= TestOption.testDiscardType-1;                     //~vak2R~
		int num=TestOption.testDiscardNumber-1;                    //~vak2R~
    	TileData tdDiscard=null;                                   //~vak2I~
        if (PtdTaken!=null && PtdTaken.type==type && PtdTaken.number==num)//+vak2R~
        	tdDiscard=PtdTaken;                                     //~vak2I~
        else                                                       //~vak2I~
        	for (TileData td:PtdsHand)                             //~vak2I~
            {                                                      //~vak2I~
		        if (td.type==type && td.number==num)               //~vak2I~
                {                                                  //~vak2I~
		        	tdDiscard=td;                                  //~vak2I~
                    break;                                         //~vak2I~
                }                                                  //~vak2I~
            }                                                      //~vak2I~
        if (Dump.Y) Dump.println("RADiscard.selectTestDiscard testDiscardType="+type+",testDiscardNumber="+num+",tdDiscard="+TileData.toString(tdDiscard)+",tdsHand="+TileData.toString(PtdsHand));//~vak2R~
        return tdDiscard;                                          //~vak2I~
    }                                                              //~vak2I~
    //***********************************************************************//~1111M~
    //*not smart robot                                             //~1111M~
    //*discard jsut take if discardable(not winning of open reachnot or pao//~1111M~
    //************************************************************************//~1111M~
    private TileData selectDull(int Peswn,TileData PtdTaken)//~1111R~//~1116R~
    {                                                              //~1111M~
    	TileData tdDiscard=null;                                   //~1111M~
        //**************************                               //~1111M~
        if (Dump.Y) Dump.println("RADiscard.selectDull eswn="+Peswn);//~va60I~//~1111M~
        Point p=new Point();                                       //~1111R~//~1116M~
        btsOrphan=Shanten.chkOrphan(itsHand,p);                    //~1111R~//~1115M~
        ctrOrphan=p.x;                                             //~1111R~//~1116M~
        if (PtdTaken==null)                                        //~1126I~
        {                                                          //~1126I~
        	return selectDullPonChii(Peswn);                       //~1126I~
        }                                                          //~1126I~
        int pos=RAUtils.getPosTile(PtdTaken);                    //~va60I~//~1116M~//~1119R~
        if (isDiscardable(Peswn,pos))                             //~1111M~//~1116R~
        	tdDiscard=PtdTaken;                                    //~1111M~
        else                                                       //~1111M~
        {                                                          //~1111M~
			tdDiscard=searchOrphan(Peswn);                         //~1111M~
//          if (tdDiscard==null)                                   //~1111M~//~1112R~
//  			tdDiscard=searchNonOrphan(Peswn);                  //~1111M~//~1112R~
            if (tdDiscard==null)                                   //~1111M~
            	tdDiscard=PtdTaken;	//even open reach              //~1111M~
        }                                                          //~1111M~
        if (Dump.Y) Dump.println("RADiscard.selectDull tdDiscard="+Utils.toString(tdDiscard));//~1111M~
        return tdDiscard;                                          //~va60I~//~1111M~
    }                                                              //~va60I~//~1111M~
    //************************************************************************//~1126I~
    private TileData selectDullPonChii(int Peswn)                  //~1126I~
    {                                                              //~1126I~
    	TileData tdDiscard=null;                                   //~1126I~
        //**************************                               //~1126I~
        if (Dump.Y) Dump.println("RADiscard.selectDull eswn="+Peswn);//~1126I~
		tdDiscard=searchOrphan(Peswn);                             //~1126I~
        if (tdDiscard==null)                                       //~1126I~
			tdDiscard=searchNonOrphan(Peswn);                      //~1126I~
        if (tdDiscard==null)                                       //~1126I~
        	tdDiscard=tdsHand[0];                                  //~1126I~
        if (Dump.Y) Dump.println("RADiscard.selectDull tdDiscard="+Utils.toString(tdDiscard));//~1126I~
        return tdDiscard;                                          //~1126I~
    }                                                              //~1126I~
    //***********************************************************************//~1111I~
    private TileData searchOrphan(int Peswn)                       //~1111I~
    {                                                              //~1111I~
    	TileData tdDiscard=null,tdRed5=null;                                   //~1111I~//~1112R~
        boolean swDiscardableAll=RS.isDiscardableAll();            //~1126I~
        //**************************                               //~1111I~
        if (ctrOrphan!=0)                                         //~1111I~
        {                                                          //~1112I~
            for (int ii=itsHand.length-1;ii>=0;ii--)              //~1111I~
            {                                                      //~1111I~
                if (btsOrphan[ii]) //and ctr>0                     //~1111R~
                {                                                  //~1111R~
                    if (swDiscardableAll || isDiscardable(Peswn,ii))                   //~1111R~//~1126R~
                    {                                              //~1111R~
//                      tdDiscard=selectInHand(Peswn,ii,tdssHand); //~1111R~//~1112R~
//                      tdDiscard=selectInHand(Peswn,ii);          //~1112I~//~1119R~
//                        tdDiscard=RAUtils.selectTileInHand(ii,itsHand,itsHandRed,tdsHand);//~1119I~//~1129R~
                        tdDiscard=RAUtils.selectTileInHand(ii,itsHand,tdsHand);//~1129I~
                        if (tdDiscard!=null)                       //~1112I~
                        {                                          //~1112I~
						 	if (tdDiscard.isRed5())                //~1112I~
                            {                                      //~1112I~
                            	tdRed5=tdDiscard;                  //~1112I~
                                continue;                          //~1112I~
                            }                                      //~1112I~
	                        break;                                     //~1111R~//~1112I~
                        }                                          //~1112I~
                    }                                              //~1111R~
                }                                                  //~1111R~
            }                                                      //~1111I~
            if (tdDiscard==null)                                   //~1112I~
            	tdDiscard=tdRed5;                                  //~1112I~
        }                                                          //~1112I~
        if (Dump.Y) Dump.println("RADiscard.searchOrphan eswn="+Peswn+",ctrOrphan="+ctrOrphan+",tdDiscard="+Utils.toString(tdDiscard));//~1111I~
        return tdDiscard;
    }                                                              //~1111I~
    //***********************************************************************//~1111I~
    private TileData searchNonOrphan(int Peswn)                    //~1111I~
    {                                                              //~1111I~
    	TileData tdDiscard=null,tdRed5=null;                                   //~1111I~//~1112R~
        boolean swDiscardableAll=RS.isDiscardableAll();            //~1126I~
        //**************************                               //~1111I~
        for (int ii=0;ii<itsHand.length;ii++)                      //~1111I~
        {                                                          //~1111I~
            if (!btsOrphan[ii])                                    //~1111I~
            {                                                      //~1111I~
    	        if (itsHand[ii]!=0)                                //~1111I~
                {                                                  //~1111I~
                    if (swDiscardableAll || isDiscardable(Peswn,ii))                   //~1111I~//~1126R~
                    {                                              //~1111I~
//                      tdDiscard=selectInHand(Peswn,ii,tdssHand); //~1111I~//~1112R~
//                      tdDiscard=selectInHand(Peswn,ii);          //~1112I~//~1119R~
//                        tdDiscard=RAUtils.selectTileInHand(ii,itsHand,itsHandRed,tdsHand);//~1119I~//~1129R~
                        tdDiscard=RAUtils.selectTileInHand(ii,itsHand,tdsHand);//~1129I~
                        if (tdDiscard!=null)                       //~1112I~
                        {                                          //~1112I~
						 	if (tdDiscard.isRed5())                //~1112I~
                            {                                      //~1112I~
                            	tdRed5=tdDiscard;                  //~1112I~
                                continue;                          //~1112I~
                            }                                      //~1112I~
	                        break;                                 //~1112I~
                        }                                          //~1112I~
                    }                                              //~1111I~
                }                                                  //~1111I~
            }                                                      //~1111I~
            if (tdDiscard==null)                                   //~1112I~
            	tdDiscard=tdRed5;                                  //~1112I~
        }                                                          //~1111I~
        if (Dump.Y) Dump.println("RADiscard.searchNonOrphan eswn="+Peswn+",ctrOrphan="+ctrOrphan+",tdDiscard="+Utils.toString(tdDiscard));//~1111I~
        return tdDiscard;
    }                                                              //~1111I~
    //*********************************************************    //~1111I~
    public  boolean isDiscardable(int Peswn,int Ppos)               //~1111I~//~1114R~
    {                                                              //~1111I~
        if (RS.isDiscardableAll())                                 //~1126I~
        	return true;                                           //~1126I~
    	boolean rc=true;                                           //~1111I~//~1126M~
    	Point p=RS.chkDiscardable(Peswn,Ppos); //oppenreach,pao                    //~1111I~//~1113R~
        if (p!=null)                                               //~1111I~
        {                                                          //~1111I~
        	int pos_eswn=p.y;  //tile pos or 4kan player eswn          //~1111I~
        	switch(p.x)                                            //~1111I~
            {                                                      //~1111I~
            case ERR_DISCARD_OPENREACH:   //if 2 player open,hard to chk furiten                         //~1111I~//~1113R~
            	rc=false;                                          //~1111I~
            	break;                                             //~1111I~
            case ERR_DISCARD_PAO_3DRAGON:                              //~1111I~
            case ERR_DISCARD_PAO_4WIND:                                //~1111I~
    			rc=isFuriten(pos_eswn,Ppos); //discardable if furiten//~1113I~
            	break;                                             //~1111I~
            case ERR_DISCARD_PAO_4KAN:                                 //~1111I~
            	rc=isFuriten(pos_eswn,Ppos);  //beta ori           //~1111R~
            	break;                                             //~1111I~
            }                                                      //~1111I~
        }                                                          //~1111I~
        if (Dump.Y) Dump.println("RADiscard.isDiscardable rc="+rc+",eswn="+Peswn+",Ppos="+Ppos);//~1111I~
        return rc;                                                 //~1111I~
    }                                                              //~1111I~
    //*********************************************************    //~1111I~
    private boolean isFuriten(int Peswn/*targetEswn*/,int Ppos)     //~1111R~
    {                                                              //~1111I~
    	boolean rc=RS.isFuriten(Peswn,Ppos);                       //~1111R~
        if (Dump.Y) Dump.println("RADiscard.isFuriTen rc="+rc+",eswn="+Peswn+",Ppos="+Ppos);//~1111I~
        return rc;                                                 //~1111I~
    }                                                              //~1111I~
    //*********************************************************    //~1113I~
    private  TileData selectSmart(int Peswn,TileData PtdTaken)//~1113I~//~1116R~
    {                                                              //~1113I~
        if (Dump.Y) Dump.println("RADSmart.selectSmart eswn="+Peswn);//~1113I~//~1116R~
        TileData tdDiscard=RADS.selectSmart(Peswn,PtdTaken);   //~1113I~
        return tdDiscard;                                          //~1113I~
    }                                                              //~1113I~
}//class RADiscard                                                 //~dataR~//~@@@@R~//~v@@@R~//~va60R~
