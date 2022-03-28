package com.tfl.food2forkkmm.android.di

import com.tfl.food2forkkmm.datasource.cache.RecipeCache
import com.tfl.food2forkkmm.datasource.network.RecipeService
import com.tfl.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.tfl.food2forkkmm.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Provides
    @Singleton
    fun provideSearchRecipes(recipeService: RecipeService, recipeCache: RecipeCache): SearchRecipes {
        return SearchRecipes(recipeService = recipeService, recipeCache = recipeCache)
    }

    @Provides
    @Singleton
    fun provideGetRecipe(recipeCache: RecipeCache): GetRecipe {
        return GetRecipe(recipeCache = recipeCache)
    }
}