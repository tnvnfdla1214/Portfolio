package com.example.portfolio.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager

object Display {

    const val SIZE_NORMAL = Configuration.SCREENLAYOUT_SIZE_NORMAL
    const val SIZE_LARGE = Configuration.SCREENLAYOUT_SIZE_LARGE
    const val SIZE_XLARGE = Configuration.SCREENLAYOUT_SIZE_XLARGE

    fun isPhone(context: Context): Boolean {
        return getSize(context) == SIZE_NORMAL
    }

    fun isTablet(context: Context): Boolean {
        return !isPhone(context)
    }

    fun isNormal(context: Context): Boolean {
        return getSize(context) == SIZE_NORMAL
    }

    fun isLarge(context: Context): Boolean {
        return getSize(context) == SIZE_LARGE
    }

    fun isXLarge(context: Context): Boolean {
        return getSize(context) == SIZE_XLARGE
    }

    fun getSize(context: Context): Int {
        return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
    }

    fun getWidth(context: Context): Int {
        return getWidth(context as Activity)
    }

    fun getWidth(app: Activity): Int {
        return getWidth(app.window)
    }

    fun getWidth(window: Window): Int {
        val rect = Rect()

        window.decorView.getWindowVisibleDisplayFrame(rect)

        return rect.width()
    }

    fun getHeight(context: Context): Int {
        return getHeight(context as Activity)
    }

    fun getHeight(app: Activity): Int {
        return getHeight(app.window)
    }

    fun getHeight(window: Window): Int {
        val rect = Rect()

        window.decorView.getWindowVisibleDisplayFrame(rect)

        return rect.height()
    }

    fun getMeasuredWidth(context: Context, view: View): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay

        view.measure(display.width, display.height)

        return view.measuredWidth
    }

    fun getMeasuredHeight(context: Context, view: View): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay

        view.measure(display.width, display.height)

        return view.measuredHeight
    }

    fun getImmutableWidth(context: Context): Int {
        return getImmutableWidth(context as Activity)
    }

    fun getImmutableWidth(activity: Activity): Int {
        val metrics = DisplayMetrics()

        activity.windowManager.defaultDisplay.getMetrics(metrics)

        return metrics.widthPixels
    }

    fun getImmutableHeight(context: Context): Int {
        return getImmutableHeight(context as Activity)
    }

    fun getImmutableHeight(activity: Activity): Int {
        val metrics = DisplayMetrics()

        activity.windowManager.defaultDisplay.getMetrics(metrics)

        return metrics.heightPixels
    }

    fun getInches(): Float {
        val displayMetrics = Resources.getSystem().displayMetrics
        val screenWidth = displayMetrics.widthPixels / displayMetrics.xdpi
        val screenHeight = displayMetrics.heightPixels / displayMetrics.ydpi
        return Math.sqrt((screenWidth * screenWidth + screenHeight * screenHeight).toDouble()).toFloat()
    }

    @Deprecated("")
    fun getOrientation(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        var orientation = Configuration.ORIENTATION_PORTRAIT

        if (display.width > display.height) {
            orientation = Configuration.ORIENTATION_LANDSCAPE
        }
        return orientation
    }

    @SuppressLint("SourceLockedOrientationActivity")
    fun setOrientation(activity: Activity, orientation: Int) {
        when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE ->
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            Configuration.ORIENTATION_PORTRAIT ->
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    fun isPortrait(context: Context): Boolean {
        return getOrientation(context) == Configuration.ORIENTATION_PORTRAIT
    }

    fun isLandscape(context: Context): Boolean {
        return getOrientation(context) == Configuration.ORIENTATION_LANDSCAPE
    }
}