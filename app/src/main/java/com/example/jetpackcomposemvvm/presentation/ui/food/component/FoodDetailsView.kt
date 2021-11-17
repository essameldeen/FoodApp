package com.example.jetpackcomposemvvm.presentation.ui.food.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposemvvm.domain.model.Food
import com.example.jetpackcomposemvvm.utlis.DEFAULT_IMAGE
import com.example.jetpackcomposemvvm.utlis.loadImage

@Composable
fun foodView(
    food: Food
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        food.featuredImage?.let { url ->
            val image = loadImage(url = url, defaultImage = DEFAULT_IMAGE).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                food.title?.let { title ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h3
                        )
                        val rank = food.rating.toString()
                        Text(
                            text = rank,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.h5
                        )
                    }
                }
                food.publisher?.let { publisher ->
                    val updated = food.dateUpdated
                    Text(
                        text = if (updated != null) {
                            "Updated ${updated} by ${publisher}"
                        } else {
                            "By ${publisher}"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        style = MaterialTheme.typography.caption
                    )
                }
                food.description?.let { description ->
                    if (description != "N/A") {
                        Text(
                            text = description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
                for (ingredient in food.ingredients) {
                    Text(
                        text = ingredient,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
                food.cookingInstructions?.let { instructions ->
                    Text(
                        text = instructions,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}