package com.example.portfolio

import com.example.portfolio.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_activity_main)
    override fun getNavHostFragmentId(): Int = binding.navHostFragment.id
}
