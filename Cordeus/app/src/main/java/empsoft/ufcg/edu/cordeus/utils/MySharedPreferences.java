package empsoft.ufcg.edu.cordeus.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import empsoft.ufcg.edu.cordeus.views.LoginActivity;

public class  MySharedPreferences {

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    int PRIVATE_MODE = 0;
    private List<String> myCordels;

    private static final String PREFER_NAME = "Pref";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LIST_MY_CORDELS = "listMyCordels";

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

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> userDetails = new HashMap<String, String>();
        userDetails.put(KEY_USERNAME, mPref.getString(KEY_USERNAME, null));
        userDetails.put(KEY_PASSWORD, mPref.getString(KEY_PASSWORD, null));
        return userDetails;
    }

    public void saveListMyCordels(String json_my_cordels){
        mEditor.putString(KEY_LIST_MY_CORDELS, json_my_cordels);
        mEditor.commit();
    }

    public List<String> getMyCordels(){
        String jsonArrayString = mPref.getString(KEY_LIST_MY_CORDELS, "");
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            loadsList(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myCordels;
    }


    public void loadsList(JSONArray jsonArray)throws JSONException{
        myCordels =  new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonList = jsonArray.getJSONObject(i);
            String refercordel = jsonList.getString("refercordel");
            try {
                myCordels.add(refercordel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
