package com.tfl.food2forkkmm.interactors.recipe_list

import com.tfl.food2forkkmm.datasource.cache.RecipeCache
import com.tfl.food2forkkmm.datasource.network.RecipeService
import com.tfl.food2forkkmm.domain.model.GenericMessageInfo
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.model.UIComponentType
import com.tfl.food2forkkmm.domain.util.CommonFlow
import com.tfl.food2forkkmm.domain.util.DataState
import com.tfl.food2forkkmm.domain.util.asCommonFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(private val recipeService: RecipeService, private val recipeCache: RecipeCache) {
    fun execute(page: Int, query: String): CommonFlow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())
        try {
            val recipes = recipeService.search(page, query)
            delay(500)
            if (query == "error") {
                throw Exception("Forcing an error... Search FAILED")
            }
            recipeCache.insert(recipes)
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(query, page)
            }
            emit(DataState.data(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipes.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message ?: "Unknown Error")
            ))
        }
    }.asCommonFlow()
}