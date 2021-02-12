package com.mmfinfotech.NavneetTiwaritest.https;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.mmfinfotech.NavneetTiwaritest.interfacess.Consts;
import com.mmfinfotech.NavneetTiwaritest.interfacess.Helper;
import com.mmfinfotech.NavneetTiwaritest.jsonparser.JSONParser;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

public class HttpsRequest {
    private String match;
    private Map<String, String> params;
    private Map<String, File> fileparams;
    private Context ctx;
    private JSONObject jObject;



    public HttpsRequest(String match, Context ctx) {
        this.match = match;
        this.ctx = ctx;
    }



      public void stringGet(final String TAG, final Helper h) {
        AndroidNetworking.get(Consts.BASE_URL + match)
                .setTag("test")

                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e(TAG, " response body --->" + response.toString());
                        JSONParser jsonParser = new JSONParser(ctx, response);
                        if (jsonParser.RESULT) {

                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, response);
                        } else {
                            h.backResponse(jsonParser.RESULT, jsonParser.MESSAGE, null);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, " error body --->" + anError.getErrorBody() + " error msg --->" + anError.getMessage());
                    }
                });
    }

}