package com.example.portfolio.util.navigation

import androidx.navigation.NavController
import com.example.portfolio.MainFragmentDirections

object NavTarget {

    fun toNavigate(navController: NavController) {
        navController.navigate(MainFragmentDirections.actionMainFragmentToNavBaseFragment())
    }

    fun toCrash(navController: NavController) {
        navController.navigate(MainFragmentDirections.actionMainFragmentToCrashFragment())
    }
}
