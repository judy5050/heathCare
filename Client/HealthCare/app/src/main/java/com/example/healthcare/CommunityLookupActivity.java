package com.example.healthcare;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityLookupActivity extends AppCompatActivity {

    private Button btn_community_lookup_correction;

    private TextView tv_community_lookup_time;
    private TextView tv_community_lookup_title;
    private TextView tv_community_lookup_content;

    private RecyclerView rv_community_lookup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_lookup);

        init_view();
    }

    private void init_view(){
        btn_community_lookup_correction = (Button)findViewById(R.id.btn_community_lookup_correction);

        tv_community_lookup_time = (TextView)findViewById(R.id.tv_community_lookup_time);
        tv_community_lookup_title = (TextView)findViewById(R.id.tv_community_lookup_title);
        tv_community_lookup_content = (TextView)findViewById(R.id.tv_community_lookup_content);

        rv_community_lookup = (RecyclerView)findViewById(R.id.rv_community_lookup);
    }
}
