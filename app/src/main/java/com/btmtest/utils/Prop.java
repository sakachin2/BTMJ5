//*CID://+vat1R~:                             update#=   79;       //~vat1R~
//**********************************************************************//~@@@@I~
//2022/10/16 vat1 deprecated; Java9 new Integer,Boolean,Double-->valueOf//~vat1I~
//2021/12/24 vaid Toast if Scoped file already exists.             //~vaicI~
//2021/12/24 vaic Dump at send History rule from sdcard device to scoped file device//~vaicI~
//2021/09/19 vae8 keep sharedPreference to external storage with PrefSetting item.//~vae8I~
//2021/09/12 vae0 Scped for BTMJ5                                  //~vae0I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//**********************************************************************//~@@@@I~
package com.btmtest.utils;                                         //~@@@@R~


import android.content.Context;

import com.btmtest.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;                                   //~@@@@R~
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Properties;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~//~9719I~

public class Prop                                                  //~@@@@R~
{
	public    Properties P=new Properties();                       //~@@@@R~
	public       String propFilename;                              //~@@@@R~
	//************************************************************ //~@@@@I~
	public  synchronized Enumeration properties()                  //~@@@@R~
	{                                                              //~@@@@I~
		return P.keys();                                           //~@@@@I~
	}                                                              //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized Prop getClone()                           //~@@@@R~
	{                                                              //~@@@@R~
		Prop newprop=new Prop();                                   //~@@@@R~
		newprop.P=(Properties)(P.clone());                                       //~@@@@R~
		newprop.propFilename=propFilename;                         //~@@@@R~
        if (Dump.Y) Dump.println("Prop.getClone() filename="+propFilename);//~@@@@R~
        return newprop;	                                           //~@@@@R~
	}
	//************************************************************ //~@@@@I~
	public  synchronized void loadProperties(String Pfname,InputStream Pis)//~@@@@I~
	{                                                              //~@@@@I~
		propFilename=Pfname;                                       //~@@@@I~
        if (Dump.Y) Dump.println("Prop.loadProperties with InputStream: "+Pfname);//~@@@@I~
		loadProperties(Pis);                                       //~@@@@I~
	}                                                              //~@@@@I~
	//************************************************************ //~@@@@M~
	//* input by fullpath                                          //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized boolean loadProperties(String Pfname)   //~@@@@M~
	{                                                              //~@@@@M~
        if (Dump.Y) Dump.println("Prop.loadProperties without InputStream: swScoped="+UScoped.isScoped()+",Pfname="+Pfname);//~@@@@I~//~vae0R~
        if (UScoped.isScoped())                                    //~vae0I~
        {                                                          //~vae0I~
        	return loadPropertiesScoped(Pfname);                   //~vae0I~
        }                                                          //~vae0I~
		propFilename=Pfname;                                       //~@@@@R~
		try                                                        //~@@@@M~
		{                                                          //~@@@@M~
			FileInputStream in=new FileInputStream(Pfname);      //~@@@@M~
			clearProperties();                                     //~9827I~
			P.load(in);                                            //~@@@@M~
			in.close();                                            //~@@@@M~
		}                                                          //~@@@@M~
		catch (Exception e)                                        //~@@@@M~
		{                                                          //~@@@@M~
        	if (Dump.Y) Dump.println("Prop.loadProperties with InputStream fnm="+Pfname+",exception="+e.toString());//~vae8I~
			return false;                                          //~@@@@M~
		}                                                          //~@@@@M~
		return true;                                               //~@@@@M~
	}                                                              //~@@@@M~
	//************************************************************ //~vae0I~
	public  synchronized boolean loadPropertiesScoped(String Pfpath)//~vae0I~
	{                                                              //~vae0I~
        if (Dump.Y) Dump.println("Prop.loadPropertiesScoped fpath="+Pfpath);//~vae0I~
        String member=AG.aUScoped.parseMember(Pfpath);                    //~vae0I~
		propFilename=AG.aUScoped.getFullpath(member);               //~vae0I~
		try                                                        //~vae0I~
		{                                                          //~vae0I~
			BufferedReader br=AG.aUScoped.openInputDocumentBufferedReader(member);//~vae0I~
            if (br!=null)                                          //~vae0I~
            {                                                      //~vae0I~
				clearProperties();                                 //~vae0I~
				P.load(br);                                        //~vae0I~
				br.close();                                        //~vae0I~
            }                                                      //~vae0I~
		}                                                          //~vae0I~
		catch (Exception e)                                        //~vae0I~
		{                                                          //~vae0I~
			return false;                                          //~vae0I~
		}                                                          //~vae0I~
		return true;                                               //~vae0I~
	}                                                              //~vae0I~
	//************************************************************ //~@@@@I~
	//*load from data/data/<pkg>/files                             //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized boolean loadPropFiles(String Pfname)      //~@@@@R~
	{                                                              //~@@@@I~
        if (Dump.Y) Dump.println("Prop.loadPropFiles:"+Pfname);    //~@@@@R~
		propFilename=Pfname;                                       //~@@@@I~
		try                                                        //~@@@@I~
		{                                                          //~@@@@I~
	        FileInputStream in=UFile.openInputData(Pfname);        //~@@@@R~
            if (in!=null)                                          //~@@@@I~
            {                                                      //~@@@@I~
				clearProperties();                                 //~9827I~
				P.load(in);                                        //~@@@@I~
				in.close();                                        //~@@@@I~
            }                                                      //~@@@@I~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~@@@@I~
			return false;                                          //~@@@@I~
		}                                                          //~@@@@I~
		return true;                                               //~@@@@I~
	}                                                              //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized void loadProperties(InputStream in)       //~@@@@I~
	{                                                              //~@@@@I~
    	if (Dump.Y) Dump.println("Prop.loadProperties from inputStream");//~9827I~
		try                                                        //~@@@@I~
		{                                                          //~@@@@I~
			clearProperties();                                     //~9827I~
			P.load(in);                                            //~@@@@I~
			in.close();                                            //~@@@@I~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~@@@@I~
        	Dump.println("Prop.loadProperties InputStream");       //~@@@@I~
		}                                                          //~@@@@I~
	}                                                              //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized void loadFromString(String Pprops)        //~@@@@R~
	{                                                              //~@@@@I~
    	if (Dump.Y) Dump.println("Prop.loadFromString props="+Pprops);//~@@@@R~
		try                                                        //~@@@@I~
		{                                                          //~@@@@I~
			InputStream is=new ByteArrayInputStream(Pprops.getBytes("UTF-8"));//~@@@@I~
			loadProperties(is);                                    //~@@@@I~
	    	if (Dump.Y) Dump.println("Prop.loadFromString toString="+toString("loadFromString"));//~@@@@I~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~@@@@I~
        	Dump.println("Prop.loadProperties String");            //~@@@@I~
		}                                                          //~@@@@I~
	}                                                              //~@@@@I~
	//************************************************************ //~@@@@I~
	//*output by fullpath                                          //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized boolean saveProperties(String Pfname,String Pcomment)//~@@@@R~
	{                                                              //~@@@@I~
    	if (Dump.Y) Dump.println("Prop.saveProperties fnm="+Pfname); //~9616I~
    	boolean rc=false;                                          //~@@@@I~
		try                                                        //~@@@@I~
		{                                                          //~@@@@I~
        	FileOutputStream fos=new FileOutputStream(Pfname);     //~@@@@R~
			P.store(fos,Pcomment);                                 //~@@@@R~
			fos.close();                                       //~@@@@I~
            rc=true;                                               //~@@@@I~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~@@@@I~
			Dump.println(e,"Prop.saveProperties cmt="+Pcomment);   //~@@@@I~
		}                                                          //~@@@@I~
        return rc;                                                 //~@@@@I~
	}                                                              //~@@@@I~
	//************************************************************ //~vae0I~
	//*output scoped                                               //~vae0R~
	//************************************************************ //~vae0I~
	public  synchronized boolean savePropertiesScoped(String Pfname,String Pcomment,boolean PswOverride)//~vae0R~
	{                                                              //~vae0I~
    	if (Dump.Y) Dump.println("Prop.savePropertiesScoped fnm="+Pfname+",swOverride="+PswOverride+",cmt="+Pcomment);//~vae0R~
    	boolean rc=false;                                          //~vae0I~
		try                                                        //~vae0I~
		{                                                          //~vae0I~
//      	OutputStreamWriter osw=AG.aUScoped.openOutputDocumentWriter(Pfname,PswOverride);//~vae0R~//~vaidR~
			String member=AG.aUScoped.parseMember(Pfname);                        //~vae0I~//~vaidR~
        	OutputStreamWriter osw=AG.aUScoped.openOutputDocumentWriter(member,PswOverride);//~vaidR~
            if (osw!=null)                                         //~vae0I~
            {                                                      //~vae0I~
				P.store(osw,Pcomment);                             //~vae0R~
				osw.close();                                       //~vae0R~
            	rc=true;                                           //~vae0R~
            }                                                      //~vae0I~
		}                                                          //~vae0I~
		catch (IOException e)                                      //~vae0I~
		{                                                          //~vae0I~
			Dump.println(e,"Prop.savePropertiesScoped Pfname="+Pfname);//~vaidR~
		}                                                          //~vae0I~
        return rc;                                                 //~vae0I~
	}                                                              //~vae0I~
	//************************************************************ //~@@@@I~
	//*output by fullpath                                          //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized String toString(String Pcomment)          //~@@@@I~
	{                                                              //~@@@@I~
    	String s="";                                               //~@@@@I~
		try                                                        //~@@@@I~
		{                                                          //~@@@@I~
        	ByteArrayOutputStream fos=new ByteArrayOutputStream(); //~@@@@I~
			P.store(fos,Pcomment);                                 //~@@@@I~
            fos.close();                                           //~@@@@I~
            s=fos.toString("UTF-8");                               //~@@@@I~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~@@@@I~
			Dump.println(e,"Prop.saveProperties cmt="+Pcomment);   //~@@@@I~
		}                                                          //~@@@@I~
		Dump.println("Prop.toString cmt="+Pcomment+",s="+s);     //~@@@@I~
        return s;                                                  //~@@@@I~
	}                                                              //~@@@@I~
	//************************************************************ //~vae8I~
	public String toString()                                       //~vae8R~
	{                                                              //~vae8I~
        StringWriter sw=new StringWriter();                        //~vae8I~
        String s="null";                                           //~vae8I~
		try                                                        //~vae8I~
		{                                                          //~vae8I~
			P.store(sw,"toString"/*cmt*/);                         //~vae8I~
            s=sw.getBuffer().toString();                           //~vae8I~
            sw.close();                                            //~vae8I~
		}                                                          //~vae8I~
		catch (IOException e)                                      //~vae8I~
		{                                                          //~vae8I~
			Dump.println(e,"Prop.toString");                       //~vae8I~
		}                                                          //~vae8I~
        return s;                                                  //~vae8I~
	}                                                              //~vae8I~
	//************************************************************ //~@@@@I~
	//*save to /data/data/<pkg>/files                              //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized boolean savePropDataFile(String Pcomment) //~@@@@I~
	{                                                              //~@@@@I~
    	boolean rc=false;                                          //~@@@@I~
        String fnm=propFilename;                                   //~@@@@I~
    	if (Dump.Y) Dump.println("Prop.savePropDataFile fnm="+fnm+",cmt="+Pcomment);//~vaidR~
		try                                                        //~@@@@I~
		{                                                          //~@@@@I~
        	FileOutputStream fos=UFile.openOutputData(fnm);//private//~@@@@R~
            if (fos!=null)                                         //~@@@@I~
            {                                                      //~@@@@I~
				P.store(fos,Pcomment);                             //~@@@@I~
				fos.close();                                       //~@@@@I~
            	rc=true;                                           //~@@@@I~
            }                                                      //~@@@@I~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~@@@@I~
			Dump.println(e,"Prop.savePropDataFile file="+fnm+",cmt="+Pcomment);//~@@@@I~
		}                                                          //~@@@@I~
        return rc;                                                 //~@@@@I~
	}                                                              //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized void clearProperties()                    //~@@@@R~
	{                                                              //~@@@@R~
		P=new Properties();                                        //~@@@@I~
	}
	//************************************************************ //~@@@@I~
	public  synchronized void resetProperties(Prop Pprop)          //~@@@@R~
	{                                                              //~@@@@I~
		P=Pprop.P;                                                 //~@@@@R~
	}                                                              //~@@@@I~
//    //************************************************************ //~@@@@I~//~va40R~
//    public  synchronized void saveProperties(String text)          //~@@@@R~//~va40R~
//    {                                                              //~9616I~//~va40R~
//        if (Dump.Y) Dump.println("Prop.saveProperties fnm="+propFilename+",text="+text);//~9616I~//~9826R~//~va40R~
//        try                                                        //~9616R~//~va40R~
//        {                                                          //~@@@@R~//~va40R~
//            FileOutputStream out=new FileOutputStream(propFilename);//~@@@@R~//~va40R~
//            P.save(out,text);                                    //~va40R~
//            out.close();                                         //~va40R~
//        }                                                        //~va40R~
//        catch (Exception e)                                        //~1401R~//~va40R~
//        {                                                          //~1401I~//~va40R~
//            Dump.println(e,"Property:"+propFilename+ "save failed");//~1308I~//~1401I~//~@@@@R~//~va40R~
//        }                                                          //~1308I~//~1401I~//~va40R~
//        if (Dump.Y) Dump.println("Property "+text+" saved to "+propFilename);        //~1308I~//~1506R~//~@@@@R~//~va40R~
//    }                                                            //~va40R~
	//************************************************************ //~9826I~
	public  static void savePropertiesString(String Pfnm,String Ptext)//~9826R~
    {                                                              //~9826I~
    	if (Dump.Y) Dump.println("Prop.savePropertiesString fnm="+Pfnm+",text="+Ptext);//~9826I~//~9827R~
		try                                                        //~9826I~
		{                                                          //~9826I~
			FileOutputStream out=new FileOutputStream(Pfnm);       //~9826I~
			OutputStreamWriter os= new OutputStreamWriter(out,"UTF8");//~9826I~
			BufferedWriter bw= new BufferedWriter(os);             //~9826I~
            bw.write(Ptext);                                       //~9826I~
			bw.close();                                            //~9826I~
		}                                                          //~9826I~
		catch (Exception e)                                        //~9826I~
		{                                                          //~9826I~
            Dump.println(e,"Property:"+Pfnm+ "save failed");       //~9826I~
		}                                                          //~9826I~
        if (Dump.Y) Dump.println("Property saved to "+Pfnm);       //~9826I~
	}                                                              //~9826I~
	//************************************************************ //~vae0I~
	//*write received rule,aviod override ************************ //~vae0I~
	//************************************************************ //~vae0I~
//	public  static void savePropertiesStringScoped(String Pmember,String Ptext)//~vae0I~//~vaidR~
  	public  static boolean savePropertiesStringScoped(String Pmember,String Ptext)//~vaidI~
    {                                                              //~vae0I~
    	boolean rc=false;                                          //~vaidI~
    	if (Dump.Y) Dump.println("Prop.savePropertiesStringScoped member="+Pmember+",text="+Ptext);//~vae0I~
		try                                                        //~vae0I~
		{                                                          //~vae0I~
//  		OutputStreamWriter os= AG.aUScoped.openOutputDocumentWriter(Pmember,false/*PswOveride*/);//~vae0I~//~vaidR~
			String member=AG.aUScoped.parseMember(Pmember);        //~vaidR~
    		OutputStreamWriter os= AG.aUScoped.openOutputDocumentWriter(member,false/*PswOveride*/);//~vaidR~
          if (AG.aUScoped.swCouldNotOverride)                      //~vaidR~
          {                                                        //~vaidR~
    	    if (Dump.Y) Dump.println("Prop.savePropertiesStringScoped Already exist skip write Pmember="+Pmember+",member"+member);//~vaidR~
//          UView.showToastLong(Utils.getStr(R.string.Info_AlreadyExist_SkipWrite,member));//~vaidR~
          }                                                        //~vaidR~
          else                                                     //~vaidR~
          {                                                        //~vaidR~
			BufferedWriter bw= new BufferedWriter(os);             //~vae0I~
            bw.write(Ptext);                                       //~vae0I~
			bw.close();                                            //~vae0I~
            rc=true;                                               //~vaidI~
          }                                                        //~vaidR~
		}                                                          //~vae0I~
		catch (Exception e)                                        //~vae0I~
		{                                                          //~vae0I~
            Dump.println(e,"Property:"+Pmember+ "save failed");    //~vae0I~
		}                                                          //~vae0I~
        if (Dump.Y) Dump.println("Property saved to "+Pmember+",rc="+rc);    //~vae0I~//~vaidR~
        return rc;                                                 //~vaidI~
	}                                                              //~vae0I~
//    //************************************************************ //~@@@@I~//~va40R~
//    public  synchronized boolean saveProperties(String Pfname,FileOutputStream Pfos,String Pcmt)//~@@@@I~//~va40R~
//    {                                                              //~@@@@I~//~va40R~
//        boolean rc=false;                                              //~@@@@I~//~va40R~
//        if (Dump.Y) Dump.println("Prop.saveProperties file="+Pfname+",cmt="+Pcmt);//~@@@@I~//~va40R~
//        try                                                        //~@@@@I~//~va40R~
//        {                                                          //~@@@@I~//~va40R~
//            P.save(Pfos,Pcmt);                                     //~@@@@I~//~va40R~
//            Pfos.close();                                          //~@@@@I~//~va40R~
//            rc=true;                                               //~@@@@I~//~va40R~
//        }                                                          //~@@@@I~//~va40R~
//        catch (Exception e)                                        //~@@@@I~//~va40R~
//        {                                                          //~@@@@I~//~va40R~
//            Dump.println(e,"Prop.saveProperties:filename="+Pfname);//~@@@@I~//~va40R~
//        }                                                          //~@@@@I~//~va40R~
//        return rc;                                                 //~@@@@I~//~va40R~
//    }                                                              //~@@@@I~//~va40R~
	//************************************************************ //~@@@@I~
	public  synchronized void setParameter(String key, boolean value)//~@@@@R~
	{                                                              //~@@@@R~
		if (P==null) return;                                       //~@@@@I~
		if (value)                                                 //~@@@@R~
			P.put(key,"true");                                     //~@@@@I~
		else                                                       //~@@@@R~
			P.put(key,"false");                                    //~@@@@I~
        if (Dump.Y) Dump.println("Prop.setParameter key="+key+",value="+value);//~vaidI~
	}
	//************************************************************ //~@@@@I~
	public  synchronized boolean getParameter(String key, boolean def)//~@@@@R~
	{                                                              //~@@@@R~
		try                                                        //~@@@@I~
		{                                                          //~@@@@R~
			String s=P.getProperty(key);                           //~@@@@I~
            if (s!=null)                                           //~@@@@I~
                if (s.equals("true"))                              //~@@@@R~
                    return true;                                   //~@@@@R~
                else                                               //~@@@@R~
                if (s.equals("false"))                             //~@@@@R~
                    return false;                                  //~@@@@R~
			return def;
		}
		catch (Exception e)
		{	return def;
		}
	}
	//************************************************************ //~@@@@I~
	public  synchronized String getParameter(String key, String def)//~@@@@R~
	{                                                              //~@@@@R~
		String res=def;                                            //~@@@@I~
		try
		{                                                          //~@@@@R~
			res=P.getProperty(key);                                //~@@@@I~
		}
		catch (Exception e)                                        //~@@@@R~
		{                                                          //~@@@@I~
		}                                                          //~@@@@I~
		if (res!=null)
		{                                                          //~@@@@R~
			if (res.startsWith("$"))                               //~@@@@I~
				res=res.substring(1);                              //~@@@@I~
			return res;
		}
		else return def;
	}
	//************************************************************ //~@@@@I~
	public  synchronized void setParameter(String key, String value)
	{//~@@@@R~
		if (value.length()>0 && Character.isSpaceChar(value.charAt(0)))
			value="$"+value;
        if (Dump.Y) Dump.println("Prop.setparameter key="+key+",str val="+value);//~@@@@I~
		P.put(key,value);
	}
	//************************************************************ //~@@@@I~
	public  synchronized int getParameter(String key, int def)     //~@@@@R~
	{                                                              //~@@@@R~
    	if (Dump.Y) Dump.println("Prop.getParameter key="+key+",def="+def);//~@@@@I~
		try                                                        //~@@@@I~
		{                                                          //~@@@@R~
			String s=getParameter(key,"");                         //~@@@@R~
    		if (Dump.Y) Dump.println("Prop.getParameter str="+s);  //~0217I~
            if (s.equals(""))                                      //~@@@@I~
            	return def;                                        //~@@@@I~
			return Integer.parseInt(s);                            //~@@@@I~
		}
		catch (Exception e)
		{                                                          //~@@@@R~
//      	if (Dump.Y) Dump.println(e,"Prop.getParameter");       //~@@@@I~//~9514R~
			try                                                    //~@@@@I~
			{                                                      //~@@@@R~
//  			double x=new Double(getParameter(key,"")).doubleValue();//~@@@@I~//~vat1R~
    			double x=Double.valueOf(getParameter(key,"")).doubleValue();//~vat1I~
				return (int)x;
			}
			catch (Exception ex)                                   //~@@@@R~
			{                                                      //~@@@@I~
//  	    	if (Dump.Y) Dump.println(e,"Prop.getParameter2");  //~@@@@I~//~9514R~
			}                                                      //~@@@@I~
			return def;
		}
	}
	//************************************************************ //~@@@@I~
	public  synchronized void setParameter(String key, int value)  //~@@@@R~
	{                                                              //~@@@@R~
		setParameter(key,""+value);                                //~@@@@I~
	}
	//************************************************************ //~@@@@I~
	public  synchronized double getParameter(String key, double def)//~@@@@R~
	{	try
		{                                                          //~@@@@R~
//  		return new Double(getParameter(key,"")).doubleValue(); //~@@@@I~//~vat1R~
    		return Double.parseDouble(getParameter(key,"0.0"));//+vat1R~
		}
		catch (Exception e)
		{                                                          //~@@@@R~
			return def;                                            //~@@@@I~
		}
	}
	//************************************************************ //~@@@@I~
	public  synchronized void setParameter(String key, double value)//~@@@@R~
	{                                                              //~@@@@R~
		setParameter(key,""+value);                                //~@@@@I~
	}
	//************************************************************ //~@@@@I~
	public  synchronized void removeParameter(String key)          //~@@@@R~
	{                                                              //~@@@@R~
		P.remove((Object)key);                                     //~@@@@I~
	}
	//************************************************************ //~@@@@I~
	public  synchronized void removeAllParameters(String start)    //~@@@@R~
	{                                                              //~@@@@R~
		Enumeration e=P.keys();                                    //~@@@@I~
		while (e.hasMoreElements())
		{                                                          //~@@@@R~
			String key=(String)e.nextElement();                    //~@@@@I~
			if (key.startsWith(start))
			{                                                      //~@@@@R~
				P.remove((Object)key);                             //~@@@@I~
			}
		}	
	}
	//************************************************************ //~@@@@I~
	public  synchronized void resetDefaults(String defaults)       //~@@@@R~
	{                                                              //~@@@@R~
		Enumeration e=P.keys();                                    //~@@@@I~
		while (e.hasMoreElements())
		{                                                          //~@@@@R~
			String key=(String)e.nextElement();                    //~@@@@I~
			if (key.startsWith(defaults))
			{                                                      //~@@@@R~
				setParameter(key.substring(defaults.length()),getParameter(key,""));//~@@@@I~
			}
		}	
	}
	//************************************************************ //~@@@@I~
	public  void resetDefaults()                                   //~@@@@R~
	{                                                              //~@@@@R~
		resetDefaults("default.");                                 //~@@@@I~
	}
	//************************************************************ //~@@@@I~
	public  synchronized boolean haveParameter (String key)        //~@@@@R~
	{	try
		{                                                          //~@@@@R~
			String res=P.getProperty(key);                         //~@@@@I~
			if (res==null)                                         //~@@@@R~
				return false;                                      //~@@@@I~
		}
		catch (Exception e)                                        //~@@@@R~
		{                                                          //~@@@@I~
			return false;                                          //~@@@@I~
		}                                                          //~@@@@I~
		return true;
	}
}
