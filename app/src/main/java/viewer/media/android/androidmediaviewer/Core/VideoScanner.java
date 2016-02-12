package viewer.media.android.androidmediaviewer.Core;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;

/**
 * Created by Amr Mahfouz on 2/12/2016.
 */
public class VideoScanner {

    private Context context;

    private PlaylistLoader playlistLoader;
    private ArrayList<String> videoNames;

    public VideoScanner(Context context) {
        this.context = context;
        playlistLoader = new PlaylistLoader();
        videoNames = playlistLoader.getVideosNames();
        scanVideos();
    }

    private void scanVideos() {
        // scan internal storage
        String[] projection = { MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.DATA, MediaStore.Video.Media.SIZE };
        Cursor cursor = new CursorLoader(context, MediaStore.Video.Media.INTERNAL_CONTENT_URI, projection, null, null, null).loadInBackground();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
            if (videoNames.contains(name)) {
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                int videoIndex = videoNames.indexOf(name);
                videoNames.remove(videoIndex);
                videoNames.add(videoIndex, path);
            }
        }
        cursor.close();

        // scan external storage
        cursor = new CursorLoader(context, MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null).loadInBackground();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
            if (videoNames.contains(name)) {
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                int videoIndex = videoNames.indexOf(name);
                videoNames.remove(videoIndex);
                videoNames.add(videoIndex, path);
            }
        }
        cursor.close();
    }

    public ArrayList<String> getPlayList() {
        return videoNames;
    }
}
