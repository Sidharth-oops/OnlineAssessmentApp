package com.example.quizc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class genre extends AppCompatActivity {
     public LinearLayout linux,javascript,html,sql;
     public String choice="";
    public AppCompatButton b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        javascript=findViewById(R.id.javascript_linear);
        linux=findViewById(R.id.duck_linear);
        html=findViewById(R.id.html_linear);
        sql=findViewById(R.id.sql_linear);
        b=findViewById(R.id.start_quiz);
       javascript.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               choice="javascript";
               javascript.setBackgroundResource(R.drawable.round_back);
               linux.setBackgroundResource(R.drawable.roundbackicon);
               sql.setBackgroundResource(R.drawable.roundbackicon);
               html.setBackgroundResource(R.drawable.roundbackicon);


           }
       });
        linux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice="linux";
                linux.setBackgroundResource(R.drawable.round_back);
                javascript.setBackgroundResource(R.drawable.roundbackicon);
                html.setBackgroundResource(R.drawable.roundbackicon);
                sql.setBackgroundResource(R.drawable.roundbackicon);
            }
        });
        sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice="sql";
                sql.setBackgroundResource(R.drawable.round_back);
                javascript.setBackgroundResource(R.drawable.roundbackicon);
                html.setBackgroundResource(R.drawable.roundbackicon);
                linux.setBackgroundResource(R.drawable.roundbackicon);
            }
        });
        html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice="html";
                html.setBackgroundResource(R.drawable.round_back);
                linux.setBackgroundResource(R.drawable.roundbackicon);
                sql.setBackgroundResource(R.drawable.roundbackicon);
                javascript.setBackgroundResource(R.drawable.roundbackicon);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice.isEmpty()==true){
                    Toast.makeText(getApplicationContext(),
                                    "PLEASE SELECT THE GENRE",
                                    Toast.LENGTH_LONG)
                            .show();
                }
                if(choice.equals("javascript")){
                    startActivity(new Intent(genre.this,javascript_first.class));
                    finish();
                }
                if(choice.equals("html")){
                    startActivity(new Intent(genre.this,html_first.class));
                    finish();
                }
                if(choice.equals("sql")){
                    startActivity(new Intent(genre.this,sql_first.class));
                    finish();
                }
                if(choice.equals("linux")){
                    startActivity(new Intent(genre.this,linux_first.class));
                    finish();
                }

            }
        });




    }
}