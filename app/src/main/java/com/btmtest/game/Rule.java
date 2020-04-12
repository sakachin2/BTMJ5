//*CID://+v@@@R~: update#= 261;                                    //~v@@@R~
//**********************************************************************//~v101I~
//rule                                                             //~v@@@R~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.os.Handler;

import com.btmtest.dialog.RuleSetting;
import com.btmtest.utils.Dump;
                                                                   //~v@@@I~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~

public class Rule                            //~v@@@R~
{                                                                  //~0914I~
    private static final int INITIAL_POINT=27000;                  //~v@@@R~
    public boolean swLeftButtons=false;  //game buttons on right edge when landscape//~v@@@R~
    public boolean swUseRedTile;                                   //~v@@@R~
                                                                   //~v@@@I~
    public int initialPointStick=INITIAL_POINT;                    //~v@@@R~
//*************************                                        //~v@@@I~
	public Rule()                                                  //~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("Rule Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        AG.aRule=this;                                             //~v@@@I~
        swUseRedTile= RuleSetting.isUseRed5Tile();                  //+v@@@R~
    }                                                              //~0914I~//~v@@@R~
}//class Rule                                                 //~dataR~//~@@@@R~//~v@@@R~
