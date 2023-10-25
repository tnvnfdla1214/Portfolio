package com.example.portfolio.test

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentTestBaseBinding
import com.example.portfolio.util.navigation.NavTarget

class TestBaseFragment : BaseFragment<FragmentTestBaseBinding>(R.layout.fragment_test_base) {
    override fun initView() {
        binding.imageTest.setOnClickListener {
            NavTarget.toImageTest(navController)
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_test_base)
}
