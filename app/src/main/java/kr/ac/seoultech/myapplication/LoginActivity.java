package kr.ac.seoultech.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
                            implements View.OnClickListener {

    private EditText etLoginId;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginId = (EditText) findViewById(R.id.et_login_id);
        etPassword = (EditText) findViewById(R.id.et_password);

        findViewById(R.id.btn_login).setOnClickListener(this);

        SharedPreferences sp = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String loginId = sp.getString("loginId", null);
        if (loginId != null) {
            etLoginId.setText(loginId);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login : {

                String loginId = etLoginId.getText().toString();
                String password = etPassword.getText().toString();

                if (loginId.equals("seoultech") && password.equals("1234")) {
                    Toast.makeText(this,
                            "로그인 되였습니다.",
                            Toast.LENGTH_SHORT).show();

                    SharedPreferences sp
                            = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("loginId", loginId);
                    editor.commit();

                    finish();
                } else {
                    Toast.makeText(this,
                            "아이디 또는 패스워드가 다릅니다.",
                            Toast.LENGTH_SHORT).show();

                    SharedPreferences sp
                            = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("loginId");
                    editor.commit();
                }

                break;
            }
        }
    }

}
