package com.example.portfolio.audio

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentAudioTrackListBinding

class AudioTrackListFragment : BaseFragment<FragmentAudioTrackListBinding>(R.layout.fragment_audio_track_list) {
    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_fragment_audio_track_list)
}
