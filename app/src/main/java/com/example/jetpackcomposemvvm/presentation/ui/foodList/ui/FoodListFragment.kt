package com.example.jetpackcomposemvvm.presentation.ui.foodList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.jetpackcomposemvvm.R
import com.example.jetpackcomposemvvm.presentation.ui.foodList.component.*
import com.example.jetpackcomposemvvm.presentation.ui.foodList.viewModel.FoodListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodListFragment : Fragment() {
    private val viewModel: FoodListViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                val foods = viewModel._food.value
                val searchValue = viewModel.foodSearchInput.value
                val selectedCategory = viewModel.categorySelected.value
                val keyboardController = LocalSoftwareKeyboardController.current
                val progressVisibility = viewModel.progressVisibility.value
                Column(modifier = Modifier.background(color = Color.White)) {
                    SearchAppBar(
                        searchValue = searchValue,
                        onFoodSearchChange = viewModel::onFoodSearchChange,
                        onExecuteSearch = viewModel::search,
                        categories = getAllFoodCategories(),
                        selectedCategory = selectedCategory,
                        onCategorySelected = viewModel::onCategorySelected,
                        keyboardController = keyboardController,
                    )
                    val state = remember { mutableStateOf(HeartButtonState.IDLE) }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.Center
                    ){
                        HeartButton(
                            modifier = Modifier,
                            state = state ,
                            onToggle = {
                                state.value = if(state.value == HeartButtonState.IDLE) HeartButtonState.ACTIVE else HeartButtonState.IDLE
                            },
                        )
                    }


                    Box(modifier = Modifier.fillMaxWidth()) {
                        LazyColumn {
                            itemsIndexed(items = foods) { index, item ->
                                foodCard(food = item, onClick = {})
                            }
                        }
                        // in the end be in the top in box
                        CircularProgressBarIndicator(progressVisibility)
                    }


                }


            }
        }
    }

    private fun goToRecipeFragment() {
        findNavController().navigate(
            R.id.action_recipeListFragment_to_recipeFragment
        )
    }

}
