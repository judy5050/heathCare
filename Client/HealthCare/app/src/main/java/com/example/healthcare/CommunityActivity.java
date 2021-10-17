package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcare.data.MessageBoardsGetListData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityActivity extends AppCompatActivity {
    private Button btn_community_new;

    private TextView tv_community_no;

    private RecyclerView rv_community;

    private RecyclerCommunityAdapter adapter_community;

    private Intent intent;

    private APIClient apiClient;
    private APIInterface apiInterface;

    private String jwt;

    private int useridx;

    @Override
    protected void onStart() {
        super.onStart();

        init_intent();
        init_view();
        btn_clicked();
        messageboardGLAPI();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        init_intent();
        init_view();
        btn_clicked();
        messageboardGLAPI();
    }

    private void init_view(){
        btn_community_new = (Button)findViewById(R.id.btn_community_new);
        tv_community_no = (TextView)findViewById(R.id.tv_community_no);
        rv_community = (RecyclerView)findViewById(R.id.rv_community);

        rv_community.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter_community = new RecyclerCommunityAdapter(jwt, useridx);

        rv_community.setAdapter(adapter_community);
    }

    private void btn_clicked(){
        btn_community_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityActivity.this, CommunityWriteActivity.class);
                intent.putExtra("jwt", jwt);
                intent.putExtra("useridx", useridx);
                startActivity(intent);
            }
        });
    }

    private void init_intent(){
        intent = getIntent();
        jwt = intent.getStringExtra("jwt");
        useridx = intent.getIntExtra("useridx",0);
    }

    private void messageboardGLAPI(){
        try {
            apiClient = APIClient.getInstance();
            apiInterface = APIClient.getApiInterface();
            apiInterface.messageboardsGLAPI(jwt).enqueue(new Callback<MessageBoardsGetListData>() {
                @Override
                public void onResponse(Call<MessageBoardsGetListData> call, Response<MessageBoardsGetListData> response) {
                    MessageBoardsGetListData result = response.body();
                    if(result.getResult().getMessageBoardList().size() == 0){
                        tv_community_no.setVisibility(View.VISIBLE);
                    }
                    else{
                        tv_community_no.setVisibility(View.INVISIBLE);
                        for(int i = 0; i < result.getResult().getMessageBoardList().size(); i++){
                            adapter_community.addItem(result.getResult().getMessageBoardList().get(i).getMessageBoardIdx(),
                                    result.getResult().getMessageBoardList().get(i).getUserNickName(),
                                    result.getResult().getMessageBoardList().get(i).getMessage(),
                                    result.getResult().getMessageBoardList().get(i).getTitle(),
                                    result.getResult().getMessageBoardList().get(i).getUserIdx(),
                                    result.getResult().getMessageBoardList().get(i).getCreatedDate());
                        }
                    }
                    rv_community.setAdapter(adapter_community);
                }

                @Override
                public void onFailure(Call<MessageBoardsGetListData> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }
}
