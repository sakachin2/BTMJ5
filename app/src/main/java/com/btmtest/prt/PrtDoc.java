//*CID://+DATER~:                             update#=   58;       //~2A10R~
//******************************************************************************************************************
//2022/10/09 vas0 print history
//******************************************************************************************************************
package com.btmtest.prt;
//*******************************************
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.print.PrintManager;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.utils.Utils.*;

import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

//*******************************************
public class PrtDoc
{
	private static final float RATE_TEXTSIZE=0.7f;	//setTextSize:lineHeght*//~vas0R~
	private static final int TITLE_HEIGHT=2;	//by lines         //~2A10I~
    private static final float HLINE_POSY=0.2F; //for lineHeight ,pos of horizontalLine//~2A10I~
    private static final int MARGIN_TOP=0;		//by line count    //~2A11I~
    private static final int MARGIN_BOTTOM=2;                      //~2A11I~
    private static final int MARGIN_LEFT=2;     //by column        //~2A11I~
    private static final int MARGIN_RIGHT=2;                       //~2A11I~
    private static final float HLINE_POSY_TITLE=0.2F;              //~2A11I~
    private static final int SEPARATER_WIDTH_HEADER=1;             //~2A11R~
	private PrtAdapter adapter;                                    //~vas0I~
	private int maxLine,maxColumn;                                 //~vas0I~
	private int xLeft,yTop,yBottom,lineH,pageW,pageH,colW;         //~vas0R~
	private float xCurrent,yCurrent;                               //~vas0I~
	private float rateTextSize;                                    //~vas0I~
	private int ctrPage,ctrLine;                                   //~vas0R~
	private boolean swDraw;                                        //~vas0I~
	private PrtDocI callback;                                      //~vas0I~
	private int printPage;                                         //~vas0I~
	private String pageTitle;                                      //~2A10I~
	private int titleHeight;                                       //~2A10I~
	private int totalPage;                                         //~2A11I~
//**********************************                               //~vas0I~
    public interface PrtDocI                                       //~vas0I~
    {                                                              //~vas0I~
		void cbGetCtrPage(PrtDoc PprtDoc);                         //~vas0I~
		void cbDrawPage(PrtDoc PprtDoc);                           //~vas0I~
    }                                                              //~vas0I~
	//*****************************                                //~vas0I~
	public PrtDoc()                                                //~vas0I~
    {                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.condtructor");            //~vas0I~
        AG.aPrtDoc=this;                                           //~vas0I~
    }                                                              //~vas0I~
    //***************************                                  //~vas0I~
	public void print(PrtDocI Pcallback,String Ptitle,int PtypeDocument)//~vas0R~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.print title="+Ptitle+",typeDocumant="+PtypeDocument+",callback="+Pcallback);//~vas0R~
        callback=Pcallback;                                        //~vas0I~
        if (Build.VERSION.SDK_INT>=19) //kitkat 4.4                //~vas0I~
		    print19(Ptitle,PtypeDocument);                         //~vas0R~
        else                                                       //~vas0I~
            UView.showToastLong(R.string.Err_PrintSupportApi19);   //~vas0I~
    }                                                              //~vas0I~
    //***************************                                  //~vas0I~
	@TargetApi(19)                                                 //~vas0I~
	public void print19(String Ptitle,int PtypeDocument)           //~vas0R~
	{
        if (Dump.Y) Dump.println("PrtDoc.print19 title="+Ptitle);  //~vas0R~
		Context context=AG.context;                                //~vas0I~
	    PrintManager printManager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);
	    String jobName=AG.appName+":"+Ptitle;
	    PrtAdapter adapter=new PrtAdapter(this,context,PtypeDocument);//~vas0R~
	    printManager.print(jobName,adapter,null);                  //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.print19 exit");           //~vas0R~
	}
    //***************************                                  //~vas0I~
	public int/*totalpage*/ getCtrPage(PrtAdapter Padapter)        //~vas0R~
	{                                                              //~vas0I~
        adapter=Padapter;                                        //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.getCtrPage typeDocument="+adapter.typeDocument);//~vas0R~
        if (Dump.Y) Dump.println("PrtDoc.getCtrPage adapter pageH="+adapter.pageH+",pageW="+adapter.pageW);//~vas0I~//~2A11R~
        pageW=adapter.pageW;                                       //~vas0R~
        pageH=adapter.pageH;                                       //~vas0R~
        lineH=pageH/(maxLine+MARGIN_TOP+MARGIN_BOTTOM);                                   //~vas0I~//~2A11R~
        colW=pageW/(maxColumn+MARGIN_LEFT+MARGIN_RIGHT);                                  //~vas0R~//~2A11R~
        float marginL=colW*MARGIN_LEFT;                                      //~vas0R~//~2A11R~
        float marginR=colW*MARGIN_RIGHT;                                      //~vas0R~//~2A11R~
        float marginT=lineH*MARGIN_TOP;                                     //~vas0R~//~2A11R~
        float marginB=lineH*MARGIN_BOTTOM;                                     //~vas0R~//~2A11R~
        xLeft=(int)marginL;                                             //~vas0I~
        yTop=(int)marginT;                                              //~vas0I~
        yBottom=(int)(pageH-marginB);                                     //~vas0I~
        pageH=yBottom-yTop;                                        //~vas0I~
        pageW-=(marginL+marginR);                                  //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.getCtrPage lineH="+lineH+",colW="+colW+",maxCol="+maxColumn+",maxRow="+maxLine+",pageH="+pageH+",pageW="+pageW+",xLeft="+xLeft+",yTop="+yTop+",yBottom="+yBottom);//~vas0R~
        swDraw=false;                                              //~vas0I~//~2A10M~
        ctrPage=0;                                                 //~vas0M~//~2A11M~
        newPage();                                                 //~vas0I~
		rateTextSize=0;   //calback may set it                     //~vas0I~
		callback.cbGetCtrPage(this);                     //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.getCtrPage exit rc="+ctrPage);//~vas0R~
        adapter=null;                                              //~vas0I~
        totalPage=ctrPage;                                         //~2A11I~
		return ctrPage;                                            //~vas0R~//~2A10R~//~2A11R~
	}                                                              //~vas0I~
    //***************************                                  //~vas0I~
	public void drawPage(PrtAdapter Padapter,PdfDocument.Page Ppage, PdfDocument.PageInfo Pinfo, Canvas Pcanvas,int PpageNumber)//~vas0R~
	{                                                              //~vas0I~
        adapter=Padapter;                                        //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.drawPage typeDocument="+adapter.typeDocument+",pageNumber="+PpageNumber+",Page="+Ppage+"pageInfo="+Pinfo);//~vas0R~
        if (Dump.Y) Dump.println("PrtDoc.drawPage adapter.pageH="+adapter.pageH+",adapter.pageW="+adapter.pageW);//~vas0I~//~2A11R~
        if (Dump.Y) Dump.println("PrtDoc.drawPage pageH="+pageH+",pageW="+pageW);//~2A11I~
        printPage=PpageNumber+1;                                     //~vas0I~//~2A11R~
        swDraw=true;                                               //~vas0M~//~2A10M~
        ctrPage=0;                                                 //~vas0I~//~2A11M~
        newPage();                                                 //~vas0I~
		callback.cbDrawPage(this);                       //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.drawPage exit");          //~vas0I~
        adapter=null;                                              //~vas0I~
	}                                                              //~vas0I~
    //*************************************************************                                  //~vas0I~//~2A10R~
    //*from onClickPrint                                           //~2A10I~
    //*************************************************************//~2A10I~
	public void setMaxLineColumn(int PmaxLine,int PmaxColumn)      //~vas0I~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.setMaxLine maxline="+PmaxLine+",maxCol="+PmaxColumn);//~vas0I~
        maxLine=PmaxLine;                                          //~vas0I~
        maxColumn=PmaxColumn;                                      //~vas0I~
	}                                                              //~vas0I~
    //***************************                                  //~2A10I~
	public void setTitle(String Ptitle,int Pheight)                //~2A10I~
	{                                                              //~2A10I~
        if (Dump.Y) Dump.println("PrtDoc.setTitle title="+Ptitle+",height="+Pheight);//~2A10I~
        pageTitle=Ptitle;                                          //~2A10I~
        titleHeight=Pheight;                                       //~2A10I~
	}                                                              //~2A10I~
    //***************************                                  //~vas0I~
    //*fromcallback at getCtrPage                                  //~vas0I~
    //***************************                                  //~vas0I~
	public void setTextSize(float PrateTextSize)                   //~vas0I~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.setMaxLine rateTextSize="+PrateTextSize);//~vas0I~
        rateTextSize=PrateTextSize;                                //~vas0I~
	}                                                              //~vas0I~
    //***************************                                  //~vas0I~
    //*newline                                                     //~vas0I~
    //***************************                                  //~vas0I~
	public void drawLineNL(float PstepY)                           //~vas0R~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.drawLineNL ctrLine="+ctrLine);//~vas0R~
        updateY(PstepY);                                           //~vas0R~
        ctrLine++;                                                 //~vas0I~
    	xCurrent=xLeft;                                            //~vas0I~
        if (chkNewPage())                                              //~vas0R~//~2A11R~
	        updateY(PstepY);                                       //~2A11I~
	}                                                              //~vas0I~
    //***************************                                  //~vas0I~
	public void updateY(float Pyy)                                 //~vas0R~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.updateY Pyy="+Pyy+",yCurrent="+yCurrent+",lineH="+lineH);//~vas0I~//~2A10R~
        yCurrent+=Pyy*lineH;                                       //~vas0R~
	}                                                              //~vas0I~
    //***************************                                  //~vas0I~
	public void updateX(int Pxx)                                 //~vas0I~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.updateX Pxx="+Pxx+",xCurrent="+xCurrent);//~vas0R~
        xCurrent+=Pxx;                                             //~vas0I~
	}                                                              //~vas0I~
    //***************************                                  //~vas0I~
	public boolean chkNewPage()                                       //~vas0R~//~2A11R~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.chkNewPage ctrPage="+ctrPage+",yCurrent="+yCurrent+",yBottom="+yBottom);//~vas0R~
        boolean rc=false;                                          //~2A11I~
        if (yCurrent>yBottom)                                      //~vas0R~
        {                                                          //~2A11I~
        	newPage();                                             //~vas0I~
            rc=true;                                               //~2A11I~
        }                                                          //~2A11I~
        if (Dump.Y) Dump.println("PrtDoc.chkNewPage rc="+rc+",ctrPage="+ctrPage+",yCurrent="+yCurrent+",yBottom="+yBottom);//~2A11I~
        return rc;                                                 //~2A11I~
	}                                                              //~vas0I~
    //***************************                                  //~vas0I~
	public void newPage()                                          //~vas0I~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.newPage entry ctrPage="+ctrPage+",ctrLine="+ctrLine+",yCurrent="+yCurrent);//~vas0R~
	    ctrPage++;                                                 //~vas0I~//~2A10M~//~2A11M~
    	yCurrent=yTop;                                             //~vas0I~
    	xCurrent=xLeft;                                            //~vas0I~
        ctrLine=0;                                                 //~vas0I~
        drawTitle();                                               //~2A10I~
	    if (Dump.Y) Dump.println("PrtDoc.newPage exit ctrPage="+ctrPage+",yCurrent="+yCurrent);//~vas0I~
	}                                                              //~vas0I~
    //***************************                                  //~vas0M~
    //*draw horizontal separater                                   //~vas0I~
    //***************************                                  //~vas0I~
	public void drawLine(float PrateY)                             //~2A11I~
	{                                                              //~2A11I~
		drawLine(PrateY,0);                                        //~2A11I~
    }                                                              //~2A11I~
	public void drawLine(float PrateY,int PstrokeWidth)                             //~vas0R~//~2A11R~
	{                                                              //~vas0M~
        if (Dump.Y) Dump.println("PrtDoc.drawLine ctrPage="+ctrPage+",printPage="+printPage+",rateY="+PrateY+",strokeWidth="+PstrokeWidth);//~vas0R~//~2A10R~//~2A11R~
        if (swDraw && ctrPage==printPage)                          //~vas0R~//~2A11R~
        {                                                          //~vas0I~
            Paint p=new Paint();                                    //~vas0I~
            p.setColor(Color.argb(255,0,0,0));                     //~vas0I~
            if (PstrokeWidth!=0)                                   //~2A11I~
            	p.setStrokeWidth(PstrokeWidth);                    //~2A11I~
            int posY=(int)(yCurrent+PrateY*lineH);                        //~vas0I~
            adapter.canvas.drawLine(xLeft,posY,xLeft+pageW,posY,p);//~vas0R~//~2A10R~
	        if (Dump.Y) Dump.println("PrtDoc.drawLine ctrLine="+ctrLine+",posY="+posY+",x="+xLeft+"--"+(xLeft+pageW)+",pageW="+pageW);//~2A10I~//~2A11R~
        }                                                          //~vas0I~
	}                                                              //~vas0M~
    //***************************                                  //~vas0I~
	public void drawLine(String Ptext,float PrateW)                //~vas0R~
	{                                                              //~vas0I~
		drawLine(Ptext,PrateW,null);                  //~vas0I~
    }                                                              //~vas0I~
    //***************************                                  //~vas0I~
	public void drawLine(String Ptext,float PrateW,Paint.Align Palign)//~vas0I~
	{                                                              //~vas0I~
		drawLine(Ptext,0.0F,PrateW,Palign,0);                      //~vas0R~
    }                                                              //~vas0I~
    //***************************                                  //~vas0I~
	public void drawLine(String Ptext,float PrateW,int PmarginLeft/*char count*/)//~vas0I~
	{                                                              //~vas0I~
		drawLine(Ptext,0.0F,PrateW,null,PmarginLeft);              //~vas0R~
    }                                                              //~vas0I~
    //***************************                                  //~vas0I~
	public void drawLine(String Ptext,float PrateH,float PrateW,Paint.Align Palign,int PmarginLeft)//~vas0R~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.drawLine swDraw="+swDraw+",text="+Ptext+",rateH="+PrateH+",rateW="+PrateW+",xCurrent="+xCurrent+",yCurrent="+yCurrent+",ctrPage="+ctrPage+",printPage="+printPage);//~vas0R~
        float ww=pageW*PrateW;                                     //~vas0I~
        if (swDraw && ctrPage==printPage)                          //~vas0I~//~2A11R~
        {                                                          //~vas0I~
            Paint p=new Paint();                                    //~vas0I~
            float rate;                                            //~vas0I~
            if (PrateH==0.0F)	                                   //~vas0I~
            	if (rateTextSize!=0)                               //~vas0I~
	            	rate=rateTextSize;                             //~vas0I~
                else                                               //~vas0I~
	            	rate=RATE_TEXTSIZE;                            //~vas0I~
            else                                                   //~vas0I~
            	rate=PrateH;                                       //~vas0I~
            int lh=(int)(lineH*rate);                              //~vas0I~
            p.setTextSize(lh);                                     //~vas0I~
            p.setColor(Color.argb(255,0,0,0));                     //~2A10I~
        	if (Dump.Y) Dump.println("PrtDoc.drawLine rate="+rate+",textSize="+lh+",lineH="+lineH);//~2A10I~
            int posx=(int)xCurrent;                                     //~vas0I~
            if (Palign!=null)                                      //~vas0I~
            	posx+=getAlign(ww,p,Ptext,Palign);                //~vas0R~//~2A10R~
            if (PmarginLeft!=0)                                       //~vas0I~
            	posx+=PmarginLeft*colW;      //by char width       //~vas0I~
            p.setColor(Color.argb(255,0,0,0));                     //~vas0I~
        	if (Dump.Y) Dump.println("PrtDoc.drawLine drawText x="+posx+",y="+yCurrent);//~2A10I~
            adapter.canvas.drawText(Ptext,posx,yCurrent,p);        //~vas0R~
        }                                                          //~vas0I~
        updateX((int)ww);                                               //~vas0I~
	}                                                              //~vas0I~
    //***************************                                  //~vas0I~
	private int getAlign(float Pwidth, Paint Ppaint, String Ptext, Paint.Align Palighn)//~vas0I~//~2A10R~
	{                                                              //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.getAlign Pwidth="+Pwidth+",text="+Ptext+",paint="+Ppaint);//~vas0I~//~2A10R~
        Rect r=new Rect();                                         //~vas0I~
        Ppaint.getTextBounds(Ptext,0,Ptext.length(),r);               //~vas0I~
        int width=r.width();                                       //~vas0I~
        int pos=0;                                                 //~vas0I~
        if (width<(int)Pwidth)                                     //~vas0I~
        	if (Palighn==Paint.Align.RIGHT)                        //~vas0I~
	        	pos=(int)(Pwidth-width);                           //~vas0I~
            else                                                   //~vas0I~
        	if (Palighn==Paint.Align.CENTER)                       //~vas0I~
	        	pos=(int)((Pwidth-width)/2);                       //~vas0I~
        if (Dump.Y) Dump.println("PrtDoc.getAlign rc="+pos+",width="+width+",bounds="+r);//~vas0I~//~2A10R~
        return pos;                                                //~vas0I~
	}                                                              //~vas0I~
    //***************************                                  //~2A10I~
	private void drawTitle()                                       //~2A10I~
	{                                                              //~2A10I~
        if (Dump.Y) Dump.println("PrtDoc.drawTitle swDraw="+swDraw+",title="+pageTitle+",ctrPage="+ctrPage);//~2A10R~
		drawLineNL((float)titleHeight);                        //~2A10I~//~2A11R~
        if (swDraw && ctrPage==printPage)                          //~2A10I~//~2A11R~
        {                                                          //~2A10I~
            Paint p=new Paint();                                   //~2A10I~
	        int lh=lineH*titleHeight;                              //~2A10I~
            int sz=(int)(lh*RATE_TEXTSIZE);                        //~2A10I~
            p.setTextSize(sz);                                     //~2A10I~
            p.setColor(Color.argb(255,0,0,0));                     //~2A10I~
            adapter.canvas.drawText(pageTitle,xLeft,yCurrent,p);            //~2A10I~//~2A11R~
	        String ts=getTitleTS_Page();                                //~2A10I~//~2A11I~
            p.setTextSize(sz*2/3);                                 //~2A11I~
			int pos=getAlign((float)pageW,p,ts,Paint.Align.RIGHT); //~2A11R~
            adapter.canvas.drawText(ts,xLeft+pos,yCurrent,p);      //~2A11R~
	        if (Dump.Y) Dump.println("PrtDoc.drawTitle draw timestamp pox="+(xLeft+pos));//~2A11I~
	        drawLine(HLINE_POSY_TITLE,SEPARATER_WIDTH_HEADER);     //~2A11R~
        }                                                          //~2A10I~
	}                                                              //~2A10I~
    //***************************                                  //~2A10I~
	private String getTitleTS_Page()                                      //~2A10I~//~2A11R~
	{                                                              //~2A10I~
		String ts= Utils.getTimeStamp(TS_DATE_TIME3);               //~2A10I~//~2A11R~
        String rc= ts+"      page = "+ctrPage+"/"+totalPage;//~2A10R~//+2A11R~
        if (Dump.Y) Dump.println("PrtDoc.getTitleTS_Page rc="+rc); //~2A11R~
        return rc;                                              //~2A10I~
	}                                                              //~2A10I~
}