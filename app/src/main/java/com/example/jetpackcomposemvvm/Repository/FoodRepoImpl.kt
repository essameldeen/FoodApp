package com.example.jetpackcomposemvvm.Repository

import com.example.jetpackcomposemvvm.Network.FoodService
import com.example.jetpackcomposemvvm.Network.model.FoodDtoMapper
import com.example.jetpackcomposemvvm.domain.model.Food

class FoodRepoImpl(
    private val foodService: FoodService,
    private val mapper: FoodDtoMapper
) : FoodRepo {
    override suspend fun search(token: String, page: Int, query: String): List<Food> {
        val result = foodService.search(token = token, page = page, value = query).foods
        return mapper.toDomainList(result)
    }

    override suspend fun get(token: String, id: Int): Food {
        val reuslt = foodService.get(token = token, id = id)
        return mapper.mapToDomainModel(reuslt)
    }
}