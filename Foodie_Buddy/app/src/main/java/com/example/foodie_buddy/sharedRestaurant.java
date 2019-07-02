package com.example.foodie_buddy;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class sharedRestaurant {
    private static sharedRestaurant instance;
    private static Context ctx;
    private ArrayList<Restaurants> rList;

    private sharedRestaurant(Context context) {
        ctx = context;
    }

    //make it static to get direct access from the class and then use the singleton pattern
    public static synchronized sharedRestaurant getInstance(Context context) {
        if (instance == null) {
            instance = new sharedRestaurant(context);
        }
        return instance;
    }

    public void add(String a,String b,String c,String d)
    {
        rList.add(new Restaurants(a,b,c,d));
    }

    public ArrayList<Restaurants> getRest()
    {
        return rList;
    }
}
