package com.music.lib_architecture.domain.manager

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.music.lib_architecture.utils.Utils

class NetworkStateManager private constructor(): DefaultLifecycleObserver {

    private val mNetworkStateReceive: NetworkStateReceive by lazy {
        NetworkStateReceive()
    }

    companion object {
        public fun getInstance(): NetworkStateManager {
            return Holder.S_MANAGER
        }
    }

    object Holder {
        val S_MANAGER = NetworkStateManager()
    }

    override fun onResume(owner: LifecycleOwner) {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        Utils.getApp().applicationContext.registerReceiver(mNetworkStateReceive, filter)
    }

    override fun onPause(owner: LifecycleOwner) {
        Utils.getApp().applicationContext.unregisterReceiver(mNetworkStateReceive)
    }
}