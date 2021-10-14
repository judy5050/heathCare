package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeActivity extends AppCompatActivity {
    private Button btn_drawer;
    private Button btn_settings;
    private Button btn_community;
    private Button btn_search;

    private DrawerLayout drawerLayout;
    private View drawerView;

    private TextView tv_home_nickname;
    private TextView tv_home_name;
    private TextView tv_home_birthday;

    private Intent intent;


    private String nickname, name, birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        init_view();
        init_intent();
        btn_clicked();

        tv_home_nickname.setText(nickname);
        tv_home_name.setText(name);
        tv_home_birthday.setText(birthday);
    }

    private void init_intent() {
        intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        name = intent.getStringExtra("name");
        birthday = intent.getStringExtra("birthday");

    }

    private void init_view() {
        btn_drawer = (Button) findViewById(R.id.btn_drawer);
        btn_community = (Button) findViewById(R.id.btn_community);
        btn_settings = (Button) findViewById(R.id.btn_settings);
        btn_search = (Button) findViewById(R.id.btn_search);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_home);
        drawerView = (View) findViewById(R.id.drawer);

        tv_home_nickname = (TextView) findViewById(R.id.tv_home_nickname);
        tv_home_name = (TextView) findViewById(R.id.tv_home_name);
        tv_home_birthday = (TextView) findViewById(R.id.tv_home_birthday);

    }

    private void btn_clicked() {
        btn_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        btn_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(HomeActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
