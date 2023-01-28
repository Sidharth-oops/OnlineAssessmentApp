package com.example.quizc;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class last_page extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView result_text;
    LinearLayout moveto_main;
    ImageView imageView;
    TextView message;
    public static final String ACCOUNT_SID = "AC8c469bdb0991a6932f9f304a637d3fd0";
    public static final String AUTH_TOKEN = "20b8484f1af899962e9c58f6475f6d46";
    DatabaseReference databaseReference;
    String phoneNo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_last_page);
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        circularProgressBar=findViewById(R.id.circularProgressBar);
        result_text=findViewById(R.id.result_text);
        imageView=findViewById(R.id.image);
        moveto_main=findViewById(R.id.movetomain);
        message=findViewById(R.id.message);
        Singleton b=Singleton.getInstance();
        String name=b.getData();
        Intent intent = getIntent();
        int intValue = intent.getIntExtra("Correct", 0);
        String genre=intent.getStringExtra("Genre");
        databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("HISTORY").child(name).push().setValue(new score(intValue,genre));
        databaseReference= FirebaseDatabase.getInstance().getReference("USER");
        databaseReference.child(name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                       if(task.getResult().exists()){
                           progress.dismiss();
                           Toast.makeText(last_page.this, "RESPONSE SUCCESSFUL", Toast.LENGTH_SHORT).show();

                           DataSnapshot dataSnapshot=task.getResult();
                           Log.i(TAG, dataSnapshot.toString());
                           phoneNo=String.valueOf(dataSnapshot.child("phone").getValue());
                           if(intValue>=15){
                               message.setText("CONGRATULATIONS YOU ARE QUALIFIED FOR NEXT ROUND "+name);
                               String from = "+17207123678";
                               String to = "+91"+phoneNo;
                               String body;
                               if(intValue>=15){
                                   body = "CONGRATULATIONS YOU ARE QUALIFIED FOR NEXT ROUND "+name;}
                               else{
                                   body="SORRY, YOU ARE NOT QUALIFIED FOR NEXT ROUND "+name;
                               }


                               String base64EncodedCredentials = "Basic " + Base64.encodeToString(
                                       (ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP
                               );

                               Map<String, String> data = new HashMap<>();
                               data.put("From", from);
                               data.put("To", to);
                               data.put("Body", body);

                               Retrofit retrofit = new Retrofit.Builder()
                                       .baseUrl("https://api.twilio.com/2010-04-01/")
                                       .build();
                               twillio_api api = retrofit.create(twillio_api.class);

                               api.sendMessage(ACCOUNT_SID, base64EncodedCredentials, data).enqueue(new Callback<ResponseBody>() {
                                   @Override
                                   public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                       if (response.isSuccessful()) Log.d("TAG", "onResponse->success");
                                       else Log.d("TAG", "onResponse->failure");
                                   }

                                   @Override
                                   public void onFailure(Call<ResponseBody> call, Throwable t) {
                                       Log.d("TAG", "onFailure");
                                   }
                               });
                           }
                           else{
                               message.setText("SORRY, YOU ARE NOT QUALIFIED FOR NEXT ROUND "+name);
                               String from = "+17207123678";
                               String to = "+91"+phoneNo;
                               String body;
                               if(intValue>=15){
                                   body = "CONGRATULATIONS YOU ARE QUALIFIED FOR NEXT ROUND "+name;}
                               else{
                                   body="SORRY, YOU ARE NOT QUALIFIED FOR NEXT ROUND "+name;
                               }


                               String base64EncodedCredentials = "Basic " + Base64.encodeToString(
                                       (ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP
                               );

                               Map<String, String> data = new HashMap<>();
                               data.put("From", from);
                               data.put("To", to);
                               data.put("Body", body);

                               Retrofit retrofit = new Retrofit.Builder()
                                       .baseUrl("https://api.twilio.com/2010-04-01/")
                                       .build();
                               twillio_api api = retrofit.create(twillio_api.class);

                               api.sendMessage(ACCOUNT_SID, base64EncodedCredentials, data).enqueue(new Callback<ResponseBody>() {
                                   @Override
                                   public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                       if (response.isSuccessful()) Log.d("TAG", "onResponse->success");
                                       else Log.d("TAG", "onResponse->failure");
                                   }

                                   @Override
                                   public void onFailure(Call<ResponseBody> call, Throwable t) {
                                       Log.d("TAG", "onFailure");
                                   }
                               });
                           }
                       }
                }
                else{

                }
            }
        });





        circularProgressBar.setProgress(intValue);
        result_text.setText(String.valueOf(intValue)+"/20");
        if(intValue>=15){
            imageView.setImageResource(R.drawable.cm);
        }
        else{
            imageView.setImageResource(R.drawable.sm);

        }
        moveto_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                startActivity(new Intent(last_page.this,login.class));
                finish();
            }
        });






    }
}