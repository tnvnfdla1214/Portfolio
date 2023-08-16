package com.example.portfolio.customView

import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.portfolio.BaseFragment
import com.example.portfolio.R
import com.example.portfolio.customView.view.OverlayTutorialListener
import com.example.portfolio.customView.view.OverlayTutorialView
import com.example.portfolio.customView.view.OverlayTutorialViewItem
import com.example.portfolio.customView.view.OverlayTutorialViewItemTooltipPosition
import com.example.portfolio.databinding.FragmentCustom1Binding

class Custom1Frgment :
    BaseFragment<FragmentCustom1Binding>(R.layout.fragment_custom1),
    OverlayTutorialListener {

    private lateinit var overlayTutorialView: OverlayTutorialView

    override fun initView() {
        binding.onboardingButton.setOnClickListener {
            showOverlayTutorial()
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_custom_1)

    private fun showOverlayTutorial() {
        val tutorialItems = listOf(
            OverlayTutorialViewItem(
                view = binding.bottomSheetButton,
                message = "바텀 시트 버튼입니다.",
                labelPosition = OverlayTutorialViewItemTooltipPosition.BOTTOM,
                overlayMessage = "계속하려면 탭 하세요",
            ),
            OverlayTutorialViewItem(
                view = binding.onboardingButton,
                message = "오버레이 온보딩 버튼입니다.",
                labelPosition = OverlayTutorialViewItemTooltipPosition.BOTTOM,
                overlayMessage = "계속하려면 탭 하세요",
            ),
            OverlayTutorialViewItem(
                view = binding.text1,
                message = "메세지도 위치를 정해줄 수 있습니다.",
                labelPosition = OverlayTutorialViewItemTooltipPosition.TOP,
                overlayMessage = "계속하려면 탭 하세요",
            ),
            OverlayTutorialViewItem(
                view = binding.text2,
                message = "뷰의 크기가 달라도 하이라이트의 크기가 다릅니다.",
                labelPosition = OverlayTutorialViewItemTooltipPosition.BOTTOM,
                overlayMessage = "계속하려면 탭 하세요",
            ),
            OverlayTutorialViewItem(
                view = binding.text3,
                message = "순서도 마음대로 입니다.",
                labelPosition = OverlayTutorialViewItemTooltipPosition.TOP,
                overlayMessage = "이제 끝났습니다.",
            ),
        )

        overlayTutorialView = OverlayTutorialView(requireContext()).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT,
            )
            listener = this@Custom1Frgment
            isFocusable = true
            setTutorialItems(tutorialItems)
            boundaryMessage(binding.root)
            show()
        }
        (requireActivity().window.decorView as? ViewGroup)?.addView(overlayTutorialView)
    }

    override fun onOverlayTutorialVisibilityChanged(isVisible: Boolean) {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                overlayTutorialView.onDismiss()
                remove()
            }
        }
        if (isVisible) {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        } else {
            callback.remove()
        }
    }
}
