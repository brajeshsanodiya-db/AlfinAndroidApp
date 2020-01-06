package com.alfinapp.ui.views.fontTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class LatoMediumTextView extends AppCompatTextView {

    public LatoMediumTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LatoMediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LatoMediumTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Medium.ttf");
        setTypeface(tf);

    }
}
