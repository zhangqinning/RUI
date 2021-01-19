package com.music.rui.ui.base.binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.music.lib_architecture.utils.ClickUtils
import com.music.lib_common_ui.widget.CaptchaView


class CommonBindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter(value = ["visible"], requireAll = false)
        fun visible(view: View, visible: Boolean) {
            view.visibility = if (visible) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter(value = ["initCaptureViewListener"])
        fun setListener(captchaView: CaptchaView, listener: CaptchaView.OnInputListener?) {
            captchaView.setOnInputListener(listener)
        }

        @JvmStatic
        @BindingAdapter(value = ["onClickWithDebouncing"], requireAll = false)
        fun onClickWithDebouncing(view: View?, clickListener: View.OnClickListener?) {
            ClickUtils.applySingleDebouncing(view, clickListener)
        }

        @JvmStatic
        @BindingAdapter(value = ["currentItem"])
        fun setCurrentItem(viewPager: ViewPager, item: Int) {
            viewPager.currentItem = item
        }

        @JvmStatic
        @BindingAdapter(value = ["viewPagerAdapter"])
        fun setViewPagerAdapter(viewPager: ViewPager, adapter: PagerAdapter) {
            viewPager.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter(value = ["viewPagerOffsetLimit"])
        fun setViewPagerOffsetLimit(viewPager: ViewPager, limit: Int) {
            viewPager.offscreenPageLimit = limit
        }

        @JvmStatic
        @BindingAdapter(value = ["addOnPageChangerListener"])
        fun addOnPageChangerListener(viewPager: ViewPager, listener:ViewPager.OnPageChangeListener){
            viewPager.addOnPageChangeListener(listener)
        }
    }
}