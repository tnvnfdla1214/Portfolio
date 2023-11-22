package com.example.portfolio.navigate

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentSearchBookBinding

class SearchBookFragment : BaseFragment<FragmentSearchBookBinding>(R.layout.fragment_search_book) {
    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_fragment_search_book)
}
