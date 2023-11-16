package com.example.portfolio.utils

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import java.io.IOException

object ImageUtils {

    fun getImageOrientation(path: String): Int {
        var degree = 0

        try {
            val exif = ExifInterface(path)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)

            if (orientation != -1) {
                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                    ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                    ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return degree
    }
    fun rotatedImage(bitmap: Bitmap, degrees: Int): Bitmap {
        var rotatedBitmap = bitmap

        if (degrees != 0) {
            val matrix = Matrix()
            val centerX = bitmap.width.toFloat() / 2
            val centerY = bitmap.height.toFloat() / 2

            matrix.setRotate(degrees.toFloat(), centerX, centerY)

            try {
                rotatedBitmap =
                    Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)

                bitmap.recycle()
            } catch (e: OutOfMemoryError) {
                e.printStackTrace()
            }
        }
        return rotatedBitmap
    }
}
