package com.tfl.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfl.food2forkkmm.datasource.network.RecipeService
import com.tfl.food2forkkmm.domain.model.GenericMessageInfo
import com.tfl.food2forkkmm.domain.model.Recipe
import com.tfl.food2forkkmm.domain.model.UIComponentType
import com.tfl.food2forkkmm.domain.util.DateTimeUtil
import com.tfl.food2forkkmm.domain.util.GenericMessageQueueUtil
import com.tfl.food2forkkmm.domain.util.Queue
import com.tfl.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.tfl.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents
import com.tfl.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*
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
            is RecipeDetailEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            else -> {
                appendToMessageQueue(GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("Invalid Event"))
            }
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf()))
            state.value = state.value.copy(queue = queue)
        } catch (e: Exception) {

        }
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!GenericMessageQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue,
                messageInfo = messageInfo.build()
            )) {
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).collectCommon(coroutineScope = viewModelScope)
        { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            println("RecipeDetailViewModel: ${dataState.isLoading}")
            dataState.data.let { recipe ->
                println("RecipeDetailViewModel: $recipe")
                this.state.value = state.value.copy(recipe = recipe)
            }
            dataState.message.let { message ->
                if (message != null) {
                    appendToMessageQueue(message)
                }
            }
        }
    }
}
