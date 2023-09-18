package com.example.ppb

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ppb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivityLifecycle"
    private lateinit var binding: ActivityMainBinding
    companion object {
        const val EXTRA_NAME = "extra_name";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with (binding) {
            btnToSecond.setOnClickListener {
                val intentToSecondActivity =
                    Intent(
                        this@MainActivity,
                        SecondActivity::class.java
                    )
                val name = inpName.text.toString()
                intentToSecondActivity.putExtra(EXTRA_NAME, name)
                startActivity(intentToSecondActivity)
            }
            btnSendMsg.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Test hallo!!")
                startActivity(Intent.createChooser(intent, "Select an app"))
            }
            btnDial.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:08237482948")
                startActivity(intent)
            }
            btnOpenLink.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://google.com")
                startActivity(intent)
            }
        }
        Log.d(TAG, "oncreate is called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onstart is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onresume is called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onpause is called")
    }
}