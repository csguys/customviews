package one.session.customviews.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import one.session.customviews.R;

/**
 * ********** Pawan ********
 * **********(16/12/17)*******
 */

public class MailCompositeView extends RelativeLayout {

    private TextView tvNameTag, tvSubject, tvHead, tvContent, tvDate;
    private ImageView ivStar;

    public MailCompositeView(final Context context) {
        this(context, null);
    }

    public MailCompositeView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MailCompositeView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // inflate the layout for this ViewGroup
        View view = inflate(context, R.layout.view_mail_composite, this);

        //------------- get Views from group----
        tvNameTag = view.findViewById(R.id.tvNameTag);
        tvSubject = view.findViewById(R.id.tvSubject);
        tvHead = view.findViewById(R.id.tvHead);
        tvContent  = view.findViewById(R.id.tvContent);
        tvDate = view.findViewById(R.id.mail_date);
        ivStar = view.findViewById(R.id.ivStarred);
    }

    /**
     * Update View Data with view Model object
     * @param dataObject
     */
    public void updateViewDate(final Object dataObject) {
         //update fields of view
    }
}
