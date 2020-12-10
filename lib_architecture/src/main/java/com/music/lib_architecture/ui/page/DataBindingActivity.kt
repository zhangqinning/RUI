package com.music.lib_architecture.ui.page

import android.app.Activity
import android.app.Application
import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.keyIterator
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lib_architecture.R

abstract class DataBindingActivity : AppCompatActivity() {

    val mActivityViewModelProvider: ViewModelProvider by lazy { ViewModelProvider(this) }
    val mApplicationViewModelProvider: ViewModelProvider by lazy {
        ViewModelProvider(
            applicationContext as BaseApplication,
            getAppFactory(this)
        )
    }

    lateinit var mBinding: ViewDataBinding

    abstract fun initViewModel()
    abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        val dataBindingConfig = getDataBindingConfig()

        val binding: ViewDataBinding = DataBindingUtil.setContentView(
            this,
            dataBindingConfig.layout
        )
        binding.lifecycleOwner = this
        binding.setVariable(dataBindingConfig.vmVariableId, dataBindingConfig.stateViewModel)
        for (i in dataBindingConfig.bindingParams.keyIterator()) {
            binding.setVariable(i, dataBindingConfig.bindingParams[i])
        }
        mBinding = binding
    }

    private val mTvStrictModeTip: TextView by lazy {
        TextView(applicationContext).apply {
            alpha = 0.5f
            textSize = 16f
            setBackgroundColor(Color.WHITE)
            setText(R.string.debug_activity_databinding_warning)
        }
    }

    fun <T : ViewModel> getActivityViewModel(@NonNull modelClass: Class<T>) =
        mActivityViewModelProvider.get(modelClass)

    fun getBinding(): ViewDataBinding {
        if (isDebug()) {
            (mBinding.root as ViewGroup).addView(mTvStrictModeTip)
        }
        return mBinding
    }

    open fun isDebug(): Boolean {
        return applicationContext.applicationInfo != null &&
                applicationContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }


    protected open fun showLongToast(text: String?) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    protected open fun showShortToast(text: String?) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    protected open fun showLongToast(stringRes: Int) {
        showLongToast(applicationContext.getString(stringRes))
    }

    protected open fun showShortToast(stringRes: Int) {
        showShortToast(applicationContext.getString(stringRes))
    }

    fun getAppFactory(activity: Activity): ViewModelProvider.Factory {
        val application: Application = checkApplication(activity)
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call."
            )
    }


}