package com.mmfinfotech.NavneetTiwaritest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mmfinfotech.NavneetTiwaritest.interfacess.Consts;
import com.mmfinfotech.NavneetTiwaritest.preferences.SharedPrefrence;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.Arrays;

import static java.util.Arrays.*;

public class MainActivity extends AppCompatActivity {
    private static final String EMAIL = "email";
    LoginButton loginButton;
    CallbackManager callbackManager;
    SharedPrefrence preferences;

    String socialId="",userName="",loginType="",email="",mobile="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences=SharedPrefrence.getInstance(MainActivity.this);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        if (preferences.getBooleanValue(Consts.IS_REGISTERED)) {
            Intent in =new Intent(MainActivity.this,TabActivity.class);
            startActivity(in);
            finish();
        }else {
            loginButton.setVisibility(View.VISIBLE);
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(getApplicationContext());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loginButton.setReadPermissions(Arrays.asList(EMAIL));
            }
        });
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.e("API123", loggedIn + " ??");

                if (!loggedIn) {

                    //Using Graph API
                    getUserProfile(AccessToken.getCurrentAccessToken());
                }
            }

            @Override
            public void onCancel() {
                // App code
                Log.e("TAG", "onCancel: " );
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("TAG", "onError: "+error );
            }


        });
//        printHashKey(MainActivity.this);
    }




    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        String first_name="",last_name="",emailFB="",mobilefb="",id="",image_url="";
                        try {
                            first_name = object.getString("first_name");
                            last_name = object.getString("last_name");
                            id = object.getString("id");
                            try {
                                image_url = "https://graph.facebook.com/" + id + "/picture?type=normal"; } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {  emailFB = object.getString("email");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {  mobilefb = object.getString("mobile");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        userName=first_name+" "+last_name;
                        preferences.setValue(Consts.FULL_NAME,userName);
                        if(!emailFB.equalsIgnoreCase(""))
                            email=emailFB;

                        preferences.setValue(Consts.EMAIL,email);
                        if(!mobilefb.equalsIgnoreCase(""))
                            mobile=mobilefb;
                            preferences.setValue(Consts.MOBILE,mobile);
                        socialId=id;
                        loginType=Consts.FACEBOOK;

                        if(!image_url.equalsIgnoreCase(""))
                            preferences.setValue(Consts.IMAGE_URL_FB,image_url);
                        preferences.setBooleanValue(Consts.IS_REGISTERED, true);
                        Intent in =new Intent(MainActivity.this,TabActivity.class);
                        startActivity(in);
                    }

                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }
    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(
                    "com.mmfinfotech.NavneetTiwaritest",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", "KeyHash:"+ Base64.encodeToString(md.digest(),
                        Base64.DEFAULT));
            /*    Toast.makeText(pContext.getApplicationContext(), Base64.encodeToString(md.digest(),
                        Base64.DEFAULT), Toast.LENGTH_LONG).show();*/
            }
        } catch (Exception e) {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}