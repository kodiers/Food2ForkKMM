package com.tfl.food2forkkmm.presentation.recipe_detail

import com.tfl.food2forkkmm.domain.model.GenericMessageInfo
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
) {
    constructor(): this (
        isLoading = false,
        recipe = null,
        queue = Queue(mutableListOf())
    )
}
