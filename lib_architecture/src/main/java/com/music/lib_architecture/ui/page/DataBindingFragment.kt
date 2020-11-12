package com.music.lib_architecture.ui.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.core.util.keyIterator
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope

abstract class DataBindingFragment : Fragment() {

    lateinit var mBinding: ViewDataBinding



    abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

        val dataBindingConfig = getDataBindingConfig()


    }

    abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val dataBindingConfig = getDataBindingConfig()

        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, dataBindingConfig.layout, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(dataBindingConfig.vmVariableId, dataBindingConfig.stateViewModel)
        for (i in dataBindingConfig.bindingParams.keyIterator()) {
            binding.setVariable(i, dataBindingConfig.bindingParams[i])
        }
        mBinding = binding
        return binding.root
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        GlobalScope.
    }


}