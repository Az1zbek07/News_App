package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.InformDao
import com.example.data.database.InformDatabase
import com.example.data.repository.LocalRepositoryImpl
import com.example.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
    @InstallIn(SingletonComponent::class)
object DatabaseModule{

    @[Provides Singleton]
    fun provideDatabase(
        @ApplicationContext context: Context
    ): InformDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            InformDatabase::class.java,
            "Weather.db"
        ).fallbackToDestructiveMigration().build()
    }

    @[Provides Singleton]
    fun provideInformDao(
        informDatabase: InformDatabase
    ): InformDao{
        return informDatabase.dao
    }

    @[Provides Singleton]
    fun provideLocalRepository(
        dao: InformDao
    ): LocalRepository {
        return LocalRepositoryImpl(dao)
    }

}
