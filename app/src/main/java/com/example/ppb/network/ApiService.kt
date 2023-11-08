package com.example.ppb.network

import com.example.ppb.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("employees") // base url dari api client ditambah employees
                      // menjadi https://dummy.restapiexample.com/public/api/v1/employees
    fun getAllUsers(): Call<Users>
}