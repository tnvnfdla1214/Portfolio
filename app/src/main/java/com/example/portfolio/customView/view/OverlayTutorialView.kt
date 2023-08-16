package com.example.portfolio.customView.view

import android.content.Context
import android.graphics.RectF
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.portfolio.R
import com.example.portfolio.databinding.OverlayTutorialViewBinding
import com.example.portfolio.utils.dimen
import com.example.portfolio.utils.dp
import com.example.portfolio.widget.inflate

class OverlayTutorialView(context: Context) : ConstraintLayout(context) {
    private lateinit var binding: OverlayTutorialViewBinding

    private var currentStep = 0
    private lateinit var tutorialItems: List<OverlayTutorialViewItem>
    private val transparentHoleView = TransparentHoleView(context)
    private lateinit var spindleTutorialTooltipLabel: TutorialTooltipLabel
    private var boundaryView: View? = null

    var listener: OverlayTutorialListener? = null

    init {
        addView(transparentHoleView)
    }

    private fun initListener() {
        binding.close.setOnClickListener {
            onDismiss()
        }
        setOnClickListener {
            showNextStep()
            spindleTutorialTooltipLabel.toolTipDissmiss()
            nextStepTvTab()
        }
        listener?.onOverlayTutorialVisibilityChanged(true)
    }

    private fun nextStepTvTab() {
        val step = currentStep - 1
        if (step < tutorialItems.size) {
            binding.text.text = tutorialItems[step].overlayMessage
        }
    }

    fun boundaryMessage(view: View) {
        boundaryView = view
    }

    fun setTutorialItems(items: List<OverlayTutorialViewItem>) {
        tutorialItems = items
    }

    fun show() {
        showNextStep()
        binding = inflate<OverlayTutorialViewBinding>(R.layout.overlay_tutorial_view, true)
        initListener()
        nextStepTvTab()
    }

    private fun showNextStep() {
        if (currentStep < tutorialItems.size) {
            val currentItem = tutorialItems[currentStep]
            val page = "${currentStep + 1}/${tutorialItems.size}"
            highlightView(currentItem.view)
            if (::binding.isInitialized) {
                showTooltipLabel(
                    currentItem.labelPosition,
                    currentItem.message,
                    currentItem.view,
                    page,
                )
            }
            currentStep++
        } else {
            onDismiss()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (w != oldw || h != oldh) {
            if (::binding.isInitialized) {
                currentStep--
                showNextStep()
            }
        }
    }

    fun onDismiss() {
        listener?.onOverlayTutorialVisibilityChanged(false)
        spindleTutorialTooltipLabel.toolTipDissmiss()
        (parent as? ViewGroup)?.removeView(this@OverlayTutorialView)
    }

    private fun highlightView(view: View) {
        val location = IntArray(2)
        view.getLocationOnScreen(location)

        val parentLocation = IntArray(2)
        getLocationOnScreen(parentLocation)

        val x = location[0].toFloat() - parentLocation[0]
        val y = location[1].toFloat() - parentLocation[1]

        val margin = context.dp(context.dimen(R.dimen.tutorial_highlight_margin).toInt())

        val width = view.width
        val height = view.height
        val holeBounds = RectF(
            x - margin,
            y - margin,
            x + width + margin,
            y + height + margin,
        )
        transparentHoleView.setHoleBounds(holeBounds)
    }

    private fun showTooltipLabel(
        tooltipLabelPosition: OverlayTutorialViewItemTooltipPosition,
        message: String,
        view: View,
        page: String,
    ) {
        spindleTutorialTooltipLabel = TutorialTooltipLabel.Builder(context)
            .setMessage(message)
            .setTooltipPosition(tooltipLabelPosition)
            .setColor(TutorialTooltipLabel.Color.NEUTRAL)
            .setOnClickListener(object : TutorialTooltipLabel.Builder.OnClickListener {
                override fun onClick() {
                    showNextStep()
                }
            })
            .build()

        spindleTutorialTooltipLabel.setPage(page)
        if (boundaryView != null) {
            spindleTutorialTooltipLabel.setBoundary(boundaryView!!)
        } else {
            spindleTutorialTooltipLabel.setBoundary(binding.root)
        }
        spindleTutorialTooltipLabel.show(view)
    }
}

data class OverlayTutorialViewItem(
    val view: View,
    val message: String,
    val labelPosition: OverlayTutorialViewItemTooltipPosition,
    val overlayMessage: String,
)
