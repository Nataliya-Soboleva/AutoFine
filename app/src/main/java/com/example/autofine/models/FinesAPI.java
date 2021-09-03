package com.example.autofine.models;

import com.example.autofine.models.Fine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FinesAPI {

    //Get Fine by car data Id
    @GET("/fine/{id}")
    public Call<List<Fine>> byId(@Path("id") Long id);


}
