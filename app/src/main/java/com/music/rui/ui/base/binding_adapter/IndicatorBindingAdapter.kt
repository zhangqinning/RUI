package com.music.rui.ui.base.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

object IndicatorBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["setNavigator"])
    fun setNavigator(indicator: MagicIndicator, commonNavigator: CommonNavigator) {
        indicator.navigator = commonNavigator
    }

    @JvmStatic
    @BindingAdapter(value = ["setBackgroundColor"])
    fun setBackgroundColor(indicator: MagicIndicator, color: Int) {
        indicator.setBackgroundColor(color)
    }

    @JvmStatic
    @BindingAdapter(value = ["bind"])
    fun bind(viewPager: ViewPager,indicator: MagicIndicator) {
        ViewPagerHelper.bind(indicator, viewPager)
    }

//    @JvmStatic
//    @BindingAdapter(value = ["onPageScrolled"])
//    fun onPageScrolled(
//        indicator: MagicIndicator,
//        position: Int,
//        positionOffset: Float,
//        positionOffsetPixels: Int
//    ) {
//        indicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
//    }

//    @JvmStatic
//    @BindingAdapter(value = ["onPageSelected"])
//    fun onPageSelected(indicator: MagicIndicator, position: Int) {
//        indicator.onPageSelected(position)
//    }
}