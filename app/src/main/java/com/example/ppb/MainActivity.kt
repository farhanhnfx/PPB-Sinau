package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ppb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var number = 0

    private val username = "farhanh"
    private val password = "pw123"


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with (binding) {
            txtNumber.text = number.toString()

            // ganti bg
//            txtNumber.setBackgroundResource(R.color.teal_700)

            btnCount.setOnClickListener {
                number++
                txtNumber.text = number.toString()
            }
            btnToast.setOnClickListener {
                Toast.makeText( this@MainActivity, number.toString(), Toast.LENGTH_SHORT).show()
            }

            // TUGAS
            btnLogin.setOnClickListener {
                var getUsername = inpUsername.text.toString()
                var getpass = inpPass.text.toString()
                if (getUsername.equals(username) && getpass.equals(password)) {
                    Toast.makeText(this@MainActivity, "Login Sukses!", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@MainActivity, "Username atau Password Salah!", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}