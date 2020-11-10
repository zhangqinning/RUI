package com.music.lib_architecture.ui.callback

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class UnPeekLiveData<T> : ProtectedUnPeekLiveData<T>() {

    @Deprecated("")
    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        throw IllegalArgumentException(
            "请不要在 UnPeekLiveData 中使用 observe 方法。" +
                    "取而代之的是在 Activity 和 Fragment 中分别使用 observeInActivity 和 observeInFragment 来观察。\n\n" +
                    "Taking into account the normal permission of preventing backflow logic, " +
                    " do not use observeForever to communicate between pages." +
                    "Instead, you can use ObserveInActivity and ObserveInFragment methods " +
                    "to observe in Activity and Fragment respectively."
        )
    }

    @Deprecated("")
    override fun observeForever(observer: Observer<in T?>) {
        throw java.lang.IllegalArgumentException(
            "出于生命周期安全的考虑，请不要在 UnPeekLiveData 中使用 observeForever 方法。\n\n" +
                    "Considering avoid lifecycle security issues," +
                    " do not use observeForever for communication between pages."
        )
    }

    companion object{
        class Builder<T> {
            /**
             * 是否允许传入 null value
             */
            private var isAllowNullValue = false
            fun setAllowNullValue(allowNullValue: Boolean): Builder<T> {
                isAllowNullValue = allowNullValue
                return this
            }

            fun create(): UnPeekLiveData<T> {
                val liveData = UnPeekLiveData<T>()
                liveData.isAllowNullValue = isAllowNullValue
                return liveData
            }
        }
    }

}