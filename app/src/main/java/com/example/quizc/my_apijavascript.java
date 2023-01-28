package com.example.quizc;

import com.example.quizc.Questions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface my_apijavascript {
     @GET("questions?apiKey=q68vzh8PNp2oWWKCQSTVqDY2Q5opi2XQEJaO8si9&limit=20&tags=javascript")
     Call<List<Questions>>getQuestions();

}
