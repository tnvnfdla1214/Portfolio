package com.example.portfolio.navigate

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentFavoriteBookBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class FavoriteBookFragment :
    BaseFragment<FragmentFavoriteBookBinding>(R.layout.fragment_favorite_book) {
    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_fragment_favorite_book)
}
