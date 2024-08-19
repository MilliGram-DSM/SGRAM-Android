package com.example.sgram.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.sgram.R;
import com.example.sgram.data.api.ApiProvider;
import com.example.sgram.data.api.AuthApi;
import com.example.sgram.data.request.JoinRequest;
import com.example.sgram.databinding.ActivityJoinBinding;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_join);

        ActivityJoinBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_join);
        Intent loginIntent = new Intent(this, LogInActivity.class);

        binding.logInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginIntent);
            }
        });

        // 정규식 ( 최대 글자 제한 )
        binding.pwdTx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = "[a-z|]";
                if (Pattern.matches(password, s)) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.signInButton.setOnClickListener(v -> {
            String id = binding.idTx.getText().toString();
            String pw = binding.pwdTx.getText().toString();
            String phone = binding.phoneTx.getText().toString();

            JoinRequest joinRequest = new JoinRequest(id, pw, phone);

            AuthApi authApi = new ApiProvider(JoinActivity.this).getAuthApi();

            authApi.Join(joinRequest).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    int code = response.code();

                    switch (code) {
                        case 201 : {
                            Toast.makeText(JoinActivity.this, "회원가입을 성공했습니다!",Toast.LENGTH_SHORT).show();
                            startActivity(loginIntent);
                            Log.d("JoinActivity", response.body().toString());
                            break;
                        }

                        case 400: {
                            Toast.makeText(JoinActivity.this, "Bad Request", Toast.LENGTH_LONG).show();
                            break;
                        }

                        case 409: {
                            Toast.makeText(JoinActivity.this, "이미 가입된 정보 입니다.", Toast.LENGTH_LONG).show();
                        }

                        case 500: {
                            Toast.makeText(JoinActivity.this, "서버 오류", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    Toast.makeText(JoinActivity.this, "정보를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    // textview, imageview, recyclerview ~~view :: tv, img, rv
    // button, image :: btn
    // ConstraintLayout, LinearLayout, FrameLayout :: constraint, linear,
}