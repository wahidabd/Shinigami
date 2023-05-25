package com.wahidabd.shinigami.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahidabd.shinigami.data.favorite.note.FavoriteDao
import com.wahidabd.shinigami.data.favorite.note.FavoriteEntity


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */


@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null){
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "favorite")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyDb(){
            INSTANCE = null
        }
    }
}