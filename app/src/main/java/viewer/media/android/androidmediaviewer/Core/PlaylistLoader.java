package viewer.media.android.androidmediaviewer.Core;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Mohamed on 2/12/2016.
 */
public class PlaylistLoader {

    private final String PLAYLIST_FILE_NAME = "playlist.txt";
    private ArrayList<String> videosNames;

    public PlaylistLoader() {
        videosNames = new ArrayList<>();
        loadPlayList();
    }

    /**
     * Load the playlist file if found into list of its content (videos/Streams) Names.
     */
    private void loadPlayList() {
        String defaultStoragePath = System.getenv("EXTERNAL_STORAGE");
        if (defaultStoragePath != null || !defaultStoragePath.equals("")) {
            File playlistFile = new File(defaultStoragePath + "/" + PLAYLIST_FILE_NAME);
            Scanner sc = null;
            try {
                sc = new Scanner(playlistFile);
                while (sc.hasNextLine()) {
                    String videoName = sc.nextLine().trim();
                    if (!videoName.equals("")) {
                        videosNames.add(videoName);
                    }
                }
            } catch (Exception e) {
                Log.d("error", e.getMessage());
            } finally {
                sc.close();
            }
        }
    }
}