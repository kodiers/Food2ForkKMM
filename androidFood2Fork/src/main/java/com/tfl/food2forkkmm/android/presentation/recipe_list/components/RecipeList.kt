package com.tfl.food2forkkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.tfl.food2forkkmm.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.tfl.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SiZE
import com.tfl.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onClickRecipeListItem: (Int) -> Unit
) {
    if (loading && recipes.isEmpty()) {
        LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
    } else if (recipes.isEmpty()) {
        // no recipes
    } else {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) {index, recipe ->
                if (index + 1 >= (page * RECIPE_PAGINATION_PAGE_SiZE) && !loading) {
                    onTriggerNextPage()
                }
                RecipeCard(recipe = recipe) {
                    onClickRecipeListItem(recipe.id)
                }
            }
        }
    }
}