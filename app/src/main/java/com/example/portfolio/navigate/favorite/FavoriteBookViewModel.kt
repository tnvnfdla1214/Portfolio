package com.example.portfolio.navigate.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Book
import com.example.domain.usecase.DeleteFavoriteBookUseCase
import com.example.domain.usecase.GetFavoriteBooksUseCase
import com.example.domain.usecase.SaveFavoriteBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteBookViewModel @Inject constructor(
    private val saveFavoriteBookUseCase: SaveFavoriteBookUseCase,
    private val deleteFavoriteBookUseCase: DeleteFavoriteBookUseCase,
    getFavoriteBooksUseCase: GetFavoriteBooksUseCase,
) : ViewModel() {

    // Paging
    val favoritePagingBooks: StateFlow<PagingData<Book>> =
        getFavoriteBooksUseCase.invoke()
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

    // Room
    fun saveBook(book: Book) = viewModelScope.launch {
        saveFavoriteBookUseCase.invoke(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        deleteFavoriteBookUseCase.invoke(book)
    }
}