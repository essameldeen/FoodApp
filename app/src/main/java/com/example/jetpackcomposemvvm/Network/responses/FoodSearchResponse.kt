package com.example.jetpackcomposemvvm.Network.responses

import com.example.jetpackcomposemvvm.Network.model.FoodNetworkEntity
import com.google.gson.annotations.SerializedName

class FoodSearchResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("results")
    var foods: List<FoodNetworkEntity>

)