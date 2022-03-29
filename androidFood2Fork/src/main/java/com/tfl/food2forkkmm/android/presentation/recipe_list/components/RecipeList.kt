package com.tfl.food2forkkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.tfl.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeList(loading: Boolean, recipes: List<Recipe>, onClickRecipeListItem: (Int) -> Unit) {
    if (loading && recipes.isEmpty()) {

    } else if (recipes.isEmpty()) {
        // no recipes
    } else {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) {index, recipe ->
                RecipeCard(recipe = recipe) {
                    onClickRecipeListItem(recipe.id)
                }
            }
        }
    }
}