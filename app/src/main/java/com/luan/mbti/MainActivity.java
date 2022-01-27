package com.luan.mbti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // DB 데이터
    String strNickname, strMbti;

    // 뒤로가기 버튼 관리 (2번 누르면 앱 종료)
    private BackHandler backHandler = new BackHandler(this);

    // 레이아웃 요소
    LinearLayout llTitle, llAbout;
    LinearLayout llMbti1, llMbti2, llMbti3, llMbti4, llMbti5, llMbti6, llMbti7, llMbti8;
    LinearLayout llMbti9, llMbti10, llMbti11, llMbti12, llMbti13, llMbti14, llMbti15, llMbti16;
    TextView tvTitle1, tvTitle2;
    Button btnMbti1, btnMbti2, btnMbti3, btnMbti4, btnMbti5, btnMbti6, btnMbti7, btnMbti8;
    Button btnMbti9, btnMbti10, btnMbti11, btnMbti12, btnMbti13, btnMbti14, btnMbti15, btnMbti16;
    Button btnAbout;

    // 애니메이션
    Animation aniLlTitle, aniLlAbout;
    Animation aniLlMbti1, aniLlMbti2, aniLlMbti3, aniLlMbti4, aniLlMbti5, aniLlMbti6, aniLlMbti7, aniLlMbti8;
    Animation aniLlMbti9, aniLlMbti10, aniLlMbti11, aniLlMbti12, aniLlMbti13, aniLlMbti14, aniLlMbti15, aniLlMbti16;
    Animation aniTvTitle1, aniTvTitle2;
    Animation aniBtnMbti1, aniBtnMbti2, aniBtnMbti3, aniBtnMbti4, aniBtnMbti5, aniBtnMbti6, aniBtnMbti7, aniBtnMbti8;
    Animation aniBtnMbti9, aniBtnMbti10, aniBtnMbti11, aniBtnMbti12, aniBtnMbti13, aniBtnMbti14, aniBtnMbti15, aniBtnMbti16;
    Animation aniBtnAbout;
    Animation aniTouch;

    // 사이드 메뉴
    private DrawerLayout drawerLayout;
    private View drawerView;
    Button btnNickname;
    TextView tvMbti;
    ImageView btnClose;
    Button btnMatchSide, btnGameSide, btnBookSide, btnYoutubeSide;
    Button btnMusicSide, btnMovieSide, btnDramaSide, btnJobSide;

    // 하단 메뉴
    ImageButton btnSidemenu, btnHome, btnAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 이전 페이지에서 데이터 받기
        Intent getData = getIntent();
        strNickname = getData.getStringExtra("nick");
        strMbti = getData.getStringExtra("mbti");

        // 사이드메뉴
        btnNickname = (Button) findViewById(R.id.btnNickname);
        tvMbti = (TextView) findViewById(R.id.tvMbti);
        drawerLayout = (DrawerLayout) findViewById(R.id.sidemenu_layout);
        drawerView = (View) findViewById(R.id.drawer);
        drawerLayout.setDrawerListener(drawerListener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        btnClose = (ImageView) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        btnNickname = (Button) findViewById(R.id.btnNickname);
        btnNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogProfile();
            }
        });
        btnMatchSide = (Button) findViewById(R.id.btnMatchSide);
        btnMatchSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageMatch();
            }
        });
        btnGameSide = (Button) findViewById(R.id.btnGameSide);
        btnGameSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageGame();
            }
        });
        btnBookSide = (Button) findViewById(R.id.btnBookSide);
        btnBookSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageBook();
            }
        });
        btnYoutubeSide = (Button) findViewById(R.id.btnYoutubeSide);
        btnYoutubeSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageYoutube();
            }
        });
        btnMusicSide = (Button) findViewById(R.id.btnMusicSide);
        btnMusicSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageMusic();
            }
        });
        btnMovieSide = (Button) findViewById(R.id.btnMovieSide);
        btnMovieSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageMovie();
            }
        });
        btnDramaSide = (Button) findViewById(R.id.btnDramaSide);
        btnDramaSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageDrama();
            }
        });
        btnJobSide = (Button) findViewById(R.id.btnJobSide);
        btnJobSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageJob();
            }
        });

        // 레이아웃
        llTitle = (LinearLayout) findViewById(R.id.llTitle);
        llMbti1 = (LinearLayout) findViewById(R.id.llMbti1);
        llMbti2 = (LinearLayout) findViewById(R.id.llMbti2);
        llMbti3 = (LinearLayout) findViewById(R.id.llMbti3);
        llMbti4 = (LinearLayout) findViewById(R.id.llMbti4);
        llMbti5 = (LinearLayout) findViewById(R.id.llMbti5);
        llMbti6 = (LinearLayout) findViewById(R.id.llMbti6);
        llMbti7 = (LinearLayout) findViewById(R.id.llMbti7);
        llMbti8 = (LinearLayout) findViewById(R.id.llMbti8);
        llMbti9 = (LinearLayout) findViewById(R.id.llMbti9);
        llMbti10 = (LinearLayout) findViewById(R.id.llMbti10);
        llMbti11 = (LinearLayout) findViewById(R.id.llMbti11);
        llMbti12 = (LinearLayout) findViewById(R.id.llMbti12);
        llMbti13 = (LinearLayout) findViewById(R.id.llMbti13);
        llMbti14 = (LinearLayout) findViewById(R.id.llMbti14);
        llMbti15 = (LinearLayout) findViewById(R.id.llMbti15);
        llMbti16 = (LinearLayout) findViewById(R.id.llMbti16);
        llAbout = (LinearLayout) findViewById(R.id.llAbout);

        // 레이아웃 애니메이션
        aniLlTitle = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlMbti1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti5 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti6 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti7 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti8 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti9 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti10 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti11 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti12 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti13 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti14 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti15 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti16 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlAbout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniLlMbti1.setStartOffset(200);
        aniLlMbti2.setStartOffset(300);
        aniLlMbti3.setStartOffset(400);
        aniLlMbti4.setStartOffset(500);
        aniLlMbti5.setStartOffset(600);
        aniLlMbti6.setStartOffset(700);
        aniLlMbti7.setStartOffset(800);
        aniLlMbti8.setStartOffset(900);
        aniLlMbti9.setStartOffset(1000);
        aniLlMbti10.setStartOffset(1100);
        aniLlMbti11.setStartOffset(1200);
        aniLlMbti12.setStartOffset(1300);
        aniLlMbti13.setStartOffset(1400);
        aniLlMbti14.setStartOffset(1500);
        aniLlMbti15.setStartOffset(1600);
        aniLlMbti16.setStartOffset(1700);
        aniLlAbout.setStartOffset(1800);
        llTitle.startAnimation(aniLlTitle);
        llMbti1.startAnimation(aniLlMbti1);
        llMbti2.startAnimation(aniLlMbti2);
        llMbti3.startAnimation(aniLlMbti3);
        llMbti4.startAnimation(aniLlMbti4);
        llMbti5.startAnimation(aniLlMbti5);
        llMbti6.startAnimation(aniLlMbti6);
        llMbti7.startAnimation(aniLlMbti7);
        llMbti8.startAnimation(aniLlMbti8);
        llMbti9.startAnimation(aniLlMbti9);
        llMbti10.startAnimation(aniLlMbti10);
        llMbti11.startAnimation(aniLlMbti11);
        llMbti12.startAnimation(aniLlMbti12);
        llMbti13.startAnimation(aniLlMbti13);
        llMbti14.startAnimation(aniLlMbti14);
        llMbti15.startAnimation(aniLlMbti15);
        llMbti16.startAnimation(aniLlMbti16);
        llAbout.startAnimation(aniLlAbout);

        // 버튼 & 텍스트
        tvTitle1 = (TextView) findViewById(R.id.tvTitle1);
        tvTitle1.setOnClickListener(mClickListener);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        btnMbti1 = (Button) findViewById(R.id.btnMbti1);
        btnMbti2 = (Button) findViewById(R.id.btnMbti2);
        btnMbti3 = (Button) findViewById(R.id.btnMbti3);
        btnMbti4 = (Button) findViewById(R.id.btnMbti4);
        btnMbti5 = (Button) findViewById(R.id.btnMbti5);
        btnMbti6 = (Button) findViewById(R.id.btnMbti6);
        btnMbti7 = (Button) findViewById(R.id.btnMbti7);
        btnMbti8 = (Button) findViewById(R.id.btnMbti8);
        btnMbti9 = (Button) findViewById(R.id.btnMbti9);
        btnMbti10 = (Button) findViewById(R.id.btnMbti10);
        btnMbti11 = (Button) findViewById(R.id.btnMbti11);
        btnMbti12 = (Button) findViewById(R.id.btnMbti12);
        btnMbti13 = (Button) findViewById(R.id.btnMbti13);
        btnMbti14 = (Button) findViewById(R.id.btnMbti14);
        btnMbti15 = (Button) findViewById(R.id.btnMbti15);
        btnMbti16 = (Button) findViewById(R.id.btnMbti16);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnMbti1.setOnClickListener(mClickListener);
        btnMbti2.setOnClickListener(mClickListener);
        btnMbti3.setOnClickListener(mClickListener);
        btnMbti4.setOnClickListener(mClickListener);
        btnMbti5.setOnClickListener(mClickListener);
        btnMbti6.setOnClickListener(mClickListener);
        btnMbti7.setOnClickListener(mClickListener);
        btnMbti8.setOnClickListener(mClickListener);
        btnMbti9.setOnClickListener(mClickListener);
        btnMbti10.setOnClickListener(mClickListener);
        btnMbti11.setOnClickListener(mClickListener);
        btnMbti12.setOnClickListener(mClickListener);
        btnMbti13.setOnClickListener(mClickListener);
        btnMbti14.setOnClickListener(mClickListener);
        btnMbti15.setOnClickListener(mClickListener);
        btnMbti16.setOnClickListener(mClickListener);
        btnAbout.setOnClickListener(mClickListener);

        // 버튼 & 텍스트 애니메이션
        aniTvTitle1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend_fast);
        aniTvTitle1.setStartOffset(500);
        aniTvTitle2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend_fast);
        aniTvTitle2.setStartOffset(700);
        aniBtnMbti1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti5 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti6 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti7 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti8 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti9 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti10 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti11 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti12 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti13 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti14 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti15 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti16 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnAbout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
        aniBtnMbti1.setStartOffset(400);
        aniBtnMbti2.setStartOffset(500);
        aniBtnMbti3.setStartOffset(600);
        aniBtnMbti4.setStartOffset(700);
        aniBtnMbti5.setStartOffset(800);
        aniBtnMbti6.setStartOffset(900);
        aniBtnMbti7.setStartOffset(1000);
        aniBtnMbti8.setStartOffset(1100);
        aniBtnMbti9.setStartOffset(1200);
        aniBtnMbti10.setStartOffset(1300);
        aniBtnMbti11.setStartOffset(1400);
        aniBtnMbti12.setStartOffset(1500);
        aniBtnMbti13.setStartOffset(1600);
        aniBtnMbti14.setStartOffset(1700);
        aniBtnMbti15.setStartOffset(1800);
        aniBtnMbti16.setStartOffset(1900);
        aniBtnAbout.setStartOffset(2000);
        btnMbti1.startAnimation(aniBtnMbti1);
        btnMbti2.startAnimation(aniBtnMbti2);
        btnMbti3.startAnimation(aniBtnMbti3);
        btnMbti4.startAnimation(aniBtnMbti4);
        btnMbti5.startAnimation(aniBtnMbti5);
        btnMbti6.startAnimation(aniBtnMbti6);
        btnMbti7.startAnimation(aniBtnMbti7);
        btnMbti8.startAnimation(aniBtnMbti8);
        btnMbti9.startAnimation(aniBtnMbti9);
        btnMbti10.startAnimation(aniBtnMbti10);
        btnMbti11.startAnimation(aniBtnMbti11);
        btnMbti12.startAnimation(aniBtnMbti12);
        btnMbti13.startAnimation(aniBtnMbti13);
        btnMbti14.startAnimation(aniBtnMbti14);
        btnMbti15.startAnimation(aniBtnMbti15);
        btnMbti16.startAnimation(aniBtnMbti16);
        btnAbout.startAnimation(aniBtnAbout);

        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);

        // 터치 할 때 색 변환
        setColorStateList(btnMbti1, ContextCompat.getColor(this, R.color.mbti_red), btnMbti1.getCurrentTextColor());
        setColorStateList(btnMbti2, ContextCompat.getColor(this, R.color.mbti_yellow), btnMbti2.getCurrentTextColor());
        setColorStateList(btnMbti3, ContextCompat.getColor(this, R.color.mbti_green), btnMbti3.getCurrentTextColor());
        setColorStateList(btnMbti4, ContextCompat.getColor(this, R.color.mbti_orange), btnMbti4.getCurrentTextColor());

        setColorStateList(btnMbti5, ContextCompat.getColor(this, R.color.mbti_orange), btnMbti5.getCurrentTextColor());
        setColorStateList(btnMbti6, ContextCompat.getColor(this, R.color.mbti_red), btnMbti6.getCurrentTextColor());
        setColorStateList(btnMbti7, ContextCompat.getColor(this, R.color.mbti_yellow), btnMbti7.getCurrentTextColor());
        setColorStateList(btnMbti8, ContextCompat.getColor(this, R.color.mbti_green), btnMbti8.getCurrentTextColor());

        setColorStateList(btnMbti9, ContextCompat.getColor(this, R.color.mbti_green), btnMbti9.getCurrentTextColor());
        setColorStateList(btnMbti10, ContextCompat.getColor(this, R.color.mbti_orange), btnMbti10.getCurrentTextColor());
        setColorStateList(btnMbti11, ContextCompat.getColor(this, R.color.mbti_red), btnMbti11.getCurrentTextColor());
        setColorStateList(btnMbti12, ContextCompat.getColor(this, R.color.mbti_yellow), btnMbti12.getCurrentTextColor());

        setColorStateList(btnMbti13, ContextCompat.getColor(this, R.color.mbti_yellow), btnMbti13.getCurrentTextColor());
        setColorStateList(btnMbti14, ContextCompat.getColor(this, R.color.mbti_green), btnMbti14.getCurrentTextColor());
        setColorStateList(btnMbti15, ContextCompat.getColor(this, R.color.mbti_orange), btnMbti15.getCurrentTextColor());
        setColorStateList(btnMbti16, ContextCompat.getColor(this, R.color.mbti_red), btnMbti16.getCurrentTextColor());

        // 하단메뉴
        btnSidemenu = (ImageButton) findViewById(R.id.btnSidemenu);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnAccount = (ImageButton) findViewById(R.id.btnAccount);
        btnSidemenu.setOnClickListener(mClickListener);
        btnHome.setOnClickListener(mClickListener);
        btnAccount.setOnClickListener(mClickListener);

    }

    // Main Layout
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tvTitle1:
                    rainbow();
                    break;
                case R.id.btnMbti1:
                    llMbti1.startAnimation(aniTouch);
                    btnMbti1.startAnimation(aniTouch);
                    pageIntj();
                    break;
                case R.id.btnMbti2:
                    llMbti2.startAnimation(aniTouch);
                    btnMbti2.startAnimation(aniTouch);
                    pageIntp();
                    break;
                case R.id.btnMbti3:
                    llMbti3.startAnimation(aniTouch);
                    btnMbti3.startAnimation(aniTouch);
                    pageEntj();
                    break;
                case R.id.btnMbti4:
                    llMbti4.startAnimation(aniTouch);
                    btnMbti4.startAnimation(aniTouch);
                    pageEntp();
                    break;
                case R.id.btnMbti5:
                    llMbti5.startAnimation(aniTouch);
                    btnMbti5.startAnimation(aniTouch);
                    pageInfj();
                    break;
                case R.id.btnMbti6:
                    llMbti6.startAnimation(aniTouch);
                    btnMbti6.startAnimation(aniTouch);
                    pageInfp();
                    break;
                case R.id.btnMbti7:
                    llMbti7.startAnimation(aniTouch);
                    btnMbti7.startAnimation(aniTouch);
                    pageEnfj();
                    break;
                case R.id.btnMbti8:
                    llMbti8.startAnimation(aniTouch);
                    btnMbti8.startAnimation(aniTouch);
                    pageEnfp();
                    break;
                case R.id.btnMbti9:
                    llMbti9.startAnimation(aniTouch);
                    btnMbti9.startAnimation(aniTouch);
                    pageIstj();
                    break;
                case R.id.btnMbti10:
                    llMbti10.startAnimation(aniTouch);
                    btnMbti10.startAnimation(aniTouch);
                    pageIsfj();
                    break;
                case R.id.btnMbti11:
                    llMbti11.startAnimation(aniTouch);
                    btnMbti11.startAnimation(aniTouch);
                    pageEstj();
                    break;
                case R.id.btnMbti12:
                    llMbti12.startAnimation(aniTouch);
                    btnMbti12.startAnimation(aniTouch);
                    pageEsfj();
                    break;
                case R.id.btnMbti13:
                    llMbti13.startAnimation(aniTouch);
                    btnMbti13.startAnimation(aniTouch);
                    pageIstp();
                    break;
                case R.id.btnMbti14:
                    llMbti14.startAnimation(aniTouch);
                    btnMbti14.startAnimation(aniTouch);
                    pageIsfp();
                    break;
                case R.id.btnMbti15:
                    llMbti15.startAnimation(aniTouch);
                    btnMbti15.startAnimation(aniTouch);
                    pageEstp();
                    break;
                case R.id.btnMbti16:
                    llMbti16.startAnimation(aniTouch);
                    btnMbti16.startAnimation(aniTouch);
                    pageEsfp();
                    break;
                case R.id.btnAbout:
                    llAbout.startAnimation(aniTouch);
                    btnAbout.startAnimation(aniTouch);
                    dialogAbout();
                    break;
                // 하단 버튼들
                case R.id.btnSidemenu:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnHome:
                    Intent intentHome = new Intent(MainActivity.this, MainActivity.class);
                    intentHome.putExtra("nick", strNickname);
                    intentHome.putExtra("mbti", strMbti);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnAccount:
                    Intent intentAccount = new Intent(MainActivity.this, AccountActivity.class);
                    intentAccount.putExtra("nick", strNickname);
                    intentAccount.putExtra("mbti", strMbti);
                    startActivity(intentAccount);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
            }
        }
    };

    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        backHandler.onBackPressed();
    }

    // 사이드 메뉴
    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
            if (strNickname == null) {
                btnNickname.setText("닉네임");
            } else {
                btnNickname.setText(strNickname);
            }
            if (strMbti == null) {
                tvMbti.setText("MBTI");
            } else {
                tvMbti.setText(strMbti);
            }
        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    // 프로필 다이얼로그 (닉네임, MBTI 설정)
    public void dialogProfile() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.profile_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dl_background);
        dialog.setCancelable(false);

        // 닫기 버튼
        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // "네" 버튼
        Button btnYes = dialog.findViewById(R.id.btn_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intentProfile = new Intent(MainActivity.this, ProfileActivity.class);
                intentProfile.putExtra("nick", strNickname);
                intentProfile.putExtra("mbti", strMbti);
                startActivity(intentProfile);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });

        // "아니오" 버튼
        Button btnNo = dialog.findViewById(R.id.btn_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // About 다이얼로그
    public void dialogAbout() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.about_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dl_background);
        dialog.setCancelable(false);

        // 닫기 버튼
        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // "돌아가기" 버튼
        Button btnReturn = dialog.findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // 타이틀 색상 애니메이션
    private void rainbow() {
        Integer dark = Color.parseColor("#292929");
        Integer green = Color.parseColor("#96CEB4");
        Integer yellow = Color.parseColor("#FFEEAD");
        Integer red = Color.parseColor("#D9534F");
        Integer orange = Color.parseColor("#FFAD60");

        ValueAnimator colorAni1 = ValueAnimator.ofObject(new ArgbEvaluator(), dark, green);
        colorAni1.setDuration(300);
        colorAni1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvTitle1.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni2 = ValueAnimator.ofObject(new ArgbEvaluator(), green, yellow);
        colorAni2.setDuration(300);
        colorAni2.setStartDelay(300);
        colorAni2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvTitle1.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni3 = ValueAnimator.ofObject(new ArgbEvaluator(), yellow, red);
        colorAni3.setDuration(300);
        colorAni3.setStartDelay(600);
        colorAni3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvTitle1.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni4 = ValueAnimator.ofObject(new ArgbEvaluator(), red, orange);
        colorAni4.setDuration(300);
        colorAni4.setStartDelay(900);
        colorAni4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvTitle1.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        ValueAnimator colorAni5 = ValueAnimator.ofObject(new ArgbEvaluator(), orange, dark);
        colorAni5.setDuration(300);
        colorAni5.setStartDelay(1200);
        colorAni5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                tvTitle1.setTextColor((Integer) animator.getAnimatedValue());
            }
        });

        colorAni1.start();
        colorAni2.start();
        colorAni3.start();
        colorAni4.start();
        colorAni5.start();
    }

    // INTJ
    public void pageIntj() {
        Intent intentIntj = new Intent(MainActivity.this, IntjActivity.class);
        intentIntj.putExtra("nick", strNickname);
        intentIntj.putExtra("mbti", strMbti);
        startActivity(intentIntj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // INTP
    public void pageIntp() {
        Intent intentIntp = new Intent(MainActivity.this, IntpActivity.class);
        intentIntp.putExtra("nick", strNickname);
        intentIntp.putExtra("mbti", strMbti);
        startActivity(intentIntp);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ENTJ
    public void pageEntj() {
        Intent intentEntj = new Intent(MainActivity.this, EntjActivity.class);
        intentEntj.putExtra("nick", strNickname);
        intentEntj.putExtra("mbti", strMbti);
        startActivity(intentEntj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ENTP
    public void pageEntp() {
        Intent intentEntp = new Intent(MainActivity.this, EntpActivity.class);
        intentEntp.putExtra("nick", strNickname);
        intentEntp.putExtra("mbti", strMbti);
        startActivity(intentEntp);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // INFJ
    public void pageInfj() {
        Intent intentInfj = new Intent(MainActivity.this, InfjActivity.class);
        intentInfj.putExtra("nick", strNickname);
        intentInfj.putExtra("mbti", strMbti);
        startActivity(intentInfj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // INFP
    public void pageInfp() {
        Intent intentInfp = new Intent(MainActivity.this, InfpActivity.class);
        intentInfp.putExtra("nick", strNickname);
        intentInfp.putExtra("mbti", strMbti);
        startActivity(intentInfp);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ENFJ
    public void pageEnfj() {
        Intent intentEnfj = new Intent(MainActivity.this, EnfjActivity.class);
        intentEnfj.putExtra("nick", strNickname);
        intentEnfj.putExtra("mbti", strMbti);
        startActivity(intentEnfj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ENFP
    public void pageEnfp() {
        Intent intentEnfp = new Intent(MainActivity.this, EnfpActivity.class);
        intentEnfp.putExtra("nick", strNickname);
        intentEnfp.putExtra("mbti", strMbti);
        startActivity(intentEnfp);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ISTJ
    public void pageIstj() {
        Intent intentIstj = new Intent(MainActivity.this, IstjActivity.class);
        intentIstj.putExtra("nick", strNickname);
        intentIstj.putExtra("mbti", strMbti);
        startActivity(intentIstj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ISFJ
    public void pageIsfj() {
        Intent intentIsfj = new Intent(MainActivity.this, IsfjActivity.class);
        intentIsfj.putExtra("nick", strNickname);
        intentIsfj.putExtra("mbti", strMbti);
        startActivity(intentIsfj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ESTJ
    public void pageEstj() {
        Intent intentEstj = new Intent(MainActivity.this, EstjActivity.class);
        intentEstj.putExtra("nick", strNickname);
        intentEstj.putExtra("mbti", strMbti);
        startActivity(intentEstj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ESFJ
    public void pageEsfj() {
        Intent intentEsfj = new Intent(MainActivity.this, EsfjActivity.class);
        intentEsfj.putExtra("nick", strNickname);
        intentEsfj.putExtra("mbti", strMbti);
        startActivity(intentEsfj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ISTP
    public void pageIstp() {
        Intent intentIstp = new Intent(MainActivity.this, IstpActivity.class);
        intentIstp.putExtra("nick", strNickname);
        intentIstp.putExtra("mbti", strMbti);
        startActivity(intentIstp);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ISFP
    public void pageIsfp() {
        Intent intentIsfp = new Intent(MainActivity.this, IsfpActivity.class);
        intentIsfp.putExtra("nick", strNickname);
        intentIsfp.putExtra("mbti", strMbti);
        startActivity(intentIsfp);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ESTP
    public void pageEstp() {
        Intent intentEstp = new Intent(MainActivity.this, EstpActivity.class);
        intentEstp.putExtra("nick", strNickname);
        intentEstp.putExtra("mbti", strMbti);
        startActivity(intentEstp);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // ESFP
    public void pageEsfp() {
        Intent intentEsfp = new Intent(MainActivity.this, EsfpActivity.class);
        intentEsfp.putExtra("nick", strNickname);
        intentEsfp.putExtra("mbti", strMbti);
        startActivity(intentEsfp);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // 색 변환 함수
    public void setColorStateList(View view, int selectedColor, int defaultColor) {
        int[][] states = new int[][]{
                new int[]{
                        android.R.attr.state_pressed,
                        android.R.attr.state_selected
                }, // pressed, selected, focused
                new int[]{} // default
        };

        int[] colors = new int[]{
                selectedColor,
                defaultColor
        };

        ColorStateList textColorList = new ColorStateList(states, colors);
        if (view instanceof TextView || view instanceof AppCompatTextView) { // TextView
            ((TextView) view).setTextColor(textColorList);
            view.setClickable(true);
        } else if (view instanceof Button || view instanceof AppCompatButton) { // Button
            ((Button) view).setTextColor(textColorList);
        }
        view.setSelected(true);
    }

    // Match page
    public void pageMatch() {
        Intent intentMatch = new Intent(MainActivity.this, MatchAllActivity.class);
        intentMatch.putExtra("nick", strNickname);
        intentMatch.putExtra("mbti", strMbti);
        startActivity(intentMatch);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Game page
    public void pageGame() {
        Intent intentGame = new Intent(MainActivity.this, GameAllActivity.class);
        intentGame.putExtra("nick", strNickname);
        intentGame.putExtra("mbti", strMbti);
        startActivity(intentGame);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Book page
    public void pageBook() {
        Intent intentBook = new Intent(MainActivity.this, BookAllActivity.class);
        intentBook.putExtra("nick", strNickname);
        intentBook.putExtra("mbti", strMbti);
        startActivity(intentBook);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Youtube page
    public void pageYoutube() {
        Intent intentYoutube = new Intent(MainActivity.this, YoutubeAllActivity.class);
        intentYoutube.putExtra("nick", strNickname);
        intentYoutube.putExtra("mbti", strMbti);
        startActivity(intentYoutube);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Music page
    public void pageMusic() {
        Intent intentMusic = new Intent(MainActivity.this, MusicAllActivity.class);
        intentMusic.putExtra("nick", strNickname);
        intentMusic.putExtra("mbti", strMbti);
        startActivity(intentMusic);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Movie page
    public void pageMovie() {
        Intent intentMovie = new Intent(MainActivity.this, MovieAllActivity.class);
        intentMovie.putExtra("nick", strNickname);
        intentMovie.putExtra("mbti", strMbti);
        startActivity(intentMovie);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Drama page
    public void pageDrama() {
        Intent intentDrama = new Intent(MainActivity.this, DramaAllActivity.class);
        intentDrama.putExtra("nick", strNickname);
        intentDrama.putExtra("mbti", strMbti);
        startActivity(intentDrama);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Job page
    public void pageJob() {
        Intent intentJob = new Intent(MainActivity.this, JobAllActivity.class);
        intentJob.putExtra("nick", strNickname);
        intentJob.putExtra("mbti", strMbti);
        startActivity(intentJob);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
}