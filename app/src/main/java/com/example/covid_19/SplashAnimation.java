package com.example.covid_19;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashAnimation extends AppCompatActivity {

    TextView textView;
    LottieAnimationView lottieAnimationView, emptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        emptyView = (LottieAnimationView) findViewById(R.id.empty_view_splash);
        textView = (TextView) findViewById(R.id.animation_view_text);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view);


        LottieAnimationView lottieView = findViewById(R.id.animation_view);

        if (isNetworkAvailable(this) == false) {
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        } else {

            emptyView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);

            lottieView.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    startActivity(new Intent(getBaseContext(), WorldActivity.class));
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }

    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}

