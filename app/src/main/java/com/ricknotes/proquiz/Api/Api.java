package com.ricknotes.proquiz.Api;

import com.ricknotes.proquiz.Model.Questions;
import com.ricknotes.proquiz.Model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://opentdb.com/";


    @GET("api.php")
    Call<Result> getQuestions(@Query("amount")int amount,
                               @Query("category")String category,
                               @Query("difficulty")String difficulty,
                               @Query("type")String type);
}
