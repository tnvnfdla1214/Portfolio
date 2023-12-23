package com.example.portfolio.util.navigation

import androidx.navigation.NavController
import com.example.domain.model.Book
import com.example.portfolio.audioMedia.AudioMediaBaseFragmentDirections
import com.example.portfolio.customView.CustomViewBaseFragmentDirections
import com.example.portfolio.main.MainFragmentDirections
import com.example.portfolio.navigate.favorite.FavoriteBookFragmentDirections
import com.example.portfolio.navigate.searchBook.SearchBookFragmentDirections

object NavTarget {

    fun toNavigate(navController: NavController) {
        navController.navigate(MainFragmentDirections.actionMainFragmentToNavBaseFragment())
    }

    fun toCrash(navController: NavController) {
        navController.navigate(MainFragmentDirections.actionMainFragmentToCrashFragment())
    }

    fun toCustom(navController: NavController) {
        navController.navigate(MainFragmentDirections.actionMainFragmentToCustomViewBaseFragment())
    }

    fun toCustom1(navController: NavController) {
        navController.navigate(CustomViewBaseFragmentDirections.actionCustomViewBaseFragmentToCustom1Frgment())
    }

    fun toAudio(navController: NavController) {
        navController.navigate(AudioMediaBaseFragmentDirections.actionAudioMediaFragmentToAudioFragment())
    }

    fun toMedia(navController: NavController) {
        navController.navigate(AudioMediaBaseFragmentDirections.actionAudioMediaFragmentToMediaFragment())
    }

    fun toAudioMedia(navController: NavController) {
        navController.navigate(MainFragmentDirections.actionMainFragmentToAudioMediaFragment())
    }

    fun toSearchedBook(navController: NavController, book: Book) {
        navController.navigate(SearchBookFragmentDirections.actionFragmentSearchBookToFragmentBook(book))
    }

    fun toFavoriteBook(navController: NavController, book: Book) {
        navController.navigate(FavoriteBookFragmentDirections.actionFavoriteBookFragmentToFragmentBook(book))
    }
}
