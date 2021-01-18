package com.music.rui.ui.base.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

class IndicatorBindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter(value = ["setNavigator"])
        fun setNavigator(indicator: MagicIndicator,commonNavigator:CommonNavigator) {
            indicator.navigator = commonNavigator
        }

        @JvmStatic
        @BindingAdapter(value = ["setBackgroundColor"])
        fun setBackgroundColor(indicator: MagicIndicator, color: Int) {
            indicator.setBackgroundColor(color)
        }

        @JvmStatic
        @BindingAdapter(value = ["bind"])
        fun bind(indicator: MagicIndicator, viewPager: ViewPager) {
            ViewPagerHelper.bind(indicator, viewPager)
        }
    }
}