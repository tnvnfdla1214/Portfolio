package com.example.portfolio.audioMedia.audio

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.media3.exoplayer.SimpleExoPlayer
import com.example.portfolio.R
import com.example.portfolio.android.collectWithLifecycle
import com.example.portfolio.audioMedia.audio.view.PlayerSheetView
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentAudioBinding
import dagger.hilt.android.AndroidEntryPoint
//ToDo: 1. Exoplayer 라이브러리를 사용하여 음악 재생 기능을 구현한다.
//ToDo: 2. Player Notification을 구현한다.
//ToDo: 3. Player Notification을 클릭하면 PlayerSheetView가 나타나도록 한다.
//ToDo: 4. PlayerSheetView에서는 음악을 재생/일시정지 할 수 있어야 한다.
//ToDo: 5. PlayerSheetView에서는 음악의 재생 시간을 SeekBar로 표시한다.
//ToDo: 6. PlayerSheetView에서는 음악의 재생 시간을 TextView로 표시한다.
//ToDO: 7. 다운로드 기능을 만든다.
@AndroidEntryPoint
class AudioFragment : BaseFragment<FragmentAudioBinding>(R.layout.fragment_audio) {
    private val viewModel by viewModels<AudioViewModel>()
    private lateinit var musicListAdapter: MusicListAdapter
    override fun initView() {
        observeViewModel()
        //showPlayerView()
    }

    private fun observeViewModel() {
        viewModel.tracks.collectWithLifecycle(this) {
            musicListAdapter = MusicListAdapter(it) { track -> viewModel.selectTrackList(track) }
            binding.musicList.adapter = musicListAdapter
        }
    }

    private fun showPlayerView() {
        val dialog = PlayerSheetView()

        dialog.show(parentFragmentManager, "PLAYER_BOTTOM_SHEET_TAG")
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_audio)
}
