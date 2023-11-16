package com.example.portfolio

import android.app.Application
import android.content.Context
import com.example.portfolio.wrapper.EventBus
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        ServerUrl.init(this)
        EventBus.register(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        appContext = null
        EventBus.unregister(this)
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}
