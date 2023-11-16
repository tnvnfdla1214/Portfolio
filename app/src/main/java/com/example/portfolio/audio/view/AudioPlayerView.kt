package com.example.portfolio.audio.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.portfolio.R
import com.example.portfolio.databinding.AudioPlayerViewBinding
import com.example.portfolio.widget.inflate
import com.google.android.material.bottomsheet.BottomSheetBehavior

class AudioPlayerView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs), PlayerSheetStateListener {
    private val binding = inflate<AudioPlayerViewBinding>(R.layout.audio_player_view, true)
    override fun onPlayerSheetChanged(newState: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        const val PLAYER_SHEET_COLLAPSED = BottomSheetBehavior.STATE_COLLAPSED
        const val PLAYER_SHEET_EXPANDED = BottomSheetBehavior.STATE_EXPANDED
        private const val OPENER_ROTATION_COLLAPSE = 0
        private const val OPENER_ROTATION_EXPAND = 180
        private const val OPENER_ROTATION_MAX = OPENER_ROTATION_EXPAND
    }

}
