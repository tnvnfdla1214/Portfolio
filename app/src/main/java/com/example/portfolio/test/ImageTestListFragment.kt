package com.example.portfolio.test

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentImageTestListBinding

class ImageTestListFragment :
    BaseFragment<FragmentImageTestListBinding>(R.layout.fragment_image_test_list) {

    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_fragment_test_image_list)
}
