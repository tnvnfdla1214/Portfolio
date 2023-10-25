package com.example.portfolio.main

import com.example.portfolio.R
import com.example.portfolio.base.BaseActivity
import com.example.portfolio.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_activity_main)
    override fun getNavHostFragmentId(): Int = binding.navHostFragment.id
}
