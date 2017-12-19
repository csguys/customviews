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

    /**
     *
     * @param context
     */
    public MailCustomCompositeView(final Context context) {
        this(context, null);
    }

    /**
     *
     * @param context
     * @param attrs
     */
    public MailCustomCompositeView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
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

    /**
     * Calculate width of view with left right margin added
     * @param child view
     * @return total width
     */
    private int getWidthWithMargins(final View child) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        return child.getWidth() + lp.leftMargin + lp.rightMargin;
    }

    /**
     * Calculate height of view with left right margin added
     * @param child view
     * @return total width
     */
    private int getHeightWithMargins(final View child) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        return child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
    }

    /**
     * Calculate measured width of view with left right margin added
     * @param child view
     * @return total width
     */
    private int getMeasuredWidthWithMargins(final View child) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        return child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
    }

    /**
     * Calculate measured height of view with left right margin added
     * @param child view
     * @return total width
     */
    private int getMeasuredHeightWithMargins(final View child) {
        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        return child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
    }

    /**
     * Disable scrolling for this view
     * @return
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /**
     * Perform measure for each view in ViewGroup
     * @param widthMeasureSpec widthSpec
     * @param heightMeasureSpec heightSpec
     */
    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {

        /* **** View requirement*******
        /*
        |***************************************|
        | ********   <****Subject****>  ******* |
        | *  Tag *   <****Title******>  * Date* |(fixed)
        | *      *   <****content****>  ***_*** |
        | ********     --WrapContent--     |    |
        |  (fixed)                      ******* |
        |                       (fixed) * star* |
        |                               ******* |
        *****************************************
        */

        // param to trace width and height  used
        int widthUsed = getPaddingLeft() + getPaddingLeft();
        int heightUsed = getPaddingTop() + getPaddingBottom();

        // final dimensions
        int width = 0;
        int height = 0;

        /*
         First measure views for which dimensions are fixed because after measuring all of them
         we have remaining space for view for which dimensions are flexible (wrap_content)

              First we measure dimension for NameTag in left who's dimensions are fixed
         */
        measureChildWithMargins(tvNameTag, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);

        /*
         Update the dimension used after measuring the view
         */
        widthUsed += tvNameTag.getMeasuredWidth();
        width += tvNameTag.getMeasuredWidth();

        /*
         update height which is longest
         */
        height = Math.max(height, tvNameTag.getMeasuredHeight());
        Log.i(TAG, " 1 - width :" +width+" height :"+height);

        /*
         measure next view with fixed dimension date on top of right most view
         */
        measureChildWithMargins(tvDate, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);

         /*
         Update the dimension used after measuring the view
         */
        widthUsed += tvDate.getMeasuredWidth();
        width += tvDate.getMeasuredWidth();
        height += Math.max(height, tvDate.getMeasuredHeight());
        Log.i(TAG, " 2 - width :" +width+" height :"+height);

        /*
         measure next fixed dimension view
         */
        measureChildWithMargins(ivStar, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);

        /*
         check weather
         */
        int sum1 = tvNameTag.getMeasuredWidth() + tvDate.getMeasuredWidth() + ivStar.getMeasuredWidth();
        if (sum1 > widthUsed) {
            widthUsed += ivStar.getMeasuredWidth();
        }

        /*
          As start view and Date views are in vertical manner check weather sum of height of both
          view is greater then previous and update the longest one
         */
        height = Math.max(height, tvDate.getMeasuredHeight() + ivStar.getMeasuredHeight());
        Log.i(TAG, " 3 - width :" +width+" height :"+height);

        /*
         Now till this point we finish measuring all view with fixed dimensions and left with flexible view
         Here we create New MeasureSpec for remaining flexible views
         */
        int widthMeasureSpecForText = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) - widthUsed
                , MeasureSpec.getMode(widthMeasureSpec));
        int heightMeasureSpecForText = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) - heightUsed
                , MeasureSpec.getMode(heightMeasureSpec));

        /*
         Measure subject text as its above all 3 text view its height used is 0 and width also 0
         because we are measuring it under new MeasureSpec
         */
        measureChildWithMargins(tvSubject, widthMeasureSpecForText, 0, heightMeasureSpecForText, 0);

        /*
         Now width used is still 0 because these views are vertically aligned
         but height get increased by amount of height of above view
         */
        measureChildWithMargins(tvHead, widthMeasureSpecForText, 0, heightMeasureSpecForText, tvSubject.getMeasuredHeight());
        measureChildWithMargins(tvContent, widthMeasureSpecForText, 0, heightMeasureSpecForText
                , tvSubject.getMeasuredHeight() + tvHead.getMeasuredHeight());

        /*
          add the longest textView length with previous one
         */
        width += Math.max(tvSubject.getMeasuredHeight(), Math.max(tvHead.getMeasuredHeight(), tvContent.getMeasuredHeight()));

        /*
         Update longest height
         */
        height = Math.max(height, tvSubject.getMeasuredHeight() + tvHead.getMeasuredHeight() + tvContent.getMeasuredHeight());
        Log.i(TAG, " 4 - width :" +width+" height :"+height);

        /*
         setMeasuredDimension need to be called with calculate dimensions
         */
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(final boolean changed, final int l, final int t, final int r, final int b) {
       int paddingLeft = getPaddingLeft();
       int paddingTop = getPaddingTop();
       int topUsed = paddingTop;
       int leftUsed;
       /*
        align TagView to left most position after adding padding
        */
       layoutView(tvNameTag, paddingLeft, topUsed, tvNameTag.getMeasuredWidth(), tvNameTag.getMeasuredHeight());

       /*
        calculate top right position for date view left point
        */
       int dateLeft = r - getPaddingEnd() - tvDate.getMeasuredWidth();

       /*
        align date to top right position
        */
       layoutView(tvDate, dateLeft, topUsed, tvDate.getMeasuredWidth(), tvDate.getMeasuredHeight());

       /*
        align star image below date view with same left but incremented top
        */
       layoutView(ivStar, dateLeft, topUsed + getHeightWithMargins(tvDate), ivStar.getMeasuredWidth(), ivStar.getMeasuredHeight());
       int textWidth = r - l - getWidthWithMargins(tvNameTag) - paddingLeft - getWidthWithMargins(tvDate);
       leftUsed = getWidthWithMargins(tvNameTag) + paddingLeft;
       layoutView(tvSubject, leftUsed, topUsed, textWidth, tvSubject.getMeasuredHeight());
       topUsed += getHeightWithMargins(tvSubject);
       layoutView(tvHead, leftUsed, topUsed, textWidth, tvHead.getMeasuredHeight());
       topUsed += getHeightWithMargins(tvHead);
       layoutView(tvContent, leftUsed, topUsed, textWidth, tvContent.getMeasuredHeight());
    }

    /**
     * layout view in parent view
     * @param view view to layout
     * @param left left point
     * @param top top point
     * @param width view width
     * @param height view height
     */
    private void layoutView(final View view, final int left, final int top, final int width, final int height) {
        MarginLayoutParams margins = (MarginLayoutParams) view.getLayoutParams();
        final int leftWithMargins = left + margins.leftMargin;
        final int topWithMargins = top + margins.topMargin;
        Log.i("test", "margins :["+margins.leftMargin+"-"+margins.rightMargin+"]");
        view.layout(leftWithMargins, topWithMargins,
                leftWithMargins + width, topWithMargins + height);
    }

    /*
      override measureChildWithMargins because default implementation add padding to all child view
     */
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
    public LayoutParams generateLayoutParams(final AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }
}
