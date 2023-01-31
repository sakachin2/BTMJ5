//*CID://+DATER~:                             update#=   27;       //~3127R~
//*************************************************************************//~1A08I~
//2023/01/27 vavn try to use Parcelable for Bundle(for test Parcelable)//~3127R~
//*************************************************************************//~0427I~
package com.btmtest.parcel;                                        //~3127R~

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.btmtest.utils.Dump;

//***********************************************                  //~3127I~
public class UParcel                                        //~3127R~
{                                                                  //~3127I~
	private static final String CN="UParcel.";                     //~3127I~
//***********************************************                  //~3127I~
	@SuppressWarnings("deprecation")                               //~3127I~
    public static Parcelable getParcelable(Bundle Pbundle, String Pkey, Class Pclazz)//~3127I~
    {                                                              //~3127I~
        if (Build.VERSION.SDK_INT>=33)                              //~3127I~
    		return getParcelableNew(Pbundle,Pkey,Pclazz);          //~3127R~
    	if (Dump.Y) Dump.println(CN+"getParcelable for Bundle");    //~3127R~
        return Pbundle.getParcelable(Pkey);                        //~3127I~
    }                                                              //~3127I~
//***********************************************                  //~3127I~
    @SuppressWarnings("unchecked")
	@TargetApi(33)                                                 //~3127I~
    public static Parcelable getParcelableNew(Bundle Pbundle,String Pkey,Class Pclazz)//~3127R~
    {                                                              //~3127I~
    	if (Dump.Y) Dump.println(CN+"getParcelable for Bundle");    //~3127I~
        return (Parcelable)(Pbundle.getParcelable(Pkey,Pclazz));   //~3127R~
    }                                                              //~3127I~
}//class
