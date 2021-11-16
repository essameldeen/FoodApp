package com.example.jetpackcomposemvvm.presentation.ui.foodList.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackcomposemvvm.presentation.ui.foodList.ui.FoodCategory
import com.example.jetpackcomposemvvm.utlis.*


@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    searchValue: String,
    onFoodSearchChange: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categories: List<FoodCategory>,
    onCategorySelected: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?,
    selectedCategory: FoodCategory?,
    onToggleTheme: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface,
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
                ConstraintLayout(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    val menu = createRef()
                    IconButton(
                        modifier = Modifier.constrainAs(menu) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        },


                        onClick = onToggleTheme
                    ) {
                        val image = loadPicture(LIGHT).value
                        image?.let {
                            Icon(bitmap = it.asImageBitmap(), contentDescription = "")
                        }
                    }

                }
            }
            ScrollableTabRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp),
                selectedTabIndex = 0,
                backgroundColor = MaterialTheme.colors.surface,

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