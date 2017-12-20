package one.session.customviews.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * +++++++++++++++++++++++++++++++++
 * ++++++++++Click labs ++++++++++++
 * +++++++++++++++++++++++++++++++++
 */

public class CircleView extends View {

    private int radius = 20;
    private Paint paint;
    int i=0;
    static class State extends View.BaseSavedState{

        public State(final Parcel source) {
            super(source);
        }

        public State(final Parcelable superState) {
            super(superState);
        }
    }

    public CircleView(final Context context) {
        super(context);
        init();
    }

    public CircleView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setRadius(final int radius) {
        this.radius = radius;
        invalidate();
    }

    public void setCircleColor(final int color) {
        paint.setColor(color);
        invalidate();
    }

    public int getRadius() {
        return radius;
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        int x = getWidth()/2;
        int y = getHeight()/2;
        canvas.drawCircle(x, y, getHeight() / 2 - (10+i*2), paint);
    }


}
