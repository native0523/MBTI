package com.luan.mbti;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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
import androidx.drawerlayout.widget.DrawerLayout;

import org.w3c.dom.Text;

public class NicknameActivity extends AppCompatActivity {
    // DB
    SQLiteDatabase db;
    MySQLiteOpenHelper helper;

    // DB 데이터
    String strNickname, strMbti;

    // 뒤로가기 버튼 관리 (2번 누르면 앱 종료)
    private BackHandler backHandler = new BackHandler(this);

    // 레이아웃 요소
    LinearLayout llTitle, llNick, llNext, llTest;
    TextView tvTitle1, tvTitle2, tvNick, tvMbti, tvDesc;
    EditText etNick, etMbti;
    Button btnNext, btnTest;
    String TAG = "Nickname";

    // 애니메이션
    Animation aniLlTitle, aniLlNick, aniLlNext, aniLlTest;
    Animation aniTvTitle1, aniTvTitle2, aniTvDesc;
    Animation aniTvNick, aniEtNick, aniTvMbti, aniEtMbti;
    Animation aniBtnNext, aniBtnTest;
    Animation aniTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        // DB
        helper = new MySQLiteOpenHelper(NicknameActivity.this, "member.db", null, 1);

        // 레이아웃
        llTitle = (LinearLayout) findViewById(R.id.llTitle);
        llNick = (LinearLayout) findViewById(R.id.llNick);
        llNext = (LinearLayout) findViewById(R.id.llNext);

        // 레이아웃 애니메이션
        aniLlTitle = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.descend);
        aniLlNick = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.ascend_fast);
        aniLlNext = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.ascend_fast);
        aniLlNick.setStartOffset(200);
        aniLlNext.setStartOffset(400);
        llTitle.startAnimation(aniLlTitle);
        llNick.startAnimation(aniLlNick);
        llNext.startAnimation(aniLlNext);

        // 버튼 & 텍스트
        tvTitle1 = (TextView) findViewById(R.id.tvTitle1);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        tvNick = (TextView) findViewById(R.id.tvNick);
        etNick = (EditText) findViewById(R.id.etNick);
        tvMbti = (TextView) findViewById(R.id.tvMbti);
        etMbti = (EditText) findViewById(R.id.etMbti);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(mClickListener);

        // 버튼 & 텍스트 애니메이션
        aniTvTitle1 = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.descend_fast);
        aniTvTitle1.setStartOffset(400);
        aniTvTitle2 = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.ascend_fast);
        aniTvTitle2.setStartOffset(400);
        aniTvNick = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.fadein);
        aniEtNick = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.fadein);
        aniTvMbti = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.fadein);
        aniEtMbti = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.fadein);
        aniTvDesc = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.fadein);
        aniBtnNext = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.fadein);
        aniTvNick.setStartOffset(600);
        aniEtNick.setStartOffset(700);
        aniTvMbti.setStartOffset(800);
        aniEtMbti.setStartOffset(900);
        aniTvDesc.setStartOffset(1000);
        aniBtnNext.setStartOffset(1100);
        tvTitle1.startAnimation(aniTvTitle1);
        tvTitle2.startAnimation(aniTvTitle2);
        tvNick.startAnimation(aniTvNick);
        etNick.startAnimation(aniEtNick);
        tvMbti.startAnimation(aniTvMbti);
        etMbti.startAnimation(aniEtMbti);
        tvDesc.startAnimation(aniTvDesc);
        btnNext.startAnimation(aniBtnNext);

        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.scale);

        // MBTI 테스트
        llTest = (LinearLayout) findViewById(R.id.llTest);
        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(mClickListener);
        aniLlTest = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.ascend_fast);
        aniBtnTest = AnimationUtils.loadAnimation(NicknameActivity.this, R.anim.fadein);
        aniLlTest.setStartOffset(600);
        aniBtnTest.setStartOffset(1200);
        llTest.startAnimation(aniLlTest);
        btnTest.startAnimation(aniBtnTest);

    }

    // Main Layout
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnNext:
                    llNext.startAnimation(aniTouch);
                    btnNext.startAnimation(aniTouch);

                    // 닉네임 입력 확인
                    if (etNick.getText().toString().equals("")) {
                        Toast.makeText(NicknameActivity.this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    strNickname = etNick.getText().toString();

                    // MBTI 입력 확인
                    if (etMbti.getText().toString().equals("")) {
                        Toast.makeText(NicknameActivity.this, "MBTI를 입력해주세요", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    strMbti = etMbti.getText().toString();

                    // MBTI 바르게 입력했는지 확인
                    if (strMbti.equals("INTJ") || strMbti.equals("INTP") || strMbti.equals("ENTJ") || strMbti.equals("ENTP") || strMbti.equals("INFJ") || strMbti.equals("INFP") || strMbti.equals("ENFJ") || strMbti.equals("ENFP") || strMbti.equals("ISTJ") || strMbti.equals("ISFJ") || strMbti.equals("ESTJ") || strMbti.equals("ESFJ") || strMbti.equals("ISTP") || strMbti.equals("ISFP") || strMbti.equals("ESTP") || strMbti.equals("ESFP")) {
                        dialogMain();
                        break;
                    } else if (strMbti.equals("intj") || strMbti.equals("intp") || strMbti.equals("entj") || strMbti.equals("entp") || strMbti.equals("infj") || strMbti.equals("infp") || strMbti.equals("enfj") || strMbti.equals("enfp") || strMbti.equals("istj") || strMbti.equals("isfj") || strMbti.equals("estj") || strMbti.equals("esfj") || strMbti.equals("istp") || strMbti.equals("isfp") || strMbti.equals("estp") || strMbti.equals("esfp")) {
                        Toast.makeText(NicknameActivity.this, "대문자로 입력해주세요.", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        Toast.makeText(NicknameActivity.this, "잘못된 MBTI를 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                case R.id.btnTest:
                    llTest.startAnimation(aniTouch);
                    btnTest.startAnimation(aniTouch);
                    dialogTest();
                    break;
            }
        }
    };

    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        backHandler.onBackPressed();
    }

    // 데이터베이스 INSERT 함수
    public void insert(String nick, String mbti) {
        db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nick", nick);
        values.put("mbti", mbti);
        db.insert("member", null, values);
        db.close();

        Toast.makeText(getApplicationContext(), nick + "님 환영합니다!", Toast.LENGTH_LONG).show();
        Log.d(TAG, "Sign-Up completed: " + nick + " / " + mbti);
    }

    // 메인 다이얼로그 (닉네임 DB에 저장 후 메인으로 이동)
    public void dialogMain() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.main_dialog);
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

                // DB에 데이터 입력
                insert(strNickname, strMbti);

                Intent intentProfile = new Intent(NicknameActivity.this, MainActivity.class);
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

    // MBTI 테스트 다이얼로그
    public void dialogTest() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.test_dialog);
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
                Intent intentTest = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.16personalities.com/ko");
                intentTest.setData(uri);
                startActivity(intentTest);
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

}