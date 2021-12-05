package me.basilmt.evmanager;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsHelper {

    public static final String NO_DATA = "no_data_found" ;
    public static final String PREF_NAME = "my_prefs" ;

    public static final String VEH_NAME = "VEH_NAME";
    public static final String FULL_CHARGE_TIME = "FULL_CHARGE_TIME";
    public static final String PERSON_NAME = "PERSON_NAME";


    public static SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(PREF_NAME ,Context.MODE_PRIVATE);
    }
    public static void insertData(Context context,String key,String value){
        SharedPreferences.Editor editor=getPrefs(context).edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String retrieveData(Context context, String key){
        return getPrefs(context).getString(key,NO_DATA);
    }

    public static void insertBoolData(Context context,String key,boolean value){
        SharedPreferences.Editor editor=getPrefs(context).edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public static boolean retrieveBoolData(Context context, String key){
        return getPrefs(context).getBoolean(key,false);
    }

}
