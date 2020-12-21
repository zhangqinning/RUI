package com.music.rui.ui.base.binding_adapter

import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout

class DrawerBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["isOpenDrawer"], requireAll = false)
        fun openDrawer(drawerLayout: DrawerLayout, isOpenDrawer: Boolean) {
            if (isOpenDrawer && !drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["allowDrawerOpen"], requireAll = false)
        fun allowDrawerOpen(drawerLayout: DrawerLayout, allowDrawerOpen: Boolean) {
            drawerLayout.setDrawerLockMode(if (allowDrawerOpen) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }

        @JvmStatic
        @BindingAdapter(value = ["bindDrawerListener"], requireAll = false)
        fun listenDrawerState(
            drawerLayout: DrawerLayout,
            listener: DrawerLayout.SimpleDrawerListener?
        ) {
            drawerLayout.addDrawerListener(listener!!)
        }
    }
}