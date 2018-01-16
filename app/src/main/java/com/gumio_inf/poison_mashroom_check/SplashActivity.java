package com.gumio_inf.poison_mashroom_check;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.gumio_inf.poison_mashroom_check.databinding.ActivitySplashBinding;


public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "kin.ttf");
        mBinding.textView.setTypeface(typeface);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), ClassifierActivity.class);//画面遷移のための    　　　　　　Intentを準備
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}
