package com.example.portfolio.navigate.favorite

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentFavoriteBookBinding
import com.example.portfolio.navigate.searchBook.adapter.BookSearchPagingAdapter
import com.example.portfolio.util.navigation.NavTarget.toFavoriteBook
import com.example.portfolio.utils.collectLatestStateFlow
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteBookFragment :
    BaseFragment<FragmentFavoriteBookBinding>(R.layout.fragment_favorite_book) {

    private val viewModel by viewModels<FavoriteBookViewModel>()
    private lateinit var bookSearchAdapter: BookSearchPagingAdapter

    override fun initView() {
        setupRecyclerView()
        setupTouchHelper()
        collectLatestStateFlow(viewModel.favoritePagingBooks) {
            bookSearchAdapter.submitData(it)
        }
    }

    private fun setupRecyclerView() {
        bookSearchAdapter = BookSearchPagingAdapter()
        binding.rvFavoriteBooks.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL,
                ),
            )
            adapter = bookSearchAdapter
        }
        bookSearchAdapter.setOnItemClickListener {
            toFavoriteBook(navController, it)
        }
    }

    private fun setupTouchHelper() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT,
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val pagedBook = bookSearchAdapter.peek(position)
                pagedBook?.let { book ->
                    viewModel.deleteBook(book)
                    Snackbar.make(binding.root, "책이 삭제 되었습니다.", Snackbar.LENGTH_SHORT).apply {
                        setAction("취소") {
                            viewModel.saveBook(book)
                        }
                    }.show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvFavoriteBooks)
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_favorite_book)
}
