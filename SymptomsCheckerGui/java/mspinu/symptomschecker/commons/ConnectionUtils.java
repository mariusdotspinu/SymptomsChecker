package mspinu.symptomschecker.commons;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static mspinu.symptomschecker.commons.Constants.CONNECTION_TIMEOUT;
import static mspinu.symptomschecker.commons.Constants.PREDICT_ENDPOINT;

/**
 * Created by Marius on 3/27/2018.
 */

public abstract class ConnectionUtils {

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info == null) {
                return false;
            } else {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return true;
    }

    public static HttpURLConnection getConnection(String address, String input) {
        try {
            URL url = new URL(address + PREDICT_ENDPOINT + input);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);

            return urlConnection;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
