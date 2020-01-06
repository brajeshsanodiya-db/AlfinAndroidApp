package com.alfinapp.ui.views.fontTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class LatoLightItalicTextView extends AppCompatTextView {

    public LatoLightItalicTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LatoLightItalicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LatoLightItalicTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-LightItalic.ttf");
        setTypeface(tf);

    }
}
