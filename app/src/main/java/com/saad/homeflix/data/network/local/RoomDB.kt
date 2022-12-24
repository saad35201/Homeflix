package com.saad.homeflix.data.network.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.utils.Constants.LOCAL_DATABASE_NAME

@Database(entities = [ResultsItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        @Volatile
        private var INSTANCE: LocalDB? = null

        fun getInstance(context: Context): LocalDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDB::class.java,
                    LOCAL_DATABASE_NAME,
                ).build()
            }
            return INSTANCE!!
        }
    }

}