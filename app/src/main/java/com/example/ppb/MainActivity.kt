package com.example.ppb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ppb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
    private lateinit var txtResult: TextView
    private lateinit var txtInput: TextView
    private lateinit var txtProcess: TextView
    private lateinit var txtLastNum: TextView
    private var lastNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtResult = findViewById<TextView>(R.id.txt_result)
        txtInput = findViewById<TextView>(R.id.txt_input)
        txtProcess = findViewById<TextView>(R.id.txt_process)
        txtLastNum = findViewById<TextView>(R.id.txt_lastNum)
    }

    fun inputNum(view: View) {
        val button = view as Button
        if (txtInput.text != "0") {
            txtInput.text = txtInput.text.toString() + button.text.toString()
        }
        else {
            txtInput.text = button.text.toString()
        }
    }

    fun operatorProcess(view: View) {
        val button = view as Button

        if (button.text.toString() == "C") {
            txtResult.text = "Belum ada"
            txtInput.text = "0"
            txtLastNum.text = "0"
        }
        else {
            lastNum = txtInput.text.toString().toInt()
            txtResult.text = button.text
            txtInput.text = "0"
            txtLastNum.text = lastNum.toString()
        }
        txtProcess.text = "Operasi:"
    }

    fun getResult(view: View) {
        var result = 0
        var decimalNum = false
        var notDefined = false
        val inp = txtInput.text.toString().toInt()
        when (txtResult.text.toString()) {
            "+" -> {
                result = lastNum + inp
                txtLastNum.text = "$lastNum + $inp"
            }
            "-" -> {
                result = lastNum - inp
                txtLastNum.text = "$lastNum - $inp"
            }
            "*" -> {
                result = lastNum * inp
                txtLastNum.text = "$lastNum * $inp"
            }
            "/" -> {
                if (inp != 0) {
                    result = lastNum / inp
                    if (lastNum % inp != 0) {
                        decimalNum = true
                    }
                }
                else {
                    notDefined = true
                }
                txtLastNum.text = "$lastNum / $inp"
            }
        }
        if (!decimalNum) {
            txtResult.text = result.toString()
        }
        else {
            txtResult.text = (lastNum.toDouble() / inp).toString()
        }
        if (notDefined) {
            txtResult.text = "Tak terdefinisi"
        }
        txtInput.text = "0"
        lastNum = result
        txtProcess.text = "Hasil:"
    }
}