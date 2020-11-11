package com.music.lib_architecture.domain.manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import com.example.lib_architecture.R
import com.music.lib_architecture.utils.NetworkUtils

class NetworkStateReceive : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            if (!NetworkUtils.isConnected()) {
                Toast.makeText(context, context.getString(R.string.network_not_good), Toast.LENGTH_SHORT).show()
            }
        }
    }


}