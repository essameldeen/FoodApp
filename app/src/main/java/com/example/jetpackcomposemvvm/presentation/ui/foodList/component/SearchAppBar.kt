package com.example.jetpackcomposemvvm.presentation.ui.foodList.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposemvvm.presentation.ui.foodList.ui.FoodCategory

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    searchValue: String,
    onFoodSearchChange: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categories: List<FoodCategory>,
    onCategorySelected: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?,
    selectedCategory: FoodCategory?
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.background,
        elevation = 8.dp,
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    value = searchValue,
                    onValueChange = { newValue ->
                        onFoodSearchChange(newValue)
                    },
                    label = {
                        Text("Search")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search

                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = ""
                        )
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            // do something here
                            onExecuteSearch()
                        }
                    ),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onSurface
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface,

                        ),

                    )
            }
            ScrollableTabRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                selectedTabIndex = 0,
                backgroundColor = Color.White,

                ) {
                for (category in categories) {
                    FoodCategoryChip(
                        category = category.value,
                        onExecuteSearch = {
                            onExecuteSearch()
                        },
                        onExecuteCategoryChange = {
                            onCategorySelected(it)
                        },
                        isSelected = category == selectedCategory
                    )
                }
            }
        }

    }
}