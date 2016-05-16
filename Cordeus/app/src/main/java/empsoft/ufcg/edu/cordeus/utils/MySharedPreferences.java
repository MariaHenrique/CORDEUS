package empsoft.ufcg.edu.cordeus.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import empsoft.ufcg.edu.cordeus.views.LoginActivity;

public class MySharedPreferences {

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    int PRIVATE_MODE = 0;

    private static final String PREFER_NAME = "Pref";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String LIST_MY_CORDELS = "listMyCordels";

    public MySharedPreferences(Context context){
        this.mContext = context;
        mPref = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        mEditor = mPref.edit();
    }

    public void saveUser(String username, String password){
        mEditor.putBoolean(IS_USER_LOGIN, true);
        mEditor.putString(KEY_USERNAME, username);
        mEditor.putString(KEY_PASSWORD, password);
        mEditor.commit();
    }

    public boolean isUserLoggedIn(){
        return mPref.getBoolean(IS_USER_LOGIN, false);
    }

    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();
        Intent i = new Intent(mContext, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }




}
