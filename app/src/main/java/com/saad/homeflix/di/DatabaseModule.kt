package com.saad.homeflix.di

import android.content.Context
import com.saad.homeflix.data.network.local.LocalDB
import com.saad.homeflix.data.network.local.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext appContext: Context): LocalDB {
        return LocalDB.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun provideMovieDao(roomDB: LocalDB) : MovieDao {
        return roomDB.movieDao()
    }

}