package com.tfl.food2forkkmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.interactors.recipe_list.SearchRecipes
import com.tfl.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.tfl.food2forkkmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(private val savedStateHandle: SavedStateHandle,
            private val searchRecipes: SearchRecipes): ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvents(RecipeListEvents.LoadRecipes)
    }

    fun onTriggerEvents(event: RecipeListEvents) {
        when(event) {
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }

    private fun handleError(errorMessage: String) {
        println(errorMessage)
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(page = state.value.page, query = state.value.query)
            .onEach { dataState ->
                state.value = state.value.copy(isLoading = dataState.isLoading)
                dataState.data?.let { recipes ->
                    appendRecipes(recipes = recipes)
                }
                dataState.message.let { message ->
                    handleError("RecipeListVM: ${message}")
                }
            }
            .launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }
}
