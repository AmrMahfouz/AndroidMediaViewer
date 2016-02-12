package viewer.media.android.androidmediaviewer.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import viewer.media.android.androidmediaviewer.Core.CrashHandler;
import viewer.media.android.androidmediaviewer.Core.VideoScanner;
import viewer.media.android.androidmediaviewer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoScanner videoScanner = new VideoScanner(getApplicationContext());
        ArrayList<String> playList = videoScanner.getPlayList();
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this, MainActivity.class));
    }

}
