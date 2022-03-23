package com.tfl.food2forkkmm.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tfl.food2forkkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.tfl.food2forkkmm.android.presentation.recipe_detail.RecipeDetailViewModel
import com.tfl.food2forkkmm.android.presentation.recipe_list.RecipeListScreen
import com.tfl.food2forkkmm.android.presentation.recipe_list.RecipeListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) {
            navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel(RecipeListViewModel::class.java, factory = factory)
            RecipeListScreen(onSelectedRecipe = {recipeId ->
                navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
            })
        }
        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })) { navBackStackEntry ->
                val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                val viewModel: RecipeDetailViewModel = viewModel(RecipeDetailViewModel::class.java, factory = factory)
                RecipeDetailScreen(recipe = viewModel.recipe.value)
            }
    }
}