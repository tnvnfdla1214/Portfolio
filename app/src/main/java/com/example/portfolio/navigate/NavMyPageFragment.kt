package com.example.portfolio.navigate

import com.example.portfolio.BaseFragment
import com.example.portfolio.R
import com.example.portfolio.databinding.FragmentNavMyPageBinding

class NavMyPageFragment : BaseFragment<FragmentNavMyPageBinding>(R.layout.fragment_nav_my_page) {
    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_fragment_nav_my_page)
}
