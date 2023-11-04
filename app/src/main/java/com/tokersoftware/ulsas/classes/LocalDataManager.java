package com.tokersoftware.ulsas.classes;

import android.content.Context;
import android.content.SharedPreferences;



public class LocalDataManager {

    public enum keys {
        email("login_email"),
        password("login_password");

        private String key;

        keys (String key){
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    Context context;

    public LocalDataManager(Context context){
        this.context = context;
    }

    public void setSharedPreference(String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public String getSharedPreference(String key) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).getString(key, "");
    }

    public void clearSharedPreference(){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.clear();
        edit.apply();
    }

    public void removeSharedPreference(String key){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.remove(key);
        edit.apply();
    }
}
