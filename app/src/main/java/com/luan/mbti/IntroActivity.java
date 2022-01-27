package com.luan.mbti;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    LinearLayout llTitle;
    ImageView imgTitle;
    Animation ani;
    AnimationDrawable aniDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        llTitle = (LinearLayout) findViewById(R.id.llTitle);
        aniDrawable = (AnimationDrawable) llTitle.getBackground();
        aniDrawable.start();

        imgTitle = (ImageView) findViewById(R.id.imgTitle);
        ani = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.ascend_fast);
        imgTitle.startAnimation(ani);

        // 이벤트 핸들러를 통해서 몇초 후의 동작을 시킨다.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, NicknameActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        }, 2000);
    }
}