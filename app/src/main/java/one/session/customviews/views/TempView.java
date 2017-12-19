package one.session.customviews.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * ********** Pawan ********
 * **********(19/12/17)*******
 */

public class TempView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG  = TempView.class.getSimpleName();

    public TempView(final Context context) {
        super(context);
    }

    public TempView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public TempView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        String tag = (String) getTag();
        Log.i(TAG, "["+tag+"] : w-"+MeasureSpec.toString(widthMeasureSpec) +" : h-"+MeasureSpec.toString(heightMeasureSpec));
        //Log.i(TAG, "["+tag+"]"+MeasureSpec.toString(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(final boolean changed, final int left, final int top, final int right, final int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        String tag = (String) getTag();
        Log.i("onLayout ","["+tag+"]"+"onLayout called");
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        String tag = (String) getTag();
        Log.i("onDraw","["+tag+"]"+"on Draw called");
    }
}
