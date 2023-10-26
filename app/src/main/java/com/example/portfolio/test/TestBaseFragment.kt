package com.example.portfolio.test

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentTestBaseBinding
import com.example.portfolio.util.navigation.NavTarget

class TestBaseFragment : BaseFragment<FragmentTestBaseBinding>(R.layout.fragment_test_base) {
    override fun initView() {
        binding.imageTest1.setOnClickListener {
            NavTarget.toImageTest1(navController)
        }
        binding.imageTest2.setOnClickListener {
            NavTarget.toImageTest2(navController)
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_test_base)
}
