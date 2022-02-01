//*CID://+vaieR~:                             update#=  275;       //~vaieR~
//************************************************************************
//2021/12/24 vaie Scoped device->sdcard device;History rule send fails.//~vaieI~
//2021/12/24 vaid Toast if Scoped file already exists.             //~vaidI~
//2021/08/25 vae0 Scped for BTMJ5                                  //~vae0I~
//1ak0 2021/08/26 androd11:externalStorage:ScopedStorage
//************************************************************************
package com.btmtest.utils;

import com.btmtest.R;
import static com.btmtest.StaticVars.AG;
import static com.btmtest.AG.*;                                    //~1ak0I~

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.UriPermission;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;

import androidx.documentfile.provider.DocumentFile;

//~1110I~
@TargetApi(30)
public class UScoped
		 implements Alert.AlertI                                   //~1ak0R~
{
	private static final String PREFKEY_SAVE_DIR="SaveDir";
	private static final String PREFKEY_SAVE_DIR_INTERNAL="SaveDirInternal";//~vae0I~
	private static final String FILTER_HISTORY=".history";
	private static final String SEP_MEMBER="%2F";
	private static final String CN="UScoped.";
	private static final int[] ALERT_BTN_LABEL_IDS={R.string.OK,R.string.NoTryAgain,R.string.Cancel};//~vae0R~
    private static final String[] COLOMN_DOCFILE=                  //~vae0I~
				{DocumentsContract.Document.COLUMN_DOCUMENT_ID,    //~vae0I~
                 DocumentsContract.Document.COLUMN_DISPLAY_NAME,   //~vae0I~
                 DocumentsContract.Document.COLUMN_LAST_MODIFIED,  //~vae0I~
                 DocumentsContract.Document.COLUMN_SIZE,           //~vae0I~
                 DocumentsContract.Document.COLUMN_MIME_TYPE,      //~vae0I~
                };                                                 //~vae0I~
                                                                   //~vae0I~
	private ContentResolver CR;
	private String saveDirTop;
	private Uri uriSaveDir;
    private DocumentFile docDir;
    private boolean swScopedTest=false; //TODO test                //~vae0R~
    private boolean swInitialized;
    private boolean swUseInternal;                                 //~vae0I~
    private boolean swNoOverride;                                  //~vae0I~
    public  boolean swCouldNotOverride;                            //~vaidI~
//********************************************************
    public UScoped()
    {
        if (Dump.Y) Dump.println(CN+"constructor osVersion="+AG.osVersion+",swScopedTest="+swScopedTest);
        AG.aUScoped=this;
        if (swScopedTest
        ||  AG.osVersion>= Build.VERSION_CODES.R) //>=Android-11 api30
        try
        {
	        init();
        }
        catch(NoClassDefFoundError e)
        {
            Dump.println(e,CN+"constructor NoClassDefFoundError");
        }
    }
//********************************************************
    private void init()
    {
        if (Dump.Y) Dump.println(CN+"init");
        AG.swScoped=true;                                          //~vae0I~
        saveDirTop=AG.appNameE;	//default                          //~vae0R~
	    CR=AG.context.getContentResolver();
        swUseInternal=Utils.getPreference(PREFKEY_SAVE_DIR_INTERNAL,0)!=0;//~vae0I~
    	if (Dump.Y) Dump.println(CN+"init swUseInternal="+swUseInternal);//~vae0I~
//TODO test start
    	File fd;
    	fd=AG.context.getCacheDir();
    	if (Dump.Y) Dump.println(CN+"init CacheDir="+fd.getAbsolutePath());
    	fd=AG.context.getExternalCacheDir();
    	if (Dump.Y) Dump.println(CN+"init ExternalCacheDir="+fd.getAbsolutePath());
    	fd=AG.context.getExternalFilesDir("cfgs");
    	if (Dump.Y) Dump.println(CN+"init context.getExternnalFilesDir="+fd.getAbsolutePath());
        if (AG.osVersion>=29) //allow on 29:android10 by manifest: application-->requestLagacyExternalStorage="true" (ignored when target=androd11)
	    	chkExternalDir();
//TODO test end
        swInitialized=true;
    }
//********************************************************
//*true activity result from MainActivity by ACTIVITY_RESULT       //~vae0R~
//********************************************************
	private void initComp(Uri PuriDir)
    {
        if (Dump.Y) Dump.println(CN+"initComp uriDir="+PuriDir);
        uriSaveDir=PuriDir;
		confirmPermission(uriSaveDir,true/*swAction*/);
		docDir=DocumentFile.fromTreeUri(AG.context,uriSaveDir);
//      AG.swScoped=true;                                          //~vae0R~
//      AG.swScopedGranted=true;                                   //~vae0R~
//      chkReadPermission();                                       //~vae0R~
		initComp();                                                //~vae0I~
    }
//********************************************************
//*by saved strUri at APP restart
//********************************************************
	private void initComp(String PstrUri)
    {
        if (Dump.Y) Dump.println(CN+"initComp strUri="+PstrUri);
        uriSaveDir=Uri.parse(PstrUri);
		boolean rc=confirmPermission(uriSaveDir,false/*swAction*/);
        if (!rc)
        {
			requestDocumentTree(false/*swInstall*/);
        	return;
        }
		docDir=DocumentFile.fromTreeUri(AG.context,uriSaveDir);
//      AG.swScoped=true;                                          //~vae0R~
		initComp();                                                //~vae0I~
	}                                                              //~vae0I~
//********************************************************         //~vae0I~
	private void initComp()                                        //~vae0I~
	{                                                              //~vae0R~
        if (Dump.Y) Dump.println(CN+"initComp");                   //~vae0I~
        String nm=uriSaveDir.getPath();                            //~vae0R~
        int pos=nm.lastIndexOf(":");                               //~vae0I~
        if (pos>=0)                                                //~vae0I~
        	nm=nm.substring(pos+1);                                //~vae0I~
        saveDirTop=nm;                                             //~vae0I~
        if (Dump.Y) Dump.println(CN+"initComp dirTop="+saveDirTop);    //~vae0I~
        AG.swScopedGranted=true;                                   //~vae0I~
        if (chkReadPermission())                                   //~vae0R~
        	if (AG.osVersion==Build.VERSION_CODES.R) // migration available at Android-11 api30 only//~vae0I~
		        UFile.transferSDToScoped();                        //~vae0R~
    }
//********************************************************         //~vae0I~
	private boolean chkReadPermission()                            //~vae0R~
    {                                                              //~vae0I~
        if (Dump.Y) Dump.println(CN+"chkReadPermission");          //~vae0I~
    	boolean rc=UFile.chkExternalStoragePermissionOnScoped();   //~vae0R~
        if (Dump.Y) Dump.println(CN+"chkReadPermission rc="+rc);   //~vae0I~
        return rc;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
//*from Main                                                       //~vae0I~
//********************************************************         //~vae0I~
	public void grantedExternalStorageRead(boolean Pgranted)       //~vae0R~
    {                                                              //~vae0I~
        if (Dump.Y) Dump.println(CN+"UScoped.grantedExternalStorageRead granted="+Pgranted);//~vae0R~
        if (Pgranted)                                              //~vae0I~
	        UFile.transferSDToScoped();                            //~vae0I~
    }                                                              //~vae0I~
//********************************************************
	private boolean confirmPermission(Uri Puri,boolean PswAction)
    {
        if (Dump.Y) Dump.println(CN+"confirmPermission swAction="+PswAction+",uri="+Puri);
        boolean rc=false;
		rc=chkPermission(Puri);
        if (PswAction)
        {
	        int flags=Intent.FLAG_GRANT_READ_URI_PERMISSION
    	    		| Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
        	CR.takePersistableUriPermission(Puri,flags);  //remember across device reboot
			rc=chkPermission(Puri);
        }
        if (Dump.Y) Dump.println(CN+"confirmPermission rc="+rc);
        return rc;
    }
//********************************************************
	private boolean chkPermission(Uri Puri)
    {
    	boolean rc=false;
	    if (Dump.Y) Dump.println(CN+"chkPermission uri="+Puri);
//        List<UriPermission> list1=CR.getOutgoingPersistedUriPermissions();
//        if (Dump.Y) Dump.println(CN+"chkPermission outGoingPersisted list size="+list1.size());
//        for (UriPermission p:list1)
//        {
//            if (Dump.Y) Dump.println(CN+"chkPermission getOutgoingPerssistedUriPermission permision="+p.toString()+",uri="+p.getUri());
//        }
    	List<UriPermission> list2=CR.getPersistedUriPermissions(); //persisted by this app
	    if (Dump.Y) Dump.println(CN+"chkPermission persisted list size="+list2.size());
        for (UriPermission p:list2)
        {
	        if (Dump.Y) Dump.println(CN+"chkPermission getPerssistedUriPermission permision="+p.toString()+",uri="+p.getUri());
            if ((Puri.toString()).compareTo(p.getUri().toString())==0)
            	rc=true;
        }
	    if (Dump.Y) Dump.println(CN+"chkPermission rc="+rc);
        return rc;
    }
//*****************************************************************************//~vae0R~
//*rc=-1:no Scoped Stroage use(API<30), 0:Use Scoped(APi>=30), 1:grnated//~vae0I~
//*****************************************************************************//~vae0I~
//	public static boolean isScoped()                               //~vae0R~
  	public static int chkScoped()                                  //~vae0I~
    {
//  	UScoped us=getInstance();                                  //~vae0R~
//      boolean rc=us!=null && us.swInitialized;                   //~vae0R~
        int rc=-1;                                                 //~vae0I~
        if (AG.swScopedGranted)                                    //~vae0I~
            rc=1;                                                  //~vae0I~
        else                                                       //~vae0I~
        if (AG.swScoped)                                           //~vae0I~
            rc=0;                                                  //~vae0I~
        if (Dump.Y) Dump.println(CN+"chkScoped rc="+rc+",osVersion="+AG.osVersion);//~vae0R~
        return rc;
    }
  	public static boolean isScoped()                               //~vae0I~
    {                                                              //~vae0I~
        boolean rc=chkScoped()>0;                                  //~vae0I~
        if (Dump.Y) Dump.println(CN+"isScoped rc="+rc);            //~vae0I~
        return rc;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************
	private void chkExternalDir()
    {
	    if (Dump.Y) Dump.println(CN+"chkExternalDir appNameE="+AG.appNameE);//~1ak0I~
    	File[] fds;
    	fds=AG.context.getExternalFilesDirs("AG.appNameE");        //~1ak0R~
        for (File f:fds)
        {
        	if (f==null)
            	continue;
	    	if (Dump.Y) Dump.println(CN+"chkExternalDir context.getExternnalFilesDirs="+f.getAbsolutePath());//~1ak0R~
        }
    }
//********************************************************
	private void requestDocumentTree(boolean PswInstall)
    {
	    if (Dump.Y) Dump.println(CN+"requestDocumentTree swUseInternal="+swUseInternal);//~vae0R~
        if (swUseInternal)                                        //~vae0I~
        {                                                          //~vae0I~
        	chkReadPermission();                                   //~vae0I~
        	return;                                                //~vae0I~
        }                                                          //~vae0I~
    	int flag= Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE|Alert.BUTTON_CLOSE;//~vae0R~
        int msgid;
        msgid=R.string.RequestPickupDocumentSaveDir;
//      Alert.simpleAlertDialog(this/*callback AlertI*/,null/*title*/,Utils.getStr(msgid,saveDirTop),flag);//~1ak0R~
        Alert.showAlert(-1/*title*/,Utils.getStr(msgid,saveDirTop),flag,this/*callback*/,ALERT_BTN_LABEL_IDS);//~vae0R~
    }
    @Override	//AlertI
    public int alertButtonAction(int Pbuttonid,int PitemPos)
    {
    	if (Dump.Y) Dump.println(CN+"alertButtonAction buttonid="+Integer.toHexString(Pbuttonid)+",itempos="+PitemPos);
        if (Pbuttonid==Alert.BUTTON_POSITIVE)
    		startPicker();
        else
        if (Pbuttonid==Alert.BUTTON_CLOSE)    //fix No             //~vae0R~
        {                                                          //~vae0I~
          	UView.showToastLong(R.string.RequestPickupDocumentSaveDirCanceledNoTryAgain);//~vae0I~
            Utils.putPreference(PREFKEY_SAVE_DIR_INTERNAL,1);      //~vae0I~
	        chkReadPermission();                                   //~vae0I~
        }                                                          //~vae0I~
        else        //canceled                                     //~vae0R~
        {                                                          //~vae0I~
          	UView.showToastLong(R.string.RequestPickupDocumentSaveDirCanceled);
	        chkReadPermission();                                   //~vae0I~
        }                                                          //~vae0I~
    	return 1;
    }
//********************************************************
	private void startPicker()
    {
	    if (Dump.Y) Dump.println(CN+"startPicker");
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        int flags=Intent.FLAG_GRANT_READ_URI_PERMISSION
        		| Intent.FLAG_GRANT_WRITE_URI_PERMISSION
				| Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION;	//persistent thru device restart
        intent.addFlags(flags);
//      AG.aMain.startActivityForResult(intent,AG.ACTIVITY_REQUEST_SCOPED_OPEN_TREE);//~1ak0R~
        AG.aMainActivity.startActivityForResult(intent,AG.ACTIVITY_REQUEST_SCOPED_OPEN_TREE);//~1ak0I~
    }
//********************************************************
	public void onActivityResult(int Prequest, int Presult, Intent Pdata)
    {
        try
        {
			activityResult(Prequest,Presult,Pdata);
        }
        catch(Exception e)
        {
            Dump.println(e,CN+"onActivityResult");
        }
    }
//********************************************************
	private void activityResult(int Prequest, int Presult, Intent Pdata)
    {
	    if (Dump.Y) Dump.println(CN+"openTree result="+Presult+",request="+Prequest+",intent="+Pdata);//~vae0R~
//      Pmain.onActivityResult(Presult,Prequest,Pdata);
        switch(Prequest)
        {
//      case AG.ACTIVITY_REQUEST_SCOPED_OPEN_TREE:                 //~1ak0R~
        case ACTIVITY_REQUEST_SCOPED_OPEN_TREE:                    //~1ak0I~
          if (Pdata==null)                                         //~vae0I~
          {                                                        //~vae0I~
                UView.showToastLong(R.string.ErrCanceledScopedPermission);//~vae0I~
          }                                                        //~vae0I~
          else                                                     //~vae0I~
          {                                                        //~vae0I~
            Uri uri=Pdata.getData();                               //~1ak0I~
        	if (Presult== Activity.RESULT_OK) //-1
            {
				initComp(uri);
				treeOpened(uri);
                UFile.transferSDToScoped();                        //~vae0I~
            }
            else
            {
                UView.showToastLong(Utils.getStr(R.string.ErrNoScopedPermission,uri.toString()));//~1ak0R~
            }
          }                                                        //~vae0I~
            UFile.chkExternalStoragePermissionOnScoped();           //~vae0I~
            break;
        }
    }
//********************************************************         //~vae0I~
	public String getDirPath()                                     //~vae0R~
    {                                                              //~vae0I~
    	String path="";                                            //~vae0I~
		if (uriSaveDir!=null)                                      //~vae0I~
        {                                                          //~vae0I~
        	path=saveDirTop;                                       //~vae0R~
        }                                                          //~vae0I~
	    if (Dump.Y) Dump.println(CN+"getDirPath path="+path);      //~vae0I~
        return path;                                               //~vae0I~
    }                                                              //~vae0I~
//********************************************************
	private void treeOpened(Uri Puri)
    {
                String nm=Puri.getPath();
			    if (Dump.Y) Dump.println(CN+"treeOpened uri="+Puri+",path="+Puri.getPath());//~vae0I~
                if (!nm.endsWith(saveDirTop))
                {
                    int pos=nm.lastIndexOf(":");
                    if (pos>=0)
                    	nm=nm.substring(pos+1);
	            	UView.showToastLong(Utils.getStr(R.string.RequestPickupDocumentSaveDirInvalidSelection,nm,
                            saveDirTop));
//                	return;                                        //~vae0R~
                }
			    if (Dump.Y) Dump.println(CN+"treeOpened uri="+Puri+",path="+Puri.getPath()+",scheme="+Puri.getScheme());//~vae0R~
//                File f=new File(Puri.getPath());                 //~vae0R~
//                if (Dump.Y) Dump.println(CN+"onActivityResult uri to file="+f.toString()+",path="+f.getPath()+",absolutePath="+f.getAbsolutePath());//~vae0R~
////TODO test     getPath2(Puri);                                  //~vae0R~
//                getPath(Puri);                                   //~vae0R~
//                getTreeMember(Puri,FILTER_HISTORY); //good performance//~vae0R~
////              Prop.putPreference(PREFKEY_SAVE_DIR,Puri.toString());//~1ak0R~//~vae0R~
                Utils.putPreference(PREFKEY_SAVE_DIR,Puri.toString());//~1ak0I~
                  DocumentFile[] docs=listDir(uriSaveDir); //TODO test   //slow, bad performance//~vae0R~
//                if (docs.length>0)                               //~vae0R~
//                    readDocument(docs[0]);                       //~vae0R~
//                if (docs.length>1)                               //~vae0R~
//                    readDocument(docs[1]);                       //~vae0R~
//              writeDocument(Puri,docs);
//                writeDocument("Dump10.txt","Over");              //~vae0R~
//                readDocument("Dump10.txt");                      //~vae0R~
//              createDocument(Puri);
    }
//********************************************************
//*TODO test,slow bad performance
//********************************************************
	public DocumentFile[] listDir(Uri Puri)
    {
	    if (Dump.Y) Dump.println(CN+"listDir uri="+Puri);
//      DocumentFile tree=DocumentFile.fromTreeUri(AG.context,Puri);
        Uri withFilter=Uri.withAppendedPath(Puri,"*.history");
//  	Uri withFilter=getMemberUri(Puri,null,"*.history");
		if (Dump.Y) Dump.println(CN+"listDir uri with filter="+withFilter);
        DocumentFile tree=DocumentFile.fromTreeUri(AG.context,withFilter);
        DocumentFile[] docs=tree.listFiles();
        for (DocumentFile doc:docs)
        {
		    if (Dump.Y) Dump.println(CN+"listDir doc="+doc.getName()+",lastModified="+Utils.getTimeStamp("yy.MM.dd-HH.mm.ss",doc.lastModified())+",uri="+doc.getUri());
        }
        return docs;
    }
//********************************************************
	public void readDocument(DocumentFile Pdoc)
    {
	    if (Dump.Y) Dump.println(CN+"readDocument Pdoc uri="+Pdoc.getUri());
	    if (Dump.Y) Dump.println(CN+"readDocument canRead="+Pdoc.canRead()+",canWrite="+Pdoc.canWrite()+",name="+Pdoc.getName()+",type="+Pdoc.getType()+",isFile="+Pdoc.isFile());
        if (!Pdoc.isFile())
        {
	    	if (Dump.Y) Dump.println(CN+"readDocument return it is not File");
        	return;
        }
        int lineno=0;
        try
        {
        	InputStream is=CR.openInputStream(Pdoc.getUri());
        	InputStreamReader isr=new InputStreamReader(is);
        	BufferedReader br=new BufferedReader(isr);
            for (;;)
            {
            	String line=br.readLine();
                if (line==null)
                	break;
                lineno++;
			    if (Dump.Y) Dump.println(CN+"readDocument lineno="+lineno+"="+line);
            }
            br.close();
        }
        catch(FileNotFoundException e)
        {
        	Dump.println(e,CN+"readDocument FileNotFound:"+Pdoc.getUri());
        }
        catch(IOException e)
        {
        	Dump.println(e,CN+"readDocument IOException:"+Pdoc.getUri());
        }
	    if (Dump.Y) Dump.println(CN+"readDocument exit lineno="+lineno);
    }
//********************************************************
	private void writeDocument(Uri Puri,DocumentFile[] Pdocs)
    {
	    if (Dump.Y) Dump.println(CN+"writeDocument uri="+Puri+",memberCount="+Pdocs.length);
//      Uri memberUri=Uri.withAppendedPath(Puri,"%2Ftest1.txt");
        DocumentFile doc0=null;
		if (Pdocs.length>0)
        	doc0=Pdocs[0];
//      Uri memberUri=getMemberUri(Puri,doc0,"Dump.txt");
        Uri memberUri=getMemberUri(Puri,"Dump.txt");
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,memberUri);
	    if (Dump.Y) Dump.println(CN+"writeDocument canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile());
        String data="testData";
	    writeDocument(memberUri,data);
		readDocument(doc);
    }
////********************************************************       //~vae0R~
//    private Uri getMemberUri(Uri Puri/*tree*/,DocumentFile Pdoc0,String Pmember)//~vae0R~
//    {                                                            //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getMemberUri uri="+Puri+",member="+Pmember+",member1="+Utils.toString(Pdoc0));//~vae0R~
//        Uri memberUri;                                           //~vae0R~
//        if (Pdoc0!=null)                                         //~vae0R~
//        {                                                        //~vae0R~
//            String content0=Pdoc0.getUri().toString();           //~vae0R~
//            String strUri=Pdoc0.getUri().toString();             //~vae0R~
//            String name=Pdoc0.getName();                         //~vae0R~
//            int pos=strUri.lastIndexOf(name);                    //~vae0R~
//            String strUriNew=strUri.substring(0,pos)+Pmember;    //~vae0R~
//            if (Dump.Y) Dump.println(CN+"getMemberUri strUri="+strUri+",name="+name+",pos="+pos);//~vae0R~
//            memberUri=Uri.parse(strUriNew);                      //~vae0R~
//        }                                                        //~vae0R~
//        else                                                     //~vae0R~
//        {                                                        //~vae0R~
//            String parent=Puri.toString();                       //~vae0R~
//            int pos=parent.indexOf("/tree/");                    //~vae0R~
//            String member=parent+"/document/"+parent.substring(pos+6)+SEP_MEMBER+Pmember;//~vae0R~
//            memberUri=Uri.parse(member);                         //~vae0R~
//        }                                                        //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getMemberUri uri="+memberUri);//~vae0R~
//        return memberUri;                                        //~vae0R~
//    }                                                            //~vae0R~
//********************************************************         //~vae0I~
	public ArrayList<TreeMember> getTreeMember(String Ppath, String Pfilter)//~vae0I~
    {                                                              //~vae0I~
 	    if (Dump.Y) Dump.println(CN+"getTreeMember path="+Ppath+",filetr="+Pfilter);//~vae0I~
		return getTreeMember(uriSaveDir,Pfilter);                  //~vae0I~
    }                                                              //~vae0I~
//********************************************************
	private ArrayList<TreeMember> getTreeMember(Uri Puri, String Pfilter)
    {
    	int ctrLine=0;
 	    if (Dump.Y) Dump.println(CN+"getTreeMember uri="+Puri+",getPath="+Puri.getPath()+",filetr="+Pfilter);
        String[] columns=COLOMN_DOCFILE;                           //~vae0R~
        int[] idxCol=new int[columns.length];
        Uri uriQuery=DocumentsContract.buildChildDocumentsUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));
        Cursor cursor=CR.query(uriQuery,columns,null,null,null);
        if (Dump.Y) Dump.println(CN+"getTreeMember uriQuery="+uriQuery+",cursor="+Utils.toString(cursor));
//      int ctrSelected=0;
        ArrayList<TreeMember> members=new ArrayList<TreeMember>(0/*initial ctr*/);
        if (cursor!=null && cursor.moveToFirst())
        {
        	for (int ii=0;ii<columns.length;ii++)
            {
            	idxCol[ii]=cursor.getColumnIndex(columns[ii]);
		        if (Dump.Y) Dump.println(CN+"getTreeMember colidx ii="+ii+",col="+columns[ii]+",idx="+idxCol[ii]);
            }
            ctrLine=cursor.getCount();
//      	members=new TreeMember[ctrLine];
        	members=new ArrayList<TreeMember>(ctrLine);
		    if (Dump.Y) Dump.println(CN+"getTreeMember ctrLine="+ctrLine);
            for (int ii=0;ii<ctrLine;ii++)
            {
//              for (int jj=0;jj<idxCol.length;jj++)
//              {
//              	if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",jj="+jj+",idx="+idxCol[jj]+",value="+columns[jj]+"="+cursor.getString(jj));
//              }
				String nm=cursor.getString(idxCol[1]);
                if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",name="+nm+",mimeType="+cursor.getString(idxCol[4]));//~vae0R~
                if (Pfilter==null || nm.endsWith(Pfilter))
                {
//              	members[ctrSelected]=new TreeMember(nm,cursor.getLong(idxCol[2]),cursor.getLong(idxCol[3]));	//displayname,LastModified,size
                	TreeMember tm=new TreeMember(nm,cursor.getLong(idxCol[2]),cursor.getLong(idxCol[3]));	//displayname,LastModified,size
                	members.add(tm);
                	if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",TreeMember="+tm.toString()+",mimeType="+cursor.getString(idxCol[4]));//~vae0R~
//                  ctrSelected++;
                }
                cursor.moveToNext();
            }
//            if (ctrSelected!=ctrLine)
//            {
//                if (ctrSelected!=0)
//                {
//                    TreeMember[] membersSelected=new TreeMember[ctrSelected];
//                    System.arraycopy(members,0,membersSelected,0,ctrSelected);
//                    members=membersSelected;
//                }
//                else
//                    members=null;
//            }
        }
		if (Dump.Y) Dump.println(CN+"getTreeMember exit members size="+members.size());
        return members;
    }
//********************************************************
	private TreeMember[] getTreeMember(Uri Puri)
    {
    	int ctrLine=0;
        TreeMember[] members=null;
	    if (Dump.Y) Dump.println(CN+"getTreeMember uri="+Puri+",getPath="+Puri.getPath());
        String[] columns=COLOMN_DOCFILE;                           //~vae0I~
        int[] idxCol=new int[columns.length];
        Uri uriQuery=DocumentsContract.buildChildDocumentsUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));
        Cursor cursor=CR.query(uriQuery,columns,null,null,null);
        if (Dump.Y) Dump.println(CN+"getTreeMember uriQuery="+uriQuery+",cursor="+Utils.toString(cursor));
        if (cursor!=null && cursor.moveToFirst())
        {
        	for (int ii=0;ii<columns.length;ii++)
            {
            	idxCol[ii]=cursor.getColumnIndex(columns[ii]);
		        if (Dump.Y) Dump.println(CN+"getTreeMember colidx ii="+ii+",col="+columns[ii]+",idx="+idxCol[ii]);
            }
            ctrLine=cursor.getCount();
        	members=new TreeMember[ctrLine];
		    if (Dump.Y) Dump.println(CN+"getTreeMember ctrLine="+ctrLine);
            for (int ii=0;ii<ctrLine;ii++)
            {
//              for (int jj=0;jj<idxCol.length;jj++)
//              {
//              	if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",jj="+jj+",idx="+idxCol[jj]+",value="+columns[jj]+"="+cursor.getString(jj));
//              }
                members[ii]=new TreeMember(cursor.getString(idxCol[1]),cursor.getLong(idxCol[2]),cursor.getLong(idxCol[3]));	//displayname,LastModified,size
                if (Dump.Y) Dump.println(CN+"getTreeMember ii="+ii+",TreeMember="+members[ii].toString());
                cursor.moveToNext();
            }
        }
		if (Dump.Y) Dump.println(CN+"getTreeMember exit members="+Utils.toString(members));
        return members;
    }
////********************************************************       //~vae0R~
//    private String getPath(Uri Puri)                             //~vae0R~
//    {                                                            //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath uri="+Puri+",getPath="+Puri.getPath());//~vae0R~
//        File f=new File(Puri.getPath());                         //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath f="+f.toString());  //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath file="+f.getPath()+",abs="+f.getAbsolutePath());//~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath exist="+f.exists()+",name="+f.getName()+",isFile="+f.isFile()+",isDir="+f.isDirectory()+",isAbsolute="+f.isAbsolute());//~vae0R~
// //       f=Puri.toFile();                                       //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath uri.toFile exist="+f.exists()+",name="+f.getName()+",isFile="+f.isFile()+",isDir="+f.isDirectory()+",isAbsolute="+f.isAbsolute());//~vae0R~

//        try                                                      //~vae0R~
//        {                                                        //~vae0R~
//            if (Dump.Y) Dump.println(CN+"getPath canonicalPath="+f.getCanonicalPath());//~vae0R~
//        }                                                        //~vae0R~
//        catch (IOException e)                                    //~vae0R~
//        {                                                        //~vae0R~
//            Dump.println(CN+"getPath canonicalPath");            //~vae0R~
//        }                                                        //~vae0R~
//        File[] listF;                                            //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath list="+Utils.toString(f.list(null)));//~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath listFiles="+Utils.toString(f.listFiles()));//~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath listFiles="+Utils.toString(f.listRoots()));//~vae0R~
//        listF=f.listRoots();                                     //~vae0R~
//        for (File lf:listF)                                      //~vae0R~
//            if (Dump.Y) Dump.println(CN+"getPath listRoot="+lf+",path="+lf.getPath());//~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath toPath="+f.toPath()); //API26//~vae0R~
//        String path="";                                          //~vae0R~
//        if (isContentUri(Puri))                                  //~vae0R~
//        {                                                        //~vae0R~
//            String[] columns;                                    //~vae0R~
////          columns={"_data"};                                   //~vae0R~
////          columns=null;                                        //~vae0R~
////          columns={DocumentsContract.Document.COLUMN_DISPLAY_NAME,//~vae0R~
////                            DocumentsContract.Document.COLUMN_DOCUMENT_ID,//~vae0R~
////                            DocumentsContract.Document.COLUMN_LAST_MODIFIED,//~vae0R~
////                            };                                 //~vae0R~
//            columns=null;                                        //~vae0R~
////          Cursor cursor=CR.query(Puri,columns,null,null,null); //~vae0R~
//            Uri uriQuery=DocumentsContract.buildDocumentUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~vae0R~
////          Uri uriQuery=getMemberUri(Puri,null/*Pmember*/);     //~vae0R~
//            String auth=uriQuery.getAuthority();                 //~vae0R~
//            if (Dump.Y) Dump.println(CN+"getPath uriQuery="+uriQuery+",auth="+auth);//~vae0R~
//            if (Dump.Y) Dump.println(CN+"getPath buildDocumentUriUsingTree getDocumentId="+DocumentsContract.getDocumentId(uriQuery));//~vae0R~
////          if (Dump.Y) Dump.println(CN+"getPath Puri getDocumentId="+DocumentsContract.getDocumentId(Puri));//~vae0R~
//            Cursor cursor=CR.query(uriQuery,columns,null,null,null);//~vae0R~
//            if (Dump.Y) Dump.println(CN+"getPath cursor="+Utils.toString(cursor));//~vae0R~
//            String parentDocID=null;                             //~vae0R~
//            if (cursor!=null && cursor.moveToFirst())            //~vae0R~
//            {                                                    //~vae0R~
//                int idx;                                         //~vae0R~
////              idx=cursor.getColumnIndex(columns[0]);           //~vae0R~
//                int ctr=cursor.getColumnCount();                 //~vae0R~
//                if (Dump.Y) Dump.println(CN+"getPath colmnNames="+Utils.toString(cursor.getColumnNames()));//~vae0R~

//                for (idx=0;idx<ctr;idx++)                        //~vae0R~
//                    if (!cursor.isNull(idx))                     //~vae0R~
//                        if (Dump.Y) Dump.println(CN+"getPath idx="+idx+",column="+cursor.getColumnName(idx)+",value="+cursor.getString(idx));//~vae0R~
//                if (Dump.Y) Dump.println(CN+"getPath  cursor.getCount="+cursor.getCount());//~vae0R~
//                parentDocID=cursor.getString(0);                 //~vae0R~
//                cursor.close();                                  //~vae0R~
//            }                                                    //~vae0R~
////          Uri uriChild=DocumentsContract.buildChildDocumentsUri(auth,parentDocID);//~vae0R~
////          cursor=CR.query(uriChild,columns,null,null,null);    //~vae0R~
////good performance                                               //~vae0R~
//            Uri uriChild=DocumentsContract.buildChildDocumentsUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));//~vae0R~
//            if (Dump.Y) Dump.println(CN+"getPath uriChild="+uriQuery);//~vae0R~
//            cursor=CR.query(uriChild,columns,null,null,null);    //~vae0R~
//            if (cursor!=null && cursor.moveToFirst())            //~vae0R~
//            {                                                    //~vae0R~
//                if (Dump.Y) Dump.println(CN+"getPath  child cursor.getCount="+cursor.getCount());//~vae0R~
//                int ctr=cursor.getColumnCount();                 //~vae0R~
//                if (Dump.Y) Dump.println(CN+"getPath colmnNames="+Utils.toString(cursor.getColumnNames()));//~vae0R~
//                for (int ii=0;ii<cursor.getCount();ii++)         //~vae0R~
//                {                                                //~vae0R~
//                    for (int idx=0;idx<ctr;idx++)                //~vae0R~
//                        if (!cursor.isNull(idx))                 //~vae0R~
//                            if (Dump.Y) Dump.println(CN+"getPath idx="+idx+",column="+cursor.getColumnName(idx)+",value="+cursor.getString(idx));//~vae0R~
////                  if (Dump.Y) Dump.println(CN+"getPath ii="+ii+",column[0]="+cursor.getColumnName(0)+",value="+cursor.getString(0));//~vae0R~
//                    cursor.moveToNext();                         //~vae0R~
//                }                                                //~vae0R~
//            }                                                    //~vae0R~
//        }                                                        //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath path="+path);       //~vae0R~
//        return path;                                             //~vae0R~
//    }                                                            //~vae0R~
////********************************************************       //~vae0R~
//    private String getPath2(Uri Puri)                            //~vae0R~
//    {                                                            //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath uri="+Puri);        //~vae0R~
//        String path="";                                          //~vae0R~
//        if (isContentUri(Puri))                                  //~vae0R~
//        {                                                        //~vae0R~
//            String[] columns=null;                               //~vae0R~
//            Cursor csr=CR.query(Puri,columns,null,null,null);    //~vae0R~
//            if (Dump.Y) Dump.println(CN+"getPath2 colomns="+ Utils.toString(csr.getColumnNames()));//~vae0R~
//            int ctr=csr.getColumnCount();                        //~vae0R~
//            while(csr.moveToNext())                              //~vae0R~
//            {                                                    //~vae0R~
//                for (int ii=0;ii<ctr;ii++)                       //~vae0R~
//                    if (Dump.Y) Dump.println(CN+"getPath2 getString("+ii+")="+csr.getString(ii));//~vae0R~
//            }                                                    //~vae0R~
//            csr.close();                                         //~vae0R~
//        }                                                        //~vae0R~
//        if (Dump.Y) Dump.println(CN+"getPath2 path="+path);      //~vae0R~
//        return path;                                             //~vae0R~
//    }                                                            //~vae0R~
//********************************************************
	private boolean isDocumentUir(Uri Puri)
    {
	    if (Dump.Y) Dump.println(CN+"isDocumentUri uri="+Puri);
        boolean rc= DocumentFile.isDocumentUri(AG.context,Puri);
	    if (Dump.Y) Dump.println(CN+"isDocumentUri rc="+rc);
        return rc;
    }
//********************************************************
	private boolean isContentUri(Uri Puri)
    {
	    if (Dump.Y) Dump.println(CN+"isContentUri uri="+Puri);
        String scheme=Puri.getScheme();
        boolean rc="content".equalsIgnoreCase(scheme);
	    if (Dump.Y) Dump.println(CN+"isContentUri rc="+rc+",scheme="+scheme);
        return rc;
    }
//********************************************************
	private boolean isFileUri(Uri Puri)
    {
	    if (Dump.Y) Dump.println(CN+"isFileUri uri="+Puri);
        String scheme=Puri.getScheme();
        boolean rc="file".equalsIgnoreCase(scheme);
	    if (Dump.Y) Dump.println(CN+"isFileUri rc="+rc+",scheme="+scheme);
        return rc;
    }
//********************************************************
    public class TreeMember
    {
        public String name; long size;                             //~vae0R~
        public String lastModified;                                //~vae0R~
        public TreeMember(String Pname,long PlastModified,long Psize)
        {
        	name=Pname;  size=Psize;
            lastModified=Utils.getTimeStamp("yy.MM.dd-HH.mm.ss",PlastModified);
        }
        public String toString()
        {
        	return "namd="+name+",size="+size+",lastModified="+lastModified;
        }
    }
//********************************************************
//********************************************************
//********************************************************
    private static UScoped getInstance()
    {
        UScoped us=AG.aUScoped;
	    if (Dump.Y) Dump.println(CN+"getInstance AG.aUScoped="+Utils.toString(us));
        if (us==null)
        	us=new UScoped();
        return us;
    }
//********************************************************
    public static boolean isSDMounted()
    {
        UScoped us=getInstance();
        boolean rc=us.chkDocumentSaveDir();
	    if (Dump.Y) Dump.println(CN+"isSDMounted rc="+rc);
        return rc;
    }
//********************************************************
//AMain-->UFile
//********************************************************
    public static boolean chkWritableSD()
    {
        UScoped us=getInstance();
        boolean rc=us.chkDocumentSaveDir();
	    if (Dump.Y) Dump.println(CN+"chkWritableSD rc="+rc);
        return rc;
    }
//********************************************************
//*return uri.toString()
//********************************************************
    public static String getSDPath(String Pfnm)
    {
        UScoped us=getInstance();
        String strUri=us.getMemberPath(Pfnm);
	    if (Dump.Y) Dump.println(CN+"getSDPath fnm="+Pfnm+",path="+strUri);
        return strUri;
    }
//********************************************************
    public static OutputStream openOutputSD(String Pmember,boolean PswDeleteExisting)
    {
        UScoped us=getInstance();
        Uri uri=us.uriSaveDir;
	    if (Dump.Y) Dump.println(CN+"openOutputSD uriSaveDir="+Utils.toString(uri));
        if (uri==null)
        	return null;
        Uri memberUri=us.getMemberUri(uri,Pmember);
        OutputStream os=us.openOutputDocument(memberUri,Pmember);
	    if (Dump.Y) Dump.println(CN+"openOutputSD rc="+Utils.toString(os));
        return os;
    }
//********************************************************
    public static InputStream openInputSD(String Pmember)
    {
        UScoped us=getInstance();
        Uri uri=us.uriSaveDir;
	    if (Dump.Y) Dump.println(CN+"openInputSD uriSaveDir="+Utils.toString(uri));
        if (uri==null)
        	return null;
        Uri memberUri=us.getMemberUri(uri,Pmember);
        InputStream is=us.openInputDocument(memberUri);
	    if (Dump.Y) Dump.println(CN+"openInputSD rc="+Utils.toString(is));
        return is;
    }
//********************************************************
//** rc :true yet confirmed, false:requested confirm
//********************************************************
	public boolean chkDocumentSaveDir()
    {
    	boolean rc=false;
	    if (Dump.Y) Dump.println(CN+"chkDocumentSaveDir");
//      String strUri= Prop.getPreference(PREFKEY_SAVE_DIR,"");    //~1ak0R~
        String strUri= Utils.getPreference(PREFKEY_SAVE_DIR,"");   //~1ak0I~
		if (Dump.Y) Dump.println(CN+"chkDocumentSaveDir getPreference strUri="+strUri);
        if (strUri.compareTo("")==0)	//1st time
        {
			requestDocumentTree(true/*swInstall*/);
        }
        else
        {
        	initComp(strUri);
		    if (Dump.Y) Dump.println(CN+"chkDocumentSaveDir uriSaveDir="+uriSaveDir);
            getTreeMember(uriSaveDir,FILTER_HISTORY);	//using query
			treeOpened(uriSaveDir);
            rc=true;
        }
	    if (Dump.Y) Dump.println(CN+"chkDocumentSaveDir rc="+rc);
        return rc;
    }
//********************************************************
	private String getMemberPath(String Pmember)
    {
	    if (Dump.Y) Dump.println(CN+"getMemberPath uri="+Utils.toString(uriSaveDir)+",member="+Pmember);
        if (uriSaveDir==null)
        	return null;
        Uri uri=getMemberUri(uriSaveDir,Pmember);
        String strUri=uri.toString();
	    if (Dump.Y) Dump.println(CN+"getMemberPath rc="+strUri);
        return strUri;
    }
//********************************************************         //~vae0M~
	private Uri getMemberUri(String Pmember)                       //~vae0M~
    {                                                              //~vae0M~
	    if (Dump.Y) Dump.println(CN+"getMemberUri member="+Pmember);//~vae0M~
		Uri uri=getMemberUri(uriSaveDir,Pmember);                  //~vae0I~
	    if (Dump.Y) Dump.println(CN+"getMemberUri uri="+uri);      //~vae0M~
        return uri;                                                //~vae0M~
    }                                                              //~vae0M~
//********************************************************
	private Uri getMemberUri(Uri Puri/*tree*/,String Pmember)
    {
	    if (Dump.Y) Dump.println(CN+"getMemberUri uri="+Puri+",member="+Pmember);
        Uri uri=DocumentsContract.buildDocumentUriUsingTree(Puri,DocumentsContract.getTreeDocumentId(Puri));
        Uri memberUri=uri;
        if (Pmember!=null)
        {
	        String strUri=uri.toString()+SEP_MEMBER+Pmember;
	    	memberUri=Uri.parse(strUri);
        }
	    if (Dump.Y) Dump.println(CN+"getMemberUri uri="+memberUri);
        return memberUri;
    }
//********************************************************
	private OutputStream openOutputStream(Uri Puri)
    {
	    if (Dump.Y) Dump.println(CN+"openOutputStream uri="+Puri);
        OutputStream os=null;
        try
        {
        	os=CR.openOutputStream(Puri);
        }
        catch(FileNotFoundException e)
        {
        	Dump.println(e,CN+"openOutputStream openOutputStream FileNotFound:"+Puri);
        }
	    if (Dump.Y) Dump.println(CN+"openOutputDocument os="+Utils.toString(os));
        return os;
    }
//********************************************************
	private void createDocument(Uri Puri/*tree*/)
    {
	    if (Dump.Y) Dump.println(CN+"createDocument uri="+Puri);
        DocumentFile tree=DocumentFile.fromTreeUri(AG.context,Puri);
        String name="Dump5.txt";
        Uri memberUri=getMemberUri(Puri,name);
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,memberUri);
	    if (Dump.Y) Dump.println(CN+"createDocument member uri="+doc.getUri()+",canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile()+",exists="+doc.exists());
        if (doc.exists())
        {
		    if (Dump.Y) Dump.println(CN+"createDocument delete exist uri="+doc.getUri());
        	doc.delete();
        }
        else
        {
		    if (Dump.Y) Dump.println(CN+"createDocument not found uri="+doc.getUri());
	    }
        doc=tree.createFile("text/plain",name);
        if (Dump.Y) Dump.println(CN+"createDocument create uri="+doc.getUri());
	    if (Dump.Y) Dump.println(CN+"createDocument uri="+doc.getUri()+",canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile());
//      String data="D3\nD4\nD5";   //todo test override
        String data="D6";   //todo test override
	    writeDocument(name,data);
	    writeDocument(doc.getUri(),data);
		readDocument(doc);
    }
//********************************************************
	private void writeDocument(String Pmember,String Pdata)
    {
        if (Dump.Y) Dump.println(CN+"writeDocument member=" + Pmember + ",data=" + Pdata);
//      OutputStream os = openOutputSD(Pmember,false/*delete existing*/);//if false ...(1) is created
        OutputStream os = openOutputSD(Pmember,true/*delete existing*/);
        if (os == null)
            return;
        OutputStreamWriter osw=null;
      	try
      	{
        	osw = new OutputStreamWriter(os, "UTF-8");
        }
        catch(UnsupportedEncodingException e)
        {
            Dump.println(e,"CN+writeDocument FileNotFound:"+Pmember);
        }
        if (osw!=null)
        {
            try
            {
                PrintWriter pw = new PrintWriter(osw, true/*autoFlush*/);
                pw.print(Pdata);
                pw.close();
            }
            catch(Exception e)
            {
                Dump.println(e,CN+"writeDocument IOException:"+Pmember);
            }
        }
        if (Dump.Y) Dump.println(CN+"writeDocument exit");
    }
//********************************************************
	private void writeDocument(Uri Puri,String Pdata)
    {
	    if (Dump.Y) Dump.println(CN+"writeDocument uri="+Puri+",data="+Pdata);
        try
        {
        	OutputStream os=CR.openOutputStream(Puri);
			OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");
			PrintWriter pw=new PrintWriter(osw,true/*autoFlush*/);
        	pw.print(Pdata);
            pw.close();
        }
        catch(FileNotFoundException e)
        {
        	Dump.println(e,CN+"writeDocument FileNotFound:"+Puri);
        }
        catch(IOException e)
        {
        	Dump.println(e,CN+"writeDocument IOException:"+Puri);
        }
	    if (Dump.Y) Dump.println(CN+"writeDocument exit");
    }
//********************************************************
	private void readDocument(String Pmember)
    {
	    if (Dump.Y) Dump.println(CN+"readDocument Member="+Pmember);
//      InputStream is=openInputSD(Pmember);                       //~vae0R~
        InputStream is=openInputDocument(Pmember);                 //~vae0I~
        if (is == null)
            return;
        int lineno=0;
        try
        {
        	InputStreamReader isr=new InputStreamReader(is);
        	BufferedReader br=new BufferedReader(isr);
            for (;;)
            {
            	String line=br.readLine();
                if (line==null)
                	break;
                lineno++;
			    if (Dump.Y) Dump.println(CN+"readDocument lineno="+lineno+"="+line);
            }
            br.close();
        }
        catch(FileNotFoundException e)
        {
        	Dump.println(e,CN+"readDocument FileNotFound:"+saveDirTop+"/"+Pmember);
        }
        catch(IOException e)
        {
        	Dump.println(e,CN+"readDocument IOException:"+saveDirTop+"/"+Pmember);
        }
	    if (Dump.Y) Dump.println(CN+"readDocument exit lineno="+lineno);
    }
//********************************************************         //~vaieI~
//* from UFile.fileToStringBuffer                                  //+vaieI~
//********************************************************         //+vaieI~
	public StringBuffer fileToStringBuffer(String Pmember)         //+vaieR~
    {                                                              //~vaieI~
	    if (Dump.Y) Dump.println(CN+"fileToStringBuffer Member="+Pmember);//~vaieI~
        InputStream is=openInputDocument(Pmember);                 //~vaieI~
        StringBuffer sb=new StringBuffer();                         //~vaieI~
        if (is == null)                                            //~vaieI~
            return null;                                           //~vaieI~
        int lineno=0;                                              //~vaieI~
        try                                                        //~vaieI~
        {                                                          //~vaieI~
        	InputStreamReader isr=new InputStreamReader(is);       //~vaieI~
        	BufferedReader br=new BufferedReader(isr);             //~vaieI~
            for (;;)                                               //~vaieI~
            {                                                      //~vaieI~
            	String line=br.readLine();                         //~vaieI~
                if (line==null)                                    //~vaieI~
                	break;                                         //~vaieI~
                lineno++;                                          //~vaieI~
                sb.append(line+"\n");                              //~vaieI~
			    if (Dump.Y) Dump.println(CN+"fileToStringBuffer lineno="+lineno+"="+line);//~vaieI~
            }                                                      //~vaieI~
            br.close();                                            //~vaieI~
        }                                                          //~vaieI~
        catch(FileNotFoundException e)                             //~vaieI~
        {                                                          //~vaieI~
        	Dump.println(e,CN+"fileToStringBuffer FileNotFound:"+saveDirTop+"/"+Pmember);//~vaieI~
        }                                                          //~vaieI~
        catch(IOException e)                                       //~vaieI~
        {                                                          //~vaieI~
        	Dump.println(e,CN+"fileToStringBuffer IOException:"+saveDirTop+"/"+Pmember);//~vaieI~
        }                                                          //~vaieI~
	    if (Dump.Y) Dump.println(CN+"fileToStringBuffer exit lineno="+lineno+",sb="+sb.toString());//~vaieI~
        return sb;                                                 //~vaieI~
    }                                                              //~vaieI~
//********************************************************         //~vae0I~
	private DocumentFile uriToDocument(Uri Puri)                   //~vae0I~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"uriToDocument uri="+Puri);    //~vae0I~
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,Puri);//~vae0I~
        return doc;                                                //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
	private DocumentFile getMemberDocument(String Pmember)         //~vae0I~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"getMemberDocument member="+Pmember);//~vae0I~
        Uri uri=getMemberUri(Pmember);                             //~vae0I~
        DocumentFile doc=uriToDocument(uri);                       //~vae0I~
        return doc;                                                //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
	public boolean deleteMember(String Pmember)                    //~vae0R~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"deleteMember Member="+Pmember);//~vae0R~
        DocumentFile doc=getMemberDocument(Pmember);               //~vae0I~
        boolean rc=doc.delete();                                   //~vae0I~
	    if (Dump.Y) Dump.println(CN+"deleteMember rc="+rc);          //~vae0R~
        return rc;
    }                                               //~vae0I~
//********************************************************         //~vae0I~
	public String getFullpath(String Pmember)                      //~vae0I~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"getFullpath Member="+Pmember);//~vae0I~
        String rc=saveDirTop+"/"+Pmember;                          //~vae0I~
	    if (Dump.Y) Dump.println(CN+"getFullPath rc="+rc);         //~vae0I~
        return rc;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
	public String parseMember(String Ppath)                        //~vae0I~
    {                                                              //~vae0I~
    	int pos=Ppath.lastIndexOf("/");                            //~vae0I~
    	String rc=Ppath.substring(pos+1);                          //~vae0R~
	    if (Dump.Y) Dump.println(CN+"parseMember member="+rc+",path="+Ppath);//~vae0I~
        return rc;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0M~
    public static OutputStreamWriter openOutputStreamWriter(String Ppath)//~vae0I~
    {                                                              //~vae0M~
        OutputStreamWriter osw=null;                               //~vae0M~
        UScoped us=getInstance();                                  //~vae0M~
        Uri uri=us.uriSaveDir;                                     //~vae0M~
	    if (Dump.Y) Dump.println(CN+"openOutputStreamWriter uriSaveDir="+Utils.toString(uri));//~vae0M~
        if (uri==null)                                             //~vae0M~
        	return null;                                           //~vae0M~
    	String member=us.parseMember(Ppath);                       //~vae0M~
        Uri memberUri=us.getMemberUri(uri,member);                 //~vae0M~
        try                                                        //~vae0M~
        {                                                          //~vae0M~
        	OutputStream os=us.openOutputDocument(memberUri,member);//~vae0R~
        	osw=new OutputStreamWriter(os,"UTF-8");                //~vae0M~
        }                                                          //~vae0M~
        catch(Exception e)                                         //~vae0M~
        {                                                          //~vae0M~
        	Dump.println(e,CN+"openOutputStreamWriter:"+member);   //~vae0M~
        }                                                          //~vae0M~
	    if (Dump.Y) Dump.println(CN+"openOutputStreamWriter rc="+Utils.toString(osw));//~vae0M~
        return osw;                                                //~vae0M~
    }                                                              //~vae0M~
//********************************************************         //~vae0M~
	private OutputStream openOutputDocument(Uri PmemberUri,String Pmember)//~vae0R~
    {                                                              //~vae0M~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument swNoOverride="+swNoOverride+",member="+Pmember+",uri="+PmemberUri);//~vae0R~
        DocumentFile doc=DocumentFile.fromSingleUri(AG.context,PmemberUri);//~vae0M~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument docuri="+doc.getUri()+",canRead="+doc.canRead()+",canWrite="+doc.canWrite()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile()+",exists="+doc.exists()+",length="+doc.length()+",name="+doc.getName());//~vae0I~
        swCouldNotOverride=false;                                  //~vaidI~
        if (doc.exists())                                          //~vae0M~
        {                                                          //~vae0M~
        	if (swNoOverride)                                      //~vae0I~
            {                                                      //~vae0I~
			   	if (Dump.Y) Dump.println(CN+"openOutputDocument return null by existing set swCouldNotOverride:true");//~vae0I~//~vaidR~
		        swCouldNotOverride=true;                           //~vaidI~
            	return null;                                       //~vae0I~
            }                                                      //~vae0I~
		   	if (Dump.Y) Dump.println(CN+"openOutputDocument delete existing uri="+doc.getUri());//~vae0I~
	        doc.delete();                                          //~vae0I~
        }                                                          //~vae0M~
        String mimeType;                                           //~vae0M~
//* text/plain: .txt is added                                      //~vae0I~
//* else no additional extent ut mimetype is set to application/octet-stream//~vae0I~
//      String mimeType="text/plain";                              //~vae0I~
//      String mimeType="";                                        //~vae0I~
//      mimeType="text/";                                          //~vae0I~
//      int pos=Pmember.indexOf(".");                              //~vae0R~
//      mimeType=pos>0 ? Pmember.substring(pos+1) : "";            //~vae0R~
//      mimeType="text/"+mimeType;                                 //~vae0R~
        mimeType="";                                        //~vae0I~
        doc=docDir.createFile(mimeType,Pmember);                   //~vae0R~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument after create mimeType="+mimeType+",uri="+doc.getUri()+",exists="+doc.exists()+",length="+doc.length()+",name="+doc.getName()+",type="+doc.getType()+",isFile="+doc.isFile()+",exist="+doc.exists());//~vae0R~
        OutputStream os=openOutputStream(PmemberUri);                    //~vae0M~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument os="+Utils.toString(os));//~vae0M~
        return os;                                                 //~vae0M~
    }                                                              //~vae0M~
//********************************************************         //~vae0M~
	private InputStream openInputDocument(Uri Puri)                //~vae0M~
    {                                                              //~vae0M~
	    if (Dump.Y) Dump.println(CN+"openInputDocument uri="+Puri);//~vae0M~
        InputStream is=null;                                       //~vae0M~
        try                                                        //~vae0M~
        {                                                          //~vae0M~
        	is=CR.openInputStream(Puri);                           //~vae0M~
        }                                                          //~vae0M~
        catch(FileNotFoundException e)                             //~vae0M~
        {                                                          //~vae0M~
        	Dump.println(e,CN+"openInputDocument FileNotFound:"+Puri);//~vae0M~
        }                                                          //~vae0M~
	    if (Dump.Y) Dump.println(CN+"openInputDocument is="+Utils.toString(is));//~vae0M~
        return is;                                                 //~vae0M~
    }                                                              //~vae0M~
//********************************************************         //~vae0I~
	private OutputStream openOutputDocument(String Pfpath)         //~vae0R~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument pfath="+Pfpath);//~vae0R~
        String member=parseMember(Pfpath);                         //~vae0R~
        Uri uri=getMemberUri(member);                              //~vae0R~
        if (uri==null)                                             //~vae0I~
        	return null;                                           //~vae0I~
        OutputStream os=openOutputDocument(uri,member);            //~vae0R~
	    if (Dump.Y) Dump.println(CN+"openOutputDocument rc="+Utils.toString(os));//~vae0R~
        return os;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
    private OutputStreamWriter openOutputDocumentWriter(String Pfpath)//~vae0R~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocumentWriter fpath="+Pfpath);//~vae0R~
        OutputStreamWriter osw=null;                               //~vae0I~
        OutputStream os=openOutputDocument(Pfpath);                //~vae0R~
        if (os!=null)                                              //~vae0I~
        	osw=new OutputStreamWriter(os);                        //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocumentWriter rc="+Utils.toString(osw));//~vae0R~
        return osw;                                                //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
    public OutputStreamWriter openOutputDocumentWriter(String Pfpath,boolean PswOverride)//~vae0R~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocumentWriter swOverride="+PswOverride+",fpath="+Pfpath);//~vae0R~
	    swNoOverride=!PswOverride;                                 //~vae0I~
    	OutputStreamWriter osw=openOutputDocumentWriter(Pfpath);   //~vae0R~
	    swNoOverride=false;                                        //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocumentWriter rc="+Utils.toString(osw));//~vae0I~
        return osw;                                                //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
    public BufferedWriter openOutputDocumentBufferedWriter(String Pfpath)//~vae0R~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocumenBufferedWriter fpath="+Pfpath);//~vae0R~
        BufferedWriter bw=null;                                    //~vae0I~
        OutputStreamWriter osw=openOutputDocumentWriter(Pfpath);   //~vae0R~
        if (osw!=null)                                             //~vae0I~
        	bw=new BufferedWriter(osw);                            //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocumentBufferedWriter rc="+Utils.toString(bw));//~vae0R~
        return bw;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
    public PrintWriter openOutputDocumentPrintWriter(String Pfpath)//~vae0R~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocumenPrintWriter fpath="+Pfpath);//~vae0R~
        PrintWriter pw=null;                                       //~vae0I~
        BufferedWriter bw=openOutputDocumentBufferedWriter(Pfpath);//~vae0R~
        if (bw!=null)                                              //~vae0I~
        	pw=new PrintWriter(bw);                                //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openOutputDocumentPrintWriter rc="+Utils.toString(pw));//~vae0R~
        return pw;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
    private InputStream openInputDocument(String Pfpath)           //~vae0R~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openInputDocument fpath="+Pfpath);//~vae0R~
        String member=parseMember(Pfpath);                         //~vae0I~
        Uri uri=getMemberUri(member);                              //~vae0R~
        if (uri==null)                                             //~vae0I~
        	return null;                                           //~vae0I~
        InputStream is=openInputDocument(uri);               //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openInputDocument rc="+Utils.toString(is));//~vae0I~
        return is;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
    private InputStreamReader openInputDocumentReader(String Pfpath)//~vae0R~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openInputDocumentReader fpath="+Pfpath);//~vae0R~
        InputStreamReader isr=null;                                //~vae0I~
        InputStream is=openInputDocument(Pfpath);                  //~vae0R~
        if (is!=null)                                              //~vae0I~
        	isr=new InputStreamReader(is);                         //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openInputDocumentReader rc="+Utils.toString(isr));//~vae0I~
        return isr;                                                //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
    public BufferedReader openInputDocumentBufferedReader(String Pfpath)//~vae0R~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openInputDocumentBufferedReader fpath="+Pfpath);//~vae0R~
        BufferedReader br=null;                                    //~vae0I~
        InputStreamReader isr=openInputDocumentReader(Pfpath);     //~vae0R~
        if (isr!=null)                                             //~vae0I~
        	br=new BufferedReader(isr);                            //~vae0I~
	    if (Dump.Y) Dump.println(CN+"openInputDocumentBufferedReader rc="+Utils.toString(br));//~vae0I~
        return br;                                                //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
    public boolean isDocumentExist(String Pfpath)                  //~vae0I~
    {                                                              //~vae0I~
	    if (Dump.Y) Dump.println(CN+"isDocumentExist fpath="+Pfpath);//~vae0R~
        String member=parseMember(Pfpath);                        //~vae0I~
		DocumentFile doc=getMemberDocument(member);                //~vae0I~
        boolean rc=doc.exists();                                   //~vae0I~
	    if (Dump.Y) Dump.println(CN+"isDocumentExist rc="+rc);     //~vae0R~
        return rc;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
    public boolean deleteDocument(String Pfpath)                   //~vae0I~
    {                                                              //~vae0I~
    	boolean rc=false;                                           //~vae0I~
	    if (Dump.Y) Dump.println(CN+"deleteDocument fpath="+Pfpath);//~vae0I~
        String member=parseMember(Pfpath);                         //~vae0I~
		DocumentFile doc=getMemberDocument(member);                //~vae0I~
        if (doc.exists())                                          //~vae0R~
        {                                                          //~vae0I~
	    	if (Dump.Y) Dump.println(CN+"deleteDocument exist");   //~vae0I~
        	doc.delete();                                          //~vae0I~
            rc=true;                                               //~vae0I~
        }                                                          //~vae0I~
	    if (Dump.Y) Dump.println(CN+"deleteDocument rc="+rc);      //~vae0I~
        return rc;                                                 //~vae0I~
    }                                                              //~vae0I~
//********************************************************         //~vae0I~
//*rc 0:copyed,1:already exist,-1:failed                           //~vae0I~
//********************************************************         //~vae0I~
	public int writeDocumentFromFile(String Pfpath)                //~vae0R~
    {                                                              //~vae0I~
    	int rc=-1;                                                 //~vae0R~
	    if (Dump.Y) Dump.println(CN+"writeDocumentFromFile path="+Pfpath);//~vae0I~
    	if (isDocumentExist(Pfpath))                               //~vae0I~
        {                                                          //~vae0I~
		    if (Dump.Y) Dump.println(CN+"writeDocumentFromFile return target exist"+Pfpath);//~vae0I~
            return 1;	//exist means db captured /sdcard/eMahjong/*, no need to copy, do not delete and leave it there//~vae0R~
        }                                                          //~vae0I~
    	PrintWriter pw=openOutputDocumentPrintWriter(Pfpath);      //~vae0I~
        if (pw==null)                                              //~vae0I~
        {                                                          //~vae0I~
		    if (Dump.Y) Dump.println(CN+"writeDocumentFromFile rc=-1 by printWriter=null");//~vae0R~
        	return -1;                                             //~vae0R~
        }                                                          //~vae0I~
        try                                                        //~vae0I~
        {                                                          //~vae0I~
        	BufferedReader br=new BufferedReader(new FileReader(Pfpath));//~vae0I~
            if (Dump.Y) Dump.println(CN+"writeDocumentFromFile bufferedReader="+Utils.toString(br));//~vae0I~
            if (br!=null)                                          //~vae0I~
            {                                                      //~vae0I~
                String line;                                       //~vae0R~
                while(true)                                        //~vae0R~
                {                                                  //~vae0I~
                    line=br.readLine();                            //~vae0I~
                    if (Dump.Y) Dump.println(CN+"writeDocumentFromFile line="+Utils.toString(line));//~vae0R~
                    if (line==null)                                //~vae0I~
                        break;                                     //~vae0I~
                    pw.println(line);                              //~vae0R~
                }                                                  //~vae0R~
                pw.close();                                        //~vae0R~
                rc=0;                                              //~vae0R~
            }                                                      //~vae0I~
        }                                                          //~vae0I~
        catch(FileNotFoundException e)                             //~vae0I~
        {                                                          //~vae0I~
        	Dump.println(e,CN+"writeDocumentFromFile:"+Pfpath);    //~vae0I~
        }                                                          //~vae0I~
        catch(IOException e)                                       //~vae0I~
        {                                                          //~vae0I~
        	Dump.println(e,CN+"writeDocumentFromFile:"+Pfpath);    //~vae0I~
        }                                                          //~vae0I~
	    if (Dump.Y) Dump.println(CN+"writeDocumentFromFile exit rc="+rc+",path="+Pfpath);//~vae0R~
        return rc;                                                 //~vae0I~
    }                                                              //~vae0I~
}//class
