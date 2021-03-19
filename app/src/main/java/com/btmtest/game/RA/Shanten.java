//*CID://+va60R~:                             update#=   78;       //~1107I~//~va60R~
//*******************************************************          //~1107I~
//2021/01/07 va60 CalcShanten                                      //~va60I~
//*******************************************************          //~va60I~
package com.btmtest.game.RA;
                                                                   //~va60I~
import android.graphics.Point;

import com.btmtest.utils.Dump;                                     //~va60I~
import com.btmtest.utils.UFile;
import com.btmtest.utils.Utils;
import com.btmtest.dialog.RuleSettingYaku;                         //~va60I~
//~va60I~
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.StaticVars.AG;                           //~va60I~
                                                                   //~va60I~
import java.io.*;                                                  //~1106I~
import java.io.File;                                               //~1106I~
import java.io.FileInputStream;                                    //~1106I~
import java.io.IOException;                                        //~1106I~
import java.util.Arrays;                                           //~1106I~
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//*******************************************************          //~1106I~
public class Shanten                                               //~1106R~//~va60R~
{                                                                  //~1106I~
    private static final String TBLFNM_SHANTEN="calshan_bytetbl";     //~va60I~
                                                                   //~va60I~
                                                                   //~va60I~
	public static final int[] itsOrphans={0, 8, 9, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33};//~1106I~//~va60R~
//  { 1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125 };   //~1106M~
	private static final int[] powerFive={1/*0*/, 5/*1*/, 5*5/*2*/, 5*5*5/*3*/, 5*5*5*5/*4*/, 5*5*5*5*5/*5*/, 5*5*5*5*5*5/*6*/, 5*5*5*5*5*5*5/*7*/, 5*5*5*5*5*5*5*5/*8*/, 5*5*5*5*5*5*5*5*5/*9*/};//~1106M~
                                                                   //~1106I~
//  private  int[] itsHand=new int[CTR_MAXHAND];             //~1106I~//~va60R~
//  private  int[] itsTile=new int[CTR_TILETYPE];            //~1106R~//~va60R~
    private  int[] itsTileWork=new int[CTR_TILETYPE];              //~va60I~
    private  int[] itsTile;                                        //~va60I~
    private  boolean[] btsOrphan=new boolean[CTR_TILETYPE];  //~1107R~//~va60R~
    private  int ctrOrphan;                                        //~va60I~
    private  int[] itsShanten=new int[CTR_SHANTENTYPE];      //~1106I~//~va60R~
    private  int[] itsMeldAndPair=new int[4*CTR_NUMSUIT];    //~1106I~//~va60R~
                                                                   //~1106I~
    private  boolean sw7PairKan;                             //~1106I~//~va60R~
	private  byte[] tblShanten;                              //~1106R~//~va60R~
    private  FileWriter fwOut;                               //~1107I~//~va60R~
    private int pending13Orphan,pending7Pair;                      //~va60I~

//*******************************************                      //~1106I~
public Shanten()                                                   //~va60R~
{                                                                  //~1106I~
	AG.aShanten=this;                                              //~va60I~
	init(null);                                               //~va60R~
}                                                                  //~va60I~
public Shanten(String PfnmTbl,FileWriter PfwOut)                   //~va60R~
{                                                                  //~va60I~
	AG.aShanten=this;                                              //~va60I~
    fwOut=PfwOut;                                                  //~va60I~
	init(PfnmTbl);                                                 //~va60R~
}                                                                  //~va60I~
//*******************************************                      //~va60I~
private void init(String PfnmTbl)                                  //~va60R~
{                                                                  //~va60I~
	sw7PairKan=RuleSettingYaku.is7Pair4Pair();               //~9C11I~//~va60I~
	if (PfnmTbl==null) //test file                                 //~va60R~
    	tblShanten=readTableAsset(TBLFNM_SHANTEN+".zip");          //~va60I~
    else                                                           //~va60I~
    	tblShanten=readTableZipFile(PfnmTbl);                          //~va60R~
}                                                                  //~va60I~
//*******************************************                      //~va60I~
private byte[] readTable(String Pfnm)                              //~va60R~
{                                                                  //~va60I~
    byte[] buff=null;                                              //~1106I~
 	try                                                            //~1106I~
    {                                                              //~1106I~
		File file=new File(Pfnm);                                  //~1106I~
        int sz=(int)file.length();                                 //~1106R~
        buff=new byte[sz];                                         //~1106R~
		FileInputStream fis=new FileInputStream(Pfnm);             //~1106R~
		int len=fis.read(buff,0/*offs*/,sz);                       //~1106R~
//      System.out.println("readTable file sizw="+sz+",readlen="+len);//~1106I~//~va60R~
    	fis.close();                                               //~1106R~
    }                                                              //~1106I~
    catch(Exception e)                                             //~1106I~
    {                                                              //~1106I~
        Dump.println(e,"readTable file:"+Pfnm+";"+e.toString());//~1106I~//~va60R~
    }                                                              //~1106I~
    return buff;                                                   //~1106I~
}                                                                  //~1106I~
//********************************************************************//~va60I~
//*for unit test(could not access Asset)                           //~va60I~
//********************************************************************//~va60I~
private byte[] readTableZipFile(String Pzipfnm)                    //~va60R~
{                                                                  //~va60I~
  	FileInputStream fis=null;                                      //~va60I~
	try                                                            //~va60I~
    {                                                              //~va60I~
  		fis=new FileInputStream(Pzipfnm);                          //~va60R~
    }                                                              //~va60I~
    catch(IOException e)                                           //~va60I~
    {                                                              //~va60I~
        Dump.println(e,"readTableZipFile file:"+Pzipfnm+";"+e.toString());//~va60R~
    }                                                              //~va60I~
    byte[] buff=unzipFile(Pzipfnm,fis);                                    //~va60I~
    return buff;                                                   //~va60I~
}                                                                  //~va60I~
//********************************************************************//~va60I~
//*Asset file size has limit of 1MB, it should be zipped           //~va60I~
//********************************************************************//~va60I~
private byte[] readTableAsset(String Pzipfnm)                      //~va60R~
{                                                                  //~va60I~
	InputStream fis=UFile.openAssetFile(Pzipfnm,true/*showexception*/);//~va60I~
    if (fis==null)                                                 //~va60I~
    	return null;                                               //~va60I~
    byte[] buff=unzipFile(Pzipfnm,fis);                                    //~va60I~
    return buff;                                                   //~va60I~
}                                                                  //~va60I~
//********************************************************************//~va60I~
private byte[] unzipFile(String Pfnm,InputStream Pis)              //~va60R~
{                                                                  //~va60I~
    byte[] buff=null;                                              //~va60I~
	try                                                            //~va60I~
    {                                                              //~va60I~
    	BufferedInputStream bis=new BufferedInputStream(Pis);      //~va60I~
    	ZipInputStream zis=new ZipInputStream(bis);                //~va60I~
        ZipEntry ze=zis.getNextEntry();                            //~va60I~
        int sz=(int)ze.getSize();                                  //~va60I~
//      System.out.println("readTable file size="+sz);             //~va60R~
        buff=new byte[sz];                                         //~va60I~
        int offs=0;                                                //~va60I~
        int unit=0x10000; //64K, cannot read at once fully         //~va60R~
        while(true)                                                //~va60I~
        {                                                          //~va60I~
            int reqlen=Math.min(unit,sz-offs);                     //~va60I~
//	        if (Dump.Y) Dump.println("Shanten.unzipFile file offs="+offs+",unit="+unit+",reqlen="+reqlen+",sz="+sz);//~va60R~
            if (reqlen<=0)                                         //~va60I~
            	break;                                             //~va60I~
			int len=zis.read(buff,offs,reqlen);                    //~va60R~
//	        if (Dump.Y) Dump.println("Shanten.unzipFile file len="+len);//~va60R~
            if (len==-1)                                           //~va60I~
            	break;                                             //~va60I~
            offs+=len;                                             //~va60I~
        }                                                          //~va60I~
        zis.close();                                               //~va60I~
    }                                                              //~va60I~
    catch(Exception e)                                             //~va60I~
    {                                                              //~va60I~
        Dump.println(e,"unzipTable file:"+Pfnm+";"+e.toString());  //~va60R~
    }                                                              //~va60I~
    return buff;                                                   //~va60I~
}                                                                  //~va60I~
//*******************************************                      //~1106I~
private int getHash(int PidxNumSuit)                        //~1106R~//~va60R~
{                                                                  //~1106I~
    int hash=0;                                                    //~1106R~//~1107R~
    int pos=CTR_NUMBER_TILE*PidxNumSuit;                           //~1107I~
    for (int ii=0;ii<CTR_NUMBER_TILE;ii++)                         //~1106R~
    {                                                              //~1106R~
        int ctr=itsTile[pos];           //~1106R~                  //~1107R~
        if (ctr>4)                                                 //~va60I~
        {                                                          //~va60I~
			if (Dump.Y) Dump.println("Shanten.getHash @@@@err ctr="+ctr+">4");//~va60I~
        	hash=-1;                                               //~va60I~
            break;                                                 //~va60I~
        }                                                          //~va60I~
        hash+=ctr*powerFive[ii];                                    //~1106R~//~1107R~
        pos++;                                                     //~1107I~
    }                                                              //~1106R~
//  System.out.println("getHash idx="+PidxNumSuit+",hash="+hash);  //~1107I~
    return hash;                                                   //~1106R~
}                                                                  //~1106I~
//*******************************************                      //~1106I~
private void getMeldAndPair(int[] PmeldAndPair/*[3][4]*/)        //~1106R~//~1107R~//~va60R~
{                                                                  //~1106I~
	int meldPair;                                                  //~1106I~
    for (int ii=0;ii<CTR_NUMSUIT/*3*/;ii++)                             //~1106R~//~va60R~
    {                                                              //~1106I~
		int hash=getHash(ii);                                      //~1106I~
        if (hash>=tblShanten.length)                               //~1107I~
            hash=-1;                                                //~1107I~//~va60R~
        if (hash<0)                                                //~va60I~
        {                                                          //~va60I~
            hash=0;                                                //~va60I~
        }                                                          //~va60I~
    	int meldPair1=(int)tblShanten[hash*2  ];                   //~1106R~
    	int meldPair2=(int)tblShanten[hash*2+1];                   //~1106R~
//  System.out.println("getMeldAndPair pair1="+meldPair1+",2="+meldPair2);//~1107I~
        PmeldAndPair[ii*4  ]=(meldPair1 & 0xf0)>>4;     //B.meld   //~1106R~
        PmeldAndPair[ii*4+1]=meldPair1 & 0x0f;          //B.candidate//~1106R~
        PmeldAndPair[ii*4+2]=(meldPair2 & 0xf0)>>4;     //A.meld   //~1106R~
        PmeldAndPair[ii*4+3]=meldPair2 & 0x0f;          //A.candidate//~1106R~
    }                                                              //~1106I~
}                                                                  //~1106I~
//*******************************************                      //~1106I~
private int getShanten_NormalSub(int PmaxMeld)              //~1106R~//~va60R~
{                                                                  //~1106I~
  	int meldWord=0,pairWord=0;                                     //~1106I~
    int ctrMeld=0,ctrPair=0;                                       //~1106I~
  	for (int ii=OFFS_WORDTILE;ii<CTR_TILETYPE;ii++)                //~1106R~
    {                                                              //~1106I~
    	int ctr=itsTile[ii];                                       //~1106R~
    	if(ctr>=3)                                                 //~1106I~
            meldWord++;                                            //~1106I~
        else                                                       //~1106I~
    	if(ctr==2)                                                 //~1106I~
            pairWord++;                                            //~1106I~
  	}                                                              //~1106I~
//  System.out.println("NormalSub meldWord="+meldWord+",pairWord="+pairWord);//~1107R~
  	getMeldAndPair(itsMeldAndPair);  //[3/*num suit idx*/][4/*(ctrMeld+ctrPair)*2*/]                       //~1106R~//~1107R~
  	int rc=CTR_MAXHAND;                                            //~1106R~
  	for (int ii=0;ii<2;ii++)        //B and A                      //~1106R~
    {                                                              //~1106I~
    	ctrMeld=meldWord;                                          //~1106R~
    	ctrPair=pairWord;                                          //~1106R~
                                                                   //~1107I~
	  	for (int jj=0;jj<CTR_NUMSUIT;jj++)                         //~1106R~
        {                                                          //~1106I~
        	ctrMeld+=itsMeldAndPair[jj*4+ii*2];                    //~1106R~
        	ctrPair+=itsMeldAndPair[jj*4+ii*2+1];                  //~1106R~
//  System.out.println("NormalSub ctrMeld="+ctrMeld+",ctrPair="+ctrPair);//~1107R~
        }                                                          //~1106I~
    }                                                              //~1106I~
    ctrPair=Math.min(ctrPair,PmaxMeld-ctrMeld);                    //~1106R~
//  System.out.println("NormalSub ctrMeld="+ctrMeld+",ctrPair="+ctrPair+",PmaxMeld="+PmaxMeld);//~1107R~
    rc=Math.min(rc,8-2*ctrMeld-ctrPair);                           //~1106R~
  	return rc;                                                     //~1106I~
}                                                                  //~1106I~
//*******************************************                      //~1107I~
private void dropOrphan(int[] PitsTile,boolean PswDrop)                      //~1107I~//~va60R~
{                                                                  //~1107I~
    int pos=0;                                                     //~1107R~
	Arrays.fill(btsOrphan,false);                                  //~1107R~
    ctrOrphan=0;                                                   //~va60I~
//*number tile                                                     //~1107I~
    for (int ii=0;ii<CTR_NUMSUIT;ii++)                             //~1107I~
    {                                                              //~1107I~
        for (int jj=0;jj<CTR_NUMBER_TILE;jj++)                     //~1107R~
        {                                                          //~1107I~
            if (PitsTile[pos]==1)                                   //~1107R~//~va60R~
            {                                                      //~1107I~
                boolean swOrphan;                                  //~1107I~
            	switch(jj)                                         //~1107I~
                {                                                  //~1107I~
                	case 0:                                        //~1107I~
		                swOrphan=(PitsTile[pos+1]==0 && PitsTile[pos+2]==0);//~1107I~//~va60R~
                        break;                                     //~1107I~
                	case 1:                                        //~1107I~
		                swOrphan=(PitsTile[pos-1]==0 && PitsTile[pos+1]==0 && PitsTile[pos+2]==0);//~1107I~//~va60R~
                        break;                                     //~1107I~
                	case 8:                                        //~1107I~
                        swOrphan=(PitsTile[pos-1]==0 && PitsTile[pos-2]==0);//~1107I~//~va60R~
                        break;                                     //~1107I~
                	case 7:                                        //~1107I~
                        swOrphan=(PitsTile[pos+1]==0 && PitsTile[pos-1]==0 && PitsTile[pos-2]==0);//~1107I~//~va60R~
                        break;                                     //~1107I~
                    default:                                       //~1107I~
                        swOrphan=(PitsTile[pos-2]==0 && PitsTile[pos-1]==0 && PitsTile[pos+1]==0 && PitsTile[pos+2]==0);//~1107I~//~va60R~
                }                                                  //~1107I~
                if (swOrphan)                                      //~1107R~
                {                                                  //~1107R~
//                  System.out.println("dropOrphan="+pos);         //~1107R~
                    btsOrphan[pos]=true;                           //~1107R~
                    ctrOrphan++;                                   //~va60I~
                }                                                  //~1107R~
            }                                                      //~1107I~
            pos++;                                                 //~1107I~
        }                                                          //~1107I~
    }                                                              //~1107I~
//*word tile                                                       //~1107I~
    for (int ii=pos;ii<CTR_TILETYPE;ii++)                          //~1107I~
    {                                                              //~1107I~
        if (PitsTile[ii]==1)                                        //~1107I~//~va60R~
        {                                                          //~va60I~
    		btsOrphan[ii]=true;                                    //~1107I~
            ctrOrphan++;                                           //~va60I~
        }                                                          //~va60I~
    }                                                              //~1107I~
//*drop orphan                                                     //~1107I~
	if (PswDrop)                                                   //~va60I~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                            //~1107R~//~va60R~
        {                                                              //~1107I~//~va60R~
            if (btsOrphan[ii])                                         //~1107I~//~va60R~
                PitsTile[ii]=0;                                         //~1107I~//~va60R~
        }                                                              //~1107I~//~va60R~
    if (Dump.Y) Dump.println("Shanten.dropOrphan exit btsOrphan="+ Utils.toString(btsOrphan,9));//~va60I~
}                                                                  //~1107I~
//*******************************************                      //~va60I~
private void restoreOrphan(int[] PitsTile)                         //~va60I~
{                                                                  //~va60I~
    for (int ii=0;ii<CTR_TILETYPE;ii++)                            //~va60I~
    {                                                              //~va60I~
        if (btsOrphan[ii])                                         //~va60I~
            PitsTile[ii]=1;                                        //~va60I~
    }                                                              //~va60I~
}                                                                  //~va60I~
//*******************************************                      //~va60I~
public static boolean[] chkOrphan(int[] PitsTile, Point Ppoint)     //~va60R~
{                                                                  //~va60I~
	AG.aShanten.dropOrphan(PitsTile,false/*swDrop*/);                            //~va60I~
    Ppoint.x=AG.aShanten.ctrOrphan;                                            //~va60I~
    Ppoint.y=0;                                                    //~va60I~
    if (Dump.Y) Dump.println("Shanten.chkOrphan ctrOrphan="+Ppoint.toString()+",btsOrphan="+ Utils.toString(AG.aShanten.btsOrphan,9));//~va60R~
    return AG.aShanten.btsOrphan;                                              //~va60I~
}                                                                  //~va60I~
//*******************************************************          //~1106I~
private int getShanten_Normal(int PmaxMeld)                 //~1106R~//~va60R~
{                                                                  //~1106I~
	int rc=CTR_MAXHAND,shanten;                                    //~1106R~
    //****************                                             //~1106I~
    if (Dump.Y) Dump.println("Shanten.getShanten_Normal entry itsTile="+ Utils.toString(itsTile,9));//~va60I~
    dropOrphan(itsTile,true/*drop*/);                                                  //~1107R~//~va60R~
	for (int ii=0;ii<CTR_TILETYPE;ii++)                             //~1106R~//~1107R~
	{                                                              //~1106I~
		if (itsTile[ii]>=2)                                        //~1106R~
		{                                                          //~1106I~
			itsTile[ii]-=2;                                        //~1106R~
            shanten=getShanten_NormalSub(PmaxMeld)-1;              //~1106R~
			itsTile[ii]+=2;                                        //~1106R~
			rc=Math.min(rc,shanten);                               //~1106I~//~1107M~
        }                                                          //~1106I~
    }                                                              //~1106I~
    shanten=getShanten_NormalSub(PmaxMeld);                        //~1106R~
    if (ctrOrphan!=0)                                                 //~va60I~
		restoreOrphan(itsTile);                                    //~va60I~
    if (Dump.Y) Dump.println("Shanten.getShanten_Normal exit itsTile="+ Utils.toString(itsTile,9));//~va60R~
	rc=Math.min(rc,shanten);                                       //~1106I~
	rc=rc-(CTR_MAXMELD-PmaxMeld)*2;                                //~1106R~
    return rc;                                                     //~1106R~
}                                                                  //~1106I~
//*******************************************************          //~1106I~
public int getShanten_13Orphan(int[] PitsTile)                           //~1106R~//~va60R~
{                                                                  //~1106I~
    int ctrOrphan=0,head=0;                                        //~1106R~
    pending13Orphan=CTR_TILETYPE; 	//13wait id                    //~va60I~
	for (int ii=0;ii<itsOrphans.length;ii++)                       //~1106R~
    {                                                              //~1106I~
    	int pos=itsOrphans[ii];                           //~1106R~//~va60R~
    	int ctr=PitsTile[pos];                                     //~va60I~
    	if (ctr!=0)                                                //~1106I~
        {                                                          //~1106I~
        	ctrOrphan++;                                           //~1106R~
            if (ctr>=2)                                            //~1106I~
            	head++;                                            //~1106I~
        }                                                          //~1106I~
        else                                                       //~va60I~
        	pending13Orphan=pos;                                   //~va60I~
    }                                                              //~1106I~
    int rc=13-(ctrOrphan+(head!=0 ? 1 : 0));                       //~1106R~
    return rc;                                                     //~1106I~
}                                                                  //~1106I~
//*******************************************************          //~va60I~
//*return head index,-1:not 13Orphan win,34:13wait                 //~va60I~
//*******************************************************          //~va60I~
public int getWin_13Orphan(int[] PitsTile)                         //~va60I~
{                                                                  //~va60I~
	int shanten=getShanten_13Orphan(PitsTile);                      //~va60I~
    int rc=(shanten==0 ? pending13Orphan : -1);                    //~va60I~
    if (Dump.Y) Dump.println("Shanten.getWin_13Orphan rc="+rc);    //~va60I~
    return rc;                                                     //~va60I~
}                                                                  //~va60I~
//*******************************************************          //~1106I~
private int getShanten_7Pair(int[] PitsTile)                              //~1106R~//~va60R~
{                                                                  //~1106I~
    int ctrPair=0,ctrType=0;                                                 //~1106R~//~1107R~
    pending7Pair=-1;                                                 //~va60I~
    for (int ii=0;ii<CTR_TILETYPE;ii++)                            //~1106I~
    {                                                              //~1106I~
        int ctr=PitsTile[ii];                                       //~1106I~//~va60R~
//  	System.out.println("7pair ii="+ii+",ctr="+ctr);            //~1106I~
    	if (ctr==4)                                                //~1106I~
        {                                                          //~1106I~
        	if (sw7PairKan)                                        //~1106I~
            {                                                      //~va60I~
	        	ctrPair+=2;                                        //~1106I~
                ctrType++;                                         //~va60I~
            }                                                      //~va60I~
            else                                                   //~1106I~
	        	ctrPair++;                                         //~1106I~
        }                                                          //~1106I~
        else                                                       //~1106I~
    	if (ctr>=2)                                                //~1106I~//~va60R~
        {                                                          //~va60I~
        	if (ctr==3 && sw7PairKan)                              //+va60R~
	        	pending7Pair=ii;                                   //~va60I~
        	ctrPair++;                                             //~1106I~
        }                                                          //~va60I~
        else                                                       //~va60I~
    	if (ctr==1)                                                 //~va60I~
        	pending7Pair=ii;                                       //~va60I~
                                                                   //~va60I~
        if (ctr!=0)                                                //~1107I~
            ctrType++;                                             //~1107I~
//  	if (Dump.Y) Dump.println("Shanten.getShanten_7Pair ctr="+ctr+",ctrType="+ctrType+",pending7Pair="+pending7Pair+",ctrPair="+ctrPair);//~va60R~
    }                                                              //~1106I~
	int rc=6-ctrPair+(7>ctrType ? 7-ctrType : 0);                  //~1107R~
//  if (Dump.Y) Dump.println("Shanten.getShanten_7Pair sw7PairKan="+sw7PairKan+",rc="+rc);//~va60R~
    return rc;                                                     //~1106I~
}                                                                  //~1106I~
//*******************************************************          //~va60I~
//*return pending index,-1:not 7pair at shanten=0                  //~va60I~
//*******************************************************          //~va60I~
public int getWin_7Pair(int[] PitsTile)                            //~va60I~
{                                                                  //~va60I~
	int shanten=getShanten_7Pair(PitsTile);                        //~va60I~
    int rc=(shanten==0 ? pending7Pair : -1);                       //~va60I~
    if (Dump.Y) Dump.println("Shanten.getWin_7Pair rc="+rc);
    return rc;//~va60I~
}                                                                  //~va60I~
//*******************************************                      //~1107I~
//public void countTile(int[] PitsHand,int PctrHand)                                    //~1107I~//~va60R~
public void countTile(int[] PitsHand,int PctrHand,int[] PitsTile)  //~va60I~
{                                                                  //~1107I~
//  Arrays.fill(itsTile,0);                                        //~1106R~//~1107M~//~va60R~
    Arrays.fill(PitsTile,0);                                       //~va60I~
    for (int ii=0;ii<PctrHand;ii++)                             //~1107I~//~va60R~
    {                                                              //~1107I~
//  	itsTile[PitsHand[ii]]++;                                    //~1107I~//~va60R~
    	PitsTile[PitsHand[ii]]++;                                  //~va60I~
    }                                                              //~1107I~
}                                                                  //~1107I~
//*******************************************                      //~1106M~
private void getShanten(int PmaxMeld)                       //~1106R~//~va60R~
{                                                                  //~1106M~
//  countTile();                                                   //~1107R~//~va60R~
	itsShanten[0]=getShanten_Normal(PmaxMeld);                     //~1106R~//~1107M~//~va60M~
    if (PmaxMeld==4)                                               //~va60I~
    {                                                              //~va60I~
		itsShanten[1]=getShanten_13Orphan(itsTile);                           //~1106R~//~va60R~
		itsShanten[2]=getShanten_7Pair(itsTile);                              //~1106R~//~va60R~
    }                                                              //~va60I~
    else                                                           //~va60I~
    {                                                              //~va60I~
		itsShanten[1]=MAX_SHANTEN;                                 //~va60I~
		itsShanten[2]=MAX_SHANTEN;                                 //~va60I~
    }                                                              //~va60I~
//  System.out.println(itsShanten[0]+" "+itsShanten[1]+" "+itsShanten[2]);//~1106R~//~va60R~
    if (Dump.Y) Dump.println("Shanten.getShanten PmaxMeld="+PmaxMeld+",itsShanten="+Arrays.toString(itsShanten));//~va60R~
    if (AG.aShanten.fwOut!=null)                                               //~1107I~//~va60R~
        writeLine(itsShanten[0]+" "+itsShanten[1]+" "+itsShanten[2]);//~1107I~//~va60R~
}                                                                  //~1106M~
//***********************************************                  //~va60I~
// man:0-8, pin:9-17, sou:18-26, ji:27-30,31-33                    //~va60I~
//***********************************************                  //~va60I~
    public void getShanten(int[] Phand,int PctrHand)               //~va60R~
    {                                                              //~va60I~
//      itsHand=Phand;                                             //~va60R~
//      countTile(PctrHand);                                       //~va60R~
//      countTile(Phand,PctrHand);                                 //~va60R~
        countTile(Phand,PctrHand,itsTileWork);                     //~va60R~
        itsTile=itsTileWork;                                       //~va60R~
		getShanten(PctrHand/3);                                    //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~va60I~
    public int[] getShantenNew(int[] PitsTile,int PctrHand)        //~va60R~
    {                                                              //~va60I~
//    	System.arraycopy(PitsTile,0,itsTile,0,itsTile.length);     //~va60R~
      	itsTile=PitsTile;                                          //~va60I~
		getShanten(PctrHand/3);                                    //~va60I~
    	if (Dump.Y) Dump.println("Shanten.getShantenNew rc="+Arrays.toString(itsShanten));//~va60I~
		return itsShanten;                                         //~va60R~
    }                                                              //~va60I~
//***********************************************                  //~va60I~
    public int getShantenNewNormal(int[] PitsTile,int PctrHand)    //~va60I~
    {                                                              //~va60I~
      	itsTile=PitsTile;                                          //~va60I~
		int rc=getShanten_Normal(PctrHand/3);                      //~va60I~
    	if (Dump.Y) Dump.println("Shanten.getShantenNewNormal rc="+rc);//~va60I~
		return rc;                                                 //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~va60I~
    public static int chkShanten0(int[] PitsTile,int PctrHand)     //~va60I~
    {                                                              //~va60I~
    	int[] result=AG.aShanten.getShantenNew(PitsTile,PctrHand); //~va60I~
		int rc=(result[0]==0 ? SHANTEN_STANDARD : 0)    //1        //~va60R~
		      +(result[1]==0 ? SHANTEN_13ORPHAN : 0)    //2        //~va60R~
		      +(result[2]==0 ? SHANTEN_7PAIR    : 0);   //4        //~va60R~
    	if (Dump.Y) Dump.println("Shanten.chkShanten0 rc="+rc);    //~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~va60I~
    public static int getShantenMin(int[] PitsTile,int PctrHand)   //~va60R~
    {                                                              //~va60I~
    	int[] result=AG.aShanten.getShantenNew(PitsTile,PctrHand); //~va60I~
        int rc=MAX_SHANTEN;                                        //~va60I~
        for (int ii=0;ii<3;ii++)                                   //~va60I~
			if (rc>result[ii])                                     //~va60R~
            	rc=result[ii];                                     //~va60I~
    	if (Dump.Y) Dump.println("Shanten.getShantenMin rc="+rc);  //~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~va60I~
    public static int getShantenMinFlag(int[] PitsTile,int PctrHand)//~va60I~
    {                                                              //~va60I~
    	int[] result=AG.aShanten.getShantenNew(PitsTile,PctrHand); //~va60I~
        int rc=MAX_SHANTEN;                                        //~va60I~
        int flag=0;                                                //~va60I~
        for (int ii=0;ii<3;ii++)                                   //~va60I~
        {                                                          //~va60I~
			if (rc>result[ii])                                     //~va60R~
            	rc=result[ii];                                     //~va60I~
        }                                                          //~va60I~
        for (int ii=0;ii<3;ii++)                                   //~va60I~
        {                                                          //~va60I~
            if (rc == result[ii])                                    //~va60I~
                flag |= SHANTEN_STANDARD << ii;    //0x01,0x02 0x04       //~va60I~
        }                                                          //+va60I~        rc|=flag<<8;                                               //~va60I~
        rc=(rc & 0xff) | (flag<<8);                                //~va60R~
    	if (Dump.Y) Dump.println("Shanten.getShantenMinFlag rc="+Integer.toHexString(rc));//~va60R~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~va60I~
    public static int chkWin(int[] PitsTile,int PctrHand)          //~va60I~
    {                                                              //~va60I~
    	int[] result=AG.aShanten.getShantenNew(PitsTile,PctrHand); //~va60I~
		int rc=(result[0]==-1 ? SHANTEN_STANDARD : 0)              //~va60I~
		      +(result[1]==-1 ? SHANTEN_13ORPHAN : 0)              //~va60I~
		      +(result[2]==-1 ? SHANTEN_7PAIR    : 0);             //~va60I~
    	if (Dump.Y) Dump.println("Shanten.chkWin ctrHand="+PctrHand+",rc="+rc);//~va60R~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~va60I~
    public static int chkWinStandard(int[] PitsTile,int PctrHand)  //~va60I~
    {                                                              //~va60I~
//    	System.arraycopy(PitsTile,0,AG.aShanten.itsTile,0,PitsTile.length);//~va60R~
      	AG.aShanten.itsTile=PitsTile;                                          //~va60I~
		int shanten=AG.aShanten.getShanten_Normal(PctrHand/3);     //~va60I~
    	if (Dump.Y) Dump.println("Shanten.chkWinStandard shanten="+shanten);//~va60I~
        return shanten;                                            //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~1107I~//~va60R~
//for ITShanten test                                               //~va60I~
//***********************************************                  //~va60I~
    private static void writeLine(String Ptext)                    //~1107I~//~va60R~
    {                                                              //~1107I~//~va60R~
        try                                                        //~1107I~//~va60R~
        {                                                          //~1107I~//~va60R~
            AG.aShanten.fwOut.write(Ptext+"\n");                                    //~1107I~//~va60R~
        }                                                          //~1107I~//~va60R~
        catch(IOException e)                                       //~1107I~//~va60R~
        {                                                          //~1107I~//~va60R~
            Dump.println(e,"writeLine text="+Ptext+",exception="+e.toString());//~1107I~//~va60R~
        }                                                          //~1107I~//~va60R~
    }                                                              //~1107I~//~va60R~
}//class                                                           //~1106R~
