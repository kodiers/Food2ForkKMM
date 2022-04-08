package com.tfl.food2forkkmm.di

import com.tfl.food2forkkmm.datasource.network.KtorClientFactory
import com.tfl.food2forkkmm.datasource.network.RecipeService
import com.tfl.food2forkkmm.datasource.network.RecipeServiceImpl

class NetworkModule {
    val recipeService: RecipeService by lazy {
        RecipeServiceImpl(
            httpClient = KtorClientFactory().build(),
            baseUrl = RecipeServiceImpl.BASE_URL
        )
    }
}