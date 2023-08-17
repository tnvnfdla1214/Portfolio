package com.example.portfolio.customView.view

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.portfolio.databinding.BottomSheetViewBinding
import com.example.portfolio.utils.Display
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetView : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetViewBinding
    private lateinit var buttonLayoutParams: ConstraintLayout.LayoutParams

    private var collapsedMargin = 0
    private var buttonHeight = 0
    private var expandedHeight = 0

    private val bottomSheetDialogDefaultHeight: Int
        get() = windowHeight

    private val windowHeight: Int
        get() {
            val displayMetrics = DisplayMetrics()
            (requireContext() as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.heightPixels
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomSheetViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface: DialogInterface ->
            setupRatio(
                dialogInterface as BottomSheetDialog,
            )
        }
        (dialog as BottomSheetDialog).behavior
            .addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) = Unit
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    if (slideOffset > 0) {
                        buttonLayoutParams.topMargin =
                            (
                                (expandedHeight - buttonHeight - collapsedMargin) *
                                    slideOffset + collapsedMargin
                                ).toInt()
                    } else {
                        buttonLayoutParams.topMargin = collapsedMargin
                    }
                    binding.button.layoutParams = buttonLayoutParams
                }
            })
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet: FrameLayout =
            bottomSheetDialog.findViewById(R.id.design_bottom_sheet) ?: return

        buttonLayoutParams = binding.button.layoutParams as ConstraintLayout.LayoutParams

        BottomSheetBehavior.from<FrameLayout>(bottomSheet).state =
            BottomSheetBehavior.STATE_COLLAPSED
        val bottomSheetLayoutParams = bottomSheet.layoutParams
        bottomSheetLayoutParams.height = bottomSheetDialogDefaultHeight

        expandedHeight = (Display.getImmutableHeight(requireActivity()))
        val peekHeight = (Display.getImmutableHeight(requireActivity()) * 0.6).toInt()

        bottomSheet.layoutParams = bottomSheetLayoutParams
        BottomSheetBehavior.from<FrameLayout>(bottomSheet).skipCollapsed = false
        BottomSheetBehavior.from<FrameLayout>(bottomSheet).peekHeight = peekHeight
        BottomSheetBehavior.from<FrameLayout>(bottomSheet).isHideable = true

        buttonHeight = binding.button.height
        collapsedMargin = peekHeight - buttonHeight
        buttonLayoutParams.topMargin = collapsedMargin
        binding.button.layoutParams = buttonLayoutParams

        val recyclerLayoutParams = binding.scrollView.layoutParams as ViewGroup.MarginLayoutParams
        recyclerLayoutParams.bottomMargin = buttonHeight
        binding.scrollView.layoutParams = recyclerLayoutParams
    }
}
