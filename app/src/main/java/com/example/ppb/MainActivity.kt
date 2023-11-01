package com.example.ppb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterMhs = MahasiswaAdapter(generateMahasiswa()) {
            mahasiswa ->
            Toast.makeText(this@MainActivity, mahasiswa.nama, Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("DATA_MHS", mahasiswa)
            startActivity(intent)
        }
        with(binding) {
            rvContent.apply {
                adapter = adapterMhs
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    private fun generateMahasiswa(): List<Mahasiswa> {
        return listOf(
            Mahasiswa(nama ="Farhan Hnf", nim ="22/453643/SV/12934", prodi = "TRPL", status = "Aktif", ipk =3.99, imgAddress = "https://img.freepik.com/premium-photo/confident-businessman-portrait_220507-11324.jpg"),
            Mahasiswa(nama ="Aldy Iqbal", nim ="21/364349/TK/13744", prodi = "Teknik Fisika", status = "Cuti", ipk =4.00),
            Mahasiswa(nama ="Lorem", nim ="24/713843/SA/12934", prodi = "Sastra Prancis", status = "Lulus", ipk =2.9, imgAddress = "https://e7.pngegg.com/pngimages/304/992/png-clipart-the-thinker-thinking-man-image-file-formats-photography.png"),
            Mahasiswa(nama ="Ipsoem", nim ="23/724442/SP/18263", prodi = "Ilmu Komunikasi", status = "Aktif", ipk =1.4, imgAddress = "https://purepng.com/public/uploads/large/purepng.com-thinking-manthinking-manpersongentle-men-thinkingthinking-brain-1421526976458gpxqy.png"),
            Mahasiswa(nama ="Dolor", nim ="19/264642/PA/12934", prodi = "Matematika", status = "Lulus", ipk =3.98)
        )
    }
}