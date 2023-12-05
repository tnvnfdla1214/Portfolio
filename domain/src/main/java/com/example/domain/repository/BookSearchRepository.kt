package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookSearchRepository {

//    suspend fun searchBooks(
//        query: String,
//        sort: String,
//        page: Int,
//        size: Int,
//    ): Response<Search>

    // Room
    suspend fun insertBooks(book: Book)

    fun getFavoriteBooks(): Flow<List<Book>>

//    suspend fun deleteBooks(book: Book)
//    // DataStore
//    suspend fun saveSortMode(mode: String)

//    suspend fun getSortMode(): Flow<String> //
//
//    suspend fun saveCacheDeleteMode(mode: Boolean)
//
//    suspend fun getCacheDeleteMode(): Flow<Boolean>
//
//    // Paging
//    fun getFavoritePagingBooks(): Flow<PagingData<Book>>

    fun searchBooksPaging(query: String, sort: String): Flow<PagingData<Book>>
}
