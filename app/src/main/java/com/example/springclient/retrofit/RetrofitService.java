package com.example.springclient.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static Retrofit retrofit; //creating retrofit object

    public RetrofitService() {
        InitializeRetrofit();
    }

    private void InitializeRetrofit() {
        retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.5.7:8086/productDetails/") //this is tomcat server port 8086
                .addConverterFactory(GsonConverterFactory.create(new Gson())) //we use converter factory because we are using json format
                .build();


    }



    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }
}
