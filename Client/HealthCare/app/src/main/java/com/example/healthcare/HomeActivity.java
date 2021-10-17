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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private Button btn_drawer;
    private Button btn_settings;
    private Button btn_community;
    private Button btn_search;

    private DrawerLayout drawerLayout;
    private View drawerView;

    private ImageView iv_home_icon;
    private ImageView iv_home_userdata;

    private TextView tv_home_nickname;
    private TextView tv_home_name;
    private TextView tv_home_birthday;

    private Intent intent;


    private String nickname, name, birthday, jwt;

    private int photoIdx, useridx;
    private final List<Integer> image = new ArrayList<>();
    private final List<Integer> user = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init_intent();
        init_view();

        btn_clicked();



    }

    private void init_intent() {
        intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        name = intent.getStringExtra("name");
        birthday = intent.getStringExtra("birthday");
        jwt = intent.getStringExtra("jwt");
        photoIdx = intent.getIntExtra("photoIdx", 0);
        useridx = intent.getIntExtra("useridx", 0);

    }

    private void init_view() {
        btn_drawer = (Button) findViewById(R.id.btn_drawer);
        btn_community = (Button) findViewById(R.id.btn_community);
        btn_settings = (Button) findViewById(R.id.btn_settings);
        btn_search = (Button) findViewById(R.id.btn_search);

        iv_home_icon = (ImageView)findViewById(R.id.iv_home_icon);
        iv_home_userdata = (ImageView)findViewById(R.id.iv_home_userdata);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_home);
        drawerView = (View) findViewById(R.id.drawer);

        tv_home_nickname = (TextView) findViewById(R.id.tv_home_nickname);
        tv_home_name = (TextView) findViewById(R.id.tv_home_name);
        tv_home_birthday = (TextView) findViewById(R.id.tv_home_birthday);

        image.add( R.drawable.icon_plus);
        image.add( R.drawable.icon_man1);
        image.add( R.drawable.icon_man2);
        image.add( R.drawable.icon_man3);
        image.add( R.drawable.icon_woman1);
        image.add( R.drawable.icon_woman2);
        image.add( R.drawable.icon_woman3);

        user.add( R.drawable.image_user_1);
        user.add( R.drawable.image_user_2);
        user.add( R.drawable.image_user_3);
        user.add( R.drawable.image_user_4);

        tv_home_nickname.setText(nickname);
        tv_home_name.setText(name);
        tv_home_birthday.setText(birthday);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image.get(photoIdx));
        bitmap = getBitmapSquareCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
        bitmap = getBitmapCircleCrop(bitmap, bitmap.getWidth(), bitmap.getHeight());
        iv_home_icon.setImageBitmap(bitmap);

        Bitmap userdata = BitmapFactory.decodeResource(getResources(), user.get(useridx%4));
        iv_home_userdata.setImageBitmap(userdata);

    }

    private void btn_clicked() {
        btn_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        btn_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(HomeActivity.this, CommunityActivity.class);
                intent.putExtra("useridx", useridx);
                intent.putExtra("jwt", jwt);
                startActivity(intent);
            }
        });

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                intent.putExtra("jwt", jwt);
                startActivity(intent);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                intent.putExtra("jwt", jwt);
                startActivity(intent);
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
