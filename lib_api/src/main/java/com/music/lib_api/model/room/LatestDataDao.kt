package com.music.lib_api.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.music.lib_api.model.song.AudioBean

@Dao
interface LatestDataDao {
    /**
     * 存储并且获取最近播放的歌曲、视频、专辑、歌单、电台
     */
    //获取最近播放的歌曲
    @get:Query("SELECT * FROM AudioBean")
    val recentSong: List<Any?>?

    //查询最近播放的歌曲的数量
    @get:Query("SELECT COUNT(*) FROM AudioBean")
    val recentSongSize: LiveData<Int?>?

    //插入最近播放的一首歌曲
    @Insert
    fun insertRecentSong(vararg song: AudioBean?)

    @Query("SELECT * FROM AudioBean WHERE song_id = :id")
    fun getAudioBeanById(id: String?): AudioBean?

    //清空最近播放的音乐存储
    @Query("DELETE FROM AudioBean")
    fun deleteAllRecentSong()
}