package com.luan.mbti;

import android.app.Dialog;
import android.content.Intent;
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

public class MatchAllActivity extends AppCompatActivity {

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
        setContentView(R.layout.match_all);

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
        aniLlTop = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.descend);
        aniLlIntj = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlIntj.setStartOffset(200);
        aniLlIntp = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlIntp.setStartOffset(300);
        aniLlEntj = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlEntj.setStartOffset(400);
        aniLlEntp = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlEntp.setStartOffset(500);
        aniLlInfj = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlInfj.setStartOffset(600);
        aniLlInfp = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlInfp.setStartOffset(700);
        aniLlEnfj = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlEnfj.setStartOffset(800);
        aniLlEnfp = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlEnfp.setStartOffset(900);
        aniLlIstj = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlIstj.setStartOffset(1000);
        aniLlIsfj = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlIsfj.setStartOffset(1100);
        aniLlEstj = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlEstj.setStartOffset(1200);
        aniLlEsfj = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlEsfj.setStartOffset(1300);
        aniLlIstp = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlIstp.setStartOffset(1400);
        aniLlIsfp = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlIsfp.setStartOffset(1500);
        aniLlEstp = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
        aniLlEstp.setStartOffset(1600);
        aniLlEsfp = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.ascend_fast);
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
        aniTouch = AnimationUtils.loadAnimation(MatchAllActivity.this, R.anim.scale);

    }

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 하단 버튼들
                case R.id.btnSidemenu:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnHome:
                    Intent intentHome = new Intent(MatchAllActivity.this, MainActivity.class);
                    intentHome.putExtra("nick", strNickname);
                    intentHome.putExtra("mbti", strMbti);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnAccount:
                    Intent intentAccount = new Intent(MatchAllActivity.this, AccountActivity.class);
                    intentAccount.putExtra("nick", strNickname);
                    intentAccount.putExtra("mbti", strMbti);
                    startActivity(intentAccount);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                // MBTI 버튼
                case R.id.tvIntjTitle:
                    llIntj.startAnimation(aniTouch);
                    Intent intentIntj = new Intent(MatchAllActivity.this, IntjActivity.class);
                    intentIntj.putExtra("nick", strNickname);
                    intentIntj.putExtra("mbti", strMbti);
                    startActivity(intentIntj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIntpTitle:
                    llIntp.startAnimation(aniTouch);
                    Intent intentIntp = new Intent(MatchAllActivity.this, IntpActivity.class);
                    intentIntp.putExtra("nick", strNickname);
                    intentIntp.putExtra("mbti", strMbti);
                    startActivity(intentIntp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEntjTitle:
                    llEntj.startAnimation(aniTouch);
                    Intent intentEntj = new Intent(MatchAllActivity.this, EntjActivity.class);
                    intentEntj.putExtra("nick", strNickname);
                    intentEntj.putExtra("mbti", strMbti);
                    startActivity(intentEntj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEntpTitle:
                    llEntp.startAnimation(aniTouch);
                    Intent intentEntp = new Intent(MatchAllActivity.this, EntpActivity.class);
                    intentEntp.putExtra("nick", strNickname);
                    intentEntp.putExtra("mbti", strMbti);
                    startActivity(intentEntp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvInfjTitle:
                    llInfj.startAnimation(aniTouch);
                    Intent intentInfj = new Intent(MatchAllActivity.this, InfjActivity.class);
                    intentInfj.putExtra("nick", strNickname);
                    intentInfj.putExtra("mbti", strMbti);
                    startActivity(intentInfj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvInfpTitle:
                    llInfp.startAnimation(aniTouch);
                    Intent intentInfp = new Intent(MatchAllActivity.this, InfpActivity.class);
                    intentInfp.putExtra("nick", strNickname);
                    intentInfp.putExtra("mbti", strMbti);
                    startActivity(intentInfp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEnfjTitle:
                    llEnfj.startAnimation(aniTouch);
                    Intent intentEnfj = new Intent(MatchAllActivity.this, EnfjActivity.class);
                    intentEnfj.putExtra("nick", strNickname);
                    intentEnfj.putExtra("mbti", strMbti);
                    startActivity(intentEnfj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEnfpTitle:
                    llEnfp.startAnimation(aniTouch);
                    Intent intentEnfp = new Intent(MatchAllActivity.this, EnfpActivity.class);
                    intentEnfp.putExtra("nick", strNickname);
                    intentEnfp.putExtra("mbti", strMbti);
                    startActivity(intentEnfp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIstjTitle:
                    llIstj.startAnimation(aniTouch);
                    Intent intentIstj = new Intent(MatchAllActivity.this, IstjActivity.class);
                    intentIstj.putExtra("nick", strNickname);
                    intentIstj.putExtra("mbti", strMbti);
                    startActivity(intentIstj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIsfjTitle:
                    llIsfj.startAnimation(aniTouch);
                    Intent intentIsfj = new Intent(MatchAllActivity.this, IsfjActivity.class);
                    intentIsfj.putExtra("nick", strNickname);
                    intentIsfj.putExtra("mbti", strMbti);
                    startActivity(intentIsfj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEstjTitle:
                    llEstj.startAnimation(aniTouch);
                    Intent intentEstj = new Intent(MatchAllActivity.this, EstjActivity.class);
                    intentEstj.putExtra("nick", strNickname);
                    intentEstj.putExtra("mbti", strMbti);
                    startActivity(intentEstj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEsfjTitle:
                    llEsfj.startAnimation(aniTouch);
                    Intent intentEsfj = new Intent(MatchAllActivity.this, EsfjActivity.class);
                    intentEsfj.putExtra("nick", strNickname);
                    intentEsfj.putExtra("mbti", strMbti);
                    startActivity(intentEsfj);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIstpTitle:
                    llIstp.startAnimation(aniTouch);
                    Intent intentIstp = new Intent(MatchAllActivity.this, IstpActivity.class);
                    intentIstp.putExtra("nick", strNickname);
                    intentIstp.putExtra("mbti", strMbti);
                    startActivity(intentIstp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvIsfpTitle:
                    llIsfp.startAnimation(aniTouch);
                    Intent intentIsfp = new Intent(MatchAllActivity.this, IsfpActivity.class);
                    intentIsfp.putExtra("nick", strNickname);
                    intentIsfp.putExtra("mbti", strMbti);
                    startActivity(intentIsfp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEstpTitle:
                    llEstp.startAnimation(aniTouch);
                    Intent intentEstp = new Intent(MatchAllActivity.this, EstpActivity.class);
                    intentEstp.putExtra("nick", strNickname);
                    intentEstp.putExtra("mbti", strMbti);
                    startActivity(intentEstp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.tvEsfpTitle:
                    llEsfp.startAnimation(aniTouch);
                    Intent intentEsfp = new Intent(MatchAllActivity.this, EsfpActivity.class);
                    intentEsfp.putExtra("nick", strNickname);
                    intentEsfp.putExtra("mbti", strMbti);
                    startActivity(intentEsfp);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
            }
        }
    };

    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(MatchAllActivity.this, MainActivity.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }

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
                Intent intentProfile = new Intent(MatchAllActivity.this, ProfileActivity.class);
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
        Intent intentMatch = new Intent(MatchAllActivity.this, MatchAllActivity.class);
        intentMatch.putExtra("nick", strNickname);
        intentMatch.putExtra("mbti", strMbti);
        startActivity(intentMatch);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Game page
    public void pageGame() {
        Intent intentGame = new Intent(MatchAllActivity.this, GameAllActivity.class);
        intentGame.putExtra("nick", strNickname);
        intentGame.putExtra("mbti", strMbti);
        startActivity(intentGame);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Book page
    public void pageBook() {
        Intent intentBook = new Intent(MatchAllActivity.this, BookAllActivity.class);
        intentBook.putExtra("nick", strNickname);
        intentBook.putExtra("mbti", strMbti);
        startActivity(intentBook);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Youtube page
    public void pageYoutube() {
        Intent intentYoutube = new Intent(MatchAllActivity.this, YoutubeAllActivity.class);
        intentYoutube.putExtra("nick", strNickname);
        intentYoutube.putExtra("mbti", strMbti);
        startActivity(intentYoutube);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Music page
    public void pageMusic() {
        Intent intentMusic = new Intent(MatchAllActivity.this, MusicAllActivity.class);
        intentMusic.putExtra("nick", strNickname);
        intentMusic.putExtra("mbti", strMbti);
        startActivity(intentMusic);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Movie page
    public void pageMovie() {
        Intent intentMovie = new Intent(MatchAllActivity.this, MovieAllActivity.class);
        intentMovie.putExtra("nick", strNickname);
        intentMovie.putExtra("mbti", strMbti);
        startActivity(intentMovie);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Drama page
    public void pageDrama() {
        Intent intentDrama = new Intent(MatchAllActivity.this, DramaAllActivity.class);
        intentDrama.putExtra("nick", strNickname);
        intentDrama.putExtra("mbti", strMbti);
        startActivity(intentDrama);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Job page
    public void pageJob() {
        Intent intentJob = new Intent(MatchAllActivity.this, JobAllActivity.class);
        intentJob.putExtra("nick", strNickname);
        intentJob.putExtra("mbti", strMbti);
        startActivity(intentJob);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

}