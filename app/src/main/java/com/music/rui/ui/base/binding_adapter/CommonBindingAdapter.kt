package com.music.rui.ui.base.binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.music.lib_architecture.utils.ClickUtils
import com.music.lib_common_ui.widget.CaptchaView

class CommonBindingAdapter {

    @BindingAdapter(value = ["visible"], requireAll = false)
    fun visible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }


    @BindingAdapter(value = ["initCaptureViewListener"])
    fun setListener(captchaView: CaptchaView, listener: CaptchaView.OnInputListener?) {
        captchaView.setOnInputListener(listener)
    }

    @BindingAdapter(value = ["onClickWithDebouncing"], requireAll = false)
    fun onClickWithDebouncing(view: View?, clickListener: View.OnClickListener?) {
        ClickUtils.applySingleDebouncing(view, clickListener)
    }

}