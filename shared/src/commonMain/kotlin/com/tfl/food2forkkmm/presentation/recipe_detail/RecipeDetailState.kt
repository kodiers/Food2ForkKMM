package com.tfl.food2forkkmm.presentation.recipe_detail

import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<String> = Queue(mutableListOf()))
