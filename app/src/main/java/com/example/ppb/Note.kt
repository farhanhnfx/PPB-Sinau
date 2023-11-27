package com.example.ppb

data class Note(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var category: String = "", // isine pemasukan / pengeluaran
    var amount: Int = 0,
)
