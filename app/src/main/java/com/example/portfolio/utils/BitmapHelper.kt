package com.example.portfolio.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object BitmapHelper {
    private const val DEFAULT_MAX_IMAGE_SIZE = 2000

    fun getBitmapFromUrl(context: Context, imageUrl: String?, placeHolderId: Int): Bitmap? {
        var bitmap: Bitmap? = null
        if (!imageUrl.isNullOrEmpty()) {
            var conn: HttpURLConnection? = null
            try {
                val url = URL(imageUrl)
                conn = url.openConnection() as HttpURLConnection
                conn.instanceFollowRedirects = true
                conn.doInput = true
                conn.connect()
                bitmap = BitmapFactory.decodeStream(conn.inputStream)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                conn?.disconnect()
            }
        }
        return bitmap ?: ResourcesCompat.getDrawable(context.resources, placeHolderId, null)?.toBitmap()
    }

    fun getBitmap(path: String, autoRotate: Boolean): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        var bitmap: Bitmap?
        options.inSampleSize = calculateInSampleSize(options)
        options.inJustDecodeBounds = false
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        bitmap = BitmapFactory.decodeFile(path, options)
        if (autoRotate) {
            val degree = ImageUtils.getImageOrientation(path)
            bitmap = ImageUtils.rotatedImage(bitmap, degree)
        }
        return bitmap
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options): Int {
        var inSampleSize = 1
        val height = options.outHeight
        val width = options.outWidth
        while (height / inSampleSize > DEFAULT_MAX_IMAGE_SIZE || width / inSampleSize > DEFAULT_MAX_IMAGE_SIZE) {
            inSampleSize *= 2
        }
        return inSampleSize
    }
}
