package com.example.foodie_buddy;

public class Restaurants {

    public String name;
    public String type;
    public String opens_at;
    public String closes_at;

    public Restaurants(String a,String b,String c,String d)
    {
        name = a;
        type = b;
        opens_at = c;
        closes_at = d;
    }

    public String format()
    {
        return name+"\n"+type+"\nOpens at: "+opens_at+"\nCloses at: "+closes_at;
    }
}
