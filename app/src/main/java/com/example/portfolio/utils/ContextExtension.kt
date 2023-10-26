package com.example.portfolio.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.annotation.DimenRes

fun Context.dimen(@DimenRes dimenRes: Int): Float {
    return resources.getDimension(dimenRes)
}

fun Context.dp(valueInDp: Int): Int = (valueInDp * resources.displayMetrics.density).toInt()

internal fun Context.activityContext(): Activity {
    if (this is Activity) return this

    var context = this

    while (context is ContextWrapper && context !is Activity) {
        context = context.baseContext
    }
    return context as Activity
}
