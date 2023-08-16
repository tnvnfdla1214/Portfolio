package com.example.portfolio.compat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat

object ResourceCompat {

    @ColorInt
    @JvmStatic
    fun getColor(context: Context, @ColorRes colorResId: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getColor(colorResId)
        } else {
            context.resources.getColor(colorResId)
        }
    }

    @JvmStatic
    fun getDrawable(context: Context, @DrawableRes drawableResId: Int): Drawable? {
        return AppCompatResources.getDrawable(context, drawableResId)
    }

    @JvmStatic
    fun getBitmap(context: Context, @DrawableRes drawableResId: Int): Bitmap? {
        return context.getBitmap(drawableResId)
    }

    @JvmStatic
    fun getDimen(context: Context, @DimenRes dimenResId: Int): Float {
        return context.getDimen(dimenResId)
    }

    @JvmStatic
    fun getFont(context: Context, @FontRes fontResId: Int): Typeface? {
        return context.getFont(fontResId)
    }
}

fun Context.getBitmap(@DrawableRes drawableResId: Int): Bitmap? {
    val drawable = ResourceCompat.getDrawable(this, drawableResId)

    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }
    return null
}

fun Context.getDimen(@DimenRes dimenResId: Int): Float {
    return resources.getDimension(dimenResId)
}

fun Context.getFont(@FontRes fontResId: Int): Typeface? {
    return ResourcesCompat.getFont(this, fontResId)
}
