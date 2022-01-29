package com.example.prototype;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_page extends AppCompatActivity {
    EditText lUser,lPass;
    boolean passvil;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        lUser = findViewById(R.id.email);
        lPass = findViewById(R.id.password);

        ImageButton regbtn = (ImageButton) findViewById(R.id.regbtn);
        ImageButton lgnbtn = (ImageButton) findViewById(R.id.lgnbtn);

        mAuth = FirebaseAuth.getInstance();


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login_page.this, "Creating an account", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login_page.this,signup_page.class);
                startActivity(intent);

            }
        });

        lgnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = lUser.getText().toString().trim();

                String password = lPass.getText().toString().trim();

                //if username is empty
                if(TextUtils.isEmpty(user)){
                    lUser.setError("Please Fill up the Username");
                    return;
                }
                //if password is empty
                if(TextUtils.isEmpty(password)){
                    lPass.setError("Please Fill up the Password");
                    return;
                }


                mAuth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login_page.this,"Logging In", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login_page.this, home_page.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(login_page.this, "Please check your email and password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        lPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=lPass.getRight()-lPass.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=lPass.getSelectionEnd();
                        if(passvil){
                            //drawable image
                            lPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_off_24,0);
                            //hide password
                            lPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passvil=false;
                        }else{
                            //drawable image
                            lPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_remove_red_eye_24,0);
                            //hide password
                            lPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passvil=true;
                        }
                        lPass.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });



    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int i){
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}