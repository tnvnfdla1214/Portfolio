package com.example.portfolio.test

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentImageTestBinding

class ImageTestFragment : BaseFragment<FragmentImageTestBinding>(R.layout.fragment_image_test) {
    override fun initView() {

    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_crash)
}
