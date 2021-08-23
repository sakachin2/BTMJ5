//*CID://+vac5R~:                             update#=  193;       //+vac5R~
//*************************************************************************
//2021/08/15 vac5 phone device(small DPI) support; use small size font//+vac5I~
//1A4z 2014/12/09 FileDialog:view file by click twice
//*************************************************************************
package com.btmtest.dialog;                                           //~1A4zR~

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;

import com.btmtest.R;                                              //~1A4zR~
import com.btmtest.utils.UView;                                    //~1A4zI~
import com.btmtest.utils.Dump;                                     //~1A4zI~
import com.btmtest.utils.Utils;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~1A4zI~

public class FileViewer extends UFDlg                                      //~1A4zR~
{
    private static final int LAYOUTID=R.layout.fileviewer;
    private static final int LAYOUTID_SMALLFONT=R.layout.fileviewer_theme;//+vac5I~
    private static final int TITLEID_VIEWER=R.string.Title_FileViewer;//~1A4zI~
    private static final int HELPTITLEID=R.string.HelpTitle_FileViewer;//~1A4zR~
    private static final String HELPFILE="FileViewer";         //~v@@@R~//~1A4zI~
    private static final String PARM_FILENAME="filename";          //~1A4zI~
    private static final String PARM_FULLPATH="fullpath";          //~1A4zI~
    private static final String PARM_U2S="U2S";                    //~1A4zI~
    private final static int    VIEWER_MAXSIZE=4096*16;            //~1A4zR~
    private final static int    TEXTVIEWID=R.id.FileContents;      //~1A4zI~
    private String fileName;
    private boolean swInitEnd;
//  private UFDlg ufdlg;                                           //~1A4zR~
    private String fullpathName;//~1A4zI~
    private TextView tvFileContents;                               //~1A4zI~
    private boolean swU2S;  //display \\uxxxx to String             //~1A4zI~
//************************************************                 //~1A4zI~
    public FileViewer()                                            //~1A4zI~
    {                                                              //~1A4zI~
    }                                                              //~1A4zI~
    //************************************************             //~1A4zI~
    public static FileViewer newInstance(String Pfname,String Pfullpath)//~1A4zR~
    {                                                              //~1A4zI~
    	FileViewer dlg=new FileViewer();                           //~1A4zI~
//      String title=Utils.getStr(TITLEID_VIEWER,Pfullpath);       //~1A4zR~
        int pos=Pfullpath.lastIndexOf("/");                        //~1A4zI~
        String fnm=pos>=0 ? Pfullpath.substring(pos+1) : Pfullpath;//~1A4zI~
        String title=Utils.getStr(TITLEID_VIEWER,fnm);             //~1A4zI~
//  	UFDlg ufdlg=UFDlg.newInstance(dlg,title,LAYOUTID,          //~1A4zR~
//  	UFDlg.setBundle(dlg,title,LAYOUTID,                        //~1A4zI~//+vac5R~
    	UFDlg.setBundle(dlg,title,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//+vac5I~
//                  UFDlg.FLAG_CLOSEBTN,0,HELPFILE);               //~1A4zR~
                    UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN,HELPTITLEID,HELPFILE);//~1A4zR~
//  	dlg.ufdlg=ufdlg;                                           //~1A4zR~
//      Bundle b=ufdlg.bundle;                                     //~1A4zR~
        Bundle b=dlg.bundle;                                       //~1A4zI~
        b.putString(PARM_FILENAME,Pfname);                         //~1A4zI~
        b.putString(PARM_FULLPATH,Pfullpath);                      //~1A4zI~
    	return dlg;                                                //~1A4zI~
    }                                                              //~1A4zI~
    //************************************************             //~1A4zI~
    public static FileViewer newInstance(String Pfname,String Pfullpath,boolean PswU2S)//~1A4zI~
    {                                                              //~1A4zI~
    	if (Dump.Y) Dump.println("FileViewer newInstance PswU2S="+PswU2S);//~1A4zI~
    	FileViewer dlg=newInstance(Pfname,Pfullpath);              //~1A4zI~
        Bundle b=dlg.bundle;                                       //~1A4zI~
        b.putBoolean(PARM_U2S,PswU2S);                             //~1A4zI~
    	return dlg;                                                //~1A4zI~
    }                                                              //~1A4zI~
    //******************************************                   //~1A4zI~
    @Override                                                      //~1A4zI~
    protected void initLayout(View Playoutview)                    //~1A4zI~
    {                                                              //~1A4zI~
    	super.initLayout(Playoutview);                             //~1A4zI~
        getComponent(Playoutview);                                 //~1A4zI~
    }                                                              //~1A4zI~
    //******************************************                   //~1A4zI~
    private void getComponent(View Playoutview)                    //~1A4zI~
    {                                                              //~1A4zI~
//      Bundle b=ufdlg.bundle;                                     //~1A4zR~
        Bundle b=bundle;                                           //~1A4zI~
        fileName=b.getString(PARM_FILENAME,null);                  //~1A4zI~
        fullpathName=b.getString(PARM_FULLPATH,null);              //~1A4zI~
        swU2S=b.getBoolean(PARM_U2S,false);                         //~1A4zI~
        tvFileContents=(TextView)UView.findViewById(Playoutview,R.id.FileContents);//~1A4zI~
     	if (Dump.Y) Dump.println("FileViewer.getComponent fname="+fileName+",fullpath="+fullpathName+",swU2S="+swU2S);//~1A4zR~
    }
    //******************************************                   //~1A4zI~
    //*called at onActivityCreated                                 //~1A4zI~
    //******************************************                   //~1A4zI~
	@Override                                                      //~1A4zI~
    public void setupDialog(Bundle Pbundle)                        //~1A4zI~
	{                                                              //~1A4zI~
        if (Dump.Y) Dump.println("FileViewer:setupDialog");        //~1A4zI~
	    setFileContents();                                         //~1A4zI~
    }                                                              //~1A4zI~
    //*****************************************************************
	private void setFileContents()
    {
    	String text=getFileContents();
        if (text==null)                                            //~1A4zI~
        	text="";                                               //~1A4zI~
        if (text.equals(""))                                       //~1A4zR~
       		UView.showToast(R.string.ErrFileViewerNoText);
        else
    		tvFileContents.setText(text);                          //~1A4zR~
    }
    //**********************************************************************
    private String getFileContents()
    {
        BufferedReader br=null;                                    //~1A4zI~
        try                                                        //~1A4zR~
		{                                                          //~1A4zI~
            FileReader fr = new FileReader(fullpathName);          //~1A4zR~
            br=new BufferedReader(fr);                                 //~1A4zR~
        }                                                          //~1A4zI~
        catch(Exception e)                                         //~1A4zM~
        {                                                          //~1A4zM~
        	Dump.println(true,e,"FileViewer:openFile:"+fileName);  //~1A4zI~
        	return null;                                           //~1A4zI~
        }                                                          //~1A4zM~
        StringBuffer sb = new StringBuffer("");                    //~1A4zR~
        readFile(br, VIEWER_MAXSIZE, sb);                          //~1A4zR~
        return new String(sb);
    }
    //******************************************************************************
	private int readFile(BufferedReader Pbr,int Pmaxsize,StringBuffer Pbuff)
    {
        String line;
    //************************************
        BufferedReader br=Pbr;
    	try
        {
            for (;;)
            {
            	line=br.readLine();
                if (line==null)  //eof
                	break;
                if (Pbuff.length()+line.length()>Pmaxsize)
                {
		            UView.showToastLong(Utils.getStr(R.string.ErrFileViewerTooLarge,Pmaxsize));
                	return 4;
                }
                if (swU2S)                                         //~1A4zI~
                	line=Utils.cvTextU2S(line);                    //~1A4zI~
                Pbuff.append(line+"\n");                           //~1A4zR~
            }
            br.close();
        }
		catch (Exception e)
		{
        	Dump.println(e,"FileViewer:readFile:"+fileName);
            UView.showToastLong(AG.resource.getString(R.string.ErrFileViewerRead));
		}
		return 0;
	}
}//class
