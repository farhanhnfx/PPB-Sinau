package com.example.ppb

import android.os.Parcel
import android.os.Parcelable

data class Mahasiswa(
    val nama: String? = "",
    val nim: String? = "",
    val prodi: String? = "",
    val status: String? = "-",
    val ipk: Double = 0.0,
    val imgAddress: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(nama)
        parcel.writeString(nim)
        parcel.writeString(prodi)
        parcel.writeString(status)
        parcel.writeDouble(ipk)
        parcel.writeString(imgAddress)
    }

    companion object CREATOR : Parcelable.Creator<Mahasiswa> {
        override fun createFromParcel(parcel: Parcel): Mahasiswa {
            return Mahasiswa(parcel)
        }

        override fun newArray(size: Int): Array<Mahasiswa?> {
            return arrayOfNulls(size)
        }
    }

}
