//*CID://+DATER~:                             update#=  536;       //~v@@@R~//~9211R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.dialog.RuleSetting;                             //~9412R~
import com.btmtest.game.Status;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.game.Complete.*;
import static com.btmtest.game.GConst.*;

public class CompDlgGainer                                         //~9219R~
{                                                                  //~2C29R~     //~9214I~
//  private static final int POINT_REACH  =1000;                   //~9212I~//~9511R~
    private static final String[] strsPoint=AG.resource.getStringArray(R.array.NormalPoint);//~9222I~
    private static final String[] strsRank=AG.resource.getStringArray(R.array.NormalRank);//~9222I~
    private TextView tvWinnerEswn;                                 //~9222I~
    private TextView tvReach,tvDup,tvPoint,tvPointStick,tvReachRate,tvDupRate;//~9212R~
    private TextView tvBasePoint,tvRank,tvPointCalc;                    //~9214I~
    private LinearLayout llPointStick,llNormalPoint;               //~9223I~
    private LinearLayout llReachStick;                             //~9B10I~
                                                                   //~1A6fI~
    protected UFDlg ufdlg;                                         //~v@@@R~
    private  int completeEswn,completeType,completeEswnLooser;      //~9212I~//~9217R~
    private int gameDup,gameReach,pointReach,pointDup;               //~9212I~//~9213R~
    private int net,netup,pointStick;//~9212R~//~9213R~
    private int idxPoint,idxRank,dupRate;                                  //~9212I~
    private  boolean swTake,swErr;                                        //~9217I~//~9223R~
    private Complete.Status compStat;                              //~9217I~
//    private View layout;                                   //~9219I~//~9222R~
//    private CompleteDlg compDlg;                                    //~9219I~
    private int[] calcOut=new int[CALC_AMT_MAXCTR];                //~9222I~
    private int[] amtTotal;                                           //~9222I~
    private int relativePos,ctrReach,ctrDup;                                       //~9223I~
    private boolean swMultiRon,swMultiRonPointDupAll;       //~9223I~
    private int cutEswn;                                           //~9531I~
    private boolean swInvalid;                                     //~9707I~
    public int widthTileImage;                                     //~9927I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public CompDlgGainer(CompleteDlg PcompDlg,View Pparent,int PrelativePos,Complete.Status PcompStat,boolean PswInvalid,int[] Pamt)                                           //~v@@@R~//~9219R~//~9222R~//~9223R~//~9707R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("CompDlgGainer.Constructor layoutid="+Pparent.getId());//~9219R~
//        compDlg=PcompDlg;                                          //~9219I~
//        layout=Pparent;                                            //~9219R~//~9222R~
        compStat=PcompStat;                                        //~9219I~
        relativePos=PrelativePos;                                  //~9223I~
        amtTotal=Pamt;                                             //~9223I~
        cutEswn=AG.aAccounts.getCutEswn();                         //~9531I~
        swInvalid=PswInvalid;                                      //~9707I~
        initLayout(Pparent);                                 //~9219I~//~9222R~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@M~
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("CompDlgGainer.initLayout");        //~v@@@R~//~9219R~
                                                                   //~9222I~
        setupValue();                                              //~9212I~//~9222M~
                                                                   //~9222I~
        tvWinnerEswn    =(TextView)    UView.findViewById(PView,R.id.tvWinnerEswn);//~9222I~
        tvBasePoint     =(TextView)    UView.findViewById(PView,R.id.tvBasePoint);//~9222I~
        tvRank          =(TextView)    UView.findViewById(PView,R.id.tvRank);//~9222I~
        tvReach         =(TextView)    UView.findViewById(PView,R.id.pointReach);//~9212I~
        tvReachRate     =(TextView)    UView.findViewById(PView,R.id.pointReachRate);//~9212I~
        tvDup           =(TextView)    UView.findViewById(PView,R.id.pointContinued);//~9212I~
        tvDupRate       =(TextView)    UView.findViewById(PView,R.id.pointDupRate);//~9212I~
        tvPoint         =(TextView)    UView.findViewById(PView,R.id.pointAmmount);//~9212R~
        tvPointCalc     =(TextView)    UView.findViewById(PView,R.id.pointCalc);//~9222I~
        tvPointStick    =(TextView)    UView.findViewById(PView,R.id.stickPoint);//~9212R~
        llNormalPoint   =(LinearLayout)UView.findViewById(PView,R.id.compdlgpoint);//~9223I~
        llPointStick    =(LinearLayout)UView.findViewById(PView,R.id.llPointStick);//point//~9223I~//~9B10R~
        llReachStick    =(LinearLayout)UView.findViewById(PView,R.id.llReachStick);//reach,cont stick//~9B10I~
                                                                   //~v@@@I~
    	if ((TestOption.option & TestOption.TO_COMPDLG_LAYOUT)==0) //TODO TEST//~9219I~
        {                                                          //~9927I~
//  		CompDlgTiles.setImageLayout(PView);                        //~9217R~//~9218R~//~9219R~//~9519R~
//  		CompDlgTiles.setImageLayout(PView,compStat);           //~9519I~//~9927R~
    		CompDlgTiles dlg=CompDlgTiles.setImageLayout(PView,compStat);//~9927I~
		    widthTileImage=dlg.widthTileImage;                     //~9927I~
    		if (Dump.Y) Dump.println("CompDlgGainer.initLayout widthTileImage="+widthTileImage);//~9927I~
        }                                                           //~9222I~//~9927R~
        getRuleSetting();                                                           //~v@@@I~//~9212R~
        if (swErr)                                                 //~9223I~
        {                                                          //~9223I~
//      	setNormalPointErr();                                   //~9223R~//~9B10R~
        	setNormalPointErr(true/*swErr*/);                      //~9B10I~
        	setPointStickErr();                                    //~9707I~
//      	llPointStick.setVisibility(View.GONE);                 //~9223I~//~9707R~
        	PView.setVisibility(View.VISIBLE);                     //~9707R~
        }                                                          //~9223I~
        else                                                       //~9707I~
        if (swInvalid)                                             //~9707I~
        {                                                          //~9707I~
//      	setNormalPointErr();                                   //~9707I~//~9B10R~
        	setNormalPointErr(false);                              //~9B10I~
        	setPointStickErr();                                    //~9707I~
        	PView.setVisibility(View.VISIBLE);                     //~9707I~
        }                                                          //~9707I~
        else	                                                   //~9223I~
        {                                                          //~9223I~
        	setNormalPoint();                                          //~9212I~//~9222R~//~9223R~
        	setPointStick();                                           //~9212I~//~9222R~//~9223R~
//      	llPointStick.setVisibility(View.VISIBLE);              //~9223I~//~9707R~
        	PView.setVisibility(View.VISIBLE);                     //~9707I~
        }                                                          //~9223I~
    }                                                              //~v@@@M~
    //******************************************                   //~9212I~
    private void getRuleSetting()                                      //~9212I~
    {                                                              //~9212I~
    	dupRate=RuleSetting.getDupRate();                          //~9212I~
    	swMultiRon=RuleSetting.isMultiRon();                       //~9223I~
    	swMultiRonPointDupAll=RuleSetting.isMultiRonPointDupAll(); //~9223I~
    }                                                              //~9212I~
    //******************************************                   //~9212I~
    private void setupValue()                                      //~9212I~
    {                                                              //~9212I~
    	completeType=compStat.completeType;                        //~9222I~
    	completeEswn=compStat.completeEswn;                        //~9222I~
    	completeEswnLooser=compStat.completeEswnLooser;            //~9222I~
    	swTake=compStat.swTake;                                    //~9223I~
    	swErr=compStat.swErr;                                      //~9223I~
        calcOut=compStat.ammount;                                  //~9222I~
                                                                   //~9222I~
        idxPoint=calcOut[CALC_AMT_IDXPOINT];                       //~9222I~
        idxRank=calcOut[CALC_AMT_IDXRANK];                         //~9222I~
        net=calcOut[CALC_AMT_NET];                                 //~9222I~
        netup=calcOut[CALC_AMT_NETUP];                             //~9222I~
                                                                   //~9222I~
    	Rect r=Status.getGameSeq();                                //~9222I~
        gameDup=r.right;                                           //~9222I~
        gameReach=r.bottom;                                        //~9222I~
                                                                   //~9222I~
        swTake=(completeType & (COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0;//~9222I~
    	if (Dump.Y) Dump.println("CompDlgGainer.setupValue calcOut="+Arrays.toString(calcOut));//+1211I~
    }                                                              //~9212I~
    //******************************************
    private void setNormalPoint()                                  //~9212I~
    {
    	if (Dump.Y) Dump.println("CompDlgGainer.setNormalPoint idxPoint=" + idxPoint + ",idxRank=" + idxRank);//~9214R~//~9219R~
		tvWinnerEswn.setText(GConst.nameESWN[completeEswn]);       //~9222I~
        tvBasePoint.setText(strsPoint[idxPoint]);                  //~9222I~
        tvRank.setText(strsRank[idxRank]);                         //~9222I~
        tvPointCalc.setText(Integer.toString(net));                //~9222I~
        tvPoint.setText(Integer.toString(netup));                  //~9222I~
        addNormalPoint();                                           //~9222I~//~9223R~
    	if (Dump.Y) Dump.println("CompDlgGainer.setNormalPoint amtTotal="+Arrays.toString(amtTotal));//~9222I~//~9223R~
    }
    //******************************************                   //~9223I~
//  private void setNormalPointErr()                               //~9223I~//~9B10R~
    private void setNormalPointErr(boolean PswErr)                 //~9B10I~
    {                                                              //~9223I~
    	if (Dump.Y) Dump.println("CompDlgGainer.setNormalPointErr");//~9223I~
		tvWinnerEswn.setText(GConst.nameESWN[completeEswn]);       //~9223I~
//      tvBasePoint.setText(Utils.getStr(R.string.AbnormalGain));  //~9223I~//~0301R~
        tvBasePoint.setText(Utils.getStr(PswErr ? R.string.AbnormalGain : R.string.NotAgreed));//~0301I~
        tvRank.setText("");                                        //~9223I~
        tvPointCalc.setText("");                                   //~9223I~
//      tvPoint.setText("");                                        //~9223I~//~9B10R~
//  	int errScore=(completeEswn==ESWN_E) ? POINT_RANKM_DEALER : POINT_RANKM;//~9B10I~//~0301R~
    	int errScore=PswErr ? ((completeEswn==ESWN_E) ? POINT_RANKM_DEALER : POINT_RANKM) : 0;//~0301I~
        tvPoint.setText(Integer.toString(-errScore));               //~9B10I~
    }                                                              //~9223I~
    //******************************************                   //~9223I~
    private void addNormalPoint()                                  //~9223I~
    {                                                              //~9223I~
    	if (Dump.Y) Dump.println("CompDlgGainer.addNormalPoint cutEswn="+cutEswn+",swTake="+swTake+",calcOut="+Arrays.toString(calcOut));//~9223R~//~9531R~
        int dealer=calcOut[CALC_AMT_DEALER];                       //~9223I~
        int nondealer=calcOut[CALC_AMT_NONDEALER];                 //~9223I~
        int nondealerCutPos=calcOut[CALC_AMT_NONDEALER_CUTPOS];     //~9531R~
        if (swTake)                                                //~9223I~
        	for (int ii=0;ii<PLAYERS;ii++)                         //~9223I~
            {                                                      //~9223I~
            	if (ii==completeEswn)                              //~9223I~
                	amtTotal[ii]+=netup;                           //~9223I~
                else                                               //~9531I~
                if (cutEswn>=0 && completeEswn!=cutEswn)     //unbalanced pay//~9531I~
                {                                                  //~9531I~
                    if (ii==0)                                     //~9531I~
	                	amtTotal[ii]-=dealer;                      //~9531I~
                    else                                           //~9531I~
                    if (ii==cutEswn)                               //~9531I~
	                	amtTotal[ii]-=nondealerCutPos;    //~9531I~
                    else                                           //~9531I~
	                	amtTotal[ii]-=nondealer;          //~9531I~
                }                                                  //~9531I~
                else                                               //~9223I~
                {                                                  //~9531I~
                    if (completeEswn==0)                           //~9224I~
                    {                                              //~9531I~
	                	amtTotal[ii]-=netup/3;                     //~9224I~
                      	if (ii==cutEswn)                           //~9531I~
		                	amtTotal[ii]-=netup/3;                 //~9531I~
                    }                                              //~9531I~
                    else                                           //~9224I~
	                	amtTotal[ii]-=(ii==0) ? dealer : nondealer;    //~9223I~//~9224R~
                }                                                  //~9531I~
            }                                                      //~9223I~
        else                                                       //~9223I~
        	for (int ii=0;ii<PLAYERS;ii++)                         //~9223I~
            {                                                      //~9223I~
            	if (ii==completeEswn)                              //~9223I~
                	amtTotal[ii]+=netup;                           //~9223I~
                else                                               //~9223I~
            	if (ii==completeEswnLooser)                        //~9223I~
                	amtTotal[ii]-=netup;                           //~9223I~
            }                                                      //~9223I~
    	if (Dump.Y) Dump.println("CompDlgGainer.addNormalPoint amtTotal="+Arrays.toString(amtTotal));//~9223I~
    }                                                              //~9223I~
    //******************************************                   //~9212I~
    private void setPointStick()                                      //~9212I~
    {                                                              //~9212I~
    	addPointStick();                                           //~9223M~
        tvReach.setText(Integer.toString(ctrReach));              //~9212R~//~9223R~
        tvReachRate.setText("x"+POINT_REACH);                      //~9212I~
        tvDup.setText(Integer.toString(ctrDup));                  //~9212R~//~9223R~
        tvDupRate.setText("x"+dupRate);                            //~9212I~
        tvPointStick.setText(Integer.toString(pointStick));        //~9212R~
        llReachStick.setVisibility(View.VISIBLE);                  //~9B12I~
    	if (Dump.Y) Dump.println("CompDlgGainer.setPointStick="+pointStick);//~9223I~
    }                                                              //~9212I~
    //******************************************                   //~9707I~
    private void setPointStickErr()                                //~9707I~
    {                                                              //~9707I~
//        tvReach.setText(Integer.toString(0));                      //~9707I~//~9B10R~
//        tvReachRate.setText("x"+POINT_REACH);                      //~9707I~//~9B10R~
//        tvDup.setText(Integer.toString(0));                        //~9707I~//~9B10R~
//        tvDupRate.setText("x"+dupRate);                            //~9707I~//~9B10R~
//        tvPointStick.setText(Integer.toString(0));                 //~9707I~//~9B10R~
        llReachStick.setVisibility(View.GONE);                     //~9B10I~
    	if (Dump.Y) Dump.println("CompDlgGainer.setPointStickErr");//~9707I~
    }                                                              //~9707I~
    //******************************************                   //~9212I~
    private void addPointStick()                                   //~9223I~
    {                                                              //~9223I~
    	                                                           //~9223I~
        int ctrReachTotal=AG.aPlayers.ctrReach+gameReach;          //~9706I~
    	if (Dump.Y) Dump.println("CompDlgGainer.addPointStick gameReach="+gameReach+",gameDup="+gameDup+",ctrReachTotal="+ctrReachTotal+",amt before="+Arrays.toString(amtTotal));//~9223R~//~9224I~//~9706I~
//  	int reach=gameReach*POINT_REACH;                           //~9223I~//~9706R~
    	int reach=ctrReachTotal*POINT_REACH;                       //~9706I~
        int dup=gameDup*dupRate;                                   //~9223I~
        pointStick=0;
        ctrReach=0; ctrDup=0;//~9223I~
        if (swTake)                                                //~9223I~
        {                                                          //~9223I~
        	for (int ii=0;ii<PLAYERS;ii++)                         //~9223I~
            {                                                      //~9223I~
            	if (ii==completeEswn)                              //~9223I~
                	amtTotal[ii]+=reach+dup;                       //~9223I~
                else                                               //~9223I~
                	amtTotal[ii]-=dup/3;                           //~9223I~
            }                                                      //~9223I~
//          ctrReach=gameReach;                                    //~9223I~//~9706R~
            ctrReach=ctrReachTotal;                                //~9706I~
	        ctrDup=gameDup;                                      //~9223R~//~9224R~
            pointStick=reach+dup;                                  //~9224I~
        }                                                          //~9223I~
        else                                                       //~9223I~
        {                                                          //~9223I~
        	for (int ii=0;ii<PLAYERS;ii++)                         //~9223I~
            {                                                      //~9223I~
            	if (ii==completeEswn)                              //~9223I~
                {                                                  //~9223I~
            		if (relativePos==0)                            //~9223I~
                    {                                              //~9223I~
                		amtTotal[ii]+=reach;                       //~9223I~
                        pointStick+=reach;                         //~9223I~
//                      ctrReach=gameReach;                            //~9223I~//~9224R~//~9706R~
                        ctrReach=ctrReachTotal;                    //~9706I~
                    }                                              //~9223I~
            		if (swMultiRonPointDupAll || relativePos==0)//~9223I~//~9513R~
                    {                                              //~9223I~
                		amtTotal[ii]+=dup;                         //~9223I~
                		amtTotal[completeEswnLooser]-=dup;         //~9223I~
                        pointStick+=dup;                           //~9223I~
                        ctrDup=gameDup;                                //~9223I~//~9224R~
                    }                                              //~9223I~
                }                                                  //~9223I~
            }                                                      //~9223I~
        }                                                          //~9223I~
    	if (Dump.Y) Dump.println("CompDlgGainer.addPointStick pointStick="+pointStick+",reach="+reach+",dup="+dup+",swMultiRon="+swMultiRon+",swMultiRonPointDupAll="+swMultiRonPointDupAll+",swTake="+swTake+",amt="+Arrays.toString(amtTotal));//~9223I~//~9224R~
    }                                                              //~9223I~
    //******************************************                   //~9223I~
}//class                                                           //~v@@@R~
