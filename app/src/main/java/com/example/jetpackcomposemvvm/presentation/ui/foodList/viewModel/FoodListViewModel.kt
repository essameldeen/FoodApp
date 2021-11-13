package com.example.jetpackcomposemvvm.presentation.ui.foodList.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposemvvm.Repository.FoodRepo
import com.example.jetpackcomposemvvm.domain.model.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel
@Inject constructor(
    private val token: String,
    private val repo: FoodRepo
) : ViewModel() {
     val _food: MutableState<List<Food>> = mutableStateOf(ArrayList())

    init {
      search()
    }
    fun  search(){
        viewModelScope.launch {
            val reuslt = repo.search(token, 1, "chicken")
            _food.value = reuslt
        }
    }
}