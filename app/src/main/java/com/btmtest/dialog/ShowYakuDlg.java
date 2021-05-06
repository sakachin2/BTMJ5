//*CID://+va8qR~:                             update#=  798;       //~va11R~//+va8qR~
//*****************************************************************//~v101I~
//2021/04/26 va8q (Bug)shows "app checkValue option is off" even if option is on;//+va8qI~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.view.View;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.game.UA.Rank;
import com.btmtest.game.UA.RonResult;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UCheckBox;                                  //~va11I~

import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.dialog.CompReqDlg.*;
import static com.btmtest.game.UA.Rank.*;

public class ShowYakuDlg extends UFDlg                             //~v@@@R~//~9220R~//~va11R~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.showyakudlg;      //~9220I~//~va11R~
    private static final int TITLEID=R.string.Title_ShowYakuDlg;//~9220I~//~va11R~
    private static final String HELPFILE="ShowYakuDlg";                //~9220I~//~va11R~
    private RonResult ronResult;                                   //~va11I~
    private Rank longRank;                                         //~va11I~
                                                                   //~va11I~
    private static final int[] yakuCBIDS=new int[]{                //~va11I~
	 R.id.cb7PAIR                                                  //~va11R~
	,R.id.cbTANYAO                                                 //~va11R~
	,R.id.cbPINFU                                                  //~va11R~
	,R.id.cbWGR                                                    //~va11R~
	,R.id.cbWIND                                                   //~va11R~
	,R.id.cbROUND                                                  //~va11R~
	,R.id.cb1SAMESEQ    //1peiko                                   //~va11R~
	,R.id.cb3SAMESEQ    //3shiki                                   //~va11R~
	,R.id.cb3SAMENUM    //3tonko                                   //~va11R~
	,R.id.cb3SAMEHAND   //3anko                                    //~va11R~
	,R.id.cbSTRAIGHT    //itsu                                     //~va11R~
	,R.id.cbALLSAME     //toitoi                                   //~va11R~
	,R.id.cb19SEQMIX    //honchan                                  //~va11R~
	,R.id.cb3KAN                                                   //~va11R~
	,R.id.cb2SAMESEQ    //2peiko                                   //~va11R~
	,R.id.cb19SEQ       //junchan                                  //~va11R~
	,R.id.cbFLASHMIX    //honitu                                   //~va11R~
	,R.id.cb3DRAGONSMALL //shosangen                               //~va11R~
	,R.id.cb19SAMEMIX       //honro                                //~va11R~
	,R.id.cbFLASH           //chinitu                              //~va11R~
                                                                   //~va11I~
	,R.id.cbREACH                                                  //~va11R~
	,R.id.cbREACH_DOUBLE                                           //~va11R~
	,R.id.cbREACH_OPEN                                             //~va11R~
	,R.id.cbREACH_JUST                                             //~va11R~
	,R.id.cbTAKE_NOEARTH                                           //~va11R~
	,R.id.cbLAST_TAKEN    //haitei                                 //~va11R~
	,R.id.cbLAST_DISCARDED//hotei                                  //~va11R~
	,R.id.cbKAN_ADD       //chankan                                //~va11R~
	,R.id.cbKAN_TAKEN     //rinshan                                //~va11R~
	,R.id.cbRSV2          //dora                                   //~va11R~
	,R.id.cbNAGASI        //nagasi mangan                          //~va11R~
	,R.id.cbCHILDRON_NY   //renho not yakuman                      //~va11R~
//yakuman                                                          //~va11I~
	,R.id.cb4SAME     //4anko                                      //~va11R~
	,R.id.cb13ALL       //kokushi                                  //~va11R~
	,R.id.cb9GATE       //9gate                                    //~va11R~
	,R.id.cb7PAIR28     //daisharin                                //~va11R~
	,R.id.cb13NOPAIR    //13puto                                   //~va11R~
	,R.id.cb14NOPAIR    //14puto                                   //~va11R~
	,R.id.cb3DRAGON     //daisangen                                //~va11R~
	,R.id.cbALLHONOR    //tuiiso                                   //~va11R~
	,R.id.cb4WIND       //4siho big                                //~va11R~
	,R.id.cb4WINDSMALL  //4shiho small                             //~va11R~
	,R.id.cbALLGREEN     //ryuiso                                  //~va11R~
	,R.id.cbALL19       //chinraoyo                                //~va11R~
	,R.id.cb4KAN        //4kan                                     //~va11R~
                                                                   //~va11R~
	,R.id.cbPARENTTAKE  //tenho                                    //~va11R~
	,R.id.cbCHILDTAKE   //chiiho                                   //~va11R~
	,R.id.cbCHILDRON    //renho                                    //~va11R~
	,R.id.cbBYRANK      //kazoe yakuman                            //~va11R~
	,R.id.cb8CONT       //8 renchan                                //~va11R~
	,R.id.cb4SAME2    //4anko                                      //~va11I~
	,R.id.cb13ALL2      //kokushi                                  //~va11I~
	,R.id.cb9GATE2      //9gate                                    //~va11I~
    						};                                     //~va11I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public ShowYakuDlg()                                           //~v@@@R~//~9220R~//~9221R~//~va11R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("ShowYakuDlg.defaultConstructor"); //~9221R~//~va11R~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
    public static ShowYakuDlg newInstance(RonResult Presult)       //~va11R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("ShowYakuDlg.newInstance result="+Presult.toString());        //~9226I~//~va11R~
    	ShowYakuDlg dlg=new ShowYakuDlg();                                     //~v@@@I~//~9220R~//~9221R~//~va11R~
    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~
    			UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN,//~v@@@I~//~9220R~//~9708R~//~va11R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~
        dlg.ronResult=Presult;                                     //~va11I~
        dlg.longRank=Presult.longRank;                             //~va11I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
    //******************************************                   //~9403I~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("ShowYakuDlg.initLayout result="+ronResult.toString());        //~v@@@R~//~9220R~//~va11R~
        super.initLayout(PView);                                   //~va11R~
        androidDlg=getDialog();                                    //~v@@@I~
        layoutView=PView;                                                           //~9219I~//~9220R~
        setupValue(PView);                                              //~9212I~//~9219M~//~va11R~
    }                                                              //~v@@@M~
    //******************************************                   //~9212I~
    private void setupValue(View Pview)                                      //~9212I~//~va11R~
    {                                                              //~9212I~
        UButton.bind(Pview,R.id.ShowRule,this);                    //~va11R~
        setSummary(Pview);                                         //~va11R~
    	setUpYaku(Pview);                                          //~va11I~
    }                                                              //~va11I~
    //*********************************************************************//~va11I~
    private void setSummary(View Pview)                            //~va11R~
    {                                                              //~va11I~
        TextView tvDoraDragon=(TextView)    UView.findViewById(Pview,R.id.tvDoraDragon);//~va11I~
    	String txt;                                                //~va11I~
      if (!RuleSettingOperation.isCheckRonValue())                 //~va11I~
	  	txt=Utils.getStr(R.string.Info_NoOptionShowValue);        //~va11I~
      else                                                         //~va11I~
      {                                                            //~va11I~
    	int ctrDora=longRank.getDora();                            //~va11R~
    	int ctrDragon=longRank.getWGR();                           //~va11R~
    	txt="";                                                    //~va11I~
//    	txt+=Utils.getStr(R.string.Info_NoOptionShowValue);        //~va11I~//+va8qR~
        if (!longRank.isYakumanExceptByRank()) //except kazoeyakuman//~va11I~
		    txt+=ronResult.han+" "+Utils.getStr(R.string.Label_Han)+" : ";//~va11I~
    	txt+=Utils.getStr(R.string.Label_Dora)+" = "+ctrDora+" , "+Utils.getStr(R.string.Label_Dragon)+" = "+ctrDragon;//~va11I~
        if (longRank.isContains(RYAKU_CHILDRON_NY))                   //~va11I~
        {                                                          //~va11I~
			if (RuleSettingYaku.isMixRank1stChildRon())            //~va11I~
	  		  	txt+=" , "+Utils.getStr(R.string.Label_Yakuman_Renho)+":"+Utils.getStr(R.string.Desc_RenhoMix);//~va11R~
            else                                                   //~va11I~
	  		  	txt+=" , "+Utils.getStr(R.string.Label_Yakuman_Renho)+":"+Utils.getStr(R.string.Desc_RenhoHigh);//~va11R~
        }                                                          //~va11I~
      }                                                            //~va11I~
        tvDoraDragon.setText(txt);                                 //~va11I~
    }                                                              //~va11I~
    //*********************************************************************//~va11I~
    private void setUpYaku(View Pview)                                 //~va11I~
    {                                                              //~va11I~
    	int sz=yakuCBIDS.length;                                   //~va11I~
    	for (int ii=0;ii<sz;ii++)                                  //~va11I~
        {                                                          //~va11I~
        	UCheckBox cb=new UCheckBox(Pview,yakuCBIDS[ii]);       //~va11R~
            if (cb!=null)                                          //~va11I~
            {                                                      //~va11I~
            	String txt=Rank.SyakuNameS[ii];                    //~va11R~
                if (ii<RYAKU_YAKUMAN)                              //~va11I~
                {                                                  //~va11I~
            		int intHan=Rank.ShanS[ii];                     //~va11I~
                    String strHan="";                              //~va11I~
                    switch(Rank.SconditionS[ii])                   //~va11I~
                    {                                              //~va11I~
    				case YCOND_NODOWN:	//  =0;     //"n"          //~va11I~
                    	strHan=":"+intHan;                         //~va11I~
                    	break;                                     //~va11I~
    				case YCOND_DOWN  :	//  =1;     //"n/m"        //~va11I~
                    	strHan=":"+intHan+"/"+(intHan-1);          //~va11I~
                    	break;                                     //~va11I~
    				case YCOND_MENZEN:	//  =2;     //"n/0"        //~va11I~
                    	strHan=":"+intHan+"/0";                    //~va11I~
                    	break;                                     //~va11I~
    				case YCOND_SETTING:	// =3;     //x             //~va11I~
                    	if (ii==RYAKU_TANYAO)                      //~va11I~
					        strHan=":"+RANK_TANYAO+"/"+(RuleSettingYaku.isTanyaoEarth() ? 1 : 0);//~va11I~
                        else                                       //~va11I~
                        if (ii==RYAKU_7PAIR)                       //~va11I~
					        strHan=":"+(RuleSettingYaku.is7Pair50() ? RANK_7PAIR : RANK_7PAIR2);//~va11I~
                        else                                       //~va11I~
                        if (ii==RYAKU_CHILDRON_NY)                 //~va11I~
                        {                                          //~va11I~
				        	int idxRank=RuleSettingYaku.getRank1stChildRon(); //0,4mangan)-->8(yakuman)//~va11R~
			       		 	int han=0;                             //~va11R~
                            if (idxRank!=0)                        //~va11I~
			       		 		han=CompReqDlg.intsRankIdx[idxRank];        //4(mangan),6(haneman),8(dowble),12(triple)//~va11I~
            				if (idxRank!=RANKIDX_YAKUMAN)    //8   //~va11I~
					        	strHan=":"+han;                    //~va11R~
                        }                                          //~va11I~
                        else                                       //~va11I~
                        if (ii==RYAKU_REACH_JUST)                  //~va11I~
                        {                                          //~va11I~
    	  					if (!RuleSettingYaku.isReachOneShot()) //~va11I~
					    		cb.setEnabled(false);              //~va11I~
                        }                                          //~va11I~
                    	break;                                     //~va11I~
                    default:			// YCOND_OUTOF   =4;     //(no info)//~va11I~
                    }                                              //~va11I~
            		txt+=strHan;                                  //~va11I~
                }                                                  //~va11I~
                else //yakuman                                     //~va11I~
                {                                                  //~va11I~
					if (ii==RYAKU_BYRANK && !RuleSettingYaku.isYakumanByRank())//~va11I~
			    		cb.setEnabled(false);                      //~va11I~
                    else                                           //~va11I~
					if (ii==RYAKU_13NOPAIR && !RuleSettingYaku.isYakuman13NoPair())//~va11I~
			    		cb.setEnabled(false);                      //~va11I~
                    else                                           //~va11I~
					if (ii==RYAKU_14NOPAIR && !RuleSettingYaku.isYakuman14NoPair())//~va11I~
			    		cb.setEnabled(false);                      //~va11I~
                    else                                           //~va11I~
					if (ii==RYAKU_7PAIR28  && !RuleSettingYaku.isYakumanChariot())//~va11I~
			    		cb.setEnabled(false);                      //~va11I~
                }                                                  //~va11I~
            	cb.setText(txt);                                   //~va11I~
            	boolean sw=Rank.isContains(longRank,ii);           //~va11R~
		    	cb.setState(sw,true/*fix*/);                       //~va11R~
                if (ii==RYAKU_4SAME2 && !RuleSettingYaku.is4SameDouble())//~va11I~
		    		cb.setEnabled(false);                          //~va11I~
                else                                               //~va11I~
                if (ii==RYAKU_13ALL2 && !RuleSettingYaku.is13WaitAll())//~va11I~
		    		cb.setEnabled(false);                          //~va11I~
                else                                               //~va11I~
                if (ii==RYAKU_9GATE2 && !RuleSettingYaku.is9GateDouble())//~va11I~
		    		cb.setEnabled(false);                          //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    @Override                                                      //~va11I~
    public void onClickOther(int Pbuttonid)                        //~va11I~
	{                                                              //~va11I~
    	if (Dump.Y) Dump.println("CompReqDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~va11I~
        switch(Pbuttonid)                                          //~va11I~
        {                                                          //~va11I~
            case R.id.ShowRule:                                    //~va11I~
                onClickShowRule();                                 //~va11I~
                break;                                             //~va11I~
            default:                                               //~va11I~
        }                                                          //~va11I~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    private void onClickShowRule()                                 //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("ShowYakuDlg.onClickShowRule");   //~va11I~
        RuleSetting.showRuleInGame();                              //~va11I~
    }                                                              //~va11I~
}//class                                                           //~v@@@R~
