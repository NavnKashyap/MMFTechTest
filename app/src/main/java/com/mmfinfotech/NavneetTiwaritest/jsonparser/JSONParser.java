package com.mmfinfotech.NavneetTiwaritest.jsonparser;

import android.content.Context;
import android.content.Intent;

import com.mmfinfotech.NavneetTiwaritest.preferences.SharedPrefrence;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;


public class JSONParser {

    public String MESSAGE = "";
    public boolean RESULT = false;
    public Context context;
    public JSONObject jObj;


    private SharedPrefrence prefrence;

    public JSONParser(Context context, JSONObject response) {
        try {
            this.context = context;
            jObj  = response;
            prefrence = SharedPrefrence.getInstance(context);
            RESULT = true;


        } catch (Exception e) {
            jObj = null;
            e.printStackTrace();
        }
    }


    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }


}