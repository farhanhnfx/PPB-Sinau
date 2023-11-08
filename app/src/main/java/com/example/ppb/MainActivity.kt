package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.ppb.databinding.ActivityMainBinding
import com.example.ppb.model.Users
import com.example.ppb.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    val binding by lazy { // dijalankan oleh sistem ketika dipanggil
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val client = ApiClient.getInstance()
        val response = client.getAllUsers()
        val test = client.getSomePic(4) // mengisi query yang didefinisikan

        response.enqueue(object: retrofit2.Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val namaUsers = ArrayList<String>()
                for (i in response.body()?.data ?: arrayListOf()) { // ?: kalau null mengembalikan list kosong
                    namaUsers.add(i.employeeName)
                }
                val listAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, namaUsers)
                binding.viewList.adapter = listAdapter
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }
}