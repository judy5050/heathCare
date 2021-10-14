package com.example.healthcare;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityMypostActivity extends AppCompatActivity {
    private TextView tv_community_mypost_no;
    private RecyclerView rv_community_mypost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_mypost);

        init_view();
    }

    private void init_view(){
        tv_community_mypost_no = (TextView)findViewById(R.id.tv_community_mypost_no);
        rv_community_mypost = (RecyclerView)findViewById(R.id.rv_community_mypost);
    }
}
