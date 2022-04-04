package com.tfl.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tfl.food2forkkmm.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.tfl.food2forkkmm.android.presentation.components.RecipeImage
import com.tfl.food2forkkmm.android.presentation.recipe_detail.components.LoadingRecipeShimmer
import com.tfl.food2forkkmm.android.presentation.recipe_detail.components.RecipeView
import com.tfl.food2forkkmm.android.presentation.recipe_list.components.RecipeCard
import com.tfl.food2forkkmm.android.presentation.theme.AppTheme
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents
import com.tfl.food2forkkmm.presentation.recipe_detail.RecipeDetailState

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailScreen(state: RecipeDetailState,
                       onTriggerEvent: (RecipeDetailEvents) -> Unit)
{
    AppTheme(displayProgressBar = state.isLoading) {
        if (state.recipe == null && state.isLoading) {
            LoadingRecipeShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
        } else if (state.recipe == null) {
            Text(text = "We were unable to retrieve the details for this recipe.\nTry resetting app.",
            modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.body1)
        } else {
            RecipeView(recipe = state.recipe!!)
        }
    }

}