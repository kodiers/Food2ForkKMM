package com.tfl.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfl.food2forkkmm.datasource.network.RecipeService
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.util.DateTimeUtil
import com.tfl.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.tfl.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents
import com.tfl.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(private val savedStateHandle: SavedStateHandle,
            private val getRecipe: GetRecipe): ViewModel() {
    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())
    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId = recipeId))
        }
    }

    fun onTriggerEvent(event: RecipeDetailEvents) {
        when(event) {
            is RecipeDetailEvents.GetRecipe -> {
                getRecipe(event.recipeId)
            }
            else -> {
                handleError("Invalid event")
            }
        }
    }

    private fun handleError(errorMessage: String) {
        TODO("Not yet implemented")
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            println("RecipeDetailViewModel: ${dataState.isLoading}")
            dataState.data.let { recipe ->
                println("RecipeDetailViewModel: $recipe")
                this.state.value = state.value.copy(recipe = recipe)
            }
            dataState.message.let { message ->
                if (message != null) {
                    handleError(message)
                }
            }
        }
            .launchIn(viewModelScope)
    }
}
