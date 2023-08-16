package com.example.portfolio.customView.view

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupWindow
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.ImageViewCompat
import com.example.portfolio.R
import com.example.portfolio.compat.ResourceCompat
import com.example.portfolio.databinding.TutorialTooltipLabelBinding
import com.example.portfolio.utils.dimen
import com.example.portfolio.utils.getViewPointOnScreen

class TutorialTooltipLabel(
    context: Context,
    private val builder: Builder,
) {
    private val binding: TutorialTooltipLabelBinding = TutorialTooltipLabelBinding.inflate(
        LayoutInflater.from(context),
        null,
        false,
    )

    private val bodyWindow: PopupWindow = PopupWindow(
        binding.root,
        FrameLayout.LayoutParams.WRAP_CONTENT,
        FrameLayout.LayoutParams.WRAP_CONTENT,
    )

    private var tooltipPosition = OverlayTutorialViewItemTooltipPosition.TOP
    private var arrowPosition = OverlayTutorialViewItemTooltipPosition.BOTTOM
    private lateinit var boundaryView: View

    init {
        tooltipPosition = builder.tooltipPosition
        arrowPosition = tooltipPosition.reverse()

        initializeMessage()
        initializeTooltipColor()
    }

    private fun initializeMessage() {
        binding.message.text = builder.message
    }

    private fun initializeTooltipColor() {
        with(binding.llTooltip) {
            val backgroundDrawable = ResourceCompat.getDrawable(
                context,
                R.color.theme_color_palette_gray_90,
            )

            val wrappedBackground = DrawableCompat.wrap(backgroundDrawable!!)
            DrawableCompat.setTint(wrappedBackground, builder.backgroundColor)
            background = wrappedBackground
        }
    }

    fun setPage(page: String) {
        binding.page.text = page
    }

    fun setBoundary(boundaryView: View) {
        this.boundaryView = boundaryView
    }

    private fun initializeTooltipWindow() {
        with(bodyWindow) {
            isOutsideTouchable = true
            animationStyle = builder.animation
            setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        }
        with(binding.wrapper) {
            val safePadding = context.dimen(R.dimen.tooltip_label_safe_padding).toInt()

            val padding = mutableListOf(safePadding, safePadding, safePadding, safePadding)

            padding[arrowPosition.ordinal] = 0

            setPadding(
                padding[TooltipPosition.LEFT.ordinal],
                padding[TooltipPosition.TOP.ordinal],
                padding[TooltipPosition.RIGHT.ordinal],
                padding[TooltipPosition.BOTTOM.ordinal],
            )
        }
//        binding.tooltipMessage.maxWidth = boundaryView.width
    }

    private fun initializeTooltipListeners() {
        binding.wrapper.setOnClickListener {
            bodyWindow.dismiss()
            builder.onClickListener?.onClick()
        }
    }

    fun toolTipDissmiss() {
        bodyWindow.dismiss()
    }

    private fun initializeArrow(anchor: View) {
        with(binding.tooltipArrow) {
            ImageViewCompat.setImageTintList(
                this,
                ColorStateList.valueOf(builder.backgroundColor),
            )

            binding.message.post {
                val arrowBoundary = context.dimen(R.dimen.tutorial_tooltip_arrow_boundary)
                val arrowSize = context.dimen(R.dimen.tooltip_arrow_size)
                val safePadding = context.dimen(R.dimen.tooltip_label_safe_padding).toInt()
                when (arrowPosition) {
                    OverlayTutorialViewItemTooltipPosition.TOP -> {
                        rotation = 0f
                        x = getArrowConstraintPositionX(anchor)
                        y = binding.message.y - arrowSize + arrowBoundary
                    }

                    OverlayTutorialViewItemTooltipPosition.BOTTOM -> {
                        rotation = 180f
                        x = getArrowConstraintPositionX(anchor)
                        y = safePadding + arrowSize + binding.message.height - arrowBoundary
                    }
                }
            }
        }
        binding.root.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    }

    private fun getArrowConstraintPositionX(anchor: View): Float {
        val arrowSize = anchor.context.dimen(R.dimen.tooltip_arrow_size)
        val anchorCenterX = anchor.getViewPointOnScreen().x + anchor.width / 2f
        val horizontalOffset = anchorCenterX - binding.wrapper.getViewPointOnScreen().x

        return horizontalOffset - arrowSize / 2f
    }

    fun show(
        anchor: View,
    ) {
        anchor.post {
            tooltipPosition = getOptimizedTooltipPosition(anchor)
            arrowPosition = tooltipPosition.reverse()

            initializeTooltipWindow()
            initializeArrow(anchor)
            initializeTooltipListeners()

            showAsDropDown(anchor)
        }
    }

    private fun showAsDropDown(anchor: View) {
        bodyWindow.showAsDropDown(
            anchor,
            getXOffset(anchor),
            getYOffset(anchor),
        )
    }

    private fun getXOffset(anchor: View): Int {
        val xOffset = (anchor.measuredWidth / 2) - (getMeasuredWidth() / 2)
        if (boundaryView == binding.root.rootView) {
            return xOffset
        }

        val bodyWindowX = anchor.getViewPointOnScreen().x + xOffset
        val leftBoundary = boundaryView.getViewPointOnScreen().x
        val rightBoundary = boundaryView.getViewPointOnScreen().x + boundaryView.width
        val adjustedXOffset = when {
            bodyWindowX < leftBoundary -> {
                xOffset + (leftBoundary - bodyWindowX)
            }

            bodyWindowX + getMeasuredWidth() > rightBoundary -> {
                val newXOffset = xOffset - (bodyWindowX + getMeasuredWidth() - rightBoundary)
                if (newXOffset + bodyWindowX < leftBoundary) {
                    xOffset + (leftBoundary - bodyWindowX)
                } else {
                    newXOffset
                }
            }

            else -> xOffset
        }
        return adjustedXOffset
    }

    private fun getYOffset(anchor: View) = when (tooltipPosition) {
        OverlayTutorialViewItemTooltipPosition.TOP -> -getMeasuredHeight() - anchor.measuredHeight
        OverlayTutorialViewItemTooltipPosition.BOTTOM -> 0
    }

    private fun getOptimizedTooltipPosition(anchor: View): OverlayTutorialViewItemTooltipPosition {
        binding.root.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)

        val availableSizeInPx = when (builder.tooltipPosition) {
            OverlayTutorialViewItemTooltipPosition.TOP -> anchor.getViewPointOnScreen().y
            OverlayTutorialViewItemTooltipPosition.BOTTOM -> Resources.getSystem().displayMetrics.heightPixels - anchor.getViewPointOnScreen().y
        }

        val requiredSizeInPx = getMeasuredHeight()

        return if (availableSizeInPx < requiredSizeInPx) builder.tooltipPosition.reverse() else builder.tooltipPosition
    }

    private fun getMeasuredWidth(): Int {
        return this.binding.root.measuredWidth
    }

    private fun getMeasuredHeight(): Int {
        return this.binding.root.measuredHeight
    }

    enum class Color {
        ACCENT, NEUTRAL
    }

    class Builder(private val context: Context) {
        @JvmField
        @set:JvmSynthetic
        var message: CharSequence = ""

        @JvmField
        @ColorInt
        @set:JvmSynthetic
        var backgroundColor = ContextCompat.getColor(context, R.color.theme_color_palette_gray_90)

        @JvmField
        @set:JvmSynthetic
        var tooltipPosition = OverlayTutorialViewItemTooltipPosition.TOP

        @JvmField
        @set:JvmSynthetic
        var animation = R.style.Spindle_Tooltip_Animation

        @set:JvmSynthetic
        var onClickListener: OnClickListener? = null

        fun setOnClickListener(onClickListener: OnClickListener): Builder = apply {
            this.onClickListener = onClickListener
        }

        interface OnClickListener {
            fun onClick()
        }

        fun setMessage(message: CharSequence): Builder = apply { this.message = message }

        fun setColor(color: Color = Color.ACCENT): Builder = apply {
            val resourceColor = R.color.theme_color_palette_gray_90

            this.backgroundColor = ContextCompat.getColor(context, resourceColor)
        }

        fun setTooltipPosition(tooltipPosition: OverlayTutorialViewItemTooltipPosition): Builder =
            apply {
                this.tooltipPosition = tooltipPosition
            }

        fun build(): TutorialTooltipLabel = TutorialTooltipLabel(context, this)
    }
}
