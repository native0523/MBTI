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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.drawerlayout.widget.DrawerLayout;

public class AccountActivity extends AppCompatActivity {
    // DB 데이터
    String strNickname, strMbti;

    // 레이아웃 요소
    LinearLayout llTitle, llMyInfo, llMyMbti;
    TextView tvTitle1, tvTitle2;
    TextView tvCurrentNick1, tvCurrentNick2, tvCurrentMbti1, tvCurrentMbti2;
    TextView tvYourMbti1, tvYourMbti2;
    LinearLayout llChange, llReturn;
    Button btnChange, btnReturn;

    // 애니메이션
    Animation aniLlTitle, aniLlMyInfo, aniLlMyMbti;
    Animation aniTvTitle1, aniTvTitle2;
    Animation aniTvCurrentNick1, aniTvCurrentNick2, aniTvCurrentMbti1, aniTvCurrentMbti2;
    Animation aniTvYourMbti1, aniTvYourMbti2;
    Animation aniLlChange, aniLlReturn;
    Animation aniBtnChange, aniBtnReturn;
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
        setContentView(R.layout.activity_account);

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
        llMyInfo = (LinearLayout) findViewById(R.id.llMyInfo);
        llMyMbti = (LinearLayout) findViewById(R.id.llMyMbti);
        llChange = (LinearLayout) findViewById(R.id.llChange);
        llReturn = (LinearLayout) findViewById(R.id.llReturn);

        // 레이아웃 애니메이션
        aniLlTitle = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.descend);
        aniLlMyInfo = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.ascend_fast);
        aniLlMyMbti = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.ascend_fast);
        aniLlChange = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.ascend_fast);
        aniLlReturn = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.ascend_fast);
        aniLlMyInfo.setStartOffset(200);
        aniLlMyMbti.setStartOffset(400);
        aniLlChange.setStartOffset(600);
        aniLlReturn.setStartOffset(700);
        llTitle.startAnimation(aniLlTitle);
        llMyInfo.startAnimation(aniLlMyInfo);
        llMyMbti.startAnimation(aniLlMyMbti);
        llChange.startAnimation(aniLlChange);
        llReturn.startAnimation(aniLlReturn);

        // 버튼 & 텍스트
        tvTitle1 = (TextView) findViewById(R.id.tvTitle1);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        tvCurrentNick1 = (TextView) findViewById(R.id.tvCurrentNick1);
        tvCurrentNick2 = (TextView) findViewById(R.id.tvCurrentNick2);
        tvCurrentMbti1 = (TextView) findViewById(R.id.tvCurrentMbti1);
        tvCurrentMbti2 = (TextView) findViewById(R.id.tvCurrentMbti2);
        tvYourMbti1 = (TextView) findViewById(R.id.tvYourMbti1);
        tvYourMbti2 = (TextView) findViewById(R.id.tvYourMbti2);
        btnChange = (Button) findViewById(R.id.btnChange);
        btnReturn = (Button) findViewById(R.id.btnReturn);
        btnChange.setOnClickListener(mClickListener);
        btnReturn.setOnClickListener(mClickListener);

        // DB에서 가져온 닉네임 & MBTI 데이터를 텍스트뷰에 세팅
        if (strNickname == null) {
            tvCurrentNick2.setText("닉네임");
        } else {
            tvCurrentNick2.setText(strNickname);
        }
        if (strMbti == null) {
            tvCurrentMbti2.setText("MBTI");
        } else {
            tvCurrentMbti2.setText(strMbti);
        }

        // MBTI의 특성을 텍스트뷰에 세팅
        if (strMbti == null) {
            tvYourMbti2.setText("아직 MBTI를 선택하지 않으셨네요.");
        } else {
            switch (strMbti) {
                // 첫번째 줄
                case "INTJ":
                    tvYourMbti2.setText("용의주도한 전략가");
                    break;
                case "INTP":
                    tvYourMbti2.setText("논리적인 사색가");
                    break;
                case "ENTJ":
                    tvYourMbti2.setText("대담한 통솔자");
                    break;
                case "ENTP":
                    tvYourMbti2.setText("논쟁을 즐기는 변론가");
                    break;
                // 두번째 줄
                case "INFJ":
                    tvYourMbti2.setText("선의의 옹호자");
                    break;
                case "INFP":
                    tvYourMbti2.setText("열정적인 중재자");
                    break;
                case "ENFJ":
                    tvYourMbti2.setText("정의로운 사회운동가");
                    break;
                case "ENFP":
                    tvYourMbti2.setText("재기발랄한 활동가");
                    break;
                // 세번째 줄
                case "ISTJ":
                    tvYourMbti2.setText("청렴결백 논리주의자");
                    break;
                case "ISFJ":
                    tvYourMbti2.setText("용감한 수호자");
                    break;
                case "ESTJ":
                    tvYourMbti2.setText("엄격한 관리자");
                    break;
                case "ESFJ":
                    tvYourMbti2.setText("사교적인 외교관");
                    break;
                // 네번째 줄
                case "ISTP":
                    tvYourMbti2.setText("만능 재주꾼");
                    break;
                case "ISFP":
                    tvYourMbti2.setText("호기심 많은 예술가");
                    break;
                case "ESTP":
                    tvYourMbti2.setText("모험을 즐기는 사업가");
                    break;
                case "ESFP":
                    tvYourMbti2.setText("자유로운 영혼의 연예인");
                    break;
            }
        }

        // 버튼 & 텍스트 애니메이션
        aniTvTitle1 = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.descend_fast);
        aniTvTitle1.setStartOffset(400);
        aniTvTitle2 = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.ascend_fast);
        aniTvTitle2.setStartOffset(400);
        aniTvCurrentNick1 = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.fadein);
        aniTvCurrentNick2 = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.fadein);
        aniTvCurrentMbti1 = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.fadein);
        aniTvCurrentMbti2 = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.fadein);
        aniTvYourMbti1 = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.fadein);
        aniTvYourMbti2 = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.fadein);
        aniBtnChange = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.fadein);
        aniBtnReturn = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.fadein);
        aniTvCurrentNick1.setStartOffset(600);
        aniTvCurrentNick2.setStartOffset(700);
        aniTvCurrentMbti1.setStartOffset(800);
        aniTvCurrentMbti2.setStartOffset(900);
        aniTvYourMbti1.setStartOffset(1000);
        aniTvYourMbti2.setStartOffset(1100);
        aniBtnChange.setStartOffset(1300);
        aniBtnReturn.setStartOffset(1400);
        tvTitle1.startAnimation(aniTvTitle1);
        tvTitle2.startAnimation(aniTvTitle2);
        tvCurrentNick1.startAnimation(aniTvCurrentNick1);
        tvCurrentNick2.startAnimation(aniTvCurrentNick2);
        tvCurrentMbti1.startAnimation(aniTvCurrentMbti1);
        tvCurrentMbti2.startAnimation(aniTvCurrentMbti2);
        tvYourMbti1.startAnimation(aniTvYourMbti1);
        tvYourMbti2.startAnimation(aniTvYourMbti2);
        btnChange.startAnimation(aniBtnChange);
        btnReturn.startAnimation(aniBtnReturn);

        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(AccountActivity.this, R.anim.scale);

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
                case R.id.btnChange:
                    llChange.startAnimation(aniTouch);
                    btnChange.startAnimation(aniTouch);
                    dialogProfile();
                    break;
                case R.id.btnReturn:
                    llReturn.startAnimation(aniTouch);
                    btnReturn.startAnimation(aniTouch);
                    Intent intentReturn = new Intent(AccountActivity.this, MainActivity.class);
                    intentReturn.putExtra("nick", strNickname);
                    intentReturn.putExtra("mbti", strMbti);
                    startActivity(intentReturn);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                // 하단 버튼들
                case R.id.btnSidemenu:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnHome:
                    Intent intentHome = new Intent(AccountActivity.this, MainActivity.class);
                    intentHome.putExtra("nick", strNickname);
                    intentHome.putExtra("mbti", strMbti);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnAccount:
                    Intent intentAccount = new Intent(AccountActivity.this, AccountActivity.class);
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
        Intent intentBack = new Intent(AccountActivity.this, MainActivity.class);
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
                Intent intentProfile = new Intent(AccountActivity.this, ProfileActivity.class);
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
        Intent intentMatch = new Intent(AccountActivity.this, MatchAllActivity.class);
        intentMatch.putExtra("nick", strNickname);
        intentMatch.putExtra("mbti", strMbti);
        startActivity(intentMatch);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Game page
    public void pageGame() {
        Intent intentGame = new Intent(AccountActivity.this, GameAllActivity.class);
        intentGame.putExtra("nick", strNickname);
        intentGame.putExtra("mbti", strMbti);
        startActivity(intentGame);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Book page
    public void pageBook() {
        Intent intentBook = new Intent(AccountActivity.this, BookAllActivity.class);
        intentBook.putExtra("nick", strNickname);
        intentBook.putExtra("mbti", strMbti);
        startActivity(intentBook);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Youtube page
    public void pageYoutube() {
        Intent intentYoutube = new Intent(AccountActivity.this, YoutubeAllActivity.class);
        intentYoutube.putExtra("nick", strNickname);
        intentYoutube.putExtra("mbti", strMbti);
        startActivity(intentYoutube);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Music page
    public void pageMusic() {
        Intent intentMusic = new Intent(AccountActivity.this, MusicAllActivity.class);
        intentMusic.putExtra("nick", strNickname);
        intentMusic.putExtra("mbti", strMbti);
        startActivity(intentMusic);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Movie page
    public void pageMovie() {
        Intent intentMovie = new Intent(AccountActivity.this, MovieAllActivity.class);
        intentMovie.putExtra("nick", strNickname);
        intentMovie.putExtra("mbti", strMbti);
        startActivity(intentMovie);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Drama page
    public void pageDrama() {
        Intent intentDrama = new Intent(AccountActivity.this, DramaAllActivity.class);
        intentDrama.putExtra("nick", strNickname);
        intentDrama.putExtra("mbti", strMbti);
        startActivity(intentDrama);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Job page
    public void pageJob() {
        Intent intentJob = new Intent(AccountActivity.this, JobAllActivity.class);
        intentJob.putExtra("nick", strNickname);
        intentJob.putExtra("mbti", strMbti);
        startActivity(intentJob);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
}