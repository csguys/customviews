package one.session.customviews;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout frameLayout;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.main_fr);
//        showDIs();
    }

    private void showDIs(){
            DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//    DisplayMetrics metrics = getResources().getDisplayMetrics();
//        Log.i("text", " density:" + metrics.density);
//        Log.i("text", " densityDpi:" + metrics.densityDpi);
//        Log.i("text", " heightPixels:" + metrics.heightPixels);
//        Log.i("text", " widthPixels:" + metrics.widthPixels);
//        Log.i("text", " xdpi:" + metrics.xdpi);
//        Log.i("text", " ydpi:" + metrics.ydpi);
        Log.i("text", " density:" +      displayMetrics.density);
        Log.i("text", " densityDpi:" +   displayMetrics.densityDpi);
        Log.i("text", " heightPixels:" + displayMetrics.heightPixels);
        Log.i("text", " widthPixels:" +  displayMetrics.widthPixels);
        Log.i("text", " xdpi:" +         displayMetrics.xdpi);
        Log.i("text", " ydpi:" +         displayMetrics.ydpi);
        Log.i("text", "-----------------------------------------------");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.v("fr", "height :"+ frameLayout.getHeight());
                Log.v("fr", "width :"+ frameLayout.getWidth());
                Log.v("fr", "bottom :"+ frameLayout.getBottom());
                Log.v("fr", "right :"+ frameLayout.getRight());
            }
        }, 3000);


    }

}
