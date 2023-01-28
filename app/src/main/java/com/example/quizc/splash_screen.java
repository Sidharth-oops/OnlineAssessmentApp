package com.example.quizc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.VideoView;



public class splash_screen extends AppCompatActivity {
    VideoView videoView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        videoView=(VideoView) findViewById(R.id.videoView);
        String videopath=new StringBuilder("android.resource://").append(getPackageName()).append("/raw/splash_video").toString();
        videoView.setVideoPath(videopath);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
                                    sharedPreferences.edit().clear().commit();
                                    startActivity(new Intent(splash_screen.this,login.class));
                                    finish();
                                }
                            },3000);
            }
        });
        videoView.start();

    }
}