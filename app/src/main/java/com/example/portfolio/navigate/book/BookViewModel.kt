package com.example.portfolio.navigate.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Book
import com.example.domain.usecase.SaveFavoriteBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val saveFavoriteBookUseCase: SaveFavoriteBookUseCase,
) : ViewModel() {
    fun saveBook(book: Book) = viewModelScope.launch {
        saveFavoriteBookUseCase.invoke(book)
    }
}