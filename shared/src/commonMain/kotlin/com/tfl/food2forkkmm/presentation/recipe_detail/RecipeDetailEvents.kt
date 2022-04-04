package com.tfl.food2forkkmm.presentation.recipe_detail

sealed class RecipeDetailEvents {
    data class GetRecipe(val recipeId: Int): RecipeDetailEvents()
}
