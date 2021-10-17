package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.data.CleaningInfoGData;
import com.example.healthcare.data.CleaningInfoPData;
import com.example.healthcare.data.PostCleaningInfoData;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends AppCompatActivity {

    private Button btn_settings_save;

    private EditText et_settings_clean;

    private TextView tv_settings_time;
    private TextView tv_settings_clean;
    private TextView tv_settings_percent;

    private ProgressBar pgb_settings;

    private Switch sw_settings;

    private Intent intent;

    private APIClient apiClient;
    private APIInterface apiInterface;

    private String jwt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init_view();
        init_intent();
        cleaninginfoPAPI();
        cleaninginfoGAPI();
    }

    private void init_view(){
        btn_settings_save = (Button)findViewById(R.id.btn_settings_save);

        et_settings_clean = (EditText)findViewById(R.id.et_settings_clean);

        tv_settings_time = (TextView)findViewById(R.id.tv_settings_time);
        tv_settings_clean = (TextView)findViewById(R.id.tv_settings_clean);
        tv_settings_percent = (TextView)findViewById(R.id.tv_settings_percent);

        pgb_settings = (ProgressBar)findViewById(R.id.pgb_settings);

        sw_settings = (Switch)findViewById(R.id.sw_settings);
    }

    private void init_intent(){
        intent = getIntent();
        jwt = intent.getStringExtra("jwt");
    }

    private void cleaninginfoGAPI(){
        apiClient = APIClient.getInstance();
        apiInterface = APIClient.getApiInterface();
        apiInterface.cleaninginfoGAPI(jwt).enqueue(new Callback<CleaningInfoGData>() {
            @Override
            public void onResponse(Call<CleaningInfoGData> call, Response<CleaningInfoGData> response) {
                CleaningInfoGData result = response.body();
                if(result.getCode() == 1030){
                    pgb_settings.setProgress(result.getResult().getLiquidAmount());
                    tv_settings_percent.setText(result.getResult().getLiquidAmount().toString() + "%");

                    tv_settings_time.setText(new SimpleDateFormat("yyyy-MM-dd hh:00").format(System.currentTimeMillis()));
                    tv_settings_clean.setText(new SimpleDateFormat("yyyy-MM-dd").format(result.getResult().getRecentlyCleaning()));
                    if(result.getResult().getIsCleaned()==1){
                        sw_settings.setChecked(true);
                        sw_settings.setText("on");
                    }
                    else{
                        sw_settings.setChecked(false);
                        sw_settings.setText("off");
                    }
                    et_settings_clean.setText(result.getResult().getAutoCleaningCycle().toString());
                }
            }

            @Override
            public void onFailure(Call<CleaningInfoGData> call, Throwable t) {

            }
        });
    }

    private void cleaninginfoPAPI(){
        apiClient = APIClient.getInstance();
        apiInterface = APIClient.getApiInterface();
        PostCleaningInfoData data = new PostCleaningInfoData();
        data.setLiquidAmount(25);
        data.setRecentlyCleaningTime(System.currentTimeMillis()-86400*1000);
        data.setIsCleaned(1);
        data.setAutoCleaningCycle(2);
        apiInterface.cleaninginfoPAPI(jwt, data).enqueue(new Callback<CleaningInfoPData>() {
            @Override
            public void onResponse(Call<CleaningInfoPData> call, Response<CleaningInfoPData> response) {
                CleaningInfoPData result = response.body();
                //Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CleaningInfoPData> call, Throwable t) {

            }
        });
    }
}
