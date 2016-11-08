package app.appsmatic.com.deliverymasterclintapp.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Mido PC on 9/5/2016.
 */
public class SaveSharedPreference {

    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_PASS = "password";
    static final String LANG_ID="langId";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.putString(PREF_USER_PASS, password);
        editor.commit();
    }

    public static void setLangId(Context context,String lang){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LANG_ID,lang);
        editor.commit();
    }

    public static String getLangId(Context context){
        return getSharedPreferences(context).getString(LANG_ID, "");
    }


    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_PASS, "");
    }

    public static void clearUserName(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

}