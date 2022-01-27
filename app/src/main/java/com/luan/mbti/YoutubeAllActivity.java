package com.luan.mbti;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class YoutubeAllActivity extends AppCompatActivity {

    // DB 데이터
    String strNickname, strMbti;

    // 타이틀
    TextView tvTitle;
    ImageView imgAvatar;

    // 레이아웃 애니메이션
    LinearLayout llTop;
    LinearLayout llIntj, llIntp, llEntj, llEntp, llInfj, llInfp, llEnfj, llEnfp;
    LinearLayout llIstj, llIsfj, llEstj, llEsfj, llIstp, llIsfp, llEstp, llEsfp;
    Animation aniLlTop;
    Animation aniLlIntj, aniLlIntp, aniLlEntj, aniLlEntp, aniLlInfj, aniLlInfp, aniLlEnfj, aniLlEnfp;
    Animation aniLlIstj, aniLlIsfj, aniLlEstj, aniLlEsfj, aniLlIstp, aniLlIsfp, aniLlEstp, aniLlEsfp;
    Animation aniTouch;

    // 하단 메뉴
    ImageButton btnSidemenu, btnHome, btnAccount;

    // 사이드 메뉴
    private View drawerView;
    private DrawerLayout drawerLayout;
    ImageView btnClose;
    Button btnNickname;
    TextView tvMbti;
    Button btnMatchSide, btnGameSide, btnBookSide, btnYoutubeSide;
    Button btnMusicSide, btnMovieSide, btnDramaSide, btnJobSide;
    
    // 레이아웃 요소들
    TextView tvIntjTitle, tvIntjContent;
    TextView tvIntpTitle, tvIntpContent;
    TextView tvEntjTitle, tvEntjContent;
    TextView tvEntpTitle, tvEntpContent;
    TextView tvInfjTitle, tvInfjContent;
    TextView tvInfpTitle, tvInfpContent;
    TextView tvEnfjTitle, tvEnfjContent;
    TextView tvEnfpTitle, tvEnfpContent;
    TextView tvIstjTitle, tvIstjContent;
    TextView tvIsfjTitle, tvIsfjContent;
    TextView tvEstjTitle, tvEstjContent;
    TextView tvEsfjTitle, tvEsfjContent;
    TextView tvIstpTitle, tvIstpContent;
    TextView tvIsfpTitle, tvIsfpContent;
    TextView tvEstpTitle, tvEstpContent;
    TextView tvEsfpTitle, tvEsfpContent;

    String add = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_all);

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

        // 하단메뉴
        btnSidemenu = (ImageButton) findViewById(R.id.btnSidemenu);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnAccount = (ImageButton) findViewById(R.id.btnAccount);
        btnSidemenu.setOnClickListener(mClickListener);
        btnHome.setOnClickListener(mClickListener);
        btnAccount.setOnClickListener(mClickListener);
        
        // MBTI 버튼
        tvIntjTitle = (TextView) findViewById(R.id.tvIntjTitle);
        tvIntpTitle = (TextView) findViewById(R.id.tvIntpTitle);
        tvEntjTitle = (TextView) findViewById(R.id.tvEntjTitle);
        tvEntpTitle = (TextView) findViewById(R.id.tvEntpTitle);
        tvInfjTitle = (TextView) findViewById(R.id.tvInfjTitle);
        tvInfpTitle = (TextView) findViewById(R.id.tvInfpTitle);
        tvEnfjTitle = (TextView) findViewById(R.id.tvEnfjTitle);
        tvEnfpTitle = (TextView) findViewById(R.id.tvEnfpTitle);
        tvIstjTitle = (TextView) findViewById(R.id.tvIstjTitle);
        tvIsfjTitle = (TextView) findViewById(R.id.tvIsfjTitle);
        tvEstjTitle = (TextView) findViewById(R.id.tvEstjTitle);
        tvEsfjTitle = (TextView) findViewById(R.id.tvEsfjTitle);
        tvIstpTitle = (TextView) findViewById(R.id.tvIstpTitle);
        tvIsfpTitle = (TextView) findViewById(R.id.tvIsfpTitle);
        tvEstpTitle = (TextView) findViewById(R.id.tvEstpTitle);
        tvEsfpTitle = (TextView) findViewById(R.id.tvEsfpTitle);
        tvIntjTitle.setOnClickListener(mClickListener);
        tvIntpTitle.setOnClickListener(mClickListener);
        tvEntjTitle.setOnClickListener(mClickListener);
        tvEntpTitle.setOnClickListener(mClickListener);
        tvInfjTitle.setOnClickListener(mClickListener);
        tvInfpTitle.setOnClickListener(mClickListener);
        tvEnfjTitle.setOnClickListener(mClickListener);
        tvEnfpTitle.setOnClickListener(mClickListener);
        tvIstjTitle.setOnClickListener(mClickListener);
        tvIsfjTitle.setOnClickListener(mClickListener);
        tvEstjTitle.setOnClickListener(mClickListener);
        tvEsfjTitle.setOnClickListener(mClickListener);
        tvIstpTitle.setOnClickListener(mClickListener);
        tvIsfpTitle.setOnClickListener(mClickListener);
        tvEstpTitle.setOnClickListener(mClickListener);
        tvEsfpTitle.setOnClickListener(mClickListener);
        
        // 분야별 컨텐츠 버튼
        tvIstpContent = (TextView) findViewById(R.id.tvIstpContent);
        tvIsfpContent = (TextView) findViewById(R.id.tvIsfpContent);
        tvEstpContent = (TextView) findViewById(R.id.tvEstpContent);
        tvEsfpContent = (TextView) findViewById(R.id.tvEsfpContent);
        tvIntjContent = (TextView) findViewById(R.id.tvIntjContent);
        tvIntpContent = (TextView) findViewById(R.id.tvIntpContent);
        tvEntjContent = (TextView) findViewById(R.id.tvEntjContent);
        tvEntpContent = (TextView) findViewById(R.id.tvEntpContent);
        tvInfjContent = (TextView) findViewById(R.id.tvInfjContent);
        tvInfpContent = (TextView) findViewById(R.id.tvInfpContent);
        tvEnfjContent = (TextView) findViewById(R.id.tvEnfjContent);
        tvEnfpContent = (TextView) findViewById(R.id.tvEnfpContent);
        tvIstjContent = (TextView) findViewById(R.id.tvIstjContent);
        tvIsfjContent = (TextView) findViewById(R.id.tvIsfjContent);
        tvEstjContent = (TextView) findViewById(R.id.tvEstjContent);
        tvEsfjContent = (TextView) findViewById(R.id.tvEsfjContent);
        tvIntjContent.setOnClickListener(mClickListener);
        tvIntpContent.setOnClickListener(mClickListener);
        tvEntjContent.setOnClickListener(mClickListener);
        tvEntpContent.setOnClickListener(mClickListener);
        tvInfjContent.setOnClickListener(mClickListener);
        tvInfpContent.setOnClickListener(mClickListener);
        tvEnfjContent.setOnClickListener(mClickListener);
        tvEnfpContent.setOnClickListener(mClickListener);
        tvIstjContent.setOnClickListener(mClickListener);
        tvIsfjContent.setOnClickListener(mClickListener);
        tvEstjContent.setOnClickListener(mClickListener);
        tvEsfjContent.setOnClickListener(mClickListener);
        tvIstpContent.setOnClickListener(mClickListener);
        tvIsfpContent.setOnClickListener(mClickListener);
        tvEstpContent.setOnClickListener(mClickListener);
        tvEsfpContent.setOnClickListener(mClickListener);

        // 애니메이션
        llTop = (LinearLayout) findViewById(R.id.llTop);
        llIntj = (LinearLayout) findViewById(R.id.llIntj);
        llIntp = (LinearLayout) findViewById(R.id.llIntp);
        llEntj = (LinearLayout) findViewById(R.id.llEntj);
        llEntp = (LinearLayout) findViewById(R.id.llEntp);
        llInfj = (LinearLayout) findViewById(R.id.llInfj);
        llInfp = (LinearLayout) findViewById(R.id.llInfp);
        llEnfj = (LinearLayout) findViewById(R.id.llEnfj);
        llEnfp = (LinearLayout) findViewById(R.id.llEnfp);
        llIstj = (LinearLayout) findViewById(R.id.llIstj);
        llIsfj = (LinearLayout) findViewById(R.id.llIsfj);
        llEstj = (LinearLayout) findViewById(R.id.llEstj);
        llEsfj = (LinearLayout) findViewById(R.id.llEsfj);
        llIstp = (LinearLayout) findViewById(R.id.llIstp);
        llIsfp = (LinearLayout) findViewById(R.id.llIsfp);
        llEstp = (LinearLayout) findViewById(R.id.llEstp);
        llEsfp = (LinearLayout) findViewById(R.id.llEsfp);
        aniLlTop = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.descend);
        aniLlIntj = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlIntj.setStartOffset(200);
        aniLlIntp = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlIntp.setStartOffset(300);
        aniLlEntj = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlEntj.setStartOffset(400);
        aniLlEntp = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlEntp.setStartOffset(500);
        aniLlInfj = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlInfj.setStartOffset(600);
        aniLlInfp = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlInfp.setStartOffset(700);
        aniLlEnfj = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlEnfj.setStartOffset(800);
        aniLlEnfp = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlEnfp.setStartOffset(900);
        aniLlIstj = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlIstj.setStartOffset(1000);
        aniLlIsfj = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlIsfj.setStartOffset(1100);
        aniLlEstj = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlEstj.setStartOffset(1200);
        aniLlEsfj = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlEsfj.setStartOffset(1300);
        aniLlIstp = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlIstp.setStartOffset(1400);
        aniLlIsfp = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlIsfp.setStartOffset(1500);
        aniLlEstp = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlEstp.setStartOffset(1600);
        aniLlEsfp = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.ascend_fast);
        aniLlEsfp.setStartOffset(1700);
        llTop.startAnimation(aniLlTop);
        llIntj.startAnimation(aniLlIntj);
        llIntp.startAnimation(aniLlIntp);
        llEntj.startAnimation(aniLlEntj);
        llEntp.startAnimation(aniLlEntp);
        llInfj.startAnimation(aniLlInfj);
        llInfp.startAnimation(aniLlInfp);
        llEnfj.startAnimation(aniLlEnfj);
        llEnfp.startAnimation(aniLlEnfp);
        llIstj.startAnimation(aniLlIstj);
        llIsfj.startAnimation(aniLlIsfj);
        llEstj.startAnimation(aniLlEstj);
        llEsfj.startAnimation(aniLlEsfj);
        llIstp.startAnimation(aniLlIstp);
        llIsfp.startAnimation(aniLlIsfp);
        llEstp.startAnimation(aniLlEstp);
        llEsfp.startAnimation(aniLlEsfp);

        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(YoutubeAllActivity.this, R.anim.scale);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 하단 버튼들
                case R.id.btnSidemenu:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnHome:
                    Intent intentHome = new Intent(YoutubeAllActivity.this, MainActivity.class);
                    intentHome.putExtra("nick", strNickname);
                    intentHome.putExtra("mbti", strMbti);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnAccount:
                    Intent intentAccount = new Intent(YoutubeAllActivity.this, AccountActivity.class);
                    intentAccount.putExtra("nick", strNickname);
                    intentAccount.putExtra("mbti", strMbti);
                    startActivity(intentAccount);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                // MBTI 버튼
                case R.id.tvIntjTitle:
                    llIntj.startAnimation(aniTouch);
                    Intent intentIntj = new Intent(YoutubeAllActivity.this, IntjActivity.class);
                    intentIntj.putExtra("nick", strNickname);
                    intentIntj.putExtra("mbti", strMbti);
                    startActivity(intentIntj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIntpTitle:
                    llIntp.startAnimation(aniTouch);
                    Intent intentIntp = new Intent(YoutubeAllActivity.this, IntpActivity.class);
                    intentIntp.putExtra("nick", strNickname);
                    intentIntp.putExtra("mbti", strMbti);
                    startActivity(intentIntp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEntjTitle:
                    llEntj.startAnimation(aniTouch);
                    Intent intentEntj = new Intent(YoutubeAllActivity.this, EntjActivity.class);
                    intentEntj.putExtra("nick", strNickname);
                    intentEntj.putExtra("mbti", strMbti);
                    startActivity(intentEntj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEntpTitle:
                    llEntp.startAnimation(aniTouch);
                    Intent intentEntp = new Intent(YoutubeAllActivity.this, EntpActivity.class);
                    intentEntp.putExtra("nick", strNickname);
                    intentEntp.putExtra("mbti", strMbti);
                    startActivity(intentEntp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvInfjTitle:
                    llInfj.startAnimation(aniTouch);
                    Intent intentInfj = new Intent(YoutubeAllActivity.this, InfjActivity.class);
                    intentInfj.putExtra("nick", strNickname);
                    intentInfj.putExtra("mbti", strMbti);
                    startActivity(intentInfj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvInfpTitle:
                    llInfp.startAnimation(aniTouch);
                    Intent intentInfp = new Intent(YoutubeAllActivity.this, InfpActivity.class);
                    intentInfp.putExtra("nick", strNickname);
                    intentInfp.putExtra("mbti", strMbti);
                    startActivity(intentInfp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEnfjTitle:
                    llEnfj.startAnimation(aniTouch);
                    Intent intentEnfj = new Intent(YoutubeAllActivity.this, EnfjActivity.class);
                    intentEnfj.putExtra("nick", strNickname);
                    intentEnfj.putExtra("mbti", strMbti);
                    startActivity(intentEnfj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEnfpTitle:
                    llEnfp.startAnimation(aniTouch);
                    Intent intentEnfp = new Intent(YoutubeAllActivity.this, EnfpActivity.class);
                    intentEnfp.putExtra("nick", strNickname);
                    intentEnfp.putExtra("mbti", strMbti);
                    startActivity(intentEnfp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIstjTitle:
                    llIstj.startAnimation(aniTouch);
                    Intent intentIstj = new Intent(YoutubeAllActivity.this, IstjActivity.class);
                    intentIstj.putExtra("nick", strNickname);
                    intentIstj.putExtra("mbti", strMbti);
                    startActivity(intentIstj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIsfjTitle:
                    llIsfj.startAnimation(aniTouch);
                    Intent intentIsfj = new Intent(YoutubeAllActivity.this, IsfjActivity.class);
                    intentIsfj.putExtra("nick", strNickname);
                    intentIsfj.putExtra("mbti", strMbti);
                    startActivity(intentIsfj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEstjTitle:
                    llEstj.startAnimation(aniTouch);
                    Intent intentEstj = new Intent(YoutubeAllActivity.this, EstjActivity.class);
                    intentEstj.putExtra("nick", strNickname);
                    intentEstj.putExtra("mbti", strMbti);
                    startActivity(intentEstj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEsfjTitle:
                    llEsfj.startAnimation(aniTouch);
                    Intent intentEsfj = new Intent(YoutubeAllActivity.this, EsfjActivity.class);
                    intentEsfj.putExtra("nick", strNickname);
                    intentEsfj.putExtra("mbti", strMbti);
                    startActivity(intentEsfj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIstpTitle:
                    llIstp.startAnimation(aniTouch);
                    Intent intentIstp = new Intent(YoutubeAllActivity.this, IstpActivity.class);
                    intentIstp.putExtra("nick", strNickname);
                    intentIstp.putExtra("mbti", strMbti);
                    startActivity(intentIstp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIsfpTitle:
                    llIsfp.startAnimation(aniTouch);
                    Intent intentIsfp = new Intent(YoutubeAllActivity.this, IsfpActivity.class);
                    intentIsfp.putExtra("nick", strNickname);
                    intentIsfp.putExtra("mbti", strMbti);
                    startActivity(intentIsfp);
                    finish();
                    break;
                case R.id.tvEstpTitle:
                    llEstp.startAnimation(aniTouch);
                    Intent intentEstp = new Intent(YoutubeAllActivity.this, EstpActivity.class);
                    intentEstp.putExtra("nick", strNickname);
                    intentEstp.putExtra("mbti", strMbti);
                    startActivity(intentEstp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEsfpTitle:
                    llEsfp.startAnimation(aniTouch);
                    Intent intentEsfp = new Intent(YoutubeAllActivity.this, EsfpActivity.class);
                    intentEsfp.putExtra("nick", strNickname);
                    intentEsfp.putExtra("mbti", strMbti);
                    startActivity(intentEsfp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                // 분야별 컨텐츠
                case R.id.tvIntjContent:
                    llIntj.startAnimation(aniTouch);
                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
                    Uri uri1 = Uri.parse("https://www.youtube.com/results?search_query=%EC%8B%9C%EA%B3%A8%EC%A5%90%EC%9D%98+%EB%8F%84%EC%8B%9C%EC%83%9D%ED%99%9C");
                    intent1.setData(uri1);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvIntpContent:
                    llIntp.startAnimation(aniTouch);
                    Intent intent2 = new Intent(Intent.ACTION_VIEW);
                    Uri uri2 = Uri.parse("https://www.youtube.com/results?search_query=%EB%AC%B4%EB%B9%84%ED%8A%B8%EB%A6%BD");
                    intent2.setData(uri2);
                    startActivity(intent2);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvEntjContent:
                    llEntj.startAnimation(aniTouch);
                    Intent intent3 = new Intent(Intent.ACTION_VIEW);
                    Uri uri3 = Uri.parse("https://www.youtube.com/results?search_query=%EC%84%B8%EB%B0%94%EC%8B%9C%EA%B0%95%EC%97%B0");
                    intent3.setData(uri3);
                    startActivity(intent3);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvEntpContent:
                    llEntp.startAnimation(aniTouch);
                    Intent intent4 = new Intent(Intent.ACTION_VIEW);
                    Uri uri4 = Uri.parse("https://www.youtube.com/results?search_query=%EA%B3%B5%EB%B6%80%EC%99%95%EC%B0%90%EC%B2%9C%EC%9E%AC+%ED%99%8D%EC%A7%84%EA%B2%BD");
                    intent4.setData(uri4);
                    startActivity(intent4);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvInfjContent:
                    llInfj.startAnimation(aniTouch);
                    Intent intent5 = new Intent(Intent.ACTION_VIEW);
                    Uri uri5 = Uri.parse("https://www.youtube.com/results?search_query=%EB%94%B0%EB%93%AF%ED%95%9C+%EB%AA%A9%EC%86%8C%EB%A6%AC+%ED%98%84%EC%A4%80");
                    intent5.setData(uri5);
                    startActivity(intent5);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvInfpContent:
                    llInfp.startAnimation(aniTouch);
                    Intent intent6 = new Intent(Intent.ACTION_VIEW);
                    Uri uri6 = Uri.parse("https://www.youtube.com/results?search_query=%EC%95%85%EB%8F%99+%EA%B9%80%EB%B8%94%EB%A3%A8");
                    intent6.setData(uri6);
                    startActivity(intent6);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvEnfjContent:
                    llEnfj.startAnimation(aniTouch);
                    Intent intent7 = new Intent(Intent.ACTION_VIEW);
                    Uri uri7 = Uri.parse("https://www.youtube.com/results?search_query=%EC%97%94%EC%A1%B0%EC%9D%B4+%EC%BB%A4%ED%94%8C");
                    intent7.setData(uri7);
                    startActivity(intent7);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvEnfpContent:
                    llEnfp.startAnimation(aniTouch);
                    Intent intent8 = new Intent(Intent.ACTION_VIEW);
                    Uri uri8 = Uri.parse("https://www.youtube.com/results?search_query=%EB%B3%B4%EA%B3%A0%EC%8B%B6%EC%A7%84%EC%95%84");
                    intent8.setData(uri8);
                    startActivity(intent8);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvIstjContent:
                    llIstj.startAnimation(aniTouch);
                    Intent intent9 = new Intent(Intent.ACTION_VIEW);
                    Uri uri9 = Uri.parse("https://www.youtube.com/results?search_query=%ED%95%9C%EB%AC%B8%EC%B2%A0+TV");
                    intent9.setData(uri9);
                    startActivity(intent9);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvIsfjContent:
                    llIsfj.startAnimation(aniTouch);
                    Intent intent10 = new Intent(Intent.ACTION_VIEW);
                    Uri uri10 = Uri.parse("https://www.youtube.com/results?search_query=%EC%86%8D%EC%82%AD%EC%9D%B4%EB%8A%94+%EB%AA%BD%EC%9E%90");
                    intent10.setData(uri10);
                    startActivity(intent10);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvEstjContent:
                    llEstj.startAnimation(aniTouch);
                    Intent intent11 = new Intent(Intent.ACTION_VIEW);
                    Uri uri11 = Uri.parse("https://www.youtube.com/results?search_query=%EC%9D%B4%EC%8A%88%ED%85%94%EB%9F%AC");
                    intent11.setData(uri11);
                    startActivity(intent11);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvEsfjContent:
                    llEsfj.startAnimation(aniTouch);
                    Intent intent12 = new Intent(Intent.ACTION_VIEW);
                    Uri uri12 = Uri.parse("https://www.youtube.com/results?search_query=%EA%B9%80%EC%9C%A0%EC%8B%A0");
                    intent12.setData(uri12);
                    startActivity(intent12);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvIstpContent:
                    llIstp.startAnimation(aniTouch);
                    Intent intent13 = new Intent(Intent.ACTION_VIEW);
                    Uri uri13 = Uri.parse("https://www.youtube.com/results?search_query=%EB%BD%80%EB%AA%A8");
                    intent13.setData(uri13);
                    startActivity(intent13);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvIsfpContent:
                    llIsfp.startAnimation(aniTouch);
                    Intent intent14 = new Intent(Intent.ACTION_VIEW);
                    Uri uri14 = Uri.parse("https://www.youtube.com/results?search_query=%EC%9C%A0%ED%8A%B8%EB%A3%A8");
                    intent14.setData(uri14);
                    startActivity(intent14);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvEstpContent:
                    llEstp.startAnimation(aniTouch);
                    Intent intent15 = new Intent(Intent.ACTION_VIEW);
                    Uri uri15 = Uri.parse("https://www.youtube.com/results?search_query=%EB%95%85%EB%81%84%EB%B6%80%EB%B6%80");
                    intent15.setData(uri15);
                    startActivity(intent15);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
                case R.id.tvEsfpContent:
                    llEsfp.startAnimation(aniTouch);
                    Intent intent16 = new Intent(Intent.ACTION_VIEW);
                    Uri uri16 = Uri.parse("https://www.youtube.com/results?search_query=%EC%A1%B0%ED%9A%A8%EC%A7%84");
                    intent16.setData(uri16);
                    startActivity(intent16);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    break;
            }
        }
    };

    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(YoutubeAllActivity.this, MainActivity.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
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
                tvMbti.setText("ENFP");
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
                Intent intentProfile = new Intent(YoutubeAllActivity.this, ProfileActivity.class);
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

    // Match page
    public void pageMatch() {
        Intent intentMatch = new Intent(YoutubeAllActivity.this, MatchAllActivity.class);
        intentMatch.putExtra("nick", strNickname);
        intentMatch.putExtra("mbti", strMbti);
        startActivity(intentMatch);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Game page
    public void pageGame() {
        Intent intentGame = new Intent(YoutubeAllActivity.this, GameAllActivity.class);
        intentGame.putExtra("nick", strNickname);
        intentGame.putExtra("mbti", strMbti);
        startActivity(intentGame);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Book page
    public void pageBook() {
        Intent intentBook = new Intent(YoutubeAllActivity.this, BookAllActivity.class);
        intentBook.putExtra("nick", strNickname);
        intentBook.putExtra("mbti", strMbti);
        startActivity(intentBook);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Youtube page
    public void pageYoutube() {
        Intent intentYoutube = new Intent(YoutubeAllActivity.this, YoutubeAllActivity.class);
        intentYoutube.putExtra("nick", strNickname);
        intentYoutube.putExtra("mbti", strMbti);
        startActivity(intentYoutube);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Music page
    public void pageMusic() {
        Intent intentMusic = new Intent(YoutubeAllActivity.this, MusicAllActivity.class);
        intentMusic.putExtra("nick", strNickname);
        intentMusic.putExtra("mbti", strMbti);
        startActivity(intentMusic);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Movie page
    public void pageMovie() {
        Intent intentMovie = new Intent(YoutubeAllActivity.this, MovieAllActivity.class);
        intentMovie.putExtra("nick", strNickname);
        intentMovie.putExtra("mbti", strMbti);
        startActivity(intentMovie);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Drama page
    public void pageDrama() {
        Intent intentDrama = new Intent(YoutubeAllActivity.this, DramaAllActivity.class);
        intentDrama.putExtra("nick", strNickname);
        intentDrama.putExtra("mbti", strMbti);
        startActivity(intentDrama);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Job page
    public void pageJob() {
        Intent intentJob = new Intent(YoutubeAllActivity.this, JobAllActivity.class);
        intentJob.putExtra("nick", strNickname);
        intentJob.putExtra("mbti", strMbti);
        startActivity(intentJob);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

}