package com.tfl.food2forkkmm.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

interface Dummy {
    fun description(): String
}

class DummyImpl(private val dummyCapacity: Int): Dummy {
    override fun description(): String {
        return "This object is for $dummyCapacity dummies"
    }

}

@Module
@InstallIn(SingletonComponent::class)
object DummyModule {
    @Provides
    @Singleton
    fun provideDummyCapacity(): Int {
        return 5
    }

    @Provides
    @Singleton
    fun provideDummyObject(dummyCapacity: Int): Dummy {
        return DummyImpl(dummyCapacity)
    }
}