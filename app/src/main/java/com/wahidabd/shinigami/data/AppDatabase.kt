package com.wahidabd.shinigami.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahidabd.shinigami.data.favorite.dao.FavoriteDao
import com.wahidabd.shinigami.data.favorite.dao.FavoriteEntity
import com.wahidabd.shinigami.data.history.dao.HistoryDao
import com.wahidabd.shinigami.data.history.dao.HistoryEntity


/**
 * Created by Wahid on 5/25/2023.
 * Github github.com/wahidabd.
 */


@Database(entities = [FavoriteEntity::class, HistoryEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
    abstract fun historyDao(): HistoryDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null){
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "shinigami.db")
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