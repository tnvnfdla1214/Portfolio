package com.example.portfolio.audioMedia

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentMediaBinding

class MediaFragment : BaseFragment<FragmentMediaBinding>(R.layout.fragment_media) {

    override fun initView() {}

    override fun getScreenName(): String = getString(R.string.screen_fragment_audio_media)
}
