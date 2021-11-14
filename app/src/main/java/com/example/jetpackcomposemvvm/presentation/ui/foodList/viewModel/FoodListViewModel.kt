package com.example.jetpackcomposemvvm.presentation.ui.foodList.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposemvvm.Repository.FoodRepo
import com.example.jetpackcomposemvvm.domain.model.Food
import com.example.jetpackcomposemvvm.presentation.ui.foodList.ui.FoodCategory
import com.example.jetpackcomposemvvm.presentation.ui.foodList.ui.getFoodCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel
@Inject constructor(
    private val token: String,
    private val repo: FoodRepo
) : ViewModel() {
    val _food: MutableState<List<Food>> = mutableStateOf(ArrayList())
    val foodSearchInput: MutableState<String> = mutableStateOf("chicken")
    val categorySelected: MutableState<FoodCategory?> = mutableStateOf(null)
    var progressVisibility: MutableState<Boolean> = mutableStateOf(false)

    init {

        search()
    }

    fun search() {
        viewModelScope.launch {
            progressVisibility.value = true
            resetListOfFoods()
            delay(500)
            val result = repo.search(token, 1, foodSearchInput.value)
            _food.value = result
            progressVisibility.value = false
        }
    }

    fun onFoodSearchChange(newValue: String) {
        foodSearchInput.value = newValue
    }

    fun onCategorySelected(category: String) {
        val newCategory = getFoodCategory(category)
        categorySelected.value = newCategory
        onFoodSearchChange(category)
    }

    private fun resetListOfFoods() {
        _food.value = listOf()
        if (categorySelected?.value?.value != foodSearchInput.value) {
            clearCategorySelected()
        }
    }

    private fun clearCategorySelected() {
        categorySelected.value = null
    }


}