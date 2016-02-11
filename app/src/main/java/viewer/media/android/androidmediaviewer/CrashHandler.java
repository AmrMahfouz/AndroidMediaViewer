package viewer.media.android.androidmediaviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
/**
 * Created by Mohamed on 2/11/2016.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Context context;
    private Class<?> crashedActivityClass;

    public CrashHandler(Context context, Class<?> crashedActivityClass){
        this.context=context;
        this.crashedActivityClass=crashedActivityClass;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Intent intent=new Intent(context,crashedActivityClass);
        context.startActivity(intent);
        //kill the current process
        Process.killProcess(Process.myPid());
    }
}
