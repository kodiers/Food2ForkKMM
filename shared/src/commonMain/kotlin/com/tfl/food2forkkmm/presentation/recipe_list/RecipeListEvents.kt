package com.tfl.food2forkkmm.presentation.recipe_list

sealed class RecipeListEvents {
    object LoadRecipes: RecipeListEvents()
    object NextPage: RecipeListEvents()
}
