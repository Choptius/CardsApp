package com.cardsapp.di

import android.content.Context
import androidx.room.Room
import com.cardsapp.data.AppDatabase
import com.cardsapp.data.CardsRepositoryImpl
import com.cardsapp.domain.CardsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindRepository(foldersRepositoryImpl: CardsRepositoryImpl): CardsRepository

    companion object {
        @Provides
        fun provideFolderDao(appDatabase: AppDatabase) = appDatabase.cardsDao()

        @Singleton
        @Provides
        fun provideAppDatabase(@ApplicationContext context: Context) =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

    }
}
