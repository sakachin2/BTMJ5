//*CID://+v@@@R~: update#= 336;                                    //~v@@@I~
//**********************************************************************//~v@@@I~
//StaticVars                                                       //~v@@@R~
//* staric valiable remains after Destry,need to reset at createActivity//~v@@@I~
//*                                                                //~v@@@I~
//*static block is executed only once over onDestroy()             //~v@@@I~
//*And static valiable is not reset over onDestroy()               //~v@@@I~
//**********************************************************************//~v@@@I~
package com.btmtest;                                               //~v@@@I~
                                                                   //~v@@@I~
import com.btmtest.BT.enums.ConnectionStatus;                      //~v@@@I~
import static com.btmtest.BT.enums.ConnectionStatus.*;             //~v@@@I~
                                                                   //~v@@@I~
public class StaticVars                                            //~v@@@R~
{                                                                  //~v@@@I~
    public static AG AG;                                           //~v@@@R~
//*Pieces                                                          //~v@@@I~
    public static int earthPieceW,riverPieceW,riverPieceH,earthPieceH;//~v@@@I~
    public static int starterH,starterW;                           //~v@@@I~
    public static int birdH,birdW;                                 //~v@@@I~
    public static int coinH,coinW;                                 //~v@@@I~
    public static int StockPieceW,StockPieceH;                     //~v@@@I~
    public static int stockPieceW,stockPieceH,stockEarthPieceW,stockEarthPieceH;//~v@@@I~
    public static double oldScaleRiver;                            //~v@@@R~
    public static double oldScale;                                 //~v@@@R~
    public static int oldDiceWidth;                                //~v@@@R~
                                                                   //~v@@@I~
    public static ConnectionStatus connectionStatus;	//BTCDialog//~v@@@I~
//*********************************************                    //~v@@@I~
	public StaticVars(MainActivity Pmain)                          //~v@@@R~
    {                                                              //~v@@@I~
        AG=new AG();                                               //~v@@@R~
        AG.init(Pmain);                                            //~v@@@I~
        resetPieces();                                             //~v@@@I~
                                                                   //~v@@@I~
	    connectionStatus=CS_UNKNOWN;                               //~v@@@I~
    }                                                              //~v@@@I~
//*********************************************                    //~v@@@I~
//*Pieces                                                          //~v@@@I~
//*********************************************                    //~v@@@I~
	public static void resetPieces()                               //~v@@@I~
    {                                                              //~v@@@I~
    	earthPieceW=0; riverPieceW=0; riverPieceH=0; earthPieceH=0;//~v@@@I~
    	starterH=0; starterW=0;                                    //~v@@@I~
    	coinH=0; coinW=0;                                          //~v@@@I~
    	StockPieceW=0;	StockPieceH=0;                             //~v@@@I~
    	stockPieceW=0; stockPieceH=0;	stockEarthPieceW=0;	stockEarthPieceH=0;//~v@@@I~
    	oldScaleRiver=0.0;                                         //~v@@@I~
	    oldScale=0.0;                                              //~v@@@I~
    	oldDiceWidth=0;                                            //~v@@@I~
    }                                                              //~v@@@I~
//*********************************************                    //+v@@@I~
	public static void onDestroy()                                 //+v@@@I~
    {                                                              //+v@@@I~
	    AG=null;                                                   //+v@@@I~
	    connectionStatus=null;	//BTCDialog                        //+v@@@I~
    }                                                              //+v@@@I~
}                                                                  //~v@@@I~
