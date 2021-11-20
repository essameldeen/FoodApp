package com.example.jetpackcomposemvvm.presentation.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jetpackcomposemvvm.presentation.ui.food.component.foodView
import com.example.jetpackcomposemvvm.presentation.ui.food.event.FoodEvent
import com.example.jetpackcomposemvvm.presentation.ui.food.viewModel.FoodViewModel
import com.example.jetpackcomposemvvm.presentation.ui.foodList.component.CircularProgressBarIndicator
import com.example.jetpackcomposemvvm.presentation.ui.theme.AppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodFragment : Fragment() {

    private var foodId: Int = -1
    private var mode: Boolean = false
    private val viewModel: FoodViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getBoolean("mode")?.let { mode = it }
        arguments?.getInt("foodId")?.let { valuePassed ->
            foodId = valuePassed
            viewModel.onTriggerEvent(FoodEvent.GetFoodEvent(foodId))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val loading = viewModel.loading.value

                val food = viewModel.foods.value

                val scaffoldState = rememberScaffoldState()

                AppTheme(
                    darkTheme =mode,
                ) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            if (loading && food == null) Text(text = "LOADING...")
                            else food?.let {
                                foodView(
                                    food = it,
                                )
                            }
                            CircularProgressBarIndicator(isVisible = loading)
                        }
                    }
                }
            }
        }
    }
}