package com.music.lib_architecture.ui.page

import android.app.Activity
import android.app.Application
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.util.keyIterator
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class DataBindingFragment : Fragment() {

    lateinit var mBinding: ViewDataBinding
    private var mAnimationLoaded = false
    abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBindingConfig = getDataBindingConfig()
        Log.d("zqn","onCreateView : $dataBindingConfig")

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            dataBindingConfig.layout,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.setVariable(dataBindingConfig.vmVariableId, dataBindingConfig.stateViewModel)
        for (i in dataBindingConfig.bindingParams.keyIterator()) {
            binding.setVariable(i, dataBindingConfig.bindingParams[i])
        }
        mBinding = binding
        return binding.root
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        GlobalScope.launch(Dispatchers.Main) {
            delay(280)
            if (!mAnimationLoaded) {
                mAnimationLoaded = true
                loadInitData()
            }
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    open fun loadInitData() {}

    open fun isDebug(): Boolean {
        return requireActivity().applicationContext.applicationInfo != null &&
                requireActivity().applicationContext
                    .applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }

    protected open fun showLongToast(text: String?) {
        Toast.makeText(requireActivity().applicationContext, text, Toast.LENGTH_LONG).show()
    }

    protected open fun showShortToast(text: String?) {
        Toast.makeText(requireActivity().applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    protected open fun showLongToast(stringRes: Int) {
        showLongToast(requireActivity().applicationContext.getString(stringRes))
    }

    protected open fun showShortToast(stringRes: Int) {
        showShortToast(requireActivity().applicationContext.getString(stringRes))
    }

    fun getAppFactory(activity: Activity): ViewModelProvider.Factory {
        requireActivity()
        return ViewModelProvider.AndroidViewModelFactory.getInstance(checkApplication(activity))
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call."
            )
    }
}