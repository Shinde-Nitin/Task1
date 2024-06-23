package com.example.googlepaysplashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    TextView tvTitle,tvTagline;
    LottieAnimationView lottieAnimationView;
    ImageView ivLogo;
    Animation fadeIn,scaletagline;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setContentView(R.layout.activity_main);
        tvTitle = findViewById(R.id.tvTitle1);
        tvTagline = findViewById(R.id.tvTagline);
        ivLogo = findViewById(R.id.imMainlogo);
        lottieAnimationView  = findViewById(R.id.ltAnimation);
        fadeIn = AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation);
        scaletagline = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);
        ivLogo.startAnimation(fadeIn);
        tvTitle.startAnimation(fadeIn);
        tvTagline.startAnimation(fadeIn);
        lottieAnimationView.setAnimation(R.raw.loader);
        lottieAnimationView.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();
            }
        },3000);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,loginPage.class);
                startActivity(intent);
                finish();
            }
        },6000);



    }
}