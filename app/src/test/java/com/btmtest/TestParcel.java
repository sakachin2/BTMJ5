//*CID://+dateR~:                             update#=   23;       //~1107I~
//**********************************************************************//~v101I~
//**********************************************************************//~1107I~
package com.btmtest;                                          //~va1aR~

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.btmtest.utils.Dump;                                     //~3126I~

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;                                        //~3126I~
import org.mockito.junit.MockitoJUnitRunner;                       //~3126I~

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

//****************************************************             //~3126I~
@RunWith(MockitoJUnitRunner.class)                                       //~3126I~
public class TestParcel                                           //~1107R~//~3126M~
{                                                                  //~0914I~//~3126M~
    private	static final String datapath="w:\\AndroidStudioProjects\\BTMJ5W\\app\\src\\test\\java\\com\\btmtest\\testdata\\";//~3126I~
    private	static final String dumpfile=datapath+"Dump.txt";      //~3126I~
    private static FileWriter fwDump;                              //~3126I~
    //*************************************************************************//~va1aR~
    @Before                                                        //~va1aR~
    public void setUp() throws Exception                           //~va1aR~
    {                                                              //~va1aR~
    	initEnv();                                                 //~3126I~
    }                                                              //~va1aR~
	//*************************************************************************//~va1aM~
    @Test                                                          //~va1aM~
    public void testParcel()                                  //~va1aM~//~1107R~//~3126R~
    {                                                              //~va1aM~
        try                                                        //~3126I~
		{                                                          //~3126I~
        	doTest();                                              //~3126R~
        }                                                          //~3126I~
        catch(Exception e)                                         //~3126I~
        {                                                          //~3126I~
        	System.out.println("Exception"+e.toString()+","+e.getStackTrace());          //~3126I~
            Dump.println("Exception "+e.toString()+","+ Arrays.toString(e.getStackTrace()));                 //~3126I~
    		closeOutput(fwDump);                                   //~3126I~
        }                                                          //~3126I~
    	closeOutput(fwDump);                                       //~3126I~
    }                                                              //~va1aM~
	//*************************************************************************//~3126I~
    private void doTest()                                          //~3126I~
    {                                                              //~3126I~
//        Bundle b=new Bundle();                                   //~3126R~
//        SampParcel in=new SampParcel();                          //~3126R~
//        Dump.println("doText in="+in);                           //~3126R~
//        b.putParcelable("KEY",in);                               //~3126R~
//        SampParcel out=b.getParcelable("KEY",Class.forfromName(SampParcel));//~3126R~
//        Dump.println("doText out="+out);                         //~3126R~
//                                                                 //~3126R~
//        SampParcel in2=new SampParcel(99,"strVal:Y");            //~3126R~
//                                                                 //~3126R~
//        Dump.println("doText in2="+in2);                         //~3126R~
//        b.putParcelable("KEY",in2);                              //~3126R~
//        SampParcel out2=b.getParcelable("KEY");                  //~3126R~
//        Dump.println("doText out2="+out2);                       //~3126R~
                                                                   //~3126I~
//*bundle requires Moc                                             //~3126I~
//        Bundle b=Mockito.mock(Bundle.class);                     //~3126R~
//        SampParcel in=new SampParcel();                          //~3126R~
//        Dump.println("doText in="+in);                           //~3126R~
//        b.putParcelable("KEY",in);                               //~3126R~
//        SampParcel out=b.getParcelable("KEY",SampParcel.class);  //~3126R~
//        Dump.println("doText out="+out);                         //~3126R~
//                                                                 //~3126R~
//        SampParcel in2=new SampParcel(99,"strVal:Y");            //~3126R~
//                                                                 //~3126R~
//        Dump.println("doText in2="+in2);                         //~3126R~
//        b.putParcelable("KEY",in2);                              //~3126R~
//        SampParcel out2=b.getParcelable("KEY");                  //~3126R~
//        Dump.println("doText out2="+out2);                       //~3126R~
//***                                                              //~3126R~
        SampParcel in=new SampParcel();                            //~3126R~
        Dump.println("doText in="+in);                             //~3126R~
        SampParcel in2=new SampParcel(99,"strVal:Y");              //~3126R~
        Dump.println("doText in2="+in2);                           //~3126R~
        Parcel parcel=Parcel.obtain();                             //~3126R~
        Dump.println("obtaind Parce="+parcel);                     //~3126I~
        in2.writeToParcel(parcel,0/*flag*/);                       //~3126R~
        SampParcel in3=new SampParcel(parcel);                     //~3126R~
        Dump.println("doText in3="+in3);                           //~3126R~
//*Parcel requires Moc                                             //~3126I~
    }                                                              //~3126I~
//***********************************************                  //~3126I~
    private void initEnv()                                         //~3126I~
    {                                                              //~3126I~
    	fwDump=openOutput(dumpfile);                               //~3126I~
        Dump.open(new PrintWriter(fwDump));                        //~3126I~
        if (Dump.Y) Dump.println("TestParcel.initEnv");            //~3126R~
    }                                                              //~3126I~
//***********************************************                  //~3126I~
    private static FileWriter openOutput(String Pfnm)              //~3126I~
    {                                                              //~3126I~
        FileWriter fwOut=null;                                     //~3126I~
        try                                                        //~3126I~
        {                                                          //~3126I~
            File f=new File(Pfnm);                                 //~3126I~
            fwOut=new FileWriter(f);                               //~3126I~
        }                                                          //~3126I~
        catch(IOException e)                                       //~3126I~
        {                                                          //~3126I~
            System.out.println("openOutput file:"+Pfnm+";"+e.toString());//~3126I~
        }                                                          //~3126I~
        return fwOut;                                              //~3126I~
    }                                                              //~3126I~
//***********************************************                  //~3126I~
    private static void closeOutput(FileWriter Pfw)                //~3126I~
    {                                                              //~3126I~
        if (Pfw!=null)                                             //~3126I~
            try                                                    //~3126I~
            {                                                      //~3126I~
                Pfw.close();                                       //~3126I~
            }                                                      //~3126I~
            catch(IOException e)                                   //~3126I~
            {                                                      //~3126I~
                System.out.println("closeOutput exception="+e.toString());//~3126I~
            }                                                      //~3126I~
    }                                                              //~3126I~
}//class                                                           //~v@@@R~
//***********************************************                  //~3126M~
//***********************************************                  //~3126M~
//***********************************************                  //~3126M~
class SampParcel implements Parcelable                      //~3126M~
{                                                                  //~3126M~
    private int intVal=9;                                          //~3126M~
    private String strVal="strVal:X";                              //~3126M~
    public SampParcel()                                            //~3126I~
    {                                                              //~3126I~
    	Dump.println("SampParcel.default constructor");            //~3126I~
    }                                                              //~3126I~
    public SampParcel(int Pint,String Pstr)                        //~3126I~
    {                                                              //~3126I~
    	Dump.println("SampParcel.constructor int="+Pint+",str="+Pstr);//~3126I~
        intVal=Pint; strVal=Pstr;                                  //~3126I~
    	Dump.println("SampParcel.constructor this="+this);         //~3126I~
    }                                                              //~3126I~
    @Override                                                      //~3126M~
    public int describeContents() {                                //~3126M~
    	Dump.println("SampParcel.descriveContent");                //~3126M~
        return 0;                                                  //~3126M~
    }                                                              //~3126M~
    public String toString()                                       //~3126M~
    {                                                              //~3126M~
    	return "intVal="+intVal+",strVal="+strVal;                 //~3126M~
    }                                                              //~3126M~
                                                                   //~3126M~
    @Override                                                      //~3126M~
    public void writeToParcel(Parcel dest, int flags) {            //~3126M~
    	Dump.println("SampParcel.writeToParcel");                  //~3126M~
        dest.writeInt(intVal);                                     //~3126M~
        dest.writeString(strVal);                                  //~3126M~
    }                                                              //~3126M~
                                                                   //~3126M~
//    public void readFromParcel(Parcel source) {                  //~3126R~
//        Dump.println("SampParcel.readFromParcel source="+source);//~3126R~
//    }                                                            //~3126R~
                                                                   //~3126M~
    public SampParcel(Parcel in) {                                 //~3126M~
    	Dump.println("SampParcel.constructor in="+in);             //~3126M~
        intVal=in.readInt();                                       //~3126M~
        strVal=in.readString();                                    //~3126M~
    	Dump.println("SampParcel.constructor out="+this);          //~3126M~
    }                                                              //~3126M~
    public static final Parcelable.Creator<SampParcel> CREATOR = new Parcelable.Creator<SampParcel>() {//~3126M~
        @Override                                                  //~3126M~
        public SampParcel createFromParcel(Parcel source) {        //~3126M~
	    	Dump.println("SampParcel.CREATOR.createFromParcel source="+source);//~3126M~
            return new SampParcel(source);                         //~3126M~
        }                                                          //~3126M~
                                                                   //~3126M~
        @Override                                                  //~3126M~
        public SampParcel[] newArray(int size) {                   //~3126M~
	    	Dump.println("SampParcel.CREATOR.newArray size="+size);//~3126M~
            return new SampParcel[size];                           //~3126M~
        }                                                          //~3126M~
    };                                                             //~3126M~
} //class SampParcel                                               //~3126M~
