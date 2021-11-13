package com.example.jetpackcomposemvvm.di

import com.example.jetpackcomposemvvm.Network.FoodService
import com.example.jetpackcomposemvvm.Network.model.FoodDtoMapper
import com.example.jetpackcomposemvvm.Repository.FoodRepo
import com.example.jetpackcomposemvvm.Repository.FoodRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Singleton
    @Provides
    fun provideFoodRepo(
        foodService: FoodService,
        foodMapper: FoodDtoMapper,
    ): FoodRepo{
        return FoodRepoImpl(
            foodService = foodService,
            mapper = foodMapper
        )
    }
}