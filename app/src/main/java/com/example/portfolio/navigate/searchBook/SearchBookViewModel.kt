package com.example.portfolio.navigate.searchBook

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.util.Sort
import com.example.domain.model.Book
import com.example.domain.usecase.FetchBookSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModel @Inject constructor(
    private val fetchBookSearchUseCase: FetchBookSearchUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Paging
    private val _searchPagingResult = MutableStateFlow<PagingData<Book>>(PagingData.empty())
    val searchPagingResult: StateFlow<PagingData<Book>> = _searchPagingResult.asStateFlow()
    fun searchBooksPaging(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                fetchBookSearchUseCase.invoke(query, getSortMode())
                    .cachedIn(viewModelScope)
                    .collect {
                        _searchPagingResult.value = it
                    }
            }
        }
    }

    // SavedState
    var query = String()
        set(value) {
            field = value
            savedStateHandle[SAVE_STATE_KEY] = value
        }

    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    companion object {
        private const val SAVE_STATE_KEY = "query"
    }

    // DataStore
    private suspend fun getSortMode() = withContext(Dispatchers.IO) {
        // bookSearchRepository.getSortMode().first()
        Sort.ACCURACY.value
    }
}