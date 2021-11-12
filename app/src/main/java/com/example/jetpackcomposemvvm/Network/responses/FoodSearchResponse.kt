package com.example.jetpackcomposemvvm.Network.responses

import com.example.jetpackcomposemvvm.Network.model.FoodDto
import com.google.gson.annotations.SerializedName

data  class FoodSearchResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("results")
    var foods: List<FoodDto>

)