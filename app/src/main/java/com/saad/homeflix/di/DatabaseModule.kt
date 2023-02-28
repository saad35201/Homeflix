package com.saad.homeflix.di

import android.content.Context
import com.saad.homeflix.data.network.local.MovieDao
import com.saad.homeflix.data.network.local.RoomDB
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
    fun provideRoomDB(@ApplicationContext appContext: Context): RoomDB {
        return RoomDB.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun provideMovieDao(roomDB: RoomDB): MovieDao {
        return roomDB.movieDao()
    }

}