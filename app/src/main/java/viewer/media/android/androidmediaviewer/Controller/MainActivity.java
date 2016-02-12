package viewer.media.android.androidmediaviewer.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.util.ArrayList;

import viewer.media.android.androidmediaviewer.Core.CrashHandler;
import viewer.media.android.androidmediaviewer.Core.VideoScanner;
import viewer.media.android.androidmediaviewer.R;
import viewer.media.android.androidmediaviewer.Utils.FinishedLoadingPlaylistListener;
import viewer.media.android.androidmediaviewer.Utils.WakeLocker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this, MainActivity.class));

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final VideoView videoView = (VideoView) findViewById(R.id.videoPlayer);
        videoView.requestFocus();

        final VideoScanner videoScanner = new VideoScanner(getApplicationContext(),progressBar);
        videoScanner.setOnFinishedLoadingPlaylistListener(new FinishedLoadingPlaylistListener() {
            @Override
            public void onFinished() {
                ArrayList<String> playList = videoScanner.getPlayList();
                MediaViewer mediaViewer = new MediaViewer(getApplicationContext(), videoView, playList);
                mediaViewer.initPlayer();
            }
        });

        WakeLocker.acquire(getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        WakeLocker.release();
        super.onDestroy();
    }
}
