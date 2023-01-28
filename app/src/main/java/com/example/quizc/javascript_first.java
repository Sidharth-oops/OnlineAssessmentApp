package com.example.quizc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class javascript_first extends AppCompatActivity {
    String url="https://quizapi.io/api/v1/";
    private TextView question;
    private TextView questionNumbers;
    private AppCompatButton option1,option2,option3,option4,next_btn;
    private Timer quiz_timer;
    private int totalTimeInMin=1;
    private int seconds=0;
    private int correctAnswer=0;
    private int incorrectAnswer=0;
    public  int i=0;
    boolean checkClick=false;
    String correct="";
    boolean once=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_javascript_first);
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        final ImageView backbtn=findViewById(R.id.back_btn);
        final TextView timer=findViewById(R.id.timer);
        question=findViewById(R.id.question);
        questionNumbers=findViewById(R.id.questionNumber);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        next_btn=findViewById(R.id.next_btn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz_timer.purge();
                quiz_timer.cancel();
                startActivity(new Intent(javascript_first.this,genre.class));
                finish();
            }
        });

        Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
       my_apijavascript my_api=retrofit.create(my_apijavascript.class);
        Call<List<Questions>>call=my_api.getQuestions();
        call.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                progress.dismiss();
                startTimer(timer);
                List<Questions> data=  response.body();

                    String q=data.get(i).getQuestion();
                    String o1=data.get(i).answers.getAnswer_a();
                String o2=data.get(i).answers.getAnswer_b();
                String o3=data.get(i).answers.getAnswer_c();
                String o4=data.get(i).answers.getAnswer_d();



                    question.setText(q);
                    option1.setText(o1);
                option2.setText(o2);
                if(o3==null){

                    option3.setVisibility(View.INVISIBLE);
                }
                else{
                    option3.setText(o3);
                    option3.setVisibility(View.VISIBLE);
                }
                if(o4==null){

                    option4.setVisibility(View.INVISIBLE);
                }
                else{
                    option4.setText(o4);
                    option4.setVisibility(View.VISIBLE);
                }

                String tc1=data.get(i).correct_answers.getAnsAC();
                String tc2=data.get(i).correct_answers.getAnsBC();
                String tc3=data.get(i).correct_answers.getAnsCC();
                String tc4=data.get(i).correct_answers.getAnsDC();
                if(tc1.equals("true")){
                    correct=o1;
                }
                else if(tc2.equals("true")){
                    correct=o2;
                }
                else if(tc3.equals("true")){
                    correct=o3;
                }
                else if(tc4.equals("true")){
                    correct=o4;
                }

                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkClick==false){
                        if(correct.equals(o1)){
                            option1.setBackgroundResource(R.drawable.correct_background);
                            correctAnswer=correctAnswer+1;
                            checkClick=true;
                        }
                        else{
                            option1.setBackgroundResource((R.drawable.incorrect_background));
                            checkClick=true;
                            option1.setTextColor(Color.WHITE);
                        }
                        if(correct.equals(o2)){
                            option2.setBackgroundResource(R.drawable.correct_background);

                            checkClick=true;
                        }
                        if(correct.equals(o3)){
                            option3.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }
                        if(correct.equals(o4)){
                            option4.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }

                    }}
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkClick==false){
                        if(correct.equals(o2)){
                            option2.setBackgroundResource(R.drawable.correct_background);
                            correctAnswer=correctAnswer+1;
                            checkClick=true;
                        }
                        else{
                            option2.setBackgroundResource((R.drawable.incorrect_background));
                            option2.setTextColor(Color.WHITE);
                            checkClick=true;
                        }
                        if(correct.equals(o1)){
                            option1.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }
                        if(correct.equals(o3)){
                            option3.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }
                        if(correct.equals(o4)){
                            option4.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }

                    }}
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkClick==false){
                        if(correct.equals(o3)){
                            option3.setBackgroundResource(R.drawable.correct_background);
                            correctAnswer=correctAnswer+1;
                            checkClick=true;
                        }
                        else{
                            option3.setBackgroundResource((R.drawable.incorrect_background));
                            checkClick=true;
                            option3.setTextColor(Color.WHITE);
                        }
                        if(correct.equals(o2)){
                            option2.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }
                        if(correct.equals(o1)){
                            option1.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }
                        if(correct.equals(o4)){
                            option4.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }

                    }}
                });
                option4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkClick==false){
                        if(correct.equals(o4)){
                            option4.setBackgroundResource(R.drawable.correct_background);
                            correctAnswer=correctAnswer+1;
                            checkClick=true;
                        }
                        else{
                            option4.setBackgroundResource((R.drawable.incorrect_background));
                            option4.setTextColor(Color.WHITE);
                            checkClick=true;
                        }
                        if(correct.equals(o2)){
                            option2.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }
                        if(correct.equals(o3)){
                            option3.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }
                        if(correct.equals(o1)){
                            option1.setBackgroundResource(R.drawable.correct_background);
                            checkClick=true;
                        }

                    }}
                });


                    next_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(once==false){

                            if(i==data.size()-1){
                                Intent intent=new Intent(javascript_first.this, last_page.class);
                                intent.putExtra("Correct", correctAnswer);
                                intent.putExtra("Genre","JAVASCRIPT");
                                startActivity(intent);
                                once=true;
                                finish();

                            }
                           else{
                               i++;
                            }}
                            checkClick=false;
                            option1.setBackgroundResource(R.drawable.round_back_option_white_stroke);
                            option2.setBackgroundResource(R.drawable.round_back_option_white_stroke);
                            option3.setBackgroundResource(R.drawable.round_back_option_white_stroke);
                            option4.setBackgroundResource(R.drawable.round_back_option_white_stroke);
                            option1.setTextColor(Color.parseColor("#1f6bb8"));
                            option2.setTextColor(Color.parseColor("#1f6bb8"));
                            option3.setTextColor(Color.parseColor("#1f6bb8"));
                            option4.setTextColor(Color.parseColor("#1f6bb8"));




                            questionNumbers.setText(String.valueOf(i+1)+"/20");
                            String q=data.get(i).getQuestion();
                            question.setText(q);
                            String o1=data.get(i).answers.getAnswer_a();
                            String o2=data.get(i).answers.getAnswer_b();
                            String o3=data.get(i).answers.getAnswer_c();
                            String o4=data.get(i).answers.getAnswer_d();
                            option1.setText(o1);
                            option2.setText(o2);
                            if(o3==null){

                                option3.setVisibility(View.INVISIBLE);
                            }
                            else{
                                option3.setText(o3);
                                option3.setVisibility(View.VISIBLE);
                            }

                            if(o4==null){
                                option4.setVisibility(View.INVISIBLE);
                            }
                            else{
                                option4.setText(o4);
                                option4.setVisibility(View.VISIBLE);
                            }
                            String tc1=data.get(i).correct_answers.getAnsAC();
                            String tc2=data.get(i).correct_answers.getAnsBC();
                            String tc3=data.get(i).correct_answers.getAnsCC();
                            String tc4=data.get(i).correct_answers.getAnsDC();
                            if(tc1.equals("true")){
                                correct=o1;
                            }
                            else if(tc2.equals("true")){
                                correct=o2;
                            }
                            else if(tc3.equals("true")){
                                correct=o3;
                            }
                            else if(tc4.equals("true")){
                                correct=o4;
                            }

                            option1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(checkClick==false){
                                    if(correct.equals(o1)){
                                        option1.setBackgroundResource(R.drawable.correct_background);
                                        correctAnswer=correctAnswer+1;
                                        checkClick=true;
                                    }
                                    else{
                                        option1.setBackgroundResource((R.drawable.incorrect_background));
                                        option1.setTextColor(Color.WHITE);
                                        checkClick=true;

                                    }
                                    if(correct.equals(o2)){
                                        option2.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;

                                    }
                                    if(correct.equals(o3)){
                                        option3.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }
                                    if(correct.equals(o4)){
                                        option4.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }

                                }}
                            });
                            option2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(checkClick==false){
                                    if(correct.equals(o2)){
                                        option2.setBackgroundResource(R.drawable.correct_background);
                                        correctAnswer=correctAnswer+1;
                                        checkClick=true;
                                    }
                                    else{
                                        option2.setBackgroundResource((R.drawable.incorrect_background));
                                        option2.setTextColor(Color.WHITE);
                                        checkClick=true;

                                    }
                                    if(correct.equals(o1)){
                                        option1.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }
                                    if(correct.equals(o3)){
                                        option3.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }
                                    if(correct.equals(o4)){
                                        option4.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }

                                }}
                            });
                            option3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(checkClick==false){
                                    if(correct.equals(o3)){
                                        option3.setBackgroundResource(R.drawable.correct_background);
                                        correctAnswer=correctAnswer+1;
                                        checkClick=true;
                                    }
                                    else{
                                        option3.setBackgroundResource((R.drawable.incorrect_background));
                                        option3.setTextColor(Color.WHITE);
                                        checkClick=true;

                                    }
                                    if(correct.equals(o2)){
                                        option2.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }
                                    if(correct.equals(o1)){
                                        option1.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }
                                    if(correct.equals(o4)){
                                        option4.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }

                                }}
                            });
                            option4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(checkClick==false){
                                    if(correct.equals(o4)){
                                        option4.setBackgroundResource(R.drawable.correct_background);
                                        correctAnswer=correctAnswer+1;
                                        checkClick=true;
                                    }
                                    else{
                                        option4.setBackgroundResource((R.drawable.incorrect_background));
                                        option4.setTextColor(Color.WHITE);
                                        checkClick=true;

                                    }
                                    if(correct.equals(o2)){
                                        option2.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }
                                    if(correct.equals(o3)){
                                        option3.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }
                                    if(correct.equals(o1)){
                                        option1.setBackgroundResource(R.drawable.correct_background);
                                        checkClick=true;
                                    }}

                                }
                            });
                            if(i==data.size()-1){
                                next_btn.setText("SUBMIT");
                            }

                        }


                    });





                }


            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {

            }
        });


    }
    private  void startTimer(TextView timerTextView){
        quiz_timer=new Timer();
        quiz_timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

              if(seconds==0&&totalTimeInMin==0){
                  quiz_timer.purge();
                  quiz_timer.cancel();
                  if(once==false){

                  Intent intent=new Intent(javascript_first.this,last_page.class);
                  intent.putExtra("Correct", correctAnswer);
                  intent.putExtra("Genre","JAVASCRIPT");
                  startActivity(intent);
                  finish();}
              }
                else if(seconds==0){
                    totalTimeInMin--;
                    seconds=59;
                }
              else{
                  seconds--;
              }
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                        String finalMinutes=String.valueOf(totalTimeInMin);
                       String finalSeconds=String.valueOf(seconds);
                       if(finalMinutes.length()==1){
                           finalMinutes='0'+finalMinutes;
                       }
                       if(finalSeconds.length()==1){
                           finalSeconds='0'+finalSeconds;
                       }
                       timerTextView.setText(finalMinutes+":"+finalSeconds);

                  }
              });
            }
        },1000,1000);
    }
    public void onBackPressed(){
        quiz_timer.purge();
        quiz_timer.cancel();
        startActivity(new Intent(javascript_first.this,genre.class));
        finish();
    }
}