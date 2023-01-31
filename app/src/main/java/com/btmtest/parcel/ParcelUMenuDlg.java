//*CID://+DATER~:                             update#=   18;       //~3127R~
//*************************************************************************//~1A08I~
//2023/01/27 vavn try to use Parcelable for Bundle(for test Parcelable)//~3127R~
//*************************************************************************//~0427I~
package com.btmtest.parcel;                                        //~3127R~

import android.os.Parcel;
import android.os.Parcelable;

import com.btmtest.utils.Dump;

//***********************************************                  //~3127I~
public class ParcelUMenuDlg implements Parcelable                  //~3127R~
{                                                                  //~3127I~
    private static final String CN="ParceUMenuDlg.";               //~3127R~
    public  String  title;                                         //~3127R~
    public  int     itemsID;                                       //~3127R~
    public  boolean choiceMode;                                    //~3127R~
//***********************************************                  //~3127I~
    public ParcelUMenuDlg()                                        //~3127R~
    {                                                              //~3127I~
    	if (Dump.Y) Dump.println(CN+"default constructor");        //~3127I~
    }                                                              //~3127I~
//***********************************************                  //~3127I~
    public ParcelUMenuDlg(String Ptitle,int PitemsID,boolean PchoiceMode)//~3127R~
    {                                                              //~3127I~
    	if (Dump.Y) Dump.println(CN+"constructor title="+Ptitle+",itemsID="+PitemsID+",chiceMode="+PchoiceMode);//~3127R~
        title=Ptitle; itemsID=+PitemsID; choiceMode=PchoiceMode;    //~3127I~
    }                                                              //~3127I~
//***********************************************                  //~3127I~
    public String toString()                                       //~3127I~
    {                                                              //~3127I~
    	return "title="+title+",itemsID="+itemsID+",choiceMode="+choiceMode//~3127I~
        ;                                                          //~3127I~
    }                                                              //~3127I~
//***********************************************                  //~3127I~
    @Override
    public int describeContents()                                  //~3127R~
    {                                                              //~3127I~
    	if (Dump.Y) Dump.println(CN+"describeContents");           //~3127I~
        return 0;
    }
//***********************************************                  //~3127I~
//*called when pause                                               //+3127I~
//***********************************************                  //+3127I~
    @Override
    public void writeToParcel(Parcel dest, int flags)              //~3127R~
    {                                                              //~3127I~
    	if (Dump.Y) Dump.println(CN+"writeToParcel dest="+dest+",flag="+flags);//~3127I~
        dest.writeString(this.title);
        dest.writeInt(this.itemsID);
        dest.writeByte(this.choiceMode ? (byte) 1 : (byte) 0);
    }
//***********************************************                  //~3127I~
    public void readFromParcel(Parcel source)                      //~3127R~
    {                                                              //~3127I~
    	if (Dump.Y) Dump.println(CN+"readFromParcel source="+source);//~3127I~
        this.title = source.readString();
        this.itemsID = source.readInt();
        this.choiceMode = source.readByte() != 0;
    }
//***********************************************                  //~3127I~
    protected ParcelUMenuDlg(Parcel in)                            //~3127R~
    {                                                              //~3127I~
    	if (Dump.Y) Dump.println(CN+"constructor by Parcel="+in);  //~3127I~
        this.title = in.readString();
        this.itemsID = in.readInt();
        this.choiceMode = in.readByte() != 0;
    }
//***********************************************                  //~3127I~
    public static final Creator<ParcelUMenuDlg> CREATOR = new Creator<ParcelUMenuDlg>()//~3127R~
    {                                                              //~3127I~
        @Override
        public ParcelUMenuDlg createFromParcel(Parcel source)      //~3127R~
        {                                                          //~3127I~
    		if (Dump.Y) Dump.println(CN+"CREATOR.createFromParcel source="+source);//~3127I~
            return new ParcelUMenuDlg(source);                     //~3127R~
        }

        @Override
        public ParcelUMenuDlg[] newArray(int size)                 //~3127R~
        {                                                          //~3127I~
    		if (Dump.Y) Dump.println(CN+"CREATOR.newArray size="+size);//~3127I~
            return new ParcelUMenuDlg[size];                       //~3127R~
        }
    };
}//class
                                                                 //~3127I~
