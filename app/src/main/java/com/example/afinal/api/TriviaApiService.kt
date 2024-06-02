package com.example.afinal.api

import com.example.afinal.models.CategoriesResponse
import com.example.afinal.models.TriviaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApiService {
    @GET("api.php")
    fun getQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int?,
        @Query("difficulty") difficulty: String?,
        @Query("type") type: String?
    ): Call<TriviaResponse>

    @GET("api_category.php")
    fun getCategories(): Call<CategoriesResponse>
}


