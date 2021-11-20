package com.example.jetpackcomposemvvm.presentation.ui.food.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposemvvm.Repository.FoodRepo
import com.example.jetpackcomposemvvm.domain.model.Food
import com.example.jetpackcomposemvvm.presentation.ui.food.event.FoodEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


const val STATE_KEY_RECIPE = "food.state.food.key"
@HiltViewModel
class FoodViewModel
@Inject
constructor(
    private val repo: FoodRepo,
    private val token: String,
    @Assisted private val state: SavedStateHandle
) :
    ViewModel() {
    val foods: MutableState<Food?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    init {
        //restore if process dies
     state.get<Int>(STATE_KEY_RECIPE)?.let{ recipeId ->
           onTriggerEvent(FoodEvent.GetFoodEvent(recipeId))      }
    }

    fun onTriggerEvent(event: FoodEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is FoodEvent.GetFoodEvent -> {
                        if (foods.value == null) {
                            getFood(event.id)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("ViewModelFood", "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private suspend fun getFood(id: Int) {
        loading.value = true

        // simulate a delay to show loading
        delay(1000)

        val recipe = repo.get(token = token, id = id)
        this.foods.value = recipe

        state.set(STATE_KEY_RECIPE, recipe.id)

        loading.value = false
    }


}