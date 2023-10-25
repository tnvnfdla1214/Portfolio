package com.example.portfolio.navigate

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentNavHomeBinding

class NavHomeFragment : BaseFragment<FragmentNavHomeBinding>(R.layout.fragment_nav_home) {
    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_fragment_nav_home)
}
