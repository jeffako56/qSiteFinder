package com.example.prototype;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_page extends AppCompatActivity {

    EditText sUser,sEmail,sPass,sPhone;
    private ImageButton createbtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        mAuth = FirebaseAuth.getInstance();

        createbtn = (ImageButton) findViewById(R.id.create_btn);
        sUser = findViewById(R.id.name);
        sEmail = findViewById(R.id.email);
        sPass = findViewById(R.id.password);
        sPhone = findViewById(R.id.mobile);




        //click create button
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = sUser.getText().toString().trim();
                String email = sEmail.getText().toString().trim();
                String password = sPass.getText().toString().trim();

                //if username is empty
                if(TextUtils.isEmpty(user)){
                    sUser.setError("Please fill up the Username");
                    return;
                }
                //if email is empty
                else if(TextUtils.isEmpty(email)){
                    sEmail.setError("Please fill up the Email");
                    return;
                }

                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    sEmail.setError("Please input valid email address");
                }
                //if password is empty
                else if(TextUtils.isEmpty(password)){
                    sPass.setError("Please fill up the Password");
                    return;
                }
                //password length
                else if(password.length()<8){
                    sPass.setError("Password must be greater than 7");
                    return;
                }

                //creating an account
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signup_page.this, "Account created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signup_page.this, login_page.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(signup_page.this, "Sign up Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }


}