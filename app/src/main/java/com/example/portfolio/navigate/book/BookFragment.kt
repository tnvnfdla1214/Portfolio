package com.example.portfolio.navigate.book

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentBookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : BaseFragment<FragmentBookBinding>(R.layout.fragment_book) {

    private val bookViewModel by viewModels<BookViewModel>()
    private val args by navArgs<BookFragmentArgs>()

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        val book = args.book
        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }

        binding.fabFavorite.setOnClickListener {
            bookViewModel.saveBook(book)
            Toast.makeText(requireContext(), "Book has saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        binding.webview.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.webview.onResume()
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_book)
}