package com.example.prototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SocialLoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_login);

//        printHashKey();
        CallbackManager callbackManager = CallbackManager.Factory.create();

        Log.e("jeff",callbackManager.toString());
//        Toast.makeText(getApplicationContext(), callbackManager.toString(), Toast.LENGTH_SHORT).show();

        final String EMAIL = "email";

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email", "user_friends");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onError(@NonNull FacebookException e) {
                Log.i("Error" , "Error");
            }

            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent i = new Intent(SocialLoginActivity.this, home_page.class);
                startActivity(i);
                System.out.print("Logged in");
            }

            @Override
            public void onCancel() {
                // App code

            }
        });

    }





}