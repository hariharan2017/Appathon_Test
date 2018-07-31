package com.cringe.appathon;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    int images[] = { R.drawable.image01,R.drawable.image02,R.drawable.image03,R.drawable.image04,R.drawable.image05,R.drawable.image06};
    ImageView imageView;
    int count;
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        handler.postDelayed(changeImage, 5000);
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
    }

    Runnable changeImage = new Runnable() {

        @Override
        public void run() {

            if (count>5) {
                handler.removeCallbacks(changeImage);
            } else {

                if (count >= 5)
                    count = 0;
                //AlphaAnimation animation1 = new AlphaAnimation(0.5f, 1.0f); //here is a bit of animation for ya ;)
                //animation1.setDuration(5000);
                //animation1.setStartOffset(700); //time for that color effect
                //animation1.setFillAfter(true);
                //imageView.startAnimation(animation1);
                imageView.setBackgroundResource(images[count++]);
                handler.postDelayed(changeImage, 5000);
            }
        }
    };

    public boolean onTouchEvent (MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {

        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if (event2.getX() > event1.getX()) {
                Intent intent = new Intent(MainActivity.this, Start.class);
                finish();
                startActivity(intent);

            } else if (event2.getX() < event1.getX()) {
                Toast.makeText(MainActivity.this, "Dude, that's a left swipe", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
}
