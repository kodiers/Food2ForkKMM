package com.tfl.food2forkkmm.di

import com.tfl.food2forkkmm.datasource.cache.*
import com.tfl.food2forkkmm.domain.util.DateTimeUtil

class CacheModule {

    private val driverFactory: DriverFactory by lazy { DriverFactory() }

    val recipeDatabase: RecipeDatabase by lazy {
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(recipeDatabase = recipeDatabase, dateTimeUtil = DateTimeUtil())
    }
}