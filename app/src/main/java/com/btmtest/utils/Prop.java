//*CID://+DATER~:                             update#=   58;       //~@@@@I~//~9514R~
//**********************************************************************//~@@@@I~
//**********************************************************************//~@@@@I~
package com.btmtest.utils;                                         //~@@@@R~


import android.content.Context;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;                                   //~@@@@R~
import java.io.InputStream;
import java.io.OutputStreamWriter;
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
        if (Dump.Y) Dump.println("Prop.loadProperties without InputStream: "+Pfname);//~@@@@I~
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
			return false;                                          //~@@@@M~
		}                                                          //~@@@@M~
		return true;                                               //~@@@@M~
	}                                                              //~@@@@M~
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
	//************************************************************ //~@@@@I~
	//*save to /data/data/<pkg>/files                              //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized boolean savePropDataFile(String Pcomment) //~@@@@I~
	{                                                              //~@@@@I~
    	boolean rc=false;                                          //~@@@@I~
        String fnm=propFilename;                                   //~@@@@I~
    	if (Dump.Y) Dump.println("Prop.savePropDataFile fnm="+fnm);//~9616I~
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
	//************************************************************ //~@@@@I~
	public  synchronized void saveProperties(String text)          //~@@@@R~
    {                                                              //~9616I~
    	if (Dump.Y) Dump.println("Prop.saveProperties fnm="+propFilename+",text="+text);//~9616I~//~9826R~
		try                                                        //~9616R~
		{                                                          //~@@@@R~
			FileOutputStream out=new FileOutputStream(propFilename);//~@@@@R~
			P.save(out,text);
			out.close();
		}
		catch (Exception e)                                        //~1401R~
		{                                                          //~1401I~
            Dump.println(e,"Property:"+propFilename+ "save failed");//~1308I~//~1401I~//~@@@@R~
		}                                                          //~1308I~//~1401I~
        if (Dump.Y) Dump.println("Property "+text+" saved to "+propFilename);        //~1308I~//~1506R~//~@@@@R~
	}
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
	//************************************************************ //~@@@@I~
	public  synchronized boolean saveProperties(String Pfname,FileOutputStream Pfos,String Pcmt)//~@@@@I~
	{                                                              //~@@@@I~
    	boolean rc=false;                                              //~@@@@I~
        if (Dump.Y) Dump.println("Prop.saveProperties file="+Pfname+",cmt="+Pcmt);//~@@@@I~
        try                                                        //~@@@@I~
		{                                                          //~@@@@I~
			P.save(Pfos,Pcmt);                                     //~@@@@I~
			Pfos.close();                                          //~@@@@I~
            rc=true;                                               //~@@@@I~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~@@@@I~
            Dump.println(e,"Prop.saveProperties:filename="+Pfname);//~@@@@I~
		}                                                          //~@@@@I~
        return rc;                                                 //~@@@@I~
	}                                                              //~@@@@I~
	//************************************************************ //~@@@@I~
	public  synchronized void setParameter(String key, boolean value)//~@@@@R~
	{                                                              //~@@@@R~
		if (P==null) return;                                       //~@@@@I~
		if (value)                                                 //~@@@@R~
			P.put(key,"true");                                     //~@@@@I~
		else                                                       //~@@@@R~
			P.put(key,"false");                                    //~@@@@I~
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
    		if (Dump.Y) Dump.println("Prop.getParameter str="+s);  //+0217I~
            if (s.equals(""))                                      //~@@@@I~
            	return def;                                        //~@@@@I~
			return Integer.parseInt(s);                            //~@@@@I~
		}
		catch (Exception e)
		{                                                          //~@@@@R~
//      	if (Dump.Y) Dump.println(e,"Prop.getParameter");       //~@@@@I~//~9514R~
			try                                                    //~@@@@I~
			{                                                      //~@@@@R~
				double x=new Double(getParameter(key,"")).doubleValue();//~@@@@I~
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
			return new Double(getParameter(key,"")).doubleValue(); //~@@@@I~
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
