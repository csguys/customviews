package one.session.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AnimationActivity extends AppCompatActivity {

    private View viewObject;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        viewObject = findViewById(R.id.view_to_animate);
    }

    public void xAnim(View view) {
        viewObject.animate().translationX(-100).translationY(100).translationX(0).setDuration(1000).start();
    }

    public void yAnim(View view) {

    }

    public void rAnim(View view) {

    }

    public void animSet(View view) {

    }
}
