package com.example.portfolio.coroutine

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.coroutine.adapter.CoroutineItemAdapter
import com.example.portfolio.databinding.FragmentCoroutineTestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class CoroutineTestFragment :
    BaseFragment<FragmentCoroutineTestBinding>(R.layout.fragment_coroutine_test) {
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
        viewModel.testItem.observe(viewLifecycleOwner) {
            Log.d("qweqwe","it : " + it)
            adapter.addTest(it)
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_coroutine_test)
}