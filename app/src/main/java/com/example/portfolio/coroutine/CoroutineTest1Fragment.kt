package com.example.portfolio.coroutine

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.portfolio.R
import com.example.portfolio.android.collectWithLifecycle
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.coroutine.adapter.CoroutineItemAdapter
import com.example.portfolio.databinding.FragmentCoroutineTest1Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoroutineTest1Fragment :
    BaseFragment<FragmentCoroutineTest1Binding>(R.layout.fragment_coroutine_test1) {
    private val viewModel: CoroutineTestViewModel by viewModels()
    private val adapter = CoroutineItemAdapter()
    override fun initView() {
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.items.collectWithLifecycle(this) {
            Log.d("qweqwe", it.toString())
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_coroutine_test1)
}