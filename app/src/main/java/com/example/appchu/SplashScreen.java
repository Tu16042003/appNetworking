package com.example.appchu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {


    private ImageView mImageView;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mImageView = findViewById(R.id.imageScreen);
        startAnimation();

    }
    private void startAnimation() {
//        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
//        Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);
//
//        rotate.reset();
//        translate.reset();
//
//        mImageView.setAnimation(rotate);
//        mTextView.setAnimation(translate);

        mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 3500) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                SplashScreen.this.finish();
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        };
        mThread.start();
    }
}