package com.music.rui.ui.page.adapter

import android.content.Context
import android.graphics.Color
import com.music.rui.data.config.CHANNEL
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

class IndicatorAdapter(private val data: Array<CHANNEL>, val clickListener: (index: Int) -> Unit) :
    CommonNavigatorAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getTitleView(context: Context, index: Int): IPagerTitleView {
        return SimplePagerTitleView(context).apply {
            text = data[index].key
            textSize = 18f
            normalColor = Color.GRAY
            selectedColor = Color.BLACK
            setOnClickListener {
                clickListener(index)
            }
        }
    }

    override fun getIndicator(context: Context?): IPagerIndicator {
        return BezierPagerIndicator(context).apply {
            setColors(
                Color.parseColor("#ff4a42"),
                Color.parseColor("#fcde64"),
                Color.parseColor("#73e8f4"),
                Color.parseColor("#76b0ff"),
                Color.parseColor("#c683fe")
            )
        }
    }
}

class ScaleTransitionPagerTitleView(context: Context) : ColorTransitionPagerTitleView(context) {

    private var mMinScale = 0.75f

    override fun onEnter(index: Int, totalCount: Int, enterPercent: Float, leftToRight: Boolean) {
        super.onEnter(index, totalCount, enterPercent, leftToRight)
        scaleX = mMinScale + (1.0f - mMinScale) * enterPercent
        scaleY = mMinScale + (1.0f - mMinScale) * enterPercent
    }

    override fun onLeave(index: Int, totalCount: Int, leavePercent: Float, leftToRight: Boolean) {
        super.onLeave(index, totalCount, leavePercent, leftToRight)
        scaleX = 1.0f + (mMinScale - 1.0f) * leavePercent
        scaleY = 1.0f + (mMinScale - 1.0f) * leavePercent
    }

}