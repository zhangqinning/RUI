package com.music.rui.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Files.FileColumns;

import com.github.promeg.pinyinhelper.Pinyin;
import com.netease.lib_api.model.info.AlbumInfo;
import com.netease.lib_api.model.info.ArtistInfo;
import com.netease.lib_api.model.info.FolderInfo;
import com.netease.lib_api.model.info.MusicInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询各主页信息，获取封面图片等
 */
public class MusicUtils {


    public static final int START_FROM_ARTIST = 1;
    public static final int START_FROM_ALBUM = 2;
    public static final int START_FROM_LOCAL = 3;
    public static final int START_FROM_FOLDER = 4;

    public static final int FILTER_SIZE = 1024 * 1024;// 1MB
    public static final int FILTER_DURATION = 60 * 1000;// 1分钟


    private static String[] proj_music = new String[]{
            Media._ID, Media.TITLE,
            Media.DATA, Media.ALBUM_ID,
            Media.ALBUM, Media.ARTIST,
            Media.ARTIST_ID, Media.DURATION, Media.SIZE};
    private static String[] proj_album = new String[]{Albums._ID, Albums.ALBUM_ART,
            Albums.ALBUM, Albums.NUMBER_OF_SONGS, Albums.ARTIST};
    private static String[] proj_artist = new String[]{
            MediaStore.Audio.Artists.ARTIST,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
            MediaStore.Audio.Artists._ID};
    private static String[] proj_folder = new String[]{FileColumns.DATA};


    /**
     * 获取包含音频文件的文件夹信息
     *
     * @param context context
     */
    public static List<FolderInfo> queryFolder(Context context) {

        Uri uri = MediaStore.Files.getContentUri("external");
        ContentResolver cr = context.getContentResolver();
        // 查询语句：检索出.mp3为后缀名，时长大于1分钟，文件大小大于1MB的媒体文件

        String mSelection = FileColumns.MEDIA_TYPE
                + " = " + FileColumns.MEDIA_TYPE_AUDIO + " and " + "("
                + FileColumns.DATA + " like'%.mp3' or " + Media.DATA
                + " like'%.wma')" + " and " + Media.SIZE + " > " + FILTER_SIZE +
                " and " + Media.DURATION + " > " + FILTER_DURATION +
                ") group by ( " + FileColumns.PARENT;

        return getFolderList(cr.query(uri, proj_folder, mSelection, null,
                null));

    }

    /**
     * 获取歌手信息
     *
     * @param context context
     */
    public static List<ArtistInfo> queryArtist(Context context) {

        Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        ContentResolver cr = context.getContentResolver();

        String where = MediaStore.Audio.Artists._ID
                + " in (select distinct " + Media.ARTIST_ID
                + " from audio_meta where (1=1 )" + " and " + Media.SIZE + " > " + FILTER_SIZE +
                " and " + Media.DURATION + " > " + FILTER_DURATION +
                ")";

        return getArtistList(cr.query(uri, proj_artist,
                where, null, PreferencesUtil.getInstance(context).getArtistSortOrder()));

    }

    /**
     * 获取专辑信息
     *
     * @param context context
     */
    public static List<AlbumInfo> queryAlbums(Context context) {

        ContentResolver cr = context.getContentResolver();

        // Media.ALBUM_KEY 按专辑名称排序
        String where = Albums._ID
                + " in (select distinct " + Media.ALBUM_ID
                + " from audio_meta where (1=1)" + " and " + Media.SIZE + " > " + FILTER_SIZE +
                " and " + Media.DURATION + " > " + FILTER_DURATION +
                " )";
        return getAlbumList(cr.query(Albums.EXTERNAL_CONTENT_URI, proj_album,
                where, null, PreferencesUtil.getInstance(context).getAlbumSortOrder()));

    }


    /**
     * @param context context
     * @param from    不同的界面进来要做不同的查询
     */
    public static List<MusicInfo> queryMusic(Context context, int from) {
        return queryMusic(context, null, from);
    }

    //音乐数量
    public static int queryMusicSize(Context context, int from) {
        return queryMusic(context, null, from).size();
    }


    public static ArrayList<MusicInfo> queryMusic(Context context, String id, int from) {

        Uri uri = Media.EXTERNAL_CONTENT_URI;
        ContentResolver cr = context.getContentResolver();

        StringBuilder select = new StringBuilder(" 1=1 and title != ''");
        // 查询语句：检索出.mp3为后缀名，时长大于1分钟，文件大小大于1MB的媒体文件
        select.append(" and " + Media.SIZE + " > " + FILTER_SIZE);
        select.append(" and " + Media.DURATION + " > " + FILTER_DURATION);

        String selectionStatement = "is_music=1 AND title != ''";
        final String songSortOrder = PreferencesUtil.getInstance(context).getSongSortOrder();


        switch (from) {
            //本地歌曲
            case START_FROM_LOCAL:
                return getMusicListCursor(cr.query(uri, proj_music,
                        select.toString(), null,
                        songSortOrder));
            //从歌手
            case START_FROM_ARTIST:
                select.append(" and " + Media.ARTIST_ID + " = " + id);
                return getMusicListCursor(cr.query(uri, proj_music, select.toString(), null,
                        PreferencesUtil.getInstance(context).getArtistSongSortOrder()));
            //从专辑
            case START_FROM_ALBUM:
                select.append(" and " + Media.ALBUM_ID + " = " + id);
                return getMusicListCursor(cr.query(uri, proj_music,
                        select.toString(), null,
                        PreferencesUtil.getInstance(context).getAlbumSongSortOrder()));
            //从文件夹
            case START_FROM_FOLDER:
                ArrayList<MusicInfo> list1 = new ArrayList<>();
                ArrayList<MusicInfo> list = getMusicListCursor(cr.query(Media.EXTERNAL_CONTENT_URI, proj_music,
                        select.toString(), null,
                        null));
                for (MusicInfo music : list) {
                    if (music.data.substring(0, music.data.lastIndexOf(File.separator)).equals(id)) {
                        list1.add(music);
                    }
                }
                return list1;
            default:
                return null;
        }

    }


    public static ArrayList<MusicInfo> getMusicLists(Context context, long[] id) {
        final StringBuilder selection = new StringBuilder();
        selection.append(Media._ID + " IN (");
        for (int i = 0; i < id.length; i++) {
            selection.append(id[i]);
            if (i < id.length - 1) {
                selection.append(",");
            }
        }
        selection.append(")");

        //sqlite 不支持decode

//        final StringBuilder order = new StringBuilder();
//        order.append("DECODE(" +MediaStore.Audio.Media._ID +",");
//        for (int i = 0; i < id.length; i++) {
//            order.append(id[i]);
//            order.append(",");
//            order.append(i);
//            if (i < id.length - 1) {
//                order.append(",");
//            }
//        }
//        order.append(")");

        Cursor cursor = (context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, proj_music,
                selection.toString(),
                null, null));
        if (cursor == null) {
            return null;
        }
        ArrayList<MusicInfo> musicList = new ArrayList<>();
        musicList.ensureCapacity(id.length);
        for (int i = 0; i < id.length; i++) {
            musicList.add(null);
        }

        while (cursor.moveToNext()) {
            MusicInfo music = new MusicInfo();
            music.songId = cursor.getInt(cursor
                    .getColumnIndex(Media._ID));
            music.albumId = cursor.getInt(cursor
                    .getColumnIndex(Media.ALBUM_ID));
            music.albumName = cursor.getString(cursor
                    .getColumnIndex(Albums.ALBUM));
            music.albumData = getAlbumArtUri(music.albumId) + "";
            music.musicName = cursor.getString(cursor
                    .getColumnIndex(Media.TITLE));
            music.artist = cursor.getString(cursor
                    .getColumnIndex(Media.ARTIST));
            music.artistId = cursor.getLong(cursor.getColumnIndex(Media.ARTIST_ID));
            music.islocal = true;
            for (int i = 0; i < id.length; i++) {
                if (id[i] == music.songId) {
                    musicList.set(i, music);
                }
            }
        }
        cursor.close();
        return musicList;
    }


    public static ArrayList<MusicInfo> getMusicListCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }

        ArrayList<MusicInfo> musicList = new ArrayList<>();
        while (cursor.moveToNext()) {
            MusicInfo music = new MusicInfo();
            music.songId = cursor.getInt(cursor
                    .getColumnIndex(Media._ID));
            music.albumId = cursor.getInt(cursor
                    .getColumnIndex(Media.ALBUM_ID));
            music.albumName = cursor.getString(cursor
                    .getColumnIndex(Albums.ALBUM));
            music.albumData = getAlbumArtUri(music.albumId) + "";
            music.duration = cursor.getInt(cursor
                    .getColumnIndex(Media.DURATION));
            music.musicName = cursor.getString(cursor
                    .getColumnIndex(Media.TITLE));
            music.artist = cursor.getString(cursor
                    .getColumnIndex(Media.ARTIST));
            music.artistId = cursor.getLong(cursor.getColumnIndex(Media.ARTIST_ID));
            String filePath = cursor.getString(cursor
                    .getColumnIndex(Media.DATA));
            music.data = filePath;
            music.folder = filePath.substring(0, filePath.lastIndexOf(File.separator));
            music.size = cursor.getInt(cursor
                    .getColumnIndex(Media.SIZE));
            music.islocal = true;
            music.sort = Pinyin.toPinyin(music.musicName.charAt(0)).substring(0, 1).toUpperCase();
            musicList.add(music);
        }
        cursor.close();
        return musicList;
    }

    public static List<AlbumInfo> getAlbumList(Cursor cursor) {
        List<AlbumInfo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            AlbumInfo info = new AlbumInfo();
            info.album_name = cursor.getString(cursor
                    .getColumnIndex(Albums.ALBUM));
            info.album_id = cursor.getInt(cursor.getColumnIndex(Albums._ID));
            info.number_of_songs = cursor.getInt(cursor
                    .getColumnIndex(Albums.NUMBER_OF_SONGS));
            info.album_art = getAlbumArtUri(info.album_id) + "";
            info.album_artist = cursor.getString(cursor.getColumnIndex(Albums.ARTIST));
            info.album_sort = Pinyin.toPinyin(info.album_name.charAt(0)).substring(0, 1).toUpperCase();
            list.add(info);
        }
        cursor.close();
        return list;
    }

    public static List<ArtistInfo> getArtistList(Cursor cursor) {
        List<ArtistInfo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            ArtistInfo info = new ArtistInfo();
            info.artist_name = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Artists.ARTIST));
            info.number_of_tracks = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS));
            info.artist_id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Artists._ID));
            info.artist_sort = Pinyin.toPinyin(info.artist_name.charAt(0)).substring(0, 1).toUpperCase();
            list.add(info);
        }
        cursor.close();
        return list;
    }

    public static List<FolderInfo> getFolderList(Cursor cursor) {
        List<FolderInfo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            FolderInfo info = new FolderInfo();
            String filePath = cursor.getString(cursor
                    .getColumnIndex(FileColumns.DATA));
            info.folder_path = filePath.substring(0, filePath.lastIndexOf(File.separator));
            info.folder_name = info.folder_path.substring(info.folder_path
                    .lastIndexOf(File.separator) + 1);
            info.folder_sort = Pinyin.toPinyin(info.folder_name.charAt(0)).substring(0, 1).toUpperCase();
            list.add(info);
        }
        cursor.close();
        return list;
    }

    public static String makeTimeString(long milliSecs) {
        StringBuffer sb = new StringBuffer();
        long m = milliSecs / (60 * 1000);
        sb.append(m < 10 ? "0" + m : m);
        sb.append(":");
        long s = (milliSecs % (60 * 1000)) / 1000;
        sb.append(s < 10 ? "0" + s : s);
        return sb.toString();
    }


    public static Uri getAlbumArtUri(long albumId) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }

    public static Uri getAlbumUri(Context context, long musicId) {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(Media.EXTERNAL_CONTENT_URI, proj_music, "_id =" + musicId, null, null);
        long id = -3;
        if (cursor == null) {
            return null;
        }
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(Media.ALBUM_ID));
        }

        cursor.close();
        return getAlbumArtUri(id);
    }

    public static String getAlbumdata(Context context, long musicid) {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(Media.EXTERNAL_CONTENT_URI, proj_music, "_id = " + musicid, null, null);
        if (cursor == null) {
            return null;
        }
        long albumId = -1;
        if (cursor.moveToNext()) {
            albumId = cursor.getLong(cursor.getColumnIndex(Media.ALBUM_ID));
        }

        if (albumId != -1) {
            cursor = cr.query(Albums.EXTERNAL_CONTENT_URI, proj_album, Albums._ID + " = " + albumId, null, null);
        }
        if (cursor == null) {
            return null;
        }
        String data = "";
        if (cursor.moveToNext()) {
            data = cursor.getString(cursor.getColumnIndex(Albums.ALBUM_ART));
        }
        cursor.close();

        return data;
    }


    public static ArtistInfo getArtistinfo(Context context, long id) {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, proj_artist, "_id =" + id, null, null);
        if (cursor == null) {
            return null;
        }
        ArtistInfo artistInfo = new ArtistInfo();
        while (cursor.moveToNext()) {
            artistInfo.artist_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
            artistInfo.number_of_tracks = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS));
        }
        cursor.close();
        return artistInfo;
    }


    public static AlbumInfo getAlbumInfo(Context context, long albumId) {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(Albums.EXTERNAL_CONTENT_URI, proj_album, "_id =" + albumId, null, null);
        if (cursor == null) {
            return null;
        }
        AlbumInfo albumInfo = new AlbumInfo();
        while (cursor.moveToNext()) {
            albumInfo.album_name = cursor.getString(cursor.getColumnIndex(Albums.ALBUM));
            albumInfo.album_art = cursor.getString(cursor.getColumnIndex(Albums.ALBUM_ART));
        }
        cursor.close();
        return albumInfo;

    }


    public static MusicInfo getMusicInfo(Context context, long id) {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(Media.EXTERNAL_CONTENT_URI, proj_music, "_id = " + id, null, null);
        if (cursor == null) {
            return null;
        }
        MusicInfo music = new MusicInfo();
        while (cursor.moveToNext()) {
            music.songId = cursor.getInt(cursor
                    .getColumnIndex(Media._ID));
            music.albumId = cursor.getInt(cursor
                    .getColumnIndex(Media.ALBUM_ID));
            music.albumName = cursor.getString(cursor
                    .getColumnIndex(Albums.ALBUM));
            music.albumData = getAlbumArtUri(music.albumId) + "";
            music.duration = cursor.getInt(cursor
                    .getColumnIndex(Media.DURATION));
            music.musicName = cursor.getString(cursor
                    .getColumnIndex(Media.TITLE));
            music.size = cursor.getInt(cursor.getColumnIndex(Media.SIZE));
            music.artist = cursor.getString(cursor
                    .getColumnIndex(Media.ARTIST));
            music.artistId = cursor.getLong(cursor.getColumnIndex(Media.ARTIST_ID));
            String filePath = cursor.getString(cursor
                    .getColumnIndex(Media.DATA));
            music.data = filePath;
            music.folder = filePath.substring(0,
                    filePath.lastIndexOf(File.separator));
            music.sort = Pinyin.toPinyin(music.musicName.charAt(0)).substring(0, 1).toUpperCase();
        }
        cursor.close();
        return music;
    }
}

