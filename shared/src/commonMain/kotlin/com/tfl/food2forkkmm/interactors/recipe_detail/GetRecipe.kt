package com.tfl.food2forkkmm.interactors.recipe_detail

import com.tfl.food2forkkmm.datasource.cache.RecipeCache
import com.tfl.food2forkkmm.domain.model.GenericMessageInfo
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.model.UIComponentType
import com.tfl.food2forkkmm.domain.util.CommonFlow
import com.tfl.food2forkkmm.domain.util.DataState
import com.tfl.food2forkkmm.domain.util.asCommonFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache
) {
    fun execute(recipeId: Int): CommonFlow<DataState<Recipe>> = flow {
        emit(DataState.loading())
        try {
            val recipe = recipeCache.get(recipeId = recipeId)
            emit(DataState.data(message = null, data = recipe))
        } catch (e: Exception) {
            emit(DataState.error(message = GenericMessageInfo.Builder()
                .id("GetRecipe.Error")
                .title("Error")
                .uiComponentType(UIComponentType.Dialog)
                .description(e.message ?: "Unknown Error")
            ))
        }
    }.asCommonFlow()
}