package com.tfl.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfl.food2forkkmm.datasource.network.RecipeService
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.util.DateTimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(private val savedStateHandle: SavedStateHandle,
            private val recipeService: RecipeService): ViewModel() {
    val recipe: MutableState<Recipe?> = mutableStateOf(null)
    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            viewModelScope.launch {
                recipe.value = recipeService.get(recipeId)
                println("KtorTest: ${recipe.value!!.title}")
                println("KtorTest: ${recipe.value!!.ingredients}")
                println("KtorTest: ${DateTimeUtil().humanizeDatetime(recipe.value!!.dateUpdated)}")
            }
        }
    }
}
