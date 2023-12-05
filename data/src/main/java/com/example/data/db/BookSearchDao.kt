package com.example.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("SELECT * FROM books")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books")
    fun getFavoritePagingBooks(): PagingSource<Int, BookEntity>
}