package com.music.lib_architecture.ui.page

import android.util.SparseArray
import androidx.lifecycle.ViewModel

data class DataBindingConfig(
    val layout: Int,
    val vmVariableId: Int,
    val stateViewModel: ViewModel,
) {
    private val bindingParams = SparseArray<Any>()

    fun addBindingParam(variableId: Int, any: Any): DataBindingConfig {
        if (bindingParams[variableId] == null) {
            bindingParams.put(variableId, any)
        }
        return this
    }
}