package viewer.media.android.androidmediaviewer.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Mohamed on 2/12/2016.
 */
public class DataUtils {
    /**
     * Check if the input string is a stream url or not.
     *
     * @param url the input string
     * @return true if it is a url , false otherwise.
     */
    public static boolean isUrl(String url) {
        return (url.contains(":") && url.contains("//"));
    }

    /**
     * Check if there is access to internet or not.
     * @return
     * true if there is a connection available, false otherwise.
     */
    public static boolean connectedToNetwork(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}
