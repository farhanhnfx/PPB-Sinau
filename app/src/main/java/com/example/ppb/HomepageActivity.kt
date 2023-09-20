package com.example.ppb

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.example.ppb.RegisterActivity.Companion.EXTRA_EMAIL
import com.example.ppb.RegisterActivity.Companion.EXTRA_PHONE
import com.example.ppb.RegisterActivity.Companion.EXTRA_USERNAME
import com.example.ppb.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val email = intent.getStringExtra(EXTRA_EMAIL)
        val phone = intent.getStringExtra(EXTRA_PHONE)

        with (binding) {
            txtWelcome.text = changeText(txtWelcome.text.toString(), "username", username.toString())
            txtEmailActivated.text = changeText(txtEmailActivated.text.toString(), "email", email.toString())
            txtPhoneRegistered.text = changeText(txtPhoneRegistered.text.toString(), "phone", phone.toString())
            btnLogout.setOnClickListener {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
    }
    private fun changeText(fullText: String, old: String, new: String): SpannableString {
        val newText = fullText.replace(old, new)
        val span = SpannableString(newText)
        val start = newText.indexOf(new)
        val end = start + new.length
        val color = Color.parseColor("#525BFF")
        span.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return span
    }
}