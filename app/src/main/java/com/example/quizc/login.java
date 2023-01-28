package com.example.quizc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class login extends AppCompatActivity {
    public EditText userEmail;
    public EditText userPassword;
    public Button loginButton;
    public Button registerButton;
    public Button forgotPassword;
    public ProgressBar progressBar;
    private FirebaseAuth mAuth;
    public EditText userName;
    String p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Application-LOGIN");

        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        userEmail=findViewById(R.id.useremaillogin);
        userPassword=findViewById(R.id.userPasswordlogin);
        loginButton=findViewById(R.id.login_button);
        registerButton=findViewById(R.id.rgs_button);
        forgotPassword=findViewById(R.id.forgot_button);
        progressBar=findViewById(R.id.progressbarlogin);
        userName=findViewById(R.id.username);



        SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
        Boolean counter=sharedPreferences.getBoolean("logincounter",Boolean.valueOf(String.valueOf(MODE_PRIVATE)));
        String email=sharedPreferences.getString("useremail",String.valueOf(MODE_PRIVATE));
        if(counter){
            startActivity(new Intent(login.this,first_screen.class));
        }

        forgotPassword.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String email=userEmail.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),
                                    "Please Enter The Email",
                                    Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                else{

                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(
                                                    getApplicationContext(),
                                                    "Email Sent To Your Registered Account",
                                                    Toast.LENGTH_LONG)
                                            .show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),
                                                    "Please Enter registered Email",
                                                    Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });}
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,MainActivity.class));
                finish();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserAccount();
            }
        });

    }
    private void loginUserAccount()
    {         String s=userName.getText().toString();
        p=s.toUpperCase(Locale.ROOT);

        Singleton a=Singleton.getInstance();
        a.setData(p);

        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = userEmail.getText().toString();
        password = userPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }



        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();


                                    progressBar.setVisibility(View.GONE);

                                    savedata(email);
                                }

                                else {


                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();


                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
    }
    void  savedata(String email){
        SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("logincounter",true);
        editor.putString("useremail",email);
        editor.apply();
        startActivity(new Intent(login.this,first_screen.class));
        finish();
    }
    public void onBackPressed() {
        finishAffinity();
    }

}