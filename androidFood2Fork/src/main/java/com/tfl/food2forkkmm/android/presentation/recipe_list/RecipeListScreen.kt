package com.tfl.food2forkkmm.android.presentation.recipe_list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import com.tfl.food2forkkmm.android.presentation.recipe_list.components.RecipeList
import com.tfl.food2forkkmm.android.presentation.recipe_list.components.SearchAppBar
import com.tfl.food2forkkmm.android.presentation.theme.AppTheme
import com.tfl.food2forkkmm.presentation.recipe_list.FoodCategoryUtil
import com.tfl.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.tfl.food2forkkmm.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit) {
    AppTheme(displayProgressBar = state.isLoading) {
        val foodCategories = remember {
            FoodCategoryUtil().getAllFoodCategories()
        }
        Scaffold(topBar = {
            SearchAppBar(
                query = state.query,
                categories = foodCategories,
                onSelectedCategoryChanged = {
                    onTriggerEvent(RecipeListEvents.OnSelectCategory(it))
                },
                selectedCategory = state.selectedCategory,
                onQueryChange = {
                    onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                },
                onExecuteSearch = {
                    onTriggerEvent(RecipeListEvents.NewSearch)
                }
            )
        }) {
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onClickRecipeListItem = onClickRecipeListItem,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                }
            )
        }
    }

}