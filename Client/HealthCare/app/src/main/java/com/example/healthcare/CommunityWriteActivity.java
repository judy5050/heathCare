package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.data.MessageBoardPData;
import com.example.healthcare.data.PostMessageBoardPData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityWriteActivity extends AppCompatActivity {

    private Button btn_community_write_save;
    private EditText et_community_write_title;
    private EditText et_community_write_content;

    private Intent intent;

    private APIClient apiClient;
    private APIInterface apiInterface;

    private String jwt;
    private int useridx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_write);

        init_intent();
        init_view();
        btn_clicked();
    }

    private void init_intent(){
        intent = getIntent();
        jwt = intent.getStringExtra("jwt");
        useridx = intent.getIntExtra("useridx", 0);
    }

    private void init_view(){
        btn_community_write_save = (Button)findViewById(R.id.btn_community_write_save);

        et_community_write_title = (EditText)findViewById(R.id.et_community_write_title);
        et_community_write_content = (EditText)findViewById(R.id.et_community_write_content);

    }

    private void btn_clicked(){
        btn_community_write_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageboardPAPI();
            }
        });
    }

    private void messageboardPAPI(){
        try{
            apiClient = APIClient.getInstance();
            apiInterface = APIClient.getApiInterface();
            PostMessageBoardPData data = new PostMessageBoardPData();
            data.setTitle(et_community_write_title.getText().toString());
            data.setMessage(et_community_write_content.getText().toString());
            if(data.getTitle().length()==0){
                Toast.makeText(getApplicationContext(),"제목을 입력하세요.", Toast.LENGTH_SHORT).show();
            }
            else if(data.getMessage().length() == 0){
                Toast.makeText(getApplicationContext(),"내용을 입력하세요.", Toast.LENGTH_SHORT).show();
            }else{
                apiInterface.messageboardPAPI(useridx, jwt, data).enqueue(new Callback<MessageBoardPData>() {
                    @Override
                    public void onResponse(Call<MessageBoardPData> call, Response<MessageBoardPData> response) {
                        MessageBoardPData result = response.body();
                        Toast.makeText(getApplicationContext(),result.getMessage(), Toast.LENGTH_SHORT).show();
                        if(result.getCode()==1020) finish();
                    }

                    @Override
                    public void onFailure(Call<MessageBoardPData> call, Throwable t) {

                    }
                });
            }
        }catch (Exception e){

        }
    }
}
