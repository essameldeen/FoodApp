package com.example.jetpackcomposemvvm.presentation.ui.foodList.ui

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.jetpackcomposemvvm.R
import com.example.jetpackcomposemvvm.presentation.ui.App
import com.example.jetpackcomposemvvm.presentation.ui.foodList.component.*
import com.example.jetpackcomposemvvm.presentation.ui.foodList.viewModel.FoodListViewModel
import com.example.jetpackcomposemvvm.presentation.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


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
                val dark = remember { mutableStateOf(false) }
                AppTheme(darkTheme = dark.value) {
                    val foods = viewModel._food.value
                    val searchValue = viewModel.foodSearchInput.value
                    val selectedCategory = viewModel.categorySelected.value
                    val keyboardController = LocalSoftwareKeyboardController.current
                    val progressVisibility = viewModel.progressVisibility.value

                    val scaffoldState = rememberScaffoldState()
                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                searchValue = searchValue,
                                onFoodSearchChange = viewModel::onFoodSearchChange,
                                onExecuteSearch = viewModel::search,
                                categories = getAllFoodCategories(),
                                selectedCategory = selectedCategory,
                                onCategorySelected = viewModel::onCategorySelected,
                                keyboardController = keyboardController,
                                onToggleTheme = {
                                    dark.value = !dark.value
                                }
                            )
                        },
                        bottomBar = {
                            MyBottomBar()
                        },
                        drawerContent = {
                            MyDrawer()
                        },
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }


                    ) {
                        foodList(
                            foods = foods,
                            progressVisibility = progressVisibility,
                            mode =dark.value,
                            findNavController()
                        )
                    }
                }
            }
        }
    }


    @Composable
    fun MyBottomBar() {
        BottomNavigation(
            elevation = 12.dp
        ) {
            BottomNavigationItem(
                icon = { Icon(Icons.Default.BrokenImage, contentDescription = "") },
                selected = false,
                onClick = {}
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Search, contentDescription = "") },
                selected = true,
                onClick = {}
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Default.AccountBalanceWallet,
                        contentDescription = ""
                    )

                },
                selected = false,
                onClick = {}
            )
        }
    }

    @Composable
    fun MyDrawer() {
        Column() {
            Text(text = "Item1")
            Text(text = "Item2")
            Text(text = "Item3")
            Text(text = "Item4")
            Text(text = "Item5")
        }
    }


}
