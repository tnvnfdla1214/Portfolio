package com.example.portfolio.navigate

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class SettingsFragment :
    BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {
    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_fragment_favorite_book)
}
