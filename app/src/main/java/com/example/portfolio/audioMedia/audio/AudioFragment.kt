package com.example.portfolio.audioMedia.audio

import androidx.fragment.app.viewModels
import com.example.portfolio.R
import com.example.portfolio.android.collectWithLifecycle
import com.example.portfolio.audioMedia.audio.view.PlayerSheetView
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentAudioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AudioFragment : BaseFragment<FragmentAudioBinding>(R.layout.fragment_audio) {
    private val viewModel by viewModels<AudioViewModel>()
    private lateinit var musicListAdapter: MusicListAdapter
    override fun initView() {
        observeViewModel()
        showPlayerView()
    }

    private fun observeViewModel() {
        viewModel.musics.collectWithLifecycle(this) {
            musicListAdapter = MusicListAdapter(it)
            binding.musicList.adapter = musicListAdapter
        }
    }

    private fun showPlayerView() {
        val dialog = PlayerSheetView()

        dialog.show(parentFragmentManager, "PLAYER_BOTTOM_SHEET_TAG")
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_audio)
}
