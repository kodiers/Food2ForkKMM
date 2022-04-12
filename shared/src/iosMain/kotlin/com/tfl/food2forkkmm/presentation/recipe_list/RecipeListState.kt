package com.tfl.food2forkkmm.presentation.recipe_list

import com.tfl.food2forkkmm.domain.model.GenericMessageInfo
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.util.Queue

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val bottomRecipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
) {
    constructor(): this (
        isLoading = false,
        page = 1,
        query = "",
        selectedCategory = null,
        recipes = listOf(),
        bottomRecipe = null,
        queue = Queue(mutableListOf())
    )

    companion object {
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}