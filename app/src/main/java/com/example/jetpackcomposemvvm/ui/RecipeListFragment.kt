package com.example.jetpackcomposemvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.contextaware.ContextAware
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codingwithmitch.foodrecipes.util.HorizontalDottedProgress
import com.example.jetpackcomposemvvm.R


class RecipeListFragment : Fragment() {
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