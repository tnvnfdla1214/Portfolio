package com.example.portfolio.navigate.book

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.domain.model.Book.Companion.fromJson
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
        val book = fromJson(args.bookJson)
        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(book.url)
        }

//        binding.fabFavorite.setOnClickListener {
//            bookViewModel.saveBook(book)
//            Snackbar.make(view, "Book has saved", Snackbar.LENGTH_SHORT).show()
//        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_book)
}