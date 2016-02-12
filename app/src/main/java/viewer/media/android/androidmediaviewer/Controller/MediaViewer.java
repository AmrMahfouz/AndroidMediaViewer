package viewer.media.android.androidmediaviewer.Controller;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.VideoView;
import java.util.ArrayList;

import viewer.media.android.androidmediaviewer.Utils.DataUtils;

/**
 * Created by Mohamed on 2/12/2016.
 */
public class MediaViewer {

    private ArrayList<String>videos;
    private int currentVideoIndex;
    private VideoView videoView;
    private Context context;

    public MediaViewer(Context context, VideoView videoView, ArrayList<String> videos){
        this.videoView = videoView;
        this.videos = videos;
        this.context = context;
        currentVideoIndex = -1;
    }

    /**
     * Initiate the media player and start playing.
     */
    public void initPlayer(){
        //check for empty list not yet
        videoView.setVideoPath(getNextVideo());
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.setVideoPath(getNextVideo());
                videoView.start();

            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
            }
        });
        videoView.setOnErrorListener(new android.media.MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(android.media.MediaPlayer mp, int what, int extra) {
                //skip the current video
                videoView.setVideoPath(getNextVideo());
                videoView.start();
                return true;
            }
        });
    }

    /**
     * Get the next video to play.
     */
    private String getNextVideo(){
        currentVideoIndex = (currentVideoIndex + 1) % videos.size();
        String nextVideoPath = videos.get(currentVideoIndex);
        if(DataUtils.isUrl(nextVideoPath) && !DataUtils.connectedToNetwork(context)){
            return getNextVideo();
        }
        return nextVideoPath;
    }
}
