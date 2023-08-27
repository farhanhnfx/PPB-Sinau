package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ppb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val username = "farhanh"
    private val password = "pw123"


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var getUsername: String
        lateinit var getPass: String

        with (binding) {
            // TUGAS
            btnLogin.setOnClickListener {
                getUsername = inpUsername.text.toString()
                getPass = inpPass.text.toString()
                if (getUsername == username && getPass == password) {
                    Toast.makeText(this@MainActivity, "Login Sukses!", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@MainActivity, "Username atau Password Salah!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}