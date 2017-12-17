package one.session.customviews.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import one.session.customviews.R;

/**
 * ********** Pawan ********
 * **********(13/12/17)*******
 */

public class ButtonX extends FrameLayout {

    private Button button;
    private ProgressBar progressBar;
    private int color;

    public ButtonX(final Context context) {
        this(context, null);
    }

    public ButtonX(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonX(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_button_x, this);
        TypedArray array = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.background});
        TypedValue colorType = new TypedValue();
        //array.getValue(array.getIndex(android.R.), colorType);
        if (colorType.type == TypedValue.TYPE_REFERENCE) {
            Log.v("alex","yes type is ref");
        }
        array.recycle();
       // int color = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android","background",R.color.colorAccent);
        button = findViewById(R.id.x_button);
        progressBar = findViewById(R.id.x_progress);
    }

}
