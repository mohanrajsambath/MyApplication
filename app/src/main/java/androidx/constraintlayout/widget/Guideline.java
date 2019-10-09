package androidx.constraintlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

class Guideline extends View {
    public Guideline(Context context) {
        this(context, null);
    }

    public Guideline(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Guideline(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
