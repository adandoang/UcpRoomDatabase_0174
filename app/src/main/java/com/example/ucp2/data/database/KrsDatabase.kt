package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MataKuliahDao
import com.example.ucp2.data.entity.MataKuliah

//Mendefinisikan database dengan tabel Mahasiswa
@Database(entities = [MataKuliah::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase(){

    //Mendefinisikan fungsi untuk mengakses data Mahasiswa
    abstract fun mataKuliahDao(): MataKuliahDao
    abstract fun dosenDao(): DosenDao

    companion object {
        @Volatile //Memastikan bahwa nilai variabel Instance selalu sama di
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java, //Class database
                    "KrsDatabase" //Nama database
                )
                    .build().also { Instance = it }
            })
        }
    }
}