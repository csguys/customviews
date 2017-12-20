package one.session.customviews;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import one.session.customviews.views.CircleView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout frameLayout;
    private CircleView circleView;
    int color[] = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GREEN};
    int count = 0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.main_fr);
        circleView = findViewById(R.id.custom_view);
        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                circleView.setCircleColor(color[++count%5]);
            }
        });
    }


    public void animationActivity(View view) {
       startActivity(new Intent(this, AnimationActivity.class));
    }
}
