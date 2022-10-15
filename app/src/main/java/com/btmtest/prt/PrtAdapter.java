//*CID://+vas0R~:                             update#=   25;
//******************************************************************************************************************
//2022/10/09 vas0 print history
//******************************************************************************************************************
package com.btmtest.prt;
//*******************************************
import static android.print.PrintAttributes.Margins.NO_MARGINS;

import android.annotation.TargetApi;                               //~vas0I~
                                                                   //~vas0I~
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.content.Context;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
                                                                   //~vas0I~
import static com.btmtest.StaticVars.AG;                           //~vas0I~
import com.btmtest.utils.Dump;                                     //~vas0I~
import com.btmtest.utils.Utils;

//*******************************************
@TargetApi(19)                                                     //~vas0I~
public class PrtAdapter extends PrintDocumentAdapter               //~vas0R~
{
    Context context;
    PrtDoc aPrtDoc;                                                //~vas0I~
    public int pageH,pageW;                                        //~vas0R~
    public int marginL,marginR,marginT,marginB;                    //~vas0I~
    private PdfDocument aPdfDoc;                                   //~vas0R~
    private int totalpages;                                        //~vas0R~
    public int typeDocument;                                       //~vas0R~
//  public int dpi;                                                //~vas0R~
    public Canvas canvas;                                          //~vas0I~

    public PrtAdapter(PrtDoc PprtDoc,Context Pcontext,int PtypeDocument)//~vas0R~
    {
        context = Pcontext;                                        //~vas0R~
        aPrtDoc=PprtDoc;                                           //~vas0I~
        typeDocument=PtypeDocument;                                //~vas0I~
        if (Dump.Y) Dump.println("PrtAdapter.condtructor typeDocument="+PtypeDocument);//~vas0R~
    }

    //***********************************************************
    @Override
    public void onLayout(PrintAttributes oldAttributes,
                     PrintAttributes newAttributes,
                     CancellationSignal cancellationSignal,
                     LayoutResultCallback callback,
                     Bundle metadata)                              //~vas0R~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtAdapter.onLayout newAttributes="+newAttributes);//~vas0I~
        aPdfDoc = new PrintedPdfDocument(context, newAttributes);  //~vas0R~
        getPaperSize(newAttributes);                               //~vas0R~
        totalpages=0;                                               //~vas0I~
        if (cancellationSignal.isCanceled() )                      //~vas0R~
		{                                                          //~vas0I~
        	if (Dump.Y) Dump.println("PrtAdapter.onLayout canceled");//~vas0I~
            callback.onLayoutCancelled();
            return;
        }
        try                                                        //~vas0I~
		{                                                          //~vas0I~
        	totalpages=aPrtDoc.getCtrPage(this);                   //~vas0R~
        }                                                          //~vas0I~
		catch (Exception e)                                        //~vas0I~
		{                                                          //~vas0I~
        	if (Dump.Y) Dump.println(e,"PrtAdapter.onLayout getCtrPage");//~vas0I~
        }                                                          //~vas0I~
        if (totalpages > 0)                                        //~vas0R~
		{                                                          //~vas0I~
           PrintDocumentInfo.Builder builder = new PrintDocumentInfo
              .Builder("print_output.pdf")
              .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
              .setPageCount(totalpages);

           PrintDocumentInfo info = builder.build();
           callback.onLayoutFinished(info, true);
        }                                                          //~vas0R~
		else                                                       //~vas0I~
		{                                                          //~vas0I~
        	if (Dump.Y) Dump.println("PrtAdapter.onLayout totalpage=0 onLayout failed");//~vas0I~
            callback.onLayoutFailed("Page count is zero.");        //~vas0R~
        }
        if (Dump.Y) Dump.println("PrtAdapter.onLayout exit");      //~vas0I~
    }
    //***********************************************************
    @Override
    public void onWrite(final PageRange[] pageRanges,
                  final ParcelFileDescriptor destination,
                  final CancellationSignal cancellationSignal,
                  final WriteResultCallback callback)              //~vas0R~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtAdapter.onWrite totalpages="+totalpages);//~vas0I~
        try                                                        //~vas0I~
		{                                                          //~vas0I~
            for (int i = 0; i < totalpages; i++)                   //~vas0R~
            {                                                      //~vas0R~
                if (pageInRange(pageRanges, i))                    //~vas0R~
                {                                                  //~vas0R~
                     PageInfo newPage = new PageInfo.Builder(pageW/*point*/,pageH,i).create();//~vas0R~
                     PdfDocument.Page page = aPdfDoc.startPage(newPage);//~vas0R~
                     if (cancellationSignal.isCanceled())          //~vas0R~
                     {                                             //~vas0R~
                        if (Dump.Y) Dump.println("PrtAdapter.onWrite canceled");//~vas0R~
                        callback.onWriteCancelled();               //~vas0R~
                        if (Dump.Y) Dump.println("PrtAdapter.onWrite call finishPage");//~vas0I~
                        aPdfDoc.finishPage(page);  //? avoid illeagal state:current page not finished//+vas0R~
                        if (Dump.Y) Dump.println("PrtAdapter.onWrite call close");//~vas0I~
                        aPdfDoc.close();                           //~vas0R~
                        aPdfDoc = null;                            //~vas0R~
                        return;                                    //~vas0R~
                     }                                             //~vas0R~
                     drawPage(page, i);                            //~vas0R~
                     if (Dump.Y) Dump.println("PrtAdapter.onWrite call finishPage after drawPage");//~vas0I~
                     aPdfDoc.finishPage(page);                     //~vas0R~
                }                                                  //~vas0R~
            }                                                      //~vas0R~

            try                                                    //~vas0R~
            {                                                      //~vas0R~
                if (Dump.Y) Dump.println("PrtAdapter.onWrite writeTo");//~vas0I~
                aPdfDoc.writeTo(new FileOutputStream(destination.getFileDescriptor()));//~vas0R~
            }                                                      //~vas0R~
            catch (IOException e)                                  //~vas0R~
            {                                                      //~vas0R~
                if (Dump.Y) Dump.println("PrtAdapter.onWrite IOException"+e);//~vas0I~
                callback.onWriteFailed(e.toString());              //~vas0R~
                return;                                            //~vas0R~
            }                                                      //~vas0R~
            finally                                                //~vas0R~
            {                                                      //~vas0R~
                if (Dump.Y) Dump.println("PrtAdapter.onWrite writeTo finally close");//~vas0R~
                aPdfDoc.close();                                   //~vas0R~
                aPdfDoc = null;                                    //~vas0R~
            }                                                      //~vas0R~
        }                                                          //~vas0I~
		catch (Exception e)                                        //~vas0I~
		{                                                          //~vas0I~
        	if (Dump.Y) Dump.println(e,"PrtAdapter.onWrite drawPage");//~vas0I~
        }                                                          //~vas0I~
        finally                                                    //~vas0I~
        {                                                          //~vas0I~
            if (aPdfDoc!=null)                                     //~vas0I~
            {                                                      //~vas0I~
		        if (Dump.Y) Dump.println("PrtAdapter.onWrite finally2 close");//+vas0R~
	            try                                                //+vas0I~
    	        {                                                  //+vas0I~
        	    	aPdfDoc.close();                               //+vas0R~
            		aPdfDoc = null;                                //+vas0R~
            	}                                                  //+vas0R~
            	catch (Exception e)                              //+vas0I~
            	{                                                  //+vas0I~
                	if (Dump.Y) Dump.println("PrtAdapter.onWrite finally2 close"+e);//+vas0I~
            	}                                                  //+vas0I~
            }                                                      //+vas0I~
        }                                                          //~vas0I~
        if (Dump.Y) Dump.println("PrtAdapter.onWrite callback onWriteFinished");//~vas0I~
        callback.onWriteFinished(pageRanges);
        if (Dump.Y) Dump.println("PrtAdapter.onWrite exit");       //~vas0I~
    }
    //***********************************************************
    private boolean pageInRange(PageRange[] pageRanges, int Ppage) //~vas0R~
    {
    	boolean rc=false;                                          //~vas0I~
        for (int i = 0; i<pageRanges.length; i++)
        {
            if ((Ppage >= pageRanges[i].getStart()) && (Ppage <= pageRanges[i].getEnd()))//~vas0R~
            {                                                      //~vas0I~
                rc=true;                                           //~vas0R~
                break;                                             //~vas0I~
            }                                                      //~vas0I~
        }
        if (Dump.Y) Dump.println("PrtAdapter.pageInRange rc="+rc+",page="+Ppage+",range="+ Utils.toString(pageRanges));//~vas0I~
        return rc;                                                 //~vas0R~
    }
    //***********************************************************
    private void drawPage(PdfDocument.Page Ppage,int PpageNumber)  //~vas0R~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtAdapter.drawPage page="+Ppage+",pageNumber="+PpageNumber);//~vas0I~
        canvas = Ppage.getCanvas();                                //~vas0R~
//      PpageNumber++; // Make sure page numbers start at 1        //~vas0R~
//        int titleBaseLine = 72;                                  //~vas0R~
//        int leftMargin = 54;                                     //~vas0R~
//        Paint paint = new Paint();                               //~vas0R~
//        paint.setColor(Color.BLACK);                             //~vas0R~
//        paint.setTextSize(40);                                   //~vas0R~
//        canvas.drawText(                                         //~vas0R~
//                 "Test Print Document Page " + pagenumber,       //~vas0R~
//                                               leftMargin,       //~vas0R~
//                                               titleBaseLine,    //~vas0R~
//                                               paint);           //~vas0R~
//        paint.setTextSize(14);                                   //~vas0R~
//        canvas.drawText("This is some test content to verify that custom document printing works", leftMargin, titleBaseLine + 35, paint);//~vas0R~
//        if (pagenumber % 2 == 0)                                 //~vas0R~
//                paint.setColor(Color.RED);                       //~vas0R~
//        else                                                     //~vas0R~
//            paint.setColor(Color.GREEN);                         //~vas0R~
        PageInfo pageInfo = Ppage.getInfo();
//        canvas.drawCircle(pageInfo.getPageWidth()/2,             //~vas0R~
//                                 pageInfo.getPageHeight()/2,     //~vas0R~
//                                 150,                            //~vas0R~
//                                 paint);                         //~vas0R~
		aPrtDoc.drawPage(this,Ppage,pageInfo,canvas,PpageNumber);  //~vas0R~
    }
    //***********************************************************  //~vas0I~
    private void getPaperSize(PrintAttributes Pattr)                    //~vas0I~
    {                                                              //~vas0I~
        PrintAttributes.Margins margins=Pattr.getMinMargins();  //~vas0I~
        if (Dump.Y) Dump.println("PrtAdapter.getPaperSize margins="+margins+",NO_MARGINS="+NO_MARGINS);//~vas0I~
        if (margins.equals(NO_MARGINS))                           //~vas0I~
        {                                                          //~vas0I~
        	marginL=margins.getLeftMils()*72/1000;                 //~vas0R~
        	marginR=margins.getRightMils()*72/1000;                //~vas0I~
        	marginT=margins.getTopMils()*72/1000;                  //~vas0I~
        	marginB=margins.getBottomMils()*72/1000;               //~vas0I~
        }
        else
        {
            marginL=0; marginR=0; marginT=0; marginB=0;//~vas0I~
        }
        if (Dump.Y) Dump.println("PrtAdapter.getPaperSize margin by point L="+marginL+",R="+marginR+",T="+marginT+",B="+marginB);//~vas0R~
        pageH=Pattr.getMediaSize().getHeightMils()*72/1000;//~vas0I~
        pageW=Pattr.getMediaSize().getWidthMils()*72/1000; //~vas0I~
        if (Dump.Y) Dump.println("PrtAdapter.getPaperSize by  point H="+pageH+",W="+pageW);//~vas0R~
//      int dpiH=Pattr.getResolution().getVerticalDpi();           //~vas0R~
//      int dpiW=Pattr.getResolution().getHorizontalDpi();         //~vas0R~
//      dpi=Math.min(dpiH,dpiW);                                   //~vas0R~
//      if (Dump.Y) Dump.println("PrtAdapter.getPaperSizeby  dpiH="+dpiH+",dpiW="+dpiW+",dpi="+dpi);//~vas0R~
    }                                                              //~vas0I~
}   //class
