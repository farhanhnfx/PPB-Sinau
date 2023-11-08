package com.example.ppb.network

import com.example.ppb.model.Data
import com.example.ppb.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("employees") // base url dari api client ditambah employees
                      // menjadi https://dummy.restapiexample.com/public/api/v1/employees
    fun getAllUsers(): Call<Users>
    fun getSomePic(@Query("limit") int:Int): Call<List<Data>> // misal pake query di url-nya
}