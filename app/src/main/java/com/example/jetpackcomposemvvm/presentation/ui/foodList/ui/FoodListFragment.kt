package com.example.jetpackcomposemvvm.presentation.ui.foodList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import com.example.jetpackcomposemvvm.presentation.ui.foodList.viewModel.FoodListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodListFragment : Fragment() {
    private val viewModel: FoodListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("VIEWMODEL: ${viewModel}")
        println("VIEWMODEL: ${viewModel.getRepo()}")
        println("VIEWMODEL: token: ${viewModel.getAuthToken()}")

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Recipe List ",
                        fontSize = 21.sp
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(onClick = {
                        goToRecipeFragment()
                    }) {
                        Text("Go To Recipe Fragment")
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
