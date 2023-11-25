package com.example.portfolio.navigate.searchBook

import android.text.Editable
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentSearchBookBinding
import com.example.portfolio.navigate.searchBook.adapter.BookSearchLoadStateAdapter
import com.example.portfolio.navigate.searchBook.adapter.BookSearchPagingAdapter
import com.example.portfolio.network.Constants.SEARCH_BOOKS_TIME_DELAY
import com.example.portfolio.utils.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class SearchBookFragment : BaseFragment<FragmentSearchBookBinding>(R.layout.fragment_search_book) {
    private val searchViewModel by viewModels<SearchBookViewModel>()
    private lateinit var bookSearchAdapter: BookSearchPagingAdapter
    override fun initView() {
        setupRecyclerView()
        searchBooks()
        setupLoadState()

        collectLatestStateFlow(searchViewModel.searchPagingResult) {
            bookSearchAdapter.submitData(it)
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_search_book)

    private fun setupRecyclerView() {
        bookSearchAdapter = BookSearchPagingAdapter()
        binding.rvSearchResult.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL,
                ),
            )
            adapter = bookSearchAdapter.withLoadStateFooter(
                footer = BookSearchLoadStateAdapter(bookSearchAdapter::retry),
            )
        }
//        bookSearchAdapter.setOnItemClickListener {
//            val action = SearchFragmentDirections.actionFragmentSearchToFragmentBook(it)
//            findNavController().navigate(action)
//        }
    }

    private fun searchBooks() {
        var startTime = System.currentTimeMillis()
        var endTime: Long

        binding.etSearch.text =
            Editable.Factory.getInstance().newEditable(searchViewModel.query)

        binding.etSearch.addTextChangedListener { text: Editable? ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= SEARCH_BOOKS_TIME_DELAY) {
                text?.let {
                    val query = it.toString().trim()
                    if (query.isNotEmpty()) {
                        searchViewModel.searchBooksPaging(query)
                        searchViewModel.query = query
                    }
                }
            }
            startTime = endTime
        }
    }

    private fun setupLoadState() {
        bookSearchAdapter.addLoadStateListener { combinedLoadStates ->
            val loadState = combinedLoadStates.source
            val isListEmpty = bookSearchAdapter.itemCount < 1 &&
                loadState.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached

            binding.tvEmptylist.isVisible = isListEmpty
            binding.rvSearchResult.isVisible = !isListEmpty

            binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
        }
    }
}
