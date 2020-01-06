package com.alfinapp.ui.views.fontTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class LatoHeavyTextView extends AppCompatTextView {

    public LatoHeavyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LatoHeavyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LatoHeavyTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Heavy.ttf");
        setTypeface(tf);

    }
}
