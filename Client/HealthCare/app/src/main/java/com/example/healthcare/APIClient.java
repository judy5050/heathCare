package com.example.healthcare;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIClient instance = null;
    private static APIInterface apiInterface;
    private static String baseUrl = "http://3.34.96.113/";

    private APIClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
        apiInterface = retrofit.create(APIInterface.class);
    }

    public static APIClient getInstance(){
        if(instance == null){
            instance = new APIClient();
        }
        return instance;
    }

    public static APIInterface getApiInterface(){
        return apiInterface;
    }
}
