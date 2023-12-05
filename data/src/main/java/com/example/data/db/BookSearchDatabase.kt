package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
@TypeConverters(OrmConverter::class)
abstract class BookSearchDatabase : RoomDatabase() {

    abstract fun bookSearchDao(): BookSearchDao
}