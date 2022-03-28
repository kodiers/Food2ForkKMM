package com.tfl.food2forkkmm.android.di

import com.tfl.food2forkkmm.android.BaseApplication
import com.tfl.food2forkkmm.datasource.cache.*
import com.tfl.food2forkkmm.domain.util.DateTimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(recipeDatabase: RecipeDatabase): RecipeCache {
        return RecipeCacheImpl(recipeDatabase = recipeDatabase, dateTimeUtil = DateTimeUtil())
    }
}