package one.session.customviews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class AnimationActivity extends AppCompatActivity {

    private static final int DURATION = 3000;
    private View viewObject;
    boolean flag;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        viewObject = findViewById(R.id.view_to_animate);
        viewObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.i("test","view clicked");
            }
        });
    }

    public void valueAnimation(View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,360);
        valueAnimator.setDuration(DURATION);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                float val = (float) animation.getAnimatedValue();
                viewObject.setRotation(val);
            }
        });
        valueAnimator.start();
    }

    public void objectAnimator(View view) {
        ObjectAnimator  objectAnimator = ObjectAnimator.ofFloat(viewObject,"rotation",0,360);
        objectAnimator.setDuration(DURATION);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
    }

    public void valuePropertyAnimator(View view) {
        viewObject.animate().rotation(360).setDuration(DURATION).start();
//        if (flag) {
//            viewObject.animate().rotation(360).setDuration(DURATION).start();
//        }else {
//            viewObject.animate().rotation(0).setDuration(DURATION);
//        }
//        flag = !flag;
    }

    public void animSet(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,360);
        valueAnimator.setDuration(DURATION);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                float val = (float) animation.getAnimatedValue();
                viewObject.setRotation(val);
            }
        });
        ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(0,100);
        valueAnimator1.setDuration(DURATION);
        valueAnimator1.setInterpolator(new LinearInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(final ValueAnimator animation) {
                float val = (float) animation.getAnimatedValue();
                viewObject.setTranslationX(val);
            }
        });

        animatorSet.playTogether(valueAnimator, valueAnimator1);
        animatorSet.start();
    }

    public void goBack(View view) {
        finish();
    }

    public void reset(View view) {
        viewObject.animate().translationX(0).translationY(0);
    }
}
