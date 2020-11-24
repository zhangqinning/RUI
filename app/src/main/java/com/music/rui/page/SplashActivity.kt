package com.music.rui.page

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.music.lib_architecture.utils.BarUtils
import com.music.lib_architecture.utils.Utils
import com.music.lib_common_ui.utils.SharePreferenceUtil
import com.music.rui.MainActivity
import com.music.rui.R
import com.music.rui.page.login.LoginActivity
import io.reactivex.rxjava3.core.Observable
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    var perms = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        setContentView(R.layout.delegate_splash)

        if (!EasyPermissions.hasPermissions(this, *perms)) {
            EasyPermissions.requestPermissions(this, "开权限", 1, *perms)
        } else {
            jumpIntoMainActivity()
        }
    }

    private fun jumpIntoMainActivity() {
        Timber.d("splash jumpIntoMainActivity ")
        Observable.timer(2, TimeUnit.SECONDS).subscribe {
            val preferenceUtil = SharePreferenceUtil.getInstance(Utils.getApp())
            if (preferenceUtil.localMusicCount == -1) {
//                preferenceUtil.saveLocalMusicCount(
//                    MusicUtils.queryMusicSize(
//                        Utils.getApp(),
//                        MusicUtils.START_FROM_LOCAL
//                    )
//                )
            }
            val authToken = preferenceUtil.getAuthToken("")
            if (authToken.isNullOrEmpty()) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (!EasyPermissions.hasPermissions(this, *perms.toTypedArray())) {
            EasyPermissions.requestPermissions(this, "开权限", 1, *perms.toTypedArray())
        } else {
            jumpIntoMainActivity()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}