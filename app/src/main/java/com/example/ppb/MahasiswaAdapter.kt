package com.example.ppb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ppb.databinding.ItemMahasiswaBinding

typealias OnClickMahasiswa = (Mahasiswa) -> Unit
class MahasiswaAdapter(private val listMahasiswa: List<Mahasiswa>, private val onClickMahasiswa: OnClickMahasiswa) : RecyclerView.Adapter<MahasiswaAdapter.ItemMahasiswaViewHolder>() {
    inner class ItemMahasiswaViewHolder(private val binding: ItemMahasiswaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mahasiswa: Mahasiswa) {
            with(binding) {
                txtMhsName.text = mahasiswa.nama
                txtMhsAngkatan.text = "Angkatan 20" + mahasiswa.nim?.substring(0, 2)
                txtMhsIpk.text = mahasiswa.ipk.toString()
                if (mahasiswa.ipk > 3) {
                    txtMhsIpk.setBackgroundResource(R.drawable.card_gpa_green)
                }
                else if (mahasiswa.ipk > 2) {
                    txtMhsIpk.setBackgroundResource(R.drawable.card_gpa_orange)
                }
                else {
                    txtMhsIpk.setBackgroundResource(R.drawable.card_gpa_red)
                }

                if (mahasiswa.imgAddress != null) {
                    imgMhs.setBackgroundResource(0)
                    Glide.with(itemView).load(mahasiswa.imgAddress).into(imgMhs)
                }
                else {
                    imgMhs.setBackgroundResource(R.drawable.ic_profile)
                }

                itemView.setOnClickListener {
                    onClickMahasiswa(mahasiswa)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMahasiswaViewHolder {
        val binding = ItemMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMahasiswaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

    override fun onBindViewHolder(holder: ItemMahasiswaViewHolder, position: Int) {
        holder.bind(listMahasiswa[position])
    }
}