package com.example.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.db.BookSearchDatabase
import com.example.data.entity.BookMapper
import com.example.data.repository.BookSearchRepositoryImpl.PreferencesKeys.SORT_MODE
import com.example.data.service.BookSearchApi
import com.example.data.util.Constants.PAGING_SIZE
import com.example.data.util.Sort
import com.example.domain.model.Book
import com.example.domain.repository.BookSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookSearchRepositoryImpl @Inject constructor(
    private val db: BookSearchDatabase,
    private val dataStore: DataStore<Preferences>,
    private val api: BookSearchApi,
) : BookSearchRepository {
    override suspend fun insertBooks(book: Book) {
        db.bookSearchDao().insertBook(BookMapper.toBookEntity(book))
    }

    override suspend fun deleteBooks(book: Book) {
        db.bookSearchDao().deleteBook(BookMapper.toBookEntity(book))
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return db.bookSearchDao().getFavoriteBooks()
            .map { bookEntityList -> bookEntityList.map { it.toBook() } }
    }

    override suspend fun saveSortMode(mode: String) {
        dataStore.edit { prefs ->
            prefs[SORT_MODE] = mode
        }
    }

    override suspend fun getSortMode(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[SORT_MODE] ?: Sort.ACCURACY.value
            }
    }

    override suspend fun saveCacheDeleteMode(mode: Boolean) {
        dataStore.edit { prefs ->
            prefs[PreferencesKeys.CACHE_DELETE_MODE] = mode
        }
    }

    override suspend fun getCacheDeleteMode(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[PreferencesKeys.CACHE_DELETE_MODE] ?: false
            }
    }

    // Paging
    override fun getFavoritePagingBooks(): Flow<PagingData<Book>> {
        val pagingSourceFactory = { db.bookSearchDao().getFavoritePagingBooks() }

        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false,
                maxSize = PAGING_SIZE * 3,
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow.map { pagingData -> pagingData.map { it.toBook() } }
    }

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