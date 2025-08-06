package com.example.aiva;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    private View parallaxLayer;
    private LottieAnimationView lottieView;
    private View titleAiva;
    private View subTitle;
    private View loadingHint;

    private final Handler handler = new Handler();
    private final String[] phrases = new String[]{
            "your lifestyle companion",
            "guiding your day with AI",
            "smarter choices, effortlessly"
    };
    private int phraseIndex = 0;

    // Runnable for infinite subtitle swapping
    private final Runnable subtitleRunnable = new Runnable() {
        @Override
        public void run() {
            // Fade out current text
            fadeOutView(subTitle, 0, 300);

            // After fade-out, change text & fade in
            handler.postDelayed(() -> {
                if (subTitle instanceof android.widget.TextView) {
                    ((android.widget.TextView) subTitle).setText(phrases[phraseIndex]);
                }
                fadeInView(subTitle, 0, 300);

                // Move to next phrase
                phraseIndex = (phraseIndex + 1) % phrases.length;

                // Schedule next change after 2 seconds
                handler.postDelayed(subtitleRunnable, 2000);
            }, 300);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        parallaxLayer = findViewById(R.id.parallaxLayer);
        lottieView = findViewById(R.id.lottieView);
        titleAiva = findViewById(R.id.titleAiva);
        subTitle = findViewById(R.id.subTitle);
        loadingHint = findViewById(R.id.loadingHint);

        // Lottie infinite loop
        lottieView.setRepeatCount(Animation.INFINITE);
        lottieView.setSpeed(1.0f);

        // Background infinite parallax
        startParallax();

        // Title pop-in animation
        popTitle();

        // Show first phrase and start infinite swap
        if (subTitle instanceof android.widget.TextView) {
            ((android.widget.TextView) subTitle).setText(phrases[0]);
        }
        fadeInView(subTitle, 0, 300);
        phraseIndex = 1; // start from second phrase next
        handler.postDelayed(subtitleRunnable, 2000);

        // Fade-in loading text
        fadeInView(loadingHint, 250, 600);
    }

    private void startParallax() {
        TranslateAnimation translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0.04f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -0.04f
        );
        translate.setDuration(14000);
        translate.setRepeatCount(Animation.INFINITE);
        translate.setRepeatMode(Animation.REVERSE);
        parallaxLayer.startAnimation(translate);
    }

    private void popTitle() {
        ScaleAnimation scale = new ScaleAnimation(
                0.9f, 1.0f, 0.9f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scale.setDuration(500);

        AlphaAnimation alpha = new AlphaAnimation(0f, 1f);
        alpha.setDuration(500);

        titleAiva.startAnimation(scale);
        titleAiva.startAnimation(alpha);
    }

    private void fadeInView(View v, long startOffsetMs, long durationMs) {
        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setStartOffset(startOffsetMs);
        fadeIn.setDuration(durationMs);
        fadeIn.setFillAfter(true);
        v.startAnimation(fadeIn);
    }

    private void fadeOutView(View v, long startOffsetMs, long durationMs) {
        AlphaAnimation fadeOut = new AlphaAnimation(1f, 0f);
        fadeOut.setStartOffset(startOffsetMs);
        fadeOut.setDuration(durationMs);
        fadeOut.setFillAfter(true);
        v.startAnimation(fadeOut);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
