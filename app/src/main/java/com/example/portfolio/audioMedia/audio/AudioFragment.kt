package com.example.portfolio.audioMedia.audio

import androidx.fragment.app.viewModels
import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentAudioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AudioFragment : BaseFragment<FragmentAudioBinding>(R.layout.fragment_audio) {
    private val viewModel by viewModels<AudioViewModel>()
    private lateinit var musicListAdapter: MusicListAdapter
    override fun initView() {
        viewModel.getMusicList()
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_audio)
}
