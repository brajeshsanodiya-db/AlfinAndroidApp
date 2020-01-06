package com.alfinapp.ui.views.fontTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class LatoMediumItalicTextView extends AppCompatTextView {

    public LatoMediumItalicTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LatoMediumItalicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LatoMediumItalicTextView(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-MediumItalic.ttf");
        setTypeface(tf);

    }
}
