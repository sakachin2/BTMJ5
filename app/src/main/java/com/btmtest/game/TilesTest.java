//*CID://+vah1R~: update#= 728;                                    //~vah1R~
//**********************************************************************//~v101I~
//2021/11/19 vah1 complete vagv(delete from main/java)             //~vah1I~
//2021/11/15 vagv move TilesTest to debug dir                      //~vagvI~
//2021/07/08 vaau split test deal to TilesTest.java                //~vaauI~
//2021/05/01 va8x (Test)specify robot discard tile                 //~va8xI~
//v@@5 20190126 player means position on the device                //~v@@5I~
//reset tile to new game                                           //~v@@@R~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;                                    //~v@@@I~
                                                                   //~9412I~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GConst.*;                                  //~v@@@I~//~9412M~
import static com.btmtest.game.RA.RAConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.gv.Stock.*;//~9412M~


import java.util.ArrayList;

public class TilesTest                                 //~v@@@R~   //~va8xR~
{                                                                  //~0914I~
//  private Tiles aTiles;                                          //~va8xR~//~vagvR~
    protected Tiles aTiles;	//protexted with androidTest           //~vagvI~
    private TileData[] shuffledTileData;                           //~vaauI~
//  private boolean swTestDeal;                                    //~vaauI~//~vah1R~
//  private boolean swUseRed5;                                     //~vaauI~//~vah1R~
    protected boolean swUseRed5; //protected for ITTilesTest       //~vah1I~
    //*********************************************************************//~v@@@I~
    public TilesTest()                                                 //~v@@@R~//~va8xR~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("TilesTest Constructor");         //~1506R~//~@@@@R~//~v@@@R~//+vah1R~
//      aTiles=AG.aTiles;                                          //~va8xI~//~vagvR~
//  	swUseRed5= RuleSetting.isUseRed5Tile();                    //~vaauI~//~vagvR~
    }                                                              //~0914I~//~v@@@R~
    //*************************                                    //~vagvI~
    public void init(Tiles Ptiles)                                      //~vagvI~
    {                                                              //~vagvI~
        aTiles=Ptiles;                                             //~vagvI~
		swUseRed5= RuleSetting.isUseRed5Tile();                    //~vagvI~
    }                                                              //~vagvI~
    //*************************                                    //~vah1I~
    public boolean shuffleTest()                                   //~vah1I~
    {                                                              //~vah1I~
		UView.showToastLong("shuffleTest is NOP, try ITMainActivity");//~vah1I~
        return false;                                              //~vah1I~
    }                                                              //~vah1I~
    //*************************                                    //~vah1I~
    public void testSetDora(TileData[] Pout)                       //~vah1I~
    {                                                              //~vah1I~
		UView.showToastLong("testSetDora is NOP, try ITMainActivity");//~vah1I~
    }                                                              //~vah1I~
}
