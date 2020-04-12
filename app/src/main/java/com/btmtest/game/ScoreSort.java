//*CID://+DATER~:                             update#= 1039;       //~v@@@R~//~9211R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.game;                                        //~v@@@R~//~9520R~

import com.btmtest.utils.Dump;

import java.util.Arrays;
import java.util.Comparator;

import static com.btmtest.game.GConst.*;

public class ScoreSort                                             //~9520R~
{                                                                  //~2C29R~
	public int[] pos2Order;                                        //~9520I~
	public int[] order2Pos;                                        //~9520I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public ScoreSort()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9520R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("ScoreSort.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9520R~
    }                                                              //~v@@@R~
    //**************************************************************                   //~9401I~//~9407R~
    //*sort by point , if same order by eswn at initial            //~9407I~
    //**************************************************************//~9407I~
    public int[] sortByPoint(int[] Pscore)                        //~9401I~//~9520R~
    {                                                              //~9401I~
        Integer[] scoreI=new Integer[]{0,1,2,3};	//idxPosition  //~9416R~
        Arrays.sort(scoreI,new PointComp(Pscore));                 //~9401R~
        int[] sorted=new int[]{scoreI[0],scoreI[1],scoreI[2],scoreI[3]};
    	if (Dump.Y) Dump.println("ScoreSort.sortByPoint sorted="+Arrays.toString(sorted));//~9416I~//~9520R~
        order2Pos=sorted;                                          //~9520I~
        return sorted;                                              //~9401I~
    }                                                              //~9401I~
    //******************************************                   //~9322I~//+9520M~
    public  int[] getOrder(int[] Pscore)                           //~9322I~//+9520I~
    {                                                              //~9322I~//+9520M~
        sortByPoint(Pscore);	//getOrder2Pos                             //~9401I~//+9520M~
        if (Dump.Y) Dump.println("ScoreSort:getOrder score="+Arrays.toString(Pscore));//~9322I~//~9401M~//+9520M~
        if (Dump.Y) Dump.println("ScoreSort:getOrder order2Idx="+Arrays.toString(order2Pos));//~9322I~//~9401M~//+9520M~
        int[] pos2Order=new int[PLAYERS];                                //~9401I~//+9520M~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9401I~//+9520M~
            pos2Order[order2Pos[ii]]=ii;                           //~9401I~//+9520M~
        if (Dump.Y) Dump.println("ScoreSort:getOrder posOrder="+Arrays.toString(pos2Order));//~9401I~//+9520M~
        return pos2Order;                                          //+9520M~
    }                                                              //~9322I~//+9520M~
    //******************************************                   //~9401I~
    class PointComp implements Comparator<Integer>                     //~9401I~
    {                                                              //~9401I~
    	int[] compScore;                                           //~9401R~
    	public PointComp(int[] Pscore)                             //~9401R~
        {                                                          //~9401I~
			compScore=Pscore;                                      //~9401I~
	    	if (Dump.Y) Dump.println("ScoreSort.sortByPoint compScore="+Arrays.toString(compScore));//~9416I~//~9520R~
        }                                                          //~9401I~
    	@Override                                                  //~9401I~
        public int compare(Integer Pidx1,Integer Pidx2)                    //~9401I~
        {                                                          //~9401I~
        	int rc=-(compScore[Pidx1]-compScore[Pidx2]);           //~9401R~
            if (rc==0)                                             //~9401I~
            {                                                      //~9401I~
            	int eswn1=Pidx1;                                   //~9416I~
            	int eswn2=Pidx2;                                   //~9416I~
                rc=eswn1-eswn2;                                    //~9401R~
	    		if (Dump.Y) Dump.println("ScoreSort.PointComp.compre eswn1="+eswn1+",eswn2="+eswn2);//~9401I~//~9416R~//~9520R~
            }                                                      //~9401I~
    		if (Dump.Y) Dump.println("ScoreSort.PointComp.compre rc="+rc+",idx1="+Pidx1+",idx2="+Pidx2+",score1="+compScore[Pidx1]+",score2="+compScore[Pidx2]);//~9401R~//~9416R~//~9520R~
            return rc;                                             //~9401I~
        }                                                          //~9401I~
    }                                                              //~9401I~
}//class                                                           //~v@@@R~
