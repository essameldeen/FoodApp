package com.example.jetpackcomposemvvm.presentation.ui.foodList.viewModel

import androidx.lifecycle.ViewModel
import com.example.jetpackcomposemvvm.Repository.FoodRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel
@Inject constructor(
    private val token: String,
    private val repo: FoodRepo
) : ViewModel() {

    fun getAuthToken() = token
    fun  getRepo() = repo
}