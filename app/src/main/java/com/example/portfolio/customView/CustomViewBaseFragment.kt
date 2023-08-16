package com.example.portfolio.customView

import com.example.portfolio.BaseFragment
import com.example.portfolio.R
import com.example.portfolio.databinding.FragmentCustomViewBaseBinding
import com.example.portfolio.util.navigation.NavTarget

class CustomViewBaseFragment :
    BaseFragment<FragmentCustomViewBaseBinding>(R.layout.fragment_custom_view_base) {
    override fun initView() {
        binding.custom1.setOnClickListener {
            NavTarget.toCustom1(navController)
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_custom_base)
}
