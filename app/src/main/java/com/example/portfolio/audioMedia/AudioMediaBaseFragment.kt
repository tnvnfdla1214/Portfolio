package com.example.portfolio.audioMedia

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentAudioMediaBaseBinding
import com.example.portfolio.util.navigation.NavTarget

class AudioMediaBaseFragment : BaseFragment<FragmentAudioMediaBaseBinding>(R.layout.fragment_audio_media_base) {
    override fun initView() {
        binding.audio.setOnClickListener {
            NavTarget.toAudio(navController)
        }
        binding.media.setOnClickListener {
            NavTarget.toMedia(navController)
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_audio_media)
}
