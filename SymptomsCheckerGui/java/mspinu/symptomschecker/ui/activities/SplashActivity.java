package mspinu.symptomschecker.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import mspinu.symptomschecker.ui.views.FadingTextView;
import mspinu.symptomschecker.R;

import static mspinu.symptomschecker.commons.CommonUtils.*;
import static mspinu.symptomschecker.commons.ConnectionUtils.isConnectedToInternet;
import static mspinu.symptomschecker.commons.Constants.*;

/**
 * Created by Marius on 3/4/2018.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_background);
        TextView splashText = findViewById(R.id.text);
        splashText.setText(WELCOME_TEXT);
        FadingTextView fadingTextView = new FadingTextView(this, splashText, WELCOME_TEXT);
        fadingTextView.startAnimation();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isConnectedToInternet(this)) {
            startDelay(this);
        } else {
            showErrorDialog(INTERNET_CONNECTION_ERROR_MESSAGE, this, true);
        }
    }

    private void initMainActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void startDelay(final Context context) {
        Thread delayThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(DELAY_TIME);
                    }
                } catch (InterruptedException ignored) {
                }
                initMainActivity(context);
            }
        };
        delayThread.start();
    }
}

