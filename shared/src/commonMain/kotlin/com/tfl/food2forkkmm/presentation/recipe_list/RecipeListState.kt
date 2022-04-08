package com.tfl.food2forkkmm.presentation.recipe_list

import com.tfl.food2forkkmm.domain.model.GenericMessageInfo
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.util.Queue

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
) {
    constructor(): this (
        isLoading = false,
        page = 1,
        query = "",
        selectedCategory = null,
        recipes = listOf(),
        queue = Queue(mutableListOf())
    )
}