package com.example.healthcare;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Button btn_settings_save;

    private EditText et_settings_clean;

    private TextView tv_settings_time;
    private TextView tv_settings_clean;

    private ProgressBar pgb_settings;

    private Switch sw_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init_view();
    }

    private void init_view(){
        btn_settings_save = (Button)findViewById(R.id.btn_settings_save);

        et_settings_clean = (EditText)findViewById(R.id.et_settings_clean);

        tv_settings_time = (TextView)findViewById(R.id.tv_settings_time);
        tv_settings_clean = (TextView)findViewById(R.id.tv_settings_clean);

        pgb_settings = (ProgressBar)findViewById(R.id.pgb_settings);

        sw_settings = (Switch)findViewById(R.id.sw_settings);
    }
}
