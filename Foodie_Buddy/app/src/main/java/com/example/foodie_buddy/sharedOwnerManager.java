package com.example.foodie_buddy;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedOwnerManager {
    private static sharedOwnerManager instance;
    private static Context ctx;

    private static final String MyPREFERENCES = "owner" ;
    private static final String Name = "oname";
    private static final String Email = "omail";
    private static final String ID = "identifier";

    private sharedOwnerManager(Context context) {
        ctx = context;
    }

    public static synchronized sharedOwnerManager getInstance(Context context) {
        if (instance == null) {
            instance = new sharedOwnerManager(context);
        }
        return instance;
    }

    public boolean ownerLogin(int id,String name,String email)
    {
        SharedPreferences s = ctx.getSharedPreferences(MyPREFERENCES,ctx.MODE_PRIVATE);
        SharedPreferences.Editor e = s.edit();

        e.putInt(ID, id);
        e.putString(Name,name);
        e.putString(Email,email);
        e.apply();

        return true;
    }

    public boolean isOwnerLoggedIn()
    {
        SharedPreferences s = ctx.getSharedPreferences(MyPREFERENCES,ctx.MODE_PRIVATE);
        if(s.getString(Email,null) != null)//a user with the given key(Email id) is logged in
        {
            return true;
        }

        return false;
    }

    public int getOwnerId()
    {
        SharedPreferences s = ctx.getSharedPreferences(MyPREFERENCES,ctx.MODE_PRIVATE);
        return s.getInt(ID,-1);
    }

    public String getOwnerName()
    {
        SharedPreferences s = ctx.getSharedPreferences(MyPREFERENCES,ctx.MODE_PRIVATE);
        return s.getString(Name,null);
    }

    public String getOwnerEmail()
    {
        SharedPreferences s = ctx.getSharedPreferences(MyPREFERENCES,ctx.MODE_PRIVATE);
        return s.getString(Email,null);
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
