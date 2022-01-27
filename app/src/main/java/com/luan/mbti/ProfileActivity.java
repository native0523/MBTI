package com.luan.mbti;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class ProfileActivity extends AppCompatActivity {
    // DB
    SQLiteDatabase db;
    MySQLiteOpenHelper helper;

    // DB 데이터
    String strNickname, strMbti;

    // 데이터 비교 변수
    String inputNickname, inputMbti;

    // 레이아웃 요소
    LinearLayout llTitle, llBefore, llAfter;
    TextView tvTitle1, tvTitle2;
    TextView tvBeforeNick1, tvBeforeNick2, tvBeforeMbti1, tvBeforeMbti2;
    TextView tvAfterNick, tvAfterMbti;
    EditText etAfterNick, etAfterMbti;
    LinearLayout llSetup, llSave;
    Button btnSetup, btnSave;
    String TAG = "Profile";

    // 애니메이션
    Animation aniLlTitle, aniLlBefore, aniLlAfter;
    Animation aniTvTitle1, aniTvTitle2;
    Animation aniTvBeforeNick1, aniTvBeforeNick2, aniTvBeforeMbti1, aniTvBeforeMbti2;
    Animation aniTvAfterNick, aniTvAfterMbti;
    Animation aniEtAfterNick, aniEtAfterMbti;
    Animation aniLlSetup, aniLlSave;
    Animation aniBtnSetup, aniBtnSave;
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
        setContentView(R.layout.activity_profile);

        // DB
        helper = new MySQLiteOpenHelper(ProfileActivity.this, "member.db", null, 1);
        
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
        llBefore = (LinearLayout) findViewById(R.id.llBefore);
        llAfter = (LinearLayout) findViewById(R.id.llAfter);
        llSetup = (LinearLayout) findViewById(R.id.llSetup);
        llSave = (LinearLayout) findViewById(R.id.llSave);

        // 레이아웃 애니메이션
        aniLlTitle = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.descend);
        aniLlBefore = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.ascend_fast);
        aniLlAfter = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.ascend_fast);
        aniLlSetup = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.ascend_fast);
        aniLlSave = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.ascend_fast);
        aniLlBefore.setStartOffset(200);
        aniLlAfter.setStartOffset(400);
        aniLlSetup.setStartOffset(600);
        aniLlSave.setStartOffset(700);
        llTitle.startAnimation(aniLlTitle);
        llBefore.startAnimation(aniLlBefore);
        llAfter.startAnimation(aniLlAfter);
        llSetup.startAnimation(aniLlSetup);
        llSave.startAnimation(aniLlSave);

        // 버튼 & 텍스트
        tvTitle1 = (TextView) findViewById(R.id.tvTitle1);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        tvBeforeNick1 = (TextView) findViewById(R.id.tvBeforeNick1);
        tvBeforeNick2 = (TextView) findViewById(R.id.tvBeforeNick2);
        tvBeforeMbti1 = (TextView) findViewById(R.id.tvBeforeMbti1);
        tvBeforeMbti2 = (TextView) findViewById(R.id.tvBeforeMbti2);
        tvAfterNick = (TextView) findViewById(R.id.tvAfterNick);
        tvAfterMbti = (TextView) findViewById(R.id.tvAfterMbti);
        etAfterNick = (EditText) findViewById(R.id.etAfterNick);
        etAfterMbti = (EditText) findViewById(R.id.etAfterMbti);
        btnSetup = (Button) findViewById(R.id.btnSetup);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSetup.setOnClickListener(mClickListener);
        btnSave.setOnClickListener(mClickListener);

        // DB에서 가져온 닉네임 & MBTI 데이터를 텍스트뷰에 세팅
        if (strNickname == null) {
            tvBeforeNick2.setText("닉네임");
        } else {
            tvBeforeNick2.setText(strNickname);
        }
        if (strMbti == null) {
            tvBeforeMbti2.setText("MBTI");
        } else {
            tvBeforeMbti2.setText(strMbti);
        }

        // 버튼 & 텍스트 애니메이션
        aniTvTitle1 = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.descend_fast);
        aniTvTitle1.setStartOffset(400);
        aniTvTitle2 = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.ascend_fast);
        aniTvTitle2.setStartOffset(400);
        aniTvBeforeNick1 = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniTvBeforeNick2 = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniTvBeforeMbti1 = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniTvBeforeMbti2 = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniTvAfterNick = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniEtAfterNick = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniTvAfterMbti = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniEtAfterMbti = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniBtnSetup = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniBtnSave = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.fadein);
        aniTvBeforeNick1.setStartOffset(600);
        aniTvBeforeNick2.setStartOffset(700);
        aniTvBeforeMbti1.setStartOffset(800);
        aniTvBeforeMbti2.setStartOffset(900);
        aniTvAfterNick.setStartOffset(1000);
        aniEtAfterNick.setStartOffset(1100);
        aniTvAfterMbti.setStartOffset(1200);
        aniEtAfterMbti.setStartOffset(1300);
        aniBtnSetup.setStartOffset(1400);
        aniBtnSave.setStartOffset(1500);
        tvTitle1.startAnimation(aniTvTitle1);
        tvTitle2.startAnimation(aniTvTitle2);
        tvBeforeNick1.startAnimation(aniTvBeforeNick1);
        tvBeforeNick2.startAnimation(aniTvBeforeNick2);
        tvBeforeMbti1.startAnimation(aniTvBeforeMbti1);
        tvBeforeMbti2.startAnimation(aniTvBeforeMbti2);
        tvAfterNick.startAnimation(aniTvAfterNick);
        tvAfterMbti.startAnimation(aniTvAfterMbti);
        etAfterNick.startAnimation(aniEtAfterNick);
        etAfterMbti.startAnimation(aniEtAfterMbti);
        btnSetup.startAnimation(aniBtnSetup);
        btnSave.startAnimation(aniBtnSave);

        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(ProfileActivity.this, R.anim.scale);

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
                case R.id.btnSetup:
                    llSetup.startAnimation(aniTouch);
                    btnSetup.startAnimation(aniTouch);

                    // 변경할 닉네임 입력 확인
                    if (etAfterNick.getText().toString().equals("")) {
                        Toast.makeText(ProfileActivity.this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    inputNickname = etAfterNick.getText().toString();

                    // 변경할 MBTI 입력 확인
                    if (etAfterMbti.getText().toString().equals("")) {
                        Toast.makeText(ProfileActivity.this, "MBTI를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    inputMbti = etAfterMbti.getText().toString();

                    // MBTI 바르게 입력했는지 확인
                    if (inputMbti.equals("INTJ") || inputMbti.equals("INTP") || inputMbti.equals("ENTJ") || inputMbti.equals("ENTP") || inputMbti.equals("INFJ") || inputMbti.equals("INFP") || inputMbti.equals("ENFJ") || inputMbti.equals("ENFP") || inputMbti.equals("ISTJ") || inputMbti.equals("ISFJ") || inputMbti.equals("ESTJ") || inputMbti.equals("ESFJ") || inputMbti.equals("ISTP") || inputMbti.equals("ISFP") || inputMbti.equals("ESTP") || inputMbti.equals("ESFP")) {
                        Toast.makeText(ProfileActivity.this, "저장 버튼을 눌러주세요.", Toast.LENGTH_SHORT).show();
                        tvBeforeNick2.setText(inputNickname);
                        tvBeforeMbti2.setText(inputMbti);
                        break;
                    } else {
                        Toast.makeText(ProfileActivity.this, "잘못된 MBTI를 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                case R.id.btnSave:
                    llSave.startAnimation(aniTouch);
                    btnSave.startAnimation(aniTouch);

                    // 빈칸 확인
                    if (etAfterNick.getText().toString().equals("") || etAfterMbti.getText().toString().equals("")) {
                        Toast.makeText(ProfileActivity.this, "변경 사항을 먼저 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        if (inputNickname == null || inputMbti == null) {
                            Toast.makeText(ProfileActivity.this, "먼저 설정 버튼을 눌러주세요.", Toast.LENGTH_SHORT).show();
                            break;
                        } else {
                            Toast.makeText(ProfileActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                            strNickname = inputNickname;
                            strMbti = inputMbti;

                            // DB에 데이터 입력
                            insert(strNickname, strMbti);

                            btnNickname.setText(strNickname);
                            tvMbti.setText(strMbti);
                            break;
                        }
                    }
                case R.id.btnSidemenu:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnHome:
                    Intent intentHome = new Intent(ProfileActivity.this, MainActivity.class);
                    intentHome.putExtra("nick", strNickname);
                    intentHome.putExtra("mbti", strMbti);
                    startActivity(intentHome);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btnAccount:
                    Intent intentAccount = new Intent(ProfileActivity.this, AccountActivity.class);
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
        Intent intentBack = new Intent(ProfileActivity.this, MainActivity.class);
        intentBack.putExtra("nick", strNickname);
        intentBack.putExtra("mbti", strMbti);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }

    // 데이터베이스 INSERT 함수
    public void insert(String nick, String mbti) {
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nick", nick);
        values.put("mbti", mbti);
        db.insert("member", null, values);
        db.close();

        Log.d(TAG, "Profile changed: " + nick + " / " + mbti);
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
                Intent intentProfile = new Intent(ProfileActivity.this, ProfileActivity.class);
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
        Intent intentMatch = new Intent(ProfileActivity.this, MatchAllActivity.class);
        intentMatch.putExtra("nick", strNickname);
        intentMatch.putExtra("mbti", strMbti);
        startActivity(intentMatch);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Game page
    public void pageGame() {
        Intent intentGame = new Intent(ProfileActivity.this, GameAllActivity.class);
        intentGame.putExtra("nick", strNickname);
        intentGame.putExtra("mbti", strMbti);
        startActivity(intentGame);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Book page
    public void pageBook() {
        Intent intentBook = new Intent(ProfileActivity.this, BookAllActivity.class);
        intentBook.putExtra("nick", strNickname);
        intentBook.putExtra("mbti", strMbti);
        startActivity(intentBook);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Youtube page
    public void pageYoutube() {
        Intent intentYoutube = new Intent(ProfileActivity.this, YoutubeAllActivity.class);
        intentYoutube.putExtra("nick", strNickname);
        intentYoutube.putExtra("mbti", strMbti);
        startActivity(intentYoutube);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Music page
    public void pageMusic() {
        Intent intentMusic = new Intent(ProfileActivity.this, MusicAllActivity.class);
        intentMusic.putExtra("nick", strNickname);
        intentMusic.putExtra("mbti", strMbti);
        startActivity(intentMusic);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Movie page
    public void pageMovie() {
        Intent intentMovie = new Intent(ProfileActivity.this, MovieAllActivity.class);
        intentMovie.putExtra("nick", strNickname);
        intentMovie.putExtra("mbti", strMbti);
        startActivity(intentMovie);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Drama page
    public void pageDrama() {
        Intent intentDrama = new Intent(ProfileActivity.this, DramaAllActivity.class);
        intentDrama.putExtra("nick", strNickname);
        intentDrama.putExtra("mbti", strMbti);
        startActivity(intentDrama);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    // Job page
    public void pageJob() {
        Intent intentJob = new Intent(ProfileActivity.this, JobAllActivity.class);
        intentJob.putExtra("nick", strNickname);
        intentJob.putExtra("mbti", strMbti);
        startActivity(intentJob);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
}