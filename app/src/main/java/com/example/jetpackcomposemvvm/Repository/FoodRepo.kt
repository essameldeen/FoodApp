package com.example.jetpackcomposemvvm.Repository

import com.example.jetpackcomposemvvm.domain.model.Food

interface FoodRepo {
    suspend fun search(toke: String, page: Int, query: String): List<Food>
    suspend fun get(toke: String, id: Int): Food
}