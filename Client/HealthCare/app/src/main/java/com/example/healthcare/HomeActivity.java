package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private String nickname, name, birthday;

    private TextView tv_home_nickname;
    private TextView tv_home_name;
    private TextView tv_home_birthday;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        init_view();
        init_intent();


        tv_home_nickname.setText(nickname);
        tv_home_name.setText(name);
        tv_home_birthday.setText(birthday);
    }

    private void init_intent(){
        intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        name = intent.getStringExtra("name");
        birthday = intent.getStringExtra("birthday");

    }

    private void init_view(){
        tv_home_nickname = (TextView)findViewById(R.id.tv_home_nickname);
        tv_home_name = (TextView)findViewById(R.id.tv_home_name);
        tv_home_birthday = (TextView)findViewById(R.id.tv_home_birthday);
    }
}
