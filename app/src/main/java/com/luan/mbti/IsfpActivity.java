package com.luan.mbti;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
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
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class IsfpActivity extends AppCompatActivity {
    // DB 데이터
    String strNickname, strMbti;

    // 레이아웃 요소
    LinearLayout llBanner, llTitle, llAvatar;
    LinearLayout llMate, llGame, llBook, llYoutube, llMusic, llMovie, llDrama, llJob;
    Button btnMate, btnGame, btnBook, btnYoutube, btnMusic, btnMovie, btnDrama, btnJob;
    TextView tvMate, tvGame, tvBook, tvYoutube, tvMusic, tvMovie, tvDrama, tvJob;

    // 애니메이션
    Animation aniLlBanner, aniLlTitle, aniLlAvatar;
    Animation aniLlMate, aniLlGame, aniLlBook, aniLlYoutube, aniLlMusic, aniLlMovie, aniLlDrama, aniLlJob;
    Animation aniBtnMate, aniBtnGame, aniBtnBook, aniBtnYoutube, aniBtnMusic, aniBtnMovie, aniBtnDrama, aniBtnJob;
    Animation aniTvMate, aniTvGame, aniTvBook, aniTvYoutube, aniTvMusic, aniTvMovie, aniTvDrama, aniTvJob;
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
        setContentView(R.layout.layout_isfp);

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

        // 상단 배너
        llBanner = (LinearLayout) findViewById(R.id.llBanner);
        llTitle = (LinearLayout) findViewById(R.id.llTitle);
        llAvatar = (LinearLayout) findViewById(R.id.llAvatar);
        aniLlBanner = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.descend);
        aniLlTitle = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniLlAvatar = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniLlTitle.setStartOffset(200);
        aniLlAvatar.setStartOffset(400);
        llBanner.startAnimation(aniLlBanner);
        llTitle.startAnimation(aniLlTitle);
        llAvatar.startAnimation(aniLlAvatar);

        // 레이아웃
        llMate = (LinearLayout) findViewById(R.id.llMate);
        llGame = (LinearLayout) findViewById(R.id.llGame);
        llBook = (LinearLayout) findViewById(R.id.llBook);
        llYoutube = (LinearLayout) findViewById(R.id.llYoutube);
        llMusic = (LinearLayout) findViewById(R.id.llMusic);
        llMovie = (LinearLayout) findViewById(R.id.llMovie);
        llDrama = (LinearLayout) findViewById(R.id.llDrama);
        llJob = (LinearLayout) findViewById(R.id.llJob);

        // 레이아웃 애니메이션
        aniLlMate = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.ascend);
        aniLlGame = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.ascend);
        aniLlBook = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.ascend);
        aniLlYoutube = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.ascend);
        aniLlMusic = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.ascend);
        aniLlMovie = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.ascend);
        aniLlDrama = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.ascend);
        aniLlJob = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.ascend);
        aniLlMate.setStartOffset(200);
        aniLlGame.setStartOffset(400);
        aniLlBook.setStartOffset(600);
        aniLlYoutube.setStartOffset(800);
        aniLlMusic.setStartOffset(1000);
        aniLlMovie.setStartOffset(1200);
        aniLlDrama.setStartOffset(1400);
        aniLlJob.setStartOffset(1600);
        llMate.startAnimation(aniLlMate);
        llGame.startAnimation(aniLlGame);
        llBook.startAnimation(aniLlBook);
        llYoutube.startAnimation(aniLlYoutube);
        llMusic.startAnimation(aniLlMusic);
        llMovie.startAnimation(aniLlMovie);
        llDrama.startAnimation(aniLlDrama);
        llJob.startAnimation(aniLlJob);

        // 버튼 & 텍스트
        btnMate = (Button) findViewById(R.id.btnMate);
        btnGame = (Button) findViewById(R.id.btnGame);
        btnBook = (Button) findViewById(R.id.btnBook);
        btnYoutube = (Button) findViewById(R.id.btnYoutube);
        btnMusic = (Button) findViewById(R.id.btnMusic);
        btnMovie = (Button) findViewById(R.id.btnMovie);
        btnDrama = (Button) findViewById(R.id.btnDrama);
        btnJob = (Button) findViewById(R.id.btnJob);
        btnMate.setOnClickListener(mClickListener);
        btnGame.setOnClickListener(mClickListener);
        btnBook.setOnClickListener(mClickListener);
        btnYoutube.setOnClickListener(mClickListener);
        btnMusic.setOnClickListener(mClickListener);
        btnMovie.setOnClickListener(mClickListener);
        btnDrama.setOnClickListener(mClickListener);
        btnJob.setOnClickListener(mClickListener);
        tvMate = (TextView) findViewById(R.id.tvMate);
        tvGame = (TextView) findViewById(R.id.tvGame);
        tvBook = (TextView) findViewById(R.id.tvBook);
        tvYoutube = (TextView) findViewById(R.id.tvYoutube);
        tvMusic = (TextView) findViewById(R.id.tvMusic);
        tvMovie = (TextView) findViewById(R.id.tvMovie);
        tvDrama = (TextView) findViewById(R.id.tvDrama);
        tvJob = (TextView) findViewById(R.id.tvJob);

        // 버튼 & 텍스트 애니메이션
        aniBtnMate = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniBtnGame = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniBtnBook = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniBtnYoutube = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniBtnMusic = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniBtnMovie = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniBtnDrama = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniBtnJob = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniBtnMate.setStartOffset(500);
        aniBtnGame.setStartOffset(700);
        aniBtnBook.setStartOffset(900);
        aniBtnYoutube.setStartOffset(1100);
        aniBtnMusic.setStartOffset(1300);
        aniBtnMovie.setStartOffset(1500);
        aniBtnDrama.setStartOffset(1700);
        aniBtnJob.setStartOffset(1900);
        btnMate.startAnimation(aniBtnMate);
        btnGame.startAnimation(aniBtnGame);
        btnBook.startAnimation(aniBtnBook);
        btnYoutube.startAnimation(aniBtnYoutube);
        btnMusic.startAnimation(aniBtnMusic);
        btnMovie.startAnimation(aniBtnMovie);
        btnDrama.startAnimation(aniBtnDrama);
        btnJob.startAnimation(aniBtnJob);

        aniTvMate = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniTvGame = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniTvBook = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniTvYoutube = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniTvMusic = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniTvMovie = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniTvDrama = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniTvJob = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.fadein);
        aniTvMate.setStartOffset(600);
        aniTvGame.setStartOffset(800);
        aniTvBook.setStartOffset(1000);
        aniTvYoutube.setStartOffset(1200);
        aniTvMusic.setStartOffset(1400);
        aniTvMovie.setStartOffset(1600);
        aniTvDrama.setStartOffset(1800);
        aniTvJob.setStartOffset(2000);
        tvMate.startAnimation(aniTvMate);
        tvGame.startAnimation(aniTvGame);
        tvBook.startAnimation(aniTvBook);
        tvYoutube.startAnimation(aniTvYoutube);
        tvMusic.startAnimation(aniTvMusic);
        tvMovie.startAnimation(aniTvMovie);
        tvDrama.startAnimation(aniTvDrama);
        tvJob.startAnimation(aniTvJob);

        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(IsfpActivity.this, R.anim.scale);

        // 터치 할 때 색 변환
        setColorStateList(btnMate, ContextCompat.getColor(this, R.color.mbti_red), btnMate.getCurrentTextColor());
        setColorStateList(btnGame, ContextCompat.getColor(this, R.color.mbti_yellow), btnGame.getCurrentTextColor());
        setColorStateList(btnBook, ContextCompat.getColor(this, R.color.mbti_green), btnBook.getCurrentTextColor());
        setColorStateList(btnYoutube, ContextCompat.getColor(this, R.color.mbti_orange), btnYoutube.getCurrentTextColor());
        setColorStateList(btnMusic, ContextCompat.getColor(this, R.color.mbti_red), btnMusic.getCurrentTextColor());
        setColorStateList(btnMovie, ContextCompat.getColor(this, R.color.mbti_yellow), btnMovie.getCurrentTextColor());
        setColorStateList(btnDrama, ContextCompat.getColor(this, R.color.mbti_green), btnDrama.getCurrentTextColor());
        setColorStateList(btnJob, ContextCompat.getColor(this, R.color.mbti_orange), btnJob.getCurrentTextColor());

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
                case R.id.btnMate:
                    llMate.startAnimation(aniTouch);
                    btnMate.startAnimation(aniTouch);
                    tvMate.startAnimation(aniTouch);
                    pageMatch();
                    break;
                case R.id.btnGame:
                    llGame.startAnimation(aniTouch);
                    btnGame.startAnimation(aniTouch);
                    tvGame.startAnimation(aniTouch);
                    pageGame();
                    break;
                case R.id.btnBook:
                    llBook.startAnimation(aniTouch);
                    btnBook.startAnimation(aniTouch);
                    tvBook.startAnimation(aniTouch);
                    pageBook();
                    break;
                case R.id.btnYoutube:
                    llYoutube.startAnimation(aniTouch);
                    btnYoutube.startAnimation(aniTouch);
                    tvYoutube.startAnimation(aniTouch);
                    pageYoutube();
                    break;
                case R.id.btnMusic:
                    llMusic.startAnimation(aniTouch);
                    btnMusic.startAnimation(aniTouch);
                    tvMusic.startAnimation(aniTouch);
                    pageMusic();
                    break;
                case R.id.btnMovie:
                    llMovie.startAnimation(aniTouch);
                    btnMovie.startAnimation(aniTouch);
                    tvMovie.startAnimation(aniTouch);
                    pageMovie();
                    break;
                case R.id.btnDrama:
                    llDrama.startAnimation(aniTouch);
                    btnDrama.startAnimation(aniTouch);
                    tvDrama.startAnimation(aniTouch);
                    pageDrama();
                    break;
                case R.id.btnJob:
                    llJob.startAnimation(aniTouch);
                    btnJob.startAnimation(aniTouch);
                    tvJob.startAnimation(aniTouch);
                    pageJob();
                    break;
                case R.id.btnSidemenu:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnHome:
                    Intent intentHome = new Intent(IsfpActivity.this, MainActivity.class);
                    intentHome.putExtra("nick", strNickname);
                    intentHome.putExtra("mbti", strMbti);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnAccount:
                    Intent intentAccount = new Intent(IsfpActivity.this, AccountActivity.class);
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
        super.onBackPressed();
        Intent intentBack = new Intent(IsfpActivity.this, MainActivity.class);
        intentBack.putExtra("nick", strNickname);
        intentBack.putExtra("mbti", strMbti);
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
    private void dialogProfile() {
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
                Intent intentProfile = new Intent(IsfpActivity.this, ProfileActivity.class);
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
        Intent intentMatch = new Intent(IsfpActivity.this, MatchAllActivity.class);
        intentMatch.putExtra("nick", strNickname);
        intentMatch.putExtra("mbti", strMbti);
        startActivity(intentMatch);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Game page
    public void pageGame() {
        Intent intentGame = new Intent(IsfpActivity.this, GameAllActivity.class);
        intentGame.putExtra("nick", strNickname);
        intentGame.putExtra("mbti", strMbti);
        startActivity(intentGame);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Book page
    public void pageBook() {
        Intent intentBook = new Intent(IsfpActivity.this, BookAllActivity.class);
        intentBook.putExtra("nick", strNickname);
        intentBook.putExtra("mbti", strMbti);
        startActivity(intentBook);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Youtube page
    public void pageYoutube() {
        Intent intentYoutube = new Intent(IsfpActivity.this, YoutubeAllActivity.class);
        intentYoutube.putExtra("nick", strNickname);
        intentYoutube.putExtra("mbti", strMbti);
        startActivity(intentYoutube);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Music page
    public void pageMusic() {
        Intent intentMusic = new Intent(IsfpActivity.this, MusicAllActivity.class);
        intentMusic.putExtra("nick", strNickname);
        intentMusic.putExtra("mbti", strMbti);
        startActivity(intentMusic);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Movie page
    public void pageMovie() {
        Intent intentMovie = new Intent(IsfpActivity.this, MovieAllActivity.class);
        intentMovie.putExtra("nick", strNickname);
        intentMovie.putExtra("mbti", strMbti);
        startActivity(intentMovie);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Drama page
    public void pageDrama() {
        Intent intentDrama = new Intent(IsfpActivity.this, DramaAllActivity.class);
        intentDrama.putExtra("nick", strNickname);
        intentDrama.putExtra("mbti", strMbti);
        startActivity(intentDrama);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Job page
    public void pageJob() {
        Intent intentJob = new Intent(IsfpActivity.this, JobAllActivity.class);
        intentJob.putExtra("nick", strNickname);
        intentJob.putExtra("mbti", strMbti);
        startActivity(intentJob);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
}