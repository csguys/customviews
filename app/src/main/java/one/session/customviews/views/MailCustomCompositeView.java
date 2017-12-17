package one.session.customviews.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import one.session.customviews.R;

/**
 * ********** Pawan ********
 * **********(16/12/17)*******
 */

public class MailCustomCompositeView extends ViewGroup {

    private static final String TAG = MailCustomCompositeView.class.getSimpleName();
    private TextView tvNameTag, tvSubject, tvHead, tvContent, tvDate;
    private ImageView ivStar;

    public MailCustomCompositeView(final Context context) {
        this(context, null);
    }

    public MailCustomCompositeView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MailCustomCompositeView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_mail_custom_compound, this, true);
        tvNameTag = view.findViewById(R.id.tvNameTagCustom);
        tvSubject = view.findViewById(R.id.tvSubjectCustom);
        tvHead = view.findViewById(R.id.tvHeadCustom);
        tvContent = view.findViewById(R.id.tvContentCustom);
        tvDate = view.findViewById(R.id.mail_dateCustom);
        ivStar = view.findViewById(R.id.ivStarredCustom);
    }

    private int getWidthWithMargins(View child) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        return child.getWidth() + lp.leftMargin + lp.rightMargin;
    }

    private int getHeightWithMargins(View child) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        return child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
    }

    private int getMeasuredWidthWithMargins(View child) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        return child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
    }

    private int getMeasuredHeightWithMargins(View child) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        return child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        int widthUsed = getPaddingLeft() + getPaddingLeft();
        int heightUsed = getPaddingTop() + getPaddingBottom();
        int width = 0;
        int height = 0;
        measureChildWithMargins(tvNameTag, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
        widthUsed += tvNameTag.getMeasuredWidth();
        width += tvNameTag.getMeasuredWidth();
        height = Math.max(height, tvNameTag.getMeasuredHeight());
        Log.i(TAG, " 1 - width :" +width+" height :"+height);
        measureChildWithMargins(tvDate, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
        widthUsed += tvDate.getMeasuredWidth();
        width += tvDate.getMeasuredWidth();
        height += Math.max(height, tvDate.getMeasuredHeight());
        Log.i(TAG, " 2 - width :" +width+" height :"+height);
        measureChildWithMargins(ivStar, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
        int sum1 = tvNameTag.getMeasuredWidth() + tvDate.getMeasuredWidth() + ivStar.getMeasuredWidth();
        if (sum1 > widthUsed) {
            widthUsed += ivStar.getMeasuredWidth();
        }
        height = Math.max(height, tvDate.getMeasuredHeight() + ivStar.getMeasuredHeight());
        Log.i(TAG, " 3 - width :" +width+" height :"+height);
        int widthMeasureSpecForText = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) - widthUsed
                , MeasureSpec.getMode(widthMeasureSpec));
        int heightMeasureSpecForText = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) - heightUsed
                , MeasureSpec.getMode(heightMeasureSpec));

        measureChildWithMargins(tvSubject, widthMeasureSpecForText, 0, heightMeasureSpecForText, 0);
        measureChildWithMargins(tvHead, widthMeasureSpecForText, 0, heightMeasureSpecForText, tvSubject.getMeasuredHeight());
        measureChildWithMargins(tvContent, widthMeasureSpecForText, 0, heightMeasureSpecForText
                , tvSubject.getMeasuredHeight() + tvHead.getMeasuredHeight());
        width += Math.max(tvSubject.getMeasuredHeight(), Math.max(tvHead.getMeasuredHeight(), tvContent.getMeasuredHeight()));
        height = Math.max(height, tvSubject.getMeasuredHeight() + tvHead.getMeasuredHeight() + tvContent.getMeasuredHeight());
        Log.i(TAG, " 4 - width :" +width+" height :"+height);
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b) {
       int paddingLeft = getPaddingLeft();
       int paddingTop =getPaddingTop();
       int topUsed = paddingTop;
       int leftUsed;
       layoutView(tvNameTag, paddingLeft, topUsed, tvNameTag.getMeasuredWidth(), tvNameTag.getMeasuredHeight());
       int dateLeft = r - getPaddingEnd() - tvDate.getMeasuredWidth();
       layoutView(tvDate, dateLeft, topUsed, tvDate.getMeasuredWidth(), tvDate.getMeasuredHeight());
       layoutView(ivStar, dateLeft, topUsed + getHeightWithMargins(tvDate), ivStar.getMeasuredWidth(), ivStar.getMeasuredHeight());
       int textWidth = r - l - getWidthWithMargins(tvNameTag) - paddingLeft - getWidthWithMargins(tvDate);
       leftUsed = getWidthWithMargins(tvNameTag) + paddingLeft;
       layoutView(tvSubject, leftUsed, topUsed, textWidth, tvSubject.getMeasuredHeight());
       topUsed += getHeightWithMargins(tvSubject);
       layoutView(tvHead, leftUsed, topUsed, textWidth, tvHead.getMeasuredHeight());
       topUsed += getHeightWithMargins(tvHead);
       layoutView(tvContent, leftUsed, topUsed, textWidth, tvContent.getMeasuredHeight());
    }

    private void layoutView(View view, int left, int top, int width, int height) {
        MarginLayoutParams margins = (MarginLayoutParams) view.getLayoutParams();
        final int leftWithMargins = left + margins.leftMargin;
        final int topWithMargins = top + margins.topMargin;
        Log.i("test", "margins :["+margins.leftMargin+"-"+margins.rightMargin+"]");
        view.layout(leftWithMargins, topWithMargins,
                leftWithMargins + width, topWithMargins + height);
    }

    @Override
    protected void measureChildWithMargins(final View child, final int parentWidthMeasureSpec
            , final int widthUsed, final int parentHeightMeasureSpec
            , final int heightUsed) {
        MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
        Log.i(TAG, "margins ["+mlp.getMarginEnd()+"-"+mlp.getMarginStart()+"]");
        int widthMeasure = getChildMeasureSpec(parentWidthMeasureSpec
                , widthUsed + mlp.getMarginEnd() + mlp.getMarginStart()
                , mlp.width);
        int heightMeasure = getChildMeasureSpec(parentHeightMeasureSpec, heightUsed + mlp.topMargin + mlp.bottomMargin
                , mlp.height);
        child.measure(widthMeasure, heightMeasure);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }
}
