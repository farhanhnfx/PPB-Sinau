package com.example.ppb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ppb.MainActivity.Companion.EXTRA_NAME
import com.example.ppb.ThirdActivity.Companion.EXTRA_ADDRESS
import com.example.ppb.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
        // should update UI
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val name = data?.getStringExtra(EXTRA_NAME)
            val address = data?.getStringExtra(EXTRA_ADDRESS)
            binding.txtText.text = "Hello $name di $address!"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)

        with (binding) {
            txtText.text = "Hello, $name!"

            btnToThird.setOnClickListener {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                             .apply { putExtra(EXTRA_NAME, name) }
                launcher.launch(intent)
            }
        }
    }
}