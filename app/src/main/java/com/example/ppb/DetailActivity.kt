package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ppb.databinding.ActivityDetailBinding
import com.example.ppb.model.Product

class DetailActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            val data = intent.getParcelableExtra<Product>("DATA_PRODUCT")
            if (data != null) {
                txtProductName.text = data.title
                txtProductDesc.text = "'${data.description}'"
                txtProductBrand.text = data.brand
                txtProductPrice.text = "$${data.price}"
                txtProductCategory.text = data.category
                txtProductRating.text = data.rating.toString()
                txtProductStock.text = data.stock.toString()
                imgProductThumbnail.setBackgroundResource(0)
                Glide.with(this@DetailActivity).load(data.thumbnail).into(imgProductThumbnail)
            }
        }
    }
}