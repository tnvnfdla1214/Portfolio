package com.example.portfolio.main

import com.example.portfolio.R
import com.example.portfolio.base.BaseFragment
import com.example.portfolio.databinding.FragmentMainBinding
import com.example.portfolio.util.navigation.NavTarget

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun initView() {
        binding.run {
            nav.setOnClickListener {
                NavTarget.toNavigate(navController)
            }

            crash.setOnClickListener {
                NavTarget.toCrash(navController)
            }

            custom.setOnClickListener {
                NavTarget.toCustom(navController)
            }

            audioTrack.setOnClickListener {
                NavTarget.toAudioTrackList(navController)
            }
        }
    }

    override fun getScreenName(): String = getString(R.string.screen_fragment_main)
}
