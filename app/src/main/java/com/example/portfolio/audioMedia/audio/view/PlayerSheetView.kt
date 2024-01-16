package com.example.portfolio.audioMedia.audio.view

import android.app.Activity
import android.app.Dialog
import android.content.ContextWrapper
import android.content.DialogInterface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.portfolio.audioMedia.audio.view.control.DefaultMediaControl
import com.example.portfolio.audioMedia.audio.view.control.MediaControl
import com.example.portfolio.databinding.PlayerSheetViewBinding
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerSheetView() : BottomSheetDialogFragment(), MediaControl by DefaultMediaControl() {
    private lateinit var binding: PlayerSheetViewBinding

    private val peekHeight = 250

    private val bottomSheetDialogDefaultHeight: Int
        get() = windowHeight

    private val windowHeight: Int
        get() {
            val displayMetrics = DisplayMetrics()
            val activityContext = when (val context = requireContext()) {
                is ContextWrapper -> context.baseContext as Activity
                else -> context as Activity
            }
            activityContext.windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.heightPixels
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = PlayerSheetViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface: DialogInterface ->
            setupRatio(dialogInterface as BottomSheetDialog)
        }
        (dialog as BottomSheetDialog).behavior
            .addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) = Unit
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    binding.collapsedView.alpha = 1 - slideOffset
                    binding.expendThumbnail.alpha = slideOffset
                }
            })
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet: FrameLayout =
            bottomSheetDialog.findViewById(R.id.design_bottom_sheet) ?: return

        BottomSheetBehavior.from<FrameLayout>(bottomSheet).state =
            BottomSheetBehavior.STATE_COLLAPSED
        val bottomSheetLayoutParams = bottomSheet.layoutParams
        bottomSheetLayoutParams.height = bottomSheetDialogDefaultHeight

        bottomSheet.layoutParams = bottomSheetLayoutParams
        BottomSheetBehavior.from<FrameLayout>(bottomSheet).skipCollapsed = false
        BottomSheetBehavior.from<FrameLayout>(bottomSheet).peekHeight = peekHeight
        BottomSheetBehavior.from<FrameLayout>(bottomSheet).isHideable = false
    }
}