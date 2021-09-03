package com.example.autofine;

import com.example.autofine.models.CarDataAPI;
import com.example.autofine.models.FinesAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static RetrofitInstance retrofitInstance;

    private Retrofit retrofit;


    private RetrofitInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://app-autofine-maven.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitInstance getInstance()
    {
        if(retrofitInstance == null)
        {
            retrofitInstance = new RetrofitInstance();
            return retrofitInstance;
        }
        return retrofitInstance;
    }

    public FinesAPI getFineApiInterface()
    {
        return retrofit.create(FinesAPI.class);
    }

    public CarDataAPI getCarDataApiInterface() { return retrofit.create(CarDataAPI.class);  }


}
