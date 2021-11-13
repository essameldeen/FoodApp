package com.example.jetpackcomposemvvm.di

import com.example.jetpackcomposemvvm.Network.FoodService
import com.example.jetpackcomposemvvm.Network.model.FoodDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideFoodMapper(): FoodDtoMapper {
        return FoodDtoMapper()
    }

    @Singleton
    @Provides
    fun provideFoodService(): FoodService {
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(FoodService::class.java)
    }
    @Singleton
    @Provides
    fun provideAuthToken(): String{
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}