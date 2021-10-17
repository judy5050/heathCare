package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcare.data.MessageBoardsCommentPData;
import com.example.healthcare.data.MessageBoardsCommentsGData;
import com.example.healthcare.data.MessageBoardsGetMsgData;
import com.example.healthcare.data.PostMessageBoardsCommentPData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityLookupActivity extends AppCompatActivity {

    private Button btn_community_lookup_correction;
    private Button btn_community_lookup_input;

    private EditText et_community_lookup_reply;

    private TextView tv_community_lookup_time;
    private TextView tv_community_lookup_title;
    private TextView tv_community_lookup_content;
    private TextView tv_community_lookup_nickname;

    private Intent intent;

    private APIClient apiClient;
    private APIInterface apiInterface;

    private RecyclerView rv_community_comment;

    private RecyclerCommunityCommentAdapter adapter_commmunity_comment;

    private String jwt;
    private int idx,useridx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_lookup);

        init_intent();
        init_view();
        btn_clicked();
        messageboardGMAPI();
        messageboardscommentsGAPI();
    }

    private void init_view(){
        btn_community_lookup_correction = (Button)findViewById(R.id.btn_community_lookup_correction);
        btn_community_lookup_input = (Button)findViewById(R.id.btn_community_lookup_input);

        et_community_lookup_reply = (EditText)findViewById(R.id.et_community_lookup_reply);

        tv_community_lookup_time = (TextView)findViewById(R.id.tv_community_lookup_time);
        tv_community_lookup_title = (TextView)findViewById(R.id.tv_community_lookup_title);
        tv_community_lookup_content = (TextView)findViewById(R.id.tv_community_lookup_content);
        tv_community_lookup_nickname = (TextView)findViewById(R.id.tv_community_lookup_nickname);

        rv_community_comment = (RecyclerView)findViewById(R.id.rv_community_comment);

        rv_community_comment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter_commmunity_comment = new RecyclerCommunityCommentAdapter();

        rv_community_comment.setAdapter(adapter_commmunity_comment);
    }

    private void btn_clicked(){
        btn_community_lookup_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_community_lookup_reply.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(),"텍스트를 입력하세요.",Toast.LENGTH_SHORT).show();
                }
                else if (et_community_lookup_reply.getText().toString().length() > 25){
                    Toast.makeText(getApplicationContext(),"댓글이 너무 깁니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    messageboardscommentPAPI(et_community_lookup_reply.getText().toString());
                }
            }
        });
    }

    private void init_intent(){
        intent = getIntent();
        jwt = intent.getStringExtra("jwt");
        idx = intent.getIntExtra("idx", 0);
        useridx = intent.getIntExtra("useridx",0);
    }

    private void messageboardGMAPI(){
        try {
            apiClient = APIClient.getInstance();
            apiInterface = APIClient.getApiInterface();
            apiInterface.messageboardsGMAPI(idx, jwt).enqueue(new Callback<MessageBoardsGetMsgData>() {
                @Override
                public void onResponse(Call<MessageBoardsGetMsgData> call, Response<MessageBoardsGetMsgData> response) {
                    MessageBoardsGetMsgData result = response.body();
                    tv_community_lookup_title.setText(Integer.toString(idx));
                    if (result.getCode() == 1024) {
                        tv_community_lookup_title.setText(result.getResult().getTitle());
                        tv_community_lookup_content.setText(result.getResult().getMessage());
                        tv_community_lookup_time.setText(result.getResult().getCreatedDate());
                        tv_community_lookup_nickname.setText(result.getResult().getUserNickName());
                    } else {
                        Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageBoardsGetMsgData> call, Throwable t) {

                }
            });
        }
        catch (Exception e){

        }
    }

    private void messageboardscommentsGAPI(){
        try{
            apiClient = APIClient.getInstance();
            apiInterface = APIClient.getApiInterface();
            apiInterface.messageboardscommentsGAPI(idx,jwt).enqueue(new Callback<MessageBoardsCommentsGData>() {
                @Override
                public void onResponse(Call<MessageBoardsCommentsGData> call, Response<MessageBoardsCommentsGData> response) {
                    MessageBoardsCommentsGData result = response.body();

                    if(result.getCode() == 1027) {
                        for (int i = 0; i < result.getResult().getCommentResList().size(); i++) {
                            adapter_commmunity_comment.addItem(result.getResult().getCommentResList().get(i).getCommentIdx(),
                                    result.getResult().getCommentResList().get(i).getUserIdx(),
                                    result.getResult().getCommentResList().get(i).getUserNickName(),
                                    result.getResult().getCommentResList().get(i).getCommentMsg(),
                                    result.getResult().getCommentResList().get(i).getCreatedDate());
                        }

                        rv_community_comment.setAdapter(adapter_commmunity_comment);
                    }
                }

                @Override
                public void onFailure(Call<MessageBoardsCommentsGData> call, Throwable t) {

                }
            });
        }
        catch (Exception e){

        }
    }

    private void messageboardscommentPAPI(String msg){
        try{
            apiClient = APIClient.getInstance();
            apiInterface = APIClient.getApiInterface();
            PostMessageBoardsCommentPData data = new PostMessageBoardsCommentPData();
            data.setMsg(msg);
            apiInterface.messageboardscommentPAPI(idx, useridx, jwt, data).enqueue(new Callback<MessageBoardsCommentPData>() {
                @Override
                public void onResponse(Call<MessageBoardsCommentPData> call, Response<MessageBoardsCommentPData> response) {
                    MessageBoardsCommentPData result = response.body();
                    Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                    if(result.getCode() == 1025){
                        init_view();
                        messageboardscommentsGAPI();
                    }
                }

                @Override
                public void onFailure(Call<MessageBoardsCommentPData> call, Throwable t) {

                }
            });
        }
        catch (Exception e){

        }
    }
}
