package com.example.portfolio.coroutine

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentCoroutineBaseBinding
import com.example.portfolio.util.navigation.NavTarget

class CoroutineBaseFragment :
    BaseFragment<FragmentCoroutineBaseBinding>(R.layout.fragment_coroutine_base) {
    override fun initView() {
        binding.test1.setOnClickListener { NavTarget.toCoroutineTest1(navController) }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_coroutine_base)
}