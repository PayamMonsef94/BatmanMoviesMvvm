package com.example.batmanmoviesmvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.batmanmoviesmvvm.data.db.AppDao
import com.example.batmanmoviesmvvm.data.model.Detail
import com.example.batmanmoviesmvvm.data.model.Movie

@Database(
    entities = [Movie::class,Detail::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}
