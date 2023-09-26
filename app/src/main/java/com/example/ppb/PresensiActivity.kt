package com.example.ppb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ppb.databinding.ActivityPresensiBinding
import com.example.ppb.databinding.ActivityRegisterBinding

class PresensiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPresensiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPresensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with (binding) {
            var selectedDate = ""
            var selectedTime = ""
            val alasanArray = resources.getStringArray(R.array.alasan_list)
            spinnerAlasan.adapter = ArrayAdapter<String>(this@PresensiActivity, android.R.layout.simple_spinner_item, alasanArray)
            spinnerAlasan.onItemSelectedListener =
                object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                        if (position != 0) {
                            editKeterangan.visibility = View.VISIBLE
                        }
                        else {
                            editKeterangan.visibility = View.GONE
                        }
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
            datePickerView.init(datePickerView.year, datePickerView.month, datePickerView.dayOfMonth) {
                    _, year, month, dayOfMonth ->
                selectedDate = "$dayOfMonth ${monthToString(month)} $year"
            }
            timePickerView.setOnTimeChangedListener {
                    _, hourOfDay, minute ->
                selectedTime = "$hourOfDay:$minute"
            }
            btnSubmit.setOnClickListener {
                if (selectedDate != "" && selectedTime != "") {
                    Toast.makeText(
                        this@PresensiActivity,
                        "Presensi berhasil ${selectedDate} jam ${selectedTime}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    Toast.makeText(this@PresensiActivity, "Belum memilih waktu presensi!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun monthToString(month: Int): String {
        when(month) {
            0 -> return "Januari"
            1 -> return "Februari"
            2 -> return "Maret"
            3 -> return "April"
            4 -> return "Mei"
            5 -> return "Juni"
            6 -> return "Juli"
            7 -> return "Agustus"
            8 -> return "September"
            9 -> return "Oktober"
            10 -> return "November"
            11 -> return "Desember"
            else -> return "Tidak valid"
        }
    }
}