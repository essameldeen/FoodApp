package com.example.jetpackcomposemvvm.presentation.ui.foodList.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.jetpackcomposemvvm.R
import com.example.jetpackcomposemvvm.presentation.ui.foodList.component.foodCard
import com.example.jetpackcomposemvvm.presentation.ui.foodList.viewModel.FoodListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodListFragment : Fragment() {
    private val viewModel: FoodListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                val foods = viewModel._food.value
                LazyColumn {
                    itemsIndexed(items = foods) { index, item ->
                        foodCard(food = item, onClick = {})
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
