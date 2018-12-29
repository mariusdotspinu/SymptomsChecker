package mspinu.symptomschecker.ui.views;

import android.content.Context;
import android.content.DialogInterface;

import mspinu.symptomschecker.ui.activities.SplashActivity;

import static mspinu.symptomschecker.commons.ConnectionUtils.isConnectedToInternet;

/**
 * Created by Marius on 3/3/2018.
 */

public class DialogView implements DialogInterface.OnClickListener {

    private boolean isSplashActivity;
    private Context context;

    public DialogView(Context context, boolean isSplashActivity) {
        this.context = context;
        this.isSplashActivity = isSplashActivity;

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(isSplashActivity) {
            SplashActivity splashActivity = ((SplashActivity) context);
            if (!isConnectedToInternet(context)) {
                splashActivity.finish();
            } else {
                splashActivity.onResume();
            }
        }
    }
}
