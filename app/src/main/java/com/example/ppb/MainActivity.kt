package com.example.ppb

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ppb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val provinsi = arrayOf("Jawa Timur", "Jawa Tengah",
                                "Jawa Barat", "DKI Jakarta", "DIY")
        with (binding) {
            val adapterProvinsi = ArrayAdapter<String>(this@MainActivity,
                                                        android.R.layout.simple_spinner_item,
                                                        provinsi)
            spinnerProvinsi.adapter = adapterProvinsi
            val countries = resources.getStringArray(R.array.countries)
            spinnerCountry.adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_item, countries)

            // get selected item
            spinnerCountry.onItemSelectedListener =
                object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(this@MainActivity, countries[position], Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


            // ketika button diklik baru muncul kalender
            btnShowCalendar.setOnClickListener {
                val datePicker = DatePicker()
                datePicker.show(supportFragmentManager, "datePicker")
            }
            btnShowTimePicker.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager, "timePicker")
            }

            datePicker.init(datePicker.year, datePicker.month, datePicker.dayOfMonth) {
                _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month+1}/$year"
                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }

            // setontime punya 3 respond yaitu view, hourofDay, dan minute
            // biasanya view diganti dengan _ karena tidak diubah atau tidak dipakai
            timePickerView.setOnTimeChangedListener {
                _, hourOfDay, minute ->
                val selectedTime = "$hourOfDay:$minute"
                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDateSet(
        view: android.widget.DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val selectedDate = "$year/${month+1}/$dayOfMonth"
        Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
    }

    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = "$hourOfDay:$minute"
        Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
    }
}