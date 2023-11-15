package com.example.ppb

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "note")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "category")
    val category: String, // isine pemasukan / pengeluaran
    @ColumnInfo(name = "amount")
    val amount: Int,
//    @ColumnInfo(name = "date")
//    val date: java.util.Date
)