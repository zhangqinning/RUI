package com.music.lib_architecture.ui.page

import android.app.Activity
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.jetbrains.annotations.NotNull
import android.os.Handler

abstract class BaseFragment : DataBindingFragment() {

    companion object {
        val HANDLER: Handler by lazy {
            Handler()
        }
        var mAnimationLoaded = false
    }

    fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

    protected open fun toggleSoftInput() {
        val imm =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun <T : ViewModel> getFragmentScopeViewModel(@NotNull modelClass: Class<T>) =
        fragmentProvider[modelClass]


    fun <T : ViewModel> getActivityScopeViewModel(@NotNull modelClass: Class<T>) =
        activityProvider[modelClass]


    fun <T : ViewModel> getApplicationScopeViewModel(@NotNull modelClass: Class<T>) =
        mApplicationViewModelProvider[modelClass]


    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        //TODO 错开动画转场与 UI 刷新的时机，避免掉帧卡顿的现象
        HANDLER.postDelayed({
            if (!mAnimationLoaded) {
                mAnimationLoaded = true
                loadInitData()
            }
        }, 280)
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

}