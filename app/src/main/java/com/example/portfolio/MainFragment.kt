package com.example.portfolio

import com.example.portfolio.databinding.FragmentMainBinding
import com.example.portfolio.util.navigation.NavTarget

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun initView() {
        binding.nav.setOnClickListener {
            NavTarget.toNavigate(navController)
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_main)
}
