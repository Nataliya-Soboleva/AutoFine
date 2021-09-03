package com.example.autofine.models;

import com.example.autofine.models.CarData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CarDataAPI {

    //get car data id by plate number
    @GET("/cardata/{plateNumber}")
    public Call<CarData> findByPlateNumber(@Path("plateNumber")String plateNumber);


}
