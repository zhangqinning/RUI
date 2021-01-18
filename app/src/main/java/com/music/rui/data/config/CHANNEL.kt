package com.music.rui.data.config

enum class CHANNEL(val key: String, value: Int) {
    DISCORY("发现",DISCORY_ID),
    PLAY("播放",PLAY_ID)
}

const val DISCORY_ID = 0x01
const val PLAY_ID = 0x02

