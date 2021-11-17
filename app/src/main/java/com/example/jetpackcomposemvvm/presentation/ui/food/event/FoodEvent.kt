package com.example.jetpackcomposemvvm.presentation.ui.food.event

sealed class FoodEvent {
    data class  GetFoodEvent(
        val id :Int
    ):FoodEvent()
}