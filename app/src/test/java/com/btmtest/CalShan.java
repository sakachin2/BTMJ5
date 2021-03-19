//*CID://+dateR~:                             update#=   20;       //~1107I~
//*******************************************************          //~1107I~
//2021/01/07 va60 CalcShanten                                      //~1107I~
//*******************************************************          //~1107I~
//*For unit test without BTMJ5 env                                 //+1107R~
//*test file read and output to file                               //~1107I~
//*******************************************************          //~1107I~
package com.btmtest.game.RA;
import java.io.*;                                                  //~1106I~
import java.io.File;                                               //~1106I~
import java.io.FileInputStream;                                    //~1106I~
import java.io.FileReader;                                         //~1106I~
import java.io.BufferedReader;                                     //~1106I~
import java.io.IOException;                                        //~1106I~
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;                                           //~1106I~
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//*******************************************************          //~1106I~
public class CalShan                                               //~1106R~
{                                                                  //~1106I~
    private static final int CTR_TILETYPE=34;                      //~1106I~
    private static final int OFFS_WORDTILE=3*9;                    //~1106I~
    private static final int CTR_NUMSUIT=3;                        //~1106I~
    private static final int CTR_NUMBER_TILE=9;                    //~1106R~
    private static final int CTR_MAXHAND=14;                       //~1106I~
    private static final int CTR_MAXMELD=4;                        //~1106I~
    private static final int CTR_SHANTENTYPE=3;	//normal,13orphan,7pair//~1106I~
	private static final int[] itsOrphans={0, 8, 9, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33};//~1106I~
//  { 1, 5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125 };   //~1106M~
	private static final int[] powerFive={1/*0*/, 5/*1*/, 5*5/*2*/, 5*5*5/*3*/, 5*5*5*5/*4*/, 5*5*5*5*5/*5*/, 5*5*5*5*5*5/*6*/, 5*5*5*5*5*5*5/*7*/, 5*5*5*5*5*5*5*5/*8*/, 5*5*5*5*5*5*5*5*5/*9*/};//~1106M~
                                                                   //~1106I~
    private static int[] itsHand=new int[CTR_MAXHAND];             //~1106I~
    private static int[] itsTile=new int[CTR_TILETYPE];            //~1106R~
    private static boolean[] btsOrphan=new boolean[CTR_TILETYPE];  //~1107R~
    private static int[] itsShanten=new int[CTR_SHANTENTYPE];      //~1106I~
    private static int[] itsMeldAndPair=new int[4*CTR_NUMSUIT];    //~1106I~
                                                                   //~1106I~
    private static boolean sw7PairKan;                             //~1106I~
	private static byte[] tblShanten;                              //~1106R~
	private static FileWriter fwOut;                               //~1107I~

//*******************************************                      //~1107I~
private static byte[] unzipTable(String Pfnm)                      //~1107R~
{                                                                  //~1107I~
    System.out.println("unzipTable fnm="+Pfnm);                    //~1107I~
    byte[] buff=null;                                              //~1107I~
	try                                                            //~1107I~
    {                                                              //~1107I~
    	FileInputStream fis=new FileInputStream(Pfnm);             //~1107I~
    	BufferedInputStream bis=new BufferedInputStream(fis);      //~1107I~
    	ZipInputStream zis=new ZipInputStream(bis);                //~1107R~
        ZipEntry ze=zis.getNextEntry();
        int sz=(int)ze.getSize();                                  //~1107R~
        System.out.println("readTable file size="+sz);             //~1107I~
        buff=new byte[sz];                                         //~1107I~
        int offs=0;                                                //~1107I~
        int reqlen=0x10000; //64K, cannot read at once fully                                      //~1107I~
        while(true)                                                //~1107I~
        {                                                          //~1107I~
			int len=zis.read(buff,offs,reqlen);                    //~1107R~
	        System.out.println("readTable file len="+len+",offs="+offs);//~1107I~
            if (len==-1)                                           //~1107I~
            	break;                                             //~1107I~
            offs+=len;                                             //~1107I~
        }                                                          //~1107I~
        zis.close();                                               //~1107I~
    }                                                              //~1107I~
    catch(Exception e)                                             //~1107I~
    {                                                              //~1107I~
        System.out.println("unzipTable file:"+Pfnm+";"+e.toString());//~1107I~
    }                                                              //~1107I~
    return buff;                                                   //~1107I~
}                                                                  //~1107I~
//*******************************************                      //~1106I~
private static byte[] readTable(String Pfnm)                       //~1106R~
{                                                                  //~1106I~
	if (Pfnm.endsWith(".zip"))                                     //~1107I~
    {                                                              //~1107I~
    	return unzipTable(Pfnm);                                   //~1107R~
    }                                                              //~1107I~
    byte[] buff=null;                                              //~1106I~
 	try                                                            //~1106I~
    {                                                              //~1106I~
		File file=new File(Pfnm);                                  //~1106I~//~1107R~
        int sz=(int)file.length();                                 //~1106R~
        buff=new byte[sz];                                         //~1106R~
		FileInputStream fis=new FileInputStream(Pfnm);             //~1106R~//~1107R~
		int len=fis.read(buff,0/*offs*/,sz);                       //~1106R~
        System.out.println("readTable file size="+sz+",readlen="+len);//~1106I~//~1107R~
    	fis.close();                                               //~1106R~
    }                                                              //~1106I~
    catch(Exception e)                                             //~1106I~
    {                                                              //~1106I~
        System.out.println("readTable file:"+Pfnm+";"+e.toString());//~1106I~//~1107R~
    }                                                              //~1106I~
    return buff;                                                   //~1106I~
}                                                                  //~1106I~
//*******************************************                      //~1106I~
private static int getHash(int PidxNumSuit)                        //~1106R~
{                                                                  //~1106I~
    int hash=0;                                                    //~1106R~//~1107R~
    int pos=CTR_NUMBER_TILE*PidxNumSuit;                           //~1107I~
    for (int ii=0;ii<CTR_NUMBER_TILE;ii++)                         //~1106R~
    {                                                              //~1106R~
        int ctr=itsTile[pos];           //~1106R~                  //~1107R~
        hash+=ctr*powerFive[ii];                                    //~1106R~//~1107R~
        pos++;                                                     //~1107I~
    }                                                              //~1106R~
//  System.out.println("getHash idx="+PidxNumSuit+",hash="+hash);  //~1107I~
    return hash;                                                   //~1106R~
}                                                                  //~1106I~
//*******************************************                      //~1106I~
private static void getMeldAndPair(int[] PmeldAndPair/*[3][4]*/)        //~1106R~//~1107R~
{                                                                  //~1106I~
	int meldPair;                                                  //~1106I~
    for (int ii=0;ii<CTR_NUMSUIT;ii++)                             //~1106R~
    {                                                              //~1106I~
		int hash=getHash(ii);                                      //~1106I~
        if (hash>=tblShanten.length)                               //~1107I~
            hash=0;                                                //~1107I~
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
private static int getShanten_NormalSub(int PmaxMeld)              //~1106R~
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
private static void dropOrphan()                                   //~1107I~
{                                                                  //~1107I~
    int pos=0;                                                     //~1107R~
	Arrays.fill(btsOrphan,false);                                  //~1107R~
//*number tile                                                     //~1107I~
    for (int ii=0;ii<CTR_NUMSUIT;ii++)                             //~1107I~
    {                                                              //~1107I~
        for (int jj=0;jj<CTR_NUMBER_TILE;jj++)                     //~1107R~
        {                                                          //~1107I~
            if (itsTile[pos]==1)                                   //~1107R~
            {                                                      //~1107I~
                boolean swOrphan;                                  //~1107I~
            	switch(jj)                                         //~1107I~
                {                                                  //~1107I~
                	case 0:                                        //~1107I~
		                swOrphan=(itsTile[pos+1]==0 && itsTile[pos+2]==0);//~1107I~
                        break;                                     //~1107I~
                	case 1:                                        //~1107I~
		                swOrphan=(itsTile[pos-1]==0 && itsTile[pos+1]==0 && itsTile[pos+2]==0);//~1107I~
                        break;                                     //~1107I~
                	case 8:                                        //~1107I~
                        swOrphan=(itsTile[pos-1]==0 && itsTile[pos-2]==0);//~1107I~
                        break;                                     //~1107I~
                	case 7:                                        //~1107I~
                        swOrphan=(itsTile[pos+1]==0 && itsTile[pos-1]==0 && itsTile[pos-2]==0);//~1107I~
                        break;                                     //~1107I~
                    default:                                       //~1107I~
                        swOrphan=(itsTile[pos-2]==0 && itsTile[pos-1]==0 && itsTile[pos+1]==0 && itsTile[pos+2]==0);//~1107I~
                               ;                                   //~1107I~
                }                                                  //~1107I~
                if (swOrphan)                                      //~1107R~
                {                                                  //~1107R~
//                  System.out.println("dropOrphan="+pos);         //~1107R~
                    btsOrphan[pos]=true;                           //~1107R~
                }                                                  //~1107R~
            }                                                      //~1107I~
            pos++;                                                 //~1107I~
        }                                                          //~1107I~
    }                                                              //~1107I~
//*word tile                                                       //~1107I~
    for (int ii=pos;ii<CTR_TILETYPE;ii++)                          //~1107I~
    {                                                              //~1107I~
        if (itsTile[ii]==1)                                        //~1107I~
    		btsOrphan[ii]=true;                                    //~1107I~
    }                                                              //~1107I~
//*drop orphan                                                     //~1107I~
    for (int ii=0;ii<CTR_TILETYPE;ii++)                            //~1107R~
    {                                                              //~1107I~
    	if (btsOrphan[ii])                                         //~1107I~
        	itsTile[ii]=0;                                         //~1107I~
    }                                                              //~1107I~
}                                                                  //~1107I~
//*******************************************************          //~1106I~
private static int getShanten_Normal(int PmaxMeld)                 //~1106R~
{                                                                  //~1106I~
	int rc=CTR_MAXHAND,shanten;                                    //~1106R~
    //****************                                             //~1106I~
    dropOrphan();                                                  //~1107R~
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
	rc=Math.min(rc,shanten);                                       //~1106I~
	rc=rc-(CTR_MAXMELD-PmaxMeld)*2;                                //~1106R~
    return rc;                                                     //~1106R~
}                                                                  //~1106I~
//*******************************************************          //~1106I~
private static int getShanten_13Orphan()                           //~1106R~
{                                                                  //~1106I~
    int ctrOrphan=0,head=0;                                        //~1106R~
	for (int ii=0;ii<itsOrphans.length;ii++)                       //~1106R~
    {                                                              //~1106I~
    	int ctr=itsTile[itsOrphans[ii]];                           //~1106R~
    	if (ctr!=0)                                                //~1106I~
        {                                                          //~1106I~
        	ctrOrphan++;                                           //~1106R~
            if (ctr>=2)                                            //~1106I~
            	head++;                                            //~1106I~
        }                                                          //~1106I~
    }                                                              //~1106I~
    int rc=13-(ctrOrphan+(head!=0 ? 1 : 0));                       //~1106R~
    return rc;                                                     //~1106I~
}                                                                  //~1106I~
//*******************************************************          //~1106I~
private static int getShanten_7Pair()                              //~1106R~
{                                                                  //~1106I~
    int ctrPair=0,ctrType=0;                                                 //~1106R~//~1107R~
    for (int ii=0;ii<CTR_TILETYPE;ii++)                            //~1106I~
    {                                                              //~1106I~
        int ctr=itsTile[ii];                                       //~1106I~
//  	System.out.println("7pair ii="+ii+",ctr="+ctr);            //~1106I~
    	if (ctr==4)                                                //~1106I~
        {                                                          //~1106I~
        	if (sw7PairKan)                                        //~1106I~
	        	ctrPair+=2;                                        //~1106I~
            else                                                   //~1106I~
	        	ctrPair++;                                         //~1106I~
        }                                                          //~1106I~
        else                                                       //~1106I~
    	if (ctr>=2)                                                //~1106I~
        	ctrPair++;                                             //~1106I~
        if (ctr!=0)                                                //~1107I~
            ctrType++;                                             //~1107I~
    }                                                              //~1106I~
	int rc=6-ctrPair+(7>ctrType ? 7-ctrType : 0);                  //~1107R~
    return rc;                                                     //~1106I~
}                                                                  //~1106I~
//*******************************************                      //~1107I~
private static void countTile()                                    //~1107I~
{                                                                  //~1107I~
	Arrays.fill(itsTile,0);                                        //~1106R~//~1107M~
    for (int ii=0;ii<CTR_MAXHAND;ii++)                             //~1107I~
    {                                                              //~1107I~
    	itsTile[itsHand[ii]]++;                                    //~1107I~
    }                                                              //~1107I~
}                                                                  //~1107I~
//*******************************************                      //~1106M~
private static void getShanten(int PmaxMeld)                       //~1106R~
{                                                                  //~1106M~
    countTile();                                                   //~1107R~
	itsShanten[1]=getShanten_13Orphan();                           //~1106R~
	itsShanten[2]=getShanten_7Pair();                              //~1106R~
	itsShanten[0]=getShanten_Normal(PmaxMeld);                     //~1106R~//~1107M~
    System.out.println(itsShanten[0]+" "+itsShanten[1]+" "+itsShanten[2]);//~1106R~
    if (fwOut!=null)                                               //~1107I~
	    writeLine(itsShanten[0]+" "+itsShanten[1]+" "+itsShanten[2]);//~1107I~
}                                                                  //~1106M~
//***********************************************                  //~1106I~
//test file                                                        //~1106I~
//1st lien:line ctr                                                //~1106I~
//2nd 14 items of tile id                                          //~1106I~
// man:0-8, pin:9-17, sou:18-26, ji:27-30,31-33                    //~1106I~
//***********************************************                  //~1106I~
	private static boolean testLine(String Pline)                     //~1106R~//~1107R~
    {                                                              //~1106I~
    	String line=Pline.trim();                                  //~1106I~
        String[] hand=line.split(" +");                            //~1106R~
        if (hand.length==1)	//linectr line                         //~1106R~
        	return false;                                                //~1106I~//~1107R~
//      System.out.println("line="+Pline);                         //~1106R~//~1107R~
        Arrays.fill(itsHand,0);                                    //~1106R~
        for (int ii=0;ii<hand.length;ii++)                         //~1106R~
		{                                                          //~1106I~
        	try                                                    //~1106I~
            {                                                      //~1106I~
            	itsHand[ii]=Integer.parseInt(hand[ii]);            //~1106R~
            }                                                      //~1106I~
            catch(NumberFormatException e)                         //~1106I~
            {                                                      //~1106I~
                System.out.println("line="+Pline+"\n"+e.toString());//~1106I~
            }                                                      //~1106I~
        }                                                          //~1106I~
        try                                                        //~1107I~
        {                                                          //~1107I~
        	getShanten(hand.length/3 /*max meld*/);                    //~1106R~//~1107R~
        }                                                          //~1107I~
        catch(Exception e)                                         //~1107I~
        {                                                          //~1107I~
            System.out.println("testLine line="+line+",exception="+e.toString());//~1107I~
            e.printStackTrace();                                   //~1107I~
            System.exit(4);                                        //~1107I~
        }                                                          //~1107I~
        return true;                                               //~1107I~
    }                                                              //~1106I~
//***********************************************                  //~1106M~
	private static void testCalcShanten(String Pfnm)               //~1106I~
    {                                                              //~1106M~
        int ctrLine=0;                                             //~1106M~
        try                                                        //~1A4zR~//~1106M~
		{                                                          //~1A4zI~//~1106M~
            FileReader fr = new FileReader(Pfnm);          //~1A4zR~//~1106M~
            BufferedReader br=new BufferedReader(fr);                                 //~1A4zR~//~1106M~
            while(true)                                            //~1106R~
            {                                                      //~1106M~
            	String line=br.readLine();                         //~1106M~
                if (line==null)                                    //~1106M~
                	break;                                         //~1106M~
                if (testLine(line))                                    //~1106I~//~1107R~
	                ctrLine++;                                         //~1106M~//~1107R~
            }                                                      //~1106R~
            System.out.println("ctrLine="+ctrLine);                //~1106R~
            br.close();                                            //~1106M~
        }                                                          //~1A4zI~//~1106M~
        catch(Exception e)                                         //~1A4zM~//~1106M~
        {                                                          //~1A4zM~//~1106M~
        	System.out.println("file:"+Pfnm+";"+e.toString());     //~1106M~
        }                                                          //~1A4zM~//~1106M~
    }                                                              //~1106M~
//***********************************************                  //~1107I~
	private static void openOutput(String Pfnm)                    //~1107I~
    {                                                              //~1107I~
    	fwOut=null;                                                //~1107I~
        try                                                        //~1107I~
		{                                                          //~1107I~
            File f=new File(Pfnm);                                 //~1107I~
            fwOut=new FileWriter(f);                               //~1107R~
        }                                                          //~1107I~
        catch(IOException e)                                       //~1107I~
        {                                                          //~1107I~
        	System.out.println("openOutput file:"+Pfnm+";"+e.toString());//~1107I~
        }                                                          //~1107I~
    }                                                              //~1107I~
//***********************************************                  //~1107I~
	private static void writeLine(String Ptext)                    //~1107I~
    {                                                              //~1107I~
        try                                                        //~1107I~
		{                                                          //~1107I~
            fwOut.write(Ptext+"\n");                                    //~1107I~
        }                                                          //~1107I~
        catch(IOException e)                                       //~1107I~
        {                                                          //~1107I~
        	System.out.println("writeLine text="+Ptext+",exception="+e.toString());//~1107I~
        }                                                          //~1107I~
    }                                                              //~1107I~
//***********************************************                  //~1107I~
	private static void closeOutput()                              //~1107I~
    {                                                              //~1107I~
        if (fwOut!=null)                                           //~1107I~
            try                                                    //~1107I~
            {                                                      //~1107I~
                fwOut.close();                                     //~1107I~
            }                                                      //~1107I~
            catch(IOException e)                                   //~1107I~
            {                                                      //~1107I~
                System.out.println("closeOutput exception="+e.toString());//~1107I~
            }                                                      //~1107I~
    }                                                              //~1107I~
//***********************************************                  //~1106I~
	public static void main(String[] Pargs)                        //~1106I~
    {                                                              //~1106I~
    	String fnmInput=Pargs[0];                                  //~1106R~
    	String fnmTbl=Pargs[1];                                    //~1106R~
    	String opt=Pargs[2];                                       //~1107R~
    	String fnmout=Pargs[3];                                    //~1107I~
        if (opt.charAt(0)=='t')                                    //~1107R~
        	sw7PairKan=true;                                       //~1107I~
        System.out.println("input="+fnmInput+",tbl="+fnmTbl+",out="+fnmout+",opt="+opt);      //~1106R~//~1107R~
		openOutput(fnmout);                                        //~1107I~
		tblShanten=readTable(fnmTbl);                              //~1106R~
        testCalcShanten(fnmInput);                                 //~1106R~
		closeOutput();                                             //~1107I~
    }                                                              //~1106I~
}//class                                                           //~1106R~
