package com.tfl.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.tfl.food2forkkmm.android.presentation.components.RecipeImage
import com.tfl.food2forkkmm.android.presentation.theme.AppTheme
import com.tfl.food2forkkmm.domain.model.Recipe

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailScreen(recipe: Recipe?) {
    AppTheme(displayProgressBar = false) {
        if (recipe == null) {
            Text(text = "Error")
        } else {
            RecipeImage(url = recipe.featuredImage, contentDescription = recipe.title)
        }
    }

}