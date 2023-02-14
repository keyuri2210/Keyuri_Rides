package com.example.rides

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api/vehicle/random_vehicle")
    fun getAllPhotos(@Query("size") email: Int): Call<List<VehicleClass?>?>?
}