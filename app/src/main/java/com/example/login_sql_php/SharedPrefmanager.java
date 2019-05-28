package com.example.login_sql_php;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SharedPrefmanager {
    private static SharedPrefmanager instance;
    private static Context mctx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_ID = "userid";

    private static final String KEY_SHOP_ID = "shopId";
    private static final String KEY_SHOP_OWNER_NAME = "shopOwner";
    private static final String KEY_SHOP_CONTACT = "shopContact";


    private SharedPrefmanager(Context context) {
        mctx = context;

            }

    public static synchronized SharedPrefmanager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefmanager(context);
        }
        return instance;
    }

    public boolean userLogin(int id, String username, String email){

        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, username);

        editor.apply();
        return true;

    }

    public boolean shopLogin(String id, String ownerName, String ContactNumber){

        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_SHOP_ID, id);
        editor.putString(KEY_SHOP_OWNER_NAME, ownerName);
        editor.putString(KEY_SHOP_CONTACT, ContactNumber);

        editor.apply();
        return true;
    }

    public boolean isLoggedIn(){

        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,  Context.MODE_PRIVATE);

        if (sharedPreferences.getString(KEY_USERNAME, null) != null){

            return true;
        }

        return false;
    }

    public boolean isShopLoggedIn(){

        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,  Context.MODE_PRIVATE);

        if (sharedPreferences.getString(KEY_SHOP_ID, null) != null){

            return true;
        }

        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        return true;
    }

    public boolean Shoplogout(){
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        return true;
    }

    public String getUserName(){
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,  Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);

    }

    public String getShopId() {
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SHOP_ID, null);

    }

    public String getUserEmail(){
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,  Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public String getShopOwnerName(){
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,  Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SHOP_OWNER_NAME, null);
    }

    public String getShopContactNumber(){
        SharedPreferences sharedPreferences = mctx.getSharedPreferences(SHARED_PREF_NAME,  Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SHOP_CONTACT, null);
    }

}