package com.example.jetpackcomposemvvm.Repository

import com.example.jetpackcomposemvvm.domain.model.Food

interface FoodRepo {
    suspend fun search(token: String, page: Int, query: String): List<Food>
    suspend fun get(token: String, id: Int): Food
}