package com.music.lib_architecture.ui.page

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFragment : DataBindingFragment() {

    fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

    protected open fun toggleSoftInput() {
        val imm =
                requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}