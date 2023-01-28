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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public EditText userName;
    public EditText userEmail;
    public EditText userPassword;
    public EditText confirmPassword;
   public  EditText age;
    public EditText phoneNo;
    public  ProgressBar progressBar;
    public FirebaseAuth mAuth;
    public DatabaseReference databaseReference;
    public Button b;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Application-SIGNUP");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        userName=findViewById(R.id.username);

        userEmail=findViewById(R.id.useremail);
        userPassword=findViewById(R.id.userPassword);
        confirmPassword=findViewById(R.id.confirm_password);
        age=findViewById(R.id.age);
        phoneNo=findViewById(R.id.phoneno);
        progressBar=findViewById(R.id.progressbar);
        b=findViewById(R.id.sign_in_button);
        SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
        Boolean counter=sharedPreferences.getBoolean("logincounter",Boolean.valueOf(String.valueOf(MODE_PRIVATE)));
        String email=sharedPreferences.getString("useremail",String.valueOf(MODE_PRIVATE));
        if(counter){
            startActivity(new Intent(MainActivity.this,first_screen.class));
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });
    }
    public void registerNewUser(){
        progressBar.setVisibility(View.VISIBLE);
        String userNameText,userEmailText,passwordText,confirmPasswordText,ageText,phoneNoText;
        userEmailText=userEmail.getText().toString();
        userNameText=userName.getText().toString();

     temp=userNameText.toUpperCase(Locale.ROOT);
        Singleton a=Singleton.getInstance();
        a.setData(temp);
        passwordText=userPassword.getText().toString();
        confirmPasswordText=confirmPassword.getText().toString();
        ageText=age.getText().toString();
        phoneNoText=phoneNo.getText().toString();
        if (TextUtils.isEmpty(userEmailText)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(userNameText)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter name!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(passwordText)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(confirmPasswordText)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter confirm password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(ageText)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter age!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(phoneNoText)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter phoneNo!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if(passwordText.equals(confirmPasswordText)){
            mAuth
                    .createUserWithEmailAndPassword(userEmailText,passwordText)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                                "Registration successful!",
                                                Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
                                progressBar.setVisibility(View.GONE);


                                // if the user created intent to login activity
                                addUsertodatabase(userEmailText,temp,ageText,phoneNoText);
                                savedata(userEmailText);


                            }
                            else {

                                // Registration failed
                                Toast.makeText(
                                                getApplicationContext(),
                                                "Registration failed!!"
                                                        + " Please try again later",
                                                Toast.LENGTH_LONG)
                                        .show();

                                // hide the progress bar
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
        else{
            Toast.makeText(getApplicationContext(),
                            "Password and confirm password not matched",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }



    }
    void  savedata(String email){
        SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("logincounter",true);
        editor.putString("useremail",email);
        editor.apply();
        startActivity(new Intent(MainActivity.this,first_screen.class));
        finish();
    }
    private void addUsertodatabase(String email,String name,String age,String phone){
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("USER").child(name).setValue(new user(email,name,age,phone));

    }
    public void onBackPressed() {
        finishAffinity();
    }
}