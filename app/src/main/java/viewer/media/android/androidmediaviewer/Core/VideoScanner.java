package viewer.media.android.androidmediaviewer.Core;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.File;
import java.util.ArrayList;

import viewer.media.android.androidmediaviewer.Utils.FinishedLoadingPlaylistListener;

/**
 * Created by Amr Mahfouz on 2/12/2016.
 */
public class VideoScanner extends AsyncTask<Void,Void,Void>  {

    private Context context;
    private ProgressBar progressBar;
    private PlaylistLoader playlistLoader;
    private ArrayList<String> videoNames;


    private FinishedLoadingPlaylistListener onFinishedLoadingPlaylistListener;
    public VideoScanner(Context context,ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
        playlistLoader = new PlaylistLoader();
        videoNames = playlistLoader.getVideosNames();
        execute(new Void[]{});
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        scanVideos();
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        notifyListener();
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

    public void setOnFinishedLoadingPlaylistListener(FinishedLoadingPlaylistListener onFinishedLoadingPlaylistListener){
        this.onFinishedLoadingPlaylistListener = onFinishedLoadingPlaylistListener;
    }

    private void notifyListener(){
        if(onFinishedLoadingPlaylistListener != null){
            onFinishedLoadingPlaylistListener.onFinished();
        }
    }

}
