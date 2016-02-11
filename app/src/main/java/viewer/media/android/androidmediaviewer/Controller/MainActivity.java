package viewer.media.android.androidmediaviewer.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import viewer.media.android.androidmediaviewer.Core.CrashHandler;
import viewer.media.android.androidmediaviewer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this, MainActivity.class));
    }

}
