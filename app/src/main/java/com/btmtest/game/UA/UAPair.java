//*CID://+vaguR~: update#= 805;                                    //~vaguR~
//**********************************************************************//~v101I~
//2021/11/15 vagu (Bug)mix paring loose pair when PIN is 2 pattern; e.g)MAN 123,567(1 pattern) and PIN 55777789(2 pattern), it loose man meld and say flush of PIN//~vaguI~
//                if PIN 123,567(1 pattern) and MAN 55777789(2 pattern), no problem//~vaguI~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~va11R~

import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UA.Pair.*;
import static com.btmtest.game.UA.UARonData.*;

//****************************************************             //~9C11I~
public class UAPair                                                //~va11R~
{                                                                  //~0914I~
//******************************************************           //~va11I~ //~va11R~
    public  int typePair,type,number,flag,dupCtr;                  //~va11R~
    public  boolean swHand;                                        //~va11R~
//  private Pair pairSame,pairSeq,parent;                          //~va11R~
    private Pair pairTop;                                          //~va11I~
    public  Pair[] pairNotNum;	//earth and hand                   //~va11R~
    public  int ctrPairNotNum;                                     //~va11M~
    private Pair[] listSame,listSeq; //by makepair from UARonchk   //~va11R~
	private Pair[][][] pairSSS;      //[man/pin/sou] patern mix    //~va11R~
	public  Pair[][] mixedSS;         //mix of man,pin,sou pattern from pairSSS//~va11R~
	private int[] ctrPairSSS,ctrSelect;//rankS,pointS;             //~va11R~
   //**********************************************************   //~va11I~
    public UAPair(int PtypePair,int Ptype,int Pnumber,boolean PswHand)//~va11R~
    {                                                              //~va11R~
    	pairTop=new Pair(PtypePair,Ptype,Pnumber,PswHand);         //~va11R~
//      if (PtypePair==PT_DUMMY_TOP)                               //~va11R~
//      {                                                          //~va11R~
        	initTop();                                             //~va11M~
//      }                                                          //~va11R~
        if (Dump.Y) Dump.println("UAPair.constructor "+toString());//~va11R~
    }                                                              //~va11R~
//    //** for Earth ***********************                       //~va11R~
//    public Pairs(int PtypePair,int Ptype,int Pnumber,int PdupCtr,int Pflag)//for Earth//~va11R~
//    {                                                            //~va11R~
//        this(PtypePair,Ptype,Pnumber,false);                     //~va11R~
//        dupCtr=PdupCtr; flag=Pflag;                              //~va11R~
//        if (Dump.Y) Dump.println("Pairs.constructor "+toString());//~va11R~
//    }                                                            //~va11R~
//    public String toString()                                     //~va11R~
//    {                                                            //~va11R~
//        return "Pairing typePair="+typePair+",type="+type+",number="+number+",dupCtr="+dupCtr+",flag="+flag+",swHand="+swHand;//~va11R~
//    }                                                            //~va11R~
    public String toStringPattern()                                //~va11I~
    {                                                              //~va11I~
    	StringBuffer sb=new StringBuffer();                        //~va11I~
        sb.append("AllPattern:\n");                                //~va11R~
    	for (Pair[][] pairSS:pairSSS)                              //~va11R~
        {                                                          //~va11I~
        	if (pairSS==null)                                      //~va11I~
            	continue;                                          //~va11I~
        	for (Pair[] pairS:pairSS)                              //~va11R~
            {                                                      //~va11I~
            	if (pairS==null)                                   //~va11R~
                	break;                                         //~va11I~
            	sb.append(Pair.toString(pairS)+"\n");              //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        return sb.toString();                                      //~va11I~
    }                                                              //~va11I~
    public String toStringPairAll()                                //~va11R~
    {                                                              //~va11I~
    	StringBuffer sb=new StringBuffer();                        //~va11I~
        sb.append("toStringPairAll "+toString()+"\n");             //~va11R~
        sb.append("notNum\n");                                     //~va11I~
        sb.append(Pair.toString(pairNotNum)+"\n");                 //~va11I~
//      if (typePair==PT_DUMMY_TOP)                                //~va11R~
//      {                                                          //~va11R~
        	for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)             //~va11I~
            {                                                      //~va11I~
            	if (listSame[ii]!=null)                            //~va11I~
            		sb.append("listSame["+ii+"]="+Pair.toString(listSame[ii])+"\n");//~va11R~
                else                                               //~va11I~
            		sb.append("listSame["+ii+"]=null\n");          //~va11I~
            	if (listSeq[ii]!=null)                             //~va11I~
            		sb.append("listSeq["+ii+"]="+Pair.toString(listSeq[ii])+"\n");//~va11R~
                else                                               //~va11I~
            		sb.append("listSeq["+ii+"]=null\n");           //~va11I~
//          }                                                      //~va11R~
        }                                                          //~va11I~
        return sb.toString();                                      //~va11I~
    }                                                              //~va11I~
    //************************************                         //~va11M~
    private void initTop()                                         //~va11M~
    {                                                              //~va11M~
    	if (Dump.Y) Dump.println("UAPair.initTop");                //~va11R~
        pairNotNum=new Pair[PAIRS_MAX];                            //~va11R~
        listSame=new Pair[PIECE_NUMBERTYPECTR];                    //~va11R~
        listSeq=new Pair[PIECE_NUMBERTYPECTR];                     //~va11R~
		pairSSS=new Pair[PIECE_NUMBERTYPECTR][][];                 //~va11R~
		ctrPairSSS=new int[PIECE_NUMBERTYPECTR];                   //~va11M~
		ctrSelect=new int[PIECE_NUMBERTYPECTR];                    //~va11I~
    }                                                              //~va11M~
    //******************************************************************
    //*from UARonChk                                               //~va11I~
    //******************************************************************//~va11I~
    public Pair add(int PtypePair,int Ptype,int Pnumber,boolean PswHand)//~va11R~
    {
    	if (Dump.Y) Dump.println("UAPair.add typePair="+PtypePair+",type="+Ptype+",number="+Pnumber+",swHand="+PswHand);//~va11R~
	    Pair pair=new Pair(PtypePair,Ptype,Pnumber,PswHand);       //~va11R~
//        if (PtypePair==PT_NOTNUM)                                //~va11R~
//        {                                                        //~va11R~
//            if (typePair==PT_DUMMY_TOP)                          //~va11R~
//                pairNotNum[ctrPairNotNum++]=pair;                //~va11R~
//            else                                                 //~va11R~
//                if (Dump.Y) Dump.println("UAPair.add @@@@ NOTNUM to not PT_DUMMY_TOP");//~va11R~
//        }                                                        //~va11R~
//        else                                                     //~va11R~
//        if (PtypePair==PT_NUMSAME)                               //~va11R~
//        {                                                        //~va11R~
//            if (typePair==PT_DUMMY_TOP)                          //~va11R~
//                listSame[Ptype]=pair;                            //~va11R~
//            else                                                 //~va11R~
//            {                                                    //~va11R~
//                pairSame=pair;                                   //~va11R~
//                pair.parent=this;                                //~va11R~
//            }                                                    //~va11R~
//        }                                                        //~va11R~
//        else         //NUMSEQ                                    //~va11R~
//        {                                                        //~va11R~
//            if (typePair==PT_DUMMY_TOP)                          //~va11R~
//                listSeq[Ptype]=pair;                             //~va11R~
//            else                                                 //~va11R~
//            {                                                    //~va11R~
//                pairSeq=pair;                                    //~va11R~
//                pair.parent=this;                                //~va11R~
//            }                                                    //~va11R~
//        }                                                        //~va11R~
        if (PtypePair==PT_NOTNUM)                                  //~va11I~
        	pairNotNum[ctrPairNotNum++]=pair;                      //~va11I~
        else                                                       //~va11I~
        if (PtypePair==PT_NUMSAME)                                 //~va11I~
        	listSame[Ptype]=pair;                                  //~va11I~
        else         //NUMSEQ                                      //~va11I~
            listSeq[Ptype]=pair;                                   //~va11I~
    	if (Dump.Y) Dump.println("UAPair.add return "+toStringPairAll());//~va11R~
		return pair;                                               //~va11I~
    }                                                              //~va11I~
    public Pair add(Pair Pparent,int PtypePair,int Ptype,int Pnumber,boolean PswHand)//~va11I~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UAPair.add typePair="+PtypePair+",type="+type+",number="+Pnumber+",swHand="+PswHand+",parent="+Pair.toString(Pparent));//~va11R~
        if (Pparent==null)                                         //~va11I~
        	return add(PtypePair,Ptype,Pnumber,PswHand);           //~va11R~
	    Pair pair=new Pair(PtypePair,Ptype,Pnumber,PswHand);       //~va11I~
        if (PtypePair==PT_NUMSAME)                                 //~va11I~
        {                                                          //~va11I~
        	Pparent.pairSame=pair;                                 //~va11I~
            pair.parent=Pparent;                                   //~va11I~
        }                                                          //~va11I~
        else         //NUMSEQ                                      //~va11I~
        {                                                          //~va11I~
        	Pparent.pairSeq=pair;                                  //~va11I~
            pair.parent=Pparent;                                   //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UAPair.add return parent="+Pair.toString(Pparent));//~va11I~
		return pair;                                               //~va11I~
    }                                                              //~va11I~
    //******************************************************************//~va11I~
    //*from UARonData                                              //~va11I~
    //******************************************************************//~va11I~
    public void makePattern(Pair[] PpairsEarth)                    //~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UAPair.makePattern earth="+ Pair.toString(PpairsEarth));//~va11R~
    	makeNumPatternHandAll();                                   //~va11R~
        if (PpairsEarth!=null)                                     //~va11M~
		    addEarth(PpairsEarth);                                 //~va11M~
        pairNotNum=shrinkPairS(pairNotNum,ctrPairNotNum);          //~va11I~
        mixPatternNum();	//combine man/pin/sou                  //~va11R~
//      rankS=new int[mixedSS.length];                             //~va11R~
//      pointS=new int[mixedSS.length];                            //~va11R~
	    if (Dump.Y) Dump.println("UAPair.makePattern return "+toStringPattern());//~va11R~
    }                                                              //~va11I~
    //******************************************************************//~va11I~
    //*from UARonChk.makePairing                                   //~va11I~
    //******************************************************************//~va11I~
    public void makeNumPatternHandAll()                 //~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UAPair.makeNumPatternHandAll all chain:"+toStringPairAll());//~va11R~
        int maxPairing=16;  //2**4                                 //~va11I~
    	for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~va11I~
        {                                                          //~va11I~
	        pairSSS[ii]=new Pair[maxPairing][];                    //~va11R~
        	makeNumPattern(ii,listSame[ii]);                          //~va11R~
        	makeNumPattern(ii,listSeq[ii]);                            //~va11I~
        }                                                          //~va11I~
	    if (Dump.Y) Dump.println("UAPair.makeNumPatternHandAll return "+toStringPattern());//~va11R~
    }                                                              //~va11I~
    //******************************************************************//~va11I~
    public void makeNumPattern(int Ptype,Pair Ptop)                //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UAPair.makeNumPattern Ptype="+Ptype+",top="+Utils.toString(Ptop));//~va11R~
        if (Ptop==null)                                            //~va11R~
            return;                                                //~va11I~
    	Pair cpos=Ptop;                                            //~va11R~
    	for (;;)                                                   //~va11I~
        {                                                          //~va11I~
    		Pair bottom=getDown(cpos);                             //~va11R~
	        if (Dump.Y) Dump.println("UAPair.makeNumPattern Ptype="+Ptype+",top="+Ptop.toString()+"\n,bottom="+bottom.toString());//~va11R~
            if (bottom==null)                                      //~va11I~
            	break;                                             //~va11I~
	        setNumPattern(Ptype,Ptop,bottom);                      //~va11I~
            cpos=getUp(bottom);                                    //~va11R~
            if (cpos==null)                                        //~va11I~
            	break;                                             //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UAPair.makeNumPattern exit Ptype="+Ptype);//~va11I~
    }                                                              //~va11I~
    //******************************************************************//~va11I~
    private Pair getDown(Pair Pcpos)                               //~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UAPair.getDown entry Pcpos="+Utils.toString(Pcpos));//~va11I~
        Pair cpos=Pcpos;                                           //~va11R~
        if (cpos.pairSame!=null)                                   //~va11I~
            cpos=getDown(cpos.pairSame);                           //~va11I~
        else                                                       //~va11I~
        if (cpos.pairSeq!=null)                                    //~va11I~
            cpos=getDown(cpos.pairSeq);                            //~va11I~
    	if (Dump.Y) Dump.println("UAPair.getDown exit Pcpos="+Utils.toString(Pcpos)+"\n,rc=cpos="+Utils.toString(cpos));//~va11R~
        return cpos;                                               //~va11I~
    }                                                              //~va11I~
    //***************************************************          //~va11I~
    private Pair getUp(Pair Pcpos)                                 //~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UAPair.getUp entry Pcpos="+Utils.toString(Pcpos));//~va11I~
    	Pair next=null;                                            //~va11R~
    	Pair up=Pcpos.parent;                                      //~va11R~
        if (up!=null)                                              //~va11R~
        {                                                          //~va11I~
            if (Pcpos.typePair==PT_NUMSAME && up.pairSeq!=null)    //~va11R~
            	next=up.pairSeq;                                   //~va11M~
            else                                                   //~va11I~
				next=getUp(up);                                    //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UAPair.getUp exit Pcpos="+Utils.toString(Pcpos)+"\n,rc=next="+Utils.toString(next));//~va11R~
        return next;                                               //~va11I~
    }                                                              //~va11I~
    //***************************************************          //~va11M~
    private void setNumPattern(int Ptype,Pair Ptop,Pair Pbottom)   //~va11R~
    {                                                              //~va11M~
    	Pair pair=Pbottom;                                         //~va11R~
        int ctr=0;                                                 //~va11I~
        Pair[] pairS=new Pair[PAIRS_MAX];                          //~va11R~
    	for (;;)                                                   //~va11M~
        {                                                          //~va11M~
	        pairS[ctr++]=pair;                                    //~va11I~
        	Pair up=pair.parent;                                   //~va11R~
        	if (up==null)                                          //~va11R~
            	break;                                             //~va11I~
            pair=up;                                               //~va11I~
        }                                                          //~va11M~
        pairSSS[Ptype][ctrPairSSS[Ptype]++]=pairS;                 //~va11I~
    	if (Dump.Y) Dump.println("UAPair.setNumPattern type="+Ptype+",top="+Ptop.toString()+"\n,bottom="+Pbottom.toString()+",ctrPairSSS="+Utils.toString(ctrPairSSS));//~va11R~
    	if (Dump.Y) Dump.println("UAPair.setNumPattern return "+toStringPattern());//~va11I~
    }                                                              //~va11M~
    //******************************************************************//~va11I~
    private void addEarth(Pair[] PpairS)                           //~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UAPair.addEarth earth="+Pair.toString(PpairS));//~va11R~
    	for (Pair pairEarth:PpairS)                                //~va11R~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UAPair.addEarth pairEarth="+Pair.toString(pairEarth));//~va11R~
            if (pairEarth==null)                                   //~va11I~
            	break;                                             //~va11I~
        	int type=pairEarth.type;                                    //~va11R~
            if (type<PIECE_NUMBERTYPECTR)                          //~va11I~
                appendEarth(pairSSS,ctrPairSSS,type,pairEarth);//~va11R~
            else                                                   //~va11I~
    			pairNotNum[ctrPairNotNum++]=pairEarth;             //~va11I~
                                                                   //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UAPair.addEarth return pairNotNum="+Pair.toString(pairNotNum));//~va11R~
    }                                                              //~va11I~
    //******************************************************************//~va11I~
    private void appendEarth(Pair[][][] PpairSSS/*[type][paternIdx][pair]*/,int Pctr[]/*paternCtr*/,int Ptype,Pair Ppair)//~va11R~
    {                                                              //~va11I~
    	int ctrHand=Pctr[Ptype];                                   //~va11I~
        Pair[] pairS;                                              //~va11I~
        Pair[][] pairSS;                                           //~va11I~
    	if (Dump.Y) Dump.println("UAPair.appendEarth ctrHand="+ctrHand+",pairEarth="+Ppair.toString(Ppair));//~va11R~
        if (ctrHand==0)            //create for earth              //~va11I~
        {                                                          //~va11I~
        	pairSS=new Pair[1][];                                  //~va11I~
        	pairS=new Pair[PAIRS_MAX];                              //~va11I~
        	PpairSSS[Ptype]=pairSS;                                //~va11I~
        	pairSS[0]=pairS;                                       //~va11I~
        	Pctr[Ptype]=1;                                         //~va11I~
            ctrHand=1;                                             //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
        	pairSS=PpairSSS[Ptype];                                //~va11I~
    	for(int ii=0;ii<ctrHand;ii++)  //all pair pattern of the type//~va11R~
        {                                                          //~va11I~
        	pairS=pairSS[ii]; //a patern                           //~va11R~
        	for (int jj=0;jj<PAIRS_MAX;jj++)                       //~va11I~
            	if (pairS[jj]==null)                               //~va11I~
                {                                                  //~va11I~
                	pairS[jj]=Ppair;    //append earth pair        //~va11R~
                    break;                                         //~va11I~
                }                                                  //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UAPair.appendEarth return PpairSS="+Pair.toString(pairSS));//~va11R~
    }                                                              //~va11I~
    //******************************************************************//~va11I~
//  private void mixPatternNum()                                   //~va11R~//~vaguR~
    private void mixPatternNum_Old()                               //~vaguI~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UAPair.mixPatternNum entry ctrPairSSS="+ Arrays.toString(ctrPairSSS));//~va11R~//~vaguR~
    	int ctrTotal=1;                                            //~va11I~
    	for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                     //~va11I~
        {	                                                       //~va11I~
    		int ctr=ctrPairSSS[ii];                                //~va11I~
            ctrTotal*=Math.max(1,ctr);                             //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UAPair.mixPattern ctrTotal="+ctrTotal);//~va11I~
	    mixedSS=new Pair[ctrTotal][];                              //~va11R~
        int ctrMixedSS=0;                                              //~va11I~
        for (;;)                                                   //~va11I~
        {                                                          //~va11I~
		    Pair[] mixS=new Pair[PAIRS_MAX];                       //~va11R~
        	Pair[] pairS;                                          //~va11R~
        	int ctrMix=0;                                          //~va11I~
        	pairS=selectPair(TT_MAN);                                //~va11I~
            if (pairS!=null)                                        //~va11I~
            {                                                      //~va11I~
            	ctrSelect[TT_PIN]=0;                               //~va11I~
            	ctrSelect[TT_SOU]=0;                               //~va11I~
                ctrMix=appendMix(mixS,ctrMix,pairS);         //MAN  //~va11I~
	        	pairS=selectPair(TT_PIN);                            //~va11I~
                if (pairS!=null)                                    //~va11I~
	                ctrMix=appendMix(mixS,ctrMix,pairS);     //PIN  //~va11I~
	        	pairS=selectPair(TT_SOU);                            //~va11I~
                if (pairS!=null)                                    //~va11I~
	                ctrMix=appendMix(mixS,ctrMix,pairS);     //SOU  //~va11I~
                ctrMixedSS=addMix(ctrMixedSS,mixS);                //~va11I~
            }                                                      //~va11I~
            else                                                   //~va11I~
            {                                                      //~va11I~
                pairS = selectPair(TT_PIN);                            //~va11I~
                if (pairS != null)                                    //~va11I~
                {                                                  //~va11I~
                    ctrSelect[TT_SOU] = 0;                           //~va11I~
                    ctrMix = appendMix(mixS, ctrMix, pairS);        //PIN  //~va11I~
                    pairS = selectPair(TT_SOU);                        //~va11I~
                    if (pairS != null)                                //~va11I~
                        ctrMix = appendMix(mixS, ctrMix, pairS);     //SOU//~va11I~
                    ctrMixedSS = addMix(ctrMixedSS, mixS);            //~va11I~
                }                                                  //~va11I~
                else                                               //~va11I~
                {                                                  //~va11I~
                    pairS = selectPair(TT_SOU);                        //~va11I~
                    if (pairS != null)                                //~va11I~
                    {                                              //~va11I~
                        ctrMix = appendMix(mixS, ctrMix, pairS);        //~va11I~
                        ctrMixedSS = addMix(ctrMixedSS, mixS);        //~va11I~
                    }                                              //~va11I~
                    else                                           //~va11I~
                        break;                                     //~va11I~
                }
            }//~va11I~
	    	if (Dump.Y) Dump.println("UAPair.mixPatternNum forLoop ctrMix="+ctrMix+",ctrSelctr="+Utils.toString(ctrSelect));//~va11I~//~vaguR~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UAPair.mixPatternNum ctrMixedSS="+ctrMixedSS+",mixedSS="+Pair.toString(mixedSS));//~va11R~
    }                                                              //~va11I~
    //******************************************************************//~vaguI~
    private void mixPatternNum()                                   //+vaguR~
    {                                                              //~vaguI~
    	if (Dump.Y) Dump.println("UAPair.mixPatternNum entry ctrPairSSS="+ Arrays.toString(ctrPairSSS));//~vaguI~
    	int ctrTotal=1;                                            //~vaguI~
    	for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~vaguI~
        {                                                          //~vaguI~
    		int ctr=ctrPairSSS[ii];                                //~vaguI~
            ctrTotal*=Math.max(1,ctr);                             //~vaguI~
        }                                                          //~vaguI~
    	if (Dump.Y) Dump.println("UAPair.mixPattern ctrTotal="+ctrTotal);//~vaguI~
	    mixedSS=new Pair[ctrTotal][];                              //~vaguI~
        int ctrMixedSS=0;                                          //~vaguI~
        boolean swPin=false,swSou=false;                           //~vaguI~
		Pair[] mixS;                                               //~vaguI~
      	Pair[] pairS_MAN,pairS_PIN,pairS_SOU;                      //~vaguI~
        int ctrMix;                                                //~vaguI~
//      for (int ii=0;ii<ctrPairSSS[TT_MAN];ii++)                  //+vaguR~
        for (int ii=0;ii==0 || ii<ctrPairSSS[TT_MAN];ii++)         //+vaguI~
        {                                                          //~vaguI~
        	pairS_MAN=selectPair(TT_MAN,ii);                       //~vaguR~
//          for (int jj=0;jj<ctrPairSSS[TT_PIN];jj++)    //by Pin pair//+vaguR~
            for (int jj=0;jj==0 || jj<ctrPairSSS[TT_PIN];jj++)    //by Pin pair//+vaguI~
            {                                                      //~vaguI~
	        	pairS_PIN=selectPair(TT_PIN,jj);                   //~vaguR~
                for (int kk=0;kk<ctrPairSSS[TT_SOU];kk++)  //by Sou pair//~vaguR~
                {                                                  //~vaguI~
		        	pairS_SOU=selectPair(TT_SOU,kk);               //~vaguR~
                    if (pairS_SOU==null)                           //~vaguI~
                    	break;                                     //~vaguI~
				    mixS=new Pair[PAIRS_MAX];                      //~vaguI~
			        ctrMix=0;                                      //~vaguI~
                    if (pairS_MAN!=null)                           //~vaguI~
                		ctrMix=appendMix(mixS,ctrMix,pairS_MAN);         //MAN//~vaguI~
                    if (pairS_PIN!=null)                           //~vaguI~
                		ctrMix=appendMix(mixS,ctrMix,pairS_PIN);         //MAN//~vaguI~
                	ctrMix=appendMix(mixS,ctrMix,pairS_SOU);         //MAN//~vaguI~
	                ctrMixedSS=addMix(ctrMixedSS,mixS);            //~vaguI~
	                swSou=true;                                    //~vaguI~
                }                                                  //~vaguI~
                if (swSou)   //Pin appended with Sou               //+vaguM~
                	continue;                                      //+vaguM~
                //*no Sou pair                                     //+vaguI~
                if (pairS_PIN==null)                               //+vaguR~
                	break;                                         //~vaguI~
				mixS=new Pair[PAIRS_MAX];                          //~vaguI~
			    ctrMix=0;                                          //~vaguI~
	            if (pairS_MAN!=null)                               //~vaguI~
    	            ctrMix=appendMix(mixS,ctrMix,pairS_MAN);         //MAN//~vaguI~
                ctrMix=appendMix(mixS,ctrMix,pairS_PIN);         //MAN//~vaguI~
	            ctrMixedSS=addMix(ctrMixedSS,mixS);                //~vaguI~
	            swPin=true;                                        //~vaguI~
            }                                                      //~vaguI~
	    	if (Dump.Y) Dump.println("UAPair.mixPatternNum swPin="+swPin+",wsSou="+swSou+",pairS_MAN="+Pair.toString(pairS_MAN));//~vaguI~
            if (swPin || swSou)                                    //+vaguM~
            	continue;                                          //+vaguM~
            if (pairS_MAN==null)                                   //~vaguI~
                break;                                             //~vaguI~
            mixS=new Pair[PAIRS_MAX];                              //~vaguI~
			ctrMix=0;                                              //~vaguI~
            ctrMix=appendMix(mixS,ctrMix,pairS_MAN);         //MAN //~vaguI~
	        ctrMixedSS=addMix(ctrMixedSS,mixS);                    //~vaguI~
        }                                                          //~vaguI~
    	if (Dump.Y) Dump.println("UAPair.mixPatternNum ctrMixedSS="+ctrMixedSS+",mixedSS="+Pair.toString(mixedSS));//~vaguI~
    }                                                              //~vaguI~
    //*************************************************************//~va11I~
    private Pair[] selectPair(int Ptype)                           //~va11R~
    {                                                              //~va11I~
     	if (Dump.Y) Dump.println("UAPair.selectPair entry type="+Ptype+",ctrSelect[Ptype]="+ctrSelect[Ptype]+",pairSSS[Ptype]="+Pair.toString(pairSSS[Ptype]));//~va11R~
    	Pair[] pairS=null;                                         //~va11R~
    	if (ctrSelect[Ptype]<ctrPairSSS[Ptype])                    //~va11I~
        {                                                          //~va11I~
        	pairS=pairSSS[Ptype][ctrSelect[Ptype]++];            //~va11I~
        }                                                          //~va11I~
     	if (Dump.Y) Dump.println("UAPair.selectPair exit type="+Ptype+",ctrSelect[Ptype]="+ctrSelect[Ptype]+",pair="+Pair.toString(pairS));//~va11R~
        return pairS;
    }                                                              //~va11I~
    //*************************************************************//~vaguI~
    private Pair[] selectPair(int Ptype,int Pidx)                  //~vaguI~
    {                                                              //~vaguI~
    	Pair[] pairS=null;                                         //~vaguI~
    	if (Pidx<ctrPairSSS[Ptype])                                //~vaguI~
        {                                                          //~vaguI~
        	pairS=pairSSS[Ptype][Pidx];                            //~vaguI~
        }                                                          //~vaguI~
     	if (Dump.Y) Dump.println("UAPair.selectPair exit type="+Ptype+",idx="+Pidx+",ctrPairSSS[Ptype]="+ctrPairSSS[Ptype]+",pairS="+Pair.toString(pairS));//~vaguI~
        return pairS;                                              //~vaguI~
    }                                                              //~vaguI~
    //*************************************************************//~va11I~
    private int appendMix(Pair[] PpairSOut,int Pctr,Pair[] PpairSIn)//~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UPair.appendMix entry Pctr="+Pctr+",pairSIn="+Pair.toString(PpairSIn));//~va11R~
    	int ctr=Pctr;
    	for (int ii=0;ii<PpairSIn.length;ii++)
        {                                                          //~va11I~
    	   	if (PpairSIn[ii]==null)                                //~va11R~
            	break;                                             //~va11I~
	    	PpairSOut[ctr++]=PpairSIn[ii];                         //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UPair.appendMix exit ctr="+ctr+",pairS="+Pair.toString(PpairSOut));//~va11R~
        return ctr;                                                //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    private int addMix(int Pctr,Pair[] PpairS)                     //~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UAPair.addMix entry Pctr="+Pctr+",PpairS="+Pair.toString(PpairS));//~va11I~
        int ctr=Pctr;
        Pair[] pairS=shrinkPairS(PpairS,-1);                       //~va11R~
    	mixedSS[ctr++]=pairS;                                      //~va11R~
    	if (Dump.Y) Dump.println("UAPair.addMix exit ctr="+ctr+",mixedSS="+Pair.toString(mixedSS));//~va11R~
        return ctr;                                                //~va11I~
    }                                                              //~va11I~
    //*************************************************************//~va11I~
    //*no shrink if ctr=0                                          //~va11I~
    //*************************************************************//~va11I~
    private static Pair[] shrinkPairS(Pair[] PpairS,int Pctr)      //~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UAPair.shrinkPairS entry ctr="+Pctr+",PpairS="+Pair.toString(PpairS));//~va11I~
    	int ctr=Pctr;                                              //~va11I~
    	if (ctr==-1)                                               //~va11I~
        {                                                          //~va11I~
    	    for (int ii=0;ii<PpairS.length;ii++)                   //~va11I~
        	{                                                      //~va11I~
        		if (PpairS[ii]==null)                              //~va11I~
                {                                                  //~va11I~
                    ctr = ii;                                        //~va11I~
                    break;
                }//~va11I~
            }                                                      //~va11I~
            if (ctr==-1)                                           //~va11I~
	    	    ctr=PpairS.length;                                 //~va11I~
        }                                                          //~va11I~
        Pair[] pairS;                                              //~va11R~
        if (ctr!=0 && ctr!=PpairS.length)                         //~va11I~
        {                                                          //~va11I~
        	pairS=new Pair[ctr];                                   //~va11R~
			System.arraycopy(PpairS,0,pairS,0,ctr);                //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
        	pairS=PpairS;                                          //~va11I~
    	if (Dump.Y) Dump.println("UAPair.shrinkPairS exit ctr="+Pctr+",out PairS="+Pair.toString(pairS));//~va11R~
        return pairS;                                              //~va11I~
    }                                                              //~va11I~
}//class                                                           //~v@@@R~
