package com.example.portfolio.audioMedia

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentAudioBinding

class AudioFragment : BaseFragment<FragmentAudioBinding>(R.layout.fragment_audio) {
    override fun initView() {
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_audio)
}
