package com.tfl.food2forkkmm.android.di

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
    fun provideSearchRecipes(recipeService: RecipeService): SearchRecipes {
        return SearchRecipes(recipeService = recipeService)
    }

    @Provides
    @Singleton
    fun provideGetRecipe(recipeService: RecipeService): GetRecipe {
        return GetRecipe(recipeService = recipeService)
    }
}