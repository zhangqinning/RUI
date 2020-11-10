package com.music.lib_architecture.ui.page

import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

open class DataBindingActivity : AppCompatActivity() {

    private val mActivityProvider:ViewModelProvider
    val mFactory:ViewModelProvider.Factory
    val mBinding: ViewDataBinding
    var mTvStrictModeTip: TextView

    open fun initViewModle()
    open fun getDataBindingConfig():DataBindingConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModle()
        val dataBindingConfig = getDataBindingConfig()

        val binding:ViewDataBinding = DataBindingUtil.setContentView(this,
            dataBindingConfig.layout)
        binding.lifecycleOwner = this
    }
\

    protected open fun getBinding(): ViewDataBinding? {
        if (isDebug() && mBinding != null) {
            if (mTvStrictModeTip == null) {
                mTvStrictModeTip = TextView(applicationContext)
                mTvStrictModeTip.alpha = 0.5f
                mTvStrictModeTip.textSize = 16f
                mTvStrictModeTip.setBackgroundColor(Color.WHITE)
                mTvStrictModeTip.setText(R.string.debug_activity_databinding_warning)
                (mBinding.getRoot() as ViewGroup).addView(mTvStrictModeTip)
            }
        }
        return mBinding
    }

    open fun isDebug(): Boolean {
        return applicationContext.applicationInfo != null &&
                applicationContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }
}