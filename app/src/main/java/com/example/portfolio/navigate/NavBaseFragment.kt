package com.example.portfolio.navigate

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentNavBaseBinding

class NavBaseFragment : BaseFragment<FragmentNavBaseBinding>(R.layout.fragment_nav_base) {
    override fun initView() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navBar.setupWithNavController(navController)
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_nav_base)
}
