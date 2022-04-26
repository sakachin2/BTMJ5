//*CID://+vamsR~: update#= 843;                                    //~vamsR~
//**********************************************************************//~v101I~
//2022/04/20 vams Menu:gameover fail by "During game" when FinalGame canceled//~vamsI~
//2022/04/19 vamr Menu:gameover fail by "During game" when accountDlg canceled//~vamrI~
//2021/11/20 vah2 show total score on complete dialog like as DrawndlgLast/DrawnDlgHW//~vah2I~
//2021/09/16 vae5 (Bug)Property of resumed game did not use sg.rulefile at interrupted.//~vae5I~
//2021/07/29 vabg (Bug)Test option set final game, 1st dealer is not east player//~vabgI~
//2021/06/19 va9i (Bug)err by lacking member ast startGame after matchi mode anded bu disconnecting.//~va9iI~
//2021/03/30 va72 (Bug)when multiron for reach tile,nemaplate win color shadow was lost by showscore from resetReachDone//~va72I~
//2021/03/12 va6g (BUG)suspend/resume reach stick remains if last gane ended ron with anyone reach//~va6gI~
//2021/03/11 va6e add robot name over 3 robot                      //~va6eI~
//2021/02/10 va68 change robot name                                //~va68I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
                                                                   //~v@@@I~
import android.graphics.Point;
import android.graphics.Rect;

import com.btmtest.BT.BTIOThread;
import com.btmtest.BT.BTMulti;
import com.btmtest.MainView;
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.dialog.SuspendDlg;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.utils.Dump;
import com.btmtest.BT.Members;                                     //~v@@@I~
import com.btmtest.utils.Prop;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.BT.BTMulti.*;
import static com.btmtest.BT.Members.*;
import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.StaticVars.AG;
import static com.btmtest.game.HistoryData.*;
import static com.btmtest.game.Status.*;//~v@@@I~
import static com.btmtest.game.UA.UAEndGame.*;

//~v@@@I~
public class Accounts                                              //~v@@@R~
{                                                                  //~0914I~
	private static final int AT_DUMMY=0;                        //~v@@@R~
	private static final int AT_LOCALSERVER=1;                       //~v@@@R~
	private static final int AT_LOCALCLIENT=2;                       //~v@@@I~
	private static final int AT_REMOTESERVER=3;                      //~v@@@I~
	private static final int AT_REMOTECLIENT=4;                      //~v@@@R~
                                                                   //~v@@@I~
//    private static final int AF_SETUPEND       =0x01;            //~v@@@R~
	private static final int AF_WAITINGRESPONSE=0x02;              //~v@@@I~
	private static final int AF_ONCE_RON       =0x04;              //~9501I~
                                                                   //~v@@@I~
	private static final int REACH_DONE    =0x01;	//conin:1000 was shown//~v@@@R~
	private static final int REACH_SHOWN   =0x02;	//conin:1000 was shown//~v@@@I~
//	private static final int COST_REACH=1000;                      //~v@@@I~//~9511R~
                                                                   //~v@@@I~
	private ACAction acaction;                                     //~v@@@R~
	private String[] accountNames;                                 //~v@@@I~
	private String[] accountNamesByPostion;                        //~9416R~
    private int flag;                                                  //~v@@@I~
	private int starter;                                           //~v@@@I~
    public  int starterRelativePos;	//first starter Position(=player)                                //~v@@@R~//~9602R~
	public  int tempStarter;                                       //~v@@@R~
	private int yourPosition;                                      //~v@@@I~
	public  int idxServer,idxLocal;                                //~v@@@R~
	public  int yourESWN;	//starting position                                           //~v@@@I~//~9504R~
//  public  Members.MemberData memberServer,memberLocal;           //~v@@@R~//~0118R~
	private Robot robot;                                           //~v@@@R~
    public int activeMembers;                                          //~v@@@I~
    public int[] posMember;	//eswn-->accounts member,create at ACAction:diceCastedAll_TempStarterDice               //~v@@@M~//~v@@5R~//~9525R~
	public  Account[] accounts;                                    //~v@@@R~//~v@@5R~
	public  Account[] accountsByESWN;  //staring ESWN->idxAccount          //~v@@@R~//~9429R~
	private Account[] currentAccountsByESWN;  //current ESWN->idxAccount   //~v@@@I~//~9606R~
	private int[] accountsLocal;   //player(your are top)->idxAccount//~v@@@R~
    private boolean swPositionMoved=true;                          //~v@@@R~
    private int currentServerEswn;                                 //~v@@@I~
    private int[] currentEswnByAccount;                            //~v@@5I~
    private int[] currentEswnByPosition=new int[PLAYERS];  //by initial pos        //~9416R~//~9A31R~
    private int[] currentPositionByEswn=new int[PLAYERS];   //current Eswn to initial Eswn//~9606R~
    private int[] ctrContinuedGain=new int[PLAYERS];	//pos seq               //~9519I~//~9A31R~
    public  int[] intsCtrPendingReach=new int[PLAYERS]; //position seq//~9820R~
    public  int[] score=new int[PLAYERS];	//account position seq                          //~9312R~//~9318R~//~9511R~
    public  String[] finalPoint1000=new String[PLAYERS];           //~9615R~
    public  int topPrize;                                          //~9428I~
    public  int endgameTypeNextGame,nextgameTypeNextGame;            //~9823I~//~9830R~
    private int cutPlayer=-1,cutEswn=-1;                           //~9530R~
//  public int currentESWN;                                        //~v@@@R~
//*************************                                        //~v@@@I~
	public Accounts()                                              //~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("Accounts Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        AG.aAccounts=this;
        //      init();                                                    //~v@@@R~
    }                                                              //~0914I~//~v@@@R~
    //*****************************************************        //~9A19I~
    public static int getTypeStringID(int Ptype)   //~9A19R~       //~9A20R~
    {       	                                                   //~9A19I~
    	int id;                                                    //~9A19I~
        switch (Ptype)                                             //~9A19I~
        {                                                          //~9A19I~
        case AT_DUMMY:                                             //~9A19I~
        	id=R.string.DeviceType_Dummy;                          //~9A19I~
            break;                                                 //~9A19I~
        case AT_LOCALSERVER:                                       //~9A19I~
        	id=R.string.DeviceType_LocalServer;                    //~9A19I~
            break;                                                 //~9A19I~
        case AT_LOCALCLIENT:                                       //~9A19I~
        	id=R.string.DeviceType_LocalClient;                    //~9A19I~
            break;                                                 //~9A19I~
        case AT_REMOTESERVER:                                      //~9A19I~
        	id=R.string.DeviceType_RemoteServer;                   //~9A19I~
            break;                                                 //~9A19I~
        case AT_REMOTECLIENT:                                      //~9A19I~
//      	id=PmemberRole==ROLE_SERVER ? R.string.DeviceType_ClientOfServer : R.string.DeviceType_RemoteClient;//~9A19R~//~9A20R~
        	id=R.string.DeviceType_RemoteClient;                   //~9A20I~
            break;                                                 //~9A19I~
        default:                                                   //~9A19I~
        	id=R.string.DeviceUnknown;                             //~9A19I~
        }                                                          //~9A19I~
        if (Dump.Y) Dump.println("Account.getTypeStringID type="+Ptype+",stringid="+Integer.toHexString(id)+",string="+Utils.getStr(id));//~9A19I~
        return id;                                                 //~9A19I~
    }                                                              //~9A19I~
    //*****************************************************    //~v@@@I~//~9824I~
//  public static int isRobotName(String Pname)                //~9824I~//~9828R~//~va68R~
    public static int isRobotName_Old(String Pname)                //~va68I~
    {                                                              //~9824I~
        int rc=0;                                                  //~9828R~
        String botname=Utils.getStr(R.string.YournameRobot);       //~9824I~
        if (Pname.length()==botname.length()+1                         //~9824I~
        &&  Pname.startsWith(botname))                             //~9824I~
        {                                                          //~9824I~
            int suffix=Utils.parseInt(Pname.substring(botname.length()),-1);//~9824I~
//          if (suffix==1 || suffix==2)                            //~9824I~//~0304R~
            if (suffix>=1 && suffix<=3)                            //~0304I~
                rc=suffix;                                         //~9824I~//~9828R~
        }                                                          //~9824I~
        if (Dump.Y) Dump.println("Account.isRobotNameOld rc="+rc+",Pname="+Pname);//~9824I~//~va68R~
        return rc;                                                 //~9824I~
    }                                                              //~9824I~
    //*****************************************************        //~va68I~
    public static int isRobotName(String Pname)                    //~va68I~
    {                                                              //~va68I~
        int rc=0;                                                  //~va68I~
//      for (int ii=1;ii<PLAYERS;ii++)                                 //~va68I~//~va6eR~
        for (int ii=1;ii<GConst.robotYourNameDefaultConst.length;ii++)//~va6eI~
        {                                                          //~va68I~
//      	if (Pname.equals(GConst.robotYourNameDefault[ii]))     //~va68I~//~va6eR~
        	if (Pname.equals(GConst.robotYourNameDefaultConst[ii]))//~va6eI~
            {	                                                   //~va68I~
            	rc=ii;                                             //~va68I~
                break;                                             //~va68I~
            }                                                      //~va68I~
        }                                                          //~va68I~
        if (rc==0)                                                 //~va68I~
        	rc=isRobotName_Old(Pname);                             //~va68I~
        if (Dump.Y) Dump.println("Accounts.isRobotName rc="+rc+",Pname="+Pname);//~va68I~//~vabgR~
        return rc;                                                 //~va68I~
    }                                                              //~va68I~
//    //*****************************************************      //~0204R~
//    public static static getRobotName(boolean PswDefault)        //~0204R~
//    {                                                            //~0204R~
//        String botname;                                          //~0204R~
//        if (PswDefault)                                          //~0204R~
//            botname=Utils.getStr(R.string.YournameRobot);        //~0204R~
//        else                                                     //~0204R~
//        {                                                        //~0204R~
//            if (AG.isLangJP)                                     //~0204R~
//                botname=Utils.getStr(R.string.YournameRobotE);   //~0204R~
//            else                                                 //~0204R~
//                botname=Utils.getStr(R.string.YournameRobotJ);   //~0204R~
//        }                                                        //~0204R~
//        if (Dump.Y) Dump.println("Account.getRobotName rc="+botname+",PswDefault="+PswDefault+",isLangJP="+AG.isLangJP);//~0204R~
//        return botname;                                          //~0204R~
//    }                                                            //~0204R~
//    //*****************************************************      //~0204R~
//    public static int isRobotName(String Pname,boolean PswDefaultLang)//~0204R~
//    {                                                            //~0204R~
//        int rc=0;                                                //~0204R~
//        String botname=getRobotName(PswDefaultLang);             //~0204R~
//        if (Pname.length()==botname.length()+1                   //~0204R~
//        &&  Pname.startsWith(botname))                           //~0204R~
//        {                                                        //~0204R~
//            int suffix=Utils.parseInt(Pname.substring(botname.length()),-1);//~0204R~
//            if (suffix==1 || suffix==2)                          //~0204R~
//                rc=suffix;                                       //~0204R~
//        }                                                        //~0204R~
//        if (Dump.Y) Dump.println("Account.isRobotName rc="+rc+",Pname="+Pname+",swDefaultLang="+PswDefaultLang);//~0204R~
//        return rc;                                               //~0204R~
//    }                                                            //~0204R~
//    //*****************************************************      //~0204R~
//    public static int isRobotName2(String Pname)                 //~0204R~
//    {                                                            //~0204R~
//        int rc;                                                  //~0204R~
//        String botname;                                          //~0204R~
//        rc=isRobotName(Pname,true/*defaultLang*/);               //~0204R~
//        if (rc==0)                                               //~0204R~
//            rc=isRobotName(Pname,false/*defaultLang*/);          //~0204R~
//        if (Dump.Y) Dump.println("Account.isRobotName2 rc="+rc+",Pname="+Pname);//~0204R~
//        return rc;                                               //~0204R~
//    }                                                            //~0204R~
    //**************************************************           //~v@@@M~
    public static boolean isServer()                               //~v@@@M~
    {                                                              //~v@@@M~
//  	boolean rc=AG.aAccounts.idxLocal==AG.aAccounts.idxServer;  //~v@@@M~//~0113R~
    	boolean rc=AG.aAccounts!=null && AG.aAccounts.idxLocal==AG.aAccounts.idxServer;//~0113I~
      	if (AG.swTrainingMode)                                     //~va66I~
        	rc=true;                                               //~va66I~
        if (Dump.Y) Dump.println("Accounts.isServer swTrainingMode="+AG.swTrainingMode+",rc="+rc);         //~v@@@M~//~va66R~
        return rc;                                                 //~v@@@M~
    }                                                              //~v@@@M~
    //**************************************************           //~v@@@I~
    public static boolean isDummy(int Pplayer/*idxAccount*/)                     //~v@@@I~//~9306R~
    {                                                              //~v@@@I~
    	Account a=AG.aAccounts.accounts[Pplayer];                  //~9905R~
    	boolean rc=a.type==AT_DUMMY;                               //~9905I~
        if (Dump.Y) Dump.println("Accounts.isDummy="+rc+",Pplayer="+Pplayer+",accountName="+a.name+",ESWN="+a.ESWN);          //~v@@@I~//~9630R~//~9905R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*mapping on account[]                                        //~v@@@R~
    //!! Use only before positioning moved                                           //~9306I~//~va60R~
    //select next nonDymmy account                                 //~9B25R~
    //**************************************************           //~v@@@I~
    public static int mapDummy(int Pplayer/*idxAccount*/)                        //~v@@@I~//~9306R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.mapDummy accountsLocal="+Arrays.toString(AG.aAccounts.accountsLocal)); //player->idxAccounts//~9905I~
//      int player=Pplayer%AG.aAccounts.activeMembers;             //~v@@@R~
        int player=Pplayer;                                        //~v@@@I~
        if (isDummy(Pplayer))                                      //~v@@@I~
        {                                                          //~v@@@I~
            player=Players.nextPlayer(Pplayer);                    //~v@@@I~
            if (isDummy(player))                                   //~v@@@I~
            {                                                      //~va66I~
//              player=Players.prevPlayer(Pplayer);                //~v@@@R~//~9B25R~
                player=Players.nextPlayer(player);                 //~9B25I~
            	if (isDummy(player))    //for the case training mode(3 robots)//~va66I~
	                player=Players.nextPlayer(player);             //~va66I~
            }                                                      //~va66I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.mapDummy "+Pplayer+"-->"+player);//~v@@@I~
        return player;                                             //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************************         //~va60R~
//    public static int mapDummyByEswn(int Peswn)                  //~va60R~
//    {                                                            //~va60R~
//        if (Dump.Y) Dump.println("Accounts.mapDummyByEswn Peswn="+Peswn);//~va60R~
//        int player=eswnToPlayer(Peswn);                          //~va60R~
//        int robot=mapDummy(player);                              //~va60R~
//        int eswn=playerToEswn(robot);                            //~va60R~
//        if (Dump.Y) Dump.println("Accounts.mapDummyByEswn eswn="+eswn);//~va60R~
//        return eswn;                                             //~va60R~
//    }                                                            //~va60R~
    //**************************************************           //~v@@5I~//~9306M~
    public boolean isDummyByCurrentEswn(int Peswn)                 //~v@@5I~//~9306M~
    {                                                              //~v@@5I~//~9306M~
        if (Dump.Y) Dump.println("Accounts.isDummyByCurrentEswn Peswn="+Peswn);//~va9iI~
		Account a=currentAccountsByESWN[Peswn];                    //~v@@5I~//~9306M~
        if (Dump.Y) Dump.println("Accounts.isDummyByCurrentEswn Peswn="+Peswn+",idx="+a.idxMembers+",isDummy="+a.isDummy());//~v@@5I~//~9306M~//~9526R~
		return a.isDummy();                                          //~v@@5I~//~9306M~
    }                                                              //~v@@5I~//~9306M~
//    //**************************************************           //~v@@@I~//~9306M~//~9905R~
//    //*map dummy after positioning                                 //~v@@@I~//~9306M~//~9905R~
//    //**************************************************           //~v@@@I~//~9306M~//~9905R~
//    public int mapDummyPlayer(int Pplayer)                         //~v@@@I~//~9306M~//~9905R~
//    {                                                              //~v@@@I~//~9306M~//~9905R~
//        int idxAccount=playerToMember(Pplayer);                    //~v@@@I~//~9306M~//~9905R~
//        int idxReal=mapDummy(idxAccount);                          //~v@@@I~//~9306M~//~9905R~
//        int eswn=idxToCurrentEswn(idxReal);                        //~v@@@I~//~9306M~//~9905R~
//        int player=eswnToPlayer(eswn);                             //~v@@@I~//~9306M~//~9905R~
//        if (Dump.Y) Dump.println("Accounts.mapDummyPlayer player="+Pplayer+",idxReal="+idxReal+",eswn="+eswn+",player="+player);//~v@@@I~//~9306M~//~9905R~
//        return player;                                             //~v@@@I~//~9306M~//~9905R~
//    }                                                              //~v@@@I~//~9306M~//~9905R~
    //**************************************************           //~9306I~
    //*map dummy after positioning                                 //~9306I~
    //*map to next eswn on current eswn (not previous)                           //~9306I~//~9905R~
    //**************************************************           //~9306I~
    public int mapDummyPlayerByCurrentEswn(int Pplayer)            //~9306R~
    {                                                              //~9306I~
    	int idxAccount,player=Pplayer;                             //~9306I~
        for (int ii=0;ii<PLAYERS-1;ii++)                           //~9306I~
        {                                                          //~9306I~
    		idxAccount=playerToMember(player);                     //~9306I~
    		if (!isDummy(idxAccount))                              //~9306I~
            	break;                                             //~9306I~
	    	player=Players.nextPlayer(player);                     //~9306I~
        }                                                          //~9306I~
        if (Dump.Y) Dump.println("Accounts.mapDummyPlayerByCurrentEswn player="+Pplayer+",out="+player);//~9306I~//~9905R~
		return player;                                             //~9306I~
    }                                                              //~9306I~
    //**************************************************           //~9306I~
    //*if dealer is robot,next humean in counterclockwise          //~vae5I~
    //**************************************************           //~vae5I~
    public int getCurrentDealerReal()                              //~9306I~
    {                                                              //~9306I~
        int playerE=eswnToPlayer(0/*East*/);   //East:Dealer       //~9306I~
        int realPlayer=AG.aAccounts.mapDummyPlayerByCurrentEswn(playerE);//~9306I~
        if (Dump.Y) Dump.println("Accounts.getCurrentDealerReal player="+realPlayer);//~9306I~
		return realPlayer;                                         //~9306I~
    }                                                              //~9306I~
    //**************************************************           //~vamsI~
    public String getCurrentDealerRealName()                       //~vamsR~
    {                                                              //~vamsI~
	    int player=getCurrentDealerReal();                         //+vamsI~
		int idxAccount=playerToMember(player);                     //+vamsI~
        String rc=accounts[idxAccount].name;                       //+vamsI~
        if (Dump.Y) Dump.println("Accounts.getCurrentDealerRealName rc="+rc+",playerReal="+player+",idxAccounts="+idxAccount);//+vamsR~
		return rc;                                                 //~vamsI~
    }                                                              //~vamsI~
    //**************************************************           //~9610I~
    public int getRealPlayer(int Pplayer)                          //~9610I~
    {                                                              //~9610I~
        int realPlayer=AG.aAccounts.mapDummyPlayerByCurrentEswn(Pplayer);//~9610I~
        if (Dump.Y) Dump.println("Accounts.getRealPlayer Pplayer="+Pplayer+",realPlayer="+realPlayer);//~9610I~
		return realPlayer;                                         //~9610I~
    }                                                              //~9610I~
    //**************************************************           //~9605I~
    //*first dealer Real Eswn Position                             //~9606I~
    //**************************************************           //~9606I~
    public int getFirstDealerPosReal()                             //~9605I~//~9606R~
    {                                                              //~9605I~
    	int idxAccount;                                            //~9605I~
        int pos=0;                                                 //~9605I~
        for (;pos<PLAYERS;pos++)     //search 1st non robot position//~9605I~
        {                                                          //~9605I~
		    idxAccount=accountsByESWN[pos].idxMembers;            //~9605I~
        	if (Dump.Y) Dump.println("Accounts.isFirstDealerPosReal po="+pos+",idx="+idxAccount);//~9605I~//~9606R~
    		if (!isDummy(idxAccount))                              //~9605I~
            	break;                                             //~9605I~
        }                                                          //~9605I~
        if (Dump.Y) Dump.println("Accounts.isFirstDealerPosReal rc="+pos);//~9606R~
		return pos;                                                //~9605I~//~9606R~
    }                                                              //~9605I~
    //**************************************************           //~9606I~
    //*first dealer Real Eswn Position                             //~9606I~
    //**************************************************           //~9606I~
    public int getFirstDealerPlayerReal()                          //~9606I~
    {                                                              //~9606I~
        int player=Players.playerRelative(ESWN_E,yourESWN);        //~9606I~
        int realPlayer=AG.aAccounts.mapDummyPlayerByCurrentEswn(player);//~9606I~
        if (Dump.Y) Dump.println("Accounts.getFirstDealerPlayerReal rc="+realPlayer);//~9606I~
		return realPlayer;                                         //~9606I~
    }                                                              //~9606I~
    //**************************************************           //~9606I~
    public boolean isFirstDealerReal()                             //~9606I~
    {                                                              //~9606I~
        int pos=getFirstDealerPosReal();                           //~9606I~
        boolean rc=pos==yourESWN;                                  //~9606I~
        if (Dump.Y) Dump.println("Accounts.isFirstDealerReal rc="+rc+",yourESWN="+yourESWN);//~9606I~
		return rc;                                                 //~9606I~
    }                                                              //~9606I~
    //**************************************************           //~vamrI~
    public String getFirstDealerRealName()                         //~vamrI~
    {                                                              //~vamrI~
        int pos=getFirstDealerPosReal();                           //~vamrI~
    	String rc=accountsByESWN[pos].name;                        //~vamrI~
        if (Dump.Y) Dump.println("Accounts.getFirstDealerRealName rc="+rc+",pos="+pos);//~vamrI~
		return rc;                                                 //~vamrI~
    }                                                              //~vamrI~
    //**************************************************           //~9315I~
    public int getCurrentDealerRealEswn()                          //~9315I~
    {                                                              //~9315I~
    	int eswn=playerToEswn(getCurrentDealerReal());	//current dealer eswn//~9315I~
        if (Dump.Y) Dump.println("Accounts.getCurrentDealerRealEswn eswn="+eswn);//~9315I~
		return eswn;                                               //~9315I~
    }                                                              //~9315I~
//    //**************************************************         //~v@@@R~
//    //*by idxAccount,out relative pos of you                     //~v@@@R~
//    //**************************************************         //~v@@@R~
//    public int mapDummyIdxAccount(int Pplayer/*idxAccount*/)     //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Accounts.mapDummyIdxAccount Pplayer="+Pplayer);//~v@@@R~
//        int player=mapDummy(Pplayer);                            //~v@@@R~
//        if (player!=Pplayer)                                     //~v@@@R~
//        {                                                        //~v@@@R~
//            int eswn=accounts[player].ESWN;                      //~v@@@R~
//            player=Players.playerRelative(eswn,yourESWN);        //~v@@@R~
//            if (Dump.Y) Dump.println("Accounts.mapDummyIdxAccount eswn="+eswn+",yourESWN="+yourESWN+",out player="+player);//~v@@@R~
//        }                                                        //~v@@@R~
//        return player;                                           //~v@@@R~
//    }                                                            //~v@@@R~
//    //**************************************************         //~v@@@R~
//    public int mapDummyRelativePos(int Pplayer/*relativePosOfYou*/)//~v@@@R~
//    {                                                            //~v@@@R~
//        int pos=accountsLocal[Pplayer];  //index of ESWN         //~v@@@R~
//        int idxAccount=accountsByESWN[pos].idxMembers;      //indexAccounts//~v@@@R~
//        int idxRealAccount=mapDummy(idxAccount);                 //~v@@@R~
//        int eswn=accounts[idxRealAccount].ESWN;                  //~v@@@R~
//        int player=Players.playerRelative(eswn,yourESWN);        //~v@@@R~
//        if (Dump.Y) Dump.println("Accounts.mapDummyRelativePos Pplayer="+Pplayer+",pos="+pos+",idxAccount="+idxAccount+",idxReal="+idxRealAccount+",eswn="+eswn+",player="+player);//~v@@@R~
//        return player;                                           //~v@@@R~
//    }                                                            //~v@@@R~
    //**************************************************           //~v@@@I~
    private void enableDiceRelative(int PposRelative/*relativePosFromYou*/)//~v@@@I~//~9502R~
    {                                                              //~v@@@I~
//      int idxAccount=accountsLocal[PposRelative];	//idx of accounts//~v@@@I~//~9905R~
//      int idxReal=mapDummy(idxAccount);                              //~v@@@I~//~9905R~
        int realPlayer=mapDummyPlayerByCurrentEswn(PposRelative);  //~9905R~
//      boolean swDummy=idxReal!=idxAccount;                       //~v@@@I~//~9905R~
        boolean swDummy=realPlayer!=PposRelative;                  //~9905I~
//      boolean swShadow=idxReal!=idxLocal;                        //~v@@@I~//~9905R~
        boolean swShadow=realPlayer!=PLAYER_YOU;                   //~9905I~
	    if (Dump.Y) Dump.println("Accounts.enableDiceRelative PposRelative="+PposRelative+",realPlayer="+realPlayer+",swDummy="+swDummy+",swShadow="+swShadow);//~v@@@R~//~9905R~
        acaction.showWaitingDiceLight(PposRelative,swShadow,swDummy);//~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*from MainActivity at startGame                              //~9828I~
    //**************************************************           //~9828I~
	public static boolean createInstance()                         //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.createInstance");       //~v@@@R~
    	BTMulti btm=AG.aBTMulti;                                   //~v@@@I~
        if (btm==null)	                                           //~v@@@I~
        	return false;                                          //~v@@@I~
    	Members members=btm.BTGroup;                                       //~v@@@R~
        if (members==null)                                         //~v@@@I~
        	return false;                                          //~v@@@I~
        Accounts ac=new Accounts();                                  //~v@@@I~
        if (!ac.init(members))                                     //~v@@@I~
        	return false;                                          //~v@@@I~
//      AG.aAccounts=ac;                                           //~v@@@R~
    	ac.acaction=new ACAction(ac);                              //~v@@@R~
//        new UserAction(); //override new UserAction() for test called at GC//~v@@@R~
//        AG.aUserAction.init();                                   //~v@@@R~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@M~
    private boolean init(Members Pmembers)                         //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("Accounts.init");                 //~v@@@I~
    	boolean rc=true;                                           //~v@@@I~
        accounts=new Account[PLAYERS];                             //~v@@@M~
        int ctrMember=Pmembers.MD.length;                      //~v@@@I~
//        idxServer=-1;                                              //~v@@@R~//~0118R~
//        idxLocal=-1;                                               //~v@@@I~//~0118R~
//        for (int ii=0;ii<ctrMember;ii++)                           //~v@@@R~//~0118R~
//        {                                                          //~v@@@I~//~0118R~
//            Pmembers.MD[ii].resetSetupEnd();                       //~v@@@R~//~0118R~
//            if ((Pmembers.MD[ii].status & Members.MS_SERVER)!=0)           //~v@@@R~//~0118R~
//            {                                                      //~v@@@I~//~0118R~
//                idxServer=ii;                                      //~v@@@R~//~0118R~
//                memberServer=Pmembers.MD[ii];                      //~v@@@I~//~0118R~
//            }                                                      //~v@@@I~//~0118R~
//            if ((Pmembers.MD[ii].status & Members.MS_LOCAL)!=0)            //~v@@@R~//~0118R~
//            {                                                      //~v@@@I~//~0118R~
//                idxLocal=ii;                                       //~v@@@I~//~0118R~
//                memberLocal=Pmembers.MD[ii];                       //~v@@@I~//~0118R~
//            }                                                      //~v@@@I~//~0118R~
//        }                                                          //~v@@@I~//~0118R~
        idxServer=Pmembers.idxServer;                              //~0118I~
        idxLocal=Pmembers.idxLocal;                                //~0118I~
      if (!AG.swTrainingMode)                                      //~va66I~
      {                                                            //~va66I~
        if (idxServer==-1 || idxLocal==-1)                         //~v@@@R~
        	return false;                                          //~v@@@I~
      }                                                            //~va66I~
//      if (Dump.Y) Dump.println("Accounts.init server="+memberServer.getYourName()+",dev="+memberServer.getName());//~v@@@I~//~0118R~
        boolean swServer=isServer(); //idxLocal==idxServer;        //~0118I~
        if (Dump.Y) Dump.println("Accounts.init idxServer="+idxServer+",idxLocal="+idxLocal+",isServer="+swServer);//~0118I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@M~
        {                                                          //~v@@@I~
        	accounts[ii]=new Account(ii);                          //~v@@@I~
        	if (Dump.Y) Dump.println("Accounts.init Pmembers.MD["+ii+"]="+Utils.toString(Pmembers.MD[ii]));//~9924I~//~9B11R~
//            int status=Pmembers.MD[ii].status;                   //~0118R~
//            Thread t=Pmembers.MD[ii].getThread();                //~0118R~
//            boolean swValid;                                     //~0118R~
//            if (swServer)                                        //~0118R~
//                swValid=(status & MS_SERVER)!=0 || (t!=null);    //~0118R~
//            else                                                 //~0118R~
//                swValid=(status & MS_REMOTECLIENT)!=0 || (t!=null);//~0118R~
            if (!accounts[ii].setMember((ii<ctrMember ? Pmembers.MD[ii] : null)))//~v@@@R~//~0118R~
//          if (!accounts[ii].setMember(swValid ? Pmembers.MD[ii] : null))//~0118R~
                rc=false;                                          //~v@@@I~//~0118R~
            if (ii<ctrMember)                                      //~v@@@I~//~0118R~
//              Pmembers.MD[ii].account=accounts[ii];                //~v@@@I~//~9B11R~//~0118R~
                Pmembers.MD[ii].setAccount(accounts[ii]);         //~9B11I~//~0118R~
        }                                                          //~v@@@I~
      if (!AG.swTrainingMode)                                      //~va9iR~
        if (chkDupYourname())                                      //~0217I~
        	return false;	                                       //~0217I~
        activeMembers=getMemberCtr();                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.init rc="+rc+",memberctr="+activeMembers);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@M~
//******************************************************************************//~va60I~
//*for instrumented Test                                           //~va60I~
//******************************************************************************//~va60I~
	public Account newAccount(int Pidx)                            //~va60R~
    {                                                              //~va60I~
    	return new Account(Pidx);                                  //~va60R~
    }                                                              //~va60I~
//******************************************************************************//~0217I~
	private boolean chkDupYourname()                               //~0217I~
    {                                                              //~0217I~
    	boolean rc=false;                                          //~0217I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~0217I~
        {                                                          //~0217I~
        	String nm=accounts[ii].name;                           //~0217I~
        	if (Dump.Y) Dump.println("Accounts.chkDupYournameii="+ii+",nm="+nm);//~0217I~
            for (int jj=ii+1;jj<PLAYERS;jj++)                      //~0217I~
            {                                                      //~0217I~
                if (nm.equals(accounts[jj].name))                   //~0217I~
                {                                                  //~0217I~
					UView.showToastLong(Utils.getStr(R.string.Err_DupYourname,nm));//~0217I~//~0322R~
                	rc=true;                                       //~0217I~
                    break;                                         //~0217I~
                }                                                  //~0217I~
            }                                                      //~0217I~
            if (true)                                              //~va9iI~
                break;                                             //~va9iI~
        }                                                          //~0217I~
        return rc;                                                 //~0217I~
    }                                                              //~0217I~
//******************************************************************************//~v@@@I~
//*ESWN-->players                                                  //~v@@@I~
//******************************************************************************//~v@@@I~
	public void setPosition(int [] Ppos)                           //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.setPosition Ppos="+Arrays.toString(Ppos));          //~v@@@I~//~0305R~
        starter=Ppos[0];                                           //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	int player=Ppos[ii];                                   //~v@@@I~
//          if (player==0)                                         //~v@@@R~
//          	yourPosition=ii;                                   //~v@@@R~
        	accounts[player].ESWN=ii;                              //~v@@@I~
	        if (Dump.Y) Dump.println("Accounts.setPosition player="+player+",name="+accounts[player].name+",pos="+ii);//~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*sort by ESWN                                                //~v@@@I~
    //**************************************************           //~v@@@I~
    private Account[] sortAccounts()                              //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.sortAccount posMember="+Arrays.toString(posMember));           //~v@@@I~//~9901R~
		Account[] byESWN=new Account[PLAYERS];                     //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	byESWN[ii]=accounts[posMember[ii]];                    //~v@@@I~
            if (posMember[ii]==idxLocal)                           //~v@@@I~
                yourESWN=ii;                                       //~v@@@I~
	        if (Dump.Y) Dump.println("Accounts.sortAccounts ii(ESWN)="+ii+",accountIdx="+posMember[ii]+",name="+byESWN[ii].name);//~v@@@I~//~9901R~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("Accounts.sortAccounts yourESWN="+yourESWN);//~9901R~
        return byESWN;                                             //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~9901I~
    public int searchAccountByDeviceName(String Pname)                  //~9901I~//~9A18R~//~9A20R~
    {                                                              //~9901I~
        if (Dump.Y) Dump.println("Accounts.searchAccountByDeviceName name="+Pname);//~9901R~//~9A20R~
        int rc=-1;                                                 //~9901I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9901I~
        {                                                          //~9901I~
	        if (Dump.Y) Dump.println("Accounts.searchAccountByDeviceName ii="+ii+",account="+accounts[ii].toString());//~9901I~//~9A20R~
        	if (Pname.compareTo(accounts[ii].memberData.getName())==0)                 //~9901I~//~9A20R~
            {                                                      //~9901I~
            	rc=ii;                                             //~9901I~
                break;                                             //~9901I~
            }                                                      //~9901I~
        }                                                          //~9901I~
	    if (Dump.Y) Dump.println("Accounts.searchAccountByDeviceName name="+Pname+",rc="+rc);//~9901I~//~9A20R~
        return rc;                                                 //~9901I~
    }                                                              //~9901I~
    //**************************************************           //~9A20I~
    public int searchAccountByName(String Pname)                   //~9A20I~
    {                                                              //~9A20I~
        if (Dump.Y) Dump.println("Accounts.searchAccountByName name="+Pname);//~9A20I~
        int rc=-1;                                                 //~9A20I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9A20I~
        {                                                          //~9A20I~
	        if (Dump.Y) Dump.println("Accounts.searchAccountByName ii="+ii+",account="+accounts[ii].toString());//~9A20I~
        	if (Pname.compareTo(accounts[ii].name)==0)             //~9A20I~
            {                                                      //~9A20I~
            	rc=ii;                                             //~9A20I~
                break;                                             //~9A20I~
            }                                                      //~9A20I~
        }                                                          //~9A20I~
	    if (Dump.Y) Dump.println("Accounts.searchAccountByName name="+Pname+",rc="+rc);//~9A20I~
        return rc;                                                 //~9A20I~
    }                                                              //~9A20I~
    //**************************************************           //~9901I~
    private void setPosMemberResume(HistoryData Phd)               //~9901I~
    {                                                              //~9901I~
//        posMember=new int[PLAYERS];                                //~9901I~//~0305R~
//        String[] names=Phd.HD[HDPOS_MEMBER];                      //~9901I~//~0305R~
//        if (Dump.Y) Dump.println("Accounts.setPosMemberResume member="+Arrays.toString(names));//~9901I~//~0305R~
//        for (int ii=0;ii<PLAYERS;ii++)                                 //~9901I~//~0305R~
//        {                                                          //~9901I~//~0305R~
//            int idx=searchAccountByName(names[ii]);                //~9901I~//~9A20R~//~0305R~
//            posMember[ii]=idx;                                     //~9901I~//~0305R~
//        }                                                          //~9901I~//~0305R~
//        if (Dump.Y) Dump.println("Accounts.setPosMemberResume posMember="+Arrays.toString(posMember));//~9901I~//~0305I~
        if (Dump.Y) Dump.println("Accounts.setPosMemberResume hd="+Phd.toString());//~0305I~
        posMember=Phd.idxMembers;                                    //~0305I~
        boolean swRobotNameChnged=false;                            //~0305I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~0305I~
        {                                                          //~0305I~
        	Account a=AG.aBTMulti.BTGroup.MD[posMember[ii]].account;//~0305I~
            if (Accounts.isRobotName(a.name)>0)                      //~0305I~
            {                                                      //~0305I~
            	if (!a.name.equals(Phd.getName(ii)))                //~0305I~
                {                                                  //~0305I~
			        if (Dump.Y) Dump.println("Accounts.setPosMemberResume robotname cahnged old="+a.name+",new="+Phd.getName(ii));//~0305I~
			        swRobotNameChnged=true;                        //~0305I~
	            	a.name=Phd.getName(ii);                        //~0305R~
                }                                                  //~0305I~
            }                                                      //~0305I~
	        if (Dump.Y) Dump.println("Accounts.setPosMemberResume after robotname update account="+a.toString());//~0305I~
        }                                                          //~0305I~
		if (swRobotNameChnged)                                     //~0305I~
        {                                                          //~0305I~
        	accountNames=null;	//return newvale for GC.getAccountNames()//~0305R~
			getAccountNames();	//update accountNames;             //~0305I~
        }                                                          //~0305I~
    }                                                              //~9901I~
    //**************************************************           //~0222I~
    //*from Status.setGameSeq                                      //~0222I~
    //**************************************************           //~0222I~
    public void setCurrentAccountsByESWN()                         //~0222I~
    {                                                              //~0222I~
		currentAccountsByESWN=setCurrentAccountsByESWN(AG.aStatus.gameCtrGame);//~0222I~
    }                                                              //~0222I~
    //**************************************************           //~v@@@I~
    private Account[] setCurrentAccountsByESWN(int PctrGame)       //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("setCurrentAccountsByESWN  ctrGame="+PctrGame);//~v@@@I~
        if (Dump.Y) Dump.println("setCurrentAccountsByESWN  accountsByESWN="+Arrays.toString(accountsByESWN));//~0309I~
        Account[] acs;                                             //~v@@@I~
        if (PctrGame==0)                                           //~v@@@I~
        {                                                          //~9826I~
        	acs=accountsByESWN;                                    //~v@@@I~
	        for (int ii=0;ii<PLAYERS;ii++)                         //~9826I~
    	    {                                                      //~9826I~
                int type=acs[ii].type;                             //~9826I~
				if (Dump.Y) Dump.println("setCurrentAccountsByESWN accountsByESWN ii="+ii+",name="+acs[ii].name+",type="+type);//~9830I~
                if (type==AT_LOCALSERVER || type==AT_REMOTESERVER) //~9826I~
                	currentServerEswn=ii;                      //~9826I~
            }                                                      //~9826I~
        }                                                          //~9826I~
        else                                                       //~v@@@I~
        {
            acs=new Account[PLAYERS];
            //~v@@@I~
	        for (int ii=0;ii<PLAYERS;ii++)                         //~v@@@I~
    	    {                                                      //~v@@@I~
//          	int newpos=Players.nextPlayer(ii,-(PctrGame/PLAYERS));//~v@@@I~//~9503R~
//          	int newpos=Players.nextPlayer(ii,-PctrGame);      //~9503I~//~9513R~
            	int newpos=Players.nextPlayer(ii,3*PctrGame);      //~9513I~
        		acs[newpos]=accountsByESWN[ii];                    //~v@@@I~
                int type=acs[newpos].type;                         //~v@@@I~
                if (type==AT_LOCALSERVER || type==AT_REMOTESERVER) //~v@@@I~
                	currentServerEswn=newpos;                      //~v@@@I~
		        if (Dump.Y) Dump.println("setCurrentAccountsByESWN ii="+ii+",newPos="+newpos);//~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
		if (Dump.Y) Dump.println("setCurrentAccountsByESWN currentServerEswn="+currentServerEswn);//~9826I~
        currentEswnByAccount=new int[PLAYERS];                     //~v@@5I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@5I~
        {                                                          //~v@@5I~
            currentEswnByAccount[acs[ii].idxMembers]=ii;            //~v@@5I~
			if (Dump.Y) Dump.println("setCurrentAccountsByESWN currentEswn="+ii+",name="+acs[ii].name);//~9503I~
        }                                                          //~v@@5I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9416I~
        {                                                          //~9416I~
//          currentEswnByPosition[ii]=Players.nextPlayer(ii,-(PctrGame/PLAYERS));//~9416I~//~9513R~
            currentEswnByPosition[ii]=Players.nextPlayer(ii,3*PctrGame);//~9513R~
//          currentPositionByEswn[Players.nextPlayer(ii,-(PctrGame/PLAYERS))]=ii;//~9416I~//~9513R~
            currentPositionByEswn[Players.nextPlayer(ii,3*PctrGame)]=ii;//~9513R~
        }                                                          //~9416I~
		if (Dump.Y) Dump.println("setCurrentAccountsByESWN currentEswnByAccount="+Arrays.toString(currentEswnByAccount));//~v@@5I~
		if (Dump.Y) Dump.println("setCurrentAccountsByESWN currentServerEswn="+currentServerEswn);//~v@@@I~
		if (Dump.Y) Dump.println("setCurrentAccountsByESWN currentEswnByPosition="+Arrays.toString(currentEswnByPosition));//~9416I~
		if (Dump.Y) Dump.println("setCurrentAccountsByESWN rc currentAccountsByESWN="+Arrays.toString(acs));//~0309I~
        return acs;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~9828I~
    public static int[] getCurrentEswnByPositionAtResume(int PctrGame)//~9828R~
    {                                                              //~9828I~
        if (Dump.Y) Dump.println("getCurrentEswnByPositionAtResume  ctrGame="+PctrGame);//~9828I~//~9829R~
        int[] winds=new int[PLAYERS];                              //~9828I~
	    for (int ii=0;ii<PLAYERS;ii++)                             //~9828I~
    	{                                                          //~9828I~
            winds[ii]=Players.nextPlayer(0/*eswn-E*/,3*PctrGame+ii);	//1st dealer current wind//~9829R~
        }                                                          //~9828I~
		if (Dump.Y) Dump.println("getCurrentEswnByPositionAtResume ="+Arrays.toString(winds));//~9828I~//~9829R~
        return winds;                                              //~9828I~
    }                                                              //~9828I~
    //**************************************************           //~v@@5I~
    //*current eswn in account sequence                            //~v@@5I~
    //**************************************************           //~v@@5I~
    public int[] getCurrentEswnByAccount()                         //~v@@5I~
    {                                                              //~v@@5I~
        if (Dump.Y) Dump.println("getCurrentEswnByAccount tbl="+Arrays.toString(currentEswnByAccount));//~v@@5I~
        return currentEswnByAccount;                                //~v@@5I~
    }                                                              //~v@@5I~
    //**************************************************           //~9416I~
    //*current eswn in position(startingEswn) sequence             //~9416I~
    //**************************************************           //~9416I~
    public int[] getCurrentEswnByPosition()                        //~9416I~
    {                                                              //~9416I~
        if (Dump.Y) Dump.println("getCurrentEswnByPosition tbl="+Arrays.toString(currentEswnByPosition));//~9416I~
        return currentEswnByPosition;                              //~9416I~
    }                                                              //~9416I~
    //**************************************************           //~9320I~
    public int getCurrentEswnByAccount(int Pidx)                   //~9320I~
    {                                                              //~9320I~
    	int eswn=currentEswnByAccount[Pidx];                       //~9320I~
        if (Dump.Y) Dump.println("getCurrentEswnByAccount idx="+Pidx+",eswn="+eswn+",tbl="+Arrays.toString(currentEswnByAccount));//~9320I~
        return eswn;                                               //~9320I~
    }                                                              //~9320I~
    //**************************************************           //~9416I~
    public int getCurrentEswnByPosition(int Pidx)                  //~9416I~
    {                                                              //~9416I~
    	int eswn=currentEswnByPosition[Pidx];                      //~9416I~
        if (Dump.Y) Dump.println("getCurrentEswnByPosition idx="+Pidx+",eswn="+eswn+",tbl="+Arrays.toString(currentEswnByPosition));//~9416I~
        return eswn;                                               //~9416I~
    }                                                              //~9416I~
    //**************************************************           //~v@@@I~
    //*starting ESWN-->idxAccount as you are at [0]                         //~v@@@R~//~9416R~
    //**************************************************           //~v@@@I~
    private int[] accountsByYourESWN()                              //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.accountsByYourESWN");    //~v@@@I~
		int[] byESWN=new int[PLAYERS];                     //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	int pos=Players.playerRelative(ii,yourESWN);           //~v@@@I~
        	int idxAccount=accountsByESWN[ii].idxMembers;          //~v@@@R~
        	byESWN[pos]=idxAccount;  //Account idx of Members      //~v@@@R~
	        if (Dump.Y) Dump.println("Accounts.accountsByYourESWN ii="+ii+",newpos="+pos+",idxAccount="+idxAccount+",name="+accounts[idxAccount].name);//~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.accountsByYourESWN return "+Arrays.toString(byESWN));//~9416R~
        return byESWN;                                             //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*get idxAccount of current ESWN                              //~v@@@I~
    //**************************************************           //~v@@@I~
    public int currentEswnToMember(int Peswn)                      //~v@@@R~
    {                                                              //~v@@@I~
		int idxAccount=currentAccountsByESWN[Peswn].idxMembers;  //ESWN->idxAccount//~v@@@I~
        if (Dump.Y) Dump.println("Accounts.currentEswnToMember eswn="+Peswn+",idxAccount="+idxAccount);//~v@@@I~
		return idxAccount;                                         //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~9B01I~
    //*get position(startingEswn) to account[](member) idx;        //~9B01I~
    //**************************************************           //~9B01I~
    public int positionToMember(int Peswn)                         //~9B01I~
    {                                                              //~9B01I~
		int idxAccount=accountsByESWN[Peswn].idxMembers;  //ESWN->idxAccount//~9B01I~
        if (Dump.Y) Dump.println("Accounts.positionToMember eswn="+Peswn+",idxAccount="+idxAccount);//~9B01I~
		return idxAccount;                                         //~9B01I~
    }                                                              //~9B01I~
    //**************************************************           //~9416I~
    //*get position(startingEswn) from current eswn                //~9416I~
    //**************************************************           //~9416I~
    public int currentEswnToPosition(int Peswn)                   //~9416I~
    {                                                              //~9416I~
    	int rc=currentPositionByEswn[Peswn];                       //~9416I~
        if (Dump.Y) Dump.println("Accounts.currentEswnToPosition eswn="+Peswn+",rc="+rc+",tbl="+Arrays.toString(currentPositionByEswn));//~9416I~
        return rc;
    }                                                              //~9416I~
    //**************************************************           //~v@@5I~
    //*get Account of current ESWN                                 //~v@@5I~
    //**************************************************           //~v@@5I~
    public Account getAccountByCurrentEswn(int Peswn)                  //~v@@5I~
    {                                                              //~v@@5I~
		Account a=currentAccountsByESWN[Peswn];                    //~v@@5I~
        if (Dump.Y) Dump.println("Accounts.getAccountByCurrentEswn Peswn="+Peswn+",idx="+a.idxMembers);//~v@@5I~
		return a;                                                  //~v@@5I~
    }                                                              //~v@@5I~
    //**************************************************           //~v@@5I~
    public String currentEswnToAccountName(int Peswn)              //~v@@5R~
    {                                                              //~v@@5I~
		int idx=currentEswnToMember(Peswn);                        //~v@@5I~
    	String s=accountNames[idx];                                //~v@@5I~
        if (Dump.Y) Dump.println("Accounts.currentEswnToAccountName eswn="+Peswn+",idxAccount="+idx+",name="+s+",all="+Arrays.toString(accountNames));//~v@@5I~
		return s;                                           //~v@@5I~
    }                                                              //~v@@5I~
//******************************************************************************//~v@@@M~
//* your ESWN in the game                                          //~v@@@M~
//******************************************************************************//~v@@@M~
	public static int getCurrentEswn()                             //~v@@@R~
    {                                                              //~v@@@M~
//        if (Dump.Y) Dump.println("Accounts.getCurrentEswn="+currentESWN);//~v@@@R~
//        return currentESWN;                                      //~v@@@M~
//      int eswn=Players.nextPlayer(AG.aAccounts.yourESWN/*base*/,AG.aStatus.gameCtrGame);//~v@@@R~//~9319R~
//      int eswn=Players.nextPlayer(AG.aAccounts.yourESWN/*base*/,-AG.aStatus.gameCtrGame);//~9319I~//~9513R~
        int eswn=Players.nextPlayer(AG.aAccounts.yourESWN/*base*/,3*AG.aStatus.gameCtrGame);//~9513I~
        if (Dump.Y) Dump.println("Accounts.getCurrentEswn="+eswn+",yourESWN="+AG.aAccounts.yourESWN+",gameCtrGame="+AG.aStatus.gameCtrGame); //~v@@@R~//~va9iR~
        return eswn;                                               //~v@@@M~
    }                                                              //~v@@@M~
//******************************************************************************//~v@@5I~
//******************************************************************************//~v@@5I~
	public static int getIndexLocal()                              //~v@@5I~
    {                                                              //~v@@5I~
        if (Dump.Y) Dump.println("Accounts.getLocalIndex idxLocal="+AG.aAccounts.idxLocal);//~v@@5I~
        return AG.aAccounts.idxLocal;                                           //~v@@5I~
    }                                                              //~v@@5I~
////******************************************************************************//~v@@@I~//~v@@5R~
////* get index on players; on Server return eswn, onClient return 0 //~v@@@R~//~v@@5R~
////******************************************************************************//~v@@@I~//~v@@5R~
//    public static int getPlayerYou()                               //~v@@@R~//~v@@5R~
//    {                                                              //~v@@@I~//~v@@5R~
//        int pos;                                                   //~v@@@I~//~v@@5R~
//        boolean swServer=isServer();                               //~v@@@I~//~v@@5R~
//        if  (swServer)                                             //~v@@@I~//~v@@5R~
//            pos=Players.nextPlayer(AG.aAccounts.yourESWN/*base*/,AG.aStatus.gameCtrGame);//~v@@@I~//~v@@5R~
//        else                                                       //~v@@@I~//~v@@5R~
//            pos=PLAYER_YOU;                                        //~v@@@I~//~v@@5R~
//        if (Dump.Y) Dump.println("Accounts.getPlayerYou swServer="+swServer+",pos="+pos);//~v@@@R~//~v@@5R~
//        return pos;                                                //~v@@@I~//~v@@5R~
//    }                                                              //~v@@@I~//~v@@5R~
//    //*******************************************************************//~v@@@I~//~v@@5R~
//    public static TileData[] getHands()                            //~v@@@I~//~v@@5R~
//    {                                                              //~v@@@I~//~v@@5R~
//        if (Dump.Y) Dump.println("Accounts.getHands");             //~v@@@I~//~v@@5R~
//        int player=Accounts.getPlayerYou(); //eswn(server) or PLAYER_YOU(client)//~v@@@R~//~v@@5R~
//        TileData[] tds=AG.aPlayers.getHands(player);               //~v@@@I~//~v@@5R~
//        if (Dump.Y) Dump.println("Accounts.getHands ctr="+tds.length);//~v@@@I~//~v@@5R~
//        return tds;                                                //~v@@@I~//~v@@5R~
//    }                                                              //~v@@@I~//~v@@5R~
//******************************************************************************//~v@@@I~
//* your ESWN in the game                                          //~v@@@I~
//******************************************************************************//~v@@@I~
	public int getCurrentEswnOfAccount(Accounts.Account Paccount)  //~v@@@I~
    {                                                              //~v@@@I~
        int eswn=Players.nextPlayer(Paccount.ESWN/*base*/,AG.aStatus.gameCtrGame);//~v@@@I~
        if (Dump.Y) Dump.println("Accounts.getCurrentEswnOfAccount="+eswn+"idxAaccount="+Paccount.idxMembers);//~v@@@I~
        return eswn;                                               //~v@@@I~
    }                                                              //~v@@@I~
//******************************************************************************//~9322I~
	public int[] getStartingEswnOfAccounts()                       //~9322I~
    {                                                              //~9322I~
    	int[] eswn=new int[PLAYERS];                               //~9322I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9322I~
        	eswn[ii]=accounts[ii].ESWN;                                //~9322I~
        if (Dump.Y) Dump.println("Accounts.getStartingEswnOfAccounts eswn="+Arrays.toString(eswn));//~9322I~
        return eswn;                                               //~9322I~
    }                                                              //~9322I~
//******************************************************************************//~v@@@M~
//* relative pos of starter of the game                            //~v@@@M~
//******************************************************************************//~v@@@M~
	public int getCurrentStarterPos()                              //~v@@@M~
    {                                                              //~v@@@M~
//  	int pos=Players.nextPlayer(0,-currentESWN);                //~v@@@M~
		int eswn=getCurrentEswn();                                 //~v@@@R~
	  	int pos=Players.nextPlayer(0,-eswn);                       //~v@@@M~
        if (Dump.Y) Dump.println("Accounts.getCurrentStarterPos currentESWN="+eswn+",currentStarterPos="+pos);//~v@@@M~
        return pos;                                                //~v@@@M~
    }                                                              //~v@@@M~
    //******************************************************************************//~v@@@I~
    //* server ESWN in this game                                   //~v@@@I~
    //******************************************************************************//~v@@@I~
	public int getCurrentServerEswn()                              //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.getCurrentServerEswn eswn="+currentServerEswn);//~v@@@I~//~9826R~
        return currentServerEswn;                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************************************//~v@@@I~
    //* relative pos of the ESWN from current account              //~v@@@I~
    //******************************************************************************//~v@@@I~
	public static int eswnToPlayer(int Peswn)                      //~v@@@R~
    {                                                              //~v@@@M~
		int eswn=getCurrentEswn();                                 //~v@@@R~
	  	int player=Players.playerRelative(Peswn,eswn);             //~v@@@R~
        if (Dump.Y) Dump.println("Accounts.eswnToPlayer parm eswn="+Peswn+",currentESWN="+eswn+",out player="+player);//~v@@@R~
        return player;                                             //~v@@@R~
    }                                                              //~v@@@M~
    //**************************************************           //~v@@@I~
    //*get idxAccount of player postition on table                 //~v@@@I~
    //**************************************************           //~v@@@I~
    public int playerToMember(int Pplayer)                         //~v@@@I~
    {                                                              //~v@@@I~
		int eswn=Players.nextPlayer(getCurrentEswn(),Pplayer); 	//eswn of the player relative to you//~v@@@R~
		int idxAccount=currentAccountsByESWN[eswn].idxMembers;  //ESWN->idxAccount//~v@@@I~
        if (Dump.Y) Dump.println("Accounts.playerToMember player="+Pplayer+",eswn="+eswn+",idxAccount="+idxAccount);//~v@@@I~
		return idxAccount;                                         //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~9630I~
    //*get Robot                                                   //~9630I~
    //**************************************************           //~9630I~
    public Robot getRobot(int Pplayer)                             //~9630R~
    {                                                              //~9630I~
    	int idx=AG.aAccounts.playerToMember(Pplayer);                           //~9630I~
        Account a=accounts[idx];                                   //~9630R~
        Robot r=null;                                              //~9630I~
        if (a.isDummy())                                           //~9630I~
        	r=a.robot;                                             //~9630I~
        if (Dump.Y) Dump.println("Accounts.getRobot player="+Pplayer+",idx="+idx+",name="+a.name+",rc="+Utils.toString(r));//~9630R~//~0228R~
        return r;                                                  //~9630I~
    }                                                              //~9630I~
    //**************************************************           //~9A26I~
    public boolean isRobotPlayer(int Pplayer)                      //~9A26I~
    {                                                              //~9A26I~
    	int idx=AG.aAccounts.playerToMember(Pplayer);              //~9A26I~
        Account a=accounts[idx];                                   //~9A26I~
        boolean rc=a.isDummy();                                    //~9A26I~
        if (Dump.Y) Dump.println("Accounts.isRobotPlayer player="+Pplayer+",idx="+idx+",name="+a.name+",rc="+rc);//~9A26I~
        return rc;                                                 //~9A26I~
    }                                                              //~9A26I~
    //**************************************************           //~9420I~
    //*get idxAccount of player postition on startingESWN sequence table//~9420I~
    //**************************************************           //~9420I~
    public int playerToPosition(int Pplayer)                       //~9420I~
    {                                                              //~9420I~
		int eswn=Players.nextPlayer(getCurrentEswn(),Pplayer); 	//eswn of the player relative to you//~9420I~
	    int idx=currentEswnToPosition(eswn);                        //~9420I~
        if (Dump.Y) Dump.println("Accounts.playerToPosition player="+Pplayer+",eswn="+eswn+",idx="+idx);//~9420I~
		return idx;                                                //~9420I~
    }                                                              //~9420I~
    //**************************************************           //~v@@@I~
    //*idxAccount->eswn                                            //~v@@@I~
    //**************************************************           //~v@@@I~
    public int idxToCurrentEswn(int Pidx)                          //~v@@@I~
    {                                                              //~v@@@I~
		Account[] acs=currentAccountsByESWN;  //ESWN->idxAccount   //~v@@@I~
        int eswn=0;                                                //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	if (acs[ii].idxMembers==Pidx)                                      //~v@@@I~
            {                                                      //~v@@@I~
            	eswn=ii;                                           //~v@@@I~
            	break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("Accounts.idxToCurrentEswn Pidx="+Pidx+",eswn="+eswn);//~v@@@I~
        return eswn;                                               //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************************           //~v@@@I~//~9905R~
//    //*isDrawThisDevice                                            //~v@@@I~//~9905R~
//    //**************************************************           //~v@@@I~//~9905R~
//    public boolean isDrawThisDevice(int Pplayer)                   //~v@@@I~//~9905R~
//    {                                                              //~v@@@I~//~9905R~
//        int player=mapDummyPlayer(Pplayer);                        //~v@@@I~//~9905R~
//        if (Dump.Y) Dump.println("Accounts.isDrawThisDevice Player="+Pplayer+",player="+player+",rc="+(player==PLAYER_YOU));//~v@@@I~//~9905R~
//        return player==PLAYER_YOU;                                 //~v@@@I~//~9905R~
//    }                                                              //~v@@@I~//~9905R~
    //**************************************************           //~v@@@I~
    //*get eswn of the player                                      //~v@@@I~
    //**************************************************           //~v@@@I~
    public static int playerToEswn(int Pplayer)                    //~v@@@R~
    {                                                              //~v@@@I~
		int eswn=Players.nextPlayer(getCurrentEswn(),Pplayer); 	//eswn of the player relative to you//~v@@@R~
        if (Dump.Y) Dump.println("Accounts.playerToEswn player="+Pplayer+",eswn="+eswn);//~v@@@I~
		return eswn;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~9B26I~
    public static int getCurrentPlayerEswn()                       //~9B26I~
    {                                                              //~9B26I~
        int player=AG.aPlayers.getCurrentPlayer();                 //~9B26I~
        int eswn=playerToEswn(player);                              //~9B26I~
        if (Dump.Y) Dump.println("Accounts.getCurrentPlayerEswn player="+player+",eswn="+eswn);//~9B26I~
		return eswn;                                               //~9B26I~
    }                                                              //~9B26I~
    //**************************************************           //~v@@@I~
    //*chk dummy of the player position                            //~v@@@R~
    //**************************************************           //~v@@@I~
    public boolean isDummyPlayer(int Pplayer)                      //~v@@@I~
    {                                                              //~v@@@I~
		int idxAccount=playerToMember(Pplayer);                    //~v@@@R~
        boolean rc=accounts[idxAccount].isDummy();                 //~v@@@R~
        if (Dump.Y) Dump.println("Accounts.isDummyPlayer player="+Pplayer+",idxAccount="+idxAccount+",rc="+rc);//~v@@@R~
		return rc;                                                 //~v@@@R~
    }                                                              //~v@@@I~
    //**************************************************************           //~va60I~
    //*get real player of dealer if player is robot,else return -1 //~va60I~
    //*if dealer is robot,next humean in counterclockwise          //~vae5I~
    //**************************************************************           //~va60I~
    public int/*player*/ getRealDealerForRobot(int Pplayer)        //~va60I~
    {                                                              //~va60I~
    	int player=-1;                                             //~va60I~
    	if (isDummyPlayer(Pplayer))                                //~va60I~
        {                                                          //~va60I~
		    player=getCurrentDealerReal();                          //~va60I~
        }                                                          //~va60I~
        if (Dump.Y) Dump.println("Accounts.getRealDealerForRobot Pplayer="+Pplayer+",player="+player);//~va60R~
		return player;                                             //~va60I~
    }                                                              //~va60I~
    //**************************************************************//~va60I~
    public int/*eswn*/ getRealDealerEswnForRobot(int Pplayer)      //~va60I~
    {                                                              //~va60I~
    	int eswn=-1;                                               //~va60I~
    	if (isDummyPlayer(Pplayer))                                //~va60I~
        {                                                          //~va60I~
		    int player=getCurrentDealerReal();                     //~va60I~
            eswn=playerToEswn(player);                             //~va60I~
        }                                                          //~va60I~
        if (Dump.Y) Dump.println("Accounts.getRealDealerEswnForRobot Pplayer="+Pplayer+",eswn="+eswn);//~va60I~
		return eswn;                                               //~va60I~
    }                                                              //~va60I~
    //**************************************************************//~va60I~
    //*get real dealer eswn for robot eswn,-1 if not robot         //~va60I~
    //**************************************************************//~va60I~
    public int/*eswn*/ getRealDealerEswnForRobotEswn(int Peswn)    //~va60I~
    {                                                              //~va60I~
    	int player=eswnToPlayer(Peswn);                            //~va60I~
        int eswn=getRealDealerEswnForRobot(player);                //~va60I~
        if (Dump.Y) Dump.println("Accounts.getRealDealerEswnForRobotEswn Peswn="+Peswn+",eswn="+eswn);//~va60I~
		return eswn;                                               //~va60I~
    }                                                              //~va60I~
    //**************************************************           //~va60I~
    //*sender is current dealer for robot, else itself             //~va60I~
    //**************************************************           //~va60I~
    public int/*eswn*/ getRealSenderEswn(int Peswn)                //~va60I~
    {                                                              //~va60I~
        int eswn=getRealDealerEswnForRobotEswn(Peswn);             //~va60I~
        if (eswn==-1)	//not robot                                //~va60I~
        	eswn=Peswn;                                            //~va60I~
        if (Dump.Y) Dump.println("Accounts.getRealSenderEswn Peswn="+Peswn+",eswn="+eswn);//~va60I~
		return eswn;                                               //~va60I~
    }                                                              //~va60I~
    //**************************************************           //~v@@@I~
    //*get Robot of the player position                            //~v@@@I~
    //**************************************************           //~v@@@I~
    public Robot getRobotPlayer(int Pplayer)                       //~v@@@I~
    {                                                              //~v@@@I~
		int idxAccount=playerToMember(Pplayer);                    //~v@@@I~
        Robot r=accounts[idxAccount].robot;                        //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.getRobotPlayer player="+Pplayer+",idxAccount="+idxAccount+",robot=null:"+(robot==null));//~v@@@R~
		return r;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    //*get player pos in players[] on Server                       //~v@@@I~
    //**************************************************           //~v@@@I~
    public int  getPlayerPosOnServer()                            //~v@@@R~
    {                                                              //~v@@@I~
        int player=getPlayerPosOnServer(getCurrentEswn());                    //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.getPlayerPosOnServer player="+player);//~v@@@I~
        return player;                                             //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@I~
    public int getPlayerPosOnServer(int Peswn/*of client*/)        //~v@@@I~
    {                                                              //~v@@@I~
	  	int player=Players.playerRelative(Peswn,currentServerEswn);//~v@@@I~
        if (Dump.Y) Dump.println("Accounts.getPlayerPosOnServer eswn="+Peswn+",currentserverEswn="+currentServerEswn+"out player="+player);//~v@@@I~
		return player;                                             //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************************************//~v@@@I~
    //*current game starter's relative position from me            //~v@@@I~
    //******************************************************************************//~v@@@I~
	public int getCurrentStarter()                                 //~v@@@M~
    {                                                              //~v@@@M~
//  	int pos=Players.nextPlayer(0,-currentESWN);	               //~v@@@M~
		int eswn=getCurrentEswn();                                 //~v@@@R~
    	int pos=Players.nextPlayer(0,-eswn);                       //~v@@@M~
        if (Dump.Y) Dump.println("Accounts.getCurrentStarter currentESWN="+eswn+",pos="+pos);//~v@@@M~
        return pos;                                                //~v@@@M~
    }                                                              //~v@@@M~
    //**************************************************           //~v@@@I~
    private void showNamePlate()                                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.showNamePlate");         //~v@@@I~
		AG.aNamePlate.showPlate(accountsLocal);                    //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~v@@@M~
    public  void positionMove()                                    //~v@@@M~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("Accounts.positionMove starter="+tempStarter+",idxLocal="+idxLocal+",status="+Status.getGameStatus());//~v@@@R~
        setPosition(posMember);	//set eswn to original accounts//~v@@@I~
        swPositionMoved=true;                                      //~v@@@I~
        accountsByESWN=sortAccounts();	//by initial position                                            //~v@@@I~//~9429R~
	    currentAccountsByESWN=setCurrentAccountsByESWN(0);         //~v@@@I~
        accountsLocal=accountsByYourESWN();   //ESWN->membersIdx   //~v@@@I~
        showNamePlate();                                           //~v@@@I~
	    AG.aRiver.endOfPositioning(); //erase setup 6 tiles        //~v@@@I~
        starterRelativePos=Players.playerRelative(0,yourESWN);     //~v@@@R~
        if (Dump.Y) Dump.println("Accounts.positionMove starterRelativePos="+starterRelativePos+",yourESWN="+yourESWN);//~9319I~
	    AG.aStarter.moveStarter(starterRelativePos);               //~v@@@I~
//      Status.setGameStatusNewSet(starterRelativePos);            //~v@@@R~
//  	AG.aStarter.showGameSeq(starterRelativePos);               //~v@@@R~
    }                                                              //~v@@@M~
    //**************************************************           //~9901I~
    private void positionMoveResume(HistoryData Phd)               //~9901I~
    {                                                              //~9901I~
        if (Dump.Y) Dump.println("Accounts.positionMoveResume");   //~9901I~
        setPosMemberResume(Phd);	//posMember,index to Account[] //~9901I~
        setPosition(posMember);	//set eswn to original accounts    //~9901I~
        swPositionMoved=true;                                      //~9901I~
        accountsByESWN=sortAccounts();	//by initial position      //~9901I~
//      int ctrGame=Phd.scores[HDPOS_SCORES_GAMESEQ][POS_GAMESEQ_CTRGAME];//~9902I~//~0309R~
    	Rect r=setGameSeqResume(Phd);  //set gameCtr               //~0309I~
//      int ctrGame=r.top;                                         //~0309I~//~va60R~
//      currentAccountsByESWN=setCurrentAccountsByESWN(ctrGame); //do in Status.setGameSeqRusume to use currentAccountsByEswn in RoundStat.newGame from resetForNewGame         //~9901I~//~9902R~//~va60R~
        accountsLocal=accountsByYourESWN();   //ESWN->membersIdx   //~9901I~
		AG.aNamePlate.showPlate();      //like as showFirstDiceShooter//~9902I~
        showNamePlate();                                           //~9901I~
//      AG.aRiver.endOfPositioning(); //erase setup 6 tiles        //~9901I~
        starterRelativePos=Players.playerRelative(0,yourESWN);     //~9901I~
        if (Dump.Y) Dump.println("Accounts.positionMoveResume starterRelativePos="+starterRelativePos+",yourESWN="+yourESWN);//~9901I~//~0309R~
	    AG.aStarter.moveStarter(starterRelativePos);               //~9901I~
    }                                                              //~9901I~
    //**************************************************           //~v@@@I~
    //*at all position moved                                       //~v@@@I~
    //**************************************************           //~v@@@I~
    public  void positionMoved()                                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.positionMoved status="+Status.getGameStatus());//~v@@@I~
        Status.setGameStatusNewSet(starterRelativePos);            //~v@@@I~
    	AG.aStarter.showGameSeq(starterRelativePos);   //status=STARTGAME//~v@@@I~
        enableDiceRelative(starterRelativePos);                        //~v@@@R~
	    acaction.setWaitStatus(GCM_STARTGAME);                               //~v@@@I~
//      AG.aDiceBox.setWaitingDice(starterRelativePos,true/*set starterID*/);   //waiting lamp//~9514R~
        AG.aDiceBox.setLightStarter(starterRelativePos,false/*resetPrev*/);   //waiting lamp//~9514I~//~9604R~
//        currentESWN=yourESWN;   //advance by nextgame            //~v@@@R~
        if ((TestOption.option2 & TestOption.TO2_FINAL_GAME)!=0)              //~9527I~
			AG.aAccounts.finalGameTest();                          //~9527I~
    }                                                              //~v@@@I~
    //**************************************************           //~9901I~
    private void positionMovedResume(HistoryData Phd)              //~9901I~
    {                                                              //~9901I~
        if (Dump.Y) Dump.println("Accounts.positionMovedResume status="+Status.getGameStatus());//~9901I~
//      Status.setGameStatusNewSet(starterRelativePos);            //~9901I~
		starterRelativePos=Players.playerRelative(0,yourESWN);      //~9901I~
        if (Dump.Y) Dump.println("Accounts.positionMovedResume starterRelativePos="+starterRelativePos);//~9901I~
        Status.setGameStatusNewSetResume(starterRelativePos,Phd.scores[HDPOS_SCORES_SCORE]);  //set score//~9901R~
//  	setGameSeqResume(Phd);  //set gameCtr                      //~9901I~//~0309R~
    	AG.aStarter.drawStarter(starterRelativePos);               //~9902R~
    	AG.aStarter.showGameSeq(starterRelativePos);   //status=STARTGAME//~9902I~
//      enableDiceRelative(starterRelativePos);                    //~9905R~//~0309R~
		int dealer=eswnToPlayer(PLAYER_YOU);                       //~0309I~
        enableDiceRelative(dealer);                                //~0309I~
//      acaction.setWaitStatus(GCM_STARTGAME);                     //~9901R~//~9905R~
        acaction.setWaitStatus(GCM_STARTGAME_NEXT);                //~9905I~
//      AG.aDiceBox.setLightStarter(starterRelativePos,false/*resetPrev*/);   //waiting lamp//~9901R~
//  	AG.aAccounts.finalGameTest();                              //~9901I~
        setBirdAndCont(Phd);                                       //~9B01I~
    	setNextGameResume();                                       //~9901R~
    }                                                              //~9901I~
    //**************************************************           //~9B01I~
    private void setBirdAndCont(HistoryData Phd)                   //~9B01I~
    {                                                              //~9B01I~
        if (Dump.Y) Dump.println("Accounts.setBirdAndCont");       //~9B01I~
    	int birdAndCont=Phd.scores[HDPOS_SCORES_OTHER][POS_OTHER_BIRDANDCONT];//~9B01I~
        boolean[] swsBird=new boolean[PLAYERS];	//initial ESWN seq //~9B01R~
        Point p=getBirdAndCont(birdAndCont,swsBird);               //~9B01I~
        int contpos=p.x; int contctr=p.y;                          //~9B01I~
	    ctrContinuedGain[contpos]=contctr;                         //~9B01I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9B01I~
        {                                                          //~9B01I~
		    int idxMember=positionToMember(ii);                    //~9B01I~
	        if (Dump.Y) Dump.println("Accounts.setBirdAndCont ii="+ii+",idx="+idxMember);//~9B01I~
	    	accounts[idxMember].setGrilled(swsBird[ii]);           //~9B01R~
        }                                                          //~9B01I~
        AG.aStarter.showCtrContinuedGain(ctrContinuedGain);        //~9B01M~
    }                                                              //~9B01I~
    //**************************************************           //~9901I~
    private void setNextGameResume()                               //~9901R~//~9B01R~
    {                                                              //~9901I~
        if (Dump.Y) Dump.println("Accounts.setNextGameResume");    //~9901R~//~9B01R~
//      currentAccountsByESWN=setCurrentAccountsByESWN(AG.aStatus.gameCtrGame);//~9901I~//~0309R~
        int player=eswnToPlayer(0/*East*/);                        //~9901I~
        AG.aDiceBox.setLightStarter(player,false/*resetPrev*/);   //startermark//~9905R~
        AG.aNamePlate.showScore();	//after update currentPositionByEswn//~9901I~
    }                                                              //~9901I~
    //**************************************************           //~9901I~
//  private void setGameSeqResume(HistoryData Phd)                 //~9901I~//~0309R~
    private Rect setGameSeqResume(HistoryData Phd)                 //~0309I~
    {                                                              //~9901I~
        int[] gameSeq=Phd.scores[HDPOS_SCORES_GAMESEQ];          //~9901I~
        int endgameType=Phd.scores[HDPOS_SCORES_OTHER][POS_OTHER_ENDGAME];//~0308I~
        int nextgameType=Phd.scores[HDPOS_SCORES_OTHER][POS_OTHER_NEXTGAME];//~9901I~
        if (Dump.Y) Dump.println("Accounts.setGameSeqResume nextgameType="+nextgameType+",gameseq="+Arrays.toString(gameSeq));//~9901I~
//      Status.setGameSeqResume(gameSeq,nextgameType==NGTP_CONTINUE,false/*nextRound*/); //TODO nextround//~9901I~//~9904R~
//      Status.setGameSeqResume(gameSeq,nextgameType,false/*nextRound*/); //TODO nextround//~9904I~//~0308R~
//      Status.setGameSeqResume(gameSeq,endgameType,nextgameType,false/*nextRound*/); //TODO nextround//~0308I~//~0309R~
        Rect r=Status.setGameSeqResume(gameSeq,endgameType,nextgameType,false/*nextRound*/); //nextround//~0309I~//~0322R~
        if (Dump.Y) Dump.println("Accounts.setGameSeqResume return="+r.toString());//~0309I~
        return r;                                                  //~0309I~
    }                                                              //~9901I~
    //**************************************************           //~9502I~
    //*from ScoreDlg  Svr/Client                                   //~9502I~//~9830R~
    //**************************************************           //~9502I~
    public  void nextGame(boolean PswServer,int PendgameType,int PnextgameType)                                        //~9502R~
    {                                                              //~9502R~
        if (Dump.Y) Dump.println("Accounts.nextGame status="+Status.getGameStatus()+",swServer="+PswServer+",endgametype="+PendgameType+",nextgameType="+PnextgameType);//~9502R~
        endgameTypeNextGame=PendgameType;                          //~9830I~
        nextgameTypeNextGame=PnextgameType;                        //~9830I~
        if (Status.isGameOver())                                   //~9502R~
        {                                                          //~9519I~
        	gameOver();                                            //~9519I~
            return;                                          //~9502R~//~0322R~
        }                                                          //~9519I~
        AG.aComplete.savePendingReach();                           //~9521I~
        if (AG.aLastGame.chkFinalGame(PendgameType,PnextgameType)) //FinalGameDlg shown//~9817R~
        {                                                          //~9502I~
            return;                                                //~9504R~
        }
//      AG.aComplete.resetGrilledBird(PendgameType);                            //~9501I~//~9503R~
//        if ((TestOption.option2 & TestOption.TO2_SUSPEND)!=0)                 //~9819I~//~9820I~//~9830R~
//        {                                                          //~9820I~//~9830R~
//            AG.aStatus.ctrSuspendRequest=2;                        //~9820R~//~9830R~
//            AG.aStatus.swEswnSuspendRequest[1]=true;               //~9820R~//~9830R~
//            AG.aStatus.swEswnSuspendRequest[2]=true;               //~9820I~//~9830R~
//        }                                                          //~9820I~//~9830R~
        if (!suspendGame(PswServer,PendgameType,PnextgameType))                               //~9822R~//~9823R~
        {                                                          //~9822I~
            nextGameWithoutSuspended(PendgameType,PnextgameType);  //~9822I~
//      Status.endGame(PendgameType,PnextgameType);	//advance gameSeq,resetForNextGame//~9820R~//~9822R~
//      currentAccountsByESWN=setCurrentAccountsByESWN(AG.aStatus.gameCtrGame);//~9503I~//~9822R~
//      AG.aACATouch.showReadyToStartNextGame();    //may be suspend               //~9503I~//~9820R~//~9822R~
        }                                                          //~9822I~
//        if (!PswServer)                                            //~9502I~//~9503R~
//            return;                                                //~9502I~//~9503R~
//        int dealerPos=getCurrentStarter();                         //~9502R~//~9503R~
//        if (Dump.Y) Dump.println("Accounts.nextGame current dealerPos="+dealerPos);//~9502R~//~9503R~
//        enableDiceRelative(dealerPos);                             //~9502R~//~9503R~
//        acaction.setWaitStatus(GCM_STARTGAME_NEXT);                //~9502R~//~9503R~
    }                                                              //~9502R~
    //**************************************************           //~9830I~
    //*from ACATouch by GS_READY_TO_NEXTGAME                       //~9830I~
    //**************************************************           //~9830I~
    public void nextGameWithoutSuspended()                         //~9830I~
    {                                                              //~9830I~
        if (Dump.Y) Dump.println("Accounts.nextGameWithoutSuspended from ACAT by GS_READY_TO_NEXTGAME");//~9830R~
	    nextGameWithoutSuspended(endgameTypeNextGame,nextgameTypeNextGame);//~9830I~
    }                                                              //~9830I~
    //**************************************************           //~9822I~
    //*from suspendDlg.doContinue                                  //~9830I~
    //**************************************************           //~9830I~
    public void nextGameWithoutSuspended(int PendgameType,int PnextgameType)//~9822I~
    {                                                              //~9822I~
        if (Dump.Y) Dump.println("Accounts.nextGameWithoutSuspended endgameType="+PendgameType+",nextgameType="+PnextgameType);//~9822R~
        if (PnextgameType==NGTP_RESET)                   //~v@01I~ //~9905I~
        {                                                          //~9905I~
        	resetGameWithoutSuspended();                           //~v@01I~//~9905I~
            return;                                                //~9905I~
        }                                                          //~9905I~
        Status.endGame(PendgameType,PnextgameType);	//advance gameSeq,resetForNextGame//~9822I~
//      currentAccountsByESWN=setCurrentAccountsByESWN(AG.aStatus.gameCtrGame);//call in endgemae before resetForNewGame(use sendmag)//~9822I~//~0222R~
//      AG.aACATouch.showReadyToStartNextGame();    //may be suspend//~9822I~//~0307R~
        AG.aACATouch.showReadyToStartNextGame(GS_READY_TO_NEXTGAME_CONTINUE);    //may be suspend//~0307I~
    }                                                              //~9822I~
    //**************************************************           //~9704I~
    //*from ScoreDlg                                               //~9704I~
    //**************************************************           //~9704I~
    public  void resetGame(boolean PswServer)                      //~9704I~
    {                                                              //~9704I~
        if (Dump.Y) Dump.println("Accounts.resetGame status="+Status.getGameStatus()+",swServer="+PswServer);//~9704I~
        endgameTypeNextGame=EGDR_RESET; //for when suspended       //~9904I~
        nextgameTypeNextGame=NGTP_RESET;                           //~9904I~
        if (!suspendGame(PswServer,endgameTypeNextGame,nextgameTypeNextGame))//~9904I~
        {                                                          //~9904I~
////      AG.aComplete.savePendingReach();                           //~9704I~//~9904R~
//        Status.resetGame(); //reset for newgame                  //~9904R~
////      currentAccountsByESWN=setCurrentAccountsByESWN(AG.aStatus.gameCtrGame);//~9704I~//~9904R~
//        AG.aACATouch.showReadyToStartNextGame();                   //~9704I~//~9904R~
			resetGameWithoutSuspended();                           //~9904I~
        }                                                          //~9904I~
    }                                                              //~9704I~
    //**************************************************           //~9B01I~
    public  void resetGameByIOErr()                                //~9B01I~
    {                                                              //~9B01I~
        endgameTypeNextGame=EGDR_RESET; //for when suspended       //~9B01I~
        nextgameTypeNextGame=NGTP_RESET;                           //~9B01I~
        if (Dump.Y) Dump.println("Accounts.resetGameByIOErr status="+Status.getGameStatus()+",endgametype="+endgameTypeNextGame+",nextgametype="+nextgameTypeNextGame);//~9B01I~
//        if (!suspendGame(PswServer,endgameTypeNextGame,nextgameTypeNextGame))//~9B01I~
//        {                                                        //~9B01I~
//            resetGameWithoutSuspended();                         //~9B01I~
//        }                                                        //~9B01I~
    }                                                              //~9B01I~
    //**************************************************           //~9904I~
    //*from suspendDlg.doContinue                                  //~9904I~
    //**************************************************           //~9904I~
    private void resetGameWithoutSuspended()                       //~9904I~//~va60R~
    {                                                              //~9904I~
        Status.resetGame();	//reset for newgame                    //~9904I~
//      AG.aACATouch.showReadyToStartNextGame();                   //~9904I~//~0307R~
        AG.aACATouch.showReadyToStartNextGame(GS_READY_TO_NEXTGAME_RESET);//~0307I~
    }                                                              //~9904I~
    //**************************************************           //~9522I~
    //*from ScoreDlg                                               //~9522I~
    //**************************************************           //~9522I~
    public  void nextGameByLastGame(boolean PswServer,int PendgameType,int PnextgameType)//~9522I~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("Accounts.nextGameByLastGame status="+Status.getGameStatus()+",swServer="+PswServer+",endgametype="+PendgameType+",nextgameType="+PnextgameType);//~9522I~
//      Status.endGame(PendgameType,PnextgameType);	//advance gameSeq//~9522I~//~9902R~
//      currentAccountsByESWN=setCurrentAccountsByESWN(AG.aStatus.gameCtrGame);//~9522I~//~9902R~
//      AG.aACATouch.showReadyToStartNextGame();                   //~9522I~//~9902R~
        if (!suspendGame(PswServer,PendgameType,PnextgameType))    //~9902I~
        {                                                          //~9902I~
            nextGameWithoutSuspended(PendgameType,PnextgameType);  //~9902I~
        }                                                          //~9902I~
    }                                                              //~9522I~
    //**************************************************           //~9526I~
    //finalGameTest() TODO test by  if ((TestOption.option2 & TestOption.TO2_FINAL_GAME)!=0)//~0322I~
    //**************************************************           //~0322I~
    public  void finalGameTest() //TODO test                       //~9526R~
    {                                                              //~9526I~
        if (Dump.Y) Dump.println("Accounts.finalGameTest ctrGame="+AG.aStatus.gameCtrGame);//~9526I~
	    currentAccountsByESWN=setCurrentAccountsByESWN(AG.aStatus.gameCtrGame);//~9526I~
        int player=eswnToPlayer(0/*East*/);                         //~9527R~
        AG.aDiceBox.setLightStarter(player,true/*resetPrev*/);   //waiting lamp //~9527R~//~9604R~
        AG.aNamePlate.showScore();	//after update currentPositionByEswn//~9606I~
//      	int accidx=posMember[0];  	//                         //~9922R~//~9923M~
//          int eswn=currentEswnByAccount[accidx];                 //~9922I~//~9923M~
//          int player=Players.nextPlayer(testDealer,AG.aStatus.gameCtrGame);//~9922R~//~9923M~
//          if (Dump.Y) Dump.println("Accounts.positionMoved enableDiceRelative gamectr="+AG.aStatus.gameCtrGame+",starterPlayer="+starter+",testDelaer="+testDealer+",posMember="+Arrays.toString(posMember));//~9922I~//~9923M~
//          int starter=getCurrentStarter();                       //~9922I~//~9923M~
//          starter=Players.nextPlayer(starterRelativePos,AG.aStatus.gameCtrGame);//~9923M~
            starter=player;                                        //~9923I~
            if (Dump.Y) Dump.println("Accounts.finalGameTest OLD AG,playerCurrent="+AG.aPlayers.playerCurrent);//~vabgI~
            AG.aPlayers.playerCurrent=starter;                     //~vabgI~
            if (Dump.Y) Dump.println("Accounts.finalGameTest enableDiceRelative starter="+starter+",gameCtr="+AG.aStatus.gameCtrGame+",AG,playerCurrent="+AG.aPlayers.playerCurrent);//~vabgR~
            if (Dump.Y) Dump.println("Accounts.finalGameTest currentAccountsByESWN="+Arrays.toString(currentAccountsByESWN));//~9923I~//~0222R~
	        enableDiceRelative(starter);                           //~9922R~//~9923M~
    	AG.aRoundStat.newGame(false/*Psw1st*/,TestOption.finalGameCtrSet,TestOption.finalGameCtrGame,0/*dupctr*/);//~va60R~
        if (Dump.Y) Dump.println("Accounts.finalGameTest exit");   //~va60I~
    }                                                              //~9526I~
    //**************************************************           //~9519I~
    private void gameOver()                                        //~9519I~
    {                                                              //~9519I~
        if (Dump.Y) Dump.println("Accounts.gameOver");             //~9519I~
    	Arrays.fill(ctrContinuedGain,0);                           //~9519I~
    }                                                              //~9519I~
    //**************************************************           //~9519I~
    //*callback from Status.endGame                                //~9519I~
    //**************************************************           //~9519I~
    public void setCtrContinuedGain(boolean PswGameover,boolean PswDrawn,boolean PswContinue)//~9519I~
    {                                                              //~9519I~
        if (Dump.Y) Dump.println("Accounts.setCtrContinuedGain swGameover="+PswGameover+",swDrawn="+PswDrawn+",swContinue="+PswContinue+",ctrContinuedGain="+Arrays.toString(ctrContinuedGain));//~9519I~
        if (PswGameover)                                           //~9519I~
	    	Arrays.fill(ctrContinuedGain,0);                       //~9519I~
        else                                                       //~9519I~
        if (PswDrawn)                                              //~9519I~
        {                                                          //~9520I~
        	boolean swMangan=false;
        	boolean[] sws=AG.aComplete.swsMangan;//~9520I~
        	if (RuleSettingYaku.isDrawnManganAsRon() && sws!=null)//~9520I~
            {
                for (int eswn=0;eswn<PLAYERS;eswn++)               //~9520I~
                {                                                  //~9520I~
                	if (sws[eswn])                           //~9520I~
                    	swMangan=true;                             //~9520I~
                }                                                  //~9520I~
		        if (Dump.Y) Dump.println("Accounts.setCtrContinuedGain swsMangan="+Arrays.toString(sws));//~9520I~
                if (swMangan)                                      //~9520I~
                {                                                  //~9520I~
	                for (int pos=0;pos<PLAYERS;pos++)	//by pos   //~9520I~
    	            {                                              //~9520I~
                    	int eswn=getCurrentEswnByPosition(pos);    //~9520I~
				        if (Dump.Y) Dump.println("Accounts.setCtrContinuedGain pos="+pos+",eswn="+eswn);//~9520I~
        	        	if (sws[eswn])                       //~9520I~
	                    	ctrContinuedGain[pos]++;                //~9520I~
                    	else                                       //~9520I~
	                    	ctrContinuedGain[pos]=0;                //~9520I~
            	    }                                              //~9520I~
                }                                                  //~9520I~
            }                                                      //~9520I~
            if (!swMangan)                                         //~9520I~
		    	Arrays.fill(ctrContinuedGain,0);                       //~9519I~//~9520R~
        }                                                          //~9520I~
        else                                                       //~9519I~
        {                                                          //~9519I~
            Complete.Status[] ss=AG.aComplete.sortedStatusS;         //~9519I~
            if (ss!=null)                                          //~9519I~
            {                                                      //~9519I~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9519I~
                {                                                  //~9519I~
                	boolean swGain=false;                          //~9519I~
	            	for (Complete.Status s:ss)                     //~9519I~
    	            {                                              //~9519I~
                		int eswn=s.completeEswn;                   //~9519I~
                    	int pos=currentEswnToPosition(eswn);       //~9519I~
				        if (Dump.Y) Dump.println("Accounts.setCtrContinuedGain ii="+ii+",completeEswn="+eswn+",pos="+pos);//~9519R~
			    		if (!s.isValidCompleteion())               //~9519I~
                        	continue;                              //~9519I~
                        if (ii==pos)                               //~9519I~
                        {                                          //~9519I~
                            swGain=true;                           //~9519I~
                            break;                                 //~9519I~
                        }                                          //~9519I~
                    }                                              //~9519I~
                    if (swGain)                                    //~9519I~
	                    ctrContinuedGain[ii]++;                    //~9519I~
                    else                                           //~9519I~
	                    ctrContinuedGain[ii]=0;                    //~9519I~
			        if (Dump.Y) Dump.println("Accounts.setCtrContinuedGain swGain="+swGain+",ctr after="+ctrContinuedGain[ii]);//~9519R~
                }                                                  //~9519I~
            }                                                      //~9519I~
        }                                                          //~9519I~
        AG.aStarter.showCtrContinuedGain(ctrContinuedGain);        //~9519I~
        if (Dump.Y) Dump.println("Accounts.setCtrContinuedGain ctrContinuedGain="+Arrays.toString(ctrContinuedGain));//~9519I~
    }                                                              //~9519I~
    //**************************************************           //~9503I~
    //*from ACATouch.showReadyToStartNextGame(nextGame,resetGame,nextGameByLastGame)//~9818R~
    //**************************************************           //~9503I~
    public  void startNextGame()                                   //~9503I~
    {                                                              //~9503I~
        if (Dump.Y) Dump.println("Accounts.startNextGame");             //~9822R~//~9830R~
//      AG.aNamePlate.showScore();                                 //~9320I~//~9503R~
//      if (AG.aStatus.ctrSuspendRequestOld!=0)                    //~9818M~//~9822R~
//      	suspendGame();                                         //~9818M~//~9822R~
//      else                                                       //~9818M~//~9822R~
	    	prepareNextGame();                                     //~9818I~
//        Complete.newInstance();                                    //~9320I~//~9502R~//~9503I~//~9818R~
//        AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9318I~//~9502R~//~9503I~//~9818R~
////      UserAction.showInfoAll(0/*opt*/,Status.getStringGameSeq());//~9318I~//~9502R~//~9503R~//~9818R~
//        int dealerPos=getCurrentStarter();                         //~9503I~//~9818R~
//        if (Dump.Y) Dump.println("Accounts.nextGame current dealerPos="+dealerPos);//~9503I~//~9818R~
//        enableDiceRelative(dealerPos);                             //~9503I~//~9818R~
////      AG.aDiceBox.setWaitingDice(dealerPos,true/*set starterID*/);   //waiting lamp//~v@@@I~//~9514R~//~9818R~
//        AG.aDiceBox.setLightStarter(dealerPos,true/*resetPrev*/);   //waiting lamp//~9514R~//~9604R~//~9818R~
//        acaction.setWaitStatus(GCM_STARTGAME_NEXT);                //~9503I~//~9818R~
    }                                                              //~9503I~
    //**************************************************           //~9818I~
    private void prepareNextGame()                                 //~9818R~
    {                                                              //~9818I~
        if (Dump.Y) Dump.println("Accounts.prepareNextGame");      //~va6gI~
        Complete.newInstance();                                    //~9818I~
        AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9818I~
        int dealerPos=getCurrentStarter();                         //~9818I~
        if (Dump.Y) Dump.println("Accounts.prepareNextGame current dealerPos="+dealerPos);//~9818I~//~9830R~
        enableDiceRelative(dealerPos);                             //~9818I~
        AG.aDiceBox.setLightStarter(dealerPos,true/*resetPrev*/);   //waiting lamp//~9818I~
        acaction.setWaitStatus(GCM_STARTGAME_NEXT);                //~9818I~
    }                                                              //~9818I~
    //**************************************************           //~9818I~
    private boolean suspendGame(boolean PswServer,int PendgameType,int PnextgameType)                                     //~9818I~//~9822R~//~9823R~//~0304R~
    {                                                              //~9818I~
        if (Dump.Y) Dump.println("Accounts.suspendgame isServer="+PswServer+",endgameType="+PendgameType+",nextgametype="+PnextgameType);//~0304R~
        boolean rc=false;                                          //~9822I~
//      suspendEndgameType=PendgameType; suspendNextgameType=PnextgameType;//~9823I~//~9830R~
//      if (AG.aStatus.ctrSuspendRequest!=0)                       //~9822I~//~0304R~
        if (AG.aStatus.isSuspendRequested())                       //~0304I~
        {                                                          //~9822I~
        	rc=true;                                               //~9822I~
			if (isServer())                                        //~9822R~
	        	SuspendDlg.newInstance().show();                           //~9818R~//~9822R~//~9823R~
        }                                                          //~9822I~
        if (Dump.Y) Dump.println("Accounts.suspendgame rc="+rc);   //~9822I~
        return rc;                                                 //~9822I~
    }                                                              //~9818I~
//******************************************************************************//~v@@@I~
	public String[] getAccountNames()                              //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.getAccountNames");      //~v@@@I~
        if (accountNames==null)                                    //~v@@@I~
        {                                                          //~v@@@I~
        	accountNames=new String[PLAYERS];                      //~v@@@I~
            for (int ii=0;ii<PLAYERS;ii++)                         //~v@@@I~
            	accountNames[ii]=accounts[ii].name;                //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.getAccountNames="+Arrays.toString(accountNames));//~9416I~
        return accountNames;                                       //~v@@@I~
    }                                                              //~v@@@I~
//******************************************************************************//~9416I~
//*accountNames by startingEswn                                    //~9416I~
//******************************************************************************//~9416I~
	public String[] getAccountNamesByPosition()                    //~9416R~
    {                                                              //~9416I~
        if (accountNamesByPostion==null)                           //~9416R~
        {                                                          //~9416I~
	        accountNamesByPostion=setAccountNamesByPosition();     //~0305I~
        }                                                          //~9416I~
        if (Dump.Y) Dump.println("Accounts.getAccountNamesByStartingEswn="+Arrays.toString(accountNamesByPostion));//~9416R~
        return accountNamesByPostion;                              //~9416R~
    }                                                              //~9416I~
	private String[] setAccountNamesByPosition()                   //~0305I~
    {                                                              //~0305I~
        accountNamesByPostion=new String[PLAYERS];                 //~0305I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~0305I~
            accountNamesByPostion[ii]=accountsByESWN[ii].name;     //~0305I~
        if (Dump.Y) Dump.println("Accounts.setAccountNamesByStartingEswn="+Arrays.toString(accountNamesByPostion));//~0305I~
        return accountNamesByPostion;                              //~0305I~
    }                                                              //~0305I~
//******************************************************************************//~v@@5I~
	public String getAccountName(int PidxMember)                   //~v@@5I~
    {                                                              //~v@@5I~
        String s=accounts[PidxMember].name;                        //~v@@5I~
        if (Dump.Y) Dump.println("Accounts.getAccountName idx="+PidxMember+",name="+s);//~v@@5I~
        return s;                                                  //~v@@5I~
    }                                                              //~v@@5I~
//******************************************************************************//~v@@@I~
	public void propagate(int Pactionid,int Pplayer)               //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.propagate actionid="+Pactionid+",player="+Pplayer+",server="+idxServer);//~v@@@R~
        if (Pplayer==0)	//your action                              //~v@@@I~
        	if (idxServer!=0)	//your are not server                  //~v@@@I~
        	{                                                      //~v@@@I~
        		accountNames=new String[PLAYERS];                   //~v@@@I~
        	}                                                      //~v@@@I~
    }                                                              //~v@@@I~
	//******************************************************************************//~v@@@I~
	//*under Server on BTIOThread or GameViewHandler thread        //~v@@@R~
	//******************************************************************************//~v@@@I~
    public void setupEndAll()                                      //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.setupEndAll");          //~v@@@I~
//      UView.showToast(Utils.getStr(R.string.Info_AllMemberSetupEnd));//~v@@@I~//~0123R~
        MainView.drawMsg(Utils.getStr(R.string.Info_AllMemberSetupEnd));//~0123I~
        if (AG.resumeHD!=null)                                     //~9901I~
        {                                                          //~9901I~
	        HistoryData hd=AG.resumeHD;                            //~9901I~
	        AG.resumeHD=null;                                      //~9901I~
        	resumeGame(true/*swServer*/,hd);                                        //~9901I~//~9902R~
        	GameViewHandler.sendMsg(GCM_FIRSTDICE_SETUP_RESUME);   //~9902I~
            return;                                                //~9902I~
        }                                                          //~9901I~
//      GameViewHandler.sendMsg(GCM_TEMPSTARTER,"");               //~v@@@R~
        GameViewHandler.sendMsg(GCM_FIRSTDICE_SETUP);              //~v@@@R~
    }                                                              //~v@@@I~
	//******************************************************************************//~9B21I~
    public void endGame()                                          //~9B21I~
    {                                                              //~9B21I~
	    endGame(false);                                             //~9B21I~
    }                                                              //~9B21I~
	//******************************************************************************//~v@@@I~
//  public void endGame()                                          //~v@@@I~//~9B21R~
    public void endGame(boolean PswReturn)                         //~9B21I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.endGame swReturn="+PswReturn);              //~v@@@I~//~9B21R~
//      BTMulti.endGame(idxLocal,false/*swReceived*/);                                 //~v@@@R~//~9B19R~//~9B20R~
//      BTMulti.endGame(idxServer,false/*swReceived*/,PswReturn);            //~9B20I~//~9B21R~//~9B22R~
        BTMulti.endGame(idxLocal,false/*swReceived*/,PswReturn); //idx is to reset MS_SETUPEND//~9B22I~
        if (AG.aUADelayed!=null)                                      //~v@@5I~
	        AG.aUADelayed.endGame();                                 //~v@@5I~
                                                                   //~v@@5I~
    }                                                              //~v@@@I~
////******************************************************************************//~v@@@R~
//    public  void setupEndAllClient()                             //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Accounts.setupEndClient");     //~v@@@R~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~v@@@R~
//            if (accounts[ii].type==AT_REMOTECLIENT)              //~v@@@R~
//                accounts[ii].setupEnd();                         //~v@@@R~
//    }                                                            //~v@@@R~
//******************************************************************************//~v@@@I~
	private void setWaitingResponse()                              //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.setWaitingResponse before flag="+flag);//~v@@@R~
        flag|=AF_WAITINGRESPONSE;                                   //~v@@@I~
    }                                                              //~v@@@I~
//******************************************************************************//~v@@@I~
	private boolean resetWaitingResponse()                         //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.resetWaitingResponse before flag="+flag);//~v@@@I~
        int ctr=0;                                                 //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        	if ((accounts[ii].flag & AF_WAITINGRESPONSE)!=0)       //~v@@@I~
            	ctr++;                                             //~v@@@I~
        if (ctr==0)                                                //~v@@@I~
	        flag&=~AF_WAITINGRESPONSE;                             //~v@@@I~
        return ctr==0;	//all received                             //~v@@@I~
    }                                                              //~v@@@I~
//******************************************************************************//~v@@@I~
	public boolean receivedResponse(int PidxMember)                //~v@@@R~
    {                                                              //~v@@@I~
        Account a=AG.aBTMulti.BTGroup.MD[PidxMember].account;              //~v@@@R~
        a.resetWaitingResponse();                                   //~v@@@I~
        boolean rc=resetWaitingResponse();	//all responsed                //~v@@@R~
        if (Dump.Y) Dump.println("Accounts.receivedResponse idxMember="+PidxMember+",rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//******************************************************************************//~v@@@I~
	public int sendToClient(boolean PswRobot,int Pmsgid,int PintData)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.sendToClient msgid="+Pmsgid+",intData="+PintData);//~v@@@I~
        String s=Integer.toString(PintData);                       //~v@@@I~
		return sendToClient(PswRobot,-1/*skip this*/,Pmsgid,s);    //~v@@@R~
    }                                                              //~v@@@I~
//******************************************************************************//~v@@@I~
	public int sendToClient(boolean PswRobot,int Pskip,int PmsgID,int PintData)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.sendToClient msgid="+PmsgID+",intData="+PintData);//~v@@@I~
        String s=Integer.toString(PintData);                       //~v@@@I~
		return sendToClient(PswRobot,Pskip,PmsgID,s);              //~v@@@R~
    }                                                              //~v@@@I~
//******************************************************************************//~v@@@I~
//*on Server                                                       //~0226I~
//******************************************************************************//~0226I~
	public int sendToClient(boolean PswRobot,int Pskip,int PmsgID,String PmsgData)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.sendToClient swRobot="+PswRobot+",skip="+Pskip+",msgid="+PmsgID+",StrData="+PmsgData);//~v@@@R~
        int sendctr=0;                                             //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@R~
        {                                                          //~v@@@R~
            Account a=accounts[ii];                                //~v@@@I~
        	if (Dump.Y) Dump.println("Accounts.sendToClient ii="+ii+",account name="+a.name+",eswn="+a.ESWN+",thread="+Utils.toString(a.getThread())+",type="+a.type);//~9809I~//~9A24R~
         	if (a.type==AT_REMOTECLIENT && ii!=Pskip)              //~v@@@R~
            {                                                      //~v@@@I~
//          	BTIOThread.sendMsg(a.getThread(),true/*appmsg*/,PmsgID,PmsgData);//~v@@@I~//~9A24R~//~9A27R~
            	BTIOThread.sendMsg(a.getIdxMembers(),true/*appmsg*/,PmsgID,PmsgData);//~9A27I~
                a.setWaitingResponse();                            //~v@@@I~
                setWaitingResponse();                              //~v@@@I~
                sendctr++;                                         //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
         	if (a.type==AT_DUMMY)                                  //~v@@@I~
            {                                                      //~v@@@I~
            	if (PswRobot)                                      //~v@@@I~
                {                                                  //~v@@@I~
                	//TODO                                         //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@R~
        if (Dump.Y) Dump.println("Accounts.sendToClient sendctr="+sendctr);//~v@@@I~
        return sendctr;                                            //~v@@@I~
    }                                                              //~v@@@I~
//******************************************************************************//~9430I~
	public int sendToClientSkipByEswn(boolean PswRobot,int PskipEswn,int PmsgID,String PmsgData)//~9430I~
    {                                                              //~9430I~
        int idxAccount=currentEswnToMember(PskipEswn);              //~9430I~
        if (Dump.Y) Dump.println("Accounts.sendToClient swRobot="+PswRobot+",skipEswn="+PskipEswn+",idxAccount="+idxAccount);//~9430I~
		int rc=sendToClient(PswRobot,idxAccount,PmsgID,PmsgData);//~9430I~
        if (Dump.Y) Dump.println("Accounts.sendToClientSkipByEswn rc="+rc);//~9430I~
        return rc;                                                 //~9430I~
    }                                                              //~9430I~
////******************************************************************************//~9404R~
//    public int sendToClientProp(int PmsgID,String Phdr,Prop Pprop)//~9404R~
//    {                                                            //~9404R~
//        if (Dump.Y) Dump.println("Accounts.sendToClientProp hdr="+Phdr);//~9404R~
//        int sendctr=0;                                           //~9404R~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~9404R~
//        {                                                        //~9404R~
//            Account a=accounts[ii];                              //~9404R~
//            if (a.type==AT_REMOTECLIENT)                         //~9404R~
//            {                                                    //~9404R~
//                BTIOThread.sendMsgProp(a.getThread(),PmsgID,Phdr,Pprop);//~9404R~//~9A24R~
//                a.setWaitingResponse();                          //~9404R~
//                setWaitingResponse();                            //~9404R~
//                sendctr++;                                       //~9404R~
//            }                                                    //~9404R~
//        }                                                        //~9404R~
//        if (Dump.Y) Dump.println("Accounts.sendToClient sendctr="+sendctr);//~9404R~
//        return sendctr;                                          //~9404R~
//    }                                                            //~9404R~
//******************************************************************************//~v@@5I~
	public int sendToAll(int PmsgID,String PmsgData)               //~v@@5R~
    {                                                              //~v@@5I~
        if (Dump.Y) Dump.println("Accounts.sendToAll msgid="+PmsgID+",StrData="+PmsgData);//~v@@5R~
        int sendctr=1;
        if (isServer())                                            //~v@@5I~
			sendctr=sendToClient(false/*PswRobot*/,-1/*Pskip*/,PmsgID,PmsgData);//~v@@5I~
        else                                                       //~v@@5I~
			sendToServer(PmsgID,PmsgData);                         //~v@@5I~
        return sendctr;                                            //~v@@5I~
    }                                                              //~v@@5I~
//******************************************************************************//~v@@@I~
	public boolean sendToClientAction(boolean PswRobot,int PidxAccount,int PmsgID,int PactionID,String PmsgData)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.sendToClientAction swRobot="+PswRobot+",idxAccount="+PidxAccount+",msgid="+PmsgID+",actionid="+PactionID+",Data="+PmsgData);//~v@@@R~
        boolean rc=false;                                          //~v@@@R~
        Account a=accounts[PidxAccount];                            //~v@@@I~
        if (a.type==AT_DUMMY)                                      //~v@@@I~
        {                                                          //~v@@@I~
        	if (PswRobot)                                          //~v@@@I~
            {                                                      //~v@@@I~
        		if (Dump.Y) Dump.println("Accounts.sendToClientAction to Robot data="+PmsgData);//~v@@@I~
		        rc=accounts[PidxAccount].robot.action(PactionID,PmsgData);//~v@@@R~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
        		if (Dump.Y) Dump.println("Accounts.sendToClientAction skipped by PswRobot");//~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	String data=PactionID+MSG_SEP+PmsgData;                //~v@@@I~
//          BTIOThread.sendMsg(a.getThread(),true/*appmsg*/,PmsgID,data);//~v@@@R~//~9A24R~//~9A27R~
            BTIOThread.sendMsg(a.getIdxMembers(),true/*appmsg*/,PmsgID,data);//~9A27I~
            a.setWaitingResponse();                                //~v@@@I~
            rc=true;                                               //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.sendToClientAction rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@R~
    }                                                              //~v@@@I~
//******************************************************************************//~v@@@I~
	public void sendToServer(int PmsgID)                           //~v@@@R~
    {                                                              //~v@@@I~
		sendToServer(PmsgID,"");                                   //~v@@@I~
    }                                                              //~v@@@I~
//******************************************************************************//~v@@@I~
//  public void sendToServer(int PmsgID,int Pintdata)              //~v@@@I~//~0110R~
    public boolean sendToServer(int PmsgID,int Pintdata)           //~0110I~
    {                                                              //~v@@@I~
		return sendToServer(PmsgID,Integer.toString(Pintdata));           //~v@@@I~//~0110R~
    }                                                              //~v@@@I~
//******************************************************************************//~v@@@I~
//  public void sendToServer(int PmsgID,String PmsgData)           //~v@@@I~//~0110R~
    public boolean sendToServer(int PmsgID,String PmsgData)        //~0110I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.sendToServer msgid="+PmsgID+",data="+PmsgData);//~v@@@I~
        boolean rc=false;                                          //~0110I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
            Account a=accounts[ii];                                //~v@@@I~
         	if (a.type==AT_REMOTESERVER)                           //~v@@@I~
            {                                                      //~v@@@I~
		        if (Dump.Y) Dump.println("Accounts.sendToServer account ii="+ii+",name="+a.name+",thread="+Utils.toString(a.getThread()));//~9808I~//~9A24R~
//            	BTIOThread.sendMsg(a.getThread(),true/*appmsg*/,PmsgID,PmsgData);//~v@@@I~//~9A24R~//~9A27R~
//            	BTIOThread.sendMsg(a.getIdxMembers(),true/*appmsg*/,PmsgID,PmsgData);//~9A27I~//~0110R~
              	rc=BTIOThread.sendMsg(a.getIdxMembers(),true/*appmsg*/,PmsgID,PmsgData);//~0110I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                         //~v@@@I~//~0110R~
        if (Dump.Y) Dump.println("Accounts.sendToServer rc="+rc);  //~0110I~
    	return rc;                                                 //~0110I~
    }                                                              //~v@@@I~
////******************************************************************************//~v@@@I~//~0220R~
//    public void sendToServerDelayedResponse(int PmsgID)            //~v@@@I~//~0220R~
//    {                                                              //~v@@@I~//~0220R~
//        if (Dump.Y) Dump.println("Accounts.sendToServerDelayedResponse msgid="+PmsgID);//~v@@@I~//~0220R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~//~0220R~
//        {                                                          //~v@@@I~//~0220R~
//            Account a=accounts[ii];                                //~v@@@I~//~0220R~
//            if (a.type==AT_REMOTESERVER)                           //~v@@@I~//~0220R~
//            {                                                      //~v@@@I~//~0220R~
////              BTIOThread.sendMsgDelayedAppResponse(a.getThread(),PmsgID,"");//~v@@@I~//~9A24R~//~9A27R~//~0220R~
//                BTIOThread.sendMsgDelayedAppResponse(a.getIdxMembers(),PmsgID,"");//~9A27I~//~0220R~
//            }                                                      //~v@@@I~//~0220R~
//         }                                                         //~v@@@I~//~0220R~
//    }                                                              //~v@@@I~//~0220R~
//******************************************************************************//~v@@@I~
	public void sendToTheClient(int Pplayer,int PmsgID,String Pmsg)//~v@@@I~
    {                                                              //~v@@@I~
		int idx=playerToMember(Pplayer);                            //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.sendToTheClient player="+Pplayer+",idxAccount="+idx+",msg="+Pmsg);//~v@@@I~//~v@@5I~
        Account a=accounts[idx];                                   //~v@@@I~
//  	BTIOThread.sendMsg(a.getThread(),true/*Pswapp*/,PmsgID,Pmsg);//~1AecR~//~v@@@I~//~9A24R~//~9A27R~
    	BTIOThread.sendMsg(a.getIdxMembers(),true/*Pswapp*/,PmsgID,Pmsg);//~9A27I~
//      BTIOThread.sendMsgDelayedAppResponse(a.getThread(),PmsgID,"");//~v@@@I~//~9703R~//~9A24R~//~9A27R~
//      BTIOThread.sendMsgDelayedAppResponse(a.getIdxMembers(),PmsgID,"");//~9A27I~//~0220R~
    }                                                              //~v@@@I~
//******************************************************************************//~9430I~
	public void sendToTheClientDealer(int PmsgID,String Pmsg)      //~9430I~
    {                                                              //~9430I~
    	int player=AG.aAccounts.getCurrentDealerReal();            //~v@@7I~//~9430I~
        if (Dump.Y) Dump.println("Accounts.sendToTheClientDealer player="+player+",msgid="+PmsgID+",msg="+Pmsg);//~9430I~
		sendToTheClient(player,PmsgID,Pmsg);                        //~9430I~
    }                                                              //~9430I~
//******************************************************************************//~v@@@I~
	private int getMemberCtr()                                     //~v@@@R~
    {                                                              //~v@@@I~
        int ctr=0;                                                 //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
            Account a=accounts[ii];                                //~v@@@I~
	        if (Dump.Y) Dump.println("Accounts.getMemberCtrtype is Dummy? account["+ii+"]="+a.toString());//~9B04I~
         	if (a.type!=AT_DUMMY)                                  //~v@@@I~
                ctr++;                                             //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.getMemberCtr ctr="+ctr);
        return ctr;//~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************************           //~v@@@I~//~9511R~
//    private void reachDone(int Pplayer)                            //~v@@@I~//~9511R~
//    {                                                              //~v@@@I~//~9511R~
//        if (Dump.Y) Dump.println("Accounts.reachDone player="+Pplayer);//~9511R~
//        accounts[Pplayer].reachDone();                             //~v@@@I~//~9511R~
//        AG.aPointStick.reachDone(Pplayer);                         //~v@@@I~//~9511R~
//        accounts[Pplayer].reachShown();                            //~v@@@I~//~9511R~
//    }                                                              //~v@@@I~//~9511R~
    //**************************************************           //~9511I~
    public void reachDonePay(int Pplayer)                          //~9511R~
    {                                                              //~9511I~
        if (Dump.Y) Dump.println("Accounts.reachDonePay player="+Pplayer);//~9511I~
        int pos=playerToPosition(Pplayer);                         //~9511I~
        score[pos]-=POINT_REACH;                                   //~9511I~
        if (Dump.Y) Dump.println("Accounts.reachDonePay player="+Pplayer+",pos="+pos+",score="+Arrays.toString(score));//~9511I~//~9904R~
        AG.aNamePlate.showScore();                                 //~9511I~
    }                                                              //~9511I~
    //**************************************************           //~9511I~
    public void resetReachDonePay(int Pplayer)                     //~9511I~
    {                                                              //~9511I~
        int pos=playerToPosition(Pplayer);                         //~9511R~
        score[pos]+=POINT_REACH;                                   //~9511I~
        if (Dump.Y) Dump.println("Accounts.resetReachDonePay lastReach="+Pplayer+",pos="+pos+",score="+Arrays.toString(score));//~9511R~
//      AG.aNamePlate.showScore(); //showScore will be called later at endgame/nextgame from scoreDlg                                 //~9511I~//~va72R~
    }                                                              //~9511I~
    //**************************************************           //~9704I~
    //*at ResetGame by Chombo etc                                  //~9704I~
    //**************************************************           //~9704I~
    public void resetReachAll(int Pplayer)                         //~9704I~
    {                                                              //~9704I~
        int pos=playerToPosition(Pplayer);                         //~9704I~
        score[pos]+=POINT_REACH;                                   //~9704I~
        if (Dump.Y) Dump.println("Accounts.resetReachAll player="+Pplayer+",pos="+pos+",score="+Arrays.toString(score));//~9704I~//~9904R~
    }                                                              //~9704I~
//    //**************************************************         //~9704R~
//    //*when ResetGame back point reach to score                  //~9704R~
//    //**************************************************         //~9704R~
//    public void resetReach(boolean[] PswsReach)                  //~9704R~
//    {                                                            //~9704R~
//        if (Dump.Y) Dump.println("Accounts.resetReach swsReach="+Arrays.toString(PswsReach)+",oldscore="+Arrays.toString(score));//~9704R~
//        for (int eswn=0;eswn<PLAYERS;eswn++)                     //~9704R~
//        {                                                        //~9704R~
//            if (!PswsReach[eswn])                                //~9704R~
//                continue;                                        //~9704R~
//            int pos=currentEswnToPosition(eswn);                 //~9704R~
//            if (Dump.Y) Dump.println("Accounts.resetReach reach eswn="+eswn+",pos="+pos);//~9704R~
//            score[pos]+=POINT_REACH;                             //~9704R~
//        }                                                        //~9704R~
//        if (Dump.Y) Dump.println("Accounts.resetReach after score "+Arrays.toString(score));//~9704R~
//    }                                                            //~9704R~
    //**************************************************           //~v@@@I~
    public  void tempStarter_Setup(int Pstarter)                   //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Accounts.tempStarter starter="+Pstarter+",status="+Status.getGameStatus());//~v@@@R~
        tempStarter=Pstarter;                                      //~v@@@I~
        Status.setGameStatus(GS_POSITIONING);                      //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************************           //~9312I~
    public  void setInitialScore(int Pscore)                       //~9312I~
    {                                                              //~9312I~
        if (Dump.Y) Dump.println("Accounts.setInitialScore score="+Pscore);//~9312I~
        Arrays.fill(score,Pscore);                                 //~9312I~
        topPrize=(RuleSetting.getDebt()-Pscore)*PLAYERS;           //~9428I~
        if ((TestOption.option & TestOption.TO_INITIALSCORE)!=0)              //~9423I~
        {                                                          //~9424I~
        	score=RuleSetting.getInitialScoreTest();               //~9425R~
            topPrize=RuleSetting.getDebt()*PLAYERS-(score[0]+score[1]+score[2]+score[3]);//~9428I~
        }                                                          //~9424I~
        AG.aNamePlate.showScore();                                 //~9318I~
    }                                                              //~9312I~
    //**************************************************           //~9901I~
    //*from Status.setGameStatusNewSetResume                       //~9901I~
    //**************************************************           //~9901I~
    public  void setInitialScoreResume(int Pscore,int Pscores[])    //~9901I~
    {                                                              //~9901I~
        if (Dump.Y) Dump.println("Accounts.setInitialScoreResume score="+Pscore+",scores="+Arrays.toString(Pscores));//~9901I~
//      Arrays.fill(score,Pscore);                                 //~9901I~
		System.arraycopy(Pscores,0/*srcpos*/,score,0/*tgtpos*/,PLAYERS);//~9901I~
        topPrize=(RuleSetting.getDebt()-Pscore)*PLAYERS;           //~9901I~
        AG.aNamePlate.showScore();                                 //~9901I~
    }                                                              //~9901I~
    //**************************************************           //~9530I~
	public void setCutPlayer(int Pplayer,int Peswn)                //~9530I~
    {                                                              //~9530I~
        if (Dump.Y) Dump.println("Accounts.setCutPlayer Rule.isSpritPos="+RuleSetting.isSpritPos()+",Pplayer="+Pplayer+",Peswn="+Peswn);//~9530I~//~9607R~
        if (RuleSetting.isSpritPos())                               //~9530I~
        {                                                          //~9531I~
	        cutPlayer=Pplayer; cutEswn=Peswn;                      //~9530I~
        }                                                          //~9531I~
        if (Dump.Y) Dump.println("Accounts.setCutPlayer cutPlayer="+cutPlayer+",curEswn="+cutEswn);//~9530I~//~9607R~
    }                                                              //~9530I~
    //**************************************************           //~9530I~
	public int getCutEswn()                                        //~9530I~
    {                                                              //~9530I~
        if (Dump.Y) Dump.println("Accounts.getCutEswn rc="+cutEswn);//~9530I~
        return cutEswn;                                            //~9530I~
    }                                                              //~9530I~
    //**************************************************           //~9319I~
    public int getTopScore()                                       //~9319I~
    {                                                              //~9319I~
    	int topScore=0;                                            //~9319I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9319I~
        {                                                          //~9319I~
            if (topScore<score[ii])                                //~9319I~
            	topScore=score[ii];                                //~9319I~
        }                                                          //~9319I~
        if (Dump.Y) Dump.println("Accounts.getTopScore score="+topScore);//~9319I~
        return topScore;
    }                                                              //~9319I~
    //**************************************************           //~9312I~
    public  void updateScore(int[] Pammount/*eswnSeq*/)                       //~9312I~//~9318R~
    {                                                              //~9312I~
        if (Dump.Y) Dump.println("Accounts.updateScore old="+Arrays.toString(score)+",add(EswnSeq)="+Arrays.toString(Pammount));//~9312I~//~9316R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9316I~
        {                                                          //~9316I~
//          int idx=currentEswnToMember(ii);                       //~9316I~//~9416R~
            int idx=currentEswnToPosition(ii);                     //~9416I~
            score[idx]+=Pammount[ii];                               //~9316I~
        }                                                          //~9316I~
        if (Dump.Y) Dump.println("Accounts.updateScore newScore="+Arrays.toString(score));//~9316I~
    }                                                              //~9312I~
    //**************************************************           //~vah2I~
    public  void updateScore(int[] Pscore/*eswnSeq*/,int[] Pammount/*eswnSeq*/)//~vah2I~
    {                                                              //~vah2I~
        if (Dump.Y) Dump.println("Accounts.updateScore To Parm Score oldScore(PositionSeq)="+Arrays.toString(score)+",add(EswnSeq)="+Arrays.toString(Pammount));//~vah2I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~vah2I~
        {                                                          //~vah2I~
            int idx=currentEswnToPosition(ii);                     //~vah2I~
            Pscore[ii]=score[idx]+Pammount[ii];                    //~vah2I~
        }                                                          //~vah2I~
        if (Dump.Y) Dump.println("Accounts.updateScore To Parm Score newScore="+Arrays.toString(Pscore));//~vah2I~
    }                                                              //~vah2I~
    //**************************************************           //~9321I~
    public  void setScore(int[] Ptotal/*indexSeq*/)                 //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("Accounts.setScore new="+Arrays.toString(Ptotal)+",old="+Arrays.toString(score));//~9321I~
        System.arraycopy(Ptotal,0,score,0,PLAYERS);                //~9321I~
    }                                                              //~9321I~
    //**************************************************           //~9427I~
    public boolean isReachable(int Pplayer)                        //~9427I~
    {                                                              //~9427I~
    	boolean rc=true;                                           //~9427I~
    	int pos=playerToPosition(Pplayer);                         //~9427I~
        int remain=score[pos];                                     //~9427I~
        if (remain<=POINT_REACH)                                //~9427I~
        {                                                          //~9427I~
        	if (RuleSetting.isMinusStop())                         //~9427I~
            	if (remain<POINT_REACH || (remain==POINT_REACH && RuleSetting.isZeroStop()))//~9427I~
                	rc=false;                                      //~9427I~
        }                                                          //~9427I~
        if (Dump.Y) Dump.println("Accounts.isReachable rc="+rc+",Pplayer="+Pplayer+",position="+pos+",remain="+remain+",score="+Arrays.toString(score));//~9427I~
        return rc;                                                 //~9427I~
    }                                                              //~9427I~
    //**************************************************           //~9703I~
    public boolean isGameWithRobot()                               //~9703I~
    {                                                              //~9703I~
        boolean rc=activeMembers!=PLAYERS;                        //~9703I~
        if (Dump.Y) Dump.println("Accounts.isGameWithRobot rc="+rc+",activeMembers="+activeMembers);//~9703I~
        return rc;
    }                                                              //~9703I~
    //**************************************************           //~9501I~
    public boolean isGrillBird()                                   //~9501I~
    {                                                              //~9501I~
    	boolean swBird=RuleSetting.isGrillBird();                  //~9501I~
//      boolean rc=(swBird && activeMembers==PLAYERS);               //~9501I~//~va72R~
        boolean rc=(swBird && (activeMembers==PLAYERS||RuleSetting.isThinkRobot()));//~va72I~
        if ((TestOption.option & TestOption.TO_BIRD_WITH_ROBOT)!=0) rc=swBird;       //~9501I~
        if (Dump.Y) Dump.println("Accounts.isGrillBird rc="+rc+",swBird="+swBird+",isRobotExist="+(activeMembers!=PLAYERS));//~9501I~
        return rc;                                                 //~9501I~
    }                                                              //~9501I~
    //**************************************************           //~9501I~
    public boolean isGrillBird(int Pplayer)                        //~9501I~
    {                                                              //~9501I~
    	int idx=playerToMember(Pplayer);                           //~9501I~
    	boolean rc;                                                //~9501I~
//      if (!RuleSetting.isGrillBird())                                      //~9501I~//~va72R~
        if (!isGrillBird())                                        //~va72R~
        	rc=false;                                              //~9501I~
        else                                                       //~9501I~
    		rc=accounts[idx].isGrilled();                          //~9501R~
        if (Dump.Y) Dump.println("Accounts.isGrillBird player="+Pplayer+",rc="+rc);//~9501I~
        return rc;                                                 //~9501I~
    }                                                              //~9501I~
    //**************************************************           //~9B01I~
    //*make int from isGrilled and ctrContinedGain                 //~9B01I~
    //**************************************************           //~9B01I~
    public int getBirdAndCont()                                    //~9B01I~
    {                                                              //~9B01I~
    	int bird=0;               //idx seq                        //~9B01R~
//      if (RuleSetting.isGrillBird())                             //~9B01I~//~va72R~
        if (isGrillBird())                                         //~va72I~
        {                                                          //~9B01I~
        	for (int ii=0;ii<PLAYERS;ii++)                         //~9B01I~
            {                                                      //~9B01I~
    			if (accounts[ii].isGrilled())                      //~9B01R~
                {                                                  //~9B01I~
                	int pos=accounts[ii].ESWN;                     //~9B01I~
                    bird|=0x01<<pos;                               //~9B01R~
                }                                                  //~9B01I~
            }                                                      //~9B01I~
        }                                                          //~9B01I~
        int cont=0;                                                //~9B01I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9B01I~
        {                                                          //~9B01I~
            if (ctrContinuedGain[ii]!=0)	//pos seq              //~9B01I~
            {                                                      //~9B01I~
                cont=(ii<<8)|ctrContinuedGain[ii];                 //~9B01I~
                break;                                             //~9B01I~
            }                                                      //~9B01I~
        }                                                          //~9B01I~
        int rc=cont<<8 | bird;                                     //~9B01I~
        if (Dump.Y) Dump.println("Accounts.getBirdAndCont rc="+Integer.toHexString(rc)+",ctrContGain="+Arrays.toString(ctrContinuedGain));//~9B01I~
        return rc;                                                 //~9B01I~
    }                                                              //~9B01I~
    //**************************************************           //~9522I~
    public static Point getBirdAndCont(int PbirdAndCont,boolean[] PswsBird)//~9B01R~
    {                                                              //~9B01I~
    	int bird=PbirdAndCont & 0xff;                              //~9B01I~
    	int cont=PbirdAndCont >>8;                                 //~9B01I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9B01I~
        {                                                          //~9B01I~
            PswsBird[ii]=(bird & 0x01)!=0;	//idx seq                  //~9B01R~
            bird>>=1;                                              //~9B01I~
        }                                                          //~9B01I~
        int contpos=cont>>8;                                       //~9B01I~
        int contctr=cont & 0xff;                                   //~9B01I~
        Point p=new Point(contpos,contctr);                        //~9B01I~
        if (Dump.Y) Dump.println("Accounts.getBirdAndCont parm="+PbirdAndCont+",swsBird="+Arrays.toString(PswsBird)+",contPos="+contpos+",contctr="+contctr);//~9B01I~
        return p;                                                  //~9B01I~
    }                                                              //~9B01I~
    //**************************************************           //~9B01I~
    public void savePendingReach(boolean PswRon,boolean[] PswsReach)//~9522I~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("Accounts.savePendingReach swRon="+PswRon+",before ctrPendingReach="+Arrays.toString(intsCtrPendingReach));//~9522I~
        if (PswRon)                                                //~9522I~
        {                                                          //~va6gI~
		    Arrays.fill(intsCtrPendingReach,0);                    //~9522I~
            AG.aPlayers.clearReachAll();                           //~va6gI~
        }                                                          //~va6gI~
        else                                                       //~9522I~
        {                                                          //~9522I~
			if (PswsReach!=null)                                   //~9522I~
            {                                                      //~9522I~
		        if (Dump.Y) Dump.println("Accounts.savePendingReach swsReach="+Arrays.toString(PswsReach));//~9522I~//~9704R~
                for (int eswn=0;eswn<PLAYERS;eswn++)               //~9522I~
                {                                                  //~9522I~
                	if (!PswsReach[eswn])                          //~9522I~
                        continue;                                  //~9522I~
                    int pos=currentEswnToPosition(eswn);           //~9522I~
                    if (Dump.Y) Dump.println("Accounts.savePendingReach reach eswn="+eswn+",pos="+pos);//~9522I~//~9704R~
                    intsCtrPendingReach[pos]++;                    //~9522I~
                }                                                  //~9522I~
            }                                                      //~9522I~
        }                                                          //~9522I~
        if (Dump.Y) Dump.println("Accounts.savePendingReach after ctrPendingReach="+Arrays.toString(intsCtrPendingReach));//~9522I~
    }                                                              //~9522I~
	//******************************************************************************//~9901I~
    public  void resumeGame(boolean PswServer,HistoryData Phd)                       //~9901I~//~9902R~
    {                                                              //~9901I~
		if (Dump.Y) Dump.println("Accounts.resumeGame swServer="+PswServer+",hd="+Phd.toString());//~9901I~//~9902R~
        positionMoveResume(Phd);                                   //~9901I~
        positionMovedResume(Phd);                                  //~9901I~
        loadResumeProperties(Phd);                                 //~vae5R~
    }                                                              //~9901I~
	//******************************************************************************//~vae5R~
    private  void loadResumeProperties(HistoryData Phd)            //~vae5R~
    {                                                              //~vae5R~
		if (Dump.Y) Dump.println("Accounts.loadResumeProperties hd="+Phd.toString());//~vae5R~
        Prop p=Phd.getRuleProp();                                  //~vae5R~
        if (p==null)                                //already confirmed at ResumeDlg->HistoryData.getRuleProp//~vae5R~
        {                                                          //~vae5R~
        	if (Dump.Y) Dump.println("Accounts.loadResumeProperties canceled by prop get failed");//~vae5R~
            return;                                                //~vae5R~
        }                                                          //~vae5R~
    	AG.savePropForResume=AG.ruleProp;                          //~vae5R~
    	AG.ruleProp=p;                                             //~vae5R~
    }                                                              //~vae5R~
//******************************************************************************//~v@@@I~
//******************************************************************************//~v@@@I~
//******************************************************************************//~v@@@I~
    public class Account                                           //~v@@@R~
    {                                                              //~v@@@I~
        public int idxMembers;                                     //~v@@@R~
        public int status;                                         //~v@@@I~
        public int coins;                                          //~v@@@I~
        public int flag;                                           //~v@@@R~
        public int type;                                           //~v@@@I~
        public int ESWN;	//initial(after position moved) position on Server//~v@@@R~
    	public String name;                                        //~v@@@I~
//  	private BTIOThread thread;                                 //~v@@@R~//~9A24R~
    	public  Members.MemberData memberData;                     //~v@@@I~//~9A21R~
//  	private Robot robot;                                       //~v@@@R~//~va60R~
    	public  Robot robot;    //public for IT                    //~va60I~
        //*****************************************************    //~v@@@R~
        public Account(int PidxMembers)                            //~v@@@R~
        {                                                          //~v@@@R~
            idxMembers=PidxMembers;                                //~v@@@R~
//          name=Utils.getStr(R.string.YournameRobot)+PidxMembers; //~v@@@R~//~9605R~
//          name=Utils.getStr(R.string.YournameRobot)+(PLAYERS-PidxMembers);//~9605I~//~va68R~
            name=GConst.robotYourNameDefault[PidxMembers];         //~va68I~
        }                                                          //~v@@@R~
        //*****************************************************    //~9824I~
        private boolean setMember(Members.MemberData PmemberData)   //~v@@@I~//~9B11R~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("Account.setMember");         //~v@@@I~
        	boolean rc=true;                                           //~v@@@I~
        	type=AT_DUMMY;                                         //~v@@@R~
            if (PmemberData!=null)                                 //~v@@@R~
            {                                                      //~v@@@I~
            	memberData=PmemberData;                            //~v@@@I~
//            if (PmemberData.getThread()!=null)                   //~9B11R~
//            {                                                    //~9B11R~
//            if (!AG.swTrainingMode || memberData.getName().equals(DEVICENAME_TRAINING))//stat is cleard at Member.setTraingMode//~va9iR~
              {                                                    //~va9iI~
            	int stat=memberData.status;                        //~v@@@R~
                if ((stat & Members.MS_LOCAL)!=0)                          //~v@@@R~
                {                                                  //~v@@@I~
	                if ((stat & MS_SERVER)!=0)                     //~v@@@I~
                    	type=AT_LOCALSERVER;                       //~v@@@I~
                    else                                           //~v@@@I~
                    	type=AT_LOCALCLIENT;                       //~v@@@I~
                }                                                  //~v@@@I~
                else                                               //~v@@@I~
                {                                                  //~v@@@I~
	                if ((stat & MS_SERVER)!=0)                     //~v@@@I~
                    	type=AT_REMOTESERVER;                      //~v@@@I~
                    else                                           //~v@@@I~
	                if ((stat & Members.MS_REMOTECLIENT)!=0)               //~v@@@I~
                    	type=AT_REMOTECLIENT;                      //~v@@@I~
                }                                                  //~v@@@I~
                String nm=memberData.getYourName();                //~v@@@R~
                if (nm!=null)                                      //~v@@@I~
//                  name=memberData.getYourName();                 //~v@@@I~//~9924R~
                    name=nm;                                       //~9924I~
//              thread=(BTIOThread)(memberData.getThread());       //~v@@@I~//~9A24R~
	        	if (Dump.Y) Dump.println("Account.setMember type="+type+",devname="+memberData.getName()+",name="+name);//~v@@@I~//~9924R~
//            }                                                    //~9B11R~
              } //!trainingmode                                    //~va9iI~
            }                                                      //~v@@@I~
            if (type==AT_DUMMY)                                    //~v@@@I~
            {                                                      //~v@@@I~
                                                                   //~v@@@I~
//          	if ((TestOption.option & TestOption.TO_DUMMYACCOUNT)==0)	//not allow dummy account//~v@@@R~//~9607R~
            	if (!RuleSetting.isAllowRobot())	//not allow dummy account//~9607I~
                    rc=false;                                      //~v@@@R~
                else                                               //~v@@@I~
                {                                                  //~9630I~
		        	robot=Robot.createNewInstance(this);           //~v@@@R~
                    this.robot=robot;                              //~9630I~
//  	            name=Utils.getStr(R.string.YournameRobot)+(PLAYERS-idxMembers);	//reset when disconnected case//~9B30I~//~va68R~
    	            name=GConst.robotYourNameDefault[idxMembers];	//reset when disconnected case//~va68I~
                }                                                  //~9630I~
            }
	        if (Dump.Y) Dump.println("Account.setMember rc="+rc+",type="+type);//~v@@@R~
            return rc;//~v@@@I~
        }                                                          //~v@@@I~
        //*****************************************************    //~9A24I~
        public BTIOThread getThread()                              //~9A24I~
        {                                                          //~9A24I~
        	BTIOThread thread=(BTIOThread)(memberData.getThread());//~9A24I~
            if (Dump.Y) Dump.println("Accounts.getThread idx="+idxMembers+",thread="+Utils.toString(thread));//~9A24I~
		    return thread;
        }                                                          //~9A24I~
        //*****************************************************    //~9A27I~
        public int getIdxMembers()                                 //~9A27I~
        {                                                          //~9A27I~
            if (Dump.Y) Dump.println("Accounts.getIdxMembers idx="+idxMembers);//~9A27I~
		    return idxMembers;                                     //~9A27I~
        }                                                          //~9A27I~
//        //*****************************************************    //~v@@@I~//~9511R~
//        public void reachDone()                                    //~v@@@I~//~9511R~
//        {                                                          //~v@@@I~//~9511R~
//            status|=REACH_DONE;                                    //~v@@@I~//~9511R~
//        }                                                          //~v@@@I~//~9511R~
//        //*****************************************************    //~v@@@I~//~9511R~
//        public void reachShown()                                   //~v@@@I~//~9511R~
//        {                                                          //~v@@@I~//~9511R~
//            status|=REACH_SHOWN;                                   //~v@@@I~//~9511R~
//            coins-=COST_REACH;                                      //~v@@@I~//~9511R~
//        }                                                          //~v@@@I~//~9511R~
//        //*****************************************************  //~v@@@R~
//        public void setupEnd()                                   //~v@@@R~
//        {                                                        //~v@@@R~
//            flag|=AF_SETUPEND;                                   //~v@@@R~
//        }                                                        //~v@@@R~
        //*****************************************************    //~v@@@I~
        public void setWaitingResponse()                           //~v@@@I~
        {                                                          //~v@@@I~
        	flag|=AF_WAITINGRESPONSE;                              //~v@@@I~
        }                                                          //~v@@@I~
        //*****************************************************    //~v@@@I~
        public void resetWaitingResponse()                         //~v@@@I~
        {                                                          //~v@@@I~
        	flag&=~AF_WAITINGRESPONSE;                             //~v@@@I~
        }                                                          //~v@@@I~
        //*****************************************************    //~v@@@I~
        public boolean isDummy()                                   //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("Account.isDummy initialESWN="+ESWN+",idxMember="+idxMembers+",rc="+(type==AT_DUMMY));//~v@@@I~//~va60R~
        	return (type==AT_DUMMY);                               //~v@@@I~
        }                                                          //~v@@@I~
        //*****************************************************    //~9501I~
        public boolean isGrilled()                                 //~9501I~
        {                                                          //~9501I~
        	boolean rc=(flag & AF_ONCE_RON)==0;                    //~9501R~
	        if (Dump.Y) Dump.println("Account.isGrilled idx="+idxMembers+",rc="+rc);//~9501I~
        	return rc;                                             //~9501I~
        }                                                          //~9501I~
        //*****************************************************    //~9501I~
        public void setGrilled(boolean Psw)                        //~9501I~
        {                                                          //~9501I~
        	if (Psw)                                               //~9501I~
        		flag &= ~AF_ONCE_RON;                              //~9501I~
            else                                                   //~9501I~
        		flag |=  AF_ONCE_RON;                              //~9501I~
            AG.aStarter.drawBird(idxMembers,!Psw/*swReset*/);       //~9501I~
	        if (Dump.Y) Dump.println("Account.setGrilled idx="+idxMembers+",sw="+Psw+",flag="+flag);//~9501I~
        }                                                          //~9501I~
        //*****************************************************    //~9901I~
        public String toString()                                   //~9901I~
        {                                                          //~9901I~
        	return "["+name+",stat="+status+",idxMember="+idxMembers+",ESWN="+ESWN+",flag=x"+Integer.toHexString(flag)+",type="+type+",coins="+coins+",robot="+Utils.toString(robot)+",memberData="+Utils.toString(memberData);//~9901I~//~9A24R~
        }                                                          //~9901I~
	}//class Account                                               //~v@@@R~
}//class Accounts                                                 //~dataR~//~@@@@R~//~v@@@R~
