package com.example.portfolio.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil

fun ViewGroup.inflate(
    @LayoutRes layoutRes: Int,
    attachToRoot: Boolean = false,
): View = context.inflate(layoutRes, this, attachToRoot)

fun Context.inflate(
    @LayoutRes layoutRes: Int,
): View = layoutInflater().inflate(layoutRes, null)

fun Context.inflate(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup,
    attachToRoot: Boolean = false,
): View = layoutInflater().inflate(layoutRes, parent, attachToRoot)

fun <T> ViewGroup.inflate(
    @LayoutRes layoutRes: Int,
    attachToRoot: Boolean = false,
): T {
    return DataBindingUtil.inflate(context.layoutInflater(), layoutRes, this, attachToRoot)
}

private fun Context.layoutInflater(): LayoutInflater = LayoutInflater.from(this)