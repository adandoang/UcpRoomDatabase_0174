package com.example.ucp2.data


import com.example.ucp2.data.entity.Dosen


object isiDDMataKuliah {
    var options: List<String> = listOf() // Inisialisasi dengan list kosong

    // Fungsi untuk mengisi options dengan data dosen dari database
    fun loadOptions(dosenList: List<Dosen>) {
        options = dosenList.map { it.nama } // Mengambil nama dosen dari daftar dosen
    }
}

