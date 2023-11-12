package com.example.ppb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppb.databinding.ActivityMainBinding
import com.example.ppb.model.Product
import com.example.ppb.model.ProductList
import com.example.ppb.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val binding by lazy { // dijalankan oleh sistem ketika dipanggil
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val client = ApiClient.getInstance()
        val response = client.getProducts(6)

        response.enqueue(object: retrofit2.Callback<ProductList> {

            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                val productList = ArrayList<Product>()
                for (product in response.body()?.data ?: arrayListOf()) {
                    productList.add(product)
                }
                val listAdapter = ProductAdapter(productList) {
                    product ->
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("DATA_PRODUCT", product)
                    startActivity(intent)
                }
                binding.rvContent.apply {
                    adapter = listAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }
}