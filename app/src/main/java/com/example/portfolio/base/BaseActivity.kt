package com.example.portfolio.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int,
) : AppCompatActivity() {
    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    private lateinit var navController: NavController

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this

        initNavController(getNavHostFragmentId())
        initView()
    }

    private fun initNavController(navHostFragmentId: Int) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(navHostFragmentId) as NavHostFragment
        navController = navHostFragment.navController
    }

    protected abstract fun getScreenName(): String?
    protected abstract fun getNavHostFragmentId(): Int
}
