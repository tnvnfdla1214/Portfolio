package com.example.portfolio.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.portfolio.R
import com.example.portfolio.databinding.MenuItemViewBinding

class MenuItemView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private val binding: MenuItemViewBinding =
        MenuItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        bindAttributes(context, attrs)
    }

    private fun bindAttributes(context: Context, attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.menuItemView).apply {
            binding.text.text = getString(R.styleable.menuItemView_text) ?: ""
        }.recycle()
    }
}
