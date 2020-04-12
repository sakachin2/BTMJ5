//*CID://+@002R~:                             update#=  183;       //~@002R~
//****************************************************************************
//@002:20181103 use enum
//****************************************************************************
package com.btmtest.BT.enums;                                      //~@002R~

import static com.btmtest.BT.enums.MsgIDConst.*;                   //~@002R~

//****************************************************************************
public enum MsgID
{
	CLOSE          (MSGID_CLOSE,         MSGQ_CLOSE,         MSGR_CLOSE),//~@002R~
    NAME           (MSGID_NAME,          MSGQ_NAME,          MSGR_NAME),//~@002R~
    NEWNAME        (MSGID_NEWNAME,       MSGQ_NEWNAME,       MSGR_NEWNAME),//~@002R~
    MEMBER_ADD     (MSGID_MEMBER_ADD,    MSGQ_MEMBER_ADD,    MSGR_MEMBER_ADD),//~@002R~
    MEMBER_DELETE  (MSGID_MEMBER_DELETE, MSGQ_MEMBER_DELETE, MSGR_MEMBER_DELETE),//~@002R~
    KEEPALIVE      (MSGID_KEEPALIVE,     MSGQ_KEEPALIVE,     MSGR_KEEPALIVE),//+@002I~
    APP            (MSGID_APP,           MSGQ_APP,           MSGR_APP);//~@002R~
    //****************************************************************
    private int num; String reqID,respID;
    //*
    private MsgID(int Pidnum,String Preq,String Presp)
    {
        num=Pidnum; reqID=Preq; respID=Presp;
    }
    public int getNum()
    {
    	return num;
    }
    public String getRequestID()
    {
    	return reqID;
    }
    public String getReqspID()
    {
    	return respID;
    }
}
