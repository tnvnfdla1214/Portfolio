package com.example.portfolio.wrapper

import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer
import java.util.HashSet

object EventBus {
    private val mHandler = Handler(Looper.getMainLooper())
    private var sBus: Bus? = Bus(ThreadEnforcer.MAIN)
    private val sRegistered = HashSet<Any>()

    private val bus: Bus
        get() {
            if (sBus == null) {
                sBus = Bus(ThreadEnforcer.MAIN)
            }
            return sBus!!
        }

    val registered: Set<Any>
        get() = sRegistered

    fun register(obj: Any) {
        if (!isRegistered(obj)) {
            try {
                bus.register(obj)
                sRegistered.add(obj)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }

    fun unregister(obj: Any) {
        if (isRegistered(obj)) {
            try {
                bus.unregister(obj)
                sRegistered.remove(obj)
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }

    private fun isRegistered(obj: Any): Boolean {
        return sRegistered.contains(obj)
    }

    fun post(event: Any) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            bus.post(event)
        } else {
            mHandler.post { bus.post(event) }
        }
    }
}
