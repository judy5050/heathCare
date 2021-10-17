package com.example.healthcare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.data.GroupUserListData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePrepareActivity extends AppCompatActivity {
    private List<ImageView> iv_home_prepare = new ArrayList<>();
    private List<TextView> tv_home_prepare = new ArrayList<>();

    private Intent intent;

    private APIClient apiClient;
    private APIInterface apiInterface;

    private List<Integer> image = new ArrayList<>();
    //[7] = { 0, R.drawable.icon_man1, R.drawable.icon_man2, R.drawable.icon_man3, R.drawable.icon_woman1, R.drawable.icon_woman2, R.drawable.icon_woman3};


    private String jwt;

    private String nickname = "seminzzang", name = "semin", birthday = "19960214";
    private int photoIdx, useridx;

    @Override
    protected void onStart() {
        super.onStart();
        init_intent();
        init_view();
        groupuserlistAPI();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_prepare);


        // Read data for user
        // nickname, imagecode [4]
        init_intent();
        init_view();
        groupuserlistAPI();

        //iv_clicked();

    }

    private void init_intent(){
        intent = getIntent();
        jwt = intent.getStringExtra("jwt");
    }

    private void init_view(){

        iv_home_prepare.add( (ImageView)findViewById(R.id.iv_home_prepare_1));
        iv_home_prepare.add( (ImageView)findViewById(R.id.iv_home_prepare_2));
        iv_home_prepare.add( (ImageView)findViewById(R.id.iv_home_prepare_3));
        iv_home_prepare.add( (ImageView)findViewById(R.id.iv_home_prepare_4));

        tv_home_prepare.add( (TextView)findViewById(R.id.tv_home_prepare_1));
        tv_home_prepare.add( (TextView)findViewById(R.id.tv_home_prepare_2));
        tv_home_prepare.add( (TextView)findViewById(R.id.tv_home_prepare_3));
        tv_home_prepare.add( (TextView)findViewById(R.id.tv_home_prepare_4));

        image.add( R.drawable.icon_plus);
        image.add( R.drawable.icon_man1);
        image.add( R.drawable.icon_man2);
        image.add( R.drawable.icon_man3);
        image.add( R.drawable.icon_woman1);
        image.add( R.drawable.icon_woman2);
        image.add( R.drawable.icon_woman3);
    }

    private void set_view(GroupUserListData result){
        int cnt = result.getResult().getCount();
        for(int i = 0; i < cnt; i++){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image.get(result.getResult().getUserInfoList().get(i).getPhotoIdx()));
            bitmap = getBitmapSquareCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
            bitmap = getBitmapCircleCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
            iv_home_prepare.get(i).setImageBitmap(bitmap);
            tv_home_prepare.get(i).setText(result.getResult().getUserInfoList().get(i).getUserNickName());
        }
        for(int i = cnt; i < 4; i++){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image.get(0));
            bitmap = getBitmapSquareCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
            bitmap = getBitmapCircleCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
            iv_home_prepare.get(i).setImageBitmap(bitmap);
            tv_home_prepare.get(i).setText("");
        }
    }

    private void iv_clicked(GroupUserListData result){
        //for cnt까지
        int cnt = result.getResult().getCount();
        for(int i = 0; i < cnt; i++){
            int idx = i;
            iv_home_prepare.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // If have Information'
                    nickname =  result.getResult().getUserInfoList().get(idx).getUserNickName();
                    name =  result.getResult().getUserInfoList().get(idx).getUserName();
                    birthday =  result.getResult().getUserInfoList().get(idx).getBirth();
                    photoIdx = result.getResult().getUserInfoList().get(idx).getPhotoIdx();
                    useridx = result.getResult().getUserInfoList().get(idx).getUserIdx();

                    Intent intent = new Intent(HomePrepareActivity.this, HomeActivity.class);
                    intent.putExtra("jwt", jwt);
                    intent.putExtra("nickname",nickname);
                    intent.putExtra("name", name);
                    intent.putExtra("birthday", birthday);
                    intent.putExtra("photoIdx", photoIdx);
                    intent.putExtra("useridx", useridx);
                    startActivity(intent);
                }
            });
        }
        for(int i = cnt; i < 4; i++){
            iv_home_prepare.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // If dont have Information
                    Intent intent = new Intent(HomePrepareActivity.this, HomePrepareAddActivity.class);
                    intent.putExtra("jwt", jwt);
                    startActivity(intent);
                }
            });
        }
    }

    private void groupuserlistAPI(){
        apiClient = APIClient.getInstance();
        apiInterface = APIClient.getApiInterface();
        apiInterface.groupuserlistAPI(jwt).enqueue(new Callback<GroupUserListData>() {
            @Override
            public void onResponse(Call<GroupUserListData> call, Response<GroupUserListData> response) {
                GroupUserListData result = response.body();
                set_view(result);
                iv_clicked(result);
            }

            @Override
            public void onFailure(Call<GroupUserListData> call, Throwable t) {

            }
        });
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
