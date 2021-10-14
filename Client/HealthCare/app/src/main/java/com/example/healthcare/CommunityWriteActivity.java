package com.example.healthcare;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CommunityWriteActivity extends AppCompatActivity {

    private Button btn_community_write_save;
    private EditText et_community_write_title;
    private EditText et_community_write_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_write);

        init_view();
    }

    private void init_view(){
        btn_community_write_save = (Button)findViewById(R.id.btn_community_write_save);

        et_community_write_title = (EditText)findViewById(R.id.et_community_write_title);
        et_community_write_content = (EditText)findViewById(R.id.et_community_write_content);

    }
}
