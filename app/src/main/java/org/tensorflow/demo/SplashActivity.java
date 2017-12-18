package org.tensorflow.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), ClassifierActivity.class);//画面遷移のための    　　　　　　Intentを準備
                startActivity(intent);//実際の画面遷移を開始
                finish();//現在のActivityを削除
            }
        }, 2000);//2秒後にrun()を行う

    }
}
