package com.example.healthcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.healthcare.data.LoginData;
import com.example.healthcare.data.PostLoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_join;

    private EditText et_id;
    private EditText et_pw;

    private TextView tv_test;

    private APIClient apiClient;
    private APIInterface apiInterface;

    private static final int PERMISSIONS_REQUEST_CODE = 100;

    private String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_view();
        btn_clicked();
        checkRunTimePermission();

    }

    private void init_view(){
        et_id = (EditText)findViewById(R.id.et_id);
        et_pw = (EditText)findViewById(R.id.et_pw);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_join = (Button)findViewById(R.id.btn_join);

        tv_test = (TextView)findViewById(R.id.tv_test);
    }

    private void btn_clicked(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAPI();
            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginAPI(){
        try{
            apiClient = APIClient.getInstance();
            apiInterface = APIClient.getApiInterface();
            PostLoginData data = new PostLoginData();
            data.setUserId(et_id.getText().toString());
            data.setPassword(et_pw.getText().toString());
            apiInterface.loginAPI(data).enqueue(new Callback<LoginData>() {
                @Override
                public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                    LoginData result = response.body();
                    // success
                    if(result.getCode() == 1013){
                        Intent intent = new Intent(MainActivity.this, HomePrepareActivity.class);
                        intent.putExtra("jwt", result.getResult().getJwt());
                        startActivity(intent);
                    }
                    // fail
                    else{
                        Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_SHORT).show();
                    }



                }

                @Override
                public void onFailure(Call<LoginData> call, Throwable t) {
                    tv_test.setText(t.getMessage());
                }
            });

        }catch (Exception e){
            tv_test.setText(e.getMessage());
        }
    }

    private void checkRunTimePermission(){
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
        }
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {
                Toast.makeText(MainActivity.this, "위치 접근 권한이 거부되어 일부 기능이 제한됩니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }
        }
    }
}