package com.example.healthcare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.data.GroupData;
import com.example.healthcare.data.PostGroupData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    private Button btn_join;

    private EditText et_join_id;
    private EditText et_join_pw;

    private APIClient apiClient;
    private APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        init_view();
        btn_clicked();
    }

    private void init_view(){
        btn_join = (Button)findViewById(R.id.btn_join);

        et_join_id = (EditText)findViewById(R.id.et_join_id);
        et_join_pw = (EditText)findViewById(R.id.et_join_pw);
    }

    private void btn_clicked(){
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupAPI();
            }
        });
    }

    private void groupAPI(){
        try{
            apiClient = APIClient.getInstance();
            apiInterface = APIClient.getApiInterface();
            PostGroupData data = new PostGroupData();
            data.setLoginId(et_join_id.getText().toString());
            data.setPassword(et_join_pw.getText().toString());
            apiInterface.groupAPI(data).enqueue(new Callback<GroupData>() {
                @Override
                public void onResponse(Call<GroupData> call, Response<GroupData> response) {
                    GroupData result = response.body();

                    if(result.getCode()== 1012){
                        Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GroupData> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }
}
