package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityActivity extends AppCompatActivity {
    private Button btn_community_new;

    private TextView tv_community_no;

    private RecyclerView rv_community;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        init_view();
        btn_clicked();
    }

    private void init_view(){
        btn_community_new = (Button)findViewById(R.id.btn_community_new);
        tv_community_no = (TextView)findViewById(R.id.tv_community_no);
        rv_community = (RecyclerView)findViewById(R.id.rv_community);
    }

    private void btn_clicked(){
        btn_community_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityActivity.this, CommunityWriteActivity.class);
                startActivity(intent);
            }
        });
    }
}
