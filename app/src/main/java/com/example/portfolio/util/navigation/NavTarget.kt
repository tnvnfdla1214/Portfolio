package com.example.portfolio.util.navigation

import androidx.navigation.NavController
import com.example.portfolio.MainFragmentDirections
import com.example.portfolio.customView.CustomViewBaseFragmentDirections

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
}
