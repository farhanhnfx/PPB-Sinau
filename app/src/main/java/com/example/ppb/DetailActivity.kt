package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ppb.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            val data = intent.getParcelableExtra<Mahasiswa>("DATA_MHS")
            if (data != null) {
                txtNama.text = data.nama
                txtNim.text = data.nim
                txtProdi.text = data.prodi
                txtStatus.text = data.status
                txtIpk.text = data.ipk.toString()

                if (data.imgAddress != null) {
                    imgMhs.setBackgroundResource(0)
                    Glide.with(this@DetailActivity).load(data.imgAddress).into(imgMhs)
                }
                else {
                    imgMhs.setBackgroundResource(R.drawable.ic_profile)
                }
            }
        }
    }
}