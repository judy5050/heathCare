package com.example.healthcare;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomePrepareAddActivity extends AppCompatActivity {

    private Button btn_home_prepare_add_checknickname;

    private EditText et_home_prepare_add_nickname;
    private EditText et_home_prepare_add_name;

    private TextView tv_home_prepare_add_checknickname;

    private Spinner spn_home_prepare_add_year;
    private Spinner spn_home_prepare_add_month;
    private Spinner spn_home_prepare_add_day;

    private ArrayList<Integer> list_year, list_month, list_day;
    private ArrayAdapter<Integer> adapter_year, adapter_month, adapter_day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_prepare_add);

        init_view();
        init_spinner();

        //set ImageView and Save Button


    }

    private void init_view(){
        btn_home_prepare_add_checknickname = (Button)findViewById(R.id.btn_home_prepare_add_checknickname);

        et_home_prepare_add_nickname = (EditText)findViewById(R.id.et_home_prepare_add_nickname);
        et_home_prepare_add_name = (EditText)findViewById(R.id.et_home_prepare_add_name);

        tv_home_prepare_add_checknickname = (TextView) findViewById(R.id.tv_home_prepare_add_checknickname);

        spn_home_prepare_add_year = (Spinner)findViewById(R.id.spn_home_prepare_add_year);
        spn_home_prepare_add_month = (Spinner)findViewById(R.id.spn_home_prepare_add_month);
        spn_home_prepare_add_day = (Spinner)findViewById(R.id.spn_home_prepare_add_day);
    }

    private void init_spinner(){

        list_year = new ArrayList<>();
        for(int i = 2021; i >= 1900; i--) list_year.add(i);
        adapter_year = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_year);
        spn_home_prepare_add_year.setAdapter(adapter_year);

        list_month = new ArrayList<>();
        for(int i = 1; i <= 12; i++) list_month.add(i);
        adapter_month = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list_month);
        spn_home_prepare_add_month.setAdapter(adapter_month);
        // Need to set Leap year
        spn_home_prepare_add_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Integer month = adapter_month.getItem(position);

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
    }

}
