package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePrepareActivity extends AppCompatActivity {
    private ImageView iv_home_prepare_1;
    private ImageView iv_home_prepare_2;
    private ImageView iv_home_prepare_3;
    private ImageView iv_home_prepare_4;

    private Intent intent;

    private String nickname = "seminzzang", name = "semin", birthday = "19960214";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_prepare);


        // Read data for user
        // nickname, imagecode [4]
        init_intent();
        init_view();
        iv_clicked();

    }

    private void init_intent(){
        intent = getIntent();
    }

    private void init_view(){
        iv_home_prepare_1 = (ImageView)findViewById(R.id.iv_home_prepare_1);
        iv_home_prepare_2 = (ImageView)findViewById(R.id.iv_home_prepare_2);
        iv_home_prepare_3 = (ImageView)findViewById(R.id.iv_home_prepare_3);
        iv_home_prepare_4 = (ImageView)findViewById(R.id.iv_home_prepare_4);
    }

    private void iv_clicked(){
        iv_home_prepare_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePrepareActivity.this, HomeActivity.class);
                intent.putExtra("nickname", nickname);
                intent.putExtra("name", name);
                intent.putExtra("birthday", birthday);
                startActivity(intent);
            }
        });

        iv_home_prepare_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_home_prepare_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_home_prepare_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
