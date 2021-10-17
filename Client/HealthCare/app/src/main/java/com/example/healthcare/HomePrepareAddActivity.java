package com.example.healthcare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.data.GroupUserData;
import com.example.healthcare.data.PostGroupUserData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePrepareAddActivity extends AppCompatActivity {

    private Button btn_home_prepare_add_save;

    private EditText et_home_prepare_add_nickname;
    private EditText et_home_prepare_add_name;
    private EditText et_home_prepare_add_year;
    private EditText et_home_prepare_add_month;
    private EditText et_home_prepare_add_day;

//    private Spinner spn_home_prepare_add_year;
//    private Spinner spn_home_prepare_add_month;
//    private Spinner spn_home_prepare_add_day;

    private List<ImageButton> iv_home_prepare_add = new ArrayList<>();

    private ImageView iv_home_prepare_add_icon;

    private ArrayList<Integer> list_year, list_month, list_day;
    private ArrayAdapter<Integer> adapter_year, adapter_month, adapter_day;

    private Intent intent;

    private List<Integer> image = new ArrayList<>();


    private APIClient apiClient;
    private APIInterface apiInterface;

    private String nickname, name, jwt, birth;
    private int year, month, day, photoIdx;
    private List<Integer> date = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_prepare_add);

        init_intent();
        init_view();
        //init_spinner();
        iv_clicked();
        btn_clicked();
        //set ImageView and Save Button


    }

    private void btn_clicked(){
        btn_home_prepare_add_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupuserAPI();
            }
        });
    }

    private void groupuserAPI(){
        try{
            apiClient = APIClient.getInstance();
            apiInterface = APIClient.getApiInterface();
            PostGroupUserData data = new PostGroupUserData();
            data.setBirth(et_home_prepare_add_year.getText().toString() + et_home_prepare_add_month.getText().toString() + et_home_prepare_add_day.getText().toString());
            data.setUserName(et_home_prepare_add_name.getText().toString());
            data.setUserNickName(et_home_prepare_add_nickname.getText().toString());
            data.setPhotoIdx(photoIdx);
            apiInterface.groupuserAPI(jwt, data).enqueue(new Callback<GroupUserData>() {
                @Override
                public void onResponse(Call<GroupUserData> call, Response<GroupUserData> response) {
                    GroupUserData result = response.body();
                    if(result.getCode() == 1018){
                        Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GroupUserData> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }

    }

    private void init_intent(){
        intent = getIntent();
        jwt = intent.getStringExtra("jwt");
    }

    private void init_view(){
        btn_home_prepare_add_save = (Button)findViewById(R.id.btn_home_prepare_add_save);

        et_home_prepare_add_nickname = (EditText)findViewById(R.id.et_home_prepare_add_nickname);
        et_home_prepare_add_name = (EditText)findViewById(R.id.et_home_prepare_add_name);
        et_home_prepare_add_year = (EditText)findViewById(R.id.et_home_prepare_add_year);
        et_home_prepare_add_month = (EditText)findViewById(R.id.et_home_prepare_add_month);
        et_home_prepare_add_day = (EditText)findViewById(R.id.et_home_prepare_add_day);

        iv_home_prepare_add.add(null);
        iv_home_prepare_add.add((ImageButton)findViewById(R.id.iv_man1));
        iv_home_prepare_add.add((ImageButton)findViewById(R.id.iv_man2));
        iv_home_prepare_add.add((ImageButton)findViewById(R.id.iv_man3));
        iv_home_prepare_add.add((ImageButton)findViewById(R.id.iv_woman1));
        iv_home_prepare_add.add((ImageButton)findViewById(R.id.iv_woman2));
        iv_home_prepare_add.add((ImageButton)findViewById(R.id.iv_woman3));

        iv_home_prepare_add_icon = (ImageView)findViewById(R.id.iv_home_prepare_add_icon);

//        spn_home_prepare_add_year = (Spinner)findViewById(R.id.spn_home_prepare_add_year);
//        spn_home_prepare_add_month = (Spinner)findViewById(R.id.spn_home_prepare_add_month);
//        spn_home_prepare_add_day = (Spinner)findViewById(R.id.spn_home_prepare_add_day);

        image.add( R.drawable.icon_choose);
        image.add( R.drawable.icon_man1);
        image.add( R.drawable.icon_man2);
        image.add( R.drawable.icon_man3);
        image.add( R.drawable.icon_woman1);
        image.add( R.drawable.icon_woman2);
        image.add( R.drawable.icon_woman3);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image.get(0));
        bitmap = getBitmapSquareCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
        bitmap = getBitmapCircleCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
        iv_home_prepare_add_icon.setImageBitmap(bitmap);
        date.add(0);
        date.add(0);
        date.add(0);
    }
/*
    private void init_spinner(){

        list_year = new ArrayList<>();
        for(int i = 2021; i >= 1900; i--) list_year.add(i);
        adapter_year = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_year);
        spn_home_prepare_add_year.setAdapter(adapter_year);

        list_month = new ArrayList<>();
        for(int i = 1; i <= 12; i++) list_month.add(i);
        adapter_month = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_month);
        spn_home_prepare_add_month.setAdapter(adapter_month);

        spn_home_prepare_add_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = adapter_year.getItem(position);
                date.set(0, year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Need to set Leap year
        spn_home_prepare_add_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = adapter_month.getItem(position);
                date.set(1,month);

                list_day = new ArrayList<>();
                if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    for(int i = 1; i <= 31; i++)list_day.add(i);
                    adapter_day = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_day);
                    spn_home_prepare_add_day.setAdapter(adapter_day);
                }
                else if(month == 2){
                    for(int i = 1; i <= 28; i++)list_day.add(i);
                    adapter_day = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_day);
                    spn_home_prepare_add_day.setAdapter(adapter_day);
                }
                else{
                    for(int i = 1; i <= 30; i++)list_day.add(i);
                    adapter_day = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_day);
                    spn_home_prepare_add_day.setAdapter(adapter_day);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spn_home_prepare_add_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = adapter_day.getItem(position);
                date.set(2,day);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        birth = Integer.toString((date.get(0)*10000) + (date.get(1)*100) + date.get(2));
    }
*/
    private void iv_clicked(){
        for(int i = 1; i <= 6; i++){
            int idx = i;
            iv_home_prepare_add.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    photoIdx = idx;
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image.get(idx));
                    bitmap = getBitmapSquareCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
                    bitmap = getBitmapCircleCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
                    iv_home_prepare_add_icon.setImageBitmap(bitmap);
                }
            });
        }
    }

    private static Bitmap getBitmapCircleCrop(Bitmap bitmap, int Width, int Height) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        Bitmap CroppedBitmap = output;

        return CroppedBitmap;
    }

    private static Bitmap getBitmapSquareCrop(Bitmap bitmap, int Width, int Height){
        Bitmap CroppedBitmap;
        if(bitmap.getWidth()<bitmap.getHeight())
            CroppedBitmap = Bitmap.createBitmap(bitmap,0,(Height-Width)/2,bitmap.getWidth(),bitmap.getWidth());
        else if(bitmap.getWidth()>bitmap.getHeight())
            CroppedBitmap = Bitmap.createBitmap(bitmap,(Width-Height)/2,0,bitmap.getHeight(),bitmap.getHeight());
        else
            CroppedBitmap = bitmap;

        return CroppedBitmap;
    }

}
