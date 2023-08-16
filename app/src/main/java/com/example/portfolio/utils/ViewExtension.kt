package com.example.portfolio.utils

import android.graphics.Point
import android.view.View

fun View.getViewPointOnScreen(): Point {
    val location: IntArray = intArrayOf(0, 0)
    getLocationOnScreen(location)
    return Point(location[0], location[1])
}

fun View.getMeasurement(): Pair<Int, Int> {
    measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)

    val width = measuredWidth
    val height = measuredHeight

    return width to height
}
