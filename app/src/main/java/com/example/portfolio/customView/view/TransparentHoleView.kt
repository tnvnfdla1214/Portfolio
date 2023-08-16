package com.example.portfolio.customView.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.portfolio.R
import com.example.portfolio.utils.dimen
import com.example.portfolio.utils.dp

class TransparentHoleView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var holeBounds: RectF? = null

    init {
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

        backgroundPaint.color = Color.BLACK
        backgroundPaint.alpha = TRANSPARENCY
    }

    fun setHoleBounds(bounds: RectF) {
        holeBounds = bounds
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)
        holeBounds?.let {
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

            val cornerRadius = context.dp(
                context.dimen(R.dimen.tutorial_highlight_radius)
                    .toInt(),
            ) * resources.displayMetrics.density
            canvas?.drawRoundRect(it, cornerRadius, cornerRadius, paint)
            paint.xfermode = null
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    companion object {
        const val TRANSPARENCY = 89
    }
}
