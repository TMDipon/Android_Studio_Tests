package com.example.foodiebuddy;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPrefManager {
    private static sharedPrefManager instance;
    private static Context ctx;

    private static final String MyPREFERENCES = "pref" ;
    private static final String Name = "uname";
    private static final String Email = "umail";

    private sharedPrefManager(Context context) {
        ctx = context;
    }

    //make it static to get direct access from the class and then use the singleton pattern
    public static synchronized sharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new sharedPrefManager(context);
        }
        return instance;
    }

    public boolean userLogin(String name,String email)
    {
        SharedPreferences s = ctx.getSharedPreferences(MyPREFERENCES,ctx.MODE_PRIVATE);
        SharedPreferences.Editor e = s.edit();

        e.putString(Name,name);
        e.putString(Email,email);
        e.apply();

        return true;
    }

    public boolean isLoggedIn()
    {
        SharedPreferences s = ctx.getSharedPreferences(MyPREFERENCES,ctx.MODE_PRIVATE);
        if(s.getString(Email,null) != null)//a user with the given key(Email id) is logged in
        {
            return true;
        }

        return false;
    }

    public boolean logout()
    {
        SharedPreferences s = ctx.getSharedPreferences(MyPREFERENCES,ctx.MODE_PRIVATE);
        SharedPreferences.Editor e = s.edit();
        e.clear();
        e.apply();
        return true;
    }
}
