package viewer.media.android.androidmediaviewer.Core;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        String defaultStoragePath = System.getenv("EXTERNAL_STORAGE");
        String[] path = defaultStoragePath.split("/");
        if (path != null && path.length >= 2) {
            String storageRoot = defaultStoragePath.split("/")[1];
            File folder_file = new File("/" + storageRoot + "/");
            loadVideoFiles(folder_file);
        }
    }

    private void loadVideoFiles(File folder) {
        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                loadVideoFiles(file);
            } else if (file.isFile()){
                String fileName = file.getName();
                if (videoNames.contains(fileName)) {
                    String path = file.getAbsolutePath();
                    int videoIndex = videoNames.indexOf(fileName);
                    videoNames.remove(videoIndex);
                    videoNames.add(videoIndex, path);
                }
            }
        }
    }

    public ArrayList<String> getPlayList() {
        return videoNames;
    }
}
