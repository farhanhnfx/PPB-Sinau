package com.example.ppb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ppb.MainActivity.Companion.EXTRA_NAME
import com.example.ppb.databinding.ActivityMainBinding
import com.example.ppb.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    companion object {
        const val EXTRA_ADDRESS = "extra_address"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)

        val name = intent.getStringExtra(EXTRA_NAME)
        with (binding) {
            btnDismiss.setOnClickListener {
                val intent = Intent()
                val address = inpAddress.text.toString()
                intent.putExtra(EXTRA_NAME, name)
                intent.putExtra(EXTRA_ADDRESS, address)

                setResult(Activity.RESULT_OK, intent) // mengirim sesuatu yang dibungkus ke dalam sebuah intent
                finish() // jika hanya finish tok tanpa setresult maka
                         // tidak memberitahu bahwa ada perubahan
            }
        }
        setContentView(binding.root)
    }
}