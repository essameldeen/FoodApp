package com.example.jetpackcomposemvvm.Network

import com.example.jetpackcomposemvvm.Network.model.FoodDto
import com.example.jetpackcomposemvvm.Network.responses.FoodSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FoodService {
    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") value: String
    ): FoodSearchResponse

    @GET("get")
    suspend fun get(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): FoodDto
}