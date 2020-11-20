package com.music.lib_api.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class AudioBean(
    @ColumnInfo(name = "song_id") val id: String,
    @ColumnInfo(name = "song_url") val url: String,
    @ColumnInfo(name = "song_name") val name: String,
    @ColumnInfo(name = "song_author") val author: String,
    @ColumnInfo(name = "song_album") val album: String,
    @ColumnInfo(name = "song_albumInfo") val albumInfo: String,
    @ColumnInfo(name = "song_albumPic") val albumPic: String,
    @ColumnInfo(name = "song_totaltime") val totalTime: String,
) {

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0

    private fun getSongPlayUrl(id: Long): String? {
        return "https://music.163.com/song/media/outer/url?id=$id.mp3"
    }

    override fun toString(): String {
        return "AudioBean{" +
                "id='" + id + '\'' +
                ", Url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", album='" + album + '\'' +
                ", albumInfo='" + albumInfo + '\'' +
                ", albumPic='" + albumPic + '\'' +
                ", totalTime='" + totalTime + '\'' +
                '}'
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        return if (other !is AudioBean) {
            false
        } else other.id == id
    }


//    //将歌单中的SongsBean转化成AudioBean
//    fun convertSongToAudioBean(item: SongDetailBean.SongsBean): AudioBean? {
//        return AudioBean(
//            java.lang.String.valueOf(item.getId()),
//            AudioBean(item.getId()),
//            item.getName(),
//            item.getAr().get(0).getName(),
//            item.getAl().getName(),
//            item.getAl().getName(),
//            item.getAl().getPicUrl(),
//            SimpleDateFormat("mm:ss", Locale.getDefault()).format(item.getDt())
//        )
//    }
//
//    //将动态中的RecommendBean转化成AudioBean
//    fun convertSongToAudioBean(item: DailyRecommendBean.RecommendBean): AudioBean? {
//        return AudioBean(
//            java.lang.String.valueOf(item.getId()),
//            item.getSongPlayUrl(item.getId()),
//            item.getName(),
//            item.getArtists().get(0).getName(),
//            item.getAlbum().getName(),
//            item.getAlbum().getName(),
//            item.getAlbum().getPicUrl(),
//            SimpleDateFormat("mm:ss", Locale.getDefault()).format(item.getDuration())
//        )
//    }

}