package com.music.lib_api.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.music.lib_api.model.song.AudioBean

@Database(entities = [AudioBean::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val latestSongDao: LatestDataDao?

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder<AppDatabase>(
                    context,
                    AppDatabase::class.java,
                    "cloud_music_data"
                ) //允许在主线程访问数据库
                    //.allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}