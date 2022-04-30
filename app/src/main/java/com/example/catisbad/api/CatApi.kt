package com.example.catisbad.api

import com.example.catisbad.model.CatsClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("v1/images/search")
    fun getImagesData(
        @Query("api_key") apiKey: String
    ): Call<List<CatsClass>>
}