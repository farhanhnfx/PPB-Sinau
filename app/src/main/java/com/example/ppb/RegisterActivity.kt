package com.example.ppb

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ppb.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
        if (result.resultCode == Activity.RESULT_CANCELED) {
            binding.editUsername.text = null
            binding.editEmail.text = null
            binding.editPhone.text = null
            binding.editPassword.text = null
            binding.checkboxAgreeTnc.isChecked = false
            binding.btnRegister.setBackgroundColor(Color.parseColor("#d9d9d9"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with (binding) {
            btnRegister.isEnabled = false
            btnRegister.setBackgroundColor(Color.parseColor("#d9d9d9"))

            btnRegister.setOnClickListener {
                val username = editUsername.text.toString()
                val email = editEmail.text.toString()
                val phone = editPhone.text.toString()
                val password = editPassword.text.toString()

                if (username == "" || email == "" || phone == "" || password == "") {
                    Toast.makeText(this@RegisterActivity, "Please fill all in all columns", Toast.LENGTH_SHORT).show()
                }
                else {
                    val intentToHomepageActivity =
                        Intent(this@RegisterActivity, HomepageActivity::class.java)
                            .apply {
                                putExtra(EXTRA_USERNAME, username)
                                putExtra(EXTRA_EMAIL, email)
                                putExtra(EXTRA_PHONE, phone)
                            }
                    launcher.launch(intentToHomepageActivity)
                }
            }

            checkboxAgreeTnc.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked) {
                    btnRegister.isEnabled = false
                    btnRegister.setBackgroundColor(Color.parseColor("#d9d9d9"))
                }
                else {
                    btnRegister.isEnabled = true
                    btnRegister.setBackgroundColor(Color.parseColor("#525BFF"))
                }
            }
        }
    }
    
}