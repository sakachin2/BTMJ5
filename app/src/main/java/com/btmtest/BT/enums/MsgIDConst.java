//*CID://+var8R~:                             update#=  200;       //~var8R~
//****************************************************************************
//2022/09/24 var8 display profile icon                             //~var8I~
//@002:20181103 use enum
//****************************************************************************
package com.btmtest.BT.enums;                                      //~@002R~

//****************************************************************************
public class MsgIDConst
{
    //*****************************************************************
	public static final int MSGID_INTERNAL       =0;
	public static final int MSGID_CLOSE          =1;
    public static final int MSGID_NAME           =2;
    public static final int MSGID_NEWNAME        =3;
    public static final int MSGID_MEMBER_ADD     =4;
    public static final int MSGID_MEMBER_DELETE  =5;
    public static final int MSGID_QNAME          =6;    //received MSGQ_NAME with synchDate//~@002I~
    public static final int MSGID_IOERR          =7;    //IOThread exception//~@002I~
    public static final int MSGID_KEEPALIVE      =8;               //~@002I~
    public static final int MSGID_PROFILE        =9;               //~var8I~
    public static final int MSGID_APP            =100;

    public static final String MSG_SEP         =";";               //~@002R~
    public static final String MSG_SEPAPP      =":";  //separator in long app data//~@002R~
    public static final String MSG_SEPAPP2     =" ";  //separator in long app data//~@002R~
    public static final String MSG_SEPAPP3     =",";  //separator in long app data//~@002I~
    public static final String MSG_SEPAPP2RE   ="\\s+";  //separator in long app data//~@002R~
//  public static final String MSG_PARSEAPP    ="["+MSG_SEPAPP+"|"+MSG_SEPAPP2RE+"]";  //separator in long app data//~@002R~
//  public static final String MSG_PARSEAPP    ="["+MSG_SEPAPP+"|"+MSG_SEPAPP3+"|"+MSG_SEPAPP2RE+"]";  //separator in long app data//~@002R~
    public static final String MSG_PARSEAPP    =MSG_SEPAPP+"|"+MSG_SEPAPP3+"|"+MSG_SEPAPP2RE;  //separator in long app data//~@002I~
//  public static final String MSG_PARSEALL    ="["+MSG_SEP+"|"+MSG_SEPAPP+"|"+MSG_SEPAPP2RE+"]";  ////~@002R~
//  public static final String MSG_PARSEALL    ="["+MSG_SEP+"|"+MSG_SEPAPP+"|"+MSG_SEPAPP3+"|"+MSG_SEPAPP2RE+"]";  ////~@002R~
    public static final String MSG_PARSEALL    =MSG_SEP+"|"+MSG_SEPAPP+"|"+MSG_SEPAPP3+"|"+MSG_SEPAPP2RE;  ////~@002I~
                                                                   //~@002I~
    public static final String MSG_RESPID ="#";
    public static final String REQ        ="@@";
    public static final String RESP       ="#@";
    public static final String APP        ="!!";
    public static final String APPSEQNO   ="!@";                   //~@002I~

    public static final String MSG_CLOSE      ="close";
    public static final String MSGQ_CLOSE     =REQ +MSG_CLOSE;
    public static final String MSGR_CLOSE     =RESP+MSG_CLOSE;
    public static final String MSG_NAME       ="name";
    public static final String MSGQ_NAME      =REQ +MSG_NAME;
    public static final String MSGR_NAME      =RESP+MSG_NAME;
    public static final String MSG_NEWNAME    ="newname";
    public static final String MSGQ_NEWNAME   =REQ +MSG_NEWNAME;
    public static final String MSGR_NEWNAME   =RESP+MSG_NEWNAME;
    public static final String MSG_MEMBADD       ="add";
    public static final String MSGQ_MEMBER_ADD   =REQ +MSG_MEMBADD;
    public static final String MSGR_MEMBER_ADD   =RESP+MSG_MEMBADD;
    public static final String MSG_MEMB_DEL      ="del";
    public static final String MSGQ_MEMBER_DELETE=REQ +MSG_MEMB_DEL;
    public static final String MSGR_MEMBER_DELETE=RESP+MSG_MEMB_DEL;
    public static final String MSG_KEEPALIVE     ="keepalive";      //~@002I~
    public static final String MSGQ_KEEPALIVE    =REQ +MSG_KEEPALIVE;//~@002I~
    public static final String MSGR_KEEPALIVE    =RESP+MSG_KEEPALIVE;//~@002I~
//  public static final String MSG_PROFILE       ="Profile";       //+var8R~
//  public static final String MSGQ_PROFILE      =REQ +MSG_PROFILE;//+var8R~
//  public static final String MSGR_PROFILE      =RESP+MSG_PROFILE;//+var8R~
    public static final String MSG_APP        =APP;
    public static final String MSGQ_APP       =REQ +MSG_APP;
    public static final String MSGR_APP       =RESP+MSG_APP;
    public static final String MSGQ_APPSEQNO  =REQ +APPSEQNO;      //~@002I~
    public static final String MSGR_APPSEQNO  =RESP+APPSEQNO;      //~@002I~
}
