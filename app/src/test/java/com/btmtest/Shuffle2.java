//*CID://+@@@@R~:                             update#=   34;       //~1107I~//~@@@@R~
//*******************************************************          //~1107I~
//*******************************************************          //~1107I~
//*For unit test without BTMJ5 env                                 //~1107R~
//*******************************************************          //~1107I~
package com.btmtest;                                               //~1B19R~
                                                                   //~1B19I~
import com.btmtest.game.TileData;                                  //~1106I~//~1B19R~
import com.btmtest.utils.Utils;                                    //~1B19I~
import com.btmtest.utils.URand;                                    //~@@@@I~
import static com.btmtest.game.GConst.*;                           //~1B19I~
import static com.btmtest.game.Tiles.*;                            //~1B19I~
import static com.btmtest.game.gv.Pieces.*;                        //~1B19I~
import static com.btmtest.game.TileData.*;                         //~1B19I~
                                                                   //~1B19I~
import java.io.*;                                                  //~1B19I~
import java.io.File;                                               //~1106I~
import java.io.FileInputStream;                                    //~1106I~
import java.io.FileReader;                                         //~1106I~
import java.io.BufferedReader;                                     //~1106I~
import java.io.IOException;                                        //~1106I~
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;                                           //~1106I~
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//*******************************************************          //~1106I~
public class Shuffle2                                               //~1106R~//~1B19R~//~@@@@R~
{                                                                  //~1106I~
    private static final int CTR_TILETYPE=34;                      //~1106I~
    private static final int OFFS_WORDTILE=3*9;                    //~1106I~
    private static final int CTR_NUMSUIT=3;                        //~1106I~
    private static final int CTR_NUMBER_TILE=9;                    //~1106R~
    private static final int TT_CTR=4;                             //~1B19I~
                                                                   //~1106I~
                                                                   //~1106I~
	private static FileWriter fwOut;                               //~1107I~
    public  TileData[] shuffledTileData;                           //~vaauI~//~1B19I~
    private TileData[] baseTileData;                               //~vaauI~//~1B19I~
    private int[] typeIndex;                                       //~v@@@I~//~1B19I~
    private boolean swUseRedTile=false;                            //~1B19I~
    private int 	posNextTile=TILECTR_KEEPLEFT+PLAYERS*HANDCTR;              //~v@@@R~//~1B19I~
    TileData[] out;                                                //~1B19I~
    boolean swURand64;                                             //~@@@@I~
    private URand aURand64=URand.initRandom64(0L/*seed:use timestamp*/,PIECE_TYPECTR/*34*/);//~@@@@I~

    //*************************                                        //~v@@@I~//~1B19I~
    public TileData[] getNewAllType()                              //~v@@@R~//~1B19I~
    {                                                              //~v@@@I~//~1B19I~
        TileData[] tds = new TileData[PIECE_TYPECTR];   //34         //~v@@@R~//~1B19I~
        return tds;                                                //~v@@@I~//~1B19I~
    }                                                              //~v@@@I~//~1B19I~
    //********************************************************************                                        //~v@@@I~//~9C01R~//~1B19I~
    //*setup all piece type(3*9+3+4=34)                            //~9C01I~//~1B19I~
    //********************************************************************//~9C01I~//~1B19I~
    public void setupBase()                                        //~v@@@I~//~1B19I~
    {                                                              //~v@@@I~//~1B19I~
        TileData[] tds = getNewAllType();     //array[34]                       //~v@@@R~//~9C01R~//~1B19I~
        baseTileData = tds;                                          //~v@@@I~//~1B19I~
        int[] idx = new int[TT_CTR];                                 //~v@@@I~//~1B19I~
        typeIndex = idx;                                             //~v@@@I~//~1B19I~
        TileData td;                                               //~v@@@I~//~1B19I~
        int ctr = 0;                                                 //~v@@@I~//~1B19I~
        for (int ii = 0; ii < PIECE_NUMBERTYPECTR; ii++)   //3           //~v@@@R~//~1B19I~
        {                                                          //~v@@@I~//~1B19I~
            idx[ii] = ctr;                                           //~v@@@I~//~1B19I~
            for (int jj = 0; jj < PIECE_NUMBERCTR; jj++)     //9         //~v@@@R~//~1B19I~
            {                                                      //~v@@@I~//~1B19I~
                td = new TileData(ii/*man,pin,sou*/,jj, (swUseRedTile && jj == PIECE_NUMBER_DORA - 1)/*dora*/);//~v@@5I~//~1B19I~
                tds[ctr++] = td;                                     //~v@@@I~//~1B19I~
            }                                                      //~v@@@I~//~1B19I~
        }                                                          //~v@@@I~//~1B19I~
        idx[TT_JI] = ctr;                                            //~v@@@I~//~1B19I~
        for (int jj = 0; jj < TT_4ESWN_CTR + TT_3WGR_CTR; jj++)             //~v@@@R~//~1B19I~
        {                                                          //~v@@@I~//~1B19I~
            td = new TileData(TT_JI,jj/*number*/, false/*dora*/);  //~v@@5I~//~1B19I~
            tds[ctr++] = td;                                         //~v@@@I~//~1B19I~
        }                                                          //~v@@@I~//~1B19I~
//      if (Dump.Y) Dump.println("Tiles.setupBase ="+TileData.toString(baseTileData));//~9C01I~//~1B19I~
        writeLine("Tiles.setupBase ="+TileData.toString(baseTileData));//~1B19I~
    }                                                              //~v@@@I~//~1B19I~
    //*************************                                        //~v@@@I~//~1B19I~
    public TileData[] getNewAllTile()                              //~v@@@R~//~1B19I~
    {                                                              //~v@@@I~//~1B19I~
        TileData[] tds = new TileData[PIECE_TILECTR];      //*4         //~v@@@R~//~1B19I~
        return tds;                                                //~v@@@I~//~1B19I~
    }                                                              //~v@@@I~//~1B19I~
    //*************************                                        //~v@@@I~//~1B19I~
    public ArrayList<TileData> deepCopyToArrayList()                //~v@@@I~//~1B19I~
    {                                                              //~v@@@I~//~1B19I~
        TileData[] src = baseTileData.clone();                       //~v@@@I~//~1B19I~
        ArrayList<TileData> al = new ArrayList<TileData>();          //~v@@@I~//~1B19I~
        for (int ii = 0; ii < src.length; ii++)                          //~v@@@I~//~1B19I~
        {                                                          //~v@@@I~//~1B19I~
            al.add(TileData.newInstance(src[ii]));                 //~v@@@I~//~1B19I~
        }                                                          //~v@@@I~//~1B19I~
        return al;                                                 //~v@@@I~//~1B19I~
    }                                                              //~v@@@I~//~1B19I~
//*******************************************                      //~1107I~
private void shuffle()                                    //~1107I~//~1B19R~//~@@@@R~
{                                                                  //~1B19I~
            ArrayList<TileData> al = deepCopyToArrayList();	//copy of baseTileData(34 entry)           //~v@@@R~//~9C01R~//~1B19I~
            out = getNewAllTile();                        //~v@@@R~//~1B19R~
            int outctr = 0;                                          //~v@@@R~//~1B19I~
            int typectr = PIECE_TYPECTR;                             //~v@@@I~//~1B19I~
            for (;;)                                               //~1315I~//~1B19I~
            {                                                      //~v@@@R~//~1B19I~
//              int jj = Utils.getRandom(typectr);   //max 34-1        //~v@@@R~//~v@@5R~//~9C01R~//~1B19I~//~@@@@R~
                int jj= Utils.getRandom(typectr*4)%typectr;   //max 34-1//~@@@@I~
              	if (swURand64)                                     //~@@@@I~
                	jj = aURand64.getRandom(typectr);   //max 34-1 //~@@@@I~
                TileData td = al.get(jj);                            //~v@@@R~//~1B19I~
                td.ctrRemain--;                                    //~v@@@R~//~1B19I~
                if (td.ctrRemain >= 0)                               //~v@@@R~//~v@@5R~//~1B19I~
                {                                                  //~v@@@R~//~v@@5R~//~1B19I~
                    TileData tdnew = TileData.newInstance(td);              //~v@@@I~//~9C01R~//~1B19I~
//                    if ((td.flag & TDF_RED5) != 0)  //on when swUseRedTile                        //~v@@@I~//~v@@5R~//~9C01R~//~1B19R~
//                    {                                              //~v@@@I~//~v@@5R~//~1B19R~
//                        tdnew.setRed5(red5List[td.type][td.ctrRemain]);//~v@@@R~//~v@@5R~//~1B19R~
//                        writeLine("Tiles.shuffle red5 type=" + td.type + ",remain=" + td.ctrRemain + ",flag=" + td.flag);//~v@@5R~//~1B19R~
//                    }                                              //~v@@@I~//~v@@5R~//~1B19R~
                    out[outctr++] = tdnew;                         //~v@@@R~//~v@@5R~//~1B19I~
                	writeLine("Tiles.shuffle swURand64="+swURand64+",rand="+jj+",t="+tdnew.type+",n="+tdnew.number+",remain="+td.ctrRemain);//~v@@@I~//~v@@5R~//~1B19I~//+@@@@R~
                }                                                  //~v@@@R~//~v@@5R~//~1B19I~
                else                                               //~v@@@R~//~v@@5R~//~1B19I~
                {                                                  //~v@@@R~//~v@@5R~//~1B19I~
                    writeLine("Tiles.shuffle remove typectr=" + typectr + ",outctr=" + outctr);//~1B19I~
                    al.remove(jj);                                 //~v@@@R~//~v@@5R~//~1B19I~
                    typectr--;                                     //~v@@@R~//~v@@5R~//~1B19I~
                    if (typectr==0)                                //~1315I~//~1B19I~
                        break;                                     //~1315I~//~1B19I~
                }                                                  //~v@@@R~//~v@@5R~//~1B19I~
            }                                                      //~v@@@R~//~1B19I~
        writeLine("Tiles.out swURand64="+swURand64+","+TileData.toString(out));           //~1B19I~//~@@@@R~
}                                                                  //~1B19I~
private void getAllTile()                                          //~1B19I~
{                                                                  //~1B19I~
	for (int ii=posNextTile;ii<PIECE_TYPECTR*4;ii++)               //~1B19I~
        writeLine("ii="+ii+": t="+out[ii].type+",n="+out[ii].number);//~1B19I~
}                                                                  //~1B19I~
//*******************************************                      //~1106M~
private void testShuffle()                       //~1106R~         //~1B19R~
{                                                                  //~1106M~
    swURand64=false;                                               //~@@@@M~
    setupBase();                                                   //~1B19I~
    shuffle();                                                     //~1B19I~
    getAllTile();                                                  //~@@@@I~
                                                                   //~@@@@I~
    setupBase();                                                   //~@@@@I~
    swURand64=true;                                                //~@@@@I~
    shuffle();                                                     //~@@@@I~
    getAllTile();                                                  //~1B19I~
}                                                                  //~1106M~
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
	public static void main(String[] Pargs)                        //~1106I~//~@@@@R~
    {                                                              //~1106I~
    	String fnmout=Pargs[0];                                    //~1107I~//~1B19R~
        System.out.println("Shuffle out="+fnmout);      //~1106R~//~1107R~//~1B19R~
		openOutput(fnmout);                                        //~1107I~
        (new Shuffle2()).testShuffle();                                 //~1106R~//~1B19R~//~@@@@R~
		closeOutput();                                             //~1107I~
    }                                                              //~1106I~
}//class                                                           //~1106R~
