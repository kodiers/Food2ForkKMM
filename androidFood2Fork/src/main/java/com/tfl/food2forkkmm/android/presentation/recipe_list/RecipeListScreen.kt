package com.tfl.food2forkkmm.android.presentation.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tfl.food2forkkmm.android.presentation.recipe_list.components.RecipeCard
import com.tfl.food2forkkmm.android.presentation.recipe_list.components.RecipeList
import com.tfl.food2forkkmm.android.presentation.theme.AppTheme
import com.tfl.food2forkkmm.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onClickRecipeListItem: (Int) -> Unit) {
    AppTheme(displayProgressBar = false) {
        RecipeList(
            loading = state.isLoading,
            recipes = state.recipes,
            onClickRecipeListItem = onClickRecipeListItem)
    }

}