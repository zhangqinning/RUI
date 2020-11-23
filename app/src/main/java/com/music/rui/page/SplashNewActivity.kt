package com.music.rui.page

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.music.lib_architecture.utils.BarUtils
import com.music.rui.R
import timber.log.Timber

class SplashNewActivity :AppCompatActivity() {

    var perms = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        setContentView(R.layout.delegate_splash)
        Timber.d("splash onCreate ")
    }
}