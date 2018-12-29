package mspinu.symptomschecker.ui.views;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

/**
 * Created by Marius on 3/10/2018.
 */

public class FadingTextView extends android.support.v7.widget.AppCompatTextView {

    private TextView blobText;
    public String text;
    public int position = 0;
    private Animation textDisplayAnimationObject;
    private Animation fadeOutAnimationObject;

    int fadeEffectDuration;
    int delayDuration;
    int displayFor;

    public FadingTextView(Context context){
        super(context);
    }

    public FadingTextView(Context context, TextView textV, String text)
    {
        this(context, textV,3000,1000,5000, text);
    }
    public FadingTextView(Context context, TextView textView,
                                  int fadeLength,
                                  int delayLength,
                                  int displayLength,
                                  String text)
    {
        super(context);
        blobText = textView;
        this.text = text;
        this.fadeEffectDuration = fadeLength;
        this.delayDuration = delayLength;
        this.displayFor = displayLength;
        InitAnimation();
    }

    public void startAnimation()
    {
        blobText.startAnimation(fadeOutAnimationObject);
    }

    private void InitAnimation()
    {
        Animation fadeInAnimationObject = new AlphaAnimation(0f, 1f);
        fadeInAnimationObject.setDuration(fadeEffectDuration);

        Animation delayBetweenAnimations = new AlphaAnimation(0f, 0f);
        delayBetweenAnimations.setDuration(delayDuration);

        textDisplayAnimationObject = new AlphaAnimation(1f, 1f);
        textDisplayAnimationObject.setDuration(displayFor);

        fadeOutAnimationObject = new AlphaAnimation(1f, 0f);
        fadeOutAnimationObject.setDuration(fadeEffectDuration);

        fadeInAnimationObject.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                position++;
                if(position>=text.length())
                {
                    position = 0;
                }
                blobText.setText(text);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                blobText.startAnimation(textDisplayAnimationObject);
            }
        });
    }
}
