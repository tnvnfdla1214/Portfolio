package com.example.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.service.BookSearchApi
import com.example.data.util.Constants.PAGING_SIZE
import com.example.domain.model.Book
import com.example.domain.repository.BookSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookSearchRepositoryImpl @Inject constructor(
//    private val db: BookSearchDatabase,
    private val dataStore: DataStore<Preferences>,
    private val api: BookSearchApi,
) : BookSearchRepository {
//    override suspend fun searchBooks(
//        query: String,
//        sort: String,
//        page: Int,
//        size: Int
//    ): Response<Search> {
//        return api.searchBooks(query, sort, page, size).map { it.toSearch() }
//    }
//
//    override suspend fun insertBooks(book: Book) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteBooks(book: Book) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getFavoriteBooks(): Flow<List<Book>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun saveSortMode(mode: String) {
//        dataStore.edit { prefs ->
//            prefs[SORT_MODE] = mode
//        }
//    }
//
//    override suspend fun getSortMode(): Flow<String> {
//        return dataStore.data
//            .catch { exception ->
//                if (exception is IOException) {
//                    exception.printStackTrace()
//                    emit(emptyPreferences())
//                } else {
//                    throw exception
//                }
//            }
//            .map { prefs ->
//                prefs[SORT_MODE] ?: Sort.ACCURACY.value
//            }
//    }
//
//    override suspend fun saveCacheDeleteMode(mode: Boolean) {
//        dataStore.edit { prefs ->
//            prefs[PreferencesKeys.CACHE_DELETE_MODE] = mode
//        }
//    }
//
//    override suspend fun getCacheDeleteMode(): Flow<Boolean> {
//        return dataStore.data
//            .catch { exception ->
//                if (exception is IOException) {
//                    exception.printStackTrace()
//                    emit(emptyPreferences())
//                } else {
//                    throw exception
//                }
//            }
//            .map { prefs ->
//                prefs[PreferencesKeys.CACHE_DELETE_MODE] ?: false
//            }
//    }
//
//    override fun getFavoritePagingBooks(): Flow<PagingData<Book>> {
//        TODO("Not yet implemented")
//    }
//
//     //Paging
//    override fun getFavoritePagingBooks(): Flow<PagingData<Book>> {
//        val pagingSourceFactory = { db.bookSearchDao().getFavoritePagingBooks() }
//
//        return Pager(
//            config = PagingConfig(
//                pageSize = PAGING_SIZE,
//                enablePlaceholders = false,
//                maxSize = PAGING_SIZE * 3
//            ),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//    }

    override fun searchBooksPaging(query: String, sort: String): Flow<PagingData<Book>> {
        val pagingSourceFactory = { BookSearchPagingSource(api, query, sort) }

        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = PAGING_SIZE * 3,
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow.map { pagingData -> pagingData.map { it.toBook() } }
    }

    // DataStore
    private object PreferencesKeys {
        val SORT_MODE = stringPreferencesKey("sort_mode")
        val CACHE_DELETE_MODE = booleanPreferencesKey("cache_delete_mode")
    }
}