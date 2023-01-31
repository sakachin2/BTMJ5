//*CID://+vavcR~: update#= 888;                                    //~vavcR~
//**********************************************************************//~v101I~
//2023/01/23 vavc display historical entry on WD devicelist        //~vavcI~
//2023/01/22 vav9 display not devicename but username on connection dialog//~vav9I~
//**********************************************************************//~1107I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~
                                                                   //~v@@@I~
import com.btmtest.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;                                       //~vav9I~
import static com.btmtest.StaticVars.AG;                           //~vav9I~

import com.btmtest.dialog.FileDialog;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Prop;                                     //~vav9I~
import com.btmtest.utils.UFile;
import com.btmtest.utils.UScoped;                                  //~vav9I~

//~v@@@I~
public class AccName                                           //~v@@@R~//~vav9R~
{                                                              //~v@@@I~//~vav9R~
    private static final String WD_PREFIX=" ";                     //~vav9R~
    public  static final String DEV_AND_USER=" ? ";                //~vavcI~
	public  static final String PROP_NAME="AccountName";           //~vav9I~
    public  static final String PROP_EXT_ACCNAME=".accountnamefile";//~vav9I~
    private static final String CMT_PROP_ACCNAME="AccountName onece connected";//~vav9R~
    private static final String propFile       ="current"+PROP_EXT_ACCNAME;//~vav9R~
    private static final String propFileDefault="default"+PROP_EXT_ACCNAME;//~vav9R~
                                                                   //~vav9I~
    private Prop propAccName;                                      //~vav9R~
    private boolean swUpdated;                                     //~vav9R~
    private boolean swScoped;                                      //~vav9I~
    private String fpathSD;                                        //~vav9R~
    private Map<String,String> mapHistoryWD;                      //~vavcI~
    private Map<String,String> mapHistoryAddrWD=new HashMap<>();    //for save mac addr//+vavcR~
    //*****************************************************    //~v@@@R~//~vav9R~
    public AccName()                                               //~vav9R~
    {                                                          //~v@@@R~//~vav9R~
        if (Dump.Y) Dump.println("AccName.constructor");           //~vav9I~
        AG.aAccName=this;                                          //~vav9R~
    }                                                          //~v@@@R~//~vav9R~
    //*****************************************************        //~vav9I~
    public void loadProp()                                         //~vav9I~
    {                                                              //~vav9I~
        if (Dump.Y) Dump.println("AccName.loadProp propAccName="+propAccName);//~vav9R~
        if (propAccName!=null)  //one instance                     //~vav9I~
            return;                                                //~vav9I~
    	if (UFile.chkWritableSD())	//chk also UScoped             //~vav9I~
        {                                                          //~vav9I~
        	swScoped=UScoped.isScoped();	//after ScopedGranted  //~vav9R~
        	fpathSD=makeFullpath(propFile);                        //~vav9R~
        	propAccName=new Prop();                                //~vav9R~
        	if (!propAccName.loadProperties(fpathSD))              //~vav9R~
        		propAccName=AG.loadProp(propFile,propFileDefault/*from asset*/);//~vav9R~
        }                                                          //~vav9I~
        else                                                       //~vav9I~
        	propAccName=AG.loadProp(propFile,propFileDefault/*from asset*/);//~vav9I~
        if (Dump.Y) Dump.println("AccName.loadProp propAccName="+propAccName);//~vav9I~
    }                                                              //~vav9I~
    //*****************************************************        //~vav9I~
    public boolean saveProp()                                      //~vav9R~
    {                                                              //~vav9I~
        if (Dump.Y) Dump.println("AccName.saveProp swUpdated="+swUpdated+",propAccName="+propAccName);//~vav9R~
        if (!swUpdated)                                            //~vav9I~
        	return true;                                                //~vav9I~
        boolean rc=false;                                          //~vav9I~
        if (fpathSD!=null)                                         //~vav9R~
        {                                                          //~vav9I~
            if (swScoped)                                          //~vav9R~
                rc=propAccName.savePropertiesScoped(fpathSD,PROP_NAME,true/*swOverride*/);//~vav9R~
            else                                                   //~vav9R~
                rc=propAccName.saveProperties(fpathSD,PROP_NAME);  //~vav9R~
        }                                                          //~vav9I~
        if (Dump.Y) Dump.println("AccName.saveProp rc="+rc);       //~vav9I~
        return rc;                                                 //~vav9I~
    }                                                              //~vav9I~
    //*******************************************************************************//~vav9I~
    public String makeFullpath(String Pmember)              //~vav9I~
    {                                                              //~vav9I~
    	if (Dump.Y) Dump.println("AccName.makeFullpath member="+Pmember);//~vav9I~
        String[] folders= FileDialog.getFolder(swScoped);           //~vav9I~
        String workDirSD=folders[0];                               //~vav9I~
        String pathDataDir=folders[1];                             //~vav9I~
        String path=null;                                          //~vav9I~
        if (workDirSD!=null)	//external or scoped storage available//~vav9I~
        {                                                          //~vav9I~
        	path= UFile.makeFullpath(workDirSD,Pmember,""/*extension*/);//~vav9I~
        }                                                          //~vav9I~
        if (Dump.Y) Dump.println("AccName.makeFullpath swScoped="+swScoped+",workDirSD="+workDirSD+",pathDataDir="+pathDataDir+",path="+path);//~vav9I~
        return path;                                               //~vav9I~
    }                                                              //~vav9I~
    //*******************************************************************************//~vav9I~
    public String search(String PdevName)                          //~vav9I~
    {                                                              //~vav9I~
    	if (Dump.Y) Dump.println("AccName.search devName="+PdevName+",prop="+propAccName);//~vav9R~
    	String def=null;                                              //~vav9I~
        String rc=propAccName.getParameter(PdevName,def);         //~vav9I~
        if (Dump.Y) Dump.println("AccName.search rc="+rc);         //~vav9I~
        return rc;                                                 //~vav9I~
    }                                                              //~vav9I~
    //*******************************************************************************//~vav9I~
    public String searchWD(String PdevName)                        //~vav9I~
    {                                                              //~vav9I~
    	if (Dump.Y) Dump.println("AccName.searchWD devName="+PdevName);//~vav9R~
	    String rc=search(WD_PREFIX+PdevName);                       //~vav9I~
        return rc;                                                 //~vav9I~
    }                                                              //~vav9I~
    //*******************************************************************************//~vav9I~
    public boolean update(String PdevName,String PuserName)        //~vav9I~
    {                                                              //~vav9I~
    	if (Dump.Y) Dump.println("AccName.update devName="+PdevName+",userName="+PuserName+",swUpdated="+swUpdated);//~vav9I~
		String old=search(PdevName);                               //~vav9I~
    	boolean swNew=false;                                             //~vav9I~
        if (old==null || !old.equals(PuserName))                   //~vav9R~
        {                                                          //~vav9I~
        	swNew=true;                                            //~vav9I~
        	swUpdated=true;                                        //~vav9I~
	    	propAccName.setParameter(PdevName,PuserName);          //~vav9I~
        }                                                          //~vav9I~
    	if (Dump.Y) Dump.println("AccName.update exit swNew="+swNew+",devName="+PdevName+",userName="+PuserName+",swUpdated="+swUpdated);//~vav9I~
        return swNew;                                              //~vav9I~
    }                                                              //~vav9I~                    ]//+vavcR~
    //*******************************************************************************//~vav9I~
    public boolean updateWD(String PdevName,String PuserName)      //~vav9I~
    {                                                              //~vav9I~
    	if (Dump.Y) Dump.println("AccName.updateWD devName="+PdevName+",userName="+PuserName+",swUpdated="+swUpdated);//~vav9I~
		boolean rc=update(WD_PREFIX+PdevName,PuserName);                     //~vav9R~
    	if (Dump.Y) Dump.println("AccName.updateWD rc="+rc);       //~vav9R~
        return rc;                                                 //~vav9R~
    }                                                              //~vav9I~
    //*******************************************************************************//~vavcI~
    private int updateHistoryAddrWD(String PdevName,String PdevAddr)//~vavcI~
    {                                                              //~vavcI~
    	if (Dump.Y) Dump.println("AccName.updateHistoryAddrWD devName="+PdevName+",devAddr="+PdevAddr+",swUpdated="+swUpdated);//~vavcR~
        mapHistoryAddrWD.put(PdevName,PdevAddr);                   //~vavcI~
        int rc=mapHistoryAddrWD.size();                            //~vavcI~
        swUpdated=true;                                            //~vavcI~
    	if (Dump.Y) Dump.println("AccName.updateHistoryAddrWD ctr="+rc);//~vavcR~
        return rc;                                                 //~vavcI~
    }                                                              //~vavcI~
    //*******************************************************************************//~vavcI~
    public int createHistoryListWD()                               //~vavcI~
    {                                                              //~vavcI~
    	if (Dump.Y) Dump.println("AccName.createHistoryListWD propAccName="+propAccName);//~vavcI~
        Map <String,String> map=new HashMap<>();                   //~vavcI~
        for (Map.Entry<Object,Object> prop: propAccName.P.entrySet())//~vavcI~
        {                                                          //~vavcI~
        	String dev=prop.getKey().toString();                   //~vavcI~
        	String user=prop.getValue().toString();                //~vavcI~
	    	if (Dump.Y) Dump.println("AccName.createHistoryListWD dev="+dev+",user="+user);//~vavcI~
            if (dev.startsWith(WD_PREFIX))                        //~vavcI~
            	map.put(dev.substring(1),user);                    //~vavcI~
        }                                                          //~vavcI~
        mapHistoryWD=map;                                          //~vavcI~
        int rc=map.size();                                          //~vavcI~
	    if (Dump.Y) Dump.println("AccName.createHistoryListWD rc="+rc);//~vavcI~
        return rc;                                                 //~vavcI~
    }                                                              //~vavcI~
    //*******************************************************************************//~vavcI~
    public boolean pairedWD(String PdevName,String PdevAddr)       //~vavcR~
    {                                                              //~vavcI~
    	if (Dump.Y) Dump.println("AccName.pairedWD devName="+PdevName+",addr="+PdevAddr);//~vavcI~
    	updateHistoryAddrWD(PdevName,PdevAddr);                     //~vavcI~
	    boolean rc=removeHistoryWD(PdevName);	//drop from history//~vavcR~
    	if (Dump.Y) Dump.println("AccName.pairedWD rc="+rc);       //~vavcI~
        return rc;                                                 //~vavcI~
    }                                                              //~vavcI~
    //*******************************************************************************//~vavcI~
    private boolean removeHistoryWD(String PdevName)               //~vavcR~
    {                                                              //~vavcI~
        String user=mapHistoryWD.remove(PdevName);                  //~vavcI~
    	if (Dump.Y) Dump.println("AccName.removeHistoryWD user="+user+",dev="+PdevName);//~vavcI~
        return user!=null;                                                 //~vavcI~
    }                                                              //~vavcI~
    //*******************************************************************************//~vavcI~
    public String[] getEntrySetHistoryWD()                         //~vavcI~
    {                                                              //~vavcI~
        int sz=mapHistoryWD.size()*3;                              //~vavcR~
    	if (Dump.Y) Dump.println("AccName.getEntrySetHistoryWD sz="+sz);//~vavcR~
        if (sz==0)                                                 //~vavcI~
        	return null;                                           //~vavcI~
        String[] rc=new String[sz];                                //~vavcI~
        int ii=0;                                                  //~vavcI~
        for (String dev:mapHistoryWD.keySet())                     //~vavcI~
        {                                                          //~vavcI~
        	rc[ii++]=dev;                                          //~vavcI~
        	rc[ii++]=mapHistoryWD.get(dev);                        //~vavcI~
        	rc[ii++]=mapHistoryAddrWD.get(dev);                    //~vavcI~
        }                                                          //~vavcI~
    	if (Dump.Y) Dump.println("AccName.getEntrySetHistoryWD rc="+ Arrays.toString(rc));//~vavcR~
        return rc;                                                 //~vavcI~
    }                                                              //~vavcI~
}//class AccName                                                  //~dataR~//~@@@@R~//~v@@@R~//~vav9R~
