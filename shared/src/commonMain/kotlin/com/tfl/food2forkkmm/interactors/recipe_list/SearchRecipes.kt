package com.tfl.food2forkkmm.interactors.recipe_list

import com.tfl.food2forkkmm.datasource.cache.RecipeCache
import com.tfl.food2forkkmm.datasource.network.RecipeService
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(private val recipeService: RecipeService, private val recipeCache: RecipeCache) {
    fun execute(page: Int, query: String): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())
        try {
            val recipes = recipeService.search(page, query)
            delay(500)
            recipeCache.insert(recipes)
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(query, page)
            }
            emit(DataState.data(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error(message = e.message ?: "Unknown error"))
        }
    }
}