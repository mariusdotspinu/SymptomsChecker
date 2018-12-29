package mspinu.symptomschecker.ui.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;

import mspinu.symptomschecker.R;

public abstract class UiUtils {

    public static DividerItemDecoration getItemDecorator(Context context) {
        DividerItemDecoration itemDecorator = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider));

        return itemDecorator;
    }
}
