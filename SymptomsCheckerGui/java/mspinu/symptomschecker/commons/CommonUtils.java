package mspinu.symptomschecker.commons;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import mspinu.symptomschecker.ui.views.DialogView;
import mspinu.symptomschecker.R;

import static mspinu.symptomschecker.commons.Constants.ERROR_TITLE;
import static mspinu.symptomschecker.commons.Constants.POSITIVE_BUTTON_TEXT;
import static mspinu.symptomschecker.commons.Constants.PROGRESS_BAR_TITLE;


/**
 * Created by Marius on 12/2/2017.
 */

public abstract class CommonUtils {

    public static AlertDialog initProgressBarDialog(ProgressBar progressBar) {
        return getProgressBarDialog(progressBar);
    }

    private static AlertDialog getProgressBarDialog(ProgressBar progressBar) {
        AlertDialog dialog = buildDialog(progressBar.getContext(), null, null, null);
        progressBar.setVisibility(View.VISIBLE);
        dialog.setView(progressBar);
        dialog.setTitle(PROGRESS_BAR_TITLE);

        return dialog;
    }

    public static void dismissProgressBarDialog(AlertDialog progressBarDialog) {
        progressBarDialog.dismiss();
    }

    public static void showErrorDialog(String message, Context context, boolean isSplashActivity) {
        AlertDialog dialog = buildDialog(context, message, ERROR_TITLE,
                new DialogView(context, isSplashActivity));
        dialog.show();
    }

    private static AlertDialog buildDialog(Context context, String message, String title,
                                           DialogView dialogView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context,
                R.style.Theme_AppCompat_DayNight_Dialog);

        builder.setMessage(message)
                .setTitle(title);

        if (title != null && title.equals(ERROR_TITLE)) {
            builder.setPositiveButton(POSITIVE_BUTTON_TEXT, dialogView);
        }
        return builder.create();
    }


    public static String getResultFromStream(InputStream inputStream) {
        InputStream in = new BufferedInputStream(inputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        return getResultFromReader(reader);
    }


    private static String getResultFromReader(BufferedReader reader) {
        StringBuilder result = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
