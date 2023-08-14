package com.example.portfolio.crash

import com.example.portfolio.BaseFragment
import com.example.portfolio.R
import com.example.portfolio.databinding.FragmentCrashBinding
import java.lang.RuntimeException

class CrashFragment : BaseFragment<FragmentCrashBinding>(R.layout.fragment_crash) {
    override fun initView() {
        binding.test.setOnClickListener {
            throw RuntimeException(getString(R.string.screen_fragment_crash))
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_crash)
}
