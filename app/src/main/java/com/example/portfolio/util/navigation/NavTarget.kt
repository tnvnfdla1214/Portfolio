package com.example.portfolio.util.navigation

import androidx.navigation.NavController
import com.example.domain.model.Book
import com.example.portfolio.customView.CustomViewBaseFragmentDirections
import com.example.portfolio.main.MainFragmentDirections
import com.example.portfolio.navigate.searchBook.SearchBookFragmentDirections
import com.example.portfolio.test.TestBaseFragmentDirections

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

    fun toImageTest1(navController: NavController) {
        navController.navigate(TestBaseFragmentDirections.actionTestFragmentToImageTestFragment())
    }

    fun toImageTest2(navController: NavController) {
        navController.navigate(TestBaseFragmentDirections.actionTestFragmentToImageTestFragment())
    }

    fun toTest(navController: NavController) {
        navController.navigate(MainFragmentDirections.actionMainFragmentToTestFragment())
    }

    fun toBook(navController: NavController, bookJson: String) {
        navController.navigate(SearchBookFragmentDirections.actionFragmentSearchBookToFragmentBook(bookJson))
    }
}
