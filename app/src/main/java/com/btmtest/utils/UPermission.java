//*CID://+vau4R~:                             update#=   15;       //~vau4R~
//**********************************************************************
//2022/11/03 vau4 Ahsv-1an0 Permission may not rescheduled if pending entry>=2//~vau4I~
//2022/11/01 vau2 Ahsv-1ams 2022/11/01 control request permission to avoid 1amh:"null permission result".(W Activity: Can request only one set of permissions at a time)//~vau2I~
//**********************************************************************
//control request permission(issue one set at a time)
//**********************************************************************
package com.btmtest.utils;

import androidx.core.app.ActivityCompat;
import static com.btmtest.StaticVars.AG;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UPermission
{
	private final Map<Integer,RequestData> requestMap= Collections.synchronizedMap(new HashMap<>());
	private boolean swReschedule;                                  //~vau4I~
//*************************
	public UPermission()
    {
    	AG.aUPermission=this;
    }
//*************************
	private static UPermission getInstance()
    {
    	UPermission up=AG.aUPermission;
        if (up==null)
        	up=new UPermission();
        return up;
    }
//*************************
    public static void requestPermissions(String Ptype,int PrequestID)
    {
        if (Dump.Y) Dump.println("UPermission.requestPermissions single type="+Ptype+",requestID="+PrequestID);
        String[] types=new String[]{Ptype};
        requestPermissions(types,PrequestID);
    }
//*************************
    public static void requestPermissions(String[] Ptypes,int PrequestID)
    {
        if (Dump.Y) Dump.println("UPermission.requestPermissions types="+ Utils.toString(Ptypes)+",requestID="+PrequestID);
        UPermission up=getInstance();
        up.requestPermissionInstance(Ptypes,PrequestID);
    }
//*************************
    public void requestPermissionInstance(String[] Ptypes,int PrequestID)
    {
        if (Dump.Y) Dump.println("UPermission.requestPermissionInstance types="+ Utils.toString(Ptypes)+",requestID="+PrequestID);
        RequestData data=new RequestData(PrequestID,Ptypes);
        requestPermissions(PrequestID,data);
    }
//*************************
    private void requestPermissions(int Pid,RequestData Pdata)
    {
        if (Dump.Y) Dump.println("UPermission.requestPermission id="+Pid+",data="+Pdata);
        if (!isPendingRequest(Pid,Pdata))
	        requestPermissionActivity(Pdata.types,Pid);
    }
//*************************
    private void requestPermissionActivity(String[] Ptypes,int Pid)
    {
        if (Dump.Y) Dump.println("UPermission.requestPermissionActivity id="+Pid+",types="+Utils.toString(Ptypes));
        ActivityCompat.requestPermissions(AG.activity,Ptypes,Pid);
    }
//*************************
//*rc:false:not pending, issue request
//*************************
    private boolean isPendingRequest(int Pid,RequestData Pdata)
    {
        if (Dump.Y) Dump.println("UPermission.isPendingRequest id="+Pid);
        boolean rc;
        Integer id=Integer.valueOf(Pid);
        if (requestMap.get(id)!=null)
        {
	        if (Dump.Y) Dump.println("UPermission.isPendingRequest @@@@ rc=true id="+Pid+" is already pending");//+vau4R~
            return true;
        }
        if (requestMap.size()==0)
        {
	        if (Dump.Y) Dump.println("UPermission.isPendingRequest rc=false by map is empty");
            rc=false;
        }
        else
        if (swReschedule)                                          //~vau4I~
        {                                                          //~vau4I~
	        if (Dump.Y) Dump.println("UPermission.isPendingRequest rc=false by NOT empty but reschedule");//~vau4I~
            rc=false;                                              //~vau4I~
        }                                                          //~vau4I~
        else                                                       //~vau4I~
        {
	        if (Dump.Y) Dump.println("UPermission.isPendingRequest @@@@ rc=true by map is NOT empty");
            rc=true;
        }
        requestMap.put(id,Pdata);
	    if (Dump.Y) Dump.println("UPermission.isPendingRequest rc="+rc);
        return rc;
    }
//*************************
    public static boolean onRequestPermissionResult(int Pid,String[] Ptypes,int[] Presults)//~vau2R~
    {
        if (Dump.Y) Dump.println("UPermission.onRequestPermissionResult id="+Pid+",result="+Utils.toString(Presults)+",type="+Utils.toString(Ptypes));//~vau2R~
        UPermission up=getInstance();
	    boolean rc=up.schedulePendingRequest(Pid);
        if (Dump.Y) Dump.println("UPermission.onRequestPermissionResult rc="+rc+",id="+Pid);
        return rc;
    }
//*************************
    public boolean schedulePendingRequest(int Pid)
    {
    	boolean rc=false;
        if (Dump.Y) Dump.println("UPermission.schedulePendingRequest id="+Pid);
        if (requestMap.get(Pid)!=null)
        {
	        requestMap.remove(Pid);
	        if (Dump.Y) Dump.println("UPermission.schedulePendingRequest remove after size="+requestMap.size());
        }
	    if (requestMap.size()!=0)
        {
        	for (Integer id:requestMap.keySet())
            {
            	if (id!=null)
                {
            		int intID=id.intValue();
		        	RequestData data=requestMap.get(intID);
		        	requestMap.remove(intID);
			        if (Dump.Y) Dump.println("UPermission.schedulePendingRequest @@@@ scheduled pending id="+intID);
                    swReschedule=true;                             //~vau4I~
				    requestPermissions(intID,data);
                    swReschedule=false;                            //~vau4I~
                	break;
                }
            }
        }
        if (Dump.Y) Dump.println("UPermission.schedulePendingRequest rc="+rc+",id="+Pid);
        return rc;
    }
//****************************************************************************************
//****************************************************************************************
//****************************************************************************************
    class RequestData
    {
	    private int ID;
	    private String[] types;
        //*******************************************
        RequestData(int Pid,String[] Ptypes)
        {
	    	ID=Pid; types=Ptypes;
    		if (Dump.Y) Dump.println("UPermission.RequestData.constructor id="+Pid+",types="+Utils.toString(Ptypes));
        }
    }
}//class UPermission
